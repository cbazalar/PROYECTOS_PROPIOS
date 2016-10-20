CREATE OR REPLACE PACKAGE INT_PKG_LET IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/************************************************************************************
Descripcion       : Genera Interfase de Envio de Campaña Activa (CTRLFACT)
Fecha Creacion    : 11/03/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CAMPA_ACTIV(psCodigoPais VARCHAR2,
                                       psCodigoSistema VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo VARCHAR2);

/**************************************************************************************
Descripcion       : Genera Interfase de Envio de Consultoras (EBIDENT)
Fecha Creacion    : 11/03/2014
Autor             : Carlos Bazalar
**************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CONSU      (psCodigoPais VARCHAR2,
                                       psCodigoSistema VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo VARCHAR2,
                                       psCodigoPeriodo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Tipos de Operaciones (SDMLOGS)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TIPO_OPERA  (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2);

/***********************************************************************************
Descripcion       : Genera Interfase de Envio de Regiones (STAREAS)
Fecha Creacion    : 11/03/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_REGIO        (psCodigoPais VARCHAR2,
                                         psCodigoSistema VARCHAR2,
                                         psCodigoInterfaz VARCHAR2,
                                         psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas (STZONAS)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_ZONA        (psCodigoPais VARCHAR2,
                                         psCodigoSistema VARCHAR2,
                                         psCodigoInterfaz VARCHAR2,
                                         psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Territorios (STTERRI)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TERRI   (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas Consultoras (STDZONA)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_ZONA_CONSU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Territorios Consultoras (STDTERR)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TERRI_CONSU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Nuevas (EBNUEVAS)
Fecha Creacion    : 27/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_NUEVA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Facturas Consultoras (EBESTAD)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CONSU_FACTU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaProceso VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Recomendaciones(HIRCRECO)
Fecha Creacion    : 27/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_RECOM(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      pscodigoperiodo varchar2,
                                      psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Lideres (LIDLIDER)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_LIDER (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);


/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Cabecera
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_CAB(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Cabecera 1
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_CAB1(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Detalle
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_DETA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de tarjetas asociadas Cabecera
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TARJ_ASOC_CAB(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfase de Envio de tarjetas asociadas detalle
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENV_TARJ_ASOC_DETA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);
                                      
/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Detalle1
Fecha Creacion    : 24/02/2015
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_DETA1(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2);                                    

/***************************************************************************
Descripcion       : Reemplaza campos invalidos
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
FUNCTION LET_FN_REEMPLAZA_CARAC_INVAL(parametro in varchar2 )
   return varchar2 ;


END INT_PKG_LET;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_LET IS

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Campaña Activa (CTRLFACT)
Fecha Creacion    : 11/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CAMPA_ACTIV(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2)IS

   CURSOR c_interfaz IS
    SELECT X.COD_PERI,
           TO_CHAR(X.FEC_PROC, 'DD/MM/YYYY')
    FROM BAS_CTRL_FACT X
    WHERE X.STA_CAMP = '0'
      AND X.IND_CAMP_ACT = '1';

   TYPE interfazRec IS RECORD(
        campannaProceso  BAS_CTRL_FACT.COD_PERI%TYPE,
        fechaProceso     VARCHAR2(10)
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   BEGIN
       OPEN c_interfaz;
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).campannaProceso ||';'||
                              interfazRecord(x).fechaProceso ;
                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;
        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_CAMPA_ACTIV: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_CAMPA_ACTIV;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Consultoras (EBIDENT)
Fecha Creacion    : 11/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CONSU(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psCodigoPeriodo VARCHAR2)IS

   searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   replacestr VARCHAR2(100) := 'a        ';

   CURSOR c_interfaz(vnIdPais NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER, vsCodPeriAnterior VARCHAR2, vnIdPeriAnterior NUMBER) IS

     select cli.cod_clie,
            RE.COD_REGI || ZO.COD_ZONA || SE.COD_SECC || to_char(te.cod_terr) AS TERRI,
            TRANSLATE(
                 TRIM(CLI.VAL_NOM1) || ' ' || TRIM(CLI.VAL_NOM2) || ' ' || TRIM(CLI.VAL_APE1) || ' ' || TRIM(CLI.VAL_APE2),
                 searchstr,
                 replacestr) AS NOM_CLIE,
            TRANSLATE(
                 MI.NUM_DOCU_IDEN,
                 searchstr,
                 replacestr) AS NUM_DOCU_IDEN,
            TRANSLATE(
                 SUBSTR(des_abrv_tipo_via || ' '|| val_nomb_via || ' ' || TRIM (num_ppal) || ' ' || TRIM (val_inte) || ' ' || TRIM (val_obse),0,80),
                 searchstr,
                 replacestr) AS DIRECCION,
            (
             SELECT des_geog
               FROM zon_valor_estru_geopo eg
              WHERE eg.orde_1 = substr(cdi.cod_unid_geog,1,6)
                AND eg.orde_2 = substr(cdi.cod_unid_geog,7,6)
                AND eg.orde_3 = substr(cdi.cod_unid_geog,13,6)
                AND eg.orde_4 IS NULL
            ) AS MUNICIPIO,
            (
             SELECT DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO eg
              WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                AND eg.ORDE_3 IS NULL
            ) DISTRITO ,
            (
             SELECT DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO eg
              WHERE 1=1
                AND eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                AND eg.ORDE_2 IS NULL
                AND eg.ORDE_3 IS NULL
            ) PROVINCIA,
            (
             SELECT b.cod_peri
               FROM cra_perio a,
                    seg_perio_corpo b
              WHERE a.pais_oid_pais = vnIdPais
                AND a.marc_oid_marc = vnIdMarca
                AND a.cana_oid_cana = vnIdCanal
                AND a.fec_inic <= cli.fec_ingr
                AND a.fec_fina >= cli.fec_ingr
                AND b.oid_peri = a.peri_oid_peri
                AND ROWNUM = 1
             ) CAM_REGI,
             (
              SELECT NVL(seg.cod_peri,'')
                FROM MAE_CLIEN_PRIME_CONTA mpc,
                     cra_perio cra,
                     seg_perio_corpo seg ,
                     mae_clien_datos_adici macd
               WHERE mpc.clie_oid_clie = cli.oid_clie
                 AND seg.oid_peri = cra.peri_oid_peri
                 AND cra.oid_peri = mpc.perd_oid_peri
                 AND macd.esta_oid_esta_clie <> '01'
                 AND macd.clie_oid_clie = mpc.clie_oid_clie
              ) CAM_PRIM_PEDI,
             (
              SELECT MAX(seg.cod_peri)
                FROM ped_solic_cabec c,
                     cra_perio cra,
                     seg_perio_corpo seg
               WHERE clie_oid_clie = cli.oid_clie
                 AND seg.oid_peri = cra.peri_oid_peri
                 AND cra.oid_peri = c.perd_oid_peri
                 AND c.tspa_oid_tipo_soli_pais = (
                                                  SELECT tsp.oid_tipo_soli_pais
                                                    FROM ped_tipo_solic_pais tsp,
                                                         ped_tipo_solic ts
                                                   WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                                     AND ts.cod_tipo_soli = 'SOC'
                                                 )
             ) CAM_ULTI_PEDI,
             substr(ECL.COD_ESTA_CLIE,2,1) AS ESTA_CLIE,
             (
              SELECT TO_CHAR(clhe.esta_oid_esta_clie)
                FROM mae_clien_histo_estat clhe,
                     cra_perio perd,
                     seg_perio_corpo peri,
                     cra_perio perd_b,
                     seg_perio_corpo peri_b
               WHERE clhe.clie_oid_clie = cli.oid_clie
                 AND clhe.perd_oid_peri = perd.oid_peri
                 AND perd.peri_oid_peri = peri.oid_peri
                 AND nvl(clhe.perd_oid_peri_peri_fin, vnIdPeriAnterior) = perd_b.oid_peri
                 AND perd_b.peri_oid_peri = peri_b.oid_peri
                 AND vsCodPeriAnterior BETWEEN peri.cod_peri and peri_b.cod_peri
             ) EST_ANTE,
             TRANSLATE
             ((
               SELECT val_text_comu
                 FROM mae_clien_comun f1
                WHERE cli.oid_clie = f1.clie_oid_clie
                  AND f1.ticm_oid_tipo_comu = 1
              ) ,
              searchstr,
              replacestr
             ) AS TEL_CASA,
             TRANSLATE
             ((
               SELECT val_text_comu
                 FROM mae_clien_comun f2
                WHERE cli.oid_clie = f2.clie_oid_clie
                  AND f2.ticm_oid_tipo_comu = 6
              ) ,
              searchstr,
              replacestr
             ) AS TEL_CELU
        FROM mae_clien cli,
             mae_clien_direc cdi,
             mae_clien_ident mi,
             mae_clien_unida_admin cu,
             zon_terri_admin       zta,
             zon_regio             re,
             zon_zona              zo,
             zon_secci             se,
             zon_terri             te,
             seg_tipo_via          via,
             mae_clien_datos_adici est,
             mae_estat_clien       ecl
       WHERE cli.pais_oid_pais = vnidpais

         AND cli.oid_clie = mi.clie_oid_clie
         AND mi.val_iden_docu_prin = 1

         AND cli.oid_clie = cu.clie_oid_clie
         AND cu.ind_acti = 1
         AND cu.ztad_oid_terr_admi = zta.oid_terr_admi

         AND zta.zscc_oid_secc = se.oid_secc
         AND zta.terr_oid_terr = te.oid_terr
         AND se.zzon_oid_zona = zo.oid_zona
         AND zo.zorg_oid_regi = re.oid_regi

         AND cli.oid_clie  = cdi.clie_oid_clie
         AND cdi.ind_dire_ppal = 1
         AND cdi.ind_elim = 0
         AND cdi.tivi_oid_tipo_via = via.oid_tipo_via

         AND cli.oid_clie = est.clie_oid_clie
         AND est.ind_acti = 1

         AND est.esta_oid_esta_clie = ecl.oid_esta_clie
      ;


    TYPE interfazRec IS RECORD(
        codigoConsultora             mae_clien.cod_clie%TYPE,
        territorio                   VARCHAR2(100),
        nombreConsultora             VARCHAR2(200),
        documentoIdentidad           mae_clien_ident.NUM_DOCU_IDEN%TYPE,
        direccion                    VARCHAR2(500),
        municipio                    ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
        distrito                     ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
        provincia                    ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
        campannaRegistro             VARCHAR2(6),
        campannaPrimerPedido         VARCHAR2(6),
        campannaUltimoPedido         VARCHAR2(6),
        estatus                      MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
        estatusAnterior              MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
        telefonoCasa                 VARCHAR2(100),
        telefonoMovil                VARCHAR2(200)
    );

     TYPE interfazRecTab IS TABLE OF interfazRec;
     interfazRecord interfazRecTab;

     lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
     lbAbrirUtlFile      BOOLEAN := TRUE;
     v_handle            UTL_FILE.FILE_TYPE;
     lsLinea             VARCHAR2(1000);

     lsNombreArchivo     VARCHAR2(50);
     lnIdPais            NUMBER;
     lnIdMarca           NUMBER;
     lnIdCanal           NUMBER;
     lsCodPeriAnterior   VARCHAR2(6);
     lnIdPeriAnterior    NUMBER;

   BEGIN
        lnIdPais         := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        lnIdMarca        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
        lnIdCanal        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

        lsCodPeriAnterior  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
        lnIdPeriAnterior   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnterior, lnIdMarca, lnIdCanal);

        OPEN c_interfaz(lnIdPais,lnIdMarca,lnIdCanal, lsCodPeriAnterior, lnIdPeriAnterior)  ;
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
              lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).territorio ||';'||
                              interfazRecord(x).nombreConsultora ||';'||
                              interfazRecord(x).documentoIdentidad ||';'||
                              interfazRecord(x).direccion ||';'||
                              interfazRecord(x).municipio ||';'||
                              interfazRecord(x).distrito ||';'||
                              interfazRecord(x).provincia ||';'||
                              interfazRecord(x).campannaRegistro ||';'||
                              interfazRecord(x).campannaPrimerPedido ||';'||
                              interfazRecord(x).campannaUltimoPedido ||';'||
                              interfazRecord(x).estatus ||';'||
                              interfazRecord(x).estatusAnterior ||';'||
                              interfazRecord(x).telefonoCasa ||';'||
                              interfazRecord(x).telefonoMovil;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

               END LOOP;

            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_CONSU: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_CONSU;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Tipos de Operaciones (SDMLOGS)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TIPO_OPERA  (psCodigoPais VARCHAR2,
                                        psCodigoSistema VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                        psNombreArchivo VARCHAR2)
IS

  CURSOR c_interfaz(vnIdPais NUMBER) IS
     SELECT CASE
               WHEN fpc.tip_cier = 'R' THEN '2'
               WHEN fpc.tip_cier = 'C' THEN '3'
               ELSE '1'
            END tipo_cier,
            fpc.cam_proc,
            fpc.cod_regi,
            TO_CHAR(fpc.fec_cier, 'DD/MM/YYYY')
       FROM fac_progr_cierr fpc,
            bas_ctrl_fact bcf
      WHERE fpc.cam_proc = bcf.cod_peri
        AND bcf.sta_camp = '0'
        AND bcf.ind_camp_act = '1'
        AND fpc.tip_cier in ('C', 'R')
        AND fpc.est_regi <> '9'
      ORDER BY fpc.cam_proc, fpc.fec_cier ;


   TYPE interfazRec IS RECORD(
        codigoTipo      VARCHAR2(1),
        codigoCampana   VARCHAR2(6),
        codigoRegion    ZON_REGIO.cod_regi%TYPE,
        fechaProceso    VARCHAR2(10)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lnIdPais            NUMBER;
   lbEncontroRegistros BOOLEAN;
   lsFecha             VARCHAR2(10);
   lsRegion            VARCHAR2(2);
   lsCodigo            VARCHAR2(1);
   lsCampana           VARCHAR2(6);

   BEGIN
        lbEncontroRegistros := FALSE;
        lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

        OPEN c_interfaz(lnIdPais);
          LOOP
              FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

              IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                lbAbrirUtlFile := FALSE;
              END IF;
              IF interfazRecord.COUNT > 0 THEN
                 lbEncontroRegistros := TRUE;
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lsLinea := interfazRecord(x).codigoTipo ||';'||
                                interfazRecord(x).codigoCampana ||';'||
                                interfazRecord(x).codigoRegion ||';'||
                                interfazRecord(x).fechaProceso;

                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
              EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;

        CLOSE c_interfaz;

        IF NOT lbEncontroRegistros THEN
           SELECT '1',
                  x.cod_peri,
                  NULL,
                  TO_CHAR(x.fec_proc, 'DD/MM/YYYY')
             INTO lsCodigo,
                  lsCampana,
                  lsRegion,
                  lsFecha
             FROM cra_perio cp,
                  bas_ctrl_fact x,
                  seg_perio_corpo a
            WHERE x.sta_camp = '0'
              AND x.ind_camp_act = '1'
              AND a.cod_peri = x.cod_peri
              AND a.oid_peri = cp.peri_oid_peri ;

           lsLinea := lsCodigo ||';'||
                      lsCampana||';'||
                      lsRegion ||';'||
                      lsFecha;

           UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END IF;


        IF NOT lbAbrirUtlFile THEN
         	 UTL_FILE.FCLOSE(V_HANDLE);
           /* Procedimiento final para generar interfaz */
           GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_TIPO_OPERA: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_TIPO_OPERA;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Regiones (STAREAS)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_REGIO        (psCodigoPais VARCHAR2,
                                         psCodigoSistema VARCHAR2,
                                         psCodigoInterfaz VARCHAR2,
                                         psNombreArchivo VARCHAR2)IS

   searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   replacestr VARCHAR2(100) := 'a        ';
   CURSOR c_interfaz(vnIdPais NUMBER) IS
        SELECT x.cod_regi,
               x.des_regi,
               translate(
                   TRIM(CLI.VAL_NOM1) || ' ' || TRIM(CLI.VAL_NOM2) || ' ' || TRIM(CLI.VAL_APE1) || ' ' || TRIM(CLI.VAL_APE2),
                   searchstr,
                   replacestr) AS NOM_CLIE,
               translate(
                   (SELECT val_text_comu
                              FROM mae_clien_comun f1
                             WHERE cli.oid_clie = f1.clie_oid_clie
                                   AND f1.ticm_oid_tipo_comu = 6) ,
                   searchstr,
                   replacestr) AS TEL_CASA,
                translate(
                   (SELECT VAL_TEXT_COMU
                                    FROM mae_clien_comun f2
                                   where cli.oid_clie = f2.clie_oid_clie
                                     AND f2.ticm_oid_tipo_comu = 2006
                                  ) ,
                   searchstr,
                   replacestr) AS VAL_EMAI
        FROM zon_regio x,
             mae_clien cli
        WHERE X.PAIS_OID_PAIS = vnIdPais
         AND x.ind_acti = 1
         and x.ind_borr = 0
         AND x.clie_oid_clie = cli.oid_clie(+)
        ORDER BY cod_regi;

   TYPE interfazRec IS RECORD(
        codigoRegion   zon_regio.cod_regi%TYPE,
        nombreRegion   zon_regio.des_regi%TYPE,
        nombreGR       VARCHAR2(500),
        telefonoGR     VARCHAR2(500),
        emailGR        VARCHAR2(500)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnIdPais            NUMBER;

   BEGIN
        lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        OPEN c_interfaz(lnIdPais);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
              lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoRegion ||';'||
                              interfazRecord(x).nombreRegion ||';'||
                              interfazRecord(x).nombreGR        ||';'||
                              interfazRecord(x).telefonoGR        ||';'||
                              interfazRecord(x).emailGR;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_REGIO: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_REGIO;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas (STZONAS)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_ZONA        (psCodigoPais VARCHAR2,
                                        psCodigoSistema VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                        psNombreArchivo VARCHAR2)IS

   searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   replacestr VARCHAR2(100) := 'a        ';

   CURSOR c_interfaz(vnIdPais NUMBER) IS

        SELECT zon.cod_zona,
               zon.des_zona,
               TRANSLATE
               (
                TRIM(cli.val_nom1) || ' ' || TRIM(cli.val_nom2) || ' ' || TRIM(CLI.VAL_APE1) || ' ' || TRIM(CLI.VAL_APE2),searchstr,replacestr
               ) nom_clie,
               zon.ind_acti est_zona,
               TRANSLATE
               (
                (
                 SELECT val_text_comu
                   FROM mae_clien_comun f2
                  WHERE cli.oid_clie = f2.clie_oid_clie
                    AND f2.ticm_oid_tipo_comu = 2006
                ), searchstr, replacestr
               ) val_emai
          FROM zon_zona zon,
               mae_clien cli
         WHERE zon.pais_oid_pais = vnIdPais
           AND zon.ind_borr = 0
           AND zon.clie_oid_clie = cli.oid_clie(+)
         ORDER BY zon.cod_zona;

   TYPE interfazRec IS RECORD(
        codigoZona     zon_zona.cod_zona%TYPE,
        nombreZona     zon_zona.des_zona%TYPE,
        nombreGZ       VARCHAR2(500),
        estatus        VARCHAR2(1),
        emailGZ        VARCHAR2(500)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lnIdPais            NUMBER;

   BEGIN
        lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

        OPEN c_interfaz(lnIdPais);
          LOOP
              FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

              IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                lbAbrirUtlFile := FALSE;
              END IF;
              IF interfazRecord.COUNT > 0 THEN
                 FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lsLinea := interfazRecord(x).codigoZona ||';'||
                                interfazRecord(x).nombreZona ||';'||
                                interfazRecord(x).nombreGZ   ||';'||
                                interfazRecord(x).estatus    ||';'||
                                interfazRecord(x).emailGZ;

                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 END LOOP;
              END IF;
              EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_ZONA: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_ZONA;


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Territorios (STTERRI)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TERRI   (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2)
IS

   searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   replacestr VARCHAR2(100) := 'a        ';
   CURSOR c_interfaz(vnIdPais NUMBER) IS
       SELECT RE.COD_REGI || ZO.COD_ZONA || SE.COD_SECC || TO_CHAR(TE.COD_TERR) AS TERRI,
              translate(
                 est.des_geog,
                 searchstr,
                 replacestr) AS des_geog
        FROM zon_terri_admin      zta,
            ZON_REGIO             RE,
            zon_zona              zo,
            zon_secci             se,
            zon_terri             te,
            zon_valor_estru_geopo est
        WHERE te.pais_oid_pais = vnIdPais
          AND TE.IND_BORR = 0
          AND te.vepo_oid_valo_estr_geop = est.oid_valo_estr_geop
          AND ZTA.TERR_OID_TERR = TE.OID_TERR
          AND SE.OID_SECC = ZTA.ZSCC_OID_SECC
          AND ZO.OID_ZONA = SE.ZZON_OID_ZONA
          AND RE.OID_REGI = ZO.ZORG_OID_REGI
        ORDER BY RE.COD_REGI,  ZO.COD_ZONA , SE.COD_SECC ,TE.COD_TERR;

   TYPE interfazRec IS RECORD(
        codigoTerri     VARCHAR2(100),
        nombreTerri     VARCHAR2(500)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnIdPais            NUMBER;

   BEGIN
        lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        OPEN c_interfaz(lnIdPais);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
              lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoTerri ||';'||
                              interfazRecord(x).nombreTerri;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_TERRI: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_TERRI;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas Consultoras (STDZONA)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_ZONA_CONSU (psCodigoPais VARCHAR2,
                                       psCodigoSistema VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo VARCHAR2,
                                       psCodigoPeriodo VARCHAR2)
is
   CURSOR c_interfaz(vnIdPais NUMBER, vnIdPeriodo NUMBER) IS


select hmae.cod_zona codigozona,
       psCodigoPeriodo codigoPeriodo,
       sum(hmae.val_nume_ingr) totalPrimerPedido,
       sum(hmae.val_nume_rein) totalReingreso,
       sum(hmae.val_nume_egre) totalEgresadas,
       SUM(hmae.val_nume_acti) - (SUM(hmae.val_nume_ingr) + SUM(hmae.val_nume_rein) - SUM(hmae.val_nume_egre)) totalRegulares,
       sum(nvl(pedi.val_nume_pedi_tota,0)) totalPedidos
  from (
          select zorg.cod_regi,
                 zzon.cod_zona,
                 zscc.cod_secc,
                 terr.cod_terr,
                 sum(case when clhe.esta_oid_esta_clie in (2,8) then 1 else 0 end) val_nume_ingr,
                 sum(case when clhe.esta_oid_esta_clie = 6 then 1 else 0 end) val_nume_rein,
                 sum(case when clhe.esta_oid_esta_clie = 5 and clda.num_camp_sin_pedi = 2 then 1 else 0 end) val_nume_egre,
                 sum(case when clhe.esta_oid_esta_clie in (2,3,4,6,8) then 1 else 0 end) val_nume_acti
            from mae_clien_histo_estat clhe,
                 mae_clien_unida_admin cuad,
                 zon_terri_admin       ztad,
                 zon_terri             terr,
                 zon_secci             zscc,
                 zon_zona              zzon,
                 zon_regio             zorg,
                 mae_clien_datos_adici clda
           where 1=1
             and clhe.clie_oid_clie = cuad.clie_oid_clie
             and clhe.clie_oid_clie = clda.clie_oid_clie
             and cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
             and ztad.terr_oid_terr = terr.oid_terr
             and ztad.zscc_oid_secc = zscc.oid_secc
             and zscc.zzon_oid_zona = zzon.oid_zona
             and zzon.zorg_oid_regi = zorg.oid_regi
             --
             and vnIdPeriodo between clhe.perd_oid_peri and nvl(clhe.perd_oid_peri_peri_fin,vnIdPeriodo)
             and vnIdPeriodo between cuad.perd_oid_peri_ini and nvl(cuad.perd_oid_peri_fin,vnIdPeriodo)
           group by zorg.cod_regi,
                    zzon.cod_zona,
                    zscc.cod_secc,
                    terr.cod_terr
       ) hmae,
       (
          select zorg.cod_regi,
                 zzon.cod_zona,
                 zscc.cod_secc,
                 terr.cod_terr,
                 count( distinct soca.clie_oid_clie ) val_nume_pedi_tota
            from ped_solic_cabec       soca,
                 ped_solic_cabec       cons,
                 mae_clien             clie,
                 seg_modul             modu,
                 ped_tipo_solic_pais   tspa,
                 ped_tipo_solic        tsol,
                 cra_perio             perd,
                 seg_perio_corpo       peri,
                 mae_clien_unida_admin cuad,
                 zon_terri_admin       ztad,
                 zon_terri             terr,
                 zon_secci             zscc,
                 zon_zona              zzon,
                 zon_regio             zorg
           where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
             and soca.clie_oid_clie = clie.oid_clie
             and soca.modu_oid_modu = modu.oid_modu
             and soca.perd_oid_peri = perd.oid_peri
             and soca.clie_oid_clie = cuad.clie_oid_clie
             and cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
             and ztad.terr_oid_terr = terr.oid_terr
             and ztad.zscc_oid_secc = zscc.oid_secc
             and zscc.zzon_oid_zona = zzon.oid_zona
             and zzon.zorg_oid_regi = zorg.oid_regi
             and perd.peri_oid_peri = peri.oid_peri
             and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
             and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
             --
             and soca.ind_ts_no_conso = 1
             and soca.ind_oc = 1
             and soca.ind_pedi_prue <> 1
             and soca.fec_fact is not null
             and modu.cod_modu <> 15
             and tsol.ind_anul <> 1
             and tsol.ind_devo <> 1
             and peri.cod_peri = pscodigoperiodo
             and vnIdPeriodo between cuad.perd_oid_peri_ini and nvl(cuad.perd_oid_peri_fin,vnIdPeriodo)
           group by zorg.cod_regi,
                    zzon.cod_zona,
                    zscc.cod_secc,
                    terr.cod_terr
     ) pedi
 where 1=1
   and hmae.cod_regi = pedi.cod_regi (+)
   and hmae.cod_zona = pedi.cod_zona (+)
   and hmae.cod_secc = pedi.cod_secc (+)
   and hmae.cod_terr = pedi.cod_terr (+)
 group by hmae.cod_regi,
          hmae.cod_zona
     ;

   TYPE interfazRec IS RECORD(
        codigozona            zon_zona.cod_zona%type,
        codigoPeriodo         NUMBER(12),
        totalPrimerPedido     NUMBER(12),
        totalReingreso        NUMBER(12),
        totalEgresadas        NUMBER(12),
    --    totalActivas          NUMBER(12),
        totalRegulares        NUMBER(12),
        totalPedidos          NUMBER(12)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnidpais            number;
   lnidperiodo         cra_perio.oid_peri%type;

   BEGIN
        lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
        lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

        OPEN c_interfaz(lnIdPais,lnidperiodo);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lslinea := interfazrecord(x).codigozona ||';'||
                              interfazRecord(x).codigoPeriodo ||';'||
                              interfazRecord(x).totalPrimerPedido ||';'||
                              interfazRecord(x).totalReingreso ||';'||
                              interfazRecord(x).totalEgresadas ||';'||
                 --             interfazrecord(x).totalactivas ||';'||
                              interfazRecord(x).totalRegulares ||';'||
                              interfazRecord(x).totalPedidos ;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_ZONA_CONSU: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_ZONA_CONSU;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Territorios Consultoras (STDTERR)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TERRI_CONSU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
is

CURSOR c_interfaz(vnIdPais NUMBER, vnIdPeriodo NUMBER) IS

SELECT hmae.cod_regi || hmae.cod_zona || hmae.cod_secc || to_char(hmae.cod_terr) codigoterri,
       pscodigoperiodo codigoPeriodo,
       SUM(hmae.val_nume_ingr) totalPrimerPedido,
       SUM(hmae.val_nume_rein) totalReingreso,
       SUM(hmae.val_nume_egre) totalEgresadas,
       SUM(hmae.val_nume_acti) - (SUM(hmae.val_nume_ingr) + SUM(hmae.val_nume_rein) - SUM(hmae.val_nume_egre)) totalRegulares,
       SUM(nvl(pedi.val_nume_pedi_tota,0)) totalPedidos
  from (
          select zorg.cod_regi,
                 zzon.cod_zona,
                 zscc.cod_secc,
                 terr.cod_terr,
                 SUM(case when clhe.esta_oid_esta_clie in (2,8) then 1 else 0 end) val_nume_ingr,
                 SUM(case when clhe.esta_oid_esta_clie = 6 then 1 else 0 end) val_nume_rein,
                 SUM(case when clhe.esta_oid_esta_clie = 5 and clda.num_camp_sin_pedi = 2 then 1 else 0 end) val_nume_egre,
                 SUM(case when clhe.esta_oid_esta_clie in (2,3,4,6,8) then 1 else 0 end) val_nume_acti
            from mae_clien_histo_estat clhe,
                 mae_clien_unida_admin cuad,
                 zon_terri_admin       ztad,
                 zon_terri             terr,
                 zon_secci             zscc,
                 zon_zona              zzon,
                 zon_regio             zorg,
                 mae_clien_datos_adici clda
           where 1=1
             and clhe.clie_oid_clie = cuad.clie_oid_clie
             and clhe.clie_oid_clie = clda.clie_oid_clie
             and cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
             and ztad.terr_oid_terr = terr.oid_terr
             and ztad.zscc_oid_secc = zscc.oid_secc
             and zscc.zzon_oid_zona = zzon.oid_zona
             and zzon.zorg_oid_regi = zorg.oid_regi
             --
             and vnIdPeriodo between clhe.perd_oid_peri and nvl(clhe.perd_oid_peri_peri_fin,vnIdPeriodo)
             and vnIdPeriodo between cuad.perd_oid_peri_ini and nvl(cuad.perd_oid_peri_fin,vnIdPeriodo)
           group by zorg.cod_regi,
                    zzon.cod_zona,
                    zscc.cod_secc,
                    terr.cod_terr
       ) hmae,
       (
          select zorg.cod_regi,
                 zzon.cod_zona,
                 zscc.cod_secc,
                 terr.cod_terr,
                 count( distinct soca.clie_oid_clie ) val_nume_pedi_tota
            from ped_solic_cabec       soca,
                 ped_solic_cabec       cons,
                 mae_clien             clie,
                 seg_modul             modu,
                 ped_tipo_solic_pais   tspa,
                 ped_tipo_solic        tsol,
                 cra_perio             perd,
                 seg_perio_corpo       peri,
                 mae_clien_unida_admin cuad,
                 zon_terri_admin       ztad,
                 zon_terri             terr,
                 zon_secci             zscc,
                 zon_zona              zzon,
                 zon_regio             zorg
           where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
             and soca.clie_oid_clie = clie.oid_clie
             and soca.modu_oid_modu = modu.oid_modu
             and soca.perd_oid_peri = perd.oid_peri
             and soca.clie_oid_clie = cuad.clie_oid_clie
             and cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
             and ztad.terr_oid_terr = terr.oid_terr
             and ztad.zscc_oid_secc = zscc.oid_secc
             and zscc.zzon_oid_zona = zzon.oid_zona
             and zzon.zorg_oid_regi = zorg.oid_regi
             and perd.peri_oid_peri = peri.oid_peri
             and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
             and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
             --
             and soca.ind_ts_no_conso = 1
             and soca.ind_oc = 1
             and soca.ind_pedi_prue <> 1
             and soca.fec_fact is not null
             and modu.cod_modu <> 15
             and tsol.ind_anul <> 1
             and tsol.ind_devo <> 1
             and peri.cod_peri = pscodigoperiodo
             and vnIdPeriodo between cuad.perd_oid_peri_ini and nvl(cuad.perd_oid_peri_fin,vnIdPeriodo)
           group by zorg.cod_regi,
                    zzon.cod_zona,
                    zscc.cod_secc,
                    terr.cod_terr
     ) pedi
 where 1=1
   and hmae.cod_regi = pedi.cod_regi (+)
   and hmae.cod_zona = pedi.cod_zona (+)
   and hmae.cod_secc = pedi.cod_secc (+)
   and hmae.cod_terr = pedi.cod_terr (+)
 group by hmae.cod_regi,
          hmae.cod_zona,
          hmae.cod_secc,
          hmae.cod_terr
     ;

   TYPE interfazRec IS RECORD(
        codigoTerri           VARCHAR2(100),
        codigoPeriodo         VARCHAR2(6),
        totalPrimerPedido     NUMBER(12),
        totalReingreso        NUMBER(12),
        totalEgresadas        NUMBER(12),
        totalRegulares        NUMBER(12),
        totalPedidos          NUMBER(12)
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnidpais            number;
   lnIdPeriodo         cra_perio.oid_peri%type;

   BEGIN
        lnIdPais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
        lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

        OPEN c_interfaz(lnIdPais,lnIdPeriodo);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lslinea := interfazrecord(x).codigoterri ||';'||
                              interfazRecord(x).codigoPeriodo ||';'||
                              interfazRecord(x).totalPrimerPedido ||';'||
                              interfazRecord(x).totalReingreso ||';'||
                              interfazRecord(x).totalEgresadas ||';'||
                              interfazRecord(x).totalRegulares ||';'||
                              interfazRecord(x).totalPedidos ;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_TERRI_CONSU: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_TERRI_CONSU;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Nuevas (EBNUEVAS)
Fecha Creacion    : 27/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_NUEVA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      pscodigointerfaz varchar2,
                                      psNombreArchivo VARCHAR2) IS

   CURSOR c_interfaz(lsCodPeriodo VARCHAR2) IS
    SELECT clie.cod_clie ebicodeb,
       (
         SELECT COUNT(*) val_nume_peds
           FROM ped_solic_cabec_acum2 sca2,
                cra_perio perd,
                seg_perio_corpo peri
          WHERE sca2.clie_oid_clie = clie.oid_clie
            AND sca2.perd_oid_peri = perd.oid_peri
            AND perd.peri_oid_peri = peri.oid_peri
            AND peri.cod_peri < (
                                  SELECT MIN( peri_2.cod_peri ) val_peri_mini
                                    FROM mae_clien_histo_estat clhe,
                                         cra_perio perd_2,
                                         seg_perio_corpo peri_2
                                   WHERE clhe.perd_oid_peri = perd_2.oid_peri
                                     AND perd_2.peri_oid_peri = peri_2.oid_peri
                                     AND clhe.esta_oid_esta_clie = 5
                                     AND clhe.clie_oid_clie = sca2.clie_oid_clie
                                )
       ) ebinrped, -- # peds antes 1er egreso
      (
         SELECT COUNT(*) val_nume_peds
           FROM ped_solic_cabec_acum2 sca2,
                cra_perio perd,
                seg_perio_corpo peri
          WHERE sca2.clie_oid_clie = clie.oid_clie
            AND sca2.perd_oid_peri = perd.oid_peri
            AND perd.peri_oid_peri = peri.oid_peri
            AND peri.cod_peri <= NVL(
                                  ( SELECT MIN( peri_2.cod_peri ) val_peri_mini
                                    FROM mae_clien_histo_estat clhe,
                                         cra_perio perd_2,
                                         seg_perio_corpo peri_2
                                   WHERE clhe.perd_oid_peri = perd_2.oid_peri
                                     AND perd_2.peri_oid_peri = peri_2.oid_peri
                                     AND clhe.esta_oid_esta_clie = 4
                                     AND clhe.clie_oid_clie = sca2.clie_oid_clie
                                ),lsCodPeriodo)
       ) ebincped, -- # peds const desde 1er ped
       (
         SELECT COUNT(*) val_nume_peds
           FROM ped_solic_cabec_acum2 sca2,
                cra_perio perd,
                seg_perio_corpo peri
          WHERE sca2.clie_oid_clie = clie.oid_clie
            AND sca2.perd_oid_peri = perd.oid_peri
            AND perd.peri_oid_peri = peri.oid_peri
            AND PERI.COD_PERI >=  NVL(
                              ( SELECT MAX( peri_2.cod_peri ) val_peri_mini
                                FROM mae_clien_histo_estat clhe,
                                     cra_perio perd_2,
                                     seg_perio_corpo peri_2
                               WHERE clhe.perd_oid_peri = perd_2.oid_peri
                                 AND perd_2.peri_oid_peri = peri_2.oid_peri
                                 AND clhe.esta_oid_esta_clie IN (2,8)
                                 AND clhe.clie_oid_clie = sca2.clie_oid_clie
                            ),lsCodPeriodo)
       ) ebintped -- # peds total desde 1er ped
    FROM mae_clien clie,
       mae_clien_tipo_subti ctsu,
       mae_clien_datos_adici clda
    WHERE clie.oid_clie = ctsu.clie_oid_clie
       AND clie.oid_clie = clda.clie_oid_clie
       AND ctsu.ticl_oid_tipo_clie = 2
       AND ctsu.sbti_oid_subt_clie IN (1,21);

   TYPE interfazRec IS RECORD(
        codigoConsultora           mae_clien.cod_clie%TYPE,
        cantidadPedidosAntes       NUMBER(12),
        cantidadPedidosCtes        NUMBER(12),
        cantidadTotalPedidos       NUMBER(12)
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsCodPeriodo        VARCHAR2(6);

   BEGIN

       SELECT COD_PERI
       INTO lsCodPeriodo
       FROM BAS_CTRL_FACT
       WHERE STA_CAMP = 0
       AND  IND_CAMP_ACT = 1;

       OPEN c_interfaz(lsCodPeriodo);
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).cantidadPedidosAntes ||';'||
                              interfazRecord(x).cantidadPedidosCtes ||';'||
                              interfazRecord(x).cantidadTotalPedidos ;
                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;
        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_NUEVA: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_NUEVA;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Facturas Consultoras (EBESTAD)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_CONSU_FACTU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaProceso VARCHAR2)
IS
   CURSOR c_interfaz(vnIdPais NUMBER, vnIdPeriodo NUMBER) IS
   SELECT CLI.COD_CLIE,
       psCodigoPeriodo COD_PERI,
       RE.COD_REGI || ZO.COD_ZONA || SE.COD_SECC || TO_CHAR(TE.COD_TERR) AS COD_TERR,
       TO_CHAR(C.FEC_FACT,'DD/MM/YYYY') FEC_FACT,
       ( SELECT
          CASE
          WHEN o.COD_orig = 'OC' THEN 'O'
          WHEN o.COD_orig = 'DI' THEN 'O'
          WHEN o.COD_orig = 'DD' THEN 'O'
          WHEN o.COD_orig = 'WE' THEN 'W'
          WHEN o.COD_orig = 'OL' THEN 'W'
          WHEN o.COD_orig = 'MI' THEN 'M'
          WHEN o.COD_orig = 'CC' THEN 'M'
          ELSE o.COD_orig
          END
          FROM sto_orige_docum       o,
               sto_combi_orige_docum c,
               sto_docum_digit       d,
               int_solic_conso_cabec e
         WHERE d.sec_nume_docu = e.sec_nume_docu
           AND d.num_lote = e.num_lote
           AND o.cod_tipo_docu = d.cod_tipo_docu
           AND o.cod_pais = d.cod_pais
           AND o.cod_tipo_docu = c.cod_tipo_docu
           AND o.cod_orig = c.cod_orig
           AND c.ind_rece_ocr = e.ind_rece_ocr
           AND c.ind_rece_web = e.ind_rece_web
           AND c.ind_rece_dd = e.ind_rece_dd
           AND c.ind_rece_digi = e.ind_rece_digi
           AND c.ind_rece_cc = e.ind_rece_cc
           AND c.ind_rece_mens = e.ind_rece_mens
           AND c.ind_rece_onli = e.ind_rece_onli
           AND c.ind_rece_ivr = e.ind_rece_ivr
           AND e.soca_oid_soli_cabe_refe = c.oid_soli_cabe) TIP_ORIG_FACT,

       ( SELECT
          CASE
          WHEN e.IND_RECE_WEB='1'    AND
               e.ind_rece_ocr = '0'  AND
               e.ind_rece_dd = '0'   AND
               e.ind_rece_digi = '0' AND
               e.ind_rece_cc = '0'   AND
               e.ind_rece_mens = '0' AND
               e.ind_rece_onli = '0' AND
               e.ind_rece_ivr ='0'
               THEN 'M'
          WHEN e.IND_RECE_WEB='0'    AND
               e.ind_rece_ocr = '0'  AND
               e.ind_rece_dd = '1'   AND
               e.ind_rece_digi = '0' AND
               e.ind_rece_cc = '0'   AND
               e.ind_rece_mens = '0' AND
               e.ind_rece_onli = '0' AND
               e.ind_rece_ivr ='0'
               THEN 'D'
          WHEN e.IND_RECE_WEB='1'    AND
               e.ind_rece_ocr = '0'  AND
               e.ind_rece_dd = '1'   AND
               e.ind_rece_digi = '0' AND
               e.ind_rece_cc = '0'   AND
               e.ind_rece_mens = '0' AND
               e.ind_rece_onli = '0' AND
               e.ind_rece_ivr ='0'
               THEN 'X'
          END
          FROM sto_orige_docum       o,
               sto_combi_orige_docum c,
               sto_docum_digit       d,
               int_solic_conso_cabec e
         WHERE d.sec_nume_docu = e.sec_nume_docu
           AND d.num_lote = e.num_lote
           AND o.cod_tipo_docu = d.cod_tipo_docu
           AND o.cod_pais = d.cod_pais
           AND o.cod_tipo_docu = c.cod_tipo_docu
           AND o.cod_orig = c.cod_orig
           AND c.ind_rece_ocr = e.ind_rece_ocr
           AND c.ind_rece_web = e.ind_rece_web
           AND c.ind_rece_dd = e.ind_rece_dd
           AND c.ind_rece_digi = e.ind_rece_digi
           AND c.ind_rece_cc = e.ind_rece_cc
           AND c.ind_rece_mens = e.ind_rece_mens
           AND c.ind_rece_onli = e.ind_rece_onli
           AND c.ind_rece_ivr = e.ind_rece_ivr
           AND e.soca_oid_soli_cabe_refe = c.oid_soli_cabe
       )  TIP_WEB_FACT,

       (select
         val_tota_paga_loca
       from ped_solic_cabec x
       where x.oid_soli_cabe = c.soca_oid_soli_cabe
       ) VAL_TOTA_PAGA,

       C.VAL_TOTA_PAGA_LOCA
     FROM ped_solic_cabec c,
          MAE_CLIEN CLI,
          mae_clien_unida_admin cu,
          zon_terri_admin       zta,
          ZON_REGIO             RE,
          zon_zona              zo,
          zon_secci             se,
          zon_terri             te
     WHERE C.PAIS_OID_PAIS = vnIdPais
       AND C.PERD_OID_PERI = vnIdPeriodo
   --    AND C.FEC_FACT = TO_DATE(psFechaProceso,'DD/MM/YYYY')
       AND c.clie_oid_clie = cli.oid_clie

       AND CLI.OID_CLIE = CU.CLIE_OID_CLIE
       AND CU.IND_ACTI = 1
       AND cu.ztad_oid_terr_admi = zta.oid_terr_admi

       AND zta.zscc_oid_secc = se.oid_secc
       AND ZTA.TERR_OID_TERR = TE.OID_TERR
       AND SE.ZZON_OID_ZONA = ZO.OID_ZONA
       AND ZO.ZORG_OID_REGI = RE.OID_REGI

       AND c.tspa_oid_tipo_soli_pais = (SELECT tsp.oid_tipo_soli_pais
                                        FROM ped_tipo_solic_pais tsp,
                                             ped_tipo_solic      ts
                                       WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                         AND ts.cod_tipo_soli = 'SOC');

   TYPE interfazRec IS RECORD(
        codigoConsultora      mae_clien.cod_clie%TYPE,
        codigoPeriodo         VARCHAR2(6),
        codigoTerritorio      VARCHAR2(100),
        fechaFactura          VARCHAR2(10),
        origenFactura         VARCHAR2(1),
        tipoWeb               VARCHAR2(1),
        montoFactura          ped_solic_cabec.VAL_TOTA_PAGA_LOCA%TYPE,
        montoFacturaImpto     ped_solic_cabec.VAL_TOTA_PAGA_LOCA%TYPE
    );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnIdPais            NUMBER;
   lnIdPeriodo         NUMBER;

   BEGIN
        lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        lnIdPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);
        OPEN c_interfaz(lnIdPais, lnIdPeriodo);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).codigoPeriodo ||';'||
                              interfazRecord(x).codigoTerritorio ||';'||
                              interfazRecord(x).fechaFactura ||';'||
                              interfazRecord(x).origenFactura ||';'||
                              interfazRecord(x).tipoWeb ||';'||
                              interfazRecord(x).montoFactura ||';'||
                              interfazRecord(x).montoFacturaImpto ;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_CONSU_FACTU: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_CONSU_FACTU;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Recomendaciones(HIRCRECO)
Fecha Creacion    : 27/03/2014
Autor             : Carlos Bazalar
***************************************************************************/
procedure INT_PR_LET_ENVIO_RECOM( pscodigopais varchar2,
                                  psCodigoSistema VARCHAR2,
                                  pscodigointerfaz varchar2,
                                  pscodigoperiodo varchar2,
                                  psNombreArchivo VARCHAR2) IS

   CURSOR c_interfaz (vnIdPeriodo NUMBER) IS
      with temp as
      (
      select (
                select peri.cod_peri
                  from cra_perio perd,
                       seg_perio_corpo peri
                 where perd.peri_oid_peri = peri.oid_peri
                   and mchs.perd_oid_peri = perd.oid_peri

               ) cprcampa,
               gen_fn_calcu_perio((
                                    select peri.cod_peri
                                      from cra_perio perd,
                                           seg_perio_corpo peri
                                     where perd.peri_oid_peri = peri.oid_peri
                                       and mchs.perd_oid_peri = perd.oid_peri
                                     ), 1 ) cprcmeva,
               clie_b.cod_clie cprcodeb,
               clie.cod_clie cprcodeb1
    FROM mae_clien_vincu clvi,
         mae_tipo_vincu tivc,
         mae_clien clie,
         mae_clien clie_b,
         mae_clien_datos_adici clda,
         mae_clien_histo_estat mchs
    WHERE clvi.tivc_oid_tipo_vinc = tivc.oid_tipo_vinc
      AND clvi.clie_oid_clie_vndo = clie.oid_clie
      AND clvi.clie_oid_clie_vndo = clda.clie_oid_clie
      AND clvi.clie_oid_clie_vnte = clie_b.oid_clie
      and tivc.cod_tipo_vinc = '03'
      and clvi.fec_hast is not null
      and mchs.clie_oid_clie = clie.oid_clie
      and mchs.esta_oid_esta_clie in (2,8)
      and mchs.perd_oid_peri = ( select max(perd_oid_peri) from mae_clien_histo_estat
                                  where clie_oid_clie =  mchs.clie_oid_clie
                                    and esta_oid_esta_clie in (2,8) )
      )
      select *
        from temp
        where cprcampa = psCodigoPeriodo;

   TYPE interfazRec IS RECORD(
        campannaRegistro    BAS_CTRL_FACT.COD_PERI%TYPE,
        campannaEvaluacion  BAS_CTRL_FACT.COD_PERI%TYPE,
        codigoRecomendante  mae_clien.cod_clie%TYPE,
        codigoRecomendada   mae_clien.cod_clie%TYPE
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnidperiodo         cra_perio.oid_peri%type;

   BEGIN

       lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
       OPEN c_interfaz(lnidperiodo);
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).campannaRegistro ||';'||
                              interfazRecord(x).campannaEvaluacion ||';'||
                              interfazRecord(x).codigoRecomendante ||';'||
                              interfazRecord(x).codigoRecomendada ;
                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;
        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_RECOM: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_RECOM;


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Lideres (LIDLIDER)
  Fecha Creacion    : 11/03/2014
  Autor             : Carlos Bazalar
 ************************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_LIDER (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
is
   cursor c_interfaz(vnidpais number, vnidperiodo number , vnideperiodoant number) is
    select gere.gere codigolider,
           gere.cod_regi || gere.cod_zona || gere.cod_secc codigoSeccion,
           peri.cod_peri codigoPeriodoIngreso,
           case when gere.perd_oid_peri_hast is null then null else peri2.cod_peri end codigoPeriodoBaja
      from zon_histo_geren gere,
           cra_perio perd,
           seg_perio_corpo peri,
           cra_perio perd2,
           seg_perio_corpo peri2
     where 1 = 1
       and gere.perd_oid_peri_desd = perd.oid_peri
       and nvl(gere.perd_oid_peri_hast,vnidperiodo) = perd2.oid_peri
       and perd.peri_oid_peri = peri.oid_peri
       and perd2.peri_oid_peri = peri2.oid_peri
       --
       and pscodigoperiodo between peri.cod_peri and peri2.cod_peri
       and gere.cod_secc is not null
    union
    select gere_a.gere codigolider,
           gere_a.cod_regi || gere_a.cod_zona || gere_a.cod_secc codigoSeccion,
           peri_a.cod_peri codigoPeriodoIngreso,
           case when gere_a.perd_oid_peri_hast is null then null else peri2_a.cod_peri end codigoPeriodoBaja
      from zon_histo_geren gere_a,
           cra_perio perd_a,
           seg_perio_corpo peri_a,
           cra_perio perd2_a,
           seg_perio_corpo peri2_a
     where 1 = 1
       and gere_a.perd_oid_peri_desd = perd_a.oid_peri
       and gere_a.perd_oid_peri_hast  = perd2_a.oid_peri
       and perd_a.peri_oid_peri = peri_a.oid_peri
       and perd2_a.peri_oid_peri = peri2_a.oid_peri
       and perd2_a.oid_peri = vnideperiodoant
       and gere_a.cod_secc is not null;


type interfazRecTab is table of c_interfaz%rowtype index by binary_integer;
interfazRecord interfazRecTab;

lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
lbAbrirUtlFile      BOOLEAN := TRUE;
v_handle            UTL_FILE.FILE_TYPE;
lsLinea             VARCHAR2(1000);
lsNombreArchivo     VARCHAR2(50);
lnidpais            number;
lnidPeriodo         cra_perio.oid_peri%type;
lnIdMarca           NUMBER;
lnIdCanal           NUMBER;
lsCodPeriAnterior   VARCHAR2(6);
lnIdPeriAnterior    NUMBER;


begin
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais( pscodigopais );
    lnidPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2( psCodigoPeriodo );
    lnIdMarca   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnIdCanal   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

    lsCodPeriAnterior  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo, lnidPais, lnIdMarca, lnIdCanal, -1);
    lnIdPeriAnterior   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnterior, lnIdMarca, lnIdCanal);

    OPEN c_interfaz( lnidPais, lnidPeriodo, lnIdPeriAnterior );
    LOOP
        FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

        IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF interfazRecord.COUNT > 0 THEN
           FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
               lsLinea := interfazRecord(x).codigoLider ||';'||
                          interfazRecord(x).codigoSeccion ||';'||
                          interfazRecord(x).codigoPeriodoIngreso ||';'||
                          interfazRecord(x).codigoPeriodoBaja ;

               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
           END LOOP;
        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
      UTL_FILE.FCLOSE(V_HANDLE);
      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_LIDER: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_LIDER;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Cabecera
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_CAB(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

   CURSOR c_interfaz_novedades IS
    SELECT X.COD_LIDE,TO_CHAR(sysdate , 'YYMMDD'),X.NUM_SECU FROM LEC_LIDER_PAGO_COMIS X
   INNER JOIN LEC_TARJE_LIDER A
   ON X.COD_LIDE=A.COD_LIDE
   INNER JOIN LEC_TARJE_PAGOS B
   ON A.LTPG_COD_TARJ=B.COD_TARJ
   INNER JOIN LEC_ESTAD_TARJE C
   ON B.LEST_COD_ESTA=C.COD_ESTA
   INNER JOIN MAE_CLIEN D
   ON A.COD_LIDE=D.COD_CLIE
   WHERE X.CAM_PROC=psCodigoPeriodo 
   AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
   AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='1';


   CURSOR c_interfaz_reenvio IS
    SELECT X.COD_LIDE,TO_CHAR(X.FEC_CONT , 'YYMMDD'),X.NUM_SECU FROM LEC_LIDER_PAGO_COMIS X
    INNER JOIN LEC_TARJE_LIDER A
    ON X.COD_LIDE=A.COD_LIDE
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN LEC_ESTAD_TARJE C
    ON B.LEST_COD_ESTA=C.COD_ESTA
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    WHERE TRUNC(X.FEC_CONT)=TO_DATE(psFechaReenvio,'DD/MM/YYYY') AND X.CAM_PROC=psCodigoPeriodo 
    AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
    AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='2';




   TYPE interfazRec IS RECORD(
        numeroCliente  LEC_LIDER_PAGO_COMIS.COD_LIDE%TYPE,
        fechaPago     VARCHAR2(10),
        numSecu       LEC_LIDER_PAGO_COMIS.NUM_SECU%TYPE
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE;
   lsNumCliente         VARCHAR2(12);
   lsNomEmpre         VARCHAR2(36);

   BEGIN
     BEGIN
      select SUBSTR( MAX(NUM_LOTE),9,4)
      into lsNumeroLote
      from BAS_HISTO_LOTES where inte_cod_intE =psCodigoInterfaz;
      exception when no_data_found then
          lsNumeroLote:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsNumCliente
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vNumCliente'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsNumCliente:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsNomEmpre
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vNomEmpre'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsNomEmpre:='';
     END;

    IF psTipoEnvio='N' THEN

       OPEN c_interfaz_novedades;
       LOOP
            FETCH c_interfaz_novedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea :=
                                                 1 ||';'||
                                      lsNumCliente ||';'||
                       interfazRecord(x).fechaPago ||';'||
                                    lsNumeroLote ||';'||
                                      lsNomEmpre  ||';'||
                                 'Pago Apoyo Let'  ||';'||
                                               15  ||';'||
                                              'D'  ||';'||
                                               '01'  ;




               END LOOP;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_novedades;
     END IF;

     IF psTipoEnvio='R' THEN

       OPEN c_interfaz_reenvio;
       LOOP
            FETCH c_interfaz_reenvio BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea :=    1 ||';'||
                                      lsNumCliente ||';'||
                       interfazRecord(x).fechaPago ||';'||
                                    lsNumeroLote ||';'||
                                      lsNomEmpre  ||';'||
                                 'Pago Apoyo Let'  ||';'||
                                               15  ||';'||
                                              'D'  ||';'||
                                               '01'  ;
                       /*
                       UPDATE LEC_LIDER_PAGO_COMIS
                       SET
                       FEC_CONT=SYSDATE,
                       IND_PROC_PAGO='2',
                       USU_MODI=pscodigousuario,
                       FEC_MODI=SYSDATE
                       WHERE NUM_SECU=interfazRecord(x).numSecu;
                       */


               END LOOP;
               	 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END IF;
            EXIT WHEN c_interfaz_reenvio%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_reenvio;
     END IF;


        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_PAGOS_CAB: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_PAGOS_CAB;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Cabecera 1
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
Modificado        : Carlos Mori ( 19.03.2015) - Se modifica toda la lógica.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_CAB1(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

CURSOR c_interfaz IS
SELECT COUNT(DISTINCT lcom.cod_lide) numeroClientes,
       SUM(lcom.val_mont_brut) valMontBrut
  FROM LEC_LIDER_PAGO_COMIS lcom,
       LEC_TARJE_LIDER ltli,
       LEC_TARJE_PAGOS ltpg,
       LEC_ESTAD_TARJE lest
 WHERE 1=1
   AND lcom.COD_LIDE = ltli.COD_LIDE
   AND ltli.LTPG_COD_TARJ = ltpg.COD_TARJ
   AND ltpg.LEST_COD_ESTA = lest.COD_ESTA
   AND lcom.CAM_PROC = psCodigoPeriodo
   AND lest.COD_ESTA = '03'
   AND ( lcom.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL )
   AND (( psTipoEnvio = 'N' AND lcom.ind_proc_pago = '1' ) OR
        ( psTipoEnvio = 'R' AND lcom.ind_proc_pago = '2' AND
          TRUNC(lcom.fec_cont) = TO_DATE(psFechaReenvio, 'DD/MM/YYYY') ));

TYPE interfazrectab IS TABLE OF c_interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazrecord interfazrectab;

lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
lbAbrirUtlFile      BOOLEAN := TRUE;
v_handle            UTL_FILE.FILE_TYPE;
lsLinea             VARCHAR2(1000);
lsNombreArchivo     VARCHAR2(50);
lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE;
lsMoneda            VARCHAR2(3);
lsNumeroCuenta      VARCHAR2(20);

-- Obtiene el codigo de la moneda del país

   BEGIN
     BEGIN
        SELECT VAL_PAIN
          INTO lsMoneda
          FROM BAS_PARAM_INTER
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND SIST_COD_SIST = psCodigoSistema
           AND UPPER(NOM_PAIN) = 'VMONEDA'
           AND INTE_COD_INTE = psCodigoInterfaz;
      EXCEPTION WHEN NO_DATA_FOUND THEN
          lsMoneda:='';
     END;

-- Obtiene el número de cuenta asociada a la dispersión

     BEGIN
      SELECT VAL_PAIN
        INTO lsNumeroCuenta
        FROM BAS_PARAM_INTER
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND SIST_COD_SIST = psCodigoSistema
         AND UPPER(NOM_PAIN) = 'VNROCUENTA'
         AND INTE_COD_INTE = psCodigoInterfaz;
      EXCEPTION WHEN NO_DATA_FOUND THEN
          lsNumeroCuenta:=0;
     END;

       OPEN c_interfaz;
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER( psCodigoPais,
                                                       psCodigosistema,
                                                       psCodigoInterfaz,
                                                       psNombreArchivo,
                                                       lsDirTempo,
                                                       lsNombreArchivo,
                                                       V_HANDLE );
               lbAbrirUtlFile := FALSE;
            END IF;
            
            IF interfazRecord.COUNT > 0 THEN

               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := '2' ||';'||
                              '1' ||';'||
                              lsMoneda ||';'||
                              LPAD(TO_NUMBER(REPLACE(TO_CHAR(interfazRecord(x).valMontBrut,'999999999999999.99'), '.', ''), '99999999999999999'),18,'0') ||';'||
                              '01' ||';'||
                              lsNumeroCuenta ||';'||
                              LPAD(interfazRecord(x).numeroClientes,6,'0')  ;
               END LOOP;
               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
           UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_PAGOS_CAB1: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_PAGOS_CAB1;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de pagos Detalle
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
Modificado        : CS FFVV (11.03.2015 )
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_DETA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

   CURSOR c_interfaz_novedades IS
     SELECT
          X.COD_LIDE,
     ----     TO_CHAR(X.FEC_PROC_PAGO , 'YYYY/MM/DD'),
          sum(X.VAL_MONT_BRUT),
          B.NUM_TARJ,
      /*(CASE WHEN (D.VAL_APE1 IS NULL ) THEN
        ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim( D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    ELSE
        (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1))||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    END )*/
      CASE
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ' '
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ','
        WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
      END
      ||
      CASE
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
        WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
             || '/'
      END
      Nombres
    FROM LEC_LIDER_PAGO_COMIS X
   INNER JOIN LEC_TARJE_LIDER A
   ON X.COD_LIDE=A.COD_LIDE
   INNER JOIN LEC_TARJE_PAGOS B
   ON A.LTPG_COD_TARJ=B.COD_TARJ
   INNER JOIN LEC_ESTAD_TARJE C
   ON B.LEST_COD_ESTA=C.COD_ESTA
   INNER JOIN MAE_CLIEN D
   ON A.COD_LIDE=D.COD_CLIE
  WHERE X.CAM_PROC=psCodigoPeriodo 
  AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
  AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='1'
  group by   X.COD_LIDE,
          --TO_CHAR(X.FEC_PROC_PAGO , 'YYYY/MM/DD'),
          B.NUM_TARJ,  /*(CASE WHEN (D.VAL_APE1 IS NULL ) THEN
        ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim( D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    ELSE
        (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1))||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    END )*/
      CASE
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ' '
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ','
        WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
      END
      ||
      CASE
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
        WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
             || '/'
      END
       ;


   CURSOR c_interfaz_reenvio IS
     SELECT
          X.COD_LIDE,
      ---    TO_CHAR(X.FEC_PROC_PAGO , 'YYYY/MM/DD'),
          sum(X.VAL_MONT_BRUT),
          B.NUM_TARJ,
          CASE
            WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
                 || ' '
                 || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
                 || ','
            WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
                 || ','
            WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
                 || ','
          END
          ||
          CASE
            WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
                 || '/'
                 || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
            WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
                 || '/'
            WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
                 INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
                 || '/'
          END
          Nombres

        /*(CASE WHEN (D.VAL_APE1 IS NULL ) THEN
        ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim( D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    ELSE
        (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1))||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    END )*/

    FROM LEC_LIDER_PAGO_COMIS X
    INNER JOIN LEC_TARJE_LIDER A
    ON X.COD_LIDE=A.COD_LIDE
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN LEC_ESTAD_TARJE C
    ON B.LEST_COD_ESTA=C.COD_ESTA
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    WHERE TRUNC(X.FEC_CONT)=TO_DATE(psFechaReenvio,'DD/MM/YYYY') 
    AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
    AND X.CAM_PROC=psCodigoPeriodo AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='2'
     group by   X.COD_LIDE,
         -- TO_CHAR(X.FEC_PROC_PAGO , 'YYYY/MM/DD'),
          B.NUM_TARJ,  /*(CASE WHEN (D.VAL_APE1 IS NULL ) THEN
        ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      ( INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) ||'/' )
    WHEN (D.VAL_NOM2 IS NULL AND D.VAL_APE1 IS NULL) THEN
      (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim( D.VAL_NOM1)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    ELSE
        (INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM1))||' '|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_NOM2)) ||','|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE1)) ||'/'|| INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(trim(D.VAL_APE2)) )
    END )*/
    CASE
      WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
           || ' '
           || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
           || ','
      WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
           || ','
      WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
           || ','
    END
    ||
    CASE
      WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
           || '/'
           || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
      WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
           || '/'
      WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
           || '/'
    END
    ;





   TYPE interfazRec IS RECORD(
        numeroCliente  LEC_LIDER_PAGO_COMIS.COD_LIDE%TYPE,
     ---   fechaPago     VARCHAR2(10),
        valMontBrut     LEC_LIDER_PAGO_COMIS.Val_Mont_Brut%TYPE,
        numeroTarjeta   LEC_TARJE_PAGOS.NUM_TARJ%TYPE,
        nombres     VARCHAR2(100)
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE;
   lsMetPago           NUMBER(3);
   lsTipPago           VARCHAR2(2);
   lsMoneda            VARCHAR2(3);
   lsTipCuenta         NUMBER(2);
   lsClaveBanco        NUMBER(4);
   montoBrutoFormat    varchar2(18);
   lsResultado         NUMBER;

   BEGIN

     BEGIN
      SELECT  VAL_PAIN
      into lsMetPago
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vMetPago'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsMetPago:=0;
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsTipPago
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vTipPago'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsTipPago:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsMoneda
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vMoneda'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsMoneda:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsTipCuenta
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vTipCuenta'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsTipCuenta:=0;
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsClaveBanco
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vClaveBanco'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsClaveBanco:=0;
     END;

    IF psTipoEnvio='N' THEN

       OPEN c_interfaz_novedades;
       LOOP
            FETCH c_interfaz_novedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP


                       montoBrutoFormat:= LPAD(TO_NUMBER(replace (to_char(interfazRecord(x).valMontBrut,'999999999999999.99'), '.', ''), '99999999999999999'),18,'0');


                   lsLinea :=
                                                  3 ||';'||
                                            0 ||';'||
                                         lsMetPago ||';'||
                                         lsTipPago ||';'||
                                          lsMoneda ||';'||
                    montoBrutoFormat  ||';'||
                                       lsTipCuenta ||';'||
                                      interfazRecord(x).numeroTarjeta ||';'||
                                   'Pago Apoyo Let' ||';'||
                          interfazRecord(x).nombres ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                      lsClaveBanco ||';'||
                                               00 ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                    null  ;

                    CCC_PKG_PROCE.CCC_PR_GENER_CARGO_ABONO(775,interfazRecord(x).numeroCliente,interfazRecord(x).valMontBrut,pscodigousuario, lsResultado);

                   /* UPDATE LEC_LIDER_PAGO_COMIS
                    SET IND_PROC_PAGO='2',
                    USU_MODI=pscodigousuario,
                    FEC_MODI=SYSDATE,
                    FEC_CONT=SYSDATE
                    WHERE CAM_PROC=psCodigoPeriodo AND COD_REGI = psCodigoRegion AND IND_PROC_PAGO='1'
                    AND COD_LIDE=interfazRecord(x).numeroCliente;*/

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;

            END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_novedades;
     END IF;

     IF psTipoEnvio='R' THEN

       OPEN c_interfaz_reenvio;
       LOOP
            FETCH c_interfaz_reenvio BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP



                       montoBrutoFormat:= LPAD(TO_NUMBER(replace (to_char(interfazRecord(x).valMontBrut,'999999999999999.99'), '.', ''), '99999999999999999'),18,'0');


                   lsLinea :=                    3 ||';'||
                                            0 ||';'||
                                         lsMetPago ||';'||
                                         lsTipPago ||';'||
                                          lsMoneda ||';'||
                    montoBrutoFormat  ||';'||
                                       lsTipCuenta ||';'||
                                      interfazRecord(x).numeroTarjeta ||';'||
                                   'Pago Apoyo Let' ||';'||
                          interfazRecord(x).nombres ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                      lsClaveBanco ||';'||
                                               00 ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                 null ||';'||
                                                    null  ;
                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz_reenvio%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_reenvio;
     END IF;


        IF NOT lbAbrirUtlFile THEN
           UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_PAGOS_DETA: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_PAGOS_DETA;
/***************************************************************************
Descripcion       : Genera Interfase de Envio de tarjetas asociadas Cabecera
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_TARJ_ASOC_CAB(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

   CURSOR c_interfaz_novedades IS
    SELECT
        INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(( D.VAL_NOM1||','||D.VAL_APE1||'/'||D.VAL_APE2)) AS NOMBRES,
    TO_CHAR(E.FEC_NACI , 'YYYY/MM/DD') AS FEC_NACI,
    F.VAL_NOMB_VIA,
    F.VAL_COD_POST,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL((SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
        WHERE
            G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
            AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
            AND G.ORDE_3=SUBSTR(F.COD_UNID_GEOG,13,6)
            AND G.ORDE_4=SUBSTR(F.COD_UNID_GEOG,19,6)))
     AS DESCRIPCION_GEOG,
     INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL((SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
            WHERE
                G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                AND G.ORDE_3 IS NULL)) AS DESC_POB
    FROM LEC_TARJE_LIDER A
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN LEC_ESTAD_TARJE C
    ON C.COD_ESTA=B.LEST_COD_ESTA
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    INNER JOIN MAE_CLIEN_DATOS_ADICI E
    ON E.CLIE_OID_CLIE=D.OID_CLIE
    INNER JOIN MAE_CLIEN_DIREC F
    ON F.CLIE_OID_CLIE=E.CLIE_OID_CLIE
     WHERE B.LEST_COD_ESTA='02'  and f.ind_dire_ppal = 1 and f.ind_elim = 0;


   CURSOR c_interfaz_reenvio IS
    SELECT
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(( D.VAL_NOM1||','||D.VAL_APE1||'/'||D.VAL_APE2)) AS NOMBRES,
    TO_CHAR(E.FEC_NACI , 'YYYY/MM/DD') AS FEC_NACI,
    F.VAL_NOMB_VIA,
    F.VAL_COD_POST,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL((SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
        WHERE
            G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
            AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
            AND G.ORDE_3=SUBSTR(F.COD_UNID_GEOG,13,6)
            AND G.ORDE_4=SUBSTR(F.COD_UNID_GEOG,19,6)))
     AS DESCRIPCION_GEOG,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL((SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
            WHERE
                G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                AND G.ORDE_3 IS NULL)) AS DESC_POB
    FROM LEC_TARJE_LIDER A
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    INNER JOIN MAE_CLIEN_DATOS_ADICI E
    ON E.CLIE_OID_CLIE=D.OID_CLIE
    INNER JOIN MAE_CLIEN_DIREC F
    ON F.CLIE_OID_CLIE=E.CLIE_OID_CLIE
    WHERE TRUNC(A.FEC_ENVI)=TO_DATE(psFechaReenvio,'DD/MM/YYYY') and f.ind_dire_ppal = 1 and f.ind_elim = 0;




   TYPE interfazRec IS RECORD(
        nombres     VARCHAR2(100),
        fechaNacimiento     VARCHAR2(10),
        direccion     MAE_CLIEN_DIREC.VAL_NOMB_VIA%TYPE,
        codigoPostal  MAE_CLIEN_DIREC.VAL_COD_POST%TYPE,
        desColo     VARCHAR2(100),
        desPob     VARCHAR2(100)

   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE;
   lsNumCliente         VARCHAR2(12);


   BEGIN

     BEGIN
      select SUBSTR( MAX(NUM_LOTE),9,4)
      into lsNumeroLote
      from BAS_HISTO_LOTES where inte_cod_intE =psCodigoInterfaz;
      exception when no_data_found then
          lsNumeroLote:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsNumCliente
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vNumCliente'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsNumCliente:='';
     END;

    IF psTipoEnvio='N' THEN

       OPEN c_interfaz_novedades;
       LOOP
            FETCH c_interfaz_novedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP


               lslinea :=
                                 1 || ';' ||
                               lsNumCliente || ';' ||
                               TO_CHAR(SYSDATE , 'YYYYMMDD') || ';' ||
                               lsNumeroLote || ';' ||
                             LPAD(interfazRecord.COUNT,6,0)  || ';' ||
                               null;

               END LOOP;

               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_novedades;
     END IF;

     IF psTipoEnvio='R' THEN

       OPEN c_interfaz_reenvio;
       LOOP
            FETCH c_interfaz_reenvio BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lslinea :=
                                 1 || ';' ||
                               lsNumCliente || ';' ||
                               TO_CHAR(SYSDATE , 'YYYYMMDD') || ';' ||
                               lsNumeroLote || ';' ||
                            LPAD(interfazRecord.COUNT,6,0) || ';' ||
                               null;

               END LOOP;

               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END IF;
            EXIT WHEN c_interfaz_reenvio%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_reenvio;
     END IF;


        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_TARJ_ASOC_CAB: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_TARJ_ASOC_CAB;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de tarjetas asociadas detalle
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
Modificado        : CS FFVV (11.03.2015 )
***************************************************************************/
PROCEDURE INT_PR_LET_ENV_TARJ_ASOC_DETA(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

CURSOR c_interfaz_novedades IS
    SELECT
      CASE
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ' '
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ','
        WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
      END
      ||
      CASE
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
        WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
             || '/'
      END
      Nombres,
    TO_CHAR(E.FEC_NACI , 'YYYYMMDD') AS FEC_NACI,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(RPAD(F.VAL_NOMB_VIA ,36,' ')) AS VAL_OBSE,
    LPAD(F.VAL_COD_POST,6,'0') AS VAL_COD_POST,
    CASE
      WHEN LENGTH(F.COD_UNID_GEOG) = 24 THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(( SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
                                                                   WHERE G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                                                                     AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                                                                     AND G.ORDE_3=SUBSTR(F.COD_UNID_GEOG,13,6)
                                                                     AND G.ORDE_4=SUBSTR(F.COD_UNID_GEOG,19,6)))
      ELSE
          INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(f.val_obse)
    END DESCRIPCION_GEOG,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL( (SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
                                                WHERE
                                                    G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                                                    AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                                                    AND G.ORDE_3 IS NULL)) AS DESC_POB,
    (
     SELECT LPAD(vepo.cod_homo,2,'0') val_codi_homo
       FROM zon_valor_estru_geopo vepo
      WHERE vepo.orde_1 = SUBSTR(f.cod_unid_geog,1,6)
        AND vepo.orde_2 IS NULL
        AND vepo.orde_3 IS NULL
        AND vepo.orde_4 IS NULL
    ) AS ESTADO,
        --SUBSTR(F.COD_UNID_GEOG,5,2) AS ESTADO,
        B.NUM_TARJ, A.LTPG_COD_TARJ, A.COD_LIDE
    FROM LEC_TARJE_LIDER A
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN LEC_ESTAD_TARJE C
    ON C.COD_ESTA=B.LEST_COD_ESTA
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    INNER JOIN MAE_CLIEN_DATOS_ADICI E
    ON E.CLIE_OID_CLIE=D.OID_CLIE
    INNER JOIN MAE_CLIEN_DIREC F
    ON F.CLIE_OID_CLIE=E.CLIE_OID_CLIE
    WHERE B.LEST_COD_ESTA='02'
    AND F.IND_DIRE_PPAL = 1 AND F.IND_ELIM=0;


CURSOR c_interfaz_reenvio IS
    SELECT
      CASE
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ' '
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
        WHEN d.val_nom1 IS NOT NULL AND d.val_nom2 IS NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom1))
             || ','
        WHEN d.val_nom1 IS NULL AND d.val_nom2 IS NOT NULL THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_nom2))
             || ','
      END
      ||
      CASE
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
             || INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
        WHEN ( d.val_ape1 IS NOT NULL AND d.val_ape1 != 'X' ) AND ( d.val_ape2 IS NULL OR d.val_ape2 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape1))
             || '/'
        WHEN ( d.val_ape2 IS NOT NULL AND d.val_ape2 != 'X' ) AND ( d.val_ape1 IS NULL OR d.val_ape1 = 'X' )  THEN
             INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(TRIM(d.val_ape2))
             || '/'
      END
      Nombres,
    TO_CHAR(E.FEC_NACI , 'YYYYMMDD') AS FEC_NACI,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(RPAD(F.VAL_NOMB_VIA,36,' ')) AS VAL_OBSE,
    LPAD(F.VAL_COD_POST,6,'0') AS VAL_COD_POST,
    CASE
      WHEN LENGTH(F.COD_UNID_GEOG) = 24 THEN
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(( SELECT G.DES_GEOG
                                                        FROM ZON_VALOR_ESTRU_GEOPO G
                                                       WHERE G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                                                         AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                                                         AND G.ORDE_3=SUBSTR(F.COD_UNID_GEOG,13,6)
                                                         AND G.ORDE_4=SUBSTR(F.COD_UNID_GEOG,19,6)))
      ELSE
           INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(f.val_obse)
    END DESC_COLO,
    INT_PKG_LET.LET_FN_REEMPLAZA_CARAC_INVAL(( SELECT G.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO G
                                                WHERE G.ORDE_1=SUBSTR(F.COD_UNID_GEOG,1,6)
                                                  AND G.ORDE_2=SUBSTR(F.COD_UNID_GEOG,7,6)
                                                  AND G.ORDE_3 IS NULL)) AS DESC_POB,
    (
     SELECT LPAD(vepo.cod_homo,2,'0') val_codi_homo
       FROM zon_valor_estru_geopo vepo
      WHERE vepo.orde_1 = SUBSTR(f.cod_unid_geog,1,6)
        AND vepo.orde_2 IS NULL
        AND vepo.orde_3 IS NULL
        AND vepo.orde_4 IS NULL
    ) AS ESTADO,
            --SUBSTR(F.COD_UNID_GEOG,5,2) AS ESTADO,
            B.NUM_TARJ, A.LTPG_COD_TARJ, A.COD_LIDE
    FROM LEC_TARJE_LIDER A
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    INNER JOIN MAE_CLIEN_DATOS_ADICI E
    ON E.CLIE_OID_CLIE=D.OID_CLIE
    INNER JOIN MAE_CLIEN_DIREC F
    ON F.CLIE_OID_CLIE=E.CLIE_OID_CLIE
    WHERE TRUNC(A.FEC_ENVI)=TO_DATE(psFechaReenvio,'DD/MM/YYYY')
    AND F.IND_DIRE_PPAL = 1 AND F.IND_ELIM=0;

   TYPE interfazRec IS RECORD(
       nombres     VARCHAR2(100),
        fechaNacimiento     VARCHAR2(10),
        direccion     VARCHAR2(60),
        codigoPostal  VARCHAR2(6),
        desColo     VARCHAR2(100),
        desPob     VARCHAR2(100),
        estado     VARCHAR2(2),
        numeroTarjeta LEC_TARJE_PAGOS.NUM_TARJ%TYPE,
        codigoTarjeta LEC_TARJE_LIDER.LTPG_COD_TARJ%TYPE,
        codigoLider   LEC_TARJE_LIDER.COD_LIDE%TYPE
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE;
   lsTipProd           NUMBER(1);
   lsTipEntre          NUMBER(1);
   lsUnidadTrabajo     VARCHAR2(16);
   lsTipPerso          VARCHAR2(2);
   lsFormaPago         NUMBER(1);
   lsAsigPago          NUMBER(1);
   lsNacional          VARCHAR2(4);

   BEGIN
     BEGIN
      SELECT  VAL_PAIN
      into lsTipProd
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vTipProd'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsTipProd:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsTipEntre
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vTipEntre'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsTipEntre:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsUnidadTrabajo
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vUnidadTrabajo'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsUnidadTrabajo:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsTipPerso
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vTipPerso'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsTipPerso:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsFormaPago
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'nFormPago'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsFormaPago:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsAsigPago
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'nAsigPago'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsAsigPago:='';
     END;

     BEGIN
      SELECT  VAL_PAIN
      into lsNacional
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vNacional'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsNacional:='';
     END;

    IF psTipoEnvio='N' THEN

       OPEN c_interfaz_novedades;
       LOOP
            FETCH c_interfaz_novedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea :=
                                                 2 ||';'||
                                    lsTipProd ||';'||
                                    lsTipEntre ||';'||
                                    lsUnidadTrabajo ||';'||
                                    lsTipPerso ||';'||
                                    interfazRecord(x).nombres ||';'||
                                     interfazRecord(x).fechaNacimiento ||';'||
                  interfazRecord(x).direccion ||';'||
                  interfazRecord(x).desColo ||';'||
                   interfazRecord(x).codigoPostal ||';'||
                   interfazRecord(x).desPob ||';'||
                   interfazRecord(x).estado ||';'||
                    interfazRecord(x).numeroTarjeta ||';'||
                                    lsFormaPago ||';'||
                                      lsAsigPago  ||';'||
                                 lsNacional  ||';'||
                                   null  ;



                   UPDATE LEC_TARJE_LIDER
                   SET
                   FEC_ENVI=SYSDATE,
                   CAM_ENVI=(SELECT cod_peri FROM BAS_CTRL_FACT WHERE STA_CAMP='0' AND IND_CAMP_ACT = 1),
                   USU_ENVI=pscodigousuario,
                   USU_MODI=pscodigousuario,
                   FEC_MODI=SYSDATE
                   WHERE COD_LIDE=interfazRecord(x).codigoLider AND LTPG_COD_TARJ=interfazRecord(x).codigoTarjeta;

                   UPDATE LEC_TARJE_PAGOS
                   SET
                   LEST_COD_ESTA='03',
                   USU_MODI=pscodigousuario,
                   FEC_MODI=SYSDATE
                   WHERE COD_TARJ=interfazRecord(x).codigoTarjeta;

                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_novedades;
     END IF;

     IF psTipoEnvio='R' THEN

       OPEN c_interfaz_reenvio;
       LOOP
            FETCH c_interfaz_reenvio BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea :=              2 ||';'||
                                    lsTipProd ||';'||
                                    lsTipEntre ||';'||
                                    lsUnidadTrabajo ||';'||
                                    lsTipPerso ||';'||
                                    interfazRecord(x).nombres ||';'||
                                     interfazRecord(x).fechaNacimiento ||';'||
                  interfazRecord(x).direccion ||';'||
                  interfazRecord(x).desColo ||';'||
                   interfazRecord(x).codigoPostal ||';'||
                   interfazRecord(x).desPob ||';'||
                   interfazRecord(x).estado ||';'||
                    interfazRecord(x).numeroTarjeta ||';'||
                                    lsFormaPago ||';'||
                                      lsAsigPago  ||';'||
                                 lsNacional  ||';'||
                                   null  ;




                   UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
               END LOOP;
            END IF;
            EXIT WHEN c_interfaz_reenvio%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_reenvio;
     END IF;


        IF NOT lbAbrirUtlFile THEN
         	UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENV_TARJ_ASOC_DETA: '||ls_sqlerrm);
END INT_PR_LET_ENV_TARJ_ASOC_DETA;



/***************************************************************************
Descripcion       : Genera Interfase de Envio de tarjetas asociadas detalle1
Fecha Creacion    : 24/02/2015
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_LET_ENVIO_PAGOS_DETA1(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psTipoEnvio VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psFechaReenvio VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

   CURSOR c_interfaz_novedades IS
     SELECT
     
        sum(x.val_mont_brut),X.COD_LIDE

    FROM LEC_LIDER_PAGO_COMIS X
   INNER JOIN LEC_TARJE_LIDER A
   ON X.COD_LIDE=A.COD_LIDE
   INNER JOIN LEC_TARJE_PAGOS B
   ON A.LTPG_COD_TARJ=B.COD_TARJ
   INNER JOIN LEC_ESTAD_TARJE C
   ON B.LEST_COD_ESTA=C.COD_ESTA
   INNER JOIN MAE_CLIEN D
   ON A.COD_LIDE=D.COD_CLIE
  WHERE X.CAM_PROC=psCodigoPeriodo 
  AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
  AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='1'
  group by   X.COD_LIDE;


   CURSOR c_interfaz_reenvio IS
     SELECT
     
         sum(x.val_mont_brut),X.COD_LIDE

    FROM LEC_LIDER_PAGO_COMIS X
    INNER JOIN LEC_TARJE_LIDER A
    ON X.COD_LIDE=A.COD_LIDE
    INNER JOIN LEC_TARJE_PAGOS B
    ON A.LTPG_COD_TARJ=B.COD_TARJ
    INNER JOIN LEC_ESTAD_TARJE C
    ON B.LEST_COD_ESTA=C.COD_ESTA
    INNER JOIN MAE_CLIEN D
    ON A.COD_LIDE=D.COD_CLIE
    WHERE TRUNC(X.FEC_CONT)=TO_DATE(psFechaReenvio,'DD/MM/YYYY') 
    AND (X.COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
    AND X.CAM_PROC=psCodigoPeriodo AND C.COD_ESTA='03' AND X.IND_PROC_PAGO='2'
     group by   X.COD_LIDE;





   TYPE interfazRec IS RECORD(
        valMontBrut     LEC_LIDER_PAGO_COMIS.Val_Mont_Brut%TYPE,
        numeroCliente  LEC_LIDER_PAGO_COMIS.COD_LIDE%TYPE  
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsNumeroLote        BAS_HISTO_LOTES.NUM_LOTE%TYPE; 
   lsMoneda            VARCHAR2(3);
   montoBrutoFormat    varchar2(18);
   sumaAbono            NUMBER(12,2);

   BEGIN

     sumaAbono:=0;

     BEGIN
      SELECT  VAL_PAIN
      into lsMoneda
      FROM BAS_PARAM_INTER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST = psCodigoSistema AND NOM_PAIN = 'vMoneda'
      AND INTE_COD_INTE = psCodigoInterfaz;
      exception when no_data_found then
          lsMoneda:='';
     END;

    

    

    IF psTipoEnvio='N' THEN

       OPEN c_interfaz_novedades;
       LOOP
            FETCH c_interfaz_novedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
              
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        sumaAbono :=   interfazRecord(x).valMontBrut +  sumaAbono;

               END LOOP;  
            
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP


                       montoBrutoFormat:= LPAD(TO_NUMBER(replace (to_char(sumaAbono,'999999999999999.99'), '.', ''), '99999999999999999'),18,'0');


                   lsLinea :=
                                                  4 ||';'||
                                          lsMoneda ||';'||
                       LPAD(interfazRecord.COUNT,6,'0') ||';'||
                    montoBrutoFormat  ||';'||
                                   '000001' ||';'||
                          montoBrutoFormat  ;

                   UPDATE LEC_LIDER_PAGO_COMIS
                    SET IND_PROC_PAGO = '2',
                    USU_MODI = pscodigousuario,
                    FEC_MODI = SYSDATE,
                    FEC_CONT = SYSDATE,
                    ltpg_num_tarj = (select tp.num_tarj from lec_tarje_lider tl, lec_tarje_pagos tp
                            where tl.ltpg_cod_tarj=tp.cod_tarj 
                            and cod_lide=interfazRecord(x).numeroCliente 
                            and cam_bloq is null 
                            and tp.LEST_COD_ESTA in ('02','03'))
                    WHERE CAM_PROC = psCodigoPeriodo 
                    AND (COD_REGI = psCodigoRegion OR psCodigoRegion IS NULL)
                    AND IND_PROC_PAGO = '1'
                    AND COD_LIDE = interfazRecord(x).numeroCliente;
                    
               END LOOP;
               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_novedades;
     END IF;

     IF psTipoEnvio='R' THEN

       OPEN c_interfaz_reenvio;
       LOOP
            FETCH c_interfaz_reenvio BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
              
             FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        sumaAbono :=   interfazRecord(x).valMontBrut +  sumaAbono;

               END LOOP;    
            
            
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP



                       montoBrutoFormat:= LPAD(TO_NUMBER(replace (to_char(sumaAbono,'999999999999999.99'), '.', ''), '99999999999999999'),18,'0');


                   lsLinea :=                
                             4 ||';'||
                                          lsMoneda ||';'||
                       LPAD(interfazRecord.COUNT,6,'0') ||';'||
                    montoBrutoFormat  ||';'||
                                   '000001' ||';'||
                          montoBrutoFormat  ;
                          
                   
               END LOOP;
               UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END IF;
            EXIT WHEN c_interfaz_reenvio%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz_reenvio;
     END IF;


        IF NOT lbAbrirUtlFile THEN
           UTL_FILE.FCLOSE(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
        END IF;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LET_ENVIO_PAGOS_DETA1: '||ls_sqlerrm);
END INT_PR_LET_ENVIO_PAGOS_DETA1;

/***************************************************************************
Descripcion       : Reemplaza campos invalidos
Fecha Creacion    : 09/12/2014
Autor             : Diego Torres L.
***************************************************************************/
FUNCTION LET_FN_REEMPLAZA_CARAC_INVAL(parametro in varchar2 )
 return varchar2
 as
     lsCadena    varchar2(4000);

 begin
               lsCadena := replace(parametro, '\\¥', '\\@');
               lsCadena := replace(lsCadena, '\\.', ' ');
               lsCadena := replace(lsCadena, '\\#', ' ');
               lsCadena := replace(lsCadena, '\\_', ' ');
               lsCadena := replace(lsCadena, '\\-', ' ');
               lsCadena := replace(lsCadena, '\\(', ' ');
               lsCadena := replace(lsCadena, '\\)', ' ');
               lsCadena := replace(lsCadena, '\\>', ' ');
               lsCadena := replace(lsCadena, '\\<', ' ');
               lsCadena := replace(lsCadena, '#', 'N');
               lsCadena := replace(lsCadena, '.', ' ');
               lsCadena := replace(lsCadena, ',', ' ');
               lsCadena := replace(lsCadena, '-', ' ');
               lsCadena := replace(lsCadena, '_', ' ');
               lsCadena := replace(lsCadena, '/', ' ');
               lsCadena := replace(lsCadena, '\', ' ');
               lsCadena := replace(lsCadena, '<', ' ');
               lsCadena := replace(lsCadena, '>', ' ');
               lsCadena := replace(lsCadena, '(', ' ');
               lsCadena := replace(lsCadena, ')', ' ');
               lsCadena := replace(lsCadena, 'ª', ' ');
               lsCadena := replace(lsCadena, '°', ' ');
               lsCadena := replace(lsCadena, 'S/N', ' ');
               lsCadena := replace(lsCadena, 's/n', ' ');
               lsCadena := replace(lsCadena, '¦', ' ');
               lsCadena := replace(lsCadena, 'Ñ', '@');
               lsCadena := replace(lsCadena, 'ñ', '@');

     return lsCadena;
 end;


END INT_PKG_LET;
/
