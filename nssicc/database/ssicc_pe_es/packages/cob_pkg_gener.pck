CREATE OR REPLACE PACKAGE COB_PKG_GENER is

 FUNCTION COB_FN_OBTIE_PARAM_GENER(
  p_cod_para                   IN       ccc_param_gener.cod_para%TYPE)
 RETURN VARCHAR2;

 FUNCTION COB_FN_OBTIE_NUMER_LOTE
 RETURN NUMBER;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene el nombre del Cobrador de Cobranza.
   Fecha Creacion    : 15/07/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION COB_FN_OBTIE_NOMBR_COBRA(
  p_cod_pais seg_pais.cod_pais%TYPE,
  p_cod_soci seg_socie.cod_soci%TYPE,
  p_cod_usu cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
 RETURN VARCHAR2;

 FUNCTION COB_FN_OBTIE_NOMBR_COBRA(
   p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2;

 FUNCTION COB_FN_OBTIE_ETAPA_COBRA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2;

   /*****************************************************************************
     Descripcion       : Funcion que obtiene la descripcion de la Etapa de Cobranza
     Fecha Creacion    : 15/07/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
  FUNCTION COB_FN_OBTIE_DESCR_ETAPA_DEUDA(
     p_cod_pais seg_pais.cod_pais%TYPE,
     p_cod_soci seg_socie.cod_soci%TYPE,
     p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE)
  RETURN VARCHAR2;

   /*****************************************************************************
     Descripcion       : Funcion que calcula el periodo siguiente
     Fecha Creacion    : 15/07/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION COB_FN_CALCU_PERIO_NSGTE(
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_nro_periodos number )
   RETURN seg_perio_corpo.cod_peri%TYPE;

   /*****************************************************************************
     Descripcion       : Funcion que obtiene la lista de email a actualizar con el proceso
                                 de Actualizacion de Cartera
     Fecha Creacion    : 15/07/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
 FUNCTION COB_FN_OBTIE_EMAIL_ACTUA_CARTE(
  p_cod_pais                     seg_pais.cod_pais%TYPE,
  p_cod_soci                     seg_socie.cod_soci%TYPE)
 RETURN VARCHAR2;

 FUNCTION COB_FN_OBTIE_EMAIL_GEREN_APOYO(
  p_cod_regi                      IN   zon_regio.cod_regi%TYPE)
 RETURN VARCHAR2; 
     
END COB_PKG_GENER;
/
CREATE OR REPLACE PACKAGE BODY COB_PKG_GENER is

 FUNCTION COB_FN_OBTIE_PARAM_GENER(
  p_cod_para                       IN   ccc_param_gener.cod_para%TYPE)
 RETURN VARCHAR2
 IS

  lv_val_para                      cob_param_gener.val_para%TYPE;

 BEGIN

  SELECT g.val_para
  INTO lv_val_para
  FROM cob_param_gener g
  WHERE g.cod_para =  p_cod_para;

  RETURN lv_val_para;

 EXCEPTION
  WHEN NO_DATA_FOUND THEN
   RETURN NULL;

 END COB_FN_OBTIE_PARAM_GENER;

 FUNCTION COB_FN_OBTIE_NUMER_LOTE
 RETURN NUMBER
 IS

  lv_cont                          ccc_numer_lote.cont%TYPE;
  lv_val_cade_fech                 ccc_numer_lote.val_cade_fech%TYPE;
  lv_num_secu                      VARCHAR2(5);
  lv_num_lote                      ccc_movim_banca.num_lote%TYPE;

 BEGIN

  lv_val_cade_fech:=to_char(trunc(SYSDATE),'YYYYMMDD');

  SELECT cnl.cont
  INTO lv_cont
  FROM cob_numer_lote cnl
  WHERE cnl.val_cade_fech=lv_val_cade_fech
  FOR UPDATE;

  UPDATE cob_numer_lote cnl
  SET cnl.cont=lv_cont+1
  WHERE cnl.val_cade_fech=lv_val_cade_fech;

  lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

  lv_num_lote := TO_NUMBER(concat(lv_val_cade_fech,lv_num_secu));

  RETURN lv_num_lote;

 EXCEPTION

  WHEN no_data_found THEN

   lv_cont:=001;
   lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

   INSERT INTO cob_numer_lote VALUES (to_char(trunc(SYSDATE),'YYYYMMDD'),lv_cont+1);

   lv_num_lote := concat(lv_val_cade_fech,lv_num_secu);

   RETURN lv_num_lote;

 END COB_FN_OBTIE_NUMER_LOTE;

 FUNCTION COB_FN_OBTIE_NOMBR_COBRA(
  p_cod_pais                     IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                     IN   seg_socie.cod_soci%TYPE,
  p_cod_usu                      IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
 RETURN VARCHAR2
 IS

  v_nomb_cob cob_usuar_cobra_pais.val_nomb_usua_cobr%TYPE;

 BEGIN

  SELECT c.val_nomb_usua_cobr
  INTO v_nomb_cob
  FROM cob_usuar_cobra_pais c
  WHERE c.cod_pais=p_cod_pais
    AND c.cod_soci = p_cod_soci
    AND c.cod_usua_cobr=p_cod_usu;

  RETURN v_nomb_cob;

 END COB_FN_OBTIE_NOMBR_COBRA;

 FUNCTION COB_FN_OBTIE_NOMBR_COBRA(
   p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS

  lv_nomb_cob                       cob_usuar_cobra_pais.val_nomb_usua_cobr%TYPE;
  lv_num_secu_etap                 cob_etapa_deuda_pais.num_secu_etap%TYPE;

 BEGIN

  SELECT MAX(e.num_secu_etap)
  INTO lv_num_secu_etap
  FROM
   cob_detal_asign_carte d,
   cob_etapa_deuda_pais e
  WHERE d.cod_etap_deud = e.cod_etap_deud
    AND d.oid_clie = p_oid_clie
    AND d.fec_cier >= TRUNC(SYSDATE);

  SELECT MAX(u.val_nomb_usua_cobr)
  INTO lv_nomb_cob
  FROM
   cob_etapa_deuda_pais e,
   cob_detal_asign_carte d,
   cob_usuar_cobra_pais u
  WHERE d.cod_etap_deud = e.cod_etap_deud
    AND d.cod_usua_cobr = u.cod_usua_cobr
    AND d.oid_clie = p_oid_clie
    AND d.fec_cier >= TRUNC(SYSDATE)
    AND e.num_secu_etap = lv_num_secu_etap;

  RETURN lv_nomb_cob;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN '';

 END COB_FN_OBTIE_NOMBR_COBRA;

 FUNCTION COB_FN_OBTIE_ETAPA_COBRA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS

  lv_desc_etap_deud                cob_etapa_deuda_pais.val_desc%TYPE;
  lv_num_secu_etap                 cob_etapa_deuda_pais.num_secu_etap%TYPE;

 BEGIN

  SELECT MAX(e.num_secu_etap)
  INTO lv_num_secu_etap
  FROM
   cob_detal_asign_carte d,
   cob_etapa_deuda_pais e
  WHERE d.cod_etap_deud = e.cod_etap_deud
    AND d.oid_clie = p_oid_clie
    AND d.fec_cier >= TRUNC(SYSDATE);

  SELECT e.val_desc
  INTO lv_desc_etap_deud
  FROM cob_etapa_deuda_pais e
  WHERE e.num_secu_etap = lv_num_secu_etap;

  RETURN lv_desc_etap_deud;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN '';

 END COB_FN_OBTIE_ETAPA_COBRA;

 FUNCTION COB_FN_OBTIE_DESCR_ETAPA_DEUDA(
  p_cod_pais seg_pais.cod_pais%TYPE,
  p_cod_soci seg_socie.cod_soci%TYPE,
  p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE)
 RETURN VARCHAR2
 IS

  v_desc_etap_deud cob_etapa_deuda_pais.val_desc%TYPE;

 BEGIN

  SELECT e.val_desc
  INTO v_desc_etap_deud
  FROM cob_etapa_deuda_pais e
  WHERE e.cod_pais=p_cod_pais
    AND e.cod_soci=p_cod_soci
    AND e.cod_etap_deud=p_cod_etap_deud;

 RETURN v_desc_etap_deud;
 END COB_FN_OBTIE_DESCR_ETAPA_DEUDA;


   FUNCTION COB_FN_CALCU_PERIO_NSGTE(
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_nro_periodos number)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
     

  BEGIN
       RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL,
                                            p_cod_peri,
                                            p_nro_periodos);
   END COB_FN_CALCU_PERIO_NSGTE;

 FUNCTION COB_FN_OBTIE_EMAIL_ACTUA_CARTE(
  p_cod_pais                     seg_pais.cod_pais%TYPE,
  p_cod_soci                     seg_socie.cod_soci%TYPE)
 RETURN VARCHAR2
 IS
 
      CURSOR c_mail
      IS
        SELECT uc.val_mail
        FROM cob_usuar_cobra_pais uc
        WHERE uc.cod_pais = p_cod_pais
        AND uc.cod_soci = p_cod_soci
        AND uc.ind_acti=1
        AND uc.ind_mail_proc_actu=1;

        lv_list_mail  VARCHAR2(1000):=NULL;
        lv_cont         NUMBER(2):=0;
 BEGIN

  FOR v_mail IN c_mail  LOOP

         IF lv_cont > 0 THEN
            lv_list_mail:=lv_list_mail || ',' || v_mail.val_mail;
         ELSE
            lv_list_mail:= v_mail.val_mail;
         END IF;

         lv_cont:= lv_cont + 1 ;
      END LOOP;
      RETURN lv_list_mail;

 END COB_FN_OBTIE_EMAIL_ACTUA_CARTE;

 FUNCTION COB_FN_OBTIE_EMAIL_GEREN_APOYO(
  p_cod_regi                      IN   zon_regio.cod_regi%TYPE)   
 RETURN VARCHAR2 
 IS
 
  lv_val_emai                     mae_clien_comun.val_text_comu%TYPE;
  
 BEGIN
     
  SELECT mcc.val_text_comu
  INTO lv_val_emai
  FROM 
   zon_direc_venta_cabec ca, 
   zon_direc_venta_detal de, 
   zon_tipo_cargo tc,
   mae_clien mc,
   mae_clien_comun mcc,
   mae_tipo_comun mtc 
  WHERE ca.pais_cod_pais = de.pais_cod_pais 
    AND CA.TIOP_COD_TIPO_OPER=DE.TIOP_COD_TIPO_OPER 
    AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG 
    AND ca.cod_clie = de.cod_clie 
    AND mc.cod_clie = ca.cod_clie
    AND mc.cod_clie = de.cod_clie
    AND mc.oid_clie = mcc.clie_oid_clie
    AND mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
    AND mtc.cod_tipo_comu = 'ML'
    AND CA.FEC_REGI= DE.DICA_FEC_REGI 
    AND CA.CAM_PROC= DE.DICA_CAM_PROC 
    AND ca.est_regi = 1 
    AND de.est_regi = 1  
    AND TC.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG 
    AND TC.EST_REGI = 1 
    AND TC.COD_TIPO_CARG = 'YRF' 
    AND CA.ESCA_COD_ESTA_CARG = 'A'
    AND DE.COD_SUBG = 01 
    AND DE.COD_REGI = p_cod_regi; 

  RETURN lv_val_emai;
 
 EXCEPTION
  WHEN OTHERS THEN
   RETURN NULL;
        
 END COB_FN_OBTIE_EMAIL_GEREN_APOYO;

END COB_PKG_GENER;
/
