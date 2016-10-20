CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_ocd AS
  /***************************************************************************
  Validaciones de Detalle de Orden de Compra
  Fecha Creacion    : 01/08/2011
  Autor             : Jose Cairampoma
  Parametros
            pscodigopais          : Pais
            pscodigotipodoc       : Tipo Documento (OCD)
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psusuario             : Usuario
            psnumeroproceso       : Numero proceso
  ***************************************************************************/
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /***************************************************************************
  Descripcion       : Validación de tipo de posicion sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_posic
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de codigo de venta sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_codig_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validación de codigo de venta sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 20/07/2012
  Autor             :Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_codig_venta_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validación de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_indic_noimp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ssicc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de cantidades demandadassin error
                      para Orden de compra Detalle
  Fecha Creacion    : 02/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_canti_deman
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de ofertas web sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 27/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_ofert_web
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Ofertas Web 2
                      para Orden de compra Detalle
  Fecha Creacion    : 10/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ofert_webde
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Indicador de Error - Programa de nuevas
                      para Orden de compra Detalle
  Fecha Creacion    : 09/08/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Session Experte
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ssexp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Dupla Cyzone
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_dpcyz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Dupla Cyzone
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_recha_occrr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_ACTIV_OK
                       Procedimiento de Validacion de Premio para concrso Activo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_ELECT_OK
                       Procedimiento de Validacion de Premio para concrso Electivo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_NIVEL_ELECT_OK
                       Procedimiento de Validacion de Premio para nivel electivo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_nivel_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_PERIO_OK
                       Procedimiento de Validacion de Premio para el concurso en periodo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_TIPO_CONSU_CONCU_OK
                       Procedimiento de Validacion de Premio para tipologia de consultora
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_consu_concu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_OCC_UNIDA_ADMIN_CONSU_OK
                       Procedimiento de Validacion de Premio para la unidad administrativa d ela consultora
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_unida_admin_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Limite de venta focalizado por consultora
  Fecha Creacion    : 24/05/2011
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_limit_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion rechazo venta exclusiva
  Fecha Creacion    : 16/10/2012
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_venta_exclu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion rechazo Por tipo oferta web
  Fecha Creacion    : 06/01/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_ofert_web
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_ocd;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_ocd AS

  /***************************************************************************
  Descripcion       : Validación de tipo de posicion sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_posic
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipoposicion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             tsp.tpos_oid_tipo_posi,
             tsp.stpo_oid_subt_posi
        FROM ped_tipo_solic_proce  tsp,
             bel_opera,
             int_solic_conso_cabec cons,
             int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND bel_opera.cod_oper = 'OCR005'
         AND tsp.tspa_oid_tipo_soli_pais = cons.tspa_oid_tipo_soli_pais
         AND bel_opera.oid_oper = tsp.oper_oid_oper
         AND cons.cod_pais = det.cod_pais
         AND cons.cod_peri = det.cod_peri
         AND cons.cod_clie = det.cod_clie
         AND cons.num_lote = det.num_lote;

    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oidtipoposi IS TABLE OF ped_tipo_solic_proce.tpos_oid_tipo_posi%TYPE;
    TYPE t_oidsubtipoposi IS TABLE OF ped_tipo_solic_proce.stpo_oid_subt_posi%TYPE;

    v_num_lote       t_num_lote;
    v_sec_nume_docu  t_sec_nume_docu;
    v_oidtipoposi    t_oidtipoposi;
    v_oidsubtipoposi t_oidsubtipoposi;

    w_filas NUMBER := 1000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_tipoposicion;
    LOOP
      FETCH c_tipoposicion BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oidtipoposi,
             v_oidsubtipoposi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_detal
             SET tpos_oid_tipo_posi = v_oidtipoposi(i),
                 stpo_oid_subt_posi = v_oidsubtipoposi(i)
           WHERE num_lote = v_num_lote(i)
             AND sec_nume_docu = v_sec_nume_docu(i);

        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_tipoposicion%NOTFOUND;

    END LOOP;
    CLOSE c_tipoposicion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_TIPO_POSIC: ' || ls_sqlerrm);

  END sto_pr_ocd_tipo_posic;

  /***************************************************************************
  Descripcion       : Validación de codigo de venta sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008

  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_codig_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_periodos IS
      SELECT c.cod_peri,
             c.perd_oid_peri
        FROM sto_proce_docum_digit t,
             int_solic_conso_cabec c
       WHERE c.sec_nume_docu = t.sec_nume_docu_cabe
         AND c.num_lote = t.num_lote
         AND t.cod_tipo_docu = pscodigotipodoc
         AND t.cod_pais = pscodigopais
         AND t.num_proc = psnumeroproceso
         AND c.perd_oid_peri IS NOT NULL
       GROUP BY c.cod_peri,
                c.perd_oid_peri;

    TYPE t_gcod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_gperd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;

    v_gcod_peri      t_gcod_peri;
    v_gperd_oid_peri t_gperd_oid_peri;

    CURSOR c_codigoventa
    (
      vnmaxicoveprod  NUMBER,
      vnminicoveprod  NUMBER,
      vnmaxicoveprem  NUMBER,
      vnminicoveprem  NUMBER,
      vscodigoperiodo NUMBER,
      vnoidperiodo    NUMBER
    ) IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             pod.prod_oid_prod oid_prod,
             pod.fopa_oid_form_pago oid_form_pago,
             pod.oid_deta_ofer oid_deta_ofer,
             NULL oid_para_nive_prem,
             NULL num_prem,
             NULL oid_para_gral,
             (SELECT COUNT(f.oid_tipo_ofer_excl)
                FROM fac_tipo_ofert_exclu f,
                     pre_ofert_detal      d
               WHERE d.oid_deta_ofer = pod.oid_deta_ofer
                 AND d.tofe_oid_tipo_ofer = f.tofe_oid_tipo_ofer
                 AND rownum = 1) ind_no_impr,
             pod.precio_unitario val_prec_unit,
             pod.val_fact_repe val_fact_repe,
             po.acce_oid_acce acce_oid_acce,
             det.cod_peri,
             vnoidperiodo
        FROM pre_ofert_detal       pod,
             pre_ofert             po,
             pre_matri_factu_cabec pmf,
             int_solic_conso_detal det,
             sto_proce_docum_digit dd
       WHERE dd.sec_nume_docu = det.sec_nume_docu
         AND dd.num_lote = det.num_lote
         AND dd.num_proc = psnumeroproceso
         AND dd.cod_tipo_docu = pscodigotipodoc
         AND dd.cod_pais = pscodigopais
         AND pod.ofer_oid_ofer = po.oid_ofer
         AND pod.val_codi_vent = det.cod_vent
         AND po.mfca_oid_cabe = pmf.oid_cabe
         AND pmf.perd_oid_peri = vnoidperiodo
         AND det.cod_peri = vscodigoperiodo
         AND (pod.ind_digi = 1 OR det.sta_proc IN ('B',
                                                   'C',
                                                   'L',
                                                   'R'))
         AND det.val_unid_dem > 0
         AND det.cod_vent >= vnminicoveprod
         AND det.cod_vent <= vnmaxicoveprod
      UNION ALL
      SELECT det.num_lote,
             det.sec_nume_docu,
             NULL                      oid_prod,
             NULL                      oid_form_pago,
             NULL                      oid_deta_ofer,
             c.panp_oid_para_nive_prem oid_para_nive_prem,
             b.num_prem                num_prem,
             f.oid_para_gral           oid_para_gral,
             NULL                      ind_no_impr,
             NULL                      val_prec_unit,
             NULL                      val_fact_repe,
             NULL                      acce_oid_acce,
             det.cod_peri,
             vnoidperiodo
        FROM inc_artic_lote        a,
             inc_lote_premi_artic  b,
             inc_premi_artic       c,
             inc_param_nivel_premi d,
             inc_param_gener_premi e,
             inc_concu_param_gener f,
             int_solic_conso_detal det,
             sto_proce_docum_digit dd
       WHERE dd.sec_nume_docu = det.sec_nume_docu
         AND dd.num_lote = det.num_lote
         AND dd.num_proc = psnumeroproceso
         AND dd.cod_tipo_docu = pscodigotipodoc
         AND dd.cod_pais = pscodigopais
         AND a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
         AND b.prar_oid_prem_arti = c.oid_prem_arti
         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
         AND e.copa_oid_para_gral = f.oid_para_gral
         AND f.ind_acti = 1
         --AND f.bcal_oid_base_calc NOT IN (3, 4)
         AND ((f.bcal_oid_base_calc NOT IN (3,4)) or (f.bcal_oid_base_calc = 4 and e.tprm_oid_tipo_pion = 1 ) )
         AND a.cod_vent_fict = det.cod_vent
         AND det.cod_peri = vscodigoperiodo
         AND det.val_unid_dem > 0
         AND det.cod_vent >= vnminicoveprem
         AND det.cod_vent <= vnmaxicoveprem;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_oid_prod IS TABLE OF pre_ofert_detal.prod_oid_prod%TYPE;
    TYPE t_oid_form_pago IS TABLE OF pre_ofert_detal.fopa_oid_form_pago%TYPE;
    TYPE t_oid_deta_ofer IS TABLE OF pre_ofert_detal.oid_deta_ofer%TYPE;
    TYPE t_oid_para_nive_prem IS TABLE OF inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF inc_lote_premi_artic.num_prem%TYPE;
    TYPE t_oid_para_gral IS TABLE OF inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    TYPE t_ind_no_impr IS TABLE OF NUMBER;
    TYPE t_val_prec_unit IS TABLE OF pre_ofert_detal.precio_unitario%TYPE;
    TYPE t_val_fact_repe IS TABLE OF pre_ofert_detal.val_fact_repe%TYPE;
    TYPE t_acce_oid_acce IS TABLE OF pre_ofert.acce_oid_acce%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;

    v_numlote            t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_prod           t_oid_prod;
    v_oid_form_pago      t_oid_form_pago;
    v_oid_deta_ofer      t_oid_deta_ofer;
    v_oid_para_nive_prem t_oid_para_nive_prem;
    v_num_prem           t_num_prem;
    v_oid_para_gral      t_oid_para_gral;
    v_ind_no_impr        t_ind_no_impr;
    v_val_prec_unit      t_val_prec_unit;
    v_val_fact_repe      t_val_fact_repe;
    v_acce_oid_acce      t_acce_oid_acce;
    v_cod_peri           t_cod_peri;
    v_perd_oid_peri      t_perd_oid_peri;

    w_filas NUMBER := 10000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

    lnmaxicoveprod NUMBER;
    lnminicoveprod NUMBER;
    lnmaxicoveprem NUMBER;
    lnminicoveprem NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lnmaxicoveprod := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MAXI_COVE_PROD');
    lnminicoveprod := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MINI_COVE_PROD');
    lnmaxicoveprem := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MAXI_COVE_PREM');
    lnminicoveprem := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MINI_COVE_PREM');

    OPEN c_periodos;
    LOOP
      FETCH c_periodos BULK COLLECT
        INTO v_gcod_peri,
             v_gperd_oid_peri LIMIT w_filas;

      IF v_gcod_peri.count > 0 THEN

        FOR j IN v_gcod_peri.first .. v_gcod_peri.last
        LOOP
          OPEN c_codigoventa(lnmaxicoveprod,
                             lnminicoveprod,
                             lnmaxicoveprem,
                             lnminicoveprem,
                             v_gcod_peri(j),
                             v_gperd_oid_peri(j));
          LOOP
            FETCH c_codigoventa BULK COLLECT
              INTO v_numlote,
                   v_sec_nume_docu,
                   v_oid_prod,
                   v_oid_form_pago,
                   v_oid_deta_ofer,
                   v_oid_para_nive_prem,
                   v_num_prem,
                   v_oid_para_gral,
                   v_ind_no_impr,
                   v_val_prec_unit,
                   v_val_fact_repe,
                   v_acce_oid_acce,
                   v_cod_peri,
                   v_perd_oid_peri LIMIT w_filas;

            IF v_sec_nume_docu.count > 0 THEN
              FORALL i IN 1 .. v_sec_nume_docu.count
                UPDATE int_solic_conso_detal
                   SET prod_oid_prod           = v_oid_prod(i),
                       fopa_oid_form_pago      = v_oid_form_pago(i),
                       ofde_oid_deta_ofer      = v_oid_deta_ofer(i),
                       panp_oid_para_nive_prem = v_oid_para_nive_prem(i),
                       num_prem                = v_num_prem(i),
                       copa_oid_para_gral      = v_oid_para_gral(i),
                       ind_no_impr             = v_ind_no_impr(i),
                       val_prec_cata_unit_loca = v_val_prec_unit(i),
                       val_fact_repe           = v_val_fact_repe(i),
                       acce_oid_acce           = v_acce_oid_acce(i),
                       tpos_oid_tipo_posi      = CASE
                                                   WHEN sta_proc IN ('C',
                                                                     'L') THEN
                                                    2031
                                                   ELSE
                                                    1
                                                 END,
                       stpo_oid_subt_posi      = CASE
                                                   WHEN sta_proc IN ('C',
                                                                     'L') THEN
                                                    2033
                                                   ELSE
                                                    1
                                                 END,
                       des_prod               =
                       (SELECT gen.val_i18n
                          FROM pre_matri_factu       matriz,
                               pre_ofert_detal       ofde,
                               pre_matri_factu_cabec cab,
                               gen_i18n_sicc_pais    gen
                         WHERE gen.attr_enti = 'MAE_PRODU'
                           AND gen.val_oid = ofde.prod_oid_prod
                           AND matriz.ofde_oid_deta_ofer = ofde.oid_deta_ofer
                           AND ofde.val_codi_vent = cod_vent
                           and gen.idio_oid_idio=1
                           AND cab.perd_oid_peri = v_perd_oid_peri(i)
                           AND matriz.mfca_oid_cabe = cab.oid_cabe)
                 WHERE num_lote = v_numlote(i)
                   AND sec_nume_docu = v_sec_nume_docu(i);

              -- Actualizamos Documentos Validados OK
              FORALL i IN 1 .. v_sec_nume_docu.count
                UPDATE sto_docum_digit occ
                   SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                       occ.cod_ulti_vali_exit = pscodigovalidactual,
                       occ.usu_modi           = psusuario,
                       occ.fec_modi           = SYSDATE
                 WHERE occ.cod_pais = pscodigopais
                   AND occ.cod_tipo_docu = pscodigotipodoc
                   AND occ.num_lote = v_numlote(i)
                   AND occ.sec_nume_docu = v_sec_nume_docu(i);

            END IF;

            EXIT WHEN c_codigoventa%NOTFOUND;
          END LOOP;
          CLOSE c_codigoventa;

        END LOOP;
      END IF;
      EXIT WHEN c_periodos%NOTFOUND;

    END LOOP;
    CLOSE c_periodos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CODIG_VENTA: ' || ls_sqlerrm);

  END sto_pr_ocd_codig_venta;
  /***************************************************************************
  Descripcion       : Validación de codigo de venta sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 20/07/2012
  Autor             :Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_codig_venta_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_codigoventa IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_vent,
             det.val_unid_dem,
             det.sta_proc,
             NULL              oid_prod,
             NULL              oid_form_pago,
             NULL              oid_deta_ofer,
             NULL              oid_para_nive_prem,
             NULL              num_prem,
             NULL              oid_para_gral,
             NULL              ind_no_impr,
             NULL              val_prec_unit,
             NULL              val_prec_unit_rang,
             NULL              val_prec_cont,
             NULL              val_fact_repe,
             NULL              acce_oid_acce,
             nvl(cab.ind_vali_prol,0),
             cab.perd_oid_peri,
             det.ind_recu_prol
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit dd,
             int_solic_conso_cabec cab
       WHERE dd.sec_nume_docu = det.sec_nume_docu
         AND dd.num_lote = det.num_lote
         --
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_lote = cab.num_lote
         --
         AND dd.num_proc = psnumeroproceso
         AND dd.cod_tipo_docu = pscodigotipodoc
         AND dd.cod_pais = pscodigopais
         AND det.val_unid_dem > 0;

    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_detal.sta_proc%TYPE;
    TYPE t_oid_prod IS TABLE OF pre_ofert_detal.prod_oid_prod%TYPE;
    TYPE t_oid_form_pago IS TABLE OF pre_ofert_detal.fopa_oid_form_pago%TYPE;
    TYPE t_oid_deta_ofer IS TABLE OF pre_ofert_detal.oid_deta_ofer%TYPE;
    TYPE t_oid_para_nive_prem IS TABLE OF inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF inc_lote_premi_artic.num_prem%TYPE;
    TYPE t_oid_para_gral IS TABLE OF inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    TYPE t_ind_no_impr IS TABLE OF NUMBER;
    TYPE t_val_prec_unit IS TABLE OF pre_ofert_detal.precio_unitario%TYPE;
    TYPE t_val_prec_unit_rang IS TABLE OF pre_ofert_detal.precio_unitario%TYPE;
    TYPE t_val_prec_cont IS TABLE OF pre_ofert_detal.imp_prec_posi%TYPE;
    TYPE t_val_fact_repe IS TABLE OF pre_ofert_detal.val_fact_repe%TYPE;
    TYPE t_acce_oid_acce IS TABLE OF pre_ofert.acce_oid_acce%TYPE;
    TYPE t_ind_vali_prol IS TABLE OF int_solic_conso_cabec.ind_vali_prol%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_ind_recu_prol IS TABLE OF int_solic_conso_detal.ind_recu_prol%TYPE;

    v_num_lote           t_num_lote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_cod_vent           t_cod_vent;
    v_val_unid_dem       t_val_unid_dem;
    v_sta_proc           t_sta_proc;
    v_oid_prod           t_oid_prod;
    v_oid_form_pago      t_oid_form_pago;
    v_oid_deta_ofer      t_oid_deta_ofer;
    v_oid_para_nive_prem t_oid_para_nive_prem;
    v_num_prem           t_num_prem;
    v_oid_para_gral      t_oid_para_gral;
    v_ind_no_impr        t_ind_no_impr;
    v_val_prec_unit      t_val_prec_unit;
    v_val_prec_unit_rang      t_val_prec_unit_rang;
    v_val_prec_cont      t_val_prec_cont;
    v_val_fact_repe      t_val_fact_repe;
    v_acce_oid_acce      t_acce_oid_acce;
    v_ind_vali_prol      t_ind_vali_prol;
    v_perd_oid_peri      t_perd_oid_peri;
    v_ind_recu_prol      t_ind_recu_prol;

    w_filas NUMBER := 10000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

    lnmaxicoveprod NUMBER;
    lnminicoveprod NUMBER;
    lnmaxicoveprem NUMBER;
    lnminicoveprem NUMBER;

    lbcuvvalido BOOLEAN;

    lsindvaliprol bas_param_pais.val_para%TYPE;
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lnmaxicoveprod := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MAXI_COVE_PROD');
    lnminicoveprod := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MINI_COVE_PROD');
    lnmaxicoveprem := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MAXI_COVE_PREM');
    lnminicoveprem := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MINI_COVE_PREM');

    lsindvaliprol := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                     'PED',
                                                     '012');
    OPEN c_codigoventa;
          LOOP
            FETCH c_codigoventa BULK COLLECT
              INTO v_num_lote,
                   v_sec_nume_docu,
                   v_cod_vent,
             v_val_unid_dem,
                   v_sta_proc,
                   v_oid_prod,
                   v_oid_form_pago,
                   v_oid_deta_ofer,
                   v_oid_para_nive_prem,
                   v_num_prem,
                   v_oid_para_gral,
                   v_ind_no_impr,
                   v_val_prec_unit,
             v_val_prec_unit_rang,
             v_val_prec_cont,
                   v_val_fact_repe,
                   v_acce_oid_acce,
             v_ind_vali_prol,
             v_perd_oid_peri,
             v_ind_recu_prol LIMIT w_filas;

            IF v_sec_nume_docu.count > 0 THEN
              FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
              LOOP
                lbcuvvalido := TRUE;
                IF (v_cod_vent(i) >= lnminicoveprod AND v_cod_vent(i) <= lnmaxicoveprod) THEN
                  BEGIN
                    SELECT pod.prod_oid_prod,
                           pod.fopa_oid_form_pago,
                           pod.oid_deta_ofer,
                           (SELECT COUNT(f.oid_tipo_ofer_excl)
                              FROM fac_tipo_ofert_exclu f,
                                   pre_ofert_detal      d
                             WHERE d.oid_deta_ofer = pod.oid_deta_ofer
                               AND d.tofe_oid_tipo_ofer = f.tofe_oid_tipo_ofer
                               AND rownum = 1),
                           pod.precio_unitario,
                     (select max(x.val_prec_cata) from pre_detal_ofert_preci x where x.ofde_oid_deta_ofer=pod.oid_deta_ofer
                         and x.num_unid_desd<=v_val_unid_dem(i)
                         and x.num_unid_hast>=v_val_unid_dem(i)
                         ) precio_rang,
                     pod.imp_prec_posi,
                           pod.val_fact_repe,
                           po.acce_oid_acce
                      INTO v_oid_prod(i),
                           v_oid_form_pago(i),
                           v_oid_deta_ofer(i),
                           v_ind_no_impr(i),
                           v_val_prec_unit(i),
                     v_val_prec_unit_rang(i),
                     v_val_prec_cont(i),
                           v_val_fact_repe(i),
                           v_acce_oid_acce(i)
                      FROM pre_ofert_detal       pod,
                           pre_ofert             po,
                           pre_matri_factu_cabec pmf
                     WHERE pod.ofer_oid_ofer = po.oid_ofer
                       AND pod.val_codi_vent = v_cod_vent(i)
                       AND po.mfca_oid_cabe = pmf.oid_cabe
                 AND pmf.perd_oid_peri = v_perd_oid_peri(i)
                 AND ((lsindvaliprol = 'S' AND
                     (v_ind_vali_prol(i) = '1' OR
                     (v_ind_vali_prol(i) = '0' AND
                     (pod.ind_digi = 1 OR
                           v_sta_proc(i) IN ('B',
                                              'C',
                                              'L',
                                            'R'))))) OR
                     (lsindvaliprol != 'S' AND pod.ind_digi = 1 OR
                     v_sta_proc(i) IN ('B',
                                         'C',
                                         'L',
                                         'R'))
                                         or (v_ind_recu_prol(i)='2')
                                         );

                  EXCEPTION
                    WHEN no_data_found THEN

                      lbcuvvalido := FALSE;
                  END;
                ELSIF v_cod_vent(i) >= lnminicoveprem AND v_cod_vent(i) <= lnmaxicoveprem THEN
                  BEGIN
                    SELECT c.panp_oid_para_nive_prem oid_para_nive_prem,
                           b.num_prem                num_prem,
                           f.oid_para_gral           oid_para_gral
                      INTO v_oid_para_nive_prem(i),
                           v_num_prem(i),
                           v_oid_para_gral(i)
                      FROM inc_artic_lote        a,
                           inc_lote_premi_artic  b,
                           inc_premi_artic       c,
                           inc_param_nivel_premi d,
                           inc_param_gener_premi e,
                           inc_concu_param_gener f
                     WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                       AND b.prar_oid_prem_arti = c.oid_prem_arti
                       AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                       AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                       AND e.copa_oid_para_gral = f.oid_para_gral
                       --and e.ind_prem_elec=1
                       And d.val_nive_sele=1
                       AND f.ind_acti = 1
                 AND ((f.bcal_oid_base_calc NOT IN (3,4)) or (f.bcal_oid_base_calc = 4 and e.tprm_oid_tipo_pion = 1 ))
                       AND a.cod_vent_fict = v_cod_vent(i);
                  EXCEPTION
                    WHEN no_data_found THEN
                      lbcuvvalido := FALSE;
                  END;
                ELSE
                  lbcuvvalido := FALSE;
                END IF;
                IF lbcuvvalido THEN
                  UPDATE int_solic_conso_detal
                     SET prod_oid_prod           = v_oid_prod(i),
                         fopa_oid_form_pago      = v_oid_form_pago(i),
                         ofde_oid_deta_ofer      = v_oid_deta_ofer(i),
                         panp_oid_para_nive_prem = v_oid_para_nive_prem(i),
                         num_prem                = v_num_prem(i),
                         copa_oid_para_gral      = v_oid_para_gral(i),
                         ind_no_impr             = v_ind_no_impr(i),
                   val_prec_cata_unit_loca = nvl(v_val_prec_unit_rang(i),v_val_prec_unit(i)),
                   val_prec_cont_unit_loca = v_val_prec_cont(i),
                         val_fact_repe           = v_val_fact_repe(i),
                         acce_oid_acce           = v_acce_oid_acce(i),
                         tpos_oid_tipo_posi      = CASE
                                                     WHEN sta_proc IN ('C',
                                                                       'L') THEN
                                                      2031
                                                     ELSE
                                                      1
                                                   END,
                         stpo_oid_subt_posi      = CASE
                                                     WHEN sta_proc IN ('C',
                                                                       'L') THEN
                                                      2033
                                                     ELSE
                                                      1
                                                   END,
                         des_prod               =
                         (SELECT gen.val_i18n
                      FROM gen_i18n_sicc_pais    gen
                           WHERE gen.attr_enti = 'MAE_PRODU'
                       AND gen.val_oid = v_oid_prod(i) and gen.idio_oid_idio=1
                       )
                   WHERE num_lote = v_num_lote(i)
                     AND sec_nume_docu = v_sec_nume_docu(i);

                  -- Actualizamos Documentos Validados OK
                  UPDATE sto_docum_digit occ
                     SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                         occ.cod_ulti_vali_exit = pscodigovalidactual,
                         occ.usu_modi           = psusuario,
                         occ.fec_modi           = SYSDATE
                   WHERE occ.cod_pais = pscodigopais
                     AND occ.cod_tipo_docu = pscodigotipodoc
                     AND occ.num_lote = v_num_lote(i)
                     AND occ.sec_nume_docu = v_sec_nume_docu(i);
                END IF;
              END LOOP;
            END IF;

            EXIT WHEN c_codigoventa%NOTFOUND;
          END LOOP;
          CLOSE c_codigoventa;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CODIG_VENTA_DETAL: ' || ls_sqlerrm);

  END sto_pr_ocd_codig_venta_detal;
  /***************************************************************************
  Descripcion       : Validación de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_indic_noimp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_codigoventa IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             (SELECT COUNT(f.oid_tipo_ofer_excl)
                FROM fac_tipo_ofert_exclu f,
                     pre_ofert_detal      d
               WHERE d.oid_deta_ofer = det.ofde_oid_deta_ofer
                 AND d.tofe_oid_tipo_ofer = f.tofe_oid_tipo_ofer
                 AND rownum = 1)
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cont IS TABLE OF NUMBER;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cont          t_cont;

    w_filas NUMBER := 1000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_codigoventa;
    LOOP
      FETCH c_codigoventa BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cont LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_detal
             SET ind_no_impr = v_cont(i)
           WHERE num_lote = v_numlote(i)
             AND sec_nume_docu = v_sec_nume_docu(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_codigoventa%NOTFOUND;
    END LOOP;
    CLOSE c_codigoventa;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_INDIC_NOIMP: ' || ls_sqlerrm);

  END sto_pr_ocd_indic_noimp;

  /***************************************************************************
  Descripcion       : Validación de SSICC
                      para Orden de compra Detalle
  Fecha Creacion    : 13/06/2008
  Autor             : Jose Antonio Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ssicc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND nvl(det.ind_erro_rech,
                 '0') = '0'
         AND nvl(det.ind_erro_sse,
                 '0') = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000;
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_VALID_SSICC: ' || ls_sqlerrm);

  END sto_pr_ocd_valid_ssicc;

  /***************************************************************************
  Descripcion       : Validación de cantidades demandadassin error
                      para Orden de compra Detalle
  Fecha Creacion    : 02/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_canti_deman
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

   /* CURSOR c_promedioventas
    (
      lsvalfr1     NUMBER,
      lsvalfr2     NUMBER,
      vnlimite1fr1 NUMBER,
      vnlimitefr2  NUMBER,
      lsigualfr2   NUMBER
    ) IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             cab.num_lote,
             cab.tspa_oid_tipo_soli_pais,
             cab.tspa_oid_tipo_soli_pais_cons,
             cab.clie_oid_clie
        FROM (SELECT DISTINCT d.cod_pais,
                              d.cod_peri,
                              d.cod_clie,
                              d.num_lote
                FROM int_solic_conso_detal d,
                     sto_proce_docum_digit occ
               WHERE occ.num_proc = psnumeroproceso
                 AND occ.sec_nume_docu = d.sec_nume_docu
                 AND occ.num_lote = d.num_lote
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND ((d. val_fact_repe > lsvalfr2 AND
                      nvl(d.val_unid_dema_ante,
                              d.val_unid_dem) >= vnlimitefr2 AND
                      ((nvl(d.val_unid_dema_ante,
                                d.val_unid_dem) >= d.val_fact_repe AND lsigualfr2 = 0) OR
                      (nvl(d.val_unid_dema_ante,
                                d.val_unid_dem) = d.val_fact_repe AND lsigualfr2 = 1))) OR
                     (d. val_fact_repe = lsvalfr1 AND
                      nvl(d.val_unid_dema_ante,
                              d.val_unid_dem) >= vnlimite1fr1))) det,
             int_solic_conso_cabec cab
       WHERE det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_lote = cab.num_lote;

    TYPE tc_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais %TYPE;
    TYPE tc_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri %TYPE;
    TYPE tc_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie %TYPE;
    TYPE tc_num_lote IS TABLE OF int_solic_conso_cabec.num_lote %TYPE;
    TYPE tc_tspa_oid_tipo_soli_pais IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais %TYPE;
    TYPE tc_tspa_oid_tipo_soli_cons IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais_cons %TYPE;
    TYPE tc_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie %TYPE;

    vc_cod_pais                tc_cod_pais;
    vc_cod_peri                tc_cod_peri;
    vc_cod_clie                tc_cod_clie;
    vc_num_lote                tc_num_lote;
    vc_tspa_oid_tipo_soli_pais tc_tspa_oid_tipo_soli_pais;
    vc_tspa_oid_tipo_soli_cons tc_tspa_oid_tipo_soli_cons;
    vc_clie_oid_clie           tc_clie_oid_clie;*/

    CURSOR c_tipoposicion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             cons.val_tota_paga_prom,
             det.val_fact_repe,
             nvl(det.val_unid_dema_ante,
                 det.val_unid_dem),
             cons.clie_oid_clie,
             cons.tspa_oid_tipo_soli_pais,
             cons.tspa_oid_tipo_soli_pais_cons,
             cons.ind_vali_prol
        FROM int_solic_conso_cabec cons,
             int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND cons.cod_pais = det.cod_pais
         AND cons.cod_peri = det.cod_peri
         AND cons.cod_clie = det.cod_clie
         AND cons.num_lote = det.num_lote;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_val_tota_paga_prom IS TABLE OF int_solic_conso_cabec.val_tota_paga_prom%TYPE;
    TYPE t_val_fact_repe IS TABLE OF int_solic_conso_detal.val_fact_repe%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_tspa_oid_tipo_soli_pais IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais%TYPE;
    TYPE t_tspa_oid_tipo_soli_pais_cons IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais_cons%TYPE;
    TYPE t_ind_vali_prol IS TABLE OF int_solic_conso_cabec.ind_vali_prol%TYPE;

    v_numlote                      t_numlote;
    v_sec_nume_docu                t_sec_nume_docu;
    v_val_tota_paga_prom           t_val_tota_paga_prom;
    v_val_fact_repe                t_val_fact_repe;
    v_val_unid_dem                 t_val_unid_dem;
    v_clie_oid_clie                t_clie_oid_clie;
    v_tspa_oid_tipo_soli_pais      t_tspa_oid_tipo_soli_pais;
    v_tspa_oid_tipo_soli_pais_cons t_tspa_oid_tipo_soli_pais_cons;
    v_ind_vali_prol                t_ind_vali_prol;

    w_filas NUMBER := 1000; -- Numero de filas a procesar cada vez
    --i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

    --lsvalfr1        sto_param_gener_occrr.val_param%TYPE;
    --lsdivlimite1fr1 sto_param_gener_occrr.val_param%TYPE;
    --lsdivlimite2fr1 sto_param_gener_occrr.val_param%TYPE;
    --lslimite1fr1    sto_param_gener_occrr.val_param%TYPE;
    --lslimite2fr1    sto_param_gener_occrr.val_param%TYPE;

    lsvalfr2    sto_param_gener_occrr.val_param%TYPE;
    lslimitefr2 sto_param_gener_occrr.val_param%TYPE;

    --lnventa          ped_solic_cabec.val_tota_paga_loca%TYPE;
    --lntotalregistros NUMBER := 0;
    --lndescuento      rec_linea_opera_recla.imp_abon%TYPE;
    --lscampanas       sto_param_gener_occrr.val_param%TYPE;
    --lspromedioventa  sto_param_gener_occrr.val_param%TYPE;
    lsigualfr2       sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /*lsvalfr1        := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR1_VAL');
    lsdivlimite1fr1 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR1_DIV_LIM1');
    lsdivlimite2fr1 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR1_DIV_LIM2');
    lslimite1fr1    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR1_LIM1');
    lslimite2fr1    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR1_LIM2');*/
    lsvalfr2        := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR2_VAL');
    lslimitefr2     := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR2_LIM');
    /*lspromedioventa := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_PROMEDIO_VENTA');
    lscampanas      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_CAMPANAS');*/
    lsigualfr2      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_FR2_IGUA');

    /*OPEN c_promedioventas(lsvalfr1,
                          lsvalfr2,
                          lslimite1fr1,
                          lslimitefr2,
                          lsigualfr2);
    LOOP
      FETCH c_promedioventas BULK COLLECT
        INTO vc_cod_pais,
             vc_cod_peri,
             vc_cod_clie,
             vc_num_lote,
             vc_tspa_oid_tipo_soli_pais,
             vc_tspa_oid_tipo_soli_cons,
             vc_clie_oid_clie LIMIT w_filas;

      IF vc_cod_pais.count > 0 THEN
        FOR i IN vc_cod_pais.first .. vc_cod_pais.last
        LOOP

          SELECT SUM(con.val_tota_paga_loca) venta,
                 COUNT(1) total
            INTO lnventa,
                 lntotalregistros
            FROM ped_solic_cabec con,
                 ped_solic_cabec ped
           WHERE con.oid_soli_cabe = ped.soca_oid_soli_cabe
             AND ped.tspa_oid_tipo_soli_pais = vc_tspa_oid_tipo_soli_pais(i)
             AND con.tspa_oid_tipo_soli_pais = vc_tspa_oid_tipo_soli_cons(i)
             AND con.perd_oid_peri IN
                 (SELECT a.oid_peri
                    FROM (SELECT z1.oid_peri,
                                 z2.cod_peri
                            FROM cra_perio       z1,
                                 seg_perio_corpo z2
                           WHERE z1.peri_oid_peri = z2.oid_peri
                           ORDER BY z2.cod_peri DESC) a
                   WHERE a.cod_peri < vc_cod_peri(i)
                     AND rownum < (to_number(lscampanas) + 1))
             AND con.clie_oid_clie = vc_clie_oid_clie(i);

          lndescuento := sto_pkg_gener.sto_fn_devue_descu(pscodigopais,
                                                          vc_tspa_oid_tipo_soli_pais(i),
                                                          vc_tspa_oid_tipo_soli_cons(i),
                                                          vc_cod_peri(i),
                                                          vc_clie_oid_clie(i));

          IF (lntotalregistros IS NOT NULL) THEN

            UPDATE int_solic_conso_cabec
               SET val_tota_paga_prom = nvl((lnventa - lndescuento) / lntotalregistros,
                                            0)
             WHERE cod_pais = vc_cod_pais(i)
               AND cod_peri = vc_cod_peri(i)
               AND cod_clie = vc_cod_clie(i)
               AND num_lote = vc_num_lote(i);
          ELSE
            UPDATE int_solic_conso_cabec
               SET val_tota_paga_prom = 0
             WHERE cod_pais = vc_cod_pais(i)
               AND cod_peri = vc_cod_peri(i)
               AND cod_clie = vc_cod_clie(i)
               AND num_lote = vc_num_lote(i);

          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_promedioventas%NOTFOUND;

    END LOOP;
    CLOSE c_promedioventas;*/

    OPEN c_tipoposicion;
    LOOP
      FETCH c_tipoposicion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_val_tota_paga_prom,
             v_val_fact_repe,
             v_val_unid_dem,
             v_clie_oid_clie,
             v_tspa_oid_tipo_soli_pais,
             v_tspa_oid_tipo_soli_pais_cons,
             v_ind_vali_prol
             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          BEGIN
            /*IF (v_val_fact_repe(j) IS NOT NULL) THEN
              IF (v_val_fact_repe(j) = lsvalfr1) THEN

                IF (v_val_unid_dem(j) >= to_number(lslimite1fr1)) THEN

                  IF (to_number(lspromedioventa) > v_val_tota_paga_prom(j)) THEN

                    IF (to_number(lslimite1fr1) <= v_val_unid_dem(j) AND
                       v_val_unid_dem(j) <= to_number(lslimite2fr1)) THEN

                      UPDATE int_solic_conso_detal
                         SET val_unid_dema_ante = v_val_unid_dem(j),
                             val_unid_dem       = trunc(v_val_unid_dem(j) /
                                                        to_number(lsdivlimite1fr1))
                       WHERE num_lote = v_numlote(j)
                         AND sec_nume_docu = v_sec_nume_docu(j);

                    END IF;

                    IF (to_number(lslimite2fr1) < v_val_unid_dem(j)) THEN

                      UPDATE int_solic_conso_detal
                         SET val_unid_dema_ante = v_val_unid_dem(j),
                             val_unid_dem       = trunc(v_val_unid_dem(j) /
                                                        to_number(lsdivlimite2fr1))
                       WHERE num_lote = v_numlote(j)
                         AND sec_nume_docu = v_sec_nume_docu(j);

                    END IF;
                  END IF;
                END IF;

              END IF;*/

              IF (v_val_fact_repe(j) > lsvalfr2 AND v_val_unid_dem(j) >= to_number(lslimitefr2) and nvl(v_ind_vali_prol(j),'0')<>'1') THEN

                IF ((v_val_unid_dem(j) >= v_val_fact_repe(j) AND to_number(lsigualfr2) = 0) OR
                   (v_val_unid_dem(j) = v_val_fact_repe(j) AND to_number(lsigualfr2) = 1)) THEN

                  --IF (to_number(lspromedioventa) > v_val_tota_paga_prom(j)) THEN

                    UPDATE int_solic_conso_detal
                       SET val_unid_dema_ante = v_val_unid_dem(j),
                           val_unid_dem       = trunc(v_val_unid_dem(j) / v_val_fact_repe(j))
                     WHERE num_lote = v_numlote(j)
                       AND sec_nume_docu = v_sec_nume_docu(j);

                  --END IF;
                END IF;

              END IF;

            --END IF;

          END;

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_tipoposicion%NOTFOUND;

    END LOOP;
    CLOSE c_tipoposicion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CANTI_DEMAN: ' || ls_sqlerrm);

  END sto_pr_ocd_canti_deman;

  /***************************************************************************
  Descripcion       : Validación de ofertas web sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 27/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_ofert_web
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion(vnoidacceso NUMBER) IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND (det.acce_oid_acce <> vnoidacceso OR det.acce_oid_acce IS NULL)
      UNION ALL
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND det.acce_oid_acce = vnoidacceso
         AND det.ind_rece_ocr = '0'
         AND det.ind_rece_digi = '0'
         AND det.ind_rece_dd = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas     NUMBER := 5000;
    i           BINARY_INTEGER := 0;
    lnoidacceso sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lnoidacceso := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                  'STO_ACCE_WEB'));

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion(lnoidacceso);
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_OFERT_WEB: ' || ls_sqlerrm);

  END sto_pr_ocd_ofert_web;

  /***************************************************************************
  Descripcion       : Validación de Ofertas Web 2
                      para Orden de compra Detalle
  Fecha Creacion    : 10/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ofert_webde
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion(vnoidacceso NUMBER, vnoidacceso2 NUMBER) IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.sec_nume_docu_cabe,
             det.acce_oid_acce,
             det.ind_rece_web
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         and (det.acce_oid_acce is null or det.acce_oid_acce=vnoidacceso or det.acce_oid_acce=vnoidacceso2)
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_sec_nume_docu_cabe IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_acce IS TABLE OF int_solic_conso_detal.acce_oid_acce%TYPE;
    TYPE t_ind_rece_web IS TABLE OF int_solic_conso_cabec.ind_rece_web%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_sec_nume_docu_cabe t_sec_nume_docu_cabe;
    v_oid_acce      t_oid_acce;
    v_ind_rece_web  t_ind_rece_web;

    w_filas     NUMBER := 5000;
    i           BINARY_INTEGER := 0;
    is_valid    BOOLEAN;
    cuenta      NUMBER:=0;
    lnoidacceso sto_param_gener_occrr.val_param%TYPE;
    lnoidacceso2 sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lnoidacceso := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_ACCE_WEB');


    lnoidacceso2 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_ACCE_PROL');

    OPEN c_validacion(lnoidacceso,lnoidacceso2);
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_sec_nume_docu_cabe,
             v_oid_acce,
             v_ind_rece_web LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          cuenta:=1;

          if v_oid_acce(i) is not null and v_oid_acce(i)=lnoidacceso then

          select count(1) into cuenta
          from int_solic_conso_cabec
          where sec_nume_docu=v_sec_nume_docu_cabe(i)
          and ind_rece_web=1;

          end if;


          if v_oid_acce(i) is not null and v_oid_acce(i)=lnoidacceso2 then

          select count(1) into cuenta
          from int_solic_conso_cabec
          where sec_nume_docu=v_sec_nume_docu_cabe(i)
          and ind_vali_prol=1;

          end if;


          IF cuenta=0 THEN
            is_valid := FALSE;
          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_ofert_webde: ' || ls_sqlerrm);

  END sto_pr_ocd_valid_ofert_webde;

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Programa de nuevas
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND nvl(det.ind_erro_sse,
                 '0') != '1';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000;
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_nueva: ' || ls_sqlerrm);

  END sto_pr_ocd_valid_nueva;

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Session Experte
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_ssexp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND nvl(det.ind_erro_sse,
                 '0') != 'S';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000;
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_ssexp: ' || ls_sqlerrm);

  END sto_pr_ocd_valid_ssexp;

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Dupla Cyzone
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_dpcyz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND nvl(det.ind_erro_sse,
                 '0') != 'D';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000;
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_dpcyz: ' || ls_sqlerrm);

  END sto_pr_ocd_valid_dpcyz;

  /***************************************************************************
    Descripcion       : Validación de Indicador de Error - Rechazo OCR
                        para Orden de compra Detalle
    Fecha Creacion    : 09/08/2010
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_recha_occrr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND nvl(det.ind_erro_rech,
                 '0') = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000;
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_dpcyz: ' || ls_sqlerrm);

  END sto_pr_ocd_recha_occrr;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_ACTIV_OK
                       Procedimiento de Validacion de Premio para concrso Activo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas  NUMBER := 5000;
    i        BINARY_INTEGER := 0;
    is_valid BOOLEAN;
    lncont   NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          IF (v_oid_param_gral(i) IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lncont
              FROM inc_concu_param_gener
             WHERE oid_para_gral = v_oid_param_gral(i)
               AND ind_acti = 1;
          ELSE
            is_valid := TRUE;
            lncont   := 1;
          END IF;

          IF lncont = 0 THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               2);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CONCU_ACTIV: ' || ls_sqlerrm);
  END sto_pr_ocd_concu_activ;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_ELECT_OK
                       Procedimiento de Validacion de Premio para concrso Electivo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;
    --AND det.copa_oid_para_gral IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas  NUMBER := 5000;
    i        BINARY_INTEGER := 0;
    is_valid BOOLEAN;
    lncont   NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          IF (v_oid_param_gral(i) IS NOT NULL) THEN

            SELECT COUNT(1)
              INTO lncont
              FROM inc_param_gener_premi
             WHERE copa_oid_para_gral = v_oid_param_gral(i)
               AND ind_prem_elec = 1;
          ELSE
            is_valid := TRUE;
            lncont   := 1;
          END IF;

          IF lncont = 0 THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               3);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CONCU_ELECT: ' || ls_sqlerrm);
  END sto_pr_ocd_concu_elect;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_NIVEL_ELECT_OK
                       Procedimiento de Validacion de Premio para nivel electivo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_nivel_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;
    --AND det.copa_oid_para_gral IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas  NUMBER := 5000;
    i        BINARY_INTEGER := 0;
    is_valid BOOLEAN;
    lncont   NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;
          IF (v_oid_param_gral(i) IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lncont
              FROM inc_concu_param_gener cpg,
                   inc_param_gener_premi pgp,
                   inc_param_nivel_premi pnp,
                   inc_premi_artic       pa,
                   inc_lote_premi_artic  lpa,
                   inc_artic_lote        al
             WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
               AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
               AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
               AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
               AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
               AND al.cod_vent_fict = v_cod_vent(i)
               AND pnp.val_nive_sele = 1;
          ELSE
            lncont   := 1;
            is_valid := TRUE;
          END IF;

          IF lncont = 0 THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               4);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_NIVEL_ELECT: ' || ls_sqlerrm);
  END sto_pr_ocd_nivel_elect;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_CONCU_PERIO_OK
                       Procedimiento de Validacion de Premio para el concurso en periodo
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_concu_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;
    --AND det.copa_oid_para_gral IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas  NUMBER := 5000;
    i        BINARY_INTEGER := 0;
    is_valid BOOLEAN;
    lncont   NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          IF (v_oid_param_gral(i) IS NOT NULL) THEN

            SELECT COUNT(1)
              INTO lncont
              FROM (SELECT oid_para_gral,
                           gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_desd) perinic,
                           gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_hast) perhast,
                           CASE
                             WHEN pgp.tele_oid_tipo_elec = 1 THEN
                              gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_desd)
                             ELSE
                              gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_hast)
                           END perinicvali,
                           CASE
                             WHEN pgp.perd_oid_peri IS NULL THEN
                              per_pkg_repor_perce.per_fn_obtie_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_hast),
                                                                     gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais), -- pais
                                                                     gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                                                     gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                                                     2)
                             ELSE
                              gen_pkg_gener.gen_fn_devuelve_des_perio(pgp.perd_oid_peri)
                           END perhastvali
                      FROM inc_concu_param_gener cpg,
                           inc_param_gener_premi pgp
                     WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral) t
             WHERE t.oid_para_gral = v_oid_param_gral(i) -- concurso
               AND t.perinicvali <= v_cod_peri(i) -- periodo proceso
               AND t.perhastvali >= v_cod_peri(i); -- periodo proceso

          ELSE
            is_valid := TRUE;
            lncont   := 1;
          END IF;

          IF lncont = 0 THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               5);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_CONCU_PERIO: ' || ls_sqlerrm);
  END sto_pr_ocd_concu_perio;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_TIPO_CONSU_CONCU_OK
                       Procedimiento de Validacion de Premio para tipologia de consultora
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_consu_concu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas     NUMBER := 5000;
    i           BINARY_INTEGER := 0;
    is_valid    BOOLEAN;
    lsresultado VARCHAR2(1);
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          IF (v_oid_param_gral(i) IS NULL) THEN
            is_valid    := TRUE;
            lsresultado := '1';
          ELSE
            lsresultado := lov_pkg_proce.lov_fn_valid_clasi_concu(v_oid_param_gral(i),
                                                                  v_clie_oid_clie(i));
          END IF;

          IF (lsresultado = '0') THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               6);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_TIPO_CONSU_CONCU: ' || ls_sqlerrm);
  END sto_pr_ocd_tipo_consu_concu;

  /**************************************************************************
   Descripcion       : STO_PR_OCC_UNIDA_ADMIN_CONSU_OK
                       Procedimiento de Validacion de Premio para la unidad administrativa d ela consultora
                       segun secuencia de ejecucion
   Fecha Creacion    : 26/08/2010
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_unida_admin_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_clie,
             det.cod_vent,
             det.fec_digi,
             det.val_unid_dem,
             det.panp_oid_para_nive_prem,
             det.num_prem,
             det.cod_peri,
             det.copa_oid_para_gral,
             nvl((SELECT x.clie_oid_clie
                   FROM int_solic_conso_cabec x
                  WHERE x.cod_pais = pscodigopais
                    AND x.cod_peri = det.cod_peri
                    AND x.cod_clie = det.cod_clie
                    AND x.num_lote = det.num_lote),
                 1) clie_oid_clie
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_panp_oid_para_nive_prem IS TABLE OF int_solic_conso_detal.panp_oid_para_nive_prem%TYPE;
    TYPE t_num_prem IS TABLE OF int_solic_conso_detal.num_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_oid_param_gral IS TABLE OF int_solic_conso_detal.copa_oid_para_gral%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;

    v_numlote                 t_numlote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_oid_param_gral          t_oid_param_gral;
    v_cod_clie                t_cod_clie;
    v_cod_vent                t_cod_vent;
    v_fec_soli                t_fec_soli;
    v_val_unid_dem            t_val_unid_dem;
    v_panp_oid_para_nive_prem t_panp_oid_para_nive_prem;
    v_num_prem                t_num_prem;
    v_cod_peri                t_cod_peri;
    v_clie_oid_clie           t_clie_oid_clie;

    w_filas     NUMBER := 5000;
    i           BINARY_INTEGER := 0;
    is_valid    BOOLEAN;
    lsresultado VARCHAR2(1);
    ---
    vvar NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_vent,
             v_fec_soli,
             v_val_unid_dem,
             v_panp_oid_para_nive_prem,
             v_num_prem,
             v_cod_peri,
             v_oid_param_gral,
             v_clie_oid_clie LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FOR i IN 1 .. v_numlote.count
        LOOP
          is_valid := TRUE;

          IF (v_oid_param_gral(i) IS NULL) THEN
            is_valid    := TRUE;
            lsresultado := '1';

          ELSE
            ---
            SELECT COUNT(1)
              INTO vvar
              FROM inc_ambit_geogr g
             WHERE g.copa_oid_para_gral = v_oid_param_gral(i);
            IF vvar != 0 THEN
              lsresultado := inc_pkg_proce_incen.inc_fn_valid_ambit_geogr_concu(v_oid_param_gral(i),
                                                                                v_clie_oid_clie(i));
            ELSE
              lsresultado := '1';
            END IF;
            ---
            /*lsresultado := inc_pkg_proce_incen.inc_fn_valid_ambit_geogr_concu(v_oid_param_gral(i),
            v_clie_oid_clie(i));*/
          END IF;

          IF (lsresultado = '0') THEN
            is_valid := FALSE;
            DELETE FROM inc_premi_digit_inval
             WHERE cod_clie = v_cod_clie(i)
               AND cod_conc = (SELECT num_conc
                                 FROM inc_concu_param_gener
                                WHERE oid_para_gral = v_oid_param_gral(i))
               AND cod_vent_fict = v_cod_vent(i);
            INSERT INTO inc_premi_digit_inval
              (cod_clie,
               cod_conc,
               cod_vent_fict,
               fec_digi,
               num_unid,
               num_punt_disp,
               num_punt_prem,
               cod_usua,
               clie_oid_clie,
               copa_oid_para_gral,
               panp_oid_para_nive_prem,
               num_prem,
               cod_peri,
               cod_moti_inva)
            VALUES
              (v_cod_clie(i),
               (SELECT num_conc FROM inc_concu_param_gener WHERE oid_para_gral = v_oid_param_gral(i)),
               v_cod_vent(i),
               v_fec_soli(i),
               v_val_unid_dem(i),
               NULL,
               NULL,
               psusuario,
               v_clie_oid_clie(i),
               v_oid_param_gral(i),
               v_panp_oid_para_nive_prem(i),
               v_num_prem(i),
               v_cod_peri(i),
               7);

          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_UNIDA_ADMIN_CONSU: ' || ls_sqlerrm);
  END sto_pr_ocd_unida_admin_consu;
  /***************************************************************************
  Descripcion       : Validacion de Limite de venta focalizado por consultora
  Fecha Creacion    : 24/05/2011
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_valid_limit_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_peri,
             det.cod_clie,
             det.ind_limi_vent_foca,
             det.val_unid_dema_lvfo,
             det.val_unid_dem
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_ind_limi_vent_foca IS TABLE OF int_solic_conso_detal.ind_limi_vent_foca%TYPE;
    TYPE t_val_unid_dema_lvfo IS TABLE OF int_solic_conso_detal.val_unid_dema_lvfo%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;

    v_numlote            t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_ind_limi_vent_foca t_ind_limi_vent_foca;
    v_val_unid_dema_lvfo t_val_unid_dema_lvfo;
    v_val_unid_dem       t_val_unid_dem;

    w_filas NUMBER := 5000;

    v_val_unid_limi sto_limit_venta_focal_consu.val_nume_unid_limi%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_peri,
             v_cod_clie,
             v_ind_limi_vent_foca,
             v_val_unid_dema_lvfo,
             v_val_unid_dem LIMIT w_filas;

      IF v_numlote.count > 0 THEN
        FOR i IN 1 .. v_numlote.count
        LOOP

          v_ind_limi_vent_foca(i) := '0';
          v_val_unid_dema_lvfo(i) := NULL;

          SELECT MIN(val_nume_unid_limi)
            INTO v_val_unid_limi
            FROM sto_limit_venta_focal_consu
           WHERE cod_pais = pscodigopais
             AND cod_peri = v_cod_peri(i)
             AND cod_clie = v_cod_clie(i);

          IF v_val_unid_dem(i) > v_val_unid_limi THEN
            v_ind_limi_vent_foca(i) := '1';
            v_val_unid_dema_lvfo(i) := v_val_unid_limi;
          END IF;

        END LOOP;

        FORALL i IN 1 .. v_numlote.count
          UPDATE int_solic_conso_detal
             SET ind_limi_vent_foca = v_ind_limi_vent_foca(i),
                 val_unid_dema_lvfo = v_val_unid_dema_lvfo(i)
           WHERE num_lote = v_numlote(i)
             AND sec_nume_docu = v_sec_nume_docu(i);

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_ocd_valid_limit_venta: ' || ls_sqlerrm);
  END sto_pr_ocd_valid_limit_venta;

  /***************************************************************************
  Descripcion       : Validacion rechazo venta exclusiva
  Fecha Creacion    : 16/10/2012
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_venta_exclu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.sec_nume_docu_cabe,
             det.ofde_oid_deta_ofer,
             (select count(1) from pre_venta_exclu where ofer_oid_ofer=ofer.ofer_oid_ofer) cuenta
        FROM int_solic_conso_detal det,
             pre_ofert_detal ofer,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         and det.ofde_oid_deta_ofer=ofer.oid_deta_ofer(+)
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         ;

    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_sec_nume_docu_cabe IS TABLE OF int_solic_conso_detal.sec_nume_docu_cabe%TYPE;
    TYPE t_ofde_oid_deta_ofer IS TABLE OF int_solic_conso_detal.ofde_oid_deta_ofer%TYPE;
    TYPE t_cuenta IS TABLE OF int_solic_conso_detal.ofde_oid_deta_ofer%TYPE;

    v_num_lote           t_num_lote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_sec_nume_docu_cabe      t_sec_nume_docu_cabe;
    v_ofde_oid_deta_ofer t_ofde_oid_deta_ofer;
    v_cuenta             t_cuenta;

    w_filas           NUMBER := 5000;
    lnofertaexclusiva NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_sec_nume_docu_cabe,
             v_ofde_oid_deta_ofer,
             v_cuenta
             LIMIT w_filas;

      IF v_num_lote.count > 0 THEN
        FOR i IN 1 .. v_num_lote.count
        LOOP

          if v_cuenta(i)=0 then

            lnofertaexclusiva:=1;

          else

          SELECT COUNT(1)
            INTO lnofertaexclusiva
            FROM pre_ofert_detal c,
                 pre_ofert       d,
                   pre_venta_exclu e,
                   int_solic_conso_cabec cab,
                   mae_clien_tipo_subti g,
                   mae_clien_clasi      h,
                   zon_zona      i,
                   zon_regio      j
           WHERE v_ofde_oid_deta_ofer(i) = c.oid_deta_ofer
             AND c.ofer_oid_ofer = d.oid_ofer
               AND d.oid_ofer = e.ofer_oid_ofer
               and cab.zzon_oid_zona=i.oid_zona
               and cab.sec_nume_docu=v_sec_nume_docu_cabe(i)
               and i.zorg_oid_regi=j.oid_regi
               and cab.clie_oid_clie=g.clie_oid_clie
               and g.oid_clie_tipo_subt=h.ctsu_oid_clie_tipo_subt
               and (g.ticl_oid_tipo_clie=e.ticl_oid_tipo_clie or e.ticl_oid_tipo_clie is null)
               and (g.sbti_oid_subt_clie=e.sbti_oid_subt_clie or e.sbti_oid_subt_clie is null)
               and (h.tccl_oid_tipo_clasi=e.tccl_oid_tipo_clas or e.tccl_oid_tipo_clas is null)
               and (h.clas_oid_clas=e.clas_oid_clas or e.clas_oid_clas is null)
               and (i.oid_zona=e.zzon_oid_zona or e.zzon_oid_zona is null)
               and (j.oid_regi=e.zorg_oid_regi or e.zorg_oid_regi is null)
               ;


          end if;

          IF lnofertaexclusiva > 0 THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_VENTA_EXCLU: ' || ls_sqlerrm);
  END sto_pr_ocd_venta_exclu;

  /***************************************************************************
  Descripcion       : Validacion rechazo Por tipo oferta web
  Fecha Creacion    : 06/01/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_ocd_tipo_ofert_web
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion(tipoOferta VARCHAR2) IS
      SELECT det.num_lote,
             det.sec_nume_docu
        FROM int_solic_conso_detal det,
             sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND det.sec_nume_docu NOT IN(
            select distinct b.sec_nume_docu
            from int_solic_conso_cabec a,
            int_solic_conso_detal b,
            pre_ofert_detal c,
            pre_tipo_ofert d
            where a.sec_nume_docu = b.sec_nume_docu_cabe
            and b.ofde_oid_deta_ofer = c.oid_deta_ofer
            and c.tofe_oid_tipo_ofer = d.oid_tipo_ofer
            and d.cod_tipo_ofer = tipoOferta
            and b.ind_rece_web = 0);

    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_ofde_oid_deta_ofer IS TABLE OF int_solic_conso_detal.ofde_oid_deta_ofer%TYPE;

    v_num_lote           t_num_lote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_ofde_oid_deta_ofer t_ofde_oid_deta_ofer;

    w_filas           NUMBER := 5000;
    lnofertaexclusiva NUMBER := 0;
    v_tipoOferta      VARCHAR2(4);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    select val_para
    into v_tipoOferta
    from bas_param_pais where cod_pais = pscodigopais and cod_sist = 'STO' and nom_para = 'tipoOfertaWeb';

    OPEN c_validacion(v_tipoOferta);
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_num_lote.count > 0 THEN

        FORALL i IN 1 .. v_num_lote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCD_TIPO_OFERT_WEB: ' || ls_sqlerrm);
  END sto_pr_ocd_tipo_ofert_web;

END sto_pkg_proce_valid_ocd;
/
