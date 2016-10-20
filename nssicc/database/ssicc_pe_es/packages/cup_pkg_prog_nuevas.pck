CREATE OR REPLACE PACKAGE "CUP_PKG_PROG_NUEVAS" IS

  /************************************************************************/
  /* Descripcion    :Obtiene la N siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_nsgte_campa
  (
    pscodperiodo VARCHAR2,
    numcampanhas NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : CUP_FN_NIVEL_MATR_EQUI, obtienes los niveles de un programa
  Fecha Creacion    : 30/03/2007
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION cup_fn_nivel_matr_equi
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_CONSU
                    Cambio de EST_REGI a '1' de consultoras de la tabla cup_consu_nueva
                    para indicar que ya se facturo su ultima campaña
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_consu
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_DETAL_CUPON
                    Actualiza el IND_FACTU a 1 a los productos no facturados
                              de la tabla Detalle Cupon
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_detal_cupon
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_CODVTA_BY_CUPON
                     Devuelve el Codigo de Venta de un Codigo Cupon para un Periodo
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoPeriodo : Codigo de periodo actual
      psCodigoCupon : Codigo de Cupon
      psCodigoNivel : Codigo de Nivel
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_codvta_by_cupon
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocupon    VARCHAR2,
    pscodigonivel    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NIVEL_NOCON
                     Devuelve el Nivel para las consultoras PRINT (Sin Constancia)
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPeriodoIni : Codigo de periodo Inicio
      psCodigoPeriodoFin : Codigo de periodo Fin
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_nivel_nocon
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NIVEL_NOCON
                     Devuelve el Nivel para las consultoras PRINT (Sin Constancia)
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPeriodoIni : Codigo de periodo Inicio
      psCodigoPeriodoFin : Codigo de periodo Fin
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_nivel_nocon_cierr
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_CUPON_UTILI
                    Actualiza los acumulados que se almacenan en cup_consu_cupon
                    a partir de la tabla Detalle Cupones
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_cupon_utili
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );
  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_CARGA_CONSU_NIVEL
                    Actualiza los acumulados que se almacenan en cup_consu_cupon
                    a partir de la tabla Detalle Cupones
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_carga_consu_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_CARGA_NOCON_NIVEL
                    Incializa los Niveles por Consultoras de la tabla CUP_CONSU_NIVEL
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_carga_nocon_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_SUSCR_NIVEL
                     Devuelve el valor permitido de unidades a depachar para un nivel
  Fecha Creacion     : 28/04/2008
  Parametros Entrada:
      psCodigoPais : Codigo Pais
      psCodigoPrograma : Codigo Programa
      psCodigoNivel: Codigo Nivel
      psCodigoCliente: Codigo Cliente
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_suscr_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigonivel    VARCHAR2,
    pscodigocliente  VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_PROGR_CUPON_ACTU
                     Devuelve Programa de Cupones Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodPeriodo : Codigo de periodo actual
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_progr_cupon_actu
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_NIVEL
                     Devuelve el valor permitido de unidades a depachar para un nivel
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel : Codigo Nivel
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_nivel
  (
    pscodnivel    VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : FN_DEV_NIVEL_UTILI_CONS
                     Devuelve la cantidad de cupones utilizados en un nivel por consultora
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel : Codigo Nivel
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_nivel_utili_cons
  (
    pscodpais  VARCHAR2,
    pscodprog  VARCHAR2,
    pscodnivel VARCHAR2,
    pscodclie  VARCHAR2
  ) RETURN NUMBER;


    /**************************************************************************
  Descripcion        : FN_DEV_NIVEL_CAMP_UTI_CONS
                     Devuelve la cantidad de cupones utilizados en un nivel y una campaña por consultora
  Fecha Creacion     : 07/05/2015
  Parametros Entrada:
  psCodNivel : Codigo Nivel
  pscodigoperiodo : Codigo Periodo
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_nivel_camp_uti_cons
  (
    pscodpais  VARCHAR2,
    pscodprog  VARCHAR2,
    pscodnivel VARCHAR2,
    pscodclie  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : CUP_PR_CARGA_CONSU_SUSCR
                     Carga los pedidos de la consultora de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_suscr
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion        :       CUP_PR_CIERR_ACTUA_SUSCR_UTILI
                     Actualiza los cupones de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_suscr_utili
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );
  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_UNID_SUSCR
                     Actualiza las unidades de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_actua_unida_suscr
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_INDIC_CUPON
                     Devuelve el indicador de cupon actual
  Fecha Creacion     : 18/05/2009
  Parametros Entrada:
      psCodPais : Codigo de pais
      psCodPrograma : Codigo Programa
  Autor              : Rosalvina Ramirez
  ***************************************************************************/
  FUNCTION cup_fn_devue_indic_cupon
  (
    pscodpais     VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_CONSU_FACTU
                      Actualiza las consultoras nuevas que pasaron pedido y facturaron
                      en el periodo en la tabla de facturacion de consultoras AL FINAL
                      DE CAMPAÑA
  Fecha Creacion     : 21/05/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_consu_factu_campa
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_ANFAC
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que no ha facturado
  Fecha Creacion    : 21/08/2009
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_anfac
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_CIERR_FUERA
                      Procedimiento para revertir los cambios en Programa
                      Nuevas al cierre de la campaña para los pedidos
                      anulados no gestionados
  Fecha Creacion    : 17/08/2009
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_cierr_fuera
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_DETAL_CUPON_SICC
                      Resetea el IND_FACTU a 0 a los productos tabla Detalle Cupon
  Fecha Creacion    : 15/10/2009
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_detal_cupon_sicc
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_PROCE_CIERR_CUPON_MULTI
                      Procedimiento principal que llama a los procedimientos que
                      se deben ejecutar para el cierre de facturacion que soporta la
            configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_proce_cierr_cupon_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_ANFAC_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que no ha facturado que soporta la
            configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_anfac_multi
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  );

  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_CONSU_FACTU_MULTI
                      Actualiza las consultoras nuevas que pasaron pedido y facturaron
                      en el periodo en la tabla de facturacion de consultoras AL FINAL
                      DE CAMPAÑA  que soporta la
            configuracion de multiples programas
  Fecha Creacion     : 05/02/2010
  Parametros Entrada:
           psCodigoPais:     Codigo Pais
           psCodigoPeriodo:  Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_consu_factu_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_CIERR_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas al cierre de la campaña para los pedidos
                      anulados no gestionados que soporta la
            configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_cierr_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que soporta la
                      configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_multi
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  );

  --------------------------------------------------------------------------
  --------------------------------------------------------------------------
  -- STORES QUE SOPORTAN LA CONFIGURACION DE MULTIPLES PROGRAMAS DESDE STO
  --------------------------------------------------------------------------
  --------------------------------------------------------------------------

  /**************************************************************************
  Descripcion       : CUP_PR_PROCE_DESPA_NUEVA_STO
                      Procedimiento principal que llama a los procedimientos que
                      se deben ejecutar antes de la facturacion que soporta la
                      configuracion de multiples programas desde la validacion
                      de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_proce_despa_nueva_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2,
    pscodigocliente VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_DESPA_CUPON_DEFAU_STO
                      Despacho Cupones por defecto, ejecutado desde la
                      validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_despa_cupon_defau_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    ncantidad        NUMBER,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_CANTI_STO
                      Validacion de Cupones LOVE que marca con error cuando
                      hay mas de un cupon love solicitado en el periodo por una consultora,
                      ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_canti_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_NONIV_STO
                      Validacion de Cupones LOVE que marca con error los cupones love
                      solicitado no corresponde al nivel de la consultora. ejecutado
                      desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_noniv_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psindconst       VARCHAR2,
    psindconstprem   VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_BORRA_CUPON_DEFEC_STO
                      Borra los cupones love adicionados por defecto, ejecutado
                      desde la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_borra_cupon_defec_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_UNIDA_DEMAN_STO
                    Validacion de Cupones solicitados que pidieron mas
                    de las unidades que se le permite a una consultora Print
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_actua_unida_deman_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_VAL_UNID_NIVEL_STO
                    Valida la cantidad de cupones a despachar dentro del nivel.
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_val_unid_nivel_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_IND_ERROR_CUSTO
                    Validacion de Cupones q son solicitados por Consultoras
                    que no pertenecen o q ya fueron usados
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_actua_ind_error_custo
  (
    pscodigopais        VARCHAR2,
    pscodigoperiodo     VARCHAR2,
    pscodigoprograma    VARCHAR2,
    psusuario           VARCHAR2,
    pscodtipodocu       VARCHAR2,
    psnumeroproceso     VARCHAR2,
    pscodigocliente     VARCHAR2,
    psindconst          VARCHAR2,
    psindconstpremeelec VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_DETAL_PERIO_STO
                    Inicializa cupones de una consultora por nivel y periodo
                    ejecutado por la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_detal_perio_sto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psind_cons_prem_elec  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_CUPON_STO
                    Inicializa cupones Acumulado por Consultora
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_cupon_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NIVEL_PRSTO
                    Incializa los Niveles por Consultoras para Prog CON Constancia de Primer Pedido
                    de la tabla CUP_CONSU_NIVEL, ejecutado desde la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nivel_prsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_NOCON_NIVEL_PRSTO
                    Incializa los Niveles por Consultoras para Prog SIN Constancia del primer pedido
                    de la tabla CUP_CONSU_NIVEL ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE cup_pr_carga_nocon_nivel_prsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_CONST_STO
                    Despacho de Premios Consultoras Nuevas por Nivel cuando el
                    indicador de constancia esta activado, ejecutado desde la
                    validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_const_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_ANTIG_CTSTO
                    Actualiza la informacion de una consultora constante(paso 2doped, 3er...,etc)
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_antig_ctsto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_NUEVA_STO
                    Despacho de Premios Consultoras Nuevas por Nivel
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_nueva_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_ANTIG_NCSTO
                    Actualiza la informacion de una consultora no constante(print)
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_antig_ncsto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  );

  /**************************************************************************
  Descripcion        :CUP_PR_CARGA_CONSU_FACTU_STO
                      Inserta inicialmente las consultoras nuevas que pasaron pedido
                      en el periodo en la tabla de facturacion de consultoras
                      ejecutado desde la validacion de STO
  Fecha Creacion     : 14/04/2010
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_factu_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_HOMOL_CUPON_DETAL_STO
                      Homologa los cupones en base a la matriz de nuevas
                      ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_homol_cupon_detal_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2,
    psoidalmacenel   VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NUEVA_STO
                    Registra o actualiza la informacion de una consultora nueva,
                    con el programa que le corresponda segun su UA ejecutado
                    desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nueva_sto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_RESET_CONSU_REACT_STO
                      Resetea la informacion de cupones de una consultora reactivada
                      ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_reset_consu_react_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_PRIME_PEDID
                     Actualiza el IND_PROD a '1' a las consultoras nuevas o reactivadas
                     de la tabla cup_consu_nueva para indicar que solicito el producto
                     parametrizado para el primer pedido
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_prime_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_PRODU_STO
                      Despacho de Premios Consultoras Nuevas por Nivel, cuando
                      el indicador que exige solicitar el kit en el primer
                      pedido este encendido
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_produ_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_CONPR_STO
                    Despacho de Premios Consultoras Nuevas por Nivel cuando el
                    indicador de constancia esta activado, ejecutado desde la
          validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_conpr_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_LOG_ERROR
                      Guarda Logs ante errores en una transaccion autonoma
  Fecha Creacion    : 18/03/2010
  Parametros Entrada:
      psCadena     : Cadena a insertar
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_log_error(pscadena IN VARCHAR2);

  /**************************************************************************
  Descripcion       : CUP_PR_BORRA_CUPON_DEFEC_CTSTO
                      Borra los cupones love adicionados por defecto, a las
                      consultoras que son constantes, ejecutado desde la
                      validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador si es a todo el pais o solo a un tipo de
                         clasificacion
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_borra_cupon_defec_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_NONIV_STO
                      Validacion de Cupones LOVE que marca con error los cupones love
                      solicitado no corresponde al nivel de las consultoras que son
                      constantes. ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_noniv_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_CANTI_CTSTO
                      Validacion de Cupones LOVE que marca con error cuando
                      hay mas de un cupon love solicitado en el periodo por una consultora,
                      que sea constante. ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador Todos
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_canti_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : LOV_PR_DESPA_CUPON_DEFAU_CTSTO
                      Despacho Cupones por defecto a las consultoras que son
                      constantes, ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador todos
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_despa_cupon_defau_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    ncantidad        NUMBER,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  );

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_NIVEL_LOVE
                        Devuelve el valor permitido de unidades LOVE a depachar
                        para un nivel
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel    : Codigo Nivel
      psCodPeriodo  : Codigo Periodo
      psCodPrograma : Codigo programa
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_nivel_love
  (
    pscodnivel    VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NUEVA_OBLIG
                    Registra o actualiza la informacion de una consultora nueva,
                    con el programa que le corresponda segun su UA, para el caso en
                    que es obligatorio solicitar un premio electivo en 1er pedido.
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 27/12/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nueva_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_INSER_MENSA_NUEVA
                      Inserta mensajes del programa de nuevas para el paquete
                      documentario
  Fecha Creacion    : 07/02/2011
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_inser_mensa_nueva
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  );

  /**************************************************************************
  Descripcion       : CUP_PR_GENER_INGRE_METAS
                      Genera data en la tabla temporal para luego ser leida
                      por el reporte de ingresos de metas
  Fecha Creacion    : 10/10/2011
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoRegion   : Codigo de region
      psCodigoZona     : Codigo de Zona
      psCodigoPeriodoInicial : Codigo de Periodo Inicial
      psCodigoPeriodoFinal : Codigo de Periodo Final
      psOrigenRegistro : Origen del Registro
      psEstado : Estado

  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE cup_pr_gener_ingre_metas
  (
    pscodigopais           VARCHAR2,
    pscodigoregion         VARCHAR2,
    pscodigozona           VARCHAR2,
    pscodigoperiodoinicial VARCHAR2,
    pscodigoperiodofinal   VARCHAR2,
    psorigenregistro       VARCHAR2,
    psestado               VARCHAR2,
    psusuario              VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Devuelve cero si no existe traslape de campañas, caso
                      contrario devuelve el codigo de programa que esta con
                      traslape
  Fecha Creacion    : 25/10/2011
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  FUNCTION cup_fn_trasl_perio
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Inserta un registro si es Regalo x Pedido
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 14/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_inser_regal_pedid
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Realiza un despacho de Regalo x Pedido
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 15/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_despa_regal_pedid
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Inserta una Solicitud RxP
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 16/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_inser_solic_regal_pedid
  (
    pscodigopais        VARCHAR2,
    pnoidpais           NUMBER,
    pscodigoperiodo     VARCHAR2,
    pnoidperiodoproceso NUMBER,
    pnoidtiposol        NUMBER,
    pnoidcliente        NUMBER,
    pscodigocliente     VARCHAR2,
    pscodigoprograma    VARCHAR2,
    pscodigousuario     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Verifica si existe cruce de Programa
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 01/06/2012
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_verif_cruce_proga
  (
    pscodigopais       VARCHAR2,
    pscodigoprograma   VARCHAR2,
    pscodigoperiodoact VARCHAR2,
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2,
    pscodigoregion     VARCHAR2,
    pscodigozona       VARCHAR2,
    psresultado        OUT VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve el Nivel, saliendo de la resta de PeriodoFin - PeriodoInicio
                       cambio RCR PER-SiCC-2012-0362
  Fecha Creacion     : 04/06/2012
  Parametros Entrada:
      pscodigoperiodoini : Periodo Inicio
      pscodigoperiodofin : Periodo Fin
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION cup_fn_devue_nivel_noco2
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Valida la descripcion de los campos de las
                      consultoras al programa de nuevas mediante un Excel
  Fecha Creacion    : 10/12/2012
  Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE cup_pr_nvs_valid_consu
  (
    pscodpais       VARCHAR2,
    pscodcliente    VARCHAR2,
    pscodperiodo    VARCHAR2,
    pscodusuario    VARCHAR2,
    psmensajesalida OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Carga a la tabla temporal para la validacion
                         de las zonas por programas
  Fecha Creacion    : 11/12/2012
  Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE cup_pr_nvs_obten_zonas_asoci
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodusuario VARCHAR2
  );

  --PER-SiCC-2013-0141 - INI
  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NUMRO_PEDID_ORIG
                     Devuelve numero de pedidos por origen
  Fecha Creacion     : 07/05/2013
  Parametros Entrada:
      pscodpais : Codigo Nivel
      pscodperi : Codigo Nivel
      pscodclie : Codigo Nivel
  Autor              : CSVD - FFVV
  ***************************************************************************/
  FUNCTION cup_fn_devue_numro_pedid_orig
  (
    pscodpais VARCHAR2,
    pscodperi VARCHAR2,
    pscodclie VARCHAR2
  ) RETURN NUMBER;
  --PER-SiCC-2013-0141 - FIN

  /**************************************************************************
   Descripcion        :
                      Relaliza las validaciones antes rel registo de metas
   Fecha Creacion     : 13/05/2013
   Parametros Entrada:
       pscodigopais : Codigo del pais
       pscodigoconsultora : Codigo de la consultora que se esta registrando

   Parametros de Salida: Retorna un codigo de error indicando el error obtenido,
                         CERO en otro caso
   Autor              : Ivan Tocto
  **************************************************************************/
  PROCEDURE cup_pr_valida_regis_metas
  (
    pscodigopais       VARCHAR2,
    pscodigoconsultora VARCHAR2,
    pscodigoerror      OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera el Reporte Nuevas Unidades Atendidas en CSV
  Fecha Creacion    : 13/11/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  PROCEDURE cup_pr_gener_nueva_unida_atend
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    psnombrearchivo VARCHAR2,
    pstitulo        VARCHAR2,
    psdirectorio    OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Data para el Reporte de Nuevas Cupones
  Fecha Creacion    : 03/09/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE cup_pr_gener_repor_nueva_cupon
  (
    pscodigopais         VARCHAR2,
    pscodigoperiododesde VARCHAR2,
    pscodigoperiodohasta VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera el Reporte Nuevas Cupones en CSV.
  Fecha Creacion    : 03/09/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE cup_pr_repor_nueva_cupon_csv
  (
    pscodigopais    VARCHAR2,
    psnombrearchivo VARCHAR2,
    pstitulo        VARCHAR2,
    psdirectorio    OUT VARCHAR2
  );
  
  /**************************************************************************
  Descripcion       : Eliminar los despachos de las consultoras no constantes
  Fecha Creacion    : 20/10/2015
  Parametros Entrada:
    psCodigoPeriodo     :  Codigo de Periodo
    psFechaFacturacion  :  Fecha Facturacion

  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE CUP_PR_ELIMI_DESPA_NOCON_CRUCE
    (psCodigoPeriodo            VARCHAR2,
     psFechaFacturacion         VARCHAR2);
  
END cup_pkg_prog_nuevas;
/
CREATE OR REPLACE PACKAGE BODY "CUP_PKG_PROG_NUEVAS" IS

  motivo_insercion_default    CONSTANT VARCHAR2(2) := '01';
  motivo_error_no_nivel_cupon CONSTANT VARCHAR2(2) := '02';
  motivo_error_cantidad_cupon CONSTANT VARCHAR2(2) := '03';

  tipo_posicion     CONSTANT NUMBER := 2031;
  sub_tipo_posicion CONSTANT NUMBER := 2033;

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /************************************************************************/
  /* Descripcion    :Obtiene la N siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/
  FUNCTION gen_fn_dev_nsgte_campa
  (
    pscodperiodo VARCHAR2,
    numcampanhas NUMBER
  ) RETURN VARCHAR2 IS

  BEGIN

    RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL,
                                            pscodperiodo,
                                            numcampanhas);

  END gen_fn_dev_nsgte_campa;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_CONSU
                    Cambio de EST_REGI a '1' de consultoras de la tabla cup_consu_nueva
                    para indicar que ya se facturo su ultima campaña
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_consu
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS

    -- Consultoras que SI estan en cup_consu_nueva y que tienen pedidos facturados
    CURSOR curupdconsul IS
      SELECT consul.cod_pais AS cod_pais,
             consul.cod_prog AS cod_programa,
             consul.cod_cons AS cod_consu,
             '1' AS est_regi, -- 1: se marca la consultora para indicar que ya se facturaron sus pedidos con productos session experte
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM cup_consu_nueva consul
       WHERE consul.cod_pais = pscodigopais
         AND consul.cod_prog = pscodigoprograma
         AND consul.camp_fin_ccc = pscodigoperiodo
         AND -- consultora con ultima campaña igual al periodo actual
             consul.est_proc = '0'
         AND -- consultora est_reg = 0', es decir que no ha facturado.. su ultima campaña
             EXISTS (SELECT cab.cod_clie
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_pais = consul.cod_pais
                 AND cab.cod_peri = consul.camp_fin_ccc
                 AND cab.cod_clie = consul.cod_cons
                 AND
                    -- flags de facturado
                     cab.ind_ocs_proc = '1'
                 AND cab.ind_proc_gp2 = '1');

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_est_reg IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_consu_nueva.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_consu_nueva.fec_modi%TYPE;

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

    OPEN curupdconsul;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsul BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_est_reg,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsul%ROWCOUNT;
      v_row_count := curupdconsul%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva
           SET est_proc = v_est_reg(j),
               usu_modi = v_usu_modi(j),
               fec_modi = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsul;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (ls_sqlerrm);
      RETURN;

  END cup_pr_cierr_actua_consu;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_DETAL_CUPON
                    Actualiza el IND_FACTU a 1 a los productos no facturados
                              de la tabla Detalle Cupon
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_detal_cupon
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS

    CURSOR curupddetalcupon IS

      SELECT soliconsodet.cod_pais AS cod_pais,
             pscodigoprograma AS cod_programa,
             soliconsodet.cod_clie AS cod_consu,
             soliconsodet.cod_peri AS cod_periodo,
             soliconsodet.cod_vent AS cod_venta,
             soliconsodet.val_unid_dem AS val_unida_real,
             '1' AS ind_factu,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal soliconsodet
       WHERE soliconsodet.cod_pais = pscodigopais
         AND soliconsodet.cod_peri = pscodigoperiodo
         AND soliconsodet.ind_erro_sse = '0'
         AND -- deberia ser error de Cupones
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
            AND cab.ind_proc_gp2 = '1')
         AND EXISTS (SELECT NULL
                FROM cup_detal_cupon_perio detal
               WHERE detal.cod_pais = soliconsodet.cod_pais
                 AND detal.cod_prog = pscodigoprograma
                 AND detal.cod_cons = soliconsodet.cod_clie
                 AND detal.cod_peri = soliconsodet.cod_peri
                 AND detal.cod_venta = soliconsodet.cod_vent
                 AND detal.ind_factu = '0' -- productos no facturados
              );

    TYPE t_cod_pais IS TABLE OF cup_detal_cupon_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_detal_cupon_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_detal_cupon_perio.cod_cons%TYPE;
    TYPE t_cod_peri IS TABLE OF cup_detal_cupon_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF cup_detal_cupon_perio.cod_venta%TYPE;
    TYPE t_val_unida_pedid IS TABLE OF cup_detal_cupon_perio.val_unida_pedid%TYPE;
    TYPE t_ind_factu IS TABLE OF cup_detal_cupon_perio.ind_factu%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_detal_cupon_perio.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_detal_cupon_perio.fec_modi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_peri        t_cod_peri;
    v_cod_venta       t_cod_venta;
    v_val_unida_pedid t_val_unida_pedid;
    v_ind_factu       t_ind_factu;
    v_usu_modi        t_usu_modi;
    v_fec_modi        t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupddetalcupon;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupddetalcupon BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_peri,
             v_cod_venta,
             v_val_unida_pedid,
             v_ind_factu,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupddetalcupon%ROWCOUNT;
      v_row_count := curupddetalcupon%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_detal_cupon_perio
           SET val_unida_pedid = v_val_unida_pedid(j),
               ind_factu       = v_ind_factu(j),
               usu_modi        = v_usu_modi(j),
               fec_modi        = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_venta = v_cod_venta(j);

    END LOOP;
    CLOSE curupddetalcupon;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (ls_sqlerrm);
      RETURN;

  END cup_pr_cierr_actua_detal_cupon;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_CUPON_UTILI
                    Actualiza los acumulados que se almacenan en cup_consu_cupon
                    a partir de la tabla Detalle Cupones
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_cupon_utili
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS

    CURSOR curupdcuponconsu IS

      SELECT cupon.cod_pais  AS cod_pais,
             cupon.cod_prog  AS cod_programa,
             cupon.cod_nivel AS nivel,
             cupon.cod_cupon AS cod_cupon,
             cupon.cod_cons  AS cod_consu,
             -- se utiliza para actualizar acumulados
             '1' AS ind_utili,
             -- se setea la campaña de utilizacion  del cupon
             pscodigoperiodo AS cam_utili,
             psusuario       AS usu_modi,
             SYSDATE         AS fec_modi
        FROM cup_consu_cupon cupon
       WHERE cupon.cod_pais = pscodigopais
         AND cupon.cod_prog = pscodigoprograma
         AND cupon.ind_utili = '0'
         AND
            -- Cupones facturados en la campaña de proceso
             EXISTS (SELECT NULL
                FROM cup_detal_cupon_perio detal,
                     cup_equiv_matr        equiv
               WHERE detal.cod_pais = cupon.cod_pais
                 AND detal.cod_prog = cupon.cod_prog
                 AND detal.cod_cons = cupon.cod_cons
                 AND detal.cod_peri = pscodigoperiodo
                 AND
                    -----------------------------------------
                     detal.cod_venta = equiv.cod_venta
                 AND equiv.cod_pais = detal.cod_pais
                 AND equiv.cod_prog = detal.cod_prog
                 AND equiv.cod_peri = detal.cod_peri
                 AND equiv.cod_cupon = cupon.cod_cupon
                 AND equiv.cod_nivel = cupon.cod_nivel
                 AND
                    -----------------------------------------
                     detal.ind_factu = '1');

    TYPE t_cod_pais IS TABLE OF cup_consu_cupon.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_cupon.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_cupon.cod_nivel%TYPE;
    TYPE t_cod_cupon IS TABLE OF cup_consu_cupon.cod_cupon%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_cupon.cod_cons%TYPE;
    TYPE t_ind_utili IS TABLE OF cup_consu_cupon.ind_utili%TYPE;
    TYPE t_cam_utili IS TABLE OF cup_consu_cupon.cam_utili%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_consu_cupon.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_consu_cupon.fec_modi%TYPE;

    v_cod_pais  t_cod_pais;
    v_cod_prog  t_cod_prog;
    v_cod_nivel t_cod_nivel;
    v_cod_consu t_cod_consu;
    v_cod_cupon t_cod_cupon;
    v_ind_utili t_ind_utili;
    v_cam_utili t_cam_utili;
    v_usu_modi  t_usu_modi;
    v_fec_modi  t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdcuponconsu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdcuponconsu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_cupon,
             v_cod_consu,
             v_ind_utili,
             v_cam_utili,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdcuponconsu%ROWCOUNT;
      v_row_count := curupdcuponconsu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Actualizar el indicador y campanha de utilizacion de los cupones
        UPDATE cup_consu_cupon
           SET ind_utili = v_ind_utili(j),
               cam_utili = v_cam_utili(j),
               usu_modi  = v_usu_modi(j),
               fec_modi  = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_nivel = v_cod_nivel(j)
           AND cod_cupon = v_cod_cupon(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdcuponconsu;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (ls_sqlerrm);
      RETURN;

  END cup_pr_cierr_actua_cupon_utili;
  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_CARGA_CONSU_NIVEL
                    Incializa los Niveles por Consultoras para Prog CON Constancia
                    de la tabla CUP_CONSU_NIVEL
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_carga_consu_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS

    CURSOR curinsconsunivel IS

      SELECT consu.cod_pais      AS cod_pais,
             consu.cod_prog      AS cod_programa,
             consu.cod_ult_nivel AS nivel,
             consu.cod_cons      AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 1) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND consu.cod_ult_nivel =
                                            pn.cod_nive(+))) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva consu
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND consu.est_proc = '1'
         AND -- las consultoras q facturaron sus pedidos
            -- Los q aun no se registran en CUP_CONSU_NIVEL
             NOT EXISTS
       (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = consu.cod_ult_nivel
                 AND nivel.cod_cons = consu.cod_cons);
    ----------------------------------------------------
    CURSOR curupdconsunivel IS

      SELECT consu.cod_pais      AS cod_pais,
             consu.cod_prog      AS cod_programa,
             consu.cod_ult_nivel AS nivel,
             consu.cod_cons      AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 1) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND consu.cod_ult_nivel =
                                            pn.cod_nive(+))) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva consu
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND consu.est_proc = '1'
         AND -- las consultoras q facturaron sus pedidos
            -- Los q ya se registraron en CUP_CONSU_NIVEL
             EXISTS (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = consu.cod_ult_nivel
                 AND nivel.cod_cons = consu.cod_cons);
    ----------------------------------------------------

    TYPE t_cod_pais IS TABLE OF cup_consu_nivel.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nivel.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_nivel.cod_nivel%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nivel.cod_cons%TYPE;
    TYPE t_cam_inic_vig IS TABLE OF cup_consu_nivel.cam_inic_vig%TYPE;
    TYPE t_cam_fin_vig IS TABLE OF cup_consu_nivel.cam_fin_vig%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nivel.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nivel.fec_digi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_prog     t_cod_prog;
    v_cod_nivel    t_cod_nivel;
    v_cod_consu    t_cod_consu;
    v_cam_inic_vig t_cam_inic_vig;
    v_cam_fin_vig  t_cam_fin_vig;
    v_usu_digi     t_usu_digi;
    v_fec_digi     t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    k           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curinsconsunivel%ROWCOUNT;
      v_row_count := curinsconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        INSERT INTO cup_consu_nivel
          (cod_pais,
           cod_prog,
           cod_nivel,
           cam_inic_vig,
           cam_fin_vig,
           usu_digi,
           fec_digi,
           cod_cons)
        VALUES
          (v_cod_pais(j),
           v_cod_prog(j),
           v_cod_nivel(j),
           v_cam_inic_vig(j),
           v_cam_fin_vig(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_cod_consu(j));

    END LOOP;
    CLOSE curinsconsunivel;

    ----------------------------------------------------
    OPEN curupdconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsunivel%ROWCOUNT;
      v_row_count := curupdconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL k IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        UPDATE cup_consu_nivel
           SET cam_inic_vig = v_cam_inic_vig(k),
               cam_fin_vig  = v_cam_fin_vig(k),
               usu_modi     = v_usu_digi(k),
               fec_modi     = SYSDATE
         WHERE cod_pais = v_cod_pais(k)
           AND cod_prog = v_cod_prog(k)
           AND cod_cons = v_cod_consu(k)
           AND cod_nivel = v_cod_nivel(k);

    END LOOP;
    CLOSE curupdconsunivel;
    ----------------------------------------------------

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (ls_sqlerrm);
      RETURN;

  END cup_pr_cierr_carga_consu_nivel;

  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_CARGA_NOCON_NIVEL
                    Incializa los Niveles por Consultoras para Prog SIN Constancia
                    de la tabla CUP_CONSU_NIVEL
  Fecha Creacion    : 27/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_carga_nocon_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS

    CURSOR curinsconsunivel IS

      SELECT consu.cod_pais AS cod_pais,
             consu.cod_prog AS cod_programa,
             niv.cod_nivel  AS nivel, -- todos los niveles en tabla equiv por periodo actual
             consu.cod_cons AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 0 + niv.cod_nivel) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND niv.cod_nivel = pn.cod_nive(+)) +
                                    niv.cod_nivel - 1) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva consu,
             cup_nivel       niv
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.cod_ult_nivel = '01'
         AND --
             consu.camp_fin_ccc = pscodigoperiodo
         AND consu.est_proc = '1'
         AND -- las consultoras q facturaron sus pedidos
             EXISTS (SELECT NULL
                FROM cup_equiv_matr ma
               WHERE ma.cod_pais = consu.cod_pais
                 AND ma.cod_prog = consu.cod_prog
                 AND ma.cod_peri = consu.camp_fin_ccc
                 AND ma.cod_nivel = niv.cod_nivel)
         AND
            -- Los q aun no se registran en CUP_CONSU_NIVEL
             NOT EXISTS
       (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = niv.cod_nivel
                 AND nivel.cod_cons = consu.cod_cons);
    ---------------------------------------------
    CURSOR curupdconsunivel IS

      SELECT consu.cod_pais AS cod_pais,
             consu.cod_prog AS cod_programa,
             niv.cod_nivel  AS nivel, -- todos los niveles en tabla equiv por periodo actual
             consu.cod_cons AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 0 + niv.cod_nivel) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND niv.cod_nivel = pn.cod_nive(+)) +
                                    niv.cod_nivel - 1) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva consu,
             cup_nivel       niv
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.cod_ult_nivel = '01'
         AND --
             consu.camp_fin_ccc = pscodigoperiodo
         AND consu.est_proc = '1'
         AND -- las consultoras q facturaron sus pedidos
             EXISTS (SELECT NULL
                FROM cup_equiv_matr ma
               WHERE ma.cod_pais = consu.cod_pais
                 AND ma.cod_prog = consu.cod_prog
                 AND ma.cod_peri = consu.camp_fin_ccc
                 AND ma.cod_nivel = niv.cod_nivel)
         AND
            -- Los q ya se registran en CUP_CONSU_NIVEL
             EXISTS (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = niv.cod_nivel
                 AND nivel.cod_cons = consu.cod_cons);
    ---------------------------------------------

    TYPE t_cod_pais IS TABLE OF cup_consu_nivel.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nivel.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_nivel.cod_nivel%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nivel.cod_cons%TYPE;
    TYPE t_cam_inic_vig IS TABLE OF cup_consu_nivel.cam_inic_vig%TYPE;
    TYPE t_cam_fin_vig IS TABLE OF cup_consu_nivel.cam_fin_vig%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nivel.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nivel.fec_digi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_prog     t_cod_prog;
    v_cod_nivel    t_cod_nivel;
    v_cod_consu    t_cod_consu;
    v_cam_inic_vig t_cam_inic_vig;
    v_cam_fin_vig  t_cam_fin_vig;
    v_usu_digi     t_usu_digi;
    v_fec_digi     t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    k           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curinsconsunivel%ROWCOUNT;
      v_row_count := curinsconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        INSERT INTO cup_consu_nivel
          (cod_pais,
           cod_prog,
           cod_nivel,
           cam_inic_vig,
           cam_fin_vig,
           usu_digi,
           fec_digi,
           cod_cons)
        VALUES
          (v_cod_pais(j),
           v_cod_prog(j),
           v_cod_nivel(j),
           v_cam_inic_vig(j),
           v_cam_fin_vig(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_cod_consu(j));

    END LOOP;
    CLOSE curinsconsunivel;

    ----------------------------------------------------
    OPEN curupdconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsunivel%ROWCOUNT;
      v_row_count := curupdconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL k IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        UPDATE cup_consu_nivel
           SET cam_inic_vig = v_cam_inic_vig(k),
               cam_fin_vig  = v_cam_fin_vig(k),
               usu_modi     = v_usu_digi(k),
               fec_modi     = SYSDATE
         WHERE cod_pais = v_cod_pais(k)
           AND cod_prog = v_cod_prog(k)
           AND cod_cons = v_cod_consu(k)
           AND cod_nivel = v_cod_nivel(k);

    END LOOP;
    CLOSE curupdconsunivel;
    ----------------------------------------------------

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (ls_sqlerrm);
      RETURN;

  END cup_pr_cierr_carga_nocon_nivel;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NIVEL_NOCON
                     Devuelve el Nivel para las consultoras PRINT (Sin Constancia)
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPeriodoIni : Codigo de periodo Inicio
      psCodigoPeriodoFin : Codigo de periodo Fin
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_nivel_nocon
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_nivel        VARCHAR2(6);
    i                  BINARY_INTEGER := 0;
    lscodigoperiodoini VARCHAR2(6);
    lsNumCampPais varchar2(2);
    lsCampa varchar2(2);
    lsCodPais   varchar2(3);

  BEGIN
    v_cod_nivel        := '00';
    lscodigoperiodoini := pscodigoperiodoini;
    IF (pscodigoperiodoini > pscodigoperiodofin OR
       TRIM(pscodigoperiodoini) IS NULL OR
       TRIM(pscodigoperiodofin) IS NULL) THEN
      RETURN v_cod_nivel;
    END IF;
  
    WHILE TRUE
    LOOP
      lscodigoperiodoini := gen_fn_dev_nsgte_campa(pscodigoperiodoini, i);
      IF (pscodigoperiodofin = lscodigoperiodoini) THEN
        SELECT TRIM(to_char(i + 1, '09')) INTO v_cod_nivel FROM dual;
        EXIT;
      END IF;
      i := i + 1;
    END LOOP;

    RETURN v_cod_nivel;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_FN_DEVUE_NIVEL_NOCON: ' ||
                              ls_sqlerrm);

  END cup_fn_devue_nivel_nocon;
  /*************************************
  Descripcion        : CUP_FN_DEVUE_NIVEL_NOCON_CIERR
                     Devuelve el Nivel para las consultoras PRINT (Sin Constancia)
  
  Autor              : Doris Martinich
  *****************************************/
  FUNCTION cup_fn_devue_nivel_nocon_cierr
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_nivel        VARCHAR2(6);
    i                  BINARY_INTEGER := 0;
    lscodigoperiodoini VARCHAR2(6);
    ps2codigoperiodoini VARCHAR2(6);
    ps2codigoperiodofin VARCHAR2(6);
    lsNumCampPais varchar2(2);
    lsCampa varchar2(2);
    lsCodPais   varchar2(3);

  BEGIN
    v_cod_nivel        := '00';
    lscodigoperiodoini := pscodigoperiodoini;
    IF (pscodigoperiodoini > pscodigoperiodofin OR
       TRIM(pscodigoperiodoini) IS NULL OR
       TRIM(pscodigoperiodofin) IS NULL) THEN
      RETURN v_cod_nivel;
    END IF;
    
    ps2codigoperiodoini := pscodigoperiodoini;
    ps2codigoperiodofin := pscodigoperiodofin;
    
    SELECT val_para   
    INTO lsCodPais
      FROM bas_param_pais
     WHERE nom_para = 'codigoPaisDefault'
       AND cod_sist = 'GEN';  
       
    SELECT val_para   
    INTO lsNumCampPais
      FROM bas_param_pais
     WHERE cod_para = '000'
       AND cod_pais = lsCodPais
       AND cod_sist = 'GEN';
       
    lsCampa := substr(ps2codigoperiodoini,5,2);
    IF  lsCampa  > lsNumCampPais THEN
        ps2codigoperiodoini := substr(ps2codigoperiodoini,1,4) || lsNumCampPais;
    END IF;
    lsCampa := substr(ps2codigoperiodofin,5,2);
    IF  lsCampa  > lsNumCampPais THEN
        ps2codigoperiodofin := substr(ps2codigoperiodofin,1,4) || lsNumCampPais;
    END IF;

    WHILE TRUE
    LOOP
      lscodigoperiodoini := gen_fn_dev_nsgte_campa(ps2codigoperiodoini, i);
      IF (ps2codigoperiodofin = lscodigoperiodoini) THEN
        SELECT TRIM(to_char(i + 1, '09')) INTO v_cod_nivel FROM dual;
        EXIT;
      END IF;
      i := i + 1;
    END LOOP;

    RETURN v_cod_nivel;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_FN_DEVUE_NIVEL_NOCON_CIERR: ' ||
                              ls_sqlerrm);

  END cup_fn_devue_nivel_nocon_cierr;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_CODVTA_BY_CUPON
                     Devuelve el Codigo de Venta de un Codigo Cupon para un Periodo
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPrograma : Codigo de programa
      psCodigoPeriodo : Codigo de periodo actual
      psCodigoCupon : Codigo de Cupon
      psCodigoNivel : Codigo de Nivel
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_codvta_by_cupon
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocupon    VARCHAR2,
    pscodigonivel    VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_venta VARCHAR2(6);

    CURSOR cequivmatr IS
      SELECT equiv.cod_venta
        FROM cup_equiv_matr equiv
       WHERE equiv.cod_pais = pscodigopais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_cupon = pscodigocupon
         AND equiv.cod_nivel = pscodigonivel
       ORDER BY equiv.cod_venta DESC; -- mayor codigo de venta
  BEGIN

    BEGIN

      SELECT equiv.cod_venta
        INTO v_cod_venta
        FROM cup_equiv_matr equiv
       WHERE equiv.cod_pais = pscodigopais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_cupon = pscodigocupon
         AND equiv.cod_nivel = pscodigonivel;

      RETURN v_cod_venta;

    EXCEPTION
      WHEN too_many_rows THEN
        FOR c1 IN cequivmatr
        LOOP
          v_cod_venta := c1.cod_venta;
          EXIT;
        END LOOP;
        RETURN v_cod_venta;

      WHEN no_data_found THEN
        v_cod_venta := '-1';
        RETURN v_cod_venta;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_FN_DEVUE_CODVTA_BY_CUPON: ' ||
                              ls_sqlerrm);

  END cup_fn_devue_codvta_by_cupon;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_PROGR_CUPON_ACTU
                     Devuelve Programa de Cupones Actual
  Fecha Creacion     : 25/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodPeriodo : Codigo de periodo actual
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION cup_fn_devue_progr_cupon_actu
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_programa VARCHAR2(6);

    CURSOR cprograma IS
      SELECT prog.cod_prog
        FROM cup_prog_nueva_cupon prog
       WHERE prog.cod_pais = pscodpais
         AND pscodperiodo BETWEEN prog.cam_inic AND prog.cam_fin -- el periodo actual debe estar en el rango de campañas permitidas para el programa
         AND prog.est_prog = 'S' -- activo
       ORDER BY prog.cod_prog DESC; -- mayor programa
  BEGIN

    BEGIN

      SELECT prog.cod_prog
        INTO v_cod_programa
        FROM cup_prog_nueva_cupon prog
       WHERE prog.cod_pais = pscodpais
         AND pscodperiodo BETWEEN prog.cam_inic AND prog.cam_fin
         AND prog.est_prog = 'S' -- activo
      ;

      RETURN v_cod_programa;

    EXCEPTION
      WHEN too_many_rows THEN
        FOR c1 IN cprograma
        LOOP
          v_cod_programa := c1.cod_prog;
          EXIT;
        END LOOP;
        RETURN v_cod_programa;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN NULL;

  END cup_fn_devue_progr_cupon_actu;

  /**************************************************************************
  Descripcion       : CUP_FN_NIVEL_MATR_EQUI, obtienes los niveles de un programa
  Fecha Creacion    : 30/03/2007
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION cup_fn_nivel_matr_equi
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2
  ) RETURN VARCHAR2 IS
    CURSOR niveles IS
      SELECT DISTINCT cup_equiv_matr.cod_nivel
        FROM cup_equiv_matr
       WHERE cup_equiv_matr.cod_pais = pscodigopais
         AND cup_equiv_matr.cod_prog = pscodigoprograma
         AND cup_equiv_matr.cod_peri = pscodigoperiodo
       ORDER BY cup_equiv_matr.cod_nivel;

    l_str     VARCHAR2(4000);
    r_niveles niveles%ROWTYPE;
  BEGIN

    l_str := '';

    OPEN niveles;
    LOOP
      FETCH niveles
        INTO r_niveles;
      EXIT WHEN niveles%NOTFOUND;
      BEGIN
        l_str := l_str || ' ' || r_niveles.cod_nivel;
      END;
    END LOOP;
    CLOSE niveles;
    RETURN l_str;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN l_str;
  END cup_fn_nivel_matr_equi;

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_NIVEL
                     Devuelve el valor permitido de unidades a depachar para un nivel
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel : Codigo Nivel
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_nivel
  (
    pscodnivel    VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN NUMBER IS
    val_nivel NUMBER;

  BEGIN
    SELECT c.val_unid
      INTO val_nivel
      FROM cup_perio_nivel c
     WHERE c.cod_nive = pscodnivel
       AND c.cod_peri = pscodperiodo
       AND c.cod_prog = pscodprograma;
    RETURN val_nivel;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;

  END cup_fn_dev_unidad_nivel;

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_SUSCR_NIVEL
                     Devuelve el valor permitido de unidades a depachar para un nivel
  Fecha Creacion     : 28/04/2008
  Parametros Entrada:
      psCodigoPais : Codigo Pais
      psCodigoPrograma : Codigo Programa
      psCodigoNivel: Codigo Nivel
      psCodigoCliente: Codigo Cliente
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_suscr_nivel
  (
    pscodigopais     VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodigonivel    VARCHAR2,
    pscodigocliente  VARCHAR2
  ) RETURN NUMBER IS
    val_unid NUMBER;

  BEGIN
    SELECT nvl(SUM((SELECT val_unida_pedid
                     FROM cup_detal_cupon_perio d
                    WHERE d.cod_pais = c.cod_pais
                      AND d.cod_prog = c.cod_prog
                      AND d.cod_cons = c.cod_cons
                      AND d.cod_peri = c.cam_utili
                      AND d.cod_venta =
                          (SELECT e.cod_venta
                             FROM cup_equiv_matr e
                            WHERE e.cod_pais = d.cod_pais
                              AND e.cod_prog = d.cod_prog
                              AND e.cod_peri = d.cod_peri
                              AND e.cod_nivel = c.cod_nivel
                              AND e.cod_cupon = c.cod_cupon))),
               0)
      INTO val_unid
      FROM cup_consu_cupon c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_prog = pscodigoprograma
       AND c.cod_cons = pscodigocliente
       AND c.cod_nivel = pscodigonivel
       AND ind_utili = '1';
    RETURN val_unid;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;

  END cup_fn_dev_unidad_suscr_nivel;

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_NIVEL_UTILI_CONS
                     Devuelve la cantidad de cupones utilizados en un nivel por consultora
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel : Codigo Nivel
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_nivel_utili_cons
  (
    pscodpais  VARCHAR2,
    pscodprog  VARCHAR2,
    pscodnivel VARCHAR2,
    pscodclie  VARCHAR2
  ) RETURN NUMBER IS
    val_cant NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO val_cant
      FROM cup_consu_cupon con
     WHERE con.cod_pais = pscodpais
       AND con.cod_prog = pscodprog
       AND con.cod_nivel = pscodnivel
       AND con.cod_cons = pscodclie
       AND con.ind_utili = '1'
          ---------------------------------------------
          -- para que devuelva solo los cupones NO Love
       AND NOT EXISTS (SELECT NULL
              FROM lov_equiv_matr em
             WHERE em.cod_prog = con.cod_prog
               AND em.cod_pais = con.cod_pais
               AND em.cod_cupon = con.cod_cupon
               AND em.cod_peri = con.cam_utili);
    RETURN val_cant;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
  END cup_fn_dev_nivel_utili_cons;

  /**************************************************************************
  Descripcion        : FN_DEV_NIVEL_CAMP_UTI_CONS
                     Devuelve la cantidad de cupones utilizados en un nivel por consultora
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
  psCodNivel : Codigo Nivel
  pscodigoperiodo  VARCHAR2
  Autor              : RRG
  ***************************************************************************/
  FUNCTION cup_fn_dev_nivel_camp_uti_cons
  (
    pscodpais  VARCHAR2,
    pscodprog  VARCHAR2,
    pscodnivel VARCHAR2,
    pscodclie  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  ) RETURN NUMBER IS
    val_cant NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO val_cant
      FROM cup_consu_cupon con
     WHERE con.cod_pais = pscodpais
       AND con.cod_prog = pscodprog
       AND con.cod_nivel = pscodnivel
       AND con.cod_cons = pscodclie
       AND con.ind_utili = '1'
       AND CON.CAM_UTILI = pscodigoperiodo   --campaña de proceso
          ---------------------------------------------
          -- para que devuelva solo los cupones NO Love
       AND NOT EXISTS (SELECT NULL
              FROM lov_equiv_matr em
             WHERE em.cod_prog = con.cod_prog
               AND em.cod_pais = con.cod_pais
               AND em.cod_cupon = con.cod_cupon
               AND em.cod_peri = con.cam_utili);
    RETURN val_cant;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
  END cup_fn_dev_nivel_camp_uti_cons;

  /**************************************************************************
  Descripcion        : CUP_PR_CARGA_CONSU_SUSCR
                     Carga los pedidos de la consultora de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_suscr
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    CURSOR curinsconsodetal IS
      SELECT ca.cod_pais   AS cod_pais,
             cem.cod_peri  AS cod_peri,
             ca.cod_clie   AS cod_clie,
             cem.cod_venta AS cod_venta,
             sude.val_dema AS val_unid,
             psusuario     AS usu_digi,
             SYSDATE       AS fec_digi,
             ca.num_lote   AS num_lote,
             ca.fec_soli   AS fec_soli
        FROM int_solic_conso_cabec ca,
             cup_suscr_nivel_consu suni,
             cup_suscr_detal_consu sude,
             cup_equiv_matr        cem
       WHERE suni.cod_pais = ca.cod_pais
         AND suni.cod_cons = ca.cod_clie
         AND suni.cod_pais = sude.cod_pais
         AND suni.cod_prog = sude.cod_prog
         AND suni.cod_cons = sude.cod_cons
         AND suni.cod_nive = sude.cod_nive
         AND sude.ind_util <> 1 --No utilizado
         AND sude.val_dema >= 1 --solo las que tengan pedidos
         AND ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND sude.cod_prog = pscodigoprograma
         AND pscodigoperiodo BETWEEN suni.cam_inic AND suni.cam_fin
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0'
         AND EXISTS (SELECT NULL
                FROM cup_suscr_cabec_consu suca
               WHERE suca.cod_pais = suni.cod_pais
                 AND suca.cod_prog = suni.cod_prog
                 AND suca.cod_cons = suni.cod_cons
                 AND suca.est_regi = 1 --Activo
              )
            -- Considerar solo los codigo de cupones
         AND cem.cod_pais = sude.cod_pais
         AND cem.cod_prog = sude.cod_prog
         AND cem.cod_cupon = sude.cod_cupo
         AND cem.cod_nivel = sude.cod_nive
         AND cem.cod_peri = pscodigoperiodo
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = cem.cod_venta);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_cod_venta IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_cabec.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_cabec.fec_digi%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais  t_cod_pais;
    v_cod_peri  t_cod_peri;
    v_cod_clie  t_cod_clie;
    v_cod_venta t_cod_venta;
    v_val_unid  t_val_unid;
    v_usu_digi  t_usu_digi;
    v_fec_digi  t_fec_digi;
    v_num_lote  t_num_lote;
    v_fec_soli  t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_cod_venta,
             v_val_unid,
             v_usu_digi,
             v_fec_digi,
             v_num_lote,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetal%ROWCOUNT;
      v_row_count := curinsconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli)
        VALUES
          (v_cod_pais(j),
           v_cod_peri(j),
           v_cod_clie(j),
           v_cod_venta(j),
           'OC',
           v_val_unid(j),
           'S', -- las agregadas por el programa de nuevas (Print Suscripcion)
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           '0',
           '0',
           v_fec_soli(j));

    END LOOP;
    CLOSE curinsconsodetal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_CARGA_CONSU_SUSCR: ' ||
                              ls_sqlerrm);
  END cup_pr_carga_consu_suscr;

  /**************************************************************************
  Descripcion        :       CUP_PR_CIERR_ACTUA_SUSCR_UTILI
                     Actualiza los cupones de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_suscr_utili
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    CURSOR curupdconsosuscr IS
      SELECT sude.cod_pais AS cod_pais,
             sude.cod_prog AS cod_prog,
             sude.cod_nive AS cod_nive,
             sude.cod_cupo AS cod_cupo,
             sude.cod_cons AS cod_cons,
             '1' AS ind_util,
             pscodigoperiodo AS cam_util,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM cup_suscr_detal_consu sude
       WHERE sude.cod_pais = pscodigopais
         AND sude.cod_prog = pscodigoprograma
         AND sude.ind_util = '0'
            -- Cupones facturados en la campaña de proceso
         AND EXISTS
       (SELECT NULL
                FROM cup_suscr_nivel_consu suni
               WHERE suni.cod_pais = sude.cod_pais
                 AND suni.cod_prog = sude.cod_prog
                 AND suni.cod_cons = sude.cod_cons
                 AND suni.cod_nive = sude.cod_nive
                 AND pscodigoperiodo BETWEEN suni.cam_inic AND suni.cam_fin)
         AND EXISTS (SELECT NULL
                FROM cup_detal_cupon_perio detal
               WHERE detal.cod_pais = sude.cod_pais
                 AND detal.cod_prog = sude.cod_prog
                 AND detal.cod_cons = sude.cod_cons
                 AND detal.cod_peri = pscodigoperiodo
                 AND detal.cod_venta =
                     cup_pkg_prog_nuevas.cup_fn_devue_codvta_by_cupon(detal.cod_pais,
                                                                      detal.cod_prog,
                                                                      detal.cod_peri,
                                                                      sude.cod_cupo,
                                                                      sude.cod_nive)
                 AND detal.ind_factu = '1');

    TYPE t_cod_pais IS TABLE OF cup_suscr_detal_consu.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_suscr_detal_consu.cod_prog%TYPE;
    TYPE t_cod_nive IS TABLE OF cup_suscr_detal_consu.cod_nive%TYPE;
    TYPE t_cod_cupo IS TABLE OF cup_suscr_detal_consu.cod_cupo%TYPE;
    TYPE t_cod_cons IS TABLE OF cup_suscr_detal_consu.cod_cons%TYPE;
    TYPE t_ind_util IS TABLE OF cup_suscr_detal_consu.ind_util%TYPE;
    TYPE t_cam_util IS TABLE OF cup_suscr_detal_consu.cam_util%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_suscr_detal_consu.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_suscr_detal_consu.fec_modi%TYPE;

    v_cod_pais t_cod_pais;
    v_cod_prog t_cod_prog;
    v_cod_nive t_cod_nive;
    v_cod_cupo t_cod_cupo;
    v_cod_cons t_cod_cons;
    v_ind_util t_ind_util;
    v_cam_util t_cam_util;
    v_usu_modi t_usu_modi;
    v_fec_modi t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsosuscr;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsosuscr BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nive,
             v_cod_cupo,
             v_cod_cons,
             v_ind_util,
             v_cam_util,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsosuscr%ROWCOUNT;
      v_row_count := curupdconsosuscr%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_suscr_detal_consu c
           SET c.ind_util = v_ind_util(j),
               c.cam_util = v_cam_util(j),
               c.usu_modi = v_usu_modi(j),
               c.fec_modi = v_fec_modi(j)
         WHERE c.cod_pais = v_cod_pais(j)
           AND c.cod_prog = v_cod_prog(j)
           AND c.cod_nive = v_cod_nive(j)
           AND c.cod_cons = v_cod_cons(j)
           AND c.cod_cupo = v_cod_cupo(j);

    END LOOP;
    CLOSE curupdconsosuscr;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_CIERR_ACTUA_SUSCR_UTILI: ' ||
                              ls_sqlerrm);
  END cup_pr_cierr_actua_suscr_utili;

  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_UNIDA_SUSCR
                     Actualiza las unidades de suscripcion
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_actua_unida_suscr
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             pscodigoprograma AS cod_prog,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             equiv.cod_nivel AS cod_nivel,
             det.cod_vent AS cod_venta,
             equiv.cod_cupon AS cod_cupon,
             det.val_unid_dem AS val_unid,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_equiv_matr        equiv
       WHERE
      -- join INT_SOLIC_CONSO_DETAL
       det.cod_pais = pscodigopais
      --------------------------------
      /*and det.num_lote = (select num_lote
       from bas_ctrl_fact
      where COD_PAIS = det.COD_PAIS
        and sta_camp = '0'
        and ind_camp_act = '1')*/
      --------------------------------
       AND det.cod_peri = pscodigoperiodo
       AND equiv.cod_pais = det.cod_pais
       AND equiv.cod_prog = pscodigoprograma
       AND equiv.cod_peri = det.cod_peri
       AND equiv.cod_venta = det.cod_vent
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
      AND cab.ind_proc_gp2 = '0'
        ---------------------------------
      AND cab.ind_erro_rech = '0'
      AND cab.ind_erro_remp = '0'
      AND cab.ind_erro_node = '0')
       AND
      -- validamos q los productos existan en el detalle de cupones x consultora x periodo
       EXISTS (SELECT detprodu.cod_venta
          FROM cup_detal_cupon_perio detprodu
         WHERE detprodu.cod_pais = det.cod_pais
           AND detprodu.cod_prog = pscodigoprograma
           AND detprodu.cod_cons = det.cod_clie
           AND detprodu.cod_peri = det.cod_peri
           AND detprodu.cod_venta = det.cod_vent
           AND detprodu.ind_factu = '0' -- no facturado
        )
       ORDER BY det.cod_clie,
                equiv.cod_nivel,
                equiv.val_prio ASC;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_equiv_matr.cod_nivel%TYPE;
    TYPE t_cod_cupon IS TABLE OF cup_equiv_matr.cod_cupon%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_val_unid     t_val_unid;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;
    v_cod_prog     t_cod_prog;
    v_cod_nivel    t_cod_nivel;
    v_cod_cupon    t_cod_cupon;

    rows          NATURAL := 1000; -- Number of rows to process at a time
    j             BINARY_INTEGER := 0;
    v_row_count   NUMBER := 0;
    codcliente    int_solic_conso_detal.cod_clie%TYPE := '0';
    codnivel      cup_equiv_matr.cod_nivel%TYPE := '0';
    num_permitido NUMBER := 0;
    num_cantidad  NUMBER := 0;
    valunid       NUMBER := 0;
    indtope       VARCHAR2(1) := 0;

  BEGIN

    OPEN curupdconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_prog,
             v_cod_clie,
             v_num_lote,
             v_cod_nivel,
             v_cod_vent,
             v_cod_cupon,
             v_val_unid,
             v_tip_posic,
             v_ind_erro_sse,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FOR j IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        IF (j > 1) THEN
          valunid := to_number(v_val_unid(j - 1));
        END IF;

        IF ((v_cod_clie(j) <> codcliente) OR (v_cod_nivel(j) <> codnivel)) THEN
          indtope       := 0;
          codcliente    := v_cod_clie(j);
          codnivel      := v_cod_nivel(j);
          num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel(v_cod_nivel(j),
                                                                       v_cod_peri(j),
                                                                       pscodigoprograma);
          num_cantidad  := cup_pkg_prog_nuevas.cup_fn_dev_unidad_suscr_nivel(v_cod_pais(j),
                                                                             v_cod_prog(j),
                                                                             v_cod_nivel(j),
                                                                             v_cod_clie(j));
          valunid       := num_cantidad;
        END IF;

        IF (indtope = 1) THEN
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = v_ind_erro_sse(j),
                 usu_modi     = v_usu_modi(j),
                 fec_modi     = v_fec_modi(j)
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = codcliente
             AND num_lote = v_num_lote(j)
             AND cod_vent = v_cod_vent(j)
             AND tip_posic = v_tip_posic(j);

        ELSE

          num_cantidad := num_cantidad + to_number(v_val_unid(j));
          IF (num_cantidad > num_permitido) THEN
            UPDATE int_solic_conso_detal
               SET val_unid_dem = num_permitido - valunid,
                   usu_modi     = v_usu_modi(j),
                   fec_modi     = v_fec_modi(j)
             WHERE cod_pais = v_cod_pais(j)
               AND cod_peri = v_cod_peri(j)
               AND cod_clie = codcliente
               AND num_lote = v_num_lote(j)
               AND cod_vent = v_cod_vent(j)
               AND tip_posic = v_tip_posic(j);
            indtope := 1;
          END IF;

        END IF;

      END LOOP;

    END LOOP;
    CLOSE curupdconsodetal;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ACTUA_UNIDA_SUSCR: ' ||
                              ls_sqlerrm);
  END cup_pr_actua_unida_suscr;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_INDIC_CUPON
                     Devuelve el indicador de cupon actual
  Fecha Creacion     : 18/05/2009
  Parametros Entrada:
      psCodPais : Codigo de pais
      psCodPrograma : Codigo Programa
  Autor              : Rosalvina Ramirez
  ***************************************************************************/
  FUNCTION cup_fn_devue_indic_cupon
  (
    pscodpais     VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN VARCHAR2 IS
    v_ind_cupon VARCHAR2(6);
  BEGIN
    SELECT ind_cupo
      INTO v_ind_cupon
      FROM cup_prog_nueva_cupon
     WHERE cod_pais = pscodpais
       AND cod_prog = pscodprograma;

    RETURN v_ind_cupon;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '0';
  END cup_fn_devue_indic_cupon;

  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_CONSU_FACTU_CAMPA
                      Actualiza las consultoras nuevas que pasaron pedido y facturaron
                      en el periodo en la tabla de facturacion de consultoras AL FINAL
                      DE CAMPAÑA
  Fecha Creacion     : 21/05/2009
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_consu_factu_campa
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
  BEGIN
    UPDATE cup_consu_factu a
       SET a.ind_cons      = '1',
           a.usu_modi      = psusuario,
           a.fec_modi      = SYSDATE,
           a.cod_peri_noco = pscodigoperiodo
     WHERE a.cod_pais = pscodigopais
       AND a.cod_prog = pscodigoprograma
       AND a.ind_cons = '0'
          --Que no haya hecho pedido
       AND EXISTS (SELECT NULL
              FROM cup_consu_nueva ccn
             WHERE ccn.cod_pais = a.cod_pais
               AND ccn.cod_prog = a.cod_prog
               AND ccn.cod_cons = a.cod_cons
               AND ccn.camp_fin_ccc < pscodigoperiodo);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ACTUA_CONSU_FACTU_CAMPA: ' ||
                              ls_sqlerrm);
  END cup_pr_actua_consu_factu_campa;

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_ANFAC
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que no ha facturado
  Fecha Creacion    : 21/08/2009
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_anfac
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  ) IS
    ls_indicador_constancia VARCHAR2(1);
    ls_indicador_cupon      VARCHAR2(1);
    ls_codigo_programa      VARCHAR2(3);

  BEGIN

    -- Obtiene la parametria del pais
    SELECT cod_prog,
           ind_const,
           ind_cupo
      INTO ls_codigo_programa,
           ls_indicador_constancia,
           ls_indicador_cupon
      FROM cup_prog_nueva_cupon
     WHERE cod_pais = pscodigopais;

    -- LOVE --
    DELETE FROM lov_histo_movim lh
     WHERE lh.cod_pais = pscodigopais
       AND lh.cod_prog = ls_codigo_programa
       AND lh.cod_cons = pscodigoconsultora
       AND lh.cod_peri = pscodigoperiodo;
    ----------
    IF ls_indicador_cupon = '1' AND ls_indicador_constancia = '0' THEN
      -- Reversa el proceso CUP_PR_CARGA_NOCON_NIVEL_PRIME
      -- Este caso les crea nivel desde 1er pedido
      -- SI ES NUEVA - Borrar todos los niveles
      DELETE FROM cup_consu_nivel c
       WHERE c.cod_pais = pscodigopais
         AND c.cod_prog = ls_codigo_programa
         AND c.cod_cons = pscodigoconsultora
         AND EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva gtt
               WHERE gtt.cod_clie = c.cod_cons
                 AND gtt.val_esta_clie IN ('01', '07'));
    END IF;

    IF ls_indicador_cupon = '1' AND ls_indicador_constancia = '1' THEN
      -- Reversa el proceso CUP_PR_CARGA_CONSU_NIVEL_PRIME
      -- Este caso les crea nivel desde 1er pedido
      -- Borrar solo el que se creo para el actual nivel
      DELETE FROM cup_consu_nivel
       WHERE cod_pais = pscodigopais
         AND cod_prog = ls_codigo_programa
         AND cod_cons = pscodigoconsultora
         AND cam_inic_vig = pscodigoperiodo;
    END IF;

    IF ls_indicador_constancia = '0' THEN
      -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_NOCON
      -- Actualiza las consultoras que no son nuevas
      UPDATE cup_consu_nueva ant
         SET ant.camp_fin_ccc = nvl(ant.camp_fin_ccc_hist,
                                    (SELECT MAX(ca.cod_peri)
                                       FROM ped_histo_solic_conso_cabec ca
                                      WHERE ca.cod_clie = ant.cod_cons
                                        AND ca.ind_proc_gp2 = '1'
                                        AND ca.ind_ocs_proc = '1')),

             ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                     cup_fn_devue_nivel_nocon(ant.camp_ini_ccc,
                                                              (SELECT MAX(ca.cod_peri)
                                                                 FROM ped_histo_solic_conso_cabec ca
                                                                WHERE ca.cod_clie =
                                                                      ant.cod_cons
                                                                  AND ca.ind_proc_gp2 = '1'
                                                                  AND ca.ind_ocs_proc = '1'))),
             ant.est_proc      = decode((SELECT COUNT(1)
                                          FROM ped_histo_solic_conso_cabec ca
                                         WHERE ca.cod_clie = ant.cod_cons
                                           AND ca.cod_peri =
                                               gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                      -1)
                                           AND ca.ind_proc_gp2 = '1'
                                           AND ca.ind_ocs_proc = '1'),
                                        0,
                                        '0',
                                        '1'),
             ant.usu_modi      = psusuario,
             ant.fec_modi      = SYSDATE
       WHERE ant.cod_pais = pscodigopais
         AND ant.cod_prog = ls_codigo_programa
         AND ant.cod_cons = pscodigoconsultora
         AND EXISTS (SELECT NULL
                FROM cup_consu_nueva nu
               WHERE nu.cod_cons = ant.cod_cons
                 AND nu.camp_ini_ccc <> pscodigoperiodo)
         AND NOT EXISTS
       (SELECT NULL
                FROM gtt_cup_consu_nueva gtt
               WHERE gtt.cod_clie = ant.cod_cons
                 AND gtt.val_esta_clie IN ('01', '07'));
    END IF;

    IF ls_indicador_constancia = '1' THEN
      -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_CONST
      -- Actualiza las consultoras que no son nuevas
      UPDATE cup_consu_nueva ant
         SET ant.camp_fin_ccc  = nvl(ant.camp_fin_ccc_hist,
                                     gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                            -1)),
             ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                     TRIM(to_char(ant.cod_ult_nivel - 1, '09'))),
             ant.est_proc      = '1',
             ant.usu_modi      = psusuario,
             ant.fec_modi      = SYSDATE
       WHERE ant.cod_pais = pscodigopais
         AND ant.cod_prog = ls_codigo_programa
         AND ant.cod_cons = pscodigoconsultora
         AND ant.camp_fin_ccc = pscodigoperiodo --Periodo a anular
         AND NOT EXISTS
       (SELECT NULL
                FROM gtt_cup_consu_nueva gtt
               WHERE gtt.cod_clie = ant.cod_cons
                 AND gtt.val_esta_clie IN ('01', '07'));
      -- si la campaña de fin es igual a la actual es xq es constante y paso pedido la campaña anterior
    END IF;

    -- Reversa el proceso CUP_PR_CARGA_CONSU_FACTU
    -- Si son nuevas NUEVAS (estado 01 en MAE)
    -- Borrar de CUP_CONSU_FACTU a esa consultora
    DELETE FROM cup_consu_factu c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_prog = ls_codigo_programa
       AND c.cod_cons = pscodigoconsultora
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = c.cod_cons
               AND gtt.val_esta_clie IN ('01'));

    ----------------------------------------------------------
    -- Por constraints, SI ES NUEVA, borro el cup_consu_cupon
    -- y el cup_detal_cupon_perio
    ----------------------------------------------------------
    DELETE FROM cup_consu_cupon c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_prog = ls_codigo_programa
       AND c.cod_cons = pscodigoconsultora
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = c.cod_cons
               AND gtt.val_esta_clie IN ('01'));

    DELETE FROM cup_detal_cupon_perio c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_prog = ls_codigo_programa
       AND c.cod_cons = pscodigoconsultora
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = c.cod_cons
               AND gtt.val_esta_clie IN ('01'));
    ----------------------------------------------------------

    -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
    -- Si son nuevas NUEVAS (estado 01 en MAE)
    -- Borrar de CUP_CONSU_NUEVAS a esa consultora
    DELETE cup_consu_nueva c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_prog = ls_codigo_programa
       AND c.cod_cons = pscodigoconsultora
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = c.cod_cons
               AND gtt.val_esta_clie IN ('01'));

    -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
    -- Si son nuevas REINGRESOS (estado 07 en MAE)
    -- Modificar el registro en CUP_CONSU_NUEVAS para esa consultora
    UPDATE cup_consu_nueva n
       SET n.camp_ini_ccc  = nvl(n.camp_ini_ccc_hist,
                                 (SELECT MIN(ca.cod_peri)
                                    FROM ped_histo_solic_conso_cabec ca
                                   WHERE ca.cod_clie = n.cod_cons
                                     AND ca.ind_proc_gp2 = '1'
                                     AND ca.ind_ocs_proc = '1')),
           n.camp_fin_ccc  = nvl(n.camp_fin_ccc_hist,
                                 (SELECT MAX(ca.cod_peri)
                                    FROM ped_histo_solic_conso_cabec ca
                                   WHERE ca.cod_clie = n.cod_cons
                                     AND ca.ind_ocs_proc = '1'
                                     AND ca.ind_proc_gp2 = '1')),
           n.cod_ult_nivel = nvl(n.cod_ult_nivel_hist,
                                 cup_fn_devue_nivel_nocon((SELECT MIN(ca.cod_peri)
                                                            FROM ped_histo_solic_conso_cabec ca
                                                           WHERE ca.cod_clie =
                                                                 n.cod_cons
                                                             AND ca.ind_ocs_proc = '1'
                                                             AND ca.ind_proc_gp2 = '1'),
                                                          (SELECT MAX(ca.cod_peri)
                                                             FROM ped_histo_solic_conso_cabec ca
                                                            WHERE ca.cod_clie =
                                                                  n.cod_cons
                                                              AND ca.ind_proc_gp2 = '1'
                                                              AND ca.ind_ocs_proc = '1'))),
           n.est_proc     =
           (decode((SELECT COUNT(1)
                     FROM ped_histo_solic_conso_cabec ca
                    WHERE ca.cod_clie = n.cod_cons
                      AND ca.cod_peri =
                          gen_fn_dev_nsgte_campa(pscodigoperiodo, -1)
                      AND ca.ind_proc_gp2 = '1'
                      AND ca.ind_ocs_proc = '1'),
                   0,
                   0,
                   1)),
           n.usu_modi      = psusuario,
           n.fec_modi      = SYSDATE
     WHERE n.cod_pais = pscodigopais
       AND n.cod_prog = ls_codigo_programa
       AND n.cod_cons = pscodigoconsultora
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = n.cod_cons
               AND gtt.val_esta_clie IN ('07'));

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ANULA_PEDID_ANFAC: ' ||
                              ls_sqlerrm);
  END cup_pr_anula_pedid_anfac;

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_CIERR_FUERA
                      Procedimiento para revertir los cambios en Programa
                      Nuevas al cierre de la campaña para los pedidos
                      anulados no gestionados
  Fecha Creacion    : 17/08/2009
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_cierr_fuera
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  ) IS
    CURSOR cursorconsultoras IS
      SELECT DISTINCT ca.cod_clie
        FROM ped_histo_solic_conso_cabec ca
       WHERE ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pscodigopais
         AND ca.ind_proc_gp2 != '1'
         AND NOT EXISTS (SELECT NULL
                FROM ped_histo_solic_conso_cabec cc
               WHERE cc.cod_pais = ca.cod_pais
                 AND cc.cod_peri = ca.cod_peri
                 AND cc.cod_clie = ca.cod_clie
                 AND cc.ind_ocs_proc = '1'
                 AND cc.ind_proc_gp2 = '1');

    TYPE t_cod_cons IS TABLE OF ped_histo_solic_conso_cabec.cod_clie%TYPE;
    v_cod_cons t_cod_cons;

    ls_codigo_programa cup_prog_nueva_cupon.cod_prog%TYPE;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    v_row_count NUMBER := 0;

  BEGIN
    OPEN cursorconsultoras;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cursorconsultoras BULK COLLECT
        INTO v_cod_cons LIMIT rows;

      EXIT WHEN v_row_count = cursorconsultoras%ROWCOUNT;
      v_row_count := cursorconsultoras%ROWCOUNT;
      IF v_cod_cons.count > 0 THEN
        FOR i IN v_cod_cons.first .. v_cod_cons.last
        LOOP
          cup_pr_anula_pedid_anfac(pscodigopais,
                                   pscodigoperiodo,
                                   v_cod_cons(i),
                                   psusuario);
        END LOOP;
      END IF;
    END LOOP;
    CLOSE cursorconsultoras;

    SELECT cu.cod_prog
      INTO ls_codigo_programa
      FROM cup_prog_nueva_cupon cu
     WHERE cu.cod_pais = pscodigopais;

    cup_pr_actua_consu_factu_campa(pscodigopais,
                                   pscodigoperiodo,
                                   ls_codigo_programa,
                                   psusuario);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ANULA_CIERR_FUERA: ' ||
                              ls_sqlerrm);
  END cup_pr_anula_cierr_fuera;

  -------------------------
  -- STORES MODULO LOVE

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_DETAL_CUPON_SICC
                      Resetea el IND_FACTU a 0 a los productos tabla Detalle Cupon
                      que hayan sido retirados por SiCC (PED_SOLIC_POSIC)
  Fecha Creacion    : 15/10/2009
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_detal_cupon_sicc
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    CURSOR curupddetalcupon(vnoidperiodo NUMBER) IS
      SELECT soliconsodet.cod_pais AS cod_pais,
             pscodigoprograma AS cod_programa,
             soliconsodet.cod_clie AS cod_consu,
             soliconsodet.cod_peri AS cod_periodo,
             soliconsodet.cod_vent AS cod_venta,
             0 AS val_unida_real,
             '0' AS ind_factu,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal soliconsodet,
             int_solic_conso_cabec soliconsocab
       WHERE soliconsodet.cod_pais = pscodigopais
         AND soliconsodet.cod_peri = pscodigoperiodo
         AND soliconsocab.cod_pais = soliconsodet.cod_pais
         AND soliconsocab.cod_peri = soliconsodet.cod_peri
         AND soliconsocab.cod_clie = soliconsodet.cod_clie
         AND soliconsocab.num_lote = soliconsodet.num_lote
         AND soliconsodet.ind_erro_sse = '0'
         AND soliconsocab.ind_ocs_proc = '1'
         AND soliconsocab.ind_proc_gp2 = '1'
         AND EXISTS (SELECT NULL
                FROM cup_detal_cupon_perio detal
               WHERE detal.cod_pais = pscodigopais
                 AND detal.cod_prog = pscodigoprograma
                 AND detal.cod_cons = soliconsodet.cod_clie
                 AND detal.cod_peri = pscodigoperiodo
                 AND detal.cod_venta = soliconsodet.cod_vent
                 AND detal.ind_factu = '1' -- productos no facturados
              )
         AND EXISTS
       (SELECT NULL
                FROM ped_solic_cabec      soc,
                     ped_solic_posic      pos,
                     seg_pais             pai,
                     seg_socie            soci,
                     cra_perio            peri,
                     ped_tipo_solic       ts,
                     int_param_tipo_solic pt,
                     bel_almac            alm,
                     ped_tipo_solic_pais  tsp,
                     mae_clien            mae
               WHERE pos.soca_oid_soli_cabe = soc.oid_soli_cabe
                 AND soc.tspa_oid_tipo_soli_pais =
                     pt.tspa_oid_tipo_soli_pais
                 AND pt.num_unid_vend = 1
                 AND soc.perd_oid_peri = peri.oid_peri
                 AND soc.pais_oid_pais = pai.oid_pais
                 AND soc.soci_oid_soci = soci.oid_soci
                 AND soc.almc_oid_alma = alm.oid_alma
                 AND soc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                 AND soc.perd_oid_peri = vnoidperiodo
                 AND soc.fec_fact IS NOT NULL
                 AND soc.ind_ts_no_conso = 1
                 AND soc.ind_oc = 1
                 AND soc.ind_pedi_prue = 0
                 AND pos.val_codi_vent IS NOT NULL
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.ind_anul = 0
                 AND ts.ind_devo = 0
                 AND pos.num_unid_aten = 0 -- Que el detalle no tenga unidades
                 AND pos.val_codi_vent = soliconsodet.cod_vent
                 AND soc.clie_oid_clie = mae.oid_clie
                 AND mae.cod_clie = soliconsodet.cod_clie
                    -----------------------------------------
                 AND trunc(soc.fec_fact) = trunc(soliconsocab.fec_prog_fact));

    TYPE t_cod_pais IS TABLE OF cup_detal_cupon_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_detal_cupon_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_detal_cupon_perio.cod_cons%TYPE;
    TYPE t_cod_peri IS TABLE OF cup_detal_cupon_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF cup_detal_cupon_perio.cod_venta%TYPE;
    TYPE t_val_unida_pedid IS TABLE OF cup_detal_cupon_perio.val_unida_pedid%TYPE;
    TYPE t_ind_factu IS TABLE OF cup_detal_cupon_perio.ind_factu%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_detal_cupon_perio.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_detal_cupon_perio.fec_modi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_peri        t_cod_peri;
    v_cod_venta       t_cod_venta;
    v_val_unida_pedid t_val_unida_pedid;
    v_ind_factu       t_ind_factu;
    v_usu_modi        t_usu_modi;
    v_fec_modi        t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

    lnoidperiodo cra_perio.oid_peri%TYPE;

  BEGIN

    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    OPEN curupddetalcupon(lnoidperiodo);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupddetalcupon BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_peri,
             v_cod_venta,
             v_val_unida_pedid,
             v_ind_factu,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupddetalcupon%ROWCOUNT;
      v_row_count := curupddetalcupon%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_detal_cupon_perio
           SET val_unida_pedid = v_val_unida_pedid(j),
               ind_factu       = v_ind_factu(j),
               usu_modi        = v_usu_modi(j),
               fec_modi        = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_venta = v_cod_venta(j);

    END LOOP;
    CLOSE curupddetalcupon;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ACTUA_DETAL_CUPON_SICC: ' ||
                              ls_sqlerrm);
  END cup_pr_actua_detal_cupon_sicc;

  /**************************************************************************
  Descripcion       : CUP_PR_PROCE_CIERR_CUPON_MULTI
                      Procedimiento principal que llama a los procedimientos que
                      se deben ejecutar para el cierre de facturacion que soporta la
                      configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte

  Fecha Modificacion  : 03/09/2013
  Autor Modificacion : Yahir Rivas Luna.
  ***************************************************************************/
  PROCEDURE cup_pr_proce_cierr_cupon_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  ) IS
    CURSOR curprogramas IS
      SELECT cod_prog,
             ind_const,
             ind_cons_prem,
             ind_cupo,
             num_vig,
             ind_acti_love,
             cam_fin
        FROM cup_prog_nueva_cupon p
       WHERE p.ind_esta IN ('A', 'V')
         AND cod_pais = pscodigopais;

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_cons_prem IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem%TYPE;
    TYPE t_ind_cupo IS TABLE OF cup_prog_nueva_cupon.ind_cupo%TYPE;
    TYPE t_num_vig IS TABLE OF cup_prog_nueva_cupon.num_vig%TYPE;
    TYPE t_ind_acti_love IS TABLE OF cup_prog_nueva_cupon.ind_acti_love%TYPE;
    TYPE t_cam_fin IS TABLE OF cup_prog_nueva_cupon.cam_fin%TYPE;

    v_cod_prog      t_cod_prog;
    v_ind_const     t_ind_const;
    v_ind_cons_prem t_ind_cons_prem;
    v_ind_cupo      t_ind_cupo;
    v_num_vig       t_num_vig;
    v_ind_acti_love t_ind_acti_love;
    v_cam_fin       t_cam_fin;

    v_row_count NUMBER := 0;
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    vncampa     NUMBER(15);
    vncampa2    NUMBER(15);

  BEGIN

    SELECT to_number(val_para)
      INTO vncampa
      FROM nvs_param_gener
     WHERE cod_para = '02'
       AND pais_oid_pais =
           gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    -- cargo las consultoras nuevas a una tabla temporal
    INSERT INTO gtt_cup_consu_nueva
      (cod_clie,
       val_esta_clie)
      (SELECT mc.cod_clie,
              est.cod_esta_clie
         FROM mae_clien_datos_adici ad,
              mae_estat_clien       est,
              mae_clien             mc
        WHERE ad.clie_oid_clie = mc.oid_clie
          AND ad.esta_oid_esta_clie = est.oid_esta_clie
          AND est.cod_esta_clie IN ('01', '07')
          AND ad.ind_acti = 1
          AND EXISTS
        (SELECT NULL
                 FROM mae_clien_tipo_subti mcts,
                      mae_subti_clien      msc,
                      mae_tipo_clien       mtc
                WHERE mcts.clie_oid_clie = ad.clie_oid_clie
                  AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                  AND mtc.cod_tipo_clie = '02'
                  AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                  AND msc.cod_subt_clie = '04'));

    OPEN curprogramas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curprogramas BULK COLLECT
        INTO v_cod_prog,
             v_ind_const,
             v_ind_cons_prem,
             v_ind_cupo,
             v_num_vig,
             v_ind_acti_love,
             v_cam_fin LIMIT rows;
      EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
      v_row_count := curprogramas%ROWCOUNT;
      -- Recorro todos los programas creados para Nuevas
      FOR i IN v_cod_prog.first .. v_cod_prog.last
      LOOP
        IF (v_cod_prog(i) IS NOT NULL) THEN
          BEGIN

            -- Actualiza las consultoras  con pedidos facturados
            cup_pr_cierr_actua_consu(pscodigopais,
                                     pscodigoperiodo,
                                     v_cod_prog(i),
                                     psusuario);

            -- Actualiza el indicador de pedido a las nuevas que solicitaron
            -- el producto parametrizado en su primer pedido
            -- 04-08-2010

            /*--No se esta utilizado
            cup_pr_cierr_actua_prime_pedid(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario);
            */
            --

            -- Actualiza detalle de cupones facturados
            cup_pr_cierr_actua_detal_cupon(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario);
            -- Proceso que reversa la informacion de los detalles
            -- que SiCC anulo en PED_SOLIC_POSIC
            cup_pr_actua_detal_cupon_sicc(pscodigopais,
                                          pscodigoperiodo,
                                          v_cod_prog(i),
                                          psusuario);
            -- Actualiza cupon utilizado
            cup_pr_cierr_actua_cupon_utili(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario);
            -- Despacho de Cupones desde el segundo pedido
            IF (v_ind_cupo(i) = '0') THEN
              IF (v_ind_const(i) = '0') THEN
                -- Actualiza carga de nivel Programa Sin Constancia
                cup_pr_cierr_carga_nocon_nivel(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario);
              ELSIF (v_ind_const(i) = '1') THEN
                -- Actualiza carga de nivel Programa Con Constancia
                cup_pr_cierr_carga_consu_nivel(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario);
              END IF;
            END IF;

          /*  IF (to_number(v_cam_fin(i)) <= to_number(pscodigoperiodo)) THEN

              SELECT ven_pkg_repor.ven_fn_devue_nume_campa(v_cam_fin(i),
                                                           pscodigoperiodo,
                                                           gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                                                           gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                                           gen_pkg_gener.gen_fn_devuelve_id_canal('VD'))
                INTO vncampa2
                FROM dual;

              IF ((vncampa2 - 1) + 1) < vncampa THEN
                UPDATE cup_prog_nueva_cupon  SET ind_esta = 'V'
                 WHERE cod_prog = v_cod_prog(i);
              ELSE
                UPDATE cup_prog_nueva_cupon  SET ind_esta = 'C'
                 WHERE cod_prog = v_cod_prog(i);
              END IF;
            END IF;  */

          END;
        END IF;
      END LOOP;
    END LOOP;
    CLOSE curprogramas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_PROCE_CIERR_CUPON_MULTI: ' ||
                              ls_sqlerrm);

  END cup_pr_proce_cierr_cupon_multi;

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_ANFAC_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que no ha facturado que soporta la
            configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_anfac_multi
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  ) IS

    CURSOR curprogramas IS
      SELECT cod_prog,
             ind_const,
             ind_cons_prem,
             ind_cupo,
             num_vig,
             ind_acti_love
        FROM cup_prog_nueva_cupon
       WHERE cod_pais = pscodigopais
       AND  ind_esta in ( 'A', 'V');

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_cons_prem IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem%TYPE;
    TYPE t_ind_cupo IS TABLE OF cup_prog_nueva_cupon.ind_cupo%TYPE;
    TYPE t_num_vig IS TABLE OF cup_prog_nueva_cupon.num_vig%TYPE;
    TYPE t_ind_acti_love IS TABLE OF cup_prog_nueva_cupon.ind_acti_love%TYPE;

    v_cod_prog      t_cod_prog;
    v_ind_const     t_ind_const;
    v_ind_cons_prem t_ind_cons_prem;
    v_ind_cupo      t_ind_cupo;
    v_num_vig       t_num_vig;
    v_ind_acti_love t_ind_acti_love;

    v_row_count NUMBER := 0;
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;

    lnoidtiposolisoc ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
    lnoidperiodo     NUMBER;

  BEGIN

    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
    -- cargo las consultoras nuevas a una tabla temporal
    /*INSERT INTO gtt_cup_consu_nueva(cod_clie,
                                    val_esta_clie)
    (SELECT mc.cod_clie,
            est.cod_esta_clie
       FROM MAE_CLIEN_DATOS_ADICI AD,
            MAE_ESTAT_CLIEN EST,
            MAE_CLIEN MC
      WHERE ad.clie_oid_clie = mc.oid_clie
        AND AD.ESTA_OID_ESTA_CLIE = EST.OID_ESTA_CLIE
        AND EST.COD_ESTA_CLIE IN ('01', '07')
        AND AD.IND_ACTI = 1
        AND EXISTS (SELECT NULL
               FROM MAE_CLIEN_TIPO_SUBTI MCTS,
                    MAE_SUBTI_CLIEN      MSC,
                    MAE_TIPO_CLIEN       MTC
              WHERE MCTS.CLIE_OID_CLIE = AD.CLIE_OID_CLIE
                AND MCTS.TICL_OID_TIPO_CLIE = MTC.OID_TIPO_CLIE
                AND MTC.COD_TIPO_CLIE = '02'
                AND MCTS.SBTI_OID_SUBT_CLIE = MSC.OID_SUBT_CLIE
                AND MSC.COD_SUBT_CLIE = '04'));*/

    SELECT tsp.oid_tipo_soli_pais
      INTO lnoidtiposolisoc
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND ts.cod_tipo_soli = 'SOC';

    OPEN curprogramas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curprogramas BULK COLLECT
        INTO v_cod_prog,
             v_ind_const,
             v_ind_cons_prem,
             v_ind_cupo,
             v_num_vig,
             v_ind_acti_love LIMIT rows;
      EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
      v_row_count := curprogramas%ROWCOUNT;

      -- Recorro todos los programas creados para Nuevas
      FOR i IN v_cod_prog.first .. v_cod_prog.last
      LOOP
        -- LOVE --
        DELETE FROM lov_histo_movim lh
         WHERE lh.cod_pais = pscodigopais
           AND lh.cod_prog = v_cod_prog(i)
           AND lh.cod_cons = pscodigoconsultora
           AND lh.cod_peri = pscodigoperiodo;
        ----------
        IF v_ind_cupo(i) = '1' AND v_ind_const(i) = '0' THEN
          -- Reversa el proceso CUP_PR_CARGA_NOCON_NIVEL_PRIME
          -- Este caso les crea nivel desde 1er pedido
          -- SI ES NUEVA - Borrar todos los niveles
          DELETE FROM cup_consu_nivel c
           WHERE c.cod_pais = pscodigopais
             AND c.cod_prog = v_cod_prog(i)
             AND c.cod_cons = pscodigoconsultora
             AND EXISTS
           (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = c.cod_cons
                     AND gtt.val_esta_clie IN ('01', '07'));
        END IF;

        IF v_ind_cupo(i) = '1' AND v_ind_const(i) = '1' THEN
          -- Reversa el proceso CUP_PR_CARGA_CONSU_NIVEL_PRIME
          -- Este caso les crea nivel desde 1er pedido
          -- Borrar solo el que se creo para el actual nivel
          DELETE FROM cup_consu_nivel
           WHERE cod_pais = pscodigopais
             AND cod_prog = v_cod_prog(i)
             AND cod_cons = pscodigoconsultora
             AND cam_inic_vig = pscodigoperiodo;
        END IF;

        IF v_ind_const(i) = '0' THEN
          -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_NOCON
          -- Actualiza las consultoras que no son nuevas
          UPDATE cup_consu_nueva ant
             SET /*ant.camp_fin_ccc = NVL(ant.camp_fin_ccc_hist, (SELECT MAX(ca.cod_peri)
                                                                  FROM ped_histo_solic_conso_cabec ca
                                                                 WHERE ca.cod_clie = ant.cod_cons
                                                                   AND ca.ind_proc_gp2 = '1'
                                                                   AND ca.ind_ocs_proc = '1')),
                 ant.cod_ult_nivel = NVL (ant.cod_ult_nivel_hist, CUP_FN_DEVUE_NIVEL_NOCON(ant.camp_ini_ccc,
                                                                                           (SELECT MAX(ca.cod_peri)
                                                                                              FROM ped_histo_solic_conso_cabec ca
                                                                                             WHERE ca.cod_clie = ant.cod_cons
                                                                                               AND ca.ind_proc_gp2 = '1'
                                                                                               AND ca.ind_ocs_proc = '1'))),
                 ant.est_proc = decode((SELECT COUNT(1)
                                          FROM ped_histo_solic_conso_cabec ca
                                         WHERE ca.cod_clie = ant.cod_cons
                                           AND ca.cod_peri = GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo,-1)
                                           AND ca.ind_proc_gp2 = '1'
                                           AND ca.ind_ocs_proc = '1'),0,'0','1'),*/ ant.camp_fin_ccc = nvl(ant.camp_fin_ccc_hist,
                                                                                                           gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MAX(ca.perd_oid_peri),
                                                                                                                                                              lnoidperiodo)
                                                                                                                                                     FROM ped_solic_cabec ca,
                                                                                                                                                          mae_clien       d
                                                                                                                                                    WHERE ca.clie_oid_clie =
                                                                                                                                                          d.oid_clie
                                                                                                                                                      AND ca.pais_oid_pais =
                                                                                                                                                          d.pais_oid_pais
                                                                                                                                                      AND d.cod_clie =
                                                                                                                                                          ant.cod_cons
                                                                                                                                                      AND ca.ind_oc = '1'
                                                                                                                                                      AND ca.tspa_oid_tipo_soli_pais =
                                                                                                                                                          lnoidtiposolisoc
                                                                                                                                                      AND ca.fec_fact IS NOT NULL))),

                 ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                         cup_fn_devue_nivel_nocon_cierr(ant.camp_ini_ccc,
                                                                  gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MAX(ca.perd_oid_peri),
                                                                                                                     lnoidperiodo)
                                                                                                            FROM ped_solic_cabec ca,
                                                                                                                 mae_clien       d
                                                                                                           WHERE ca.clie_oid_clie =
                                                                                                                 d.oid_clie
                                                                                                             AND ca.pais_oid_pais =
                                                                                                                 d.pais_oid_pais
                                                                                                             AND d.cod_clie =
                                                                                                                 ant.cod_cons
                                                                                                             AND ca.ind_oc = '1'
                                                                                                             AND ca.fec_fact IS NOT NULL
                                                                                                             AND ca.tspa_oid_tipo_soli_pais =
                                                                                                                 lnoidtiposolisoc)))),

                 ant.est_proc = decode((SELECT COUNT(1)
                                         FROM ped_solic_cabec ca,
                                              mae_clien       d
                                        WHERE ca.clie_oid_clie = d.oid_clie
                                          AND ca.pais_oid_pais =
                                              d.pais_oid_pais
                                          AND d.cod_clie = ant.cod_cons
                                          AND gen_pkg_gener.gen_fn_devuelve_des_perio(ca.perd_oid_peri) =
                                              gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                     -1)
                                          AND ca.tspa_oid_tipo_soli_pais =
                                              lnoidtiposolisoc
                                          AND ca.fec_fact IS NOT NULL
                                          AND ca.ind_oc = '1'),
                                       0,
                                       '0',
                                       '1'),

                 ant.usu_modi = psusuario,
                 ant.fec_modi = SYSDATE
           WHERE ant.cod_pais = pscodigopais
             AND ant.cod_prog = v_cod_prog(i)
             AND ant.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM cup_consu_nueva nu
                   WHERE nu.cod_cons = ant.cod_cons
                     AND nu.camp_ini_ccc <> pscodigoperiodo)
             AND NOT EXISTS
           (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = ant.cod_cons
                     AND gtt.val_esta_clie IN ('01', '07'));
        END IF;

        IF v_ind_const(i) = '1' THEN
          -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_CONST
          -- Actualiza las consultoras que no son nuevas
          UPDATE cup_consu_nueva ant
             SET ant.camp_fin_ccc  = nvl(ant.camp_fin_ccc_hist,
                                         gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                -1)),
                 ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                         TRIM(to_char(ant.cod_ult_nivel - 1,
                                                      '09'))),
                 ant.est_proc      = '1',
                 ant.usu_modi      = psusuario,
                 ant.fec_modi      = SYSDATE
           WHERE ant.cod_pais = pscodigopais
             AND ant.cod_prog = v_cod_prog(i)
             AND ant.cod_cons = pscodigoconsultora
             AND ant.camp_fin_ccc = pscodigoperiodo --Periodo a anular
             AND NOT EXISTS
           (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = ant.cod_cons
                     AND gtt.val_esta_clie IN ('01', '07'));
          -- si la campaña de fin es igual a la actual es xq es constante y paso pedido la campaña anterior
        END IF;

        -- Reversa el proceso CUP_PR_CARGA_CONSU_FACTU
        -- Si son nuevas NUEVAS (estado 01 en MAE)
        -- Borrar de CUP_CONSU_FACTU a esa consultora
        DELETE FROM cup_consu_factu c
         WHERE c.cod_pais = pscodigopais
           AND c.cod_prog = v_cod_prog(i)
           AND c.cod_cons = pscodigoconsultora
           AND EXISTS (SELECT NULL
                  FROM gtt_cup_consu_nueva gtt
                 WHERE gtt.cod_clie = c.cod_cons
                   AND gtt.val_esta_clie IN ('01'));
        ----------------------------------------------------------
        -- Por constraints, SI ES NUEVA, borro el cup_consu_cupon
        -- y el cup_detal_cupon_perio
        ----------------------------------------------------------
        DELETE FROM cup_consu_cupon c
         WHERE c.cod_pais = pscodigopais
           AND c.cod_prog = v_cod_prog(i)
           AND c.cod_cons = pscodigoconsultora
           AND EXISTS (SELECT NULL
                  FROM gtt_cup_consu_nueva gtt
                 WHERE gtt.cod_clie = c.cod_cons
                   AND gtt.val_esta_clie IN ('01'));

        DELETE FROM cup_detal_cupon_perio c
         WHERE c.cod_pais = pscodigopais
           AND c.cod_prog = v_cod_prog(i)
           AND c.cod_cons = pscodigoconsultora
           AND EXISTS (SELECT NULL
                  FROM gtt_cup_consu_nueva gtt
                 WHERE gtt.cod_clie = c.cod_cons
                   AND gtt.val_esta_clie IN ('01'));
        ----------------------------------------------------------
        -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
        -- Si son nuevas NUEVAS (estado 01 en MAE)
        -- Borrar de CUP_CONSU_NIVEL y CUP_CONSU_NUEVAS a esa consultora

        -- ADD CONV_CONS_FK
        DELETE FROM cup_consu_nivel c
               WHERE     c.cod_pais = pscodigopais
                     AND c.cod_prog = v_cod_prog (i)
                     AND c.cod_cons = pscodigoconsultora
                     AND EXISTS
                            (SELECT NULL
                               FROM gtt_cup_consu_nueva gtt
                              WHERE gtt.cod_clie = c.cod_cons
                                    AND gtt.val_esta_clie IN
                                           ('01', '07'));
        -- ADD
        --ADD 2 HIMO_CONU_FK
                             DELETE FROM LOV_HISTO_MOVIM lv
                                         WHERE     lv.cod_pais = pscodigopais
                                               AND lv.cod_prog = v_cod_prog (i)
                                               AND lv.cod_cons = pscodigoconsultora
                                               AND EXISTS
                                                      (SELECT NULL
                                                         FROM gtt_cup_consu_nueva gtt
                                                        WHERE gtt.cod_clie = LV.cod_cons
                                                              AND gtt.val_esta_clie IN
                                                                     ('01', '07'));
        --ADD 2

        DELETE cup_consu_nueva c
         WHERE c.cod_pais = pscodigopais
           AND c.cod_prog = v_cod_prog(i)
           AND c.cod_cons = pscodigoconsultora
           AND EXISTS (SELECT NULL
                  FROM gtt_cup_consu_nueva gtt
                 WHERE gtt.cod_clie = c.cod_cons
                   AND gtt.val_esta_clie IN ('01'));

        -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
        -- Si son nuevas REINGRESOS (estado 07 en MAE)
        -- Modificar el registro en CUP_CONSU_NUEVAS para esa consultora
        UPDATE cup_consu_nueva n
           SET /*n.camp_ini_ccc = NVL(n.camp_ini_ccc_hist,
               (SELECT MIN(ca.cod_peri)
                  FROM ped_histo_solic_conso_cabec ca
                 WHERE ca.cod_clie = n.cod_cons
                   AND ca.ind_proc_gp2 = '1'
                   AND ca.ind_ocs_proc = '1')),*/ n.camp_ini_ccc = nvl(n.camp_ini_ccc_hist,
                                                                       gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MIN(ca.perd_oid_peri),
                                                                                                                          lnoidperiodo)
                                                                                                                 FROM ped_solic_cabec ca,
                                                                                                                      mae_clien       d
                                                                                                                WHERE ca.clie_oid_clie =
                                                                                                                      d.oid_clie
                                                                                                                  AND ca.pais_oid_pais =
                                                                                                                      d.pais_oid_pais
                                                                                                                  AND d.cod_clie =
                                                                                                                      n.cod_cons
                                                                                                                  AND ca.tspa_oid_tipo_soli_pais =
                                                                                                                      lnoidtiposolisoc
                                                                                                                  AND ca.ind_oc = '1'
                                                                                                                  AND ca.fec_fact IS NOT NULL))),

               /*n.camp_fin_ccc = NVL(n.camp_fin_ccc_hist,
               (SELECT MAX(ca.cod_peri)
                  FROM ped_histo_solic_conso_cabec ca
                 WHERE ca.cod_clie = n.cod_cons
                   AND ca.ind_ocs_proc = '1'
                   AND ca.ind_proc_gp2 = '1')),*/

               n.camp_fin_ccc = nvl(n.camp_fin_ccc_hist,
                                    gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MAX(ca.perd_oid_peri),
                                                                                       lnoidperiodo)
                                                                              FROM ped_solic_cabec ca,
                                                                                   mae_clien       d
                                                                             WHERE ca.clie_oid_clie =
                                                                                   d.oid_clie
                                                                               AND ca.pais_oid_pais =
                                                                                   d.pais_oid_pais
                                                                               AND d.cod_clie =
                                                                                   n.cod_cons
                                                                               AND ca.tspa_oid_tipo_soli_pais =
                                                                                   lnoidtiposolisoc
                                                                               AND ca.ind_oc = '1'
                                                                               AND ca.fec_fact IS NOT NULL))),
               /*n.cod_ult_nivel = NVL(n.cod_ult_nivel_hist,
               CUP_FN_DEVUE_NIVEL_NOCON((SELECT MIN(ca.cod_peri)
                                           FROM ped_histo_solic_conso_cabec ca
                                          WHERE ca.cod_clie = n.cod_cons
                                            AND ca.ind_ocs_proc = '1'
                                            AND ca.ind_proc_gp2 = '1'),
                                        (SELECT MAX(ca.cod_peri)
                                           FROM ped_histo_solic_conso_cabec ca
                                          WHERE ca.cod_clie = n.cod_cons
                                            AND ca.ind_proc_gp2 = '1'
                                            AND ca.ind_ocs_proc = '1'))),*/
               n.cod_ult_nivel = nvl(n.cod_ult_nivel_hist,
                                     cup_fn_devue_nivel_nocon_cierr(gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MIN(ca.perd_oid_peri),
                                                                                                                 lnoidperiodo)
                                                                                                        FROM ped_solic_cabec ca,
                                                                                                             mae_clien       d
                                                                                                       WHERE ca.clie_oid_clie =
                                                                                                             d.oid_clie
                                                                                                         AND ca.pais_oid_pais =
                                                                                                             d.pais_oid_pais
                                                                                                         AND d.cod_clie =
                                                                                                             n.cod_cons
                                                                                                         AND ca.tspa_oid_tipo_soli_pais =
                                                                                                             lnoidtiposolisoc
                                                                                                         AND ca.fec_fact IS NOT NULL
                                                                                                         AND ca.ind_oc = '1')),

                                                              gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT nvl(MAX(ca.perd_oid_peri),
                                                                                                                 lnoidperiodo)
                                                                                                        FROM ped_solic_cabec ca,
                                                                                                             mae_clien       d
                                                                                                       WHERE ca.clie_oid_clie =
                                                                                                             d.oid_clie
                                                                                                         AND ca.pais_oid_pais =
                                                                                                             d.pais_oid_pais
                                                                                                         AND d.cod_clie =
                                                                                                             n.cod_cons
                                                                                                         AND ca.tspa_oid_tipo_soli_pais =
                                                                                                             lnoidtiposolisoc
                                                                                                         AND ca.fec_fact IS NOT NULL
                                                                                                         AND ca.ind_oc = '1')))),

               /*n.est_proc = (decode((SELECT COUNT(1)
                FROM ped_histo_solic_conso_cabec ca
               WHERE ca.cod_clie = n.cod_cons
                 AND ca.cod_peri = GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo,-1)
                 AND ca.ind_proc_gp2 = '1'
                 AND ca.ind_ocs_proc = '1'),0,0,1)),*/
               n.est_proc = decode((SELECT COUNT(1)
                                     FROM ped_solic_cabec ca,
                                          mae_clien       d
                                    WHERE ca.clie_oid_clie = d.oid_clie
                                      AND ca.pais_oid_pais = d.pais_oid_pais
                                      AND d.cod_clie = n.cod_cons
                                      AND gen_pkg_gener.gen_fn_devuelve_des_perio(ca.perd_oid_peri) =
                                          gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                 -1)
                                      AND ca.tspa_oid_tipo_soli_pais =
                                          lnoidtiposolisoc
                                      AND ca.fec_fact IS NOT NULL
                                      AND ca.ind_oc = '1'),
                                   0,
                                   '0',
                                   '1'),

               n.usu_modi = psusuario,
               n.fec_modi = SYSDATE
         WHERE n.cod_pais = pscodigopais
           AND n.cod_prog = v_cod_prog(i)
           AND n.cod_cons = pscodigoconsultora
           AND EXISTS (SELECT NULL
                  FROM gtt_cup_consu_nueva gtt
                 WHERE gtt.cod_clie = n.cod_cons
                   AND gtt.val_esta_clie IN ('07'));
      END LOOP;
    END LOOP;
    CLOSE curprogramas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ANULA_PEDID_ANFAC_MULTI: ' ||
                              ls_sqlerrm);
  END cup_pr_anula_pedid_anfac_multi;

  /**************************************************************************
  Descripcion        :CUP_PR_ACTUA_CONSU_FACTU_MULTI
                      Actualiza las consultoras nuevas que pasaron pedido y facturaron
                      en el periodo en la tabla de facturacion de consultoras AL FINAL
                      DE CAMPAÑA  que soporta la
            configuracion de multiples programas
  Fecha Creacion     : 05/02/2010
  Parametros Entrada:
           psCodigoPais:     Codigo Pais
           psCodigoPeriodo:  Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_actua_consu_factu_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  ) IS
    CURSOR curprogramas IS
      SELECT cod_prog,
             ind_const,
             ind_cons_prem,
             ind_cupo,
             num_vig,
             ind_acti_love,
             nvl(ind_rega_pedi, '0') ind_rega_pedi -- cambio RCR PER-SiCC-2012-0362
        FROM cup_prog_nueva_cupon
       WHERE cod_pais = pscodigopais
       AND   ind_esta in ( 'A', 'V');

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_cons_prem IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem%TYPE;
    TYPE t_ind_cupo IS TABLE OF cup_prog_nueva_cupon.ind_cupo%TYPE;
    TYPE t_num_vig IS TABLE OF cup_prog_nueva_cupon.num_vig%TYPE;
    TYPE t_ind_acti_love IS TABLE OF cup_prog_nueva_cupon.ind_acti_love%TYPE;
    TYPE t_ind_rega_pedi IS TABLE OF cup_prog_nueva_cupon.ind_rega_pedi%TYPE; -- cambio RCR PER-SiCC-2012-0362

    v_cod_prog      t_cod_prog;
    v_ind_const     t_ind_const;
    v_ind_cons_prem t_ind_cons_prem;
    v_ind_cupo      t_ind_cupo;
    v_num_vig       t_num_vig;
    v_ind_acti_love t_ind_acti_love;
    v_ind_rega_pedi t_ind_rega_pedi; -- cambio RCR PER-SiCC-2012-0362

    v_row_count NUMBER := 0;
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;

  BEGIN
    OPEN curprogramas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curprogramas BULK COLLECT
        INTO v_cod_prog,
             v_ind_const,
             v_ind_cons_prem,
             v_ind_cupo,
             v_num_vig,
             v_ind_acti_love,
             v_ind_rega_pedi LIMIT rows;
      EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
      v_row_count := curprogramas%ROWCOUNT;

      -- Recorro todos los programas creados para Nuevas
      FOR i IN v_cod_prog.first .. v_cod_prog.last
      LOOP

        -- Inicio cambio RCR PER-SiCC-2012-0362
        /*IF(v_ind_rega_pedi(i) = '1') THEN
          INSERT INTO nvs_consu_regal_pedid
              (cons_cod_pais,
               cons_cod_prog,
               cod_peri_desp,
               cons_cod_cons,
               soca_oid_soli_cabe,
               est_rega,
               usu_crea,
               fec_crea)
          SELECT
               pscodigopais,
               a.cod_prog,
               pscodigoperiodo,
               a.COD_CONS,
               NULL,
               '03',
               psusuario,
               SYSDATE
            FROM cup_consu_factu a
           WHERE a.cod_pais = pscodigopais
             AND a.cod_prog = v_cod_prog(i)
             AND a.ind_cons = '0'
                --Que no haya hecho pedido
             AND EXISTS (SELECT NULL
                    FROM cup_consu_nueva ccn
                   WHERE ccn.cod_pais = a.cod_pais
                     AND ccn.cod_prog = a.cod_prog
                     AND ccn.cod_cons = a.cod_cons
                     AND ccn.camp_fin_ccc < pscodigoperiodo)
             AND NOT EXISTS (SELECT NULL
                    FROM nvs_consu_regal_pedid reg
                   WHERE reg.cons_cod_pais = a.cod_pais
                     AND reg.cons_cod_prog = a.cod_prog
                     AND reg.cons_cod_cons = a.cod_cons
                     AND reg.cod_peri_desp = pscodigoperiodo
                     AND reg.est_rega = '03');

        END IF;*/
        -- Fin cambio RCR PER-SiCC-2012-0362

        UPDATE cup_consu_factu a
           SET a.ind_cons      = '1',
               a.usu_modi      = psusuario,
               a.fec_modi      = SYSDATE,
               a.cod_peri_noco = pscodigoperiodo
         WHERE a.cod_pais = pscodigopais
           AND a.cod_prog = v_cod_prog(i)
           AND a.ind_cons = '0'
              --Que no haya hecho pedido
           AND EXISTS
         (SELECT NULL
                  FROM cup_consu_nueva ccn
                 WHERE ccn.cod_pais = a.cod_pais
                   AND ccn.cod_prog = a.cod_prog
                   AND ccn.cod_cons = a.cod_cons
                   AND ccn.camp_fin_ccc < pscodigoperiodo);
      END LOOP;

    END LOOP;
    CLOSE curprogramas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ACTUA_CONSU_FACTU_MULTI: ' ||
                              ls_sqlerrm);
  END cup_pr_actua_consu_factu_multi;

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_CIERR_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas al cierre de la campaña para los pedidos
                      anulados no gestionados que soporta la
            configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_cierr_multi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2
  ) IS
  
    CURSOR cursorconsultoras
    (
      vp_oidpais       NUMBER,
      vp_oidperiodo    NUMBER,
      vp_oidperiodosig NUMBER
    ) IS
      SELECT DISTINCT ca.cod_clie
        FROM int_solic_conso_cabec ca
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_clie NOT IN
             (SELECT a.cod_clie
                FROM int_solic_conso_cabec a,
                     ped_solic_cabec       b
               WHERE a.soca_oid_soli_cabe_refe = b.oid_soli_cabe
                 AND b.pais_oid_pais = vp_oidpais
                 AND b.perd_oid_peri IN (vp_oidperiodo, vp_oidperiodosig)
                 AND b.grpr_oid_grup_proc = 5);
    /*SELECT DISTINCT
           d.cod_clie
      FROM ped_solic_cabec a,
           ped_tipo_solic b,
           ped_tipo_solic_pais c,
           mae_clien d
     WHERE a.pais_oid_pais = vp_oidPais
       AND a.perd_oid_peri = vp_oidPeriodo
       AND a.fec_fact IS NULL
       AND a.grpr_oid_grup_proc <> 5
       AND a.ind_ts_no_conso = 1
       AND a.ind_oc = 1
       AND a.ind_pedi_prue = 0
       AND b.ind_devo = 0
       AND b.ind_anul = 0
       AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
       AND a.pais_oid_pais = c.pais_oid_pais
       AND b.oid_tipo_soli = c.tsol_oid_tipo_soli
       AND a.clie_oid_clie = d.oid_clie
       AND a.pais_oid_pais = d.pais_oid_pais;
    /*select distinct ca.cod_clie
     from int_solic_conso_cabec ca
    where ca.cod_peri = psCodigoPeriodo
      and ca.cod_pais = psCodigoPais
      and ca.ind_proc_gp2 != '1'
      and not exists(select null from int_solic_conso_cabec cc
             where cc.cod_pais = ca.cod_pais
               and cc.cod_peri = ca.cod_peri
               and cc.cod_clie = ca.cod_clie
               and cc.ind_ocs_proc = '1'
               and cc.ind_proc_gp2 = '1');*/

    TYPE t_cod_cons IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;

    v_cod_cons t_cod_cons;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    v_row_count NUMBER := 0;

    vn_oidpais       NUMBER;
    vn_oidperiodo    NUMBER;
    vn_oidperiodosig NUMBER;
    vncampa   NUMBER;

  BEGIN

    vn_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    vn_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
    vncampa  := 0;
    --obtiene el siguiente periodo
    vn_oidperiodosig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                     pscodigoperiodo,
                                                                                                     1));
    SELECT to_number(val_para)
    INTO vncampa
    FROM nvs_param_gener
    WHERE cod_para = '02'
    AND pais_oid_pais =
           gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
                                                                                                        
    -- cargo las consultoras nuevas a una tabla temporal
    INSERT INTO gtt_cup_consu_nueva
      (cod_clie,
       val_esta_clie)
      (SELECT mc.cod_clie,
              est.cod_esta_clie
         FROM mae_clien_datos_adici ad,
              mae_estat_clien       est,
              mae_clien             mc
        WHERE ad.clie_oid_clie = mc.oid_clie
          AND ad.esta_oid_esta_clie = est.oid_esta_clie
          AND est.cod_esta_clie IN ('01', '07')
          AND ad.ind_acti = 1
          AND EXISTS
        (SELECT NULL
                 FROM mae_clien_tipo_subti mcts,
                      mae_subti_clien      msc,
                      mae_tipo_clien       mtc
                WHERE mcts.clie_oid_clie = ad.clie_oid_clie
                  AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                  AND mtc.cod_tipo_clie = '02'
                  AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                  AND msc.cod_subt_clie = '04'));

    OPEN cursorconsultoras(vn_oidpais, vn_oidperiodo, vn_oidperiodosig);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cursorconsultoras BULK COLLECT
        INTO v_cod_cons LIMIT rows;
      EXIT WHEN v_row_count = cursorconsultoras%ROWCOUNT;
      v_row_count := cursorconsultoras%ROWCOUNT;

      IF v_cod_cons.count > 0 THEN
        FOR i IN v_cod_cons.first .. v_cod_cons.last
        LOOP
          cup_pr_anula_pedid_anfac_multi(pscodigopais,
                                         pscodigoperiodo,
                                         v_cod_cons(i),
                                         psusuario);
        END LOOP;
      END IF;

    END LOOP;
    CLOSE cursorconsultoras;

    cup_pr_actua_consu_factu_multi(pscodigopais,
                                   pscodigoperiodo,
                                   psusuario);
                                   
    update  cup_prog_nueva_cupon
    set IND_ESTA ='C'
    where ind_esta in ( 'A', 'V')
    AND CAM_FIN <  GEN_FN_CALCU_PERIO (pscodigoperiodo, -vncampa);

    update cup_prog_nueva_cupon
    set IND_ESTA ='V'
    where ind_esta in ( 'A', 'V')
    AND CAM_FIN >=   GEN_FN_CALCU_PERIO (pscodigoperiodo, -vncampa)
    AND CAM_FIN <= pscodigoperiodo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ANULA_PEDID_CIERR_MULTI: ' ||
                              ls_sqlerrm);
  END cup_pr_anula_pedid_cierr_multi;

  /**************************************************************************
  Descripcion       : CUP_PR_ANULA_PEDID_MULTI
                      Procedimiento para revertir los cambios en Programa
                      Nuevas cuando se anula un pedido que soporta la
                      configuracion de multiples programas
  Fecha Creacion    : 05/02/2010
  Parametros Entrada:
      psCodigoPais       : Codigo de pais
      psCodigoPeriodo    : Codigo de periodo
      psCodigoConsultora : Codigo de cliente
      psUsuario          : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_anula_pedid_multi
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  ) IS
    CURSOR curprogramas IS
      SELECT cod_prog,
             ind_const,
             ind_cons_prem,
             ind_cupo,
             num_vig,
             ind_acti_love
        FROM cup_prog_nueva_cupon
       WHERE cod_pais = pscodigopais;

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_cons_prem IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem%TYPE;
    TYPE t_ind_cupo IS TABLE OF cup_prog_nueva_cupon.ind_cupo%TYPE;
    TYPE t_num_vig IS TABLE OF cup_prog_nueva_cupon.num_vig%TYPE;
    TYPE t_ind_acti_love IS TABLE OF cup_prog_nueva_cupon.ind_acti_love%TYPE;

    v_cod_prog      t_cod_prog;
    v_ind_const     t_ind_const;
    v_ind_cons_prem t_ind_cons_prem;
    v_ind_cupo      t_ind_cupo;
    v_num_vig       t_num_vig;
    v_ind_acti_love t_ind_acti_love;

    v_row_count            NUMBER := 0;
    rows                   NATURAL := 1000; -- Number of rows to process at a time
    i                      BINARY_INTEGER := 0;
    ls_indicador_facturado NUMBER;

  BEGIN

    SELECT COUNT(1)
      INTO ls_indicador_facturado
      FROM int_solic_conso_cabec cb
     WHERE cb.cod_pais = pscodigopais
       AND cb.cod_clie = pscodigoconsultora
       AND cb.cod_peri = pscodigoperiodo
       AND cb.ind_ocs_proc = '1'
       AND cb.ind_proc_gp2 = '1';

    -- cargo las consultoras nuevas a una tabla temporal
    INSERT INTO gtt_cup_consu_nueva
      (cod_clie,
       val_esta_clie)
      (SELECT mc.cod_clie,
              est.cod_esta_clie
         FROM mae_clien_datos_adici ad,
              mae_estat_clien       est,
              mae_clien             mc
        WHERE ad.clie_oid_clie = mc.oid_clie
          AND ad.esta_oid_esta_clie = est.oid_esta_clie
          AND est.cod_esta_clie IN ('01', '07')
          AND ad.ind_acti = 1
          AND EXISTS
        (SELECT NULL
                 FROM mae_clien_tipo_subti mcts,
                      mae_subti_clien      msc,
                      mae_tipo_clien       mtc
                WHERE mcts.clie_oid_clie = ad.clie_oid_clie
                  AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                  AND mtc.cod_tipo_clie = '02'
                  AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                  AND msc.cod_subt_clie = '04'));

    -- SOLO REVERSARA EL PROGRAMA SI LA CONSULTORA HA FACTURADO EN EL PERIODO
    IF ls_indicador_facturado <> 0 THEN
      OPEN curprogramas;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curprogramas BULK COLLECT
          INTO v_cod_prog,
               v_ind_const,
               v_ind_cons_prem,
               v_ind_cupo,
               v_num_vig,
               v_ind_acti_love LIMIT rows;
        EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
        v_row_count := curprogramas%ROWCOUNT;
        -- Recorro todos los programas creados para Nuevas
        FOR i IN v_cod_prog.first .. v_cod_prog.last
        LOOP
          IF v_ind_cupo(i) = '0' AND v_ind_const(i) = '0' THEN
            -- Reversa el proceso CUP_PR_CIERR_CARGA_NOCON_NIVEL
            -- Borra todos los niveles creados si es NUEVA
            DELETE FROM cup_consu_nivel c
             WHERE c.cod_pais = pscodigopais
               AND c.cod_prog = v_cod_prog(i)
               AND c.cod_cons = pscodigoconsultora
               AND EXISTS
             (SELECT NULL
                      FROM gtt_cup_consu_nueva gtt
                     WHERE gtt.cod_clie = c.cod_cons
                       AND gtt.val_esta_clie IN ('01', '07'));
          END IF;
          IF v_ind_cupo(i) = '0' AND v_ind_const(i) = '1' THEN
            -- Reversa el proceso CUP_PR_CIERR_CARGA_CONSU_NIVEL
            -- Borra solo el que se creo para el siguiente nivel
            DELETE FROM cup_consu_nivel
             WHERE cod_pais = pscodigopais
               AND cod_prog = v_cod_prog(i)
               AND cod_cons = pscodigoconsultora
               AND cam_inic_vig =
                   gen_fn_dev_nsgte_campa(pscodigoperiodo, 1);
          END IF;
          -- Reversa el proceso CUP_PR_CIERR_ACTUA_CUPON_UTILI
          UPDATE cup_consu_cupon cu
             SET cu.ind_utili = 0,
                 cu.cam_utili = NULL,
                 cu.usu_digi  = psusuario,
                 cu.fec_modi  = SYSDATE
           WHERE cu.cod_pais = pscodigopais
             AND cu.cod_prog = v_cod_prog(i)
             AND cu.cod_cons = pscodigoconsultora
             AND cu.cam_utili = pscodigoperiodo;

          -- Reversa el proceso CUP_PR_CIERR_ACTUA_DETAL_CUPON
          UPDATE cup_detal_cupon_perio pe
             SET pe.val_unida_pedid = 0,
                 pe.ind_factu       = 0,
                 pe.usu_modi        = psusuario,
                 pe.fec_modi        = SYSDATE
           WHERE pe.cod_pais = pscodigopais
             AND pe.cod_prog = v_cod_prog(i)
             AND pe.cod_cons = pscodigoconsultora
             AND pe.cod_peri = pscodigoperiodo;

          IF v_ind_cupo(i) = '1' AND v_ind_const(i) = '0' THEN
            -- Reversa el proceso CUP_PR_CARGA_NOCON_NIVEL_PRIME
            -- Este caso les crea nivel desde 1er pedido
            -- SI ES NUEVA - Borrar todos los niveles
            DELETE FROM cup_consu_nivel c
             WHERE c.cod_pais = pscodigopais
               AND c.cod_prog = v_cod_prog(i)
               AND c.cod_cons = pscodigoconsultora
               AND EXISTS
             (SELECT NULL
                      FROM gtt_cup_consu_nueva gtt
                     WHERE gtt.cod_clie = c.cod_cons
                       AND gtt.val_esta_clie IN ('01', '07'));
          END IF;
          IF v_ind_cupo(i) = '1' AND v_ind_const(i) = '1' THEN
            -- Reversa el proceso CUP_PR_CARGA_CONSU_NIVEL_PRIME
            -- Este caso les crea nivel desde 1er pedido
            -- Borrar solo el que se creo para el actual nivel
            DELETE FROM cup_consu_nivel
             WHERE cod_pais = pscodigopais
               AND cod_prog = v_cod_prog(i)
               AND cod_cons = pscodigoconsultora
               AND cam_inic_vig = pscodigoperiodo;
          END IF;
          IF v_ind_const(i) = '0' THEN
            -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_NOCON
            -- Actualiza las consultoras que no son nuevas
            UPDATE cup_consu_nueva ant
               SET ant.camp_fin_ccc  = nvl(ant.camp_fin_ccc_hist,
                                           (SELECT MAX(ca.cod_peri)
                                              FROM ped_histo_solic_conso_cabec ca
                                             WHERE ca.cod_clie = ant.cod_cons
                                               AND ca.ind_proc_gp2 = '1'
                                               AND ca.ind_ocs_proc = '1')),
                   ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                           cup_fn_devue_nivel_nocon(ant.camp_ini_ccc,
                                                                    (SELECT MAX(ca.cod_peri)
                                                                       FROM ped_histo_solic_conso_cabec ca
                                                                      WHERE ca.cod_clie =
                                                                            ant.cod_cons
                                                                        AND ca.ind_proc_gp2 = '1'
                                                                        AND ca.ind_ocs_proc = '1'))),
                   ant.est_proc      = decode((SELECT COUNT(1)
                                                FROM ped_histo_solic_conso_cabec ca
                                               WHERE ca.cod_clie =
                                                     ant.cod_cons
                                                 AND ca.cod_peri =
                                                     gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                            -1)
                                                 AND ca.ind_proc_gp2 = '1'
                                                 AND ca.ind_ocs_proc = '1'),
                                              0,
                                              '0',
                                              '1'),
                   ant.usu_modi      = psusuario,
                   ant.fec_modi      = SYSDATE
             WHERE ant.cod_pais = pscodigopais
               AND ant.cod_prog = v_cod_prog(i)
               AND ant.cod_cons = pscodigoconsultora
               AND EXISTS (SELECT NULL
                      FROM cup_consu_nueva nu
                     WHERE nu.cod_cons = ant.cod_cons
                       AND nu.camp_ini_ccc <> pscodigoperiodo)
               AND NOT EXISTS
             (SELECT NULL
                      FROM gtt_cup_consu_nueva gtt
                     WHERE gtt.cod_clie = ant.cod_cons
                       AND gtt.val_esta_clie IN ('01', '07'));
          END IF;
          IF v_ind_const(i) = '1' THEN
            -- Reversa el proceso CUP_PR_CARGA_CONSU_ANTIG_CONST
            -- Actualiza las consultoras que no son nuevas
            UPDATE cup_consu_nueva ant
               SET ant.camp_fin_ccc  = nvl(ant.camp_fin_ccc_hist,
                                           gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                                                  -1)),
                   ant.cod_ult_nivel = nvl(ant.cod_ult_nivel_hist,
                                           TRIM(to_char(ant.cod_ult_nivel - 1,
                                                        '09'))),
                   ant.est_proc      = '1',
                   ant.usu_modi      = psusuario,
                   ant.fec_modi      = SYSDATE
             WHERE ant.cod_pais = pscodigopais
               AND ant.cod_prog = v_cod_prog(i)
               AND ant.cod_cons = pscodigoconsultora
               AND ant.camp_fin_ccc = pscodigoperiodo --Periodo a anular
               AND NOT EXISTS
             (SELECT NULL
                      FROM gtt_cup_consu_nueva gtt
                     WHERE gtt.cod_clie = ant.cod_cons
                       AND gtt.val_esta_clie IN ('01', '07'));
            -- si la campaña de fin es igual a la actual es xq es constante y paso pedido la campaña anterior
          END IF;
          -- Reversa el proceso CUP_PR_CARGA_CONSU_FACTU
          -- Si son nuevas NUEVAS (estado 01 en MAE)
          --  Borrar de CUP_CONSU_FACTU a esa consultora
          DELETE FROM cup_consu_factu c
           WHERE c.cod_pais = pscodigopais
             AND c.cod_prog = v_cod_prog(i)
             AND c.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = c.cod_cons
                     AND gtt.val_esta_clie IN ('01'));
          ----------------------------------------------------------
          -- Por constraints, SI ES NUEVA, borro el cup_consu_cupon
          -- y el cup_detal_cupon_perio
          ----------------------------------------------------------
          DELETE FROM cup_consu_cupon c
           WHERE c.cod_pais = pscodigopais
             AND c.cod_prog = v_cod_prog(i)
             AND c.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = c.cod_cons
                     AND gtt.val_esta_clie IN ('01'));

          DELETE FROM cup_detal_cupon_perio c
           WHERE c.cod_pais = pscodigopais
             AND c.cod_prog = v_cod_prog(i)
             AND c.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = c.cod_cons
                     AND gtt.val_esta_clie IN ('01'));
          ----------------------------------------------------------
          -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
          -- Si son nuevas NUEVAS (estado 01 en MAE)
          --  Borrar de CUP_CONSU_NUEVAS a esa consultora
          DELETE cup_consu_nueva c
           WHERE c.cod_pais = pscodigopais
             AND c.cod_prog = v_cod_prog(i)
             AND c.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = c.cod_cons
                     AND gtt.val_esta_clie IN ('01'));
          -- Reversa el proceso CUP_PR_CARGA_CONSU_NUEVA
          -- Si son nuevas REINGRESOS (estado 07 en MAE)
          --  Modificar el registro en CUP_CONSU_NUEVAS para esa consultora
          UPDATE cup_consu_nueva n
             SET n.camp_ini_ccc  = nvl(n.camp_ini_ccc_hist,
                                       (SELECT MIN(ca.cod_peri)
                                          FROM ped_histo_solic_conso_cabec ca
                                         WHERE ca.cod_clie = n.cod_cons
                                           AND ca.ind_proc_gp2 = '1'
                                           AND ca.ind_ocs_proc = '1')),
                 n.camp_fin_ccc  = nvl(n.camp_fin_ccc_hist,
                                       (SELECT MAX(ca.cod_peri)
                                          FROM ped_histo_solic_conso_cabec ca
                                         WHERE ca.cod_clie = n.cod_cons
                                           AND ca.ind_ocs_proc = '1'
                                           AND ca.ind_proc_gp2 = '1')),
                 n.cod_ult_nivel = nvl(n.cod_ult_nivel_hist,
                                       cup_fn_devue_nivel_nocon((SELECT MIN(ca.cod_peri)
                                                                  FROM ped_histo_solic_conso_cabec ca
                                                                 WHERE ca.cod_clie =
                                                                       n.cod_cons
                                                                   AND ca.ind_ocs_proc = '1'
                                                                   AND ca.ind_proc_gp2 = '1'),
                                                                (SELECT MAX(ca.cod_peri)
                                                                   FROM ped_histo_solic_conso_cabec ca
                                                                  WHERE ca.cod_clie =
                                                                        n.cod_cons
                                                                    AND ca.ind_proc_gp2 = '1'
                                                                    AND ca.ind_ocs_proc = '1'))),
                 n.est_proc     =
                 (decode((SELECT COUNT(1)
                           FROM ped_histo_solic_conso_cabec ca
                          WHERE ca.cod_clie = n.cod_cons
                            AND ca.cod_peri =
                                gen_fn_dev_nsgte_campa(pscodigoperiodo, -1)
                            AND ca.ind_proc_gp2 = '1'
                            AND ca.ind_ocs_proc = '1'),
                         0,
                         0,
                         1)),
                 n.usu_modi      = psusuario,
                 n.fec_modi      = SYSDATE
           WHERE n.cod_pais = pscodigopais
             AND n.cod_prog = v_cod_prog(i)
             AND n.cod_cons = pscodigoconsultora
             AND EXISTS (SELECT NULL
                    FROM gtt_cup_consu_nueva gtt
                   WHERE gtt.cod_clie = n.cod_cons
                     AND gtt.val_esta_clie IN ('07'));

        END LOOP;
      END LOOP;
      CLOSE curprogramas;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_ANULA_PEDID_MULTI: ' ||
                              ls_sqlerrm);
  END cup_pr_anula_pedid_multi;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NUEVA_MULTI
                    Registra o actualiza la informacion de una consultora nueva,
          con el programa que le corresponda segun su UA
  Fecha Creacion    : 09/02/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nueva_multi
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2
  ) IS
    -- Consultoras que NO estan en cup_consu_nueva y q son nuevas (pasan su priemr pedido)
    CURSOR curinsconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM int_solic_conso_cabec ca,
             -------------------------
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu
      --------------------------
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -----------------------------------
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
            ---------------
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
            ---------------
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona
            -----------------------------------
            -- no se toma en cuenta la fecha de solic para multiples recepciones 14Dic2007
         AND NOT EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva nu
               WHERE nu.cod_pais = ca.cod_pais
                 AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie)
         AND EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva gtt
               WHERE gtt.cod_clie = ca.cod_clie
                 AND gtt.val_esta_clie IN ('01', '07'))
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    -- Consultoras que SI estan en CUP_CONSU_NUEVA y son nuevas
    CURSOR curupdconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec ca,
             -------------------------
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu
      --------------------------
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -----------------------------------
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
            ---------------
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
            ---------------
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona
            -----------------------------------
            -- no se toma en cuenta la fecha de solic para multiples recepciones 14Dic2007
         AND EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva nu
               WHERE nu.cod_pais = ca.cod_pais
                 AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie)
         AND EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva gtt
               WHERE gtt.cod_clie = ca.cod_clie
                 AND gtt.val_esta_clie IN ('01', '07'))
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_cod_ult_nivel IS TABLE OF cup_consu_nueva.cod_ult_nivel%TYPE;
    TYPE t_cam_prime_pedid IS TABLE OF cup_consu_nueva.camp_ini_ccc%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF cup_consu_nueva.camp_fin_ccc%TYPE;
    TYPE t_est_proc IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_ind_const IS TABLE OF cup_consu_nueva.ind_const%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_ult_nivel   t_cod_ult_nivel;
    v_cam_prime_pedid t_cam_prime_pedid;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_ind_const       t_ind_const;
    v_est_proc        t_est_proc;
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

    OPEN curupdconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulnueva%ROWCOUNT;
      v_row_count := curupdconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva
           SET -- Se guardan historicos
               camp_ini_ccc_hist = camp_ini_ccc,
               camp_fin_ccc_hist  = camp_fin_ccc,
               cod_ult_nivel_hist = cod_ult_nivel,

               camp_ini_ccc  = v_cam_prime_pedid(j),
               camp_fin_ccc  = v_cam_ultim_pedid(j),
               cod_ult_nivel = v_cod_ult_nivel(j),
               est_proc      = v_est_proc(j),
               usu_modi      = v_usu_modi(j),
               fec_modi      = v_fec_modi(j)

         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulnueva;

    -- Inserta en SSE_CONSU_SESIO_EXPER
    OPEN curinsconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsconsulnueva%ROWCOUNT;
      v_row_count_ins := curinsconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO cup_consu_nueva
          (cod_pais,
           cod_prog,
           cod_cons,
           cod_ult_nivel,
           camp_ini_ccc,
           camp_fin_ccc,
           est_proc,
           ind_const,
           usu_digi,
           fec_digi,
           -- Se guardan historicos
           camp_ini_ccc_hist,
           camp_fin_ccc_hist,
           cod_ult_nivel_hist)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_ult_nivel(i),
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_est_proc(i),
           v_ind_const(i),
           v_usu_digi(i),
           v_fec_digi(i),
           -- Se guardan historicos
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_cod_ult_nivel(i));

    END LOOP;
    CLOSE curinsconsulnueva;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_carga_consu_nueva_multi;

  /**************************************************************************
  Descripcion       : CUP_PR_RESET_CONSU_REACT_STO
                      Resetea la informacion de cupones de una consultora reactivada
            ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_reset_consu_react_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    -- Consultoras reactivadas
    CURSOR cursorconsureacti IS
      SELECT ca.cod_clie AS cod_consu
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       nu,
             gtt_cup_consu_nueva   gtt,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND nu.cod_pais = ca.cod_pais
         AND nu.cod_cons = ca.cod_clie
         AND nu.cod_prog = pscodigoprograma
         AND gtt.cod_clie = ca.cod_clie
         AND gtt.val_esta_clie = '07'
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    TYPE t_cod_consu IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;

    v_cod_consu t_cod_consu;
    rows        NATURAL := 1000;
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN
    OPEN cursorconsureacti;
    LOOP

      FETCH cursorconsureacti BULK COLLECT
        INTO v_cod_consu LIMIT rows;
      EXIT WHEN v_row_count = cursorconsureacti%ROWCOUNT;
      v_row_count := cursorconsureacti%ROWCOUNT;

      FORALL j IN 1 .. v_cod_consu.count
        UPDATE cup_consu_cupon
           SET ind_utili = '0',
               cam_utili = NULL,
               usu_modi  = psusuario,
               fec_modi  = SYSDATE
         WHERE cod_pais = pscodigopais
           AND cod_prog = pscodigoprograma
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE cursorconsureacti;
  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_reset_consu_react_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NUEVA_STO
                    Registra o actualiza la informacion de una consultora nueva,
          con el programa que le corresponda segun su UA ejecutado
          desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nueva_sto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  ) IS
    -- Consultoras que NO estan en cup_consu_nueva y q son nuevas (pasan su priemr pedido)
    CURSOR curinsconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM int_solic_conso_cabec ca,
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu,
             gtt_cup_consu_nueva   gtt,
             sto_proce_docum_digit tmp,
             cup_prog_nueva_cupon  prg
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona
         AND prg.cod_prog = pscodigoprograma
         AND prg.cam_inic <= pscodigoperiodo
         AND prg.cam_fin >= pscodigoperiodo
            -- no se toma en cuenta la fecha de solic para multiples recepciones 14Dic2007
         AND NOT EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva      nu,
                     cup_prog_nueva_cupon prg1
               WHERE nu.cod_pais = ca.cod_pais
                    -- AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie
                 AND prg1.cod_prog = nu.cod_prog
                 AND prg1.cam_inic <= pscodigoperiodo
                 AND prg1.cam_fin >= pscodigoperiodo)
         AND gtt.cod_clie = ca.cod_clie
         AND gtt.val_esta_clie IN ('01', '07')
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    -- Consultoras que SI estan en CUP_CONSU_NUEVA y son nuevas
    CURSOR curupdconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec ca,
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu,
             gtt_cup_consu_nueva   gtt,
             sto_proce_docum_digit tmp,
             cup_prog_nueva_cupon  prg
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona
         AND prg.cod_prog = pscodigoprograma
         AND prg.cam_inic <= pscodigoperiodo
         AND prg.cam_fin >= pscodigoperiodo
         AND EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva nu
               WHERE nu.cod_pais = ca.cod_pais
                 AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie)
         AND gtt.cod_clie = ca.cod_clie
         AND gtt.val_esta_clie IN ('01', '07')
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_cod_ult_nivel IS TABLE OF cup_consu_nueva.cod_ult_nivel%TYPE;
    TYPE t_cam_prime_pedid IS TABLE OF cup_consu_nueva.camp_ini_ccc%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF cup_consu_nueva.camp_fin_ccc%TYPE;
    TYPE t_est_proc IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_ind_const IS TABLE OF cup_consu_nueva.ind_const%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_ult_nivel   t_cod_ult_nivel;
    v_cam_prime_pedid t_cam_prime_pedid;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_ind_const       t_ind_const;
    v_est_proc        t_est_proc;
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

    OPEN curupdconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulnueva%ROWCOUNT;
      v_row_count := curupdconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva
           SET -- Se guardan historicos
               camp_ini_ccc_hist = camp_ini_ccc,
               camp_fin_ccc_hist  = camp_fin_ccc,
               cod_ult_nivel_hist = cod_ult_nivel,
               camp_ini_ccc       = v_cam_prime_pedid(j),
               camp_fin_ccc       = v_cam_ultim_pedid(j),
               cod_ult_nivel      = v_cod_ult_nivel(j),
               est_proc           = v_est_proc(j),
               usu_modi           = v_usu_modi(j),
               fec_modi           = v_fec_modi(j)

         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulnueva;

    -- Inserta en SSE_CONSU_SESIO_EXPER
    OPEN curinsconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsconsulnueva%ROWCOUNT;
      v_row_count_ins := curinsconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO cup_consu_nueva
          (cod_pais,
           cod_prog,
           cod_cons,
           cod_ult_nivel,
           camp_ini_ccc,
           camp_fin_ccc,
           est_proc,
           ind_const,
           usu_digi,
           fec_digi,
           -- Se guardan historicos
           camp_ini_ccc_hist,
           camp_fin_ccc_hist,
           cod_ult_nivel_hist)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_ult_nivel(i),
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_est_proc(i),
           v_ind_const(i),
           v_usu_digi(i),
           v_fec_digi(i),
           -- Se guardan historicos
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_cod_ult_nivel(i));

    END LOOP;
    CLOSE curinsconsulnueva;

  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_carga_consu_nueva_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_HOMOL_CUPON_DETAL_STO
                      Homologa los cupones en base a la matriz de nuevas
            ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_homol_cupon_detal_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2,
    psoidalmacenel   VARCHAR2

  ) IS
    CURSOR curhomolcupo IS
      SELECT em.cod_venta,
             det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.tip_posic
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             cup_equiv_matr        em,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo
         AND ca.cod_pais = det.cod_pais
         AND ca.cod_peri = det.cod_peri
         AND ca.cod_clie = det.cod_clie
         AND ca.num_lote = det.num_lote
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -----------------------------------
         AND det.cod_vent >= 99900 --99950
            ------------------------
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_cons = det.cod_clie
            -------------------------
         AND em.cod_pais = cn.cod_pais
         AND em.cod_prog = cn.cod_prog
         AND em.cod_peri = det.cod_peri
         AND em.cod_cupon = det.cod_vent
            --------------------------
         AND cn.cod_prog = pscodigoprograma;

    TYPE t_cod_venta_homol IS TABLE OF cup_equiv_matr.cod_venta%TYPE;
    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;

    v_cod_venta_homol t_cod_venta_homol;
    v_cod_pais        t_cod_pais;
    v_cod_peri        t_cod_peri;
    v_cod_clie        t_cod_clie;
    v_num_lote        t_num_lote;
    v_cod_vent        t_cod_vent;
    v_tip_posic       t_tip_posic;
    lnexiste          NUMBER(2);
    v_almacen         VARCHAR2(10);

    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curhomolcupo;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curhomolcupo BULK COLLECT
        INTO v_cod_venta_homol,
             v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic LIMIT rows;

      EXIT WHEN v_row_count = curhomolcupo%ROWCOUNT;
      v_row_count := curhomolcupo%ROWCOUNT;

      -- Bulk bind of data in memory table...
      -- Mejora 15-03-2010
      FOR i IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        BEGIN
          SELECT COUNT(1)
            INTO lnexiste
            FROM lov_equiv_matr le
           WHERE le.cod_peri = v_cod_peri(i)
             AND le.cod_prog = pscodigoprograma
             AND le.cod_cupon = v_cod_vent(i);

          IF lnexiste > 0 THEN
            v_almacen := psoidalmacenel;
          ELSE
            v_almacen := psoidalmacen;
          END IF;

          UPDATE int_solic_conso_detal de
             SET de.cod_vent = v_cod_venta_homol(i),
                 de.sta_proc = 'B',
                 de.oid_alma = v_almacen ------psoidalmacen
           WHERE de.cod_pais = v_cod_pais(i)
             AND de.cod_peri = v_cod_peri(i)
             AND de.cod_clie = v_cod_clie(i)
             AND de.num_lote = v_num_lote(i)
             AND de.cod_vent = v_cod_vent(i)
             AND de.tip_posic = v_tip_posic(i);
        EXCEPTION
          WHEN dup_val_on_index THEN
            DELETE int_solic_conso_detal de
             WHERE de.cod_pais = v_cod_pais(i)
               AND de.cod_peri = v_cod_peri(i)
               AND de.cod_clie = v_cod_clie(i)
               AND de.num_lote = v_num_lote(i)
               AND de.cod_vent = v_cod_vent(i)
               AND de.tip_posic = v_tip_posic(i);
        END;
      END LOOP;
    END LOOP;
    CLOSE curhomolcupo;

  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_homol_cupon_detal_sto;

  /**************************************************************************
  Descripcion        :CUP_PR_CARGA_CONSU_FACTU_STO
                      Inserta inicialmente las consultoras nuevas que pasaron pedido
                      en el periodo en la tabla de facturacion de consultoras
            ejecutado desde la validacion de STO
  Fecha Creacion     : 14/04/2010
  Parametros Entrada:
           psCodigoPais: Codigo Pais
           psCodigoPeriodo: Codigo Periodo
           psCodigoPrograma: Codigo Programa
           psUsuario: Usuario
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_factu_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curinscupconsufactu IS
      SELECT conu.cod_pais,
             conu.cod_prog,
             conu.cod_cons,
             '0', -- Se inicializa en 0
             psusuario,
             SYSDATE
        FROM cup_consu_nueva       conu,
             int_solic_conso_cabec soca,
             sto_proce_docum_digit tmp
       WHERE conu.cod_pais = pscodigopais
         AND conu.cod_prog = pscodigoprograma
         AND conu.cod_ult_nivel <=
             (SELECT MAX(cod_nive)
                FROM cup_perio_nivel
               WHERE cod_pais = conu.cod_pais
                 AND cod_prog = conu.cod_prog
                 AND cod_peri = pscodigoperiodo)
            /*AND EXISTS (SELECT NULL
             FROM INT_SOLIC_CONSO_CABEC SOCA
            WHERE SOCA.COD_PAIS = CONU.COD_PAIS
              AND SOCA.COD_PERI = PSCODIGOPERIODO
              AND SOCA.IND_OCS_PROC = '0'
              AND SOCA.IND_PROC_GP2 = '0')*/
         AND soca.cod_pais = conu.cod_pais
         AND soca.cod_peri = pscodigoperiodo
         AND soca.cod_clie = conu.cod_cons
         AND soca.ind_ocs_proc = '0'
         AND soca.ind_proc_gp2 = '0'
            -- JOIN STO
         AND tmp.num_lote = soca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND soca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = soca.sec_nume_docu
            -----------------------------------
         AND NOT EXISTS (SELECT NULL
                FROM cup_consu_factu x
               WHERE x.cod_pais = conu.cod_pais
                 AND x.cod_prog = conu.cod_prog
                 AND x.cod_cons = conu.cod_cons);

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_cons IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_ind_cons IS TABLE OF cup_consu_nueva.ind_const%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais t_cod_pais;
    v_cod_prog t_cod_prog;
    v_cod_cons t_cod_cons;
    v_ind_cons t_ind_cons;
    v_usu_digi t_usu_digi;
    v_fec_digi t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinscupconsufactu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinscupconsufactu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_cons,
             v_ind_cons,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curinscupconsufactu%ROWCOUNT;
      v_row_count := curinscupconsufactu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

        INSERT INTO cup_consu_factu
          (cod_pais,
           cod_prog,
           cod_cons,
           ind_cons,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(j),
           v_cod_prog(j),
           v_cod_cons(j),
           v_ind_cons(j),
           v_usu_digi(j),
           v_fec_digi(j));

    END LOOP;
    CLOSE curinscupconsufactu;

    -- Actualizo las nuevas que vuelven a entrar al programa
    UPDATE cup_consu_factu y
       SET y.ind_cons = '0',
           y.usu_modi = psusuario,
           y.fec_modi = SYSDATE
     WHERE y.cod_pais = pscodigopais
       AND y.cod_prog = pscodigoprograma
       AND EXISTS (SELECT NULL
              FROM cup_consu_factu x
             WHERE x.cod_pais = y.cod_pais
               AND x.cod_prog = y.cod_prog
               AND x.cod_cons = y.cod_cons)
       AND EXISTS (SELECT NULL
              FROM gtt_cup_consu_nueva gtt
             WHERE gtt.cod_clie = y.cod_cons
               AND gtt.val_esta_clie IN ('01', '07'))
       AND EXISTS (SELECT NULL
              FROM int_solic_conso_cabec cc,
                   sto_proce_docum_digit tmp
             WHERE cc.cod_pais = y.cod_pais
               AND cc.cod_peri = pscodigoperiodo
               AND cc.cod_clie = y.cod_cons
                  -- JOIN STO
               AND tmp.num_lote = cc.num_lote
               AND tmp.cod_tipo_docu = pscodtipodocu
               AND tmp.num_proc = psnumeroproceso
               AND cc.cod_clie = pscodigocliente
               AND tmp.sec_nume_docu = cc.sec_nume_docu);

  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_consu_factu_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_ANTIG_NCSTO
                    Actualiza la informacion de una consultora no constante(print)
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_antig_ncsto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  ) IS
    -- Consultoras que SI estan en CUP_CONSU_NUEVA y son nuevas
    CURSOR curupdconsulnoconst IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             ca.cod_peri AS cam_fin,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       nu,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            /*AND EXISTS (SELECT NU.COD_CONS
             FROM CUP_CONSU_NUEVA NU
            WHERE NU.COD_PAIS = CA.COD_PAIS
              AND NU.COD_PROG = PSCODIGOPROGRAMA
              AND NU.COD_CONS = CA.COD_CLIE
              AND NU.CAMP_INI_CCC <> PSCODIGOPERIODO
              AND NU.IND_CONST = PSINDICADORCONSTANCIA)*/
         AND nu.cod_pais = ca.cod_pais
         AND nu.cod_prog = pscodigoprograma
         AND nu.cod_cons = ca.cod_clie
         AND nu.camp_ini_ccc <> ca.cod_peri
         AND nu.ind_const = psindicadorconstancia
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF cup_consu_nueva.camp_fin_ccc%TYPE;
    TYPE t_est_proc IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_est_proc        t_est_proc;
    v_usu_modi        t_usu_digi;
    v_fec_modi        t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

    v_camp_fin    cup_consu_nueva.camp_fin_ccc%TYPE;
    v_ult_nivel   cup_consu_nueva.cod_ult_nivel%TYPE;
    v_camp_actual bas_ctrl_fact.cod_peri%TYPE;

  BEGIN

    -- Obtiene la campaña actual del archivo de control
    SELECT cod_peri
      INTO v_camp_actual
      FROM bas_ctrl_fact
     WHERE sta_camp = '0'
       AND ind_camp_act = '1';

    OPEN curupdconsulnoconst;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulnoconst BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_ultim_pedid,
             v_est_proc,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulnoconst%ROWCOUNT;
      v_row_count := curupdconsulnoconst%ROWCOUNT;

      -- Bulk bind of data in memory table...
      -- CAMBIAR !!! -- CAMBIO REVISAR --
      --FORALL j IN 1..v_cod_pais.count
      FOR j IN 1 .. v_cod_pais.count
      LOOP
        SELECT ant.camp_fin_ccc,
               ant.cod_ult_nivel
          INTO v_camp_fin,
               v_ult_nivel
          FROM cup_consu_nueva ant
         WHERE ant.cod_pais = v_cod_pais(j)
           AND ant.cod_prog = v_cod_prog(j)
           AND ant.cod_cons = v_cod_consu(j);

        UPDATE cup_consu_nueva ant
           SET -- Se guardara valores historicos
               /*ant.camp_fin_ccc_hist = ant.camp_fin_ccc,
               ant.cod_ult_nivel_hist = ant.cod_ult_nivel,*/  ant.camp_fin_ccc = v_cam_ultim_pedid(j),
               ant.cod_ult_nivel = cup_fn_devue_nivel_nocon(ant.camp_ini_ccc,
                                                            v_cam_ultim_pedid(j)),
               ant.est_proc      = v_est_proc(j),
               ant.usu_modi      = v_usu_modi(j),
               ant.fec_modi      = v_fec_modi(j)
         WHERE ant.cod_pais = v_cod_pais(j)
           AND ant.cod_prog = v_cod_prog(j)
           AND ant.cod_cons = v_cod_consu(j);

        IF v_camp_fin != v_camp_actual THEN
          -- Se guardara valores historicos, validando que no se reemplazan
          -- x los valores actuales como consecuencia de las siguientes cargas
          -- de pedidos
          UPDATE cup_consu_nueva ant
             SET -- Se guardara valores historicos
                 ant.camp_fin_ccc_hist = v_camp_fin,
                 ant.cod_ult_nivel_hist = v_ult_nivel
           WHERE ant.cod_pais = v_cod_pais(j)
             AND ant.cod_prog = v_cod_prog(j)
             AND ant.cod_cons = v_cod_consu(j);
        END IF;

      END LOOP;
    END LOOP;
    CLOSE curupdconsulnoconst;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_consu_antig_ncsto;

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_NUEVA_STO
                    Despacho de Premios Consultoras Nuevas por Nivel
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_nueva_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  ) IS
    --PER-SiCC-2013-0141 - INI
    /*CURSOR curinsconsodetal IS*/
    CURSOR curinsconsodetal(pinumpediorig NUMBER) IS
    --PER-SiCC-2013-0141 - FIN
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'

            -- Inicio cambio RCR PER-SiCC-2012-0202
            -- Que no sea Regalo x Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
            --PER-SiCC-2013-0141 - INI
         AND (nvl(pr.tide_tipo_desp, '01') = '01' OR
             (pr.tide_tipo_desp = '04' AND
             pinumpediorig =
             cup_fn_devue_numro_pedid_orig(pscodigopais,
                                             gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                              pscodigoperiodo,
                                                                              -to_number(pr.cod_nivel) + 1),
                                             pscodigocliente) AND
             nvl(ca.ind_rece_web, '0') = 1))
            --PER-SiCC-2013-0141 - FIN
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta)
            -- add
          AND NOT EXISTS (SELECT NULL
                 FROM ped_solic_cabec psc,
                      ped_tipo_solic_pais tsp,
                      ped_tipo_solic ts,
                      mae_clien cli,
                      seg_pais sp,
                      cra_perio cp,
                      seg_perio_corpo spc
                  WHERE sp.cod_pais = pscodigopais
                  AND spc.cod_peri = pscodigoperiodo
                  AND psc.fec_fact IS NOT NULL
                  AND ((psc.ind_pedi_prue IS NULL) OR (psc.ind_pedi_prue = 0))
                  AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND cli.oid_clie = psc.clie_oid_clie
                  AND psc.pais_oid_pais = sp.oid_pais
                  AND psc.perd_oid_peri = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND ts.ind_devo  = 0
                  AND psc.modu_oid_modu <> 15
                  AND ts.ind_anul  = 0
                  AND psc.ind_ts_no_conso = 1
                   AND psc.ind_oc = 1
                   AND cli.cod_clie = ca.cod_clie)
                  --add
            ;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    --PER-SiCC-2013-0141 - INI
    lnnumpedi NUMBER := 0;
    --PER-SiCC-2013-0141 - FIN
  BEGIN
    --PER-SiCC-2013-0141 - INI
    BEGIN
      SELECT nvl(prog.num_pedi, 0)
        INTO lnnumpedi
        FROM cup_prog_nueva_cupon prog
       WHERE prog.cod_prog = pscodigoprograma;

    EXCEPTION
      WHEN no_data_found THEN
        lnnumpedi := 0;
    END;
    --PER-SiCC-2013-0141 - FIN

    OPEN curinsconsodetal(lnnumpedi);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetal%ROWCOUNT;
      v_row_count := curinsconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(j),
           v_cod_peri(j),
           v_cod_clie(j),
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);


    END LOOP;
    CLOSE curinsconsodetal;
  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_despa_premi_nueva_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_ANTIG_CTSTO
                    Actualiza la informacion de una consultora constante(paso 2doped, 3er...,etc)
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_antig_ctsto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  ) IS
    -- Consultoras que SI estan en CUP_CONSU_NUEVA y son nuevas
    CURSOR curupdconsulconst IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             ca.cod_peri AS cam_fin,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       nu,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            /*AND EXISTS (SELECT NU.COD_CONS
             FROM CUP_CONSU_NUEVA NU
            WHERE NU.COD_PAIS = CA.COD_PAIS
              AND NU.COD_PROG = PSCODIGOPROGRAMA
              AND NU.COD_CONS = CA.COD_CLIE
              AND NU.IND_CONST = PSINDICADORCONSTANCIA
              AND NU.EST_PROC = '1'
              AND NU.CAMP_FIN_CCC = GEN_FN_DEV_NSGTE_CAMPA(PSCODIGOPERIODO, -1)
              AND GEN_FN_DEV_NSGTE_CAMPA(NU.CAMP_INI_CCC, TO_NUMBER(NU.COD_ULT_NIVEL)) = PSCODIGOPERIODO)*/
         AND nu.cod_pais = ca.cod_pais
         AND nu.cod_prog = pscodigoprograma
         AND nu.cod_cons = ca.cod_clie
         AND nu.ind_const = psindicadorconstancia
         AND nu.est_proc = '1'
         AND nu.camp_fin_ccc = gen_fn_dev_nsgte_campa(ca.cod_peri, -1)

         AND gen_fn_dev_nsgte_campa(nu.camp_ini_ccc,
                                    to_number(nu.cod_ult_nivel)) =
             ca.cod_peri
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0';

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF cup_consu_nueva.camp_fin_ccc%TYPE;
    TYPE t_est_proc IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_est_proc        t_est_proc;
    v_usu_modi        t_usu_digi;
    v_fec_modi        t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curupdconsulconst;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulconst BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cam_ultim_pedid,
             v_est_proc,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulconst%ROWCOUNT;
      v_row_count := curupdconsulconst%ROWCOUNT;
      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva ant
           SET -- Se guardara valores historicos
               ant.camp_fin_ccc_hist = ant.camp_fin_ccc,
               ant.cod_ult_nivel_hist = ant.cod_ult_nivel,

               ant.camp_fin_ccc  = v_cam_ultim_pedid(j),
               ant.cod_ult_nivel = TRIM(to_char(ant.cod_ult_nivel + 1, '09')),
               ant.est_proc      = v_est_proc(j),
               ant.usu_modi      = v_usu_modi(j),
               ant.fec_modi      = v_fec_modi(j)
         WHERE ant.cod_pais = v_cod_pais(j)
           AND ant.cod_prog = v_cod_prog(j)
           AND ant.cod_cons = v_cod_consu(j);
    END LOOP;
    CLOSE curupdconsulconst;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_consu_antig_ctsto;

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_CONST_STO
                    Despacho de Premios Consultoras Nuevas por Nivel cuando el
                    indicador de constancia esta activado, ejecutado desde la
          validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_const_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  )

   IS
    --PER-SiCC-2013-0141 - INI
    /*CURSOR curinsconsodetal IS*/
    CURSOR curinsconsodetal(pinumpediorig NUMBER) IS
    --PER-SiCC-2013-0141 - FIN
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'
            -- Inicio cambio RCR PER-SiCC-2012-0202
            -- Que no sea Regalo x Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
            --PER-SiCC-2013-0141 - INI
         AND (nvl(pr.tide_tipo_desp, '01') = '01' OR
             (pr.tide_tipo_desp = '04' AND
             pinumpediorig =
             cup_fn_devue_numro_pedid_orig(pscodigopais,
                                             gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                              pscodigoperiodo,
                                                                              -to_number(pr.cod_nivel) + 1),
                                             pscodigocliente) AND
             nvl(ca.ind_rece_web, '0') = 1))

            --PER-SiCC-2013-0141 - FIN
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta)
            -- La consultora debe haber facturado en la campaña anterior
            /*AND EXISTS (SELECT NULL
             FROM CUP_CONSU_FACTU CF
            WHERE CF.COD_PAIS = CA.COD_PAIS
              AND CF.COD_CONS = CA.COD_CLIE
              AND CF.COD_PROG = PSCODIGOPROGRAMA
              AND CF.IND_CONS = '0')*/
              --ADD2
          AND NOT EXISTS (SELECT NULL
                 FROM ped_solic_cabec psc,
                      ped_tipo_solic_pais tsp,
                      ped_tipo_solic ts,
                      mae_clien cli,
                      seg_pais sp,
                      cra_perio cp,
                      seg_perio_corpo spc
                  WHERE sp.cod_pais = pscodigopais
                  AND spc.cod_peri = pscodigoperiodo
                  AND psc.fec_fact IS NOT NULL
                  AND ((psc.ind_pedi_prue IS NULL) OR (psc.ind_pedi_prue = 0))
                  AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND cli.oid_clie = psc.clie_oid_clie
                  AND psc.pais_oid_pais = sp.oid_pais
                  AND psc.perd_oid_peri = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND ts.ind_devo  = 0
                  AND psc.modu_oid_modu <> 15
                  AND ts.ind_anul  = 0
                  AND psc.ind_ts_no_conso = 1
                   AND psc.ind_oc = 1
                   AND cli.cod_clie = ca.cod_clie)
              --ADD2
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    --PER-SiCC-2013-0141 - INI
    lnnumpedi NUMBER := 0;
    --PER-SiCC-2013-0141 - FIN
  BEGIN
    --PER-SiCC-2013-0141 - INI
    BEGIN
      SELECT nvl(prog.num_pedi, 0)
        INTO lnnumpedi
        FROM cup_prog_nueva_cupon prog
       WHERE prog.cod_prog = pscodigoprograma;

    EXCEPTION
      WHEN no_data_found THEN
        lnnumpedi := 0;
    END;
    --PER-SiCC-2013-0141 - FIN
    --PER-SiCC-2013-0141 - INI
    /*OPEN curinsconsodetal;*/
    OPEN curinsconsodetal(lnnumpedi);
    --PER-SiCC-2013-0141 - FIN
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetal%ROWCOUNT;
      v_row_count := curinsconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(j),
           v_cod_peri(j),
           v_cod_clie(j),
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);
           

    END LOOP;
    CLOSE curinsconsodetal;
  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_despa_premi_const_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_NOCON_NIVEL_PRSTO
                    Incializa los Niveles por Consultoras para Prog SIN Constancia del primer pedido
                    de la tabla CUP_CONSU_NIVEL
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE cup_pr_carga_nocon_nivel_prsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS

    CURSOR curinsconsunivel IS
      SELECT consu.cod_pais AS cod_pais,
             consu.cod_prog AS cod_programa,
             niv.cod_nivel  AS nivel, -- todos los niveles en tabla equiv por periodo actual
             consu.cod_cons AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, niv.cod_nivel - 1) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND niv.cod_nivel = pn.cod_nive(+)) +
                                    niv.cod_nivel - 2) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva       consu,
             cup_nivel             niv,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.cod_ult_nivel = '01'
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND ca.cod_clie = consu.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND EXISTS (SELECT NULL
                FROM cup_equiv_matr ma
               WHERE ma.cod_pais = consu.cod_pais
                 AND ma.cod_prog = consu.cod_prog
                 AND ma.cod_peri = consu.camp_fin_ccc
                 AND ma.cod_nivel = niv.cod_nivel)
            -- Los q aun no se registran en CUP_CONSU_NIVEL
         AND NOT EXISTS
       (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = niv.cod_nivel
                 AND nivel.cod_cons = consu.cod_cons);

    CURSOR curupdconsunivel IS
      SELECT consu.cod_pais AS cod_pais,
             consu.cod_prog AS cod_programa,
             niv.cod_nivel  AS nivel, -- todos los niveles en tabla equiv por periodo actual
             consu.cod_cons AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, niv.cod_nivel - 1) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND niv.cod_nivel = pn.cod_nive(+)) +
                                    niv.cod_nivel - 2) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva       consu,
             cup_nivel             niv,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.cod_ult_nivel = '01'
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND ca.cod_clie = consu.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND EXISTS (SELECT NULL
                FROM cup_equiv_matr ma
               WHERE ma.cod_pais = consu.cod_pais
                 AND ma.cod_prog = consu.cod_prog
                 AND ma.cod_peri = consu.camp_fin_ccc
                 AND ma.cod_nivel = niv.cod_nivel)
            -- Los q aun no se registran en CUP_CONSU_NIVEL
         AND EXISTS (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = niv.cod_nivel
                 AND nivel.cod_cons = consu.cod_cons);

    TYPE t_cod_pais IS TABLE OF cup_consu_nivel.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nivel.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_nivel.cod_nivel%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nivel.cod_cons%TYPE;
    TYPE t_cam_inic_vig IS TABLE OF cup_consu_nivel.cam_inic_vig%TYPE;
    TYPE t_cam_fin_vig IS TABLE OF cup_consu_nivel.cam_fin_vig%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nivel.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nivel.fec_digi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_prog     t_cod_prog;
    v_cod_nivel    t_cod_nivel;
    v_cod_consu    t_cod_consu;
    v_cam_inic_vig t_cam_inic_vig;
    v_cam_fin_vig  t_cam_fin_vig;
    v_usu_digi     t_usu_digi;
    v_fec_digi     t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curinsconsunivel%ROWCOUNT;
      v_row_count := curinsconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        INSERT INTO cup_consu_nivel
          (cod_pais,
           cod_prog,
           cod_nivel,
           cam_inic_vig,
           cam_fin_vig,
           usu_digi,
           fec_digi,
           cod_cons)
        VALUES
          (v_cod_pais(j),
           v_cod_prog(j),
           v_cod_nivel(j),
           v_cam_inic_vig(j),
           v_cam_fin_vig(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_cod_consu(j));

    END LOOP;

    CLOSE curinsconsunivel;

    OPEN curupdconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsunivel%ROWCOUNT;
      v_row_count := curupdconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        UPDATE cup_consu_nivel
           SET cam_inic_vig = v_cam_inic_vig(j),
               cam_fin_vig  = v_cam_fin_vig(j),
               usu_modi     = v_usu_digi(j),
               fec_modi     = SYSDATE
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j)
           AND cod_nivel = v_cod_nivel(j);

    END LOOP;
    CLOSE curupdconsunivel;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_nocon_nivel_prsto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NIVEL_PRSTO
                    Incializa los Niveles por Consultoras para Prog CON Constancia de Primer Pedido
                    de la tabla CUP_CONSU_NIVEL, ejecutado desde la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nivel_prsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curinsconsunivel IS
      SELECT consu.cod_pais      AS cod_pais,
             consu.cod_prog      AS cod_programa,
             consu.cod_ult_nivel AS nivel,
             consu.cod_cons      AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 0) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND consu.cod_ult_nivel =
                                            pn.cod_nive(+)) - 1) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva       consu,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND ca.cod_clie = consu.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -- Los q aun no se registran en CUP_CONSU_NIVEL
         AND NOT EXISTS
       (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = consu.cod_ult_nivel
                 AND nivel.cod_cons = consu.cod_cons);

    CURSOR curupdconsunivel IS

      SELECT consu.cod_pais      AS cod_pais,
             consu.cod_prog      AS cod_programa,
             consu.cod_ult_nivel AS nivel,
             consu.cod_cons      AS cod_consu,
             -- se setea las campa?as de inicio y fin de vigencia
             gen_fn_dev_nsgte_campa(pscodigoperiodo, 0) AS cam_ini,
             gen_fn_dev_nsgte_campa(pscodigoperiodo,
                                    ( /*SELECT p.num_vig
                                                                            FROM cup_prog_nueva_cupon p
                                                                           WHERE p.cod_pais = pscodigopais
                                                                             AND p.cod_prog = pscodigoprograma*/
                                     SELECT CASE
                                               WHEN p.ind_vige = 'N' THEN
                                                pn.num_vige
                                               ELSE
                                                p.num_vig
                                             END num_vig
                                       FROM cup_prog_nueva_cupon p,
                                             nvs_progr_nivel      pn
                                      WHERE p.cod_pais = pscodigopais
                                        AND p.cod_prog = pscodigoprograma
                                        AND p.cod_pais = pn.cod_pais(+)
                                        AND p.cod_prog = pn.cod_prog(+)
                                        AND consu.cod_ult_nivel =
                                            pn.cod_nive(+)) - 1) AS cam_fin,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nueva       consu,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
       WHERE consu.cod_pais = pscodigopais
         AND consu.cod_prog = pscodigoprograma
         AND consu.camp_fin_ccc = pscodigoperiodo
         AND ca.cod_clie = consu.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND EXISTS (SELECT NULL
                FROM cup_consu_nivel nivel
               WHERE nivel.cod_pais = consu.cod_pais
                 AND nivel.cod_prog = consu.cod_prog
                 AND nivel.cod_nivel = consu.cod_ult_nivel
                 AND nivel.cod_cons = consu.cod_cons);

    TYPE t_cod_pais IS TABLE OF cup_consu_nivel.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nivel.cod_prog%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_nivel.cod_nivel%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nivel.cod_cons%TYPE;
    TYPE t_cam_inic_vig IS TABLE OF cup_consu_nivel.cam_inic_vig%TYPE;
    TYPE t_cam_fin_vig IS TABLE OF cup_consu_nivel.cam_fin_vig%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nivel.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nivel.fec_digi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_prog     t_cod_prog;
    v_cod_nivel    t_cod_nivel;
    v_cod_consu    t_cod_consu;
    v_cam_inic_vig t_cam_inic_vig;
    v_cam_fin_vig  t_cam_fin_vig;
    v_usu_digi     t_usu_digi;
    v_fec_digi     t_fec_digi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curinsconsunivel%ROWCOUNT;
      v_row_count := curinsconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

      -- Insertamos nivel por consultora
        INSERT INTO cup_consu_nivel
          (cod_pais,
           cod_prog,
           cod_nivel,
           cam_inic_vig,
           cam_fin_vig,
           usu_digi,
           fec_digi,
           cod_cons)
        VALUES
          (v_cod_pais(j),
           v_cod_prog(j),
           v_cod_nivel(j),
           v_cam_inic_vig(j),
           v_cam_fin_vig(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_cod_consu(j));

    END LOOP;
    CLOSE curinsconsunivel;

    OPEN curupdconsunivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsunivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_nivel,
             v_cod_consu,
             v_cam_inic_vig,
             v_cam_fin_vig,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsunivel%ROWCOUNT;
      v_row_count := curupdconsunivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count

        UPDATE cup_consu_nivel
           SET cam_inic_vig = v_cam_inic_vig(j),
               cam_fin_vig  = v_cam_fin_vig(j),
               usu_modi     = v_usu_digi(j),
               fec_modi     = SYSDATE
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j)
           AND cod_nivel = v_cod_nivel(j);

    END LOOP;
    CLOSE curupdconsunivel;
  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_consu_nivel_prsto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_CUPON_STO
                    Inicializa cupones Acumulado por Consultora
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_cupon_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS

    -- Inicializa Historico de Cupones por Consultora
    CURSOR curinscuponesconsu IS
      SELECT consuniv.cod_pais AS cod_pais,
             consuniv.cod_prog AS cod_programa,
             consuniv.cod_cons AS cod_consu,
             equiv.cod_nivel AS cod_nivel,
             equiv.cod_cupon AS cod_cupon,
             0 AS ind_utili,
             '' AS cam_utili,
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM cup_consu_nivel       consuniv,
             cup_equiv_matr        equiv,
             cup_consu_nueva       nueva,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
      -- MULTIPLICACION: Todas las consultoras con todos los cupones
       WHERE consuniv.cod_pais = pscodigopais
         AND consuniv.cod_prog = pscodigoprograma
         AND pscodigoperiodo BETWEEN consuniv.cam_inic_vig AND
             consuniv.cam_fin_vig
         AND equiv.cod_pais = consuniv.cod_pais
         AND equiv.cod_prog = consuniv.cod_prog
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_nivel = consuniv.cod_nivel
            /*AND EXISTS (SELECT NULL
             FROM CUP_CONSU_NUEVA NUEVA
            WHERE NUEVA.COD_PAIS = CONSUNIV.COD_PAIS
              AND NUEVA.COD_PROG = CONSUNIV.COD_PROG
              AND NUEVA.COD_CONS = CONSUNIV.COD_CONS
              AND NUEVA.CAMP_FIN_CCC = PSCODIGOPERIODO
              AND NUEVA.EST_PROC = '0')*/
         AND nueva.cod_pais = consuniv.cod_pais
         AND nueva.cod_prog = consuniv.cod_prog
         AND nueva.cod_cons = consuniv.cod_cons
         AND nueva.camp_fin_ccc = pscodigoperiodo
         AND nueva.est_proc = '0'
         AND ca.cod_clie = nueva.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -- Cupon no esta en tabla cup_consu_cupon
         AND NOT EXISTS
       (SELECT cupon.cod_cupon
                FROM cup_consu_cupon cupon
               WHERE cupon.cod_pais = equiv.cod_pais
                 AND cupon.cod_prog = equiv.cod_prog
                 AND cupon.cod_nivel = equiv.cod_nivel
                 AND cupon.cod_cupon = equiv.cod_cupon
                 AND cupon.cod_cons = consuniv.cod_cons);

    TYPE t_cod_pais IS TABLE OF cup_consu_cupon.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_cupon.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_cupon.cod_cons%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_cupon.cod_nivel%TYPE;
    TYPE t_cod_cupon IS TABLE OF cup_consu_cupon.cod_cupon%TYPE;
    TYPE t_ind_utili IS TABLE OF cup_consu_cupon.ind_utili%TYPE;
    TYPE t_cam_utili IS TABLE OF cup_consu_cupon.cam_utili%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_cupon.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_cupon.fec_digi%TYPE;

    v_cod_pais  t_cod_pais;
    v_cod_prog  t_cod_prog;
    v_cod_consu t_cod_consu;
    v_cod_nivel t_cod_nivel;
    v_cod_cupon t_cod_cupon;
    v_ind_utili t_ind_utili;
    v_cam_utili t_cam_utili;
    v_usu_digi  t_usu_digi;
    v_fec_digi  t_fec_digi;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

  BEGIN

    -- Inserta en cup_consu_cupon
    OPEN curinscuponesconsu;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinscuponesconsu BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_nivel,
             v_cod_cupon,
             v_ind_utili,
             v_cam_utili,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinscuponesconsu%ROWCOUNT;
      v_row_count_ins := curinscuponesconsu%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO cup_consu_cupon
          (cod_pais,
           cod_prog,
           cod_cons,
           cod_nivel,
           cod_cupon,
           ind_utili,
           cam_utili,
           usu_digi,
           fec_digi)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_nivel(i),
           v_cod_cupon(i),
           v_ind_utili(i),
           v_cam_utili(i),
           v_usu_digi(i),
           v_fec_digi(i));

    END LOOP;
    CLOSE curinscuponesconsu;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_consu_cupon_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_DETAL_PERIO_STO
                    Inicializa cupones de una consultora por nivel y periodo
          ejecutado por la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_carga_detal_perio_sto
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psind_cons_prem_elec  VARCHAR2
  ) IS
    -- Inicializa Detalle de Cupones por Consultora y Nivel
    CURSOR curinscuponesperio IS
      SELECT consuniv.cod_pais AS cod_pais,
             consuniv.cod_prog AS cod_programa,
             consuniv.cod_cons AS cod_consu,
             equiv.cod_peri    AS cod_periodo,
             equiv.cod_venta   AS cod_venta,
             0                 AS val_unida_pedid,
             0                 AS ind_factu,
             psusuario         AS usu_digi,
             SYSDATE           AS fec_digi
        FROM cup_consu_nivel       consuniv,
             cup_equiv_matr        equiv,
             cup_consu_nueva       nueva,
             cup_consu_cupon       cup,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp
      -- MULTIPLICACION: Todas las consultoras con todos los cupones
       WHERE consuniv.cod_pais = pscodigopais
         AND consuniv.cod_prog = pscodigoprograma
         AND (pscodigoperiodo BETWEEN consuniv.cam_inic_vig AND
             consuniv.cam_fin_vig)
         AND equiv.cod_pais = consuniv.cod_pais
         AND equiv.cod_prog = consuniv.cod_prog
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_nivel = consuniv.cod_nivel
         AND nueva.cod_pais = consuniv.cod_pais
         AND nueva.cod_prog = consuniv.cod_prog
         AND nueva.cod_cons = consuniv.cod_cons
         AND nueva.camp_fin_ccc = pscodigoperiodo
         AND nueva.est_proc = '0'
         AND ca.cod_clie = nueva.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            --  se insertan solo los cupones q aun no se utilizan
         AND cup.cod_pais = equiv.cod_pais
         AND cup.cod_prog = equiv.cod_prog
         AND cup.cod_nivel = equiv.cod_nivel
         AND cup.cod_cupon = equiv.cod_cupon
         AND cup.cod_cons = consuniv.cod_cons
         AND cup.ind_utili =
          -- add
          CASE
               WHEN (SELECT cup.ind_cupo_reut
                       FROM cup_prog_nueva_cupon cup
                      where cup.cod_pais= pscodigopais
                        and cup.cod_prog=pscodigoprograma) = '1' THEN
              CUP.ind_utili
             ELSE
              '0'
           END
           -- add
            -- Cupon no esta en tabla cup_detal_cupon_perio
         AND NOT EXISTS
       (SELECT detalprod.cod_venta
                FROM cup_detal_cupon_perio detalprod
               WHERE detalprod.cod_pais = equiv.cod_pais
                 AND detalprod.cod_prog = equiv.cod_prog
                 AND detalprod.cod_cons = consuniv.cod_cons
                 AND detalprod.cod_peri = equiv.cod_peri
                 AND detalprod.cod_venta = equiv.cod_venta);

    CURSOR curinscuponesperio2 IS
      SELECT consuniv.cod_pais AS cod_pais,
             consuniv.cod_prog AS cod_programa,
             consuniv.cod_cons AS cod_consu,
             equiv.cod_peri    AS cod_periodo,
             equiv.cod_venta   AS cod_venta,
             0                 AS val_unida_pedid,
             0                 AS ind_factu,
             psusuario         AS usu_digi,
             SYSDATE           AS fec_digi
        FROM cup_consu_nivel       consuniv,
             cup_equiv_matr        equiv,
             cup_consu_nueva       nueva,
             cup_consu_cupon       cup,
             sto_proce_docum_digit tmp
       WHERE consuniv.cod_pais = pscodigopais
         AND consuniv.cod_prog = pscodigoprograma
         AND pscodigoperiodo >= consuniv.cam_inic_vig
         AND pscodigoperiodo <= consuniv.cam_fin_vig
         AND equiv.cod_pais = consuniv.cod_pais
         AND equiv.cod_prog = consuniv.cod_prog
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_nivel = consuniv.cod_nivel
         AND nueva.cod_pais = consuniv.cod_pais
         AND nueva.cod_prog = consuniv.cod_prog
         AND nueva.cod_cons = consuniv.cod_cons
         AND nueva.camp_fin_ccc = pscodigoperiodo
         AND nueva.est_proc = '0'
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND consuniv.cod_cons = pscodigocliente
         AND tmp.cod_clie = nueva.cod_cons
         AND cup.cod_pais = equiv.cod_pais
         AND cup.cod_prog = equiv.cod_prog
         AND cup.cod_nivel = equiv.cod_nivel
         AND cup.cod_cupon = equiv.cod_cupon
         AND cup.cod_cons = consuniv.cod_cons
         AND cup.ind_utili =
         -- add
          CASE
               WHEN (SELECT cup.ind_cupo_reut
                       FROM cup_prog_nueva_cupon cup
                      where cup.cod_pais= pscodigopais
                        and cup.cod_prog=pscodigoprograma) = '1' THEN
              CUP.ind_utili
             ELSE
              '0'
           END
           -- add
         AND NOT EXISTS
       (SELECT detalprod.cod_venta
                FROM cup_detal_cupon_perio detalprod
               WHERE detalprod.cod_pais = equiv.cod_pais
                 AND detalprod.cod_prog = equiv.cod_prog
                 AND detalprod.cod_cons = consuniv.cod_cons
                 AND detalprod.cod_peri = equiv.cod_peri
                 AND detalprod.cod_venta = equiv.cod_venta
                 AND equiv.cod_peri = pscodigoperiodo);

    ------------------------------------------------------------------
    -- PARA EL CASO DE SIN CONSTACIA Y LA CONSULTORA DEBE SER CONSTANTE
    CURSOR curinscuponesperio3 IS
      SELECT consuniv.cod_pais AS cod_pais,
             consuniv.cod_prog AS cod_programa,
             consuniv.cod_cons AS cod_consu,
             equiv.cod_peri    AS cod_periodo,
             equiv.cod_venta   AS cod_venta,
             0                 AS val_unida_pedid,
             0                 AS ind_factu,
             psusuario         AS usu_digi,
             SYSDATE           AS fec_digi,
             cf.ind_cons       AS ind_cons,
             cup.cod_cupon     AS cup_cupon,
             cup.cod_nivel     AS cod_nivel
        FROM cup_consu_nivel       consuniv,
             cup_equiv_matr        equiv,
             cup_consu_nueva       nueva,
             cup_consu_cupon       cup,
             int_solic_conso_cabec ca,
             sto_proce_docum_digit tmp,
             cup_consu_factu       cf
      -- MULTIPLICACION: Todas las consultoras con todos los cupones
       WHERE consuniv.cod_pais = pscodigopais
         AND consuniv.cod_prog = pscodigoprograma
         AND (pscodigoperiodo BETWEEN consuniv.cam_inic_vig AND
             consuniv.cam_fin_vig)
         AND equiv.cod_pais = consuniv.cod_pais
         AND equiv.cod_prog = consuniv.cod_prog
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_nivel = consuniv.cod_nivel
         AND nueva.cod_pais = consuniv.cod_pais
         AND nueva.cod_prog = consuniv.cod_prog
         AND nueva.cod_cons = consuniv.cod_cons
         AND nueva.camp_fin_ccc = pscodigoperiodo
         AND nueva.est_proc = '0'
         AND ca.cod_clie = nueva.cod_cons
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            --  se insertan solo los cupones q aun no se utilizan
         AND cup.cod_pais = equiv.cod_pais
         AND cup.cod_prog = equiv.cod_prog
         AND cup.cod_nivel = equiv.cod_nivel
         AND cup.cod_cupon = equiv.cod_cupon
         AND cup.cod_cons = consuniv.cod_cons
         AND cup.ind_utili =
          -- add
          CASE
               WHEN (SELECT cup.ind_cupo_reut
                       FROM cup_prog_nueva_cupon cup
                      where cup.cod_pais= pscodigopais
                        and cup.cod_prog=pscodigoprograma) = '1' THEN
              CUP.ind_utili
             ELSE
              '0'
           END
           -- add
         AND cf.cod_pais = nueva.cod_pais
         AND cf.cod_prog = nueva.cod_prog
         AND cf.cod_cons = nueva.cod_cons
            --AND cf.ind_cons = '0'

            -- Cupon no esta en tabla cup_detal_cupon_perio
         AND NOT EXISTS
       (SELECT detalprod.cod_venta
                FROM cup_detal_cupon_perio detalprod
               WHERE detalprod.cod_pais = equiv.cod_pais
                 AND detalprod.cod_prog = equiv.cod_prog
                 AND detalprod.cod_cons = consuniv.cod_cons
                 AND detalprod.cod_peri = equiv.cod_peri
                 AND detalprod.cod_venta = equiv.cod_venta);

    CURSOR curinscuponesperio4 IS
      SELECT consuniv.cod_pais AS cod_pais,
             consuniv.cod_prog AS cod_programa,
             consuniv.cod_cons AS cod_consu,
             equiv.cod_peri    AS cod_periodo,
             equiv.cod_venta   AS cod_venta,
             0                 AS val_unida_pedid,
             0                 AS ind_factu,
             psusuario         AS usu_digi,
             SYSDATE           AS fec_digi,
             cf.ind_cons       AS ind_cons,
             cup.cod_cupon     AS cup_cupon,
             cup.cod_nivel     AS cod_nivel
        FROM cup_consu_nivel       consuniv,
             cup_equiv_matr        equiv,
             cup_consu_nueva       nueva,
             cup_consu_cupon       cup,
             sto_proce_docum_digit tmp,
             cup_consu_factu       cf
       WHERE consuniv.cod_pais = pscodigopais
         AND consuniv.cod_prog = pscodigoprograma
         AND pscodigoperiodo >= consuniv.cam_inic_vig
         AND pscodigoperiodo <= consuniv.cam_fin_vig
         AND equiv.cod_pais = consuniv.cod_pais
         AND equiv.cod_prog = consuniv.cod_prog
         AND equiv.cod_peri = pscodigoperiodo
         AND equiv.cod_nivel = consuniv.cod_nivel
         AND nueva.cod_pais = consuniv.cod_pais
         AND nueva.cod_prog = consuniv.cod_prog
         AND nueva.cod_cons = consuniv.cod_cons
         AND nueva.camp_fin_ccc = pscodigoperiodo
         AND nueva.est_proc = '0'
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND consuniv.cod_cons = pscodigocliente
         AND tmp.cod_clie = nueva.cod_cons
         AND cup.cod_pais = equiv.cod_pais
         AND cup.cod_prog = equiv.cod_prog
         AND cup.cod_nivel = equiv.cod_nivel
         AND cup.cod_cupon = equiv.cod_cupon
         AND cup.cod_cons = consuniv.cod_cons
         AND cup.ind_utili =
          -- add
          CASE
               WHEN (SELECT cup.ind_cupo_reut
                       FROM cup_prog_nueva_cupon cup
                      where cup.cod_pais= pscodigopais
                        and cup.cod_prog=pscodigoprograma) = '1' THEN
              CUP.ind_utili
             ELSE
              '0'
           END
           -- add
         AND cf.cod_pais = nueva.cod_pais
         AND cf.cod_prog = nueva.cod_prog
         AND cf.cod_cons = nueva.cod_cons
            --AND cf.ind_cons = '0'

         AND NOT EXISTS
       (SELECT detalprod.cod_venta
                FROM cup_detal_cupon_perio detalprod
               WHERE detalprod.cod_pais = equiv.cod_pais
                 AND detalprod.cod_prog = equiv.cod_prog
                 AND detalprod.cod_cons = consuniv.cod_cons
                 AND detalprod.cod_peri = equiv.cod_peri
                 AND detalprod.cod_venta = equiv.cod_venta
                 AND equiv.cod_peri = pscodigoperiodo);

    ------------------------------------------------------------------

    TYPE t_cod_pais IS TABLE OF cup_detal_cupon_perio.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_detal_cupon_perio.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_detal_cupon_perio.cod_cons%TYPE;
    TYPE t_cod_peri IS TABLE OF cup_detal_cupon_perio.cod_peri%TYPE;
    TYPE t_cod_venta IS TABLE OF cup_detal_cupon_perio.cod_venta%TYPE;
    TYPE t_val_unida_pedid IS TABLE OF cup_detal_cupon_perio.val_unida_pedid%TYPE;
    TYPE t_ind_factu IS TABLE OF cup_detal_cupon_perio.ind_factu%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_detal_cupon_perio.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_detal_cupon_perio.fec_digi%TYPE;
    TYPE t_ind_cons IS TABLE OF cup_consu_factu.ind_cons%TYPE;
    TYPE t_cod_cupon IS TABLE OF cup_consu_cupon.cod_cupon%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_consu_cupon.cod_nivel%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_peri        t_cod_peri;
    v_cod_venta       t_cod_venta;
    v_val_unida_pedid t_val_unida_pedid;
    v_ind_factu       t_ind_factu;
    v_usu_digi        t_usu_digi;
    v_fec_digi        t_fec_digi;
    v_ind_cons        t_ind_cons;
    v_cod_cupon       t_cod_cupon;
    v_cod_nivel       t_cod_nivel;

    rows            NATURAL := 1000; -- Number of rows to process at a time
    i               BINARY_INTEGER := 0;
    v_row_count_ins NUMBER := 0;

    lsindnuevas bas_param_pais.val_para%TYPE;

    countpremioelect NUMBER := 0;

  BEGIN

    SELECT MIN(val_para)
      INTO lsindnuevas
      FROM bas_param_pais
     WHERE cod_sist = 'CUP'
       AND cod_para = '001'
       AND cod_pais = pscodigopais;

    IF lsindnuevas = 'S' THEN

      IF (psindicadorconstancia = '0' AND psind_cons_prem_elec = '1') THEN

        OPEN curinscuponesperio4;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curinscuponesperio4 BULK COLLECT
            INTO v_cod_pais,
                 v_cod_prog,
                 v_cod_consu,
                 v_cod_peri,
                 v_cod_venta,
                 v_val_unida_pedid,
                 v_ind_factu,
                 v_usu_digi,
                 v_fec_digi,
                 v_ind_cons,
                 v_cod_cupon,
                 v_cod_nivel LIMIT rows;

          IF v_cod_pais.count > 0 THEN
            FOR i IN 1 .. v_cod_pais.last
            LOOP

              -- se verifica si es premio electivo
              SELECT COUNT(1)
                INTO countpremioelect
                FROM lov_equiv_matr a
               WHERE a.cod_prog = v_cod_prog(i)
                 AND a.cod_pais = v_cod_pais(i)
                 AND a.cod_cupon = v_cod_cupon(i)
                 AND a.cod_peri = v_cod_peri(i)
                 AND a.cod_nivel = v_cod_nivel(i);

              -- Si no es premio despacha el cupon
              IF (countpremioelect = 0) THEN

                INSERT INTO cup_detal_cupon_perio
                  (cod_pais,
                   cod_prog,
                   cod_cons,
                   cod_peri,
                   cod_venta,
                   val_unida_pedid,
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
                   v_ind_factu(i),
                   v_usu_digi(i),
                   v_fec_digi(i));
                -- Si es premio electivo la consultora debe de ser constante
              ELSIF (countpremioelect = 1) THEN
                IF (v_ind_cons(i) = '0') THEN

                  INSERT INTO cup_detal_cupon_perio
                    (cod_pais,
                     cod_prog,
                     cod_cons,
                     cod_peri,
                     cod_venta,
                     val_unida_pedid,
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
                     v_ind_factu(i),
                     v_usu_digi(i),
                     v_fec_digi(i));

                END IF;
              END IF;

            END LOOP;
          END IF;

          EXIT WHEN curinscuponesperio4%NOTFOUND;
        END LOOP;
        CLOSE curinscuponesperio4;

      ELSE

        OPEN curinscuponesperio2;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curinscuponesperio2 BULK COLLECT
            INTO v_cod_pais,
                 v_cod_prog,
                 v_cod_consu,
                 v_cod_peri,
                 v_cod_venta,
                 v_val_unida_pedid,
                 v_ind_factu,
                 v_usu_digi,
                 v_fec_digi LIMIT rows;

          IF v_cod_pais.count > 0 THEN
            FORALL i IN 1 .. v_cod_pais.count
              INSERT INTO cup_detal_cupon_perio
                (cod_pais,
                 cod_prog,
                 cod_cons,
                 cod_peri,
                 cod_venta,
                 val_unida_pedid,
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
                 v_ind_factu(i),
                 v_usu_digi(i),
                 v_fec_digi(i));
          END IF;
          EXIT WHEN curinscuponesperio2%NOTFOUND;
        END LOOP;
        CLOSE curinscuponesperio2;

      END IF;

    ELSE

      IF (psindicadorconstancia = '0' AND psind_cons_prem_elec = '1') THEN

        -- Inserta en cup_detal_cupon_perio
        OPEN curinscuponesperio3;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curinscuponesperio3 BULK COLLECT
            INTO v_cod_pais,
                 v_cod_prog,
                 v_cod_consu,
                 v_cod_peri,
                 v_cod_venta,
                 v_val_unida_pedid,
                 v_ind_factu,
                 v_usu_digi,
                 v_fec_digi,
                 v_ind_cons,
                 v_cod_cupon,
                 v_cod_nivel LIMIT rows;

          IF v_cod_pais.count > 0 THEN
            FOR i IN 1 .. v_cod_pais.last
            LOOP

              SELECT COUNT(1)
                INTO countpremioelect
                FROM lov_equiv_matr a
               WHERE a.cod_prog = v_cod_prog(i)
                 AND a.cod_pais = v_cod_pais(i)
                 AND a.cod_cupon = v_cod_cupon(i)
                 AND a.cod_peri = v_cod_peri(i)
                 AND a.cod_nivel = v_cod_nivel(i);

              -- Si no es premio despacha el cupon
              IF (countpremioelect = 0) THEN

                INSERT INTO cup_detal_cupon_perio
                  (cod_pais,
                   cod_prog,
                   cod_cons,
                   cod_peri,
                   cod_venta,
                   val_unida_pedid,
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
                   v_ind_factu(i),
                   v_usu_digi(i),
                   v_fec_digi(i));
                -- Si es premio electivo la consultora debe de ser constante
              ELSIF (countpremioelect = 1) THEN
                IF (v_ind_cons(i) = '0') THEN

                  INSERT INTO cup_detal_cupon_perio
                    (cod_pais,
                     cod_prog,
                     cod_cons,
                     cod_peri,
                     cod_venta,
                     val_unida_pedid,
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
                     v_ind_factu(i),
                     v_usu_digi(i),
                     v_fec_digi(i));

                END IF;
              END IF;

            END LOOP;
          END IF;

          EXIT WHEN curinscuponesperio3%NOTFOUND;
        END LOOP;
        CLOSE curinscuponesperio3;

      ELSE

        -- Inserta en cup_detal_cupon_perio
        OPEN curinscuponesperio;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curinscuponesperio BULK COLLECT
            INTO v_cod_pais,
                 v_cod_prog,
                 v_cod_consu,
                 v_cod_peri,
                 v_cod_venta,
                 v_val_unida_pedid,
                 v_ind_factu,
                 v_usu_digi,
                 v_fec_digi LIMIT rows;

          EXIT WHEN v_row_count_ins = curinscuponesperio%ROWCOUNT;
          v_row_count_ins := curinscuponesperio%ROWCOUNT;

          -- Bulk bind of data in memory table...
          FORALL i IN 1 .. v_cod_pais.count
            INSERT INTO cup_detal_cupon_perio
              (cod_pais,
               cod_prog,
               cod_cons,
               cod_peri,
               cod_venta,
               val_unida_pedid,
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
               v_ind_factu(i),
               v_usu_digi(i),
               v_fec_digi(i));

        END LOOP;
        CLOSE curinscuponesperio;

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_carga_detal_perio_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_IND_ERROR_CUSTO
                    Validacion de Cupones q son solicitados por Consultoras
                    que no pertenecen o q ya fueron usados
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_actua_ind_error_custo
  (
    pscodigopais        VARCHAR2,
    pscodigoperiodo     VARCHAR2,
    pscodigoprograma    VARCHAR2,
    psusuario           VARCHAR2,
    pscodtipodocu       VARCHAR2,
    psnumeroproceso     VARCHAR2,
    pscodigocliente     VARCHAR2,
    psindconst          VARCHAR2,
    psindconstpremeelec VARCHAR2
  ) IS

    CURSOR curupdconsodetal /*(pNumLote varchar2)*/
    IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             cup_equiv_matr        equiv,
             -- nuevo
             cup_consu_nueva ccn,
             --
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND ccn.cod_pais = det.cod_pais
            --NUEVO
         AND ccn.cod_cons = det.cod_clie
         AND ccn.cod_prog = pscodigoprograma
            --
         AND det.cod_peri = pscodigoperiodo
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent
            -- validamos q los productos existan en el detalle de cupones x consultora x periodo
         AND NOT EXISTS (SELECT detprodu.cod_venta
                FROM cup_detal_cupon_perio detprodu
               WHERE detprodu.cod_pais = det.cod_pais
                 AND detprodu.cod_prog = pscodigoprograma
                 AND detprodu.cod_cons = det.cod_clie
                 AND detprodu.cod_peri = det.cod_peri
                 AND detprodu.cod_venta = det.cod_vent
                 AND detprodu.ind_factu = '0'); -- no facturado

    CURSOR curupdconsodetal2 IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             cup_equiv_matr        equiv,
             -- nuevo
             cup_consu_nueva ccn,
             --
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND ccn.cod_pais = det.cod_pais
            --NUEVO
         AND ccn.cod_cons = det.cod_clie
         AND ccn.cod_prog = pscodigoprograma
            --
         AND det.cod_peri = pscodigoperiodo
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent
            -- validamos q los productos existan en el detalle de cupones x consultora x periodo
         AND NOT EXISTS (SELECT detprodu.cod_venta
                FROM cup_detal_cupon_perio detprodu
               WHERE detprodu.cod_pais = det.cod_pais
                 AND detprodu.cod_prog = pscodigoprograma
                 AND detprodu.cod_cons = det.cod_clie
                 AND detprodu.cod_peri = det.cod_peri
                 AND detprodu.cod_venta = det.cod_vent
                 AND detprodu.ind_factu = '0') -- no facturado
            -- que no sea premio electivo
         AND NOT EXISTS (SELECT NULL
                FROM lov_equiv_matr b
               WHERE b.cod_venta = equiv.cod_venta
                 AND b.cod_peri = equiv.cod_peri

                    -- Inicio cambio RCR PER-SiCC-2012-0362
                 AND b.cod_prog = pscodigoprograma
                    -- Fin cambio RCR PER-SiCC-2012-0362

                 AND b.cod_pais = equiv.cod_pais);

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

    IF (psindconst = '1' AND psindconstpremeelec = '0') THEN

      OPEN curupdconsodetal2;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal2 BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal2%ROWCOUNT;
        v_row_count := curupdconsodetal2%ROWCOUNT;

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
      CLOSE curupdconsodetal2;

    ELSE

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

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_actua_ind_error_custo;

  /**************************************************************************
  Descripcion       : CUP_PR_VAL_UNID_NIVEL_STO
                    Valida la cantidad de cupones a despachar dentro del nivel.
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : RRG
  ***************************************************************************/
  PROCEDURE cup_pr_val_unid_nivel_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curupdunidadesnivel IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             pscodigoprograma AS cod_prog,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             equiv.val_prio AS val_prio,
             equiv.cod_nivel AS cod_nivel,
             equiv.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_equiv_matr        equiv,
             int_solic_conso_cabec cab,
             cup_detal_cupon_perio detprodu,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
            --------------------------------
            -- Se quita el lote actual porque se deben procesar
            -- las consultoras que estan en la tabla TMP de STO,
            /*AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/
            --------------------------------
         AND det.cod_peri = pscodigoperiodo
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent
         AND det.ind_erro_sse = '0'
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- validamos q los productos existan en el detalle de cupones x consultora x periodo
         AND detprodu.cod_pais = det.cod_pais
         AND detprodu.cod_prog = pscodigoprograma
         AND detprodu.cod_cons = det.cod_clie
         AND detprodu.cod_peri = det.cod_peri
         AND detprodu.cod_venta = det.cod_vent
         AND detprodu.ind_factu = '0'
            ---
         AND NOT EXISTS
       (SELECT NULL
                FROM lov_equiv_matr em
               WHERE em.cod_prog = pscodigoprograma
                 AND em.cod_pais = detprodu.cod_pais
                 AND em.cod_cupon = equiv.cod_cupon
                 AND em.cod_peri = detprodu.cod_peri)
       ORDER BY det.cod_clie,
                equiv.cod_nivel,
                equiv.val_prio ASC;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_val_prio IS TABLE OF cup_equiv_matr.val_prio%TYPE;
    TYPE t_cod_nivel IS TABLE OF cup_equiv_matr.cod_nivel%TYPE;
    TYPE t_cod_cupon IS TABLE OF cup_equiv_matr.cod_cupon%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;
    v_cod_prog     t_cod_prog;
    v_val_prio     t_val_prio;
    v_cod_nivel    t_cod_nivel;
    v_cod_cupon    t_cod_cupon;

    rows          NATURAL := 1000; -- Number of rows to process at a time
    j             BINARY_INTEGER := 0;
    v_row_count   NUMBER := 0;
    codcliente    int_solic_conso_detal.cod_clie%TYPE := '0';
    codnivel      cup_equiv_matr.cod_nivel%TYPE := '0';
    num_permitido NUMBER := 0;
    num_cantidad  NUMBER := 0;

  BEGIN

    OPEN curupdunidadesnivel;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdunidadesnivel BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_prog,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_prio,
             v_cod_nivel,
             v_cod_cupon,
             v_tip_posic,
             v_ind_erro_sse,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdunidadesnivel%ROWCOUNT;
      v_row_count := curupdunidadesnivel%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FOR j IN v_cod_pais.first .. v_cod_pais.last
      LOOP

        IF ((v_cod_clie(j) <> codcliente) OR (v_cod_nivel(j) <> codnivel)) THEN
          codcliente := v_cod_clie(j);
          codnivel   := v_cod_nivel(j);
          -- Parametro de unidades permitidas por nivel
          num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel(v_cod_nivel(j),
                                                                       v_cod_peri(j),
                                                                       pscodigoprograma);
          -- Cantidad cupones del nivel utilizados x la consultora
          num_cantidad := cup_pkg_prog_nuevas.cup_fn_dev_nivel_camp_uti_cons(v_cod_pais(j),
                                                                          v_cod_prog(j),
                                                                          v_cod_nivel(j),
                                                                          v_cod_clie(j),
                                                                          pscodigoperiodo
                                                                          );



        END IF;

        IF (num_cantidad >= num_permitido) THEN
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = v_ind_erro_sse(j),
                 usu_modi     = v_usu_modi(j),
                 fec_modi     = v_fec_modi(j)
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = codcliente
             AND num_lote = v_num_lote(j)
             AND cod_vent = v_cod_vent(j)
             AND tip_posic = v_tip_posic(j);

        END IF;
        num_cantidad := num_cantidad + 1;

      END LOOP;

    END LOOP;
    CLOSE curupdunidadesnivel;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_val_unid_nivel_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_ACTUA_UNIDA_DEMAN_STO
                    Validacion de Cupones solicitados que pidieron mas
                    de las unidades que se le permite a una consultora Print
          ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE cup_pr_actua_unida_deman_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS

    CURSOR curupdconsodetal IS
      SELECT pscodigopais     AS cod_pais,
             pscodigoperiodo  AS cod_periodo,
             det.cod_clie     AS cod_consu,
             det.num_lote     AS num_lote,
             det.cod_vent     AS cod_venta,
             det.tip_posic    AS tip_posic,
             det.val_unid_dem AS val_unid_dem,
             psusuario        AS usu_modi,
             SYSDATE          AS fec_modi
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             cup_detal_cupon_perio detprodu,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo
         AND det.val_unid_dem >
             (SELECT nvl(em.val_unid_maxi, 1)
                FROM cup_equiv_matr em
               WHERE em.cod_pais = det.cod_pais
                 AND em.cod_prog = pscodigoprograma
                 AND em.cod_peri = det.cod_peri
                 AND em.cod_venta = det.cod_vent)
         AND det.ind_erro_sse = '0' --Actualiza val_unid_dem solo para los pedidos  validos
         AND det.sta_proc <> 'S'
            --  AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND detprodu.cod_pais = det.cod_pais
         AND detprodu.cod_prog = pscodigoprograma
         AND detprodu.cod_cons = det.cod_clie
         AND detprodu.cod_peri = det.cod_peri
         AND detprodu.cod_venta = det.cod_vent
         AND detprodu.ind_factu = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_tip_posic    t_tip_posic;
    v_val_unid_dem t_val_unid_dem;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
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
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
      v_row_count := curupdconsodetal%ROWCOUNT;

      FORALL j IN 1 .. v_cod_pais.count
        UPDATE int_solic_conso_detal
           SET val_unid_dem =
               (SELECT nvl(em.val_unid_maxi, 1)
                  FROM cup_equiv_matr em
                 WHERE em.cod_pais = v_cod_pais(j)
                   AND em.cod_prog = pscodigoprograma
                   AND em.cod_peri = v_cod_peri(j)
                   AND em.cod_venta = v_cod_vent(j)),
               usu_modi     = v_usu_modi(j),
               fec_modi     = v_fec_modi(j)
         WHERE cod_pais = v_cod_pais(j)
           AND cod_peri = v_cod_peri(j)
           AND cod_clie = v_cod_clie(j)
           AND num_lote = v_num_lote(j)
           AND cod_vent = v_cod_vent(j)
           AND tip_posic = v_tip_posic(j);

      FORALL k IN 1 .. v_cod_pais.count
      -- Salvar el valor realmente solicitado (originalmente registrado en INT_SOLIC_CONSO_DETAL) a la tabla cup_detal_cupon_perio
        UPDATE cup_detal_cupon_perio
           SET val_unida_pedid = v_val_unid_dem(k),
               usu_modi        = v_usu_modi(k),
               fec_modi        = v_fec_modi(k)
         WHERE cod_pais = v_cod_pais(k)
           AND cod_prog = pscodigoprograma
           AND cod_cons = v_cod_clie(k)
           AND cod_peri = v_cod_peri(k)
           AND cod_venta = v_cod_vent(k);

    END LOOP;

    CLOSE curupdconsodetal;

  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);

  END cup_pr_actua_unida_deman_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_BORRA_CUPON_DEFEC_STO
                      Borra los cupones love adicionados por defecto, ejecutado
            desde la validacion de STO
  Fecha Creacion    : 10/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_borra_cupon_defec_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curdelete IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       cn,
             sto_proce_docum_digit tmp,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c
       WHERE ca.ind_proc_gp2 = '0'
            -------------------------------------------------
         AND ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -------------------------------------------------
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
            -------------------------------------------------
            /*AND EXISTS(SELECT NULL
            FROM MAE_CLIEN_TIPO_SUBTI A,
               MAE_CLIEN_CLASI B,
               MAE_CLIEN C
             WHERE A.CLIE_OID_CLIE = C.OID_CLIE
             AND C.COD_CLIE = CA.COD_CLIE
             AND A.OID_CLIE_TIPO_SUBT = B.CTSU_OID_CLIE_TIPO_SUBT
             AND B.CLAS_OID_CLAS = (SELECT OID_CLAS
                          FROM MAE_LOVE_CLASI_PRINT
                         WHERE TIP_PROG = 'LOV'))*/
            -- Que sea LOVE
         AND a.clie_oid_clie = c.oid_clie
         AND c.cod_clie = ca.cod_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV');

    CURSOR curdelete_todos IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       cn,
             sto_proce_docum_digit tmp
       WHERE ca.ind_proc_gp2 = '0'
            -------------------------------------------------
         AND ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -------------------------------------------------
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    v_cod_pais t_cod_pais;
    v_cod_peri t_cod_peri;
    v_cod_clie t_cod_clie;
    v_num_lote t_num_lote;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN
    IF psindicadortodos = 'N' THEN
      OPEN curdelete;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curdelete BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote LIMIT rows;

        EXIT WHEN v_row_count = curdelete%ROWCOUNT;
        v_row_count := curdelete%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Cambio por STO
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = '1'
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = v_cod_clie(j)
             AND num_lote = v_num_lote(j)
             AND sta_proc = 'L';

          -- Borro el log de agregar el cupon por defecto
          DELETE FROM lov_histo_movim
           WHERE cod_peri = v_cod_peri(j)
             AND cod_cons = v_cod_clie(j)
             AND cod_moti_acci = motivo_insercion_default
             AND cod_prog = pscodigoprograma
             AND cod_pais = v_cod_pais(j);

        END LOOP;

      END LOOP;
      CLOSE curdelete;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN curdelete_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curdelete_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote LIMIT rows;

        EXIT WHEN v_row_count = curdelete_todos%ROWCOUNT;
        v_row_count := curdelete_todos%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP

          -- Borro del detalle el cupon por defecto
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = '1'
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = v_cod_clie(j)
             AND num_lote = v_num_lote(j)
             AND sta_proc = 'L';
          /*DELETE FROM INT_SOLIC_CONSO_DETAL
          WHERE COD_PAIS = v_cod_pais(j)
           and COD_PERI = v_cod_peri(j)
           and COD_CLIE = v_cod_clie(j)
           and NUM_LOTE = v_num_lote(j)
           and sta_proc = 'L';  */

          -- Borro el log de agregar el cupon por defecto
          DELETE FROM lov_histo_movim
           WHERE cod_peri = v_cod_peri(j)
             AND cod_cons = v_cod_clie(j)
             AND cod_moti_acci = motivo_insercion_default
             AND cod_prog = pscodigoprograma
             AND cod_pais = v_cod_pais(j);

        END LOOP;

      END LOOP;
      CLOSE curdelete_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
  END cup_pr_borra_cupon_defec_sto;

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_NONIV_STO
                      Validacion de Cupones LOVE que marca con error los cupones love
            solicitado no corresponde al nivel de la consultora. ejecutado
            desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_noniv_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psindconst       VARCHAR2,
    psindconstprem   VARCHAR2
  ) IS

    CURSOR curupdconsodetal IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent
         AND det.cod_peri = pscodigoperiodo
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
         AND c.cod_clie = det.cod_clie
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

         AND cn.cod_ult_nivel !=
             (SELECT '0' || (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                       FROM cup_prog_nueva_cupon c
                                      WHERE c.cod_prog = pscodigoprograma),
                                     '0',
                                     1,
                                     0)))
              --AND CN.COD_ULT_NIVEL != (SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent);

    CURSOR curupdconsodetal_todos IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent

         AND det.cod_peri = pscodigoperiodo
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

         AND cn.cod_ult_nivel !=
             (SELECT '0' || (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                       FROM cup_prog_nueva_cupon c
                                      WHERE c.cod_prog = pscodigoprograma),
                                     '0',
                                     1,
                                     0)))
              --AND CN.COD_ULT_NIVEL != (SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent);

    CURSOR curupdconsodetal2
    (
      oidpais  NUMBER,
      oidmarca NUMBER,
      oidcanal NUMBER
    ) IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent
         AND det.cod_peri = pscodigoperiodo
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
         AND c.cod_clie = det.cod_clie
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

         AND (cn.cod_ult_nivel +
             ven_pkg_repor.ven_fn_devue_nume_campa(camp_fin_ccc,
                                                    pscodigoperiodo,
                                                    oidpais,
                                                    oidmarca,
                                                    oidcanal) - 1) !=
             (SELECT (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                FROM cup_prog_nueva_cupon c
                               WHERE c.cod_prog = pscodigoprograma),
                              '0',
                              1,
                              0)))
              --AND CN.COD_ULT_NIVEL != (SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent);

    CURSOR curupdconsodetal_todos2
    (
      oidpais  NUMBER,
      oidmarca NUMBER,
      oidcanal NUMBER
    ) IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent

         AND det.cod_peri = pscodigoperiodo
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

         AND (cn.cod_ult_nivel +
             ven_pkg_repor.ven_fn_devue_nume_campa(camp_fin_ccc,
                                                    pscodigoperiodo,
                                                    oidpais,
                                                    oidmarca,
                                                    oidcanal) - 1) !=
             (SELECT (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                FROM cup_prog_nueva_cupon c
                               WHERE c.cod_prog = pscodigoprograma),
                              '0',
                              1,
                              0)))
              --AND CN.COD_ULT_NIVEL != (SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupon IS TABLE OF lov_equiv_matr.cod_cupon%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_cod_cupo     t_cod_cupon;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

    vnoidpais  NUMBER(12);
    vnoidmarca NUMBER(12);
    vnoidcanal NUMBER(12);

  BEGIN

    vnoidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    vnoidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
    vnoidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');

    IF psindicadortodos = 'N' THEN

      IF (psindconst = '1' AND psindconstprem = '0') THEN

        OPEN curupdconsodetal2(vnoidpais, vnoidmarca, vnoidcanal);
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curupdconsodetal2 BULK COLLECT
            INTO v_cod_pais,
                 v_cod_peri,
                 v_cod_clie,
                 v_num_lote,
                 v_cod_vent,
                 v_cod_cupo,
                 v_tip_posic,
                 v_ind_erro_sse,
                 v_usu_modi,
                 v_fec_modi LIMIT rows;

          EXIT WHEN v_row_count = curupdconsodetal2%ROWCOUNT;
          v_row_count := curupdconsodetal2%ROWCOUNT;

          -- Bulk bind of data in memory table...
          FOR j IN v_cod_pais.first .. v_cod_pais.last
          LOOP
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

            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupo(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupo(j)
                   AND cod_prog = psusuario
                   AND cod_pais = v_cod_pais(j);
            END;

          END LOOP;

        END LOOP;
        CLOSE curupdconsodetal2;

      ELSE

        OPEN curupdconsodetal;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curupdconsodetal BULK COLLECT
            INTO v_cod_pais,
                 v_cod_peri,
                 v_cod_clie,
                 v_num_lote,
                 v_cod_vent,
                 v_cod_cupo,
                 v_tip_posic,
                 v_ind_erro_sse,
                 v_usu_modi,
                 v_fec_modi LIMIT rows;

          EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
          v_row_count := curupdconsodetal%ROWCOUNT;

          -- Bulk bind of data in memory table...
          FOR j IN v_cod_pais.first .. v_cod_pais.last
          LOOP
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

            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupo(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupo(j)
                   AND cod_prog = psusuario
                   AND cod_pais = v_cod_pais(j);
            END;

          END LOOP;

        END LOOP;
        CLOSE curupdconsodetal;
      END IF;

    END IF;

    IF psindicadortodos = 'S' THEN

      IF (psindconst = '1' AND psindconstprem = '0') THEN

        OPEN curupdconsodetal_todos2(vnoidpais, vnoidmarca, vnoidcanal);
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curupdconsodetal_todos2 BULK COLLECT
            INTO v_cod_pais,
                 v_cod_peri,
                 v_cod_clie,
                 v_num_lote,
                 v_cod_vent,
                 v_cod_cupo,
                 v_tip_posic,
                 v_ind_erro_sse,
                 v_usu_modi,
                 v_fec_modi LIMIT rows;

          EXIT WHEN v_row_count = curupdconsodetal_todos2%ROWCOUNT;
          v_row_count := curupdconsodetal_todos2%ROWCOUNT;

          -- Bulk bind of data in memory table...
          FOR j IN v_cod_pais.first .. v_cod_pais.last
          LOOP
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

            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupo(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupo(j)
                   AND cod_prog = psusuario
                   AND cod_pais = v_cod_pais(j);
            END;

          END LOOP;
        END LOOP;
        CLOSE curupdconsodetal_todos2;

      ELSE

        OPEN curupdconsodetal_todos;
        LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH curupdconsodetal_todos BULK COLLECT
            INTO v_cod_pais,
                 v_cod_peri,
                 v_cod_clie,
                 v_num_lote,
                 v_cod_vent,
                 v_cod_cupo,
                 v_tip_posic,
                 v_ind_erro_sse,
                 v_usu_modi,
                 v_fec_modi LIMIT rows;

          EXIT WHEN v_row_count = curupdconsodetal_todos%ROWCOUNT;
          v_row_count := curupdconsodetal_todos%ROWCOUNT;

          -- Bulk bind of data in memory table...
          FOR j IN v_cod_pais.first .. v_cod_pais.last
          LOOP
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

            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupo(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupo(j)
                   AND cod_prog = psusuario
                   AND cod_pais = v_cod_pais(j);
            END;

          END LOOP;
        END LOOP;
        CLOSE curupdconsodetal_todos;

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR LOV_PR_ACTUA_ERROR_NONIV_STO: ' ||
                              ls_sqlerrm);
  END lov_pr_actua_error_noniv_sto;

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_CANTI_STO
                      Validacion de Cupones LOVE que marca con error cuando
            hay mas de un cupon love solicitado en el periodo por una consultora,
            ejecutado desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_canti_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi,
             lem.cod_nivel AS cod_nivel
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             lov_equiv_matr        lem,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             sto_proce_docum_digit tmp,
             cup_consu_nueva       ccn
       WHERE det.cod_pais = pscodigopais
            --NUEVO
         AND ccn.cod_cons = det.cod_clie
         AND ccn.cod_prog = pscodigoprograma
            --
         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = pscodigoprograma
         AND lem.cod_venta = det.cod_vent

            -- Pedidos no facturados
            /*AND EXISTS (SELECT CAB.COD_CLIE
             FROM INT_SOLIC_CONSO_CABEC CAB
            WHERE CAB.COD_PAIS = DET.COD_PAIS
              AND CAB.COD_PERI = DET.COD_PERI
              AND CAB.COD_CLIE = DET.COD_CLIE
              AND CAB.NUM_LOTE = DET.NUM_LOTE
              AND CAB.IND_OCS_PROC = '0'
              AND CAB.IND_PROC_GP2 = '0')*/
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu

            /* AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/
         AND det.cod_peri = pscodigoperiodo
            --Que la consultora sea LOVE
            /*AND EXISTS (SELECT NULL
             FROM MAE_CLIEN_TIPO_SUBTI A, MAE_CLIEN_CLASI B, MAE_CLIEN C
            WHERE A.CLIE_OID_CLIE = C.OID_CLIE
              AND A.OID_CLIE_TIPO_SUBT = B.CTSU_OID_CLIE_TIPO_SUBT
              AND B.CLAS_OID_CLAS = (SELECT OID_CLAS
                           FROM MAE_LOVE_CLASI_PRINT
                          WHERE TIP_PROG = 'LOV')
              AND C.COD_CLIE = DET.COD_CLIE)*/
         AND c.cod_clie = det.cod_clie
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
            -- Trabaja con los cupones sin error, ya validados por el proceso normal de nuevas y por
            -- la validacion de nivel de LOVE
         AND det.ind_erro_sse = '0'

       ORDER BY det.cod_clie;

    CURSOR curupdconsodetal_todos IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi,
             lem.cod_nivel AS cod_nivel
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             lov_equiv_matr        lem,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = pscodigoprograma
         AND lem.cod_venta = det.cod_vent

            /* AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/
         AND det.cod_peri = pscodigoperiodo
            -- Trabaja con los cupones sin error, ya validados por el proceso normal de nuevas y por
            -- la validacion de nivel de LOVE
         AND det.ind_erro_sse = '0'
            -- Pedidos no facturados
            /*AND EXISTS (SELECT CAB.COD_CLIE
             FROM INT_SOLIC_CONSO_CABEC CAB
            WHERE CAB.COD_PAIS = DET.COD_PAIS
              AND CAB.COD_PERI = DET.COD_PERI
              AND CAB.COD_CLIE = DET.COD_CLIE
              AND CAB.NUM_LOTE = DET.NUM_LOTE
               -- flags de no facturado
              AND CAB.IND_OCS_PROC = '0'
              AND CAB.IND_PROC_GP2 = '0')*/
         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'

            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu

       ORDER BY det.cod_clie;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupon IS TABLE OF lov_equiv_matr.cod_cupon%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    TYPE t_cod_nivel IS TABLE OF lov_equiv_matr.cod_nivel%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_cod_cupon    t_cod_cupon;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    v_cod_nivel t_cod_nivel;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    ls_cod_clie int_solic_conso_detal.cod_clie%TYPE := '0';
    ls_count    NUMBER := 0;

    num_permitido NUMBER := 0;

  BEGIN

    IF psindicadortodos = 'N' THEN
      OPEN curupdconsodetal;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupon,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi,
               v_cod_nivel LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
        v_row_count := curupdconsodetal%ROWCOUNT;
        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Trabaja por cada quiebre de cliente
          IF ls_cod_clie <> v_cod_clie(j) THEN
            ls_cod_clie := v_cod_clie(j);
            ls_count    := 0;

            -- Parametro de unidades permitidas por nivel
            num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel_love(v_cod_nivel(j),
                                                                              v_cod_peri(j),
                                                                              pscodigoprograma);

          END IF;
          -- Acumula el numero de detalles LOVE Solicitados por una consultora
          ls_count := ls_count + 1;

          --IF ls_count <> 1 THEN -- Antes solo se permitia 1 cupon LOVE
          IF ls_count > num_permitido THEN
            -- Si solicita mas de un detalle LOVE en el pedido
            -- Marca como error al detalle
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

            -- Guarda el evento en el Log de movimientos LOVE
            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupon(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupon(j)
                   AND cod_prog = pscodigoprograma
                   AND cod_pais = v_cod_pais(j);
            END;
          END IF;
        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN curupdconsodetal_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupon,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi,
               v_cod_nivel LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal_todos%ROWCOUNT;
        v_row_count := curupdconsodetal_todos%ROWCOUNT;
        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Trabaja por cada quiebre de cliente
          IF ls_cod_clie <> v_cod_clie(j) THEN
            ls_cod_clie := v_cod_clie(j);
            ls_count    := 0;

            -- Parametro de unidades permitidas por nivel
            num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel_love(v_cod_nivel(j),
                                                                              v_cod_peri(j),
                                                                              pscodigoprograma);
          END IF;
          -- Acumula el numero de detalles LOVE Solicitados por una consultora
          ls_count := ls_count + 1;

          IF ls_count > num_permitido THEN
            --IF ls_count <> 1 THEN
            -- Si solicita mas de un detalle LOVE en el pedido
            -- Marca como error al detalle
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

            -- Guarda el evento en el Log de movimientos LOVE
            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupon(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupon(j)
                   AND cod_prog = pscodigoprograma
                   AND cod_pais = v_cod_pais(j);
            END;
          END IF;
        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);

  END lov_pr_actua_error_canti_sto;

  /**************************************************************************
  Descripcion       : LOV_PR_DESPA_CUPON_DEFAU_STO
                      Despacho Cupones por defecto, ejecutado desde la
            validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_despa_cupon_defau_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    ncantidad        NUMBER,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  ) IS
    CURSOR cursorinsdetaldefault IS
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             lm.cod_venta AS cod_venta,
             ld.cod_cupo AS cod_cupon,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             lov_cupon_defec       ld,
             lov_equiv_matr        lm,
             cup_consu_nueva       cn,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = ld.cod_pais
         AND ca.cod_peri = ld.cod_peri
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND lm.cod_pais = ld.cod_pais
         AND lm.cod_peri = ld.cod_peri
         AND lm.cod_nivel = ld.cod_nivel
         AND lm.cod_prog = ld.cod_prog -- se aumento
         AND lm.cod_cupon = ld.cod_cupo
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
         AND ca.ind_orig_cabe != '0'
            --Que la consultora sea LOVE
            /*AND EXISTS (SELECT NULL
             FROM MAE_CLIEN_TIPO_SUBTI A, MAE_CLIEN_CLASI B, MAE_CLIEN C
            WHERE A.CLIE_OID_CLIE = C.OID_CLIE
              AND A.OID_CLIE_TIPO_SUBT = B.CTSU_OID_CLIE_TIPO_SUBT
              AND B.CLAS_OID_CLAS = (SELECT OID_CLAS
                         FROM MAE_LOVE_CLASI_PRINT
                          WHERE TIP_PROG = 'LOV')
              AND C.COD_CLIE = CA.COD_CLIE)*/
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
         AND c.cod_clie = ca.cod_clie

         AND ca.ind_proc_gp2 = '0'
            -- PARA QUE SOLO TRABAJE CON LOS PEDIDOS VALIDOS
         AND ca.ind_erro_remp = '0'
            ------------------------
         AND ld.cod_prog = pscodigoprograma
         AND ld.cod_nivel =
             '0' ||
             (to_number((SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                            pscodigoperiodo)
                          FROM cup_consu_nueva n
                         WHERE n.cod_pais = ca.cod_pais
                           AND n.cod_cons = ca.cod_clie
                           AND n.cod_prog = pscodigoprograma)) - ncantidad)
            -- Y que no haya pedido ningun cupon LOVE
            -- o que no haya pedido un LOVE que no le correspondia
         AND NOT EXISTS (SELECT de.cod_clie,
                     de.cod_vent
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE de.cod_pais = em.cod_pais
                 AND de.cod_peri = em.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.cod_vent = em.cod_venta
                 AND de.cod_peri = ca.cod_peri
                 AND em.cod_prog = pscodigoprograma
                 AND de.num_lote = ca.num_lote
                 AND de.ind_erro_sse = '0')
      -- La consultora debe haber facturado en la campaña anterior
      /*AND EXISTS (SELECT NULL
            FROM CUP_CONSU_FACTU CF
             WHERE CF.COD_PAIS = CA.COD_PAIS
             AND CF.COD_CONS = CA.COD_CLIE
             AND CF.COD_PROG = PSCODIGOPROGRAMA
             AND CF.IND_CONS = '0')
      AND CF.COD_PAIS = CA.COD_PAIS
      AND CF.COD_CONS = CA.COD_CLIE
      AND CF.COD_PROG = PSCODIGOPROGRAMA
      AND CF.IND_CONS = '0'*/
      ;

    CURSOR cursorinsdetaldefault_todos IS
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             lm.cod_venta AS cod_venta,
             ld.cod_cupo AS cod_cupon,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             lov_cupon_defec       ld,
             lov_equiv_matr        lm,
             cup_consu_nueva       cn,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = ld.cod_pais
         AND ca.cod_peri = ld.cod_peri
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND lm.cod_pais = ld.cod_pais
         AND lm.cod_peri = ld.cod_peri
         AND lm.cod_nivel = ld.cod_nivel
         AND lm.cod_cupon = ld.cod_cupo
         AND lm.cod_prog = ld.cod_prog -- se aumento
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
         AND ca.ind_orig_cabe != '0'
         AND ca.ind_proc_gp2 = '0'
            -- PARA QUE SOLO TRABAJE CON LOS PEDIDOS VALIDOS
         AND ca.ind_erro_remp = '0'
         AND ld.cod_prog = pscodigoprograma
         AND ld.cod_nivel =
             '0' ||
             (to_number((SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                            pscodigoperiodo)
                          FROM cup_consu_nueva n
                         WHERE n.cod_pais = ca.cod_pais
                           AND n.cod_cons = ca.cod_clie
                           AND n.cod_prog = pscodigoprograma)) - ncantidad)
            -- Y que no haya pedido ningun cupon LOVE
            -- o que no haya pedido un LOVE que no le correspondia
         AND NOT EXISTS (SELECT de.cod_clie,
                     de.cod_vent
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE de.cod_pais = em.cod_pais
                 AND de.cod_peri = em.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.cod_vent = em.cod_venta
                 AND de.cod_peri = ca.cod_peri
                 AND em.cod_prog = pscodigoprograma
                 AND de.num_lote = ca.num_lote
                 AND de.ind_erro_sse = '0')
      -- La consultora debe haber facturado en la campaña anterior
      /*AND EXISTS (SELECT NULL
         FROM CUP_CONSU_FACTU CF
        WHERE CF.COD_PAIS = CA.COD_PAIS
          AND CF.COD_CONS = CA.COD_CLIE
          AND CF.COD_PROG = PSCODIGOPROGRAMA
          AND CF.IND_CONS = '0')
      AND CF.COD_PAIS = CA.COD_PAIS
      AND CF.COD_CONS = CA.COD_CLIE
      AND CF.COD_PROG = PSCODIGOPROGRAMA
      AND CF.IND_CONS = '0'*/
      ;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupo IS TABLE OF lov_cupon_defec.cod_cupo%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_cod_cupo      t_cod_cupo;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    IF psindicadortodos = 'N' THEN
      OPEN cursorinsdetaldefault;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH cursorinsdetaldefault BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_val_unid_dem,
               v_ind_erro_sse,
               v_ind_erro_rech,
               v_usu_digi,
               v_fec_digi,
               v_fec_soli LIMIT rows;

        EXIT WHEN v_row_count = cursorinsdetaldefault%ROWCOUNT;
        v_row_count := cursorinsdetaldefault%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          BEGIN
            INSERT INTO int_solic_conso_detal
              (cod_pais,
               cod_peri,
               cod_clie,
               cod_vent,
               tip_posic,
               val_unid_dem,
               sta_proc,
               usu_digi,
               fec_digi,
               num_lote,
               ind_erro_sse,
               ind_erro_rech,
               fec_soli,
               tpos_oid_tipo_posi,
               stpo_oid_subt_posi,
               oid_alma)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_vent(j),
               v_tip_posic(j),
               v_val_unid_dem(j),
               'L', -- las agregadas por el programa de Love - nuevas (CUPON LOVE x DEFAULT)
               v_usu_digi(j),
               v_fec_digi(j),
               v_num_lote(j),
               v_ind_erro_sse(j),
               v_ind_erro_rech(j),
               v_fec_soli(j),
               tipo_posicion,
               sub_tipo_posicion,
               psoidalmacen);
          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE int_solic_conso_detal d
                 SET d.ind_erro_sse = '0'
               WHERE cod_pais = v_cod_pais(j)
                 AND cod_peri = v_cod_peri(j)
                 AND cod_clie = v_cod_clie(j)
                 AND num_lote = v_num_lote(j)
                 AND cod_vent = v_cod_vent(j)
                 AND tip_posic = v_tip_posic(j);
          END;

          -- Solo debe haber un registro en el historico x campaña x consultora con motivo = '01'
          DELETE FROM lov_histo_movim lhm
           WHERE lhm.cod_pais = v_cod_pais(j)
             AND lhm.cod_prog = pscodigoprograma
             AND lhm.cod_peri = v_cod_peri(j)
             AND lhm.cod_cons = v_cod_clie(j)
             AND lhm.cod_cupo = v_cod_cupo(j)
          /*and lhm.COD_MOTI_ACCI = MOTIVO_INSERCION_DEFAULT*/
          ; -- Motivo Insercion x Default

          INSERT INTO lov_histo_movim
            (cod_pais,
             cod_peri,
             cod_cons,
             cod_cupo,
             cod_vent,
             num_lote,
             cod_moti_acci,
             cod_prog,
             usu_digi,
             fec_digi)
          VALUES
            (v_cod_pais(j),
             v_cod_peri(j),
             v_cod_clie(j),
             v_cod_cupo(j),
             v_cod_vent(j),
             v_num_lote(j),
             motivo_insercion_default, -- Motivo : Insercion x defecto
             pscodigoprograma,
             v_usu_digi(j),
             v_fec_digi(j));
        END LOOP;
      END LOOP;
      CLOSE cursorinsdetaldefault;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN cursorinsdetaldefault_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH cursorinsdetaldefault_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_val_unid_dem,
               v_ind_erro_sse,
               v_ind_erro_rech,
               v_usu_digi,
               v_fec_digi,
               v_fec_soli LIMIT rows;

        EXIT WHEN v_row_count = cursorinsdetaldefault_todos%ROWCOUNT;
        v_row_count := cursorinsdetaldefault_todos%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          BEGIN
            INSERT INTO int_solic_conso_detal
              (cod_pais,
               cod_peri,
               cod_clie,
               cod_vent,
               tip_posic,
               val_unid_dem,
               sta_proc,
               usu_digi,
               fec_digi,
               num_lote,
               ind_erro_sse,
               ind_erro_rech,
               fec_soli,
               tpos_oid_tipo_posi,
               stpo_oid_subt_posi,
               oid_alma)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_vent(j),
               v_tip_posic(j),
               v_val_unid_dem(j),
               'L', -- las agregadas por el programa de Love - nuevas (CUPON LOVE x DEFAULT)
               v_usu_digi(j),
               v_fec_digi(j),
               v_num_lote(j),
               v_ind_erro_sse(j),
               v_ind_erro_rech(j),
               v_fec_soli(j),
               tipo_posicion,
               sub_tipo_posicion,
               psoidalmacen);

          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE int_solic_conso_detal d
                 SET d.ind_erro_sse = '0'
               WHERE cod_pais = v_cod_pais(j)
                 AND cod_peri = v_cod_peri(j)
                 AND cod_clie = v_cod_clie(j)
                 AND num_lote = v_num_lote(j)
                 AND cod_vent = v_cod_vent(j)
                 AND tip_posic = v_tip_posic(j);
          END;
          -- Solo debe haber un registro en el historico x campaña x consultora con motivo = '01'
          DELETE FROM lov_histo_movim lhm
           WHERE lhm.cod_pais = v_cod_pais(j)
             AND lhm.cod_prog = pscodigoprograma
             AND lhm.cod_peri = v_cod_peri(j)
             AND lhm.cod_cons = v_cod_clie(j)
             AND lhm.cod_cupo = v_cod_cupo(j)
          /*and lhm.COD_MOTI_ACCI = MOTIVO_INSERCION_DEFAULT*/
          ; -- Motivo Insercion x Default

          INSERT INTO lov_histo_movim
            (cod_pais,
             cod_peri,
             cod_cons,
             cod_cupo,
             cod_vent,
             num_lote,
             cod_moti_acci,
             cod_prog,
             usu_digi,
             fec_digi)
          VALUES
            (v_cod_pais(j),
             v_cod_peri(j),
             v_cod_clie(j),
             v_cod_cupo(j),
             v_cod_vent(j),
             v_num_lote(j),
             motivo_insercion_default, -- Motivo : Insercion x defecto
             pscodigoprograma,
             v_usu_digi(j),
             v_fec_digi(j));
        END LOOP;
      END LOOP;
      CLOSE cursorinsdetaldefault_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
  END lov_pr_despa_cupon_defau_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_PROCE_DESPA_NUEVA_STO
                      Procedimiento principal que llama a los procedimientos que
                      se deben ejecutar antes de la facturacion que soporta la
                      configuracion de multiples programas desde la validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais    : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario       : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_proce_despa_nueva_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psusuario       VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2,
    pscodigocliente VARCHAR2
  ) IS
    CURSOR curprogramas IS
      SELECT cod_prog,
             nvl(ind_const, 0),
             ind_cons_prem,
             ind_cupo,
             num_vig,
             ind_acti_love,
             ind_acti_prim_pedi,
             ind_cons_prem_elec,
             nvl(ind_prog_obli, 0)
        FROM cup_prog_nueva_cupon
       WHERE cod_pais = pscodigopais;

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_cons_prem IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem%TYPE;
    TYPE t_ind_cupo IS TABLE OF cup_prog_nueva_cupon.ind_cupo%TYPE;
    TYPE t_num_vig IS TABLE OF cup_prog_nueva_cupon.num_vig%TYPE;
    TYPE t_ind_acti_love IS TABLE OF cup_prog_nueva_cupon.ind_acti_love%TYPE;
    TYPE t_ind_acti_prim_pedi IS TABLE OF cup_prog_nueva_cupon.ind_acti_prim_pedi%TYPE;
    TYPE t_ind_cons_prem_elec IS TABLE OF cup_prog_nueva_cupon.ind_cons_prem_elec%TYPE;
    TYPE t_ind_prog_obli IS TABLE OF cup_prog_nueva_cupon.ind_prog_obli%TYPE;

    v_cod_prog           t_cod_prog;
    v_ind_const          t_ind_const;
    v_ind_cons_prem      t_ind_cons_prem;
    v_ind_cupo           t_ind_cupo;
    v_num_vig            t_num_vig;
    v_ind_acti_love      t_ind_acti_love;
    v_ind_acti_prim_pedi t_ind_acti_prim_pedi;
    v_ind_cons_prem_elec t_ind_cons_prem_elec;
    v_ind_prog_obli      t_ind_prog_obli;

    v_row_count NUMBER := 0;
    rows        NATURAL := 1000;
    i           BINARY_INTEGER := 0;

    ncantidad        NUMBER := 0;
    lsestatuscliente VARCHAR2(2);
    lscodigoventa    VARCHAR2(15);
    lsnumlote        VARCHAR2(10);
    lsfechasoli      DATE;
    lsoidalmacen     VARCHAR2(10);
    lsoidalmacenda   VARCHAR2(10);
    lsoidalmacenel   VARCHAR2(10);

    lscodigonueva mae_clien.cod_clie%TYPE;

    lsnumeroperiodos bas_param_pais.val_para%TYPE;

    lscodigoperiodoanterior seg_perio_corpo.cod_peri%TYPE;
    lnoidperiodoanterior    cra_perio.oid_peri%TYPE;
  BEGIN

    SELECT pg.val_para
      INTO lsnumeroperiodos
      FROM seg_pais        p,
           nvs_param_gener pg
     WHERE p.cod_pais = pscodigopais
       AND pg.pais_oid_pais = p.oid_pais
       AND cod_para = '02';

    -----  Obtengo almacen para cupones
    BEGIN
      SELECT val_para
        INTO lsoidalmacen
        FROM nvs_param_gener
       WHERE cod_para = '03'
         AND est_regi = '1';
    EXCEPTION
      WHEN no_data_found THEN
        lsoidalmacen := NULL;
    END;

    -----  Obtengo almacen para despacho automático
    BEGIN
      SELECT val_para
        INTO lsoidalmacenda
        FROM nvs_param_gener
       WHERE cod_para = '04'
         AND est_regi = '1';
    EXCEPTION
      WHEN no_data_found THEN
        lsoidalmacenda := NULL;
    END;

    BEGIN
      SELECT val_para
        INTO lsoidalmacenel
        FROM nvs_param_gener
       WHERE cod_para = '05'
         AND est_regi = '1';
    EXCEPTION
      WHEN no_data_found THEN
        lsoidalmacenel := NULL;
    END;

    lscodigoperiodoanterior := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                pscodigoperiodo,
                                                                -lsnumeroperiodos);

    lnoidperiodoanterior := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodigoperiodoanterior);
    -- cargo las consultoras nuevas a una tabla temporal
    SELECT MIN(cl.cod_clie)
      INTO lscodigonueva
      FROM mae_clien             cl,
           mae_clien_histo_estat hi,
           mae_clien_datos_adici ad
     WHERE cl.cod_clie = pscodigocliente
       AND cl.oid_clie = hi.clie_oid_clie
       AND cl.oid_clie = ad.clie_oid_clie
       AND ((hi.esta_oid_esta_clie IN (2, 8) AND
           hi.perd_oid_peri >= lnoidperiodoanterior) OR
           ad.esta_oid_esta_clie IN (1, 7))
       AND EXISTS (SELECT NULL
              FROM mae_clien_tipo_subti mcts,
                   mae_subti_clien      msc,
                   mae_tipo_clien       mtc
             WHERE mcts.clie_oid_clie = cl.oid_clie
               AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
               AND mtc.cod_tipo_clie = '02'
               AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
               AND msc.cod_subt_clie = '04');

    IF lscodigonueva IS NOT NULL THEN
      DELETE gtt_cup_consu_nueva;
      INSERT INTO gtt_cup_consu_nueva
        (cod_clie,
         val_esta_clie)
        (SELECT mc.cod_clie,
                est.cod_esta_clie
           FROM mae_clien_datos_adici ad,
                mae_estat_clien       est,
                mae_clien             mc
          WHERE mc.cod_clie = pscodigocliente
            AND ad.clie_oid_clie = mc.oid_clie
            AND ad.esta_oid_esta_clie = est.oid_esta_clie
            AND est.cod_esta_clie IN ('01', '07')
            AND ad.ind_acti = 1
            AND EXISTS
          (SELECT NULL
                   FROM mae_clien_tipo_subti mcts,
                        mae_subti_clien      msc,
                        mae_tipo_clien       mtc
                  WHERE mcts.clie_oid_clie = ad.clie_oid_clie
                    AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                    AND mtc.cod_tipo_clie = '02'
                    AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                    AND msc.cod_subt_clie = '04'));

      OPEN curprogramas;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curprogramas BULK COLLECT
          INTO v_cod_prog,
               v_ind_const,
               v_ind_cons_prem,
               v_ind_cupo,
               v_num_vig,
               v_ind_acti_love,
               v_ind_acti_prim_pedi,
               v_ind_cons_prem_elec,
               v_ind_prog_obli LIMIT rows;
        EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
        v_row_count := curprogramas%ROWCOUNT;
        -- Recorro todos los programas creados para Nuevas
        FOR i IN v_cod_prog.first .. v_cod_prog.last
        LOOP
          IF (v_cod_prog(i) IS NOT NULL) THEN
            BEGIN
              -- Inicio cambio RCR PER-SiCC-2012-0202
              DELETE FROM gtt_cup_progr_uadmi;

              -- Se insertan las UAS x programa
              INSERT INTO gtt_cup_progr_uadmi
                (cod_pais,
                 cod_peri,
                 cod_prog,
                 cod_regi,
                 cod_zona,
                 cod_secc,
                 cod_terr)
                (SELECT pscodigopais,
                        pscodigoperiodo,
                        v_cod_prog(i),
                        zr.cod_regi,
                        zz.cod_zona,
                        NULL,
                        NULL
                   FROM zon_zona  zz,
                        zon_regio zr
                  WHERE zz.ind_acti = '1'
                    AND zr.oid_regi = zz.zorg_oid_regi
                    AND 0 = (SELECT COUNT(1)
                               FROM nvs_param_progr_unadm a
                              WHERE a.pnvs_cod_prog = v_cod_prog(i)
                                AND a.pais_cod_pais = pscodigopais
                                AND a.est_regi = '1')
                 UNION
                 SELECT pscodigopais,
                        pscodigoperiodo,
                        v_cod_prog(i),
                        a.cod_regi,
                        zz.cod_zona,
                        NULL,
                        NULL
                   FROM zon_zona              zz,
                        nvs_param_progr_unadm a
                  WHERE a.est_regi = '1'
                    AND a.pnvs_cod_prog = v_cod_prog(i)
                    AND a.pais_cod_pais = pscodigopais
                    AND a.cod_regi IS NOT NULL
                    AND a.cod_zona IS NULL
                       --AND zz.oid_zona = a.zzon_oid_zona
                    AND zz.zorg_oid_regi = a.zorg_oid_regi
                    AND zz.ind_acti = '1'
                 UNION
                 SELECT pscodigopais,
                        pscodigoperiodo,
                        v_cod_prog(i),
                        a.cod_regi,
                        a.cod_zona,
                        NULL,
                        NULL
                   FROM nvs_param_progr_unadm a
                  WHERE a.est_regi = '1'
                    AND a.pnvs_cod_prog = v_cod_prog(i)
                    AND a.pais_cod_pais = pscodigopais
                    AND a.cod_regi IS NOT NULL
                    AND a.cod_zona IS NOT NULL);
              -- Fin  cambio RCR PER-SiCC-2012-0202

              -- Procedimiento que resetea los cupones utilizados a las
              -- consultoras que reingresan al programa
              cup_pr_reset_consu_react_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente);

              -- Si el programa no es obligatorio, se ejecuta como siempre
              IF v_ind_prog_obli(i) = '0' THEN
                -- Inicializa a las consultoras en el programa
                cup_pr_carga_consu_nueva_sto(pscodigopais,
                                             pscodigoperiodo,
                                             v_cod_prog(i),
                                             v_ind_const(i),
                                             psusuario,
                                             pscodtipodocu,
                                             psnumeroproceso,
                                             pscodigocliente);
              ELSE
                -- Si el programa es obligatorio, se exige que se pida un premio electivo en el primer pedido
                -- para matricularlo en CUP_CONSU_NUEVA
                cup_pr_carga_consu_nueva_oblig(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               v_ind_const(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);

              END IF;

              -- Homologa los cupones ingresados
              cup_pr_homol_cupon_detal_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente,
                                           lsoidalmacen,
                                           lsoidalmacenel);

              -- Inserto todas las consultoras del programa que han pasado pedido
              -- en la tabla de facturacion
              cup_pr_carga_consu_factu_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente);
              -- Evalua indicadores de constancia para despacho de cupones del programa
              IF (v_ind_const(i) = '0') THEN
                -- Programa No Constancia (PRINT)
                cup_pr_carga_consu_antig_ncsto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               v_ind_const(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
              ELSIF (v_ind_const(i) = '1') THEN
                -- Programa Constancia (Cupones de Descto)
                cup_pr_carga_consu_antig_ctsto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               v_ind_const(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
              END IF;
              -- Evalua indicadores de constancia para despacho de premios del programa
              IF v_ind_cons_prem(i) = '0' THEN
                IF v_ind_acti_prim_pedi(i) = '1' THEN
                  -- No hay Constancia de Premios y exige haber pedido el kit en el primer pedido
                  cup_pr_despa_premi_produ_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenda);
                ELSE
                  -- No hay Constancia de Premios
                  cup_pr_despa_premi_nueva_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenda);
                END IF;
              ELSE
                IF v_ind_acti_prim_pedi(i) = '1' THEN
                  -- Si hay Constancia de Premios y exige haber pedido el kit en el primer pedido
                  cup_pr_despa_premi_conpr_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenda);
                ELSE
                  --Si hay Constancia de Premios
                  cup_pr_despa_premi_const_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenda);
                END IF;
              END IF;

              -- Despacha el KIT Segundo Pedido
              BEGIN
                SELECT (SELECT estat.cod_esta_clie
                          FROM mae_estat_clien estat
                         WHERE estat.oid_esta_clie = mae.esta_oid_esta_clie)
                  INTO lsestatuscliente
                  FROM mae_clien_datos_adici mae
                 WHERE mae.clie_oid_clie =
                       gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente);
              EXCEPTION
                WHEN no_data_found THEN
                  lsestatuscliente := '00';
              END;

              IF lsestatuscliente = '02' OR lsestatuscliente = '08' THEN

                BEGIN
                  SELECT desp.cod_venta
                    INTO lscodigoventa
                    FROM cup_desp_prod   desp,
                         cup_consu_nueva consu
                   WHERE desp.cod_pais = pscodigopais
                     AND desp.cod_prog = v_cod_prog(i)
                     AND desp.cod_peri = pscodigoperiodo
                     AND desp.cod_nivel = '02'
                     AND consu.cod_cons = pscodigocliente
                     AND consu.ind_orig_regi = '1'
                     AND desp.ind_prod_kit = '2';
                EXCEPTION
                  WHEN no_data_found THEN
                    lscodigoventa := NULL;
                END;

                IF lscodigoventa IS NOT NULL THEN
                  --Si se obtiene el CUV se inserta en el detalle del Pedido (INT_SOLIC_CONSO_DETAL)
                  BEGIN
                    SELECT fec_soli,
                           num_lote
                      INTO lsfechasoli,
                           lsnumlote
                      FROM int_solic_conso_cabec
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = pscodigoperiodo
                       AND cod_clie = pscodigocliente;

                    INSERT INTO int_solic_conso_detal
                      (cod_pais,
                       cod_peri,
                       cod_clie,
                       cod_vent,
                       tip_posic,
                       val_unid_dem,
                       sta_proc,
                       usu_digi,
                       fec_digi,
                       num_lote,
                       ind_erro_sse,
                       ind_erro_rech,
                       fec_soli,
                       oid_alma)
                    VALUES
                      (pscodigopais,
                       pscodigoperiodo,
                       pscodigocliente,
                       lscodigoventa,
                       'OC',
                       1,
                       'C',
                       psusuario,
                       SYSDATE,
                       lsnumlote,
                       0,
                       0,
                       lsfechasoli,
                       lsoidalmacenda);

                    -- se actualiza el Atributo IND_ORIG_REG A 2
                    UPDATE cup_consu_nueva
                       SET ind_orig_regi = '2'
                     WHERE cod_pais = pscodigopais
                       AND cod_prog = v_cod_prog(i)
                       AND cod_cons = pscodigocliente;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lsfechasoli := NULL;
                      lsnumlote   := '';
                  END;

                END IF;

              END IF;

              --Evalua Despacho de cupones desde el primer pedido
              IF (v_ind_cupo(i) = '1') THEN
                IF (v_ind_const(i) = '0') THEN
                  -- Actualiza carga de nivel Programa Sin Constancia
                  cup_pr_carga_nocon_nivel_prsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSIF (v_ind_const(i) = '1') THEN
                  -- Actualiza carga de nivel Programa Con Constancia
                  cup_pr_carga_consu_nivel_prsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                END IF;
              END IF;
              -- Inicializa los cupones para el periodo
              cup_pr_carga_consu_cupon_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente);
              -- Inicializa el detalle de los cupones para el periodo
              cup_pr_carga_detal_perio_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente,
                                           v_ind_const(i),
                                           v_ind_cons_prem_elec(i));
              -- Valida los cupones y marca con error los que no corresponden
              cup_pr_actua_ind_error_custo(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente,
                                           v_ind_const(i),
                                           v_ind_cons_prem_elec(i));
              -- Valida unidades solicitadas por nivel
              cup_pr_val_unid_nivel_sto(pscodigopais,
                                        pscodigoperiodo,
                                        v_cod_prog(i),
                                        psusuario,
                                        pscodtipodocu,
                                        psnumeroproceso,
                                        pscodigocliente);
              -- Actualiza Unidades fuera de Limite de Unidades para pedidos de cupones
              cup_pr_actua_unida_deman_sto(pscodigopais,
                                           pscodigoperiodo,
                                           v_cod_prog(i),
                                           psusuario,
                                           pscodtipodocu,
                                           psnumeroproceso,
                                           pscodigocliente);

              --------------
              IF v_ind_cupo(i) = '1' THEN
                ncantidad := 0;
              ELSE
                ncantidad := 1;
              END IF;
              --------------

              -- LOVE --
              IF v_ind_acti_love(i) = '1' THEN
                -- BORRO LOS CUPONES POR DEFAULT ANTES DE CADA CARGA
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  cup_pr_borra_cupon_defec_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 'N',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  cup_pr_borra_cupon_defec_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               'N',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
                END IF;
                --ERROR CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_actua_error_noniv_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'N',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  lov_pr_actua_error_noniv_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'N',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               v_ind_const(i),
                                               v_ind_cons_prem(i));
                END IF;
                -- ERROR MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_actua_error_canti_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'N',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  lov_pr_actua_error_canti_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'N',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
                END IF;

                -- AGREGA CUPON LOVE POR DEFECTO
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_despa_cupon_defau_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'N',
                                                 ncantidad,
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente,
                                                 lsoidalmacenel);
                ELSE
                  lov_pr_despa_cupon_defau_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'N',
                                               ncantidad,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenel);
                END IF;
              END IF;

              -- Se agrego para que LOVE se aplique a todo el pais
              IF v_ind_acti_love(i) = '2' THEN
                -- BORRO LOS CUPONES POR DEFAULT ANTES DE CADA CARGA
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  cup_pr_borra_cupon_defec_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 'S',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  cup_pr_borra_cupon_defec_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               'S',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
                END IF;
                --ERROR CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_actua_error_noniv_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'S',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  lov_pr_actua_error_noniv_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'S',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               v_ind_const(i),
                                               v_ind_cons_prem(i));
                END IF;
                -- ERROR MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_actua_error_canti_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'S',
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente);
                ELSE
                  lov_pr_actua_error_canti_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'S',
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente);
                END IF;
                -- AGREGA CUPON LOVE POR DEFECTO
                IF v_ind_cons_prem_elec(i) = '1' THEN
                  -- Exige constancia de pedidos
                  lov_pr_despa_cupon_defau_ctsto(pscodigopais,
                                                 pscodigoperiodo,
                                                 v_cod_prog(i),
                                                 psusuario,
                                                 'S',
                                                 ncantidad,
                                                 pscodtipodocu,
                                                 psnumeroproceso,
                                                 pscodigocliente,
                                                 lsoidalmacenel);
                ELSE
                  lov_pr_despa_cupon_defau_sto(pscodigopais,
                                               pscodigoperiodo,
                                               v_cod_prog(i),
                                               psusuario,
                                               'S',
                                               ncantidad,
                                               pscodtipodocu,
                                               psnumeroproceso,
                                               pscodigocliente,
                                               lsoidalmacenel);
                END IF;
              END IF;
              ----------
            END;
          END IF;
        END LOOP;
      END LOOP;
      CLOSE curprogramas;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_PROCE_DESPA_NUEVA_STO: ' ||
                              ls_sqlerrm);
  END cup_pr_proce_despa_nueva_sto;
  -----------------------------------------------------------------
  -----------------------------------------------------------------
  /**************************************************************************
  Descripcion       : CUP_PR_CIERR_ACTUA_PRIME_PEDID
                     Actualiza el IND_PROD a '1' a las consultoras nuevas o reactivadas
                     de la tabla cup_consu_nueva para indicar que solicito el producto
                     parametrizado para el primer pedido
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_cierr_actua_prime_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    -- Consultoras Nuevas y Reactivadas que tienen pedidos facturados
    CURSOR curupdconsul IS
      SELECT consul.cod_pais AS cod_pais,
             consul.cod_prog AS cod_programa,
             consul.cod_cons AS cod_consu,
             psusuario       AS usu_modi,
             SYSDATE         AS fec_modi
        FROM cup_consu_nueva     consul,
             gtt_cup_consu_nueva tmp
       WHERE consul.cod_pais = pscodigopais
         AND consul.cod_prog = pscodigoprograma
            -- que sea nueva
         AND consul.cod_cons = tmp.cod_clie
            -- consultora con ultima campaña igual al periodo actual
         AND consul.camp_fin_ccc = pscodigoperiodo
            --and consul.est_proc = '1'
         AND EXISTS (SELECT NULL
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_pais = consul.cod_pais
                 AND cab.cod_peri = consul.camp_fin_ccc
                 AND cab.cod_clie = consul.cod_cons
                    -- flags de facturado
                 AND cab.ind_ocs_proc = '1'
                 AND cab.ind_proc_gp2 = '1')
            -- Que el detalle contenga los productos parametrizados para primer pedido
         AND EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = consul.cod_pais
                 AND de.cod_peri = consul.camp_fin_ccc
                 AND de.cod_clie = consul.cod_cons
                 AND de.cod_vent IN
                     (SELECT ps.cod_vent
                        FROM cup_prod_solic ps
                       WHERE ps.cod_pais = consul.cod_pais
                         AND ps.cod_peri = consul.camp_fin_ccc
                         AND ps.cod_prog = consul.cod_prog
                         AND ps.sta_regi = '1'));

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_usu_modi IS TABLE OF cup_consu_nueva.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF cup_consu_nueva.fec_modi%TYPE;

    v_cod_pais  t_cod_pais;
    v_cod_prog  t_cod_prog;
    v_cod_consu t_cod_consu;
    v_usu_modi  t_usu_modi;
    v_fec_modi  t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN
    OPEN curupdconsul;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsul BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsul%ROWCOUNT;
      v_row_count := curupdconsul%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva cn
           SET cn.ind_prod = '1'
         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsul;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      cup_pr_log_error(ls_sqlerrm);
      raise_application_error(-20123,
                              'ERROR CUP_PR_CIERR_ACTUA_PRIME_PEDID: ' ||
                              ls_sqlerrm);
      RETURN;
  END cup_pr_cierr_actua_prime_pedid;

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_PRODU_STO
                      Despacho de Premios Consultoras Nuevas por Nivel, cuando
                      el indicador que exige solicitar el kit en el primer
                      pedido este encendido
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_produ_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  ) IS
    CURSOR curinsconsodetal IS
    -- todas las q son nuevas recibiran el mismo trato de siempre
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'

            -- Inicio cambio RCR PER-SiCC-2012-0202
            --Que no sea Regalo por Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- deben ser nuevas
         AND EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva tmp
               WHERE tmp.cod_clie = ca.cod_clie)
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta);

    CURSOR curinsconsodetalnonuevas IS
    -- todas las q no son nuevas deberan haber pedido el kit en su primer pedido
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             sto_proce_docum_digit tmp,
             cup_consu_nueva       cn
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'

            -- Inicio cambio RCR PER-SiCC-2012-0202
            --Que no sea Regalo por Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- No deben ser nuevas
         AND NOT EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva tmp
               WHERE tmp.cod_clie = ca.cod_clie)
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_prog = pscodigoprograma
            -- Debe haber pedido el kit en el primer pedido
         AND cn.ind_prod = '1'
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetal%ROWCOUNT;
      v_row_count := curinsconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(j),
           v_cod_peri(j),
           v_cod_clie(j),
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);

    END LOOP;
    CLOSE curinsconsodetal;

    OPEN curinsconsodetalnonuevas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetalnonuevas BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetalnonuevas%ROWCOUNT;
      v_row_count := curinsconsodetalnonuevas%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(i),
           v_cod_peri(i),
           v_cod_clie(i),
           v_cod_vent(i),
           v_tip_posic(i),
           v_val_unid_dem(i),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(i),
           v_fec_digi(i),
           v_num_lote(i),
           v_ind_erro_sse(i),
           v_ind_erro_rech(i),
           v_fec_soli(i),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);

    END LOOP;
    CLOSE curinsconsodetalnonuevas;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_despa_premi_produ_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_DESPA_PREMI_CONPR_STO
                    Despacho de Premios Consultoras Nuevas por Nivel cuando el
                    indicador de constancia esta activado, ejecutado desde la
          validacion de STO
  Fecha Creacion    : 14/04/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_despa_premi_conpr_sto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  )

   IS
    CURSOR curinsconsodetal IS
    -- todas las q son nuevas recibiran el mismo trato de siempre
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'

            -- Inicio cambio RCR PER-SiCC-2012-0202
            -- Que no sea Regalo x Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- deben ser nuevas
         AND EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva tmp
               WHERE tmp.cod_clie = ca.cod_clie)
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta)
            -- La consultora debe haber facturado en la campaña anterior
            /*AND EXISTS (SELECT NULL
             FROM CUP_CONSU_FACTU CF
            WHERE CF.COD_PAIS = CA.COD_PAIS
              AND CF.COD_CONS = CA.COD_CLIE
              AND CF.COD_PROG = PSCODIGOPROGRAMA
              AND CF.IND_CONS = '0')*/
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    CURSOR curinsconsodetalnonuevas IS
    -- todas las q no son nuevas deberan haber pedido el kit en su primer pedido
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             pr.cod_venta AS cod_venta,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             cup_desp_prod         pr,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp,
             cup_consu_nueva       cn
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = pr.cod_pais
         AND ca.cod_peri = pr.cod_peri
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_proc_gp2 = '0'

            -- Inicio cambio RCR PER-SiCC-2012-0202
            -- Que no sea Regalo x Pedido
         AND nvl(pr.ind_rega_pedi, '0') <> '1'
            -- Fin  cambio RCR PER-SiCC-2012-0202
            --RCR:PER-SiCC-2012-1055 - INICIO
         AND nvl(pr.ind_prod_kit, '0') <> '2'
            --RCR:PER-SiCC-2012-1055 - FIN
            -- No deben ser nuevas
         AND NOT EXISTS (SELECT NULL
                FROM gtt_cup_consu_nueva tmp
               WHERE tmp.cod_clie = ca.cod_clie)
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_prog = pscodigoprograma
            -- Debe haber pedido el kit en el primer pedido
         AND cn.ind_prod = '1'
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND pr.cod_prog = pscodigoprograma
         AND pr.sta_reg = 1
         AND pr.cod_nivel =
             (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                  pscodigoperiodo)
                FROM cup_consu_nueva n
               WHERE n.cod_pais = ca.cod_pais
                 AND n.cod_prog = pr.cod_prog
                 AND n.cod_cons = ca.cod_clie)
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal de
               WHERE de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                 AND de.cod_vent = pr.cod_venta)
            -- La consultora debe haber facturado en la campaña anterior
            /*AND EXISTS (SELECT NULL
             FROM CUP_CONSU_FACTU CF
            WHERE CF.COD_PAIS = CA.COD_PAIS
              AND CF.COD_CONS = CA.COD_CLIE
              AND CF.COD_PROG = PSCODIGOPROGRAMA
              AND CF.IND_CONS = '0')*/
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN curinsconsodetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetal BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetal%ROWCOUNT;
      v_row_count := curinsconsodetal%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(j),
           v_cod_peri(j),
           v_cod_clie(j),
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);

    END LOOP;
    CLOSE curinsconsodetal;

    OPEN curinsconsodetalnonuevas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsodetalnonuevas BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = curinsconsodetalnonuevas%ROWCOUNT;
      v_row_count := curinsconsodetalnonuevas%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO int_solic_conso_detal
          (cod_pais,
           cod_peri,
           cod_clie,
           cod_vent,
           tip_posic,
           val_unid_dem,
           sta_proc,
           usu_digi,
           fec_digi,
           num_lote,
           ind_erro_sse,
           ind_erro_rech,
           fec_soli,
           tpos_oid_tipo_posi,
           stpo_oid_subt_posi,
           oid_alma)
        VALUES
          (v_cod_pais(i),
           v_cod_peri(i),
           v_cod_clie(i),
           v_cod_vent(i),
           v_tip_posic(i),
           v_val_unid_dem(i),
           'C', -- las agregadas por el programa de nuevas (Print o Cupnes)
           v_usu_digi(i),
           v_fec_digi(i),
           v_num_lote(i),
           v_ind_erro_sse(i),
           v_ind_erro_rech(i),
           v_fec_soli(i),
           tipo_posicion,
           sub_tipo_posicion,
           psoidalmacen);

    END LOOP;
    CLOSE curinsconsodetalnonuevas;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;
  END cup_pr_despa_premi_conpr_sto;

  /**************************************************************************
  Descripcion       : CUP_PR_LOG_ERROR
                      Guarda Logs ante errores en una transaccion autonoma
  Fecha Creacion    : 18/03/2010
  Parametros Entrada:
      psCadena     : Cadena a insertar
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_log_error(pscadena IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    INSERT INTO int_tmp_cupon_equiv (cup_log_err) VALUES (pscadena);
    COMMIT;
  END cup_pr_log_error;

  /**************************************************************************
  Descripcion       : CUP_PR_BORRA_CUPON_DEFEC_CTSTO
                      Borra los cupones love adicionados por defecto, a las
                      consultoras que son constantes, ejecutado desde la
                      validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador si es a todo el pais o solo a un tipo de
                         clasificacion
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_borra_cupon_defec_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curdelete IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       cn,
             sto_proce_docum_digit tmp,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             cup_consu_factu       cf
       WHERE ca.ind_proc_gp2 = '0'
            -------------------------------------------------
         AND ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -------------------------------------------------
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
            -------------------------------------------------
            -- Que sea LOVE
         AND a.clie_oid_clie = c.oid_clie
         AND c.cod_clie = ca.cod_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    CURSOR curdelete_todos IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca,
             cup_consu_nueva       cn,
             sto_proce_docum_digit tmp,
             cup_consu_factu       cf
       WHERE ca.ind_proc_gp2 = '0'
            -------------------------------------------------
         AND ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -------------------------------------------------
         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    v_cod_pais t_cod_pais;
    v_cod_peri t_cod_peri;
    v_cod_clie t_cod_clie;
    v_num_lote t_num_lote;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN
    IF psindicadortodos = 'N' THEN
      OPEN curdelete;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curdelete BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote LIMIT rows;

        EXIT WHEN v_row_count = curdelete%ROWCOUNT;
        v_row_count := curdelete%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Cambio por STO
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = '1'
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = v_cod_clie(j)
             AND num_lote = v_num_lote(j)
             AND sta_proc = 'L';

          -- Borro el log de agregar el cupon por defecto
          DELETE FROM lov_histo_movim
           WHERE cod_peri = v_cod_peri(j)
             AND cod_cons = v_cod_clie(j)
             AND cod_moti_acci = motivo_insercion_default
             AND cod_prog = pscodigoprograma
             AND cod_pais = v_cod_pais(j);

        END LOOP;

      END LOOP;
      CLOSE curdelete;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN curdelete_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curdelete_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote LIMIT rows;

        EXIT WHEN v_row_count = curdelete_todos%ROWCOUNT;
        v_row_count := curdelete_todos%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP

          -- Borro del detalle el cupon por defecto
          UPDATE int_solic_conso_detal
             SET ind_erro_sse = '1'
           WHERE cod_pais = v_cod_pais(j)
             AND cod_peri = v_cod_peri(j)
             AND cod_clie = v_cod_clie(j)
             AND num_lote = v_num_lote(j)
             AND sta_proc = 'L';

          -- Borro el log de agregar el cupon por defecto
          DELETE FROM lov_histo_movim
           WHERE cod_peri = v_cod_peri(j)
             AND cod_cons = v_cod_clie(j)
             AND cod_moti_acci = motivo_insercion_default
             AND cod_prog = pscodigoprograma
             AND cod_pais = v_cod_pais(j);

        END LOOP;

      END LOOP;
      CLOSE curdelete_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
  END cup_pr_borra_cupon_defec_ctsto;

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_NONIV_STO
                      Validacion de Cupones LOVE que marca con error los cupones love
                      solicitado no corresponde al nivel de las consultoras que son
                      constantes. ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_noniv_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent

            -- Se quita el lote actual porque se deben procesar
            -- las consultoras que estan en la tabla TMP de STO,
            /*
            AND DET.NUM_LOTE = (SELECT NUM_LOTE
                                  FROM BAS_CTRL_FACT
                                 WHERE COD_PAIS = DET.COD_PAIS
                                   AND STA_CAMP = '0'
                                   AND IND_CAMP_ACT = '1')
            */

         AND det.cod_peri = pscodigoperiodo

         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
         AND c.cod_clie = det.cod_clie

         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'

            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu

         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

            --AND CN.COD_ULT_NIVEL != (SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
         AND cn.cod_ult_nivel !=
             (SELECT '0' || (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                       FROM cup_prog_nueva_cupon c
                                      WHERE c.cod_prog = pscodigoprograma),
                                     '0',
                                     1,
                                     0)))
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent)

            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = cab.cod_pais
         AND cf.cod_cons = cab.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    CURSOR curupdconsodetal_todos IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_detal det,
             cup_consu_nueva       cn,
             lov_equiv_matr        lem,
             int_solic_conso_cabec cab,
             lov_equiv_matr        equiv,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais
         AND cn.cod_pais = det.cod_pais
         AND cn.cod_prog = pscodigoprograma
         AND cn.cod_cons = det.cod_clie

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = cn.cod_prog
         AND lem.cod_venta = det.cod_vent

            -- Se quita el lote actual porque se deben procesar
            -- las consultoras que estan en la tabla TMP de STO,
            /*AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/

         AND det.cod_peri = pscodigoperiodo

         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'

            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu

         AND equiv.cod_pais = det.cod_pais
         AND equiv.cod_prog = pscodigoprograma
         AND equiv.cod_peri = det.cod_peri
         AND equiv.cod_venta = det.cod_vent

            --AND CN.COD_ULT_NIVEL !=(SELECT '0' || (TO_NUMBER(EQUIV.COD_NIVEL) + 1)
         AND cn.cod_ult_nivel !=
             (SELECT '0' || (to_number(equiv.cod_nivel) +
                     (decode((SELECT nvl(c.ind_cupo, '0')
                                       FROM cup_prog_nueva_cupon c
                                      WHERE c.cod_prog = pscodigoprograma),
                                     '0',
                                     1,
                                     0)))
                FROM lov_equiv_matr equiv
               WHERE equiv.cod_pais = det.cod_pais
                 AND equiv.cod_prog = pscodigoprograma
                 AND equiv.cod_peri = det.cod_peri
                 AND equiv.cod_venta = det.cod_vent)
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = cab.cod_pais
         AND cf.cod_cons = cab.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupon IS TABLE OF lov_equiv_matr.cod_cupon%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_cod_cupo     t_cod_cupon;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN
    IF psindicadortodos = 'N' THEN
      OPEN curupdconsodetal;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
        v_row_count := curupdconsodetal%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
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

          BEGIN
            INSERT INTO lov_histo_movim
              (cod_pais,
               cod_peri,
               cod_cons,
               cod_cupo,
               cod_vent,
               num_lote,
               cod_moti_acci,
               cod_prog,
               usu_digi,
               fec_digi)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_cupo(j),
               v_cod_vent(j),
               v_num_lote(j),
               motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
               pscodigoprograma,
               psusuario,
               SYSDATE);
          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE lov_histo_movim
                 SET cod_vent      = v_cod_vent(j),
                     num_lote      = v_num_lote(j),
                     cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                     usu_modi      = v_usu_modi(j),
                     fec_modi      = v_fec_modi(j)
               WHERE cod_peri = v_cod_peri(j)
                 AND cod_cons = v_cod_clie(j)
                 AND cod_cupo = v_cod_cupo(j)
                 AND cod_prog = psusuario
                 AND cod_pais = v_cod_pais(j);
          END;

        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN curupdconsodetal_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal_todos%ROWCOUNT;
        v_row_count := curupdconsodetal_todos%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
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

          BEGIN
            INSERT INTO lov_histo_movim
              (cod_pais,
               cod_peri,
               cod_cons,
               cod_cupo,
               cod_vent,
               num_lote,
               cod_moti_acci,
               cod_prog,
               usu_digi,
               fec_digi)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_cupo(j),
               v_cod_vent(j),
               v_num_lote(j),
               motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
               pscodigoprograma,
               psusuario,
               SYSDATE);
          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE lov_histo_movim
                 SET cod_vent      = v_cod_vent(j),
                     num_lote      = v_num_lote(j),
                     cod_moti_acci = motivo_error_no_nivel_cupon, -- Motivo : CUPON LOVE SOLICITADO NO CORRESPONDE AL NIVEL DE LA CONSULTORA
                     usu_modi      = v_usu_modi(j),
                     fec_modi      = v_fec_modi(j)
               WHERE cod_peri = v_cod_peri(j)
                 AND cod_cons = v_cod_clie(j)
                 AND cod_cupo = v_cod_cupo(j)
                 AND cod_prog = psusuario
                 AND cod_pais = v_cod_pais(j);
          END;

        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR LOV_PR_ACTUA_ERROR_NONIV_CTSTO: ' ||
                              ls_sqlerrm);
  END lov_pr_actua_error_noniv_ctsto;

  /**************************************************************************
  Descripcion       : LOV_PR_ACTUA_ERROR_CANTI_CTSTO
                      Validacion de Cupones LOVE que marca con error cuando
                      hay mas de un cupon love solicitado en el periodo por una consultora,
                      que sea constante. ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador Todos
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_actua_error_canti_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2
  ) IS
    CURSOR curupdconsodetal IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi,
             lem.cod_nivel AS cod_nivel -- nuevo
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             lov_equiv_matr        lem,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             sto_proce_docum_digit tmp,
             cup_consu_nueva       ccn,
             cup_consu_factu       cf
       WHERE det.cod_pais = pscodigopais
            --NUEVO
         AND ccn.cod_cons = det.cod_clie
         AND ccn.cod_prog = pscodigoprograma
            --
         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = pscodigoprograma
         AND lem.cod_venta = det.cod_vent

         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'

            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu

            -- Se quita el lote actual porque se deben procesar
            -- las consultoras que estan en la tabla TMP de STO,
            /*AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/

         AND det.cod_peri = pscodigoperiodo

         AND c.cod_clie = det.cod_clie
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
            -- Trabaja con los cupones sin error, ya validados por el proceso normal de nuevas y por
            -- la validacion de nivel de LOVE
         AND det.ind_erro_sse = '0'
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = cab.cod_pais
         AND cf.cod_cons = cab.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0'
       ORDER BY det.cod_clie;

    CURSOR curupdconsodetal_todos IS
      SELECT pscodigopais AS cod_pais,
             pscodigoperiodo AS cod_periodo,
             det.cod_clie AS cod_consu,
             det.num_lote AS num_lote,
             det.cod_vent AS cod_venta,
             lem.cod_cupon AS cod_cupon,
             det.tip_posic AS tip_posic,
             '1' AS ind_erro_sse,
             psusuario AS usu_modi,
             SYSDATE AS fec_modi,
             lem.cod_nivel AS cod_nivel -- nuevo
        FROM int_solic_conso_detal det,
             int_solic_conso_cabec cab,
             lov_equiv_matr        lem,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE det.cod_pais = pscodigopais

         AND lem.cod_pais = det.cod_pais
         AND lem.cod_peri = det.cod_peri
         AND lem.cod_prog = pscodigoprograma
         AND lem.cod_venta = det.cod_vent

            -- Se quita el lote actual porque se deben procesar
            -- las consultoras que estan en la tabla TMP de STO,
            /*AND DET.NUM_LOTE = (SELECT NUM_LOTE
             FROM BAS_CTRL_FACT
            WHERE COD_PAIS = DET.COD_PAIS
              AND STA_CAMP = '0'
              AND IND_CAMP_ACT = '1')*/
         AND det.cod_peri = pscodigoperiodo
            -- Trabaja con los cupones sin error, ya validados por el proceso normal de nuevas y por
            -- la validacion de nivel de LOVE
         AND det.ind_erro_sse = '0'

         AND cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
            ---------------------------------
         AND cab.ind_erro_rech = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_node = '0'

            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND cab.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = cab.sec_nume_docu
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = cab.cod_pais
         AND cf.cod_cons = cab.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0'

       ORDER BY det.cod_clie;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupon IS TABLE OF lov_equiv_matr.cod_cupon%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_usu_modi IS TABLE OF int_solic_conso_detal.usu_modi%TYPE;
    TYPE t_fec_modi IS TABLE OF int_solic_conso_detal.fec_modi%TYPE;
    TYPE t_cod_nivel IS TABLE OF lov_equiv_matr.cod_nivel%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_cod_cupon    t_cod_cupon;
    v_tip_posic    t_tip_posic;
    v_ind_erro_sse t_ind_erro_sse;
    v_usu_modi     t_usu_modi;
    v_fec_modi     t_fec_modi;
    v_cod_nivel    t_cod_nivel;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    ls_cod_clie int_solic_conso_detal.cod_clie%TYPE := '0';
    ls_count    NUMBER := 0;

    num_permitido NUMBER := 0;

  BEGIN

    IF psindicadortodos = 'N' THEN
      OPEN curupdconsodetal;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupon,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi,
               v_cod_nivel LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal%ROWCOUNT;
        v_row_count := curupdconsodetal%ROWCOUNT;
        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Trabaja por cada quiebre de cliente
          IF ls_cod_clie <> v_cod_clie(j) THEN
            ls_cod_clie := v_cod_clie(j);
            ls_count    := 0;

            -- Parametro de unidades permitidas por nivel
            num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel_love(v_cod_nivel(j),
                                                                              v_cod_peri(j),
                                                                              pscodigoprograma);

          END IF;
          -- Acumula el numero de detalles LOVE Solicitados por una consultora
          ls_count := ls_count + 1;

          IF ls_count > num_permitido THEN
            -- Si solicita mas de un detalle LOVE en el pedido
            -- Marca como error al detalle
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

            -- Guarda el evento en el Log de movimientos LOVE
            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupon(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupon(j)
                   AND cod_prog = pscodigoprograma
                   AND cod_pais = v_cod_pais(j);
            END;
          END IF;
        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN curupdconsodetal_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsodetal_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupon,
               v_tip_posic,
               v_ind_erro_sse,
               v_usu_modi,
               v_fec_modi,
               v_cod_nivel LIMIT rows;

        EXIT WHEN v_row_count = curupdconsodetal_todos%ROWCOUNT;
        v_row_count := curupdconsodetal_todos%ROWCOUNT;
        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Trabaja por cada quiebre de cliente
          IF ls_cod_clie <> v_cod_clie(j) THEN
            ls_cod_clie := v_cod_clie(j);
            ls_count    := 0;

            -- Parametro de unidades permitidas por nivel
            num_permitido := cup_pkg_prog_nuevas.cup_fn_dev_unidad_nivel_love(v_cod_nivel(j),
                                                                              v_cod_peri(j),
                                                                              pscodigoprograma);
          END IF;
          -- Acumula el numero de detalles LOVE Solicitados por una consultora
          ls_count := ls_count + 1;

          IF ls_count > num_permitido THEN
            --IF ls_count <> 1 THEN
            -- Si solicita mas de un detalle LOVE en el pedido
            -- Marca como error al detalle
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

            -- Guarda el evento en el Log de movimientos LOVE
            BEGIN
              INSERT INTO lov_histo_movim
                (cod_pais,
                 cod_peri,
                 cod_cons,
                 cod_cupo,
                 cod_vent,
                 num_lote,
                 cod_moti_acci,
                 cod_prog,
                 usu_digi,
                 fec_digi)
              VALUES
                (v_cod_pais(j),
                 v_cod_peri(j),
                 v_cod_clie(j),
                 v_cod_cupon(j),
                 v_cod_vent(j),
                 v_num_lote(j),
                 motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                 pscodigoprograma,
                 psusuario,
                 SYSDATE);
            EXCEPTION
              WHEN dup_val_on_index THEN
                UPDATE lov_histo_movim
                   SET cod_vent      = v_cod_vent(j),
                       num_lote      = v_num_lote(j),
                       cod_moti_acci = motivo_error_cantidad_cupon, -- Motivo : MAS DE UN CUPON LOVE SOLICITADO EN EL PERIODO
                       usu_modi      = v_usu_modi(j),
                       fec_modi      = v_fec_modi(j)
                 WHERE cod_peri = v_cod_peri(j)
                   AND cod_cons = v_cod_clie(j)
                   AND cod_cupo = v_cod_cupon(j)
                   AND cod_prog = pscodigoprograma
                   AND cod_pais = v_cod_pais(j);
            END;
          END IF;
        END LOOP;
      END LOOP;
      CLOSE curupdconsodetal_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);

  END lov_pr_actua_error_canti_ctsto;

  /**************************************************************************
  Descripcion       : LOV_PR_DESPA_CUPON_DEFAU_CTSTO
                      Despacho Cupones por defecto a las consultoras que son
                      constantes, ejecutado desde la validacion de STO
  Fecha Creacion    : 22/11/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psIndicadorTodos : Indicador todos
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE lov_pr_despa_cupon_defau_ctsto
  (
    pscodigopais     VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoprograma VARCHAR2,
    psusuario        VARCHAR2,
    psindicadortodos VARCHAR2,
    ncantidad        NUMBER,
    pscodtipodocu    VARCHAR2,
    psnumeroproceso  VARCHAR2,
    pscodigocliente  VARCHAR2,
    psoidalmacen     VARCHAR2
  ) IS
    CURSOR cursorinsdetaldefault IS
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             lm.cod_venta AS cod_venta,
             ld.cod_cupo AS cod_cupon,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             lov_cupon_defec       ld,
             lov_equiv_matr        lm,
             cup_consu_nueva       cn,
             mae_clien_tipo_subti  a,
             mae_clien_clasi       b,
             mae_clien             c,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = ld.cod_pais
         AND ca.cod_peri = ld.cod_peri
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND lm.cod_pais = ld.cod_pais
         AND lm.cod_peri = ld.cod_peri
         AND lm.cod_nivel = ld.cod_nivel
         AND lm.cod_cupon = ld.cod_cupo

            -- Inicio cambio RCR PER-SiCC-2012-0362
         AND lm.cod_prog = cn.cod_prog
            -- Fin cambio RCR PER-SiCC-2012-0362

         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
         AND ca.ind_orig_cabe != '0'
            --Que la consultora sea LOVE
         AND a.clie_oid_clie = c.oid_clie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.clas_oid_clas = (SELECT oid_clas
                                  FROM mae_love_clasi_print
                                 WHERE tip_prog = 'LOV')
         AND c.cod_clie = ca.cod_clie

         AND ca.ind_proc_gp2 = '0'
            -- PARA QUE SOLO TRABAJE CON LOS PEDIDOS VALIDOS
         AND ca.ind_erro_remp = '0'
            ------------------------
         AND ld.cod_prog = pscodigoprograma
         AND ld.cod_nivel =
             '0' ||
             (to_number((SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                            pscodigoperiodo)
                          FROM cup_consu_nueva n
                         WHERE n.cod_pais = ca.cod_pais
                           AND n.cod_cons = ca.cod_clie
                           AND n.cod_prog = pscodigoprograma)) - ncantidad)
            -- Y que no haya pedido ningun cupon LOVE
            -- o que no haya pedido un LOVE que no le correspondia
         AND NOT EXISTS (SELECT de.cod_clie,
                     de.cod_vent
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE de.cod_pais = em.cod_pais
                 AND de.cod_peri = em.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.cod_vent = em.cod_venta
                 AND de.cod_peri = ca.cod_peri
                 AND em.cod_prog = pscodigoprograma
                 AND de.num_lote = ca.num_lote
                 AND de.ind_erro_sse = '0')
            --------------------------------------------------
            --Que sea constante
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    CURSOR cursorinsdetaldefault_todos IS
      SELECT ca.cod_pais AS cod_pais,
             ca.cod_peri AS cod_periodo,
             ca.cod_clie AS cod_consu,
             ca.num_lote AS num_lote,
             lm.cod_venta AS cod_venta,
             ld.cod_cupo AS cod_cupon,
             'OC' AS tip_posic,
             '1' AS val_unid,
             '0' AS ind_erro_sse,
             '0' AS ind_erro_rech, --Indicador Error Rechazada (STO)
             psusuario AS usu_digi,
             SYSDATE AS fec_digi,
             ca.fec_soli
        FROM int_solic_conso_cabec ca,
             lov_cupon_defec       ld,
             lov_equiv_matr        lm,
             cup_consu_nueva       cn,
             cup_consu_factu       cf,
             sto_proce_docum_digit tmp
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
         AND ca.cod_pais = ld.cod_pais
         AND ca.cod_peri = ld.cod_peri
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
         AND lm.cod_pais = ld.cod_pais
         AND lm.cod_peri = ld.cod_peri
         AND lm.cod_nivel = ld.cod_nivel
         AND lm.cod_cupon = ld.cod_cupo

            -- Inicio cambio RCR PER-SiCC-2012-0362
         AND lm.cod_prog = cn.cod_prog
            -- Fin cambio RCR PER-SiCC-2012-0362

         AND cn.cod_pais = ca.cod_pais
         AND cn.cod_cons = ca.cod_clie
         AND cn.cod_prog = pscodigoprograma
         AND ca.ind_orig_cabe != '0'
         AND ca.ind_proc_gp2 = '0'
            -- PARA QUE SOLO TRABAJE CON LOS PEDIDOS VALIDOS
         AND ca.ind_erro_remp = '0'
         AND ld.cod_prog = pscodigoprograma
         AND ld.cod_nivel =
             '0' ||
             (to_number((SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_nocon(n.camp_ini_ccc,
                                                                            pscodigoperiodo)
                          FROM cup_consu_nueva n
                         WHERE n.cod_pais = ca.cod_pais
                           AND n.cod_cons = ca.cod_clie
                           AND n.cod_prog = pscodigoprograma)) - ncantidad)
            -- Y que no haya pedido ningun cupon LOVE
            -- o que no haya pedido un LOVE que no le correspondia
         AND NOT EXISTS (SELECT de.cod_clie,
                     de.cod_vent
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE de.cod_pais = em.cod_pais
                 AND de.cod_peri = em.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.cod_vent = em.cod_venta
                 AND de.cod_peri = ca.cod_peri
                 AND em.cod_prog = pscodigoprograma
                 AND de.num_lote = ca.num_lote
                 AND de.ind_erro_sse = '0')
            --------------------------------------------------
            -- Que sea constante
         AND cf.cod_pais = ca.cod_pais
         AND cf.cod_cons = ca.cod_clie
         AND cf.cod_prog = pscodigoprograma
         AND cf.ind_cons = '0';

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_cod_cupo IS TABLE OF lov_cupon_defec.cod_cupo%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_sse IS TABLE OF int_solic_conso_detal.ind_erro_sse%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;
    TYPE t_usu_digi IS TABLE OF int_solic_conso_detal.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_detal.fec_digi%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_detal.fec_soli%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_cod_cupo      t_cod_cupo;
    v_tip_posic     t_tip_posic;
    v_ind_erro_sse  t_ind_erro_sse;
    v_ind_erro_rech t_ind_erro_rech;
    v_val_unid_dem  t_val_unid_dem;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000; -- Number of rows to process at a time
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    IF psindicadortodos = 'N' THEN
      OPEN cursorinsdetaldefault;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH cursorinsdetaldefault BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_val_unid_dem,
               v_ind_erro_sse,
               v_ind_erro_rech,
               v_usu_digi,
               v_fec_digi,
               v_fec_soli LIMIT rows;

        EXIT WHEN v_row_count = cursorinsdetaldefault%ROWCOUNT;
        v_row_count := cursorinsdetaldefault%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          BEGIN
            INSERT INTO int_solic_conso_detal
              (cod_pais,
               cod_peri,
               cod_clie,
               cod_vent,
               tip_posic,
               val_unid_dem,
               sta_proc,
               usu_digi,
               fec_digi,
               num_lote,
               ind_erro_sse,
               ind_erro_rech,
               fec_soli,
               tpos_oid_tipo_posi,
               stpo_oid_subt_posi,
               oid_alma)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_vent(j),
               v_tip_posic(j),
               v_val_unid_dem(j),
               'L', -- las agregadas por el programa de Love - nuevas (CUPON LOVE x DEFAULT)
               v_usu_digi(j),
               v_fec_digi(j),
               v_num_lote(j),
               v_ind_erro_sse(j),
               v_ind_erro_rech(j),
               v_fec_soli(j),
               tipo_posicion,
               sub_tipo_posicion,
               psoidalmacen);
          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE int_solic_conso_detal d
                 SET d.ind_erro_sse = '0'
               WHERE cod_pais = v_cod_pais(j)
                 AND cod_peri = v_cod_peri(j)
                 AND cod_clie = v_cod_clie(j)
                 AND num_lote = v_num_lote(j)
                 AND cod_vent = v_cod_vent(j)
                 AND tip_posic = v_tip_posic(j);
          END;

          -- Solo debe haber un registro en el historico x campaña x consultora con motivo = '01'
          DELETE FROM lov_histo_movim lhm
           WHERE lhm.cod_pais = v_cod_pais(j)
             AND lhm.cod_prog = pscodigoprograma
             AND lhm.cod_peri = v_cod_peri(j)
             AND lhm.cod_cons = v_cod_clie(j)
             AND lhm.cod_cupo = v_cod_cupo(j)
          /*and lhm.COD_MOTI_ACCI = MOTIVO_INSERCION_DEFAULT*/
          ; -- Motivo Insercion x Default

          INSERT INTO lov_histo_movim
            (cod_pais,
             cod_peri,
             cod_cons,
             cod_cupo,
             cod_vent,
             num_lote,
             cod_moti_acci,
             cod_prog,
             usu_digi,
             fec_digi)
          VALUES
            (v_cod_pais(j),
             v_cod_peri(j),
             v_cod_clie(j),
             v_cod_cupo(j),
             v_cod_vent(j),
             v_num_lote(j),
             motivo_insercion_default, -- Motivo : Insercion x defecto
             pscodigoprograma,
             v_usu_digi(j),
             v_fec_digi(j));
        END LOOP;
      END LOOP;
      CLOSE cursorinsdetaldefault;
    END IF;

    IF psindicadortodos = 'S' THEN
      OPEN cursorinsdetaldefault_todos;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH cursorinsdetaldefault_todos BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote,
               v_cod_vent,
               v_cod_cupo,
               v_tip_posic,
               v_val_unid_dem,
               v_ind_erro_sse,
               v_ind_erro_rech,
               v_usu_digi,
               v_fec_digi,
               v_fec_soli LIMIT rows;

        EXIT WHEN v_row_count = cursorinsdetaldefault_todos%ROWCOUNT;
        v_row_count := cursorinsdetaldefault_todos%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          BEGIN
            INSERT INTO int_solic_conso_detal
              (cod_pais,
               cod_peri,
               cod_clie,
               cod_vent,
               tip_posic,
               val_unid_dem,
               sta_proc,
               usu_digi,
               fec_digi,
               num_lote,
               ind_erro_sse,
               ind_erro_rech,
               fec_soli,
               tpos_oid_tipo_posi,
               stpo_oid_subt_posi,
               oid_alma)
            VALUES
              (v_cod_pais(j),
               v_cod_peri(j),
               v_cod_clie(j),
               v_cod_vent(j),
               v_tip_posic(j),
               v_val_unid_dem(j),
               'L', -- las agregadas por el programa de Love - nuevas (CUPON LOVE x DEFAULT)
               v_usu_digi(j),
               v_fec_digi(j),
               v_num_lote(j),
               v_ind_erro_sse(j),
               v_ind_erro_rech(j),
               v_fec_soli(j),
               tipo_posicion,
               sub_tipo_posicion,
               psoidalmacen);

          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE int_solic_conso_detal d
                 SET d.ind_erro_sse = '0'
               WHERE cod_pais = v_cod_pais(j)
                 AND cod_peri = v_cod_peri(j)
                 AND cod_clie = v_cod_clie(j)
                 AND num_lote = v_num_lote(j)
                 AND cod_vent = v_cod_vent(j)
                 AND tip_posic = v_tip_posic(j);
          END;
          -- Solo debe haber un registro en el historico x campaña x consultora con motivo = '01'
          DELETE FROM lov_histo_movim lhm
           WHERE lhm.cod_pais = v_cod_pais(j)
             AND lhm.cod_prog = pscodigoprograma
             AND lhm.cod_peri = v_cod_peri(j)
             AND lhm.cod_cons = v_cod_clie(j)
             AND lhm.cod_cupo = v_cod_cupo(j)
          /*and lhm.COD_MOTI_ACCI = MOTIVO_INSERCION_DEFAULT*/
          ; -- Motivo Insercion x Default

          INSERT INTO lov_histo_movim
            (cod_pais,
             cod_peri,
             cod_cons,
             cod_cupo,
             cod_vent,
             num_lote,
             cod_moti_acci,
             cod_prog,
             usu_digi,
             fec_digi)
          VALUES
            (v_cod_pais(j),
             v_cod_peri(j),
             v_cod_clie(j),
             v_cod_cupo(j),
             v_cod_vent(j),
             v_num_lote(j),
             motivo_insercion_default, -- Motivo : Insercion x defecto
             pscodigoprograma,
             v_usu_digi(j),
             v_fec_digi(j));
        END LOOP;
      END LOOP;
      CLOSE cursorinsdetaldefault_todos;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
  END lov_pr_despa_cupon_defau_ctsto;

  /**************************************************************************
  Descripcion        : CUP_FN_DEV_UNIDAD_NIVEL_LOVE
                        Devuelve el valor permitido de unidades LOVE a depachar
                        para un nivel
  Fecha Creacion     : 31/07/2008
  Parametros Entrada:
      psCodNivel    : Codigo Nivel
      psCodPeriodo  : Codigo Periodo
      psCodPrograma : Codigo programa
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION cup_fn_dev_unidad_nivel_love
  (
    pscodnivel    VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodprograma VARCHAR2
  ) RETURN NUMBER IS
    val_nivel NUMBER;
  BEGIN
    SELECT c.val_unid_prem_elec
      INTO val_nivel
      FROM cup_perio_nivel c
     WHERE c.cod_nive = pscodnivel
       AND c.cod_peri = pscodperiodo
       AND c.cod_prog = pscodprograma;

    RETURN nvl(val_nivel, 1);
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;

  END cup_fn_dev_unidad_nivel_love;

  /**************************************************************************
  Descripcion       : CUP_PR_CARGA_CONSU_NUEVA_OBLIG
                    Registra o actualiza la informacion de una consultora nueva,
                    con el programa que le corresponda segun su UA, para el caso en
                    que es obligatorio solicitar un premio electivo en 1er pedido.
                    ejecutado desde la validacion de STO
  Fecha Creacion    : 27/12/2010
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
      psCodigoPrograma : Codigo de programa
      psUsuario        : Codigo de Usuario
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_carga_consu_nueva_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigoprograma      VARCHAR2,
    psindicadorconstancia VARCHAR2,
    psusuario             VARCHAR2,
    pscodtipodocu         VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigocliente       VARCHAR2
  ) IS
    -- Consultoras que NO estan en cup_consu_nueva y q son nuevas (pasan su priemr pedido)
    CURSOR curinsconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_digi,
             SYSDATE AS fec_digi
        FROM int_solic_conso_cabec ca,
             -------------------------
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu,
             gtt_cup_consu_nueva   gtt,
             sto_proce_docum_digit tmp,
             cup_prog_nueva_cupon  prg
      --------------------------
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -----------------------------------
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
            ---------------
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
            ---------------
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona

         AND prg.cod_prog = pscodigoprograma
         AND prg.cam_inic <= pscodigoperiodo
         AND prg.cam_fin >= pscodigoperiodo

         AND NOT EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva      nu,
                     cup_prog_nueva_cupon prg1
               WHERE nu.cod_pais = ca.cod_pais
                    --AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie
                 AND prg1.cod_prog = nu.cod_prog
                 AND prg1.cam_inic <= pscodigoperiodo
                 AND prg1.cam_fin >= pscodigoperiodo)
         AND gtt.cod_clie = ca.cod_clie
         AND gtt.val_esta_clie IN ('01', '07')
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0'

            -- Exige que se solicite al menos un premio electivo del nivel 1 en su primer pedido
         AND EXISTS (SELECT NULL
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE em.cod_prog = pscodigoprograma
                 AND em.cod_nivel = '01' -- Nivel 1 del programa de premios electivos
                 AND em.cod_pais = de.cod_pais
                 AND em.cod_cupon = de.cod_vent
                 AND em.cod_peri = de.cod_peri
                 AND de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                    --El Premio Electivo deber ser KIT
                 AND em.ind_prod_kit = 1);

    -- Consultoras que SI estan en CUP_CONSU_NUEVA y son nuevas
    CURSOR curupdconsulnueva IS
      SELECT ca.cod_pais AS cod_pais,
             pscodigoprograma AS cod_prog,
             ca.cod_clie AS cod_consu,
             '01' AS cod_nivel, -- consultoras inician en el primer nivel
             ca.cod_peri AS cam_ini,
             ca.cod_peri AS cam_fin,
             psindicadorconstancia AS ind_const,
             '0' AS est_reg, -- se setea a 0 cuando se recepciona, cuando factura se pone a 1
             psusuario AS usu_modi,
             SYSDATE AS fec_modi
        FROM int_solic_conso_cabec ca,
             -------------------------
             mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             gtt_cup_progr_uadmi   cppu,
             gtt_cup_consu_nueva   gtt,
             sto_proce_docum_digit tmp,
             cup_prog_nueva_cupon  prg
      --------------------------
       WHERE ca.cod_pais = pscodigopais
         AND ca.cod_peri = pscodigoperiodo
            -- JOIN STO
         AND tmp.num_lote = ca.num_lote
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND tmp.num_proc = psnumeroproceso
         AND ca.cod_clie = pscodigocliente
         AND tmp.sec_nume_docu = ca.sec_nume_docu
            -----------------------------------
         AND zon_terri_admin.oid_terr_admi =
             mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
         AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND mae_clien_unida_admin.ind_acti = '1'
         AND mae_clien.cod_clie = ca.cod_clie
            ---------------
         AND cppu.cod_prog = pscodigoprograma
         AND cppu.cod_pais = pscodigopais
            ---------------
         AND cppu.cod_peri = ca.cod_peri
         AND cppu.cod_regi = zon_regio.cod_regi
         AND cppu.cod_zona = zon_zona.cod_zona
         AND prg.cod_prog = pscodigoprograma
         AND prg.cam_inic <= pscodigoperiodo
         AND prg.cam_fin >= pscodigoperiodo
            -----------------------------------
            -- no se toma en cuenta la fecha de solic para multiples recepciones 14Dic2007
         AND EXISTS (SELECT nu.cod_cons
                FROM cup_consu_nueva nu
               WHERE nu.cod_pais = ca.cod_pais
                 AND nu.cod_prog = pscodigoprograma
                 AND nu.cod_cons = ca.cod_clie)
         AND gtt.cod_clie = ca.cod_clie
         AND gtt.val_esta_clie IN ('01', '07')
         AND ca.ind_proc_gp2 = '0'
         AND ca.ind_ocs_proc = '0'
         AND ca.ind_erro_rech = '0'
         AND ca.ind_erro_remp = '0'
         AND ca.ind_erro_node = '0'

            -- Exige que se solicite al menos un premio electivo del nivel 1 en su primer pedido
         AND EXISTS (SELECT NULL
                FROM int_solic_conso_detal de,
                     lov_equiv_matr        em
               WHERE em.cod_prog = pscodigoprograma
                 AND em.cod_nivel = '01' -- Nivel 1 del programa de premios electivos
                 AND em.cod_pais = de.cod_pais
                 AND em.cod_cupon = de.cod_vent
                 AND em.cod_peri = de.cod_peri
                 AND de.cod_pais = ca.cod_pais
                 AND de.cod_peri = ca.cod_peri
                 AND de.cod_clie = ca.cod_clie
                 AND de.num_lote = ca.num_lote
                    --el premio electivo debe ser un KIT
                 AND em.ind_prod_kit = 1);

    TYPE t_cod_pais IS TABLE OF cup_consu_nueva.cod_pais%TYPE;
    TYPE t_cod_prog IS TABLE OF cup_consu_nueva.cod_prog%TYPE;
    TYPE t_cod_consu IS TABLE OF cup_consu_nueva.cod_cons%TYPE;
    TYPE t_cod_ult_nivel IS TABLE OF cup_consu_nueva.cod_ult_nivel%TYPE;
    TYPE t_cam_prime_pedid IS TABLE OF cup_consu_nueva.camp_ini_ccc%TYPE;
    TYPE t_cam_ultim_pedid IS TABLE OF cup_consu_nueva.camp_fin_ccc%TYPE;
    TYPE t_est_proc IS TABLE OF cup_consu_nueva.est_proc%TYPE;
    TYPE t_ind_const IS TABLE OF cup_consu_nueva.ind_const%TYPE;
    TYPE t_usu_digi IS TABLE OF cup_consu_nueva.usu_digi%TYPE;
    TYPE t_fec_digi IS TABLE OF cup_consu_nueva.fec_digi%TYPE;

    v_cod_pais        t_cod_pais;
    v_cod_prog        t_cod_prog;
    v_cod_consu       t_cod_consu;
    v_cod_ult_nivel   t_cod_ult_nivel;
    v_cam_prime_pedid t_cam_prime_pedid;
    v_cam_ultim_pedid t_cam_ultim_pedid;
    v_ind_const       t_ind_const;
    v_est_proc        t_est_proc;
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

    OPEN curupdconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_modi,
             v_fec_modi LIMIT rows;

      EXIT WHEN v_row_count = curupdconsulnueva%ROWCOUNT;
      v_row_count := curupdconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_cod_pais.count
        UPDATE cup_consu_nueva
           SET -- Se guardan historicos
               camp_ini_ccc_hist = camp_ini_ccc,
               camp_fin_ccc_hist  = camp_fin_ccc,
               cod_ult_nivel_hist = cod_ult_nivel,

               camp_ini_ccc  = v_cam_prime_pedid(j),
               camp_fin_ccc  = v_cam_ultim_pedid(j),
               cod_ult_nivel = v_cod_ult_nivel(j),
               est_proc      = v_est_proc(j),
               usu_modi      = v_usu_modi(j),
               fec_modi      = v_fec_modi(j)

         WHERE cod_pais = v_cod_pais(j)
           AND cod_prog = v_cod_prog(j)
           AND cod_cons = v_cod_consu(j);

    END LOOP;
    CLOSE curupdconsulnueva;

    -- Inserta en SSE_CONSU_SESIO_EXPER
    OPEN curinsconsulnueva;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsulnueva BULK COLLECT
        INTO v_cod_pais,
             v_cod_prog,
             v_cod_consu,
             v_cod_ult_nivel,
             v_cam_prime_pedid,
             v_cam_ultim_pedid,
             v_ind_const,
             v_est_proc,
             v_usu_digi,
             v_fec_digi LIMIT rows;

      EXIT WHEN v_row_count_ins = curinsconsulnueva%ROWCOUNT;
      v_row_count_ins := curinsconsulnueva%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_pais.count
        INSERT INTO cup_consu_nueva
          (cod_pais,
           cod_prog,
           cod_cons,
           cod_ult_nivel,
           camp_ini_ccc,
           camp_fin_ccc,
           est_proc,
           ind_const,
           usu_digi,
           fec_digi,
           -- Se guardan historicos
           camp_ini_ccc_hist,
           camp_fin_ccc_hist,
           cod_ult_nivel_hist)
        VALUES
          (v_cod_pais(i),
           v_cod_prog(i),
           v_cod_consu(i),
           v_cod_ult_nivel(i),
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_est_proc(i),
           v_ind_const(i),
           v_usu_digi(i),
           v_fec_digi(i),
           -- Se guardan historicos
           v_cam_prime_pedid(i),
           v_cam_ultim_pedid(i),
           v_cod_ult_nivel(i));

    END LOOP;
    CLOSE curinsconsulnueva;

  EXCEPTION
    WHEN OTHERS THEN

      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RETURN;

  END cup_pr_carga_consu_nueva_oblig;

  /**************************************************************************
  Descripcion       : CUP_PR_INSER_MENSA_NUEVA
                      Inserta mensajes del programa de nuevas para el paquete
                      documentario
  Fecha Creacion    : 07/02/2011
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE cup_pr_inser_mensa_nueva
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodtipodocu   VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS

    lsperiodoant1 seg_perio_corpo.cod_peri%TYPE;
    lsperiodoant2 seg_perio_corpo.cod_peri%TYPE;
    lsperiodoant3 seg_perio_corpo.cod_peri%TYPE;

  BEGIN

    lsperiodoant1 := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                      pscodigoperiodo,
                                                      -1);
    lsperiodoant2 := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                      pscodigoperiodo,
                                                      -2);
    lsperiodoant3 := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                      pscodigoperiodo,
                                                      -3);

    --- carga mesajes para consultoras 1er pedido ---
    INSERT INTO msg_buzon_mensa
      (oid_buzo_mens,
       num_secu,
       dato_vari_10,
       dato_vari_11,
       dato_vari_12,
       dato_vari_13,
       dato_vari_14,
       dato_vari_15,
       dato_vari_16,
       dato_vari_17,
       dato_vari_18,
       dato_vari_19,
       dato_vari_20,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie,
       val_nom2_clie,
       val_ape1_clie,
       val_ape2_clie,
       val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       dato_vari_06,
       dato_vari_07,
       dato_vari_08,
       dato_vari_09,
       num_lote_impr,
       fec_grab,
       fec_impr,
       ind_list_cons,
       peri_oid_peri,
       ind_acti)
      (SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'PRIMER PEDIDO',
              'DV11',
              'DV12',
              'DV13',
              'DV14',
              'DV15',
              'DV16',
              'DV17',
              'DV18',
              'DV19',
              'DV20',
              NULL,
              base.clie_oid_clie,
              (SELECT a.oid_mens FROM msg_mensa a WHERE a.cod_mens = 'NVA01'),
              13,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_prog,
              base.camp_ini_ccc,
              NULL,
              NULL,
              NULL,
              NULL,
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              1,
              NULL,
              1
         FROM (SELECT DISTINCT sc.clie_oid_clie,
                               mc.val_nom1 AS nom1,
                               mc.val_nom2 AS nom2,
                               mc.val_ape1 AS ape1,
                               mc.val_ape2 AS ape2,
                               mc.val_apel_casa AS apec,
                               mc.val_nom1 || ' ' || val_nom2 || ' ' ||
                               val_ape1 || ' ' || val_ape2 AS nombre,
                               nu.cod_prog,
                               nu.camp_ini_ccc
                 FROM int_solic_conso_cabec sc,
                      mae_clien             mc,
                      cup_consu_nueva       nu,
                      cup_prog_nueva_cupon  pn,
                      sto_proce_docum_digit occ
                WHERE sc.sec_nume_docu = occ.sec_nume_docu
                  AND sc.num_lote = occ.num_lote
                  AND occ.cod_tipo_docu = pscodtipodocu
                  AND occ.cod_pais = pscodigopais
                  AND occ.num_proc = psnumeroproceso
                  AND sc.cod_clie = mc.cod_clie
                  AND sc.cod_pais = nu.cod_pais
                  AND nu.cod_pais = pscodigopais
                  AND sc.cod_clie = nu.cod_cons
                  AND nu.camp_ini_ccc = pscodigoperiodo
                  AND nu.cod_prog = pn.cod_prog
                  AND pn.cod_pais = nu.cod_pais
                  AND pn.ind_gene_mens = '1') base);

    --- Carga mensaje consultoras 2do pedido ---
    INSERT INTO msg_buzon_mensa
      (oid_buzo_mens,
       num_secu,
       dato_vari_10,
       dato_vari_11,
       dato_vari_12,
       dato_vari_13,
       dato_vari_14,
       dato_vari_15,
       dato_vari_16,
       dato_vari_17,
       dato_vari_18,
       dato_vari_19,
       dato_vari_20,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie,
       val_nom2_clie,
       val_ape1_clie,
       val_ape2_clie,
       val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       dato_vari_06,
       dato_vari_07,
       dato_vari_08,
       dato_vari_09,
       num_lote_impr,
       fec_grab,
       fec_impr,
       ind_list_cons,
       peri_oid_peri,
       ind_acti)
      (SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'SEGUNDO PEDIDO',
              'DV11',
              'DV12',
              'DV13',
              'DV14',
              'DV15',
              'DV16',
              'DV17',
              'DV18',
              'DV19',
              'DV20',
              NULL,
              base.clie_oid_clie,
              (SELECT a.oid_mens FROM msg_mensa a WHERE a.cod_mens = 'NVA02'),
              13,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_prog,
              base.camp_ini_ccc,
              NULL,
              NULL,
              NULL,
              NULL,
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              1,
              NULL,
              1
         FROM (SELECT DISTINCT sc.clie_oid_clie,
                               mc.val_nom1 AS nom1,
                               mc.val_nom2 AS nom2,
                               mc.val_ape1 AS ape1,
                               mc.val_ape2 AS ape2,
                               mc.val_apel_casa AS apec,
                               mc.val_nom1 || ' ' || val_nom2 || ' ' ||
                               val_ape1 || ' ' || val_ape2 AS nombre,
                               nu.cod_prog,
                               nu.camp_ini_ccc
                 FROM int_solic_conso_cabec sc,
                      mae_clien             mc,
                      cup_consu_nueva       nu,
                      cup_prog_nueva_cupon  pn,
                      sto_proce_docum_digit occ
                WHERE sc.sec_nume_docu = occ.sec_nume_docu
                  AND sc.num_lote = occ.num_lote
                  AND occ.cod_tipo_docu = pscodtipodocu
                  AND occ.cod_pais = pscodigopais
                  AND occ.num_proc = psnumeroproceso
                  AND sc.cod_clie = mc.cod_clie
                  AND nu.cod_pais = pscodigopais
                  AND sc.cod_clie = nu.cod_cons
                  AND sc.cod_pais = nu.cod_pais
                  AND nu.camp_ini_ccc = lsperiodoant1
                  AND nu.cod_prog = pn.cod_prog
                  AND pn.ind_gene_mens = '1'
                  AND pn.cod_pais = nu.cod_pais) base);

    --- Carga mensajes tercer pedido consecutivo ---
    INSERT INTO msg_buzon_mensa
      (oid_buzo_mens,
       num_secu,
       dato_vari_10,
       dato_vari_11,
       dato_vari_12,
       dato_vari_13,
       dato_vari_14,
       dato_vari_15,
       dato_vari_16,
       dato_vari_17,
       dato_vari_18,
       dato_vari_19,
       dato_vari_20,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie,
       val_nom2_clie,
       val_ape1_clie,
       val_ape2_clie,
       val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       dato_vari_06,
       dato_vari_07,
       dato_vari_08,
       dato_vari_09,
       num_lote_impr,
       fec_grab,
       fec_impr,
       ind_list_cons,
       peri_oid_peri,
       ind_acti)
      (SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'TERCER PEDIDO CONSECUTIVO',
              'DV11',
              'DV12',
              'DV13',
              'DV14',
              'DV15',
              'DV16',
              'DV17',
              'DV18',
              'DV19',
              'DV20',
              NULL,
              base.clie_oid_clie,
              (SELECT a.oid_mens FROM msg_mensa a WHERE a.cod_mens = 'NVA3C'),
              13,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_prog,
              base.camp_ini_ccc,
              NULL,
              NULL,
              NULL,
              NULL,
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              1,
              NULL,
              1
         FROM (SELECT DISTINCT sc.clie_oid_clie,
                               mc.val_nom1 AS nom1,
                               mc.val_nom2 AS nom2,
                               mc.val_ape1 AS ape1,
                               mc.val_ape2 AS ape2,
                               mc.val_apel_casa AS apec,
                               mc.val_nom1 || ' ' || val_nom2 || ' ' ||
                               val_ape1 || ' ' || val_ape2 AS nombre,
                               nu.cod_prog,
                               nu.camp_ini_ccc
                 FROM int_solic_conso_cabec sc,
                      mae_clien             mc,
                      cup_consu_nueva       nu,
                      cup_prog_nueva_cupon  pn,
                      cup_consu_factu       fc,
                      sto_proce_docum_digit occ
                WHERE sc.sec_nume_docu = occ.sec_nume_docu
                  AND sc.num_lote = occ.num_lote
                  AND occ.cod_tipo_docu = pscodtipodocu
                  AND occ.cod_pais = pscodigopais
                  AND occ.num_proc = psnumeroproceso
                  AND sc.cod_clie = mc.cod_clie
                  AND sc.cod_clie = nu.cod_cons
                  AND nu.cod_pais = pscodigopais
                  AND sc.cod_pais = nu.cod_pais
                  AND nu.camp_ini_ccc = lsperiodoant2
                  AND nu.cod_prog = pn.cod_prog
                  AND nu.cod_cons = fc.cod_cons
                  AND fc.cod_pais = nu.cod_pais
                  AND fc.ind_cons = '0'
                  AND fc.cod_prog = nu.cod_prog
                  AND pn.cod_pais = nu.cod_pais
                  AND pn.ind_gene_mens = '1') base);

    --- Carga mensajes tercer pedido no consecutivo ---
    INSERT INTO msg_buzon_mensa
      (oid_buzo_mens,
       num_secu,
       dato_vari_10,
       dato_vari_11,
       dato_vari_12,
       dato_vari_13,
       dato_vari_14,
       dato_vari_15,
       dato_vari_16,
       dato_vari_17,
       dato_vari_18,
       dato_vari_19,
       dato_vari_20,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie,
       val_nom2_clie,
       val_ape1_clie,
       val_ape2_clie,
       val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       dato_vari_06,
       dato_vari_07,
       dato_vari_08,
       dato_vari_09,
       num_lote_impr,
       fec_grab,
       fec_impr,
       ind_list_cons,
       peri_oid_peri,
       ind_acti)
      (SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'TERCER PEDIDO NO CONSECUTIVO',
              'DV11',
              'DV12',
              'DV13',
              'DV14',
              'DV15',
              'DV16',
              'DV17',
              'DV18',
              'DV19',
              'DV20',
              NULL,
              base.clie_oid_clie,
              (SELECT a.oid_mens FROM msg_mensa a WHERE a.cod_mens = 'NVA3N'),
              13,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_prog,
              base.camp_ini_ccc,
              NULL,
              NULL,
              NULL,
              NULL,
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              1,
              NULL,
              1
         FROM (SELECT DISTINCT sc.clie_oid_clie,
                               mc.val_nom1 AS nom1,
                               mc.val_nom2 AS nom2,
                               mc.val_ape1 AS ape1,
                               mc.val_ape2 AS ape2,
                               mc.val_apel_casa AS apec,
                               mc.val_nom1 || ' ' || val_nom2 || ' ' ||
                               val_ape1 || ' ' || val_ape2 AS nombre,
                               nu.cod_prog,
                               nu.camp_ini_ccc
                 FROM int_solic_conso_cabec sc,
                      mae_clien             mc,
                      cup_consu_nueva       nu,
                      cup_prog_nueva_cupon  pn,
                      cup_consu_factu       fc,
                      sto_proce_docum_digit occ
                WHERE sc.sec_nume_docu = occ.sec_nume_docu
                  AND sc.num_lote = occ.num_lote
                  AND occ.cod_tipo_docu = pscodtipodocu
                  AND occ.cod_pais = pscodigopais
                  AND occ.num_proc = psnumeroproceso
                  AND sc.cod_clie = mc.cod_clie
                  AND sc.cod_clie = nu.cod_cons
                  AND nu.cod_pais = pscodigopais
                  AND sc.cod_pais = nu.cod_pais
                  AND nu.camp_ini_ccc = lsperiodoant2
                  AND nu.cod_prog = pn.cod_prog
                  AND nu.cod_cons = fc.cod_cons
                  AND fc.cod_pais = nu.cod_pais
                  AND fc.ind_cons = '1'
                  AND fc.cod_prog = nu.cod_prog
                  AND pn.cod_pais = nu.cod_pais
                  AND pn.ind_gene_mens = '1') base);

    --- Carga mensajes cuarto pedido consecutivo ---
    INSERT INTO msg_buzon_mensa
      (oid_buzo_mens,
       num_secu,
       dato_vari_10,
       dato_vari_11,
       dato_vari_12,
       dato_vari_13,
       dato_vari_14,
       dato_vari_15,
       dato_vari_16,
       dato_vari_17,
       dato_vari_18,
       dato_vari_19,
       dato_vari_20,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie,
       val_nom2_clie,
       val_ape1_clie,
       val_ape2_clie,
       val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       dato_vari_06,
       dato_vari_07,
       dato_vari_08,
       dato_vari_09,
       num_lote_impr,
       fec_grab,
       fec_impr,
       ind_list_cons,
       peri_oid_peri,
       ind_acti)
      (SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'CUARTO PEDIDO CONSECUTIVO',
              'DV11',
              'DV12',
              'DV13',
              'DV14',
              'DV15',
              'DV16',
              'DV17',
              'DV18',
              'DV19',
              'DV20',
              NULL,
              base.clie_oid_clie,
              (SELECT a.oid_mens FROM msg_mensa a WHERE a.cod_mens = 'NVA04'),
              13,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_prog,
              base.camp_ini_ccc,
              NULL,
              NULL,
              NULL,
              NULL,
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              1,
              NULL,
              1
         FROM (SELECT DISTINCT sc.clie_oid_clie,
                               mc.val_nom1 AS nom1,
                               mc.val_nom2 AS nom2,
                               mc.val_ape1 AS ape1,
                               mc.val_ape2 AS ape2,
                               mc.val_apel_casa AS apec,
                               mc.val_nom1 || ' ' || val_nom2 || ' ' ||
                               val_ape1 || ' ' || val_ape2 AS nombre,
                               nu.cod_prog,
                               nu.camp_ini_ccc
                 FROM int_solic_conso_cabec sc,
                      mae_clien             mc,
                      cup_consu_nueva       nu,
                      cup_prog_nueva_cupon  pn,
                      cup_consu_factu       fc,
                      sto_proce_docum_digit occ
                WHERE sc.sec_nume_docu = occ.sec_nume_docu
                  AND sc.num_lote = occ.num_lote
                  AND occ.cod_tipo_docu = pscodtipodocu
                  AND occ.cod_pais = pscodigopais
                  AND occ.num_proc = psnumeroproceso
                  AND sc.cod_clie = mc.cod_clie
                  AND sc.cod_clie = nu.cod_cons
                  AND nu.cod_pais = pscodigopais
                  AND sc.cod_pais = nu.cod_pais
                  AND nu.camp_ini_ccc = lsperiodoant3
                  AND nu.cod_prog = pn.cod_prog
                  AND nu.cod_cons = fc.cod_cons
                  AND fc.cod_pais = nu.cod_pais
                  AND fc.ind_cons = '0'
                  AND fc.cod_prog = nu.cod_prog
                  AND pn.cod_pais = nu.cod_pais
                  AND pn.ind_gene_mens = '1') base);

  EXCEPTION
    WHEN OTHERS THEN
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      cup_pr_log_error(ls_sqlerrm);
      raise_application_error(-20123,
                              'ERROR CUP_PR_INSER_MENSA_NUEVA: ' ||
                              ls_sqlerrm);
      RETURN;
  END cup_pr_inser_mensa_nueva;

  /**************************************************************************
  Descripcion       : CUP_PR_GENER_INGRE_METAS
                      Genera data en la tabla temporal para luego ser leida
                      por el reporte de ingresos de metas
  Fecha Creacion    : 10/10/2011
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoRegion   : Codigo de region
      psCodigoZona     : Codigo de Zona
      psCodigoPeriodoInicial : Codigo de Periodo Inicial
      psCodigoPeriodoFinal : Codigo de Periodo Final
      psOrigenRegistro : Origen del Registro
      psEstado : Estado

  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE cup_pr_gener_ingre_metas
  (
    pscodigopais           VARCHAR2,
    pscodigoregion         VARCHAR2,
    pscodigozona           VARCHAR2,
    pscodigoperiodoinicial VARCHAR2,
    pscodigoperiodofinal   VARCHAR2,
    psorigenregistro       VARCHAR2,
    psestado               VARCHAR2,
    psusuario              VARCHAR2
  ) IS

    CURSOR c_ingreso_metas_todos IS
    -- APROBADAS
      SELECT camp.cod_peri AS campaña_registro,
             trunc(i.fec_digi) AS fecha_carga,
             trunc(i.fec_modi) AS fecha_proceso,
             n.cod_pais AS pais,
             uas.des_regi AS region,
             uas.des_zona AS zona,
             uas.des_secci AS seccion,
             i.num_lote AS lote,
             i.num_docu AS num_documento,
             n.cod_clie AS cod_consultora,
             uas.nombre AS nombre,
             'APROBADO' AS estado,
             '' AS motivo_rechazo,
             nt.des_tipo_logr AS desc_tipo_logro,
             n.imp_logr AS importe_meta,
             n.cmp_inic AS campaña_inicio,
             n.cmp_fina AS campaña_final,
             CASE
               WHEN n.ori_regi IN ('C', 'S') THEN
                'SISTEMA COMERCIAL'
               WHEN n.ori_regi = 'O' THEN
                'OCR'
               WHEN n.ori_regi = 'W' THEN
                'WEB'
               WHEN n.ori_regi = 'B' THEN
                'BLACKBERRY'
             END AS origen_registro,
             TRIM(n.des_larg) AS detalle_logro,
             psusuario AS cod_usua
        FROM nvs_consu_logro n,
             nvs_tipo_logro nt,
             (SELECT ii.cod_clie,
                     ii.cod_camp_inic,
                     s.num_lote,
                     s.num_docu,
                     s.cod_moti_rech,
                     s.fec_digi,
                     s.fec_modi
                FROM (SELECT cod_clie,
                             cod_camp_inic,
                             num_lote,
                             sec_nume_docu
                        FROM int_solic_conso_ingre_metas
                      UNION
                      SELECT cod_clie,
                             cod_camp_inic,
                             num_lote,
                             sec_nume_docu
                        FROM int_histo_conso_ingre_metas) ii,
                     (SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_docum_digit
                       WHERE cod_tipo_docu = 'SIM'
                      UNION
                      SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_histo_docum_digit
                       WHERE cod_tipo_docu = 'SIM') s
               WHERE s.num_lote = ii.num_lote
                 AND s.sec_nume_docu = ii.sec_nume_docu
                 AND s.ind_rech = 0
                 AND s.ind_envi = 1 HAVING
               s.sec_nume_docu = MAX(s.sec_nume_docu)
               GROUP BY ii.cod_clie,
                        ii.cod_camp_inic,
                        s.num_lote,
                        s.num_docu,
                        s.cod_moti_rech,
                        s.fec_digi,
                        s.fec_modi,
                        s.sec_nume_docu) i,
             (SELECT peri.cod_peri,
                     perd.fec_inic,
                     perd.fec_fina
                FROM cra_perio       perd,
                     seg_perio_corpo peri
               WHERE perd.peri_oid_peri = peri.oid_peri) camp,
             (SELECT clie.cod_clie,
                     clie.val_nom1 || ' ' || clie.val_nom2 || ' ' ||
                     clie.val_ape1 || ' ' || clie.val_ape2 AS nombre,
                     zorg.des_regi,
                     zzon.des_zona,
                     zscc.des_secci
                FROM mae_clien_unida_admin cuad,
                     zon_terri_admin       ztad,
                     zon_secci             zscc,
                     zon_zona              zzon,
                     zon_regio             zorg,
                     mae_clien             clie
               WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND cuad.clie_oid_clie = clie.oid_clie
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 AND zorg.cod_regi = nvl(pscodigoregion, zorg.cod_regi)
                 AND zzon.cod_zona = nvl(pscodigozona, zzon.cod_zona)
                 AND cuad.ind_acti = 1) uas
       WHERE n.cod_clie = i.cod_clie(+)
         AND n.cmp_inic = i.cod_camp_inic(+)
         AND n.cod_tipo_logr = nt.cod_tipo_logr(+)
         AND n.fec_modi BETWEEN camp.fec_inic AND camp.fec_fina
         AND uas.cod_clie(+) = n.cod_clie
         AND n.cmp_inic >= pscodigoperiodoinicial
         AND n.cmp_inic <= pscodigoperiodofinal
         AND n.ori_regi = nvl(psorigenregistro, n.ori_regi)
         AND n.est_logr = '1' -- Nuevo
      UNION ALL
      -- RECHAZADAS
      SELECT camp.cod_peri AS campaña_registro,
             trunc(i.fec_digi) AS fecha_carga,
             trunc(i.fec_modi) AS fecha_proceso,
             i.cod_pais AS pais,
             uas.des_regi AS region,
             uas.des_zona AS zona,
             uas.des_secci AS seccion,
             i.num_lote AS lote,
             i.num_docu AS num_documento,
             i.cod_clie AS cod_consultora,
             uas.nombre AS nombre,
             'RECHAZADO' AS estado,
             TRIM(sr.des_moti_rech) AS motivo_rechazo,
             nt.des_tipo_logr AS desc_tipo_logro,
             to_number(i.val_mnto_meta, '9999999999.99') AS importe_meta,
             i.cod_camp_inic AS campaña_inicio,
             per_pkg_repor_perce.per_fn_obtie_perio(i.cod_camp_inic,
                                                    gen_pkg_gener.gen_fn_devuelve_id_pais(i.cod_pais),
                                                    gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                                    gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                                    3) AS campaña_final,
             'OCR' AS origen_registro,
             NULL AS detalle_logro,
             psusuario AS cod_usua
        FROM nvs_tipo_logro nt,
             (SELECT cod_moti_rech,
                     des_moti_rech,
                     cod_pais
                FROM sto_recha_motiv
               WHERE cod_tipo_docu = 'SIM') sr,
             (SELECT ii.cod_pais,
                     ii.cod_clie,
                     ii.val_mnto_meta,
                     ii.cod_camp_inic,
                     ii.tip_meta,
                     s.num_lote,
                     s.cod_moti_rech,
                     s.fec_digi,
                     s.fec_modi,
                     s.num_docu
                FROM (SELECT *
                        FROM int_solic_conso_ingre_metas
                      UNION
                      SELECT *
                        FROM int_histo_conso_ingre_metas) ii,
                     (SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_docum_digit
                       WHERE cod_tipo_docu = 'SIM'
                      UNION
                      SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_histo_docum_digit
                       WHERE cod_tipo_docu = 'SIM') s
               WHERE s.num_lote = ii.num_lote
                 AND s.sec_nume_docu = ii.sec_nume_docu
                 AND s.ind_rech = 1
                 AND s.ind_envi = 0
               GROUP BY ii.cod_pais,
                        ii.cod_clie,
                        ii.val_mnto_meta,
                        ii.cod_camp_inic,
                        ii.tip_meta,
                        s.num_lote,
                        s.cod_moti_rech,
                        s.fec_digi,
                        s.fec_modi,
                        s.sec_nume_docu,
                        s.num_docu) i,
             (SELECT peri.cod_peri,
                     perd.fec_inic,
                     perd.fec_fina
                FROM cra_perio       perd,
                     seg_perio_corpo peri
               WHERE perd.peri_oid_peri = peri.oid_peri) camp,
             (SELECT clie.cod_clie,
                     clie.val_nom1 || ' ' || clie.val_nom2 || ' ' ||
                     clie.val_ape1 || ' ' || clie.val_ape2 AS nombre,
                     zorg.des_regi,
                     zzon.des_zona,
                     zscc.des_secci
                FROM mae_clien_unida_admin cuad,
                     zon_terri_admin       ztad,
                     zon_secci             zscc,
                     zon_zona              zzon,
                     zon_regio             zorg,
                     mae_clien             clie
               WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND cuad.clie_oid_clie = clie.oid_clie
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 AND zorg.cod_regi = nvl(pscodigoregion, zorg.cod_regi)
                 AND zzon.cod_zona = nvl(pscodigozona, zzon.cod_zona)
                 AND cuad.ind_acti = 1) uas
       WHERE i.tip_meta = nt.cod_tipo_logr(+)
         AND i.fec_modi BETWEEN camp.fec_inic AND camp.fec_fina
         AND i.cod_moti_rech = sr.cod_moti_rech(+)
         AND i.cod_pais = sr.cod_pais(+)
         AND uas.cod_clie(+) = i.cod_clie
         AND i.cod_camp_inic >= pscodigoperiodoinicial
         AND i.cod_camp_inic <= pscodigoperiodofinal
         AND 'O' = nvl(psorigenregistro, 'O')
       ORDER BY campaña_registro,
                region,
                zona,
                seccion,
                cod_consultora;

    CURSOR c_ingreso_metas_aprobadas IS
    -- APROBADAS
      SELECT camp.cod_peri AS campaña_registro,
             trunc(i.fec_digi) AS fecha_carga,
             trunc(i.fec_modi) AS fecha_proceso,
             n.cod_pais AS pais,
             uas.des_regi AS region,
             uas.des_zona AS zona,
             uas.des_secci AS seccion,
             i.num_lote AS lote,
             i.num_docu AS num_documento,
             n.cod_clie AS cod_consultora,
             uas.nombre AS nombre,
             'APROBADO' AS estado,
             '' AS motivo_rechazo,
             nt.des_tipo_logr AS desc_tipo_logro,
             n.imp_logr AS importe_meta,
             n.cmp_inic AS campaña_inicio,
             n.cmp_fina AS campaña_final,
             CASE
               WHEN n.ori_regi IN ('C', 'S') THEN
                'SISTEMA COMERCIAL'
               WHEN n.ori_regi = 'O' THEN
                'OCR'
               WHEN n.ori_regi = 'W' THEN
                'WEB'
               WHEN n.ori_regi = 'B' THEN
                'BLACKBERRY'
             END AS origen_registro,
             TRIM(n.des_larg) AS detalle_logro,
             psusuario AS cod_usua
        FROM nvs_consu_logro n,
             nvs_tipo_logro nt,
             (SELECT ii.cod_clie,
                     ii.cod_camp_inic,
                     s.num_lote,
                     s.num_docu,
                     s.cod_moti_rech,
                     s.fec_digi,
                     s.fec_modi
                FROM (SELECT cod_clie,
                             cod_camp_inic,
                             num_lote,
                             sec_nume_docu
                        FROM int_solic_conso_ingre_metas
                      UNION
                      SELECT cod_clie,
                             cod_camp_inic,
                             num_lote,
                             sec_nume_docu
                        FROM int_histo_conso_ingre_metas) ii,
                     (SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_docum_digit
                       WHERE cod_tipo_docu = 'SIM'
                      UNION
                      SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_histo_docum_digit
                       WHERE cod_tipo_docu = 'SIM') s
               WHERE s.num_lote = ii.num_lote
                 AND s.sec_nume_docu = ii.sec_nume_docu
                 AND s.ind_rech = 0
                 AND s.ind_envi = 1 HAVING
               s.sec_nume_docu = MAX(s.sec_nume_docu)
               GROUP BY ii.cod_clie,
                        ii.cod_camp_inic,
                        s.num_lote,
                        s.num_docu,
                        s.cod_moti_rech,
                        s.fec_digi,
                        s.fec_modi,
                        s.sec_nume_docu) i,
             (SELECT peri.cod_peri,
                     perd.fec_inic,
                     perd.fec_fina
                FROM cra_perio       perd,
                     seg_perio_corpo peri
               WHERE perd.peri_oid_peri = peri.oid_peri) camp,
             (SELECT clie.cod_clie,
                     clie.val_nom1 || ' ' || clie.val_nom2 || ' ' ||
                     clie.val_ape1 || ' ' || clie.val_ape2 AS nombre,
                     zorg.des_regi,
                     zzon.des_zona,
                     zscc.des_secci
                FROM mae_clien_unida_admin cuad,
                     zon_terri_admin       ztad,
                     zon_secci             zscc,
                     zon_zona              zzon,
                     zon_regio             zorg,
                     mae_clien             clie
               WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND cuad.clie_oid_clie = clie.oid_clie
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 AND zorg.cod_regi = nvl(pscodigoregion, zorg.cod_regi)
                 AND zzon.cod_zona = nvl(pscodigozona, zzon.cod_zona)
                 AND cuad.ind_acti = 1) uas
       WHERE n.cod_clie = i.cod_clie(+)
         AND n.cmp_inic = i.cod_camp_inic(+)
         AND n.cod_tipo_logr = nt.cod_tipo_logr(+)
         AND n.fec_modi BETWEEN camp.fec_inic AND camp.fec_fina
         AND uas.cod_clie(+) = n.cod_clie
         AND n.cmp_inic >= pscodigoperiodoinicial
         AND n.cmp_inic <= pscodigoperiodofinal
         AND n.ori_regi = nvl(psorigenregistro, n.ori_regi)
         AND n.est_logr = '1' -- Nuevo
       ORDER BY campaña_registro,
                region,
                zona,
                seccion,
                cod_consultora;

    CURSOR c_ingreso_metas_rechazadas IS
    -- RECHAZADAS
      SELECT camp.cod_peri AS campaña_registro,
             trunc(i.fec_digi) AS fecha_carga,
             trunc(i.fec_modi) AS fecha_proceso,
             i.cod_pais AS pais,
             uas.des_regi AS region,
             uas.des_zona AS zona,
             uas.des_secci AS seccion,
             i.num_lote AS lote,
             i.num_docu AS num_documento,
             i.cod_clie AS cod_consultora,
             uas.nombre AS nombre,
             'RECHAZADO' AS estado,
             TRIM(sr.des_moti_rech) AS motivo_rechazo,
             nt.des_tipo_logr AS desc_tipo_logro,
             to_number(i.val_mnto_meta, '9999999999.99') AS importe_meta,
             i.cod_camp_inic AS campaña_inicio,
             per_pkg_repor_perce.per_fn_obtie_perio(i.cod_camp_inic,
                                                    gen_pkg_gener.gen_fn_devuelve_id_pais(i.cod_pais),
                                                    gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                                    gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                                    3) AS campaña_final,
             'OCR' AS origen_registro,
             NULL AS detalle_logro,
             psusuario AS cod_usua
        FROM nvs_tipo_logro nt,
             (SELECT cod_moti_rech,
                     des_moti_rech,
                     cod_pais
                FROM sto_recha_motiv
               WHERE cod_tipo_docu = 'SIM') sr,
             (SELECT ii.cod_pais,
                     ii.cod_clie,
                     ii.val_mnto_meta,
                     ii.cod_camp_inic,
                     ii.tip_meta,
                     s.num_lote,
                     s.cod_moti_rech,
                     s.fec_digi,
                     s.fec_modi,
                     s.num_docu
                FROM (SELECT *
                        FROM int_solic_conso_ingre_metas
                      UNION
                      SELECT *
                        FROM int_histo_conso_ingre_metas) ii,
                     (SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_docum_digit
                       WHERE cod_tipo_docu = 'SIM'
                      UNION
                      SELECT num_lote,
                             sec_nume_docu,
                             ind_rech,
                             ind_envi,
                             cod_moti_rech,
                             fec_digi,
                             fec_modi,
                             num_docu
                        FROM sto_histo_docum_digit
                       WHERE cod_tipo_docu = 'SIM') s
               WHERE s.num_lote = ii.num_lote
                 AND s.sec_nume_docu = ii.sec_nume_docu
                 AND s.ind_rech = 1
                 AND s.ind_envi = 0
               GROUP BY ii.cod_pais,
                        ii.cod_clie,
                        ii.val_mnto_meta,
                        ii.cod_camp_inic,
                        ii.tip_meta,
                        s.num_lote,
                        s.cod_moti_rech,
                        s.fec_digi,
                        s.fec_modi,
                        s.sec_nume_docu,
                        s.num_docu) i,
             (SELECT peri.cod_peri,
                     perd.fec_inic,
                     perd.fec_fina
                FROM cra_perio       perd,
                     seg_perio_corpo peri
               WHERE perd.peri_oid_peri = peri.oid_peri) camp,
             (SELECT clie.cod_clie,
                     clie.val_nom1 || ' ' || clie.val_nom2 || ' ' ||
                     clie.val_ape1 || ' ' || clie.val_ape2 AS nombre,
                     zorg.des_regi,
                     zzon.des_zona,
                     zscc.des_secci
                FROM mae_clien_unida_admin cuad,
                     zon_terri_admin       ztad,
                     zon_secci             zscc,
                     zon_zona              zzon,
                     zon_regio             zorg,
                     mae_clien             clie
               WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND cuad.clie_oid_clie = clie.oid_clie
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 AND zorg.cod_regi = nvl(pscodigoregion, zorg.cod_regi)
                 AND zzon.cod_zona = nvl(pscodigozona, zzon.cod_zona)
                 AND cuad.ind_acti = 1) uas
       WHERE i.tip_meta = nt.cod_tipo_logr(+)
         AND i.fec_modi BETWEEN camp.fec_inic AND camp.fec_fina
         AND i.cod_moti_rech = sr.cod_moti_rech(+)
         AND i.cod_pais = sr.cod_pais(+)
         AND uas.cod_clie(+) = i.cod_clie
         AND i.cod_camp_inic >= pscodigoperiodoinicial
         AND i.cod_camp_inic <= pscodigoperiodofinal
         AND 'O' = nvl(psorigenregistro, 'O')
       ORDER BY campaña_registro,
                region,
                zona,
                seccion,
                cod_consultora;

    TYPE ingresometastab IS TABLE OF cup_tempo_ingre_metas%ROWTYPE;
    ingresometas ingresometastab;

    rows NATURAL := 1000;

  BEGIN

    DELETE FROM cup_tempo_ingre_metas WHERE cod_usua = psusuario;

    IF psestado = 'Todos' THEN

      OPEN c_ingreso_metas_todos;
      LOOP
        FETCH c_ingreso_metas_todos BULK COLLECT
          INTO ingresometas LIMIT rows;

        FORALL i IN 1 .. ingresometas.count
          INSERT INTO cup_tempo_ingre_metas VALUES ingresometas (i);

        EXIT WHEN c_ingreso_metas_todos%NOTFOUND;
      END LOOP;
      CLOSE c_ingreso_metas_todos;
    END IF;

    IF psestado = 'A' THEN

      OPEN c_ingreso_metas_aprobadas;
      LOOP
        FETCH c_ingreso_metas_aprobadas BULK COLLECT
          INTO ingresometas LIMIT rows;

        FORALL i IN 1 .. ingresometas.count
          INSERT INTO cup_tempo_ingre_metas VALUES ingresometas (i);

        EXIT WHEN c_ingreso_metas_aprobadas%NOTFOUND;
      END LOOP;
      CLOSE c_ingreso_metas_aprobadas;
    END IF;

    IF psestado = 'R' THEN

      OPEN c_ingreso_metas_rechazadas;
      LOOP
        FETCH c_ingreso_metas_rechazadas BULK COLLECT
          INTO ingresometas LIMIT rows;

        FORALL i IN 1 .. ingresometas.count
          INSERT INTO cup_tempo_ingre_metas VALUES ingresometas (i);

        EXIT WHEN c_ingreso_metas_rechazadas%NOTFOUND;
      END LOOP;
      CLOSE c_ingreso_metas_rechazadas;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_GENER_INGRE_METAS: ' ||
                              ls_sqlerrm);
  END cup_pr_gener_ingre_metas;

  /**************************************************************************
  Descripcion       : Devuelve cero si no existe traslape de campañas, caso
                      contrario devuelve el codigo de programa que esta con
                      traslape
  Fecha Creacion    : 25/10/2011
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  FUNCTION cup_fn_trasl_perio
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2 IS

    lsresultado VARCHAR2(3) := '0';

  BEGIN

    BEGIN
      WITH tmp AS
       (SELECT MIN(cam_inic) mini,
               MAX(cam_fin) maxi
          FROM cup_prog_nueva_cupon
         WHERE cod_pais = pscodigopais
           AND est_prog = 'S')
      SELECT nvl(MIN(a.cod_prog), 0)
        INTO lsresultado
        FROM cup_prog_nueva_cupon a,
             tmp
       WHERE a.cod_pais = pscodigopais
         AND a.est_prog = 'S'
         AND (pscodigoperiodoini BETWEEN a.cam_inic AND a.cam_fin OR
             pscodigoperiodofin BETWEEN a.cam_inic AND a.cam_fin OR
             (pscodigoperiodoini < tmp.mini AND
             pscodigoperiodofin > tmp.maxi));

    EXCEPTION
      WHEN no_data_found THEN
        lsresultado := 0;
    END;

    RETURN lsresultado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_FN_TRASL_PERIO: ' || ls_sqlerrm);

  END cup_fn_trasl_perio;

  /**************************************************************************
  Descripcion       : Inserta un registro si es Regalo x Pedido
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 14/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_inser_regal_pedid
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    pscodigoconsultora VARCHAR2,
    psusuario          VARCHAR2
  ) IS
    CURSOR curprogramas IS
      SELECT cod_prog,
             ind_const,
             ind_rega_pedi
        FROM cup_prog_nueva_cupon
       WHERE cod_pais = pscodigopais;

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_ind_const IS TABLE OF cup_prog_nueva_cupon.ind_const%TYPE;
    TYPE t_ind_rxp IS TABLE OF cup_prog_nueva_cupon.ind_rega_pedi%TYPE;

    v_cod_prog  t_cod_prog;
    v_ind_const t_ind_const;
    v_ind_rxp   t_ind_rxp;

    v_row_count NUMBER := 0;
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;

  BEGIN

    OPEN curprogramas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curprogramas BULK COLLECT
        INTO v_cod_prog,
             v_ind_const,
             v_ind_rxp LIMIT rows;
      EXIT WHEN v_row_count = curprogramas%ROWCOUNT;
      v_row_count := curprogramas%ROWCOUNT;
      -- Recorro todos los programas creados para Nuevas
      FOR i IN v_cod_prog.first .. v_cod_prog.last
      LOOP
        -- Si tiene el indicador RxP en 1 se crea registro en tabla NVS_CONSU_REGAL_PEDID
        IF (v_ind_const(i) = '0' AND v_ind_rxp(i) = '1') THEN

          INSERT INTO nvs_consu_regal_pedid
            (cons_cod_pais,
             cons_cod_prog,
             cod_peri_desp,
             cons_cod_cons,
             soca_oid_soli_cabe,
             est_rega,
             usu_crea,
             fec_crea)
          VALUES
            (pscodigopais,
             v_cod_prog(i),
             pscodigoperiodo,
             pscodigoconsultora,
             NULL,
             '03',
             psusuario,
             SYSDATE);

        END IF;

      END LOOP;

    END LOOP;
    CLOSE curprogramas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_INSER_REGAL_PEDID: ' ||
                              ls_sqlerrm);

  END cup_pr_inser_regal_pedid;

  /**************************************************************************
  Descripcion       : Realiza un despacho de Regalo x Pedido
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 15/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_despa_regal_pedid
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  ) IS

    CURSOR curprogramas(pscampanaant VARCHAR2) IS
      SELECT DISTINCT a.cod_prog,
                      nvl(a.ped_mont_mini, 0)
        FROM cup_prog_nueva_cupon a,
             cup_desp_prod        b
       WHERE a.cod_pais = pscodigopais
         AND nvl(a.ind_rega_pedi, '0') = '1'
         AND a.est_prog = 'S'
         AND a.cod_pais = b.cod_pais
         AND a.cod_prog = b.cod_prog
         AND b.cod_peri = pscodigoperiodo
         AND b.ind_rega_pedi = '1';

    TYPE t_cod_prog IS TABLE OF cup_prog_nueva_cupon.cod_prog%TYPE;
    TYPE t_mont_mini IS TABLE OF cup_prog_nueva_cupon.ped_mont_mini%TYPE;

    v_cod_prog  t_cod_prog;
    v_mont_mini t_mont_mini;

    CURSOR curconsultoras
    (
      pnoidperiodo     NUMBER,
      pscampanaant     VARCHAR2,
      pscodigoprograma VARCHAR2
    ) IS
      SELECT mae.cod_clie,
             mae.oid_clie
        FROM ped_solic_cabec     soc,
             ped_tipo_solic      ts,
             ped_tipo_solic_pais tsp,
             mae_clien           mae,
             cup_consu_nueva     a
       WHERE soc.perd_oid_peri = pnoidperiodo
         AND soc.fec_fact IS NULL
         AND soc.ind_ts_no_conso = '1'
         AND soc.grpr_oid_grup_proc = 3
         AND soc.ind_oc = '1'
         AND soc.ind_pedi_prue = '0'
         AND ts.ind_devo = 0
         AND ts.ind_anul = 0
         AND soc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
         AND mae.oid_clie = soc.clie_oid_clie
         AND mae.cod_clie = a.cod_cons
         AND a.cod_pais = pscodigopais
         AND a.cod_prog = pscodigoprograma
         AND EXISTS (SELECT NULL
                FROM cup_desp_prod c
               WHERE c.cod_pais = a.cod_pais
                 AND c.cod_prog = a.cod_prog
                 AND c.cod_peri = pscodigoperiodo
                 AND c.cod_nivel =
                     cup_pkg_prog_nuevas.cup_fn_devue_nivel_noco2(a.camp_ini_ccc,
                                                                  pscodigoperiodo)
                 AND c.ind_rega_pedi = '1')
         AND NOT EXISTS (SELECT NULL
                FROM nvs_consu_regal_pedid reg
               WHERE reg.cons_cod_pais = a.cod_pais
                 AND reg.cons_cod_prog = a.cod_prog
                 AND reg.cons_cod_cons = a.cod_cons
                 AND reg.cod_peri_desp = pscodigoperiodo
                 AND reg.est_rega = '01');

    TYPE t_cod_clie IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_oid_clie IS TABLE OF mae_clien.oid_clie%TYPE;

    v_cod_clie t_cod_clie;
    v_oid_clie t_oid_clie;

    vnoidtiposol NUMBER;
    vnoidpais    NUMBER;
    vscampanaant VARCHAR2(6);

    vnoidperiodoproceso  NUMBER;
    vnoidperiodoanterior NUMBER;

    contadorpedido NUMBER := 0;
    vnmontopedido  ped_solic_cabec_acum2.val_mont_tota%TYPE;
    vnocurrencias  NUMBER;

    rows NATURAL := 5000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN

    vnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    -- Obteniendo el oid Tipo de Solicitud
    SELECT to_number(a.val_para)
      INTO vnoidtiposol
      FROM nvs_param_gener a
     WHERE a.pais_oid_pais = vnoidpais
       AND a.cod_para = '01';

    -- Obteniendo la campaña anterior
    vscampanaant := gen_fn_dev_nsgte_campa(pscodigoperiodo, -1);

    -- Obteniendo los oids de la campaña anterior y la de proceso
    vnoidperiodoproceso  := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
    vnoidperiodoanterior := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(vscampanaant);

    OPEN curprogramas(vscampanaant);
    LOOP

      -- Bulk collect data into memory table - X rows at a time
      FETCH curprogramas BULK COLLECT
        INTO v_cod_prog,
             v_mont_mini LIMIT rows;

      -- Recorro todos los programas que tengan RxP
      IF v_cod_prog.count > 0 THEN

        FOR i IN v_cod_prog.first .. v_cod_prog.last
        LOOP

          --Obteniendo las Consultoras q pasan pedido dentro del programa
          OPEN curconsultoras(vnoidperiodoproceso,
                              vscampanaant,
                              v_cod_prog(i));
          LOOP

            -- Bulk collect data into memory table - X rows at a time
            FETCH curconsultoras BULK COLLECT
              INTO v_cod_clie,
                   v_oid_clie LIMIT rows;

            IF v_cod_clie.count > 0 THEN

              FOR j IN v_cod_clie.first .. v_cod_clie.last
              LOOP

                -- Se valida si la consultora no tiene pedido anulado en la campaña anterior
                SELECT COUNT(1)
                  INTO contadorpedido
                  FROM ped_solic_cabec soc2
                 WHERE soc2.oid_soli_cabe =
                       (SELECT soc.soca_oid_soli_cabe
                          FROM ped_solic_cabec     soc,
                               ped_tipo_solic      ts,
                               ped_tipo_solic_pais tsp
                         WHERE soc.perd_oid_peri = vnoidperiodoanterior
                           AND soc.clie_oid_clie = v_oid_clie(j)
                           AND soc.fec_fact IS NOT NULL
                           AND soc.ind_ts_no_conso = '1'
                           AND soc.ind_oc = '1'
                           AND soc.ind_pedi_prue = '0'
                           AND ts.ind_devo = 0
                           AND ts.ind_anul = 0
                           AND soc.tspa_oid_tipo_soli_pais =
                               tsp.oid_tipo_soli_pais
                           AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli)
                   AND soc2.esso_oid_esta_soli != 4;

                --Si pedido es anulado crear un registro en NVS_CONSU_REGAL_PEDID
                IF (contadorpedido = 0) THEN

                  SELECT COUNT(1)
                    INTO vnocurrencias
                    FROM nvs_consu_regal_pedid
                   WHERE cons_cod_pais = pscodigopais
                     AND cons_cod_prog = v_cod_prog(i)
                     AND cod_peri_desp = pscodigoperiodo
                     AND cons_cod_cons = v_cod_clie(j)
                     AND est_rega = '02';

                  IF (vnocurrencias = 0) THEN
                    INSERT INTO nvs_consu_regal_pedid
                      (cons_cod_pais,
                       cons_cod_prog,
                       cod_peri_desp,
                       cons_cod_cons,
                       soca_oid_soli_cabe,
                       est_rega,
                       usu_crea,
                       fec_crea)
                    VALUES
                      (pscodigopais,
                       v_cod_prog(i),
                       pscodigoperiodo,
                       v_cod_clie(j),
                       NULL,
                       '02',
                       pscodigousuario,
                       SYSDATE);
                  END IF;
                END IF;

                IF (contadorpedido > 0) THEN
                  --Se valida el monto del pedido
                  SELECT a.val_mont_tota
                    INTO vnmontopedido
                    FROM ped_solic_cabec_acum2 a
                   WHERE a.clie_oid_clie = v_oid_clie(j)
                     AND a.perd_oid_peri = vnoidperiodoanterior;

                  IF (vnmontopedido < v_mont_mini(i)) THEN

                    SELECT COUNT(1)
                      INTO vnocurrencias
                      FROM nvs_consu_regal_pedid
                     WHERE cons_cod_pais = pscodigopais
                       AND cons_cod_prog = v_cod_prog(i)
                       AND cod_peri_desp = pscodigoperiodo
                       AND cons_cod_cons = v_cod_clie(j)
                       AND est_rega = '04';

                    IF (vnocurrencias = 0) THEN
                      INSERT INTO nvs_consu_regal_pedid
                        (cons_cod_pais,
                         cons_cod_prog,
                         cod_peri_desp,
                         cons_cod_cons,
                         soca_oid_soli_cabe,
                         est_rega,
                         usu_crea,
                         fec_crea)
                      VALUES
                        (pscodigopais,
                         v_cod_prog(i),
                         pscodigoperiodo,
                         v_cod_clie(j),
                         NULL,
                         '04',
                         pscodigousuario,
                         SYSDATE);
                    END IF;
                    -- CC se crea la solicitud
                  ELSE
                    cup_pr_inser_solic_regal_pedid(pscodigopais,
                                                   vnoidpais,
                                                   pscodigoperiodo,
                                                   vnoidperiodoproceso,
                                                   vnoidtiposol,
                                                   v_oid_clie(j),
                                                   v_cod_clie(j),
                                                   v_cod_prog(i),
                                                   pscodigousuario);
                  END IF;

                END IF;

              END LOOP;

            END IF;

            EXIT WHEN curconsultoras%NOTFOUND;

          END LOOP;
          CLOSE curconsultoras;

        END LOOP;

      END IF;

      EXIT WHEN curprogramas%NOTFOUND;

    END LOOP;
    CLOSE curprogramas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_DESPA_REGAL_PEDID: ' ||
                              ls_sqlerrm);

  END cup_pr_despa_regal_pedid;

  /**************************************************************************
  Descripcion       : Inserta una Solicitud RxP
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 16/05/2012
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_inser_solic_regal_pedid
  (
    pscodigopais        VARCHAR2,
    pnoidpais           NUMBER,
    pscodigoperiodo     VARCHAR2,
    pnoidperiodoproceso NUMBER,
    pnoidtiposol        NUMBER,
    pnoidcliente        NUMBER,
    pscodigocliente     VARCHAR2,
    pscodigoprograma    VARCHAR2,
    pscodigousuario     VARCHAR2
  ) IS

    CURSOR detalles IS
      SELECT DISTINCT rownum,
                      ofedet.prod_oid_prod,
                      ofedet.imp_prec_posi,
                      ofedet.val_codi_vent,
                      ofedet.oid_deta_ofer
        FROM pre_ofert             ofe,
             pre_ofert_detal       ofedet,
             pre_matri_factu       mf,
             pre_matri_factu_cabec mfc
       WHERE mfc.perd_oid_peri = pnoidperiodoproceso
         AND mf.mfca_oid_cabe = mfc.oid_cabe
         AND ofe.mfca_oid_cabe = mfc.oid_cabe
         AND ofe.oid_ofer = ofedet.ofer_oid_ofer
         AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
         AND ofedet.val_codi_vent IN
             (SELECT pr.cod_venta
                FROM cup_desp_prod pr
               WHERE pr.cod_pais = pscodigopais
                 AND pr.cod_prog = pscodigoprograma
                 AND pr.cod_peri = pscodigoperiodo
                 AND pr.ind_rega_pedi = '1'
                 AND pr.cod_nivel =
                     (SELECT cup_pkg_prog_nuevas.cup_fn_devue_nivel_noco2(n.camp_ini_ccc,
                                                                          pscodigoperiodo)
                        FROM cup_consu_nueva n
                       WHERE n.cod_pais = pr.cod_pais
                         AND n.cod_prog = pr.cod_prog
                         AND n.cod_cons = pscodigocliente));

    TYPE t_cod_posicion IS TABLE OF ped_solic_posic.cod_posi%TYPE;
    TYPE t_oid_producto IS TABLE OF pre_ofert_detal.prod_oid_prod%TYPE;
    TYPE t_pre_posi IS TABLE OF pre_ofert_detal.imp_prec_posi%TYPE;
    TYPE t_cod_venta IS TABLE OF pre_ofert_detal.val_codi_vent%TYPE;
    TYPE t_oid_deta_ofer IS TABLE OF pre_ofert_detal.oid_deta_ofer%TYPE;

    v_cod_posicion  t_cod_posicion;
    v_oid_producto  t_oid_producto;
    v_pre_posi      t_pre_posi;
    v_cod_venta     t_cod_venta;
    v_oid_deta_ofer t_oid_deta_ofer;

    varformapagoenv  ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
    varclasesolicenv ped_clase_solic.oid_clas_soli%TYPE;
    varoidalmacenv   ped_tipo_solic_pais.almc_oid_alma%TYPE;
    vartiposolicons  ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
    vartipodocum2    ped_tipo_solic_pais.tido_oid_tipo_docu%TYPE;
    varsubac         ped_tipo_solic.sbac_oid_sbac%TYPE;
    varsocie         ped_tipo_solic_pais.soci_oid_soci%TYPE;
    varnumesoli      ped_solic_cabec.val_nume_soli%TYPE;
    varnumeformato   ped_solic_cabec.val_nume_soli%TYPE;

    varfechaprogfactdev  DATE;
    varoidcabe           NUMBER(12);
    vartipoclie          NUMBER(12);
    varoiddireclie       NUMBER(12);
    vartipodocident      NUMBER(12);
    varoidterritorio     zon_terri.oid_terr%TYPE;
    varoidzona           zon_zona.oid_zona%TYPE;
    varoidvalestrugeopo  zon_terri.vepo_oid_valo_estr_geop%TYPE;
    varoidsubtipocliente NUMBER(12);
    varoidterriadmi      NUMBER(12);
    vartipocambio        NUMBER(12);
    varoidformapago      NUMBER(12);
    varoidtipoprog       inc_concu_tipo_prog.oid_tipo_prog%TYPE;
    varoidtipoposi       ped_tipo_posic.oid_tipo_posi%TYPE;
    varoidsubtposi       ped_subti_posic.oid_subt_posi%TYPE;
    varocurrencias       NUMBER;

    rows        NATURAL := 5000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    SELECT a.fopa_oid_form_pago,
           d.oid_clas_soli,
           a.almc_oid_alma,
           a.tsol_oid_tipo_cons,
           a.tido_oid_tipo_docu,
           c.sbac_oid_sbac,
           a.soci_oid_soci
      INTO varformapagoenv,
           varclasesolicenv,
           varoidalmacenv,
           vartiposolicons,
           vartipodocum2,
           varsubac,
           varsocie
      FROM ped_tipo_solic_pais a,
           ped_tipo_solic      c,
           ped_clase_solic     d
     WHERE a.oid_tipo_soli_pais = pnoidtiposol
       AND a.tsol_oid_tipo_soli = c.oid_tipo_soli
       AND c.clso_oid_clas_soli = d.oid_clas_soli;

    --Obtenemos el Numero de Solicitud
    varnumesoli := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                          'PED001',
                                                          '000',
                                                          0);

    varnumeformato := to_char(SYSDATE, 'YY') || lpad(varnumesoli, 8, '0') + 1;

    -- obtenemos el siguiente numero de Secuencia
    SELECT ped_soca_seq.nextval INTO varoidcabe FROM dual;

    -- Obtenemos la fecha final del periodo
    SELECT b.fec_fina
      INTO varfechaprogfactdev
      FROM cra_perio b
     WHERE b.oid_peri = pnoidperiodoproceso;

    SELECT m.ticl_oid_tipo_clie,
           m.sbti_oid_subt_clie
      INTO vartipoclie,
           varoidsubtipocliente
      FROM mae_clien_tipo_subti m
     WHERE m.clie_oid_clie = pnoidcliente
       AND m.ind_ppal = 1;

    --Recuperamos datos de la direccion del Cliente
    SELECT m.oid_clie_dire
      INTO varoiddireclie
      FROM mae_clien_direc m
     WHERE m.clie_oid_clie = pnoidcliente
       AND m.ind_dire_ppal = 1
       AND m.ind_elim = 0;

    --Recuperamos los datos del documento de identidad del Cliente
    SELECT m.tdoc_oid_tipo_docu
      INTO vartipodocident
      FROM mae_clien_ident m
     WHERE m.clie_oid_clie = pnoidcliente
       AND m.val_iden_docu_prin = 1;

    --Recuperamos los datos de la unidad administrativa del Cliente
    SELECT ter.terr_oid_terr,
           zon.oid_zona,
           ztr.vepo_oid_valo_estr_geop,
           adm.ztad_oid_terr_admi
      INTO varoidterritorio,
           varoidzona,
           varoidvalestrugeopo,
           varoidterriadmi
      FROM mae_clien_unida_admin adm,
           zon_terri_admin       ter,
           zon_secci             sec,
           zon_zona              zon,
           zon_terri             ztr
     WHERE adm.clie_oid_clie = pnoidcliente
       AND adm.ind_acti = 1
       AND adm.ztad_oid_terr_admi = ter.oid_terr_admi
       AND ter.zscc_oid_secc = sec.oid_secc
       AND sec.zzon_oid_zona = zon.oid_zona
       AND ztr.oid_terr = ter.terr_oid_terr
       AND ztr.pais_oid_pais = ter.pais_oid_pais;

    --Recuperamos el Tipo de Cambio
    SELECT val_tipo_camb
      INTO vartipocambio
      FROM pre_matri_factu_cabec
     WHERE perd_oid_peri = pnoidperiodoproceso;

    -- Calculando la forma de pago
    IF (varformapagoenv IS NOT NULL) THEN
      varoidformapago := varformapagoenv;
    ELSE

      SELECT m.fopa_oid_form_pago
        INTO varoidformapago
        FROM mae_clien m
       WHERE m.oid_clie = pnoidcliente;

      IF (varoidformapago IS NULL) THEN

        SELECT s.fopa_oid_form_pago
          INTO varoidformapago
          FROM seg_pais s
         WHERE s.oid_pais = pnoidpais;

      END IF;
    END IF;

    --Obteniendo el Tipo Programa Bonificacion
    SELECT oid_tipo_prog
      INTO varoidtipoprog
      FROM inc_concu_tipo_prog
     WHERE cod_tipo_prog = 'B';

    --Obteniendo el Tipo Posicion (PX)
    SELECT oid_tipo_posi
      INTO varoidtipoposi
      FROM ped_tipo_posic
     WHERE cod_tipo_posi = 'PX';

    --Obteniendo el SubTipo Posicion (PX)
    SELECT oid_subt_posi
      INTO varoidsubtposi
      FROM ped_subti_posic
     WHERE cod_subt_posi = 'PX';

    -- insertando la cabecera
    INSERT INTO ped_solic_cabec
      (oid_soli_cabe,
       fec_prog_fact,
       tspa_oid_tipo_soli_pais,
       tids_oid_tipo_desp,
       almc_oid_alma,
       modu_oid_modu,
       ticl_oid_tipo_clie,
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
       fec_cron,
       ind_perm_unio_sol,
       num_docu_orig,
       ind_ts_no_conso,
       ind_oc,
       pais_oid_pais,
       tido_oid_tipo_docu,
       vepo_oid_valo_estr_geop,
       esso_oid_esta_soli,
       copa_oid_para_gene,
       grpr_oid_grup_proc,
       sbti_oid_subt_clie,
       tspa_oid_tipo_soli_pais_cons,
       fopa_oid_form_pago,
       clso_oid_clas_soli,
       ztad_oid_terr_admi,
       oper_oid_oper,
       proc_oid_proc,
       soca_oid_docu_refe,
       ictp_oid_tipo_prog,
       val_tipo_camb)
    VALUES
      (varoidcabe,
       varfechaprogfactdev,
       pnoidtiposol,
       1,
       varoidalmacenv,
       13,
       vartipoclie,
       pnoidperiodoproceso,
       pnoidcliente,
       pnoidcliente,
       pnoidcliente,
       pnoidcliente,
       varoiddireclie,
       vartipodocident,
       varsocie,
       varsubac,
       varoidterritorio,
       varoidzona,
       varnumeformato,
       SYSDATE,
       1,
       NULL,
       1,
       0,
       pnoidpais,
       30,
       varoidvalestrugeopo,
       1,
       NULL,
       3,
       varoidsubtipocliente,
       vartiposolicons,
       varoidformapago,
       varclasesolicenv,
       varoidterriadmi,
       14,
       1,
       NULL,
       varoidtipoprog,
       vartipocambio);

    -- Insertando los detalles
    OPEN detalles;
    LOOP

      -- Bulk collect data into memory table - X rows at a time
      FETCH detalles BULK COLLECT
        INTO v_cod_posicion,
             v_oid_producto,
             v_pre_posi,
             v_cod_venta,
             v_oid_deta_ofer LIMIT rows;

      EXIT WHEN v_row_count = detalles%ROWCOUNT;
      v_row_count := detalles%ROWCOUNT;

      FORALL i IN 1 .. v_oid_producto.count
        INSERT INTO ped_solic_posic
          (oid_soli_posi,
           cod_posi,
           num_unid_dema,
           num_unid_por_aten,
           num_unid_compr,
           num_unid_dema_real,
           val_tasa_impu,
           soca_oid_soli_cabe,
           tpos_oid_tipo_posi,
           prod_oid_prod,
           val_prec_cata_unit_loca,
           val_prec_cont_unit_loca,
           val_prec_cata_unit_docu,
           val_prec_conta_unit_docu,
           ofde_oid_deta_ofer,
           val_codi_vent,
           espo_oid_esta_posi,
           stpo_oid_subt_posi)
        VALUES
          (ped_sopo_seq.nextval,
           v_cod_posicion(i),
           1,
           1,
           1,
           1,
           0,
           varoidcabe,
           varoidtipoposi,
           v_oid_producto(i),
           0,
           v_pre_posi(i),
           0,
           v_pre_posi(i),
           v_oid_deta_ofer(i),
           v_cod_venta(i),
           4,
           varoidsubtposi);

    END LOOP;
    CLOSE detalles;

    SELECT COUNT(1)
      INTO varocurrencias
      FROM nvs_consu_regal_pedid
     WHERE cons_cod_pais = pscodigopais
       AND cons_cod_prog = pscodigoprograma
       AND cod_peri_desp = pscodigoperiodo
       AND cons_cod_cons = pscodigocliente
       AND est_rega = '01';

    -- Creando registro en NVS_CONSU_REGAL_PEDID
    IF (varocurrencias = 0) THEN
      INSERT INTO nvs_consu_regal_pedid
        (cons_cod_pais,
         cons_cod_prog,
         cod_peri_desp,
         cons_cod_cons,
         soca_oid_soli_cabe,
         est_rega,
         usu_crea,
         fec_crea)
      VALUES
        (pscodigopais,
         pscodigoprograma,
         pscodigoperiodo,
         pscodigocliente,
         varoidcabe,
         '01',
         pscodigousuario,
         SYSDATE);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_INSER_SOLIC_REGAL_PEDID: ' ||
                              ls_sqlerrm);

  END cup_pr_inser_solic_regal_pedid;

  /**************************************************************************
  Descripcion       : Verifica si existe cruce de Programa
                      cambio RCR PER-SiCC-2012-0362
  Fecha Creacion    : 01/06/2012
  Autor             : José Luis Rodrgíguez
  ***************************************************************************/
  PROCEDURE cup_pr_verif_cruce_proga
  (
    pscodigopais       VARCHAR2,
    pscodigoprograma   VARCHAR2,
    pscodigoperiodoact VARCHAR2,
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2,
    pscodigoregion     VARCHAR2,
    pscodigozona       VARCHAR2,
    psresultado        OUT VARCHAR2
  ) IS

    vs_resultado VARCHAR2(1) := '0';
    vn_total     NUMBER := 0;

  BEGIN

    SELECT decode(SUM(val), 0, 0, 1) val
      INTO vn_total
      FROM (SELECT COUNT(*) val
              FROM cup_prog_nueva_cupon  cu,
                   nvs_param_progr_unadm ua
             WHERE cu.cod_pais = ua.pais_cod_pais
               AND cu.cod_prog = ua.pnvs_cod_prog
               AND cu.cod_pais = pscodigopais
               AND cu.est_prog = 'S'
               AND ua.est_regi = '1'
               AND cu.cod_prog != pscodigoprograma
               AND cu.cam_fin >= pscodigoperiodoact
               AND (pscodigoperiodoini BETWEEN cu.cam_inic AND cu.cam_fin OR
                   pscodigoperiodofin BETWEEN cu.cam_inic AND cu.cam_fin OR
                   cu.cam_inic BETWEEN pscodigoperiodoini AND
                   pscodigoperiodofin)
               AND (pscodigoregion IS NULL OR pscodigoregion = '' OR
                   ua.cod_regi = pscodigoregion)
               AND (pscodigozona IS NULL OR pscodigozona = '' OR
                   ua.cod_zona = pscodigozona)
            UNION
            SELECT COUNT(*) val
              FROM cup_prog_nueva_cupon a
             WHERE a.cod_pais = pscodigopais
               AND a.est_prog = 'S'
               AND a.cam_fin >= pscodigoperiodoact
               AND a.cod_prog != pscodigoprograma
               AND (pscodigoperiodoini BETWEEN a.cam_inic AND a.cam_fin OR
                   pscodigoperiodofin BETWEEN a.cam_inic AND a.cam_fin OR
                   a.cam_inic BETWEEN pscodigoperiodoini AND
                   pscodigoperiodofin)
               AND a.cod_prog NOT IN
                   (SELECT pnvs_cod_prog
                      FROM nvs_param_progr_unadm
                     WHERE est_regi = '1'));

    IF vn_total > 0 THEN
      vs_resultado := '1';
    ELSE
      vs_resultado := '0';
    END IF;

    psresultado := vs_resultado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_VERIF_CRUCE_PROGA: ' ||
                              ls_sqlerrm);

  END cup_pr_verif_cruce_proga;

  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NIVEL_NOCO2
                       Devuelve el Nivel, saliendo de la resta de PeriodoFin - PeriodoInicio
  Fecha Creacion     : 04/06/2012
  Parametros Entrada:
      pscodigoperiodoini : Periodo Inicio
      pscodigoperiodofin : Periodo Fin
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION cup_fn_devue_nivel_noco2
  (
    pscodigoperiodoini VARCHAR2,
    pscodigoperiodofin VARCHAR2
  ) RETURN VARCHAR2 IS
    v_cod_nivel        VARCHAR2(6);
    i                  BINARY_INTEGER := 0;
    lscodigoperiodoini VARCHAR2(6);

  BEGIN
    v_cod_nivel        := '00';
    lscodigoperiodoini := pscodigoperiodoini;
    IF (pscodigoperiodoini > pscodigoperiodofin OR
       TRIM(pscodigoperiodoini) IS NULL OR
       TRIM(pscodigoperiodofin) IS NULL) THEN
      RETURN v_cod_nivel;
    END IF;

    WHILE TRUE
    LOOP
      lscodigoperiodoini := gen_fn_dev_nsgte_campa(pscodigoperiodoini, i);
      IF (pscodigoperiodofin = lscodigoperiodoini) THEN
        SELECT TRIM(to_char(i, '09')) INTO v_cod_nivel FROM dual;
        EXIT;
      END IF;
      i := i + 1;
    END LOOP;

    RETURN v_cod_nivel;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_FN_DEVUE_NIVEL_NOCO2: ' ||
                              ls_sqlerrm);

  END cup_fn_devue_nivel_noco2;

  /***************************************************************************
  Descripcion       : Valida la descripcion de los campos de las
                      consultoras al programa de nuevas mediante un Excel
  Fecha Creacion    : 10/12/2012
  Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE cup_pr_nvs_valid_consu
  (
    pscodpais       VARCHAR2,
    pscodcliente    VARCHAR2,
    pscodperiodo    VARCHAR2,
    pscodusuario    VARCHAR2,
    psmensajesalida OUT VARCHAR2
  ) IS
    lsoidcliente            mae_clien.oid_clie%TYPE;
    lsindicadoractivo       mae_clien_datos_adici.ind_acti%TYPE;
    lsoidestatusconsultora  NUMBER(1);
    lscodigoestatus         VARCHAR(2);
    lsclientesencontrados   NUMBER(2);
    lscampaniamas18         NUMBER(6);
    lsperiodoconsultora     VARCHAR2(6);
    lscodigoprograma        VARCHAR2(3);
    lsindicador             VARCHAR2(1);
    lsnombreclientecompleto VARCHAR2(150);
    lsindboolean            BOOLEAN := FALSE;
    lsvalidacionok          BOOLEAN := FALSE;
    lncantidad              NUMBER;

  BEGIN
    ------------------Seccion Validar Registros NVS-009-----------------------------------
    -- El sistema busca en la entidad Cliente (MAE_CLIEN) si el valor
    -- del ítem tiene alguna coincidencia con el Atributo Código de Cliente (COD_CLIE)
    -- del maestro de cliente (MAE_CLIEN).

    BEGIN
      SELECT oid_clie
        INTO lsoidcliente
        FROM mae_clien
       WHERE cod_clie = pscodcliente;
    EXCEPTION
      WHEN no_data_found THEN
        lsoidcliente := NULL;
    END;

    --Si lscodigoCliente tiene el cliente entonces procedemos a la siguiente validacion
    IF lsoidcliente IS NOT NULL THEN

      -- Si el código Existe, El sistema verifica que el código se encuentra ACTIVO,
      -- si no se encuentra activo (IND_ACTI = 0) en la entidad Datos Adicionales  (MAE_CLIEN_DATOS_ADICI)

      SELECT a.ind_acti,
             c.cod_esta_clie
        INTO lsindicadoractivo,
             lscodigoestatus
        FROM mae_clien_datos_adici a,
             mae_estat_clien       c
       WHERE a.clie_oid_clie = lsoidcliente
         AND a.esta_oid_esta_clie = c.oid_esta_clie;

      IF lsindicadoractivo = 1 THEN
        --Si el código se encuentra Activo, el sistema verifica el estatus de la
        -- consultora, si es diferente al
        --             Estatus Nueva("02") ó Reactivada("08")
        IF lscodigoestatus = '02' OR lscodigoestatus = '08' THEN

          --Obenemos el codigo del programa asiciado ala ua de la consultora
          BEGIN
            SELECT pnvs_cod_prog
              INTO lscodigoprograma
              FROM nvs_param_progr_unadm
             WHERE est_regi = '1'
               AND (zorg_oid_regi, zzon_oid_zona) IN
                   (SELECT zz.zorg_oid_regi,
                           a3.zzon_oid_zona
                      FROM mae_clien_unida_admin a1,
                           zon_terri_admin       a2,
                           zon_secci             a3,
                           zon_zona              zz
                     WHERE a1.ztad_oid_terr_admi = a2.oid_terr_admi
                       AND a2.zscc_oid_secc = a3.oid_secc
                       AND zz.oid_zona = a3.zzon_oid_zona
                       AND a1.clie_oid_clie = lsoidcliente
                       AND a1.ind_acti = '1');
          EXCEPTION
            WHEN no_data_found THEN
              lscodigoprograma := '';

          END;

          IF length(lscodigoprograma) = 0 THEN

            BEGIN
              SELECT pnvs_cod_prog
                INTO lscodigoprograma
                FROM nvs_param_progr_unadm
               WHERE est_regi = '1'
                 AND zzon_oid_zona IS NULL
                 AND (zorg_oid_regi) IN
                     (SELECT zz.zorg_oid_regi
                        FROM mae_clien_unida_admin a1,
                             zon_terri_admin       a2,
                             zon_secci             a3,
                             zon_zona              zz
                       WHERE a1.ztad_oid_terr_admi = a2.oid_terr_admi
                         AND a2.zscc_oid_secc = a3.oid_secc
                         AND zz.oid_zona = a3.zzon_oid_zona
                         AND a1.clie_oid_clie = lsoidcliente
                         AND a1.ind_acti = '1');

            EXCEPTION
              WHEN no_data_found THEN
                lscodigoprograma := '';
            END;

          END IF;

          BEGIN
            SELECT prg_ua.cod_prog
              INTO lscodigoprograma
              FROM nvs_lista_zonas_asoci prg_ua
             WHERE prg_ua.oid_zona IN
                   (SELECT zz.oid_zona
                      FROM mae_clien_unida_admin a1,
                           zon_terri_admin       a2,
                           zon_secci             a3,
                           zon_zona              zz
                     WHERE a1.ztad_oid_terr_admi = a2.oid_terr_admi
                       AND a2.zscc_oid_secc = a3.oid_secc
                       AND zz.oid_zona = a3.zzon_oid_zona
                       AND a1.clie_oid_clie = lsoidcliente
                       AND a1.ind_acti = '1');
          EXCEPTION
            WHEN no_data_found THEN
              lscodigoprograma := '';
          END;

          SELECT COUNT(1)
            INTO lsclientesencontrados
            FROM cup_consu_nueva cup
           WHERE cod_pais = pscodpais
             AND cod_prog = lscodigoprograma
             AND cod_cons = pscodcliente;

          IF lsclientesencontrados > 0 THEN
            -- eL CLINTE ESTA REGISTRADO EN LA TABLA  CUP_CONSU_NUEVA
            IF lscodigoestatus = '02' THEN
              -- ERROR Estatus Nueva("02")
              SELECT num_vali || '_' || des_vali
                INTO psmensajesalida
                FROM mae_valid_clien_param
               WHERE pais_cod_pais = pscodpais
                 AND sist_cod_sist = 'MAE'
                 AND num_vali = 'NVS-VAL-05'
                 AND tip_vali = '4';

            ELSIF lscodigoestatus = '08' THEN
              -- Si encuentra Registrado, verificar si la cantidad de campañas transcurridas
              --desde su ingreso al programa(CAMP_INI_CCC),
              --con referencia a la campaña de proceso, es menor a 18

              SELECT camp_ini_ccc
                INTO lsperiodoconsultora
                FROM cup_consu_nueva
               WHERE cod_pais = pscodpais
                 AND cod_prog = lscodigoprograma
                 AND cod_cons = pscodcliente;

              lscampaniamas18 := gen_pkg_gener.gen_fn_perio_nsigu(pscodpais,
                                                                  lsperiodoconsultora,
                                                                  18);

              IF to_number(lscampaniamas18) > to_number(pscodperiodo) THEN
                -- ERROR Reactivada("08")
                SELECT num_vali || '_' || des_vali
                  INTO psmensajesalida
                  FROM mae_valid_clien_param
                 WHERE pais_cod_pais = pscodpais
                   AND sist_cod_sist = 'MAE'
                   AND num_vali = 'NVS-VAL-06'
                   AND tip_vali = '4';
              ELSE
                lsvalidacionok := TRUE;
              END IF;
            END IF;
          ELSE
            lsvalidacionok := TRUE;
          END IF;

          IF lsvalidacionok THEN
            -- Verifica si la consultora tiene Pedido en proceso de facturación en la campanya de proceso

            SELECT COUNT(1)
              INTO lsclientesencontrados
              FROM int_solic_conso_cabec iscc
             WHERE iscc.cod_clie = pscodcliente --codigo de cliente
               AND iscc.ind_ocs_proc = '1'
               AND iscc.cod_peri = pscodperiodo;

            IF lsclientesencontrados = 0 THEN
              --Indicador que servirá para seguir con la validación 'Obtener Zonas Asociadas'
              lsindboolean := TRUE;
            ELSE
              -- Si se encuentra coincidencia
              SELECT num_vali || '_' || des_vali
                INTO psmensajesalida
                FROM mae_valid_clien_param
               WHERE pais_cod_pais = pscodpais
                 AND sist_cod_sist = 'MAE'
                 AND num_vali = 'NVS-VAL-07'
                 AND tip_vali = '4';
            END IF;

          END IF;

        ELSE
          --si es diferente al Estatus Nueva("02") ó Reactivada("08")
          SELECT num_vali || '_' || des_vali
            INTO psmensajesalida
            FROM mae_valid_clien_param
           WHERE pais_cod_pais = pscodpais
             AND sist_cod_sist = 'MAE'
             AND num_vali = 'NVS-VAL-04'
             AND tip_vali = '4';
        END IF;
      ELSE
        -- si no se encuentra activo (IND_ACTI = 0)
        SELECT num_vali || '_' || des_vali
          INTO psmensajesalida
          FROM mae_valid_clien_param
         WHERE pais_cod_pais = pscodpais
           AND sist_cod_sist = 'MAE'
           AND num_vali = 'MAE-VAL-25'
           AND tip_vali = '4';

      END IF;
    ELSE
      -- Si la consultora no existe en el Maesto de Clientes muestra un mensaje
      SELECT num_vali || '_' || des_vali
        INTO psmensajesalida
        FROM mae_valid_clien_param
       WHERE pais_cod_pais = pscodpais
         AND sist_cod_sist = 'MAE'
         AND num_vali = 'MAE-VAL-24'
         AND tip_vali = '4';
    END IF;

    IF lsindboolean THEN

      --DEPENTE DEL PROCEDURE COM_PR_NVS_OBTEN_ZONAS_ASOCI PARA REALIZAR ESTA VALIDACION
      BEGIN

        SELECT nvs.cod_prog
          INTO lscodigoprograma
          FROM (SELECT a3.zzon_oid_zona oid_zona
                  FROM mae_clien_unida_admin a1,
                       zon_terri_admin       a2,
                       zon_secci             a3
                 WHERE a1.ztad_oid_terr_admi = a2.oid_terr_admi
                   AND a2.zscc_oid_secc = a3.oid_secc
                   AND a1.clie_oid_clie = lsoidcliente
                   AND a1.ind_acti = '1') b,
               nvs_lista_zonas_asoci nvs
         WHERE b.oid_zona = nvs.oid_zona
         GROUP BY nvs.cod_prog;

        IF lscodigoestatus = '02' THEN
          lsindicador := '0';
        END IF;

        IF lscodigoestatus = '08' THEN

          SELECT COUNT(1)
            INTO lncantidad
            FROM cup_consu_nueva
           WHERE cod_pais = pscodpais
             AND cod_prog = lscodigoprograma
             AND cod_cons = pscodcliente;

          IF lncantidad = 0 THEN
            lsindicador := '0';

          ELSE
            lsindicador := '1';
          END IF;

        END IF;

        SELECT val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' ||
               val_ape2
          INTO lsnombreclientecompleto
          FROM mae_clien
         WHERE oid_clie = lsoidcliente;
        -- Si el código pasa todas las validaciones
        psmensajesalida := lscodigoprograma || '_' || lsindicador || '_' ||
                           lsnombreclientecompleto;

      EXCEPTION
        WHEN no_data_found THEN
          -- Si la Zona no se encuentra en la Lista de Zonas Asociadas
          SELECT num_vali || '_' || des_vali
            INTO psmensajesalida
            FROM mae_valid_clien_param
           WHERE pais_cod_pais = pscodpais
             AND sist_cod_sist = 'MAE'
             AND num_vali = 'NVS-VAL-08'
             AND tip_vali = '4';
      END;

    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'CUP_PR_NVS_VALID_CONSU: ' || ls_sqlerrm);

  END cup_pr_nvs_valid_consu;

  /***************************************************************************
  Descripcion       : Carga a la tabla temporal para la validacion
                         de las zonas por programas
  Fecha Creacion    : 11/12/2012
  Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE cup_pr_nvs_obten_zonas_asoci
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodusuario VARCHAR2
  ) IS

  BEGIN

    DELETE FROM nvs_lista_zonas_asoci WHERE usu_regi = pscodusuario;

    --Insertar Todas las Zonas Asociadas
    INSERT INTO nvs_lista_zonas_asoci
      (cod_prog,
       oid_zona,
       usu_regi)
      SELECT pnva_ua.cod_prog,
             gen_pkg_gener.gen_fn_devuelve_id_zona(pscodpais,
                                                   'T',
                                                   'VD',
                                                   pnva_ua.cod_regi,
                                                   pnva_ua.cod_zona) oid_zona,
             pnva_ua.usu_regi
        FROM (SELECT pnva.cod_pais,
                     pscodperiodo       cod_peri,
                     pnva.cod_prog,
                     zr.cod_regi,
                     zz.cod_zona,
                     pnva.ind_prog_obli,
                     pscodusuario       usu_regi
                FROM cup_prog_nueva_cupon pnva,
                     zon_zona             zz,
                     zon_regio            zr
               WHERE zz.ind_acti = '1'
                 AND zz.ind_borr = '0'
                 AND zr.oid_regi = zz.zorg_oid_regi
                 AND 0 = (SELECT COUNT(1)
                            FROM nvs_param_progr_unadm a
                           WHERE a.pnvs_cod_prog = pnva.cod_prog
                             AND a.pais_cod_pais = pnva.cod_pais
                             AND a.est_regi = '1')
                 AND pnva.cam_inic <= pscodperiodo -- codigo de periodo
                 AND pnva.cam_fin >= pscodperiodo -- codigo de periodo
                 AND pnva.cod_pais = pscodpais --codigo de pais
              UNION
              SELECT pnva.cod_pais,
                     pscodperiodo       cod_peri,
                     pnva.cod_prog,
                     a.cod_regi,
                     zz.cod_zona,
                     pnva.ind_prog_obli,
                     pscodusuario       usu_regi
                FROM cup_prog_nueva_cupon  pnva,
                     zon_zona              zz,
                     nvs_param_progr_unadm a
               WHERE pnva.cod_prog = a.pnvs_cod_prog
                 AND pnva.cod_pais = a.pais_cod_pais
                 AND a.est_regi = '1'
                 AND a.cod_regi IS NOT NULL
                 AND a.cod_zona IS NULL
                 AND zz.zorg_oid_regi = a.zorg_oid_regi
                 AND zz.ind_acti = '1'
                 AND zz.ind_borr = '0'
                 AND pnva.cam_inic <= pscodperiodo
                 AND pnva.cam_fin >= pscodperiodo
                 AND pnva.cod_pais = pscodpais
              UNION
              SELECT pnva.cod_pais,
                     pscodperiodo       cod_peri,
                     pnva.cod_prog,
                     a.cod_regi,
                     a.cod_zona,
                     pnva.ind_prog_obli,
                     pscodusuario       usu_regi
                FROM cup_prog_nueva_cupon  pnva,
                     nvs_param_progr_unadm a
               WHERE pnva.cod_pais = a.pais_cod_pais
                 AND pnva.cod_prog = a.pnvs_cod_prog
                 AND a.est_regi = '1'
                 AND a.cod_regi IS NOT NULL
                 AND a.cod_zona IS NOT NULL
                 AND pnva.cam_inic <= pscodperiodo
                 AND pnva.cam_fin >= pscodperiodo
                 AND pnva.cod_pais = pscodpais) pnva_ua;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'CUP_PR_NVS_OBTEN_ZONAS_ASOCI: ' ||
                              ls_sqlerrm);

  END cup_pr_nvs_obten_zonas_asoci;
  --PER-SiCC-2013-0141 - INI
  /**************************************************************************
  Descripcion        : CUP_FN_DEVUE_NUMRO_PEDID_ORIG
                     Devuelve numero de pedidos por origen
  Fecha Creacion     : 07/05/2013
  Parametros Entrada:
      pscodpais : Codigo Nivel
      pscodperi : Codigo Nivel
      pscodclie : Codigo Nivel
  Autor              : CSVD - FFVV
  ***************************************************************************/
  FUNCTION cup_fn_devue_numro_pedid_orig
  (
    pscodpais VARCHAR2,
    pscodperi VARCHAR2,
    pscodclie VARCHAR2
  ) RETURN NUMBER IS
    val_cant NUMBER;

  BEGIN

    SELECT COUNT(*)
      INTO val_cant
      FROM (SELECT iscc.cod_peri,
                   --iscc.SOCA_OID_SOLI_CABE_REFE,
                   nvl(iscc.ind_rece_cc, '0'),
                   nvl(iscc.ind_rece_dd, '0'),
                   nvl(iscc.ind_rece_digi, '0'),
                   nvl(iscc.ind_rece_ocr, '0'),
                   nvl(iscc.ind_rece_web, '0'),
                   nvl(iscc.ind_rece_ivr, '0')
              FROM int_solic_conso_cabec iscc
             WHERE nvl(iscc.ind_rece_web, '0') = 1
               AND iscc.cod_clie = pscodclie
            UNION
            SELECT hscc.cod_peri,
                   --hscc.SOCA_OID_SOLI_CABE_REFE,
                   nvl(hscc.ind_rece_cc, '0'),
                   nvl(hscc.ind_rece_dd, '0'),
                   nvl(hscc.ind_rece_digi, '0'),
                   nvl(hscc.ind_rece_ocr, '0'),
                   nvl(hscc.ind_rece_web, '0'),
                   nvl(hscc.ind_rece_ivr, '0')
              FROM ped_histo_solic_conso_cabec hscc
             WHERE nvl(hscc.ind_rece_web, '0') = 1
               AND hscc.ind_ocs_proc = 1
               AND hscc.ind_proc_gp2 = 1
               AND hscc.cod_clie = pscodclie) ped_clien
     WHERE ped_clien.cod_peri >= pscodperi;

    RETURN val_cant;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
  END cup_fn_devue_numro_pedid_orig;
  --PER-SiCC-2013-0141 - FIN

  /**************************************************************************
   Descripcion        :
                      Relaliza las validaciones antes rel registo de metas
   Fecha Creacion     : 13/05/2013
   Parametros Entrada:
       pscodigopais : Codigo del pais
       pscodigoconsultora : Codigo de la consultora que se esta registrando

   Parametros de Salida: Retorna un codigo de error indicando el error obtenido,
                         CERO en otro caso
   Autor              : Ivan Tocto
  **************************************************************************/
  PROCEDURE cup_pr_valida_regis_metas
  (
    pscodigopais       VARCHAR2,
    pscodigoconsultora VARCHAR2,
    pscodigoerror      OUT VARCHAR2
  ) IS
    lnstatus              NUMBER := 0;
    lscampanyafacturacion VARCHAR2(6);
  BEGIN

    pscodigoerror := '0';

    SELECT cod_peri
      INTO lscampanyafacturacion
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    /*
    1)  Se valida que el código de la consultora pertenezca a una consultora de
    negocio ú oficina, en la entidad Tipos de Clientes, filtrando por consultora
    y que el tipo de cliente sea 2 y el subtipo de cliente sea 1 o 21.
    Si no cumple esta condición no se permitirá registrar la meta de la consultora
    y el foco se mantendrá en el campo.
    */

    -- si es 1=OK
    SELECT COUNT(1) val_01
      INTO lnstatus
      FROM mae_clien            mc,
           mae_clien_tipo_subti tc
     WHERE mc.oid_clie = tc.clie_oid_clie
       AND mc.cod_clie = pscodigoconsultora
       AND tc.ticl_oid_tipo_clie = 2
       AND tc.sbti_oid_subt_clie IN (1, 21);

    IF lnstatus = 1 THEN

      /*
      2)  Se valida que el Indicador de Activa de la consultora en la entidad Datos
          Adicionales se encuentre activado. Si el indicador no está activado,
          no se permitirá registrar la meta de la consultora y el foco se mantendrá en el campo.
      */

      -- si es 1=OK
      SELECT COUNT(1)
        INTO lnstatus
        FROM mae_clien             mc,
             mae_clien_datos_adici da
       WHERE mc.oid_clie = da.clie_oid_clie
         AND mc.cod_clie = pscodigoconsultora
         AND da.ind_acti = '1';

      IF lnstatus = 1 THEN
        /*
        3)  Se valida que la consultora no tenga una meta registrada en la entidad
        Registro de Metas  cuyas campañas de inicio y fin de meta se
        encuentren en el rango de la campaña de facturación. De encontrarse,
        no se permitirá registrar la meta de la consultora y el foco se mantendrá en el campo.
        */

        -- si es 1=ERROR
        SELECT COUNT(1)
          INTO lnstatus
          FROM nvs_consu_logro
         WHERE cod_clie = pscodigoconsultora
           AND lscampanyafacturacion >= cmp_inic
           AND lscampanyafacturacion <= cmp_fina
           AND est_regi = '1';

        IF lnstatus = 0 THEN

          /*
          4)  Si el  indicador de nueva ( ind_nueva ) de la entidad Parámetros
          de Logro está activado, entonces se verifica si la consultora se encuentra
          en su segunda campaña de pedido, esto es: La diferencia entre la campaña actual
          de facturación menos la campaña de ingreso de la consultora en la entidad Primeros
          Pedidos, si la diferencia es mayor a 1, no se permitirá registrar la meta de la
          consultora y el foco se mantendrá en el campo
          */

          SELECT COUNT(1)
            INTO lnstatus
            FROM nvs_param_logro
           WHERE cod_pais = pscodigopais
             AND ind_nuev = '1';

          IF lnstatus = 1 THEN

            -- SI ES 1=OK
            SELECT gen_pkg_gener.gen_fn_devue_difer_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(pc.perd_oid_peri),
                                                          lscampanyafacturacion)
              INTO lnstatus
              FROM mae_clien             mc,
                   mae_clien_prime_conta pc
             WHERE mc.oid_clie = pc.clie_oid_clie
               AND mc.cod_clie = pscodigoconsultora;

            IF lnstatus = 1 THEN
              pscodigoerror := '0';
            ELSE
              pscodigoerror := '4';
            END IF;

          END IF;
        ELSE
          pscodigoerror := '3';
        END IF;
      ELSE
        pscodigoerror := '2';
      END IF;
    ELSE
      pscodigoerror := '1';
    END IF;

  END cup_pr_valida_regis_metas;

  /***************************************************************************
  Descripcion       : Genera el Reporte Nuevas Unidades Atendidas en CSV
  Fecha Creacion    : 13/11/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/

  PROCEDURE cup_pr_gener_nueva_unida_atend
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    psnombrearchivo VARCHAR2,
    pstitulo        VARCHAR2,
    psdirectorio    OUT VARCHAR2
  ) IS

    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 5000;
    v_handle   utl_file.file_type;
    lslinea    VARCHAR2(4000);

    CURSOR c_interfaz IS
      SELECT dat_repo.cam_des     cam_des,
             dat_repo.nive        nive,
             dat_repo.cod_sap     cod_sap,
             prod.des_pro         des,
             dat_repo.tot_uni     tot_uni,
             dat_repo.tot_uni_def tot_uni_def
        FROM (SELECT phcd.cod_peri cam_des,
                     em.cod_nivel nive,
                     p.cod_sap cod_sap,
                     SUM(sp.num_unid_por_aten) tot_uni,
                     SUM(CASE
                           WHEN phcd.sta_proc = 'L' THEN
                            sp.num_unid_por_aten
                           ELSE
                            0
                         END) tot_uni_def
                FROM ped_histo_solic_conso_cabec phsc,
                     ped_histo_solic_conso_detal phcd,
                     ped_solic_cabec             sc,
                     ped_solic_posic             sp,
                     ped_tipo_solic              ts,
                     ped_tipo_solic_pais         tsp,
                     mae_produ                   p,
                     cup_equiv_matr              em
               WHERE phsc.cod_peri = psperiodo
                 AND phsc.cod_peri = phcd.cod_peri
                 AND phsc.cod_clie = phcd.cod_clie
                 AND phsc.num_lote = phcd.num_lote
                 AND phsc.sec_nume_docu = phcd.sec_nume_docu_cabe
                 AND phcd.sta_proc IN ('L', 'B')
                 AND phcd.ind_erro_sse = 0
                 AND phsc.soca_oid_soli_cabe_refe = sc.oid_soli_cabe
                 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
                 AND sc.perd_oid_peri =
                     gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo)
                 AND sc.fec_fact IS NOT NULL
                 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.cod_tipo_soli = 'SOC'
                 AND phcd.cod_vent = sp.val_codi_vent
                 AND sp.prod_oid_prod = p.oid_prod
                 AND phcd.cod_peri = em.cod_peri(+)
                 AND phcd.cod_vent = em.cod_venta(+)
               GROUP BY phcd.cod_peri,
                        em.cod_nivel,
                        p.cod_sap
              UNION
              SELECT phcd.cod_peri cam_des,
                     em.cod_nivel nive,
                     p.cod_sap cod_sap,
                     SUM(sp.num_unid_por_aten) tot_uni,
                     SUM(CASE
                           WHEN phcd.sta_proc = 'L' THEN
                            sp.num_unid_por_aten
                           ELSE
                            0
                         END) tot_uni_def
                FROM int_solic_conso_cabec phsc,
                     int_solic_conso_detal phcd,
                     ped_solic_cabec       sc,
                     ped_solic_posic       sp,
                     ped_tipo_solic        ts,
                     ped_tipo_solic_pais   tsp,
                     mae_produ             p,
                     cup_equiv_matr        em
               WHERE phsc.cod_peri = psperiodo
                 AND phsc.cod_peri = phcd.cod_peri
                 AND phsc.cod_clie = phcd.cod_clie
                 AND phsc.num_lote = phcd.num_lote
                 AND phsc.sec_nume_docu = phcd.sec_nume_docu_cabe
                 AND phcd.sta_proc IN ('L', 'B')
                 AND phcd.ind_erro_sse = 0
                 AND phsc.soca_oid_soli_cabe_refe = sc.oid_soli_cabe
                 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
                 AND sc.perd_oid_peri =
                     gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo)
                 AND sc.fec_fact IS NOT NULL
                 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.cod_tipo_soli = 'SOC'
                 AND phcd.cod_vent = sp.val_codi_vent
                 AND sp.prod_oid_prod = p.oid_prod
                 AND phcd.cod_peri = em.cod_peri(+)
                 AND phcd.cod_vent = em.cod_venta(+)
               GROUP BY phcd.cod_peri,
                        em.cod_nivel,
                        p.cod_sap) dat_repo,
             (SELECT p.cod_sap         cod_sap,
                     des_prod.val_i18n des_pro
                FROM mae_produ p,
                     (SELECT v.val_oid,
                             v.val_i18n
                        FROM v_gen_i18n_sicc v
                       WHERE v.attr_enti = 'MAE_PRODU'
                         AND v.idio_oid_idio = 1) des_prod
               WHERE p.oid_prod = des_prod.val_oid) prod
       WHERE dat_repo.cod_sap = prod.cod_sap;

    TYPE interfaztipo IS RECORD(

      v_cam_des     ped_histo_solic_conso_detal.cod_peri%TYPE,
      v_nive        cup_equiv_matr.cod_nivel%TYPE,
      v_cod_sap     mae_produ.cod_sap%TYPE,
      v_des_prod    v_gen_i18n_sicc.val_i18n%TYPE,
      v_tot_uni     NUMBER(20),
      v_tot_uni_def NUMBER(20));

    TYPE interfaztab IS TABLE OF interfaztipo;
    interfazrecord interfaztab;
    lbabrirutlfile BOOLEAN;

  BEGIN
    lbabrirutlfile := TRUE;

    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     psnombrearchivo,
                                                     '.csv',
                                                     pstitulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfile := FALSE;

      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x)
                     .v_cam_des || ',' || '=T("' || interfazrecord(x).v_nive || '")' || ',' ||
                      '=T("' || interfazrecord(x).v_cod_sap || '")' || ',' || interfazrecord(x)
                     .v_des_prod || ',' || interfazrecord(x).v_tot_uni || ',' || interfazrecord(x)
                     .v_tot_uni_def;

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR CUP_PR_GENER_NUEVA_UNIDA_ATEND: ' ||
                              ls_sqlerrm);
  END cup_pr_gener_nueva_unida_atend;

  /***************************************************************************
  Descripcion       : Genera Data para el Reporte de Nuevas Cupones
  Fecha Creacion    : 03/09/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE cup_pr_gener_repor_nueva_cupon
  (
    pscodigopais         VARCHAR2,
    pscodigoperiododesde VARCHAR2,
    pscodigoperiodohasta VARCHAR2
  ) IS
    CURSOR c_nuevas
    (
      vnoidpais         NUMBER,
      vnoidperiododesde NUMBER,
      vnoidperiodohasta NUMBER
    ) IS
      SELECT b.cod_clie,
             b.oid_clie,
             a.perd_oid_peri,
             gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri)
        FROM mae_clien_histo_estat a,
             mae_clien             b
       WHERE b.pais_oid_pais = vnoidpais
         AND a.clie_oid_clie = b.oid_clie
         AND a.esta_oid_esta_clie = 2
         AND a.perd_oid_peri >= vnoidperiododesde
         AND a.perd_oid_peri_peri_fin <= vnoidperiodohasta;

    TYPE tnuevas IS RECORD(
      cod_clie   mae_clien.cod_clie%TYPE,
      oid_clie   mae_clien.oid_clie%TYPE,
      oidperiodo NUMBER,
      codperiodo VARCHAR2(6));

    TYPE tnuevastab IS TABLE OF tnuevas;
    tnuevasrecord   tnuevastab;
    nuevasrecord    tnuevas;
    w_filas         NUMBER := 5000;
    lscodperiodo2do VARCHAR2(6);
    lnoidperiodo2do NUMBER;
    lscodperiodo3er VARCHAR2(6);
    lnoidperiodo3er NUMBER;
    lscodperiodo4to VARCHAR2(6);
    lnoidperiodo4to NUMBER;

    lnoidperiododesde NUMBER;
    lnoidperiodohasta NUMBER;

    lnoidpais NUMBER;
    lnidmarca NUMBER;
    lnidcanal NUMBER;

    lbpasopedido          BOOLEAN;
    lnmontoventa2dopedido ped_solic_cabec_acum2.val_mont_tota%TYPE;
    lnmontoventa3erpedido ped_solic_cabec_acum2.val_mont_tota%TYPE;
    lnmontoventa4topedido ped_solic_cabec_acum2.val_mont_tota%TYPE;
    lnmontodscto2do       nvs_clien_descu.mon_desc%TYPE;
    lnmontodscto3er       nvs_clien_descu.mon_desc%TYPE;
    lnmontodscto4to       nvs_clien_descu.mon_desc%TYPE;

    lsindicadordscto2do VARCHAR2(1);
    lsindicadordscto3er VARCHAR2(1);
    lsindicadordscto4to VARCHAR2(1);

    lscodigoregion  VARCHAR2(2);
    lscodigozona    VARCHAR2(4);
    lnoidterriadmin NUMBER;

  BEGIN
    lnoidpais         := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidcanal         := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
    lnidmarca         := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
    lnoidperiododesde := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiododesde);
    lnoidperiodohasta := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodohasta);

    DELETE FROM cup_repor_nueva_cupon;

    OPEN c_nuevas(lnoidpais, lnoidperiododesde, lnoidperiodohasta);
    LOOP
      FETCH c_nuevas BULK COLLECT
        INTO tnuevasrecord LIMIT w_filas;
      IF tnuevasrecord.count > 0 THEN
        FOR x IN tnuevasrecord.first .. tnuevasrecord.last
        LOOP
          nuevasrecord    := tnuevasrecord(x);
          lbpasopedido    := TRUE;
          lscodperiodo2do := per_pkg_repor_perce.per_fn_obtie_perio(nuevasrecord.codperiodo,
                                                                    lnoidpais,
                                                                    lnidmarca,
                                                                    lnidcanal,
                                                                    1);
          lnoidperiodo2do := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodperiodo2do,
                                                                        lnidmarca,
                                                                        lnidcanal);

          /* Verificando que haya pasado pedido en 2da, 3era y 4ta Campaña seguida */
          BEGIN
            SELECT x.val_mont_tota
              INTO lnmontoventa2dopedido
              FROM ped_solic_cabec_acum2 x
             WHERE x.clie_oid_clie = nuevasrecord.oid_clie
               AND x.perd_oid_peri = lnoidperiodo2do;
          EXCEPTION
            WHEN no_data_found THEN
              lbpasopedido := FALSE;
          END;
          IF lbpasopedido THEN
            lscodperiodo3er := per_pkg_repor_perce.per_fn_obtie_perio(nuevasrecord.codperiodo,
                                                                      lnoidpais,
                                                                      lnidmarca,
                                                                      lnidcanal,
                                                                      2);
            lnoidperiodo3er := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodperiodo3er,
                                                                          lnidmarca,
                                                                          lnidcanal);

            BEGIN
              SELECT x.val_mont_tota
                INTO lnmontoventa3erpedido
                FROM ped_solic_cabec_acum2 x
               WHERE x.clie_oid_clie = nuevasrecord.oid_clie
                 AND x.perd_oid_peri = lnoidperiodo3er;
            EXCEPTION
              WHEN no_data_found THEN
                lbpasopedido := FALSE;
            END;

            IF lbpasopedido THEN
              lscodperiodo4to := per_pkg_repor_perce.per_fn_obtie_perio(nuevasrecord.codperiodo,
                                                                        lnoidpais,
                                                                        lnidmarca,
                                                                        lnidcanal,
                                                                        3);
              lnoidperiodo4to := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodperiodo4to,
                                                                            lnidmarca,
                                                                            lnidcanal);

              BEGIN
                SELECT x.val_mont_tota
                  INTO lnmontoventa4topedido
                  FROM ped_solic_cabec_acum2 x
                 WHERE x.clie_oid_clie = nuevasrecord.oid_clie
                   AND x.perd_oid_peri = lnoidperiodo4to;
              EXCEPTION
                WHEN no_data_found THEN
                  lbpasopedido := FALSE;
              END;
            END IF;

          END IF;

          /* Verificando que haya tenido descuento en 2da o 3era o 4ta Campaña seguida */
          IF lbpasopedido THEN
            lsindicadordscto2do := 'S';
            BEGIN
              SELECT x.mon_desc
                INTO lnmontodscto2do
                FROM nvs_clien_descu x
               WHERE x.pais_cod_pais = pscodigopais
                 AND x.cod_clie = nuevasrecord.cod_clie
                 AND x.cam_proc = lscodperiodo2do;
            EXCEPTION
              WHEN no_data_found THEN
                lsindicadordscto2do := 'N';
            END;

            lsindicadordscto3er := 'S';
            BEGIN
              SELECT x.mon_desc
                INTO lnmontodscto3er
                FROM nvs_clien_descu x
               WHERE x.pais_cod_pais = pscodigopais
                 AND x.cod_clie = nuevasrecord.cod_clie
                 AND x.cam_proc = lscodperiodo3er;
            EXCEPTION
              WHEN no_data_found THEN
                lsindicadordscto3er := 'N';
            END;

            lsindicadordscto4to := 'S';
            BEGIN
              SELECT x.mon_desc
                INTO lnmontodscto4to
                FROM nvs_clien_descu x
               WHERE x.pais_cod_pais = pscodigopais
                 AND x.cod_clie = nuevasrecord.cod_clie
                 AND x.cam_proc = lscodperiodo4to;
            EXCEPTION
              WHEN no_data_found THEN
                lsindicadordscto4to := 'N';
            END;
          END IF;

          /* Obteniendo Unidad Administrativa */
          IF lbpasopedido THEN
            lscodigoregion := NULL;
            lscodigozona   := NULL;
            BEGIN
              SELECT x.ztad_oid_terr_admi
                INTO lnoidterriadmin
                FROM mae_clien_unida_admin x
               WHERE x.clie_oid_clie = nuevasrecord.oid_clie
                 AND x.ind_acti = 1;
            EXCEPTION
              WHEN no_data_found THEN
                lnoidterriadmin := NULL;
            END;
            IF lnoidterriadmin IS NOT NULL THEN
              SELECT e.cod_regi,
                     d.cod_zona
                INTO lscodigoregion,
                     lscodigozona
                FROM zon_terri_admin a,
                     zon_secci       c,
                     zon_zona        d,
                     zon_regio       e
               WHERE a.pais_oid_pais = lnoidpais
                 AND a.oid_terr_admi = lnoidterriadmin
                 AND c.oid_secc = a.zscc_oid_secc
                 AND d.oid_zona = c.zzon_oid_zona
                 AND e.oid_regi = d.zorg_oid_regi;
            END IF;
          END IF;

          /* Insertando Registro */
          IF lbpasopedido THEN
            INSERT INTO cup_repor_nueva_cupon
              (cod_peri_prim_pedi,
               cod_regi,
               cod_zona,
               cod_clie,
               mon_vent_segu_pedi,
               ind_dcto_segu_pedi,
               mon_dcto_segu_pedi,
               mon_vent_terc_pedi,
               ind_dcto_terc_pedi,
               mon_dcto_terc_pedi,
               mon_vent_cuar_pedi,
               ind_dcto_cuar_pedi,
               mon_dcto_cuar_pedi)
            VALUES
              (nuevasrecord.codperiodo,
               lscodigoregion,
               lscodigozona,
               nuevasrecord.cod_clie,
               lnmontoventa2dopedido,
               lsindicadordscto2do,
               lnmontodscto2do,
               lnmontoventa3erpedido,
               lsindicadordscto3er,
               lnmontodscto3er,
               lnmontoventa4topedido,
               lsindicadordscto4to,
               lnmontodscto4to);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_nuevas%NOTFOUND;
    END LOOP;
    CLOSE c_nuevas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_GENER_REPOR_NUEVA_CUPON: ' ||
                              ls_sqlerrm);

  END cup_pr_gener_repor_nueva_cupon;

  /***************************************************************************
  Descripcion       : Genera el Reporte Nuevas Cupones en CSV.
  Fecha Creacion    : 03/09/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE cup_pr_repor_nueva_cupon_csv
  (
    pscodigopais    VARCHAR2,
    psnombrearchivo VARCHAR2,
    pstitulo        VARCHAR2,
    psdirectorio    OUT VARCHAR2
  ) IS
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 5000;
    v_handle   utl_file.file_type;
    lslinea    VARCHAR2(4000);
    lnposicion NUMBER;

    CURSOR c_nuevas IS
      SELECT cod_peri_prim_pedi,
             cod_regi,
             cod_zona,
             cod_clie,
             mon_vent_segu_pedi,
             ind_dcto_segu_pedi,
             mon_dcto_segu_pedi,
             mon_vent_terc_pedi,
             ind_dcto_terc_pedi,
             mon_dcto_terc_pedi,
             mon_vent_cuar_pedi,
             ind_dcto_cuar_pedi,
             mon_dcto_cuar_pedi
        FROM cup_repor_nueva_cupon x
       ORDER BY cod_peri_prim_pedi,
                cod_regi,
                cod_zona,
                cod_clie;

    TYPE tnuevas IS RECORD(
      cod_peri_prim_pedi cup_repor_nueva_cupon.cod_peri_prim_pedi%TYPE,
      cod_regi           cup_repor_nueva_cupon.cod_regi%TYPE,
      cod_zona           cup_repor_nueva_cupon.cod_zona%TYPE,
      cod_clie           cup_repor_nueva_cupon.cod_clie%TYPE,
      mon_vent_segu_pedi cup_repor_nueva_cupon.mon_vent_segu_pedi%TYPE,
      ind_dcto_segu_pedi cup_repor_nueva_cupon.ind_dcto_segu_pedi%TYPE,
      mon_dcto_segu_pedi cup_repor_nueva_cupon.mon_dcto_segu_pedi%TYPE,
      mon_vent_terc_pedi cup_repor_nueva_cupon.mon_vent_terc_pedi%TYPE,
      ind_dcto_terc_pedi cup_repor_nueva_cupon.ind_dcto_terc_pedi%TYPE,
      mon_dcto_terc_pedi cup_repor_nueva_cupon.mon_dcto_terc_pedi%TYPE,
      mon_vent_cuar_pedi cup_repor_nueva_cupon.mon_vent_cuar_pedi%TYPE,
      ind_dcto_cuar_pedi cup_repor_nueva_cupon.ind_dcto_cuar_pedi%TYPE,
      mon_dcto_cuar_pedi cup_repor_nueva_cupon.mon_dcto_cuar_pedi%TYPE);

    TYPE tnuevastab IS TABLE OF tnuevas;
    tnuevasrecord tnuevastab;

    lbabrirutlfile BOOLEAN;
  BEGIN
    lnposicion     := 1;
    lbabrirutlfile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_nuevas;
    LOOP
      FETCH c_nuevas BULK COLLECT
        INTO tnuevasrecord LIMIT w_filas;
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     psnombrearchivo,
                                                     '.csv',
                                                     pstitulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfile := FALSE;
      END IF;
      IF tnuevasrecord.count > 0 THEN
        FOR x IN tnuevasrecord.first .. tnuevasrecord.last
        LOOP
          lslinea := '"' || tnuevasrecord(x).cod_peri_prim_pedi || '"' || ',' ||
                     '=T("' || tnuevasrecord(x).cod_regi || '")' || ',' ||
                     '=T("' || tnuevasrecord(x).cod_zona || '")' || ',' ||
                     '=T("' || tnuevasrecord(x).cod_clie || '")' || ',' || '"' || tnuevasrecord(x)
                    .mon_vent_segu_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .ind_dcto_segu_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .mon_dcto_segu_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .mon_vent_terc_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .ind_dcto_terc_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .mon_dcto_terc_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .mon_vent_cuar_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .ind_dcto_cuar_pedi || '"' || ',' || '"' || tnuevasrecord(x)
                    .mon_dcto_cuar_pedi || '"';

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_nuevas%NOTFOUND;
    END LOOP;
    CLOSE c_nuevas;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR CUP_PR_REPOR_NUEVA_CUPON_CSV: ' ||
                              ls_sqlerrm);

  END cup_pr_repor_nueva_cupon_csv;
  
/**************************************************************************
  Descripcion       : Eliminar los despachos de las consultoras no constantes
  Fecha Creacion    : 20/10/2015
  Parametros Entrada:
    psCodigoPeriodo     :  Codigo de Periodo
    psFechaFacturacion  :  Fecha Facturacion

  Autor             : Aurelio Oviedo
  ***************************************************************************/
PROCEDURE CUP_PR_ELIMI_DESPA_NOCON_CRUCE (
     psCodigoPeriodo            VARCHAR2,
     psFechaFacturacion         VARCHAR2)
IS

    w_filas           NUMBER:=1000;

    --variables a almacenar
    lnExisteCampFact        NUMBER;
    vsCampCruce             seg_perio_corpo.cod_peri%type;
    vsMinCamp               seg_perio_corpo.cod_peri%type;
    lnMaxCodProgClie        CUP_CONSU_NUEVA.COD_PROG%TYPE;
    vsIndCruce              CRA_PERIO.Ind_Peri_Cruc%TYPE;
    lnIndConstancia         CUP_PROG_NUEVA_CUPON.IND_CONST%TYPE;
    lnIndConstanciaPrem     CUP_PROG_NUEVA_CUPON.IND_CONS_PREM%TYPE;
    lnIndConstanciaPremElec CUP_PROG_NUEVA_CUPON.IND_CONS_PREM_ELEC%TYPE;
    lnFechaFact             DATE;
        
    CURSOR c_clientes IS
        SELECT PSP.OID_SOLI_POSI,
               ISCC.COD_CLIE,
               ISCD.COD_VENT,
               ISCD.STA_PROC
          FROM INT_SOLIC_CONSO_CABEC ISCC, 
               MAE_CLIEN_DATOS_ADICI MCDA,
               INT_SOLIC_CONSO_DETAL ISCD,
               PED_SOLIC_POSIC PSP
         WHERE ISCC.CLIE_OID_CLIE = MCDA.CLIE_OID_CLIE
           AND ISCC.COD_CLIE = ISCD.COD_CLIE
           AND ISCC.COD_PERI = ISCD.COD_PERI
           AND ISCC.SOCA_OID_SOLI_CABE_REFE = PSP.SOCA_OID_SOLI_CABE
           AND ISCC.IND_OC = '1'
           AND MCDA.ESTA_OID_ESTA_CLIE = 4
           AND ISCD.IND_ERRO_SSE = '0'
           AND ISCD.STA_PROC IN ('B', 'C', 'L')
           AND PSP.VAL_CODI_VENT = ISCD.COD_VENT
           AND ISCC.NUM_LOTE =  ISCD.NUM_LOTE
           AND ISCC.COD_PERI = psCodigoPeriodo;

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;

BEGIN
    
    lnFechaFact := TO_DATE(psFechaFacturacion, 'DD/MM/YYYY');

    SELECT COUNT(1)
      INTO lnExisteCampFact
      FROM CRA_PERIO
     WHERE lnFechaFact >= FEC_INIC
       AND lnFechaFact <= FEC_FINA;

    IF lnExisteCampFact > 1 THEN
        SELECT MAX(COD_PERI), Min(COD_PERI)
          INTO vsCampCruce, vsMinCamp
          FROM CRA_PERIO cr, SEG_PERIO_CORPO sg
         WHERE lnFechaFact >= FEC_INIC
           AND lnFechaFact <= FEC_FINA
           AND cr.peri_oid_peri = sg.oid_peri ;
           
          SELECT CRA.IND_PERI_CRUC
          INTO vsIndCruce
          FROM CRA_PERIO CRA, SEG_PERIO_CORPO CP
         WHERE COD_PERI = vsMinCamp
           AND CRA.PERI_OID_PERI = CP.OID_PERI;
           
           
        IF vsCampCruce = psCodigoPeriodo AND vsIndCruce = 1 THEN
            -- Abrimos el cursor clientes
            OPEN c_clientes;
            LOOP
                FETCH c_clientes BULK COLLECT
                INTO clieclasRecord LIMIT w_filas;

                IF  clieclasRecord.COUNT > 0 THEN
                    FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
                        
                        SELECT MAX(COD_PROG) 
                          INTO lnMaxCodProgClie
                          FROM CUP_CONSU_NUEVA
                         WHERE COD_CONS = clieclasRecord(x).cod_clie;
                        
                        IF lnMaxCodProgClie IS NOT NULL THEN
                            SELECT IND_CONST, IND_CONS_PREM, IND_CONS_PREM_ELEC
                              INTO lnIndConstancia, lnIndConstanciaPrem, lnIndConstanciaPremElec
                              FROM CUP_PROG_NUEVA_CUPON
                             WHERE COD_PROG = lnMaxCodProgClie;
                            
                            IF lnIndConstancia = '1' THEN
                                IF clieclasRecord(x).sta_proc = 'B' THEN
                                    DELETE FROM PED_SOLIC_POSIC
                                     WHERE OID_SOLI_POSI = clieclasRecord(x).oid_soli_posi;
                                    
                                    UPDATE INT_SOLIC_CONSO_DETAL
                                       SET STA_PROC = 'X'
                                     WHERE COD_CLIE = clieclasRecord(x).cod_clie
                                       AND COD_VENT = clieclasRecord(x).cod_vent
                                       AND COD_PERI = psCodigoPeriodo;
                                END IF;
                            END IF;
                                
                            IF lnIndConstanciaPrem = '1' THEN
                                IF clieclasRecord(x).sta_proc = 'C' THEN
                                    DELETE FROM PED_SOLIC_POSIC
                                     WHERE OID_SOLI_POSI = clieclasRecord(x).oid_soli_posi;
                                     
                                    UPDATE INT_SOLIC_CONSO_DETAL
                                       SET STA_PROC = 'X'
                                     WHERE COD_CLIE = clieclasRecord(x).cod_clie
                                       AND COD_VENT = clieclasRecord(x).cod_vent
                                       AND COD_PERI = psCodigoPeriodo;
                                END IF;
                            END IF;
                                
                            IF lnIndConstanciaPremElec = '1' THEN
                                IF clieclasRecord(x).sta_proc = 'L' THEN
                                    DELETE FROM PED_SOLIC_POSIC
                                     WHERE OID_SOLI_POSI = clieclasRecord(x).oid_soli_posi;
                                     
                                    UPDATE INT_SOLIC_CONSO_DETAL
                                       SET STA_PROC = 'X'
                                     WHERE COD_CLIE = clieclasRecord(x).cod_clie
                                       AND COD_VENT = clieclasRecord(x).cod_vent
                                       AND COD_PERI = psCodigoPeriodo;
                                END IF;
                            END IF;
                        END IF;
                    END LOOP;
                END IF;
            EXIT WHEN c_clientes%NOTFOUND;
            END LOOP;
        END IF;
    END IF;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR CUP_PR_ELIMI_DESPA_NOCON_CRUCE: '||ls_sqlerrm);

END CUP_PR_ELIMI_DESPA_NOCON_CRUCE;

END cup_pkg_prog_nuevas;
/
