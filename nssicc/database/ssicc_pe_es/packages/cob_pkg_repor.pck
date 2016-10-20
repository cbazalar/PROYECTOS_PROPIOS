CREATE OR REPLACE PACKAGE COB_PKG_REPOR IS

  gc_cod_modu                    CONSTANT CHAR(6):= 'COB';
  gv_log_cod_modu                fin_modul.cod_modu%TYPE;
  gv_log_cod_proc                fin_proce_modul.cod_proc%TYPE;

  gv_cod_proc_ejec               NUMBER(12);
  gv_log_cod_pais                own_comun.seg_pais.cod_pais%TYPE;
  gv_log_cod_soci                seg_socie.cod_soci%TYPE;
  gv_des_log                     VARCHAR2(2500);
  gv_log_user                    fin_proce_ejecu.usu_proc%TYPE;

  /* Declaracion de variables */
  ln_sqlcode                     NUMBER(10);
  ls_sqlerrm                     VARCHAR2(1500);
  w_filas                        NUMBER := 5000;

  PROCEDURE COB_PR_REPOR_PEDID_LEVAN_DEUDA(
  p_cod_peri_inic                seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                seg_perio_corpo.cod_peri%TYPE DEFAULT NULL);

 PROCEDURE COB_PR_REPOR_PRIME_PEDID_DEUDO(
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,

  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL);

 PROCEDURE COB_PR_REPOR_SEGUN_PEDID_DEUDO (
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL);

 PROCEDURE COB_PR_REPOR_TERCE_PEDID_DEUDO (
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL);

 PROCEDURE COB_PR_REPOR_CUART_PEDID_DEUDO (
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL);
  
 PROCEDURE COB_PR_REPOR_EGRES_SINDE(
  p_imp_deud                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT 0);

 PROCEDURE COB_PR_REPOR_RETIR_SINDE(
  p_imp_deud                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT 0);

 /************************************************************************************
  Descripcion           : Genera Reporte COB Detallado Movimiento Recuperacion Incobrable en formato CSV
  Fecha Creacion    : 18/02/2014
  Autor                    : Sebastian Guerra
 ************************************************************************************/
 PROCEDURE COB_PR_REPOR_MOVIM_RECUP_INCOB(
  pscodigopais                   IN   VARCHAR2,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  pscodigousuario                IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2);

  /************************************************************************************
  Descripcion           : Genera la data para todos los Reportes de FFVV - FTP
  Fecha Creacion    : 11/04/2014
  Autor                    : Aurelio Oviedo
  ************************************************************************************/
 PROCEDURE COB_PR_REPOR_FFVV_FTP(
  psCodigoUsuario                IN   VARCHAR2);

 PROCEDURE COB_PR_GENER_REPOR_COMIS_ABOGA(
  p_fec_inic                     IN   VARCHAR2,
  p_fec_fina                     IN   VARCHAR2);

 
 PROCEDURE COB_PR_GENER_COBRA_FFVV(
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE DEFAULT NULL);
  
/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte cobranza FFVV
Fecha Creacion    : 03/03/2015
Autor             : Ivan Tocto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psTitulo : Cabecera del archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE COB_PR_GENER_REPOR_COBRA_FFVV(
    psCodigoPais            VARCHAR2,
    psCodigoPeriodo         VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );  
    
/************************************************************************************
Descripcion       : Genera Reporte COB Numero de Pagos por Campaña en formato CSV
Fecha Creacion    : 05/11/2015
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE COB_PR_REPOR_PAGOS_CAMPA_CSV(
  pscodigopais                   IN   VARCHAR2,
  psTipoReporte                  IN   VARCHAR2,
  pnNumeroPagosMayores           IN   NUMBER,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  pscodigousuario                IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2);
END COB_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY COB_PKG_REPOR IS

 PROCEDURE COB_PR_REPOR_PEDID_LEVAN_DEUDA(
  p_cod_peri_inic                seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 IS

  lv_cod_peri_inic               seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_fina               seg_perio_corpo.cod_peri%TYPE;
  lv_num_camp                    NUMBER(2);
  lv_imp_deud_mini               NUMBER(12,2);

 BEGIN

  lv_num_camp := TO_NUMBER(NVL(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('NumeCampRepoFactDeud'),3))*-1;
  lv_imp_deud_mini := TO_NUMBER(NVL(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('ImporteDeudaMinimaRepoFactDeud'),0));

  IF p_cod_peri_inic IS NULL THEN
   lv_cod_peri_inic := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA,lv_num_camp);
  ELSE
   lv_cod_peri_inic := p_cod_peri_inic;
  END IF;

  IF p_cod_peri_fina IS NULL THEN
   lv_cod_peri_fina := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;
  ELSE
   lv_cod_peri_fina := p_cod_peri_fina;
  END IF;

  DELETE FROM cob_repor_segui_levan_deuda;

  INSERT INTO cob_repor_segui_levan_deuda
    (cod_peri, oid_peri,oid_clie,cod_clie, nom_clie, fec_admi_cart,usu_admi_cart, imp_deud_leva )
   SELECT
    c.cod_peri,
    c.perd_oid_peri,
    c.clie_oid_clie,
    c.cod_clie,
    c.nom_clie,
    c.fec_admi_cart,
    c.usu_admi_cart,
    c.val_sald_deud
   FROM
    int_solic_conso_cabec c
   WHERE c.ind_admi_cart = 1
     AND c.cod_peri >= lv_cod_peri_inic
     AND c.cod_peri <= lv_cod_peri_fina;

  INSERT INTO cob_repor_segui_levan_deuda
     (cod_peri, oid_peri,oid_clie,cod_clie, nom_clie, fec_admi_cart,usu_admi_cart, imp_deud_leva )
   SELECT
    h.cod_peri,
    h.perd_oid_peri,
    h.clie_oid_clie,
    h.cod_clie,
    h.nom_clie,
    h.fec_admi_cart,
    h.usu_admi_cart,
    h.val_sald_deud
   FROM
    ped_histo_solic_conso_cabec h
   WHERE h.ind_admi_cart = 1
     AND h.cod_peri >= lv_cod_peri_inic
     AND h.cod_peri <= lv_cod_peri_fina;

  UPDATE cob_repor_segui_levan_deuda c
  SET c.imp_deud_leva_actu =
        NVL((SELECT SUM(mcc.imp_pend)
             FROM
              ccc_movim_cuent_corri mcc,
              cra_perio cp,
              seg_perio_corpo spc
             WHERE mcc.clie_oid_clie = c.oid_clie
               AND mcc.perd_oid_peri = cp.oid_peri
               AND spc.oid_peri = cp.peri_oid_peri
               AND spc.cod_peri < c.cod_peri
               AND mcc.imp_pend <> 0),0);


  DELETE FROM cob_repor_segui_levan_deuda c
  WHERE c.imp_deud_leva_actu < lv_imp_deud_mini;

  UPDATE cob_repor_segui_levan_deuda c
  SET
   c.cod_regi =
     (SELECT MAX(v.cod_regi)
      FROM v_mae_clie_unida_admin v
      WHERE v.oid_clie = c.oid_clie
        AND v.ind_acti = 1),
   c.cod_zona =
     (SELECT MAX(v.cod_zona)
      FROM v_mae_clie_unida_admin v
      WHERE v.oid_clie = c.oid_clie
        AND v.ind_acti = 1),
   c.cod_secc =
      (SELECT MAX(v.cod_secc)
       FROM v_mae_clie_unida_admin v
       WHERE v.oid_clie = c.oid_clie
         AND v.ind_acti = 1),
   c.num_docu_iden = (
       SELECT MAX(mci.num_docu_iden)
       FROM mae_clien_ident mci
       WHERE mci.clie_oid_clie = c.oid_clie
       AND mci.val_iden_docu_prin = 1);


  UPDATE cob_repor_segui_levan_deuda c
  SET
   c.val_camp_prim_pedi =
           ( SELECT MIN(mce.camp_prim_pedi)
             FROM mae_clien_estat mce
             WHERE mce.oid_clie = c.oid_clie),
   c.imp_mont_pedi =
           NVL((SELECT SUM(mcc.imp_movi)
                FROM ccc_movim_cuent_corri mcc
                WHERE mcc.subp_oid_subp_crea = 2001
                  AND mcc.clie_oid_clie = c.oid_clie
                  AND mcc.perd_oid_peri = c.oid_peri
                  AND mcc.imp_movi > 0),0),
   c.val_etap_cobr = (
                   SELECT MIN(ed.val_desc)
                   FROM cob_detal_asign_carte d,
                        cob_etapa_deuda_pais ed
                   WHERE d.cod_etap_deud = ed.cod_etap_deud
                    AND d.cod_peri = c.cod_peri
                    AND d.oid_clie = c.oid_clie
                    AND d.fec_cier >= TRUNC(SYSDATE)),
   c.cod_cobr_cart =
          (SELECT MIN(u.val_nomb_usua_cobr)
                   FROM cob_detal_asign_carte d,
                        cob_usuar_cobra_pais u
                   WHERE  d.cod_peri = c.cod_peri
                    AND d.oid_clie = c.oid_clie
                    AND d.cod_usua_cobr = u.cod_usua_cobr
                    AND d.fec_cier >= TRUNC(SYSDATE));

   UPDATE cob_repor_segui_levan_deuda c
   SET
     c.imp_pend_cam3 =
        NVL((SELECT SUM(mcc.imp_pend)
             FROM ccc_movim_cuent_corri mcc,
                  cra_perio cp,
                  seg_perio_corpo spc
             WHERE mcc.clie_oid_clie = c.oid_clie
               AND mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(c.cod_peri,-3)
               AND mcc.imp_pend <> 0),0),
     c.imp_pend_cam2 =
        NVL((SELECT SUM(mcc.imp_pend)
             FROM ccc_movim_cuent_corri mcc,
                  cra_perio cp,
                  seg_perio_corpo spc
             WHERE mcc.clie_oid_clie = c.oid_clie
               AND mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(c.cod_peri,-2)
               AND mcc.imp_pend <> 0),0),
     c.imp_pend_cam1 =
         NVL((SELECT SUM(mcc.imp_pend)
             FROM ccc_movim_cuent_corri mcc,
                  cra_perio cp,
                  seg_perio_corpo spc
             WHERE mcc.clie_oid_clie = c.oid_clie
               AND mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(c.cod_peri,-1)
               AND mcc.imp_pend <> 0),0),
     c.imp_pend_camp =
         NVL((SELECT SUM(mcc.imp_pend)
                        FROM ccc_movim_cuent_corri mcc
                        WHERE mcc.clie_oid_clie = c.oid_clie
                          AND mcc.perd_oid_peri = c.oid_peri
                          AND mcc.imp_pend <> 0),0),
     c.imp_deud_tota =
          NVL((SELECT SUM(mcc.imp_pend)
               FROM ccc_movim_cuent_corri mcc
               WHERE mcc.clie_oid_clie = c.oid_clie
                 AND mcc.imp_pend <> 0),0);

 END COB_PR_REPOR_PEDID_LEVAN_DEUDA;

 PROCEDURE COB_PR_REPOR_PRIME_PEDID_DEUDO(
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 IS

 BEGIN
  
  DELETE FROM cob_repor_prime_pedid_deudo;

  INSERT INTO cob_repor_prime_pedid_deudo
   WITH 
    temp1 AS (
     SELECT
      mce.oid_clie,
      mce.camp_prim_pedi cod_peri_prim_pedi,
      cp.oid_peri oid_peri_prim_pedi
     FROM 
      mae_clien_estat mce,
      cra_perio cp,
      seg_perio_corpo spc
     WHERE mce.camp_prim_pedi = spc.cod_peri
       AND mce.val_nume_pedi = 1
       AND spc.oid_peri = cp.peri_oid_peri
       AND mce.camp_prim_pedi >= p_cod_peri_inic
       AND mce.camp_prim_pedi <= p_cod_peri_fina),
    temp2 AS (
     SELECT
      pc.cod_peri_prim_pedi,
      pc.oid_peri_prim_pedi,
      mcc.clie_oid_clie oid_clie,
      SUM(mcc.imp_movi) imp_pedi,
      SUM(mcc.imp_pend) imp_pedi_pend
     FROM
      temp1  pc,
      ccc_movim_cuent_corri mcc
     WHERE mcc.clie_oid_clie = pc.oid_clie
       AND mcc.perd_oid_peri = pc.oid_peri_prim_pedi
     HAVING SUM(mcc.imp_pend) > 0
     GROUP BY pc.cod_peri_prim_pedi,pc.oid_peri_prim_pedi, mcc.clie_oid_clie)
    SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_REGI') regi,
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_ZONA') zona,
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_SECC') secc,
     t2.oid_clie,
     mc.cod_clie,
     mc.val_nom1 || ' ' ||
     mc.val_nom2 || ' ' ||
     mc.val_ape1 || ' ' ||
     mc.val_ape2 nom_clie,
     cp.oid_peri,
     spc.cod_peri,
     t2.imp_pedi,
     t2.imp_pedi_pend,
     NVL((SELECT SUM(mcc.imp_pend)
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.clie_oid_clie = t2.oid_clie
        AND mcc.perd_oid_peri <> t2.oid_peri_prim_pedi
        AND mcc.imp_pend > 0),0) imp_otro_deud,
     mc.sal_deud_ante imp_deud_tota
    FROM
     temp2 t2,
     mae_clien mc,
     cra_perio cp,
     seg_perio_corpo spc
    WHERE t2.oid_clie = mc.oid_clie
    AND t2.oid_peri_prim_pedi = cp.oid_peri
    AND cp.peri_oid_peri = spc.oid_peri;
     
   DELETE FROM cob_repor_prime_pedid_deudo sp
   WHERE sp.imp_prim_pedi_pend <= 0;
   
  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_PRIME_PEDID_DEUDO: '||ls_sqlerrm);

 END COB_PR_REPOR_PRIME_PEDID_DEUDO;

 PROCEDURE COB_PR_REPOR_SEGUN_PEDID_DEUDO (
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 IS

 BEGIN
    
  DELETE FROM cob_repor_segun_pedid_deudo;

  INSERT INTO cob_repor_segun_pedid_deudo
   WITH 
    temp1 AS (
     SELECT
      mce.oid_clie,
      mce.camp_prim_pedi cod_peri_prim_pedi,
      cp.oid_peri oid_peri_prim_pedi,
      mce.camp_ulti_pedi cod_peri_segu_pedi
     FROM 
      mae_clien_estat mce,
      cra_perio cp,
      seg_perio_corpo spc
     WHERE mce.camp_prim_pedi = spc.cod_peri
       AND spc.oid_peri = cp.peri_oid_peri
       AND mce.val_nume_pedi = 2
       AND mce.camp_prim_pedi >= p_cod_peri_inic
       AND mce.camp_prim_pedi <= p_cod_peri_fina)      
    SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_REGI'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_ZONA'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_SECC'),
     t1.oid_clie,
     mc.cod_clie,
     mc.val_nom1 || ' ' ||
     mc.val_nom2 || ' ' ||
     mc.val_ape1 || ' ' ||
     mc.val_ape2 nom_clie,
     t1.oid_peri_prim_pedi,
     t1.cod_peri_prim_pedi,
     NULL,
     NULL,
     NULL,
     t1.cod_peri_segu_pedi,
     NULL,
     NULL,
     NULL,
     mc.sal_deud_ante
    FROM
     temp1 t1,
     mae_clien mc
    WHERE t1.oid_clie = mc.oid_clie;
  
  UPDATE cob_repor_segun_pedid_deudo  sp
  SET
   sp.oid_peri_segu_pedi =  
      (SELECT cp.oid_peri
       FROM 
        seg_perio_corpo spc,
        cra_perio cp
       WHERE spc.oid_peri=cp.peri_oid_peri
         AND spc.cod_peri = sp.cod_peri_segu_pedi);
  
  UPDATE cob_repor_segun_pedid_deudo  sp
  SET
   (sp.imp_prim_pedi,  sp.imp_prim_pedi_pend ) =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                  AND cc.perd_oid_peri  =  sp.oid_peri_prim_pedi
                  AND cc.imp_movi > 0),
   (sp.imp_segu_pedi,  sp.imp_segu_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND   cc.perd_oid_peri  =  sp.oid_peri_segu_pedi
                 AND cc.imp_movi > 0),
    sp.imp_otro_carg =   NVL((SELECT SUM(mcc.imp_pend)
                              FROM ccc_movim_cuent_corri mcc
                              WHERE mcc.clie_oid_clie = sp.oid_clie
                              AND mcc.perd_oid_peri NOT IN (sp.oid_peri_prim_pedi,
                                                            sp.oid_peri_segu_pedi)
                              AND mcc.imp_pend > 0),0);                  
  

   DELETE FROM cob_repor_segun_pedid_deudo sp
   WHERE sp.imp_prim_pedi_pend <= 0
     AND sp.imp_segu_pedi_pend <= 0;
  
  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_SEGUN_PEDID_DEUDO: '||ls_sqlerrm);

 END COB_PR_REPOR_SEGUN_PEDID_DEUDO;

 PROCEDURE COB_PR_REPOR_TERCE_PEDID_DEUDO (
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 IS

  lv_oid_subp                    NUMBER;
  lv_oid_tipo_soli_pais          NUMBER;
      
 BEGIN
  
  SELECT sp.oid_subp
  INTO lv_oid_subp
  FROM
   ccc_proce pr,
   ccc_subpr sp
  WHERE sp.ccpr_oid_proc = pr.oid_proc
    AND pr.cod_proc = 'CCC001'
    AND sp.cod_subp = 1;

  --- Obtiene  oid   de tipo solicitud  pais
  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');
    
  DELETE FROM cob_repor_terce_pedid_deudo;
  
  INSERT INTO cob_repor_terce_pedid_deudo
   WITH
   temp1 AS (
     SELECT
      mce.oid_clie,
      spc.cod_peri cod_peri_prim_pedi,
      cp.oid_peri oid_peri_prim_pedi,
      mce.camp_ulti_pedi cod_peri_terc_pedi
     FROM 
      mae_clien_estat mce,
      cra_perio cp,
      seg_perio_corpo spc
     WHERE mce.camp_prim_pedi = spc.cod_peri
       AND spc.oid_peri = cp.peri_oid_peri
       AND mce.val_nume_pedi = 3
       AND mce.camp_prim_pedi >= p_cod_peri_inic
       AND mce.camp_prim_pedi <= p_cod_peri_fina)   
    SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_REGI'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_ZONA'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_SECC'),
     t1.oid_clie,
     mc.cod_clie,
     mc.val_nom1 || ' ' ||
     mc.val_nom2 || ' ' ||
     mc.val_ape1 || ' ' ||
     mc.val_ape2 nom_clie,
     t1.oid_peri_prim_pedi,
     t1.cod_peri_prim_pedi,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     t1.cod_peri_terc_pedi,
     NULL,
     NULL,
     NULL,
     mc.sal_deud_ante
    FROM
     temp1 t1,
     mae_clien mc
    WHERE t1.oid_clie = mc.oid_clie;

  UPDATE cob_repor_terce_pedid_deudo  sp
  SET sp.cod_peri_segu_pedi =
     (SELECT MAX(spc.cod_peri)
      FROM 
       ped_solic_cabec psc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE psc.clie_oid_clie = sp.oid_clie
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
        AND psc.grpr_oid_grup_proc = 5
        AND psc.fec_fact IS NOT NULL
        AND psc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND spc.cod_peri > sp.cod_peri_prim_pedi
        AND spc.cod_peri < sp.cod_peri_terc_pedi);
  
  UPDATE cob_repor_terce_pedid_deudo  sp
  SET
   sp.oid_peri_segu_pedi =  
      (SELECT cp.oid_peri
       FROM 
        seg_perio_corpo spc,
        cra_perio cp
       WHERE spc.oid_peri=cp.peri_oid_peri
         AND spc.cod_peri = sp.cod_peri_segu_pedi),
   sp.oid_peri_terc_pedi = 
      (SELECT cp.oid_peri
       FROM 
        seg_perio_corpo spc,
        cra_perio cp
       WHERE spc.oid_peri=cp.peri_oid_peri
         AND spc.cod_peri = sp.cod_peri_terc_pedi);
         
  UPDATE cob_repor_terce_pedid_deudo  sp
  SET   
   (sp.imp_prim_pedi,  sp.imp_prim_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                  AND cc.perd_oid_peri  =  sp.oid_peri_prim_pedi
                  AND cc.imp_movi > 0),  
   (sp.imp_segu_pedi,  sp.imp_segu_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND   cc.perd_oid_peri  =  sp.oid_peri_segu_pedi
                 AND cc.imp_movi > 0),    
   (sp.imp_terc_pedi,  sp.imp_terc_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND  cc.perd_oid_peri  =  sp.oid_peri_terc_pedi
                 AND cc.imp_movi > 0),
    sp.imp_otro_carg =   NVL((SELECT SUM(mcc.imp_pend)
                              FROM ccc_movim_cuent_corri mcc
                              WHERE mcc.clie_oid_clie = sp.oid_clie
                              AND mcc.perd_oid_peri NOT IN (sp.oid_peri_prim_pedi,
                                                            sp.oid_peri_segu_pedi,
                                                            sp.oid_peri_terc_pedi)
                              AND mcc.imp_pend > 0),0);

   DELETE FROM cob_repor_terce_pedid_deudo sp
   WHERE sp.imp_prim_pedi_pend <= 0
     AND sp.imp_segu_pedi_pend <= 0;
 
  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_TERCE_PEDID_DEUDO: '||ls_sqlerrm);

 END COB_PR_REPOR_TERCE_PEDID_DEUDO;

 PROCEDURE COB_PR_REPOR_CUART_PEDID_DEUDO (
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 IS

  lv_oid_tipo_soli_pais          ped_solic_cabec.oid_soli_cabe%TYPE;   

 BEGIN
  
  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');
    
  DELETE FROM cob_repor_cuart_pedid_deudo;
  
  INSERT INTO cob_repor_cuart_pedid_deudo
   WITH
    temp1 AS (
      SELECT
       mce.oid_clie,
       mce.camp_prim_pedi cod_peri_prim_pedi,
       cp.oid_peri        oid_peri_prim_pedi,
       mce.camp_ulti_pedi cod_peri_cuar_pedi
      FROM 
       mae_clien_estat mce,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE mce.camp_prim_pedi = spc.cod_peri
        AND spc.oid_peri = cp.peri_oid_peri
        AND mce.val_nume_pedi = 4
        AND mce.camp_prim_pedi >= p_cod_peri_inic
        AND mce.camp_prim_pedi <= p_cod_peri_fina)
    SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_REGI'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_ZONA'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_SECC'),
     t1.oid_clie,
     mc.cod_clie,
     mc.val_nom1 || ' ' ||
     mc.val_nom2 || ' ' ||
     mc.val_ape1 || ' ' ||
     mc.val_ape2 nom_clie,
     t1.oid_peri_prim_pedi,
     t1.cod_peri_prim_pedi,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     t1.cod_peri_cuar_pedi,
     NULL,
     NULL,
     NULL,
     mc.sal_deud_ante
    FROM
     temp1 t1,
     mae_clien mc
    WHERE t1.oid_clie = mc.oid_clie;

  UPDATE cob_repor_cuart_pedid_deudo  sp
  SET 
   sp.cod_peri_segu_pedi =
     (SELECT MIN(spc.cod_peri)
      FROM 
       ped_solic_cabec psc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE psc.clie_oid_clie = sp.oid_clie
        AND psc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
        AND psc.grpr_oid_grup_proc = 5
        AND psc.fec_fact IS NOT NULL
        AND spc.cod_peri > sp.cod_peri_prim_pedi
        AND spc.cod_peri < sp.cod_peri_cuar_pedi),
   sp.cod_peri_terc_pedi =
     (SELECT MAX(spc.cod_peri)
      FROM 
       ped_solic_cabec psc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE psc.clie_oid_clie = sp.oid_clie
        AND psc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
        AND psc.grpr_oid_grup_proc = 5
        AND psc.fec_fact IS NOT NULL
        AND spc.cod_peri > sp.cod_peri_prim_pedi
        AND spc.cod_peri < sp.cod_peri_cuar_pedi);
   
  UPDATE cob_repor_cuart_pedid_deudo  sp
  SET 
   sp.oid_peri_segu_pedi =
       (SELECT cp.oid_peri
        FROM 
         seg_perio_corpo spc,
         cra_perio cp
        WHERE spc.oid_peri=cp.peri_oid_peri
          AND spc.cod_peri = sp.cod_peri_segu_pedi), 
   sp.oid_peri_terc_pedi = 
       (SELECT cp.oid_peri
        FROM 
         seg_perio_corpo spc,
         cra_perio cp
        WHERE spc.oid_peri=cp.peri_oid_peri
          AND spc.cod_peri = sp.cod_peri_terc_pedi), 
   sp.oid_peri_cuar_pedi =  
        (SELECT cp.oid_peri
        FROM 
         seg_perio_corpo spc,
         cra_perio cp
        WHERE spc.oid_peri=cp.peri_oid_peri
          AND spc.cod_peri = sp.cod_peri_cuar_pedi);
            
  UPDATE cob_repor_cuart_pedid_deudo  sp
  SET  
   (sp.imp_prim_pedi,  sp.imp_prim_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                  AND cc.perd_oid_peri  =  sp.oid_peri_prim_pedi
                  AND cc.imp_movi > 0),
   (sp.imp_segu_pedi,  sp.imp_segu_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND   cc.perd_oid_peri  =  sp.oid_peri_segu_pedi
                 AND cc.imp_movi > 0),
   (sp.imp_terc_pedi,  sp.imp_terc_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND  cc.perd_oid_peri  =  sp.oid_peri_terc_pedi
                 AND cc.imp_movi > 0),
    (sp.imp_cuar_pedi,  sp.imp_cuar_pedi_pend )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.oid_clie
                 AND  cc.perd_oid_peri  =  sp.oid_peri_cuar_pedi
                 AND cc.imp_movi > 0),                 
    sp.imp_otro_carg =   NVL((SELECT SUM(mcc.imp_pend)
                              FROM ccc_movim_cuent_corri mcc
                              WHERE mcc.clie_oid_clie = sp.oid_clie
                              AND mcc.perd_oid_peri NOT IN (sp.oid_peri_prim_pedi,
                                                            sp.oid_peri_segu_pedi,
                                                            sp.oid_peri_terc_pedi,
                                                            sp.oid_peri_cuar_pedi)
                              AND mcc.imp_pend > 0),0);

   DELETE FROM cob_repor_cuart_pedid_deudo sp
   WHERE sp.imp_prim_pedi_pend <= 0
     AND sp.imp_segu_pedi_pend <= 0
     AND sp.imp_terc_pedi_pend <= 0;
 
  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_CUART_PEDID_DEUDO: '||ls_sqlerrm);

 END COB_PR_REPOR_CUART_PEDID_DEUDO;
 
 PROCEDURE COB_PR_REPOR_EGRES_SINDE(
  p_imp_deud                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT 0)
 IS

  lv_cod_peri_actu               seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  lv_cod_peri_actu := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

  DELETE FROM cob_repor_egres_sinde;

  INSERT INTO cob_repor_egres_sinde(
   cod_regi,
   cod_zona,
   cod_secc,
   cod_clie,
   nom_clie,
   val_tele,
   val_emai,
   val_camp_prim_pedi,
   val_camp_ulti_pedi,
   val_sald_tota)
  SELECT
   zr.cod_regi,
   zz.cod_zona,
   zs.cod_secc,
   mc.cod_clie,
   mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie,
   tf.val_text_comu || '/' || tt.val_text_comu || '/'|| tm.val_text_comu val_tele,
   ml.val_text_comu val_emai,
   mec.camp_prim_pedi val_camp_prim_pedi,
   mec.camp_ulti_pedi val_camp_ulti_pedi,
   mc.sal_deud_ante val_sald_tota
  FROM
   mae_clien_unida_admin mcua,
   mae_clien mc,
   mae_clien_datos_adici mcda,
   zon_terri_admin zta,
   gen_i18n_sicc_comun gen,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr,
   mae_clien_estat mec,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 1) tf,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 6) tt,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 7) tm, 
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 3) ml     
  WHERE mc.oid_clie = mcua.clie_oid_clie
    AND mc.oid_clie = mec.oid_clie(+)
    AND mc.oid_clie = tf.clie_oid_clie(+)
    AND mc.oid_clie = tt.clie_oid_clie(+)
    AND mc.oid_clie = tm.clie_oid_clie(+)
    AND mc.oid_clie = ml.clie_oid_clie(+)   
    AND mc.oid_clie = mcda.clie_oid_clie
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND mcda.esta_oid_esta_clie = gen.val_oid
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi
    AND mcua.ind_acti = 1
    AND mcda.esta_oid_esta_clie = 5
    AND gen.attr_enti = 'MAE_ESTAT_CLIEN'
    AND zz.ind_ofic IS NULL
    AND mc.sal_deud_ante <= TO_NUMBER(p_imp_deud,'9999999999.99')
    AND NOT EXISTS (
         SELECT NULL
         FROM 
          mae_clien_bloqu mcb
         WHERE mcb.clie_oid_clie = mc.oid_clie
           AND mcb.fec_desb IS NULL)
    AND NOT EXISTS (
         SELECT NULL
         FROM
          cob_detal_asign_carte dc,
          cob_etapa_deuda_pais ed
         WHERE dc.cod_etap_deud = ed.cod_etap_deud
           AND dc.oid_clie = mc.oid_clie
           AND ed.ind_cobr_exte = '1');

  DELETE FROM cob_repor_egres_sinde r
  WHERE r.val_camp_ulti_pedi = lv_cod_peri_actu;

 END COB_PR_REPOR_EGRES_SINDE;

 PROCEDURE COB_PR_REPOR_RETIR_SINDE(
  p_imp_deud                IN   seg_perio_corpo.cod_peri%TYPE DEFAULT 0)
 IS

  lv_cod_peri_actu               seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  lv_cod_peri_actu := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

  DELETE FROM cob_repor_retir_sinde;

  INSERT INTO cob_repor_retir_sinde(
   cod_regi,
   cod_zona,
   cod_secc,
   cod_clie,
   nom_clie,
   val_tele,
   val_emai,
   val_camp_prim_pedi,
   val_camp_ulti_pedi,
   val_sald_tota)
  SELECT
   zr.cod_regi,
   zz.cod_zona,
   zs.cod_secc,
   mc.cod_clie,
   mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie,
   tf.val_text_comu || '/' || tt.val_text_comu || '/'|| tm.val_text_comu val_tele,
   ml.val_text_comu val_emai,
   mec.camp_prim_pedi val_camp_prim_pedi,
   mec.camp_ulti_pedi val_camp_ulti_pedi,
   mc.sal_deud_ante val_sald_tota
  FROM
   mae_clien_unida_admin mcua,
   mae_clien mc,
   mae_clien_datos_adici mcda,
   zon_terri_admin zta,
   gen_i18n_sicc_comun gen,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr,
   mae_clien_estat mec,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 1) tf,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 6) tt,
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 7) tm, 
   (SELECT mcc.clie_oid_clie, mcc.val_text_comu
    FROM mae_clien_comun mcc
    WHERE mcc.ticm_oid_tipo_comu = 3) ml     
  WHERE mc.oid_clie = mcua.clie_oid_clie
    AND mc.oid_clie = mec.oid_clie(+)
    AND mc.oid_clie = tf.clie_oid_clie(+)
    AND mc.oid_clie = tt.clie_oid_clie(+)
    AND mc.oid_clie = tm.clie_oid_clie(+)
    AND mc.oid_clie = ml.clie_oid_clie(+)   
    AND mc.oid_clie = mcda.clie_oid_clie
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND mcda.esta_oid_esta_clie = gen.val_oid
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi
    AND mcua.ind_acti = 1
    AND mcda.esta_oid_esta_clie = 7
    AND gen.attr_enti = 'MAE_ESTAT_CLIEN'
    AND zz.ind_ofic IS NULL
    AND mc.sal_deud_ante <= TO_NUMBER(p_imp_deud,'9999999999.99')
    AND NOT EXISTS (
         SELECT NULL
         FROM 
          mae_clien_bloqu mcb
         WHERE mcb.clie_oid_clie = mc.oid_clie
           AND mcb.fec_desb IS NULL)
    AND NOT EXISTS (
         SELECT NULL
         FROM
          cob_detal_asign_carte dc,
          cob_etapa_deuda_pais ed
         WHERE dc.cod_etap_deud = ed.cod_etap_deud
           AND dc.oid_clie = mc.oid_clie
           AND ed.ind_cobr_exte = '1');

  DELETE FROM cob_repor_retir_sinde r
  WHERE r.val_camp_ulti_pedi = lv_cod_peri_actu;

 END COB_PR_REPOR_RETIR_SINDE;

  /************************************************************************************
  Descripcion           : Genera Reporte COB Detallado Movimiento Recuperacion Incobrable en formato CSV
  Fecha Creacion    : 18/02/2014
  Autor                    : Sebastian Guerra
  ************************************************************************************/
 PROCEDURE COB_PR_REPOR_MOVIM_RECUP_INCOB(
  pscodigopais                   IN   VARCHAR2,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  pscodigousuario                IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2)
 IS
      lsdirtempo      bas_inter.dir_temp%TYPE;
      v_handle       utl_file.file_type;
      lslinea            varchar2(4000);

      CURSOR C_REPOR_MOVIM_RECUP_INCOB IS
      SELECT
          MRI.NUM_DOCU_IDEN,
          MRI.DES_SUBP,
          MRI.VAL_NOMB,
          MRI.VAL_DIRE,
          MRI.VAL_DPTO,
          MRI.VAL_PROV,
          MRI.MNT_VALOR,
          MRI.NUM_DIAS_VENC,
          MRI.NUM_DIAS_FACT,
          MRI.OID_MOVI_CC,
          (SELECT DES_REGI FROM ZON_REGIO WHERE COD_REGI = MRI.COD_REGI) COD_REGI,
          (SELECT DES_ZONA FROM ZON_ZONA WHERE COD_ZONA = MRI.COD_ZONA) COD_ZONA,
          MRI.COD_PERI,
          TO_CHAR(MRI.FEC_DOCU, 'DD/MM/YYYY') FEC_DOCU,
          MRI.IMP_MOVI
       FROM COB_TMP_MOVIM_RECUP_INCOB MRI
       WHERE MRI.USU_DIGI = pscodigousuario
       ORDER BY MRI.FEC_DOCU;

       TYPE detalleMovRecInc IS RECORD(
          cedula                   COB_TMP_MOVIM_RECUP_INCOB.NUM_DOCU_IDEN%TYPE,
          proceso                  COB_TMP_MOVIM_RECUP_INCOB.DES_SUBP%TYPE,
          nombres                  COB_TMP_MOVIM_RECUP_INCOB.VAL_NOMB%TYPE,
          direccion                COB_TMP_MOVIM_RECUP_INCOB.VAL_DIRE%TYPE,
          departamento             COB_TMP_MOVIM_RECUP_INCOB.VAL_DPTO%TYPE,
          municipio                COB_TMP_MOVIM_RECUP_INCOB.VAL_PROV%TYPE,
          valor                    COB_TMP_MOVIM_RECUP_INCOB.MNT_VALOR%TYPE,
          diasVencidos             COB_TMP_MOVIM_RECUP_INCOB.NUM_DIAS_VENC%TYPE,
          diasFacturados           COB_TMP_MOVIM_RECUP_INCOB.NUM_DIAS_FACT%TYPE,
          numDocumento             COB_TMP_MOVIM_RECUP_INCOB.OID_MOVI_CC%TYPE,
          region                   VARCHAR2(40),
          zona                     VARCHAR2(40),
          campania                 COB_TMP_MOVIM_RECUP_INCOB.COD_PERI%TYPE,
          fechaDocumento           VARCHAR2(10),
          valorRecuperado          COB_TMP_MOVIM_RECUP_INCOB.IMP_MOVI%TYPE
        );

       TYPE detalleMovRecIncTab IS TABLE OF detalleMovRecInc;
       detalleMovRecIncRecord detalleMovRecIncTab;

       lbAbrirUtlFile  BOOLEAN;

  BEGIN

      lbAbrirUtlFile := true;
      EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
      OPEN C_REPOR_MOVIM_RECUP_INCOB;
        LOOP
         FETCH C_REPOR_MOVIM_RECUP_INCOB BULK COLLECT INTO detalleMovRecIncRecord LIMIT w_filas;
         IF lbAbrirUtlFile THEN
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
            psdirectorio := lsdirtempo;
            lbAbrirUtlFile := FALSE;
         END IF ;
         IF detalleMovRecIncRecord.COUNT > 0 THEN
            FOR x IN detalleMovRecIncRecord.FIRST .. detalleMovRecIncRecord.LAST LOOP
                  lslinea :=     '=T("'|| detalleMovRecIncRecord(x).cedula || '")' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).proceso || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).nombres || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).direccion || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).departamento || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).municipio || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).valor || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).diasVencidos || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).diasFacturados || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).numDocumento || '"' || ',' ||
                                  '=T("'|| detalleMovRecIncRecord(x).region || '")' || ',' ||
                                  '=T("'|| detalleMovRecIncRecord(x).zona || '")' || ',' ||
                                  '=T("'|| detalleMovRecIncRecord(x).campania || '")' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).fechaDocumento || '"' || ',' ||
                                  '"'|| detalleMovRecIncRecord(x).valorRecuperado || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MOVIM_RECUP_INCOB%NOTFOUND;
       END LOOP;
      CLOSE C_REPOR_MOVIM_RECUP_INCOB;

       IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(v_handle);
      END IF;

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_MOVIM_RECUP_INCOB: '||ls_sqlerrm);

  END COB_PR_REPOR_MOVIM_RECUP_INCOB;

  /************************************************************************************
  Descripcion           : Genera la data para todos los Reportes de FFVV - FTP
  Fecha Creacion    : 11/04/2014
  Autor                    : Aurelio Oviedo
  ************************************************************************************/
 PROCEDURE COB_PR_REPOR_FFVV_FTP(
  psCodigoUsuario                IN   VARCHAR2)
 IS

    lv_num_dias_inic                 NUMBER(12);
    lv_num_dias_fina                 NUMBER(12);

    CURSOR c_regi IS
        SELECT spc.cod_peri,
                    zr.cod_regi,
                    dv.val_emai
           FROM cra_crono cr,
                    cra_activ ca,
                    cra_perio cp,
                    seg_perio_corpo spc,
                    zon_zona zz,
                    zon_regio zr,
                   (SELECT zr.cod_regi, mcc.val_text_comu val_emai
                      FROM zon_regio zr,
                                mae_clien mc,
                                mae_clien_comun mcc,
                                mae_tipo_comun mtc
                     WHERE zr.clie_oid_clie = mc.oid_clie
                         AND mc.oid_clie = mcc.clie_oid_clie
                         AND mtc.oid_tipo_comu = mcc.ticm_oid_tipo_comu
                         AND mtc.cod_tipo_comu = 'ML') dv
         WHERE cr.cact_oid_acti = ca.oid_acti
             AND cr.zzon_oid_zona = zz.oid_zona
             AND zz.zorg_oid_regi = zr.oid_regi
             AND cr.perd_oid_peri = cp.oid_peri
             AND cp.peri_oid_peri = spc.oid_peri
             AND ca.cod_acti = 'FA'
             AND zr.cod_regi = dv.cod_regi
        HAVING TRUNC(SYSDATE) - MAX(cr.fec_inic) >= lv_num_dias_inic
             AND TRUNC(SYSDATE) - MAX(cr.fec_inic) <= lv_num_dias_fina
         GROUP BY  spc.cod_peri, zr.cod_regi, dv.val_emai
         ORDER BY 1 ASC;

    CURSOR c_zona IS
        SELECT spc.cod_peri,
                    zr.cod_regi,
                    zz.cod_zona,
                    dv.val_emai
           FROM cra_crono cr,
                    cra_activ ca,
                    cra_perio cp,
                    seg_perio_corpo spc,
                    zon_zona zz,
                    zon_regio zr,
                   (SELECT zr.cod_regi, zz.cod_zona,mc.cod_clie, mcc.val_text_comu val_emai
                       FROM zon_regio zr,
                              zon_zona zz,
                              mae_clien mc,
                              mae_clien_comun mcc,
                              mae_tipo_comun mtc
                   WHERE zz.clie_oid_clie = mc.oid_clie
                       AND mc.oid_clie = mcc.clie_oid_clie
                       AND mtc.oid_tipo_comu = mcc.ticm_oid_tipo_comu
                       AND mtc.cod_tipo_comu = 'ML'
                       AND zr.oid_regi = zz.zorg_oid_regi) dv
            WHERE cr.cact_oid_acti = ca.oid_acti
                 AND cr.zzon_oid_zona = zz.oid_zona
                 AND zz.zorg_oid_regi = zr.oid_regi
                 AND cr.perd_oid_peri = cp.oid_peri
                 AND cp.peri_oid_peri = spc.oid_peri
                 AND zr.cod_regi = dv.cod_regi
                 AND zz.cod_zona = dv.cod_zona
                 AND ca.cod_acti = 'FA'
                 AND zr.cod_regi = dv.cod_regi
            HAVING TRUNC(SYSDATE) - MAX(cr.fec_inic) >= lv_num_dias_inic
                 AND TRUNC(SYSDATE) - MAX(cr.fec_inic) <= lv_num_dias_fina
             GROUP BY  spc.cod_peri, zr.cod_regi, zz.cod_zona, dv.val_emai
             ORDER BY 1 ASC;

    TYPE detalleRegion IS RECORD(
        codPeriR                SEG_PERIO_CORPO.COD_PERI%TYPE,
        codRegiR                ZON_REGIO.COD_REGI%TYPE,
        valEmailR               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE
    );

    TYPE detalleZona IS RECORD(
        codPeriZ                SEG_PERIO_CORPO.COD_PERI%TYPE,
        codRegiZ                ZON_REGIO.COD_REGI%TYPE,
        codZonaZ               ZON_ZONA.COD_ZONA%TYPE,
        valEmailZ               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE
    );

    TYPE detalleRegionTab IS TABLE OF detalleRegion;
       detalleRegionRecord detalleRegionTab;

    TYPE detalleZonaTab IS TABLE OF detalleZona;
       detalleZonaRecord detalleZonaTab;

    BEGIN

        lv_num_dias_inic := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('NumeroDiasInicioReporteFFVV');
        lv_num_dias_fina := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('NumeroDiasFinReporteFFVV');

        DELETE COB_TEMPO_FFVV_REGIA WHERE COD_USUA = psCodigoUsuario;
        DELETE COB_TEMPO_FFVV_REGIB WHERE COD_USUA = psCodigoUsuario;

        OPEN c_regi;
        LOOP
            FETCH c_regi BULK COLLECT INTO detalleRegionRecord LIMIT w_filas;
            IF detalleRegionRecord.COUNT > 0 THEN
                FOR x IN detalleRegionRecord.FIRST .. detalleRegionRecord.LAST LOOP

                    --Inserta los datos para el PRIMER CUADRO del Reporte REGION
                    INSERT INTO COB_TEMPO_FFVV_REGIA (COD_PERI, IMP_FACT_NETO, COB_31, PORC_RECU, FEC_INIC_CIER, COD_REGI, COD_USUA)
                    (SELECT c.cod_peri,
                                SUM(c.imp_fact_neto) imp_fact_neto,
                                SUM(c.cob_dias_31) cob_31,
                                 CASE
                                    WHEN SUM(c.imp_fact_neto) > 0 THEN
                                    ROUND((SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100),2)
                                    ELSE 0
                                 END porc_recu,
                                 MIN(c.fec_cier_31) fec_inic_cier,
                                 detalleRegionRecord(x).codRegiR,
                                 psCodigoUsuario
                       FROM cob_repor_estad_recup_cobra c
                     WHERE c.cod_peri = detalleRegionRecord(x).codPeriR
                          AND c.cod_regi = detalleRegionRecord(x).codRegiR
                      GROUP BY c.cod_peri, c.cod_regi);

                    --Inserta los datos para el SEGUNDO CUADRO del Reporte REGION
                    INSERT INTO COB_TEMPO_FFVV_REGIB(COD_PERI, COD_ZONA, IMP_FACT_NETO, COB_31, PORC_RECU, IMP_SALD_PEND, FEC_INIC_CIER, COD_REGI, COD_USUA)
                    (SELECT c.cod_peri,
                                c.cod_zona,
                                SUM(c.imp_fact_neto) imp_fact_neto,
                                SUM(c.cob_dias_31) cob_31,
                                CASE
                                 WHEN SUM(c.imp_fact_neto) > 0 THEN
                                  ROUND((SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100),2)
                                 ELSE 0
                                END porc_recu,
                                SUM(c.imp_sald_pend_sac) imp_sald_pend,
                                MIN(c.fec_cier_31) fec_inic_cier,
                                detalleRegionRecord(x).codRegiR,
                                psCodigoUsuario
                               FROM cob_repor_estad_recup_cobra c
                             WHERE c.cod_peri = detalleRegionRecord(x).codPeriR
                                  AND c.cod_regi = detalleRegionRecord(x).codRegiR
                               GROUP BY c.cod_peri, c.des_regi, c.cod_zona);

                END LOOP;
            END IF;

            EXIT WHEN c_regi%NOTFOUND;
        END LOOP;
        CLOSE c_regi;

        DELETE COB_TEMPO_FFVV_ZONAA WHERE COD_USUA = psCodigoUsuario;
        DELETE COB_TEMPO_FFVV_ZONAB WHERE COD_USUA = psCodigoUsuario;
        DELETE COB_TEMPO_FFVV_ZONAC WHERE COD_USUA = psCodigoUsuario;
        DELETE COB_TEMPO_FFVV_ZONA_DEUDO WHERE COD_USUA = psCodigoUsuario;
        DELETE COB_TEMPO_FFVV_ZONA_TOTAL WHERE COD_USUA = psCodigoUsuario;

        OPEN c_zona;
        LOOP
            FETCH c_zona BULK COLLECT INTO detalleZonaRecord LIMIT w_filas;
            IF detalleZonaRecord.COUNT > 0 THEN
                FOR x IN detalleZonaRecord.FIRST .. detalleZonaRecord.LAST LOOP

                    --Inserta los datos para el PRIMER CUADRO del Reporte ZONA
                    INSERT INTO COB_TEMPO_FFVV_ZONAA(COD_PERI, IMP_FACT_NETO, COB_31, PORC_RECU, SALDO, FEC_INIC_CIER, COD_REGI, COD_ZONA, COD_USUA)
                    (SELECT c.cod_peri,
                               SUM(c.imp_fact_neto) imp_fact_neto,
                               SUM(c.cob_dias_31) cob_31,
                               CASE
                                WHEN SUM(c.imp_fact_neto) > 0 THEN
                                 ROUND(SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100,2)
                                ELSE 0
                               END porc_recu,
                               SUM(c.imp_sald_pend_sac) saldo,
                               MIN(c.fec_cier_31) fec_inic_cier,
                               detalleZonaRecord(x).codRegiZ,
                               detalleZonaRecord(x).codZonaZ,
                               psCodigoUsuario
                              FROM cob_repor_estad_recup_cobra c
                            WHERE c.cod_peri = detalleZonaRecord(x).codPeriZ
                                 AND c.cod_regi = detalleZonaRecord(x).codRegiZ
                                 AND c.cod_zona = detalleZonaRecord(x).codZonaZ
                             GROUP BY c.cod_peri,c.cod_regi,c.cod_zona);

                    --Inserta los datos para el SEGUNDO CUADRO del Reporte ZONA
                    INSERT INTO COB_TEMPO_FFVV_ZONAB(COD_PERI, COD_ZONA, COD_SECC, IMP_FACT_NETO, COB_31, PORC_RECU, SALDO, FEC_INIC_CIER, COD_REGI, COD_USUA)
                    (SELECT c.cod_peri,
                               c.cod_zona,
                               c.cod_secc,
                               SUM(c.imp_fact_neto) imp_fact_neto,
                               SUM(c.cob_dias_31) cob_31,
                               CASE
                                WHEN SUM(c.imp_fact_neto) > 0 THEN
                                 ROUND(SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100,2)
                                ELSE 0
                               END porc_recu,
                               SUM(c.imp_sald_pend_sac) saldo,
                               MIN(c.fec_cier_31) fec_inic_cier,
                               detalleZonaRecord(x).codRegiZ,
                               psCodigoUsuario
                              FROM cob_repor_estad_recup_cobra c
                            WHERE c.cod_peri = detalleZonaRecord(x).codPeriZ
                                 AND c.cod_zona = detalleZonaRecord(x).codZonaZ
                              GROUP BY c.cod_peri,c.cod_zona,c.cod_secc);

                    --Inserta los datos para el TERCER CUADRO del Reporte ZONA
                    INSERT INTO COB_TEMPO_FFVV_ZONAC(COD_PERI, COD_ZONA, COD_SECC, IMP_FACT_NETO, COB_EJEC, PORC_RECU, SALDO, COD_REGI, COD_USUA)
                    (SELECT detalleZonaRecord(x).codPeriZ,
                               SUBSTR(ej.cod_secc,1,4) cod_zona,
                               SUBSTR(ej.cod_secc,5,1) cod_secc,
                               CASE
                                WHEN ej.val_porc_reca > 0 THEN
                                 ROUND((ej.val_mont_reca*100/ej.val_porc_reca))
                                ELSE 0
                               END imp_fact_neto,
                               ej.val_mont_reca cob_ejec,
                               ej.val_porc_reca porc_recu,
                               CASE
                                WHEN ej.val_porc_reca > 0 THEN
                                 (ROUND((ej.val_mont_reca*100/ej.val_porc_reca)) - ej.val_mont_reca)
                                ELSE 0
                               END saldo,
                               detalleZonaRecord(x).codRegiZ,
                               psCodigoUsuario
                              FROM eje_evalu_secci_campa ej
                            WHERE ej.cam_proc = detalleZonaRecord(x).codPeriZ
                                AND SUBSTR(ej.cod_secc,1,4) = detalleZonaRecord(x).codZonaZ
                                AND ej.val_porc_reca > 0);

                    --Inserta los datos para el Reporte de DEUDORAS
                    INSERT INTO COB_TEMPO_FFVV_ZONA_DEUDO(COD_SECC, NOM_CLIE, NUM_DOCU_IDEN, VAL_DIRE, VAL_TELE, VAL_ESTA, IMP_FACT_NETO,
                                                                                          COB_31, IMP_SALD_PEND, POR_RECU, FEC_CARG, FEC_ULTI_ACTU, NUM_DIAS_GEST,
                                                                                          ULTI_FEC_PAGO, FACT_CAMP_ACTU, CONS_EXCL, COD_ZONA, COD_USUA)
                    (SELECT
                             cod_secc,
                             nom_clie,
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
                             FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') || '-' ||
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') || '-' ||
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele,
                             FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
                             SUM(imp_fact_neto) imp_fact_neto,
                             SUM(cob_dias_31) cob_dias_31,
                             SUM(imp_sald_pend_sac) imp_sald_pend,
                             CASE
                              WHEN SUM(imp_fact_neto) > 0 THEN
                               ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2)
                              ELSE 0
                             END por_recu,
                             MAX(fec_docu) fec_carg,
                             SYSDATE fec_ulti_actu,
                             (TRUNC(SYSDATE) - MAX(fec_docu)) num_dias_gest,
                             MAX(TRUNC(fec_ulti_movi))  ulti_fec_pago,
                             'Si' fact_camp_actu,
                             'No' cons_excl,
                             detalleZonaRecord(x).codZonaZ,
                             psCodigoUsuario
                            FROM ( SELECT
                             est.oid_peri,
                             est.cod_peri,
                             est.des_regi,
                             est.des_zona,
                             est.cod_secc,
                             mc.oid_clie,
                             mc.cod_clie,
                             TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
                             est.imp_fact_neto,
                             est.cob_dias_31,
                             est.imp_sald_pend_sac,
                             est.fec_cier_vent,
                             mcc.fec_docu,
                            mcc.fec_ulti_movi
                            FROM
                             cob_repor_estad_recup_cobra est,
                             ccc_movim_cuent_corri mcc,
                             mae_clien mc
                            WHERE est.oid_clie=mc.oid_clie
                              AND est.oid_movi_cc = mcc.oid_movi_cc
                              AND est.cod_peri = detalleZonaRecord(x).codPeriZ
                              AND est.imp_sald_pend_sac >= TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('ImporteDeudaReporteFFVV'))--p_mont_deud_desd
                              AND est.cod_regi = detalleZonaRecord(x).codRegiZ--p_cod_regi
                              AND est.cod_zona = detalleZonaRecord(x).codZonaZ)TEMP1
                            GROUP BY
                             oid_peri,
                             cod_peri,
                             des_regi,
                             des_zona,
                             cod_secc,
                             oid_clie,
                             cod_clie,
                             nom_clie);

                    --Inserta los datos para el Reporte de TOTALES
                    INSERT INTO COB_TEMPO_FFVV_ZONA_TOTAL(COD_SECC, NOM_CLIE, NUM_DOCU_IDEN, VAL_DIRE, VAL_TELE, VAL_ESTA, IMP_FACT_NETO,
                                                                                          COB_31, IMP_SALD_PEND, POR_RECU, FEC_CARG, FEC_ULTI_ACTU, NUM_DIAS_GEST,
                                                                                          ULTI_FEC_PAGO, FACT_CAMP_ACTU, CONS_EXCL, COD_ZONA, COD_USUA)
                    (SELECT
                             cod_secc,
                             nom_clie,
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
                             FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') || '-' ||
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') || '-' ||
                             FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele,
                             FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
                             SUM(imp_fact_neto) imp_fact_neto,
                             SUM(cob_dias_31) cob_dias_31,
                             SUM(imp_sald_pend_sac) imp_sald_pend,
                             CASE
                              WHEN SUM(imp_fact_neto) > 0 THEN
                               ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2)
                              ELSE 0
                             END por_recu,
                             MAX(fec_docu) fec_carg,
                             SYSDATE fec_ulti_actu,
                             (TRUNC(SYSDATE) - MAX(fec_docu)) num_dias_gest,
                             MAX(TRUNC(fec_ulti_movi)) ulti_fec_pago,
                             'Si' fact_camp_actu,
                             'No' cons_excl,
                             detalleZonaRecord(x).codZonaZ,
                             psCodigoUsuario
                            FROM (SELECT
                             est.oid_peri,
                             est.cod_peri,
                             est.des_regi,
                             est.des_zona,
                             est.cod_secc,
                             mc.oid_clie,
                             mc.cod_clie,
                             TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
                             est.imp_fact_neto,
                             est.cob_dias_31,
                             est.imp_sald_pend_sac,
                             est.fec_cier_vent,
                             mcc.fec_docu,
                             mcc.fec_ulti_movi
                            FROM
                             cob_repor_estad_recup_cobra est,
                            ccc_movim_cuent_corri mcc,
                             mae_clien mc
                            WHERE est.oid_clie=mc.oid_clie
                              AND est.oid_movi_cc = mcc.oid_movi_cc
                              AND est.cod_peri = detalleZonaRecord(x).codPeriZ--p_cod_peri
                              AND est.cod_regi = detalleZonaRecord(x).codRegiZ--p_cod_regi
                              AND est.cod_zona = detalleZonaRecord(x).codZonaZ) TEMP1
                            GROUP BY
                             oid_peri,
                             cod_peri,
                             des_regi,
                             des_zona,
                             cod_secc,
                             oid_clie,
                             cod_clie,
                             nom_clie);

                END LOOP;
            END IF;

            EXIT WHEN c_zona%NOTFOUND;
        END LOOP;
        CLOSE c_zona;

    EXCEPTION
        WHEN OTHERS THEN
            ln_sqlcode := sqlcode;
            ls_sqlerrm := substr(sqlerrm,1,250);
            RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_FFVV_FTP: '||ls_sqlerrm);
    END COB_PR_REPOR_FFVV_FTP;

 PROCEDURE COB_PR_GENER_REPOR_COMIS_ABOGA(
  p_fec_inic                     IN   VARCHAR2,
  p_fec_fina                     IN   VARCHAR2)
 IS

 CURSOR c_etap_cobr_exte
 IS
  SELECT ed.cod_etap_deud
  FROM cob_etapa_deuda_pais ed
  WHERE ed.ind_cobr_exte = '1'
  ORDER BY ed.num_secu_etap DESC;

 BEGIN

  DELETE FROM cob_repor_comis_aboga;

  INSERT INTO cob_repor_comis_aboga
   SELECT
    mb.clie_oid_clie,
    mb.oid_movi_banc,
    NULL,
    mb.imp_apli_cobr_exte,
    0,
    NULL,
    NULL,
    NULL,
    NULL
   FROM ccc_movim_banca mb
   WHERE mb.imp_apli_cobr_exte > 0
    AND mb.fec_proc >= TO_DATE(p_fec_inic,'DD/MM/YYYY')
    AND mb.fec_proc <= TO_DATE(p_fec_fina,'DD/MM/YYYY')
    AND EXISTS (
        SELECT NULL
        FROM
         ccc_aplic_abono_cargo cad,
         cob_detal_movim_carte dm,
         cob_etapa_deuda_pais ed
        WHERE cad.cmba_oid_movi_banc = mb.oid_movi_banc
          AND cad.mvcc_oid_movi_carg = dm.mvcc_oid_movi_cc
          AND dm.cod_etap_deud = ed.cod_etap_deud
          AND ed.ind_cobr_exte = '1'
          AND dm.fec_cier >= mb.fec_pago);

  FOR v_etap_cobr_exte IN c_etap_cobr_exte LOOP

   UPDATE cob_repor_comis_aboga ca
   SET ca.oid_movi_cc =
       (SELECT MIN(cad.mvcc_oid_movi_carg)
        FROM
         ccc_aplic_abono_cargo cad,
         cob_detal_movim_carte dm
        WHERE cad.cmba_oid_movi_banc = ca.oid_movi_banc
          AND cad.mvcc_oid_movi_carg = dm.mvcc_oid_movi_cc
          AND dm.cod_etap_deud = v_etap_cobr_exte.cod_etap_deud)
   WHERE ca.oid_movi_cc IS NULL;

   UPDATE cob_repor_comis_aboga ca
   SET ca.cod_cart =
                  (SELECT MIN(dm.cod_cart)
                   FROM
                    cob_detal_movim_carte dm
                   WHERE dm.mvcc_oid_movi_cc = ca.oid_movi_cc
                     AND dm.cod_etap_deud = v_etap_cobr_exte.cod_etap_deud)
   WHERE ca.cod_cart IS NULL;

  END LOOP;

  UPDATE cob_repor_comis_aboga ca
  SET ca.cod_peri = (
                      SELECT c.cod_peri
                      FROM cob_cabec_asign_carte c
                      WHERE c.cod_cart = ca.cod_cart),
       ca.cod_etap_deud = (
                      SELECT c.cod_etap_deud
                      FROM cob_cabec_asign_carte c
                      WHERE c.cod_cart = ca.cod_cart),
       ca.cod_cobr = (
                      SELECT MIN(d.cod_usua_cobr)
                      FROM cob_detal_asign_carte d
                      WHERE d.cod_cart = ca.cod_cart
                      AND d.oid_clie = ca.clie_oid_clie);

  DELETE FROM cob_repor_comis_aboga ca
  WHERE EXISTS (
   SELECT NULL
   FROM cob_usuar_cobra_pais u
   WHERE u.cod_usua_cobr = ca.cod_cobr
   AND u.ind_supe = 1);

 END COB_PR_GENER_REPOR_COMIS_ABOGA;
 
 PROCEDURE COB_PR_GENER_COBRA_FFVV(
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE DEFAULT NULL)
 IS
 BEGIN
 
  DELETE FROM cob_repor_cobra_ffvv_consu;
  
  DELETE FROM cob_repor_cobra_ffvv_zona;
  
  INSERT INTO cob_repor_cobra_ffvv_consu
   SELECT 
    cbz.cod_peri,
    cbz.cod_regi,
    cbz.cod_zona,
    cbz.cod_secc,
    mc.oid_clie,
    mc.cod_clie,
    mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2,
    FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(mc.oid_clie),
    FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mc.oid_clie,'TF'),
    NULL,
    SUM(cbz.imp_fact_neto),
    SUM(cbz.cob_dias_21),
    MIN(cbz.fec_cier_21),
    CASE
     WHEN SUM(cbz.imp_fact_neto) > 0 THEN  
      ROUND(SUM(cbz.cob_dias_21)*100/SUM(cbz.imp_fact_neto),2)
     ELSE
      0  
    END,
    SUM(cbz.cob_dias_31),
    CASE
     WHEN SUM(cbz.imp_fact_neto) > 0 THEN  
      ROUND(SUM(cbz.cob_dias_31)*100/SUM(cbz.imp_fact_neto),2)
     ELSE 
       0
    END,
    MIN(cbz.fec_cier_31),
    SUM(cbz.imp_sald_pend_sac)
   FROM 
    cob_repor_estad_recup_cobra cbz,
    mae_clien mc 
   WHERE mc.oid_clie = cbz.oid_clie
     AND cbz.cod_peri = p_cod_peri
     AND cbz.cod_regi = p_cod_regi
   GROUP BY 
    cbz.cod_peri,
    cbz.cod_regi,
    cbz.cod_zona,
    cbz.cod_secc,
    mc.oid_clie,
    mc.cod_clie,
    mc.val_nom1,
    mc.val_nom2,
    mc.val_ape1,
    mc.val_ape2;
 
  INSERT INTO cob_repor_cobra_ffvv_zona
   SELECT 
    cbz.cod_peri, --cod_peri  varchar2(6)
    cbz.cod_regi, --cod_regi  varchar2(2)
    (SELECT zr.clie_oid_clie
     FROM zon_regio zr
     WHERE zr.cod_regi = cbz.cod_regi),--cbz.oid_clie_regi, --oid_clie_regi  number(12)
    (SELECT mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2
     FROM 
      zon_regio zr,
      mae_clien mc
     WHERE zr.cod_regi = cbz.cod_regi
       AND zr.clie_oid_clie = mc.oid_clie),--cbz.nom_gere_regi  varchar2(200)
    (SELECT co.val_text_comu
     FROM 
      zon_regio zr,
      mae_clien_comun co,
      mae_tipo_comun mtc
     WHERE zr.clie_oid_clie = co.clie_oid_clie
       AND zr.cod_regi = cbz.cod_regi
       AND co.ticm_oid_tipo_comu = mtc.oid_tipo_comu
       AND mtc.cod_tipo_comu = 'ML'),--val_mail_gere_regi  varchar2(50)
    cbz.cod_zona, --cod_zona  varchar2(4)
    (SELECT zz.clie_oid_clie
     FROM 
      zon_regio zr,
      zon_zona zz
     WHERE zr.cod_regi = cbz.cod_regi
       AND zz.cod_zona = cbz.cod_zona
       AND zr.oid_regi = zz.zorg_oid_regi), --oid_clie_zona  number(12)
    (SELECT mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2
     FROM 
      zon_regio zr,
      mae_clien mc,
      zon_zona zz
     WHERE zr.cod_regi = cbz.cod_regi
       AND zr.oid_regi = zz.zorg_oid_regi
       AND zz.cod_zona = cbz.cod_zona
       AND zz.clie_oid_clie = mc.oid_clie), --nom_gere_zona  varchar2(200)
    (SELECT co.val_text_comu
     FROM 
      zon_regio zr,
      zon_zona zz,
      mae_clien_comun co,
      mae_tipo_comun mtc
     WHERE zz.clie_oid_clie = co.clie_oid_clie
       AND zr.cod_regi = cbz.cod_regi
       AND zr.oid_regi = zz.zorg_oid_regi
       AND zz.cod_zona = cbz.cod_zona
       AND co.ticm_oid_tipo_comu = mtc.oid_tipo_comu
       AND mtc.cod_tipo_comu = 'ML'), --val_mail_gere_zona  varchar2(50)
    SUM(cbz.imp_fact_neto), --imp_fact_neto  number(15,2)
    SUM(cbz.cob_dias_21), --imp_cobr_21  number(15,2)
    MAX(cbz.fec_cier_21), --fec_cier_21  date
    CASE
     WHEN SUM(cbz.imp_fact_neto) > 0 THEN
      ROUND((SUM(cbz.cob_dias_21)*100/SUM(cbz.imp_fact_neto)),2) --imp_porc_recu_21  number(5,2)
     ELSE
      0  
    END,
    SUM(cbz.cob_dias_31), --imp_cobr_31  number(15,2) 
    CASE
     WHEN SUM(cbz.imp_fact_neto) > 0 THEN  
      ROUND((SUM(cbz.cob_dias_31)*100/SUM(cbz.imp_fact_neto)),2) --imp_porc_recu_31  number(5,2)
    ELSE
     0  
    END,
    MAX(cbz.fec_cier_31), --fec_cier_31  date
    SUM(cbz.imp_sald_pend_sac) --imp_sald_pend  number(15,2)
   FROM cob_repor_estad_recup_cobra cbz
   WHERE cbz.cod_peri = p_cod_peri 
     AND cbz.cod_regi = p_cod_regi
   GROUP BY 
    cbz.cod_peri,
    cbz.cod_regi,
    cbz.cod_zona;
   
  UPDATE cob_repor_cobra_ffvv_zona ff
  SET
   ff.nom_gere_zona = ff.nom_gere_regi,
   ff.val_mail_gere_zona = ff.val_mail_gere_regi
  WHERE ff.oid_clie_zona IS NULL;
  
  UPDATE cob_repor_cobra_ffvv_zona ff
  SET
   ff.val_mail_gere_zona = ff.val_mail_gere_regi
  WHERE ff.val_mail_gere_zona IS NULL;
    
 END COB_PR_GENER_COBRA_FFVV;
 
 
/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte cobranza FFVV
Fecha Creacion    : 03/03/2015
Autor             : Ivan Tocto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psTitulo : Cabecera del archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE COB_PR_GENER_REPOR_COBRA_FFVV(
    psCodigoPais            VARCHAR2,
    psCodigoPeriodo         VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);

  CURSOR c_interfazDetalle1 IS
    SELECT 
     RPAD(COD_CLIE, 7) COD_CLIE, 
     RPAD(NOM_CLIE, 30) NOM_CLIE, 
     LPAD(TO_CHAR(IMP_FACT_NETO, '999,999.99'), 16) VAL_MONT, 
     LPAD(TO_CHAR(IMP_COBR_31, '999,999.99'), 16) VAL_ABON,
     LPAD(TO_CHAR(IMP_PORC_RECU_31, '999.99'), 9) POR_RECU,
     LPAD(COD_ZONA, 9) COD_ZONA, 
     RPAD(VAL_TELE, 17) VAL_TELE, 
     RPAD(VAL_ESTA_CLIE, 12) VAL_ESTA, 
     LPAD(NVL(NUM_PEDI, 0), 9) NUM_PEDI, 
     LPAD(COD_ZONA||COD_SECC, 10) COD_SECC, 
     LPAD(TO_CHAR(FEC_CIER_31, 'dd/mm/yy'), 11) F31D
    FROM COB_REPOR_COBRA_FFVV_CONSU;

    CURSOR c_interfazDetalle2 IS
    SELECT
     LPAD(COD_ZONA, 5) COD_ZONA, 
     RPAD(NVL(NOM_GERE_ZONA, ' '), 30) NOM_GERE, 
     LPAD(TO_CHAR(IMP_FACT_NETO, '999,999.99'), 16) VAL_MONT, 
     LPAD(TO_CHAR(IMP_COBR_31, '999,999.99'), 16) VAL_ABON, 
     LPAD(TO_CHAR(IMP_PORC_RECU_31, '999.99'), 10) POR_RECU, 
     LPAD(TO_CHAR(FEC_CIER_31, 'dd/mm/yy'), 11) F31D, 
     RPAD(VAL_MAIL_GERE_ZONA, 35) EMAI_GERE, 
     RPAD(NOM_GERE_REGI, 40) NOM_GERE_REGI,
     RPAD(VAL_MAIL_GERE_REGI, 40) VAL_MAIL_GERE_REGI
    FROM COB_REPOR_COBRA_FFVV_ZONA;

TYPE interfazTipo1 IS RECORD (
 codigoCliente      VARCHAR2(15),
 nombreCliente      VARCHAR2(30),
 monto              VARCHAR2(20),
 abono              VARCHAR2(20),
 porcentaje         VARCHAR2(11),
 codigoZona         VARCHAR2(11),
 telefono           VARCHAR2(20),
 estado             VARCHAR2(30),
 pedidos            VARCHAR2(10),
 seccion            VARCHAR2(10),
 f31d               VARCHAR2(11)
);

TYPE interfazTipo2 IS RECORD (
 codigoZona         VARCHAR2(15),
 nombreGerente      VARCHAR2(40),
 monto              VARCHAR2(20),
 abono              VARCHAR2(20),
 porcentaje         VARCHAR2(11),
 f31d               VARCHAR2(11),
 emailGerente       VARCHAR2(40),
 nombreGR           VARCHAR2(40),
 emailGR            VARCHAR2(40)
);

   TYPE interfazTab1  IS TABLE OF interfazTipo1 ;
   interfazRecord1 interfazTab1;

   TYPE interfazTab2  IS TABLE OF interfazTipo2 ;
   interfazRecord2 interfazTab2;

   lbAbrirUtlFile  BOOLEAN;
   
   lsCampanyaVenta VARCHAR2(6);

BEGIN

  lsCampanyaVenta := gen_pkg_gener.gen_fn_perio_nsigu (psCodigoPais, psCodigoPeriodo,-2);
    
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfazDetalle1;
  LOOP
   FETCH c_interfazDetalle1 BULK COLLECT INTO interfazRecord1 LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
      
      lsLinea := ' <<<<<<INICIA CONSULTORAS >>>>>>>';
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      
      lsLinea := ' '||to_char(sysdate, 'HH24:MI:SS DD/MM/YY') || ' CAMPAÑA ACTUAL: ' || psCodigoPeriodo || ' VENTA CAMPAÑA: ' || lsCampanyaVenta;
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      
      lsLinea := ' ' ||
                 RPAD('CONSULTORA', 7) ||' ' ||
                 RPAD('NOMBRE', 30) ||'  ' ||
                 LPAD('MONTO', 13) ||'   ' ||
                 LPAD('ABONOS', 12) ||'     ' ||
                 LPAD('% RECUP', 6) ||'     ' ||
                 LPAD('ZONA', 6) ||'  '||
                 RPAD('TELEFONO', 14) ||'  ' ||
                 RPAD('ESTATUS', 9) ||
                 LPAD('# PED', 6) ||' ' ||
                 LPAD('SECCION', 7) ||' ' ||
                 LPAD('F+31D', 8);
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      
   END IF ;
   IF interfazRecord1.COUNT > 0 THEN
      FOR x IN interfazRecord1.FIRST .. interfazRecord1.LAST LOOP
          lsLinea := ' '||
                     interfazRecord1(x).codigoCliente || ' ' ||
                     interfazRecord1(x).nombreCliente || '  ' ||
                     interfazRecord1(x).monto || '   ' ||
                     interfazRecord1(x).abono || '     ' ||
                     interfazRecord1(x).porcentaje ||'     ' ||
                     interfazRecord1(x).codigoZona ||'  '||
                     interfazRecord1(x).telefono ||'  ' ||
                     interfazRecord1(x).estado ||
                     interfazRecord1(x).pedidos ||' ' ||
                     interfazRecord1(x).seccion ||' ' ||
                     interfazRecord1(x).f31d;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            
      END LOOP;
    END IF;
    EXIT WHEN c_interfazDetalle1%NOTFOUND;
 END LOOP;
 CLOSE c_interfazDetalle1;
 
 lsLinea := ' <<<<<<TERMINA CONSULTORAS >>>>>>>';
 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
 
 lsLinea := ' <<<<<<INICIA ZONAS >>>>>>>';
 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

  OPEN c_interfazDetalle2;
  LOOP
   FETCH c_interfazDetalle2 BULK COLLECT INTO interfazRecord2 LIMIT W_FILAS;
   IF interfazRecord2.COUNT > 0 THEN
      FOR x IN interfazRecord2.FIRST .. interfazRecord2.LAST LOOP
          lsLinea := ' '||
                     interfazRecord2(x).codigoZona || '  ' ||
                     interfazRecord2(x).nombreGerente ||'  ' ||
                     interfazRecord2(x).monto ||'    ' ||
                     interfazRecord2(x).abono ||'      ' ||
                     interfazRecord2(x).porcentaje ||'        ' ||
                     interfazRecord2(x).f31d || '    ' ||
                     interfazRecord2(x).emailGerente ||'    ' ||
                     interfazRecord2(x).nombreGR ||'      ' ||
                     interfazRecord2(x).emailGR;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            
      END LOOP;
    END IF;
    EXIT WHEN c_interfazDetalle2%NOTFOUND;
 END LOOP;
 CLOSE c_interfazDetalle2;

 lsLinea := ' <<<<<<TERMINA ZONAS >>>>>>>';
 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_GENER_REPOR_COBRA_FFVV: '||ls_sqlerrm);
END COB_PR_GENER_REPOR_COBRA_FFVV;

  /************************************************************************************
  Descripcion       : Genera Reporte COB Numero de Pagos por Campaña en formato CSV
  Fecha Creacion    : 05/11/2015
  Autor             : Carlos Bazalar
  ************************************************************************************/
 PROCEDURE COB_PR_REPOR_PAGOS_CAMPA_CSV(
  pscodigopais                   IN   VARCHAR2,
  psTipoReporte                  IN   VARCHAR2,
  pnNumeroPagosMayores           IN   NUMBER,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  pscodigousuario                IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2)
 IS
      lsdirtempo      bas_inter.dir_temp%TYPE;
      v_handle       utl_file.file_type;
      lslinea            varchar2(4000);

      CURSOR C_REPOR_CONSULTORA IS
        SELECT
          COD_REGI, 
          DES_REGI, 
          COD_ZONA, 
          DES_ZONA, 
          COD_SECC, 
          COD_CLIE, 
          VAL_NOMB,
          DES_ESTA, 
          COD_PERI,
          COUNT(1) val_cant,
          ROUND(AVG(imp_abon),2) val_prom
      FROM COB_REPOR_NUMER_PAGOS_CAMPA x
      HAVING COUNT(1) > pnNumeroPagosMayores
      GROUP BY COD_REGI, 
              DES_REGI, 
              COD_ZONA, 
              DES_ZONA, 
              COD_SECC, 
              COD_CLIE, 
              VAL_NOMB, 
              DES_ESTA, 
              COD_PERI
      ORDER BY 10 DESC;
 
      TYPE detalleConsultoraTab IS TABLE OF C_REPOR_CONSULTORA%ROWTYPE;
      detalleConsultoraRecord detalleConsultoraTab;
      
      CURSOR C_REPOR_BANCO IS
        SELECT 
            COD_REGI, 
            DES_REGI, 
            COD_ZONA, 
            DES_ZONA, 
            COD_SECC, 
            COD_CLIE, 
            VAL_NOMB,
            DES_ESTA, 
            COD_PERI, 
            DES_CC, 
            COUNT(1) val_cant,
            ROUND(AVG(imp_abon),2) val_prom
        FROM COB_REPOR_NUMER_PAGOS_CAMPA x
        HAVING COUNT(1) > pnNumeroPagosMayores
        GROUP BY COD_REGI, 
                DES_REGI, 
                COD_ZONA, 
                DES_ZONA, 
                COD_SECC, 
                COD_CLIE, 
                VAL_NOMB, 
                DES_ESTA, 
                COD_PERI, 
                DES_CC
        ORDER BY 11 DESC;
 
      TYPE detalleBancoTab IS TABLE OF C_REPOR_BANCO%ROWTYPE;
      detalleBancoRecord detalleBancoTab;
      
      
      lbAbrirUtlFile  BOOLEAN;

  BEGIN

      lbAbrirUtlFile := true;
      EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
      IF (psTipoReporte = 'C') THEN
          OPEN C_REPOR_CONSULTORA;
          LOOP
             FETCH C_REPOR_CONSULTORA BULK COLLECT INTO detalleConsultoraRecord LIMIT w_filas;
             IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
             END IF ;
             IF detalleConsultoraRecord.COUNT > 0 THEN
                FOR x IN detalleConsultoraRecord.FIRST .. detalleConsultoraRecord.LAST LOOP
                      lslinea :=     '=T("'|| detalleConsultoraRecord(x).COD_REGI || '")' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).DES_REGI || '"' || ',' ||
                                      '=T("'|| detalleConsultoraRecord(x).COD_ZONA || '")' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).DES_ZONA || '"' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).COD_SECC || '"' || ',' ||
                                      '=T("'|| detalleConsultoraRecord(x).COD_CLIE || '")' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).VAL_NOMB || '"' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).DES_ESTA || '"' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).val_cant || '"' || ',' ||
                                      '"'|| detalleConsultoraRecord(x).val_prom || '"' ;
                        UTL_FILE.PUT_LINE (v_handle, lslinea);

                END LOOP;
              END IF;
              EXIT WHEN C_REPOR_CONSULTORA%NOTFOUND;
         END LOOP;
         CLOSE C_REPOR_CONSULTORA;
     ELSE
         OPEN C_REPOR_BANCO;
          LOOP
             FETCH C_REPOR_BANCO BULK COLLECT INTO detalleBancoRecord LIMIT w_filas;
             IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
             END IF ;
             IF detalleBancoRecord.COUNT > 0 THEN
                FOR x IN detalleBancoRecord.FIRST .. detalleBancoRecord.LAST LOOP
                      lslinea :=     '=T("'|| detalleBancoRecord(x).COD_REGI || '")' || ',' ||
                                      '"'|| detalleBancoRecord(x).DES_REGI || '"' || ',' ||
                                      '=T("'|| detalleBancoRecord(x).COD_ZONA || '")' || ',' ||
                                      '"'|| detalleBancoRecord(x).DES_ZONA || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).COD_SECC || '"' || ',' ||
                                      '=T("'|| detalleBancoRecord(x).COD_CLIE || '")' || ',' ||
                                      '"'|| detalleBancoRecord(x).VAL_NOMB || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).DES_ESTA || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).COD_PERI || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).COD_PERI || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).DES_CC || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).val_cant || '"' || ',' ||
                                      '"'|| detalleBancoRecord(x).val_prom || '"' ;
                        UTL_FILE.PUT_LINE (v_handle, lslinea);

                END LOOP;
              END IF;
              EXIT WHEN C_REPOR_BANCO%NOTFOUND;
         END LOOP;
         CLOSE C_REPOR_BANCO;
       
     END IF;
     IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(v_handle);
     END IF;

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_PAGOS_CAMPA_CSV: '||ls_sqlerrm);

  END COB_PR_REPOR_PAGOS_CAMPA_CSV;
          
END COB_PKG_REPOR;
/
