create or replace package INT_PKG_PORTA_LOCAL is

  -- Author  : JUANGUTIERREZ
  -- Created : 13/04/2015 04:03:10 p.m.
  -- Purpose : 
  
  /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(1500);
  W_FILAS      NUMBER:=5000;
  

/**************************************************************************************
Descripcion       : Genera Interfase de Envio de Consultoras (EBIDENT)
Fecha Creacion    : 13/04/2015
Autor             : Juan Gutiérrez
**************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CONSU      (psCodigoPais VARCHAR2,
                                       psCodigoSistema VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo VARCHAR2,
                                       psCodigoPeriodo VARCHAR2);
                                       
/***************************************************************************
Descripcion       : Genera Interfase de Envio de Recomendaciones(GARANTE)
Fecha Creacion    : 13/04/2015
Autor             : Juan Gutierrez
***************************************************************************/
procedure INT_PR_POL_ENVIO_RECOM( pscodigopais varchar2,
                                  psCodigoSistema VARCHAR2,
                                  pscodigointerfaz varchar2,
                                  psNombreArchivo varchar2,
                                  psCodigoPeriodo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas (STZONAS)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ZONA        (psCodigoPais VARCHAR2,
                                        psCodigoSistema VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                        psNombreArchivo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas Consultoras (STDZONA)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ZONA_CONSU (psCodigoPais VARCHAR2,
                                       psCodigoSistema VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo VARCHAR2,
                                       psCodigoPeriodo VARCHAR2);  

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Facturas Consultoras (EBESTAD)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CONSU_FACTU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaProceso VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Matriz Facturación (CMMTRIZ)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_MATRI_FACTU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio Productos fuera de caja (FUERACJA)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_PRODU_FUCJA (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);
                                   
/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Cargos Cuenta Corriente (CTACTE_CARGO)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CARGO_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);
                 
/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CTACTE_ABONO)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ABONO_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaProceso VARCHAR2);

                 
/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CTACTE_CNTINT)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_INTER_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);
                                   
/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CONTROL)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ARCHI_CONTR(psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2);                                   

END INT_PKG_PORTA_LOCAL;
/
create or replace package body INT_PKG_PORTA_LOCAL is


/***************************************************************************
Descripcion       : Genera Interfase de Envio de Consultoras (EBIDENT)
Fecha Creacion    : 13/04/2015
Autor             : Juan Gutiérrez
***************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CONSU(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      psCodigoPeriodo VARCHAR2)IS

   searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   replacestr VARCHAR2(100) := 'a        ';

   CURSOR c_interfaz(vnIdPais NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER, vsCodPeriAnterior VARCHAR2, vnIdPeriAnterior NUMBER,fechaActualizacionInterfaz DATE,
                     vnIdTipClaReco number, vnIdTipClaImpr number, vsCodTipBloqCV VARCHAR) IS

     select cli.cod_clie,
            RE.COD_REGI || ZO.COD_ZONA || SE.COD_SECC || to_char(te.cod_terr) AS TERRI,
            TRANSLATE(
                 TRIM(CLI.VAL_NOM1) || ' ' || TRIM(CLI.VAL_NOM2) ,
                 searchstr,
                 replacestr) AS NOM_CLIE,
                 
            TRANSLATE(
                 TRIM(CLI.VAL_APE1),
                 searchstr,
                 replacestr) AS APE1_CLIE,
                 
            TRANSLATE(
                 TRIM(CLI.VAL_APE2) ,
                 searchstr,
                 replacestr) AS APE2_CLIE,
                     
            TRANSLATE(
                 MI.NUM_DOCU_IDEN,
                 searchstr,
                 replacestr) AS NUM_DOCU_IDEN,
            TRANSLATE(
                 SUBSTR( val_nomb_via || ' ' || TRIM (num_ppal) || ' ' || TRIM (val_inte) || ' ' || TRIM (val_obse),0,80),
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
             CASE 
               when substr(ECL.COD_ESTA_CLIE,2,1) = '1' then '0' 
               when substr(ECL.COD_ESTA_CLIE,2,1) = '2' then '1'
               when substr(ECL.COD_ESTA_CLIE,2,1) = '3' then '2' 
               when substr(ECL.COD_ESTA_CLIE,2,1) = '4' then '2'
               when substr(ECL.COD_ESTA_CLIE,2,1) = '5' AND EST.NUM_CAMP_SIN_PEDI > 3 then '5'
               when substr(ECL.COD_ESTA_CLIE,2,1) = '5' AND EST.NUM_CAMP_SIN_PEDI <= 3 then '4'
               when substr(ECL.COD_ESTA_CLIE,2,1) = '6' then '3' 
               when substr(ECL.COD_ESTA_CLIE,2,1) = '7' then '5'
             END   
                 AS ESTA_CLIE,
             
               (SELECT  
                CASE
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '1' then '0' 
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '2' then '1' 
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '3' then '2' 
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '4' then '2' 
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '5' AND EST.NUM_CAMP_SIN_PEDI > 3 then '5'
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '5' AND EST.NUM_CAMP_SIN_PEDI <= 3 then '4'      
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '6' then '3'
                  WHEN TO_CHAR(clhe.esta_oid_esta_clie) = '7' then '5'
                END
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
                 AND vsCodPeriAnterior BETWEEN peri.cod_peri and peri_b.cod_peri)
            
              EST_ANTE,
             TRANSLATE
             ((
               SELECT decode (f1.val_text_comu, '','', f1.val_text_comu )
                 FROM mae_clien_comun f1
                WHERE cli.oid_clie = f1.clie_oid_clie
                  AND f1.ticm_oid_tipo_comu = 6
              ) ,
              searchstr,
              replacestr
             ) || '/' ||
             TRANSLATE
             ((
               SELECT decode (f2.val_text_comu, '' ,'', f2.val_text_comu) 
                 FROM mae_clien_comun f2
                WHERE cli.oid_clie = f2.clie_oid_clie
                  AND f2.ticm_oid_tipo_comu = 1
              ) ,
              searchstr,
              replacestr
             ) AS TELE1,
             TRANSLATE
             ((
               SELECT val_text_comu
                 FROM mae_clien_comun f2
                WHERE cli.oid_clie = f2.clie_oid_clie
                  AND f2.ticm_oid_tipo_comu = 7
              ) ,
              searchstr,
              replacestr
             ) AS TELE2,
           --   CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(cli.oid_clie) as Saldo,
              cli.sal_deud_ante as Saldo,
             (select nvl(c.imp_inte_vari,0) +  nvl(c.imp_inte_fijo,0)
                from ccc_conso_calcu_inter_mora c
               where c.clie_oid_clie = cli.oid_clie
                 and c.ind_fact = 0) as  MontoInte ,   
             TRANSLATE
             ((
               SELECT val_text_comu
                 FROM mae_clien_comun f2
                WHERE cli.oid_clie = f2.clie_oid_clie
                  AND f2.ticm_oid_tipo_comu = 3
              ) ,
              searchstr,
              replacestr
             ) AS CORREO,
             ( SELECT COD_CLAS 
                 FROM mae_clien_clasi mcc,
                      mae_clien_tipo_subti mts,
                      MAE_CLASI mcl
              WHERE 1=1
              AND mcl.oid_clas = mcc.clas_oid_clas
              AND mcl.tccl_oid_tipo_clas = mcc.tccl_oid_tipo_clasi
              AND mts.clie_oid_clie = cli.oid_clie
              AND mcc.ctsu_oid_clie_tipo_subt = mts.oid_clie_tipo_subt
              AND mcc.tccl_oid_tipo_clasi IN (vnIdTipClaReco)
              AND ROWNUM= 1
             )  AS PROG_RECO,
             CASE ( SELECT count(1) 
                 FROM mae_clien_clasi mcc,
                      mae_clien_tipo_subti mts,
                      MAE_EXENC_FLETE mef
              WHERE 1=1
              AND mef.ind_exen_flet = 1
              AND mef.tccl_oid_tipo_clas = mcc.tccl_oid_tipo_clasi
              AND mef.clas_oid_clas = mcc.clas_oid_clas
              AND mef.ticl_oid_tipo_clie = mts.ticl_oid_tipo_clie
              AND mef.sbti_oid_subt_clie = mts.sbti_oid_subt_clie
              AND mts.clie_oid_clie = cli.oid_clie
              AND mcc.ctsu_oid_clie_tipo_subt = mts.oid_clie_tipo_subt
             )  WHEN 0 THEN 'N' ELSE 'S' END  AS IND_FLETE,
            
               CASE ( SELECT count(1) 
               FROM mae_clien_clasi mcc,
                    mae_clien_tipo_subti mts
              WHERE 1=1
              AND mts.clie_oid_clie = cli.oid_clie
              AND mcc.ctsu_oid_clie_tipo_subt = mts.oid_clie_tipo_subt
              AND mcc.tccl_oid_tipo_clasi IN (vnIdTipClaImpr)
             ) WHEN 0 THEN 'N' ELSE 'S' END AS IND_IMPR,
             
              CASE (SELECT count(1)
               FROM MAE_CLIEN_BLOQU mb,
                    MAE_TIPO_BLOQU mtb
              WHERE 1=1
              AND CLIE_OID_CLIE = cli.oid_clie
              AND FEC_DESB IS NULL
              AND TIBQ_OID_TIPO_BLOQ = mtb.oid_tipo_bloq)
               WHEN 0 THEN 'N' ELSE 'S' END AS IND_BLOQ ,
             
             (SELECT mb.val_moti_bloq
               FROM MAE_CLIEN_BLOQU mb,
                    MAE_TIPO_BLOQU mtb
              WHERE 1=1
              AND CLIE_OID_CLIE = cli.oid_clie
              AND FEC_DESB IS NULL
              AND TIBQ_OID_TIPO_BLOQ = mtb.oid_tipo_bloq
              AND mb.fec_bloq = (SELECT MAX(FEC_BLOQ) 
                                   FROM mae_clien_bloqu
                                  WHERE CLIE_OID_CLIE = mb.CLIE_OID_CLIE
                                    AND FEC_DESB IS NULL
                                    AND ROWNUM=1)
              AND ROWNUM= 1) AS OBS_BLOQ,
                                                
              (SELECT mtb.cod_tipo_bloq
               FROM MAE_CLIEN_BLOQU mb,
                    MAE_TIPO_BLOQU mtb
              WHERE 1=1
              AND CLIE_OID_CLIE = cli.oid_clie
              AND FEC_DESB IS NULL
              AND TIBQ_OID_TIPO_BLOQ = mtb.oid_tipo_bloq
              AND mb.fec_bloq = (SELECT MAX(FEC_BLOQ) 
                                   FROM mae_clien_bloqu
                                  WHERE CLIE_OID_CLIE = mb.CLIE_OID_CLIE
                                    AND FEC_DESB IS NULL
                                    AND ROWNUM=1)
              AND ROWNUM= 1) AS COD_BLOQ,
              
              cli.cod_digi_ctrl,
              cli.fec_ingr
   
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
             mae_estat_clien       ecl,
             (
             SELECT oid_clie AS clie_oid_clie FROM mae_clien clie WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
             UNION
             SELECT clie_oid_clie FROM mae_clien_datos_adici WHERE fec_ulti_actu > ( fechaActualizacionInterfaz  )
             UNION
             SELECT clie_oid_clie FROM mae_clien_unida_admin WHERE ind_acti = 1 AND fec_ulti_actu > ( fechaActualizacionInterfaz )        
             UNION
             SELECT clie_oid_clie FROM mae_clien_direc WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
             UNION
             SELECT clie_oid_clie FROM mae_clien_comun WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
             UNION
             SELECT   clie.oid_clie clie_oid_clie
               FROM   int_tmp_gener_solic_cabec temp,
                      mae_clien clie
              WHERE   temp.cod_clie = clie.cod_clie
             GROUP BY clie.oid_clie
              ) data
             
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
--         AND est.ind_acti = 1
         AND est.esta_oid_esta_clie = ecl.oid_esta_clie
         AND data.clie_oid_clie = cli.oid_clie
         AND data.clie_oid_clie = est.clie_oid_clie
         AND data.clie_oid_clie = cdi.clie_oid_clie
         AND data.clie_oid_clie = cu.clie_oid_clie
         AND data.clie_oid_clie = mi.clie_oid_clie ;


    TYPE interfazRec IS RECORD(
        codigoConsultora             mae_clien.cod_clie%TYPE,
        territorio                   VARCHAR2(100),
        nombreConsultora             VARCHAR2(100),
        Ape1Consultora               VARCHAR2(50),
        Ape2Consultora               VARCHAR2(50),
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
        telefono12                   VARCHAR2(100),
        telefono3                    VARCHAR2(200),
        salCtaCte                    number(15,2),
        montInteres                  number(15,2),
        correo                       VARCHAR2(100),
        nivelReco                    VARCHAR2(2),
        exonFlete                    VARCHAR2(2),
        prioImp                      VARCHAR2(1),
        flagBloq                     VARCHAR2(1),
        obseBloq                     MAE_CLIEN_BLOQU.VAL_MOTI_BLOQ%type ,
        tipBloq                      VARCHAR2(15),
        digCtrl                      VARCHAR2(3),
        fecIngr                      date
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
     pdFechaUltimaActualizacion date;
     lnOidTipClaReco     NUMBER;
     lnOidTipClaImpr     NUMBER;
     lsCodTipBloqCV      VARCHAR2(2);

   BEGIN
        lnIdPais         := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        lnIdMarca        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
        lnIdCanal        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

        lsCodPeriAnterior  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
        lnIdPeriAnterior   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnterior, lnIdMarca, lnIdCanal);

       BEGIN 
         SELECT MAX(FEC_IPRO) INTO pdFechaUltimaActualizacion
          FROM BAS_HISTO_LOTES
          WHERE PAIS_COD_PAIS = psCodigoPais
             AND SIST_COD_SIST= psCodigosistema
             AND INTE_COD_INTE = psCodigoInterfaz
             AND IND_LOER ='N'
             AND FEC_FPRO IS NOT NULL;    
        EXCEPTION
          WHEN NO_DATA_FOUND THEN  
           SELECT TO_DATE('01/01/1990','DD/MM/YYYY') INTO pdFechaUltimaActualizacion FROM DUAL;
        END;   

        BEGIN   
          SELECT to_number(VAL_PAIN) 
          INTO  lnOidTipClaReco
          FROM  BAS_PARAM_INTER
          WHERE PAIS_COD_PAIS = psCodigoPais
            AND SIST_COD_SIST= psCodigosistema
            AND INTE_COD_INTE = psCodigoInterfaz
            AND NOM_PAIN = 'TipClaReco';     
        EXCEPTION
          WHEN NO_DATA_FOUND THEN  
             lnOidTipClaReco:=2245;  
        END;  
        
        BEGIN   
          SELECT to_number(VAL_PAIN) 
          INTO  lnOidTipClaImpr
          FROM  BAS_PARAM_INTER
          WHERE PAIS_COD_PAIS = psCodigoPais
            AND SIST_COD_SIST= psCodigosistema
            AND INTE_COD_INTE = psCodigoInterfaz
            AND NOM_PAIN = 'TipClaImgr';     
        EXCEPTION
          WHEN NO_DATA_FOUND THEN  
             lnOidTipClaReco:=2081;  
        END;  

        BEGIN   
          SELECT VAL_PAIN 
          INTO lsCodTipBloqCV
          FROM BAS_PARAM_INTER
          WHERE PAIS_COD_PAIS = psCodigoPais
            AND SIST_COD_SIST= psCodigosistema
            AND INTE_COD_INTE = psCodigoInterfaz
            AND NOM_PAIN = 'CodBloqCV';     
        EXCEPTION
          WHEN NO_DATA_FOUND THEN  
             lsCodTipBloqCV:='CV';
        END;          
        
        

        OPEN c_interfaz(lnIdPais,lnIdMarca,lnIdCanal, lsCodPeriAnterior, lnIdPeriAnterior ,pdFechaUltimaActualizacion,lnOidTipClaReco, lnOidTipClaImpr,lsCodTipBloqCV)  ;
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
                              interfazRecord(x).Ape1Consultora  ||';'||
                              interfazRecord(x).Ape2Consultora  ||';'||
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
                              interfazRecord(x).telefono12 ||';'||
                              interfazRecord(x).telefono3   ||';'||
                              ltrim(to_char(interfazRecord(x).salCtaCte , '9999999990.00'))  ||';'||
                              ltrim(to_char(interfazRecord(x).montInteres , '9999999990.00')) ||';'||
                           --   interfazRecord(x).salCtaCte   ||';'||
                           --   interfazRecord(x).montInteres ||';'||
                              interfazRecord(x).correo      ||';'||
                              interfazRecord(x).nivelReco   ||';'||
                              interfazRecord(x).exonFlete   ||';'||
                              interfazRecord(x).prioImp     ||';'||
                              interfazRecord(x).flagBloq    ||';'||
                              interfazRecord(x).obseBloq    ||';'||
                              interfazRecord(x).tipBloq     ||';'||
                              interfazRecord(x).digCtrl     ||';'||
                              to_char(interfazRecord(x).fecIngr ,'DD/MM/YYYY')     ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FOX_ENVIO_CONSU: '||ls_sqlerrm);
END INT_PR_POL_ENVIO_CONSU;

/***************************************************************************
Descripcion       : Genera Interfase de Envio de Recomendaciones(GARANTE)
Fecha Creacion    : 13/04/2015
Autor             : Juan Gutierrez
***************************************************************************/
procedure INT_PR_POL_ENVIO_RECOM( pscodigopais varchar2,
                                  psCodigoSistema VARCHAR2,
                                  pscodigoInterfaz varchar2,
                                  psNombreArchivo varchar2,
                                  psCodigoPeriodo VARCHAR2) IS

   CURSOR c_interfaz (pdFechaUltActua date) IS
    /*  with temp as
      (
      select   clie_b.cod_clie cprcodeb,
               clie.cod_clie cprcodeb1,
               (SELECT mci_b.num_docu_iden FROM  mae_clien_ident mci_b
                WHERE mci_b.tdoc_oid_tipo_docu = 2001
                AND   mci_b.clie_oid_clie = clie_b.oid_clie) docIdenGte      
    FROM mae_clien_vincu clvi,
         mae_tipo_vincu tivc,
         mae_clien clie,
         mae_clien clie_b,
         mae_clien_datos_adici clda
        
    WHERE clvi.tivc_oid_tipo_vinc = tivc.oid_tipo_vinc
      AND clvi.clie_oid_clie_vndo = clie.oid_clie
      AND clvi.clie_oid_clie_vndo = clda.clie_oid_clie
      AND clvi.clie_oid_clie_vnte = clie_b.oid_clie
      and tivc.cod_tipo_vinc = '03'
      and clvi.fec_hast is not null
      and ( clvi.fec_desd >= pdFechaUltActua 
        or trunc(clvi.fec_ulti_actu) >= pdFechaUltActua))
      select cprcodeb1,
             docIdenGte
        from temp;*/
                    
    SELECT COD_CLIE,
           NUM_DOCU_REFE 
    FROM   mae_refer
    WHERE  tipo_refe = 3
    AND    trunc(FEC_MODI) >=  pdFechaUltActua ;

   TYPE interfazRec IS RECORD(
        codigoRecomendada  mae_clien.cod_clie%TYPE,
        docIdenGarante     mae_clien_ident.num_docu_iden%TYPE
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnidperiodo         cra_perio.oid_peri%type;
   pdFechaUltActua     date;

   BEGIN

   BEGIN 
     SELECT TRUNC(MAX(FEC_IPRO))
       INTO pdFechaUltActua -- debe ser cargada con ultim envio de ssicc
       FROM BAS_HISTO_LOTES
      WHERE PAIS_COD_PAIS = psCodigoPais
        AND SIST_COD_SIST= psCodigosistema
        AND INTE_COD_INTE = psCodigoInterfaz
        AND IND_LOER ='N'
        AND FEC_FPRO IS NOT NULL;
   EXCEPTION
    WHEN NO_DATA_FOUND THEN  
     SELECT TO_DATE('01/01/1990','DD/MM/YYYY') INTO pdFechaUltActua FROM DUAL;
   END; 

       lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
       OPEN c_interfaz(pdFechaUltActua);
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoRecomendada ||';'||
                              interfazRecord(x).docIdenGarante ;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FOX_ENVIO_RECOM: '||ls_sqlerrm);
END INT_PR_POL_ENVIO_RECOM;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas (STZONAS)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ZONA        (psCodigoPais VARCHAR2,
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
                    AND f2.ticm_oid_tipo_comu = 3
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FOX_ENVIO_ZONA: '||ls_sqlerrm);
END INT_PR_POL_ENVIO_ZONA;


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Zonas Consultoras (STDZONA)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ZONA_CONSU (psCodigoPais VARCHAR2,
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FOX_ENVIO_ZONA_CONSU: '||ls_sqlerrm);
END INT_PR_POL_ENVIO_ZONA_CONSU;



/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Facturas Consultoras (EBESTAD)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CONSU_FACTU (psCodigoPais VARCHAR2,
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
       C.VAL_TOTA_PAGA_LOCA,
       TO_CHAR(C.VAL_NUME_SOLI),
       CASE 
       (SELECT COUNT(1) FROM MAE_CLIEN_DATOS_ADICI macd 
        WHERE macd.clie_oid_clie = cli.oid_clie
        AND macd.esta_oid_esta_clie =  1)  
          WHEN 1 THEN 'S'
            ELSE 'N'
       END AS indPriPed
       
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
       AND C.FEC_FACT = TO_DATE(psFechaProceso,'DD/MM/YYYY')
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
        montoFacturaImpto     ped_solic_cabec.VAL_TOTA_PAGA_LOCA%TYPE,
        numPedi               NUMBER(10),
        indPriPed             VARCHAR2(1)
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
                              interfazRecord(x).montoFacturaImpto ||';'||
                              interfazRecord(x).numPedi ||';'||
                              interfazRecord(x).indPriPed ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FOX_ENVIO_CONSU_FACTU: '||ls_sqlerrm);
END INT_PR_POL_ENVIO_CONSU_FACTU;


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Matriz Facturación (CMMTRIZ)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_MATRI_FACTU (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
IS  
 CURSOR c_interfaz(vnIdPeriodo NUMBER , vnIdPais NUMBER) IS    
             select d.val_nomb_peri,
                    b.val_codi_vent ,
                    f.cod_sap ,
                    g.val_i18n  ,
                    b.imp_prec_cata  ,
                    j.cod_cata  ,
                    j.des_cata  ,
                    NVL(b.num_pagi_cata,0) NUM_PAGINA,
                    NVL(b.num_unid_esti,0) UNID_ESTIMADAS,
                    k.cod_marc_prod,   
                    l.cod_nego 
               FROM pre_ofert             a,
                    pre_ofert_detal       b,
                    pre_matri_factu_cabec c,
                    cra_perio             d,
                    pre_tipo_ofert        e,
                    mae_produ             f,
                    gen_i18n_sicc_pais    g,
                    pre_estra             h,
                    gen_i18n_sicc_pais    i,
                    pre_catal             j,
                    seg_marca_produ       k,
                    mae_negoc             l
              WHERE oid_peri = vnIdPeriodo
                AND g.attr_enti = 'MAE_PRODU'
                AND i.attr_enti = 'PRE_ESTRA'
                AND oid_ofer = ofer_oid_ofer
                AND mfca_oid_cabe = oid_cabe
                AND perd_oid_peri = oid_peri
                AND b.tofe_oid_tipo_ofer = e.oid_tipo_ofer
                AND b.prod_oid_prod = f.oid_prod
                AND g.val_oid = f.oid_prod
                AND a.coes_oid_estr = h.oid_estr
                AND i.val_oid = h.oid_estr
                AND b.ocat_oid_catal = j.oid_cata
                AND f.mapr_oid_marc_prod = k.oid_marc_prod
                AND l.oid_nego = f.nego_oid_nego
                AND l.pais_oid_pais = vnIdPais
              ORDER BY oid_ofer, oid_deta_ofer;
      
  
   TYPE interfazRec IS RECORD(
        codigoPeriodo        varchar(6),
        codCUV                pre_ofert_detal.val_codi_vent%TYPE,
        codSAP                mae_produ.cod_sap%TYPE,
        desProd               varchar(100),
        mtoCatal              pre_ofert_detal.imp_prec_cata%TYPE,
        codCatal              pre_catal.cod_cata%TYPE,
        desCatal              pre_catal.des_cata%TYPE,
        numPagi               pre_ofert_detal.num_pagi_cata%TYPE,
        uniEstim              pre_ofert_detal.Num_Unid_Esti%TYPE,
        codMarca              seg_marca_produ.cod_marc_prod%TYPE,
        codClase              MAE_NEGOC.COD_NEGO%TYPE
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
        OPEN c_interfaz( lnIdPeriodo,lnIdPais);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoPeriodo ||';'||
                              interfazRecord(x).codCUV        ||';'||
                              interfazRecord(x).codSAP        ||';'||
                              interfazRecord(x).desProd       ||';'||
                              interfazRecord(x).mtoCatal      ||';'||
                              interfazRecord(x).codCatal      ||';'||
                              interfazRecord(x).desCatal      ||';'|| 
                              interfazRecord(x).numPagi       ||';'||                                     
                              interfazRecord(x).uniEstim      ||';'||
                              interfazRecord(x).codMarca      ||';'||
                              interfazRecord(x).codClase      ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_MATRI_FACTU: '||ls_sqlerrm);
    
END INT_PR_POL_ENVIO_MATRI_FACTU;         


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio Productos fuera de caja (FUERACJA)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_PRODU_FUCJA (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
IS        
CURSOR c_interfaz IS    
       
  select cp.val_nomb_peri,con.val_nume_soli, mp.cod_sap,gen.val_i18n, sum(psp.num_unid_aten)             
    from ped_solic_cabec con,
         ped_solic_cabec psc,
         ped_solic_posic psp,
         mae_produ mp,
         cra_perio cp,
         gen_i18n_sicc_pais gen
    where con.oid_soli_cabe = psc.soca_oid_soli_cabe
      and psc.oid_soli_cabe = psp.soca_oid_soli_cabe
      and psp.prod_oid_prod = mp.oid_prod
      and cp.oid_peri = con.perd_oid_peri
      and gen.attr_enti = 'MAE_PRODU'
      and gen.val_oid = mp.oid_prod
      and cp.val_nomb_peri= psCodigoPeriodo
      and psp.ind_dent_fuer_caja_bols != 'C'
      and psp.num_unid_aten > 0
      and nvl(mp.val_atri_3,'0') <> '1'
      --and psc.clie_oid_clie = (select oid_clie from mae_clien where cod_clie ='0354560')
    group by  cp.val_nomb_peri,con.val_nume_soli, mp.cod_sap,gen.val_i18n
    order by 2,3;
   
  
                         
  TYPE interfazRec IS RECORD(
        codPeriodo            varchar(6),
        numPedido             ped_solic_cabec.val_nume_soli%TYPE,
        codSAP                mae_produ.cod_sap%TYPE,
        desProd               varchar(100),
        cant                  ped_solic_posic.num_unid_aten%TYPE
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
                   lsLinea := interfazRecord(x).codPeriodo ||';'||
                              interfazRecord(x).numPedido  ||';'||
                              interfazRecord(x).codSAP     ||';'||
                              interfazRecord(x).desProd    ||';'||
                              interfazRecord(x).cant      ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_PRODU_FUCJA: '||ls_sqlerrm);
                               
END INT_PR_POL_ENVIO_PRODU_FUCJA;      

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Cargos Cuenta Corriente (CTACTE_CARGO)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_CARGO_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
IS  
 CURSOR c_interfaz(vnIdPeriodo NUMBER) IS    
    SELECT mc.cod_clie,
           mcc.oid_movi_cc,
           (SELECT cod_proc FROM CCC_PROCE
            WHERE oid_proc = su.ccpr_oid_proc ) AS codProc,
           (SELECT des_proc FROM CCC_PROCE
            WHERE oid_proc = su.ccpr_oid_proc ) AS desProc,           
           su.cod_subp,
           su.des_subp,
           mcc.imp_movi,
           TO_CHAR(TRUNC(mcc.fec_docu),'dd/mm/yyyy'),
           spc.cod_peri
     FROM  ccc_movim_cuent_corri mcc,
           ccc_subpr su,
           mae_clien mc,
           cra_perio cp,
           seg_perio_corpo spc
    WHERE  mcc.subp_oid_subp_crea = su.oid_subp
      AND  mcc.clie_oid_clie = mc.oid_clie
      AND  mcc.perd_oid_peri = cp.oid_peri
      AND  cp.peri_oid_peri = spc.oid_peri  
      AND  mcc.imp_movi > 0
      AND  mcc.perd_oid_peri = vnIdPeriodo;
      
  
   TYPE interfazRec IS RECORD(
        codigoConsultora      mae_clien.cod_clie%TYPE,
        numCargo              ccc_movim_cuent_corri.oid_movi_cc%TYPE,
        codTipoProc           CCC_PROCE.COD_PROC%TYPE,
        desTipoProc           CCC_PROCE.DES_PROC%TYPE,
        codTipoCargo          ccc_subpr.cod_subp%TYPE,
        destipoCargo          ccc_subpr.des_subp%TYPE,
        mtoCargo              ccc_movim_cuent_corri.imp_movi%TYPE,
        fecCargo              VARCHAR2(10),
        codigoPeriodo         VARCHAR2(6)
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
        OPEN c_interfaz( lnIdPeriodo);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).numCargo         ||';'||
                              interfazRecord(x).codTipoProc      ||';'||
                              interfazRecord(x).desTipoProc      ||';'||
                              interfazRecord(x).codTipoCargo     ||';'||
                              interfazRecord(x).desTipoCargo     ||';'||
                              interfazRecord(x).mtoCargo         ||';'||
                              interfazRecord(x).fecCargo         ||';'||
                              interfazRecord(x).codigoPeriodo ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_CARGO_CTCTE: '||ls_sqlerrm);
                                   
END INT_PR_POL_ENVIO_CARGO_CTCTE;

                 
/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CTACTE_ABONO)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ABONO_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaProceso VARCHAR2)
IS                                   

 CURSOR c_interfaz(vnIdPeriodo NUMBER) IS    
   SELECT mc.cod_clie,
          abo.oid_movi_cc,
          (SELECT cod_proc FROM CCC_PROCE
           WHERE oid_proc = su.ccpr_oid_proc ) AS codProc,
          (SELECT des_proc FROM CCC_PROCE
           WHERE oid_proc = su.ccpr_oid_proc ) AS desProc,
          su.cod_subp, 
          su.des_subp,
          a.imp_abon,
          TO_CHAR(TRUNC(a.fec_apli), 'dd/mm/yyyy'),
          a.mvcc_oid_movi_carg
     FROM ccc_aplic_abono_cargo a,
          ccc_movim_cuent_corri abo,
          mae_clien mc,
          ccc_subpr su
    WHERE a.clie_oid_clie = mc.oid_clie
      AND a.mvcc_oid_movi_abon = abo.oid_movi_cc
      AND abo.subp_oid_subp_crea = su.oid_subp
      AND abo.perd_oid_peri = vnIdPeriodo
      AND TRUNC(a.fec_apli) = TO_DATE(psFechaProceso, 'dd/mm/yyyy')
    UNION ALL
   SELECT  mc.cod_clie,
           ban.oid_movi_banc,
          (SELECT cod_proc FROM CCC_PROCE
           WHERE oid_proc = su.ccpr_oid_proc ) AS codProc,
          (SELECT des_proc FROM CCC_PROCE
           WHERE oid_proc = su.ccpr_oid_proc ) AS desProc,
           su.cod_subp,
           su.des_subp,
           a.imp_abon,
           TO_CHAR(TRUNC(a.fec_apli), 'dd/mm/yyyy'),
           a.mvcc_oid_movi_carg
     FROM  ccc_aplic_abono_cargo a,
           ccc_movim_banca ban,
           mae_clien mc,
           ccc_subpr su
    WHERE  a.clie_oid_clie = mc.oid_clie
      AND  a.mvcc_oid_movi_abon = ban.oid_movi_banc
      AND  ban.subp_oid_marc_crea = su.oid_subp
      AND  TRUNC(a.fec_apli) = TO_DATE(psFechaProceso, 'dd/mm/yyyy')  ;
      
  
   TYPE interfazRec IS RECORD(
        codigoConsultora      mae_clien.cod_clie%TYPE,
        numAbono              ccc_movim_cuent_corri.oid_movi_cc%TYPE,
        codTipoProc           CCC_PROCE.COD_PROC%TYPE,
        desTipoProc           CCC_PROCE.DES_PROC%TYPE,
        codTipoAbono          ccc_subpr.cod_subp%TYPE,
        destipoAbono          ccc_subpr.des_subp%TYPE,
        mtoAbono              ccc_aplic_abono_cargo.imp_abon%TYPE,
        fecCancel             VARCHAR2(10),
        numCargo              ccc_aplic_abono_cargo.mvcc_oid_movi_carg%TYPE
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
        OPEN c_interfaz( lnIdPeriodo);
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).numAbono         ||';'||
                              interfazRecord(x).codTipoProc      ||';'||
                              interfazRecord(x).desTipoProc      ||';'||
                              interfazRecord(x).codTipoAbono     ||';'||
                              interfazRecord(x).desTipoAbono     ||';'||
                              interfazRecord(x).mtoAbono         ||';'||
                              interfazRecord(x).fecCancel        ||';'||
                              interfazRecord(x).numCargo           ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_ABONO_CTCTE: '||ls_sqlerrm);
        
END INT_PR_POL_ENVIO_ABONO_CTCTE;

/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CTACTE_CNTINT)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_INTER_CTCTE (psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
IS        

CURSOR c_interfaz  IS    
     WITH temp1 AS
  (SELECT mcc.clie_oid_clie, mcc.imp_movi imp_inte
     FROM ccc_movim_cuent_corri mcc
    WHERE mcc.cod_tipo_docu_homo = '4'
      AND mcc.imp_movi > 0
   UNION ALL
   SELECT i.clie_oid_clie, i.mon_fact imp_inte
     FROM ccc_conso_calcu_inter_histo i)
 SELECT mc.cod_clie, COUNT(*) val_cant, SUM(imp_inte) val_mont
   FROM temp1 t1, mae_clien mc
  where mc.oid_clie = t1.clie_oid_clie
  GROUP BY mc.cod_clie;
                           
   
    TYPE interfazRec IS RECORD(
        codigoConsultora      mae_clien.cod_clie%TYPE,
        cantCargoInt          NUMBER(6),
        monCargoInt           NUMBER(15,2)
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

 OPEN c_interfaz;
        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;

            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea := interfazRecord(x).codigoConsultora ||';'||
                              interfazRecord(x).cantCargoInt     ||';'||
                              interfazRecord(x).monCargoInt      ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_INTER_CTCTE: '||ls_sqlerrm);

END INT_PR_POL_ENVIO_INTER_CTCTE;


/***********************************************************************************
  Descripcion       : Genera Interfase de Envio de Abonos Cuenta Corriente (CONTROL)
  Fecha Creacion    : 13/04/2015
  Autor             : Juan Gutiérrez
 ************************************************************************************/
PROCEDURE INT_PR_POL_ENVIO_ARCHI_CONTR(psCodigoPais VARCHAR2,
                                   psCodigoSistema VARCHAR2,
                                   psCodigoInterfaz VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psCodigoPeriodo VARCHAR2)
IS        




   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnIdPais            NUMBER;
   lnIdPeriodo         NUMBER;
   lsNumLote           VARCHAR2(15);   
                                
BEGIN
                   
   lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);   
   lnIdPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);   

    BEGIN           
   SELECT MAX(NUM_LOTE) INTO lsNumLote
     FROM BAS_HISTO_LOTES
    WHERE PAIS_COD_PAIS = psCodigoPais
      AND SIST_COD_SIST=  psCodigosistema
      AND INTE_COD_INTE = psCodigoInterfaz
      AND IND_LOER ='N'
      AND FEC_FPRO IS NULL;    
        EXCEPTION
          WHEN NO_DATA_FOUND THEN  
           SELECT '00000000' INTO lsNumLote FROM DUAL;
     END;   
        
     IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
     END IF;

      lsLinea := lsNumLote    ;

      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

    IF NOT lbAbrirUtlFile THEN
      UTL_FILE.FCLOSE(V_HANDLE);
      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_POL_ENVIO_ARCHI_CONTR: '||ls_sqlerrm);

END INT_PR_POL_ENVIO_ARCHI_CONTR;

END INT_PKG_PORTA_LOCAL;
/
