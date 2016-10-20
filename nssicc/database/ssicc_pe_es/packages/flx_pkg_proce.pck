CREATE OR REPLACE PACKAGE FLX_PKG_PROCE is

 /* Declaracion de variables */
 ln_sqlcode                            NUMBER(10);
 ls_sqlerrm                            VARCHAR2(1500);
 W_FILAS                               NUMBER:=5000;

 PROCEDURE FLX_PR_PROCE_FLEXI_GP3;

 PROCEDURE FLX_PR_PROCE_FLEXI_GP5;

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_FLEXI;

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_FLEXI(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_imp_fina                     IN   flx_gener_finan_consu_flexi.val_mont_flex_fina%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE);

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_CIERR;

 PROCEDURE FLX_PR_GENER_CUENT_CORRI_FLEXI(
  p_num_lote                       IN   flx_gener_finan_consu_flexi.num_lote%TYPE);

 PROCEDURE FLX_PR_PROCE_REGIS_INTER_FLEX;

 PROCEDURE FLX_PR_PROCE_ACTUA_INTER_FLEX(
  p_oid_peri                      IN   cra_perio.oid_peri%TYPE);

 PROCEDURE FLX_PR_GENER_CARGO_INTER_FLEXI;

 PROCEDURE FLX_PR_GENER_INTER_FLEXI_CIERR;

 PROCEDURE FLX_PR_GENER_SOLIC_CARGO_FLEXI(
  p_num_lote                       IN   ccc_carga_cadir_docle_masiv.num_lote%TYPE) ;

 PROCEDURE FLX_PR_GENER_PROYE_FINAN_FLEXI;

 PROCEDURE FLX_PR_GENER_PADOC_FLEXI_ZONAS;

 PROCEDURE FLX_PR_GENER_PADOC_FLEXI_ZONA2;

 PROCEDURE FLX_PR_OBTIE_VALOR_TASAS(
  p_q               IN  NUMBER,
  p_imp_tcea        out VARCHAR2,
  p_imp_tcem        out VARCHAR2);

 FUNCTION FLX_FN_OBTIE_SALDO_FLEXI_ANTER(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_oid_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_OBTIE_MONTO_INTER_FLEXI(
  p_cod_peri                       IN   VARCHAR2,
  p_num_dias_camp                  IN   NUMBER,
  p_mont_cuot_flex                 IN   NUMBER)
 RETURN NUMBER;

 FUNCTION FLX_FN_OBTIE_Q(
  p_oid_clie        IN mae_clien.oid_clie%TYPE,
  p_cod_peri_res1   IN seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_res2   IN seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_OBTIE_SALDO_FLEXI_CAMP(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_cod_peri_gene                  IN   cra_perio.oid_peri%TYPE,
  p_oid_peri_actu                  IN   cra_perio.oid_peri%TYPE,
  p_oid_peri_cobr                  IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_OBTIE_SALDO_MAXIM(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_peri                       IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_FACTO_CONVE_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_FLAG_USO_FLEXI_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR;

 FUNCTION FLX_FN_FLAG_PREPA_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR;

 FUNCTION FLX_FN_MONTO_FINAN_CATAL_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_MONTO_CUOTA_21DIA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_MONTO_CUOTA_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_MONTO_INTER_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_SALDO_CUOTA_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

  FUNCTION FLX_FN_MONTO_FINAN_FACTU_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR;

 FUNCTION FLX_FN_OBTIE_CUOTA_PAGAD_FLX(
  psOidClie                       IN   mae_clien.oid_clie%TYPE,
  psOidPeri                       IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER;

 PROCEDURE FLX_PR_GENER_REPOR_SALDO(psCodigoPeriodo   IN seg_perio_corpo.cod_peri%type,
                                    psCodigoPais       IN seg_pais.cod_pais%type,
                                    psOid             OUT VARCHAR2);

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Gestionar consultora
Fecha Creacion    : 15/10/2013
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE FLX_PR_REPOR_GESTI_CONSU_CSV(
    psCodigoPais                        VARCHAR2,
    psCampanyaComunicacion              VARCHAR2,
    psCampanyaFacturacion               VARCHAR2,
    psCodigoCliente                     VARCHAR2,
    psCodigoCalifComp                   VARCHAR2,
    psCodigoCalifExpe                   VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;

 PROCEDURE FLX_PR_CARGA_DATOS_WEBSE;

 FUNCTION FLX_FN_OBTIE_MONMI_CONSU_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_FN_OBTIE_MONMI_ZONAS_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE)
 RETURN NUMBER;

 FUNCTION FLX_PR_OBTIE_MONMI_WEBSE(
  p_cod_clie                       IN   VARCHAR2,
  p_cod_peri                       IN   VARCHAR2)
 RETURN NUMBER;

 /**************************************************************************
    Descripcion       : flx_pr_desac_autom

    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte                                                 SEG_CANAL can
                               WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER

  ***************************************************************************/
  PROCEDURE flx_pr_desac_autom;

  PROCEDURE flx_pr_activ_web(p_numlote varchar2);

END FLX_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY FLX_PKG_PROCE IS

 -- Declaracion de Variables --
 gv_des_log                        VARCHAR2(2500);

 gc_oid_tipo_soli_carg_uso         CONSTANT NUMBER(4):=1507;
 gc_cod_tipo_soli_carg_flex        CONSTANT VARCHAR2(4):='SCUF';
 gc_oid_subp_carg_flex             CONSTANT NUMBER(3):= 203;
 gc_oid_subp_inte_flex             CONSTANT NUMBER(3):= 204;
 gc_oid_tipo_subp_carg_flex        CONSTANT NUMBER(3):= 203;
 gc_oid_tipo_subp_abon_flex        CONSTANT NUMBER(3):= 205;
 gc_oid_tipo_subp_perc_flex        CONSTANT NUMBER(3):= 206;

 gc_cod_modu                       CONSTANT fin_proce_ejecu.cod_modu%TYPE:='FLX';
 gc_cod_gene_cuot_fina_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5001';
 gc_cod_gene_cuen_corr_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5002';
 gc_cod_proc_actu_inte_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5003';
 gc_cod_gene_carg_inte_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5004';
 gc_cod_gene_soli_carg_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5005';
 gc_cod_gene_proy_fina_flex        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5006';
 gc_cod_gene_padoc_flex_zona       CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5007';
 gc_cod_gene_inte_flex_cier        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5008';
 gc_cod_gene_cuot_fina_cier        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='5009';

 gc_cod_prod_carg_inte_flex        CONSTANT VARCHAR2(10):='9999999996';
 gc_cod_form_pago_carg_flex        CONSTANT VARCHAR2(3):='CUF';

 -- Excepciones --
 gv_reco_trac                    FIN_PKG_GENER.error_rt;
 e_exis_proc_ejec                EXCEPTION;
 e_codi_soci_null                EXCEPTION;
 e_codi_pais_null                EXCEPTION;
 e_no_exis_impo_gast_admi        EXCEPTION;
 e_no_exis_camp_acti             EXCEPTION;
 e_no_para_tipo_soli             EXCEPTION;
 e_no_para_carg_fase             EXCEPTION;
 e_no_coin_nume_regi             EXCEPTION;
 e_no_perm_para                  EXCEPTION;
 e_no_exis_movi                  EXCEPTION;

 e_no_gene_regi                  EXCEPTION;
 e_no_gene_regi_real             EXCEPTION;
 e_erro_proc_inte                EXCEPTION;

 PROCEDURE FLX_PR_PROCE_FLEXI_GP3
 IS
 BEGIN

  FLX_PR_GENER_CUOTA_FINAN_FLEXI;
  FLX_PR_GENER_CUOTA_FINAN_CIERR;
  FLX_PR_GENER_CARGO_INTER_FLEXI;
  FLX_PR_GENER_INTER_FLEXI_CIERR;

 END FLX_PR_PROCE_FLEXI_GP3;

 PROCEDURE FLX_PR_PROCE_FLEXI_GP5
 IS

  ls_tipoXMLFlex       VARCHAR2(2):=CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('TipoXMLFlexipago');


 BEGIN

  FLX_PR_PROCE_REGIS_INTER_FLEX;
  FLX_PR_GENER_PROYE_FINAN_FLEXI;

  IF ls_tipoXMLFlex='V2' THEN
   FLX_PR_GENER_PADOC_FLEXI_ZONA2;
  ELSE
   FLX_PR_GENER_PADOC_FLEXI_ZONAS;
  END IF;

 END FLX_PR_PROCE_FLEXI_GP5;

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_FLEXI
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_1               seg_perio_corpo.oid_peri%TYPE;
  lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
  lv_oid_tipo_soli_pais            ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_num_lote_gene_fina            fin_inter_ejecu.num_lote%TYPE;
  lv_mont_mini_deud                bas_ctrl_fact.val_mnt_min_deud%TYPE;

  lv_imp_mini_flex_fina            NUMBER(12,2);
  lv_cant_cons_gene_fina           NUMBER(12);
  lv_cant_cons_fina_real           NUMBER(12);
  lv_num_deci                      NUMBER(2);
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                 fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_cuot_fina_flex;

  lv_num_lote_gene_fina := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cuotas Flexipago por Financiamiento';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Numero Lote Financiacion ' || lv_num_lote_gene_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  
  SELECT sm.num_deci
  INTO lv_num_deci
  FROM 
   seg_moned sm,
   seg_pais sp
  WHERE sp.mone_oid_mone = sm.oid_mone
  AND sp.cod_pais = lv_cod_pais;
    
  lv_cod_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);
  lv_oid_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_rest_1);

  gv_des_log:='Codigo Campana : ' || lv_cod_peri;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fecha Facturacion : ' || lv_fec_fact;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT bcf.val_mnt_min_deud
  INTO lv_mont_mini_deud
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  lv_imp_mini_flex_fina := TO_NUMBER(NVL(CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteMinimoFinanciamientoFlexipago'),0),'999999.99');

  SELECT ptsp.oid_tipo_soli_pais
  INTO lv_oid_tipo_soli_pais
  FROM
   ped_tipo_solic pts,
   ped_tipo_solic_pais ptsp
  WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
    AND pts.cod_tipo_soli = 'SOC';

  gv_des_log:='Oid Tipo Soli Pais : ' || lv_oid_tipo_soli_pais;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Cerrando Financiamientos Pasados --
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.num_lote_gene_flex = '201410130642',
      ff.ind_gene_fina_flex = 2,
      ff.fec_gene_fina = lv_fec_fact
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND ff.cod_peri < lv_cod_peri_rest_1
    AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE psc.clie_oid_clie = ff.oid_clie
         AND psc.fec_prog_fact = lv_fec_fact
         AND psc.perd_oid_peri = lv_oid_peri
         AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
         AND psc.grpr_oid_grup_proc = 3);

  SELECT COUNT(*)
  INTO lv_cant_cons_gene_fina
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND ff.cod_peri = lv_cod_peri_rest_1
    AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE psc.clie_oid_clie = ff.oid_clie
         AND psc.fec_prog_fact = lv_fec_fact
         AND psc.perd_oid_peri = lv_oid_peri
         AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
         AND psc.grpr_oid_grup_proc = 3);

  gv_des_log:='Cantidad de Consultoras a Financiar : ' || lv_cant_cons_gene_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cons_gene_fina = 0 THEN
    RAISE e_no_gene_regi;
  END IF;

  gv_des_log:='Actualizando el Numero de Lote y Fecha de Generacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.num_lote_gene_flex = lv_num_lote_gene_fina,
      ff.fec_gene_fina = lv_fec_fact,
      ff.ind_gene_fina_flex = 0
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE psc.clie_oid_clie = ff.oid_clie
         AND psc.fec_prog_fact = lv_fec_fact
         AND psc.perd_oid_peri = lv_oid_peri
         AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
         AND psc.grpr_oid_grup_proc = 3);

  gv_des_log:='Actualizando la deuda final';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
    SET ff.val_sald_deud_fina = CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(ff.oid_clie,lv_cod_peri)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
  
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_abon_pend_apli = 
            NVL((SELECT SUM(mc.val_recl_pend)
             FROM mae_clien mc
             WHERE mc.oid_clie = ff.oid_clie),0),
      ff.val_cupo_pend_apli = 
             NVL((SELECT SUM(dc.imp_deta)
              FROM
               ccc_detal_cupon_trami_depur dc,
               ccc_situa_cupon sc
              WHERE dc.sicu_oid_situ_cupo = sc.oid_situ_cupo
                AND sc.cod_situ_cupo = 'T' 
                AND dc.clie_oid_clie = ff.oid_clie),0)                                     
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
  
  UPDATE flx_gener_finan_consu_flexi ff
    SET ff.val_sald_deud_fina = ff.val_sald_deud_fina - ( NVL(ff.val_abon_pend_apli,0) + NVL(ff.val_cupo_pend_apli,0))  
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
  
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 9
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.val_sald_deud_fina - lv_mont_mini_deud > ff.val_sald_maxi_camp;

  gv_des_log:=' *** Consultoras que no cumplieron con el pago ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Calculando el Monto Flexipago a Financiar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_flex_fina = ROUND(LEAST(ff.val_sald_deud_fina, ff.val_mont_flex_fina_proy),lv_num_deci)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 0;
    
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 8
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.val_mont_flex_fina < lv_imp_mini_flex_fina
  AND ff.ind_gene_fina_flex = 0;

  gv_des_log:=' *** Consultoras que con monto de financiacion insuficiente ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT COUNT(*)
  INTO lv_cant_cons_fina_real
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 0;

  gv_des_log:=' *** Cantidad de Consultoras a Financiar Real: ' || lv_cant_cons_fina_real;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cons_fina_real = 0 THEN
   RAISE e_no_gene_regi_real;
  END IF;

  gv_des_log:='     Actualizando el indicador a las consultoras a financiar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 1      
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.val_mont_flex_fina >= lv_imp_mini_flex_fina
  AND ff.ind_gene_fina_flex = 0;
   
  gv_des_log:=' *** Consultoras Actualizadas : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  gv_des_log:='Actualizando el Oid Zona de las Consultoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.oid_zona_fina = FIN_PKG_GENER.FIN_FN_OBTIE_OID_ZONA_CONSU(ff.oid_clie)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Marcando los segundos pedidos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  BEGIN
      
   UPDATE ssicc_pe_es.flx_gener_finan_consu_flexi ff
   SET ff.ind_segu_pedi = 1
   WHERE ff.cod_peri = lv_cod_peri
     AND ff.ind_gene_fina_flex <> 1
     AND ff.oid_clie IN (
      SELECT a.oid_clie
      FROM flx_gener_finan_consu_flexi a
      WHERE a.num_lote_gene_flex = lv_num_lote_gene_fina 
        AND a.cod_peri = ff.cod_peri
        AND a.oid_clie = ff.oid_clie
        AND a.ind_gene_fina_flex = 1);
  
  EXCEPTION
   WHEN OTHERS THEN
     NULL;
  END;
                        
  gv_des_log:='Calculando la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle1 = ROUND(ff.val_mont_flex_fina/2,lv_num_deci)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle1 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle1 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle1,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando los interes para la  Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_inte_fle1 =  FLX_FN_OBTIE_MONTO_INTER_FLEXI(ff.cod_peri_cuot_fle1,ff.val_num_dias_camp_cuot_fle1,ff.val_mont_cuot_fle1)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle2 = ff.val_mont_flex_fina - ff.val_mont_cuot_fle1
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle2 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle2 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle2,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas en la entidad FLX_CUOTA_FLEXI_FACTU_CABEC';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Generando las Cuotas Flexipago
  INSERT INTO flx_cuota_flexi_factu_cabec
   SELECT
     ff.num_lote_gene_flex, -- num_lote  varchar2(15)
     ff.cod_peri,           -- cod_peri  varchar2(6)
     lv_fec_fact,           -- fec_fact  date
     ff.cod_clie,           -- cod_clie  varchar2(15)
     ff.oid_clie,           -- oid_clie  number(12)
     ff.oid_zona,           -- oid_zona  number(12)
     NULL,                  -- val_nume_soli  number(10)
     ff.val_pedi_base_cata, -- val_pedi_base_cata  number(12,2)
     0,                     -- val_mont_soli_fina  number(12,2)
     ff.val_mont_flex_fina, -- val_mont_fina  number(12,2)
     0,                     -- val_mont_fina_desg  number(12,2)
     0,                     -- val_mont_fina_canc  number(12,2)
     ff.val_porc_conv_pedi, -- val_fact_conv  number(12,2)
     ff.oid_soli_cabe,      -- oid_soli_cabe  number(12)
     ff.val_cuot_21di_pedi_vige,   -- val_mont_pedi_base  number(12,2)
     ff.soca_oid_soli_cabe  -- soca_oid_soli_cabe  number(12)
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas 1 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- 1era Cuota Flexipago
  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,      --num_lote  varchar2(15)
    ff.cod_peri,                --cod_peri  varchar2(6)
    lv_fec_fact,                --fec_fact  date
    ff.cod_clie,                --cod_clie  varchar2(15)
    ff.oid_clie,                --oid_clie  number(12)
    ff.oid_zona,                --oid_zona  number(12)
    NULL,                       --oid_form_pago  number(12)
    NULL,                       --val_nume_soli  number(10)
    2,                          --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle1,      --oid_peri_cuot_flex  number(12)
    NULL,                       --fec_venc_cuot_flex  date
    NULL,                       --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle1,      --val_mont_cuot_flex  number(12,2)
    NULL,                       --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle1,      --val_mont_carg_uso  number(12,2)
    NULL,                       --oid_soli_cabe_carg_uso  number(12)
    NULL,                        --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle1,  -- val_refe_nume_docu,
    NULL,                        -- fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  -- 2da Cuota Flexipago
  gv_des_log:='Registrando las cuotas 2 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,  --num_lote  varchar2(15)
    ff.cod_peri,            --cod_peri  varchar2(6)
    lv_fec_fact,            --fec_fact  date
    ff.cod_clie,            --cod_clie  varchar2(15)
    ff.oid_clie,            --oid_clie  number(12)
    ff.oid_zona,            --oid_zona  number(12)
    NULL,                   --oid_form_pago  number(12)
    NULL,                   --val_nume_soli  number(10)
    3,                      --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle2,   --oid_peri_cuot_flex  number(12)
    NULL,                   --fec_venc_cuot_flex  date
    NULL,                   --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle2,  --val_mont_cuot_flex  number(12,2)
    NULL,                   --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle2,  --val_mont_carg_uso  number(12,2)
    NULL,                   --oid_soli_cabe_carg_uso  number(12)
    NULL,                   --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle2,  -- val_refe_nume_docu
    NULL,                        --fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Inicio Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN
   FLX_PR_GENER_CUENT_CORRI_FLEXI(lv_num_lote_gene_fina);

  EXCEPTION
   WHEN OTHERS THEN
     gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
     gv_des_log :=  ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                     ' en el programa ' ||
                     gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name ;
     FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
     RAISE e_erro_proc_inte;
  END;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 2;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 3;

  gv_des_log:='Fin Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN e_no_gene_regi THEN
   gv_des_log := '*** No existen consultoras por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_no_gene_regi_real THEN
   gv_des_log := '*** No existen consultoras reales por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_erro_proc_inte THEN
   gv_des_log := ' *** Error la generacion de la Cuenta Corriente de las Cuotas : FLX_PR_GENER_CUENT_CORRI_FLEXI ***';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log := 'Fin del Proceso con errores' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error en la Generacion de la cuenta Corriente de las Cuotas ***');

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_CUOTA_FINAN_FLEXI;

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_FLEXI(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_imp_fina                     IN   flx_gener_finan_consu_flexi.val_mont_flex_fina%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;

  lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
  lv_oid_tipo_soli_pais            ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_num_lote_gene_fina            fin_inter_ejecu.num_lote%TYPE;


  lv_cant_cons_gene_fina           NUMBER(12);
  lv_num_deci                      NUMBER(2);
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                 fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_cuot_fina_flex;

  lv_num_lote_gene_fina := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cuotas Flexipago por Financiamiento';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);


  lv_fec_fact := TRUNC(SYSDATE);

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_peri_rest_1 := p_cod_peri;

  SELECT sm.num_deci
  INTO lv_num_deci
  FROM 
   seg_moned sm,
   seg_pais sp
  WHERE sp.mone_oid_mone = sm.oid_mone
  AND sp.cod_pais = lv_cod_pais;
  
  gv_des_log:='Codigo Campana : ' || lv_cod_peri;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fecha Facturacion : ' || lv_fec_fact;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT ptsp.oid_tipo_soli_pais
  INTO lv_oid_tipo_soli_pais
  FROM
   ped_tipo_solic pts,
   ped_tipo_solic_pais ptsp
  WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
    AND pts.cod_tipo_soli = 'SOC';

  gv_des_log:='Oid Tipo Soli Pais : ' || lv_oid_tipo_soli_pais;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT COUNT(*)
  INTO lv_cant_cons_gene_fina
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.cod_peri = lv_cod_peri_rest_1
    AND ff.cod_clie = p_cod_clie;

  gv_des_log:='Cantidad de Consultoras a Financiar : ' || lv_cant_cons_gene_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cons_gene_fina = 0 THEN
    RAISE e_no_gene_regi;
  END IF;

  gv_des_log:='Actualizando el Numero de Lote y Fecha de Generacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.num_lote_gene_flex = lv_num_lote_gene_fina,
      ff.fec_gene_fina = lv_fec_fact,
      ff.ind_gene_fina_flex = 0
  WHERE ff.cod_clie = p_cod_clie
    AND ff.cod_peri = lv_cod_peri_rest_1;

  gv_des_log:='Actualizando la deuda final';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
    SET ff.val_sald_deud_fina = CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(ff.oid_clie,lv_cod_peri)    
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
  
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_abon_pend_apli =  
            NVL((SELECT SUM(mc.val_recl_pend)
             FROM mae_clien mc
             WHERE mc.oid_clie = ff.oid_clie),0),
      ff.val_cupo_pend_apli = 
             NVL((SELECT SUM(dc.imp_deta)
              FROM
               ccc_detal_cupon_trami_depur dc,
               ccc_situa_cupon sc
              WHERE dc.sicu_oid_situ_cupo = sc.oid_situ_cupo
                AND sc.cod_situ_cupo = 'T' 
                AND dc.clie_oid_clie = ff.oid_clie),0)                                     
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
  
  UPDATE flx_gener_finan_consu_flexi ff
    SET ff.val_sald_deud_fina = ff.val_sald_deud_fina - ( NVL(ff.val_abon_pend_apli,0) + NVL(ff.val_cupo_pend_apli,0)  )
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;
   
  gv_des_log:='Calculando el Monto Flexipago a Financiar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_flex_fina = ROUND(p_imp_fina,lv_num_deci),
      ff.ind_gene_fina_flex = 1
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 0;
  
  gv_des_log:='Actualizando el Oid Zona de las Consultoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.oid_zona_fina = FIN_PKG_GENER.FIN_FN_OBTIE_OID_ZONA_CONSU(ff.oid_clie)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;
  
  gv_des_log:='Marcando los segundos pedidos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
    
  BEGIN
      
   UPDATE ssicc_pe_es.flx_gener_finan_consu_flexi ff
   SET ff.ind_segu_pedi = 1
   WHERE ff.cod_peri = lv_cod_peri
     AND ff.ind_gene_fina_flex <> 1
     AND ff.oid_clie IN (
      SELECT a.oid_clie
      FROM flx_gener_finan_consu_flexi a
      WHERE a.num_lote_gene_flex = lv_num_lote_gene_fina 
        AND a.cod_peri = ff.cod_peri
        AND a.oid_clie = ff.oid_clie
        AND a.ind_gene_fina_flex = 1);
  
  EXCEPTION
   WHEN OTHERS THEN
     NULL;
  END;
       
  gv_des_log:='Calculando la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle1 = ROUND(ff.val_mont_flex_fina/2,lv_num_deci)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle1 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle1 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle1,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando los interes para la  Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_inte_fle1 =  FLX_FN_OBTIE_MONTO_INTER_FLEXI(ff.cod_peri_cuot_fle1,ff.val_num_dias_camp_cuot_fle1,ff.val_mont_cuot_fle1)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle2 = ff.val_mont_flex_fina - ff.val_mont_cuot_fle1
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle2 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle2 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle2,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas en la entidad FLX_CUOTA_FLEXI_FACTU_CABEC';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Generando las Cuotas Flexipago
  INSERT INTO flx_cuota_flexi_factu_cabec
   SELECT
     ff.num_lote_gene_flex, -- num_lote  varchar2(15)
     ff.cod_peri,           -- cod_peri  varchar2(6)
     lv_fec_fact,           -- fec_fact  date
     ff.cod_clie,           -- cod_clie  varchar2(15)
     ff.oid_clie,           -- oid_clie  number(12)
     ff.oid_zona,           -- oid_zona  number(12)
     NULL,                  -- val_nume_soli  number(10)
     ff.val_pedi_base_cata, -- val_pedi_base_cata  number(12,2)
     0,                     -- val_mont_soli_fina  number(12,2)
     ff.val_mont_flex_fina, -- val_mont_fina  number(12,2)
     0,                     -- val_mont_fina_desg  number(12,2)
     0,                     -- val_mont_fina_canc  number(12,2)
     ff.val_porc_conv_pedi, -- val_fact_conv  number(12,2)
     ff.oid_soli_cabe,      -- oid_soli_cabe  number(12)
     ff.val_cuot_21di_pedi_vige,   -- val_mont_pedi_base  number(12,2)
     ff.soca_oid_soli_cabe  -- soca_oid_soli_cabe  number(12)
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas 1 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- 1era Cuota Flexipago
  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,      --num_lote  varchar2(15)
    ff.cod_peri,                --cod_peri  varchar2(6)
    lv_fec_fact,                --fec_fact  date
    ff.cod_clie,                --cod_clie  varchar2(15)
    ff.oid_clie,                --oid_clie  number(12)
    ff.oid_zona,                --oid_zona  number(12)
    NULL,                       --oid_form_pago  number(12)
    NULL,                       --val_nume_soli  number(10)
    2,                          --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle1,      --oid_peri_cuot_flex  number(12)
    NULL,                       --fec_venc_cuot_flex  date
    NULL,                       --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle1,      --val_mont_cuot_flex  number(12,2)
    NULL,                       --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle1,      --val_mont_carg_uso  number(12,2)
    NULL,                       --oid_soli_cabe_carg_uso  number(12)
    NULL,                        --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle1,  -- val_refe_nume_docu,
    NULL,                        -- fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  -- 2da Cuota Flexipago
  gv_des_log:='Registrando las cuotas 2 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,  --num_lote  varchar2(15)
    ff.cod_peri,            --cod_peri  varchar2(6)
    lv_fec_fact,            --fec_fact  date
    ff.cod_clie,            --cod_clie  varchar2(15)
    ff.oid_clie,            --oid_clie  number(12)
    ff.oid_zona,            --oid_zona  number(12)
    NULL,                   --oid_form_pago  number(12)
    NULL,                   --val_nume_soli  number(10)
    3,                      --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle2,   --oid_peri_cuot_flex  number(12)
    NULL,                   --fec_venc_cuot_flex  date
    NULL,                   --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle2,  --val_mont_cuot_flex  number(12,2)
    NULL,                   --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle2,  --val_mont_carg_uso  number(12,2)
    NULL,                   --oid_soli_cabe_carg_uso  number(12)
    NULL,                   --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle2,  -- val_refe_nume_docu
    NULL,                        --fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Inicio Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN
   FLX_PR_GENER_CUENT_CORRI_FLEXI(lv_num_lote_gene_fina);

  EXCEPTION
   WHEN OTHERS THEN
     gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
     gv_des_log :=  ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                     ' en el programa ' ||
                     gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name ;
     FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
     RAISE e_erro_proc_inte;
  END;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 2;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 3;

  gv_des_log:='Fin Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN e_no_gene_regi THEN
   gv_des_log := '*** No existen consultoras por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_no_gene_regi_real THEN
   gv_des_log := '*** No existen consultoras reales por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_erro_proc_inte THEN
   gv_des_log := ' *** Error la generacion de la Cuenta Corriente de las Cuotas : FLX_PR_GENER_CUENT_CORRI_FLEXI ***';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log := 'Fin del Proceso con errores' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error en la Generacion de la cuenta Corriente de las Cuotas ***');

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_CUOTA_FINAN_FLEXI;

 PROCEDURE FLX_PR_GENER_CUOTA_FINAN_CIERR
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_1               seg_perio_corpo.oid_peri%TYPE;
  lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
  lv_num_lote_gene_fina            fin_inter_ejecu.num_lote%TYPE;
  lv_mont_mini_deud                bas_ctrl_fact.val_mnt_min_deud%TYPE;

  lv_imp_mini_flex_fina            NUMBER(12,2);
  lv_cant_cons_gene_fina           NUMBER(12);
  lv_cant_cons_fina_real           NUMBER(12);
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                 fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_cuot_fina_cier;

  lv_num_lote_gene_fina := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cuotas Flexipago por Financiamiento al Cierre';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);
  lv_oid_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_rest_1);

  gv_des_log:='Codigo Campana : ' || lv_cod_peri;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fecha Facturacion : ' || lv_fec_fact;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT bcf.val_mnt_min_deud
  INTO lv_mont_mini_deud
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  lv_imp_mini_flex_fina := TO_NUMBER(NVL(CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteMinimoFinanciamientoFlexipago'),0),'99999.99');

  -- Cerrando Financiamientos Incompletos
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.num_lote_gene_flex = '201410130642',
      ff.ind_gene_fina_flex = 2,
      ff.fec_gene_fina = lv_fec_fact
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND ff.cod_peri < lv_cod_peri_rest_1
    AND EXISTS (
       SELECT NULL
       FROM
        fac_progr_cierr fpc,
        zon_zona zz,
        zon_secci zs,
        zon_terri_admin zta,
        mae_clien_unida_admin mcua
       WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND mcua.ind_acti = 1
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND fpc.cod_zona = zz.cod_zona
         AND fpc.fec_cier = lv_fec_fact
         AND mcua.clie_oid_clie = ff.oid_clie
         AND fpc.est_cier = 'A'
         AND fpc.tip_cier = 'Z');

  SELECT COUNT(*)
  INTO lv_cant_cons_gene_fina
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND ff.cod_peri = lv_cod_peri_rest_1
    AND EXISTS (
       SELECT NULL
       FROM
        fac_progr_cierr fpc,
        zon_zona zz,
        zon_secci zs,
        zon_terri_admin zta,
        mae_clien_unida_admin mcua
       WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND mcua.ind_acti = 1
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND fpc.cod_zona = zz.cod_zona
         AND fpc.fec_cier = lv_fec_fact
         AND mcua.clie_oid_clie = ff.oid_clie
         AND fpc.est_cier = 'A'
         AND fpc.tip_cier = 'Z');

  gv_des_log:='Cantidad de Consultoras a Financiar en Cierre: ' || lv_cant_cons_gene_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cons_gene_fina = 0 THEN
    RAISE e_no_gene_regi;
  END IF;

  gv_des_log:='Actualizando el Numero de Lote y Fecha de Generacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.num_lote_gene_flex = lv_num_lote_gene_fina,
      ff.fec_gene_fina = lv_fec_fact,
      ff.ind_gene_fina_flex = 0
  WHERE ff.cod_moti_rech IS NULL
    AND ff.num_lote_gene_flex IS NULL
    AND ff.cod_peri = lv_cod_peri_rest_1
    AND EXISTS (
       SELECT NULL
       FROM
        fac_progr_cierr fpc,
        zon_zona zz,
        zon_secci zs,
        zon_terri_admin zta,
        mae_clien_unida_admin mcua
       WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND mcua.ind_acti = 1
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND fpc.cod_zona = zz.cod_zona
         AND fpc.fec_cier = lv_fec_fact
         AND mcua.clie_oid_clie = ff.oid_clie
         AND fpc.est_cier = 'A'
         AND fpc.tip_cier = 'Z');

  gv_des_log:='Actualizando la deuda final';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
    SET ff.val_sald_deud_fina = --CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(ff.oid_clie,lv_cod_peri)
     (SELECT  SUM(mcc.imp_pend)
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.clie_oid_clie = ff.oid_clie
       AND mcc.perd_oid_peri = lv_oid_peri_rest_1
      AND mcc.imp_pend > 0)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina;

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 9
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.val_sald_deud_fina - lv_mont_mini_deud > ff.val_sald_maxi_camp;

  gv_des_log:=' *** Consultoras que no cumplieron con el pago ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='     Calculando el Monto Flexipago a Financiar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_flex_fina = LEAST(ff.val_sald_deud_fina, ff.val_mont_flex_fina_proy)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 0;

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 8
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.val_mont_flex_fina < lv_imp_mini_flex_fina
  AND ff.ind_gene_fina_flex = 0;

  gv_des_log:=' *** Consultoras que con monto de financiacion insuficiente ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT COUNT(*)
  INTO lv_cant_cons_fina_real
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 0;

  gv_des_log:=' *** Cantidad de Consultoras a Financiar Real: ' || lv_cant_cons_fina_real;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cons_fina_real = 0 THEN
   RAISE e_no_gene_regi_real;
  END IF;

  gv_des_log:='     Actualizando el indicador a las consultoras a financiar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.ind_gene_fina_flex = 1
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.val_mont_flex_fina >= lv_imp_mini_flex_fina
  AND ff.ind_gene_fina_flex = 0;

  gv_des_log:='Actualizando el Oid Zona de las Consultoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.oid_zona_fina = FIN_PKG_GENER.FIN_FN_OBTIE_OID_ZONA_CONSU(ff.oid_clie)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle1 = ROUND(ff.val_mont_flex_fina/2,2)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle1 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle1 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle1,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando los interes para la  Cuota Flexipago 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_inte_fle1 =  FLX_FN_OBTIE_MONTO_INTER_FLEXI(ff.cod_peri_cuot_fle1,ff.val_num_dias_camp_cuot_fle1,ff.val_mont_cuot_fle1)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Calculando la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_mont_cuot_fle2 = ff.val_mont_flex_fina - ff.val_mont_cuot_fle1
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Generando el documento de referencia de la Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.val_refe_nume_docu_fle2 = SUBSTR(TO_CHAR(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF'),1,20)
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Obteniendo la  Fecha de Vencimiento Cuota Flexipago 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.fec_venc_cuot_fle2 = FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,  FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(ff.cod_peri_cuot_fle2,1) )
                              ,ff.oid_zona_fina,'CV')
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas en la entidad FLX_CUOTA_FLEXI_FACTU_CABEC';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Generando las Cuotas Flexipago
  INSERT INTO flx_cuota_flexi_factu_cabec
   SELECT
     ff.num_lote_gene_flex, -- num_lote  varchar2(15)
     ff.cod_peri,           -- cod_peri  varchar2(6)
     lv_fec_fact,           -- fec_fact  date
     ff.cod_clie,           -- cod_clie  varchar2(15)
     ff.oid_clie,           -- oid_clie  number(12)
     ff.oid_zona,           -- oid_zona  number(12)
     NULL,                  -- val_nume_soli  number(10)
     ff.val_pedi_base_cata, -- val_pedi_base_cata  number(12,2)
     0,                     -- val_mont_soli_fina  number(12,2)
     ff.val_mont_flex_fina, -- val_mont_fina  number(12,2)
     0,                     -- val_mont_fina_desg  number(12,2)
     0,                     -- val_mont_fina_canc  number(12,2)
     ff.val_porc_conv_pedi, -- val_fact_conv  number(12,2)
     ff.oid_soli_cabe,      -- oid_soli_cabe  number(12)
     0,                     -- val_mont_pedi_base  number(12,2)
     ff.soca_oid_soli_cabe  -- soca_oid_soli_cabe  number(12)
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Registrando las cuotas 1 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- 1era Cuota Flexipago
  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,      --num_lote  varchar2(15)
    ff.cod_peri,                --cod_peri  varchar2(6)
    lv_fec_fact,                --fec_fact  date
    ff.cod_clie,                --cod_clie  varchar2(15)
    ff.oid_clie,                --oid_clie  number(12)
    ff.oid_zona,                --oid_zona  number(12)
    NULL,                       --oid_form_pago  number(12)
    NULL,                       --val_nume_soli  number(10)
    2,                          --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle1,      --oid_peri_cuot_flex  number(12)
    NULL,                       --fec_venc_cuot_flex  date
    NULL,                       --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle1,      --val_mont_cuot_flex  number(12,2)
    NULL,                       --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle1,      --val_mont_carg_uso  number(12,2)
    NULL,                       --oid_soli_cabe_carg_uso  number(12)
    NULL,                        --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle1,  -- val_refe_nume_docu,
    NULL,                        -- fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

  -- 2da Cuota Flexipago
  gv_des_log:='Registrando las cuotas 2 en la entidad FLX_CUOTA_FLEXI_FACTU_DETAL Total : ' || SQL%ROWCOUNT ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO flx_cuota_flexi_factu_detal
   SELECT
    ff.num_lote_gene_flex,  --num_lote  varchar2(15)
    ff.cod_peri,            --cod_peri  varchar2(6)
    lv_fec_fact,            --fec_fact  date
    ff.cod_clie,            --cod_clie  varchar2(15)
    ff.oid_clie,            --oid_clie  number(12)
    ff.oid_zona,            --oid_zona  number(12)
    NULL,                   --oid_form_pago  number(12)
    NULL,                   --val_nume_soli  number(10)
    3,                      --val_nume_orde_cuot  number(12)
    ff.oid_peri_cuot_fle2,   --oid_peri_cuot_flex  number(12)
    NULL,                   --fec_venc_cuot_flex  date
    NULL,                   --oid_movi_carg_flex  number(12)
    ff.val_mont_cuot_fle2,  --val_mont_cuot_flex  number(12,2)
    NULL,                   --oid_movi_carg_uso  number(12)
    ff.val_mont_inte_fle2,  --val_mont_carg_uso  number(12,2)
    NULL,                   --oid_soli_cabe_carg_uso  number(12)
    NULL,                   --oid_soli_cons_cabe_carg_uso  number(12)
    0,                          --val_sald_cuot_flex
    ff.val_refe_nume_docu_fle2,  -- val_refe_nume_docu
    NULL,                        --fec_gene_inte
    ff.soca_oid_soli_cabe       -- oid_soli_cabe
   FROM flx_gener_finan_consu_flexi ff
   WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
    AND ff.ind_gene_fina_flex = 1;

  gv_des_log:='Inicio Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN
   FLX_PR_GENER_CUENT_CORRI_FLEXI(lv_num_lote_gene_fina);
  EXCEPTION
   WHEN OTHERS THEN
     RAISE e_erro_proc_inte;
  END;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 1';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 2;

  gv_des_log:='Actualizando en flx_cuota_flexi_factu_detal el oid_movi_cc de las cuotas 2';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_flex = (
     SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.subp_oid_subp_crea = 203
     AND mcc.clie_oid_clie = fd.oid_clie
     AND mcc.imp_movi = fd.val_mont_cuot_flex
     AND mcc.val_refe_nume_docu_exte = fd.val_refe_nume_docu)
  WHERE fd.num_lote = lv_num_lote_gene_fina
  AND fd.oid_movi_carg_flex IS NULL
  AND fd.val_nume_orde_cuot = 3;

  gv_des_log:='Fin Generando la Cuenta Corriente para las Cuotas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN e_no_gene_regi THEN
   gv_des_log := '*** No existen consultoras por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_no_gene_regi_real THEN
   gv_des_log := '*** No existen consultoras reales por generar financiamiento *** ';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log:='Fin del Proceso con exito';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  WHEN e_erro_proc_inte THEN
   gv_des_log := ' *** Error la generacion de la Cuenta Corriente de las Cuotas : FLX_PR_GENER_CUENT_CORRI_FLEXI ***';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_des_log := 'Fin del Proceso con errores' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error en la Generacion de la cuenta Corriente de las Cuotas ***');

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_CUOTA_FINAN_CIERR;

 PROCEDURE FLX_PR_GENER_CUENT_CORRI_FLEXI(
  p_num_lote                       IN   flx_gener_finan_consu_flexi.num_lote%TYPE)
 IS

  lv_num_lote_gene_fina            fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote_abon_flex            fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote_perc_flex            fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote_cuot_fle1            fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote_cuot_fle2            fin_inter_ejecu.num_lote%TYPE;
  lv_fact_calc_perc                per_porce_perce.fac_calc_agen_perC%TYPE;
  lv_ind_gene_perc                 ccc_param_gener.val_para%TYPE;

  lv_cant_abon_flex                NUMBER(12);
  lv_cant_cuot_fle1                NUMBER(12);
  lv_cant_cuot_fle2                NUMBER(12);
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                 fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_cuen_corr_flex;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cuenta Corriente Flexipago';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_num_lote_gene_fina := p_num_lote;

  gv_des_log:='Numero de Lote Generacion Financiamiento : ' || lv_num_lote_gene_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_ind_gene_perc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorGeneracionPercepcionFlexipago');

  lv_num_lote_abon_flex := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  lv_num_lote_perc_flex := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  lv_num_lote_cuot_fle1 := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  lv_num_lote_cuot_fle2 := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;

  --Generando el Abono No Monetario
  SELECT COUNT(*)
  INTO lv_cant_abon_flex
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;

  IF lv_cant_abon_flex > 0 THEN

   gv_des_log := '   Se van a generar abonos : ' || lv_cant_abon_flex;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   IF lv_ind_gene_perc = 'S' THEN

    SELECT round(p.por_agen_nper/100,2)
    INTO lv_fact_calc_perc
    FROM per_porce_perce p;

    gv_des_log := '   ***   ';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    gv_des_log := '   Lote de Generacion de Abonos Monetarios : ' || lv_num_lote_abon_flex;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    gv_des_log := '   Cantidad de Abonos Monetarios por generar : ' || lv_cant_abon_flex;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);


    INSERT INTO ccc_carga_cargo_abono_masiv
     SELECT
      lv_num_lote_abon_flex,             --num_lote  varchar2(25)
      rownum,                            --val_fila  number(10)
      ff.cod_clie,                       --cod_clie  varchar2(15)
      ROUND((ff.val_mont_flex_fina/(1 + lv_fact_calc_perc)),2),             --imp_movi  number(12,2)
      NULL,                              --imp_movi_vali  varchar2(250)
      USER,                              --cod_usua  varchar2(20)
      trunc(SYSDATE),                    --fec_proc  date
      ff.oid_peri,
      ff.cod_peri,
      trunc(SYSDATE),
      NULL,
      NULL
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
       AND ff.ind_gene_fina_flex = 1;

     CCC_PKG_PROCE.CCC_PR_PROCE_CADIR_DETAL_MASIV(gc_oid_tipo_subp_abon_flex,lv_num_lote_abon_flex,USER);

    gv_des_log := '   Insertando la percepcion de los abonos';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    INSERT INTO ccc_carga_cargo_abono_masiv
     SELECT
      lv_num_lote_perc_flex,             --num_lote  varchar2(25)
      rownum,                            --val_fila  number(10)
      ff.cod_clie,                       --cod_clie  varchar2(15)
      ff.val_mont_flex_fina - ROUND((ff.val_mont_flex_fina/(1 + lv_fact_calc_perc)),2),             --imp_movi  number(12,2)
      NULL,                              --imp_movi_vali  varchar2(250)
      USER,                              --cod_usua  varchar2(20)
      trunc(SYSDATE),                    --fec_proc  date
      ff.oid_peri,
      ff.cod_peri,
      trunc(SYSDATE),
      NULL,
      NULL
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
       AND ff.ind_gene_fina_flex = 1;

     CCC_PKG_PROCE.CCC_PR_PROCE_CADIR_DETAL_MASIV(gc_oid_tipo_subp_perc_flex,lv_num_lote_perc_flex,USER);

  ELSE

   INSERT INTO ccc_carga_cargo_abono_masiv
    SELECT
     lv_num_lote_abon_flex,             --num_lote  varchar2(25)
     rownum,                            --val_fila  number(10)
     ff.cod_clie,                       --cod_clie  varchar2(15)
     ff.val_mont_flex_fina,             --imp_movi  number(12,2)
     NULL,                              --imp_movi_vali  varchar2(250)
     USER,                              --cod_usua  varchar2(20)
     trunc(SYSDATE),                    --fec_proc  date
     ff.oid_peri,
     ff.cod_peri,
     trunc(SYSDATE),
     NULL,
     NULL
    FROM flx_gener_finan_consu_flexi ff
    WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
      AND ff.ind_gene_fina_flex = 1;

    CCC_PKG_PROCE.CCC_PR_PROCE_CADIR_DETAL_MASIV(gc_oid_tipo_subp_abon_flex,lv_num_lote_abon_flex,USER);

   end IF;

  ELSE

    gv_des_log := '   No se van a generar abonos' ;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END IF;

  --Generando la Cuota 1
  SELECT COUNT(*)
  INTO lv_cant_cuot_fle1
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;

  gv_des_log := '   ***   ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Lote de Generacion de Cuotas 1 : ' || lv_num_lote_cuot_fle1;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Cantidad de Cuotas 1 por generar : ' || lv_cant_cuot_fle1;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cuot_fle1 > 0 THEN

    INSERT INTO ccc_carga_cargo_abono_masiv
     SELECT
      lv_num_lote_cuot_fle1,             --num_lote  varchar2(25)
      rownum,                            --val_fila  number(10)
      ff.cod_clie,                       --cod_clie  varchar2(15)
      ff.val_mont_cuot_fle1,             --imp_movi  number(12,2)
      NULL,                              --imp_movi_vali  varchar2(250)
      USER,                              --cod_usua  varchar2(20)
      SYSDATE,                           --fec_proc  date
      ff.oid_peri_cuot_fle1,
      ff.cod_peri_cuot_fle1,
      ff.fec_venc_cuot_fle1,
      ff.val_refe_nume_docu_fle1,
      NULL
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

    CCC_PKG_PROCE.CCC_PR_PROCE_CADIR_DETAL_MASIV(gc_oid_tipo_subp_carg_flex,lv_num_lote_cuot_fle1,USER);

  END IF;

  --Generando la Cuota 2

  SELECT COUNT(*)
  INTO lv_cant_cuot_fle2
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
  AND ff.ind_gene_fina_flex = 1;

  gv_des_log := '   ***   ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Lote de Generacion de Cuotas 2 : ' || lv_num_lote_cuot_fle2;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Cantidad de Cuotas 2 por generar : ' || lv_cant_cuot_fle2;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF lv_cant_cuot_fle2 > 0 THEN

    INSERT INTO ccc_carga_cargo_abono_masiv
     SELECT
      lv_num_lote_cuot_fle2,             --num_lote  varchar2(25)
      rownum,                            --val_fila  number(10)
      ff.cod_clie,                       --cod_clie  varchar2(15)
      ff.val_mont_cuot_fle2,             --imp_movi  number(12,2)
      NULL,                              --imp_movi_vali  varchar2(250)
      USER,                              --cod_usua  varchar2(20)
      SYSDATE,                           --fec_proc  date
      ff.oid_peri_cuot_fle2,
      ff.cod_peri_cuot_fle2,
      ff.fec_venc_cuot_fle2,
      ff.val_refe_nume_docu_fle2,
      NULL
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.num_lote_gene_flex = lv_num_lote_gene_fina
     AND ff.ind_gene_fina_flex = 1;

    CCC_PKG_PROCE.CCC_PR_PROCE_CADIR_DETAL_MASIV(gc_oid_tipo_subp_carg_flex,lv_num_lote_cuot_fle2,USER);

  END IF;

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_CUENT_CORRI_FLEXI;

 PROCEDURE FLX_PR_PROCE_ACTUA_INTER_FLEX(
  p_oid_peri                      IN   cra_perio.oid_peri%TYPE)
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_num_dias_camp               NUMBER(3);
  ln_num_deci                    seg_moned.num_deci%TYPE;
  lv_cod_pais                    seg_pais.cod_pais%TYPE;

 BEGIN
   
   lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
   
   select sm.num_deci
     INTO ln_num_deci
     from seg_moned sm, seg_pais sp
    where sm.oid_mone = sp.mone_oid_mone
      and sp.cod_pais = lv_cod_pais;

  SELECT spc.cod_peri, cp.fec_fina - cp.fec_inic + 1
  INTO lv_cod_peri, lv_num_dias_camp
  FROM cra_perio cp,
       seg_perio_corpo spc
  WHERE cp.peri_oid_peri = spc.oid_peri
  AND cp.oid_peri = p_oid_peri;

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.val_sald_cuot_flex = (
      SELECT SUM(mcc.imp_pend)
      FROM ccc_movim_cuent_corri mcc
      where mcc.oid_movi_cc = fd.oid_movi_carg_flex)
  WHERE fd.oid_peri_cuot_flex >= p_oid_peri
    AND fd.fec_gene_inte IS NULL;

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.val_mont_carg_uso = round(FLX_FN_OBTIE_MONTO_INTER_FLEXI(lv_Cod_peri,lv_num_dias_camp,fd.val_sald_cuot_flex),ln_num_deci)
  WHERE fd.oid_peri_cuot_flex >= p_oid_peri
    AND fd.fec_gene_inte IS NULL;

 END FLX_PR_PROCE_ACTUA_INTER_FLEX;

 PROCEDURE FLX_PR_GENER_CARGO_INTER_FLEXI
 IS

  lv_cod_peri                       seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                       bas_ctrl_fact.fec_proc%TYPE;
  lv_oid_peri                       cra_perio.oid_peri%TYPE;
  lv_oid_tipo_soli_pais             ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_num_lote                       fin_inter_ejecu.num_lote%TYPE;
  lv_oid_prod_inte_flex             mae_produ.oid_prod%TYPE;
  lv_cod_tipo_docu_lega             fac_tipo_docum.cod_tipo_docu%TYPE;
  lv_oid_tipo_docu_lega             fac_tipo_docum.oid_tipo_docu%TYPE;

  lv_id_proc_ejec                   fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                   fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                       fin_proce_ejecu.usu_proc%TYPE;
  lv_cod_erro                       VARCHAR2(250);

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_carg_inte_flex;

  SELECT mp.oid_prod
  INTO lv_oid_prod_inte_flex
  FROM mae_produ mp
  WHERE mp.cod_sap = gc_cod_prod_carg_inte_flex;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cargos por Uso Flexipago';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  SELECT ptsp.oid_tipo_soli_pais
  INTO lv_oid_tipo_soli_pais
  FROM
    ped_tipo_solic pts,
    ped_tipo_solic_pais ptsp
  WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
    AND pts.cod_tipo_soli = 'SOC';

  lv_num_lote := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  gv_des_log:='Numero de Lote : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_num_lote := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  gv_des_log:='Actulizando los intereses flexipago : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FLX_PR_PROCE_ACTUA_INTER_FLEX(lv_oid_peri);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.fec_gene_inte = lv_fec_fact
  WHERE fd.oid_peri_cuot_flex = lv_oid_peri
    AND EXISTS (
         SELECT NULL
         FROM ped_solic_cabec psc
         WHERE psc.fec_prog_fact = lv_fec_fact
           AND psc.grpr_oid_grup_proc = 3
           AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
           AND psc.clie_oid_clie = fd.oid_clie );


  gv_des_log:='Insertando los cargos x uso flexipago en GP4 ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_cod_tipo_docu_lega := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('TipoDocuLegaCargFlex');

  SELECT ftd.oid_tipo_docu
  INTO lv_oid_tipo_docu_lega
  FROM fac_tipo_docum ftd
  WHERE ftd.cod_tipo_docu = lv_cod_tipo_docu_lega;

  INSERT INTO ccc_carga_cadir_docle_masiv
   SELECT
    lv_num_lote,     --num_lote  varchar2(25)
    ROWNUM,          --val_fila  number(10)
    a.cod_clie,      --cod_clie  varchar2(15)
    NULL,            --soca_oid_soli_cabe  number(12)
    NULL,            --num_bole_desp_refe  number(12,2)
    NULL,            --soca_oid_docu_refe  number(12)
    NULL,            --cod_unic_vent  varchar2(6)
    lv_oid_prod_inte_flex,           --prod_oid_prod  number(12)
    1,               --val_cant  number(12,2)
    NULL,            --num_docu_lega_refe  number(10)
    NULL,            --oid_tipo_docu  number(12)
    lv_oid_tipo_docu_lega,              --oid_tipo_docu_refe  number(12)
    imp_pend    ,-- imp_movi  number(12,2)
    NULL,             -- imp_movi_vali  varchar2(250)
    lv_log_user,           -- cod_usua  varchar2(20)
    SYSDATE           -- fec_proc  DATE
  FROM
    (SELECT
      d.cod_clie, SUM(d.val_mont_carg_uso) imp_pend
     FROM
      flx_cuota_flexi_factu_detal d
     WHERE d.oid_peri_cuot_flex = lv_oid_peri
       AND EXISTS (
            SELECT NULL
            FROM ped_solic_cabec psc
            WHERE psc.fec_prog_fact = lv_fec_fact
              AND psc.grpr_oid_grup_proc = 3
              AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
              AND psc.clie_oid_clie = d.oid_clie )
       AND NOT EXISTS (
           SELECT NULL
           FROM ccc_movim_cuent_corri mcc
           WHERE mcc.subp_oid_subp_crea = 204
             AND mcc.perd_oid_peri = lv_oid_peri
             AND mcc.clie_oid_clie = d.oid_clie )
       GROUP BY d.cod_clie
       HAVING SUM(d.val_mont_carg_uso)> 0  ) a;

  gv_des_log:='Se van a generar ' || SQL%ROWCOUNT || ' cargos x uso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Generando las solicitudes por intereses en GP4';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FLX_PR_GENER_SOLIC_CARGO_FLEXI(lv_num_lote);

  gv_des_log:='Fin del Proceso Correctamente';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec, 2);

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_CARGO_INTER_FLEXI;

 PROCEDURE FLX_PR_GENER_INTER_FLEXI_CIERR
 IS

  lv_cod_peri                       seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                       bas_ctrl_fact.fec_proc%TYPE;
  lv_oid_peri                       cra_perio.oid_peri%TYPE;
  lv_oid_tipo_soli_pais             ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_num_lote                       fin_inter_ejecu.num_lote%TYPE;
  lv_oid_prod_inte_flex             mae_produ.oid_prod%TYPE;
  lv_cod_tipo_docu_lega             fac_tipo_docum.cod_tipo_docu%TYPE;
  lv_oid_tipo_docu_lega             fac_tipo_docum.oid_tipo_docu%TYPE;

  lv_id_proc_ejec                   fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                   fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                       fin_proce_ejecu.usu_proc%TYPE;
  lv_cod_erro                       VARCHAR2(250);

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_inte_flex_cier;

  SELECT mp.oid_prod
  INTO lv_oid_prod_inte_flex
  FROM mae_produ mp
  WHERE mp.cod_sap = gc_cod_prod_carg_inte_flex;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Interes Flexipago en el Cierre';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  SELECT ptsp.oid_tipo_soli_pais
  INTO lv_oid_tipo_soli_pais
  FROM
    ped_tipo_solic pts,
    ped_tipo_solic_pais ptsp
  WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
    AND pts.cod_tipo_soli = 'SOC';

  lv_num_lote := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  gv_des_log:='Numero de Lote : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_num_lote := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  gv_des_log:='Actulizando los intereses flexipago : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FLX_PR_PROCE_ACTUA_INTER_FLEX(lv_oid_peri);

  -- Generando los Pedidos de Servicio --
  lv_num_lote := ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;
  gv_des_log:='Numero de Lote : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Insertando los pedidos de servicio por cargos x uso flexipago en GP4 ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_cod_tipo_docu_lega := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('TipoDocuLegaCargFlex');

  SELECT ftd.oid_tipo_docu
  INTO lv_oid_tipo_docu_lega
  FROM fac_tipo_docum ftd
  WHERE ftd.cod_tipo_docu = lv_cod_tipo_docu_lega;

  INSERT INTO ccc_carga_cadir_docle_masiv
   SELECT
    lv_num_lote,     --num_lote  varchar2(25)
    ROWNUM,          --val_fila  number(10)
    b.cod_clie,      --cod_clie  varchar2(15)
    NULL,            --soca_oid_soli_cabe  number(12)
    NULL,            --num_bole_desp_refe  number(12,2)
    NULL,            --soca_oid_docu_refe  number(12)
    NULL,            --cod_unic_vent  varchar2(6)
    lv_oid_prod_inte_flex,           --prod_oid_prod  number(12)
    1,               --val_cant  number(12,2)
    NULL,            --num_docu_lega_refe  number(10)
    NULL,            --oid_tipo_docu  number(12)
    lv_oid_tipo_docu_lega,              --oid_tipo_docu_refe  number(12)
    imp_pend,-- imp_movi  number(12,2)
    NULL,             -- imp_movi_vali  varchar2(250)
    lv_log_user,           -- cod_usua  varchar2(20)
    SYSDATE           -- fec_proc  DATE
  FROM
    (SELECT
      d.cod_clie, SUM(d.val_mont_carg_uso) imp_pend
     FROM
      flx_cuota_flexi_factu_detal d
     WHERE d.oid_peri_cuot_flex = lv_oid_peri
       AND NOT EXISTS (
            SELECT NULL
           FROM ccc_movim_cuent_corri mcc
           WHERE mcc.subp_oid_subp_crea = 204
             AND mcc.perd_oid_peri = lv_oid_peri
             AND mcc.clie_oid_clie = d.oid_clie )
       AND NOT EXISTS (
            SELECT NULL
            FROM ped_solic_cabec psc
            WHERE psc.fec_prog_fact = lv_fec_fact
              AND psc.grpr_oid_grup_proc = 3
              AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
              AND psc.clie_oid_clie = d.oid_clie )
      AND EXISTS (
       SELECT NULL
       FROM
        fac_progr_cierr fpc,
        zon_zona zz,
        zon_secci zs,
        zon_terri_admin zta,
        mae_clien_unida_admin mcua
       WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND mcua.ind_acti = 1
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND fpc.cod_zona = zz.cod_zona
         AND fpc.fec_cier = lv_fec_fact
         AND mcua.clie_oid_clie = d.oid_clie
         AND fpc.est_cier = 'A'
         AND fpc.tip_cier = 'Z')
       HAVING SUM(d.val_mont_carg_uso)> 0
       GROUP BY d.cod_clie ) b;

  gv_des_log:='Se van a generar ' || SQL%ROWCOUNT || ' pedidos de servicio cargos x uso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Generando los pedidos de servicio';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FLX_PR_GENER_SOLIC_CARGO_FLEXI(lv_num_lote);

  ----------------------------------------
  gv_des_log:='Actualizando los Oids de las Solicitudes Generadas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_soli_cabe_carg_uso = (
            SELECT psc.oid_soli_cabe
            FROM ped_solic_cabec psc
            WHERE psc.clie_oid_clie = fd.oid_clie
              AND psc.grpr_oid_grup_proc = 4
              AND psc.fec_fact = lv_fec_fact
              AND psc.tspa_oid_tipo_soli_pais = gc_oid_tipo_soli_carg_uso
           )
  WHERE EXISTS
    ( SELECT NULL
      FROM
       flx_cuota_flexi_factu_detal d,
       ccc_movim_cuent_corri mcc
      WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND mcc.imp_pend > 0
        AND d.oid_peri_cuot_flex = lv_oid_peri
        AND d.oid_clie = fd.oid_clie);

  gv_des_log:='Fin del Proceso Correctamente';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec, 2);

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_INTER_FLEXI_CIERR;

 PROCEDURE FLX_PR_GENER_SOLIC_CARGO_FLEXI(
  p_num_lote                     IN   ccc_carga_cadir_docle_masiv.num_lote%TYPE)
 IS

  lv_cod_pais                    seg_pais.cod_pais%TYPE;
  lv_oid_peri                    cra_perio.oid_peri%TYPE;
  lv_num_iden_cuot_inic          ccc_numer_ident_cuota.val_ulti_nume_iden_cuot%TYPE;
  lv_oid_esta_posi               ped_estad_posic.oid_esta_posi%TYPE;
  lv_oid_tipo_soli_pais          ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_oid_alma                    ped_tipo_solic_pais.almc_oid_alma%TYPE;
  lv_oid_tipo_cons               ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  lv_oid_tipo_docu               ped_tipo_solic_pais.tido_oid_tipo_docu%TYPE;
  lv_oid_acti                    ped_tipo_solic_pais.cact_oid_acti%TYPE;
  lv_oid_sbac                    ped_tipo_solic.sbac_oid_sbac%TYPE;
  lv_oid_soci                    ped_tipo_solic_pais.soci_oid_soci%TYPE;
  lv_oid_mone                    ped_tipo_solic_pais.mone_oid_mone%TYPE;
  lv_oid_clas_soli               ped_tipo_solic.clso_oid_clas_soli%TYPE;
  lv_oid_pais                    ped_tipo_solic_pais.pais_oid_pais%TYPE;
  lv_val_glos                    ped_tipo_solic_pais.val_glos%TYPE;
  lv_oid_tipo_posi               ped_tipo_solic_proce.tpos_oid_tipo_posi%TYPE;
  lv_oid_subt_posi               ped_tipo_solic_proce.stpo_oid_subt_posi%TYPE;
  lv_oid_prod                    ped_tipo_solic_proce.prod_oid_prod%TYPE;
  lv_ind_pedi_prue               ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  lv_ind_perm_unio               ped_tipo_solic_pais.ind_perm_unio%TYPE;
  lv_oid_tipo_clie               ped_tipo_solic.ticl_oid_tipo_clie%TYPE;
  lv_ind_tipo_nega               ped_tipo_solic.ind_soli_nega%TYPE;
  lv_oid_form_pago_carg_flex     bel_forma_pago.oid_form_pago%TYPE;
  lv_oid_form_pago               bel_forma_pago.oid_form_pago%TYPE;
  lv_fec_fact                    bas_ctrl_fact.fec_proc%TYPE;
  lv_cod_tipo_docu_lega          fac_tipo_docum.cod_tipo_docu%TYPE;
  lv_oid_tipo_docu_lega          fac_tipo_docum.oid_tipo_docu%TYPE;
  lv_ind_sign                    NUMBER(1);
  lv_oid_tasa                    NUMBER(12);
  lv_tasa                        NUMBER(12,2);

  lv_cant_carg_masi              NUMBER(6);

  lv_id_proc_ejec                fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                    fin_proce_ejecu.usu_proc%TYPE;
  lv_cod_erro                    VARCHAR2(250);

  CURSOR cur_carg_masi
  IS
      SELECT
         ped_soca_seq.NEXTVAL                     OID_SOLI_CABE,
         lv_fec_fact                              FEC_PROG_FACT,
         NULL                                                    FEC_FACT,
         0                                                           NUM_CLIEN ,
         NULL                                                    VAL_GRUP_REVE,
         lv_oid_tipo_soli_pais                        TSPA_OID_TIPO_SOLI_PAIS ,
         NULL                                                    MONE_OID_MONE ,
         3                                                           TIDS_OID_TIPO_DESP,
         lv_oid_alma                                           ALMC_OID_ALMA,
         23                                                         MODU_OID_MODU,
         lv_oid_tipo_clie                                 TICL_OID_TIPO_CLIE ,
         NULL                                                   TAIM_OID_TASA_IMPU,
         lv_oid_peri                                           PERD_OID_PERI ,
         NULL                                                   SOCA_OID_SOLI_CABE,
         mc.oid_clie                                           CLIE_OID_CLIE,
         mc.oid_clie                                           CLIE_OID_CLIE_RECE_FACT,
         mc.oid_clie                                           CLIE_OID_CLIE_PAGA,
         mc.oid_clie                                           CLIE_OID_CLIE_DEST,
         mcd.oid_clie_dire                                CLDI_OID_CLIE_DIRE,
         mci.tdoc_oid_tipo_docu                      TDOC_OID_TIPO_DOCU ,
         lv_oid_soci                                           SOCI_OID_SOCI ,
         lv_oid_sbac                                           SBAC_OID_SBAC ,
         zt.oid_terr                                            TERR_OID_TERR ,
         zz.oid_zona                                            ZZON_OID_ZONA ,
         NULL                                                      IND_ESTA ,
         NULL                                                      IND_IMPR ,
         NULL                                                      IND_EXEN_FLET ,
         lv_num_iden_cuot_inic + rownum - 1      VAL_NUME_SOLI ,
         USER                                               VAL_USUA,
         0                                                              VAL_TASA_IMPU ,
         TRUNC(SYSDATE)                     FEC_CRON ,
         lv_ind_perm_unio                   IND_PERM_UNIO_SOL ,
         NULL                               IND_GENE_CC ,
         NULL                               IND_APLI_MANU ,
         1                                  VAL_TIPO_CAMB ,
         NULL                               NUM_DOCU_CONT_INTE ,
         0                                  NUM_DOCU_ORIG,
         NULL                               VAL_LOTE_REPO_FALT ,
         NULL                               FEC_REPO_FALT ,
         0                                  VAL_BASE_FLET_LOCA,
         0                                  VAL_IMPO_FLET_LOCA,
         0                                          VAL_IMPO_FLET_TOTA_LOCA,
         0                                          VAL_IMPO_FLET_SIN_IMPU_TOTA,
         0                                          VAL_RECA_FLET_LOCA,
         0                                          VAL_OTRO_RECA_LOCA,
         cad.imp_movi                               VAL_TOTA_PAGA_LOCA,
         0                                          VAL_PREC_CATA_TOTA_LOCA,
         0                                          VAL_PREC_CATA_SIN_IMPU_TOTA,
         0                                          VAL_PREC_FACT_TOTA_LOCA,
         0                                          VAL_IMPO_IMPU_TOTA_LOCA,
         0                                          VAL_IMPO_DESC_1_TOTA_LOCA,
         0                                          VAL_IMPO_DESC_1_TOTA_DOCU ,
         0                                          VAL_IMPO_DESC_1_SIN_IMPU_TOTA ,
         0                                          VAL_IMPO_DESC_3_TOTA_DOCU ,
         0                                          VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
         0                                          VAL_IMPO_DESC_TOTA_LOCA,
         0                                          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
         0                                          VAL_IMPO_REDO_LOCA,
         0                                          VAL_BASE_FLET_DOCU ,
         0                                          VAL_IMPO_FLET_DOCU ,
         0                                          VAL_IMPO_DESC_TOTA_DOCU ,
         0                                          VAL_IMPO_FLET_SIN_IMPU_DOCU ,
         0                                          VAL_RECA_FLET_DOCU ,
         0                                          VAL_OTRO_RECA_DOCU ,
         0                                          VAL_TOTA_FLET_DOCU,
         0                                          VAL_IMPO_FLET_TOTA_DOCU ,
         0                                          VAL_TOTA_FLET_LOCA,
         0                                          VAL_TOTA_PAGA_DOCU,
         0                                          VAL_PREC_CATA_TOTA_DOCU ,
         0                                          VAL_PREC_CATA_SIN_IMPU_TOTA_DO ,
         0                                          VAL_PREC_CONT_TOTA_LOCA ,
         0                                          VAL_PREC_CONT_SIN_IMPU_TOTA,
         0                                          VAL_PREC_CONT_SIN_IMPU_TOTA_1,
         0                                          VAL_PREC_FACT_TOTA_DOCU ,
         0                                          VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
         0                                          VAL_PREC_NETO_TOTA_DOCU,
         0                                          VAL_PREC_NETO_TOTA_LOCA,
         0                                          VAL_IMPO_IMPU_TOTA_DOCU ,
         0                                          VAL_IMPO_REDO_DOCU ,
         0                                          VAL_IMPO_REDO_CONS_LOCA,
         0                                          VAL_IMPO_REDO_CONS_DOCU ,
         NULL                                       VAL_UNID_DEMA_REAL_TOTA ,
         NULL                                       NUM_UNID_POR_ATEN_TOTA,
         NULL                                       NUM_UNID_ATEN_TOTA,
         0                                          IND_OC ,
         lv_ind_pedi_prue                           IND_PEDI_PRUE ,
         1                                          IND_TS_NO_CONSO ,
         NULL                                       VAL_GLOS_OBSE ,
         NULL                                       VAL_OBSE_REVI ,
         NULL                                       NUM_PREM,
         0                                          VAL_IMPO_DESC_3_TOTA_LOCA ,
         0                                          VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC ,
         lv_oid_pais                                PAIS_OID_PAIS ,
         cad.tido_oid_tipo_docu                          TIDO_OID_TIPO_DOCU ,
         zt.vepo_oid_valo_estr_geop                 VEPO_OID_VALO_ESTR_GEOP ,
         NULL                                       RECQ_OID_RESU_CHEQ,
         1                                          ESSO_OID_ESTA_SOLI ,
         NULL                                       COPA_OID_PARA_GENE ,
         4                                          GRPR_OID_GRUP_PROC ,
         1                                          SBTI_OID_SUBT_CLIE ,
         1                                          ACFI_OID_ACCE_FISI ,
         lv_oid_tipo_cons                           TSPA_OID_TIPO_SOLI_PAIS_CONS ,
         lv_oid_form_pago                           FOPA_OID_FORM_PAGO ,
         NULL                                       CLIE_OID_CONS_ASOC ,
         NULL                                       ESPE_OID_ESTA_PEDI ,
         lv_oid_clas_soli                           CLSO_OID_CLAS_SOLI ,
         zta.oid_terr_admi                          ZTAD_OID_TERR_ADMI ,
         NULL                                       INRE_OID_INDI_REVI ,
         44                                         OPER_OID_OPER  ,
         1                                          PROC_OID_PROC  ,
         TO_NUMBER(cad.soca_oid_docu_refe)           SOCA_OID_DOCU_REFE ,
         NULL                                       TCCL_OID_TCCL_FLET ,
         NULL                                       CLAS_OID_CLAS_FLET ,
         NULL                                       VAL_PUNT_EMIS ,
         NULL                                       NUM_LOTE_FACT ,
         NULL                                       VAL_PREC_CONT_TOTA_DOCU ,
         0                                          IND_INTE_LARI_GENE ,
         TO_CHAR(SYSDATE,'YYYYMMDD')                FEC_PROG_FACT_COMP ,
         NULL                                       ICTP_OID_TIPO_PROG ,
         NULL                                       ICTP_OID_CONC_TIPO_PROG ,
         NULL                                       VAL_ORIG_CHEQ ,
         NULL                                       VAL_IMPO_IVA_ASUM_EMPR,
         NULL                                       VAL_GANA_TOTA_LOCA,
         NULL                                       VAL_GANA_TOTA_DOCU,
         NULL                                       VAL_TASA_FLET,
         NULL                                       VAL_RECA_FLET,
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
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL
      FROM
         (SELECT
             cad.num_lote ,
             cad.cod_clie,
             cad.soca_oid_docu_refe,
             lv_oid_tipo_docu_lega tido_oid_tipo_docu,
             SUM(TO_NUMBER(cad.imp_movi)) imp_movi
          FROM ccc_carga_cadir_docle_masiv cad
          WHERE cad.num_lote = p_num_lote
          GROUP BY cad.num_lote , cad.cod_clie, cad.soca_oid_docu_refe,cad.oid_tipo_docu_refe) cad,
          mae_clien mc,
          mae_clien_direc mcd,
          mae_clien_unida_admin mcua,
          mae_clien_ident mci,
          zon_terri_admin zta,
          zon_secci zs,
          zon_terri zt,
          zon_zona zz
    WHERE cad.cod_clie=mc.cod_clie
    AND mcua.clie_oid_clie=mc.oid_clie
    AND mcua.ind_acti=1
    AND mc.oid_clie=mcd.clie_oid_clie
    AND mcd.ind_dire_ppal=1
    AND mcd.ind_elim=0
    AND mc.oid_clie=mci.clie_oid_clie
    AND mci.val_iden_docu_prin=1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zta.zscc_oid_secc=zs.oid_secc
    AND zta.terr_oid_terr=zt.oid_terr
    AND cad.num_lote=p_num_lote;

   CURSOR cur_carg_masi_deta
   IS
      SELECT
         ped_sopo_seq.NEXTVAL,         --OID_SOLI_POSI
         ROWNUM,                                --COD_POSI
         p_num_lote,                               --VAL_LOTE_PROD
         1,                                                 --NUM_UNID_DEMA
         1,                                                 --NUM_UNID_POR_ATEN
         lv_tasa,                                       --VAL_TASA_IMPU
         det.soca_oid_soli_cabe,             --SOCA_OID_SOLI_CABE
         lv_oid_tasa,                                --TAIM_OID_TASA_IMPU
         lv_oid_tipo_posi,                        --TPOS_OID_TIPO_POSI
         det.prod_oid_prod,                    --PROD_OID_PROD
         lv_oid_form_pago_carg_flex,                         --FOPA_OID_FORM_PAGO
         NULL,                                         --IND_LIMI_VENT
         NULL,                                         --IND_CTRL_STOC
         NULL,                                         --IND_CTRL_LIQU
         det.imp_movi*lv_ind_sign,         --VAL_PREC_CATA_UNIT_LOCA,
         0,                                                --VAL_PREC_CONT_UNIT_LOCA,
         0,                                                --VAL_PREC_CATA_UNIT_DOCU,
         0,                                                --VAL_PREC_CONTA_UNIT_DOCU,
         0,                                                --VAL_PREC_FACT_UNIT_LOCA,
         0,                                                --VAL_PREC_FACT_UNIT_DOCU,
         0,                                                --VAL_PREC_SIN_IMPU_UNIT_LOCA,
         0,                                                --VAL_PREC_SIN_IMPU_UNIT_DOCU,
         0,                                                --VAL_PREC_SIN_IMPU_TOTA_DOCU,
         0,                                                --VAL_IMPO_DESC_UNIT_LOCA,
         NULL,                                         --VAL_IMPO_DESC_UNIT_DOCU,
         0,                                                --VAL_PREC_NETO_UNIT_LOCA,
         0,                                                --VAL_PREC_NETO_TOTA_DOCU,
         0,                                                --VAL_PREC_NETO_UNIT_DOCU,
         0,                                                --VAL_PREC_TOTA_TOTA_LOCA,
         0,                                                --VAL_PREC_TOTA_TOTA_DOCU,
         0,                                                --VAL_IMPO_IMPU_UNIT_LOCA,
         0,                                                --VAL_IMPO_IMPU_UNIT_DOCU,
         0,                                                --VAL_IMPO_DESC_TOTA_DOCU,
         0,                                                --VAL_IMPO_IMPU_TOTA_LOCA,
         0,                                                --VAL_IMPO_IMPU_TOTA_DOCU,
         0,                                                --VAL_IMPO_DESC_TOTA_LOCA,
         0,                                                --VAL_PREC_TOTA_UNIT_LOCA,
         0,                                                --VAL_PREC_TOTA_UNIT_DOCU,
         0,                                                --VAL_PREC_CONT_TOTA_LOCA,
         0,                                                --VAL_PREC_CATA_TOTA_LOCA,
         0,                                                --VAL_PREC_CATA_TOTA_DOCU,
         0,                                                --VAL_PREC_CONT_TOTA_DOCU,
         NULL,                                         --VAL_PORC_DESC,
         0,                                                --VAL_PREC_CATA_TOTA_LOCA_UNID,
         1,                                                 --NUM_UNID_DEMA_REAL,
         1,                                                  --NUM_UNID_COMPR,
         NULL,                                          --NUM_UNID_CAMB,
         NULL,                                          --NUM_UNID_VENT,
         NULL,                                          --NUM_UNID_ATEN,
         NULL,                                          --VAL_CODI_VENT_FICT,
         0,                                                 --VAL_PREC_FACT_TOTA_LOCA,
         0,                                                 --VAL_PREC_FACT_TOTA_DOCU,
         0,                                                 --VAL_PREC_SIN_IMPU_TOTA_LOCA,
         0,                                                 --VAL_PREC_NETO_TOTA_LOCA,
         NULL,                                          --OFDE_OID_DETA_OFER,
         lv_oid_esta_posi,                        --ESPO_OID_ESTA_POSI,
         lv_oid_subt_posi,                        --STPO_OID_SUBT_POSI,
         NULL,                                          --IND_RECU_OBLI,
         NULL,                                          --VAL_CODI_VENT,
         NULL,                                          --SOPO_OID_SOLI_POSI,
         0,                                                 --IND_NO_IMPR,
         NULL,                                          --IND_DENT_FUER_CAJA_BOLS,
         NULL,                                          --VAL_CATA,
         NULL,                                          --NUM_PAGI_CATA,
         NULL,                                          --NUM_CONS,
         NULL,                                          --NUM_DOCU_CONT_INTE,
         NULL,                                          --VAL_EJER_DOCU_CONT_INTE,
         NULL,                                          --VAL_IMPO_DES_SIN_IMP_UNIT_LOCA,
         NULL,                                          --VAL_IMPO_DES_SIN_IMP_UNIT_DOCU,
         NULL,                                          --VAL_IMPO_DES_SIN_IMP_TOTA,
         NULL,                                          --VAL_IMPO_DES_SIN_IMP_TOTA_DOCU,
         NULL,                                           --VAL_OBSE,
         NULL,                                           --VAL_PREC_PUBL_UNIT_LOCA
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
         NULL,
         null,
         null,
         null,
         null,
         NULL
        FROM ccc_carga_cadir_docle_masiv det
        WHERE det.num_lote = p_num_lote;


      TYPE t_tab_ped_solic_cabe                      IS TABLE OF ped_solic_cabec%ROWTYPE;
      TYPE t_tab_ped_solic_posi                       IS TABLE OF ped_solic_posic%ROWTYPE;
      TYPE t_tab_oid_soli_cabe                         IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
      TYPE t_tab_oid_clie                                  IS TABLE OF mae_clien.oid_clie%TYPE;
      TYPE t_tab_imp_movi                                IS TABLE OF ped_solic_cabec.val_tota_paga_loca%TYPE;
      TYPE t_tab_soca_oid_docu_refe             IS TABLE OF ped_solic_cabec.soca_oid_docu_refe%TYPE;

  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
      lv_cod_soci                                  seg_socie.cod_soci%TYPE;
      lv_tab_ped_soli_cabe                               t_tab_ped_solic_cabe;
      lv_tab_ped_soli_posi                                 t_tab_ped_solic_posi;
      lv_tab_oid_soli_cabe                                 t_tab_oid_soli_cabe;
      lv_tab_oid_clie                                          t_tab_oid_clie;
      lv_tab_imp_movi                                        t_tab_imp_movi;
      lv_tab_soca_oid_docu_refe                     t_tab_soca_oid_docu_refe;

  BEGIN

   lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
   lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
   lv_cod_tipo_docu_lega := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('TipoDocuLegaCargFlex');

   SELECT ftd.oid_tipo_docu
   INTO lv_oid_tipo_docu_lega
   FROM fac_tipo_docum ftd
   WHERE ftd.cod_tipo_docu = lv_cod_tipo_docu_lega;

   lv_log_user     := USER;
   lv_log_cod_proc := gc_cod_gene_soli_carg_flex;

   FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

      SELECT fp.oid_form_pago
      INTO lv_oid_form_pago_carg_flex
      FROM bel_forma_pago fp
      WHERE fp.cod_form_pago = gc_cod_form_pago_carg_flex;

      gv_des_log:='Inicio CCC_PR_GENER_SOLIC_CARGO_FLEXI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

      SELECT
          b.oid_tipo_soli_pais,
          b.almc_oid_alma,
          b.fopa_oid_form_pago,
          b.tsol_oid_tipo_cons,
          b.tido_oid_tipo_docu,
          b.cact_oid_acti,
          a.sbac_oid_sbac,
          b.soci_oid_soci,
          b.mone_oid_mone,
          a.clso_oid_clas_soli,
          b.pais_oid_pais,
          b.val_glos,
          g.tpos_oid_tipo_posi,
          g.stpo_oid_subt_posi,
          g.prod_oid_prod,
          b.ind_pedi_prue,
          b.ind_perm_unio,
          a.ticl_oid_tipo_clie,
          a.ind_soli_nega
       INTO
          lv_oid_tipo_soli_pais,
          lv_oid_alma,
          lv_oid_form_pago,
          lv_oid_tipo_cons,
          lv_oid_tipo_docu,
          lv_oid_acti,
          lv_oid_sbac,
          lv_oid_soci,
          lv_oid_mone,
          lv_oid_clas_soli,
          lv_oid_pais,
          lv_val_glos,
          lv_oid_tipo_posi,
          lv_oid_subt_posi,
          lv_oid_prod,
          lv_ind_pedi_prue,
          lv_ind_perm_unio,
          lv_oid_tipo_clie,
          lv_ind_tipo_nega
       FROM
             ped_tipo_solic a,
             ped_tipo_solic_pais b,
             ped_tipo_solic_proce g
          WHERE b.tsol_oid_tipo_soli=a.oid_tipo_soli
          AND a.cod_tipo_soli= gc_cod_tipo_soli_carg_flex
          AND g.OPER_OID_OPER = 44
          AND  g.TSPA_OID_TIPO_SOLI_PAIS=b.OID_TIPO_SOLI_PAIS;

      -- Obteniendo el estado de la posicion
      SELECT pep.oid_esta_posi
      INTO lv_oid_esta_posi
      FROM ped_estad_posic pep
      WHERE pep.cod_esta_posi='CO';

      SELECT
         DECODE(zz.OID_TASA_IMPU,NULL,z.OID_TASA_IMPU,zz.OID_TASA_IMPU) ,
         DECODE(zz.VAL_TASA_IMPU,NULL,z.VAL_TASA_IMPU,zz.VAL_TASA_IMPU)
         INTO lv_oid_tasa, lv_tasa
      FROM
           ped_impue_gener v,
           ped_tasa_impue z,
           ped_impue_negoc x,
           mae_produ y,
           ped_tasa_impue zz
      WHERE v.pais_oid_pais= lv_oid_pais
      AND v.sbac_oid_sbac= lv_oid_sbac
      AND v.taim_oid_tasa_impu=z.oid_tasa_impu
      AND x.nego_oid_nego(+)=y.nego_oid_nego
      AND x.taim_oid_tasa_impu=zz.oid_tasa_impu(+)
      AND y.oid_prod= lv_oid_prod;

      IF lv_ind_tipo_nega=1 THEN
        lv_ind_sign:=-1;
      ELSE
         lv_ind_sign:=1;
      END IF;

      -- Obtener la cantidad de Cargos Masivos a procesar
      SELECT COUNT(*)
      INTO lv_cant_carg_masi
      FROM ccc_carga_cadir_docle_masiv
      WHERE num_lote = p_num_lote;

      gv_des_log:='Cantidad de CAD a Procesar : ' || lv_cant_carg_masi;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

      lv_num_iden_cuot_inic:=ccc_pkg_gener.CCC_FN_OBTIE_NUMER_IDENT_CUOTA(lv_cod_pais,lv_cod_soci,'000',lv_cant_carg_masi);

      gv_des_log:='Inicio : Insertando en PED_SOLIC_CABEC';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

      OPEN cur_carg_masi;
      LOOP
         FETCH cur_carg_masi BULK COLLECT INTO  lv_tab_ped_soli_cabe LIMIT w_filas;

         IF lv_tab_ped_soli_cabe.COUNT > 0 THEN

            FORALL x IN lv_tab_ped_soli_cabe.FIRST .. lv_tab_ped_soli_cabe.LAST
                INSERT INTO ped_solic_cabec VALUES lv_tab_ped_soli_cabe(x)
                RETURNING oid_soli_cabe ,
                          clie_oid_clie,
                          val_tota_paga_loca,
                          soca_oid_docu_refe
                BULK COLLECT INTO lv_tab_oid_soli_cabe,
                                  lv_tab_oid_clie,
                                  lv_tab_imp_movi,
                                  lv_tab_soca_oid_docu_refe;

             FORALL y IN lv_tab_ped_soli_cabe.FIRST .. lv_tab_ped_soli_cabe.LAST
                UPDATE ccc_carga_cadir_docle_masiv cad
                SET cad.soca_oid_soli_cabe = lv_tab_oid_soli_cabe(y)
                WHERE (SELECT mc.oid_clie
                       FROM mae_clien mc
                       WHERE mc.cod_clie = cad.cod_clie) = lv_tab_oid_clie(y)
                AND cad.imp_movi = lv_tab_imp_movi(y)
                AND cad.num_lote = p_num_lote;

         END IF;
         EXIT WHEN cur_carg_masi%NOTFOUND;
      END LOOP;
      CLOSE cur_carg_masi;

      gv_des_log:='Inicio : Insertando en PED_SOLIC_POSIC';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

      OPEN cur_carg_masi_deta;
      LOOP
         FETCH cur_carg_masi_deta BULK COLLECT INTO  lv_tab_ped_soli_posi LIMIT w_filas;
            IF lv_tab_ped_soli_posi.COUNT > 0 THEN

               FORALL z IN 1 .. lv_tab_ped_soli_posi.COUNT
                  INSERT INTO ped_solic_posic VALUES lv_tab_ped_soli_posi(z);

            END IF;

         EXIT WHEN cur_carg_masi_deta%NOTFOUND;
      END LOOP;

      CLOSE cur_carg_masi_deta;

      gv_des_log:='Registrando en el Historico de Cargos Procesados';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO ccc_histo_cadir_docle_masiv
   SELECT *
   FROM ccc_carga_cadir_docle_masiv  cdm
   WHERE cdm.num_lote=p_num_lote;

  DELETE FROM ccc_carga_cadir_docle_masiv  cdm
  WHERE cdm.num_lote=p_num_lote;


  gv_des_log:='Fin CCC_PR_CARGA_CARGO_ABONO_MASIV';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu, lv_log_cod_proc, lv_id_proc_ejec, 2);

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_SOLIC_CARGO_FLEXI;

 PROCEDURE FLX_PR_PROCE_REGIS_INTER_FLEX
 IS

  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_oid_peri_adic_1               cra_perio.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_adic_1               seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
  lv_cod_pais                      seg_pais.cod_pais%TYPE;

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  lv_cod_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1);
  lv_oid_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_adic_1);

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_soli_cabe_carg_uso = (
            SELECT MIN(psc.oid_soli_cabe)
            FROM ped_solic_cabec psc
            WHERE psc.clie_oid_clie = fd.oid_clie
              AND psc.grpr_oid_grup_proc = 5
              AND psc.fec_fact = lv_fec_fact
              AND psc.tspa_oid_tipo_soli_pais = gc_oid_tipo_soli_carg_uso)
  WHERE fd.oid_peri_cuot_flex = lv_oid_peri
    AND fd.fec_gene_inte = lv_fec_fact;

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_soli_cons_cabe_carg_uso =
        (SELECT MIN(psc.soca_oid_soli_cabe)
         FROM ped_solic_cabec psc
         WHERE psc.oid_soli_cabe = fd.oid_soli_cabe_carg_uso
           AND psc.clie_oid_clie = fd.oid_clie
           AND psc.perd_oid_peri = lv_oid_peri)
  WHERE fd.oid_peri_cuot_flex = lv_oid_peri
    AND fd.fec_gene_inte = lv_fec_fact;

  UPDATE flx_cuota_flexi_factu_detal fd
  SET fd.oid_movi_carg_uso =
         (SELECT MIN(mcc.oid_movi_cc)
          FROM ccc_movim_cuent_corri mcc
          WHERE mcc.soca_oid_soli_cabe = fd.oid_soli_cons_cabe_carg_uso
            AND mcc.clie_oid_clie = fd.oid_clie
            AND mcc.perd_oid_peri = lv_oid_peri
            AND mcc.subp_oid_subp_crea = gc_oid_subp_inte_flex)
   WHERE fd.oid_peri_cuot_flex = lv_oid_peri
     AND fd.fec_gene_inte = lv_fec_fact;

  UPDATE ccc_movim_cuent_corri mcc
     SET mcc.soca_oid_soli_cabe =
           (SELECT MIN(fc.soca_oid_soli_cabe)
            FROM flx_cuota_flexi_factu_cabec fc
            WHERE fc.oid_clie = mcc.clie_oid_clie
              AND fc.fec_fact = lv_fec_fact)
   WHERE mcc.subp_oid_subp_crea = gc_oid_subp_carg_flex
     AND EXISTS (
            SELECT fd.oid_movi_carg_flex
            FROM flx_cuota_flexi_factu_detal fd
            WHERE fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
              AND fd.fec_fact = lv_fec_fact
              AND  fd.oid_movi_carg_flex = mcc.oid_movi_cc);


 END FLX_PR_PROCE_REGIS_INTER_FLEX;

 PROCEDURE FLX_PR_GENER_PROYE_FINAN_FLEXI
 IS

  lv_oid_tipo_soli_pais            ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_num_lote                      ccc_carga_cargo_abono_masiv.num_lote%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                      ped_solic_cabec.fec_prog_fact%TYPE;
  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_peri_adic_1               seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_adic_2               seg_perio_corpo.cod_peri%TYPE;

  lv_reg_ccc_cuota_flexi_factu     flx_cuota_flexi_factu_cabec%ROWTYPE;

  lv_oid_tipo_soli_pais_cons       NUMBER(12);
  lv_oid_peri                      NUMBER(12);
  lv_oid_peri_adic_1               NUMBER(12);
  lv_oid_peri_adic_2               NUMBER(12);
  lv_num_dias_peri_adic_1          NUMBER(12);
  lv_num_dias_peri_adic_2          NUMBER(12);
  lv_imp_mini_flex                 NUMBER(12,2);
  lv_imp_mini_flex_fina            NUMBER(12,2);
  lv_cant_cons_fina                NUMBER(12);
  lv_num_deci                      NUMBER(2);
  
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;
  lv_cod_erro                      VARCHAR2(250);

  lv_ind_flex_web                  ccc_param_gener.val_para%TYPE:= CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorFlexipagoWeb');
    
 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_gene_proy_fina_flex;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Consultoras Habiles Flexipago';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_imp_mini_flex := TO_NUMBER(CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('MontoMinimoFlexipago'),'9999.99');
  gv_des_log:='Monto Minimo Flexipago : ' || lv_imp_mini_flex ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  SELECT sm.num_deci
  INTO lv_num_deci
  FROM seg_moned sm, seg_pais sp
  WHERE sm.oid_mone = sp.mone_oid_mone
    AND sp.cod_pais = lv_cod_pais;

  BEGIN

   FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

   lv_cod_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1);
   lv_oid_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_adic_1);

   SELECT cp.fec_fina - cp.fec_inic + 1
   INTO lv_num_dias_peri_adic_1
   FROM cra_perio cp
   WHERE cp.oid_peri = lv_oid_peri_adic_1;

   lv_cod_peri_adic_2 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,2);
   lv_oid_peri_adic_2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_adic_2);

   SELECT cp.fec_fina - cp.fec_inic + 1
   INTO lv_num_dias_peri_adic_2
   FROM cra_perio cp
   WHERE cp.oid_peri = lv_oid_peri_adic_2;

   lv_reg_ccc_cuota_flexi_factu.cod_peri := lv_cod_peri;
   lv_reg_ccc_cuota_flexi_factu.fec_fact := lv_fec_fact;

  EXCEPTION

   WHEN NO_DATA_FOUND THEN
    gv_des_log:='ERROR!!! No Existe Campa?a Activa : ';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
    RAISE e_no_exis_camp_acti;

  END;

  gv_des_log:='Fecha Facturacion ' || lv_fec_fact ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Campa?a Activa : ' || lv_cod_peri ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_peri);
  --lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('COD_SOCI');
  lv_imp_mini_flex_fina := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteMinimoFinanciamientoFlexipago');

  lv_num_lote:= ccc_pkg_gener.CCC_FN_OBTIE_NUMER_LOTE;

  gv_des_log:='Numero de Lote : ' || lv_num_lote ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN

   SELECT ptsp.oid_tipo_soli_pais
   INTO lv_oid_tipo_soli_pais
   FROM
    ped_tipo_solic pts,
    ped_tipo_solic_pais ptsp
   WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
     AND pts.cod_tipo_soli = 'SOC';

   SELECT ptsp.oid_tipo_soli_pais
   INTO lv_oid_tipo_soli_pais_cons
   FROM
    ped_tipo_solic pts,
    ped_tipo_solic_pais ptsp
   WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
     AND pts.cod_tipo_soli = 'C1';

  EXCEPTION

   WHEN NO_DATA_FOUND THEN

    gv_des_log:='ERROR!!! No Existe parametria para el Tipo de Solicitud Pais : ';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
    RAISE e_no_para_tipo_soli;

  END;

  SELECT COUNT(*)
  INTO lv_cant_cons_fina
  FROM
   ped_solic_cabec psc,
   mae_clien mc,
   flx_consu_habil_flexi ch,
   cra_perio cp,
   seg_perio_corpo spc,
   int_solic_conso_cabec con
  WHERE psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
    AND psc.perd_oid_peri = cp.oid_peri
    AND cp.peri_oid_peri = spc.oid_peri
    AND psc.clie_oid_clie = mc.oid_clie
    AND mc.cod_clie = ch.cod_clie
    AND mc.cod_clie = con.cod_clie
    AND ch.cod_clie = con.cod_clie
    AND ch.cod_peri_fact =  lv_cod_peri
    AND con.soca_oid_soli_cabe_refe = psc.oid_soli_cabe
    AND psc.grpr_oid_grup_proc = 5
    AND psc.fec_fact = lv_fec_fact
    AND con.ind_envi_sto = 1
    AND NOT EXISTS (
      SELECT NULL
      FROM flx_gener_finan_consu_flexi ff
      WHERE ff.oid_clie = mc.oid_clie
        AND ff.cod_peri = lv_cod_peri
        AND ff.fec_fact = lv_fec_fact)
    AND mc.oid_clie NOT IN (
      SELECT psc.clie_oid_clie
      FROM ped_solic_cabec psc
      WHERE psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
       AND psc.perd_oid_peri = lv_oid_peri
       AND psc.fec_fact = lv_fec_fact
      HAVING COUNT(*) > 1
      GROUP BY psc.clie_oid_clie);

  IF lv_cant_cons_fina = 0 THEN
   RAISE e_no_exis_movi;
  END IF;

  gv_des_log:='Insertando las consultoras a generar el financiamiento : ' || lv_cant_cons_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO flx_gener_finan_consu_flexi
   SELECT
    lv_num_lote,                   -- NUM_LOTE                    NUMBER(12),
    lv_oid_peri,                   -- OID_PERI                    NUMBER(12),
    lv_cod_peri,                   -- COD_PERI                    VARCHAR2(6),
    lv_fec_fact,                   -- FEC_FACT                    DATE,
    mc.oid_clie,                   -- OID_CLIE                    NUMBER(12),
    mc.cod_clie,                   -- COD_CLIE                    VARCHAR2(15),
    psc.oid_soli_cabe,             -- OID_SOLI_CABE               NUMBER(12),
    psc.soca_oid_soli_cabe,        -- SOCA_OID_SOLI_CABE          NUMBER(12),
    psc.zzon_oid_zona,             -- OID_ZONA                    NUMBER(12),
    ch.ind_habi,                   -- IND_HABI                    NUMBER(1),
    ch.ind_acti,                   -- IND_ACTI                    NUMBER(1),
    ch.val_pedi_base,              -- VAL_PEDI_BASE_CATA          NUMBER(12,2),
    ch.val_line_cred val_line_cred_disp_cata, -- VAL_LINE_CRED_DISP_CATA     NUMBER(12,2),
    NULL,                          -- VAL_VENT_CATA_CONSO         NUMBER(12,2),
    NULL,                          -- VAL_TOTA_FACTU              NUMBER(12,2),
    NULL,                          -- VAL_TOTA_ATEN_CATA          NUMBER(12,2),
    NULL,                          -- VAL_PORC_CONV_PEDI          NUMBER(12,2),
    NULL,                          -- VAL_CUOT_21DI_PEDI_VIGE     NUMBER(12,2),
    NULL,                          -- VAL_MONT_FLEX_FINA_PROY     NUMBER(12,2),
    lv_oid_peri_adic_1,            -- OID_PERI_CUOT_FLE1          NUMBER(12),
    lv_cod_peri_adic_1,            -- COD_PERI_CUOT_FLE1          VARCHAR2(6),
    NULL,                          -- VAL_MONT_CUOT_FLE1_PROY     NUMBER(12,2),
    lv_oid_peri_adic_2,            -- OID_PERI_CUOT_FLE2          NUMBER(12),
    lv_cod_peri_adic_2,            -- COD_PERI_CUOT_FLE2          VARCHAR2(6),
    NULL,                          -- VAL_MONT_CUOT_FLE2_PROY     NUMBER(12,2),
    lv_num_dias_peri_adic_1,       -- VAL_NUM_DIAS_CAMP_CUOT_FLE1 NUMBER(12),
    NULL,                          -- VAL_MONT_INTE_FLE1_PROY     NUMBER(12,2),
    lv_num_dias_peri_adic_2,       -- VAL_NUM_DIAS_CAMP_CUOT_FLE2 NUMBER(12),
    NULL,                          -- VAL_MONT_INTE_FLE2_PROY     NUMBER(12,2),
    NULL,                          -- VAL_MONT_MINI_PAGA          NUMBER(12,2),
    lv_oid_peri_adic_1,            -- OID_PERI_SALD_MAXI_CAMP     NUMBER(12),
    0,                             -- VAL_SALD_MAXI_CAMP          NUMBER(12,2),
    NULL,                          -- VAL_SALD_FLEX_ANTE          NUMBER(12,2),
    NULL,                          -- VAL_SALD_OTRO_ANTE          NUMBER(12,2),
    NULL,                          -- VAL_MONT_PAGA_SINF          NUMBER(12,2),
    NULL,                          -- VAL_PORC_TCEA               NUMBER(12,2),
    NULL,                          -- VAL_PORC_TCEM               NUMBER(12,2),
    NULL,                          -- COD_MOTI_RECH               VARCHAR2(2),
    NULL,                          -- VAL_SALD_DEUD_FINA          NUMBER(12,2),
    NULL,                          -- NUM_LOTE_GENE_FLEX          NUMBER(12),
    NULL,                          -- IND_GENE_FINA_FLEX          NUMBER(1),
    NULL,                          -- FEC_GENE_FINA               DATE,
    NULL,                          -- OID_ZONA_FINA               NUMBER(12),
    NULL,                          -- VAL_MONT_FLEX_FINA          NUMBER(12,2),
    NULL,                          -- VAL_MONT_CUOT_FLE1          NUMBER(12,2),
    NULL,                          -- VAL_REFE_NUME_DOCU_FLE1     VARCHAR2(20),
    NULL,                          -- FEC_VENC_CUOT_FLE1          DATE,
    NULL,                          -- VAL_MONT_INTE_FLE1          NUMBER(12,2),
    NULL,                          -- VAL_MONT_CUOT_FLE2          NUMBER(12,2),
    NULL,                          -- VAL_REFE_NUME_DOCU_FLE2     VARCHAR2(20),
    NULL,                          -- FEC_VENC_CUOT_FLE2          DATE,
    NULL,                          -- VAL_MONT_INTE_FLE2          NUMBER(12,2),
    0,                            -- VAL_ABON_PEND_APLI
    0,                             -- VAL_CUPO_PEND_APLI
    0                              -- IND_SEGU_PEDI
   FROM
    ped_solic_cabec psc,
    mae_clien mc,
    flx_consu_habil_flexi ch,
    cra_perio cp,
    seg_perio_corpo spc,
    int_solic_conso_cabec con
   WHERE psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
     AND psc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND psc.clie_oid_clie = mc.oid_clie
     AND mc.cod_clie = ch.cod_clie
     AND mc.cod_clie = con.cod_clie
     AND ch.cod_clie = con.cod_clie
     AND ch.cod_peri_fact =  lv_cod_peri
     AND con.soca_oid_soli_cabe_refe = psc.oid_soli_cabe
     AND psc.grpr_oid_grup_proc = 5
     AND psc.fec_fact = lv_fec_fact
     AND con.ind_envi_sto = 1
     AND NOT EXISTS (
         SELECT NULL
         FROM flx_gener_finan_consu_flexi ff
         WHERE ff.oid_clie = mc.oid_clie
           AND ff.cod_peri = lv_cod_peri
           AND ff.fec_fact = lv_fec_fact)
     AND mc.oid_clie NOT IN (
      SELECT psc.clie_oid_clie
      FROM ped_solic_cabec psc
      WHERE psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
       AND psc.perd_oid_peri = lv_oid_peri
       AND psc.fec_fact = lv_fec_fact
      HAVING COUNT(*) > 1
      GROUP BY psc.clie_oid_clie);

  IF lv_ind_flex_web = 'S' THEN

   UPDATE flx_gener_finan_consu_flexi ff
   SET ff.cod_moti_rech = 'NW'
   WHERE ff.num_lote = lv_num_lote
   AND ff.cod_moti_rech IS NULL
     AND NOT EXISTS
      (SELECT NULL
       FROM int_solic_conso_cabec
       WHERE soca_oid_soli_cabe_refe=ff.oid_soli_cabe
         AND ind_rece_web = 1);

  gv_des_log:='Eliminando Consultoras por no pasar pedido web ' ||  SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END IF;

  gv_des_log:='Se van a realizar la validaciones a ' ||  lv_cant_cons_fina || ' consultoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Inicio Validando los Motivos de Rechazo
  -- Activa
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'NA'
  WHERE f.num_lote = lv_num_lote
   AND f.cod_moti_rech IS NULL
   AND f.ind_acti = 0;

  -- Habil
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'NH'
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL
    AND f.ind_habi = 0;

  --Linea de Credito
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'LC'
  WHERE f.num_lote = lv_num_lote
    AND f.val_line_cred_disp_cata <= 0
    AND f.cod_moti_rech IS NULL;

  -- Calculando la venta catalogo consolidada de la campa?a
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_vent_cata_conso =
      (SELECT SUM(psp.val_prec_cata_unit_loca*psp.num_unid_por_aten)
       FROM
        ped_solic_cabec psc,
        ped_solic_posic psp
      WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
        AND psc.clie_oid_clie = f.oid_clie
        AND psc.perd_oid_peri = lv_oid_peri
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- No supera el pedido base
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'PB'
  WHERE f.num_lote = lv_num_lote
    AND f.val_pedi_base_cata > f.val_vent_cata_conso
    AND f.cod_moti_rech IS NULL;
  -- Fin Validando los Motivos de Rechazo


  -- Calculando los datos a mostrar en el paquete documentario
  -- Valor Total Factura
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_tota_factu =
      (SELECT SUM(psc.val_tota_paga_loca)
       FROM
        ped_solic_cabec psc
       WHERE psc.perd_oid_peri = lv_oid_peri
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais_cons
        AND psc.clie_oid_clie = f.oid_clie)
  WHERE f.num_lote = lv_num_lote
   AND f.cod_moti_rech IS NULL;

  -- Valor Total Atendido Catalogo
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_tota_aten_cata =
      (SELECT SUM(psp.val_prec_cata_tota_loca)
      FROM
       ped_solic_cabec psc,
       ped_solic_posic psp
      WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
        AND psc.perd_oid_peri = lv_oid_peri
        AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
        AND psc.clie_oid_clie = f.oid_clie
        AND psp.num_unid_aten > 0)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Porcentaje de Conversion del Pedido (PCP)
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_porc_conv_pedi =
       CASE
         WHEN f.val_tota_aten_cata > 0 THEN
          ROUND(f.val_tota_factu/f.val_tota_aten_cata,2)
       ELSE
        0
       END
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Cuota a 21 Dias del Pedido Vigente --
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_cuot_21di_pedi_vige =
      GREATEST( ROUND(f.val_pedi_base_cata*f.val_porc_conv_pedi,2) , (f.val_tota_factu - ROUND(f.val_line_cred_disp_cata*f.val_porc_conv_pedi,2)))
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Cuota a 21 Dias del Pedido Vigente --
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'PI'
  WHERE f.val_cuot_21di_pedi_vige >= f.val_tota_factu
    AND f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Obtener Monto Flexipago a Financiar
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_flex_fina_proy = round((f.val_tota_factu - f.val_cuot_21di_pedi_vige),lv_num_deci)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Rechazo por Monto de Financiamiento Insuficiente
  UPDATE flx_gener_finan_consu_flexi f
  SET f.cod_moti_rech = 'FI'
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL
    AND f.val_mont_flex_fina < lv_imp_mini_flex_fina ;

  -- Rechazo por Segundo Financimiento Proyectdo (Se rechaza el primero)
  UPDATE flx_gener_finan_consu_flexi ff
  SET ff.cod_moti_rech = '2P'
  WHERE to_date(ff.fec_fact,'dd/mm/yyyy') =
       (select to_date(min(f1.fec_fact), 'dd/mm/yyyy')
          from flx_gener_finan_consu_flexi f1
         where f1.cod_peri = lv_cod_peri
           and f1.cod_moti_rech is null
           and f1.cod_clie = ff.cod_clie)
    AND ff.cod_peri = lv_cod_peri
    AND ff.cod_clie in (select f2.cod_clie
                        from flx_gener_finan_consu_flexi f2
                        where f2.cod_peri = lv_cod_peri
                          and f2.cod_moti_rech is null
                        group by f2.cod_clie
                        having count(*) >= 2);

  -- Obtener Monto de la Cuota Flexipago
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_cuot_fle1_proy = ROUND(f.val_mont_flex_fina_proy/2,lv_num_deci)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_cuot_fle2_proy = f.val_mont_flex_fina_proy - f.val_mont_cuot_fle1_proy
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Proyectando el Interes Flexipago
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_inte_fle1_proy = FLX_FN_OBTIE_MONTO_INTER_FLEXI(lv_cod_peri_adic_1,f.val_num_dias_camp_cuot_fle1,f.val_mont_cuot_fle1_proy)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_inte_fle2_proy = FLX_FN_OBTIE_MONTO_INTER_FLEXI(lv_cod_peri_adic_2,f.val_num_dias_camp_cuot_fle2,f.val_mont_cuot_fle2_proy)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

--  lv_porc_tcea := lv_q/2 + lv_q**2/4

  -- Obtener el Monto Total a Pagar Sin Financiamiento --
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_mont_paga_sinf = round(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(f.oid_clie, lv_cod_peri_adic_1),lv_num_deci)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Monto Minimo a Pagar y Saldo Maximo sobre Campa?a --
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_sald_maxi_camp = f.val_mont_flex_fina_proy,
      f.val_mont_mini_paga = f.val_mont_paga_sinf - f.val_mont_flex_fina_proy
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  -- Obteniendo el Saldo Flexipago Anterior --
  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_sald_flex_ante = FLX_FN_OBTIE_SALDO_FLEXI_ANTER(f.oid_clie, f.oid_peri)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  UPDATE flx_gener_finan_consu_flexi f
  SET f.val_sald_otro_ante = f.val_mont_mini_paga -(  f.val_cuot_21di_pedi_vige + f.val_sald_flex_ante)
  WHERE f.num_lote = lv_num_lote
    AND f.cod_moti_rech IS NULL;

  gv_des_log:='Fin del Proceso Correctamente';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec, 2);

 EXCEPTION

  WHEN e_no_exis_camp_acti THEN
   RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_GENER_PROYE_FINAN_FLEXI: No Existe Campa?a Activa');

  WHEN e_no_para_tipo_soli THEN
   RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_GENER_PROYE_FINAN_FLEXI: No Existe Parametria para el Tipo de Solicitud SOC');

  WHEN e_no_exis_movi THEN
   gv_des_log:='Todas las consultoras ya fueron financiadas';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := 'Fin del Proceso';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE (gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE (gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,2);
   NULL;


  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FLX_PR_GENER_PROYE_FINAN_FLEXI;

 PROCEDURE FLX_PR_GENER_PADOC_FLEXI_ZONAS
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_peri                      seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                      ped_solic_cabec.fec_prog_fact%TYPE;
  lv_cod_peri_adic_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_adic_1               seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_adic_2               seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_1               seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_rest_2               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_2               seg_perio_corpo.oid_peri%TYPE;
  lv_inic_secc_flex                CHAR(11):= '<flexipago>';
  lv_fina_secc_flex                CHAR(12):='</flexipago>';
  lv_esta_flex                     CHAR(18);

  /*** D09 ***/
  lv_imp_pago_inic                 VARCHAR2(250);
  lv_sald_flex_ante                VARCHAR2(250);
  lv_sald_otro_ante                VARCHAR2(250);
  lv_mont_mini_paga                VARCHAR2(250);
  lv_camp_adic_1                   VARCHAR2(250);
  lv_camp_adic_2                   VARCHAR2(250);
  lv_cuot_flex_1                   VARCHAR2(250);
  lv_cuot_flex_2                   VARCHAR2(250);
  lv_paga_mas                      VARCHAR2(250);
  lv_mont_paga                     VARCHAR2(250);
  lv_part_util_flex                VARCHAR2(4000);

  /*** D11 ***/
  lv_tabl_inte                     VARCHAR2(250);

  /*** D10 ***/
  lv_inic_deta_flex                CHAR(10):='<detflexi>';
  lv_fina_deta_flex                CHAR(11):='</detflexi>';
  lv_inic_detal_flex_nodo          CHAR(5):='<det>';
  lv_fina_detal_flex_nodo          CHAR(6):='</det>';

  lv_pend_cam1                     VARCHAR2(250);
  lv_pend_cam2                     VARCHAR2(250);
  lv_camp_rest_2                   VARCHAR2(250);
  lv_tota_camp_rest_2              VARCHAR2(250);
  lv_mont_paga_rest_2              VARCHAR2(250);
  lv_mont_cuot_flex_2              VARCHAR2(250);
  lv_mont_inte_flex_2              VARCHAR2(250);
  lv_cuot_fle1_pend_2              VARCHAR2(250);
  lv_cuot_fle2_pend_2              VARCHAR2(250);

  lv_camp_rest_1                   VARCHAR2(250);
  lv_tota_camp_rest_1              VARCHAR2(250);
  lv_mont_paga_rest_1              VARCHAR2(250);
  lv_mont_cuot_flex_1              VARCHAR2(250);
  lv_mont_inte_flex_1              VARCHAR2(250);
  lv_cuot_fle1_pend_1              VARCHAR2(250);
  lv_cuot_fle2_pend_1              VARCHAR2(250);
  lv_tasa_tcem                     VARCHAR2(250);
  lv_tasa_tcea                     VARCHAR2(250);
  lv_part_cono_deta                VARCHAR2(4000);

  lv_tota_cuot_flex                NUMBER(12,2);
  lv_imp_cuot_inte_fle1            NUMBER(12,2);
  lv_imp_cuot_inte_fle2            NUMBER(12,2);
  lv_imp_tota_camp                 NUMBER(12,2);
  lv_imp_tota_paga                 NUMBER(12,2);
  lv_imp_cuot_flex                 NUMBER(12,2);
  lv_imp_inte_flex                 NUMBER(12,2);
  lv_imp_cuot_flex_a               NUMBER(12,2);
  lv_imp_cuot_flex_b               NUMBER(12,2);
  lv_imp_cuot_flex_c               NUMBER(12,2);
  lv_imp_tasa_tcem                 NUMBER(12,2);
  lv_imp_tasa_tcea                 NUMBER(12,2);
  lv_max_cuot_flex                 NUMBER(12,2);
  lv_q                             NUMBER(12,8);
  lv_oid_tipo_soli_pais            NUMBER(12);

  lv_mens_tota                     VARCHAR2(4000);
  lv_reg_flx_paque_docum           flx_paque_docum%ROWTYPE;

  CURSOR c_fact_flex(
   pc_cod_peri   VARCHAR2,
   pc_fec_fact   DATE)
  IS
   SELECT
    f.cod_clie,
    f.oid_clie,
    1 ind_acti,
    f.val_cuot_21di_pedi_vige,
    f.val_sald_flex_ante,
    f.val_sald_otro_ante,
    f.val_mont_mini_paga,
    f.val_mont_cuot_fle1_proy,
    f.val_mont_inte_fle1_proy,
    f.val_mont_cuot_fle2_proy,
    f.val_mont_inte_fle2_proy,
    f.val_mont_paga_sinf
   FROM flx_gener_finan_consu_flexi f
   WHERE f.cod_peri = pc_cod_peri
     AND f.fec_fact = pc_fec_fact
     AND f.cod_moti_rech IS NULL
   UNION
      SELECT
        fc.cod_clie,
        fc.oid_clie,
        1 ind_acti,
        NULL val_cuot_21di_pedi_vige,
        NULL val_sald_flex_ante,
        NULL val_sald_otro_ante,
        NULL val_mont_mini_paga,
        NULL val_mont_cuot_fle1_proy,
        NULL val_mont_inte_fle1_proy,
        NULL val_mont_cuot_fle2_proy,
        NULL val_mont_inte_fle2_proy,
        NULL val_mont_paga_sinf
      FROM flx_cuota_flexi_factu_detal fc
      WHERE ((fc.oid_peri_cuot_flex = lv_oid_peri)
      OR (fc.oid_peri_cuot_flex = lv_oid_peri_adic_1))
      AND NOT EXISTS (
         SELECT NULL
         FROM flx_gener_finan_consu_flexi f
         WHERE f.cod_peri = pc_cod_peri
           AND f.fec_fact = pc_fec_fact
           AND f.cod_moti_rech IS NULL
           AND f.oid_clie = fc.oid_clie)
      AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE psc.fec_fact = lv_fec_fact
         AND psc.perd_oid_peri = lv_oid_peri
         AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
         AND psc.clie_oid_clie = fc.oid_clie   );


  r_fact_flex c_fact_flex%ROWTYPE;

 BEGIN

  DELETE FROM flx_paque_docum;

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri);

  lv_cod_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);
  lv_oid_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_rest_1);

  lv_cod_peri_rest_2 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-2);
  lv_oid_peri_rest_2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_rest_2);

  lv_cod_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1);
  lv_oid_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_adic_1);

  lv_cod_peri_adic_2 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,2);

  OPEN c_fact_flex(lv_cod_peri,lv_fec_fact);
  LOOP
   FETCH c_fact_flex INTO r_fact_flex;
   EXIT WHEN c_fact_flex%NOTFOUND;

   BEGIN

    lv_inic_secc_flex := '<flexipago>';
    lv_fina_secc_flex := '</flexipago>';

    lv_esta_flex := '<estado>' || r_fact_flex.ind_acti  || '</estado>';

    /*** D09 ***/
    lv_imp_pago_inic := '<pagoinicial>' || r_fact_flex.val_cuot_21di_pedi_vige || '</pagoinicial>';
    lv_sald_flex_ante := '<cuointanterior>' || r_fact_flex.val_sald_flex_ante  || '</cuointanterior>';
    lv_sald_otro_ante := '<otros>' || r_fact_flex.val_sald_otro_ante || '</otros>';
    lv_mont_mini_paga := '<minmontopagar>' || r_fact_flex.val_mont_mini_paga || '</minmontopagar>';
    lv_camp_adic_1 := '<cfcx1cmp>' || concat('C',substr(lv_cod_peri_adic_1,5,2)) || '</cfcx1cmp>';
    lv_camp_adic_2 := '<cfcx2cmp>' || concat('C',substr(lv_cod_peri_adic_2,5,2)) || '</cfcx2cmp>';
    lv_imp_cuot_inte_fle1 := r_fact_flex.val_mont_cuot_fle1_proy + r_fact_flex.val_mont_inte_fle1_proy;
    lv_imp_cuot_inte_fle2 := r_fact_flex.val_mont_cuot_fle2_proy + r_fact_flex.val_mont_inte_fle2_proy;
    lv_cuot_flex_1 := '<cfcx1>' || lv_imp_cuot_inte_fle1 || '</cfcx1>';
    lv_cuot_flex_2 :='<cfcx2>' || lv_imp_cuot_inte_fle2 || '</cfcx2>';
    lv_paga_mas := '<pagasmas>' || r_fact_flex.val_mont_mini_paga || '</pagasmas>';
    lv_mont_paga := '<montopagar>' || r_fact_flex.val_mont_paga_sinf || '</montopagar>';
    lv_part_util_flex := lv_imp_pago_inic ||
                         lv_sald_flex_ante ||
                         lv_sald_otro_ante  ||
                         lv_mont_mini_paga ||
                         lv_camp_adic_1 ||
                         lv_camp_adic_2 ||
                         lv_cuot_flex_1 ||
                         lv_cuot_flex_2 ||
                         lv_paga_mas ||
                         lv_mont_paga;
    /***********/

    /*** D11 ***/
    lv_tabl_inte := '<tablaint>tabla1</tablaint>';
    /***********/

    /*** D10 ***/
    lv_pend_cam1 := '<pendcmp1>' || concat('C',substr(lv_cod_peri,5,2)) || '</pendcmp1>';
    lv_pend_cam2 := '<pendcmp2>' || concat('C',substr(lv_cod_peri_adic_1,5,2)) || '</pendcmp2>';

    -- Campana X-2--
    lv_camp_rest_2 := '<cmp>' || concat('C',substr(lv_cod_peri_rest_2,5,2)) || '</cmp>';

    SELECT SUM(mcc.imp_movi)--, SUM(mcc.imp_paga)
    INTO lv_imp_tota_camp--, lv_imp_tota_paga
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = r_fact_flex.oid_clie
      AND mcc.perd_oid_peri  = lv_oid_peri_rest_2
      AND mcc.subp_oid_subp_crea NOT IN (203,204)
      AND mcc.imp_movi > 0
      AND EXISTS (
       SELECT NULL
       FROM flx_cuota_flexi_factu_detal fd,
            ccc_movim_cuent_corri ccc
       WHERE fd.cod_peri = lv_cod_peri_rest_2
         AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
         AND fd.oid_clie = mcc.clie_oid_clie
         AND ccc.imp_pend > 0);


    IF (lv_imp_tota_camp IS NOT NULL AND lv_imp_tota_camp > 0) THEN

    lv_tota_camp_rest_2 := '<totalcmp>' || lv_imp_tota_camp || '</totalcmp>';
    --lv_mont_paga_rest_2 := '<montopag>' || lv_imp_tota_paga ||'</montopag>';

      SELECT SUM(mcc.imp_pend)
      INTO lv_imp_cuot_flex
     FROM
        flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND mcc.subp_oid_subp_crea = 203
      AND fd.oid_clie = r_fact_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri, lv_oid_peri_adic_1)
        AND fd.cod_peri = lv_cod_peri_rest_2;

   SELECT  NVL(SUM(mcc.imp_pend),0)
     INTO  lv_tota_cuot_flex
     FROM
       flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
      AND fd.oid_clie = r_fact_flex.oid_clie
      AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_2;

      SELECT SUM(fd.val_mont_carg_uso)
      INTO lv_imp_inte_flex
       FROM
          flx_cuota_flexi_factu_detal fd
      WHERE fd.oid_clie = r_fact_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri, lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_2;

    lv_imp_tota_paga := lv_imp_tota_camp - lv_tota_cuot_flex;

    lv_mont_paga_rest_2 := '<montopag>' || lv_imp_tota_paga ||'</montopag>';

    lv_mont_cuot_flex_2 := '<montocuo>' || lv_imp_cuot_flex || '</montocuo>';
    lv_mont_inte_flex_2 := '<montoint>' || lv_imp_inte_flex || '</montoint>';

     lv_imp_cuot_flex_a := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_fact_flex.oid_clie,lv_cod_peri_rest_2,lv_oid_peri,lv_oid_peri);

     lv_cuot_fle1_pend_2 := '<coutapend1>' || lv_imp_cuot_flex_a || '</coutapend1>';
     lv_cuot_fle2_pend_2 := '<coutapend2></coutapend2>';

    ELSE

     lv_tota_camp_rest_2 := '<totalcmp></totalcmp>';
     lv_mont_paga_rest_2 := '<montopag></montopag>';
     lv_mont_cuot_flex_2 := '<montocuo></montocuo>';
     lv_mont_inte_flex_2 := '<montoint></montoint>';
     lv_cuot_fle1_pend_2 := '<coutapend1></coutapend1>';
     lv_cuot_fle2_pend_2 := '<coutapend2></coutapend2>';

    END IF;

    -- Campana X-1 --
    lv_camp_rest_1 := '<cmp>' || concat('C',substr(lv_cod_peri_rest_1,5,2)) || '</cmp>';

    /*
    Se obtienen los datos solo si ha tenido financiamiento
    sobre esa campa?a y este financiamiento esta pendiente
    */
    SELECT SUM(mcc.imp_movi)
    INTO lv_imp_tota_camp
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = r_fact_flex.oid_clie
      AND mcc.perd_oid_peri = lv_oid_peri_rest_1
      AND mcc.subp_oid_subp_crea NOT IN (203,204)
      AND mcc.imp_movi > 0
      AND EXISTS (
       SELECT NULL
        FROM
         flx_cuota_flexi_factu_detal fd,
            ccc_movim_cuent_corri ccc
       WHERE fd.cod_peri = lv_cod_peri_rest_1
         AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
         AND fd.oid_clie = mcc.clie_oid_clie
         AND ccc.imp_pend > 0);


    IF (lv_imp_tota_camp IS NOT NULL AND lv_imp_tota_camp > 0) THEN

    lv_tota_camp_rest_1 := '<totalcmp>' || lv_imp_tota_camp || '</totalcmp>';

    SELECT NVL(MAX(mcc.imp_pend),0), NVL(SUM(mcc.imp_pend),0)
    INTO lv_max_cuot_flex, lv_tota_cuot_flex
     FROM
       flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
     AND fd.oid_clie = r_fact_flex.oid_clie
      AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_1;

   BEGIN
      SELECT NVL(MAX(mcc.imp_pend),0)
      INTO lv_imp_cuot_flex
      FROM
          flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND mcc.subp_oid_subp_crea = 203
     AND fd.oid_clie = r_fact_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_1
      AND fd.val_mont_cuot_flex = lv_max_cuot_flex
      AND rownum < 2;
      EXCEPTION

      WHEN no_data_found THEN
        lv_imp_cuot_flex := NULL;
      END;


      BEGIN

       SELECT fd.val_mont_carg_uso
       INTO lv_imp_inte_flex
        FROM
            flx_cuota_flexi_factu_detal fd
      WHERE fd.oid_clie = r_fact_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
        AND fd.cod_peri = lv_cod_peri_rest_1
        AND rownum < 2;

    EXCEPTION

       WHEN no_data_found THEN
         lv_imp_inte_flex := NULL;

    END;

    lv_imp_tota_paga := lv_imp_tota_camp - lv_tota_cuot_flex;

    lv_mont_paga_rest_1 := '<montopag>' || lv_imp_tota_paga ||'</montopag>';
    lv_mont_cuot_flex_1 := '<montocuo>' || lv_imp_cuot_flex || '</montocuo>';
    lv_mont_inte_flex_1 := '<montoint>' || lv_imp_inte_flex || '</montoint>';

     lv_imp_cuot_flex_b := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_fact_flex.oid_clie,lv_cod_peri_rest_1,lv_oid_peri,lv_oid_peri);
     lv_imp_cuot_flex_c := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_fact_flex.oid_clie,lv_cod_peri_rest_1,lv_oid_peri,lv_oid_peri_adic_1);

    lv_cuot_fle1_pend_1 := '<coutapend1>' || lv_imp_cuot_flex_b || '</coutapend1>';
    lv_cuot_fle2_pend_1 := '<coutapend2>' || lv_imp_cuot_flex_c || '</coutapend2>';

     ELSE

      lv_tota_camp_rest_1 := '<totalcmp></totalcmp>';
      lv_mont_paga_rest_1 := '<montopag></montopag>';
      lv_mont_cuot_flex_1 := '<montocuo></montocuo>';
      lv_mont_inte_flex_1 := '<montoint></montoint>';
      lv_cuot_fle1_pend_1 := '<coutapend1></coutapend1>';
      lv_cuot_fle2_pend_1 := '<coutapend2></coutapend2>';

    END IF;

    lv_q := FLX_FN_OBTIE_Q(r_fact_flex.oid_clie,lv_cod_peri_rest_1,lv_cod_peri_rest_2);

    IF lv_q IS NOT NULL AND lv_q < 1 THEN

     FLX_PR_OBTIE_VALOR_TASAS(lv_q,lv_imp_tasa_tcea,lv_imp_tasa_tcem);
     lv_tasa_tcem := '<tcem>' || lv_imp_tasa_tcem || '</tcem>';
     lv_tasa_tcea := '<tcea>' || lv_imp_tasa_tcea || '</tcea>';

    ELSE
     lv_tasa_tcem := '<tcem></tcem>';
     lv_tasa_tcea := '<tcea></tcea>';
    END IF;

    lv_part_cono_deta := lv_pend_cam1 ||
                         lv_pend_cam2 ||
                         lv_tasa_tcem ||
                         lv_tasa_tcea ||
                         lv_tabl_inte ||
                         lv_inic_deta_flex ||
                         lv_inic_detal_flex_nodo ||
                         lv_camp_rest_2 ||
                         lv_tota_camp_rest_2 ||
                         lv_mont_paga_rest_2 ||
                         lv_mont_cuot_flex_2 ||
                         lv_mont_inte_flex_2 ||
                         lv_cuot_fle1_pend_2 ||
                         lv_cuot_fle2_pend_2 ||
                         lv_fina_detal_flex_nodo ||
                         lv_inic_detal_flex_nodo ||
                         lv_camp_rest_1 ||
                         lv_tota_camp_rest_1 ||
                         lv_mont_paga_rest_1 ||
                         lv_mont_cuot_flex_1 ||
                         lv_mont_inte_flex_1 ||
                         lv_cuot_fle1_pend_1 ||
                         lv_cuot_fle2_pend_1 ||
                         lv_fina_detal_flex_nodo ||
                         lv_fina_deta_flex;

   lv_mens_tota := lv_inic_secc_flex ||
                   lv_esta_flex ||
                   lv_part_util_flex ||
                   lv_part_cono_deta ||
                   lv_fina_secc_flex;

   lv_reg_flx_paque_docum.xml_cons := lv_mens_tota;

   lv_reg_flx_paque_docum.cod_clie := r_fact_flex.cod_clie;

   INSERT INTO flx_paque_docum VALUES lv_reg_flx_paque_docum;

    END;
   END LOOP;
  CLOSE c_fact_flex;

 END FLX_PR_GENER_PADOC_FLEXI_ZONAS;


 PROCEDURE FLX_PR_GENER_PADOC_FLEXI_ZONA2
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_peri                      seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact                      ped_solic_cabec.fec_prog_fact%TYPE;
  lv_cod_peri_adic_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_adic_1               seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_2               seg_perio_corpo.cod_peri%TYPE;
  lv_inic_secc_flex                CHAR(11):= '<flexipago>';
  lv_fina_secc_flex                CHAR(12):='</flexipago>';
  lv_esta_flex                     CHAR(18);

  lv_mont_mini_paga                VARCHAR2(250);
  lv_cuot_flex_1                   VARCHAR2(250);
  lv_cuot_flex_2                   VARCHAR2(250);
  lv_mont_paga                     VARCHAR2(250);


  lv_imp_cuot_inte_fle1            NUMBER(12,2);
  lv_imp_cuot_inte_fle2            NUMBER(12,2);
  lv_imp_deud_tota                 NUMBER(12,2);

  ln_cuota_pend_1                  NUMBER(12,2);
  ln_cuota_pend_2                  NUMBER(12,2);
  ln_cuota_pend_3                  NUMBER(12,2);

  ln_inte_pend_1                   NUMBER(12,2);
  ln_inte_pend_2                   NUMBER(12,2);
  ln_inte_pend_3                   NUMBER(12,2);

  ln_monto_pend_1                  NUMBER(12,2);
  ln_monto_pend_2                  NUMBER(12,2);
  ln_monto_pend_3                  NUMBER(12,2);

  ln_monto_ped_flex                NUMBER(12,2);

  ln_monto_ped_tot                 NUMBER(12,2);

  lv_cuota_pend_1                  VARCHAR2(250);
  lv_cuota_pend_2                  VARCHAR2(250);
  lv_cuota_pend_3                  VARCHAR2(250);
  lv_cuota_pend_4                  VARCHAR2(250);
  lv_cuota_pend_5                  VARCHAR2(250);

  lv_inte_pend_1                   VARCHAR2(250);
  lv_inte_pend_2                   VARCHAR2(250);
  lv_inte_pend_3                   VARCHAR2(250);
  lv_inte_pend_4                   VARCHAR2(250);
  lv_inte_pend_5                   VARCHAR2(250);
  lv_monto_pend_1                  VARCHAR2(250);
  lv_monto_pend_2                  VARCHAR2(250);
  lv_monto_pend_3                  VARCHAR2(250);
  lv_monto_ped_flex                VARCHAR2(250);
  lv_monto_finan                   VARCHAR2(250);
  lv_monto_ped_tot                 VARCHAR2(250);
  lv_comportamiento                 VARCHAR2(250);
  lv_tasa_tcem                     VARCHAR2(250);
  lv_tasa_tcea                     VARCHAR2(250);
  lv_imp_tasa_tcem                 NUMBER(12,2);
  lv_imp_tasa_tcea                 NUMBER(12,2);
  lv_q                             NUMBER(12,8);
  lv_pedi_base                     NUMBER(12,2);
  lv_linea_credi                   NUMBER(12,2);
  lv_sald_tota                     NUMBER(12,2);
  lv_oid_tipo_soli_pais            NUMBER(12);
  lv_oid_tipo_soli_cons            NUMBER(12);
  lv_mens_tota                     VARCHAR2(4000);
  lv_reg_flx_paque_docum           flx_paque_docum%ROWTYPE;
  l_formatoNumerico                VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'99,999,999,999.99');


  CURSOR c_fact_flex
  IS
   SELECT
    pd.cod_clie,
    pd.oid_clie,
    pd.ind_acti,
    pd.val_cuot_21di_pedi_vige,
    pd.val_sald_flex_ante,
    pd.val_sald_otro_ante,
    pd.val_sald_maxi_camp,
    pd.val_mont_mini_paga,
    pd.val_mont_cuot_fle1_proy,
    pd.val_mont_inte_fle1_proy,
    pd.val_mont_cuot_fle2_proy,
    pd.val_mont_inte_fle2_proy,
    pd.val_mont_paga_sinf
   FROM flx_gener_consu_paque_docum pd;

  r_fact_flex c_fact_flex%ROWTYPE;

 BEGIN

  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri,lv_cod_peri,lv_fec_fact);

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');
  lv_oid_tipo_soli_cons := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');

  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri);

  DELETE FROM flx_paque_docum;

  DELETE FROM flx_gener_consu_paque_docum;

  INSERT INTO flx_gener_consu_paque_docum
   SELECT
    f.cod_clie,
    f.oid_clie,
    1 ind_acti,
    f.val_cuot_21di_pedi_vige,
    f.val_sald_flex_ante,
    f.val_sald_otro_ante,
    f.val_sald_maxi_camp,
    f.val_mont_mini_paga,
    f.val_mont_cuot_fle1_proy,
    f.val_mont_inte_fle1_proy,
    f.val_mont_cuot_fle2_proy,
    f.val_mont_inte_fle2_proy,
    f.val_mont_paga_sinf
   FROM flx_gener_finan_consu_flexi f
   WHERE f.cod_peri = lv_cod_peri
     AND f.fec_fact = lv_fec_fact
     AND f.cod_moti_rech IS NULL;

  INSERT INTO flx_gener_consu_paque_docum
      SELECT
        fc.cod_clie,
        fc.oid_clie,
        1 ind_acti,
        NULL val_cuot_21di_pedi_vige,
        NULL val_sald_flex_ante,
        NULL val_sald_maxi_camp,
        NULL val_sald_otro_ante,
        NULL val_mont_mini_paga,
        NULL val_mont_cuot_fle1_proy,
        NULL val_mont_inte_fle1_proy,
        NULL val_mont_cuot_fle2_proy,
        NULL val_mont_inte_fle2_proy,
        NULL val_mont_paga_sinf
   FROM flx_cuota_flexi_factu_cabec fc
   WHERE NOT EXISTS (
         SELECT NULL
         FROM flx_gener_finan_consu_flexi f
          WHERE f.cod_peri = lv_cod_peri
            AND f.fec_fact = lv_fec_fact
           AND f.cod_moti_rech IS NULL
           AND f.oid_clie = fc.oid_clie)
      AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE psc.fec_fact = lv_fec_fact
             AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_cons
         AND psc.perd_oid_peri = lv_oid_peri
             AND psc.clie_oid_clie = fc.oid_clie)
    GROUP BY
     fc.cod_clie,
     fc.oid_clie;

  lv_cod_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);

  lv_cod_peri_rest_2 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-2);

  lv_cod_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1);
  lv_oid_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri_adic_1);

  OPEN c_fact_flex;
  LOOP
   FETCH c_fact_flex INTO r_fact_flex;
   EXIT WHEN c_fact_flex%NOTFOUND;

   BEGIN

    lv_inic_secc_flex := '<flexipago>';
    lv_fina_secc_flex := '</flexipago>';

    lv_esta_flex := '<estado>' || r_fact_flex.ind_acti  || '</estado>';

    IF r_fact_flex.val_mont_paga_sinf > 0 THEN
     lv_mont_paga := '<montopagar>' || trim(to_char(r_fact_flex.val_mont_paga_sinf,l_formatoNumerico)) || '</montopagar>';
    ELSE
     lv_imp_deud_tota := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_fact_flex.oid_clie,lv_cod_peri_adic_1);
     lv_mont_paga := '<montopagar>' || trim(to_char(lv_imp_deud_tota,l_formatoNumerico)) || '</montopagar>';
    END IF;

    lv_mont_mini_paga := '<minmontopagar>' || trim(to_char(r_fact_flex.val_mont_mini_paga,l_formatoNumerico)) || '</minmontopagar>';
    lv_imp_cuot_inte_fle1 := r_fact_flex.val_mont_cuot_fle1_proy + r_fact_flex.val_mont_inte_fle1_proy;
    lv_imp_cuot_inte_fle2 := r_fact_flex.val_mont_cuot_fle2_proy + r_fact_flex.val_mont_inte_fle2_proy;
    lv_cuot_flex_1 := '<cfcx1>' || trim(to_char(lv_imp_cuot_inte_fle1,l_formatoNumerico)) || '</cfcx1>';
    lv_cuot_flex_2 :='<cfcx2>' || trim(to_char(lv_imp_cuot_inte_fle2,l_formatoNumerico)) || '</cfcx2>';


    lv_cuota_pend_4 :='<cuotapend4>' || trim(to_char(r_fact_flex.val_mont_cuot_fle1_proy,l_formatoNumerico)) || '</cuotapend4>';
    lv_cuota_pend_5 :='<cuotapend5>' || trim(to_char(r_fact_flex.val_mont_cuot_fle2_proy,l_formatoNumerico)) || '</cuotapend5>';

    lv_inte_pend_4 :='<montointpend4>' || trim(to_char(r_fact_flex.val_mont_inte_fle1_proy,l_formatoNumerico)) || '</montointpend4>';
    lv_inte_pend_5 :='<montointpend5>' || trim(to_char(r_fact_flex.val_mont_inte_fle2_proy,l_formatoNumerico)) || '</montointpend5>';

    lv_monto_finan  :='<montofinan>' || trim(to_char(r_fact_flex.val_sald_maxi_camp,l_formatoNumerico)) || '</montofinan>';

    -- Inicio Cuota Pendiente 1 --

    BEGIN
     
     ln_cuota_pend_1:=0;
     lv_inte_pend_1:=0;
      
     SELECT
      NVL(ccc.imp_pend,0),
      fd.val_mont_carg_uso
     INTO
      ln_cuota_pend_1,
      ln_inte_pend_1
     FROM
      flx_cuota_flexi_factu_detal fd,
      ccc_movim_cuent_corri ccc
     WHERE fd.cod_peri = lv_cod_peri_rest_2
       AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
       AND fd.oid_clie = r_fact_flex.oid_clie
       AND fd.val_nume_orde_cuot = 3;

    EXCEPTION

     WHEN OTHERS THEN

      ln_cuota_pend_1:=0;
      lv_inte_pend_1:=0;

    END;

    lv_cuota_pend_1:='<cuotapend1>' || trim(to_char(ln_cuota_pend_1,l_formatoNumerico)) || '</cuotapend1>';
    lv_inte_pend_1:='<montointpend1>' || trim(to_char(nvl(ln_inte_pend_1,0),l_formatoNumerico)) || '</montointpend1>';

    ln_monto_pend_1:= nvl(ln_cuota_pend_1,0) + nvl(ln_inte_pend_1,0);
    lv_monto_pend_1:='<montopend1>' || trim(to_char(ln_monto_pend_1,l_formatoNumerico)) || '</montopend1>';

    -- Fin Cuota Pendiente 1 --

    -- Inicio Cuota Pendiente 2 --
    BEGIN
    
     ln_cuota_pend_2:=0;
     ln_inte_pend_2:=0;
     
     SELECT
      fd.val_mont_cuot_flex,
      fd.val_mont_carg_uso
     INTO
      ln_cuota_pend_2,
      ln_inte_pend_2
     FROM
      flx_cuota_flexi_factu_detal fd
         WHERE fd.cod_peri = lv_cod_peri_rest_1
       AND fd.oid_clie = r_fact_flex.oid_clie
       AND fd.val_nume_orde_cuot = 2;

    EXCEPTION

     WHEN OTHERS THEN
      ln_cuota_pend_2:=0;
      ln_inte_pend_2:=0;

    END;

    lv_cuota_pend_2:='<cuotapend2>' || trim(to_char(ln_cuota_pend_2,l_formatoNumerico)) || '</cuotapend2>';
    lv_inte_pend_2:='<montointpend2>' || trim(to_char(nvl(ln_inte_pend_2,0),l_formatoNumerico)) || '</montointpend2>';
    ln_monto_pend_2:= nvl(ln_cuota_pend_2,0) + nvl(ln_inte_pend_2,0);
    lv_monto_pend_2:='<montopend2>' || trim(to_char(ln_monto_pend_2,l_formatoNumerico)) || '</montopend2>';

    -- Fin Cuota Pendiente 2 --

    -- Inicio Cuota Pendiente 3 --
  BEGIN

     ln_cuota_pend_3:=0;
     ln_inte_pend_3:=0;
      
     SELECT
      fd.val_mont_cuot_flex,
      fd.val_mont_carg_uso
     INTO
      ln_cuota_pend_3,
      ln_inte_pend_3
     FROM
      flx_cuota_flexi_factu_detal fd
         WHERE fd.cod_peri = lv_cod_peri_rest_1
       AND fd.oid_clie = r_fact_flex.oid_clie
       AND fd.val_nume_orde_cuot = 3;

    EXCEPTION

     WHEN OTHERS THEN
      ln_cuota_pend_3:=0;
      ln_inte_pend_3:=0;

    END;

    lv_cuota_pend_3:='<cuotapend3>' || trim(to_char(nvl(ln_cuota_pend_3,0),l_formatoNumerico)) || '</cuotapend3>';
    lv_inte_pend_3:='<montointpend3>' || trim(to_char(nvl(ln_inte_pend_3,0),l_formatoNumerico)) || '</montointpend3>';
    ln_monto_pend_3:= nvl(ln_cuota_pend_3,0) + nvl(ln_inte_pend_3,0);
    lv_monto_pend_3:='<montopend3>' || trim(to_char(ln_monto_pend_3,l_formatoNumerico)) || '</montopend3>';

    -- Fin Cuota Pendiente 3 --


    IF nvl(r_fact_flex.val_mont_mini_paga,0) > 0 THEN
    ln_monto_ped_flex:=nvl(r_fact_flex.val_mont_mini_paga,0)-nvl(ln_monto_pend_1,0)-nvl(ln_monto_pend_2,0);
    ELSE
     ln_monto_ped_flex:= '';
    END IF;

    lv_monto_ped_flex:='<montopedflex>' || trim(to_char(ln_monto_ped_flex,l_formatoNumerico)) || '</montopedflex>';

    lv_sald_tota := NVL(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_fact_flex.oid_clie,lv_cod_peri_adic_1),0);

    ln_monto_ped_tot:= lv_sald_tota -nvl(ln_monto_pend_1,0)-nvl(ln_monto_pend_2,0);


    lv_monto_ped_tot:='<montopedtot>' || trim(to_char(ln_monto_ped_tot,l_formatoNumerico)) || '</montopedtot>';

    BEGIN

     SELECT
     CASE
      WHEN b.des_cali='Excelente' then '<tablaint1/>'
      WHEN b.des_cali='Bueno' then '<tablaint2/>'
      WHEN b.des_cali='Regular' then '<tablaint3/>'
      WHEN b.des_cali='Malo' then '<tablaint4/>'
     ELSE ''
      END,
     NVL(a.val_pedi_base,0),
     NVL(a.val_line_cred,0)
     INTO
      lv_comportamiento,
      lv_pedi_base,
     lv_linea_credi
     FROM
      flx_consu_habil_flexi a,
     flx_calif_compo b
     WHERE a.ind_cali_comp = b.cod_cali
      AND a.cod_clie = r_fact_flex.cod_clie
      AND a.cod_peri_fact = lv_cod_peri
      AND ROWNUM = 1;

    EXCEPTION
     WHEN no_data_found THEN
      lv_comportamiento := '<tablaint3/>';
      lv_pedi_base:= 0;
      lv_linea_credi := 0;
    END;

    BEGIN

    lv_q := FLX_FN_OBTIE_Q(r_fact_flex.oid_clie,lv_cod_peri_rest_1,lv_cod_peri_rest_2);

    IF lv_q IS NOT NULL AND lv_q < 1 THEN

     FLX_PR_OBTIE_VALOR_TASAS(lv_q,lv_imp_tasa_tcea,lv_imp_tasa_tcem);
     lv_tasa_tcem := '<tcem>' || lv_imp_tasa_tcem || '</tcem>';
     lv_tasa_tcea := '<tcea>' || lv_imp_tasa_tcea || '</tcea>';

    ELSE

     lv_tasa_tcem := '<tcem></tcem>';
     lv_tasa_tcea := '<tcea></tcea>';

    END IF;

    EXCEPTION

     WHEN OTHERS THEN

      lv_tasa_tcem := '<tcem></tcem>';
      lv_tasa_tcea := '<tcea></tcea>';

    END;

   lv_mens_tota := lv_inic_secc_flex ||
                   lv_mont_paga ||
                   lv_mont_mini_paga ||
                   lv_cuot_flex_1 ||
                   lv_cuot_flex_2 ||
                   lv_monto_pend_1 ||
                   lv_monto_pend_2 ||
                   lv_monto_pend_3 ||
                   lv_monto_ped_flex ||
                   lv_monto_finan ||
                   lv_cuota_pend_1 ||
                   lv_cuota_pend_2 ||
                   lv_cuota_pend_3 ||
                   lv_cuota_pend_4 ||
                   lv_cuota_pend_5 ||
                   lv_inte_pend_1 ||
                   lv_inte_pend_2 ||
                   lv_inte_pend_3 ||
                   lv_inte_pend_4 ||
                   lv_inte_pend_5 ||
                   lv_monto_ped_tot ||
                   lv_tasa_tcem ||
                   lv_tasa_tcea ||
                   '<pedbase>' || trim(to_char(lv_pedi_base,l_formatoNumerico)) || '</pedbase>' ||
                   '<flexipago>' || trim(to_char(lv_linea_credi,l_formatoNumerico)) || '</flexipago>' ||
                   lv_comportamiento ||
                   lv_fina_secc_flex;

   lv_reg_flx_paque_docum.xml_cons := lv_mens_tota;

   lv_reg_flx_paque_docum.cod_clie := r_fact_flex.cod_clie;

   INSERT INTO flx_paque_docum VALUES lv_reg_flx_paque_docum;

    END;
   END LOOP;
  CLOSE c_fact_flex;

 END FLX_PR_GENER_PADOC_FLEXI_ZONA2;

 PROCEDURE FLX_PR_OBTIE_VALOR_TASAS(
  p_q               IN  NUMBER,
  p_imp_tcea        OUT VARCHAR2,
  p_imp_tcem        OUT VARCHAR2)
 IS

  lv_a        NUMBER(12,8);
  lv_b        NUMBER(12,8);
  lv_imp_tcea NUMBER(12,8);
  lv_imp_tcem NUMBER(12,8);

 BEGIN

  lv_a :=  POWER((p_q/2 + POWER(((POWER(p_q,2)/4) - (POWER(p_q,3)/27)),0.5)),0.33333333);
  lv_b :=  POWER((p_q/2 - POWER(((POWER(p_q,2)/4) - (POWER(p_q,3)/27)),0.5)),0.33333333);

  lv_imp_tcea :=  ROUND(((POWER((lv_a +  lv_b),17.142857) -1)*100),2);
  lv_imp_tcem :=  ROUND(((POWER((lv_a +  lv_b),1.42857) -1)*100),2);

  p_imp_tcea := TO_CHAR(lv_imp_tcea,'09.99');
  p_imp_tcem := TO_CHAR(lv_imp_tcem,'09.99');

 END FLX_PR_OBTIE_VALOR_TASAS;

 FUNCTION FLX_FN_OBTIE_SALDO_FLEXI_ANTER(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_oid_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

  lv_imp_sald_pend                 NUMBER(12,2);
  lv_imp_sald_pend_cuot            NUMBER(12,2);
  lv_imp_sald_pend_inte            NUMBER(12,2);

 BEGIN

  SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
  INTO lv_imp_sald_pend_cuot
  FROM
   ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie = p_oid_clie
    AND mcc.subp_oid_subp_crea = gc_oid_subp_carg_flex
    AND mcc.perd_oid_peri <= p_oid_peri
    AND mcc.imp_pend > 0;

  SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
  INTO lv_imp_sald_pend_inte
  FROM
   ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie = p_oid_clie
   AND mcc.subp_oid_subp_crea = gc_oid_subp_inte_flex
    AND mcc.perd_oid_peri <= p_oid_peri
    AND mcc.imp_pend > 0;

  lv_imp_sald_pend := lv_imp_sald_pend_cuot + lv_imp_sald_pend_inte;

  RETURN lv_imp_sald_pend;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END FLX_FN_OBTIE_SALDO_FLEXI_ANTER;

 FUNCTION FLX_FN_OBTIE_MONTO_INTER_FLEXI(
  p_cod_peri                IN VARCHAR2,
  p_num_dias_camp           IN NUMBER,
  p_mont_cuot_flex          IN NUMBER)
 RETURN NUMBER
 IS

   lv_imp_cost_util_flex    NUMBER(12,2);
   lv_mont_inte_flex        NUMBER(12,2);
   lv_cod_pais              seg_pais.cod_pais%TYPE;
   lv_num_deci              seg_moned.num_deci%TYPE;

 BEGIN

  BEGIN
    
   lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
   
   SELECT sm.num_deci
     INTO lv_num_deci
   FROM seg_moned sm, seg_pais sp
   WHERE sm.oid_mone = sp.mone_oid_mone
     AND sp.cod_pais = lv_cod_pais;

   SELECT NVL(cu.val_cost_util_flex,0)
   INTO lv_imp_cost_util_flex
   FROM flx_costo_utili_flexi cu
   WHERE cu.val_impo_fina_desd <= p_mont_cuot_flex
     AND cu.val_impo_fina_hast >= p_mont_cuot_flex
     AND cu.cod_peri_inic <= p_cod_peri
     AND NVL(cu.cod_peri_fin,'9999') >= p_cod_peri;

  EXCEPTION

   WHEN NO_DATA_FOUND THEN
    lv_imp_cost_util_flex:=0;

  END;

  lv_mont_inte_flex := p_num_dias_camp*lv_imp_cost_util_flex;

  RETURN round(lv_mont_inte_flex,lv_num_deci);

 END FLX_FN_OBTIE_MONTO_INTER_FLEXI;

 FUNCTION FLX_FN_OBTIE_Q(
  p_oid_clie        IN mae_clien.oid_clie%TYPE,
  p_cod_peri_res1   IN seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_res2   IN seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

   lv_q NUMBER(12,8);
   lv_sald_cuot_flex   NUMBER(12,2);
   lv_factorIVA   NUMBER(12,2);
   lv_tasa   NUMBER(12);

l_indicadorIVAFlexi     VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorIVAFlexi'),0);

 BEGIN

if l_indicadorIVAFlexi=1 then

   begin
  select pti.val_tasa_impu into lv_tasa
  from flx_cuota_flexi_factu_detal ff, fac_tipos_impue_ubige ft, ped_tasa_impue pti
  where ff.oid_clie=p_oid_clie and ff.cod_peri=p_cod_peri_res1
  and ff.oid_zona=ft.vepo_oid_valo_estr_geop
  and ft.taim_oid_tasa_impu=pti.oid_tasa_impu
  and rownum=1;
  exception when others then
      select max(pti.val_tasa_impu) into lv_tasa
      from ped_impue_gener pig, ped_tasa_impue pti
      where pig.sbac_oid_sbac=888
      and pig.taim_oid_tasa_impu=pti.oid_tasa_impu;
  end;

  lv_factorIVA:=1+(lv_tasa/100);

else
  lv_factorIVA:=1;
end if;


  SELECT NVL(SUM(fd.val_sald_cuot_flex),0)
  INTO lv_sald_cuot_flex
  FROM flx_cuota_flexi_factu_detal fd
  WHERE fd.oid_clie = p_oid_clie
  AND fd.cod_peri IN (p_cod_peri_res1,p_cod_peri_res2);

  IF lv_sald_cuot_flex > 0 THEN

   SELECT SUM(fd.val_sald_cuot_flex + (fd.val_mont_carg_uso/lv_factorIVA))/(SUM(fd.val_sald_cuot_flex)*2)
   INTO lv_q
   FROM flx_cuota_flexi_factu_detal fd
   WHERE fd.oid_clie = p_oid_clie
   AND fd.cod_peri IN (p_cod_peri_res1,p_cod_peri_res2);

  ELSE
   lv_q := NULL;
  END IF;

  RETURN lv_q;

 END FLX_FN_OBTIE_Q;

 FUNCTION FLX_FN_OBTIE_SALDO_FLEXI_CAMP(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_cod_peri_gene                  IN   cra_perio.oid_peri%TYPE,
  p_oid_peri_actu                  IN   cra_perio.oid_peri%TYPE,
  p_oid_peri_cobr                  IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER
 IS
  lv_imp_sald_pend                 NUMBER(12,2);
  lv_imp_cuot_pend                 NUMBER(12,2);
  lv_imp_inte_pend                 NUMBER(12,2);

 BEGIN

  SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
  INTO lv_imp_cuot_pend
  FROM
   flx_cuota_flexi_factu_detal fd,
   ccc_movim_cuent_corri mcc
  WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
    AND fd.oid_clie =  p_oid_clie
    AND fd.cod_peri = p_cod_peri_gene
    AND fd.oid_peri_cuot_flex = p_oid_peri_cobr
    AND mcc.imp_pend > 0;

  IF p_oid_peri_cobr <= p_oid_peri_actu THEN

  SELECT NVL(SUM(NVL(fd.val_mont_carg_uso,0)),0)
   INTO lv_imp_inte_pend
   FROM
    flx_cuota_flexi_factu_detal fd
   WHERE fd.oid_clie =  p_oid_clie
     AND fd.cod_peri = p_cod_peri_gene
     AND fd.oid_peri_cuot_flex = p_oid_peri_cobr;

   /*
   SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
   INTO lv_imp_inte_pend
   FROM
    flx_cuota_flexi_factu_detal fd,
    ccc_movim_cuent_corri mcc
   WHERE fd.oid_movi_carg_uso = mcc.oid_movi_cc
     AND fd.oid_clie =  p_oid_clie
     AND fd.cod_peri = p_cod_peri_gene
     AND fd.oid_peri_cuot_flex = p_oid_peri_cobr
     AND mcc.imp_pend > 0;
   */

  ELSE

   SELECT NVL(SUM(NVL(fd.val_mont_carg_uso,0)),0)
   INTO lv_imp_inte_pend
   FROM
    flx_cuota_flexi_factu_detal fd
   WHERE fd.oid_clie =  p_oid_clie
     AND fd.cod_peri = p_cod_peri_gene
     AND fd.oid_peri_cuot_flex = p_oid_peri_cobr;

  END IF;

  lv_imp_sald_pend := lv_imp_cuot_pend + lv_imp_inte_pend;

  RETURN lv_imp_sald_pend;

 END FLX_FN_OBTIE_SALDO_FLEXI_CAMP;

 FUNCTION FLX_FN_OBTIE_SALDO_MAXIM(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_peri                       IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER
 IS

  lv_sald_maxi_camp                NUMBER(12,2);

 BEGIN

  SELECT SUM(NVL(ff.val_sald_maxi_camp,0))
  INTO lv_sald_maxi_camp
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.oid_peri_sald_maxi_camp = p_oid_peri
  AND ff.oid_clie = p_oid_clie
  AND ff.cod_moti_rech is null;

  RETURN lv_sald_maxi_camp;

 END FLX_FN_OBTIE_SALDO_MAXIM;

 FUNCTION FLX_FN_FACTO_CONVE_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS

  lv_resu                          NUMBER(12,2);

 BEGIN

  SELECT NVL(MAX(fc.val_fact_conv),0)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_cabec fc
  WHERE fc.cod_peri = p_cod_peri
   AND fc.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_FACTO_CONVE_CAMPA;

 FUNCTION FLX_FN_FLAG_USO_FLEXI_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR
 IS

  lv_cant                          NUMBER(12):=0;

 BEGIN

  SELECT COUNT(*)
  INTO lv_cant
  FROM flx_cuota_flexi_factu_cabec fc
  WHERE fc.cod_peri = p_cod_peri
   AND fc.cod_clie = p_cod_clie;

  IF NVL(lv_cant,0) > 0 THEN
   RETURN '1';
  ELSE
   RETURN '0';
  END IF;

 END FLX_FN_FLAG_USO_FLEXI_CAMPA;

 FUNCTION FLX_FN_FLAG_PREPA_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR
 IS
  lv_oid_peri_proc                 cra_perio.oid_peri%TYPE;
  lv_resu                          NUMBER(12,2);

 BEGIN

  lv_oid_peri_proc := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri);

  SELECT NVL(SUM(mcc.imp_paga),0)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_detal fd,
       ccc_movim_cuent_corri mcc
  WHERE fd.oid_peri_cuot_flex = mcc.oid_movi_cc
   AND fd.cod_peri = p_cod_peri
   AND fd.oid_peri_cuot_flex = lv_oid_peri_proc
   AND fd.cod_clie = p_cod_clie;

  IF lv_resu > 0 THEN
   RETURN '1';
  ELSE
   RETURN '0';
  END IF;

 END FLX_FN_FLAG_PREPA_CAMPA;

 FUNCTION FLX_FN_MONTO_FINAN_CATAL_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS

  lv_resu                          NUMBER(12,2);

 BEGIN

  SELECT NVL(SUM(ROUND(fc.val_mont_fina/fc.val_fact_conv,2)),0)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_cabec fc
  WHERE fc.cod_peri = p_cod_peri
   AND fc.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_MONTO_FINAN_CATAL_CAMPA;

 FUNCTION FLX_FN_MONTO_FINAN_FACTU_CAMPA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN VARCHAR
 IS

  lv_resu                          NUMBER(12,2);

 BEGIN

  SELECT SUM(fc.val_mont_fina)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_cabec fc
  WHERE fc.cod_peri = p_cod_peri
   AND fc.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_MONTO_FINAN_FACTU_CAMPA;

 FUNCTION FLX_FN_MONTO_CUOTA_21DIA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS

  lv_resu                          NUMBER(12);

 BEGIN

  SELECT SUM(fc.val_mont_pedi_base)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_cabec fc
  WHERE fc.cod_peri = p_cod_peri
   AND fc.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_MONTO_CUOTA_21DIA;

 FUNCTION FLX_FN_MONTO_CUOTA_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS
  lv_oid_peri_proc                 cra_perio.oid_peri%TYPE;
  lv_resu                          NUMBER(12,2);

 BEGIN

  lv_oid_peri_proc := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( p_cod_peri_proc);

  SELECT SUM(fd.val_mont_cuot_flex)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_detal fd
  WHERE fd.cod_peri = p_cod_peri_orig
   AND fd.oid_peri_cuot_flex = lv_oid_peri_proc
   AND fd.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_MONTO_CUOTA_FLEXI;

 FUNCTION FLX_FN_MONTO_INTER_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS
   lv_oid_peri_proc                 cra_perio.oid_peri%TYPE;
   lv_resu                          NUMBER(12,2);

 BEGIN

  lv_oid_peri_proc := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( p_cod_peri_proc);

  SELECT SUM(fd.val_mont_carg_uso)
  INTO lv_resu
  FROM flx_cuota_flexi_factu_detal fd
  WHERE fd.cod_peri = p_cod_peri_orig
   AND fd.oid_peri_cuot_flex = lv_oid_peri_proc
   AND fd.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_MONTO_INTER_FLEXI;

 FUNCTION FLX_FN_SALDO_CUOTA_FLEXI(
  p_cod_peri_orig                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_proc                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_clie                       IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS
   lv_oid_peri_proc                 cra_perio.oid_peri%TYPE;
   lv_resu                          NUMBER(12,2);

 BEGIN

  lv_oid_peri_proc := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( p_cod_peri_proc);

  SELECT SUM(mcc.imp_pend)
  INTO lv_resu
  FROM
    flx_cuota_flexi_factu_detal fd,
    ccc_movim_cuent_corri mcc
  WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
   AND fd.cod_peri = p_cod_peri_orig
   AND fd.oid_peri_cuot_flex = lv_oid_peri_proc
   AND fd.cod_clie = p_cod_clie;

  RETURN lv_resu;

 END FLX_FN_SALDO_CUOTA_FLEXI;

 FUNCTION FLX_FN_OBTIE_CUOTA_PAGAD_FLX(
  psOidClie                       IN   mae_clien.oid_clie%TYPE,
  psOidPeri                       IN   cra_perio.oid_peri%TYPE)
 RETURN NUMBER
 IS

  lv_imp_paga_flx                NUMBER(12,2);

 BEGIN

  select SUM(NVL(mcc.imp_pend,0))
   into lv_imp_paga_flx
    from ccc_movim_cuent_corri mcc, flx_cuota_flexi_factu_detal de
   where mcc.oid_movi_cc = de.oid_movi_carg_flex
     and de.oid_peri_cuot_flex = psOidPeri
     and de.oid_clie =psOidClie;

  RETURN lv_imp_paga_flx;

 END FLX_FN_OBTIE_CUOTA_PAGAD_FLX;

  PROCEDURE FLX_PR_GENER_REPOR_SALDO(psCodigoPeriodo   IN seg_perio_corpo.cod_peri%type,
                                    psCodigoPais      IN seg_pais.cod_pais%type,
                                    psOid             OUT VARCHAR2)
 IS

  lv_oid_peri                      seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_adic_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_adic_1               seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_rest_1               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_1               seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_rest_2               seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_rest_2               seg_perio_corpo.oid_peri%TYPE;
  lv_oid_tipo_soli_pais            NUMBER(12);
  ln_oid                           NUMBER(12);

  lv_tota_cuot_flex                NUMBER(12,2);
  lv_imp_tota_camp                 NUMBER(12,2);
  lv_imp_tota_paga                 NUMBER(12,2);
  lv_imp_cuot_flex                 NUMBER(12,2);
  lv_imp_inte_flex                 NUMBER(12,2);
  lv_max_cuot_flex                 NUMBER(12,2);
  ln_imp_sald_inte                 NUMBER(12,2);
  ln_imp_cuot_pend                 NUMBER(12,2);
  ln_imp_inte_pend                 NUMBER(12,2);

  lv_reg_flx_repor           gtt_flx_repor_saldo%ROWTYPE;

  CURSOR c_rep_flex IS
  select zr.cod_regi, zz.cod_zona, zs.cod_secc,x.cod_clie, x.oid_clie,
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nombre
    from (select f.cod_clie, f.oid_clie
            FROM flx_gener_finan_consu_flexi f
           WHERE f.cod_peri = psCodigoPeriodo
             AND f.cod_moti_rech IS NULL
          UNION
          SELECT fc.cod_clie, fc.oid_clie
            FROM flx_cuota_flexi_factu_detal fc
           WHERE ((fc.oid_peri_cuot_flex = lv_oid_peri) OR
                 (fc.oid_peri_cuot_flex = lv_oid_peri_adic_1))
             AND NOT EXISTS (SELECT NULL
                    FROM flx_gener_finan_consu_flexi f
                   WHERE f.cod_peri = psCodigoPeriodo
                     AND f.cod_moti_rech IS NULL
                     AND f.oid_clie = fc.oid_clie)
             AND EXISTS (
       SELECT NULL
       FROM ped_solic_cabec psc
       WHERE 1=1
         AND psc.perd_oid_peri = lv_oid_peri
         AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
         AND psc.clie_oid_clie = fc.oid_clie)
         ) x,
         mae_clien mc,
         mae_clien_unida_admin ua,
         zon_terri_admin ta,
         zon_terri zt,
         zon_secci zs,
         zon_zona zz,
         zon_regio zr
   where ua.ind_acti = 1
     and ua.perd_oid_peri_fin is null
     and ua.ztad_oid_terr_admi = ta.oid_terr_admi
     and ta.terr_oid_terr = zt.oid_terr
     and ta.zscc_oid_secc = zs.oid_secc
     and zs.zzon_oid_zona = zz.oid_zona
     and zr.oid_regi = zz.zorg_oid_regi
     and mc.oid_clie = ua.clie_oid_clie
     and mc.oid_clie = x.oid_clie
   order by 1,2,3;

   r_rep_flex c_rep_flex%ROWTYPE;

 BEGIN

  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');
  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPais,psCodigoPeriodo);

  lv_cod_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(psCodigoPeriodo,-1);
  lv_oid_peri_rest_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPais,lv_cod_peri_rest_1);

  lv_cod_peri_rest_2 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(psCodigoPeriodo,-2);
  lv_oid_peri_rest_2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPais,lv_cod_peri_rest_2);

  lv_cod_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(psCodigoPeriodo,1);
  lv_oid_peri_adic_1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPais,lv_cod_peri_adic_1);

  delete from GTT_FLX_REPOR_SALDO;

  ln_oid := FLX_REPO_SALD_SEQ.Nextval;

  psOid := ln_oid;


  OPEN c_rep_flex;
  LOOP
   FETCH c_rep_flex INTO r_rep_flex;
   EXIT WHEN c_rep_flex%NOTFOUND;

   BEGIN

   lv_reg_flx_repor.oid_repo := ln_oid;
   lv_reg_flx_repor.cod_regi := r_rep_flex.cod_regi;
   lv_reg_flx_repor.cod_zona := r_rep_flex.cod_zona;
   lv_reg_flx_repor.cod_secc := r_rep_flex.cod_secc;
   lv_reg_flx_repor.cod_clie := r_rep_flex.cod_clie;
   lv_reg_flx_repor.val_nomb := r_rep_flex.nombre;

   ln_imp_sald_inte := 0;
   ln_imp_cuot_pend :=0;
   ln_imp_inte_pend := 0;

       -- Campana X-2--
   lv_reg_flx_repor.cod_peri := lv_cod_peri_rest_2;

    SELECT NVL(SUM(mcc.imp_movi),0)--, NVL(SUM(mcc.imp_paga),0)
    INTO lv_imp_tota_camp--, lv_imp_tota_paga
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = r_rep_flex.oid_clie
      AND mcc.perd_oid_peri  = lv_oid_peri_rest_2
      AND mcc.subp_oid_subp_crea NOT IN (gc_oid_subp_carg_flex,gc_oid_subp_inte_flex)
      AND mcc.imp_movi > 0
      AND EXISTS (
       SELECT NULL
       FROM flx_cuota_flexi_factu_detal fd,
            ccc_movim_cuent_corri ccc
       WHERE fd.cod_peri = lv_cod_peri_rest_2
         AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
         AND fd.oid_clie = mcc.clie_oid_clie
         AND ccc.imp_pend > 0);

    lv_reg_flx_repor.mon_tota := lv_imp_tota_camp;
    --lv_reg_flx_repor.mon_paga := lv_imp_tota_paga;

      SELECT NVL(SUM(mcc.imp_pend),0)
      INTO lv_imp_cuot_flex
     FROM
        flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND mcc.subp_oid_subp_crea = gc_oid_subp_carg_flex
        AND fd.oid_clie = r_rep_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri, lv_oid_peri_adic_1)
        AND fd.cod_peri = lv_cod_peri_rest_2;

   SELECT  NVL(SUM(mcc.imp_pend),0)
    INTO  lv_tota_cuot_flex
     FROM
       flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
      AND fd.oid_clie = r_rep_flex.oid_clie
      AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_2;

      SELECT NVL(SUM(fd.val_mont_carg_uso),0)
      INTO lv_imp_inte_flex
       FROM
          flx_cuota_flexi_factu_detal fd
      WHERE fd.oid_clie = r_rep_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri, lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_2;

    lv_imp_tota_paga := lv_imp_tota_camp - lv_tota_cuot_flex;
    lv_reg_flx_repor.mon_paga := lv_imp_tota_paga;

    lv_reg_flx_repor.mon_cuot := lv_imp_cuot_flex;
    lv_reg_flx_repor.mon_inte := lv_imp_inte_flex;

    -- lv_reg_flx_repor.cuo_pen1 := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_rep_flex.oid_clie,lv_cod_peri_rest_2,lv_oid_peri,lv_oid_peri);

     SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
       INTO ln_imp_cuot_pend
      FROM
       flx_cuota_flexi_factu_detal fd,
       ccc_movim_cuent_corri mcc
      WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND fd.oid_clie =  r_rep_flex.oid_clie
        AND fd.cod_peri = lv_cod_peri_rest_2
        AND fd.oid_peri_cuot_flex = lv_oid_peri
        AND mcc.imp_pend > 0;

      SELECT NVL(SUM(NVL(fd.val_mont_carg_uso,0)),0)
       INTO ln_imp_inte_pend
       FROM
        flx_cuota_flexi_factu_detal fd
       WHERE fd.oid_clie =  r_rep_flex.oid_clie
         AND fd.cod_peri = lv_cod_peri_rest_2
         AND fd.oid_peri_cuot_flex = lv_oid_peri;

     lv_reg_flx_repor.cuo_pen2 := 0;

     BEGIN
        SELECT ln_imp_inte_pend - NVL(mcc.imp_paga,0) INTO ln_imp_sald_inte
         FROM ccc_movim_cuent_corri mcc
        WHERE  mcc.subp_oid_subp_crea = gc_oid_subp_inte_flex
        AND mcc.perd_oid_peri = lv_oid_peri
        AND mcc.clie_oid_clie =r_rep_flex.oid_clie;
     EXCEPTION
       WHEN no_data_found THEN
         ln_imp_sald_inte := 0;
     END;

        IF ( ln_imp_sald_inte < 0) THEN
           lv_reg_flx_repor.cuo_pen1:= ln_imp_cuot_pend;
        ELSE
           lv_reg_flx_repor.cuo_pen1 :=  ln_imp_cuot_pend + ln_imp_sald_inte;
        END IF;

        IF (lv_reg_flx_repor.cuo_pen1 > 0) THEN

             INSERT INTO gtt_flx_repor_saldo VALUES lv_reg_flx_repor;
         END IF;

    -- Campana X-1 --
    lv_reg_flx_repor.cod_peri := lv_cod_peri_rest_1;


    --Se obtienen los datos solo si ha tenido financiamiento
   -- sobre esa campa?a y este financiamiento esta pendiente

    SELECT NVL(SUM(mcc.imp_movi),0)
    INTO lv_imp_tota_camp
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = r_rep_flex.oid_clie
      AND mcc.perd_oid_peri = lv_oid_peri_rest_1
      AND mcc.subp_oid_subp_crea NOT IN (gc_oid_subp_carg_flex,gc_oid_subp_inte_flex)
      AND mcc.imp_movi > 0
      AND EXISTS (
        SELECT NULL
        FROM
         flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri ccc
       WHERE fd.cod_peri = lv_cod_peri_rest_1
         AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
         AND fd.oid_clie = mcc.clie_oid_clie
         AND ccc.imp_pend > 0);

    lv_reg_flx_repor.mon_tota := lv_imp_tota_camp;

    SELECT NVL(MAX(mcc.imp_pend),0), NVL(SUM(mcc.imp_pend),0)
    INTO lv_max_cuot_flex, lv_tota_cuot_flex
     FROM
       flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
      AND fd.oid_clie = r_rep_flex.oid_clie
      AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_1;

   BEGIN
      SELECT  NVL(MAX(mcc.imp_pend),0)
      INTO lv_imp_cuot_flex
      FROM
          flx_cuota_flexi_factu_detal fd,
         ccc_movim_cuent_corri mcc
    WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND mcc.subp_oid_subp_crea = gc_oid_subp_carg_flex
     AND fd.oid_clie = r_rep_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
      AND fd.cod_peri = lv_cod_peri_rest_1
      AND fd.val_mont_cuot_flex = lv_max_cuot_flex;
     -- AND rownum < 2;
   EXCEPTION
   WHEN no_data_found THEN
        lv_imp_cuot_flex := NULL;
   END;


      BEGIN

       SELECT NVL(MAX(fd.val_mont_carg_uso),0)
       INTO lv_imp_inte_flex
        FROM
            flx_cuota_flexi_factu_detal fd
      WHERE fd.oid_clie = r_rep_flex.oid_clie
        AND fd.oid_peri_cuot_flex IN (lv_oid_peri,lv_oid_peri_adic_1)
        AND fd.cod_peri = lv_cod_peri_rest_1
        AND rownum < 2;

    EXCEPTION

       WHEN no_data_found THEN
         lv_imp_inte_flex := NULL;

    END;

    lv_imp_tota_paga := lv_imp_tota_camp - lv_tota_cuot_flex;

     lv_reg_flx_repor.mon_paga := lv_imp_tota_paga;

     lv_reg_flx_repor.mon_cuot := lv_imp_cuot_flex;
     lv_reg_flx_repor.mon_inte := lv_imp_inte_flex;

     --lv_reg_flx_repor.cuo_pen1 := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_rep_flex.oid_clie,lv_cod_peri_rest_1,lv_oid_peri,lv_oid_peri);

     lv_reg_flx_repor.cuo_pen2 := FLX_FN_OBTIE_SALDO_FLEXI_CAMP(r_rep_flex.oid_clie,lv_cod_peri_rest_1,lv_oid_peri,lv_oid_peri_adic_1);

       SELECT NVL(SUM(NVL(mcc.imp_pend,0)),0)
      INTO ln_imp_cuot_pend
      FROM
       flx_cuota_flexi_factu_detal fd,
       ccc_movim_cuent_corri mcc
      WHERE fd.oid_movi_carg_flex = mcc.oid_movi_cc
        AND fd.oid_clie =  r_rep_flex.oid_clie
        AND fd.cod_peri = lv_cod_peri_rest_1
        AND fd.oid_peri_cuot_flex = lv_oid_peri
        AND mcc.imp_pend > 0;

      SELECT NVL(SUM(NVL(fd.val_mont_carg_uso,0)),0)
       INTO ln_imp_inte_pend
       FROM
        flx_cuota_flexi_factu_detal fd
       WHERE fd.oid_clie =   r_rep_flex.oid_clie
         AND fd.cod_peri = lv_cod_peri_rest_1
         AND fd.oid_peri_cuot_flex = lv_oid_peri;

       IF (ln_imp_sald_inte < 0) THEN
             lv_reg_flx_repor.cuo_pen1 := ln_imp_cuot_pend + ln_imp_inte_pend + ln_imp_sald_inte;
       ELSE
           lv_reg_flx_repor.cuo_pen1 := ln_imp_cuot_pend + ln_imp_inte_pend;
       END IF;

       IF ((lv_reg_flx_repor.cuo_pen1 > 0) or (lv_reg_flx_repor.cuo_pen2>0)) THEN
            INSERT INTO gtt_flx_repor_saldo VALUES lv_reg_flx_repor;
       END IF;

    END;
   END LOOP;
  CLOSE c_rep_flex;

 END FLX_PR_GENER_REPOR_SALDO;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Gestionar consultora
Fecha Creacion    : 15/10/2013
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE FLX_PR_REPOR_GESTI_CONSU_CSV(
    psCodigoPais                        VARCHAR2,
    psCampanyaComunicacion              VARCHAR2,
    psCampanyaFacturacion               VARCHAR2,
    psCodigoCliente                     VARCHAR2,
    psCodigoCalifComp                   VARCHAR2,
    psCodigoCalifExpe                   VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS

  SELECT  FC.COD_PAIS,
          CL.COD_CLIE,
          FC.COD_PERI_FACT CAM_FACT,
          TRIM(CL.VAL_APE1) || ' ' || TRIM(CL.VAL_APE2) || ' ' || TRIM(CL.VAL_NOM1) || ' ' || TRIM(CL.VAL_NOM2) AS NOM_CLIE,
          FC.VAL_LINE_CRED LIN_CRED,
          FC.VAL_PEDI_BASE PED_BASE,
          FC.COD_PERI_COMU CAM_COMU,
          CE.DES_CALI CAL_EXPE,
          CC.DES_CALI CAL_COMP,
          FC.IND_HABI,
          FC.IND_ACTI,
          FC.IND_COMU,
          FC.IND_ENVI,
          FC.IND_CANC,
        case
           when fc.ind_acti = '0' and fc.ind_canc = '0' then 'Inactivo'
           when fc.ind_acti = '0' and fc.ind_canc = '1' then 'Cancelado'
           when fc.ind_acti = '1' and fc.ind_canc = '0' then 'Activo'
        end ESTATUS,
        (select to_char(max(a.fec_acci),'dd/mm/yyyy')
            from flx_audit_consu_habil a
           where a.cod_clie = fc.cod_clie
             and a.cod_acci = '02')  FECHA_ACTIVACION,
        case
          when fc.num_docu is null then (select a.usu_acci
                                          from flx_audit_consu_habil a
                                         where a.cod_clie = fc.cod_clie
                                           and a.cod_acci = '02'
                                           and rownum = 1)
          when fc.num_docu is not null then 'OCR'
        end ORIGEN,
        case
          when fc.ind_acti = '0' and fc.ind_canc = '1' then (select to_char(max(a.fec_acci),'dd/mm/yyyy')
                                                             from flx_audit_consu_habil a
                                                            where a.cod_clie = fc.cod_clie
                                                              and a.cod_acci = '01')
        end FECHA_CANCELACION,
        case
          when fc.ind_acti = '0' and fc.ind_canc = '1' then (select a.usu_acci
                                                             from flx_audit_consu_habil a
                                                            where a.cod_clie = fc.cod_clie
                                                              and a.cod_acci = '01'
                                                              and rownum = 1)
        end USUARIO_CANCELACION,
        case
          when fc.ind_acti = '0' and fc.ind_canc = '1' then (select m.des_moti
                                                             from flx_audit_consu_habil a,
                                                                  flx_motiv_cance m
                                                            where a.cod_clie = fc.cod_clie
                                                              and a.cod_acci = '01'
                                                              and a.cod_moti = m.cod_moti
                                                              and rownum = 1)
        end MOTIVO_CANCELACION
          FROM FLX_CONSU_HABIL_FLEXI FC, MAE_CLIEN CL, FLX_CALIF_EXPER_FLEXI CE, FLX_CALIF_COMPO CC
          WHERE FC.COD_CLIE = CL.COD_CLIE
          AND FC.IND_CALI_EXPE_FLEX = CE.COD_CALI
          AND FC.IND_CALI_COMP = CC.COD_CALI
          AND FC.COD_PAIS = psCodigoPais
          AND (psCampanyaComunicacion IS NULL OR FC.COD_PERI_COMU = psCampanyaComunicacion)
          AND (psCampanyaFacturacion IS NULL OR FC.COD_PERI_FACT = psCampanyaFacturacion)
          AND (psCodigoCliente IS NULL OR FC.COD_CLIE = psCodigoCliente)
          AND (psCodigoCalifComp IS NULL OR FC.IND_CALI_COMP = psCodigoCalifComp)
          AND (psCodigoCalifExpe IS NULL OR FC.IND_CALI_EXPE_FLEX = psCodigoCalifExpe);

TYPE interfazTipo IS RECORD (

 v_COD_PAIS          VARCHAR2(3),
 v_COD_CLIE          VARCHAR2(15),
 v_CAM_FACT          VARCHAR2(6),
 v_NOM_CLIE          VARCHAR2(200),
 v_LIN_CRED          NUMBER(12,2),
 v_PED_BASE          NUMBER(12,2),
 v_CAM_COMU          VARCHAR2(6),
 v_CAL_EXPE          VARCHAR2(100),
 v_CAL_COMP          VARCHAR2(100),
 v_IND_HABI          VARCHAR2(1),
 v_IND_ACTI          VARCHAR2(1),
 v_IND_COMU          VARCHAR2(1),
 v_IND_ENVI          VARCHAR2(1),
 v_IND_CANC                 VARCHAR2(1),
 v_ESTATUS                  VARCHAR2(20),
 v_FECHA_ACTIVACION         VARCHAR2(20),
 v_ORIGEN                   VARCHAR2(100),
 v_FECHA_CANCELACION        VARCHAR2(20),
 v_USUARIO_CANCELACION      VARCHAR2(100),
 v_MOTIVO_CANCELACION       VARCHAR2(200)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_COD_PAIS ||','||
                    '=T("'||interfazRecord(x).v_COD_CLIE||'")' ||','||
                    interfazRecord(x).v_CAM_FACT ||','||
                    interfazRecord(x).v_NOM_CLIE ||','||
                    '=T("'||interfazRecord(x).v_LIN_CRED||'")' ||','||
                    '=T("'||interfazRecord(x). v_PED_BASE||'")' ||','||
                    interfazRecord(x).v_CAM_COMU ||','||
                    interfazRecord(x).v_CAL_EXPE ||','||--
                    interfazRecord(x).v_CAL_COMP ||','||--
                    interfazRecord(x).v_IND_HABI ||','||
                    interfazRecord(x).v_IND_ACTI ||','||
                    interfazRecord(x).v_IND_COMU ||','||
                    interfazRecord(x).v_IND_ENVI ||','||
                    interfazRecord(x).v_IND_CANC ||','||
                    interfazRecord(x).v_ESTATUS ||','||
                    interfazRecord(x).v_FECHA_ACTIVACION  ||','||
                    interfazRecord(x).v_ORIGEN  ||','||
                    interfazRecord(x).v_FECHA_CANCELACION  ||','||
                    interfazRecord(x).v_USUARIO_CANCELACION ||','||
                    interfazRecord(x).v_MOTIVO_CANCELACION;
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_REPOR_GESTI_CONSU_CSV: '||ls_sqlerrm);
END FLX_PR_REPOR_GESTI_CONSU_CSV;

 PROCEDURE FLX_PR_CARGA_DATOS_WEBSE
 IS

  lv_cod_peri_actu               VARCHAR2(6);
  lv_cod_peri_sigu               VARCHAR2(6);

 BEGIN

  lv_cod_peri_actu := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;
  lv_cod_peri_sigu := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri_actu,1);

  DELETE FROM flx_carga_datos_webse;

  INSERT INTO flx_carga_datos_webse
   SELECT
    mc.oid_clie,
    h.cod_clie,
    h.cod_peri_fact,
    CASE
      WHEN CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(mc.oid_clie, h.cod_peri_fact) > 0 THEN
       CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(mc.oid_clie, h.cod_peri_fact)
      ELSE
       -2
    END imp_sald_camp,
    FLX_PKG_PROCE.FLX_FN_OBTIE_MONMI_CONSU_CAMPA(mc.oid_clie, h.cod_peri_fact) val_mont_mini,
    SYSDATE
   FROM
    flx_consu_habil_flexi h,
    mae_clien mc
   WHERE h.cod_clie = mc.cod_clie
     AND h.ind_acti = 1
     AND h.cod_peri_fact IN (
    lv_cod_peri_actu,
      lv_cod_peri_sigu);

 END FLX_PR_CARGA_DATOS_WEBSE;

 FUNCTION FLX_FN_OBTIE_MONMI_CONSU_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_imp_sald_tota                 NUMBER(12,2);
  lv_imp_sald                      NUMBER(12,2);
  lv_imp_sald_maxi                 NUMBER(12,2);
  lv_cant                          NUMBER(12);

 BEGIN


  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  lv_imp_sald_tota := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,p_cod_peri);

  lv_imp_sald_maxi := NVL(FLX_PKG_PROCE.FLX_FN_OBTIE_SALDO_MAXIM(p_oid_clie,FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,p_cod_peri)),0);

  lv_imp_sald := lv_imp_sald_tota - lv_imp_sald_maxi;

  IF lv_imp_sald <= 0 THEN
   lv_imp_sald := -2;
  END IF;

  SELECT COUNT(*)
  INTO lv_cant
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.oid_peri_sald_maxi_camp = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,p_cod_peri)
    AND ff.oid_clie = p_oid_clie
    AND ff.cod_moti_rech is NULL;

  IF lv_cant = 0 THEN
   lv_imp_sald := -1;
  END IF;

  RETURN lv_imp_sald;

 END FLX_FN_OBTIE_MONMI_CONSU_CAMPA;

 FUNCTION FLX_FN_OBTIE_MONMI_ZONAS_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE)
 RETURN NUMBER
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_fec_fact                      cra_crono.fec_inic%TYPE;
  lv_oid_acti_fact                 cra_activ.oid_acti%TYPE;
  lv_imp_sald                      NUMBER(12,2);

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  SELECT ca.oid_acti
  INTO lv_oid_acti_fact
  FROM
   cra_activ ca,
   seg_pais sp
  WHERE ca.pais_oid_pais = sp.oid_pais
    AND ca.cod_acti = 'FA'
    AND sp.cod_pais = lv_cod_pais;

  SELECT bcf.cod_peri, cp.oid_peri
  INTO lv_cod_peri, lv_oid_peri
  FROM
   bas_ctrl_fact bcf,
   cra_perio cp,
   seg_perio_corpo spc
  WHERE bcf.cod_peri = spc.cod_peri
    AND spc.oid_peri = cp.peri_oid_peri
    AND bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  SELECT MAX(cr.fec_inic)
  INTO lv_fec_fact
  FROM
   cra_crono cr
  WHERE cr.cact_oid_acti = lv_oid_acti_fact
    AND cr.zzon_oid_zona = p_oid_zona
    AND cr.perd_oid_peri = lv_oid_peri;

  IF lv_fec_fact < TRUNC(SYSDATE) THEN
   lv_cod_peri := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1);
  END IF;

  lv_imp_sald:= CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,lv_cod_peri) - FLX_PKG_PROCE.FLX_FN_OBTIE_SALDO_MAXIM(p_oid_clie,FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_pais,lv_cod_peri));

  RETURN lv_imp_sald;

 END FLX_FN_OBTIE_MONMI_ZONAS_CAMPA;


 FUNCTION FLX_PR_OBTIE_MONMI_WEBSE(
  p_cod_clie                       IN    VARCHAR2,
  p_cod_peri                       IN   VARCHAR2)
 RETURN NUMBER
 IS

  lv_oid_clie                      mae_clien.oid_clie%TYPE;

  lv_mont_mini                     NUMBER(12,2);

 BEGIN

  SELECT mc.oid_clie
  INTO lv_oid_clie
  FROM mae_clien mc
  WHERE mc.cod_clie = p_cod_clie;

  SELECT ff.val_mont_mini_paga
  INTO lv_mont_mini
  FROM flx_gener_finan_consu_flexi ff
  WHERE ff.cod_peri = p_cod_peri;

  RETURN lv_mont_mini;

 END FLX_PR_OBTIE_MONMI_WEBSE;

 /**************************************************************************
    Descripcion       : flx_pr_desac_autom

    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte                                                 SEG_CANAL can
                               WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER

  ***************************************************************************/
 PROCEDURE flx_pr_desac_autom
 IS

 CURSOR c_tipodocumento
 IS
 SELECT
  h.cod_clie,
  bc.cod_peri,
  bc.cod_pais
 FROM
  flx_consu_habil_flexi h,
     bas_ctrl_fact bc,
     flx_audit_consu_habil au ,
     mae_clien,
     mae_clien_unida_admin,
     zon_terri_admin,
     zon_secci,
     zon_zona,
     zon_regio
where -- Activas de la campaña activa
      bc.ind_camp_act = '1'
  and bc.sta_camp = '0'
  and h.cod_peri_fact = bc.cod_peri
  and h.ind_acti = '1'
  and h.ind_canc = '0'
 -- join para la fecha de activacion
 and au.cod_acci(+) = '02'
 and au.cod_clie(+) = h.cod_clie
 --
 and zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi
 and zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
 and zon_zona.oid_zona = zon_secci.zzon_oid_zona
 and zon_regio.oid_regi = zon_zona.zorg_oid_regi
 and mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
 and mae_clien_unida_admin.ind_acti = '1'
 AND mae_clien.cod_clie = h.cod_clie
 --
  -- que tenga contrato enviado en STO
  and exists (select null
                    from (select * from sto_docum_digit where cod_tipo_docu = 'CIF'
                           union
                          select * from sto_histo_docum_digit where cod_tipo_docu = 'CIF' ) dd
                   where dd.cod_tipo_docu = 'CIF'
                     and dd.cod_clie = h.cod_clie
                     and dd.ind_envi = '1')
 --que no haya enviado contrato físico
 and h.ind_envi = '0'
 -- que su region este cerrando en este dia
 and zon_regio.cod_regi in (SELECT distinct(reg.COD_REGI)
                               FROM FAC_CONTR_CIERR con,
                                    FAC_TIPOS_CIERR tip,
                                    CRA_PERIO cra,
                                    SEG_PERIO_CORPO cor,
                                    SEG_PAIS pai,
                                    ZON_REGIO reg,
                                    SEG_MARCA mar,
                                    SEG_CANAL can
                              WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
                                AND cra.OID_PERI = con.PERD_OID_PERI
                                AND cor.OID_PERI = cra.PERI_OID_PERI
                                AND pai.OID_PAIS = cra.PAIS_OID_PAIS
                                AND reg.OID_REGI = con.ZORG_OID_REGI
                                AND tip.COD_TIPO_CIER = 'R'
                                AND pai.COD_PAIS = bc.cod_pais
                                AND mar.COD_MARC = 'T'
                                AND can.COD_CANA = 'VD'
                                AND con.VAL_RESU_PROC = 'OK'
                                AND reg.MARC_OID_MARC = mar.OID_MARC
                                AND reg.CANA_OID_CANA = can.OID_CANA
                                AND reg.PAIS_OID_PAIS = pai.OID_PAIS
                                and cor.COD_PERI = bc.cod_peri
                                and con.fec_cier = bc.fec_proc
                                )
     -- su fecha de activacion sea mayor a la fecha del cierre de su region en la campaña anterior
     and trunc((nvl(au.fec_acci,sysdate))) > (SELECT con.fec_cier
                                                FROM FAC_CONTR_CIERR con,
                                                     FAC_TIPOS_CIERR tip,
                                                     CRA_PERIO cra,
                                                     SEG_PERIO_CORPO cor,
                                                     SEG_PAIS pai,
                                                     ZON_REGIO reg,
                                                     SEG_MARCA mar,
                                                     SEG_CANAL can
                                               WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
                                                 AND cra.OID_PERI = con.PERD_OID_PERI
                                                 AND cor.OID_PERI = cra.PERI_OID_PERI
                                                 AND pai.OID_PAIS = cra.PAIS_OID_PAIS
                                                 AND reg.OID_REGI = con.ZORG_OID_REGI
                                                 AND reg.COD_REGI = zon_regio.cod_regi
                                                 AND tip.COD_TIPO_CIER = 'R'
                                                 AND pai.COD_PAIS = bc.cod_pais
                                                 AND mar.COD_MARC = 'T'
                                                 AND can.COD_CANA = 'VD'
                                                 AND con.VAL_RESU_PROC = 'OK'
                                                 AND reg.MARC_OID_MARC = mar.OID_MARC
                                                 AND reg.CANA_OID_CANA = can.OID_CANA
                                                 AND reg.PAIS_OID_PAIS = pai.OID_PAIS
                                                 and cor.COD_PERI = cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(bc.cod_peri,-1) --bc.cod_peri
                                                 and rownum = 1
                                                 )               ;

    /**************************************************************************************/
     TYPE t_codclie IS TABLE OF flx_consu_habil_flexi.cod_clie%TYPE;
     TYPE t_codperi IS TABLE OF bas_ctrl_fact.cod_peri%TYPE;
     type t_cod_pais is table of bas_ctrl_fact.cod_pais%type;
    /**************************************************************************************/
    v_codclie     t_codclie;
    v_codperi     t_codperi;
    v_cod_pais    t_cod_pais;
    /**************************************************************************************/
    i       BINARY_INTEGER := 0;
    rows       NUMBER := 5000;

  BEGIN


    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codclie,v_codperi,v_cod_pais LIMIT rows;
      IF v_codclie.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_codclie.first .. v_codclie.last
        LOOP

          update flx_consu_habil_flexi h
            set h.ind_acti = '0',
                h.ind_canc = '1'
          where h.cod_clie = v_codclie(i)
            and h.cod_peri_fact >= v_codperi(i);

             insert into flx_audit_consu_habil
               (cod_pais, cod_clie, fec_acci, cod_acci, usu_acci, cod_peri_fact)
             values
               (v_cod_pais(i), v_codclie(i), sysdate, '01', 'AUTO', v_codperi(i));

        END LOOP;

      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR flx_pr_desac_autom: ' || ls_sqlerrm);

  END flx_pr_desac_autom;

 /**************************************************************************
    Descripcion       : flx_pr_desac_autom

    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte                                                 SEG_CANAL can
                               WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER

  ***************************************************************************/
 PROCEDURE flx_pr_activ_web(p_numlote varchar2)
 IS

    CURSOR c_activ IS
      select  h.cod_clie, h.cod_peri, h.ind_acti
      from flx_activ_flexi_web h
     where nvl(h.ind_carg,'0')='0';

r_activ c_activ%ROWTYPE;
l_codpais    VARCHAR2(100);

  BEGIN

  SELECT DISTINCT cod_pais
  INTO l_codpais
  FROM bas_ctrl_fact;

    OPEN c_activ;
     LOOP
    FETCH c_activ INTO r_activ;
    EXIT WHEN c_activ%NOTFOUND;

   IF r_activ.ind_acti='1' THEN

    UPDATE FLX_CONSU_HABIL_FLEXI
             SET IND_HABI = 1,
                    IND_ACTI = 1,
                    IND_CANC = 0
             WHERE cod_pais=l_codpais
             and COD_CLIE = r_activ.cod_clie
             AND COD_PERI_FACT >= r_activ.cod_peri;


       INSERT INTO FLX_AUDIT_CONSU_HABIL
             (
                    COD_PAIS,
                    COD_CLIE,
                    FEC_ACCI,
                    COD_ACCI,
                    USU_ACCI,
                    COD_PERI_FACT,
                    COD_MOTI
                    )
             VALUES(
                    l_codpais,
                    r_activ.cod_clie,
                    Sysdate,
                    '02',--#codigoAccion#,
                    'CARGA WEB',
                    r_activ.cod_peri,
                    NULL
                    );

   ELSE

    UPDATE FLX_CONSU_HABIL_FLEXI
    SET
     IND_HABI = 1,
                    IND_ACTI = 0,
                    IND_CANC = 1
             WHERE cod_pais=l_codpais
      AND COD_CLIE = r_activ.cod_clie
             AND COD_PERI_FACT >= r_activ.cod_peri;


       INSERT INTO FLX_AUDIT_CONSU_HABIL
             (
                    COD_PAIS,
                    COD_CLIE,
                    FEC_ACCI,
                    COD_ACCI,
                    USU_ACCI,
                    COD_PERI_FACT,
                    COD_MOTI
                    )
             VALUES(
                    l_codpais,
                    r_activ.cod_clie,
                    Sysdate,
                    '01',--#codigoAccion#,
                    'CARGA WEB',
                    r_activ.cod_peri,
                    NULL
                    );


      END IF;



    END LOOP;
    CLOSE c_activ;

  UPDATE flx_activ_flexi_web h
  SET h.ind_carg='1'
  WHERE nvl(h.ind_carg,0)='0';

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR flx_pr_activ_web: ' || ls_sqlerrm);

   NULL;

  END flx_pr_activ_web;

END FLX_PKG_PROCE;
/
