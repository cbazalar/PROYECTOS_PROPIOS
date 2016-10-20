CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_sim IS

  /* Declaracion de Variables */

  rows NUMBER := 5000;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CODIG_CLIEN
                        Procedimiento de Validacion del código del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_ANTIG_CLIEN
                        Procedimiento de Validacion de Antiguedad del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_antig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_EXIST_METRE
                        Procedimiento de Validacion de Meta Existente
                        ya registrada
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_exist_metre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_MONTO_MXMTA
                        Procedimiento de Validacion de Monto Máximo de Meta
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_monto_mxmta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CAMPA_INICI
                        Procedimiento de Validacion de Campaña de Inicio
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_campa_inici
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_DATOS_OBLIG
                        Procedimiento de Validacion de Datos Obligatorios
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_INDIC_RECHA
                        Procedimiento de Validacion de Rechazo OCR
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_indic_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : STO_PR_SIM_ENVIO_SICC
                        Envio de SIM a SICC
  Fecha Creacion      : 03/03/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

END sto_pkg_proce_valid_sim;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_sim IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  /* Constantes */
  CANTIDAD_PERIODOS    CONSTANT NUMBER := 3;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CODIG_CLIEN
                        Procedimiento de Validacion del código del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS (SELECT 1 FROM mae_clien cli WHERE cli.cod_clie = cons.cod_clie);

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_CODIG_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sim_codig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_ANTIG_CLIEN
                        Procedimiento de Validacion de Antiguedad del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_antig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ,
             mae_clien                   cl,
             mae_clien_datos_adici       mc
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cl.oid_clie = mc.clie_oid_clie
         AND cl.cod_clie = cons.cod_clie
         AND mc.esta_oid_esta_clie IN (1,
                                       2,
                                       7,
                                       8)
         AND mc.ind_acti = 1;

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_ANTIG_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sim_antig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_EXIST_METRE
                        Procedimiento de Validacion de Meta Existente
                        ya registrada
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_exist_metre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         --  No haya sido ya enviada
         AND NOT EXISTS (SELECT 1
                FROM nvs_consu_logro ncl
               WHERE ncl.cod_clie = cons.cod_clie
                 AND ncl.cod_pais = cons.cod_pais)
         --  No se encuentre duplicada en el proceso
         AND NOT EXISTS (SELECT NULL
                            FROM int_solic_conso_ingre_metas cons2,
                                 sto_docum_digit             occ2
                           WHERE cons2.sec_nume_docu = occ2.sec_nume_docu
                             AND cons2.num_lote = occ2.num_lote
                             AND cons2.cod_clie = cons.cod_clie
                             AND cons2.sec_nume_docu <> occ.sec_nume_docu
                             AND occ2.ind_envi = '0'
                             AND occ2.ind_rech = '0')
         /*AND NOT EXISTS (SELECT *
                FROM (SELECT a.cod_clie,
                             a.cod_camp_proc,
                             COUNT(*)
                        FROM int_solic_conso_ingre_metas a,
                             sto_docum_digit             b
                       WHERE a.sec_nume_docu = b.sec_nume_docu
                         AND b.ind_rech = 0
                         AND b.ind_envi = 0
                         AND NOT EXISTS (SELECT sec_nume_docu
                                FROM sto_detal_docum_excep
                               WHERE sec_nume_docu = a.sec_nume_docu
                                 AND ind_apro <> 0)
                       GROUP BY a.cod_clie,
                                a.cod_camp_proc
                      HAVING COUNT(*) > 1)
               WHERE cod_clie = cons.cod_clie
                 AND cod_camp_proc = cons.cod_camp_proc)*/
          ;

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    j            BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_EXIST_METRE: ' || ls_sqlerrm);

  END sto_pr_sim_exist_metre;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_MONTO_MXMTA
                        Procedimiento de Validacion de Monto Máximo de Meta
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_monto_mxmta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_meta,
             nvl(cons.val_mnto_meta,
                 0) val_mnto_meta
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_ingre_metas.tip_meta%TYPE;
    TYPE t_mntmaximo IS TABLE OF int_solic_conso_ingre_metas.val_mnto_meta%TYPE;

    v_numlote     t_numlote;
    v_secnumdocu  t_secnumdocu;
    v_tipmeta     t_tipmeta;
    v_mntomaximo  t_mntmaximo;
    isvalid       BOOLEAN := TRUE;
    lnmontomaximo NUMBER(12,
                         2);
    lnmontominimo NUMBER(12,
                         2);

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el Monto Máximo de la Meta
    --lsMontoMaximo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_MONT_MAXI_META');

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_tipmeta,
             v_mntomaximo LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := TRUE;

          BEGIN
            SELECT a.imp_maxi_logr,
                   a.imp_mini_logr
              INTO lnmontomaximo,
                   lnmontominimo
              FROM nvs_tipo_logro a
             WHERE a.cod_tipo_logr = v_tipmeta(j);
            lnmontomaximo := to_number(lnmontomaximo);
            lnmontominimo := to_number(lnmontominimo);
            v_mntomaximo(j) := to_number(v_mntomaximo(j));
          EXCEPTION
            WHEN OTHERS THEN
              isvalid := FALSE;
              --lnMontoMaximo := 0;
          END;
          IF (lnmontomaximo IS NOT NULL) THEN
            IF (v_mntomaximo(j) > lnmontomaximo OR v_mntomaximo(j) < lnmontominimo) THEN
              isvalid := FALSE;
            END IF;
          ELSE
            isvalid := FALSE;
          END IF;

          -- Actualizamos Documentos Validados OK
          IF (isvalid) THEN
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_MONTO_MXMTA: ' || ls_sqlerrm);

  END sto_pr_sim_monto_mxmta;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CAMPA_INICI
                        Procedimiento de Validacion de Campaña de Inicio
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_campa_inici
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.cod_pais,
             cons.cod_comp,
             cons.cod_clie,
             cons.num_docu,
             cons.num_lote,
             cons.sec_nume_docu,
             clad.esta_oid_esta_clie
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ,
             mae_clien                   cl,
             mae_clien_datos_adici       clad
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cl.cod_clie = cons.cod_clie
         AND cl.oid_clie = clad.clie_oid_clie
         AND clad.ind_acti = 1;
    --         AND clad.esta_oid_esta_clie IN (1,2);

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_codcomp IS TABLE OF int_solic_conso_actua_datos.cod_comp%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_actua_datos.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;
    TYPE t_estaoidestaclie IS TABLE OF mae_clien_datos_adici.esta_oid_esta_clie%TYPE;

    v_codpais         t_codpais;
    v_codcomp         t_codcomp;
    v_numlote         t_numlote;
    v_secnumdocu      t_secnumdocu;
    v_codclie         t_codclie;
    v_numdocu         t_numdocu;
    v_estaoidestaclie t_estaoidestaclie;

    ls_codperio seg_perio_corpo.cod_peri%TYPE;
    ln_idperio  cra_perio.oid_peri%TYPE;
    i           BINARY_INTEGER := 0;
    isvalid     BOOLEAN := TRUE;

    ln_id_pais  seg_pais.oid_pais%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    ln_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais, TRUE);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T', TRUE);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD', TRUE);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codpais,
             v_codcomp,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_secnumdocu,
             v_estaoidestaclie LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          -- Obtenemos el Id Periodo
          BEGIN
            SELECT mpc.perd_oid_peri
              INTO ln_idperio
              FROM mae_clien_prime_conta mpc,
                   mae_clien             mc
             WHERE lpad(mc.cod_clie,
                        15,
                        '0') = lpad(v_codclie(i),
                                    15,
                                    '0')
               AND mc.oid_clie = mpc.clie_oid_clie;
          EXCEPTION
            WHEN no_data_found THEN
              isvalid := FALSE;
          END;
          -- Obtenemos el Cod Periodo
          BEGIN
            SELECT seg.cod_peri
              INTO ls_codperio
              FROM cra_perio       cra,
                   seg_perio_corpo seg
             WHERE cra.peri_oid_peri = seg.oid_peri
               AND cra.oid_peri = ln_idperio;
          EXCEPTION
            WHEN no_data_found THEN
              isvalid := FALSE;
          END;

          -- DOI 11-10-2011
          begin
            -- Si no devuelve registros, que lo detenga la validacion,
            -- sino se cae al hacer el envio a SiCC
            IF per_pkg_repor_perce.per_fn_obtie_perio(ls_codperio,
                                                      ln_id_pais,
                                                      ln_id_marca,
                                                      ln_id_canal,
                                                      CANTIDAD_PERIODOS) is null THEN
                isvalid := FALSE;
            END IF;
          exception
          when others then
              isvalid := FALSE;
          end;
          --

          -- Actualizamos CAMPOS ADICIONALES
          IF ls_codperio IS NOT NULL AND v_estaoidestaclie(i) IN (1,
                                                                  2,
                                                                  7,
                                                                  8) THEN
            UPDATE int_solic_conso_ingre_metas dat
               SET dat.cod_camp_inic = ls_codperio
             WHERE dat.cod_pais = v_codpais(i)
               AND lpad(dat.cod_clie,
                        15,
                        '0') = lpad(v_codclie(i),
                                    15,
                                    '0')
               AND dat.num_docu = v_numdocu(i)
               AND dat.num_lote = v_numlote(i);
          END IF;

          -- Actualizamos Documentos Validados OK

          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumdocu(i);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_CAMPA_INICI: ' || ls_sqlerrm);

  END sto_pr_sim_campa_inici;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_DATOS_OBLIG
                        Procedimiento de Validacion de Datos Obligatorios
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_meta,
             cons.val_mnto_meta
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    CURSOR c_actualizadatos2 IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_moti_rech IN (1,
                                    2)
         AND cons.cod_camp_inic IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_ingre_metas.tip_meta%TYPE;
    TYPE t_mntmeta IS TABLE OF int_solic_conso_ingre_metas.val_mnto_meta%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tipmeta    t_tipmeta;
    v_mntmeta    t_mntmeta;

    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    i       BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_tipmeta,
             v_mntmeta LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := TRUE;

          IF (v_tipmeta(j) IS NULL) THEN
            isvalid := FALSE;
          END IF;

          IF (v_mntmeta(j) IS NULL) THEN
            isvalid := FALSE;
          END IF;

          -- Actualizamos Documentos Validados OK
          IF (isvalid) THEN
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    OPEN c_actualizadatos2;
    LOOP
      FETCH c_actualizadatos2 BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        isvalid := TRUE;

        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);

      END IF;
      EXIT WHEN c_actualizadatos2%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos2;

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
                              'ERROR STO_PR_SIM_DATOS_OBLIG: ' || ls_sqlerrm);

  END sto_pr_sim_datos_oblig;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_INDIC_RECHA
                        Procedimiento de Validacion de Rechazo OCR
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_indic_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_esta = '01';

    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    j            BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_SIM_INDIC_RECHA: ' || ls_sqlerrm);

  END sto_pr_sim_indic_recha;

  /**************************************************************************
  Descripcion         : STO_PR_SIM_ENVIO_SICC
                        Envio de SIM a SICC
  Fecha Creacion      : 03/03/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Nicolás López
  ***************************************************************************/
  PROCEDURE sto_pr_sim_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviosim IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie, -- posicion 7
             cons.num_lote,
             cons.tip_meta, -- posicion 8
             cons.sec_nume_docu,
             nvl(cons.val_mnto_meta,
                 0) val_mnto_meta, -- posicion 9
             cons.cod_camp_inic -- posicion 10
        FROM int_solic_conso_ingre_metas cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_ingre_metas.cod_pais%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_ingre_metas.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_ingre_metas.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_ingre_metas.num_lote%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_ingre_metas.tip_meta %TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_ingre_metas.sec_nume_docu%TYPE;
    TYPE t_mntmeta IS TABLE OF int_solic_conso_ingre_metas.val_mnto_meta%TYPE;
    TYPE t_codcampinic IS TABLE OF int_solic_conso_ingre_metas.cod_camp_inic%TYPE;

    v_codpais     t_codpais;
    v_num_docu    t_num_docu;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_tipmeta     t_tipmeta;
    v_secnumdocu  t_secnumdocu;
    v_mntmeta     t_mntmeta;
    v_codcampinic t_codcampinic;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    ln_id_pais  seg_pais.oid_pais%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;

  BEGIN
    ln_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,
                                                         TRUE);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T',
                                                          TRUE);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD',
                                                          TRUE);

    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);

    OPEN c_enviosim;
    LOOP
      FETCH c_enviosim BULK COLLECT
        INTO v_codpais,
             v_num_docu,
             v_codclie,
             v_numlote,
             v_tipmeta,
             v_secnumdocu,
             v_mntmeta,
             v_codcampinic LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          INSERT INTO nvs_consu_logro
            (cod_pais,
             cod_clie,
             cod_tipo_logr,
             cod_medi_capt,
             imp_logr,
             cmp_inic,
             cmp_fina,
             ori_regi,
             usu_modi,
             fec_modi,
             est_regi,
             est_logr)
          VALUES
            (v_codpais(i),
             v_codclie(i),
             v_tipmeta(i),
             NULL,
             to_number(v_mntmeta(i)),
             v_codcampinic(i),
             per_pkg_repor_perce.per_fn_obtie_perio(v_codcampinic(i),
                                                    ln_id_pais,
                                                    ln_id_marca,
                                                    ln_id_canal,
                                                    3),
             'O',
             psusuario,
             SYSDATE,
             '1',
             '1');

        END LOOP;
        -- Actualizamos Documentos Enviados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualizamos Indicadores de Envio
                 occ.ind_envi = '1',
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoccabecera
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

      END IF;

      EXIT WHEN c_enviosim %NOTFOUND;
    END LOOP;

    CLOSE c_enviosim;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_sim_envio_sicc;

END sto_pkg_proce_valid_sim;
/
