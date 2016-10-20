CREATE OR REPLACE PACKAGE INT_PKG_CCC IS

 /***************************************************************************
  Descripcion       : Obtiene el numero de lote para la interface
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_NUMER_LOTE
 RETURN VARCHAR2;

 /***************************************************************************
  Descripcion       : Obtiene el numero de lote para la interface
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE CCC_PR_OBTIE_NUMER_LOTE(
  p_num_lote                       OUT  VARCHAR2 );

 /***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE CCC_PR_CARGA_MOVIM_BANCA(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE,
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE,
  p_num_lote_exte                  IN   ccc_movim_banca.num_lote_exte%TYPE,
  p_cod_usua                       IN   VARCHAR2);

 PROCEDURE int_pr_ccc_valid_codig_consu(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE int_pr_ccc_valid_digit_contr(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE int_pr_ccc_valid_banco_agric(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE int_pr_ccc_valid_docum_ident(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE int_pr_ccc_valid_tipol_consu(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE int_pr_ccc_envio_email_liqui(
  p_num_lote                       IN  fin_inter_ejecu.num_lote%TYPE);

 PROCEDURE INT_PR_CCC_JOB_CARGA_BANCA;

 PROCEDURE INT_PR_CCC_CARGA_LOTES_BANCA(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE);

 PROCEDURE INT_PR_CCC_CARGA_CHEQU_BANCA(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE);

 PROCEDURE INT_PR_CCC_GENER_INFOR_RECAU(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN4(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN5(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN6(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-2
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SALDO_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-3
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SALDO_CASTI(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-4
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_MAEST_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-5
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_NOVED_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_INFOR_COBFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_INFOR_CDIFI(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_INFOR_ABIFI(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2);

PROCEDURE INT_PR_CCC_GENER_INFOR_AREFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_INFOR_EREFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER);

 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_fec_proc_inic                  IN   VARCHAR2,
  p_fec_proc_fina                  IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER);

 PROCEDURE INT_PR_CCC_VALID_INFOR_SAPFI(
  p_oid_regi_inic                  IN   NUMBER,
  p_oid_regi_fina                  IN   NUMBER);

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Cobranza a SAPFI
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SAPFI_COBRA(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2,
  p_fec_proc_hast                  IN   VARCHAR2,
  p_num_lote                       IN   VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que genera la informacion a SAPFI
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que reprocesa la informacion a SAPFI
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_REPRO_INFOR_SAPFI(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_fec_proc_inic                  IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2);

 /***************************************************************************
  Descripcion       : Procedimiento que genera la informacion para Datamart
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_GENER_INFOR_DATAM(
  p_cod_modu                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2);

 PROCEDURE INT_PR_CCC_GENER_SALDO_REPDO(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE   DEFAULT USER);

 PROCEDURE INT_PR_CCC_GENER_BUROC_REPDO;

 PROCEDURE INT_CCC_PR_CARGO_EFTGR_MAIL;

 PROCEDURE INT_CCC_PR_CARGO_EFTGR_FTP;

 PROCEDURE INT_PR_CCC_GENER_SAPFI_CHILE(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER);

 PROCEDURE INT_CCC_PR_GENER_INFOR_MOROS(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER);

 PROCEDURE INT_CCC_PR_GENER_MOROS_DICOM(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER,
  p_can_regi                       OUT NUMBER);

 PROCEDURE INT_CCC_PR_GENER_MOROS_DATAB(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER,
  p_can_regi                       OUT NUMBER);

 PROCEDURE INT_CCC_PR_GENER_MOROS_SIISA(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER,
  p_can_regi                       OUT NUMBER);

 PROCEDURE INT_PR_CCC_GENER_EMAIL_CONFI(
  p_cod_proc                       IN   ccc_proce_envio_email.cod_proc%TYPE,
  p_ind_ejec                       IN   NUMBER,
  p_val_mens                       IN   VARCHAR2 DEFAULT NULL,
  p_des_erro                       IN   VARCHAR2 DEFAULT NULL);

 PROCEDURE INT_PR_CCC_SLEEP(
  p_num_segu                       IN   INT);

 /***************************************************************************
  Descripcion       : Obtiene el codigo identificador de proceso para
                                 la recepcion de movimientos bancarios
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_CODI_IDEN_PROC(
  p_cod_clie                       IN   VARCHAR2,
  p_cod_digi_cont                  IN   VARCHAR2,
  P_ind_digi_cont                  IN   NUMBER)
 RETURN VARCHAR2;

 /***************************************************************************
  Descripcion       : Obtiene el codigo identificador de estado  para
                                 la recepcion de movimientos bancarios
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_CODI_IDEN_ESTAD(
  p_cod_clie                       IN   VARCHAR2,
  p_cod_digi_cont                  IN   VARCHAR2,
  P_ind_digi_cont                  IN   NUMBER)
 RETURN VARCHAR2;

/***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 12/10/2012
  Autor             :
 ***************************************************************************/
 PROCEDURE CCC_PR_RUTEA_PAGOS_SEGUN_MARCA(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE,
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE,
  p_num_lote_exte                  IN   ccc_movim_banca.num_lote_exte%TYPE,
  p_num_posi_codi_clie             IN   seg_pais.num_posi_nume_clie%TYPE,
  p_cod_usua                       IN   VARCHAR2);



 /***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 12/10/2012
  Autor             :
 ***************************************************************************/
 PROCEDURE CCC_PR_RUTEA_PAGOS_PRIOR_DEST  (
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE);


  /***************************************************************************
  Descripcion       : Genera archivo TXT
  Fecha Creacion    : 10/03/2014
  Autor             : Gonzalo Javier Huertas Agurto
  Parametros        :
              psCodigoPeriodo : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
 PROCEDURE INT_PR_CCC_CARGA_DEUDA_WEB(

  psCodigoPais                   VARCHAR2,
  pscodigosistema                     VARCHAR2,
  pscodigointerfaz                    VARCHAR2,
  psnombrearchivo                     VARCHAR2,
  p_ante_oid_regi                OUT    VARCHAR2,
  p_ulti_oid_regi                OUT    VARCHAR2);

    /***************************************************************************
  Descripcion       : Genera Interfase de informacion buro crediticia
  Fecha Creacion    : 15/04/2015
  Autor             : Diego Torres L.
  ***************************************************************************/
  PROCEDURE INT_PR_CCC_GENER_INFOR_BURO(psCodigoPais VARCHAR2,
                                        psCodigoSistema VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                        psNombreArchivo VARCHAR2,
                                        pscodigousuario    VARCHAR2);

 END INT_PKG_CCC;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_CCC is

 gc_cod_modu                       CONSTANT CHAR(3):='CCC';
 gc_cod_inte_ban1                  CONSTANT CHAR(5):='BAN-1';
 gc_cod_inte_ban2                  CONSTANT CHAR(5):='BAN-2';
 gc_cod_inte_ban7                  CONSTANT CHAR(5):='BAN-7';
 gc_cod_inte_gene_info_ban4        CONSTANT CHAR(5):='BAN-4';
 gc_cod_inte_gene_info_ban5        CONSTANT CHAR(5):='BAN-5';
 gc_cod_inte_gene_info_ban6        CONSTANT CHAR(5):='BAN-6';
 gc_cod_inte_sapf                  CONSTANT CHAR(5):='SAPFI';
 gc_cod_proc_vali_codi_cons        CONSTANT CHAR(4):='7121';
 gc_cod_proc_vali_digi_ctrl        CONSTANT CHAR(4):='7122';
 gc_cod_proc_vali_banc_agri        CONSTANT CHAR(4):='7123';
 gc_cod_proc_vali_docu_iden        CONSTANT CHAR(4):='7124';
 gc_cod_proc_vali_tipo_clie        CONSTANT CHAR(4):='7125';

 ln_sqlcode                        NUMBER(10);
 ls_sqlerrm                        VARCHAR2(2500);
 w_filas                           NUMBER:=1000;
 gv_des_log                        VARCHAR2(4000);
 gc_cod_iden_proc_tran             CONSTANT VARCHAR2(1):='T';
 gc_cod_iden_proc_proc             CONSTANT VARCHAR2(1):='P';
 gc_cod_iden_proc_erro             CONSTANT VARCHAR2(1):='I';

 gc_cod_proc_reca_banc             CONSTANT VARCHAR2(6):='TES001';
 gc_cod_subp_reca_banc_auto        CONSTANT VARCHAR2(1):='1';
 gc_cod_subp_reca_banc_manu        CONSTANT VARCHAR2(1):='2';
 gc_cod_subp_reca_banc_regu        CONSTANT VARCHAR2(1):='3';
 gc_cod_subp_reca_banc_cheq        CONSTANT VARCHAR2(1):='4';
 gc_cod_inte_data_carg             CONSTANT VARCHAR2(6):='DAT-1';
 gc_cod_inte_data_abon             CONSTANT VARCHAR2(6):='DAT-2';
 gc_cod_inte_data_fech_cier        CONSTANT VARCHAR2(6):='DAT-3';
 gc_cod_inte_data_tipo_carg        CONSTANT VARCHAR2(6):='DAT-4';
 gc_cod_inte_data_tipo_abon        CONSTANT VARCHAR2(6):='DAT-5';
 gc_cod_tipo_orig_inte             CONSTANT VARCHAR2(3):='INT';
 gc_cod_tipo_orig_digi             CONSTANT VARCHAR2(3):='DIG';
 gc_cod_tipo_orig_exce             CONSTANT VARCHAR2(3):='EXC';

 gc_cod_tipo_orig_regu             CONSTANT VARCHAR2(3):='REG';
 gc_cod_tipo_tran_cheq             CONSTANT VARCHAR2(5):='TBCHQ';
 gc_cod_cuen_corr_banc_cheq        CONSTANT VARCHAR2(6):='BACHEQ';
 gc_cod_inte_dicom                 CONSTANT VARCHAR2(7):='DICOM';
 gc_cod_inte_datab                 CONSTANT VARCHAR2(7):='DATABIZ';
 gc_cod_inte_sapf_cobr             CONSTANT VARCHAR2(7):='COBFI';
 gc_cod_inte_sapf_cad              CONSTANT VARCHAR2(7):='CDIFI';
 gc_cod_inte_sapf_adi              CONSTANT VARCHAR2(7):='ADIFI';
 gc_cod_inte_sapf_are              CONSTANT VARCHAR2(7):='AREFI';
 gc_cod_inte_sapf_ere              CONSTANT VARCHAR2(7):='EREFI';
 gc_cod_inte_sapf_ape              CONSTANT VARCHAR2(7):='APEFI';
 gc_cod_gene_info_sapf             CONSTANT VARCHAR2(5):='SAPFI';
 gc_ind_debe                       CONSTANT CHAR(1):='D';
 gc_ind_habe                       CONSTANT CHAR(1):='H';


 e_inte_erro_cont                  EXCEPTION;
 e_dife_impo                       EXCEPTION;
 e_no_gene_regi                    EXCEPTION;
 e_exec_inte_entr                  EXCEPTION;
 e_no_exis_pago_banc               EXCEPTION;
 e_no_arch_gene                    EXCEPTION;

  -- Declaracion de Tipos
 TYPE t_tab_camp                   IS TABLE OF VARCHAR2(250) INDEX BY BINARY_INTEGER;

 -- Excepciones --
 gv_reco_trac                      FIN_PKG_GENER.error_rt;

 /***************************************************************************
  Descripcion       : Obtiene el codigo identificador de proceso para
                                 la recepcion de movimientos bancarios
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION ccc_fn_obtie_codi_iden_proc(
  p_cod_clie                       IN   VARCHAR2,
  p_cod_digi_cont                  IN   VARCHAR2,
  p_ind_digi_cont                  IN   NUMBER)
 RETURN VARCHAR2
 IS

  lv_cod_iden_proc_tran            CONSTANT VARCHAR2(1):='T';
  lv_cod_iden_proc_erro            CONSTANT VARCHAR2(1):='I';
  lv_oid_clie                      mae_clien.oid_clie%TYPE;
  lv_cod_digi_cont                 mae_clien.cod_digi_ctrl%TYPE;

 BEGIN

  SELECT mc.oid_clie , mc.cod_digi_ctrl
  INTO lv_oid_clie, lv_cod_digi_cont
  FROM mae_clien mc
  WHERE mc.cod_clie=p_cod_clie;

  IF p_ind_digi_cont=0 THEN
   RETURN lv_cod_iden_proc_tran;
  ELSIF p_ind_digi_cont=1 AND p_cod_digi_cont=lv_cod_digi_cont THEN
   RETURN lv_cod_iden_proc_tran;
  ELSE
   RETURN lv_cod_iden_proc_erro;
  END IF;

 EXCEPTION
  WHEN no_data_found THEN
    RETURN lv_cod_iden_proc_erro;

 END ccc_fn_obtie_codi_iden_proc;

 /***************************************************************************
  Descripcion       : Obtiene el codigo identificador de estado para
                                 la recepcion de movimientos bancarios
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION ccc_fn_obtie_codi_iden_estad(
  p_cod_clie                       IN   VARCHAR2,
  p_cod_digi_cont                  IN   VARCHAR2,
  p_ind_digi_cont                  IN   NUMBER)
 RETURN VARCHAR2
 IS

  lv_cod_iden_esta_norm            CONSTANT VARCHAR2(1):='N';
  lv_cod_iden_esta_pend            CONSTANT VARCHAR2(1):='P';
  lv_oid_clie                      mae_clien.oid_clie%TYPE;
  lv_cod_digi_cont                 mae_clien.cod_digi_ctrl%TYPE;

 BEGIN

  SELECT mc.oid_clie , mc.cod_digi_ctrl
  INTO lv_oid_clie, lv_cod_digi_cont
  FROM mae_clien mc
  WHERE mc.cod_clie=p_cod_clie;

  IF p_ind_digi_cont=0 THEN
   RETURN lv_cod_iden_esta_norm;
  ELSIF p_ind_digi_cont=1 AND p_cod_digi_cont=lv_cod_digi_cont THEN
   RETURN lv_cod_iden_esta_norm;
  ELSE
   RETURN lv_cod_iden_esta_pend;
  END IF;

 EXCEPTION
  WHEN no_data_found THEN
   RETURN lv_cod_iden_esta_pend;

 END ccc_fn_obtie_codi_iden_estad;


   /***************************************************************************
     Descripcion       : Funcion que obtiene el numero de lote para la interface
    Fecha Creacion    : 13/05/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION ccc_fn_obtie_numer_lote
   RETURN VARCHAR2
   IS
      lv_cont                    ccc_numer_lote.cont%TYPE;
      lv_val_cade_fech    ccc_numer_lote.val_cade_fech%TYPE;
      lv_num_secu            VARCHAR2(5);
      lv_num_lote             ccc_movim_banca.num_lote%TYPE;

   BEGIN

      lv_val_cade_fech:=to_char(trunc(SYSDATE),'YYYYMMDD');

      SELECT cnl.cont
      INTO lv_cont
      FROM ccc_numer_lote cnl
      WHERE cnl.val_cade_fech=lv_val_cade_fech
      FOR UPDATE;

      UPDATE ccc_numer_lote cnl
      SET cnl.cont=lv_cont+1
      WHERE cnl.val_cade_fech=lv_val_cade_fech;

      lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

     lv_num_lote := concat(lv_val_cade_fech,lv_num_secu);

     RETURN lv_num_lote;

  EXCEPTION
    WHEN no_data_found THEN
        lv_cont:=101;
        lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

       INSERT INTO ccc_numer_lote VALUES (to_char(trunc(SYSDATE),'YYYYMMDD'),lv_cont+1);

       lv_num_lote := concat(lv_val_cade_fech,lv_num_secu);

       RETURN lv_num_lote;
   END ccc_fn_obtie_numer_lote;

   /***************************************************************************
     Descripcion       : Procedimiento que obtiene el numero de lote para la interface
    Fecha Creacion    : 13/05/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   PROCEDURE ccc_pr_obtie_numer_lote(
      p_num_lote                   OUT      VARCHAR2 )
  IS
     lv_cont                           ccc_numer_lote.cont%TYPE;
     lv_val_cade_fech          ccc_numer_lote.val_cade_fech%TYPE;
     lv_num_secu                  VARCHAR2(5);

  BEGIN

     lv_val_cade_fech:=to_char(trunc(SYSDATE),'YYYYMMDD');

     SELECT cnl.cont
     INTO lv_cont
     FROM ccc_numer_lote cnl
     WHERE cnl.val_cade_fech=lv_val_cade_fech
     FOR UPDATE;

     UPDATE ccc_numer_lote cnl
     SET cnl.cont=lv_cont+1
     WHERE cnl.val_cade_fech=lv_val_cade_fech;

     lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

     p_num_lote := concat(lv_val_cade_fech,lv_num_secu);

  EXCEPTION
    WHEN no_data_found THEN
        lv_cont:=101;
        lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

       INSERT INTO ccc_numer_lote VALUES (to_char(trunc(SYSDATE),'YYYYMMDD'),lv_cont+1);

       p_num_lote := concat(lv_val_cade_fech,lv_num_secu);

   END ccc_pr_obtie_numer_lote;

 PROCEDURE ccc_pr_valid_banco_agric(
  p_num_lote                       IN   ccc_movim_banca.num_lote%TYPE)
 IS

  lv_imp_suma                      NUMBER(12):=0;
  lv_imp_rem                       NUMBER(12):=0;
  lv_valo_npe                      VARCHAR2(30);
  lv_valo_digi                     NUMBER(1);

 BEGIN

  FOR v_ccc IN (SELECT mb.oid_movi_banc, mb.cod_cons, mb.val_nomb_ofic FROM ccc_movim_banca mb where mb.num_lote = p_num_lote)  LOOP

   lv_imp_suma := 0;
   lv_valo_npe :=   substr(v_ccc.val_nomb_ofic,1,12) || v_ccc.cod_cons ||   substr(v_ccc.val_nomb_ofic,13,1);

   FOR x IN 1 .. 19 LOOP

    lv_valo_digi:= SUBSTR(lv_valo_npe,x,1);


    IF MOD(x,2) = 0 THEN

     lv_imp_suma := lv_imp_suma + lv_valo_digi;

    ELSE

     lv_imp_suma := lv_imp_suma + ( lv_valo_digi * 2 ) ;

     IF lv_valo_digi > 4 THEN

      lv_imp_suma := lv_imp_suma + 1;

     END IF;

    END IF;

   END LOOP;

   lv_imp_rem:= MOD((10 - MOD(lv_imp_suma,10)),10);

   IF lv_imp_rem = substr(v_ccc.val_nomb_ofic,13,1) THEN

    UPDATE ccc_movim_banca mb
    SET mb.cod_iden_proc = gc_cod_iden_proc_tran,
        mb.val_hora_norm_adic = 'N',
        mb.clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_ccc.cod_cons)
    WHERE mb.oid_movi_banc = v_ccc.oid_movi_banc;

   END IF;

  END LOOP;

 END ccc_pr_valid_banco_agric;

 /***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE CCC_PR_CARGA_MOVIM_BANCA(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE,
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE,
  p_num_lote_exte                  IN   ccc_movim_banca.num_lote_exte%TYPE,
  p_cod_usua                       IN   VARCHAR2)
 IS

  lv_oid_pais                      seg_pais.oid_pais%TYPE;
  lv_oid_soci                      seg_socie.oid_soci%TYPE;
  lv_oid_subp_crea_banc            ccc_subpr.oid_subp%TYPE;
  lv_oid_tcab_crea                 ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_num_posi_codi_clie            seg_pais.num_posi_nume_clie%TYPE;
  lv_ind_digi_cont                 ccc_pais_socie_param.ind_digi_cheq%TYPE;
  lv_oid_tipo_tran                 ccc_tipo_trans.oid_tipo_tran%TYPE;
  lv_cod_tipo_tran                 ccc_tipo_trans.cod_tipo_tran%TYPE;
  lv_ind_rec_banc_conv_nume        NUMBER(1);
  lv_ind_util_docu_iden            NUMBER(1);

  lc_cod_erro                      CONSTANT VARCHAR2(2):='00';
  lv_cod_erro                      VARCHAR2(2);
  lc_val_esta_movi_pend            CONSTANT VARCHAR2(1):='P';
  lc_cod_proc_reca                 CONSTANT VARCHAR2(6):='TES001';
  lc_cod_subp_reca_auto            CONSTANT NUMBER(1):=1;
  lv_cod_tipo_orig                 ccc_tempo_movim_banca.cod_tipo_orig%TYPE;

 BEGIN

  lv_oid_pais:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_Pais);
  lv_oid_soci:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_SOCIE(p_cod_soci);

  -- Validar el uso del codigo o de la cedula de identidad --
  SELECT ccb.ind_util_docu_iden
  INTO lv_ind_util_docu_iden
  FROM ccc_cuent_corri_banca ccb
  WHERE ccb.cod_cc = p_cod_cban;

   UPDATE ccc_tempo_movim_banca tmb
   SET tmb.cod_usua_proc = tmb.cod_clie
   WHERE tmb.cod_pais = p_cod_pais
     AND tmb.cod_soci = p_cod_soci
     AND tmb.cod_banc_sicc = p_cod_cban
     AND tmb.num_lote_exte = p_num_lote_exte;

  IF lv_ind_util_docu_iden = 1 THEN



   UPDATE ccc_tempo_movim_banca tmb
      SET tmb.cod_clie = NVL(( SELECT mc.cod_clie
                        FROM mae_clien_ident mci,
                             mae_clien mc
                        WHERE mc.oid_clie = mci.clie_oid_clie
                        AND mci.num_docu_iden = tmb.cod_usua_proc),'0')
    WHERE tmb.cod_pais = p_cod_pais
      AND tmb.cod_soci = p_cod_soci
      AND tmb.cod_banc_sicc = p_cod_cban
      AND tmb.num_lote_exte = p_num_lote_exte;

  END IF;


  --Obtener el Indicador de Digito de Chequeo
  SELECT psp.ind_digi_cheq
  INTO lv_ind_digi_cont
  FROM ccc_pais_socie_param psp
  WHERE psp.cod_pais=p_cod_pais
  AND psp.cod_soci = p_cod_soci;

  -- Obtener el Numero de Digitos del Codigo de la Consultora
  SELECT sp.num_posi_nume_clie
  INTO lv_num_posi_codi_clie
  FROM seg_pais sp
  WHERE sp.cod_pais=p_cod_pais;

  SELECT ind_rece_banc_conv_nume
  INTO lv_ind_rec_banc_conv_nume
  FROM ccc_pais_socie_param;

  IF lv_ind_rec_banc_conv_nume=1 THEN

   UPDATE ccc_tempo_movim_banca tmb
   SET tmb.cod_clie = LPAD(TO_NUMBER(tmb.cod_clie),lv_num_posi_codi_clie,'0')
   WHERE tmb.cod_pais = p_cod_pais
     AND tmb.cod_soci = p_cod_soci
     AND tmb.cod_banc_sicc = p_cod_cban
     AND tmb.num_lote_exte = p_num_lote_exte;

  END IF;

      -- Obteniendo el Codigo de Consultora y el Digito de Chequeo
      UPDATE ccc_tempo_movim_banca tmb
      SET tmb.cod_clie      = TRIM(SUBSTR(tmb.cod_clie,1,lv_num_posi_codi_clie)),
          tmb.val_digi_cheq =  TRIM(SUBSTR(tmb.cod_clie,lv_num_posi_codi_clie+1))
      WHERE tmb.cod_pais = p_cod_pais
      AND tmb.cod_soci = p_cod_soci
      AND tmb.cod_banc_sicc = p_cod_cban
      AND tmb.num_lote_exte = p_num_lote_exte;

      -- Subproceso de Creacion
      SELECT cs.oid_subp
      INTO lv_oid_subp_crea_banc
      FROM
           ccc_proce cp,
           ccc_subpr cs
      WHERE cp.oid_proc=cs.ccpr_oid_proc
      AND cp.cod_proc = lc_cod_proc_reca
      AND cs.cod_subp = lc_cod_subp_reca_auto;

      -- Tipo Cargo Abono de Creacion
      SELECT tas.tcab_oid_tcab
      INTO lv_oid_tcab_crea
      FROM ccc_tipo_abono_subpr tas
      WHERE tas.subp_oid_subp=lv_oid_subp_crea_banc;

      -- Oid Cuenta Corriente Bancaria
      SELECT ccb.oid_cuen_corr_banc
      INTO lv_oid_ccba
      FROM ccc_cuent_corri_banca ccb
      WHERE ccb.pais_oid_pais=lv_oid_pais
      AND ccb.cod_cc = p_cod_cban;

      SELECT tmb.val_tipo_tran, tmb.cod_tipo_orig
      INTO lv_cod_tipo_tran, lv_cod_tipo_orig
      FROM ccc_tempo_movim_banca tmb
      WHERE tmb.cod_pais = p_cod_pais
      AND tmb.cod_soci = p_cod_soci
      AND tmb.cod_banc_sicc = p_cod_cban
      AND tmb.num_lote_exte = p_num_lote_exte
      AND rownum < 2;

      SELECT tt.oid_tipo_tran
      INTO lv_oid_tipo_tran
      FROM ccc_tipo_trans tt
      WHERE tt.cod_tipo_tran=lv_cod_tipo_tran;

      -- Insetando en los Movimientos Bancarios --
      INSERT INTO ccc_movim_banca
         SELECT
            ccc_cmba_seq.NEXTVAL,
            lv_oid_soci,
            lv_oid_subp_crea_banc,
            lv_oid_subp_crea_banc,
            lv_oid_tcab_crea,
            lv_oid_tcab_crea,
            lv_oid_tipo_tran,
            lv_oid_ccba,
            tmb.num_cons_tran,
            p_num_lote_inte,
            tmb.cod_clie,
            lc_cod_erro,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            lc_val_esta_movi_pend,
            NULL,
            TO_DATE(tmb.fec_pago,'YYYYMMDD'),
            trunc(SYSDATE),
            SYSDATE,
            CCC_FN_OBTIE_CODI_IDEN_ESTAD(tmb.cod_clie,tmb.val_digi_cheq,lv_ind_digi_cont),
            CCC_FN_OBTIE_CODI_IDEN_PROC(tmb.cod_clie,tmb.val_digi_cheq,lv_ind_digi_cont),
            TO_NUMBER(tmb.imp_pago),
            tmb.val_nomb_ofic,
            tmb.num_cupo,
            tmb.num_docu,
            tmb.num_fact_bole,
            NULL,
            NULL,
            tmb.val_digi_cheq,
            p_cod_usua,
            SYSDATE,
            TO_NUMBER(tmb.imp_pago),
            p_cod_usua,
            tmb.num_lote_exte,
            0,
            0,
            TO_NUMBER(tmb.imp_pago),
            lv_oid_pais,
            NULL,
            NULL,
            CASE
              WHEN CCC_FN_OBTIE_CODI_IDEN_PROC(tmb.cod_clie,tmb.val_digi_cheq,lv_ind_digi_cont)=gc_cod_iden_proc_tran THEN
               (SELECT mc.oid_clie
                FROM mae_clien mc
                WHERE mc.cod_clie=tmb.cod_clie)
             ELSE
              NULL
            END CASE,
            NULL,
            p_cod_usua,                           -- cod_usua_rece  varchar2(25)
            SYSDATE,                           -- val_hora_rece  date
            NULL,                           -- cod_usua_liqu  varchar2(25)
            NULL,                           -- val_hora_liqu  date
            NULL,                           -- cod_usua_regu  varchar2(25)
            NULL,                           -- val_hora_regu  date
            NULL,                     -- cod_usu_carg_exce  varchar2(25)
            NULL,                         -- val_hora_carg_exce  date
            0,                               -- ind_pago_regu
            NULL,                             -- val_digi_ctrl
            NULL,                             -- num_docu_iden
            NULL,                             -- oid_banc_sucu_cheq
            NULL,                            -- ind_tipo_cheq
            NULL,                             -- fec_cobr_cheq,
            NULL,
            NULL,
            NULL,                              --cod_tipo_docu_homo,
            NULL,
            NULL,
            NULL,
            0,                             -- ind_pago_inco
            0,                            -- ind_pago_exce
            0,                             --ind_exce_vali
            0,                             --imp_apli_clie NUMBER(15,2)
            0,                             -- imp_apli_exce NUMBER(15,2)
            0,                             --imp_apli_inco NUMBER(15,2)
            0,                             --imp_apli_cobr_exte NUMBER(15,2)
            0,                             --imp_apli_cobr_pend_regu NUMBER(15,2));
            0,                              -- ind_apli-fami_prot
            0,                               -- ind_elim
            0                                --ind_pago_reve
         FROM ccc_tempo_movim_banca tmb
         WHERE tmb.cod_pais = p_cod_pais
         AND tmb.cod_soci = p_cod_soci
         AND tmb.cod_banc_sicc = p_cod_cban
         AND tmb.num_lote_exte = p_num_lote_exte;


     IF p_cod_pais = 'SVE' AND p_cod_cban = 'BAAGR' THEN

      ccc_pr_valid_banco_agric(p_num_lote_inte);

     END IF;

     lv_cod_erro := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoErrorCxR');

     IF lv_cod_erro IS NOT NULL THEN

      UPDATE ccc_movim_banca mb
      SET mb.Cod_Erro = lv_cod_erro
      WHERE mb.num_lote = p_num_lote_inte
      AND mb.cod_iden_proc = 'I';

     END IF;

     -- Manejo de Pagos Cruzados
     CCC_PR_RUTEA_PAGOS_SEGUN_MARCA(p_cod_pais, p_cod_soci, p_cod_cban, p_num_lote_inte, p_num_lote_exte, lv_num_posi_codi_clie, p_cod_usua);
     -- --

      -- Registrando Lote Bancario
      CCC_PKG_GENER.CCC_PR_REGIS_LOTE_BANCA(p_cod_cban, lv_cod_tipo_orig, p_num_lote_inte,p_cod_usua);
     -- --

      DELETE FROM ccc_tempo_movim_banca tmb;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_CARGA_MOVIM_BANCA: ' ||
                               ls_sqlerrm);
   END CCC_PR_CARGA_MOVIM_BANCA;

 PROCEDURE int_pr_ccc_valid_codig_consu(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_num_posi_codi_clie            seg_pais.num_posi_nume_clie%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_ind_rec_banc_conv_nume        NUMBER(1);

  lv_cod_erro                      VARCHAR2(250);

 BEGIN

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,p_cod_usua,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio de la Validacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec,gv_des_log);

  IF p_cod_cuen_corr_banc = 'T' THEN
   lv_oid_ccba := 999;
  ELSE
   lv_oid_ccba := ccc_pkg_gener.CCC_FN_OBTIE_OID_BANCO_CCBAN(p_cod_cuen_corr_banc);
  END IF;

  -- Obtener el Numero de Digitos del Codigo de la Consultora
  SELECT sp.num_posi_nume_clie
  INTO lv_num_posi_codi_clie
  FROM seg_pais sp
  WHERE sp.cod_pais = lv_cod_pais;

  SELECT ind_rece_banc_conv_nume
  INTO lv_ind_rec_banc_conv_nume
  FROM ccc_pais_socie_param;

  IF lv_ind_rec_banc_conv_nume=1 THEN

   UPDATE ccc_movim_banca_valid mb
   SET mb.cod_cons = LPAD(TO_NUMBER(mb.cod_cons),lv_num_posi_codi_clie,'0')
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  END IF;

  -- Obteniendo el Codigo de Consultora y el Digito de Chequeo
  UPDATE ccc_movim_banca_valid mb
  SET
   mb.cod_cons      = TRIM(SUBSTR(mb.cod_cons,1,lv_num_posi_codi_clie)),
   mb.cod_digi_ctrl = TRIM(SUBSTR(mb.cod_cons,lv_num_posi_codi_clie+1))
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Obteniendo el Codigo de Consultora y el Digito de Chequeo';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec,gv_des_log);

  UPDATE ccc_movim_banca_valid mb
  SET mb.cod_erro = p_cod_tipo_erro,
      mb.cod_iden_proc = p_cod_iden_proc,
      mb.val_esta_movi_pend = p_cod_esta_movi
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba)
    AND NOT EXISTS (
            SELECT NULL
            FROM mae_clien mc
            WHERE mc.cod_clie = mb.cod_cons);

  gv_des_log:='Se encontraron ' || SQL%ROWCOUNT || ' pagos bancarios incorrectos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec,gv_des_log);

  UPDATE ccc_movim_banca_valid mb
  SET mb.clie_oid_clie = ( SELECT mc.oid_clie
                           FROM mae_clien mc
                           WHERE mc.cod_clie = mb.cod_cons)
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Actualizando el Oid Clie a ' || SQL%ROWCOUNT || ' pagos bancarios';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_codi_cons,lv_id_proc_ejec, 2);

 END int_pr_ccc_valid_codig_consu;

 PROCEDURE int_pr_ccc_valid_digit_contr(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;

  lv_cod_erro                      VARCHAR2(250);

 BEGIN

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,p_cod_usua,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio de la Validacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,lv_id_proc_ejec,gv_des_log);

  IF p_cod_cuen_corr_banc = 'T' THEN
   lv_oid_ccba := 999;
  ELSE
   lv_oid_ccba := ccc_pkg_gener.CCC_FN_OBTIE_OID_BANCO_CCBAN(p_cod_cuen_corr_banc);
  END IF;

  UPDATE ccc_movim_banca_valid mb
  SET
   mb.cod_erro = p_cod_tipo_erro,
   mb.cod_iden_proc = p_cod_iden_proc,
   mb.val_esta_movi_pend = p_cod_esta_movi
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba)
    AND mb.cod_digi_ctrl <> (SELECT mc.cod_digi_ctrl
                             FROM mae_clien mc
                             WHERE mc.oid_clie = mb.clie_oid_clie);

  UPDATE ccc_movim_banca_valid mb
  SET
   mb.clie_oid_clie = NULL
  WHERE mb.cod_erro = p_cod_tipo_erro
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Se encontraron ' || SQL%ROWCOUNT || ' pagos bancarios incorrectos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,lv_id_proc_ejec,gv_des_log);

  UPDATE ccc_movim_banca_valid mb
  SET mb.clie_oid_clie = ( SELECT mc.oid_clie
                           FROM mae_clien mc
                           WHERE mc.cod_clie = mb.cod_cons)
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Actualizando el Oid Clie a ' || SQL%ROWCOUNT || ' pagos bancarios';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_digi_ctrl,lv_id_proc_ejec, 2);

 END int_pr_ccc_valid_digit_contr;

 PROCEDURE int_pr_ccc_valid_banco_agric(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;

  lv_cod_erro                      VARCHAR2(250);
  lv_imp_suma                      NUMBER(12):=0;
  lv_imp_rem                       NUMBER(12):=0;
  lv_valo_npe                      VARCHAR2(30);
  lv_valo_digi                     NUMBER(1);

 BEGIN

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_banc_agri,p_cod_usua,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio de la Validacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_banc_agri,lv_id_proc_ejec,gv_des_log);

  IF p_cod_cuen_corr_banc = 'T' THEN
   lv_oid_ccba := 999;
  ELSE
   lv_oid_ccba := ccc_pkg_gener.CCC_FN_OBTIE_OID_BANCO_CCBAN(p_cod_cuen_corr_banc);
  END IF;

  FOR v_ccc IN (SELECT
                 mb.oid_movi_banc,
                 mb.cod_cons,
                 mb.val_nomb_ofic
                FROM ccc_movim_banca_valid mb)  LOOP

   lv_imp_suma := 0;
   lv_valo_npe :=   substr(v_ccc.val_nomb_ofic,1,12) || v_ccc.cod_cons ||   substr(v_ccc.val_nomb_ofic,13,1);

   FOR x IN 1 .. 19 LOOP

    lv_valo_digi:= SUBSTR(lv_valo_npe,x,1);

    IF MOD(x,2) = 0 THEN

     lv_imp_suma := lv_imp_suma + lv_valo_digi;

    ELSE

     lv_imp_suma := lv_imp_suma + ( lv_valo_digi * 2 ) ;

     IF lv_valo_digi > 4 THEN

      lv_imp_suma := lv_imp_suma + 1;

     END IF;

    END IF;

   END LOOP;

   lv_imp_rem:= MOD((10 - MOD(lv_imp_suma,10)),10);

   IF lv_imp_rem <> substr(v_ccc.val_nomb_ofic,13,1) THEN

    UPDATE ccc_movim_banca_valid mb
    SET
     mb.cod_erro = p_cod_tipo_erro,
     mb.cod_iden_proc = p_cod_iden_proc,
     mb.val_esta_movi_pend = p_cod_esta_movi,
     mb.clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_ccc.cod_cons)
    WHERE mb.oid_movi_banc = v_ccc.oid_movi_banc
      AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

   END IF;

  END LOOP;

  UPDATE ccc_movim_banca_valid mb
  SET mb.clie_oid_clie = ( SELECT mc.oid_clie
                           FROM mae_clien mc
                           WHERE mc.cod_clie = mb.cod_cons)
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Actualizando el Oid Clie a ' || SQL%ROWCOUNT || ' pagos bancarios';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_banc_agri,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_banc_agri,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_banc_agri,lv_id_proc_ejec, 2);

 END int_pr_ccc_valid_banco_agric;

 PROCEDURE int_pr_ccc_valid_docum_ident(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;

  lv_cod_erro                      VARCHAR2(250);
  lv_long_docu_iden                NUMBER;

 BEGIN

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,p_cod_usua,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio de la Validacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,lv_id_proc_ejec,gv_des_log);

  -- Actualizando la Cedula de Identidad
  lv_long_docu_iden := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('LongitudDocumentoIdentidad');

  IF p_cod_cuen_corr_banc = 'T' THEN
   lv_oid_ccba := 999;
  ELSE
   lv_oid_ccba := ccc_pkg_gener.CCC_FN_OBTIE_OID_BANCO_CCBAN(p_cod_cuen_corr_banc);
  END IF;

  UPDATE ccc_movim_banca_valid mb
  SET mb.num_docu_iden = substr(mb.num_docu_iden,length(mb.num_docu_iden) - lv_long_docu_iden + 1,lv_long_docu_iden)
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  -- Obteniendo el codigo de consultora para las cedulas
  UPDATE ccc_movim_banca_valid mb
  SET
   mb.cod_cons = ( SELECT mc.cod_clie
                   FROM
                    mae_clien_ident mci,
                    mae_clien mc
                   WHERE mc.oid_clie = mci.clie_oid_clie
                     AND mci.num_docu_iden = mb.num_docu_iden),
   mb.cod_iden_proc = 'T'
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba)
    AND EXISTS (
            SELECT 1
            FROM mae_clien_ident mci
            WHERE mci.num_docu_iden = mb.num_docu_iden);


  -- Marcando los Erroneos --
  UPDATE ccc_movim_banca_valid mb
  SET
   mb.cod_erro = p_cod_tipo_erro,
   mb.cod_iden_proc = p_cod_iden_proc,
   mb.val_esta_movi_pend = p_cod_esta_movi
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba)
    AND NOT EXISTS (
            SELECT 1
            FROM mae_clien_ident mci
            WHERE mci.num_docu_iden = mb.num_docu_iden);

  gv_des_log:='Se encontraron ' || SQL%ROWCOUNT || ' pagos bancarios incorrectos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,lv_id_proc_ejec,gv_des_log);

  UPDATE ccc_movim_banca_valid mb
  SET mb.clie_oid_clie = ( SELECT mc.oid_clie
                           FROM mae_clien mc
                           WHERE mc.cod_clie = mb.cod_cons)
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba);

  gv_des_log:='Actualizando el Oid Clie a ' || SQL%ROWCOUNT || ' pagos bancarios';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_docu_iden,lv_id_proc_ejec, 2);

 END int_pr_ccc_valid_docum_ident;

 PROCEDURE int_pr_ccc_valid_tipol_consu(
  p_cod_cuen_corr_banc             IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_ccba                      ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;

  lv_cod_erro                      VARCHAR2(250);

 BEGIN

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_tipo_clie,p_cod_usua,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio de la Validacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_tipo_clie,lv_id_proc_ejec,gv_des_log);

  IF p_cod_cuen_corr_banc = 'T' THEN
   lv_oid_ccba := 999;
  ELSE
   lv_oid_ccba := ccc_pkg_gener.CCC_FN_OBTIE_OID_BANCO_CCBAN(p_cod_cuen_corr_banc);
  END IF;

  UPDATE ccc_movim_banca_valid mb
  SET
   mb.cod_erro = p_cod_tipo_erro,
   mb.cod_iden_proc = p_cod_iden_proc,
   mb.val_esta_movi_pend = p_cod_esta_movi
  WHERE mb.cod_erro IS NULL
    AND mb.ccba_oid_cc_banc = DECODE(lv_oid_ccba,999,mb.ccba_oid_cc_banc,lv_oid_ccba)
    AND EXISTS (
               SELECT NULL
               FROM
                ccc_movim_banca_valid ba,
                mae_clien_tipo_subti mcts
               WHERE ba.clie_oid_clie = mcts.clie_oid_clie
                 AND mcts.ticl_oid_tipo_clie NOT IN (2,4)
                 AND ba.oid_movi_banc = mb.oid_movi_banc);

  gv_des_log:='Se encontraron ' || SQL%ROWCOUNT || ' pagos bancarios incorrectos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_tipo_clie,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_tipo_clie,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,gc_cod_modu,gc_cod_proc_vali_tipo_clie,lv_id_proc_ejec, 2);

 END int_pr_ccc_valid_tipol_consu;

 PROCEDURE int_pr_ccc_valid_banca_indiv(
  p_cod_cuen_banc                  IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_val_nom_prog                   IN   ccc_param_valid_lotes_banca.val_nomb_prog%TYPE,
  p_cod_iden_proc                  IN   ccc_param_valid_lotes_banca.cod_iden_proc%TYPE,
  p_cod_esta_movi                  IN   ccc_param_valid_lotes_banca.cod_esta_movi%TYPE,
  p_cod_tipo_erro                  IN   ccc_param_valid_lotes_banca.cod_tipo_erro%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_sql            VARCHAR2(250);

 BEGIN

  lv_sql:='BEGIN ' || p_val_nom_prog || '(:1,:2,:3,:4,:5); END;';
  EXECUTE IMMEDIATE lv_sql
  USING IN p_cod_cuen_banc,IN p_cod_iden_proc, IN p_cod_esta_movi,IN p_cod_tipo_erro,IN p_cod_usua;

 END int_pr_ccc_valid_banca_indiv;

 PROCEDURE int_pr_ccc_valid_lotes_banca(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

 CURSOR c_vali
 IS
  SELECT
   vb.val_nomb_prog,
   vb.cod_iden_proc,
   vb.cod_esta_movi,
   vb.cod_tipo_erro,
   vb.cod_cuen_corr_banc
  FROM ccc_param_valid_lotes_banca vb
  WHERE vb.ind_acti = 1
  ORDER BY vb.num_orde_vali ASC;

 BEGIN

  FOR v_vali IN c_vali LOOP

   int_pr_ccc_valid_banca_indiv(v_vali.cod_cuen_corr_banc,v_vali.val_nomb_prog,v_vali.cod_iden_proc,v_vali.cod_esta_movi,v_vali.cod_tipo_erro, p_cod_usua);

  END LOOP;

 END int_pr_ccc_valid_lotes_banca;

 PROCEDURE int_pr_ccc_envio_email_liqui(
  p_num_lote                       IN  fin_inter_ejecu.num_lote%TYPE)
 IS

  lv_mail_conn                     utl_smtp.connection;
  lv_linea                         VARCHAR2(400);
  lv_nomb_usua                     ccc_envio_email_liqui.val_nomb_usua%TYPE;
  lv_val_emai                      ccc_envio_email_liqui.val_email%TYPE;

  CURSOR c_lote_liqu
  IS
  SELECT
   ccb.des_cc banco,
   br.num_lote,
   el.val_desc estado,
   br.val_cant_regi_tota,
   br.val_cant_regi_ok,
   br.val_cant_regi_erro
  FROM
   ccc_numer_lote_banca_recep br,
   ccc_cuent_corri_banca ccb,
   ccc_estad_lote_banca el
  WHERE br.cod_cuen_corr_banc = ccb.cod_cc
   AND br.cod_iden_proc = el.cod_esta
   AND br.num_lote_mult = p_num_lote
   ORDER BY br.fec_inic_liqu ASC;

 BEGIN

  SELECT ee.val_nomb_usua, ee.val_email
  INTO lv_nomb_usua,lv_val_emai
  FROM ccc_envio_email_liqui ee;

  lv_mail_conn := ssicc_comun.log_email.begin_mail('andrea.florencio@belcorp.biz',
                lv_val_emai,' SSICC Liquidacion de Lotes Bancarios','text/html' );

   ssicc_comun.log_email.write_text(lv_mail_conn,'<html><body>');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">');
   ssicc_comun.log_email.write_text(lv_mail_conn, '<tr><font size="5"><b>');
   ssicc_comun.log_email.write_text(lv_mail_conn, 'Estimada ' || lv_nomb_usua  );
   ssicc_comun.log_email.write_text(lv_mail_conn, '</b></font></tr>');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<tr><td style="height: 75px; width: 75px;"><font color="#4188b2" face="Arial" size="4"><b>');
   ssicc_comun.log_email.write_text(lv_mail_conn, '   Se han procesado los siguientes lotes bancarios : ' );
   ssicc_comun.log_email.write_text(lv_mail_conn,'</b></font></td></tr></table>');
--   ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
--   ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<tr bgcolor="#AB82FF" width="80%"><td width="25%"><font face="Arial" size="2" color="white"><b><center>BANCO</center></b></font></td><td width="10%"><font face="Arial" size="2" color="white"><b><center>LOTE</center></b></font></td><td width="10%"><font face="Arial" size="2" color="white"><b><center>ESTADO</center></b></font></td><td width="20%"><font face="Arial" size="2" color="white"><b><center>TOTAL PAGOS</center></b></font></td><td width="20%"><font face="Arial" size="2" color="white"><b><center>PAGOS OK</center></b></font></td><td width="20%"><font face="Arial" size="2" color="white"><b><center>PAGOS CON ERROR</center></b></font></td></tr>');

   FOR v_lote_liqu IN c_lote_liqu LOOP

    ssicc_comun.log_email.write_text(lv_mail_conn,'<tr>');

    IF v_lote_liqu.banco IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.banco)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    IF v_lote_liqu.num_lote IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.num_lote)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    IF v_lote_liqu.estado IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.estado)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    IF v_lote_liqu.val_cant_regi_tota IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.val_cant_regi_tota)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    IF v_lote_liqu.val_cant_regi_ok IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.val_cant_regi_ok)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    IF v_lote_liqu.val_cant_regi_erro IS NOT NULL THEN
     lv_linea := '<td ><font face="Arial" size="2"><center>'||TRIM(v_lote_liqu.val_cant_regi_erro)||'</center></font></td>';
     ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
    END IF;

    ssicc_comun.log_email.write_text(lv_mail_conn,'</tr>');

   END LOOP;

   ssicc_comun.log_email.write_text(lv_mail_conn,'</table>');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
   ssicc_comun.log_email.write_text(lv_mail_conn,'<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>');
   ssicc_comun.log_email.end_mail( lv_mail_conn);

  EXCEPTION
   WHEN OTHERS THEN
    NULL;

 END int_pr_ccc_envio_email_liqui;

 PROCEDURE INT_PR_CCC_JOB_CARGA_BANCA
 IS

  lv_num_lote                      ccc_movim_banca.num_lote%TYPE;

 BEGIN

  INT_PR_CCC_CARGA_LOTES_BANCA(USER,lv_num_lote);

 END INT_PR_CCC_JOB_CARGA_BANCA;

 PROCEDURE INT_PR_CCC_CARGA_LOTES_BANCA(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_pais                      seg_pais.oid_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;
  lv_oid_soci                      seg_socie.oid_soci%TYPE;
  lv_oid_subp_crea_banc            ccc_subpr.oid_subp%TYPE;
  lv_oid_tcab_crea                 ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
  lv_num_lote_cheq                 ccc_movim_banca.num_lote%TYPE;
  lv_acti_cheq                     ccc_param_gener.val_para%TYPE;
  lv_ind_vali_banc                 ccc_param_gener.val_para%TYPE;
  lv_ind_actu_modu_cobr            ccc_param_gener.val_para%TYPE;
  lv_cod_iden_proc_rece            ccc_movim_banca.cod_iden_proc%TYPE;
  lv_ind_envi_emai                 ccc_param_gener.val_para%TYPE;
  lv_cant_pago_banc                NUMBER(12);
  lv_cod_erro                      VARCHAR2(4000);

 CURSOR c_lote_proc
 IS
  SELECT
   ccb.cod_cc,
   ccb.des_cc,
   mb.num_lote,
   ccb.ind_liqu_auto_inte
  FROM
   ccc_movim_banca_valid mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
  GROUP BY ccb.cod_cc,ccb.des_cc,mb.num_lote,ccb.ind_liqu_auto_inte;

 BEGIN

  DELETE FROM ccc_movim_banca_valid;

  fin_pkg_inter.FIN_PR_GENER_INTER_ENTRA_MAGIC(gc_cod_modu,gc_cod_inte_ban1,p_cod_usua,p_num_lote,lv_cod_erro);

  SELECT COUNT(*)
  INTO lv_cant_pago_banc
  FROM int_ccc_datos_ban1 c;

  IF NVL(lv_cant_pago_banc,0) = 0 THEN
   RAISE e_no_exis_pago_banc;
  END IF;

  lv_ind_vali_banc := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('ActivacionValidacioneBancariasV2');
  lv_ind_actu_modu_cobr := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndActualizaModuloCobranzas');
  lv_acti_cheq := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('ActivarInterfazCheques');

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_oid_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(lv_cod_pais);

  lv_cod_soci := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
  lv_oid_soci := gen_pkg_gener.gen_fn_devuelve_id_socie(lv_cod_soci);

  -- Subproceso de Creacion
  SELECT cs.oid_subp
  INTO lv_oid_subp_crea_banc
  FROM
   ccc_proce cp,
   ccc_subpr cs
  WHERE cp.oid_proc = cs.ccpr_oid_proc
    AND cp.cod_proc = gc_cod_proc_reca_banc
    AND cs.cod_subp = gc_cod_subp_reca_banc_auto;

  -- Tipo Cargo Abono de Creacion
  SELECT tas.tcab_oid_tcab
  INTO lv_oid_tcab_crea
  FROM ccc_tipo_abono_subpr tas
  WHERE tas.subp_oid_subp = lv_oid_subp_crea_banc;

  INSERT INTO ccc_movim_banca_valid
   SELECT
    ccc_cmba_seq.nextval,          -- oid_movi_banc  number(12)
    lv_oid_soci,                   -- soci_oid_soci  number(12)
    lv_oid_subp_crea_banc,         -- subp_oid_marc_ulti  number(12)
    lv_oid_subp_crea_banc,         -- subp_oid_marc_crea  number(12)
    lv_oid_tcab_crea,              -- tcab_oid_abon_ulti  number(12)
    lv_oid_tcab_crea,              -- tcab_oid_abon_crea  number(12)
    ctt.oid_tipo_tran,             -- ttra_oid_tipo_trans  number(12)
    ccb.oid_cuen_corr_banc,        -- ccba_oid_cc_banc  number(12)
    ban.num_cons_tran,             -- num_cons_tran  number(10)
    ban.num_lote_inte,             -- num_lote  number(12)
    ban.cod_clie,                  -- cod_cons  varchar2(15)
    NULL,                          -- cod_erro  varchar2(2)
    ban.val_digi_cheq_fact,        -- val_digi_cheq_fact  number(2)
    NULL,                          -- val_docu_apli_anio  varchar2(2)
    NULL,                          -- val_docu_apli_mes_seri  varchar2(2)
    NULL,                          -- val_docu_apli_nume  number(8)
    NULL,                          -- val_docu_crea_anio  varchar2(2)
    NULL,                          -- val_docu_crea_mes_seri  varchar2(2)
    NULL,                          -- val_docu_crea_nume  number(8)
    NULL,                          -- val_esta_movi_pend  varchar2(1)
    NULL,                          -- fec_cont  date
    ban.fec_pago,                  -- fec_pago  date
    TRUNC(SYSDATE),                -- fec_proc  date
    SYSDATE,                       -- val_hora_proc  date
    NULL,                          -- val_hora_norm_adic  varchar2(1)
    gc_cod_iden_proc_tran,         -- cod_iden_proc  varchar2(1)
    ban.val_pago,                  -- imp_pago  number(12,2)
    ban.val_nomb_ofic,             -- val_nomb_ofic  varchar2(30)
    ban.val_nume_cupo,             -- num_cupo  number(12)
    ban.val_nume_docu,             -- val_nume_docu  varchar2(15)
    ban.val_nume_fact,             -- num_fact_bole  number(10)
    null,                          -- val_nume_lote_cont  varchar2(20)
    'CARGA POR INTERFACE',         -- val_obse  varchar2(100)
    ban.val_ofic_reca,             -- cod_ofic_reca  varchar2(5)
    p_cod_usua,                    -- val_usua_proc  varchar2(20)
    NULL,                          -- fec_movi_apli  date
    NULL,                          -- val_impo_movi  number(12,2)
    p_cod_usua,                    -- cod_usua  varchar2(20)
    ban.num_lote_exte,             -- num_lote_exte  number(12)
    0,                             -- num_hist  number(4)
    0,                             -- imp_apli  number(12,2)
    ban.val_pago,                  -- imp_sald_pend  number(12,2)
    lv_oid_pais,                   -- pais_oid_pais  number(12)
    NULL,                          -- sbac_oid_sbac  number(12)
    NULL,                          -- tier_oid_erro  number(12)
    NULL,                          -- clie_oid_clie  number(12)
    NULL,                          -- mcac_oid_cabe  number(12)
    p_cod_usua,                    -- cod_usua_rece  varchar2(25)
    SYSDATE,                       -- val_hora_rece  date
    NULL,                          -- cod_usua_liqu  varchar2(25)
    NULL,                          -- val_hora_liqu  date
    NULL,                          -- cod_usua_regu  varchar2(25)
    NULL,                          -- val_hora_regu  date
    NULL,                          -- cod_usu_carg_exce  varchar2(25)
    NULL,                          -- val_hora_carg_exce  date
    0,                              -- ind_pago_regu  number(1),
    NULL,                             -- val_digi_ctrl
    ban.num_docu_iden,                -- num_docu_iden
    NULL,                             -- oid_banc_sucu_cheq
    NULL,                            -- ind_tipo_cheq
    NULL,                             -- fec_cobr_cheq
    NULL,
    NULL,
    NULL,                              --cod_tipo_docu_homo
    0,
    NULL,
    NULL,
    0,                             -- ind_pago_inco
    0,                            -- ind_pago_exce
    0,                             --ind_exce_vali
    0,                             --imp_apli_clie NUMBER(15,2)
    0,                             -- imp_apli_exce NUMBER(15,2)
    0,                             --imp_apli_inco NUMBER(15,2)
    0,                             --imp_apli_cobr_exte NUMBER(15,2)
    0,                             --imp_apli_cobr_pend_regu NUMBER(15,2));
    0,                              -- imp_apli_fami_prot
    0,                               -- ind_elim
    0                                -- ind_pago_reve
  FROM
   int_ccc_datos_ban1 ban,
   ccc_cuent_corri_banca ccb,
   ccc_tipo_trans ctt
  WHERE ban.val_codi_banc = ccb.cod_cc(+)
  AND ban.val_tipo_trans = ctt.cod_tipo_tran(+);

  IF lv_ind_vali_banc = 'S' THEN
   CCC_PKG_PROCE.CCC_PR_VALID_LOTES_BANCA(gc_cod_tipo_orig_inte,p_cod_usua);
  ELSE
   INT_PR_CCC_VALID_LOTES_BANCA(p_cod_usua);
  END IF;

  INSERT INTO ccc_movim_banca
   SELECT * FROM ccc_movim_banca_valid;

  -- Registrando Lote Bancario
  FOR v_lote_proc IN c_lote_proc LOOP

   CCC_PKG_GENER.CCC_PR_GENER_CABEC_LOTE_BANCA(v_lote_proc.cod_cc, gc_cod_tipo_orig_inte,v_lote_proc.num_lote,p_num_lote, p_cod_usua);

   -- Actualizando las referencias --
   UPDATE fin_inter_ejecu f
   SET f.val_refe = v_lote_proc.des_cc
   WHERE f.num_lote = v_lote_proc.num_lote;

   SELECT lr.cod_iden_proc
   INTO lv_cod_iden_proc_rece
   FROM ccc_numer_lote_banca_recep lr
   WHERE lr.num_lote = v_lote_proc.num_lote;

   IF v_lote_proc.ind_liqu_auto_inte = 1 AND lv_cod_iden_proc_rece = gc_cod_iden_proc_tran THEN

    CCC_PKG_PROCE.CCC_PR_LIQUI_LOTE_BANCA(v_lote_proc.num_lote,p_cod_usua);

   END IF;

  END LOOP;

  COMMIT;

  lv_ind_envi_emai := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('IndicadorEnvioEmailLiquidacion');

  IF lv_ind_envi_emai = 'S' THEN
   INT_PR_CCC_ENVIO_EMAIL_LIQUI(p_num_lote);
  END IF;

  IF lv_ind_actu_modu_cobr = '1' THEN
   COB_PKG_PROCE.COB_PR_ACTUA_CARTE(lv_cod_pais,lv_cod_soci,p_cod_usua);
  END IF;

  IF lv_acti_cheq = 1 THEN

   BEGIN
    INT_PR_CCC_CARGA_CHEQU_BANCA(p_cod_usua,lv_num_lote_cheq);

    UPDATE fin_inter_ejecu f
    SET f.num_mult_lote = p_num_lote
    WHERE f.num_lote = lv_num_lote_cheq;

   EXCEPTION
    WHEN OTHERS THEN
     NULL;

   END;

  END IF;

 EXCEPTION

  WHEN e_no_exis_pago_banc THEN
   NULL;

 END INT_PR_CCC_CARGA_LOTES_BANCA;

 PROCEDURE INT_PR_CCC_PARSE_ARCHI_BANCA
 IS

  lv_arch                          utl_file.file_type;
  lv_line                          VARCHAR2(4000);
  lv_cant_arch                     NUMBER(3);
  lv_cont_line                     NUMBER(12):=0;

 CURSOR c_para_arch
 IS
  SELECT
   p.cod_cc,
   p.val_dire_entr,
   p.val_iden_cons_inic,
   p.val_iden_cons_fina,
   p.val_iden_cons_fina - p.val_iden_cons_inic + 1 lon_iden_cons,
   p.val_fech_pago_inic,
   p.val_fech_pago_fina - p.val_fech_pago_inic + 1 lon_fech_pago,
   p.val_form_fech_pago,
   p.val_impo_pago_inic,
   p.val_impo_pago_fina - p.val_impo_pago_inic + 1 lon_impo_pago
  FROM ccc_param_inter_archi_banca p;

 CURSOR c_list_arch
 IS
  SELECT nom_arch
  FROM fin_tempo_lista_direc
  ORDER BY nom_arch ASC;

 BEGIN

  FOR v_para_arch IN c_para_arch LOOP

   FIN_PKG_INTER.FIN_PR_LISTA_ARCHI_DIREC(v_para_arch.val_dire_entr);

   SELECT COUNT(*)
   INTO lv_cant_arch
   FROM fin_tempo_lista_direc;

   /*
   IF lv_cant_arch = 0 THEN
    RAISE e_no_exis_arch;
   END IF;
   */

   lv_cont_line := 0;
   FOR v_list_arch IN c_list_arch LOOP

    -- Cargar Archivo en Tabla Temporal --
    lv_arch := utl_file.fopen(v_para_arch.val_dire_entr, v_list_arch.nom_arch, 'r' );

   LOOP

    BEGIN
     utl_file.get_line( lv_arch, lv_line );
     lv_cont_line := lv_cont_line + 1;

     INSERT INTO ccc_tempo_parse_archi_banca VALUES (lv_cont_line,lv_line);

    EXCEPTION
     WHEN NO_DATA_FOUND then
      EXIT;
    END;

    END LOOP;

    --Empieza el parseo segun parametria
    INSERT INTO ccc_tempo_carga_archi_banca
     SELECT
      num_fila,--num_fila  number(8)
      v_para_arch.cod_cc, --banc_cod_banc  varchar2(6)
      substr(val_camp,v_para_arch.val_iden_cons_inic,  v_para_arch.lon_iden_cons), --cod_clie  varchar2(15)
      substr(val_camp,v_para_arch.val_fech_pago_inic,  v_para_arch.lon_fech_pago), --fec_pago  date
      substr(val_camp,v_para_arch.val_impo_pago_inic,  v_para_arch.lon_impo_pago),--imp_pago  number(15,2)
      TRUNC(SYSDATE)--fec_proc  date
     FROM ccc_tempo_parse_archi_banca;

   END LOOP;

  END LOOP;

 END INT_PR_CCC_PARSE_ARCHI_BANCA;

 PROCEDURE INT_PR_CCC_CARGA_CHEQU_BANCA(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE)
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_oid_pais                      seg_pais.oid_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;
  lv_oid_soci                      seg_socie.oid_soci%TYPE;
  lv_oid_subp_crea_cheq            ccc_subpr.oid_subp%TYPE;
  lv_oid_tcab_crea_cheq            ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
  lv_oid_tipo_tran                 ccc_tipo_trans.oid_tipo_tran%TYPE;
  lv_oid_cuen_corr_cheq            ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;

 CURSOR c_lote_proc
 IS
  SELECT
   ccb.cod_cc,
   ccb.des_cc,
   mb.num_lote,
   ccb.ind_liqu_auto_inte
  FROM
   ccc_movim_banca_valid mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
  GROUP BY ccb.cod_cc,ccb.des_cc,mb.num_lote,ccb.ind_liqu_auto_inte;

  lv_cod_erro VARCHAR2(4000);

 BEGIN

  DELETE FROM ccc_carga_inter_chequ_banca;

  DELETE FROM ccc_movim_banca_valid;

  fin_pkg_inter.FIN_PR_GENER_INTER_ENTRA_MAGIC(gc_cod_modu,gc_cod_inte_ban7,p_cod_usua,p_num_lote,lv_cod_erro);

  lv_cod_pais := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_oid_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(lv_cod_pais);

  lv_cod_soci := ccc_pkg_gener.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
  lv_oid_soci := gen_pkg_gener.gen_fn_devuelve_id_socie(lv_cod_soci);

  -- Subproceso de Creacion
  SELECT cs.oid_subp
  INTO lv_oid_subp_crea_cheq
  FROM
   ccc_proce cp,
   ccc_subpr cs
  WHERE cp.oid_proc = cs.ccpr_oid_proc
    AND cp.cod_proc = gc_cod_proc_reca_banc
    AND cs.cod_subp = gc_cod_subp_reca_banc_cheq;

  -- Tipo Cargo Abono de Creacion
  SELECT tas.tcab_oid_tcab
  INTO lv_oid_tcab_crea_cheq
  FROM ccc_tipo_abono_subpr tas
  WHERE tas.subp_oid_subp = lv_oid_subp_crea_cheq;

  --Tipo de Transacci?n
  SELECT  ctt.oid_tipo_tran
  INTO lv_oid_tipo_tran
  FROM ccc_tipo_trans ctt
  WHERE ctt.cod_tipo_tran = gc_cod_tipo_tran_cheq;

  -- Oid Cuenta Corriente Bancaria Cheque
  SELECT ccb.oid_cuen_corr_banc
  INTO lv_oid_cuen_corr_cheq
  FROM ccc_cuent_corri_banca ccb
  WHERE ccb.cod_cc = gc_cod_cuen_corr_banc_cheq;

  -- Identificando el tipo de cuenta corriente bancaria
  INSERT INTO ccc_movim_banca_valid
   SELECT
    ccc_cmba_seq.nextval,          -- oid_movi_banc  number(12)
    lv_oid_soci,                   -- soci_oid_soci  number(12)
    lv_oid_subp_crea_cheq,         -- subp_oid_marc_ulti  number(12)
    lv_oid_subp_crea_cheq,         -- subp_oid_marc_crea  number(12)
    lv_oid_tcab_crea_cheq,              -- tcab_oid_abon_ulti  number(12)
    lv_oid_tcab_crea_cheq,              -- tcab_oid_abon_crea  number(12)
    lv_oid_tipo_tran,             -- ttra_oid_tipo_trans  number(12)
    lv_oid_cuen_corr_cheq,        -- ccba_oid_cc_banc  number(12)
    ROWNUM,                       -- num_cons_tran  number(10)
    ban.num_lote,                 -- num_lote  number(12)
    ban.cod_clie,                  -- cod_cons  varchar2(15)
    NULL,                          -- cod_erro  varchar2(2)
    NULL,                          -- val_digi_cheq_fact  number(2)
    NULL,                          -- val_docu_apli_anio  varchar2(2)
    NULL,                          -- val_docu_apli_mes_seri  varchar2(2)
    NULL,                          -- val_docu_apli_nume  number(8)
    NULL,                          -- val_docu_crea_anio  varchar2(2)
    NULL,                          -- val_docu_crea_mes_seri  varchar2(2)
    NULL,                          -- val_docu_crea_nume  number(8)
    NULL,                          -- val_esta_movi_pend  varchar2(1)
    NULL,                          -- fec_cont  date
    TRUNC(ban.fec_crea),            -- fec_pago  date
    TRUNC(SYSDATE),                -- fec_proc  date
    SYSDATE,                       -- val_hora_proc  date
    NULL,                          -- val_hora_norm_adic  varchar2(1)
    gc_cod_iden_proc_tran,         -- cod_iden_proc  varchar2(1)
    ban.imp_cheq,                  -- imp_pago  number(12,2)
    NULL,                          -- val_nomb_ofic  varchar2(30)
    NULL,                          -- num_cupo  number(12)
    NULL,                          -- val_nume_docu  varchar2(15)
    NULL,                          -- num_fact_bole  number(10)
    null,                          -- val_nume_lote_cont  varchar2(20)
    'CARGA POR INTERFACE DE CHEQUES',         -- val_obse  varchar2(100)
    NULL,                          -- cod_ofic_reca  varchar2(5)
    p_cod_usua,                    -- val_usua_proc  varchar2(20)
    NULL,                          -- fec_movi_apli  date
    NULL,                          -- val_impo_movi  number(12,2)
    p_cod_usua,                    -- cod_usua  varchar2(20)
    NULL,                          -- num_lote_exte  number(12)
    0,                             -- num_hist  number(4)
    0,                             -- imp_apli  number(12,2)
    ban.imp_cheq,                  -- imp_sald_pend  number(12,2)
    lv_oid_pais,                   -- pais_oid_pais  number(12)
    NULL,                          -- sbac_oid_sbac  number(12)
    NULL,                          -- tier_oid_erro  number(12)
    NULL,                          -- clie_oid_clie  number(12)
    NULL,                          -- mcac_oid_cabe  number(12)
    p_cod_usua,                    -- cod_usua_rece  varchar2(25)
    SYSDATE,                       -- val_hora_rece  date
    NULL,                          -- cod_usua_liqu  varchar2(25)
    NULL,                          -- val_hora_liqu  date
    NULL,                          -- cod_usua_regu  varchar2(25)
    NULL,                          -- val_hora_regu  date
    NULL,                          -- cod_usu_carg_exce  varchar2(25)
    NULL,                          -- val_hora_carg_exce  date
    0,                              -- ind_pago_regu  number(1),
    NULL,                           -- val_digi_ctrl
    NULL,                           -- num_docu_iden
    NULL,                            -- oid_banc_sucu_cheq
    NULL,                            -- ind_tipo_cheq
    ban.fec_cobr,                   -- fec_cobr_cheq,
    bc.cod_banc,                    -- cod_banc_cheq,
    bc.cod_sucu_prin,                --cod_sucu_cheq
    NULL,                              --cod_tipo_docu_homo
    0,
    NULL,
    NULL,
    0,                             -- ind_pago_inco
    0,                            -- ind_pago_exce
    0,                             --ind_exce_vali
    0,                             --imp_apli_clie NUMBER(15,2)
    0,                             -- imp_apli_exce NUMBER(15,2)
    0,                             --imp_apli_inco NUMBER(15,2)
    0,                             --imp_apli_cobr_exte NUMBER(15,2)
    0,                             --imp_apli_cobr_pend_regu NUMBER(15,2));
    0,                              -- imp_apli_fami_prot
    0,                               -- ind_elim
    0                                -- ind_pago_reve
  FROM
   ccc_carga_inter_chequ_banca ban,
   ccc_banco_chequ bc
  WHERE ban.cod_banc = bc.cod_banc;

  INT_PR_CCC_VALID_LOTES_BANCA(p_cod_usua);

  INSERT INTO ccc_movim_banca
   SELECT * FROM ccc_movim_banca_valid;

  -- Registrando Lote Bancario
  FOR v_lote_proc IN c_lote_proc LOOP

   CCC_PKG_GENER.CCC_PR_GENER_CABEC_LOTE_BANCA(v_lote_proc.cod_cc, gc_cod_tipo_orig_inte,v_lote_proc.num_lote,p_num_lote, p_cod_usua);

   -- Actualizando las referencias --
   UPDATE fin_inter_ejecu f
   SET f.val_refe = v_lote_proc.des_cc
   WHERE f.num_lote = v_lote_proc.num_lote;

   IF  v_lote_proc.ind_liqu_auto_inte = 1  THEN

    CCC_PKG_PROCE.CCC_PR_LIQUI_LOTE_BANCA(v_lote_proc.num_lote,p_cod_usua);

   END IF;

  END LOOP;

 EXCEPTION
  WHEN OTHERS THEN
    gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
    raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );


 END INT_PR_CCC_CARGA_CHEQU_BANCA;

 PROCEDURE INT_PR_CCC_GENER_INFOR_RECAU(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_cod_usua                      seg_usuar.use_usua%TYPE;
  lv_ind_envi_ban4                 ccc_param_gener.val_para%TYPE;
  lv_ind_envi_ban5                 ccc_param_gener.val_para%TYPE;
  lv_ind_envi_ban6                 ccc_param_gener.val_para%TYPE;
  lv_des_erro                      VARCHAR2(4000);

  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_inte_ban2;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Informacion Recaudadoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);


  IF p_cod_usua IS NULL THEN
   lv_cod_usua := USER;
  ELSE
   lv_cod_usua := p_cod_usua;
  END IF;

  lv_ind_envi_ban4 := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorEnvioBAN-4');
  lv_ind_envi_ban5 := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorEnvioBAN-5');
  lv_ind_envi_ban6 := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorEnvioBAN-6');

  IF lv_ind_envi_ban4 = 'S' THEN

   gv_des_log:='   Generando la Informacion BAN-4';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_CCC_GENER_INFOR_BAN4(lv_cod_usua);

  END IF;

  IF lv_ind_envi_ban5 = 'S' THEN

   gv_des_log:='   Generando la Informacion BAN-5';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_CCC_GENER_INFOR_BAN5(lv_cod_usua);

  END IF;

   IF lv_ind_envi_ban6 = 'S' THEN

   gv_des_log:='   Generando la Informacion BAN-6';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_BAN6(lv_cod_usua);

  END IF;

  gv_des_log:='Enviando email de confirmacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN
   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_ban2,1);
 EXCEPTION
  WHEN OTHERS THEN
      gv_des_log:='*** Errores con el envio del email de confirmaci¿n';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END;

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN OTHERS THEN

   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);

   lv_des_erro := ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' ||
                   gv_reco_trac.line_number ||
                             ' en el programa ' ||
                   gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_ban2,9,lv_des_erro);

   gv_des_log:='!!!ERROR ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='El proceso ha terminado con errores.';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

 END INT_PR_CCC_GENER_INFOR_RECAU;

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN4(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_ind_util_cedu                 ccc_param_gener.val_para%TYPE;

 BEGIN

  lv_ind_util_cedu := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorUtilizacionCedulaBAN2');

  DELETE FROM ccc_int_gener_saldo_consu;

  IF lv_ind_util_cedu = 'S' THEN

   INSERT INTO ccc_int_gener_saldo_consu
    SELECT
     mci.num_docu_iden cod_clie,
     '' cod_digi_ctrl,
     mtd.cod_tipo_docu,
     mci.num_docu_iden,
     '',
     NVL(mc.val_ape1,' ') val_ape1,
     NVL(mc.val_ape2,' ') val_Ape2,
     NVL(mc.val_nom1,' ') val_nom1,
     NVL(mc.val_nom2,' ') val_nom2,
     NULL,
     NULL,
     NULL,
     NULL,
     NULL,
     TRIM(REPLACE(REPLACE(TO_CHAR(mc.sal_deud_ante,'000000000000000D99'),'.'),','))
    FROM
     mae_clien mc,
     mae_clien_ident mci,
     mae_tipo_docum mtd
    WHERE mc.oid_clie=mci.clie_oid_clie
      AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
      AND mc.sal_deud_ante > 0
      AND mci.val_iden_docu_prin=1;

  ELSE

   INSERT INTO ccc_int_gener_saldo_consu
        SELECT
           mc.cod_clie,
           NVL(mc.cod_digi_ctrl,' ') cod_digi_ctrl  ,
           mtd.cod_tipo_docu,
           mci.num_docu_iden,
           '',
           NVL(mc.val_ape1,' ') val_ape1,
           NVL(mc.val_ape2,' ') val_Ape2,
           NVL(mc.val_nom1,' ') val_nom1,
           NVL(mc.val_nom2,' ') val_nom2,
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
           TRIM(REPLACE(TO_CHAR(mc.sal_deud_ante,'0000000000000000D99'),'.'))
        FROM
           mae_clien mc,
           mae_clien_ident mci,
           mae_tipo_docum mtd
        WHERE mc.oid_clie=mci.clie_oid_clie
        AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
        AND mci.val_iden_docu_prin=1
        AND mc.sal_deud_ante > 0;

  END IF;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_gene_info_ban4,lv_num_lote,p_cod_usua);


 END INT_PR_CCC_GENER_INFOR_BAN4;

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN5(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_ind_util_cedu                 ccc_param_gener.val_para%TYPE;
  --lv_cod_soci                      ccc_param_gener.val_para%TYPE;

 BEGIN

 lv_ind_util_cedu := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorUtilizacionCedulaBAN2');
 --lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  DELETE FROM ccc_int_gener_saldo_consu;

  IF lv_ind_util_cedu = 'S' THEN

   INSERT INTO ccc_int_gener_saldo_consu
    SELECT
           mci.num_docu_iden cod_clie,
      '' cod_digi_ctrl,
           mtd.cod_tipo_docu,
           mci.num_docu_iden,
      '',
           NVL(mc.val_ape1,' ') val_ape1,
           NVL(mc.val_ape2,' ') val_Ape2,
           NVL(mc.val_nom1,' ') val_nom1,
           NVL(mc.val_nom2,' ') val_nom2,
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
      TRIM(REPLACE(REPLACE(TO_CHAR(mc.sal_deud_ante,'000000000000000D99'),'.'),','))
        FROM
           mae_clien mc,
           mae_clien_ident mci,
      mae_tipo_docum mtd
        WHERE mc.oid_clie=mci.clie_oid_clie
        AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
       AND mc.sal_deud_ante > 0
       AND mci.val_iden_docu_prin=1
       AND NOT EXISTS (
        SELECT NULL
        FROM ccc_consu_casti_cabec ccc
        WHERE ccc.num_docu_iden = mci.num_docu_iden
         AND ccc.ind_acti = 1);

    INSERT INTO ccc_int_gener_saldo_consu
     (SELECT
        t1.num_docu_iden cod_clie,
       NULL cod_digi_ctrl,
        t1.cod_tipo_docu_iden,
        t1.num_docu_iden,
       NULL,
        NVL(t1.val_ape1,' ') val_ape1,
        NVL(t1.val_ape2,' ') val_Ape2,
        NVL(t1.val_nom1,' ') val_nom1,
        NVL(t1.val_nom2,' ') val_nom2,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
        TRIM(REPLACE(REPLACE(TO_CHAR(t1.imp_deud_actu,'000000000000000D99'),'.'),','))
     FROM
       (SELECT ccc.cod_tipo_docu_iden,ccc.num_docu_iden,ccc.val_nom1,ccc.val_nom2,ccc.val_ape1,ccc.val_ape2, SUM(ccc.imp_deud_actu) imp_deud_actu
        FROM ccc_consu_casti_cabec ccc
        WHERE ccc.ind_acti = 1
        -- AND ccc.cod_soci = lv_cod_soci
        HAVING SUM(ccc.imp_deud_actu) > 0
        GROUP BY ccc.cod_tipo_docu_iden,ccc.num_docu_iden,ccc.val_nom1,ccc.val_nom2,ccc.val_ape1,ccc.val_ape2) t1);

  ELSE

   INSERT INTO ccc_int_gener_saldo_consu
    (SELECT
           mc.cod_clie,
      '' cod_digi_ctrl,
           mtd.cod_tipo_docu,
           mci.num_docu_iden,
      '',
           NVL(mc.val_ape1,' ') val_ape1,
           NVL(mc.val_ape2,' ') val_Ape2,
           NVL(mc.val_nom1,' ') val_nom1,
           NVL(mc.val_nom2,' ') val_nom2,
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
      TRIM(REPLACE(TO_CHAR(mc.sal_deud_ante,'000000000000000D99'),'.'))
        FROM
           mae_clien mc,
           mae_clien_ident mci,
      mae_tipo_docum mtd
        WHERE mc.oid_clie = mci.clie_oid_clie
        AND mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
       AND mc.sal_deud_ante > 0
       AND mci.val_iden_docu_prin=1)
     UNION ALL
     (SELECT
       ccc.num_docu_iden cod_clie,
       '' cod_digi_ctrl,
       ccc.cod_tipo_docu_iden,
       ccc.num_docu_iden,
       '',
       NVL(ccc.val_ape1,' ') val_ape1,
       NVL(ccc.val_ape2,' ') val_Ape2,
       NVL(ccc.val_nom1,' ') val_nom1,
       NVL(ccc.val_nom2,' ') val_nom2,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       TRIM(REPLACE(TO_CHAR(ccc.imp_deud_actu,'000000000000000D99'),'.'))
     FROM
      ccc_consu_casti_cabec ccc);

  END IF;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_gene_info_ban5,lv_num_lote,p_cod_usua);

 END INT_PR_CCC_GENER_INFOR_BAN5;

 PROCEDURE INT_PR_CCC_GENER_INFOR_BAN6(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_ind_util_cedu                 ccc_param_gener.val_para%TYPE;

 BEGIN

  lv_ind_util_cedu := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorUtilizacionCedulaBAN2');

  DELETE FROM ccc_int_gener_saldo_consu;

  IF lv_ind_util_cedu = 'S' THEN

   INSERT INTO ccc_int_gener_saldo_consu
    (SELECT
       ccc.num_docu_iden cod_clie,
       '' cod_digi_ctrl,
       ccc.cod_tipo_docu_iden,
       ccc.num_docu_iden,
       '',
       NVL(ccc.val_ape1,' ') val_ape1,
       NVL(ccc.val_ape2,' ') val_Ape2,
       NVL(ccc.val_nom1,' ') val_nom1,
       NVL(ccc.val_nom2,' ') val_nom2,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       TRIM(REPLACE(TO_CHAR(ccc.imp_deud_actu,'000000000000000D99'),'.'))
     FROM
      ccc_consu_casti_cabec ccc
     WHERE ccc.ind_acti = 1);


  ELSE

   INSERT INTO ccc_int_gener_saldo_consu
    (SELECT
       ccc.cod_clie cod_clie,
       '' cod_digi_ctrl,
       ccc.cod_tipo_docu_iden,
       ccc.num_docu_iden,
       '',
       NVL(ccc.val_ape1,' ') val_ape1,
       NVL(ccc.val_ape2,' ') val_Ape2,
       NVL(ccc.val_nom1,' ') val_nom1,
       NVL(ccc.val_nom2,' ') val_nom2,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       TRIM(REPLACE(TO_CHAR(ccc.imp_deud_actu,'000000000000000D99'),'.'))
     FROM
      ccc_consu_casti_cabec ccc
     WHERE ccc.ind_acti = 1);

  END IF;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_gene_info_ban6,lv_num_lote,p_cod_usua);

 END INT_PR_CCC_GENER_INFOR_BAN6;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-2
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 *********************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SALDO_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2)
 IS
 
 CURSOR c_interfaz 
 IS 
 SELECT
  mc.cod_clie,
  NVL(mc.cod_digi_ctrl,' ') cod_digi_ctrl,
  mtd.cod_tipo_docu,
  mci.num_docu_iden,
  NULL,
  NVL(mc.val_ape1,' ') val_ape1,
  NVL(mc.val_ape2,' ') val_Ape2,
  NVL(mc.val_nom1,' ') val_nom1,
  NVL(mc.val_nom2,' ') val_nom2,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  TRIM(REPLACE(REPLACE(TO_CHAR(mc.sal_deud_ante - NVL(mc.val_recl_pend,0),'0000000000000000D99'),'.',''),',',''))
 FROM
  mae_clien mc,
  mae_clien_ident mci,
  mae_tipo_docum mtd         
 WHERE mc.oid_clie = mci.clie_oid_clie
   AND mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
   AND mci.val_iden_docu_prin = 1
   AND mc.sal_deud_ante > 0
   AND NOT EXISTS (
       SELECT NULL
       FROM ccc_consu_casti_cabec c
       WHERE c.oid_clie = mc.oid_clie
         AND c.ind_acti = 1)
   AND NOT EXISTS (
       SELECT NULL
       FROM ccc_clien_casti i
       WHERE i.oid_clie = mc.oid_clie)
   AND EXISTS (
      SELECT NULL
      FROM mae_clien_tipo_subti mcts
      WHERE mcts.clie_oid_clie = mc.oid_clie
        AND mcts.ticl_oid_tipo_clie = 2); 
 
 TYPE interfazrec IS RECORD(
           codigoConsultora                                         mae_clien.cod_clie%TYPE,
           codigoDigitoControl                                    mae_clien.cod_digi_ctrl%TYPE,
           codigoTipoDocumento                                 VARCHAR2(8),
           numeroDocumentoIdentidad                      mae_clien_ident.num_docu_iden%TYPE,
           digitoControlDocumento                             VARCHAR2(2),
           apellidoPaterno                                            mae_clien.val_ape1%TYPE,
           apellidoMaterno                                          mae_clien.val_ape2%TYPE,
           primerNombre                                             mae_clien.val_nom1%TYPE,
           segundoNombre                                           mae_clien.val_nom2%TYPE,
           codigoZona                                                   zon_zona.cod_zona%TYPE,
           tipoCliente                                                    VARCHAR2(2),
           subTipoCliente                                              VARCHAR2(2),
           tipoClasificacion                                           VARCHAR2(2),
           clasificacion                                                 VARCHAR2(2),
           montoDeuda                                                 VARCHAR2(18));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 1000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    lsLinea             VARCHAR2(1000);
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
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
    END IF;
    
    IF interfazRecord.COUNT > 0 THEN
    
     FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoConsultora                    ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).codigoTipoDocumento                    ||';'||
                              interfazRecord(x).numeroDocumentoIdentidad            ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).apellidoPaterno                   ||';'||
                              interfazRecord(x).apellidoMaterno                  ||';'||
                              interfazRecord(x).primerNombre               ||';'||
                              interfazRecord(x).segundoNombre          ||';'||
                              interfazRecord(x).codigoZona              ||';'||
                              interfazRecord(x).tipoCliente                ||';'||
                              interfazRecord(x).subTipoCliente          ||';'||
                              interfazRecord(x).tipoClasificacion        ||';'||
                              interfazRecord(x).clasificacion              ||';'||
                              interfazRecord(x).montoDeuda           ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;
    RETURN;
 EXCEPTION
 
  WHEN OTHERS THEN
  
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 1000);
   raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SALDO_CONSU: ' ||
                               ls_sqlerrm);

 END INT_PR_CCC_ENVIA_SALDO_CONSU;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-3
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 *********************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SALDO_CASTI(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2)
 IS
   CURSOR c_interfaz IS
        SELECT
           ccc.cod_clie,
           NVL(ccc.cod_digi_ctrl,' ') cod_digi_ctrl  ,
           ccc.cod_tipo_docu,
           ccc.num_docu_iden,
           NULL,
           NVL(ccc.val_ape1,' ') val_ape1,
           NVL(ccc.val_ape2,' ') val_Ape2,
           NVL(ccc.val_nom1,' ') val_nom1,
           NVL(ccc.val_nom2,' ') val_nom2,
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
           TRIM(REPLACE(TO_CHAR(ccc.imp_deud_actu,'0000000000000000D99'),'.')),
            TO_CHAR(ccc.fec_deud,'YYYYMMDD')
        FROM
           ccc_clien_casti ccc
        WHERE ccc.imp_deud_actu > 0
        AND NOT EXISTS
          ( SELECT NULL
             FROM cob_detal_movim_carte_manua carte
             WHERE carte.oid_clie = ccc.oid_clie);

        TYPE interfazrec IS RECORD(
           codigoConsultora                                         mae_clien.cod_clie%TYPE,
           codigoDigitoControl                                    mae_clien.cod_digi_ctrl%TYPE,
           codigoTipoDocumento                                 VARCHAR2(8),
           numeroDocumentoIdentidad                      mae_clien_ident.num_docu_iden%TYPE,
           digitoControlDocumento                             VARCHAR2(2),
           apellidoPaterno                                            mae_clien.val_ape1%TYPE,
           apellidoMaterno                                          mae_clien.val_ape2%TYPE,
           primerNombre                                             mae_clien.val_nom1%TYPE,
           segundoNombre                                           mae_clien.val_nom2%TYPE,
           codigoZona                                                   zon_zona.cod_zona%TYPE,
           tipoCliente                                                    VARCHAR2(2),
           subTipoCliente                                              VARCHAR2(2),
           tipoClasificacion                                           VARCHAR2(2),
           clasificacion                                                 VARCHAR2(2),
           montoDeuda                                                 VARCHAR2(18),
           fechaDeuda                                                  VARCHAR2(8));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 1000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    lsLinea             VARCHAR2(1000);
    lsNombreArchivo     VARCHAR2(50);
    lbAbrirUtlFile      BOOLEAN;

    BEGIN

      /* Actualizar Saldos de las Consultoras Castigadas */


     /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoConsultora                    ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).codigoTipoDocumento                    ||';'||
                              interfazRecord(x).numeroDocumentoIdentidad            ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).apellidoPaterno                   ||';'||
                              interfazRecord(x).apellidoMaterno                  ||';'||
                              interfazRecord(x).primerNombre               ||';'||
                              interfazRecord(x).segundoNombre          ||';'||
                              interfazRecord(x).codigoZona              ||';'||
                              interfazRecord(x).tipoCliente                ||';'||
                              interfazRecord(x).subTipoCliente          ||';'||
                              interfazRecord(x).tipoClasificacion        ||';'||
                              interfazRecord(x).clasificacion              ||';'||
                              interfazRecord(x).montoDeuda                 ||';'||
                              interfazRecord(x).fechaDeuda   ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SALDO_CASTI: ' ||
                               ls_sqlerrm);

   END INT_PR_CCC_ENVIA_SALDO_CASTI;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-4
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 *********************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_MAEST_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
      p_nomb_arch                  IN   VARCHAR2)
   IS
   CURSOR c_interfaz(
      p_mont_deud_fict NUMBER) IS
        WITH
            clie AS (
               SELECT
                  mc.oid_clie,
                  mc.cod_clie,
                  mc.cod_digi_ctrl,
                  mtd.cod_tipo_docu,
                  TRANSLATE(mci.num_docu_iden,'0ABCDEFGHIJKLMN?OPQRSTUVWXYZ_/-.','0') num_docu_iden,
                  mc.val_ape1,
                  mc.val_ape2,
                  mc.val_nom1,
                  mc.val_nom2
               FROM
                  mae_clien mc,
                  mae_clien_ident mci,
                  mae_tipo_docum mtd,
                  mae_clien_tipo_subti mcts
               WHERE mc.oid_clie=mci.clie_oid_clie
                    AND mc.oid_clie=mcts.clie_oid_clie
                    AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
                    AND mci.val_iden_docu_prin=1
                    AND mcts.ticl_oid_tipo_clie=2),
           ccc AS
               (SELECT clie.oid_clie ,
                               SUM(mcc.imp_pend) imp_pend
                 FROM ccc_movim_cuent_corri mcc,
                            clie
                 WHERE mcc.clie_oid_clie=clie.oid_clie
                 AND mcc.imp_pend <> 0
                 HAVING SUM(mcc.imp_pend)> 0
                 GROUP BY clie.oid_clie)
             SELECT
                clie.cod_clie,
                NVL(clie.cod_digi_ctrl,' ') cod_digi_ctrl  ,
                clie.cod_tipo_docu,
                clie.num_docu_iden,
                NULL,
                NVL(clie.val_ape1,' ') val_ape1,
                NVL(clie.val_ape2,' ') val_Ape2,
                NVL(clie.val_nom1,' ') val_nom1,
                NVL(clie.val_nom2,' ') val_nom2,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                TRIM(REPLACE(TO_CHAR(NVL(ccc.imp_pend,p_mont_deud_fict),'0000000000000000D99'),'.'))
             FROM ccc,
                        clie
             WHERE clie.oid_clie = ccc.oid_clie(+);


        TYPE interfazrec IS RECORD(
           codigoConsultora                                         mae_clien.cod_clie%TYPE,
           codigoDigitoControl                                    mae_clien.cod_digi_ctrl%TYPE,
           codigoTipoDocumento                                 VARCHAR2(8),
           numeroDocumentoIdentidad                      mae_clien_ident.num_docu_iden%TYPE,
           digitoControlDocumento                             VARCHAR2(2),
           apellidoPaterno                                            mae_clien.val_ape1%TYPE,
           apellidoMaterno                                          mae_clien.val_ape2%TYPE,
           primerNombre                                             mae_clien.val_nom1%TYPE,
           segundoNombre                                           mae_clien.val_nom2%TYPE,
           codigoZona                                                   zon_zona.cod_zona%TYPE,
           tipoCliente                                                    VARCHAR2(2),
           subTipoCliente                                              VARCHAR2(2),
           tipoClasificacion                                           VARCHAR2(2),
           clasificacion                                                 VARCHAR2(2),
           montoDeuda                                                 VARCHAR2(18));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 1000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    lsLinea             VARCHAR2(1000);
    lsNombreArchivo     VARCHAR2(50);
    lbAbrirUtlFile      BOOLEAN;
    lv_mont_deud_fict    NUMBER;

    BEGIN

       SELECT p.val_mont_deud_fict
       INTO lv_mont_deud_fict
       FROM ccc_pais_socie_param p
       WHERE p.cod_pais  = p_cod_pais;

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(lv_mont_deud_fict);
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoConsultora                    ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).codigoTipoDocumento                    ||';'||
                              interfazRecord(x).numeroDocumentoIdentidad            ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).apellidoPaterno                   ||';'||
                              interfazRecord(x).apellidoMaterno                  ||';'||
                              interfazRecord(x).primerNombre               ||';'||
                              interfazRecord(x).segundoNombre          ||';'||
                              interfazRecord(x).codigoZona              ||';'||
                              interfazRecord(x).tipoCliente                ||';'||
                              interfazRecord(x).subTipoCliente          ||';'||
                              interfazRecord(x).tipoClasificacion        ||';'||
                              interfazRecord(x).clasificacion              ||';'||
                              interfazRecord(x).montoDeuda           ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_MAEST_CONSU: ' ||
                               ls_sqlerrm);

   END INT_PR_CCC_ENVIA_MAEST_CONSU;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Interface BAN-5
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 *********************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_NOVED_CONSU(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2)
 IS
   CURSOR c_interfaz(
      p_clie_inic              NUMBER,
      p_clie_fina               NUMBER,
      p_mont_deud_fict NUMBER) IS
        WITH
            clie AS (
               SELECT
                  mc.oid_clie,
                  mc.cod_clie,
                  mc.cod_digi_ctrl,
                  mtd.cod_tipo_docu,
                  mci.num_docu_iden,
                  mc.val_ape1,
                  mc.val_ape2,
                  mc.val_nom1,
                  mc.val_nom2
               FROM
                  mae_clien mc,
                  mae_clien_ident mci,
                  mae_tipo_docum mtd,
                  mae_clien_tipo_subti mcts
               WHERE mc.oid_clie=mci.clie_oid_clie
                    AND mc.oid_clie=mcts.clie_oid_clie
                    AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
                    AND mci.val_iden_docu_prin=1
                    AND mcts.ticl_oid_tipo_clie=2
                    AND mc.oid_clie > p_clie_inic
                    AND mc.oid_clie <= p_clie_fina),
           ccc AS
               (SELECT clie.oid_clie ,
                               SUM(mcc.imp_pend) imp_pend
                 FROM ccc_movim_cuent_corri mcc,
                            clie
                 WHERE mcc.clie_oid_clie=clie.oid_clie
                 AND mcc.imp_pend <> 0
                 HAVING SUM(mcc.imp_pend)> 0
                 GROUP BY clie.oid_clie)
             SELECT
                clie.cod_clie,
                NVL(clie.cod_digi_ctrl,' ') cod_digi_ctrl  ,
                clie.cod_tipo_docu,
                clie.num_docu_iden,
                NULL,
                NVL(clie.val_ape1,' ') val_ape1,
                NVL(clie.val_ape2,' ') val_Ape2,
                NVL(clie.val_nom1,' ') val_nom1,
                NVL(clie.val_nom2,' ') val_nom2,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                TRIM(REPLACE(TO_CHAR(NVL(ccc.imp_pend,p_mont_deud_fict),'0000000000000000D99'),'.'))
             FROM ccc,
                        clie
             WHERE clie.oid_clie = ccc.oid_clie(+);


        TYPE interfazrec IS RECORD(
           codigoConsultora                                         mae_clien.cod_clie%TYPE,
           codigoDigitoControl                                    mae_clien.cod_digi_ctrl%TYPE,
           codigoTipoDocumento                                 VARCHAR2(8),
           numeroDocumentoIdentidad                      mae_clien_ident.num_docu_iden%TYPE,
           digitoControlDocumento                             VARCHAR2(2),
           apellidoPaterno                                            mae_clien.val_ape1%TYPE,
           apellidoMaterno                                          mae_clien.val_ape2%TYPE,
           primerNombre                                             mae_clien.val_nom1%TYPE,
           segundoNombre                                           mae_clien.val_nom2%TYPE,
           codigoZona                                                   zon_zona.cod_zona%TYPE,
           tipoCliente                                                    VARCHAR2(2),
           subTipoCliente                                              VARCHAR2(2),
           tipoClasificacion                                           VARCHAR2(2),
           clasificacion                                                 VARCHAR2(2),
           montoDeuda                                                 VARCHAR2(18));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 1000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    lsLinea             VARCHAR2(1000);
    lsNombreArchivo     VARCHAR2(50);
    lbAbrirUtlFile      BOOLEAN;
    lv_mont_deud_fict    NUMBER;
    p_clie_inic                 NUMBER;
    p_clie_fina                NUMBER;

    BEGIN

       SELECT rp.oid_ulti_regi_proc
       INTO  p_clie_inic
       FROM   fin_contr_regis_progr rp
       WHERE rp.cod_modu = p_cod_sist
       AND rp.cod_prog = p_cod_inte;

       SELECT MAX(mc.oid_clie)
       INTO p_clie_fina
       FROM mae_clien mc;

       SELECT p.val_mont_deud_fict
       INTO lv_mont_deud_fict
       FROM ccc_pais_socie_param p
       WHERE p.cod_pais  = p_cod_pais;

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(p_clie_inic,p_clie_fina,lv_mont_deud_fict);
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoConsultora                    ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).codigoTipoDocumento                    ||';'||
                              interfazRecord(x).numeroDocumentoIdentidad            ||';'||
                              interfazRecord(x).codigoDigitoControl                ||';'||
                              interfazRecord(x).apellidoPaterno                   ||';'||
                              interfazRecord(x).apellidoMaterno                  ||';'||
                              interfazRecord(x).primerNombre               ||';'||
                              interfazRecord(x).segundoNombre          ||';'||
                              interfazRecord(x).codigoZona              ||';'||
                              interfazRecord(x).tipoCliente                ||';'||
                              interfazRecord(x).subTipoCliente          ||';'||
                              interfazRecord(x).tipoClasificacion        ||';'||
                              interfazRecord(x).clasificacion              ||';'||
                              interfazRecord(x).montoDeuda           ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;

        UPDATE fin_contr_regis_progr rp
       SET rp.oid_ante_regi_proc = p_clie_inic,
               rp.oid_ulti_regi_proc =   p_clie_fina
       WHERE rp.cod_modu = p_cod_sist
       AND rp.cod_prog = p_cod_inte;

    RETURN;
 EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 1000);
   raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_NOVED_CONSU: ' ||
                               ls_sqlerrm);

 END INT_PR_CCC_ENVIA_NOVED_CONSU;

 PROCEDURE INT_PR_CCC_GENER_COBRA_TOTAL(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='011';
  gc_ind_debe_habe                 CHAR(1):='D';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_pago)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Cobranza Total ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'COBRANZA';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := p_cod_banc;
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_TOTAL;

 PROCEDURE INT_PR_CCC_GENER_COBRA_CLIEN(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='012';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_clie)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Clientes ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'APLICACION CLIENTE';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_CLIEN;

 PROCEDURE INT_PR_CCC_GENER_COBRA_PENDI(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='013';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_cobr_pend_regu)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli >0 THEN

   --dbms_output.put_line('Cobranza Pendiente ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'CXR';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
   lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := p_cod_banc;
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_PENDI;

 PROCEDURE INT_PR_CCC_GENER_COBRA_INCOB(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='017';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_inco)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
 WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Incobrable ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'INCOBRABLE';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_INCOB;

 PROCEDURE INT_PR_CCC_GENER_COBRA_ABOGA(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='018';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_cobr_exte)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Cobranza Externa' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'ABOGADOS';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_ABOGA;

 PROCEDURE INT_PR_CCC_GENER_COBRA_EXCES(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='019';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_exce)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Exceso ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
  lv_reg_ccc_carga_inter_sapfi.val_glosa := 'EXCESO';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_EXCES;

 PROCEDURE INT_PR_CCC_GENER_COBRA_FAMIL(
  p_cod_tipo_orig                  IN   ccc_numer_lote_banca_recep.cod_tipo_orig%TYPE,
  p_cod_banc                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote                       IN   ccc_numer_lote_banca_recep.num_lote%TYPE,
  p_fec_proc                       IN   ccc_movim_banca.fec_proc%TYPE,
  p_fec_pago                       IN   ccc_movim_banca.fec_pago%TYPE,
  p_oid_regi_inic                  IN   ccc_movim_banca.oid_movi_banc%TYPE,
  p_oid_regi_fina                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS

  lv_imp_apli                      NUMBER(15,2);
  gc_tipo_asie_cobr                CHAR(3):='CR';
  gc_tipo_movi                     CHAR(3):='038';
  gc_ind_debe_habe                 CHAR(1):='H';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 BEGIN

  SELECT SUM(mb.imp_apli_fami_prot)
  INTO lv_imp_apli
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE br.num_lote = mb.num_lote
    AND br.cod_cuen_corr_banc = p_cod_banc
    AND br.cod_tipo_orig = p_cod_tipo_orig
    AND mb.fec_proc = p_fec_proc
    AND mb.fec_pago = p_fec_pago
    AND mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina;

  IF lv_imp_apli > 0 THEN

   --dbms_output.put_line('Familia ' || lv_imp_apli);

  lv_reg_ccc_carga_inter_sapfi.num_lote := p_num_lote;
  lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
  lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
  lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi;
   lv_reg_ccc_carga_inter_sapfi.val_glosa := 'FAMILIA PROTEGIDA';
  lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe_habe;
  lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
  lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(p_fec_proc,'MM');
  lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(p_fec_proc,'YYYY');
  lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
  lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := p_fec_pago;
  lv_reg_ccc_carga_inter_sapfi.fec_valo := p_fec_proc;
  lv_reg_ccc_carga_inter_sapfi.zona := p_cod_tipo_orig;

  INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

  END IF;

 END INT_PR_CCC_GENER_COBRA_FAMIL;

 PROCEDURE INT_PR_CCC_GENER_INFOR_COBFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc                      DATE;
  lv_cant_lote_pend                NUMBER(12);
  lv_num_lote                      VARCHAR2(15);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_vali                          NUMBER(15,2);

  CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc, br.cod_tipo_orig
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND br.cod_tipo_orig IN (gc_cod_tipo_orig_inte,gc_cod_tipo_orig_digi,gc_cod_tipo_orig_exce)
  GROUP BY br.cod_cuen_corr_banc, br.cod_tipo_orig;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE,
   pc_cod_tipo_orig                ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND br.cod_tipo_orig = pc_cod_tipo_orig
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  SELECT COUNT(1)
  INTO lv_cant_lote_pend
  FROM
   ccc_movim_banca mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
    AND mb.fec_proc <= lv_fec_proc
    AND mb.cod_iden_proc = gc_cod_iden_proc_tran;

  IF lv_cant_lote_pend > 0 THEN
   RAISE e_lote_pend;
  END IF;

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_cobr;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.fec_proc <= lv_fec_proc;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  INT_PR_CCC_VALID_INFOR_SAPFI(lv_oid_regi_inic,lv_oid_regi_fina);

  -- POR BANCO UN GENERAMOS UN ARCHIVO SIN DISCRIMINAR EL TIPO DE ORIGEN
  FOR v_banc IN c_banc LOOP

   DELETE FROM ccc_carga_inter_sapfi;

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc,v_banc.cod_tipo_orig)LOOP

    INT_PR_CCC_GENER_COBRA_TOTAL(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_CLIEN(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_PENDI(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_INCOB(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_ABOGA(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    --INT_PR_CCC_GENER_COBRA_EXCES(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_FAMIL(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);

  END LOOP;

  -- Validacion
  SELECT
  (SELECT sum(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = 'D') -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = 'H')
  INTO lv_vali
  FROM dual;


  IF lv_vali <> 0 THEN
   RAISE e_dife_impo;
  END IF;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_cobr,lv_num_lote,p_cod_usua);

  --INT_PR_CCC_SLEEP(60);

 END LOOP;

  UPDATE fin_contr_regis_progr rp
  SET
   rp.oid_ante_regi_proc = lv_oid_regi_inic,
   rp.oid_ulti_regi_proc =   lv_oid_regi_fina
  WHERE rp.cod_modu = gc_cod_modu
   AND rp.cod_prog = gc_cod_inte_sapf_cobr;

 EXCEPTION

   WHEN e_lote_pend THEN
   p_ind_erro := 'S';
   p_des_erro := 'Existen lotes bancarios pendientes de procesar';

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro := 'Existen diferencias Interface SAPFI Cobranzas';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_COBFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_COBFI(
  p_fec_proc_inic                  IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc_inic                 DATE;
  lv_fec_proc_fina                 DATE;
  lv_cant_lote_pend                NUMBER(12);
  lv_num_lote                      VARCHAR2(15);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_vali                          NUMBER(15,2);

  CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc, br.cod_tipo_orig
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND br.cod_tipo_orig IN (gc_cod_tipo_orig_inte,gc_cod_tipo_orig_digi,gc_cod_tipo_orig_exce)
  GROUP BY br.cod_cuen_corr_banc, br.cod_tipo_orig;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE,
   pc_cod_tipo_orig                ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND br.cod_tipo_orig = pc_cod_tipo_orig
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc_inic := TO_DATE(p_fec_proc_inic,'DD/MM/YYYY');
  lv_fec_proc_fina := TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  SELECT COUNT(1)
  INTO lv_cant_lote_pend
  FROM
   ccc_movim_banca mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
    AND mb.fec_proc >= p_fec_proc_inic
    AND mb.fec_proc <= p_fec_proc_fina
    AND mb.cod_iden_proc = gc_cod_iden_proc_tran;

  IF lv_cant_lote_pend > 0 THEN
   RAISE e_lote_pend;
  END IF;

  SELECT MIN(mb.oid_movi_banc),MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_inic,lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.fec_proc >= lv_fec_proc_inic
    AND mb.fec_proc <= lv_fec_proc_fina;

  lv_oid_regi_inic := lv_oid_regi_inic - 1;

  INT_PR_CCC_VALID_INFOR_SAPFI(lv_oid_regi_inic,lv_oid_regi_fina);

  -- POR BANCO UN GENERAMOS UN ARCHIVO SIN DISCRIMINAR EL TIPO DE ORIGEN
  FOR v_banc IN c_banc LOOP

   DELETE FROM ccc_carga_inter_sapfi;

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc,v_banc.cod_tipo_orig)LOOP

    INT_PR_CCC_GENER_COBRA_TOTAL(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_CLIEN(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_PENDI(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_INCOB(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_ABOGA(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_EXCES(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);
    INT_PR_CCC_GENER_COBRA_FAMIL(v_banc.cod_tipo_orig,v_banc.cod_cuen_corr_banc,lv_num_lote,v_banc_fech.fec_proc,v_banc_fech.fec_pago,lv_oid_regi_inic,lv_oid_regi_fina);

  END LOOP;

  -- Validacion
  SELECT
  (SELECT sum(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = 'D') -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = 'H')
  INTO lv_vali
  FROM dual;


  IF lv_vali <> 0 THEN
   RAISE e_dife_impo;
  END IF;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_cobr,lv_num_lote,p_cod_usua);

  --INT_PR_CCC_SLEEP(60);

 END LOOP;

 EXCEPTION

   WHEN e_lote_pend THEN
   p_ind_erro := 'S';
   p_des_erro := 'Existen lotes bancarios pendientes de procesar';

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro := 'Existen diferencias Interface SAPFI Cobranzas';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_COBFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_CDIFI(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc                      DATE;
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_imp_base                      NUMBER(15,2);
  lv_imp_impu                      NUMBER(15,2);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

  CURSOR c_tipo_abon_dire(
   pc_oid_movi_inic    ccc_movim_cuent_corri.oid_movi_cc%TYPE,
   pc_oid_movi_fina    ccc_movim_cuent_corri.oid_movi_cc%TYPE)
  IS
  SELECT p.cod_proc,p.cod_subp, su.des_subp,mcc.fec_docu, SUM(mcc.imp_movi) imp_movi
  FROM ccc_param_conta_sapfi p,
      ccc_movim_cuent_corri mcc,
      ccc_proce cp,
      ccc_subpr su
  WHERE p.cod_proc = cp.cod_proc
    AND p.cod_subp = su.cod_subp
    AND cp.oid_proc = su.ccpr_oid_proc
    AND mcc.subp_oid_subp_crea = su.oid_subp
    AND p.ind_acti = 2
    AND su.ind_carg_dire = 1
    AND p.ind_calc = 'T'
    AND mcc.oid_movi_cc > pc_oid_movi_inic
    AND mcc.oid_movi_cc <= pc_oid_movi_fina
    AND mcc.fec_docu <= lv_fec_proc
    AND NOT EXISTS (
     SELECT NULL
     FROM fac_tipos_impue_ubige f,
          zon_secci zs
     WHERE f.vepo_oid_valo_estr_geop = zs.zzon_oid_zona
       AND mcc.zscc_oid_secc = zs.oid_secc
    )
  GROUP BY p.cod_proc,p.cod_subp,su.des_subp, mcc.fec_docu
  ORDER BY 4 ASC;

  CURSOR c_abon_dire_espe(
   pc_cod_proc      ccc_proce.cod_proc%TYPE,
   pc_cod_subp      ccc_subpr.cod_subp%TYPE)
  IS
  SELECT
   p.val_tipo_asie,
   p.val_debe_habe,
   p.cod_cuen_sicc,
   p.imp_porc,
   p.ind_calc,
   p.val_porc_base,
   p.val_porc_impu,
   p.val_porc_cree
  FROM ccc_param_conta_sapfi p
  WHERE p.ind_acti = 2
    AND p.cod_proc = pc_cod_proc
    AND p.cod_subp = pc_cod_subp;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  -- Cargos y Abonos Directos --
  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_cad;

  SELECT MAX(mcc.oid_movi_cc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.oid_movi_cc > lv_oid_regi_inic
    AND mcc.fec_docu <= lv_fec_proc;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM ccc_carga_inter_sapfi;

  -- Insertando los Abonos Especiales --
  FOR v_tipo_abon_dire in c_tipo_abon_dire(lv_oid_regi_inic,lv_oid_regi_fina) LOOP

   FOR v_abon_dire_espe in c_abon_dire_espe(v_tipo_abon_dire.cod_proc,v_tipo_abon_dire.cod_subp) LOOP

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := v_abon_dire_espe.val_tipo_asie;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := v_abon_dire_espe.cod_cuen_sicc;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := v_tipo_abon_dire.des_subp;
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := v_abon_dire_espe.val_debe_habe;

    CASE
     WHEN v_abon_dire_espe.ind_calc = 'T' THEN
      lv_reg_ccc_carga_inter_sapfi.imp_movi := v_tipo_abon_dire.imp_movi;

     WHEN v_abon_dire_espe.ind_calc = 'B' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(v_tipo_abon_dire.imp_movi*v_abon_dire_espe.imp_porc);
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_base;

     WHEN v_abon_dire_espe.ind_calc = 'I' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      lv_imp_impu := v_tipo_abon_dire.imp_movi - lv_imp_base;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_impu;

     WHEN v_abon_dire_espe.ind_calc = 'R' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(lv_reg_ccc_carga_inter_sapfi.imp_movi*v_abon_dire_espe.imp_porc);
      --lv_imp_base := v_tipo_abon_dire.imp_movi - lv_imp_impu;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := ROUND(lv_imp_base*v_abon_dire_espe.val_porc_cree/100) ;

    END CASE;

    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.zona :=  v_abon_dire_espe.val_tipo_asie;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;
  END LOOP;

  -- INSERTAR REGISTROS DE CARGOS Y ABONOS DIRECTOS --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote              num_lote,
     TRUNC(SYSDATE)           fec_cont,
     val_tipo_asie            val_tipo_asie_cont,
     cod_cuen_sicc            val_tipo_movi_cont,
     val_text_apunt           val_glosa,
     val_debe_habe            val_debe_habe,
     imp_movi                 imp_movi,
     TO_CHAR(SYSDATE,'MM')    val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY')  val_ejer_cont,
     NULL                     cod_cuent_corri_banc,
     fec_docu                 fec_pago_banc,
     fec_docu                 fec_valo,
     'CDDI'                   zona
   FROM
     (SELECT
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt,
       SUM(ROUND(ABS(mcc.imp_movi),2)) imp_movi
      FROM
       ccc_movim_cuent_corri mcc,
       ccc_param_conta_sapfi pc,
       ccc_proce cp,
       ccc_subpr su
      WHERE mcc.subp_oid_subp_crea = su.oid_subp
        AND su.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = su.cod_subp
        AND su.ind_carg_dire = 1
        AND pc.ind_acti = 1
        AND mcc.oid_movi_cc > lv_oid_regi_inic
        AND mcc.oid_movi_cc <= lv_oid_regi_fina
        AND mcc.fec_docu <= lv_fec_proc
      GROUP BY
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt)
   ORDER BY fec_docu DESC, cod_cuen_sicc DESC;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  from ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_cad,lv_num_lote,p_cod_usua);

   UPDATE ccc_movim_cuent_corri mcc
   SET
    mcc.fec_conta = trunc(SYSDATE),
    mcc.val_nume_lote_cont = lv_num_lote
   WHERE mcc.oid_movi_cc >  lv_oid_regi_inic
     AND mcc.oid_movi_cc < = lv_oid_regi_fina;

   UPDATE fin_contr_regis_progr rp
   SET
    rp.oid_ante_regi_proc = lv_oid_regi_inic,
    rp.oid_ulti_regi_proc =   lv_oid_regi_fina
   WHERE rp.cod_modu = gc_cod_modu
     AND rp.cod_prog = gc_cod_inte_sapf_cad;

  ELSE

   RAISE e_no_gene_regi;

  END IF;

 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN

   p_ind_erro := 'S';
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_CDIFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_CDIFI(
  p_fec_proc_inic                  IN   VARCHAR2,
  p_fec_proc_fina                  IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc_inic                 DATE;
  lv_fec_proc_fina                 DATE;
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_imp_base                      NUMBER(15,2);
  lv_imp_impu                      NUMBER(15,2);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

  CURSOR c_tipo_abon_dire(
   pc_oid_movi_inic    ccc_movim_cuent_corri.oid_movi_cc%TYPE,
   pc_oid_movi_fina    ccc_movim_cuent_corri.oid_movi_cc%TYPE)
  IS
  SELECT p.cod_proc,p.cod_subp, su.des_subp,mcc.fec_docu, SUM(mcc.imp_movi) imp_movi
  FROM ccc_param_conta_sapfi p,
      ccc_movim_cuent_corri mcc,
      ccc_proce cp,
      ccc_subpr su
  WHERE p.cod_proc = cp.cod_proc
    AND p.cod_subp = su.cod_subp
    AND cp.oid_proc = su.ccpr_oid_proc
    AND mcc.subp_oid_subp_crea = su.oid_subp
    AND p.ind_acti = 2
    AND su.ind_carg_dire = 1
    AND p.ind_calc = 'T'
    AND mcc.oid_movi_cc > pc_oid_movi_inic
    AND mcc.oid_movi_cc <= pc_oid_movi_fina
    AND NOT EXISTS (
     SELECT NULL
     FROM fac_tipos_impue_ubige f,
          zon_secci zs
     WHERE f.vepo_oid_valo_estr_geop = zs.zzon_oid_zona
       AND mcc.zscc_oid_secc = zs.oid_secc
    )
  GROUP BY p.cod_proc,p.cod_subp,su.des_subp, mcc.fec_docu
  ORDER BY 4 ASC;

  CURSOR c_abon_dire_espe(
   pc_cod_proc      ccc_proce.cod_proc%TYPE,
   pc_cod_subp      ccc_subpr.cod_subp%TYPE)
  IS
  SELECT
   p.val_tipo_asie,
   p.val_debe_habe,
   p.cod_cuen_sicc,
   p.imp_porc,
   p.ind_calc,
   p.val_porc_base,
   p.val_porc_impu,
   p.val_porc_cree
  FROM ccc_param_conta_sapfi p
  WHERE p.ind_acti = 2
    AND p.cod_proc = pc_cod_proc
    AND p.cod_subp = pc_cod_subp;

 BEGIN

  lv_fec_proc_inic := TO_DATE(p_fec_proc_inic,'DD/MM/YYYY');
  lv_fec_proc_fina := TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  -- Cargos y Abonos Directos --
  SELECT MIN(mcc.oid_movi_cc),MAX(mcc.oid_movi_cc)
  INTO lv_oid_regi_inic,lv_oid_regi_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.fec_docu >= lv_fec_proc_inic
    AND mcc.fec_docu <= lv_fec_proc_fina;

  lv_oid_regi_inic := lv_oid_regi_inic -1;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM ccc_carga_inter_sapfi;

  -- Insertando los Abonos Especiales --
  FOR v_tipo_abon_dire in c_tipo_abon_dire(lv_oid_regi_inic,lv_oid_regi_fina) LOOP

   FOR v_abon_dire_espe in c_abon_dire_espe(v_tipo_abon_dire.cod_proc,v_tipo_abon_dire.cod_subp) LOOP

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := v_abon_dire_espe.val_tipo_asie;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := v_abon_dire_espe.cod_cuen_sicc;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := v_tipo_abon_dire.des_subp;
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := v_abon_dire_espe.val_debe_habe;

    CASE
     WHEN v_abon_dire_espe.ind_calc = 'T' THEN
      lv_reg_ccc_carga_inter_sapfi.imp_movi := v_tipo_abon_dire.imp_movi;

     WHEN v_abon_dire_espe.ind_calc = 'B' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(v_tipo_abon_dire.imp_movi*v_abon_dire_espe.imp_porc);
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_base;

     WHEN v_abon_dire_espe.ind_calc = 'I' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      lv_imp_impu := v_tipo_abon_dire.imp_movi - lv_imp_base;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_impu;

     WHEN v_abon_dire_espe.ind_calc = 'R' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(lv_reg_ccc_carga_inter_sapfi.imp_movi*v_abon_dire_espe.imp_porc);
      --lv_imp_base := v_tipo_abon_dire.imp_movi - lv_imp_impu;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := ROUND(lv_imp_base*v_abon_dire_espe.val_porc_cree/100) ;

    END CASE;

    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.zona :=  v_abon_dire_espe.val_tipo_asie;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;
  END LOOP;

  -- INSERTAR REGISTROS DE CARGOS Y ABONOS DIRECTOS --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote              num_lote,
     TRUNC(SYSDATE)           fec_cont,
     val_tipo_asie            val_tipo_asie_cont,
     cod_cuen_sicc            val_tipo_movi_cont,
     val_text_apunt           val_glosa,
     val_debe_habe            val_debe_habe,
     imp_movi                 imp_movi,
     TO_CHAR(SYSDATE,'MM')    val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY')  val_ejer_cont,
     NULL                     cod_cuent_corri_banc,
     fec_docu                 fec_pago_banc,
     fec_docu                 fec_valo,
     'CDDI'                   zona
   FROM
     (SELECT
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt,
       SUM(ROUND(ABS(mcc.imp_movi),2)) imp_movi
      FROM
       ccc_movim_cuent_corri mcc,
       ccc_param_conta_sapfi pc,
       ccc_proce cp,
       ccc_subpr su
      WHERE mcc.subp_oid_subp_crea = su.oid_subp
        AND su.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = su.cod_subp
        AND su.ind_carg_dire = 1
        AND pc.ind_acti = 1
        AND mcc.oid_movi_cc > lv_oid_regi_inic
        AND mcc.oid_movi_cc <= lv_oid_regi_fina
      GROUP BY
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt)
   ORDER BY fec_docu DESC, cod_cuen_sicc DESC;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  from ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_cad,lv_num_lote,p_cod_usua);

   UPDATE ccc_movim_cuent_corri mcc
   SET
    mcc.fec_conta = trunc(SYSDATE),
    mcc.val_nume_lote_cont = lv_num_lote
   WHERE mcc.oid_movi_cc >  lv_oid_regi_inic
     AND mcc.oid_movi_cc < = lv_oid_regi_fina;

  ELSE

   RAISE e_no_gene_regi;

  END IF;

 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN

   p_ind_erro := 'S';
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_CDIFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_ABIFI(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc                      DATE;
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_imp_base                      NUMBER(15,2);
  lv_imp_impu                      NUMBER(15,2);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

  CURSOR c_tipo_abon_dire(
   pc_oid_movi_inic    ccc_movim_cuent_corri.oid_movi_cc%TYPE,
   pc_oid_movi_fina    ccc_movim_cuent_corri.oid_movi_cc%TYPE)
  IS
  SELECT p.cod_proc,p.cod_subp, su.des_subp,mcc.fec_docu, SUM(mcc.imp_movi*-1) imp_movi
  FROM ccc_param_conta_sapfi p,
      ccc_movim_cuent_corri mcc,
      ccc_proce cp,
      ccc_subpr su
  WHERE p.cod_proc = cp.cod_proc
    AND p.cod_subp = su.cod_subp
    AND cp.oid_proc = su.ccpr_oid_proc
    AND mcc.subp_oid_subp_crea = su.oid_subp
    AND p.ind_acti = 2
    AND p.ind_calc = 'T'
    AND su.ind_abon_dire = 1
    AND mcc.oid_movi_cc > pc_oid_movi_inic
    AND mcc.oid_movi_cc <= pc_oid_movi_fina
    AND mcc.fec_docu <= lv_fec_proc
  GROUP BY p.cod_proc,p.cod_subp,su.des_subp, mcc.fec_docu
  ORDER BY 4 ASC;

  CURSOR c_abon_dire_espe(
   pc_cod_proc      ccc_proce.cod_proc%TYPE,
   pc_cod_subp      ccc_subpr.cod_subp%TYPE)
  IS
  SELECT
   p.val_tipo_asie,
   p.val_debe_habe,
   p.cod_cuen_sicc,
   p.imp_porc,
   p.ind_calc,
   p.val_porc_base,
   p.val_porc_impu,
   p.val_porc_cree
  FROM ccc_param_conta_sapfi p
  WHERE p.ind_acti = 2
    AND p.cod_proc = pc_cod_proc
    AND p.cod_subp = pc_cod_subp;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  -- Cargos y Abonos Directos --
  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_adi;

  SELECT MAX(mcc.oid_movi_cc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.oid_movi_cc > lv_oid_regi_inic
    AND mcc.fec_docu <= lv_fec_proc;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM ccc_carga_inter_sapfi;


  -- Insertando los Abonos Especiales --
  FOR v_tipo_abon_dire in c_tipo_abon_dire(lv_oid_regi_inic,lv_oid_regi_fina) LOOP

   FOR v_abon_dire_espe in c_abon_dire_espe(v_tipo_abon_dire.cod_proc,v_tipo_abon_dire.cod_subp) LOOP

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := v_abon_dire_espe.val_tipo_asie;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := v_abon_dire_espe.cod_cuen_sicc;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := v_tipo_abon_dire.des_subp;
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := v_abon_dire_espe.val_debe_habe;

    CASE
     WHEN v_abon_dire_espe.ind_calc = 'T' THEN
      lv_reg_ccc_carga_inter_sapfi.imp_movi := v_tipo_abon_dire.imp_movi;

     WHEN v_abon_dire_espe.ind_calc = 'B' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(v_tipo_abon_dire.imp_movi*v_abon_dire_espe.imp_porc);
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_base;

     WHEN v_abon_dire_espe.ind_calc = 'I' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      lv_imp_impu := v_tipo_abon_dire.imp_movi - lv_imp_base;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_impu;

     WHEN v_abon_dire_espe.ind_calc = 'R' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(lv_reg_ccc_carga_inter_sapfi.imp_movi*v_abon_dire_espe.imp_porc);
      --lv_imp_base := v_tipo_abon_dire.imp_movi - lv_imp_impu;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := ROUND(lv_imp_base*v_abon_dire_espe.val_porc_cree/100) ;

    END CASE;

    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.zona :=  v_abon_dire_espe.val_tipo_asie;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;
  END LOOP;


  -- INSERTAR REGISTROS DE CARGOS Y ABONOS DIRECTOS --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote              num_lote,
     TRUNC(SYSDATE)           fec_cont,
     val_tipo_asie            val_tipo_asie_cont,
     cod_cuen_sicc            val_tipo_movi_cont,
     val_text_apunt           val_glosa,
     val_debe_habe            val_debe_habe,
     imp_movi                 imp_movi,
     TO_CHAR(SYSDATE,'MM')    val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY')  val_ejer_cont,
     NULL                     cod_cuent_corri_banc,
     fec_docu                 fec_pago_banc,
     fec_docu                 fec_valo,
     'ABDI'                   zona
   FROM
     (SELECT
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt,
       SUM(ROUND(ABS(mcc.imp_movi),2)) imp_movi
      FROM
       ccc_movim_cuent_corri mcc,
       ccc_param_conta_sapfi pc,
       ccc_proce cp,
       ccc_subpr su
      WHERE mcc.subp_oid_subp_crea = su.oid_subp
        AND su.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = su.cod_subp
        AND su.ind_abon_dire = 1
        AND pc.ind_acti = 1
        AND mcc.oid_movi_cc > lv_oid_regi_inic
        AND mcc.oid_movi_cc <= lv_oid_regi_fina
        AND mcc.fec_docu <= lv_fec_proc
      GROUP BY
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt)
   ORDER BY fec_docu DESC, cod_cuen_sicc DESC;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  from ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_adi,lv_num_lote,p_cod_usua);

   UPDATE ccc_movim_cuent_corri mcc
   SET
    mcc.fec_conta = trunc(SYSDATE),
    mcc.val_nume_lote_cont = lv_num_lote
   WHERE mcc.oid_movi_cc >  lv_oid_regi_inic
     AND mcc.oid_movi_cc < = lv_oid_regi_fina;

   UPDATE fin_contr_regis_progr rp
   SET
    rp.oid_ante_regi_proc = lv_oid_regi_inic,
    rp.oid_ulti_regi_proc =   lv_oid_regi_fina
   WHERE rp.cod_modu = gc_cod_modu
     AND rp.cod_prog = gc_cod_inte_sapf_adi;

  ELSE

   RAISE e_no_gene_regi;

  END IF;


 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_ABIFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_ABIFI(
  p_fec_proc_inic                  IN   VARCHAR2,
  p_fec_proc_fina                  IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2,
  p_ind_erro                       OUT  VARCHAR2,
  p_des_erro                       OUT  VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc_inic                 DATE;
  lv_fec_proc_fina                 DATE;
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_imp_base                      NUMBER(15,2);
  lv_imp_impu                      NUMBER(15,2);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

  CURSOR c_tipo_abon_dire(
   pc_oid_movi_inic    ccc_movim_cuent_corri.oid_movi_cc%TYPE,
   pc_oid_movi_fina    ccc_movim_cuent_corri.oid_movi_cc%TYPE)
  IS
  SELECT p.cod_proc,p.cod_subp, su.des_subp,mcc.fec_docu, SUM(mcc.imp_movi*-1) imp_movi
  FROM ccc_param_conta_sapfi p,
      ccc_movim_cuent_corri mcc,
      ccc_proce cp,
      ccc_subpr su
  WHERE p.cod_proc = cp.cod_proc
    AND p.cod_subp = su.cod_subp
    AND cp.oid_proc = su.ccpr_oid_proc
    AND mcc.subp_oid_subp_crea = su.oid_subp
    AND p.ind_acti = 2
    AND p.ind_calc = 'T'
    AND su.ind_abon_dire = 1
    AND mcc.oid_movi_cc > pc_oid_movi_inic
    AND mcc.oid_movi_cc <= pc_oid_movi_fina
  GROUP BY p.cod_proc,p.cod_subp,su.des_subp, mcc.fec_docu
  ORDER BY 4 ASC;

  CURSOR c_abon_dire_espe(
   pc_cod_proc      ccc_proce.cod_proc%TYPE,
   pc_cod_subp      ccc_subpr.cod_subp%TYPE)
  IS
  SELECT
   p.val_tipo_asie,
   p.val_debe_habe,
   p.cod_cuen_sicc,
   p.imp_porc,
   p.ind_calc,
   p.val_porc_base,
   p.val_porc_impu,
   p.val_porc_cree
  FROM ccc_param_conta_sapfi p
  WHERE p.ind_acti = 2
    AND p.cod_proc = pc_cod_proc
    AND p.cod_subp = pc_cod_subp;

 BEGIN

  lv_fec_proc_inic := TO_DATE(p_fec_proc_inic,'DD/MM/YYYY');
  lv_fec_proc_fina := TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  -- Cargos y Abonos Directos --
  SELECT MIN(mcc.oid_movi_cc),MAX(mcc.oid_movi_cc)
  INTO lv_oid_regi_inic,lv_oid_regi_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.fec_docu >= lv_fec_proc_fina
    AND mcc.fec_docu <= lv_fec_proc_inic;

  lv_oid_regi_inic := lv_oid_regi_inic - 1;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM ccc_carga_inter_sapfi;


  -- Insertando los Abonos Especiales --
  FOR v_tipo_abon_dire in c_tipo_abon_dire(lv_oid_regi_inic,lv_oid_regi_fina) LOOP

   FOR v_abon_dire_espe in c_abon_dire_espe(v_tipo_abon_dire.cod_proc,v_tipo_abon_dire.cod_subp) LOOP

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := v_abon_dire_espe.val_tipo_asie;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := v_abon_dire_espe.cod_cuen_sicc;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := v_tipo_abon_dire.des_subp;
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := v_abon_dire_espe.val_debe_habe;

    CASE
     WHEN v_abon_dire_espe.ind_calc = 'T' THEN
      lv_reg_ccc_carga_inter_sapfi.imp_movi := v_tipo_abon_dire.imp_movi;

     WHEN v_abon_dire_espe.ind_calc = 'B' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(v_tipo_abon_dire.imp_movi*v_abon_dire_espe.imp_porc);
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_base;

     WHEN v_abon_dire_espe.ind_calc = 'I' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      lv_imp_impu := v_tipo_abon_dire.imp_movi - lv_imp_base;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_impu;

     WHEN v_abon_dire_espe.ind_calc = 'R' THEN
      lv_imp_base := ROUND(v_tipo_abon_dire.imp_movi/v_abon_dire_espe.val_porc_base) ;
      --lv_imp_impu := ROUND(lv_reg_ccc_carga_inter_sapfi.imp_movi*v_abon_dire_espe.imp_porc);
      --lv_imp_base := v_tipo_abon_dire.imp_movi - lv_imp_impu;
      lv_reg_ccc_carga_inter_sapfi.imp_movi := ROUND(lv_imp_base*v_abon_dire_espe.val_porc_cree/100) ;

    END CASE;

    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_tipo_abon_dire.fec_docu,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := '';
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_tipo_abon_dire.fec_docu;
    lv_reg_ccc_carga_inter_sapfi.zona :=  v_abon_dire_espe.val_tipo_asie;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;
  END LOOP;


  -- INSERTAR REGISTROS DE CARGOS Y ABONOS DIRECTOS --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote              num_lote,
     TRUNC(SYSDATE)           fec_cont,
     val_tipo_asie            val_tipo_asie_cont,
     cod_cuen_sicc            val_tipo_movi_cont,
     val_text_apunt           val_glosa,
     val_debe_habe            val_debe_habe,
     imp_movi                 imp_movi,
     TO_CHAR(SYSDATE,'MM')    val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY')  val_ejer_cont,
     NULL                     cod_cuent_corri_banc,
     fec_docu                 fec_pago_banc,
     fec_docu                 fec_valo,
     'ABDI'                   zona
   FROM
     (SELECT
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt,
       SUM(ROUND(ABS(mcc.imp_movi),2)) imp_movi
      FROM
       ccc_movim_cuent_corri mcc,
       ccc_param_conta_sapfi pc,
       ccc_proce cp,
       ccc_subpr su
      WHERE mcc.subp_oid_subp_crea = su.oid_subp
        AND su.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = su.cod_subp
        AND su.ind_abon_dire = 1
        AND pc.ind_acti = 1
        AND mcc.oid_movi_cc >= lv_oid_regi_inic
        AND mcc.oid_movi_cc <= lv_oid_regi_fina
      GROUP BY
       mcc.fec_docu,
       pc.val_tipo_asie,
       pc.cod_cuen_sicc,
       pc.val_debe_habe,
       pc.val_text_apunt)
   ORDER BY fec_docu DESC, cod_cuen_sicc DESC;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  from ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_adi,lv_num_lote,p_cod_usua);

   UPDATE ccc_movim_cuent_corri mcc
   SET
    mcc.fec_conta = trunc(SYSDATE),
    mcc.val_nume_lote_cont = lv_num_lote
   WHERE mcc.oid_movi_cc >  lv_oid_regi_inic
     AND mcc.oid_movi_cc < = lv_oid_regi_fina;

  ELSE

   RAISE e_no_gene_regi;

  END IF;


 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_ABIFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_AREFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_impo_dife                     NUMBER(12,2);
  lv_imp_apli                      NUMBER(12,2);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_nom_arch                      VARCHAR2(4000);
  lv_fec_proc                      DATE;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  gc_tipo_asie_cobr                CHAR(3):='ARE';
  gc_tipo_movi_debe                CHAR(3):='014';
  gc_tipo_movi_habe                CHAR(3):='015';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
  GROUP BY br.cod_cuen_corr_banc;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_are;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.fec_proc <= lv_fec_proc;

  IF lv_oid_regi_fina is NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  DELETE FROM ccc_carga_inter_sapfi;

  FOR v_banc IN c_banc LOOP

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
   lv_nom_arch := 'SAF1R_' || lv_num_lote ||  '.TXT';

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc) LOOP

    SELECT SUM(mb.imp_pago)
    INTO lv_imp_apli
      FROM
       ccc_movim_banca mb,
     ccc_numer_lote_banca_recep br
    WHERE br.num_lote = mb.num_lote
     AND br.cod_cuen_corr_banc = v_banc.cod_cuen_corr_banc
     AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
     AND mb.fec_proc = v_banc_fech.fec_proc
     AND mb.fec_pago = v_banc_fech.fec_pago;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_habe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'REGULARIZACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_habe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_cod_tipo_orig_regu;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_debe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'REGULARIZACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_cod_tipo_orig_regu;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;

 END LOOP;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  FROM ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   -- Validacion
 SELECT
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_debe) -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_habe)
  INTO lv_impo_dife
  FROM dual;

  IF lv_impo_dife <> 0 THEN
    RAISE e_dife_impo;
  END IF;

  UPDATE fin_contr_regis_progr f
  SET f.oid_ulti_regi_proc = lv_oid_regi_fina
  WHERE f.cod_modu = gc_cod_modu
    AND f.cod_prog = gc_cod_inte_sapf_are;

 FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_are,lv_num_lote,p_cod_usua);

  END IF;

 EXCEPTION

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro :=  'Existen diferencias Interface ARE';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_AREFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_AREFI(
  p_fec_proc_inic                  IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_impo_dife                     NUMBER(12,2);
  lv_imp_apli                      NUMBER(12,2);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_nom_arch                      VARCHAR2(4000);
  lv_fec_proc_inic                 DATE;
  lv_fec_proc_fina                 DATE;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  gc_tipo_asie_cobr                CHAR(3):='ARE';
  gc_tipo_movi_debe                CHAR(3):='014';
  gc_tipo_movi_habe                CHAR(3):='015';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
  GROUP BY br.cod_cuen_corr_banc;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc_inic := TO_DATE(p_fec_proc_inic,'DD/MM/YYYY');
  lv_fec_proc_fina := TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT MIN(mb.oid_movi_banc),MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_inic,lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.fec_proc >= lv_fec_proc_inic
    AND mb.fec_proc <= lv_fec_proc_fina;

  lv_oid_regi_inic := lv_oid_regi_inic - 1;

  DELETE FROM ccc_carga_inter_sapfi;

  FOR v_banc IN c_banc LOOP

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
   lv_nom_arch := 'SAF1R_' || lv_num_lote ||  '.TXT';

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc) LOOP

    SELECT SUM(mb.imp_pago)
    INTO lv_imp_apli
      FROM
       ccc_movim_banca mb,
     ccc_numer_lote_banca_recep br
    WHERE br.num_lote = mb.num_lote
     AND br.cod_cuen_corr_banc = v_banc.cod_cuen_corr_banc
     AND br.cod_tipo_orig = gc_cod_tipo_orig_regu
     AND mb.fec_proc = v_banc_fech.fec_proc
     AND mb.fec_pago = v_banc_fech.fec_pago;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_habe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'REGULARIZACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_habe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_cod_tipo_orig_regu;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_debe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'REGULARIZACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_cod_tipo_orig_regu;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;

 END LOOP;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  FROM ccc_carga_inter_sapfi
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   -- Validacion
 SELECT
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_debe) -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_habe)
  INTO lv_impo_dife
  FROM dual;

  IF lv_impo_dife <> 0 THEN
    RAISE e_dife_impo;
  END IF;

  UPDATE fin_contr_regis_progr f
  SET f.oid_ulti_regi_proc = lv_oid_regi_fina
  WHERE f.cod_modu = gc_cod_modu
    AND f.cod_prog = gc_cod_inte_sapf_are;

 FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_are,lv_num_lote,p_cod_usua);

  END IF;

 EXCEPTION

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro :=  'Existen diferencias Interface ARE';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_AREFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_EREFI(
  p_fec_proc                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_impo_dife                     NUMBER(15,2);
  lv_imp_apli                      NUMBER(15,2);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_nom_arch                      VARCHAR2(4000);
  lv_fec_proc                      DATE;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  gc_tipo_asie_cobr                CHAR(3):='ERE';
  gc_tipo_movi_debe                CHAR(3):='013';
  gc_tipo_movi_habe                CHAR(3):='011';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND mb.ind_elim = 1
  GROUP BY br.cod_cuen_corr_banc;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND mb.ind_elim = 1
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_ere;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.fec_proc <= lv_fec_proc;

  IF lv_oid_regi_fina is NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  DELETE FROM ccc_carga_inter_sapfi;

  FOR v_banc IN c_banc LOOP

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
   lv_nom_arch := 'SAF1R_' || lv_num_lote ||  '.TXT';

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc) LOOP

    SELECT SUM(mb.imp_pago)
    INTO lv_imp_apli
      FROM
     ccc_movim_banca mb,
     ccc_numer_lote_banca_recep br
    WHERE br.num_lote = mb.num_lote
     AND br.cod_cuen_corr_banc = v_banc.cod_cuen_corr_banc
     AND mb.fec_proc = v_banc_fech.fec_proc
     AND mb.fec_pago = v_banc_fech.fec_pago
     AND mb.ind_elim = 1;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_habe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'ELIMINACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_habe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_tipo_asie_cobr;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_debe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'ELIMINACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_tipo_asie_cobr;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;

 END LOOP;

 SELECT COUNT(*)
 INTO lv_ind_gene_arch
 FROM ccc_carga_inter_sapfi
 WHERE num_lote = lv_num_lote;

 IF lv_ind_gene_arch > 0 THEN

  -- Valdacion
  SELECT
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_debe) -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_habe)
  INTO lv_impo_dife
  FROM dual;

  IF lv_impo_dife <> 0 THEN
    RAISE e_dife_impo;
  END IF;

  UPDATE fin_contr_regis_progr f
  SET f.oid_ulti_regi_proc = lv_oid_regi_fina
  WHERE f.cod_modu = gc_cod_modu
    AND f.cod_prog = gc_cod_inte_sapf_ere;

 FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_ere,lv_num_lote,p_cod_usua);

 END IF;

 EXCEPTION

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro :=  'Existen diferencias Interface ERE';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_EREFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_EREFI(
  p_fec_proc_inic                  IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2,
  p_ind_erro                       OUT      VARCHAR2,
  p_des_erro                       OUT      VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_impo_dife                     NUMBER(15,2);
  lv_imp_apli                      NUMBER(15,2);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  lv_nom_arch                      VARCHAR2(4000);
  lv_fec_proc_inic                 DATE;
  lv_fec_proc_fina                 DATE;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

  gc_tipo_asie_cobr                CHAR(3):='ERE';
  gc_tipo_movi_debe                CHAR(3):='013';
  gc_tipo_movi_habe                CHAR(3):='011';

  lv_reg_ccc_carga_inter_sapfi     ccc_carga_inter_sapfi%ROWTYPE;

 CURSOR c_banc
  IS
  SELECT br.cod_cuen_corr_banc
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
    AND mb.ind_elim = 1
  GROUP BY br.cod_cuen_corr_banc;

  CURSOR c_banc_fech(
   pc_cod_banc                     ccc_cuent_corri_banca.cod_cc%TYPE)
  IS
  SELECT mb.fec_proc, mb.fec_pago
  FROM
   ccc_movim_banca mb,
   ccc_numer_lote_banca_recep br
  WHERE mb.num_lote = br.num_lote
    AND br.cod_cuen_corr_banc = pc_cod_banc
    AND mb.ind_elim = 1
    AND mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.oid_movi_banc <= lv_oid_regi_fina
  GROUP BY mb.fec_proc, mb.fec_pago;

 BEGIN

  lv_fec_proc_inic := TO_DATE(p_fec_proc_inic,'DD/MM/YYYY');
  lv_fec_proc_fina := TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT MIN(mb.oid_movi_banc),MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_inic,lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.fec_proc >= lv_fec_proc_inic
    AND mb.fec_proc <= lv_fec_proc_fina;

  lv_oid_regi_inic := lv_oid_regi_inic - 1;

  DELETE FROM ccc_carga_inter_sapfi;

  FOR v_banc IN c_banc LOOP

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
   lv_nom_arch := 'SAF1R_' || lv_num_lote ||  '.TXT';

   FOR v_banc_fech IN c_banc_fech (v_banc.cod_cuen_corr_banc) LOOP

    SELECT SUM(mb.imp_pago)
    INTO lv_imp_apli
      FROM
     ccc_movim_banca mb,
     ccc_numer_lote_banca_recep br
    WHERE br.num_lote = mb.num_lote
     AND br.cod_cuen_corr_banc = v_banc.cod_cuen_corr_banc
     AND mb.fec_proc = v_banc_fech.fec_proc
     AND mb.fec_pago = v_banc_fech.fec_pago
     AND mb.ind_elim = 1;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_habe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'ELIMINACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_habe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_tipo_asie_cobr;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

    lv_reg_ccc_carga_inter_sapfi.num_lote := lv_num_lote;
    lv_reg_ccc_carga_inter_sapfi.fec_cont := TRUNC(SYSDATE);
    lv_reg_ccc_carga_inter_sapfi.val_tipo_asie_cont := gc_tipo_asie_cobr;
    lv_reg_ccc_carga_inter_sapfi.val_tipo_movi_cont := gc_tipo_movi_debe;
    lv_reg_ccc_carga_inter_sapfi.val_glosa := 'ELIMINACION';
    lv_reg_ccc_carga_inter_sapfi.val_debe_habe := gc_ind_debe;
    lv_reg_ccc_carga_inter_sapfi.imp_movi := lv_imp_apli;
    lv_reg_ccc_carga_inter_sapfi.val_peri_cont := TO_CHAR(v_banc_fech.fec_proc,'MM');
    lv_reg_ccc_carga_inter_sapfi.val_ejer_cont := TO_CHAR(v_banc_fech.fec_proc,'YYYY');
    lv_reg_ccc_carga_inter_sapfi.cod_cuent_corri_banc := v_banc.cod_cuen_corr_banc;
    lv_reg_ccc_carga_inter_sapfi.fec_pago_banc := v_banc_fech.fec_pago;
    lv_reg_ccc_carga_inter_sapfi.fec_valo := v_banc_fech.fec_proc;
    lv_reg_ccc_carga_inter_sapfi.zona := gc_tipo_asie_cobr;

    INSERT INTO ccc_carga_inter_sapfi VALUES lv_reg_ccc_carga_inter_sapfi;

   END LOOP;

 END LOOP;

 SELECT COUNT(*)
 INTO lv_ind_gene_arch
 FROM ccc_carga_inter_sapfi
 WHERE num_lote = lv_num_lote;

 IF lv_ind_gene_arch > 0 THEN

  -- Valdacion
  SELECT
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_debe) -
  (SELECT SUM(g.imp_movi)
   FROM ccc_carga_inter_sapfi g
   WHERE g.val_debe_habe = gc_ind_habe)
  INTO lv_impo_dife
  FROM dual;

  IF lv_impo_dife <> 0 THEN
    RAISE e_dife_impo;
  END IF;

  UPDATE fin_contr_regis_progr f
  SET f.oid_ulti_regi_proc = lv_oid_regi_fina
  WHERE f.cod_modu = gc_cod_modu
    AND f.cod_prog = gc_cod_inte_sapf_ere;

 FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_ere,lv_num_lote,p_cod_usua);

 END IF;

 EXCEPTION

  WHEN e_dife_impo THEN
   p_ind_erro := 'S';
   p_des_erro :=  'Existen diferencias Interface ERE';

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN OTHERS THEN
   p_ind_erro := 'S';
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   p_des_erro :=   ' *** Error ' || SQLERRM  ||
                   ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                   ' en el programa ' || gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

 END INT_PR_CCC_GENER_INFOR_EREFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_APEFI(
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_cant_lote_pend                NUMBER(12);
  lv_num_lote                      VARCHAR2(15);
  lv_nom_arch                      VARCHAR2(4000);
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;


 BEGIN

  IF lv_cant_lote_pend > 0 THEN
   RAISE e_lote_pend;
  END IF;

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf_ape;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  -- INSERTAR REGISTROS DE PAGOS BANCARIOS AUTOMATICOS --
  INSERT INTO int_datos_sapfi_cobra
   SELECT
     lv_num_lote num_lote,
     TRUNC(SYSDATE) fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'REGULARIZACION' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(SYSDATE,'MM') val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_pago   fec_pago_banc,
     fec_proc   fec_valo
   FROM
     (SELECT
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_aplic_abono_cargo cad,
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_param_conta_banca pc
      WHERE cad.cmba_oid_movi_banc = mb.oid_movi_banc
        AND mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND cad.ind_pago_exce = 1
        AND pc.cod_esta_pago = 'X'
        AND cad.oid_movi_abon > lv_oid_regi_inic
        AND cad.oid_movi_abon <= lv_oid_regi_fina
      GROUP BY
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf_are,lv_num_lote,lv_nom_arch,p_cod_usua);

 END INT_PR_CCC_GENER_INFOR_APEFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER)
 IS

  lv_fec_proc                      VARCHAR2(10);
  lv_cod_erro                      VARCHAR2(150);
  lv_ind_tipo_sapf                 ccc_param_gener.val_para%TYPE;
  lv_ind_cont_prev                 ccc_param_gener.val_para%TYPE;

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

  lv_ind_erro                      VARCHAR2(1);
  lv_des_erro                      VARCHAR2(4000);

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_gene_info_sapf;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Interface SAPFI';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF p_fec_proc IS NULL THEN
   lv_fec_proc := TO_CHAR(TRUNC(SYSDATE-1),'DD/MM/YYYY');
  ELSE
   lv_fec_proc := p_fec_proc;
  END IF;

  lv_ind_tipo_sapf := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorTipoSAPFI');

  lv_ind_cont_prev := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorContaPreviaSAPFI');

  IF lv_ind_cont_prev = 'S' THEN
   gv_des_log:='Contabilizacion Previa SAPFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   CCC_PKG_PROCE.CCC_PR_CONTA_BANCA_PREVI_SAPFI;

  END IF;

  IF lv_ind_tipo_sapf = 'P' THEN

   gv_des_log:='   Generando Interface SAPFI Paises';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   BEGIN

    INT_PR_CCC_GENER_INFOR_SAPFI(gc_cod_modu,lv_log_cod_proc,lv_fec_proc,USER);

   EXCEPTION
    WHEN OTHERS THEN
     lv_ind_erro := 'S';

   END;

  ELSE

   gv_des_log:='   Interface COBFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_COBFI(lv_fec_proc,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface AREFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_AREFI(lv_fec_proc,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface EREFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_EREFI(lv_fec_proc,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface CDIFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_CDIFI(lv_fec_proc,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface ABIFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_ABIFI(lv_fec_proc,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

  END IF;

  BEGIN

   gv_des_log:='   Enviando el email de confirmación';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_sapf,1);

  EXCEPTION

   WHEN OTHERS THEN
    gv_des_log:='   Problemas con el envio de email de confirmación';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  END;

  gv_des_log:=' Finalizacion Interface SAPFI';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN e_inte_erro_cont THEN

   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_sapf,9);

   gv_des_log:='   Fin del proceso de manera erronea : ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

  WHEN OTHERS THEN

   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);

   lv_des_erro := ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' ||
                   gv_reco_trac.line_number ||
                             ' en el programa ' ||
                   gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

   INT_PR_CCC_GENER_EMAIL_CONFI(lv_log_cod_proc,9,lv_des_erro);

   gv_des_log:='!!!ERROR ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='El proceso ha terminado con errores.';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

 END INT_PR_CCC_GENER_INFOR_SAPFI;

 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_fec_proc_inic                  IN   VARCHAR2,
  p_fec_proc_fina                  IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER)
 IS

  lv_cod_erro                      VARCHAR2(150);
  lv_ind_tipo_sapf                 ccc_param_gener.val_para%TYPE;
  lv_ind_cont_prev                 ccc_param_gener.val_para%TYPE;

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

  lv_ind_erro                      VARCHAR2(1);
  lv_des_erro                      VARCHAR2(4000);

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_gene_info_sapf;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Interface SAPFI';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_ind_tipo_sapf := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorTipoSAPFI');

  lv_ind_cont_prev := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorContaPreviaSAPFI');

  IF lv_ind_cont_prev = 'S' THEN
   gv_des_log:='Contabilizacion Previa SAPFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   CCC_PKG_PROCE.CCC_PR_CONTA_BANCA_PREVI_SAPFI;

  END IF;

  IF lv_ind_tipo_sapf = 'P' THEN

   gv_des_log:='   Generando Interface SAPFI Paises';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   BEGIN

    INT_PR_CCC_GENER_INFOR_SAPFI(gc_cod_modu,lv_log_cod_proc,p_fec_proc_fina,USER);

   EXCEPTION
    WHEN OTHERS THEN
     lv_ind_erro := 'S';

   END;

  ELSE

   gv_des_log:='   Interface COBFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_COBFI(p_fec_proc_inic,p_fec_proc_fina,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface AREFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_AREFI(p_fec_proc_inic,p_fec_proc_fina,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface EREFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_EREFI(p_fec_proc_inic,p_fec_proc_fina,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface CDIFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_CDIFI(p_fec_proc_inic,p_fec_proc_fina,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

   gv_des_log:='   Interface ABIFI';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_INFOR_ABIFI(p_fec_proc_inic,p_fec_proc_fina,p_cod_usua,lv_ind_erro,lv_des_erro);

   IF lv_ind_erro = 'S' THEN
    RAISE e_inte_erro_cont;
   END IF;

  END IF;

  BEGIN

   gv_des_log:='   Enviando el email de confirmación';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_sapf,1);

  EXCEPTION

   WHEN OTHERS THEN
    gv_des_log:='   Problemas con el envio de email de confirmación';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  END;

  gv_des_log:=' Finalizacion Interface SAPFI';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN e_inte_erro_cont THEN

   INT_PR_CCC_GENER_EMAIL_CONFI(gc_cod_inte_sapf,9);

   gv_des_log:='   Fin del proceso de manera erronea : ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

  WHEN OTHERS THEN

   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);

   lv_des_erro := ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' ||
                   gv_reco_trac.line_number ||
                             ' en el programa ' ||
                   gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

   INT_PR_CCC_GENER_EMAIL_CONFI(lv_log_cod_proc,9,lv_des_erro);

   gv_des_log:='!!!ERROR ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='El proceso ha terminado con errores.';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

 END INT_PR_CCC_GENER_INFOR_SAPFI;

 PROCEDURE INT_PR_CCC_VALID_INFOR_SAPFI(
  p_oid_regi_inic                  IN   NUMBER,
  p_oid_regi_fina                  IN   NUMBER)
 IS

 BEGIN

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_exce = 0
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.imp_apli_exce IS NULL;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_inco = 0
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.imp_apli_inco IS NULL;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_cobr_exte = 0
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.imp_apli_cobr_exte IS NULL;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_cobr_pend_regu = 0
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.imp_apli_cobr_pend_regu IS NULL;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_fami_prot = 0
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.imp_apli_fami_prot IS NULL;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_cobr_pend_regu = mb.imp_pago
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.cod_iden_proc = gc_cod_iden_proc_erro;

  UPDATE ccc_movim_banca mb
  SET mb.imp_apli_clie = mb.imp_pago - (NVL(mb.imp_apli_exce,0) + NVL(mb.imp_apli_cobr_exte,0) + NVL(mb.imp_apli_inco,0) + NVL(mb.imp_apli_cobr_pend_regu,0) + NVL(mb.imp_apli_fami_prot,0))
  WHERE mb.oid_movi_banc > p_oid_regi_inic
    AND mb.oid_movi_banc <= p_oid_regi_fina
    AND mb.cod_iden_proc = gc_cod_iden_proc_proc;

 END INT_PR_CCC_VALID_INFOR_SAPFI;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Cobranza a SAPFI
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SAPFI_COBRA(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2,
  p_fec_proc_hast                  IN   VARCHAR2,
  p_num_lote                       IN   VARCHAR2)
 IS
   CURSOR c_interfaz(
      p_num_lote          VARCHAR2,
     p_oid_regi_inic     NUMBER,
     p_oid_regi_fina    NUMBER) IS
         WITH temp1 AS
          (SELECT  mb.pais_oid_pais, mb.soci_oid_soci,mb.fec_proc,mb.fec_pago, ccb.cod_cc, ccb.des_cc ,mb.subp_oid_marc_crea, mb.cod_iden_proc ,SUM(mb.imp_pago) importe
           FROM
                ccc_movim_banca mb,
                ccc_cuent_corri_banca ccb
           WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
           AND EXISTS (
             SELECT NULL
             FROM ccc_param_conta_banca pc,
                ccc_proce cp,
                  ccc_subpr cs
             WHERE cp.oid_proc = cs.ccpr_oid_proc
           AND pc.cod_proc = cp.cod_proc
           AND pc.cod_subp = cs.cod_subp
           AND pc.cod_esta_pago = mb.cod_iden_proc
             AND cs.oid_subp = mb.subp_oid_marc_crea )
           AND mb.oid_movi_banc > p_oid_regi_inic
          AND mb.oid_movi_banc <= p_oid_regi_fina
           HAVING SUM(mb.imp_pago) > 0
           GROUP BY mb.pais_oid_pais, mb.soci_oid_soci,mb.fec_proc, mb.fec_pago, ccb.cod_cc, ccb.des_cc ,mb.subp_oid_marc_crea, mb.cod_iden_proc)
           SELECT
               p_num_lote ,--Numero de lote
              pais.cod_pais,       --Codigo pais
              TO_CHAR(movim.fec_proc,'YYYYMMDD'),  --Fecha contable
              socie.cod_soci,       --Codigo sociedad
              NULL,                 --Codigo canal
              NULL,                 --Codigo acceso
              NULL,                 --Codigo subacceso
              NULL,                 --Tipo periodo comercial
              NULL,                 --Periodo comercial
              NULL,                 --Ejercicio Comercial
              'CR',                 --Tipo de asiento
              param.VAL_COD_CUENT_SAP,  -- Tipo Movimiento
              NULL,                                   --Documento de identidad RUC transportista
              SUBSTR(TRIM('COBRANZA ' || UPPER(movim.des_cc)),1,40),   --Glosa/Descripcion apunte
              param.val_debe_habe,                    --Indicador (D)ebe/(H)aber
              NULL,   --Marca del producto
              NULL,   --Grupo de articulo
              NULL,   --Negocio del producto
              NULL,   --Tipo de oferta
              NULL,   --Ciclo de vida
              TRIM(REPLACE(TO_CHAR(NVL(movim.importe,0),'00000000000000D99'),'.')),  --Importe
              moned.cod_mone,          --Codigo Moneda
              TO_CHAR(SYSDATE,'MM'),   --Periodo contable
              TO_CHAR(SYSDATE,'YYYY'), --Ejercicio contable
              NULL,                    --Fecha documento
              movim.cod_cc,            --Codigo Banco
              TO_CHAR(movim.fec_pago,'YYYYMMDD'), --Fecha pago en bancos
              TO_CHAR(movim.fec_proc,'YYYYMMDD'), --Fecha valor
              NULL,--Numero comprobante
              NULL,--Sucursal
              NULL --Zona
           FROM
              seg_pais pais,
              seg_moned moned,
              seg_socie socie,
              ccc_param_conta_banca  param ,
              ccc_proce cp,
              ccc_subpr cs,
              temp1 movim
           WHERE pais.oid_pais=socie.pais_oid_pais
           AND pais.mone_oid_mone=moned.oid_mone
           AND pais.oid_pais=movim.pais_oid_pais
           AND socie.oid_soci=movim.soci_oid_soci
           AND cp.oid_proc = cs.ccpr_oid_proc
           AND cp.cod_proc = param.cod_proc
           AND cs.cod_subp = param.cod_subp
           AND cs.oid_subp = movim.subp_oid_marc_crea
           AND param.cod_esta_pago = movim.cod_iden_proc
           ORDER BY movim.fec_proc DESC,param.VAL_COD_CUENT_SAP DESC, movim.cod_cc DESC , movim.fec_pago DESC;

        TYPE interfazrec IS RECORD(
           numeroLote                          VARCHAR2(12),
           codigoPais                            VARCHAR2(3),
           fechaContable                      VARCHAR2(8),
           codigoSociedad                    VARCHAR2(4),
           codigoCanal                           VARCHAR2(3),
           codigoAcceso                        VARCHAR2(3),
           codigoSubacceso                  VARCHAR2(3),
           tipoPeriodoComercial           VARCHAR2(5),
           periodoComercial                 VARCHAR2(5),
           ejercicioComercial               VARCHAR2(5),
           tipoAsiento                          VARCHAR2(5),
           tipoMovimiento                    VARCHAR2(5),
           documentoTransportista     VARCHAR2(30),
           glosaContable                       VARCHAR2(40),
           indicadorDebeHaber            VARCHAR2(1),
           marcaProducto                     VARCHAR2(5),
           grupoArticulo                       VARCHAR2(5),
           negocioProducto                   VARCHAR2(5),
           tipoOferta                            VARCHAR2(5),
           cicloVida                               VARCHAR2(5),
           importe                                 VARCHAR2(16),
           codigoMoneda                      VARCHAR2(3),
           periodoContable                   VARCHAR2(5),
           ejercicioContable                 VARCHAR2(5),
           fechaDocumento                   VARCHAR2(8),
           codigoBanco                          VARCHAR2(6),
           fechaPago                              VARCHAR2(8),
           fechaValor                             VARCHAR2(8),
           numeroComprobante             VARCHAR2(5),
           sucursal                                 VARCHAR2(5),
           zona                                       VARCHAR2(5));

       TYPE interfazrectab IS TABLE OF interfazrec;
       interfazrecord interfazrectab;

       /* Variables usadas para la generacion del archivo de texto */
       lsDirTempo                             BAS_INTER.DIR_TEMP%TYPE;
       W_FILAS                               NUMBER := 1000 ;
       v_handle                                  UTL_FILE.FILE_TYPE;
       lsLinea                                      VARCHAR2(1000);
       lsNombreArchivo                     VARCHAR2(50);
       lbAbrirUtlFile                          BOOLEAN;
       lv_oid_regi_inic                       NUMBER(12);
       lv_oid_regi_fina                    NUMBER(12);
       lv_cont_lote_pend                    NUMBER(12);
       lc_cod_iden_proc_tran           CONSTANT VARCHAR2(1):='T';

       e_exis_lote_pend_liqu               EXCEPTION;


    BEGIN

       /* Validando Contador de Lote */
       SELECT rp.oid_ulti_regi_proc
       INTO lv_oid_regi_inic
       FROM fin_contr_regis_progr rp
       WHERE rp.cod_pais = p_cod_pais
       AND rp.cod_modu = p_cod_sist
       AND rp.cod_prog = p_cod_inte;

       SELECT MAX(mb.oid_movi_banc)
       INTO lv_oid_regi_fina
       FROM ccc_movim_banca mb
       WHERE mb.val_nume_lote_cont IS NULL
       AND mb.fec_cont IS NULL
       AND mb.fec_proc<= TO_DATE(p_fec_proc_hast,'DD/MM/YYYY')
       AND mb.oid_movi_banc > lv_oid_regi_inic;

      -- Validando que no existan lotes pendientes de procesar --
      SELECT COUNT(*)
      INTO lv_cont_lote_pend
      FROM ccc_movim_banca mb
      WHERE mb.oid_movi_banc >= lv_oid_regi_inic
      AND mb.oid_movi_banc < = lv_oid_regi_fina
      AND mb.cod_iden_proc = lc_cod_iden_proc_tran;

      IF lv_cont_lote_pend>0 THEN
         RAISE e_exis_lote_pend_liqu;
      END IF;

     /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(p_num_lote,lv_oid_regi_inic,lv_oid_regi_fina);
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).numeroLote                    ||';'||
                              interfazRecord(x).codigoPais                    ||';'||
                              interfazRecord(x).fechaContable            ||';'||
                              interfazRecord(x).codigoSociedad                ||';'||
                              interfazRecord(x).codigoCanal                   ||';'||
                              interfazRecord(x).codigoAcceso                  ||';'||
                              interfazRecord(x).codigoSubacceso               ||';'||
                              interfazRecord(x).tipoPeriodoComercial          ||';'||
                              interfazRecord(x).periodoComercial              ||';'||
                              interfazRecord(x).ejercicioComercial            ||';'||
                              interfazRecord(x).tipoAsiento                   ||';'||
                              interfazRecord(x).tipoMovimiento                ||';'||
                              interfazRecord(x).documentoTransportista        ||';'||
                              interfazRecord(x).glosaContable                 ||';'||
                              interfazRecord(x).indicadorDebeHaber            ||';'||
                              interfazRecord(x).marcaProducto                 ||';'||
                              interfazRecord(x).grupoArticulo                 ||';'||
                              interfazRecord(x).negocioProducto               ||';'||
                              interfazRecord(x).tipoOferta                    ||';'||
                              interfazRecord(x).cicloVida                     ||';'||
                              interfazRecord(x).importe                       ||';'||
                              interfazRecord(x).codigoMoneda                  ||';'||
                              interfazRecord(x).periodoContable               ||';'||
                              interfazRecord(x).ejercicioContable             ||';'||
                              interfazRecord(x).fechaDocumento                ||';'||
                              interfazRecord(x).codigoBanco                   ||';'||
                              interfazRecord(x).fechaPago                     ||';'||
                              interfazRecord(x).fechaValor                    ||';'||
                              interfazRecord(x).numeroComprobante             ||';'||
                              interfazRecord(x).sucursal                      ||';'||
                              interfazRecord(x).zona                          ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;

        UPDATE ccc_movim_banca mb
        SET mb.fec_cont= trunc(SYSDATE),
                mb.val_nume_lote_cont=p_num_lote
        WHERE mb.oid_movi_banc > = lv_oid_regi_inic
        AND mb.oid_movi_banc < = lv_oid_regi_fina;

       UPDATE fin_contr_regis_progr rp
       SET rp.oid_ante_regi_proc = lv_oid_regi_inic,
               rp.oid_ulti_regi_proc =   lv_oid_regi_fina
       WHERE rp.cod_modu = p_cod_sist
       AND rp.cod_prog = p_cod_inte;

    RETURN;

  EXCEPTION
    WHEN e_exis_lote_pend_liqu THEN
       raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SAPFI_COBRA: ' ||
                               'Existen Lote Pendientes de Liquidar');

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SAPFI_COBRA: ' ||
                               ls_sqlerrm);

   END INT_PR_CCC_ENVIA_SAPFI_COBRA;

 /***************************************************************************
  Descripcion       : Procedimiento que envia la Cobranza a SAPFI
  Fecha Creacion    : 13/05/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 PROCEDURE INT_PR_CCC_ENVIA_SAPFI_BANCA(
  p_cod_pais                       IN   VARCHAR2,
  p_cod_sist                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_nomb_arch                      IN   VARCHAR2,
  p_fec_proc_hast                  IN   VARCHAR2,
  p_num_lote                       IN   VARCHAR2)
 IS
   CURSOR c_interfaz(
      p_num_lote          VARCHAR2) IS
           SELECT
              sf.num_lote ,--Numero de lote
              sf.cod_pais,       --Codigo pais
              TO_CHAR(sf.fec_cont,'YYYYMMDD'),  --Fecha contable
              sf.cod_soci,       --Codigo sociedad
              NULL,                 --Codigo canal
              NULL,                 --Codigo acceso
              NULL,                 --Codigo subacceso
              NULL,                 --Tipo periodo comercial
              NULL,                 --Periodo comercial
              NULL,                 --Ejercicio Comercial
              sf.tip_asie_cont,                 --Tipo de asiento
              sf.tip_movi_cont,  -- Tipo Movimiento
              NULL,                                   --Documento de identidad RUC transportista
              SUBSTR(TRIM(sf.val_glos_cont),1,40),   --Glosa/Descripcion apunte
              sf.val_debe_habe,                    --Indicador (D)ebe/(H)aber
              NULL,   --Marca del producto
              NULL,   --Grupo de articulo
              NULL,   --Negocio del producto
              NULL,   --Tipo de oferta
              NULL,   --Ciclo de vida
              TRIM(REPLACE(TO_CHAR(NVL(sf.val_imp_movi,0),'00000000000000D99'),'.')),  --Importe
              sf.cod_mone,          --Codigo Moneda
              sf.val_peri_cont,   --Periodo contable
              sf.val_ejer_cont, --Ejercicio contable
              NULL,                    --Fecha documento
              sf.val_codi_banc,            --Codigo Banco
              TO_CHAR(sf.fec_pago_banc,'YYYYMMDD'), --Fecha pago en bancos
              TO_CHAR(sf.fec_proc,'YYYYMMDD'), --Fecha valor
              NULL,--Numero comprobante
              NULL,--Sucursal
              NULL --Zona
           FROM ccc_datos_sapfi_cobra sf
           WHERE sf.num_lote=p_num_lote
           ORDER BY fec_proc DESC,tip_movi_cont DESC, val_codi_banc DESC , fec_pago_banc DESC;

        TYPE interfazrec IS RECORD(
           numeroLote                          VARCHAR2(12),
           codigoPais                            VARCHAR2(3),
           fechaContable                      VARCHAR2(8),
           codigoSociedad                    VARCHAR2(4),
           codigoCanal                           VARCHAR2(3),
           codigoAcceso                        VARCHAR2(3),
           codigoSubacceso                  VARCHAR2(3),
           tipoPeriodoComercial           VARCHAR2(5),
           periodoComercial                 VARCHAR2(5),
           ejercicioComercial               VARCHAR2(5),
           tipoAsiento                          VARCHAR2(5),
           tipoMovimiento                    VARCHAR2(5),
           documentoTransportista     VARCHAR2(30),
           glosaContable                       VARCHAR2(40),
           indicadorDebeHaber            VARCHAR2(1),
           marcaProducto                     VARCHAR2(5),
           grupoArticulo                       VARCHAR2(5),
           negocioProducto                   VARCHAR2(5),
           tipoOferta                            VARCHAR2(5),
           cicloVida                               VARCHAR2(5),
           importe                                 VARCHAR2(16),
           codigoMoneda                      VARCHAR2(3),
           periodoContable                   VARCHAR2(5),
           ejercicioContable                 VARCHAR2(5),
           fechaDocumento                   VARCHAR2(8),
           codigoBanco                          VARCHAR2(6),
           fechaPago                              VARCHAR2(8),
           fechaValor                             VARCHAR2(8),
           numeroComprobante             VARCHAR2(5),
           sucursal                                 VARCHAR2(5),
           zona                                       VARCHAR2(5));

       TYPE interfazrectab IS TABLE OF interfazrec;
       interfazrecord interfazrectab;

       /* Variables usadas para la generacion del archivo de texto */
       lsDirTempo                             BAS_INTER.DIR_TEMP%TYPE;
       W_FILAS                               NUMBER := 1000 ;
       v_handle                                  UTL_FILE.FILE_TYPE;
       lsLinea                                      VARCHAR2(1000);
       lsNombreArchivo                     VARCHAR2(50);
       lbAbrirUtlFile                          BOOLEAN;
       lv_cant_regi_proce                NUMBER(12);
       lv_cont_lote_pend                    NUMBER(12);

       e_noex_regi_proce                    EXCEPTION;
       e_exis_lote_pend_liqu               EXCEPTION;


    BEGIN

       --Validando que existen registrso a procesar --
       SELECT COUNT(*)
       INTO lv_cant_regi_proce
       FROM ccc_movim_banca mb
       WHERE mb.val_nume_lote_cont IS NULL
       AND mb.fec_cont IS NULL
       AND mb.fec_proc<= TO_DATE(p_fec_proc_hast,'DD/MM/YYYY');

        IF lv_cant_regi_proce=0 THEN
         RAISE e_noex_regi_proce;
      END IF;

      -- Validando que no existan lotes pendientes de procesar --
      SELECT COUNT(*)
      INTO lv_cont_lote_pend
      FROM ccc_movim_banca mb
       WHERE mb.val_nume_lote_cont IS NULL
       AND mb.fec_cont IS NULL
       AND mb.fec_proc<= TO_DATE(p_fec_proc_hast,'DD/MM/YYYY');

      IF lv_cont_lote_pend>0 THEN
         RAISE e_exis_lote_pend_liqu;
      END IF;

      -- Generando la informacion para la interface --
      INSERT INTO ccc_datos_sapfi_cobra
         SELECT
            TO_CHAR(SYSDATE,'YYYYMMDDMISS') num_Lote,
            cod_pais,
            TRUNC(SYSDATE) fec_cont,
            cod_soci,
            val_tipo_asie tip_asie,
            val_cod_cuent_sap,
            'COBRANZA BANCOS' val_glosa,
            val_debe_habe,
            imp_movi,
            cod_mone,
            TO_CHAR(SYSDATE,'MM') VAL_PERI_CONT,
            TO_CHAR(SYSDATE,'YYYY') VAL_EJER_CONT,
            CASE
               WHEN ind_cuen_cod_cban = 0
                  THEN
                     cod_cc
                   ELSE
                      NULL
              END cod_cc,
              fec_pago,
              fec_proc
         FROM
            (SELECT
                sp.cod_pais,
                ss.cod_soci,
                sm.cod_mone,
                mb.fec_proc,
                mb.fec_pago,
                pc.val_tipo_asie,
                pc.val_cod_cuent_sap,
                pc.val_debe_habe,
                pc.ind_cuen_cod_cban,
                ccb.cod_cc,
                SUM(MB.IMP_PAGO) IMP_MOVI
              FROM
                 ccc_movim_banca mb,
                 seg_pais sp,
                 seg_socie ss,
                 seg_moned  sm,
                 ccc_cuent_corri_banca ccb,
                 ccc_proce cp,
                 ccc_subpr cs,
                 ccc_param_conta_banca pc
             WHERE mb.pais_oid_pais=sp.oid_pais
             AND mb.soci_oid_soci = ss.oid_soci
             AND sp.mone_oid_mone = sm.oid_mone
             AND mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
             AND mb.subp_oid_marc_crea = cs.oid_subp
             AND cs.ccpr_oid_proc = cp.oid_proc
             AND cp.cod_proc = pc.cod_proc
             AND pc.cod_subp = cs.cod_subp
             AND mb.cod_iden_proc = pc.cod_esta_pago
             AND mb.fec_proc <= TO_DATE(p_fec_proc_hast,'DD/MM/YYYY')
             AND mb.val_nume_lote_cont IS NULL
             AND mb.fec_cont IS NULL
             GROUP BY
                sp.cod_pais,
                ss.cod_soci,
                sm.cod_mone,
                mb.fec_proc,
                mb.fec_pago,
                pc.val_tipo_asie,
                pc.val_cod_cuent_sap,
                pc.val_debe_habe,
                pc.ind_cuen_cod_cban,
                ccb.cod_cc)
             ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

        UPDATE ccc_datos_sapfi_cobra ccc
        SET ccc.fec_pago_banc = ccc.fec_proc
        WHERE TO_CHAR(ccc.fec_pago_banc,'MM')<>TO_CHAR(ccc.fec_proc,'MM');

     /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(p_num_lote);
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(p_Cod_pais, p_Cod_sist, p_Cod_Inte,
                  p_Nomb_Arch, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).numeroLote                    ||';'||
                              interfazRecord(x).codigoPais                    ||';'||
                              interfazRecord(x).fechaContable            ||';'||
                              interfazRecord(x).codigoSociedad                ||';'||
                              interfazRecord(x).codigoCanal                   ||';'||
                              interfazRecord(x).codigoAcceso                  ||';'||
                              interfazRecord(x).codigoSubacceso               ||';'||
                              interfazRecord(x).tipoPeriodoComercial          ||';'||
                              interfazRecord(x).periodoComercial              ||';'||
                              interfazRecord(x).ejercicioComercial            ||';'||
                              interfazRecord(x).tipoAsiento                   ||';'||
                              interfazRecord(x).tipoMovimiento                ||';'||
                              interfazRecord(x).documentoTransportista        ||';'||
                              interfazRecord(x).glosaContable                 ||';'||
                              interfazRecord(x).indicadorDebeHaber            ||';'||
                              interfazRecord(x).marcaProducto                 ||';'||
                              interfazRecord(x).grupoArticulo                 ||';'||
                              interfazRecord(x).negocioProducto               ||';'||
                              interfazRecord(x).tipoOferta                    ||';'||
                              interfazRecord(x).cicloVida                     ||';'||
                              interfazRecord(x).importe                       ||';'||
                              interfazRecord(x).codigoMoneda                  ||';'||
                              interfazRecord(x).periodoContable               ||';'||
                              interfazRecord(x).ejercicioContable             ||';'||
                              interfazRecord(x).fechaDocumento                ||';'||
                              interfazRecord(x).codigoBanco                   ||';'||
                              interfazRecord(x).fechaPago                     ||';'||
                              interfazRecord(x).fechaValor                    ||';'||
                              interfazRecord(x).numeroComprobante             ||';'||
                              interfazRecord(x).sucursal                      ||';'||
                              interfazRecord(x).zona                          ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

        IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, p_Nomb_Arch, lsNombreArchivo);
        END IF;

        -- Actualizando el Numero de Lote Contable --
        UPDATE ccc_movim_banca mb
        SET mb.fec_cont= trunc(SYSDATE),
                mb.val_nume_lote_cont=p_num_lote
        WHERE mb.val_nume_lote_cont IS NULL
       AND mb.fec_cont IS NULL
       AND mb.fec_proc<= TO_DATE(p_fec_proc_hast,'DD/MM/YYYY');

    RETURN;

  EXCEPTION
    WHEN e_noex_regi_proce THEN
       raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SAPFI_COBRA: ' ||
                               'No existen registros a procesar');

    WHEN e_exis_lote_pend_liqu THEN
       raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SAPFI_COBRA: ' ||
                               'Existen Lote Pendientes de Liquidar');

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_CCC_ENVIA_SAPFI_COBRA: ' ||
                               ls_sqlerrm);

 END INT_PR_CCC_ENVIA_SAPFI_BANCA;

 PROCEDURE INT_PR_CCC_GENER_INFOR_SAPFI(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_cant_lote_pend                NUMBER(12);
  lv_impo_dife                     NUMBER(12,2);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;


 BEGIN

  SELECT COUNT(1)
  INTO lv_cant_lote_pend
  FROM
   ccc_movim_banca mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
    AND mb.fec_proc <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')
    AND mb.cod_iden_proc = gc_cod_iden_proc_tran;

  IF lv_cant_lote_pend > 0 THEN
   RAISE e_lote_pend;
  END IF;

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = p_cod_modu
    AND rp.cod_prog = p_cod_inte;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.fec_proc <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY');

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM int_datos_sapfi_cobra;

  -- INSERTAR REGISTROS DE PAGOS BANCARIOS AUTOMATICOS --
  INSERT INTO int_datos_sapfi_cobra
   SELECT
     lv_num_lote num_lote,
     TRUNC(SYSDATE) fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZAS BANCOS ' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(SYSDATE,'MM') val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_pago   fec_pago_banc,
     fec_proc   fec_valo
   FROM
     (SELECT
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       NVL(ccb.cod_banc_sapf,ccb.cod_cc) cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_auto
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
        AND ccb.ind_inte_sapf = 1
      GROUP BY
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       ccb.cod_banc_sapf)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

   -- INSERTAR REGISTROS DE PAGOS BANCARIOS MANUALES --
  INSERT INTO int_datos_sapfi_cobra
   SELECT
     lv_num_lote num_lote,
     TRUNC(SYSDATE) fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZAS BANCOS ' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(SYSDATE,'MM') val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_pago   fec_pago_banc,
     fec_proc   fec_valo
   FROM
     (SELECT
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       NVL(ccb.cod_banc_sapf,ccb.cod_cc) cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_manu
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
        AND ccb.ind_inte_sapf = 1
      GROUP BY
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       ccb.cod_banc_sapf)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

  -- INSERTAR REGISTROS DE PAGOS BANCARIOS REGULARIZADOS --
  INSERT INTO int_datos_sapfi_cobra
   SELECT
     lv_num_lote num_lote,
     TRUNC(SYSDATE) fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZAS BANCOS ' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(SYSDATE,'MM') val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_proc   fec_pago_banc,
     fec_proc   fec_valo
   FROM
     (SELECT
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       NVL(ccb.cod_banc_sapf,ccb.cod_cc) cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_regu
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
        AND ccb.ind_inte_sapf = 1
      GROUP BY
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       ccb.cod_banc_sapf)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

  SELECT COUNT(*)
  INTO lv_ind_gene_arch
  from int_datos_sapfi_cobra
  WHERE num_lote = lv_num_lote;

  IF lv_ind_gene_arch > 0 THEN

   /* Cuendo se reciben pagos de otros meses se
     cambia al mes actual */
   UPDATE int_datos_sapfi_cobra ccc
     SET ccc.fec_pago_banc = ccc.fec_valo
    WHERE TO_CHAR(ccc.fec_pago_banc,'MM')<>TO_CHAR(ccc.fec_valo,'MM');

   SELECT sicc - sapfi
   INTO lv_impo_dife
   FROM
    (SELECT
     (SELECT SUM(sa.imp_movi)/2
      FROM int_datos_sapfi_cobra sa
      WHERE sa.fec_valo <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')) sapfi,
     (SELECT SUM(mb.imp_pago)
      FROM ccc_movim_banca mb,
           ccc_cuent_corri_banca ccb
        WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND ccb.ind_inte_sapf = 1
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina) sicc
    FROM dual);

   IF lv_impo_dife > 0 THEN
    RAISE e_dife_impo;
   END IF;

   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

   UPDATE ccc_movim_banca mb
   SET
    mb.fec_cont= trunc(SYSDATE),
    mb.val_nume_lote_cont= lv_num_lote
   WHERE mb.oid_movi_banc >  lv_oid_regi_inic
     AND mb.oid_movi_banc < = lv_oid_regi_fina;

   UPDATE fin_contr_regis_progr rp
   SET
    rp.oid_ante_regi_proc = lv_oid_regi_inic,
    rp.oid_ulti_regi_proc =   lv_oid_regi_fina
   WHERE rp.cod_modu = p_cod_modu
     AND rp.cod_prog = p_cod_inte;

  ELSE

   RAISE e_no_gene_regi;

  END IF;

 EXCEPTION

   WHEN e_lote_pend THEN
    raise_application_error(-20123,
                               'Existen lotes bancarios pendientes de procesar');

  WHEN e_dife_impo THEN
   raise_application_error(-20123,
                               'Existen diferencias');

  WHEN e_no_gene_regi THEN
   raise_application_error(-20123,
                               'No genera registros');

  WHEN OTHERS THEN
    gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
    raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END INT_PR_CCC_GENER_INFOR_SAPFI;

 PROCEDURE INT_PR_CCC_REPRO_INFOR_SAPFI(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_fec_proc_inic                  IN       VARCHAR2,
  p_fec_proc_fina                  IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_cant_lote_pend                NUMBER(12);
  lv_impo_dife                     NUMBER(12,2);
  lv_num_lote                      VARCHAR2(15);
  e_lote_pend                      EXCEPTION;

 BEGIN


  SELECT COUNT(1)
  INTO lv_cant_lote_pend
  FROM
   ccc_movim_banca mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
    AND mb.fec_proc >= TO_DATE(p_fec_proc_inic,'DD/MM/YYYY')
    AND mb.fec_proc <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')
    AND mb.cod_iden_proc = gc_cod_iden_proc_tran;

  IF lv_cant_lote_pend>0 THEN
   RAISE e_lote_pend;
  END IF;

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  -- INSERTAR REGISTROS EN LA ENTIDAD DE SAPFI --
  INSERT INTO int_datos_sapfi_cobra
   SELECT
     lv_num_lote num_lote,
     TRUNC(SYSDATE) fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZA BANCOS' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(SYSDATE,'MM') val_peri_cont,
     TO_CHAR(SYSDATE,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_pago   fec_pago_banc,
     fec_proc   fec_valo
   FROM
     (SELECT
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND mb.fec_proc >= TO_DATE(p_fec_proc_inic,'DD/MM/YYYY')
        AND mb.fec_proc <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')
      GROUP BY
       mb.fec_proc,
       mb.fec_pago,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC, fec_pago DESC;

  IF SQL%ROWCOUNT > 0 THEN

   /* Cuendo se reciben pagos de otros meses se
     cambia al mes actual */
   UPDATE int_datos_sapfi_cobra ccc
      SET ccc.fec_pago_banc = ccc.fec_valo
    WHERE TO_CHAR(ccc.fec_pago_banc,'MM')<>TO_CHAR(ccc.fec_valo,'MM');

   SELECT sicc - sapfi
   INTO lv_impo_dife
     FROM
     (SELECT
      (SELECT SUM(sa.imp_movi)/2
       FROM int_datos_sapfi_cobra sa
       WHERE sa.fec_valo >= TO_DATE(p_fec_proc_inic,'DD/MM/YYYY')
       AND sa.fec_valo <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')) sapfi,
      (SELECT SUM(mb.imp_pago)
       FROM ccc_movim_banca mb
       WHERE mb.fec_proc >= TO_DATE(p_fec_proc_inic,'DD/MM/YYYY')
         AND mb.fec_proc <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')) sicc
     FROM dual);


   IF lv_impo_dife > 0 THEN
    RAISE e_dife_impo;
   END IF;


   FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

  ELSE

   RAISE e_no_gene_regi;

  END IF;

 EXCEPTION

  WHEN e_lote_pend THEN
   raise_application_error(-20123,
                               'Existen lotes bancarios pendientes de procesar');

  WHEN e_dife_impo THEN
   raise_application_error(-20123,
                               'Existen diferencias');

  WHEN e_no_gene_regi THEN
   raise_application_error(-20123,
                               'No genera registros');

  WHEN OTHERS THEN
   raise_application_error(-20123,
                              'ERROR BACKTRACE INT_PR_CCC_GENER_INFOR_SAPFI: ' ||
                               DBMS_UTILITY.format_error_backtrace);

 END INT_PR_CCC_REPRO_INFOR_SAPFI;

 PROCEDURE INT_PR_CCC_DATAM_CARGO_CONSU(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_lim_regi                       IN       NUMBER,
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_oid_regi_inic                 fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;
  lv_oid_regi_fina                 fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_imp_gene_inte                 NUMBER(15,2);
  lv_imp_cuen_corr                 NUMBER(15,2);

 BEGIN

  -- DAT-301 --
  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = p_cod_modu
    AND rp.cod_prog = p_cod_inte;

  IF p_lim_regi IS NULL THEN

  SELECT MAX(mcc.oid_movi_cc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.oid_movi_cc > lv_oid_regi_inic;

  ELSE

   lv_oid_regi_fina := lv_oid_regi_inic + p_lim_regi;

  END IF;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;

  SELECT SUM(mcc.imp_movi)
  INTO lv_imp_cuen_corr
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.imp_movi > 0
   AND mcc.oid_movi_cc > lv_oid_regi_inic
   AND mcc.oid_movi_cc <= lv_oid_regi_fina;

  DELETE FROM ccc_int_datam_cargo_consu;

  INSERT INTO ccc_int_datam_cargo_consu
   SELECT *
   FROM
    (SELECT
      mcc.oid_movi_cc num_iden_carg,
      mc.cod_clie,
      tca.cod_tipo_carg_datm,
      mcc.num_orde_cuot,
      TO_CHAR(mcc.fec_docu,'YYYYMMDD') fec_docu,
      spco.cod_peri camp_orig,
      to_char(psc.val_nume_soli) num_bole_desp,
      TO_CHAR(mcc.fec_venc,'YYYYMMDD') fec_venc,
      mcc.imp_movi,
      spc.cod_peri camp_pago
     FROM
      ccc_movim_cuent_corri mcc,
      ped_solic_cabec psc,
      mae_clien mc,
      cra_perio cp,
      seg_perio_corpo spc,
      cra_perio cpo,
      seg_perio_corpo spco,
      ccc_int_datam_homol_cargo tca,
      ccc_proce pr,
      ccc_subpr su
     WHERE mcc.clie_oid_clie = mc.oid_clie
       AND mcc.soca_oid_soli_cabe = psc.oid_soli_cabe
       AND mcc.perd_oid_peri = cp.oid_peri
       AND cp.peri_oid_peri = spc.oid_peri
       AND psc.perd_oid_peri = cpo.oid_peri
       AND cpo.peri_oid_peri = spco.oid_peri
       AND mcc.subp_oid_subp_crea = su.oid_subp
       AND pr.oid_proc = su.ccpr_oid_proc
       AND pr.cod_proc = tca.cod_proc
       AND su.cod_subp = tca.cod_subp
       AND mcc.subp_oid_subp_crea = 2001
       AND mcc.imp_movi > 0
       AND mcc.oid_movi_cc > lv_oid_regi_inic
       AND mcc.oid_movi_cc <= lv_oid_regi_fina)
    UNION ALL
     (SELECT
       mcc.oid_movi_cc num_iden_carg,
       mc.cod_clie,
       tca.cod_tipo_carg_datm,
       mcc.num_orde_cuot,
       TO_CHAR(mcc.fec_docu,'YYYYMMDD') fec_docu,
       spc.cod_peri camp_orig,
       '           ' num_bole_desp,
       TO_CHAR(mcc.fec_venc,'YYYYMMDD') fec_venc,
       mcc.imp_movi,
       spc.cod_peri camp_pago
      FROM
       ccc_movim_cuent_corri mcc,
       mae_clien mc,
       cra_perio cp,
       seg_perio_corpo spc,
       ccc_int_datam_homol_cargo tca,
       ccc_proce pr,
       ccc_subpr su
     WHERE mcc.clie_oid_clie = mc.oid_clie
       AND mcc.perd_oid_peri = cp.oid_peri
       AND cp.peri_oid_peri = spc.oid_peri
       AND mcc.subp_oid_subp_crea = su.oid_subp
       AND pr.oid_proc = su.ccpr_oid_proc
       AND pr.cod_proc = tca.cod_proc
       AND su.cod_subp = tca.cod_subp
       AND mcc.subp_oid_subp_crea <> 2001
       AND mcc.imp_movi > 0
       AND mcc.oid_movi_cc > lv_oid_regi_inic
       AND mcc.oid_movi_cc <= lv_oid_regi_fina);

    --Validacion --
    SELECT SUM(car.imp_carg)
    INTO lv_imp_gene_inte
    FROM ccc_int_datam_cargo_consu car;

    IF lv_imp_gene_inte = lv_imp_cuen_corr THEN

     FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
     FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC('CCC','DAT-1',lv_num_lote,p_cod_usua);

     UPDATE fin_contr_regis_progr rp
     SET
      rp.oid_ante_regi_proc = lv_oid_regi_inic,
      rp.oid_ulti_regi_proc =   lv_oid_regi_fina
     WHERE rp.cod_modu = p_cod_modu
       AND rp.cod_prog = p_cod_inte;

    ELSE

     RAISE e_dife_impo;

    END IF;

 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

  WHEN e_dife_impo THEN
   raise_application_error(-20123,
                               'Existen diferencias');

 END INT_PR_CCC_DATAM_CARGO_CONSU;

 PROCEDURE INT_PR_CCC_DATAM_ABONO_CONSU(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_lim_regi                       IN       NUMBER,
  p_cod_usua                       IN       VARCHAR2)

 IS

  lv_oid_regi_inic                 fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;
  lv_oid_regi_fina                 fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_cod_peri_inic                 seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_inic                 cra_perio.oid_peri%TYPE;

 BEGIN

  -- DAT-302 --
  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = p_cod_modu
    AND rp.cod_prog = p_cod_inte;

  IF p_lim_regi IS NULL THEN

  SELECT MAX(abo.oid_movi_abon)
  INTO lv_oid_regi_fina
  FROM ccc_aplic_abono_cargo abo
  WHERE abo.oid_movi_abon > lv_oid_regi_inic;

  ELSE

   lv_oid_regi_fina := lv_oid_regi_inic + p_lim_regi;
  END IF;

  IF lv_oid_regi_fina IS NULL THEN
   RAISE e_no_gene_regi;
  END IF;

 lv_cod_peri_inic := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPeriodoInicioDatamart');
 lv_oid_peri_inic := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lv_cod_peri_inic);

 DELETE FROM ccc_int_datam_abono_consu;

 INSERT INTO ccc_int_datam_abono_consu
  SELECT
   abo.oid_movi_abon num_iden_abon,
   CASE
    WHEN abo.ind_tipo_abon_nmon = 0 THEN
     '001'
    ELSE
    '002'
   END cod_tipo_abon_datm,
   mcc.oid_movi_cc num_iden_carg,
   tca.cod_tipo_carg_datm,
   mcc.num_orde_cuot,
   mc.cod_clie,
   CASE
    WHEN  abo.ind_tipo_abon_nmon = 0 THEN
     TO_CHAR(TRUNC(abo.fec_apli),'YYYYMMDD')
   ELSE '        '
   END fec_pago_banc,
   TO_CHAR(TRUNC(abo.fec_proc),'YYYYMMDD') fec_proc,
   ABS(abo.imp_abon) imp_pago,
   (SELECT MIN(spc.cod_peri)
   FROM cra_perio cp,
       seg_perio_corpo spc
   WHERE cp.peri_oid_peri = spc.oid_peri
     AND cp.fec_inic <= abo.fec_apli
     AND cp.fec_fina >= abo.fec_apli) camp_abon,
    abo.imp_pend_carg,
    CASE
     WHEN mcc.perd_oid_peri >= lv_oid_peri_inic THEN 0
      ELSE 1
    END ind_deud_ante
 FROM
  ccc_aplic_abono_cargo abo,
  ccc_movim_cuent_corri mcc,
  ccc_int_datam_homol_cargo tca,
  ccc_proce pr,
  ccc_subpr su,
  mae_clien mc
 WHERE mcc.clie_oid_clie = mc.oid_clie
   AND abo.clie_oid_clie = mc.oid_clie
   AND mcc.subp_oid_subp_crea = su.oid_subp
   AND abo.mvcc_oid_movi_carg = mcc.oid_movi_cc
   AND abo.clie_oid_clie = mcc.clie_oid_clie
   AND pr.oid_proc = su.ccpr_oid_proc
   AND pr.cod_proc = tca.cod_proc
   AND su.cod_subp = tca.cod_subp
   AND abo.oid_movi_abon >= lv_oid_regi_inic
   AND abo.oid_movi_abon <= lv_oid_regi_fina;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

  UPDATE fin_contr_regis_progr rp
  SET
   rp.oid_ante_regi_proc = lv_oid_regi_inic,
   rp.oid_ulti_regi_proc =   lv_oid_regi_fina
  WHERE rp.cod_modu = p_cod_modu
    AND rp.cod_prog = p_cod_inte;

 EXCEPTION

  WHEN e_no_gene_regi THEN
   NULL;

 END INT_PR_CCC_DATAM_ABONO_CONSU;


 PROCEDURE INT_PR_CCC_DATAM_FECHA_CRONO(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  lv_cod_peri := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

  -- Facturacion --
  INSERT INTO ccc_int_datam_fecha_cierr_zona
   SELECT
    spc.cod_peri,
    zz.cod_zona,
    'F',
    to_char(cr.fec_inic,'DD/MM/YYYY')
   FROM
    cra_crono cr,
    cra_perio cp,
    seg_perio_corpo spc,
    zon_zona zz,
    cra_activ ca
   WHERE cr.zzon_oid_zona = zz.oid_zona
     AND cr.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND cr.cact_oid_acti = ca.oid_acti
     AND ca.cod_acti = 'FA'
     AND spc.cod_peri = lv_cod_peri;

   -- Cierre --
   INSERT INTO ccc_int_datam_fecha_cierr_zona
    SELECT DISTINCT cod_peri, zon_cerr, 'C',fec_cier
    FROM (
        SELECT
          spc.cod_peri,
              c.OID_CTRL, c.fec_cier, c.val_proc_ejec, c.val_resu_proc, c.perd_oid_peri,c.tcie_oid_tipo_cier,
               fac.cod_tipo_cier, reg.OID_REGI,reg.cod_regi,  zz.cod_zona zon_cerr, reg1.DES_REGI DES_REGION_ZONA_CERRADA,
               reg.des_regi, c.fec_ulti_actu, c.oid_ctrl
          FROM fac_contr_cierr c,
               own_comun.fac_tipos_cierr fac,
               zon_regio reg,
               zon_regio reg1,
               zon_zona zz,
               cra_perio cp,
               own_comun.seg_perio_corpo spc
         WHERE 1=1
           AND fac.oid_tipo_cier(+) = c.tcie_oid_tipo_cier
           AND reg.oid_regi(+) = c.zorg_oid_regi
           AND c.zzon_oid_zona = zz.oid_zona(+)
           AND zz.zorg_oid_regi = reg1.oid_regi(+)
           AND c.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = lv_cod_peri
           AND fac.oid_tipo_cier IN (2)  ---1=Region, 2= Zonas, 3=Campa?a
         ORDER BY FEC_ULTI_ACTU DESC
        )
  ORDER BY 1,2,4 ASC;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

 END INT_PR_CCC_DATAM_FECHA_CRONO;

 PROCEDURE INT_PR_CCC_DATAM_TIPOS_CARGO(
  p_cod_modu                       IN       VARCHAR2,
  p_cod_inte                       IN       VARCHAR2,
  p_cod_usua                       IN       VARCHAR2)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;

 BEGIN

  -- DAT-4 --
  INSERT INTO ccc_int_datam_tipos_cargo
   SELECT
    c.cod_tipo_carg_datm,
    c.des_tipo_carg_datm
   FROM ccc_int_datam_homol_cargo c;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

 END INT_PR_CCC_DATAM_TIPOS_CARGO;

 PROCEDURE INT_PR_CCC_DATAM_TIPOS_ABONO(
  p_cod_modu                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;

 BEGIN

  -- DAT-5 --

  INSERT INTO ccc_int_datam_tipos_abono
  SELECT
   a.cod_tipo_abon_datm,
   a.des_tipo_abon_datm
  FROM ccc_int_datam_homol_abono a;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(p_cod_modu,p_cod_inte,lv_num_lote,p_cod_usua);

 END INT_PR_CCC_DATAM_TIPOS_ABONO;

 PROCEDURE INT_PR_CCC_GENER_INFOR_DATAM(
  p_cod_modu                       IN   VARCHAR2,
  p_cod_inte                       IN   VARCHAR2,
  p_cod_usua                       IN   VARCHAR2)
 IS

 BEGIN

  INT_PR_CCC_DATAM_CARGO_CONSU(p_cod_modu,gc_cod_inte_data_carg,NULL,p_cod_usua);
  INT_PR_CCC_DATAM_ABONO_CONSU(p_cod_modu,gc_cod_inte_data_abon,NULL,p_cod_usua);
  INT_PR_CCC_DATAM_FECHA_CRONO(p_cod_modu,gc_cod_inte_data_fech_cier,p_cod_usua);
  INT_PR_CCC_DATAM_TIPOS_CARGO(p_cod_modu,gc_cod_inte_data_tipo_carg,p_cod_usua);
  INT_PR_CCC_DATAM_TIPOS_ABONO(p_cod_modu,gc_cod_inte_data_tipo_abon,p_cod_usua);

 EXCEPTION
  WHEN OTHERS THEN
    gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
    raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END INT_PR_CCC_GENER_INFOR_DATAM;

 PROCEDURE INT_PR_CCC_GENER_SALDO_REPDO(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE   DEFAULT USER)
 IS

  -- Cabecera
  lc_tipo_regi_cabe                CHAR(2):='01';
  lc_nom_empr                      CHAR(32):= RPAD('TRANSBEL',32,' ');
  lc_cant_regi                     CHAR(15);
  lc_mont_regi                     CHAR(15);
  lc_val_fech_hora                 CHAR(19):= RPAD(TO_CHAR(SYSDATE,'YYYY-MM-DD hh:mm:ss'),19,' ');
  lc_fill_cabe                     CHAR(67):= RPAD(' ',67,' ');
  lc_line_cabe                     CHAR(189);

  -- Detalle
  lc_tipo_regi_deta                CHAR(2):='02';
  lv_cod_clie                      CHAR(35);
  lv_imp_bala                      CHAR(14);
  lc_imp_itbs_impu                 CHAR(14):='00000000000.00';
  lc_imp_otro_impu                 CHAR(14):='00000000000.00';
  lc_val_fech_venc                 CHAR(19):=RPAD(TO_CHAR(SYSDATE + 21,'YYYY-MM-DD hh:mm:ss'),19,' ');
  lv_nom_clie                      CHAR(25);
  lv_num_docu_iden                 CHAR(11);
  lv_line_deta                     CHAR(134);

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dir_ensa_mail                 fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_mail                 fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_exte_arch_mail                bas_exten_archi.des_exar%TYPE;
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(9):=0;
  lv_mont_tota                     NUMBER(15,2):=0;
  lv_cod_inte_sald_redo            fin_proce_modul.cod_inte%TYPE :='SALRD';

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);

 CURSOR c_deta
 IS
  SELECT
   s.cod_clie,
   s.nom_clie,
   s.num_docu_iden,
   s.imp_deud_tota
  FROM ccc_int_gener_saldo_repdo s;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := lv_cod_inte_sald_redo;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Archivo Saldos RD';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  DELETE FROM ccc_int_gener_saldo_repdo;

  INSERT INTO ccc_int_gener_saldo_repdo
   SELECT
    mc.cod_clie,
    mc.val_ape1 || '/' || mc.val_ape2 || '/' || mc.val_nom1 nom_clie,
    mci.num_docu_iden,
    NVL(mc.sal_deud_ante,0)
   FROM
    mae_clien mc,
    mae_clien_ident mci
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.sal_deud_ante > 0;

  SELECT
   COUNT(*),
   SUM(imp_deud_tota)
  INTO
   lv_cant_regi,
   lv_mont_tota
  FROM ccc_int_gener_saldo_repdo;

  lc_cant_regi := SUBSTR(LPAD(lv_cant_regi,15,'0'),1,15);
  lc_mont_regi := SUBSTR(LPAD(TRIM(TO_CHAR(lv_mont_tota,'000000000000.00')),15,'0'),1,15);

  gv_des_log:='Cantidad de registros :  ' || lv_cant_regi;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Monto Total :  ' || lv_mont_tota;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT ea.des_exar
  INTO lv_exte_arch_mail
  FROM fin_param_inter_cabec pc,
       bas_exten_archi ea
  WHERE pc.cod_exar = ea.cod_exar
  AND pc.cod_modu = gc_cod_modu
  AND pc.cod_inte = lv_cod_inte_sald_redo;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  gv_des_log:='Numero de Lote : ' || lv_num_lote;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_nom_arch_mail := 'FACTURAS_TRANSBEL_' || lv_num_lote || '.' || lv_exte_arch_mail;

  gv_des_log:='Nombre de Archivo : ' || lv_nom_arch_mail;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_sald_redo,USER,lv_num_lote,lv_dir_ensa_mail,lv_nom_arch_mail,lv_handle);

  gv_des_log:='Generando archivo';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lc_line_cabe := lc_tipo_regi_cabe ||
                  lc_nom_empr ||
                  lc_cant_regi ||
                  lc_mont_regi ||
                  lc_val_fech_hora ||
                  lc_fill_cabe;

  utl_file.put_line(lv_handle,lc_line_cabe);

  FOR v_deta in c_deta LOOP

   lv_cod_clie :=  SUBSTR(RPAD(v_deta.cod_clie,35,' '),1,35);
   lv_nom_clie :=  SUBSTR(RPAD(v_deta.nom_clie,25,' '),1,25);
   lv_imp_bala :=  SUBSTR(LPAD(TRIM(TO_CHAR(v_deta.imp_deud_tota,'00000000000.00')),14,'0'),1,14);
   lv_num_docu_iden := SUBSTR(LPAD(v_deta.num_docu_iden,11,'0'),1,11);

   lv_line_deta := lc_tipo_regi_deta ||
                  lv_cod_clie ||
                  lv_imp_bala ||
                  lc_imp_itbs_impu ||
                  lc_imp_otro_impu ||
                  lc_val_fech_venc ||
                  lv_nom_clie ||
                  lv_num_docu_iden;

   utl_file.put_line(lv_handle,lv_line_deta);

  END LOOP;

  utl_file.fclose(lv_handle);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_sald_redo,lv_num_lote,lv_cant_regi,'2',lv_desc_erro);

  gv_des_log:='Archivo generado correctamente';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Enviando email de confirmacion';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INT_PR_CCC_GENER_EMAIL_CONFI(lv_cod_inte_sald_redo,1);

  gv_des_log:='Fin del proceso de manera correcta';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION
  WHEN OTHERS THEN

   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);

   lv_des_erro := ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' ||
                   gv_reco_trac.line_number ||
                             ' en el programa ' ||
                   gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

   INT_PR_CCC_GENER_EMAIL_CONFI(lv_log_cod_proc,9,lv_des_erro);

   gv_des_log:='!!!ERROR ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='El proceso ha terminado con errores.';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

 END INT_PR_CCC_GENER_SALDO_REPDO;

 PROCEDURE INT_PR_CCC_GENER_BUROC_REPDO
 IS

  -- Detalle
  lc_tipo_enti CHAR(1):='I';
  lv_cod_clie  CHAR(7);
  lc_val_fill_1 CHAR(2):='||';
  lv_nom_clie VARCHAR2(250);
  lc_val_fill_2 CHAR(1):='|';
  lv_num_cedu CHAR(9);
  lc_val_fill_3 CHAR(4):='||||';
  lc_val_tele_fijo CHAR(10);
  lc_val_tele_trab CHAR(10);
  lc_val_tele_movi CHAR(10);
  lc_val_fill_3 CHAR(4):='|||';

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dir_ensa_mail                 fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_mail                 fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_exte_arch_mail                bas_exten_archi.des_exar%TYPE;
  lv_imp_bala                      VARCHAR2(500);
  lv_num_docu_iden                 VARCHAR2(10);
  lv_line_deta                     VARCHAR2(400);
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(9):=0;
  lv_mont_tota                     NUMBER(10):=0;
  lv_cod_inte_sald_redo            fin_proce_modul.cod_inte%TYPE:='SALDOS_REPDO';
  lv_sum_tota_vali                 NUMBER(12,2);
  lv_can_tota_vali                 NUMBER(12);

 CURSOR c_deta
 IS
  SELECT
   s.cod_clie,
   s.nom_clie,
   s.num_docu_iden,
   s.imp_deud_tota
  FROM ccc_int_gener_saldo_repdo s;

 BEGIN

  SELECT ea.des_exar
  INTO lv_exte_arch_mail
  FROM fin_param_inter_cabec pc,
       bas_exten_archi ea
  WHERE pc.cod_exar = ea.cod_exar
  AND pc.cod_modu = gc_cod_modu
  AND pc.cod_inte = lv_cod_inte_sald_redo;

  lv_nom_arch_mail := 'MCT5013D.' || lv_exte_arch_mail;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_sald_redo,USER,lv_num_lote,lv_dir_ensa_mail,lv_nom_arch_mail,lv_handle);

  FOR v_deta in c_deta LOOP

   lv_cod_clie :=  v_deta.cod_clie;
   lv_nom_clie :=  v_deta.nom_clie;
   lv_imp_bala :=  SUBSTR(LPAD(v_deta.imp_deud_tota,10,'0'),1,10);
   lv_num_docu_iden := SUBSTR(LPAD(v_deta.num_docu_iden,9,'0'),1,10);

   /*
   lv_line_deta := lc_tipo_regi_deta ||
                  lv_cod_clie ||
                  lv_imp_bala ||
                  lc_imp_itbs_impu ||
                  lc_imp_otro_impu ||
                  lc_val_fech_venc ||
                  lv_nom_clie ||
                  lv_num_docu_iden;
   */
   utl_file.put_line(lv_handle,lv_line_deta);

  END LOOP;

  utl_file.fclose(lv_handle);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_sald_redo,lv_num_lote,lv_cant_regi,'2',lv_desc_erro);

 END INT_PR_CCC_GENER_BUROC_REPDO;

 PROCEDURE INT_CCC_PR_CARGO_EFTGR_MAIL
 IS

  -- Cabecera
  lc_iden_cabe                     CHAR(1):='1';
  lv_val_fech                      CHAR(8):=TO_CHAR(SYSDATE-1,'YYYYMMDD');
  lv_val_hora                      CHAR(6):=TO_CHAR(SYSDATE-1,'HHMMSS');
  lv_val_rell_18                   CHAR(33):= RPAD('0',18,'0');
  lv_line_cabe                     CHAR(49);

  -- Detalle
  lv_iden_deta                     CHAR(1):='2';
  lv_val_rut                       CHAR(9);
  lv_cod_peri                      CHAR(6);
  lv_num_iden_cuot                 CHAR(8);
  lv_line_deta                     CHAR(49);

  -- Pie
  lc_iden_pie                      CONSTANT CHAR(1):='3';
  lv_val_rell_13                    CHAR(13):=RPAD('0',13,'0');
  lv_line_pie                      CHAR(49);

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dir_ensa_mail                 fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_mail                 fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_exte_arch_mail                bas_exten_archi.des_exar%TYPE;
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(9):=0;
  lv_mont_tota                     NUMBER(10):=0;
  lv_cod_inte_mail                 fin_proce_modul.cod_inte%TYPE:='ZZ-MAIL';
  lv_sum_tota_vali                 NUMBER(12,2);
  lv_can_tota_vali                 NUMBER(12);

 CURSOR c_cons
 IS
  SELECT
   mc.oid_clie,
   mci.num_docu_iden,
   ROWNUM num_cupo,
   zz.cod_zona,
   zs.cod_secc,
   zt.cod_terr,   
    ROUND(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(
    mc.oid_clie),0)  val_deud
  FROM
   mae_clien mc,
   mae_clien_ident mci,
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_terri zt,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr
  WHERE mc.oid_clie = mci.clie_oid_clie
    AND mci.val_iden_docu_prin = 1
    AND mc.oid_clie = mcua.clie_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi
    AND zta.terr_oid_terr = zt.oid_terr
    AND mc.sal_deud_ante > 0;

  lv_fec_fact            bas_ctrl_fact.fec_proc%type;

 BEGIN

  SELECT bcf.fec_proc
  INTO lv_fec_fact
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  lv_val_fech :=TO_CHAR(lv_fec_fact,'YYYYMMDD');

 SELECT
   SUM( ROUND(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(
    mc.oid_clie),0)),
--    SUM(mc.sal_deud_ante),
    COUNT(*)
 INTO lv_sum_tota_vali, lv_can_tota_vali
  FROM
   mae_clien mc,
   mae_clien_ident mci,
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_terri zt,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr
  WHERE mc.oid_clie = mci.clie_oid_clie
    AND mci.val_iden_docu_prin = 1
    AND mc.oid_clie = mcua.clie_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zta.terr_oid_terr = zt.oid_terr
    AND zz.zorg_oid_regi = zr.oid_regi
    AND mc.sal_deud_ante > 0;

  --- Archivo Email ---

  lv_mont_tota := 0;
  lv_cant_regi := 0;

  SELECT ea.des_exar
  INTO lv_exte_arch_mail
  FROM fin_param_inter_cabec pc,
       bas_exten_archi ea
  WHERE pc.cod_exar = ea.cod_exar
  AND pc.cod_modu = gc_cod_modu
  AND pc.cod_inte = lv_cod_inte_mail;

  lv_nom_arch_mail := 'ZZ' || TO_CHAR(lv_fec_fact,'YYMMDD') || '.' || lv_exte_arch_mail;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_mail,USER,lv_num_lote,lv_dir_ensa_mail,lv_nom_arch_mail,lv_handle);

  lv_line_cabe := lc_iden_cabe || lv_val_fech || lv_val_hora || lv_val_rell_18;
  utl_file.put_line(lv_handle,lv_line_cabe);

  FOR v_cons in c_cons LOOP

   lv_val_rut := LPAD(v_cons.num_docu_iden,9,'0');

   SELECT
    RPAD(MAX(spc.cod_peri),6,' '),SUBSTR(LPAD(MAX(mcc.oid_movi_cc),12,'0'),5,12)
   INTO lv_cod_peri, lv_num_iden_cuot
   FROM
    ccc_movim_cuent_corri mcc,
    cra_perio cp,
    seg_perio_corpo spc
   WHERE mcc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND mcc.clie_oid_clie = v_cons.oid_clie;

   lv_line_deta := lv_iden_deta ||
                  lv_val_rut ||
                  SUBSTR(LPAD(lv_num_iden_cuot,8,'0'),1,8)  ||
                  SUBSTR(LPAD(v_cons.num_cupo,5,'0'),1,5) ||
                  SUBSTR(LPAD(v_cons.val_deud,10,'0'),1,10);

   utl_file.put_line(lv_handle,lv_line_deta);

   lv_mont_tota := lv_mont_tota + v_cons.val_deud;
   lv_cant_regi := lv_cant_regi + 1;

  END LOOP;

  lv_line_pie := lc_iden_pie ||
                 SUBSTR(LPAD(lv_cant_regi,9,'0'),1,9) ||
                 lv_val_rell_13 ||
                 SUBSTR(RPAD(lv_mont_tota,10,'0'),1,10);
  utl_file.put_line(lv_handle,lv_line_pie);

  utl_file.fclose(lv_handle);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_mail,lv_num_lote,lv_cant_regi,'2',lv_desc_erro);

 END INT_CCC_PR_CARGO_EFTGR_MAIL;

 PROCEDURE INT_CCC_PR_CARGO_EFTGR_FTP
 IS

  -- Cabecera
  lc_iden_cabe                     CHAR(1):='1';
  lv_val_fech                      CHAR(8):=TO_CHAR(SYSDATE-1,'YYYYMMDD');
  lv_val_hora                      CHAR(6):=TO_CHAR(SYSDATE-1,'HHMMSS');
  lv_val_rell_33                   CHAR(33):= RPAD('0',33,'0');
  lv_line_cabe                     CHAR(49);

  -- Detalle
  lv_iden_deta                     CHAR(1):='2';
  lv_val_rut                       CHAR(9);
  lv_cod_peri                      CHAR(6);
  lv_num_iden_cuot                 CHAR(8);
  lv_line_deta                     CHAR(49);

  -- Pie
  lc_iden_pie                      CONSTANT CHAR(1):='3';
  lv_val_rell_13                   CHAR(13):=RPAD('0',13,'0');
  lv_val_rell_15                   CHAR(15):=RPAD('0',15,'0');
  lv_line_pie                      CHAR(49);

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_ftp                  fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_exte_arch_ftp                 bas_exten_archi.des_exar%TYPE;
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(9):=0;
  lv_mont_tota                     NUMBER(10):=0;
  lv_cod_inte_ftp                  fin_proce_modul.cod_inte%TYPE:='ZZ-FTP';
  lv_sum_tota_vali                 NUMBER(12,2);
  lv_can_tota_vali                 NUMBER(12);

 CURSOR c_cons
 IS
  SELECT
   mc.oid_clie,
   mci.num_docu_iden,
   ROWNUM num_cupo,
   zz.cod_zona,
   zs.cod_secc,
   zt.cod_terr,  
   ROUND(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(mc.oid_clie),0)  val_deud
  FROM
   mae_clien mc,
   mae_clien_ident mci,
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_terri zt,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr
  WHERE mc.oid_clie = mci.clie_oid_clie
    AND mci.val_iden_docu_prin = 1
    AND mc.oid_clie = mcua.clie_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi
    AND zta.terr_oid_terr = zt.oid_terr
    AND mc.sal_deud_ante > 0;

  lv_fec_fact bas_ctrl_fact.fec_proc%type;
 BEGIN

   SELECT bcf.fec_proc
  INTO lv_fec_fact
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  lv_val_fech := TO_CHAR(lv_fec_fact,'YYYYMMDD');

 SELECT
   SUM(ROUND(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(mc.oid_clie),0)),
    --SUM(mc.sal_deud_ante),
    COUNT(*)
 INTO lv_sum_tota_vali, lv_can_tota_vali
  FROM
   mae_clien mc,
   mae_clien_ident mci,
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_terri zt,
   zon_secci zs,
   zon_zona zz,
   zon_regio zr
  WHERE mc.oid_clie = mci.clie_oid_clie
    AND mci.val_iden_docu_prin = 1
    AND mc.oid_clie = mcua.clie_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zta.terr_oid_terr = zt.oid_terr
    AND zz.zorg_oid_regi = zr.oid_regi
    AND mc.sal_deud_ante > 0;

  SELECT ea.des_exar
  INTO lv_exte_arch_ftp
  FROM fin_param_inter_cabec pc,
       bas_exten_archi ea
  WHERE pc.cod_exar = ea.cod_exar
  AND pc.cod_modu = gc_cod_modu
  AND pc.cod_inte = lv_cod_inte_ftp;

  lv_nom_arch_ftp := 'ZZ' || TO_CHAR(lv_fec_fact,'YYMMDD') || '.' || lv_exte_arch_ftp;

  -- Archivo FTP --
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_ftp,USER,lv_num_lote,lv_dir_ensa,lv_nom_arch_ftp,lv_handle);

  lv_line_cabe := lc_iden_cabe || lv_val_fech || lv_val_hora || lv_val_rell_33;
  utl_file.put_line(lv_handle,lv_line_cabe);

  FOR v_cons in c_cons LOOP

   lv_val_rut := LPAD(v_cons.num_docu_iden,9,'0');

   SELECT
    RPAD(MAX(spc.cod_peri),6,' '), SUBSTR(LPAD(MAX(mcc.oid_movi_cc),12,'0'),5,12)
   INTO lv_cod_peri, lv_num_iden_cuot
   FROM
    ccc_movim_cuent_corri mcc,
    cra_perio cp,
    seg_perio_corpo spc
   WHERE mcc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND mcc.clie_oid_clie = v_cons.oid_clie;

   lv_line_deta := lv_iden_deta ||
                  lv_val_rut ||
                  SUBSTR(LPAD(lv_num_iden_cuot,8,'0'),1,8)  ||
                  SUBSTR(LPAD(v_cons.num_cupo,5,'0'),1,5) ||
                  SUBSTR(LPAD(v_cons.val_deud,10,'0'),1,10) ||
                  SUBSTR(v_cons.cod_zona,1,4) || SUBSTR(v_cons.cod_secc,1,1) || SUBSTR(LPAD(v_cons.cod_terr,4,'0'),1,4) ||
                  lv_cod_peri;

   BEGIN
    insert into CCC_CARGO_EFTGR_FTP(NUM_DOCU_IDEN,COD_ZONA,COD_SECC,COD_TERR,COD_PERI,VAL_DEUD,FEC_PROC)
    values ( SUBSTR(LPAD(lv_num_iden_cuot,8,'0'),1,8),
             SUBSTR(v_cons.cod_zona,1,4),
             SUBSTR(v_cons.cod_secc,1,1),
             SUBSTR(LPAD(v_cons.cod_terr,4,'0'),1,4),
             lv_cod_peri,
             SUBSTR(LPAD(v_cons.val_deud,10,'0'),1,10),
             sysdate);
   EXCEPTION
     WHEN dup_val_on_index THEN
       update CCC_CARGO_EFTGR_FTP
              set COD_ZONA = SUBSTR(v_cons.cod_zona,1,4)
                  ,COD_SECC = SUBSTR(v_cons.cod_secc,1,1)
                  ,COD_TERR = SUBSTR(LPAD(v_cons.cod_terr,4,'0'),1,4)
                  ,COD_PERI = lv_cod_peri
                  ,VAL_DEUD = SUBSTR(LPAD(v_cons.val_deud,10,'0'),1,10)
                  , FEC_PROC = sysdate
       where NUM_DOCU_IDEN = SUBSTR(LPAD(lv_num_iden_cuot,8,'0'),1,8);

   END;

   utl_file.put_line(lv_handle,lv_line_deta);

   lv_mont_tota := lv_mont_tota + v_cons.val_deud;
   lv_cant_regi := lv_cant_regi + 1;

  END LOOP;

  lv_line_pie := lc_iden_pie ||
                 SUBSTR(LPAD(lv_cant_regi,9,'0'),1,9) ||
                 lv_val_rell_13 ||
                 SUBSTR(RPAD(lv_mont_tota,10,'0'),1,10) ||
                 lv_val_rell_15;

  dbms_output.put_line('Monto Total : ' || lv_mont_tota);
  dbms_output.put_line('Monto Total Validacion: ' || lv_sum_tota_vali);

  utl_file.put_line(lv_handle,lv_line_pie);

  utl_file.fclose(lv_handle);
  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lv_cod_inte_ftp,lv_num_lote,lv_cant_regi,'2',lv_desc_erro);

 END INT_CCC_PR_CARGO_EFTGR_FTP;

 PROCEDURE INT_PR_CCC_GENER_SAPFI_CHILE(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER)
 IS

  lv_oid_regi_inic                 NUMBER(12);
  lv_oid_regi_fina                 NUMBER(12);
  lv_fec_proc_fina                 DATE;
  lv_cant_lote_pend                NUMBER(12);
  lv_num_lote                      VARCHAR2(15);
  lv_ind_gene_arch                 NUMBER(12):=0;
  e_lote_pend                      EXCEPTION;
  e_dife_impo                      EXCEPTION;
  e_no_gene_regi                   EXCEPTION;

 BEGIN

  lv_fec_proc_fina := TRUNC(SYSDATE-1);

  SELECT COUNT(1)
  INTO lv_cant_lote_pend
  FROM
   ccc_movim_banca mb,
   ccc_cuent_corri_banca ccb
  WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
    AND mb.fec_proc <= lv_fec_proc_fina
    AND mb.cod_iden_proc = gc_cod_iden_proc_tran;

  IF lv_cant_lote_pend > 0 THEN
   RAISE e_lote_pend;
  END IF;

  SELECT rp.oid_ulti_regi_proc
  INTO lv_oid_regi_inic
  FROM fin_contr_regis_progr rp
  WHERE rp.cod_modu = gc_cod_modu
    AND rp.cod_prog = gc_cod_inte_sapf;

  SELECT MAX(mb.oid_movi_banc)
  INTO lv_oid_regi_fina
  FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc > lv_oid_regi_inic
    AND mb.fec_proc <= lv_fec_proc_fina;

  -- INSERTAR REGISTROS DE PAGOS BANCARIOS AUTOMATICOS --

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  DELETE FROM ccc_carga_inter_sapfi;

  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote num_lote,
     fec_proc fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZA INTERFACE' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(fec_proc,'MM') val_peri_cont,
     TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_proc   fec_pago_banc,
     fec_proc   fec_valo,
     'INTE'     zona
   FROM
     (SELECT
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_auto
        AND ccb.ind_bole_depo = 0
        AND ccb.ind_banc_cheq = 0
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
      GROUP BY
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

   SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;

   -- INSERTAR REGISTROS DE PAGOS BANCARIOS MANUALES --

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
   DELETE FROM ccc_carga_inter_sapfi;

   INSERT INTO ccc_carga_inter_sapfi
    SELECT
     lv_num_lote num_lote,
     fec_proc fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZA MANUAL' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(fec_proc,'MM') val_peri_cont,
     TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_proc   fec_pago_banc,
     fec_proc   fec_valo,
     'DIGI'
   FROM
     (SELECT
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_manu
        AND ccb.ind_bole_depo = 0
        AND ccb.ind_banc_cheq = 0
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
      GROUP BY
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

  SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;

  -- INSERTAR REGISTROS DE PAGOS BANCARIOS REGULARIZADOS --
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  DELETE FROM ccc_carga_inter_sapfi;


  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote num_lote,
     fec_proc fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZA REGULARIZADA' val_glosa,
     val_debe_habe,
     imp_movi,
     TO_CHAR(fec_proc,'MM') val_peri_cont,
     TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
     END cod_cuent_corri_banc,
     fec_proc   fec_pago_banc,
     fec_proc   fec_valo,
     'REGU'     zona
   FROM
     (SELECT
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc,
       SUM(mb.imp_pago) imp_movi
      FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_regu
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
      GROUP BY
       mb.fec_proc,
       pc.val_tipo_asie,
       pc.val_cod_cuent_sap,
       pc.val_debe_habe,
       pc.ind_cuen_cod_cban,
       ccb.cod_cc)
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

   SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;


   -- Cheques --
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  DELETE FROM ccc_carga_inter_sapfi;

  -- INSERTAR REGISTROS DE CHEQUES --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
     lv_num_lote num_lote,
     mb.fec_proc fec_cont,
     val_tipo_asie  val_tipo_asie_cont,
     val_cod_cuent_sap val_tipo_movi_cont,
     'COBRANZA CHEQUES' val_glosa,
     val_debe_habe,
     imp_pago,
     TO_CHAR(fec_proc,'MM') val_peri_cont,
     TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
     CASE
      WHEN ind_cuen_cod_cban = 0
       THEN
        mb.cod_banc_cheq
       ELSE
        '      '
     END cod_cuent_corri_banc,
     mb.fec_cobr_cheq   fec_pago_banc,
     fec_proc   fec_valo,
     'DCHE'     zona
   FROM
       ccc_movim_banca mb,
       ccc_cuent_corri_banca ccb,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_param_conta_banca pc
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = cs.oid_subp
        AND cs.ccpr_oid_proc = cp.oid_proc
        AND cp.cod_proc = pc.cod_proc
        AND pc.cod_subp = cs.cod_subp
        AND mb.cod_iden_proc = pc.cod_esta_pago
        AND cp.cod_proc = gc_cod_proc_reca_banc
        AND cs.cod_subp = gc_cod_subp_reca_banc_cheq
        AND mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

   SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;

  ----- Inicio Boletas de Deposito Mes Actual -----
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  DELETE FROM ccc_carga_inter_sapfi;

  -- INSERTAR REGISTROS DE BOLETAS DE DEPOSITO MES ACTUAL --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
    lv_num_lote num_lote,
    mb.fec_proc fec_cont,
    val_tipo_asie  val_tipo_asie_cont,
    val_cod_cuent_sap val_tipo_movi_cont,
    'COBRANZA BOLETA DEPOSITO MES ACTUAL' val_glosa,
    val_debe_habe,
    mb.imp_pago,
    TO_CHAR(fec_proc,'MM') val_peri_cont,
    TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
    CASE
     WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
    END cod_cuent_corri_banc,
    mb.fec_pago   fec_pago_banc,
    fec_proc   fec_valo,
    'BDMA'
    FROM
     ccc_movim_banca mb,
     ccc_cuent_corri_banca ccb,
     ccc_proce cp,
     ccc_subpr cs,
     ccc_param_conta_banca pc
    WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
      AND mb.subp_oid_marc_crea = cs.oid_subp
      AND cs.ccpr_oid_proc = cp.oid_proc
      AND cp.cod_proc = pc.cod_proc
      AND pc.cod_subp = cs.cod_subp
      AND mb.cod_iden_proc = pc.cod_esta_pago
      AND cp.cod_proc = gc_cod_proc_reca_banc
      AND cs.cod_subp = gc_cod_subp_reca_banc_manu
      AND ccb.ind_bole_depo = 1
      AND ccb.ind_banc_cheq = 0
      AND TO_CHAR(mb.fec_pago,'MM')=TO_CHAR(TRUNC(SYSDATE),'MM')
      AND mb.oid_movi_banc > lv_oid_regi_inic
      AND mb.oid_movi_banc <= lv_oid_regi_fina
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

   UPDATE ccc_carga_inter_sapfi c
   SET c.val_tipo_movi_cont = '035'
   WHERE c.val_debe_habe = 'D';

   UPDATE ccc_carga_inter_sapfi c
   SET c.val_tipo_movi_cont = '037'
   WHERE c.val_debe_habe = 'H';

   SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;

  ----- Fin Boletas de Deposito Mes Actual -----

  ----- Inicio Boletas de Deposito Mesese Anteriores -----

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  DELETE FROM ccc_carga_inter_sapfi;

  -- INSERTAR REGISTROS DE BOLETAS DE DEPOSITO MESES ANTERIORES --
  INSERT INTO ccc_carga_inter_sapfi
   SELECT
    lv_num_lote num_lote,
    mb.fec_proc fec_cont,
    val_tipo_asie  val_tipo_asie_cont,
    val_cod_cuent_sap val_tipo_movi_cont,
    'COBRANZA BOLETA DEPOSITO MES ANTERIOR' val_glosa,
    val_debe_habe,
    mb.imp_pago,
    TO_CHAR(fec_proc,'MM') val_peri_cont,
    TO_CHAR(fec_proc,'YYYY') val_ejer_cont,
    CASE
     WHEN ind_cuen_cod_cban = 0
       THEN
        cod_cc
       ELSE
        '      '
    END cod_cuent_corri_banc,
    mb.fec_pago   fec_pago_banc,
    fec_proc   fec_valo,
    'BDMA'
    FROM
     ccc_movim_banca mb,
     ccc_cuent_corri_banca ccb,
     ccc_proce cp,
     ccc_subpr cs,
     ccc_param_conta_banca pc
    WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
      AND mb.subp_oid_marc_crea = cs.oid_subp
      AND cs.ccpr_oid_proc = cp.oid_proc
      AND cp.cod_proc = pc.cod_proc
      AND pc.cod_subp = cs.cod_subp
      AND mb.cod_iden_proc = pc.cod_esta_pago
      AND cp.cod_proc = gc_cod_proc_reca_banc
      AND cs.cod_subp = gc_cod_subp_reca_banc_manu
      AND ccb.ind_bole_depo = 1
      AND ccb.ind_banc_cheq = 0
      AND TO_CHAR(mb.fec_pago,'MM')<>TO_CHAR(TRUNC(SYSDATE),'MM')
      AND mb.fec_proc = TO_DATE(lv_fec_proc_fina,'DD/MM/YYYY')
      AND mb.oid_movi_banc > lv_oid_regi_inic
      AND mb.oid_movi_banc <= lv_oid_regi_fina
   ORDER BY fec_proc DESC,val_cod_cuent_sap DESC, cod_cc DESC;

   UPDATE ccc_carga_inter_sapfi c
   SET c.val_tipo_movi_cont = '036'
   WHERE c.val_debe_habe = 'D';

   UPDATE ccc_carga_inter_sapfi c
   SET c.val_tipo_movi_cont = '037'
   WHERE c.val_debe_habe = 'H';

   SELECT COUNT(*)
   INTO lv_ind_gene_arch
   FROM ccc_carga_inter_sapfi
   WHERE num_lote = lv_num_lote;

   IF lv_ind_gene_arch > 0 THEN
    FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_sapf,lv_num_lote,p_cod_usua);
   END IF;

  ----- Fin Boletas de Deposito Meses Anteriores -----

  --------------------------------------------------------------
  -- Validaciones Finales --
/*
   SELECT sicc - sapfi
   INTO lv_impo_dife
   FROM
    (SELECT
     (SELECT SUM(sa.imp_movi)/2
      FROM ccc_carga_inter_sapfi sa
      WHERE sa.fec_valo <= TO_DATE(p_fec_proc_fina,'DD/MM/YYYY')) sapfi,
     (SELECT SUM(mb.imp_pago)
      FROM ccc_movim_banca mb
        WHERE mb.oid_movi_banc > lv_oid_regi_inic
        AND mb.oid_movi_banc <= lv_oid_regi_fina) sicc
    FROM dual);


   IF lv_impo_dife > 0 THEN
    RAISE e_dife_impo;
   END IF;
  */


   UPDATE ccc_movim_banca mb
   SET
    mb.fec_cont= trunc(SYSDATE),
    mb.val_nume_lote_cont= lv_num_lote
   WHERE mb.oid_movi_banc >  lv_oid_regi_inic
     AND mb.oid_movi_banc < = lv_oid_regi_fina;


   UPDATE fin_contr_regis_progr rp
   SET
    rp.oid_ante_regi_proc = lv_oid_regi_inic,
    rp.oid_ulti_regi_proc =   lv_oid_regi_fina
   WHERE rp.cod_modu = gc_cod_modu
     AND rp.cod_prog = gc_cod_inte_sapf;

  --ELSE

   --RAISE e_no_gene_regi;

  --END IF;

 EXCEPTION


   WHEN e_lote_pend THEN
    raise_application_error(-20123,
                               'Existen lotes bancarios pendientes de procesar');

  WHEN e_dife_impo THEN
   raise_application_error(-20123,
                               'Existen diferencias');

  WHEN e_no_gene_regi THEN
   raise_application_error(-20123,
                               'No genera registros');


 END INT_PR_CCC_GENER_SAPFI_CHILE;

 PROCEDURE INT_CCC_PR_GENER_INFOR_MOROS(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE  DEFAULT USER)
 IS

  lv_cant_dico                   NUMBER(12);
  lv_cant_data                   NUMBER(12);
  lv_cant_siis                   NUMBER(12);

 BEGIN

  INT_PKG_CCC.INT_CCC_PR_GENER_MOROS_DICOM(p_cod_usua,lv_cant_dico);
  INT_PKG_CCC.INT_CCC_PR_GENER_MOROS_DATAB(p_cod_usua,lv_cant_data);
  INT_PKG_CCC.INT_CCC_PR_GENER_MOROS_SIISA(p_cod_usua,lv_cant_siis);

 END INT_CCC_PR_GENER_INFOR_MOROS;

 PROCEDURE INT_CCC_PR_GENER_MOROS_DICOM(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER,
  p_can_regi                       OUT NUMBER)
 IS

  lv_line_cabe                     VARCHAR2(500);
  lv_iden_sist                     CHAR(5):='SICOM';
  lv_rut_apor                      VARCHAR2(10):= '204263';
  lv_tipo_moda                     CHAR(1):='R';
  lv_camp_rell                     CHAR(216):= LPAD(' ',216,' ');

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch                      fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dias_venc                     NUMBER(3);
  lv_imp_desd                      NUMBER(12,2);
  lv_camp_desd                     seg_perio_corpo.cod_peri%TYPE;
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(12):=0;
  lv_mont_tota                     NUMBER(12):=0;

 BEGIN

  lv_nom_arch := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NombreArchivoDicom');
  lv_nom_arch := lv_nom_arch || TO_CHAR(SYSDATE,'_DDMMYYYY') || '.TXT';

  lv_dias_venc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NumeroDiasVencimientoDicom');
  lv_imp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteDesdeDicom');
  lv_camp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CampanaDesdeDicom');

  DELETE FROM ccc_int_dicom;

  INSERT INTO ccc_int_dicom
   SELECT
    mci.num_docu_iden, -- VAL_NUME_IDEN  VARCHAR(10),
    mcc.fec_venc,      -- VAL_FECH_VENC VARCHAR(8),
    mcc.oid_movi_cc,   -- VAL_NUME_CARG VARCHAR(16),
    1,                 -- VAL_TIPO_TRAN VARCHAR(1),
    mc.val_ape1 || ' ' || mc.val_ape2 || ' ' ||
    mc.val_nom1 || ' ' ||mc.val_nom2 nom_clie,   --VAL_IDEN_MORO VARCHAR(80),
    FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie), --VAL_NOMB_CALL VARCHAR(40),
    GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_PROV'), -- VAL_DESC_COMU  VARCHAR(20),
    FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_DISTR(mc.oid_clie), -- VAL_DESC_CIUD  VARCHAR(20),
    FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mc.oid_clie,'TF'), -- VAL_NUME_TELE  VARCHAR(12),
    mcc.imp_pend -- IMP_DEUD_MORO  VARCHAR(14))
   FROM
    ccc_movim_cuent_corri mcc,
    cra_perio cp,
    seg_perio_corpo spc,
    mae_clien mc,
    mae_clien_ident mci
   WHERE mcc.clie_oid_clie = mc.oid_clie
     AND mc.oid_clie = mci.clie_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND mci.val_iden_docu_prin = 1
     AND trunc(SYSDATE) - mcc.fec_docu > lv_dias_venc
     AND mcc.imp_pend > lv_imp_desd
     AND spc.cod_peri >= lv_camp_desd;

  SELECT COUNT(*), SUM(cid.imp_deud_moro)
  INTO lv_cant_regi, lv_mont_tota
  FROM ccc_int_dicom  cid;

  p_can_regi := lv_cant_regi;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,gc_cod_inte_dicom,p_cod_usua,lv_num_lote,lv_dir_ensa,lv_nom_arch,lv_handle);

  -- Generando la cabecera --
  lv_line_cabe := lv_iden_sist ||
                  LPAD(lv_rut_apor,10,'0') ||
                  LPAD(lv_cant_regi,12,'0') ||
                  TRIM(REPLACE(TO_CHAR(lv_mont_tota,'000000000000000000D99'),',','')) ||
                  lv_tipo_moda ||
                  lv_camp_rell;

  utl_file.put_line(lv_handle,lv_line_cabe);

  -- Generando el detalle
  --utl_file.fclose(lv_handle);
  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID(gc_cod_modu,gc_cod_inte_dicom,NULL,lv_handle,lv_cant_regi,NULL,lv_nom_arch);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,gc_cod_inte_dicom,lv_num_lote,lv_cant_regi,'2',lv_desc_erro);


 END INT_CCC_PR_GENER_MOROS_DICOM;

 PROCEDURE INT_CCC_PR_GENER_MOROS_DATAB(
   p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER,
  p_can_regi                       OUT NUMBER)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_nom_arch                      fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_camp_desd                     seg_perio_corpo.cod_peri%TYPE;
  lv_dias_venc                     NUMBER;
  lv_imp_desd                      NUMBER;

 BEGIN

  DELETE FROM ccc_int_datab;

  lv_nom_arch := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NombreArchivoDatabiz');
  lv_nom_arch := lv_nom_arch || TO_CHAR(SYSDATE,'_DDMMYYYY') || '.TXT';

  lv_dias_venc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NumeroDiasVencimientoDatabiz');
  lv_imp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteDesdeDatabiz');
  lv_camp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CampanaDesdeDatabiz');

  INSERT INTO ccc_int_datab
    WITH temp1 AS
     (SELECT
       mcc.clie_oid_clie,
       SUBSTR(LPAD(mcc.oid_movi_cc,12,'0'),3,12) num_iden_carg,
       mcc.fec_docu,
       mcc.imp_pend
      FROM
       ccc_movim_cuent_corri mcc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND spc.cod_peri >= lv_camp_desd
        AND mcc.imp_pend > lv_imp_desd
        AND TRUNC(SYSDATE) - mcc.fec_docu > lv_dias_venc)
      SELECT
       mc.val_ape1,             --val_ape1  varchar2(25)
       mc.val_ape2,             --val_ape2  varchar2(25)
       SUBSTR( (mc.val_nom1 || ' ' || mc.val_nom2 ),1,25), --val_nom1  varchar2(25)
       mci.num_docu_iden,               --val_rut  varchar2(10)
       SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie),1,25),  --val_dire  varchar2(25)
       SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_DISTR(mc.oid_clie),1,10) ,  --val_comu  varchar2(10)
       t1.imp_pend,             --val_mont_deud  varchar2(14)
       t1.fec_docu,             --val_fech_docu  varchar2(8)
       mci.num_docu_iden,       --val_rut_infa  varchar2(10)
       t1.num_iden_carg         --num_carg_deud  varchar2(10)
      FROM
       temp1 t1,
       mae_clien mc,
       mae_clien_ident mci
      WHERE t1.clie_oid_clie = mc.oid_clie
      AND mc.oid_clie = mci.clie_oid_clie
      AND mci.val_iden_docu_prin = 1;

  SELECT COUNT(*)
  INTO p_can_regi
  FROM ccc_int_datab;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_datab,lv_num_lote,p_cod_usua,NULL,lv_nom_arch);

 END INT_CCC_PR_GENER_MOROS_DATAB;

 PROCEDURE INT_CCC_PR_GENER_MOROS_SIISA(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER,
  p_can_regi                       OUT NUMBER)
 IS

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_nom_arch                      fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_camp_desd                     seg_perio_corpo.cod_peri%TYPE;
  lv_dias_venc                     NUMBER;
  lv_imp_desd                      NUMBER;

 BEGIN

  DELETE FROM ccc_int_siisa;

  lv_nom_arch := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NombreArchivoSiisa');
  lv_nom_arch := lv_nom_arch || TO_CHAR(SYSDATE,'_DDMMYYYY') || '.TXT';

  lv_dias_venc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('NumeroDiasVencimientoSiisa');
  lv_imp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('ImporteDesdeSiisa');
  lv_camp_desd := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CampanaDesdeSiisa');

  INSERT INTO ccc_int_siisa
    WITH temp1 AS
     (SELECT
       mcc.clie_oid_clie,
       SUBSTR(LPAD(mcc.oid_movi_cc,12,'0'),3,12) num_iden_carg,
       mcc.fec_docu,
       mcc.imp_pend
      FROM
       ccc_movim_cuent_corri mcc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND spc.cod_peri >= lv_camp_desd
        AND mcc.imp_pend > lv_imp_desd
        AND TRUNC(SYSDATE) - mcc.fec_docu > lv_dias_venc)
      SELECT
       mc.val_ape1,             --val_ape1  varchar2(25)
       mc.val_ape2,             --val_ape2  varchar2(25)
       SUBSTR( (mc.val_nom1 || ' ' || mc.val_nom2 ),1,25), --val_nom1  varchar2(25)
       mci.num_docu_iden,               --val_rut  varchar2(10)
       SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie),1,25),  --val_dire  varchar2(25)
       SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_DISTR(mc.oid_clie),1,10) ,  --val_comu  varchar2(10)
       t1.imp_pend,             --val_mont_deud  varchar2(14)
       t1.fec_docu,             --val_fech_docu  varchar2(8)
       mci.num_docu_iden,       --val_rut_infa  varchar2(10)
       t1.num_iden_carg         --num_carg_deud  varchar2(10)
      FROM temp1 t1,
           mae_clien mc,
           mae_clien_ident mci
      WHERE t1.clie_oid_clie = mc.oid_clie
      AND mc.oid_clie = mci.clie_oid_clie
      AND mci.val_iden_docu_prin = 1;

  SELECT COUNT(*)
  INTO p_can_regi
  FROM ccc_int_siisa;

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,gc_cod_inte_datab,lv_num_lote,p_cod_usua,NULL,lv_nom_arch);

 END INT_CCC_PR_GENER_MOROS_SIISA;
 /***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 12/10/2012
  Autor             :
 ***************************************************************************/
 PROCEDURE CCC_PR_RUTEA_PAGOS_SEGUN_MARCA(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE,
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE,
  p_num_lote_exte                  IN   ccc_movim_banca.num_lote_exte%TYPE,
  p_num_posi_codi_clie             IN   seg_pais.num_posi_nume_clie%TYPE,
  p_cod_usua                       IN   VARCHAR2)
 IS

  ind_rut_pago                     VARCHAR2(100);
  v_conta                          INTEGER;

    lv_oid_pais_dest ccc_param_gener.val_para%type;
    lv_oid_pais seg_pais.oid_pais%TYPE;

 BEGIN

    lv_oid_pais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_Pais);

    begin
        select  val_para
     into  ind_rut_pago
     from  ccc_param_gener
        where  cod_para  =  'BAN_RUTEA_PAGO_01';
    exception
        when no_data_found then
            ind_rut_pago := '0';
    end;

    --si el indicador  esta  apagado (valor  0),  salir de la rutina y continuar  normalmente.
     if ind_rut_pago = '1' then

       insert into ccc_movim_banca_orig
        select
          mb.oid_movi_banc          ,
          mb.soci_oid_soci           ,
          mb.subp_oid_marc_ulti      ,
          mb.subp_oid_marc_crea      ,
          mb.tcab_oid_abon_ulti      ,
          mb.tcab_oid_abon_crea      ,
          mb.ttra_oid_tipo_trans     ,
          mb.ccba_oid_cc_banc        ,
          mb.num_cons_tran           ,
          mb.num_lote                ,
          mb.cod_cons                ,
          mb.cod_erro                ,
          mb.val_digi_cheq_fact      ,
          mb.val_docu_apli_anio      ,
          mb.val_docu_apli_mes_seri  ,
          mb.val_docu_apli_nume      ,
          mb.val_docu_crea_anio      ,
          mb.val_docu_crea_mes_seri  ,
          mb.val_docu_crea_nume      ,
          mb.val_esta_movi_pend      ,
          mb.fec_cont                ,
          mb.fec_pago                ,
          mb.fec_proc                ,
          mb.val_hora_proc           ,
          mb.val_hora_norm_adic      ,
          mb.cod_iden_proc           ,
          mb.imp_pago                ,
          mb.val_nomb_ofic           ,
          mb.num_cupo                ,
          mb.val_nume_docu           ,
          mb.num_fact_bole           ,
          mb.val_nume_lote_cont      ,
          mb.val_obse                ,
          mb.cod_ofic_reca           ,
          mb.val_usua_proc           ,
          mb.fec_movi_apli           ,
          mb.val_impo_movi           ,
          mb.cod_usua                ,
          mb.num_lote_exte           ,
          mb.num_hist                ,
          mb.imp_apli                ,
          mb.imp_sald_pend           ,
          mb.pais_oid_pais           ,
          mb.sbac_oid_sbac           ,
          mb.tier_oid_erro           ,
          mb.clie_oid_clie           ,
          mb.mcac_oid_cabe           ,
          mb.cod_usua_rece           ,
          mb.val_hora_rece           ,
          mb.cod_usua_liqu           ,
          mb.val_hora_liqu           ,
          mb.cod_usua_regu           ,
          mb.val_hora_regu           ,
          mb.cod_usu_carg_exce       ,
          mb.val_hora_carg_exce      ,
          mb.ind_pago_regu           ,
          mb.cod_digi_ctrl           ,
          mb.num_docu_iden           ,
          mb.oid_banc_sucu_cheq      ,
          mb.ind_tipo_cheq           ,
          mb.fec_cobr_cheq           ,
          mb.cod_banc_cheq           ,
          mb.cod_sucu_cheq
        from  ccc_movim_banca mb
        where mb.num_lote = p_num_lote_inte;



        ccc_pr_rutea_pagos_prior_dest(p_num_lote_inte);

    select count(*)
     into v_conta
        from ccc_movim_banca_ruteo;

     if v_conta > 0 then


            select  val_para
            into lv_oid_pais_dest
            from  ccc_param_gener
            where  cod_para  =   'BAN_RUTEA_PAGO_02';


            update   ccc_movim_banca_ruteo  mbr
            set   pais_oid_pais  =  lv_oid_pais_dest
            where mbr.num_lote   =  p_num_lote_inte;




        end if;
     end if;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_CARGA_MOVIM_BANCA: ' ||
                               ls_sqlerrm);

   END CCC_PR_RUTEA_PAGOS_SEGUN_MARCA;

 /***************************************************************************
  Descripcion       : Procedimiento que carga los Movmientos Bancarios a partir de
                                 la tabla temporal generada por la Interfaz
  Fecha Creacion    : 12/10/2012
  Autor             :
 ***************************************************************************/
 PROCEDURE CCC_PR_RUTEA_PAGOS_PRIOR_DEST  (
  p_num_lote_inte                  IN   ccc_movim_banca.num_lote%TYPE
  )
 is

    v_cod_clie ccc_deuda_marca.cod_clie%type;
    v_saldo_destino ccc_deuda_marca.val_sald_deud%type;
    v_num_docu_iden_esk   ccc_deuda_marca.num_docu_iden%type;
    v_oid_clie_esik     ccc_deuda_marca.oid_clie_esik%type;

    type tab_ccc_movim_banca is table of ccc_movim_banca%rowtype;
 rec_tem_movim_banca tab_ccc_movim_banca;

 begin

    --mediante un cursor  ordenado  por numero de secuencia  num_cons_tran  procesar
    --uno a uno los  registros  de  ccc_movim_banca ,
    --identificados por el numero de lote de proceso,
 select mv.oid_movi_banc,
              mv.soci_oid_soci       ,
              mv.subp_oid_marc_ulti  ,
              mv.subp_oid_marc_crea  ,
              mv.tcab_oid_abon_ulti  ,
              mv.tcab_oid_abon_crea  ,
              mv.ttra_oid_tipo_trans ,
              mv.ccba_oid_cc_banc    ,
              mv.num_cons_tran       ,
              mv.num_lote            ,
              mv.cod_cons            ,
              mv.cod_erro            ,
              mv.val_digi_cheq_fact,
              mv.val_docu_apli_anio,
              mv.val_docu_apli_mes_seri,
              mv.val_docu_apli_nume,
              mv.val_docu_crea_anio,
              mv.val_docu_crea_mes_seri,
              mv.val_docu_crea_nume,
              mv.val_esta_movi_pend  ,
              mv.fec_cont     ,
              mv.fec_pago      ,
              mv.fec_proc,
              mv.val_hora_proc     ,
              mv.val_hora_norm_adic   ,
              mv.cod_iden_proc  ,
              mv.imp_pago,
              mv.val_nomb_ofic,
              mv.num_cupo       ,
              mv.val_nume_docu,
              mv.num_fact_bole   ,
              mv.val_nume_lote_cont ,
              mv.val_obse,
              mv.cod_ofic_reca,
              mv.val_usua_proc,
              mv.fec_movi_apli  ,
              mv.val_impo_movi   ,
              mv.cod_usua,
              mv.num_lote_exte,
              mv.num_hist,
              mv.imp_apli,
              mv.imp_sald_pend ,
              mv.pais_oid_pais ,
              mv.sbac_oid_sbac ,
              mv.tier_oid_erro ,
              mv.clie_oid_clie ,
              mv.mcac_oid_cabe ,
              mv.cod_usua_rece ,
              mv.val_hora_rece ,
              mv.cod_usua_liqu ,
              mv.val_hora_liqu ,
              mv.cod_usua_regu ,
              mv.val_hora_regu ,
              mv.cod_usu_carg_exce  ,
              mv.val_hora_carg_exce,
              mv.ind_pago_regu,
              mv.cod_digi_ctrl,
              mv.num_docu_iden,
              mv.oid_banc_sucu_cheq  ,
              mv.ind_tipo_cheq,
              mv.fec_cobr_cheq,
              mv.cod_banc_cheq,
              mv.cod_sucu_cheq,
              NULL,
              0,
              NULL,
              NULL,
              0,                             -- ind_pago_inco
              0,                            -- ind_pago_exce
              0,                             --ind_exce_vali
              0,                             --imp_apli_clie NUMBER(15,2)
              0,                             -- imp_apli_exce NUMBER(15,2)
              0,                             --imp_apli_inco NUMBER(15,2)
              0,                             --imp_apli_cobr_exte NUMBER(15,2)
              0,                             --imp_apli_cobr_pend_regu NUMBER(15,2));
              0,                              -- imp_apli_fami_prot
              0,                               -- ind_elim
              0                                -- ind_pago_reve
         bulk collect into rec_tem_movim_banca
         from  ccc_movim_banca mv
         where  mv.num_lote  =  p_num_lote_inte
   order by mv.num_cons_tran ;

    --iterar  por cada  registro:
   for i in 1 .. rec_tem_movim_banca.count
  loop
        -- determinar  si la consultora  tiene  saldo en esika,
        -- para  esto  acceder  a la tabla  ccc_deuda_marca  accediendo por  el  codigo  de consultora.
        --denominaremos  saldo_destino  al  valor obtenido en el campo

        begin
            select d.cod_clie, d.val_sald_deud,   d.num_docu_iden,      oid_clie_esik
            into v_cod_clie, v_saldo_destino,     v_num_docu_iden_esk,   v_oid_clie_esik
            from ccc_deuda_marca  d                 --Tabla ccc_deuda_marca en ESIKA
            where d.cod_clie = rec_tem_movim_banca(i).cod_cons;
        exception
            when no_data_found then
                v_cod_clie := null;
                v_num_docu_iden_esk:=null;
                v_saldo_destino := 0;
                v_oid_clie_esik:=null;
        end;

       if v_cod_clie is not null then

          if v_saldo_destino > 0 then

             if  rec_tem_movim_banca(i).imp_pago > v_saldo_destino then

                    rec_tem_movim_banca(i).imp_pago := v_saldo_destino;
                    rec_tem_movim_banca(i).val_impo_movi := v_saldo_destino;
                    rec_tem_movim_banca(i).imp_sald_pend  := v_saldo_destino;

                    rec_tem_movim_banca(i).cod_cons  := v_num_docu_iden_esk  ;
                    rec_tem_movim_banca(i).clie_oid_clie   := v_oid_clie_esik  ;

                    insert into ccc_movim_banca_ruteo values rec_tem_movim_banca(i);

                    update  ccc_movim_banca mv
                    set  mv.imp_pago  =   mv.imp_pago  -  v_saldo_destino,
                       mv.val_impo_movi  =  mv.val_impo_movi  - v_saldo_destino,
                       mv.imp_sald_pend  =  mv.imp_sald_pend  - v_saldo_destino
                    where   mv.oid_movi_banc    =  rec_tem_movim_banca(i).oid_movi_banc;

                    update  ccc_deuda_marca m
                    set     m.val_sald_deud  =  0
                    where   m.num_docu_iden  =   rec_tem_movim_banca(i).cod_cons;

          else
                    --insertar  un registro  en ccc_movim_banca_ruteo con los mismos datos  de  ccc_movim_banca .
                    rec_tem_movim_banca(i).cod_cons  := v_num_docu_iden_esk  ;
                    rec_tem_movim_banca(i).clie_oid_clie   := v_oid_clie_esik  ;

                    insert into ccc_movim_banca_ruteo values rec_tem_movim_banca(i);


                    --actualizar la tabla ccc_deuda_marca   rebjando
    -- el saldo pendiente por el importe  pagado de ccc_movim_banca:
                    update  ccc_deuda_marca m
                    set  m.val_sald_deud  =  m.val_sald_deud  -  rec_tem_movim_banca(i).imp_pago
                    where   m.num_docu_iden =   rec_tem_movim_banca(i).cod_cons;

                    --eliminar de ccc_movim_banca  el registro que se  estaba
                    delete from  ccc_movim_banca mb
                    where   mb.oid_movi_banc = rec_tem_movim_banca(i).oid_movi_banc;

          end if;

          end if;

       end if;

   end loop;

 end;

 PROCEDURE INT_PR_CCC_GENER_EMAIL_CONFI(
  p_cod_proc                       IN   ccc_proce_envio_email.cod_proc%TYPE,
  p_ind_ejec                       IN   NUMBER,
  p_val_mens                       IN   VARCHAR2 DEFAULT NULL,
  p_des_erro                       IN   VARCHAR2 DEFAULT NULL)
 IS

  lv_cuer_mens                     CLOB;
  lv_emai_orig                     ccc_proce_envio_email.val_emai_orig%TYPE;
  lv_emai_dest                     ccc_proce_envio_email.val_emai_dest%TYPE;
  lv_emai_copi                     ccc_proce_envio_email.val_copy%TYPE;
  lv_val_subj                      ccc_proce_envio_email.val_subj%TYPE;
  lv_salu_usua                     ccc_proce_envio_email.val_salu_usua%TYPE;

 BEGIN

  SELECT e.val_emai_orig,e.val_emai_dest,e.val_copy, e.val_subj, e.val_salu_usua
  INTO lv_emai_orig, lv_emai_dest, lv_emai_copi, lv_val_subj, lv_salu_usua
  FROM ccc_proce_envio_email e
  WHERE e.cod_proc = p_cod_proc;

  IF p_ind_ejec = 1 THEN

   lv_cuer_mens := lv_cuer_mens || '<html><body>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr>';
   lv_cuer_mens := lv_cuer_mens || '<b>'|| lv_salu_usua || '</b>';
   lv_cuer_mens := lv_cuer_mens || '</tr>';
   lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || '   La interface fue generada exitosamente desde SSICC' ;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table></body></html>';

   lv_val_subj := lv_val_subj || ' OK';

  ELSE

   lv_cuer_mens := lv_cuer_mens || '<html><body>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr>';
   lv_cuer_mens := lv_cuer_mens || '<b>'|| lv_salu_usua || '</b>';
   lv_cuer_mens := lv_cuer_mens || '</tr>';
   lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || '   La interface no fue generada correctamnete. Por favor contactar al analista de soporte de SSICC' ;

   IF  p_des_erro IS NOT NULL THEN

    lv_cuer_mens := lv_cuer_mens || '   Descripcion del Error' || p_des_erro;
    lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table></body></html>';
    lv_val_subj := lv_val_subj || ' ERROR';

  END IF;

  END IF;

  FIN_PKG_SEND_MAIL.SEND_MAIL(lv_emai_orig,lv_emai_dest,lv_val_subj,lv_cuer_mens,lv_emai_copi,NULL,'text/html');

 EXCEPTION

  WHEN OTHERS THEN

   NULL;

 END;

 PROCEDURE INT_PR_CCC_SLEEP(
  p_num_segu                       IN   INT)
 IS

  lv_now                           DATE;

 BEGIN

  SELECT SYSDATE
  INTO lv_now
  FROM DUAL;

  LOOP
   EXIT WHEN lv_now + (p_num_segu * (1/86400)) = SYSDATE;
  END LOOP;

 END INT_PR_CCC_SLEEP;

 /***************************************************************************
Descripcion       : Genera archivo TXT
Fecha Creacion    : 10/03/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_CCC_CARGA_DEUDA_WEB(

    psCodigoPais                        VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2,
    p_ante_oid_regi                   OUT    VARCHAR2,
    p_ulti_oid_regi                   OUT    VARCHAR2
    )
IS

  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS

with temp1 as
(
    select mcc.clie_oid_clie, max(mcc.fec_venc) fec_venc
          from ccc_movim_cuent_corri mcc
         where  mcc.subp_oid_subp_crea = 2001
         group by mcc.clie_oid_clie
)
SELECT  mc.cod_clie CODIGO_CLIENTE,
        SUBSTR(mc.val_nom1 || ' ' ||
                                                    mc.val_ape1,
                                                    1,
                                                    50) NOMBRE_CLIENTE,
       CASE
         WHEN NVL(mc.sal_deud_ante, 0) > 0 THEN
          LPAD(TO_CHAR(mc.sal_deud_ante * 100), 12, '0')
         ELSE
          '000000000000'
       END sal_deud_ante,
       LPAD(GEN_PKG_GENER.gen_fn_param_pais(psCodigoPais,pscodigosistema,'011'),12,0) monto_min,
      to_char(temp1.fec_venc,'YYYYMMDD') fec_venc,
      mci.num_docu_iden
  FROM mae_clien             mc,
       mae_clien_ident       mci,  
       mae_clien_tipo_subti  mcts,
       mae_clien_datos_adici mcda, temp1
 WHERE mc.oid_clie = mcts.clie_oid_clie
   and mc.oid_clie = mcda.clie_oid_clie
   AND mcda.ind_acti = 1
   and mcts.ticl_oid_tipo_clie = 2
   AND NOT EXISTS (SELECT NULL
          FROM ccc_clien_casti ccc
         WHERE ccc.oid_clie = mc.oid_clie)
   and   mc.sal_deud_ante > 0
   and temp1.clie_oid_clie(+) = mc.oid_clie
   and mci.clie_oid_clie= mc.oid_clie
   and mci.val_iden_docu_prin=1;

TYPE interfazTipo IS RECORD (

    v_CODIGO_CLIENTE  VARCHAR2(15),
    v_NOMBRE_CLIENTE  VARCHAR2(50),
    v_sal_deud_ante   VARCHAR2(20),
    v_monto_min       VARCHAR2(20),
    v_fec_venc        VARCHAR2(8),
    v_nroDocu_Cliente      VARCHAR2(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
     /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    lsnombrearchivo VARCHAR2(50);

BEGIN

    lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN

      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := to_char(interfazRecord(x).v_CODIGO_CLIENTE)||';'||
                     interfazRecord(x).v_NOMBRE_CLIENTE||';'||
                     interfazRecord(x).v_sal_deud_ante||';'||
                     interfazRecord(x).v_monto_min||';'||
                     to_char(interfazRecord(x).v_fec_venc)||';'||
                     interfazRecord(x).v_nroDocu_Cliente;
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

   p_ante_oid_regi:=0;
   p_ulti_oid_regi:=0;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
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
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CCC_CARGA_DEUDA_WEB: '||ls_sqlerrm);
END INT_PR_CCC_CARGA_DEUDA_WEB;


/***************************************************************************
Descripcion       : Genera Interfase de informacion buro crediticia
Fecha Creacion    : 15/04/2015
Autor             : Diego Torres L.
***************************************************************************/
PROCEDURE INT_PR_CCC_GENER_INFOR_BURO(psCodigoPais VARCHAR2,
                                      psCodigoSistema VARCHAR2,
                                      psCodigoInterfaz VARCHAR2,
                                      psNombreArchivo VARCHAR2,
                                      pscodigousuario    VARCHAR2)IS

   CURSOR c_interfaz IS

    SELECT MC.COD_CLIE,
           MC.COD_DIGI_CTRL,
           MTD.COD_TIPO_DOCU,
           MCI.NUM_DOCU_IDEN,
           MC.VAL_APE1,
           MC.VAL_APE2,
           MC.VAL_NOM1,
           MC.VAL_NOM2,
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE, 'COD_ZONA') COD_ZONA,
           (select cod_subt_clie from mae_subti_clien where oid_subt_clie = (select val_oid from gen_i18n_sicc_comun gen
              where gen.attr_enti ='MAE_SUBTI_CLIEN'
              and val_i18n = 'Negocio')) TIPO_CLIENTE,
           LPAD(TO_CHAR(CI.IMP_PEND  * 100), 18, '0') MON_DEUD,
           LPAD(TO_CHAR( (nvl(CI.IMP_INTE_VARI,0)+ nvl(CI.IMP_INTE_FIJO,0))  * 100), 18, '0') MON_INTE,
           CI.VAL_NUME_SOLI VAL_NUME_ULTI,
           TO_CHAR(CI.FEC_FACT_ULTI,'YYYYMMDD') FEC_FACT_ULTI,
           (SELECT MR.NUM_DOCU_REFE FROM MAE_REFER MR
           WHERE MR.COD_CLIE = MC.COD_CLIE
           AND MR.TIPO_REFE = 3) NUM_DOCU_REFE,
           (SELECT MR.VAL_APE1 FROM MAE_REFER MR
           WHERE MR.COD_CLIE = MC.COD_CLIE
           AND MR.TIPO_REFE = 3) VAL_APE1,
            (SELECT MR.VAL_APE2 FROM MAE_REFER MR
           WHERE MR.COD_CLIE = MC.COD_CLIE
           AND MR.TIPO_REFE = 3) VAL_APE2,
            (SELECT MR.VAL_NOM1 FROM MAE_REFER MR
           WHERE MR.COD_CLIE = MC.COD_CLIE
           AND MR.TIPO_REFE = 3) VAL_NOM1,
            (SELECT MR.VAL_NOM2 FROM MAE_REFER MR
           WHERE MR.COD_CLIE = MC.COD_CLIE
           AND MR.TIPO_REFE = 3) VAL_NOM2,
           MC.OID_CLIE
      FROM CCC_CONSO_CALCU_INTER_MORA CI,
           MAE_CLIEN                  MC,
           MAE_CLIEN_IDENT            MCI,
           MAE_TIPO_DOCUM             MTD
     WHERE MC.OID_CLIE = CI.CLIE_OID_CLIE
       AND MCI.CLIE_OID_CLIE = CI.CLIE_OID_CLIE
       AND MCI.TDOC_OID_TIPO_DOCU = MTD.OID_TIPO_DOCU
       AND MCI.VAL_IDEN_DOCU_PRIN = 1
       AND CI.IND_BLOQ = 1
       AND CI.IND_FACT = 0
       --Y debe de tener saldo pendiente mayor a cero
       AND (nvl((select sum(mcc.imp_pend)
              from ccc_movim_cuent_corri mcc
             where mcc.clie_oid_clie = ci.clie_oid_clie
               and mcc.imp_pend <> 0
               and mcc.perd_oid_peri <= (select oid_peri from cra_perio 
                                        where val_nomb_peri = (select cod_peri from
                                              bas_ctrl_fact where sta_camP='0'))),
            0) - nvl((SELECT SUM(mb.imp_sald_pend)
                        FROM ccc_movim_banca mb
                       WHERE mb.clie_oid_clie = ci.clie_oid_clie
                         AND mb.cod_iden_proc = 'P'
                         AND mb.imp_sald_pend > 0),
                      0))  > 0 
       --Y debe estar bloqueada por DP - Interés
       AND exists (select null from mae_clien_bloqu mcb where 
            mcb.clie_oid_clie = ci.clie_oid_clie
            and mcb.tibq_oid_tipo_bloq=(  SELECT mtb.oid_tipo_bloq
                                            FROM mae_tipo_bloqu mtb
                                            WHERE mtb.cod_tipo_bloq = 
                                            CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoBloqueoInteMora'))
            AND mcb.fec_desb IS NULL );


   TYPE interfazRec IS RECORD(
        codigoCliente  MAE_CLIEN.COD_CLIE%TYPE,
        codigoDigiCtrl MAE_CLIEN.Cod_Digi_Ctrl%TYPE,
        codTipoDocu    MAE_TIPO_DOCUM.COD_TIPO_DOCU%TYPE,
        numDocIden     MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
        valApe1        MAE_CLIEN.VAL_APE1%TYPE,
        valApe2        MAE_CLIEN.VAL_APE2%TYPE,
        valNom1        MAE_CLIEN.VAL_NOM1%TYPE,
        valNom2        MAE_CLIEN.VAL_NOM2%TYPE,
        codZona        ZON_ZONA.COD_ZONA%TYPE,
        tipoCliente    varchar2(2),
        montoDeuda     varchar2(18),
        montoInteres   varchar2(18),
        valNumeUlti    CCC_CONSO_CALCU_INTER_MORA.VAL_NUME_SOLI%TYPE,
        fecFactUlti    varchar2(8),
        numDocuRefe    MAE_REFER.NUM_DOCU_REFE%TYPE,
        valApe1Mr      MAE_REFER.VAL_APE1%TYPE,
        valApe2Mr      MAE_REFER.VAL_APE2%TYPE,
        valNom1Mr      MAE_REFER.VAL_NOM1%TYPE,
        valNom2Mr      MAE_REFER.VAL_NOM2%TYPE,
        oidClie        MAE_CLIEN.OID_CLIE%TYPE
   );

   TYPE interfazRecTab IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   lbAbrirUtlFile      BOOLEAN := TRUE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   BEGIN
     
       lbAbrirUtlFile := TRUE;

       OPEN c_interfaz;
       LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT 1000;
            IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
            END IF;
            IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lsLinea :=

                       interfazRecord(x).codigoCliente ||';'||
                       interfazRecord(x).codigoDigiCtrl ||';'||
                       interfazRecord(x).codTipoDocu ||';'||
                       interfazRecord(x).numDocIden ||';'||
                       interfazRecord(x).valApe1 ||';'||
                       interfazRecord(x).valApe2 ||';'||
                       interfazRecord(x).valNom1 ||';'||
                       interfazRecord(x).valNom2 ||';'||
                       interfazRecord(x).codZona ||';'||
                       interfazRecord(x).tipoCliente ||';'||
                       interfazRecord(x).montoDeuda ||';'||
                       interfazRecord(x).montoInteres ||';'||
                       interfazRecord(x).valNumeUlti ||';'||
                       interfazRecord(x).fecFactUlti ||';'||
                       interfazRecord(x).numDocuRefe ||';'||
                       interfazRecord(x).valApe1Mr ||';'||
                       interfazRecord(x).valApe2Mr ||';'||
                       interfazRecord(x).valNom1Mr ||';'||
                       interfazRecord(x).valNom2Mr ;


                        update mae_clien_bloqu mb
                           set mb.obs_bloq = 'ENSERBIC' ||
                                             (SELECT TO_CHAR(FEC_PROC,'DD/MM/YYYY')
                                                FROM BAS_CTRL_FACT
                                               WHERE STA_CAMP = 0
                                                 AND IND_CAMP_ACT = 1)
                         where clie_oid_clie = interfazRecord(x).oidClie;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CCC_GENER_INFOR_BURO: '||ls_sqlerrm);
END INT_PR_CCC_GENER_INFOR_BURO;

END INT_PKG_CCC;
/
