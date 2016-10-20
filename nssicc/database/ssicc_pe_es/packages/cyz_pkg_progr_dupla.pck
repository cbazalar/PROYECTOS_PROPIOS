CREATE OR REPLACE PACKAGE "CYZ_PKG_PROGR_DUPLA" AS

  w_filas NUMBER := 1000;

  /* Declaracion de procedures */

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta en el buzon los mensajes configurados
                      para las consultoras que han adquirido un determinado producto,
                      asociado a un programa especifico.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Codigo Usuario
              psIndicadorValidaClasificacion : Indicador que determina si el proceso
                                               ademas de validar el despacho valida que
                                               la consultora tenga la clasificacion
                                               asociada a la estrategia.
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_progr
  (
    pscodigopais                   IN VARCHAR2,
    pscodigoprograma               IN VARCHAR2,
    pscodigoperiodo                IN VARCHAR2,
    pscodigousuario                IN VARCHAR2,
    psindicadorvalidaclasificacion IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que identifica a los clientes a los cuales se
                      les ha de insertar un mensaje en el buzon, en base a si se le
                      despacho el producto del programa asociado.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psIndicadorValidaClasificacion : Indicador que determina si el proceso
                                               ademas de validar el despacho valida que
                                               la consultora tenga la clasificacion
                                               asociada a la estrategia.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_clien_mensa_produ
  (
    pscodigopais                   IN VARCHAR2,
    pscodigoprograma               IN VARCHAR2,
    pscodigoperiodo                IN VARCHAR2,
    psindicadorvalidaclasificacion IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los mensajes en el buzon de los
                      clientes identificados por el proceso anterior.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_buzon_mensa
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que elimina mensajes antiguos que no han
                      sido impresos.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
  ***************************************************************************/
  PROCEDURE cyz_pr_elimi_mensa_nimpr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta a la tabla de
                      consolidado de detalles, si estos han sido solicitados
                      desde la web.
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_premi_solic
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Funcion que devuelve el numero de unidades totales de un
                      producto a partir de la tabla INT_SOLIC_CONSO_DETAL los cuales
                      no estan rechazados por OCR ni por SSE.  Se utiliza para evitar
                      que el proceso anterior genere pedidos con un solo detalle de
                      premio.
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoCliente : Codigo de Periodo
              psNumeroLote : Codigo de Usuario
  ***************************************************************************/
  FUNCTION cyz_fn_devue_numer_unida
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    psnumerolote    IN VARCHAR2
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica la primeras duplas inscritas
                      y que cuentan con correo electronico de una cliente, de tal
                      forma que sirva de input para la identificacion de las
                      clasificaciones utilizadas en el Welcome Pack.
  Fecha Creacion    : 12/12/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_dupla_inscr
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la informacion de los productos
                      del programa en la tabla CYZ_PRODU_PROGR_DUPLA obteniendolos
                      de la matriz, a partir de la clasificacion asignada a un
                      programa en la tabla CYZ_CLASI_PROGR_DUPLA.  Las ofertas
                      que esten configuradas en la matriz del periodo pasado como
                      parametro y que tengan la configuracion de Venta Exclusiva
                      para dichas clasificaciones, seran insertados en la tabla
                      de productos.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoPrograma : Codigo de Programa
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_produ_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza las clasificaciones de los
                      clientes que son evaluados como parte del programa
                      Welcome Pack.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_paque_bienv
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que agrega mensajes en el buzon a los clientes
                      que han adiquido algun producto relacionado con los programs
                      del Welcome Pack.  Hace uso del procedimiento utilizado por
                      el CySet y el Gloss.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_progr_bienv
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que identifica la que cuentan con correo
                      electronico y que cumplen años dentro de una determinada
                      campaña.
  Fecha Creacion    : 16/05/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_dupla_cumpl_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras
                      para el despacho de la oferta de cumpleaños.
  Fecha Creacion    : 18/05/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_ofert_cumpl
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza los detalles del pedido al
                      momento de hacer la carga para validar que la consultora
                      solo solicite una de las ofertas.
  Fecha Creacion    : 08/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_solic_cumpl
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_PRODU_PROGR_DUPLA,
                      si existen unidades que exceden el valor de VAL_LIMI_CTRL_VENT
                      estas se actualizan a dicho valor. Esto aplica para todos
                      los productos asociados a los programas activos en el
                      periodo a procesar.
  Fecha Creacion    : 08/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_solic
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta en el buzon los mensajes configurados
                      para las consultoras que NO han adquirido un determinado producto,
                      asociado a un programa especifico, ya sea porque no cuentan con
                      dupla o porque no cumplen con las reglas definidas para
                      la estrategia.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_error_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que identifica a los clientes a los cuales se
                      les ha de insertar un mensaje en el buzon, debido a que NO
                      se les despacho el producto de la estrategia.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_clien_mensa_error
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que elimina mensajes de error antiguos que
                      no han sido impresos.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
  ***************************************************************************/
  PROCEDURE cyz_pr_elimi_mensa_error_nimpr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los mensajes de error en el buzon
                      de los clientes identificados por el proceso anterior.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_buzon_mensa_error
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos.
  Fecha Creacion    : 26/02/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_dupla_inscr
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 3
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos, la relacion de consultoras se
                      toma a partir de las duplas que cumplen años en el periodo.
  Fecha Creacion    : 26/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_cumpl_dupla
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos, la relacion de consultoras se
                      toma a partir de las consultoras que cumplen años en el
                      periodo.
  Fecha Creacion    : 26/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_cumpl_clien
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza las clasificaciones de los
                      clientes que son evaluados como parte del programa
                      Welcome Pack.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_paque_bien2
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle.
  Fecha Creacion    : 17/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_bien2
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      para las estrategias de cumpleaños (dupla y consultora)
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle.
  Fecha Creacion    : 25/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_cumpl
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el numero de unidades atendidas
                      en la entidad que almacena las cantidades y productos que
                      puede solicitar la consultora en un rango de periodos, para
                      ello el proceso busca sobre todos los pedidos que la consultora
                      ha pasado en ese rango de periodos y los sumariza.
  Fecha Creacion    : 17/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_bolsa_produ_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que identifica las consultoras que cumplen
                      años en una determinada campaña.
  Fecha Creacion    : 22/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clien_cumpl_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras
                      para el despacho de la oferta de cumpleaños para consultoras.
  Fecha Creacion    : 22/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_cumpl_clien
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta a la tabla de
                      consolidado de detalles, si estos han sido solicitados
                      desde la web. Ejecutado desde la validacion de STO
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_premi_solic_sto
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2,
    pscodtipodocu    IN VARCHAR2,
    psnumeroproceso  IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      para las estrategias de cumpleaños (dupla y consultora)
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle. Ejecutado desde la validacion de STO
  Fecha Creacion    : 25/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_cumpl_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_PRODU_PROGR_DUPLA,
                      si existen unidades que exceden el valor de VAL_LIMI_CTRL_VENT
                      estas se actualizan a dicho valor. Esto aplica para todos
                      los productos asociados a los programas activos en el
                      periodo a procesar. Ejecutado desde la validacion de STO
  Fecha Creacion    : 15/04/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais      : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario  : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_solic_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle. Ejecutado desde la validacion de STO.
  Fecha Creacion    : 15/04/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais      : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario  : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_bien2_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  );

END;
/

CREATE OR REPLACE PACKAGE BODY "CYZ_PKG_PROGR_DUPLA" AS

  cod_cana_defe    VARCHAR2(100) := 'VD';
  cod_marc_defe    VARCHAR2(100) := 'T';
  cod_tipo_soli_oc VARCHAR2(100) := 'SOC';

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  ind_error_sse_dupla_cyzone CONSTANT VARCHAR2(1) := 'D';

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    l_count        NUMBER := 0;
    l_mensajeerror VARCHAR2(4000);

    CURSOR cclasificacion IS
      SELECT tcc.oid_tipo_clas,
             mc.oid_clas,
             mtc.oid_tipo_clie,
             msc.oid_subt_clie
        FROM cyz_clasi_progr_dupla cpd,
             mae_tipo_clien        mtc,
             mae_subti_clien       msc,
             mae_tipo_clasi_clien  tcc,
             mae_clasi             mc
       WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
         AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
         AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
         AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
         AND msc.cod_subt_clie = cpd.cod_subt_clie
         AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
         AND mc.cod_clas = cpd.cod_clas_prdu
         AND cpd.pais_cod_pais = pscodigopais
         AND cpd.prdu_cod_prog = pscodigoprograma;

    TYPE t_oidtipoclasi IS TABLE OF mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    TYPE t_oidclasi IS TABLE OF mae_clasi.oid_clas%TYPE;
    TYPE t_oidtipoclie IS TABLE OF mae_tipo_clien.oid_tipo_clie%TYPE;
    TYPE t_oidsubtclie IS TABLE OF mae_subti_clien.oid_subt_clie%TYPE;

    v_oidtipoclasi t_oidtipoclasi;
    v_oidclasi     t_oidclasi;
    v_oidtipoclie  t_oidtipoclie;
    v_oidsubtclie  t_oidsubtclie;

    CURSOR cdeleteclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_vincu mcv
               WHERE mcv.tivc_oid_tipo_vinc = 1 -- Dupla
                 AND mcv.fec_hast IS NULL -- Duplas activas
                 AND mcv.clie_oid_clie_vnte = cts.clie_oid_clie);

    CURSOR cdeleteclasifdespa
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER,
      numperiodos  NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND EXISTS (SELECT NULL
                FROM cyz_despa_produ cdp
               WHERE cdp.clie_oid_clie = cts.clie_oid_clie
                 AND cdp.prdu_cod_prog = pscodigoprograma
                 AND cdp.pais_cod_pais = pscodigopais
                 AND cdp.num_unid_aten > 0
                 AND gen_fn_calcu_perio(cdp.cod_peri,
                                        numperiodos) > pscodigoperiodo);

    TYPE t_oidclieclasi IS TABLE OF mae_clien_clasi.oid_clie_clas%TYPE;
    v_oidclieclasi t_oidclieclasi;

    CURSOR cinsertclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND EXISTS (SELECT NULL
                FROM mae_clien_vincu mcv
               WHERE mcv.tivc_oid_tipo_vinc = 1 -- Tipo Vinculo Dupla
                 AND mcv.fec_hast IS NULL
                 AND cts.clie_oid_clie = mcv.clie_oid_clie_vnte -- Mama Dupla
              )
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_clasi mcc
               WHERE mcc.tccl_oid_tipo_clasi = oidtipoclasi
                 AND mcc.clas_oid_clas = oidclasi
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt);

    TYPE t_oidclietiposubt IS TABLE OF mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
    v_oidclietiposubt t_oidclietiposubt;

    lnoidperiodo  NUMBER;
    lnoidpais     NUMBER;
    lnoidcanal    NUMBER;
    lnoidmarca    NUMBER;
    lnnumperiodos NUMBER;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Validamos que el programa este activo y vigente
    SELECT COUNT(*)
      INTO l_count
      FROM cyz_progr_dupla
     WHERE cod_pais = pscodigopais
       AND cod_prog = pscodigoprograma
       AND est_prog = '1'
       AND pscodigoperiodo >= cod_peri_inic
       AND pscodigoperiodo <= cod_peri_fina;

    -- Si el programa esta vigente
    IF l_count != 0 THEN

      SELECT num_peri_eval
        INTO lnnumperiodos
        FROM cyz_progr_dupla
       WHERE cod_pais = pscodigopais
         AND cod_prog = pscodigoprograma;

      OPEN cclasificacion;
      LOOP
        FETCH cclasificacion BULK COLLECT
          INTO v_oidtipoclasi,
               v_oidclasi,
               v_oidtipoclie,
               v_oidsubtclie LIMIT w_filas;
        IF v_oidtipoclasi.count > 0 THEN
          FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
          LOOP

            -- Abrimos el curos para la eliminacion de clasificaciones
            OPEN cdeleteclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cdeleteclasif BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasif%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasif;

            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cinsertclasif BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL k IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(k),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasif%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasif;

            -- Eliminamos la clasificacion a aquellos que ya se les ha despachado el producto
            OPEN cdeleteclasifdespa(v_oidtipoclie(i),
                                    v_oidsubtclie(i),
                                    v_oidtipoclasi(i),
                                    v_oidclasi(i),
                                    lnnumperiodos);
            LOOP
              FETCH cdeleteclasifdespa BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasifdespa%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasifdespa;

          END LOOP;
        END IF;

        EXIT WHEN cclasificacion%NOTFOUND;
      END LOOP;
      CLOSE cclasificacion;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_CLASI_PROGR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta en el buzon los mensajes configurados
                      para las consultoras que han adquirido un determinado producto,
                      asociado a un programa especifico.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Codigo Usuario
              psIndicadorValidaClasificacion : Indicador que determina si el proceso
                                               ademas de validar el despacho valida que
                                               la consultora tenga la clasificacion
                                               asociada a la estrategia.
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_progr
  (
    pscodigopais                   IN VARCHAR2,
    pscodigoprograma               IN VARCHAR2,
    pscodigoperiodo                IN VARCHAR2,
    pscodigousuario                IN VARCHAR2,
    psindicadorvalidaclasificacion IN VARCHAR2
  ) IS
    l_programa NUMBER := 0;
    l_mensaje  NUMBER := 0;

  BEGIN

    SELECT COUNT(*)
      INTO l_programa
      FROM cyz_progr_dupla cpd
     WHERE cpd.cod_pais = pscodigopais
       AND cpd.cod_prog = pscodigoprograma
       AND cpd.est_prog = '1' -- Activo
       AND cpd.cod_peri_inic <= pscodigoperiodo
       AND cpd.cod_peri_fina >= pscodigoperiodo;

    SELECT COUNT(*)
      INTO l_mensaje
      FROM cyz_mensa_progr_dupla mpd
     WHERE mpd.pais_cod_pais = pscodigopais
       AND mpd.prdu_cod_prog = pscodigoprograma
       AND mpd.est_mens = '1' -- Activo
       AND mpd.ind_erro_nego = 0
       AND mpd.ind_erro_nodu = 0;

    -- Solo enviamos mensajes si el programa esta vigente
    -- y si existen mensaje de envio configurados
    IF l_programa > 0 THEN

      -- Actualizamos los productos configurados con venta exclusiva
      cyz_pr_actua_produ_progr(pscodigopais,
                               pscodigoprograma,
                               pscodigoperiodo,
                               pscodigousuario);

      IF l_mensaje > 0 THEN

        -- Primero cargamos la relacion de clientes a los cuales hay que enviarles el mensaje
        cyz_pr_carga_clien_mensa_produ(pscodigopais,
                                       pscodigoprograma,
                                       pscodigoperiodo,
                                       psindicadorvalidaclasificacion);

        -- Eliminamos los mensajes antiguos que no fueron impresos
        cyz_pr_elimi_mensa_nimpr(pscodigopais,
                                 pscodigoprograma);

        -- Enviamos los mensajes a los clientes identificados previamente
        cyz_pr_envia_buzon_mensa(pscodigopais,
                                 pscodigoprograma,
                                 pscodigoperiodo);
      END IF;

    END IF;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica a los clientes a los cuales se
                      les ha de insertar un mensaje en el buzon, en base a si se le
                      despacho el producto del programa asociado.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psIndicadorValidaClasificacion : Indicador que determina si el proceso
                                               ademas de validar el despacho valida que
                                               la consultora tenga la clasificacion
                                               asociada a la estrategia.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_clien_mensa_produ
  (
    pscodigopais                   IN VARCHAR2,
    pscodigoprograma               IN VARCHAR2,
    pscodigoperiodo                IN VARCHAR2,
    psindicadorvalidaclasificacion IN VARCHAR2
  ) IS

    lnoidperiodo      NUMBER;
    lnoidpais         NUMBER;
    lnoidtiposolipais NUMBER;
    lnoidcanal        NUMBER;
    lnoidmarca        NUMBER;

    CURSOR cclientes
    (
      oidpais         NUMBER,
      oidtiposolipais NUMBER,
      oidperiodo      NUMBER
    ) IS
      SELECT clie_oid_clie,
             oid_soli_cabe
        FROM ped_solic_cabec psc
       WHERE psc.pais_oid_pais = oidpais
         AND psc.tspa_oid_tipo_soli_pais = oidtiposolipais
         AND psc.perd_oid_peri = oidperiodo
         AND psc.grpr_oid_grup_proc = 4 -- GP4
         AND EXISTS (SELECT NULL
                FROM ped_solic_posic       psp,
                     mae_produ             mp,
                     cyz_produ_progr_dupla ppd
               WHERE psp.prod_oid_prod = mp.oid_prod
                 AND mp.cod_sap = ppd.cod_prod
                 AND ppd.cod_peri = pscodigoperiodo
                 AND ppd.pais_cod_pais = pscodigopais
                 AND ppd.prdu_cod_prog = pscodigoprograma
                 AND psp.soca_oid_soli_cabe = psc.oid_soli_cabe
                 AND psp.val_codi_vent = ppd.val_codi_vent
                 AND psp.num_unid_compr > 0
                 AND psp.espo_oid_esta_posi = 4 -- Estado Correcto
              );

    -- Cursor que ademas de obtener las clientes a las cuales se les ha
    -- despachado el producto asociado, valida que los clientes tengan
    -- la clasificacion correspondiente
    CURSOR cclientesclasificacion
    (
      oidpais         NUMBER,
      oidtiposolipais NUMBER,
      oidperiodo      NUMBER
    ) IS
      SELECT clie_oid_clie,
             oid_soli_cabe
        FROM ped_solic_cabec psc
       WHERE psc.pais_oid_pais = oidpais
         AND psc.tspa_oid_tipo_soli_pais = oidtiposolipais
         AND psc.perd_oid_peri = oidperiodo
         AND psc.grpr_oid_grup_proc = 4 -- GP4
         AND EXISTS (SELECT NULL
                FROM ped_solic_posic       psp,
                     mae_produ             mp,
                     cyz_produ_progr_dupla ppd
               WHERE psp.prod_oid_prod = mp.oid_prod
                 AND mp.cod_sap = ppd.cod_prod
                 AND ppd.cod_peri = pscodigoperiodo
                 AND ppd.pais_cod_pais = pscodigopais
                 AND ppd.prdu_cod_prog = pscodigoprograma
                 AND psp.soca_oid_soli_cabe = psc.oid_soli_cabe
                 AND psp.val_codi_vent = ppd.val_codi_vent
                 AND psp.num_unid_compr > 0
                 AND psp.espo_oid_esta_posi = 4 -- Estado Correcto
              )
         AND EXISTS (SELECT NULL
                FROM cyz_clasi_progr_dupla cpd,
                     mae_tipo_clien        mtc,
                     mae_subti_clien       msc,
                     mae_tipo_clasi_clien  tcc,
                     mae_clasi             mc,
                     mae_clien_clasi       mcc,
                     mae_clien_tipo_subti  cts
               WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
                 AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
                 AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                 AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
                 AND msc.cod_subt_clie = cpd.cod_subt_clie
                 AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
                 AND mc.cod_clas = cpd.cod_clas_prdu
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
                 AND mcc.clas_oid_clas = mc.oid_clas
                 AND mcc.tccl_oid_tipo_clasi = tcc.oid_tipo_clas
                 AND cts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                 AND cts.sbti_oid_subt_clie = msc.oid_subt_clie
                 AND cts.clie_oid_clie = psc.clie_oid_clie
                 AND cpd.pais_cod_pais = pscodigopais
                 AND cpd.prdu_cod_prog = pscodigoprograma);

    TYPE t_oidclie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
    TYPE t_oidsolicabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;

    v_oidclie     t_oidclie;
    v_oidsolicabe t_oidsolicabe;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    SELECT b.oid_tipo_soli_pais
      INTO lnoidtiposolipais
      FROM ped_tipo_solic      a,
           ped_tipo_solic_pais b
     WHERE a.oid_tipo_soli = b.tsol_oid_tipo_soli
       AND a.cod_tipo_soli = cod_tipo_soli_oc;

    -- Limpiamos la tabla que contendra la lista de clientes
    EXECUTE IMMEDIATE 'TRUNCATE TABLE CYZ_TMP_CLIEN_MENSA';

    IF psindicadorvalidaclasificacion = 'S' THEN
      OPEN cclientesclasificacion(lnoidpais,
                                  lnoidtiposolipais,
                                  lnoidperiodo);
      LOOP
        FETCH cclientesclasificacion BULK COLLECT
          INTO v_oidclie,
               v_oidsolicabe LIMIT w_filas;
        IF v_oidclie.count > 0 THEN

          FORALL i IN v_oidclie.first .. v_oidclie.last
            INSERT INTO cyz_tmp_clien_mensa
              (cod_pais,
               cod_prog,
               cod_peri,
               oid_clie,
               oid_soli_cabe,
               fec_proc)
            VALUES
              (pscodigopais,
               pscodigoprograma,
               pscodigoperiodo,
               v_oidclie(i),
               v_oidsolicabe(i),
               SYSDATE);
        END IF;

        EXIT WHEN cclientesclasificacion%NOTFOUND;
      END LOOP;
      CLOSE cclientesclasificacion;
    ELSE
      OPEN cclientes(lnoidpais,
                     lnoidtiposolipais,
                     lnoidperiodo);
      LOOP
        FETCH cclientes BULK COLLECT
          INTO v_oidclie,
               v_oidsolicabe LIMIT w_filas;
        IF v_oidclie.count > 0 THEN

          FORALL i IN v_oidclie.first .. v_oidclie.last
            INSERT INTO cyz_tmp_clien_mensa
              (cod_pais,
               cod_prog,
               cod_peri,
               oid_clie,
               oid_soli_cabe,
               fec_proc)
            VALUES
              (pscodigopais,
               pscodigoprograma,
               pscodigoperiodo,
               v_oidclie(i),
               v_oidsolicabe(i),
               SYSDATE);
        END IF;

        EXIT WHEN cclientes%NOTFOUND;
      END LOOP;
      CLOSE cclientes;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_CARGA_CLIEN_MENSA_CYSET: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los mensajes en el buzon de los
                      clientes identificados por el proceso anterior.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_buzon_mensa
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    CURSOR cmensajes IS
      SELECT men.oid_mens
        FROM cyz_mensa_progr_dupla mpd,
             msg_mensa             men
       WHERE mpd.cod_mens = men.cod_mens
         AND mpd.pais_cod_pais = pscodigopais
         AND mpd.prdu_cod_prog = pscodigoprograma
         AND mpd.ind_erro_nego = 0 -- No es mensaje de error de negocio
         AND mpd.ind_erro_nodu = 0 -- No es mensaje de error por no contar con dupla
         AND mpd.est_mens = '1';

    TYPE t_oidmens IS TABLE OF msg_mensa.oid_mens%TYPE;

    v_oidmens t_oidmens;

  BEGIN

    OPEN cmensajes;
    LOOP
      FETCH cmensajes BULK COLLECT
        INTO v_oidmens LIMIT w_filas;

      IF v_oidmens.count > 0 THEN
        FOR i IN v_oidmens.first .. v_oidmens.last
        LOOP

          INSERT INTO msg_buzon_mensa
            (oid_buzo_mens,
             num_secu,
             clie_oid_clie,
             mens_oid_mens,
             modu_oid_modu_orig,
             fec_grab,
             ind_list_cons,
             ind_acti)
            SELECT msg_bume_seq.nextval,
                   msg_bum2_seq.nextval,
                   oid_clie,
                   v_oidmens(i),
                   13, -- Incentivos
                   SYSDATE,
                   1,
                   1
              FROM cyz_tmp_clien_mensa
             WHERE cod_pais = pscodigopais
               AND cod_prog = pscodigoprograma
               AND cod_peri = pscodigoperiodo;

        END LOOP;
      END IF;

      EXIT WHEN cmensajes%NOTFOUND;
    END LOOP;
    CLOSE cmensajes;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ENVIA_BUZON_MENSA: ' || ls_sqlerrm);
  END;

  /***************************************************************************
  Descripcion       : Procedimiento que elimina mensajes antiguos que no han
                      sido impresos.
  Fecha Creacion    : 02/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
  ***************************************************************************/
  PROCEDURE cyz_pr_elimi_mensa_nimpr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2
  ) IS

    CURSOR cmensaje(oidpais NUMBER) IS
      SELECT a.oid_buzo_mens
        FROM msg_buzon_mensa       a,
             msg_mensa             b,
             cyz_mensa_progr_dupla c
       WHERE b.pais_oid_pais = oidpais
         AND b.oid_mens = a.mens_oid_mens
         AND c.cod_mens = b.cod_mens
         AND c.prdu_cod_prog = pscodigoprograma
         AND c.pais_cod_pais = pscodigopais
         AND c.ind_erro_nego = 0
         AND c.ind_erro_nodu = 0
         AND a.num_lote_impr IS NULL;

    TYPE t_oidmensaje IS TABLE OF msg_buzon_mensa.oid_buzo_mens%TYPE;
    v_oidmensaje t_oidmensaje;
    lnoidpais    NUMBER;

  BEGIN
    -- Obtenemos el oid del pais
    lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    /* eliminando los mensajes sin imprimir */
    OPEN cmensaje(lnoidpais);
    LOOP
      FETCH cmensaje BULK COLLECT
        INTO v_oidmensaje LIMIT w_filas;
      IF v_oidmensaje.count > 0 THEN
        FORALL i IN v_oidmensaje.first .. v_oidmensaje.last
          DELETE FROM msg_buzon_mensa a WHERE a.oid_buzo_mens = v_oidmensaje(i);
      END IF;
      EXIT WHEN cmensaje%NOTFOUND;
    END LOOP;
    CLOSE cmensaje;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'CYZ_PR_ELIMI_MENSA_NIMPR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta a la tabla de
                      consolidado de detalles, si estos han sido solicitados
                      desde la web.
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_premi_solic
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  ) IS

    CURSOR cinsertdetalle IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             cab.num_lote,
             cyz.val_codi_vent,
             'OC' tip_posic,
             cyz.num_unid_soli,
             'D', -- las agregadas por el programa de duplas (premios)
             '0' ind_erro_sse,
             '0' ind_erro_rech,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             cab.fec_soli
        FROM int_solic_conso_cabec cab,
             cyz_solic_produ       cyz,
             cyz_produ_progr_dupla ppd
       WHERE cab.cod_pais = cyz.pais_cod_pais
         AND cab.cod_clie = cyz.cod_clie
         AND cab.cod_peri = cyz.cod_peri
         AND cyz.pais_cod_pais = ppd.pais_cod_pais
         AND cyz.prdu_cod_prog = ppd.prdu_cod_prog
         AND cyz.cod_peri = ppd.cod_peri
         AND cyz.val_codi_vent = ppd.val_codi_vent
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cyz.prdu_cod_prog = pscodigoprograma
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.cod_vent = cyz.val_codi_vent)
         AND cyz_pkg_progr_dupla.cyz_fn_devue_numer_unida(cab.cod_pais,
                                                          cab.cod_peri,
                                                          cab.cod_clie,
                                                          cab.num_lote) > 0;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_detal.sta_proc%TYPE;
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
    v_sta_proc      t_sta_proc;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000;
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN cinsertdetalle;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cinsertdetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_sta_proc,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = cinsertdetalle%ROWCOUNT;
      v_row_count := cinsertdetalle%ROWCOUNT;

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
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           v_sta_proc(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j));

    END LOOP;
    CLOSE cinsertdetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'CYZ_PR_CARGA_PREMI_SOLIC: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el numero de unidades totales de un
                      producto a partir de la tabla INT_SOLIC_CONSO_DETAL los cuales
                      no estan rechazados por OCR ni por SSE.  Se utiliza para evitar
                      que el proceso anterior genere pedidos con un solo detalle de
                      premio.
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Programa
              psCodigoCliente : Codigo de Periodo
              psNumeroLote : Codigo de Usuario
  ***************************************************************************/
  FUNCTION cyz_fn_devue_numer_unida
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    psnumerolote    IN VARCHAR2
  ) RETURN NUMBER IS
    lncount NUMBER := 0;

  BEGIN

    SELECT SUM(nvl(det.val_unid_dem,
                   0))
      INTO lncount
      FROM int_solic_conso_detal det
     WHERE det.cod_pais = pscodigopais
       AND det.cod_peri = pscodigoperiodo
       AND det.cod_clie = pscodigocliente
       AND det.num_lote = psnumerolote
       AND det.ind_erro_rech = '0'
       AND det.ind_erro_sse = '0';

    RETURN lncount;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica la primeras duplas inscritas
                      y que cuentan con correo electronico de una cliente, de tal
                      forma que sirva de input para la identificacion de las
                      clasificaciones utilizadas en el Welcome Pack.
  Fecha Creacion    : 12/12/2009
  Fecha Modificacion: 22/02/2010 (CHR)
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  Cambios Importantes:
              22/02/2010 CHR - Se quita la condicion de email.
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_dupla_inscr
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cduplas
    (
      fechainicio DATE,
      fechafin    DATE
    ) IS
      SELECT mc.oid_clie,
             mc.cod_clie,
             md.oid_clie,
             md.cod_clie cod_dupl,
             cv.fec_desd
        FROM mae_clien       mc,
             mae_clien_vincu cv,
             mae_clien       md --,
      --MAE_CLIEN_COMUN CC -- Se quita la condicion del email
       WHERE mc.oid_clie = cv.clie_oid_clie_vnte
         AND md.oid_clie = cv.clie_oid_clie_vndo
         AND cv.tivc_oid_tipo_vinc = 1 -- Dupla
         AND cv.fec_hast IS NULL -- Dupla Activa
            --  AND MD.OID_CLIE = CC.CLIE_OID_CLIE
            --  AND CC.VAL_TEXT_COMU IS NOT NULL -- Corre Electronico no vacio
            --  AND CC.TICM_OID_TIPO_COMU = 3 -- Correo electronico
         AND cv.fec_desd >= fechainicio
         AND cv.fec_desd < fechafin
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_vincu x
               WHERE x.fec_desd < fechainicio
                 AND x.tivc_oid_tipo_vinc = 1 -- Dupla
                 AND x.clie_oid_clie_vnte = mc.oid_clie)
         AND NOT EXISTS (SELECT NULL
                FROM cyz_prime_dupla_inscr y
               WHERE y.clie_oid_clie = cv.clie_oid_clie_vnte
                 AND y.clie_oid_dupl = cv.clie_oid_clie_vndo);

    TYPE t_oidclie IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_oiddupla IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_coddupla IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_fecinsc IS TABLE OF mae_clien_vincu.fec_desd%TYPE;

    v_oidclie  t_oidclie;
    v_codclie  t_codclie;
    v_oiddupla t_oiddupla;
    v_coddupla t_coddupla;
    v_fecinsc  t_fecinsc;

    lsperiodominimo       VARCHAR2(6);
    lsperiodosiguiente    VARCHAR2(6);
    lnoidperiodo          NUMBER;
    lnoidperiodosiguiente NUMBER;
    lnoidpais             NUMBER;
    lnoidcanal            NUMBER;
    lnoidmarca            NUMBER;
    ldfechainicio         DATE;
    ldfechafin            DATE;

  BEGIN

    -- Validamos si el periodo ingresado es superior o igual al minimo periodo
    -- de inicio de los programas relacionados con el welcome pack
    SELECT MIN(cpd.cod_peri_inic)
      INTO lsperiodominimo
      FROM cyz_progr_dupla cpd
     WHERE cpd.ind_paqu_bien = 1;

    -- Solo si el periodo pasado como parametro es mayor a igual al periodo minimo
    IF lsperiodominimo IS NOT NULL AND lsperiodominimo <= pscodigoperiodo THEN

      -- Obtenemos el valor del periodo siguiente
      lsperiodosiguiente := gen_fn_calcu_perio(pscodigoperiodo,
                                               1);

      -- Obtenemos los OIDs de los periodos
      lnoidmarca            := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
      lnoidcanal            := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
      lnoidperiodo          := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                          lnoidmarca,
                                                                          lnoidcanal);
      lnoidperiodosiguiente := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lsperiodosiguiente,
                                                                          lnoidmarca,
                                                                          lnoidcanal);

      -- Obtenemos la fechas de inicio y de fin
      SELECT fec_inic INTO ldfechainicio FROM cra_perio WHERE oid_peri = lnoidperiodo;

      SELECT fec_inic INTO ldfechafin FROM cra_perio WHERE oid_peri = lnoidperiodosiguiente;

      -- Abrimos el cursor de insercion de clasificaciones por cliente
      OPEN cduplas(ldfechainicio,
                   ldfechafin);
      LOOP
        FETCH cduplas BULK COLLECT
          INTO v_oidclie,
               v_codclie,
               v_oiddupla,
               v_coddupla,
               v_fecinsc LIMIT w_filas;
        IF v_oidclie.count > 0 THEN
          FORALL i IN v_oidclie.first .. v_oidclie.last
            INSERT INTO cyz_prime_dupla_inscr
              (pais_cod_pais,
               cod_peri,
               clie_oid_clie,
               cod_clie,
               clie_oid_dupl,
               cod_dupl,
               fec_insc,
               usu_proc,
               fec_proc)
            VALUES
              (pscodigopais,
               pscodigoperiodo,
               v_oidclie(i),
               v_codclie(i),
               v_oiddupla(i),
               v_coddupla(i),
               v_fecinsc(i),
               pscodigousuario,
               SYSDATE);
        END IF;
        EXIT WHEN cduplas%NOTFOUND;
      END LOOP;
      CLOSE cduplas;

    END IF;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la informacion de los productos
                      del programa en la tabla CYZ_PRODU_PROGR_DUPLA obteniendolos
                      de la matriz, a partir de la clasificacion asignada a un
                      programa en la tabla CYZ_CLASI_PROGR_DUPLA.  Las ofertas
                      que esten configuradas en la matriz del periodo pasado como
                      parametro y que tengan la configuracion de Venta Exclusiva
                      para dichas clasificaciones, seran insertados en la tabla
                      de productos.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoPrograma : Codigo de Programa
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_produ_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  ) IS

    CURSOR cproductos(oidperiodo NUMBER) IS
      SELECT mp.cod_sap        cod_prod,
             i18n.val_i18n     des_prod,
             pod.val_codi_vent,
             pto.cod_tipo_ofer
        FROM pre_ofert_detal       pod,
             pre_matri_factu_cabec mfc,
             pre_ofert             po,
             pre_tipo_ofert        pto,
             mae_produ             mp,
             gen_i18n_sicc_pais    i18n
       WHERE mfc.oid_cabe = po.mfca_oid_cabe
         AND po.oid_ofer = pod.ofer_oid_ofer
         AND pod.prod_oid_prod = mp.oid_prod
         AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer
         AND mp.oid_prod = i18n.val_oid
         AND i18n.attr_enti = 'MAE_PRODU'
         AND mfc.perd_oid_peri = oidperiodo
         AND EXISTS (SELECT pve.oid_vent_excl,
                     pve.ofer_oid_ofer,
                     tcc.oid_tipo_clas,
                     mc.oid_clas,
                     mtc.oid_tipo_clie,
                     msc.oid_subt_clie
                FROM cyz_clasi_progr_dupla cpd,
                     mae_tipo_clien        mtc,
                     mae_subti_clien       msc,
                     mae_tipo_clasi_clien  tcc,
                     mae_clasi             mc,
                     pre_venta_exclu       pve
               WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
                 AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
                 AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                 AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
                 AND msc.cod_subt_clie = cpd.cod_subt_clie
                 AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
                 AND mc.cod_clas = cpd.cod_clas_prdu
                 AND pve.clas_oid_clas = mc.oid_clas
                 AND pve.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                 AND pve.sbti_oid_subt_clie = msc.oid_subt_clie
                 AND pve.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                 AND cpd.pais_cod_pais = pscodigopais
                 AND cpd.prdu_cod_prog = pscodigoprograma
                 AND pve.ofer_oid_ofer = po.oid_ofer)
         AND NOT EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd
               WHERE ppd.pais_cod_pais = pscodigopais
                 AND ppd.prdu_cod_prog = pscodigoprograma
                 AND ppd.cod_peri = pscodigoperiodo
                 AND ppd.cod_prod = mp.cod_sap
                 AND ppd.val_codi_vent = pod.val_codi_vent);

    TYPE t_codprod IS TABLE OF mae_produ.cod_sap%TYPE;
    TYPE t_desprod IS TABLE OF gen_i18n_sicc_pais.val_i18n%TYPE;
    TYPE t_valcodivent IS TABLE OF pre_ofert_detal.val_codi_vent%TYPE;
    TYPE t_codtipoofer IS TABLE OF pre_tipo_ofert.cod_tipo_ofer%TYPE;

    v_codprod     t_codprod;
    v_desprod     t_desprod;
    v_valcodivent t_valcodivent;
    v_codtipoofer t_codtipoofer;

    lnoidperiodo NUMBER;
    lnoidpais    NUMBER;
    lnoidcanal   NUMBER;
    lnoidmarca   NUMBER;

  BEGIN

    -- Obtenemos los OIDs del periodo
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cproductos(lnoidperiodo);
    LOOP
      FETCH cproductos BULK COLLECT
        INTO v_codprod,
             v_desprod,
             v_valcodivent,
             v_codtipoofer LIMIT w_filas;
      IF v_codprod.count > 0 THEN
        FORALL i IN v_codprod.first .. v_codprod.last
          INSERT INTO cyz_produ_progr_dupla
            (pais_cod_pais,
             prdu_cod_prog,
             cod_peri,
             cod_prod,
             des_prod,
             val_codi_vent,
             cod_tipo_ofer,
             est_prod_prdu,
             usu_digi,
             fec_digi)
          VALUES
            (pscodigopais,
             pscodigoprograma,
             pscodigoperiodo,
             v_codprod(i),
             v_desprod(i),
             v_valcodivent(i),
             v_codtipoofer(i),
             '1',
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cproductos%NOTFOUND;
    END LOOP;
    CLOSE cproductos;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza las clasificaciones de los
                      clientes que son evaluados como parte del programa
                      Welcome Pack.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_paque_bienv
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2
  ) IS

    l_count        NUMBER := 0;
    l_mensajeerror VARCHAR2(4000);

    CURSOR cclasificacion IS
      SELECT pd.cod_prog,
             pd.num_peri_eval,
             pd.cod_prog_prev,
             tcc.oid_tipo_clas,
             mc.oid_clas,
             mtc.oid_tipo_clie,
             msc.oid_subt_clie
        FROM cyz_clasi_progr_dupla cpd,
             cyz_progr_dupla       pd,
             mae_tipo_clien        mtc,
             mae_subti_clien       msc,
             mae_tipo_clasi_clien  tcc,
             mae_clasi             mc
       WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
         AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
         AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
         AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
         AND msc.cod_subt_clie = cpd.cod_subt_clie
         AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
         AND mc.cod_clas = cpd.cod_clas_prdu
         AND cpd.pais_cod_pais = pd.cod_pais
         AND cpd.prdu_cod_prog = pd.cod_prog
         AND pd.cod_pais = pscodigopais
         AND pd.ind_paqu_bien = 1
         AND pd.cod_peri_inic <= pscodigoperiodo
         AND pd.cod_peri_fina >= pscodigoperiodo
       ORDER BY pd.cod_prog;

    TYPE t_codprog IS TABLE OF cyz_progr_dupla.cod_prog%TYPE;
    TYPE t_numperieval IS TABLE OF cyz_progr_dupla.num_peri_eval%TYPE;
    TYPE t_codprogprev IS TABLE OF cyz_progr_dupla.cod_prog_prev%TYPE;
    TYPE t_oidtipoclasi IS TABLE OF mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    TYPE t_oidclasi IS TABLE OF mae_clasi.oid_clas%TYPE;
    TYPE t_oidtipoclie IS TABLE OF mae_tipo_clien.oid_tipo_clie%TYPE;
    TYPE t_oidsubtclie IS TABLE OF mae_subti_clien.oid_subt_clie%TYPE;

    v_codprog      t_codprog;
    v_numperieval  t_numperieval;
    v_codprogprev  t_codprogprev;
    v_oidtipoclasi t_oidtipoclasi;
    v_oidclasi     t_oidclasi;
    v_oidtipoclie  t_oidtipoclie;
    v_oidsubtclie  t_oidsubtclie;

    CURSOR cdeleteclasif
    (
      codprog      VARCHAR2,
      numperieval  NUMBER,
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
      /*AND NOT EXISTS (
          SELECT NULL
          FROM CYZ_PRIME_DUPLA_INSCR PDI
          WHERE PDI.COD_PERI = GEN_FN_CALCU_PERIO(psCodigoPeriodo, - numPeriEval)
          AND CTS.CLIE_OID_CLIE = PDI.CLIE_OID_CLIE
      )
      */
      ; -- Comentado para simplificar la evaluacion eliminando la totalidad

    TYPE t_oidclieclasi IS TABLE OF mae_clien_clasi.oid_clie_clas%TYPE;
    v_oidclieclasi t_oidclieclasi;

    CURSOR cinsertclasifinic
    (
      codprog      VARCHAR2,
      numperieval  NUMBER,
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND ( -- Periodo de inscripcion es igual al periodo de proceso menos
             -- 1 y no hubo solicitud del producto del 1er dscto
              (EXISTS (SELECT NULL
                         FROM cyz_prime_dupla_inscr x
                        WHERE x.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                              -numperieval)
                          AND x.clie_oid_clie = cts.clie_oid_clie) AND NOT EXISTS -- Que no exista solicitud del 1er dscto en el periodo de inscripcion
               (SELECT NULL
                  FROM cyz_despa_produ       y,
                       cyz_prime_dupla_inscr z
                 WHERE y.pais_cod_pais = z.pais_cod_pais
                   AND y.cod_peri = z.cod_peri
                   AND y.clie_oid_clie = z.clie_oid_clie
                   AND y.clie_oid_clie = cts.clie_oid_clie
                   AND y.prdu_cod_prog = codprog)) OR -- Periodo de inscripcion es igual a periodo proceso
              (EXISTS (SELECT NULL
                         FROM cyz_prime_dupla_inscr p
                        WHERE p.cod_peri = pscodigoperiodo
                          AND p.clie_oid_clie = cts.clie_oid_clie)))
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_clasi mcc
               WHERE mcc.tccl_oid_tipo_clasi = oidtipoclasi
                 AND mcc.clas_oid_clas = oidclasi
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt);

    CURSOR cinsertclasif
    (
      codprog      VARCHAR2,
      numperieval  NUMBER,
      codprogprev  VARCHAR2,
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND ( -- Periodo de inscripcion es igual al periodo de proceso menos
             -- el numero de periodos configurados y no hubo solicitud del
             -- producto del 1er dscto en el periodo de inscripcion
              (EXISTS (SELECT NULL
                         FROM cyz_prime_dupla_inscr x
                        WHERE x.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                              -numperieval)
                          AND x.clie_oid_clie = cts.clie_oid_clie) AND NOT EXISTS -- Que no exista solicitud del 1er dscto en el periodo de inscripcion
               (SELECT NULL
                  FROM cyz_despa_produ       y,
                       cyz_prime_dupla_inscr z
                 WHERE y.pais_cod_pais = z.pais_cod_pais
                   AND y.cod_peri = z.cod_peri
                   AND y.clie_oid_clie = z.clie_oid_clie
                   AND y.clie_oid_clie = cts.clie_oid_clie
                   AND y.prdu_cod_prog = codprogprev)) OR -- Periodo de inscripcion es igual a periodo proceso menos el numero
             -- de periodos configurados mas 1
              (EXISTS (SELECT NULL
                         FROM cyz_prime_dupla_inscr p
                        WHERE p.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                              -numperieval + 1)
                          AND p.clie_oid_clie = cts.clie_oid_clie) AND EXISTS -- Que exista solicitud del 1er dscto en el periodo de inscripcion
               (SELECT NULL
                  FROM cyz_despa_produ       q,
                       cyz_prime_dupla_inscr r
                 WHERE q.pais_cod_pais = r.pais_cod_pais
                   AND q.cod_peri = r.cod_peri
                   AND q.clie_oid_clie = r.clie_oid_clie
                   AND q.clie_oid_clie = cts.clie_oid_clie
                   AND q.prdu_cod_prog = codprogprev)))
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_clasi mcc
               WHERE mcc.tccl_oid_tipo_clasi = oidtipoclasi
                 AND mcc.clas_oid_clas = oidclasi
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt);

    TYPE t_oidclietiposubt IS TABLE OF mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
    v_oidclietiposubt t_oidclietiposubt;

    lnoidperiodo  NUMBER;
    lnoidpais     NUMBER;
    lnoidcanal    NUMBER;
    lnoidmarca    NUMBER;
    lnnumperiodos NUMBER;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Eliminamos todas las clasificaciones
    OPEN cclasificacion;
    LOOP
      FETCH cclasificacion BULK COLLECT
        INTO v_codprog,
             v_numperieval,
             v_codprogprev,
             v_oidtipoclasi,
             v_oidclasi,
             v_oidtipoclie,
             v_oidsubtclie LIMIT w_filas;
      IF v_oidtipoclasi.count > 0 THEN
        FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
        LOOP

          -- Abrimos el cursor para la eliminacion de clasificaciones
          OPEN cdeleteclasif(v_codprog(i),
                             v_numperieval(i),
                             v_oidtipoclie(i),
                             v_oidsubtclie(i),
                             v_oidtipoclasi(i),
                             v_oidclasi(i));
          LOOP
            FETCH cdeleteclasif BULK COLLECT
              INTO v_oidclieclasi LIMIT w_filas;
            IF v_oidclieclasi.count > 0 THEN
              FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
            END IF;
            EXIT WHEN cdeleteclasif%NOTFOUND;
          END LOOP;
          CLOSE cdeleteclasif;

        END LOOP;
      END IF;

      EXIT WHEN cclasificacion%NOTFOUND;
    END LOOP;
    CLOSE cclasificacion;

    -- Insertamos las clasificaciones
    OPEN cclasificacion;
    LOOP
      FETCH cclasificacion BULK COLLECT
        INTO v_codprog,
             v_numperieval,
             v_codprogprev,
             v_oidtipoclasi,
             v_oidclasi,
             v_oidtipoclie,
             v_oidsubtclie LIMIT w_filas;
      IF v_oidtipoclasi.count > 0 THEN
        FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
        LOOP

          IF v_codprogprev(i) IS NULL THEN
            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasifinic(v_codprog(i),
                                   v_numperieval(i),
                                   v_oidtipoclie(i),
                                   v_oidsubtclie(i),
                                   v_oidtipoclasi(i),
                                   v_oidclasi(i));
            LOOP
              FETCH cinsertclasifinic BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL k IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(k),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasifinic%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasifinic;
          ELSE
            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasif(v_codprog(i),
                               v_numperieval(i),
                               v_codprogprev(i),
                               v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cinsertclasif BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL l IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(l),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasif%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasif;
          END IF;

        END LOOP;
      END IF;

      EXIT WHEN cclasificacion%NOTFOUND;
    END LOOP;
    CLOSE cclasificacion;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_CLASI_PAQUE_BIENV: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que agrega mensajes en el buzon a los clientes
                      que han adiquido algun producto relacionado con los programs
                      del Welcome Pack.  Hace uso del procedimiento utilizado por
                      el CySet y el Gloss.
  Fecha Creacion    : 13/02/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_progr_bienv
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cmensajeproducto IS
      SELECT pd.cod_pais,
             pd.cod_prog
        FROM cyz_progr_dupla pd
       WHERE pd.cod_peri_inic <= pscodigoperiodo
         AND pd.cod_peri_fina >= pscodigoperiodo
         AND pd.est_prog = '1' -- Estado Activo
         AND pd.ind_paqu_bien = 1 -- Welcome Pack
         AND EXISTS (SELECT NULL
                FROM cyz_mensa_progr_dupla mpd
               WHERE pd.cod_pais = mpd.pais_cod_pais
                 AND pd.cod_prog = mpd.prdu_cod_prog
                 AND mpd.est_mens = '1' -- Estado Activo
                 AND mpd.ind_erro_nego = 0
                 AND mpd.ind_erro_nodu = 0)
         AND EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd
               WHERE pd.cod_pais = ppd.pais_cod_pais
                 AND pd.cod_prog = ppd.prdu_cod_prog
                 AND ppd.cod_peri = pscodigoperiodo
                 AND ppd.est_prod_prdu = '1' -- Estado Activo
              );

    TYPE t_codpais IS TABLE OF cyz_progr_dupla.cod_pais%TYPE;
    TYPE t_codprog IS TABLE OF cyz_progr_dupla.cod_prog%TYPE;

    v_codpais t_codpais;
    v_codprog t_codprog;

  BEGIN

    -- Hacemos el envio de mensajes para los programas vigentes del welcome pack
    OPEN cmensajeproducto;
    LOOP
      FETCH cmensajeproducto BULK COLLECT
        INTO v_codpais,
             v_codprog LIMIT w_filas;
      IF v_codprog.count > 0 THEN
        FOR k IN v_codprog.first .. v_codprog.last
        LOOP

          cyz_pr_envia_mensa_progr(v_codpais(k),
                                   v_codprog(k),
                                   pscodigoperiodo,
                                   pscodigousuario,
                                   'N');

        END LOOP;
      END IF;

      EXIT WHEN cmensajeproducto%NOTFOUND;
    END LOOP;
    CLOSE cmensajeproducto;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ENVIA_MENSA_PROGR_BIENV: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica las duplas que cuentan con
                      correo electronico y que cumplen años dentro de una
                      determinada campaña.
  Fecha Creacion    : 16/05/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_dupla_cumpl_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cduplas
    (
      fechainicio DATE,
      fechafin    DATE
    ) IS
      SELECT mc.oid_clie,
             mc.cod_clie,
             md.oid_clie,
             md.cod_clie cod_dupl,
             dad.fec_naci
        FROM mae_clien       mc,
             mae_clien_vincu cv,
             mae_clien       md,
             --     MAE_CLIEN_COMUN CC,
             mae_clien_datos_adici dad
       WHERE mc.oid_clie = cv.clie_oid_clie_vnte
         AND md.oid_clie = cv.clie_oid_clie_vndo
         AND cv.tivc_oid_tipo_vinc = 1 -- Dupla
         AND cv.fec_hast IS NULL -- Dupla Activa
            --  AND MD.OID_CLIE = CC.CLIE_OID_CLIE -- (CHR - se quita la condicion de email desde C201005)
            --  AND CC.VAL_TEXT_COMU IS NOT NULL -- Corre Electronico no vacio
            --  AND CC.TICM_OID_TIPO_COMU = 3 -- Correo electronico
         AND md.oid_clie = dad.clie_oid_clie
         AND dad.fec_naci IS NOT NULL -- Fecha de nacimiento
         AND ((to_char(fechafin,
                       'MM') >= to_char(fechainicio,
                                          'MM') AND
             to_char(dad.fec_naci,
                       'MMDD') >= to_char(fechainicio,
                                            'MMDD') AND
             to_char(dad.fec_naci,
                       'MMDD') < to_char(fechafin,
                                           'MMDD')) OR
             (to_char(fechafin,
                       'MM') < to_char(fechainicio,
                                         'MM') AND
             (to_char(dad.fec_naci,
                        'MMDD') >= to_char(fechainicio,
                                              'MMDD') OR
             to_char(dad.fec_naci,
                        'MMDD') < to_char(fechafin,
                                             'MMDD'))))
         AND NOT EXISTS (SELECT NULL
                FROM cyz_dupla_cumpl_perio y
               WHERE y.clie_oid_clie = cv.clie_oid_clie_vnte
                 AND y.clie_oid_dupl = cv.clie_oid_clie_vndo
                 AND y.cod_peri = pscodigoperiodo);

    TYPE t_oidclie IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_oiddupla IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_coddupla IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_fecnaci IS TABLE OF mae_clien_datos_adici.fec_naci%TYPE;

    v_oidclie  t_oidclie;
    v_codclie  t_codclie;
    v_oiddupla t_oiddupla;
    v_coddupla t_coddupla;
    v_fecnaci  t_fecnaci;

    lsperiodominimo       VARCHAR2(6);
    lsperiodosiguiente    VARCHAR2(6);
    lnoidperiodo          NUMBER;
    lnoidperiodosiguiente NUMBER;
    lnoidpais             NUMBER;
    lnoidcanal            NUMBER;
    lnoidmarca            NUMBER;
    ldfechainicio         DATE;
    ldfechafin            DATE;

  BEGIN

    -- Obtenemos el valor del periodo siguiente
    lsperiodosiguiente := gen_fn_calcu_perio(pscodigoperiodo,
                                             1);

    -- Obtenemos los OIDs de los periodos
    lnoidmarca            := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal            := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo          := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                        lnoidmarca,
                                                                        lnoidcanal);
    lnoidperiodosiguiente := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lsperiodosiguiente,
                                                                        lnoidmarca,
                                                                        lnoidcanal);

    -- Obtenemos la fechas de inicio y de fin
    SELECT fec_inic INTO ldfechainicio FROM cra_perio WHERE oid_peri = lnoidperiodo;

    SELECT fec_inic INTO ldfechafin FROM cra_perio WHERE oid_peri = lnoidperiodosiguiente;

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cduplas(ldfechainicio,
                 ldfechafin);
    LOOP
      FETCH cduplas BULK COLLECT
        INTO v_oidclie,
             v_codclie,
             v_oiddupla,
             v_coddupla,
             v_fecnaci LIMIT w_filas;
      IF v_oidclie.count > 0 THEN
        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_dupla_cumpl_perio
            (pais_cod_pais,
             cod_peri,
             clie_oid_clie,
             cod_clie,
             clie_oid_dupl,
             cod_dupl,
             fec_naci,
             usu_proc,
             fec_proc)
          VALUES
            (pscodigopais,
             pscodigoperiodo,
             v_oidclie(i),
             v_codclie(i),
             v_oiddupla(i),
             v_coddupla(i),
             v_fecnaci(i),
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cduplas%NOTFOUND;
    END LOOP;
    CLOSE cduplas;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras
                      para el despacho de la oferta de cumpleaños.
  Fecha Creacion    : 18/05/2009
  Fecha Modificacion: 22/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_ofert_cumpl
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    l_count        NUMBER := 0;
    l_mensajeerror VARCHAR2(4000);

    CURSOR cclasificacion IS
      SELECT tcc.oid_tipo_clas,
             mc.oid_clas,
             mtc.oid_tipo_clie,
             msc.oid_subt_clie
        FROM cyz_clasi_progr_dupla cpd,
             mae_tipo_clien        mtc,
             mae_subti_clien       msc,
             mae_tipo_clasi_clien  tcc,
             mae_clasi             mc
       WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
         AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
         AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
         AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
         AND msc.cod_subt_clie = cpd.cod_subt_clie
         AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
         AND mc.cod_clas = cpd.cod_clas_prdu
         AND cpd.pais_cod_pais = pscodigopais
         AND cpd.prdu_cod_prog = pscodigoprograma;

    TYPE t_oidtipoclasi IS TABLE OF mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    TYPE t_oidclasi IS TABLE OF mae_clasi.oid_clas%TYPE;
    TYPE t_oidtipoclie IS TABLE OF mae_tipo_clien.oid_tipo_clie%TYPE;
    TYPE t_oidsubtclie IS TABLE OF mae_subti_clien.oid_subt_clie%TYPE;

    v_oidtipoclasi t_oidtipoclasi;
    v_oidclasi     t_oidclasi;
    v_oidtipoclie  t_oidtipoclie;
    v_oidsubtclie  t_oidsubtclie;

    -- Curso que elimina la clasificacion a aquellas consultoras cuyas duplas
    -- no estan en el rango de periodos validos (2 campañas siguientes a la
    -- campaña en la que cumplio años).
    CURSOR cdeleteclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND NOT EXISTS (SELECT NULL
                FROM cyz_dupla_cumpl_perio dcp
               WHERE (dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -1) OR
                     dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -2))
                 AND cts.clie_oid_clie = dcp.clie_oid_clie);

    -- Cursor que elimina la clasificacion a las consultoras cuyas duplas
    -- tuvieron despacho de los productos asociados a esta estrategia en un
    -- periodo minimo de 2 campañas. TODO revisar si se quita por el tema de la oferta para consultoras.
    CURSOR cdeleteclasifdespa
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER,
      numperiodos  NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND EXISTS (SELECT NULL
                FROM cyz_despa_produ cdp
               WHERE cdp.clie_oid_clie = cts.clie_oid_clie
                 AND cdp.prdu_cod_prog = pscodigoprograma
                 AND cdp.pais_cod_pais = pscodigopais
                 AND cdp.num_unid_aten > 0
                 AND gen_fn_calcu_perio(cdp.cod_peri,
                                        numperiodos) > pscodigoperiodo);

    TYPE t_oidclieclasi IS TABLE OF mae_clien_clasi.oid_clie_clas%TYPE;
    v_oidclieclasi t_oidclieclasi;

    CURSOR cinsertclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND EXISTS (SELECT NULL
                FROM cyz_dupla_cumpl_perio dcp
               WHERE (dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -1) OR
                     dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -2))
                 AND cts.clie_oid_clie = dcp.clie_oid_clie)
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_clasi mcc
               WHERE mcc.tccl_oid_tipo_clasi = oidtipoclasi
                 AND mcc.clas_oid_clas = oidclasi
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt);

    TYPE t_oidclietiposubt IS TABLE OF mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
    v_oidclietiposubt t_oidclietiposubt;

    lnoidperiodo  NUMBER;
    lnoidpais     NUMBER;
    lnoidcanal    NUMBER;
    lnoidmarca    NUMBER;
    lnnumperiodos NUMBER;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Validamos que el programa este activo y vigente
    SELECT COUNT(*)
      INTO l_count
      FROM cyz_progr_dupla
     WHERE cod_pais = pscodigopais
       AND cod_prog = pscodigoprograma
       AND est_prog = '1'
       AND pscodigoperiodo >= cod_peri_inic
       AND pscodigoperiodo <= cod_peri_fina;

    IF l_count > 0 THEN

      SELECT num_peri_eval
        INTO lnnumperiodos
        FROM cyz_progr_dupla
       WHERE cod_pais = pscodigopais
         AND cod_prog = pscodigoprograma;

      OPEN cclasificacion;
      LOOP
        FETCH cclasificacion BULK COLLECT
          INTO v_oidtipoclasi,
               v_oidclasi,
               v_oidtipoclie,
               v_oidsubtclie LIMIT w_filas;
        IF v_oidtipoclasi.count > 0 THEN
          FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
          LOOP

            -- Abrimos el cursor para la eliminacion de clasificaciones
            OPEN cdeleteclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cdeleteclasif BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasif%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasif;

            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cinsertclasif BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL k IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(k),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasif%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasif;

            -- Eliminamos la clasificacion a aquellos que ya se les ha despachado el producto
            OPEN cdeleteclasifdespa(v_oidtipoclie(i),
                                    v_oidsubtclie(i),
                                    v_oidtipoclasi(i),
                                    v_oidclasi(i),
                                    lnnumperiodos);
            LOOP
              FETCH cdeleteclasifdespa BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasifdespa%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasifdespa;

          END LOOP;
        END IF;

        EXIT WHEN cclasificacion%NOTFOUND;
      END LOOP;
      CLOSE cclasificacion;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_CLASI_OFERT_CUMPL: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza los detalles del pedido al
                      momento de hacer la carga para validar que la consultora
                      solo solicite una de las ofertas.
  Fecha Creacion    : 08/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_solic_cumpl
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  ) IS

    CURSOR cupdatedetalle IS
      SELECT x.cod_pais,
             x.cod_peri,
             x.cod_clie,
             x.num_lote,
             x.cod_vent,
             x.val_unid_dem,
             x.ind_erro_rech
        FROM int_solic_conso_detal x,
             cyz_produ_progr_dupla y
       WHERE x.cod_pais = y.pais_cod_pais
         AND x.cod_peri = y.cod_peri
         AND x.cod_vent = y.val_codi_vent
         AND y.prdu_cod_prog = pscodigoprograma
         AND EXISTS (

              SELECT cod_pais,
                      cod_peri,
                      cod_clie,
                      num_lote
                FROM (SELECT cab.cod_pais,
                              cab.cod_peri,
                              cab.cod_clie,
                              cab.num_lote,
                              COUNT(*)
                         FROM int_solic_conso_cabec cab,
                              int_solic_conso_detal det,
                              cyz_produ_progr_dupla ppd
                        WHERE cab.cod_pais = det.cod_pais
                          AND cab.cod_clie = det.cod_clie
                          AND cab.cod_peri = det.cod_peri
                          AND cab.num_lote = det.num_lote
                          AND ppd.pais_cod_pais = det.cod_pais
                          AND ppd.cod_peri = det.cod_peri
                          AND ppd.val_codi_vent = det.cod_vent
                          AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
                          AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
                          AND cab.ind_erro_remp = '0' -- Ultimos pedidos
                          AND cab.ind_erro_rech = '0' -- No rechazados
                          AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
                          AND cab.cod_pais = pscodigopais
                          AND cab.cod_peri = pscodigoperiodo
                          AND ppd.pais_cod_pais = pscodigopais
                          AND ppd.cod_peri = pscodigoperiodo
                          AND ppd.prdu_cod_prog = pscodigoprograma
                        GROUP BY cab.cod_pais,
                                 cab.cod_peri,
                                 cab.cod_clie,
                                 cab.num_lote
                       HAVING COUNT(*) > 1) z
               WHERE z.cod_pais = x.cod_pais
                 AND z.cod_clie = x.cod_clie
                 AND z.cod_peri = x.cod_peri
                 AND z.num_lote = x.num_lote

              )
       ORDER BY x.cod_clie,
                y.val_codi_vent;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_detal.ind_erro_rech%TYPE;

    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_num_lote      t_num_lote;
    v_cod_vent      t_cod_vent;
    v_val_unid_dem  t_val_unid_dem;
    v_ind_erro_rech t_ind_erro_rech;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

    l_codigocliente VARCHAR2(25) := 'X';

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem,
             v_ind_erro_rech LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Validamos si se trata del mismo cliente
          -- de la variable auxiliar
          IF l_codigocliente <> v_cod_clie(j) THEN
            UPDATE int_solic_conso_detal det
               SET det.ind_erro_rech = 0 -- Dejamos activo el primer cuv
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);

            l_codigocliente := v_cod_clie(j);
          ELSE
            UPDATE int_solic_conso_detal det
               SET /*det.ind_erro_rech*/ det.ind_erro_sse = ind_error_sse_dupla_cyzone --1 -- Rechazamos el resto de cuvs
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_SOLIC_CUMPL: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_PRODU_PROGR_DUPLA,
                      si existen unidades que exceden el valor de VAL_LIMI_CTRL_VENT
                      estas se actualizan a dicho valor. Esto aplica para todos
                      los productos asociados a los programas activos en el
                      periodo a procesar.
  Fecha Creacion    : 08/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_solic
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem,
             ppd.val_limi_ctrl_vent
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det,
             cyz_produ_progr_dupla ppd,
             cyz_progr_dupla       cpd
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND ppd.pais_cod_pais = det.cod_pais
         AND ppd.cod_peri = det.cod_peri
         AND ppd.val_codi_vent = det.cod_vent
         AND cpd.cod_pais = ppd.pais_cod_pais
         AND cpd.cod_prog = ppd.prdu_cod_prog
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cpd.est_prog = '1' -- Programa activo
         AND cpd.cod_peri_inic <= pscodigoperiodo
         AND cpd.cod_peri_fina >= pscodigoperiodo
         AND ppd.val_limi_ctrl_vent != 0 -- limite de venta mayor a 0
         AND det.val_unid_dem > ppd.val_limi_ctrl_vent -- Exceden el valor maximo
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_val_limi_ctrl_vent IS TABLE OF cyz_produ_progr_dupla.val_limi_ctrl_vent%TYPE;

    v_cod_pais           t_cod_pais;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_num_lote           t_num_lote;
    v_cod_vent           t_cod_vent;
    v_val_unid_dem       t_val_unid_dem;
    v_val_limi_ctrl_vent t_val_limi_ctrl_vent;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem,
             v_val_limi_ctrl_vent LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FORALL j IN v_cod_pais.first .. v_cod_pais.last
          UPDATE int_solic_conso_detal det
             SET det.val_unid_dem = v_val_limi_ctrl_vent(j)
           WHERE det.cod_pais = v_cod_pais(j)
             AND det.cod_peri = v_cod_peri(j)
             AND det.cod_clie = v_cod_clie(j)
             AND det.num_lote = v_num_lote(j)
             AND det.cod_vent = v_cod_vent(j);
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_NUMER_UNIDA_SOLIC: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta en el buzon los mensajes configurados
                      para las consultoras que NO han adquirido un determinado producto,
                      asociado a un programa especifico, ya sea porque no cuentan con
                      dupla o porque no cumplen con las reglas definidas para
                      la estrategia.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_mensa_error_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    l_programa NUMBER := 0;
    l_mensaje  NUMBER := 0;

  BEGIN

    SELECT COUNT(*)
      INTO l_programa
      FROM cyz_progr_dupla cpd
     WHERE cpd.cod_pais = pscodigopais
       AND cpd.cod_prog = pscodigoprograma
       AND cpd.est_prog = '1' -- Activo
       AND cpd.cod_peri_inic <= pscodigoperiodo
       AND cpd.cod_peri_fina >= pscodigoperiodo;

    SELECT COUNT(*)
      INTO l_mensaje
      FROM cyz_mensa_progr_dupla mpd
     WHERE mpd.pais_cod_pais = pscodigopais
       AND mpd.prdu_cod_prog = pscodigoprograma
       AND mpd.est_mens = '1' -- Activo
       AND (mpd.ind_erro_nego = 1 OR mpd.ind_erro_nodu = 1); -- Mensajes de error

    -- Solo enviamos mensajes si el programa esta vigente
    -- y si existen mensaje de error configurados
    IF l_programa > 0 AND l_mensaje > 0 THEN

      -- Primero cargamos la relacion de clientes a los cuales hay que enviarles el mensaje
      cyz_pr_carga_clien_mensa_error(pscodigopais,
                                     pscodigoprograma,
                                     pscodigoperiodo);

      -- Eliminamos los mensajes antiguos que no fueron impresos
      cyz_pr_elimi_mensa_error_nimpr(pscodigopais,
                                     pscodigoprograma);

      -- Enviamos los mensajes de error a los clientes identificados previamente
      cyz_pr_envia_buzon_mensa_error(pscodigopais,
                                     pscodigoprograma,
                                     pscodigoperiodo);

    END IF;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica a los clientes a los cuales se
                      les ha de insertar un mensaje en el buzon, debido a que NO
                      se les despacho el producto de la estrategia.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_clien_mensa_error
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS
    lnoidperiodo      NUMBER;
    lnoidpais         NUMBER;
    lnoidtiposolipais NUMBER;
    lnoidcanal        NUMBER;
    lnoidmarca        NUMBER;

    CURSOR cclientes
    (
      oidpais         NUMBER,
      oidtiposolipais NUMBER,
      oidperiodo      NUMBER
    ) IS
      SELECT clie_oid_clie,
             oid_soli_cabe
        FROM ped_solic_cabec psc
       WHERE psc.pais_oid_pais = oidpais
         AND psc.tspa_oid_tipo_soli_pais = oidtiposolipais
         AND psc.perd_oid_peri = oidperiodo
         AND psc.grpr_oid_grup_proc = 4 -- GP4
         AND EXISTS (SELECT NULL
                FROM ped_solic_posic       psp,
                     mae_produ             mp,
                     cyz_produ_progr_dupla ppd
               WHERE psp.prod_oid_prod = mp.oid_prod
                 AND mp.cod_sap = ppd.cod_prod
                 AND ppd.cod_peri = pscodigoperiodo
                 AND ppd.pais_cod_pais = pscodigopais
                 AND ppd.prdu_cod_prog = pscodigoprograma
                 AND psp.soca_oid_soli_cabe = psc.oid_soli_cabe
                 AND psp.val_codi_vent = ppd.val_codi_vent
                 AND psp.num_unid_dema > 0
                 AND psp.espo_oid_esta_posi = 2 -- Estado Anulado
              );

    TYPE t_oidclie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
    TYPE t_oidsolicabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;

    v_oidclie     t_oidclie;
    v_oidsolicabe t_oidsolicabe;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    SELECT b.oid_tipo_soli_pais
      INTO lnoidtiposolipais
      FROM ped_tipo_solic      a,
           ped_tipo_solic_pais b
     WHERE a.oid_tipo_soli = b.tsol_oid_tipo_soli
       AND a.cod_tipo_soli = cod_tipo_soli_oc;

    -- Limpiamos la tabla que contendra la lista de clientes
    EXECUTE IMMEDIATE 'TRUNCATE TABLE CYZ_TMP_CLIEN_MENSA';

    OPEN cclientes(lnoidpais,
                   lnoidtiposolipais,
                   lnoidperiodo);
    LOOP
      FETCH cclientes BULK COLLECT
        INTO v_oidclie,
             v_oidsolicabe LIMIT w_filas;
      IF v_oidclie.count > 0 THEN

        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_tmp_clien_mensa
            (cod_pais,
             cod_prog,
             cod_peri,
             oid_clie,
             oid_soli_cabe,
             fec_proc)
          VALUES
            (pscodigopais,
             pscodigoprograma,
             pscodigoperiodo,
             v_oidclie(i),
             v_oidsolicabe(i),
             SYSDATE);
      END IF;

      EXIT WHEN cclientes%NOTFOUND;
    END LOOP;
    CLOSE cclientes;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_CARGA_CLIEN_MENSA_ERROR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que elimina mensajes de error antiguos que
                      no han sido impresos.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
  ***************************************************************************/
  PROCEDURE cyz_pr_elimi_mensa_error_nimpr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2
  ) IS

    CURSOR cmensaje(oidpais NUMBER) IS
      SELECT a.oid_buzo_mens
        FROM msg_buzon_mensa       a,
             msg_mensa             b,
             cyz_mensa_progr_dupla c
       WHERE b.pais_oid_pais = oidpais
         AND b.oid_mens = a.mens_oid_mens
         AND c.cod_mens = b.cod_mens
         AND c.prdu_cod_prog = pscodigoprograma
         AND c.pais_cod_pais = pscodigopais
         AND (c.ind_erro_nego = 1 OR c.ind_erro_nodu = 1)
         AND a.num_lote_impr IS NULL;

    TYPE t_oidmensaje IS TABLE OF msg_buzon_mensa.oid_buzo_mens%TYPE;
    v_oidmensaje t_oidmensaje;
    lnoidpais    NUMBER;

  BEGIN
    -- Obtenemos el oid del pais
    lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    /* eliminando los mensajes sin imprimir */
    OPEN cmensaje(lnoidpais);
    LOOP
      FETCH cmensaje BULK COLLECT
        INTO v_oidmensaje LIMIT w_filas;
      IF v_oidmensaje.count > 0 THEN
        FORALL i IN v_oidmensaje.first .. v_oidmensaje.last
          DELETE FROM msg_buzon_mensa a WHERE a.oid_buzo_mens = v_oidmensaje(i);
      END IF;
      EXIT WHEN cmensaje%NOTFOUND;
    END LOOP;
    CLOSE cmensaje;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'CYZ_PR_ELIMI_MENSA_ERROR_NIMPR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los mensajes de error en el buzon
                      de los clientes identificados por el proceso anterior.
  Fecha Creacion    : 23/06/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_envia_buzon_mensa_error
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    CURSOR cmensajeerrornegocio IS
      SELECT men.oid_mens
        FROM cyz_mensa_progr_dupla mpd,
             msg_mensa             men
       WHERE mpd.cod_mens = men.cod_mens
         AND mpd.pais_cod_pais = pscodigopais
         AND mpd.prdu_cod_prog = pscodigoprograma
         AND mpd.ind_erro_nego = 1 -- Mensaje de error de negocio
         AND mpd.ind_erro_nodu = 0 -- No es mensaje de error por no contar con dupla
         AND mpd.est_mens = '1';

    CURSOR cmensajeerrornodupla IS
      SELECT men.oid_mens
        FROM cyz_mensa_progr_dupla mpd,
             msg_mensa             men
       WHERE mpd.cod_mens = men.cod_mens
         AND mpd.pais_cod_pais = pscodigopais
         AND mpd.prdu_cod_prog = pscodigoprograma
         AND mpd.ind_erro_nego = 0 -- No es mensaje de error de negocio
         AND mpd.ind_erro_nodu = 1 -- Mensaje de error por no contar con dupla
         AND mpd.est_mens = '1';

    TYPE t_oidmens IS TABLE OF msg_mensa.oid_mens%TYPE;

    v_oidmens t_oidmens;

  BEGIN

    -- Para clientes con posiciones rechazadas que si tienen dupla
    OPEN cmensajeerrornegocio;
    LOOP
      FETCH cmensajeerrornegocio BULK COLLECT
        INTO v_oidmens LIMIT w_filas;

      IF v_oidmens.count > 0 THEN
        FOR i IN v_oidmens.first .. v_oidmens.last
        LOOP

          INSERT INTO msg_buzon_mensa
            (oid_buzo_mens,
             num_secu,
             clie_oid_clie,
             mens_oid_mens,
             modu_oid_modu_orig,
             fec_grab,
             ind_list_cons,
             ind_acti)
            SELECT msg_bume_seq.nextval,
                   msg_bum2_seq.nextval,
                   oid_clie,
                   v_oidmens(i),
                   13, -- Incentivos
                   SYSDATE,
                   1,
                   1
              FROM cyz_tmp_clien_mensa x
             WHERE x.cod_pais = pscodigopais
               AND x.cod_prog = pscodigoprograma
               AND x.cod_peri = pscodigoperiodo
               AND EXISTS (SELECT NULL
                      FROM mae_clien_vincu mcv
                     WHERE mcv.clie_oid_clie_vnte = x.oid_clie
                       AND mcv.tivc_oid_tipo_vinc = 1 --  Dupla
                    );

        END LOOP;
      END IF;

      EXIT WHEN cmensajeerrornegocio%NOTFOUND;
    END LOOP;
    CLOSE cmensajeerrornegocio;

    -- Para clientes con posiciones rechazadas que no tienen dupla
    OPEN cmensajeerrornodupla;
    LOOP
      FETCH cmensajeerrornodupla BULK COLLECT
        INTO v_oidmens LIMIT w_filas;

      IF v_oidmens.count > 0 THEN
        FOR i IN v_oidmens.first .. v_oidmens.last
        LOOP

          INSERT INTO msg_buzon_mensa
            (oid_buzo_mens,
             num_secu,
             clie_oid_clie,
             mens_oid_mens,
             modu_oid_modu_orig,
             fec_grab,
             ind_list_cons,
             ind_acti)
            SELECT msg_bume_seq.nextval,
                   msg_bum2_seq.nextval,
                   oid_clie,
                   v_oidmens(i),
                   13, -- Incentivos
                   SYSDATE,
                   1,
                   1
              FROM cyz_tmp_clien_mensa x
             WHERE x.cod_pais = pscodigopais
               AND x.cod_prog = pscodigoprograma
               AND x.cod_peri = pscodigoperiodo
               AND NOT EXISTS (SELECT NULL
                      FROM mae_clien_vincu mcv
                     WHERE mcv.clie_oid_clie_vnte = x.oid_clie
                       AND mcv.tivc_oid_tipo_vinc = 1 --  Dupla
                    );

        END LOOP;
      END IF;

      EXIT WHEN cmensajeerrornodupla%NOTFOUND;
    END LOOP;
    CLOSE cmensajeerrornodupla;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ENVIA_BUZON_MENSA_ERROR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos.
  Fecha Creacion    : 26/02/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_dupla_inscr
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 3
  ) IS

    CURSOR cbolsaclientes IS
      SELECT pdi.clie_oid_clie,
             pdi.cod_clie,
             ppd.cod_prod,
             gen_fn_calcu_perio(ppd.cod_peri,
                                pncantidadperiodosinicio) cod_peri_inic,
             gen_fn_calcu_perio(ppd.cod_peri,
                                (pncantidadperiodosinicio + pncantidadperiodosvigencia)) cod_peri_fina,
             2 num_unid_tota,
             0 num_unid_aten
        FROM cyz_produ_progr_dupla ppd,
             (SELECT cpd.pais_cod_pais,
                     cpd.cod_peri,
                     cpd.clie_oid_clie,
                     cpd.cod_clie,
                     MAX(cpd.clie_oid_dupl) clie_oid_dupl
                FROM cyz_prime_dupla_inscr cpd
               WHERE cpd.pais_cod_pais = pscodigopais
                 AND cpd.cod_peri = pscodigoperiodo
               GROUP BY cpd.pais_cod_pais,
                        cpd.cod_peri,
                        cpd.clie_oid_clie,
                        cpd.cod_clie) pdi
       WHERE ppd.pais_cod_pais = pdi.pais_cod_pais
         AND ppd.cod_peri = pdi.cod_peri
         AND ppd.prdu_cod_prog = pscodigoprograma
         AND NOT EXISTS (SELECT NULL
                FROM cyz_bolsa_produ_progr bpp
               WHERE bpp.pais_cod_pais = ppd.pais_cod_pais -- No consideramos la condicion
                 AND bpp.clie_oid_clie = pdi.clie_oid_clie -- del periodo ya que solo se
                 AND bpp.prdu_cod_prog = ppd.prdu_cod_prog -- espera que haya un registro por
                 AND bpp.cod_prod = ppd.cod_prod -- consultora
              );

    TYPE t_oidclie IS TABLE OF cyz_bolsa_produ_progr.clie_oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF cyz_bolsa_produ_progr.cod_clie%TYPE;
    TYPE t_codprod IS TABLE OF cyz_bolsa_produ_progr.cod_prod%TYPE;
    TYPE t_codperiinic IS TABLE OF cyz_bolsa_produ_progr.cod_peri_inic%TYPE;
    TYPE t_codperifina IS TABLE OF cyz_bolsa_produ_progr.cod_peri_fina%TYPE;
    TYPE t_numunidtota IS TABLE OF cyz_bolsa_produ_progr.num_unid_tota%TYPE;
    TYPE t_numunidaten IS TABLE OF cyz_bolsa_produ_progr.num_unid_aten%TYPE;

    v_oidclie     t_oidclie;
    v_codclie     t_codclie;
    v_codprod     t_codprod;
    v_codperiinic t_codperiinic;
    v_codperifina t_codperifina;
    v_numunidtota t_numunidtota;
    v_numunidaten t_numunidaten;

  BEGIN

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cbolsaclientes;
    LOOP
      FETCH cbolsaclientes BULK COLLECT
        INTO v_oidclie,
             v_codclie,
             v_codprod,
             v_codperiinic,
             v_codperifina,
             v_numunidtota,
             v_numunidaten LIMIT w_filas;
      IF v_oidclie.count > 0 THEN
        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_bolsa_produ_progr
            (pais_cod_pais,
             prdu_cod_prog,
             cod_peri_proc,
             clie_oid_clie,
             cod_clie,
             cod_prod,
             cod_peri_inic,
             cod_peri_fina,
             num_unid_tota,
             num_unid_aten,
             usu_digi,
             fec_digi)
          VALUES
            (pscodigopais,
             pscodigoprograma,
             pscodigoperiodo,
             v_oidclie(i),
             v_codclie(i),
             v_codprod(i),
             v_codperiinic(i),
             v_codperifina(i),
             v_numunidtota(i),
             v_numunidaten(i),
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cbolsaclientes%NOTFOUND;
    END LOOP;
    CLOSE cbolsaclientes;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_CARGA_BOLSA_PRODU_PROGR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos, la relacion de consultoras se
                      toma a partir de las duplas que cumplen años en el periodo.
  Fecha Creacion    : 26/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_cumpl_dupla
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 2
  ) IS

    CURSOR cbolsaclientes IS
      SELECT dcp.clie_oid_clie,
             dcp.cod_clie,
             ppd.cod_prod,
             gen_fn_calcu_perio(ppd.cod_peri,
                                pncantidadperiodosinicio) cod_peri_inic,
             gen_fn_calcu_perio(ppd.cod_peri,
                                (pncantidadperiodosinicio + pncantidadperiodosvigencia)) cod_peri_fina,
             1 num_unid_tota,
             0 num_unid_aten
        FROM cyz_produ_progr_dupla ppd,
             (SELECT x.pais_cod_pais,
                     x.cod_peri,
                     x.clie_oid_clie,
                     x.cod_clie,
                     MAX(x.clie_oid_dupl) clie_oid_dupl
                FROM cyz_dupla_cumpl_perio x
               WHERE x.pais_cod_pais = pscodigopais
                 AND x.cod_peri = pscodigoperiodo
               GROUP BY x.pais_cod_pais,
                        x.cod_peri,
                        x.clie_oid_clie,
                        x.cod_clie) dcp
       WHERE ppd.pais_cod_pais = dcp.pais_cod_pais
         AND ppd.cod_peri = dcp.cod_peri
         AND ppd.prdu_cod_prog = pscodigoprograma
         AND NOT EXISTS (SELECT NULL
                FROM cyz_bolsa_produ_progr bpp
               WHERE bpp.pais_cod_pais = ppd.pais_cod_pais
                 AND bpp.clie_oid_clie = dcp.clie_oid_clie
                 AND bpp.prdu_cod_prog = ppd.prdu_cod_prog
                 AND bpp.cod_peri_proc = ppd.cod_peri -- Aqui si consideramos el periodo
                 AND bpp.cod_prod = ppd.cod_prod);

    TYPE t_oidclie IS TABLE OF cyz_bolsa_produ_progr.clie_oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF cyz_bolsa_produ_progr.cod_clie%TYPE;
    TYPE t_codprod IS TABLE OF cyz_bolsa_produ_progr.cod_prod%TYPE;
    TYPE t_codperiinic IS TABLE OF cyz_bolsa_produ_progr.cod_peri_inic%TYPE;
    TYPE t_codperifina IS TABLE OF cyz_bolsa_produ_progr.cod_peri_fina%TYPE;
    TYPE t_numunidtota IS TABLE OF cyz_bolsa_produ_progr.num_unid_tota%TYPE;
    TYPE t_numunidaten IS TABLE OF cyz_bolsa_produ_progr.num_unid_aten%TYPE;

    v_oidclie     t_oidclie;
    v_codclie     t_codclie;
    v_codprod     t_codprod;
    v_codperiinic t_codperiinic;
    v_codperifina t_codperifina;
    v_numunidtota t_numunidtota;
    v_numunidaten t_numunidaten;

  BEGIN

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cbolsaclientes;
    LOOP
      FETCH cbolsaclientes BULK COLLECT
        INTO v_oidclie,
             v_codclie,
             v_codprod,
             v_codperiinic,
             v_codperifina,
             v_numunidtota,
             v_numunidaten LIMIT w_filas;
      IF v_oidclie.count > 0 THEN
        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_bolsa_produ_progr
            (pais_cod_pais,
             prdu_cod_prog,
             cod_peri_proc,
             clie_oid_clie,
             cod_clie,
             cod_prod,
             cod_peri_inic,
             cod_peri_fina,
             num_unid_tota,
             num_unid_aten,
             usu_digi,
             fec_digi)
          VALUES
            (pscodigopais,
             pscodigoprograma,
             pscodigoperiodo,
             v_oidclie(i),
             v_codclie(i),
             v_codprod(i),
             v_codperiinic(i),
             v_codperifina(i),
             v_numunidtota(i),
             v_numunidaten(i),
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cbolsaclientes%NOTFOUND;
    END LOOP;
    CLOSE cbolsaclientes;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_CARGA_BOLSA_CUMPL_DUPLA: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que inserta los productos del programa de
                      bienvenida en la bolsa de productos de tal manera que se
                      pueda llevar el control de las unidades despachadas a lo
                      largo de los periodos en los cuales la consultora tiene
                      posibilidad de solicitarlos, la relacion de consultoras se
                      toma a partir de las consultoras que cumplen años en el
                      periodo.
  Fecha Creacion    : 26/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              psCodigoUsuario : Usuario que ejecuta el proceso
              pnCantidadPeriodosInicio : Numero de periodos a considerar desde el
                                         de proceso hasta el periodo inicial.
              pnCantidadPeriodosVigencia : Numero de periodos desde el periodo
                                           inicial al periodo de fin.
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_bolsa_cumpl_clien
  (
    pscodigopais               IN VARCHAR2,
    pscodigoprograma           IN VARCHAR2,
    pscodigoperiodo            IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    pncantidadperiodosinicio   IN NUMBER := 1,
    pncantidadperiodosvigencia IN NUMBER := 2
  ) IS

    CURSOR cbolsaclientes IS
      SELECT ccp.clie_oid_clie,
             ccp.cod_clie,
             ppd.cod_prod,
             gen_fn_calcu_perio(ppd.cod_peri,
                                pncantidadperiodosinicio) cod_peri_inic,
             gen_fn_calcu_perio(ppd.cod_peri,
                                (pncantidadperiodosinicio + pncantidadperiodosvigencia)) cod_peri_fina,
             1 num_unid_tota,
             0 num_unid_aten
        FROM cyz_produ_progr_dupla ppd,
             cyz_clien_cumpl_perio ccp
       WHERE ppd.pais_cod_pais = ccp.pais_cod_pais
         AND ppd.cod_peri = ccp.cod_peri
         AND ppd.pais_cod_pais = pscodigopais
         AND ppd.prdu_cod_prog = pscodigoprograma
         AND ppd.cod_peri = pscodigoperiodo
         AND NOT EXISTS (SELECT NULL
                FROM cyz_bolsa_produ_progr bpp
               WHERE bpp.pais_cod_pais = ppd.pais_cod_pais
                 AND bpp.clie_oid_clie = ccp.clie_oid_clie
                 AND bpp.prdu_cod_prog = ppd.prdu_cod_prog
                 AND bpp.cod_peri_proc = ppd.cod_peri -- Aqui si consideramos el periodo
                 AND bpp.cod_prod = ppd.cod_prod);

    TYPE t_oidclie IS TABLE OF cyz_bolsa_produ_progr.clie_oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF cyz_bolsa_produ_progr.cod_clie%TYPE;
    TYPE t_codprod IS TABLE OF cyz_bolsa_produ_progr.cod_prod%TYPE;
    TYPE t_codperiinic IS TABLE OF cyz_bolsa_produ_progr.cod_peri_inic%TYPE;
    TYPE t_codperifina IS TABLE OF cyz_bolsa_produ_progr.cod_peri_fina%TYPE;
    TYPE t_numunidtota IS TABLE OF cyz_bolsa_produ_progr.num_unid_tota%TYPE;
    TYPE t_numunidaten IS TABLE OF cyz_bolsa_produ_progr.num_unid_aten%TYPE;

    v_oidclie     t_oidclie;
    v_codclie     t_codclie;
    v_codprod     t_codprod;
    v_codperiinic t_codperiinic;
    v_codperifina t_codperifina;
    v_numunidtota t_numunidtota;
    v_numunidaten t_numunidaten;

  BEGIN

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cbolsaclientes;
    LOOP
      FETCH cbolsaclientes BULK COLLECT
        INTO v_oidclie,
             v_codclie,
             v_codprod,
             v_codperiinic,
             v_codperifina,
             v_numunidtota,
             v_numunidaten LIMIT w_filas;
      IF v_oidclie.count > 0 THEN
        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_bolsa_produ_progr
            (pais_cod_pais,
             prdu_cod_prog,
             cod_peri_proc,
             clie_oid_clie,
             cod_clie,
             cod_prod,
             cod_peri_inic,
             cod_peri_fina,
             num_unid_tota,
             num_unid_aten,
             usu_digi,
             fec_digi)
          VALUES
            (pscodigopais,
             pscodigoprograma,
             pscodigoperiodo,
             v_oidclie(i),
             v_codclie(i),
             v_codprod(i),
             v_codperiinic(i),
             v_codperifina(i),
             v_numunidtota(i),
             v_numunidaten(i),
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cbolsaclientes%NOTFOUND;
    END LOOP;
    CLOSE cbolsaclientes;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_CARGA_BOLSA_CUMPL_CONSU: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza las clasificaciones de los
                      clientes que son evaluados como parte del programa
                      Welcome Pack en su nueva version vigente a partir del
                      periodo 201005.
  Fecha Creacion    : 22/02/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_paque_bien2
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    l_count        NUMBER := 0;
    l_mensajeerror VARCHAR2(4000);

    /* Cursor que identifica las clasificaciones asociadas al codigo de programa */
    CURSOR cclasificacion IS
      SELECT tcc.oid_tipo_clas,
             mc.oid_clas,
             mtc.oid_tipo_clie,
             msc.oid_subt_clie
        FROM cyz_clasi_progr_dupla cpd,
             mae_tipo_clien        mtc,
             mae_subti_clien       msc,
             mae_tipo_clasi_clien  tcc,
             mae_clasi             mc
       WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
         AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
         AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
         AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
         AND msc.cod_subt_clie = cpd.cod_subt_clie
         AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
         AND mc.cod_clas = cpd.cod_clas_prdu
         AND cpd.pais_cod_pais = pscodigopais
         AND cpd.prdu_cod_prog = pscodigoprograma;

    TYPE t_oidtipoclasi IS TABLE OF mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    TYPE t_oidclasi IS TABLE OF mae_clasi.oid_clas%TYPE;
    TYPE t_oidtipoclie IS TABLE OF mae_tipo_clien.oid_tipo_clie%TYPE;
    TYPE t_oidsubtclie IS TABLE OF mae_subti_clien.oid_subt_clie%TYPE;

    v_oidtipoclasi t_oidtipoclasi;
    v_oidclasi     t_oidclasi;
    v_oidtipoclie  t_oidtipoclie;
    v_oidsubtclie  t_oidsubtclie;

    -- Cursor que elimina las clasificaciones previas de tal manera que se
    -- haga un ingreso total de las clasificaciones
    CURSOR cdeleteclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie;

    TYPE t_oidclieclasi IS TABLE OF mae_clien_clasi.oid_clie_clas%TYPE;
    v_oidclieclasi t_oidclieclasi;

    -- Cursor que insertara las clasificaciones, se consideran a las consultoras
    -- que estan en la tabla CYZ_BOLSA_PRODU_PROGR en el dentro del rango de
    -- periodos y que tienen unidades por atender pendientes para cualquier
    -- producto.
    CURSOR cinsertclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND EXISTS (SELECT NULL
                FROM cyz_bolsa_produ_progr bpp
               WHERE bpp.clie_oid_clie = cda.clie_oid_clie
                 AND bpp.cod_peri_inic <= pscodigoperiodo
                 AND bpp.cod_peri_fina >= pscodigoperiodo
                 AND bpp.num_unid_tota > num_unid_aten -- Tiene unidades disponibles
              );

    TYPE t_oidclietiposubt IS TABLE OF mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
    v_oidclietiposubt t_oidclietiposubt;

    lnoidperiodo NUMBER;
    lnoidpais    NUMBER;
    lnoidcanal   NUMBER;
    lnoidmarca   NUMBER;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Validamos que el programa este activo y vigente
    SELECT COUNT(*)
      INTO l_count
      FROM cyz_progr_dupla
     WHERE cod_pais = pscodigopais
       AND cod_prog = pscodigoprograma
       AND est_prog = '1'
       AND pscodigoperiodo >= cod_peri_inic
       AND pscodigoperiodo <= cod_peri_fina;

    -- Si el programa esta vigente
    IF l_count != 0 THEN

      OPEN cclasificacion;
      LOOP
        FETCH cclasificacion BULK COLLECT
          INTO v_oidtipoclasi,
               v_oidclasi,
               v_oidtipoclie,
               v_oidsubtclie LIMIT w_filas;
        IF v_oidtipoclasi.count > 0 THEN
          FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
          LOOP

            -- Abrimos el cursor para la eliminacion de clasificaciones
            OPEN cdeleteclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cdeleteclasif BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasif%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasif;

            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cinsertclasif BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL k IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(k),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasif%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasif;

          END LOOP;
        END IF;

        EXIT WHEN cclasificacion%NOTFOUND;
      END LOOP;
      CLOSE cclasificacion;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_CLASI_PAQUE_BIEN2: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle.
  Fecha Creacion    : 17/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_bien2
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo -- Pedidos del periodo
         AND EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd, -- Para obtener los CUVS
                     cyz_progr_dupla       cpd
               WHERE cpd.cod_pais = ppd.pais_cod_pais
                 AND cpd.cod_prog = ppd.prdu_cod_prog
                 AND cpd.ind_paqu_bien = 1 -- Oferta Welcome Pack
                 AND cpd.est_prog = '1' -- Programa activo
                 AND cpd.cod_peri_inic <= pscodigoperiodo -- Programa Vigente
                 AND cpd.cod_peri_fina >= pscodigoperiodo -- Programa Vigente
                 AND ppd.cod_peri = pscodigoperiodo -- Productos del periodo
                 AND ppd.pais_cod_pais = det.cod_pais
                 AND ppd.cod_peri = det.cod_peri
                 AND ppd.val_codi_vent = det.cod_vent);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_val_unid_dem t_val_unid_dem;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

    lnunidadesdisponibles NUMBER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Obtenemos el valor de unidades disponibles
          SELECT nvl(SUM(bpp.num_unid_tota - bpp.num_unid_aten),
                     0)
            INTO lnunidadesdisponibles
            FROM cyz_bolsa_produ_progr bpp
           WHERE bpp.cod_clie = v_cod_clie(j)
             AND bpp.cod_peri_inic <= pscodigoperiodo -- Bolsa Vigente
             AND bpp.cod_peri_fina >= pscodigoperiodo -- Bolsa Vigente
             AND EXISTS (SELECT NULL
                    FROM cyz_produ_progr_dupla ppd,
                         cyz_progr_dupla       cpd
                   WHERE cpd.cod_pais = ppd.pais_cod_pais
                     AND cpd.cod_prog = ppd.prdu_cod_prog
                     AND ppd.pais_cod_pais = bpp.pais_cod_pais
                     AND ppd.cod_prod = bpp.cod_prod
                     AND ppd.prdu_cod_prog = bpp.prdu_cod_prog
                     AND cpd.ind_paqu_bien = 1 -- Oferta Welcome Pack
                     AND cpd.est_prog = '1' -- Estado Activo
                     AND ppd.cod_peri = pscodigoperiodo
                     AND ppd.val_codi_vent = v_cod_vent(j));

          -- Validamos si existen unidades disponibles
          IF lnunidadesdisponibles > 0 THEN
            -- Actualizamos el valor de las unidades
            -- solo si superan al valor del disponible
            IF v_val_unid_dem(j) > lnunidadesdisponibles THEN
              UPDATE int_solic_conso_detal det
                 SET det.val_unid_dem = lnunidadesdisponibles, -- actualizamos el valor de unidades
                     det.usu_modi     = pscodigousuario,
                     det.fec_modi     = SYSDATE
               WHERE det.cod_pais = v_cod_pais(j)
                 AND det.cod_peri = v_cod_peri(j)
                 AND det.cod_clie = v_cod_clie(j)
                 AND det.num_lote = v_num_lote(j)
                 AND det.cod_vent = v_cod_vent(j);
            END IF;
          ELSE
            UPDATE int_solic_conso_detal det
               SET /*det.ind_erro_rech*/ det.ind_erro_sse = ind_error_sse_dupla_cyzone, --1, -- Rechazamos el cuv
                   det.usu_modi     = pscodigousuario,
                   det.fec_modi     = SYSDATE
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_NUMER_UNIDA_BIEN2: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      para las estrategias de cumpleaños (dupla y consultora)
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle.
  Fecha Creacion    : 25/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_numer_unida_cumpl
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS
    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo -- Pedidos del periodo
         AND EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd, -- Para obtener los CUVS
                     cyz_progr_dupla       cpd
               WHERE cpd.cod_pais = ppd.pais_cod_pais
                 AND cpd.cod_prog = ppd.prdu_cod_prog
                 AND cpd.ind_ofer_cump = 1 -- Oferta de cumpleaños
                 AND cpd.est_prog = '1' -- Programa activo
                 AND cpd.cod_peri_inic <= pscodigoperiodo -- Programa Vigente
                 AND cpd.cod_peri_fina >= pscodigoperiodo -- Programa Vigente
                 AND ppd.cod_peri = pscodigoperiodo -- Productos del periodo
                 AND ppd.pais_cod_pais = det.cod_pais
                 AND ppd.cod_peri = det.cod_peri
                 AND ppd.val_codi_vent = det.cod_vent);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_val_unid_dem t_val_unid_dem;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

    lnunidadesdisponibles NUMBER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Obtenemos el valor de unidades disponibles
          SELECT nvl(SUM(bpp.num_unid_tota - bpp.num_unid_aten),
                     0)
            INTO lnunidadesdisponibles
            FROM cyz_bolsa_produ_progr bpp
           WHERE bpp.cod_clie = v_cod_clie(j)
             AND bpp.cod_peri_inic <= pscodigoperiodo -- Bolsa Vigente
             AND bpp.cod_peri_fina >= pscodigoperiodo -- Bolsa Vigente
             AND EXISTS (SELECT NULL
                    FROM cyz_produ_progr_dupla ppd,
                         cyz_progr_dupla       cpd
                   WHERE cpd.cod_pais = ppd.pais_cod_pais
                     AND cpd.cod_prog = ppd.prdu_cod_prog
                     AND ppd.pais_cod_pais = bpp.pais_cod_pais
                     AND ppd.cod_prod = bpp.cod_prod
                     AND ppd.prdu_cod_prog = bpp.prdu_cod_prog
                     AND cpd.ind_ofer_cump = 1 -- Oferta de cumpleaños
                     AND cpd.est_prog = '1' -- Estado Activo
                     AND ppd.cod_peri = pscodigoperiodo
                     AND ppd.val_codi_vent = v_cod_vent(j));

          -- Validamos si existen unidades disponibles
          IF lnunidadesdisponibles > 0 THEN
            -- Actualizamos el valor de las unidades
            -- solo si superan al valor del disponible
            IF v_val_unid_dem(j) > lnunidadesdisponibles THEN
              UPDATE int_solic_conso_detal det
                 SET det.val_unid_dem = lnunidadesdisponibles, -- actualizamos el valor de unidades
                     det.usu_modi     = pscodigousuario,
                     det.fec_modi     = SYSDATE
               WHERE det.cod_pais = v_cod_pais(j)
                 AND det.cod_peri = v_cod_peri(j)
                 AND det.cod_clie = v_cod_clie(j)
                 AND det.num_lote = v_num_lote(j)
                 AND det.cod_vent = v_cod_vent(j);
            END IF;
          ELSE
            UPDATE int_solic_conso_detal det
               SET /*det.ind_erro_rech*/ det.ind_erro_sse = ind_error_sse_dupla_cyzone, --1, -- Rechazamos el cuv
                   det.usu_modi     = pscodigousuario,
                   det.fec_modi     = SYSDATE
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_NUMER_UNIDA_CUMPL: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el numero de unidades atendidas
                      en la entidad que almacena las cantidades y productos que
                      puede solicitar la consultora en un rango de periodos, para
                      ello el proceso busca sobre todos los pedidos que la consultora
                      ha pasado en ese rango de periodos y los sumariza.
  Fecha Creacion    : 17/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_bolsa_produ_progr
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2
  ) IS

    CURSOR cupdatebolsa(oidtiposolipais NUMBER) IS
      SELECT psc.clie_oid_clie,
             mp.cod_sap,
             SUM(psp.num_unid_aten) num_unid_aten
        FROM ped_solic_cabec psc,
             ped_solic_posic psp,
             mae_produ mp,
             cra_perio cp,
             seg_perio_corpo spc,
             cyz_produ_progr_dupla ppd,
             (SELECT bpp.clie_oid_clie,
                     bpp.cod_peri_proc,
                     bpp.cod_peri_inic,
                     bpp.cod_peri_fina,
                     bpp.pais_cod_pais,
                     bpp.prdu_cod_prog,
                     bpp.cod_prod
                FROM cyz_bolsa_produ_progr bpp
               WHERE bpp.cod_peri_inic <= pscodigoperiodo
                 AND bpp.cod_peri_fina >= pscodigoperiodo
                 AND bpp.prdu_cod_prog = pscodigoprograma) x
       WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
         AND psp.prod_oid_prod = mp.oid_prod
         AND psc.perd_oid_peri = cp.oid_peri
         AND cp.peri_oid_peri = spc.oid_peri
         AND ppd.pais_cod_pais = pscodigopais
         AND ppd.prdu_cod_prog = pscodigoprograma
         AND psc.fec_fact IS NOT NULL
         AND psc.tspa_oid_tipo_soli_pais = oidtiposolipais
         AND psp.num_unid_aten > 0
         AND psp.espo_oid_esta_posi != 2
         AND ppd.val_codi_vent = psp.val_codi_vent
         AND ppd.cod_peri = spc.cod_peri
         AND x.clie_oid_clie = psc.clie_oid_clie
         AND x.cod_peri_inic <= spc.cod_peri
         AND x.cod_peri_fina >= spc.cod_peri
         AND x.cod_prod = ppd.cod_prod
       GROUP BY psc.clie_oid_clie,
                mp.cod_sap;

    TYPE t_oid_clie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
    TYPE t_cod_prod IS TABLE OF mae_produ.cod_sap%TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;

    v_oid_clie      t_oid_clie;
    v_cod_prod      t_cod_prod;
    v_num_unid_aten t_num_unid_aten;

    lnoidtiposolipais NUMBER;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

  BEGIN

    -- Obtenemos el tipo de solicitud pais de los pedidos
    SELECT b.oid_tipo_soli_pais
      INTO lnoidtiposolipais
      FROM ped_tipo_solic      a,
           ped_tipo_solic_pais b
     WHERE a.oid_tipo_soli = b.tsol_oid_tipo_soli
       AND a.cod_tipo_soli = cod_tipo_soli_oc; -- Pedidos

    OPEN cupdatebolsa(lnoidtiposolipais);
    LOOP
      FETCH cupdatebolsa BULK COLLECT
        INTO v_oid_clie,
             v_cod_prod,
             v_num_unid_aten LIMIT rows;

      IF v_oid_clie.count > 0 THEN
        FORALL j IN v_oid_clie.first .. v_oid_clie.last
          UPDATE cyz_bolsa_produ_progr bpp
             SET bpp.num_unid_aten = v_num_unid_aten(j),
                 bpp.usu_modi      = pscodigousuario,
                 bpp.fec_modi      = SYSDATE
           WHERE bpp.clie_oid_clie = v_oid_clie(j)
             AND bpp.cod_prod = v_cod_prod(j);
      END IF;
      EXIT WHEN cupdatebolsa%NOTFOUND;
    END LOOP;
    CLOSE cupdatebolsa;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_BOLSA_PRODU_PROGR: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que identifica las consultoras que cumplen
                      años en una determinada campaña.
  Fecha Creacion    : 22/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clien_cumpl_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS

    CURSOR cclientes
    (
      fechainicio DATE,
      fechafin    DATE
    ) IS
      SELECT mc.oid_clie,
             mc.cod_clie,
             dad.fec_naci
        FROM mae_clien             mc,
             mae_clien_datos_adici dad
       WHERE mc.oid_clie = dad.clie_oid_clie
         AND dad.fec_naci IS NOT NULL -- Fecha de nacimiento
         AND dad.ind_acti = 1 -- Clientes Activos
         AND ((to_char(fechafin,
                       'MM') >= to_char(fechainicio,
                                          'MM') AND
             to_char(dad.fec_naci,
                       'MMDD') >= to_char(fechainicio,
                                            'MMDD') AND
             to_char(dad.fec_naci,
                       'MMDD') < to_char(fechafin,
                                           'MMDD')) OR
             (to_char(fechafin,
                       'MM') < to_char(fechainicio,
                                         'MM') AND
             (to_char(dad.fec_naci,
                        'MMDD') >= to_char(fechainicio,
                                              'MMDD') OR
             to_char(dad.fec_naci,
                        'MMDD') < to_char(fechafin,
                                             'MMDD'))))
         AND NOT EXISTS (SELECT NULL
                FROM cyz_clien_cumpl_perio y
               WHERE y.clie_oid_clie = mc.oid_clie
                 AND y.cod_peri = pscodigoperiodo);

    TYPE t_oidclie IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_codclie IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_fecnaci IS TABLE OF mae_clien_datos_adici.fec_naci%TYPE;

    v_oidclie t_oidclie;
    v_codclie t_codclie;
    v_fecnaci t_fecnaci;

    lsperiodominimo       VARCHAR2(6);
    lsperiodosiguiente    VARCHAR2(6);
    lnoidperiodo          NUMBER;
    lnoidperiodosiguiente NUMBER;
    lnoidpais             NUMBER;
    lnoidcanal            NUMBER;
    lnoidmarca            NUMBER;
    ldfechainicio         DATE;
    ldfechafin            DATE;

  BEGIN

    -- Obtenemos el valor del periodo siguiente
    lsperiodosiguiente := gen_fn_calcu_perio(pscodigoperiodo,
                                             1);

    -- Obtenemos los OIDs de los periodos
    lnoidmarca            := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal            := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo          := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                        lnoidmarca,
                                                                        lnoidcanal);
    lnoidperiodosiguiente := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lsperiodosiguiente,
                                                                        lnoidmarca,
                                                                        lnoidcanal);

    -- Obtenemos la fechas de inicio y de fin
    SELECT fec_inic INTO ldfechainicio FROM cra_perio WHERE oid_peri = lnoidperiodo;

    SELECT fec_inic INTO ldfechafin FROM cra_perio WHERE oid_peri = lnoidperiodosiguiente;

    -- Abrimos el cursor de insercion de clasificaciones por cliente
    OPEN cclientes(ldfechainicio,
                   ldfechafin);
    LOOP
      FETCH cclientes BULK COLLECT
        INTO v_oidclie,
             v_codclie,
             v_fecnaci LIMIT w_filas;
      IF v_oidclie.count > 0 THEN
        FORALL i IN v_oidclie.first .. v_oidclie.last
          INSERT INTO cyz_clien_cumpl_perio
            (pais_cod_pais,
             cod_peri,
             clie_oid_clie,
             cod_clie,
             fec_naci,
             usu_proc,
             fec_proc)
          VALUES
            (pscodigopais,
             pscodigoperiodo,
             v_oidclie(i),
             v_codclie(i),
             v_fecnaci(i),
             pscodigousuario,
             SYSDATE);
      END IF;
      EXIT WHEN cclientes%NOTFOUND;
    END LOOP;
    CLOSE cclientes;

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza la clasificacion de las consultoras
                      para el despacho de la oferta de cumpleaños para consultoras.
  Fecha Creacion    : 22/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo   : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_clasi_cumpl_clien
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2
  ) IS

    l_count        NUMBER := 0;
    l_mensajeerror VARCHAR2(4000);

    CURSOR cclasificacion IS
      SELECT tcc.oid_tipo_clas,
             mc.oid_clas,
             mtc.oid_tipo_clie,
             msc.oid_subt_clie
        FROM cyz_clasi_progr_dupla cpd,
             mae_tipo_clien        mtc,
             mae_subti_clien       msc,
             mae_tipo_clasi_clien  tcc,
             mae_clasi             mc
       WHERE mtc.oid_tipo_clie = msc.ticl_oid_tipo_clie
         AND tcc.sbti_oid_subt_clie = msc.oid_subt_clie
         AND mc.tccl_oid_tipo_clas = tcc.oid_tipo_clas
         AND mtc.cod_tipo_clie = cpd.cod_tipo_clie
         AND msc.cod_subt_clie = cpd.cod_subt_clie
         AND tcc.cod_tipo_clas = cpd.cod_tipo_clas_prdu
         AND mc.cod_clas = cpd.cod_clas_prdu
         AND cpd.pais_cod_pais = pscodigopais
         AND cpd.prdu_cod_prog = pscodigoprograma;

    TYPE t_oidtipoclasi IS TABLE OF mae_tipo_clasi_clien.oid_tipo_clas%TYPE;
    TYPE t_oidclasi IS TABLE OF mae_clasi.oid_clas%TYPE;
    TYPE t_oidtipoclie IS TABLE OF mae_tipo_clien.oid_tipo_clie%TYPE;
    TYPE t_oidsubtclie IS TABLE OF mae_subti_clien.oid_subt_clie%TYPE;

    v_oidtipoclasi t_oidtipoclasi;
    v_oidclasi     t_oidclasi;
    v_oidtipoclie  t_oidtipoclie;
    v_oidsubtclie  t_oidsubtclie;

    CURSOR cdeleteclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND NOT EXISTS (SELECT NULL
                FROM cyz_clien_cumpl_perio dcp
               WHERE (dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -1) OR
                     dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -2))
                 AND cts.clie_oid_clie = dcp.clie_oid_clie);

    CURSOR cdeleteclasifdespa
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER,
      numperiodos  NUMBER
    ) IS
      SELECT mcc.oid_clie_clas
        FROM mae_clien_clasi      mcc,
             mae_clien_tipo_subti cts
       WHERE mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND mcc.clas_oid_clas = oidclasi
         AND mcc.tccl_oid_tipo_clasi = oidtipoclasi
         AND cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND EXISTS (SELECT NULL
                FROM cyz_despa_produ cdp
               WHERE cdp.clie_oid_clie = cts.clie_oid_clie
                 AND cdp.prdu_cod_prog = pscodigoprograma
                 AND cdp.pais_cod_pais = pscodigopais
                 AND cdp.num_unid_aten > 0
                 AND gen_fn_calcu_perio(cdp.cod_peri,
                                        numperiodos) > pscodigoperiodo);

    TYPE t_oidclieclasi IS TABLE OF mae_clien_clasi.oid_clie_clas%TYPE;
    v_oidclieclasi t_oidclieclasi;

    CURSOR cinsertclasif
    (
      oidtipoclie  NUMBER,
      oidsubtclie  NUMBER,
      oidtipoclasi NUMBER,
      oidclasi     NUMBER
    ) IS
      SELECT cts.oid_clie_tipo_subt
        FROM mae_clien_tipo_subti  cts,
             mae_clien_datos_adici cda
       WHERE cts.ticl_oid_tipo_clie = oidtipoclie
         AND cts.sbti_oid_subt_clie = oidsubtclie
         AND cts.clie_oid_clie = cda.clie_oid_clie
         AND cda.ind_acti = 1 -- Solo consideramos a los clientes activos
         AND EXISTS (SELECT NULL
                FROM cyz_clien_cumpl_perio dcp
               WHERE (dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -1) OR
                     dcp.cod_peri = gen_fn_calcu_perio(pscodigoperiodo,
                                                        -2))
                 AND cts.clie_oid_clie = dcp.clie_oid_clie)
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_clasi mcc
               WHERE mcc.tccl_oid_tipo_clasi = oidtipoclasi
                 AND mcc.clas_oid_clas = oidclasi
                 AND mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt);

    TYPE t_oidclietiposubt IS TABLE OF mae_clien_tipo_subti.oid_clie_tipo_subt%TYPE;
    v_oidclietiposubt t_oidclietiposubt;

    lnoidperiodo  NUMBER;
    lnoidpais     NUMBER;
    lnoidcanal    NUMBER;
    lnoidmarca    NUMBER;
    lnnumperiodos NUMBER;

  BEGIN

    -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(cod_marc_defe);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(cod_cana_defe);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    -- Validamos que el programa este activo y vigente
    SELECT COUNT(*)
      INTO l_count
      FROM cyz_progr_dupla
     WHERE cod_pais = pscodigopais
       AND cod_prog = pscodigoprograma
       AND est_prog = '1'
       AND pscodigoperiodo >= cod_peri_inic
       AND pscodigoperiodo <= cod_peri_fina;

    IF l_count > 0 THEN

      SELECT num_peri_eval
        INTO lnnumperiodos
        FROM cyz_progr_dupla
       WHERE cod_pais = pscodigopais
         AND cod_prog = pscodigoprograma;

      OPEN cclasificacion;
      LOOP
        FETCH cclasificacion BULK COLLECT
          INTO v_oidtipoclasi,
               v_oidclasi,
               v_oidtipoclie,
               v_oidsubtclie LIMIT w_filas;
        IF v_oidtipoclasi.count > 0 THEN
          FOR i IN v_oidtipoclasi.first .. v_oidtipoclasi.last
          LOOP

            -- Abrimos el cursor para la eliminacion de clasificaciones
            OPEN cdeleteclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cdeleteclasif BULK COLLECT
                INTO v_oidclieclasi LIMIT w_filas;
              IF v_oidclieclasi.count > 0 THEN
                FORALL j IN v_oidclieclasi.first .. v_oidclieclasi.last
                  DELETE FROM mae_clien_clasi WHERE oid_clie_clas = v_oidclieclasi(j);
              END IF;
              EXIT WHEN cdeleteclasif%NOTFOUND;
            END LOOP;
            CLOSE cdeleteclasif;

            -- Abrimos el cursor de insercion de clasificaciones por cliente
            OPEN cinsertclasif(v_oidtipoclie(i),
                               v_oidsubtclie(i),
                               v_oidtipoclasi(i),
                               v_oidclasi(i));
            LOOP
              FETCH cinsertclasif BULK COLLECT
                INTO v_oidclietiposubt LIMIT w_filas;
              IF v_oidclietiposubt.count > 0 THEN
                FORALL k IN v_oidclietiposubt.first .. v_oidclietiposubt.last
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
                     v_oidclietiposubt(k),
                     v_oidclasi(i),
                     lnoidperiodo,
                     v_oidtipoclasi(i),
                     trunc(SYSDATE),
                     '0',
                     SYSDATE);
              END IF;
              EXIT WHEN cinsertclasif%NOTFOUND;
            END LOOP;
            CLOSE cinsertclasif;

          -- Eliminamos la clasificacion a aquellos que ya se les ha despachado el producto
          /*
                              OPEN cDeleteClasifDespa(v_oidTipoClie(i),
                                                      v_oidSubtClie(i),
                                                      v_oidTipoClasi(i),
                                                      v_oidClasi(i),
                                                      lnNumPeriodos);
                              LOOP
                                  FETCH cDeleteClasifDespa BULK COLLECT INTO
                                        v_oidClieClasi
                                        LIMIT W_FILAS;
                                  IF v_oidClieClasi.COUNT > 0 THEN
                                      FORALL j IN v_oidClieClasi.FIRST..v_oidClieClasi.LAST
                                          DELETE FROM MAE_CLIEN_CLASI
                                          WHERE OID_CLIE_CLAS = v_oidClieClasi(j);
                                  END IF;
                                  EXIT WHEN cDeleteClasifDespa%NOTFOUND;
                              END LOOP;
                              CLOSE cDeleteClasifDespa;
                              */

          END LOOP;
        END IF;

        EXIT WHEN cclasificacion%NOTFOUND;
      END LOOP;
      CLOSE cclasificacion;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_CLASI_CUMPL_CLIEN: ' || ls_sqlerrm);

  END;

  /*********************/
  /* PROCESOS PARA STO */
  /*********************/

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta a la tabla de
                      consolidado de detalles, si estos han sido solicitados
                      desde la web. Ejecutado desde la validacion de STO
  Fecha Creacion    : 19/01/2009
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPrograma : Codigo de Programa
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_carga_premi_solic_sto
  (
    pscodigopais     IN VARCHAR2,
    pscodigoprograma IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigousuario  IN VARCHAR2,
    pscodtipodocu    IN VARCHAR2,
    psnumeroproceso  IN VARCHAR2

  ) IS

    CURSOR cinsertdetalle IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             cab.num_lote,
             cyz.val_codi_vent,
             'OC' tip_posic,
             cyz.num_unid_soli,
             'D', -- las agregadas por el programa de duplas (premios)
             '0' ind_erro_sse,
             '0' ind_erro_rech,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             cab.fec_soli
        FROM int_solic_conso_cabec cab,
             cyz_solic_produ       cyz,
             cyz_produ_progr_dupla ppd,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = cyz.pais_cod_pais
         AND cab.cod_clie = cyz.cod_clie
         AND cab.cod_peri = cyz.cod_peri
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND cyz.pais_cod_pais = ppd.pais_cod_pais
         AND cyz.prdu_cod_prog = ppd.prdu_cod_prog
         AND cyz.cod_peri = ppd.cod_peri
         AND cyz.val_codi_vent = ppd.val_codi_vent
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cyz.prdu_cod_prog = pscodigoprograma
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.cod_vent = cyz.val_codi_vent)
         AND cyz_pkg_progr_dupla.cyz_fn_devue_numer_unida(cab.cod_pais,
                                                          cab.cod_peri,
                                                          cab.cod_clie,
                                                          cab.num_lote) > 0;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_detal.sta_proc%TYPE;
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
    v_sta_proc      t_sta_proc;
    v_usu_digi      t_usu_digi;
    v_fec_digi      t_fec_digi;
    v_fec_soli      t_fec_soli;

    rows        NATURAL := 1000;
    j           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;

  BEGIN

    OPEN cinsertdetalle;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cinsertdetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_tip_posic,
             v_val_unid_dem,
             v_sta_proc,
             v_ind_erro_sse,
             v_ind_erro_rech,
             v_usu_digi,
             v_fec_digi,
             v_fec_soli LIMIT rows;

      EXIT WHEN v_row_count = cinsertdetalle%ROWCOUNT;
      v_row_count := cinsertdetalle%ROWCOUNT;

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
           v_cod_vent(j),
           v_tip_posic(j),
           v_val_unid_dem(j),
           v_sta_proc(j),
           v_usu_digi(j),
           v_fec_digi(j),
           v_num_lote(j),
           v_ind_erro_sse(j),
           v_ind_erro_rech(j),
           v_fec_soli(j));

    END LOOP;
    CLOSE cinsertdetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'CYZ_PR_CARGA_PREMI_SOLIC_STO: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      para las estrategias de cumpleaños (dupla y consultora)
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle. Ejecutado desde la validacion de STO
  Fecha Creacion    : 25/03/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_cumpl_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  ) IS
    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
            --
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo -- Pedidos del periodo
         AND EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd, -- Para obtener los CUVS
                     cyz_progr_dupla       cpd
               WHERE cpd.cod_pais = ppd.pais_cod_pais
                 AND cpd.cod_prog = ppd.prdu_cod_prog
                 AND cpd.ind_ofer_cump = 1 -- Oferta de cumpleaños
                 AND cpd.est_prog = '1' -- Programa activo
                 AND cpd.cod_peri_inic <= pscodigoperiodo -- Programa Vigente
                 AND cpd.cod_peri_fina >= pscodigoperiodo -- Programa Vigente
                 AND ppd.cod_peri = pscodigoperiodo -- Productos del periodo
                 AND ppd.pais_cod_pais = det.cod_pais
                 AND ppd.cod_peri = det.cod_peri
                 AND ppd.val_codi_vent = det.cod_vent);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_val_unid_dem t_val_unid_dem;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

    lnunidadesdisponibles NUMBER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Obtenemos el valor de unidades disponibles
          SELECT nvl(SUM(bpp.num_unid_tota - bpp.num_unid_aten),
                     0)
            INTO lnunidadesdisponibles
            FROM cyz_bolsa_produ_progr bpp
           WHERE bpp.cod_clie = v_cod_clie(j)
             AND bpp.cod_peri_inic <= pscodigoperiodo -- Bolsa Vigente
             AND bpp.cod_peri_fina >= pscodigoperiodo -- Bolsa Vigente
             AND EXISTS (SELECT NULL
                    FROM cyz_produ_progr_dupla ppd,
                         cyz_progr_dupla       cpd
                   WHERE cpd.cod_pais = ppd.pais_cod_pais
                     AND cpd.cod_prog = ppd.prdu_cod_prog
                     AND ppd.pais_cod_pais = bpp.pais_cod_pais
                     AND ppd.cod_prod = bpp.cod_prod
                     AND ppd.prdu_cod_prog = bpp.prdu_cod_prog
                     AND cpd.ind_ofer_cump = 1 -- Oferta de cumpleaños
                     AND cpd.est_prog = '1' -- Estado Activo
                     AND ppd.cod_peri = pscodigoperiodo
                     AND ppd.val_codi_vent = v_cod_vent(j));

          -- Validamos si existen unidades disponibles
          IF lnunidadesdisponibles > 0 THEN
            -- Actualizamos el valor de las unidades
            -- solo si superan al valor del disponible
            IF v_val_unid_dem(j) > lnunidadesdisponibles THEN
              UPDATE int_solic_conso_detal det
                 SET det.val_unid_dem = lnunidadesdisponibles, -- actualizamos el valor de unidades
                     det.usu_modi     = pscodigousuario,
                     det.fec_modi     = SYSDATE
               WHERE det.cod_pais = v_cod_pais(j)
                 AND det.cod_peri = v_cod_peri(j)
                 AND det.cod_clie = v_cod_clie(j)
                 AND det.num_lote = v_num_lote(j)
                 AND det.cod_vent = v_cod_vent(j);
            END IF;
          ELSE
            UPDATE int_solic_conso_detal det
               SET /*det.ind_erro_rech*/ det.ind_erro_sse = ind_error_sse_dupla_cyzone, --1, -- Rechazamos el cuv
                   det.usu_modi     = pscodigousuario,
                   det.fec_modi     = SYSDATE
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_UNIDA_CUMPL_STO: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_PRODU_PROGR_DUPLA,
                      si existen unidades que exceden el valor de VAL_LIMI_CTRL_VENT
                      estas se actualizan a dicho valor. Esto aplica para todos
                      los productos asociados a los programas activos en el
                      periodo a procesar. Ejecutado desde la validacion de STO
  Fecha Creacion    : 15/04/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais      : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario  : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_solic_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  ) IS

    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem,
             ppd.val_limi_ctrl_vent
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det,
             cyz_produ_progr_dupla ppd,
             cyz_progr_dupla       cpd,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
            --
         AND ppd.pais_cod_pais = det.cod_pais
         AND ppd.cod_peri = det.cod_peri
         AND ppd.val_codi_vent = det.cod_vent
         AND cpd.cod_pais = ppd.pais_cod_pais
         AND cpd.cod_prog = ppd.prdu_cod_prog
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cpd.est_prog = '1' -- Programa activo
         AND cpd.cod_peri_inic <= pscodigoperiodo
         AND cpd.cod_peri_fina >= pscodigoperiodo
         AND ppd.val_limi_ctrl_vent != 0 -- limite de venta mayor a 0
         AND det.val_unid_dem > ppd.val_limi_ctrl_vent -- Exceden el valor maximo
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
    TYPE t_val_limi_ctrl_vent IS TABLE OF cyz_produ_progr_dupla.val_limi_ctrl_vent%TYPE;

    v_cod_pais           t_cod_pais;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_num_lote           t_num_lote;
    v_cod_vent           t_cod_vent;
    v_val_unid_dem       t_val_unid_dem;
    v_val_limi_ctrl_vent t_val_limi_ctrl_vent;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem,
             v_val_limi_ctrl_vent LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FORALL j IN v_cod_pais.first .. v_cod_pais.last
          UPDATE int_solic_conso_detal det
             SET det.val_unid_dem = v_val_limi_ctrl_vent(j)
           WHERE det.cod_pais = v_cod_pais(j)
             AND det.cod_peri = v_cod_peri(j)
             AND det.cod_clie = v_cod_clie(j)
             AND det.num_lote = v_num_lote(j)
             AND det.cod_vent = v_cod_vent(j);
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_UNIDA_SOLIC_STO: ' || ls_sqlerrm);

  END;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el valor de las unidades
                      solicitadas al momento de hacer la carga en base al valor
                      del control de unidades de la tabla CYZ_BOLSA_PRODU_PROGR,
                      si el numero de unidades solicitadas excede al numero de
                      unidades disponibles, se actualiza a dicho valor, en caso
                      no existan unidades disponibles se rechaza la linea de
                      detalle. Ejecutado desde la validacion de STO.
  Fecha Creacion    : 15/04/2010
  Autor             : Carlos Hurtado
  Parametros        :
              psCodigoPais      : Codigo de Pais
              psCodigoPeriodo   : Codigo de Periodo
              pscopdigoUsuario  : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE cyz_pr_actua_unida_bien2_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  ) IS

    CURSOR cupdatedetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.val_unid_dem
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
            -- JOIN STO
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
            --
         AND det.ind_erro_rech = '0' -- Detalle no rechazado
         AND det.ind_erro_sse = '0' -- Detalle no rechazado por SSE
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo -- Pedidos del periodo
         AND EXISTS (SELECT NULL
                FROM cyz_produ_progr_dupla ppd, -- Para obtener los CUVS
                     cyz_progr_dupla       cpd
               WHERE cpd.cod_pais = ppd.pais_cod_pais
                 AND cpd.cod_prog = ppd.prdu_cod_prog
                 AND cpd.ind_paqu_bien = 1 -- Oferta Welcome Pack
                 AND cpd.est_prog = '1' -- Programa activo
                 AND cpd.cod_peri_inic <= pscodigoperiodo -- Programa Vigente
                 AND cpd.cod_peri_fina >= pscodigoperiodo -- Programa Vigente
                 AND ppd.cod_peri = pscodigoperiodo -- Productos del periodo
                 AND ppd.pais_cod_pais = det.cod_pais
                 AND ppd.cod_peri = det.cod_peri
                 AND ppd.val_codi_vent = det.cod_vent);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;

    v_cod_pais     t_cod_pais;
    v_cod_peri     t_cod_peri;
    v_cod_clie     t_cod_clie;
    v_num_lote     t_num_lote;
    v_cod_vent     t_cod_vent;
    v_val_unid_dem t_val_unid_dem;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

    lnunidadesdisponibles NUMBER := 0;

  BEGIN

    OPEN cupdatedetalle;
    LOOP
      FETCH cupdatedetalle BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote,
             v_cod_vent,
             v_val_unid_dem LIMIT rows;

      IF v_cod_pais.count > 0 THEN
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Obtenemos el valor de unidades disponibles
          SELECT nvl(SUM(bpp.num_unid_tota - bpp.num_unid_aten),
                     0)
            INTO lnunidadesdisponibles
            FROM cyz_bolsa_produ_progr bpp
           WHERE bpp.cod_clie = v_cod_clie(j)
             AND bpp.cod_peri_inic <= pscodigoperiodo -- Bolsa Vigente
             AND bpp.cod_peri_fina >= pscodigoperiodo -- Bolsa Vigente
             AND EXISTS (SELECT NULL
                    FROM cyz_produ_progr_dupla ppd,
                         cyz_progr_dupla       cpd
                   WHERE cpd.cod_pais = ppd.pais_cod_pais
                     AND cpd.cod_prog = ppd.prdu_cod_prog
                     AND ppd.pais_cod_pais = bpp.pais_cod_pais
                     AND ppd.cod_prod = bpp.cod_prod
                     AND ppd.prdu_cod_prog = bpp.prdu_cod_prog
                     AND cpd.ind_paqu_bien = 1 -- Oferta Welcome Pack
                     AND cpd.est_prog = '1' -- Estado Activo
                     AND ppd.cod_peri = pscodigoperiodo
                     AND ppd.val_codi_vent = v_cod_vent(j));

          -- Validamos si existen unidades disponibles
          IF lnunidadesdisponibles > 0 THEN
            -- Actualizamos el valor de las unidades
            -- solo si superan al valor del disponible
            IF v_val_unid_dem(j) > lnunidadesdisponibles THEN
              UPDATE int_solic_conso_detal det
                 SET det.val_unid_dem = lnunidadesdisponibles, -- actualizamos el valor de unidades
                     det.usu_modi     = pscodigousuario,
                     det.fec_modi     = SYSDATE
               WHERE det.cod_pais = v_cod_pais(j)
                 AND det.cod_peri = v_cod_peri(j)
                 AND det.cod_clie = v_cod_clie(j)
                 AND det.num_lote = v_num_lote(j)
                 AND det.cod_vent = v_cod_vent(j);
            END IF;
          ELSE
            UPDATE int_solic_conso_detal det
               SET /*det.ind_erro_rech*/ det.ind_erro_sse = ind_error_sse_dupla_cyzone, --1, -- Rechazamos el cuv
                   det.usu_modi     = pscodigousuario,
                   det.fec_modi     = SYSDATE
             WHERE det.cod_pais = v_cod_pais(j)
               AND det.cod_peri = v_cod_peri(j)
               AND det.cod_clie = v_cod_clie(j)
               AND det.num_lote = v_num_lote(j)
               AND det.cod_vent = v_cod_vent(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN cupdatedetalle%NOTFOUND;
    END LOOP;
    CLOSE cupdatedetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR CYZ_PR_ACTUA_UNIDA_BIEN2_STO: ' || ls_sqlerrm);

  END;

END;
/

