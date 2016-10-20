CREATE OR REPLACE PACKAGE "INT_PKG_SAB" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Interfaz que envía maestros zonas
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_ZONAS
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);


/***************************************************************************
Descripcion       : Interfaz que envía maestros regiones
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_REGIO
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía fuente real de venta
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_FUENT_VENTA
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía fuente real de venta
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_TERRI
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/*********************************************************************************
Descripcion       : Genera interfaz de cabecera de pedidos para datamart (SAB-12)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-12)
            psNombreArchivo    = Archivo de salida ( SAB-12-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
            psCodigoAcceso     = Código de acceso
            psCodigoCanal      = Código de canal
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_PEDID_TOTAL
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Genera información de pedidos por marca (SAB-13)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-13)
            psNombreArchivo    = Archivo de salida ( SAB-13-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
            psCodigoAcceso     = Código de acceso
            psCodigoCanal      = Código de canal
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_PEDID_MARCA
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Genera información de venta diaria (SAB-14)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-14)
            psNombreArchivo    = Archivo de salida ( SAB-14-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_VENTA_DIARI
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Genera información de maestro de territorio (SAB-29)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-29)
            psNombreArchivo    = Archivo de salida ( SAB-29-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_MAEST_TERRI
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Genera información de pedidos por marca (SAB-16)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-16)
            psNombreArchivo    = Archivo de salida ( SAB-16-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_DEMAN_ANORM
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Genera información de totales al cierre de campaña (SAB-17)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-17)
            psNombreArchivo    = Archivo de salida ( SAB-17-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_TOTAL_CIERR
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  );

/*********************************************************************************
Descripcion       : Actualiza Fuente de Ventas con facturación del día
Fecha Creacion    : 29/11/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ACTUA_VENTA_DIARI
(
 psCodigoPais        VARCHAR2,
 psCodigoPeriodo     VARCHAR2,
 psFechaFacturacion  VARCHAR2
);

END INT_PKG_SAB;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_SAB" IS

/***************************************************************************
Descripcion       : Interfaz que envía maestros zonas
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_ZONAS
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
   CURSOR c_interfaz IS
    SELECT SP.COD_PAIS,
    ZON.COD_ZONA,
    ZR.COD_REGI,
    SUBSTR(ZON.DES_ZONA,1,20) DES_ZONA,
    CANALBPS.COD_SOCI,
    SMA.COD_MARC,
    CANALBPS.CANBPS,
    SUBG.COD_SUBG_VENT
    FROM ZON_REGIO ZR,
       ZON_ZONA ZON,
       ZON_SUB_GEREN_VENTA SUBG,
       SEG_PAIS SP,
     SEG_MARCA SMA,
    (SELECT TSP.PAIS_OID_PAIS, SOC.COD_SOCI, MAX(ICB.VAL_CANA_BPS) CANBPS
       FROM INT_CANAL_BPS ICB,
          PED_TIPO_SOLIC_PAIS TSP,
       SEG_SOCIE SOC
        WHERE ICB.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
           AND TSP.SOCI_OID_SOCI = SOC.OID_SOCI
     GROUP BY  TSP.PAIS_OID_PAIS,SOC.COD_SOCI) CANALBPS
   WHERE
        ZR.OID_REGI = ZON.ZORG_OID_REGI
        AND (SUBG.OID_SUBG_VENT = ZR.ZSGV_OID_SUBG_VENT)
    AND ZON.IND_BORR = 0
    AND ZON.IND_ACTI = 1
    AND ZR.IND_BORR = 0
  AND ZR.IND_ACTI = 1
  AND SUBG.IND_ACTI = 1
  AND SUBG.IND_BORR = 0
   AND SUBG.PAIS_OID_PAIS = SP.OID_PAIS
  AND SUBG.MARC_OID_MARC = SMA.OID_MARC
  AND SP.OID_PAIS = CANALBPS.PAIS_OID_PAIS
  AND SP.OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

   TYPE interfazTipo IS RECORD
   (  COD_PAIS        SEG_PAIS.COD_PAIS%TYPE,
      COD_ZONA        ZON_ZONA.COD_ZONA%TYPE,
      COD_REGI        ZON_REGIO.COD_REGI%TYPE,
      DES_ZONA        VARCHAR2(20),
      COD_SOCI        SEG_SOCIE.COD_SOCI%TYPE,
      COD_MARC        SEG_MARCA.COD_MARC%TYPE,
      CANBPS          INT_CANAL_BPS.VAL_CANA_BPS%TYPE,
      COD_SUBG_VENT   ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
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
                  lsLinea :=  psCodigoPais                  ||';'||
                              interfazRecord(x).COD_ZONA      ||';'||
                              interfazRecord(x).COD_REGI      ||';'||
                              interfazRecord(x).DES_ZONA    ||';'||
                              interfazRecord(x).COD_SOCI      ||';'||
                              interfazRecord(x).COD_MARC      ||';'||
                              interfazRecord(x).CANBPS       ||';'||
                              interfazRecord(x).COD_SUBG_VENT ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_VEN_ENVIO_MAEST_ZONAS: '||ls_sqlerrm);
END INT_PR_VEN_ENVIO_MAEST_ZONAS;

/***************************************************************************
Descripcion       : Interfaz que envía maestros Regiones
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_REGIO
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
   CURSOR c_interfaz IS
     SELECT SP.COD_PAIS,
    ZR.COD_REGI,
    SUBSTR(ZR.DES_REGI,1,20) DES_REGI,
    CANALBPS.COD_SOCI,
    SMA.COD_MARC,
    CANALBPS.CANBPS,
    SUBG.COD_SUBG_VENT
  FROM ZON_REGIO ZR,
       ZON_SUB_GEREN_VENTA SUBG,
    OWN_COMUN.SEG_PAIS SP,
    OWN_COMUN.SEG_MARCA SMA,
      ( SELECT TSP.PAIS_OID_PAIS, SOC.COD_SOCI, MAX(ICB.VAL_CANA_BPS) CANBPS
     FROM INT_CANAL_BPS ICB,
        PED_TIPO_SOLIC_PAIS TSP,
     SEG_SOCIE SOC
      WHERE ICB.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
         AND TSP.SOCI_OID_SOCI = SOC.OID_SOCI
   GROUP BY  TSP.PAIS_OID_PAIS,SOC.COD_SOCI  ) CANALBPS
   WHERE
    SUBG.OID_SUBG_VENT = ZR.ZSGV_OID_SUBG_VENT
 AND ZR.IND_BORR = 0
 AND ZR.IND_ACTI = 1
 AND SUBG.IND_ACTI = 1
 AND SUBG.IND_BORR = 0
 AND SUBG.PAIS_OID_PAIS = SP.OID_PAIS
 AND SUBG.MARC_OID_MARC = SMA.OID_MARC
 AND SP.OID_PAIS = CANALBPS.PAIS_OID_PAIS
 AND SP.OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS);

   TYPE interfazTipo IS RECORD
   (  COD_PAIS        SEG_PAIS.COD_PAIS%TYPE,
      COD_REGI        ZON_REGIO.COD_REGI%TYPE,
      DES_REGI        VARCHAR2(20),
      COD_SOCI        SEG_SOCIE.COD_SOCI%TYPE,
      COD_MARC        SEG_MARCA.COD_MARC%TYPE,
      CANBPS          INT_CANAL_BPS.VAL_CANA_BPS%TYPE,
      COD_SUBG_VENT   ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
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
                lsLinea :=  psCodigoPais                      ||';'||
                              interfazRecord(x).COD_REGI      ||';'||
                              interfazRecord(x).DES_REGI      ||';'||
                              interfazRecord(x).COD_SOCI      ||';'||
                              interfazRecord(x).COD_MARC      ||';'||
                              interfazRecord(x).CANBPS        ||';'||
                              interfazRecord(x).COD_SUBG_VENT ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_VEN_ENVIO_MAEST_REGIO: '||ls_sqlerrm);
END INT_PR_VEN_ENVIO_MAEST_REGIO;


/***************************************************************************
Descripcion       : Interfaz que envía fuente venta real
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_FUENT_VENTA
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
   CURSOR c_interfaz(vnIdPais NUMBER, vnIdperiodo NUMBER)  IS
SELECT cod_pais,
cod_soci,
cod_alma,
canbps,
anno,
cod_mone,
campa,
cod_marc,
'' Terri,
ActivasIniciales,
ActivasFinales,
Ingresos,
Reingreso,
Egresos,
CRecibidas,
CEntregadas,
NOrdenes,
NPedidos,
UniVend,
NClientes,
ImpNeto,
to_char(SYSDATE,'YYYYMMDD') fecproc

  FROM (SELECT DISTINCT spa.COD_PAIS,
                        CanalBps.COD_SOCI,
                        CanalBps.COD_ALMA,
                        CanalBps.Canbps,
                        TO_NUMBER(fvr.VAL_ANIO_COME) anno,
                        smo.COD_MONE,
                        TO_NUMBER(SUBSTR(spc.COD_PERI, 5, 2)) campa,
         sma.COD_MARC cod_marc,
         SUM(NVL (fvr.num_acti_inic, 0)) ActivasIniciales,
         SUM(NVL (fvr.num_acti_fina, 0)) ActivasFinales,
         SUM(NVL (fvr.num_ingr, 0)) Ingresos,
         SUM(NVL (fvr.num_rein, 0)) Reingreso,
         SUM(NVL (fvr.num_egre, 0)) Egresos,
         SUM(NVL (fvr.num_rezo_reci, 0)) CRecibidas,
         SUM(NVL (fvr.num_rezo_entr, 0)) CEntregadas,
         SUM(NVL (acum.NUM_ORDE ,0)) NOrdenes,
         SUM(NVL (acum.num_pedi  ,0)) NPedidos,
         SUM(NVL (acum.num_unid_vend ,0)) UniVend,
         SUM(NVL (acum.num_clie ,0)) NClientes,
                        LTRIM(TO_CHAR(SUM(NVL(acum.imp_vent_neta_esta, 0)),
                                      '999999999990999.99')) ImpNeto
          FROM cra_perio peri,
          int_fuent_ventas_real fvr,
          zon_terri ter,
               (
                select x.perd_oid_peri,
                       x.terr_oid_terr,
                       SUM(x.num_orde) num_orde,
                       SUM(x.num_pedi) num_pedi,
                       SUM(x.num_unid_vend) num_unid_vend,
                       SUM(x.num_clie) num_clie,
                       SUM(x.imp_vent_neta_esta) imp_vent_neta_esta
                  from int_fuent_venta_real_vacum x
                 where x.perd_oid_peri = vnidperiodo
                 group by x.perd_oid_peri, x.terr_oid_terr
               ) acum,
               --int_fuent_venta_real_vacum acum,
          seg_pais spa,
          seg_perio_corpo spc,
          seg_moned smo,
          seg_marca sma,
               (SELECT DISTINCT tsp.PAIS_OID_PAIS,
                                icb.VAL_CANA_BPS CanBps,
                                alm.COD_ALMA,
                                sso.COD_SOCI
                  FROM ped_tipo_solic_pais tsp,
              ped_tipo_solic ts,
              INT_CANAL_BPS icb,
              BEL_ALMAC alm,
              seg_socie sso
                 WHERE tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
              AND ts.COD_TIPO_SOLI ='SOC'
              AND icb.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
              AND tsp.ALMC_OID_ALMA = alm.OID_ALMA(+)
              AND tsp.SOCI_OID_SOCI = sso.OID_SOCI(+) ) CanalBps
         WHERE fvr.pais_oid_pais =  vnidpais
             AND fvr.PERD_OID_PERI=  vnidperiodo
             AND fvr.TERR_OID_TERR=ter.OID_TERR
             AND fvr.PAIS_OID_PAIS = spa.OID_PAIS
             AND spa.OID_PAIS = CanalBps.pais_oid_pais
             AND fvr.PERD_OID_PERI = peri.OID_PERI
             AND peri.PERI_OID_PERI = spc.OID_PERI
             AND peri.MARC_OID_MARC = sma.OID_MARC
             AND spa.MONE_OID_MONE = smo.OID_MONE
             AND fvr.TERR_OID_TERR= acum.TERR_OID_TERR(+)
           --AND acum.PERD_OID_PERI(+) = :vnidperiodo
         GROUP BY spa.COD_PAIS,
             CanalBps.COD_SOCI,
             CanalBps.COD_ALMA,
             CanalBps.Canbps,
             TO_NUMBER(fvr.VAL_ANIO_COME),
             smo.COD_MONE,
             TO_NUMBER(SUBSTR(spc.COD_PERI,5,2)),
                  sma.cod_marc);

TYPE interfazTipo IS RECORD
   (
 COD_PAIS    SEG_PAIS.COD_PAIS%TYPE,
 COD_SOCI    SEG_SOCIE.COD_SOCI%TYPE,
 COD_ALMA    BEL_ALMAC.COD_ALMA%TYPE,
 CANBPS      INT_CANAL_BPS.VAL_CANA_BPS%TYPE,
 VAL_ANIO_COME      INT_FUENT_VENTAS_REAL.VAL_ANIO_COME%TYPE,
 COD_MONE    SEG_MONED.COD_MONE%TYPE,
 COD_PERI    SEG_PERIO_CORPO.COD_PERI%TYPE,
 COD_MARC    SEG_MARCA.COD_MARC%TYPE,
 COD_TERR    ZON_TERRI.COD_TERR%TYPE,
 NUM_ACTI_INIC   INT_FUENT_VENTAS_REAL.NUM_ACTI_INIC%TYPE,
 NUM_ACTI_FINA   INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA%TYPE,
 NUM_INGR    INT_FUENT_VENTAS_REAL.NUM_INGR%TYPE,
 NUM_REIN    INT_FUENT_VENTAS_REAL.NUM_REIN%TYPE,
 NUM_EGRE    INT_FUENT_VENTAS_REAL.NUM_EGRE%TYPE,
 NUM_REZO_RECI   INT_FUENT_VENTAS_REAL.NUM_REZO_RECI%TYPE,
 NUM_REZO_ENTR   INT_FUENT_VENTAS_REAL.NUM_REZO_ENTR%TYPE,
 NUM_ORDE    INT_FUENT_VENTA_REAL_VACUM.NUM_ORDE%TYPE,
 NUM_PEDI    INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE,
 NUM_UNID_VEND   INT_FUENT_VENTA_REAL_VACUM.NUM_UNID_VEND%TYPE,
 NUM_CLIE    INT_FUENT_VENTA_REAL_VACUM.NUM_CLIE%TYPE,
 IMP_VENT_NETA_ESTA  VARCHAR2(18),
 FEC_CIER       VARCHAR2(8)  --SEGUN ESPECIFICACION
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal                   seg_canal.oid_cana%TYPE;
  lnIdMarca                   seg_marca.oid_marc%TYPE;
  lnIdPeriodo    cra_perio.oid_peri%TYPE;

BEGIN

   lnIdPais:= gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
   lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);
   lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);

   lnIdPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                  lnidmarca,
                                                  lnidcanal);

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(lnIdPais,lnIdPeriodo);
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
          lsLinea := psCodigoPais                  ||';'||
      interfazRecord(x).COD_SOCI   ||';'||
      interfazRecord(x).COD_ALMA   ||';'||
      interfazRecord(x).CANBPS   ||';'||
      interfazRecord(x).VAL_ANIO_COME ||';'||
      interfazRecord(x).COD_MONE   ||';'||
      interfazRecord(x).COD_PERI   ||';'||
      interfazRecord(x).COD_MARC   ||';'||
      interfazRecord(x).COD_TERR   ||';'||
      interfazRecord(x).NUM_ACTI_INIC ||';'||
      interfazRecord(x).NUM_ACTI_FINA ||';'||
      interfazRecord(x).NUM_INGR   ||';'||
      interfazRecord(x).NUM_REIN   ||';'||
      interfazRecord(x).NUM_EGRE   ||';'||
      interfazRecord(x).NUM_REZO_RECI ||';'||
      interfazRecord(x).NUM_REZO_ENTR ||';'||
      interfazRecord(x).NUM_ORDE   ||';'||
      interfazRecord(x).NUM_PEDI   ||';'||
      interfazRecord(x).NUM_UNID_VEND ||';'||
      interfazRecord(x).NUM_CLIE   ||';'||
      interfazRecord(x).IMP_VENT_NETA_ESTA  ||';'||
      interfazRecord(x).FEC_CIER;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_VEN_ENVIO_FUENT_VENTA: '||ls_sqlerrm);
END INT_PR_VEN_ENVIO_FUENT_VENTA;

/***************************************************************************
Descripcion       : Interfaz que envía fuente venta real
Fecha Creacion    : 01/06/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo de Pais
          psCodigoCanal   : Codigo Periodo
       psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_VEN_ENVIO_MAEST_TERRI
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
   CURSOR c_interfaz IS
    SELECT SP.COD_PAIS,
    CANALBPS.COD_SOCI,
    SMA.COD_MARC,
    TER.COD_TERR,
    ZON.COD_ZONA,
    ZR.COD_REGI,
    CANALBPS.CANBPS,
    SUBG.COD_SUBG_VENT,
       SECC.COD_SECC
    FROM ZON_REGIO ZR,
       ZON_SECCI SECC,
       ZON_TERRI TER,
       ZON_TERRI_ADMIN TERRI,
       ZON_ZONA ZON,
       ZON_SUB_GEREN_VENTA SUBG,
    SEG_PAIS SP,
    SEG_MARCA SMA,
      ( SELECT TSP.PAIS_OID_PAIS, SOC.COD_SOCI, MAX(ICB.VAL_CANA_BPS) CANBPS
     FROM INT_CANAL_BPS ICB,
        PED_TIPO_SOLIC_PAIS TSP,
     SEG_SOCIE SOC
      WHERE ICB.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
         AND TSP.SOCI_OID_SOCI = SOC.OID_SOCI
  GROUP BY  TSP.PAIS_OID_PAIS,SOC.COD_SOCI  ) CANALBPS
  WHERE     (SECC.OID_SECC = TERRI.ZSCC_OID_SECC)
        AND (TER.OID_TERR = TERRI.TERR_OID_TERR)
        AND (ZR.OID_REGI = ZON.ZORG_OID_REGI)
        AND (ZON.OID_ZONA = SECC.ZZON_OID_ZONA)
        AND (SUBG.OID_SUBG_VENT = ZR.ZSGV_OID_SUBG_VENT)
        AND SECC.IND_BORR = 0
  AND SECC.IND_ACTI = 1
  AND TERRI.IND_BORR = 0
  AND ZON.IND_BORR = 0
  AND ZON.IND_ACTI = 1
  AND ZR.IND_BORR = 0
  AND ZR.IND_ACTI = 1
  AND SUBG.IND_ACTI = 1
  AND SUBG.IND_BORR = 0
   AND SUBG.PAIS_OID_PAIS = SP.OID_PAIS
  AND SUBG.MARC_OID_MARC = SMA.OID_MARC
  AND SP.OID_PAIS = CANALBPS.PAIS_OID_PAIS
  AND SP.OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS);

   TYPE interfazTipo IS RECORD
   (  COD_PAIS        SEG_PAIS.COD_PAIS%TYPE,
      COD_SOCI        SEG_SOCIE.COD_SOCI%TYPE,
      COD_MARC       SEG_MARCA.COD_MARC%TYPE,
   COD_TERR    ZON_TERRI.COD_TERR%TYPE,
      COD_ZONA        ZON_ZONA.COD_ZONA%TYPE,
      COD_REGI        ZON_REGIO.COD_REGI%TYPE,
      CANBPS        INT_CANAL_BPS.VAL_CANA_BPS%TYPE,
      COD_SUBG_VENT   ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
   COD_SECC     ZON_SECCI.COD_SECC%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
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
          lsLinea :=  psCodigoPais                      ||';'||
                              interfazRecord(x).COD_SOCI      ||';'||
                              interfazRecord(x).COD_MARC      ||';'||
         interfazRecord(x).COD_TERR   ||';'||
                              interfazRecord(x).COD_ZONA      ||';'||
                              interfazRecord(x).COD_REGI      ||';'||
                              interfazRecord(x).CANBPS        ||';'||
                              interfazRecord(x).COD_SUBG_VENT ||';'||
         interfazRecord(x).COD_SECC ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_VEN_ENVIO_MAEST_TERRI: '||ls_sqlerrm);
END INT_PR_VEN_ENVIO_MAEST_TERRI;

/*********************************************************************************
Descripcion       : Genera información de pedidos totales (SAB-12)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-12)
            psNombreArchivo    = Archivo de salida ( SAB-12-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
            psCodigoAcceso     = Código de acceso
            psCodigoCanal      = Código de canal
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_PEDID_TOTAL
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lnIdCampa NUMBER ) IS
    SELECT pais.cod_pais pais,
           soci.cod_soci sociedad,
           'A01' centro,
           ivdt.val_cana_bps canal,
           ivdt.val_medi medio,
           ivdt.val_anio anioComercial,
           SUBSTR( FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( ivdt.perd_oid_peri ), 5, 2 ) campania,
           TO_DATE( psFechaFacturacion, 'DD/MM/YYYY') fechaFacturacion,
           SUM( CASE WHEN ivdt.fec_fact = TO_DATE( psFechaFacturacion, 'DD/MM/YYYY') THEN ivdt.val_nume_orde ELSE 0 END ) numeroOrdenesTotalesDia,
           SUM( ivdt.val_nume_pedi ) numeroPedidosAcumulados,
           0 numeroPedidosProyectados,
           marc.cod_marc marca
      FROM int_venta_diari_total ivdt,
           seg_pais              pais,
           seg_socie             soci,
           zon_sub_geren_venta   zsgv,
           seg_marca             marc
     WHERE ivdt.pais_oid_pais = pais.oid_pais
       AND ivdt.soci_oid_soci = soci.oid_soci
       AND ivdt.pais_oid_pais = zsgv.pais_oid_pais
       AND zsgv.marc_oid_marc = marc.oid_marc
       --
       AND ivdt.perd_oid_peri = lnIdCampa
       AND ivdt.fec_fact     <= TO_DATE( psFechaFacturacion, 'DD/MM/YYYY')
       AND zsgv.ind_acti      = 1
       AND zsgv.ind_borr      = 0
     GROUP BY pais.cod_pais,
              soci.cod_soci,
              ivdt.val_cana_bps,
              ivdt.val_medi,
              ivdt.val_anio,
              ivdt.perd_oid_peri,
              marc.cod_marc;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
--
lnIdCampa       CRA_PERIO.Oid_Peri%TYPE;

BEGIN
  lbAbrirUtlFile := TRUE;
  lnIdCampa      := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lnIdCampa );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
          /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                         psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais                     ||';'||
                                interfazrecord(x).sociedad                 ||';'||
                                interfazrecord(x).centro                   ||';'||
                                interfazrecord(x).canal                    ||';'||
                                interfazrecord(x).medio                    ||';'||
                                interfazrecord(x).anioComercial            ||';'||
                                interfazrecord(x).campania                 ||';'||
                                TO_CHAR(interfazrecord(x).fechaFacturacion,'YYYYMMDD') ||';'||
                                interfazrecord(x).numeroOrdenesTotalesdia  ||';'||
                                interfazrecord(x).numeroPedidosAcumulados  ||';'||
                                interfazrecord(x).numeroPedidosProyectados ||';'||
                                interfazrecord(x).marca;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_PEDID_TOTAL: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_PEDID_TOTAL;

/*********************************************************************************
Descripcion       : Genera información de pedidos por marca (SAB-13)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-13)
            psNombreArchivo    = Archivo de salida ( SAB-13-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_PEDID_MARCA
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lsFechaFacturacion VARCHAR2, vnNumeroRegistros NUMBER ) IS
    SELECT pais.cod_pais pais,
           soci.cod_soci sociedad,
           almc.cod_alma centro,
           cbps.val_cana_bps canal,
           0 medio,
           SUBSTR( geco.cod_peri, 1, 4 ) anioComercial,
           SUBSTR( geco.cod_peri, 5, 2 ) campania,
           geco.cod_marc_prod marca,
           MAX( geco.fec_fact ) fechaFacturacion,
           MAX( CASE WHEN geco.fec_fact = lsFechaFacturacion THEN geco.num_pedi_marc ELSE 0 END ) numeroordenestotalesdia,
           SUM( DISTINCT geco.num_pedi_marc ) numeropedidosacumulados
      FROM int_gener_diari_conso geco,
           seg_perio_corpo       peri,
           cra_perio             perd,
           int_param_pais        ptpa,
           seg_pais              pais,
           int_canal_bps         cbps,
           seg_socie             soci,
           bel_almac almc
     WHERE geco.cod_peri          = peri.cod_peri
       AND peri.oid_peri          = perd.peri_oid_peri
       AND perd.pais_oid_pais     = pais.oid_pais
       AND pais.oid_pais          = ptpa.pais_oid_pais
       AND ptpa.cbps_oid_cana_bps = cbps.oid_cana_bps
       AND ptpa.soci_oid_soci     = soci.oid_soci
       AND ptpa.almc_oid_alma     = almc.oid_alma
       AND geco.cod_peri          = psCodigoPeriodo
       AND geco.fec_fact         <= lsFechaFacturacion
       AND vnNumeroRegistros > 0
     GROUP BY pais.cod_pais,
              soci.cod_soci,
              almc.cod_alma,
              cbps.val_cana_bps,
              geco.cod_peri,
              geco.cod_marc_prod
     UNION
    SELECT pais.cod_pais pais,
           soci.cod_soci sociedad,
           almc.cod_alma centro,
           cbps.val_cana_bps canal,
           0 medio,
           SUBSTR( psCodigoPeriodo, 1, 4 ) anioComercial,
           SUBSTR( psCodigoPeriodo, 5, 2 ) campania,
           null marca,
           lsFechaFacturacion fechaFacturacion,
           0 numeroordenestotalesdia,
           0 numeropedidosacumulados
      FROM seg_perio_corpo peri,
           cra_perio perd,
           int_param_pais ptpa,
           seg_pais pais,
           int_canal_bps cbps,
           seg_socie soci,
           bel_almac almc
     WHERE peri.oid_peri = perd.peri_oid_peri
       AND perd.pais_oid_pais = pais.oid_pais
       AND pais.oid_pais = ptpa.pais_oid_pais
       AND ptpa.cbps_oid_cana_bps = cbps.oid_cana_bps
       AND ptpa.soci_oid_soci = soci.oid_soci
       AND ptpa.almc_oid_alma = almc.oid_alma
       AND vnNumeroRegistros = 0
     GROUP BY pais.cod_pais,
              soci.cod_soci,
              almc.cod_alma,
              cbps.val_cana_bps
         ;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
--
lnIdCampa          CRA_PERIO.Oid_Peri%TYPE;
lsFechaFacturacion INT_GENER_DIARI_CONSO.FEC_FACT%TYPE;
lnNumeroRegistros NUMBER(12):=0;

BEGIN
  lbAbrirUtlFile     := TRUE;
  lnIdCampa          := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
  lsFechaFacturacion := TO_CHAR( TO_DATE( psFechaFacturacion, 'DD/MM/YYYY' ), 'YYYYMMDD' );

  -- Verifica el número de registros existentes de pedidos facturados para una campaña y fecha
  BEGIN
      SELECT COUNT(*)
        INTO lnNumeroRegistros
        FROM int_gener_diari_conso geco
       WHERE geco.cod_peri = psCodigoPeriodo
         AND geco.fec_fact = lsFechaFacturacion
           ;
  EXCEPTION WHEN NO_DATA_FOUND THEN
       lnNumeroRegistros := 0;
  END;

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lsFechaFacturacion, lnNumeroRegistros );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
            /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais                    ||';'||
                                interfazrecord(x).sociedad                ||';'||
                                interfazrecord(x).centro                  ||';'||
                                interfazrecord(x).canal                   ||';'||
                                interfazrecord(x).medio                   ||';'||
                                interfazrecord(x).aniocomercial           ||';'||
                                interfazrecord(x).campania                ||';'||
                                interfazrecord(x).marca                   ||';'||
                                interfazrecord(x).fechafacturacion        ||';'||
                                interfazrecord(x).numeroordenestotalesdia ||';'||
                                interfazrecord(x).numeropedidosacumulados;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_PEDID_MARCA: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_PEDID_MARCA;

/*********************************************************************************
Descripcion       : Genera información de venta diaria (SAB-14)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-14)
            psNombreArchivo    = Archivo de salida ( SAB-14-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_VENTA_DIARI
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lnIndCruce NUMBEr, lsProxiCampa VARCHAR2, lsFec_Proc VARCHAR2 ) IS
    SELECT pais.cod_pais pais,
           geca.cod_soci sociedad,
           almc.cod_alma centro,
           cbps.val_cana_bps canal,
           0 medio,
           SUBSTR( geca.cod_peri, 1, 4 ) anioComercial,
           SUBSTR( geca.cod_peri, 5, 2 ) campania,
           DECODE( gede.oid_deta_ofer_equi, NULL, gede.cod_prod, gede.cod_prod_equi ) codigoproducto,
           gede.cod_cicl_vida ciclovida,
           DECODE( gede.oid_deta_ofer_equi, NULL, gede.cod_tipo_ofert, gede.cod_tipo_ofert_equi ) tipooferta,
           '00' condicionpromocion, -- Modificado
           geca.fec_fact  fecha,
           1 flagventa,
           geca.cod_mone monedatransaccion,
           0 unidadesproyectadas,
           SUM( gede.num_unid_vent ) unidadesvendidas,
           SUM( gede.num_unid_falt ) unidadesfaltantes,
           SUM( gede.num_unid_devo ) unidadesdevueltas,
           SUM( gede.num_unid_anul ) unidadesanuladas,
           SUM( gede.num_unid_reto ) unidadescanje,
           SUM( gede.num_unid_aten ) unidadestrueque,
           0 ventanetaproyectada,
           SUM( gede.imp_neto_vent ) ventanetaatendida,
           SUM( gede.imp_neto_falt ) ventanetafaltante,
           SUM( gede.imp_neto_devo ) ventanetadevoluciones,
           SUM( gede.imp_neto_anul ) ventanetaanulaciones,
           SUM( gede.imp_neto_reto ) ventanetacanjes,
           SUM( gede.imp_neto_aten ) ventanetatrueques,
           SUM( gede.imp_vent_cata ) ventabrutareal,
           SUM( gede.imp_vent_cata_falt ) ventabrutafaltante,
           geca.cod_marc marcaproducto,
           geca.cod_terr territorio
      FROM int_tmp_gener_solic_detal gede,
           int_tmp_gener_solic_cabec geca,
           seg_perio_corpo       peri,
           cra_perio             perd,
           int_param_pais        ptpa,
           seg_pais              pais,
           int_canal_bps         cbps,
           bel_almac almc
     WHERE gede.oid_soli_cabe     = geca.oid_soli_cabe
       AND gede.soca_oid_soli_cabe= geca.soca_oid_soli_cabe
       AND gede.cod_peri_refe     = geca.cod_peri_refe
       AND geca.cod_peri          = peri.cod_peri
       AND peri.oid_peri          = perd.peri_oid_peri
       AND perd.pais_oid_pais     = pais.oid_pais
       AND pais.oid_pais          = ptpa.pais_oid_pais
       AND ptpa.cbps_oid_cana_bps = cbps.oid_cana_bps
       AND ptpa.almc_oid_alma     = almc.oid_alma
       AND geca.fec_fact <= TO_DATE( psfechafacturacion, 'DD/MM/YYYY' )
       AND psfechafacturacion = lsFec_Proc
       AND ( geca.cod_peri = pscodigoperiodo OR lnIndCruce = 0 AND geca.cod_peri = lsProxiCampa )
     GROUP BY pais.cod_pais,
              geca.cod_peri,
              geca.cod_soci,
              almc.cod_alma,
              cbps.val_cana_bps,
              geca.cod_peri,
              DECODE( gede.oid_deta_ofer_equi, NULL, gede.cod_prod, gede.cod_prod_equi ),
              gede.cod_cicl_vida,
              DECODE( gede.oid_deta_ofer_equi, NULL, gede.cod_tipo_ofert, gede.cod_tipo_ofert_equi ),
              geca.cod_mone,
              geca.cod_marc,
              geca.cod_terr,
              geca.fec_fact
    ORDER BY geca.fec_fact DESC;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
--
lnIndCruce      CRA_PERIO.Ind_Peri_Cruc%TYPE;
lsProxiCampa    SEG_PERIO_CORPO.Cod_Peri%TYPE;
lnIdCampa       CRA_PERIO.Oid_Peri%TYPE;
lsFec_proc      INT_TMP_DATAM_CTROL.FEC_PROC%TYPE;

BEGIN
  lbAbrirUtlFile := TRUE;
  lnIdCampa      := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
  lsProxiCampa   := GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 );

  -- Verificar cruce de campañas
  SELECT perd.ind_peri_cruc
    INTO lnIndCruce
    FROM cra_perio perd
   WHERE perd.oid_peri = lnIdCampa;

  SELECT max(FEC_PROC) INTO lsfec_proc FROM INT_TMP_DATAM_CTROL   ;

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lnIndCruce, lsProxiCampa, lsfec_proc );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
            /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais
                                ||';'|| interfazrecord(x).sociedad
                                ||';'|| interfazrecord(x).centro
                                ||';'|| interfazrecord(x).canal
                                ||';'|| interfazrecord(x).medio
                                ||';'|| interfazrecord(x).aniocomercial
                                ||';'|| interfazrecord(x).campania
                                ||';'|| interfazrecord(x).codigoproducto
                                ||';'|| interfazrecord(x).ciclovida
                                ||';'|| interfazrecord(x).tipooferta
                                ||';'|| interfazrecord(x).condicionpromocion
                                ||';'|| TO_CHAR(interfazrecord(x).fecha,'YYYYMMDD')
                                ||';'|| interfazrecord(x).flagventa
                                ||';'|| interfazrecord(x).monedatransaccion
                                ||';'|| interfazrecord(x).unidadesproyectadas
                                ||';'|| interfazrecord(x).unidadesvendidas
                                ||';'|| interfazrecord(x).unidadesfaltantes
                                ||';'|| interfazrecord(x).unidadesdevueltas
                                ||';'|| interfazrecord(x).unidadesanuladas
                                ||';'|| interfazrecord(x).unidadescanje
                                ||';'|| interfazrecord(x).unidadestrueque
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetaproyectada,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetaatendida,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetafaltante,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetadevoluciones,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetaanulaciones,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetacanjes,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventanetatrueques,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventabrutareal,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventabrutafaltante,'999999999990.00')
                                ||';'|| interfazrecord(x).marcaproducto
                                ||';'|| interfazrecord(x).territorio;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  -- =================================
  -- Actualizar Fuente de Venta Diaria
  -- =================================

  INT_PKG_SAB.INT_PR_SAB_ACTUA_VENTA_DIARI(
                                            pscodigopais,
                                            psCodigoPeriodo,
                                            psFechaFacturacion
                                          );
  --
  -- Fin: Actualizar Fuente de Venta Diaria
  --
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_VENTA_DIARI: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_VENTA_DIARI;

/*********************************************************************************
Descripcion       : Genera información de maestro de territorio (SAB-29)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-29)
            psNombreArchivo    = Archivo de salida ( SAB-29-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_MAEST_TERRI
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lnIndCruce NUMBEr, lsProxiCampa VARCHAR2, lsFec_Proc VARCHAR2 ) IS
    SELECT pais.cod_pais pais,
           geca.cod_soci sociedad,
           geca.cod_marc marca,
           terr.cod_terr territorio,
           zzon.cod_zona codigoZona,
           zorg.cod_regi codigoRegion,
           cbps.val_cana_bps canalBPS,
           zsgv.cod_subg_vent subgerenciaVentas,
           zscc.cod_secc seccion
      FROM int_tmp_gener_solic_cabec geca,
           zon_terri_admin      ztad,
           zon_secci            zscc,
           zon_zona             zzon,
           zon_terri            terr,
           zon_regio            zorg,
           seg_perio_corpo      peri,
           cra_perio            perd,
           int_param_pais       ptpa,
           seg_pais             pais,
           int_canal_bps        cbps,
           zon_sub_geren_venta  zsgv
     WHERE geca.cod_terr          = terr.cod_terr
       AND geca.cod_peri          = peri.cod_peri
       AND peri.oid_peri          = perd.peri_oid_peri
       AND perd.pais_oid_pais     = ptpa.pais_oid_pais
       AND perd.pais_oid_pais     = pais.oid_pais
       AND ptpa.cbps_oid_cana_bps = cbps.oid_cana_bps
       AND terr.oid_terr          = ztad.terr_oid_terr
       AND ztad.zscc_oid_secc     = zscc.oid_secc
       AND zscc.zzon_oid_zona     = zzon.oid_zona
       AND zzon.zorg_oid_regi     = zorg.oid_regi
       AND zsgv.oid_subg_vent     = zorg.zsgv_oid_subg_vent
       --
--       AND geca.cod_pedi IN ('PN', 'PR')
       AND ztad.ind_borr = 0
       AND geca.fec_fact <= TO_DATE(psfechafacturacion,'DD/MM/YYYY')
       AND psfechafacturacion = lsFec_Proc
       AND ( geca.cod_peri = pscodigoperiodo OR lnIndCruce = 0 AND geca.cod_peri = lsProxiCampa )
     GROUP BY pais.cod_pais,
              geca.cod_soci,
              geca.cod_marc,
              terr.cod_terr,
              zzon.cod_zona,
              zorg.cod_regi,
              cbps.val_cana_bps,
              zsgv.cod_subg_vent,
              zscc.cod_secc;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
--
lnIdCampa          CRA_PERIO.Oid_Peri%TYPE;
lnIndCruce         CRA_PERIO.Ind_Peri_Cruc%TYPE;
lsProxiCampa       SEG_PERIO_CORPO.Cod_Peri%TYPE;
lsFec_proc         INT_TMP_DATAM_CTROL.FEC_PROC%TYPE;

BEGIN
  lbAbrirUtlFile     := TRUE;
  lnIdCampa          := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
  lsProxiCampa   := GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 );

  SELECT max(FEC_PROC) INTO lsfec_proc FROM INT_TMP_DATAM_CTROL   ;

  SELECT perd.ind_peri_cruc
    INTO lnIndCruce
    FROM cra_perio perd
   WHERE perd.oid_peri = lnIdCampa;

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lnIndCruce,lsProxiCampa, lsFec_Proc );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
            /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais              ||';'||
                                interfazrecord(x).sociedad          ||';'||
                                interfazrecord(x).marca             ||';'||
                                interfazrecord(x).territorio        ||';'||
                                interfazrecord(x).codigoZona        ||';'||
                                interfazrecord(x).codigoRegion      ||';'||
                                interfazrecord(x).canalBPS          ||';'||
                                interfazrecord(x).subgerenciaVentas ||';'||
                                interfazrecord(x).seccion;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_MAEST_TERRI: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_MAEST_TERRI;

/*********************************************************************************
Descripcion       : Genera información de pedidos por marca (SAB-16)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-16)
            psNombreArchivo    = Archivo de salida ( SAB-16-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_DEMAN_ANORM
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lsFechaFacturacion VARCHAR2 ) IS
    SELECT pais.cod_pais pais,
           soci.cod_soci sociedad,
           almc.cod_alma centro,
           cbps.val_cana_bps canal
      FROM int_gener_diari_conso geco,
           seg_perio_corpo       peri,
           cra_perio             perd,
           int_param_pais        ptpa,
           seg_pais              pais,
           int_canal_bps         cbps,
           seg_socie             soci,
           bel_almac almc
     WHERE geco.cod_peri          = peri.cod_peri
       AND peri.oid_peri          = perd.peri_oid_peri
       AND perd.pais_oid_pais     = pais.oid_pais
       AND pais.oid_pais          = ptpa.pais_oid_pais
       AND ptpa.cbps_oid_cana_bps = cbps.oid_cana_bps
       AND ptpa.soci_oid_soci     = soci.oid_soci
       AND ptpa.almc_oid_alma     = almc.oid_alma
       AND geco.cod_peri          = psCodigoPeriodo
       AND geco.fec_fact          = 'X' -- Se puso este valor para que el cursor no genere registro alguno
     GROUP BY pais.cod_pais,
              soci.cod_soci,
              almc.cod_alma,
              cbps.val_cana_bps,
              geco.cod_peri,
              geco.cod_marc_prod;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
--
lnIdCampa          CRA_PERIO.Oid_Peri%TYPE;
lsFechaFacturacion INT_GENER_DIARI_CONSO.FEC_FACT%TYPE;

BEGIN
  lbAbrirUtlFile     := TRUE;
  lnIdCampa          := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
  lsFechaFacturacion := TO_CHAR( TO_DATE( psFechaFacturacion, 'DD/MM/YYYY' ), 'YYYYMMDD' );

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lsFechaFacturacion );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
            /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais                    ||';'||
                                interfazrecord(x).sociedad                ||';'||
                                interfazrecord(x).centro                  ||';'||
                                interfazrecord(x).canal;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_DEMAN_ANORM: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_DEMAN_ANORM;

/*********************************************************************************
Descripcion       : Genera información de totales al cierre de campaña (SAB-17)
Fecha Creacion    : 25/06/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psCodigoSistema    = Sistema de interfaz ( SAB )
            psCodigoInterfaz   = Código de interfaz ( SAB-17)
            psNombreArchivo    = Archivo de salida ( SAB-17-AAAAMMDDssss )
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ENVIO_TOTAL_CIERR
  (
   psCodigoPais        VARCHAR2,
   psCodigoPeriodo     VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psFechaFacturacion  VARCHAR2
  )
IS
CURSOR c_Interfaz( lnIdCampa NUMBER ) IS
    SELECT pais.cod_pais pais,
           soc.cod_soci sociedad,
           MIN( alm.cod_alma ) centro,
           ivdt.val_cana_bps canal,
           ivdt.val_medi medio,
           SUBSTR( peri.cod_peri, 1, 4 ) aniocomercial,
           SUBSTR( peri.cod_peri, 5, 2 ) campania,
           mone.cod_mone monedatransaccion,
           SUM( NVL( ivdt.val_nume_unid_vend, 0 ) ) unidadesvendidas,
           SUM( NVL( ivdt.val_nume_unid_falt, 0 ) ) unidadesfaltantes,
           SUM( NVL( ivdt.val_nume_unid_devu, 0 ) ) unidadesdevueltas,
           SUM( NVL( ivdt.val_nume_unid_anul, 0 ) ) unidadesanuladas,
           SUM( NVL( ivdt.val_nume_unid_canj, 0 ) ) unidadescanje,
           SUM( NVL( ivdt.val_nume_unid_true, 0 ) ) unidadestrueque,
           SUM( NVL( ivdt.val_impo_vent_neta_aten, 0 ) ) ventanetaatendida,
           SUM( NVL( ivdt.val_impo_vent_neta_falt, 0 ) ) ventanetafaltante,
           SUM( NVL( ivdt.val_impo_vent_neta_devo, 0 ) ) ventanetadevoluciones,
           SUM( NVL( ivdt.val_impo_vent_neta_anul, 0 ) ) ventanetaanulaciones,
           SUM( NVL( ivdt.val_impo_vent_neta_canj, 0 ) ) ventanetacanjes,
           SUM( NVL( ivdt.val_impo_vent_neta_true, 0 ) ) ventanetatrueques,
           SUM( NVL( ivdt.val_nume_zona, 0 ) ) numerozonas,
           SUM( NVL( ivdt.val_nume_orde, 0 ) ) numeroordenes,
           MAX(cier.fec_cier) fechacierre
      FROM int_venta_diari_total ivdt,
           cra_perio perd,
           seg_perio_corpo peri,
           seg_pais pais,
           seg_socie soc,
           bel_almac alm,
           seg_moned mone,
           (
            SELECT coci.pais_oid_pais,
                   coci.perd_oid_peri,
                   MAX(coci.fec_cier) fec_cier -- Modificado
              FROM fac_contr_cierr coci
             WHERE coci.perd_oid_peri      = lnIdCampa
               AND coci.tcie_oid_tipo_cier = 3
             GROUP BY coci.pais_oid_pais,
                      coci.perd_oid_peri
           ) cier
     WHERE ivdt.pais_oid_pais = pais.oid_pais
       AND ivdt.soci_oid_soci = soc.oid_soci
       AND ivdt.almc_oid_alma = alm.oid_alma
       AND ivdt.mone_oid_mone = mone.oid_mone
       AND ivdt.perd_oid_peri = perd.oid_peri
       AND ivdt.pais_oid_pais = cier.pais_oid_pais
       AND ivdt.perd_oid_peri = cier.perd_oid_peri
       AND perd.peri_oid_peri = peri.oid_peri
       --
       AND ivdt.perd_oid_peri = lnIdCampa
     GROUP BY pais.cod_pais,
              soc.cod_soci,
              ivdt.val_cana_bps,
              val_medi,
              peri.cod_peri,
              mone.cod_mone;

TYPE c_Interfaz_t IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazRecord c_Interfaz_t;

-- Variables usadas para la generacion del archivo de texto
lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
v_hANDle        UTL_FILE.FILE_TYPE;
lbAbrirUtlFile  BOOLEAN;
lsLinea         VARCHAR2(1000);
lsNombreArchivo VARCHAR2(50);
lnNumeroPedidos NUMBER(12,0);
--
lnIdCampa          CRA_PERIO.Oid_Peri%TYPE;

BEGIN
  lbAbrirUtlFile     := TRUE;
  lnIdCampa          := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );

  -- Obtener el número de pedidos en el campaña

  SELECT SUM( num_pedi )
    INTO lnNumeroPedidos
    FROM int_fuent_venta_real_vacum
   WHERE perd_oid_peri = lnIdCampa;

  -- Generando Archivo de Texto (Detalle)
  OPEN c_interfaz( lnIdCampa );
       LOOP
          FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
            /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lslinea := interfazrecord(x).pais
                                ||';'|| interfazrecord(x).sociedad
                                ||';'|| interfazrecord(x).centro
                                ||';'|| interfazrecord(x).canal
                                ||';'|| interfazrecord(x).medio
                                ||';'|| interfazrecord(x).anioComercial
                                ||';'|| interfazrecord(x).campania
                                ||';'|| interfazrecord(x).monedaTransaccion
                                ||';'|| interfazrecord(x).unidadesVendidas
                                ||';'|| interfazrecord(x).unidadesFaltantes
                                ||';'|| interfazrecord(x).unidadesDevueltas
                                ||';'|| interfazrecord(x).unidadesAnuladas
                                ||';'|| interfazrecord(x).unidadesCanje
                                ||';'|| interfazrecord(x).unidadesTrueque
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaAtendida,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaFaltante,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaDevoluciones,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaAnulaciones,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaCanjes,'999999999990.00')
                                ||';'|| TO_CHAR(interfazrecord(x).ventaNetaTrueques,'999999999990.00')
                                ||';'|| interfazrecord(x).numeroZonas
                                ||';'|| interfazrecord(x).numeroOrdenes
                                ||';'|| NVL( lnNumeroPedidos,0 )
                                ||';'|| TO_CHAR(interfazrecord(x).fechaCierre,'YYYYMMDD');
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
          EXIT WHEN c_interfaz%NOTFOUND;
       END LOOP;
  CLOSE c_interfaz;

  /* Procedimiento final para generar interfaz */
  IF NOT lbAbrirUtlFile THEN
     utl_file.fclose(V_HANDLE);
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ENVIO_TOTAL_CIERR: '||ls_sqlerrm);
END INT_PR_SAB_ENVIO_TOTAL_CIERR;

/*********************************************************************************
Descripcion       : Actualiza Fuente de Ventas con facturación del día
Fecha Creacion    : 29/11/2012
Parametros: psCodigoPais       = Código de pais
            psCodigoPeriodo    = Campaña de facturación
            psFechaFacturacion = Fecha de facturación
Autor: CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SAB_ACTUA_VENTA_DIARI
(
 psCodigoPais        VARCHAR2,
 psCodigoPeriodo     VARCHAR2,
 psFechaFacturacion  VARCHAR2
) IS

lnindcruce NUMBER;
lnidcampa CRA_PERIO.Oid_Peri%TYPE;
lnidcampasig CRA_PERIO.Oid_Peri%TYPE;
lnidpais SEG_PAIS.Oid_Pais%TYPE;
lsFec_proc   INT_TMP_DATAM_CTROL.FEC_PROC%TYPE;

BEGIN
  lnidpais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS( pscodigopais );
  lnIdCampa := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
  lnidcampasig := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 ) );

  -- Verificar cruce de campañas
  SELECT perd.ind_peri_cruc
    INTO lnIndCruce
    FROM cra_perio perd
   WHERE perd.oid_peri = lnIdCampa;

  SELECT max(FEC_PROC) INTO lsfec_proc FROM INT_TMP_DATAM_CTROL   ;

  -- Borra los registros antes de cargar o volver a cargar :
  
 -- DELETE int_venta_diari_total
 --    WHERE TRUNC( fec_fact ) = TO_DATE( psFechaFacturacion, 'DD/MM/YYYY' )
 --      AND ( perd_oid_peri =  lnidcampa OR ( lnindcruce = 0 AND perd_oid_peri = lnidcampasig )
 --          );

  DELETE int_venta_diari_total
     WHERE fec_fact in (select distinct fec_fact from int_tmp_gener_solic_cabec
                           where fec_fact <=TO_DATE( psfechafacturacion, 'DD/MM/YYYY' ))
       AND psfechafacturacion = lsFec_Proc                     
       AND ( perd_oid_peri =  lnidcampa OR ( lnindcruce = 0 AND perd_oid_peri = lnidcampasig )
           );


  -- Inserta los registros de la fecha (Se considera periodos actual y siguiente)

  INSERT INTO INT_VENTA_DIARI_TOTAL
  SELECT INT_IVDT_SEQ.NEXTVAL,
         0,
         unidadesatendidas,
         unidadesnoatendidas,
         unidadesdevueltas,
         unidadesanuladas,
         unidadesretorno,
         unidadesatencion,
         0,
         montonetoventa,
         monto_faltante,
         montonetodevolucion,
         montonetoanulacion,
         montonetoretorno,
         montonetoatencion,
         ventabruta,
         ventabrutafalt,
         numzonas,
         numords,
         numpeds,
         fec_fact,
         oid_peri,
         almacen,
         oid_soci,
         oid_mone,
         oid_pais,
         canal,
         medio,
         val_anio
    FROM ( SELECT OID_SOCI,
                  FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( TOTALES.cod_peri ) OID_PERI ,
                  OID_MONE,
                  VAL_ANIO,
                  ( SELECT COUNT( DISTINCT tcabec.oid_soli_cabe )
                      FROM int_tmp_gener_solic_cabec tcabec
                      WHERE tcabec.cod_peri = totales.cod_peri
                        AND tcabec.fec_fact = totales.fec_fact
                        AND tcabec.cod_pedi ='PN' ) numords,
                  ( SELECT COUNT( DISTINCT tcabec.cod_clie )
                      FROM int_tmp_gener_solic_cabec tcabec
                     WHERE tcabec.cod_peri = totales.cod_peri
                       AND tcabec.fec_fact = totales.fec_fact
                       AND tcabec.cod_pedi ='PN' ) numpeds,
                  ( SELECT COUNT( DISTINCT cod_zona )
                      FROM int_tmp_gener_solic_cabec tcabec,
                           zon_terri_admin ta,
                           zon_secci sec,
                           zon_zona zon,
                           zon_terri ter
                     WHERE ta.ind_borr = 0
                       AND ta.zscc_oid_secc = sec.oid_secc
                       AND sec.zzon_oid_zona = zon.oid_zona
                       AND ta.terr_oid_terr = ter.oid_terr
                       AND tcabec.cod_terr = ter.cod_terr
                       AND tcabec.cod_peri = totales.cod_peri
                       AND tcabec.fec_fact = totales.fec_fact
                       AND cod_pedi = 'PN' ) numzonas,
                  TOTALES.*
             FROM ( SELECT fec_fact,
                           cabec.cod_peri,
                           2001 almacen,
                           cod_soci,
                           cod_mone,
                           lnidpais oid_pais,
                           1 canal,
                           0 medio,
                           SUM( num_unid_vent ) unidadesatendidas,
                           SUM( num_unid_falt ) unidadesnoatendidas,
                           SUM( num_unid_devo ) unidadesdevueltas,
                           SUM( num_unid_anul ) unidadesanuladas,
                           SUM( imp_neto_vent ) montonetoventa,
                           SUM( pre_fact_tota_loca ) preciofacturatotallocal,
                           SUM( imp_neto_falt ) monto_faltante,
                           SUM( imp_neto_devo ) montonetodevolucion,
                           SUM( imp_neto_anul ) montonetoanulacion,
                           SUM( num_unid_reto ) unidadesretorno,
                           SUM( num_unid_aten ) unidadesatencion,
                           SUM( imp_neto_reto ) montonetoretorno,
                           SUM( imp_neto_aten ) montonetoatencion,
                           SUM( imp_vent_cata ) ventabruta,
                           SUM( imp_vent_cata_falt ) ventabrutafalt
                      FROM int_tmp_gener_solic_cabec cabec,
                           int_tmp_gener_solic_detal detal
                     WHERE cabec.oid_soli_cabe     = detal.oid_soli_cabe 
                       AND cabec.soca_oid_soli_cabe= detal.soca_oid_soli_cabe
                       AND cabec.cod_peri_refe     = detal.cod_peri_refe                     
--                       AND cod_pedi IN ( 'PN', 'PR' )
                       AND cabec.fec_fact <= TO_DATE( psFechaFacturacion, 'DD/MM/YYYY' )
                       AND psfechafacturacion = lsFec_Proc
                       AND ( cabec.cod_peri = pscodigoperiodo OR
                             ( lnindcruce = 0 AND cabec.cod_peri = gen_fn_calcu_perio( pscodigoperiodo, 1 ) )
                           )
                     GROUP BY fec_fact,
                              cabec.cod_peri,
                              --2001,
                              cod_soci,
                              cod_mone,
                              lnidpais/*,
                              1,
                              0*/
                   ) totales,
                   seg_socie,
                   seg_moned,
                   seg_perio_corpo
             WHERE totales.cod_soci = seg_socie.cod_soci
               AND totales.cod_mone = seg_moned.cod_mone
               AND totales.cod_peri = seg_perio_corpo.cod_peri
          );
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_SAB_ACTUA_VENTA_DIARI: '||ls_sqlerrm);
END INT_PR_SAB_ACTUA_VENTA_DIARI;

END INT_PKG_SAB;
/
