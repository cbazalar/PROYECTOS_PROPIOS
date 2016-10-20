CREATE OR REPLACE PACKAGE INT_PKG_COB IS

   gv_des_log                      VARCHAR2(200);
   gc_cod_modu                   CONSTANT CHAR(3):='COB';

   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(1500);
   W_FILAS      NUMBER:=1000;

PROCEDURE INT_PR_COB_GENER_INFCO_CORPO(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE INT_PR_COB_GENER_CARTE_CORPO(
  p_fec_proc                       IN    VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE INT_PR_COB_GENER_SALDO_CORPO(
  p_fec_proc                       IN    VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE INT_PR_COB_GENER_CARTE_COBRA(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_SALDO_COBRA(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_CARTE(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_SALDO(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR1(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_CAMPA_OCR1(
  p_cod_cobr                       IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR1(
  p_cod_cobr                       IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_asig                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR2(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE INT_PR_COB_GENER_INFOR_COBRA(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Cobranza y
                      saldos pendiente
  Fecha Creacion    : 26/04/2011
  Autor             : Jose Luis Rodriguez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_cobra_saldo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Transaccion
                      Cobranza
  Fecha Creacion    : 27/04/2011
  Autor             : Nicolas Lopez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_trans_cobra(
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2,
    psanhio           VARCHAR2,
    psmes             VARCHAR2
 );

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Cobranza y
                      Periodo por Zona
  Fecha Creacion    : 25/07/2011
  Autor             : Jose Luis Rodriguez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_cobra_perzo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Recuperacion Cobranza
                      por cobrador
  Fecha Creacion    : 04/09/2012
  Autor             : Sergio Buchelli
***************************************************************************/
 PROCEDURE int_pr_cob_gener_recup_cobra
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2
  );
/***************************************************************************
  Descripcion       : Procedimiento de Envio de Informacion a Proveedores
                      de Cobranza
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE INT_PR_COB_GENER_INFOR_PROVE(
      p_cod_usua                   VARCHAR2);

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Entrega de Cartera
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_entre_carte
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Actualizacion de Saldos
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_actua_saldo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Retroalimentacion
                      de Gestiones
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_retro_gesti
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Control de Registros
                      Enviados
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_contr_regis
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el Envio de Archivos a OCR1
  Fecha Creacion    : 14/02/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
 PROCEDURE int_pr_cob_gener_archi_ocr1
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el Envio de Archivos a OCR2
  Fecha Creacion    : 14/02/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
 PROCEDURE int_pr_cob_gener_archi_ocr2
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2
  );

  /**********************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de la Gestion Cobranza de Terceros
                      a SICC
  Fecha Creacion    : 28/02/2013
  Autor             : L. Sebastian Guerra Ch.
  **********************************************************************************/
 PROCEDURE int_pr_cob_recep_gesti_cobra
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    pscodigousuario           VARCHAR2
  );

 /****************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control Registros Recibidos
                      a SICC
  Fecha Creacion    : 28/02/2013
  Autor             : L. Sebastian Guerra Ch.
  ****************************************************************************************/
 PROCEDURE int_pr_cob_recep_contr_regis
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    pscodigousuario           VARCHAR2,
    pslistaarchivos           VARCHAR2
  );

    /************************************************************************
     Descripcion : Funcion que obtiene el el nombre del archivo a partir de una lista
     Creado     : Sebastian Guerra
     Fecha      : 11/04/2013
       Parametros:
         p_list  Cadena de nombres de archivos separados por comas
         p_nombre   Nombre de archivo a buscaqr en la cadena
    ************************************************************************/
    FUNCTION int_fn_cob_busca_nombr_archi
    (
        p_list varchar2,
        p_nombre varchar2
    )
    RETURN varchar2;

 /****************************************************************************************
  Descripcion       : Genera Interfaz de Recuperaci?n Cobranza FFVV Datamart.

  Fecha Creacion    : 25/07/2013
  Autor             : Yahir Rivas Luna.
  ****************************************************************************************/

PROCEDURE INT_PR_COB_ENVIO_RECOB_FFVV
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2
  );

 PROCEDURE INT_PR_COB_GENER_INFOR_DATAC(
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER);

 PROCEDURE INT_PR_COB_GENER_INFOR_ACOVE(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);
  
PROCEDURE INT_PR_COB_ENVIO_TRANS_UNION;
  
/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz ENVIAR INFORMACION 
                      TRANSUNION
  Fecha Creacion    : 16/10/2015
  Autor             : Gonzalo Huertas
***************************************************************************/
  PROCEDURE int_pr_cob_gener_infor_trans(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2); 
                                         
 /***************************************************************************
   Descripcion       : Genera Interfaz SAP -Enviar Cobranza por Etapas
  Fecha Creacion    : 19/10/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE INT_PR_COB_ENVIA_ETAPA_COSAP(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2);                                      
 
 /***************************************************************************
   Descripcion       : Genera Interfaz - Fecha de Cierre de Facturacion Campaña
  Fecha Creacion    : 19/10/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE INT_PR_COB_CIERR_FACTU_CAMPA(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2);
 

END INT_PKG_COB;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_COB IS

 gc_cod_modu_cob                   CONSTANT CHAR(3):= 'COB';
 gc_cod_proc_gene_info_cobr        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='8400';
 gc_cod_proc_gene_info_cart        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='8401';
 gc_cod_proc_gene_info_sald        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='8402';
 gc_cod_proc_gene_cart_corp        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='8403';
 gc_cod_proc_gene_sald_corp        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='8404';
 gc_cod_proc_gene_info_datc        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='9101';
 gc_cod_proc_gene_info_acov        CONSTANT fin_proce_ejecu.cod_proc%TYPE:='ACOVEDI';

 gc_cod_proc_asig_auto             CONSTANT   VARCHAR2(8):='20010910';
 gc_cod_usua                       CONSTANT   VARCHAR2(15):='COBRANZAS';

-- Excepciones --

 gv_reco_trac                      FIN_PKG_GENER.error_rt;
 e_vali_ejec_dow                   EXCEPTION;

 PROCEDURE INT_PR_COB_GENER_INFCO_CORPO(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_cod_erro                      VARCHAR2(4000);
  lv_fec_proc                      VARCHAR2(10);
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_cobr;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Envio de Informacion a Cobradores';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF p_fec_proc IS NULL THEN
   lv_fec_proc := TO_CHAR(SYSDATE,'DD/MM/YYYY');
  ELSE
   lv_fec_proc := p_fec_proc;
  END IF;

  gv_des_log := 'Generando Carteras Corporativas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  INT_PR_COB_GENER_CARTE_CORPO(lv_fec_proc,p_cod_usua);

  COMMIT;

  gv_des_log := 'Generando Saldos Corporativos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  INT_PR_COB_GENER_SALDO_CORPO(lv_fec_proc,p_cod_usua);


  gv_des_log := 'Enviando por email el seguimiento de cobranza';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  BEGIN
   COB_PKG_PROCE.COB_PR_GENER_INFOR_SEGUI_COBRA(lv_fec_proc);
  EXCEPTION
   WHEN OTHERS THEN
      gv_des_log := 'Errores con el envio del email de seguimiento';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  END;

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  COMMIT;

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log := 'Fin del Proceso de Manera Erronea';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

 END INT_PR_COB_GENER_INFCO_CORPO;

 PROCEDURE INT_PR_COB_GENER_CARTE_CORPO(
  p_fec_proc                       IN    VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

 -- Cursor de los Cobradores --
 CURSOR c_cobr
 IS
  SELECT
   d.cod_usua_cobr,
   u.val_nomb_usua_cobr
  FROM
   cob_detal_asign_carte d,
   cob_cabec_asign_carte c,
   cob_usuar_cobra_pais u
  WHERE d.cod_cart = c.cod_cart
    AND u.ind_envi_info_cobr = 1
    AND u.ind_inte_corp = 1
    AND d.cod_usua_cobr = u.cod_usua_cobr
  GROUP BY d.cod_usua_cobr,u.val_nomb_usua_cobr;

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  COB_PKG_PROCE.COB_PR_ASIGN_CARTE_AUTOM(lv_cod_pais,lv_cod_soci,gc_cod_modu_cob,gc_cod_proc_asig_auto,lv_cod_erro);

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_cart_corp;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion de Cartera Corporativa : '  || p_fec_proc;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FOR v_cobr IN c_cobr LOOP

   gv_des_log := '   Cobrador Codigo ' || v_cobr.cod_usua_cobr || ' ' || v_cobr.val_nomb_usua_cobr;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := '   Enviando la Cartera';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_COB_GENER_CARTE_COBRA(v_cobr.cod_usua_cobr,p_fec_proc,p_cod_usua);

   gv_des_log := '   ***************';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END LOOP;

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_CARTE_CORPO;

 PROCEDURE INT_PR_COB_GENER_SALDO_CORPO(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

 CURSOR c_cobr_sald
 IS
  SELECT
   d.cod_usua_cobr,
   u.val_nomb_usua_cobr
  FROM
   cob_detal_asign_carte d,
   cob_cabec_asign_carte c,
   cob_usuar_cobra_pais u
  WHERE d.cod_cart = c.cod_cart
    AND u.ind_envi_info_cobr = 1
    AND c.ind_envi_liqu = 0
    AND u.ind_inte_corp = 1
    AND d.cod_usua_cobr = u.cod_usua_cobr
   GROUP BY d.cod_usua_cobr,u.val_nomb_usua_cobr;


  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;

  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  COB_PKG_PROCE.COB_PR_ACTUA_CARTE(lv_cod_pais,lv_cod_soci,p_cod_usua);

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_sald_corp;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion de Saldos Corporativo ' || p_fec_proc;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FOR v_cobr_sald IN c_cobr_sald LOOP

   gv_des_log := '   Cobrador Codigo ' || v_cobr_sald.cod_usua_cobr || ' ' || v_cobr_sald.val_nomb_usua_cobr;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := '   Enviando los Saldos';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_COB_GENER_SALDO_COBRA(v_cobr_sald.cod_usua_cobr,p_fec_proc,p_cod_usua);

   gv_des_log := '   ***************';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END LOOP;

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_SALDO_CORPO;

 PROCEDURE INT_PR_COB_GENER_CARTE_COBRA(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)

 IS

  lc_cod_inte                      CONSTANT CHAR(7):='COB-101';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_reg_cont                      cob_contro_envio_infor_cobra%ROWTYPE;
  lv_fec_proc                      DATE;

  lv_val_serv_ftp                  cob_usuar_cobra_pais.val_serv_ftp%TYPE;
  lv_val_puer_ftp                  cob_usuar_cobra_pais.val_puer_ftp%TYPE;
  lv_val_usua_ftp                  cob_usuar_cobra_pais.val_usua_ftp%TYPE;
  lv_val_clav_ftp                  cob_usuar_cobra_pais.val_clav_ftp%TYPE;
  lv_val_dire_ftp                  cob_usuar_cobra_pais.val_dire_ftp%TYPE;
  lv_val_dire_rece_ftp             cob_usuar_cobra_pais.val_dire_rece_ftp%TYPE;

  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_cart;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion de Cartera de ' || p_cod_usua_cobr;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  lv_fec_proc  := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  SELECT 'cartera_' || TO_CHAR(lv_fec_proc,'YYMMDD') || '.TXT'
  INTO lv_nom_arch
  FROM dual;

  gv_des_log := 'Nombre de Archivo : ' || lv_nom_arch ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Seleccionado las carteras --
  DELETE FROM cob_tmp_carte_envio;

  gv_des_log := 'Seleccionado carteras de Envio';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO cob_tmp_carte_envio
   SELECT
     cab.cod_pais,
     cab.cod_soci,
     cab.cod_etap_deud,
     cab.cod_regi,
     cab.cod_zona,
     cab.cod_peri,
     cab.cod_cart
    FROM
     cob_cabec_asign_carte   cab
    WHERE cab.ind_envi_cart = 0;

  gv_des_log := 'Se seleccionaron ' || SQL%ROWCOUNT || ' carteras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  DELETE FROM cob_inter_carte_corpo;

  gv_des_log := 'Cargando la cartera';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO cob_inter_carte_corpo
   SELECT
    carte.cod_usua_cobr,
    carte.cod_etap_deud,
    carte.cod_peri ,
    carte.cod_regi_clie cod_regi,
    carte.cod_zona_clie cod_zona,
    carte.cod_secc,
    carte.cod_terr,
    carte.cod_clie,
    carte.cod_digi_ctrl,
    ' ' cod_prog_reco,
    NVL((TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2)),' ') nom_clie,
    NVL((TRIM(mc.val_ape1)),' ') val_ape1,
    NVL((TRIM(mc.val_ape2)),' ')val_ape2,
    carte.cod_tipo_docu_iden,
    carte.num_docu_iden,
    TO_CHAR(mcda.fec_naci,'DD/MM/YYYY'),
    ROUND(((TRUNC(SYSDATE) - mcda.fec_naci)/365),0) val_edad,
    carte.num_tele_fijo,
    carte.num_tele_trab,
    carte.num_tele_movi,
    carte.cod_camp_prim_pedi,
    carte.val_mail val_mail,
    SUBSTR(carte.des_dpto,1,50),
    SUBSTR(carte.des_prov,1,50),
    SUBSTR(carte.des_dist,1,50),
    SUBSTR(carte.des_urba,1,50),
    carte.val_dire val_dire ,
    carte.val_dire_refe val_dire_refe,
    TO_CHAR(carte.fec_docu,'DD/MM/YYYY'),
    TO_CHAR(carte.fec_venc,'DD/MM/YYYY'),
    TO_CHAR(carte.fec_asig,'DD/MM/YYYY'),
    TO_CHAR(carte.fec_cier,'DD/MM/YYYY'),
    ROUND(TRUNC(SYSDATE) - TRUNC(carte.fec_docu),0) num_dias_mora,
    TO_CHAR(carte.num_iden_cuot,'9999999999999') num_bole_desp,
    TO_CHAR(carte.imp_deud_orig,'9999999999999.99') imp_deud_orig,
    TO_CHAR((carte.imp_deud_orig - carte.imp_deud_asig),'9999999999999.99')  imp_abon_ante,
    TO_CHAR(carte.imp_deud_asig,'9999999999999.99') imp_deud_asig,
    TO_CHAR(carte.imp_deud_canc,'9999999999999.99') imp_deud_canc,
    TO_CHAR(carte.imp_deud_pend,'9999999999999.99') imp_deud_pend,
    ROUND(carte.imp_deud_canc/carte.imp_deud_asig*100,2) imp_porc_recu,
    accio.des_acci_cobr,
    NVL(FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,1),' ') val_refe_fami,
    NVL(FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,2),' ') val_refe_nofa,
    NVL(FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,3),' ') val_refe_aval,
    NVL(FIN_PKG_GENER.FIN_FN_OBTIE_REFER_OBSER_CLIEN(carte.cod_clie),' ') val_refe_obse,
    NULL
   FROM
    cob_detal_asign_carte carte,
    cob_accio_cobra_pais accio,
    mae_clien mc,
    mae_clien_datos_adici mcda
   WHERE carte.oid_clie = mc.oid_clie
     AND carte.cod_ulti_gest_cobr = accio.cod_acci_cobr
     AND carte.cod_etap_deud = accio.cod_etap_deud
     AND mc.oid_clie = mcda.clie_oid_clie
     AND carte.cod_usua_cobr = p_cod_usua_cobr
     AND carte.fec_asig = lv_fec_proc;

  gv_des_log := 'Se cargaron ' || SQL%ROWCOUNT || ' registros';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Insertando la informacion en el control de envio';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_reg_cont.fecha := lv_fec_proc;
  lv_reg_cont.num_lote := lv_num_lote;
  lv_reg_cont.cod_usua_cobra_pais := p_cod_usua_cobr;
  lv_reg_cont.cod_inte := lc_cod_inte;
  lv_reg_cont.val_nomb_archi := lv_nom_arch;

  INSERT INTO cob_contro_envio_infor_cobra VALUES lv_reg_cont;

  SELECT
   c.val_serv_ftp,
   c.val_puer_ftp,
   c.val_usua_ftp,
   c.val_clav_ftp,
   c.val_dire_ftp,
   c.val_dire_rece_ftp
  INTO
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp,
   lv_val_dire_rece_ftp
  FROM cob_usuar_cobra_pais c
  WHERE c.cod_usua_cobr = p_cod_usua_cobr;

  gv_des_log := ' Datos del envio FTP Servidor : ' || lv_val_serv_ftp ||
                ' Directo Recepcion FTP : ' || lv_val_dire_rece_ftp ||
                ' Nombre de Archivo : ' || lv_nom_arch;

  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Generando los archivos de la interface';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   lv_val_dire_rece_ftp,
   lv_nom_arch,
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp);

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_CARTE_COBRA;

 PROCEDURE INT_PR_COB_GENER_SALDO_COBRA(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(7):='COB-102';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_reg_cont                      cob_contro_envio_infor_cobra%ROWTYPE;
  lv_dias_grac                     cob_param_gener.Val_Para%TYPE;
  lv_fec_proc                      DATE;

  lv_val_serv_ftp                  cob_usuar_cobra_pais.val_serv_ftp%TYPE;
  lv_val_puer_ftp                  cob_usuar_cobra_pais.val_puer_ftp%TYPE;
  lv_val_usua_ftp                  cob_usuar_cobra_pais.val_usua_ftp%TYPE;
  lv_val_clav_ftp                  cob_usuar_cobra_pais.val_clav_ftp%TYPE;
  lv_val_dire_ftp                  cob_usuar_cobra_pais.val_dire_ftp%TYPE;
  lv_val_dire_rece_ftp             cob_usuar_cobra_pais.val_dire_rece_ftp%TYPE;

  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_sald;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion de Saldos de ' || p_cod_usua_cobr;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  SELECT 'saldos_' || TO_CHAR(lv_fec_proc,'YYMMDD') || '.TXT'
  INTO lv_nom_arch
  FROM dual;

  gv_des_log := 'Nombre de Archivo : ' || lv_nom_arch;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Seleccionado las carteras --
  DELETE FROM cob_tmp_carte_envio;

  INSERT INTO cob_tmp_carte_envio
   SELECT
     cab.cod_pais,
     cab.cod_soci,
     cab.cod_etap_deud,
     cab.cod_regi,
     cab.cod_zona,
     cab.cod_peri,
     cab.cod_cart
    FROM
     cob_cabec_asign_carte   cab
    WHERE 1=1;
      --AND cab.ind_envi_cart = 1
      --AND cab.ind_envi_liqu = 1;

  DELETE FROM cob_inter_saldo_corpo;

  gv_des_log := 'Insertando Saldos a actualizar';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_dias_grac := TO_NUMBER(NVL(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DiasGraciaEnvioSaldos'),0));

  INSERT INTO cob_inter_saldo_corpo
   SELECT
    d.cod_clie,
    d.num_iden_cuot,
    TO_CHAR(d.fec_cier,'DD/MM/YYYY') fec_cier,
    TO_CHAR(d.imp_deud_canc,'9999999999999.99') imp_deud_canc,
    TO_CHAR(d.imp_deud_pend,'9999999999999.99') imp_deud_pend,
    TO_CHAR(d.fec_ult_pago_banc,'DD/MM/YYYY') fec_ult_pago_banc
   FROM cob_detal_asign_carte d
   WHERE d.cod_usua_cobr = p_cod_usua_cobr
     AND d.fec_cier >= lv_fec_proc - lv_dias_grac;

  gv_des_log := 'Se van a actualizar ' || SQL%ROWCOUNT || ' saldos';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_reg_cont.fecha := lv_fec_proc;
  lv_reg_cont.num_lote := lv_num_lote;
  lv_reg_cont.cod_usua_cobra_pais := p_cod_usua_cobr;
  lv_reg_cont.cod_inte := lc_cod_inte;
  lv_reg_cont.val_nomb_archi := 'SALDOS';

  /*
  SELECT COUNT(*), SUM(val_sald_actu)
  INTO lv_reg_cont.val_regi_proc,lv_reg_cont.val_impo_gene
  FROM cob_inter_gener_saldo;
  */

  INSERT INTO  cob_contro_envio_infor_cobra VALUES lv_reg_cont;

  SELECT
   c.val_serv_ftp,
   c.val_puer_ftp,
   c.val_usua_ftp,
   c.val_clav_ftp,
   c.val_dire_ftp,
   c.val_dire_rece_ftp
  INTO
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp,
   lv_val_dire_rece_ftp
  FROM cob_usuar_cobra_pais c
  WHERE c.cod_usua_cobr = p_cod_usua_cobr;

   gv_des_log := ' Datos del envio FTP Servidor : ' || lv_val_serv_ftp ||
                ' Directo Recepcion FTP : ' || lv_val_dire_rece_ftp ||
                ' Nombre de Archivo : ' || lv_nom_arch;

  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Generando los archivos de la interface';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   lv_val_dire_rece_ftp,
   lv_nom_arch,
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp);

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_SALDO_COBRA;

 PROCEDURE INT_PR_COB_GENER_INFOR_CARTE(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)

 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-1';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_reg_cont                      cob_contro_envio_infor_cobra%ROWTYPE;

  lv_val_serv_ftp                  cob_usuar_cobra_pais.val_serv_ftp%TYPE;
  lv_val_puer_ftp                  cob_usuar_cobra_pais.val_puer_ftp%TYPE;
  lv_val_usua_ftp                  cob_usuar_cobra_pais.val_usua_ftp%TYPE;
  lv_val_clav_ftp                  cob_usuar_cobra_pais.val_clav_ftp%TYPE;
  lv_val_dire_ftp                  cob_usuar_cobra_pais.val_dire_ftp%TYPE;
  lv_val_dire_rece_ftp             cob_usuar_cobra_pais.val_dire_rece_ftp%TYPE;

  lv_cod_erro                      VARCHAR2(4000);

  lv_fec_proc                      DATE;
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_cart;

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion de Carteras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT 'cartera' || TO_CHAR(lv_fec_proc,'YYMMDD') || '.TXT'
  INTO lv_nom_arch
  FROM dual;

  gv_des_log := 'Nombre de Archivo ' || lv_nom_arch ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  DELETE FROM cob_entre_carte_cobra;

  gv_des_log := 'Cargando las carteras del cobrador';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO cob_entre_carte_cobra
   SELECT
    det.num_docu_iden                      num_docu_iden,
    det.num_docu_iden                      num_docu_refe,
    mae.val_ape1,
    mae.val_ape2,
    mae.val_nom1,
    mae.val_nom2,
    coring.cod_peri                        camp_ingr,
    TRIM(CONCAT(det.val_dire,det.val_dire_refe)) val_dire,
    det.cod_regi_clie                      cod_regi,
    det.cod_zona_clie                      cod_zona,
    det.cod_secc                           cod_secc,
    CONCAT(det.cod_zona_clie,det.cod_secc)      cod_terr,
    det.des_urba,
    det.des_prov,
    det.des_dpto,
    det.num_tele_fijo                      num_tele_fijo1,
    det.num_tele_trab                      num_tele_fijo2,
    det.num_tele_movi                      num_tele_movi,
    det.cod_peri                           camp_deud,
    TO_CHAR(det.fec_docu,'YYYYMMDD'),
    TO_CHAR(det.fec_venc,'YYYYMMDD'),
    det.fec_asig - det.fec_venc            dias_mora,
    det.imp_deud_orig,
    det.imp_deud_asig,
    ed.num_secu_etap,
    'F' tipo_docu,
    det.num_iden_cuot,
    '1',
    '  ' tipo_docu_ref,
    '          ' num_docu_ref,
    '  ' cuot_docu_ref,
    '      ' camp_docu_ref
    FROM
    cob_cabec_asign_carte cab,
    cob_detal_asign_carte det,
    cob_etapa_deuda_pais ed,
    cob_usuar_cobra_pais cuc,
    mae_clien mae,
    mae_clien_prime_conta mpc,
    cra_perio cming,
    seg_perio_corpo coring
   WHERE cab.cod_cart = det.cod_cart
     AND det.oid_clie = mae.oid_clie
     AND det.oid_clie = mpc.clie_oid_clie
     AND ed.cod_etap_deud = cab.cod_etap_deud
     AND ed.cod_etap_deud = det.cod_etap_deud
     AND cming.peri_oid_peri = coring.oid_peri
     AND cming.oid_peri = mpc.perd_oid_peri
     AND cuc.ind_supe = 0
     AND cuc.cod_usua_cobr = det.cod_usua_cobr
     AND cuc.cod_pais = det.cod_pais
     AND  det.cod_etap_deud = cab.cod_etap_deud
     AND  det.cod_regi = cab.cod_regi
     AND  det.cod_zona = cab.cod_zona
     AND  det.cod_peri = cab.cod_peri
     AND  det.cod_cart = cab.cod_cart
     AND  det.cod_usua_cobr = p_cod_usua_cobr
     AND det.fec_asig = lv_fec_proc;

  gv_des_log := 'Insertando la informacion en el control de envio';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_reg_cont.fecha := lv_fec_proc;
  lv_reg_cont.num_lote := lv_num_lote;
  lv_reg_cont.cod_usua_cobra_pais := p_cod_usua_cobr;
  lv_reg_cont.cod_inte := lc_cod_inte;
  lv_reg_cont.val_nomb_archi := lv_nom_arch;

  INSERT INTO cob_contro_envio_infor_cobra VALUES lv_reg_cont;

  SELECT
   c.val_serv_ftp,
   c.val_puer_ftp,
   c.val_usua_ftp,
   c.val_clav_ftp,
   c.val_dire_ftp,
   c.val_dire_rece_ftp
  INTO
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp,
   lv_val_dire_rece_ftp
  FROM cob_usuar_cobra_pais c
  WHERE c.cod_usua_cobr = p_cod_usua_cobr;

  gv_des_log := 'Generando los archivos de la interface';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   lv_val_dire_rece_ftp,
   lv_nom_arch,
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp);

  gv_des_log := 'Datos del envio ' || lv_val_dire_rece_ftp || ' ' || lv_nom_arch;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_INFOR_CARTE;

  PROCEDURE INT_PR_COB_GENER_INFOR_SALDO(
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-2';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_reg_cont                      cob_contro_envio_infor_cobra%ROWTYPE;
  lv_fec_proc                      DATE;

  lv_val_serv_ftp                  cob_usuar_cobra_pais.val_serv_ftp%TYPE;
  lv_val_puer_ftp                  cob_usuar_cobra_pais.val_puer_ftp%TYPE;
  lv_val_usua_ftp                  cob_usuar_cobra_pais.val_usua_ftp%TYPE;
  lv_val_clav_ftp                  cob_usuar_cobra_pais.val_clav_ftp%TYPE;
  lv_val_dire_ftp                  cob_usuar_cobra_pais.val_dire_ftp%TYPE;
  lv_val_dire_rece_ftp             cob_usuar_cobra_pais.val_dire_rece_ftp%TYPE;

 BEGIN

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  SELECT 'saldos' || TO_CHAR(lv_fec_proc,'YYMMDD') || '.TXT'
  INTO lv_nom_arch
  FROM dual;

  DELETE FROM cob_inter_gener_saldo;

  INSERT INTO cob_inter_gener_saldo
   SELECT
    det.num_docu_iden,
    det.num_docu_iden NUM_DOCU_REFE,
    det.imp_deud_pend,
    det.imp_deud_orig,
    TO_CHAR(det.fec_docu,'YYYYMMDD') fec_docu,
    det.cod_peri camp_deud,
    ed.num_secu_etap,
    'F' tipo_docu,
    det.num_iden_cuot,
    '1'
   FROM
    cob_cabec_asign_carte cab,
    cob_detal_asign_carte det,
    cob_etapa_deuda_pais ed,
    cob_usuar_cobra_pais cuc
   WHERE cab.cod_cart = det.cod_cart
     AND det.cod_pais = cab.cod_pais
     AND det.cod_soci = cab.cod_soci
     AND det.cod_etap_deud = cab.cod_etap_deud
     AND cab.cod_etap_deud = ed.cod_etap_deud
     AND det.cod_etap_deud = ed.cod_etap_deud
     AND det.cod_regi = cab.cod_regi
     AND det.cod_zona = cab.cod_zona
     AND det.cod_peri = cab.cod_peri
     AND det.cod_cart = cab.cod_cart
     AND cuc.ind_supe = 0
     AND cuc.cod_pais = det.cod_pais
     AND cuc.cod_usua_cobr = det.cod_usua_cobr
     AND det.cod_usua_cobr = p_cod_usua_cobr
     AND det.fec_cier >= lv_fec_proc;


  lv_reg_cont.fecha := lv_fec_proc;
  lv_reg_cont.num_lote := lv_num_lote;
  lv_reg_cont.cod_usua_cobra_pais := p_cod_usua_cobr;
  lv_reg_cont.cod_inte := lc_cod_inte;
  lv_reg_cont.val_nomb_archi := 'SALDOS';

  /*
  SELECT COUNT(*), SUM(val_sald_actu)
  INTO lv_reg_cont.val_regi_proc,lv_reg_cont.val_impo_gene
  FROM cob_inter_gener_saldo;
  */

  INSERT INTO  cob_contro_envio_infor_cobra VALUES lv_reg_cont;

  SELECT
   c.val_serv_ftp,
   c.val_puer_ftp,
   c.val_usua_ftp,
   c.val_clav_ftp,
   c.val_dire_ftp,
   c.val_dire_rece_ftp
  INTO
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp,
   lv_val_dire_rece_ftp
  FROM cob_usuar_cobra_pais c
  WHERE c.cod_usua_cobr = p_cod_usua_cobr;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   lv_val_dire_rece_ftp,
   lv_nom_arch,
   lv_val_serv_ftp,
   lv_val_puer_ftp,
   lv_val_usua_ftp,
   lv_val_clav_ftp,
   lv_val_dire_ftp);

 END INT_PR_COB_GENER_INFOR_SALDO;


/*
   PROCEDURE INT_PR_COB_GENER_RETRO_GESTI(
      p_num_lote                IN   fin_inter_ejecu.num_lote%TYPE,
      p_cod_usua_cobr      IN  cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_usua                IN  seg_usuar.use_usua%TYPE)

   IS

      lc_cod_inte                  CONSTANT CHAR(5):='COB-3';
      lv_nom_arch                  fin_param_inter_cabec.nom_arch%TYPE;
      lv_num_lote                  fin_inter_ejecu.num_lote%TYPE;
      lv_file_name                 fin_param_inter_cabec.nom_arch%TYPE;
      lv_reg_cont                  cob_contro_envio_infor_cobra%ROWTYPE;
      lv_handle                    utl_file.file_type;

      lv_val_serv_ftp            cob_usuar_cobra_pais.val_serv_ftp%TYPE;
      lv_val_puer_ftp            cob_usuar_cobra_pais.val_puer_ftp%TYPE;
      lv_val_usua_ftp            cob_usuar_cobra_pais.val_usua_ftp%TYPE;
      lv_val_clav_ftp             cob_usuar_cobra_pais.val_clav_ftp%TYPE;
      lv_val_dire_ftp            cob_usuar_cobra_pais.val_dire_ftp%TYPE;

   BEGIN

      SELECT 'Retroges' || TO_CHAR(SYSDATE,'YYMMDD')
      INTO lv_nom_arch
      FROM dual;

      --FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lc_cod_inte,p_cod_usua,lv_num_lote,lv_nom_arch,lv_handle);

      --FIN_PKG_INTER.FIN_PR_ACTUA_INTER_EJECU(gc_cod_modu,lc_cod_inte,lv_num_lote,gv_des_log);

      SELECT
         c.val_serv_ftp,
         c.val_puer_ftp,
         c.val_usua_ftp,
         c.val_clav_ftp,
         c.val_dire_ftp
      INTO
         lv_val_serv_ftp,
         lv_val_puer_ftp,
         lv_val_usua_ftp,
         lv_val_clav_ftp,
         lv_val_dire_ftp
      FROM cob_usuar_cobra_pais c
      WHERE c.cod_usua_cobr = p_cod_usua_cobr;

      INSERT INTO cob_inter_retro_gesti
         SELECT
               d.cod_usua_cobr,
               d.cod_etap_deud,
               d.num_docu_iden,
               NULL , --referencia
               d.cod_peri,
               g.fec_gest,
               g.val_hora_gest,
               'T',  -- medio de gestion,
               g.cod_acci_cobr,
               g.fec_acci_cobr,
               g.ppa_imp_pago,
               NULL,  -- nueva direccion
               NULL,    -- nuevo telefono
               g.val_obse,
               g.cod_usua_cobr_pais
             FROM
               cob_detal_asign_carte d,
               cob_detal_movim_carte m,
               cob_gesti_cobra_pais g,
               cob_tempo_carte_asign_proce t
           WHERE   d.cod_cart = t.cod_cart
                AND d.cod_cart = m.cod_cart
                AND d.oid_clie = m.oid_clie
                AND g.cod_clie = d.cod_clie
                AND d.cod_usua_cobr = p_cod_usua_cobr;

         --FIN_PKG_INTER.FIN_PR_PROCE_INTER_SALID(gc_cod_modu,lc_cod_inte,  lv_num_lote,lv_nom_arch,0,p_cod_usua,lv_val_serv_ftp,lv_val_puer_ftp,lv_val_usua_ftp,lv_val_clav_ftp,lv_val_dire_ftp);

          lv_reg_cont.fecha := TRUNC(SYSDATE);
         lv_reg_cont.num_lote := p_num_lote;
         lv_reg_cont.cod_usua_cobra_pais := p_cod_usua_cobr;
         lv_reg_cont.cod_inte := lc_cod_inte;
         lv_reg_cont.val_nomb_archi := 'RETROGES';

         SELECT COUNT(*)
         INTO lv_reg_cont.val_regi_proc
         FROM cob_inter_retro_gesti;

         INSERT INTO  cob_contro_envio_infor_cobra VALUES lv_reg_cont;

         --FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lc_cod_inte,lv_num_lote,lv_file_name,2);

   END INT_PR_COB_GENER_RETRO_GESTI;

   PROCEDURE INT_PR_COB_GENER_INFOR_CONTR(
      p_num_lote                IN   fin_inter_ejecu.num_lote%TYPE,
      p_cod_usua_cobr      IN  cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_usua                IN  seg_usuar.use_usua%TYPE)

   IS

      lc_cod_inte                  CONSTANT CHAR(5):='COB-5';
      lv_nom_arch                  fin_param_inter_cabec.nom_arch%TYPE;
      lv_num_lote                  fin_inter_ejecu.num_lote%TYPE;
      lv_file_name                 fin_param_inter_cabec.nom_arch%TYPE;
      lv_handle                    utl_file.file_type;

      lv_val_serv_ftp            cob_usuar_cobra_pais.val_serv_ftp%TYPE;
      lv_val_puer_ftp            cob_usuar_cobra_pais.val_puer_ftp%TYPE;
      lv_val_usua_ftp            cob_usuar_cobra_pais.val_usua_ftp%TYPE;
      lv_val_clav_ftp             cob_usuar_cobra_pais.val_clav_ftp%TYPE;
      lv_val_dire_ftp            cob_usuar_cobra_pais.val_dire_ftp%TYPE;

   BEGIN

      SELECT 'contro' || TO_CHAR(SYSDATE,'YYMMDD')
      INTO lv_nom_arch
      FROM dual;

      --FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu,lc_cod_inte,p_cod_usua,lv_num_lote,lv_nom_arch,lv_handle  );

      --FIN_PKG_INTER.FIN_PR_ACTUA_INTER_EJECU(gc_cod_modu,lc_cod_inte,lv_num_lote,gv_des_log);

      SELECT
         c.val_serv_ftp,
         c.val_puer_ftp,
         c.val_usua_ftp,
         c.val_clav_ftp,
         c.val_dire_ftp
      INTO
         lv_val_serv_ftp,
         lv_val_puer_ftp,
         lv_val_usua_ftp,
         lv_val_clav_ftp,
         lv_val_dire_ftp
      FROM cob_usuar_cobra_pais c
      WHERE c.cod_usua_cobr = p_cod_usua_cobr;

      DELETE FROM cob_inter_envio_contr;

      INSERT INTO cob_inter_envio_contr
         SELECT
               c.cod_usua_cobra_pais,-- val_codi_prov  varchar2(6)
                TO_CHAR(c.fecha,'YYYYMMDD'),-- val_fech  varchar2(8)
                c.val_nomb_archi,-- val_nomb_arch  varchar2(10)
                c.val_regi_proc,-- val_cant_regi  varchar2(5)
                c.val_impo_gene-- val_suma_impo  varchar2(15)
            FROM
               cob_contro_envio_infor_cobra c
           WHERE c.cod_usua_cobra_pais = p_cod_usua_cobr
           AND c.num_lote = p_num_lote;

         FIN_PKG_INTER.FIN_PR_PROCE_INTER_SALID(gc_cod_modu,lc_cod_inte,  lv_num_lote,lv_nom_arch,0,p_cod_usua,lv_val_serv_ftp,lv_val_puer_ftp,lv_val_usua_ftp,lv_val_clav_ftp,lv_val_dire_ftp);

         FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu,lc_cod_inte,lv_num_lote,lv_file_name,2);

   END INT_PR_COB_GENER_INFOR_CONTR;
*/

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR1(
  p_fec_proc                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-6';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_fec_proc                      DATE;

  lv_ser_ftp                       fin_param_inter_cabec.ser_ftp%TYPE;
  lv_pue_ftp                       fin_param_inter_cabec.pue_ftp%TYPE;
  lv_usu_ftp                       fin_param_inter_cabec.usu_ftp%TYPE;
  lv_pas_ftp                       fin_param_inter_cabec.pas_ftp%TYPE;
  lv_dir_ftp                       fin_param_inter_cabec.dir_ftp%TYPE;

 BEGIN

  lv_fec_proc := TO_DATE(p_fec_proc,'DD/MM/YYYY');

  lv_nom_arch := 'ACTERDOC' || TO_CHAR(lv_fec_proc,'YYMMDD') || '.TXT';

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT
   f.ser_ftp,
   f.pue_ftp,
   f.usu_ftp,
   f.pas_ftp,
   f.dir_ftp
  INTO
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp
  FROM fin_param_inter_cabec f
  WHERE f.cod_inte = lc_cod_inte;

  -- Seleccionado las carteras --
  DELETE FROM cob_envio_datos_ocr1;

  INSERT INTO cob_envio_datos_ocr1
   SELECT
    det.cod_clie,
    det.num_docu_iden,
    det.cod_usua_cobr
   FROM
    cob_cabec_asign_carte cab,
    cob_detal_asign_carte det,
    cob_usuar_cobra_pais usu
   WHERE cab.cod_cart = det.cod_cart
     AND det.cod_pais = cab.cod_pais
     AND det.cod_soci = cab.cod_soci
     AND det.cod_etap_deud = cab.cod_etap_deud
     AND det.cod_regi = cab.cod_regi
     AND det.cod_zona = cab.cod_zona
     AND det.cod_peri = cab.cod_peri
     AND det.cod_cart = cab.cod_cart
     and det.cod_usua_cobr = usu.cod_usua_cobr
     AND usu.ind_supe = 0
     AND det.fec_asig = lv_fec_proc;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   NULL,
   lv_nom_arch,
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp);

 END INT_PR_COB_GENER_INFOR_OCR1;

 PROCEDURE INT_PR_COB_GENER_CAMPA_OCR1(
  p_cod_cobr                       IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-6';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_reg_cont                      cob_contro_envio_infor_cobra%ROWTYPE;

  lv_ser_ftp                       fin_param_inter_cabec.ser_ftp%TYPE;
  lv_pue_ftp                       fin_param_inter_cabec.pue_ftp%TYPE;
  lv_usu_ftp                       fin_param_inter_cabec.usu_ftp%TYPE;
  lv_pas_ftp                       fin_param_inter_cabec.pas_ftp%TYPE;
  lv_dir_ftp                       fin_param_inter_cabec.dir_ftp%TYPE;

 BEGIN

  lv_nom_arch := 'ACTERDOC' || p_cod_peri || '.TXT';

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT
   f.ser_ftp,
   f.pue_ftp,
   f.usu_ftp,
   f.pas_ftp,
   f.dir_ftp
  INTO
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp
  FROM fin_param_inter_cabec f
  WHERE f.cod_inte = lc_cod_inte;

  -- Seleccionado las carteras --
  DELETE FROM cob_envio_datos_ocr1;

  INSERT INTO cob_envio_datos_ocr1
   SELECT
    det.cod_clie,
    det.num_docu_iden,
    det.cod_usua_cobr
   FROM
    cob_cabec_asign_carte cab,
    cob_detal_asign_carte det,
    cob_usuar_cobra_pais usu
   WHERE cab.cod_cart = det.cod_cart
     AND det.cod_pais = cab.cod_pais
     AND det.cod_soci = cab.cod_soci
     AND det.cod_etap_deud = cab.cod_etap_deud
     AND det.cod_regi = cab.cod_regi
     AND det.cod_zona = cab.cod_zona
     AND det.cod_peri = cab.cod_peri
     AND det.cod_cart = cab.cod_cart
     AND det.cod_usua_cobr = usu.cod_usua_cobr
     AND det.cod_usua_cobr = p_cod_cobr
     AND usu.ind_supe = 0
     AND det.cod_peri = p_cod_peri;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   USER,
   NULL,
   lv_nom_arch,
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp);

 END INT_PR_COB_GENER_CAMPA_OCR1;

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR1(
  p_cod_cobr                       IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_fec_asig                       IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-6';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_fec_asig                      DATE;

  lv_ser_ftp                       fin_param_inter_cabec.ser_ftp%TYPE;
  lv_pue_ftp                       fin_param_inter_cabec.pue_ftp%TYPE;
  lv_usu_ftp                       fin_param_inter_cabec.usu_ftp%TYPE;
  lv_pas_ftp                       fin_param_inter_cabec.pas_ftp%TYPE;
  lv_dir_ftp                       fin_param_inter_cabec.dir_ftp%TYPE;

 BEGIN

  lv_fec_asig := TO_DATE(p_fec_asig,'DD/MM/YYYY');

  lv_nom_arch := 'ACTERDOC' || TO_CHAR(lv_fec_asig,'YYMMDD') || '.TXT';

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT
   f.ser_ftp,
   f.pue_ftp,
   f.usu_ftp,
   f.pas_ftp,
   f.dir_ftp
  INTO
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp
  FROM fin_param_inter_cabec f
  WHERE f.cod_inte = lc_cod_inte;

  -- Seleccionado las carteras --
  DELETE FROM cob_envio_datos_ocr1;

  INSERT INTO cob_envio_datos_ocr1
   SELECT
    det.cod_clie,
    det.num_docu_iden,
    det.cod_usua_cobr
   FROM
    cob_cabec_asign_carte cab,
    cob_detal_asign_carte det,
    cob_usuar_cobra_pais usu
   WHERE cab.cod_cart = det.cod_cart
     AND det.cod_pais = cab.cod_pais
     AND det.cod_soci = cab.cod_soci
     AND det.cod_etap_deud = cab.cod_etap_deud
     AND det.cod_regi = cab.cod_regi
     AND det.cod_zona = cab.cod_zona
     AND det.cod_peri = cab.cod_peri
     AND det.cod_cart = cab.cod_cart
     AND det.cod_usua_cobr = usu.cod_usua_cobr
     AND det.cod_usua_cobr = p_cod_cobr
     AND usu.ind_supe = 0
     AND det.fec_asig = p_fec_asig;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   NULL,
   lv_nom_arch,
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp);

 END INT_PR_COB_GENER_INFOR_OCR1;

 PROCEDURE INT_PR_COB_GENER_INFOR_OCR2(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_cod_inte                      CONSTANT CHAR(5):='COB-7';
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;

  lv_ser_ftp                       fin_param_inter_cabec.ser_ftp%TYPE;
  lv_pue_ftp                       fin_param_inter_cabec.pue_ftp%TYPE;
  lv_usu_ftp                       fin_param_inter_cabec.usu_ftp%TYPE;
  lv_pas_ftp                       fin_param_inter_cabec.pas_ftp%TYPE;
  lv_dir_ftp                       fin_param_inter_cabec.dir_ftp%TYPE;

 BEGIN

  lv_nom_arch := 'ACTERPRV' || TO_CHAR(SYSDATE,'YYMMDD') || '.TXT';

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT
   f.ser_ftp,
   f.pue_ftp,
   f.usu_ftp,
   f.pas_ftp,
   f.dir_ftp
  INTO
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp
  FROM fin_param_inter_cabec f
  WHERE f.cod_inte = lc_cod_inte;

  -- Seleccionado las carteras --
  DELETE FROM cob_envio_datos_ocr2;

  INSERT INTO cob_envio_datos_ocr2
   SELECT
    usu.cod_usua_cobr,
    usu.val_nomb_usua_cobr,
    TRIM(usu.usu_modi),
    CASE
     WHEN ind_acti = 1 THEN 'S'
     ELSE 'N'
    END ind_acti,
    usu.val_dire_rece_ocr,
    usu.val_usua_cobr_ocr,
    usu.val_clav_cobr_ocr
   FROM
    cob_usuar_cobra_pais usu
  WHERE usu.ind_supe = 0
  AND usu.ind_acti = 1;

  FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(
   gc_cod_modu,
   lc_cod_inte,
   lv_num_lote,
   p_cod_usua,
   NULL,
   lv_nom_arch,
   lv_ser_ftp,
   lv_pue_ftp,
   lv_usu_ftp,
   lv_pas_ftp,
   lv_dir_ftp);

 END INT_PR_COB_GENER_INFOR_OCR2;

 PROCEDURE INT_PR_COB_GENER_INFOR_COBRA(
  p_fec_proc                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  -- Cursor de los Cobradores --
  CURSOR c_cobr
  IS
   SELECT
    d.cod_usua_cobr,
    u.val_nomb_usua_cobr
   FROM
    cob_detal_asign_carte d,
    cob_cabec_asign_carte c,
    cob_usuar_cobra_pais u
   WHERE d.cod_cart = c.cod_cart
     AND u.ind_envi_info_cobr = 1
     AND c.ind_envi_cart = 0
     AND u.ind_inte_corp = 0
     AND d.cod_usua_cobr = u.cod_usua_cobr
   GROUP BY d.cod_usua_cobr,u.val_nomb_usua_cobr;

  CURSOR c_cobr_sald
  IS
   SELECT
    d.cod_usua_cobr,
    u.val_nomb_usua_cobr
   FROM
    cob_detal_asign_carte d,
    cob_cabec_asign_carte c,
    cob_usuar_cobra_pais u
   WHERE d.cod_cart = c.cod_cart
     AND u.ind_envi_info_cobr = 1
     AND c.ind_envi_liqu = 0
     AND u.ind_inte_corp = 0
     AND d.cod_usua_cobr = u.cod_usua_cobr
   GROUP BY d.cod_usua_cobr,u.val_nomb_usua_cobr;

  lv_cod_erro                      VARCHAR2(4000);
  lv_fec_proc                      VARCHAR2(10);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_cobr;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Cartera a Cobradores';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  IF p_fec_proc IS NULL THEN
   lv_fec_proc := TO_CHAR(SYSDATE,'DD/MM/YYYY');
  ELSE
   lv_fec_proc := p_fec_proc;
  END IF;

  gv_des_log := 'Fecha de Proceso ' || lv_fec_proc;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FOR v_cobr IN c_cobr LOOP

   gv_des_log := '   Cobrador Codigo ' || v_cobr.cod_usua_cobr || ' ' || v_cobr.val_nomb_usua_cobr;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := '   Enviando la Cartera';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_COB_GENER_INFOR_CARTE(v_cobr.cod_usua_cobr,lv_fec_proc,p_cod_usua);

   gv_des_log := '   ***************';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END LOOP;

  gv_des_log := 'Inicio Generacion Saldos a Cobradores';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FOR v_cobr_sald IN c_cobr_sald LOOP

   gv_des_log := '   Cobrador Codigo ' || v_cobr_sald.cod_usua_cobr || ' ' || v_cobr_sald.val_nomb_usua_cobr;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := '   Enviando los Saldos';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   INT_PR_COB_GENER_INFOR_SALDO(v_cobr_sald.cod_usua_cobr,lv_fec_proc,p_cod_usua);

   gv_des_log := '   ***************';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END LOOP;

  gv_des_log := '   Generando las solicitudes de OCR';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  INT_PR_COB_GENER_INFOR_OCR1(lv_fec_proc,p_cod_usua);

  gv_des_log := '   Generando las datos de los proveedores para OCR';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  INT_PR_COB_GENER_INFOR_OCR2(p_cod_usua);


  BEGIN
   gv_des_log := '   Generando Cartera Corporativa';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   INT_PR_COB_GENER_INFCO_CORPO(lv_fec_proc,p_cod_usua);
  EXCEPTION
    WHEN OTHERS THEN
     gv_des_log := '   !!!ERROR Generando Cartera Corporativa';
     FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  END;

  gv_des_log := 'Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 END INT_PR_COB_GENER_INFOR_COBRA;

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Cobranza y
                      saldos pendiente
  Fecha Creacion    : 26/04/2011
  Autor             : Jose Luis Rodriguez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_cobra_saldo(
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2
  )
 IS

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;

   lv_fec_proc      DATE;
   ls_fec_proc      VARCHAR2(8);
   lv_num_peri      VARCHAR2(3);
   lv_canal         VARCHAR2(2);
   lv_anio_str      VARCHAR2(4);
   lv_mes_str       VARCHAR2(2);
   lv_cod_peri      seg_perio_corpo.cod_peri%type;
   lv_sap_cod_soci  bas_homol_socie_sapfi.cod_soci_sap %type;
   lv_sap_cod_pais  bas_homol_socie_sapfi.cod_pais_sap %type;
   lv_anio          VARCHAR2(4);
   lv_mes           VARCHAR2(2);
   lv_cod_peri_ant  seg_perio_corpo.cod_peri%TYPE;

   CURSOR c_cam IS
     SELECT a.cod_peri  COD_PERI
       FROM (SELECT cor.cod_peri  cod_peri,
                    cor.val_anio  val_anho
               FROM seg_perio_corpo cor
              WHERE cor.cod_peri <= lv_cod_peri
           ORDER BY cor.cod_peri DESC) a
      WHERE ROWNUM <= lv_num_peri;

   CURSOR c_interfaz (pscodperiodo VARCHAR2)IS
     WITH saldos
              AS (  SELECT cbz.cod_peri,
                           cbz.cod_regi,
                           cbz.cod_zona,
                           SUM (imp_fact)          tfact,
                           SUM (imp_fact_neto)     tfact_net,
                           SUM (imp_sald_pend_sac) tpend_sac,
                           SUM (cbz.cob_dias_999)  tcob
                      FROM cob_repor_estad_recup_cobra cbz
                     WHERE cbz.cod_pais = pscodigopais
                       AND cbz.cod_soci = pscodigosociedad
                       AND cbz.cod_peri = pscodperiodo
                  GROUP BY cbz.cod_peri,
                           cbz.cod_regi,
                           cbz.cod_zona),
              fechas
              AS (  SELECT cbz.cod_peri,
                           cbz.cod_regi,
                           cbz.cod_zona,
                           MIN (fec_docu) fec_ini,
                           MAX (fec_docu) fec_fin
                      FROM cob_repor_estad_recup_cobra cbz,
                           ccc_movim_cuent_corri cc
                     WHERE cbz.cod_pais = pscodigopais
                       AND cbz.cod_soci = pscodigosociedad
                       AND cbz.cod_peri = pscodperiodo
                       AND cc.oid_movi_cc = cbz.oid_movi_cc
                       AND cbz.imp_sald_pend_sac > 0
                  GROUP BY cbz.cod_peri,
                           cbz.cod_regi,
                           cbz.cod_zona)
         SELECT lv_anio_str      ANHO_EJERCICIO,
                 lv_mes_str       PERI_CONTABLE,
                 sal.cod_peri     CODI_PERIODO,
                 lv_sap_cod_soci  CODI_SOCIEDAD_SAP,
                 lv_sap_cod_pais  CODI_PAIS_SAP,
                 lv_canal         CODI_CANAL,
                 sal.cod_regi     CODI_REGION,
                 sal.cod_zona     CODI_ZONA,
                 sal.tcob         TOTA_COBRADO,
                 sal.tpend_sac    TOTA_SALDO,
                 ls_fec_proc      FECH_PROCESO,
                 TO_CHAR(fec.fec_ini,'YYYYMMDD')      FECH_FACTURACION_INI,
                 TO_CHAR(fec.fec_fin,'YYYYMMDD')      FECH_FACTURACION_FIN
           FROM saldos sal,
                fechas fec
          WHERE sal.cod_peri = fec.cod_peri
            AND sal.cod_regi = fec.cod_regi
            AND sal.cod_zona = fec.cod_zona;

   TYPE interfazrec IS RECORD(
     anhoEjercicio       VARCHAR2(4),
     periContable        VARCHAR2(2),
     codiPeriodo         seg_perio_corpo.cod_peri%TYPE,
     codiSociedadSap     bas_homol_socie_sapfi.cod_soci_sap %type,
     codiPaisSap         bas_homol_socie_sapfi.cod_pais_sap %TYPE,
     codiCanal           VARCHAR2(2),
     codiRegion          zon_regio.cod_regi%TYPE,
     codiZona            zon_zona.cod_zona%TYPE,
     totaCobrado         NUMBER,
     totaSaldo           NUMBER,
     fechProceso         VARCHAR2(8),
     fechFacturacionIni  VARCHAR2(8),
     fechFacturacionFin  VARCHAR2(8)
   );
   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN
   lbabrirutlfile := TRUE;
   ls_fec_proc := TO_CHAR(trunc(SYSDATE),'YYYYMMDD');
   lv_fec_proc := trunc(SYSDATE);
   lv_mes := TO_CHAR(lv_fec_proc,'mm');
   lv_anio := TO_CHAR(lv_fec_proc,'yyyy');

   IF (lv_mes = '01') THEN
     lv_anio_str := to_char(to_number(lv_anio) - 1);
     lv_mes_str  := '12';
   ELSE
     lv_anio_str := lv_anio;
     lv_mes_str  := to_char(to_number(lv_mes) - 1);

     IF(length(lv_mes_str)=1) THEN
       lv_mes_str := '0'||lv_mes_str;
     END IF;
   END IF;

   -- recupera codigos homologados para informar en interfaz
   BEGIN
     SELECT sf.cod_soci_sap,
             sf.cod_pais_sap
       INTO lv_sap_cod_soci,
             lv_sap_cod_pais
       FROM bas_homol_socie_sapfi sf
      WHERE sf.cod_pais = pscodigopais
        AND sf.cod_soci = pscodigosociedad;
   EXCEPTION WHEN NO_DATA_FOUND THEN
     lv_sap_cod_soci:='';
     lv_sap_cod_pais:='';
   END;

   -- determina  campa?a en  curso
   SELECT max (pc.cod_peri)
     INTO lv_cod_peri
     FROM cra_perio cp,
          seg_perio_corpo pc
    WHERE cp.peri_oid_peri = pc.oid_peri
      AND trunc(lv_fec_proc ) >= cp.fec_inic
      AND trunc(lv_fec_proc ) <= cp.fec_fina;

   -- recupera parametro numero de campa?as por evaluar buscando saldos pendientes
   SELECT pg.val_para
     INTO lv_num_peri
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'CBZ_COB_PEN_01';

   -- recupera  parametro  valor  para Canal
   SELECT pg.val_para
     INTO lv_canal
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'CBZ_COB_PEN_02';

   OPEN c_cam;
     LOOP
       FETCH c_cam INTO lv_cod_peri_ant;
         /* Procedimiento inicial para generar interfaz */

         IF lbabrirutlfile THEN
           gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
           lbabrirutlfile := FALSE;
         END IF;

         OPEN c_interfaz(lv_cod_peri_ant);
           LOOP
             FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;

               IF interfazrecord.COUNT > 0 THEN
                 FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
                   lslinea := interfazrecord(x).anhoEjercicio                   || ';' ||
                              interfazrecord(x).periContable                    || ';' ||
                              interfazrecord(x).codiPeriodo                     || ';' ||
                              interfazrecord(x).codiSociedadSap                 || ';' ||
                              interfazrecord(x).codiPaisSap                     || ';' ||
                              interfazrecord(x).codiCanal                       || ';' ||
                              interfazrecord(x).codiRegion                      || ';' ||
                              interfazrecord(x).codiZona                        || ';' ||
                              interfazrecord(x).totaCobrado                     || ';' ||
                              interfazrecord(x).totaSaldo                       || ';' ||
                              interfazrecord(x).fechProceso                     || ';' ||
                              interfazrecord(x).fechFacturacionIni              || ';' ||
                              interfazrecord(x).fechFacturacionFin;
                   utl_file.put_line(v_handle,lslinea);
                 END LOOP;
               END IF;

             EXIT WHEN c_interfaz%NOTFOUND;
           END LOOP;
         CLOSE c_interfaz;

       EXIT WHEN (c_cam%NOTFOUND);
     END LOOP;
   CLOSE c_cam;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;

   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_cobra_saldo: '||ls_sqlerrm);
 END int_pr_cob_gener_cobra_saldo;

 /***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Transaccion
                      Cobranza
  Fecha Creacion    : 27/04/2011
  Autor             : Nicolas Lopez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_trans_cobra(
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigosociedad  VARCHAR2,
    psanhio           VARCHAR2,
    psmes             VARCHAR2
 )
 IS

   /* Declaracion de variables */
   lv_fecha_str     VARCHAR2(10);
   lv_fecha_ini     DATE;
   lv_fecha_fin     DATE;
   lv_cod_soci_sap  bas_homol_socie_sapfi.cod_soci_sap%TYPE;
   lv_cod_pais_sap  bas_homol_socie_sapfi.cod_pais_sap%TYPE;
   lv_val_des_inte  bas_homol_socie_sapfi.des_inte%TYPE;

   CURSOR c_interfazLocal IS
          WITH depo AS (
              SELECT wr.cod_pais,
                     cba.cod_banc,
                     zon.cod_zona,
                     SUM(wr.imp_reca_gene) t_pago,
                     COUNT(1) t_depositos
                FROM COB_GTT_CONS_BANC     wr,
                     ccc_cuent_corri_banca cb,
                     ccc_sucur             csu,
                     ccc_banco             cba,
                     mae_clien             cli,
                     mae_clien_unida_admin ua,
                     zon_terri_admin       za,
                     zon_secci             sec,
                     zon_zona              zon
               WHERE wr.STA_MOVI = 'C'
                 AND wr.TIOR_TIPO_ORIG_DATO = '02'
                 AND cb.COD_CC = wr.COD_CCBA
                 AND cb.SUCU_OID_SUCU =  csu.OID_SUCU
                 AND csu.CBAN_OID_BANC = cba.OID_BANC
                 AND cli.cod_clie = wr.cod_clie
                 AND cli.oid_clie = ua.clie_oid_clie
                 AND ua.ztad_oid_terr_admi = za.oid_terr_admi
                 AND za.zscc_oid_secc = sec.oid_secc
                 AND sec.zzon_oid_zona = zon.oid_zona
                 AND ua.ind_acti = 1
            GROUP BY wr.cod_pais, cba.cod_banc, zon.cod_zona)

          SELECT psanhio          ANHIO,
                 psmes            MES,
                 lv_cod_soci_sap  COD_SOCI_SAP,
                 lv_cod_pais_sap  COD_PAIS_SAP,
                 '01'             NUM,
                 cbx.cod_banc     COD_BANC,
                 cbx.des_banc     DES_BANC,
                 d.cod_zona       COD_ZONA,
                 d.t_pago         TOT_PAGO,
                 d.t_depositos    TOT_DEPOSITO
            FROM depo          d,
                 ccc_banco     cbx,
                 seg_pais      pai,
                 gen_i18n_sicc gen
           WHERE cbx.pais_oid_pais = pai.oid_pais
             AND cbx.cod_banc      = d.cod_banc
             AND gen.val_oid       = pai.oid_pais
             AND gen.attr_enti     = 'SEG_PAIS'
           ORDER BY cbx.cod_banc, d.cod_zona;

   CURSOR c_interfazNoLocal(ln_OidPais NUMBER) IS
      WITH banca AS  (
      SELECT ba.pais_oid_pais, ba.ccba_oid_cc_banc, ba.clie_oid_clie, ba.imp_pago
        FROM ccc_movim_banca ba
       WHERE ba.cod_iden_proc = 'P'
         AND ba.fec_pago >= lv_fecha_ini
         AND ba.fec_pago <= lv_fecha_fin
         AND ba.pais_oid_pais = ln_OidPais
      ),
      depo as (
      SELECT wr.pais_oid_pais,
             cba.cod_banc,
             zon.cod_zona,
             SUM(wr.imp_pago) t_pago,
             COUNT(1) t_depositos
        FROM banca                 wr,
             ccc_cuent_corri_banca cb,
             ccc_sucur             csu,
             ccc_banco             cba,
             mae_clien_unida_admin ua,
             zon_terri_admin       za,
             zon_secci             sec,
             zon_zona              zon
       WHERE wr.pais_oid_pais = cb.pais_oid_pais
         AND wr.ccba_oid_cc_banc = cb.oid_cuen_corr_banc
         AND cb.sucu_oid_sucu = csu.oid_sucu
         AND csu.cban_oid_banc = cba.oid_banc
         AND wr.clie_oid_clie = ua.clie_oid_clie
         AND ua.ztad_oid_terr_admi = za.oid_terr_admi
         AND za.zscc_oid_secc = sec.oid_secc
         AND sec.zzon_oid_zona = zon.oid_zona
         AND cb.pais_oid_pais  = ln_OidPais
         AND ua.ind_acti = 1
       GROUP BY wr.pais_oid_pais, cba.cod_banc, zon.cod_zona)

       SELECT psanhio             ANHIO,
              psmes               MES,
              lv_cod_soci_sap     COD_SOCI_SAP,
              lv_cod_pais_sap     COD_PAIS_SAP,
              '01'                NUM,
              cbx.cod_banc        COD_BANC,
              cbx.des_banc        DES_BANC,
              depo.cod_zona       COD_ZONA,
              depo.t_pago         TOT_PAGO,
              depo.t_depositos    TOT_DEPOSITO
         FROM depo, ccc_banco cbx, seg_pais pai, gen_i18n_sicc gen
        WHERE cbx.pais_oid_pais = pai.oid_pais
          AND cbx.cod_banc = depo.cod_banc
          AND gen.val_oid = pai.oid_pais
          AND gen.attr_enti = 'SEG_PAIS'
        ORDER BY cbx.cod_banc, depo.cod_zona;


   TYPE interfazrec IS RECORD(
     anhio               VARCHAR2(4),
     mes                 VARCHAR2(2),
     codiSociedadSap     bas_homol_socie_sapfi.cod_soci_sap%TYPE,
     codiPaisSap         bas_homol_socie_sapfi.cod_pais_sap%TYPE,
     num                 VARCHAR2(2),
     codiBanc            ccc_banco.cod_banc%TYPE,
     descBanc            ccc_banco.des_banc%TYPE,
     codiZona            zon_zona.cod_zona%TYPE,
     totPago             NUMBER(14,2),
     totDeposito         NUMBER(10)
   );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

  /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   W_FILAS             NUMBER := 5000 ;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);

   lsNombreArchivo     VARCHAR2(50);
   lnIdPais            NUMBER;
   lbAbrirUtlFile      BOOLEAN;

 BEGIN

--  LUD_PKG_REGISLOG.LUD_PR_REGIS_LOG('int_pr_cob_gener_trans_cobra', PSCODIGOPAIS ,  NULL,  'INICIO');
   lbabrirutlfile := TRUE;

   lv_fecha_str := '01/' || psmes || '/' || psanhio;
   lv_fecha_ini := TO_DATE(lv_fecha_str, 'dd/MM/yyyy');
   lv_fecha_fin := LAST_DAY(lv_fecha_ini);

   /* obteniendo id's */
   lnIdPais     := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);-- id del pais consultante

   -- Se obtienen los valores de Codigo Sociedad SAP, Codigo de Pais SAP
   -- y Descriptor de Interfaz
   BEGIN
      SELECT sf.cod_soci_sap, sf.cod_pais_sap, sf.des_inte
        INTO lv_cod_soci_sap, lv_cod_pais_sap, lv_val_des_inte
        FROM BAS_HOMOL_SOCIE_SAPFI sf
       WHERE sf.cod_pais = pscodigopais
         AND sf.cod_soci = pscodigosociedad;
   EXCEPTION WHEN NO_DATA_FOUND THEN
       lv_cod_soci_sap:='';
       lv_cod_pais_sap:='';
   END;

   IF (pscodigopais = 'PE') THEN

     -- Se consolida detalle en tabla Temporal
      INSERT INTO COB_GTT_CONS_BANC
      SELECT per.pais_cod_pais,
             per.cod_cons,
             per.moca_num_lote_inte,
             per.imp_reca_gene,
             per.tior_tipo_orig_dato,
             per.sta_movi,
             cab.cod_ccba
        FROM per_movim_banca_detal per, per_movim_banca_cabec cab
       WHERE per.pais_cod_pais = cab.pais_cod_pais
         AND per.tior_tipo_orig_dato = cab.tior_tipo_orig_dato
         AND per.moca_num_lote_inte = cab.num_lote_inte
         AND cab.pais_cod_pais = pscodigopais
         AND cab.tior_tipo_orig_dato = '02'
         AND per.sta_movi = 'C'
         AND per.fec_pago >= lv_fecha_ini
         AND per.fec_pago <= lv_fecha_fin;

   END IF;

   lbabrirutlfile := TRUE;


--  LUD_PKG_REGISLOG.LUD_PR_REGIS_LOG('int_pr_cob_gener_trans_cobra', PSCODIGOPAIS ,  NULL,  'PUNTO1');

   IF (pscodigopais = 'PE') THEN

       OPEN c_interfazLocal;
         LOOP
          FETCH c_interfazLocal BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

                /* Procedimiento inicial para generar interfaz */
                IF lbabrirutlfile THEN
                  gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                         lsdirtempo, lsnombrearchivo, v_handle);
                  lbabrirutlfile := FALSE;
                END IF;

                IF interfazrecord.COUNT > 0 THEN
                  FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                           lslinea := interfazrecord(x).anhio                   || ';' ||
                                      interfazrecord(x).mes                     || ';' ||
                                      interfazrecord(x).codiSociedadSap         || ';' ||
                                      interfazrecord(x).codiPaisSap             || ';' ||
                                      interfazrecord(x).num                     || ';' ||
                                      interfazrecord(x).codiBanc                || ';' ||
                                      interfazrecord(x).descBanc                || ';' ||
                                      interfazrecord(x).codiZona                || ';' ||
                                      interfazrecord(x).totPago                 || ';' ||
                                      interfazrecord(x).totDeposito;
                              utl_file.put_line(v_handle,lslinea);
                   END LOOP;
                END IF;

              EXIT WHEN c_interfazLocal%NOTFOUND;
         END LOOP;
       CLOSE c_interfazLocal;

   ELSE

       OPEN c_interfazNoLocal(lnIdPais);
         LOOP
          FETCH c_interfazNoLocal BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

                /* Procedimiento inicial para generar interfaz */
                IF lbabrirutlfile THEN
                  gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                         lsdirtempo, lsnombrearchivo, v_handle);
                  lbabrirutlfile := FALSE;
                END IF;

                IF interfazrecord.COUNT > 0 THEN
                  FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                           lslinea := interfazrecord(x).anhio                   || ';' ||
                                      interfazrecord(x).mes                     || ';' ||
                                      interfazrecord(x).codiSociedadSap         || ';' ||
                                      interfazrecord(x).codiPaisSap             || ';' ||
                                      interfazrecord(x).num                     || ';' ||
                                      interfazrecord(x).codiBanc                || ';' ||
                                      interfazrecord(x).descBanc                || ';' ||
                                      interfazrecord(x).codiZona                || ';' ||
                                      interfazrecord(x).totPago                 || ';' ||
                                      interfazrecord(x).totDeposito;
                              utl_file.put_line(v_handle,lslinea);
                   END LOOP;
                END IF;

              EXIT WHEN c_interfazNoLocal%NOTFOUND;
         END LOOP;
       CLOSE c_interfazNoLocal;

   END IF;

--  LUD_PKG_REGISLOG.LUD_PR_REGIS_LOG('int_pr_cob_gener_trans_cobra', PSCODIGOPAIS ,  NULL,  'INICIA INTERFAZ');

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;

--  LUD_PKG_REGISLOG.LUD_PR_REGIS_LOG('int_pr_cob_gener_trans_cobra', PSCODIGOPAIS ,  NULL,  'FIN');

   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_trans_cobra: '||ls_sqlerrm);
 END int_pr_cob_gener_trans_cobra;

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Cobranza y
                      Periodo por Zona
  Fecha Creacion    : 25/07/2011
  Autor             : Jose Luis Rodriguez
***************************************************************************/
 PROCEDURE int_pr_cob_gener_cobra_perzo(
   pscodigopais      VARCHAR2,
   pscodigosistema   VARCHAR2,
   pscodigointerfaz  VARCHAR2,
   psnombrearchivo   VARCHAR2,
   pscodigosociedad  VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;

   lv_fec_proc      DATE;
   lv_num_peri      VARCHAR2(3);
   lv_anio_str      VARCHAR2(4);
   lv_mes_str       VARCHAR2(2);
   lv_cod_peri_act  seg_perio_corpo.cod_peri%type;
   lv_cod_peri_ini  seg_perio_corpo.cod_peri%type;
   lv_sap_cod_soci  bas_homol_socie_sapfi.cod_soci_sap%type;
   lv_sap_cod_pais  bas_homol_socie_sapfi.cod_pais_sap%type;
   lv_val_cod_arch  bas_homol_socie_sapfi.des_inte%type;
   lv_anio          VARCHAR2(4);
   lv_mes           VARCHAR2(2);

   CURSOR c_interfaz IS
     WITH cobra
       AS (
            SELECT cod_peri                                               COD_PERIODO,
                   cod_zona                                               COD_ZONA,
                   des_zona                                               DES_ZONA,
                   FIN_PKG_GENER.FIN_FN_OBTIE_NOMBR_CLIEN(oid_clie_zona)  NOM_GERE_ZONA,
                   SUM(imp_fact)                                          IMP_FACT,
                   SUM(imp_sald_pend_sac)                                 IMP_SALD_PEND_SAC,
                   SUM(cob_dias_21)                                       COB_21,
                   SUM(cob_dias_31)                                       COB_31,
                   SUM(cob_dias_42)                                       COB_42,
                   SUM(cob_dias_63)                                       COB_63,
                   SUM(cob_dias_84)                                       COB_84,
                   SUM(cob_dias_126)                                      COB_126,
                   SUM(cob_dias_189)                                      COB_189,
                   SUM(cob_dias_999)                                      COB_999,
                   SUM(imp_carg_dire)                                     IMP_CARG_DIRE,
                   SUM(imp_abon_conx)                                     IMP_ABON_CON,
                   SUM(imp_abon_nmon)                                     IMP_ABON_NO_MON,
                   SUM(imp_sald_pend)                                     IMP_SALD_PEND,
                   SUM(imp_carg_frac)                                     IMP_CARG_FRAC,
                   SUM(imp_abon_frac)                                     IMP_ABON_FRAC,
                   SUM(imp_fact_nenv)                                     IMP_FACT_NO_ENV,
                   SUM(imp_devo)                                          IMP_DEVO
              FROM cob_repor_estad_recup_cobra
             WHERE cod_pais= pscodigopais
               AND cod_soci = pscodigosociedad
               AND cod_peri >= lv_cod_peri_ini
               AND cod_peri <= lv_cod_peri_act
          GROUP BY cod_peri,
                   cod_zona,
                   des_zona,
                   oid_clie_zona
          ORDER BY 1, 2 ASC
         )
         SELECT lv_sap_cod_pais,
                lv_anio_str,
                lv_mes_str,
                lv_sap_cod_soci,
                cob.COD_PERIODO,
                cob.COD_ZONA,
                cob.DES_ZONA,
                cob.NOM_GERE_ZONA,
                cob.IMP_FACT,
                cob.IMP_SALD_PEND_SAC,
                cob.COB_21,
                cob.COB_31,
                cob.COB_42,
                cob.COB_63,
                cob.COB_84,
                cob.COB_126,
                cob.COB_189,
                cob.COB_999,
                cob.IMP_CARG_DIRE,
                cob.IMP_ABON_CON,
                cob.IMP_ABON_NO_MON,
                cob.IMP_SALD_PEND,
                cob.IMP_CARG_FRAC,
                cob.IMP_ABON_FRAC,
                cob.IMP_FACT_NO_ENV,
                cob.IMP_DEVO
           FROM cobra cob;

   TYPE interfazrec IS RECORD(
     codiPaisSap        bas_homol_socie_sapfi.cod_pais_sap%TYPE,
     anhoEjercicio      VARCHAR2(4),
     periContable       VARCHAR2(2),
     codiSociedadSap    bas_homol_socie_sapfi.cod_soci_sap %TYPE,
     codiPeriodo        seg_perio_corpo.cod_peri%TYPE,
     codiZona           zon_zona.cod_zona%TYPE,
     desZona            zon_zona.des_zona%TYPE,
     nomGerente         VARCHAR2(100),
     monFacturado       NUMBER,
     sldPendienteSac    NUMBER,
     cobro21            NUMBER,
     cobro31            NUMBER,
     cobro42            NUMBER,
     cobro63            NUMBER,
     cobro84            NUMBER,
     cobro126           NUMBER,
     cobro189           NUMBER,
     cobro999           NUMBER,
     totCargoDirecto    NUMBER,
     totAbonoNoMoneX    NUMBER,
     totAbonoNoMoneN    NUMBER,
     sldPendiente       NUMBER,
     totCargoFrac       NUMBER,
     totAbonoFrac       NUMBER,
     totFacturadoNoEnv  NUMBER,
     totDevoluciones    NUMBER
   );
   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN

   lbabrirutlfile := TRUE;
   lv_fec_proc := trunc(SYSDATE);
   lv_mes := TO_CHAR(lv_fec_proc,'mm');
   lv_anio := TO_CHAR(lv_fec_proc,'yyyy');

   IF (lv_mes = '01') THEN
     lv_anio_str := to_char(to_number(lv_anio) - 1);
     lv_mes_str  := '12';
   ELSE
     lv_anio_str := lv_anio;
     lv_mes_str  := to_char(to_number(lv_mes) - 1);

     IF(length(lv_mes_str)=1) THEN
       lv_mes_str := '0'||lv_mes_str;
     END IF;
   END IF;

   -- determina  campa?a en  curso
   SELECT MAX(pc.cod_peri)
     INTO lv_cod_peri_act
     FROM cra_perio cp,
          seg_perio_corpo pc
    WHERE cp.peri_oid_peri = pc.oid_peri
      AND trunc(lv_fec_proc) >= cp.fec_inic
      AND trunc(lv_fec_proc) <= cp.fec_fina;

   -- recupera codigos homologados para informar en interfaz
   BEGIN
     SELECT sf.cod_soci_sap,
             sf.cod_pais_sap,
             sf.des_inte
       INTO lv_sap_cod_soci,
             lv_sap_cod_pais,
             lv_val_cod_arch
       FROM bas_homol_socie_sapfi sf
      WHERE sf.cod_pais = pscodigopais
        AND sf.cod_soci = pscodigosociedad;
   EXCEPTION WHEN NO_DATA_FOUND THEN
     lv_sap_cod_soci:='';
     lv_sap_cod_pais:='';
   END;

   -- recupera  parametro  numero de campa?as  por  evaluar
   SELECT pg.val_para
     INTO lv_num_peri
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'CBZ_COB_REC_03';

   -- Determina campa?a  inicial  de evaluacion
   SELECT MIN(cod_peri)
     INTO lv_cod_peri_ini
     FROM (SELECT *
             FROM (SELECT cor.cod_peri,
                          cor.val_anio
                     FROM seg_perio_corpo cor
                    WHERE cor.cod_peri <= lv_cod_peri_act
                 ORDER BY cor.cod_peri DESC)
            WHERE ROWNUM <= lv_num_peri );

   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;

         /* Procedimiento inicial para generar interfaz */
         IF lbabrirutlfile THEN
           gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
           lbabrirutlfile := FALSE;
         END IF;

         IF interfazrecord.COUNT > 0 THEN
           FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
             lslinea := interfazrecord(x).codiPaisSap           || ';' ||
                        interfazrecord(x).anhoEjercicio         || ';' ||
                        interfazrecord(x).periContable          || ';' ||
                        interfazrecord(x).codiSociedadSap       || ';' ||
                        interfazrecord(x).codiPeriodo           || ';' ||
                        interfazrecord(x).codiZona              || ';' ||
                        interfazrecord(x).desZona               || ';' ||
                        interfazrecord(x).nomGerente            || ';' ||
                        interfazrecord(x).monFacturado          || ';' ||
                        interfazrecord(x).sldPendienteSac       || ';' ||
                        interfazrecord(x).cobro21               || ';' ||
                        interfazrecord(x).cobro31               || ';' ||
                        interfazrecord(x).cobro42               || ';' ||
                        interfazrecord(x).cobro63               || ';' ||
                        interfazrecord(x).cobro84               || ';' ||
                        interfazrecord(x).cobro126              || ';' ||
                        interfazrecord(x).cobro189              || ';' ||
                        interfazrecord(x).cobro999              || ';' ||
                        interfazrecord(x).totCargoDirecto       || ';' ||
                        interfazrecord(x).totAbonoNoMoneX       || ';' ||
                        interfazrecord(x).totAbonoNoMoneN       || ';' ||
                        interfazrecord(x).sldPendiente          || ';' ||
                        interfazrecord(x).totCargoFrac          || ';' ||
                        interfazrecord(x).totAbonoFrac          || ';' ||
                        interfazrecord(x).totFacturadoNoEnv     || ';' ||
                        interfazrecord(x).totDevoluciones;

             utl_file.put_line(v_handle,lslinea);
           END LOOP;
         END IF;

       EXIT WHEN c_interfaz%NOTFOUND;
     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;

   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_cobra_perzo: '||ls_sqlerrm);
 END int_pr_cob_gener_cobra_perzo;



 /***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz Recuperacion Cobranza
                      por cobrador
  Fecha Creacion    : 04/09/2012
  Autor             : Sergio Buchelli
***************************************************************************/
 PROCEDURE int_pr_cob_gener_recup_cobra(
   pscodigopais      VARCHAR2,
   pscodigosistema   VARCHAR2,
   pscodigointerfaz  VARCHAR2,
   psnombrearchivo   VARCHAR2,
   pscodigosociedad  VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;


   CURSOR c_interfaz(lsEtapa VARCHAR2,lsReunion VARCHAR2,lnOidActivo NUMBER,lnOidActividad NUMBER,
                     lsPeriodoInicio VARCHAR2,lsPeriodoFin VARCHAR2,lsUsuario VARCHAR2)IS
        SELECT
           etapa.val_desc des_etap_deud,
           carte.cod_peri ,
           carte.des_regi_clie ,
           carte.cod_zona_clie,
           carte.cod_secc,
           carte.cod_terr,
           carte.cod_clie,
          case
            when lsReunion   = '1'  then
            (  select to_char(a.fec_inic,'dd/MM/yyyy')
               from  cra_crono a,
                     zon_zona b
               where a.ZZON_OID_ZONA = b.oid_zona
                  and  a.PERD_OID_PERI  = lnOidActivo
                  AND a.CACT_OID_ACTI  = lnOidActividad
                  and  b.COD_ZONA  =   carte.cod_zona_clie
            )
            else ' '
           end fecha_reunion,
           NVL((SELECT TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_NOM2)
                FROM MAE_CLIEN MC
                WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') nom_clie,
           NVL((SELECT TRIM(MC.VAL_APE1)
                FROM MAE_CLIEN MC
                WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape1_clie,
               NVL((SELECT TRIM(MC.VAL_APE2)
                FROM MAE_CLIEN MC
                WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape2_clie,
            carte.num_docu_iden,
            carte.num_tele_fijo,
            carte.num_tele_trab,
            carte.num_tele_movi,
            carte.des_dpto,
            carte.des_prov,
            carte.des_dist,
            carte.des_urba,
            carte.val_dire val_dire ,
            carte.val_dire_refe val_refe,
            to_char(carte.fec_asig,'dd/MM/yyyy') fec_asig,
            to_char(carte.fec_cier,'dd/MM/yyyy') fec_cier,
            trim(to_char(carte.imp_deud_asig,'999999999999.99')) imp_deud_asig,
            trim(to_char(carte.imp_deud_pend,'999999999999.99')) imp_deud_pend,
            accio.des_acci_cobr,
            FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,1) val_refe_fami,
            FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,2) val_refe_nofa,
            to_char(carte.num_iden_cuot) num_bole_desp,
            to_char(carte.fec_docu, 'dd/MM/yyyy') fec_fact
        FROM cob_detal_asign_carte carte,
             cob_etapa_deuda_pais etapa,
             cob_accio_cobra_pais accio
        WHERE  carte.cod_etap_deud =etapa.cod_etap_deud
        AND carte.cod_ulti_gest_cobr=accio.cod_acci_cobr
        AND carte.cod_etap_deud=accio.cod_etap_deud
        AND carte.cod_usua_cobr IN (SELECT val_para FROM cob_param_grupo where cod_grup_para='COB_KEY_FAC_01')
        AND carte.cod_zona_clie IN (SELECT val_para FROM cob_param_grupo where cod_grup_para='COB_KEY_FAC_02')
        AND carte.cod_pais = pscodigopais
        AND carte.cod_soci = pscodigosociedad
        AND TO_NUMBER(carte.cod_peri) >= lsPeriodoInicio
                AND TO_NUMBER(carte.cod_peri) <=  lsPeriodoFin
        AND carte.cod_etap_deud = lsEtapa;

   TYPE interfazrec IS RECORD(
     des_etap_deud    varchar2(15),
     cod_peri         COB_DETAL_ASIGN_CARTE.cod_peri%type,
     des_regi_clie    COB_DETAL_ASIGN_CARTE.des_regi_clie%type,
     cod_zona_clie    COB_DETAL_ASIGN_CARTE.cod_zona_clie %type,
     cod_secc         COB_DETAL_ASIGN_CARTE.cod_secc%type,
     cod_terr         COB_DETAL_ASIGN_CARTE.cod_terr%type,
     cod_clie         COB_DETAL_ASIGN_CARTE.cod_clie%type,
     fecha_reunion    VARCHAR2(10),
     nom_clie         VARCHAR2(200),
     val_ape1_clie    VARCHAR2(50),
     val_ape2_clie    VARCHAR2(50),
     num_docu_iden    COB_DETAL_ASIGN_CARTE.num_docu_iden%type,
     num_tele_fijo    COB_DETAL_ASIGN_CARTE.num_tele_fijo%type,
     num_tele_trab    COB_DETAL_ASIGN_CARTE.num_tele_trab%type,
     num_tele_movi    COB_DETAL_ASIGN_CARTE.num_tele_movi%type,
     des_dpto         COB_DETAL_ASIGN_CARTE.des_dpto%type,
     des_prov         COB_DETAL_ASIGN_CARTE.des_prov%type,
     des_dist         COB_DETAL_ASIGN_CARTE.des_dist%type,
     des_urba         COB_DETAL_ASIGN_CARTE.des_urba%type,
     val_dire         COB_DETAL_ASIGN_CARTE.val_dire%type,
     val_dire_refe    COB_DETAL_ASIGN_CARTE.val_dire_refe%type,
     fec_asig         VARCHAR2(10),
     fec_cier         VARCHAR2(10),
     imp_deud_asig    VARCHAR2(15),
     imp_deud_pend    VARCHAR2(15),
     des_acci_cobr    VARCHAR2(80),
     val_refe_fami    VARCHAR2(500),
     val_refe_nofa    VARCHAR2(500),
     num_bole_desp    VARCHAR2(10),
     fec_fact    VARCHAR2(10)
   );
   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

    lsperiodoMin seg_perio_corpo.cod_peri%type;
    lsperiodoMax seg_perio_corpo.cod_peri%type;
    lsvaletapa      cob_param_gener.val_para%type;
    lsvalactividad  cob_param_gener.val_para%type;
    lsvalreunion    cob_param_gener.val_para%type;
    lnoidperiodo   seg_perio_corpo.oid_peri%type;
    lnIdPais       seg_pais.oid_pais%type;
    lnoidacti      cra_activ.oid_acti%type;
    lsCodUsua      COB_ETAPA_DEUDA_PAIS.COD_USUA_SUPE%type;

    lsvalnumdias   cob_param_gener.val_para%type;
 BEGIN

   lbabrirutlfile := TRUE;
   lnIdPais     := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
   -- recupera  parametro  :
   SELECT pg.val_para
     INTO lsvaletapa
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'COBRP001_ETAP';

   SELECT pg.val_para
     INTO lsvalreunion
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'COBRP001_IND_REU';

   SELECT pg.val_para
     INTO lsvalactividad
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'COBRP001_COD_ACTI';

   SELECT pg.val_para
     INTO lsvalnumdias
     FROM cob_param_gener pg
    WHERE pg.cod_para = 'COBRP001_AJUSTE_CIER';

   SELECT cod_usua_supe
   INTO lsCodUsua
   FROM cob_etapa_deuda_pais
   WHERE cod_etap_deud = lsvaletapa;


   -- 3.Determinar el rango de periodos ( "Periodo Inicial", "Periodo Final")
        select  min(cod_peri),
                max(cod_peri) into lsperiodoMin, lsperiodoMax
        from   cob_crono_carte  cro
        where  cro.cod_etap_deud  =  lsvaletapa
           and   ind_gene_cart  =   1
           and    trunc(sysdate) between  fec_gene_cart  and  (fec_cier + to_number(lsvalnumdias));

    if(lsvalreunion = '1') then

        select cam.oid_peri into lnoidperiodo
        from  cra_perio cam,
                 seg_perio_corpo cor,
                 bas_ctrl_fact   bas
        where cam.PERI_OID_PERI =  cor.OID_PERI
        and   ind_camp_act = 1
        and   bas.cod_peri   =   cor.COD_PERI  ;


        select  a.oid_acti into lnoidacti
        from   cra_activ a
        where   A.PAIS_OID_PAIS  = lnIdPais
            and  a.COD_ACTI  = lsvalactividad;

    end if;

   OPEN c_interfaz(lsvaletapa,lsvalreunion,lnoidperiodo,lnoidacti,lsperiodoMin,lsperiodoMax,lsCodUsua);
     LOOP
       FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;

         /* Procedimiento inicial para generar interfaz */
         IF lbabrirutlfile THEN
           gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
           lbabrirutlfile := FALSE;
         END IF;

         IF interfazrecord.COUNT > 0 THEN
           FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP


             lslinea := interfazrecord(x).des_etap_deud        || ';' ||
                        interfazrecord(x).cod_peri             || ';' ||
                        interfazrecord(x).des_regi_clie        || ';' ||
                        interfazrecord(x).cod_zona_clie        || ';' ||
                        interfazrecord(x).cod_secc             || ';' ||
                        interfazrecord(x).cod_terr             || ';' ||
                        interfazrecord(x).cod_clie             || ';' ||
                        interfazrecord(x).fecha_reunion        || ';' ||
                        interfazrecord(x).nom_clie             || ';' ||
                        interfazrecord(x).val_ape1_clie        || ';' ||
                        interfazrecord(x).val_ape2_clie        || ';' ||
                        interfazrecord(x).num_docu_iden        || ';' ||
                        interfazrecord(x).num_tele_fijo        || ';' ||
                        interfazrecord(x).num_tele_trab        || ';' ||
                        interfazrecord(x).num_tele_movi        || ';' ||
                        interfazrecord(x).des_dpto             || ';' ||
                        interfazrecord(x).des_prov             || ';' ||
                        interfazrecord(x).des_dist             || ';' ||
                        interfazrecord(x).des_urba             || ';' ||
                        interfazrecord(x).val_dire             || ';' ||
                        interfazrecord(x).val_dire_refe        || ';' ||
                        interfazrecord(x).fec_asig             || ';' ||
                        interfazrecord(x).fec_cier             || ';' ||
                        interfazrecord(x).imp_deud_asig        || ';' ||
                        interfazrecord(x).imp_deud_pend        || ';' ||
                        interfazrecord(x).des_acci_cobr        || ';' ||
                        interfazrecord(x).val_refe_fami        || ';' ||
                        interfazrecord(x).val_refe_nofa        || ';' ||
                        interfazrecord(x).num_bole_desp        || ';' ||
                        interfazrecord(x).fec_fact;

             utl_file.put_line(v_handle,lslinea);
           END LOOP;
         END IF;

       EXIT WHEN c_interfaz%NOTFOUND;
     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;

   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_recup_cobra: '||ls_sqlerrm);
 END int_pr_cob_gener_recup_cobra;

 /***************************************************************************
  Descripcion       : Procedimiento de Envio de Informacion a Proveedores
                      de Cobranza
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE INT_PR_COB_GENER_INFOR_PROVE(
  p_cod_usua                       IN   VARCHAR2)
 IS

 BEGIN

  DELETE FROM cob_tmp_entre_carte;
   --   WHERE usu_digi = p_cod_usua;

  DELETE FROM cob_tmp_actua_saldo;
   --   WHERE usu_digi = p_cod_usua;

  DELETE FROM cob_tmp_carte_envio;

  --Insertar en la tabla temporal
  INSERT INTO cob_tmp_carte_envio
   (cod_pais,cod_soci,cod_etap_deud,cod_regi,cod_zona,cod_peri,cod_cart)
   (SELECT
     cab.cod_pais,
     cab.cod_soci,
     cab.cod_etap_deud,
     cab.cod_regi,
     cab.cod_zona,
     cab.cod_peri,
     cab.cod_cart
    FROM
     cob_cabec_asign_carte   cab
    WHERE cab.ind_envi_cart = 0
      AND cab.ind_gene_liqu = 0
      AND cab.ind_envi_liqu = 0 );

   INSERT INTO cob_tmp_entre_carte
    (NUM_DOCU_IDEN,
     NUM_DOCU_REFE,
     VAL_APE1,
     VAL_APE2,
     VAL_NOM1,
     VAL_NOM2,
     CAMP_INGR,
     VAL_DIRE,
     COD_REGI,
     COD_ZONA,
     COD_SECC,
     COD_TERR,
     DES_DIST,
     DES_PROV,
     DES_DPTO,
     NUM_TELE_FIJO1,
     NUM_TELE_FIJO2,
     NUM_TELE_MOVI,
     CAMP_DEUD,
     FEC_DOCU,
     FEC_VENC,
     DIAS_MORA,
     IMP_DEUD_ORIG,
     IMP_DEUD_ASIG,
     COD_ETAP_DEUD,
     TIPO_DOCU,
     NUM_IDEN_CUOT,
     NUM_ORDE_CUOT,
     TIPO_DOCU_REF,
     NUM_DOCU_REF,
     CUOT_DOCU_REF,
     CAMP_DOCU_REF,
     COD_USUA_COBR,
     COD_CART,
     COD_CLIE,
     NUM_LOTE_ASIGN,
     IND_ENVI_CART,
     IND_GENE_LIQU,
     IND_ENVI_LIQU,
     USU_DIGI)
    SELECT
     det.num_docu_iden,
     det.num_docu_iden NUM_DOCU_REFE,
     mae.val_ape1,
     mae.val_ape2,
     mae.val_nom1,
     mae.val_nom2,
     coring.cod_peri camp_ingr,
     det.val_dire,
     det.cod_regi_clie COD_REGI,
     det.cod_zona_clie COD_ZONA,
     det.cod_secc COD_SECC,
     det.cod_terr COD_TERR,
     det.des_dist,
     det.des_prov,
     det.des_dpto,
     det.num_tele_fijo NUM_TELE_FIJO1,
     det.num_tele_trab NUM_TELE_FIJO2,
     det.num_tele_movi NUM_TELE_MOVI,
     det.cod_peri camp_deud,
     mov.fec_docu,
     mov.fec_venc,
     mov.fec_asig - mov.fec_venc dias_mora,
     mov.imp_deud_orig,
     mov.imp_deud_asig,
     cab.cod_etap_deud,
     'TD' tipo_docu,
     mov.num_iden_cuot,
     cc.num_orde_cuot,
     '  ' tipo_docu_ref,
     '          ' num_docu_ref,
     '  ' cuot_docu_ref,
     '      ' camp_docu_ref,
     det.cod_usua_cobr,
     cab.cod_cart,
     det.cod_clie,
     cab.num_lote_asign,
     cab.ind_envi_cart,
     cab.ind_gene_liqu,
     cab.ind_envi_liqu,
     p_cod_usua usu_digi
    FROM
     cob_cabec_asign_carte cab,
     cob_detal_asign_carte det,
     cob_detal_movim_carte mov,
     cob_usuar_cobra_pais cuc,
     mae_clien mae,
     mae_clien_prime_conta mpc,
     cra_perio cming,
     seg_perio_corpo coring,
     ccc_movim_cuent_corri cc,
     cob_tmp_carte_envio inf
    WHERE cab.cod_cart = det.cod_cart
      AND det.cod_cart = mov.cod_cart
      AND det.oid_clie = mov.oid_clie
      AND det.oid_clie = mae.oid_clie
      AND det.oid_clie = mpc.clie_oid_clie
      AND cming.peri_oid_peri = coring.oid_peri
      AND cming.oid_peri = mpc.perd_oid_peri
      AND cuc.ind_supe = 0
      AND cuc.cod_usua_cobr = det.cod_usua_cobr
      AND cuc.cod_pais = det.cod_pais
      AND mov.mvcc_oid_movi_cc = cc.oid_movi_cc
      AND  inf.cod_pais = cab.cod_pais
      AND  inf.cod_soci = cab.cod_soci
      AND  inf.cod_etap_deud = cab.cod_etap_deud
      AND  inf.cod_regi = cab.cod_regi
      AND  inf.cod_zona = cab.cod_zona
      AND  inf.cod_peri = cab.cod_peri
      AND  inf.cod_cart = cab.cod_cart
      AND  det.cod_pais = cab.cod_pais
      AND  det.cod_soci = cab.cod_soci
      AND  det.cod_etap_deud = cab.cod_etap_deud
      AND  det.cod_regi = cab.cod_regi
      AND  det.cod_zona = cab.cod_zona
      AND  det.cod_peri = cab.cod_peri
      AND  det.cod_cart = cab.cod_cart
      AND  mov.cod_pais = cab.cod_pais
      AND  mov.cod_etap_deud = cab.cod_etap_deud
      AND  mov.cod_peri = cab.cod_peri
      AND  mov.cod_regi = cab.cod_regi
      AND  mov.cod_zona = cab.cod_zona
      AND  mov.cod_cart = cab.cod_cart;


     INSERT INTO COB_TMP_ACTUA_SALDO(NUM_DOCU_IDEN, NUM_DOCU_REFE, IMP_DEUD_PEND, IMP_DEUD_ORIG, FEC_DOCU, CAMP_DEUD,
                                     COD_ETAP_DEUD, TIPO_DOCU, NUM_IDEN_CUOT, NUM_ORDE_CUOT, COD_USUA_COBR, COD_CART,
                                     NUM_LOTE_ASIGN, IND_ENVI_CART, IND_GENE_LIQU, IND_ENVI_LIQU, USU_DIGI)
     SELECT det.num_docu_iden, det.num_docu_iden NUM_DOCU_REFE,
           mov.imp_deud_pend, mov.imp_deud_orig, mov.fec_docu,
           det.cod_peri camp_deud, cab.cod_etap_deud, 'TD' tipo_docu,
           mov.num_iden_cuot, cc.num_orde_cuot, det.cod_usua_cobr, cab.cod_cart,
           cab.num_lote_asign, cab.ind_envi_cart, cab.ind_gene_liqu,
           cab.ind_envi_liqu, p_cod_usua usu_digi
      FROM cob_cabec_asign_carte cab,
           cob_detal_asign_carte det,
           cob_detal_movim_carte mov,
           ccc_movim_cuent_corri cc,
           cob_usuar_cobra_pais cuc
     WHERE cab.cod_cart = det.cod_cart
       AND det.cod_cart = mov.cod_cart
       AND det.oid_clie = mov.oid_clie
       AND mov.mvcc_oid_movi_cc = cc.oid_movi_cc
       AND  det.cod_pais = cab.cod_pais
       AND  det.cod_soci = cab.cod_soci
       AND  det.cod_etap_deud = cab.cod_etap_deud
       AND  det.cod_regi = cab.cod_regi
       AND  det.cod_zona = cab.cod_zona
       AND  det.cod_peri = cab.cod_peri
       AND  det.cod_cart = cab.cod_cart
       AND  mov.COD_PAIS = cab.COD_PAIS
       AND  mov.COD_ETAP_deud = cab.COD_ETAP_deud
       AND  mov.COD_PERI = cab.COD_PERI
       AND  mov.COD_REGI = cab.COD_REGI
       AND  mov.COD_ZONA = cab.COD_ZONA
       AND  mov.COD_CART = cab.COD_CART
       AND  cuc.ind_supe = 0
       AND  cuc.cod_pais = det.cod_pais
       AND  cuc.cod_usua_cobr = det.cod_usua_cobr
       AND (   (    cab.ind_envi_cart = 1
                AND cab.ind_gene_liqu = 0
                AND cab.ind_envi_liqu = 0
               )
            OR (    cab.ind_envi_cart = 1
                AND cab.ind_gene_liqu = 1
                AND cab.ind_envi_liqu = 0
               )
           );


  UPDATE cob_cabec_asign_carte cab
  SET ind_envi_liqu = 1
  WHERE cab.cod_cart IN (
   SELECT cod_cart
   FROM cob_tmp_actua_saldo
   WHERE ind_envi_cart = 1
     AND ind_gene_liqu = 1
     AND ind_envi_liqu = 0
   GROUP BY cod_cart);

  --sb marcar las carteras que ya fueron entregadas
  UPDATE cob_cabec_asign_carte cab
  SET ind_envi_cart  =  1
  WHERE EXISTS (
   SELECT NULL
   FROM cob_tmp_carte_envio e
   WHERE e.cod_cart = cab.cod_cart);


  --ssicc_co_es.FIN_PKG_INTER.FIN_PR_GENER_INTER_SALID_MAGIC(gc_cod_modu,lc_cod_inte,lv_num_lote,lv_nom_arch,p_cod_usua);

 END INT_PR_COB_GENER_INFOR_PROVE;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Entrega de Cartera
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE INT_PR_COB_ENVI_ENTRE_CARTE(
  pscodigopais                     IN   VARCHAR2,
  pscodigosistema                  IN   VARCHAR2,
  pscodigointerfaz                 IN   VARCHAR2,
  psnombrearchivo                  IN   VARCHAR2)
 IS

  CURSOR c_usuario
  IS
   SELECT DISTINCT usuario
   FROM
    (
      SELECT cod_usua_cobr usuario
      FROM
       cob_tmp_carte_envio inf,
       cob_param_asign_zonas_cobra  paz
      WHERE inf.cod_pais  =  paz.cod_pais
        AND inf.cod_etap_deud  =  paz.cod_etap_deud
        AND inf.cod_regi   =   paz.cod_regi
        AND inf.cod_zona   =  paz.cod_zona
      UNION
      SELECT DISTINCT cod_usua_cobr usuario
      FROM cob_tmp_actua_saldo);

  CURSOR c_interfaz(psUsuario VARCHAR2)
  IS
   SELECT
    substr(det.num_docu_iden, 1, 10) num_docu_iden,
    substr(det.num_docu_refe, 1, 10) num_docu_refe,
    substr(det.val_ape1, 1, 30) val_ape1,
    substr(det.val_ape2, 1, 30) val_ape2,
    substr(det.val_nom1, 1, 30) val_nom1,
    substr(det.val_nom2, 1, 30) val_nom2,
    substr(det.camp_ingr, 1, 6) camp_ingr,
    substr(det.val_dire, 1, 40) val_dire,
    substr(det.cod_regi, 1, 2) cod_regi,
    substr(det.cod_zona, 1, 4) cod_zona,
    substr(det.cod_secc, 1, 1) cod_secc,
    CAST(det.cod_terr AS VARCHAR2(6)) cod_terr,
    substr(det.des_dist, 1, 30) des_dist,
    substr(det.des_prov, 1, 20) des_prov,
    substr(det.des_dpto, 1, 20) des_dpto,
    substr(det.num_tele_fijo1, 1, 15) num_tele_fijo1,
    substr(det.num_tele_fijo2, 1, 15) num_tele_fijo2,
    substr(det.num_tele_movi, 1, 15) num_tele_movi,
    substr(det.camp_deud, 1, 6) camp_deud,
    to_char(det.fec_docu, 'YYYYMMDD') fec_docu,
    to_char(det.fec_venc, 'YYYYMMDD') fec_venc,
    substr(det.dias_mora, 1, 5) dias_mora,
    CAST(det.imp_deud_orig AS VARCHAR2(15)) imp_deud_orig,
    CAST(det.imp_deud_asig AS VARCHAR2(15)) imp_deud_asig,
    substr(det.cod_etap_deud, 1, 3) cod_etap_deud,
    substr(det.tipo_docu, 1, 2) tipo_docu,
    CAST(det.num_iden_cuot AS VARCHAR2(10)) num_iden_cuot,
    CAST(det.num_orde_cuot AS VARCHAR2(2)) num_orde_cuot,
    substr(det.tipo_docu_ref, 1, 2) tipo_docu_ref,
    substr(det.num_docu_ref, 1, 10) num_docu_ref,
    substr(det.cuot_docu_ref, 1, 2) cuot_docu_ref,
    substr(det.camp_docu_ref, 1, 6) camp_docu_ref
   FROM cob_tmp_entre_carte det
   WHERE det.COD_USUA_COBR = psUsuario;

  TYPE usuariorec IS RECORD(
   usuario                         VARCHAR2(100));

  TYPE interfazrec IS RECORD(
   cedula                          VARCHAR2(10),
   referencia                      VARCHAR2(10),
   apellidoPaterno                 VARCHAR2(30),
   apellidoMaterno                 VARCHAR2(30),
   primerNombre                    VARCHAR2(30),
   segundoNombre                   VARCHAR2(30),
   campanya                        VARCHAR2(6),
   direccion                       VARCHAR2(40),
   codigoRegion                    VARCHAR2(2),
   codigoZona                      VARCHAR2(4),
   codigoSeccion                   VARCHAR2(1),
   codigoTerritorio                VARCHAR2(6),
   barrio                          VARCHAR2(30),
   ciudad                         VARCHAR2(20),
   departamento                   VARCHAR2(20),
   telefono1                      VARCHAR2(15),
   telefono2                      VARCHAR2(15),
   telefonoMovil                  VARCHAR2(15),
   campanyaDeuda                  VARCHAR2(6),
   fechaFactura                   VARCHAR2(8),
   fechaVencimiento               VARCHAR2(8),
   diasMora                       VARCHAR2(5),
   valorFactura                   VARCHAR2(15),
   saldoFactura                   VARCHAR2(15),
   etapa                          VARCHAR2(3),
   tipoDocumento                  VARCHAR2(2),
   numeroDocumento                VARCHAR2(10),
   cuotaDocumento                 VARCHAR2(2),
   tipoDocumentoReferencia        VARCHAR2(2),
   numeroDocumentoReferencia      VARCHAR2(10),
   cuotaDocumentoReferencia       VARCHAR2(2),
   campanyaDocumentoReferencia    VARCHAR2(6));

  TYPE usuariorectab IS TABLE OF usuariorec;
  usuariorecord usuariorectab;

  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;
  lslinea         VARCHAR2(1000);
  lsnombrearchivo VARCHAR2(50);
  lbabrirutlfile  BOOLEAN;
  lsNombreArchivoFecha VARCHAR2(50);

 BEGIN

  OPEN c_usuario;
  LOOP
   FETCH c_usuario BULK COLLECT INTO usuariorecord LIMIT w_filas;
   IF usuariorecord.count > 0 THEN
    FOR y IN usuariorecord.first .. usuariorecord.last LOOP

     lbabrirutlfile := TRUE;

     lsNombreArchivoFecha := usuariorecord(y).usuario || '_CARTERA' || TO_CHAR(SYSDATE,'YYMMDD');

     /* Generando Archivo de Texto (Detalle) */
     OPEN c_interfaz(usuariorecord(y).usuario);
     LOOP
      FETCH c_interfaz BULK COLLECT INTO interfazrecord LIMIT w_filas;

       /* Procedimiento inicial para generar interfaz */
       IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(
           pscodigopais,
           pscodigosistema,
           pscodigointerfaz,
           lsNombreArchivoFecha,
           lsdirtempo,
           lsnombrearchivo,
           v_handle);
        lbabrirutlfile := FALSE;
       END IF;

              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  lslinea := interfazrecord(x).cedula                      || ';' ||
                             interfazrecord(x).referencia                  || ';' ||
                             interfazrecord(x).apellidoPaterno             || ';' ||
                             interfazrecord(x).apellidoMaterno             || ';' ||
                             interfazrecord(x).primerNombre                || ';' ||
                             interfazrecord(x).segundoNombre               || ';' ||
                             interfazrecord(x).campanya                    || ';' ||
                             interfazrecord(x).direccion                   || ';' ||
                             interfazrecord(x).codigoRegion                || ';' ||
                             interfazrecord(x).codigoZona                  || ';' ||
                             interfazrecord(x).codigoSeccion               || ';' ||
                             interfazrecord(x).codigoTerritorio            || ';' ||
                             interfazrecord(x).barrio                      || ';' ||
                             interfazrecord(x).ciudad                      || ';' ||
                             interfazrecord(x).departamento                || ';' ||
                             interfazrecord(x).telefono1                   || ';' ||
                             interfazrecord(x).telefono2                   || ';' ||
                             interfazrecord(x).telefonoMovil               || ';' ||
                             interfazrecord(x).campanyaDeuda               || ';' ||
                             interfazrecord(x).fechaFactura                || ';' ||
                             interfazrecord(x).fechaVencimiento            || ';' ||
                             interfazrecord(x).diasMora                    || ';' ||
                             interfazrecord(x).valorFactura                || ';' ||
                             interfazrecord(x).saldoFactura                || ';' ||
                             interfazrecord(x).etapa                       || ';' ||
                             interfazrecord(x).tipoDocumento               || ';' ||
                             interfazrecord(x).numeroDocumento             || ';' ||
                             interfazrecord(x).cuotaDocumento              || ';' ||
                             interfazrecord(x).tipoDocumentoReferencia     || ';' ||
                             interfazrecord(x).numeroDocumentoReferencia   || ';' ||
                             interfazrecord(x).cuotaDocumentoReferencia    || ';' ||
                             interfazrecord(x).campanyaDocumentoReferencia;

                  utl_file.put_line(v_handle, lslinea);

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
                                                     lsNombreArchivoFecha,
                                                     lsnombrearchivo);
            END IF;

          END LOOP;
        END IF;

        EXIT WHEN c_usuario%NOTFOUND;
    END LOOP;
    CLOSE c_usuario;

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);



  RETURN;

 EXCEPTION

  WHEN OTHERS THEN

   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM,1,1000);
   raise_application_error(-20123,
                              'ERROR int_pr_cob_envi_entre_carte: ' || ls_sqlerrm);
 END INT_PR_COB_ENVI_ENTRE_CARTE;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Actualizacion de Saldos
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_actua_saldo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS

    CURSOR c_usuario IS
       select   distinct   usuario from  (
            select   cod_usua_cobr usuario
            from   cob_tmp_carte_envio inf,
                      cob_param_asign_zonas_cobra  paz
            where  inf.cod_pais  =  paz.cod_pais
            and  inf.cod_etap_deud  =  paz.cod_etap_deud
            and  inf.cod_regi   =   paz.cod_regi
            and  inf.cod_zona   =  paz.cod_zona
            union
            select  distinct   cod_usua_cobr usuario
            from  COB_TMP_ACTUA_SALDO
          ) ;

    CURSOR c_interfaz(psUsuario VARCHAR2) IS
        SELECT substr(det.num_docu_iden, 1, 10) num_docu_iden, substr(det.num_docu_refe, 1, 10) num_docu_refe,
               CAST(det.imp_deud_pend AS VARCHAR2(15)) imp_deud_pend, CAST(det.imp_deud_orig AS VARCHAR2(15)) imp_deud_orig,
               to_char(det.fec_docu, 'YYYYMMDD') fec_docu, det.camp_deud, substr(det.cod_etap_deud, 1, 3) cod_etap_deud, det.tipo_docu,
               CAST(det.num_iden_cuot AS VARCHAR2(10)) num_iden_cuot, CAST(det.num_orde_cuot AS VARCHAR2(2)) num_orde_cuot
          FROM COB_TMP_ACTUA_SALDO det
         WHERE det.COD_USUA_COBR = psUsuario;

    TYPE usuariorec IS RECORD(
      usuario                        VARCHAR2(100));

    TYPE interfazrec IS RECORD(
      cedula                         VARCHAR2(10),
      referencia                     VARCHAR2(10),
      saldoActualCargo               VARCHAR2(15),
      valorCargo                     VARCHAR2(15),
      fechaCargo                     VARCHAR2(8),
      campanya                       VARCHAR2(6),
      etapa                          VARCHAR2(3),
      tipoDocumento                  VARCHAR2(2),
      numeroDocumento                VARCHAR2(10),
      cuota                          VARCHAR2(2));

    TYPE usuariorectab IS TABLE OF usuariorec;
    usuariorecord usuariorectab;

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lsNombreArchivoFecha VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    OPEN c_usuario;
    LOOP
      FETCH c_usuario BULK COLLECT
        INTO usuariorecord LIMIT w_filas;
      IF usuariorecord.count > 0 THEN
        FOR y IN usuariorecord.first .. usuariorecord.last
        LOOP

            lbabrirutlfile := TRUE;

            lsNombreArchivoFecha := usuariorecord(y).usuario || '_SALDOS' || TO_CHAR(SYSDATE,'YYMMDD');

            /* Generando Archivo de Texto (Detalle) */
            OPEN c_interfaz(usuariorecord(y).usuario);
            LOOP
              FETCH c_interfaz BULK COLLECT
                INTO interfazrecord LIMIT w_filas;
              /* Procedimiento inicial para generar interfaz */
              IF lbabrirutlfile THEN
                gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                       pscodigosistema,
                                                       pscodigointerfaz,
                                                       lsNombreArchivoFecha,
                                                       lsdirtempo,
                                                       lsnombrearchivo,
                                                       v_handle);
                lbabrirutlfile := FALSE;
              END IF;
              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  lslinea := interfazrecord(x).cedula                      || ';' ||
                             interfazrecord(x).referencia                  || ';' ||
                             interfazrecord(x).saldoActualCargo            || ';' ||
                             interfazrecord(x).valorCargo                  || ';' ||
                             interfazrecord(x).fechaCargo                  || ';' ||
                             interfazrecord(x).campanya                    || ';' ||
                             interfazrecord(x).etapa                       || ';' ||
                             interfazrecord(x).tipoDocumento               || ';' ||
                             interfazrecord(x).numeroDocumento             || ';' ||
                             interfazrecord(x).cuota;

                  utl_file.put_line(v_handle, lslinea);

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
                                                     lsNombreArchivoFecha,
                                                     lsnombrearchivo);
            END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_usuario%NOTFOUND;
    END LOOP;
    CLOSE c_usuario;

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_cob_envi_actua_saldo: ' || ls_sqlerrm);
  END int_pr_cob_envi_actua_saldo;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Retroalimentacion
                      de Gestiones
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_retro_gesti
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS

    CURSOR c_usuario IS
        select   distinct   usuario from  (
            select   cod_usua_cobr usuario
            from   cob_tmp_carte_envio inf,
                      cob_param_asign_zonas_cobra  paz
            where  inf.cod_pais  =  paz.cod_pais
            and  inf.cod_etap_deud  =  paz.cod_etap_deud
            and  inf.cod_regi   =   paz.cod_regi
            and  inf.cod_zona   =  paz.cod_zona
            union
            select  distinct   cod_usua_cobr usuario
            from  COB_TMP_ACTUA_SALDO
          ) ;

    CURSOR c_interfaz(psUsuario VARCHAR2) IS
        WITH cartera AS
             (SELECT DISTINCT wr.cod_clie, wr.num_docu_iden cedula, wr.camp_deud,
                              edp.num_secu_etap secu_etap_ref, wr.NUM_DOCU_REF referencia,
                              wr.COD_USUA_COBR
                         FROM COB_TMP_ENTRE_CARTE wr, cob_etapa_deuda_pais edp
                        WHERE edp.cod_etap_deud = wr.cod_etap_deud
                          AND wr.cod_usua_cobr = psUsuario)
        SELECT SUBSTR(car.cod_usua_cobr, 1, 10), ges.COD_ETAP_DEUD, SUBSTR(car.cedula, 1, 10), SUBSTR(car.referencia, 1, 10), ges.COD_PERI, to_char(ges.FEC_GEST, 'YYYYMMDD') FEC_GEST, ges.VAL_HORA_GEST,
               substr(ges.VAL_TIPO_MEDI_GEST, 1, 1) VAL_TIPO_MEDI_GEST, substr(ges.COD_ACCI_COBR, 1, 2) COD_ACCI_COBR,
               to_char(ges.FEC_ACCI_COBR, 'YYYYMMDD') FEC_ACCI_COBR, CAST(ges.PPA_IMP_PAGO AS VARCHAR(15)) PPA_IMP_PAGO,
               substr(REGEXP_REPLACE(ges.VAL_NUEV_DIRE, '[[:cntrl:]]', ' '), 1, 15) VAL_NUEV_DIRE, substr(ges.VAL_NUEV_TELE, 1, 15) VAL_NUEV_TELE,
               substr(REGEXP_REPLACE(ges.VAL_OBSE, '[[:cntrl:]]', ' '), 1, 300) VAL_OBSE, substr(ges.COD_USUA_COBR_PAIS, 1, 10) COD_USUA_COBR_PAIS
          FROM cartera car, cob_gesti_cobra_pais ges, cob_etapa_deuda_pais edp2
         WHERE car.cod_clie = ges.cod_clie
           AND ges.cod_etap_deud = edp2.cod_etap_deud
           AND edp2.num_secu_etap < car.secu_etap_ref
           AND ges.cod_peri = car.camp_deud;

    TYPE usuariorec IS RECORD(
      usuario              VARCHAR2(100));

    TYPE interfazrec IS RECORD(
      proveedorAsignado    VARCHAR2(10),
      etapa                VARCHAR2(3),
      cedula               VARCHAR2(10),
      referencia           VARCHAR2(10),
      campanyaDeuda        VARCHAR2(6),
      fechaGestion         VARCHAR2(8),
      horaGestion          VARCHAR2(8),
      medioGestion         VARCHAR2(1),
      codigoAccion         VARCHAR2(2),
      fechaCompromiso      VARCHAR2(8),
      valorCompromiso      VARCHAR2(15),
      nuevaDireccion       VARCHAR2(15),
      nuevoTelefono        VARCHAR2(15),
      observaciones        VARCHAR2(300),
      usuarioGestor        VARCHAR2(10));

    TYPE usuariorectab IS TABLE OF usuariorec;
    usuariorecord usuariorectab;

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lsNombreArchivoFecha VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    /* Generando Archivo de Texto (Detalle) */

    OPEN c_usuario;
    LOOP
      FETCH c_usuario BULK COLLECT
        INTO usuariorecord LIMIT w_filas;
      IF usuariorecord.count > 0 THEN
        FOR y IN usuariorecord.first .. usuariorecord.last
        LOOP

            lbabrirutlfile := TRUE;

            lsNombreArchivoFecha := usuariorecord(y).usuario || '_' || psnombrearchivo;

            OPEN c_interfaz(usuariorecord(y).usuario);
            LOOP
              FETCH c_interfaz BULK COLLECT
                INTO interfazrecord LIMIT w_filas;
              /* Procedimiento inicial para generar interfaz */
              IF lbabrirutlfile THEN
                gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                       pscodigosistema,
                                                       pscodigointerfaz,
                                                       lsNombreArchivoFecha,
                                                       lsdirtempo,
                                                       lsnombrearchivo,
                                                       v_handle);
                lbabrirutlfile := FALSE;
              END IF;
              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  lslinea := interfazrecord(x).proveedorAsignado   || ';' ||
                             interfazrecord(x).etapa               || ';' ||
                             interfazrecord(x).cedula              || ';' ||
                             interfazrecord(x).referencia          || ';' ||
                             interfazrecord(x).campanyaDeuda       || ';' ||
                             interfazrecord(x).fechaGestion        || ';' ||
                             interfazrecord(x).horaGestion         || ';' ||
                             interfazrecord(x).medioGestion        || ';' ||
                             interfazrecord(x).codigoAccion        || ';' ||
                             interfazrecord(x).fechaCompromiso     || ';' ||
                             interfazrecord(x).valorCompromiso     || ';' ||
                             interfazrecord(x).nuevaDireccion      || ';' ||
                             interfazrecord(x).nuevoTelefono       || ';' ||
                             interfazrecord(x).observaciones       || ';' ||
                             interfazrecord(x).usuarioGestor;

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
                                                     lsNombreArchivoFecha,
                                                     lsnombrearchivo);
            END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_usuario%NOTFOUND;
    END LOOP;
    CLOSE c_usuario;

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_cob_envi_retro_gesti: ' || ls_sqlerrm);
  END int_pr_cob_envi_retro_gesti;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Control de Registros
                      Enviados
  Fecha Creacion    : 30/01/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
  PROCEDURE int_pr_cob_envi_contr_regis
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS

    CURSOR c_usuario IS
       select   distinct   usuario from  (
            select   cod_usua_cobr usuario
            from   cob_tmp_carte_envio inf,
                      cob_param_asign_zonas_cobra  paz
            where  inf.cod_pais  =  paz.cod_pais
            and  inf.cod_etap_deud  =  paz.cod_etap_deud
            and  inf.cod_regi   =   paz.cod_regi
            and  inf.cod_zona   =  paz.cod_zona
            union
            select  distinct   cod_usua_cobr usuario
            from  COB_TMP_ACTUA_SALDO
          ) ;

    CURSOR c_interfaz(psUsuario VARCHAR2) IS
         SELECT
         SUBSTR(psUsuario, 1, 6) PROVEEDOR, TO_CHAR(SYSDATE,'YYYYMMDD') FECHA, 'CARTERA' ARCHIVO, count(*) REGISTROS, nvl(SUM (imp_deud_asig), 0) VALOR
         FROM COB_TMP_ENTRE_CARTE
         WHERE cod_usua_cobr = psUsuario
         UNION ALL
         SELECT
         SUBSTR(psUsuario, 1, 6) PROVEEDOR, TO_CHAR(SYSDATE,'YYYYMMDD') FECHA, 'SALDOS' ARCHIVO, count(*) REGISTROS, nvl(SUM (imp_deud_pend), 0) VALOR
         FROM COB_TMP_ACTUA_SALDO
         WHERE cod_usua_cobr = psUsuario
         UNION ALL
         SELECT SUBSTR(psUsuario, 1, 6) PROVEEDOR, TO_CHAR(SYSDATE,'YYYYMMDD') FECHA, 'RETROGES' ARCHIVO, count(*) REGISTROS, 0 VALOR
          FROM cob_gesti_cobra_pais ges, cob_etapa_deuda_pais edp2,
          (SELECT DISTINCT wr.cod_clie, wr.num_docu_iden cedula, wr.camp_deud,
                              edp.num_secu_etap secu_etap_ref, wr.NUM_DOCU_REF referencia,
                              wr.COD_USUA_COBR
                         FROM COB_TMP_ENTRE_CARTE wr, cob_etapa_deuda_pais edp
                        WHERE edp.cod_etap_deud = wr.cod_etap_deud
                          AND wr.cod_usua_cobr = psUsuario) CAR
         WHERE car.cod_clie = ges.cod_clie
           AND ges.cod_etap_deud = edp2.cod_etap_deud
           AND edp2.num_secu_etap < car.secu_etap_ref
           AND ges.cod_peri = car.camp_deud;

    TYPE usuariorec IS RECORD(
      usuario              VARCHAR2(100));

    TYPE usuariorectab IS TABLE OF usuariorec;
    usuariorecord usuariorectab;

    TYPE interfazrec IS RECORD(
      proveedor           VARCHAR2(6),
      fecha               VARCHAR2(8),
      archivo             VARCHAR2(10),
      registros           VARCHAR2(5),
      valor               VARCHAR2(15));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
    lsNombreArchivoFecha VARCHAR2(50);

  BEGIN

    OPEN c_usuario;
    LOOP
      FETCH c_usuario BULK COLLECT
        INTO usuariorecord LIMIT w_filas;
      IF usuariorecord.count > 0 THEN
        FOR y IN usuariorecord.first .. usuariorecord.last
        LOOP

            lbabrirutlfile := TRUE;

            lsNombreArchivoFecha := usuariorecord(y).usuario || '_' || psnombrearchivo || TO_CHAR(SYSDATE,'YYMMDD');

            OPEN c_interfaz(usuariorecord(y).usuario);
            LOOP
              FETCH c_interfaz BULK COLLECT
                INTO interfazrecord LIMIT w_filas;
              /* Procedimiento inicial para generar interfaz */
              IF lbabrirutlfile THEN
                gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                       pscodigosistema,
                                                       pscodigointerfaz,
                                                       lsNombreArchivoFecha,
                                                       lsdirtempo,
                                                       lsnombrearchivo,
                                                       v_handle);
                lbabrirutlfile := FALSE;
              END IF;
              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  lslinea := interfazrecord(x).proveedor      || ';' ||
                             interfazrecord(x).fecha          || ';' ||
                             interfazrecord(x).archivo        || ';' ||
                             interfazrecord(x).registros      || ';' ||
                             interfazrecord(x).valor;

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
                                                     lsNombreArchivoFecha,
                                                     lsnombrearchivo);
            END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_usuario%NOTFOUND;
    END LOOP;
    CLOSE c_usuario;

     /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_cob_envi_contr_regis: ' || ls_sqlerrm);
  END int_pr_cob_envi_contr_regis;

/***************************************************************************
  Descripcion       : Procedimiento que genera el Envio de Archivos a OCR1
  Fecha Creacion    : 13/02/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
 PROCEDURE int_pr_cob_gener_archi_ocr1(
   pscodigopais      VARCHAR2,
   pscodigosistema   VARCHAR2,
   pscodigointerfaz  VARCHAR2,
   psnombrearchivo   VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;
   lsNombreArchivoFecha VARCHAR2(50);

   CURSOR c_interfaz IS
        SELECT DISTINCT to_char(SYSDATE, 'YYYYMMDD') FECHA, substr(ec.NUM_DOCU_IDEN, 1, 30) NUM_DOCU_IDEN, substr(ec.COD_USUA_COBR, 1, 15) COD_USUA_COBR
          FROM cob_tmp_entre_carte ec;

   TYPE interfazrec IS RECORD(
     fecha          VARCHAR2(8),
     cedula         VARCHAR2(30),
     proveedor      VARCHAR2(15));

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN
/* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    lsNombreArchivoFecha := psnombrearchivo || TO_CHAR(SYSDATE,'YYMMDD');

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               lsNombreArchivoFecha,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).fecha        || ';' ||
                     interfazrecord(x).cedula       || ';' ||
                     interfazrecord(x).proveedor;

          utl_file.put_line(v_handle, lslinea);
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
                                             lsNombreArchivoFecha,
                                             lsnombrearchivo);
    END IF;

    /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

   RETURN;
 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_archi_ocr1: '||ls_sqlerrm);
 END int_pr_cob_gener_archi_ocr1;

/***************************************************************************
  Descripcion       : Procedimiento que genera el Envio de Archivos a OCR2
  Fecha Creacion    : 13/02/2013
  Autor             : Aurelio Oviedo
***************************************************************************/
 PROCEDURE int_pr_cob_gener_archi_ocr2(
   pscodigopais      VARCHAR2,
   pscodigosistema   VARCHAR2,
   pscodigointerfaz  VARCHAR2,
   psnombrearchivo   VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;
   lsNombreArchivoFecha VARCHAR2(50);

   CURSOR c_interfaz IS
        SELECT substr(cob.cod_usua_cobr, 1, 15) cod_usua_cobr, substr(cob.val_serv_ftp, 1, 50) val_serv_ftp, substr(cob.val_usua_ftp, 1, 25) val_usua_ftp,
               substr(cob.val_clav_ftp, 1, 25) val_clav_ftp, substr(cob.val_dire_ftp, 1, 25) val_dire_ftp
          FROM (SELECT DISTINCT cod_usua_cobr
                           FROM cob_tmp_entre_carte) prov,
               cob_usuar_cobra_pais cob
         WHERE prov.cod_usua_cobr = cob.cod_usua_cobr;

   TYPE interfazrec IS RECORD(
     proveedor      VARCHAR2(15),
     servidorFTP    VARCHAR2(50),
     usuarioFTP     VARCHAR2(25),
     claveFTP       VARCHAR2(25),
     carpetaFTP     VARCHAR2(25));

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN
/* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    lsNombreArchivoFecha := psnombrearchivo || TO_CHAR(SYSDATE,'YYMMDD');

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               lsNombreArchivoFecha,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).proveedor        || ';' ||
                     interfazrecord(x).servidorFTP      || ';' ||
                     interfazrecord(x).usuarioFTP       || ';' ||
                     interfazrecord(x).claveFTP         || ';' ||
                     interfazrecord(x).carpetaFTP;

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz*/
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             lsNombreArchivoFecha,
                                             lsnombrearchivo);
    END IF;

    /*Archivo dummy para que el proceso no se caiga */
    gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
    utl_file.put_line(v_handle, '0');
    utl_file.fclose(v_handle);

    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

   RETURN;
 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_cob_gener_archi_ocr2: '||ls_sqlerrm);
 END int_pr_cob_gener_archi_ocr2;

/**********************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de la Gestion Cobranza de Terceros
                      a SICC
  Fecha Creacion    : 28/02/2013
  Autor             : L. Sebastian Guerra Ch.
  **********************************************************************************/
  PROCEDURE int_pr_cob_recep_gesti_cobra
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    pscodigousuario           VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

    TYPE t_val_cedu IS TABLE OF VARCHAR2(10);
    TYPE t_cod_clie IS TABLE OF VARCHAR2(15);
    TYPE t_val_refe IS TABLE OF VARCHAR2(10);
    TYPE t_val_camp IS TABLE OF VARCHAR2(6);
    TYPE t_cod_etap IS TABLE OF VARCHAR2(3);
    TYPE t_fec_gest IS TABLE OF VARCHAR2(8);
    TYPE t_val_hora_gest IS TABLE OF VARCHAR2(8);
    TYPE t_cod_medi_gest IS TABLE OF VARCHAR2(1);
    TYPE t_cod_acci IS TABLE OF VARCHAR2(2);
    TYPE t_cod_acti_moti IS TABLE OF VARCHAR2(1);
    TYPE t_cod_acti_soli IS TABLE OF VARCHAR2(2);
    TYPE t_fec_comp IS TABLE OF VARCHAR2(8);
    TYPE t_val_impo_comp IS TABLE OF VARCHAR2(15);
    TYPE t_val_nuev_dire IS TABLE OF VARCHAR2(30);
    TYPE t_val_nuev_tele IS TABLE OF VARCHAR2(15);
    TYPE t_val_obse IS TABLE OF VARCHAR2(300);
    TYPE t_cod_usua_gest IS TABLE OF VARCHAR2(10);

    v_val_cedu t_val_cedu := t_val_cedu();
    v_cod_clie t_cod_clie := t_cod_clie();
    v_val_refe t_val_refe := t_val_refe();
    v_val_camp t_val_camp := t_val_camp();
    v_cod_etap t_cod_etap := t_cod_etap();
    v_fec_gest t_fec_gest := t_fec_gest();
    v_val_hora_gest t_val_hora_gest := t_val_hora_gest();
    v_cod_medi_gest t_cod_medi_gest := t_cod_medi_gest();
    v_cod_acci t_cod_acci := t_cod_acci();
    v_cod_acti_moti t_cod_acti_moti := t_cod_acti_moti();
    v_cod_acti_soli t_cod_acti_soli := t_cod_acti_soli();
    v_fec_comp t_fec_comp := t_fec_comp();
    v_val_impo_comp t_val_impo_comp := t_val_impo_comp();
    v_val_nuev_dire t_val_nuev_dire := t_val_nuev_dire();
    v_val_nuev_tele t_val_nuev_tele := t_val_nuev_tele();
    v_val_obse t_val_obse := t_val_obse();
    v_cod_usua_gest t_cod_usua_gest := t_cod_usua_gest();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    lv_long_docu_iden COB_PARAM_GENER.VAL_PARA%TYPE;
    ls_cod_clie MAE_CLIEN.COD_CLIE%TYPE;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    SELECT VAL_PARA
    INTO lv_long_docu_iden
    FROM COB_PARAM_GENER
    WHERE COD_PARA = 'VAL_LONG_DOCU_IDEN';

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN

          utl_file.get_line(v_handle,
                            lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                    v_val_cedu.extend;
                    v_cod_clie.extend;

                    v_val_cedu(i) := substr(lslinea,
                                          inicio,
                                          longitud);

                   BEGIN
                    SELECT MAE.COD_CLIE
                    INTO ls_cod_clie
                    FROM MAE_CLIEN_IDENT IDE, MAE_CLIEN MAE
                    WHERE IDE.CLIE_OID_CLIE = MAE.OID_CLIE
                    AND IDE.NUM_DOCU_IDEN = substr(v_val_cedu(i),1,lv_long_docu_iden);
                   EXCEPTION WHEN NO_DATA_FOUND THEN
                       ls_cod_clie :=null;
                   END;

                    v_cod_clie(i) := ls_cod_clie;

                ELSIF (posicion = 2) THEN
                    v_val_refe.extend;
                    v_val_refe(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 3) THEN
                  v_val_camp.extend;
                  v_val_camp(i) := substr(lslinea,
                                               inicio,
                                               longitud);
                ELSIF (posicion = 4) THEN
                  v_cod_etap.extend;
                  v_cod_etap(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 5) THEN
                  v_fec_gest.extend;
                  v_fec_gest(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 6) THEN
                  v_val_hora_gest.extend;
                  v_val_hora_gest(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 7) THEN
                  v_cod_medi_gest.extend;
                  v_cod_medi_gest(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 8) THEN
                  v_cod_acci.extend;
                  v_cod_acci(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 9) THEN
                  v_cod_acti_moti.extend;
                  v_cod_acti_moti(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 10) THEN
                  v_cod_acti_soli.extend;
                  v_cod_acti_soli(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 11) THEN
                  v_fec_comp.extend;
                  v_fec_comp(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 12) THEN
                  v_val_impo_comp.extend;
                  v_val_impo_comp(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 13) THEN
                  v_val_nuev_dire.extend;
                  v_val_nuev_dire(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 14) THEN
                  v_val_nuev_tele.extend;
                  v_val_nuev_tele(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 15) THEN
                  v_val_obse.extend;
                  v_val_obse(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                ELSIF (posicion = 16) THEN
                  v_cod_usua_gest.extend;
                  v_cod_usua_gest(i) := substr(lslinea,
                                          inicio,
                                          longitud);
                END IF;

                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    --obtenemos num lote del archiv

    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_val_cedu.count
    LOOP
      IF v_cod_clie(i) IS NULL THEN
            INSERT INTO COB_GESTI_COBRA_TERCE_ERRO
            (VAL_CEDU,
             VAL_REFE,
             VAL_CAMP,
             COD_ETAP,
             FEC_GEST,
             VAL_HORA_GEST,
             COD_MEDI_GEST,
             COD_ACCI,
             COD_ACTI_MOTI,
             COD_ACTI_SOLI,
             FEC_COMP,
             VAL_IMPO_COMP,
             VAL_NUEV_DIRE,
             VAL_NUEV_TELE,
             VAL_OBSE,
             COD_USUA_GEST,
             VAL_NOMB_FILE,
             FEC_CREA,
             USU_CREA)
            VALUES
            (v_val_cedu(i),
             v_val_refe(i),
             v_val_camp(i),
             v_cod_etap(i),
             v_fec_gest(i),
             v_val_hora_gest(i),
             v_cod_medi_gest(i),
             v_cod_acci(i),
             v_cod_acti_moti(i),
             v_cod_acti_soli(i),
             v_fec_comp(i),
             v_val_impo_comp(i),
             v_val_nuev_dire(i),
             v_val_nuev_tele(i),
             v_val_obse(i),
             v_cod_usua_gest(i),
             psnombrearchivo,
             SYSDATE,
             pscodigousuario);
      ELSE
        INSERT INTO COB_GESTI_COBRA_PAIS
        (
        COD_ETAP_DEUD,
        COD_PERI,
        VAL_TIPO_MOTI_INCU,
        VAL_TIPO_MEDI_GEST,
        VAL_ESTA_SOLI_CRED,
        VAL_NUEV_DIRE,
        VAL_NUEV_TELE,
        COD_CLIE,
        COD_ACCI_COBR,
        FEC_GEST,
        VAL_HORA_GEST,
        VAL_OBSE,
        FEC_ACCI_COBR,
        PPA_IMP_PAGO,
        COD_USUA_COBR_PAIS
         )
        VALUES
        (v_cod_etap(i),
         v_val_camp(i),
         v_cod_acti_moti(i),
         v_cod_medi_gest(i),
         v_cod_acti_soli(i),
         v_val_nuev_dire(i),
         v_val_nuev_tele(i),
         v_cod_clie(i),
         v_cod_acci(i),
         TO_DATE(v_fec_gest(i),'yyyyMMdd'),
         v_val_hora_gest(i),
         v_val_obse(i),
         TO_DATE(v_fec_comp(i),'yyyyMMdd'),
         TO_NUMBER(v_val_impo_comp(i)),
         v_cod_usua_gest(i));
      END IF;
    END LOOP;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_COB_RECEP_GESTI_COBRA: ' || ls_sqlerrm);

  END int_pr_cob_recep_gesti_cobra;

/****************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control Registros Recibidos
                      a SICC
  Fecha Creacion    : 28/02/2013
  Autor             : L. Sebastian Guerra Ch.
  ****************************************************************************************/
  PROCEDURE int_pr_cob_recep_contr_regis
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    pscodigousuario           VARCHAR2,
    pslistaarchivos           VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

    TYPE t_cod_usua_cobr_exte IS TABLE OF VARCHAR2(6);
    TYPE t_val_fec_proc IS TABLE OF VARCHAR2(8);
    TYPE t_val_arch_rece IS TABLE OF VARCHAR2(10);
    TYPE t_num_regi_rece IS TABLE OF VARCHAR2(5);
    TYPE t_val_impo_rece IS TABLE OF VARCHAR2(15);


    v_cod_usua_cobr_exte t_cod_usua_cobr_exte := t_cod_usua_cobr_exte();
    v_val_fec_proc t_val_fec_proc := t_val_fec_proc();
    v_val_arch_rece t_val_arch_rece := t_val_arch_rece();
    v_num_regi_rece t_num_regi_rece := t_num_regi_rece();
    v_val_impo_rece t_val_impo_rece := t_val_impo_rece();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN

          utl_file.get_line(v_handle,
                            lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                    v_cod_usua_cobr_exte.extend;
                    v_cod_usua_cobr_exte(i) := substr(lslinea,
                                                      inicio,
                                                      longitud);

                ELSIF (posicion = 2) THEN
                    v_val_fec_proc.extend;
                    v_val_fec_proc(i) := substr(lslinea,
                                                inicio,
                                                longitud);
                ELSIF (posicion = 3) THEN
                    v_val_arch_rece.extend;
                    v_val_arch_rece(i) := substr(lslinea,
                                                 inicio,
                                                 longitud);
                ELSIF (posicion = 4) THEN
                    v_num_regi_rece.extend;
                    v_num_regi_rece(i) := substr(lslinea,
                                                 inicio,
                                                 longitud);
                ELSIF (posicion = 5) THEN
                    v_val_impo_rece.extend;
                    v_val_impo_rece(i) := substr(lslinea,
                                                 inicio,
                                                 longitud);
                END IF;

                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    --obtenemos num lote del archiv

    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_usua_cobr_exte.count
    LOOP

        INSERT INTO COB_RECEP_INTER_CONTR
        (COD_USUA_COBR_EXTE,
         VAL_FEC_PROC,
         VAL_ARCH_RECE,
         NUM_REGI_RECE,
         VAL_IMPO_RECE,
         VAL_NOMB_ARCH,
         FEC_CREA,
         USU_CREA)
        VALUES
        (v_cod_usua_cobr_exte(i),
         v_val_fec_proc(i),
         v_val_arch_rece(i),
         to_number(v_num_regi_rece(i)),
         to_number(REGEXP_REPLACE(v_val_impo_rece(i), '[^0-9]+', '')),
         int_fn_cob_busca_nombr_archi( pslistaarchivos, v_cod_usua_cobr_exte(i)),
         sysdate,
         pscodigousuario);
    END LOOP;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_COB_RECEP_CONTR_REGIS: ' || ls_sqlerrm);

  END int_pr_cob_recep_contr_regis;

  /************************************************************************
    Descripcion : Funcion que obtiene el el nombre del archivo a partir de una lista
    Creado     : Sebastian Guerra
    Fecha      : 11/04/2013
    Parametros:
         p_list  Cadena de nombres de archivos separados por comas
         p_nombre   Nombre de archivo a buscaqr en la cadena
   ************************************************************************/
    FUNCTION int_fn_cob_busca_nombr_archi
    (
        p_list varchar2,
        p_nombre varchar2
    )
    RETURN varchar2
    IS
        listaCampos        FDV_PKG_PROCE.split_tbl;
        listaCamposNombre  FDV_PKG_PROCE.split_tbl;
        retVal varchar2(128);
    BEGIN

        listaCampos := FDV_PKG_PROCE.fdv_fn_split(p_list);


        FOR i IN 1 .. listaCampos.count
        LOOP
            IF listaCampos(i) like trim(p_nombre)||'%' THEN
                listaCamposNombre := FDV_PKG_PROCE.fdv_fn_split(listaCampos(i), '_');
                retVal := listaCamposNombre(2);
                exit;
            END IF;

        END LOOP;


        return substr(retVal, 0, 20);

    END int_fn_cob_busca_nombr_archi;

 /****************************************************************************************
  Descripcion       : Genera Interfaz de Recuperaci?n Cobranza FFVV Datamart.

  Fecha Creacion    : 25/07/2013
  Autor             : Yahir Rivas Luna.
  ****************************************************************************************/

PROCEDURE INT_PR_COB_ENVIO_RECOB_FFVV
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2
  ) IS

    CURSOR c_interfaz IS
     SELECT cbz.cod_peri, cbz.cod_regi,cbz.cod_zona, cbz.cod_secc,
     TO_CHAR(SUM(cbz.imp_fact_neto), '999999999999999999.99') imp_fact_neto,
     TO_CHAR(SUM(cbz.cob_dias_21), '999999999999999999.99') cob21,
     TO_CHAR(SUM(
     CASE
       WHEN cbz.fec_cier_vent IS NULL THEN
          cbz.cob_dias_31
       ELSE
          cbz.cob_dias_vent
       END), '999999999999999999.99') cob31,
     TO_CHAR(SUM(cbz.cob_dias_36), '999999999999999999.99') cob36
       from cob_repor_estad_recup_cobra cbz
       WHERE cbz.cod_peri >= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa((SELECT COD_PERI FROM BAS_CTRL_FACT  WHERE STA_CAMP=0 AND IND_CAMP_ACT=1),-3)
       GROUP BY cbz.cod_peri, cbz.cod_regi,cbz.cod_zona, cbz.cod_secc
       ORDER BY 1,2,3,4 ASC;

    TYPE interfazrec IS RECORD(
      codPeriodo           VARCHAR2(6),
      codRegion            VARCHAR2(3),
      codZona              VARCHAR2(6),
      codSeccion           VARCHAR2(7),
      importeFactNeto      VARCHAR2(22),
      cob21                VARCHAR(22),
      cob31                VARCHAR(22),
      cob36                VARCHAR(22)
    );

    TYPE interfazrectab IS TABLE OF interfazrec;

    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas            NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea             VARCHAR2(1500);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile    BOOLEAN;

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
          lslinea := interfazrecord(x).codPeriodo        || ';' ||
                     interfazrecord(x).codRegion         || ';' ||
                     interfazrecord(x).codZona           || ';' ||
                     interfazrecord(x).codSeccion        || ';' ||
                     interfazrecord(x).importeFactNeto   || ';' ||
                     interfazrecord(x).cob21             || ';' ||
                     interfazrecord(x).cob31             || ';' ||
                     interfazrecord(x).cob36;

          utl_file.put_line(v_handle,lslinea);
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
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_COB_ENVIO_RECOB_FFVV: ' || ls_sqlerrm);

 END INT_PR_COB_ENVIO_RECOB_FFVV;

 PROCEDURE INT_PR_COB_GENER_INFOR_DATAC(
  p_cod_usua                       IN   VARCHAR2 DEFAULT USER)
 IS
  -- Cabecera
  lv_cod_subp                      CHAR(6):='390039';
  lv_tip_cuen                      CHAR(2):='39';
  lv_fec_cort                      DATE;
  lv_amp_mile                      CHAR(1):='M';
  lv_ind_mile                      CHAR(1):='0';
  lv_tip_entr                      CHAR(1):='T';
  lv_fec_inic_repo                 CHAR(8):='00000000';
  lv_fec_fina_repo                 CHAR(8):='00000000';
  lv_ind_part                      CHAR(1):='N';
  lv_val_fill                      CHAR(746):=LPAD(' ',746,' ');


  -- Detalle
  lv_tipo_iden                     CHAR(1):='1';
  lv_val_situ_titu                 CHAR(1):='0';
  lv_cali_deud                     CHAR(2):='00';
  lv_tip_obli                      CHAR(1):='1';
  lv_sub_hipo                      CHAR(1):='0';
  lv_fec_subd                      CHAR(8):='00000000';
  lv_ter_cont                      CHAR(1):='2';
  lv_per_pago                      CHAR(1):='9';
  lv_est_plas                      CHAR(1):='0';
  lv_fec_esta_plas                 CHAR(8):='00000000';
  lv_val_adje                      CHAR(1):='0';
  lv_cla_tarj                      CHAR(1):='0';
  lv_des_fran                      CHAR(1):='0';
  lv_nom_marc                      char(30):=RPAD(' ',30,' ');
  lv_tip_mone                      CHAR(1):='1';
  lv_tip_gara                      CHAR(1):='1';
  lv_val_cali                      CHAR(2):='  ';
  lv_pro_incu                      CHAR(3):='000';


 CURSOR c_interfaz
 IS
 SELECT
   lv_tipo_iden                                val_tipo_iden,
   TRIM(TO_CHAR(mci.num_docu_iden,'00000000000')) num_docu_iden,
   RPAD(TO_CHAR(mcc.oid_movi_cc),18,'0')       num_iden_cuot,
   RPAD(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' || mc.val_nom2,45,' ') nom_clie,
   lv_val_situ_titu                            val_situ_titu,
   TO_CHAR(mcc.fec_docu,'YYYYMMDD')            val_fech_aper,
   TO_CHAR(mcc.fec_venc,'YYYYMMDD')            val_fech_venc,
   lv_cali_deud                                val_cali_deud,
   lv_tip_obli                                 val_tipo_obli,
   lv_sub_hipo                                 val_subs_hipo,
   lv_fec_subd                                 val_fech_subs,
   lv_ter_cont                                 val_term_cont,
   CASE
     WHEN mcc.imp_pend = 0 THEN
       '1'
     ELSE
        '0'
   END                                         val_form_pago,
   lv_per_pago                                 val_peri_pago,
   det.cod_nove                                val_desc_nove,
   '0'                                         val_esta_cuen_orig,
   TO_CHAR(mcc.fec_docu,'YYYYMMDD') val_fec_esta_orig,
   det.cod_esta_cuen val_esta_cuen,
   TO_CHAR(lv_fec_cort,'YYYYMMDD')          val_fech_esta_cuen,
   lv_est_plas                              val_esta_plas,
   lv_fec_esta_plas                         val_fech_esta_plas,
   lv_val_adje                              val_adje,
   TO_CHAR(mcc.fec_docu,'YYYYMMDD')         val_fech_adje,
   lv_cla_tarj                              val_clas_tarj,
   lv_des_fran                              val_desc_fran,
   lv_nom_marc                              val_nomb_marc,
   lv_tip_mone                              val_tipo_mone,
   lv_tip_gara                              val_tipo_gar,
   lv_val_cali                              val_cali,
   lv_pro_incu                              val_prob_incu,
   LPAD(det.val_dias_atra,3,'0')            val_dias_mora,
   SUBSTR(LPAD(mcc.imp_movi,11,'0'),1,11) val_inic,
   SUBSTR(LPAD(det.imp_pend,11,'0'),1,11) val_sald,
   SUBSTR(LPAD('0',11,'0'),1,11) val_disp,
   SUBSTR(LPAD(mcc.imp_movi,11,'0'),1,11) val_cuot_mens,
   SUBSTR(LPAD(det.imp_pend,11,'0'),1,11) val_sald_mora,
   '001',
   CASE
    WHEN mcc.imp_pend = 0 THEN '001'
   ELSE '000'
   END val_cuot_canc,
   CASE
    WHEN mcc.imp_pend = 0 THEN '000'
   ELSE '001'
   end val_cuot_mora,
   '000' val_clau_perm,
   '00000000' val_fech_perm,
   TO_CHAR(mcc.fec_venc,'YYYYMMDD') val_fech_limi_pago,
   CASE
    WHEN mcc.imp_pend = 0 THEN TO_CHAR(TRUNC(mcc.fec_ulti_movi),'YYYYMMDD')
   ELSE '00000000'
   END val_fech_pago,
   RPAD('BEL STAR',30,' ') val_ofic_radi,
   RPAD('TOCANCIPA',20,' ') val_ciud_radi,
   '25817000'               val_codi_dane_ciud_radi,
   SUBSTR(RPAD(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,'DES_PROV'),20,' '),1,20) val_ciud_resi,
   RPAD(TO_CHAR(TO_NUMBER(SUBSTR(mcd.cod_unid_geog,1,6))) || TO_CHAR(TO_NUMBER(SUBSTR(mcd.cod_unid_geog,7,6))),8,'0') val_cod_dane_ciud_resi,
   SUBSTR(RPAD(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,'DES_DPTO'),20,' '),1,20) val_depa_resi,
   SUBSTR(RPAD(TRIM(FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie)),60,' '),1,60) val_dire_resi,
   RPAD(SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mc.oid_clie,'TF'),1,12),12,' ') val_tele_resi,
   LPAD(' ',20,' ') val_ciud_labo,
   LPAD('0',8,'0') val_codi_dabe_ciud_labo,
   LPAD(' ',20,' ') val_depa_labo,
   LPAD(' ',60,' ') val_dire_labo,
   LPAD(' ',12,' ') val_tele_labo,
   SUBSTR(RPAD(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,'DES_PROV'),20,' '),1,20) val_ciud_corr,
   RPAD(TO_CHAR(TO_NUMBER(SUBSTR(mcd.cod_unid_geog,1,6))) || TO_CHAR(TO_NUMBER(SUBSTR(mcd.cod_unid_geog,7,6))),8,'0') val_codi_dane_ciud_corr,
   SUBSTR(RPAD(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,'DES_DPTO'),20,' '),1,20) val_depa_corr,
   SUBSTR(RPAD(TRIM(FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie)),60,' '),1,60) val_dire_corr,
   LPAD(' ',60,' ') val_emai,
   RPAD(SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mc.oid_clie,'TM'),1,12),12,' ') val_tele_movi,
   '390039' val_susc_dest,
   LPAD(' ',64,' ') val_fille
  FROM
   ccc_movim_cuent_corri mcc,
   mae_clien mc,
   mae_clien_ident mci,
   mae_clien_direc mcd,
   cob_inter_datac_detal det
  WHERE mcc.imp_movi > 0
    AND mcc.clie_oid_clie = mc.oid_clie
    AND mc.oid_clie = mci.clie_oid_clie
    AND mc.oid_clie = mcd.clie_oid_clie
    AND mcd.ind_dire_ppal = 1
    AND mci.val_iden_docu_prin = 1
    AND mcd.ind_elim = 0
    AND mcc.oid_movi_cc = det.oid_movi_cc
    AND mci.num_docu_iden NOT LIKE '%X'
    AND mci.num_docu_iden NOT LIKE 'X%';

   TYPE interfazrec IS RECORD(
    val_tipo_iden            VARCHAR2(1),
    num_docu_iden            VARCHAR2(11),
    num_iden_cuot            VARCHAR2(18),
    val_nomb_comp            VARCHAR2(45),
    val_situa_titu           VARCHAR2(1),
    val_fech_aper            VARCHAR2(8),
    val_fech_venc            VARCHAR2(8),
    val_cali_deud            VARCHAR2(2),
    val_tipo_obli            VARCHAR2(1),
    val_subs_hipo            VARCHAR2(1),
    val_fech_subs            VARCHAR2(8),
    val_term_cont            VARCHAR2(1),
    val_form_pago            VARCHAR2(1),
    val_peri_pago            VARCHAR2(1),
    val_desc_nove            VARCHAR2(2),
    val_esta_cuen_orig       VARCHAR2(1),
    val_fech_esta_orig       VARCHAR2(8),
    val_esta_cuen            VARCHAR2(2),
    val_fech_esta_cuen       VARCHAR2(8),
    val_esta_plas            VARCHAR2(1),
    val_fech_esta_plas       VARCHAR2(8),
    val_adje                 VARCHAR2(1),
    val_fech_adje            VARCHAR2(8),
    val_clas_tarj            VARCHAR2(1),
    val_desc_fran            VARCHAR2(1),
    val_nomb_marc            VARCHAR2(30),
    val_tipo_mone            VARCHAR2(1),
    val_tipo_gara            VARCHAR2(1),
    val_desc_cali            VARCHAR2(2),
    val_prob_incu            VARCHAR2(3),
    val_edad_mora            VARCHAR2(3),
    val_valo_inic            VARCHAR2(11),
    val_sald_deud            VARCHAR2(11),
    val_line_disp            VARCHAR2(11),
    val_cuot_mens            VARCHAR2(11),
    val_sald_mora            VARCHAR2(11),
    val_tota_cuot            VARCHAR2(3),
    val_cuot_canc            VARCHAR2(3),
    val_cuot_mora            VARCHAR2(3),
    val_clau_perm            VARCHAR2(3),
    val_fech_perm            VARCHAR2(8),
    val_fech_limi_pago       VARCHAR2(8),
    val_fech_pago            VARCHAR2(8),
    val_ofic_radi            VARCHAR2(30),
    val_ciud_radi            VARCHAR2(20),
    val_codi_dane_ciud_radi  VARCHAR2(8),
    val_ciud_resi            VARCHAR2(20),
    val_codi_dane_ciud_resi  VARCHAR2(8),
    val_depa_resi            VARCHAR2(20),
    val_dire_resi            VARCHAR2(60),
    val_tele_resi            VARCHAR2(12),
    val_ciud_labo            VARCHAR2(20),
    val_codi_dane_ciud_labo  VARCHAR2(8),
    val_depa_labo            VARCHAR2(20),
    val_dire_labo            VARCHAR2(60),
    val_tele_labo            VARCHAR2(12),
    val_ciud_corr            VARCHAR2(20),
    val_codi_dane_ciud_corr  VARCHAR2(8),
    val_depa_corr            VARCHAR2(20),
    val_dire_corr            VARCHAR2(60),
    val_mail                 VARCHAR2(60),
    val_celu                 VARCHAR2(12),
    val_susc_dest            VARCHAR2(6),
    val_fill                 VARCHAR2(37));

  TYPE interfazrectab IS TABLE OF interfazrec;

  interfazrecord                   interfazrectab;

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote_ante                 fin_inter_ejecu.num_lote%TYPE;
  lv_deud_posi_impo_desd           NUMBER(15,2);
  lv_deud_posi_impo_hast           NUMBER(15,2);
  lv_cant_regi                     NUMBER(15,2);
  lv_suma_nove                     NUMBER(15,2);
  lv_deud_posi_fec_docu_desd       DATE;
  lv_deud_posi_dias_atra           NUMBER(15,2);
  lv_deud_nega_impo_desd           NUMBER(15,2);
  lv_deud_nega_impo_hast           NUMBER(15,2);
  lv_deud_nega_fec_docu_desd       DATE;
  lv_deud_nega_dias_atra           NUMBER(15,2);

  lv_deud_aldi_impo_desd           NUMBER(15,2);
  lv_deud_aldi_impo_hast           NUMBER(15,2);
  lv_deud_aldi_dias_desd           NUMBER(15,2);
  lv_deud_aldi_dias_hast           NUMBER(15,2);

  lv_deud_mini_repo            NUMBER(15,2);

  lv_ulti_oid_movi_cc              NUMBER(12);
  lv_oid_movi_maxi                 NUMBER(12);
  w_filas                          NUMBER := 1000;
  lv_handle                        utl_file.file_type;
  lv_line_cabe                     VARCHAR2(1801);
  lv_line_pie                      VARCHAR2(1801);
  lv_line_deta                     VARCHAR2(4000);
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch                      VARCHAR2(50);
  lv_cod_inte_data                 CHAR(4):='DACR';
  lv_cant_tota                     CHAR(8);
  lv_desc_erro                     VARCHAR2(4000);

  lv_reg_cob_inter_datac_cabec     cob_inter_datac_cabec%ROWTYPE;

  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_datc;
  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion Datacredito';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);
  lv_reg_cob_inter_datac_cabec.num_lote := lv_num_lote;

  gv_des_log := 'Obteniendo el numero de lote : ' || lv_num_lote;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_nom_arch := 'DATACREDITO_' || lv_num_lote || '.TXT';

  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_data,p_cod_usua,lv_num_lote,lv_dir_ensa,lv_nom_arch,lv_handle);

  lv_deud_mini_repo := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoImporteDeudaMinima'));

  lv_deud_posi_impo_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaPositivaImporteDesde'));
  lv_deud_posi_impo_hast := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaPositivaImporteHasta'));
  lv_deud_posi_fec_docu_desd := TO_DATE(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaPositivaFechaDocumentoDesde'),'DD/MM/YYYY');
  lv_deud_posi_dias_atra := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaPositivaDiasAtraso'));

  lv_deud_nega_impo_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaNegativaImporteDesde'));
  lv_deud_nega_impo_hast := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaNegativaImporteHasta'));
  lv_deud_nega_fec_docu_desd := TO_DATE(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaNegativaFechaDocumentoDesde'),'DD/MM/YYYY');
  lv_deud_nega_dias_atra := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaNegativaDiasAtraso'));

  lv_deud_aldi_impo_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaAlDiaImporteDesde'));
  lv_deud_aldi_impo_hast := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaAlDiaImporteHasta'));
  lv_deud_aldi_dias_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaAlDiaDiasAtrasoDesde'));
  lv_deud_aldi_dias_hast := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DatacreditoDeudaAlDiaDiasAtrasoHasta'));

  SELECT LAST_DAY(TRUNC(ADD_MONTHS(SYSDATE,-1)))
  INTO lv_fec_cort
  FROM dual;

  gv_des_log := 'Fecha de Corte : ' || lv_fec_cort;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_reg_cob_inter_datac_cabec.fec_cort := lv_fec_cort;
  lv_reg_cob_inter_datac_cabec.fec_gene := SYSDATE;
  lv_reg_cob_inter_datac_cabec.ind_ulti_envi := 1;

  BEGIN

   SELECT c.num_lote,c.ulti_oid_movi
   INTO lv_num_lote_ante,lv_ulti_oid_movi_cc
   FROM cob_inter_datac_cabec c
   WHERE c.ind_ulti_envi = 1;

   gv_des_log := 'Lote previo cargado : ' || lv_num_lote_ante;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   UPDATE cob_inter_datac_cabec cab
   SET cab.ind_ulti_envi = 0
   WHERE cab.num_lote = lv_num_lote_ante;

   DELETE FROM cob_inter_datac_detal;

   -- Cargando las Deudas al Dia del Ultimo Envio --
   INSERT INTO cob_inter_datac_detal
    SELECT
     lv_num_lote,
     d.oid_movi_cc,
     d.oid_clie,
     'I',
     '99',
     '99',
     mcc.imp_pend,
     lv_fec_cort - mcc.fec_docu
    FROM
     cob_inter_datac_detal_histo d,
     ccc_movim_cuent_corri mcc
    WHERE d.oid_movi_cc = mcc.oid_movi_cc
     AND d.num_lote = lv_num_lote_ante
     AND d.ind_deud IN ('D','I')
     AND NOT EXISTS (
      SELECT NULL
      FROM  cob_consu_excep_datac dc
      WHERE dc.oid_clie = mcc.clie_oid_clie);

   gv_des_log := 'Se cargaron  ' || SQL%ROWCOUNT || ' Deudas Al Dia reportadas en el ultimo envio' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   -- Cargando las Deudas Negativas del Ultimo Envio --
   INSERT INTO cob_inter_datac_detal
    SELECT
     lv_num_lote,
     d.oid_movi_cc,
     d.oid_clie,
     'R',
     '99',
     '99',
     mcc.imp_pend,
     lv_fec_cort - mcc.fec_docu
    FROM
     cob_inter_datac_detal_histo d,
     ccc_movim_cuent_corri mcc
    WHERE d.oid_movi_cc = mcc.oid_movi_cc
     AND d.num_lote = lv_num_lote_ante
     AND d.ind_deud IN  ('N','R')
     AND NOT EXISTS (
      SELECT NULL
      FROM  cob_consu_excep_datac dc
      WHERE dc.oid_clie = mcc.clie_oid_clie);

   gv_des_log := 'Se cargaron  ' || SQL%ROWCOUNT || ' Deudas negativas reportadas en el ultimo envio' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  EXCEPTION

   WHEN no_data_found THEN
    gv_des_log := 'NO hay cargas previas';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    lv_ulti_oid_movi_cc := 0;

  END;

  gv_des_log := 'Oid Movi CCC Anterior: ' || lv_ulti_oid_movi_cc;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT MAX(mcc.oid_movi_cc)
  INTO lv_oid_movi_maxi
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.fec_docu <= lv_fec_cort
    AND mcc.fec_docu >= lv_deud_posi_fec_docu_desd
    AND mcc.oid_movi_cc > lv_ulti_oid_movi_cc;

  gv_des_log := 'Oid Movi CCC Maximo: ' || lv_oid_movi_maxi;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '-------------------------';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Cargando Deuda Al Dia';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Deuda Al Dia Dias Atraso Desde ' || lv_deud_aldi_dias_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Deuda Al Dia Atraso Hasta ' || lv_deud_aldi_dias_hast;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Importe Deuda Al Dia Desde ' || lv_deud_aldi_impo_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Importe Deuda Al Dia Hasta ' || lv_deud_aldi_impo_hast;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Insertando Al Dia Nuevas --
  INSERT INTO cob_inter_datac_detal
   SELECT
    lv_num_lote,
    mcc.oid_movi_cc,
    mc.oid_clie,
    'D' ind_deud,
    '01' cod_nove,
    '01' cod_esta_cuen,
    mcc.imp_pend,
    lv_fec_cort - mcc.fec_docu
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_pend >=  lv_deud_aldi_impo_desd
     AND mcc.imp_pend <=  lv_deud_aldi_impo_hast
     AND lv_fec_cort - mcc.fec_docu >= lv_deud_aldi_dias_desd
     AND lv_fec_cort - mcc.fec_docu <= lv_deud_aldi_dias_hast
     AND mcc.fec_docu >= lv_deud_posi_fec_docu_desd
     AND mcc.fec_docu <= lv_fec_cort
     AND mcc.oid_movi_cc <= lv_oid_movi_maxi
     AND NOT EXISTS (
       SELECT NULL
       FROM ccc_consu_casti_cabec c
       WHERE c.num_docu_iden = mci.num_docu_iden)
    AND NOT EXISTS (
      SELECT NULL
      FROM cob_inter_datac_detal_histo d
      WHERE d.oid_movi_cc = mcc.oid_movi_cc)
      AND NOT EXISTS (
      SELECT NULL
      FROM  cob_consu_excep_datac dc
      WHERE dc.oid_clie = mcc.clie_oid_clie);

  gv_des_log := 'Se cargaron  ' || SQL%ROWCOUNT || ' Deudas Al Dia Nuevas' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '-------------------------';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Cargando Deuda Positivas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Deuda Positiva Dias Atras ' || lv_deud_posi_dias_atra;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Fecha Documento Desde ' ||  lv_deud_posi_fec_docu_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Importe Deuda Positiva Desde ' || lv_deud_posi_impo_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Importe Deuda Positiva Hasta ' || lv_deud_posi_impo_hast;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Deudas Positivas Nuevas Canceladas
  INSERT INTO cob_inter_datac_detal
   SELECT
    lv_num_lote,
    mcc.oid_movi_cc,
    mc.oid_clie,
    'P' ind_deud,
    '05' cod_nove,
    '03' cod_esta_cuen,
    mcc.imp_pend,
    lv_fec_cort - mcc.fec_docu
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_movi > lv_deud_posi_impo_desd
     AND mcc.imp_movi < lv_deud_posi_impo_hast
     AND mcc.imp_pend = 0
     AND mcc.fec_ulti_movi - mcc.fec_docu < lv_deud_posi_dias_atra
     AND mcc.fec_docu >=  lv_deud_posi_fec_docu_desd
     AND mcc.fec_docu <= lv_fec_cort
     AND mcc.oid_movi_cc > lv_ulti_oid_movi_cc
     AND mcc.oid_movi_cc <= lv_oid_movi_maxi
     AND NOT EXISTS (
         SELECT NULL
         FROM ccc_consu_casti_cabec c
         WHERE c.num_docu_iden = mci.num_docu_iden)
      AND NOT EXISTS (
      SELECT NULL
      FROM  cob_consu_excep_datac dc
      WHERE dc.oid_clie = mcc.clie_oid_clie);

  gv_des_log := '   Se cargaron  ' || SQL%ROWCOUNT || ' Deudas positivas canceladas nuevas' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  /*
  INSERT INTO cob_inter_datac_detal
   SELECT
    lv_num_lote,
    'P' ind_deud,
    '01' cod_nove,
    '03' cod_esta_cuen,
    mc.oid_clie,
    mcc.oid_movi_cc,
    mcc.imp_pend,
    lv_fec_cort - mcc.fec_docu
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_movi > lv_deud_posi_impo_desd
     AND mcc.imp_movi < lv_deud_posi_impo_hast
     AND mcc.imp_pend > 0
     AND lv_fec_cort - mcc.fec_docu < lv_deud_posi_dias_atra
     AND mcc.fec_docu >=  lv_deud_posi_fec_docu_desd
     AND mcc.fec_docu <= lv_fec_cort
     AND mcc.oid_movi_cc > lv_ulti_oid_movi_cc
     AND mcc.oid_movi_cc <= lv_oid_movi_maxi
     AND NOT EXISTS (
         SELECT NULL
         FROM ccc_consu_casti_cabec c
         WHERE c.num_docu_iden = mci.num_docu_iden);
  */


  gv_des_log := '-------------------------';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Cargando Deuda Negativas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Deuda Negativa Dias Atras ' || lv_deud_nega_dias_atra;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Fecha Documento Desde ' || lv_deud_nega_fec_docu_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := '   Importe Deuda Negativa Desde ' || lv_deud_nega_impo_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_reg_cob_inter_datac_cabec.ulti_oid_movi := lv_oid_movi_maxi;

  -- Deudas Negativas Nuevas --
  INSERT INTO cob_inter_datac_detal
   SELECT
    lv_num_lote,
    mcc.oid_movi_cc,
    mc.oid_clie,
    'N' ind_deud,
    '99' cod_nove,
    '99' cod_esta_cuen,
    mcc.imp_pend,
    lv_fec_cort - mcc.fec_docu
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_pend > lv_deud_nega_impo_desd
     AND lv_fec_cort - mcc.fec_docu >= lv_deud_nega_dias_atra
     AND mcc.fec_docu >= lv_deud_nega_fec_docu_desd
     AND mcc.fec_docu <= lv_fec_cort
--     AND mcc.oid_movi_cc > lv_ulti_oid_movi_cc
     AND mcc.oid_movi_cc <= lv_oid_movi_maxi
     AND NOT EXISTS (
       SELECT NULL
       FROM ccc_consu_casti_cabec c
       WHERE c.num_docu_iden = mci.num_docu_iden)
    AND NOT EXISTS (
      SELECT NULL
      FROM cob_inter_datac_detal_histo d
      WHERE d.oid_movi_cc = mcc.oid_movi_cc)
      AND NOT EXISTS (
      SELECT NULL
      FROM  cob_consu_excep_datac dc
      WHERE dc.oid_clie = mcc.clie_oid_clie);

  gv_des_log := 'Se cargaron  ' || SQL%ROWCOUNT || ' Deudas Negativas Nuevas' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  ----------------------------------
  -- Calculando Codigos de Novedad
  ----------------------------------
  gv_des_log := 'Calculando los Codigos de Novedad' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Deudas Canceladas
  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '05',
      det.cod_esta_cuen = '03',
      det.imp_pend = 0
  WHERE det.imp_pend <= NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I');

  -- Deudas Al Dia
  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '01',
      det.cod_esta_cuen = '01'
  WHERE det.imp_pend > NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I')
    AND det.val_dias_atra >= 30
    AND det.val_dias_atra <= 62;

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '07',
      det.cod_esta_cuen = '02'
  WHERE det.imp_pend > NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I')
    AND det.val_dias_atra >= 63
    AND det.val_dias_atra <= 89;

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '08',
      det.cod_esta_cuen = '02'
  WHERE det.imp_pend > NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I')
    AND det.val_dias_atra >= 90
    AND det.val_dias_atra <= 119;

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '09',
      det.cod_esta_cuen = '02'
  WHERE det.imp_pend > NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I')
    AND det.val_dias_atra >= 120
    AND det.val_dias_atra <= 189;

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '12',
      det.cod_esta_cuen = '05'
  WHERE det.imp_pend > NVL(lv_deud_mini_repo,0)
    AND det.ind_deud IN ('N','R','I')
    AND det.val_dias_atra >= 190;

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '13',
      det.cod_esta_cuen = '06',
      det.imp_pend = NVL((
                     SELECT SUM(d.imp_deud_actu)
                     FROM ccc_consu_casti_detal d
                     WHERE d.oid_movi_cc = det.oid_movi_cc),0)
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_consu_casti_detal d
   WHERE d.oid_movi_cc = det.oid_movi_cc);

  UPDATE cob_inter_datac_detal det
  SET det.cod_nove = '14',
      det.cod_esta_cuen = '03'
  WHERE det.cod_nove = '13'
    AND det.imp_pend = 0;

  UPDATE cob_inter_datac_detal det
  SET det.val_dias_atra = 999
  WHERE det.val_dias_atra > 999;

  SELECT COUNT(*), SUM(TO_NUMBER(det.cod_nove))
  INTO lv_cant_regi, lv_suma_nove
  FROM cob_inter_datac_detal det
  WHERE det.num_lote = lv_num_lote;

  lv_reg_cob_inter_datac_cabec.cant_caso := lv_cant_regi;
  lv_reg_cob_inter_datac_cabec.sum_nove := lv_suma_nove;

  lv_line_cabe := LPAD('H',18,'H') ||
                  lv_cod_subp ||
                  lv_tip_cuen ||
                  TO_CHAR(lv_fec_cort,'YYYYMMDD') ||
                  lv_amp_mile ||
                  lv_ind_mile ||
                  lv_tip_entr ||
                  lv_fec_inic_repo ||
                  lv_fec_fina_repo ||
                  lv_ind_part ||
                  lv_val_fill;

  gv_des_log := 'Generando el archivo';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  utl_file.put_line(lv_handle,lv_line_cabe);

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.count > 0 THEN
     FOR x IN interfazrecord.first .. interfazrecord.last LOOP
      lv_line_deta := interfazrecord(x).val_tipo_iden ||
                      interfazrecord(x).num_docu_iden ||
                      interfazrecord(x).num_iden_cuot ||
                      interfazrecord(x).val_nomb_comp ||
                      interfazrecord(x).val_situa_titu ||
                      interfazrecord(x).val_fech_aper ||
                      interfazrecord(x).val_fech_venc ||
                      interfazrecord(x).val_cali_deud ||
                      interfazrecord(x).val_tipo_obli ||
                      interfazrecord(x).val_subs_hipo ||
                      interfazrecord(x).val_fech_subs ||
                      interfazrecord(x).val_term_cont ||
                      interfazrecord(x).val_form_pago ||
                      interfazrecord(x).val_peri_pago ||
                      interfazrecord(x).val_desc_nove ||
                      interfazrecord(x).val_esta_cuen_orig ||
                      interfazrecord(x).val_fech_esta_orig ||
                      interfazrecord(x).val_esta_cuen ||
                      interfazrecord(x).val_fech_esta_cuen ||
                      interfazrecord(x).val_esta_plas ||
                      interfazrecord(x).val_fech_esta_plas ||
                      interfazrecord(x).val_adje ||
                      interfazrecord(x).val_fech_adje ||
                      interfazrecord(x).val_clas_tarj ||
                      interfazrecord(x).val_desc_fran ||
                      interfazrecord(x).val_nomb_marc ||
                      interfazrecord(x).val_tipo_mone ||
                      interfazrecord(x).val_tipo_gara ||
                      interfazrecord(x).val_desc_cali ||
                      interfazrecord(x).val_prob_incu ||
                      interfazrecord(x).val_edad_mora ||
                      interfazrecord(x).val_valo_inic ||
                      interfazrecord(x).val_sald_deud ||
                      interfazrecord(x).val_line_disp ||
                      interfazrecord(x).val_cuot_mens ||
                      interfazrecord(x).val_sald_mora ||
                      interfazrecord(x).val_tota_cuot ||
                      interfazrecord(x).val_cuot_canc ||
                      interfazrecord(x).val_cuot_mora ||
                      interfazrecord(x).val_clau_perm ||
                      interfazrecord(x).val_fech_perm ||
                      interfazrecord(x).val_fech_limi_pago ||
                      interfazrecord(x).val_fech_pago ||
                      interfazrecord(x).val_ofic_radi ||
                      interfazrecord(x).val_ciud_radi ||
                      interfazrecord(x).val_codi_dane_ciud_radi ||
                      interfazrecord(x).val_ciud_resi ||
                      interfazrecord(x).val_codi_dane_ciud_resi ||
                      interfazrecord(x).val_depa_resi ||
                      interfazrecord(x).val_dire_resi ||
                      interfazrecord(x).val_tele_resi ||
                      interfazrecord(x).val_ciud_labo ||
                      interfazrecord(x).val_codi_dane_ciud_labo ||
                      interfazrecord(x).val_depa_labo ||
                      interfazrecord(x).val_dire_labo ||
                      interfazrecord(x).val_tele_labo ||
                      interfazrecord(x).val_ciud_corr ||
                      interfazrecord(x).val_codi_dane_ciud_corr ||
                      interfazrecord(x).val_depa_corr ||
                      interfazrecord(x).val_dire_corr ||
                      interfazrecord(x).val_mail ||
                      interfazrecord(x).val_celu ||
                      interfazrecord(x).val_susc_dest ||
                      interfazrecord(x).val_fill;

          utl_file.put_line(lv_handle,lv_line_deta);

     END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  lv_line_pie := LPAD('Z',18,'Z') ||
                 TO_CHAR(SYSDATE,'YYYYMMDD') ||
                 SUBSTR(RPAD(lv_cant_regi,8,'0'),1,8) ||
                 SUBSTR(RPAD(lv_suma_nove,8,'0'),1,8) ||
                 RPAD(' ',758,' ');

  utl_file.put_line(lv_handle,lv_line_pie);

  utl_file.fclose(lv_handle);

  gv_des_log := 'Guardando informacion en el Historico' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO cob_inter_datac_cabec VALUES lv_reg_cob_inter_datac_cabec;

  INSERT INTO cob_inter_datac_detal_histo
   SELECT *
   FROM cob_inter_datac_detal d
   WHERE d.num_lote = lv_num_lote;

  DELETE FROM cob_inter_datac_detal d
  WHERE d.num_lote = lv_num_lote;

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu_cob,lv_log_cod_proc,lv_id_proc_ejec,2);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_data,lv_num_lote,lv_cant_tota,'2',lv_desc_erro);

 END INT_PR_COB_GENER_INFOR_DATAC;

 PROCEDURE INT_PR_COB_GENER_INFOR_ACOVE(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_num_lote                      cob_envio_histo_acove.num_lote%TYPE;
  lv_num_lote_clie                 cob_envio_histo_acove.num_lote%TYPE;
  lv_num_lote_obli                 cob_envio_histo_acove.num_lote%TYPE;
  lv_handle_clie                   utl_file.file_type;
  lv_line_deta_clie                VARCHAR2(4000);
  lv_handle_obli                   utl_file.file_type;
  lv_line_deta_obli                VARCHAR2(4000);
  w_filas                          NUMBER := 1000;
  lv_fec_docu_desd                 DATE;
  lv_dias_atra_desd                NUMBER(3);
  lv_impo_deud_nega_desd           NUMBER(12,2);
  lv_impo_deud_posi_hast           NUMBER(12,2);
  lv_oid_movi_cc_inic              NUMBER(12);
  lv_oid_movi_cc_fina              NUMBER(12);
  lv_cod_inte_clie                 VARCHAR2(5):='ACO-1';
  lv_cod_inte_obli                 VARCHAR2(5):='ACO-2';
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_clie                 VARCHAR2(50);
  lv_nom_arch_obli                 VARCHAR2(50);
  lv_cant_tota_clie                NUMBER(12):=0;
  lv_cant_tota_obli                NUMBER(12):=0;
  lv_desc_erro_clie                VARCHAR2(4000);
  lv_desc_erro_obli                VARCHAR2(4000);
  lv_dow                           CHAR(1);

  CURSOR c_clie
  IS
   SELECT
    'CC'                           val_tipo_iden,
    TO_NUMBER(mci.num_docu_iden)   val_nume_iden,
    mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 val_razo_soci,
    'NA'                           val_tipo_cont,
    '0'                            val_nume_cont,
    'A'                            val_esta_vend,
    SUBSTR(mcd.cod_unid_geog,5,2) || SUBSTR(mcd.cod_unid_geog,10,3) val_codi_dane_muni,
    1                                               val_cupo_asig,
    1                                               val_estr,
    mc.cod_sexo                                     val_sexo,
    ROUND((TRUNC(SYSDATE) - mcda.fec_naci)/365,0)   val_edad
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    mae_clien_direc mcd,
    mae_clien_datos_adici mcda
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcd.clie_oid_clie
     AND mcd.ind_dire_ppal = 1
     AND mci.val_iden_docu_prin = 1
     AND mcd.ind_elim = 0
     AND mc.oid_clie = mcda.clie_oid_clie
     AND EXISTS (
          SELECT NULL
          FROM cob_envio_actua_acove a
          WHERE a.oid_clie = mc.oid_clie
            AND a.num_lote = lv_num_lote);

  TYPE t_rec_clie IS RECORD(
   val_tipo_iden                   VARCHAR2(2),
   val_nume_iden                   VARCHAR2(12),
   val_razo_soci                   VARCHAR2(400),
   val_tipo_cont                   VARCHAR2(2),
   val_nume_cont                   VARCHAR2(1),
   val_esta_vend                   VARCHAR2(1),
   val_codi_dane_muni              VARCHAR2(5),
   val_cupo_asig                   VARCHAR2(1),
   val_estr                        VARCHAR2(1),
   val_sexo                        VARCHAR2(1),
   val_edad                        VARCHAR2(3));

  TYPE tab_rec_clie                IS TABLE OF t_rec_clie;

  lv_tab_rec_clie                  tab_rec_clie;

  CURSOR c_obli
  IS
   SELECT
    'CC'                                   val_tipo_iden,
    TO_NUMBER(mci.num_docu_iden)           val_nume_iden,
    'DP'                                   val_cond_titu,
    mcc.oid_movi_cc                        val_nume_obli,
    TO_CHAR(mcc.fec_docu,'DDMMYYYY')       fec_inic_obli,
    mcc.imp_movi                           val_obli,
    CASE
     WHEN mcc.imp_pend > 0 THEN 'M'
     ELSE 'D'
    END                                    val_esta_obli,
    mcc.imp_pend                           val_sald_mora,
    TO_CHAR(TRUNC(SYSDATE) - mcc.fec_venc) val_dias_mora,
    '0'                                    val_deud_cast,
    CASE
     WHEN mcc.imp_pend > 0 THEN 'NP'
     ELSE 'PV'
    END                                    val_form_pago,
    TO_CHAR(SYSDATE,'DDMMYYYY')            fec_repo,
    spc.cod_peri                           val_peri,
    'N'                                    val_nove,
    'NA'                                   val_obse
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc,
    cra_perio cp,
    seg_perio_corpo spc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.perd_oid_peri = cp.peri_oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND EXISTS (
          SELECT NULL
          FROM cob_envio_actua_acove a
          WHERE a.oid_movi_cc = mcc.oid_movi_cc
            AND a.num_lote = lv_num_lote);

   TYPE t_rec_obli IS RECORD(
    val_tipo_iden                  VARCHAR2(2),
    val_nume_iden                  VARCHAR2(12),
    val_cond_titu                  VARCHAR2(2),
    val_nume_obli                  VARCHAR2(20),
    fec_inic_obli                  VARCHAR2(8),
    val_obli                       VARCHAR2(20),
    val_esta_obli                  VARCHAR2(1),
    val_sald_mora                  VARCHAR2(20),
    val_dias_mora                  VARCHAR2(4),
    val_deud_cast                  VARCHAR2(1),
    val_form_pago                  VARCHAR2(2),
    fec_repo                       VARCHAR2(8),
    val_peri                       VARCHAR2(6),
    val_nove                       VARCHAR2(1),
    val_obse                       VARCHAR2(2));

  TYPE tab_rec_obli                IS TABLE OF t_rec_obli;

  lv_tab_rec_obli                  tab_rec_obli;

  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  lv_log_user     := p_cod_usua;
  lv_log_cod_proc := gc_cod_proc_gene_info_acov;
  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log := 'Inicio Generacion Informacion Acovedi';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Validacionde Generacion
  lv_dow := TO_CHAR(SYSDATE,'d');

  IF lv_dow = 1 OR lv_dow = 7 THEN
   RAISE e_vali_ejec_dow;
  END IF;

  lv_fec_docu_desd := ADD_MONTHS(TRUNC(SYSDATE),-12);

  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote);

  SELECT fc.dir_ensa
  INTO lv_dir_ensa
  FROM fin_param_inter_cabec fc
  WHERE fc.cod_modu = gc_cod_modu_cob
    AND fc.cod_inte = lv_cod_inte_clie;

  SELECT fin.oid_ulti_regi_proc
  INTO lv_oid_movi_cc_inic
  FROM fin_contr_regis_progr fin
  WHERE fin.cod_modu = gc_cod_modu_cob
    AND fin.cod_prog = gc_cod_proc_gene_info_acov;

  SELECT MAX(mcc.oid_movi_cc)
  INTO lv_oid_movi_cc_fina
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.oid_movi_cc > lv_oid_movi_cc_inic;

  gv_des_log := 'Oid Movi CC Inicial ' || lv_oid_movi_cc_inic;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Oid Movi CC Final ' || lv_oid_movi_cc_fina;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_dias_atra_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('AcovediDiasAtrasoDesde'));
  lv_impo_deud_nega_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('AcovediImporteDeudaNegativaDesde'));
  lv_impo_deud_posi_hast := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('AcovediImporteDeudaPositivaHasta'));

  gv_des_log := 'Dias Atraso Desde : ' || lv_dias_atra_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Importe Deuda Negativa : ' || lv_impo_deud_nega_desd;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log := 'Importe Deuda Positiva : ' || lv_impo_deud_posi_hast;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  DELETE FROM cob_envio_actua_acove;

  -- Cargando Cancelaciones --
  INSERT INTO cob_envio_actua_acove
   SELECT
    lv_num_lote,
    TRUNC(SYSDATE) fec_envio,
    'P' ind_deud,
    mc.oid_clie,
    mcc.oid_movi_cc,
    'C'
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_pend = 0
     AND EXISTS (
      SELECT NULL
      FROM cob_envio_histo_acove t
      WHERE t.oid_movi_cc = mcc.oid_movi_cc
        AND t.ind_deud_actu = 'N');

  gv_des_log := 'Se cargaron ' || SQL%ROWCOUNT || ' cancelaciones';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE cob_envio_histo_acove c
  SET c.ind_deud_actu = 'C'
  WHERE c.ind_deud_actu = 'N'
   AND c.fec_envi < TRUNC(SYSDATE)
   AND c.num_lote <> lv_num_lote
   AND (c.oid_clie,c.oid_movi_cc) IN (
   SELECT h.oid_clie, h.oid_movi_cc
   FROM cob_envio_actua_acove h
   WHERE h.num_lote = lv_num_lote);

  gv_des_log := 'Actualizando las cancelaciones en el historico ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  --Cargando Deudas Negativas --
  INSERT INTO cob_envio_actua_acove
   SELECT
    lv_num_lote,
    TRUNC(SYSDATE) fec_envio,
    'N' ind_deud,
    mc.oid_clie,
    mcc.oid_movi_cc,
    'N'
   FROM
    mae_clien mc,
    mae_clien_ident mci,
    ccc_movim_cuent_corri mcc
   WHERE mc.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin = 1
     AND mc.oid_clie = mcc.clie_oid_clie
     AND mcc.imp_pend > lv_impo_deud_nega_desd
     AND mcc.fec_docu >= lv_fec_docu_desd
     AND TRUNC(SYSDATE) - mcc.fec_docu > lv_dias_atra_desd
     AND mcc.oid_movi_cc <= lv_oid_movi_cc_fina
     AND NOT REGEXP_LIKE(REPLACE((mc.val_nom1 || mc.val_nom2 || mc.val_ape1 || mc.val_ape2),' ',''), '[^A-Z]')
     AND NOT REGEXP_LIKE(REPLACE((mc.val_nom1 || mc.val_nom2 || mc.val_ape1 || mc.val_ape2),' ',''), '[Ñ]')
     AND NOT EXISTS (
      SELECT NULL
      FROM ccc_consu_casti_cabec c
      WHERE c.num_docu_iden = mci.num_docu_iden)
     AND NOT EXISTS (
      SELECT NULL
      FROM cob_envio_histo_acove t
      WHERE t.oid_movi_cc = mcc.oid_movi_cc);

   gv_des_log := 'Se cargaron ' || SQL%ROWCOUNT || ' Deudas Negativas';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   -- Cargando Deudas Positivas --
   INSERT INTO cob_envio_actua_acove
    SELECT
     lv_num_lote,
     TRUNC(SYSDATE) fec_envio,
     'P' ind_deud,
     mc.oid_clie,
     mcc.oid_movi_cc,
     'P'
    FROM
     mae_clien mc,
     mae_clien_ident mci,
     ccc_movim_cuent_corri mcc
    WHERE mc.oid_clie = mci.clie_oid_clie
      AND mci.val_iden_docu_prin = 1
      AND mc.oid_clie = mcc.clie_oid_clie
      AND mcc.imp_movi <= lv_impo_deud_posi_hast
      AND mcc.imp_movi > 0
      AND mcc.imp_pend = 0
      AND mcc.oid_movi_cc <= lv_oid_movi_cc_fina
      AND mcc.fec_docu >= lv_fec_docu_desd
      AND mcc.fec_ulti_movi - mcc.fec_docu > lv_dias_atra_desd
      AND NOT REGEXP_LIKE(REPLACE((mc.val_nom1 || mc.val_nom2 || mc.val_ape1 || mc.val_ape2),' ',''), '[^A-Z]')
      AND NOT REGEXP_LIKE(REPLACE((mc.val_nom1 || mc.val_nom2 || mc.val_ape1 || mc.val_ape2),' ',''), '[Ñ]')
      AND NOT EXISTS (
       SELECT NULL
       FROM ccc_consu_casti_cabec c
       WHERE c.num_docu_iden = mci.num_docu_iden)
      AND NOT EXISTS (
       SELECT NULL
       FROM cob_envio_histo_acove t
       WHERE t.oid_movi_cc = mcc.oid_movi_cc);

   gv_des_log := 'Se cargaron ' || SQL%ROWCOUNT || ' Deudas Positivas';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote_clie);
   lv_nom_arch_clie := 'CLIENTES_' || lv_num_lote_clie || '.TXT';

   gv_des_log := 'Generando el archivo ' || lv_nom_arch_clie;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_clie,p_cod_usua,lv_num_lote_clie,lv_dir_ensa,lv_nom_arch_clie,lv_handle_clie);

   OPEN c_clie;
   LOOP
   FETCH c_clie BULK COLLECT INTO lv_tab_rec_clie LIMIT w_filas;
    IF lv_tab_rec_clie.count > 0 THEN
     FOR x IN lv_tab_rec_clie.first .. lv_tab_rec_clie.last LOOP
      lv_line_deta_clie := lv_tab_rec_clie(x).val_tipo_iden || ';' ||
                      lv_tab_rec_clie(x).val_nume_iden  || ';' ||
                      lv_tab_rec_clie(x).val_razo_soci || ';' ||
                      lv_tab_rec_clie(x).val_tipo_cont || ';' ||
                      lv_tab_rec_clie(x).val_nume_cont || ';' ||
                      lv_tab_rec_clie(x).val_esta_vend || ';' ||
                      lv_tab_rec_clie(x).val_codi_dane_muni || ';' ||
                      lv_tab_rec_clie(x).val_cupo_asig || ';' ||
                      lv_tab_rec_clie(x).val_estr || ';' ||
                      lv_tab_rec_clie(x).val_sexo || ';' ||
                      lv_tab_rec_clie(x).val_edad;

       utl_file.put_line(lv_handle_clie,lv_line_deta_clie);

       lv_cant_tota_clie := lv_cant_tota_clie + 1;

     END LOOP;
    END IF;
    EXIT WHEN c_clie%NOTFOUND;
   END LOOP;
   CLOSE c_clie;

  utl_file.fclose(lv_handle_clie);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_clie,lv_num_lote_clie,lv_cant_tota_clie,'2',lv_desc_erro_clie);

  ----
  FIN_PKG_INTER.FIN_PR_OBTIE_NUMER_LOTE(lv_num_lote_obli);
  lv_nom_arch_obli := 'OBLIGACIONES_' || lv_num_lote_obli || '.TXT';

  gv_des_log := 'Generando el archivo ' || lv_nom_arch_obli;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_INTER.FIN_PR_REGIS_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_obli,p_cod_usua,lv_num_lote_obli,lv_dir_ensa,lv_nom_arch_obli,lv_handle_obli);

  OPEN c_obli;
   LOOP
   FETCH c_obli BULK COLLECT INTO lv_tab_rec_obli LIMIT w_filas;
    IF lv_tab_rec_obli.count > 0 THEN
     FOR x IN lv_tab_rec_obli.first .. lv_tab_rec_obli.last LOOP
      lv_line_deta_obli := lv_tab_rec_obli(x).val_tipo_iden  || ';' ||
                           lv_tab_rec_obli(x).val_nume_iden || ';' ||
                           lv_tab_rec_obli(x).val_cond_titu || ';' ||
                           lv_tab_rec_obli(x).val_nume_obli || ';' ||
                           lv_tab_rec_obli(x).fec_inic_obli || ';' ||
                           lv_tab_rec_obli(x).val_obli || ';' ||
                           lv_tab_rec_obli(x).val_esta_obli || ';' ||
                           lv_tab_rec_obli(x).val_sald_mora || ';' ||
                           lv_tab_rec_obli(x).val_dias_mora || ';' ||
                           lv_tab_rec_obli(x).val_deud_cast || ';' ||
                           lv_tab_rec_obli(x).val_form_pago || ';' ||
                           lv_tab_rec_obli(x).fec_repo || ';' ||
                           lv_tab_rec_obli(x).val_peri || ';' ||
                           lv_tab_rec_obli(x).val_nove || ';' ||
                           lv_tab_rec_obli(x).val_obse;

      utl_file.put_line(lv_handle_obli,lv_line_deta_obli);

      lv_cant_tota_obli := lv_cant_tota_obli + 1;

     END LOOP;
    END IF;
    EXIT WHEN c_obli%NOTFOUND;
   END LOOP;
   CLOSE c_obli;

  utl_file.fclose(lv_handle_obli);

  FIN_PKG_INTER.FIN_PR_FINAL_INTER_SALID_EJECU(gc_cod_modu_cob,lv_cod_inte_obli,lv_num_lote_obli,lv_cant_tota_obli,'2',lv_desc_erro_obli);

  gv_des_log := 'Actualizando el contador';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE fin_contr_regis_progr fin
    SET fin.oid_ulti_regi_proc = lv_oid_movi_cc_fina
  WHERE fin.cod_modu = gc_cod_modu_cob
    AND fin.cod_prog = gc_cod_proc_gene_info_acov;

  gv_des_log := 'Cargando la informacion al hist¿rico';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  INSERT INTO cob_envio_histo_acove
   SELECT * FROM  cob_envio_actua_acove;

  gv_des_log := 'El proceso finalizo correctamente';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu_cob,lv_log_cod_proc,lv_id_proc_ejec,2);

 EXCEPTION

  WHEN e_vali_ejec_dow THEN
   gv_des_log := 'No se generan archivos por validacion DOW';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := 'El proceso finalizo correctamente';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu_cob,lv_log_cod_proc,lv_id_proc_ejec,2);

  WHEN OTHERS THEN

   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);

   lv_des_erro := ' *** Error ' || SQLERRM  || ' *** encontrado en la linea ' ||
                   gv_reco_trac.line_number ||
                   ' en el programa ' ||
                   gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;

   gv_des_log := '!!! ERROR : ' || lv_des_erro;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log := 'El proceso finalizo con errores';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu_cob,lv_log_cod_proc,lv_id_proc_ejec,9);

 END INT_PR_COB_GENER_INFOR_ACOVE;

  PROCEDURE INT_PR_COB_ENVIO_TRANS_UNION
  IS
  BEGIN

   COB_PKG_REPOR_ESTAD.COB_PR_ACTUA_ESTAD_RECUP_COBRA;
   COB_PKG_PROCE.COB_PR_ENVIO_CARTE_CORPO;

  END INT_PR_COB_ENVIO_TRANS_UNION ;
  /***************************************************************************
    Descripcion       : Procedimiento que genera la interfaz ENVIAR INFORMACION
                        TRANSUNION
    Fecha Creacion    : 16/10/2015
    Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_cob_gener_infor_trans(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;

    CURSOR c_interfaz IS
      SELECT val_tipo_enti,
             val_codi_clie,
             val_codi_comp,
             val_codi_sucu,
             val_rela_clie,
             val_nomb_comp,
             val_cedu_viej,
             val_cedu_nuev,
             val_nume_pasa,
             val_razo_soci,
             val_sigl,
             val_rnc,
             val_luga_resi,
             val_ofic_empr,
             val_celu,
             val_beep,
             val_fax,
             val_otro,
             val_dire_call_1,
             val_dire_esqu_1,
             val_dire_nume_1,
             val_dire_edif_resi_1,
             val_dire_piso_1,
             val_dire_apar_1,
             val_dire_urba_1,
             val_dire_sect_1,
             val_dire_ciud_1,
             val_dire_prov_1,
             val_dire_call_2,
             val_dire_esqu_2,
             val_dire_nume_2,
             val_dire_edif_resi_2,
             val_dire_piso_2,
             val_dire_apar_2,
             val_dire_urba_2,
             val_dire_sect_2,
             val_dire_ciud_2,
             val_dire_prov_2,
             val_nume_cuen,
             val_nume_fact,
             val_unid_mone,
             val_tipo_cuen,
             val_fech_aper,
             val_fech_venc,
             val_limi_cred,
             val_cred_alto,
             val_mont_cuot,
             val_cant_cuot,
             fec_ulti_cort,
             fec_ulti_pago,
             val_mont_ulti_pago,
             val_bala_actu,
             val_mont_atras,
             val_cant_cuot_atras,
             val_esta_cuen,
             val_indi_cuen,
             val_sald_30d,
             val_sald_60d,
             val_sald_90d,
             val_sald_120d,
             val_sald_150d,
             val_sald_180d,
             val_sald_999d
        FROM cob_inter_trans_union;

    TYPE interfazrec IS RECORD(
      v_val_tipo_enti        cob_inter_trans_union.val_tipo_enti%type,
      v_val_codi_clie        cob_inter_trans_union.val_codi_clie%type,
      v_val_codi_comp        cob_inter_trans_union.val_codi_comp%type,
      v_val_codi_sucu        cob_inter_trans_union.val_codi_sucu%type,
      v_val_rela_clie        cob_inter_trans_union.val_rela_clie%type,
      v_val_nomb_comp        cob_inter_trans_union.val_nomb_comp%type,
      v_val_cedu_viej        cob_inter_trans_union.val_cedu_viej%type,
      v_val_cedu_nuev        cob_inter_trans_union.val_cedu_nuev%type,
      v_val_nume_pasa        cob_inter_trans_union.val_nume_pasa%type,
      v_val_razo_soci        cob_inter_trans_union.val_razo_soci%type,
      v_val_sigl             cob_inter_trans_union.val_sigl%type,
      v_val_rnc              cob_inter_trans_union.val_rnc%type,
      v_val_luga_resi        cob_inter_trans_union.val_luga_resi%type,
      v_val_ofic_empr        cob_inter_trans_union.val_ofic_empr%type,
      v_val_celu             cob_inter_trans_union.val_celu%type,
      v_val_beep             cob_inter_trans_union.val_beep%type,
      v_val_fax              cob_inter_trans_union.val_fax%type,
      v_val_otro             cob_inter_trans_union.val_otro%type,
      v_val_dire_call_1      cob_inter_trans_union.val_dire_call_1%type,
      v_val_dire_esqu_1      cob_inter_trans_union.val_dire_esqu_1%type,
      v_val_dire_nume_1      cob_inter_trans_union.val_dire_nume_1%type,
      v_val_dire_edif_resi_1 cob_inter_trans_union.val_dire_edif_resi_1%type,
      v_val_dire_piso_1      cob_inter_trans_union.val_dire_piso_1%type,
      v_val_dire_apar_1      cob_inter_trans_union.val_dire_apar_1%type,
      v_val_dire_urba_1      cob_inter_trans_union.val_dire_urba_1%type,
      v_val_dire_sect_1      cob_inter_trans_union.val_dire_sect_1%type,
      v_val_dire_ciud_1      cob_inter_trans_union.val_dire_ciud_1%type,
      v_val_dire_prov_1      cob_inter_trans_union.val_dire_prov_1%type,
      v_val_dire_call_2      cob_inter_trans_union.val_dire_call_2%type,
      v_val_dire_esqu_2      cob_inter_trans_union.val_dire_esqu_2%type,
      v_val_dire_nume_2      cob_inter_trans_union.val_dire_nume_2%type,
      v_val_dire_edif_resi_2 cob_inter_trans_union.val_dire_edif_resi_2%type,
      v_val_dire_piso_2      cob_inter_trans_union.val_dire_piso_2%type,
      v_val_dire_apar_2      cob_inter_trans_union.val_dire_apar_2%type,
      v_val_dire_urba_2      cob_inter_trans_union.val_dire_urba_2%type,
      v_val_dire_sect_2      cob_inter_trans_union.val_dire_sect_2%type,
      v_val_dire_ciud_2      cob_inter_trans_union.val_dire_ciud_2%type,
      v_val_dire_prov_2      cob_inter_trans_union.val_dire_prov_2%type,
      v_val_nume_cuen        cob_inter_trans_union.val_nume_cuen%type,
      v_val_nume_fact        cob_inter_trans_union.val_nume_fact%type,
      v_val_unid_mone        cob_inter_trans_union.val_unid_mone%type,
      v_val_tipo_cuen        cob_inter_trans_union.val_tipo_cuen%type,
      v_val_fech_aper        cob_inter_trans_union.val_fech_aper%type,
      v_val_fech_venc        cob_inter_trans_union.val_fech_venc%type,
      v_val_limi_cred        cob_inter_trans_union.val_limi_cred%type,
      v_val_cred_alto        cob_inter_trans_union.val_cred_alto%type,
      v_val_mont_cuot        cob_inter_trans_union.val_mont_cuot%type,
      v_val_cant_cuot        cob_inter_trans_union.val_cant_cuot%type,
      v_fec_ulti_cort        cob_inter_trans_union.fec_ulti_cort%type,
      v_fec_ulti_pago        cob_inter_trans_union.fec_ulti_pago%type,
      v_val_mont_ulti_pago   cob_inter_trans_union.val_mont_ulti_pago%type,
      v_val_bala_actu        cob_inter_trans_union.val_bala_actu%type,
      v_val_mont_atras       cob_inter_trans_union.val_mont_atras%type,
      v_val_cant_cuot_atras  cob_inter_trans_union.val_cant_cuot_atras%type,
      v_val_esta_cuen        cob_inter_trans_union.val_esta_cuen%type,
      v_val_indi_cuen        cob_inter_trans_union.val_indi_cuen%type,
      v_val_sald_30d         cob_inter_trans_union.val_sald_30d%type,
      v_val_sald_60d         cob_inter_trans_union.val_sald_60d%type,
      v_val_sald_90d         cob_inter_trans_union.val_sald_90d%type,
      v_val_sald_120d        cob_inter_trans_union.val_sald_120d%type,
      v_val_sald_150d        cob_inter_trans_union.val_sald_150d%type,
      v_val_sald_180d        cob_inter_trans_union.val_sald_180d%type,
      v_val_sald_999d        cob_inter_trans_union.val_sald_999d%type);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    lnIdPais seg_pais.oid_pais%type;
  BEGIN

    lbabrirutlfile := TRUE;
    lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

          lslinea := interfazrecord(x)
                     .v_val_tipo_enti || ';' || interfazrecord(x)
                     .v_val_codi_clie || ';' || interfazrecord(x)
                     .v_val_codi_comp || ';' || interfazrecord(x)
                     .v_val_codi_sucu || ';' || interfazrecord(x)
                     .v_val_rela_clie || ';' || interfazrecord(x)
                     .v_val_nomb_comp || ';' || interfazrecord(x)
                     .v_val_cedu_viej || ';' || interfazrecord(x)
                     .v_val_cedu_nuev || ';' || interfazrecord(x)
                     .v_val_nume_pasa || ';' || interfazrecord(x)
                     .v_val_razo_soci || ';' || interfazrecord(x).v_val_sigl || ';' || interfazrecord(x)
                     .v_val_rnc || ';' || interfazrecord(x).v_val_luga_resi || ';' || interfazrecord(x)
                     .v_val_ofic_empr || ';' || interfazrecord(x).v_val_celu || ';' || interfazrecord(x)
                     .v_val_beep || ';' || interfazrecord(x).v_val_fax || ';' || interfazrecord(x)
                     .v_val_otro || ';' || interfazrecord(x)
                     .v_val_dire_call_1 || ';' || interfazrecord(x)
                     .v_val_dire_esqu_1 || ';' || interfazrecord(x)
                     .v_val_dire_nume_1 || ';' || interfazrecord(x)
                     .v_val_dire_edif_resi_1 || ';' || interfazrecord(x)
                     .v_val_dire_piso_1 || ';' || interfazrecord(x)
                     .v_val_dire_apar_1 || ';' || interfazrecord(x)
                     .v_val_dire_urba_1 || ';' || interfazrecord(x)
                     .v_val_dire_sect_1 || ';' || interfazrecord(x)
                     .v_val_dire_ciud_1 || ';' || interfazrecord(x)
                     .v_val_dire_prov_1 || ';' || interfazrecord(x)
                     .v_val_dire_call_2 || ';' || interfazrecord(x)
                     .v_val_dire_esqu_2 || ';' || interfazrecord(x)
                     .v_val_dire_nume_2 || ';' || interfazrecord(x)
                     .v_val_dire_edif_resi_2 || ';' || interfazrecord(x)
                     .v_val_dire_piso_2 || ';' || interfazrecord(x)
                     .v_val_dire_apar_2 || ';' || interfazrecord(x)
                     .v_val_dire_urba_2 || ';' || interfazrecord(x)
                     .v_val_dire_sect_2 || ';' || interfazrecord(x)
                     .v_val_dire_ciud_2 || ';' || interfazrecord(x)
                     .v_val_dire_prov_2 || ';' || interfazrecord(x)
                     .v_val_nume_cuen || ';' || interfazrecord(x)
                     .v_val_nume_fact || ';' || interfazrecord(x)
                     .v_val_unid_mone || ';' || interfazrecord(x)
                     .v_val_tipo_cuen || ';' || interfazrecord(x)
                     .v_val_fech_aper || ';' || interfazrecord(x)
                     .v_val_fech_venc || ';' || interfazrecord(x)
                     .v_val_limi_cred || ';' || interfazrecord(x)
                     .v_val_cred_alto || ';' || interfazrecord(x)
                     .v_val_mont_cuot || ';' || interfazrecord(x)
                     .v_val_cant_cuot || ';' || interfazrecord(x)
                     .v_fec_ulti_cort || ';' || interfazrecord(x)
                     .v_fec_ulti_pago || ';' || interfazrecord(x)
                     .v_val_mont_ulti_pago || ';' || interfazrecord(x)
                     .v_val_bala_actu || ';' || interfazrecord(x)
                     .v_val_mont_atras || ';' || interfazrecord(x)
                     .v_val_cant_cuot_atras || ';' || interfazrecord(x)
                     .v_val_esta_cuen || ';' || interfazrecord(x)
                     .v_val_indi_cuen || ';' || interfazrecord(x)
                     .v_val_sald_30d || ';' || interfazrecord(x)
                     .v_val_sald_60d || ';' || interfazrecord(x)
                     .v_val_sald_90d || ';' || interfazrecord(x)
                     .v_val_sald_120d || ';' || interfazrecord(x)
                     .v_val_sald_150d || ';' || interfazrecord(x)
                     .v_val_sald_180d || ';' || interfazrecord(x)
                     .v_val_sald_999d;

          utl_file.put_line(v_handle, lslinea);
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
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR int_pr_cob_gener_infor_trans: ' ||
                              ls_sqlerrm);
  END int_pr_cob_gener_infor_trans;

  /***************************************************************************
   Descripcion       : Genera Interfaz SAP -Enviar Cobranza por Etapas
  Fecha Creacion    : 19/10/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE INT_PR_COB_ENVIA_ETAPA_COSAP(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2
                                        ) IS

    lsSociedad      VARCHAR2(4);
    lsCampaniaDesde VARCHAR2(6);
    lsCampaniaHasta VARCHAR2(6);

    CURSOR c_interfaz(psSociedad      VARCHAR2,
                      psCampaniaDesde VARCHAR2,
                      psCampaniaHasta VARCHAR2) IS

      SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') fec_proc,
             TO_CHAR(SYSDATE, 'HHMISS') hor_proc,
             SUBSTR(cbz.cod_pais, 1, 2) cod_pais,
             psSociedad, --$P{Sociedad}
             cbz.cod_peri,
             cbz.cod_zona,
             cbz.cod_secc,
             CASE
               WHEN cbz.ind_cicl_nuev < 5 THEN
                cbz.ind_cicl_nuev
               ELSE
                5
             END ind_cicl_nuev,
             
             SUM(cbz.imp_fact) imp_fact,
             SUM(cbz.imp_sald_pend_sac) imp_sald_pend_sac,
             SUM(cbz.cob_dias_21) cob_dias_21,
             SUM(cbz.cob_dias_31) cob_dias_31,
             SUM(cbz.cob_dias_42) cob_dias_42,
             SUM(cbz.cob_dias_63) cob_dias_63,
             SUM(cbz.cob_dias_84) cob_dias_84,
             SUM(cbz.cob_dias_126) cob_dias_126,
             SUM(cbz.cob_dias_189) cob_dias_189,
             SUM(cbz.cob_dias_999) cob_dias_999,
             SUM(cbz.imp_carg_dire) imp_carg_dire,
             SUM(cbz.imp_abon_conx) imp_abon_conx,
             SUM(cbz.imp_abon_nmon) imp_abon_nmon,
             SUM(cbz.imp_sald_pend) imp_sald_pend,
             SUM(cbz.imp_carg_frac) imp_carg_frac,
             SUM(cbz.imp_abon_frac) imp_abon_frac,
             SUM(cbz.imp_fact_nenv) imp_fact_nenv,
             SUM(cbz.imp_devo) imp_devo
        FROM cob_repor_estad_recup_cobra cbz
       WHERE cbz.cod_peri >= psCampaniaDesde --$P{Campaña Desde}
         AND cbz.cod_peri <= psCampaniaHasta --$P{Campaña Hasta}
       GROUP BY SUBSTR(cbz.cod_pais, 1, 2),
                cbz.cod_soci,
                cbz.cod_peri,
                cbz.cod_zona,
                cbz.cod_secc,
                cbz.ind_cicl_nuev;

    TYPE interfazrec IS RECORD(
      fechaProceso   VARCHAR2(8),
      horaProceso    VARCHAR2(6),
      paisSap        VARCHAR2(2),
      sociedad       VARCHAR2(4),
      campania       VARCHAR2(6),
      zonaCod        VARCHAR2(4),
      seccionCod     VARCHAR2(2),
      cicloNueva     VARCHAR2(1),
      mFactura       NUMBER(18,6),
      saldoPendi     NUMBER(18,6),
      cobro21        NUMBER(18,6),
      cobro31        NUMBER(18,6),
      cobro42        NUMBER(18,6),
      cobro63        NUMBER(18,6),
      cobro84        NUMBER(18,6),
      cobro126       NUMBER(18,6),
      cobro189       NUMBER(18,6),
      cobro999       NUMBER(18,6),
      cargoDirecto   NUMBER(18,6),
      xTotalAbono    NUMBER(18,6),
      nTotalAbono    NUMBER(18,6),
      saldoCancela   NUMBER(18,6),
      totalCargo     NUMBER(18,6),
      totalAbono     NUMBER(18,6),
      totalFractu    NUMBER(18,6),
      totalTransa    NUMBER(18,6)

      );

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

    --Codigo de Sociedad
    lsSociedad := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('COD_SOCI_SAFI');
    IF (lsSociedad IS NULL) THEN
      lsSociedad := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
    END IF;

    --Campaña Desde para la interface
    lsCampaniaDesde:= TO_CHAR(SYSDATE, 'YYYY')-1 || '10';

    --Campaña Hasta para la interface
    lsCampaniaHasta := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lsSociedad, lsCampaniaDesde, lsCampaniaHasta);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
      IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                               psCodigosistema,
                                               psCodigoInterfaz,
                                               psNombreArchivo,
                                               lsDirTempo,
                                               lsNombreArchivo,
                                               V_HANDLE);
        lbAbrirUtlFile := FALSE;
      END IF;
            
      IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).fechaProceso || ';' ||
                    interfazRecord(x).horaProceso || ';' ||
                    interfazRecord(x).paisSap || ';' ||
                    interfazRecord(x).sociedad || ';' ||
                    interfazRecord(x).campania || ';' ||
                    interfazRecord(x).zonaCod || ';' ||
                    interfazRecord(x).seccionCod || ';' ||
                    interfazRecord(x).cicloNueva || ';' ||                           
                    TO_CHAR(interfazRecord(x).mFactura, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).saldoPendi, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro21, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro31, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro42, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro63, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro84, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro126, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro189, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cobro999, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).cargoDirecto, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).xTotalAbono, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).nTotalAbono, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).saldoCancela, '99999999999.999999')|| ';' ||
                    TO_CHAR(interfazRecord(x).totalCargo, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).totalAbono, '99999999999.999999') || ';' ||
                    TO_CHAR(interfazRecord(x).totalFractu, '99999999999.999999')|| ';' ||
                    TO_CHAR(interfazRecord(x).totalTransa, '99999999999.999999');

          UTL_FILE.PUT_LINE(V_HANDLE, lslinea);
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
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,'ERROR INT_PR_COB_ENVIA_ETAPA_COSAP: ' || ls_sqlerrm);
  END INT_PR_COB_ENVIA_ETAPA_COSAP;

   /***************************************************************************
   Descripcion       : Genera Interfaz - Fecha de Cierre de Facturacion Campaña
  Fecha Creacion    : 19/10/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE INT_PR_COB_CIERR_FACTU_CAMPA(pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2
                                        ) IS

    lsSociedad      VARCHAR2(4);
    lsCampania      VARCHAR2(6);


    CURSOR c_interfaz(psSociedad      VARCHAR2,
                      psCampania       VARCHAR2) IS

      SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') fec_proc,
             TO_CHAR(SYSDATE, 'HHMISS') hor_proc,
             SUBSTR(sp.cod_pais, 1, 2) cod_pais,
             psSociedad, --$P{Sociedad}
             psCampania, --$P{Campania}
             TO_CHAR(cp.fec_fina, 'YYYYMMDD')fec_fina
        FROM seg_perio_corpo spc, cra_perio cp, seg_pais sp, seg_socie so
       WHERE spc.oid_peri = cp.peri_oid_peri
         AND cp.pais_oid_pais = sp.oid_pais
         AND spc.cod_peri = psCampania;

    TYPE interfazrec IS RECORD(
      fechaProceso   VARCHAR2(8),
      horaProceso    VARCHAR2(6),
      paisSap        VARCHAR2(2),
      sociedad       VARCHAR2(4),
      campania       VARCHAR2(6),
      fechaCieFac    VARCHAR2(8)
      );

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

    --Codigo de Sociedad
    lsSociedad := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('COD_SOCI_SAFI');
    IF (lsSociedad IS NULL) THEN
      lsSociedad := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
    END IF;

    --Campaña para la interface
    lsCampania := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lsSociedad, lsCampania);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
      IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                               psCodigosistema,
                                               psCodigoInterfaz,
                                               psNombreArchivo,
                                               lsDirTempo,
                                               lsNombreArchivo,
                                               V_HANDLE);
        lbAbrirUtlFile := FALSE;
      END IF;
              
      IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).fechaProceso || ';' ||
                    interfazRecord(x).horaProceso || ';' ||
                    interfazRecord(x).paisSap || ';' ||
                    interfazRecord(x).sociedad || ';' ||
                    interfazRecord(x).campania || ';' ||
                    interfazRecord(x).fechaCieFac;

          UTL_FILE.PUT_LINE(V_HANDLE, lslinea);
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
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,'ERROR INT_PR_COB_CIERR_FACTU_CAMPA: ' || ls_sqlerrm);
  END INT_PR_COB_CIERR_FACTU_CAMPA;

END INT_PKG_COB;
/
