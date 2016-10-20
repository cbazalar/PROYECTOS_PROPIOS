CREATE OR REPLACE PACKAGE "INT_PKG_BELCE" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;


/* Declaracion de procedures */
/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Cabecera
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_FACTU_CABEC
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
    psCodigoMarca    VARCHAR2 ,
    psCodigoCanal         VARCHAR2 ,
    psCodigoAcceso         VARCHAR2 ,
    psCodigoPeriodo         VARCHAR2 ,
    psFechaFacturacion VARCHAR2
   ) ;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Detalle
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_FACTU_DETAL
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
    psCodigoMarca    VARCHAR2 ,
    psCodigoCanal         VARCHAR2 ,
    psCodigoAcceso         VARCHAR2 ,
    psCodigoPeriodo         VARCHAR2 ,
    psFechaFacturacion VARCHAR2
   ) ;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Direcciones
                       del Cliente
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_DIREC_CLIEN
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoTipoCliente   VARCHAR2,
   psfechaInicioProceso   DATE
   ) ;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Porcentaje
                       de Referencia
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_PORCE_REFER
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
    psCodigoMarca    VARCHAR2 ,
    psCodigoCanal         VARCHAR2 ,
    psCodigoAcceso         VARCHAR2 ,
    psCodigoPeriodo         VARCHAR2 ,
    psFechaFacturacion VARCHAR2

   ) ;

/**************************************************************************
 Descripcion        : Genera el archivo para la Interfaz Enviar Unidades
                      Atendidas
 Fecha Creacion     : 27/02/2007
 Autor              : Marco Silva
***************************************************************************/
PROCEDURE INT_PR_BEL_UNIDA_ATEND
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
    psCodigoMarca    VARCHAR2 ,
    psCodigoCanal         VARCHAR2 ,
    psCodigoAcceso         VARCHAR2 ,
    psCodigoPeriodo         VARCHAR2 ,
    psFechaFacturacion VARCHAR2

   ) ;

/**************************************************************************
 Descripcion        : Genera el archivo para la Interfaz Enviar Ubicaciones
                      Geograficas
 Fecha Creacion     : 27/02/2007
 Autor              : Marco Silva
***************************************************************************/
PROCEDURE INT_PR_BEL_UBICA_GEOGR
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psfechaInicioUltimoProceso DATE
   ) ;


END INT_PKG_BELCE;
/

CREATE OR REPLACE PACKAGE BODY "INT_PKG_BELCE" IS

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Cabecera
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_FACTU_CABEC
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
    psCodigoMarca    VARCHAR2 ,
    psCodigoCanal         VARCHAR2 ,
    psCodigoAcceso         VARCHAR2 ,
    psCodigoPeriodo         VARCHAR2 ,
    psFechaFacturacion VARCHAR2
   )

IS
   CURSOR c_interfaz IS
         SELECT DISTINCT
		        CABECERA.ANYCAM AS ANYCAM,
                CABECERA.CODCLI AS CODCLI,
                PED_SOLIC_CABEC.VAL_NUME_SOLI AS NUMFAC,
                CABECERA.TIPPED AS TIPPED,
                TO_CHAR(CABECERA.FECEMI, 'YYYYMMDD') AS FECFAC,
                CABECERA.TIPDOC AS TIPDOC
          FROM
          (SELECT NVL(SEG_PERIO_CORPO.COD_PERI, ' ') AS ANYCAM,
                NVL(MAE_CLIEN.COD_CLIE, ' ') AS CODCLI,
               NVL(PED_SOLIC_CABEC.VAL_NUME_SOLI, 0) AS NUMFAC,
               'N' AS TIPPED,
               PED_SOLIC_CABEC.FEC_FACT AS FECEMI,
               (CASE FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU
                       WHEN 'B' THEN '01'
                      WHEN 'F' THEN '02'
                   END) AS TIPDOC,
                PED_SOLIC_CABEC.OID_SOLI_CABE,
                PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
             FROM PED_SOLIC_CABEC,
                  SEG_PAIS,
                  SEG_MARCA,
                  SEG_CANAL,
                  CRA_PERIO,
                  SEG_PERIO_CORPO,
                  SEG_TIPO_PERIO,
                  SEG_SUBAC,
                  SEG_ACCES,
                  PED_ESTAD_SOLIC,
                  FAC_TIPO_DOCUM,
                  FAC_TIPO_DOCUM_LEGAL,
                  PED_TIPO_SOLIC_PAIS,
                  PED_TIPO_SOLIC,
                  MAE_CLIEN
           WHERE ((SEG_PAIS.COD_PAIS = psCodigoPais)
            AND   (SEG_MARCA.COD_MARC = psCodigoMarca)
            AND   (SEG_CANAL.COD_CANA = psCodigoCanal)
            AND   (SEG_ACCES.COD_ACCE = psCodigoAcceso)
             AND  (SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo)
             AND   (PED_SOLIC_CABEC.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY'))
            AND   (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
		  AND ((COD_TIPO_SOLI LIKE 'SE%') OR (COD_TIPO_SOLI = 'SOC'))
            AND   (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
           AND   (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
          AND   (PED_TIPO_SOLIC.IND_DEVO = 0)
            AND   (PED_TIPO_SOLIC.IND_ANUL = 0)
          AND   (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
             AND   (PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
          AND   (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
            AND   (CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
          AND   (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
          AND   (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
          AND   (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
          AND   (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
          AND   (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
          AND   (PED_SOLIC_CABEC.SBAC_OID_SBAC = SEG_SUBAC.OID_SBAC)
          AND   (SEG_SUBAC.ACCE_OID_ACCE = SEG_ACCES.OID_ACCE)
          AND   (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
            AND   (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
            AND   (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOCU)
          AND   (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
          AND   (PED_TIPO_SOLIC_PAIS.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
          AND   (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
          AND   (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE))
    ) CABECERA, PED_SOLIC_CABEC
     WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
     ORDER BY NUMFAC ;

   TYPE interfazCab IS RECORD
   (
   anyoCampanya          VARCHAR2(6)  ,
   codigoCliente          VARCHAR2(15)    ,
   numeroFactura          VARCHAR2(10)    ,
   tipoPedido             VARCHAR2(1)     ,
   fechaFactura          VARCHAR2(8)     ,
   tipoDocumento         VARCHAR2(2)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;


  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
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
              lsLinea :=
                          interfazRecord(x).anyoCampanya                        ||';'||
                          interfazRecord(x).codigoCliente                         ||';'||
                          interfazRecord(x).numeroFactura                         ||';'||
                          interfazRecord(x).tipoPedido                  ||';'||
                          interfazRecord(x).fechaFactura                         ||';'||
                          interfazRecord(x).tipoDocumento        ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_FACTU_CABEC: '||ls_sqlerrm);

END INT_PR_BEL_FACTU_CABEC;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Detalle
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_FACTU_DETAL
  (psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2,
   psCodigoMarca   VARCHAR2 ,
   psCodigoCanal   VARCHAR2 ,
   psCodigoAcceso   VARCHAR2 ,
   psCodigoPeriodo VARCHAR2 ,
   psFechaFacturacion VARCHAR2
   )

IS
   CURSOR c_interfaz IS
      SELECT PED_SOLIC_CABEC.VAL_NUME_SOLI AS NUMFAC,
               CABECERA.CODVEN AS CODVEN,
               CABECERA.CODPRO AS CODPRO,
               CABECERA.UNIATE AS UNIATE,
               CABECERA.UNIFAL AS UNIFAL,
               CABECERA.PRECAT AS PRECAT,
               CABECERA.PREFAC AS PREFAC,
               CABECERA.PRECON AS PRECON,
               CABECERA.PERIOD AS PERIOD
           FROM
           (SELECT NVL(PED_SOLIC_CABEC.VAL_NUME_SOLI, 0) AS NUMFAC,
                 NVL(PED_SOLIC_POSIC.VAL_CODI_VENT, ' ') AS CODVEN,
                 NVL(MAE_PRODU.COD_SAP, ' ') AS CODPRO,
                 NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) AS UNIATE,
                 (NVL(PED_SOLIC_POSIC.NUM_UNID_POR_ATEN, 0) - NVL(PED_SOLIC_POSIC.NUM_UNID_COMPR, 0)) AS UNIFAL,
                 NVL(PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0) AS PRECAT,
                 decode (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0, 0, NVL(PED_SOLIC_POSIC.VAL_PREC_FACT_UNIT_LOCA, 0)) AS PREFAC,
                 NVL(PED_SOLIC_POSIC.VAL_PREC_CONT_UNIT_LOCA, 0) AS PRECON,
                 PED_SOLIC_CABEC.OID_SOLI_CABE,
                 PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE,
                 SEG_PERIO_CORPO_REF.COD_PERI PERIOD
              FROM PED_SOLIC_CABEC,
                  SEG_PAIS,
                  SEG_MARCA,
                  SEG_CANAL,
                  CRA_PERIO,
                  SEG_PERIO_CORPO,
                  SEG_TIPO_PERIO,
                  SEG_SUBAC,
                  SEG_ACCES,
                  PED_ESTAD_SOLIC,
                  FAC_TIPO_DOCUM,
                  FAC_TIPO_DOCUM_LEGAL,
                  PED_TIPO_SOLIC_PAIS,
                  PED_TIPO_SOLIC,
                  PED_SOLIC_POSIC,
                  PED_ESTAD_POSIC,
                  MAE_PRODU,
                  SEG_MARCA_PRODU,
                  PED_SOLIC_CABEC SOLREF,
                  CRA_PERIO CRA_PERIO_REF,
                  SEG_PERIO_CORPO SEG_PERIO_CORPO_REF
              WHERE ((SEG_PAIS.COD_PAIS = psCodigoPais)
                 AND   (SEG_MARCA.COD_MARC = psCodigoMarca)
                 AND   (SEG_CANAL.COD_CANA = psCodigoCanal)
                 AND   (SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo)
                 AND   (SEG_ACCES.COD_ACCE = psCodigoAcceso)
                 AND   (PED_SOLIC_CABEC.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY'))
                 AND   (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
			     AND ((COD_TIPO_SOLI LIKE 'SE%') OR (COD_TIPO_SOLI = 'SOC'))
                 AND   (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
                 AND   (PED_TIPO_SOLIC.IND_DEVO = 0)
                 AND   (PED_TIPO_SOLIC.IND_ANUL = 0)
                 AND   (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
                 AND   (PED_ESTAD_POSIC.COD_ESTA_POSI <> 'AN')
                 AND   (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
                 AND   (PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                 AND   (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
                 AND   (CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                 AND   (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
                 AND   (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
                 AND   (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
                 AND   (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                 AND   (PED_SOLIC_CABEC.SBAC_OID_SBAC = SEG_SUBAC.OID_SBAC)
                 AND   (SEG_SUBAC.ACCE_OID_ACCE = SEG_ACCES.OID_ACCE)
                 AND   (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                 AND   (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
                 AND   (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
                 AND   (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOCU)
                 AND   (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
                 AND   (PED_TIPO_SOLIC_PAIS.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                 AND   (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
                 AND   (PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE)
                 AND   (PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = PED_ESTAD_POSIC.OID_ESTA_POSI)
                 AND   (PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD)
                 AND   (MAE_PRODU.MAPR_OID_MARC_PROD = SEG_MARCA_PRODU.OID_MARC_PROD)
                 AND   (NVL(PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE,PED_SOLIC_CABEC.OID_SOLI_CABE) = SOLREF.OID_SOLI_CABE)
                 AND   (CRA_PERIO_REF.OID_PERI = SOLREF.PERD_OID_PERI)
                 AND   (SEG_PERIO_CORPO_REF.OID_PERI = CRA_PERIO_REF.PERI_OID_PERI)
                 )
             ) CABECERA, PED_SOLIC_CABEC
           WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
           ORDER BY NUMFAC ;


   TYPE interfazCab IS RECORD
   (
   numeroFactura      VARCHAR2(10) ,
   codigoVenta        VARCHAR2(15) ,
   codigoProducto     VARCHAR2(10) ,
   unidadesAtendidas  VARCHAR2(5) ,
   unidadesFaltantes  VARCHAR2(5) ,
   precioCatalogo     VARCHAR2(11) ,
   precioFactura      VARCHAR2(11) ,
   precioContable     VARCHAR2(11),
   periodoReferencia  VARCHAR2(6)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
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
              lsLinea :=
                          interfazRecord(x).numeroFactura                    ||';'||
                          interfazRecord(x).codigoVenta                      ||';'||
                          interfazRecord(x).codigoProducto                   ||';'||
                          interfazRecord(x).unidadesAtendidas                ||';'||
                          interfazRecord(x).unidadesFaltantes                ||';'||
                          interfazRecord(x).precioCatalogo                   ||';'||
                          interfazRecord(x).precioFactura                    ||';'||
                          interfazRecord(x).precioContable                   ||';'||
                          interfazRecord(x).periodoReferencia;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_FACTU_DETAL: '||ls_sqlerrm);

END INT_PR_BEL_FACTU_DETAL;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Direcciones
                       del Cliente
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_DIREC_CLIEN
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoTipoCliente  VARCHAR2,
   psfechaInicioProceso   DATE
   )

IS
   -- Definimos los caracteres a filtrar y reemplazar por blancos
   searchStr  VARCHAR2(100) := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
   replaceStr VARCHAR2(100) := 'a        ';

   CURSOR c_interfaz IS
   SELECT
      MAE_CLIEN.COD_CLIE AS CODCLIENTE,
      SEG_TIPO_VIA.COD_TIPO_VIA AS TIPOVIA,
      TRIM(TRANSLATE(SUBSTR(TRIM(MAE_CLIEN_DIREC.VAL_NOMB_VIA),1,40), searchStr, replaceStr)) AS NOMBREVIA,
      (CASE ZON_VIA.COD_VIA  WHEN NULL THEN NVL(ZON_VIA.COD_VIA, ' ') ELSE 1 END) AS INDDESADC ,
      TRIM(TRANSLATE(SUBSTR(TRIM(MAE_CLIEN_DIREC.NUM_PPAL),1,3), searchStr, replaceStr))  AS NUMEROVIA,
      TO_CHAR(' ') AS NUMEROBLOCK,
      MAE_CLIEN_DIREC.VAL_MANZ AS NUMEROMANZANA,
      MAE_CLIEN_DIREC.VAL_LOTE AS NUMEROLOTE,
      TO_CHAR(' ') AS NUMEROKILOMETRO,
      TO_CHAR(' ') AS NUMINTDOMICILIO,
      TO_CHAR(' ') AS CODPOSTAL,
      (ZON_VALOR_ESTRU_GEOPO.ORDE_1 || ZON_VALOR_ESTRU_GEOPO.ORDE_2 || ZON_VALOR_ESTRU_GEOPO.ORDE_3 ||
       ZON_VALOR_ESTRU_GEOPO.ORDE_4 || ZON_VALOR_ESTRU_GEOPO.ORDE_5 || ZON_VALOR_ESTRU_GEOPO.ORDE_6 ||
       ZON_VALOR_ESTRU_GEOPO.ORDE_7 || ZON_VALOR_ESTRU_GEOPO.ORDE_8 || ZON_VALOR_ESTRU_GEOPO.ORDE_9)  AS CODUBIINEI,
      TO_CHAR(' ') AS CODTIPOCENTROPOBLADO,
      TO_CHAR(' ') AS NUMEROETAPA,
      TO_CHAR(' ') AS NUMEROSECTOR,
      TO_CHAR(' ') AS INDDESREF,
      TRIM(TRANSLATE(SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TF'), 1, 15), searchStr, replaceStr)) NUMTEL1,
      TRIM(TRANSLATE(SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TM'), 1, 15), searchStr, replaceStr)) NUMTEL2,
      TO_CHAR(' ') AS INDDIRTRABAJO,
      TRIM(TRANSLATE(SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TT'), 1, 15), searchStr, replaceStr)) NUMTEL3,
      TO_CHAR(' ') AS NUMTEL4,
      TO_CHAR(' ') AS INDADICIONAL
      FROM MAE_CLIEN,
           MAE_CLIEN_TIPO_SUBTI,
           MAE_TIPO_CLIEN,
           MAE_CLIEN_DIREC,
           SEG_TIPO_VIA,
           ZON_VIA,
           ZON_TERRI,
           ZON_VALOR_ESTRU_GEOPO
  WHERE  (MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE)
  AND  (MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE = MAE_TIPO_CLIEN.OID_TIPO_CLIE)
  AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DIREC.CLIE_OID_CLIE)
     AND (MAE_CLIEN_DIREC.TIVI_OID_TIPO_VIA = SEG_TIPO_VIA.OID_TIPO_VIA)
     AND (MAE_CLIEN_DIREC.ZVIA_OID_VIA = ZON_VIA.OID_VIA (+))
     AND (MAE_CLIEN_DIREC.TERR_OID_TERR = ZON_TERRI.OID_TERR)
     AND (ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP = ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP)
     AND (MAE_CLIEN_DIREC.IND_DIRE_PPAL = 1 )
     AND  ( MAE_CLIEN_DIREC.IND_ELIM = 0 )
     AND (MAE_TIPO_CLIEN.COD_TIPO_CLIE = psCodigoTipoCliente)
    AND  ((psfechaInicioProceso IS NULL) OR (psfechaInicioProceso IS NOT NULL  AND MAE_CLIEN_DIREC.FEC_ULTI_ACTU > psfechaInicioProceso))
     ORDER BY COD_CLIE   ;


   TYPE interfazCab IS RECORD
   (
        codigoCliente                 VARCHAR2(15) ,
        tipoVia                     VARCHAR2(2 ) ,
        nombreVia                    VARCHAR2(40) ,
        indDescAdicional              VARCHAR2(1 ) ,
        numeroVia                     VARCHAR2(3 ) ,
        numeroBlock                 VARCHAR2(3 ) ,
        numeroManzana                VARCHAR2(3 ) ,
        numeroLote                    VARCHAR2(3 ) ,
        numeroKilometro               VARCHAR2(3 ) ,
        numeroInteriorDomicilio       VARCHAR2(3 ) ,
        codigoPostal                 VARCHAR2(5 ) ,
        codigoUbigeoINEI             VARCHAR2(54) ,
        codigoTipoCentroPoblado      VARCHAR2(2 ) ,
        numeroEtapa                   VARCHAR2(3 ) ,
        numeroSector                 VARCHAR2(4 ) ,
        indDescReferencia            VARCHAR2(1 ) ,
        numeroTelefono1              VARCHAR2(15) ,
        numeroTelefono2               VARCHAR2(15) ,
        indDireccionTrabajo           VARCHAR2(1 ) ,
        numeroTelefono3               VARCHAR2(15) ,
        numeroTelefono4               VARCHAR2(15) ,
        indAdicional                  VARCHAR2(1 )
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lnBusca             NUMBER;
  lsNombreVia         VARCHAR2(100);
  lbAbrirUtlFile      BOOLEAN;
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
              lsNombreVia := interfazRecord(x).nombreVia ;
              lsNombreVia := Replace(lsNombreVia , ';' , ' ');

              lsLinea :=
                  interfazRecord(x).codigoCliente                     ||';'||
                  interfazRecord(x).tipoVia                         ||';'||
                  lsNombreVia                                         ||';'||
                  interfazRecord(x).indDescAdicional                  ||';'||
                  interfazRecord(x).numeroVia                         ||';'||
                  interfazRecord(x).numeroBlock                     ||';'||
                  interfazRecord(x).numeroManzana                    ||';'||
                  interfazRecord(x).numeroLote                        ||';'||
                  interfazRecord(x).numeroKilometro                   ||';'||
                  interfazRecord(x).numeroInteriorDomicilio           ||';'||
                  interfazRecord(x).codigoPostal                     ||';'||
                  interfazRecord(x).codigoUbigeoINEI                 ||';'||
                  interfazRecord(x).codigoTipoCentroPoblado          ||';'||
                  interfazRecord(x).numeroEtapa                       ||';'||
                  interfazRecord(x).numeroSector                     ||';'||
                  interfazRecord(x).indDescReferencia                 ||';'||
                  interfazRecord(x).numeroTelefono1                  ||';'||
                  interfazRecord(x).numeroTelefono2                   ||';'||
                  interfazRecord(x).indDireccionTrabajo               ||';'||
                  interfazRecord(x).numeroTelefono3                   ||';'||
                  interfazRecord(x).numeroTelefono4                   ||';'||
                  interfazRecord(x).indAdicional  ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_DIREC_CLIEN: '||ls_sqlerrm);

END INT_PR_BEL_DIREC_CLIEN;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Porcentaje
                       de Referencia
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_BEL_PORCE_REFER
  (psCodigoPais        VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psCodigoMarca     VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoAcceso      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion  VARCHAR2
   )

IS
   CURSOR c_interfaz IS
   SELECT
         PERIODO1,
         CODCLIENTE1,
         ESTFACTURACION1,
         COD_GRUP1,
         DECODE (CODCLIENTE, NULL, PORCOMISION1,GREATEST(PORCOMISION,PORCOMISION1))
    FROM (SELECT X.PERIODO PERIODO1,
                 X.CODCLIENTE CODCLIENTE1,
                 X.ESTFACTURACION ESTFACTURACION1,
                 A.VAL_PORC_DEFE PORCOMISION1,
                 A.COD_GRUP_DESC COD_GRUP1
            FROM MAE_GRUPO_DESCU_DEFEC A,
                 (SELECT   CABECERA.PERIODO,
                           CABECERA.CODCLIENTE,
                           CABECERA.ESTFACTURACION,
                           MAX (CABECERA.PORCOMISION) PORCOMISION
                      FROM (SELECT NVL (SEG_PERIO_CORPO.COD_PERI, ' ') AS PERIODO,
                                   NVL (MAE_CLIEN.COD_CLIE, ' ') AS CODCLIENTE,
                                   TO_CHAR ('N') AS ESTFACTURACION,
                                   NVL (MAE_GRUPO_DESCU.COD_GRUP_DESC, ' ') AS CODGRUPO,
                                   NVL (PED_SOLIC_POSIC.VAL_PORC_DESC, 0) AS PORCOMISION,
                                   PED_SOLIC_CABEC.OID_SOLI_CABE,
                                   PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
                              FROM PED_SOLIC_CABEC,
                                   CRA_PERIO,
                                   SEG_PAIS,
                                   SEG_MARCA,
                                   SEG_CANAL,
                                   SEG_PERIO_CORPO,
                                   SEG_TIPO_PERIO,
                                   SEG_SUBAC,
                                   SEG_ACCES,
                                   PED_ESTAD_SOLIC,
                                   FAC_TIPO_DOCUM,
                                   FAC_TIPO_DOCUM_LEGAL,
                                   PED_TIPO_SOLIC_PAIS,
                                   PED_TIPO_SOLIC,
                                   PED_SOLIC_POSIC,
                                   PED_ESTAD_POSIC,
                                   MAE_PRODU,
                                   SEG_MARCA_PRODU,
                                   MAE_CLIEN,
                                   MAE_UNIDA_NEGOC,
                                   MAE_GRUPO_DESCU
                             WHERE (    (SEG_PAIS.COD_PAIS = psCodigoPais)
                                    AND (SEG_MARCA.COD_MARC = psCodigoMarca)
                                    AND (SEG_CANAL.COD_CANA = psCodigoCanal)
                                    AND (SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo)
                                    AND (SEG_ACCES.COD_ACCE = psCodigoAcceso)
                                    AND (PED_SOLIC_CABEC.FEC_FACT = TO_DATE (psFechaFacturacion, 'DD/MM/YYYY'))
                                    AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
                                    AND (PED_SOLIC_CABEC.IND_OC = 1)
                                    AND (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
                                    AND (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
                                    AND (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
                                    AND (PED_TIPO_SOLIC.IND_DEVO = 0)
                                    AND (PED_TIPO_SOLIC.IND_ANUL = 0)
                                    AND (PED_ESTAD_POSIC.COD_ESTA_POSI <> 'AN')
                                    AND (PED_SOLIC_POSIC.VAL_PORC_DESC <> 0 OR PED_SOLIC_POSIC.VAL_PORC_DESC IS NOT NULL
                                        )
                                    AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
                                    AND (CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                                    AND (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
                                    AND (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
                                    AND (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
                                    AND (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                                    AND (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                                    AND (PED_SOLIC_CABEC.SBAC_OID_SBAC = SEG_SUBAC.OID_SBAC)
                                    AND (SEG_SUBAC.ACCE_OID_ACCE = SEG_ACCES.OID_ACCE)
                                    AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
                                    AND (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
                                    AND (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOCU)
                                    AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS =
                                                                                  PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
                                        )
                                    AND (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
                                    AND (PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE)
                                    AND (PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = PED_ESTAD_POSIC.OID_ESTA_POSI(+))
                                    AND (PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD)
                                    AND (MAE_PRODU.MAPR_OID_MARC_PROD = SEG_MARCA_PRODU.OID_MARC_PROD(+))
                                    AND (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
                                    AND (MAE_PRODU.UNEG_OID_UNID_NEGO = MAE_UNIDA_NEGOC.OID_UNID_NEGO(+))
                                    AND (MAE_GRUPO_DESCU.PAIS_COD_PAIS = SEG_PAIS.COD_PAIS)
                                    AND (MAE_GRUPO_DESCU.COD_GRUP_ARTI = MAE_PRODU.VAL_GRUP_ARTI)
                                    AND (MAE_GRUPO_DESCU.COD_UNID_NEGO = MAE_UNIDA_NEGOC.COD_UNID_NEGO)
                                    AND (MAE_GRUPO_DESCU.COD_MARC_PROD = SEG_MARCA_PRODU.COD_MARC_PROD)
                                    AND (PED_SOLIC_POSIC.TPOS_OID_TIPO_POSI = 1)
                                   )) CABECERA,
                           PED_SOLIC_CABEC
                     WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
                  GROUP BY PERIODO,
                           CODCLIENTE,
                           ESTFACTURACION) X) TAB1,
         (SELECT   CABECERA.PERIODO,
                   CABECERA.CODCLIENTE,
                   CABECERA.ESTFACTURACION,
                   CABECERA.CODGRUPO,
                   MAX (CABECERA.PORCOMISION) PORCOMISION
              FROM (SELECT NVL (SEG_PERIO_CORPO.COD_PERI, ' ') AS PERIODO,
                           NVL (MAE_CLIEN.COD_CLIE, ' ') AS CODCLIENTE,
                           TO_CHAR ('N') AS ESTFACTURACION,
                           NVL (MAE_GRUPO_DESCU.COD_GRUP_DESC, ' ') AS CODGRUPO,
                           NVL (PED_SOLIC_POSIC.VAL_PORC_DESC, 0) AS PORCOMISION,
                           PED_SOLIC_CABEC.OID_SOLI_CABE,
                           PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
                      FROM PED_SOLIC_CABEC,
                           CRA_PERIO,
                           SEG_PAIS,
                           SEG_MARCA,
                           SEG_CANAL,
                           SEG_PERIO_CORPO,
                           SEG_TIPO_PERIO,
                           SEG_SUBAC,
                           SEG_ACCES,
                           PED_ESTAD_SOLIC,
                           FAC_TIPO_DOCUM,
                           FAC_TIPO_DOCUM_LEGAL,
                           PED_TIPO_SOLIC_PAIS,
                           PED_TIPO_SOLIC,
                           PED_SOLIC_POSIC,
                           PED_ESTAD_POSIC,
                           MAE_PRODU,
                           SEG_MARCA_PRODU,
                           MAE_CLIEN,
                           MAE_UNIDA_NEGOC,
                           MAE_GRUPO_DESCU
                     WHERE (    (SEG_PAIS.COD_PAIS = psCodigoPais)
                            AND (SEG_MARCA.COD_MARC = psCodigoMarca)
                            AND (SEG_CANAL.COD_CANA = psCodigoCanal)
                            AND (SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo)
                            AND (SEG_ACCES.COD_ACCE = psCodigoAcceso)
                            AND (PED_SOLIC_CABEC.FEC_FACT = TO_DATE (psFechaFacturacion, 'DD/MM/YYYY'))
                            AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
                            AND (PED_SOLIC_CABEC.IND_OC = 1)
                            AND (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
                            AND (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
                            AND (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
                            AND (PED_TIPO_SOLIC.IND_DEVO = 0)
                            AND (PED_TIPO_SOLIC.IND_ANUL = 0)
                            AND (PED_ESTAD_POSIC.COD_ESTA_POSI <> 'AN')
                            AND (PED_SOLIC_POSIC.VAL_PORC_DESC <> 0 OR PED_SOLIC_POSIC.VAL_PORC_DESC IS NOT NULL)
                            AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
                            AND (CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                            AND (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
                            AND (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
                            AND (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
                            AND (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                            AND (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                            AND (PED_SOLIC_CABEC.SBAC_OID_SBAC = SEG_SUBAC.OID_SBAC)
                            AND (SEG_SUBAC.ACCE_OID_ACCE = SEG_ACCES.OID_ACCE)
                            AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
                            AND (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
                            AND (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOCU)
                            AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
                            AND (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
                            AND (PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE)
                            AND (PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = PED_ESTAD_POSIC.OID_ESTA_POSI(+))
                            AND (PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD)
                            AND (MAE_PRODU.MAPR_OID_MARC_PROD = SEG_MARCA_PRODU.OID_MARC_PROD(+))
                            AND (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
                            AND (MAE_PRODU.UNEG_OID_UNID_NEGO = MAE_UNIDA_NEGOC.OID_UNID_NEGO(+))
                            AND (MAE_GRUPO_DESCU.PAIS_COD_PAIS = SEG_PAIS.COD_PAIS)
                            AND (MAE_GRUPO_DESCU.COD_GRUP_ARTI = MAE_PRODU.VAL_GRUP_ARTI)
                            AND (MAE_GRUPO_DESCU.COD_UNID_NEGO = MAE_UNIDA_NEGOC.COD_UNID_NEGO)
                            AND (MAE_GRUPO_DESCU.COD_MARC_PROD = SEG_MARCA_PRODU.COD_MARC_PROD)
                            AND (PED_SOLIC_POSIC.TPOS_OID_TIPO_POSI = 1)
                           )) CABECERA,
                   PED_SOLIC_CABEC
             WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
          GROUP BY PERIODO,
                   CODCLIENTE,
                   ESTFACTURACION,
                   CODGRUPO) TAB2
   WHERE TAB1.PERIODO1 = TAB2.PERIODO(+) AND TAB1.CODCLIENTE1 = TAB2.CODCLIENTE(+) AND TAB1.COD_GRUP1 = TAB2.CODGRUPO(+)
         AND TAB1.ESTFACTURACION1 = TAB2.ESTFACTURACION(+)
ORDER BY PERIODO1,
         CODCLIENTE1,
         COD_GRUP1,
         ESTFACTURACION1;

   TYPE interfazCab IS RECORD
   (
        periodo   VARCHAR(6),
        codigoCliente   VARCHAR(15),
        estadoFacturacion  VARCHAR(1),
        codigoGrupo   VARCHAR(2),
        porcentajeComision  VARCHAR(5)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
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
              lsLinea :=
                          interfazRecord(x).periodo                        ||';'||
                          interfazRecord(x).codigoCliente                         ||';'||
                          interfazRecord(x).estadoFacturacion                        ||';'||
                          interfazRecord(x).codigoGrupo         ||';'||
                          interfazRecord(x).porcentajeComision    ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_PORCE_REFER: '||ls_sqlerrm);

END INT_PR_BEL_PORCE_REFER;

/**************************************************************************
 Descripcion        : Genera el archivo para la Interfaz Enviar Unidades
                      Atendidas
 Fecha Creacion     : 27/02/2007
 Autor              : Marco Silva
***************************************************************************/
PROCEDURE INT_PR_BEL_UNIDA_ATEND
  (psCodigoPais        VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psCodigoMarca     VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoAcceso      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion  VARCHAR2
   )

IS
   CURSOR c_interfaz IS
      SELECT DISTINCT
              NVL(SEG_PERIO_CORPO.COD_PERI, ' ') AS PERIODO,
              NVL(PED_SOLIC_CABEC.VAL_NUME_SOLI, 0) AS NUMFACTURA,
              NVL(PRE_OFERT_DETAL.VAL_CODI_VENT, ' ') AS CODVENTA,
              DECODE(REC_LINEA_OPERA_RECLA.NUM_UNID_DEVU, 0, REC_LINEA_OPERA_RECLA.NUM_UNID_RECL, REC_LINEA_OPERA_RECLA.NUM_UNID_DEVU) AS UNDATENDIDAS
           FROM REC_OPERA_RECLA,
           REC_LINEA_OPERA_RECLA,
           REC_TIPO_MOVIM,
           REC_CABEC_RECLA,
           SEG_PAIS,
           PRE_MATRI_FACTU,
           PRE_OFERT_DETAL,
           CRA_PERIO,
           SEG_MARCA,
           SEG_CANAL,
           SEG_PERIO_CORPO,
           SEG_TIPO_PERIO,
           SEG_ACCES,
           PED_SOLIC_CABEC
          WHERE ((SEG_PAIS.COD_PAIS = psCodigoPais)
          AND   (SEG_MARCA.COD_MARC = psCodigoMarca)
         AND   (SEG_CANAL.COD_CANA = psCodigoCanal)
         AND   (SEG_ACCES.COD_ACCE = psCodigoAcceso)
         AND   (SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo)
         AND   (REC_OPERA_RECLA.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY'))
        AND   (REC_OPERA_RECLA.IND_ATEN = 1)
        AND   (REC_TIPO_MOVIM.COD_TIPO_MOVI = 'D')
         AND   (REC_OPERA_RECLA.OID_OPER_RECL = REC_LINEA_OPERA_RECLA.OPRE_OID_OPER_RECL)
       AND   (REC_LINEA_OPERA_RECLA.TIMO_OID_TIPO_MOVI = REC_TIPO_MOVIM.OID_TIPO_MOVI)
       AND   (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)
       AND   (REC_CABEC_RECLA.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
       AND   (REC_LINEA_OPERA_RECLA.MAFA_OID_MATR_FACT = PRE_MATRI_FACTU.OID_MATR_FACT (+))
         AND   (PRE_MATRI_FACTU.OFDE_OID_DETA_OFER = PRE_OFERT_DETAL.OID_DETA_OFER (+))
          AND   (REC_CABEC_RECLA.PERD_OID_PERI_RECL = CRA_PERIO.OID_PERI)
       AND   (CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
          AND   (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
          AND   (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
          AND   (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
         AND   (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
         AND   (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
         AND   (REC_CABEC_RECLA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE))
     ORDER BY PERIODO, NUMFACTURA, CODVENTA, UNDATENDIDAS ;


   TYPE interfazCab IS RECORD
   (
      periodo   VARCHAR2(6) ,
      numeroFactura          VARCHAR2(10) ,
      codigoVenta          VARCHAR2(15) ,
      unidadesAtendidas       VARCHAR2(5)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
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
              lsLinea :=
                          interfazRecord(x).periodo                        ||';'||
                          interfazRecord(x).numeroFactura                         ||';'||
                          interfazRecord(x).codigoVenta                        ||';'||
                          interfazRecord(x).unidadesAtendidas  ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_UNIDA_ATEND: '||ls_sqlerrm);

END INT_PR_BEL_UNIDA_ATEND;

/**************************************************************************
 Descripcion        : Genera el archivo para la Interfaz Enviar Ubicaciones
                      Geograficas
 Fecha Creacion     : 27/02/2007
 Autor              : Marco Silva
***************************************************************************/
PROCEDURE INT_PR_BEL_UBICA_GEOGR
  (psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2,
   psfechaInicioUltimoProceso DATE
   )
IS
   CURSOR c_interfaz IS
     WITH TEMPORAL_ZON_VALOR_ESTRU_GEOPO AS
        (SELECT DISTINCT
         OID_VALO_ESTR_GEOP,
       DES_GEOG,
       ORDE_1,
       ORDE_2,
       ORDE_3,
       ORDE_4,
       COD_POST,
       EGEO_OID_ESTR_GEOP
          FROM  SEG_PAIS,
                ZON_VALOR_ESTRU_GEOPO
          WHERE ((SEG_PAIS.COD_PAIS = psCodigoPais)
         AND (ZON_VALOR_ESTRU_GEOPO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
        AND ((psfechaInicioUltimoProceso IS NULL) OR (psfechaInicioUltimoProceso IS NOT NULL AND ZON_VALOR_ESTRU_GEOPO.FEC_ACTU  > psfechaInicioUltimoProceso))
              )
          )
       SELECT DISTINCT
        SUBSTR(TRIM(T.ORDE_1), -2) AS CODUBIGEOGRAFICA,
        SUBSTR(TRIM(T.COD_POST), 0, 6)  AS CODUBIGEOGRAFICAPOSTAL,
           SUBSTR(TRIM(T.DES_GEOG), 1,25)  AS  DESCOMDEPARTAMENTO,
           '' AS DESCOMPROVINCIA,
           '' AS DESCOMDISTRITO,
           SUBSTR(TRIM(T.DES_GEOG), 1, 10) AS DESABREDEPARTAMENTO,
           '' AS DESABREPROVINCIA,
           '' AS DESABREDISTRITO
      FROM TEMPORAL_ZON_VALOR_ESTRU_GEOPO T
      WHERE T.ORDE_1 IS NOT NULL
      AND   T.ORDE_2 IS NULL
      AND   T.ORDE_3 IS NULL
      AND   T.ORDE_4 IS NULL

       UNION

       SELECT DISTINCT
             CONCAT(SUBSTR(TRIM(T.ORDE_1), -2), SUBSTR(TRIM(T.ORDE_2), -2)) AS CODUBIGEOGRAFICA,
             SUBSTR(  TRIM(T.COD_POST),0,6) AS CODUBIGEOGRAFICAPOSTAL,
             SUBSTR(TRIM(T2.DES_GEOG),1,25) AS  DESCOMDEPARTAMENTO,
           SUBSTR(TRIM(T.DES_GEOG),1,25) AS DESCOMPROVINCIA,
             '' AS DESCOMDISTRITO,
             SUBSTR( TRIM(T2.DES_GEOG),1,10) AS DESABREDEPARTAMENTO,
             SUBSTR( TRIM(T.DES_GEOG),1,10) AS DESABREPROVINCIA,
             '' AS DESABREDISTRITO
         FROM TEMPORAL_ZON_VALOR_ESTRU_GEOPO T, TEMPORAL_ZON_VALOR_ESTRU_GEOPO T2
         WHERE T.ORDE_1 IS NOT NULL
         AND   T.ORDE_2 IS NOT NULL
         AND   T.ORDE_3 IS NULL
         AND   T.ORDE_4 IS NULL

          AND   T2.ORDE_1 IS NOT NULL
        AND   T2.ORDE_2 IS NULL
        AND   T2.ORDE_3 IS NULL
        AND   T2.ORDE_4 IS NULL
          AND   SUBSTR(TRIM(T.ORDE_1), -2) = SUBSTR(TRIM(T2.ORDE_1), -2)

       UNION

       SELECT DISTINCT
         CONCAT(CONCAT(SUBSTR(TRIM(T.ORDE_1), -2), SUBSTR(TRIM(T.ORDE_2), -2)), SUBSTR(TRIM(T.ORDE_3), -2)) AS CODUBIGEOGRAFICA,
         SUBSTR( TRIM(T.COD_POST),0,6) AS CODUBIGEOGRAFICAPOSTAL,
           SUBSTR(TRIM(T3.DES_GEOG),1,25) AS  DESCOMDEPARTAMENTO,
        SUBSTR(TRIM(T2.DES_GEOG),1,25) AS DESCOMPROVINCIA,
          SUBSTR(TRIM(T.DES_GEOG), 1,25) AS DESCOMDISTRITO,
           SUBSTR(TRIM(T3.DES_GEOG),0,10) AS DESABREDEPARTAMENTO,
           SUBSTR(TRIM(T2.DES_GEOG),0,10) AS DESABREPROVINCIA,
           SUBSTR(TRIM(T.DES_GEOG),0,10) AS DESABREDISTRITO
      FROM TEMPORAL_ZON_VALOR_ESTRU_GEOPO T,
        TEMPORAL_ZON_VALOR_ESTRU_GEOPO T2,
        TEMPORAL_ZON_VALOR_ESTRU_GEOPO T3
      WHERE T.ORDE_1 IS NOT NULL
      AND   T.ORDE_2 IS NOT NULL
      AND   T.ORDE_3 IS NOT NULL
      AND   T.ORDE_4 IS NULL

        AND   T3.ORDE_1 IS NOT NULL
       AND   T3.ORDE_2 IS NULL
       AND   T3.ORDE_3 IS NULL
       AND   T3.ORDE_4 IS NULL
        AND   SUBSTR(TRIM(T.ORDE_1), -2) = SUBSTR(TRIM(T3.ORDE_1), -2)

        AND   T2.ORDE_1 IS NOT NULL
        AND   T2.ORDE_2 IS NOT NULL
        AND   T2.ORDE_3 IS NULL
        AND   T2.ORDE_4 IS NULL

        AND   SUBSTR(TRIM(T.ORDE_1), -2) = SUBSTR(TRIM(T2.ORDE_1), -2)
        AND   SUBSTR(TRIM(T.ORDE_2), -2) = SUBSTR(TRIM(T2.ORDE_2), -2);

   TYPE interfazCab IS RECORD
   (
      codigoUbicGeo           VARCHAR2(6) ,
      codigoUbicGeoPostal     VARCHAR2(6) ,
      descCompletaDeparto    VARCHAR2(25) ,
      descCompletaProvincia   VARCHAR2(25) ,
      descCompletaDistrito    VARCHAR2(25) ,
      descAbreviadaDeparto    VARCHAR2(10) ,
      descAbreviadaProvincia  VARCHAR2(10) ,
      descAbreviadaDistrito   VARCHAR2(10)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   W_FILAS             NUMBER := 1000 ;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
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
              lsLinea :=
                          interfazRecord(x).codigoUbicGeo           ||';'||
                          interfazRecord(x).codigoUbicGeoPostal     ||';'||
                          interfazRecord(x).descCompletaDeparto     ||';'||
                          interfazRecord(x).descCompletaProvincia    ||';'||
                          interfazRecord(x).descCompletaDistrito     ||';'||
                          interfazRecord(x).descAbreviadaDeparto     ||';'||
                          interfazRecord(x).descAbreviadaProvincia   ||';'||
                          interfazRecord(x).descAbreviadaDistrito  ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_BEL_UBICA_GEOGR: '||ls_sqlerrm);

END INT_PR_BEL_UBICA_GEOGR;



END INT_PKG_BELCE;
/

