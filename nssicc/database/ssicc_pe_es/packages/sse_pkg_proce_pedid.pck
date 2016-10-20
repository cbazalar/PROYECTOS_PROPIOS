CREATE OR REPLACE PACKAGE sse_pkg_proce_pedid IS

  -- Author  : PEEXTIVEGA
  -- Created : 22/02/2008 04:58:47 p.m.
  -- Purpose : Paquete que contiene los procedimientos que procesan los pedidos con productos session experte de consultoras session experte

  /*************************************************************/
  /* **************** Declaracion de Funciones *************** */
  /*************************************************************/

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_PROGR_SESSI_ACTU
                     Devuelve Programa Session Experte Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodPeriodo : Codigo de periodo actual
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_progr_sessi_actu
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_CODPR_BY_CODVTA
                     Devuelve el Codigo de Producto de un Periodo del Programa Session Experte Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoPeriodo : Codigo de periodo actual
      psCodigoVenta : Codigo de venta de producto
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_codpr_by_codvta
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoventa    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_LIMI_UNID_PROD
                     Devuelve el valor limite de unidades para un producto en el programa
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoProducto : Codigo de producto
      psCodigoAnho  : Codigo de año
      psCodigoCiclo : Codigo de ciclo
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_limi_unid_prod
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoproducto VARCHAR2,
    pscodigoanho     NUMBER,
    pscodigociclo    VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_LIMI_FREC_PRDO
                     Devuelve el valor limite de frecuencia para un producto en el programa
  Fecha Creacion     : 28/05/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoProducto : Codigo de producto
      psCodigoAnho  : Codigo de año
      psCodigoCiclo : Codigo de ciclo
  Autor              : RRG
  ***************************************************************************/
  FUNCTION sse_fn_devue_limi_frec_prdo
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoproducto VARCHAR2,
    pscodigoanho     NUMBER,
    pscodigociclo    VARCHAR2
  ) RETURN NUMBER;

  /*************************************************************/
  /* **************** Declaracion de Procedimientos ********** */
  /*************************************************************/

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_PEDID_CONSU_SSE
                    Funcion principal que llama a los procedimientos que
                    se deben ejecutar antes de la facturacion
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_pedid_consu_sse
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_CIERR_CONSU_SSE
                    Procedimiento principal que llama a los procedimientos que
                    se deben ejecutar para el cierre de facturacion
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_cierr_consu_sse
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_CONSU_SSE
                    Registra o actualiza la informacion de una consultora
                    session experte en la tabla SSE_CONSU_SESIO_EXPER.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_consu_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_PROD_SSE
                    Inicializa el listado de productos que una consultora
                    session experte puede tener en la tabla SSE_DETAL_PRODU_PERIO.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/

  PROCEDURE sse_pr_carga_prod_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_PROD_ACUM_SSE
                    Inicializa el acumulado del listado de productos que una
                    consultora session experte puede tener en la tabla SSE_PRODU_CONSU.
  Fecha Creacion    : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_prod_acum_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_IND_ERROR_SSE
                    Validacion de Productos SSE solicitados por Consultoras
                    que no son Session Experte
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_ind_error_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_UNIDA_DEMAN
                    Validacion de Productos SSE solicitados que pidieron mas
                    de las unidades que se le permite a una consultora Session Experte.
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_unida_deman
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_CONSU
                    Cambio de EST_REGI a '1' de consultoras de la tabla SSE_CONSUL_SESIO_EXPER
                    para indicar que ya se facturo su ultima campaña
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_consu
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_PROD
                    Actualiza el IND_FACTU a 1 a los productos no facturados de la tabla SSE_DETAL_PRODU_PERIO
                    y actualiza el valor de unidades reales pedidas
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_prod
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_PROD_ACUM
                    Actualiza los acumulados que se almacenan en SSE_PRODU_CONSU
                    a partir de la tabla SSE_DETAL_PRODU_PERIO
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_prod_acum
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_PEDID_CONSU_SSE
                    Procedimiento principal que llama a los procedimientos que
                    se deben ejecutar antes de la facturacion, ejecutado desde
                    la validacion de STO
  Fecha Creacion    : 15/04/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_pedid_consu_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  );

END sse_pkg_proce_pedid;
/
CREATE OR REPLACE PACKAGE BODY sse_pkg_proce_pedid IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  ind_error_sse_session_experte CONSTANT VARCHAR2(1) := 'S';

  /*************************************************************/
  /* **************** Declaracion de Funciones *************** */
  /*************************************************************/

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_PROGR_SESSI_ACTU
                     Devuelve Programa Session Experte Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodPeriodo : Codigo de periodo actual
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_progr_sessi_actu
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_programa VARCHAR2(6);

    CURSOR cprogramasse IS
      SELECT prog.cod_prog
        FROM sse_progr_param_sesio prog,
             sse_progr_anho        progah
       WHERE prog.cod_pais = pscodpais
         AND prog.cod_pais = progah.cod_pais
         AND prog.cod_prog = progah.cod_prog
         AND pscodperiodo BETWEEN progah.cam_inic AND progah.cam_fin -- el periodo actual debe estar en el rango de campañas permitidas para Session Experte
         AND progah.est_regi = 1 -- activo
       ORDER BY prog.cod_prog DESC; -- mayor programa
  BEGIN

    BEGIN

      SELECT prog.cod_prog
        INTO v_cod_programa
        FROM sse_progr_param_sesio prog,
             sse_progr_anho        progah
       WHERE prog.cod_pais = pscodpais
         AND prog.cod_pais = progah.cod_pais
         AND prog.cod_prog = progah.cod_prog
         AND pscodperiodo BETWEEN progah.cam_inic AND progah.cam_fin
         AND progah.est_regi = 1 -- activo
      ;

      RETURN v_cod_programa;

    EXCEPTION
      WHEN too_many_rows THEN
        FOR c1 IN cprogramasse
        LOOP
          v_cod_programa := c1.cod_prog;
          EXIT;
        END LOOP;
        RETURN v_cod_programa;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;

  END sse_fn_devue_progr_sessi_actu;
  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_CODPR_BY_CODVTA
                     Devuelve el Codigo de Producto de un Periodo del Programa Session Experte Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoPeriodo : Codigo de periodo actual
      psCodigoVenta : Codigo de venta de producto
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_codpr_by_codvta
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoventa    VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_prod VARCHAR2(16);

    CURSOR cequivmatr IS
      SELECT equiv.cod_prod
        FROM sse_equiv_matr equiv
       WHERE equiv.cod_pais = pscodigopais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_venta = pscodigoventa --and
      --equiv.EST_REGI = '1' -- activo
       ORDER BY equiv.cod_prod DESC; -- mayor codigo de producto
  BEGIN

    BEGIN

      SELECT equiv.cod_prod
        INTO v_cod_prod
        FROM sse_equiv_matr equiv
       WHERE equiv.cod_pais = pscodigopais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_venta = pscodigoventa --and
      --equiv.EST_REGI = '1'
      ;

      RETURN v_cod_prod;

    EXCEPTION
      WHEN too_many_rows THEN
        FOR c1 IN cequivmatr
        LOOP
          v_cod_prod := c1.cod_prod;
          EXIT;
        END LOOP;
        RETURN v_cod_prod;
      WHEN no_data_found THEN
        v_cod_prod := '-1';
        RETURN v_cod_prod;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_FN_DEVUE_CODPR_BY_CODVTA: ' || ls_sqlerrm);
  END sse_fn_devue_codpr_by_codvta;

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_LIMI_UNID_PROD
                     Devuelve el valor limite de unidades para un producto en el programa
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoProducto : Codigo de producto
      psCodigoAnho  : Codigo de año
      psCodigoCiclo : Codigo de ciclo
  Autor              : Isabel Vega Palomino
  ***************************************************************************/
  FUNCTION sse_fn_devue_limi_unid_prod
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoproducto VARCHAR2,
    pscodigoanho     NUMBER,
    pscodigociclo    VARCHAR2
  )

   RETURN NUMBER IS
    v_val_limi_unid sse_progr_produ_ciclo.val_limi_unid%TYPE;

  BEGIN
    SELECT prcl.val_limi_unid
      INTO v_val_limi_unid
      FROM sse_progr_produ_ciclo prcl
     WHERE prcl.cod_pais = pscodigopais
       AND prcl.cod_prog = pscodigoprograma
       AND prcl.cod_prod = pscodigoproducto
       AND prcl.cod_anho = pscodigoanho
       AND prcl.cod_ciclo = pscodigociclo;

    RETURN v_val_limi_unid;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_FN_DEVUE_LIMI_UNID_PROD: ' || ls_sqlerrm);
  END sse_fn_devue_limi_unid_prod;

  /**************************************************************************
  Descripcion        : SSE_FN_DEVUE_LIMI_FREC_PRDO
                     Devuelve el valor limite de frecuencia para un producto en el programa
  Fecha Creacion     : 28/05/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoProducto : Codigo de producto
      psCodigoAnho  : Codigo de año
      psCodigoCiclo : Codigo de ciclo
  Autor              : RRG
  ***************************************************************************/
  FUNCTION sse_fn_devue_limi_frec_prdo
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoproducto VARCHAR2,
    pscodigoanho     NUMBER,
    pscodigociclo    VARCHAR2
  )

   RETURN NUMBER IS
    v_val_limi_frec sse_progr_produ_ciclo.val_limi_frec%TYPE;

  BEGIN
    SELECT prcl.val_limi_frec
      INTO v_val_limi_frec
      FROM sse_progr_produ_ciclo prcl
     WHERE prcl.cod_pais = pscodigopais
       AND prcl.cod_prog = pscodigoprograma
       AND prcl.cod_prod = pscodigoproducto
       AND prcl.cod_anho = pscodigoanho
       AND prcl.cod_ciclo = pscodigociclo;

    RETURN v_val_limi_frec;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_FN_DEVUE_LIMI_FREC_PRDO: ' || ls_sqlerrm);
  END sse_fn_devue_limi_frec_prdo;

  /*************************************************************/
  /* **************** Declaracion de Procedimientos ********** */
  /*************************************************************/

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_PEDID_CONSU_SSE
                    Procedimiento principal que llama a los procedimientos que
                    se deben ejecutar antes de la facturacion
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_pedid_consu_sse
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  )

   IS
    v_cod_programa VARCHAR2(3);
  BEGIN

    -- Obtener el programa session experte actual en base al periodo actual
    v_cod_programa := sse_pkg_proce_pedid.sse_fn_devue_progr_sessi_actu(pscodigopais,
                                                                        pscodigoperiodo);

    IF (v_cod_programa IS NOT NULL) THEN
      BEGIN

        -- Carga de consultoras session experte a partir de pedidos
        sse_pr_carga_consu_sse(pscodigopais,
                               pscodigoperiodo,
                               v_cod_programa,
                               psusuario);

        -- Carga de productos acumulados por consultora y programa
        sse_pr_carga_prod_acum_sse(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_programa,
                                   psusuario);

        -- Carga de productos solicitados por consultora session experte
        sse_pr_carga_prod_sse(pscodigopais,
                              pscodigoperiodo,
                              v_cod_programa,
                              psusuario);

        -- Actualiza Indicador Error para Pedidos con productos SSE consultoras NO SSE
        sse_pr_actua_ind_error_sse(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_programa,
                                   psusuario);

        -- Actualiza Unidades fuera de Limite de Unidades para pedidos productos SSE
        sse_pr_actua_unida_deman(pscodigopais,
                                 pscodigoperiodo,
                                 v_cod_programa,
                                 psusuario);

      END;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_PROCESA_PEDID_CONSU_SSE: ' || ls_sqlerrm);
  END sse_pr_procesa_pedid_consu_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_CIERR_CONSU_SSE
                    Procedimiento principal que llama a los procedimientos que
                    se deben ejecutar para el cierre de facturacion
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_cierr_consu_sse
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  )

   IS
    v_cod_programa VARCHAR2(3);
  BEGIN

    -- Obtener el programa session experte actual en base al periodo actual
    v_cod_programa := sse_pkg_proce_pedid.sse_fn_devue_progr_sessi_actu(pscodigopais,
                                                                        pscodigoperiodo);

    IF (v_cod_programa IS NOT NULL) THEN
      BEGIN
        -- Actualiza las consultoras session experte con pedidos facturados
        sse_pr_cierr_actua_consu(pscodigopais,
                                 pscodigoperiodo,
                                 v_cod_programa,
                                 psusuario);

        -- Actualiza productos session experte facturados
        sse_pr_cierr_actua_prod(pscodigopais,
                                pscodigoperiodo,
                                v_cod_programa,
                                psusuario);

        -- Actualiza los acumulados de los productos session experte facturados
        sse_pr_cierr_actua_prod_acum(pscodigopais,
                                     pscodigoperiodo,
                                     v_cod_programa,
                                     psusuario);
      END;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_PROCESA_PEDID_CONSU_SSE: ' || ls_sqlerrm);
  END sse_pr_procesa_cierr_consu_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_CONSU_SSE
                    Registra o actualiza la informacion de una consultora
                    session experte en la tabla SSE_CONSU_SESIO_EXPER.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_consu_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS
    -- Consultoras que NO estan en SSE_CONSUL_SESIO_EXPER y que si son session experte
    CURSOR curinsconsulsession IS
      SELECT pscodigopais     AS cod_pais,
             pscodigoprograma AS cod_programa,
             cab.cod_clie     AS cod_consu,
             pscodigoperiodo  AS cam_prime_pedid,
             pscodigoperiodo  AS cam_ultim_pedid,
             -- Hallando la campaña de afiliacion para la Consultora
             -- cuando obtuvo la Clasificacion Session Experte
             (SELECT spc.cod_peri
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl,
                     cra_perio             cp,
                     seg_perio_corpo       spc
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND cp.oid_peri = cl.perd_oid_peri
                 AND cp.peri_oid_peri = spc.oid_peri
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie
                 AND spc.cod_peri <= pscodigoperiodo) AS cam_afili,
             '0' AS est_regi, -- 0: se acaba de registrar un pedido para esta consultora, 1: ya se facturo el ultimo pedido de la consultora
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM int_solic_conso_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND
            -- Consultora no esta en tabla SSE_CONSU_SESIO_EXPER
             NOT EXISTS (SELECT consul.cod_consu
                FROM sse_consu_sesio_exper consul
               WHERE consul.cod_pais = pscodigopais
                 AND consul.cod_prog = pscodigoprograma
                 AND consul.cod_consu = cab.cod_clie)
         AND
            -- cliente es consultora session experte pues tiene clasificacion '31'
             EXISTS (SELECT tcl.cod_tipo_clas
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie)
         AND
            -- flags de no facturado
             cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0';

    -- Consultoras que SI estan en SSE_CONSUL_SESIO_EXPER
    CURSOR curupdconsulsession IS
      SELECT pscodigopais AS cod_pais,
             pscodigoprograma AS cod_programa,
             cab.cod_clie AS cod_consu,
             pscodigoperiodo AS cam_ultim_pedid,
             '0' AS est_regi, -- 0: se acaba de registrar un pedido para esta consultora
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND
            -- Consultora SI esta en tabla SSE_CONSU_SESIO_EXPER
             EXISTS (SELECT consul.cod_consu
                FROM sse_consu_sesio_exper consul
               WHERE consul.cod_pais = pscodigopais
                 AND consul.cod_prog = pscodigoprograma
                 AND consul.cod_consu = cab.cod_clie)
         AND
            -- cliente es consultora session experte pues tiene clasificacion '31'
             EXISTS (SELECT tcl.cod_tipo_clas
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie)
         AND
            -- flags de no facturado
             cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0';

    TYPE t_cod_pais IS TABLE OF sse_consu_sesio_exper.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_consu_sesio_exper.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_consu_sesio_exper.cod_consu%TYPE;
    TYPE t_cam_prime_pedid IS TABLE OF sse_consu_sesio_exper.cam_prime_pedid%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF sse_consu_sesio_exper.cam_ultim_pedid%TYPE;
    TYPE t_cam_afili IS TABLE OF sse_consu_sesio_exper.cam_afili%TYPE;
    TYPE t_est_reg IS TABLE OF sse_consu_sesio_exper.est_reg%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_consu_sesio_exper.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_consu_sesio_exper.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cam_prime_pedid t_cam_prime_pedid;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_cam_afili       t_cam_afili;
    v_est_reg         t_est_reg;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;
    v_usu_modi        t_usu_digi;
    v_fec_modi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    j               BINARY_INTEGER := 0;
    v_row_count     NUMBER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    OPEN curupdconsulsession;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulsession BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_ultim_pedid,
             v_est_reg,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulsession%ROWCOUNT;
      v_row_count := curupdconsulsession%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE sse_consu_sesio_exper
           SET cam_ultim_pedid = v_cam_ultim_pedid(j),
               est_reg         = v_est_reg(j),
               usu_modi        = v_usu_modi(j),
               fec_modi        = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_consu = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulsession;

    -- Inserta en SSE_CONSU_SESIO_EXPER
    OPEN curinsconsulsession;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsulsession BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_cam_afili,
             v_est_reg,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsconsulsession%ROWCOUNT;
      v_row_count_ins := curinsconsulsession%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_consu_sesio_exper
          (cod_pais,
           cod_prog,
           cod_consu,
           cam_prime_pedid,
           cam_ultim_pedid,
           cam_afili,
           est_reg,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_cam_afili(i),
           v_est_reg(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsconsulsession;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_CONSU_SSE: ' || ls_sqlerrm);

  END sse_pr_carga_consu_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_PROD_SSE
                    Inicializa el listado de productos que una consultora
                    session experte puede tener en la tabla SSE_DETAL_PRODU_PERIO.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/

  PROCEDURE sse_pr_carga_prod_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    -- Productos que NO estan en SSE_DETAL_PRODU_PERIO y que si son session experte
    CURSOR curinsproduperio IS
      SELECT pscodigopais     AS cod_pais,
             pscodigoprograma AS cod_programa,
             consu.cod_consu  AS cod_consu,
             pscodigoperiodo  AS cod_periodo,
             equiv.cod_venta  AS cod_venta,
             0                AS val_unida_pedid,
             0                AS val_unida_real,
             0                AS ind_factu,
             psusuario        AS usu_digi,
             SYSDATE          AS fec_digi
        FROM sse_consu_sesio_exper consu,
             sse_equiv_matr        equiv
       WHERE
      -- MULTIPLICACION: Todas las consultoras con todos los productos
      -- join SSE_CONSU_SESIO_EXPER, no se limita x consultora justamente para que se listen todas
       consu.cod_pais = equiv.cod_pais
       AND consu.cod_prog = equiv.cod_prog
       AND consu.cam_ultim_pedid = pscodigoperiodo
       AND consu.est_reg = '0'
       AND -- consultora recientemente actualizada o insertada
      -- join SSE_EQUIV_MATR, no se limita x producto justamente para que se listen todos
       equiv.cod_pais = pscodigopais
       AND equiv.cod_prog = pscodigoprograma
       AND equiv.cod_peri = pscodigoperiodo
       AND -- si se limita el periodo
      -- Producto no esta en tabla SSE_DETAL_PRODU_PERIO
       NOT EXISTS (SELECT detalprod.cod_venta
          FROM sse_detal_produ_perio detalprod
         WHERE detalprod.cod_pais = pscodigopais
           AND detalprod.cod_prog = pscodigoprograma
           AND detalprod.cod_consu = consu.cod_consu
           AND detalprod.cod_peri = pscodigoperiodo
           AND detalprod.cod_venta = equiv.cod_venta)
       AND
      -- Solo se debe insertar un producto pedido en un periodo en la tabla SSE_DETAL_PRODU_PERIO
      -- si es que para ese programa la consultora aun tiene unidades restantes en la tabla SSE_PRODU_CICLO_CONSU
       EXISTS (SELECT prcl.cod_prod
          FROM sse_produ_ciclo_consu prclcn,
               sse_progr_produ_ciclo prcl
         WHERE prclcn.cod_pais = pscodigopais
           AND prclcn.cod_prog = pscodigoprograma
           AND prclcn.cod_consu = consu.cod_consu
           AND prclcn.cod_pais = prcl.cod_pais
           AND prclcn.cod_prog = prcl.cod_prog
           AND prclcn.cod_prod = prcl.cod_prod
           AND prclcn.cod_anho = prcl.cod_anho
           AND prclcn.cod_ciclo = prcl.cod_ciclo
           AND prcl.est_regi = '1'
           AND EXISTS (SELECT NULL
                  FROM sse_progr_anho_ciclo ahcl
                 WHERE ahcl.cod_pais = prcl.cod_pais
                   AND ahcl.cod_prog = prcl.cod_prog
                   AND ahcl.cod_anho = prcl.cod_anho
                   AND ahcl.cod_ciclo = prcl.cod_ciclo
                   AND ahcl.est_regi = '1'
                   AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)
           AND prclcn.cod_prod =
               sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(pscodigopais,
                                                                pscodigoprograma,
                                                                pscodigoperiodo,
                                                                equiv.cod_venta)
           AND ((prclcn.val_unida_resta) > 0 OR (prclcn.val_unida_resta IS NULL))
           AND prclcn.val_frecu_resta > 0);

    TYPE t_cod_pais IS TABLE OF sse_detal_produ_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_detal_produ_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_detal_produ_perio.cod_consu%TYPE;
    TYPE t_cod_peri IS TABLE OF sse_detal_produ_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF sse_detal_produ_perio.cod_venta%TYPE;
    TYPE t_val_unida_pedid IS TABLE OF sse_detal_produ_perio.val_unida_pedid%TYPE;
    TYPE t_val_unida_real IS TABLE OF sse_detal_produ_perio.val_unida_real%TYPE;
    TYPE t_ind_factu IS TABLE OF sse_detal_produ_perio.ind_factu%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_detal_produ_perio.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_detal_produ_perio.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_peri        t_cod_peri;
    v_cod_venta       t_cod_venta;
    v_val_unida_pedid t_val_unida_pedid;
    v_val_unida_real  t_val_unida_real;
    v_ind_factu       t_ind_factu;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    -- Inserta en SSE_PRODU_CONSU
    OPEN curinsproduperio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsproduperio BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_peri,
             v_cod_venta,
             v_val_unida_pedid,
             v_val_unida_real,
             v_ind_factu,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsproduperio%ROWCOUNT;
      v_row_count_ins := curinsproduperio%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_detal_produ_perio
          (cod_pais,
           cod_prog,
           cod_consu,
           cod_peri,
           cod_venta,
           val_unida_pedid,
           val_unida_real,
           ind_factu,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_peri(i),
           v_cod_venta(i),
           v_val_unida_pedid(i),
           v_val_unida_real(i),
           v_ind_factu(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsproduperio;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_PROD_SSE: ' || ls_sqlerrm);

  END sse_pr_carga_prod_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_PROD_ACUM_SSE
                    Inicializa el acumulado del listado de productos que una
                    consultora session experte puede tener en la tabla SSE_PRODU_CICLO_CONSU.
  Fecha Creacion    : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_prod_acum_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS
    -- Productos que NO estan en SSE_PRODU_CICLO_CONSU y que si son session experte
    CURSOR curinsproducicloconsu IS
      SELECT pscodigopais AS cod_pais,
             pscodigoprograma AS cod_programa,
             prcl.cod_prod AS cod_prod,
             consu.cod_consu AS cod_consu,
             prcl.cod_anho AS cod_anho,
             prcl.cod_ciclo AS cod_ciclo,
             0 AS val_unida_acumu,
             sse_pkg_proce_pedid.sse_fn_devue_limi_unid_prod(pscodigopais,
                                                             pscodigoprograma,
                                                             prcl.cod_prod,
                                                             prcl.cod_anho,
                                                             prcl.cod_ciclo) AS val_unida_resta,
             0 AS val_frecu_cont,
             sse_pkg_proce_pedid.sse_fn_devue_limi_frec_prdo(pscodigopais,
                                                             pscodigoprograma,
                                                             prcl.cod_prod,
                                                             prcl.cod_anho,
                                                             prcl.cod_ciclo) AS val_frecu_resta,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM sse_consu_sesio_exper consu,
             sse_progr_produ_ciclo prcl
       WHERE
      -- MULTIPLICACION: Todas las consultoras con todos los productos
      -- join SSE_CONSU_SESIO_EXPER, no se limita x consultora justamente para que se listen todas
       consu.cod_pais = prcl.cod_pais
       AND consu.cod_prog = prcl.cod_prog
       AND consu.cam_ultim_pedid = pscodigoperiodo
       AND consu.est_reg = '0'
      -- consultora recientemente actualizada o insertada
      -- join SSE_PROG_PRODU_CICLO, no se limita x producto justamente para que se listen todos
       AND prcl.cod_pais = pscodigopais
       AND prcl.cod_prog = pscodigoprograma
       AND prcl.est_regi = '1'
      -- inserta producto para un ciclo activo
       AND EXISTS (SELECT NULL
          FROM sse_progr_anho_ciclo ahcl
         WHERE ahcl.cod_pais = prcl.cod_pais
           AND ahcl.cod_prog = prcl.cod_prog
           AND ahcl.cod_anho = prcl.cod_anho
           AND ahcl.cod_ciclo = prcl.cod_ciclo
           AND ahcl.est_regi = '1'
           AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)

      -- Producto no esta en tabla SSE_PRODU_CICLO_CONSU.
       AND NOT EXISTS (SELECT prclcn.cod_prod
          FROM sse_produ_ciclo_consu prclcn
         WHERE prclcn.cod_pais = pscodigopais
           AND prclcn.cod_prog = pscodigoprograma
           AND prclcn.cod_prod = prcl.cod_prod
           AND prclcn.cod_consu = consu.cod_consu
           AND prclcn.cod_anho = prcl.cod_anho
           AND prclcn.cod_ciclo = prcl.cod_ciclo);

    TYPE t_cod_pais IS TABLE OF sse_produ_ciclo_consu.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_produ_ciclo_consu.cod_prog%TYPE;
    TYPE t_cod_prod IS TABLE OF sse_produ_ciclo_consu.cod_prod%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_produ_ciclo_consu.cod_consu%TYPE;
    TYPE t_cod_anho IS TABLE OF sse_produ_ciclo_consu.cod_anho%TYPE;
    TYPE t_cod_ciclo IS TABLE OF sse_produ_ciclo_consu.cod_ciclo%TYPE;
    TYPE t_val_unida_acumu IS TABLE OF sse_produ_ciclo_consu.val_unida_acumu%TYPE;
    TYPE t_val_unida_resta IS TABLE OF sse_produ_ciclo_consu.val_unida_resta%TYPE;
    TYPE t_val_frecu_cont IS TABLE OF sse_produ_ciclo_consu.val_frecu_cont%TYPE;
    TYPE t_val_frecu_resta IS TABLE OF sse_produ_ciclo_consu.val_frecu_resta%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_produ_ciclo_consu.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_produ_ciclo_consu.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_prod        t_cod_prod;
    v_cod_consu       t_cod_consu;
    v_cod_anho        t_cod_anho;
    v_cod_ciclo       t_cod_ciclo;
    v_val_unida_acumu t_val_unida_acumu;
    v_val_unida_resta t_val_unida_resta;
    v_val_frecu_cont  t_val_frecu_cont;
    v_val_frecu_resta t_val_frecu_resta;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    -- Inserta en   SSE_PRODU_CICLO_CONSU
    OPEN curinsproducicloconsu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsproducicloconsu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_prod,
             v_cod_consu,
             v_cod_anho,
             v_cod_ciclo,
             v_val_unida_acumu,
             v_val_unida_resta,
             v_val_frecu_cont,
             v_val_frecu_resta,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsproducicloconsu%ROWCOUNT;
      v_row_count_ins := curinsproducicloconsu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_produ_ciclo_consu
          (cod_pais,
           cod_prog,
           cod_prod,
           cod_consu,
           cod_anho,
           cod_ciclo,
           val_unida_acumu,
           val_unida_resta,
           val_frecu_cont,
           val_frecu_resta,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_prod(i),
           v_cod_consu(i),
           v_cod_anho(i),
           v_cod_ciclo(i),
           v_val_unida_acumu(i),
           v_val_unida_resta(i),
           v_val_frecu_cont(i),
           v_val_frecu_resta(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsproducicloconsu;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_PROD_ACUM_SSE: ' || ls_sqlerrm);

  END sse_pr_carga_prod_acum_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_IND_ERROR_SSE
                    Validacion de Productos SSE solicitados por Consultoras
                    que no son Session Experte
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_ind_error_sse
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    CURSOR curupdconsodetal IS
      SELECT pscodigopais                  AS cod_pais,
             pscodigoperiodo               AS cod_periodo,
             det.cod_clie                  AS cod_consu,
             det.num_lote                  AS num_lote,
             det.cod_vent                  AS cod_venta,
             det.tip_posic                 AS tip_posic,
             ind_error_sse_session_experte AS ind_erro_sse,
             psusuario                     AS usu_modi,
             SYSDATE                       AS fec_modi
        FROM int_solic_conso_detal det

       WHERE
      -- join INT_SOLIC_CONSO_DETAL
       det.cod_pais = pscodigopais
       AND det.cod_peri = pscodigoperiodo
       AND det.ind_erro_sse = '0'
       AND
      -- Pedidos no facturados
       EXISTS (SELECT cab.cod_clie
          FROM int_solic_conso_cabec cab
         WHERE
        -- join INT_SOLIC_CONSO_CABEC
         cab.cod_pais = det.cod_pais
      AND cab.cod_peri = det.cod_peri
      AND cab.cod_clie = det.cod_clie
      AND cab.num_lote = det.num_lote
      AND
        -- flags de no facturado
         cab.ind_ocs_proc = '0'
      AND cab.ind_proc_gp2 = '0')
       AND
      -- El codigo de venta debe estar asociado a un Producto Session Experte
       EXISTS (SELECT equiv.cod_venta
          FROM sse_equiv_matr equiv
         WHERE equiv.cod_pais = det.cod_pais
           AND equiv.cod_prog = pscodigoprograma
           AND equiv.cod_peri = det.cod_peri
           AND equiv.cod_venta = det.cod_vent)
       AND
      -- Producto Session Experte asignado a una consultora que NO es Session Experte o val_unida_reta = 0 o val_frecu_resta = 0
       NOT EXISTS (SELECT detprodu.cod_venta
          FROM sse_detal_produ_perio detprodu
         WHERE detprodu.cod_pais = det.cod_pais
           AND detprodu.cod_prog = pscodigoprograma
           AND detprodu.cod_consu = det.cod_clie
           AND detprodu.cod_peri = det.cod_peri
           AND detprodu.cod_venta = det.cod_vent
           AND detprodu.ind_factu = '0' -- no facturado
        );

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_ind_erro_sse,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE int_solic_conso_detal
           SET ind_erro_sse = v_ind_erro_sse(j),
               usu_modi     = v_usu_modi(j),
               fec_modi     = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_clie = v_cod_clie(j)
           AND num_lote = v_num_lote(j)
           AND cod_vent = v_cod_vent(j)
           AND tip_posic = v_tip_posic(j);

    END LOOP;
    CLOSE curupdconsodetal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_ACTUA_IND_ERROR_SSE: ' || ls_sqlerrm);

  END sse_pr_actua_ind_error_sse;

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_UNIDA_DEMAN
                    Validacion de Productos SSE solicitados que pidieron mas
                    de las unidades que se le permite a una consultora Session Experte.
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_unida_deman
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    CURSOR curupdconsodetal IS
      SELECT pscodigopais    AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie    AS cod_consu,
             det.num_lote    AS num_lote,
             det.cod_vent    AS cod_venta,
             det.tip_posic   AS tip_posic,
             -- El exceso de unidades se copia a VAL_UNIDA_PEDID (tabla SSE_DETAL_PRODU_PERIO):
             det.val_unid_dem AS val_unid_dem,
             -- Obtener unidades restantes del producto para la consultora
             -- y actualizar el valor pedido en INT_SOLIC_CONSO_DETAL:
             prclcn.val_unida_resta AS nuevo_val_unida_dem,
             prclcn.val_frecu_cont  AS val_frecu_cont,
             prclcn.val_frecu_resta AS val_frecu_resta,
             prclcn.cod_prod        AS cod_prod,
             prcl.ind_limi_unid     AS ind_limi_unid,
             psusuario              AS usu_modi,
             SYSDATE                AS fec_modi
        FROM int_solic_conso_detal det,
             sse_produ_ciclo_consu prclcn,
             sse_progr_produ_ciclo prcl
       WHERE
      --join SSE_PROGR_PRODU_CICLO
       prcl.cod_pais = prclcn.cod_pais
       AND prcl.cod_prog = prclcn.cod_prog
       AND prcl.cod_prod = prclcn.cod_prod
       AND prcl.cod_anho = prclcn.cod_anho
       AND prcl.cod_ciclo = prclcn.cod_ciclo
       AND
      -- join INT_SOLIC_CONSO_DETAL
       det.cod_pais = pscodigopais
       AND det.cod_peri = pscodigoperiodo
       AND
      -- join de SSE_PRODU_CONSU con INT_SOLIC_CONSO_DETAL
       prclcn.cod_pais = det.cod_pais
       AND prclcn.cod_prog = pscodigoprograma
       AND prcl.est_regi = '1'
       AND EXISTS (SELECT NULL
          FROM sse_progr_anho_ciclo ahcl
         WHERE ahcl.cod_pais = prcl.cod_pais
           AND ahcl.cod_prog = prcl.cod_prog
           AND ahcl.cod_anho = prcl.cod_anho
           AND ahcl.cod_ciclo = prcl.cod_ciclo
           AND ahcl.est_regi = '1'
           AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)
       AND prclcn.cod_prod =
       sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(det.cod_pais,
                                                        pscodigoprograma,
                                                        det.cod_peri,
                                                        det.cod_vent)
       AND prclcn.cod_consu = det.cod_clie
       AND
      -- Pedidos no facturados
       EXISTS (SELECT cab.cod_clie
          FROM int_solic_conso_cabec cab
         WHERE
        -- join INT_SOLIC_CONSO_CABEC
         cab.cod_pais = det.cod_pais
      AND cab.cod_peri = det.cod_peri
      AND cab.cod_clie = det.cod_clie
      AND cab.num_lote = det.num_lote
      AND
        -- flags de no facturado
         cab.ind_ocs_proc = '0'
      AND cab.ind_proc_gp2 = '0')
       AND
      -- Producto Session Experte asignado a una consultora que SI es Session Experte
       EXISTS (SELECT detprodu.cod_venta
          FROM sse_detal_produ_perio detprodu
         WHERE detprodu.cod_pais = det.cod_pais
           AND detprodu.cod_prog = pscodigoprograma
           AND detprodu.cod_consu = det.cod_clie
           AND detprodu.cod_peri = det.cod_peri
           AND detprodu.cod_venta = det.cod_vent
           AND detprodu.ind_factu = '0' -- no facturado
        );

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_val_frecu_cont IS TABLE OF sse_produ_ciclo_consu.val_frecu_cont%TYPE;
    TYPE t_cod_prod IS TABLE OF sse_produ_ciclo_consu.cod_prod%TYPE;
    TYPE t_ind_limi_unid IS TABLE OF sse_progr_produ_ciclo.ind_limi_unid%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais           t_cod_pais;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_num_lote           t_num_lote;
    v_cod_vent           t_cod_vent;
    v_tip_posic          t_tip_posic;
    v_val_unid_dem       t_val_unid_dem;
    v_nuevo_val_unid_dem t_val_unid_dem; -- unidades restantes de SSE_PRODU_CONSU
    v_val_frecu_cont     t_val_frecu_cont;
    v_val_frecu_resta    t_val_frecu_cont;
    v_cod_prod           t_cod_prod;
    v_ind_limi_unid      t_ind_limi_unid;
    v_usu_modi           t_usu_modi;
    v_fec_modi           t_fec_modi;

    rows NATURAL := 1000; -- Number of rows to process at a time
    -- i    BINARY_INTEGER := 0;
    j           BINARY_INTEGER := 0;
    k           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_nuevo_val_unid_dem,
             v_val_frecu_cont,
             v_val_frecu_resta,
             v_cod_prod,
             v_ind_limi_unid,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      -- Salvar el valor realmente solicitado (originalmente registrado en INT_SOLIC_CONSO_DETAL) a la tabla SSE_DETAL_PRODU_PERIO
      FORALL k IN 1 .. v_cod_pais.count
        UPDATE sse_detal_produ_perio
           SET val_unida_pedid = v_val_unid_dem(k),
               usu_modi        = v_usu_modi(k),
               fec_modi        = v_fec_modi(k)
         WHERE cod_pais = v_cod_pais(k)
           AND cod_prog = pscodigoprograma
           AND cod_consu = v_cod_clie(k)
           AND cod_peri = v_cod_peri(k)
           AND cod_venta = v_cod_vent(k);

      -- Cambiar el valor de las unidades pedidas en INT_SOLIC_CONSO_DETAL con el valor restante de unidades
      FOR j IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        IF (v_ind_limi_unid(j) = '1') THEN
          IF ((v_val_unid_dem(j)) > (v_nuevo_val_unid_dem(j))) THEN
            UPDATE int_solic_conso_detal
               SET val_unid_dem = v_nuevo_val_unid_dem(j),
                   usu_modi     = v_usu_modi(j),
                   fec_modi     = v_fec_modi(j)
             WHERE cod_pais = v_cod_pais(j)
               AND cod_peri = v_cod_peri(j)
               AND cod_clie = v_cod_clie(j)
               AND num_lote = v_num_lote(j)
               AND cod_vent = v_cod_vent(j)
               AND tip_posic = v_tip_posic(j);
          END IF;
        END IF;
      END LOOP;

    END LOOP;

    CLOSE curupdconsodetal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_ACTUA_UNIDA_DEMAN: ' || ls_sqlerrm);

  END sse_pr_actua_unida_deman;

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_CONSU
                    Cambio de EST_REGI a '1' de consultoras de la tabla SSE_CONSUL_SESIO_EXPER
                    para indicar que ya se facturo su ultima campaña
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_consu
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    -- Consultoras que SI estan en SSE_CONSUL_SESIO_EXPER y que tienen pedidos facturados
    CURSOR curupdconsulsession IS
      SELECT consul.cod_pais AS cod_pais,
             consul.cod_prog AS cod_programa,
             consul.cod_consu AS cod_consu,
             '1' AS est_regi, -- 1: se marca la consultora para indicar que ya se facturaron sus pedidos con productos session experte
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM sse_consu_sesio_exper consul
       WHERE consul.cod_pais = pscodigopais
         AND consul.cod_prog = pscodigoprograma
         AND consul.cam_ultim_pedid = pscodigoperiodo
         AND -- consultora con ultima campaña igual al periodo actual
             consul.est_reg = '0'
         AND -- consultora est_reg = 0', es decir que no ha facturado.. su ultima campaña
             EXISTS (SELECT cab.cod_clie
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_pais = pscodigopais
                 AND cab.cod_peri = pscodigoperiodo
                 AND cab.cod_clie = consul.cod_consu
                 AND
                    -- flags de facturado
                     cab.ind_ocs_proc = '1'
                 AND cab.ind_proc_gp2 = '1');

    TYPE t_cod_pais IS TABLE OF sse_consu_sesio_exper.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_consu_sesio_exper.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_consu_sesio_exper.cod_consu%TYPE;
    TYPE t_est_reg IS TABLE OF sse_consu_sesio_exper.est_reg%TYPE;
    TYPE t_usu_modi IS TABLE OF sse_consu_sesio_exper.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF sse_consu_sesio_exper.fec_modi%TYPE;

    v_cod_pais  t_cod_pais;
    v_cod_prog  t_cod_prog;
    v_cod_consu t_cod_consu;
    v_est_reg   t_est_reg;
    v_usu_modi  t_usu_modi;
    v_fec_modi  t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsulsession;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulsession BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_est_reg,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulsession%ROWCOUNT;
      v_row_count := curupdconsulsession%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE sse_consu_sesio_exper
           SET est_reg  = v_est_reg(j),
               usu_modi = v_usu_modi(j),
               fec_modi = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_consu = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulsession;

  END sse_pr_cierr_actua_consu;

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_PROD
                    Actualiza el IND_FACTU a 1 a los productos no facturados de la tabla SSE_DETAL_PRODU_PERIO
                    y actualiza el valor de unidades reales pedidas
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_prod
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    CURSOR curupddetalprodu IS

      SELECT detal.cod_pais AS cod_pais,
             detal.cod_prog AS cod_programa,
             detal.cod_consu AS cod_consu,
             detal.cod_peri AS cod_periodo,
             detal.cod_venta AS cod_venta,
             soliconsodet.val_unid_dem AS val_unida_real,
             '1' AS ind_factu,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM sse_detal_produ_perio detal,
             int_solic_conso_detal soliconsodet
       WHERE
      -- join SSE_DETAL_PRODU_PERIO
       detal.cod_pais = pscodigopais
       AND detal.cod_prog = pscodigoprograma
       AND detal.cod_peri = pscodigoperiodo
       AND detal.ind_factu = '0'
       AND -- productos no facturados
      -- join SSE_DETAL_PRODU_PERIO
       soliconsodet.cod_pais = detal.cod_pais
       AND soliconsodet.cod_peri = detal.cod_peri
       AND soliconsodet.cod_clie = detal.cod_consu
       AND soliconsodet.cod_vent = detal.cod_venta
       AND soliconsodet.ind_erro_sse = '0'
       AND
      -- Productos de Pedidos SI facturados
       EXISTS (SELECT cab.cod_clie
          FROM int_solic_conso_cabec cab
         WHERE
        -- join INT_SOLIC_CONSO_CABEC
         cab.cod_pais = soliconsodet.cod_pais
      AND cab.cod_peri = soliconsodet.cod_peri
      AND cab.cod_clie = soliconsodet.cod_clie
      AND cab.num_lote = soliconsodet.num_lote
      AND
        -- flags de facturado
         cab.ind_ocs_proc = '1'
      AND cab.ind_proc_gp2 = '1');

    TYPE t_cod_pais IS TABLE OF sse_detal_produ_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_detal_produ_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_detal_produ_perio.cod_consu%TYPE;
    TYPE t_cod_peri IS TABLE OF sse_detal_produ_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF sse_detal_produ_perio.cod_venta%TYPE;
    TYPE t_val_unida_real IS TABLE OF sse_detal_produ_perio.val_unida_real%TYPE;
    TYPE t_ind_factu IS TABLE OF sse_detal_produ_perio.ind_factu%TYPE;
    TYPE t_usu_modi IS TABLE OF sse_detal_produ_perio.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF sse_detal_produ_perio.fec_modi%TYPE;

    v_cod_pais       t_cod_pais;
    v_cod_prog       t_cod_prog;
    v_cod_consu      t_cod_consu;
    v_cod_peri       t_cod_peri;
    v_cod_venta      t_cod_venta;
    v_val_unida_real t_val_unida_real;
    v_ind_factu      t_ind_factu;
    v_usu_modi       t_usu_modi;
    v_fec_modi       t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupddetalprodu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupddetalprodu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_peri,
             v_cod_venta,
             v_val_unida_real,
             v_ind_factu,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupddetalprodu%ROWCOUNT;
      v_row_count := curupddetalprodu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE sse_detal_produ_perio
           SET val_unida_real = v_val_unida_real(j),
               ind_factu      = v_ind_factu(j),
               usu_modi       = v_usu_modi(j),
               fec_modi       = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_consu = v_cod_consu(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_venta = v_cod_venta(j);

    END LOOP;
    CLOSE curupddetalprodu;

  END sse_pr_cierr_actua_prod;

  /**************************************************************************
  Descripcion       : SSE_PR_CIERR_ACTUA_PROD_ACUM
                    Actualiza los acumulados que se almacenan en SSE_PRODU_CICLO_CONSU
                    a partir de la tabla SSE_DETAL_PRODU_PERIO
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_cierr_actua_prod_acum
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  )

   IS

    CURSOR curupdproduconsu IS

      SELECT detal.cod_pais AS cod_pais,
             detal.cod_prog AS cod_programa,
             sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(detal.cod_pais,
                                                              detal.cod_prog,
                                                              detal.cod_peri,
                                                              detal.cod_venta) AS cod_prod,
             detal.cod_consu AS cod_consu,
             -- se utiliza para actualizar acumulados
             detal.val_unida_real AS val_unida_real,
             -- se marcan los acumulados ya procesados en la campaña actual
             detal.cod_peri AS cam_ultim_proce,
             psusuario      AS usu_modi,
             SYSDATE        AS fec_modi
        FROM sse_detal_produ_perio detal
       WHERE
      -- join SSE_DETAL_PRODU_PERIO
       detal.cod_pais = pscodigopais
       AND detal.cod_prog = pscodigoprograma
       AND detal.cod_peri = pscodigoperiodo
       AND
      -- productos marcados para facturar
       detal.ind_factu = '1'
       AND
      -- Consultora que debe facturar en su ultima campaña
       EXISTS (SELECT consu.cod_consu
          FROM sse_consu_sesio_exper consu
         WHERE consu.cod_pais = detal.cod_pais
           AND consu.cod_prog = detal.cod_prog
           AND consu.cod_consu = detal.cod_consu
           AND consu.cam_ultim_pedid = detal.cod_peri
           AND
              -- consultora marcada para facturar su ultima campaña
               consu.est_reg = '1')
       AND
      -- Productos de SSE_PRODU_CICLO_CONSU que aun no han procesado su ultima campaña
       EXISTS (SELECT prclcn.cod_prod
          FROM sse_produ_ciclo_consu prclcn,
               sse_progr_produ_ciclo prcl
         WHERE
        -- join SSE_DETAL_PRODU_PERIO
         prclcn.cod_pais = detal.cod_pais
      AND prclcn.cod_prog = detal.cod_prog
      AND prclcn.cod_prod =
         sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(detal.cod_pais,
                                                          detal.cod_prog,
                                                          detal.cod_peri,
                                                          detal.cod_venta)
      AND prclcn.cod_consu = detal.cod_consu
      AND
        -- la ultima campaña procesada debe ser diferente a la actual
         ((prclcn.cam_ultim_proce <> pscodigoperiodo) OR (prclcn.cam_ultim_proce IS NULL))
      AND

         prcl.cod_pais = prclcn.cod_pais
      AND prcl.cod_prog = prclcn.cod_prog
      AND prcl.cod_prod = prclcn.cod_prod
      AND prcl.cod_anho = prclcn.cod_anho
      AND prcl.cod_ciclo = prclcn.cod_ciclo
      AND prcl.est_regi = '1'
      AND EXISTS (SELECT NULL
            FROM sse_progr_anho_ciclo ahcl
           WHERE ahcl.cod_pais = prcl.cod_pais
             AND ahcl.cod_prog = prcl.cod_prog
             AND ahcl.cod_anho = prcl.cod_anho
             AND ahcl.cod_ciclo = prcl.cod_ciclo
             AND ahcl.est_regi = '1'
             AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin));

    TYPE t_cod_pais IS TABLE OF sse_produ_ciclo_consu.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_produ_ciclo_consu.cod_prog%TYPE;
    TYPE t_cod_prod IS TABLE OF sse_produ_ciclo_consu.cod_prod%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_produ_ciclo_consu.cod_consu%TYPE;
    TYPE t_val_unida_real IS TABLE OF sse_detal_produ_perio.val_unida_real%TYPE;
    TYPE t_cam_ultim_proce IS TABLE OF sse_produ_ciclo_consu.cam_ultim_proce%TYPE;
    TYPE t_usu_modi IS TABLE OF sse_produ_ciclo_consu.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF sse_produ_ciclo_consu.fec_modi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_prod        t_cod_prod;
    v_cod_consu       t_cod_consu;
    v_val_unida_real  t_val_unida_real;
    v_cam_ultim_proce t_cam_ultim_proce;
    v_usu_modi        t_usu_modi;
    v_fec_modi        t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

    ls_ind_limi_unid VARCHAR2(1);
    ls_cod_anho      sse_progr_produ_ciclo.cod_anho%TYPE;
    ls_cod_ciclo     sse_progr_produ_ciclo.cod_ciclo%TYPE;

  BEGIN
    OPEN curupdproduconsu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdproduconsu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_prod,
             v_cod_consu,
             v_val_unida_real,
             v_cam_ultim_proce,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdproduconsu%ROWCOUNT;
      v_row_count := curupdproduconsu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FOR j IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        -- Obtener variables temporales
        SELECT prcl.cod_anho,
               prcl.cod_ciclo,
               prcl.ind_limi_unid
          INTO ls_cod_anho,
               ls_cod_ciclo,
               ls_ind_limi_unid
          FROM sse_progr_produ_ciclo prcl
         WHERE prcl.est_regi = '1'
           AND EXISTS (SELECT NULL
                  FROM sse_progr_anho_ciclo ahcl
                 WHERE ahcl.cod_pais = prcl.cod_pais
                   AND ahcl.cod_prog = prcl.cod_prog
                   AND ahcl.cod_anho = prcl.cod_anho
                   AND ahcl.cod_ciclo = prcl.cod_ciclo
                   AND ahcl.est_regi = '1'
                   AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)
           AND prcl.cod_pais = pscodigopais
           AND prcl.cod_prog = pscodigoprograma
           AND prcl.cod_prod = v_cod_prod(j);

        --Actualizar el valor del contador de frecuencia y campaña ultima procesada
        UPDATE sse_produ_ciclo_consu
           SET val_frecu_cont  = val_frecu_cont + 1,
               val_frecu_resta = val_frecu_resta - 1,
               cam_ultim_proce = v_cam_ultim_proce(j),
               usu_modi        = v_usu_modi(j),
               fec_modi        = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_prod = v_cod_prod(j)
           AND cod_consu = v_cod_consu(j)
           AND cod_anho = ls_cod_anho
           AND cod_ciclo = ls_cod_ciclo;

        -- Actualizar el valor de los acumulados si el producto tiene ind_limi_unid = '1'
        IF (ls_ind_limi_unid = '1') THEN
          UPDATE sse_produ_ciclo_consu
             SET val_unida_acumu = val_unida_acumu + v_val_unida_real(j),
                 val_unida_resta = val_unida_resta - v_val_unida_real(j),
                 usu_modi        = v_usu_modi(j),
                 fec_modi        = v_fec_modi(j)
           WHERE cod_pais = v_cod_pais(j)
             AND cod_prog = v_cod_prog(j)
             AND cod_prod = v_cod_prod(j)
             AND cod_consu = v_cod_consu(j);
        END IF;
      END LOOP;
    END LOOP;
    CLOSE curupdproduconsu;

  END sse_pr_cierr_actua_prod_acum;

  /*********************/
  /* Procesos para STO */
  /*********************/

  /**************************************************************************
  Descripcion          : SSE_PR_CARGA_PROD_ACUM_STO
                       Inicializa el acumulado del listado de productos que una
                       consultora session experte puede tener en la tabla SSE_PRODU_CICLO_CONSU.
                       ejecutado desde la validacion de STO
  Fecha Creacion       : 15/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor                : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_prod_acum_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2
  )

   IS
    -- Productos que NO estan en SSE_PRODU_CICLO_CONSU y que si son session experte
    CURSOR curinsproducicloconsu IS
      SELECT pscodigopais AS cod_pais,
             pscodigoprograma AS cod_programa,
             prcl.cod_prod AS cod_prod,
             consu.cod_consu AS cod_consu,
             prcl.cod_anho AS cod_anho,
             prcl.cod_ciclo AS cod_ciclo,
             0 AS val_unida_acumu,
             sse_pkg_proce_pedid.sse_fn_devue_limi_unid_prod(pscodigopais,
                                                             pscodigoprograma,
                                                             prcl.cod_prod,
                                                             prcl.cod_anho,
                                                             prcl.cod_ciclo) AS val_unida_resta,
             0 AS val_frecu_cont,
             sse_pkg_proce_pedid.sse_fn_devue_limi_frec_prdo(pscodigopais,
                                                             pscodigoprograma,
                                                             prcl.cod_prod,
                                                             prcl.cod_anho,
                                                             prcl.cod_ciclo) AS val_frecu_resta,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM sse_consu_sesio_exper consu,
             sse_progr_produ_ciclo prcl,
             int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE
      -- MULTIPLICACION: Todas las consultoras con todos los productos
      -- join SSE_CONSU_SESIO_EXPER, no se limita x consultora justamente para que se listen todas
       consu.cod_pais = prcl.cod_pais
       AND consu.cod_prog = prcl.cod_prog
       AND consu.cam_ultim_pedid = pscodigoperiodo
       AND consu.est_reg = '0'

       AND cab.cod_pais = consu.cod_pais
       AND cab.cod_clie = consu.cod_consu
       AND cab.cod_peri = pscodigoperiodo
      -- JOIN STO
       AND tmp.num_lote = cab.num_lote
       AND tmp.sec_nume_docu = cab.sec_nume_docu
       AND tmp.num_proc = psnumeroproceso
       AND tmp.cod_tipo_docu = pscodtipodocu
      -- consultora recientemente actualizada o insertada
      -- join SSE_PROG_PRODU_CICLO, no se limita x producto justamente para que se listen todos
       AND prcl.cod_pais = pscodigopais
       AND prcl.cod_prog = pscodigoprograma
       AND prcl.est_regi = '1'
      -- inserta producto para un ciclo activo
       AND EXISTS (SELECT NULL
          FROM sse_progr_anho_ciclo ahcl
         WHERE ahcl.cod_pais = prcl.cod_pais
           AND ahcl.cod_prog = prcl.cod_prog
           AND ahcl.cod_anho = prcl.cod_anho
           AND ahcl.cod_ciclo = prcl.cod_ciclo
           AND ahcl.est_regi = '1'
           AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)

      -- Producto no esta en tabla SSE_PRODU_CICLO_CONSU.
       AND NOT EXISTS (SELECT prclcn.cod_prod
          FROM sse_produ_ciclo_consu prclcn
         WHERE prclcn.cod_pais = pscodigopais
           AND prclcn.cod_prog = pscodigoprograma
           AND prclcn.cod_prod = prcl.cod_prod
           AND prclcn.cod_consu = consu.cod_consu
           AND prclcn.cod_anho = prcl.cod_anho
           AND prclcn.cod_ciclo = prcl.cod_ciclo);

    TYPE t_cod_pais IS TABLE OF sse_produ_ciclo_consu.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_produ_ciclo_consu.cod_prog%TYPE;
    TYPE t_cod_prod IS TABLE OF sse_produ_ciclo_consu.cod_prod%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_produ_ciclo_consu.cod_consu%TYPE;
    TYPE t_cod_anho IS TABLE OF sse_produ_ciclo_consu.cod_anho%TYPE;
    TYPE t_cod_ciclo IS TABLE OF sse_produ_ciclo_consu.cod_ciclo%TYPE;
    TYPE t_val_unida_acumu IS TABLE OF sse_produ_ciclo_consu.val_unida_acumu%TYPE;
    TYPE t_val_unida_resta IS TABLE OF sse_produ_ciclo_consu.val_unida_resta%TYPE;
    TYPE t_val_frecu_cont IS TABLE OF sse_produ_ciclo_consu.val_frecu_cont%TYPE;
    TYPE t_val_frecu_resta IS TABLE OF sse_produ_ciclo_consu.val_frecu_resta%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_produ_ciclo_consu.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_produ_ciclo_consu.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_prod        t_cod_prod;
    v_cod_consu       t_cod_consu;
    v_cod_anho        t_cod_anho;
    v_cod_ciclo       t_cod_ciclo;
    v_val_unida_acumu t_val_unida_acumu;
    v_val_unida_resta t_val_unida_resta;
    v_val_frecu_cont  t_val_frecu_cont;
    v_val_frecu_resta t_val_frecu_resta;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    -- Inserta en   SSE_PRODU_CICLO_CONSU
    OPEN curinsproducicloconsu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsproducicloconsu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_prod,
             v_cod_consu,
             v_cod_anho,
             v_cod_ciclo,
             v_val_unida_acumu,
             v_val_unida_resta,
             v_val_frecu_cont,
             v_val_frecu_resta,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsproducicloconsu%ROWCOUNT;
      v_row_count_ins := curinsproducicloconsu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_produ_ciclo_consu
          (cod_pais,
           cod_prog,
           cod_prod,
           cod_consu,
           cod_anho,
           cod_ciclo,
           val_unida_acumu,
           val_unida_resta,
           val_frecu_cont,
           val_frecu_resta,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_prod(i),
           v_cod_consu(i),
           v_cod_anho(i),
           v_cod_ciclo(i),
           v_val_unida_acumu(i),
           v_val_unida_resta(i),
           v_val_frecu_cont(i),
           v_val_frecu_resta(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsproducicloconsu;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_PROD_ACUM_STO: ' || ls_sqlerrm);

  END sse_pr_carga_prod_acum_sto;

  /**************************************************************************
  Descripcion       : SSE_PR_CARGA_CONSU_SSE_STO
                      Registra o actualiza la informacion de una consultora
                      session experte en la tabla SSE_CONSU_SESIO_EXPER.
                      Ejecutada desde la validacion de STO
  Fecha Creacion    : 15/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_consu_sse_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2
  )

   IS
    -- Consultoras que NO estan en SSE_CONSUL_SESIO_EXPER y que si son session experte
    CURSOR curinsconsulsession IS
      SELECT pscodigopais     AS cod_pais,
             pscodigoprograma AS cod_programa,
             cab.cod_clie     AS cod_consu,
             pscodigoperiodo  AS cam_prime_pedid,
             pscodigoperiodo  AS cam_ultim_pedid,
             -- Hallando la campaña de afiliacion para la Consultora
             -- cuando obtuvo la Clasificacion Session Experte
             (SELECT spc.cod_peri
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl,
                     cra_perio             cp,
                     seg_perio_corpo       spc
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND cp.oid_peri = cl.perd_oid_peri
                 AND cp.peri_oid_peri = spc.oid_peri
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie
                 AND spc.cod_peri <= pscodigoperiodo) AS cam_afili,
             '0' AS est_regi, -- 0: se acaba de registrar un pedido para esta consultora, 1: ya se facturo el ultimo pedido de la consultora
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND
            -- JOIN STO
             tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND
            -- Consultora no esta en tabla SSE_CONSU_SESIO_EXPER
             NOT EXISTS (SELECT consul.cod_consu
                FROM sse_consu_sesio_exper consul
               WHERE consul.cod_pais = pscodigopais
                 AND consul.cod_prog = pscodigoprograma
                 AND consul.cod_consu = cab.cod_clie)
         AND
            -- cliente es consultora session experte pues tiene clasificacion '31'
             EXISTS (SELECT tcl.cod_tipo_clas
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie)
         AND
            -- flags de no facturado
             cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0';

    -- Consultoras que SI estan en SSE_CONSUL_SESIO_EXPER
    CURSOR curupdconsulsession IS
      SELECT pscodigopais AS cod_pais,
             pscodigoprograma AS cod_programa,
             cab.cod_clie AS cod_consu,
             pscodigoperiodo AS cam_ultim_pedid,
             '0' AS est_regi, -- 0: se acaba de registrar un pedido para esta consultora
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND
            -- JOIN STO
             tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND
            -- Consultora SI esta en tabla SSE_CONSU_SESIO_EXPER
             EXISTS (SELECT consul.cod_consu
                FROM sse_consu_sesio_exper consul
               WHERE consul.cod_pais = pscodigopais
                 AND consul.cod_prog = pscodigoprograma
                 AND consul.cod_consu = cab.cod_clie)
         AND
            -- cliente es consultora session experte pues tiene clasificacion '31'
             EXISTS (SELECT tcl.cod_tipo_clas
                FROM mae_clien,
                     mae_clien_unida_admin,
                     mae_clien_tipo_subti  ts,
                     mae_clien_clasi       cl,
                     mae_tipo_clasi_clien  tcl
               WHERE mae_clien_unida_admin.clie_oid_clie = mae_clien.oid_clie
                 AND mae_clien_unida_admin.ind_acti = 1
                 AND mae_clien.oid_clie = ts.clie_oid_clie(+)
                 AND ts.oid_clie_tipo_subt = cl.ctsu_oid_clie_tipo_subt
                 AND cl.tccl_oid_tipo_clasi = tcl.oid_tipo_clas
                 AND tcl.cod_tipo_clas = '31' --Tipo Clasificación: Session Experté
                 AND mae_clien.cod_clie = cab.cod_clie)
         AND
            -- flags de no facturado
             cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0';

    TYPE t_cod_pais IS TABLE OF sse_consu_sesio_exper.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_consu_sesio_exper.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_consu_sesio_exper.cod_consu%TYPE;
    TYPE t_cam_prime_pedid IS TABLE OF sse_consu_sesio_exper.cam_prime_pedid%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF sse_consu_sesio_exper.cam_ultim_pedid%TYPE;
    TYPE t_cam_afili IS TABLE OF sse_consu_sesio_exper.cam_afili%TYPE;
    TYPE t_est_reg IS TABLE OF sse_consu_sesio_exper.est_reg%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_consu_sesio_exper.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_consu_sesio_exper.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cam_prime_pedid t_cam_prime_pedid;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_cam_afili       t_cam_afili;
    v_est_reg         t_est_reg;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;
    v_usu_modi        t_usu_digi;
    v_fec_modi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    j               BINARY_INTEGER := 0;
    v_row_count     NUMBER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    OPEN curupdconsulsession;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulsession BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_ultim_pedid,
             v_est_reg,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulsession%ROWCOUNT;
      v_row_count := curupdconsulsession%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE sse_consu_sesio_exper
           SET cam_ultim_pedid = v_cam_ultim_pedid(j),
               est_reg         = v_est_reg(j),
               usu_modi        = v_usu_modi(j),
               fec_modi        = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_consu = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulsession;

    -- Inserta en SSE_CONSU_SESIO_EXPER
    OPEN curinsconsulsession;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsulsession BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_cam_afili,
             v_est_reg,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsconsulsession%ROWCOUNT;
      v_row_count_ins := curinsconsulsession%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_consu_sesio_exper
          (cod_pais,
           cod_prog,
           cod_consu,
           cam_prime_pedid,
           cam_ultim_pedid,
           cam_afili,
           est_reg,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_cam_afili(i),
           v_est_reg(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsconsulsession;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_CONSU_SSE_STO: ' || ls_sqlerrm);

  END sse_pr_carga_consu_sse_sto;

  /**************************************************************************
    Descripcion       : SSE_PR_CARGA_PROD_STO
                      Inicializa el listado de productos que una consultora
                      session experte puede tener en la tabla SSE_DETAL_PRODU_PERIO.
                      Ejecutado desde la validacion de STO
    Fecha Creacion    : 15/04/2010
    Parametros Entrada:
        psCodigoPais     : Codigo de pais
        psCodigoPeriodo  : Codigo de periodo
        psCodigoPrograma : Codigo de programa
        psUsuario        : Codigo de Usuario
    Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_carga_prod_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2
  )

   IS
    -- Productos que NO estan en SSE_DETAL_PRODU_PERIO y que si son session experte
    CURSOR curinsproduperio IS
      SELECT pscodigopais     AS cod_pais,
             pscodigoprograma AS cod_programa,
             consu.cod_consu  AS cod_consu,
             pscodigoperiodo  AS cod_periodo,
             equiv.cod_venta  AS cod_venta,
             0                AS val_unida_pedid,
             0                AS val_unida_real,
             0                AS ind_factu,
             psusuario        AS usu_digi,
             SYSDATE          AS fec_digi
        FROM sse_consu_sesio_exper consu,
             sse_equiv_matr        equiv,
             int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE
      -- MULTIPLICACION: Todas las consultoras con todos los productos
      -- join SSE_CONSU_SESIO_EXPER, no se limita x consultora justamente para que se listen todas
       consu.cod_pais = equiv.cod_pais
       AND consu.cod_prog = equiv.cod_prog
       AND consu.cam_ultim_pedid = pscodigoperiodo
       AND consu.est_reg = '0'

       AND cab.cod_pais = consu.cod_pais
       AND cab.cod_clie = consu.cod_consu
       AND cab.cod_peri = pscodigoperiodo
      -- JOIN STO
       AND tmp.num_lote = cab.num_lote
       AND tmp.sec_nume_docu = cab.sec_nume_docu
       AND tmp.num_proc = psnumeroproceso
       AND tmp.cod_tipo_docu = pscodtipodocu
       AND -- consultora recientemente actualizada o insertada
      -- join SSE_EQUIV_MATR, no se limita x producto justamente para que se listen todos
       equiv.cod_pais = pscodigopais
       AND equiv.cod_prog = pscodigoprograma
       AND equiv.cod_peri = pscodigoperiodo
       AND -- si se limita el periodo
      -- Producto no esta en tabla SSE_DETAL_PRODU_PERIO
       NOT EXISTS (SELECT detalprod.cod_venta
          FROM sse_detal_produ_perio detalprod
         WHERE detalprod.cod_pais = pscodigopais
           AND detalprod.cod_prog = pscodigoprograma
           AND detalprod.cod_consu = consu.cod_consu
           AND detalprod.cod_peri = pscodigoperiodo
           AND detalprod.cod_venta = equiv.cod_venta)
       AND
      -- Solo se debe insertar un producto pedido en un periodo en la tabla SSE_DETAL_PRODU_PERIO
      -- si es que para ese programa la consultora aun tiene unidades restantes en la tabla SSE_PRODU_CICLO_CONSU
       EXISTS (SELECT prcl.cod_prod
          FROM sse_produ_ciclo_consu prclcn,
               sse_progr_produ_ciclo prcl
         WHERE prclcn.cod_pais = pscodigopais
           AND prclcn.cod_prog = pscodigoprograma
           AND prclcn.cod_consu = consu.cod_consu
           AND prclcn.cod_pais = prcl.cod_pais
           AND prclcn.cod_prog = prcl.cod_prog
           AND prclcn.cod_prod = prcl.cod_prod
           AND prclcn.cod_anho = prcl.cod_anho
           AND prclcn.cod_ciclo = prcl.cod_ciclo
           AND prcl.est_regi = '1'
           AND EXISTS (SELECT NULL
                  FROM sse_progr_anho_ciclo ahcl
                 WHERE ahcl.cod_pais = prcl.cod_pais
                   AND ahcl.cod_prog = prcl.cod_prog
                   AND ahcl.cod_anho = prcl.cod_anho
                   AND ahcl.cod_ciclo = prcl.cod_ciclo
                   AND ahcl.est_regi = '1'
                   AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)
           AND prclcn.cod_prod =
               sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(pscodigopais,
                                                                pscodigoprograma,
                                                                pscodigoperiodo,
                                                                equiv.cod_venta)
           AND ((prclcn.val_unida_resta) > 0 OR (prclcn.val_unida_resta IS NULL))
           AND prclcn.val_frecu_resta > 0);

    TYPE t_cod_pais IS TABLE OF sse_detal_produ_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF sse_detal_produ_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF sse_detal_produ_perio.cod_consu%TYPE;
    TYPE t_cod_peri IS TABLE OF sse_detal_produ_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF sse_detal_produ_perio.cod_venta%TYPE;
    TYPE t_val_unida_pedid IS TABLE OF sse_detal_produ_perio.val_unida_pedid%TYPE;
    TYPE t_val_unida_real IS TABLE OF sse_detal_produ_perio.val_unida_real%TYPE;
    TYPE t_ind_factu IS TABLE OF sse_detal_produ_perio.ind_factu%TYPE;
    TYPE t_usu_digi IS TABLE OF sse_detal_produ_perio.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF sse_detal_produ_perio.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_peri        t_cod_peri;
    v_cod_venta       t_cod_venta;
    v_val_unida_pedid t_val_unida_pedid;
    v_val_unida_real  t_val_unida_real;
    v_ind_factu       t_ind_factu;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    -- Inserta en SSE_PRODU_CONSU
    OPEN curinsproduperio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsproduperio BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_peri,
             v_cod_venta,
             v_val_unida_pedid,
             v_val_unida_real,
             v_ind_factu,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsproduperio%ROWCOUNT;
      v_row_count_ins := curinsproduperio%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO sse_detal_produ_perio
          (cod_pais,
           cod_prog,
           cod_consu,
           cod_peri,
           cod_venta,
           val_unida_pedid,
           val_unida_real,
           ind_factu,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_peri(i),
           v_cod_venta(i),
           v_val_unida_pedid(i),
           v_val_unida_real(i),
           v_ind_factu(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinsproduperio;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_CARGA_PROD_STO: ' || ls_sqlerrm);

  END sse_pr_carga_prod_sto;

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_IND_ERROR_STO
                    Validacion de Productos SSE solicitados por Consultoras
                    que no son Session Experte. Ejecutado desde la validacion
                    de STO
  Fecha Creacion    : 15/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_ind_error_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2
  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais                  AS cod_pais,
             pscodigoperiodo               AS cod_periodo,
             det.cod_clie                  AS cod_consu,
             det.num_lote                  AS num_lote,
             det.cod_vent                  AS cod_venta,
             det.tip_posic                 AS tip_posic,
             ind_error_sse_session_experte AS ind_erro_sse,
             psusuario                     AS usu_modi,
             SYSDATE                       AS fec_modi
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE
      -- join INT_SOLIC_CONSO_DETAL
       det.cod_pais = pscodigopais
       AND det.cod_peri = pscodigoperiodo
       AND det.ind_erro_sse = '0'
       AND
      -- join INT_SOLIC_CONSO_CABEC
       cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_lote = det.num_lote
       AND
      -- flags de no facturado
       cab.ind_ocs_proc = '0'
       AND cab.ind_proc_gp2 = '0'
       AND
      -- JOIN STO
       tmp.num_lote = cab.num_lote
       AND tmp.sec_nume_docu = cab.sec_nume_docu
       AND tmp.num_proc = psnumeroproceso
       AND tmp.cod_tipo_docu = pscodtipodocu
       AND
      -- El codigo de venta debe estar asociado a un Producto Session Experte
       EXISTS (SELECT equiv.cod_venta
          FROM sse_equiv_matr equiv
         WHERE equiv.cod_pais = det.cod_pais
           AND equiv.cod_prog = pscodigoprograma
           AND equiv.cod_peri = det.cod_peri
           AND equiv.cod_venta = det.cod_vent)
       AND
      -- Producto Session Experte asignado a una consultora que NO es Session Experte o val_unida_reta = 0 o val_frecu_resta = 0
       NOT EXISTS (SELECT detprodu.cod_venta
          FROM sse_detal_produ_perio detprodu
         WHERE detprodu.cod_pais = det.cod_pais
           AND detprodu.cod_prog = pscodigoprograma
           AND detprodu.cod_consu = det.cod_clie
           AND detprodu.cod_peri = det.cod_peri
           AND detprodu.cod_venta = det.cod_vent
           AND detprodu.ind_factu = '0' -- no facturado
        );

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_ind_erro_sse,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE int_solic_conso_detal
           SET ind_erro_sse = v_ind_erro_sse(j),
               usu_modi     = v_usu_modi(j),
               fec_modi     = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_clie = v_cod_clie(j)
           AND num_lote = v_num_lote(j)
           AND cod_vent = v_cod_vent(j)
           AND tip_posic = v_tip_posic(j);

    END LOOP;
    CLOSE curupdconsodetal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_ACTUA_IND_ERROR_STO: ' || ls_sqlerrm);

  END sse_pr_actua_ind_error_sto;

  /**************************************************************************
  Descripcion       : SSE_PR_ACTUA_UNIDA_STO
                      Validacion de Productos SSE solicitados que pidieron mas
                      de las unidades que se le permite a una consultora Session Experte.
                      Ejecutada desde la validacion de STO
  Fecha Creacion    : 15/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_actua_unida_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2

  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais    AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie    AS cod_consu,
             det.num_lote    AS num_lote,
             det.cod_vent    AS cod_venta,
             det.tip_posic   AS tip_posic,
             -- El exceso de unidades se copia a VAL_UNIDA_PEDID (tabla SSE_DETAL_PRODU_PERIO):
             det.val_unid_dem AS val_unid_dem,
             -- Obtener unidades restantes del producto para la consultora
             -- y actualizar el valor pedido en INT_SOLIC_CONSO_DETAL:
             prclcn.val_unida_resta AS nuevo_val_unida_dem,
             prclcn.val_frecu_cont  AS val_frecu_cont,
             prclcn.val_frecu_resta AS val_frecu_resta,
             prclcn.cod_prod        AS cod_prod,
             prcl.ind_limi_unid     AS ind_limi_unid,
             psusuario              AS usu_modi,
             SYSDATE                AS fec_modi
        FROM int_solic_conso_detal det,
             sse_produ_ciclo_consu prclcn,
             sse_progr_produ_ciclo prcl,
             int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE
      --join SSE_PROGR_PRODU_CICLO
       prcl.cod_pais = prclcn.cod_pais
       AND prcl.cod_prog = prclcn.cod_prog
       AND prcl.cod_prod = prclcn.cod_prod
       AND prcl.cod_anho = prclcn.cod_anho
       AND prcl.cod_ciclo = prclcn.cod_ciclo
       AND
      -- join INT_SOLIC_CONSO_DETAL
       det.cod_pais = pscodigopais
       AND det.cod_peri = pscodigoperiodo
       AND
      -- join de SSE_PRODU_CONSU con INT_SOLIC_CONSO_DETAL
       prclcn.cod_pais = det.cod_pais
       AND prclcn.cod_prog = pscodigoprograma
       AND prcl.est_regi = '1'
       AND EXISTS (SELECT NULL
          FROM sse_progr_anho_ciclo ahcl
         WHERE ahcl.cod_pais = prcl.cod_pais
           AND ahcl.cod_prog = prcl.cod_prog
           AND ahcl.cod_anho = prcl.cod_anho
           AND ahcl.cod_ciclo = prcl.cod_ciclo
           AND ahcl.est_regi = '1'
           AND pscodigoperiodo BETWEEN ahcl.cam_inic AND ahcl.cam_fin)
       AND prclcn.cod_prod =
       sse_pkg_proce_pedid.sse_fn_devue_codpr_by_codvta(det.cod_pais,
                                                        pscodigoprograma,
                                                        det.cod_peri,
                                                        det.cod_vent)
       AND prclcn.cod_consu = det.cod_clie
       AND
      -- Pedidos no facturados
       cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_lote = det.num_lote
       AND
      -- flags de no facturado
       cab.ind_ocs_proc = '0'
       AND cab.ind_proc_gp2 = '0'
       AND
      -- JOIN STO
       tmp.num_lote = cab.num_lote
       AND tmp.sec_nume_docu = cab.sec_nume_docu
       AND tmp.num_proc = psnumeroproceso
       AND tmp.cod_tipo_docu = pscodtipodocu
       AND
      -- Producto Session Experte asignado a una consultora que SI es Session Experte
       EXISTS (SELECT detprodu.cod_venta
          FROM sse_detal_produ_perio detprodu
         WHERE detprodu.cod_pais = det.cod_pais
           AND detprodu.cod_prog = pscodigoprograma
           AND detprodu.cod_consu = det.cod_clie
           AND detprodu.cod_peri = det.cod_peri
           AND detprodu.cod_venta = det.cod_vent
           AND detprodu.ind_factu = '0' -- no facturado
        );

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_val_frecu_cont IS TABLE OF sse_produ_ciclo_consu.val_frecu_cont%TYPE;
    TYPE t_cod_prod IS TABLE OF sse_produ_ciclo_consu.cod_prod%TYPE;
    TYPE t_ind_limi_unid IS TABLE OF sse_progr_produ_ciclo.ind_limi_unid%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais           t_cod_pais;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_num_lote           t_num_lote;
    v_cod_vent           t_cod_vent;
    v_tip_posic          t_tip_posic;
    v_val_unid_dem       t_val_unid_dem;
    v_nuevo_val_unid_dem t_val_unid_dem; -- unidades restantes de SSE_PRODU_CONSU
    v_val_frecu_cont     t_val_frecu_cont;
    v_val_frecu_resta    t_val_frecu_cont;
    v_cod_prod           t_cod_prod;
    v_ind_limi_unid      t_ind_limi_unid;
    v_usu_modi           t_usu_modi;
    v_fec_modi           t_fec_modi;

    rows NATURAL := 1000; -- Number of rows to process at a time
    -- i    BINARY_INTEGER := 0;
    j           BINARY_INTEGER := 0;
    k           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_nuevo_val_unid_dem,
             v_val_frecu_cont,
             v_val_frecu_resta,
             v_cod_prod,
             v_ind_limi_unid,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      -- Salvar el valor realmente solicitado (originalmente registrado en INT_SOLIC_CONSO_DETAL) a la tabla SSE_DETAL_PRODU_PERIO
      FORALL k IN 1 .. v_cod_pais.count
        UPDATE sse_detal_produ_perio
           SET val_unida_pedid = v_val_unid_dem(k),
               usu_modi        = v_usu_modi(k),
               fec_modi        = v_fec_modi(k)
         WHERE cod_pais = v_cod_pais(k)
           AND cod_prog = pscodigoprograma
           AND cod_consu = v_cod_clie(k)
           AND cod_peri = v_cod_peri(k)
           AND cod_venta = v_cod_vent(k);

      -- Cambiar el valor de las unidades pedidas en INT_SOLIC_CONSO_DETAL con el valor restante de unidades
      FOR j IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        IF (v_ind_limi_unid(j) = '1') THEN
          IF ((v_val_unid_dem(j)) > (v_nuevo_val_unid_dem(j))) THEN
            UPDATE int_solic_conso_detal
               SET val_unid_dem = v_nuevo_val_unid_dem(j),
                   usu_modi     = v_usu_modi(j),
                   fec_modi     = v_fec_modi(j)
             WHERE cod_pais = v_cod_pais(j)
               AND cod_peri = v_cod_peri(j)
               AND cod_clie = v_cod_clie(j)
               AND num_lote = v_num_lote(j)
               AND cod_vent = v_cod_vent(j)
               AND tip_posic = v_tip_posic(j);
          END IF;
        END IF;
      END LOOP;

    END LOOP;

    CLOSE curupdconsodetal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_ACTUA_UNIDA_STO: ' || ls_sqlerrm);

  END sse_pr_actua_unida_sto;

  /**************************************************************************
  Descripcion       : SSE_PR_PROCESA_PEDID_CONSU_SSE
                      Procedimiento principal que llama a los procedimientos que
                      se deben ejecutar antes de la facturacion, ejecutado desde
                      la validacion de STO
  Fecha Creacion    : 15/04/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Isabel Vega P.
  ***************************************************************************/
  PROCEDURE sse_pr_procesa_pedid_consu_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  )

   IS
    v_cod_programa VARCHAR2(3);
  BEGIN

    -- Obtener el programa session experte actual en base al periodo actual
    v_cod_programa := sse_fn_devue_progr_sessi_actu(pscodigopais,
                                                    pscodigoperiodo);

    IF (v_cod_programa IS NOT NULL) THEN
      BEGIN
        -- Carga de consultoras session experte a partir de pedidos
        sse_pr_carga_consu_sse_sto(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_programa,
                                   psusuario,
                                   pscodtipodocu,
                                   psnumeroproceso);

        -- Carga de productos acumulados por consultora y programa
        sse_pr_carga_prod_acum_sto(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_programa,
                                   psusuario,
                                   pscodtipodocu,
                                   psnumeroproceso);

        -- Carga de productos solicitados por consultora session experte
        sse_pr_carga_prod_sto(pscodigopais,
                              pscodigoperiodo,
                              v_cod_programa,
                              psusuario,
                              pscodtipodocu,
                              psnumeroproceso);

        -- Actualiza Indicador Error para Pedidos con productos SSE consultoras NO SSE
        sse_pr_actua_ind_error_sto(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_programa,
                                   psusuario,
                                   pscodtipodocu,
                                   psnumeroproceso);

        -- Actualiza Unidades fuera de Limite de Unidades para pedidos productos SSE
        sse_pr_actua_unida_sto(pscodigopais,
                               pscodigoperiodo,
                               v_cod_programa,
                               psusuario,
                               pscodtipodocu,
                               psnumeroproceso);

      END;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR SSE_PR_PROCESA_PEDID_CONSU_STO: ' || ls_sqlerrm);
  END sse_pr_procesa_pedid_consu_sto;

END sse_pkg_proce_pedid;
/
