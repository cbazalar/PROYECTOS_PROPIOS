CREATE OR REPLACE PACKAGE INT_PKG_PRY IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/************************************************************************************
Descripcion       : Genera Interfase de Envio de proyecciones parciales PRY-5
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_PROYE_PARCI(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2);

/************************************************************************************
Descripcion       : Genera Interfase de Envio de cronograma facturacion PRY-6
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_CRONO_FACTU(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2);

/************************************************************************************
Descripcion       : Genera Interfase de Envio de informaciond eventa proyeccion PRY-7
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_INFOR_VENT(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2);

/************************************************************************************
Descripcion       : Genera Interfase de Envio de pedidos diarios y acumulados PRY-8
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_PEDID_DIACU(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio Información de Nuevos Faltantes

  Fecha Creacion    : 24/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_pry_envi_infor_nuevo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  );
  
/************************************************************************************
Descripcion       : Genera Interfase de Envio de Demanda Codigo cerrados PRY-10
Fecha Creacion    : 17/08/2015
Autor             : Karina Valencia
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion 
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_DEMA_CODIGO(psCodigoPais          VARCHAR2,
	                                    psCodigoPeriodo        VARCHAR2,
	                                    psFechaFacturacion     VARCHAR2,	                                
                                      psCodigoSistema        VARCHAR2,
                                      psCodigoInterfaz       VARCHAR2,
                                      psNombreArchivo        VARCHAR2,
                                      psCentroPais			     VARCHAR2
 );

END INT_PKG_PRY;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_PRY IS

/************************************************************************************
Descripcion       : Genera Interfase de Envio de proyecciones parciales PRY-5
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_PROYE_PARCI(psCodigoPais         VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                     psCodigoInterfaz       VARCHAR2,
                                     psNombreArchivo        VARCHAR2,
                                     psCentroPais			      VARCHAR2)
   IS
   CURSOR c_interfaz IS
 select nvl((SELECT DISTINCT e1.val_i18n
                     FROM bel_almac             a1,
                          app_confi_centr_distr b1,
                          ape_confi_liafp_cabec c1,
                          ape_confi_liafp_detal d1,
                          gen_i18n_sicc_pais e1
                    WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
                      AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                      AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                      and a1.oid_alma=e1.val_oid and e1.attr_enti='BEL_ALMAC'
                      AND d1.zzon_oid_zona =xx.oid),
                  psCentroPais) AS CEN_PAIS,
                   xx.cod_peri,
                   xx.fec_fact,
                   to_number(lpad(xx.cod_regi_zona,6,'0')) cod_regi_zona,
                   to_number(lpad(xx.ped_esti,5,'0')) ped_esti,
                   to_number(lpad(xx.ped_real,5,'0')) ped_real,
                   rpad(xx.ind_cier,1,' ') ind_cier
   from (SELECT
               psCodigoPeriodo AS COD_PERI,
               TO_CHAR(TO_DATE(psFechaFacturacion,'dd/MM/yyyy'),'yyyymmdd') AS FEC_FACT,
               ZON_ZONA.OID_ZONA AS OID,
               ZON_ZONA.COD_ZONA AS COD_REGI_ZONA,
               PRY_PKG_PROYE_PARCI.PRY_FN_NUMER_PEDID_ESTIM(psCodigoPeriodo, ZON_REGIO.COD_REGI, ZON_ZONA.COD_ZONA) AS PED_ESTI,
               COUNT(1) AS PED_REAL,
               PRY_PKG_PROYE_PARCI.PRY_FN_INDIC_CIERR(psCodigoPeriodo,
                                                      ZON_REGIO.COD_REGI,
                                                      psCodigoPais) AS IND_CIER
          FROM PED_SOLIC_CABEC,
               PED_ESTAD_SOLIC,
               ZON_ZONA,
               ZON_REGIO,
               CRA_PERIO,
               SEG_MARCA,
               SEG_CANAL,
               PED_TIPO_SOLIC_PAIS,
               PED_TIPO_SOLIC,
               SEG_ACCES
         WHERE PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI
           AND PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
           AND PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI
           AND ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI
           AND PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS = PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS
           AND SEG_MARCA.OID_MARC = PED_TIPO_SOLIC.MARC_OID_MARC
           AND PED_TIPO_SOLIC.OID_TIPO_SOLI = PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI
           AND SEG_CANAL.OID_CANA = SEG_ACCES.CANA_OID_CANA
           AND SEG_ACCES.OID_ACCE = PED_TIPO_SOLIC.ACCE_OID_ACCE
           AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS IN
               (SELECT OID_TIPO_SOLI FROM INT_PRY_TIPO_SOLIC)
           AND CRA_PERIO.PERI_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(psCodigoPeriodo)
           AND PED_SOLIC_CABEC.FEC_FACT = TO_DATE(psFechaFacturacion,'dd/MM/yyyy')
           AND PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
           AND PED_SOLIC_CABEC.IND_OC = 1
           AND PED_ESTAD_SOLIC.COD_ESTA_SOLI = 'VA'
         GROUP BY oid_zona,COD_REGI,
                  COD_ZONA
          )     xx
  UNION
          select nvl((SELECT DISTINCT e1.val_i18n
                     FROM bel_almac             a1,
                          app_confi_centr_distr b1,
                          ape_confi_liafp_cabec c1,
                          ape_confi_liafp_detal d1,
                          gen_i18n_sicc_pais e1
                    WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
                      AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                      AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                      and a1.oid_alma=e1.val_oid and e1.attr_enti='BEL_ALMAC'
                      AND d1.zzon_oid_zona =xx.oid),
                  psCentroPais) AS CEN_PAIS,
                  xx.cod_peri,
                  xx.fec_fact,
                  xx.cod_regi_zona,
                  xx.ped_esti,
                  xx.ped_real,
                  xx.ind_cier
           from (SELECT
                      psCodigoPeriodo AS COD_PERI,
                      TO_CHAR(TO_DATE(psFechaFacturacion,'dd/MM/yyyy'),'yyyymmdd') AS FEC_FACT,
                       to_number(ZON_ZONA.COD_ZONA) AS COD_REGI_ZONA,
                       ZON_ZONA.OiD_ZONA AS oid,
                       0 AS PED_ESTI,
                       0 AS PED_REAL,
                       'X' AS IND_CIER
                  FROM FAC_CONTR_CIERR,
                       FAC_TIPOS_CIERR,
                       PED_SOLIC_CABEC,
                       PED_ESTAD_SOLIC,
                       ZON_ZONA,
                       ZON_REGIO,
                       CRA_PERIO,
                       SEG_MARCA,
                       SEG_CANAL,
                       PED_TIPO_SOLIC_PAIS,
                       PED_TIPO_SOLIC,
                       SEG_ACCES
                 WHERE FAC_CONTR_CIERR.TCIE_OID_TIPO_CIER = FAC_TIPOS_CIERR.OID_TIPO_CIER
                   AND ZON_ZONA.OID_ZONA = FAC_CONTR_CIERR.ZZON_OID_ZONA
                   AND ZON_ZONA.OID_ZONA = PED_SOLIC_CABEC.ZZON_OID_ZONA
                   AND ZON_REGIO.OID_REGI = FAC_CONTR_CIERR.ZORG_OID_REGI
                   AND ZON_REGIO.OID_REGI = PED_SOLIC_CABEC.ZZON_OID_ZONA
                   AND PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI
                   AND FAC_CONTR_CIERR.PERD_OID_PERI = CRA_PERIO.OID_PERI
                   AND PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS = PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS
                   AND SEG_MARCA.OID_MARC = PED_TIPO_SOLIC.MARC_OID_MARC
                   AND PED_TIPO_SOLIC.OID_TIPO_SOLI = PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI
                   AND SEG_CANAL.OID_CANA = SEG_ACCES.CANA_OID_CANA
                   AND SEG_ACCES.OID_ACCE = PED_TIPO_SOLIC.ACCE_OID_ACCE
                   AND FAC_CONTR_CIERR.PAIS_OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais)
                   AND CRA_PERIO.PERI_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(psCodigoPeriodo)
                   AND FAC_TIPOS_CIERR.COD_TIPO_CIER = 'R'
                   AND FAC_CONTR_CIERR.FEC_CIER = TO_DATE(psFechaFacturacion,'dd/MM/yyyy')
                   AND FAC_CONTR_CIERR.VAL_RESU_PROC = 'OK'
                   AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS IN
                       (SELECT OID_TIPO_SOLI FROM INT_PRY_TIPO_SOLIC)
                   AND PED_SOLIC_CABEC.IND_TS_NO_CONSO = 0
                   AND PED_SOLIC_CABEC.IND_OC = 0
                   AND PED_ESTAD_SOLIC.COD_ESTA_SOLI = 'VA'
                 GROUP BY oid_zona,COD_REGI,
                          COD_ZONA ) xx;


   TYPE interfazRec IS RECORD
       (
        centroPais               VARCHAR2(4),
        codigoPeriodo            VARCHAR2(6),
        fechaFacturacion         VARCHAR2(8),
        codigoRegionZona         NUMBER,
        pedidosEstimados         NUMBER,
        pedidosReales            NUMBER,
        indicadorCierre          VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := interfazRecord(x).centroPais                  ||';'||
                                           interfazRecord(x).codigoPeriodo               ||';'||
                                           interfazRecord(x).fechaFacturacion            ||';'||
                                           interfazRecord(x).codigoRegionZona            ||';'||
                                           interfazRecord(x).pedidosEstimados            ||';'||
                                           interfazRecord(x).pedidosReales               ||';'||
                                           interfazRecord(x).indicadorCierre;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);

         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PRY_ENVIO_PROYE_PARCI: '||ls_sqlerrm);

END INT_PR_PRY_ENVIO_PROYE_PARCI;

/************************************************************************************
Descripcion       : Genera Interfase de Envio de cronograma facturacion PRY-6
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_CRONO_FACTU(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2)
   IS
   CURSOR c_interfaz IS
              Select SUBSTR(nvl((SELECT DISTINCT e1.val_i18n
                     FROM bel_almac             a1,
                          app_confi_centr_distr b1,
                          ape_confi_liafp_cabec c1,
                          ape_confi_liafp_detal d1,
                          gen_i18n_sicc_pais e1
                    WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
                      AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                      AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                      and a1.oid_alma=e1.val_oid and e1.attr_enti='BEL_ALMAC'
                      AND d1.zzon_oid_zona =d.oid_zona),
                  psCentroPais),0,4) AS CEN_PAIS,
               lpad(d.cod_zona,6,'0') COD_ZONA,
               c.cod_peri COD_PERI,
               TO_CHAR(a.fec_inic, 'DDMMYYYY') as FEC_FACT,
               TO_CHAR(SYSDATE, 'DDMMYYYY') as FEC_PROC
        from cra_crono a,
             cra_perio b,
             seg_perio_corpo c,
             zon_zona d
        where
         exists (select null from cra_activ act where act.oid_acti = a.cact_oid_acti and act.cod_acti = 'FA'  )
        and a.perd_oid_peri = b.oid_peri
        and b.peri_oid_peri = c.oid_peri
        and a.zzon_oid_zona = d.oid_zona
        and c.cod_peri >= psCodigoPeriodo
        and d.ind_acti = 1
        and d.ind_borr = 0;


   TYPE interfazRec IS RECORD
       (
        centroPais               VARCHAR2(4),
        codigoZona               VARCHAR2(6),
        codigoPeriodo            VARCHAR2(6),
        fechaFacturacion         VARCHAR2(8),
        fechaProceso             VARCHAR2(8)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := interfazRecord(x).centroPais                  ||';'||
                                           interfazRecord(x).codigoZona                  ||';'||
                                           interfazRecord(x).codigoPeriodo               ||';'||
                                           interfazRecord(x).fechaFacturacion            ||';'||
                                           interfazRecord(x).fechaProceso;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);

         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PRY_ENVIO_CRONO_FACTU: '||ls_sqlerrm);

END INT_PR_PRY_ENVIO_CRONO_FACTU;

/************************************************************************************
Descripcion       : Genera Interfase de Envio de informaciond eventa proyeccion PRY-7
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_INFOR_VENT(psCodigoPais           VARCHAR2,
	                                   psCodigoPeriodo        VARCHAR2,
	                                   psFechaFacturacion     VARCHAR2,
	                                   psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais			VARCHAR2)
   IS
   CURSOR c_interfaz IS
        SELECT PAIS,
               SOCIEDAD,
               CENTRO,
               MATERIAL,
               PERIODO,
               TIPO_OFERTA,
               TO_CHAR(FECHA, 'YYYYMMDD') FECHA_FACTURACION,
               SUM(UNIDADES_VENDIDAS) UNIDADES_VENDIDAS,
               SUM(UNIDADES_FALTANTES) UNIDADES_FALTANTES,
               SUM(UNIDADES_PROYECTADAS) UNIDADES_PROYECTADAS,
               SUM(VENTA_NETA)*100 VENTA_NETA,
               SUM(VENTA_NETA_FALTANTE)*100 VENTA_NETA_FALTANTE
          FROM (
                SELECT psCodigoPais PAIS,
                       D.COD_SOCI SOCIEDAD,
                       nvl((SELECT DISTINCT e1.val_i18n
                     FROM bel_almac             a1,
                          app_confi_centr_distr b1,
                          ape_confi_liafp_cabec c1,
                          ape_confi_liafp_detal d1,
                          gen_i18n_sicc_pais e1
                    WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
                      AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                      AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                      and a1.oid_alma=e1.val_oid and e1.attr_enti='BEL_ALMAC'
                      AND d1.zzon_oid_zona =a.zzon_oid_zona),
                  psCentroPais) CENTRO,
                       E.COD_SAP MATERIAL,
                       I.COD_PERI PERIODO,
                       G.COD_TIPO_OFER TIPO_OFERTA,
                       A.FEC_FACT FECHA,
                       B.NUM_UNID_ATEN UNIDADES_VENDIDAS,
                       case when B.NUM_UNID_DEMA_REAL - B.NUM_UNID_ATEN >=0 then B.NUM_UNID_DEMA_REAL - B.NUM_UNID_ATEN else 0 end UNIDADES_FALTANTES,
                       0 UNIDADES_PROYECTADAS,
                       DECODE(B.VAL_PREC_CATA_UNIT_LOCA, 0, 0,B.VAL_PREC_NETO_UNIT_LOCA)*B.NUM_UNID_ATEN VENTA_NETA,
                       DECODE(B.VAL_PREC_CATA_UNIT_LOCA, 0, 0, B.VAL_PREC_NETO_UNIT_LOCA)*(B.NUM_UNID_DEMA_REAL - B.NUM_UNID_ATEN) VENTA_NETA_FALTANTE
                  FROM PED_SOLIC_CABEC A,
                       PED_SOLIC_POSIC B,
                       SEG_PAIS        C,
                       SEG_SOCIE       D,
                       MAE_PRODU       E,
                       PRE_OFERT_DETAL F,
                       PRE_TIPO_OFERT  G,
                       CRA_PERIO       H,
                       SEG_PERIO_CORPO I
                 WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
                   AND A.IND_OC = 1
                   AND A.FEC_FACT = TO_DATE(psFechaFacturacion,'dd/MM/yyyy')
                   AND B.ESPO_OID_ESTA_POSI != 2
                   AND A.SOCI_OID_SOCI = D.OID_SOCI
                   AND A.PAIS_OID_PAIS = C.OID_PAIS
                   AND B.PROD_OID_PROD = E.OID_PROD
                   AND B.OFDE_OID_DETA_OFER = F.OID_DETA_OFER
                   AND F.TOFE_OID_TIPO_OFER = G.OID_TIPO_OFER
                   AND B.NUM_UNID_ATEN >= 0
                   AND A.PERD_OID_PERI = H.OID_PERI
                   AND H.PERI_OID_PERI = I.OID_PERI
                   AND B.ESPO_OID_ESTA_POSI != 2
                   AND I.Cod_Peri = psCodigoPeriodo)
         GROUP BY PAIS,
                  SOCIEDAD,
                  CENTRO,
                  MATERIAL,
                  PERIODO,
                  TIPO_OFERTA,
                  TO_CHAR(FECHA, 'YYYYMMDD');


   TYPE interfazRec IS RECORD
       (
        codigoPais               VARCHAR2(3),
        codigoSociedad           VARCHAR2(4),
        codigoCentro             VARCHAR2(4),
        codigoMaterial           VARCHAR2(9),
        codigoPeriodo            VARCHAR2(6),
        tipoOferta               PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE, -- Nuevo
        fechaFacturacion         VARCHAR2(8),
        unidadesVendidas         NUMBER,
        unidadesFaltantes        NUMBER,
        unidadesProyectadas      NUMBER,
        ventaNeta                NUMBER,
        ventaNetaFaltante        NUMBER
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := interfazRecord(x).codigoPais                  ||';'||
                                           interfazRecord(x).codigoSociedad              ||';'||
                                           interfazRecord(x).codigoCentro                ||';'||
                                           interfazRecord(x).codigoMaterial              ||';'||
                                           interfazRecord(x).codigoPeriodo               ||';'||
                                           interfazRecord(x).tipoOferta                  ||';'||
                                           interfazRecord(x).fechaFacturacion            ||';'||
                                           interfazRecord(x).unidadesVendidas            ||';'||
                                           interfazRecord(x).unidadesFaltantes           ||';'||
                                           interfazRecord(x).unidadesProyectadas         ||';'||
                                           interfazRecord(x).ventaNeta                   ||';'||
                                           interfazRecord(x).ventaNetaFaltante;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);

         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PRY_ENVIO_INFOR_VENT: '||ls_sqlerrm);

END INT_PR_PRY_ENVIO_INFOR_VENT;

/************************************************************************************
Descripcion       : Genera Interfase de Envio de pedidos diarios y acumulados PRY-8
Fecha Creacion    : 30/11/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCentroPais:Centro pais
************************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_PEDID_DIACU(psCodigoPais           VARCHAR2,
                                       psCodigoPeriodo        VARCHAR2,
                                       psFechaFacturacion     VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais            VARCHAR2)
   IS
   CURSOR c_interfaz IS
      select codigopais,
             codigocentro,
             codigoperiodo,
             fechafacturacion,
             (SELECT COUNT(DISTINCT sol.oid_soli_cabe) AS numordenesdia
                  FROM ped_solic_cabec      sol,
                       ped_tipo_solic_pais  tsp,
                       ped_tipo_solic       ts,
                       ped_solic_posic      psp,
                       mae_produ            prd,
                       int_param_tipo_solic pt,
                       pre_ofert_detal      pod,
                       pre_tipo_ofert       ofe,
                       pre_catal            cat,
                       cra_perio per
                 WHERE sol.perd_oid_peri = per.oid_peri
                 and per.peri_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(psCodigoPeriodo)
                   AND sol.ind_oc = 1
                   AND sol.ind_pedi_prue = 0
                   AND sol.modu_oid_modu != 13
                   AND sol.fec_fact IS NOT NULL
                   AND sol.ind_ts_no_conso = 1
                   AND sol.fec_fact = to_date(psFechaFacturacion, 'dd/MM/yyyy')
                   AND psp.espo_oid_esta_posi <> 2
                   AND sol.oid_soli_cabe = psp.soca_oid_soli_cabe
                   AND psp.prod_oid_prod = prd.oid_prod
                   AND prd.mapr_oid_marc_prod IS NOT NULL
                   AND sol.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                   AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                   AND pt.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                   AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer
                   AND pod.tofe_oid_tipo_ofer = ofe.oid_tipo_ofer
                   AND pod.ocat_oid_catal = cat.oid_cata(+)
                   and sol.almc_oid_alma=xx.almc_oid_alma
                   AND (NOT (ofe.cod_tipo_ofer = '21' AND cat.cod_cata = 5) AND
                       NOT (ofe.cod_tipo_ofer = '23' AND cat.cod_cata = 5))
                   AND ts.ind_anul = 0
                   AND ts.ind_devo = 0
                 GROUP BY sol.pais_oid_pais,
                          sol.perd_oid_peri,
                          sol.sbac_oid_sbac,
                          --sol.almc_oid_alma,
                          sol.soci_oid_soci,
                          sol.tspa_oid_tipo_soli_pais,
                          ts.ind_anul,
                          ts.ind_devo,
                          sol.ticl_oid_tipo_clie
                           ) PEDIDOSDIA,
               (
                   SELECT COUNT(DISTINCT sol.oid_soli_cabe) AS numordenesdia
              FROM ped_solic_cabec      sol,
                   ped_tipo_solic_pais  tsp,
                   ped_tipo_solic       ts,
                   ped_solic_posic      psp,
                   mae_produ            prd,
                   int_param_tipo_solic pt,
                   pre_ofert_detal      pod,
                   pre_tipo_ofert       ofe,
                   pre_catal            cat,
                   cra_perio per
             WHERE sol.perd_oid_peri = per.oid_peri
             and per.peri_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(psCodigoPeriodo)
               AND sol.ind_oc = 1
               AND sol.ind_pedi_prue = 0
               AND sol.modu_oid_modu != 13
               AND sol.fec_fact IS NOT NULL
               AND sol.ind_ts_no_conso = 1
               AND sol.fec_fact <= to_date(psFechaFacturacion, 'dd/MM/yyyy')
               AND psp.espo_oid_esta_posi <> 2
               AND sol.oid_soli_cabe = psp.soca_oid_soli_cabe
               AND psp.prod_oid_prod = prd.oid_prod
               AND prd.mapr_oid_marc_prod IS NOT NULL
               AND sol.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
               AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND pt.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer
               AND pod.tofe_oid_tipo_ofer = ofe.oid_tipo_ofer
               AND pod.ocat_oid_catal = cat.oid_cata(+)
               and sol.almc_oid_alma=xx.almc_oid_alma
               AND (NOT (ofe.cod_tipo_ofer = '21' AND cat.cod_cata = 5) AND
                   NOT (ofe.cod_tipo_ofer = '23' AND cat.cod_cata = 5))
               AND ts.ind_anul = 0
               AND ts.ind_devo = 0
             GROUP BY sol.pais_oid_pais,
                      sol.perd_oid_peri,
                      sol.sbac_oid_sbac,
                      --sol.almc_oid_alma,
                      sol.soci_oid_soci,
                      sol.tspa_oid_tipo_soli_pais,
                      ts.ind_anul,
                      ts.ind_devo,
                      sol.ticl_oid_tipo_clie
                       ) PEDIDOSACUMULADOS
    from
         (SELECT distinct  psCodigoPais CODIGOPAIS,
               nvl((SELECT DISTINCT e1.val_i18n
                     FROM bel_almac             a1,
                          app_confi_centr_distr b1,
                          ape_confi_liafp_cabec c1,
                          ape_confi_liafp_detal d1,
                          gen_i18n_sicc_pais e1
                    WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
                      AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                      AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                      and a1.oid_alma=e1.val_oid and e1.attr_enti='BEL_ALMAC'
                      AND d1.zzon_oid_zona =a.zzon_oid_zona),
                  psCentroPais) CODIGOCENTRO,
               psCodigoPeriodo CODIGOPERIODO,
               TO_CHAR(TO_DATE(psFechaFacturacion,'dd/MM/yyyy'),'yyyymmdd') FECHAFACTURACION,
               a.almc_oid_alma
          FROM ped_solic_cabec a,
               cra_perio b,
               seg_perio_corpo c
          where a.perd_oid_peri=b.oid_peri and
                b.peri_oid_peri=c.oid_peri and
                c.cod_peri=psCodigoPeriodo and
                a.fec_fact=to_date(psFechaFacturacion, 'dd/MM/yyyy')
                and a.ind_oc=1
                and a.tspa_oid_tipo_soli_pais in
                   (
          select a.OID_TIPO_SOLI_PAIS--, c.VAL_I18N
          from ped_tipo_solic_pais a, ped_tipo_solic b, gen_i18n_sicc_comun c
          where a.TSOL_OID_TIPO_SOLI=b.OID_TIPO_SOLI and b.OID_TIPO_SOLI=c.VAL_OID
          and c.ATTR_ENTI='PED_TIPO_SOLIC'
          and b.IND_CONS=0 and b.IND_SOLI_NEGA=0
                   )
          ) xx;


   TYPE interfazRec IS RECORD
       (
        codigoPais                    VARCHAR2(3),
        codigoCentro                  VARCHAR2(4),
        codigoPeriodo                 VARCHAR2(6),
        fechaFacturacion              VARCHAR2(8),
        pedidosDia                    NUMBER,
        pedidosAcumulados             NUMBER
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := interfazRecord(x).codigoPais            ||';'||
                                           interfazRecord(x).codigoCentro          ||';'||
                                           interfazRecord(x).codigoPeriodo         ||';'||
                                           interfazRecord(x).fechaFacturacion      ||';'||
                                           interfazRecord(x).pedidosDia            ||';'||
                                           interfazRecord(x).pedidosAcumulados;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);

         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PRY_ENVIO_PEDID_DIACU: '||ls_sqlerrm);

END INT_PR_PRY_ENVIO_PEDID_DIACU;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio Información de Nuevos Faltantes

  Fecha Creacion    : 24/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_pry_envi_infor_nuevo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT DISTINCT SUBSTR (h.cod_pais, 1, 2) pais, g.cod_peri,
                TO_CHAR (g.fec_proc, 'YYYYMMDD') fecha, c.cod_sap
           FROM ped_solic_cabec a,
                ped_solic_posic b,
                mae_produ c,
                cra_perio d,
                seg_perio_corpo e,
                bel_stock f,
                bas_ctrl_fact g,
                seg_pais h
          WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
            AND b.prod_oid_prod = c.oid_prod
            AND a.pais_oid_pais = h.oid_pais
            AND b.num_unid_aten > 0
            AND a.perd_oid_peri = d.oid_peri
            AND d.peri_oid_peri = e.oid_peri
            AND e.cod_peri = g.cod_peri
            AND g.sta_camp = 0
            AND g.ind_camp_act = 1
            AND a.fec_fact = g.fec_proc
            AND c.oid_prod = f.prod_oid_prod
            AND f.val_sald = 0
            AND f.esme_oid_esta_merc = 2001;

    TYPE interfazrec IS RECORD(
      codigopais        VARCHAR2(2),
      campana           VARCHAR2(6),
      fecha             VARCHAR2(8),
      codigosap         VARCHAR2(9));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigopais || ';' || interfazrecord(x).campana || ';' || interfazrecord(x)
                    .fecha || ';' || interfazrecord(x).codigosap;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_pry_envi_infor_nuevo: ' || ls_sqlerrm);
END int_pr_pry_envi_infor_nuevo;

/***************************************************************************
  Descripcion       : Genera Interfase de Envio de Demanda Codigo cerrados PRY-10

  Fecha Creacion    : 17/08/2015
  Autor             : Karina Valencia
***************************************************************************/
PROCEDURE INT_PR_PRY_ENVIO_DEMA_CODIGO(psCodigoPais           VARCHAR2,
                                       psCodigoPeriodo        VARCHAR2,
                                       psFechaFacturacion     VARCHAR2,                                    
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCentroPais            VARCHAR2)
   IS
   CURSOR c_interfaz IS
   select cod_peri, 
          to_char(a.fec_fact,'yyyymmdd')FECHA,  
          j.cod_sap, 
          lpad(i.cod_tipo_ofer,3,'0') cod_tipo_ofer, 
lpad(sum(b.num_unid_dema-b.num_unid_compr),10,'0') UNIDADES, 
lpad(sum(DECODE(B.VAL_PREC_CATA_UNIT_LOCA, 0, 0, B.VAL_PREC_NETO_UNIT_LOCA)*(b.num_unid_dema-b.num_unid_compr))*100,17,'0') VENTA
from ped_solic_cabec a, 
      ped_solic_posic b, 
      cra_perio c, 
      seg_perio_corpo d, 
      ped_tipo_solic_pais e, 
      ped_tipo_solic f, 
      ped_gesti_stock g, 
      pre_ofert_Detal h, 
      pre_tipo_ofert i, 
      mae_produ j
where a.oid_soli_cabe=b.soca_oid_soli_cabe 
      and a.perd_oid_peri=c.oid_peri 
      and c.peri_oid_peri=d.oid_peri 
      and d.cod_peri=psCodigoPeriodo
      and a.fec_fact=to_date(psFechaFacturacion, 'dd/MM/yyyy')
      and a.tspa_oid_tipo_soli_pais=e.oid_tipo_soli_pais
      and e.tsol_oid_tipo_soli=f.oid_tipo_soli
      and f.cod_tipo_soli='SOC'
      and b.ofde_oid_deta_ofer=g.ofde_oid_deta_ofer
      and b.ofde_oid_deta_ofer=h.oid_deta_ofer
      and h.tofe_oid_tipo_ofer=i.oid_tipo_ofer
      and b.prod_oid_prod=j.oid_prod
      and g.val_limi_ctrl_vent is not null
      and b.ind_limi_vent=1
      and g.ind_cier_diar=1
      and b.num_unid_dema-b.num_unid_compr>0
      and b.tpos_oid_tipo_posi=1
      group by cod_peri, to_char(a.fec_fact,'yyyymmdd'),  j.cod_sap, i.cod_tipo_ofer;

   TYPE interfazRec IS RECORD
       (
        codigoPeriodo                 VARCHAR2(6),
        fechaFacturacion              VARCHAR2(8),
        codigoSap                     VARCHAR2(20),        
        codTipoOferta                 VARCHAR2(20),
        unidades                      NUMBER,
        venta                         NUMBER
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := psCentroPais         ||';'||
                                           interfazRecord(x).codigoPeriodo         ||';'||
                                           interfazRecord(x).fechaFacturacion      ||';'||
                                           interfazRecord(x).codigoSap             ||';'||
                                           interfazRecord(x).codTipoOferta         ||';'||
                                           interfazRecord(x).unidades             ||';'||
                                           interfazRecord(x).venta;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);

         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PRY_ENVIO_DEMA_CODIGO: '||ls_sqlerrm);

END INT_PR_PRY_ENVIO_DEMA_CODIGO;

END INT_PKG_PRY;
/
