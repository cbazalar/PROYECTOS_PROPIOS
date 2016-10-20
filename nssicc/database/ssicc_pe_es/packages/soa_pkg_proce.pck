CREATE OR REPLACE PACKAGE soa_pkg_proce IS
  ls_sqlerrm VARCHAR2(150);
  -- Author  : DANNY AMARO
  -- Created : 04/01/2013 12:01:34 p.m.
  -- Purpose : Procedimientos de carga de datos de las tablas de SOA

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_COBRA_DEUDA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cobra_deuda;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_COBRA_DEUDA_SECCI
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cobra_secci;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CDR
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cdr;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONSU
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_consu;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_VISIT_CONSU
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_visit;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_TIPO
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_tipo_concu;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_REGAL_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_concu_regal;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_CONST_VENTA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_concu_venta;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_PROGR_NUEVA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_prg_nueva;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_DETAL_RECOM
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_recom;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_RECO
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_recon;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INFOR_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_pedid;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_RESUM_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_resum_pedid;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_ind_sicc_cobra;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_LETS
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_ind_sicc_lets;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_META
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_ind_sicc_meta;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_PEG
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_ind_sicc_peg;

  /************************************************************************************************************
  Descripcion       : Proceso de de invocacion a los procedimientos SOA para carga de data en las tablas de SOA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  *************************************************************************************************************/
  PROCEDURE soa_pr_ejecu_carga_data;

  /************************************************************************************************************
  Descripcion       : Proceso de de invocacion al  procedimiento SOA para carga de data en la tabla SOA_MV_CAMPA de SOA
  Fecha Creacion    : 05/09/2013
  Autor             : JPJC
  *************************************************************************************************************/
  PROCEDURE soa_pr_carga_data_mv_campa;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el estado del pedido
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_estad_pedid
  (
    pscodigoperiodo VARCHAR2,
    pnsecnumedocu   NUMBER,
    psnumlote       VARCHAR2,
    lnmontopedido   NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_PEDID_COMER
  Fecha Creacion    : 15/07/2013
  Autor             : Jean Pierre Jimenez
  ***************************************************************************/
  PROCEDURE soa_pr_ind_pedid_comer;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_PEDID_WEB_COMER
  Fecha Creacion    : 15/07/2013
  Autor             : Jean Pierre Jimenez
  ***************************************************************************/
  PROCEDURE soa_pr_ind_pedid_web_comer;
  /***************************************************************************
  Descripcion       : Funcion que devuelve el origen del pedido
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_orige_pedid
  (
    ps_ind_rece_onli int_solic_conso_cabec.ind_rece_onli %TYPE,
    ps_ind_rece_ivr  int_solic_conso_cabec.ind_rece_ivr %TYPE,
    ps_ind_rece_ocr  int_solic_conso_cabec.ind_rece_ocr %TYPE,
    ps_ind_rece_web  int_solic_conso_cabec.ind_rece_web %TYPE,
    ps_ind_rece_dd   int_solic_conso_cabec.ind_rece_dd %TYPE,
    ps_ind_rece_digi int_solic_conso_cabec.ind_rece_digi %TYPE,
    ps_ind_rece_cc   int_solic_conso_cabec.ind_rece_cc %TYPE,
    ps_ind_rece_mens int_solic_conso_cabec.ind_rece_mens %TYPE
  ) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Funcion que devuelve el motivo de rechazo
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_motiv_recha_pedid
  (
    pn_sec_nume_docu int_solic_conso_cabec.sec_nume_docu %TYPE,
    pn_ind_hist      NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Funcion que devuelve descripcion el motivo de rechazo
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_descr_motiv_recha_pedid(ps_cod_moti_rech VARCHAR2)
    RETURN VARCHAR2;
    /************************************************************************************************************
  Descripcion       : Proceso que acatualiza informacion de SOA despues de la facturacion
  Fecha Creacion    : 15/10/2015
  Autor             : Jose Cairampoma
  *************************************************************************************************************/
  PROCEDURE soa_pr_cierr_factu;
END soa_pkg_proce;
/
CREATE OR REPLACE PACKAGE BODY soa_pkg_proce IS

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_COBRA_DEUDA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cobra_deuda IS
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_cobra_deuda IS
      SELECT cod_pais,
             cod_peri,
             cod_regi,
             cod_zona,
             cod_secc,
             nom_mone,
             smb_mone,
             SUM(imp_fact_neto) imp_fact_neto,
             SUM(val_impo_can1) val_mont_cob1,
             SUM(val_impo_can2) val_mont_cob2,
             SUM(val_impo_can3) val_mont_cob3,
             SUM(val_impo_can4) val_mont_cob4,
             CASE
               WHEN SUM(imp_fact_neto) <> 0 THEN
                round(SUM(val_impo_can1) * 100 / SUM(imp_fact_neto), 2)
               ELSE
                0
             END val_porc_cob1,
             CASE
               WHEN SUM(imp_fact_neto) <> 0 THEN
                round(SUM(val_impo_can2) * 100 / SUM(imp_fact_neto), 2)
               ELSE
                0
             END val_porc_cob2,
             CASE
               WHEN SUM(imp_fact_neto) <> 0 THEN
                round(SUM(val_impo_can3) * 100 / SUM(imp_fact_neto), 2)
               ELSE
                0
             END val_porc_cob3,
             CASE
               WHEN SUM(imp_fact_neto) <> 0 THEN
                round(SUM(val_impo_can4) * 100 / SUM(imp_fact_neto), 2)
               ELSE
                0
             END val_porc_cob4,
             SUM(imp_sald_pend) imp_sald_pend,
             MIN(fec_cier_can1) fec_cier_cob1,
             MIN(fec_cier_can2) fec_cier_cob2,
             MIN(fec_cier_can3) fec_cier_cob3,
             MIN(fec_cier_can4) fec_cier_cob3,
             SUM(val_impo_can_36) val_impo_can_36,
             CASE
               WHEN SUM(imp_fact_neto) <> 0 THEN
                round(SUM(val_impo_can_36) * 100 / SUM(imp_fact_neto), 2)
               ELSE
                0
             END val_porc_cob_36,
             MIN(fec_cier_can_36) fec_cier_can_36
        FROM soa_cobra_deuda_secci
       GROUP BY cod_pais,
                cod_peri,
                cod_regi,
                cod_zona,
                cod_secc,
                nom_mone,
                smb_mone;
  
    TYPE recordcobradeuda IS RECORD(
      cod_pais        soa_cobra_deuda.cod_pais%TYPE,
      cod_peri        soa_cobra_deuda.cod_peri%TYPE,
      cod_regi        soa_cobra_deuda.cod_regi%TYPE,
      cod_zona        soa_cobra_deuda.cod_zona%TYPE,
      cod_secc        soa_cobra_deuda.cod_secc%TYPE,
      nom_mone        soa_cobra_deuda.nom_mone%TYPE,
      smb_mone        soa_cobra_deuda.smb_mone%TYPE,
      imp_fact_neto   soa_cobra_deuda.imp_fact_neto%TYPE,
      val_mont_cob1   soa_cobra_deuda.val_mont_cob1%TYPE,
      val_mont_cob2   soa_cobra_deuda.val_mont_cob2%TYPE,
      val_mont_cob3   soa_cobra_deuda.val_mont_cob3%TYPE,
      val_mont_cob4   soa_cobra_deuda.val_mont_cob4%TYPE,
      val_porc_cob1   soa_cobra_deuda.val_porc_cob1%TYPE,
      val_porc_cob2   soa_cobra_deuda.val_porc_cob2%TYPE,
      val_porc_cob3   soa_cobra_deuda.val_porc_cob3%TYPE,
      val_porc_cob4   soa_cobra_deuda.val_porc_cob4%TYPE,
      imp_sald_pend   soa_cobra_deuda.imp_sald_pend%TYPE,
      fec_cier_can1   soa_cobra_deuda.fec_cier_can1%TYPE,
      fec_cier_can2   soa_cobra_deuda.fec_cier_can2%TYPE,
      fec_cier_can3   soa_cobra_deuda.fec_cier_can3%TYPE,
      fec_cier_can4   soa_cobra_deuda.fec_cier_can4%TYPE,
      val_mont_cob_36 soa_cobra_deuda.val_mont_cob_36%TYPE,
      val_porc_cob_36 soa_cobra_deuda.val_porc_cob_36%TYPE,
      fec_cier_can_36 soa_cobra_deuda.fec_cier_can_36%TYPE);
  
    TYPE cobradeudatab IS TABLE OF recordcobradeuda;
    interfazrecord cobradeudatab;
  
    ldfecultiactu soa_cobra_deuda.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_cobra_deuda;
  
    SELECT SYSDATE INTO ldfecultiactu FROM dual;
  
    SELECT fec_utc_carga
      INTO ldfecutccarga
      FROM soa_proce_x_pais
     WHERE nom_enti = 'SOA_COBRA_DEUDA_SECCI';
  
    UPDATE soa_proce_x_pais
       SET fec_utc_carga = ldfecutccarga
     WHERE nom_enti = 'SOA_COBRA_DEUDA';
  
    OPEN c_soa_cobra_deuda;
    LOOP
      FETCH c_soa_cobra_deuda BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_cobra_deuda
            (cod_pais,
             cod_peri,
             cod_regi,
             cod_zona,
             cod_secc,
             nom_mone,
             smb_mone,
             imp_fact_neto,
             val_mont_cob1,
             val_mont_cob2,
             val_mont_cob3,
             val_mont_cob4,
             val_porc_cob1,
             val_porc_cob2,
             val_porc_cob3,
             val_porc_cob4,
             imp_sald_pend,
             fec_ulti_actu,
             fec_utc_carga,
             fec_cier_can1,
             fec_cier_can2,
             fec_cier_can3,
             fec_cier_can4,
             val_mont_cob_36,
             val_porc_cob_36,
             fec_cier_can_36)
          VALUES
            (interfazrecord(x).cod_pais,
             interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cod_secc,
             interfazrecord(x).nom_mone,
             interfazrecord(x).smb_mone,
             interfazrecord(x).imp_fact_neto,
             interfazrecord(x).val_mont_cob1,
             interfazrecord(x).val_mont_cob2,
             interfazrecord(x).val_mont_cob3,
             interfazrecord(x).val_mont_cob4,
             interfazrecord(x).val_porc_cob1,
             interfazrecord(x).val_porc_cob2,
             interfazrecord(x).val_porc_cob3,
             interfazrecord(x).val_porc_cob4,
             interfazrecord(x).imp_sald_pend,
             ldfecultiactu,
             ldfecutccarga,
             interfazrecord(x).fec_cier_can1,
             interfazrecord(x).fec_cier_can2,
             interfazrecord(x).fec_cier_can3,
             interfazrecord(x).fec_cier_can4,
             interfazrecord(x).val_mont_cob_36,
             interfazrecord(x).val_porc_cob_36,
             interfazrecord(x).fec_cier_can_36);
        END LOOP;
      
      END IF;
      EXIT WHEN c_soa_cobra_deuda%NOTFOUND;
    END LOOP;
    CLOSE c_soa_cobra_deuda;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR soa_pr_cobra_deuda: ' || ls_sqlerrm);
  END soa_pr_cobra_deuda;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_COBRA_DEUDA_SECCI
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cobra_secci IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_cobra_deuda_secci
    (
      lscodigoperiodo  VARCHAR2,
      ldfecultiactu    DATE,
      lnnumdiasprevios NUMBER,
      vsindsaldpend    VARCHAR2
    ) IS
      SELECT cbz.cod_pais,
             cbz.cod_peri,
             cbz.cod_regi,
             cbz.cod_zona,
             cbz.cod_secc,
             mc.cod_clie,
             mc.val_nom1,
             mc.val_nom2,
             mc.val_ape1,
             mc.val_ape2,
             fin_pkg_gener.fin_fn_obtie_numer_telef_clien(mc.oid_clie, 'TF') tele_fijo,
             fin_pkg_gener.fin_fn_obtie_numer_telef_clien(mc.oid_clie, 'TM') tele_movi,
             fin_pkg_gener.fin_fn_obtie_numer_telef_clien(mc.oid_clie, 'TT') tele_trab,
             mcc.fec_venc,
             nvl(cbz.imp_fact_neto, 0) imp_fact_neto,
             nvl(cbz.cob_dias_21, 0) val_impo_can1,
             nvl(CASE
                   WHEN vsindsaldpend = 'DV' THEN
                    cob_dias_vent
                   WHEN vsindsaldpend = 'D31' THEN
                    cob_dias_31
                   ELSE
                    cob_dias_31
                 END,
                 0) val_impo_can2,
             nvl(cbz.cob_dias_42, 0) val_impo_can3,
             nvl(cbz.cob_dias_63, 0) val_impo_can4,
             nvl(cbz.imp_sald_pend_sac, 0) imp_sald_pend,
             g.val_i18n nom_mone,
             sm.val_simb_mone smb_mone,
             cbz.oid_movi_cc,
             cbz.fec_cier_21 fec_cier_can1,
             CASE
               WHEN vsindsaldpend = 'DV' THEN
                cbz.fec_cier_vent
               WHEN vsindsaldpend = 'D31' THEN
                cbz.fec_cier_31
               ELSE
                cbz.fec_cier_31
             END fec_cier_can2,
             cbz.fec_cier_42 fec_cier_can3,
             cbz.fec_cier_63 fec_cier_can4,
             nvl(cbz.cob_dias_36, 0) val_impo_can_36,
             cbz.fec_cier_36 fec_cier_can_36
        FROM cob_repor_estad_recup_cobra cbz,
             mae_clien                   mc,
             ccc_movim_cuent_corri       mcc,
             gen_i18n_sicc_comun         g,
             seg_pais                    p,
             seg_moned                   sm
       WHERE cbz.oid_clie = mc.oid_clie
         AND cbz.oid_movi_cc = mcc.oid_movi_cc
         AND mcc.clie_oid_clie = mc.oid_clie
         AND g.attr_enti = 'SEG_MONED'
         AND p.mone_oid_mone = g.val_oid
         AND p.cod_pais = cbz.cod_pais
         AND sm.oid_mone = p.mone_oid_mone
         AND cbz.fec_modi BETWEEN ldfecultiactu - lnnumdiasprevios AND
             ldfecultiactu
         AND cbz.cod_peri = lscodigoperiodo;
    --  order by mc.cod_clie;
  
    TYPE recordcobradeudasecci IS RECORD(
      cod_pais        soa_cobra_deuda_secci.cod_pais%TYPE,
      cod_peri        soa_cobra_deuda_secci.cod_peri%TYPE,
      cod_regi        soa_cobra_deuda_secci.cod_regi%TYPE,
      cod_zona        soa_cobra_deuda_secci.cod_zona%TYPE,
      cod_secc        soa_cobra_deuda_secci.cod_secc%TYPE,
      cod_clie        soa_cobra_deuda_secci.cod_clie%TYPE,
      val_nom1        soa_cobra_deuda_secci.val_nom1%TYPE,
      val_nom2        soa_cobra_deuda_secci.val_nom2%TYPE,
      val_ape1        soa_cobra_deuda_secci.val_ape1%TYPE,
      val_ape2        soa_cobra_deuda_secci.val_ape2%TYPE,
      tele_fijo       soa_cobra_deuda_secci.tele_fijo%TYPE,
      tele_movi       soa_cobra_deuda_secci.tele_movi%TYPE,
      tele_trab       soa_cobra_deuda_secci.tele_trab%TYPE,
      fec_venc        soa_cobra_deuda_secci.fec_venc%TYPE,
      imp_fact_neto   soa_cobra_deuda_secci.imp_fact_neto%TYPE,
      val_impo_can1   soa_cobra_deuda_secci.val_impo_can1%TYPE,
      val_impo_can2   soa_cobra_deuda_secci.val_impo_can2%TYPE,
      val_impo_can3   soa_cobra_deuda_secci.val_impo_can3%TYPE,
      val_impo_can4   soa_cobra_deuda_secci.val_impo_can4%TYPE,
      imp_sald_pend   soa_cobra_deuda_secci.imp_sald_pend%TYPE,
      nom_mone        soa_cobra_deuda_secci.nom_mone%TYPE,
      smb_mone        soa_cobra_deuda_secci.smb_mone%TYPE,
      oid_movi_cc     soa_cobra_deuda_secci.oid_movi_cc%TYPE,
      fec_cier_can1   soa_cobra_deuda_secci.fec_cier_can1%TYPE,
      fec_cier_can2   soa_cobra_deuda_secci.fec_cier_can2%TYPE,
      fec_cier_can3   soa_cobra_deuda_secci.fec_cier_can3%TYPE,
      fec_cier_can4   soa_cobra_deuda_secci.fec_cier_can4%TYPE,
      val_impo_can_36 soa_cobra_deuda_secci.val_impo_can_36%TYPE,
      fec_cier_can_36 soa_cobra_deuda_secci.fec_cier_can_36%TYPE);
  
    TYPE cobradeudaseccitab IS TABLE OF recordcobradeudasecci;
    interfazrecord cobradeudaseccitab;
  
    ncamp     NUMBER;
    lsperiodo VARCHAR2(6);
  
    ldfecultiactu soa_cobra_deuda_secci.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
    lscodclie        VARCHAR2(15);
    lnnumdiasprevios NUMBER;
  
    lsindsaldpend bas_param_pais.val_para%TYPE;
  
  BEGIN
  
    -- DELETE FROM soa_cobra_deuda_secci;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    --obtenemos campanhas de histori
    BEGIN
      SELECT nvl(val_para, 1)
        INTO ncamp
        FROM bas_param_pais
       WHERE cod_pais = pscodigopais
         AND cod_sist = 'SOA'
         AND nom_para = 'periodosCobraDeudaSecciHistoria';
    EXCEPTION
      WHEN OTHERS THEN
        ncamp := 1;
    END;
  
    BEGIN
      SELECT val_para
        INTO lsindsaldpend
        FROM bas_param_pais
       WHERE cod_pais = pscodigopais
         AND cod_sist = 'SOA'
         AND nom_para = 'indSaldPend';
    EXCEPTION
      WHEN OTHERS THEN
        lsindsaldpend := NULL;
    END;
  
    --obtenemos num dias previos
    BEGIN
      SELECT nvl(val_para, 90)
        INTO lnnumdiasprevios
        FROM bas_param_pais
       WHERE cod_pais = pscodigopais
         AND cod_sist = 'SOA'
         AND nom_para = 'numDiasCobraDeudaSecci';
    EXCEPTION
      WHEN OTHERS THEN
        lnnumdiasprevios := 90;
    END;
  
    --se carga actual e historia
    lsperiodo := pscodigoperiodo;
  
    SELECT SYSDATE INTO ldfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    FOR i IN 1 .. ncamp
    LOOP
    
      lsperiodo := gen_fn_calcu_perio(pscodigoperiodo, (i - 1) * -1);
    
      lscodclie := '';
      OPEN c_soa_cobra_deuda_secci(lsperiodo,
                                   ldfecultiactu,
                                   lnnumdiasprevios,
                                   lsindsaldpend);
      LOOP
        FETCH c_soa_cobra_deuda_secci BULK COLLECT
          INTO interfazrecord LIMIT 1000;
        /* Procedimiento inicial para generar interfaz */
      
        IF interfazrecord.count > 0 THEN
          FOR x IN interfazrecord.first .. interfazrecord.last
          LOOP
          
            -- if(interfazrecord(x).cod_clie != lscodClie ) then
          
            DELETE soa_cobra_deuda_secci
             WHERE cod_peri = interfazrecord(x).cod_peri
               AND cod_regi = interfazrecord(x).cod_regi
               AND cod_zona = interfazrecord(x).cod_zona
               AND cod_secc = interfazrecord(x).cod_secc
               AND cod_clie = interfazrecord(x).cod_clie
               AND oid_movi_cc = interfazrecord(x).oid_movi_cc;
            --AND fec_ulti_actu >= ldfecultiactu - 5;
          
            --  end if;
          
            INSERT INTO soa_cobra_deuda_secci
              (cod_pais,
               cod_peri,
               cod_regi,
               cod_zona,
               cod_secc,
               cod_clie,
               val_nom1,
               val_nom2,
               val_ape1,
               val_ape2,
               tele_fijo,
               tele_movi,
               tele_trab,
               fec_venc,
               imp_fact_neto,
               val_impo_can1,
               val_impo_can2,
               val_impo_can3,
               val_impo_can4,
               imp_sald_pend,
               nom_mone,
               smb_mone,
               fec_ulti_actu,
               fec_utc_carga,
               oid_movi_cc,
               fec_cier_can1,
               fec_cier_can2,
               fec_cier_can3,
               fec_cier_can4,
               val_impo_can_36,
               fec_cier_can_36)
            VALUES
              (interfazrecord(x).cod_pais,
               interfazrecord(x).cod_peri,
               interfazrecord(x).cod_regi,
               interfazrecord(x).cod_zona,
               interfazrecord(x).cod_secc,
               interfazrecord(x).cod_clie,
               interfazrecord(x).val_nom1,
               interfazrecord(x).val_nom2,
               interfazrecord(x).val_ape1,
               interfazrecord(x).val_ape2,
               interfazrecord(x).tele_fijo,
               interfazrecord(x).tele_movi,
               interfazrecord(x).tele_trab,
               interfazrecord(x).fec_venc,
               interfazrecord(x).imp_fact_neto,
               interfazrecord(x).val_impo_can1,
               interfazrecord(x).val_impo_can2,
               interfazrecord(x).val_impo_can3,
               interfazrecord(x).val_impo_can4,
               interfazrecord(x).imp_sald_pend,
               interfazrecord(x).nom_mone,
               interfazrecord(x).smb_mone,
               ldfecultiactu,
               ldfecutccarga,
               interfazrecord(x).oid_movi_cc,
               interfazrecord(x).fec_cier_can1,
               interfazrecord(x).fec_cier_can2,
               interfazrecord(x).fec_cier_can3,
               interfazrecord(x).fec_cier_can4,
               interfazrecord(x).val_impo_can_36,
               interfazrecord(x).fec_cier_can_36);
          
          -- lscodClie:= interfazrecord(x).cod_clie;
          END LOOP;
        
          UPDATE soa_proce_x_pais
             SET cod_pais      = pscodigopais,
                 fec_utc_carga = ldfecutccarga
           WHERE nom_enti = 'SOA_COBRA_DEUDA_SECCI';
        
        END IF;
        EXIT WHEN c_soa_cobra_deuda_secci%NOTFOUND;
      END LOOP;
      CLOSE c_soa_cobra_deuda_secci;
    
    END LOOP;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'COBRANZA DEUDA SECCION: ' || ls_sqlerrm);
    
  END soa_pr_cobra_secci;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CDR
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_cdr IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_cdr(lscodigoperiodo VARCHAR2) IS
      SELECT a.cod_pais,
             cab.cod_peri,
             cab.num_docu nro_cdr,
             cab.cod_clie cod_clie,
             m.val_nom1,
             m.val_nom2,
             m.val_ape1,
             m.val_ape2,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'NOM_CLIE') nom_clie,
             cab.cod_peri camp_ingr,
             ---CAB.IND_EXPR,
             CASE
               WHEN cab.oid_peri_refe IS NOT NULL THEN
                gen_pkg_gener.gen_fn_devuelve_des_perio(cab.oid_peri_refe)
               ELSE
                ''
             END camp_refe,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_REGI') cod_regi,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_ZONA') cod_zona,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_SECC') cod_secc,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'DES_SECC') des_secc,
             (SELECT ro.val_desc_larg
                FROM rec_opera ro
               WHERE ro.cod_oper = det.cod_oper) tip_aten,
             (SELECT cod_moti_devo
                FROM rec_motiv_devol
               WHERE cod_moti_devo = det.mot_spv) cod_moti_devo,
             gen_pkg_gener.gen_fn_devuelve_descripcion((SELECT oid_moti_devo
                                                         FROM rec_motiv_devol
                                                        WHERE cod_moti_devo =
                                                              det.mot_spv),
                                                       'REC_MOTIV_DEVOL',
                                                       'es') des_moti,
             det.cod_vent_devu,
             pq_apl_aux.valor_gen_i18n_sicc(1,
                                            det.prod_oid_prod_devu,
                                            'MAE_PRODU') val_prod_devu,
             det.can_vent_devu,
             (det.can_vent_devu * det.val_prec_cata_devu) val_prec_cata_devu,
             det.cod_vent_dese,
             pq_apl_aux.valor_gen_i18n_sicc(1,
                                            det.prod_oid_prod_envi,
                                            'MAE_PRODU') val_prod_envi,
             det.can_prod_dese,
             (det.can_prod_dese * det.val_prec_cata_envi) val_prec_cata_envi,
             (sto.ind_envi || sto.ind_rech) des_esta,
             decode(sto.ind_envi || sto.ind_rech,
                    '01',
                    'No Procesado',
                    '10',
                    'Procesado',
                    'En Gestion') cod_esta,
             (SELECT men.des_larg_mens
                FROM sto_mensa_valid men
               WHERE men.cod_vali = sto.cod_ulti_vali_erro) val_obs,
             b.val_simb_mone,
             pq_apl_aux.valor_gen_i18n_sicc(1, b.oid_mone, 'SEG_MONED') des_moned
        FROM int_solic_conso_poven_cabec cab,
             sto_docum_digit             sto,
             int_solic_conso_poven_detal det,
             mae_clien                   m,
             seg_pais                    a,
             seg_moned                   b
       WHERE cab.sec_nume_docu = sto.sec_nume_docu_cabe
         AND sto.sec_nume_docu = det.sec_nume_docu
         AND cab.cod_peri = lscodigoperiodo
         AND m.cod_clie = cab.cod_clie
         AND a.cod_pais = cab.cod_pais
         AND b.oid_mone = a.mone_oid_mone;
  
    TYPE recordcdr IS RECORD(
      cod_pais           soa_cdr.cod_pais%TYPE,
      cod_peri           soa_cdr.cod_peri%TYPE,
      nro_cdr            soa_cdr.nro_cdr%TYPE,
      cod_clie           soa_cdr.cod_clie%TYPE,
      val_nom1           soa_cdr.val_nom1%TYPE,
      val_nom2           soa_cdr.val_nom2%TYPE,
      val_ape1           soa_cdr.val_ape1%TYPE,
      val_ape2           soa_cdr.val_ape2%TYPE,
      nom_clie           soa_cdr.nom_clie%TYPE,
      camp_ingr          soa_cdr.camp_ingr%TYPE,
      camp_refe          soa_cdr.camp_refe%TYPE,
      cod_regi           soa_cdr.cod_regi%TYPE,
      cod_zona           soa_cdr.cod_zona%TYPE,
      cod_secc           soa_cdr.cod_secc%TYPE,
      des_secc           soa_cdr.des_secc%TYPE,
      tip_aten           soa_cdr.tip_aten%TYPE,
      cod_moti_devo      soa_cdr.cod_moti_devo%TYPE,
      des_moti           soa_cdr.des_moti%TYPE,
      cod_vent_devu      soa_cdr.cod_vent_devu%TYPE,
      val_prod_devu      soa_cdr.val_prod_devu%TYPE,
      can_vent_devu      soa_cdr.can_vent_devu%TYPE,
      val_prec_cata_devu soa_cdr.val_prec_cata_devu%TYPE,
      cod_vent_dese      soa_cdr.cod_vent_dese%TYPE,
      val_prod_envi      soa_cdr.val_prod_envi%TYPE,
      can_prod_dese      soa_cdr.can_prod_dese%TYPE,
      val_prec_cata_envi soa_cdr.val_prec_cata_envi%TYPE,
      des_esta           soa_cdr.des_esta%TYPE,
      cod_esta           soa_cdr.cod_esta%TYPE,
      val_obs            soa_cdr.val_obs%TYPE,
      val_simb_mone      soa_cdr.val_simb_mone%TYPE,
      des_moned          soa_cdr.des_moned%TYPE);
  
    TYPE cdrtab IS TABLE OF recordcdr;
    interfazrecord cdrtab;
  
    ncamp  NUMBER;
    lncont NUMBER;
  
    lncont2          NUMBER;
    lsperiodo        VARCHAR2(6);
    lndata           NUMBER;
    lsminicodperiodo VARCHAR2(6);
  
    psfecultiactu soa_cdr.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT COUNT(1)
      INTO lncont
      FROM (SELECT DISTINCT cod_peri FROM soa_cdr);
  
    --obtenemos campanhas de histori
    BEGIN
      SELECT nvl(val_para, 1)
        INTO ncamp
        FROM bas_param_pais
       WHERE cod_pais = pscodigopais
         AND cod_sist = 'SOA'
         AND nom_para = 'periodosCdrHistoria';
    EXCEPTION
      WHEN OTHERS THEN
        ncamp := 1;
    END;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    IF (lncont = 0) THEN
      --se carga actual e historia
      lsperiodo := pscodigoperiodo;
    
      FOR i IN 1 .. ncamp
      LOOP
        IF (i = 1) THEN
        
          OPEN c_soa_cdr(lsperiodo);
          LOOP
            FETCH c_soa_cdr BULK COLLECT
              INTO interfazrecord LIMIT 1000;
            /* Procedimiento inicial para generar interfaz */
          
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
                INSERT INTO soa_cdr
                  (cod_pais,
                   cod_peri,
                   nro_cdr,
                   cod_clie,
                   val_nom1,
                   val_nom2,
                   val_ape1,
                   val_ape2,
                   nom_clie,
                   camp_ingr,
                   camp_refe,
                   cod_regi,
                   cod_zona,
                   cod_secc,
                   des_secc,
                   tip_aten,
                   cod_moti_devo,
                   des_moti,
                   cod_vent_devu,
                   val_prod_devu,
                   can_vent_devu,
                   val_prec_cata_devu,
                   cod_vent_dese,
                   val_prod_envi,
                   can_prod_dese,
                   val_prec_cata_envi,
                   des_esta,
                   cod_esta,
                   val_obs,
                   val_simb_mone,
                   des_moned,
                   fec_ulti_actu,
                   fec_utc_carga)
                VALUES
                  (interfazrecord(x).cod_pais,
                   interfazrecord(x).cod_peri,
                   interfazrecord(x).nro_cdr,
                   interfazrecord(x).cod_clie,
                   interfazrecord(x).val_nom1,
                   interfazrecord(x).val_nom2,
                   interfazrecord(x).val_ape1,
                   interfazrecord(x).val_ape2,
                   interfazrecord(x).nom_clie,
                   interfazrecord(x).camp_ingr,
                   interfazrecord(x).camp_refe,
                   interfazrecord(x).cod_regi,
                   interfazrecord(x).cod_zona,
                   interfazrecord(x).cod_secc,
                   interfazrecord(x).des_secc,
                   interfazrecord(x).tip_aten,
                   interfazrecord(x).cod_moti_devo,
                   interfazrecord(x).des_moti,
                   interfazrecord(x).cod_vent_devu,
                   interfazrecord(x).val_prod_devu,
                   interfazrecord(x).can_vent_devu,
                   interfazrecord(x).val_prec_cata_devu,
                   interfazrecord(x).cod_vent_dese,
                   interfazrecord(x).val_prod_envi,
                   interfazrecord(x).can_prod_dese,
                   interfazrecord(x).val_prec_cata_envi,
                   interfazrecord(x).des_esta,
                   interfazrecord(x).cod_esta,
                   interfazrecord(x).val_obs,
                   interfazrecord(x).val_simb_mone,
                   interfazrecord(x).des_moned,
                   psfecultiactu,
                   ldfecutccarga);
              
              END LOOP;
            
              UPDATE soa_proce_x_pais
                 SET cod_pais      = pscodigopais,
                     fec_utc_carga = ldfecutccarga
               WHERE nom_enti = 'SOA_CDR';
            
            END IF;
            EXIT WHEN c_soa_cdr%NOTFOUND;
          END LOOP;
          CLOSE c_soa_cdr;
        
        ELSE
          lsperiodo := gen_fn_calcu_perio(pscodigoperiodo, (i - 1) * -1);
        
          OPEN c_soa_cdr(lsperiodo);
          LOOP
            FETCH c_soa_cdr BULK COLLECT
              INTO interfazrecord LIMIT 1000;
            /* Procedimiento inicial para generar interfaz */
          
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
                INSERT INTO soa_cdr
                  (cod_pais,
                   cod_peri,
                   nro_cdr,
                   cod_clie,
                   val_nom1,
                   val_nom2,
                   val_ape1,
                   val_ape2,
                   nom_clie,
                   camp_ingr,
                   camp_refe,
                   cod_regi,
                   cod_zona,
                   cod_secc,
                   des_secc,
                   tip_aten,
                   cod_moti_devo,
                   des_moti,
                   cod_vent_devu,
                   val_prod_devu,
                   can_vent_devu,
                   val_prec_cata_devu,
                   cod_vent_dese,
                   val_prod_envi,
                   can_prod_dese,
                   val_prec_cata_envi,
                   des_esta,
                   cod_esta,
                   val_obs,
                   val_simb_mone,
                   des_moned,
                   fec_ulti_actu,
                   fec_utc_carga)
                VALUES
                  (interfazrecord(x).cod_pais,
                   interfazrecord(x).cod_peri,
                   interfazrecord(x).nro_cdr,
                   interfazrecord(x).cod_clie,
                   interfazrecord(x).val_nom1,
                   interfazrecord(x).val_nom2,
                   interfazrecord(x).val_ape1,
                   interfazrecord(x).val_ape2,
                   interfazrecord(x).nom_clie,
                   interfazrecord(x).camp_ingr,
                   interfazrecord(x).camp_refe,
                   interfazrecord(x).cod_regi,
                   interfazrecord(x).cod_zona,
                   interfazrecord(x).cod_secc,
                   interfazrecord(x).des_secc,
                   interfazrecord(x).tip_aten,
                   interfazrecord(x).cod_moti_devo,
                   interfazrecord(x).des_moti,
                   interfazrecord(x).cod_vent_devu,
                   interfazrecord(x).val_prod_devu,
                   interfazrecord(x).can_vent_devu,
                   interfazrecord(x).val_prec_cata_devu,
                   interfazrecord(x).cod_vent_dese,
                   interfazrecord(x).val_prod_envi,
                   interfazrecord(x).can_prod_dese,
                   interfazrecord(x).val_prec_cata_envi,
                   interfazrecord(x).des_esta,
                   interfazrecord(x).cod_esta,
                   interfazrecord(x).val_obs,
                   interfazrecord(x).val_simb_mone,
                   interfazrecord(x).des_moned,
                   psfecultiactu,
                   ldfecutccarga);
              END LOOP;
            
              UPDATE soa_proce_x_pais
                 SET cod_pais      = pscodigopais,
                     fec_utc_carga = ldfecutccarga
               WHERE nom_enti = 'SOA_CDR';
            
            END IF;
            EXIT WHEN c_soa_cdr%NOTFOUND;
          END LOOP;
          CLOSE c_soa_cdr;
        
        END IF;
      
      END LOOP;
    
    ELSE
      --ya hay data registrada
      --si se tiene menos historia q la solicitada
      IF (lncont < ncamp) THEN
        --refrescar la campnha actual
        lsperiodo := pscodigoperiodo;
        FOR i IN 1 .. ncamp
        LOOP
          IF (i = 1) THEN
            DELETE FROM soa_cdr WHERE cod_peri = lsperiodo;
          
            OPEN c_soa_cdr(lsperiodo);
            LOOP
              FETCH c_soa_cdr BULK COLLECT
                INTO interfazrecord LIMIT 1000;
              /* Procedimiento inicial para generar interfaz */
            
              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  INSERT INTO soa_cdr
                    (cod_pais,
                     cod_peri,
                     nro_cdr,
                     cod_clie,
                     val_nom1,
                     val_nom2,
                     val_ape1,
                     val_ape2,
                     nom_clie,
                     camp_ingr,
                     camp_refe,
                     cod_regi,
                     cod_zona,
                     cod_secc,
                     des_secc,
                     tip_aten,
                     cod_moti_devo,
                     des_moti,
                     cod_vent_devu,
                     val_prod_devu,
                     can_vent_devu,
                     val_prec_cata_devu,
                     cod_vent_dese,
                     val_prod_envi,
                     can_prod_dese,
                     val_prec_cata_envi,
                     des_esta,
                     cod_esta,
                     val_obs,
                     val_simb_mone,
                     des_moned,
                     fec_ulti_actu,
                     fec_utc_carga)
                  VALUES
                    (interfazrecord(x).cod_pais,
                     interfazrecord(x).cod_peri,
                     interfazrecord(x).nro_cdr,
                     interfazrecord(x).cod_clie,
                     interfazrecord(x).val_nom1,
                     interfazrecord(x).val_nom2,
                     interfazrecord(x).val_ape1,
                     interfazrecord(x).val_ape2,
                     interfazrecord(x).nom_clie,
                     interfazrecord(x).camp_ingr,
                     interfazrecord(x).camp_refe,
                     interfazrecord(x).cod_regi,
                     interfazrecord(x).cod_zona,
                     interfazrecord(x).cod_secc,
                     interfazrecord(x).des_secc,
                     interfazrecord(x).tip_aten,
                     interfazrecord(x).cod_moti_devo,
                     interfazrecord(x).des_moti,
                     interfazrecord(x).cod_vent_devu,
                     interfazrecord(x).val_prod_devu,
                     interfazrecord(x).can_vent_devu,
                     interfazrecord(x).val_prec_cata_devu,
                     interfazrecord(x).cod_vent_dese,
                     interfazrecord(x).val_prod_envi,
                     interfazrecord(x).can_prod_dese,
                     interfazrecord(x).val_prec_cata_envi,
                     interfazrecord(x).des_esta,
                     interfazrecord(x).cod_esta,
                     interfazrecord(x).val_obs,
                     interfazrecord(x).val_simb_mone,
                     interfazrecord(x).des_moned,
                     psfecultiactu,
                     ldfecutccarga);
                
                END LOOP;
              
                UPDATE soa_proce_x_pais
                   SET cod_pais      = pscodigopais,
                       fec_utc_carga = ldfecutccarga
                 WHERE nom_enti = 'SOA_CDR';
              
              END IF;
              EXIT WHEN c_soa_cdr%NOTFOUND;
            END LOOP;
            CLOSE c_soa_cdr;
          
          ELSE
            lsperiodo := gen_fn_calcu_perio(pscodigoperiodo, (i - 1) * -1);
            --validamos si hay data hsitorica si no hay para la campanha se inserta
          
            SELECT COUNT(1)
              INTO lndata
              FROM soa_cdr
             WHERE cod_peri = lsperiodo;
          
            IF (lndata = 0) THEN
              --si no hya data nsertamos
              OPEN c_soa_cdr(lsperiodo);
              LOOP
                FETCH c_soa_cdr BULK COLLECT
                  INTO interfazrecord LIMIT 1000;
                /* Procedimiento inicial para generar interfaz */
              
                IF interfazrecord.count > 0 THEN
                  FOR x IN interfazrecord.first .. interfazrecord.last
                  LOOP
                    INSERT INTO soa_cdr
                      (cod_pais,
                       cod_peri,
                       nro_cdr,
                       cod_clie,
                       val_nom1,
                       val_nom2,
                       val_ape1,
                       val_ape2,
                       nom_clie,
                       camp_ingr,
                       camp_refe,
                       cod_regi,
                       cod_zona,
                       cod_secc,
                       des_secc,
                       tip_aten,
                       cod_moti_devo,
                       des_moti,
                       cod_vent_devu,
                       val_prod_devu,
                       can_vent_devu,
                       val_prec_cata_devu,
                       cod_vent_dese,
                       val_prod_envi,
                       can_prod_dese,
                       val_prec_cata_envi,
                       des_esta,
                       cod_esta,
                       val_obs,
                       val_simb_mone,
                       des_moned,
                       fec_ulti_actu,
                       fec_utc_carga)
                    VALUES
                      (interfazrecord(x).cod_pais,
                       interfazrecord(x).cod_peri,
                       interfazrecord(x).nro_cdr,
                       interfazrecord(x).cod_clie,
                       interfazrecord(x).val_nom1,
                       interfazrecord(x).val_nom2,
                       interfazrecord(x).val_ape1,
                       interfazrecord(x).val_ape2,
                       interfazrecord(x).nom_clie,
                       interfazrecord(x).camp_ingr,
                       interfazrecord(x).camp_refe,
                       interfazrecord(x).cod_regi,
                       interfazrecord(x).cod_zona,
                       interfazrecord(x).cod_secc,
                       interfazrecord(x).des_secc,
                       interfazrecord(x).tip_aten,
                       interfazrecord(x).cod_moti_devo,
                       interfazrecord(x).des_moti,
                       interfazrecord(x).cod_vent_devu,
                       interfazrecord(x).val_prod_devu,
                       interfazrecord(x).can_vent_devu,
                       interfazrecord(x).val_prec_cata_devu,
                       interfazrecord(x).cod_vent_dese,
                       interfazrecord(x).val_prod_envi,
                       interfazrecord(x).can_prod_dese,
                       interfazrecord(x).val_prec_cata_envi,
                       interfazrecord(x).des_esta,
                       interfazrecord(x).cod_esta,
                       interfazrecord(x).val_obs,
                       interfazrecord(x).val_simb_mone,
                       interfazrecord(x).des_moned,
                       psfecultiactu,
                       ldfecutccarga);
                  END LOOP;
                
                  UPDATE soa_proce_x_pais
                     SET cod_pais      = pscodigopais,
                         fec_utc_carga = ldfecutccarga
                   WHERE nom_enti = 'SOA_CDR';
                
                END IF;
                EXIT WHEN c_soa_cdr%NOTFOUND;
              END LOOP;
              CLOSE c_soa_cdr;
            END IF;
          END IF;
        END LOOP;
      
      END IF; --fin si ampliaron la historia
    
      --si son iguales slo se refresca la actual
      IF (lncont = ncamp) THEN
        --refrescar la campnha actual
        lsperiodo := pscodigoperiodo;
      
        DELETE FROM soa_cdr WHERE cod_peri = lsperiodo;
      
        OPEN c_soa_cdr(lsperiodo);
        LOOP
          FETCH c_soa_cdr BULK COLLECT
            INTO interfazrecord LIMIT 1000;
          /* Procedimiento inicial para generar interfaz */
        
          IF interfazrecord.count > 0 THEN
            FOR x IN interfazrecord.first .. interfazrecord.last
            LOOP
              INSERT INTO soa_cdr
                (cod_pais,
                 cod_peri,
                 nro_cdr,
                 cod_clie,
                 val_nom1,
                 val_nom2,
                 val_ape1,
                 val_ape2,
                 nom_clie,
                 camp_ingr,
                 camp_refe,
                 cod_regi,
                 cod_zona,
                 cod_secc,
                 des_secc,
                 tip_aten,
                 cod_moti_devo,
                 des_moti,
                 cod_vent_devu,
                 val_prod_devu,
                 can_vent_devu,
                 val_prec_cata_devu,
                 cod_vent_dese,
                 val_prod_envi,
                 can_prod_dese,
                 val_prec_cata_envi,
                 des_esta,
                 cod_esta,
                 val_obs,
                 val_simb_mone,
                 des_moned,
                 fec_ulti_actu,
                 fec_utc_carga)
              VALUES
                (interfazrecord(x).cod_pais,
                 interfazrecord(x).cod_peri,
                 interfazrecord(x).nro_cdr,
                 interfazrecord(x).cod_clie,
                 interfazrecord(x).val_nom1,
                 interfazrecord(x).val_nom2,
                 interfazrecord(x).val_ape1,
                 interfazrecord(x).val_ape2,
                 interfazrecord(x).nom_clie,
                 interfazrecord(x).camp_ingr,
                 interfazrecord(x).camp_refe,
                 interfazrecord(x).cod_regi,
                 interfazrecord(x).cod_zona,
                 interfazrecord(x).cod_secc,
                 interfazrecord(x).des_secc,
                 interfazrecord(x).tip_aten,
                 interfazrecord(x).cod_moti_devo,
                 interfazrecord(x).des_moti,
                 interfazrecord(x).cod_vent_devu,
                 interfazrecord(x).val_prod_devu,
                 interfazrecord(x).can_vent_devu,
                 interfazrecord(x).val_prec_cata_devu,
                 interfazrecord(x).cod_vent_dese,
                 interfazrecord(x).val_prod_envi,
                 interfazrecord(x).can_prod_dese,
                 interfazrecord(x).val_prec_cata_envi,
                 interfazrecord(x).des_esta,
                 interfazrecord(x).cod_esta,
                 interfazrecord(x).val_obs,
                 interfazrecord(x).val_simb_mone,
                 interfazrecord(x).des_moned,
                 psfecultiactu,
                 ldfecutccarga);
            
            END LOOP;
          
            UPDATE soa_proce_x_pais
               SET cod_pais      = pscodigopais,
                   fec_utc_carga = ldfecutccarga
             WHERE nom_enti = 'SOA_CDR';
          
          END IF;
          EXIT WHEN c_soa_cdr%NOTFOUND;
        END LOOP;
        CLOSE c_soa_cdr;
      
        -- verificamos periodos
        SELECT COUNT(1)
          INTO lncont2
          FROM (SELECT DISTINCT cod_peri FROM soa_cdr);
      
        IF (lncont2 > ncamp) THEN
          --eliminamos el menor peridod
          SELECT MIN(cod_peri) INTO lsminicodperiodo FROM soa_cdr;
        
          DELETE FROM soa_cdr WHERE cod_peri = lsminicodperiodo;
        
        END IF;
      
      END IF; --fin si son iguales
    
      --si se tiene mas en periodos a lo q se requiere en historia
      IF (lncont > ncamp) THEN
        --refrescar la campnha actual
        lsperiodo := pscodigoperiodo;
        FOR i IN 1 .. ncamp
        LOOP
          IF (i = 1) THEN
            DELETE FROM soa_cdr WHERE cod_peri = lsperiodo;
          
            OPEN c_soa_cdr(lsperiodo);
            LOOP
              FETCH c_soa_cdr BULK COLLECT
                INTO interfazrecord LIMIT 1000;
              /* Procedimiento inicial para generar interfaz */
            
              IF interfazrecord.count > 0 THEN
                FOR x IN interfazrecord.first .. interfazrecord.last
                LOOP
                  INSERT INTO soa_cdr
                    (cod_pais,
                     cod_peri,
                     nro_cdr,
                     cod_clie,
                     val_nom1,
                     val_nom2,
                     val_ape1,
                     val_ape2,
                     nom_clie,
                     camp_ingr,
                     camp_refe,
                     cod_regi,
                     cod_zona,
                     cod_secc,
                     des_secc,
                     tip_aten,
                     cod_moti_devo,
                     des_moti,
                     cod_vent_devu,
                     val_prod_devu,
                     can_vent_devu,
                     val_prec_cata_devu,
                     cod_vent_dese,
                     val_prod_envi,
                     can_prod_dese,
                     val_prec_cata_envi,
                     des_esta,
                     cod_esta,
                     val_obs,
                     val_simb_mone,
                     des_moned,
                     fec_ulti_actu,
                     fec_utc_carga)
                  VALUES
                    (interfazrecord(x).cod_pais,
                     interfazrecord(x).cod_peri,
                     interfazrecord(x).nro_cdr,
                     interfazrecord(x).cod_clie,
                     interfazrecord(x).val_nom1,
                     interfazrecord(x).val_nom2,
                     interfazrecord(x).val_ape1,
                     interfazrecord(x).val_ape2,
                     interfazrecord(x).nom_clie,
                     interfazrecord(x).camp_ingr,
                     interfazrecord(x).camp_refe,
                     interfazrecord(x).cod_regi,
                     interfazrecord(x).cod_zona,
                     interfazrecord(x).cod_secc,
                     interfazrecord(x).des_secc,
                     interfazrecord(x).tip_aten,
                     interfazrecord(x).cod_moti_devo,
                     interfazrecord(x).des_moti,
                     interfazrecord(x).cod_vent_devu,
                     interfazrecord(x).val_prod_devu,
                     interfazrecord(x).can_vent_devu,
                     interfazrecord(x).val_prec_cata_devu,
                     interfazrecord(x).cod_vent_dese,
                     interfazrecord(x).val_prod_envi,
                     interfazrecord(x).can_prod_dese,
                     interfazrecord(x).val_prec_cata_envi,
                     interfazrecord(x).des_esta,
                     interfazrecord(x).cod_esta,
                     interfazrecord(x).val_obs,
                     interfazrecord(x).val_simb_mone,
                     interfazrecord(x).des_moned,
                     psfecultiactu,
                     ldfecutccarga);
                
                END LOOP;
              
                UPDATE soa_proce_x_pais
                   SET cod_pais      = pscodigopais,
                       fec_utc_carga = ldfecutccarga
                 WHERE nom_enti = 'SOA_CDR';
              
              END IF;
              EXIT WHEN c_soa_cdr%NOTFOUND;
            END LOOP;
            CLOSE c_soa_cdr;
          
          ELSE
            lsperiodo := gen_fn_calcu_perio(pscodigoperiodo, (i - 1) * -1);
            --validamos si hay data hsitorica si no hay para la campanha se inserta
          
            SELECT COUNT(1)
              INTO lndata
              FROM soa_cdr
             WHERE cod_peri = lsperiodo;
          
            IF (lndata = 0) THEN
              OPEN c_soa_cdr(lsperiodo);
              LOOP
                FETCH c_soa_cdr BULK COLLECT
                  INTO interfazrecord LIMIT 1000;
                /* Procedimiento inicial para generar interfaz */
              
                IF interfazrecord.count > 0 THEN
                  FOR x IN interfazrecord.first .. interfazrecord.last
                  LOOP
                    INSERT INTO soa_cdr
                      (cod_pais,
                       cod_peri,
                       nro_cdr,
                       cod_clie,
                       val_nom1,
                       val_nom2,
                       val_ape1,
                       val_ape2,
                       nom_clie,
                       camp_ingr,
                       camp_refe,
                       cod_regi,
                       cod_zona,
                       cod_secc,
                       des_secc,
                       tip_aten,
                       cod_moti_devo,
                       des_moti,
                       cod_vent_devu,
                       val_prod_devu,
                       can_vent_devu,
                       val_prec_cata_devu,
                       cod_vent_dese,
                       val_prod_envi,
                       can_prod_dese,
                       val_prec_cata_envi,
                       des_esta,
                       cod_esta,
                       val_obs,
                       val_simb_mone,
                       des_moned,
                       fec_ulti_actu,
                       fec_utc_carga)
                    VALUES
                      (interfazrecord(x).cod_pais,
                       interfazrecord(x).cod_peri,
                       interfazrecord(x).nro_cdr,
                       interfazrecord(x).cod_clie,
                       interfazrecord(x).val_nom1,
                       interfazrecord(x).val_nom2,
                       interfazrecord(x).val_ape1,
                       interfazrecord(x).val_ape2,
                       interfazrecord(x).nom_clie,
                       interfazrecord(x).camp_ingr,
                       interfazrecord(x).camp_refe,
                       interfazrecord(x).cod_regi,
                       interfazrecord(x).cod_zona,
                       interfazrecord(x).cod_secc,
                       interfazrecord(x).des_secc,
                       interfazrecord(x).tip_aten,
                       interfazrecord(x).cod_moti_devo,
                       interfazrecord(x).des_moti,
                       interfazrecord(x).cod_vent_devu,
                       interfazrecord(x).val_prod_devu,
                       interfazrecord(x).can_vent_devu,
                       interfazrecord(x).val_prec_cata_devu,
                       interfazrecord(x).cod_vent_dese,
                       interfazrecord(x).val_prod_envi,
                       interfazrecord(x).can_prod_dese,
                       interfazrecord(x).val_prec_cata_envi,
                       interfazrecord(x).des_esta,
                       interfazrecord(x).cod_esta,
                       interfazrecord(x).val_obs,
                       interfazrecord(x).val_simb_mone,
                       interfazrecord(x).des_moned,
                       psfecultiactu,
                       ldfecutccarga);
                  END LOOP;
                
                  UPDATE soa_proce_x_pais
                     SET cod_pais      = pscodigopais,
                         fec_utc_carga = ldfecutccarga
                   WHERE nom_enti = 'SOA_CDR';
                
                END IF;
                EXIT WHEN c_soa_cdr%NOTFOUND;
              END LOOP;
              CLOSE c_soa_cdr;
            END IF; --fin si no hay data en ese periodo
          
            IF (i = ncamp) THEN
              --si estams en el ultimo perido se borran los demas pedidod del perriod+1
              DELETE FROM soa_cdr WHERE cod_peri > lsperiodo;
            END IF;
          
          END IF;
        END LOOP;
      
      END IF; --fin si ampliaron la historia
    
    END IF; --fial si hay dat historica
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR INFORME CDR: ' || ls_sqlerrm);
    
  END soa_pr_cdr;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONSU
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/
  PROCEDURE soa_pr_consu IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_consu IS
      SELECT a.cod_pais,
             pscodigoperiodo cod_peri,
             clie.cod_clie,
             clie.oid_clie,
             clie.val_nom1 || ' ' || clie.val_nom2 || ' ' || clie.val_ape1 || ' ' ||
             clie.val_ape2 nom_cons,
             clie.val_nom1,
             clie.val_nom2,
             clie.val_ape1,
             clie.val_ape2,
             zorg.cod_regi,
             zzon.cod_zona,
             zscc.cod_secc,
             terr.cod_terr,
             (SELECT clid.num_docu_iden
                FROM mae_clien_ident clid
               WHERE clid.clie_oid_clie = clie.oid_clie
                 AND clid.val_iden_docu_prin = 1
                 AND rownum = 1) val_docu_iden,
             TRIM(nvl(dire.val_nomb_via, ' ') || nvl(dire.num_ppal, ' ') || ' ' ||
                  nvl(dire.val_obse, ' ')) val_dire,
             'TF' val_tipo_tele_fijo,
             'TM' val_tipo_tele_movi,
             (SELECT clco.val_text_comu
                FROM mae_clien_comun clco
               WHERE clco.clie_oid_clie = clie.oid_clie
                 AND clco.ticm_oid_tipo_comu = 1) val_tele_fijo,
             (SELECT clco.val_text_comu
                FROM mae_clien_comun clco
               WHERE clco.clie_oid_clie = clie.oid_clie
                 AND clco.ticm_oid_tipo_comu = 6) val_tele_celu,
             (SELECT upper(gens.val_i18n) estado_actividad
                FROM gen_i18n_sicc_comun gens
               WHERE gens.attr_enti = 'MAE_ESTAT_CLIEN'
                 AND gens.val_oid = clda.esta_oid_esta_clie) val_esta_consu,
             (SELECT cod_esta_clie
                FROM mae_estat_clien z
               WHERE z.oid_esta_clie = clda.esta_oid_esta_clie) cod_esta_clie,
             nvl(clie.sal_deud_ante, 0) val_sald_pend,
             CASE
               WHEN (gen_pkg_gener.gen_fn_clien_bloqu(clie.cod_clie, '01') = 1 OR
                    gen_pkg_gener.gen_fn_clien_bloqu(clie.cod_clie, '02') = 1) OR
                    clda.ind_acti = 0 THEN
                0
               ELSE
                1
             END val_auto_pase,
             nvl(camp.val_tota_paga_loca, 0) val_mont_pedi,
             (SELECT COUNT(1)
                FROM edu_histo_capac_cabec hicc
               WHERE hicc.clie_cod_clie = clie.cod_clie
                 AND hicc.ult_camp_capa = pscodigoperiodo) ind_capa,
             (SELECT CASE
                       WHEN COUNT(1) > 0 THEN
                        1
                       ELSE
                        0
                     END ind_pedi
                FROM int_solic_conso_cabec
               WHERE cod_pais = pscodigopais
                 AND cod_peri = pscodigoperiodo
                 AND cod_clie = clie.cod_clie) ind_cons_pedi,
             d.val_simb_mone,
             pq_apl_aux.valor_gen_i18n_sicc(1, d.oid_mone, 'SEG_MONED') des_moned,
             CASE
               WHEN EXISTS
                (SELECT ts.clie_oid_clie
                       FROM mae_clien_clasi      cc,
                            mae_clien_tipo_subti ts,
                            mae_tipo_clasi_clien tcc,
                            mae_clasi            mcls
                      WHERE cc.ctsu_oid_clie_tipo_subt =
                            ts.oid_clie_tipo_subt
                        AND cc.tccl_oid_tipo_clasi = tcc.oid_tipo_clas
                        AND mcls.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                        AND ts.clie_oid_clie = clie.oid_clie
                        AND mcls.cod_clas = '01'
                        AND tcc.cod_tipo_clas = '13'
                        AND rownum < 2) THEN
                'SI'
               ELSE
                'NO'
             END ind_cons_brill
        FROM mae_clien_datos_adici clda,
             mae_clien clie,
             mae_clien_prime_conta cprc,
             mae_clien_unida_admin cuad,
             zon_terri_admin ztad,
             zon_terri terr,
             zon_secci zscc,
             zon_zona zzon,
             zon_regio zorg,
             (SELECT soca.clie_oid_clie,
                     SUM(soca.val_tota_paga_loca) val_tota_paga_loca
                FROM ped_solic_cabec          soca,
                     ped_solic_cabec          cons,
                     ped_tipo_solic_pais      tspa,
                     own_comun.ped_tipo_solic tsol
               WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                    --
                 AND soca.perd_oid_peri = pnoidperiodo -- oid del periodo activo
                 AND tsol.cod_tipo_soli = 'SOC' -- Tipo de solicitud OC
                 AND soca.ind_oc = 1 -- Indicador de OC = 1
                 AND cons.esso_oid_esta_soli <> 4 -- No anulados
                 AND soca.grpr_oid_grup_proc = 5 -- Facturados/Procesados
               GROUP BY soca.clie_oid_clie) camp,
             (SELECT a.clie_oid_clie,
                     c.des_abrv_tipo_via,
                     a.val_nomb_via,
                     a.num_ppal,
                     a.val_obse,
                     a.val_barr,
                     a.cod_unid_geog,
                     a.des_villa_pobl,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 IS NULL) AS nivel_1,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 = substr(a.cod_unid_geog, 7, 6)
                         AND orde_3 IS NULL) AS nivel_2,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 = substr(a.cod_unid_geog, 7, 6)
                         AND orde_3 = substr(a.cod_unid_geog, 13, 6)
                         AND orde_4 IS NULL) AS nivel_3
                FROM mae_clien_direc a,
                     mae_tipo_direc  b,
                     seg_tipo_via    c,
                     mae_clien       d,
                     zon_terri       t
               WHERE d.oid_clie = a.clie_oid_clie
                 AND a.ind_elim = 0
                 AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
                 AND c.oid_tipo_via = a.tivi_oid_tipo_via
                 AND a.ind_dire_ppal = 1
                 AND a.terr_oid_terr = t.oid_terr(+)) dire,
             seg_pais a,
             seg_moned d
       WHERE clda.clie_oid_clie = clie.oid_clie
         AND (camp.clie_oid_clie IS NOT NULL AND
             clda.esta_oid_esta_clie IN (7) OR
             clda.esta_oid_esta_clie IN (1, 2, 3, 4, 5, 6, 8))
         AND clie.oid_clie = camp.clie_oid_clie(+)
         AND clie.oid_clie = dire.clie_oid_clie(+)
         AND clie.oid_clie = cprc.clie_oid_clie(+)
         AND clie.oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.terr_oid_terr = terr.oid_terr
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
         AND a.cod_pais = pscodigopais
         AND d.oid_mone = a.mone_oid_mone
         AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
             nvl(cuad.perd_oid_peri_fin, pnoidperiodo)
         AND clda.ind_acti = 1;
  
    TYPE recordconsultora IS RECORD(
      cod_pais           soa_consu.cod_pais%TYPE,
      cod_peri           soa_consu.cod_peri%TYPE,
      cod_clie           soa_consu.cod_clie%TYPE,
      oid_clie           soa_consu.oid_clie%TYPE,
      nom_cons           soa_consu.nom_cons%TYPE,
      val_nom1           soa_consu.val_nom1%TYPE,
      val_nom2           soa_consu.val_nom2%TYPE,
      val_ape1           soa_consu.val_ape1%TYPE,
      val_ape2           soa_consu.val_ape2%TYPE,
      cod_regi           soa_consu.cod_regi%TYPE,
      cod_zona           soa_consu.cod_zona%TYPE,
      cod_secc           soa_consu.cod_secc%TYPE,
      cod_terr           soa_consu.cod_terr%TYPE,
      val_docu_iden      soa_consu.val_docu_iden%TYPE,
      val_dire           soa_consu.val_dire%TYPE,
      val_tipo_tele_fijo soa_consu.val_tipo_tele_fijo%TYPE,
      val_tipo_tele_movi soa_consu.val_tipo_tele_movi%TYPE,
      val_tele_fijo      soa_consu.val_tele_fijo%TYPE,
      val_tele_celu      soa_consu.val_tele_celu%TYPE,
      val_esta_consu     soa_consu.val_esta_consu%TYPE,
      cod_esta_clie      soa_consu.cod_esta_clie%TYPE,
      val_sald_pend      soa_consu.val_sald_pend%TYPE,
      val_auto_pase      soa_consu.val_auto_pase%TYPE,
      val_mont_pedi      soa_consu.val_mont_pedi%TYPE,
      ind_capa           soa_consu.ind_capa%TYPE,
      ind_cons_pedi      soa_consu.ind_cons_pedi%TYPE,
      val_simb_mone      soa_consu.val_simb_mone%TYPE,
      des_moned          soa_consu.des_moned%TYPE,
      ind_cons_brill     soa_consu.ind_cons_brill%TYPE);
  
    TYPE consutab IS TABLE OF recordconsultora;
    interfazrecord consutab;
  
    psfecultiactu soa_consu.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_consu;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
    --pnOidPeriodoInicial:= fin_pkg_gener.fin_fn_obtie_oid_perio( gen_fn_calcu_perio( psCodigoPeriodo, -3 ) );
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --ABRIR EL CURSOS Y RECORRERLO Y HACER LOS INSERT soa_consu
    -- REVISAR INT_PKG_DANA
  
    OPEN c_soa_consu;
    LOOP
      FETCH c_soa_consu BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_consu
            (cod_pais,
             cod_peri,
             cod_clie,
             oid_clie,
             nom_cons,
             val_nom1,
             val_nom2,
             val_ape1,
             val_ape2,
             cod_regi,
             cod_zona,
             cod_secc,
             cod_terr,
             val_docu_iden,
             val_dire,
             val_tipo_tele_fijo,
             val_tipo_tele_movi,
             val_tele_fijo,
             val_tele_celu,
             val_esta_consu,
             cod_esta_clie,
             val_sald_pend,
             val_auto_pase,
             val_mont_pedi,
             ind_capa,
             ind_cons_pedi,
             val_simb_mone,
             des_moned,
             fec_ulti_actu,
             fec_utc_carga,
             ind_cons_brill)
          VALUES
            (interfazrecord(x).cod_pais,
             interfazrecord(x).cod_peri,
             interfazrecord(x).cod_clie,
             interfazrecord(x).oid_clie,
             interfazrecord(x).nom_cons,
             interfazrecord(x).val_nom1,
             interfazrecord(x).val_nom2,
             interfazrecord(x).val_ape1,
             interfazrecord(x).val_ape2,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cod_secc,
             interfazrecord(x).cod_terr,
             interfazrecord(x).val_docu_iden,
             interfazrecord(x).val_dire,
             interfazrecord(x).val_tipo_tele_fijo,
             interfazrecord(x).val_tipo_tele_movi,
             interfazrecord(x).val_tele_fijo,
             interfazrecord(x).val_tele_celu,
             interfazrecord(x).val_esta_consu,
             interfazrecord(x).cod_esta_clie,
             interfazrecord(x).val_sald_pend,
             interfazrecord(x).val_auto_pase,
             interfazrecord(x).val_mont_pedi,
             interfazrecord(x).ind_capa,
             interfazrecord(x).ind_cons_pedi,
             interfazrecord(x).val_simb_mone,
             interfazrecord(x).des_moned,
             psfecultiactu,
             ldfecutccarga,
             interfazrecord(x).ind_cons_brill);
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONSU';
      
      END IF;
      EXIT WHEN c_soa_consu%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_consu;
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR soa_pr_consu: ' || ls_sqlerrm);
    
  END soa_pr_consu;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_VISIT_CONSU
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_visit IS
  
    pnoidperiodoinicial NUMBER(12);
    pscodigoperiodo     VARCHAR2(6);
    pscodigopais        VARCHAR2(3);
    pnoidperiodo        NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_visita
    (
      lscodigopais        VARCHAR2,
      lscodigoperiodo     VARCHAR2,
      lnoidperiodo        NUMBER,
      lnoidperiodoinicial NUMBER
    ) IS
      SELECT a.cod_pais,
             lscodigoperiodo cod_peri,
             zorg.cod_regi,
             zzon.cod_zona,
             zscc.cod_secc,
             terr.cod_terr,
             clie.cod_clie,
             clie.val_ape1,
             clie.val_ape2,
             clie.val_nom1,
             clie.val_nom2,
             (SELECT clid.num_docu_iden
                FROM mae_clien_ident clid
               WHERE clid.clie_oid_clie = clhe.clie_oid_clie
                 AND clid.val_iden_docu_prin = 1
                 AND rownum = 1) val_nume_docu_iden,
             TRIM(nvl(dire.val_nomb_via, ' ') || ' ' ||
                  nvl(dire.val_obse, ' ')) val_dire_prin,
             'TF' val_tipo_tele_fijo,
             'TM' val_tipo_tele_movi,
             (SELECT clco.val_text_comu
                FROM mae_clien_comun clco
               WHERE clco.clie_oid_clie = clhe.clie_oid_clie
                 AND clco.ticm_oid_tipo_comu = 1) val_nume_tele_fijo,
             (SELECT clco.val_text_comu
                FROM mae_clien_comun clco
               WHERE clco.clie_oid_clie = clhe.clie_oid_clie
                 AND clco.ticm_oid_tipo_comu = 6) val_nume_tele_celu,
             (SELECT clco.val_text_comu
                FROM mae_clien_comun clco
               WHERE clco.clie_oid_clie = clhe.clie_oid_clie
                 AND clco.ticm_oid_tipo_comu = 3) val_corr_elec,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = cprc.perd_oid_peri)) val_camp_ingr,
             CASE
               WHEN (gen_pkg_gener.gen_fn_clien_bloqu(clie.cod_clie, '01') = 1 OR
                    gen_pkg_gener.gen_fn_clien_bloqu(clie.cod_clie, '02') = 1) OR
                    clda.ind_acti = 0 THEN
                0
               ELSE
                1
             END val_auto_pase,
             CASE
               WHEN (clda.esta_oid_esta_clie = 1 AND
                    camp.clie_oid_clie IS NULL) THEN
                '1'
               WHEN cprc.perd_oid_peri = cier.perd_oid_peri OR
                    (clda.esta_oid_esta_clie IN (1, 2, 7, 8) AND
                    camp.clie_oid_clie IS NOT NULL) THEN
                '2'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -1)) THEN
                '3'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -2)) AND
                    clda.num_camp_sin_pedi = 0 THEN
                '4'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -3)) AND
                    clda.num_camp_sin_pedi = 0 THEN
                '5'
               WHEN (cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                             -3)) AND
                    clda.num_camp_sin_pedi >= 2 OR
                    cier.cod_peri <
                    (SELECT nvcp.cmp_inic_prog FROM nvs_param_logro nvcp)) THEN
                '0'
               ELSE
                '6'
             END cod_tipo_visi,
             CASE
               WHEN (clda.esta_oid_esta_clie = 1 AND
                    camp.clie_oid_clie IS NULL) THEN
                'Visita de incorporación'
               WHEN cprc.perd_oid_peri = cier.perd_oid_peri OR
                    (clda.esta_oid_esta_clie IN (1, 2, 7, 8) AND
                    camp.clie_oid_clie IS NOT NULL) THEN
                'visita de Refuerzo'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -1)) THEN
                'visita de acompañamiento 1'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -2)) AND
                    clda.num_camp_sin_pedi = 0 THEN
                'visita de acompañamiento 2'
               WHEN cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                            -3)) AND
                    clda.num_camp_sin_pedi = 0 THEN
                'visita felicitaciones'
               WHEN (cprc.perd_oid_peri =
                    fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(cier.cod_peri,
                                                                             -3)) AND
                    clda.num_camp_sin_pedi >= 2 OR
                    cier.cod_peri <
                    (SELECT nvcp.cmp_inic_prog FROM nvs_param_logro nvcp)) THEN
                'no le corresponde visita'
               ELSE
                'visita establecida'
             END val_tipo_visi,
             nvl(triu.flagasiste, 0) val_asis_reun_triu,
             nvl(clie.sal_deud_ante, 0) val_sald_pend,
             d.val_simb_mone,
             pq_apl_aux.valor_gen_i18n_sicc(1, d.oid_mone, 'SEG_MONED') des_moned
        FROM mae_clien_histo_estat clhe,
             mae_clien clie,
             mae_clien_datos_adici clda,
             mae_clien_prime_conta cprc,
             mae_clien_unida_admin cuad,
             zon_terri_admin ztad,
             zon_terri terr,
             zon_secci zscc,
             zon_zona zzon,
             zon_regio zorg,
             (SELECT soca.clie_oid_clie
                FROM ped_solic_cabec          soca,
                     ped_solic_cabec          cons,
                     ped_tipo_solic_pais      tspa,
                     own_comun.ped_tipo_solic tsol
               WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND soca.perd_oid_peri = lnoidperiodo
                 AND tsol.cod_tipo_soli = 'SOC'
                 AND soca.ind_oc = 1
                 AND cons.esso_oid_esta_soli <> 4
                 AND soca.grpr_oid_grup_proc = 5) camp,
             (SELECT a.clie_oid_clie,
                     c.des_abrv_tipo_via,
                     a.val_nomb_via,
                     a.num_ppal,
                     a.val_obse,
                     a.val_barr,
                     a.cod_unid_geog,
                     a.des_villa_pobl,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 IS NULL) AS nivel_1,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 = substr(a.cod_unid_geog, 7, 6)
                         AND orde_3 IS NULL) AS nivel_2,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = substr(a.cod_unid_geog, 1, 6)
                         AND orde_2 = substr(a.cod_unid_geog, 7, 6)
                         AND orde_3 = substr(a.cod_unid_geog, 13, 6)
                         AND orde_4 IS NULL) AS nivel_3
                FROM mae_clien_direc a,
                     mae_tipo_direc  b,
                     seg_tipo_via    c,
                     mae_clien       d,
                     zon_terri       t
               WHERE d.oid_clie = a.clie_oid_clie
                 AND a.ind_elim = 0
                 AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
                 AND c.oid_tipo_via = a.tivi_oid_tipo_via
                 AND a.ind_dire_ppal = 1
                 AND a.terr_oid_terr = t.oid_terr(+)) dire,
             (SELECT cod_clie,
                     MAX(CASE
                           WHEN upper(cod_acce) IN ('S', 'A') THEN
                            1
                           ELSE
                            0
                         END) flagasiste
                FROM int_solic_pedid_detal
               WHERE cod_peri = lscodigoperiodo
               GROUP BY cod_clie) triu,
             (SELECT zorg_oid_regi,
                     MAX(perd_oid_peri) perd_oid_peri,
                     fin_pkg_gener.fin_fn_obtie_codig_perio(MAX(perd_oid_peri)) cod_peri
                FROM fac_contr_cierr
               WHERE tcie_oid_tipo_cier = 1
                 AND perd_oid_peri <= lnoidperiodo
               GROUP BY zorg_oid_regi) cier,
             seg_pais a,
             seg_moned d
       WHERE clhe.clie_oid_clie = clie.oid_clie
         AND clie.oid_clie = clda.clie_oid_clie
         AND clie.oid_clie = camp.clie_oid_clie(+)
         AND clie.oid_clie = dire.clie_oid_clie(+)
         AND clie.cod_clie = triu.cod_clie(+)
         AND clie.oid_clie = cprc.clie_oid_clie(+)
         AND clie.oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.terr_oid_terr = terr.oid_terr
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
         AND zorg.oid_regi = cier.zorg_oid_regi(+)
         AND a.cod_pais = lscodigopais
         AND d.oid_mone = a.mone_oid_mone
         AND (clhe.perd_oid_peri BETWEEN lnoidperiodoinicial AND
             lnoidperiodo AND clhe.esta_oid_esta_clie IN (2, 8) OR
             clhe.perd_oid_peri_peri_fin IS NULL AND
             clhe.esta_oid_esta_clie IN (1, 7) AND
             camp.clie_oid_clie IS NOT NULL)
         AND lnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
             nvl(cuad.perd_oid_peri_fin, lnoidperiodo);
  
    TYPE recordvisita IS RECORD(
      cod_pais           soa_visit_consu.cod_pais%TYPE,
      cod_peri           soa_visit_consu.cod_peri%TYPE,
      cod_regi           soa_visit_consu.cod_regi%TYPE,
      cod_zona           soa_visit_consu.cod_zona%TYPE,
      cod_secc           soa_visit_consu.cod_secc%TYPE,
      cod_terr           soa_visit_consu.cod_terr%TYPE,
      cod_clie           soa_visit_consu.cod_clie%TYPE,
      val_ape1           soa_visit_consu.val_ape1%TYPE,
      val_ape2           soa_visit_consu.val_ape2%TYPE,
      val_nom1           soa_visit_consu.val_nom1%TYPE,
      val_nom2           soa_visit_consu.val_nom2%TYPE,
      val_nume_docu_iden soa_visit_consu.val_nume_docu_iden%TYPE,
      val_dire_prin      soa_visit_consu.val_dire_prin%TYPE,
      val_tipo_tele_fijo soa_visit_consu.val_tipo_tele_fijo%TYPE,
      val_tipo_tele_movi soa_visit_consu.val_tipo_tele_movi%TYPE,
      val_nume_tele_fijo soa_visit_consu.val_nume_tele_fijo%TYPE,
      val_nume_tele_celu soa_visit_consu.val_nume_tele_celu%TYPE,
      val_corr_elec      soa_visit_consu.val_corr_elec%TYPE,
      val_camp_ingr      soa_visit_consu.val_camp_ingr%TYPE,
      val_auto_pase      soa_visit_consu.val_auto_pase%TYPE,
      cod_tipo_visi      soa_visit_consu.cod_tipo_visi%TYPE,
      val_tipo_visi      soa_visit_consu.val_tipo_visi%TYPE,
      val_asis_reun_triu soa_visit_consu.val_asis_reun_triu%TYPE,
      val_sald_pend      soa_visit_consu.val_sald_pend%TYPE,
      val_simb_mone      soa_visit_consu.val_simb_mone%TYPE,
      des_moned          soa_visit_consu.des_moned%TYPE);
  
    TYPE visitatab IS TABLE OF recordvisita;
    interfazrecord visitatab;
  
    psfecultiactu soa_visit_consu.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_visit_consu;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    pnoidperiodo        := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
    pnoidperiodoinicial := fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(pscodigoperiodo,
                                                                                   -3));
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --ABRIR EL CURSOS Y RECORRERLO Y HACER LOS INSERT soa_visit_consu
    -- REVISAR INT_PKG_DANA
  
    OPEN c_soa_visita(pscodigopais,
                      pscodigoperiodo,
                      pnoidperiodo,
                      pnoidperiodoinicial);
    LOOP
      FETCH c_soa_visita BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_visit_consu
            (cod_pais,
             cod_peri,
             cod_regi,
             cod_zona,
             cod_secc,
             cod_terr,
             cod_clie,
             val_ape1,
             val_ape2,
             val_nom1,
             val_nom2,
             val_nume_docu_iden,
             val_dire_prin,
             val_tipo_tele_fijo,
             val_tipo_tele_movi,
             val_nume_tele_fijo,
             val_nume_tele_celu,
             val_corr_elec,
             val_camp_ingr,
             val_auto_pase,
             cod_tipo_visi,
             val_tipo_visi,
             val_asis_reun_triu,
             val_sald_pend,
             val_simb_mone,
             des_moned,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_pais,
             interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cod_secc,
             interfazrecord(x).cod_terr,
             interfazrecord(x).cod_clie,
             interfazrecord(x).val_ape1,
             interfazrecord(x).val_ape2,
             interfazrecord(x).val_nom1,
             interfazrecord(x).val_nom2,
             interfazrecord(x).val_nume_docu_iden,
             interfazrecord(x).val_dire_prin,
             interfazrecord(x).val_tipo_tele_fijo,
             interfazrecord(x).val_tipo_tele_movi,
             interfazrecord(x).val_nume_tele_fijo,
             interfazrecord(x).val_nume_tele_celu,
             interfazrecord(x).val_corr_elec,
             interfazrecord(x).val_camp_ingr,
             interfazrecord(x).val_auto_pase,
             interfazrecord(x).cod_tipo_visi,
             interfazrecord(x).val_tipo_visi,
             interfazrecord(x).val_asis_reun_triu,
             interfazrecord(x).val_sald_pend,
             interfazrecord(x).val_simb_mone,
             interfazrecord(x).des_moned,
             psfecultiactu,
             ldfecutccarga);
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_VISIT_CONSU';
      
      END IF;
      EXIT WHEN c_soa_visita%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_visita;
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR soa_pr_visit: ' || ls_sqlerrm);
    
  END soa_pr_visit;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_TIPO
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_tipo_concu IS
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT 1 cod_tipo_conc,
             'Constancia y Ventas' val_tipo_conc,
             CASE
               WHEN copa.ind_acti = 1 AND veco.vico_oid_vige_conc = 1 AND
                    veco.esc2_oid_esta_conc = 2 THEN
                1
               ELSE
                0
             END cod_vige,
             copa.oid_para_gral oid_conc,
             copa.num_conc cod_conc,
             copa.num_conc || ' - ' || copa.val_nomb val_nomb_conc,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_desd)) val_inic_camp,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_hast)) val_fina_camp,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_desd) oid_inic_peri,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_hast) oid_fina_peri,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM inc_param_gener_premi
                      WHERE num_rota = 99
                        AND copa_oid_para_gral = copa.oid_para_gral) != 0 THEN
                1
               ELSE
                0
             END ind_rota
        FROM inc_concu_param_gener copa,
             inc_obten_punto       obpu,
             inc_versi_concu       veco
       WHERE copa.oid_para_gral = obpu.copa_oid_para_gral(+)
         AND copa.oid_para_gral = veco.copa_oid_para_gral(+)
         AND obpu.ind_cons = 1
         AND copa.bcal_oid_base_calc <> 4
         AND copa.perd_oid_peri_hast > copa.perd_oid_peri_desd
         AND obpu.mens_oid_mens NOT IN
             (SELECT oid_mens FROM msg_mensa WHERE cod_mens LIKE 'INC9%')
      
      UNION ALL
      
      SELECT 2 cod_tipo_conc,
             'Regalo x pedido' val_tipo_conc,
             CASE
               WHEN copa.ind_acti = 1 AND veco.vico_oid_vige_conc = 1 AND
                    veco.esc2_oid_esta_conc = 2 THEN
                1
               ELSE
                0
             END cod_vige,
             copa.oid_para_gral oid_conc,
             copa.num_conc cod_conc,
             copa.num_conc || ' - ' || copa.val_nomb val_nomb_conc,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_desd)) val_inic_camp,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_hast)) val_fina_camp,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_desd) oid_inic_peri,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_hast) oid_fina_peri,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM inc_param_gener_premi
                      WHERE num_rota = 99
                        AND copa_oid_para_gral = copa.oid_para_gral) != 0 THEN
                1
               ELSE
                0
             END ind_rota
        FROM inc_concu_param_gener copa,
             inc_obten_punto       obpu,
             inc_versi_concu       veco
       WHERE copa.oid_para_gral = obpu.copa_oid_para_gral(+)
         AND copa.oid_para_gral = veco.copa_oid_para_gral(+)
         AND copa.bcal_oid_base_calc <> 4
         AND copa.perd_oid_peri_hast = copa.perd_oid_peri_desd
      
      UNION ALL
      
      SELECT 1 cod_tipo_conc,
             'Constancia y Ventas' val_tipo_conc,
             CASE
               WHEN copa.ind_acti = 1 AND veco.vico_oid_vige_conc = 1 AND
                    veco.esc2_oid_esta_conc = 2 THEN
                1
               ELSE
                0
             END cod_vige,
             copa.oid_para_gral oid_conc,
             copa.num_conc cod_conc,
             copa.num_conc || ' - ' || copa.val_nomb val_nomb_conc,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_desd)) val_inic_camp,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_hast)) val_fina_camp,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_desd) oid_inic_peri,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_hast) oid_fina_peri,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM inc_param_gener_premi
                      WHERE num_rota = 99
                        AND copa_oid_para_gral = copa.oid_para_gral) != 0 THEN
                1
               ELSE
                0
             END ind_rota
        FROM inc_concu_param_gener copa,
             inc_obten_punto       obpu,
             inc_versi_concu       veco
       WHERE copa.oid_para_gral = obpu.copa_oid_para_gral(+)
         AND copa.oid_para_gral = veco.copa_oid_para_gral(+)
         AND copa.bcal_oid_base_calc <> 4
         AND copa.ccon_oid_clas_conc <> 6
         AND copa.perd_oid_peri_hast > copa.perd_oid_peri_desd
         AND copa.num_conc NOT IN
             (SELECT a.num_conc
                FROM inc_concu_param_gener a,
                     inc_obten_punto       b
               WHERE a.oid_para_gral = b.copa_oid_para_gral
                 AND b.ind_cons = 1
                 AND a.bcal_oid_base_calc <> 4
                 AND a.perd_oid_peri_hast > a.perd_oid_peri_desd)
      
      UNION ALL
      
      SELECT 4 cod_tipo_conc,
             'Recomendacion' val_tipo_conc,
             CASE
               WHEN copa.ind_acti = 1 AND veco.vico_oid_vige_conc = 1 AND
                    veco.esc2_oid_esta_conc = 2 THEN
                1
               ELSE
                0
             END cod_vige,
             copa.oid_para_gral oid_conc,
             copa.num_conc cod_conc,
             copa.num_conc || ' - ' || copa.val_nomb val_nomb_conc,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_desd)) val_inic_camp,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_hast)) val_fina_camp,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_desd) oid_inic_peri,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_hast) oid_fina_peri,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM inc_param_gener_premi
                      WHERE num_rota = 99
                        AND copa_oid_para_gral = copa.oid_para_gral) != 0 THEN
                1
               ELSE
                0
             END ind_rota
        FROM inc_concu_param_gener copa,
             inc_obten_punto       obpu,
             inc_versi_concu       veco
       WHERE copa.oid_para_gral = obpu.copa_oid_para_gral(+)
         AND copa.oid_para_gral = veco.copa_oid_para_gral(+)
         AND copa.bcal_oid_base_calc = 4
         AND veco.copa_oid_para_gral IS NOT NULL
      
      UNION ALL
      
      SELECT 5 cod_tipo_conc,
             'Reconocimiento' val_tipo_conc,
             CASE
               WHEN copa.ind_acti = 1 AND veco.vico_oid_vige_conc = 1 AND
                    veco.esc2_oid_esta_conc = 2 THEN
                1
               ELSE
                0
             END cod_vige,
             copa.oid_para_gral oid_conc,
             copa.num_conc cod_conc,
             copa.num_conc || ' - ' || copa.val_nomb val_nomb_conc,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_desd)) val_inic_camp,
             (SELECT peri.cod_peri
                FROM seg_perio_corpo peri
               WHERE peri.oid_peri =
                     (SELECT perd.peri_oid_peri
                        FROM cra_perio perd
                       WHERE perd.oid_peri = copa.perd_oid_peri_hast)) val_fina_camp,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_desd) oid_inic_peri,
             (SELECT perd.oid_peri
                FROM cra_perio perd
               WHERE perd.oid_peri = copa.perd_oid_peri_hast) oid_fina_peri,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM inc_param_gener_premi
                      WHERE num_rota = 99
                        AND copa_oid_para_gral = copa.oid_para_gral) != 0 THEN
                1
               ELSE
                0
             END ind_rota
        FROM inc_concu_param_gener copa,
             inc_obten_punto       obpu,
             inc_versi_concu       veco
       WHERE copa.oid_para_gral = obpu.copa_oid_para_gral(+)
         AND copa.oid_para_gral = veco.copa_oid_para_gral(+)
         AND copa.ccon_oid_clas_conc = 6
      UNION ALL
      SELECT 6 cod_tipo_conc,
             'PROGRAMA DE NUEVAS' val_tipo_conc,
             CASE
               WHEN ((SELECT cod_peri
                        FROM bas_ctrl_fact
                       WHERE sta_camp = '0'
                         AND ind_camp_act = '1') BETWEEN prog.cam_inic AND
                    prog.cam_fin) THEN
                1
               ELSE
                0
             END cod_vige,
             0 oid_conc,
             prog.cod_prog cod_conc,
             'PROGRAMA DE NUEVAS - ' || prog.cod_pais || prog.cod_prog val_nomb_conc,
             prog.cam_inic campanna_desde,
             prog.cam_fin campanna_hasta,
             fin_pkg_gener.fin_fn_obtie_oid_perio(prog.cam_inic) oid_inic_peri,
             fin_pkg_gener.fin_fn_obtie_oid_perio(prog.cam_fin) oid_fina_peri,
             0 ind_rotativo
        FROM cup_prog_nueva_cupon prog;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    ncamp     NUMBER;
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_concu_tipo.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_concu_tipo;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    BEGIN
      SELECT nvl(val_para, 1)
        INTO ncamp
        FROM bas_param_pais
       WHERE cod_pais = pscodigopais
         AND cod_sist = 'SOA'
         AND nom_para = 'periodosConcursoHistoria';
    EXCEPTION
      WHEN OTHERS THEN
        ncamp := 1;
    END;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual e historia
    lsperiodo := gen_fn_calcu_perio(pscodigoperiodo, (ncamp - 1) * -1);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          --Inserta si es activo
          IF (interfazrecord(x).cod_vige = '1') THEN
          
            INSERT INTO soa_concu_tipo
              (cod_tipo_conc,
               val_tipo_conc,
               cod_vige,
               oid_conc,
               cod_conc,
               val_nomb_conc,
               val_inic_camp,
               val_fina_camp,
               oid_inic_peri,
               oid_fina_peri,
               ind_rota,
               fec_ulti_actu,
               fec_utc_carga)
            VALUES
              (interfazrecord(x).cod_tipo_conc,
               interfazrecord(x).val_tipo_conc,
               interfazrecord(x).cod_vige,
               interfazrecord(x).oid_conc,
               interfazrecord(x).cod_conc,
               interfazrecord(x).val_nomb_conc,
               interfazrecord(x).val_inic_camp,
               interfazrecord(x).val_fina_camp,
               interfazrecord(x).oid_inic_peri,
               interfazrecord(x).oid_fina_peri,
               interfazrecord(x).ind_rota,
               psfecultiactu,
               ldfecutccarga);
          ELSE
          
            IF (interfazrecord(x).val_fina_camp >= lsperiodo AND interfazrecord(x)
               .val_fina_camp <= pscodigoperiodo) THEN
            
              INSERT INTO soa_concu_tipo
                (cod_tipo_conc,
                 val_tipo_conc,
                 cod_vige,
                 oid_conc,
                 cod_conc,
                 val_nomb_conc,
                 val_inic_camp,
                 val_fina_camp,
                 oid_inic_peri,
                 oid_fina_peri,
                 ind_rota,
                 fec_ulti_actu,
                 fec_utc_carga)
              VALUES
                (interfazrecord(x).cod_tipo_conc,
                 interfazrecord(x).val_tipo_conc,
                 interfazrecord(x).cod_vige,
                 interfazrecord(x).oid_conc,
                 interfazrecord(x).cod_conc,
                 interfazrecord(x).val_nomb_conc,
                 interfazrecord(x).val_inic_camp,
                 interfazrecord(x).val_fina_camp,
                 interfazrecord(x).oid_inic_peri,
                 interfazrecord(x).oid_fina_peri,
                 interfazrecord(x).ind_rota,
                 psfecultiactu,
                 ldfecutccarga);
            
            END IF;
          
          END IF;
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONCU_TIPO';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR soa_pr_tipo_concu: ' || ls_sqlerrm);
    
  END soa_pr_tipo_concu;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_REGAL_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_concu_regal IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT x.cod_tipo_conc,
             x.val_tipo_conc,
             x.cod_vige,
             x.oid_conc,
             x.cod_conc,
             x.val_nomb_conc,
             x.val_inic_camp,
             x.val_fina_camp,
             x.oid_inic_peri,
             x.oid_fina_peri,
             x.ind_rota
        FROM soa_concu_tipo x
       WHERE x.cod_tipo_conc = 2;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    psfecultiactu soa_concu_tipo.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_concu_regal_pedid;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          INSERT INTO soa_concu_regal_pedid
            SELECT zorg.cod_regi,
                   zzon.cod_zona,
                   zscc.cod_secc,
                   clie.cod_clie,
                   clie.val_nom1,
                   clie.val_nom2,
                   clie.val_ape1,
                   clie.val_ape2,
                   punt.oid_para_gral oid_conc,
                   punt.puntaje val_punt_acum,
                   CASE
                     WHEN (SELECT COUNT(1)
                             FROM ped_solic_cabec_acum2 sca2
                            WHERE sca2.clie_oid_clie = clie.oid_clie
                              AND sca2.perd_oid_peri = pnoidperiodo) <> 0 THEN
                      1
                     ELSE
                      0
                   END ind_paso_pedi,
                   (SELECT panp.num_nive
                      FROM inc_param_nivel_premi panp,
                           inc_param_gener_premi pagp
                     WHERE panp.pagp_oid_para_gene_prem =
                           pagp.oid_para_gene_prem
                       AND pagp.copa_oid_para_gral = punt.oid_para_gral
                       AND punt.puntaje BETWEEN
                           nvl(panp.num_cant_inic_punt, 0) AND
                           nvl(panp.num_cant_fina_punt, 0)) val_nive_actu,
                   0 val_nume_rota,
                   CASE
                     WHEN (SELECT COUNT(1) ind_gana
                             FROM inc_ganad             gana,
                                  inc_param_nivel_premi panp,
                                  inc_param_gener_premi pagp
                            WHERE gana.panp_oid_para_nive_prem = panp.oid_para_nive_prem
                              AND panp.pagp_oid_para_gene_prem = pagp.oid_para_gene_prem
                              AND pagp.copa_oid_para_gral = interfazrecord(x).oid_conc
                              AND gana.clie_oid_clie = clie.oid_clie) <> 0 THEN
                      1
                     ELSE
                      0
                   END ind_gana,
                   psfecultiactu fec_ulti_actu,
                   ldfecutccarga fec_utc_carga
              FROM mae_clien clie,
                   mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_regio zorg,
                   zon_zona zzon,
                   (SELECT cucp.clie_oid_clie,
                           copa.oid_para_gral,
                           copa.num_conc,
                           copa.val_obse,
                           SUM(cucp.num_punt) puntaje
                      FROM inc_concu_param_gener copa,
                           mae_clien             clie,
                           inc_cuent_corri_punto cucp
                     WHERE copa.oid_para_gral = cucp.copa_oid_para_gral
                       AND cucp.clie_oid_clie = clie.oid_clie
                       AND copa.oid_para_gral = interfazrecord(x).oid_conc
                       AND upper(cucp.val_desc) NOT LIKE
                           '%ENTREGA DE PREMIO%'
                     GROUP BY cucp.clie_oid_clie,
                              copa.oid_para_gral,
                              copa.num_conc,
                              copa.val_obse) punt
             WHERE clie.oid_clie = cuad.clie_oid_clie
               AND clie.oid_clie = punt.clie_oid_clie
               AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
                   nvl(cuad.perd_oid_peri_fin, pnoidperiodo);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONCU_REGAL_PEDID';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR soa_pr_concu_regal: ' || ls_sqlerrm);
  END soa_pr_concu_regal;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_CONST_VENTA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_concu_venta IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT x.cod_tipo_conc,
             x.val_tipo_conc,
             x.cod_vige,
             x.oid_conc,
             x.cod_conc,
             x.val_nomb_conc,
             x.val_inic_camp,
             x.val_fina_camp,
             x.oid_inic_peri,
             x.oid_fina_peri,
             x.ind_rota
        FROM soa_concu_tipo x
       WHERE x.cod_tipo_conc = 1;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    psfecultiactu soa_concu_const_venta.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_concu_const_venta;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          INSERT INTO soa_concu_const_venta
            SELECT zorg.cod_regi,
                   zzon.cod_zona,
                   zscc.cod_secc,
                   clie.oid_clie,
                   clie.cod_clie,
                   clie.val_nom1,
                   clie.val_nom2,
                   clie.val_ape1,
                   clie.val_ape2,
                   punt.copa_oid_para_gral oid_conc,
                   punt.val_punt val_punt_ulti_camp, -- modificado
                   punt.val_punt_adic, -- modificado
                   punt.val_punt_acum,
                   0 val_punt_camp_actu,
                   (SELECT CASE
                             WHEN MAX(panp.num_cant_fija_punt) IS NULL THEN
                              MAX(panp.num_nive)
                             ELSE
                              MIN(panp.num_nive)
                           END num_nive
                      FROM inc_param_nivel_premi panp,
                           inc_param_gener_premi pagp
                     WHERE panp.pagp_oid_para_gene_prem =
                           pagp.oid_para_gene_prem
                       AND pagp.copa_oid_para_gral = punt.copa_oid_para_gral
                       AND ((panp.num_cant_fija_punt IS NULL AND
                           punt.val_punt_acum BETWEEN
                           nvl(panp.num_cant_inic_punt, 0) AND
                           nvl(panp.num_cant_fina_punt, 0)) OR
                           (panp.num_cant_fija_punt IS NOT NULL AND
                           panp.num_cant_fija_punt <= punt.val_punt_acum))
                     GROUP BY pagp.copa_oid_para_gral) val_nive_actu,
                   0 val_nume_rota, -- No existe un procedimiento actual para obtener este valor
                   (SELECT SUM(sopo.num_unid_aten)
                      FROM ped_solic_cabec       soca,
                           ped_solic_posic       sopo,
                           inc_concu_param_gener copa
                     WHERE soca.oid_soli_cabe = sopo.soca_oid_soli_cabe(+)
                       AND soca.copa_oid_para_gene = copa.oid_para_gral
                       AND copa.oid_para_gral = interfazrecord(x).oid_conc
                       AND soca.clie_oid_clie = clie.oid_clie) val_cant_prem,
                   CASE
                     WHEN (SELECT COUNT(1) ind_gana
                             FROM inc_ganad             gana,
                                  inc_param_nivel_premi panp,
                                  inc_param_gener_premi pagp
                            WHERE gana.panp_oid_para_nive_prem = panp.oid_para_nive_prem
                              AND panp.pagp_oid_para_gene_prem = pagp.oid_para_gene_prem
                              AND pagp.copa_oid_para_gral = interfazrecord(x).oid_conc
                              AND gana.clie_oid_clie = clie.oid_clie) <> 0 THEN
                      1
                     ELSE
                      0
                   END ind_gana,
                   psfecultiactu fec_ulti_actu,
                   ldfecutccarga fec_utc_carga
              FROM mae_clien clie,
                   mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_regio zorg,
                   zon_zona zzon,
                   (SELECT cucp.clie_oid_clie,
                           copa.num_conc,
                           cucp.copa_oid_para_gral,
                           SUM(CASE
                                 WHEN (upper(cucp.val_desc) LIKE
                                      'ABONO PUNTAJE DEL PERIODO GRUPO TODAS VENTA DIRECTA%' OR
                                      tmov_oid_tipo_movi = 2) THEN
                                  cucp.num_punt
                                 ELSE
                                  0
                               END) val_punt,
                           SUM(CASE
                                 WHEN NOT (upper(cucp.val_desc) LIKE
                                       'ABONO PUNTAJE DEL PERIODO GRUPO TODAS VENTA DIRECTA%' OR
                                       tmov_oid_tipo_movi = 2) THEN
                                  cucp.num_punt
                                 ELSE
                                  0
                               END) val_punt_adic,
                           SUM(cucp.num_punt) val_punt_acum
                      FROM inc_concu_param_gener copa,
                           inc_cuent_corri_punto cucp
                     WHERE copa.oid_para_gral = cucp.copa_oid_para_gral
                       AND copa.oid_para_gral = interfazrecord(x).oid_conc
                       AND upper(cucp.val_desc) NOT LIKE
                           '%ENTREGA DE PREMIO%'
                    -- AND pnOidPeriodo BETWEEN copa.perd_oid_peri_desd AND copa.perd_oid_peri_hast
                     GROUP BY cucp.clie_oid_clie,
                              copa.num_conc,
                              cucp.copa_oid_para_gral) punt -- modificado
             WHERE clie.oid_clie = cuad.clie_oid_clie
               AND clie.oid_clie = punt.clie_oid_clie
               AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
                   nvl(cuad.perd_oid_peri_fin, pnoidperiodo);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONCU_CONST_VENTA';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR soa_pr_concu_venta: ' || ls_sqlerrm);
  END soa_pr_concu_venta;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_PROGR_NUEVA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_prg_nueva IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT x.cod_tipo_conc,
             x.val_tipo_conc,
             x.cod_vige,
             x.oid_conc,
             x.cod_conc,
             x.val_nomb_conc,
             x.val_inic_camp,
             x.val_fina_camp,
             x.oid_inic_peri,
             x.oid_fina_peri,
             x.ind_rota
        FROM soa_concu_tipo x
       WHERE x.cod_tipo_conc = 6;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    psfecultiactu soa_concu_tipo.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_progr_nueva;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          INSERT INTO soa_progr_nueva
          
            SELECT nuev.cod_prog,
                   zorg.cod_regi,
                   zzon.cod_zona,
                   zscc.cod_secc,
                   nuev.cod_clie,
                   nuev.val_ape1,
                   nuev.val_ape2,
                   nuev.val_nom1,
                   (SELECT peri.cod_peri
                      FROM seg_perio_corpo peri
                     WHERE peri.oid_peri =
                           (SELECT perd.peri_oid_peri
                              FROM cra_perio perd
                             WHERE perd.oid_peri = cprc.perd_oid_peri)) val_camp_ingr,
                   decode(nuev.ind_entr_kit, 1, 'SI', 'NO') ind_entr_kit,
                   decode(nuev.ind_cons, 1, 'SI', 'NO') ind_cons,
                   psfecultiactu fec_ulti_actu,
                   ldfecutccarga fec_utc_carga
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_regio zorg,
                   zon_zona zzon,
                   mae_clien_prime_conta cprc,
                   (SELECT prog.cod_prog,
                           clie.oid_clie,
                           clie.cod_clie,
                           clie.val_ape1,
                           clie.val_ape2,
                           clie.val_nom1,
                           nvl(fact.ind_cons, 0) ind_cons,
                           1 ind_entr_kit
                      FROM cup_consu_nueva      cons,
                           cup_prog_nueva_cupon prog,
                           cup_consu_factu      fact,
                           mae_clien            clie
                     WHERE cons.cod_pais = prog.cod_pais
                       AND cons.cod_prog = prog.cod_prog
                       AND cons.cod_cons = clie.cod_clie
                       AND cons.cod_pais = fact.cod_pais
                       AND cons.cod_prog = fact.cod_prog
                       AND cons.cod_cons = fact.cod_cons
                       AND prog.cod_prog = interfazrecord(x).cod_conc) nuev
             WHERE nuev.oid_clie = cuad.clie_oid_clie
               AND nuev.oid_clie = cprc.clie_oid_clie(+)
               AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
                   nvl(cuad.perd_oid_peri_fin, pnoidperiodo);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_PROGR_NUEVA';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR soa_pr_prg_nueva: ' || ls_sqlerrm);
  END soa_pr_prg_nueva;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_DETAL_RECOM
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_recom IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT x.cod_tipo_conc,
             x.val_tipo_conc,
             x.cod_vige,
             x.oid_conc,
             x.cod_conc,
             x.val_nomb_conc,
             x.val_inic_camp,
             x.val_fina_camp,
             x.oid_inic_peri,
             x.oid_fina_peri,
             x.ind_rota
        FROM soa_concu_tipo x
       WHERE x.cod_tipo_conc = 4;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    psfecultiactu soa_concu_tipo.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_concu_detal_recom;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          INSERT INTO soa_concu_detal_recom
            SELECT zorg.cod_regi,
                   zzon.cod_zona,
                   zscc.cod_secc,
                   (SELECT cod_clie
                      FROM mae_clien
                     WHERE oid_clie = base.clie_oid_clie_rete) cod_clie_rete,
                   (SELECT cod_clie
                      FROM mae_clien
                     WHERE oid_clie = base.clie_oid_clie_redo) cod_clie_redo,
                   base.num_conc,
                   base.oid_para_gral oid_conc,
                   (SELECT peri.cod_peri
                      FROM seg_perio_corpo peri
                     WHERE peri.oid_peri =
                           (SELECT perd.peri_oid_peri
                              FROM cra_perio perd
                             WHERE perd.oid_peri = base.perd_oid_peri)) val_camp_ingr_redo,
                   --DECODE( base.ind_efec, 1, 'SI', 'NO' ) ind_efectiva,
                   base.ind_efec,
                   base.val_nive_actu,
                   (SELECT peri.cod_peri
                      FROM seg_perio_corpo peri
                     WHERE peri.oid_peri =
                           (SELECT perd.peri_oid_peri
                              FROM cra_perio perd
                             WHERE perd.oid_peri = cprc.perd_oid_peri)) val_camp_ingr_rete,
                   CASE
                     WHEN (SELECT COUNT(1) ind_gana
                             FROM inc_ganad             gana,
                                  inc_param_nivel_premi panp,
                                  inc_param_gener_premi pagp,
                                  mae_clien             clie
                            WHERE gana.panp_oid_para_nive_prem = panp.oid_para_nive_prem
                              AND panp.pagp_oid_para_gene_prem = pagp.oid_para_gene_prem
                              AND pagp.copa_oid_para_gral = interfazrecord(x).oid_conc
                              AND gana.clie_oid_clie = clie.oid_clie) <> 0 THEN
                      1
                     ELSE
                      0
                   END ind_gana,
                   psfecultiactu fec_ulti_actu,
                   ldfecutccarga fec_utc_carga
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_regio zorg,
                   zon_zona zzon,
                   mae_clien_prime_conta cprc,
                   (SELECT rete.clie_oid_clie clie_oid_clie_rete,
                           redo.clie_oid_clie clie_oid_clie_redo,
                           copa.oid_para_gral,
                           copa.num_conc,
                           redo.perd_oid_peri,
                           nvl(redo.ind_efec, 0) ind_efec,
                           (SELECT panp.num_nive
                              FROM inc_param_nivel_premi panp,
                                   inc_param_gener_premi pagp
                             WHERE panp.pagp_oid_para_gene_prem =
                                   pagp.oid_para_gene_prem
                               AND pagp.copa_oid_para_gral =
                                   punt.copa_oid_para_gral
                               AND ((pagp.tprm_oid_tipo_pion = 1 AND
                                   punt.val_punt_acum =
                                   nvl(panp.num_cant_fija_punt, 0)) OR
                                   (pagp.tprm_oid_tipo_pion = 2 AND
                                   punt.val_punt_acum BETWEEN
                                   nvl(panp.num_cant_inic_punt, 0) AND
                                   nvl(panp.num_cant_fina_punt, 0)))) val_nive_actu
                      FROM inc_clien_recte rete,
                           inc_clien_recdo redo,
                           inc_concu_param_gener copa,
                           (SELECT cucp.clie_oid_clie,
                                   cucp.copa_oid_para_gral,
                                   SUM(CASE
                                         WHEN cucp.perd_oid_peri < pnoidperiodo THEN
                                          cucp.num_punt
                                         ELSE
                                          0
                                       END) val_punt_acum,
                                   SUM(CASE
                                         WHEN cucp.perd_oid_peri = pnoidperiodo THEN
                                          cucp.num_punt
                                         ELSE
                                          0
                                       END) val_punt_camp_actu
                              FROM inc_cuent_corri_punto cucp
                             WHERE cucp.copa_oid_para_gral = interfazrecord(x)
                                  .oid_conc
                               AND upper(cucp.val_desc) !=
                                   'ENTREGA DE PREMIO'
                             GROUP BY cucp.clie_oid_clie,
                                      cucp.copa_oid_para_gral) punt
                     WHERE rete.oid_clie_rete = redo.clr3_oid_clie_rete
                       AND rete.copa_oid_para_gral = copa.oid_para_gral
                       AND rete.clie_oid_clie = punt.clie_oid_clie(+)
                       AND copa.oid_para_gral = punt.copa_oid_para_gral) base
             WHERE base.clie_oid_clie_rete = cuad.clie_oid_clie
               AND base.clie_oid_clie_rete = cprc.clie_oid_clie(+)
               AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
                   nvl(cuad.perd_oid_peri_fin, pnoidperiodo);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONCU_DETAL_RECOM';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR soa_pr_recom: ' || ls_sqlerrm);
    
  END soa_pr_recom;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_CONCU_RECO
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_recon IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
    pnoidperiodo    NUMBER(12);
  
    ls_sqlerrm VARCHAR2(150);
  
    CURSOR c_soa_concu_tipo IS
    
      SELECT x.cod_tipo_conc,
             x.val_tipo_conc,
             x.cod_vige,
             x.oid_conc,
             x.cod_conc,
             x.val_nomb_conc,
             x.val_inic_camp,
             x.val_fina_camp,
             x.oid_inic_peri,
             x.oid_fina_peri,
             x.ind_rota
        FROM soa_concu_tipo x
       WHERE x.cod_tipo_conc = 5;
  
    TYPE recordconcursotipo IS RECORD(
      cod_tipo_conc soa_concu_tipo.cod_tipo_conc%TYPE,
      val_tipo_conc soa_concu_tipo.val_tipo_conc%TYPE,
      cod_vige      soa_concu_tipo.cod_vige%TYPE,
      oid_conc      soa_concu_tipo.oid_conc%TYPE,
      cod_conc      soa_concu_tipo.cod_conc%TYPE,
      val_nomb_conc soa_concu_tipo.val_nomb_conc%TYPE,
      val_inic_camp soa_concu_tipo.val_inic_camp%TYPE,
      val_fina_camp soa_concu_tipo.val_fina_camp%TYPE,
      oid_inic_peri soa_concu_tipo.oid_inic_peri%TYPE,
      oid_fina_peri soa_concu_tipo.oid_fina_peri%TYPE,
      ind_rota      soa_concu_tipo.ind_rota%TYPE);
  
    TYPE concursotipotab IS TABLE OF recordconcursotipo;
    interfazrecord concursotipotab;
  
    psfecultiactu soa_concu_tipo.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_concu_reco;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    pnoidperiodo := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    LOOP
    
      OPEN c_soa_concu_tipo;
    
      FETCH c_soa_concu_tipo BULK COLLECT
        INTO interfazrecord LIMIT 1000;
    
      IF interfazrecord.count > 0 THEN
      
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          INSERT INTO soa_concu_reco
            SELECT zorg.cod_regi,
                   zzon.cod_zona,
                   zscc.cod_secc,
                   reco.copa_oid_para_gral oid_conc,
                   clie.cod_clie,
                   clie.val_ape1,
                   clie.val_ape2,
                   clie.val_nom1,
                   reco.val_punt_acum,
                   psfecultiactu           fec_ulti_actu,
                   ldfecutccarga           fec_utc_carga
              FROM mae_clien_unida_admin cuad,
                   mae_clien clie,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_regio zorg,
                   zon_zona zzon,
                   (SELECT cucp.clie_oid_clie,
                           cucp.copa_oid_para_gral,
                           SUM(CASE
                                 WHEN cucp.perd_oid_peri < pnoidperiodo THEN
                                  cucp.num_punt
                                 ELSE
                                  0
                               END) val_punt_acum,
                           SUM(CASE
                                 WHEN cucp.perd_oid_peri = pnoidperiodo THEN
                                  cucp.num_punt
                                 ELSE
                                  0
                               END) val_punt_camp_actu
                      FROM inc_cuent_corri_punto cucp
                     WHERE cucp.copa_oid_para_gral = interfazrecord(x)
                          .oid_conc
                       AND upper(cucp.val_desc) != 'ENTREGA DE PREMIO'
                     GROUP BY cucp.clie_oid_clie,
                              cucp.copa_oid_para_gral) reco
             WHERE reco.clie_oid_clie = cuad.clie_oid_clie
               AND reco.clie_oid_clie = clie.oid_clie
               AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND pnoidperiodo BETWEEN cuad.perd_oid_peri_ini AND
                   nvl(cuad.perd_oid_peri_fin, pnoidperiodo);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_CONCU_RECO';
      
      END IF;
      EXIT WHEN c_soa_concu_tipo%NOTFOUND;
    END LOOP;
  
    CLOSE c_soa_concu_tipo;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR soa_pr_recon: ' || ls_sqlerrm);
  END soa_pr_recon;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INFOR_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_pedid IS
  
    lscodigoperiodoactual seg_perio_corpo.cod_peri%TYPE;
    lscodigopais          seg_pais.cod_pais%TYPE;
  
    TYPE soa_tab_t IS TABLE OF soa_infor_pedid%ROWTYPE INDEX BY BINARY_INTEGER;
    soa_tab soa_tab_t; -- In-memory table
  
    TYPE soa_rech_tab_t IS TABLE OF soa_pedid_recha%ROWTYPE INDEX BY BINARY_INTEGER;
    soa_rech_tab soa_rech_tab_t; -- In-memory table
  
    CURSOR c_soa_pedidos_rechazados(ldfecultiactu DATE) IS
      SELECT a.sec_nume_docu,
             c.cod_vali,
             a.cod_peri,
             a.docu_num_docu,
             a.cod_clie,
             m.des_cort_mens tip_rech,
             c.des_vali,
             a.cod_zona,
             a.cod_zona_arri,
             NULL            fec_ulti_actu,
             NULL            fec_utc_carg
        FROM int_solic_conso_cabec a,
             sto_param_valid       c,
             sto_detal_docum_excep e,
             sto_mensa_valid       m
       WHERE e.sec_nume_docu = a.sec_nume_docu
         AND a.ind_erro_rech = '1'
         AND e.cod_vali = c.cod_vali
         AND m.cod_vali = e.cod_vali
         AND e.cod_mens = m.cod_mens
         AND a.fec_modi >= ldfecultiactu
      UNION
      SELECT a.sec_nume_docu,
             c.cod_vali,
             a.cod_peri,
             a.docu_num_docu,
             a.cod_clie,
             m.des_cort_mens tip_rech,
             c.des_vali,
             a.cod_zona,
             a.cod_zona_arri,
             NULL            fec_ulti_actu,
             NULL            fec_utc_carg
        FROM ped_histo_solic_conso_cabec a,
             sto_param_valid             c,
             sto_histo_detal_docum_excep e,
             sto_mensa_valid             m
       WHERE e.sec_nume_docu = a.sec_nume_docu
         AND a.ind_erro_rech = '1'
         AND e.cod_vali = c.cod_vali
         AND m.cod_vali = e.cod_vali
         AND e.cod_mens = m.cod_mens
         AND a.fec_modi >= ldfecultiactu;
  
    CURSOR c_soa_pedidos_actual(ldfecultiactu DATE) IS
      SELECT cab.cod_pais,
             cab.fec_soli,
             cab.cod_peri,
             cab.sec_nume_docu val_nume_soli,
             cab.soca_oid_soli_cabe_refe,
             cab.num_lote,
             cab.ind_proc_gp2,
             cab.fec_prog_fact fec_fact,
             soa_pkg_proce.soa_fn_orige_pedid(cab.ind_rece_onli,
                                              cab.ind_rece_ivr,
                                              cab.ind_rece_ocr,
                                              cab.ind_rece_web,
                                              cab.ind_rece_dd,
                                              cab.ind_rece_digi,
                                              cab.ind_rece_cc,
                                              cab.ind_rece_mens) val_orig,
             ltrim(cab.cod_clie, 'C') cod_clie,
             nvl(m.val_nom1, '-') val_nom1,
             nvl(m.val_nom2, '-') val_nom2,
             nvl(m.val_ape1, '-') val_ape1,
             nvl(m.val_ape2, '-') val_ape2,
             nvl(m.cod_regi, cab.cod_regi_arri) cod_regi,
             nvl(m.cod_zona, cab.cod_zona_arri) cod_zona,
             nvl(m.val_nom1 || ' ' || m.val_nom2 || ' ' || m.val_ape1 || ' ' ||
                 m.val_ape2,
                 '-') nom_clie,
             nvl(m.cod_secc, '-') cod_secc,
             nvl(m.cod_terr, 0) cod_terr,
             nvl(m.sal_deud_ante, 0) sal_deud_ante,
             NULL nom_mone,
             NULL smb_mone,
             0 val_mont_esti,
             decode(cab.tip_orde, 'P', 'SI', 'NO') ind_prim_pedi,
             (SELECT COUNT(1)
                FROM int_solic_conso_detal x,
                     sto_detal_docum_excep y
               WHERE x.sec_nume_docu = y.sec_nume_docu
                 AND x.cod_clie = cab.cod_clie
                 AND x.cod_peri = cab.cod_peri
                 AND y.cod_vali = 'OCD-2') num_cuvs_erra,
             (SELECT conso.val_tota_paga_loca monto_pedido
                FROM ped_solic_cabec conso
               WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe) val_mont_fact,
             (SELECT SUM(d.val_unid_dem * CASE
                           WHEN l.coes_oid_estr IN (2002, 2006) THEN
                            (SELECT SUM(imp_prec_cata)
                               FROM pre_ofert_detal
                              WHERE ofer_oid_ofer = l.oid_ofer)
                           ELSE
                            m.imp_prec_cata
                         END) val_mont_pedi
                FROM int_solic_conso_cabec c,
                     int_solic_conso_detal d,
                     pre_ofert_detal       m,
                     pre_matri_factu_cabec i,
                     pre_ofert             l
               WHERE c.cod_peri = cab.cod_peri
                 AND d.cod_peri = c.cod_peri
                 AND c.cod_clie = cab.cod_clie
                 AND d.cod_clie = c.cod_clie
                 AND m.val_codi_vent = d.cod_vent
                 AND l.oid_ofer = m.ofer_oid_ofer
                 AND i.oid_cabe = l.mfca_oid_cabe
                 AND i.perd_oid_peri = cab.perd_oid_peri) val_mont_soli,
             NULL val_esta_pedi,
             cab.ind_erro_rech,
             soa_pkg_proce.soa_fn_motiv_recha_pedid(cab.sec_nume_docu, 0),
             NULL mot_rech,
             (SELECT cod_esta_clie
                FROM mae_estat_clien h
               WHERE h.oid_esta_clie = m.esta_oid_esta_clie) cod_esta_clie,
             pq_apl_aux.valor_gen_i18n_sicc(1,
                                            m.esta_oid_esta_clie,
                                            'MAE_ESTAT_CLIEN') val_esta_cons,
             (SELECT conso.val_prec_neto_tota_loca
                FROM ped_solic_cabec conso
               WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe) val_mont_sin_dsto,
             (SELECT conso.val_impo_desc_tota_loca
                FROM ped_solic_cabec conso
               WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe) val_mont_dsto,
             (SELECT conso.val_impo_flet_tota_loca
                FROM ped_solic_cabec conso
               WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe) val_mont_flet,
             (SELECT conso.val_impo_impu_tota_loca
                FROM ped_solic_cabec conso
               WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe) val_mont_impu,
             cab.val_mont_pedi val_mont_rech,
             0 num_falt_anun,
             nvl(m.des_zona, '-') des_zona,
             nvl((SELECT SUM(num_unid_dema)
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 1),
                 0) val_unid_lbel,
             nvl((SELECT SUM(num_unid_dema)
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 3),
                 0) val_unid_cyzo,
             nvl((SELECT SUM(num_unid_dema)
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 2),
                 0) val_unid_esik,
             nvl((SELECT SUM(num_unid_dema)
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod NOT IN (1, 2, 3)),
                 0) val_unid_marc,
             nvl((SELECT SUM(decode(val_prec_cata_unit_loca * num_unid_dema,
                                   0,
                                   0,
                                   val_prec_cata_unit_loca * num_unid_dema))
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 1
                    AND z.num_unid_dema > 0),
                 0) val_mont_lbel,
             nvl((SELECT SUM(decode(val_prec_cata_unit_loca * num_unid_dema,
                                   0,
                                   0,
                                   val_prec_cata_unit_loca * num_unid_dema))
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 3
                    AND z.num_unid_dema > 0),
                 0) val_mont_cyzo,
             nvl((SELECT SUM(decode(val_prec_cata_unit_loca * num_unid_dema,
                                   0,
                                   0,
                                   val_prec_cata_unit_loca * num_unid_dema))
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod = 2
                    AND z.num_unid_dema > 0),
                 0) val_mont_esik,
             nvl((SELECT SUM(decode(val_prec_cata_unit_loca * num_unid_dema,
                                   0,
                                   0,
                                   val_prec_cata_unit_loca * num_unid_dema))
                   FROM ped_solic_posic z,
                        mae_produ       y
                  WHERE z.soca_oid_soli_cabe = cab.soca_oid_soli_cabe_refe
                    AND z.prod_oid_prod = y.oid_prod
                    AND y.mapr_oid_marc_prod NOT IN (1, 2, 3)
                    AND z.num_unid_dema > 0),
                 0) val_mont_marc,
             NULL fec_ulti_actu,
             nvl(m.cod_digi_ctrl, '-') cod_digi_ctrl,
             NULL fec_utc_carga,
             nvl((SELECT COUNT(1)
                   FROM ped_solic_posic psp
                  WHERE psp.soca_oid_soli_cabe =
                        cab.soca_oid_soli_cabe_refe
                    AND psp.num_unid_dema_real - psp.num_unid_compr > 0),
                 0) num_falt_no_anun,
             cab.docu_num_docu,
             NULL val_mont_esti_expo,
             NULL val_mont_esti_agot_stoc,
             (SELECT SUM(val_prec_cata_unit_loca *
                         nvl(ped_pkg_cuadr_ofert.ped_fn_limit_venta(cab.clie_oid_clie,
                                                                    cab.ztad_oid_terr_admi,
                                                                    
                                                                    x.ofde_oid_deta_ofer,
                                                                    num_unid_dema),
                             0))
                FROM int_solic_conso_detal x
               WHERE sec_nume_docu_cabe = cab.sec_nume_docu) val_mont_esti_falt_anun,
             NULL val_mont_esti_vent_cata,
             cab.ind_vali_prol,
             cab.esta_oid_esta_clie,
             (SELECT COUNT(1)
                FROM sto_detal_docum_excep
               WHERE sec_nume_docu = cab.sec_nume_docu
                 AND cod_vali = 'OCC-19'
                 AND ind_apro = '0') ind_deud
        FROM int_solic_conso_cabec  cab,
             v_mae_clie_unida_admin m,
             ped_solic_cabec        ped
       WHERE cab.cod_clie = m.cod_clie(+)
         AND cab.soca_oid_soli_cabe_refe = ped.oid_soli_cabe(+)
         AND cab.fec_modi > ldfecultiactu
         AND m.ind_acti = '1';
  
    ldfecultiactu soa_cntrl_carga.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE := CAST(sys_extract_utc(systimestamp) AS DATE);
  
    lnmontominimo NUMBER := 0;
    lnmontomaximo NUMBER := 0;
  
    ls_val_simb_mone seg_moned.val_simb_mone%TYPE;
    ls_nom_mone      gen_i18n_sicc_comun.val_i18n%TYPE;
  
  BEGIN
  
    SELECT cod_peri,
           cod_pais
      INTO lscodigoperiodoactual,
           lscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = '1'
       AND sta_camp = '0';
  
    SELECT sm.val_simb_mone,
           pq_apl_aux.valor_gen_i18n_sicc(1, sm.oid_mone, 'SEG_MONED')
      INTO ls_val_simb_mone,
           ls_nom_mone
      FROM seg_moned sm,
           seg_pais  p
     WHERE sm.oid_mone = p.mone_oid_mone
       AND p.cod_pais = lscodigopais;
  
    SELECT fec_ulti_actu
      INTO ldfecultiactu
      FROM soa_cntrl_carga c
     WHERE c.nom_enti = 'SOA_INFOR_PEDID';
  
    UPDATE soa_cntrl_carga
       SET fec_ulti_actu = SYSDATE
     WHERE nom_enti = 'SOA_INFOR_PEDID';
  
    UPDATE soa_proce_x_pais
       SET cod_pais      = lscodigopais,
           fec_utc_carga = ldfecutccarga
     WHERE nom_enti IN ('SOA_INFOR_PEDID', 'SOA_PEDID_DETAL');
  
    OPEN c_soa_pedidos_actual(ldfecultiactu);
    LOOP
      FETCH c_soa_pedidos_actual BULK COLLECT
        INTO soa_tab LIMIT 1000;
      EXIT WHEN soa_tab.count = 0;
    
      FOR x IN soa_tab.first .. soa_tab.last
      LOOP
        DELETE soa_infor_pedid
         WHERE val_nume_soli = soa_tab(x).val_nume_soli;
        DELETE soa_pedid_detal
         WHERE sec_nume_docu = soa_tab(x).val_nume_soli;
        DELETE soa_pedid_falta
         WHERE val_nume_soli = soa_tab(x).val_nume_soli;
      
        soa_tab(x).smb_mone := ls_val_simb_mone;
        soa_tab(x).nom_mone := ls_nom_mone;
        soa_tab(x).fec_ulti_actu := SYSDATE;
        soa_tab(x).fec_utc_carga := ldfecutccarga;
      
        soa_tab(x).mot_rech := soa_fn_descr_motiv_recha_pedid(soa_tab(x)
                                                              .cod_moti_rech);
        soa_tab(x).val_esta_pedi := soa_fn_estad_pedid(soa_tab(x).cod_peri,
                                                       soa_tab(x)
                                                       .val_nume_soli,
                                                       soa_tab(x).num_lote,
                                                       soa_tab(x)
                                                       .val_mont_soli);
      
        SELECT SUM(val_mont_esti * CASE
                     WHEN val_unid_dem < val_unid THEN
                      val_unid_dem
                     ELSE
                      val_unid
                   END)
          INTO soa_tab(x).val_mont_esti
          FROM (SELECT h.val_unid_dem,
                       decode((SELECT MAX(n.val_limi_ctrl_vent)
                                FROM ped_gesti_stock      n,
                                     mae_clien            o,
                                     mae_clien_tipo_subti p,
                                     mae_clien_clasi      q
                               WHERE n.ofde_oid_deta_ofer = m.oid_deta_ofer
                                 AND o.cod_clie = h.cod_clie
                                 AND o.oid_clie = p.clie_oid_clie
                                 AND p.oid_clie_tipo_subt =
                                     q.ctsu_oid_clie_tipo_subt
                                 AND p.ticl_oid_tipo_clie =
                                     decode(n.ticl_oid_tipo_clie,
                                            NULL,
                                            p.ticl_oid_tipo_clie,
                                            n.ticl_oid_tipo_clie)
                                 AND p.sbti_oid_subt_clie =
                                     decode(n.sbti_oid_subt_clie,
                                            NULL,
                                            p.sbti_oid_subt_clie,
                                            n.sbti_oid_subt_clie)
                                 AND q.tccl_oid_tipo_clasi =
                                     decode(n.tccl_oid_tipo_clas,
                                            NULL,
                                            q.tccl_oid_tipo_clasi,
                                            n.tccl_oid_tipo_clas)
                                 AND q.clas_oid_clas =
                                     decode(n.clas_oid_clas,
                                            NULL,
                                            q.clas_oid_clas,
                                            n.clas_oid_clas)
                                 AND zz.zzon_oid_zona =
                                     decode(n.zzon_oid_zona,
                                            NULL,
                                            zz.zzon_oid_zona,
                                            n.zzon_oid_zona)
                                 AND xx.zorg_oid_regi =
                                     decode(n.zorg_oid_regi,
                                            NULL,
                                            xx.zorg_oid_regi,
                                            n.zorg_oid_regi)),
                              NULL,
                              h.val_unid_dem,
                              (SELECT MAX(n.val_limi_ctrl_vent)
                                 FROM ped_gesti_stock      n,
                                      mae_clien            o,
                                      mae_clien_tipo_subti p,
                                      mae_clien_clasi      q
                                WHERE n.ofde_oid_deta_ofer = m.oid_deta_ofer
                                  AND o.cod_clie = h.cod_clie
                                  AND o.oid_clie = p.clie_oid_clie
                                  AND p.oid_clie_tipo_subt =
                                      q.ctsu_oid_clie_tipo_subt
                                  AND p.ticl_oid_tipo_clie =
                                      decode(n.ticl_oid_tipo_clie,
                                             NULL,
                                             p.ticl_oid_tipo_clie,
                                             n.ticl_oid_tipo_clie)
                                  AND p.sbti_oid_subt_clie =
                                      decode(n.sbti_oid_subt_clie,
                                             NULL,
                                             p.sbti_oid_subt_clie,
                                             n.sbti_oid_subt_clie)
                                  AND q.tccl_oid_tipo_clasi =
                                      decode(n.tccl_oid_tipo_clas,
                                             NULL,
                                             q.tccl_oid_tipo_clasi,
                                             n.tccl_oid_tipo_clas)
                                  AND q.clas_oid_clas =
                                      decode(n.clas_oid_clas,
                                             NULL,
                                             q.clas_oid_clas,
                                             n.clas_oid_clas)
                                  AND n.val_limi_ctrl_vent IS NOT NULL
                                  AND n.perd_oid_peri = j.oid_peri
                                  AND zz.zzon_oid_zona =
                                      decode(n.zzon_oid_zona,
                                             NULL,
                                             zz.zzon_oid_zona,
                                             n.zzon_oid_zona)
                                  AND xx.zorg_oid_regi =
                                      decode(n.zorg_oid_regi,
                                             NULL,
                                             xx.zorg_oid_regi,
                                             n.zorg_oid_regi))) val_unid,
                       CASE
                         WHEN l.coes_oid_estr IN (2002, 2006) THEN
                          (SELECT SUM(imp_prec_cata)
                             FROM pre_ofert_detal
                            WHERE ofer_oid_ofer = l.oid_ofer)
                         ELSE
                          m.imp_prec_cata
                       END val_mont_esti
                  FROM int_solic_conso_detal h,
                       int_solic_conso_cabec zz,
                       zon_zona              xx,
                       pre_matri_factu_cabec i,
                       cra_perio             j,
                       seg_perio_corpo       k,
                       pre_ofert             l,
                       pre_ofert_detal       m
                 WHERE h.cod_peri = k.cod_peri
                   AND k.oid_peri = j.peri_oid_peri
                   AND j.oid_peri = i.perd_oid_peri
                   AND i.oid_cabe = l.mfca_oid_cabe
                   AND l.oid_ofer = m.ofer_oid_ofer
                   AND m.val_codi_vent = h.cod_vent
                   AND h.cod_clie = zz.cod_clie
                   AND h.cod_peri = zz.cod_peri
                   AND h.num_lote = zz.num_lote
                   AND zz.zzon_oid_zona = xx.oid_zona
                   AND zz.sec_nume_docu = soa_tab(x).val_nume_soli
                   AND h.sec_nume_docu_cabe = soa_tab(x).val_nume_soli
                   AND h.sec_nume_docu_cabe = soa_tab(x).val_nume_soli);
      
        SELECT SUM(val_prec_cata_unit_loca * num_unid_dema)
          INTO soa_tab(x).val_mont_esti_expo
          FROM int_solic_conso_detal x,
               pre_ofert_detal       y,
               pre_tipo_ofert        z
         WHERE sec_nume_docu_cabe = soa_tab(x).val_nume_soli
           AND x.ofde_oid_deta_ofer = y.oid_deta_ofer
           AND y.tofe_oid_tipo_ofer = z.oid_tipo_ofer
           AND z.cod_tipo_ofer = '021';
      
        SELECT SUM(val_prec_cata_unit_loca * num_unid_dema)
          INTO soa_tab(x).val_mont_esti_agot_stoc
          FROM int_solic_conso_detal x,
               pre_ofert_detal       y,
               pre_tipo_ofert        z
         WHERE sec_nume_docu_cabe = soa_tab(x).val_nume_soli
           AND x.ofde_oid_deta_ofer = y.oid_deta_ofer
           AND y.tofe_oid_tipo_ofer = z.oid_tipo_ofer
           AND z.cod_tipo_ofer = '016';
      
        SELECT SUM(val_prec_cata_unit_loca * num_unid_dema) - soa_tab(x)
               .val_mont_esti_expo - soa_tab(x).val_mont_esti_agot_stoc - soa_tab(x)
               .val_mont_esti_falt_anun
          INTO soa_tab(x).val_mont_esti_vent_cata
          FROM int_solic_conso_detal x
         WHERE sec_nume_docu_cabe = soa_tab(x).val_nume_soli;
      
        /* soa_tab(x).val_mont_esti_expo,
        soa_tab(x).val_mont_esti_agot_stoc,
        soa_tab(x).val_mont_esti_falt_anun,
        soa_tab(x).val_mont_esti_vent_cata,*/
      
        IF soa_tab(x).val_esta_pedi = 'OBSERVADO' AND soa_tab(x)
           .soca_oid_soli_cabe_refe IS NOT NULL THEN
          --
          SELECT MAX(val_niv1) INTO lnmontominimo FROM ped_monto_minim;
          IF soa_tab(x).val_mont_soli < lnmontominimo THEN
            soa_tab(x).cod_moti_rech := '01';
            soa_tab(x).mot_rech := 'MONTO MINIMO';
          
          ELSE
          
           /* SELECT MAX(val_mont_maxi_perm)
              INTO lnmontomaximo
              FROM car_param_carte
             WHERE ind_mont_maxi = 1
               AND niri_oid_nive_ries =
                   (SELECT niri_oid_nive_ries
                      FROM mae_clien_datos_adici,
                           mae_clien
                     WHERE clie_oid_clie = oid_clie
                       AND cod_clie = soa_tab(x).cod_clie);*/


    SELECT a.clie_oid_clie,
           b.ztad_oid_terr_admi
      INTO p_oidclie,
           p_oidterradmin
      FROM ped_solic_cabec       a,
           int_solic_conso_cabec b
     WHERE a.oid_soli_cabe = p_oidsoli
       AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe;
  
    p_mtomaxim := nvl(ped_fn_obtie_mmaxim(p_oidclie, p_oidterradmin),
                      999999999);






            IF soa_tab(x).val_mont_soli > lnmontomaximo THEN
              soa_tab(x).cod_moti_rech := '02';
              soa_tab(x).mot_rech := 'MONTO MAXIMO';
            END IF;
          END IF;
        END IF;
      
        --SE BORRA EL DETALLE SY SE VUELVE ACRAGAR
        IF soa_tab(x).ind_proc_gp2 = '1' THEN
          INSERT INTO soa_pedid_detal
            SELECT DISTINCT det.cod_peri,
                            det.sec_nume_docu_cabe,
                            (SELECT DISTINCT o.des_orig
                               FROM sto_orige_docum       o,
                                    sto_combi_orige_docum c
                              WHERE o.cod_pais = det.cod_pais
                                AND o.cod_tipo_docu = 'OCD'
                                AND o.cod_pais = det.cod_pais
                                AND o.cod_tipo_docu = c.cod_tipo_docu
                                AND o.cod_orig = c.cod_orig
                                AND c.ind_rece_ocr = det.ind_rece_ocr
                                AND c.ind_rece_web = det.ind_rece_web
                                AND c.ind_rece_dd = det.ind_rece_dd
                                AND c.ind_rece_digi = det.ind_rece_digi
                                AND c.ind_rece_cc = det.ind_rece_cc
                                AND c.ind_rece_mens = det.ind_rece_mens
                                AND c.ind_rece_onli = det.ind_rece_onli
                                AND c.ind_rece_ivr = det.ind_rece_ivr) des_orig,
                            det.cod_vent,
                            det.des_prod,
                            det.val_unid_dem,
                            det.val_prec_cata_unit_loca,
                            det.val_unid_dem * det.val_prec_cata_unit_loca val_pre_tota,
                            psp.val_impo_desc_tota_loca val_desc,
                            det.val_prec_cata_unit_loca * det.val_unid_dem -
                            psp.val_impo_desc_tota_loca val_tota_paga,
                            SYSDATE fec_ulti_actu,
                            ldfecutccarga fec_utc_carga,
                            NULL
              FROM int_solic_conso_detal det,
                   ped_solic_posic       psp
             WHERE det.sec_nume_docu_cabe = soa_tab(x).val_nume_soli
               AND psp.soca_oid_soli_cabe = soa_tab(x)
                  .soca_oid_soli_cabe_refe
               AND psp.val_codi_vent = det.cod_vent;
        
          INSERT INTO soa_pedid_falta
            SELECT soa_tab(x).cod_peri,
                   soa_tab(x).val_nume_soli,
                   (SELECT MIN(des_orig)
                      FROM soa_pedid_detal
                     WHERE sec_nume_docu = soa_tab(x).val_nume_soli
                       AND cod_vent = p.val_codi_vent) des_orig,
                   p.val_codi_vent,
                   (SELECT MIN(val_i18n)
                      FROM gen_i18n_sicc_pais g
                     WHERE g.attr_enti = 'MAE_PRODU'
                       AND val_oid = prod_oid_prod) des_prod,
                   val_prec_cata_unit_loca val_prec_cata,
                   num_unid_dema num_unid_dema,
                   num_unid_compr num_unid_aten,
                   'FNA' tip_falt,
                   SYSDATE fec_ulti_actu,
                   ldfecutccarga fec_utc_carg
              FROM ped_solic_posic p
             WHERE soca_oid_soli_cabe = soa_tab(x).soca_oid_soli_cabe_refe
               AND p.num_unid_dema_real - p.num_unid_compr > 0;
        
          INSERT INTO soa_pedid_falta
            SELECT soa_tab(x).cod_peri,
                   soa_tab(x).val_nume_soli,
                   (SELECT MIN(des_orig)
                      FROM soa_pedid_detal
                     WHERE sec_nume_docu = soa_tab(x).val_nume_soli
                       AND cod_vent = p.val_codi_vent) des_orig,
                   p.val_codi_vent,
                   (SELECT MIN(val_i18n)
                      FROM gen_i18n_sicc_pais g
                     WHERE g.attr_enti = 'MAE_PRODU'
                       AND val_oid = prod_oid_prod) des_prod,
                   val_prec_cata_unit_loca val_prec_cata,
                   num_unid_dema num_unid_dema,
                   num_unid_compr num_unid_aten,
                   'FA' tip_falt,
                   SYSDATE fec_ulti_actu,
                   ldfecutccarga fec_utc_carg
              FROM ped_solic_posic p
             WHERE soca_oid_soli_cabe = soa_tab(x).soca_oid_soli_cabe_refe
               AND ind_limi_vent = '1'
               AND p.num_unid_dema_real - p.num_unid_compr = 0;
        
        ELSE
          INSERT INTO soa_pedid_detal
            SELECT det.cod_peri,
                   det.sec_nume_docu_cabe,
                   (SELECT DISTINCT o.des_orig
                      FROM sto_orige_docum       o,
                           sto_combi_orige_docum c
                     WHERE o.cod_pais = det.cod_pais
                       AND o.cod_tipo_docu = 'OCD'
                       AND o.cod_pais = det.cod_pais
                       AND o.cod_tipo_docu = c.cod_tipo_docu
                       AND o.cod_orig = c.cod_orig
                       AND c.ind_rece_ocr = det.ind_rece_ocr
                       AND c.ind_rece_web = det.ind_rece_web
                       AND c.ind_rece_dd = det.ind_rece_dd
                       AND c.ind_rece_digi = det.ind_rece_digi
                       AND c.ind_rece_cc = det.ind_rece_cc
                       AND c.ind_rece_mens = det.ind_rece_mens
                       AND c.ind_rece_onli = det.ind_rece_onli
                       AND c.ind_rece_ivr = det.ind_rece_ivr) des_orig,
                   det.cod_vent,
                   det.des_prod,
                   det.val_unid_dem,
                   det.val_prec_cata_unit_loca,
                   det.val_unid_dem * det.val_prec_cata_unit_loca val_pre_tota,
                   0 val_desc,
                   0 val_tota_paga,
                   SYSDATE fec_ulti_actu,
                   ldfecutccarga fec_utc_carga,
                   NULL
              FROM int_solic_conso_detal det
             WHERE det.sec_nume_docu_cabe = soa_tab(x).val_nume_soli;
        
          INSERT INTO soa_pedid_falta
            SELECT soa_tab(x).cod_peri,
                   soa_tab(x).val_nume_soli,
                   (SELECT MIN(des_orig)
                      FROM soa_pedid_detal
                     WHERE sec_nume_docu = soa_tab(x).val_nume_soli
                       AND cod_vent = p.cod_vent) des_orig,
                   cod_vent val_codi_vent,
                   des_prod,
                   val_prec_cata_unit_loca val_prec_cata,
                   val_unid_dem num_unid_dema,
                   val_limi_venta num_unid_aten,
                   'FA' tip_falt,
                   SYSDATE fec_ulti_actu,
                   ldfecutccarga fec_utc_carg
              FROM (SELECT cd.cod_vent,
                           cd.des_prod,
                           cd.val_unid_dem,
                           cd.val_prec_cata_unit_loca,
                           (SELECT MAX(n.val_limi_ctrl_vent)
                              FROM ped_gesti_stock      n,
                                   mae_clien            o,
                                   mae_clien_tipo_subti p,
                                   mae_clien_clasi      q,
                                   zon_terri_admin      r,
                                   zon_secci            s,
                                   zon_terri            t,
                                   zon_zona             u
                             WHERE n.ofde_oid_deta_ofer =
                                   cd.ofde_oid_deta_ofer
                               AND o.oid_clie = cc.clie_oid_clie
                               AND r.oid_terr_admi = cc.terr_oid_terr
                               AND r.terr_oid_terr = t.oid_terr
                               AND r.zscc_oid_secc = s.oid_secc
                               AND s.zzon_oid_zona = u.oid_zona
                               AND p.clie_oid_clie = o.oid_clie
                               AND p.oid_clie_tipo_subt =
                                   q.ctsu_oid_clie_tipo_subt
                               AND p.ticl_oid_tipo_clie =
                                   decode(n.ticl_oid_tipo_clie,
                                          NULL,
                                          p.ticl_oid_tipo_clie,
                                          n.ticl_oid_tipo_clie)
                               AND p.sbti_oid_subt_clie =
                                   decode(n.sbti_oid_subt_clie,
                                          NULL,
                                          p.sbti_oid_subt_clie,
                                          n.sbti_oid_subt_clie)
                               AND q.tccl_oid_tipo_clasi =
                                   decode(n.tccl_oid_tipo_clas,
                                          NULL,
                                          q.tccl_oid_tipo_clasi,
                                          n.tccl_oid_tipo_clas)
                               AND q.clas_oid_clas =
                                   decode(n.clas_oid_clas,
                                          NULL,
                                          q.clas_oid_clas,
                                          n.clas_oid_clas)
                               AND u.oid_zona =
                                   decode(n.zzon_oid_zona,
                                          NULL,
                                          u.oid_zona,
                                          n.zzon_oid_zona)
                               AND u.zorg_oid_regi =
                                   decode(n.zorg_oid_regi,
                                          NULL,
                                          u.zorg_oid_regi,
                                          n.zorg_oid_regi)
                               AND n.val_limi_ctrl_vent IS NOT NULL
                               AND n.perd_oid_peri = cc.perd_oid_peri) val_limi_venta
                      FROM int_solic_conso_detal cd,
                           int_solic_conso_cabec cc
                     WHERE cc.sec_nume_docu = cd.sec_nume_docu_cabe
                       AND cc.sec_nume_docu = soa_tab(x).val_nume_soli) p
             WHERE val_limi_venta IS NOT NULL;
        
        END IF;
      
        UPDATE soa_pedid_detal d
           SET fla_falt = '1'
         WHERE d.sec_nume_docu = soa_tab(x).val_nume_soli
           AND EXISTS (SELECT 1
                  FROM soa_pedid_falta f
                 WHERE f.val_nume_soli = d.sec_nume_docu
                   AND f.cod_vent = d.cod_vent);
      
        SELECT COUNT(1)
          INTO soa_tab(x).num_falt_anun
          FROM soa_pedid_falta
         WHERE val_nume_soli = soa_tab(x).val_nume_soli
           AND tip_falt = 'FA';
      
        INSERT INTO soa_infor_pedid VALUES soa_tab (x);
      
      END LOOP;
    
    END LOOP;
    CLOSE c_soa_pedidos_actual;
  
    OPEN c_soa_pedidos_rechazados(ldfecultiactu);
    LOOP
      FETCH c_soa_pedidos_rechazados BULK COLLECT
        INTO soa_rech_tab LIMIT 1000;
      EXIT WHEN soa_rech_tab.count = 0;
    
      FOR x IN soa_rech_tab.first .. soa_rech_tab.last
      LOOP
        DELETE soa_pedid_recha
         WHERE sec_nume_docu = soa_rech_tab(x).sec_nume_docu
           AND cod_vali = soa_rech_tab(x).cod_vali;
        soa_tab(x).fec_ulti_actu := SYSDATE;
        soa_tab(x).fec_utc_carga := ldfecutccarga;
      
        INSERT INTO soa_pedid_recha VALUES soa_rech_tab (x);
      
      END LOOP;
    
    END LOOP;
    CLOSE c_soa_pedidos_rechazados;
  
    DELETE soa_infor_pedid i
     WHERE i.cod_peri = lscodigoperiodoactual
       AND NOT EXISTS
    
     (SELECT 1
              FROM int_solic_conso_cabec c
             WHERE i.val_nume_soli = c.sec_nume_docu);
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INFORME PEDIDO: ' || ls_sqlerrm);
    
  END soa_pr_pedid;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_RESUM_PEDID
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_resum_pedid IS
  
    CURSOR c_soa_resum_pedid IS
      SELECT cab.cod_peri,
             cab.cod_regi,
             cab.cod_zona,
             cab.cod_secc,
             COUNT(1) val_cant_pedi,
             SUM(decode(cab.cod_esta_clie, '04', 1, 0)) val_cant_posi_egre,
             SUM(decode(cab.ind_prim_pedi, 'SI', 1, 0)) val_cant_prim_pedi,
             SUM(decode(cab.val_orig, 'OCR', 1, 0)) val_cant_pedid_ocr,
             SUM(decode(cab.val_orig, 'Web', 1, 0)) val_cant_pedid_web,
             SUM(CASE
                   WHEN cab.val_orig NOT IN ('OCR', 'Web', 'DD', 'Mixto') THEN
                    1
                   ELSE
                    0
                 END) val_cant_pedid_otros,
             SUM(decode(cab.val_orig, 'DD', 1, 0)) val_cant_pedid_dd,
             SUM(decode(cab.val_orig, 'Mixto', 1, 0)) val_cant_pedid_mixto,
             SUM(decode(cab.val_esta_pedi, 'ENVIADO', 1, 0)) val_cant_pedid_enviados,
             SUM(decode(cab.val_esta_pedi, 'OBSERVADO', 1, 0)) val_cant_pedid_obse,
             SUM(decode(cab.val_esta_pedi, 'RECHAZADO', 1, 0)) val_cant_pedid_rech,
             SUM(decode(cab.val_esta_pedi, 'FACTURADO', 1, 0)) val_cant_facturados,
             SUM(cab.val_mont_fact) val_mont_fact,
             cab.nom_mone des_mone,
             cab.smb_mone smb_mone,
             SYSDATE fec_ulti_actu,
             MAX(fec_utc_carga) fec_utc_carga,
             (SELECT val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' ||
                     val_ape2
                FROM mae_clien a,
                     zon_zona  b
               WHERE a.oid_clie = b.clie_oid_clie
                 AND b.cod_zona = cab.cod_zona) val_nomb_gere_zona,
             SUM(CASE
                   WHEN esta_oid_esta_clie = 1 AND ind_proc_gp2 = '1' THEN
                    1
                   ELSE
                    0
                 END) val_cant_ingr,
             SUM(CASE
                   WHEN esta_oid_esta_clie = 5 AND ind_proc_gp2 = '1' THEN
                    1
                   ELSE
                    0
                 END) val_cant_rein,
             (SELECT COUNT(1)
                FROM v_mae_clie_unida_admin a
               WHERE esta_oid_esta_clie = 4
                 AND perd_oid_peri_fin IS NULL
                 AND cod_zona = cab.cod_zona
                 AND NOT EXISTS (SELECT 1
                        FROM int_solic_conso_cabec
                       WHERE cod_clie = a.cod_clie
                         AND ind_proc_gp2 = 1)) val_cant_egre,
             SUM(ind_deud) val_cant_deud
        FROM soa_infor_pedid cab
       GROUP BY cab.cod_peri,
                cab.cod_regi,
                cab.cod_zona,
                cab.cod_secc,
                cab.nom_mone,
                cab.smb_mone;
  
    TYPE soa_resum_pedid_tab_t IS TABLE OF soa_resum_pedid%ROWTYPE INDEX BY BINARY_INTEGER;
    soa_resum_pedid_tab soa_resum_pedid_tab_t;
  
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
    lscodigopais  soa_proce_x_pais.cod_pais%TYPE;
  
  BEGIN
  
    SELECT fec_utc_carga
      INTO ldfecutccarga
      FROM soa_proce_x_pais
     WHERE nom_enti = 'SOA_INFOR_PEDID';
  
    SELECT cod_pais
      INTO lscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = '1'
       AND sta_camp = '0';
  
    DELETE soa_resum_pedid;
    OPEN c_soa_resum_pedid;
    LOOP
    
      FETCH c_soa_resum_pedid BULK COLLECT
        INTO soa_resum_pedid_tab LIMIT 1000;
      EXIT WHEN soa_resum_pedid_tab.count = 0;
    
      FORALL i IN 1 .. soa_resum_pedid_tab.count
        INSERT INTO soa_resum_pedid VALUES soa_resum_pedid_tab (i);
    
    END LOOP;
    CLOSE c_soa_resum_pedid;
  
    UPDATE soa_proce_x_pais
       SET cod_pais      = lscodigopais,
           fec_utc_carga = ldfecutccarga
     WHERE nom_enti = 'SOA_RESUM_PEDID';
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INFORME RESUMEN PEDIDO: ' ||
                              ls_sqlerrm);
    
  END soa_pr_resum_pedid;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_ind_sicc_cobra IS
  
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_sicc(lscodigoperiodo VARCHAR2) IS
      SELECT DISTINCT cbz2.cod_peri,
                      cbz2.cod_regi,
                      cbz2.cod_zona,
                      (SELECT round(SUM(cbz.cob_dias_21) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                        GROUP BY cbz.cod_peri) cob_21_perio,
                      (SELECT round(SUM(cbz.cob_dias_21) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi) cob_21_region,
                      (SELECT round(SUM(cbz.cob_dias_21) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                          AND cbz.cod_zona = cbz2.cod_zona
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi,
                                 cbz.cod_zona) cob_21_zona,
                      (SELECT round(SUM(cbz.cob_dias_31) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                        GROUP BY cbz.cod_peri) cob_31_perio,
                      (SELECT round(SUM(cbz.cob_dias_31) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi) cob_31_region,
                      (SELECT round(SUM(cbz.cob_dias_31) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                          AND cbz.cod_zona = cbz2.cod_zona
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi,
                                 cbz.cod_zona) cob_31_zona,
                      (SELECT round(SUM(cbz.cob_dias_63) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                        GROUP BY cbz.cod_peri) cob_63_perio,
                      (SELECT round(SUM(cbz.cob_dias_63) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi) cob_63_region,
                      (SELECT round(SUM(cbz.cob_dias_63) /
                                    SUM(cbz.imp_fact_neto) * 100,
                                    2) por_recu
                         FROM cob_repor_estad_recup_cobra cbz
                        WHERE cod_peri = cbz2.cod_peri
                          AND cbz.cod_regi = cbz2.cod_regi
                          AND cbz.cod_zona = cbz2.cod_zona
                        GROUP BY cbz.cod_peri,
                                 cbz.cod_regi,
                                 cbz.cod_zona) cob_63_zona
        FROM cob_repor_estad_recup_cobra cbz2
       WHERE cbz2.cod_peri = lscodigoperiodo;
  
    TYPE recordindicsicc IS RECORD(
      cod_peri      soa_indic_sicc.cod_peri%TYPE,
      cod_regi      soa_indic_sicc.cod_regi%TYPE,
      cod_zona      soa_indic_sicc.cod_zona%TYPE,
      cob_21_perio  soa_indic_sicc.cob_21_perio%TYPE,
      cob_21_region soa_indic_sicc.cob_21_region%TYPE,
      cob_21_zona   soa_indic_sicc.cob_21_zona%TYPE,
      cob_31_perio  soa_indic_sicc.cob_31_perio%TYPE,
      cob_31_region soa_indic_sicc.cob_31_region%TYPE,
      cob_31_zona   soa_indic_sicc.cob_31_zona%TYPE,
      cob_63_perio  soa_indic_sicc.cob_63_perio%TYPE,
      cob_63_region soa_indic_sicc.cob_63_region%TYPE,
      cob_63_zona   soa_indic_sicc.cob_63_zona%TYPE);
  
    TYPE indicsicctab IS TABLE OF recordindicsicc;
    interfazrecord indicsicctab;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_sicc.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_sicc;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_sicc(lsperiodo);
    LOOP
      FETCH c_soa_indic_sicc BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_sicc
            (cod_peri,
             cod_regi,
             cod_zona,
             cob_21_perio,
             cob_21_region,
             cob_21_zona,
             cob_31_perio,
             cob_31_region,
             cob_31_zona,
             cob_63_perio,
             cob_63_region,
             cob_63_zona,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cob_21_perio,
             interfazrecord(x).cob_21_region,
             interfazrecord(x).cob_21_zona,
             interfazrecord(x).cob_31_perio,
             interfazrecord(x).cob_31_region,
             interfazrecord(x).cob_31_zona,
             interfazrecord(x).cob_63_perio,
             interfazrecord(x).cob_63_region,
             interfazrecord(x).cob_63_zona,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_SICC';
      
      END IF;
      EXIT WHEN c_soa_indic_sicc%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_sicc;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
    
  END soa_pr_ind_sicc_cobra;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_LETS
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_ind_sicc_lets IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_sicc_lets(lscodigoperiodo VARCHAR2) IS
      SELECT resc.lide_cam_lide cod_peri,
             resc.cod_regi,
             resc.cod_zona,
             resc.val_esta
        FROM let_resul_secci_campa resc
       WHERE resc.val_esta IN ('C', 'P', 'X', 'S')
         AND resc.lide_cam_lide = lscodigoperiodo;
  
    TYPE recordindicsicclets IS RECORD(
      cod_peri soa_indic_sicc_lets.cod_peri%TYPE,
      cod_regi soa_indic_sicc_lets.cod_regi%TYPE,
      cod_zona soa_indic_sicc_lets.cod_zona%TYPE,
      val_esta soa_indic_sicc_lets.val_esta%TYPE);
  
    TYPE indicsiccletstab IS TABLE OF recordindicsicclets;
    interfazrecord indicsiccletstab;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_sicc_lets.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_sicc_lets;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_sicc_lets(lsperiodo);
    LOOP
      FETCH c_soa_indic_sicc_lets BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_sicc_lets
            (cod_peri,
             cod_regi,
             cod_zona,
             val_esta,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).val_esta,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_SICC_LETS';
      
      END IF;
      EXIT WHEN c_soa_indic_sicc_lets%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_sicc_lets;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INDICADOR SICC LETS: ' || ls_sqlerrm);
    
  END soa_pr_ind_sicc_lets;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_META
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_ind_sicc_meta IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_sicc_meta(lscodigoperiodo VARCHAR2) IS
      SELECT DISTINCT a.cod_peri,
                      zorg.cod_regi,
                      zzon.cod_zona,
                      clie.cod_clie,
                      nvcg.imp_logr,
                      (SELECT SUM(sca2.val_mont_tota)
                         FROM ped_solic_cabec_acum2 sca2
                        WHERE sca2.clie_oid_clie = clie.oid_clie
                          AND sca2.perd_oid_peri BETWEEN cprc.perd_oid_peri AND
                              b.oid_peri) val_mont_acum
        FROM nvs_consu_logro       nvcg,
             mae_clien             clie,
             mae_clien_tipo_subti  ctsu,
             mae_clien_clasi       clcl,
             mae_clien_prime_conta cprc,
             mae_clien_unida_admin cuad,
             zon_terri_admin       ztad,
             zon_secci             zscc,
             zon_zona              zzon,
             zon_regio             zorg,
             seg_perio_corpo       a,
             cra_perio             b
       WHERE nvcg.cod_clie = clie.cod_clie
         AND clie.oid_clie = ctsu.clie_oid_clie
         AND ctsu.oid_clie_tipo_subt = clcl.ctsu_oid_clie_tipo_subt
         AND clie.oid_clie = cprc.clie_oid_clie(+)
         AND clie.oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
            --
         AND nvcg.est_regi != '9' -- Nuevo
         AND nvcg.est_logr = '1' -- Nuevo
         AND ctsu.ticl_oid_tipo_clie = 2 -- consultoras
         AND ctsu.sbti_oid_subt_clie = 1 -- negocio
         AND clcl.tccl_oid_tipo_clasi = 2010 -- consultora nueva
         AND clcl.clas_oid_clas = 2008 -- consultora nueva,
         AND a.oid_peri = b.peri_oid_peri
         AND a.cod_peri = lscodigoperiodo
         AND b.oid_peri BETWEEN cuad.perd_oid_peri_ini AND
             nvl(cuad.perd_oid_peri_fin, b.oid_peri);
  
    TYPE recordindicsiccmeta IS RECORD(
      cod_peri      soa_indic_sicc_meta.cod_peri%TYPE,
      cod_regi      soa_indic_sicc_meta.cod_regi%TYPE,
      cod_zona      soa_indic_sicc_meta.cod_zona%TYPE,
      cod_clie      soa_indic_sicc_meta.cod_clie%TYPE,
      imp_logr      soa_indic_sicc_meta.imp_logr%TYPE,
      val_mont_acum soa_indic_sicc_meta.val_mont_acum%TYPE);
  
    TYPE indicsiccmetatab IS TABLE OF recordindicsiccmeta;
    interfazrecord indicsiccmetatab;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_sicc_meta.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_sicc_meta;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_sicc_meta(lsperiodo);
    LOOP
      FETCH c_soa_indic_sicc_meta BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_sicc_meta
            (cod_peri,
             cod_regi,
             cod_zona,
             cod_clie,
             imp_logr,
             val_mont_acum,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cod_clie,
             interfazrecord(x).imp_logr,
             interfazrecord(x).val_mont_acum,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_SICC_META';
      
      END IF;
      EXIT WHEN c_soa_indic_sicc_meta%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_sicc_meta;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INDICADOR SICC META: ' || ls_sqlerrm);
    
  END soa_pr_ind_sicc_meta;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_SICC_PEG
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  ***************************************************************************/

  PROCEDURE soa_pr_ind_sicc_peg IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_sicc_peg(lscodigoperiodo VARCHAR2) IS
      SELECT a.cod_peri,
             zorg.cod_regi,
             zzon.cod_zona,
             clie.cod_clie,
             CASE
               WHEN nvl(clie.sal_deud_ante, 0) > 0 THEN
                1
               ELSE
                0
             END con_deud_venc,
             CASE
               WHEN nvl(clie.sal_deud_ante, 0) = 0 THEN
                1
               ELSE
                0
             END sin_deud_venc
        FROM mae_clien_datos_adici clda,
             mae_clien             clie,
             mae_clien_unida_admin cuad,
             zon_terri_admin       ztad,
             zon_secci             zscc,
             zon_zona              zzon,
             zon_regio             zorg,
             seg_perio_corpo       a,
             cra_perio             b
       WHERE clda.clie_oid_clie = clie.oid_clie
         AND clda.clie_oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
         AND a.oid_peri = b.peri_oid_peri
         AND clda.esta_oid_esta_clie = 4 -- posible egreso
         AND clda.ind_acti = 1 -- activo
         AND a.cod_peri = lscodigoperiodo
         AND b.oid_peri BETWEEN cuad.perd_oid_peri_ini AND
             nvl(cuad.perd_oid_peri_fin, b.oid_peri);
  
    TYPE recordindicsiccpeg IS RECORD(
      cod_peri      soa_indic_sicc_peg.cod_peri%TYPE,
      cod_regi      soa_indic_sicc_peg.cod_regi%TYPE,
      cod_zona      soa_indic_sicc_peg.cod_zona%TYPE,
      cod_clie      soa_indic_sicc_peg.cod_clie%TYPE,
      con_deud_venc soa_indic_sicc_peg.con_deud_venc%TYPE,
      sin_deud_venc soa_indic_sicc_peg.sin_deud_venc%TYPE);
  
    TYPE indicsiccpegtab IS TABLE OF recordindicsiccpeg;
    interfazrecord indicsiccpegtab;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_sicc_peg.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_sicc_peg;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_sicc_peg(lsperiodo);
    LOOP
      FETCH c_soa_indic_sicc_peg BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_sicc_peg
            (cod_peri,
             cod_regi,
             cod_zona,
             cod_clie,
             con_deud_venc,
             sin_deud_venc,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).cod_clie,
             interfazrecord(x).con_deud_venc,
             interfazrecord(x).sin_deud_venc,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_SICC_PEG';
      
      END IF;
      EXIT WHEN c_soa_indic_sicc_peg%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_sicc_peg;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INDICADOR SICC PEG: ' || ls_sqlerrm);
    
  END soa_pr_ind_sicc_peg;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_PEDID_COMER
  Fecha Creacion    : 15/07/2013
  Autor             : Jean Pierre Jimenez
  ***************************************************************************/

  PROCEDURE soa_pr_ind_pedid_comer IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_pedid_comer(lscodigoperiodo VARCHAR2) IS
      SELECT a.cod_peri,
             cab.cod_regi,
             cab.cod_zona,
             COUNT(*) AS val_tota_nume_pedi_come
        FROM ped_solic_cabec       ped,
             int_solic_conso_cabec cab,
             seg_perio_corpo       a
       WHERE ped.tspa_oid_tipo_soli_pais =
             fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC')
         AND ped.grpr_oid_grup_proc = 5 -- dato fijo que indica pedido facturado
         AND ped.perd_oid_peri =
             fin_pkg_gener.fin_fn_obtie_oid_perio(lscodigoperiodo) --  campaña actual '
         AND ped.oid_soli_cabe = cab.soca_oid_soli_cabe_refe -- enlace con STO
         AND a.cod_peri = cab.cod_peri
       GROUP BY a.cod_peri,
                cab.cod_regi,
                cab.cod_zona;
  
    TYPE recordindicpedcomer IS RECORD(
      cod_peri                soa_indic_pedid_comer.cod_peri%TYPE,
      cod_regi                soa_indic_pedid_comer.cod_regi%TYPE,
      cod_zona                soa_indic_pedid_comer.cod_zona%TYPE,
      val_tota_nume_pedi_come soa_indic_pedid_comer.val_tota_nume_pedi_come%TYPE);
  
    TYPE indicpedcomer IS TABLE OF recordindicpedcomer;
    interfazrecord indicpedcomer;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_pedid_comer.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_pedid_comer;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_pedid_comer(lsperiodo);
    LOOP
      FETCH c_soa_indic_pedid_comer BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_pedid_comer
            (cod_peri,
             cod_regi,
             cod_zona,
             val_tota_nume_pedi_come,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).val_tota_nume_pedi_come,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_PEDID_COMER';
      
      END IF;
      EXIT WHEN c_soa_indic_pedid_comer%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_pedid_comer;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INDICADOR PEDIDO COMERCIAL: ' ||
                              ls_sqlerrm);
    
  END soa_pr_ind_pedid_comer;

  /***************************************************************************
  Descripcion       : Proceso de carga de data a la tabla SOA_INDIC_PEDID_WEB_COMER
  Fecha Creacion    : 15/07/2013
  Autor             : Jean Pierre Jimenez
  ***************************************************************************/

  PROCEDURE soa_pr_ind_pedid_web_comer IS
  
    ls_sqlerrm      VARCHAR2(150);
    pscodigoperiodo VARCHAR2(6);
    pscodigopais    VARCHAR2(3);
  
    CURSOR c_soa_indic_pedid_web_comer(lscodigoperiodo VARCHAR2) IS
      SELECT a.cod_peri,
             cab.cod_regi,
             cab.cod_zona,
             COUNT(*) AS val_tota_nume_pedi_web_come,
             SUM(ped.val_tota_paga_loca) val_monto_pedi_web_come
        FROM ped_solic_cabec       ped,
             int_solic_conso_cabec cab,
             seg_perio_corpo       a
       WHERE ped.tspa_oid_tipo_soli_pais =
             fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC')
         AND ped.grpr_oid_grup_proc = 5 -- dato fijo que indica pedido facturado
         AND ped.perd_oid_peri =
             fin_pkg_gener.fin_fn_obtie_oid_perio(lscodigoperiodo) --  campaña actual
         AND ped.oid_soli_cabe = cab.soca_oid_soli_cabe_refe -- enlace con STO
         AND cab.ind_rece_web = 1 -- pedido con contenido web
         AND a.cod_peri = cab.cod_peri
       GROUP BY a.cod_peri,
                cab.cod_regi,
                cab.cod_zona;
  
    TYPE recordindicpedwebcomer IS RECORD(
      cod_peri                    soa_indic_pedid_web_comer.cod_peri%TYPE,
      cod_regi                    soa_indic_pedid_web_comer.cod_regi%TYPE,
      cod_zona                    soa_indic_pedid_web_comer.cod_zona%TYPE,
      val_tota_nume_pedi_web_come soa_indic_pedid_web_comer.val_tota_nume_pedi_web_come%TYPE,
      val_monto_pedi_web_come     soa_indic_pedid_web_comer.val_monto_pedi_web_come%TYPE);
  
    TYPE indicpedwebcomer IS TABLE OF recordindicpedwebcomer;
    interfazrecord indicpedwebcomer;
  
    lsperiodo VARCHAR2(6);
  
    psfecultiactu soa_indic_pedid_web_comer.fec_ulti_actu%TYPE;
    ldfecutccarga soa_proce_x_pais.fec_utc_carga%TYPE;
  
  BEGIN
  
    DELETE FROM soa_indic_pedid_web_comer;
  
    SELECT cod_peri,
           cod_pais
      INTO pscodigoperiodo,
           pscodigopais
      FROM bas_ctrl_fact
     WHERE ind_camp_act = 1
       AND sta_camp = 0;
  
    SELECT SYSDATE INTO psfecultiactu FROM dual;
    SELECT CAST(sys_extract_utc(systimestamp) AS DATE)
      INTO ldfecutccarga
      FROM dual;
  
    --se carga actual
    lsperiodo := pscodigoperiodo;
  
    OPEN c_soa_indic_pedid_web_comer(lsperiodo);
    LOOP
      FETCH c_soa_indic_pedid_web_comer BULK COLLECT
        INTO interfazrecord LIMIT 1000;
      /* Procedimiento inicial para generar interfaz */
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          INSERT INTO soa_indic_pedid_web_comer
            (cod_peri,
             cod_regi,
             cod_zona,
             val_tota_nume_pedi_web_come,
             val_monto_pedi_web_come,
             fec_ulti_actu,
             fec_utc_carga)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).val_tota_nume_pedi_web_come,
             interfazrecord(x).val_monto_pedi_web_come,
             psfecultiactu,
             ldfecutccarga);
        
        END LOOP;
      
        UPDATE soa_proce_x_pais
           SET cod_pais      = pscodigopais,
               fec_utc_carga = ldfecutccarga
         WHERE nom_enti = 'SOA_INDIC_PEDID_WEB_COMER';
      
      END IF;
      EXIT WHEN c_soa_indic_pedid_web_comer%NOTFOUND;
    END LOOP;
    CLOSE c_soa_indic_pedid_web_comer;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INDICADOR PEDIDO WEB COMERCIAL: ' ||
                              ls_sqlerrm);
    
  END soa_pr_ind_pedid_web_comer;

  /************************************************************************************************************
  Descripcion       : Proceso de de invocacion a los procedimientos SOA para carga de data en las tablas de SOA
  Fecha Creacion    : 04/01/2013
  Autor             : DANNY AMARO
  *************************************************************************************************************/

  PROCEDURE soa_pr_ejecu_carga_data IS
  
    lscodigopais      VARCHAR2(150);
    lsvalorparametro  VARCHAR2(150);
    lsvalorparametro1 VARCHAR2(10) := '1';
  
    lsvalorparametropedcob VARCHAR2(10) := '1';
  
  BEGIN
  
    BEGIN
      SELECT val_para
        INTO lscodigopais
        FROM bas_param_pais
       WHERE cod_sist = 'SOA'
         AND cod_para = '041'
         AND nom_para = 'PaisDefault';
    EXCEPTION
      WHEN OTHERS THEN
        lscodigopais := '';
    END;
  
    BEGIN
      SELECT nvl(val_para, '0')
        INTO lsvalorparametro
        FROM bas_param_pais
       WHERE cod_pais = lscodigopais
         AND cod_sist = 'SOA'
         AND cod_para = '072'
         AND nom_para = 'indCargaConsultora';
    EXCEPTION
      WHEN no_data_found THEN
        lsvalorparametro := '0';
    END;
  
    BEGIN
      SELECT nvl(val_para, '0')
        INTO lsvalorparametro1
        FROM bas_param_pais
       WHERE cod_pais = lscodigopais
         AND cod_sist = 'SOA'
         AND cod_para = '073'
         AND nom_para = 'indCargaConsVenta';
    EXCEPTION
      WHEN no_data_found THEN
        lsvalorparametro1 := '1';
    END;
  
    BEGIN
      SELECT nvl(val_para, '0')
        INTO lsvalorparametropedcob
        FROM bas_param_pais
       WHERE cod_pais = lscodigopais
         AND cod_sist = 'SOA'
         AND cod_para = '074'
         AND nom_para = 'indCargaPediCob';
    EXCEPTION
      WHEN no_data_found THEN
        lsvalorparametropedcob := '1';
    END;
  
    dbms_mview.refresh('SOA_MV_ZONA');
    dbms_mview.refresh('SOA_MV_TERRI');
    dbms_mview.refresh('SOA_MV_TELEF_DUPLA');
    dbms_mview.refresh('SOA_MV_TELEF_CONSU');
    dbms_mview.refresh('SOA_MV_SECCI');
    dbms_mview.refresh('SOA_MV_REGIO');
    dbms_mview.refresh('SOA_MV_PRODU_COMER');
    dbms_mview.refresh('SOA_MV_MONED');
    dbms_mview.refresh('SOA_MV_MARCA');
    dbms_mview.refresh('SOA_MV_IDIOM');
    dbms_mview.refresh('SOA_MV_EMAIL_DUPLA');
    dbms_mview.refresh('SOA_MV_EMAIL_CONSU');
    dbms_mview.refresh('SOA_MV_DUPLA_CYZON');
    dbms_mview.refresh('SOA_MV_DOCUM_IDENT_DUPLA');
    dbms_mview.refresh('SOA_MV_DOCUM_IDENT_CONSU');
    dbms_mview.refresh('SOA_MV_DIREC_DUPLA');
    dbms_mview.refresh('SOA_MV_DIREC_CONSU');
    IF lsvalorparametro = '1' THEN
      dbms_mview.refresh('SOA_MV_DATOS_ESTAT_CONSU');
    END IF;
    dbms_mview.refresh('SOA_MV_CAMPA');
  
    soa_pkg_proce.soa_pr_cdr;
    IF lsvalorparametro = '1' THEN
      soa_pkg_proce.soa_pr_consu;
    END IF;
    soa_pkg_proce.soa_pr_visit;
    soa_pkg_proce.soa_pr_tipo_concu;
    soa_pkg_proce.soa_pr_concu_regal;
    IF (lsvalorparametro1 = '1') THEN
      soa_pkg_proce.soa_pr_concu_venta;
    END IF;
    soa_pkg_proce.soa_pr_prg_nueva;
    soa_pkg_proce.soa_pr_recom;
    soa_pkg_proce.soa_pr_recon;
  
    IF (lsvalorparametropedcob = '1') THEN
      soa_pkg_proce.soa_pr_pedid;
      soa_pkg_proce.soa_pr_resum_pedid;
      soa_pkg_proce.soa_pr_cobra_secci;
      soa_pkg_proce.soa_pr_cobra_deuda;
    END IF;
    soa_pkg_proce.soa_pr_ind_sicc_cobra;
    soa_pkg_proce.soa_pr_ind_sicc_lets;
    soa_pkg_proce.soa_pr_ind_sicc_meta;
    soa_pkg_proce.soa_pr_ind_sicc_peg;
    soa_pkg_proce.soa_pr_ind_pedid_comer;
    soa_pkg_proce.soa_pr_ind_pedid_web_comer;
  
  END soa_pr_ejecu_carga_data;

  /************************************************************************************************************
  Descripcion       : Proceso de de invocacion al  procedimiento SOA para carga de data en la tabla SOA_MV_CAMPA de SOA
  Fecha Creacion    : 05/09/2013
  Autor             : JPJC
  *************************************************************************************************************/

  PROCEDURE soa_pr_carga_data_mv_campa IS
  
  BEGIN
  
    dbms_mview.refresh('SOA_MV_CAMPA');
  
  END
  
  soa_pr_carga_data_mv_campa;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el estado del pedido
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_estad_pedid
  (
    pscodigoperiodo VARCHAR2,
    pnsecnumedocu   NUMBER,
    psnumlote       VARCHAR2,
    lnmontopedido   NUMBER
  ) RETURN VARCHAR2 IS
  
    ls_ind_proc_gp2            int_solic_conso_cabec.ind_proc_gp2%TYPE;
    ls_ind_erro_mtmi           int_solic_conso_cabec.ind_erro_mtmi%TYPE;
    ls_ind_erro_mtma           int_solic_conso_cabec.ind_erro_mtma%TYPE;
    ls_ind_erro_rech           int_solic_conso_cabec.ind_erro_rech%TYPE;
    ls_cod_moti_rech           int_solic_conso_cabec.cod_moti_rech%TYPE;
    ls_ind_erro_deud           int_solic_conso_cabec.ind_erro_deud %TYPE;
    ln_soca_oid_soli_cabe_refe int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
    ls_obs_prub                int_solic_conso_cabec.obs_prub%TYPE;
    ls_cod_clie                int_solic_conso_cabec.cod_clie%TYPE;
  
    lnsuma        NUMBER := 0;
    lnmontominimo NUMBER := 0;
    lnmontomaximo NUMBER := 0;
  BEGIN
  
    BEGIN
      SELECT ind_proc_gp2,
             ind_erro_mtmi,
             ind_erro_mtma,
             ind_erro_rech,
             cod_moti_rech,
             ind_erro_deud,
             soca_oid_soli_cabe_refe,
             obs_prub,
             cod_clie
        INTO ls_ind_proc_gp2,
             ls_ind_erro_mtmi,
             ls_ind_erro_mtma,
             ls_ind_erro_rech,
             ls_cod_moti_rech,
             ls_ind_erro_deud,
             ln_soca_oid_soli_cabe_refe,
             ls_obs_prub,
             ls_cod_clie
        FROM int_solic_conso_cabec
       WHERE sec_nume_docu = pnsecnumedocu
         AND num_lote = psnumlote
         AND cod_peri = pscodigoperiodo;
    EXCEPTION
      WHEN no_data_found THEN
        SELECT ind_proc_gp2,
               ind_erro_mtmi,
               ind_erro_mtma,
               ind_erro_rech,
               cod_moti_rech,
               ind_erro_deud,
               soca_oid_soli_cabe_refe,
               obs_prub,
               cod_clie
          INTO ls_ind_proc_gp2,
               ls_ind_erro_mtmi,
               ls_ind_erro_mtma,
               ls_ind_erro_rech,
               ls_cod_moti_rech,
               ls_ind_erro_deud,
               ln_soca_oid_soli_cabe_refe,
               ls_obs_prub,
               ls_cod_clie
          FROM ped_histo_solic_conso_cabec
         WHERE sec_nume_docu = pnsecnumedocu
           AND num_lote = psnumlote
           AND cod_peri = pscodigoperiodo;
      
    END;
    IF ls_ind_proc_gp2 = '1' THEN
      RETURN 'FACTURADO';
    END IF;
    IF ls_ind_erro_mtmi = '1' THEN
      RETURN 'RECHAZADO';
    END IF;
    IF ls_ind_erro_mtma = '1' THEN
      RETURN 'RECHAZADO';
    END IF;
    IF ls_ind_erro_deud = '2' THEN
      RETURN 'RECHAZADO';
    END IF;
    IF ls_ind_erro_rech = '1' AND ls_cod_moti_rech != '00' THEN
      RETURN 'RECHAZADO';
    END IF;
  
    SELECT COUNT(1)
      INTO lnsuma
      FROM ped_solic_cabec
     WHERE oid_soli_cabe = ln_soca_oid_soli_cabe_refe
       AND esso_oid_esta_soli = 7;
  
    IF lnsuma > 0 THEN
      ls_obs_prub := 'CS';
    END IF;
  
    IF ls_obs_prub = 'CS' THEN
      RETURN 'RECHAZADO';
    END IF;
  
    IF lnsuma = 0 THEN
      lnsuma := lnsuma + ls_ind_erro_mtmi;
    END IF;
  
    IF lnsuma = 0 THEN
      lnsuma := lnsuma + ls_ind_erro_mtma;
    END IF;
  
    IF lnsuma = 0 AND ls_ind_erro_rech = '1' THEN
      lnsuma := lnsuma + ls_cod_moti_rech;
    END IF;
  
    IF lnsuma = 0 AND ls_ind_erro_deud = '2' THEN
      lnsuma := lnsuma + 1;
    END IF;
  
    IF lnsuma = 0 THEN
    
      SELECT COUNT(1)
        INTO lnsuma
        FROM sto_detal_docum_excep
       WHERE sec_nume_docu = pnsecnumedocu
         AND cod_tipo_docu = 'OCC'
         AND cod_vali NOT IN ('OCC-14', 'OCC-31')
         AND ind_apro = 0;
    
      IF lnsuma > 0 THEN
        RETURN 'RECHAZADO';
      END IF;
    END IF;
  
    SELECT MAX(val_niv1) INTO lnmontominimo FROM ped_monto_minim;
  
    IF lnmontopedido < lnmontominimo THEN
      RETURN 'OBSERVADO';
    END IF;
  
    SELECT MAX(val_mont_maxi_perm)
      INTO lnmontomaximo
      FROM car_param_carte
     WHERE ind_mont_maxi = 1
       AND niri_oid_nive_ries =
           (SELECT niri_oid_nive_ries
              FROM mae_clien_datos_adici,
                   mae_clien
             WHERE clie_oid_clie = oid_clie
               AND cod_clie = ls_cod_clie);
  
    IF lnmontopedido > lnmontomaximo THEN
      RETURN 'OBSERVADO';
    END IF;
  
    RETURN 'ENVIADO';
  
  END soa_fn_estad_pedid;
  /***************************************************************************
  Descripcion       : Funcion que devuelve el origen del pedido
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_orige_pedid
  (
    ps_ind_rece_onli int_solic_conso_cabec.ind_rece_onli %TYPE,
    ps_ind_rece_ivr  int_solic_conso_cabec.ind_rece_ivr %TYPE,
    ps_ind_rece_ocr  int_solic_conso_cabec.ind_rece_ocr %TYPE,
    ps_ind_rece_web  int_solic_conso_cabec.ind_rece_web %TYPE,
    ps_ind_rece_dd   int_solic_conso_cabec.ind_rece_dd %TYPE,
    ps_ind_rece_digi int_solic_conso_cabec.ind_rece_digi %TYPE,
    ps_ind_rece_cc   int_solic_conso_cabec.ind_rece_cc %TYPE,
    ps_ind_rece_mens int_solic_conso_cabec.ind_rece_mens %TYPE
  ) RETURN VARCHAR2 IS
  
  BEGIN
  
    IF (ps_ind_rece_dd = '1' AND ps_ind_rece_digi = '0' AND
       ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '0' AND
       ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '0' AND
       ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'DD';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '1' AND
          ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '0' AND
          ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '0' AND
          ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'Digitado';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '0' AND
          ps_ind_rece_ivr = '1' AND ps_ind_rece_mens = '0' AND
          ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '0' AND
          ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'IVR';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '0' AND
          ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '1' AND
          ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '0' AND
          ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'Mensaje';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '0' AND
          ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '0' AND
          ps_ind_rece_ocr = '1' AND ps_ind_rece_onli = '0' AND
          ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'OCR';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '0' AND
          ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '0' AND
          ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '1' AND
          ps_ind_rece_web = '0' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'Online';
    
    ELSIF (ps_ind_rece_dd = '0' AND ps_ind_rece_digi = '0' AND
          ps_ind_rece_ivr = '0' AND ps_ind_rece_mens = '0' AND
          ps_ind_rece_ocr = '0' AND ps_ind_rece_onli = '0' AND
          ps_ind_rece_web = '1' AND ps_ind_rece_cc = '0') THEN
    
      RETURN 'Web';
    
    ELSE
    
      RETURN 'Mixto';
    
    END IF;
  
  END soa_fn_orige_pedid;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el motivo de rechazo
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_motiv_recha_pedid
  (
    pn_sec_nume_docu int_solic_conso_cabec.sec_nume_docu %TYPE,
    pn_ind_hist      NUMBER
  ) RETURN VARCHAR2 IS
  
    lncount NUMBER;
  
  BEGIN
    IF pn_ind_hist = 0 THEN
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali IN ('OCC-16', 'OCC-49')
         AND dde.ind_apro = '0';
    
      IF lncount > 0 THEN
        RETURN '01';
      END IF;
    
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali IN ('OCC-17', 'OCC-50')
         AND dde.ind_apro = '0';
    
      IF lncount > 0 THEN
        RETURN '02';
      END IF;
    
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-19'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '03';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-15'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '04';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-20'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '05';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-7'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '06';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_detal_docum_excep dde
       WHERE dde.cod_tipo_docu = 'OCC'
         AND dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali NOT IN ('OCC-7',
                                  'OCC-15',
                                  'OCC-16',
                                  'OCC-17',
                                  'OCC-19',
                                  'OCC-20',
                                  'OCC-49',
                                  'OCC-50')
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '07';
      END IF;
    
    ELSE
    
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali IN ('OCC-16', 'OCC-49')
         AND dde.ind_apro = '0';
    
      IF lncount > 0 THEN
        RETURN '01';
      END IF;
    
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali IN ('OCC-17', 'OCC-50')
         AND dde.ind_apro = '0';
    
      IF lncount > 0 THEN
        RETURN '02';
      END IF;
    
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-19'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '03';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-15'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '04';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-20'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '05';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali = 'OCC-7'
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '06';
      END IF;
      SELECT COUNT(1)
        INTO lncount
        FROM sto_histo_detal_docum_excep dde
       WHERE dde.cod_tipo_docu = 'OCC'
         AND dde.sec_nume_docu = pn_sec_nume_docu
         AND dde.cod_vali NOT IN ('OCC-7',
                                  'OCC-15',
                                  'OCC-16',
                                  'OCC-17',
                                  'OCC-19',
                                  'OCC-20',
                                  'OCC-49',
                                  'OCC-50')
         AND dde.ind_apro = '0';
      IF lncount > 0 THEN
        RETURN '07';
      END IF;
    END IF;
  
    RETURN ' ';
  
  END soa_fn_motiv_recha_pedid;
  /***************************************************************************
  Descripcion       : Funcion que devuelve descripcion el motivo de rechazo
  Fecha Creacion    : 02/04/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION soa_fn_descr_motiv_recha_pedid(ps_cod_moti_rech VARCHAR2)
    RETURN VARCHAR2 IS
  
  BEGIN
    CASE ps_cod_moti_rech
      WHEN '01' THEN
        RETURN 'MONTO MINIMO';
      WHEN '02' THEN
        RETURN 'MONTO MAXIMO';
      WHEN '03' THEN
        RETURN 'DEUDA';
      WHEN '04' THEN
        RETURN 'RECHAZO POR OCR';
      WHEN '05' THEN
        RETURN 'CLIENTE INEXISTENTE';
      WHEN '06' THEN
        RETURN 'SEGUNDO PEDIDO';
      WHEN '07' THEN
        RETURN 'OTROS';
      ELSE
        RETURN ' ';
    END CASE;
  
  END soa_fn_descr_motiv_recha_pedid;

  /************************************************************************************************************
  Descripcion       : Proceso que acatualiza informacion de SOA despues de la facturacion
  Fecha Creacion    : 15/10/2015
  Autor             : Jose Cairampoma
  *************************************************************************************************************/
  PROCEDURE soa_pr_cierr_factu IS
  BEGIN
    soa_pkg_proce.soa_pr_pedid;
    soa_pkg_proce.soa_pr_resum_pedid;
    soa_pkg_proce.soa_pr_cobra_secci;
    soa_pkg_proce.soa_pr_cobra_deuda;
    soa_pkg_proce.soa_pr_cdr;
  EXCEPTION
    WHEN OTHERS THEN
    
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
      -- raise_application_error(-20123, 'ERROR INFORME CDR: ' || ls_sqlerrm);
  
  END soa_pr_cierr_factu;
END soa_pkg_proce;
/
