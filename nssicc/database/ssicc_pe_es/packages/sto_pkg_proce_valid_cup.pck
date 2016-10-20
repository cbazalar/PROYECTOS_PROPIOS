CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_cup AS
  /**************************************************************************
  Descripcion       : STO_PR_CUP_VALI_PAIS
                    Procedimiento de Validacion de Pais Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_UNIDA_ADMIN
                    Procedimiento de Validacion de Unidad administrativa
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_PERIO
                   Procedimiento de Validacion de periodo
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_RECHA_SELLO
                   Procedimiento de Validacion de rechazo por sello
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_recha_sello
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CERTI_CUPON
                    Procedimiento de Validacion de certidumbre cupon 3
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_certi_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_NODUP_CUPON
                    Procedimiento de Validacion de no duplicidad cupon 3
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_nodup_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_NOEXI_CUPON
                    Procedimiento de Validacion de no existencia cupon 1
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_noexi_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_BLOQU_CLIEN
                    Procedimiento de Validacion de bloqueo de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_DEUDA_CLIEN
                    Procedimiento de Validacion de deuda de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_deuda_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                    Procedimiento de Validacion de codigo verificador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 15/06/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_codig_verif
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                    Procedimiento de Validacion de monto de moviemientos bancarios
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_movim_banca
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                   Procedimiento de Validacion de valor de impuesto
  Fecha Creacion    : 30/11/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_impva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_CLIEN
                   Procedimiento de Validacion de codigo de cliente
  Fecha Creacion    : 03/12/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_DOCUM_RECHA
                   Procedimiento de Validacion de documento OCR rechazado
  Fecha Creacion    : 03/12/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_docum_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CUP_DEPUR_CUPON
                   Procedimiento que depura el cupon
  Fecha Creacion    : 08/06/2015


  Autor             : Rosalvina Ramirez
  **************************************************************************/  
    PROCEDURE sto_pr_cup_depur_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_cup;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_cup IS

  /* Declaracion de Variables */

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /**************************************************************************
  Descripcion       : STO_PR_CUP_VALI_PAIS
                    Procedimiento de Validacion de Pais Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )
  
   IS
    CURSOR c_validapais IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             pais.oid_pais
        FROM seg_pais                   pais,
             int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pais.cod_pais = cons.cod_pais;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_cupon_pago.oid_pais%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;
  
    lscodclie int_solic_conso_cupon_pago.cod_clie%TYPE;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    ---j    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_validapais;
    LOOP
      FETCH c_validapais BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
      
        FOR i IN 1 .. v_sec_nume_docu.count
        LOOP
        
          BEGIN
            SELECT mae.cod_clie
              INTO lscodclie
              FROM mae_clien_ident mid,
                   mae_clien       mae
             WHERE mid.num_docu_iden = v_codclie(i)
               AND mid.clie_oid_clie = mae.oid_clie;
          EXCEPTION
            WHEN no_data_found THEN
              lscodclie := v_codclie(i);
          END;
        
          UPDATE int_solic_conso_cupon_pago
             SET oid_pais = v_oid_pais(i),
                 cod_clie = lscodclie
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE,
                 occ.cod_clie           = lscodclie
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_validapais%NOTFOUND;
    END LOOP;
    CLOSE c_validapais;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123, 'STO_PR_CUP_PAIS: ' || ls_sqlerrm);
    
  END sto_pr_cup_pais;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_UNIDA:ADMIN
                    Procedimiento de Validacion de la unidad administrativa
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )
  
   IS
    CURSOR c_unidadmin IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             zon.oid_zona,
             zta.oid_terr_admi,
             ter.oid_terr
        FROM mae_clien_unida_admin      cua,
             zon_sub_geren_venta        sgv,
             zon_regio                  reg,
             zon_zona                   zon,
             zon_secci                  sec,
             zon_terri_admin            zta,
             zon_terri                  ter,
             int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ,
             mae_clien                  cli
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cua.clie_oid_clie = cli.oid_clie
         AND cli.cod_clie = cons.cod_clie
         AND sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND reg.oid_regi = zon.zorg_oid_regi
         AND zon.oid_zona = sec.zzon_oid_zona
         AND sec.oid_secc = zta.zscc_oid_secc
         AND ter.oid_terr = zta.terr_oid_terr
         AND cua.ind_acti = 1
         AND cua.perd_oid_peri_fin IS NULL
         AND cua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zon.ind_acti = 1;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oid_zona IS TABLE OF zon_zona.oid_zona%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF zon_terri_admin.oid_terr_admi%TYPE;
    TYPE t_oid_terr IS TABLE OF zon_terri.oid_terr%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_zona      t_oid_zona;
    v_oid_terr_admi t_oid_terr_admi;
    v_oid_terr      t_oid_terr;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_unidadmin;
    LOOP
      FETCH c_unidadmin BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_oid_zona,
             v_oid_terr_admi,
             v_oid_terr LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_cupon_pago
             SET oid_terr      = v_oid_terr(i),
                 oid_terr_admi = v_oid_terr_admi(i),
                 oid_zona      = v_oid_zona(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);
      
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
      EXIT WHEN c_unidadmin%NOTFOUND;
    END LOOP;
    CLOSE c_unidadmin;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_UNIDA_ADMIN: ' || ls_sqlerrm);
    
  END sto_pr_cup_unida_admin;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_PERIO
                   Procedimiento de Validacion de periodo
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_periodo IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             a.oid_peri
        FROM cra_perio                  a,
             int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND a.fec_inic <= cons.fec_proc
         AND a.fec_fina >= cons.fec_proc
         AND a.pais_oid_pais = cons.oid_pais
      /*AND NOT EXISTS
      (SELECT *
               FROM fac_contr_cierr
              WHERE tcie_oid_tipo_cier = 2
                AND perd_oid_peri = a.oid_peri
                AND zzon_oid_zona = (SELECT c.oid_zona
                                       FROM zon_terri_admin a,
                                            zon_secci       b,
                                            zon_zona        c
                                      WHERE a.zscc_oid_secc = b.oid_secc
                                        AND b.zzon_oid_zona = c.oid_zona
                                        AND a.oid_terr_admi = cons.oid_terr_admi))*/
      ;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_cupon_pago.oid_peri%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_peri      t_oid_peri;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_periodo;
    LOOP
      FETCH c_periodo BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_oid_peri LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_cupon_pago
             SET oid_peri = v_oid_peri(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);
      
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
      EXIT WHEN c_periodo%NOTFOUND;
    END LOOP;
    CLOSE c_periodo;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123, 'STO_PR_CUP_PERIO: ' || ls_sqlerrm);
    
  END sto_pr_cup_perio;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_RECHA_SELLO
                   Procedimiento de Validacion de rechazo por sello
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_cup_recha_sello
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_rechazosello IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND nvl(cons.ind_rech_sell, 0) <> '1';
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_rechazosello;
    LOOP
      FETCH c_rechazosello BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
      
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
      EXIT WHEN c_rechazosello%NOTFOUND;
    END LOOP;
    CLOSE c_rechazosello;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_RECHA_SELLO: ' || ls_sqlerrm);
  END sto_pr_cup_recha_sello;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CERTI_CUPON
                    Procedimiento de Validacion de certidumbre cupon 3
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_certi_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_certicupon IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.imp_valo,
             cons.val_deud
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;
    TYPE t_val_deud IS TABLE OF int_solic_conso_cupon_pago.val_deud%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_imp_valo      t_imp_valo;
    v_val_deud      t_val_deud;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    lnporcentaje  NUMBER;
    lndeudalimite NUMBER := 0;
    existe        BOOLEAN := TRUE;
    lnmonto       NUMBER;
  BEGIN
  
    lnporcentaje := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_POR_DESVI_CUPO'));
  
    lnmonto := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_MONTO_DESV_CUPO'));
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_certicupon;
    LOOP
      FETCH c_certicupon BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_imp_valo,
             v_val_deud LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe        := TRUE;
          lndeudalimite := v_val_deud(i) + lnmonto +
                           (v_val_deud(i) * lnporcentaje / 100);
        
          IF (lndeudalimite < v_imp_valo(i)) THEN
          
            BEGIN
              existe := FALSE;
            
            END;
          
          END IF;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_certicupon%NOTFOUND;
    END LOOP;
    CLOSE c_certicupon;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_CERTI_CUPON: ' || ls_sqlerrm);
    
  END sto_pr_cup_certi_cupon;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_NODUP_CUPON
                    Procedimiento de Validacion de no duplicidad cupon 3
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_nodup_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_noduplicado IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_proc,
             cons.imp_valo
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_cupon_pago.fec_proc%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_fec_proc      t_fec_proc;
    v_imp_valo      t_imp_valo;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    contador     NUMBER := 0;
    existe       BOOLEAN := TRUE;
    lnnumerodias NUMBER := 0;
  BEGIN
  
    lnnumerodias := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_NUM_DIAS_CRUCE'));
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_noduplicado;
    LOOP
      FETCH c_noduplicado BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_proc,
             v_imp_valo LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
        
          BEGIN
          
            contador := 0;
          
            SELECT COUNT(*)
              INTO contador
              FROM ccc_detal_cupon_trami_depur
             WHERE clie_oid_clie IN
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
                  -- AND pais_oid_pais=v_oid_pais(i)
               AND imp_deta = v_imp_valo(i)
               AND fec_docu > v_fec_proc(i) + lnnumerodias;
          
            IF (contador > 0) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;
          
          END;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_noduplicado%NOTFOUND;
    END LOOP;
    CLOSE c_noduplicado;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_NODUP_CUPON: ' || ls_sqlerrm);
    
  END sto_pr_cup_nodup_cupon;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_NOEXI_CUPON
                    Procedimiento de Validacion de no existencia cupon 1
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_noexi_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_noexiste IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_pais,
             cons.fec_proc,
             cons.imp_valo
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_cupon_pago.oid_pais%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_cupon_pago.fec_proc%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;
    v_fec_proc      t_fec_proc;
    v_imp_valo      t_imp_valo;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    i        BINARY_INTEGER := 0;
    contador NUMBER := 0;
    numero   NUMBER := 0;
  
    existe       BOOLEAN := TRUE;
    lnnumerodias NUMBER := 0;
  BEGIN
  
    lnnumerodias := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_NUM_DIAS_CRUCE'));
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_noexiste;
    LOOP
      FETCH c_noexiste BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais,
             v_fec_proc,
             v_imp_valo LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
        
          BEGIN
          
            contador := 0;
          
            SELECT COUNT(*)
              INTO contador
              FROM ccc_movim_banca
             WHERE clie_oid_clie =
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
               AND val_impo_movi = v_imp_valo(i)
               AND fec_pago > v_fec_proc(i) + lnnumerodias
               AND pais_oid_pais = v_oid_pais(i);
          
            IF (contador > 0) THEN
              existe := FALSE;
            ELSE
              BEGIN
                existe := TRUE;
              
                numero := 0;
                SELECT COUNT(*)
                  INTO numero
                  FROM ccc_movim_banca
                 WHERE clie_oid_clie =
                       (SELECT oid_clie
                          FROM mae_clien
                         WHERE cod_clie = v_codclie(i))
                   AND fec_pago > v_fec_proc(i) + lnnumerodias
                   AND pais_oid_pais = v_oid_pais(i);
              
                IF (numero > 0) THEN
                
                  UPDATE int_solic_conso_cupon_pago
                     SET val_deud = 2
                   WHERE sec_nume_docu = v_sec_nume_docu(i)
                     AND num_lote = v_numlote(i);
                
                END IF;
              
              END;
            END IF;
          
          END;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_noexiste%NOTFOUND;
    END LOOP;
    CLOSE c_noexiste;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_NOEXI_CUPON: ' || ls_sqlerrm);
    
  END sto_pr_cup_noexi_cupon;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_BLOQU_CLIEN
                    Procedimiento de Validacion de bloqueo de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_bloqueocliente IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    CURSOR c_motivosbloqueo
    (
      ln_oidclie     NUMBER,
      ln_indfacrefac VARCHAR2
    ) IS
      SELECT a.tibq_oid_tipo_bloq,
             bloq_desc.val_i18n
        FROM mae_clien_bloqu a,
             mae_accio_proce_bloqu b,
             mae_accio_bloqu c,
             mae_proce_bloqu d,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_BLOQU') bloq_desc
       WHERE a.fec_desb IS NULL
         AND a.clie_oid_clie = ln_oidclie
         AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
         AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
         AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
         AND d.cod_proc_bloq = ln_indfacrefac
         AND c.cod_acci_bloq = 'VN'
         AND a.tibq_oid_tipo_bloq = bloq_desc.val_oid;
  
    TYPE motbloqdiarectab IS TABLE OF c_motivosbloqueo%ROWTYPE INDEX BY BINARY_INTEGER;
    motbloqrecord motbloqdiarectab;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    i        BINARY_INTEGER := 0;
    j        BINARY_INTEGER := 0;
    contador NUMBER := 0;
    numero   NUMBER := 0;
  
    lnoid_clie NUMBER(12);
  
    existe BOOLEAN := FALSE;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := FALSE;
        
          BEGIN
          
            contador := 0;
            numero   := 0;
          
            SELECT COUNT(*)
              INTO contador
              FROM mae_clien_bloqu a
             WHERE a.fec_desb IS NULL
               AND a.clie_oid_clie IN
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i));
          
            IF (contador > 0) THEN
              SELECT COUNT(*)
                INTO numero
                FROM mae_clien_bloqu       a,
                     mae_accio_proce_bloqu b,
                     mae_accio_bloqu       c,
                     mae_proce_bloqu       d
               WHERE a.fec_desb IS NULL
                 AND a.clie_oid_clie IN
                     (SELECT oid_clie
                        FROM mae_clien
                       WHERE cod_clie = v_codclie(i))
                 AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
                 AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
                 AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
                 AND d.cod_proc_bloq = 'CP'
                 AND c.cod_acci_bloq = 'VS';
            
              IF (numero <> contador) THEN
              
                SELECT oid_clie
                  INTO lnoid_clie
                  FROM mae_clien
                 WHERE cod_clie = v_codclie(i);
              
                OPEN c_motivosbloqueo(lnoid_clie, 'CP');
                LOOP
                  FETCH c_motivosbloqueo BULK COLLECT
                    INTO motbloqrecord LIMIT rows;
                  IF motbloqrecord.count > 0 THEN
                    FOR j IN motbloqrecord.first .. motbloqrecord.last
                    LOOP
                      -- adiciona mensajes de sto                                                                                                                             
                      sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                           v_numlote(i),
                                                           'Motivo : ' || motbloqrecord(j)
                                                           .val_i18n);
                    END LOOP;
                  END IF;
                  EXIT WHEN c_motivosbloqueo%NOTFOUND;
                END LOOP;
                CLOSE c_motivosbloqueo;
              END IF;
            END IF;
          
            IF (contador = numero) THEN
              existe := TRUE;
            ELSE
              existe := FALSE;
            END IF;
          
          END;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_bloqueocliente%NOTFOUND;
    END LOOP;
    CLOSE c_bloqueocliente;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_BLOQU_CLIEN: ' || ls_sqlerrm);
    
  END sto_pr_cup_bloqu_clien;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_DEUDA_CLIEN
                    Procedimiento de Validacion de deuda de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 17/02/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_deuda_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_bloqueocliente IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    i        BINARY_INTEGER := 0;
    contador NUMBER := 0;
    lndeuda  NUMBER := 0;
    lncupon  NUMBER := 0;
  
    existe BOOLEAN := TRUE;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
        
          BEGIN
          
            contador := 0;
          
            SELECT SUM(imp_pend)
              INTO lndeuda
              FROM ccc_movim_cuent_corri
             WHERE clie_oid_clie =
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
               AND fec_venc <= SYSDATE + 1;
          
            IF (lndeuda - lncupon <= 0) THEN
              existe := FALSE;
            END IF;
          
            IF (lndeuda > 0) THEN
            
              UPDATE int_solic_conso_cupon_pago
                 SET val_deud = lndeuda
               WHERE sec_nume_docu = v_sec_nume_docu(i)
                 AND num_lote = v_numlote(i);
            END IF;
          
          END;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_bloqueocliente%NOTFOUND;
    END LOOP;
    CLOSE c_bloqueocliente;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_DEUDA_CLIEN: ' || ls_sqlerrm);
    
  END sto_pr_cup_deuda_clien;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                    Procedimiento de Validacion de codigo verificador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 15/06/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_codig_verif
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacodigo IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM mae_clien                  clie,
             int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND clie.cod_clie = cons.cod_clie
         AND clie.cod_digi_ctrl = cons.cod_veri;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_validacodigo;
    LOOP
      FETCH c_validacodigo BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
      
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
      EXIT WHEN c_validacodigo%NOTFOUND;
    END LOOP;
    CLOSE c_validacodigo;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_CODIG_VERIF: ' || ls_sqlerrm);
    
  END sto_pr_cup_codig_verif;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                    Procedimiento de Validacion de monto de moviemientos bancarios
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_movim_banca
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_movibancario IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_proc,
             cons.imp_valo
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_cupon_pago.fec_proc%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;
  
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_fec_proc      t_fec_proc;
    v_imp_valo      t_imp_valo;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    contador NUMBER := 0;
  
    existe BOOLEAN := TRUE;
  
    lsparamtrodias sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    lsparamtrodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_DIAS_VALI_MOV_BA');
  
    OPEN c_movibancario;
    LOOP
      FETCH c_movibancario BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_proc,
             v_imp_valo LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
        
          BEGIN
          
            contador := 0;
          
            SELECT COUNT(1)
              INTO contador
              FROM ccc_movim_banca a
             WHERE a.fec_pago + to_number(lsparamtrodias) >= v_fec_proc(i)
               AND clie_oid_clie =
                   (SELECT oid_clie
                      FROM mae_clien
                     WHERE cod_clie = v_codclie(i))
               AND val_impo_movi = v_imp_valo(i);
          
            IF (contador > 0) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;
          
          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;
          END;
        
          IF (existe) THEN
          
            -- Actualizamos Documentos Validados OK
          
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
      EXIT WHEN c_movibancario%NOTFOUND;
    END LOOP;
    CLOSE c_movibancario;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'STO_PR_CUP_MOVIM_BANCA: ' || ls_sqlerrm);
    
  END sto_pr_cup_movim_banca;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_VERIF
                   Procedimiento de Validacion de valor de impuesto
  Fecha Creacion    : 30/11/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_impva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validamonto
    (
      vsmontodefinido NUMBER,
      vsmontomindef   NUMBER
    ) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.imp_valo <= vsmontodefinido
         AND cons.imp_valo >= vsmontomindef;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
    lnmontodefinido NUMBER := 0;
    lnmontomindef   NUMBER := 0;
  BEGIN
  
    lnmontodefinido := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_MONTO_DEFIN'));
    lnmontomindef   := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_MONTO_DEINI'));
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    IF lnmontomindef IS NULL THEN
      lnmontomindef := 0;
    END IF;
  
    OPEN c_validamonto(lnmontodefinido, lnmontomindef);
    LOOP
      FETCH c_validamonto BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
      
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
      EXIT WHEN c_validamonto%NOTFOUND;
    END LOOP;
    CLOSE c_validamonto;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123, 'STO_PR_CUP_IMPVA: ' || ls_sqlerrm);
    
  END sto_pr_cup_impva;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_CODIG_CLIEN
                   Procedimiento de Validacion de codigo de cliente
  Fecha Creacion    : 03/12/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cupon IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ,
             mae_clien                  clien
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_clie = clien.cod_clie;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
  
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
  
    rows NATURAL := 1000;
  
    j                 BINARY_INTEGER := 0;
    ls_cedula_cliente VARCHAR2(3);
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    --
    -- obtiene el parametro para identificar si viene la cedula en el campo codigo_cliente
    ls_cedula_cliente := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_CEDUL_CLIEN');
  
    -- Evalua si esta activa la funcionalidad para el pais
    IF ls_cedula_cliente = 'S' THEN
    
      -- actualiza el codigo de cliente a los que tengan numero de
      -- documento de identidad en el el codigo de cliente
      UPDATE int_solic_conso_cupon_pago f
         SET f.cod_clie =
             (SELECT m.cod_clie
                FROM mae_clien_ident x,
                     mae_clien       m
               WHERE ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0')
                 AND m.oid_clie = x.clie_oid_clie
                 AND x.val_iden_docu_prin = 1
                 AND rownum = 1)
       WHERE EXISTS
       (SELECT 1
                FROM mae_clien_ident x
               WHERE ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0'))
         AND EXISTS
       (SELECT 1
                FROM sto_tmp_docum_digit occ
               WHERE occ.sec_nume_docu = f.sec_nume_docu);
    
      -- actualiza la informacion del sto_docum_digit   
      UPDATE sto_docum_digit f
         SET f.cod_clie =
             (SELECT m.cod_clie
                FROM mae_clien_ident x,
                     mae_clien       m
               WHERE ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0')
                 AND m.oid_clie = x.clie_oid_clie
                 AND x.val_iden_docu_prin = 1
                 AND rownum = 1)
       WHERE EXISTS
       (SELECT 1
                FROM mae_clien_ident x
               WHERE ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0'))
         AND EXISTS (SELECT 1
                FROM sto_tmp_docum_digit occ
               WHERE occ.sec_nume_docu = f.sec_nume_docu)
         AND f.cod_tipo_docu = pscodigotipodoc;
    
    END IF;
    --
  
    OPEN c_cupon;
    LOOP
      FETCH c_cupon BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_secnumdocu.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      END IF;
      EXIT WHEN c_cupon%NOTFOUND;
    END LOOP;
    CLOSE c_cupon;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_CUP_CODIG_CLIEN: ' ||
                              ls_sqlerrm);
    
  END sto_pr_cup_codig_clien;

  /**************************************************************************
  Descripcion       : STO_PR_CUP_DOCUM_RECHA
                   Procedimiento de Validacion de documento OCR rechazado
  Fecha Creacion    : 03/12/2009
  
  
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_cup_docum_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cupon IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.sta_proc,
             cons.cod_moti_rech
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_ind_stat_proc IS TABLE OF int_solic_conso_cupon_pago.sta_proc%TYPE;
    TYPE t_ind_moti_rech IS TABLE OF int_solic_conso_cupon_pago.cod_moti_rech%TYPE;
  
    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_ind_stat_proc t_ind_stat_proc;
    v_ind_moti_rech t_ind_moti_rech;
  
    rows NATURAL := 1000;
  
    j BINARY_INTEGER := 0;
  
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_cupon;
    LOOP
      FETCH c_cupon BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_ind_stat_proc,
             v_ind_moti_rech LIMIT rows;
      IF v_secnumdocu.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
        
          existe := FALSE;
        
          BEGIN
          
            numero := 0;
            IF (v_ind_stat_proc(j) = '02' AND
               (v_ind_moti_rech(j) IN ('D', 'I', 'N'))) THEN
            
              numero := 1;
            
            END IF;
          
            IF (numero > 0) THEN
              existe := FALSE;
            
            ELSE
              existe := TRUE;
            END IF;
          
          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;
            
          END;
          IF (existe) THEN
          
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_secnumdocu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_cupon%NOTFOUND;
    END LOOP;
    CLOSE c_cupon;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_CUP_DOCUM_RECHA: ' ||
                              ls_sqlerrm);
    
  END sto_pr_cup_docum_recha;

  PROCEDURE sto_pr_cup_depur_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_depur IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_pais,
             cons.fec_proc,
             cons.imp_valo
        FROM int_solic_conso_cupon_pago cons,
             sto_tmp_docum_digit        occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_cupon_pago.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cupon_pago.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cupon_pago.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_cupon_pago.oid_pais%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_cupon_pago.fec_proc%TYPE;
    TYPE t_imp_valo IS TABLE OF int_solic_conso_cupon_pago.imp_valo%TYPE;

    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;
    v_fec_proc      t_fec_proc;
    v_imp_valo      t_imp_valo;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    i        BINARY_INTEGER := 0;
    contador NUMBER := 0;
    numero   NUMBER := 0;

    existe       BOOLEAN := TRUE;
    lnnumerodias NUMBER := 0;
    lv_oid       NUMBER(15);
        
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_depur;
    LOOP
      FETCH c_depur BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais,
             v_fec_proc,
             v_imp_valo LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP          
        
          existe := TRUE;
          
          IF (v_imp_valo(i) < 0) THEN 
            
            BEGIN
               SELECT cd.oid_deta_cupo_tram_depu 
               INTO lv_oid
               FROM
                CCC_DETAL_CUPON_TRAMI_DEPUR cd,
                CCC_SITUA_CUPON s
               WHERE s.OID_SITU_CUPO = cd.SICU_OID_SITU_CUPO
                 AND s.COD_SITU_CUPO = 'T'
                 AND cd.CLIE_OID_CLIE = (select oid_clie from mae_clien where cod_clie=v_codclie(i))
                 AND cd.IMP_DETA = v_imp_valo(i)
                 AND cd.FEC_DIGI = v_fec_proc(i);--to_date(v_fec_proc,'dd/mm/yyyy')
             EXCEPTION
               WHEN no_data_found THEN
                  existe := FALSE;
             END;
             
             IF (lv_oid > 0 ) THEN
               
                 UPDATE CCC_DETAL_CUPON_TRAMI_DEPUR cd
                 SET cd.SICU_OID_SITU_CUPO = (SELECT OID_SITU_CUPO FROM CCC_SITUA_CUPON WHERE COD_SITU_CUPO='D') --Depurado
                 WHERE cd.OID_DETA_CUPO_TRAM_DEPU = lv_oid;
             
             END IF;
            
          END IF;
          

          IF (existe) THEN

            -- Actualizamos Documentos Validados OK

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
      EXIT WHEN c_depur%NOTFOUND;
    END LOOP;
    CLOSE c_depur;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_CUP_DEPUR_CUPON: ' || ls_sqlerrm);

  END sto_pr_cup_depur_cupon;

END sto_pkg_proce_valid_cup;
/
