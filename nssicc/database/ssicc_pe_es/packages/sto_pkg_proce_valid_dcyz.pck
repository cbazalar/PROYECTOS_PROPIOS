CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_dcyz AS

  /******************************************************************************
    Author  : Cristhian Roman
    Created : 22/05/2008 03:53:00 p.m.
    Purpose : Procesos de Validaciones de Tipo Documento Solicitud de Credito(SCC)

  *****************************************************************************/

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_VALID_PAIS
                    Procedimiento de Validacion de Pais SCC
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_CODI_MADR_VALID
                    Procedimiento de Validacion de Codigo de cliente madre
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_codig_madre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_NOMB_DUPL_VALID
                    Procedimiento de Validacion de nombre de dupla
  Fecha Creacion    : 05/02/2009
    Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_nombr_dupla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_TELE_DUPL_VALID
                    Procedimiento de Validacion de telefono de dupla
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_telef_dupla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_EDAD_MINI_VALID
                    Procedimiento de Validacion de edad minima de dupla
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_edad_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_EDAD_MAXI_VALID
                    Procedimiento de Validacion de edad maxima de dupla
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_edad_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_DOCUM_RECHA_VALID
                    Procedimiento de Validacion de documento de ocr rechazado
  Fecha Creacion    : 09/12/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_docum_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_VINCU_ACDUP
                      Procedimiento de Validacion de vinculo en la actualizacion de dupla
  Fecha Creacion    : 05/05/2010
  Autor             : José A. cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_vincu_acdup
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
END sto_pkg_proce_valid_dcyz;
/

CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_dcyz IS

  /* Declaracion de Variables */

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_VALI_PAIS_OK
                    Procedimiento de Validacion de Pais Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_pais
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
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             pais.oid_pais
        FROM seg_pais                    pais,
             int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pais.cod_pais = cons.cod_pais;

    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_dupla_cyzon.oid_pais%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;

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

    OPEN c_validapais;
    LOOP
      FETCH c_validapais BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_oid_pais LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_dupla_cyzon
             SET oid_pais = v_oid_pais(i)
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'STO_PR_DCYZ_PAIS: ' || ls_sqlerrm);

  END sto_pr_dcyz_pais;
  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_CODI_MADR_OK
                    Procedimiento de Validacion de Codigo de cliente madre
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_codig_madre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_codigomadre IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             -- clie.oid_clie
             cons.cod_clie
        FROM --mae_clien                   clie,
             int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
    --AND cons.cod_clie IS NOT NULL
    --AND cons.cod_clie = clie.cod_clie;

    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    --  TYPE t_oid_clie IS TABLE OF int_solic_conso_dupla_cyzon.oid_clie_madr%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_dupla_cyzon.cod_clie%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    -- v_oid_clie      t_oid_clie;
    v_cod_clie t_cod_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    --i    BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    existe          BOOLEAN;
    lscodigocliente mae_clien.cod_clie%TYPE;
    lnoidcliente    mae_clien.oid_clie%TYPE;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_codigomadre;
    LOOP
      FETCH c_codigomadre BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;
          --Validar si el código de cliente entrante  ya existe en la tabla mae_clien_ident como cédula (DNI
          SELECT MAX(cod_clie)
            INTO lscodigocliente
            FROM mae_clien       c,
                 mae_clien_ident i
           WHERE clie_oid_clie = oid_clie
             AND i.num_docu_iden = v_cod_clie(j);

          IF lscodigocliente IS NOT NULL THEN

            UPDATE sto_docum_digit
               SET cod_clie = lscodigocliente
             WHERE sec_nume_docu = v_sec_nume_docu(j);

            UPDATE int_solic_conso_dupla_cyzon
               SET cod_clie = lscodigocliente
             WHERE sec_nume_docu = v_sec_nume_docu(j);

            existe := TRUE;
          ELSE
            --entonces validar la existencia del mismo en la tabla mae_clien
            SELECT MAX(cod_clie)
              INTO lscodigocliente --v_codclie(j)
              FROM mae_clien c
             WHERE c.cod_clie = v_cod_clie(j);

            IF lscodigocliente IS NOT NULL THEN
              existe := TRUE;
            ELSE
              existe := FALSE;
            END IF;
          END IF;

          IF (existe) THEN

            SELECT oid_clie INTO lnoidcliente FROM mae_clien c WHERE c.cod_clie = lscodigocliente;

            UPDATE int_solic_conso_dupla_cyzon
               SET oid_clie_madr = lnoidcliente
             WHERE sec_nume_docu = v_sec_nume_docu(j)
               AND num_lote = v_numlote(j);

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_codigomadre%NOTFOUND;
    END LOOP;
    CLOSE c_codigomadre;

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
                              'STO_PR_DCYZ_CODIG_MADRE: ' || ls_sqlerrm);

  END sto_pr_dcyz_codig_madre;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_NOMB_DUPL_OK
                   Procedimiento de Validacion de nombre de dupla
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_nombr_dupla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_nombredupla IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_dupl_nuev,
             cons.val_ape1,
             cons.val_nom1
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_ind_dupl_nuev IS TABLE OF int_solic_conso_dupla_cyzon.ind_dupl_nuev%TYPE;
    TYPE t_val_ape1 IS TABLE OF int_solic_conso_dupla_cyzon.val_ape1%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_solic_conso_dupla_cyzon.val_nom1%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_dupl_nuev t_ind_dupl_nuev;
    v_val_ape1      t_val_ape1;
    v_val_nom1      t_val_nom1;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    existe BOOLEAN := TRUE;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_nombredupla;
    LOOP
      FETCH c_nombredupla BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_ind_dupl_nuev,
             v_val_ape1,
             v_val_nom1 LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
          IF (v_ind_dupl_nuev(i) = 'S') THEN

            IF (v_val_ape1(i) IS NULL OR v_val_nom1(i) IS NULL) THEN

              BEGIN
                existe := FALSE;
              END;

            END IF;

          END IF;

          IF (existe) THEN

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
      EXIT WHEN c_nombredupla%NOTFOUND;
    END LOOP;
    CLOSE c_nombredupla;

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
                              'STO_PR_DCYZ_NOMBR_DUPLA: ' || ls_sqlerrm);

  END sto_pr_dcyz_nombr_dupla;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_TELE_DUPL_OK
                   Procedimiento de Validacion de telefono de dupla
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_telef_dupla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_telefonodupla IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_dupl_nuev,
             cons.val_telf_clie,
             cons.val_celu_clie
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_ind_dupl_nuev IS TABLE OF int_solic_conso_dupla_cyzon.ind_dupl_nuev%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_dupla_cyzon.val_telf_clie%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_dupla_cyzon.val_celu_clie%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_dupl_nuev t_ind_dupl_nuev;
    v_val_telf_clie t_val_telf_clie;
    v_val_celu_clie t_val_celu_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    existe BOOLEAN := TRUE;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_telefonodupla;
    LOOP
      FETCH c_telefonodupla BULK COLLECT
        INTO v_numlote,

             v_sec_nume_docu,
             v_ind_dupl_nuev,
             v_val_telf_clie,
             v_val_celu_clie LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
          IF (v_ind_dupl_nuev(i) = 'S') THEN

            IF (v_val_telf_clie(i) IS NULL AND v_val_celu_clie(i) IS NULL) THEN

              BEGIN
                existe := FALSE;
              END;

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
      EXIT WHEN c_telefonodupla%NOTFOUND;
    END LOOP;
    CLOSE c_telefonodupla;

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
                              'STO_PR_DCYZ_TELEF_DUPLA: ' || ls_sqlerrm);

  END sto_pr_dcyz_telef_dupla;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_EDAD_MINI_OK
                    Procedimiento de Validacion de edad minima de dupla
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_dcyz_edad_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_edadminima IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_naci,
             cons.fec_proc
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_dupla_cyzon.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_fec_naci IS TABLE OF int_solic_conso_dupla_cyzon.fec_naci%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_dupla_cyzon.fec_proc%TYPE;

    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_fec_naci      t_fec_naci;
    v_fec_proc      t_fec_proc;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    lnedadminima    NUMBER;
    existe          BOOLEAN := TRUE;
    lscodigomensaje sto_param_gener_occrr.val_param%TYPE;
  BEGIN

    lnedadminima := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_EDAD_MINIM_DUPLA');

    lscodigomensaje := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_COD_MENS_RECHAZO');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_edadminima;
    LOOP
      FETCH c_edadminima BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_naci,
             v_fec_proc LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
          IF ((months_between(v_fec_proc(i),
                              v_fec_naci(i)) / 12) < lnedadminima) THEN

            BEGIN
              existe := FALSE;

              DELETE FROM msg_buzon_mensa
               WHERE clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i))
                 AND mens_oid_mens =
                     (SELECT oid_mens FROM msg_mensa WHERE cod_mens = lscodigomensaje);

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
                 (SELECT oid_mens FROM msg_mensa WHERE cod_mens = lscodigomensaje),
                 1,
                 SYSDATE,
                 0,
                 1);

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
      EXIT WHEN c_edadminima%NOTFOUND;
    END LOOP;
    CLOSE c_edadminima;

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
                              'STO_PR_DCYZ_EDAD_MINIM: ' || ls_sqlerrm);

  END sto_pr_dcyz_edad_minim;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_EDAD_MAXI_OK
                    Procedimiento de Validacion de edad maxima de dupla
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 05/02/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_dcyz_edad_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_edadmaxima IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_naci,
             cons.fec_proc
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_dupla_cyzon.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_fec_naci IS TABLE OF int_solic_conso_dupla_cyzon.fec_naci%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_dupla_cyzon.fec_proc%TYPE;

    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_fec_naci      t_fec_naci;
    v_fec_proc      t_fec_proc;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    lnedadmaxima    NUMBER;
    existe          BOOLEAN := TRUE;
    lscodigomensaje sto_param_gener_occrr.val_param%TYPE;
  BEGIN

    lnedadmaxima := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_EDAD_MAXIM_DUPLA');

    lscodigomensaje := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_COD_MENS_RECHAZO');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_edadmaxima;
    LOOP
      FETCH c_edadmaxima BULK COLLECT
        INTO v_codclie,
             v_numlote,

             v_sec_nume_docu,
             v_fec_naci,
             v_fec_proc

             LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          existe := TRUE;
          IF ((months_between(v_fec_proc(i),
                              v_fec_naci(i)) / 12) > lnedadmaxima) THEN

            BEGIN
              existe := FALSE;

              DELETE FROM msg_buzon_mensa
               WHERE clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i))
                 AND mens_oid_mens =
                     (SELECT oid_mens FROM msg_mensa WHERE cod_mens = lscodigomensaje);

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
                 (SELECT oid_mens FROM msg_mensa WHERE cod_mens = lscodigomensaje),
                 1,
                 SYSDATE,
                 0,
                 1);

            END;

          END IF;

          IF (existe) THEN

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
      EXIT WHEN c_edadmaxima%NOTFOUND;
    END LOOP;
    CLOSE c_edadmaxima;

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
                              'STO_PR_SCC_EDAD_MAXIM: ' || ls_sqlerrm);

  END sto_pr_dcyz_edad_maxim;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_DOCUM_RECHA_OK
                    Procedimiento de Validacion de documento ocr rechazado
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 09/12/2009
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_dcyz_docum_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_duplacyzone IS
      SELECT

       cons.num_lote,
       cons.sec_nume_docu,
       cons.cod_stat_proc,
       cons.cod_moti_rech
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_ind_stat_proc IS TABLE OF int_solic_conso_dupla_cyzon.cod_stat_proc%TYPE;
    TYPE t_ind_moti_rech IS TABLE OF int_solic_conso_dupla_cyzon.cod_moti_rech%TYPE;

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

    OPEN c_duplacyzone;
    LOOP
      FETCH c_duplacyzone BULK COLLECT
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
               (v_ind_moti_rech(j) IN ('D',
                                        'I',
                                        'N'))) THEN

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
      EXIT WHEN c_duplacyzone%NOTFOUND;
    END LOOP;
    CLOSE c_duplacyzone;

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
                              'ERROR STO_PR_DCYZ_DOCUM_RECHA: ' || ls_sqlerrm);

  END sto_pr_dcyz_docum_recha;

  /**************************************************************************
  Descripcion       : STO_PR_DCYZ_VINCU_ACDUP
                    Procedimiento de Validacion de vinculo en la actualizacion de dupla
  Fecha Creacion    : 05/05/2010
  Autor             : jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_dcyz_vincu_acdup
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_vinculodupla IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_actu_dato
        FROM int_solic_conso_dupla_cyzon cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_dupla_cyzon.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_dupla_cyzon.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_dupla_cyzon.sec_nume_docu%TYPE;
    TYPE t_ind_actu_dato IS TABLE OF int_solic_conso_dupla_cyzon.ind_actu_dato%TYPE;

    v_codclie  t_codclie;
    v_num_lote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_ind_actu_dato t_ind_actu_dato;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

    lsoidvinculodupla sto_param_gener_occrr.val_param%TYPE;

    lnvinculo NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsoidvinculodupla := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_OID_VINCULO_DUPL');

    OPEN c_vinculodupla;
    LOOP
      FETCH c_vinculodupla BULK COLLECT
        INTO v_codclie,
             v_num_lote,
             v_sec_nume_docu,
             v_ind_actu_dato LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          IF (v_ind_actu_dato(j) = 'S') THEN
            BEGIN
              SELECT 1
                INTO lnvinculo
                FROM mae_clien_vincu
               WHERE clie_oid_clie_vnte IN
                     (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(j))
                 AND tivc_oid_tipo_vinc = lsoidvinculodupla
                 AND fec_hast IS NULL;
            EXCEPTION
              WHEN no_data_found THEN
                UPDATE int_solic_conso_dupla_cyzon
                   SET ind_dupl_nuev = 'S',
                       ind_actu_dato = 'N'
                 WHERE sec_nume_docu = v_sec_nume_docu(j)
                   AND num_lote = v_num_lote(j);

            END;

          END IF;
        END LOOP;

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
      EXIT WHEN c_vinculodupla%NOTFOUND;

    END LOOP;
    CLOSE c_vinculodupla;

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
                              'ERROR STO_PR_DCYZ_VINCU_ACDUP: ' || ls_sqlerrm);

  END sto_pr_dcyz_vincu_acdup;
END sto_pkg_proce_valid_dcyz;
/

