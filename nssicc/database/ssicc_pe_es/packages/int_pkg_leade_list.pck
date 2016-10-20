CREATE OR REPLACE PACKAGE "INT_PKG_LEADE_LIST" AS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  W_FILAS    NUMBER := 1000;

/*********************************************************************************
Descripcion       : Genera interfaz de cabecera de pedidos para datamart (LLI-1)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( LLI )
            psCodigoInterfaz   = Código de interfaz ( LLI-1)
            psNombreArchivo    = Archivo de salida ( LLI-1-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CMori CSVD FFVV
*********************************************************************************/
PROCEDURE INT_PR_LLI_ENVIO_VENTA_PERIO
(
  psCodigoPais              VARCHAR2,
  psCodigosistema           VARCHAR2,
  psCodigoInterfaz          VARCHAR2,
  psNombreArchivo           VARCHAR2,
  psCodigoPeriodo           VARCHAR2,
  psCodigoMarcaLeaderList   VARCHAR2,
  psNumUltimosDigitos       VARCHAR2
);

/*********************************************************************************
Descripcion       : Genera interfaz de cabecera de pedidos para datamart (LLI-2)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( LLI )
            psCodigoInterfaz   = Código de interfaz ( LLI-2)
            psNombreArchivo    = Archivo de salida ( LLI-2-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CMori CSVD FFVV
*********************************************************************************/
PROCEDURE INT_PR_LLI_ENVIO_VENTA_DIARI
(
  psCodigoPais              VARCHAR2,
  psCodigosistema           VARCHAR2,
  psCodigoInterfaz          VARCHAR2,
  psNombreArchivo           VARCHAR2,
  psCodigoPeriodo           VARCHAR2,
  psFechaFacturacionInicial VARCHAR2,
  psFechaFacturacionFinal   VARCHAR2,
  psCodigoMarcaLeaderList   VARCHAR2,
  psNumUltimosDigitos       VARCHAR2
);
END INT_PKG_LEADE_LIST;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_LEADE_LIST" AS

/*********************************************************************************
Descripcion       : Genera interfaz de cabecera de pedidos para datamart (LLI-1)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( LLI )
            psCodigoInterfaz   = Código de interfaz ( LLI-1)
            psNombreArchivo    = Archivo de salida ( LLI-1-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CMori CSVD FFVV
*********************************************************************************/
PROCEDURE INT_PR_LLI_ENVIO_VENTA_PERIO
(
  psCodigoPais              VARCHAR2,
  psCodigosistema           VARCHAR2,
  psCodigoInterfaz          VARCHAR2,
  psNombreArchivo           VARCHAR2,
  psCodigoPeriodo           VARCHAR2,
  psCodigoMarcaLeaderList   VARCHAR2,
  psNumUltimosDigitos       VARCHAR2
)
IS
CURSOR c_Interfaz IS
    SELECT psCodigoMarcaLeaderList codigoMarca,
           geco.cod_peri periodo,
               geco.cod_bpcs codigoProducto,
           SUM( geco.num_unid_vent ) unidadesVendidas,
           SUM( geco.imp_neto_vent ) ventaNetaReal,
           SUM( geco.num_unid_falt ) unidadesFaltantes,
           SUM( geco.imp_neto_falt ) montoFaltante,
           geco.cod_tipo_ofert tipoOferta
    FROM int_gener_diari_conso geco
    WHERE cod_peri = psCodigoPeriodo
    GROUP BY geco.cod_peri,
             geco.cod_tipo_ofert,
                 geco.cod_bpcs
    UNION
    SELECT codigomarca, periodo, codigoproducto, unidadesvendidas, ventanetareal, unidadesfaltantes, montofaltante, tipooferta
      FROM (
            SELECT DISTINCT
                   psCodigoMarcaLeaderList codigoMarca,
                   seg_perio_corpo.cod_peri periodo,
                   DECODE( cod_sap_equi, NULL, SUBSTR( TRIM( mae_produ.codi_anti ),-9 ), codigo_bpcs_equi ) codigoproducto,
                   0 unidadesvendidas,
                   0 ventanetareal,
                   0 unidadesfaltantes,
                   0 montofaltante,
                   DECODE( cod_sap_equi, NULL, pre_tipo_ofert.cod_tipo_ofer, cod_tipo_ofer_equi ) tipooferta
                   ,cod_sap_equi
              FROM pre_ofert,
                   pre_ofert_detal,
                   pre_matri_factu_cabec,
                   cra_perio,
                   mae_produ,
                   pre_tipo_ofert,
                   seg_perio_corpo,
                   seg_pais,
                   (
                    SELECT g.oid_deta_ofer,
                           c.oid_deta_ofer oid_deta_ofer_equi,
                           c.val_codi_vent val_codi_vent_equi,
                           u.cod_tipo_ofer cod_tipo_ofer_equi,
                           j.cod_sap cod_sap_equi,
                           SUBSTR( TRIM(j.codi_anti),-9 ) Codigo_Bpcs_Equi
                      FROM pre_matri_factu_cabec a,
                           pre_ofert b,
                           pre_ofert_detal c,
                           pre_matri_factu d,
                           pre_ofert_detal g,
                           pre_matri_factu h,
                           pre_matri_codig_alter i,
                           mae_produ j,
                           mae_produ k,
                           pre_tipo_ofert t,
                           pre_tipo_ofert u,
                           pre_prod_alter_ice equi,
                           bas_pais,
                           cra_perio e,
                           seg_perio_corpo f
                     WHERE a.oid_cabe = b.mfca_oid_cabe
                       AND b.oid_ofer = c.ofer_oid_ofer
                       AND c.oid_deta_ofer = d.ofde_oid_deta_ofer
                       AND d.oid_matr_fact = i.mafa_oid_cod_ppal
                       AND i.mafa_oid_cod_alte = h.oid_matr_fact
                       AND h.ofde_oid_deta_ofer = g.oid_deta_ofer
                       AND c.prod_oid_prod = j.oid_prod
                       AND g.prod_oid_prod = k.oid_prod
                       AND g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
                       AND c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
                       AND j.cod_sap = equi.cod_sap_ppal
                       AND f.oid_peri = e.peri_oid_peri
                       AND a.perd_oid_peri = e.oid_peri
                       --
                       AND cod_pais = psCodigoPais
                       AND ind_equi = 1
                       AND cod_peri >= cod_peri_acti_equi
              AND cod_peri             = psCodigoPeriodo
          UNION
            SELECT c.oid_deta_ofer,
                   g.oid_deta_ofer oid_deta_ofer_equi,
                   g.val_codi_vent val_codi_vent_equi,
                   t.cod_tipo_ofer cod_tipo_ofer_equi,
                   k.cod_sap cod_sap_equi,
                   SUBSTR( TRIM(k.codi_anti),-9 ) Codigo_Bpcs_Equi
              FROM pre_matri_factu_cabec a,
                   pre_ofert             b,
                   pre_ofert_detal       c,
                   pre_matri_factu       d,
                   pre_ofert_detal       g,
                   pre_matri_factu       h,
                   pre_matri_codig_alter i,
                   mae_produ             j,
                   mae_produ             k,
                   pre_tipo_ofert        t,
                   pre_tipo_ofert        u,
                   pre_prod_alter_ice    equi,
                   bas_pais,
                   cra_perio             e,
                   seg_perio_corpo       f
             WHERE a.oid_cabe           = b.mfca_oid_cabe
               AND b.oid_ofer           = c.ofer_oid_ofer
               AND c.oid_deta_ofer      = d.ofde_oid_deta_ofer
               AND d.oid_matr_fact      = i.mafa_oid_cod_ppal
               AND i.mafa_oid_cod_alte  = h.oid_matr_fact
               AND h.ofde_oid_deta_ofer = g.oid_deta_ofer
               AND c.prod_oid_prod      = j.oid_prod
               AND g.prod_oid_prod      = k.oid_prod
               AND g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
               AND c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
               AND j.cod_sap            = equi.cod_sap_alte
               AND f.oid_peri           = e.peri_oid_peri
               AND a.perd_oid_peri      = e.oid_peri
               --
              AND cod_pais             = psCodigoPais
              AND ind_equi             = 1
              AND cod_peri             >= cod_peri_acti_equi
              AND cod_peri             = psCodigoPeriodo
                   ) equi

                 WHERE pre_ofert.oid_ofer = pre_ofert_detal.ofer_oid_ofer
                   AND pre_matri_factu_cabec.oid_cabe = pre_ofert.mfca_oid_cabe
                   AND cra_perio.oid_peri = pre_matri_factu_cabec.perd_oid_peri
                   AND mae_produ.oid_prod = pre_ofert_detal.prod_oid_prod
                   AND pre_tipo_ofert.oid_tipo_ofer = pre_ofert_detal.tofe_oid_tipo_ofer
                   AND seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri
                   AND seg_pais.oid_pais = mae_produ.pais_oid_pais
                   AND seg_pais.cod_pais = psCodigoPais
                   AND seg_perio_corpo.cod_peri = psCodigoPeriodo
                   AND pre_ofert_detal.oid_deta_ofer = equi.oid_deta_ofer(+)
                 ) data
                 WHERE NOT EXISTS (
                                    SELECT 1
                                      FROM (
                                             SELECT DISTINCT
                                                    geco.cod_peri periodo,
                                                    geco.cod_bpcs codigoProducto,
                                                    geco.cod_tipo_ofert tipoOferta
                                               FROM int_gener_diari_conso geco
                                              WHERE cod_peri = psCodigoPeriodo
                                           ) venta
                                     WHERE venta.periodo = data.periodo
                                       AND venta.codigoproducto = data.codigoproducto
                                       AND venta.tipooferta = data.tipooferta
                                  )
                 AND cod_sap_equi is null;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);

BEGIN
  -- Procedimiento inicial para generar interfaz
  lbAbrirUtlFile := TRUE;

  -- Generando Archivo de Texto (Detalle)
  OPEN c_Interfaz;
       LOOP
          FETCH c_Interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
             /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazRecord(x).codigoMarca       ||';'||
                                interfazRecord(x).periodo           ||';'||
                                interfazRecord(x).codigoProducto    ||';'||
                                interfazRecord(x).unidadesVendidas  ||';'||
                                interfazRecord(x).ventaNetaReal     ||';'||
                                interfazRecord(x).unidadesFaltantes ||';'||
                                interfazRecord(x).montoFaltante     ||';'||
                                interfazRecord(x).tipoOferta;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_Interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_Interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose( V_HANDLE );
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_LLI_ENVIO_VENTA_PERIO: '||ls_sqlerrm);
END INT_PR_LLI_ENVIO_VENTA_PERIO;

/*********************************************************************************
Descripcion       : Genera interfaz de cabecera de pedidos para datamart (LLI-2)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( LLI )
            psCodigoInterfaz   = Código de interfaz ( LLI-2)
            psNombreArchivo    = Archivo de salida ( LLI-2-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CMori CSVD FFVV
*********************************************************************************/
PROCEDURE INT_PR_LLI_ENVIO_VENTA_DIARI
(
  psCodigoPais              VARCHAR2,
  psCodigosistema           VARCHAR2,
  psCodigoInterfaz          VARCHAR2,
  psNombreArchivo           VARCHAR2,
  psCodigoPeriodo           VARCHAR2,
  psFechaFacturacionInicial VARCHAR2,
  psFechaFacturacionFinal   VARCHAR2,
  psCodigoMarcaLeaderList   VARCHAR2,
  psNumUltimosDigitos       VARCHAR2
)
IS
CURSOR c_Interfaz(fechaFacturacionInicial VARCHAR2, fechaFacturacionFinal VARCHAR2) IS
    SELECT psCodigoMarcaLeaderList codigoMarca,
           geco.cod_peri periodo,
           CASE
              WHEN LENGTH( geco.cod_bpcs ) > psNumUltimosDigitos THEN
                   SUBSTR( geco.cod_bpcs, LENGTH( geco.cod_bpcs ) - psNumUltimosDigitos + 1, psNumUltimosDigitos )
              ELSE geco.cod_bpcs
           END codigoProducto,
           geco.cod_tipo_ofert tipoOferta,
           SUM( geco.num_unid_vent ) unidadesVendidas,
           SUM( geco.imp_neto_vent ) importeVenta
    FROM int_gener_diari_conso geco
    WHERE geco.cod_peri = psCodigoPeriodo
      AND geco.fec_fact BETWEEN fechaFacturacionInicial AND fechaFacturacionFinal
    GROUP BY geco.cod_peri,
             geco.cod_prod,
             geco.cod_tipo_ofert,
             CASE
                WHEN LENGTH( geco.cod_bpcs ) > psNumUltimosDigitos THEN
                     SUBSTR( geco.cod_bpcs, LENGTH( geco.cod_bpcs ) - psNumUltimosDigitos + 1, psNumUltimosDigitos )
                ELSE geco.cod_bpcs
              END;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

lsFechaFacturacionInicial   VARCHAR2(8);
lsFechaFacturacionFinal     VARCHAR2(8);

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);

BEGIN
  -- Procedimiento inicial para generar interfaz
  lbAbrirUtlFile := TRUE;

 lsFechaFacturacionInicial := TO_CHAR( TO_DATE( psFechaFacturacionInicial, 'DD/MM/YYYY' ), 'YYYYMMDD' );
 lsFechaFacturacionFinal := TO_CHAR( TO_DATE( psFechaFacturacionFinal, 'DD/MM/YYYY' ), 'YYYYMMDD' );

  -- Generando Archivo de Texto (Detalle)
  OPEN c_Interfaz(lsFechaFacturacionInicial, lsFechaFacturacionFinal);
       LOOP
          FETCH c_Interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
             /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazRecord(x).codigoMarca       ||';'||
                                interfazRecord(x).periodo           ||';'||
                                interfazRecord(x).codigoProducto    ||';'||
                                interfazRecord(x).tipoOferta        ||';'||
                                interfazRecord(x).unidadesVendidas  ||';'||
                                RPAD(TRIM(TO_CHAR(interfazRecord(x).importeVenta,'9999999990.00')),13,' ')
                                ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_Interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_Interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose( V_HANDLE );
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_LLI_ENVIO_VENTA_DIARI: '||ls_sqlerrm);
END INT_PR_LLI_ENVIO_VENTA_DIARI;

END INT_PKG_LEADE_LIST;
/
