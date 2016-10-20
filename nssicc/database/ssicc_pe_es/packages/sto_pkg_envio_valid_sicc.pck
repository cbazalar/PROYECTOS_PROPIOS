CREATE OR REPLACE PACKAGE sto_pkg_envio_valid_sicc IS

  -- Author  : PEEXTMSILVA
  -- Created : 14/05/2008 04:08:26 p.m.
  -- Purpose : Paquete q contiene los envios por Tipo de Documento a SICC

  rows NATURAL := 10000;
  p_ind_line CONSTANT VARCHAR2(15) := 'STO_EXEC_LINE';

  /**************************************************************************
  Descripcion       : STO_PR_OCC_ENVIO_SICC
                    Envio de OCC a SICC
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_occ_envio_sicc
  (
    pscodigopais    VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psusuario       VARCHAR2,
    psnumeroproceso VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_ENVIO_SICC
                    Envio de SCC a SICC
  Fecha Creacion    : 27/05/200
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Grabar Solicitudes PostVenta en REC
  
  Fecha Creacion    : 05/06/2008
  Parametros Entrada:
                      psCodigoPais : Codigo de pais
                      psCodigoTipoDoc : Codigo de tipo doc
                      psCodigoUltimaValid : Codigo de Ultima Validacion
                      psUsuario : Codigo de Usuario
  
  Autor             : Leonardo  Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SAD_ENVIO_SICC
                      Envio de SAD a SICC
  Fecha Creacion    : 10/06/2008
  Parametros Entrada:
      psCodigoPais        : Codigo de pais
      psCodigoTipoDoc     : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario           : Codigo de Usuario
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_sad_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_GRABA_PED_REC_OID
                      Envio de informacion a Pedidos y Reclamos
  Fecha Creacion    : 27/06/2008
  Parametros Entrada:
      Codigo de Usuario   : OID de Reclamo
      psUsuario           : Codigo de Usuario
      pscodigopais        : Codigo de pais
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_pr_graba_ped_rec_oid
  (
    psoidreclamo VARCHAR2,
    psusuario    VARCHAR2,
    psinicio     NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : STO_PR_GRABA_PED_REC
                      Envio de informacion a Pedidos y Reclamos
  Fecha Creacion    : 27/06/2008
  Parametros Entrada:
      psUsuario           : Codigo de Usuario
      pscodigopais        : Codigo de pais
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_graba_ped_rec
  (
    psusuario    VARCHAR2,
    pscodigopais VARCHAR2
  );
  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_ENVIO_SICC
                    Envio de DYCZ a SICC
  Fecha Creacion    : 05/02/2009
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );
  /**************************************************************************
  Descripcion       : STO_PR_CUP_ENVIO_SICC
                    Envio de CUP a SICC
  Fecha Creacion    : 18/02/2009
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_ENVI_MAIL
                    Envio de Correos - Solicitud de credito
  Fecha Creacion    : 18/02/2009
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psnumeroproceso : Numero de proceso
      pstipodocumento : Codigo de Tipo de documento
  
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_envi_mail
  (
    pscodigopais    VARCHAR2,
    psnumeroproceso VARCHAR2,
    pstipodocumento VARCHAR2,
    psobservaciones VARCHAR2
  );
  /**************************************************************************
  Descripcion       : STO_PR_GRABA_FAC_REC_OID
                      Envio de informacion a Pedidos y Reclamos
  Fecha Creacion    : 22/04/2009
  Parametros Entrada:
      Codigo de Usuario   : OID de Reclamo
      psUsuario           : Codigo de Usuario
      pscodigopais        : Codigo de pais
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_pr_graba_fac_rec_oid
  (
    psoidreclamo VARCHAR2,
    psusuario    VARCHAR2,
    psinicio     NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : sto_pr_elimin_duplic_Reclam
                      Elimina los registros duplicados en tablas de Reclasmos
  Fecha Creacion    : 03/06/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_elimin_duplic_reclam;

  /**************************************************************************
  Descripcion       : sto_pr_compl_recla
                      COmpleta Reclamos
  Fecha Creacion    : 10/12/2009
  Autor             : Arturo Blumen
  ***************************************************************************/
  PROCEDURE sto_pr_compl_recla
  (
    psusuario     VARCHAR2,
    pscodigopais  VARCHAR2,
    lnnumreclamos NUMBER
  );
  /**************************************************************************
  Descripcion       : sto_pr_pulir_unida_admin
                      Pule la data actualizada de unidad administrativa
  Fecha Creacion    : 08/03/2011
  Autor             : Christian Luque
  ***************************************************************************/
  PROCEDURE sto_pr_pulir_unida_admin(pscodigopais VARCHAR2);

  /**************************************************************************
  Descripcion       : sto_pr_actua_limit_venta
                      Actualiza la PED_SOLIC_POSIC con los detalles de pedidos
                      que han sido marcados en la validacion de limite de venta
                      focalizada por consultora
  Fecha Creacion    : 24/05/2011
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_actua_limit_venta
  (
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  );

  /**************************************************************************
  Descripcion       : sto_pr_actua_boleta_elect
                      Actualiza las boletas electronicas en base a un cdr
  Fecha Creacion    : 17/04/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_actua_boleta_elect(psoidoperarecla NUMBER);

  /**************************************************************************
  Descripcion       : sto_pr_genera_consolidado
                      Genera el Consolidado de un Reclamo
  Fecha Creacion    : 04/07/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_genera_consolidado
  (
    p_oidsolic         NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2,
    psparametroconso   VARCHAR2,
    pstipooperacion    VARCHAR2
  );
  /**************************************************************************
   Descripcion       : Actualiza el indicador de Facturacion
   Fecha Creacion    : 18/02/2009
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_actua_matri_factu
  (
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 23/07/2013
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_matri_factu(pnoiddetaofer NUMBER);

END sto_pkg_envio_valid_sicc;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_envio_valid_sicc IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /**************************************************************************
  Descripcion       : STO_PR_OCC_ENVIO_SICC
                    Envio de OCC a SICC
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_occ_envio_sicc
  (
    pscodigopais    VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psusuario       VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
    CURSOR c_cabecera IS
      SELECT cab.cod_clie,
             cab.num_lote,
             cab.sec_nume_docu,
             cab.clie_oid_clie,
             cab.soca_oid_soli_cabe_refe,
             cab.ind_rece_ocr,
             cab.ind_rece_web,
             cab.ind_rece_dd,
             cab.sbti_oid_subt_clie,
             cab.perd_oid_peri,
             NULL cod_clasi,
             cab.ind_rece_onli,
             cab.fec_prog_fact,
             cab.num_clie,
             cab.tspa_oid_tipo_soli_pais,
             cab.mone_oid_mone,
             cab.tids_oid_tipo_desp,
             cab.almc_oid_alma,
             cab.ticl_oid_tipo_clie,
             cab.taim_oid_tasa_impu,
             cab.clie_oid_clie_rece_fact,
             cab.clie_oid_clie_paga,
             cab.clie_oid_clie_dest,
             cab.cldi_oid_clie_dire,
             cab.tdoc_oid_tipo_docu,
             cab.soci_oid_soci,
             cab.sbac_oid_sbac,
             cab.terr_oid_terr,
             cab.zzon_oid_zona,
             NULL num_soli,
             cab.val_tasa_impu,
             cab.ind_perm_unio_sol,
             cab.val_tipo_camb,
             cab.ind_oc,
             cab.ind_pedi_prue,
             cab.ind_ts_no_conso,
             cab.val_glos_obse,
             cab.pais_oid_pais,
             cab.tido_oid_tipo_docu,
             cab.vepo_oid_valo_estr_geop,
             cab.tspa_oid_tipo_soli_pais_cons,
             cab.fopa_oid_form_pago,
             cab.clso_oid_clas_soli,
             cab.ztad_oid_terr_admi,
             cab.fec_prog_fact_comp,
             cab.val_reca_flet,
             '1' ind_ocs_proc,
             cab.ind_vali_prol
        FROM int_solic_conso_cabec cab,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cab.sec_nume_docu
         AND occ.num_lote = cab.num_lote
         AND occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodtipodocu
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_cab_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_cab_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_cab_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cab_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_cab_soca_oid_soli_cabe_refe IS TABLE OF int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
    TYPE t_cab_ind_rece_ocr IS TABLE OF int_solic_conso_cabec.ind_rece_ocr%TYPE;
    TYPE t_cab_ind_rece_web IS TABLE OF int_solic_conso_cabec.ind_rece_web%TYPE;
    TYPE t_cab_ind_rece_dd IS TABLE OF int_solic_conso_cabec.ind_rece_dd%TYPE;
    TYPE t_cab_sbti_oid_subt_clie IS TABLE OF int_solic_conso_cabec.sbti_oid_subt_clie%TYPE;
    TYPE t_cab_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_cab_cod_clasi IS TABLE OF sto_param_gener_occrr.val_param %TYPE;
    TYPE t_cab_ind_rece_onli IS TABLE OF int_solic_conso_cabec.ind_rece_onli %TYPE;
    TYPE t_cab_fec_prog_fact IS TABLE OF int_solic_conso_cabec.fec_prog_fact %TYPE;
    TYPE t_cab_num_clie IS TABLE OF int_solic_conso_cabec.num_clie %TYPE;
    TYPE t_cab_tspa_oid_tipo_soli_pais IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais %TYPE;
    TYPE t_cab_mone_oid_mone IS TABLE OF int_solic_conso_cabec.mone_oid_mone %TYPE;
    TYPE t_cab_tids_oid_tipo_desp IS TABLE OF int_solic_conso_cabec.tids_oid_tipo_desp %TYPE;
    TYPE t_cab_almc_oid_alma IS TABLE OF int_solic_conso_cabec.almc_oid_alma %TYPE;
    TYPE t_cab_ticl_oid_tipo_clie IS TABLE OF int_solic_conso_cabec.ticl_oid_tipo_clie %TYPE;
    TYPE t_cab_taim_oid_tasa_impu IS TABLE OF int_solic_conso_cabec.taim_oid_tasa_impu %TYPE;
    TYPE t_cab_clie_oid_clie_rece_fact IS TABLE OF int_solic_conso_cabec.clie_oid_clie_rece_fact %TYPE;
    TYPE t_cab_clie_oid_clie_paga IS TABLE OF int_solic_conso_cabec.clie_oid_clie_paga %TYPE;
    TYPE t_cab_clie_oid_clie_dest IS TABLE OF int_solic_conso_cabec.clie_oid_clie_dest %TYPE;
    TYPE t_cab_cldi_oid_clie_dire IS TABLE OF int_solic_conso_cabec.cldi_oid_clie_dire %TYPE;
    TYPE t_cab_tdoc_oid_tipo_docu IS TABLE OF int_solic_conso_cabec.tdoc_oid_tipo_docu %TYPE;
    TYPE t_cab_soci_oid_soci IS TABLE OF int_solic_conso_cabec.soci_oid_soci %TYPE;
    TYPE t_cab_sbac_oid_sbac IS TABLE OF int_solic_conso_cabec.sbac_oid_sbac %TYPE;
    TYPE t_cab_terr_oid_terr IS TABLE OF int_solic_conso_cabec.terr_oid_terr %TYPE;
    TYPE t_cab_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona %TYPE;
    TYPE t_cab_num_soli IS TABLE OF ped_solic_cabec.val_nume_soli %TYPE;
    TYPE t_cab_val_tasa_impu IS TABLE OF int_solic_conso_cabec.val_tasa_impu %TYPE;
    TYPE t_cab_ind_perm_unio_sol IS TABLE OF int_solic_conso_cabec.ind_perm_unio_sol %TYPE;
    TYPE t_cab_val_tipo_camb IS TABLE OF int_solic_conso_cabec.val_tipo_camb %TYPE;
    TYPE t_cab_ind_oc IS TABLE OF int_solic_conso_cabec.ind_oc %TYPE;
    TYPE t_cab_ind_pedi_prue IS TABLE OF int_solic_conso_cabec.ind_pedi_prue %TYPE;
    TYPE t_cab_ind_ts_no_conso IS TABLE OF int_solic_conso_cabec.ind_ts_no_conso %TYPE;
    TYPE t_cab_val_glos_obse IS TABLE OF int_solic_conso_cabec.val_glos_obse %TYPE;
    TYPE t_cab_pais_oid_pais IS TABLE OF int_solic_conso_cabec.pais_oid_pais %TYPE;
    TYPE t_cab_tido_oid_tipo_docu IS TABLE OF int_solic_conso_cabec.tido_oid_tipo_docu %TYPE;
    TYPE t_cab_vepo_oid_valo_estr_geop IS TABLE OF int_solic_conso_cabec.vepo_oid_valo_estr_geop %TYPE;
    TYPE t_cab_tspa_oid_tipo_solp_cons IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais_cons%TYPE;
    TYPE t_cab_fopa_oid_form_pago IS TABLE OF int_solic_conso_cabec.fopa_oid_form_pago %TYPE;
    TYPE t_cab_clso_oid_clas_soli IS TABLE OF int_solic_conso_cabec.clso_oid_clas_soli %TYPE;
    TYPE t_cab_ztad_oid_terr_admi IS TABLE OF int_solic_conso_cabec.ztad_oid_terr_admi %TYPE;
    TYPE t_cab_fec_prog_fact_comp IS TABLE OF int_solic_conso_cabec.fec_prog_fact_comp %TYPE;
    TYPE t_cab_val_reca_flet IS TABLE OF int_solic_conso_cabec.val_reca_flet %TYPE;
    TYPE t_cab_ind_ocs_proc IS TABLE OF int_solic_conso_cabec.ind_ocs_proc %TYPE;
    TYPE t_ind_vali_prol IS TABLE OF int_solic_conso_cabec.ind_vali_prol%TYPE;
  
    v_cab_cod_clie                t_cab_cod_clie;
    v_cab_num_lote                t_cab_num_lote;
    v_cab_sec_nume_docu           t_cab_sec_nume_docu;
    v_cab_clie_oid_clie           t_cab_clie_oid_clie;
    v_cab_soca_oid_soli_cabe_refe t_cab_soca_oid_soli_cabe_refe;
    v_cab_ind_rece_ocr            t_cab_ind_rece_ocr;
    v_cab_ind_rece_web            t_cab_ind_rece_web;
    v_cab_ind_rece_dd             t_cab_ind_rece_dd;
    v_cab_sbti_oid_subt_clie      t_cab_sbti_oid_subt_clie;
    v_cab_perd_oid_peri           t_cab_perd_oid_peri;
    v_cab_cod_clasi               t_cab_cod_clasi;
    v_cab_ind_rece_onli           t_cab_ind_rece_onli;
    v_cab_fec_prog_fact           t_cab_fec_prog_fact;
    v_cab_num_clie                t_cab_num_clie;
    v_cab_tspa_oid_tipo_soli_pais t_cab_tspa_oid_tipo_soli_pais;
    v_cab_mone_oid_mone           t_cab_mone_oid_mone;
    v_cab_tids_oid_tipo_desp      t_cab_tids_oid_tipo_desp;
    v_cab_almc_oid_alma           t_cab_almc_oid_alma;
    v_cab_ticl_oid_tipo_clie      t_cab_ticl_oid_tipo_clie;
    v_cab_taim_oid_tasa_impu      t_cab_taim_oid_tasa_impu;
    v_cab_clie_oid_clie_rece_fact t_cab_clie_oid_clie_rece_fact;
    v_cab_clie_oid_clie_paga      t_cab_clie_oid_clie_paga;
    v_cab_clie_oid_clie_dest      t_cab_clie_oid_clie_dest;
    v_cab_cldi_oid_clie_dire      t_cab_cldi_oid_clie_dire;
    v_cab_tdoc_oid_tipo_docu      t_cab_tdoc_oid_tipo_docu;
    v_cab_soci_oid_soci           t_cab_soci_oid_soci;
    v_cab_sbac_oid_sbac           t_cab_sbac_oid_sbac;
    v_cab_terr_oid_terr           t_cab_terr_oid_terr;
    v_cab_zzon_oid_zona           t_cab_zzon_oid_zona;
    v_cab_num_soli                t_cab_num_soli;
    v_cab_val_tasa_impu           t_cab_val_tasa_impu;
    v_cab_ind_perm_unio_sol       t_cab_ind_perm_unio_sol;
    v_cab_val_tipo_camb           t_cab_val_tipo_camb;
    v_cab_ind_oc                  t_cab_ind_oc;
    v_cab_ind_pedi_prue           t_cab_ind_pedi_prue;
    v_cab_ind_ts_no_conso         t_cab_ind_ts_no_conso;
    v_cab_val_glos_obse           t_cab_val_glos_obse;
    v_cab_pais_oid_pais           t_cab_pais_oid_pais;
    v_cab_tido_oid_tipo_docu      t_cab_tido_oid_tipo_docu;
    v_cab_vepo_oid_valo_estr_geop t_cab_vepo_oid_valo_estr_geop;
    v_cab_tspa_oid_tipo_solp_cons t_cab_tspa_oid_tipo_solp_cons;
    v_cab_fopa_oid_form_pago      t_cab_fopa_oid_form_pago;
    v_cab_clso_oid_clas_soli      t_cab_clso_oid_clas_soli;
    v_cab_ztad_oid_terr_admi      t_cab_ztad_oid_terr_admi;
    v_cab_fec_prog_fact_comp      t_cab_fec_prog_fact_comp;
    v_cab_val_reca_flet           t_cab_val_reca_flet;
    v_cab_ind_ocs_proc            t_cab_ind_ocs_proc;
    v_ind_vali_prol               t_ind_vali_prol;
    rows                          NATURAL := 10000; -- Numero de filas a procesar cada vez
    i                             BINARY_INTEGER := 0;
  
    lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    lnnumsecudetalle NUMBER;
  
    lnnumcabeceras NUMBER;
  
    lnoidperi NUMBER;
  
    lsparametrotipopedido sto_param_gener_occrr.val_param%TYPE;
    lsparametroclasidd    sto_param_gener_occrr.val_param%TYPE;
    lsparametroclasiweb   sto_param_gener_occrr.val_param%TYPE;
    lsparametroclasiocr   sto_param_gener_occrr.val_param%TYPE;
    lsparametroclasimixto sto_param_gener_occrr.val_param%TYPE;
    lsinsertapremio       sto_param_gener_occrr.val_param%TYPE;
  
    lnoidtiposolisoc ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  
    varcuv     VARCHAR2(12);
    varnumprem NUMBER(12);
    varcopa    NUMBER(12);
    varnivprem NUMBER(12);
    varunid    NUMBER(12);
  
    CURSOR ini_stock
    (
      lnsecnumedocu NUMBER,
      lsnumlote     VARCHAR2
    ) IS
      SELECT det.cod_vent,
             det.num_prem,
             det.copa_oid_para_gral,
             det.panp_oid_para_nive_prem,
             det.val_unid_dem
        FROM int_solic_conso_detal det
       WHERE sec_nume_docu_cabe = lnsecnumedocu
         AND det.num_prem IS NOT NULL
       ORDER BY det.sec_nume_docu;
  
    TYPE ini_stockrec IS RECORD(
      varcuv     VARCHAR2(12),
      varnumprem NUMBER(12),
      varcopa    NUMBER(12),
      varnivprem NUMBER(12),
      varunid    NUMBER(12));
  
    TYPE ini_stockrectab IS TABLE OF ini_stockrec;
    ini_stockrecord ini_stockrectab;
  
    pnoidproce ped_solic_cabec.proc_oid_proc%TYPE := 1;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodtipodocu,
                                                 psnumeroproceso);
  
    /*INICIALIZACION DE VALORES PARA EL PROCESO*/
    SELECT COUNT(1)
      INTO lnnumcabeceras
      FROM sto_proce_docum_digit occ
     WHERE occ.cod_tipo_docu = pscodtipodocu
       AND occ.cod_pais = pscodigopais
       AND occ.num_proc = psnumeroproceso;
  
    IF lnnumcabeceras > 0 THEN
    
      lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                               pscodtipodocu);
      lsparametrotipopedido    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_TIPO_CLASI_PEDID');
      lsparametroclasidd       := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_COD_PED_DD_PURO');
      lsparametroclasiweb      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_COD_PED_WEB_PURO');
      lsparametroclasiocr      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_COD_PED_OCR_PURO');
      lsparametroclasimixto    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_COD_PED_MIXTO');
      lsinsertapremio          := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_PARA_INSE_PREM');
    
      SELECT tsp.oid_tipo_soli_pais
        INTO lnoidtiposolisoc
        FROM ped_tipo_solic_pais tsp,
             ped_tipo_solic      ts
       WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
         AND ts.cod_tipo_soli = 'SOC';
    
      lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                                'PED001',
                                                                '000',
                                                                lnnumcabeceras);
    
      lnnumsoliformat := to_char(SYSDATE, 'YY') ||
                         lpad(lnnumsoliinicio, 8, '0') + 1;
    
      lnnumsecudetalle := 0;
    
      OPEN c_cabecera;
      LOOP
        FETCH c_cabecera BULK COLLECT
          INTO v_cab_cod_clie,
               v_cab_num_lote,
               v_cab_sec_nume_docu,
               v_cab_clie_oid_clie,
               v_cab_soca_oid_soli_cabe_refe,
               v_cab_ind_rece_ocr,
               v_cab_ind_rece_web,
               v_cab_ind_rece_dd,
               v_cab_sbti_oid_subt_clie,
               v_cab_perd_oid_peri,
               v_cab_cod_clasi,
               v_cab_ind_rece_onli,
               v_cab_fec_prog_fact,
               v_cab_num_clie,
               v_cab_tspa_oid_tipo_soli_pais,
               v_cab_mone_oid_mone,
               v_cab_tids_oid_tipo_desp,
               v_cab_almc_oid_alma,
               v_cab_ticl_oid_tipo_clie,
               v_cab_taim_oid_tasa_impu,
               v_cab_clie_oid_clie_rece_fact,
               v_cab_clie_oid_clie_paga,
               v_cab_clie_oid_clie_dest,
               v_cab_cldi_oid_clie_dire,
               v_cab_tdoc_oid_tipo_docu,
               v_cab_soci_oid_soci,
               v_cab_sbac_oid_sbac,
               v_cab_terr_oid_terr,
               v_cab_zzon_oid_zona,
               v_cab_num_soli,
               v_cab_val_tasa_impu,
               v_cab_ind_perm_unio_sol,
               v_cab_val_tipo_camb,
               v_cab_ind_oc,
               v_cab_ind_pedi_prue,
               v_cab_ind_ts_no_conso,
               v_cab_val_glos_obse,
               v_cab_pais_oid_pais,
               v_cab_tido_oid_tipo_docu,
               v_cab_vepo_oid_valo_estr_geop,
               v_cab_tspa_oid_tipo_solp_cons,
               v_cab_fopa_oid_form_pago,
               v_cab_clso_oid_clas_soli,
               v_cab_ztad_oid_terr_admi,
               v_cab_fec_prog_fact_comp,
               v_cab_val_reca_flet,
               v_cab_ind_ocs_proc,
               v_ind_vali_prol LIMIT rows;
      
        IF v_cab_sec_nume_docu.count > 0 THEN
        
          FOR i IN v_cab_sec_nume_docu.first .. v_cab_sec_nume_docu.last
          LOOP
            pnoidproce := 1;
          
            /*SE VALIDA QUE EL PEDIDO NO TENGA ERRORES*/
            SELECT nvl(MIN('0'), '1')
              INTO v_cab_ind_ocs_proc(i)
              FROM sto_detal_docum_excep dd
             WHERE dd.sec_nume_docu = v_cab_sec_nume_docu(i)
               AND dd.num_lote = v_cab_num_lote(i)
               AND dd.cod_tipo_docu = pscodtipodocu
               AND dd.cod_pais = pscodigopais
               AND dd.ind_apro = '0';
          
            IF v_cab_ind_rece_onli(i) = '1' THEN
              SELECT tabla.oid
                INTO pnoidproce
                FROM (SELECT s.proc_oid_proc OID
                        FROM ped_secue_proce s
                       WHERE s.grpr_oid_grup_proc = 1
                         AND s.tspa_oid_tipo_soli_pais = lnoidtiposolisoc
                       ORDER BY cod_secu DESC) tabla
               WHERE rownum = 1;
            
            ELSE
            
              /*SETEA v_cab_soca_oid_soli_cabe_refe SOCA COMO NULO*/
              v_cab_soca_oid_soli_cabe_refe(i) := NULL;
            
            END IF;
          
            IF v_cab_soca_oid_soli_cabe_refe(i) IS NULL THEN
            
              /*SETEA LOS VALORES A INSERTARSE*/
              v_cab_soca_oid_soli_cabe_refe(i) := ped_soca_seq.nextval;
              v_cab_num_soli(i) := lnnumsoliformat;
              lnnumsoliformat := lnnumsoliformat + 1;
            
              lnoidperi := v_cab_perd_oid_peri(i);
            
              /*SE INSERTA LA CABECERA DE LOS PEDIDOS*/
              INSERT INTO ped_solic_cabec
                (oid_soli_cabe,
                 fec_prog_fact,
                 num_clien,
                 tspa_oid_tipo_soli_pais,
                 mone_oid_mone,
                 tids_oid_tipo_desp,
                 almc_oid_alma,
                 modu_oid_modu,
                 ticl_oid_tipo_clie,
                 taim_oid_tasa_impu,
                 perd_oid_peri,
                 clie_oid_clie,
                 clie_oid_clie_rece_fact,
                 clie_oid_clie_paga,
                 clie_oid_clie_dest,
                 cldi_oid_clie_dire,
                 tdoc_oid_tipo_docu,
                 soci_oid_soci,
                 sbac_oid_sbac,
                 terr_oid_terr,
                 zzon_oid_zona,
                 val_nume_soli,
                 val_usua,
                 val_tasa_impu,
                 fec_cron,
                 ind_perm_unio_sol,
                 val_tipo_camb,
                 num_docu_orig,
                 val_base_flet_loca,
                 val_impo_flet_loca,
                 val_impo_flet_tota_loca,
                 val_impo_flet_sin_impu_tota,
                 val_reca_flet_loca,
                 val_otro_reca_loca,
                 val_tota_paga_loca,
                 val_prec_cata_tota_loca,
                 val_prec_cata_sin_impu_tota,
                 val_prec_fact_tota_loca,
                 val_impo_impu_tota_loca,
                 val_impo_desc_1_tota_loca,
                 val_impo_desc_1_tota_docu,
                 val_impo_desc_1_sin_impu_tota,
                 val_impo_desc_3_tota_docu,
                 val_impo_desc_3_sin_impu_tota,
                 val_impo_desc_tota_loca,
                 val_impo_dto_1_sin_imp_tot_loc,
                 val_impo_redo_loca,
                 val_base_flet_docu,
                 val_impo_flet_docu,
                 val_impo_desc_tota_docu,
                 val_impo_flet_sin_impu_docu,
                 val_reca_flet_docu,
                 val_otro_reca_docu,
                 val_tota_flet_docu,
                 val_impo_flet_tota_docu,
                 val_tota_flet_loca,
                 val_tota_paga_docu,
                 val_prec_cata_tota_docu,
                 val_prec_cata_sin_impu_tota_do,
                 val_prec_cont_tota_loca,
                 val_prec_cont_sin_impu_tota,
                 val_prec_cont_sin_impu_tota_1,
                 val_prec_fact_tota_docu,
                 val_prec_cata_tota_loc_uni_dem,
                 val_prec_neto_tota_docu,
                 val_prec_neto_tota_loca,
                 val_impo_impu_tota_docu,
                 val_impo_redo_docu,
                 val_impo_redo_cons_loca,
                 val_impo_redo_cons_docu,
                 ind_oc,
                 ind_pedi_prue,
                 ind_ts_no_conso,
                 val_glos_obse,
                 val_impo_desc_3_tota_loca,
                 val_impo_dto_3_sin_imp_tot_loc,
                 pais_oid_pais,
                 tido_oid_tipo_docu,
                 vepo_oid_valo_estr_geop,
                 esso_oid_esta_soli,
                 grpr_oid_grup_proc,
                 sbti_oid_subt_clie,
                 acfi_oid_acce_fisi,
                 tspa_oid_tipo_soli_pais_cons,
                 fopa_oid_form_pago,
                 clso_oid_clas_soli,
                 ztad_oid_terr_admi,
                 oper_oid_oper,
                 proc_oid_proc,
                 ind_inte_lari_gene,
                 fec_prog_fact_comp,
                 val_reca_flet,
                 ind_rece_onli,
                 ind_vali_prol)
              VALUES
                (v_cab_soca_oid_soli_cabe_refe(i),
                 v_cab_fec_prog_fact(i),
                 v_cab_num_clie(i),
                 v_cab_tspa_oid_tipo_soli_pais(i),
                 v_cab_mone_oid_mone(i),
                 v_cab_tids_oid_tipo_desp(i),
                 nvl((SELECT DISTINCT a1.oid_alma
                       FROM bel_almac             a1,
                            app_confi_centr_distr b1,
                            ape_confi_liafp_cabec c1,
                            ape_confi_liafp_detal d1
                      WHERE a1.ccdi_oid_confi_centr_distr =
                            b1.oid_conf_cent_dist
                        AND b1.oid_conf_cent_dist =
                            c1.ccdi_oid_conf_cent_dist
                        AND c1.oid_conf_lafp_cabe =
                            d1.liac_oid_conf_lafp_cabe
                        AND d1.zzon_oid_zona = v_cab_zzon_oid_zona(i)),
                     v_cab_almc_oid_alma(i)),
                 1,
                 v_cab_ticl_oid_tipo_clie(i),
                 v_cab_taim_oid_tasa_impu(i),
                 v_cab_perd_oid_peri(i),
                 v_cab_clie_oid_clie(i),
                 v_cab_clie_oid_clie_rece_fact(i),
                 v_cab_clie_oid_clie_paga(i),
                 v_cab_clie_oid_clie_dest(i),
                 v_cab_cldi_oid_clie_dire(i),
                 v_cab_tdoc_oid_tipo_docu(i),
                 v_cab_soci_oid_soci(i),
                 v_cab_sbac_oid_sbac(i),
                 v_cab_terr_oid_terr(i),
                 v_cab_zzon_oid_zona(i),
                 v_cab_num_soli(i),
                 psusuario,
                 v_cab_val_tasa_impu(i),
                 trunc(SYSDATE),
                 v_cab_ind_perm_unio_sol(i),
                 v_cab_val_tipo_camb(i),
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 v_cab_ind_oc(i),
                 v_cab_ind_pedi_prue(i),
                 v_cab_ind_ts_no_conso(i),
                 v_cab_val_glos_obse(i),
                 0,
                 0,
                 v_cab_pais_oid_pais(i),
                 v_cab_tido_oid_tipo_docu(i),
                 v_cab_vepo_oid_valo_estr_geop(i),
                 1,
                 1,
                 v_cab_sbti_oid_subt_clie(i),
                 1,
                 v_cab_tspa_oid_tipo_solp_cons(i),
                 v_cab_fopa_oid_form_pago(i),
                 v_cab_clso_oid_clas_soli(i),
                 v_cab_ztad_oid_terr_admi(i),
                 40,
                 --1,
                 pnoidproce,
                 0,
                 v_cab_fec_prog_fact_comp(i),
                 v_cab_val_reca_flet(i),
                 v_cab_ind_rece_onli(i),
                 v_ind_vali_prol(i));
            
              /*INSERTA LOS DETALES DEL PEDIDO*/
            
              INSERT INTO ped_solic_posic
                (oid_soli_posi,
                 cod_posi,
                 num_unid_dema,
                 num_unid_por_aten,
                 val_tasa_impu,
                 soca_oid_soli_cabe,
                 taim_oid_tasa_impu,
                 tpos_oid_tipo_posi,
                 prod_oid_prod,
                 fopa_oid_form_pago,
                 val_prec_cata_unit_loca,
                 val_prec_cont_unit_loca,
                 val_prec_cata_unit_docu,
                 val_prec_conta_unit_docu,
                 val_prec_fact_unit_loca,
                 val_prec_fact_unit_docu,
                 val_prec_sin_impu_unit_loca,
                 val_prec_sin_impu_unit_docu,
                 val_prec_sin_impu_tota_docu,
                 val_impo_desc_unit_loca,
                 val_impo_desc_unit_docu,
                 val_prec_neto_unit_loca,
                 val_prec_neto_tota_docu,
                 val_prec_neto_unit_docu,
                 val_prec_tota_tota_loca,
                 val_prec_tota_tota_docu,
                 val_impo_impu_unit_loca,
                 val_impo_impu_unit_docu,
                 val_impo_desc_tota_docu,
                 val_impo_impu_tota_loca,
                 val_impo_impu_tota_docu,
                 val_impo_desc_tota_loca,
                 val_prec_tota_unit_loca,
                 val_prec_tota_unit_docu,
                 val_prec_cont_tota_loca,
                 val_prec_cata_tota_loca,
                 val_prec_cata_tota_docu,
                 val_prec_cont_tota_docu,
                 val_porc_desc,
                 val_prec_cata_tota_loca_unid,
                 num_unid_dema_real,
                 num_unid_compr,
                 val_prec_fact_tota_loca,
                 val_prec_fact_tota_docu,
                 val_prec_sin_impu_tota_loca,
                 val_prec_neto_tota_loca,
                 ofde_oid_deta_ofer,
                 espo_oid_esta_posi,
                 stpo_oid_subt_posi,
                 val_codi_vent,
                 ind_no_impr,
                 almc_oid_almc,
                 ind_recu_prol)
                SELECT ped_sopo_seq.nextval,
                       lnnumsecudetalle + rownum,
                       det.val_unid_dem,
                       det.val_unid_dem,
                       0,
                       v_cab_soca_oid_soli_cabe_refe(i),
                       NULL,
                       det.tpos_oid_tipo_posi,
                       det.prod_oid_prod,
                       det.fopa_oid_form_pago,
                       nvl(det.val_prec_cata_unit_loca, 0),
                       decode(nvl(det.val_prec_cata_unit_loca, 0),
                              0,
                              det.val_prec_cont_unit_loca,
                              0),
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       det.val_unid_dem,
                       det.val_unid_dem,
                       0,
                       0,
                       0,
                       0,
                       det.ofde_oid_deta_ofer,
                       4,
                       det.stpo_oid_subt_posi,
                       det.cod_vent,
                       det.ind_no_impr,
                       det.oid_alma,
                       decode(det.ind_recu_prol, '1', '1', '2', '2', NULL)
                  FROM int_solic_conso_detal det,
                       sto_proce_docum_digit occ2
                 WHERE occ2.sec_nume_docu = det.sec_nume_docu
                   AND occ2.num_proc = psnumeroproceso
                   AND occ2.num_lote = det.num_lote
                   AND occ2.cod_tipo_docu = lscodigodocumentodetalle
                   AND occ2.cod_pais = pscodigopais
                   AND occ2.sec_nume_docu_cabe = v_cab_sec_nume_docu(i)
                   AND occ2.num_lote = v_cab_num_lote(i)
                   AND det.num_prem IS NULL;
            
              IF (lsinsertapremio IS NOT NULL AND lsinsertapremio = 'S') THEN
              
                OPEN ini_stock(v_cab_sec_nume_docu(i), v_cab_num_lote(i));
                LOOP
                  FETCH ini_stock BULK COLLECT
                    INTO ini_stockrecord LIMIT 100;
                  IF ini_stockrecord.count > 0 THEN
                    FOR x IN ini_stockrecord.first .. ini_stockrecord.last
                    LOOP
                    
                      varcuv     := ini_stockrecord(x).varcuv;
                      varnumprem := ini_stockrecord(x).varnumprem;
                      varcopa    := ini_stockrecord(x).varcopa;
                      varnivprem := ini_stockrecord(x).varnivprem;
                      varunid    := ini_stockrecord(x).varunid;
                    
                      DELETE inc_premi_elegi
                       WHERE num_prem = varnumprem
                         AND clie_oid_clie = v_cab_clie_oid_clie(i)
                         AND copa_oid_para_gral = varcopa
                         AND panp_oid_para_nive_prem = varnivprem;
                    
                      FOR y IN 1 .. varunid
                      LOOP
                      
                        INSERT INTO inc_premi_elegi
                          (oid_prem_eleg,
                           num_prem,
                           clie_oid_clie,
                           copa_oid_para_gral,
                           panp_oid_para_nive_prem,
                           perd_oid_peri,
                           usu_crea,
                           tip_rece,
                           fec_sist)
                        VALUES
                          (inc_prel_seq.nextval,
                           varnumprem,
                           v_cab_clie_oid_clie(i),
                           varcopa,
                           varnivprem,
                           v_cab_perd_oid_peri(i),
                           psusuario,
                           'A',
                           SYSDATE);
                      END LOOP;
                    END LOOP;
                  END IF;
                  EXIT WHEN ini_stock%NOTFOUND;
                END LOOP;
                CLOSE ini_stock;
              
              END IF;
            
              /*Ingreso de Clasificiones Por Origen de Pedido*/
              IF (lsparametrotipopedido IS NOT NULL) THEN
              
                /*OCR PURO*/
                IF (v_cab_ind_rece_ocr(i) = '1' AND
                   v_cab_ind_rece_web(i) = '0' AND
                   v_cab_ind_rece_dd(i) = '0') THEN
                
                  v_cab_cod_clasi(i) := lsparametroclasiocr;
                  /*WEB PURO*/
                ELSIF (v_cab_ind_rece_ocr(i) = '0' AND
                      v_cab_ind_rece_web(i) = '1' AND
                      v_cab_ind_rece_dd(i) = '0') THEN
                
                  v_cab_cod_clasi(i) := lsparametroclasiweb;
                  /*DD PURO*/
                ELSIF (v_cab_ind_rece_ocr(i) = '0' AND
                      v_cab_ind_rece_web(i) = '0' AND
                      v_cab_ind_rece_dd(i) = '1') THEN
                
                  v_cab_cod_clasi(i) := lsparametroclasidd;
                  /*MIXTO*/
                ELSE
                  v_cab_cod_clasi(i) := lsparametroclasimixto;
                
                END IF;
              
                DELETE mae_clien_clasi
                 WHERE ctsu_oid_clie_tipo_subt =
                       (SELECT oid_clie_tipo_subt
                          FROM mae_clien_tipo_subti
                         WHERE clie_oid_clie = v_cab_clie_oid_clie(i)
                           AND ticl_oid_tipo_clie = 2)
                   AND tccl_oid_tipo_clasi =
                       (SELECT oid_tipo_clas
                          FROM mae_tipo_clasi_clien
                         WHERE sbti_oid_subt_clie =
                               v_cab_sbti_oid_subt_clie(i)
                           AND cod_tipo_clas = lsparametrotipopedido);
              
                INSERT INTO mae_clien_clasi
                  (oid_clie_clas,
                   ctsu_oid_clie_tipo_subt,
                   clas_oid_clas,
                   perd_oid_peri,
                   tccl_oid_tipo_clasi,
                   fec_clas,
                   ind_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clcl_seq.nextval,
                   (SELECT oid_clie_tipo_subt
                      FROM mae_clien_tipo_subti
                     WHERE clie_oid_clie = v_cab_clie_oid_clie(i)
                       AND ticl_oid_tipo_clie = 2),
                   (SELECT oid_clas
                      FROM mae_clasi,
                           mae_tipo_clasi_clien
                     WHERE tccl_oid_tipo_clas = oid_tipo_clas
                       AND sbti_oid_subt_clie = v_cab_sbti_oid_subt_clie(i)
                       AND cod_clas = v_cab_cod_clasi(i)),
                   v_cab_perd_oid_peri(i),
                   (SELECT oid_tipo_clas
                      FROM mae_tipo_clasi_clien
                     WHERE sbti_oid_subt_clie = v_cab_sbti_oid_subt_clie(i)
                       AND cod_tipo_clas = lsparametrotipopedido),
                   trunc(SYSDATE),
                   1,
                   SYSDATE);
              
              END IF;
            
              /*CUADRE DE OFERTAS PEDIDOS ONLINE*/
              IF v_cab_ind_rece_onli(i) = '1' THEN
                ped_pkg_cuadr_ofert.ped_pr_stock_online_1(v_cab_soca_oid_soli_cabe_refe(i));
              END IF;
            END IF;
          END LOOP;
        
          /*Actualiza indicador de PEdido GP1*/
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            UPDATE int_solic_conso_cabec iscc
               SET iscc.ind_ocs_proc            = v_cab_ind_ocs_proc(i),
                   iscc.soca_oid_soli_cabe_refe = v_cab_soca_oid_soli_cabe_refe(i),
                   iscc.fec_modi                = SYSDATE
             WHERE sec_nume_docu = v_cab_sec_nume_docu(i)
               AND num_lote = v_cab_num_lote(i);
        
        END IF;
      
        EXIT WHEN c_cabecera%NOTFOUND;
      
      END LOOP;
      CLOSE c_cabecera;
      sto_pr_actua_matri_factu(lscodigodocumentodetalle, psnumeroproceso);
      sto_pr_actua_limit_venta(pscodtipodocu, psnumeroproceso);
    
    END IF;
    sto_pkg_gener.sto_pr_after_envio(pscodigopais,
                                     pscodtipodocu,
                                     psnumeroproceso);
  
    BEGIN
      UPDATE pre_ofert_detal
         SET precio_unitario = round(imp_prec_cata / val_fact_repe, 0)
       WHERE precio_unitario - trunc(precio_unitario) > 0
         AND imp_prec_cata - trunc(imp_prec_cata) = 0
         AND EXISTS
       (SELECT 1
                FROM seg_pais,
                     seg_moned
               WHERE cod_pais =
                     (SELECT DISTINCT cod_pais FROM bas_ctrl_fact)
                 AND mone_oid_mone = oid_mone
                 AND num_deci = 0);
    
      UPDATE pre_ofert_detal
         SET num_posi_rank = 0
       WHERE num_posi_rank IS NULL
         AND ofer_oid_ofer IN
             (SELECT a.oid_ofer
                FROM pre_ofert             a,
                     pre_matri_factu_cabec b
               WHERE a.mfca_oid_cabe = b.oid_cabe
                    --AND b.perd_oid_peri = lnoidperi
                 AND a.coes_oid_estr IN (2003, 2007, 2022));
    
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
    END;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_OCC_ENVIO_SICC: ' || ls_sqlerrm);
    
  END sto_pr_occ_envio_sicc;

  /**************************************************************************
  Descripcion       : SSTO_PR_SCC_ENVIO_SICC
                      Envio de SCC a SICC
  Fecha Creacion    : 27/05/2008
  
  Parametros Entrada:
      psCodigoPais        : Codigo de pais
      psCodigoTipoDoc     : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario           : Codigo de Usuario
  
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_envioscc IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_pais, -- posicion 61
             cons.val_ape1_fiad, -- posicion 32
             cons.val_ape2_fiad, -- posicion 33
             cons.val_nom1_fiad, -- posicion 34
             cons.val_nom2_fiad, -- posicion 35
             cons.fec_proc, -- posicion 5
             cons.fec_naci, --posicion 15
             cons.ind_esta_civi, --posicion 19
             cons.oid_peri, --posicion 64
             cons.val_mail_clie, ---posicion 29
             cons.oid_terr_admi, --posicion 63
             cons.tip_via_clie, --posicion 47
             cons.oid_terr, --posicion 62
             cons.num_dire_clie, --posicion 49
             cons.val_nomb_vicl, --posicion 48
             cons.val_dire_clie, --posicion 21
             cons.cod_depa_clie, --posicion 50
             cons.cod_prov_clie, --posicion 51
             cons.cod_dist_clie, --posicion 52
             cons.cod_sect_clie, --posicion 53
             cons.val_tefl_fiad, --posicion 40
             cons.val_celu_fiad, --posicion 41
             cons.val_telf_trfi, --posicion 42
             cons.cod_clie_rete, --posicion 9
             cons.num_premi, --posicion 66
             cons.oid_para_nive_prem, --posicion 65
             cons.tip_docu, --posicion 16
             cons.num_docu_iden, --posicion 17
             cons.num_ruc, --posicion 18
             cons.val_ape1, --posicion 11
             cons.val_ape2, -- posicion 12
             cons.val_nom1, --posicion 13
             cons.val_nom2, --posicion 14
             cons.val_telf_clie, -- posicion 25
             cons.val_celu_clie, --posicion 26
             cons.val_telf_trab, -- posicion 27
             cons.oid_para_gral, --POSICION  70
             cons.cod_prem, --POSICION 10
             cons.cod_docu_idfi, --posicion 31
             cons.cod_fiad, --posicion 68
             cons.tip_via_fiad, --posicion 54
             cons.val_nomb_vifi, --posicion 55
             cons.num_dire_fiad, --posicion 56
             cons.cod_depa_fiad, --posicion 57
             cons.cod_prov_fiad, --posicion 58
             cons.cod_dist_fiad, --posicion 59
             cons.cod_sect_fiad, --posicon 60
             cons.val_dire_fiad, --posicion 36
             cons.tip_docu_fiad, --posicion 30
             cons.ind_nive_educ, --posicion 20
             cons.num_docu, --posicion 4
             cons.val_dire_entre_clie, --posicion 71
             cons.val_tele_entre_clie, --posicion 75
             cons.val_celu_entre_clie, --posicion 76
             cons.val_barr_fiad, --Posicion 37
             cons.val_ciud_fiad, -- Posicion 38
             cons.val_depa_fiad, -- Posicion 39
             cons.val_tipo_vinc_fiad, --Posicion 95
             cons.val_nomb_empr_fiad, -- Posicion 92
             cons.val_dire_empr_fiad, -- Posicion 93
             cons.val_carg_fiad, -- Posicion 94
             cons.val_nom1_refe_fami_clie, --Posicion 78
             cons.val_ape1_refe_fami_clie, --Posicion 77
             cons.val_dire_refe_fami_clie, --Posicion 79
             cons.val_barr_refe_fami_clie, --Posicion  80
             cons.val_ciud_refe_fami_clie, --Posicion  82
             cons.val_depa_refe_fami_clie, --Posicion  83
             cons.val_tele_refe_fami_clie, --Posicion  84
             cons.val_celu_refe_fami_clie, --Posicion  85
             cons.val_tipo_vinc_refe_fami_clie, --Posicion 86
             cons.val_ape1_refe_nofa_clie, --Posicion 87
             cons.val_nom1_refe_nofa_clie, --Posicion 88
             cons.val_tele_refe_nofa_clie, --Posicion 89
             cons.val_celu_refe_nofa_clie, --Posicion 90
             cons.val_tipo_vinc_refe_nofa_clie, --Posicion 91
             cons.val_dire_refe_nofa_clie, --Posicion 104
             cons.val_barr_clie,
             cons.cod_sexo,
             cons.ind_vend_mar1,
             cons.ind_vend_mar2,
             cons.ind_vend_mar3,
             cons.ind_vend_mar4,
             cons.ind_vend_mar5,
             cons.ind_vend_mar6,
             cons.ind_vend_mar7,
             cons.cod_marc_vema,
             cons.ind_requ_impr_bole,
             cons.cod_lide_reco,
             cons.val_obse,
             cons.oid_naci,
             cons.ciud_cod_ciud_domi,
             cons.cod_depa_clie ciud_cod_ugeo_regi_domi,
             cons.des_villa_pobl_domi,
             cons.val_codi_post_clie,
             cons.oid_tipo_pers,
             cons.oid_orig_ingr,
             cons.dom_manz,
             cons.dom_etap,
             cons.dom_call_prin,
             cons.dom_call_secu,
             cons.dom_num,
             cons.dom_refe,
             cons.ent_manz,
             cons.ent_etap,
             cons.ent_call_prin,
             cons.ent_call_secu,
             cons.ent_num,
             cons.ent_refe,
             cons.cod_terr_corr,
             cons.val_barr_entre_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
  
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oidpais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;
    TYPE t_primerapellfiador IS TABLE OF int_solic_conso_credi.val_ape1_fiad%TYPE;
    TYPE t_secondapellfiador IS TABLE OF int_solic_conso_credi.val_ape2_fiad%TYPE;
    TYPE t_primernomfiador IS TABLE OF int_solic_conso_credi.val_nom1_fiad%TYPE;
    TYPE t_secondnomfiador IS TABLE OF int_solic_conso_credi.val_nom2_fiad%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_solic_conso_credi.fec_proc%TYPE;
    TYPE t_fechanacimiento IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
    TYPE t_estadocivil IS TABLE OF int_solic_conso_credi.ind_esta_civi%TYPE;
    TYPE t_oidperiodo IS TABLE OF int_solic_conso_credi.oid_peri%TYPE;
    TYPE t_valormail IS TABLE OF int_solic_conso_credi.val_mail_clie%TYPE;
    TYPE t_oidterradmin IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;
    TYPE t_tipovia IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_oidterritorio IS TABLE OF int_solic_conso_credi.oid_terr%TYPE;
    TYPE t_direccioncliente IS TABLE OF int_solic_conso_credi.num_dire_clie%TYPE;
    TYPE t_valnombrevia IS TABLE OF int_solic_conso_credi.val_nomb_vicl%TYPE;
    TYPE t_valdireccion IS TABLE OF int_solic_conso_credi.val_dire_clie%TYPE;
    TYPE t_coddepartamento IS TABLE OF int_solic_conso_credi.cod_depa_clie%TYPE;
    TYPE t_codprovincia IS TABLE OF int_solic_conso_credi.cod_prov_clie%TYPE;
    TYPE t_coddistrito IS TABLE OF int_solic_conso_credi.cod_dist_clie%TYPE;
    TYPE t_codsector IS TABLE OF int_solic_conso_credi.cod_sect_clie%TYPE;
    TYPE t_valtelefiador IS TABLE OF int_solic_conso_credi.val_tefl_fiad%TYPE;
    TYPE t_valcelufiador IS TABLE OF int_solic_conso_credi.val_celu_fiad%TYPE;
    TYPE t_valtrabfiador IS TABLE OF int_solic_conso_credi.val_telf_trfi%TYPE;
    TYPE t_codclientereco IS TABLE OF int_solic_conso_credi.cod_clie_rete%TYPE;
    TYPE t_numeropremio IS TABLE OF int_solic_conso_credi.num_premi%TYPE;
    TYPE t_oidnivelpremio IS TABLE OF int_solic_conso_credi.oid_para_nive_prem%TYPE;
    TYPE t_tipodocumento IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;
    TYPE t_numerodocumento IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_ruccliente IS TABLE OF int_solic_conso_credi.num_ruc%TYPE;
    TYPE t_apell1cliente IS TABLE OF int_solic_conso_credi.val_ape1%TYPE;
    TYPE t_apell2cliente IS TABLE OF int_solic_conso_credi.val_ape2%TYPE;
    TYPE t_nom1cliente IS TABLE OF int_solic_conso_credi.val_nom1%TYPE;
    TYPE t_nom2cliente IS TABLE OF int_solic_conso_credi.val_nom2%TYPE;
    TYPE t_telfcliente IS TABLE OF int_solic_conso_credi.val_telf_clie%TYPE;
    TYPE t_celcliente IS TABLE OF int_solic_conso_credi.val_celu_clie%TYPE;
    TYPE t_teltrabcliente IS TABLE OF int_solic_conso_credi.val_telf_trab%TYPE;
    TYPE t_oid_para_gral IS TABLE OF int_solic_conso_credi.oid_para_gral%TYPE;
    TYPE t_cod_prem IS TABLE OF int_solic_conso_credi.cod_prem%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_cod_fiad IS TABLE OF int_solic_conso_credi.cod_fiad%TYPE;
    TYPE t_tip_via_fiad IS TABLE OF int_solic_conso_credi.tip_via_fiad%TYPE;
    TYPE t_val_nomb_vifi IS TABLE OF int_solic_conso_credi.val_nomb_vifi%TYPE;
    TYPE t_num_dire_fiad IS TABLE OF int_solic_conso_credi.num_dire_fiad%TYPE;
    TYPE t_cod_depa_fiad IS TABLE OF int_solic_conso_credi.cod_depa_fiad%TYPE;
    TYPE t_cod_prov_fiad IS TABLE OF int_solic_conso_credi.cod_prov_fiad%TYPE;
    TYPE t_cod_dist_fiad IS TABLE OF int_solic_conso_credi.cod_dist_fiad%TYPE;
    TYPE t_cod_sect_fiad IS TABLE OF int_solic_conso_credi.cod_sect_fiad%TYPE;
    TYPE t_val_dire_fiad IS TABLE OF int_solic_conso_credi.val_dire_fiad%TYPE;
    TYPE t_tip_docu_fiad IS TABLE OF int_solic_conso_credi.tip_docu_fiad%TYPE;
  
    TYPE t_ind_nive_educ IS TABLE OF int_solic_conso_credi.ind_nive_educ%TYPE;
    TYPE t_val_nume_docu IS TABLE OF int_solic_conso_credi.num_docu%TYPE;
    TYPE t_val_dire_entr IS TABLE OF int_solic_conso_credi.val_dire_entre_clie%TYPE;
    TYPE t_val_tele_entr IS TABLE OF int_solic_conso_credi.val_tele_entre_clie%TYPE;
    TYPE t_val_celu_entr IS TABLE OF int_solic_conso_credi.val_celu_entre_clie%TYPE;
    TYPE t_val_barr_fiad IS TABLE OF int_solic_conso_credi.val_barr_fiad%TYPE;
    TYPE t_val_ciud_fiad IS TABLE OF int_solic_conso_credi.val_ciud_fiad%TYPE;
    TYPE t_val_depa_fiad IS TABLE OF int_solic_conso_credi.val_depa_fiad%TYPE;
    TYPE t_val_tipo_vinc_fiad IS TABLE OF int_solic_conso_credi.val_tipo_vinc_fiad%TYPE;
    TYPE t_val_nomb_empr_fiad IS TABLE OF int_solic_conso_credi.val_nomb_empr_fiad%TYPE;
    TYPE t_val_dire_empr_fiad IS TABLE OF int_solic_conso_credi.val_dire_empr_fiad%TYPE;
    TYPE t_val_carg_fiad IS TABLE OF int_solic_conso_credi.val_carg_fiad%TYPE;
    TYPE t_val_nomb_refe_fami IS TABLE OF int_solic_conso_credi.val_nom1_refe_fami_clie%TYPE;
    TYPE t_val_apel_refe_fami IS TABLE OF int_solic_conso_credi.val_ape1_refe_fami_clie%TYPE;
    TYPE t_val_dire_refe_fami IS TABLE OF int_solic_conso_credi.val_dire_refe_fami_clie%TYPE;
  
    TYPE t_val_barr_refe_fami IS TABLE OF int_solic_conso_credi.val_barr_refe_fami_clie%TYPE;
    TYPE t_val_ciud_refe_fami IS TABLE OF int_solic_conso_credi.val_ciud_refe_fami_clie%TYPE;
    TYPE t_val_depa_refe_fami IS TABLE OF int_solic_conso_credi.val_depa_refe_fami_clie%TYPE;
    TYPE t_val_tele_refe_fami IS TABLE OF int_solic_conso_credi.val_tele_refe_fami_clie%TYPE;
    TYPE t_val_celu_refe_fami IS TABLE OF int_solic_conso_credi.val_celu_refe_fami_clie%TYPE;
    TYPE t_val_tipo_vinc_refe_fami IS TABLE OF int_solic_conso_credi.val_tipo_vinc_refe_fami_clie%TYPE;
  
    TYPE t_val_ape1_refe_nofa IS TABLE OF int_solic_conso_credi.val_ape1_refe_nofa_clie%TYPE;
    TYPE t_val_nomb_refe_nofa IS TABLE OF int_solic_conso_credi.val_nom1_refe_nofa_clie%TYPE;
    TYPE t_val_tele_refe_nofa IS TABLE OF int_solic_conso_credi.val_tele_refe_nofa_clie%TYPE;
    TYPE t_val_celu_refe_nofa IS TABLE OF int_solic_conso_credi.val_celu_refe_nofa_clie%TYPE;
    TYPE t_val_tipo_vinc_refe_nofa IS TABLE OF int_solic_conso_credi.val_tipo_vinc_refe_nofa_clie%TYPE;
    TYPE t_val_dire_refe_nofa IS TABLE OF int_solic_conso_credi.val_dire_refe_nofa_clie%TYPE;
    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_credi.val_barr_clie%TYPE;
    TYPE t_val_codsexo_clie IS TABLE OF int_solic_conso_credi.cod_sexo%TYPE;
  
    TYPE t_ind_vend_mar1 IS TABLE OF int_solic_conso_credi.ind_vend_mar1%TYPE;
    TYPE t_ind_vend_mar2 IS TABLE OF int_solic_conso_credi.ind_vend_mar2%TYPE;
    TYPE t_ind_vend_mar3 IS TABLE OF int_solic_conso_credi.ind_vend_mar3%TYPE;
    TYPE t_ind_vend_mar4 IS TABLE OF int_solic_conso_credi.ind_vend_mar4%TYPE;
    TYPE t_ind_vend_mar5 IS TABLE OF int_solic_conso_credi.ind_vend_mar5%TYPE;
    TYPE t_ind_vend_mar6 IS TABLE OF int_solic_conso_credi.ind_vend_mar6%TYPE;
    TYPE t_ind_vend_mar7 IS TABLE OF int_solic_conso_credi.ind_vend_mar7%TYPE;
    TYPE t_cod_marc_vema IS TABLE OF int_solic_conso_credi.cod_marc_vema%TYPE;
  
    TYPE t_ind_requ_impr_bole IS TABLE OF int_solic_conso_credi.ind_requ_impr_bole%TYPE;
    TYPE t_cod_lide_reco IS TABLE OF int_solic_conso_credi.cod_lide_reco%TYPE;
  
    TYPE t_val_obse IS TABLE OF int_solic_conso_credi.val_obse%TYPE;
    TYPE t_oid_naci IS TABLE OF int_solic_conso_credi.oid_naci%TYPE;
    TYPE t_ciud_cod_ciud_domi IS TABLE OF int_solic_conso_credi.ciud_cod_ciud_domi%TYPE;
    TYPE t_ciud_cod_ugeo_regi_domi IS TABLE OF int_solic_conso_credi.ciud_cod_ugeo_regi_domi%TYPE;
    TYPE t_des_villa_pobl_domi IS TABLE OF int_solic_conso_credi.des_villa_pobl_domi%TYPE;
    TYPE t_val_codi_post_clie IS TABLE OF int_solic_conso_credi.val_codi_post_clie%TYPE;    
    TYPE t_oid_tipo_pers   IS TABLE OF int_solic_conso_credi.oid_tipo_pers%TYPE; 
    TYPE t_oid_orig_ingr   IS TABLE OF int_solic_conso_credi.oid_orig_ingr%TYPE; 
    TYPE t_dom_manz        IS TABLE OF int_solic_conso_credi.dom_manz%TYPE;      
    TYPE t_dom_etap        IS TABLE OF int_solic_conso_credi.dom_etap%TYPE;      
    TYPE t_dom_call_prin   IS TABLE OF int_solic_conso_credi.dom_call_prin%TYPE;
    TYPE t_dom_call_secu   IS TABLE OF int_solic_conso_credi.dom_call_secu%TYPE; 
    TYPE t_dom_num         IS TABLE OF int_solic_conso_credi.dom_num%TYPE;       
    TYPE t_dom_refe        IS TABLE OF int_solic_conso_credi.dom_refe%TYPE;      
    TYPE t_ent_manz        IS TABLE OF int_solic_conso_credi.ent_manz%TYPE;      
    TYPE t_ent_etap        IS TABLE OF int_solic_conso_credi.ent_etap%TYPE;      
    TYPE t_ent_call_prin   IS TABLE OF int_solic_conso_credi.ent_call_prin%TYPE;
    TYPE t_ent_call_secu   IS TABLE OF int_solic_conso_credi.ent_call_secu%TYPE; 
    TYPE t_ent_num         IS TABLE OF int_solic_conso_credi.ent_num%TYPE;       
    TYPE t_ent_refe        IS TABLE OF int_solic_conso_credi.ent_refe%TYPE;    
    TYPE t_cod_terr_corr   IS TABLE OF int_solic_conso_credi.cod_terr_corr%TYPE;  
    TYPE t_val_barr_entre_clie      IS TABLE OF int_solic_conso_credi.val_barr_entre_clie%TYPE;  
  
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;
  
    v_secnumdocu        t_secnumdocu;
    v_oidpais           t_oidpais;
    v_primerapellfiador t_primerapellfiador;
    v_secondapellfiador t_secondapellfiador;
    v_primernomfiador   t_primernomfiador;
    v_secondnomfiador   t_secondnomfiador;
    v_fechaproceso      t_fechaproceso;
    v_fechanacimiento   t_fechanacimiento;
  
    v_estadocivil      t_estadocivil;
    v_oidperiodo       t_oidperiodo;
    v_valormail        t_valormail;
    v_oidterradmin     t_oidterradmin;
    v_tipovia          t_tipovia;
    v_oidterritorio    t_oidterritorio;
    v_direccioncliente t_direccioncliente;
    v_valnombrevia     t_valnombrevia;
    v_valdireccion     t_valdireccion;
    v_coddepartamento  t_coddepartamento;
    v_codprovincia     t_codprovincia;
    v_coddistrito      t_coddistrito;
    v_codsector        t_codsector;
    v_valtelefiador    t_valtelefiador;
    v_valcelufiador    t_valcelufiador;
    v_valtrabfiador    t_valtrabfiador;
    v_codclientereco   t_codclientereco;
    v_numeropremio     t_numeropremio;
    v_oidnivelpremio   t_oidnivelpremio;
    v_tipodocumento    t_tipodocumento;
    v_numerodocumento  t_numerodocumento;
    v_ruccliente       t_ruccliente;
    v_apell1cliente    t_apell1cliente;
    v_apell2cliente    t_apell2cliente;
    v_nom1cliente      t_nom1cliente;
    v_nom2cliente      t_nom2cliente;
    v_telfcliente      t_telfcliente;
    v_celcliente       t_celcliente;
    v_teltrabcliente   t_teltrabcliente;
    v_oid_para_gral    t_oid_para_gral;
    v_cod_prem         t_cod_prem;
    v_cod_docu_idfi    t_cod_docu_idfi;
    v_cod_fiad         t_cod_fiad;
    v_tip_via_fiad     t_tip_via_fiad;
    v_val_nomb_vifi    t_val_nomb_vifi;
    v_num_dire_fiad    t_num_dire_fiad;
    v_cod_depa_fiad    t_cod_depa_fiad;
    v_cod_prov_fiad    t_cod_prov_fiad;
    v_cod_dist_fiad    t_cod_dist_fiad;
    v_cod_sect_fiad    t_cod_sect_fiad;
    v_val_dire_fiad    t_val_dire_fiad;
    v_tip_docu_fiad    t_tip_docu_fiad;
  
    v_ind_nive_educ t_ind_nive_educ;
    v_val_nume_docu t_val_nume_docu;
    v_val_dire_entr t_val_dire_entr;
    v_val_tele_entr t_val_tele_entr;
    v_val_celu_entr t_val_celu_entr;
    v_val_barr_fiad t_val_barr_fiad;
    v_val_ciud_fiad t_val_ciud_fiad;
    v_val_depa_fiad t_val_depa_fiad;
  
    v_val_tipo_vinc_fiad t_val_tipo_vinc_fiad;
    v_val_nomb_empr_fiad t_val_nomb_empr_fiad;
    v_val_dire_empr_fiad t_val_dire_empr_fiad;
    v_val_carg_fiad      t_val_carg_fiad;
    v_val_nomb_refe_fami t_val_nomb_refe_fami;
    v_val_apel_refe_fami t_val_apel_refe_fami;
    v_val_dire_refe_fami t_val_dire_refe_fami;
  
    v_val_barr_refe_fami      t_val_barr_refe_fami;
    v_val_ciud_refe_fami      t_val_ciud_refe_fami;
    v_val_depa_refe_fami      t_val_depa_refe_fami;
    v_val_tele_refe_fami      t_val_tele_refe_fami;
    v_val_celu_refe_fami      t_val_celu_refe_fami;
    v_val_tipo_vinc_refe_fami t_val_tipo_vinc_refe_fami;
  
    v_val_ape1_refe_nofa      t_val_ape1_refe_nofa;
    v_val_nomb_refe_nofa      t_val_nomb_refe_nofa;
    v_val_tele_refe_nofa      t_val_tele_refe_nofa;
    v_val_celu_refe_nofa      t_val_celu_refe_nofa;
    v_val_tipo_vinc_refe_nofa t_val_tipo_vinc_refe_nofa;
    v_val_dire_refe_nofa      t_val_dire_refe_nofa;
    v_val_barr_clie           t_val_barr_clie;
    v_val_codsexo_clie        t_val_codsexo_clie;
  
    v_val_ind_vend_mar1 t_ind_vend_mar1;
    v_val_ind_vend_mar2 t_ind_vend_mar2;
    v_val_ind_vend_mar3 t_ind_vend_mar3;
    v_val_ind_vend_mar4 t_ind_vend_mar4;
    v_val_ind_vend_mar5 t_ind_vend_mar5;
    v_val_ind_vend_mar6 t_ind_vend_mar6;
    v_val_ind_vend_mar7 t_ind_vend_mar7;
    v_val_cod_marc_vema t_cod_marc_vema;
  
    v_ind_requ_impr_bole t_ind_requ_impr_bole;
    v_cod_lide_reco      t_cod_lide_reco;
  
    v_val_obse                t_val_obse;
    v_oid_naci                t_oid_naci;
    v_ciud_cod_ciud_domi      t_ciud_cod_ciud_domi;
    v_ciud_cod_ugeo_regi_domi t_ciud_cod_ugeo_regi_domi;
    v_des_villa_pobl_domi     t_des_villa_pobl_domi;
  
    v_val_codi_post_clie t_val_codi_post_clie;
    v_oid_tipo_pers    t_oid_tipo_pers;
    v_oid_orig_ingr    t_oid_orig_ingr;
    v_dom_manz         t_dom_manz;     
    v_dom_etap         t_dom_etap;     
    v_dom_call_prin    t_dom_call_prin;
    v_dom_call_secu    t_dom_call_secu;
    v_dom_num          t_dom_num;      
    v_dom_refe         t_dom_refe;     
    v_ent_manz         t_ent_manz;     
    v_ent_etap         t_ent_etap;     
    v_ent_call_prin    t_ent_call_prin;
    v_ent_call_secu    t_ent_call_secu;
    v_ent_num          t_ent_num;      
    v_ent_refe         t_ent_refe; 
    v_cod_terr_corr    t_cod_terr_corr;
    v_val_barr_entre_clie   t_val_barr_entre_clie;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
    lscod_zona zon_zona.cod_zona%TYPE;
    lscod_regi zon_regio.cod_regi%TYPE;
  
    lsnum_posi_nume_clie      seg_pais.num_posi_nume_clie%TYPE;
    lscodigo_tmp              ped_numer_solic.val_ulti_nume_soli%TYPE;
    lscodigo_tmp1             mae_clien.cod_clie%TYPE;
    lstmp_oid_clie            mae_clien.oid_clie%TYPE;
    lstmp_oid_clie_tipo_subti mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
  
    lstm_poid_clie_rete inc_clien_recte.oid_clie_rete%TYPE;
  
    lscodigomarca seg_marca.cod_marc%TYPE;
    lnidmarca     seg_marca.oid_marc%TYPE;
  
    lscodigo_tipo_asig mae_param_clien.cod_tipo_asig%TYPE;
    lsdigitochequeo    mae_clien.cod_digi_ctrl%TYPE;
  
    lstmp_cod_clie mae_clien.cod_digi_ctrl%TYPE;
  
    lscodtmpdigitochequeo VARCHAR2(10);
  
    lsparadigitochequeo sto_param_gener_occrr.val_param%TYPE;
    lsparagenecodclie   sto_param_gener_occrr.val_param%TYPE;
    lstipomodulo        mae_clien_modul.mod_vali%TYPE;
  
    contador_mae_clien_direc NUMBER;
    lnnumregistros           NUMBER;
    lnnumsoliinicio          NUMBER;
  
    lsdirbarrio       VARCHAR2(1);
    lsparadefaultsexo VARCHAR2(1);
    lnvendemarca      NUMBER;
    lsCampNew         VARCHAR2(1);
  
    lnidpais          NUMBER;
    lnoidclientercdte mae_clien.oid_clie%TYPE;
  
    isvalid NUMBER;
  
    lsparametrotipovinculider VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                   'STO_TIPO_VINCU_LIDER');
  
    lspartedireccscweb VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                'STO_DIREC_WEB'),
                                           'N');
  
    lspartedireccscwebPos number(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                'STO_DIREC_WEB_POS'),
                                           0);
                                             
  BEGIN
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    
    select val_para into lsCampNew 
    from bas_param_pais where cod_pais =pscodigopais
    and cod_para='055'
    and cod_sist='OCR';
  
    SELECT num_posi_nume_clie
      INTO lsnum_posi_nume_clie
      FROM seg_pais
     WHERE oid_pais = lnidpais;
  
    SELECT cod_tipo_asig
      INTO lscodigo_tipo_asig
      FROM mae_param_clien
     WHERE pais_oid_pais = lnidpais; --Posicion 61
  
    SELECT mod_vali
      INTO lstipomodulo
      FROM mae_clien_modul
     WHERE cod_pais = pscodigopais
       AND tip_vali = 'VAL_CODCLIE';
  
    lsparadigitochequeo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_PARAM_DIGI_CHEQ');
  
    lsparagenecodclie := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_GENER_COD_CLIE');
  
    lsdirbarrio := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_DIR_BARRIO');
  
    lsparadefaultsexo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_DEF_SEX_SCC');
  
    IF (lsparagenecodclie = '1' AND lscodigo_tipo_asig != 'M') THEN
    
      SELECT COUNT(1) INTO lnnumregistros FROM sto_tmp_docum_digit;
    
      lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                                'MAECLT',
                                                                NULL,
                                                                lnnumregistros,
                                                                NULL);
    
    END IF;
  
    OPEN c_envioscc;
    LOOP
      FETCH c_envioscc BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_secnumdocu,
             v_oidpais,
             v_primerapellfiador,
             v_secondapellfiador,
             v_primernomfiador,
             v_secondnomfiador,
             v_fechaproceso,
             v_fechanacimiento,
             v_estadocivil,
             v_oidperiodo,
             v_valormail,
             v_oidterradmin,
             v_tipovia,
             v_oidterritorio,
             v_direccioncliente,
             v_valnombrevia,
             v_valdireccion,
             v_coddepartamento,
             v_codprovincia,
             v_coddistrito,
             v_codsector,
             v_valtelefiador,
             v_valcelufiador,
             v_valtrabfiador,
             v_codclientereco,
             v_numeropremio,
             v_oidnivelpremio,
             v_tipodocumento,
             v_numerodocumento,
             v_ruccliente,
             v_apell1cliente,
             v_apell2cliente,
             v_nom1cliente,
             v_nom2cliente,
             v_telfcliente,
             v_celcliente,
             v_teltrabcliente,
             v_oid_para_gral,
             v_cod_prem,
             v_cod_docu_idfi,
             v_cod_fiad,
             v_tip_via_fiad,
             v_val_nomb_vifi,
             v_num_dire_fiad,
             v_cod_depa_fiad,
             v_cod_prov_fiad,
             v_cod_dist_fiad,
             v_cod_sect_fiad,
             v_val_dire_fiad,
             v_tip_docu_fiad,
             v_ind_nive_educ,
             v_val_nume_docu,
             v_val_dire_entr,
             v_val_tele_entr,
             v_val_celu_entr,
             v_val_barr_fiad,
             v_val_ciud_fiad,
             v_val_depa_fiad,
             v_val_tipo_vinc_fiad,
             v_val_nomb_empr_fiad,
             v_val_dire_empr_fiad,
             v_val_carg_fiad,
             v_val_nomb_refe_fami,
             v_val_apel_refe_fami,
             v_val_dire_refe_fami,
             v_val_barr_refe_fami,
             v_val_ciud_refe_fami,
             v_val_depa_refe_fami,
             v_val_tele_refe_fami,
             v_val_celu_refe_fami,
             v_val_tipo_vinc_refe_fami,
             v_val_ape1_refe_nofa,
             v_val_nomb_refe_nofa,
             v_val_tele_refe_nofa,
             v_val_celu_refe_nofa,
             v_val_tipo_vinc_refe_nofa,
             v_val_dire_refe_nofa,
             v_val_barr_clie,
             v_val_codsexo_clie,
             v_val_ind_vend_mar1,
             v_val_ind_vend_mar2,
             v_val_ind_vend_mar3,
             v_val_ind_vend_mar4,
             v_val_ind_vend_mar5,
             v_val_ind_vend_mar6,
             v_val_ind_vend_mar7,
             v_val_cod_marc_vema,
             v_ind_requ_impr_bole,
             v_cod_lide_reco,
             v_val_obse,
             v_oid_naci,
             v_ciud_cod_ciud_domi,
             v_ciud_cod_ugeo_regi_domi,
             v_des_villa_pobl_domi,
             v_val_codi_post_clie,
             v_oid_tipo_pers,  
             v_oid_orig_ingr,  
             v_dom_manz,       
             v_dom_etap,       
             v_dom_call_prin,  
             v_dom_call_secu,  
             v_dom_num,        
             v_dom_refe,       
             v_ent_manz,       
             v_ent_etap,       
             v_ent_call_prin,  
             v_ent_call_secu,  
             v_ent_num,        
             v_ent_refe,
             v_cod_terr_corr,
             v_val_barr_entre_clie     
             LIMIT rows;
    
      IF v_codpais.count > 0 THEN
      
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
        
          SELECT mae_clie_seq.nextval INTO lstmp_oid_clie FROM dual;
          SELECT mae_ctsu_seq.nextval
            INTO lstmp_oid_clie_tipo_subti
            FROM dual;
        
          IF ((lsparagenecodclie != '0') OR (v_codclie(i) IS NULL)) THEN
          
            IF lscodigo_tipo_asig = 'M' THEN
              v_codclie(i) := lpad(v_numerodocumento(i),
                                   lsnum_posi_nume_clie,
                                   '0');
            ELSE
            
              lnnumsoliinicio := lnnumsoliinicio + 1;
            
              lscodigo_tmp := lnnumsoliinicio;
            
              IF lscodigo_tipo_asig = 'A' THEN
              
                IF (lstipomodulo = 'MOD11') THEN
                
                  lsdigitochequeo := sto_pkg_gener.sto_fn_devue_modul_once(lscodigo_tmp,
                                                                           pscodigopais);
                  v_codclie(i) := lpad(lscodigo_tmp || lsdigitochequeo,
                                       lsnum_posi_nume_clie,
                                       '0');
                ELSIF (lstipomodulo = 'MOD03') THEN
                
                  lscodigo_tmp1 := lpad(lscodigo_tmp1,
                                        lsnum_posi_nume_clie,
                                        '0');
                
                  IF lsparagenecodclie = '2' THEN
                    lscodigo_tmp1 := mae_pkg_proce_clien.mae_fn_obten_secue_codig_clien(pscodigopais);
                    lscodigo_tmp1 := lpad(lscodigo_tmp1,
                                          lsnum_posi_nume_clie,
                                          '0');
                  END IF;
                
                  lscodtmpdigitochequeo := substr(lscodigo_tmp1,
                                                  length(lscodigo_tmp1) - 5,
                                                  length(lscodigo_tmp1));
                  lsdigitochequeo := sto_pkg_gener.sto_fn_devue_codig_refer(lscodtmpdigitochequeo,
                                                                            pscodigopais);
                  v_codclie(i) := lpad(lscodigo_tmp1,
                                       lsnum_posi_nume_clie,
                                       '0');
                ELSIF (lstipomodulo = 'MOD02') THEN
                
                  lscodigo_tmp          := lpad(lscodigo_tmp,
                                                lsnum_posi_nume_clie,
                                                '0');
                  lscodtmpdigitochequeo := substr(lscodigo_tmp,
                                                  length(lscodigo_tmp) - 6,
                                                  length(lscodigo_tmp));
                
                  lsdigitochequeo := sto_pkg_gener.sto_fn_devue_codre_esika(lscodtmpdigitochequeo,
                                                                            pscodigopais);
                
                  lsdigitochequeo := lpad(lsdigitochequeo, 2, '0');
                  v_codclie(i) := lpad(lscodigo_tmp,
                                       lsnum_posi_nume_clie,
                                       '0');
                ELSIF (lstipomodulo = 'MODPR') THEN
                
                  v_codclie(i) := lpad(lscodigo_tmp,
                                       lsnum_posi_nume_clie,
                                       '0');
                
                  SELECT mae_pkg_proce_clien.mae_fn_valid_modul_ptr(v_codclie(i))
                    INTO lstmp_cod_clie
                    FROM dual;
                
                  lsdigitochequeo := lstmp_cod_clie;
                
                ELSE
                  v_codclie(i) := lpad(lscodigo_tmp,
                                       lsnum_posi_nume_clie,
                                       '0');
                END IF;
              
              END IF;
            
            END IF;
          
          END IF;
        
          --Insercion en MAE_CLIEN
          INSERT INTO mae_clien
            (oid_clie,
             cod_clie,
             ind_fich_insc,
             pais_oid_pais,
             cod_digi_ctrl,
             val_ape1,
             val_ape2,
             val_nom1,
             val_nom2,
             val_trat,
             val_crit_bus1,
             val_crit_bus2,
             cod_sexo,
             fec_ingr,
             fopa_oid_form_pago,
             val_apel_casa,
             fec_crea,
             fec_ulti_actu,
             fec_ulti_gene_cupo,
             ind_orig_regi,
             usu_modi)
          VALUES
            (lstmp_oid_clie,
             v_codclie(i),
             0,
             v_oidpais(i),
             decode(lsparadigitochequeo,
                    NULL,
                    lsdigitochequeo,
                    lsparadigitochequeo || lsdigitochequeo),
             v_apell1cliente(i),
             v_apell2cliente(i),
             v_nom1cliente(i),
             v_nom2cliente(i),
             '3',
             v_apell1cliente(i),
             v_nom1cliente(i),
             decode(v_val_codsexo_clie(i),
                    NULL,
                    decode(lsparadefaultsexo,
                           NULL,
                           v_val_codsexo_clie(i),
                           lsparadefaultsexo),
                    v_val_codsexo_clie(i)),
             to_date(v_fechaproceso(i), 'DD/MM/YY'),
             NULL,
             NULL,
             v_fechaproceso(i),
             v_fechaproceso(i),
             v_fechaproceso(i),
             'O',
             psusuario);
        
          --Insercion en MAE_CLIEN_DATOS_ADICI
        
          INSERT INTO mae_clien_datos_adici
            (clie_oid_clie,
             nsep_oid_nsep,
             oid_clie_dato_adic,
             cod_empl,
             fec_naci,
             val_edad,
             val_ocup,
             val_prof,
             val_cent_trab,
             val_carg_dese,
             val_cent_estu,
             val_nive_soci_eco3,
             num_hijo,
             num_pers_depe,
             num_camp_sin_pedi,
             imp_ingr_fami,
             imp_mont_line_cred,
             niri_oid_nive_ries,
             nied_oid_nive_estu,
             snon_oid_naci,
             escv_oid_esta_civi,
             perd_oid_peri_nive_ries,
             perd_oid_peri_line_cred,
             ind_corr,
             ind_acti,
             esta_oid_esta_clie,
             tclv_oid_cicl_vida,
             val_emai,
             fec_ulti_actu,
             ind_impr_docu,
             usu_carg,
             fec_carg,
             tip_carg,
             tpes_oid_tipo_pers,
             orin_oid_orig_ingr)
          VALUES
            (lstmp_oid_clie,
             NULL,
             mae_clda_seq.nextval,
             NULL,
             
             trunc(v_fechanacimiento(i)),
             floor((months_between(SYSDATE, v_fechanacimiento(i)) / 12)),
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             0,
             NULL,
             0,
             nvl((SELECT oid_nive_ries
                   FROM car_nivri_secci
                  WHERE oid_secc =
                        (SELECT zscc_oid_secc
                           FROM zon_terri_admin
                          WHERE oid_terr_admi = v_oidterradmin(i))),
                 4),
             v_ind_nive_educ(i) + 2000, --NULL,
             --nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_SCC_COD_NACI'), 2001),
             nvl(v_oid_naci(i),
                 nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          'STO_SCC_COD_NACI'),
                     2001)),
             v_estadocivil(i) + 2000, --(SELECT oid_esta_civi FROM mae_estad_civil WHERE cod_esta_civi = TRIM(to_char(v_estadocivil(i), '00'))),
             v_oidperiodo(i),
             v_oidperiodo(i),
             1,
             1,
             1,
             NULL,
             NULL, --v_valormail(i),
             SYSDATE,
             CASE WHEN nvl(v_ruccliente(i), '0') <> 0 THEN '0' WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tipodocumento(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numerodocumento(i) IS NOT NULL THEN '0' ELSE
             decode(v_ind_requ_impr_bole(i), 'N', '0', '1') END,
             CASE WHEN nvl(v_ruccliente(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tipodocumento(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numerodocumento(i) IS NOT NULL THEN NULL ELSE psusuario END,
             CASE WHEN nvl(v_ruccliente(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tipodocumento(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numerodocumento(i) IS NOT NULL THEN NULL ELSE SYSDATE END,
             CASE WHEN nvl(v_ruccliente(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tipodocumento(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numerodocumento(i) IS NOT NULL THEN NULL ELSE '2' END,
             v_oid_tipo_pers(i),
             v_oid_orig_ingr(i));
          --decode(nvl(v_ruccliente(i), '0'), '0', '2', NULL));
        
          --decode(nvl(v_ruccliente(i),'0'),'0','1','0'))
        
          --Insercion en MAE_CLIEN_UNIDA_ADMIN
        
          INSERT INTO mae_clien_unida_admin
            (oid_clie_unid_admi,
             clie_oid_clie,
             perd_oid_peri_ini,
             perd_oid_peri_fin,
             ztad_oid_terr_admi,
             ind_acti,
             fec_ulti_actu,
             usu_modi,
             fec_camb)
          VALUES
            (mae_cuad_seq.nextval,
             lstmp_oid_clie,
             v_oidperiodo(i),
             NULL,
             v_oidterradmin(i),
             1,
             SYSDATE,
             psusuario,
             SYSDATE);
        
          --Insercion en MAE_CLIEN_DIREC
        
          INSERT INTO mae_clien_direc
            (oid_clie_dire,
             clie_oid_clie,
             tidc_oid_tipo_dire,
             tivi_oid_tipo_via,
             terr_oid_terr,
             zvia_oid_via,
             num_ppal,
             val_nomb_via,
             val_cod_post,
             val_inte,
             val_manz,
             val_lote,
             val_km,
             val_obse,
             val_barr,
             val_nomb_fich,
             val_coor_x,
             val_coor_y,
             val_coor_z,
             ind_dire_ppal,
             ind_ctrl_inte_geor,
             fec_ulti_actu,
             cod_unid_geog,
             ind_elim,
             ciud_cod_ciud,
             ciud_cod_ugeo_regi,
             des_villa_pobl,
             usu_modi,
             val_nom_manz,
             val_eta_conj,
             val_cal_prin,
             val_cal_secu,
             teco_cod_terr_corr,
             num_ppri
             )
          VALUES
            (mae_cldi_seq.nextval,
             lstmp_oid_clie,
             2001,
             (SELECT oid_tipo_via
                FROM seg_tipo_via
               WHERE cod_tipo_via = nvl(v_tipovia(i), '99')),
             v_oidterritorio(i),
             NULL,
             v_direccioncliente(i),
             decode(lspartedireccscweb,
                    'S',
                    substr(v_valdireccion(i), 1, lspartedireccscwebPos),
                    v_valnombrevia(i)
                    )||
               decode( lsCampNew, '0', '' ,TRIM(v_dom_manz(i)) || ' '||TRIM(v_dom_etap(i)) || ' '||TRIM(v_dom_call_prin(i)) || ' '||
                       TRIM(v_dom_num(i)) || ' '||TRIM(v_dom_call_secu(i))),
             v_val_codi_post_clie(i),
             NULL,
             NULL,
             NULL,
             NULL,
             /*v_valdireccion(i) || decode(v_val_barr_clie(i),
             NULL,
             NULL,
             ' - ' || v_val_barr_clie(i)),*/
             decode(lspartedireccscweb,
                    'S',
                    substr(v_valdireccion(i), lspartedireccscwebPos+1),
                    v_valdireccion(i)) ||
             decode(lsdirbarrio,
                    'N',
                    '',
                    decode(v_val_barr_clie(i),
                           NULL,
                           NULL,
                           ' - ' || v_val_barr_clie(i)))||
                     TRIM(v_dom_refe(i)),
             v_val_barr_clie(i),
             NULL,
             0,
             0,
             0,
             1,
             'S',
             SYSDATE,
             v_coddepartamento(i) || v_codprovincia(i) || v_coddistrito(i) ||
             v_codsector(i),
             0,
             v_ciud_cod_ciud_domi(i),
             v_ciud_cod_ugeo_regi_domi(i),
             v_des_villa_pobl_domi(i),
             psusuario,
             v_dom_manz(i),
             v_dom_etap(i),
             v_dom_call_prin(i),
             v_dom_call_secu(i),
             v_cod_terr_corr(i),
             v_dom_num(i)
             );
        
          --IF v_val_nume_docu(i) is not NULL THEN
          /*          IF sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                               'COD_TIPO_DIRE_ENTR') IS NOT NULL AND
          
          v_val_dire_entr(i) IS NOT NULL THEN*/
          IF ( ( v_val_dire_entr(i) IS NOT NULL ) OR ( 
                 v_ent_manz(i)||v_ent_etap(i)||v_ent_call_prin(i)||v_ent_num(i)||v_ent_call_secu(i)||v_ent_refe(i)||v_val_barr_entre_clie(i)
              IS NOT NULL )) AND
             sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                  'COD_TIPO_DIRE_ENTR') IS NOT NULL THEN
            INSERT INTO mae_clien_direc
              (oid_clie_dire,
               clie_oid_clie,
               tidc_oid_tipo_dire,
               tivi_oid_tipo_via,
               terr_oid_terr,
               zvia_oid_via,
               num_ppal,
               val_nomb_via,
               val_cod_post,
               val_inte,
               val_manz,
               val_lote,
               val_km,
               val_obse,
               val_barr,
               val_nomb_fich,
               val_coor_x,
               val_coor_y,
               val_coor_z,
               ind_dire_ppal,
               ind_ctrl_inte_geor,
               fec_ulti_actu,
               cod_unid_geog,
               ind_elim,
               val_nom_manz,
               val_eta_conj,
               val_cal_prin,
               val_cal_secu,
               teco_cod_terr_corr,
               num_ppri
               )
            VALUES
              (mae_cldi_seq.nextval,
               lstmp_oid_clie,
               (SELECT oid_tipo_dire
                  FROM mae_tipo_direc
                 WHERE cod_tipo_dire =
                       sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'COD_TIPO_DIRE_ENTR')),
               (SELECT oid_tipo_via
                  FROM seg_tipo_via
                 WHERE cod_tipo_via = '99'),
               v_oidterritorio(i),
               NULL,
               NULL,
               --v_ent_num(i),
               decode ( lsCampNew, '0','',TRIM(v_ent_manz(i)) || ' '||TRIM(v_ent_etap(i)) || ' '||TRIM(v_ent_call_prin(i)) || ' '||
               TRIM(v_ent_num(i)) || ' '||TRIM(v_ent_call_secu(i))),
               ------NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               TRIM(v_val_dire_entr(i)) ||decode(lsCampNew, '0','',v_ent_refe(i) || ' ' )|| TRIM(v_val_obse(i)),
               decode(lsCampNew, '0','',v_val_barr_entre_clie(i)), 
               NULL,
               0,
               0,
               0,
               0,
               'S',
               SYSDATE,
               v_coddepartamento(i) || v_codprovincia(i) ||
               v_coddistrito(i) || v_codsector(i),
               0,
               v_ent_manz(i),
               v_ent_etap(i),
               v_ent_call_prin(i),
               v_ent_call_secu(i),
               v_cod_terr_corr(i),
               v_ent_num(i));
          END IF;
        
          --Insercion en MAE_CLIEN_COMUN
        
          IF v_telfcliente(i) IS NOT NULL THEN
          
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               1,
               'L',
               v_telfcliente(i),
               NULL,
               NULL,
               1,
               1,
               SYSDATE);
          END IF;
        
          IF v_celcliente(i) IS NOT NULL THEN
          
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               6,
               'L',
               v_celcliente(i),
               NULL,
               NULL,
               1,
               0,
               SYSDATE);
          END IF;
        
          IF v_teltrabcliente(i) IS NOT NULL THEN
          
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               7,
               'L',
               v_teltrabcliente(i),
               NULL,
               NULL,
               1,
               0,
               SYSDATE);
          END IF;
        
          IF v_valormail(i) IS NOT NULL THEN
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               3,
               'L',
               v_valormail(i),
               NULL,
               NULL,
               1,
               0,
               SYSDATE);
          END IF;
        
          IF v_val_tele_entr(i) IS NOT NULL THEN
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               (SELECT oid_tipo_comu
                  FROM mae_tipo_comun
                 WHERE cod_tipo_comu =
                       sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'COD_TIPO_TELE_ENTR')),
               'L',
               v_val_tele_entr(i),
               NULL,
               NULL,
               1,
               0,
               SYSDATE);
          END IF;
        
          IF v_val_celu_entr(i) IS NOT NULL THEN
            INSERT INTO mae_clien_comun
              (oid_clie_comu,
               clie_oid_clie,
               ticm_oid_tipo_comu,
               val_dia_comu,
               val_text_comu,
               fec_hora_desd,
               fec_hora_hast,
               val_inte_comu,
               ind_comu_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clco_seq.nextval,
               lstmp_oid_clie,
               (SELECT oid_tipo_comu
                  FROM mae_tipo_comun
                 WHERE cod_tipo_comu =
                       sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'COD_TIPO_CELU_ENTR')),
               'L',
               v_val_celu_entr(i),
               NULL,
               NULL,
               1,
               0,
               SYSDATE);
          END IF;
        
          --Insercion en MAE_CLIEN_HISTO_ESTAT
        
          INSERT INTO mae_clien_histo_estat
            (oid_hist_esta,
             perd_oid_peri,
             clie_oid_clie,
             perd_oid_peri_peri_fin,
             esta_oid_esta_clie)
          VALUES
            (mae_clhe_seq.nextval,
             v_oidperiodo(i),
             lstmp_oid_clie,
             NULL,
             1);
        
          --Insercion en MAE_CLIEN_TIPO_SUBTI
        
          INSERT INTO mae_clien_tipo_subti
            (oid_clie_tipo_subt,
             clie_oid_clie,
             ticl_oid_tipo_clie,
             sbti_oid_subt_clie,
             ind_ppal,
             fec_ulti_actu)
          VALUES
            (lstmp_oid_clie_tipo_subti,
             lstmp_oid_clie,
             2,
             1,
             1,
             SYSDATE);
        
          -- Insercion en MAE_CLIEN_CLASI
          BEGIN
            SELECT DISTINCT zz.cod_zona,
                            zr.cod_regi
              INTO lscod_zona,
                   lscod_regi
              FROM zon_terri_admin zta,
                   zon_secci       zs,
                   zon_zona        zz,
                   zon_regio       zr
             WHERE zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND zs.ind_acti = 1
               AND zz.ind_acti = 1
               AND zr.ind_acti = 1
               AND zta.ind_borr = 0
               AND zta.terr_oid_terr = v_oidterritorio(i);
          
            INSERT INTO mae_clien_clasi
              (oid_clie_clas,
               ctsu_oid_clie_tipo_subt,
               clas_oid_clas,
               perd_oid_peri,
               tccl_oid_tipo_clasi,
               fec_clas,
               ind_ppal,
               fec_ulti_actu)
            
              SELECT mae_clcl_seq.nextval,
                     a.*
                FROM (SELECT lstmp_oid_clie_tipo_subti,
                             (SELECT b.oid_clas
                                FROM mae_clasi b
                               WHERE b.tccl_oid_tipo_clas IN
                                     (SELECT a.oid_tipo_clas
                                        FROM mae_tipo_clasi_clien a
                                       WHERE a.sbti_oid_subt_clie = 1
                                         AND a.cod_tipo_clas = cod_tipo_clasi)
                                 AND b.cod_clas = cod_clasi) AS oid_clas,
                             v_oidperiodo(i),
                             (SELECT a.oid_tipo_clas
                                FROM mae_tipo_clasi_clien a
                               WHERE a.sbti_oid_subt_clie = 1
                                 AND a.cod_tipo_clas = cod_tipo_clasi) AS oid_tipo_clasi,
                             trunc(SYSDATE),
                             1,
                             SYSDATE
                        FROM sto_param_clasi_occrr
                       WHERE ((cod_regio IS NULL AND cod_zona IS NULL) OR
                             (cod_regio = lscod_regi AND
                             (cod_zona = lscod_zona OR cod_zona IS NULL)))
                         AND (cod_peri =
                             (SELECT b.cod_peri
                                 FROM cra_perio       a,
                                      seg_perio_corpo b
                                WHERE a.peri_oid_peri = b.oid_peri
                                  AND a.oid_peri = v_oidperiodo(i)
                                  AND a.pais_oid_pais = v_oidpais(i)) OR
                             cod_peri IS NULL)
                       GROUP BY cod_tipo_clasi,
                                cod_clasi) a;
          
          END;
        
          --Insercion en MAE_CLIEN_VINCU
          isvalid := 0;
          IF (v_codclientereco(i) IS NOT NULL) THEN
          
            isvalid := 1;
            INSERT INTO mae_clien_vincu
              (oid_clie_vinc,
               fec_desd,
               fec_hast,
               clie_oid_clie_vnte,
               clie_oid_clie_vndo,
               tivc_oid_tipo_vinc,
               ind_vinc_ppal,
               fec_ulti_actu)
            VALUES
              (mae_cvin_seq.nextval,
               nvl(trunc(v_fechaproceso(i)), trunc(SYSDATE)),
               nvl(trunc(v_fechaproceso(i)), trunc(SYSDATE)) + 365,
               (SELECT oid_clie
                  FROM mae_clien
                 WHERE cod_clie = v_codclientereco(i)),
               lstmp_oid_clie,
               9,
               1,
               SYSDATE);
          
          END IF;
        
          --Insercion en MAE_CLIEN_VINCU PARA LA LIDER QUE RECOMIENDA,
          IF v_cod_lide_reco(i) IS NOT NULL AND
             lsparametrotipovinculider IS NOT NULL THEN
          
            INSERT INTO mae_clien_vincu
              (oid_clie_vinc,
               fec_desd,
               fec_hast,
               clie_oid_clie_vnte,
               clie_oid_clie_vndo,
               tivc_oid_tipo_vinc,
               ind_vinc_ppal,
               fec_ulti_actu)
            VALUES
              (mae_cvin_seq.nextval,
               nvl(trunc(v_fechaproceso(i)), trunc(SYSDATE)),
               nvl(trunc(v_fechaproceso(i)), trunc(SYSDATE)) + 365,
               (SELECT oid_clie
                  FROM mae_clien
                 WHERE cod_clie = v_cod_lide_reco(i)),
               lstmp_oid_clie,
               lsparametrotipovinculider,
               decode(isvalid, 1, 0, 1),
               SYSDATE);
          
          END IF;
        
          -- iNSERCION INC_CLIEN_RECTE
        
          IF (v_cod_prem(i) IS NOT NULL AND v_codclientereco(i) IS NOT NULL) THEN
            --IF (v_codclientereco(i) IS NOT NULL) THEN
            BEGIN
              SELECT a.oid_clie_rete
                INTO lstm_poid_clie_rete
                FROM inc_clien_recte a
               WHERE clie_oid_clie =
                     (SELECT oid_clie
                        FROM mae_clien
                       WHERE cod_clie = v_codclientereco(i))
                 AND copa_oid_para_gral = v_oid_para_gral(i);
            
            EXCEPTION
              WHEN no_data_found THEN
                SELECT inc_clr3_seq.nextval
                  INTO lstm_poid_clie_rete
                  FROM dual;
              
                INSERT INTO inc_clien_recte
                  (oid_clie_rete,
                   ind_fin_vinc,
                   clie_oid_clie,
                   copa_oid_para_gral,
                   ind_eval,
                   oid_modu,
                   fec_crea)
                VALUES
                  (lstm_poid_clie_rete,
                   NULL,
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclientereco(i)),
                   v_oid_para_gral(i),
                   NULL,
                   1,
                   SYSDATE);
              
            END;
          
            IF (v_codclientereco(i) IS NOT NULL AND
               lstm_poid_clie_rete IS NOT NULL) THEN
            
              INSERT INTO inc_clien_recdo
                (oid_clie_redo,
                 ind_efec,
                 num_prem,
                 clie_oid_clie,
                 clr3_oid_clie_rete,
                 perd_oid_peri,
                 panp_oid_para_nive_prem,
                 ind_eval,
                 oid_modu,
                 fec_crea)
              VALUES
                (inc_clre_seq.nextval,
                 NULL,
                 v_numeropremio(i),
                 lstmp_oid_clie,
                 lstm_poid_clie_rete,
                 v_oidperiodo(i),
                 v_oidnivelpremio(i),
                 NULL,
                 1,
                 SYSDATE);
            END IF;
          
          END IF;
        
          IF (v_cod_prem(i) IS NULL AND v_codclientereco(i) IS NOT NULL) THEN
            SELECT oid_clie
              INTO lnoidclientercdte
              FROM mae_clien
             WHERE cod_clie = v_codclientereco(i);
          
            inc_pkg_proce_incen.inc_pr_inser_regis_recom(v_oidpais(i),
                                                         lstmp_oid_clie,
                                                         v_oidperiodo(i),
                                                         lnoidclientercdte,
                                                         1,
                                                         'C');
          END IF;
        
          --Insercion en MAE_CLIEN_IDENT
        
          IF (v_numerodocumento(i) IS NOT NULL AND
             v_tipodocumento(i) IS NOT NULL) THEN
          
            INSERT INTO mae_clien_ident
              (oid_clie_iden,
               tdoc_oid_tipo_docu,
               clie_oid_clie,
               num_docu_iden,
               val_iden_docu_prin,
               val_iden_pers_empr,
               fec_ulti_actu)
            VALUES
              (mae_clid_seq.nextval,
               (SELECT oid_tipo_docu
                  FROM mae_tipo_docum
                 WHERE cod_tipo_docu = v_tipodocumento(i)),
               lstmp_oid_clie,
               v_numerodocumento(i),
               decode(v_ruccliente(i), NULL, 1, 0),
               'P',
               SYSDATE);
          END IF;
        
          IF (v_ruccliente(i) IS NOT NULL) THEN
          
            contador_mae_clien_direc := 0;
          
            SELECT COUNT(1)
              INTO contador_mae_clien_direc
              FROM mae_clien_ident
             WHERE clie_oid_clie = lstmp_oid_clie
               AND tdoc_oid_tipo_docu =
                   (SELECT oid_tipo_docu
                      FROM mae_tipo_docum
                     WHERE val_sigl = 'RUC');
          
            IF contador_mae_clien_direc = 0 THEN
            
              INSERT INTO mae_clien_ident
                (oid_clie_iden,
                 tdoc_oid_tipo_docu,
                 clie_oid_clie,
                 num_docu_iden,
                 val_iden_docu_prin,
                 val_iden_pers_empr,
                 fec_ulti_actu)
              VALUES
                (mae_clid_seq.nextval,
                 (SELECT oid_tipo_docu
                    FROM mae_tipo_docum
                   WHERE val_sigl = 'RUC'),
                 lstmp_oid_clie,
                 v_ruccliente(i),
                 1,
                 'P',
                 SYSDATE);
            
            ELSE
            
              UPDATE mae_clien_ident
                 SET num_docu_iden      = v_ruccliente(i),
                     fec_ulti_actu      = SYSDATE,
                     val_iden_docu_prin = 1
               WHERE tdoc_oid_tipo_docu =
                     (SELECT oid_tipo_docu
                        FROM mae_tipo_docum
                       WHERE val_sigl = 'RUC')
                 AND clie_oid_clie = lstmp_oid_clie;
            
            END IF;
          
          END IF;
        
          lscodigomarca := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_MARC');
          lnidmarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(lscodigomarca);
        
          INSERT INTO mae_clien_marca
            (oid_clie_marc,
             clie_oid_clie,
             marc_oid_marc,
             ind_ppal,
             fec_ulti_actu)
          VALUES
            (mae_clmr_seq.nextval,
             lstmp_oid_clie,
             lnidmarca,
             1,
             SYSDATE);
        
          INSERT INTO mae_clien_prime_conta
            (oid_clie_prim_cont,
             clie_oid_clie,
             ctsu_clie_cont,
             fec_cont,
             fec_sigu_cont,
             cod_tipo_cont,
             marc_oid_marc,
             cana_oid_cana,
             perd_oid_peri,
             fec_ulti_actu)
          VALUES
            (mae_cprc_seq.nextval,
             lstmp_oid_clie,
             /*nvl((SELECT MAX(oid_clie_tipo_subt)
                   FROM mae_clien_tipo_subti
                  WHERE clie_oid_clie =
                        (SELECT oid_clie FROM mae_clien WHERE cod_clie = 'MIGRACION')),
             1),*/
             lstmp_oid_clie_tipo_subti,
             v_fechaproceso(i),
             v_fechaproceso(i) + 365,
             'I',
             2003,
             2001,
             v_oidperiodo(i),
             SYSDATE);
        
          --Informacion del Fiador
        
          IF (v_cod_docu_idfi(i) IS NOT NULL) THEN
          
            INSERT INTO mae_refer
              (cod_clie,
               tipo_refe,
               tipo_docu_refe,
               num_docu_refe,
               val_ape1,
               val_ape2,
               val_nom1,
               val_nom2,
               val_dire,
               val_barr,
               val_ciud,
               val_depa,
               val_telf,
               val_celu,
               val_telf_trab,
               tipo_via,
               val_nom_via,
               num_dire,
               cod_depa,
               cod_prov,
               cod_dist,
               cod_sect,
               tipo_vincu,
               nom_empre,
               dir_empre,
               carg_empre)
            VALUES
              (v_codclie(i),
               3,
               nvl(v_tip_docu_fiad(i), '01'), -- le colocara 01 por default en caso venga en null
               v_cod_docu_idfi(i),
               v_primerapellfiador(i),
               v_secondapellfiador(i),
               v_primernomfiador(i),
               v_secondnomfiador(i),
               v_val_dire_fiad(i),
               v_val_barr_fiad(i),
               v_val_ciud_fiad(i),
               v_val_depa_fiad(i),
               v_valtelefiador(i),
               v_valcelufiador(i),
               v_valtrabfiador(i),
               v_tip_via_fiad(i),
               v_val_nomb_vifi(i),
               v_num_dire_fiad(i),
               v_cod_depa_fiad(i),
               v_cod_prov_fiad(i),
               v_cod_dist_fiad(i),
               v_cod_sect_fiad(i),
               v_val_tipo_vinc_fiad(i),
               v_val_nomb_empr_fiad(i),
               v_val_dire_empr_fiad(i),
               v_val_carg_fiad(i));
          END IF;
        
          --Informacion del Referencia Familiar
          IF v_val_nomb_refe_fami(i) IS NOT NULL THEN
            INSERT INTO mae_refer
              (cod_clie,
               tipo_refe,
               tipo_docu_refe,
               num_docu_refe,
               val_ape1,
               val_ape2,
               val_nom1,
               val_nom2,
               val_dire,
               val_barr,
               val_ciud,
               val_depa,
               val_telf,
               val_celu,
               val_telf_trab,
               tipo_via,
               val_nom_via,
               num_dire,
               cod_depa,
               cod_prov,
               cod_dist,
               cod_sect,
               tipo_vincu,
               nom_empre,
               dir_empre,
               carg_empre)
            VALUES
              (v_codclie(i),
               1,
               NULL,
               NULL,
               v_val_apel_refe_fami(i),
               NULL,
               v_val_nomb_refe_fami(i),
               NULL,
               v_val_dire_refe_fami(i),
               v_val_barr_refe_fami(i),
               v_val_ciud_refe_fami(i),
               v_val_depa_refe_fami(i),
               v_val_tele_refe_fami(i),
               v_val_celu_refe_fami(i),
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               v_val_tipo_vinc_refe_fami(i),
               NULL,
               NULL,
               NULL);
          END IF;
        
          IF v_val_nomb_refe_nofa(i) IS NOT NULL THEN
            INSERT INTO mae_refer
              (cod_clie,
               tipo_refe,
               tipo_docu_refe,
               num_docu_refe,
               val_ape1,
               val_ape2,
               val_nom1,
               val_nom2,
               val_dire,
               val_barr,
               val_ciud,
               val_depa,
               val_telf,
               val_celu,
               val_telf_trab,
               tipo_via,
               val_nom_via,
               num_dire,
               cod_depa,
               cod_prov,
               cod_dist,
               cod_sect,
               tipo_vincu,
               nom_empre,
               dir_empre,
               carg_empre)
            VALUES
              (v_codclie(i),
               2,
               NULL,
               NULL,
               v_val_ape1_refe_nofa(i),
               NULL,
               v_val_nomb_refe_nofa(i),
               NULL,
               v_val_dire_refe_nofa(i),
               NULL,
               NULL,
               NULL,
               v_val_tele_refe_nofa(i),
               v_val_celu_refe_nofa(i),
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               v_val_tipo_vinc_refe_nofa(i),
               NULL,
               NULL,
               NULL);
          END IF;
        
          --Informacion de marcas que vende la consultora
          IF (v_val_ind_vend_mar1(i) IS NOT NULL OR
             v_val_ind_vend_mar2(i) IS NOT NULL OR
             v_val_ind_vend_mar3(i) IS NOT NULL OR
             v_val_ind_vend_mar4(i) IS NOT NULL OR
             v_val_ind_vend_mar5(i) IS NOT NULL OR
             v_val_ind_vend_mar6(i) IS NOT NULL OR
             v_val_ind_vend_mar7(i) IS NOT NULL OR
             v_val_cod_marc_vema(i) IS NOT NULL) THEN
          
            SELECT COUNT(1)
              INTO lnvendemarca
              FROM mae_clien_vende_marca
             WHERE clie_oid_clie = lstmp_oid_clie;
          
            IF lnvendemarca = 0 THEN
            
              INSERT INTO mae_clien_vende_marca
                (clie_oid_clie,
                 ind_mrc1,
                 ind_mrc2,
                 ind_mrc3,
                 ind_mrc4,
                 ind_mrc5,
                 ind_mrc6,
                 ind_mrc7,
                 ind_marc_masv)
              VALUES
                (lstmp_oid_clie,
                 decode(v_val_ind_vend_mar1(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar2(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar3(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar4(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar5(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar6(i), 'S', 1, 0),
                 decode(v_val_ind_vend_mar7(i), 'S', 1, 0),
                 nvl(v_val_cod_marc_vema(i), 0));
            END IF;
          END IF;
          --
          UPDATE int_solic_conso_credi
             SET cod_clie = v_codclie(i)
           WHERE sec_nume_docu = v_secnumdocu(i);
        
        END LOOP;
      
        FORALL j IN 1 .. v_codpais.count
          UPDATE int_solic_conso_credi
             SET cod_clie = v_codclie(j)
           WHERE sec_nume_docu = v_secnumdocu(j)
             AND num_lote = v_numlote(j);
        -- Actualizamos Documentos Enviados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Envio
                 occ.ind_envi = '1',
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE,
                 occ.cod_clie = v_codclie(j)
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoccabecera
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      
      END IF;
    
      EXIT WHEN c_envioscc %NOTFOUND;
    END LOOP;
  
    CLOSE c_envioscc;
  
    --Carga a la tabla nvs con su logro
    sto_pkg_proce_valid_scc.sto_pr_sim_envio_sicc(pscodigopais,
                                                  pscodigotipodoccabecera,
                                                  psusuario,
                                                  psnumeroproceso);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SCC_ENVIO_SICC: ' || ls_sqlerrm);
    
  END sto_pr_scc_envio_sicc;

  /**************************************************************************
  Descripcion       : Grabar Solicitudes PostVenta en REC
  
  Fecha Creacion    : 05/06/2008
  Parametros Entrada:
                      psCodigoPais : Codigo de pais
                      psCodigoTipoDoc : Codigo de tipo doc
                      psCodigoUltimaValid : Codigo de Ultima Validacion
                      psUsuario : Codigo de Usuario
  
  Autor             : Leonardo  Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
  
    CURSOR c_tiposolicitud(vscodigodocumentodetalle VARCHAR2) IS
      SELECT cab.cod_pais                      cab_cod_pais, --1 CAB
             cab.num_docu                      cab_num_docu, --3 CAB
             cab.cod_peri                      cab_cod_peri, --4 CAB
             cab.cod_clie                      cab_cod_clie, --5 CAB
             cab.oid_peri                      cab_oid_peri, --16 CAB
             cab.oid_clie                      cab_oid_clie, --17 CAB
             cab.oid_cabe                      cab_oid_cabe, --18 CAB
             cab.sbti_oid_subt_clie            cab_sbti_oid_subt_clie, --19 CAB
             cab.ticl_oid_tipo_clie            cab_ticl_oid_tipo_clie, --20 CAB
             cab.oid_peri_refe                 cab_id_peri_refe, --21 CAB
             cab.fec_refe                      cab_fec_refe, --22 CAB
             cab.tido_oid_tipo_docu            cab_tido_oid_tipo_docu, --23 CAB
             cab.ztad_oid_terr_admi            cab_ztad_oid_terr_admi, --24 CAB
             cab.sec_nume_docu                 cab_sec_nume_docu,
             cab.num_lote                      cab_num_lote,
             det.cod_vent_devu                 det_cod_vent_devu, --6 DET
             det.cod_vent_dese                 det_cod_vent_dese, --7  DET
             det.can_vent_devu                 det_can_vent_devu, -- 8 DET
             det.mot_spv                       det_mot_spv, --12 DET
             det.ind_envi_gener_devu           det_ind_envi_gener_devu, --21 DET
             det.ind_devu_gener_envi           det_ind_devu_gener_envi, --22 DET
             det.val_prec_cata_envi            det_val_prec_cata_envi, --27 DET
             det.val_prec_cont_envi            det_val_prec_cont_envi, --28 DET
             det.val_prec_cont_devu            det_val_prec_cont_devu, --30 DET
             det.panp_oid_para_nive_prem_envi  det_panp_oid_para_nive_prenv, --31 DET
             det.lopa_oid_lote_prem_artic_envi det_lopa_oid_lote_prem_arenv, --32 DET
             det.copa_oid_para_gene_envi       det_copa_oid_para_gene_envi, --33 DET
             det.panp_oid_para_nive_prem_devu  det_panp_oid_para_nive_prede, --34 DET
             det.lopa_oid_lote_prem_artic_devu det_lopa_oid_lote_prem_ardev, --35 DET
             det.copa_oid_para_gene_devu       det_copa_oid_para_gene_devu, --36 DET
             det.tofe_oid_envi                 det_tofe_oid_envi, --37 DET
             det.mafa_oid_envi                 det_mafa_oid_envi, --38 DET
             det.prod_oid_prod_envi            det_prod_oid_prod_envi, --39 DET
             det.mafa_oid_devu                 det_mafa_oid_devu, --41 DET
             det.prod_oid_prod_devu            det_prod_oid_prod_devu, --42 DET
             det.tspa_oid_tipo_soli_pais_envu  det_tspa_oid_tipo_soli_paenv, --43 DET
             det.tspa_oid_tipo_soli_pais_devu  det_tspa_oid_tipo_soli_padev, --44 DET
             det.oid_soli_posi_envi            det_oid_soli_posi_envi, --45 DET
             det.oid_soli_posi_devu            det_oid_soli_posi_devu, --46 DET
             det.sec_nume_docu                 det_sec_nume_docu,
             det.num_lote                      det_num_lote,
             det.docu_cod_tipo_docu            det_docu_cod_tipo_docu,
             det.cod_pais                      det_cod_pais,
             det.oid_tipo_oper                 det_oid_tipo_oper,
             det.oid_pais                      det_oid_pais,
             det.can_prod_dese                 det_can_prod_dese,
             det.tofe_oid_devu                 det_tofe_oid_devu,
             det.val_prec_cata_devu            det_val_prec_cata_devu,
             det.oid_oper                      det_oid_oper,
             det.cod_moti_rech                 det_cod_moti_rech,
             cab.cod_moti_rech                 cab_cod_moti_rech,
             det.ind_devu_fisi                 det_ind_devu_fisi,
             det.ind_envi_fact                 det_ind_envi_fact,
             det.tip_refe                      det_tip_refe,
             det.val_fact_repe                 det_val_fact_repe,
             det.cod_moti_real                 cod_moti_real
        FROM int_solic_conso_poven_cabec cab,
             int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         cocc,
             sto_tmp_docum_digit         docc
       WHERE cocc.sec_nume_docu = cab.sec_nume_docu
         AND cocc.num_lote = cab.num_lote
         AND cocc.cod_tipo_docu = pscodigotipodoccabecera
         AND cocc.cod_pais = pscodigopais
         AND docc.sec_nume_docu = det.sec_nume_docu
         AND docc.num_lote = det.num_lote
         AND docc.cod_tipo_docu = vscodigodocumentodetalle
         AND docc.cod_pais = pscodigopais
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_docu = det.num_docu
         AND cab.num_lote = det.num_lote
      --and cab.sec_nume_docu = 16123213
       ORDER BY cab.num_lote,
                cab.cod_pais,
                cab.cod_peri,
                cab.cod_clie,
                cab.num_docu,
                det.oid_tipo_oper;
  
    TYPE t_cab_cod_pais IS TABLE OF int_solic_conso_poven_cabec.cod_pais %TYPE;
    TYPE t_cab_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu %TYPE;
    TYPE t_cab_cod_peri IS TABLE OF int_solic_conso_poven_cabec.cod_peri %TYPE;
    TYPE t_cab_cod_clie IS TABLE OF int_solic_conso_poven_cabec.cod_clie %TYPE;
    TYPE t_cab_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri %TYPE;
    TYPE t_cab_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie %TYPE;
    TYPE t_cab_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe %TYPE;
    TYPE t_cab_sbti_oid_subt_clie IS TABLE OF int_solic_conso_poven_cabec.sbti_oid_subt_clie %TYPE;
    TYPE t_cab_ticl_oid_tipo_clie IS TABLE OF int_solic_conso_poven_cabec.ticl_oid_tipo_clie %TYPE;
    TYPE t_cab_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe %TYPE;
    TYPE t_cab_fec_refe IS TABLE OF int_solic_conso_poven_cabec.fec_refe %TYPE;
    TYPE t_cab_tido_oid_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.tido_oid_tipo_docu %TYPE;
    TYPE t_cab_ztad_oid_terr_admi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi %TYPE;
    TYPE t_cab_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu %TYPE;
    TYPE t_cab_num_lote IS TABLE OF int_solic_conso_poven_cabec.num_lote%TYPE;
  
    TYPE t_det_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu %TYPE;
    TYPE t_det_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese %TYPE;
    TYPE t_det_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu %TYPE;
    TYPE t_det_mot_spv IS TABLE OF int_solic_conso_poven_detal.mot_spv %TYPE;
    TYPE t_det_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu %TYPE;
    TYPE t_det_ind_devu_gener_envi IS TABLE OF int_solic_conso_poven_detal.ind_devu_gener_envi %TYPE;
    TYPE t_det_val_prec_cata_envi IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_envi %TYPE;
    TYPE t_det_val_prec_cont_envi IS TABLE OF int_solic_conso_poven_detal.val_prec_cont_envi %TYPE;
    TYPE t_det_val_prec_cont_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cont_devu %TYPE;
    TYPE t_det_copa_oid_para_gene_envi IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_envi %TYPE;
    TYPE t_det_panp_oid_para_nive_prenv IS TABLE OF int_solic_conso_poven_detal.panp_oid_para_nive_prem_envi %TYPE;
    TYPE t_det_lopa_oid_lote_prem_arenv IS TABLE OF int_solic_conso_poven_detal.lopa_oid_lote_prem_artic_envi %TYPE;
    TYPE t_det_copa_oid_para_gene_devu IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_devu %TYPE;
    TYPE t_det_panp_oid_para_nive_prdev IS TABLE OF int_solic_conso_poven_detal.panp_oid_para_nive_prem_devu %TYPE;
    TYPE t_det_lopa_oid_lote_prem_ardev IS TABLE OF int_solic_conso_poven_detal.lopa_oid_lote_prem_artic_envi %TYPE;
    TYPE t_det_tofe_oid_envi IS TABLE OF int_solic_conso_poven_detal.tofe_oid_envi %TYPE;
    TYPE t_det_mafa_oid_envi IS TABLE OF int_solic_conso_poven_detal.mafa_oid_envi %TYPE;
    TYPE t_det_prod_oid_prod_envia IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_envi %TYPE;
    TYPE t_det_mafa_oid_devu IS TABLE OF int_solic_conso_poven_detal.mafa_oid_devu %TYPE;
    TYPE t_det_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu %TYPE;
    TYPE t_det_tspa_oid_tipo_soli_paenv IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_envu %TYPE;
    TYPE t_det_tspa_oid_tipo_soli_padev IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_envu %TYPE;
    TYPE t_det_oid_soli_posi_envi IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_envi %TYPE;
    TYPE t_det_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu %TYPE;
    TYPE t_det_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu %TYPE;
    TYPE t_det_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote %TYPE;
    TYPE t_det_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu %TYPE;
    TYPE t_det_cod_pais IS TABLE OF int_solic_conso_poven_detal.cod_pais %TYPE;
    TYPE t_det_oid_tipo_oper IS TABLE OF int_solic_conso_poven_detal.oid_tipo_oper %TYPE;
    TYPE t_det_oid_pais IS TABLE OF int_solic_conso_poven_detal.oid_pais %TYPE;
    TYPE t_det_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese %TYPE;
    TYPE t_det_tofe_oid_devu IS TABLE OF int_solic_conso_poven_detal.tofe_oid_devu %TYPE;
    TYPE t_det_val_prec_cata_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_devu %TYPE;
    TYPE t_det_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper %TYPE;
    TYPE t_det_cod_moti_rech IS TABLE OF int_solic_conso_poven_detal.cod_moti_rech %TYPE;
    TYPE t_cab_cod_moti_rech IS TABLE OF int_solic_conso_poven_cabec.cod_moti_rech %TYPE;
    TYPE t_det_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi %TYPE;
    TYPE t_det_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact %TYPE;
    TYPE t_det_tip_refe IS TABLE OF int_solic_conso_poven_detal.tip_refe %TYPE;
    TYPE t_det_val_fact_repe IS TABLE OF int_solic_conso_poven_detal.val_fact_repe %TYPE;
    TYPE t_cod_moti_real IS TABLE OF int_solic_conso_poven_detal.cod_moti_real%TYPE;
  
    v_cab_cod_pais           t_cab_cod_pais;
    v_cab_num_docu           t_cab_num_docu;
    v_cab_cod_peri           t_cab_cod_peri;
    v_cab_cod_clie           t_cab_cod_clie;
    v_cab_oid_peri           t_cab_oid_peri;
    v_cab_oid_clie           t_cab_oid_clie;
    v_cab_oid_cabe           t_cab_oid_cabe;
    v_cab_sbti_oid_subt_clie t_cab_sbti_oid_subt_clie;
    v_cab_ticl_oid_tipo_clie t_cab_ticl_oid_tipo_clie;
    v_cab_oid_peri_refe      t_cab_oid_peri_refe;
    v_cab_fec_refe           t_cab_fec_refe;
    v_cab_tido_oid_tipo_docu t_cab_tido_oid_tipo_docu;
    v_cab_ztad_oid_terr_admi t_cab_ztad_oid_terr_admi;
    v_cab_sec_nume_docu      t_cab_sec_nume_docu;
    v_cab_num_lote           t_cab_num_lote;
  
    v_det_cod_vent_devu            t_det_cod_vent_devu;
    v_det_cod_vent_dese            t_det_cod_vent_dese;
    v_det_can_vent_devu            t_det_can_vent_devu;
    v_det_mot_spv                  t_det_mot_spv;
    v_det_ind_envi_gener_devu      t_det_ind_envi_gener_devu;
    v_det_ind_devu_gener_envi      t_det_ind_devu_gener_envi;
    v_det_val_prec_cata_envi       t_det_val_prec_cata_envi;
    v_det_val_prec_cont_envi       t_det_val_prec_cont_envi;
    v_det_val_prec_cont_devu       t_det_val_prec_cont_devu;
    v_det_copa_oid_para_gene_envi  t_det_copa_oid_para_gene_envi;
    v_det_panp_oid_para_nive_prenv t_det_panp_oid_para_nive_prenv;
    v_det_lopa_oid_lote_prem_arenv t_det_lopa_oid_lote_prem_arenv;
    v_det_copa_oid_para_gene_devu  t_det_copa_oid_para_gene_devu;
    v_det_panp_oid_para_nive_prdev t_det_panp_oid_para_nive_prdev;
    v_det_lopa_oid_lote_prem_ardev t_det_lopa_oid_lote_prem_ardev;
    v_det_tofe_oid_envi            t_det_tofe_oid_envi;
    v_det_mafa_oid_envi            t_det_mafa_oid_envi;
    v_det_prod_oid_prod_envia      t_det_prod_oid_prod_envia;
    v_det_mafa_oid_devu            t_det_mafa_oid_devu;
    v_det_prod_oid_prod_devu       t_det_prod_oid_prod_devu;
    v_det_tspa_oid_tipo_soli_paenv t_det_tspa_oid_tipo_soli_paenv;
    v_det_tspa_oid_tipo_soli_padev t_det_tspa_oid_tipo_soli_padev;
    v_det_oid_soli_posi_envi       t_det_oid_soli_posi_envi;
    v_det_oid_soli_posi_devu       t_det_oid_soli_posi_devu;
    v_det_sec_nume_docu            t_det_sec_nume_docu;
    v_det_num_lote                 t_det_num_lote;
    v_det_docu_cod_tipo_docu       t_det_docu_cod_tipo_docu;
    v_det_cod_pais                 t_det_cod_pais;
    v_det_oid_tipo_oper            t_det_oid_tipo_oper;
    v_det_oid_pais                 t_det_oid_pais;
    v_det_can_prod_dese            t_det_can_prod_dese;
    v_det_tofe_oid_devu            t_det_tofe_oid_devu;
    v_det_val_prec_cata_devu       t_det_val_prec_cata_devu;
    v_det_oid_oper                 t_det_oid_oper;
    v_det_cod_moti_rech            t_det_cod_moti_rech;
    v_cab_cod_moti_rech            t_cab_cod_moti_rech;
    v_det_ind_devu_fisi            t_det_ind_devu_fisi;
    v_det_ind_envi_fact            t_det_ind_envi_fact;
    v_det_tip_refe                 t_det_tip_refe;
    v_det_val_fact_repe            t_det_val_fact_repe;
    v_cod_moti_real                t_cod_moti_real;
  
    ln_oid_moti_bloq NUMBER;
  
    lnidperiodoregi NUMBER;
  
    lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;
    lscodigodocumentodetalle VARCHAR2(10);
  
    lscodigodocumentopkant      VARCHAR2(200) := NULL;
    lscodigodocumentopki        VARCHAR2(200);
    lscodigooperareclapk        VARCHAR2(200) := NULL;
    lscodigooperareclapkaterior VARCHAR2(200) := NULL;
  
    ln_tmp_oid_cabe NUMBER;
    ln_tmp_oid_oper NUMBER;
    ln_sec          NUMBER;
    linea           NUMBER := 0;
    lnindonline     VARCHAR2(15) := NULL;
    lnindonlin2     VARCHAR2(15) := NULL;
  
    lv_abono_total_reclamo NUMBER := 0;
    lv_cargo_total_reclamo NUMBER := 0;
  
    lnnumcabeceras     NUMBER;
    lnnumreclamos      NUMBER;
    lnnumreclamosrec   NUMBER;
    lnnumsoliinicioped NUMBER;
  
    lsusuario sto_docum_digit.usu_digi%TYPE;
  
    lsparametrobolec sto_param_gener_occrr.val_param%TYPE;
  
    varoidoperareclabol NUMBER;
  
  BEGIN
  
    lsusuario := 'S_' || psusuario;
  
    lnindonline := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        p_ind_line);
  
    lnindonlin2 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'IND_COMP_RECL');
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoccabecera);
  
    lsparametrobolec := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_BOLELEC');
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    ---- Periodo actual
    SELECT c.oid_peri
      INTO lnidperiodoregi
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    /*INICIALIZACION DE VALORES PARA EL PROCESO*/
    SELECT COUNT(*)
      INTO lnnumcabeceras
      FROM sto_tmp_docum_digit occ
     WHERE occ.cod_tipo_docu = pscodigotipodoccabecera
       AND occ.cod_pais = pscodigopais;
  
    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'REC034',
                                                              '_',
                                                              lnnumcabeceras);
  
    SELECT to_char(SYSDATE, 'YY') || lpad(lnnumsoliinicio, 8, '0')
      INTO lnnumsoliformat
      FROM dual;
  
    SELECT COUNT(1)
      INTO lnnumreclamos
      FROM sto_tmp_docum_digit occ
     WHERE occ.cod_pais = pscodigopais;
  
    SELECT COUNT(1)
      INTO lnnumreclamosrec
      FROM sto_tmp_docum_digit         occ,
           int_solic_conso_poven_detal isd
     WHERE isd.sec_nume_docu = occ.sec_nume_docu
       AND occ.cod_pais = pscodigopais
       AND isd.ind_devu_gener_envi = 1;
  
    lnnumsoliinicioped := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                                 'PED001',
                                                                 '000',
                                                                 lnnumreclamos +
                                                                 lnnumreclamosrec);
  
    OPEN c_tiposolicitud(lscodigodocumentodetalle);
    LOOP
      FETCH c_tiposolicitud BULK COLLECT
        INTO v_cab_cod_pais,
             v_cab_num_docu,
             v_cab_cod_peri,
             v_cab_cod_clie,
             v_cab_oid_peri,
             v_cab_oid_clie,
             v_cab_oid_cabe,
             v_cab_sbti_oid_subt_clie,
             v_cab_ticl_oid_tipo_clie,
             v_cab_oid_peri_refe,
             v_cab_fec_refe,
             v_cab_tido_oid_tipo_docu,
             v_cab_ztad_oid_terr_admi,
             v_cab_sec_nume_docu,
             v_cab_num_lote,
             v_det_cod_vent_devu,
             v_det_cod_vent_dese,
             v_det_can_vent_devu,
             v_det_mot_spv,
             v_det_ind_envi_gener_devu,
             v_det_ind_devu_gener_envi,
             v_det_val_prec_cata_envi,
             v_det_val_prec_cont_envi,
             v_det_val_prec_cont_devu,
             v_det_panp_oid_para_nive_prenv,
             v_det_lopa_oid_lote_prem_arenv,
             v_det_copa_oid_para_gene_envi,
             v_det_panp_oid_para_nive_prdev,
             v_det_lopa_oid_lote_prem_ardev,
             v_det_copa_oid_para_gene_devu,
             v_det_tofe_oid_envi,
             v_det_mafa_oid_envi,
             v_det_prod_oid_prod_envia,
             v_det_mafa_oid_devu,
             v_det_prod_oid_prod_devu,
             v_det_tspa_oid_tipo_soli_paenv,
             v_det_tspa_oid_tipo_soli_padev,
             v_det_oid_soli_posi_envi,
             v_det_oid_soli_posi_devu,
             v_det_sec_nume_docu,
             v_det_num_lote,
             v_det_docu_cod_tipo_docu,
             v_det_cod_pais,
             v_det_oid_tipo_oper,
             v_det_oid_pais,
             v_det_can_prod_dese,
             v_det_tofe_oid_devu,
             v_det_val_prec_cata_devu,
             v_det_oid_oper,
             v_det_cod_moti_rech,
             v_cab_cod_moti_rech,
             v_det_ind_devu_fisi,
             v_det_ind_envi_fact,
             v_det_tip_refe,
             v_det_val_fact_repe,
             v_cod_moti_real LIMIT rows;
    
      IF v_cab_cod_pais.count > 0 THEN
      
        FOR i IN v_cab_cod_pais.first .. v_cab_cod_pais.last
        LOOP
        
          lscodigodocumentopki := v_cab_num_lote(i) || v_cab_cod_pais(i) ||
                                  v_cab_cod_peri(i) || v_cab_cod_clie(i) ||
                                  v_cab_num_docu(i);
        
          IF (lscodigodocumentopkant IS NULL OR
             lscodigodocumentopkant <> lscodigodocumentopki) THEN
            UPDATE rec_cabec_recla rcr
               SET rcr.num_tota_envi = lv_cargo_total_reclamo,
                   rcr.num_tota_devu = lv_abono_total_reclamo
             WHERE rcr.oid_cabe_recl = ln_tmp_oid_cabe;
            UPDATE rec_cabec_recla rcr
               SET rcr.imp_sald_paga = lv_cargo_total_reclamo -
                                       lv_abono_total_reclamo
             WHERE rcr.oid_cabe_recl = ln_tmp_oid_cabe;
          
            IF (lnindonline = '1') THEN
              lnnumsoliinicioped := sto_pr_graba_ped_rec_oid(ln_tmp_oid_cabe,
                                                             lsusuario,
                                                             lnnumsoliinicioped);
            END IF;
          
            SELECT rec_care_seq.nextval INTO ln_tmp_oid_cabe FROM dual;
          
            lnnumsoliinicio := lnnumsoliinicio + 1;
            lnnumsoliformat := lnnumsoliformat + 1;
          
            BEGIN
              SELECT m.oid_moti_bloq
                INTO ln_oid_moti_bloq
                FROM rec_motiv_bloqu m
               WHERE m.val_moti_bloq = v_cab_cod_moti_rech(i);
            EXCEPTION
              WHEN no_data_found THEN
                ln_oid_moti_bloq := NULL;
            END;
          
            ----- lnIdPeriodoREGI := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_cab_cod_peri(i));
          
            INSERT INTO rec_cabec_recla
              (oid_cabe_recl,
               num_aten,
               num_recl,
               fec_docu_refe,
               num_tota_envi,
               num_tota_devu,
               imp_sald_paga,
               cod_usua_ingr,
               fec_ingr,
               pais_oid_pais,
               soca_oid_soli_cabe,
               perd_oid_peri_docu_refe,
               clie_oid_clie,
               mrdb_oid_moti_rech,
               perd_oid_peri_recl,
               mrdb_oid_moti_desb,
               ztad_oid_terr_admi,
               sbti_oid_subt_clie,
               tido_oid_tipo_docu,
               esre_oid_esta_recl,
               mobl_oid_moti_bloq,
               tiin_oid_tipo_ingr,
               ticl_oid_tipo_clie,
               fec_ulti_actu,
               num_aten_inte,
               perd_oid_peri_regi)
            VALUES
              (ln_tmp_oid_cabe,
               lnnumsoliformat,
               v_cab_num_docu(i), --3 cab
               v_cab_fec_refe(i), --22 cab
               0,
               0,
               0,
               lsusuario, --usuario
               trunc(SYSDATE),
               v_det_oid_pais(i), --1 cab
               v_cab_oid_cabe(i), --18 cab
               v_cab_oid_peri_refe(i), --21 cab
               v_cab_oid_clie(i), --17 cab
               NULL,
               v_cab_oid_peri(i), --16 cab
               NULL,
               v_cab_ztad_oid_terr_admi(i), --24 cab
               v_cab_sbti_oid_subt_clie(i), --19 cab
               v_cab_tido_oid_tipo_docu(i), --23 cab
               2,
               ln_oid_moti_bloq,
               2,
               v_cab_ticl_oid_tipo_clie(i), --20 cab
               trunc(SYSDATE),
               NULL,
               lnidperiodoregi);
          
            UPDATE int_solic_conso_poven_cabec c
               SET c.fec_proc_docu      = trunc(SYSDATE),
                   c.oid_cabe_recl_refe = ln_tmp_oid_cabe
             WHERE cod_pais = v_cab_cod_pais(i)
               AND cod_peri = v_cab_cod_peri(i)
               AND cod_clie = v_cab_cod_clie(i)
               AND num_docu = v_cab_num_docu(i)
               AND num_lote = v_cab_num_lote(i);
          
            UPDATE sto_docum_digit occ
               SET occ.ind_envi = '1',
                   occ.usu_modi = lsusuario,
                   occ.fec_modi = SYSDATE
             WHERE occ.sec_nume_docu = v_cab_sec_nume_docu(i)
               AND occ.num_lote = v_cab_num_lote(i)
               AND occ.cod_tipo_docu = pscodigotipodoccabecera
               AND occ.cod_pais = pscodigopais;
          
            lscodigodocumentopkant := lscodigodocumentopki;
          
            lv_cargo_total_reclamo := 0;
            lv_abono_total_reclamo := 0;
            ln_sec                 := 0;
          
          END IF;
          /*SQA Se agrega en el quiebre el numero de lote*/
          lscodigooperareclapk := v_cab_num_lote(i) || v_cab_cod_pais(i) ||
                                  v_cab_cod_peri(i) || v_cab_cod_clie(i) ||
                                  v_cab_num_docu(i) ||
                                  v_det_oid_tipo_oper(i);
        
          IF (lscodigooperareclapkaterior IS NULL OR
             lscodigooperareclapk <> lscodigooperareclapkaterior) THEN
            ln_sec := ln_sec + 1;
            SELECT rec_opre_seq.nextval INTO ln_tmp_oid_oper FROM dual;
          
            BEGIN
              SELECT m.oid_moti_bloq
                INTO ln_oid_moti_bloq
                FROM rec_motiv_bloqu m
               WHERE m.val_moti_bloq = v_det_cod_moti_rech(i);
            EXCEPTION
              WHEN no_data_found THEN
                ln_oid_moti_bloq := NULL;
            END;
          
            INSERT INTO rec_opera_recla
              (oid_oper_recl,
               num_secu_oper,
               imp_mont_perd,
               val_porc_perd,
               care_oid_cabe_recl,
               tibl_oid_tipo_bloq,
               mobl_oid_moti_bloq,
               inem_oid_indi_entr_merc,
               aspe_oid_asum_perd,
               prod_oid_prod,
               clie_oid_clie,
               mrdb_oid_moti_rech_desb,
               mrdb_oid_moti_desb,
               pper_oid_prec_perd,
               esop_oid_esta_oper,
               clie_oid_resp_perd,
               tpos_oid_tipo_posi,
               soca_oid_soli_cabe,
               perd_oid_peri_recl,
               tspa_oid_tipo_soli_pais,
               tiop_oid_tipo_oper,
               ind_aten,
               fec_fact,
               fec_ulti_actu)
            VALUES
              (ln_tmp_oid_oper,
               ln_sec,
               0,
               NULL,
               ln_tmp_oid_cabe,
               NULL,
               ln_oid_moti_bloq,
               3,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               1,
               NULL,
               NULL,
               v_cab_oid_cabe(i),
               v_cab_oid_peri(i),
               NULL,
               v_det_oid_tipo_oper(i),
               1,
               NULL,
               SYSDATE);
            lscodigooperareclapkaterior := lscodigooperareclapk;
            linea                       := 0;
          
          END IF;
          IF (v_det_cod_vent_dese(i) IS NOT NULL AND
             v_det_prod_oid_prod_envia(i) IS NOT NULL AND
             v_det_tip_refe(i) <> 'D') THEN
            IF (v_det_ind_devu_fisi(i) = 0) THEN
              linea := linea + 1;
              INSERT INTO rec_linea_opera_recla
                (oid_line_oper_recl,
                 num_line,
                 num_unid_recl,
                 val_prec,
                 imp_abon,
                 imp_carg,
                 num_unid_devu,
                 ind_esta,
                 imp_mont_perd,
                 ind_aten,
                 opre_oid_oper_recl,
                 tofe_oid_tipo_ofer,
                 pper_oid_prec_perd,
                 tpos_oid_tipo_posi,
                 timo_oid_tipo_movi,
                 prod_oid_prod,
                 mafa_oid_matr_fact,
                 modv_oid_moti_devo,
                 mrdb_oid_moti_rech_desb,
                 tspa_oid_tipo_soli_pais,
                 sopo_oid_soli_posi,
                 copa_oid_para_gral,
                 panp_oid_para_nive_prem,
                 lopa_oid_lote_prem_arti,
                 val_prec_cont,
                 cod_moti_real)
              VALUES
                (rec_lior_seq.nextval,
                 linea,
                 v_det_can_prod_dese(i) * v_det_val_fact_repe(i),
                 v_det_val_prec_cata_envi(i),
                 v_det_can_prod_dese(i) * v_det_val_fact_repe(i) *
                 v_det_val_prec_cata_envi(i),
                 0, --8 det * 27 det
                 0,
                 'I',
                 0,
                 0,
                 ln_tmp_oid_oper,
                 v_det_tofe_oid_envi(i),
                 NULL,
                 NULL,
                 2,
                 v_det_prod_oid_prod_envia(i),
                 v_det_mafa_oid_envi(i),
                 (SELECT oid_moti_devo
                    FROM rec_motiv_devol
                   WHERE cod_moti_devo = v_det_mot_spv(i)), ---------------------------
                 NULL,
                 NULL, --v_det_tspa_oid_tipo_soli_paenv(i),--43 det
                 v_det_oid_soli_posi_envi(i), --45 det
                 v_det_copa_oid_para_gene_envi(i), -- 33 det
                 v_det_panp_oid_para_nive_prenv(i), -- 31 det
                 v_det_lopa_oid_lote_prem_arenv(i), --32 det
                 nvl(v_det_val_prec_cont_envi(i), 0), --28 det
                 v_cod_moti_real(i));
              lv_abono_total_reclamo := nvl(lv_abono_total_reclamo, 0) +
                                        nvl(v_det_val_fact_repe(i), 0) *
                                        nvl(v_det_can_prod_dese(i), 0) *
                                        nvl(v_det_val_prec_cata_envi(i), 0);
            END IF;
          
            IF ((v_det_ind_devu_fisi(i) = 1) OR
               ((v_det_ind_devu_fisi(i) = 0) AND
               (v_det_ind_envi_fact(i) = 1))) THEN
              linea := linea + 1;
              INSERT INTO rec_linea_opera_recla
                (oid_line_oper_recl,
                 num_line,
                 num_unid_recl,
                 val_prec,
                 imp_abon,
                 imp_carg,
                 num_unid_devu,
                 ind_esta,
                 imp_mont_perd,
                 ind_aten,
                 opre_oid_oper_recl,
                 tofe_oid_tipo_ofer,
                 pper_oid_prec_perd,
                 tpos_oid_tipo_posi,
                 timo_oid_tipo_movi,
                 prod_oid_prod,
                 mafa_oid_matr_fact,
                 modv_oid_moti_devo,
                 mrdb_oid_moti_rech_desb,
                 tspa_oid_tipo_soli_pais,
                 sopo_oid_soli_posi,
                 copa_oid_para_gral,
                 panp_oid_para_nive_prem,
                 lopa_oid_lote_prem_arti,
                 val_prec_cont,
                 cod_moti_real)
              VALUES
                (rec_lior_seq.nextval,
                 linea,
                 v_det_can_prod_dese(i) * v_det_val_fact_repe(i),
                 v_det_val_prec_cata_envi(i),
                 0,
                 v_det_can_prod_dese(i) * v_det_val_fact_repe(i) *
                 v_det_val_prec_cata_envi(i), --8 det * 27 det
                 0,
                 'I',
                 0,
                 0,
                 ln_tmp_oid_oper,
                 v_det_tofe_oid_envi(i),
                 NULL,
                 NULL,
                 1,
                 v_det_prod_oid_prod_envia(i),
                 v_det_mafa_oid_envi(i),
                 NULL,
                 NULL,
                 NULL, --v_det_tspa_oid_tipo_soli_paenv(i),--43 det
                 v_det_oid_soli_posi_envi(i), --45 det
                 v_det_copa_oid_para_gene_envi(i), -- 33 det
                 v_det_panp_oid_para_nive_prenv(i), -- 31 det
                 v_det_lopa_oid_lote_prem_arenv(i), --32 det
                 nvl(v_det_val_prec_cont_envi(i), 0), --28 det
                 v_cod_moti_real(i));
              lv_cargo_total_reclamo := nvl(lv_cargo_total_reclamo, 0) +
                                        nvl(v_det_val_fact_repe(i), 0) *
                                        nvl(v_det_can_prod_dese(i), 0) *
                                        nvl(v_det_val_prec_cata_envi(i), 0);
            END IF;
          END IF;
        
          IF (v_det_cod_vent_devu(i) IS NOT NULL AND
             v_det_prod_oid_prod_devu(i) IS NOT NULL AND
             v_det_tspa_oid_tipo_soli_padev(i) IS NOT NULL) THEN
            linea := linea + 1;
            INSERT INTO rec_linea_opera_recla
              (oid_line_oper_recl,
               num_line,
               num_unid_recl,
               val_prec,
               imp_abon,
               imp_carg,
               num_unid_devu,
               ind_esta,
               imp_mont_perd,
               ind_aten,
               opre_oid_oper_recl,
               tofe_oid_tipo_ofer,
               pper_oid_prec_perd,
               tpos_oid_tipo_posi,
               timo_oid_tipo_movi,
               prod_oid_prod,
               mafa_oid_matr_fact,
               modv_oid_moti_devo,
               mrdb_oid_moti_rech_desb,
               tspa_oid_tipo_soli_pais,
               sopo_oid_soli_posi,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               lopa_oid_lote_prem_arti,
               val_prec_cont,
               cod_moti_real)
            VALUES
              (rec_lior_seq.nextval,
               linea,
               v_det_can_vent_devu(i) * v_det_val_fact_repe(i), --21 det
               v_det_val_prec_cata_devu(i), --27 det
               v_det_can_vent_devu(i) * v_det_val_fact_repe(i) *
               v_det_val_prec_cata_devu(i), -- 8 det * --27 det
               0,
               0,
               'I',
               0,
               0,
               ln_tmp_oid_oper,
               v_det_tofe_oid_devu(i), --39 det
               NULL,
               NULL,
               2,
               v_det_prod_oid_prod_devu(i), --42 det
               v_det_mafa_oid_devu(i), --41 det
               (SELECT oid_moti_devo
                  FROM rec_motiv_devol
                 WHERE cod_moti_devo = v_det_mot_spv(i)), ----------------------------------
               NULL,
               NULL, --v_det_tspa_oid_tipo_soli_padev(i), --44 det
               v_det_oid_soli_posi_devu(i), --46 det
               v_det_copa_oid_para_gene_devu(i), --36 det,
               v_det_panp_oid_para_nive_prdev(i), --34 det
               v_det_lopa_oid_lote_prem_ardev(i), --35 det
               nvl(v_det_val_prec_cont_devu(i), 0), --30 det
               v_cod_moti_real(i));
            lv_abono_total_reclamo := nvl(lv_abono_total_reclamo, 0) +
                                      nvl(v_det_val_fact_repe(i), 0) *
                                      nvl(v_det_can_vent_devu(i), 0) *
                                      nvl(v_det_val_prec_cata_devu(i), 0);
          
          END IF;
        
          --  Si <Posicion 7 Detalle> <> null
          IF (v_det_ind_envi_gener_devu(i) = '1' AND
             v_det_cod_vent_devu(i) IS NULL AND
             v_det_prod_oid_prod_envia(i) IS NOT NULL) THEN
            linea := linea + 1;
            INSERT INTO rec_linea_opera_recla
              (oid_line_oper_recl,
               num_line,
               num_unid_recl,
               val_prec,
               imp_abon,
               imp_carg,
               num_unid_devu,
               ind_esta,
               imp_mont_perd,
               ind_aten,
               opre_oid_oper_recl,
               tofe_oid_tipo_ofer,
               pper_oid_prec_perd,
               tpos_oid_tipo_posi,
               timo_oid_tipo_movi,
               prod_oid_prod,
               mafa_oid_matr_fact,
               modv_oid_moti_devo,
               mrdb_oid_moti_rech_desb,
               tspa_oid_tipo_soli_pais,
               sopo_oid_soli_posi,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               lopa_oid_lote_prem_arti,
               val_prec_cont,
               cod_moti_real)
            VALUES
              (rec_lior_seq.nextval,
               linea,
               v_det_can_prod_dese(i) * v_det_val_fact_repe(i), -- 8 det
               v_det_val_prec_cata_envi(i), --27 det
               v_det_can_prod_dese(i) * v_det_val_fact_repe(i) *
               v_det_val_prec_cata_envi(i), --8 det * 27 det
               0,
               0,
               'I',
               0,
               0,
               ln_tmp_oid_oper,
               v_det_tofe_oid_envi(i), --42 det
               NULL,
               NULL,
               2,
               v_det_prod_oid_prod_envia(i), --39 det
               v_det_mafa_oid_envi(i), --41 det
               (SELECT oid_moti_devo
                  FROM rec_motiv_devol
                 WHERE cod_moti_devo = v_det_mot_spv(i)), --------------------------------
               NULL,
               v_det_tspa_oid_tipo_soli_paenv(i), --44 det
               v_det_oid_soli_posi_envi(i), --46 det
               v_det_copa_oid_para_gene_envi(i), -- 36 det
               v_det_panp_oid_para_nive_prenv(i), -- 34 det
               v_det_lopa_oid_lote_prem_arenv(i), --35 det
               nvl(v_det_val_prec_cont_envi(i), 0), --30 det
               v_cod_moti_real(i));
            lv_abono_total_reclamo := nvl(lv_abono_total_reclamo, 0) +
                                      nvl(v_det_val_fact_repe(i), 0) *
                                      nvl(v_det_can_prod_dese(i), 0) *
                                      nvl(v_det_val_prec_cata_envi(i), 0);
          
          END IF;
          -- Si <Posicion 22 Detalle> = 1
          IF (v_det_ind_devu_gener_envi(i) = 1 AND
             v_det_cod_vent_dese(i) IS NULL AND
             (v_det_prod_oid_prod_devu(i) IS NOT NULL)) THEN
            linea := linea + 1;
            INSERT INTO rec_linea_opera_recla
              (oid_line_oper_recl,
               num_line,
               num_unid_recl,
               val_prec,
               imp_abon,
               imp_carg,
               num_unid_devu,
               ind_esta,
               imp_mont_perd,
               ind_aten,
               opre_oid_oper_recl,
               tofe_oid_tipo_ofer,
               pper_oid_prec_perd,
               tpos_oid_tipo_posi,
               timo_oid_tipo_movi,
               prod_oid_prod,
               mafa_oid_matr_fact,
               modv_oid_moti_devo,
               mrdb_oid_moti_rech_desb,
               tspa_oid_tipo_soli_pais,
               sopo_oid_soli_posi,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               lopa_oid_lote_prem_arti,
               val_prec_cont,
               cod_moti_real)
            VALUES
              (rec_lior_seq.nextval,
               linea,
               v_det_can_vent_devu(i) * v_det_val_fact_repe(i), -- 8 det
               v_det_val_prec_cata_devu(i), --27 det
               0,
               v_det_can_vent_devu(i) * v_det_val_fact_repe(i) *
               v_det_val_prec_cata_devu(i), --8det * 27 det
               0,
               'I',
               0,
               0,
               ln_tmp_oid_oper,
               v_det_tofe_oid_devu(i), --37 det
               NULL,
               NULL,
               1,
               v_det_prod_oid_prod_devu(i), --37 det
               v_det_mafa_oid_devu(i), --38 det
               NULL,
               NULL,
               NULL, --v_det_tspa_oid_tipo_soli_paenv(i), --43 det
               v_det_oid_soli_posi_devu(i), --45 det
               v_det_copa_oid_para_gene_devu(i), --33 det
               v_det_panp_oid_para_nive_prdev(i), --31 det
               v_det_lopa_oid_lote_prem_ardev(i), --32 det
               nvl(v_det_val_prec_cont_devu(i), 0), --28 det
               v_cod_moti_real(i));
            lv_cargo_total_reclamo := nvl(lv_cargo_total_reclamo, 0) +
                                      nvl(v_det_val_fact_repe(i), 0) *
                                      nvl(v_det_can_vent_devu(i), 0) *
                                      nvl(v_det_val_prec_cata_devu(i), 0);
          
            --- cuando termina de insertar todas las lineas de reclamo le suma el total a los campos de cabecera
          
          END IF;
        
          -- Actualziamos Indicadores de Envio
          UPDATE sto_docum_digit occ
             SET occ.ind_envi = '1',
                 occ.usu_modi = lsusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.sec_nume_docu = v_det_sec_nume_docu(i)
             AND occ.num_lote = v_det_num_lote(i)
             AND occ.cod_tipo_docu = lscodigodocumentodetalle
             AND occ.cod_pais = pscodigopais;
        
        END LOOP;
        UPDATE rec_cabec_recla rcr
           SET rcr.num_tota_envi = lv_cargo_total_reclamo,
               rcr.num_tota_devu = lv_abono_total_reclamo
         WHERE rcr.oid_cabe_recl = ln_tmp_oid_cabe;
      
        UPDATE rec_cabec_recla rcr
           SET rcr.imp_sald_paga = lv_cargo_total_reclamo -
                                   lv_abono_total_reclamo
         WHERE rcr.oid_cabe_recl = ln_tmp_oid_cabe;
      
        IF (lnindonline = '1') THEN
          lnnumsoliinicioped := sto_pr_graba_ped_rec_oid(ln_tmp_oid_cabe,
                                                         lsusuario,
                                                         lnnumsoliinicioped);
        END IF;
      
      END IF;
    
      EXIT WHEN c_tiposolicitud%NOTFOUND;
    
    END LOOP;
  
    CLOSE c_tiposolicitud;
  
    /*ELIMINA LOS REGISTROS DUPLICADOS*/
    /* Se omite este SP ya que no se esta ingresando cdrs
    por sICC y SSICC a la vez*/
    /*sto_pr_elimin_duplic_reclam; */
  
    IF (lnindonlin2 = '1') THEN
      sto_pr_compl_recla(psusuario, pscodigopais, lnnumreclamos);
    END IF;
  
    sto_pkg_gener.sto_pr_regis_detal_sncab(pscodigopais,
                                           pscodigotipodoccabecera,
                                           lscodigodocumentodetalle,
                                           psnumeroproceso);
  
    sto_pkg_gener.sto_pr_after_envio(pscodigopais,
                                     pscodigotipodoccabecera,
                                     psnumeroproceso);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_ENVIO_SICC: ' ||
                              ls_sqlerrm ||
                              'llave: lote pais peri clie ndoc ' ||
                              lscodigodocumentopkant || ' ');
    
  END sto_pr_spvc_envio_sicc;

  /**************************************************************************
  Descripcion       : STO_PR_SAD_ENVIO_SICC
                      Envio de SAD a SICC
  Fecha Creacion    : 10/06/2008
  Parametros Entrada:
      psCodigoPais        : Codigo de pais
      psCodigoTipoDoc     : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario           : Codigo de Usuario
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_sad_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_sadenvio IS
      SELECT cons.cod_pais,
             cons.docu_cod_tipo_docu,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_ape1, --11
             cons.val_ape2, --12
             cons.val_nom1, --13
             cons.val_nom2, --14
             cons.cod_clie, --3
             cons.val_mail_clie, --29
             cons.cod_peri, --8
             cons.oid_terr_admi, --63
             cons.oid_peri, --64
             cons.tip_via_clie, --47
             cons.val_nomb_vicl, --48
             cons.num_dire_clie, --49
             cons.cod_depa_clie, --50
             cons.cod_prov_clie, --51
             cons.cod_dist_clie, --52
             cons.cod_sect_clie, --53
             cons.oid_terr, --62
             cons.val_dire_clie, --21
             cons.tip_docu, --16
             cons.num_docu_iden, --17
             cons.num_ruc, --18
             cons.val_telf_clie, --25
             cons.val_celu_clie, --26
             cons.val_telf_trab, --27
             cons.val_tipo_dire_clie, --65
             cons.val_dire_entre_clie,
             cons.val_celu_entre_clie,
             cons.val_tele_entre_clie,
             cons.val_barr_clie,
             cons.val_obse,
             cons.val_ape2_fiad,
             cons.val_nom2_fiad,
             cons.oid_naci,
             cons.cod_sexo,
             cons.ciud_cod_ciud_domi,
             cons.ciud_cod_ugeo_regi_domi,
             cons.des_villa_pobl_domi,
             cons.dom_manz,
             cons.dom_etap,
             cons.dom_call_prin,
             cons.dom_call_secu,
             cons.dom_num,
             cons.dom_refe,
             cons.ent_manz,
             cons.ent_etap,
             cons.ent_call_prin,
             cons.ent_call_secu,
             cons.ent_num,
             cons.ent_refe,
             cons.cod_terr_corr,
             cons.val_barr_entre_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_tipodocu IS TABLE OF int_solic_conso_actua_datos.docu_cod_tipo_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
  
    TYPE t_val_ape1 IS TABLE OF int_solic_conso_actua_datos.val_ape1%TYPE;
    TYPE t_val_ape2 IS TABLE OF int_solic_conso_actua_datos.val_ape2%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_solic_conso_actua_datos.val_nom1%TYPE;
    TYPE t_val_nom2 IS TABLE OF int_solic_conso_actua_datos.val_nom2%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_val_mail_clie IS TABLE OF int_solic_conso_actua_datos.val_mail_clie%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_actua_datos.cod_peri%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_actua_datos.oid_terr_admi%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_actua_datos.oid_peri%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_actua_datos.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_actua_datos.val_nomb_vicl%TYPE;
    TYPE t_num_dire_clie IS TABLE OF int_solic_conso_actua_datos.num_dire_clie%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_actua_datos.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_actua_datos.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_actua_datos.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_actua_datos.cod_sect_clie%TYPE;
    TYPE t_oid_terr IS TABLE OF int_solic_conso_actua_datos.oid_terr%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_clie%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_actua_datos.num_ruc%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_actua_datos.val_telf_clie%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_actua_datos.val_celu_clie%TYPE;
    TYPE t_val_telf_trab IS TABLE OF int_solic_conso_actua_datos.val_telf_trab%TYPE;
  
    TYPE t_val_tipo_dire IS TABLE OF int_solic_conso_actua_datos.val_tipo_dire_clie%TYPE;
    TYPE t_val_dire_entre_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_entre_clie%TYPE;
    TYPE t_val_celu_entre_clie IS TABLE OF int_solic_conso_actua_datos.val_celu_entre_clie%TYPE;
    TYPE t_val_tele_entre_clie IS TABLE OF int_solic_conso_actua_datos.val_tele_entre_clie%TYPE;
    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_actua_datos.val_barr_clie%TYPE;
  
    TYPE t_val_obse IS TABLE OF int_solic_conso_actua_datos.val_obse%TYPE;
    TYPE t_val_ape2_fiad IS TABLE OF int_solic_conso_actua_datos.val_ape2_fiad%TYPE;
    TYPE t_val_nom2_fiad IS TABLE OF int_solic_conso_actua_datos.val_nom2_fiad%TYPE;
    TYPE t_oid_naci IS TABLE OF int_solic_conso_actua_datos.oid_naci%TYPE;
    TYPE t_cod_sexo IS TABLE OF int_solic_conso_actua_datos.cod_sexo%TYPE;
    TYPE t_ciud_cod_ciud_domi IS TABLE OF int_solic_conso_actua_datos.ciud_cod_ciud_domi%TYPE;
    TYPE t_ciud_cod_ugeo_regi_domi IS TABLE OF int_solic_conso_actua_datos.ciud_cod_ugeo_regi_domi%TYPE;
    TYPE t_des_villa_pobl_domi IS TABLE OF int_solic_conso_actua_datos.des_villa_pobl_domi%TYPE;
    TYPE t_dom_manz        IS TABLE OF int_solic_conso_credi.dom_manz%TYPE;      
    TYPE t_dom_etap        IS TABLE OF int_solic_conso_credi.dom_etap%TYPE;      
    TYPE t_dom_call_prin   IS TABLE OF int_solic_conso_credi.dom_call_prin%TYPE;
    TYPE t_dom_call_secu   IS TABLE OF int_solic_conso_credi.dom_call_secu%TYPE; 
    TYPE t_dom_num         IS TABLE OF int_solic_conso_credi.dom_num%TYPE;       
    TYPE t_dom_refe        IS TABLE OF int_solic_conso_credi.dom_refe%TYPE;      
    TYPE t_ent_manz        IS TABLE OF int_solic_conso_credi.ent_manz%TYPE;      
    TYPE t_ent_etap        IS TABLE OF int_solic_conso_credi.ent_etap%TYPE;      
    TYPE t_ent_call_prin   IS TABLE OF int_solic_conso_credi.ent_call_prin%TYPE;
    TYPE t_ent_call_secu   IS TABLE OF int_solic_conso_credi.ent_call_secu%TYPE; 
    TYPE t_ent_num         IS TABLE OF int_solic_conso_credi.ent_num%TYPE;       
    TYPE t_ent_refe        IS TABLE OF int_solic_conso_credi.ent_refe%TYPE;    
    TYPE t_cod_terr_corr   IS TABLE OF int_solic_conso_credi.ent_refe%TYPE; 
    TYPE t_val_barr_entre_clie   IS TABLE OF int_solic_conso_credi.val_barr_entre_clie%TYPE; 
  
    v_codpais    t_codpais;
    v_tipodocu   t_tipodocu;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
  
    v_val_ape1      t_val_ape2;
    v_val_ape2      t_val_ape1;
    v_val_nom1      t_val_nom1;
    v_val_nom2      t_val_nom2;
    v_cod_clie      t_cod_clie;
    v_val_mail_clie t_val_mail_clie;
    v_cod_peri      t_cod_peri;
    v_oid_terr_admi t_oid_terr_admi;
    v_oid_peri      t_oid_peri;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_num_dire_clie t_num_dire_clie;
    v_cod_depa_clie t_cod_depa_clie;
    v_cod_prov_clie t_cod_prov_clie;
    v_cod_dist_clie t_cod_dist_clie;
    v_cod_sect_clie t_cod_sect_clie;
    v_oid_terr      t_oid_terr;
    v_val_dire_clie t_val_dire_clie;
    v_tip_docu      t_tip_docu;
    v_num_docu_iden t_num_docu_iden;
    v_num_ruc       t_num_ruc;
    v_val_telf_clie t_val_telf_clie;
    v_val_celu_clie t_val_celu_clie;
    v_val_telf_trab t_val_telf_trab;
  
    v_val_tipo_dire       t_val_tipo_dire;
    v_val_dire_entre_clie t_val_dire_entre_clie;
    v_val_celu_entre_clie t_val_celu_entre_clie;
    v_val_tele_entre_clie t_val_tele_entre_clie;
    v_val_barr_clie       t_val_barr_clie;
  
    v_val_obse                t_val_obse;
    v_val_ape2_fiad           t_val_ape2_fiad;
    v_val_nom2_fiad           t_val_nom2_fiad;
    v_oid_naci                t_oid_naci;
    v_cod_sexo                t_cod_sexo;
    v_ciud_cod_ciud_domi      t_ciud_cod_ciud_domi;
    v_ciud_cod_ugeo_regi_domi t_ciud_cod_ugeo_regi_domi;
    v_des_villa_pobl_domi     t_des_villa_pobl_domi;
    v_dom_manz         t_dom_manz;     
    v_dom_etap         t_dom_etap;     
    v_dom_call_prin    t_dom_call_prin;
    v_dom_call_secu    t_dom_call_secu;
    v_dom_num          t_dom_num;      
    v_dom_refe         t_dom_refe;     
    v_ent_manz         t_ent_manz;     
    v_ent_etap         t_ent_etap;     
    v_ent_call_prin    t_ent_call_prin;
    v_ent_call_secu    t_ent_call_secu;
    v_ent_num          t_ent_num;      
    v_ent_refe         t_ent_refe;
    v_cod_terr_corr  t_cod_terr_corr;
    v_val_barr_entre_clie   t_val_barr_entre_clie; 
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    tmp_oid_clie        mae_clien.oid_clie%TYPE;
    oid_terri_admin_tmp mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
    oid_peri_ant        cra_perio.oid_peri%TYPE;
    oid_peri_sig        cra_perio.oid_peri%TYPE;
    ind_prin_dni        NUMBER;
  
    oid_terr_temp mae_clien_direc.terr_oid_terr%TYPE;
    tmp_cont      NUMBER;
  
    lnpasopedido             NUMBER;
    contador_mae_clien       NUMBER := 0;
    contador_mae_clien_direc NUMBER := 0;
  
    f_existe NUMBER := 0;
  
    lsdireccionentrega sto_param_gener_occrr.val_param%TYPE;
    lstelefonoentrega  sto_param_gener_occrr.val_param%TYPE;
    lscelularentrega   sto_param_gener_occrr.val_param%TYPE;
    lnidpais           NUMBER;
    lnidmarca          NUMBER;
    lnidcanal          NUMBER;
    lsCampNew          VARCHAR2(1);
    lsdirbarrio        VARCHAR2(1);
  BEGIN
  
    lsdireccionentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'COD_TIPO_DIRE_ENTR');
  
    lstelefonoentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'COD_TIPO_TELE_ENTR');
  
    lscelularentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'COD_TIPO_CELU_ENTR');
                                                             
    lsdirbarrio := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_DIR_BARRIO');                                                             
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
    
    select val_para into lsCampNew 
    from bas_param_pais where cod_pais =pscodigopais
    and cod_para='056'
    and cod_sist='OCR';
    
    OPEN c_sadenvio;
    LOOP
      FETCH c_sadenvio BULK COLLECT
        INTO v_codpais,
             v_tipodocu,
             v_numlote,
             v_secnumdocu,
             v_val_ape1,
             v_val_ape2,
             v_val_nom1,
             v_val_nom2,
             v_cod_clie,
             v_val_mail_clie,
             v_cod_peri,
             v_oid_terr_admi,
             v_oid_peri,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_num_dire_clie,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_oid_terr,
             v_val_dire_clie,
             v_tip_docu,
             v_num_docu_iden,
             v_num_ruc,
             v_val_telf_clie,
             v_val_celu_clie,
             v_val_telf_trab,
             v_val_tipo_dire,
             v_val_dire_entre_clie,
             v_val_celu_entre_clie,
             v_val_tele_entre_clie,
             v_val_barr_clie,
             v_val_obse,
             v_val_ape2_fiad,
             v_val_nom2_fiad,
             v_oid_naci,
             v_cod_sexo,
             v_ciud_cod_ciud_domi,
             v_ciud_cod_ugeo_regi_domi,
             v_des_villa_pobl_domi,
             v_dom_manz,      
             v_dom_etap,      
             v_dom_call_prin, 
             v_dom_call_secu, 
             v_dom_num,       
             v_dom_refe,      
             v_ent_manz,      
             v_ent_etap,      
             v_ent_call_prin, 
             v_ent_call_secu, 
             v_ent_num,       
             v_ent_refe,
             v_cod_terr_corr,
             v_val_barr_entre_clie      
               LIMIT rows;
    
      IF v_codpais.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          -- Actualizar Datos en MAE_CLIEN
          SELECT oid_clie
            INTO tmp_oid_clie
            FROM mae_clien
           WHERE cod_clie = v_cod_clie(i);
        
          SELECT COUNT(1)
            INTO lnpasopedido
            FROM ped_solic_cabec a
           WHERE a.ind_oc = 1
             AND a.clie_oid_clie = tmp_oid_clie
             AND a.val_tota_paga_loca > 0
             AND a.fec_fact IS NOT NULL
             AND a.perd_oid_peri = v_oid_peri(i);
        
          UPDATE mae_clien a
             SET a.val_ape1 = decode(v_val_ape1(i),
                                     NULL,
                                     a.val_ape1,
                                     v_val_ape1(i)),
                 a.val_ape2 = decode(v_val_ape2(i),
                                     NULL,
                                     a.val_ape2,
                                     v_val_ape2(i)),
                 a.val_nom1 = decode(v_val_nom1(i),
                                     NULL,
                                     a.val_nom1,
                                     v_val_nom1(i)),
                 a.val_nom2 = decode(v_val_nom2(i),
                                     NULL,
                                     a.val_nom2,
                                     v_val_nom2(i)),
                 a.cod_sexo = decode(v_cod_sexo(i),
                                     NULL,
                                     a.cod_sexo,
                                     v_cod_sexo(i)),
                 a.usu_modi = psusuario
           WHERE cod_clie = v_cod_clie(i);
        
          -- Actualizar Datos en MAE_CLIEN_UNIDA_ADMIN
          IF (v_oid_terr_admi(i) IS NOT NULL) THEN
            SELECT ztad_oid_terr_admi
              INTO oid_terri_admin_tmp
              FROM mae_clien_unida_admin
             WHERE perd_oid_peri_fin IS NULL
               AND clie_oid_clie = tmp_oid_clie
               AND rownum = 1;
          
            oid_peri_ant := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(per_pkg_repor_perce.per_fn_obtie_perio(v_cod_peri(i),
                                                                                                              lnidpais,
                                                                                                              lnidmarca,
                                                                                                              lnidcanal,
                                                                                                              -1),
                                                                       lnidmarca,
                                                                       lnidcanal);
          
            oid_peri_sig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(per_pkg_repor_perce.per_fn_obtie_perio(v_cod_peri(i),
                                                                                                              lnidpais,
                                                                                                              lnidmarca,
                                                                                                              lnidcanal,
                                                                                                              1),
                                                                       lnidmarca,
                                                                       lnidcanal);
          
            IF oid_terri_admin_tmp != v_oid_terr_admi(i) THEN
            
              /*DELETE FROM mae_clien_unida_admin
              WHERE clie_oid_clie = tmp_oid_clie
                AND perd_oid_peri_ini = v_oid_peri(i);*/
              --
              -- Desactivo todas
              UPDATE mae_clien_unida_admin
                 SET ind_acti          = decode(lnpasopedido, 0, 0, 1),
                     perd_oid_peri_fin = decode(lnpasopedido,
                                                0,
                                                oid_peri_ant,
                                                v_oid_peri(i)),
                     fec_ulti_actu     = SYSDATE,
                     usu_modi          = psusuario,
                     fec_camb          = SYSDATE
               WHERE ind_acti = 1
                 AND perd_oid_peri_fin IS NULL
                 AND clie_oid_clie = tmp_oid_clie;
            
              SELECT COUNT(1)
                INTO f_existe
                FROM mae_clien_unida_admin ua
               WHERE clie_oid_clie = tmp_oid_clie
                 AND perd_oid_peri_ini =
                     decode(lnpasopedido, 0, v_oid_peri(i), oid_peri_sig);
              IF f_existe = 0 THEN
                --
              
                INSERT INTO mae_clien_unida_admin
                  (oid_clie_unid_admi,
                   clie_oid_clie,
                   perd_oid_peri_ini,
                   perd_oid_peri_fin,
                   ztad_oid_terr_admi,
                   ind_acti,
                   fec_ulti_actu,
                   usu_modi,
                   fec_camb)
                VALUES
                  (mae_cuad_seq.nextval,
                   tmp_oid_clie,
                   decode(lnpasopedido, 0, v_oid_peri(i), oid_peri_sig),
                   NULL,
                   v_oid_terr_admi(i),
                   decode(lnpasopedido, 0, 1, 0),
                   SYSDATE,
                   psusuario,
                   SYSDATE);
              
              ELSE
                -- si ya existia la UA para la campa?a y la consultora
                UPDATE mae_clien_unida_admin ua
                   SET ua.ind_acti           = decode(lnpasopedido, 0, 1, 0),
                       ua.perd_oid_peri_fin  = NULL,
                       ua.ztad_oid_terr_admi = v_oid_terr_admi(i),
                       ua.usu_modi           = psusuario,
                       ua.fec_camb           = SYSDATE
                 WHERE clie_oid_clie = tmp_oid_clie
                   AND perd_oid_peri_ini =
                       decode(lnpasopedido, 0, v_oid_peri(i), oid_peri_sig);
              
              END IF;
              --
            
            END IF;
          END IF;
          -- Actualizar Datos en MAE_CLIEN_DIREC
        
          --IF v_val_dire_clie(i) IS NOT NULL THEN
          IF ((v_val_dire_clie(i) IS NOT NULL OR
             v_tip_via_clie(i) IS NOT NULL OR
             v_val_nomb_vicl(i) IS NOT NULL OR
             --v_dom_refe(i) 
             v_dom_manz(i)||v_dom_etap(i)||v_dom_call_prin(i)||v_dom_num(i)||v_dom_call_secu(i)||v_dom_refe(i)||v_val_barr_clie(i) IS NOT NULL ) AND
             (length(v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                      v_cod_dist_clie(i)) > 17)) THEN
          
            BEGIN
            
              SELECT terr_oid_terr
                INTO oid_terr_temp
                FROM mae_clien_direc
               WHERE ind_elim = 0
                 AND clie_oid_clie = tmp_oid_clie
                 AND ind_dire_ppal = 1;
            
            EXCEPTION
              WHEN no_data_found THEN
                oid_terr_temp := NULL;
            END;
          
            UPDATE mae_clien_direc
               SET ind_elim      = 1,
                   fec_ulti_actu = SYSDATE,
                   usu_modi      = psusuario
             WHERE ind_elim = 0
               AND clie_oid_clie = tmp_oid_clie;
          
            INSERT INTO mae_clien_direc
              (oid_clie_dire,
               clie_oid_clie,
               tidc_oid_tipo_dire,
               tivi_oid_tipo_via,
               terr_oid_terr,
               zvia_oid_via,
               num_ppal,
               val_nomb_via,
               val_cod_post,
               val_inte,
               val_manz,
               val_lote,
               val_km,
               val_obse,
               val_barr,
               val_nomb_fich,
               val_coor_x,
               val_coor_y,
               val_coor_z,
               ind_dire_ppal,
               ind_ctrl_inte_geor,
               fec_ulti_actu,
               cod_unid_geog,
               ind_elim,
               ciud_cod_ciud,
               ciud_cod_ugeo_regi,
               des_villa_pobl,
               usu_modi,
               val_nom_manz,
               val_eta_conj,
               val_cal_prin,
               val_cal_secu,
               teco_cod_terr_corr,
               num_ppri)
            VALUES
              (mae_cldi_seq.nextval,
               tmp_oid_clie,
               2001,
               -- comment efernandezo (SELECT oid_tipo_via FROM seg_tipo_via WHERE to_number(cod_tipo_via) = v_tip_via_clie(i)),
               (SELECT oid_tipo_via
                  FROM seg_tipo_via
                 WHERE cod_tipo_via = nvl(v_tip_via_clie(i), '99')),
               nvl(v_oid_terr(i), oid_terr_temp),
               NULL,
               v_num_dire_clie(i),
               v_val_nomb_vicl(i)||decode(lsCampNew,'0','', TRIM(v_dom_manz(i)) || ' '||TRIM(v_dom_etap(i)) || ' '||TRIM(v_dom_call_prin(i)) || ' '||
                        TRIM(v_dom_num(i)) || ' '||TRIM(v_dom_call_secu(i))),
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               v_val_dire_clie(i) ||
               decode(lsdirbarrio,
                    'N',
                    '',
                   decode(v_val_barr_clie(i),
                          NULL,
                          NULL,
                          '-' || v_val_barr_clie(i)))||TRIM(v_dom_refe(i)),
               decode(lsCampNew, '0','',v_val_barr_clie(i)),            
               NULL,
               0,
               0,
               0,
               1,
               'S',
               SYSDATE,
               v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
               v_cod_dist_clie(i) || v_cod_sect_clie(i),
               0,
               v_ciud_cod_ciud_domi(i),
               v_ciud_cod_ugeo_regi_domi(i),
               v_des_villa_pobl_domi(i),
               psusuario,
               v_dom_manz(i),
               v_dom_etap(i),
               v_dom_call_prin(i),
               v_dom_call_secu(i),
               v_cod_terr_corr(i),
               v_dom_num(i)
               );
          END IF;
          --END IF;
        
          IF (lsdireccionentrega IS NOT NULL ) THEN
          
            IF ((v_val_dire_entre_clie(i) IS NOT NULL OR 
                v_ent_manz(i)||v_ent_etap(i)||v_ent_call_prin(i)||v_ent_num(i)||v_ent_call_secu(i)||v_ent_refe(i)||v_val_barr_entre_clie(i) IS NOT NULL) AND
               (length(v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                        v_cod_dist_clie(i)) > 17)) THEN
            
              BEGIN
              
                SELECT terr_oid_terr
                  INTO oid_terr_temp
                  FROM mae_clien_direc
                 WHERE tidc_oid_tipo_dire =
                       (SELECT oid_tipo_dire
                          FROM mae_tipo_direc
                         WHERE cod_tipo_dire = lsdireccionentrega
                           AND ind_elim = 0)
                   AND clie_oid_clie = tmp_oid_clie;
              
              EXCEPTION
                WHEN no_data_found THEN
                  oid_terr_temp := NULL;
              END;
            
              UPDATE mae_clien_direc
                 SET ind_elim      = 1,
                     ind_dire_ppal = 0,
                     fec_ulti_actu = SYSDATE
               WHERE ind_elim = 0
                 AND clie_oid_clie = tmp_oid_clie
                 AND tidc_oid_tipo_dire =
                     (SELECT oid_tipo_dire
                        FROM mae_tipo_direc
                       WHERE cod_tipo_dire = lsdireccionentrega);
            
              INSERT INTO mae_clien_direc
                (oid_clie_dire,
                 clie_oid_clie,
                 tidc_oid_tipo_dire,
                 tivi_oid_tipo_via,
                 terr_oid_terr,
                 zvia_oid_via,
                 num_ppal,
                 val_nomb_via,
                 val_cod_post,
                 val_inte,
                 val_manz,
                 val_lote,
                 val_km,
                 val_obse,
                 val_barr,
                 val_nomb_fich,
                 val_coor_x,
                 val_coor_y,
                 val_coor_z,
                 ind_dire_ppal,
                 ind_ctrl_inte_geor,
                 fec_ulti_actu,
                 cod_unid_geog,
                 ind_elim,
                 val_nom_manz,
                 val_eta_conj,
                 val_cal_prin,
                 val_cal_secu,
                 teco_cod_terr_corr,
                 num_ppri)
              VALUES
                (mae_cldi_seq.nextval,
                 tmp_oid_clie,
                 (SELECT oid_tipo_dire
                    FROM mae_tipo_direc
                   WHERE cod_tipo_dire = lsdireccionentrega),
                 (SELECT oid_tipo_via
                    FROM seg_tipo_via
                   WHERE cod_tipo_via = '99'),
                 nvl(v_oid_terr(i), oid_terr_temp),
                 NULL,
                 NULL,
                 --v_ent_num(i),
                 TRIM(v_ent_manz(i)) || ' '||TRIM(v_ent_etap(i)) || ' '||TRIM(v_ent_call_prin(i)) || ' '||
                 TRIM(v_ent_num(i)) || ' '||TRIM(v_ent_call_secu(i)),
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 NULL,             
                 TRIM(v_val_dire_entre_clie(i)) ||decode(lsCampNew,'0','', v_ent_refe(i) || ' ') || TRIM(v_val_obse(i)),                 
                 decode(lsCampNew, '0','',v_val_barr_entre_clie(i)),         
                 NULL,
                 0,
                 0,
                 0,
                 0,
                 'S',
                 SYSDATE,
                 v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                 v_cod_dist_clie(i) || v_cod_sect_clie(i),
                 0,
                 v_ent_manz(i),
                 v_ent_etap(i),
                 v_ent_call_prin(i),
                 v_ent_call_secu(i),
                 v_cod_terr_corr(i),
                 v_ent_num(i));
            
            END IF;
          
          END IF;
        
          --Insercion en MAE_CLIEN_COMUN
        
          IF (v_val_telf_clie(i) IS NOT NULL) THEN
          
            contador_mae_clien := 0;
            BEGIN
              SELECT COUNT(1)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 1
                 AND clie_oid_clie = tmp_oid_clie;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_val_telf_clie(i),
                       fec_ulti_actu = SYSDATE
                 WHERE ticm_oid_tipo_comu = 1
                   AND clie_oid_clie = tmp_oid_clie;
              ELSE
                UPDATE mae_clien_comun
                   SET ind_comu_ppal = '0',
                       fec_ulti_actu = SYSDATE
                 WHERE clie_oid_clie = tmp_oid_clie;
              
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   tmp_oid_clie,
                   1,
                   'L',
                   v_val_telf_clie(i),
                   NULL,
                   NULL,
                   1,
                   1,
                   SYSDATE);
              END IF;
            
            END;
          
          END IF;
        
          IF (v_val_celu_clie(i) IS NOT NULL) THEN
          
            contador_mae_clien := 0;
            BEGIN
              SELECT COUNT(1)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 6
                 AND clie_oid_clie = tmp_oid_clie;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_val_celu_clie(i),
                       fec_ulti_actu = SYSDATE
                 WHERE ticm_oid_tipo_comu = 6
                   AND clie_oid_clie = tmp_oid_clie;
              ELSE
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   tmp_oid_clie,
                   6,
                   'L',
                   v_val_celu_clie(i),
                   NULL,
                   NULL,
                   1,
                   0,
                   SYSDATE);
              
              END IF;
            
            END;
          END IF;
        
          IF (v_val_telf_trab(i) IS NOT NULL) THEN
            contador_mae_clien := 0;
          
            BEGIN
              SELECT COUNT(1)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 7
                 AND clie_oid_clie = tmp_oid_clie;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_val_telf_trab(i),
                       fec_ulti_actu = SYSDATE
                 WHERE ticm_oid_tipo_comu = 7
                   AND clie_oid_clie = tmp_oid_clie;
              
              ELSE
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   tmp_oid_clie,
                   7,
                   'L',
                   v_val_telf_trab(i),
                   NULL,
                   NULL,
                   1,
                   0,
                   SYSDATE);
              
              END IF;
            
            END;
          
          END IF;
        
          IF v_val_mail_clie(i) IS NOT NULL THEN
          
            SELECT COUNT(1)
              INTO tmp_cont
              FROM mae_clien_comun
             WHERE clie_oid_clie = tmp_oid_clie
               AND ticm_oid_tipo_comu = 3;
          
            IF tmp_cont <> 0 THEN
              UPDATE mae_clien_comun
                 SET val_text_comu = v_val_mail_clie(i),
                     fec_ulti_actu = SYSDATE
               WHERE ticm_oid_tipo_comu = 3
                 AND clie_oid_clie = tmp_oid_clie;
            ELSE
              INSERT INTO mae_clien_comun
                (oid_clie_comu,
                 clie_oid_clie,
                 ticm_oid_tipo_comu,
                 val_dia_comu,
                 val_text_comu,
                 fec_hora_desd,
                 fec_hora_hast,
                 val_inte_comu,
                 ind_comu_ppal,
                 fec_ulti_actu)
              VALUES
                (mae_clco_seq.nextval,
                 tmp_oid_clie,
                 3,
                 'L',
                 v_val_mail_clie(i),
                 NULL,
                 NULL,
                 1,
                 0,
                 SYSDATE);
            END IF;
          
          END IF;
        
          IF (lstelefonoentrega IS NOT NULL) THEN
          
            IF (v_val_tele_entre_clie(i) IS NOT NULL) THEN
            
              SELECT COUNT(1)
                INTO tmp_cont
                FROM mae_clien_comun
               WHERE clie_oid_clie = tmp_oid_clie
                 AND ticm_oid_tipo_comu IN
                     (SELECT oid_tipo_comu
                        FROM mae_tipo_comun
                       WHERE cod_tipo_comu = lstelefonoentrega);
            
              IF tmp_cont <> 0 THEN
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_val_tele_entre_clie(i),
                       fec_ulti_actu = SYSDATE
                 WHERE ticm_oid_tipo_comu IN
                       (SELECT oid_tipo_comu
                          FROM mae_tipo_comun
                         WHERE cod_tipo_comu = lstelefonoentrega)
                   AND clie_oid_clie = tmp_oid_clie;
              
              ELSE
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   tmp_oid_clie,
                   (SELECT oid_tipo_comu
                      FROM mae_tipo_comun
                     WHERE cod_tipo_comu = lstelefonoentrega),
                   'L',
                   v_val_tele_entre_clie(i),
                   NULL,
                   NULL,
                   1,
                   0,
                   SYSDATE);
              
              END IF;
            
            END IF;
          END IF;
        
          IF (lscelularentrega IS NOT NULL) THEN
          
            IF v_val_celu_entre_clie(i) IS NOT NULL THEN
            
              SELECT COUNT(1)
                INTO tmp_cont
                FROM mae_clien_comun
               WHERE clie_oid_clie = tmp_oid_clie
                 AND ticm_oid_tipo_comu IN
                     (SELECT oid_tipo_comu
                        FROM mae_tipo_comun
                       WHERE cod_tipo_comu = lscelularentrega);
            
              IF tmp_cont <> 0 THEN
                UPDATE mae_clien_comun
                   SET val_text_comu = v_val_celu_entre_clie(i),
                       fec_ulti_actu = SYSDATE
                 WHERE ticm_oid_tipo_comu IN
                       (SELECT oid_tipo_comu
                          FROM mae_tipo_comun
                         WHERE cod_tipo_comu = lscelularentrega)
                   AND clie_oid_clie = tmp_oid_clie;
              ELSE
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   tmp_oid_clie,
                   (SELECT oid_tipo_comu
                      FROM mae_tipo_comun
                     WHERE cod_tipo_comu = lscelularentrega),
                   'L',
                   v_val_celu_entre_clie(i),
                   NULL,
                   NULL,
                   1,
                   0,
                   SYSDATE);
              
              END IF;
            
            END IF;
          END IF;
          -- Actualizar Datos en MAE_CLIEN_IDENT
          IF v_num_ruc(i) IS NULL THEN
          
            contador_mae_clien_direc := 0;
          
            SELECT COUNT(1)
              INTO contador_mae_clien_direc
              FROM mae_clien_ident
             WHERE clie_oid_clie = tmp_oid_clie
               AND tdoc_oid_tipo_docu =
                   (SELECT oid_tipo_docu
                      FROM mae_tipo_docum
                     WHERE val_sigl = 'RUC')
               AND val_iden_docu_prin = 1;
          
            IF contador_mae_clien_direc = 0 THEN
              ind_prin_dni := 1;
            ELSE
              ind_prin_dni := 0;
            END IF;
          ELSE
            UPDATE mae_clien_ident
               SET val_iden_docu_prin = 0
             WHERE tdoc_oid_tipo_docu <>
                   (SELECT oid_tipo_docu
                      FROM mae_tipo_docum
                     WHERE val_sigl = 'RUC')
               AND clie_oid_clie = tmp_oid_clie;
            ind_prin_dni := 0;
          END IF;
        
          IF (v_tip_docu(i) IS NOT NULL AND v_num_docu_iden(i) IS NOT NULL) THEN
          
            contador_mae_clien_direc := 0;
          
            BEGIN
            
              SELECT COUNT(1)
                INTO contador_mae_clien_direc
                FROM mae_clien_ident
               WHERE clie_oid_clie = tmp_oid_clie
                 AND tdoc_oid_tipo_docu =
                     (SELECT oid_tipo_docu
                        FROM mae_tipo_docum
                       WHERE cod_tipo_docu = v_tip_docu(i));
            
              IF ind_prin_dni = 1 THEN
                UPDATE mae_clien_ident
                   SET val_iden_docu_prin = 0
                 WHERE clie_oid_clie = tmp_oid_clie;
              END IF;
            
              IF (contador_mae_clien_direc = 0) THEN
              
                INSERT INTO mae_clien_ident
                  (oid_clie_iden,
                   tdoc_oid_tipo_docu,
                   clie_oid_clie,
                   num_docu_iden,
                   val_iden_docu_prin,
                   val_iden_pers_empr,
                   fec_ulti_actu)
                VALUES
                  (mae_clid_seq.nextval,
                   (SELECT oid_tipo_docu
                      FROM mae_tipo_docum
                     WHERE cod_tipo_docu = v_tip_docu(i)),
                   tmp_oid_clie,
                   v_num_docu_iden(i),
                   ind_prin_dni,
                   'P',
                   SYSDATE);
              
              END IF;
            
              IF (contador_mae_clien_direc > 0) THEN
              
                UPDATE mae_clien_ident
                   SET num_docu_iden      = v_num_docu_iden(i),
                       fec_ulti_actu      = SYSDATE,
                       val_iden_docu_prin = ind_prin_dni
                 WHERE tdoc_oid_tipo_docu =
                       (SELECT oid_tipo_docu
                          FROM mae_tipo_docum
                         WHERE cod_tipo_docu = v_tip_docu(i))
                   AND clie_oid_clie = tmp_oid_clie;
              END IF;
            
            END;
          
          END IF;
        
          IF (v_num_ruc(i) IS NOT NULL) THEN
          
            contador_mae_clien_direc := 0;
          
            SELECT COUNT(1)
              INTO contador_mae_clien_direc
              FROM mae_clien_ident
             WHERE clie_oid_clie = tmp_oid_clie
               AND tdoc_oid_tipo_docu =
                   (SELECT oid_tipo_docu
                      FROM mae_tipo_docum
                     WHERE val_sigl = 'RUC');
          
            IF contador_mae_clien_direc = 0 THEN
            
              INSERT INTO mae_clien_ident
                (oid_clie_iden,
                 tdoc_oid_tipo_docu,
                 clie_oid_clie,
                 num_docu_iden,
                 val_iden_docu_prin,
                 val_iden_pers_empr,
                 fec_ulti_actu)
              VALUES
                (mae_clid_seq.nextval,
                 (SELECT oid_tipo_docu
                    FROM mae_tipo_docum
                   WHERE val_sigl = 'RUC'),
                 tmp_oid_clie,
                 v_num_ruc(i),
                 1,
                 'P',
                 SYSDATE);
            
            ELSE
            
              UPDATE mae_clien_ident
                 SET num_docu_iden      = v_num_ruc(i),
                     fec_ulti_actu      = SYSDATE,
                     val_iden_docu_prin = 1
               WHERE tdoc_oid_tipo_docu =
                     (SELECT oid_tipo_docu
                        FROM mae_tipo_docum
                       WHERE val_sigl = 'RUC')
                 AND clie_oid_clie = tmp_oid_clie;
            
            END IF;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_sadenvio %NOTFOUND;
    END LOOP;
    CLOSE c_sadenvio;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_ENVIO_SICC: ' || ls_sqlerrm);
    
  END sto_pr_sad_envio_sicc;

  /**************************************************************************
  Descripcion       : STO_PR_GRABA_PED_REC_OID
                      Envio de informacion a Pedidos y Reclamos
  Fecha Creacion    : 27/06/2008
  Parametros Entrada:
      Codigo de Usuario   : OID de Reclamo
      psUsuario           : Codigo de Usuario
      pscodigopais        : Codigo de pais
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_pr_graba_ped_rec_oid
  (
    psoidreclamo VARCHAR2,
    psusuario    VARCHAR2,
    psinicio     NUMBER
  ) RETURN NUMBER IS
    CURSOR c_cursor IS
      SELECT r.pais_oid_pais            v_pais_oid_pais,
             r.perd_oid_peri_recl       v_perd_oid_peri,
             r.sbti_oid_subt_clie       v_sbti_oid_subt_clie,
             r.ticl_oid_tipo_clie       v_ticl_oid_tipo_clie,
             r.soca_oid_soli_cabe       v_soca_oid_docu_refe,
             r.clie_oid_clie            v_clie_oid_clie,
             ua.ztad_oid_terr_admi      v_ztad_oid_terr_admi,
             zo.oid_zona                v_zzon_oid_zona,
             ad.terr_oid_terr           v_terr_oid_terr,
             te.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop,
             op.tspa_oid_soli_con_stoc  v_tspa_oid_tipo_soli_pais_env,
             op.tspa_oid_soli_pais_gene v_tspa_oid_tipo_soli_pais_dev,
             p.almc_oid_alma            v_almc_oid_alma,
             op.ind_anul                v_ind_anul,
             e.fec_fina                 v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu     v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire          v_cldi_oid_clie_dire,
             l.timo_oid_tipo_movi       v_timo_oid_tipo_movi,
             l.val_prec                 v_val_prec,
             l.val_prec_cont            v_val_prec_cont,
             l.num_unid_recl            v_num_unid_recl,
             l.prod_oid_prod            v_prod_oid_prod,
             l.mafa_oid_matr_fact       v_mafa_oid_matr_fact,
             l.tofe_oid_tipo_ofer       v_tofe_oid_tipo_ofer,
             r.oid_cabe_recl            v_oid_cabe_recl,
             l.opre_oid_oper_recl       v_opre_oid_oper_recl,
             l.oid_line_oper_recl       v_oid_line_oper_recl,
             l.sopo_oid_soli_posi       v_sopo_oid_soli_posi,
             --l.timo_oid_tipo_movi       v_timo_oid_tipo_movi,
             p.val_nume_soli       v_val_nume_soli,
             m.tido_oid_tipo_docu  v_tido_oid_tipo_docu,
             op.ind_devu_fisi_fact v_ind_devu_fisi_fact
        FROM rec_cabec_recla       r,
             rec_opera_recla       o,
             rec_opera             op,
             rec_tipos_opera       t,
             rec_linea_opera_recla l,
             mae_clien             c,
             mae_clien_unida_admin ua,
             mae_clien_ident       ide,
             mae_clien_direc       dir,
             zon_regio             re,
             zon_zona              zo,
             zon_secci             se,
             zon_terri_admin       ad,
             zon_terri             te,
             cra_perio             e,
             ped_solic_cabec       p,
             mae_tipo_docum        m
       WHERE r.oid_cabe_recl = psoidreclamo
         AND r.clie_oid_clie = c.oid_clie
         AND r.esre_oid_esta_recl = 2
         AND dir.clie_oid_clie = c.oid_clie
         AND ua.clie_oid_clie = c.oid_clie
         AND r.oid_cabe_recl = o.care_oid_cabe_recl
         AND ad.oid_terr_admi = ua.ztad_oid_terr_admi
         AND te.oid_terr = ad.terr_oid_terr
         AND ad.zscc_oid_secc = se.oid_secc
         AND se.zzon_oid_zona = zo.oid_zona
         AND zo.zorg_oid_regi = re.oid_regi
         AND ide.clie_oid_clie = c.oid_clie
         AND t.rope_oid_oper = op.oid_oper
         AND o.tiop_oid_tipo_oper = t.oid_tipo_oper
         AND l.opre_oid_oper_recl = o.oid_oper_recl
         AND c.oid_clie = r.clie_oid_clie
         AND e.oid_peri = r.perd_oid_peri_recl
         AND ua.perd_oid_peri_fin IS NULL
         AND te.ind_borr = 0
         AND zo.ind_acti = 1
         AND ad.ind_borr = 0
         AND se.ind_acti = 1
         AND se.ind_borr = 0
         AND ide.val_iden_docu_prin = 1
         AND l.copa_oid_para_gral IS NULL -- NO RECUPERA PREMIOS
         AND p.oid_soli_cabe = r.soca_oid_soli_cabe
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
       ORDER BY r.oid_cabe_recl,
                l.opre_oid_oper_recl,
                l.timo_oid_tipo_movi;
  
    CURSOR c_cursor2 IS
      SELECT r.pais_oid_pais            v_pais_oid_pais,
             r.perd_oid_peri_recl       v_perd_oid_peri,
             r.sbti_oid_subt_clie       v_sbti_oid_subt_clie,
             r.ticl_oid_tipo_clie       v_ticl_oid_tipo_clie,
             r.soca_oid_soli_cabe       v_soca_oid_docu_refe,
             r.clie_oid_clie            v_clie_oid_clie,
             ua.ztad_oid_terr_admi      v_ztad_oid_terr_admi,
             zo.oid_zona                v_zzon_oid_zona,
             ad.terr_oid_terr           v_terr_oid_terr,
             te.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop,
             op.tspa_oid_soli_con_stoc  v_tspa_oid_tipo_soli_pais_env,
             op.tspa_oid_soli_pais_gene v_tspa_oid_tipo_soli_pais_dev,
             p.almc_oid_alma            v_almc_oid_alma,
             op.ind_anul                v_ind_anul,
             e.fec_fina                 v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu     v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire          v_cldi_oid_clie_dire,
             l.timo_oid_tipo_movi       v_timo_oid_tipo_movi,
             l.val_prec                 v_val_prec,
             l.val_prec_cont            v_val_prec_cont,
             l.num_unid_recl            v_num_unid_recl,
             l.prod_oid_prod            v_prod_oid_prod,
             l.copa_oid_para_gral       v_copa_oid_para_gene,
             l.panp_oid_para_nive_prem  v_panp_oid_para_nive_prem,
             l.lopa_oid_lote_prem_arti  v_lopa_oid_lote_prem_arti,
             r.oid_cabe_recl            v_oid_cabe_recl,
             l.opre_oid_oper_recl       v_opre_oid_oper_recl,
             l.oid_line_oper_recl       v_oid_line_oper_recl,
             l.sopo_oid_soli_posi       v_sopo_oid_soli_posi,
             --l.timo_oid_tipo_movi       v_timo_oid_tipo_movi,
             p.val_nume_soli      v_val_nume_soli,
             m.tido_oid_tipo_docu v_tido_oid_tipo_docu_org
        FROM rec_cabec_recla       r,
             rec_opera_recla       o,
             rec_opera             op,
             rec_tipos_opera       t,
             rec_linea_opera_recla l,
             mae_clien             c,
             mae_clien_unida_admin ua,
             mae_clien_ident       ide,
             mae_clien_direc       dir,
             zon_regio             re,
             zon_zona              zo,
             zon_secci             se,
             zon_terri_admin       ad,
             zon_terri             te,
             cra_perio             e,
             ped_solic_cabec       p,
             mae_tipo_docum        m
       WHERE r.oid_cabe_recl = psoidreclamo
         AND r.clie_oid_clie = c.oid_clie
         AND r.esre_oid_esta_recl = 2
         AND dir.clie_oid_clie = c.oid_clie
         AND ua.clie_oid_clie = c.oid_clie
         AND r.oid_cabe_recl = o.care_oid_cabe_recl
         AND ad.oid_terr_admi = ua.ztad_oid_terr_admi
         AND te.oid_terr = ad.terr_oid_terr
         AND ad.zscc_oid_secc = se.oid_secc
         AND se.zzon_oid_zona = zo.oid_zona
         AND zo.zorg_oid_regi = re.oid_regi
         AND ide.clie_oid_clie = c.oid_clie
         AND t.rope_oid_oper = op.oid_oper
         AND o.tiop_oid_tipo_oper = t.oid_tipo_oper
         AND l.opre_oid_oper_recl = o.oid_oper_recl
         AND c.oid_clie = r.clie_oid_clie
         AND e.oid_peri = r.perd_oid_peri_recl
         AND ua.perd_oid_peri_fin IS NULL
         AND te.ind_borr = 0
         AND zo.ind_acti = 1
         AND ad.ind_borr = 0
         AND se.ind_acti = 1
         AND se.ind_borr = 0
         AND ide.val_iden_docu_prin = 1
         AND l.copa_oid_para_gral IS NOT NULL -- RECUPERA PREMIOS
         AND p.oid_soli_cabe = r.soca_oid_soli_cabe
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
       ORDER BY r.oid_cabe_recl,
                l.opre_oid_oper_recl,
                l.timo_oid_tipo_movi,
                l.copa_oid_para_gral,
                l.panp_oid_para_nive_prem,
                l.lopa_oid_lote_prem_arti;
  
    CURSOR c_cursor3 IS
      SELECT DISTINCT a.opre_oid_oper_recl oid_oper_recl
        FROM rec_solic_opera       a,
             rec_linea_opera_recla b,
             rec_opera_recla       c
       WHERE a.opre_oid_oper_recl = b.opre_oid_oper_recl
         AND b.opre_oid_oper_recl = c.oid_oper_recl
         AND b.timo_oid_tipo_movi = 2
         AND c.care_oid_cabe_recl = psoidreclamo
       ORDER BY a.opre_oid_oper_recl;
  
    CURSOR c_cursor4 IS
      SELECT DISTINCT a.soca_oid_soli_cabe oid_soli_cabe
        FROM rec_solic_opera a,
             rec_linea_opera_recla b,
             rec_opera_recla c,
             rec_tipos_opera d,
             rec_opera e,
             (SELECT tsp.oid_tipo_soli_pais,
                     ts.cod_tipo_soli,
                     i.val_i18n
                FROM ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts,
                     v_gen_i18n_sicc     i
               WHERE i.idio_oid_idio = 1
                 AND i.attr_enti = 'PED_TIPO_SOLIC'
                 AND i.val_oid = ts.oid_tipo_soli
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND upper(val_i18n) LIKE '%REC RETORNO%') tsol
       WHERE a.opre_oid_oper_recl = b.opre_oid_oper_recl
         AND b.opre_oid_oper_recl = c.oid_oper_recl
         AND c.tiop_oid_tipo_oper = d.oid_tipo_oper
         AND d.rope_oid_oper = e.oid_oper
         AND e.ind_proc_line = 1 --- procesa en linea BR Peru
         AND b.timo_oid_tipo_movi = 2
         AND a.tspa_oid_tipo_soli_pais = tsol.oid_tipo_soli_pais
         AND c.care_oid_cabe_recl = psoidreclamo
       ORDER BY a.soca_oid_soli_cabe;
  
    CURSOR c_cursor5 IS
      SELECT DISTINCT c.oid_oper_recl oid_oper_recl,
                      f.clie_oid_clie oid_clie
        FROM rec_solic_opera a,
             rec_linea_opera_recla b,
             rec_opera_recla c,
             rec_tipos_opera d,
             rec_opera e,
             rec_cabec_recla f,
             (SELECT tsp.oid_tipo_soli_pais,
                     ts.cod_tipo_soli,
                     i.val_i18n
                FROM ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts,
                     v_gen_i18n_sicc     i
               WHERE i.idio_oid_idio = 1
                 AND i.attr_enti = 'PED_TIPO_SOLIC'
                 AND i.val_oid = ts.oid_tipo_soli
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND upper(val_i18n) LIKE '%REC RETORNO%') tsol
       WHERE a.opre_oid_oper_recl = b.opre_oid_oper_recl
         AND b.opre_oid_oper_recl = c.oid_oper_recl
         AND c.tiop_oid_tipo_oper = d.oid_tipo_oper
         AND d.rope_oid_oper = e.oid_oper
         AND c.care_oid_cabe_recl = f.oid_cabe_recl
         AND e.ind_proc_line = 0 --- procesa en linea BR Peru
         AND b.timo_oid_tipo_movi = 2
         AND a.tspa_oid_tipo_soli_pais = tsol.oid_tipo_soli_pais
         AND c.care_oid_cabe_recl = psoidreclamo
       ORDER BY c.oid_oper_recl,
                f.clie_oid_clie;
  
    TYPE registrorec IS RECORD(
      oid_pais           rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri_recl      rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_sub_clie       rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie      rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_soli_cabe      rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clien          rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_adm       mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona           zon_zona.oid_zona%TYPE,
      oid_terr           zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geo  zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_soli_con_stoc  rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_soli_pais_gene rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma           rec_opera.almc_oid_alma%TYPE,
      ind_anul           rec_opera.ind_anul%TYPE,
      fec_fina           cra_perio.fec_fina%TYPE,
      oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi1     rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec           rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont      rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl      rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod           rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_matr_fact      rec_linea_opera_recla.mafa_oid_matr_fact%TYPE,
      oid_tipo_ofer      rec_linea_opera_recla.tofe_oid_tipo_ofer%TYPE,
      oid_cabe_recl      rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl      rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi      rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      --oid_tipo_movi          rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE,
      ind_devu_fisi_fact     rec_opera.ind_devu_fisi_fact%TYPE);
    lsparametrogrpate sto_param_gener_occrr.val_param%TYPE;
  
    TYPE registrorectab IS TABLE OF registrorec;
    registrorecord registrorectab;
  
    TYPE registrorec2 IS RECORD(
      oid_pais               rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri               rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_subt_clie          rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie          rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_docu_refe          rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clie               rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_admi          mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona               zon_zona.oid_zona%TYPE,
      oid_terr               zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geop     zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_tipo_soli_pais_env rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_tipo_soli_pais_dev rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma               rec_opera.almc_oid_alma%TYPE,
      ind_anul               rec_opera.ind_anul%TYPE,
      fec_prog_fact          cra_perio.fec_fina%TYPE,
      oid_tipo_docu          mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire2         mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi2         rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec               rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont          rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl          rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod               rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_para_gene          rec_linea_opera_recla.copa_oid_para_gral%TYPE,
      oid_para_nive_prem     rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE,
      oid_lote_prem_arti     rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE,
      oid_cabe_recl          rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl          rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl     rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi          rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      --oid_tipo_movi          rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE);
  
    TYPE registrorectab2 IS TABLE OF registrorec2;
    registrorecord2 registrorectab2;
  
    TYPE registrorec3 IS RECORD(
      oid_oper_recl rec_linea_opera_recla.opre_oid_oper_recl%TYPE);
    TYPE registrorectab3 IS TABLE OF registrorec3;
    registrorecord3 registrorectab3;
  
    TYPE registrorec4 IS RECORD(
      oid_soli_cabe rec_cabec_recla.soca_oid_soli_cabe%TYPE);
    TYPE registrorectab4 IS TABLE OF registrorec4;
    registrorecord4 registrorectab4;
  
    TYPE registrorec5 IS RECORD(
      oid_oper_recl rec_opera_recla.oid_oper_recl%TYPE,
      oid_clie      rec_cabec_recla.clie_oid_clie %TYPE);
    TYPE registrorectab5 IS TABLE OF registrorec5;
    registrorecord5 registrorectab5;
  
    x                             NUMBER;
    y                             NUMBER;
    c3                            NUMBER;
    c4                            NUMBER;
    c5                            NUMBER;
    varoidoperrecl                NUMBER;
    varoidclie                    NUMBER;
    varoidsolicabe                NUMBER;
    w_filas                       NUMBER := 1000;
    lnseqsolioper                 NUMBER;
    lnseqsolicabe                 NUMBER;
    v_enviado                     NUMBER;
    v_total                       NUMBER;
    v_tspa_oid_tipo_soli_pais_xxx NUMBER;
    seq_oid_soli_cabe             NUMBER;
    v_fec_cron                    DATE;
    s_val_usua                    VARCHAR2(20);
    seq_val_nume_soli             NUMBER;
  
    oid_form_pago      ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
    ind_perm_unio_sol  ped_tipo_solic_pais.ind_perm_unio%TYPE;
    soci_oid_soci      ped_tipo_solic_pais.soci_oid_soci%TYPE;
    tspa_oid_tipo_soli ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
    tido_oid_tipo_docu ped_tipo_solic_pais.tido_oid_tipo_docu%TYPE;
    val_glos_obse      ped_tipo_solic_pais.val_glos%TYPE;
    ind_pedi_prue      ped_tipo_solic_pais.ind_pedi_prue%TYPE;
    mone_oid_mone      ped_tipo_solic_pais.mone_oid_mone%TYPE;
    acfi_oid_acce_fisi ped_tipo_solic.acce_oid_acce%TYPE;
    sbac_oid_sbac      ped_tipo_solic.sbac_oid_sbac%TYPE;
    clso_oid_clas_soli ped_tipo_solic.clso_oid_clas_soli%TYPE;
    ind_orde_comp      ped_clase_solic.ind_orde_comp%TYPE;
    ind_soli_nega      ped_tipo_solic.ind_soli_nega%TYPE;
  
    oid_tipo_prog      inc_concu_param_gener.ictp_oid_tipo_prog%TYPE;
    oid_conc_tipo_prog inc_concu_param_gener.ictp_oid_conc_tipo_prog%TYPE;
    num_prem           inc_lote_premi_artic.num_prem%TYPE;
    val_codi_vent_fict inc_artic_lote.cod_vent_fict%TYPE;
  
    seq_cod_posi      NUMBER;
    seq_oid_soli_posi NUMBER;
  
    val_codi_vent      pre_ofert_detal.val_codi_vent%TYPE;
    ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;
  
    v_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE := 0;
    v_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE := 0;
    v_val_impo_desc_unit_loca ped_solic_posic.val_impo_desc_unit_loca%TYPE := 0;
    v_val_porc_desc           ped_solic_posic.val_porc_desc%TYPE := 0;
  
    r_enviado NUMBER;
    r_total   NUMBER;
  
    lnnumsoliinicio NUMBER;
    lnnumsoliformat NUMBER;
    lnvepooid       NUMBER;
    lnvepooid2      NUMBER;
  
    nom NUMBER;
  
    oid_oper_recl_anterior rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    oid_tipo_movi_anterior rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
  
    oid_oper_recl_anterior2     rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    oid_para_gene_anterior      rec_linea_opera_recla.copa_oid_para_gral%TYPE := NULL;
    oid_para_nive_prem_anterior rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE := NULL;
    oid_lote_prem_arti_anterior rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE := NULL;
    oid_tipo_movi_anterior2     rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
  
    oid_alma ped_tipo_solic_pais.almc_oid_alma%TYPE;
  
    lsparametropunta sto_param_gener_occrr.val_param%TYPE;
    lsparametroconso sto_param_gener_occrr.val_param%TYPE;
    lsparametrobolec sto_param_gener_occrr.val_param%TYPE;
    lsparametropais  bas_ctrl_fact.cod_pais%TYPE;
    lsparametrofecha VARCHAR2(10);
  
  BEGIN
    /*
      lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                                'PED001',
                                                                '000');
    */
  
    ---- Codigo Pais
    SELECT MAX(cod_pais)
      INTO lsparametropais
      FROM bas_ctrl_fact
     WHERE rownum = 1;
  
    SELECT to_char(SYSDATE, 'dd/mm/yyyy') INTO lsparametrofecha FROM dual;
  
    ---- Parametros
    /*lsparametroconso := sto_pkg_gener.sto_fn_obten_param_ocr(lsparametropais,
    'STO_IND_CONSOL');*/
    lsparametrobolec := sto_pkg_gener.sto_fn_obten_param_ocr(lsparametropais,
                                                             'STO_IND_BOLELEC');
    lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lsparametropais,
                                                                 'STO_TIPO_CALC_FACT'),
                            '1');
    lsparametropunta := sto_pkg_gener.sto_fn_obten_param_ocr(lsparametropais,
                                                             'STO_IND_DESPUNT');
  
    lsparametrogrpate := sto_pkg_gener.sto_fn_obten_param_ocr(lsparametropais,
                                                              'STO_IND_CONSOL_GP');
  
    IF ((lsparametrogrpate <> '1' AND lsparametrogrpate <> '3') OR
       lsparametrogrpate IS NULL) THEN
      lsparametrogrpate := '1';
    END IF;
  
    lnnumsoliinicio        := psinicio;
    oid_oper_recl_anterior := NULL;
    oid_tipo_movi_anterior := NULL;
  
    SELECT to_char(SYSDATE, 'YY') || lpad(lnnumsoliinicio, 8, '0')
      INTO lnnumsoliformat
      FROM dual;
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO registrorecord LIMIT w_filas;
      IF registrorecord.count > 0 THEN
        FOR x IN registrorecord.first .. registrorecord.last
        LOOP
        
          BEGIN
            SELECT md.val_codi_vent v_val_codi_vent,
                   md.oid_deta_ofer v_ofde_oid_deta_ofer
              INTO val_codi_vent,
                   ofde_oid_deta_ofer
              FROM pre_matri_factu mf,
                   pre_ofert_detal md,
                   pre_tipo_ofert  tof
             WHERE md.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
               AND mf.ofde_oid_deta_ofer = md.oid_deta_ofer
               AND md.tofe_oid_tipo_ofer = registrorecord(x).oid_tipo_ofer
               AND mf.oid_matr_fact = registrorecord(x).oid_matr_fact
               AND md.prod_oid_prod = registrorecord(x).oid_prod;
          EXCEPTION
            WHEN OTHERS THEN
              val_codi_vent      := NULL;
              ofde_oid_deta_ofer := NULL;
          END;
        
          --- SQA 13.01.2011 Por si no encuentra el cuv
          IF val_codi_vent IS NULL THEN
            BEGIN
              SELECT c.val_codi_vent,
                     c.ofde_oid_deta_ofer
                INTO val_codi_vent,
                     ofde_oid_deta_ofer
                FROM ped_solic_posic c,
                     (SELECT c.oid_soli_posi,
                             b.modu_oid_modu,
                             c.num_unid_aten,
                             SUM(nvl(d.num_unid_recl, 0)) num_unid_recl,
                             c.num_unid_aten - SUM(nvl(d.num_unid_recl, 0)) num_unid_disp
                        FROM ped_solic_cabec       a,
                             ped_solic_cabec       b,
                             ped_solic_posic       c,
                             rec_linea_opera_recla d
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                         AND d.timo_oid_tipo_movi(+) = 2
                         AND a.val_nume_soli = registrorecord(x)
                            .val_nume_soli
                         AND c.prod_oid_prod = registrorecord(x).oid_prod
                         AND c.espo_oid_esta_posi <> 2
                       GROUP BY c.oid_soli_posi,
                                b.modu_oid_modu,
                                num_unid_aten
                       ORDER BY num_unid_disp DESC,
                                modu_oid_modu ASC) b
               WHERE c.oid_soli_posi = b.oid_soli_posi
                 AND rownum = 1
               ORDER BY c.oid_soli_posi DESC;
            EXCEPTION
              WHEN OTHERS THEN
                val_codi_vent      := NULL;
                ofde_oid_deta_ofer := NULL;
            END;
          END IF;
        
          --IF registroRecord(x).oid_oper_recl != psOidReclamo THEN
          IF nvl(oid_oper_recl_anterior, 0) || oid_tipo_movi_anterior != registrorecord(x)
            .oid_oper_recl || registrorecord(x).oid_tipo_movi1 OR
             oid_oper_recl_anterior IS NULL THEN
          
            -- x1
            IF oid_oper_recl_anterior IS NOT NULL THEN
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
            
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            -- se cambio de lugar x1
            IF registrorecord(x).oid_tipo_movi1 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                               .oid_soli_pais_gene;
            ELSE
              IF registrorecord(x).oid_tipo_movi1 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                                 .oid_soli_con_stoc;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior IS NOT NULL THEN
            
              SELECT COUNT(*)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              IF v_total = v_enviado THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior;
              END IF;
            END IF;
          
            --    Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            --    SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   --ts.acce_oid_acce       v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac      v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli v_clso_oid_clas_soli,
                   tsp.almc_oid_alma     v_oid_alma,
                   cs.ind_orde_comp      v_ind_orde_comp,
                   ts.ind_soli_nega      v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   oid_alma,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                BEGIN
                  SELECT tido_oid_tipo_docu_cont
                    INTO tido_oid_tipo_docu
                    FROM fac_tipo_docum
                   WHERE oid_tipo_docu IN
                         ((SELECT ped.tido_oid_tipo_docu
                            FROM ped_solic_cabec ped
                           WHERE ped.oid_soli_cabe IN
                                 (SELECT pos.soca_oid_soli_cabe
                                    FROM ped_solic_posic pos
                                   WHERE pos.oid_soli_posi = registrorecord(x)
                                        .oid_soli_posi)));
                  -- registrorecord(x).tido_oid_tipo_docu_org;
                EXCEPTION
                  WHEN OTHERS THEN
                    SELECT tido_oid_tipo_docu_cont
                      INTO tido_oid_tipo_docu
                      FROM fac_tipo_docum
                     WHERE oid_tipo_docu = registrorecord(x)
                          .tido_oid_tipo_docu_org;
                END;
              ELSE
                tido_oid_tipo_docu := registrorecord(x)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio := lnnumsoliinicio + 1;
            lnnumsoliformat := lnnumsoliformat + 1;
          
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT SYSDATE INTO v_fec_cron FROM dual;
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            nom := registrorecord(x).oid_cabe_recl;
          
            lnvepooid := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord(x)
                                                              .oid_pais,
                                                              registrorecord(x)
                                                              .oid_clien);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord(x).fec_fina,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               ---decode(oid_alma, NULL, 2001, oid_alma), -- duro!!!!
               decode(registrorecord(x).oid_alma,
                      NULL,
                      2001,
                      registrorecord(x).oid_alma), -- duro!!!!
               15,
               registrorecord(x).oid_tipo_clie,
               NULL,
               registrorecord(x).oid_peri_recl,
               NULL,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clie_dire,
               registrorecord(x).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord(x).oid_terr,
               registrorecord(x).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(v_fec_cron),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               NULL,
               0,
               0,
               registrorecord(x).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid, --registrorecord(x).oid_valo_estr_geo,
               NULL,
               1,
               NULL,
               lsparametrogrpate, ---- 1,
               registrorecord(x).oid_sub_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli,
               --oid_form_pago,
               nvl(nvl(oid_form_pago,
                       (SELECT fopa_oid_form_pago
                          FROM mae_clien
                         WHERE oid_clie = registrorecord(x).oid_clien)),
                   (SELECT fopa_oid_form_pago
                      FROM seg_pais
                     WHERE oid_pais = registrorecord(x).oid_pais)),
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord(x).oid_terr_adm,
               NULL,
               21,
               1,
               registrorecord(x).oid_soli_cabe,
               NULL,
               NULL,
               int_pkg_recla.gen_fn_ped_br(registrorecord(x).oid_clien), -----NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord(x).fec_fina, 'ddMMyyyy'),
               NULL,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior := registrorecord(x).oid_oper_recl;
          oid_tipo_movi_anterior := registrorecord(x).oid_tipo_movi1;
        
          IF registrorecord(x).oid_soli_posi IS NOT NULL THEN
          
            BEGIN
            
              SELECT val_prec_cata_unit_loca,
                     val_prec_fact_unit_loca,
                     val_impo_desc_unit_loca,
                     val_porc_desc
                INTO v_val_prec_cata_unit_loca,
                     v_val_prec_fact_unit_loca,
                     v_val_impo_desc_unit_loca,
                     v_val_porc_desc
                FROM ped_solic_posic p
               WHERE oid_soli_posi = registrorecord(x).oid_soli_posi;
            
            EXCEPTION
              WHEN no_data_found THEN
                v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
                v_val_prec_fact_unit_loca := registrorecord(x).val_prec;
                v_val_impo_desc_unit_loca := 0;
                v_val_porc_desc           := 0;
            END;
          
          ELSE
            v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
            v_val_prec_fact_unit_loca := registrorecord(x).val_prec;
            v_val_impo_desc_unit_loca := 0;
            v_val_porc_desc           := 0;
          END IF;
        
          IF registrorecord(x).oid_tipo_movi1 = 2 THEN
            registrorecord(x).num_unid_recl := registrorecord(x)
                                               .num_unid_recl * (-1);
          ELSE
            v_val_prec_fact_unit_loca := 0;
          END IF;
        
          IF ((registrorecord(x).ind_devu_fisi_fact = 1) OR
             (((registrorecord(x).oid_tipo_movi1 = 2) AND
             (registrorecord(x).oid_soli_posi IS NOT NULL) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)) OR
             ((registrorecord(x).oid_tipo_movi1 = 1) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)))) THEN
          
            SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual; --secuencial de PED_SOLIC_POSIC
          
            seq_cod_posi := seq_cod_posi + 1;
          
            INSERT INTO ped_solic_posic
              (oid_soli_posi,
               cod_posi,
               num_unid_dema,
               num_unid_por_aten,
               val_tasa_impu,
               soca_oid_soli_cabe,
               tpos_oid_tipo_posi,
               prod_oid_prod,
               fopa_oid_form_pago,
               val_prec_cata_unit_loca,
               val_prec_cont_unit_loca,
               val_prec_cata_unit_docu,
               val_prec_conta_unit_docu,
               val_prec_fact_unit_loca,
               val_prec_fact_unit_docu,
               val_prec_sin_impu_unit_loca,
               val_prec_sin_impu_unit_docu,
               val_prec_sin_impu_tota_docu,
               val_impo_desc_unit_loca,
               val_prec_neto_unit_loca,
               val_prec_neto_tota_docu,
               val_prec_neto_unit_docu,
               val_prec_tota_tota_loca,
               val_prec_tota_tota_docu,
               val_impo_impu_unit_loca,
               val_impo_impu_unit_docu,
               val_impo_desc_tota_docu,
               val_impo_impu_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_desc_tota_loca,
               val_prec_tota_unit_loca,
               val_prec_tota_unit_docu,
               val_prec_cont_tota_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_tota_docu,
               val_prec_cont_tota_docu,
               val_prec_cata_tota_loca_unid,
               num_unid_dema_real,
               num_unid_compr,
               val_prec_fact_tota_loca,
               val_prec_fact_tota_docu,
               val_prec_sin_impu_tota_loca,
               val_prec_neto_tota_loca,
               ofde_oid_deta_ofer,
               espo_oid_esta_posi,
               stpo_oid_subt_posi,
               val_codi_vent,
               ind_no_impr,
               val_codi_vent_fict,
               val_porc_desc,
               oid_line_oper_recl)
            VALUES
              (seq_oid_soli_posi,
               seq_cod_posi,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               seq_oid_soli_cabe,
               10,
               registrorecord(x).oid_prod,
               NULL,
               v_val_prec_cata_unit_loca, -- registrorecord(x).val_prec, -- poner precio catalogo
               --- sqa registrorecord(x).val_prec_cont,
               decode(v_val_prec_cata_unit_loca,
                      0,
                      nvl(registrorecord(x).val_prec_cont, 0),
                      0),
               0,
               0,
               v_val_prec_fact_unit_loca, --- 0,
               0,
               0,
               0,
               0,
               v_val_impo_desc_unit_loca, -- 0, -- poner monto descuento
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               0,
               0,
               0,
               ofde_oid_deta_ofer,
               4,
               1,
               val_codi_vent,
               NULL,
               NULL,
               v_val_porc_desc, -- 0 -- poner porcentaje de descuento
               registrorecord(x).oid_line_oper_recl);
          
          END IF;
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord(x)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;
  
    IF oid_oper_recl_anterior IS NOT NULL THEN
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
      --SELECT PED_SOCA_SEQ INTO lnseqsolicabe FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      SELECT COUNT(1)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      IF v_total = v_enviado THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2
         WHERE oid_oper_recl = oid_oper_recl_anterior;
      END IF;
    END IF;
  
    OPEN c_cursor2;
    LOOP
      FETCH c_cursor2 BULK COLLECT
        INTO registrorecord2 LIMIT w_filas;
      IF registrorecord2.count > 0 THEN
        FOR y IN registrorecord2.first .. registrorecord2.last
        LOOP
          BEGIN
            /*SELECT cpg.ictp_oid_tipo_prog      v_ictp_oid_tipo_prog,
                   cpg.ictp_oid_conc_tipo_prog v_ictp_oid_conc_tipo_prog,
                   la.num_prem                 v_num_prem,
                   al.cod_vent_fict            v_val_codi_vent_fict
              INTO oid_tipo_prog,
                   oid_conc_tipo_prog,
                   num_prem,
                   val_codi_vent_fict
              FROM mae_produ             mp,
                   inc_artic_lote        al,
                   inc_lote_premi_artic  la,
                   inc_premi_artic       pa,
                   inc_param_nivel_premi pn,
                   inc_param_gener_premi pg,
                   inc_concu_param_gener cpg
             WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
               AND al.prod_oid_prod = mp.oid_prod
               AND la.prar_oid_prem_arti = pa.oid_prem_arti
               AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
               AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
               AND pg.copa_oid_para_gral = cpg.oid_para_gral
               AND cpg.oid_para_gral = registrorecord2(y)
            .oid_para_gene
               AND pa.panp_oid_para_nive_prem = registrorecord2(y)
            .oid_para_nive_prem
               AND al.lopa_oid_lote_prem_arti = registrorecord2(y)
            .oid_lote_prem_arti
               AND al.prod_oid_prod = registrorecord2(y).oid_prod;*/
            /* 30/06/2010 modificacion de query     */
            SELECT v_ictp_oid_tipo_prog,
                   v_ictp_oid_conc_tipo_prog,
                   v_num_prem,
                   v_val_codi_vent_fict
              INTO oid_tipo_prog,
                   oid_conc_tipo_prog,
                   num_prem,
                   val_codi_vent_fict
              FROM (SELECT cpg.ictp_oid_tipo_prog      v_ictp_oid_tipo_prog,
                           cpg.ictp_oid_conc_tipo_prog v_ictp_oid_conc_tipo_prog,
                           la.num_prem                 v_num_prem,
                           al.cod_vent_fict            v_val_codi_vent_fict
                      FROM mae_produ             mp,
                           inc_artic_lote        al,
                           inc_lote_premi_artic  la,
                           inc_premi_artic       pa,
                           inc_param_nivel_premi pn,
                           inc_param_gener_premi pg,
                           inc_concu_param_gener cpg
                     WHERE al.lopa_oid_lote_prem_arti =
                           la.oid_lote_prem_arti
                       AND al.prod_oid_prod = mp.oid_prod
                       AND la.prar_oid_prem_arti = pa.oid_prem_arti
                       AND pa.panp_oid_para_nive_prem =
                           pn.oid_para_nive_prem
                       AND pn.pagp_oid_para_gene_prem =
                           pg.oid_para_gene_prem
                       AND pg.copa_oid_para_gral = cpg.oid_para_gral
                       AND cpg.oid_para_gral = registrorecord2(y)
                          .oid_para_gene
                       AND pa.panp_oid_para_nive_prem = registrorecord2(y)
                          .oid_para_nive_prem
                       AND al.lopa_oid_lote_prem_arti = registrorecord2(y)
                          .oid_lote_prem_arti
                       AND al.prod_oid_prod = registrorecord2(y).oid_prod
                    UNION
                    SELECT cpg.ictp_oid_tipo_prog      v_ictp_oid_tipo_prog,
                           cpg.ictp_oid_conc_tipo_prog v_ictp_oid_conc_tipo_prog,
                           la.num_prem                 v_num_prem,
                           dd.cod_vent_fict            v_val_codi_vent_fict
                      FROM mae_produ             mp,
                           inc_artic_lote        al,
                           inc_lote_premi_artic  la,
                           inc_premi_artic       pa,
                           inc_param_nivel_premi pn,
                           inc_param_gener_premi pg,
                           inc_concu_param_gener cpg,
                           inc_reemp_artic_lote  dd
                     WHERE al.lopa_oid_lote_prem_arti =
                           la.oid_lote_prem_arti
                       AND al.prod_oid_prod = mp.oid_prod
                       AND la.prar_oid_prem_arti = pa.oid_prem_arti
                       AND pa.panp_oid_para_nive_prem =
                           pn.oid_para_nive_prem
                       AND pn.pagp_oid_para_gene_prem =
                           pg.oid_para_gene_prem
                       AND pg.copa_oid_para_gral = cpg.oid_para_gral
                       AND cpg.oid_para_gral = registrorecord2(y)
                          .oid_para_gene
                       AND pa.panp_oid_para_nive_prem = registrorecord2(y)
                          .oid_para_nive_prem
                       AND al.lopa_oid_lote_prem_arti = registrorecord2(y)
                          .oid_lote_prem_arti
                       AND dd.prod_oid_prod = registrorecord2(y).oid_prod
                       AND dd.arlo_oid_arti_lote = al.oid_arti_lote)
             WHERE rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              oid_tipo_prog      := NULL;
              oid_conc_tipo_prog := NULL;
              num_prem           := NULL;
              val_codi_vent_fict := NULL;
          END;
        
          IF nvl(oid_oper_recl_anterior2, 0) || oid_para_gene_anterior ||
             oid_para_nive_prem_anterior || oid_lote_prem_arti_anterior ||
             oid_tipo_movi_anterior2 != registrorecord2(y)
            .oid_oper_recl || registrorecord2(y).oid_para_gene || registrorecord2(y)
            .oid_para_nive_prem || registrorecord2(y).oid_lote_prem_arti || registrorecord2(y)
            .oid_tipo_movi2 OR oid_oper_recl_anterior2 IS NULL THEN
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
            
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior2,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                               .oid_tipo_soli_pais_dev;
            ELSE
              IF registrorecord2(y).oid_tipo_movi2 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                                 .oid_tipo_soli_pais_env;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT COUNT(1)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
                 AND ind_esta = 'E';
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
            
              IF v_enviado = v_total THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior2;
              
              END IF;
            END IF;
          
            --   Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac       v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
                   cs.ind_orde_comp       v_ind_orde_comp,
                   ts.ind_soli_nega       v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                SELECT tido_oid_tipo_docu_cont
                  INTO tido_oid_tipo_docu
                  FROM fac_tipo_docum
                 WHERE oid_tipo_docu IN
                       ((SELECT ped.tido_oid_tipo_docu
                          FROM ped_solic_cabec ped
                         WHERE ped.oid_soli_cabe IN
                               (SELECT pos.soca_oid_soli_cabe
                                  FROM ped_solic_posic pos
                                 WHERE pos.oid_soli_posi = registrorecord2(y)
                                      .oid_soli_posi)));
              ELSE
                tido_oid_tipo_docu := registrorecord2(y)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio   := lnnumsoliinicio + 1;
            lnnumsoliformat   := lnnumsoliformat + 1;
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            lnvepooid2 := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord2(y)
                                                               .oid_pais,
                                                               registrorecord2(y)
                                                               .oid_clie);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord2(y).fec_prog_fact,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               decode(registrorecord2(y).oid_tipo_movi2,
                      1,
                      2001,
                      registrorecord2(y).oid_alma),
               15,
               registrorecord2(y).oid_tipo_clie,
               NULL,
               registrorecord2(y).oid_peri,
               NULL,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie_dire2,
               registrorecord2(y).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord2(y).oid_terr,
               registrorecord2(y).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(SYSDATE),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               num_prem,
               0,
               0,
               registrorecord2(y).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid2, --registrorecord2(y).oid_valo_estr_geop,
               NULL,
               1,
               registrorecord2(y).oid_para_gene,
               lsparametrogrpate, ---- 1,
               registrorecord2(y).oid_subt_clie,
               1,
               tspa_oid_tipo_soli,
               oid_form_pago,
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord2(y).oid_terr_admi,
               NULL,
               21,
               1,
               registrorecord2(y).oid_docu_refe,
               NULL,
               NULL,
               int_pkg_recla.gen_fn_ped_br(registrorecord2(y).oid_clie), ---- NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord2(y).fec_prog_fact, 'yyyyMMdd'),
               oid_tipo_prog,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior2     := registrorecord2(y).oid_oper_recl;
          oid_para_gene_anterior      := registrorecord2(y).oid_para_gene;
          oid_para_nive_prem_anterior := registrorecord2(y)
                                         .oid_para_nive_prem;
          oid_lote_prem_arti_anterior := registrorecord2(y)
                                         .oid_lote_prem_arti;
          oid_tipo_movi_anterior2     := registrorecord2(y).oid_tipo_movi2;
        
          v_val_prec_fact_unit_loca := registrorecord2(y).val_prec;
        
          IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
            registrorecord2(y).num_unid_recl := registrorecord2(y)
                                                .num_unid_recl * (-1);
          ELSE
            v_val_prec_fact_unit_loca := 0;
          END IF;
        
          SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual;
          seq_cod_posi := seq_cod_posi + 1;
        
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr,
             val_codi_vent_fict,
             oid_line_oper_recl)
          VALUES
            (seq_oid_soli_posi,
             seq_cod_posi,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             lnseqsolicabe,
             10,
             registrorecord2(y).oid_prod,
             NULL,
             registrorecord2(y).val_prec,
             --- SQA registrorecord2(y).val_prec_cont,
             decode(registrorecord2(y).val_prec,
                    0,
                    nvl(registrorecord2(y).val_prec_cont, 0),
                    0),
             0,
             0,
             v_val_prec_fact_unit_loca, ----0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             0,
             0,
             0,
             NULL,
             4,
             14,
             NULL,
             NULL,
             val_codi_vent_fict,
             registrorecord2(y).oid_line_oper_recl);
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord2(y)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor2%NOTFOUND;
    END LOOP;
    CLOSE c_cursor2;
  
    IF oid_oper_recl_anterior2 IS NOT NULL THEN
    
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior2,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
         AND ind_esta = 'E';
    
      SELECT COUNT(*)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
    
      IF v_enviado = v_total THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2
         WHERE oid_oper_recl = oid_oper_recl_anterior2;
      
      END IF;
    END IF;
  
    SELECT COUNT(1)
      INTO r_enviado
      FROM rec_opera_recla o
     WHERE o.care_oid_cabe_recl = psoidreclamo
       AND o.esop_oid_esta_oper = 2;
  
    SELECT COUNT(1)
      INTO r_total
      FROM rec_opera_recla
     WHERE care_oid_cabe_recl = psoidreclamo;
  
    IF r_total = r_enviado THEN
      UPDATE rec_cabec_recla
         SET esre_oid_esta_recl = 6,
             fec_ulti_actu     =
             (SELECT SYSDATE FROM dual)
       WHERE oid_cabe_recl = psoidreclamo;
    END IF;
  
    ---- Marca las las Boletas Electronicas
    IF lsparametrobolec = '1' THEN
      OPEN c_cursor3;
      LOOP
        FETCH c_cursor3 BULK COLLECT
          INTO registrorecord3 LIMIT w_filas;
        IF registrorecord3.count > 0 THEN
          FOR c3 IN registrorecord3.first .. registrorecord3.last
          LOOP
            varoidoperrecl := registrorecord3(c3).oid_oper_recl;
          
            sto_pkg_envio_valid_sicc.sto_pr_actua_boleta_elect(varoidoperrecl);
          
          END LOOP;
        END IF;
        EXIT WHEN c_cursor3%NOTFOUND;
      END LOOP;
      CLOSE c_cursor3;
    END IF;
  
    ---- Genera Consolidados
    ---IF (lsparametroconso = '1' OR lsparametroconso = '2') THEN
    IF lsparametroconso IS NOT NULL THEN
      OPEN c_cursor4;
      LOOP
        FETCH c_cursor4 BULK COLLECT
          INTO registrorecord4 LIMIT w_filas;
        IF registrorecord4.count > 0 THEN
          FOR c4 IN registrorecord4.first .. registrorecord4.last
          LOOP
            varoidsolicabe := registrorecord4(c4).oid_soli_cabe;
          
            ---insert into BAS_TMP_REPOR_LOG (nom_repo,des_log) values(varoidsolicabe,'graba_ped_rec_oid varoidsolicabe');
          
            sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(varoidsolicabe,
                                                               lsparametrofecha,
                                                               lsparametropais,
                                                               lsparametroconso,
                                                               'A');
          
            --- Actualizar estado del rec_opera_recla
            SELECT COUNT(0)
              INTO r_enviado
              FROM rec_solic_opera a,
                   ped_solic_cabec b,
                   rec_solic_opera c
             WHERE a.opre_oid_oper_recl = c.opre_oid_oper_recl
               AND c.soca_oid_soli_cabe = b.oid_soli_cabe
               AND a.soca_oid_soli_cabe = varoidsolicabe
               AND b.fec_fact IS NULL;
          
            IF r_enviado = 0 THEN
            
              UPDATE rec_opera_recla b
                 SET b.esop_oid_esta_oper = 5
               WHERE b.oid_oper_recl IN
                     (SELECT opre_oid_oper_recl
                        FROM rec_solic_opera a
                       WHERE a.soca_oid_soli_cabe = varoidsolicabe);
            
            END IF;
          
            --- Descuenta puntaje
            IF lsparametropunta = '1' THEN
              inc_pkg_proce_incen.inc_pr_calcu_punta_consu(lsparametropais,
                                                           varoidsolicabe,
                                                           psusuario);
            END IF;
          
          END LOOP;
        END IF;
        EXIT WHEN c_cursor4%NOTFOUND;
      END LOOP;
      CLOSE c_cursor4;
    
      ---- Actualiza estado en rec_cabec_recla si todas fueron facturadas
      SELECT COUNT(1)
        INTO r_enviado
        FROM rec_opera_recla o
       WHERE o.care_oid_cabe_recl = psoidreclamo
         AND o.esop_oid_esta_oper = 5;
    
      SELECT COUNT(1)
        INTO r_total
        FROM rec_opera_recla
       WHERE care_oid_cabe_recl = psoidreclamo;
    
      IF r_total = r_enviado THEN
        UPDATE rec_cabec_recla
           SET esre_oid_esta_recl = 4,
               fec_ulti_actu     =
               (SELECT SYSDATE FROM dual)
         WHERE oid_cabe_recl = psoidreclamo;
      END IF;
    
    END IF;
  
    ---- Procesa los que no son aplicacion en linea
    IF lsparametroconso IS NOT NULL THEN
      varoidclie := 0;
    
      OPEN c_cursor5;
      LOOP
        FETCH c_cursor5 BULK COLLECT
          INTO registrorecord5 LIMIT w_filas;
        IF registrorecord5.count > 0 THEN
          FOR c5 IN registrorecord5.first .. registrorecord5.last
          LOOP
            varoidoperrecl := registrorecord5(c5).oid_oper_recl;
            varoidclie     := registrorecord5(c5).oid_clie;
          
            --- Actualizar estado del rec_opera_recla
            UPDATE rec_opera_recla b
               SET b.ind_abon_pdte = 1
             WHERE b.oid_oper_recl = varoidoperrecl;
          
          END LOOP;
        END IF;
        EXIT WHEN c_cursor5%NOTFOUND;
      END LOOP;
      CLOSE c_cursor5;
    
      --- Actualizar estado del rec_cabec_recla
      UPDATE rec_cabec_recla
         SET ind_abon_pdte = 1
       WHERE oid_cabe_recl = psoidreclamo;
    
      --- Actualizar saldo
      UPDATE mae_clien
         SET val_recl_pend = rec_pkg_proce.rec_fn_abono_pendi_consu(varoidclie,
                                                                    NULL)
       WHERE oid_clie = varoidclie;
    
    END IF;
  
    RETURN lnnumsoliinicio;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GRABA_PED_REC_OID: ' ||
                              ls_sqlerrm || ' ' || nom);
  END sto_pr_graba_ped_rec_oid;

  /**************************************************************************
  Descripcion       : STO_PR_GRABA_PED_REC
                      Envio de informacion a Pedidos y Reclamos
  Fecha Creacion    : 27/06/2008
  Parametros Entrada:
      psUsuario           : Codigo de Usuario
      pscodigopais        : Codigo de pais
  
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_graba_ped_rec
  (
    psusuario    VARCHAR2,
    pscodigopais VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT rec.oid_cabe_recl
        FROM rec_cabec_recla rec
       WHERE rec.esre_oid_esta_recl = '2'
         AND rec.mobl_oid_moti_bloq IS NULL;
  
    TYPE registrorec IS RECORD(
      oid_reclamo rec_cabec_recla.oid_cabe_recl%TYPE);
  
    TYPE registrorectab IS TABLE OF registrorec;
    registrorecord      registrorectab;
    nom                 NUMBER;
    x                   NUMBER;
    w_filas             NUMBER := 1000;
    var                 NUMBER;
    lsfacturareferencia sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lsfacturareferencia := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_CDR_FACT');
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO registrorecord LIMIT w_filas;
      IF registrorecord.count > 0 THEN
        FOR x IN registrorecord.first .. registrorecord.last
        LOOP
          IF (lsfacturareferencia = '0') THEN
            nom := registrorecord(x).oid_reclamo;
            var := sto_pkg_envio_valid_sicc.sto_pr_graba_ped_rec_oid(registrorecord(x)
                                                                     .oid_reclamo,
                                                                     psusuario,
                                                                     1);
          ELSE
            nom := registrorecord(x).oid_reclamo;
            var := sto_pkg_envio_valid_sicc.sto_pr_graba_fac_rec_oid(registrorecord(x)
                                                                     .oid_reclamo,
                                                                     psusuario,
                                                                     1);
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_graba_ped_rec: ' || ls_sqlerrm || ' ' || nom);
  END sto_pr_graba_ped_rec;
  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_ENVIO_SICC
                    Envio de DYCZ a SICC
  Fecha Creacion    : 05/02/2009
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_envioscc IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_pais,
             cons.fec_proc,
             cons.fec_naci,
             cons.oid_peri,
             cons.val_mail_clie,
             cons.val_ape1,
             cons.val_ape2,
             cons.val_nom1,
             cons.val_nom2,
             cons.val_telf_clie,
             cons.val_celu_clie,
             cons.ind_dupl_nuev,
             cons.ind_actu_dato,
             --
             cons.oid_clie_madr
      
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codpais IS TABLE OF int_solic_conso_dupla_cyzon.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_dupla_cyzon.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_dupla_cyzon.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_codtipodoc IS TABLE OF int_solic_conso_dupla_cyzon.docu_cod_tipo_docu%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_oidpais IS TABLE OF int_solic_conso_dupla_cyzon.oid_pais%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_solic_conso_dupla_cyzon.fec_proc%TYPE;
    TYPE t_fechanacimiento IS TABLE OF int_solic_conso_dupla_cyzon.fec_naci%TYPE;
    TYPE t_oidperiodo IS TABLE OF int_solic_conso_dupla_cyzon.oid_peri%TYPE;
    TYPE t_valormail IS TABLE OF int_solic_conso_dupla_cyzon.val_mail_clie%TYPE;
    TYPE t_apell1cliente IS TABLE OF int_solic_conso_dupla_cyzon.val_ape1%TYPE;
    TYPE t_apell2cliente IS TABLE OF int_solic_conso_dupla_cyzon.val_ape2%TYPE;
    TYPE t_nom1cliente IS TABLE OF int_solic_conso_dupla_cyzon.val_nom1%TYPE;
    TYPE t_nom2cliente IS TABLE OF int_solic_conso_dupla_cyzon.val_nom2%TYPE;
    TYPE t_telfcliente IS TABLE OF int_solic_conso_dupla_cyzon.val_telf_clie%TYPE;
    TYPE t_celcliente IS TABLE OF int_solic_conso_dupla_cyzon.val_celu_clie%TYPE;
    TYPE t_ind_dupl_nuev IS TABLE OF int_solic_conso_dupla_cyzon.ind_dupl_nuev%TYPE;
    TYPE t_ind_actu_dato IS TABLE OF int_solic_conso_dupla_cyzon.ind_actu_dato%TYPE;
    --
    TYPE t_oid_clie_madr IS TABLE OF int_solic_conso_dupla_cyzon.oid_clie_madr%TYPE;
  
    v_codpais         t_codpais;
    v_codperi         t_codperi;
    v_codclie         t_codclie;
    v_numlote         t_numlote;
    v_codtipodoc      t_codtipodoc;
    v_secnumdocu      t_secnumdocu;
    v_oidpais         t_oidpais;
    v_fechaproceso    t_fechaproceso;
    v_fechanacimiento t_fechanacimiento;
  
    v_oidperiodo    t_oidperiodo;
    v_valormail     t_valormail;
    v_apell1cliente t_apell1cliente;
    v_apell2cliente t_apell2cliente;
    v_nom1cliente   t_nom1cliente;
    v_nom2cliente   t_nom2cliente;
    v_telfcliente   t_telfcliente;
    v_celcliente    t_celcliente;
    v_ind_dupl_nuev t_ind_dupl_nuev;
    v_ind_actu_dato t_ind_actu_dato;
    --
    v_oid_clie_madr t_oid_clie_madr;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    contador_mae_clien NUMBER := 0;
  
    lstmp_oid_clie mae_clien.oid_clie%TYPE;
  
    lsoidvinculodupla          sto_param_gener_occrr.val_param%TYPE;
    lscodigomensajeinscripcion sto_param_gener_occrr.val_param%TYPE;
    lscodigomensajerechazo     sto_param_gener_occrr.val_param%TYPE;
    ------
    lsoidsubtipo           mae_tipo_clasi_clien.sbti_oid_subt_clie%TYPE;
    lsoidtipoclasificacion mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    lsoidclasificacion     mae_clasi.oid_clas%TYPE;
  
  BEGIN
  
    lsoidvinculodupla := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_OID_VINCULO_DUPL');
  
    lscodigomensajeinscripcion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_COD_MENS_INSCRIP');
  
    lscodigomensajerechazo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_COD_MENS_RECHAZO');
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
    OPEN c_envioscc;
    LOOP
      FETCH c_envioscc BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_codtipodoc,
             v_secnumdocu,
             v_oidpais,
             v_fechaproceso,
             v_fechanacimiento,
             v_oidperiodo,
             v_valormail,
             v_apell1cliente,
             v_apell2cliente,
             v_nom1cliente,
             v_nom2cliente,
             v_telfcliente,
             v_celcliente,
             v_ind_dupl_nuev,
             v_ind_actu_dato,
             --
             v_oid_clie_madr LIMIT rows;
    
      IF v_codpais.count > 0 THEN
      
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
        
          IF (v_ind_dupl_nuev(i) = 'S') THEN
          
            SELECT mae_clie_seq.nextval INTO lstmp_oid_clie FROM dual;
          
            UPDATE int_solic_conso_dupla_cyzon a
               SET a.cod_clie_dupl = 'DP' || lstmp_oid_clie
             WHERE a.cod_pais = v_codpais(i)
               AND a.cod_clie = v_codclie(i)
               AND a.num_lote = v_numlote(i)
               AND a.sec_nume_docu = v_secnumdocu(i);
          
            --Insercion en MAE_CLIEN
          
            INSERT INTO mae_clien
              (oid_clie,
               cod_clie,
               ind_fich_insc,
               pais_oid_pais,
               cod_digi_ctrl,
               val_ape1,
               val_ape2,
               val_nom1,
               val_nom2,
               val_trat,
               val_crit_bus1,
               val_crit_bus2,
               cod_sexo,
               fec_ingr,
               fopa_oid_form_pago,
               val_apel_casa,
               fec_crea,
               fec_ulti_actu,
               fec_ulti_gene_cupo)
            VALUES
              (lstmp_oid_clie,
               'DP' || lstmp_oid_clie,
               0,
               v_oidpais(i),
               NULL,
               v_apell1cliente(i),
               v_apell2cliente(i),
               v_nom1cliente(i),
               v_nom2cliente(i),
               '3',
               v_apell1cliente(i),
               v_nom1cliente(i),
               'F',
               to_date(v_fechaproceso(i), 'DD/MM/YY'),
               NULL,
               NULL,
               v_fechaproceso(i),
               v_fechaproceso(i),
               v_fechaproceso(i));
          
            --Insercion en MAE_CLIEN_DATOS_ADICI
          
            INSERT INTO mae_clien_datos_adici
              (clie_oid_clie,
               nsep_oid_nsep,
               oid_clie_dato_adic,
               cod_empl,
               fec_naci,
               val_edad,
               val_ocup,
               val_prof,
               val_cent_trab,
               val_carg_dese,
               val_cent_estu,
               val_nive_soci_eco3,
               num_hijo,
               num_pers_depe,
               num_camp_sin_pedi,
               imp_ingr_fami,
               imp_mont_line_cred,
               niri_oid_nive_ries,
               nied_oid_nive_estu,
               snon_oid_naci,
               escv_oid_esta_civi,
               perd_oid_peri_nive_ries,
               perd_oid_peri_line_cred,
               ind_corr,
               ind_acti,
               esta_oid_esta_clie,
               tclv_oid_cicl_vida,
               val_emai,
               fec_ulti_actu)
            VALUES
              (lstmp_oid_clie,
               NULL,
               mae_clda_seq.nextval,
               NULL,
               trunc(v_fechanacimiento(i)),
               floor((months_between(SYSDATE, v_fechanacimiento(i)) / 12)),
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               0,
               NULL,
               0,
               4,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               1,
               1,
               1,
               NULL,
               substr(v_valormail(i), 1, 40),
               SYSDATE);
          
          END IF;
        
          IF (v_ind_actu_dato(i) = 'S') THEN
          
            BEGIN
              SELECT clie_oid_clie_vndo
                INTO lstmp_oid_clie
                FROM mae_clien_vincu
               WHERE clie_oid_clie_vnte =
                     (SELECT oid_clie
                        FROM mae_clien
                       WHERE cod_clie = v_codclie(i))
                 AND tivc_oid_tipo_vinc = to_number(lsoidvinculodupla)
                 AND fec_hast IS NULL;
            
            EXCEPTION
              WHEN no_data_found THEN
                lstmp_oid_clie := NULL;
              
            END;
          
            UPDATE mae_clien a
               SET a.val_ape1 = decode(v_apell1cliente(i),
                                       NULL,
                                       a.val_ape1,
                                       v_apell1cliente(i)),
                   a.val_ape2 = decode(v_apell2cliente(i),
                                       NULL,
                                       a.val_ape2,
                                       v_apell2cliente(i)),
                   a.val_nom1 = decode(v_nom1cliente(i),
                                       NULL,
                                       a.val_nom1,
                                       v_nom1cliente(i)),
                   a.val_nom2 = decode(v_nom2cliente(i),
                                       NULL,
                                       a.val_nom2,
                                       v_nom2cliente(i))
             WHERE a.oid_clie = lstmp_oid_clie;
          
            --MAE_CLIEN_DATOS_ADICI
          
            --IF(v_valormail(i) IS NOT NULL) THEN
          
            --UPDATE MAE_CLIEN_DATOS_ADICI
            --SET VAL_EMAI=v_valormail(i)
            --WHERE CLIE_OID_CLIE = lstmp_oid_clie;
          
            --END IF;
          
          END IF;
        
          --Insercion en MAE_CLIEN_COMUN
        
          IF (v_telfcliente(i) IS NOT NULL) THEN
          
            BEGIN
              contador_mae_clien := 0;
            
              SELECT COUNT(*)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE clie_oid_clie = lstmp_oid_clie
                 AND ticm_oid_tipo_comu = 1;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET ind_comu_ppal = 0
                 WHERE clie_oid_clie = lstmp_oid_clie;
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_telfcliente(i),
                       ind_comu_ppal = 1
                 WHERE ticm_oid_tipo_comu = 1
                   AND clie_oid_clie = lstmp_oid_clie;
              
              ELSE
              
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   lstmp_oid_clie,
                   1,
                   'L',
                   v_telfcliente(i),
                   NULL,
                   NULL,
                   0,
                   decode((SELECT nvl(SUM(ind_comu_ppal), 0)
                            FROM mae_clien_comun
                           WHERE clie_oid_clie = lstmp_oid_clie),
                          0,
                          1,
                          0),
                   SYSDATE);
              
              END IF;
            
            END;
          END IF;
        
          IF (v_celcliente(i) IS NOT NULL) THEN
          
            BEGIN
              contador_mae_clien := 0;
            
              SELECT COUNT(*)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE clie_oid_clie = lstmp_oid_clie
                 AND ticm_oid_tipo_comu = 6;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET ind_comu_ppal = 0
                 WHERE clie_oid_clie = lstmp_oid_clie
                   AND ticm_oid_tipo_comu <> 1;
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_celcliente(i),
                       ind_comu_ppal = decode((SELECT nvl(SUM(ind_comu_ppal),
                                                         0)
                                                FROM mae_clien_comun
                                               WHERE clie_oid_clie =
                                                     lstmp_oid_clie),
                                              0,
                                              1,
                                              0)
                 WHERE ticm_oid_tipo_comu = 6
                   AND clie_oid_clie = lstmp_oid_clie;
              
              ELSE
              
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   lstmp_oid_clie,
                   6,
                   'L',
                   v_celcliente(i),
                   NULL,
                   NULL,
                   0,
                   decode((SELECT nvl(SUM(ind_comu_ppal), 0)
                            FROM mae_clien_comun
                           WHERE clie_oid_clie = lstmp_oid_clie),
                          0,
                          1,
                          0),
                   SYSDATE);
              
              END IF;
            
            END;
          
          END IF;
        
          IF (v_valormail(i) IS NOT NULL) THEN
          
            BEGIN
              contador_mae_clien := 0;
            
              SELECT COUNT(*)
                INTO contador_mae_clien
                FROM mae_clien_comun
               WHERE clie_oid_clie = lstmp_oid_clie
                 AND ticm_oid_tipo_comu = 3;
            
              IF (contador_mae_clien > 0) THEN
              
                UPDATE mae_clien_comun
                   SET ind_comu_ppal = 0
                 WHERE clie_oid_clie = lstmp_oid_clie
                   AND ticm_oid_tipo_comu NOT IN (1, 6);
              
                UPDATE mae_clien_comun
                   SET val_text_comu = v_valormail(i),
                       ind_comu_ppal = decode((SELECT nvl(SUM(ind_comu_ppal),
                                                         0)
                                                FROM mae_clien_comun
                                               WHERE clie_oid_clie =
                                                     lstmp_oid_clie),
                                              0,
                                              1,
                                              0)
                 WHERE ticm_oid_tipo_comu = 3
                   AND clie_oid_clie = lstmp_oid_clie;
              
              ELSE
              
                INSERT INTO mae_clien_comun
                  (oid_clie_comu,
                   clie_oid_clie,
                   ticm_oid_tipo_comu,
                   val_dia_comu,
                   val_text_comu,
                   fec_hora_desd,
                   fec_hora_hast,
                   val_inte_comu,
                   ind_comu_ppal,
                   fec_ulti_actu)
                VALUES
                  (mae_clco_seq.nextval,
                   lstmp_oid_clie,
                   3,
                   'L',
                   v_valormail(i),
                   NULL,
                   NULL,
                   0,
                   decode((SELECT nvl(SUM(ind_comu_ppal), 0)
                            FROM mae_clien_comun
                           WHERE clie_oid_clie = lstmp_oid_clie),
                          0,
                          1,
                          0),
                   SYSDATE);
              
              END IF;
            
            END;
          END IF;
        
          --Insercion en MAE_CLIEN_VINCU
        
          IF (v_ind_dupl_nuev(i) = 'S') THEN
          
            DELETE FROM mae_clien_vincu
             WHERE clie_oid_clie_vnte =
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
               AND tivc_oid_tipo_vinc = to_number(lsoidvinculodupla);
          
            INSERT INTO mae_clien_vincu
              (oid_clie_vinc,
               fec_desd,
               fec_hast,
               clie_oid_clie_vnte,
               clie_oid_clie_vndo,
               tivc_oid_tipo_vinc,
               ind_vinc_ppal,
               fec_ulti_actu)
            VALUES
              (mae_cvin_seq.nextval,
               trunc(SYSDATE),
               NULL,
               (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i)),
               lstmp_oid_clie,
               to_number(lsoidvinculodupla),
               0,
               SYSDATE);
          
            DELETE FROM msg_buzon_mensa
             WHERE clie_oid_clie =
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
               AND mens_oid_mens =
                   (SELECT oid_mens
                      FROM msg_mensa
                     WHERE cod_mens = lscodigomensajerechazo);
          
            INSERT INTO msg_buzon_mensa
              (oid_buzo_mens,
               num_secu,
               ind_esta_mens,
               clie_oid_clie,
               mens_oid_mens,
               modu_oid_modu_orig,
               fec_grab,
               ind_list_cons,
               ind_acti)
            VALUES
              (msg_bume_seq.nextval,
               msg_bum2_seq.nextval,
               1,
               (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i)),
               (SELECT oid_mens
                  FROM msg_mensa
                 WHERE cod_mens = lscodigomensajeinscripcion),
               1,
               SYSDATE,
               0,
               1);
          
          END IF;
        
          --Insercion en MAE_CLIEN_TIPO_SUBTI
        
          IF (v_ind_dupl_nuev(i) = 'S') THEN
          
            INSERT INTO mae_clien_tipo_subti
              (oid_clie_tipo_subt,
               clie_oid_clie,
               ticl_oid_tipo_clie,
               sbti_oid_subt_clie,
               ind_ppal,
               fec_ulti_actu)
            VALUES
              (mae_ctsu_seq.nextval,
               (SELECT a.oid_clie
                  FROM mae_clien a
                 WHERE a.cod_clie = 'DP' || lstmp_oid_clie),
               5,
               6,
               1,
               SYSDATE);
          
          END IF;
        
          --Insercion en MAE_CLIEN_CLASI
        
          IF (v_ind_dupl_nuev(i) = 'S') THEN
          
            INSERT INTO mae_clien_clasi
              (oid_clie_clas,
               ctsu_oid_clie_tipo_subt,
               clas_oid_clas,
               perd_oid_peri,
               tccl_oid_tipo_clasi,
               fec_clas,
               ind_ppal,
               fec_ulti_actu)
            VALUES
              (mae_clcl_seq.nextval,
               (SELECT a.oid_clie_tipo_subt
                  FROM mae_clien_tipo_subti a
                 WHERE ticl_oid_tipo_clie = 5
                   AND a.clie_oid_clie =
                       (SELECT a.oid_clie
                          FROM mae_clien a
                         WHERE a.cod_clie = 'DP' || lstmp_oid_clie)),
               2036,
               gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_codperi(i)),
               2028,
               trunc(SYSDATE),
               1,
               SYSDATE);
          
            --
            BEGIN
              -- Obtiene informacion para insertar las madres duplas
              SELECT mcts.oid_clie_tipo_subt,
                     tcc.oid_tipo_clas,
                     c.oid_clas
                INTO lsoidsubtipo,
                     lsoidtipoclasificacion,
                     lsoidclasificacion
                FROM mae_tipo_clien       tc,
                     mae_subti_clien      sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi            c,
                     mae_clien_tipo_subti mcts
               WHERE tc.oid_tipo_clie = sc.ticl_oid_tipo_clie
                 AND tcc.sbti_oid_subt_clie = sc.oid_subt_clie
                 AND tcc.oid_tipo_clas = c.tccl_oid_tipo_clas
                 AND tcc.cod_tipo_clas = '23'
                 AND c.cod_clas = '01'
                 AND mcts.clie_oid_clie = v_oid_clie_madr(i)
                 AND sc.oid_subt_clie = mcts.sbti_oid_subt_clie;
            
            EXCEPTION
              WHEN no_data_found THEN
                lsoidsubtipo           := NULL;
                lsoidtipoclasificacion := NULL;
                lsoidclasificacion     := NULL;
            END;
          
            -- Inserta la clasificacion a la madre dupla
            BEGIN
              INSERT INTO mae_clien_clasi
                (oid_clie_clas,
                 ctsu_oid_clie_tipo_subt,
                 clas_oid_clas,
                 perd_oid_peri,
                 tccl_oid_tipo_clasi,
                 fec_clas,
                 ind_ppal,
                 fec_ulti_actu)
              VALUES
                (mae_clcl_seq.nextval,
                 lsoidsubtipo,
                 lsoidclasificacion,
                 gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_codperi(i)),
                 lsoidtipoclasificacion,
                 SYSDATE,
                 '0',
                 SYSDATE);
            
            EXCEPTION
              WHEN OTHERS THEN
                NULL;
            END;
          
          END IF;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_envioscc %NOTFOUND;
    END LOOP;
  
    CLOSE c_envioscc;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_DCYZ_ENVIO_SICC: ' ||
                              ls_sqlerrm);
    
  END sto_pr_dcyz_envio_sicc;
  /**************************************************************************
  Descripcion       : STO_PR_CUP_ENVIO_SICC
                    Envio de CUP a SICC
  Fecha Creacion    : 18/02/2009
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Cristian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviocup IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_pais,
             cons.fec_proc,
             cons.oid_peri,
             cons.imp_valo,
             cons.sta_cupo
      
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codpais IS TABLE OF int_solic_conso_cupon_pago.cod_pais%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_cupon_pago.num_docu%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cupon_pago.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_codtipodoc IS TABLE OF int_solic_conso_cupon_pago.docu_cod_tipo_docu%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oidpais IS TABLE OF int_solic_conso_cupon_pago.oid_pais%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_solic_conso_cupon_pago.fec_proc%TYPE;
    TYPE t_oidperiodo IS TABLE OF int_solic_conso_cupon_pago.oid_peri%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;
    TYPE t_sta_cupo IS TABLE OF int_solic_conso_cupon_pago.sta_cupo%TYPE;
  
    v_codpais      t_codpais;
    v_num_docu     t_num_docu;
    v_codperi      t_codperi;
    v_codclie      t_codclie;
    v_numlote      t_numlote;
    v_codtipodoc   t_codtipodoc;
    v_secnumdocu   t_secnumdocu;
    v_oidpais      t_oidpais;
    v_fechaproceso t_fechaproceso;
    v_oidperiodo   t_oidperiodo;
    v_imp_valo     t_imp_valo;
    v_sta_cupo     t_sta_cupo;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lstmp_oid_cabec ccc_cupon_trami_depur.oid_cupo_tram_depu%TYPE;
    lstmp_oid_detal ccc_detal_cupon_trami_depur.oid_deta_cupo_tram_depu%TYPE;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    OPEN c_enviocup;
    LOOP
      FETCH c_enviocup BULK COLLECT
        INTO v_codpais,
             v_num_docu,
             v_codperi,
             v_codclie,
             v_numlote,
             v_codtipodoc,
             v_secnumdocu,
             v_oidpais,
             v_fechaproceso,
             v_oidperiodo,
             v_imp_valo,
             v_sta_cupo LIMIT rows;
    
      IF v_codpais.count > 0 THEN
      
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
        
          SELECT ccc_ctde_seq.nextval INTO lstmp_oid_cabec FROM dual;
        
          SELECT ccc_dctd_seq.nextval INTO lstmp_oid_detal FROM dual;
        
          INSERT INTO ccc_cupon_trami_depur
            (oid_cupo_tram_depu,
             sbac_oid_sbac,
             num_cupe,
             val_anio,
             val_peri_mes,
             fec_docu,
             val_refe_exte,
             val_obse,
             pais_oid_pais,
             ccpr_oid_proc,
             marc_oid_marc,
             perd_oid_peri,
             cana_oid_cana)
          VALUES
            (lstmp_oid_cabec,
             888,
             sto_nume_cupo_seq.nextval,
             to_char(v_fechaproceso(i), 'yy'),
             to_char(v_fechaproceso(i), 'mm'),
             trunc(SYSDATE),
             NULL,
             NULL,
             v_oidpais(i),
             2,
             2003,
             v_oidperiodo(i),
             2001);
        
          INSERT INTO ccc_detal_cupon_trami_depur
            (oid_deta_cupo_tram_depu,
             clie_oid_clie,
             mvcc_oid_movi_cc,
             cmba_oid_movi_banc,
             imp_deta,
             ccba_oid_cc_banc,
             fec_digi,
             num_line,
             fec_docu,
             ref_exte,
             obs_deta_cupo,
             num_cupo,
             fec_movi_banc,
             ctde_oid_cupo_tram_depu,
             soci_oid_soci,
             sicu_oid_situ_cupo,
             soca_oid_soli_cabe)
          VALUES
            (lstmp_oid_detal,
             (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i)),
             NULL,
             NULL,
             v_imp_valo(i),
             NULL,
             v_fechaproceso(i),
             1,
             v_fechaproceso(i),
             NULL,
             NULL,
             1,
             NULL,
             lstmp_oid_cabec,
             (SELECT oid_soci FROM seg_socie WHERE cod_soci = v_codclie(i)),
             --to_number(v_sta_cupo(i)),
             1,
             NULL);
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_enviocup %NOTFOUND;
    END LOOP;
  
    CLOSE c_enviocup;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_CUP_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_cup_envio_sicc;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_ENVI_MAIL
                    Envio de Correos - Solicitud de credito
  Fecha Creacion    : 18/02/2009
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psnumeroproceso : Numero de proceso
      pstipodocumento : Codigo de Tipo de documento
  
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_envi_mail
  (
    pscodigopais    VARCHAR2,
    psnumeroproceso VARCHAR2,
    pstipodocumento VARCHAR2,
    psobservaciones VARCHAR2
  ) IS
  
  BEGIN
  
    -- Listado de aprobados
    INSERT INTO sto_envio_email_scc
      (num_proce,
       val_mail_gere_zona,
       val_subj,
       val_nomb_clie,
       val_tipo_docu,
       cod_clie,
       ind_rech,
       num_lote,
       sec_nume_docu,
       val_obse,
       num_docu,
       cod_digi_ctrl,
       val_mail_clie)
      (SELECT DISTINCT psnumeroproceso AS numero_proceso,
                       (SELECT val_text_comu
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 3
                           AND clie_oid_clie =
                               (SELECT clie_oid_clie
                                  FROM zon_zona
                                 WHERE oid_zona =
                                       (gen_pkg_gener.gen_fn_devuelve_id_zona(pscodigopais,
                                                                              'T',
                                                                              'VD',
                                                                              a.cod_regi,
                                                                              a.cod_zona)))) AS correo_gerente_zona,
                       'Confirmacion de Solicitud de Credito Procesada' AS subject,
                       initcap(b.val_nom1) || ' ' || initcap(b.val_nom2) || ' ' ||
                       initcap(b.val_ape1) || ' ' || initcap(b.val_ape2) AS nombre_consultora,
                       b.tip_docu || ' - ' || b.num_docu_iden AS tipo_documento,
                       b.cod_clie AS codigo_consultora,
                       '0',
                       a.num_lote,
                       a.sec_nume_docu,
                       psobservaciones,
                       b.num_docu,
                       (SELECT mc.cod_digi_ctrl
                          FROM mae_clien mc
                         WHERE mc.cod_clie = b.cod_clie) AS cod_digi_ctrl,
                       b.val_mail_clie
         FROM sto_docum_digit       a,
              int_solic_conso_credi b
        WHERE a.cod_pais = b.cod_pais
          AND a.num_lote = b.num_lote
          AND a.sec_nume_docu = b.sec_nume_docu
          AND a.cod_tipo_docu = pstipodocumento
          AND a.num_proc = psnumeroproceso
          AND a.ind_envi = 1);
  
    -- Listado de rechazados
    INSERT INTO sto_envio_email_scc
      (num_proce,
       val_mail_gere_zona,
       val_subj,
       val_nomb_clie,
       val_tipo_docu,
       cod_clie,
       ind_rech,
       num_lote,
       sec_nume_docu,
       val_obse,
       num_docu,
       num_docu_iden,
       val_desc_moti_recha,
       val_mail_clie)
      (SELECT DISTINCT psnumeroproceso AS numero_proceso,
                       (SELECT val_text_comu
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 3
                           AND clie_oid_clie =
                               (SELECT clie_oid_clie
                                  FROM zon_zona
                                 WHERE oid_zona =
                                       (gen_pkg_gener.gen_fn_devuelve_id_zona(pscodigopais,
                                                                              'T',
                                                                              'VD',
                                                                              a.cod_regi,
                                                                              a.cod_zona)))) AS correo_gerente_zona,
                       --'Rechazo de Solicitud de Credito' AS subject,
                       decode(a.val_obse_rech_defi,
                              nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_RECHA_SC_AD'),
                                  ''),
                              nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_RECHA_SUBJT'),
                                  'Rechazo de Solicitud de Credito'),
                              'Rechazo de Solicitud de Credito') AS subject,
                       initcap(b.val_nom1) || ' ' || initcap(b.val_nom2) || ' ' ||
                       initcap(b.val_ape1) || ' ' || initcap(b.val_ape2) AS nombre_consultora,
                       b.tip_docu || ' - ' || b.num_docu_iden AS tipo_documento,
                       b.cod_clie AS codigo_consultora,
                       '1',
                       a.num_lote,
                       a.sec_nume_docu,
                       psobservaciones,
                       b.num_docu,
                       b.num_docu_iden,
                       TRIM(c.cod_moti_rech || ' ' || c.des_moti_rech) des_moti_recha,
                       b.val_mail_clie
         FROM sto_docum_digit       a,
              int_solic_conso_credi b,
              sto_recha_motiv       c
        WHERE a.cod_pais = b.cod_pais
          AND a.num_lote = b.num_lote
          AND a.sec_nume_docu = b.sec_nume_docu
          AND a.cod_tipo_docu = pstipodocumento
          AND a.num_proc = psnumeroproceso
          AND a.ind_rech = 1
          AND c.cod_tipo_docu(+) = 'SCC'
          AND a.cod_moti_rech = c.cod_moti_rech(+));
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_scc_envi_mail: ' || ls_sqlerrm);
  END sto_pr_scc_envi_mail;
  /**************************************************************************
   Descripcion       : STO_PR_GRABA_FAC_REC_OID
                       Envio de informacion a Pedidos y Reclamos
   Fecha Creacion    : 22/04/2009
   Parametros Entrada:
       Codigo de Usuario   : OID de Reclamo
       psUsuario           : Codigo de Usuario
       pscodigopais        : Codigo de pais
  
   Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_pr_graba_fac_rec_oid
  (
    psoidreclamo VARCHAR2,
    psusuario    VARCHAR2,
    psinicio     NUMBER
  ) RETURN NUMBER IS
    CURSOR c_cursor IS
      SELECT r.pais_oid_pais v_pais_oid_pais,
             r.perd_oid_peri_recl v_perd_oid_peri,
             r.sbti_oid_subt_clie v_sbti_oid_subt_clie,
             r.ticl_oid_tipo_clie v_ticl_oid_tipo_clie,
             r.soca_oid_soli_cabe v_soca_oid_docu_refe,
             r.clie_oid_clie v_clie_oid_clie,
             ua.ztad_oid_terr_admi v_ztad_oid_terr_admi,
             zo.oid_zona v_zzon_oid_zona,
             ad.terr_oid_terr v_terr_oid_terr,
             te.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop,
             op.tspa_oid_soli_con_stoc v_tspa_oid_tipo_soli_pais_env,
             op.tspa_oid_soli_pais_gene v_tspa_oid_tipo_soli_pais_dev,
             op.almc_oid_alma v_almc_oid_alma,
             op.ind_anul v_ind_anul,
             e.fec_fina v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire v_cldi_oid_clie_dire,
             l.timo_oid_tipo_movi v_timo_oid_tipo_movi,
             l.val_prec v_val_prec,
             l.val_prec_cont v_val_prec_cont,
             l.num_unid_recl v_num_unid_recl,
             l.prod_oid_prod v_prod_oid_prod,
             l.mafa_oid_matr_fact v_mafa_oid_matr_fact,
             l.tofe_oid_tipo_ofer v_tofe_oid_tipo_ofer,
             r.oid_cabe_recl v_oid_cabe_recl,
             l.opre_oid_oper_recl v_opre_oid_oper_recl,
             l.oid_line_oper_recl v_oid_line_oper_recl,
             l.sopo_oid_soli_posi v_sopo_oid_soli_posi,
             l.timo_oid_tipo_movi v_timo_oid_tipo_movi,
             p.val_nume_soli v_val_nume_soli,
             m.tido_oid_tipo_docu v_tido_oid_tipo_docu,
             op.ind_devu_fisi_fact v_ind_devu_fisi_fact,
             nvl(f.dcca_oid_cabe, 0) v_oid_cabe
        FROM rec_cabec_recla       r,
             rec_opera_recla       o,
             rec_opera             op,
             rec_tipos_opera       t,
             rec_linea_opera_recla l,
             mae_clien             c,
             mae_clien_unida_admin ua,
             mae_clien_ident       ide,
             mae_clien_direc       dir,
             zon_regio             re,
             zon_zona              zo,
             zon_secci             se,
             zon_terri_admin       ad,
             zon_terri             te,
             cra_perio             e,
             ped_solic_cabec       p,
             mae_tipo_docum        m,
             fac_docum_conta_linea f
       WHERE r.oid_cabe_recl = psoidreclamo
         AND f.sopo_oid_soli_posi = l.sopo_oid_soli_posi(+)
         AND r.clie_oid_clie = c.oid_clie
         AND dir.clie_oid_clie = c.oid_clie
         AND ua.clie_oid_clie = c.oid_clie
         AND r.oid_cabe_recl = o.care_oid_cabe_recl
         AND ad.oid_terr_admi = ua.ztad_oid_terr_admi
         AND te.oid_terr = ad.terr_oid_terr
         AND ad.zscc_oid_secc = se.oid_secc
         AND se.zzon_oid_zona = zo.oid_zona
         AND zo.zorg_oid_regi = re.oid_regi
         AND ide.clie_oid_clie = c.oid_clie
         AND t.rope_oid_oper = op.oid_oper
         AND o.tiop_oid_tipo_oper = t.oid_tipo_oper
         AND l.opre_oid_oper_recl = o.oid_oper_recl
         AND c.oid_clie = r.clie_oid_clie
         AND e.oid_peri = r.perd_oid_peri_recl
         AND ua.perd_oid_peri_fin IS NULL
         AND te.ind_borr = 0
         AND zo.ind_acti = 1
         AND ad.ind_borr = 0
         AND se.ind_acti = 1
         AND se.ind_borr = 0
         AND ide.val_iden_docu_prin = 1
         AND l.copa_oid_para_gral IS NULL -- NO RECUPERA PREMIOS
         AND p.oid_soli_cabe = r.soca_oid_soli_cabe
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
       ORDER BY r.oid_cabe_recl,
                l.opre_oid_oper_recl,
                nvl(f.dcca_oid_cabe, 0),
                l.timo_oid_tipo_movi;
  
    CURSOR c_cursor2 IS
      SELECT r.pais_oid_pais v_pais_oid_pais,
             r.perd_oid_peri_recl v_perd_oid_peri,
             r.sbti_oid_subt_clie v_sbti_oid_subt_clie,
             r.ticl_oid_tipo_clie v_ticl_oid_tipo_clie,
             r.soca_oid_soli_cabe v_soca_oid_docu_refe,
             r.clie_oid_clie v_clie_oid_clie,
             ua.ztad_oid_terr_admi v_ztad_oid_terr_admi,
             zo.oid_zona v_zzon_oid_zona,
             ad.terr_oid_terr v_terr_oid_terr,
             te.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop,
             op.tspa_oid_soli_con_stoc v_tspa_oid_tipo_soli_pais_env,
             op.tspa_oid_soli_pais_gene v_tspa_oid_tipo_soli_pais_dev,
             op.almc_oid_alma v_almc_oid_alma,
             op.ind_anul v_ind_anul,
             e.fec_fina v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire v_cldi_oid_clie_dire,
             l.timo_oid_tipo_movi v_timo_oid_tipo_movi,
             l.val_prec v_val_prec,
             l.val_prec_cont v_val_prec_cont,
             l.num_unid_recl v_num_unid_recl,
             l.prod_oid_prod v_prod_oid_prod,
             l.copa_oid_para_gral v_copa_oid_para_gene,
             l.panp_oid_para_nive_prem v_panp_oid_para_nive_prem,
             l.lopa_oid_lote_prem_arti v_lopa_oid_lote_prem_arti,
             r.oid_cabe_recl v_oid_cabe_recl,
             l.opre_oid_oper_recl v_opre_oid_oper_recl,
             l.oid_line_oper_recl v_oid_line_oper_recl,
             l.sopo_oid_soli_posi v_sopo_oid_soli_posi,
             l.timo_oid_tipo_movi v_timo_oid_tipo_movi,
             p.val_nume_soli v_val_nume_soli,
             m.tido_oid_tipo_docu v_tido_oid_tipo_docu_org,
             nvl(f.dcca_oid_cabe, 0) v_oid_cabe
        FROM rec_cabec_recla       r,
             rec_opera_recla       o,
             rec_opera             op,
             rec_tipos_opera       t,
             rec_linea_opera_recla l,
             mae_clien             c,
             mae_clien_unida_admin ua,
             mae_clien_ident       ide,
             mae_clien_direc       dir,
             zon_regio             re,
             zon_zona              zo,
             zon_secci             se,
             zon_terri_admin       ad,
             zon_terri             te,
             cra_perio             e,
             ped_solic_cabec       p,
             mae_tipo_docum        m,
             fac_docum_conta_linea f
       WHERE r.oid_cabe_recl = psoidreclamo
         AND f.sopo_oid_soli_posi = l.sopo_oid_soli_posi(+)
         AND r.clie_oid_clie = c.oid_clie
         AND dir.clie_oid_clie = c.oid_clie
         AND ua.clie_oid_clie = c.oid_clie
         AND r.oid_cabe_recl = o.care_oid_cabe_recl
         AND ad.oid_terr_admi = ua.ztad_oid_terr_admi
         AND te.oid_terr = ad.terr_oid_terr
         AND ad.zscc_oid_secc = se.oid_secc
         AND se.zzon_oid_zona = zo.oid_zona
         AND zo.zorg_oid_regi = re.oid_regi
         AND ide.clie_oid_clie = c.oid_clie
         AND t.rope_oid_oper = op.oid_oper
         AND o.tiop_oid_tipo_oper = t.oid_tipo_oper
         AND l.opre_oid_oper_recl = o.oid_oper_recl
         AND c.oid_clie = r.clie_oid_clie
         AND e.oid_peri = r.perd_oid_peri_recl
         AND ua.perd_oid_peri_fin IS NULL
         AND te.ind_borr = 0
         AND zo.ind_acti = 1
         AND ad.ind_borr = 0
         AND se.ind_acti = 1
         AND se.ind_borr = 0
         AND ide.val_iden_docu_prin = 1
         AND l.copa_oid_para_gral IS NOT NULL -- RECUPERA PREMIOS
         AND p.oid_soli_cabe = r.soca_oid_soli_cabe
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
       ORDER BY r.oid_cabe_recl,
                l.opre_oid_oper_recl,
                l.timo_oid_tipo_movi,
                l.copa_oid_para_gral,
                l.panp_oid_para_nive_prem,
                l.lopa_oid_lote_prem_arti,
                nvl(f.dcca_oid_cabe, 0);
  
    TYPE registrorec IS RECORD(
      oid_pais               rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri_recl          rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_sub_clie           rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie          rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_soli_cabe          rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clien              rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_adm           mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona               zon_zona.oid_zona%TYPE,
      oid_terr               zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geo      zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_soli_con_stoc      rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_soli_pais_gene     rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma               rec_opera.almc_oid_alma%TYPE,
      ind_anul               rec_opera.ind_anul%TYPE,
      fec_fina               cra_perio.fec_fina%TYPE,
      oid_tipo_docu          mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire          mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi1         rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec               rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont          rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl          rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod               rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_matr_fact          rec_linea_opera_recla.mafa_oid_matr_fact%TYPE,
      oid_tipo_ofer          rec_linea_opera_recla.tofe_oid_tipo_ofer%TYPE,
      oid_cabe_recl          rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl          rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl     rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi          rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      oid_tipo_movi          rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE,
      ind_devu_fisi_fact     rec_opera.ind_devu_fisi_fact%TYPE,
      dcca_oid_cabe          fac_docum_conta_cabec.oid_cabe%TYPE);
  
    TYPE registrorectab IS TABLE OF registrorec;
    registrorecord registrorectab;
  
    TYPE registrorec2 IS RECORD(
      oid_pais               rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri               rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_subt_clie          rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie          rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_docu_refe          rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clie               rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_admi          mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona               zon_zona.oid_zona%TYPE,
      oid_terr               zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geop     zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_tipo_soli_pais_env rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_tipo_soli_pais_dev rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma               rec_opera.almc_oid_alma%TYPE,
      ind_anul               rec_opera.ind_anul%TYPE,
      fec_prog_fact          cra_perio.fec_fina%TYPE,
      oid_tipo_docu          mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire2         mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi2         rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec               rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont          rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl          rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod               rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_para_gene          rec_linea_opera_recla.copa_oid_para_gral%TYPE,
      oid_para_nive_prem     rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE,
      oid_lote_prem_arti     rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE,
      oid_cabe_recl          rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl          rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl     rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi          rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      oid_tipo_movi          rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE,
      dcca_oid_cabe          fac_docum_conta_cabec.oid_cabe%TYPE);
  
    TYPE registrorectab2 IS TABLE OF registrorec2;
    registrorecord2 registrorectab2;
  
    x                             NUMBER;
    y                             NUMBER;
    w_filas                       NUMBER := 1000;
    lnseqsolioper                 NUMBER;
    lnseqsolicabe                 NUMBER;
    v_enviado                     NUMBER;
    v_total                       NUMBER;
    v_tspa_oid_tipo_soli_pais_xxx NUMBER;
    seq_oid_soli_cabe             NUMBER;
    v_fec_cron                    DATE;
    s_val_usua                    VARCHAR2(20);
    seq_val_nume_soli             NUMBER;
  
    oid_form_pago      ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
    ind_perm_unio_sol  ped_tipo_solic_pais.ind_perm_unio%TYPE;
    soci_oid_soci      ped_tipo_solic_pais.soci_oid_soci%TYPE;
    tspa_oid_tipo_soli ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
    tido_oid_tipo_docu ped_tipo_solic_pais.tido_oid_tipo_docu%TYPE;
    val_glos_obse      ped_tipo_solic_pais.val_glos%TYPE;
    ind_pedi_prue      ped_tipo_solic_pais.ind_pedi_prue%TYPE;
    mone_oid_mone      ped_tipo_solic_pais.mone_oid_mone%TYPE;
    acfi_oid_acce_fisi ped_tipo_solic.acce_oid_acce%TYPE;
    sbac_oid_sbac      ped_tipo_solic.sbac_oid_sbac%TYPE;
    clso_oid_clas_soli ped_tipo_solic.clso_oid_clas_soli%TYPE;
    ind_orde_comp      ped_clase_solic.ind_orde_comp%TYPE;
    ind_soli_nega      ped_tipo_solic.ind_soli_nega%TYPE;
  
    oid_tipo_prog      inc_concu_param_gener.ictp_oid_tipo_prog%TYPE;
    oid_conc_tipo_prog inc_concu_param_gener.ictp_oid_conc_tipo_prog%TYPE;
    num_prem           inc_lote_premi_artic.num_prem%TYPE;
    val_codi_vent_fict inc_artic_lote.cod_vent_fict%TYPE;
  
    seq_cod_posi      NUMBER;
    seq_oid_soli_posi NUMBER;
  
    val_codi_vent      pre_ofert_detal.val_codi_vent%TYPE;
    ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;
  
    v_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE := 0;
    v_val_impo_desc_unit_loca ped_solic_posic.val_impo_desc_unit_loca%TYPE := 0;
    v_val_porc_desc           ped_solic_posic.val_porc_desc%TYPE := 0;
  
    r_enviado NUMBER;
    r_total   NUMBER;
  
    lnnumsoliinicio NUMBER;
    lnnumsoliformat NUMBER;
    lnvepooid       NUMBER;
    lnvepooid2      NUMBER;
  
    nom NUMBER;
  
    oid_oper_recl_anterior rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    dcca_oid_cabe_anterior fac_docum_conta_cabec.oid_cabe%TYPE := NULL;
  
    oid_tipo_movi_anterior  rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
    dcca_oid_cabe_anterior2 fac_docum_conta_cabec.oid_cabe%TYPE := NULL;
  
    oid_oper_recl_anterior2     rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    oid_para_gene_anterior      rec_linea_opera_recla.copa_oid_para_gral%TYPE := NULL;
    oid_para_nive_prem_anterior rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE := NULL;
    oid_lote_prem_arti_anterior rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE := NULL;
    oid_tipo_movi_anterior2     rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
  
    oid_alma ped_tipo_solic_pais.almc_oid_alma%TYPE;
  
  BEGIN
    lnnumsoliinicio := psinicio;
    SELECT to_char(SYSDATE, 'YY') || lpad(lnnumsoliinicio, 8, '0')
      INTO lnnumsoliformat
      FROM dual;
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO registrorecord LIMIT w_filas;
      IF registrorecord.count > 0 THEN
        FOR x IN registrorecord.first .. registrorecord.last
        LOOP
        
          BEGIN
            SELECT md.val_codi_vent v_val_codi_vent,
                   md.oid_deta_ofer v_ofde_oid_deta_ofer
              INTO val_codi_vent,
                   ofde_oid_deta_ofer
              FROM pre_matri_factu mf,
                   pre_ofert_detal md,
                   pre_tipo_ofert  tof
             WHERE md.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
               AND mf.ofde_oid_deta_ofer = md.oid_deta_ofer
               AND md.tofe_oid_tipo_ofer = registrorecord(x).oid_tipo_ofer
               AND mf.oid_matr_fact = registrorecord(x).oid_matr_fact
               AND md.prod_oid_prod = registrorecord(x).oid_prod;
          EXCEPTION
            WHEN no_data_found THEN
              val_codi_vent      := NULL;
              ofde_oid_deta_ofer := NULL;
          END;
        
          --IF registroRecord(x).oid_oper_recl != psOidReclamo THEN
          IF oid_oper_recl_anterior || oid_tipo_movi_anterior ||
             dcca_oid_cabe_anterior != registrorecord(x)
            .oid_oper_recl || registrorecord(x).oid_tipo_movi || registrorecord(x)
            .dcca_oid_cabe OR oid_oper_recl_anterior IS NULL THEN
          
            -- x1
            IF oid_oper_recl_anterior IS NOT NULL THEN
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
            
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            -- se cambio de lugar x1
            IF registrorecord(x).oid_tipo_movi1 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                               .oid_soli_pais_gene;
            ELSE
              IF registrorecord(x).oid_tipo_movi1 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                                 .oid_soli_con_stoc;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior IS NOT NULL THEN
            
              SELECT COUNT(*)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              IF v_total = v_enviado THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior;
              END IF;
            END IF;
          
            --    Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac       v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
                   tsp.almc_oid_alma      v_oid_alma,
                   cs.ind_orde_comp       v_ind_orde_comp,
                   ts.ind_soli_nega       v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   oid_alma,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                BEGIN
                  SELECT tido_oid_tipo_docu_cont
                    INTO tido_oid_tipo_docu
                    FROM fac_tipo_docum
                   WHERE oid_tipo_docu IN
                         ((SELECT ped.tido_oid_tipo_docu
                            FROM ped_solic_cabec ped
                           WHERE ped.oid_soli_cabe IN
                                 (SELECT pos.soca_oid_soli_cabe
                                    FROM ped_solic_posic pos
                                   WHERE pos.oid_soli_posi = registrorecord(x)
                                        .oid_soli_posi)));
                EXCEPTION
                  WHEN no_data_found THEN
                    SELECT tido_oid_tipo_docu_cont
                      INTO tido_oid_tipo_docu
                      FROM fac_tipo_docum
                     WHERE oid_tipo_docu = registrorecord(x)
                          .tido_oid_tipo_docu_org;
                END;
              ELSE
                tido_oid_tipo_docu := registrorecord(x)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio := lnnumsoliinicio + 1;
            lnnumsoliformat := lnnumsoliformat + 1;
          
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT SYSDATE INTO v_fec_cron FROM dual;
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            nom       := registrorecord(x).oid_cabe_recl;
            lnvepooid := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord(x)
                                                              .oid_pais,
                                                              registrorecord(x)
                                                              .oid_clien);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord(x).fec_fina,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               decode(oid_alma, NULL, 2001, oid_alma),
               15,
               registrorecord(x).oid_tipo_clie,
               NULL,
               registrorecord(x).oid_peri_recl,
               NULL,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clie_dire,
               registrorecord(x).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord(x).oid_terr,
               registrorecord(x).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(v_fec_cron),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               NULL,
               0,
               0,
               registrorecord(x).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid, --registrorecord(x).oid_valo_estr_geo,
               NULL,
               1,
               NULL,
               1,
               registrorecord(x).oid_sub_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli,
               oid_form_pago,
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord(x).oid_terr_adm,
               NULL,
               21,
               1,
               registrorecord(x).oid_soli_cabe,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord(x).fec_fina, 'ddMMyyyy'),
               NULL,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior := registrorecord(x).oid_oper_recl;
          oid_tipo_movi_anterior := registrorecord(x).oid_tipo_movi;
          dcca_oid_cabe_anterior := registrorecord(x).dcca_oid_cabe;
        
          IF registrorecord(x).oid_tipo_movi1 = 2 THEN
            registrorecord(x).num_unid_recl := registrorecord(x)
                                               .num_unid_recl * (-1);
          END IF;
        
          IF registrorecord(x).oid_soli_posi IS NOT NULL THEN
          
            BEGIN
              SELECT val_prec_cata_unit_loca,
                     val_impo_desc_unit_loca,
                     val_porc_desc
                INTO v_val_prec_cata_unit_loca,
                     v_val_impo_desc_unit_loca,
                     v_val_porc_desc
                FROM ped_solic_posic p
               WHERE oid_soli_posi = registrorecord(x).oid_soli_posi;
            EXCEPTION
              WHEN no_data_found THEN
                v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
                v_val_impo_desc_unit_loca := 0;
                v_val_porc_desc           := 0;
            END;
          
          ELSE
            v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
            v_val_impo_desc_unit_loca := 0;
            v_val_porc_desc           := 0;
          END IF;
        
          IF ((registrorecord(x).ind_devu_fisi_fact = 1) OR
             (((registrorecord(x).oid_tipo_movi1 = 2) AND
             (registrorecord(x).oid_soli_posi IS NOT NULL) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)) OR
             ((registrorecord(x).oid_tipo_movi1 = 1) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)))) THEN
          
            SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual; --secuencial de PED_SOLIC_POSIC
          
            seq_cod_posi := seq_cod_posi + 1;
          
            INSERT INTO ped_solic_posic
              (oid_soli_posi,
               cod_posi,
               num_unid_dema,
               num_unid_por_aten,
               val_tasa_impu,
               soca_oid_soli_cabe,
               tpos_oid_tipo_posi,
               prod_oid_prod,
               fopa_oid_form_pago,
               val_prec_cata_unit_loca,
               val_prec_cont_unit_loca,
               val_prec_cata_unit_docu,
               val_prec_conta_unit_docu,
               val_prec_fact_unit_loca,
               val_prec_fact_unit_docu,
               val_prec_sin_impu_unit_loca,
               val_prec_sin_impu_unit_docu,
               val_prec_sin_impu_tota_docu,
               val_impo_desc_unit_loca,
               val_prec_neto_unit_loca,
               val_prec_neto_tota_docu,
               val_prec_neto_unit_docu,
               val_prec_tota_tota_loca,
               val_prec_tota_tota_docu,
               val_impo_impu_unit_loca,
               val_impo_impu_unit_docu,
               val_impo_desc_tota_docu,
               val_impo_impu_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_desc_tota_loca,
               val_prec_tota_unit_loca,
               val_prec_tota_unit_docu,
               val_prec_cont_tota_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_tota_docu,
               val_prec_cont_tota_docu,
               val_prec_cata_tota_loca_unid,
               num_unid_dema_real,
               num_unid_compr,
               val_prec_fact_tota_loca,
               val_prec_fact_tota_docu,
               val_prec_sin_impu_tota_loca,
               val_prec_neto_tota_loca,
               ofde_oid_deta_ofer,
               espo_oid_esta_posi,
               stpo_oid_subt_posi,
               val_codi_vent,
               ind_no_impr,
               val_codi_vent_fict,
               val_porc_desc)
            VALUES
              (seq_oid_soli_posi,
               seq_cod_posi,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               seq_oid_soli_cabe,
               10,
               registrorecord(x).oid_prod,
               NULL,
               v_val_prec_cata_unit_loca, -- registrorecord(x).val_prec, -- poner precio catalogo
               registrorecord(x).val_prec_cont,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               v_val_impo_desc_unit_loca, -- 0, -- poner monto descuento
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               0,
               0,
               0,
               ofde_oid_deta_ofer,
               4,
               1,
               val_codi_vent,
               NULL,
               NULL,
               v_val_porc_desc -- 0 -- poner porcentaje de descuento
               );
          
          END IF;
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord(x)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;
  
    IF oid_oper_recl_anterior IS NOT NULL THEN
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      SELECT COUNT(1)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      IF v_total = v_enviado THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2
         WHERE oid_oper_recl = oid_oper_recl_anterior;
      END IF;
    END IF;
  
    OPEN c_cursor2;
    LOOP
      FETCH c_cursor2 BULK COLLECT
        INTO registrorecord2 LIMIT w_filas;
      IF registrorecord2.count > 0 THEN
        FOR y IN registrorecord2.first .. registrorecord2.last
        LOOP
          BEGIN
            SELECT cpg.ictp_oid_tipo_prog      v_ictp_oid_tipo_prog,
                   cpg.ictp_oid_conc_tipo_prog v_ictp_oid_conc_tipo_prog,
                   la.num_prem                 v_num_prem,
                   al.cod_vent_fict            v_val_codi_vent_fict
              INTO oid_tipo_prog,
                   oid_conc_tipo_prog,
                   num_prem,
                   val_codi_vent_fict
              FROM mae_produ             mp,
                   inc_artic_lote        al,
                   inc_lote_premi_artic  la,
                   inc_premi_artic       pa,
                   inc_param_nivel_premi pn,
                   inc_param_gener_premi pg,
                   inc_concu_param_gener cpg
             WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
               AND al.prod_oid_prod = mp.oid_prod
               AND la.prar_oid_prem_arti = pa.oid_prem_arti
               AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
               AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
               AND pg.copa_oid_para_gral = cpg.oid_para_gral
               AND cpg.oid_para_gral = registrorecord2(y).oid_para_gene
               AND pa.panp_oid_para_nive_prem = registrorecord2(y)
                  .oid_para_nive_prem
               AND al.lopa_oid_lote_prem_arti = registrorecord2(y)
                  .oid_lote_prem_arti
               AND al.prod_oid_prod = registrorecord2(y).oid_prod;
          EXCEPTION
            WHEN no_data_found THEN
              oid_tipo_prog      := NULL;
              oid_conc_tipo_prog := NULL;
              num_prem           := NULL;
              val_codi_vent_fict := NULL;
          END;
        
          IF oid_oper_recl_anterior2 || oid_para_gene_anterior ||
             oid_para_nive_prem_anterior || oid_lote_prem_arti_anterior ||
             oid_tipo_movi_anterior2 || dcca_oid_cabe_anterior2 != registrorecord2(y)
            .oid_oper_recl || registrorecord2(y).oid_para_gene || registrorecord2(y)
            .oid_para_nive_prem || registrorecord2(y).oid_lote_prem_arti || registrorecord2(y)
            .oid_tipo_movi || registrorecord2(y).dcca_oid_cabe OR
             oid_oper_recl_anterior2 IS NULL THEN
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior2,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                               .oid_tipo_soli_pais_dev;
            ELSE
              IF registrorecord2(y).oid_tipo_movi2 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                                 .oid_tipo_soli_pais_env;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT COUNT(1)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
                 AND ind_esta = 'E';
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
            
              IF v_enviado = v_total THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior2;
              
              END IF;
            END IF;
          
            --   Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac       v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
                   cs.ind_orde_comp       v_ind_orde_comp,
                   ts.ind_soli_nega       v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                SELECT tido_oid_tipo_docu_cont
                  INTO tido_oid_tipo_docu
                  FROM fac_tipo_docum
                 WHERE oid_tipo_docu IN
                       ((SELECT ped.tido_oid_tipo_docu
                          FROM ped_solic_cabec ped
                         WHERE ped.oid_soli_cabe IN
                               (SELECT pos.soca_oid_soli_cabe
                                  FROM ped_solic_posic pos
                                 WHERE pos.oid_soli_posi = registrorecord2(y)
                                      .oid_soli_posi)));
              ELSE
                tido_oid_tipo_docu := registrorecord2(y)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio   := lnnumsoliinicio + 1;
            lnnumsoliformat   := lnnumsoliformat + 1;
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            lnvepooid2 := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord2(y)
                                                               .oid_pais,
                                                               registrorecord2(y)
                                                               .oid_clie);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord2(y).fec_prog_fact,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               decode(registrorecord2(y).oid_tipo_movi2,
                      1,
                      2001,
                      registrorecord2(y).oid_alma), -- duro!!!!
               15,
               registrorecord2(y).oid_tipo_clie,
               NULL,
               registrorecord2(y).oid_peri,
               NULL,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie_dire2,
               registrorecord2(y).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord2(y).oid_terr,
               registrorecord2(y).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(SYSDATE),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               num_prem,
               0,
               0,
               registrorecord2(y).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid2, --registrorecord2(y).oid_valo_estr_geop,
               NULL,
               1,
               registrorecord2(y).oid_para_gene,
               1,
               registrorecord2(y).oid_subt_clie,
               1,
               tspa_oid_tipo_soli,
               oid_form_pago,
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord2(y).oid_terr_admi,
               NULL,
               21,
               1,
               registrorecord2(y).oid_docu_refe,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord2(y).fec_prog_fact, 'yyyyMMdd'),
               oid_tipo_prog,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior2     := registrorecord2(y).oid_oper_recl;
          oid_para_gene_anterior      := registrorecord2(y).oid_para_gene;
          oid_para_nive_prem_anterior := registrorecord2(y)
                                         .oid_para_nive_prem;
          oid_lote_prem_arti_anterior := registrorecord2(y)
                                         .oid_lote_prem_arti;
          oid_tipo_movi_anterior2     := registrorecord2(y).oid_tipo_movi;
          dcca_oid_cabe_anterior2     := registrorecord2(y).dcca_oid_cabe;
        
          IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
            registrorecord2(y).num_unid_recl := registrorecord2(y)
                                                .num_unid_recl * (-1);
          END IF;
        
          SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual;
          seq_cod_posi := seq_cod_posi + 1;
        
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr,
             val_codi_vent_fict)
          VALUES
            (seq_oid_soli_posi,
             seq_cod_posi,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             lnseqsolicabe,
             10,
             registrorecord2(y).oid_prod,
             NULL,
             registrorecord2(y).val_prec,
             registrorecord2(y).val_prec_cont,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             0,
             0,
             0,
             NULL,
             4,
             14,
             NULL,
             NULL,
             val_codi_vent_fict);
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord2(y)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor2%NOTFOUND;
    END LOOP;
    CLOSE c_cursor2;
  
    IF oid_oper_recl_anterior2 IS NOT NULL THEN
    
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior2,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
         AND ind_esta = 'E';
    
      SELECT COUNT(*)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
    
      IF v_enviado = v_total THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2,
               fec_ulti_actu      = SYSDATE
         WHERE oid_oper_recl = oid_oper_recl_anterior2;
      
      END IF;
    END IF;
  
    SELECT COUNT(1)
      INTO r_enviado
      FROM rec_opera_recla o
     WHERE o.care_oid_cabe_recl = psoidreclamo
       AND o.esop_oid_esta_oper = 2;
  
    SELECT COUNT(1)
      INTO r_total
      FROM rec_opera_recla
     WHERE care_oid_cabe_recl = psoidreclamo;
  
    IF r_total = r_enviado THEN
      UPDATE rec_cabec_recla
         SET esre_oid_esta_recl = 6,
             fec_ulti_actu      = SYSDATE
       WHERE oid_cabe_recl = psoidreclamo;
    END IF;
  
    RETURN lnnumsoliinicio;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GRABA_FAC_REC_OID: ' ||
                              ls_sqlerrm || ' ' || nom);
  END sto_pr_graba_fac_rec_oid;

  /**************************************************************************
  Descripcion       : sto_pr_elimin_duplic_Reclam
                      Elimina los registros duplicados en tablas de Reclasmos
  Fecha Creacion    : 03/06/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_elimin_duplic_reclam IS
    CURSOR c_duplicados IS
      SELECT oid_cabe_recl,
             ro.oid_oper_recl,
             p.oid_soli_cabe
        FROM rec_cabec_recla r,
             rec_opera_recla ro,
             rec_solic_opera rso,
             ped_solic_cabec p
       WHERE r.oid_cabe_recl = ro.care_oid_cabe_recl
         AND ro.oid_oper_recl = rso.opre_oid_oper_recl
         AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
         AND rso.tspa_oid_tipo_soli_pais = p.tspa_oid_tipo_soli_pais
         AND trunc(r.fec_ingr) = trunc(SYSDATE)
         AND r.cod_usua_ingr LIKE 'S_%'
         AND NOT EXISTS
       (SELECT 1
                FROM int_solic_conso_poven_cabec i
               WHERE trunc(fec_proc_docu) = trunc(SYSDATE)
                 AND r.oid_cabe_recl = i.oid_cabe_recl_refe);
  
    TYPE t_oid_cabe_recl IS TABLE OF rec_cabec_recla.oid_cabe_recl %TYPE;
    TYPE t_oid_oper_recl IS TABLE OF rec_opera_recla.oid_oper_recl %TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe %TYPE;
  
    v_oid_cabe_recl t_oid_cabe_recl;
    v_oid_oper_recl t_oid_oper_recl;
    v_oid_soli_cabe t_oid_soli_cabe;
  
  BEGIN
  
    OPEN c_duplicados;
    LOOP
      FETCH c_duplicados BULK COLLECT
        INTO v_oid_cabe_recl,
             v_oid_oper_recl,
             v_oid_soli_cabe LIMIT rows;
    
      IF v_oid_cabe_recl.count > 0 THEN
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM rec_linea_opera_recla
           WHERE opre_oid_oper_recl = v_oid_oper_recl(i);
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM ped_solic_posic
           WHERE soca_oid_soli_cabe = v_oid_soli_cabe(i);
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM rec_solic_opera
           WHERE opre_oid_oper_recl = v_oid_oper_recl(i);
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM ped_solic_cabec
           WHERE oid_soli_cabe = v_oid_soli_cabe(i);
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM rec_opera_recla
           WHERE care_oid_cabe_recl = v_oid_cabe_recl(i);
      
        FORALL i IN 1 .. v_oid_cabe_recl.count
          DELETE FROM rec_cabec_recla
           WHERE oid_cabe_recl = v_oid_cabe_recl(i);
      
      END IF;
      EXIT WHEN c_duplicados%NOTFOUND;
    END LOOP;
    CLOSE c_duplicados;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ELIMIN_DUPLIC_RECLAM: ' ||
                              ls_sqlerrm);
  END sto_pr_elimin_duplic_reclam;

  /**************************************************************************
  Descripcion       : sto_pr_compl_recla
                      COmpleta Reclamos
  Fecha Creacion    : 10/12/2009
  Autor             : Arturo Blumen
  ***************************************************************************/
  PROCEDURE sto_pr_compl_recla
  (
    psusuario     VARCHAR2,
    pscodigopais  VARCHAR2,
    lnnumreclamos NUMBER
  ) IS
    CURSOR c_cursor IS
      SELECT rcr.pais_oid_pais          v_pais_oid_pais,
             rcr.perd_oid_peri_recl     v_perd_oid_peri,
             rcr.sbti_oid_subt_clie     v_sbti_oid_subt_clie,
             rcr.ticl_oid_tipo_clie     v_ticl_oid_tipo_clie,
             rcr.soca_oid_soli_cabe     v_soca_oid_docu_refe,
             rcr.clie_oid_clie          v_clie_oid_clie,
             zta.oid_terr_admi          v_ztad_oid_terr_admi,
             zon.oid_zona               v_zzon_oid_zona,
             zta.terr_oid_terr          v_terr_oid_terr,
             zt.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop,
             ro.tspa_oid_soli_con_stoc  v_tspa_oid_tipo_soli_pais_env,
             ro.tspa_oid_soli_pais_gene v_tspa_oid_tipo_soli_pais_dev,
             ro.almc_oid_alma           v_almc_oid_alma,
             ro.ind_anul                v_ind_anul,
             e.fec_fina                 v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu     v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire          v_cldi_oid_clie_dire,
             lor.timo_oid_tipo_movi     v_timo_oid_tipo_movi,
             lor.val_prec               v_val_prec,
             lor.val_prec_cont          v_val_prec_cont,
             lor.num_unid_recl          v_num_unid_recl,
             lor.prod_oid_prod          v_prod_oid_prod,
             lor.mafa_oid_matr_fact     v_mafa_oid_matr_fact,
             lor.tofe_oid_tipo_ofer     v_tofe_oid_tipo_ofer,
             rcr.oid_cabe_recl          v_oid_cabe_recl,
             lor.opre_oid_oper_recl     v_opre_oid_oper_recl,
             lor.oid_line_oper_recl     v_oid_line_oper_recl,
             lor.sopo_oid_soli_posi     v_sopo_oid_soli_posi,
             cab.val_nume_soli          v_val_nume_soli,
             m.tido_oid_tipo_docu       v_tido_oid_tipo_docu,
             ro.ind_devu_fisi_fact      v_ind_devu_fisi_fact
        FROM mae_clien             mc,
             rec_cabec_recla       rcr,
             mae_clien_ident       ide,
             rec_opera_recla       ror,
             rec_linea_opera_recla lor,
             mae_produ             mp,
             rec_tipos_opera       rto,
             rec_opera             ro,
             zon_terri_admin       zta,
             zon_terri             zt,
             zon_secci             sec,
             zon_zona              zon,
             ped_solic_cabec       cab,
             mae_tipo_docum        m,
             mae_clien_direc       dir,
             cra_perio             e
       WHERE mc.oid_clie = rcr.clie_oid_clie
         AND rcr.oid_cabe_recl = ror.care_oid_cabe_recl
         AND zta.terr_oid_terr = zt.oid_terr
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
         AND rcr.cod_usua_ingr LIKE 'S_%'
         AND dir.clie_oid_clie = mc.oid_clie
         AND ror.oid_oper_recl = lor.opre_oid_oper_recl
         AND ide.clie_oid_clie = mc.oid_clie
         AND e.oid_peri = rcr.perd_oid_peri_recl
         AND ide.val_iden_docu_prin = 1
         AND cab.oid_soli_cabe = rcr.soca_oid_soli_cabe
         AND lor.prod_oid_prod = mp.oid_prod
         AND rto.rope_oid_oper = ro.oid_oper
         AND rto.oid_tipo_oper = ror.tiop_oid_tipo_oper
         AND rcr.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = sec.oid_secc
         AND sec.zzon_oid_zona = zon.oid_zona
         AND EXISTS (SELECT NULL
                FROM rec_solic_opera p,
                     ped_solic_cabec q,
                     rec_opera_recla r
               WHERE p.soca_oid_soli_cabe = q.oid_soli_cabe
                 AND p.opre_oid_oper_recl = r.oid_oper_recl
                 AND r.care_oid_cabe_recl = rcr.oid_cabe_recl
                 AND q.esso_oid_esta_soli != 6)
         AND rcr.perd_oid_peri_recl >=
             (SELECT cp.oid_peri
                FROM bas_ctrl_fact   cf,
                     cra_perio       cp,
                     seg_perio_corpo sp
               WHERE cf.sta_camp = 0
                 AND cf.ind_camp_act = 1
                 AND TRIM(sp.cod_peri) = TRIM(cf.cod_peri)
                 AND sp.oid_peri = cp.peri_oid_peri
                 AND rownum = 1)
         AND trunc(rcr.fec_ingr) = trunc(SYSDATE)
         AND rcr.esre_oid_esta_recl NOT IN (8, 7) -- Obviamos los parcialmente enviados
         AND ror.esop_oid_esta_oper NOT IN (3, 8) -- Obviamos los parcialmente enviados
         AND EXISTS (SELECT NULL
                FROM rec_linea_opera_recla x
               WHERE x.opre_oid_oper_recl = ror.oid_oper_recl
                 AND (x.timo_oid_tipo_movi = 1 OR
                     (x.timo_oid_tipo_movi = 2 AND
                     x.sopo_oid_soli_posi IS NOT NULL))
                 AND x.ind_esta = 'I')
         AND lor.ind_esta = 'I'
         AND lor.copa_oid_para_gral IS NULL
       ORDER BY rcr.oid_cabe_recl,
                ror.oid_oper_recl,
                lor.timo_oid_tipo_movi;
  
    CURSOR c_cursor2 IS
      SELECT rcr.pais_oid_pais           v_pais_oid_pais,
             rcr.perd_oid_peri_recl      v_perd_oid_peri,
             rcr.sbti_oid_subt_clie      v_sbti_oid_subt_clie,
             rcr.ticl_oid_tipo_clie      v_ticl_oid_tipo_clie,
             rcr.soca_oid_soli_cabe      v_soca_oid_docu_refe,
             rcr.clie_oid_clie           v_clie_oid_clie,
             zta.oid_terr_admi           v_ztad_oid_terr_admi,
             zon.oid_zona                v_zzon_oid_zona,
             zta.terr_oid_terr           v_terr_oid_terr,
             zt.vepo_oid_valo_estr_geop  v_vepo_oid_valo_estr_geop,
             ro.tspa_oid_soli_con_stoc   v_tspa_oid_tipo_soli_pais_env,
             ro.tspa_oid_soli_pais_gene  v_tspa_oid_tipo_soli_pais_dev,
             ro.almc_oid_alma            v_almc_oid_alma,
             ro.ind_anul                 v_ind_anul,
             e.fec_fina                  v_fec_prog_fact,
             ide.tdoc_oid_tipo_docu      v_tdoc_oid_tipo_docu,
             dir.oid_clie_dire           v_cldi_oid_clie_dire,
             lor.timo_oid_tipo_movi      v_timo_oid_tipo_movi,
             lor.val_prec                v_val_prec,
             lor.val_prec_cont           v_val_prec_cont,
             lor.num_unid_recl           v_num_unid_recl,
             lor.prod_oid_prod           v_prod_oid_prod,
             lor.copa_oid_para_gral      v_copa_oid_para_gene,
             lor.panp_oid_para_nive_prem v_panp_oid_para_nive_prem,
             lor.lopa_oid_lote_prem_arti v_lopa_oid_lote_prem_arti,
             rcr.oid_cabe_recl           v_oid_cabe_recl,
             lor.opre_oid_oper_recl      v_opre_oid_oper_recl,
             lor.oid_line_oper_recl      v_oid_line_oper_recl,
             lor.sopo_oid_soli_posi      v_sopo_oid_soli_posi,
             cab.val_nume_soli           v_val_nume_soli,
             m.tido_oid_tipo_docu        v_tido_oid_tipo_docu
        FROM mae_clien             mc,
             rec_cabec_recla       rcr,
             mae_clien_ident       ide,
             rec_opera_recla       ror,
             rec_linea_opera_recla lor,
             mae_produ             mp,
             rec_tipos_opera       rto,
             rec_opera             ro,
             zon_terri_admin       zta,
             zon_terri             zt,
             zon_secci             sec,
             zon_zona              zon,
             ped_solic_cabec       cab,
             mae_tipo_docum        m,
             mae_clien_direc       dir,
             cra_perio             e
       WHERE mc.oid_clie = rcr.clie_oid_clie
         AND rcr.oid_cabe_recl = ror.care_oid_cabe_recl
         AND zta.terr_oid_terr = zt.oid_terr
         AND m.oid_tipo_docu = ide.tdoc_oid_tipo_docu
         AND dir.ind_dire_ppal = 1
         AND dir.ind_elim = 0
         AND rcr.cod_usua_ingr LIKE 'S_%'
         AND dir.clie_oid_clie = mc.oid_clie
         AND ror.oid_oper_recl = lor.opre_oid_oper_recl
         AND ide.clie_oid_clie = mc.oid_clie
         AND e.oid_peri = rcr.perd_oid_peri_recl
         AND ide.val_iden_docu_prin = 1
         AND cab.oid_soli_cabe = rcr.soca_oid_soli_cabe
         AND lor.prod_oid_prod = mp.oid_prod
         AND rto.rope_oid_oper = ro.oid_oper
         AND rto.oid_tipo_oper = ror.tiop_oid_tipo_oper
         AND rcr.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = sec.oid_secc
         AND sec.zzon_oid_zona = zon.oid_zona
         AND EXISTS (SELECT NULL
                FROM rec_solic_opera p,
                     ped_solic_cabec q,
                     rec_opera_recla r
               WHERE p.soca_oid_soli_cabe = q.oid_soli_cabe
                 AND p.opre_oid_oper_recl = r.oid_oper_recl
                 AND r.care_oid_cabe_recl = rcr.oid_cabe_recl
                 AND q.esso_oid_esta_soli != 6)
         AND rcr.perd_oid_peri_recl >=
             (SELECT cp.oid_peri
                FROM bas_ctrl_fact   cf,
                     cra_perio       cp,
                     seg_perio_corpo sp
               WHERE cf.sta_camp = 0
                 AND cf.ind_camp_act = 1
                 AND TRIM(sp.cod_peri) = TRIM(cf.cod_peri)
                 AND sp.oid_peri = cp.peri_oid_peri
                 AND rownum = 1)
         AND trunc(rcr.fec_ingr) = trunc(SYSDATE)
         AND rcr.esre_oid_esta_recl NOT IN (8, 7) -- Obviamos los parcialmente enviados
         AND ror.esop_oid_esta_oper NOT IN (3, 8) -- Obviamos los parcialmente enviados
         AND EXISTS (SELECT NULL
                FROM rec_linea_opera_recla x
               WHERE x.opre_oid_oper_recl = ror.oid_oper_recl
                 AND (x.timo_oid_tipo_movi = 1 OR
                     (x.timo_oid_tipo_movi = 2 AND
                     x.sopo_oid_soli_posi IS NOT NULL))
                 AND x.ind_esta = 'I')
         AND lor.ind_esta = 'I'
         AND lor.copa_oid_para_gral IS NOT NULL
       ORDER BY rcr.oid_cabe_recl,
                ror.oid_oper_recl,
                lor.timo_oid_tipo_movi;
  
    TYPE registrorec IS RECORD(
      oid_pais               rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri_recl          rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_sub_clie           rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie          rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_soli_cabe          rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clien              rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_adm           mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona               zon_zona.oid_zona%TYPE,
      oid_terr               zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geo      zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_soli_con_stoc      rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_soli_pais_gene     rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma               rec_opera.almc_oid_alma%TYPE,
      ind_anul               rec_opera.ind_anul%TYPE,
      fec_fina               cra_perio.fec_fina%TYPE,
      oid_tipo_docu          mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire          mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi1         rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec               rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont          rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl          rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod               rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_matr_fact          rec_linea_opera_recla.mafa_oid_matr_fact%TYPE,
      oid_tipo_ofer          rec_linea_opera_recla.tofe_oid_tipo_ofer%TYPE,
      oid_cabe_recl          rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl          rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl     rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi          rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE,
      ind_devu_fisi_fact     rec_opera.ind_devu_fisi_fact%TYPE);
  
    TYPE registrorectab IS TABLE OF registrorec;
    registrorecord registrorectab;
  
    TYPE registrorec2 IS RECORD(
      oid_pais               rec_cabec_recla.pais_oid_pais%TYPE,
      oid_peri               rec_cabec_recla.perd_oid_peri_recl%TYPE,
      oid_subt_clie          rec_cabec_recla.sbti_oid_subt_clie%TYPE,
      oid_tipo_clie          rec_cabec_recla.ticl_oid_tipo_clie%TYPE,
      oid_docu_refe          rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_clie               rec_cabec_recla.clie_oid_clie%TYPE,
      oid_terr_admi          mae_clien_unida_admin.ztad_oid_terr_admi%TYPE,
      oid_zona               zon_zona.oid_zona%TYPE,
      oid_terr               zon_terri_admin.terr_oid_terr%TYPE,
      oid_valo_estr_geop     zon_terri.vepo_oid_valo_estr_geop%TYPE,
      oid_tipo_soli_pais_env rec_opera.tspa_oid_soli_con_stoc%TYPE,
      oid_tipo_soli_pais_dev rec_opera.tspa_oid_soli_pais_gene%TYPE,
      oid_alma               rec_opera.almc_oid_alma%TYPE,
      ind_anul               rec_opera.ind_anul%TYPE,
      fec_prog_fact          cra_perio.fec_fina%TYPE,
      oid_tipo_docu          mae_clien_ident.tdoc_oid_tipo_docu%TYPE,
      oid_clie_dire2         mae_clien_direc.oid_clie_dire%TYPE,
      oid_tipo_movi2         rec_linea_opera_recla.timo_oid_tipo_movi%TYPE,
      val_prec               rec_linea_opera_recla.val_prec%TYPE,
      val_prec_cont          rec_linea_opera_recla.val_prec_cont%TYPE,
      num_unid_recl          rec_linea_opera_recla.num_unid_recl%TYPE,
      oid_prod               rec_linea_opera_recla.prod_oid_prod%TYPE,
      oid_para_gene          rec_linea_opera_recla.copa_oid_para_gral%TYPE,
      oid_para_nive_prem     rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE,
      oid_lote_prem_arti     rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE,
      oid_cabe_recl          rec_cabec_recla.oid_cabe_recl%TYPE,
      oid_oper_recl          rec_linea_opera_recla.opre_oid_oper_recl%TYPE,
      oid_line_oper_recl     rec_linea_opera_recla.oid_line_oper_recl%TYPE,
      oid_soli_posi          rec_linea_opera_recla.sopo_oid_soli_posi%TYPE,
      val_nume_soli          ped_solic_cabec.val_nume_soli%TYPE,
      tido_oid_tipo_docu_org mae_tipo_docum.tido_oid_tipo_docu%TYPE);
  
    TYPE registrorectab2 IS TABLE OF registrorec2;
    registrorecord2 registrorectab2;
  
    x                             NUMBER;
    y                             NUMBER;
    w_filas                       NUMBER := 1000;
    lnseqsolioper                 NUMBER;
    lnseqsolicabe                 NUMBER;
    v_enviado                     NUMBER;
    v_total                       NUMBER;
    v_tspa_oid_tipo_soli_pais_xxx NUMBER;
    seq_oid_soli_cabe             NUMBER;
    v_fec_cron                    DATE;
    s_val_usua                    VARCHAR2(20);
    seq_val_nume_soli             NUMBER;
  
    oid_form_pago      ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
    ind_perm_unio_sol  ped_tipo_solic_pais.ind_perm_unio%TYPE;
    soci_oid_soci      ped_tipo_solic_pais.soci_oid_soci%TYPE;
    tspa_oid_tipo_soli ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
    tido_oid_tipo_docu ped_tipo_solic_pais.tido_oid_tipo_docu%TYPE;
    val_glos_obse      ped_tipo_solic_pais.val_glos%TYPE;
    ind_pedi_prue      ped_tipo_solic_pais.ind_pedi_prue%TYPE;
    mone_oid_mone      ped_tipo_solic_pais.mone_oid_mone%TYPE;
    acfi_oid_acce_fisi ped_tipo_solic.acce_oid_acce%TYPE;
    sbac_oid_sbac      ped_tipo_solic.sbac_oid_sbac%TYPE;
    clso_oid_clas_soli ped_tipo_solic.clso_oid_clas_soli%TYPE;
    ind_orde_comp      ped_clase_solic.ind_orde_comp%TYPE;
    ind_soli_nega      ped_tipo_solic.ind_soli_nega%TYPE;
  
    oid_tipo_prog      inc_concu_param_gener.ictp_oid_tipo_prog%TYPE;
    oid_conc_tipo_prog inc_concu_param_gener.ictp_oid_conc_tipo_prog%TYPE;
    num_prem           inc_lote_premi_artic.num_prem%TYPE;
    val_codi_vent_fict inc_artic_lote.cod_vent_fict%TYPE;
  
    seq_cod_posi      NUMBER;
    seq_oid_soli_posi NUMBER;
  
    val_codi_vent      pre_ofert_detal.val_codi_vent%TYPE;
    ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;
  
    v_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE := 0;
    v_val_impo_desc_unit_loca ped_solic_posic.val_impo_desc_unit_loca%TYPE := 0;
    v_val_porc_desc           ped_solic_posic.val_porc_desc%TYPE := 0;
  
    lnnumsoliinicio NUMBER;
    lnnumsoliformat NUMBER;
    lnvepooid       NUMBER;
    lnvepooid2      NUMBER;
  
    nom NUMBER;
  
    oid_oper_recl_anterior rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    oid_tipo_movi_anterior rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
  
    oid_oper_recl_anterior2     rec_linea_opera_recla.opre_oid_oper_recl%TYPE := NULL;
    oid_para_gene_anterior      rec_linea_opera_recla.copa_oid_para_gral%TYPE := NULL;
    oid_para_nive_prem_anterior rec_linea_opera_recla.panp_oid_para_nive_prem%TYPE := NULL;
    oid_lote_prem_arti_anterior rec_linea_opera_recla.lopa_oid_lote_prem_arti%TYPE := NULL;
    oid_tipo_movi_anterior2     rec_linea_opera_recla.timo_oid_tipo_movi%TYPE := NULL;
  
    oid_alma ped_tipo_solic_pais.almc_oid_alma%TYPE;
  
  BEGIN
  
    --  lnnumsoliinicio        := psinicio;
    oid_oper_recl_anterior := NULL;
    oid_tipo_movi_anterior := NULL;
  
    /*SELECT COUNT(1)
    INTO lnnumreclamos --- definir esta cantidad
    FROM sto_tmp_docum_digit occ
    WHERE occ.cod_pais = pscodigopais;*/
  
    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              lnnumreclamos);
  
    SELECT to_char(SYSDATE, 'YY') || lpad(lnnumsoliinicio, 8, '0')
      INTO lnnumsoliformat
      FROM dual;
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO registrorecord LIMIT w_filas;
      IF registrorecord.count > 0 THEN
        FOR x IN registrorecord.first .. registrorecord.last
        LOOP
        
          BEGIN
            SELECT md.val_codi_vent v_val_codi_vent,
                   md.oid_deta_ofer v_ofde_oid_deta_ofer
              INTO val_codi_vent,
                   ofde_oid_deta_ofer
              FROM pre_matri_factu mf,
                   pre_ofert_detal md,
                   pre_tipo_ofert  tof
             WHERE md.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
               AND mf.ofde_oid_deta_ofer = md.oid_deta_ofer
               AND md.tofe_oid_tipo_ofer = registrorecord(x).oid_tipo_ofer
               AND mf.oid_matr_fact = registrorecord(x).oid_matr_fact
               AND md.prod_oid_prod = registrorecord(x).oid_prod;
          EXCEPTION
            WHEN OTHERS THEN
              val_codi_vent      := NULL;
              ofde_oid_deta_ofer := NULL;
          END;
        
          --IF registroRecord(x).oid_oper_recl != psOidReclamo THEN
          IF nvl(oid_oper_recl_anterior, 0) || oid_tipo_movi_anterior != registrorecord(x)
            .oid_oper_recl || registrorecord(x).oid_tipo_movi1 OR
             oid_oper_recl_anterior IS NULL THEN
          
            -- x1
            IF oid_oper_recl_anterior IS NOT NULL THEN
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
            
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            -- se cambio de lugar x1
            IF registrorecord(x).oid_tipo_movi1 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                               .oid_soli_pais_gene;
            ELSE
              IF registrorecord(x).oid_tipo_movi1 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord(x)
                                                 .oid_soli_con_stoc;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior IS NOT NULL THEN
            
              SELECT COUNT(*)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
            
              IF v_total = v_enviado THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior;
              END IF;
            END IF;
          
            --    Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   --ts.acce_oid_acce       v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac      v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli v_clso_oid_clas_soli,
                   tsp.almc_oid_alma     v_oid_alma,
                   cs.ind_orde_comp      v_ind_orde_comp,
                   ts.ind_soli_nega      v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   oid_alma,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                BEGIN
                  SELECT tido_oid_tipo_docu_cont
                    INTO tido_oid_tipo_docu
                    FROM fac_tipo_docum
                   WHERE oid_tipo_docu IN
                         ((SELECT ped.tido_oid_tipo_docu
                            FROM ped_solic_cabec ped
                           WHERE ped.oid_soli_cabe IN
                                 (SELECT pos.soca_oid_soli_cabe
                                    FROM ped_solic_posic pos
                                   WHERE pos.oid_soli_posi = registrorecord(x)
                                        .oid_soli_posi)));
                  -- registrorecord(x).tido_oid_tipo_docu_org;
                EXCEPTION
                  WHEN OTHERS THEN
                    SELECT tido_oid_tipo_docu_cont
                      INTO tido_oid_tipo_docu
                      FROM fac_tipo_docum
                     WHERE oid_tipo_docu = registrorecord(x)
                          .tido_oid_tipo_docu_org;
                END;
              ELSE
                tido_oid_tipo_docu := registrorecord(x)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio := lnnumsoliinicio + 1;
            lnnumsoliformat := lnnumsoliformat + 1;
          
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT SYSDATE INTO v_fec_cron FROM dual;
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            nom := registrorecord(x).oid_cabe_recl;
          
            lnvepooid := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord(x)
                                                              .oid_pais,
                                                              registrorecord(x)
                                                              .oid_clien);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord(x).fec_fina,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               decode(oid_alma, NULL, 2001, oid_alma), -- duro!!!!
               15,
               registrorecord(x).oid_tipo_clie,
               NULL,
               registrorecord(x).oid_peri_recl,
               NULL,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clien,
               registrorecord(x).oid_clie_dire,
               registrorecord(x).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord(x).oid_terr,
               registrorecord(x).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(v_fec_cron),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               NULL,
               0,
               0,
               registrorecord(x).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid,
               NULL,
               1,
               NULL,
               1,
               registrorecord(x).oid_sub_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli,
               oid_form_pago,
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord(x).oid_terr_adm,
               NULL,
               21,
               1,
               registrorecord(x).oid_soli_cabe,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord(x).fec_fina, 'ddMMyyyy'),
               NULL,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior := registrorecord(x).oid_oper_recl;
          oid_tipo_movi_anterior := registrorecord(x).oid_tipo_movi1;
        
          IF registrorecord(x).oid_tipo_movi1 = 2 THEN
            registrorecord(x).num_unid_recl := registrorecord(x)
                                               .num_unid_recl * (-1);
          END IF;
        
          IF registrorecord(x).oid_soli_posi IS NOT NULL THEN
          
            BEGIN
            
              SELECT val_prec_cata_unit_loca,
                     val_impo_desc_unit_loca,
                     val_porc_desc
                INTO v_val_prec_cata_unit_loca,
                     v_val_impo_desc_unit_loca,
                     v_val_porc_desc
                FROM ped_solic_posic p
               WHERE oid_soli_posi = registrorecord(x).oid_soli_posi;
            
            EXCEPTION
              WHEN no_data_found THEN
                v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
                v_val_impo_desc_unit_loca := 0;
                v_val_porc_desc           := 0;
            END;
          
          ELSE
            v_val_prec_cata_unit_loca := registrorecord(x).val_prec;
            v_val_impo_desc_unit_loca := 0;
            v_val_porc_desc           := 0;
          END IF;
        
          IF ((registrorecord(x).ind_devu_fisi_fact = 1) OR
             (((registrorecord(x).oid_tipo_movi1 = 2) AND
             (registrorecord(x).oid_soli_posi IS NOT NULL) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)) OR
             ((registrorecord(x).oid_tipo_movi1 = 1) AND
             (registrorecord(x).ind_devu_fisi_fact = 0)))) THEN
          
            SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual; --secuencial de PED_SOLIC_POSIC
          
            seq_cod_posi := seq_cod_posi + 1;
          
            INSERT INTO ped_solic_posic
              (oid_soli_posi,
               cod_posi,
               num_unid_dema,
               num_unid_por_aten,
               val_tasa_impu,
               soca_oid_soli_cabe,
               tpos_oid_tipo_posi,
               prod_oid_prod,
               fopa_oid_form_pago,
               val_prec_cata_unit_loca,
               val_prec_cont_unit_loca,
               val_prec_cata_unit_docu,
               val_prec_conta_unit_docu,
               val_prec_fact_unit_loca,
               val_prec_fact_unit_docu,
               val_prec_sin_impu_unit_loca,
               val_prec_sin_impu_unit_docu,
               val_prec_sin_impu_tota_docu,
               val_impo_desc_unit_loca,
               val_prec_neto_unit_loca,
               val_prec_neto_tota_docu,
               val_prec_neto_unit_docu,
               val_prec_tota_tota_loca,
               val_prec_tota_tota_docu,
               val_impo_impu_unit_loca,
               val_impo_impu_unit_docu,
               val_impo_desc_tota_docu,
               val_impo_impu_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_desc_tota_loca,
               val_prec_tota_unit_loca,
               val_prec_tota_unit_docu,
               val_prec_cont_tota_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_tota_docu,
               val_prec_cont_tota_docu,
               val_prec_cata_tota_loca_unid,
               num_unid_dema_real,
               num_unid_compr,
               val_prec_fact_tota_loca,
               val_prec_fact_tota_docu,
               val_prec_sin_impu_tota_loca,
               val_prec_neto_tota_loca,
               ofde_oid_deta_ofer,
               espo_oid_esta_posi,
               stpo_oid_subt_posi,
               val_codi_vent,
               ind_no_impr,
               val_codi_vent_fict,
               val_porc_desc)
            VALUES
              (seq_oid_soli_posi,
               seq_cod_posi,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               seq_oid_soli_cabe,
               10,
               registrorecord(x).oid_prod,
               NULL,
               v_val_prec_cata_unit_loca, -- registrorecord(x).val_prec, -- poner precio catalogo
               registrorecord(x).val_prec_cont,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               v_val_impo_desc_unit_loca, -- 0, -- poner monto descuento
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               registrorecord(x).num_unid_recl,
               registrorecord(x).num_unid_recl,
               0,
               0,
               0,
               0,
               ofde_oid_deta_ofer,
               4,
               1,
               val_codi_vent,
               NULL,
               NULL,
               v_val_porc_desc -- 0 -- poner porcentaje de descuento
               );
          
          END IF;
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord(x)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;
  
    IF oid_oper_recl_anterior IS NOT NULL THEN
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
      --SELECT PED_SOCA_SEQ INTO lnseqsolicabe FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      SELECT COUNT(1)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior;
    
      IF v_total = v_enviado THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2
         WHERE oid_oper_recl = oid_oper_recl_anterior;
      END IF;
    END IF;
  
    OPEN c_cursor2;
    LOOP
      FETCH c_cursor2 BULK COLLECT
        INTO registrorecord2 LIMIT w_filas;
      IF registrorecord2.count > 0 THEN
        FOR y IN registrorecord2.first .. registrorecord2.last
        LOOP
          BEGIN
            SELECT cpg.ictp_oid_tipo_prog      v_ictp_oid_tipo_prog,
                   cpg.ictp_oid_conc_tipo_prog v_ictp_oid_conc_tipo_prog,
                   la.num_prem                 v_num_prem,
                   al.cod_vent_fict            v_val_codi_vent_fict
              INTO oid_tipo_prog,
                   oid_conc_tipo_prog,
                   num_prem,
                   val_codi_vent_fict
              FROM mae_produ             mp,
                   inc_artic_lote        al,
                   inc_lote_premi_artic  la,
                   inc_premi_artic       pa,
                   inc_param_nivel_premi pn,
                   inc_param_gener_premi pg,
                   inc_concu_param_gener cpg
             WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
               AND al.prod_oid_prod = mp.oid_prod
               AND la.prar_oid_prem_arti = pa.oid_prem_arti
               AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
               AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
               AND pg.copa_oid_para_gral = cpg.oid_para_gral
               AND cpg.oid_para_gral = registrorecord2(y).oid_para_gene
               AND pa.panp_oid_para_nive_prem = registrorecord2(y)
                  .oid_para_nive_prem
               AND al.lopa_oid_lote_prem_arti = registrorecord2(y)
                  .oid_lote_prem_arti
               AND al.prod_oid_prod = registrorecord2(y).oid_prod;
          EXCEPTION
            WHEN no_data_found THEN
              oid_tipo_prog      := NULL;
              oid_conc_tipo_prog := NULL;
              num_prem           := NULL;
              val_codi_vent_fict := NULL;
          END;
        
          IF nvl(oid_oper_recl_anterior2, 0) || oid_para_gene_anterior ||
             oid_para_nive_prem_anterior || oid_lote_prem_arti_anterior ||
             oid_tipo_movi_anterior2 != registrorecord2(y)
            .oid_oper_recl || registrorecord2(y).oid_para_gene || registrorecord2(y)
            .oid_para_nive_prem || registrorecord2(y).oid_lote_prem_arti || registrorecord2(y)
            .oid_tipo_movi2 OR oid_oper_recl_anterior2 IS NULL THEN
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
            
              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 oid_oper_recl_anterior2,
                 lnseqsolicabe,
                 v_tspa_oid_tipo_soli_pais_xxx);
            END IF;
          
            IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
              v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                               .oid_tipo_soli_pais_dev;
            ELSE
              IF registrorecord2(y).oid_tipo_movi2 = 1 THEN
                v_tspa_oid_tipo_soli_pais_xxx := registrorecord2(y)
                                                 .oid_tipo_soli_pais_env;
              END IF;
            END IF;
          
            IF oid_oper_recl_anterior2 IS NOT NULL THEN
            
              SELECT COUNT(1)
                INTO v_enviado
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
                 AND ind_esta = 'E';
            
              SELECT COUNT(*)
                INTO v_total
                FROM rec_linea_opera_recla
               WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
            
              IF v_enviado = v_total THEN
                UPDATE rec_opera_recla
                   SET esop_oid_esta_oper = 2
                 WHERE oid_oper_recl = oid_oper_recl_anterior2;
              
              END IF;
            END IF;
          
            --   Recuperar parametros de v_tspa_oid_tipo_soli_pais_xxx
            SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
                   tsp.ind_perm_unio      v_ind_perm_unio_sol,
                   tsp.soci_oid_soci      v_soci_oid_soci,
                   tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
                   tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu,
                   tsp.val_glos           v_val_glos_obse,
                   tsp.ind_pedi_prue      v_ind_pedi_prue,
                   tsp.mone_oid_mone      v_mone_oid_mone,
                   NULL                   v_acfi_oid_acce_fisi,
                   ts.sbac_oid_sbac       v_sbac_oid_sbac,
                   ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
                   cs.ind_orde_comp       v_ind_orde_comp,
                   ts.ind_soli_nega       v_ind_soli_nega
              INTO oid_form_pago,
                   ind_perm_unio_sol,
                   soci_oid_soci,
                   tspa_oid_tipo_soli,
                   tido_oid_tipo_docu,
                   val_glos_obse,
                   ind_pedi_prue,
                   mone_oid_mone,
                   acfi_oid_acce_fisi,
                   sbac_oid_sbac,
                   clso_oid_clas_soli,
                   ind_orde_comp,
                   ind_soli_nega
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts,
                   ped_clase_solic     cs
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.clso_oid_clas_soli = cs.oid_clas_soli
               AND tsp.oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx;
          
            IF tido_oid_tipo_docu IS NULL THEN
              IF ind_soli_nega = 1 THEN
                SELECT tido_oid_tipo_docu_cont
                  INTO tido_oid_tipo_docu
                  FROM fac_tipo_docum
                 WHERE oid_tipo_docu IN
                       ((SELECT ped.tido_oid_tipo_docu
                          FROM ped_solic_cabec ped
                         WHERE ped.oid_soli_cabe IN
                               (SELECT pos.soca_oid_soli_cabe
                                  FROM ped_solic_posic pos
                                 WHERE pos.oid_soli_posi = registrorecord2(y)
                                      .oid_soli_posi)));
              ELSE
                tido_oid_tipo_docu := registrorecord2(y)
                                      .tido_oid_tipo_docu_org;
              END IF;
            END IF;
          
            s_val_usua := psusuario;
          
            lnnumsoliinicio   := lnnumsoliinicio + 1;
            lnnumsoliformat   := lnnumsoliformat + 1;
            seq_val_nume_soli := lnnumsoliformat;
          
            SELECT ped_soca_seq.nextval INTO lnseqsolicabe FROM dual;
            seq_oid_soli_cabe := lnseqsolicabe;
          
            seq_cod_posi := 0;
          
            lnvepooid2 := gen_pkg_gener.gen_fn_oid_estru_geopo(registrorecord2(y)
                                                               .oid_pais,
                                                               registrorecord2(y)
                                                               .oid_clie);
          
            INSERT INTO ped_solic_cabec
              (oid_soli_cabe,
               fec_prog_fact,
               fec_fact,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               perd_oid_peri,
               soca_oid_soli_cabe,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               val_nume_soli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               fec_repo_falt,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               val_glos_obse,
               val_obse_revi,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               grpr_oid_grup_proc,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               proc_oid_proc,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               val_punt_emis,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq)
            VALUES
              (seq_oid_soli_cabe,
               registrorecord2(y).fec_prog_fact,
               NULL,
               0,
               NULL,
               v_tspa_oid_tipo_soli_pais_xxx,
               NULL,
               1,
               decode(registrorecord2(y).oid_tipo_movi2,
                      1,
                      2001,
                      registrorecord2(y).oid_alma),
               15,
               registrorecord2(y).oid_tipo_clie,
               NULL,
               registrorecord2(y).oid_peri,
               NULL,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie,
               registrorecord2(y).oid_clie_dire2,
               registrorecord2(y).oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               registrorecord2(y).oid_terr,
               registrorecord2(y).oid_zona,
               NULL,
               NULL,
               NULL,
               seq_val_nume_soli,
               s_val_usua,
               0,
               trunc(SYSDATE),
               ind_perm_unio_sol,
               NULL,
               NULL,
               1,
               NULL,
               0,
               NULL,
               NULL,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               0,
               NULL,
               NULL,
               NULL,
               ind_orde_comp,
               ind_pedi_prue,
               1,
               val_glos_obse,
               NULL,
               num_prem,
               0,
               0,
               registrorecord2(y).oid_pais,
               tido_oid_tipo_docu,
               lnvepooid2, --registrorecord2(y).oid_valo_estr_geop,
               NULL,
               1,
               registrorecord2(y).oid_para_gene,
               1,
               registrorecord2(y).oid_subt_clie,
               1,
               tspa_oid_tipo_soli,
               oid_form_pago,
               NULL,
               NULL,
               clso_oid_clas_soli,
               registrorecord2(y).oid_terr_admi,
               NULL,
               21,
               1,
               registrorecord2(y).oid_docu_refe,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               0,
               to_char(registrorecord2(y).fec_prog_fact, 'yyyyMMdd'),
               oid_tipo_prog,
               NULL,
               NULL);
          END IF;
        
          oid_oper_recl_anterior2     := registrorecord2(y).oid_oper_recl;
          oid_para_gene_anterior      := registrorecord2(y).oid_para_gene;
          oid_para_nive_prem_anterior := registrorecord2(y)
                                         .oid_para_nive_prem;
          oid_lote_prem_arti_anterior := registrorecord2(y)
                                         .oid_lote_prem_arti;
          oid_tipo_movi_anterior2     := registrorecord2(y).oid_tipo_movi2;
        
          IF registrorecord2(y).oid_tipo_movi2 = 2 THEN
            registrorecord2(y).num_unid_recl := registrorecord2(y)
                                                .num_unid_recl * (-1);
          END IF;
        
          SELECT ped_sopo_seq.nextval INTO seq_oid_soli_posi FROM dual;
          seq_cod_posi := seq_cod_posi + 1;
        
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr,
             val_codi_vent_fict)
          VALUES
            (seq_oid_soli_posi,
             seq_cod_posi,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             lnseqsolicabe,
             10,
             registrorecord2(y).oid_prod,
             NULL,
             registrorecord2(y).val_prec,
             registrorecord2(y).val_prec_cont,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             registrorecord2(y).num_unid_recl,
             registrorecord2(y).num_unid_recl,
             0,
             0,
             0,
             0,
             NULL,
             4,
             14,
             NULL,
             NULL,
             val_codi_vent_fict);
        
          UPDATE rec_linea_opera_recla l
             SET l.ind_esta                = 'E',
                 l.tspa_oid_tipo_soli_pais = v_tspa_oid_tipo_soli_pais_xxx
           WHERE l.oid_line_oper_recl = registrorecord2(y)
                .oid_line_oper_recl;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor2%NOTFOUND;
    END LOOP;
    CLOSE c_cursor2;
  
    IF oid_oper_recl_anterior2 IS NOT NULL THEN
    
      SELECT rec_soop_seq.nextval INTO lnseqsolioper FROM dual;
    
      INSERT INTO rec_solic_opera
      VALUES
        (lnseqsolioper,
         NULL,
         oid_oper_recl_anterior2,
         lnseqsolicabe,
         v_tspa_oid_tipo_soli_pais_xxx);
    
      SELECT COUNT(1)
        INTO v_enviado
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2
         AND ind_esta = 'E';
    
      SELECT COUNT(*)
        INTO v_total
        FROM rec_linea_opera_recla
       WHERE opre_oid_oper_recl = oid_oper_recl_anterior2;
    
      IF v_enviado = v_total THEN
        UPDATE rec_opera_recla
           SET esop_oid_esta_oper = 2
         WHERE oid_oper_recl = oid_oper_recl_anterior2;
      
      END IF;
    END IF;
  
    /* SELECT COUNT(1)
     INTO r_enviado
     FROM rec_opera_recla o
    WHERE o.care_oid_cabe_recl = psoidreclamo
      AND o.esop_oid_esta_oper = 2;*/
  
    /*SELECT COUNT(1) INTO r_total FROM rec_opera_recla WHERE care_oid_cabe_recl = psoidreclamo;*/
  
    /*IF r_total = r_enviado THEN
      UPDATE rec_cabec_recla
         SET esre_oid_esta_recl = 6,
             fec_ulti_actu      = (SELECT SYSDATE FROM dual)
       WHERE oid_cabe_recl = psoidreclamo;
    END IF;*/
  
    --RETURN lnnumsoliinicio;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_COMPL_RECLA: ' || ls_sqlerrm || ' ' || nom);
  END sto_pr_compl_recla;

  /**************************************************************************
  Descripcion       : sto_pr_pulir_unida_admin
                      Pule la data actualizada de unidad administrativa
  Fecha Creacion    : 08/03/2011
  Autor             : Christian Luque
  ***************************************************************************/
  PROCEDURE sto_pr_pulir_unida_admin(pscodigopais VARCHAR2) IS
    wperiodoactual VARCHAR2(6);
    widcraperiodo  NUMBER;
    widcraperiodo2 NUMBER;
    CURSOR c_cursor IS
    
      SELECT a.clie_oid_clie
        FROM mae_clien_unida_admin a
       WHERE a.clie_oid_clie IN
             (SELECT b1.clie_oid_clie
                FROM mae_clien_unida_admin b1,
                     zon_terri_admin       c1,
                     zon_secci             d1,
                     zon_zona              e1
               WHERE b1.ztad_oid_terr_admi = c1.oid_terr_admi
                 AND c1.zscc_oid_secc = d1.oid_secc
                 AND d1.zzon_oid_zona = e1.oid_zona
                 AND b1.ind_acti = 0
                 AND b1.perd_oid_peri_ini IS NOT NULL
                 AND b1.perd_oid_peri_fin IS NULL)
       GROUP BY a.clie_oid_clie;
  
    TYPE puleunidadminrecord IS RECORD(
      oidcliente mae_clien.oid_clie%TYPE);
    TYPE puleunidadmintab IS TABLE OF puleunidadminrecord;
    puleunidadmin puleunidadmintab;
  
    lv_indactua VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                         'STO_ACTUA_UA'),
                                    'N');
  
  BEGIN
  
    IF lv_indactua <> 'S' THEN
      RETURN;
    END IF;
  
    SELECT cod_peri
      INTO wperiodoactual
      FROM bas_ctrl_fact
     WHERE cod_pais = pscodigopais
       AND sta_camp = '0'
       AND ind_camp_act = '1';
  
    widcraperiodo  := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(wperiodoactual);
    widcraperiodo2 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_fn_calcu_perio(wperiodoactual,
                                                                                     -1));
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO puleunidadmin LIMIT rows;
      IF puleunidadmin.count > 0 THEN
        FOR i IN puleunidadmin.first .. puleunidadmin.last
        LOOP
        
          DELETE FROM mae_clien_unida_admin a
           WHERE a.clie_oid_clie = puleunidadmin(i).oidcliente
             AND a.perd_oid_peri_ini = widcraperiodo
             AND a.perd_oid_peri_fin = widcraperiodo;
        
          UPDATE mae_clien_unida_admin b
             SET b.ind_acti          = 0,
                 b.perd_oid_peri_fin = widcraperiodo2
           WHERE b.clie_oid_clie = puleunidadmin(i).oidcliente
             AND b.perd_oid_peri_ini IS NOT NULL
             AND b.perd_oid_peri_fin = widcraperiodo;
        
          UPDATE mae_clien_unida_admin b
             SET b.ind_acti          = 1,
                 b.perd_oid_peri_ini = widcraperiodo
           WHERE b.clie_oid_clie = puleunidadmin(i).oidcliente
             AND b.perd_oid_peri_ini IS NOT NULL
             AND b.perd_oid_peri_fin IS NULL;
        
        END LOOP;
      END IF;
    
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_PULIR_UNIDA_ADMIN: ' ||
                              ls_sqlerrm);
  END sto_pr_pulir_unida_admin;

  /**************************************************************************
  Descripcion       : sto_pr_actua_limit_venta
                      Actualiza la PED_SOLIC_POSIC con los detalles de pedidos
                      que han sido marcados en la validacion de limite de venta
                      focalizada por consultora
  Fecha Creacion    : 24/05/2011
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_actua_limit_venta
  (
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
  
    CURSOR c_validacion IS
      SELECT ca.soca_oid_soli_cabe_refe,
             de.prod_oid_prod,
             de.des_prod,
             de.val_unid_dema_lvfo,
             ca.clie_oid_clie,
             de.val_unid_dem
        FROM int_solic_conso_cabec ca,
             int_solic_conso_detal de,
             sto_proce_docum_digit dd
       WHERE de.sec_nume_docu = dd.sec_nume_docu
         AND de.num_lote = dd.num_lote
         AND dd.num_proc = psnumeroproceso
         AND dd.cod_tipo_docu = pscodtipodocu
         AND ca.cod_pais = de.cod_pais
         AND ca.cod_peri = de.cod_peri
         AND ca.cod_clie = de.cod_clie
         AND ca.num_lote = de.num_lote
         AND de.ind_limi_vent_foca = 1;
  
    TYPE validacionrecord IS RECORD(
      soca_oid_soli_cabe_refe int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE,
      prod_oid_prod           int_solic_conso_detal.prod_oid_prod%TYPE,
      des_prod                int_solic_conso_detal.des_prod%TYPE,
      val_unid_dema_lvfo      int_solic_conso_detal.val_unid_dema_lvfo%TYPE,
      clie_oid_clie           int_solic_conso_cabec.clie_oid_clie%TYPE,
      val_unid_dem            int_solic_conso_detal.val_unid_dem%TYPE);
  
    TYPE validaciontab IS TABLE OF validacionrecord;
    validacion validaciontab;
  
    w_filas    NUMBER := 5000;
    oidmensaje msg_mensa.oid_mens%TYPE;
  
  BEGIN
  
    SELECT oid_mens
      INTO oidmensaje
      FROM msg_mensa m
     WHERE m.cod_mens = 'PED05';
  
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO validacion LIMIT w_filas;
      IF validacion.count > 0 THEN
        FOR i IN validacion.first .. validacion.last
        LOOP
          DELETE msg_buzon_mensa
           WHERE clie_oid_clie = validacion(i).clie_oid_clie
             AND mens_oid_mens = oidmensaje;
        
          UPDATE ped_solic_posic a
             SET ind_limi_vent      = 1,
                 num_unid_dema_real = validacion(i).val_unid_dema_lvfo,
                 num_unid_por_aten  = validacion(i).val_unid_dema_lvfo,
                 num_unid_compr     = validacion(i).val_unid_dema_lvfo
           WHERE a.soca_oid_soli_cabe = validacion(i)
                .soca_oid_soli_cabe_refe
             AND a.prod_oid_prod = validacion(i).prod_oid_prod;
        
          INSERT INTO msg_buzon_mensa
            (oid_buzo_mens,
             num_secu,
             mens_oid_mens,
             clie_oid_clie,
             modu_oid_modu_orig,
             dato_vari_01,
             dato_vari_02,
             fec_grab,
             ind_list_cons,
             ind_acti)
          VALUES
            (msg_bume_seq.nextval,
             msg_bum2_seq.nextval,
             oidmensaje,
             validacion(i).clie_oid_clie,
             1,
             validacion(i).prod_oid_prod || validacion(i).des_prod,
             validacion(i).val_unid_dem - validacion(i).val_unid_dema_lvfo,
             SYSDATE,
             0,
             1);
        
        END LOOP;
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ACTUA_LIMIT_VENTA: ' ||
                              ls_sqlerrm);
  END sto_pr_actua_limit_venta;

  /**************************************************************************
  Descripcion       : sto_pr_actua_boleta_elect
                      Actualiza las boletas electronicas en base a un cdr
  
  Fecha Creacion    : 17/04/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_actua_boleta_elect(psoidoperarecla NUMBER) IS
  
    CURSOR c_cursor IS
      SELECT ca.oid_line_oper_recl,
             ca.sopo_oid_soli_posi,
             ca.num_unid_recl
        FROM rec_linea_opera_recla ca
       WHERE ca.opre_oid_oper_recl = psoidoperarecla
         AND ca.timo_oid_tipo_movi = 2;
  
    TYPE t_oid_line_oper_recl IS TABLE OF rec_linea_opera_recla.oid_line_oper_recl%TYPE;
    TYPE t_sopo_oid_soli_posi IS TABLE OF rec_linea_opera_recla.sopo_oid_soli_posi%TYPE;
    TYPE t_num_unid_recl IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;
  
    v_oid_line_oper_recl t_oid_line_oper_recl;
    v_sopo_oid_soli_posi t_sopo_oid_soli_posi;
    v_num_unid_recl      t_num_unid_recl;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_oid_line_oper_recl,
             v_sopo_oid_soli_posi,
             v_num_unid_recl LIMIT rows;
      EXIT WHEN v_oid_line_oper_recl.count = 0;
    
      FORALL i IN 1 .. v_oid_line_oper_recl.count
      
        UPDATE ped_bolet_elect_histo bol
           SET bol.oid_line_recl = v_oid_line_oper_recl(i),
               bol.ind_recl      = 1 -----'S'
         WHERE bol.oid_soli_posi_pedi = v_sopo_oid_soli_posi(i)
           AND bol.ind_recl NOT IN (1, 2) ----('S','P')
           AND bol.num_unid <> 0
           AND rownum <= v_num_unid_recl(i);
    
    END LOOP;
    CLOSE c_cursor;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ACTUA_BOLETA_ELECT: ' ||
                              ls_sqlerrm);
  END sto_pr_actua_boleta_elect;

  /**************************************************************************
  Descripcion       : sto_pr_genera_consolidado
                      Genera el Consolidado de un Reclamo
  Fecha Creacion    : 04/07/2012
  Autor             : Sandro Quintana Aponte
  
       pstipooperacion = 'A'  Es abono
                         'C'  es cargo
  ***************************************************************************/
  PROCEDURE sto_pr_genera_consolidado
  (
    p_oidsolic         NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2,
    psparametroconso   VARCHAR2,
    pstipooperacion    VARCHAR2
  ) IS
  
    lnoidsoliconso   NUMBER := 0;
    lsparametrobolec sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    ---- Parametros
    lsparametrobolec := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_BOLELEC');
  
    --- Genera Consolidado de una solicitud
    lnoidsoliconso := fac_pkg_proc.fac_fn_genera_consolidado(p_oidsolic,
                                                             psfechafacturacion,
                                                             pscodigopais);
  
    ---- insert into BAS_TMP_REPOR_LOG (nom_repo,des_log) values(lnoidsoliconso,'sto_pr_genera_consolidado lnoidsoliconso');
  
    --- Realiza los calculos
    IF pstipooperacion = 'A' THEN
      ---- Abono
      IF psparametroconso = '1' THEN
        ---- Otros
        fac_pkg_proc.fac_pr_calculo_consolidado_r(lnoidsoliconso);
      ELSE
        IF psparametroconso = '2' THEN
          ---- Chile
          fac_pkg_proc.fac_pr_calculo_consolidado2_r(lnoidsoliconso);
        ELSE
          fac_pkg_proc.fac_pr_calculo_consolidado3_r(lnoidsoliconso);
        END IF;
      END IF;
    ELSE
      IF psparametroconso = '1' THEN
        ---- Otros
        fac_pkg_proc.fac_pr_calculo_consolidado(lnoidsoliconso);
      ELSE
        IF psparametroconso = '2' THEN
          ---- Chile
          fac_pkg_proc.fac_pr_calculo_consolidado2(lnoidsoliconso);
        ELSE
          fac_pkg_proc.fac_pr_calculo_consolidado3(lnoidsoliconso);
        END IF;
      END IF;
    END IF;
  
    fac_pkg_proc.fac_pr_genera_doclega(lnoidsoliconso);
    fac_pkg_proc.fac_pr_genera_ruv(lnoidsoliconso);
    fac_pkg_proc.fac_pr_genera_ccc(lnoidsoliconso);
  
    ---- Genera Boletas Electronicas por AbonoNota de Credito
    IF (lsparametrobolec = '1' AND pstipooperacion = 'A') THEN
    
      fac_pkg_proc.fac_pr_genera_bol_elec_rec(lnoidsoliconso);
    
    END IF;
  
    ---- Genera Boletas Electronicas por cargo
    IF (lsparametrobolec = '1' AND pstipooperacion = 'C') THEN
    
      fac_pkg_proc.fac_pr_genera_bol_elec(lnoidsoliconso);
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GENERA_CONSOLIDADO: ' ||
                              ls_sqlerrm);
  END sto_pr_genera_consolidado;

  /**************************************************************************
   Descripcion       : Actualiza el indicador de Facturacion
   Fecha Creacion    : 18/02/2009
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_actua_matri_factu
  (
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
  
    CURSOR c_detalle IS
      SELECT DISTINCT det.ofde_oid_deta_ofer
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ,
             pre_ofert_detal       b
       WHERE det.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodtipodocu;
  
    TYPE t_ofde_oid_deta_ofer IS TABLE OF int_solic_conso_detal.ofde_oid_deta_ofer%TYPE;
    v_ofde_oid_deta_ofer t_ofde_oid_deta_ofer;
  
  BEGIN
  
    OPEN c_detalle;
    LOOP
      FETCH c_detalle BULK COLLECT
        INTO v_ofde_oid_deta_ofer LIMIT rows;
    
      IF v_ofde_oid_deta_ofer.count > 0 THEN
        FOR i IN v_ofde_oid_deta_ofer.first .. v_ofde_oid_deta_ofer.last
        LOOP
          sto_pr_updat_matri_factu(v_ofde_oid_deta_ofer(i));
        END LOOP;
      END IF;
    
      EXIT WHEN c_detalle%NOTFOUND;
    
    END LOOP;
  
    CLOSE c_detalle;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ACTUA_MATRI_FACTU: ' ||
                              ls_sqlerrm);
  END sto_pr_actua_matri_factu;
  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 23/07/2013
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_matri_factu(pnoiddetaofer NUMBER) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
  BEGIN
  
    UPDATE pre_matri_factu mf
       SET mf.ind_matr_fact = 1
     WHERE (mf.ind_matr_fact = 0 OR mf.ind_matr_fact IS NULL)
       AND mf. ofde_oid_deta_ofer = pnoiddetaofer;
    COMMIT;
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_UPDAT_MATRI_FACTU: ' ||
                              ls_sqlerrm);
    
  END sto_pr_updat_matri_factu;
END sto_pkg_envio_valid_sicc;
/
