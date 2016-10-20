CREATE OR REPLACE PACKAGE zon_pkg_unida_admin IS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

  cod_err_fmt     CONSTANT VARCHAR2(1) := '1';
  cod_err_ubi_nex CONSTANT VARCHAR2(1) := '2';
  cod_err_ter_dup CONSTANT VARCHAR2(1) := '3';
  cod_err_uad_anu CONSTANT VARCHAR2(1) := '4';
  cod_err_uad_cst CONSTANT VARCHAR2(1) := '5';
  cte_ok          CONSTANT VARCHAR2(2) := 'OK';

  cte_tipo_peri_cm CONSTANT VARCHAR2(2) := 'CM';

  cte_opera_alta CONSTANT VARCHAR2(25) := 'AprobarCreaciones';
  cte_opera_modi CONSTANT VARCHAR2(25) := 'AprobarModificaciones';
  cte_opera_reac CONSTANT VARCHAR2(25) := 'AprobarReactivaciones';
  cte_opera_tras CONSTANT VARCHAR2(25) := 'AprobarTrasvases';
  cte_opera_elim CONSTANT VARCHAR2(25) := 'AprobarEliminaciones';

  /***************************************************************************
  Descripcion       : Valida que las regiones no hayan cerrado
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_valid_regio_cierr_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoregion   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Valida que las regiones no hayan facturado
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_valid_regio_factu_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoregion   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                      en el proceso de Actualizar Unidades Administrativas
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_elimi_tabla_unadm;

  /***************************************************************************
  Descripcion       : Realiza validaciones de formato, longitud, unid administrativas
                      anuladas, jerarquias validas.
  Fecha Creacion    : 13/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_prime_valid_unadm
  (
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Realiza validaciones par determinar las ALTAS, MODIF, BAJAS y TRASV
  Fecha Creacion    : 14/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_segun_valid_unadm
  (
    psindicadornivelse IN VARCHAR2,
    pscodigoretorno    OUT VARCHAR2,
    psmensajeretorno   OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : realiza las actualizaciones de Unidades Administrativas
                      ALTAS, MODIF, BAJAS y TRASV
  Fecha Creacion    : 19/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin
  (
    psoperacion      IN VARCHAR2,
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Recalcula errores de eliminacion
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_recal_error_elimi;

  /***************************************************************************
  Descripcion       : Trasvasa consultoras de Errores de eliminacion
                      a nuevo Codigo de Territorio
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_trasv_consu_error_elimi
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Elimina la unidad administrativa del CLiente, cuyo periodo
                      inicio corresponde al periodo recibido como parametro

  Fecha Creacion    : 26/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_elimi_unida_admin
  (
    pnoidcliente IN NUMBER,
    pnoidperiodo IN NUMBER
  );

  /***************************************************************************
  Descripcion       : Verifica que no existe territorio administrativo ya
                      registroa para un territorio en un determinado periodo

  Fecha Creacion    : 27/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_verif_terri_perio
  (
    pnoidterritorio IN NUMBER,
    pnoidperiodo    IN NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Obtiene el oid Periodo

  Fecha Creacion    : 27/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_obtie_perio
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Valida la Modificacion de Territorios a Consultoras

  Fecha Creacion    : 05/08/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_valid_modif_terri_consu
  (
    pscodigopais     IN VARCHAR2,
    pscodigomarca    IN VARCHAR2,
    pscodigocanal    IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigoregiones IN VARCHAR2,
    psUsuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Actualiza la Modificacion de Territorios a Consultoras

  Fecha Creacion    : 07/08/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_actua_modif_terri_consu
  (
    pscodigopais    IN VARCHAR2,
    pscodigomarca   IN VARCHAR2,
    pscodigocanal   IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Relaiza la validacion e insercion en el directorio
  Fecha Creacion    : 09/09/2010
  Autor             : Sergio Buchellli
  parametros
      psCodigoPais          codigo pais,
      psCodigoCargo         codigo cargo
      psCodigoCliente       codigo cliente
      psFechaIngreso        fecha ingreso
      psCodigoRegion        codigo region
      psCodigoZona          codigo zona
      psCodigoUsuario       codigo usuario
      psMsgRetorno          mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_valid_ingre_direc
  (
    pscodigopais    IN VARCHAR2,
    pscodigocargo   IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    psfechaingreso  IN VARCHAR2,
    pscodigoregion  IN VARCHAR2,
    pscodigozona    IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    psfila          IN VARCHAR2,
    psmsgretorno    OUT VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Relaiza la validacion e insercion en el directorio caso nombramiento
    Fecha Creacion    : 13/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais          codigo pais,
        psCodigoCargo         codigo cargo
        psCodigoCliente       codigo cliente
        psFechaIngreso        fecha ingreso
        psCodigoRegion        codigo region
        psCodigoZona          codigo zona
        psCodigoUsuario       codigo usuario
        psMsgRetorno          mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_valid_nombr_direc
  (
    pscodigopais        IN VARCHAR2,
    pscodigocargo       IN VARCHAR2,
    pscodigocliente     IN VARCHAR2,
    psfechanombramiento IN VARCHAR2,
    pscodigoregion      IN VARCHAR2,
    pscodigozona        IN VARCHAR2,
    pscodigousuario     IN VARCHAR2,
    psfila              IN VARCHAR2,
    psmsgretorno        OUT VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Relaiza la rotacion en el directorio
    Fecha Creacion    : 16/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais             codigo pais,
        psCodigoCargo            codigo cargo
        psCodigoCliente          codigo cliente
        psFechaRotacion          fecha rotacion
        psCodigoRegionAnt        codigo region anterior
        psCodigoRegionNuevo      codigo region nuevo
        psCodigoZonaAnt          codigo zona anterior
        psCodigoZonaNuevo        codigo zona nuevo
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_rotac_perso_direc
  (
    pscodigopais        IN VARCHAR2,
    pscodigocargo       IN VARCHAR2,
    pscodigocliente     IN VARCHAR2,
    psfecharotacion     IN VARCHAR2,
    pscodigoregionant   IN VARCHAR2,
    pscodigoregionnuevo IN VARCHAR2,
    pscodigozonaant     IN VARCHAR2,
    pscodigozonanuevo   IN VARCHAR2,
    pscodigousuario     IN VARCHAR2,
    psfila              IN VARCHAR2,
    psmsgretorno        OUT VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Relaiza la licencia en el directorio
    Fecha Creacion    : 20/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais                codigo pais,
        psCodigoCargo               codigo cargo
        psCodigoClienteReemplazado  codigo cliente
        psCodigoClienteReemplazo    codigo cliente reemplazo
        psFechaSalida               fecha salida
        psFechaRegreso              fecha regreso
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_licen_direc
  (
    pscodigopais               IN VARCHAR2,
    pscodigocargoreemplazado   IN VARCHAR2,
    pscodigolicencia           IN VARCHAR2,
    pscodigomotivolicencia     IN VARCHAR2,
    pscodigoclientereemplazado IN VARCHAR2,
    pscodigoclientereemplazo   IN VARCHAR2,
    pscodigocargoreemplazo     IN VARCHAR2,
    pscodigoregionreemplazado  IN VARCHAR2,
    pscodigozonareemplazado    IN VARCHAR2,
    pscodigoregionreemplazo    IN VARCHAR2,
    pscodigozonareemplazo      IN VARCHAR2,
    psfechasalida              IN VARCHAR2,
    psfecharegreso             IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    psfila                     IN VARCHAR2,
    psmsgretorno               OUT VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Realiza la reactivacion en el directorio
    Fecha Creacion    : 20/09/2010
    Fecha Modificacion: 23/09/2013
    Autor             : Sergio Buchelli
                      : Juan Altamirano
    parametros
        psCodigoPais                codigo pais,
        psFechaRegreso              fecha regreso
        psCodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen_direc
  (
    pscodigopais               IN VARCHAR2,
    psfecharegreso             IN VARCHAR2,
    pscodigousuario            IN VARCHAR2

  );

  /***************************************************************************
    Descripcion       : Realiza la reactivacion en el directorio para fox
    Fecha Creacion    : 06/01/2013
    Autor             : Juan Altamirano
    parametros
        psCodigoPais             codigo pais,
        psFechaRegreso           fecha regreso
        psCodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen_direc_fox
  (
    pscodigopais               IN VARCHAR2,
    psfecharegreso             IN VARCHAR2,
    pscodigousuario            IN VARCHAR2

    );

    /***************************************************************************
    Descripcion       : Realiza la asignación de gerentes futuros.
    Fecha Creacion    : 25/11/2013
    Fecha Modificacion:
    Autor             : Juan Altamirano
    parametros
        pscodigoPais             codigo pais,
        psfechaFactu             codigo proceso,
        pscodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_gere_futur
  (
    pscodigoPais               IN VARCHAR2,
    psfechaFactu             IN VARCHAR2,
    pscodigoUsuario            IN VARCHAR2

    );

  /***************************************************************************
    Descripcion       : Realiza la asignación de gerentes futuros para fox.
    Fecha Creacion    : 11/12/2013
    Fecha Modificacion:
    Autor             : Juan Altamirano
    parametros
        pscodigoPais             codigo pais,
        psfechaFactu             codigo proceso,
        pscodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_gere_futur_fox
  (
    pscodigoPais             IN VARCHAR2,
    psfechaFactu             IN VARCHAR2,
    pscodigoUsuario          IN VARCHAR2

    );

    /***************************************************************************
    Descripcion       : Relaiza la retiro en el directorio
    Fecha Creacion    : 20/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais                codigo pais,
        psCodigoCargo               codigo cargo
        psCodigoCliente               codigo cliente
        psFechaRetiro               fecha retiro
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_retir_direc
  (
    pscodigopais    IN VARCHAR2,
    pscodigocargo   IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    pscodigoregion  IN VARCHAR2,
    pscodigozona    IN VARCHAR2,
    psfecharetiro   IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    psfila          IN VARCHAR2,
    psmsgretorno    OUT VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Realiza la Reactivacion de licencias
    Fecha Creacion    : 30/10/2011
    Autor             : Christian Luque
    parametros
        psCodigoPais                codigo pais
        psCodigoMarca               codigo marca
        psCodigoCanal               codigo canal
        psFechaProceso              fecha proceso
        psPeriodoActivo             periodo activo
        psUsuario                   Usuario del proceso
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen
  (
    pscodigopais    IN VARCHAR2,
    pscodigomarca   IN VARCHAR2,
    pscodigocanal   IN VARCHAR2,
    psfechaproceso  IN VARCHAR2,
    psperiodoactivo IN VARCHAR2,
    psUsuario       IN VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Realiza aprobacion de operacion
    Fecha Creacion    : 04/04/2011
    Autor             : Christian Luque
    parametros
        psCodigoPais                 codigo pais
        psCodigoUsuario              codigo usuario
        psCodigoOperacion            codigo operacion
        psCodigoConsultora           codigo consultora
        psCodigoConsultoraReemplazo  codigo consultora reemplazo
        psFechaProceso               fecha proceso
        psCodigoSubgerencia          codigo subgerencia
        psCodigoRegion               codigo region
        psCodigoZona                 codigo zona
        psCodigoSubgerenciaActual    codigo subgerencia
        psCodigoRegionActual         codigo region
        psCodigoZonaActual           codigo zona
        psError                      mensaje Error

  ***************************************************************************/
  PROCEDURE zon_pr_aprob_opera
  (
    pscodigopais                IN VARCHAR2,
    pscodigousuario             IN VARCHAR2,
    pscodigoconsultora          IN VARCHAR2,
    pscodigoconsultorareemplazo IN VARCHAR2,
    pscodigocargo               IN VARCHAR2,
    pscodigooperacion           IN VARCHAR2,
    psfechaproceso              IN VARCHAR2,
    pscodigosubgerencia         IN VARCHAR2,
    pscodigoregion              IN VARCHAR2,
    pscodigozona                IN VARCHAR2,
    pscodigosubgerenciaactual   IN VARCHAR2,
    pscodigoregionactual        IN VARCHAR2,
    pscodigozonaactual          IN VARCHAR2,
    psfechasalida               IN VARCHAR2,
    psfecharegreso              IN VARCHAR2,
    pscodigolicencia            IN VARCHAR2,
    pserror                     OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                      determinado periodo
  Fecha Creacion    : 01/06/2011
  Autor             :
  ***************************************************************************/
  FUNCTION zon_fn_devue_respo_unida_histo
  (
    pscodresponsable OUT VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pnidpais         NUMBER,
    pscodsubgerencia VARCHAR2,
    pscodregion      VARCHAR2,
    pscodzona        VARCHAR2,
    pscodseccion     VARCHAR2 := NULL
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Valida la Creacion de Territorios de Demanda Anticipada

  Fecha Creacion    : 30/05/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_valid_crear_terri_deman
  (
    pscodigopais     IN VARCHAR2,
    pscodigoregiones IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procesa la Creacion de Territorios de Demanda Anticipada

  Fecha Creacion    : 30/05/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_proce_crear_terri_deman(pscodigopais IN VARCHAR2);

  /***************************************************************************
  Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                      determinado periodo
  Fecha Creacion    : 08/06/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_devue_respo_unida_admin
  (
    pscodigoperiodo  VARCHAR2,
    pscodigopais     VARCHAR2,
    pscodsubgerencia VARCHAR2,
    pscodregion      VARCHAR2,
    pscodzona        VARCHAR2,
    pscodseccion     VARCHAR2 := NULL
  ) RETURN VARCHAR2;

/**********************************************************************************
Descripcion       : Valida la zona y region para la asignacion
Fecha Creacion    : 20/03/2013
Autor             : Aurelio Oviedo
**********************************************************************************/
   PROCEDURE ZON_PR_ASIGN_REGI_ZON(psCodigoPais VARCHAR2,
                                  psCodigoCliente VARCHAR2,
                                  pstipo varchar2,
                                  pscodregi varchar2,
                                  pscodzona varchar2,
                                  psusuario VARCHAR2,
                                  psFechaProceso VARCHAR2,
                                  psCodigoPeriodoActual VARCHAR2,
                                  psResultado OUT VARCHAR2);

/***************************************************************************
  Descripcion       : Valida tipo Cargo Futuro.
  Fecha Creacion    : 22/11/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  FUNCTION ZON_FN_VALID_FECHA_INGRE
  (psTipoCargo  VARCHAR2,
   psCodigoPais   VARCHAR2,
   psFechaIngreso VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2
  )
  RETURN VARCHAR2;

 /***************************************************************************
  Descripcion       : Valida el cruce de fechas gerentes.
  Fecha Creacion    : 27/11/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  FUNCTION ZON_FN_VALID_CRUCE_FECHA_GEREN
  (psCodigoPais   VARCHAR2,
   psFechaIngresoIncio VARCHAR2,
   psFechaIngresoHasta VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2,
   psTipoCargo VARCHAR2,
   psTipoOperacion VARCHAR2
  )
  RETURN VARCHAR2;

/***************************************************************************
  Descripcion       : Valida el cruce de fechas gerentes.
  Fecha Creacion    : 03/12/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  FUNCTION ZON_FN_VALID_FECHA_INGRE_FOX
  (psTipoCargo  VARCHAR2,
   psCodigoPais   VARCHAR2,
   psFechaIngreso VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2
  )
  RETURN VARCHAR2;


  /***************************************************************************
  Descripcion : Procedure que Procesa Carga de Regiones
  Fecha Creacion : 11/12/2013
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psUsuario : Codigo de Usuario
  ***************************************************************************/
   PROCEDURE ZON_PR_CARGA_REGIO (
      psCodigoPais VARCHAR2,
      psUsuario VARCHAR2
   );

   /***************************************************************************
  Descripcion : Procedure que Procesa Carga de ZONAS
  Fecha Creacion : 11/12/2013
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psUsuario : Codigo de Usuario
  ***************************************************************************/
   PROCEDURE ZON_PR_CARGA_ZONAS (
      psCodigoPais VARCHAR2,
      psUsuario VARCHAR2
   );

  /***************************************************************************
  Descripcion : Procedure que Procesa Carga de CAMPAÑAS
  Fecha Creacion : 11/12/2013
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psUsuario : Codigo de Usuario
  ***************************************************************************/
   PROCEDURE ZON_PR_CARGA_CAMPA (
      psCodigoPais VARCHAR2,
      psUsuario VARCHAR2
   );

  /***************************************************************************
  Descripcion : Procedure que Procesa Carga de CONTROL DE FACTURACION
  Fecha Creacion : 11/12/2013
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psUsuario : Codigo de Usuario
  ***************************************************************************/
   PROCEDURE ZON_PR_CARGA_CNTRL_FACTU (
      psCodigoPais VARCHAR2,
      psUsuario VARCHAR2
   );

   /***************************************************************************
  Descripcion : Procedure que Procesa Carga de CONSULTORAS
  Fecha Creacion : 11/12/2013
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psUsuario : Codigo de Usuario
  ***************************************************************************/
   PROCEDURE ZON_PR_CARGA_CONSU (
      psCodigoPais VARCHAR2,
      psUsuario VARCHAR2
   );

   /******************************************************************************
Descripcion       : Proceso de Actualización de Gerentes Region y Zona SSICC
Fecha Modificacion: 17-01-2014
Parametros:
psCodigoPais     : Codigo de Pais
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ZON_ACTUA_GEREN_SSICC
  (psCodigoPais       VARCHAR2
  );

/******************************************************************************
Descripcion       : Proceso de Actualización de Gerentes Region y Zona
Fecha Modificacion: 17-01-2014
Parametros:
psCodigoPais     : Codigo de Pais
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ZON_ACTUA_GEREN_FOX
  (psCodigoPais       VARCHAR2
  ) ;

/**********************************************************************************
    Descripcion       : Realizamos las diferentes validaciones para ver si se puede
                        efectuar la asignacion de la lider a una respectiva seccion
    Fecha Creacion     : 24/02/2011
    Fecha Modificacion : 24/03/2014
    Autor             : Carlos Diaz Valverde, Juan Altamirano
    **********************************************************************************/
     FUNCTION ZON_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoCliente VARCHAR2,
                                           psIndicadorReingreso VARCHAR2,
                                           psIndicadorNoValidaUnicoLider VARCHAR2,
                                           lnNumeroActivasFinalesZona NUMBER,
                                           lnPromedioActFinalesSeccion NUMBER,
                                           psCodigoPeriodoActual VARCHAR2,
                                           psCodigoPeriodoValAsiLid VARCHAR2,
                                           pnOidSeccion NUMBER,
                                           psUnidadAdm VARCHAR2,
                                           pnOidPeriodoAnt NUMBER,
                                           psCodigoPeriodoAsigLiderSgte VARCHAR2,
                                           pscodsubgerencia varchar2,
                                           pscodregi varchar2,
                                           pscodzona varchar2,
                                           pscodsecc varchar2,
                                           psRealizarValidaciones varchar2,
                                           psIndicadorWEB varchar2
                                          )
   RETURN VARCHAR2;

  /******************************************************************************
  Descripcion       : Proceso que Modifica Territorio a Consultoras de forma masiva
  Fecha Modificacion: 21/08/2014
  Parametros:
        psCodigoPais     : Codigo de Pais
        psCampanaProceso : Campaña de Proceso
        psCodigoUsuario  : Codigo de Usuario
  Autor: Carlos Bazalar
  *******************************************************************************/
  PROCEDURE ZON_PR_ACTUA_UADMI (
    psCodigoPais       VARCHAR2,
    psCampanaProceso   VARCHAR2,
    psCodigoUsuario    VARCHAR2
  );

	/***************************************************************************
    Descripcion       : Valida la carga unidades geograficas
    Fecha Creacion    : 13/01/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE ZON_PR_VALIDAR_TERRI_UNGEO(psCodigoUsuario VARCHAR2);

    /***************************************************************************
    Descripcion       : Realiza la carga unidades geograficas
    Fecha Creacion    : 13/01/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE ZON_PR_CARGAR_TERRI_UNGEO(psCodigoUsuario VARCHAR2);
    
  /***************************************************************************
  Descripcion       : Valida que las Zonas no hayan cerrado
  Fecha Creacion    : 19/11/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE zon_pr_valid_zonas_cierr_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigozona     VARCHAR2,
    pscodigoretorno  OUT VARCHAR2    
  );
  
  /***************************************************************************
  Descripcion       : Valida que las Zonas no hayan facturado
  Fecha Creacion    : 19/11/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE zon_pr_valid_zonas_factu_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigozona     VARCHAR2,
    pscodigoretorno  OUT VARCHAR2   
  );

END zon_pkg_unida_admin;
/
CREATE OR REPLACE PACKAGE BODY zon_pkg_unida_admin AS

  gntmpoidpais    NUMBER;
  gntmpoidmarc    NUMBER;
  gntmpoidcana    NUMBER;
  gntmpoidperi    NUMBER;
  gntmpoidperiant NUMBER;
  gntmpoidperides NUMBER;

  /***************************************************************************
  Descripcion       : Valida que las regiones no hayan cerrado
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_valid_regio_cierr_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoregion   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    lncerr      NUMBER;
  BEGIN

    SELECT COUNT(*)
      INTO lncerr
      FROM zon_regio       reg,          
           fac_contr_cierr ccr
     WHERE reg.oid_regi = ccr.zorg_oid_regi       
       AND ccr.tcie_oid_tipo_cier = 1
       AND reg.cod_regi = pscodigoregion
       AND ccr.perd_oid_peri =
           (SELECT cper.oid_peri
              FROM cra_perio cper
             WHERE cper.pais_oid_pais =
                   (SELECT oid_pais FROM seg_pais WHERE cod_pais = pscodigopais)
               AND cper.marc_oid_marc =
                   (SELECT oid_marc FROM seg_marca WHERE cod_marc = pscodigomarca)
               AND cper.cana_oid_cana =
                   (SELECT oid_cana FROM seg_canal WHERE cod_cana = pscodigocanal)
               AND cper.peri_oid_peri =
                   (SELECT oid_peri
                      FROM seg_perio_corpo
                     WHERE cod_peri = pscodigoperiodo
                       AND tipe_oid_tipo_peri =
                           (SELECT oid_tipo_peri
                              FROM seg_tipo_perio
                             WHERE cod_tipo_peri = cte_tipo_peri_cm)));
    IF lncerr > 0 THEN
      pscodigoretorno := '1';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_REGIO_CIERR_UNADM: ' || ls_sqlerrm);
  END zon_pr_valid_regio_cierr_unadm;

  /***************************************************************************
  Descripcion       : Valida que las regiones no hayan facturado
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_valid_regio_factu_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoregion   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    lnfact    NUMBER;
    lnoidperi NUMBER;
  BEGIN

    SELECT cper.oid_peri
      INTO lnoidperi
      FROM cra_perio cper
     WHERE cper.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = pscodigopais)
       AND cper.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = pscodigomarca)
       AND cper.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = pscodigocanal)
       AND cper.peri_oid_peri =
           (SELECT oid_peri
              FROM seg_perio_corpo
             WHERE cod_peri = pscodigoperiodo
               AND tipe_oid_tipo_peri =
                   (SELECT oid_tipo_peri FROM seg_tipo_perio WHERE cod_tipo_peri = cte_tipo_peri_cm));

    SELECT COUNT(*)
      INTO lnfact
      FROM ped_solic_cabec     cab,
           zon_zona            zon,
           zon_regio           reg,
           ped_tipo_solic_pais tslp,
           ped_tipo_solic      tsol
     WHERE cab.zzon_oid_zona = zon.oid_zona
       AND zon.zorg_oid_regi = reg.oid_regi
       AND reg.cod_regi = pscodigoregion
       AND cab.perd_oid_peri = lnoidperi
       AND cab.ind_oc = 1
       AND cab.tspa_oid_tipo_soli_pais = tslp.oid_tipo_soli_pais
       AND tslp.tsol_oid_tipo_soli = tsol.oid_tipo_soli
       AND tsol.ind_anul = 0
       AND tsol.ind_devo = 0
       AND cab.fec_fact IS NOT NULL;

    IF lnfact > 0 THEN
      pscodigoretorno := '1';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_REGIO_FACTU_UNADM: ' || ls_sqlerrm);
  END zon_pr_valid_regio_factu_unadm;

  /***************************************************************************
  Descripcion       : Inicializacion de globales
  Fecha Creacion    : 21/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_inici_globa IS
    varerror VARCHAR2(2) := 'x1';

  BEGIN
    SELECT oid_pais
      INTO gntmpoidpais
      FROM seg_pais
     WHERE cod_pais = (SELECT cod_pais
                         FROM zon_histo_valid_unadm
                        WHERE fec_proc IS NULL
                          AND rownum = 1);
    varerror := 'x2';
    SELECT oid_marc
      INTO gntmpoidmarc
      FROM seg_marca
     WHERE cod_marc = (SELECT cod_marc
                         FROM zon_histo_valid_unadm
                        WHERE fec_proc IS NULL
                          AND rownum = 1);
    varerror := 'x3';
    SELECT oid_cana
      INTO gntmpoidcana
      FROM seg_canal
     WHERE cod_cana = (SELECT cod_cana
                         FROM zon_histo_valid_unadm
                        WHERE fec_proc IS NULL
                          AND rownum = 1);
    varerror := 'x4';
    SELECT cper.oid_peri
      INTO gntmpoidperi
      FROM cra_perio       cper,
           seg_perio_corpo spco
     WHERE cper.peri_oid_peri = spco.oid_peri
       AND spco.tipe_oid_tipo_peri =
           (SELECT oid_tipo_peri FROM seg_tipo_perio WHERE cod_tipo_peri = cte_tipo_peri_cm)
       AND spco.cod_peri =
           (SELECT DISTINCT cod_peri FROM zon_histo_valid_unadm WHERE fec_proc IS NULL)
       AND cper.pais_oid_pais = gntmpoidpais
       AND cper.marc_oid_marc = gntmpoidmarc
       AND cper.cana_oid_cana = gntmpoidcana;
    varerror := 'x5';
    SELECT cper.oid_peri
      INTO gntmpoidperiant
      FROM cra_perio       cper,
           seg_perio_corpo spco
     WHERE cper.peri_oid_peri = spco.oid_peri
       AND spco.tipe_oid_tipo_peri =
           (SELECT oid_tipo_peri FROM seg_tipo_perio WHERE cod_tipo_peri = cte_tipo_peri_cm)
       AND spco.cod_peri = gen_fn_calcu_perio((SELECT DISTINCT cod_peri
                                                FROM zon_histo_valid_unadm
                                               WHERE fec_proc IS NULL),
                                              -1)
       AND cper.pais_oid_pais = gntmpoidpais
       AND cper.marc_oid_marc = gntmpoidmarc
       AND cper.cana_oid_cana = gntmpoidcana;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_INICI_GLOBA: ' || varerror || '-' || ls_sqlerrm);
  END zon_pr_inici_globa;

  /***************************************************************************
  Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                      en el proceso de Actualizar Unidades Administrativas
  Fecha Creacion    : 09/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_elimi_tabla_unadm IS
  BEGIN
    DELETE FROM zon_tmp_unadm_error;
    DELETE FROM zon_tmp_unida_admin;
    DELETE FROM zon_histo_valid_unadm hvua WHERE hvua.fec_proc IS NULL;
    DELETE FROM zon_histo_error_elimi_unadm heeu WHERE heeu.fec_proc IS NULL;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ELIMI_TABLA_UNADM: ' || ls_sqlerrm);
  END zon_pr_elimi_tabla_unadm;

  /***************************************************************************
  Descripcion       : Realiza validaciones de formato, longitud, unid administrativas
                      anuladas, jerarquias validas.
  Fecha Creacion    : 13/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_prime_valid_unadm
  (
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typtabstring IS TABLE OF VARCHAR2(100);
    tab1           typtabstring;
    tab2           typtabstring;
    tab3           typtabstring;
    lntotalerrores NUMBER;
    CURSOR cvalfmtsgv IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_subg_vent
        FROM zon_tmp_unida_admin tuad
       WHERE length(TRIM(cod_subg_vent)) != 2
          OR REPLACE(translate(cod_subg_vent,
                               '0123456789',
                               '9999999999'),
                     '9') IS NOT NULL;
    CURSOR cvalfmtreg IS
      SELECT DISTINCT tuad.nom_arch nom_arch,
                      tuad.cod_regi uni_admi
        FROM zon_tmp_unida_admin tuad
       WHERE length(TRIM(cod_regi)) != 2
          OR REPLACE(translate(cod_regi,
                               '0123456789',
                               '9999999999'),
                     '9') IS NOT NULL;
    CURSOR cvalfmtzon IS
      SELECT DISTINCT tuad.nom_arch nom_arch,
                      tuad.cod_zona uni_admi
        FROM zon_tmp_unida_admin tuad
       WHERE length(TRIM(cod_zona)) != 4
          OR REPLACE(translate(cod_zona,
                               '0123456789',
                               '9999999999'),
                     '9') IS NOT NULL;
    CURSOR cvalfmtsec IS
      SELECT DISTINCT tuad.nom_arch nom_arch,
                      tuad.cod_zona uni_admi1,
                      tuad.cod_secc uni_admi2
        FROM zon_tmp_unida_admin tuad
       WHERE length(TRIM(cod_secc)) != 1
          OR REPLACE(translate(upper(cod_secc),
                               'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
                               'AAAAAAAAAAAAAAAAAAAAAAAAAA'),
                     'A') IS NOT NULL;
    CURSOR cvalfmtubi IS
      SELECT DISTINCT tuad.nom_arch nom_arch,
                      tuad.cod_ubig uni_admi
        FROM zon_tmp_unida_admin tuad
       WHERE MOD(length(TRIM(cod_ubig)),
                 6) > 0;

    CURSOR cvalexiubi IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_ubig
        FROM (SELECT * FROM zon_tmp_unida_admin WHERE cod_ubig IS NOT NULL) tuad
       WHERE NOT EXISTS (SELECT 1
                FROM zon_valor_estru_geopo
               WHERE orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 || orde_7 ||
                     orde_8 || orde_9 = tuad.cod_ubig);

    CURSOR cvaldupter IS
      SELECT tuad.nom_arch,
             tuad.cod_terr
        FROM zon_tmp_unida_admin tuad
       WHERE (SELECT COUNT(tuad2.cod_terr)
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_terr = tuad.cod_terr) > 1
       ORDER BY tuad.cod_terr;

    CURSOR cvalanureg IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_regi
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS (SELECT 1
                FROM zon_regio       reg,
                     cra_perio       cper,
                     seg_perio_corpo sper
               WHERE reg.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND reg.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND reg.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND reg.cod_regi = tuad.cod_regi
                 AND cper.oid_peri = reg.perd_oid_peri_inic
                 AND sper.oid_peri = cper.peri_oid_peri
                 AND sper.cod_peri =
                     (SELECT MAX(sper1.cod_peri)
                        FROM zon_regio       reg1,
                             cra_perio       cper1,
                             seg_perio_corpo sper1
                       WHERE reg1.pais_oid_pais =
                             (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                         AND reg1.marc_oid_marc =
                             (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                         AND reg1.cana_oid_cana =
                             (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                         AND reg1.cod_regi = tuad.cod_regi
                         AND cper1.oid_peri = reg1.perd_oid_peri_inic
                         AND sper1.oid_peri = cper1.peri_oid_peri
                         AND sper1.cod_peri <= tuad.cod_peri)
                 AND reg.ind_acti = 0
                 AND reg.ind_borr = 1);

    CURSOR cvalanuzon IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_zona
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS (SELECT 1
                FROM zon_zona        zon,
                     cra_perio       cper,
                     seg_perio_corpo sper
               WHERE zon.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND zon.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND zon.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND zon.cod_zona = tuad.cod_zona
                 AND cper.oid_peri = zon.perd_oid_peri_inic
                 AND sper.oid_peri = cper.peri_oid_peri
                 AND sper.cod_peri =
                     (SELECT MAX(sper1.cod_peri)
                        FROM zon_zona        zon1,
                             cra_perio       cper1,
                             seg_perio_corpo sper1
                       WHERE zon1.pais_oid_pais =
                             (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                         AND zon1.marc_oid_marc =
                             (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                         AND zon1.cana_oid_cana =
                             (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                         AND zon1.cod_zona = tuad.cod_zona
                         AND cper1.oid_peri = zon1.perd_oid_peri_inic
                         AND sper1.oid_peri = cper1.peri_oid_peri
                         AND sper1.cod_peri <= tuad.cod_peri)
                 AND zon.ind_acti = 0
                 AND zon.ind_borr = 1);

    CURSOR cvalanusec IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_zona,
                      tuad.cod_secc
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS
       (SELECT 1
                FROM zon_secci       sec,
                     cra_perio       cper,
                     seg_perio_corpo sper
               WHERE (SELECT cod_zona FROM zon_zona WHERE oid_zona = sec.zzon_oid_zona) =
                     tuad.cod_zona
                 AND sec.cod_secc = tuad.cod_secc
                 AND cper.oid_peri = sec.perd_oid_peri_inic
                 AND sper.oid_peri = cper.peri_oid_peri
                 AND sper.cod_peri = (SELECT MAX(sper1.cod_peri)
                                        FROM zon_secci       sec1,
                                             cra_perio       cper1,
                                             seg_perio_corpo sper1
                                       WHERE (SELECT cod_zona
                                                FROM zon_zona

                                               WHERE oid_zona = sec1.zzon_oid_zona) = tuad.cod_zona
                                         AND sec1.cod_secc = tuad.cod_secc
                                         AND cper1.oid_peri = sec1.perd_oid_peri_inic
                                         AND sper1.oid_peri = cper1.peri_oid_peri
                                         AND sper1.cod_peri <= tuad.cod_peri)
                 AND sec.ind_acti = 0
                 AND sec.ind_borr = 1);

    CURSOR cvalanuter IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_terr
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS (SELECT 1
                FROM zon_terri ter
               WHERE (SELECT cod_pais FROM seg_pais WHERE oid_pais = ter.pais_oid_pais) =
                     tuad.cod_pais
                 AND ter.cod_terr = tuad.cod_terr
                 AND ter.ind_borr = 1);

    CURSOR cvalcstzon IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_regi,
                      tuad.cod_zona
        FROM zon_tmp_unida_admin tuad
       WHERE (SELECT COUNT(DISTINCT tuad2.cod_regi)
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_zona = tuad.cod_zona) > 1;

    CURSOR cvalcstreg IS
      SELECT DISTINCT tuad.nom_arch,
                      tuad.cod_subg_vent,
                      tuad.cod_regi
        FROM zon_tmp_unida_admin tuad
       WHERE (SELECT COUNT(DISTINCT tuad2.cod_subg_vent)
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_regi = tuad.cod_regi) > 1;

  BEGIN
    /*
    * Longitudes y tipo de caracter
    */
    -- Subgerencias de ventas
    OPEN cvalfmtsgv;
    LOOP
      FETCH cvalfmtsgv BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           tab2(i),
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
           cod_err_fmt);

    END LOOP;
    CLOSE cvalfmtsgv;

    -- Region
    OPEN cvalfmtreg;
    LOOP
      FETCH cvalfmtreg BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           tab2(i),
           NULL,
           NULL,
           NULL,
           NULL,
           cod_err_fmt);

    END LOOP;
    CLOSE cvalfmtreg;

    -- Zona
    OPEN cvalfmtzon;
    LOOP
      FETCH cvalfmtzon BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           tab2(i),
           NULL,
           NULL,
           NULL,
           cod_err_fmt);

    END LOOP;
    CLOSE cvalfmtzon;

    -- Zona
    OPEN cvalfmtsec;
    LOOP
      FETCH cvalfmtsec BULK COLLECT
        INTO tab1,
             tab2,
             tab3 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           tab2(i),
           tab3(i),
           NULL,
           NULL,
           cod_err_fmt);

    END LOOP;
    CLOSE cvalfmtsec;

    -- Ubigeo
    OPEN cvalfmtubi;
    LOOP
      FETCH cvalfmtubi BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
           tab2(i),
           cod_err_fmt);

    END LOOP;
    CLOSE cvalfmtubi;

    /*
    * Existencia de ubigeo
    */
    OPEN cvalexiubi;
    LOOP
      FETCH cvalexiubi BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           NULL,
           NULL,
           NULL,
           tab2(i),
           cod_err_ubi_nex);

    END LOOP;
    CLOSE cvalexiubi;

    /*
    * Territorios Duplicados
    */
    OPEN cvaldupter;
    LOOP
      FETCH cvaldupter BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           NULL,
           NULL,
           tab2(i),
           NULL,
           cod_err_ter_dup);
    END LOOP;
    CLOSE cvaldupter;

    /*
    * UniAdm Anuladas
    */
    -- Region
    OPEN cvalanureg;
    LOOP
      FETCH cvalanureg BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           tab2(i),
           NULL,
           NULL,
           NULL,
           NULL,
           cod_err_uad_anu);
    END LOOP;
    CLOSE cvalanureg;
    -- Zona
    OPEN cvalanuzon;
    LOOP
      FETCH cvalanuzon BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           tab2(i),
           NULL,
           NULL,
           NULL,
           cod_err_uad_anu);
    END LOOP;
    CLOSE cvalanuzon;
    /*
    -- Seccion
    OPEN cValAnuSec;
    LOOP
        FETCH cValAnuSec BULK COLLECT INTO tab1, tab2, tab3 LIMIT W_FILAS;
        EXIT WHEN tab1.COUNT = 0;
        FORALL i IN 1..tab1.COUNT
            INSERT INTO ZON_TMP_UNADM_ERROR VALUES (tab1(i), NULL, NULL, tab2(i), tab3(i), NULL, NULL, COD_ERR_UAD_ANU);
    END LOOP;
    CLOSE cValAnuSec;
    */
    -- Territorio
    OPEN cvalanuter;
    LOOP
      FETCH cvalanuter BULK COLLECT
        INTO tab1,
             tab2 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           NULL,
           NULL,
           NULL,
           tab2(i),
           NULL,
           cod_err_uad_anu);
    END LOOP;
    CLOSE cvalanuter;

    /*
    * Validaciones de consistencia de jerarquia
    */
    -- Zona
    OPEN cvalcstzon;
    LOOP
      FETCH cvalcstzon BULK COLLECT
        INTO tab1,
             tab2,
             tab3 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           NULL,
           tab2(i),
           tab3(i),
           NULL,
           NULL,
           NULL,
           cod_err_uad_cst);
    END LOOP;
    CLOSE cvalcstzon;
    -- Region
    OPEN cvalcstreg;
    LOOP
      FETCH cvalcstreg BULK COLLECT
        INTO tab1,
             tab2,
             tab3 LIMIT w_filas;
      EXIT WHEN tab1.count = 0;
      FORALL i IN 1 .. tab1.count
        INSERT INTO zon_tmp_unadm_error
        VALUES
          (tab1(i),
           tab2(i),
           tab3(i),
           NULL,
           NULL,
           NULL,
           NULL,
           cod_err_uad_cst);
    END LOOP;
    CLOSE cvalcstreg;

    SELECT COUNT(*) INTO lntotalerrores FROM zon_tmp_unadm_error;

    IF lntotalerrores > 0 THEN
      pscodigoretorno  := '1';
      psmensajeretorno := '';
    ELSE
      UPDATE zon_tmp_unida_admin SET est_vali = cte_ok;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_PRIME_VALID_UNADM: ' || ls_sqlerrm);
  END zon_pr_prime_valid_unadm;

  /***************************************************************************
  Descripcion       : Realiza validaciones par determinar las ALTAS, MODIF, BAJAS y TRASV
  Fecha Creacion    : 14/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_segun_valid_unadm
  (
    psindicadornivelse IN VARCHAR2,
    pscodigoretorno    OUT VARCHAR2,
    psmensajeretorno   OUT VARCHAR2
  ) IS
    TYPE typtabhistvali IS TABLE OF zon_histo_valid_unadm%ROWTYPE;
    tabhistvali typtabhistvali;

    CURSOR caltzon IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      NULL cod_secc,
                      NULL cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'A' cod_oper,
                      'Z' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_tmp_unida_admin tuad
       WHERE NOT EXISTS (SELECT 1
                FROM zon_zona zon
               WHERE zon.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND zon.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND zon.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND zon.cod_zona = tuad.cod_zona)
         AND tuad.cod_zona IS NOT NULL;

    CURSOR caltsecnuezon IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      NULL cod_terr,
                      'ZONA ' || tuad.cod_zona || ' SECCION ' || tuad.cod_secc des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'A' cod_oper,
                      'S' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_tmp_unida_admin tuad
       WHERE NOT EXISTS (SELECT 1
                FROM zon_zona zon
               WHERE zon.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND zon.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND zon.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND zon.cod_zona = tuad.cod_zona)
         AND tuad.cod_zona IS NOT NULL;

    CURSOR caltsecexizon IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      NULL cod_terr,
                      'ZONA ' || tuad.cod_zona || ' SECCION ' || tuad.cod_secc des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'A' cod_oper,
                      'S' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS (SELECT 1
                FROM zon_zona zon
               WHERE zon.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND zon.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND zon.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND zon.cod_zona = tuad.cod_zona)
         AND NOT EXISTS
       (SELECT 1
                FROM zon_secci sec
               WHERE (SELECT zon1.cod_zona FROM zon_zona zon1 WHERE zon1.oid_zona = sec.zzon_oid_zona) =
                     tuad.cod_zona
                 AND sec.cod_secc = tuad.cod_secc);

    CURSOR caltter IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      tuad.cod_terr,
                      'TERRITORIO ' || tuad.cod_terr des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      tuad.cod_ubig,
                      tuad.cod_nse1,
                      tuad.cod_nse2,
                      tuad.cod_nse3,
                      'A' cod_oper,
                      'T' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM (SELECT * FROM zon_tmp_unida_admin WHERE cod_terr IS NOT NULL) tuad
       WHERE NOT EXISTS (SELECT 1
                FROM zon_terri ter
               WHERE (SELECT cod_pais FROM seg_pais pai WHERE oid_pais = ter.pais_oid_pais) =
                     tuad.cod_pais
                 AND ter.cod_terr = tuad.cod_terr);

    CURSOR cmodter IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      tuad.cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      tuad.cod_ubig,
                      tuad.cod_nse1,
                      tuad.cod_nse2,
                      tuad.cod_nse3,
                      'M' cod_oper,
                      'T' cod_nive,
                      NULL cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM (SELECT * FROM zon_tmp_unida_admin WHERE cod_terr IS NOT NULL) tuad
       WHERE EXISTS (SELECT 1
                FROM zon_terri ter
               WHERE (SELECT cod_pais FROM seg_pais pai WHERE oid_pais = ter.pais_oid_pais) =
                     tuad.cod_pais
                 AND ter.cod_terr = tuad.cod_terr
                 AND ((SELECT orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 ||
                              orde_7 || orde_8 || orde_9
                         FROM zon_valor_estru_geopo veg
                        WHERE veg.oid_valo_estr_geop = ter.vepo_oid_valo_estr_geop) !=
                     tuad.cod_ubig));
    CURSOR creasec IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      NULL cod_terr,
                      'ZONA ' || tuad.cod_zona || ' SECCION ' || tuad.cod_secc des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'R' cod_oper,
                      'S' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_tmp_unida_admin tuad
       WHERE EXISTS (SELECT 1
                FROM zon_zona zon
               WHERE zon.pais_oid_pais =
                     (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
                 AND zon.marc_oid_marc =
                     (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
                 AND zon.cana_oid_cana =
                     (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
                 AND zon.cod_zona = tuad.cod_zona)
         AND EXISTS
       (SELECT 1
                FROM zon_secci sec
               WHERE (SELECT zon1.cod_zona FROM zon_zona zon1 WHERE zon1.oid_zona = sec.zzon_oid_zona) =
                     tuad.cod_zona
                 AND sec.cod_secc = tuad.cod_secc
                 AND sec.ind_acti = 0
                 AND sec.ind_borr = 1)
         AND NOT EXISTS
       (SELECT 1
                FROM zon_secci sec
               WHERE (SELECT zon1.cod_zona FROM zon_zona zon1 WHERE zon1.oid_zona = sec.zzon_oid_zona) =
                     tuad.cod_zona
                 AND sec.cod_secc = tuad.cod_secc
                 AND sec.ind_acti = 1
                 AND sec.ind_borr = 0);
    CURSOR cmodternse IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      tuad.cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      tuad.cod_ubig,
                      tuad.cod_nse1,
                      tuad.cod_nse2,
                      tuad.cod_nse3,
                      'M' cod_oper,
                      'T' cod_nive,
                      NULL cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM (SELECT * FROM zon_tmp_unida_admin WHERE cod_terr IS NOT NULL) tuad
       WHERE EXISTS (SELECT 1
                FROM zon_terri ter
               WHERE (SELECT cod_pais FROM seg_pais pai WHERE oid_pais = ter.pais_oid_pais) =
                     tuad.cod_pais
                 AND ter.cod_terr = tuad.cod_terr
                 AND (nvl(ter.cod_nse1,
                          'XXX') != nvl(tuad.cod_nse1,
                                         'XXX') OR
                     (SELECT orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 ||
                              orde_7 || orde_8 || orde_9
                         FROM zon_valor_estru_geopo veg
                        WHERE veg.oid_valo_estr_geop = ter.vepo_oid_valo_estr_geop) !=
                     tuad.cod_ubig));

    CURSOR ctrater IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      tuad.cod_zona,
                      tuad.cod_secc,
                      tuad.cod_terr,
                      NULL des_unid_admi,
                      zon.cod_zona cod_zona_ante,
                      sec.cod_secc cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'T' cod_oper,
                      'T' cod_nive,
                      tuad.cod_peri cod_peri_inic,
                      NULL cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_terri_admin     tra,
             zon_terri           ter,
             zon_secci           sec,
             zon_zona            zon,
             zon_tmp_unida_admin tuad
       WHERE tra.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
         AND tra.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
         AND tra.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
         AND ter.oid_terr = tra.terr_oid_terr
         AND tra.ind_borr = 0
         AND tra.perd_oid_peri_fina IS NULL
         AND ter.cod_terr = tuad.cod_terr
         AND sec.oid_secc = tra.zscc_oid_secc
         AND zon.oid_zona = sec.zzon_oid_zona
         AND tuad.cod_terr IS NOT NULL
         AND (tuad.cod_zona != zon.cod_zona OR tuad.cod_secc != sec.cod_secc);

    CURSOR cborter IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      zon.cod_zona,
                      sec.cod_secc,
                      ter.cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'B' cod_oper,
                      'T' cod_nive,
                      NULL cod_peri_inic,
                      gen_fn_calcu_perio(tuad.cod_peri,
                                         -1) cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_terri_admin tra,
             zon_terri ter,
             zon_secci sec,
             zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad
       WHERE tra.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
         AND tra.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
         AND tra.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
         AND ter.oid_terr = tra.terr_oid_terr
         AND tra.ind_borr = 0
         AND tra.perd_oid_peri_fina IS NULL
         AND sec.oid_secc = tra.zscc_oid_secc
         AND zon.oid_zona = sec.zzon_oid_zona
         AND reg.oid_regi = zon.zorg_oid_regi
         AND sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND NOT EXISTS
       (SELECT 1 FROM zon_tmp_unida_admin tuad2 WHERE tuad2.cod_terr = ter.cod_terr);
    --                AND NOT EXISTS (SELECT 1
    --                                    FROM zon_tmp_unida_admin tuad2
    --                                    WHERE tuad2.COD_SUBG_VENT = sgv.COD_SUBG_VENT
    --                                     AND tuad2.COD_REGI = reg.COD_REGI
    --                                     AND tuad2.COD_ZONA = zon.COD_ZONA
    --                                     AND tuad2.COD_SECC = sec.COD_SECC
    --                                     AND tuad2.COD_TERR = ter.COD_TERR
    --                                );

    CURSOR cborsec IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      zon.cod_zona,
                      sec.cod_secc,
                      NULL cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'B' cod_oper,
                      'S' cod_nive,
                      NULL cod_peri_inic,
                      gen_fn_calcu_perio(tuad.cod_peri,
                                         -1) cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_secci sec,
             zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad
       WHERE zon.oid_zona = sec.zzon_oid_zona
         AND reg.oid_regi = zon.zorg_oid_regi
         AND sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND sec.ind_acti = 1
         AND sec.ind_borr = 0
         AND sec.perd_oid_peri_fina IS NULL
         AND NOT EXISTS (SELECT 1
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_zona = zon.cod_zona
                 AND tuad2.cod_secc = sec.cod_secc);
    --                AND NOT EXISTS (SELECT 1
    --                                    FROM zon_tmp_unida_admin tuad2
    --                                    WHERE tuad2.COD_SUBG_VENT = sgv.COD_SUBG_VENT
    --                                     AND tuad2.COD_REGI = reg.COD_REGI
    --                                     AND tuad2.COD_ZONA = zon.COD_ZONA
    --                                     AND tuad2.COD_SECC = sec.COD_SECC
    --                                );

    CURSOR cborzon IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      zon.cod_zona,
                      NULL cod_secc,
                      NULL cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'B' cod_oper,
                      'Z' cod_nive,
                      NULL cod_peri_inic,
                      gen_fn_calcu_perio(tuad.cod_peri,
                                         -1) cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad
       WHERE zon.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
         AND zon.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
         AND zon.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
         AND reg.oid_regi = zon.zorg_oid_regi
         AND sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND zon.ind_acti = 1
         AND zon.ind_borr = 0
         AND zon.perd_oid_peri_fina IS NULL
         AND NOT EXISTS (SELECT 1
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_subg_vent = sgv.cod_subg_vent
                 AND tuad2.cod_zona = zon.cod_zona);
    --                AND NOT EXISTS (SELECT 1
    --                                    FROM zon_tmp_unida_admin tuad2
    --                                    WHERE tuad2.COD_SUBG_VENT = sgv.COD_SUBG_VENT
    --                                     AND tuad2.COD_REGI = reg.COD_REGI
    --                                     AND tuad2.COD_ZONA = zon.COD_ZONA
    --                                );

    CURSOR cborreg IS
      SELECT DISTINCT tuad.cod_marc,
                      tuad.cod_cana,
                      tuad.cod_subg_vent,
                      tuad.cod_regi,
                      NULL cod_zona,
                      NULL cod_secc,
                      NULL cod_terr,
                      NULL des_unid_admi,
                      NULL cod_zona_ante,
                      NULL cod_secc_ante,
                      NULL cod_ubig,
                      NULL cod_nse1,
                      NULL cod_nse2,
                      NULL cod_nse3,
                      'B' cod_oper,
                      'R' cod_nive,
                      NULL cod_peri_inic,
                      gen_fn_calcu_perio(tuad.cod_peri,
                                         -1) cod_peri_fina,
                      NULL oid_terr_admi_ante,
                      NULL oid_terr_admi_nuev,
                      NULL usu_proc,
                      NULL fec_proc,
                      NULL val_fila,
                      tuad.cod_pais,
                      NULL oid_unid_admi,
                      tuad.cod_peri
        FROM zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi,
                              cod_zona,
                              cod_secc
                FROM zon_tmp_unida_admin) tuad
       WHERE sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.ind_acti = 1
         AND reg.ind_borr = 0
         AND reg.perd_oid_peri_fina IS NULL
         AND tuad.cod_zona IS NULL
         AND tuad.cod_secc IS NULL
         AND NOT EXISTS (SELECT 1
                FROM zon_tmp_unida_admin tuad2
               WHERE tuad2.cod_subg_vent = sgv.cod_subg_vent
                 AND tuad2.cod_regi = reg.cod_regi);

  BEGIN

    DELETE FROM zon_histo_valid_unadm hvua WHERE hvua.fec_proc IS NULL;

    dbms_stats.gather_table_stats(ownname => USER,
                                  tabname => 'ZON_TMP_UNIDA_ADMIN',
                                  cascade => TRUE);

    /*
    * Determinar UNIDADES ADMINISTRATIVAS a crear
    */
    -- Regiones
    /*
    * NO SE CREAN REGIONES
    * POR DISEÑO DE CASO DE USO LAS REGIONES SE SELECCIONAN ANTES DE LA CARGA DE ARCHIVOS
    */
    -- Zonas
    OPEN caltzon;
    LOOP
      FETCH caltzon BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE caltzon;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;
    -- Secciones de Zonas Nuevas
    OPEN caltsecnuezon;
    LOOP
      FETCH caltsecnuezon BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE caltsecnuezon;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;
    -- Secciones de Zonas Existentes
    OPEN caltsecexizon;
    LOOP
      FETCH caltsecexizon BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE caltsecexizon;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;
    -- Territorios
    OPEN caltter;
    LOOP
      FETCH caltter BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE caltter;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;

    /*
    * Modificacion de Unidades Geograficas
    */
    -- Territorios
    IF (psindicadornivelse = '1') THEN
      OPEN cmodternse;
      LOOP
        FETCH cmodternse BULK COLLECT
          INTO tabhistvali LIMIT w_filas;
        EXIT WHEN tabhistvali.count = 0;
        FORALL i IN 1 .. tabhistvali.count
          INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
      END LOOP;
      CLOSE cmodternse;

    ELSE
      OPEN cmodter;
      LOOP
        FETCH cmodter BULK COLLECT
          INTO tabhistvali LIMIT w_filas;
        EXIT WHEN tabhistvali.count = 0;
        FORALL i IN 1 .. tabhistvali.count
          INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
      END LOOP;
      CLOSE cmodter;

    END IF;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;
    /*
    * Reactivacion de Secciones
    */

    -- Secciones
    OPEN creasec;
    LOOP
      FETCH creasec BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE creasec;

    /*
    * Trasvase de Unidades Geograficas
    */
    -- Territorios
    OPEN ctrater;
    LOOP
      FETCH ctrater BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE ctrater;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;

    /*
    * Eliminacion de Unidades Geograficas
    */
    -- Territorios
    OPEN cborter;
    LOOP
      FETCH cborter BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE cborter;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;
    -- Seccion
    OPEN cborsec;
    LOOP
      FETCH cborsec BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE cborsec;
    -- Zona
    OPEN cborzon;
    LOOP
      FETCH cborzon BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE cborzon;
    -- Region
    OPEN cborreg;
    LOOP
      FETCH cborreg BULK COLLECT
        INTO tabhistvali LIMIT w_filas;
      EXIT WHEN tabhistvali.count = 0;
      FORALL i IN 1 .. tabhistvali.count
        INSERT INTO zon_histo_valid_unadm VALUES tabhistvali (i);
    END LOOP;
    CLOSE cborreg;
    --
    UPDATE zon_histo_valid_unadm
       SET val_fila = zon_seq_histo_valid_unadm.nextval
     WHERE val_fila IS NULL;

    -- LLamada al proceso de calculo de errores de eliminacion
    zon_pr_recal_error_elimi;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_SEGUN_VALID_UNADM: ' || ls_sqlerrm);
  END zon_pr_segun_valid_unadm;

  /***************************************************************************
  Descripcion       : Realiza las actualizaciones de Unidades Administrativas
                      ALTAS
  Fecha Creacion    : 19/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin_alta
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typregaltzon IS RECORD(
      val_fila NUMBER,
      oid_regi NUMBER,
      cod_zona VARCHAR2(4),
      des_zona VARCHAR2(40));
    TYPE typregaltsec IS RECORD(
      val_fila NUMBER,
      oid_zona NUMBER,
      cod_secc VARCHAR2(1),
      des_secc VARCHAR2(40));
    TYPE typregaltter IS RECORD(
      val_fila           NUMBER,
      oid_secc           NUMBER,
      oid_valo_estr_geop NUMBER,
      cod_nse1           VARCHAR(2),
      cod_terr           VARCHAR2(6));

    TYPE typtabaltzon IS TABLE OF typregaltzon;
    TYPE typtabaltsec IS TABLE OF typregaltsec;
    TYPE typtabaltter IS TABLE OF typregaltter;
    tabaltzon typtabaltzon;
    tabaltsec typtabaltsec;
    tabaltter typtabaltter;
    lntmpoid  NUMBER;
    lntmpoid2 NUMBER;

    CURSOR caltzon IS
      SELECT val_fila,
             (SELECT oid_regi
                FROM zon_regio
               WHERE cod_regi = hvua.cod_regi
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_regi,
             cod_zona,
             des_unid_admi
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'A'
         AND hvua.cod_nive = 'Z'
         AND hvua.fec_proc IS NULL;

    CURSOR caltsec IS
      SELECT val_fila,
             (SELECT oid_zona
                FROM zon_zona
               WHERE cod_zona = hvua.cod_zona
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_zona,
             cod_secc,
             des_unid_admi
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'A'
         AND hvua.cod_nive = 'S'
         AND hvua.fec_proc IS NULL;

    CURSOR caltter IS
      SELECT val_fila,
             (SELECT oid_secc
                FROM zon_secci
               WHERE zzon_oid_zona = (SELECT oid_zona
                                        FROM zon_zona
                                       WHERE cod_zona = hvua.cod_zona
                                         AND pais_oid_pais = gntmpoidpais
                                         AND marc_oid_marc = gntmpoidmarc
                                         AND cana_oid_cana = gntmpoidcana
                                         AND perd_oid_peri_fina IS NULL
                                         AND rownum = 1)
                 AND cod_secc = hvua.cod_secc
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_secc,
             (SELECT oid_valo_estr_geop
                FROM zon_valor_estru_geopo
               WHERE orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 || orde_7 || orde_8 ||
                     orde_9 = hvua.cod_ubig
                 AND rownum = 1) oid_valo_estr_geop,
             cod_nse1,
             cod_terr
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'A'
         AND hvua.cod_nive = 'T'
         AND hvua.fec_proc IS NULL;

  BEGIN

    /*
    * No se insertaran permisos para las regiones
    * Dado que no se crean regiones esto debe manejarse por
    * la opcion respectiva
    */

    /*
    * Creando NUEVAS Zonas
    */
    OPEN caltzon;
    LOOP
      FETCH caltzon BULK COLLECT
        INTO tabaltzon LIMIT w_filas;
      EXIT WHEN tabaltzon.count = 0;

      FOR i IN 1 .. tabaltzon.count
      LOOP
        SELECT zon_zzon_seq.nextval INTO lntmpoid FROM dual;
        INSERT INTO zon_zona
          (oid_zona,
           zorg_oid_regi,
           cod_zona,
           des_zona,
           pais_oid_pais,
           marc_oid_marc,
           cana_oid_cana,
           perd_oid_peri_inic,
           ind_acti,
           ind_borr)
        VALUES
          (lntmpoid,
           tabaltzon(i).oid_regi,
           tabaltzon(i).cod_zona,
           tabaltzon(i).des_zona,
           gntmpoidpais,
           gntmpoidmarc,
           gntmpoidcana,
           gntmpoidperi,
           1,
           0);

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = lntmpoid,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabaltzon(i).val_fila;
      END LOOP;

    END LOOP;
    CLOSE caltzon;

    /*
    * Creando NUEVAS Secciones
    */
    OPEN caltsec;
    LOOP
      FETCH caltsec BULK COLLECT
        INTO tabaltsec LIMIT w_filas;
      EXIT WHEN tabaltsec.count = 0;

      FOR i IN 1 .. tabaltsec.count
      LOOP
        SELECT zon_zscc_seq.nextval INTO lntmpoid FROM dual;
        INSERT INTO zon_secci
          (oid_secc,
           zzon_oid_zona,
           cod_secc,
           des_secci,
           perd_oid_peri_inic,
           ind_acti,
           ind_borr)
        VALUES
          (lntmpoid,
           tabaltsec(i).oid_zona,
           tabaltsec(i).cod_secc,
           tabaltsec(i).des_secc,
           gntmpoidperi,
           1,
           0);

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = lntmpoid,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabaltsec(i).val_fila;
      END LOOP;

    END LOOP;
    CLOSE caltsec;

    /*
    * Creando NUEVOS Territorios
    */
    OPEN caltter;
    LOOP
      FETCH caltter BULK COLLECT
        INTO tabaltter LIMIT w_filas;
      EXIT WHEN tabaltter.count = 0;

      FOR i IN 1 .. tabaltter.count
      LOOP
        SELECT zon_terr_seq.nextval INTO lntmpoid FROM dual;
        SELECT zon_ztad_seq.nextval INTO lntmpoid2 FROM dual;
        INSERT INTO zon_terri
          (pais_oid_pais,
           oid_terr,
           cod_terr,
           cod_nse1,
           vepo_oid_valo_estr_geop,
           fec_rtz,
           ind_borr)
        VALUES
          (gntmpoidpais,
           lntmpoid,
           tabaltter(i).cod_terr,
           tabaltter(i).cod_nse1,
           tabaltter(i).oid_valo_estr_geop,
           SYSDATE,
           0);
        INSERT INTO zon_terri_admin
          (oid_terr_admi,
           zscc_oid_secc,
           terr_oid_terr,
           pais_oid_pais,
           marc_oid_marc,
           cana_oid_cana,
           perd_oid_peri_inic,
           fec_rtz,
           ind_borr)
        VALUES
          (lntmpoid2,
           tabaltter(i).oid_secc,
           lntmpoid,
           gntmpoidpais,
           gntmpoidmarc,
           gntmpoidcana,
           gntmpoidperi,
           SYSDATE,
           0);

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = lntmpoid,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabaltter(i).val_fila;
      END LOOP;

    END LOOP;
    CLOSE caltter;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN_ALTA: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin_alta;

  /***************************************************************************
  Descripcion       : Realiza las actualizaciones de Unidades Administrativas
                      MODIFICACION
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin_modi
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typregmodter IS RECORD(
      val_fila           NUMBER,
      oid_valo_estr_geop NUMBER,
      cod_nse1           VARCHAR(2),
      oid_terr           NUMBER,
      cod_terr           VARCHAR2(6));
    TYPE typtabmodter IS TABLE OF typregmodter;
    tabmodter typtabmodter;

    CURSOR cmodter IS
      SELECT val_fila,
             (SELECT oid_valo_estr_geop
                FROM zon_valor_estru_geopo
               WHERE orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 || orde_7 || orde_8 ||
                     orde_9 = hvua.cod_ubig
                 AND rownum = 1) oid_valo_estr_geop,
             cod_nse1,
             (SELECT oid_terr
                FROM zon_terri
               WHERE pais_oid_pais = gntmpoidpais
                 AND cod_terr = hvua.cod_terr) oid_terr,
             cod_terr
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'M'
            --AND hvua.cod_nive = 'T'
         AND hvua.fec_proc IS NULL;

    lncontverif NUMBER;

  BEGIN

    SELECT COUNT(*)
      INTO lncontverif
      FROM zon_histo_valid_unadm hvua
     WHERE hvua.cod_oper = 'A'
       AND hvua.fec_proc IS NULL;

    IF lncontverif > 0 THEN
      pscodigoretorno  := '1';
      psmensajeretorno := lncontverif || ' registro(s)';
      RETURN;
    END IF;

    OPEN cmodter;
    LOOP
      FETCH cmodter BULK COLLECT
        INTO tabmodter LIMIT w_filas;
      EXIT WHEN tabmodter.count = 0;

      FOR i IN 1 .. tabmodter.count
      LOOP
        UPDATE zon_terri
           SET vepo_oid_valo_estr_geop = tabmodter(i).oid_valo_estr_geop,
               cod_nse1                = tabmodter(i).cod_nse1
         WHERE oid_terr = tabmodter(i).oid_terr;

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = tabmodter(i).oid_terr,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabmodter(i).val_fila;

      END LOOP;

    END LOOP;
    CLOSE cmodter;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN_MODI: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin_modi;

  /***************************************************************************
  Descripcion       : Realiza las reactivaciones de las secciones dadas de baja
                      REACTIVACIONES
  Fecha Creacion    : 30/09/2011
  Autor             : Jesse Rios
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin_reac
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typregreasec IS RECORD(
      val_fila NUMBER,
      oid_zona NUMBER,
      cod_secc VARCHAR2(1),
      des_secc zon_histo_valid_unadm.des_unid_admi%TYPE,
      cod_nse1 zon_histo_valid_unadm.cod_nse1%TYPE);

    TYPE typtabreasec IS TABLE OF typregreasec;

    tabreasec typtabreasec;

    CURSOR creasec IS
      SELECT val_fila,
             (SELECT oid_zona
                FROM zon_zona
               WHERE cod_zona = hvua.cod_zona
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_zona,
             cod_secc,
             des_unid_admi,
             cod_nse1
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'R'
         AND hvua.cod_nive = 'S'
         AND hvua.fec_proc IS NULL;

    lntmpoid NUMBER;

  BEGIN

    /*
    * Reactivando Secciones
    */
    OPEN creasec;
    LOOP
      FETCH creasec BULK COLLECT
        INTO tabreasec LIMIT w_filas;
      EXIT WHEN tabreasec.count = 0;

      FOR i IN 1 .. tabreasec.count
      LOOP
        SELECT zon_zscc_seq.nextval INTO lntmpoid FROM dual;
        INSERT INTO zon_secci
          (oid_secc,
           zzon_oid_zona,
           cod_secc,
           des_secci,
           cod_nse1,
           perd_oid_peri_inic,
           ind_acti,
           ind_borr)
        VALUES
          (lntmpoid,
           tabreasec(i).oid_zona,
           tabreasec(i).cod_secc,
           tabreasec(i).des_secc,
           tabreasec(i).cod_nse1,
           gntmpoidperi,
           1,
           0);

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = lntmpoid,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabreasec(i).val_fila;
      END LOOP;

    END LOOP;
    CLOSE creasec;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN_REAC: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin_reac;

  /***************************************************************************
  Descripcion       : Realiza las actualizaciones de Unidades Administrativas
                      TRASVASE
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin_tras
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typregtrater IS RECORD(
      val_fila      NUMBER,
      oid_terr      NUMBER,
      oid_terr_admi NUMBER,
      oid_secc      NUMBER,
      oid_zona      NUMBER);
    TYPE typregtracli IS RECORD(
      oid_clie_unid_admi NUMBER,
      oid_clie           NUMBER);
    TYPE typtabtrater IS TABLE OF typregtrater;
    TYPE typtabtracli IS TABLE OF typregtracli;
    tabtrater typtabtrater;
    tabtracli typtabtracli;

    CURSOR ctrater IS
      SELECT hvua.val_fila,
             (SELECT oid_terr
                FROM zon_terri
               WHERE pais_oid_pais = gntmpoidpais
                 AND cod_terr = hvua.cod_terr) oid_terr,
             (SELECT oid_terr_admi
                FROM zon_terri_admin
               WHERE pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND terr_oid_terr = (SELECT oid_terr
                                        FROM zon_terri
                                       WHERE pais_oid_pais = gntmpoidpais
                                         AND cod_terr = hvua.cod_terr)
                 AND ind_borr = 0
                 AND perd_oid_peri_fina IS NULL) oid_terr_admi,
             (SELECT oid_secc
                FROM zon_secci
               WHERE zzon_oid_zona = (SELECT oid_zona
                                        FROM zon_zona
                                       WHERE cod_zona = hvua.cod_zona
                                         AND pais_oid_pais = gntmpoidpais
                                         AND marc_oid_marc = gntmpoidmarc
                                         AND cana_oid_cana = gntmpoidcana
                                         AND perd_oid_peri_fina IS NULL
                                         AND rownum = 1)
                 AND cod_secc = hvua.cod_secc
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_secc,
             (SELECT oid_zona
                FROM zon_zona
               WHERE cod_zona = hvua.cod_zona
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_zona
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'T'
         AND hvua.fec_proc IS NULL;

    CURSOR ctracli(oidterradmi NUMBER) IS
      SELECT oid_clie_unid_admi,
             clie_oid_clie
        FROM mae_clien_unida_admin
       WHERE ztad_oid_terr_admi = oidterradmi
         AND ind_acti = 1
         AND perd_oid_peri_fin IS NULL;
    /*
    Se elimina validacion de Territorio ya trasvasado para un periodo
    CURSOR cTerYaTra IS
       SELECT hvua.COD_TERR
        FROM zon_histo_valid_unadm hvua
        WHERE hvua.cod_oper = 'T'
          AND hvua.FEC_PROC IS NULL
          AND hvua.DES_UNID_ADMI IS NOT NULL;
     */
    lncontverif NUMBER;
    lntmpoid    NUMBER;
    lntmpoid2   NUMBER;

    lsterritorio                   zon_histo_valid_unadm.cod_terr%TYPE;
    lsmensajeter                   VARCHAR2(1000);
    lnoidperiiniczonterriadmin     zon_terri_admin.perd_oid_peri_inic%TYPE;
    lnoidperiinicmaeclienunidadmin mae_clien_unida_admin.perd_oid_peri_ini%TYPE;
  BEGIN

    SELECT COUNT(*)
      INTO lncontverif
      FROM zon_histo_valid_unadm hvua
     WHERE hvua.cod_oper IN ('A',
                             'M')
       AND hvua.fec_proc IS NULL;

    IF lncontverif > 0 THEN
      pscodigoretorno  := '1';
      psmensajeretorno := lncontverif || ' registro(s)';
      RETURN;
    END IF;
    /*
    Se elimina validacion de Territorio ya trasvasado para un periodo
    --Verificamos si tienes territorios trasvasados
    lsMensajeTer := '';
    OPEN cTerYaTra;
    LOOP
      FETCH cTerYaTra INTO lsTerritorio;

      EXIT WHEN cTerYaTra%NOTFOUND;

      IF (lsMensajeTer IS NULL) THEN
        lsMensajeTer := lsTerritorio;
      ELSE
        lsMensajeTer := lsMensajeTer || ', ' || lsTerritorio;
      END IF;

    END LOOP;
    CLOSE cTerYaTra;

    IF (length(lsMensajeTer) > 0) THEN
        psCodigoRetorno := '5';
        psMensajeRetorno := lsMensajeTer;
        RETURN;
    END IF;
    */
    OPEN ctrater;
    LOOP
      FETCH ctrater BULK COLLECT
        INTO tabtrater LIMIT w_filas;
      EXIT WHEN tabtrater.count = 0;

      FOR i IN 1 .. tabtrater.count
      LOOP
        SELECT perd_oid_peri_inic
          INTO lnoidperiiniczonterriadmin
          FROM zon_terri_admin
         WHERE oid_terr_admi = tabtrater(i).oid_terr_admi;

        IF lnoidperiiniczonterriadmin IS NULL OR lnoidperiiniczonterriadmin <> gntmpoidperi THEN
          -- Finalizando Relacion con territorio
          UPDATE zon_terri_admin
             SET ind_borr           = 1,
                 perd_oid_peri_fina = gntmpoidperiant
          --, FEC_RTZ = SYSDATE
           WHERE oid_terr_admi = tabtrater(i).oid_terr_admi;
        END IF;

        IF lnoidperiiniczonterriadmin IS NULL OR lnoidperiiniczonterriadmin <> gntmpoidperi THEN
          -- Creando nueva relacion con territorio
          SELECT zon_ztad_seq.nextval INTO lntmpoid FROM dual;
          INSERT INTO zon_terri_admin
            (oid_terr_admi,
             zscc_oid_secc,
             terr_oid_terr,
             pais_oid_pais,
             marc_oid_marc,
             cana_oid_cana,
             perd_oid_peri_inic,
             fec_rtz,
             ind_borr)
          VALUES
            (lntmpoid,
             tabtrater(i).oid_secc,
             tabtrater(i).oid_terr,
             gntmpoidpais,
             gntmpoidmarc,
             gntmpoidcana,
             gntmpoidperi,
             SYSDATE,
             0);
        ELSE
          UPDATE zon_terri_admin
             SET zscc_oid_secc = tabtrater(i).oid_secc
           WHERE oid_terr_admi = tabtrater(i).oid_terr_admi;

          lntmpoid := tabtrater(i).oid_terr_admi;

        END IF;
        -- Actualizaando FEC_RTZ en territorio
        UPDATE zon_terri SET fec_rtz = SYSDATE WHERE oid_terr = tabtrater(i).oid_terr;

        -- Trasvasando Clientes
        OPEN ctracli(tabtrater(i).oid_terr_admi);
        LOOP
          FETCH ctracli BULK COLLECT
            INTO tabtracli LIMIT w_filas;
          EXIT WHEN tabtracli.count = 0;
          FOR j IN 1 .. tabtracli.count
          LOOP

            SELECT perd_oid_peri_ini
              INTO lnoidperiinicmaeclienunidadmin
              FROM mae_clien_unida_admin
             WHERE oid_clie_unid_admi = tabtracli(j).oid_clie_unid_admi;

            IF lnoidperiinicmaeclienunidadmin IS NULL OR
               lnoidperiinicmaeclienunidadmin <> gntmpoidperi THEN
              -- Finalizando relacion anterior
              UPDATE mae_clien_unida_admin
                 SET ind_acti          = 0,
                     perd_oid_peri_fin = gntmpoidperiant,
                     fec_ulti_actu     = SYSDATE
               WHERE oid_clie_unid_admi = tabtracli(j).oid_clie_unid_admi;
            END IF;
            --Eliminando la unidad Administrativa que tenga el mismo periodo de inicio
            --del cual se va a registar uno nuevo
            zon_pr_elimi_unida_admin(tabtracli(j).oid_clie,
                                     gntmpoidperi);

            -- Creando nueva relacion
            SELECT mae_cuad_seq.nextval INTO lntmpoid2 FROM dual;

            INSERT INTO mae_clien_unida_admin
              (oid_clie_unid_admi,
               clie_oid_clie,
               perd_oid_peri_ini,
               ztad_oid_terr_admi,
               ind_acti)
            VALUES
              (lntmpoid2,
               tabtracli(j).oid_clie,
               gntmpoidperi,
               lntmpoid,
               1);
          END LOOP;
        END LOOP;
        CLOSE ctracli;

        -- Trasvasando pedidos no facturados
        UPDATE ped_solic_cabec
           SET ztad_oid_terr_admi = lntmpoid,
               zzon_oid_zona      = tabtrater(i).oid_zona
         WHERE ztad_oid_terr_admi = tabtrater(i).oid_terr_admi
           AND fec_fact IS NULL
           AND perd_oid_peri = gntmpoidperi;

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi      = tabtrater(i).oid_terr,
               oid_terr_admi_ante = tabtrater(i).oid_terr_admi,
               oid_terr_admi_nuev = lntmpoid,
               fec_proc           = SYSDATE,
               usu_proc           = psusuario
         WHERE val_fila = tabtrater(i).val_fila;
      END LOOP;

    END LOOP;
    CLOSE ctrater;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN_TRAS: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin_tras;

  /***************************************************************************
  Descripcion       : Realiza las actualizaciones de Unidades Administrativas
                      ELIMINACION
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin_elim
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    lncontverif NUMBER;
    lncontpedid NUMBER;
    TYPE typregborter IS RECORD(
      val_fila      NUMBER,
      oid_terr      NUMBER,
      oid_terr_admi NUMBER);
    TYPE typregborsec IS RECORD(
      val_fila NUMBER,
      oid_secc NUMBER);
    TYPE typregborzon IS RECORD(
      val_fila NUMBER,
      oid_zona NUMBER,
      COD_SUBG_VENT zon_histo_valid_unadm.cod_subg_vent%type,
      cod_regi zon_histo_valid_unadm.cod_regi%type,
      cod_zona zon_histo_valid_unadm.cod_zona%type,
      cod_peri zon_histo_valid_unadm.cod_peri%type);
    TYPE typregborreg IS RECORD(
      val_fila NUMBER,
      oid_regi NUMBER,
      COD_SUBG_VENT zon_histo_valid_unadm.cod_subg_vent%type,
      cod_regi zon_histo_valid_unadm.cod_regi%type,
      cod_peri zon_histo_valid_unadm.cod_peri%type);
    TYPE typtabborter IS TABLE OF typregborter;
    TYPE typtabborsec IS TABLE OF typregborsec;
    TYPE typtabborzon IS TABLE OF typregborzon;
    TYPE typtabborreg IS TABLE OF typregborreg;
    tabborter typtabborter;
    tabborsec typtabborsec;
    tabborzon typtabborzon;
    tabborreg typtabborreg;
  v_existe NUMBER;
    CURSOR cborter IS
      SELECT val_fila,
             (SELECT oid_terr
                FROM zon_terri
               WHERE pais_oid_pais = gntmpoidpais
                 AND cod_terr = hvua.cod_terr) oid_terr,
             (SELECT oid_terr_admi
                FROM zon_terri_admin
               WHERE pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND terr_oid_terr = (SELECT oid_terr
                                        FROM zon_terri
                                       WHERE pais_oid_pais = gntmpoidpais
                                         AND cod_terr = hvua.cod_terr)
                 AND ind_borr = 0
                 AND perd_oid_peri_fina IS NULL) oid_terr_admi
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'T'
         AND hvua.fec_proc IS NULL;

    CURSOR cborsec IS
      SELECT val_fila,
             (SELECT oid_secc
                FROM zon_secci
               WHERE zzon_oid_zona = (SELECT oid_zona
                                        FROM zon_zona
                                       WHERE cod_zona = hvua.cod_zona
                                         AND pais_oid_pais = gntmpoidpais
                                         AND marc_oid_marc = gntmpoidmarc
                                         AND cana_oid_cana = gntmpoidcana
                                         AND perd_oid_peri_fina IS NULL
                                         AND rownum = 1)
                 AND cod_secc = hvua.cod_secc
                 AND perd_oid_peri_fina IS NULL
                 AND ind_acti = 1
                 AND rownum = 1) oid_secc
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'S'
         AND hvua.fec_proc IS NULL;

    CURSOR cborzon IS
      SELECT val_fila,
             (SELECT oid_zona
                FROM zon_zona
               WHERE cod_zona = hvua.cod_zona
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_zona,
              COD_SUBG_VENT,
              cod_regi,
              cod_zona,
              cod_peri
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'Z'
         AND hvua.fec_proc IS NULL;

    CURSOR cborreg IS
      SELECT val_fila,
             (SELECT oid_regi
                FROM zon_regio
               WHERE cod_regi = hvua.cod_regi
                 AND pais_oid_pais = gntmpoidpais
                 AND marc_oid_marc = gntmpoidmarc
                 AND cana_oid_cana = gntmpoidcana
                 AND perd_oid_peri_fina IS NULL
                 AND rownum = 1) oid_regi,
              COD_SUBG_VENT,
              cod_regi,
              cod_peri
        FROM zon_histo_valid_unadm hvua
       WHERE hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'R'
         AND hvua.fec_proc IS NULL;

    vsua                       VARCHAR2(9);
    vnoidresponsableseccion    zon_secci.clie_oid_clie%TYPE;
    vscodigoresponsableseccion mae_clien.cod_clie%TYPE;
    vnoidhistogere             zon_histo_geren.oid_hist_gere%TYPE;
    vnoidperizonterriadm       zon_terri_admin.PERD_OID_PERI_INIC%TYPE;
    vdfechahasta               bas_ctrl_fact.fec_proc%TYPE;
    vscodigopais               seg_pais.cod_pais%TYPE;
    vscodigoperiodo            seg_perio_corpo.cod_peri%TYPE;

    V_OID_HIST_GERE            varchar2(50);
    V_PERD_OID_PERI_DESD       varchar2(50);

  BEGIN
    SELECT COUNT(*)
      INTO lncontverif
      FROM zon_histo_valid_unadm hvua
     WHERE hvua.cod_oper IN ('A',
                             'M',
                             'T')
       AND hvua.fec_proc IS NULL;

    IF lncontverif > 0 THEN
      pscodigoretorno  := '1';
      psmensajeretorno := lncontverif || ' registro(s)';
      RETURN;
    END IF;

    SELECT fec_fina INTO vdfechahasta FROM cra_perio WHERE oid_peri = gntmpoidperiant;

    /*
            --Verificamos que las secciones a eliminar no tenga lideres
            SELECT COUNT(*) INTO lnContVerif
            FROM zon_histo_valid_unadm hvua, zon_zona z, zon_secci s
            WHERE hvua.cod_oper = 'B'
              AND hvua.cod_nive = 'S'
              AND hvua.FEC_PROC IS NULL
              AND hvua.cod_zona = z.cod_zona
              AND hvua.cod_secc = s.cod_secc
              AND z.oid_zona = s.zzon_oid_zona
              AND s.ind_acti = 1
              AND s.clie_oid_clie IS NOT NULL;

            IF lnContVerif > 0 THEN
                psCodigoRetorno := '3';
                psMensajeRetorno := lnContVerif || ' registro(s)';

                DELETE FROM ZON_HISTO_ERROR_ELIMI_UNADM
                WHERE COD_NIVE = 'S'
                  AND FEC_PROC IS NULL;

                INSERT INTO ZON_HISTO_ERROR_ELIMI_UNADM
                  (COD_MARC, COD_CANA, COD_SUBG_VENT,
                   COD_REGI, COD_ZONA, COD_SECC,
                   COD_NIVE, COD_PAIS)
                SELECT hvua.COD_MARC, hvua.COD_CANA, hvua.COD_SUBG_VENT,
                       hvua.COD_REGI, hvua.COD_ZONA, hvua.COD_SECC,
                       hvua.COD_NIVE, hvua.COD_PAIS
                  FROM zon_histo_valid_unadm hvua, zon_zona z, zon_secci s
                 WHERE hvua.cod_oper = 'B'
                   AND hvua.cod_nive = 'S'
                   AND hvua.FEC_PROC IS NULL
                   AND hvua.cod_zona = z.cod_zona
                   AND hvua.cod_secc = s.cod_secc
                   AND z.oid_zona = s.zzon_oid_zona
                   AND s.ind_acti = 1
                   AND s.clie_oid_clie IS NOT NULL;

                RETURN;
            END IF;
    */
    SELECT COUNT(*)
      INTO lncontverif
      FROM zon_histo_error_elimi_unadm heeu
     WHERE heeu.fec_proc IS NULL
       AND heeu.cod_nive = 'T';

    IF lncontverif > 0 THEN
      pscodigoretorno  := '2';
      psmensajeretorno := lncontverif || ' registro(s)';
      RETURN;
    END IF;

    SELECT COUNT(*)
      INTO lncontverif
      FROM zon_histo_error_elimi_unadm heeu
     WHERE heeu.fec_proc IS NULL;

    IF lncontverif > 0 THEN
      pscodigoretorno  := '3';
      psmensajeretorno := lncontverif || ' registro(s)';
      RETURN;
    END IF;

    -- Borrando territorios y territorios administrativos
    OPEN cborter;
    LOOP
      FETCH cborter BULK COLLECT
        INTO tabborter LIMIT w_filas;
      EXIT WHEN tabborter.count = 0;

      FOR i IN 1 .. tabborter.count
      LOOP
        UPDATE zon_terri SET ind_borr = 1 WHERE oid_terr = tabborter(i).oid_terr;
----------------
        SELECT   PERD_OID_PERI_INIC
        INTO   vnoidperizonterriadm
        FROM zon_terri_admin
        WHERE oid_terr_admi = tabborter(i).oid_terr_admi;
        IF  vnoidperizonterriadm =  gntmpoidperi
        THEN
          SELECT count(*) into lncontpedid
          FROM  PED_SOLIC_CABEC
          WHERE ztad_oid_terr_admi =  tabborter(i).oid_terr_admi;
          IF  lncontpedid > 0 THEN
              UPDATE zon_terri_admin
              SET ind_borr           = 1,
                  perd_oid_peri_fina = gntmpoidperiant
              WHERE oid_terr_admi = tabborter(i).oid_terr_admi;
          ELSE
          DELETE zon_terri_admin WHERE oid_terr_admi = tabborter(i).oid_terr_admi;
          END IF;
        ELSE
        UPDATE zon_terri_admin
           SET ind_borr           = 1,
               perd_oid_peri_fina = gntmpoidperiant
         WHERE oid_terr_admi = tabborter(i).oid_terr_admi;
        END IF;
--------------
        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = tabborter(i).oid_terr,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabborter(i).val_fila;
      END LOOP;
    END LOOP;
    CLOSE cborter;

    -- Borrando Secciones
    OPEN cborsec;
    LOOP
      FETCH cborsec BULK COLLECT
        INTO tabborsec LIMIT w_filas;
      EXIT WHEN tabborsec.count = 0;

      FOR i IN 1 .. tabborsec.count
      LOOP

        SELECT geven.cod_subg_vent || regi.cod_regi || zona.cod_zona || secc.cod_secc
          INTO vsua
          FROM zon_secci           secc,
               zon_zona            zona,
               zon_regio           regi,
               zon_sub_geren_venta geven
         WHERE geven.oid_subg_vent = regi.zsgv_oid_subg_vent
           AND regi.oid_regi = zona.zorg_oid_regi
           AND zona.oid_zona = secc.zzon_oid_zona
           AND secc.oid_secc = tabborsec(i).oid_secc;

        BEGIN
          SELECT clie_oid_clie
            INTO vnoidresponsableseccion
            FROM zon_secci
           WHERE oid_secc = tabborsec(i).oid_secc;
        EXCEPTION
          WHEN no_data_found THEN
            vnoidresponsableseccion := NULL;
        END;

        UPDATE zon_secci
           SET ind_borr           = 1,
               ind_acti           = 0,
               perd_oid_peri_fina = gntmpoidperiant,
               clie_oid_clie      = NULL
         WHERE oid_secc = tabborsec(i).oid_secc;

        IF vnoidresponsableseccion IS NOT NULL THEN
          vscodigoresponsableseccion := gen_pkg_gener.gen_fn_devuelve_cod_clie(vnoidresponsableseccion);
        END IF;

        SELECT cod_pais INTO vscodigopais FROM seg_pais WHERE oid_pais = gntmpoidpais;

        vscodigoperiodo := gen_pkg_gener.gen_fn_devuelve_des_perio(gntmpoidperi);

        let_pkg_proce.let_pr_actua_clasi_lider(vscodigopais,
                                               2,
                                               4,
                                               vscodigoperiodo,
                                               vscodigoresponsableseccion,
                                               psusuario);

        BEGIN
          SELECT oid_hist_gere,
                 perd_oid_peri_desd
            INTO vnoidhistogere,
                 gntmpoidperides
            FROM zon_histo_geren
           WHERE ua = vsua
             AND fec_hast IS NULL
             AND gere = nvl2(vnoidresponsableseccion,
                             vscodigoresponsableseccion,
                             gere);
        EXCEPTION
          WHEN no_data_found THEN
            vnoidhistogere := NULL;
        END;

        IF vnoidhistogere IS NOT NULL THEN

          IF gntmpoidperides = gntmpoidperi THEN
            DELETE FROM zon_histo_geren WHERE oid_hist_gere = vnoidhistogere;
          ELSE
            UPDATE zon_histo_geren
               SET fec_hast           = vdfechahasta,
                   perd_oid_peri_hast = gntmpoidperiant,
                   ind_desv_auto      = 3,
                   usu_modi           = psusuario,
                   fec_modi           = SYSDATE
             WHERE oid_hist_gere = vnoidhistogere;
          END IF;

        END IF;

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = tabborsec(i).oid_secc,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabborsec(i).val_fila;
      END LOOP;
    END LOOP;
    CLOSE cborsec;

    -- Borrando Zonas
    OPEN cborzon;
    LOOP
      FETCH cborzon BULK COLLECT
        INTO tabborzon LIMIT w_filas;
      EXIT WHEN tabborzon.count = 0;

      FOR i IN 1 .. tabborzon.count
      LOOP

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = tabborzon(i).oid_zona,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabborzon(i).val_fila;

         --nuevo req xxxxxxxxx
         BEGIN
         select OID_HIST_GERE, PERD_OID_PERI_DESD
         into V_OID_HIST_GERE, V_PERD_OID_PERI_DESD
         from ZON_HISTO_GEREN
         where UA = tabborzon(i).COD_SUBG_VENT || tabborzon(i).cod_regi || tabborzon(i).cod_zona
         and FEC_HAST is null
         and gere = (select COD_CLIE
                       from zon_zona z, mae_clien m
                      where z.clie_oid_clie = m.oid_clie
                        and z.oid_zona = tabborzon(i).oid_zona);
         EXCEPTION
            WHEN no_data_found THEN
                V_OID_HIST_GERE := NULL;
                V_PERD_OID_PERI_DESD := NULL;
         END;

           if V_OID_HIST_GERE IS NOT NULL then
              if V_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(tabborzon(i).cod_peri) then
                  delete ZON_HISTO_GEREN
                   where UA = tabborzon(i).COD_SUBG_VENT || tabborzon(i).cod_regi || tabborzon(i).cod_zona
                     and FEC_HAST is null;
              else
                   update ZON_HISTO_GEREN set
                   PERD_OID_PERI_HAST  =  gntmpoidperiant ,
                   FEC_HAST = vdfechahasta,
                   IND_DESV_AUTO = 6,
                     USU_MODI = psusuario,
                     FEC_MODI = sysdate
                   where UA = tabborzon(i).COD_SUBG_VENT || tabborzon(i).cod_regi || tabborzon(i).cod_zona
                   and FEC_HAST is null
                   and gere = (select COD_CLIE
                                 from zon_zona z, mae_clien m
                                where z.clie_oid_clie = m.oid_clie
                                  and z.oid_zona = tabborzon(i).oid_zona);
              end if;
           end if;

        UPDATE zon_zona
           SET ind_borr           = 1,
               ind_acti           = 0,
               perd_oid_peri_fina = gntmpoidperiant,
               fec_ulti_actu      = SYSDATE,
               CLIE_OID_CLIE      = ''
         WHERE oid_zona = tabborzon(i).oid_zona;

      END LOOP;
    END LOOP;
    CLOSE cborzon;

    -- Borrando Regiones
    OPEN cborreg;
    LOOP
      FETCH cborreg BULK COLLECT
        INTO tabborreg LIMIT w_filas;
      EXIT WHEN tabborreg.count = 0;

      FOR i IN 1 .. tabborreg.count
      LOOP

        UPDATE zon_histo_valid_unadm
           SET oid_unid_admi = tabborreg(i).oid_regi,
               fec_proc      = SYSDATE,
               usu_proc      = psusuario
         WHERE val_fila = tabborreg(i).val_fila;

         --nuevo req xxxxxxxxx
         BEGIN
         select OID_HIST_GERE, PERD_OID_PERI_DESD
         into V_OID_HIST_GERE, V_PERD_OID_PERI_DESD
         from ZON_HISTO_GEREN
         where UA = tabborreg(i).COD_SUBG_VENT || tabborreg(i).cod_regi
         and FEC_HAST is null
          and gere = (select COD_CLIE
                             from mae_clien m, zon_regio z
                            where z.clie_oid_clie = m.oid_clie
                              and z.cod_regi = tabborreg(i).cod_regi);
         EXCEPTION
            WHEN no_data_found THEN
                V_OID_HIST_GERE := NULL;
                V_PERD_OID_PERI_DESD := NULL;
         END;

           if V_OID_HIST_GERE IS NOT NULL then
              if V_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(tabborreg(i).cod_peri) then
                  delete ZON_HISTO_GEREN
                   where UA = tabborreg(i).COD_SUBG_VENT || tabborreg(i).cod_regi
                     and FEC_HAST is null;
              else
                   update ZON_HISTO_GEREN set
                   PERD_OID_PERI_HAST  =  gntmpoidperiant ,
                   FEC_HAST = vdfechahasta,
                   IND_DESV_AUTO = 7,
                     USU_MODI = psusuario,
                     FEC_MODI = sysdate
                   where UA = tabborreg(i).COD_SUBG_VENT || tabborreg(i).cod_regi
                   and FEC_HAST is null
                   and gere = (select COD_CLIE
                                 from mae_clien m, zon_regio z
                                where z.clie_oid_clie = m.oid_clie
                                  and z.cod_regi = tabborreg(i).cod_regi);
              end if;
           end if;

        UPDATE zon_regio
           SET ind_borr           = 1,
               ind_acti           = 0,
               perd_oid_peri_fina = gntmpoidperiant,
                fec_ulti_actu      = SYSDATE,
                CLIE_OID_CLIE      = ''
         WHERE oid_regi = tabborreg(i).oid_regi;

      END LOOP;
    END LOOP;
    CLOSE cborreg;

    --

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN_ELIM: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin_elim;

  /***************************************************************************
  Descripcion       : realiza las actualizaciones de Unidades Administrativas
                      ALTAS, MODIF, BAJAS y TRASV
  Fecha Creacion    : 19/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_actua_unida_admin
  (
    psoperacion      IN VARCHAR2,
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
  BEGIN

    zon_pr_inici_globa;

    IF psoperacion = cte_opera_alta THEN
      zon_pr_actua_unida_admin_alta(psusuario,
                                    pscodigoretorno,
                                    psmensajeretorno);
    ELSIF psoperacion = cte_opera_modi THEN
      zon_pr_actua_unida_admin_modi(psusuario,
                                    pscodigoretorno,
                                    psmensajeretorno);
    ELSIF psoperacion = cte_opera_reac THEN
      zon_pr_actua_unida_admin_reac(psusuario,
                                    pscodigoretorno,
                                    psmensajeretorno);
    ELSIF psoperacion = cte_opera_tras THEN
      zon_pr_actua_unida_admin_tras(psusuario,
                                    pscodigoretorno,
                                    psmensajeretorno);
    ELSIF psoperacion = cte_opera_elim THEN
      zon_pr_actua_unida_admin_elim(psusuario,
                                    pscodigoretorno,
                                    psmensajeretorno);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_UNIDA_ADMIN: ' || ls_sqlerrm);
  END zon_pr_actua_unida_admin;

  /***************************************************************************
  Descripcion       : Recalcula errores de eliminacion
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_recal_error_elimi IS
    TYPE typtabhisterroelim IS TABLE OF zon_histo_error_elimi_unadm%ROWTYPE;
    tabhisterroelim typtabhisterroelim;

    CURSOR cerrelicli IS
      SELECT cli.cod_clie,
             cli.val_ape1 || ' ' || cli.val_ape2 || ' ' || cli.val_apel_casa || ' ' || cli.val_nom1 || ' ' ||
             cli.val_nom2 nom_clie,
             tuad.cod_marc,
             tuad.cod_cana,
             tuad.cod_subg_vent,
             tuad.cod_regi,
             zon.cod_zona,
             sec.cod_secc,
             ter.cod_terr,
             tuad.cod_peri,
             'T' cod_nive,
             NULL usu_proc,
             NULL fec_proc,
             tuad.cod_pais,
             NULL val_fila,
             NULL oid_terr_admi_ante,
             NULL oid_terr_admi_nuev,
             NULL cod_terr_nuev
        FROM mae_clien cli,
             mae_clien_unida_admin cua,
             zon_terri_admin tra,
             zon_terri ter,
             zon_secci sec,
             zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad,
             zon_histo_valid_unadm hvua
       WHERE cli.oid_clie = cua.clie_oid_clie
         AND cua.ind_acti = 1
         AND cua.perd_oid_peri_fin IS NULL
         AND cua.ztad_oid_terr_admi = tra.oid_terr_admi
         AND tra.ind_borr = 0
         AND tra.perd_oid_peri_fina IS NULL
         AND tra.terr_oid_terr = ter.oid_terr
         AND tra.zscc_oid_secc = sec.oid_secc
         AND sec.zzon_oid_zona = zon.oid_zona
         AND zon.zorg_oid_regi = reg.oid_regi
         AND reg.zsgv_oid_subg_vent = sgv.oid_subg_vent
         AND (SELECT cod_pais FROM seg_pais WHERE oid_pais = sgv.pais_oid_pais) = tuad.cod_pais
         AND (SELECT cod_marc FROM seg_marca WHERE oid_marc = sgv.marc_oid_marc) = tuad.cod_marc
         AND (SELECT cod_cana FROM seg_canal WHERE oid_cana = sgv.cana_oid_cana) = tuad.cod_cana
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND tra.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
         AND tra.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
         AND tra.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
         AND ter.cod_terr = hvua.cod_terr
         AND sec.cod_secc = hvua.cod_secc
         AND zon.cod_zona = hvua.cod_zona
         AND hvua.cod_marc = tuad.cod_marc
         AND hvua.cod_cana = tuad.cod_cana
         AND hvua.fec_proc IS NULL
         AND hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'T';

    CURSOR cerrelisec IS
      SELECT NULL cod_clie,
             NULL nom_clie,
             tuad.cod_marc,
             tuad.cod_cana,
             tuad.cod_subg_vent,
             tuad.cod_regi,
             zon.cod_zona,
             sec.cod_secc,
             ter.cod_terr,
             NULL cod_peri,
             'S' cod_nive,
             NULL usu_proc,
             NULL fec_proc,
             tuad.cod_pais,
             NULL val_fila,
             NULL oid_terr_admi_ante,
             NULL oid_terr_admi_nuev,
             NULL cod_terr_nuev
        FROM zon_terri_admin tra,
             zon_terri ter,
             zon_secci sec,
             zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad,
             zon_histo_valid_unadm hvua
       WHERE tra.ind_borr = 0
         AND tra.terr_oid_terr = ter.oid_terr
         AND tra.zscc_oid_secc = sec.oid_secc
         AND sec.zzon_oid_zona = zon.oid_zona
         AND zon.zorg_oid_regi = reg.oid_regi
         AND reg.zsgv_oid_subg_vent = sgv.oid_subg_vent
         AND (SELECT cod_pais FROM seg_pais WHERE oid_pais = sgv.pais_oid_pais) = tuad.cod_pais
         AND (SELECT cod_marc FROM seg_marca WHERE oid_marc = sgv.marc_oid_marc) = tuad.cod_marc
         AND (SELECT cod_cana FROM seg_canal WHERE oid_cana = sgv.cana_oid_cana) = tuad.cod_cana
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND tra.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = tuad.cod_pais)
         AND tra.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = tuad.cod_marc)
         AND tra.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = tuad.cod_cana)
         AND sec.cod_secc = hvua.cod_secc
         AND sec.ind_acti = 1
         AND sec.ind_borr = 0
         AND sec.perd_oid_peri_fina IS NULL
         AND zon.cod_zona = hvua.cod_zona
         AND zon.ind_acti = 1
         AND zon.ind_borr = 0
         AND zon.perd_oid_peri_fina IS NULL
         AND hvua.fec_proc IS NULL
         AND hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'S'
         AND hvua.cod_marc = tuad.cod_marc
         AND hvua.cod_cana = tuad.cod_cana
         AND NOT EXISTS (SELECT 1
                FROM zon_histo_valid_unadm hvua2
               WHERE hvua2.cod_terr = ter.cod_terr
                 AND hvua2.fec_proc IS NULL
                 AND hvua2.cod_nive = 'T'
                 AND hvua2.cod_oper IN ('T',
                                        'B'));

    CURSOR cerrelizon IS
      SELECT NULL cod_clie,
             NULL nom_clie,
             tuad.cod_marc,
             tuad.cod_cana,
             tuad.cod_subg_vent,
             tuad.cod_regi,
             zon.cod_zona,
             sec.cod_secc,
             NULL cod_terr,
             NULL cod_peri,
             'Z' cod_nive,
             NULL usu_proc,
             NULL fec_proc,
             tuad.cod_pais,
             NULL val_fila,
             NULL oid_terr_admi_ante,
             NULL oid_terr_admi_nuev,
             NULL cod_terr_nuev
        FROM zon_secci sec,
             zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad,
             zon_histo_valid_unadm hvua
       WHERE sec.zzon_oid_zona = zon.oid_zona
         AND zon.zorg_oid_regi = reg.oid_regi
         AND reg.zsgv_oid_subg_vent = sgv.oid_subg_vent
         AND (SELECT cod_pais FROM seg_pais WHERE oid_pais = sgv.pais_oid_pais) = tuad.cod_pais
         AND (SELECT cod_marc FROM seg_marca WHERE oid_marc = sgv.marc_oid_marc) = tuad.cod_marc
         AND (SELECT cod_cana FROM seg_canal WHERE oid_cana = sgv.cana_oid_cana) = tuad.cod_cana
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = tuad.cod_regi
         AND sec.cod_secc = hvua.cod_secc
         AND sec.ind_acti = 1
         AND sec.ind_borr = 0
         AND sec.perd_oid_peri_fina IS NULL
         AND zon.cod_zona = hvua.cod_zona
         AND zon.ind_acti = 1
         AND zon.ind_borr = 0
         AND zon.perd_oid_peri_fina IS NULL
         AND hvua.fec_proc IS NULL
         AND hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'Z'
         AND hvua.cod_marc = tuad.cod_marc
         AND hvua.cod_cana = tuad.cod_cana
         AND NOT EXISTS (SELECT 1
                FROM zon_histo_valid_unadm hvua2
               WHERE hvua2.cod_zona = zon.cod_zona
                 AND hvua2.cod_secc = sec.cod_secc
                 AND hvua2.fec_proc IS NULL
                 AND hvua2.cod_nive = 'S'
                 AND hvua2.cod_oper = 'B');

    CURSOR cerrelireg IS
      SELECT NULL cod_clie,
             NULL nom_clie,
             tuad.cod_marc,
             tuad.cod_cana,
             tuad.cod_subg_vent,
             tuad.cod_regi,
             zon.cod_zona,
             NULL cod_secc,
             NULL cod_terr,
             NULL cod_peri,
             'R' cod_nive,
             NULL usu_proc,
             NULL fec_proc,
             tuad.cod_pais,
             NULL val_fila,
             NULL oid_terr_admi_ante,
             NULL oid_terr_admi_nuev,
             NULL cod_terr_nuev
        FROM zon_zona zon,
             zon_regio reg,
             zon_sub_geren_venta sgv,
             (SELECT DISTINCT cod_pais,
                              cod_marc,
                              cod_cana,
                              cod_peri,
                              cod_subg_vent,
                              cod_regi
                FROM zon_tmp_unida_admin) tuad,
             zon_histo_valid_unadm hvua
       WHERE zon.zorg_oid_regi = reg.oid_regi
         AND reg.zsgv_oid_subg_vent = sgv.oid_subg_vent
         AND (SELECT cod_pais FROM seg_pais WHERE oid_pais = sgv.pais_oid_pais) = tuad.cod_pais
         AND (SELECT cod_marc FROM seg_marca WHERE oid_marc = sgv.marc_oid_marc) = tuad.cod_marc
         AND (SELECT cod_cana FROM seg_canal WHERE oid_cana = sgv.cana_oid_cana) = tuad.cod_cana
         AND sgv.cod_subg_vent = tuad.cod_subg_vent
         AND reg.cod_regi = hvua.cod_regi
         AND reg.ind_acti = 1
         AND reg.ind_borr = 0
         AND reg.perd_oid_peri_fina IS NULL
         AND zon.cod_zona = hvua.cod_zona
         AND zon.ind_acti = 1
         AND zon.ind_borr = 0
         AND zon.perd_oid_peri_fina IS NULL
         AND hvua.fec_proc IS NULL
         AND hvua.cod_oper = 'B'
         AND hvua.cod_nive = 'R'
         AND hvua.cod_marc = tuad.cod_marc
         AND hvua.cod_cana = tuad.cod_cana
         AND NOT EXISTS (SELECT 1
                FROM zon_histo_valid_unadm hvua2
               WHERE hvua2.cod_zona = zon.cod_zona
                 AND hvua2.cod_regi = reg.cod_regi
                 AND hvua2.fec_proc IS NULL
                 AND hvua2.cod_nive = 'Z'
                 AND hvua2.cod_oper = 'B');

  BEGIN

    DELETE zon_histo_error_elimi_unadm WHERE fec_proc IS NULL;

    /*
    * Errores de eliminacion
    */
    -- Clientes en territorios a eliminar
    OPEN cerrelicli;
    LOOP
      FETCH cerrelicli BULK COLLECT
        INTO tabhisterroelim LIMIT w_filas;
      EXIT WHEN tabhisterroelim.count = 0;
      FORALL i IN 1 .. tabhisterroelim.count
        INSERT INTO zon_histo_error_elimi_unadm VALUES tabhisterroelim (i);
    END LOOP;
    CLOSE cerrelicli;
    --
    UPDATE zon_histo_error_elimi_unadm
       SET val_fila = zon_seq_histo_ereli_unadm.nextval
     WHERE val_fila IS NULL;
    -- Secciones con territorios activos
    OPEN cerrelisec;
    LOOP
      FETCH cerrelisec BULK COLLECT
        INTO tabhisterroelim LIMIT w_filas;
      EXIT WHEN tabhisterroelim.count = 0;
      FORALL i IN 1 .. tabhisterroelim.count
        INSERT INTO zon_histo_error_elimi_unadm VALUES tabhisterroelim (i);
    END LOOP;
    CLOSE cerrelisec;
    --
    UPDATE zon_histo_error_elimi_unadm
       SET val_fila = zon_seq_histo_ereli_unadm.nextval
     WHERE val_fila IS NULL;
    -- Zonas con secciones activos
    OPEN cerrelizon;
    LOOP
      FETCH cerrelizon BULK COLLECT
        INTO tabhisterroelim LIMIT w_filas;
      EXIT WHEN tabhisterroelim.count = 0;
      FORALL i IN 1 .. tabhisterroelim.count
        INSERT INTO zon_histo_error_elimi_unadm VALUES tabhisterroelim (i);
    END LOOP;
    CLOSE cerrelizon;
    --
    UPDATE zon_histo_error_elimi_unadm
       SET val_fila = zon_seq_histo_ereli_unadm.nextval
     WHERE val_fila IS NULL;
    -- Regiones con zonas activas
    OPEN cerrelireg;
    LOOP
      FETCH cerrelireg BULK COLLECT
        INTO tabhisterroelim LIMIT w_filas;
      EXIT WHEN tabhisterroelim.count = 0;
      FORALL i IN 1 .. tabhisterroelim.count
        INSERT INTO zon_histo_error_elimi_unadm VALUES tabhisterroelim (i);
    END LOOP;
    CLOSE cerrelireg;
    --
    UPDATE zon_histo_error_elimi_unadm
       SET val_fila = zon_seq_histo_ereli_unadm.nextval
     WHERE val_fila IS NULL;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_RECAL_ERROR_ELIMI: ' || ls_sqlerrm);
  END zon_pr_recal_error_elimi;

  /***************************************************************************
  Descripcion       : Trasvasa consultoras de Errores de eliminacion
                      a nuevo Codigo de Territorio
  Fecha Creacion    : 20/05/2008
  Autor             : Rafael Romero
  ***************************************************************************/
  PROCEDURE zon_pr_trasv_consu_error_elimi
  (
    psusuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS
    TYPE typregtraclierreli IS RECORD(
      val_fila           NUMBER,
      oid_terr_admi_ante NUMBER,
      oid_terr_admi_nuev NUMBER,
      oid_clie_unid_admi NUMBER,
      oid_clie           NUMBER);
    TYPE typtabtraclierreli IS TABLE OF typregtraclierreli;
    tabtraclierreli typtabtraclierreli;
    lntmpoid        NUMBER;

    CURSOR ctraclierreli IS
      SELECT heeu.val_fila,
             cua.ztad_oid_terr_admi,
             (SELECT tra.oid_terr_admi
                FROM zon_terri_admin tra
               WHERE tra.pais_oid_pais = gntmpoidpais
                 AND tra.marc_oid_marc = gntmpoidmarc
                 AND tra.cana_oid_cana = gntmpoidcana
                 AND tra.terr_oid_terr = (SELECT ter.oid_terr
                                            FROM zon_terri ter
                                           WHERE ter.pais_oid_pais = gntmpoidpais
                                             AND ter.cod_terr = cod_terr_nuev)
                 AND tra.perd_oid_peri_fina IS NULL
                 AND tra.ind_borr = 0) oid_terr_admi_nuev,
             cua.oid_clie_unid_admi,
             cli.oid_clie
        FROM zon_histo_error_elimi_unadm heeu,
             mae_clien                   cli,
             mae_clien_unida_admin       cua
       WHERE heeu.fec_proc IS NULL
         AND heeu.cod_terr_nuev IS NOT NULL
         AND heeu.cod_nive = 'T'
         AND cli.cod_clie = heeu.cod_clie
         AND cli.oid_clie = cua.clie_oid_clie
         AND cua.ind_acti = 1
         AND cua.perd_oid_peri_fin IS NULL;

  BEGIN

    zon_pr_inici_globa;

    OPEN ctraclierreli;
    LOOP
      FETCH ctraclierreli BULK COLLECT
        INTO tabtraclierreli LIMIT w_filas;
      EXIT WHEN tabtraclierreli.count = 0;
      FOR i IN 1 .. tabtraclierreli.count
      LOOP
        -- Finalizando relacion anterior
        UPDATE mae_clien_unida_admin
           SET ind_acti          = 0,
               perd_oid_peri_fin = gntmpoidperiant,
               fec_ulti_actu     = SYSDATE
         WHERE oid_clie_unid_admi = tabtraclierreli(i).oid_clie_unid_admi;

        --Actualizamos la direccion principal del cliente
        UPDATE mae_clien_direc
           SET terr_oid_terr =
               (SELECT terr_oid_terr
                  FROM zon_terri_admin
                 WHERE oid_terr_admi = tabtraclierreli(i).oid_terr_admi_nuev)
         WHERE clie_oid_clie = tabtraclierreli(i).oid_clie
           AND ind_dire_ppal = '1'
           AND ind_elim = '0';

        --Eliminando la unidad Administrativa que tenga el mismo periodo de inicio
        --del cual se va a registar uno nuevo
        zon_pr_elimi_unida_admin(tabtraclierreli(i).oid_clie,
                                 gntmpoidperi);

        -- Creando nueva relacion
        SELECT mae_cuad_seq.nextval INTO lntmpoid FROM dual;
        INSERT INTO mae_clien_unida_admin
          (oid_clie_unid_admi,
           clie_oid_clie,
           perd_oid_peri_ini,
           ztad_oid_terr_admi,
           ind_acti)
        VALUES
          (lntmpoid,
           tabtraclierreli(i).oid_clie,
           gntmpoidperi,
           tabtraclierreli(i).oid_terr_admi_nuev,
           1);

        UPDATE zon_histo_error_elimi_unadm
           SET oid_terr_admi_ante = tabtraclierreli(i).oid_terr_admi_ante,
               oid_terr_admi_nuev = tabtraclierreli(i).oid_terr_admi_nuev,
               fec_proc           = SYSDATE,
               usu_proc           = psusuario
         WHERE val_fila = tabtraclierreli(i).val_fila;

      END LOOP;
    END LOOP;
    CLOSE ctraclierreli;

    -- Se vuelven a calcular los errores de eliminacion
    zon_pr_recal_error_elimi;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_TRASV_CONSU_ERROR_ELIMI: ' || ls_sqlerrm);
  END zon_pr_trasv_consu_error_elimi;

  /***************************************************************************
  Descripcion       : Elimina la unidad administrativa del CLiente, cuyo periodo
                      inicio corresponde al periodo recibido como parametro

  Fecha Creacion    : 26/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_elimi_unida_admin
  (
    pnoidcliente IN NUMBER,
    pnoidperiodo IN NUMBER
  ) IS
  BEGIN
    DELETE FROM mae_clien_unida_admin
     WHERE clie_oid_clie = pnoidcliente
       AND perd_oid_peri_ini = pnoidperiodo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ELIMI_UNIDA_ADMIN: ' || ls_sqlerrm);
  END zon_pr_elimi_unida_admin;

  /***************************************************************************
  Descripcion       : Verifica que no existe territorio administrativo ya
                      registroa para un territorio en un determinado periodo

  Fecha Creacion    : 27/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_verif_terri_perio
  (
    pnoidterritorio IN NUMBER,
    pnoidperiodo    IN NUMBER
  ) RETURN VARCHAR2 IS
    contador NUMBER;
    mensaje  VARCHAR2(50);
  BEGIN
    SELECT COUNT(*)
      INTO contador
      FROM zon_terri_admin
     WHERE terr_oid_terr = pnoidterritorio
       AND perd_oid_peri_inic = pnoidperiodo;

    IF (contador = 0) THEN
      mensaje := '';
    ELSE
      mensaje := 'Territorio Trasvasado';
    END IF;

    RETURN mensaje;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_FN_VERIF_TERRI_PERIO: ' || ls_sqlerrm);
  END zon_fn_verif_terri_perio;

  /***************************************************************************
  Descripcion       : Obtiene el oid Periodo

  Fecha Creacion    : 27/05/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_obtie_perio
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN NUMBER IS
    lnidpais    NUMBER;
    lnidmarca   NUMBER;
    lnidcanal   NUMBER;
    lnidperiodo NUMBER;

  BEGIN
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              lnidmarca,
                                                              lnidcanal);

    RETURN lnidperiodo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_FN_OBTIE_PERIO: ' || ls_sqlerrm);
  END zon_fn_obtie_perio;

  /***************************************************************************
  Descripcion       : Valida la Modificacion de Territorios a Consultoras

  Fecha Creacion    : 05/08/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_valid_modif_terri_consu
  (
    pscodigopais     IN VARCHAR2,
    pscodigomarca    IN VARCHAR2,
    pscodigocanal    IN VARCHAR2,
    pscodigoperiodo  IN VARCHAR2,
    pscodigoregiones IN VARCHAR2,
    psUsuario        IN VARCHAR2,
    pscodigoretorno  OUT VARCHAR2,
    psmensajeretorno OUT VARCHAR2
  ) IS

    TYPE typregconsultora IS RECORD(
      cod_clie      zon_tmp_terri_consu.cod_clie%TYPE,
      cod_terr_dest zon_tmp_terri_consu.cod_terr_dest%TYPE,
      val_fila      zon_tmp_terri_consu.val_fila%TYPE,
      est_vali      zon_tmp_terri_consu.est_vali%type,
      ind_apli      zon_tmp_terri_consu.ind_apli%type
    );
    TYPE typtabconsultora IS TABLE OF typregconsultora;
    tabconsultora typtabconsultora;

    lncontador      NUMBER;
    lnoidterritorio zon_terri.oid_terr%TYPE;
    lnindicador     NUMBER;

    lnoidpais    seg_pais.oid_pais%TYPE;
    lnoidmarca   seg_marca.oid_marc%TYPE;
    lnoidcanal   seg_canal.oid_cana%TYPE;
    lnoidperiodo cra_perio.oid_peri%TYPE;

    lscodigoregiondest  zon_regio.cod_regi%TYPE;
    lscodigozonadest    zon_zona.cod_zona%TYPE;
    lscodigosecciondest zon_secci.cod_secc%TYPE;

    lncodigoterritorioorig zon_terri.cod_terr%TYPE;
    lscodigoregionorig     zon_regio.cod_regi%TYPE;
    lscodigozonaorig       zon_zona.cod_zona%TYPE;
    lscodigoseccionorig    zon_secci.cod_secc%TYPE;
    lsEstadoOK  varchar2(5) := 'OK';

    CURSOR cconsultoras IS
      SELECT ter.cod_clie,
             ter.cod_terr_dest,
             ter.val_fila,
             ter.est_vali,
             ter.ind_apli
        FROM zon_tmp_terri_consu ter
       WHERE ter.cod_pais = pscodigopais
       ORDER BY ter.val_fila;

  BEGIN

    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               lnoidmarca,
                                                               lnoidcanal);
    pscodigoretorno := '0';
    OPEN cconsultoras;
    LOOP
      FETCH cconsultoras BULK COLLECT
        INTO tabconsultora LIMIT w_filas;
      EXIT WHEN tabconsultora.count = 0;

      FOR i IN 1 .. tabconsultora.count
      LOOP
        psmensajeretorno := tabconsultora(i).val_fila;
        if tabconsultora(i).est_vali = lsEstadoOK then
           continue;
        end if;

        --Se valida que el codigo de Consultora exista
        SELECT COUNT(oid_clie)
          INTO lncontador
          FROM mae_clien
         WHERE cod_clie = tabconsultora(i).cod_clie;

        IF (lncontador = 0) THEN
          pscodigoretorno := '1';
          UPDATE zon_tmp_terri_consu
          SET IND_APLI = 'N',
              FEC_PROC = SYSDATE,
              COD_USUA_PROC = psUsuario,
              MEN_ERRO = 'No existe el cliente de la fila '|| i
          WHERE val_fila = tabconsultora(i).val_fila;
          continue;
        END IF;

        --Verificamos si existe el territorio y si esta activo
        BEGIN
          SELECT oid_terr,
                 ind_borr
            INTO lnoidterritorio,
                 lnindicador
            FROM zon_terri
           WHERE cod_terr = tabconsultora(i).cod_terr_dest;

          IF (lnindicador = 1) THEN
            pscodigoretorno := '3';
            UPDATE zon_tmp_terri_consu
            SET IND_APLI = 'N',
                FEC_PROC = SYSDATE,
                COD_USUA_PROC = psUsuario,
                MEN_ERRO = 'Territorio no existe o esta inactivo'
            WHERE val_fila = tabconsultora(i).val_fila;
            continue;
          END IF;

        EXCEPTION
          WHEN OTHERS THEN
            UPDATE zon_tmp_terri_consu
            SET IND_APLI = 'N',
                FEC_PROC = SYSDATE,
                COD_USUA_PROC = psUsuario,
                MEN_ERRO = 'Territorio no existe o esta inactivo'
            WHERE val_fila = tabconsultora(i).val_fila;
            continue;
        END;

        --Validamos que el territorio pertenezca a una seccion activa
        BEGIN
          SELECT adm.ind_borr,
                 reg.cod_regi,
                 zon.cod_zona,
                 sec.cod_secc
            INTO lnindicador,
                 lscodigoregiondest,
                 lscodigozonadest,
                 lscodigosecciondest
            FROM zon_terri_admin adm,
                 zon_secci       sec,
                 zon_zona        zon,
                 zon_regio       reg
           WHERE adm.terr_oid_terr = lnoidterritorio
             AND adm.ind_borr = 0
             AND adm.zscc_oid_secc = sec.oid_secc
             AND sec.zzon_oid_zona = zon.oid_zona
             AND zon.zorg_oid_regi = reg.oid_regi;

        EXCEPTION
          WHEN OTHERS THEN
            pscodigoretorno := '4';
            UPDATE zon_tmp_terri_consu
            SET IND_APLI = 'N',
                FEC_PROC = SYSDATE,
                COD_USUA_PROC = psUsuario,
                MEN_ERRO = 'Territorio no pertenece a ninguna sección'
            WHERE val_fila = tabconsultora(i).val_fila;
            continue;
        END;

        --Verificar que la region a la cual se va a mover la consultoraa perteneza
        --a las regiones seleccionadas
        IF (instr(pscodigoregiones,
                  lscodigoregiondest) = 0) THEN
           pscodigoretorno := '5';
           UPDATE zon_tmp_terri_consu
           SET IND_APLI = 'N',
               FEC_PROC = SYSDATE,
               COD_USUA_PROC = psUsuario,
               MEN_ERRO = 'La region destino no pertenece a la región seleccionada, de la fila '|| i ||' del archivo excel'
           WHERE val_fila = tabconsultora(i).val_fila;
           continue;
        END IF;

        -- Verificar si la consultora tiene UA Activa
        select count(mc.oid_clie)
        into lncontador
        from mae_clien mc, mae_clien_unida_admin ua
        where mc.oid_clie = ua.clie_oid_clie
        and mc.cod_clie = tabconsultora(i).cod_clie
        and ua.ind_Acti = 1;

        IF (lncontador = 0) THEN
          pscodigoretorno := '8';
          UPDATE zon_tmp_terri_consu
          SET IND_APLI = 'N',
              FEC_PROC = SYSDATE,
              COD_USUA_PROC = psUsuario,
              MEN_ERRO = 'Consultora no tiene UA activa '
          WHERE val_fila = tabconsultora(i).val_fila;
          continue;
        END IF;

        --Verificar que la region de la Consultora pertenezca a las regiones seleccionadas
        SELECT reg.cod_regi,
               zon.cod_zona,
               sec.cod_secc,
               ter.cod_terr
          INTO lscodigoregionorig,
               lscodigozonaorig,
               lscodigoseccionorig,
               lncodigoterritorioorig
          FROM mae_clien             cli,
               mae_clien_unida_admin adm,
               zon_terri_admin       tad,
               zon_terri             ter,
               zon_secci             sec,
               zon_zona              zon,
               zon_regio             reg
         WHERE cli.cod_clie = tabconsultora(i).cod_clie
           AND cli.oid_clie = adm.clie_oid_clie
           AND adm.ind_acti = 1
           AND adm.ztad_oid_terr_admi = tad.oid_terr_admi
           AND tad.zscc_oid_secc = sec.oid_secc
           AND tad.terr_oid_terr = ter.oid_terr
           AND sec.zzon_oid_zona = zon.oid_zona
           AND zon.zorg_oid_regi = reg.oid_regi;

        IF (instr(pscodigoregiones,
                  lscodigoregionorig) = 0) THEN
          pscodigoretorno := '6';
           UPDATE zon_tmp_terri_consu
           SET IND_APLI = 'N',
               FEC_PROC = SYSDATE,
               COD_USUA_PROC = psUsuario,
               MEN_ERRO = 'La region destino no pertenece a la región seleccionada, de la fila '|| i ||' del archivo excel'
           WHERE val_fila = tabconsultora(i).val_fila;
           continue;
        END IF;

        --Valida que la Consultora no haya Facturado
        SELECT COUNT(1)
          INTO lncontador
          FROM ped_solic_cabec     cab,
               ped_tipo_solic_pais tslp,
               ped_tipo_solic      tsol,
               mae_clien           cli
         WHERE cli.cod_clie = tabconsultora(i).cod_clie
           AND cab.clie_oid_clie = cli.oid_clie
           AND cab.perd_oid_peri = lnoidperiodo
           AND cab.ind_oc = 1
           AND cab.tspa_oid_tipo_soli_pais = tslp.oid_tipo_soli_pais
           AND tslp.tsol_oid_tipo_soli = tsol.oid_tipo_soli
           AND tsol.ind_anul = 0
           AND tsol.ind_devo = 0
           AND cab.fec_fact IS NOT NULL;

        IF (lncontador > 0) THEN
          pscodigoretorno := '7';
          UPDATE zon_tmp_terri_consu
          SET IND_APLI = 'N',
              FEC_PROC = SYSDATE,
              COD_USUA_PROC = psUsuario,
              MEN_ERRO = 'Consultora de la fila '|| i ||' ya facturó'
          WHERE val_fila = tabconsultora(i).val_fila;
          continue;
        END IF;

        UPDATE zon_tmp_terri_consu
           SET cod_secc_dest = lscodigosecciondest,
               cod_zona_dest = lscodigozonadest,
               cod_regi_dest = lscodigoregiondest,
               cod_terr_orig = lncodigoterritorioorig,
               cod_secc_orig = lscodigoseccionorig,
               cod_zona_orig = lscodigozonaorig,
               cod_regi_orig = lscodigoregionorig,
               est_vali      = lsEstadoOK,
               FEC_PROC      = SYSDATE,
               COD_USUA_PROC = psUsuario,
               ind_apli      = 'N',
               MEN_ERRO      = null
         WHERE val_fila = tabconsultora(i).val_fila;

      END LOOP;

    END LOOP;
    CLOSE cconsultoras;

    /* Verificando que existan registros OK y que no se hayan aplicado la actualizacion */
    /*SELECT COUNT(1)
    INTO lncontador
    FROM zon_tmp_terri_consu
    where IND_APLI = 'N'
      and EST_VALI = lsEstadoOK;

    IF (lncontador > 0) THEN
       zon_pr_actua_modif_terri_consu(pscodigopais, pscodigomarca, pscodigocanal, pscodigoperiodo, psUsuario);
    end if;*/

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_MODIF_TERRI_CONSU: ' || ls_sqlerrm);
  END zon_pr_valid_modif_terri_consu;

  /***************************************************************************
  Descripcion       : Actualiza la Modificacion de Territorios a Consultoras

  Fecha Creacion    : 07/08/2009
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_actua_modif_terri_consu
  (
    pscodigopais    IN VARCHAR2,
    pscodigomarca   IN VARCHAR2,
    pscodigocanal   IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) IS
    TYPE typregtraclierreli IS RECORD(
      cod_clie           zon_tmp_terri_consu.cod_clie%TYPE,
      cod_terr_dest      zon_tmp_terri_consu.cod_terr_dest%TYPE,
      cod_secc_dest      zon_tmp_terri_consu.cod_secc_dest%TYPE,
      cod_zona_dest      zon_tmp_terri_consu.cod_zona_dest%TYPE,
      cod_regi_dest      zon_tmp_terri_consu.cod_regi_dest%TYPE,
      cod_terr_orig      zon_tmp_terri_consu.cod_terr_orig%TYPE,
      cod_secc_orig      zon_tmp_terri_consu.cod_secc_orig%TYPE,
      cod_zona_orig      zon_tmp_terri_consu.cod_zona_orig%TYPE,
      cod_regi_orig      zon_tmp_terri_consu.cod_regi_orig%TYPE,
      oid_terr_admi_ante NUMBER,
      oid_terr_admi_nuev NUMBER,
      oid_clie_unid_admi NUMBER,
      oid_clie           NUMBER,
      val_fila           zon_tmp_terri_consu.val_fila%TYPE,
      perd_oid_peri_ini  NUMBER  --se añade
      );
    TYPE typtabtraclierreli IS TABLE OF typregtraclierreli;
    tabtraclierreli typtabtraclierreli;
    lntmpoid        NUMBER;

    CURSOR ctraclierreli
    (
      oidpais  NUMBER,
      oidmarca NUMBER,
      oidcanal NUMBER
    ) IS
      SELECT con.cod_clie,
             con.cod_terr_dest,
             con.cod_secc_dest,
             con.cod_zona_dest,
             con.cod_regi_dest,
             con.cod_terr_orig,
             con.cod_secc_orig,
             con.cod_zona_orig,
             con.cod_regi_orig,
             cua.ztad_oid_terr_admi,
             (SELECT tra.oid_terr_admi
                FROM zon_terri_admin tra
               WHERE tra.pais_oid_pais = oidpais
                 AND tra.marc_oid_marc = oidmarca
                 AND tra.cana_oid_cana = oidcanal
                 AND tra.terr_oid_terr =
                     (SELECT ter.oid_terr
                        FROM zon_terri ter
                       WHERE ter.pais_oid_pais = oidpais
                         AND ter.cod_terr = con.cod_terr_dest)
                 AND tra.perd_oid_peri_fina IS NULL
                 AND tra.ind_borr = 0) oid_terr_admi_nuev,
             cua.oid_clie_unid_admi,
             cli.oid_clie,
             con.val_fila,
             cua.perd_oid_peri_ini  --se añade
        FROM zon_tmp_terri_consu   con,
             mae_clien             cli,
             mae_clien_unida_admin cua
       WHERE cli.cod_clie = con.cod_clie
         AND cli.pais_oid_pais = oidpais
         AND cli.oid_clie = cua.clie_oid_clie
         AND cua.ind_acti = 1
         AND cua.perd_oid_peri_fin IS NULL
         AND con.est_vali = 'OK'
         AND con.ind_apli = 'N';

    lnidpais    NUMBER;
    lnidmarca   NUMBER;
    lnidcanal   NUMBER;
    lnidperiodo NUMBER;

    lscodigoperiodoant seg_perio_corpo.cod_peri%TYPE;
    lnidperiodoant     NUMBER;
  BEGIN

    --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID PERIODO
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              lnidmarca,
                                                              lnidcanal);

    lscodigoperiodoant := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                 lnidpais,
                                                                 lnidmarca,
                                                                 lnidcanal,
                                                                 -1);

    lnidperiodoant := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodigoperiodoant,
                                                                 lnidmarca,
                                                                 lnidcanal);

    OPEN ctraclierreli(lnidpais,
                       lnidmarca,
                       lnidcanal);
    LOOP
      FETCH ctraclierreli BULK COLLECT
        INTO tabtraclierreli LIMIT w_filas;
      EXIT WHEN tabtraclierreli.count = 0;
      FOR i IN 1 .. tabtraclierreli.count
      LOOP
        --Se añade IF caso de consultoras con UA inicio mayor al periodo actual
       IF(tabtraclierreli(i).perd_oid_peri_ini>lnidperiodo) THEN
        UPDATE mae_clien_unida_admin
           SET ZTAD_OID_TERR_ADMI=tabtraclierreli(i).oid_terr_admi_nuev,
               fec_ulti_actu     = SYSDATE,
               fec_camb          = SYSDATE,
               usu_modi          = pscodigousuario
         WHERE oid_clie_unid_admi = tabtraclierreli(i).oid_clie_unid_admi;
       
       ELSE
       -- Finalizando relacion anterior
        UPDATE mae_clien_unida_admin
           SET ind_acti          = 0,
               perd_oid_peri_fin = lnidperiodoant,
               fec_ulti_actu     = SYSDATE,
               fec_camb          = SYSDATE,
               usu_modi          = pscodigousuario
         WHERE oid_clie_unid_admi = tabtraclierreli(i).oid_clie_unid_admi;

       END IF;
        --Actualizamos la direccion principal del cliente
        UPDATE mae_clien_direc
           SET terr_oid_terr =
               (SELECT terr_oid_terr
                  FROM zon_terri_admin
                 WHERE oid_terr_admi = tabtraclierreli(i).oid_terr_admi_nuev),
           cod_unid_geog =
           (select zv.orde_1||zv.orde_2||zv.orde_3||zv.orde_4 
              from zon_valor_estru_geopo zv
              where zv.oid_valo_estr_geop = 
              (select zt.vepo_oid_valo_estr_geop from zon_terri zt
              where zt.oid_terr = 
                             (SELECT terr_oid_terr
                                FROM zon_terri_admin
                               WHERE oid_terr_admi = tabtraclierreli(i).oid_terr_admi_nuev)
              )                  
           ),
           usu_modi = pscodigousuario,
           fec_ulti_actu =SYSDATE
         WHERE clie_oid_clie = tabtraclierreli(i).oid_clie
           AND ind_dire_ppal = '1'
           AND ind_elim = '0';
           
           
        --Se añade IF caso de consultoras con UA inicio mayor al periodo actual    
      IF(tabtraclierreli(i).perd_oid_peri_ini <= lnidperiodo) THEN
        --Eliminando la unidad Administrativa que tenga el mismo periodo de inicio
        --del cual se va a registar uno nuevo
        zon_pr_elimi_unida_admin(tabtraclierreli(i).oid_clie,
                                 lnidperiodo);

        -- Creando nueva relacion
        SELECT mae_cuad_seq.nextval INTO lntmpoid FROM dual;
        INSERT INTO mae_clien_unida_admin
          (oid_clie_unid_admi,
           clie_oid_clie,
           perd_oid_peri_ini,
           ztad_oid_terr_admi,
           ind_acti,
           usu_modi,
           fec_camb,
           usu_crea,
           fec_crea
           )
        VALUES
          (lntmpoid,
           tabtraclierreli(i).oid_clie,
           lnidperiodo,
           tabtraclierreli(i).oid_terr_admi_nuev,
           1,
           pscodigousuario,
           SYSDATE,
           pscodigousuario,
           SYSDATE);
       END IF;
        --Insertamos un registro en Territorio Consultoras
        INSERT INTO zon_histo_terri_consu
          (cod_pais,
           cod_clie,
           cod_terr_dest,
           cod_secc_dest,
           cod_zona_dest,
           cod_regi_dest,
           cod_terr_orig,
           cod_secc_orig,
           cod_zona_orig,
           cod_regi_orig,
           usu_proc,
           fec_proc,
           cod_peri)
        VALUES
          (pscodigopais,
           tabtraclierreli(i).cod_clie,
           tabtraclierreli(i).cod_terr_dest,
           tabtraclierreli(i).cod_secc_dest,
           tabtraclierreli(i).cod_zona_dest,
           tabtraclierreli(i).cod_regi_dest,
           tabtraclierreli(i).cod_terr_orig,
           tabtraclierreli(i).cod_secc_orig,
           tabtraclierreli(i).cod_zona_orig,
           tabtraclierreli(i).cod_regi_orig,
           pscodigousuario,
           SYSDATE,
           pscodigoperiodo);

        UPDATE zon_tmp_terri_consu X
        SET IND_APLI = 'S',
            X.COD_USUA_PROC = pscodigousuario
        WHERE X.VAL_FILA = tabtraclierreli(i).VAL_FILA;

      END LOOP;
    END LOOP;
    CLOSE ctraclierreli;

    -- Se vuelven a calcular los errores de eliminacion
    --ZON_PR_RECAL_ERROR_ELIMI;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ACTUA_MODIF_TERRI_CONSU: ' || ls_sqlerrm);
  END zon_pr_actua_modif_terri_consu;

  /***************************************************************************
  Descripcion       : Relaiza la validacion e insercion en el directorio
  Fecha Creacion    : 09/09/2010
  Autor             : Sergio Buchellli
  parametros
      psCodigoPais          codigo pais,
      psCodigoCargo         codigo cargo
      psCodigoCliente       codigo cliente
      psFechaIngreso        fecha ingreso
      psCodigoRegion        codigo region
      psCodigoZona          codigo zona
      psCodigoUsuario       codigo usuario
      psMsgRetorno          mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_valid_ingre_direc
  (
    pscodigopais    IN VARCHAR2,
    pscodigocargo   IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    psfechaingreso  IN VARCHAR2,
    pscodigoregion  IN VARCHAR2,
    pscodigozona    IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    psfila          IN VARCHAR2,
    psmsgretorno    OUT VARCHAR2
  ) IS
    regparam            zon_tipo_cargo%ROWTYPE;
    lsretono            VARCHAR2(100) := ' ';
    lnexistegerente     NUMBER;
    lncont              NUMBER;
    lscodigoestado      zon_estat_cargo.cod_esta_carg%TYPE;
    lscodigosubgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
    lsua                VARCHAR2(10);
    lnoid               NUMBER;
    psfilaaux           VARCHAR2(1);
  BEGIN

    SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

    IF (regparam.cod_tipo_carg = 'GR' OR regparam.cod_tipo_carg = 'GZ') THEN
      --SE VALIDA SI ES GERENTE
      SELECT COUNT(1)
        INTO lnexistegerente
        FROM mae_clien_tipo_subti a,
             mae_tipo_clien       b,
             mae_clien            c
       WHERE c.cod_clie = pscodigocliente
         AND a.clie_oid_clie = c.oid_clie
         AND b.cod_tipo_clie = '04' --GERENTE
         AND b.oid_tipo_clie = a.ticl_oid_tipo_clie;

      IF (lnexistegerente = 0) THEN
        psmsgretorno := '0_' || lsretono;
        RETURN;
      END IF;

    END IF;

    --SE VALIDA ESTADO ACTIVO O INACTIVO TEMPORAL SOLO SI ES PRIMER ELEMENTO
    IF (psfila IS NULL OR psfila = '1') THEN
      BEGIN

        SELECT x.ESCA_COD_ESTA_CARG
          INTO lscodigoestado
          FROM (SELECT rownum,
                       a.*
                  FROM zon_direc_venta_cabec a
                 WHERE a.cod_clie = pscodigocliente
                 ORDER BY a.FEC_REGI DESC) x
         WHERE x.cod_clie = pscodigocliente
           AND rownum = 1;

        IF (lscodigoestado = 'A' OR lscodigoestado = 'IT') THEN
          psmsgretorno := '1_' || lsretono;
          RETURN;
        END IF;

      EXCEPTION
        WHEN no_data_found THEN
          lscodigoestado := '';
      END;
    END IF;

    IF (regparam.val_tipo_unid_admi = 'Z') THEN
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsua
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozona;
    END IF;

    IF (regparam.val_tipo_unid_admi = 'R') THEN

      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsua
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregion;

    END IF;
    --grabamos en maestro directoio
    BEGIN
      INSERT INTO zon_direc_venta_cabec
        (cod_clie,
         TICA_COD_TIPO_CARG,
         FEC_REGI,
         TIOP_COD_TIPO_OPER,
         ESCA_COD_ESTA_CARG,
         USU_CREA,
         FEC_CREA,
         IND_ESTA)
      VALUES
        (pscodigocliente,
         pscodigocargo,
         to_date(psfechaingreso,
                 'dd/MM/yyyy'),
         'IN',
         'A',
         pscodigousuario,
         SYSDATE,
         'G');
    EXCEPTION
      WHEN OTHERS THEN
        NULL; --YA EXISTE CABECEREA

    END;

    --INSERTAMOS DETALLE
    BEGIN

      INSERT INTO zon_direc_venta_detal
        (COR_DIVE_DETA,
         cod_clie,
         TICA_COD_TIPO_CARG,
         DICA_FEC_REGI,
         TIOP_COD_TIPO_OPER,
         cod_subg,
         cod_regi,
         cod_zona,
         USU_CREA,
         FEC_CREA)
      VALUES
        (zon_seq_direc_venta_detal.nextval,
         pscodigocliente,
         pscodigocargo,
         to_date(psfechaingreso,
                 'dd/MM/yyyy'),
         'IN',
         substr(lsua,
                1,
                2),
         substr(lsua,
                3,
                2),
         substr(lsua,
                5,
                4),
         pscodigousuario,
         SYSDATE);

    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_INGRE_DIREC: ' || ls_sqlerrm);
  END zon_pr_valid_ingre_direc;

  /***************************************************************************
     Descripcion       : Relaiza la validacion e insercion en el directorio caso nombramiento
     Fecha Creacion    : 13/09/2010
     Autor             : Sergio Buchelli
     parametros
         psCodigoPais          codigo pais,
         psCodigoCargo         codigo cargo
         psCodigoCargoAnterior codigo cargo anterior
         psCodigoCliente       codigo cliente
         psFechaIngreso        fecha ingreso
         psCodigoRegion        codigo region
         psCodigoZona          codigo zona
         psCodigoUsuario       codigo usuario
         psMsgRetorno          mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_valid_nombr_direc
  (
    pscodigopais        IN VARCHAR2,
    pscodigocargo       IN VARCHAR2,
    pscodigocliente     IN VARCHAR2,
    psfechanombramiento IN VARCHAR2,
    pscodigoregion      IN VARCHAR2,
    pscodigozona        IN VARCHAR2,
    pscodigousuario     IN VARCHAR2,
    psfila              IN VARCHAR2,
    psmsgretorno        OUT VARCHAR2
  ) IS
    regparam            zon_tipo_cargo%ROWTYPE;
    regdirectorio       zon_direc_venta_cabec%ROWTYPE;
    lsretono            VARCHAR2(100) := ' ';
    lnexistegerente     NUMBER;
    lncont              NUMBER;
    lscodigoestado      zon_estat_cargo.cod_esta_carg%TYPE;
    lscodigosubgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
    lsua                VARCHAR2(10);
    lnoid               NUMBER;
  BEGIN

    SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

    IF (regparam.cod_tipo_carg = 'GR' OR regparam.cod_tipo_carg = 'GZ') THEN
      --SE VALIDA SI ES GERENTE
      SELECT COUNT(1)
        INTO lnexistegerente
        FROM mae_clien_tipo_subti a,
             mae_tipo_clien       b,
             mae_clien            c
       WHERE c.cod_clie = pscodigocliente
         AND a.clie_oid_clie = c.oid_clie
         AND b.cod_tipo_clie = '04' --GERENTE
         AND b.oid_tipo_clie = a.ticl_oid_tipo_clie;

      IF (lnexistegerente = 0) THEN
        psmsgretorno := '0_' || lsretono;
        RETURN;
      END IF;

    END IF;

    IF (regparam.val_tipo_unid_admi = 'Z') THEN
      --OBTENEMOS LA UA
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsua
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozona;
    END IF;

    IF (regparam.val_tipo_unid_admi = 'R') THEN
      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsua
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregion;

    END IF;
    --grabamos en maestro directoio
    BEGIN

      INSERT INTO zon_direc_venta_cabec
        (cod_clie,
         TICA_COD_TIPO_CARG,
         FEC_REGI,
         TIOP_COD_TIPO_OPER,
         ESCA_COD_ESTA_CARG,
         USU_CREA,
         FEC_CREA,
         IND_ESTA)
      VALUES
        (pscodigocliente,
         pscodigocargo,
         to_date(psfechanombramiento,
                 'dd/MM/yyyy'),
         'NM',
         'A',
         pscodigousuario,
         SYSDATE,
         'G');
    EXCEPTION
      WHEN OTHERS THEN
        NULL; --YA EXISTE CABECEREA

    END;

    --INSERTAMOS DETALLE
    BEGIN

      INSERT INTO zon_direc_venta_detal
        (COR_DIVE_DETA,
         cod_clie,
         TICA_COD_TIPO_CARG,
         DICA_FEC_REGI,
         TIOP_COD_TIPO_OPER,
         cod_subg,
         cod_regi,
         cod_zona,
         USU_CREA,
         FEC_CREA)
      VALUES
        (zon_seq_direc_venta_detal.nextval,
         pscodigocliente,
         pscodigocargo,
         to_date(psfechanombramiento,
                 'dd/MM/yyyy'),
         'NM',
         substr(lsua,
                1,
                2),
         substr(lsua,
                3,
                2),
         substr(lsua,
                5,
                4),
         pscodigousuario,
         SYSDATE);

    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_NOMBR_DIREC: ' || ls_sqlerrm);
  END zon_pr_valid_nombr_direc;

  /***************************************************************************
    Descripcion       : Relaiza la rotacion en el directorio
    Fecha Creacion    : 16/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais             codigo pais,
        psCodigoCargo            codigo cargo
        psCodigoCliente          codigo cliente
        psFechaRotacion          fecha rotacion
        psCodigoRegionAnt        codigo region anterior
        psCodigoRegionNuevo      codigo region nuevo
        psCodigoZonaAnt          codigo zona anterior
        psCodigoZonaNuevo        codigo zona nuevo
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_rotac_perso_direc
  (
    pscodigopais        IN VARCHAR2,
    pscodigocargo       IN VARCHAR2,
    pscodigocliente     IN VARCHAR2,
    psfecharotacion     IN VARCHAR2,
    pscodigoregionant   IN VARCHAR2,
    pscodigoregionnuevo IN VARCHAR2,
    pscodigozonaant     IN VARCHAR2,
    pscodigozonanuevo   IN VARCHAR2,
    pscodigousuario     IN VARCHAR2,
    psfila              IN VARCHAR2,
    psmsgretorno        OUT VARCHAR2
  ) IS
    regparam            zon_tipo_cargo%ROWTYPE;
    regdirectorio       zon_direc_venta_cabec%ROWTYPE;
    lsretono            VARCHAR2(100) := ' ';
    lnexistegerente     NUMBER;
    lncont              NUMBER;
    lscodigoestado      zon_estat_cargo.cod_esta_carg%TYPE;
    lscodigosubgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
    lsuaanterior        VARCHAR2(10);
    lsuanuevo           VARCHAR2(10);
    lnoid               NUMBER;
  BEGIN

    SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

    --obtenemos UA anterior
    IF (regparam.val_tipo_unid_admi = 'Z') THEN

      --OBTENEMOS LA UA
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsuaanterior
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozonaant; --psCodigoZona;
    END IF;

    IF (regparam.val_tipo_unid_admi = 'R') THEN

      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsuaanterior
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregionant; --psCodigoRegion;

    END IF;

    IF (regparam.val_tipo_unid_admi = 'Z') THEN

      --OBTENEMOS LA UA
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsuanuevo
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozonanuevo; --psCodigoZona;
    END IF;

    IF (regparam.val_tipo_unid_admi = 'R') THEN

      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsuanuevo
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregionnuevo; --psCodigoRegion;

    END IF;

    --grabamos en maestro directoio
    BEGIN
      INSERT INTO zon_direc_venta_cabec
        (cod_clie,
         TICA_COD_TIPO_CARG,
         FEC_REGI,
         TIOP_COD_TIPO_OPER,
         ESCA_COD_ESTA_CARG,
         USU_CREA,
         FEC_CREA,
         IND_ESTA)
      VALUES
        (pscodigocliente,
         pscodigocargo,
         to_date(psfecharotacion,
                 'dd/MM/yyyy'),
         'RO',
         'A',
         pscodigousuario,
         SYSDATE,
         'G');
    EXCEPTION
      WHEN OTHERS THEN
        NULL; --YA EXISTE CABECEREA

    END;

    --INSERTAMOS DETALLE
    BEGIN

      INSERT INTO zon_direc_venta_detal
        (COR_DIVE_DETA,
         cod_clie,
         TICA_COD_TIPO_CARG,
         DICA_FEC_REGI,
         TIOP_COD_TIPO_OPER,
         cod_subg,
         cod_regi,
         cod_zona,
         USU_CREA,
         FEC_CREA)
      VALUES
        (zon_seq_direc_venta_detal.nextval,
         pscodigocliente,
         pscodigocargo,
         to_date(psfecharotacion,
                 'dd/MM/yyyy'),
         'RO',
         substr(lsuanuevo,
                1,
                2),
         substr(lsuanuevo,
                3,
                2),
         substr(lsuanuevo,
                5,
                4),
         pscodigousuario,
         SYSDATE);

    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_ROTAC_PERSO_DIREC: ' || ls_sqlerrm);
  END zon_pr_rotac_perso_direc;

  /***************************************************************************
    Descripcion       : Relaiza la licencia en el directorio
    Fecha Creacion    : 20/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais                codigo pais,
        psCodigoCargo               codigo cargo
        psCodigoClienteReemplazado  codigo cliente
        psCodigoClienteReemplazo    codigo cliente reemplazo
        psFechaSalida               fecha salida
        psFechaRegreso              fecha regreso
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_licen_direc
  (
    pscodigopais               IN VARCHAR2,
    pscodigocargoreemplazado   IN VARCHAR2,
    pscodigolicencia           IN VARCHAR2,
    pscodigomotivolicencia     IN VARCHAR2,
    pscodigoclientereemplazado IN VARCHAR2,
    pscodigoclientereemplazo   IN VARCHAR2,
    pscodigocargoreemplazo     IN VARCHAR2,
    pscodigoregionreemplazado  IN VARCHAR2,
    pscodigozonareemplazado    IN VARCHAR2,
    pscodigoregionreemplazo    IN VARCHAR2,
    pscodigozonareemplazo      IN VARCHAR2,
    psfechasalida              IN VARCHAR2,
    psfecharegreso             IN VARCHAR2,
    pscodigousuario            IN VARCHAR2,
    psfila                     IN VARCHAR2,
    psmsgretorno               OUT VARCHAR2
  ) IS
    regparamreemplazado zon_tipo_cargo%ROWTYPE;
    regparamreemplazo   zon_tipo_cargo%ROWTYPE;
    regdirectorio       zon_direc_venta_cabec%ROWTYPE;
    lsretono            VARCHAR2(100) := ' ';
    lnexistegerente     NUMBER;
    lncont              NUMBER;
    lscodigoestado      zon_estat_cargo.cod_esta_carg%TYPE;
    lscodigosubgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
    lsuareemplazado     VARCHAR2(10);
    lsuareemplazo       VARCHAR2(10);
    lnoid               NUMBER;
  BEGIN

    SELECT *
      INTO regparamreemplazado
      FROM zon_tipo_cargo
     WHERE cod_tipo_carg = pscodigocargoreemplazado;

    SELECT *
      INTO regparamreemplazo
      FROM zon_tipo_cargo
     WHERE cod_tipo_carg = pscodigocargoreemplazo;

    --Cuando no es titular

    IF regparamreemplazado.val_tipo_unid_admi = 'Z' THEN

      --OBTENEMOS LA UA
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsuareemplazado
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozonareemplazado; --psCodigoZona;
    END IF;

    IF regparamreemplazado.val_tipo_unid_admi = 'R' THEN

      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsuareemplazado
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregionreemplazado; --psCodigoRegion;

    END IF;

    IF regparamreemplazo.val_tipo_unid_admi = 'Z' THEN

      --OBTENEMOS LA UA
      SELECT cod_subg_vent || b.cod_regi || a.cod_zona
        INTO lsuareemplazo
        FROM zon_zona            a,
             zon_regio           b,
             zon_sub_geren_venta c
       WHERE a.zorg_oid_regi = b.oid_regi
         AND c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND a.cod_zona = pscodigozonareemplazo; --psCodigoZona;
    END IF;

    IF regparamreemplazo.val_tipo_unid_admi = 'R' THEN

      --OBTENEMOS LA ua
      SELECT cod_subg_vent || b.cod_regi
        INTO lsuareemplazo
        FROM zon_regio           b,
             zon_sub_geren_venta c
       WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
         AND b.cod_regi = pscodigoregionreemplazo; --psCodigoRegion;

    END IF;

    --para el timpo de reemplazp no titular
    IF regparamreemplazo.val_titu = '0' THEN
      --grabamos en maestro directoio licencia reemplazo

      BEGIN
        INSERT INTO zon_direc_venta_cabec
          (cod_clie,
           TICA_COD_TIPO_CARG,
           FEC_REGI,
           TIOP_COD_TIPO_OPER,
           ESCA_COD_ESTA_CARG,
           MOTI_COD_MOTI_LICE,
           COD_CLIE_REEM,
           FEC_REGR,
           USU_CREA,
           FEC_CREA,
           IND_ESTA)
        VALUES
          (pscodigoclientereemplazado,
           pscodigocargoreemplazado,
           psfechasalida,
           'LI',
           'IT',
           pscodigomotivolicencia,
           pscodigoclientereemplazo,
           psfecharegreso,
           pscodigousuario,
           SYSDATE,
           'G');
      EXCEPTION
        WHEN OTHERS THEN
          NULL; --YA EXISTE CABECEREA
      END;

      BEGIN
        INSERT INTO zon_direc_venta_detal
          (COR_DIVE_DETA,
           cod_clie,
           TICA_COD_TIPO_CARG,
           DICA_FEC_REGI,
           TIOP_COD_TIPO_OPER,
           cod_subg,
           cod_regi,
           cod_zona,
           USU_CREA,
           FEC_CREA)
        VALUES
          (zon_seq_direc_venta_detal.nextval,
           pscodigoclientereemplazado,
           pscodigocargoreemplazado,
           psfechasalida,
           'LI',
           substr(lsuareemplazado,
                  1,
                  2),
           substr(lsuareemplazado,
                  3,
                  2),
           substr(lsuareemplazado,
                  5,
                  4),
           pscodigousuario,
           SYSDATE);

      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;

      BEGIN
        INSERT INTO zon_direc_venta_cabec
          (cod_clie,
           TICA_COD_TIPO_CARG,
           FEC_REGI,
           TIOP_COD_TIPO_OPER,
           ESCA_COD_ESTA_CARG,
           USU_CREA,
           FEC_CREA,
           IND_ESTA)
        VALUES
          (pscodigoclientereemplazo,
           pscodigocargoreemplazo,
           psfechasalida,
           'RZ',
           'A',
           pscodigousuario,
           SYSDATE,
           'G');
      EXCEPTION
        WHEN OTHERS THEN
          NULL; --YA EXISTE CABECEREA
      END;

      BEGIN
        INSERT INTO zon_direc_venta_detal
          (COR_DIVE_DETA,
           cod_clie,
           TICA_COD_TIPO_CARG,
           DICA_FEC_REGI,
           TIOP_COD_TIPO_OPER,
           cod_subg,
           cod_regi,
           cod_zona,
           USU_CREA,
           FEC_CREA)
        VALUES
          (zon_seq_direc_venta_detal.nextval,
           pscodigoclientereemplazo,
           pscodigocargoreemplazo,
           psfechasalida,
           'RZ',
           substr(lsuareemplazo,
                  1,
                  2),
           substr(lsuareemplazo,
                  3,
                  2),
           substr(lsuareemplazo,
                  5,
                  4),
           pscodigousuario,
           SYSDATE);

      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_LICEN_DIREC: ' || ls_sqlerrm);
  END zon_pr_licen_direc;

  /***************************************************************************
    Descripcion       : Realiza la reactivacion en el directorio
    Fecha Creacion    : 20/09/2010
    Fecha Modificacion: 23/09/2013
    Autor             : Sergio Buchelli
                      : Juan Altamirano
    parametros
        psCodigoPais                codigo pais,
        psFechaRegreso              fecha regreso
        psCodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen_direc
  (pscodigopais               IN VARCHAR2,
    psfecharegreso             IN VARCHAR2,
   pscodigousuario            IN VARCHAR2) IS

    CURSOR clistLicencias IS
           select c.cod_clie, to_char(c.fec_regi,'DD/MM/YYYY'),
                  TO_CHAR(c.fec_regr,'DD/MM/YYYY'), d.cod_regi, d.cod_zona,
                  c.tica_cod_tipo_carg, c.tiop_cod_tipo_oper,
                  c.esca_cod_esta_carg, c.moti_cod_moti_lice,
                  tc.val_tipo_unid_admi, tc.val_titu,
                  TO_CHAR(c.fec_vent,'DD/MM/YYYY'),
                  TO_CHAR(c.fec_fact,'DD/MM/YYYY')
           from zon_direc_venta_cabec c,
                zon_direc_venta_detal d,
                zon_tipo_cargo tc
           where c.cor_dire_vent = d.dica_cor_dire_vent
                 and c.cod_clie = d.cod_clie
                 and c.pais_cod_pais = d.pais_cod_pais
                 and c.fec_regi = d.dica_fec_regi
                 and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                 and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                 and c.cam_proc = d.dica_cam_proc
                 and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                 and c.esca_cod_esta_carg = 'IT'
                 and c.ind_esta = 'A'
                 and c.est_regi = 1
				 and trunc(c.fec_regr) = to_date(psfecharegreso,'DD/MM/YYYY')+1;

    lsua                VARCHAR2(50);
    lnOidCabec          NUMBER;

    vsCodGere           zon_direc_venta_cabec.cod_clie%TYPE;
    vsfechaRegi         VARCHAR2(10);
    vsfechaRegr         VARCHAR2(10);
    vsfechaVent         VARCHAR2(10);
    vsfechaFact         VARCHAR2(10);
    vsCodRegi           VARCHAR2(2);
    vsCodZona           VARCHAR2(4);
    vsTipCarg           zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsTipOper           zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE;
    vsEsta              zon_direc_venta_cabec.esca_cod_esta_carg%TYPE;
    vsCodMoti           zon_direc_venta_cabec.moti_cod_moti_lice%TYPE;
    vsTipUni            zon_tipo_cargo.val_tipo_unid_admi%TYPE;
    vsTitu              zon_tipo_cargo.val_titu%TYPE;

    regDirVen           NUMBER;
    vsClieReem          zon_direc_venta_cabec.cod_clie%TYPE;
    vsCargoReem         zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsCorDireVenReem    NUMBER;
    vsOidHG             NUMBER;
    vsPeriodoDesdeHG    NUMBER;
    vsPeriodoHastaHG    NUMBER;
    vsRegHG             NUMBER;
    vnFecRegiFin        DATE;
  BEGIN

    OPEN clistLicencias;
      LOOP FETCH clistLicencias INTO vsCodGere,vsfechaRegi,vsfechaRegr,
                                     vsCodRegi,vsCodZona,vsTipCarg,
                                     vsTipOper,vsEsta,vsCodMoti,vsTipUni,vsTitu,vsfechaVent,vsfechaFact;
           EXIT WHEN clistLicencias%NOTFOUND;

           --IF(vsTitu = '1') THEN
      --obtenemos UA
              IF (vsTipUni = 'Z') THEN
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO lsua
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
                   AND a.cod_zona = vsCodZona; --psCodigoZona;

                --VERIFICAMOS SI HAY REEMPLAZO PARA EL QUE SE FUE DE LICENCIA
                BEGIN
                  SELECT c.cor_dire_vent, c.cod_clie,c.tica_cod_tipo_carg
                         INTO vsCorDireVenReem, vsClieReem, vsCargoReem
                  FROM  zon_direc_venta_cabec c, zon_direc_venta_detal d
                  where c.cor_dire_vent = d.dica_cor_dire_vent
                   and c.cod_clie = d.cod_clie
                   and c.pais_cod_pais = d.pais_cod_pais
                   and c.fec_regi = d.dica_fec_regi
                   and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                   and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                   and c.cam_proc = d.dica_cam_proc
                   and c.tica_cod_tipo_carg = vsTipCarg
                   and c.esca_cod_esta_carg = 'A'
                   and c.ind_esta = 'A'
                   and c.est_regi = 1
                   and d.cod_subg = substr(lsua,1,2)
                   and d.cod_regi = substr(lsua,3,2)
                   and d.cod_zona = substr(lsua,5,4);

                 EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                     vsCorDireVenReem:= NULL;
                     vsClieReem := NULL;
                     vsCargoReem := NULL;
                 END;
      END IF;

              IF (vsTipUni = 'R') THEN
        SELECT cod_subg_vent || b.cod_regi
          INTO lsua
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
                   AND b.cod_regi = vsCodRegi; --psCodigoRegion;

                 --VERIFICAMOS SI HAY REEMPLAZO PARA EL QUE SE FUE DE LICENCIA
                 BEGIN
                  SELECT c.cor_dire_vent, c.cod_clie,c.tica_cod_tipo_carg
                         INTO vsCorDireVenReem, vsClieReem, vsCargoReem
                  FROM  zon_direc_venta_cabec c, zon_direc_venta_detal d
                  where c.cor_dire_vent = d.dica_cor_dire_vent
                   and c.cod_clie = d.cod_clie
                   and c.pais_cod_pais = d.pais_cod_pais
                   and c.fec_regi = d.dica_fec_regi
                   and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                   and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                   and c.cam_proc = d.dica_cam_proc
                   and c.tica_cod_tipo_carg = vsTipCarg
                   and c.esca_cod_esta_carg = 'A'
                   and c.ind_esta = 'A'
                   and c.est_regi = 1
                   and d.cod_subg = substr(lsua,1,2)
                   and d.cod_regi = substr(lsua,3,2)
                   and d.cod_zona is NULL;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vsCorDireVenReem:= NULL;
                    vsClieReem := NULL;
                    vsCargoReem := NULL;
                END;
              END IF;

              IF(vsCorDireVenReem IS NOT NULL)THEN --HAY REEMPLAZO
                 dbms_output.put_line('SI HAY REEMPLAZO');

                 IF(vsCargoReem = 'GZ' OR vsCargoReem = 'GR') THEN
                     --BUSCA EN EL HISTORICO AL REEMPLAZANTE
                     BEGIN
                       SELECT id, perioDesd INTO vsOidHG, vsPeriodoDesdeHG
                       FROM
                         (SELECT ZHG.OID_HIST_GERE id, ZHG.PERD_OID_PERI_DESD perioDesd
                          FROM ZON_HISTO_GEREN ZHG
                         WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                           AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                           AND ZHG.GERE = vsClieReem
                           AND ZHG.UA = lsua
                           AND ZHG.FEC_HAST IS NULL
                           AND ZHG.PERD_OID_PERI_HAST IS NULL
                           ORDER BY ZHG.OID_HIST_GERE DESC)
                       WHERE ROWNUM = 1;

                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vsOidHG := NULL;
                         vsPeriodoDesdeHG := NULL;
                     END;

                     IF(vsOidHG IS NOT NULL) THEN
                        --Se obtiene la campaña a la qe hace referencia la fechaRegreso.
                        vsPeriodoHastaHG := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigopais,'T','VD',TO_DATE(vsfechaRegr,'DD/MM/YYYY')));
                        --Se valida si es igual a la campaña de inicio(PeriodoDesde)
                        IF(vsPeriodoHastaHG = vsPeriodoDesdeHG) THEN
                          --Se borra el registro de ZON_HISTO_GEREN
                          DELETE FROM  ZON_HISTO_GEREN
                            WHERE  MARC_OID_MARC  = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                AND CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                AND UA = lsua
                                AND perd_oid_peri_desd = vsPeriodoDesdeHG;

                          --Se inserta al que se fue de licencia en el ZHG
                          INSERT INTO zon_histo_geren
                              (oid_hist_gere,
                               marc_oid_marc,
                               cana_oid_cana,
                               ua, gere,
                               fec_desd, fec_hast,
                               pais_oid_pais,
                               perd_oid_peri_desd,
                               perd_oid_peri_hast,
                               usu_modi, fec_modi,
                               cod_subg,
                               cod_regi,
                               cod_zona)
                          VALUES
                            (zon_hger_seq.nextval,
                             gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                             gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                             lsua, vsCodGere,
                             TO_DATE(vsfechaRegr,'DD/MM/YYYY'), NULL,
                             gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                             vsPeriodoHastaHG,
                             NULL,
                             pscodigousuario, SYSDATE,
                             substr(lsua,1,2),
                             substr(lsua,3,2),
                             substr(lsua,5,4));


                        END IF;

                        --Se actualiza el registro de ZON_HISTO_GEREN
                        UPDATE ZON_HISTO_GEREN ZHG
                            SET ZHG.FEC_HAST = TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                            ZHG.PERD_OID_PERI_HAST = vsPeriodoHastaHG-1,
                                ZHG.USU_MODI = pscodigousuario,
                            ZHG.FEC_MODI = SYSDATE,
                            ZHG.COD_SUBG = SUBSTR(lsua,1,2),
                            ZHG.COD_REGI = SUBSTR(lsua,3,2),
                            ZHG.COD_ZONA = SUBSTR(lsua,5,4)
                        WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                              AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                              AND ZHG.GERE = vsClieReem
                              AND ZHG.UA = lsua
                              AND ZHG.FEC_HAST IS NULL
                              AND ZHG.PERD_OID_PERI_HAST IS NULL;

                       IF(vsTipCarg = 'GZ') THEN
                          UPDATE zon_zona z
                           SET z.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                             z.fec_ulti_actu = SYSDATE
                          WHERE z.cod_zona = vsCodZona;

                       ELSE--GR
                          UPDATE zon_regio r
                           SET r.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                             r.fec_ulti_actu = SYSDATE
                          WHERE r.cod_regi = vsCodRegi;

                       END IF;

                   END IF;

                 END IF;--FIN ES GERENTE

                       UPDATE ZON_DIREC_VENTA_CABEC
                         SET ESCA_COD_ESTA_CARG = 'I',
                             USU_MODI      = pscodigousuario,
                       FEC_REGR      = TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                       FEC_MODI      = SYSDATE,
                       IND_NOVE      = 'A'
                       WHERE COD_CLIE = vsClieReem
                           AND TICA_COD_TIPO_CARG = vsCargoReem
                           AND ESCA_COD_ESTA_CARG = 'A'
                           AND COR_DIRE_VENT = vsCorDireVenReem;

              ELSE--NO HAY REEMPLAZO
                  dbms_output.put_line('NO HAY REEMPLAZO');
                  IF(vsTipCarg = 'GZ' OR vsTipCarg = 'GR') THEN
                      SELECT COUNT(1) INTO vsRegHG
                        FROM ZON_HISTO_GEREN ZHG
                      WHERE ZHG.UA = lsua
                        AND ZHG.FEC_HAST IS NULL
                        AND ZHG.PERD_OID_PERI_HAST IS NULL;

                      IF(vsRegHG > 0)THEN--SI HAY ALGUIEN EN EL REGISTRO, SE ELIMINA
                        DELETE FROM  ZON_HISTO_GEREN ZHG
                        WHERE  ZHG.MARC_OID_MARC  = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                            AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                            AND ZHG.UA = lsua
                            AND ZHG.FEC_HAST is null
                            AND ZHG.PERD_OID_PERI_HAST is null;

                      END IF;

                      --SE REINCORPORA AL REGISTRO AL QUE SE FUE DE LICENCIA.
      INSERT INTO zon_histo_geren
        (oid_hist_gere,
         marc_oid_marc,
         cana_oid_cana,
                         ua, gere,
                         fec_desd, fec_hast,
         pais_oid_pais,
                         perd_oid_peri_desd,
                         perd_oid_peri_hast,
                         usu_modi, fec_modi,
                         cod_subg,
                         cod_regi,
                         cod_zona)
      VALUES
        (zon_hger_seq.nextval,
         gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
         gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                         lsua, vsCodGere,
                         TO_DATE(vsfechaRegr,'DD/MM/YYYY') , NULL,
         gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                         GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigopais,'T','VD',TO_DATE(vsfechaRegr,'DD/MM/YYYY'))),
                         NULL,
                         pscodigousuario, SYSDATE,
                         substr(lsua,1,2),
                         substr(lsua,3,2),
                         substr(lsua,5,4));


                     IF(vsTipCarg = 'GZ') THEN
        UPDATE zon_zona z
                         SET z.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
               z.fec_ulti_actu = SYSDATE
                        WHERE z.cod_zona = vsCodZona;


                     ELSE--GR
                        UPDATE zon_regio r
                         SET r.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                           r.fec_ulti_actu = SYSDATE
                        WHERE r.cod_regi = vsCodRegi;


      END IF;

                 END IF;--FIN ES GERENTE.


              END IF;--FIN CONDICION REEMPLAZO


             --SE BUSCA AL REEMPLAZADO COMO INACTIVO TEMPORAL
             SELECT COUNT(1) INTO regDirVen
                    FROM ZON_DIREC_VENTA_CABEC
             WHERE  COD_CLIE = vsCodGere
                   AND ESCA_COD_ESTA_CARG = 'IT'
                 AND TICA_COD_TIPO_CARG = vsTipCarg
             ORDER BY FEC_REGI DESC;

             vnFecRegiFin := TO_DATE(vsfechaRegr,'DD/MM/YYYY')-1;
             IF(regDirVen > 0) THEN--SE CAMBIA EL ESTADO
                 UPDATE ZON_DIREC_VENTA_CABEC
           SET ESCA_COD_ESTA_CARG = 'I',
                       USU_MODI      = pscodigousuario,
                       FEC_MODI      = SYSDATE,
                     IND_NOVE      = 'A',
                     FEC_REGI_FIN  = vnFecRegiFin
                 WHERE COD_CLIE = vsCodGere
                   AND TICA_COD_TIPO_CARG = vsTipCarg
                   AND TIOP_COD_TIPO_OPER = vsTipOper
                   AND ESCA_COD_ESTA_CARG = vsEsta;
             END IF;

             --Grabamos en maestro directoio  reactivacion licencia
             lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;

             --INSERTAMOS CABECERA reemplazado
               INSERT INTO ZON_DIREC_VENTA_CABEC
                (PAIS_COD_PAIS,
                 TIOP_COD_TIPO_OPER,
           TICA_COD_TIPO_CARG,
                 COD_CLIE,
           FEC_REGI,
                 CAM_PROC,
           ESCA_COD_ESTA_CARG,
           MOTI_COD_MOTI_LICE,
           COD_CLIE_REEM,
                 FEC_REGR,
                 IND_ESTA,
           USU_CREA,
                 FEC_CREA,
                 EST_REGI,
                 COR_DIRE_VENT,--CORRELATIVO DE CABECERA
                 FEC_VENT,
                 FEC_FACT,
                 IND_NOVE)
        VALUES
                (pscodigopais,
           'RA',
                 vsTipCarg,
                 vsCodGere,
                 TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigopais, 'T', 'VD', TO_DATE(vsfechaRegr,'DD/MM/YYYY')),
                 'A',
                 vsCodMoti,
                 NULL,--CODIGO REEMPLAZO
                 NULL,
           'A',
                 'ADMIN',
                 SYSDATE,
                 1,
                 lnOidCabec,--CORRELATIVO DE CABECERA
                 TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                 TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                 'A');

      --INSERTAMOS DETALLE reemplazado

            INSERT INTO ZON_DIREC_VENTA_DETAL
              (PAIS_COD_PAIS,
               TIOP_COD_TIPO_OPER,
           TICA_COD_TIPO_CARG,
               COD_CLIE,
           DICA_FEC_REGI,
               DICA_CAM_PROC,
               COR_DIVE_DETA,--CORRELATIVO DE DETALLE
               COD_SUBG,
               COD_REGI,
               COD_ZONA,
           USU_CREA,
               FEC_CREA,
               EST_REGI,
               DICA_COR_DIRE_VENT)--CORRELATIVO DE CABECERA
        VALUES
              (pscodigopais,
           'RA',
               vsTipCarg,
               vsCodGere,
               TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
               GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigopais, 'T', 'VD', TO_DATE(vsfechaRegr,'DD/MM/YYYY')),
               ZON_SEQ_DIREC_VENTA_DETAL.nextval,--CORRELATIVO DE DETALLE
               substr(lsua,1,2),
               substr(lsua,3,2),
               substr(lsua,5,4),
               'ADMIN',
               SYSDATE,
                  1,
               lnOidCabec);  --CORRELATIVO DE CABECERA

       --END IF;--fin titu

      END LOOP;

    CLOSE clistLicencias;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR ZON_PR_REACT_LICEN_DIREC: ' || ls_sqlerrm);
  END zon_pr_react_licen_direc;

  /***************************************************************************
    Descripcion       : Realiza la reactivacion en el directorio para fox
    Fecha Creacion    : 06/01/2013
    Autor             : Juan Altamirano
    parametros
        psCodigoPais             codigo pais,
        psFechaRegreso           fecha regreso
        psCodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen_direc_fox
   (pscodigopais               IN VARCHAR2,
   psfecharegreso             IN VARCHAR2,
   pscodigousuario            IN VARCHAR2) IS

    CURSOR clistLicencias IS
           select c.cod_clie, to_char(c.fec_regi,'DD/MM/YYYY'),
                  TO_CHAR(c.fec_regr,'DD/MM/YYYY'), d.cod_regi, d.cod_zona,
                  c.tica_cod_tipo_carg, c.tiop_cod_tipo_oper,
                  c.esca_cod_esta_carg, c.moti_cod_moti_lice,
                  tc.val_tipo_unid_admi, tc.val_titu,
                  TO_CHAR(c.fec_vent,'DD/MM/YYYY'),
                  TO_CHAR(c.fec_fact,'DD/MM/YYYY')
           from zon_direc_venta_cabec c,
                zon_direc_venta_detal d,
                zon_tipo_cargo tc
           where c.cor_dire_vent = d.dica_cor_dire_vent
                 and c.cod_clie = d.cod_clie
                 and c.pais_cod_pais = d.pais_cod_pais
                 and c.fec_regi = d.dica_fec_regi
                 and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                 and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                 and c.cam_proc = d.dica_cam_proc
                 and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                 and c.esca_cod_esta_carg = 'IT'
                 and c.ind_esta = 'A'
                 and c.est_regi = 1
				 and trunc(c.fec_regr) = to_date(psfecharegreso,'DD/MM/YYYY')+1;

    lsua                VARCHAR2(50);
    lnOidCabec          NUMBER;
    lnOidDetal          NUMBER;
    vsCodGere           zon_direc_venta_cabec.cod_clie%TYPE;
    vsfechaRegi         VARCHAR2(10);
    vsfechaRegr         VARCHAR2(10);
    vsfechaVent         VARCHAR2(10);
    vsfechaFact         VARCHAR2(10);
    vsCodRegi           VARCHAR2(2);
    vsCodZona           VARCHAR2(4);
    vsTipCarg           zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsTipOper           zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE;
    vsEsta              zon_direc_venta_cabec.esca_cod_esta_carg%TYPE;
    vsCodMoti           zon_direc_venta_cabec.moti_cod_moti_lice%TYPE;
    vsTipUni            zon_tipo_cargo.val_tipo_unid_admi%TYPE;
    vsTitu              zon_tipo_cargo.val_titu%TYPE;

    regDirVen           NUMBER;
    vsClieReem          zon_direc_venta_cabec.cod_clie%TYPE;
    vsCargoReem         zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsCorDireVenReem    NUMBER;
    vsOidHG             NUMBER;
    vsPeriodoDesdeHG    NUMBER;
    vsPeriodoHastaHG    NUMBER;
    vsRegHG             NUMBER;
    vnFecRegiFin        DATE;
  BEGIN

    OPEN clistLicencias;
      LOOP FETCH clistLicencias INTO vsCodGere,vsfechaRegi,vsfechaRegr,
                                     vsCodRegi,vsCodZona,vsTipCarg,
                                     vsTipOper,vsEsta,vsCodMoti,vsTipUni,vsTitu,vsfechaVent,vsfechaFact;
           EXIT WHEN clistLicencias%NOTFOUND;
           --obtenemos UA
              IF (vsTipUni = 'Z') THEN
                  SELECT '01' || b.cod_regi || a.cod_zona
                    INTO lsua
                    FROM zon_direc_zona            a,
                         zon_direc_regio           b
                   WHERE a.regi_cod_regi = b.cod_regi
                     AND a.cod_zona = vsCodZona
                     AND a.pais_cod_pais = b.pais_cod_pais
                     AND a.pais_cod_pais = pscodigopais;

                  --VERIFICAMOS SI HAY REEMPLAZO PARA EL QUE SE FUE DE LICENCIA
                  BEGIN
                    SELECT c.cor_dire_vent, c.cod_clie,c.tica_cod_tipo_carg
                           INTO vsCorDireVenReem, vsClieReem, vsCargoReem
                    FROM  zon_direc_venta_cabec c, zon_direc_venta_detal d
                    where c.cor_dire_vent = d.dica_cor_dire_vent
                     and c.cod_clie = d.cod_clie
                     and c.pais_cod_pais = d.pais_cod_pais
                     and c.fec_regi = d.dica_fec_regi
                     and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                     and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                     and c.cam_proc = d.dica_cam_proc
                     and c.tica_cod_tipo_carg = vsTipCarg
                     and c.esca_cod_esta_carg = 'A'
                     and c.ind_esta = 'A'
                     and c.est_regi = 1
                     and d.cod_subg = substr(lsua,1,2)
                     and d.cod_regi = substr(lsua,3,2)
                     and d.cod_zona = substr(lsua,5,4);

                   EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                       vsCorDireVenReem:= NULL;
                       vsClieReem := NULL;
                       vsCargoReem := NULL;
                   END;
              END IF;

              IF (vsTipUni = 'R') THEN
                SELECT '01' || b.cod_regi
                  INTO lsua
                  FROM zon_direc_regio b
                 WHERE b.cod_regi = vsCodRegi
                   AND b.pais_cod_pais = pscodigopais;

                 --VERIFICAMOS SI HAY REEMPLAZO PARA EL QUE SE FUE DE LICENCIA
                 BEGIN
                  SELECT c.cor_dire_vent, c.cod_clie,c.tica_cod_tipo_carg
                         INTO vsCorDireVenReem, vsClieReem, vsCargoReem
                  FROM  zon_direc_venta_cabec c, zon_direc_venta_detal d
                  where c.cor_dire_vent = d.dica_cor_dire_vent
                   and c.cod_clie = d.cod_clie
                   and c.pais_cod_pais = d.pais_cod_pais
                   and c.fec_regi = d.dica_fec_regi
                   and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                   and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                   and c.cam_proc = d.dica_cam_proc
                   and c.tica_cod_tipo_carg = vsTipCarg
                   and c.esca_cod_esta_carg = 'A'
                   and c.ind_esta = 'A'
                   and c.est_regi = 1
                   and d.cod_subg = substr(lsua,1,2)
                   and d.cod_regi = substr(lsua,3,2)
                   and d.cod_zona is NULL;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vsCorDireVenReem:= NULL;
                    vsClieReem := NULL;
                    vsCargoReem := NULL;
                END;
              END IF;

              IF(vsCorDireVenReem IS NOT NULL)THEN --HAY REEMPLAZO
                 dbms_output.put_line('SI HAY REEMPLAZO');

                 IF(vsCargoReem = 'GZ' OR vsCargoReem = 'GR') THEN
                     --BUSCA EN EL HISTORICO AL REEMPLAZANTE
                     BEGIN
                       SELECT id, perioDesd
                       INTO vsOidHG, vsPeriodoDesdeHG
                       FROM
                         (SELECT ZDHG.COR_HIST_GERE id, ZDHG.CAM_DESD perioDesd
                          FROM ZON_DIREC_HISTO_GEREN ZDHG
                         WHERE ZDHG.CLIE_COD_CLIE = vsClieReem
                           AND ZDHG.UNI_ADMI = lsua
                           AND ZDHG.FEC_HAST IS NULL
                           AND ZDHG.CAM_HAST IS NULL
                           ORDER BY ZDHG.COR_HIST_GERE DESC)
                       WHERE ROWNUM = 1;

                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vsOidHG := NULL;
                         vsPeriodoDesdeHG := NULL;
                     END;

                     IF(vsOidHG IS NOT NULL) THEN
                        --Se obtiene la campaña a la qe hace referencia la fechaRegreso.
                        vsPeriodoHastaHG := GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(pscodigopais,vsfechaRegr);
                        --Se valida si es igual a la campaña de inicio(PeriodoDesde)
                        IF(vsPeriodoHastaHG = vsPeriodoDesdeHG) THEN
                          --Se borra el registro de ZON_HISTO_GEREN
                          DELETE FROM  ZON_DIREC_HISTO_GEREN
                            WHERE UNI_ADMI = lsua
                                AND CAM_DESD = vsPeriodoDesdeHG;

                          --Se inserta al que se fue de licencia en el ZHG
                          INSERT INTO ZON_DIREC_HISTO_GEREN
                              (cor_hist_gere,
                               pais_cod_pais,
                               uni_admi,
                               clie_cod_clie,
                               fec_desd,
                               fec_hast,
                               cam_desd,
                               cam_hast,
                               usu_crea,
                               fec_crea,
                               regi_cod_regi,
                               zona_cod_zona)
                          VALUES
                            (zon_direc_hger_seq.nextval,
                             pscodigopais,
                             lsua,
                             vsCodGere,
                             TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                             NULL,
                             vsPeriodoDesdeHG,
                             vsPeriodoHastaHG,
                             pscodigousuario,
                             SYSDATE,
                             substr(lsua,3,2),
                             substr(lsua,5,4));
                        END IF;

                        --Se actualiza el registro de ZON_HISTO_GEREN
                        UPDATE ZON_DIREC_HISTO_GEREN ZDHG
                            SET ZDHG.FEC_HAST = TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                                ZDHG.CAM_HAST = vsPeriodoHastaHG-1,
                                ZDHG.USU_MODI = pscodigousuario,
                            ZDHG.FEC_MODI = SYSDATE,
                            ZDHG.REGI_COD_REGI = SUBSTR(lsua,3,2),
                            ZDHG.ZONA_COD_ZONA = SUBSTR(lsua,5,4)
                        WHERE ZDHG.CLIE_COD_CLIE = vsClieReem
                              AND ZDHG.UNI_ADMI = lsua
                              AND ZDHG.FEC_HAST IS NULL
                              AND ZDHG.CAM_HAST IS NULL;

                       IF(vsTipCarg = 'GZ') THEN
                          UPDATE zon_direc_zona z
                           SET z.clie_cod_clie = vsCodGere,
                               z.fec_modi = SYSDATE,
                               z.usu_modi = pscodigousuario
                          WHERE z.pais_cod_pais = pscodigopais
                               and z.regi_cod_regi = vsCodRegi
                               and z.cod_zona = vsCodZona;

                       ELSE--GR
                          UPDATE zon_direc_regio r
                           SET r.clie_cod_clie = vsCodGere,
                             r.fec_modi = SYSDATE,
                             r.usu_modi = pscodigousuario
                          WHERE r.pais_cod_pais = pscodigopais
                            and r.cod_regi = vsCodRegi;

                       END IF;

                   END IF;

                 END IF;--FIN ES GERENTE

                 UPDATE ZON_DIREC_VENTA_CABEC
                   SET ESCA_COD_ESTA_CARG = 'I',
                       USU_MODI      = pscodigousuario,
                       FEC_REGR      = TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                       FEC_MODI      = SYSDATE,
                       IND_NOVE      = 'A'
                 WHERE COD_CLIE = vsClieReem
                     AND TICA_COD_TIPO_CARG = vsCargoReem
                     AND ESCA_COD_ESTA_CARG = 'A'
                     AND COR_DIRE_VENT = vsCorDireVenReem;

              ELSE--NO HAY REEMPLAZO
                  dbms_output.put_line('NO HAY REEMPLAZO');
                  IF(vsTipCarg = 'GZ' OR vsTipCarg = 'GR') THEN
                      SELECT COUNT(1) INTO vsRegHG
                        FROM ZON_DIREC_HISTO_GEREN ZDHG
                      WHERE ZDHG.UNI_ADMI = lsua
                        AND ZDHG.FEC_HAST IS NULL
                        AND ZDHG.CAM_HAST IS NULL;

                      IF(vsRegHG > 0)THEN--SI HAY ALGUIEN EN EL REGISTRO, SE ELIMINA
                        DELETE FROM  ZON_DIREC_HISTO_GEREN ZDHG
                        WHERE  ZDHG.UNI_ADMI = lsua
                            AND ZDHG.FEC_HAST is null
                            AND ZDHG.CAM_HAST is null;

                      END IF;

                      --SE REINCORPORA AL REGISTRO AL QUE SE FUE DE LICENCIA.
                      INSERT INTO zon_direc_histo_geren
                        (cor_hist_gere,
                         pais_cod_pais,
                         uni_admi,
                         clie_cod_clie,
                         fec_desd,
                         fec_hast,
                         cam_desd,
                         cam_hast,
                         usu_crea,
                         fec_crea,
                         regi_cod_regi,
                         zona_cod_zona)
                      VALUES
                        (zon_direc_hger_seq.nextval,
                         pscodigopais,
                         lsua,
                         vsCodGere,
                         TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                         NULL,
                         GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(pscodigopais,vsfechaRegr),
                         NULL,
                         pscodigousuario,
                         SYSDATE,
                         substr(lsua,3,2),
                         substr(lsua,5,4));


                     IF(vsTipCarg = 'GZ') THEN
                       UPDATE zon_direc_zona z
                           SET z.clie_cod_clie = vsCodGere,
                               z.fec_modi = SYSDATE,
                               z.usu_modi = pscodigousuario
                          WHERE z.pais_cod_pais = pscodigopais
                               and z.regi_cod_regi = vsCodRegi
                               and z.cod_zona = vsCodZona;


                     ELSE--GR
                        UPDATE zon_direc_regio r
                         SET r.clie_cod_clie = vsCodGere,
                             r.fec_modi = SYSDATE,
                             r.usu_modi = pscodigousuario
                        WHERE r.pais_cod_pais = pscodigopais
                            and r.cod_regi = vsCodRegi;


                    END IF;

                 END IF;--FIN ES GERENTE.


              END IF;--FIN CONDICION REEMPLAZO


             --SE BUSCA AL REEMPLAZADO COMO INACTIVO TEMPORAL
             SELECT COUNT(1) INTO regDirVen
                    FROM ZON_DIREC_VENTA_CABEC
             WHERE  COD_CLIE = vsCodGere
                   AND ESCA_COD_ESTA_CARG = 'IT'
                 AND TICA_COD_TIPO_CARG = vsTipCarg
             ORDER BY FEC_REGI DESC;

             vnFecRegiFin := TO_DATE(vsfechaRegr,'DD/MM/YYYY')-1;
             IF(regDirVen > 0) THEN--SE CAMBIA EL ESTADO
                 UPDATE ZON_DIREC_VENTA_CABEC
                 SET ESCA_COD_ESTA_CARG = 'I',
                     USU_MODI      = pscodigousuario,
                     FEC_MODI      = SYSDATE,
                     IND_NOVE      = 'A',
                     FEC_REGI_FIN  = vnFecRegiFin
                 WHERE COD_CLIE = vsCodGere
                   AND TICA_COD_TIPO_CARG = vsTipCarg
                   AND TIOP_COD_TIPO_OPER = vsTipOper
                   AND ESCA_COD_ESTA_CARG = vsEsta;
             END IF;

             --Grabamos en maestro directoio  reactivacion licencia
             lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
             --INSERTAMOS CABECERA reemplazado
               INSERT INTO ZON_DIREC_VENTA_CABEC
                (PAIS_COD_PAIS,
                 TIOP_COD_TIPO_OPER,
                 TICA_COD_TIPO_CARG,
                 COD_CLIE,
                 FEC_REGI,
                 CAM_PROC,
                 ESCA_COD_ESTA_CARG,
                 MOTI_COD_MOTI_LICE,
                 COD_CLIE_REEM,
                 FEC_REGR,
                 IND_ESTA,
                 USU_CREA,
                 FEC_CREA,
                 EST_REGI,
                 COR_DIRE_VENT,--CORRELATIVO DE CABECERA
                 FEC_VENT,
                 FEC_FACT,
                 IND_NOVE)
        VALUES
                (pscodigopais,
                'RA',
                 vsTipCarg,
                 vsCodGere,
                 TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
                 GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(pscodigopais,vsfechaRegr),
                 'A',
                 vsCodMoti,
                 NULL,--CODIGO REEMPLAZO
                 NULL,
                 'A',
                 pscodigousuario,
                 SYSDATE,
                 '1',
                 lnOidCabec,--CORRELATIVO DE CABECERA
                 TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                 TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                 'A');

      --INSERTAMOS DETALLE reemplazado
            lnOidDetal := ZON_SEQ_DIREC_VENTA_DETAL.nextval;
            INSERT INTO ZON_DIREC_VENTA_DETAL
              (PAIS_COD_PAIS,
               TIOP_COD_TIPO_OPER,
               TICA_COD_TIPO_CARG,
               COD_CLIE,
               DICA_FEC_REGI,
               DICA_CAM_PROC,
               COR_DIVE_DETA,--CORRELATIVO DE DETALLE
               COD_SUBG,
               COD_REGI,
               COD_ZONA,
               USU_CREA,
               FEC_CREA,
               EST_REGI,
               DICA_COR_DIRE_VENT)--CORRELATIVO DE CABECERA
        VALUES
              (pscodigopais,
               'RA',
               vsTipCarg,
               vsCodGere,
               TO_DATE(vsfechaRegr,'DD/MM/YYYY'),
               GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(pscodigopais,vsfechaRegr),
               lnOidDetal,--CORRELATIVO DE DETALLE
               substr(lsua,1,2),
               substr(lsua,3,2),
               substr(lsua,5,4),
               pscodigousuario,
               SYSDATE,
               '1',
               lnOidCabec);  --CORRELATIVO DE CABECERA

      END LOOP;

    CLOSE clistLicencias;


  EXCEPTION
      WHEN  OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR zon_pr_react_licen_direc_fox: ' || ls_sqlerrm);
  END zon_pr_react_licen_direc_fox;

  /***************************************************************************
    Descripcion       : Realiza la asignación de gerentes futuros.
    Fecha Creacion    : 25/11/2013
    Fecha Modificacion:
    Autor             : Juan Altamirano
    parametros
        pscodigoPais             codigo pais,
        psfechaFactu             codigo proceso,
        pscodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_gere_futur
  (
    pscodigoPais               IN VARCHAR2,
    psfechaFactu             IN VARCHAR2,
    pscodigoUsuario            IN VARCHAR2) IS

    CURSOR c_listGereActiDire IS
           select c.cod_clie,
                  TO_CHAR(c.fec_regi,'DD/MM/YYYY') fecha_regis,
                  TO_CHAR(c.fec_regi_fin,'DD/MM/YYYY') fecha_fin,
                  d.cod_subg,d.cod_Regi,d.cod_zona,
                  c.tica_cod_tipo_carg, c.tiop_cod_tipo_oper,
                  c.esca_cod_esta_carg, tc.val_tipo_unid_admi, tc.val_titu,
                  TO_CHAR(c.fec_vent,'DD/MM/YYYY') fecha_vent,
                  TO_CHAR(c.fec_fact,'DD/MM/YYYY') fecha_fact,
                  tc.cod_tipo_carg_base
           from zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
           where c.cor_dire_vent = d.dica_cor_dire_vent
                 and c.cod_clie = d.cod_clie
                 and c.pais_cod_pais = d.pais_cod_pais
                 and c.fec_regi = d.dica_fec_regi
                 and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                 and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                 and c.cam_proc = d.dica_cam_proc
                 and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                 and c.esca_cod_esta_carg = 'A'
                 and c.ind_esta = 'A'
                 and c.est_regi = 1
                 and c.pais_cod_pais = pscodigoPais
                 and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                 and tc.perf_cod_perf = 'TI'       --Solo Titulares
                 and tc.cod_tipo_carg_base is not null
                 order by c.fec_regi desc;

    vsCodGere           zon_direc_venta_cabec.cod_clie%TYPE;
    vsCodClie           zon_direc_venta_cabec.cod_clie%TYPE;

    vsUA                VARCHAR2(8);
    vsfecVenta          zon_direc_venta_cabec.fec_vent%TYPE;
    vsfecFactu          zon_direc_venta_cabec.fec_fact%TYPE;

    vsfechaRegi         VARCHAR2(10);
    vsfechaFin          VARCHAR2(10);

    vsfechaVent         VARCHAR2(10);
    vsfechaFact         VARCHAR2(10);
    vsCodSubge          VARCHAR2(2);
    vsCodRegi           VARCHAR2(2);
    vsCodZona           VARCHAR2(4);
    vsTipCarg           zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsCargoBase         zon_tipo_cargo.cod_tipo_carg_base%TYPE;

    vsTipOper           zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE;
    vsEsta              zon_direc_venta_cabec.esca_cod_esta_carg%TYPE;
    vsTipUni            zon_tipo_cargo.val_tipo_unid_admi%TYPE;
    vsTitu              zon_tipo_cargo.val_titu%TYPE;

    vnFechaFact         VARCHAR2(10);
    vnOidPerioHastaHG   NUMBER;
    vnPerioFechaRegi    VARCHAR2(6);
    vnOidCliente        NUMBER;
    regZona             VARCHAR2(4);
    regRegi             VARCHAR2(2);
    lnOidCabec          NUMBER;
    vsRegHG             NUMBER;
    tieneFechaFin       zon_direc_venta_cabec.fec_regi_fin%TYPE;

    BEGIN

    IF(pscodigoPais IS NOT NULL
                    AND pscodigoUsuario IS NOT NULL)THEN
           BEGIN
             SELECT TO_CHAR(CTRL.FEC_PROC,'DD/MM/YYYY')
                    INTO vnFechaFact
             FROM BAS_CTRL_FACT CTRL
                  WHERE CTRL.COD_PAIS = pscodigoPais
                    AND CTRL.STA_CAMP = 0
                AND CTRL.IND_CAMP_ACT = 1;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
                    vnFechaFact:= NULL;
           END;

           --Cursor Lista de Directorio
           OPEN c_listGereActiDire;
              LOOP FETCH c_listGereActiDire INTO vsCodGere,vsfechaRegi,vsfechaFin,
                                                 vsCodSubge,vsCodRegi,vsCodZona,
                                                 vsTipCarg,vsTipOper,vsEsta,vsTipUni,vsTitu,
                                                 vsfechaVent,vsfechaFact,vsCargoBase;
               EXIT WHEN c_listGereActiDire%NOTFOUND;
               dbms_output.put_line('Entro a cursor: ');

               vnOidPerioHastaHG := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigoPais,'T','VD',TO_DATE(vsfechaRegi,'DD/MM/YYYY')));
               vnPerioFechaRegi := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigoPais, 'T', 'VD', TO_DATE(vsfechaRegi,'DD/MM/YYYY'));
                 IF(vsTipUni = 'Z') THEN
                     dbms_output.put_line('UA Tipo------------------------> Z');
                     dbms_output.put_line('UA := '||vsCodSubge||vsCodRegi||vsCodZona);
                     IF(TO_DATE(vsfechaRegi,'DD/MM/YYYY') <= TO_DATE(psfechaFactu,'DD/MM/YYYY')+1) THEN
                     dbms_output.put_line('Entro a Zona');
                       BEGIN
                          SELECT c.cod_clie, d.cod_subg||d.cod_regi||d.cod_zona, c.fec_vent, c.fec_fact
                           INTO vsCodClie, vsUA, vsfecVenta, vsfecFactu
                         FROM zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
                         WHERE c.cor_dire_vent = d.dica_cor_dire_vent
                               and c.cod_clie = d.cod_clie
                               and c.pais_cod_pais = d.pais_cod_pais
                               and c.fec_regi = d.dica_fec_regi
                               and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                               and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                               and c.cam_proc = d.dica_cam_proc
                               and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                               and c.esca_cod_esta_carg = 'A'
                               and c.pais_cod_pais = pscodigoPais
                               and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                               and tc.perf_cod_perf = 'TI'       --Solo Titulares
                               and c.tica_cod_tipo_carg = vsCargoBase --GZ
                               and d.cod_subg = vsCodSubge
                               and d.cod_regi = vsCodRegi
                               and d.cod_zona = vsCodZona
                               order by c.fec_regi desc;
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            vsCodClie := NULL;
                            vsUA := NULL;

                        END;
                          dbms_output.put_line('--Inicio del Proceso de reasignacion de cargos para Z');


                          IF(vsCodClie IS NOT NULL) THEN
                             dbms_output.put_line('GERENTE BASE EXISTE');
                             dbms_output.put_line('--1 RETIRO (Z)----------------------------------------------------------Z');
                             BEGIN
                               SELECT ZHG.GERE into vsCodClie
                               FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.UA = vsUA
                               AND ZHG.FEC_HAST IS NULL
                               AND ZHG.PERD_OID_PERI_HAST IS NULL;
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsCodClie := NULL;
                             END;

                             dbms_output.put_line('--Si el GR BASE existe en ZHG');
                             dbms_output.put_line('--Cerrar el cliente vigente con la FEC_HAST y PERD_OID_PERI_HAST');
                             IF(vsCodClie IS NOT NULL)THEN

                                  UPDATE ZON_HISTO_GEREN ZHG
                                  SET ZHG.FEC_HAST = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                      ZHG.PERD_OID_PERI_HAST = vnOidPerioHastaHG,
                                      ZHG.USU_MODI = pscodigoUsuario,
                                      ZHG.FEC_MODI = SYSDATE,
                                      ZHG.COD_SUBG = SUBSTR(vsUA,1,2),
                                      ZHG.COD_REGI = SUBSTR(vsUA,3,2),
                                      ZHG.COD_ZONA = SUBSTR(vsUA,5,4)
                                  WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.GERE = vsCodClie
                                        AND ZHG.UA = vsUA
                                        AND ZHG.FEC_HAST IS NULL
                                        AND ZHG.PERD_OID_PERI_HAST IS NULL;


                             vnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodClie);
                             BEGIN
                               SELECT ZZ.COD_ZONA INTO regZona
                               FROM ZON_ZONA ZZ
                               WHERE ZZ.CLIE_OID_CLIE = vnOidCliente
                                     AND ZZ.COD_ZONA = SUBSTR(vsUA,5,4);
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 regZona := NULL;
                             END;

                             dbms_output.put_line('--Actualizar tabla ZON_ZONA con clie_oid_clie NULL');
                              IF(regZona IS NOT NULL) THEN
                               UPDATE ZON_ZONA ZZ
                                SET    ZZ.CLIE_OID_CLIE	  = NULL,
                                       ZZ.FEC_ULTI_ACTU	  = SYSDATE
                                WHERE  ZZ.COD_ZONA      = SUBSTR(vsUA,5,4);
                              END IF;

                              dbms_output.put_line('--Inactivar en el Directorio al GZ BASE');
                             dbms_output.put_line('--Consultar si la fecha_regi_fin es null para el GZ BASE');

                             BEGIN
                               SELECT C.FEC_REGI_FIN INTO tieneFechaFin
                                  FROM ZON_DIREC_VENTA_CABEC C, ZON_DIREC_VENTA_DETAL D
                                 WHERE C.PAIS_COD_PAIS = D.PAIS_COD_PAIS
                                       AND C.TIOP_COD_TIPO_OPER = D.TIOP_COD_TIPO_OPER
                                       AND C.TICA_COD_TIPO_CARG = D.TICA_COD_TIPO_CARG
                                       AND C.COD_CLIE = D.COD_CLIE
                                       AND C.FEC_REGI = D.DICA_FEC_REGI
                                       AND C.CAM_PROC = D.DICA_CAM_PROC
                                       AND C.COR_DIRE_VENT = D.DICA_COR_DIRE_VENT
                                       AND C.PAIS_COD_PAIS = pscodigoPais
                                      AND C.COD_CLIE = vsCodClie
                                      AND C.ESCA_COD_ESTA_CARG = 'A'
                                       AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                       AND D.COD_SUBG = SUBSTR(vsUA,1,2)
                                       AND D.COD_REGI = SUBSTR(vsUA,3,2)
                                       AND D.COD_ZONA = SUBSTR(vsUA,5,4);
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 tieneFechaFin := NULL;
                             END;

                             IF(tieneFechaFin IS NOT NULL)THEN
                                 dbms_output.put_line('--fecha_regi_fin es NOT NULL para el GZ BASE');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE
                                  WHERE C.PAIS_COD_PAIS = pscodigoPais
                                    AND C.COD_CLIE = vsCodClie
                                    AND C.ESCA_COD_ESTA_CARG = 'A'
                                    AND C.TICA_COD_TIPO_CARG = vsCargoBase;

                             ELSE
                                 dbms_output.put_line('--fecha_regi_fin es NULL para el GZ BASE');
                                 UPDATE ZON_DIREC_VENTA_CABEC C
                                  SET C.ESCA_COD_ESTA_CARG = 'I',
                                      C.IND_NOVE = 'A',
                                      C.USU_MODI = pscodigoUsuario,
                                      C.FEC_MODI = SYSDATE,
                                      C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')-1
                                  WHERE C.PAIS_COD_PAIS = pscodigoPais
                                    AND C.COD_CLIE = vsCodClie
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                    AND C.TICA_COD_TIPO_CARG = vsCargoBase;
                             END IF;

                              dbms_output.put_line('--Grabamos en directorio,  el retir para el GZ BASE');
                              dbms_output.put_line('--INSERTAMOS CABECERA');
                                lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                                VALUES
                                  (pscodigoPais,
                                   'RE',
                                   vsCargoBase,
                                   vsCodClie,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'I',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'RE',
                                 vsCargoBase,
                                 vsCodClie,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 substr(vsUA,5,4),
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);

                              dbms_output.put_line('--2 CAMBIO DE CARGO (Z)----------------------------------------------------- ');

                              SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.GERE = vsCodClie
                                        AND ZHG.UA = vsUA;

                              IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_HISTO_GEREN ZHG
                                 WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.GERE = vsCodClie
                                        AND ZHG.UA = vsUA;

                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                              INSERT INTO ZON_HISTO_GEREN
                                (oid_hist_gere,
                                 marc_oid_marc,
                                 cana_oid_cana,
                                 ua, gere,
                                 fec_desd, fec_hast,
                                 pais_oid_pais,
                                 perd_oid_peri_desd,
                                 perd_oid_peri_hast,
                                 usu_modi, fec_modi,
                                 cod_subg,
                                 cod_regi,
                                 cod_zona)
                              VALUES
                                (zon_hger_seq.nextval,
                                 gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                 gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                 vsUA, vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY') , NULL,
                                 gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigoPais),
                                 vnOidPerioHastaHG,
                                 NULL,
                                 pscodigoUsuario, SYSDATE,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 substr(vsUA,5,4));

                              dbms_output.put_line('--ACTUALIZAMOS LA ZONA CON EL GERENTE FUTURO');
                              UPDATE zon_zona z
                               SET z.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                                 z.fec_ulti_actu = SYSDATE
                              WHERE z.cod_zona = vsCodZona;


                              dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                              dbms_output.put_line('--inactivar en el Directorio al GZ TITULAR');

                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE,
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')
                              WHERE C.PAIS_COD_PAIS = pscodigoPais
                                AND C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg;

                               dbms_output.put_line('--Grabamos en directorio,  al GZ TITULAR COMO GZ BASE');
                               dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                                VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'A',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                   TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'NM',
                                 vsCargoBase,
                                 vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 substr(vsUA,5,4),
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);
                             ELSE
                               dbms_output.put_line('--Si el GR BASE no existe en ZHG');

                               continue;

                             END IF;

                          ELSE
                            dbms_output.put_line('CODIGO DEL CLIENTE BASE ES NULL');
                            dbms_output.put_line(' --1 CAMBIO DE CARGO');
                            vsUA := vsCodSubge||vsCodRegi||vsCodZona;
                            SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              IF(vsRegHG > 0) THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_HISTO_GEREN ZHG
                                 WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GZ TITULAR FUTURO.');
                              INSERT INTO ZON_HISTO_GEREN
                                (oid_hist_gere,
                                 marc_oid_marc,
                                 cana_oid_cana,
                                 ua, gere,
                                 fec_desd, fec_hast,
                                 pais_oid_pais,
                                 perd_oid_peri_desd,
                                 perd_oid_peri_hast,
                                 usu_modi, fec_modi,
                                 cod_subg,
                                 cod_regi,
                                 cod_zona)
                              VALUES
                                (zon_hger_seq.nextval,
                                 gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                 gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                 vsUA, vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY') , NULL,
                                 gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigoPais),
                                 vnOidPerioHastaHG,
                                 NULL,
                                 pscodigoUsuario, SYSDATE,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 substr(vsUA,5,4));

                              dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                              UPDATE zon_zona z
                               SET z.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                                 z.fec_ulti_actu = SYSDATE
                              WHERE z.cod_zona = vsCodZona;

                              dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                              dbms_output.put_line('--Inactivar en el Directorio al GZ TITULAR');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE
                              WHERE C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                               dbms_output.put_line('--Grabamos en directorio, al GZ TITULAR como GZ BASE');
                               dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   FEC_REGI,
                                   CAM_PROC,
                                   ESCA_COD_ESTA_CARG,
                                   MOTI_COD_MOTI_LICE,
                                   COD_CLIE_REEM,
                                   FEC_REGR,
                                   IND_ESTA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   COR_DIRE_VENT,
                                   FEC_VENT,
                                   FEC_FACT,
                                   IND_NOVE,
                                   FEC_REGI_FIN)
                                VALUES
                                    (pscodigoPais,
                                     'NM',
                                     vsCargoBase,
                                     vsCodGere,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     'A',
                                     NULL,
                                     NULL,
                                     NULL,
                                     'A',
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec,
                                     TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                     TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                     'A',
                                     TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                                dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   DICA_FEC_REGI,
                                   DICA_CAM_PROC,
                                 COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   DICA_COR_DIRE_VENT)
                              VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 substr(vsUA,5,4),
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec);

                          END IF;

                     END IF;

                 END IF;

                 --Si el tipo de UA es REGION
                 IF(vsTipUni = 'R') THEN
                     dbms_output.put_line('UA Tipo------------------------> R');
                     dbms_output.put_line('UA := '||vsCodSubge||vsCodRegi);
                     IF(TO_DATE(vsfechaRegi,'DD/MM/YYYY') <= TO_DATE(psfechaFactu,'DD/MM/YYYY')+1) THEN
                     dbms_output.put_line('Entro a Region');
                        BEGIN
                          SELECT c.cod_clie, d.cod_subg||d.cod_regi, c.fec_vent, c.fec_fact
                           INTO vsCodClie, vsUA, vsfecVenta, vsfecFactu
                          FROM zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
                         WHERE c.cor_dire_vent = d.dica_cor_dire_vent
                               and c.cod_clie = d.cod_clie
                               and c.pais_cod_pais = d.pais_cod_pais
                               and c.fec_regi = d.dica_fec_regi
                               and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                               and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                               and c.cam_proc = d.dica_cam_proc
                               and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                               and c.esca_cod_esta_carg = 'A'
                               and c.pais_cod_pais = pscodigoPais
                               and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                               and tc.perf_cod_perf = 'TI'       --Solo Titulares
                               and c.tica_cod_tipo_carg = vsCargoBase --GR
                               and d.cod_subg = vsCodSubge
                               and d.cod_regi = vsCodRegi
                               order by c.fec_regi desc;
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            vsCodClie := NULL;
                        END;

                          dbms_output.put_line('--Inicio del Proceso de reasignacion de cargos');
                          IF(vsCodClie IS NOT NULL) THEN
                             dbms_output.put_line('--1 RETIRO DEL GR BASE (EN R)*********');
                             dbms_output.put_line('--Buscamos al GR BASE EN EL ZHG');
                             BEGIN
                               SELECT ZHG.GERE into vsCodClie
                               FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.UA = vsUA
                               AND ZHG.FEC_HAST IS NULL
                               AND ZHG.PERD_OID_PERI_HAST IS NULL;
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsCodClie := NULL;
                             END;

                             dbms_output.put_line('--Si el GR BASE existe en ZHG');
                             dbms_output.put_line('--Cerrar el cliente vigente con la FEC_HAST y PERD_OID_PERI_HAST');
                             IF(vsCodClie IS NOT NULL)THEN
                                  UPDATE ZON_HISTO_GEREN ZHG
                                  SET ZHG.FEC_HAST = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                      ZHG.PERD_OID_PERI_HAST = vnOidPerioHastaHG,
                                      ZHG.USU_MODI = pscodigoUsuario,
                                      ZHG.FEC_MODI = SYSDATE,
                                      ZHG.COD_SUBG = SUBSTR(vsUA,1,2),
                                      ZHG.COD_REGI = SUBSTR(vsUA,3,2)
                                  WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.GERE = vsCodClie
                                        AND ZHG.UA = vsUA
                                        AND ZHG.FEC_HAST IS NULL
                                        AND ZHG.PERD_OID_PERI_HAST IS NULL;

                             vnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodClie);
                             BEGIN
                               SELECT ZR.COD_REGI INTO regRegi
                               FROM ZON_REGIO ZR
                               WHERE ZR.CLIE_OID_CLIE = vnOidCliente
                                     AND ZR.COD_REGI = SUBSTR(vsUA,3,2);
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 regRegi := NULL;
                             END;

                             dbms_output.put_line('--Actualizar tabla ZON_REGIO con clie_oid_clie NULL');
                              IF(regRegi IS NOT NULL) THEN
                               UPDATE ZON_REGIO ZR
                                SET    ZR.CLIE_OID_CLIE	  = NULL,
                                       ZR.FEC_ULTI_ACTU	  = SYSDATE
                                WHERE  ZR.COD_REGI = SUBSTR(vsUA,3,2);
                              END IF;

                              dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');

                               BEGIN
                                 SELECT C.FEC_REGI_FIN INTO tieneFechaFin
                                        FROM ZON_DIREC_VENTA_CABEC C, ZON_DIREC_VENTA_DETAL D
                                       WHERE C.PAIS_COD_PAIS = D.PAIS_COD_PAIS
                                             AND C.TIOP_COD_TIPO_OPER = D.TIOP_COD_TIPO_OPER
                                             AND C.TICA_COD_TIPO_CARG = D.TICA_COD_TIPO_CARG
                                             AND C.COD_CLIE = D.COD_CLIE
                                             AND C.FEC_REGI = D.DICA_FEC_REGI
                                             AND C.CAM_PROC = D.DICA_CAM_PROC
                                             AND C.COR_DIRE_VENT = D.DICA_COR_DIRE_VENT
                                             AND C.PAIS_COD_PAIS = pscodigoPais
                                        AND C.COD_CLIE = vsCodClie
                                        AND C.ESCA_COD_ESTA_CARG = 'A'
                                             AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                             AND D.COD_SUBG = SUBSTR(vsUA,1,2)
                                             AND D.COD_REGI = SUBSTR(vsUA,3,2);
                               EXCEPTION

                                 WHEN NO_DATA_FOUND THEN
                                   tieneFechaFin := NULL;
                               END;

                              IF(tieneFechaFin IS NOT NULL) THEN
                                 UPDATE ZON_DIREC_VENTA_CABEC C
                                  SET C.ESCA_COD_ESTA_CARG = 'I',
                                      C.IND_NOVE = 'A',
                                      C.USU_MODI = pscodigoUsuario,
                                      C.FEC_MODI = SYSDATE
                                  WHERE C.COD_CLIE = vsCodClie
                                    AND C.ESCA_COD_ESTA_CARG = 'A'
                                    AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                    AND C.PAIS_COD_PAIS = pscodigoPais;

                              ELSE
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                      C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')-1,
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE
                              WHERE C.COD_CLIE = vsCodClie
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                              END IF;

                              dbms_output.put_line('--Grabamos en maestro directorio,  el retiro');
                              dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                                VALUES
                                  (pscodigoPais,
                                   'RE',
                                   vsCargoBase,
                                   vsCodClie,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'I',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                       TO_DATE(vsfechaRegi,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'RE',
                                 vsCargoBase,
                                 vsCodClie,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 NULL,
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);

                                dbms_output.put_line('--2 CAMBIO DE CARGO (EN R)********');
                              SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_HISTO_GEREN ZHG
                                 WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                              INSERT INTO ZON_HISTO_GEREN
                                (oid_hist_gere,
                                 marc_oid_marc,
                                 cana_oid_cana,
                                 ua, gere,
                                 fec_desd, fec_hast,
                                 pais_oid_pais,
                                 perd_oid_peri_desd,
                                 perd_oid_peri_hast,
                                 usu_modi, fec_modi,
                                 cod_subg,
                                 cod_regi,
                                 cod_zona)
                              VALUES
                                (zon_hger_seq.nextval,
                                 gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                 gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                 vsUA, vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY') , NULL,
                                 gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigoPais),
                                 vnOidPerioHastaHG,
                                 NULL,
                                 pscodigoUsuario, SYSDATE,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 null);

                              dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                              UPDATE zon_regio r
                               SET r.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                                 r.fec_ulti_actu = SYSDATE
                              WHERE r.cod_regi = vsCodRegi;

                              dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');

                              dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE
                              WHERE C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                              dbms_output.put_line('--Grabamos en directorio AL GR TITULAR COMO GR BASE');
                              dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                                VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'A',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                   TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'NM',
                                 vsCargoBase,
                                 vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 NULL,
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);

                          ELSE
                               dbms_output.put_line('--Si el GR BASE no existe en ZHG');

                               continue;

                             END IF;

                          ELSE
                            vsUA := vsCodSubge||vsCodRegi;
                            dbms_output.put_line('--1 CAMBIO DE CARGO----(EN R)******');
                             SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_HISTO_GEREN ZHG
                               WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_HISTO_GEREN ZHG
                                 WHERE ZHG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
                                        AND ZHG.CANA_OID_CANA = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
                                        AND ZHG.UA = vsUA;

                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                              INSERT INTO ZON_HISTO_GEREN
                                (oid_hist_gere,
                                 marc_oid_marc,
                                 cana_oid_cana,
                                 ua, gere,
                                 fec_desd, fec_hast,
                                 pais_oid_pais,
                                 perd_oid_peri_desd,
                                 perd_oid_peri_hast,
                                 usu_modi, fec_modi,
                                 cod_subg,
                                 cod_regi,
                                 cod_zona)
                              VALUES
                                (zon_hger_seq.nextval,
                                 gen_pkg_gener.gen_fn_devuelve_id_marca('T'),
                                 gen_pkg_gener.gen_fn_devuelve_id_canal('VD'),
                                 vsUA, vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY') , NULL,
                                 gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigoPais),
                                 vnOidPerioHastaHG,
                                 NULL,
                                 pscodigoUsuario, SYSDATE,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 null);

                              dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                              UPDATE zon_regio r
                               SET r.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodGere),
                                 r.fec_ulti_actu = SYSDATE
                              WHERE r.cod_regi = vsCodRegi;

                              dbms_output.put_line(' --REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                              dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE,
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')
                              WHERE C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                              dbms_output.put_line('--Grabamos en directorio, AL GR TITULAR COMO GR BASE');
                              dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                 INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                                VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'A',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                   TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'NM',
                                 vsCargoBase,
                                 vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 NULL,
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);

                          END IF;

                     END IF;

                 END IF;

               END LOOP;

          CLOSE c_listGereActiDire;

    END IF;


    EXCEPTION
      WHEN  OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR zon_pr_react_gere_futur: ' || ls_sqlerrm);
  END zon_pr_react_gere_futur;

  /***************************************************************************
    Descripcion       : Realiza la asignación de gerentes futuros para fox.
    Fecha Creacion    : 11/12/2013
    Fecha Modificacion:
    Autor             : Juan Altamirano
    parametros
        pscodigoPais             codigo pais,
        psfechaFactu             codigo proceso,
        pscodigoUsuario          codigo usuario
  ***************************************************************************/
  PROCEDURE zon_pr_react_gere_futur_fox
  (
    pscodigoPais             IN VARCHAR2,
    psfechaFactu             IN VARCHAR2,
    pscodigoUsuario          IN VARCHAR2) IS

    CURSOR c_listGereActiDire IS
           select c.cod_clie,
                  TO_CHAR(c.fec_regi,'DD/MM/YYYY') fecha_regis,
                  TO_CHAR(c.fec_regi_fin,'DD/MM/YYYY') fecha_fin,
                  d.cod_subg,d.cod_Regi,d.cod_zona,
                  c.tica_cod_tipo_carg, c.tiop_cod_tipo_oper,
                  c.esca_cod_esta_carg, tc.val_tipo_unid_admi, tc.val_titu,
                  TO_CHAR(c.fec_vent,'DD/MM/YYYY') fecha_vent,
                  TO_CHAR(c.fec_fact,'DD/MM/YYYY') fecha_fact,
                  tc.cod_tipo_carg_base
           from zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
           where c.cor_dire_vent = d.dica_cor_dire_vent
                 and c.cod_clie = d.cod_clie
                 and c.pais_cod_pais = d.pais_cod_pais
                 and c.fec_regi = d.dica_fec_regi
                 and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                 and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                 and c.cam_proc = d.dica_cam_proc
                 and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                 and c.esca_cod_esta_carg = 'A'
                 and c.ind_esta = 'A'
                 and c.est_regi = 1
                 and c.pais_cod_pais = pscodigoPais
                 and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                 and tc.perf_cod_perf = 'TI'       --Solo Titulares
                 and tc.cod_tipo_carg_base is not null
                 order by c.fec_regi desc;

    vsCodGere           zon_direc_venta_cabec.cod_clie%TYPE;
    vsCodClie           zon_direc_venta_cabec.cod_clie%TYPE;

    vsUA                VARCHAR2(8);
    vsfecVenta          zon_direc_venta_cabec.fec_vent%TYPE;
    vsfecFactu          zon_direc_venta_cabec.fec_fact%TYPE;

    vsfechaRegi         VARCHAR2(10);
    vsfechaFin          VARCHAR2(10);

    vsfechaVent         VARCHAR2(10);
    vsfechaFact         VARCHAR2(10);
    vsCodSubge          VARCHAR2(2);
    vsCodRegi           VARCHAR2(2);
    vsCodZona           VARCHAR2(4);
    vsTipCarg           zon_direc_venta_cabec.tica_cod_tipo_carg%TYPE;
    vsCargoBase         zon_tipo_cargo.cod_tipo_carg_base%TYPE;

    vsTipOper           zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE;
    vsEsta              zon_direc_venta_cabec.esca_cod_esta_carg%TYPE;
    vsTipUni            zon_tipo_cargo.val_tipo_unid_admi%TYPE;
    vsTitu              zon_tipo_cargo.val_titu%TYPE;

    vnFechaFact         VARCHAR2(10);
    vnOidPerioHastaHG   NUMBER;
    vnPerioFechaRegi    VARCHAR2(6);
    vnOidCliente        NUMBER;
    regZona             VARCHAR2(4);
    regRegi             VARCHAR2(2);
    lnOidCabec          NUMBER;
    vsRegHG             NUMBER;
    tieneFechaFin       zon_direc_venta_cabec.fec_regi_fin%TYPE;

    BEGIN

    IF(pscodigoPais IS NOT NULL
                    AND pscodigoUsuario IS NOT NULL)THEN
           BEGIN
             SELECT TO_CHAR(CTRL.FEC_PROC,'DD/MM/YYYY')
                    INTO vnFechaFact
             FROM BAS_CTRL_FACT CTRL
                  WHERE CTRL.COD_PAIS = pscodigoPais
                    AND CTRL.STA_CAMP = 0
                AND CTRL.IND_CAMP_ACT = 1;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
                    vnFechaFact:= NULL;
           END;

           --Cursor Lista de Directorio
           OPEN c_listGereActiDire;
              LOOP FETCH c_listGereActiDire INTO vsCodGere,vsfechaRegi,vsfechaFin,
                                                 vsCodSubge,vsCodRegi,vsCodZona,
                                                 vsTipCarg,vsTipOper,vsEsta,vsTipUni,vsTitu,
                                                 vsfechaVent,vsfechaFact,vsCargoBase;
               EXIT WHEN c_listGereActiDire%NOTFOUND;
               dbms_output.put_line('Entro a cursor: ');

               vnOidPerioHastaHG := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigoPais,'T','VD',TO_DATE(vsfechaRegi,'DD/MM/YYYY')));
               vnPerioFechaRegi := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(pscodigoPais, 'T', 'VD', TO_DATE(vsfechaRegi,'DD/MM/YYYY'));
                 IF(vsTipUni = 'Z') THEN
                     dbms_output.put_line('UA Tipo------------------------> Z');
                     dbms_output.put_line('UA := '||vsCodSubge||vsCodRegi||vsCodZona);
                     IF(TO_DATE(vsfechaRegi,'DD/MM/YYYY') <= TO_DATE(psfechaFactu,'DD/MM/YYYY')+1) THEN
                     dbms_output.put_line('Entro a Zona');
                       BEGIN
                          SELECT c.cod_clie, d.cod_subg||d.cod_regi||d.cod_zona, c.fec_vent, c.fec_fact
                           INTO vsCodClie, vsUA, vsfecVenta, vsfecFactu
                         FROM zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
                         WHERE c.cor_dire_vent = d.dica_cor_dire_vent
                               and c.cod_clie = d.cod_clie
                               and c.pais_cod_pais = d.pais_cod_pais
                               and c.fec_regi = d.dica_fec_regi
                               and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                               and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                               and c.cam_proc = d.dica_cam_proc
                               and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                               and c.esca_cod_esta_carg = 'A'
                               and c.pais_cod_pais = pscodigoPais
                               and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                               and tc.perf_cod_perf = 'TI'       --Solo Titulares
                               and c.tica_cod_tipo_carg = vsCargoBase --GZ
                               and d.cod_subg = vsCodSubge
                               and d.cod_regi = vsCodRegi
                               and d.cod_zona = vsCodZona
                               order by c.fec_regi desc;
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            vsCodClie := NULL;
                            vsUA := NULL;

                        END;
                          dbms_output.put_line('--Inicio del Proceso de reasignacion de cargos para Z');


                          IF(vsCodClie IS NOT NULL) THEN
                             dbms_output.put_line('GERENTE BASE EXISTE');
                             dbms_output.put_line('--1 RETIRO (Z)----------------------------------------------------------Z');
                             BEGIN
                               SELECT ZDHG.CLIE_COD_CLIE into vsCodClie
                               FROM ZON_DIREC_HISTO_GEREN ZDHG
                               WHERE ZDHG.UNI_ADMI = vsUA
                                     AND ZDHG.PAIS_COD_PAIS = pscodigoPais
                                     AND ZDHG.FEC_HAST IS NULL
                                     AND ZDHG.CAM_HAST IS NULL;
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsCodClie := NULL;
                             END;

                             dbms_output.put_line('--Si el GR BASE existe en ZHG');
                             dbms_output.put_line('--Cerrar el cliente vigente con la FEC_HAST y PERD_OID_PERI_HAST');
                             IF(vsCodClie IS NOT NULL)THEN

                                  UPDATE ZON_DIREC_HISTO_GEREN ZDHG
                                  SET ZDHG.FEC_HAST = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                      ZDHG.CAM_HAST = vnPerioFechaRegi,
                                      ZDHG.UNI_ADMI = vsUA,
                                      ZDHG.USU_MODI = pscodigoUsuario,
                                      ZDHG.FEC_MODI = SYSDATE
                                  WHERE ZDHG.CLIE_COD_CLIE = vsCodClie
                                        AND ZDHG.UNI_ADMI = vsUA
                                        AND ZDHG.FEC_HAST IS NULL
                                        AND ZDHG.CAM_HAST IS NULL;

                               --vnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodClie);
                               BEGIN
                                 SELECT ZZ.COD_ZONA INTO regZona
                                 FROM ZON_DIREC_ZONA ZZ
                                 WHERE ZZ.PAIS_COD_PAIS = pscodigoPais
                                       AND ZZ.REGI_COD_REGI = SUBSTR(vsUA,3,2)
                                       AND ZZ.COD_ZONA = SUBSTR(vsUA,5,4)
                                       AND ZZ.CLIE_COD_CLIE = vsCodClie;

                               EXCEPTION
                                 WHEN NO_DATA_FOUND THEN
                                   regZona := NULL;
                               END;

                               dbms_output.put_line('--Actualizar tabla ZON_ZONA con clie_oid_clie NULL');
                                IF(regZona IS NOT NULL) THEN
                                 UPDATE ZON_DIREC_ZONA ZZ
                                  SET    ZZ.CLIE_COD_CLIE    = NULL,
                                         ZZ.FEC_MODI    = SYSDATE,
                                         ZZ.USU_MODI    = pscodigoUsuario
                                  WHERE ZZ.PAIS_COD_PAIS = pscodigoPais
                                       AND ZZ.REGI_COD_REGI = SUBSTR(vsUA,3,2)
                                       AND ZZ.COD_ZONA = SUBSTR(vsUA,5,4)
                                       AND ZZ.CLIE_COD_CLIE = vsCodClie;
                                END IF;

                               dbms_output.put_line('--Inactivar en el Directorio al GZ BASE');
                               dbms_output.put_line('--Consultar si la fecha_regi_fin es null para el GZ BASE');

                               BEGIN
                                 SELECT C.FEC_REGI_FIN INTO tieneFechaFin
                                  FROM ZON_DIREC_VENTA_CABEC C, ZON_DIREC_VENTA_DETAL D
                                 WHERE C.PAIS_COD_PAIS = D.PAIS_COD_PAIS
                                       AND C.TIOP_COD_TIPO_OPER = D.TIOP_COD_TIPO_OPER
                                       AND C.TICA_COD_TIPO_CARG = D.TICA_COD_TIPO_CARG
                                       AND C.COD_CLIE = D.COD_CLIE
                                       AND C.FEC_REGI = D.DICA_FEC_REGI
                                       AND C.CAM_PROC = D.DICA_CAM_PROC
                                       AND C.COR_DIRE_VENT = D.DICA_COR_DIRE_VENT
                                       AND C.PAIS_COD_PAIS = pscodigoPais
                                       AND C.COD_CLIE = vsCodClie
                                       AND C.ESCA_COD_ESTA_CARG = 'A'
                                       AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                       AND D.COD_SUBG = SUBSTR(vsUA,1,2)
                                       AND D.COD_REGI = SUBSTR(vsUA,3,2)
                                       AND D.COD_ZONA = SUBSTR(vsUA,5,4);
                               EXCEPTION
                                 WHEN NO_DATA_FOUND THEN
                                   tieneFechaFin := NULL;
                               END;

                               IF(tieneFechaFin IS NOT NULL)THEN
                                   dbms_output.put_line('--fecha_regi_fin es NOT NULL para el GZ BASE');
                                   UPDATE ZON_DIREC_VENTA_CABEC C
                                    SET C.ESCA_COD_ESTA_CARG = 'I',
                                        C.IND_NOVE = 'A',
                                        C.USU_MODI = pscodigoUsuario,
                                        C.FEC_MODI = SYSDATE
                                    WHERE C.PAIS_COD_PAIS = pscodigoPais
                                      AND C.COD_CLIE = vsCodClie
                                      AND C.ESCA_COD_ESTA_CARG = 'A'
                                      AND C.TICA_COD_TIPO_CARG = vsCargoBase;

                               ELSE
                                   dbms_output.put_line('--fecha_regi_fin es NULL para el GZ BASE');
                                   UPDATE ZON_DIREC_VENTA_CABEC C
                                    SET C.ESCA_COD_ESTA_CARG = 'I',
                                        C.IND_NOVE = 'A',
                                        C.USU_MODI = pscodigoUsuario,
                                        C.FEC_MODI = SYSDATE,
                                        C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')-1
                                    WHERE C.PAIS_COD_PAIS = pscodigoPais
                                      AND C.COD_CLIE = vsCodClie
                                      AND C.ESCA_COD_ESTA_CARG = 'A'
                                      AND C.TICA_COD_TIPO_CARG = vsCargoBase;
                               END IF;

                                dbms_output.put_line('--Grabamos en directorio, el retiro para el GZ BASE');
                                dbms_output.put_line('--INSERTAMOS CABECERA');
                                lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                INSERT INTO ZON_DIREC_VENTA_CABEC
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   FEC_REGI,
                                   CAM_PROC,
                                   ESCA_COD_ESTA_CARG,
                                   MOTI_COD_MOTI_LICE,
                                   COD_CLIE_REEM,
                                   FEC_REGR,
                                   IND_ESTA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   COR_DIRE_VENT,
                                   FEC_VENT,
                                   FEC_FACT,
                                   IND_NOVE,
                                   FEC_REGI_FIN)
                                VALUES
                                    (pscodigoPais,
                                     'RE',
                                     vsCargoBase,
                                     vsCodClie,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     'I',
                                     NULL,
                                     NULL,
                                     NULL,
                                     'A',
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec,
                                     TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                     TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                     'A',
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'));

                                dbms_output.put_line('--INSERTAMOS DETALLE');
                                INSERT INTO ZON_DIREC_VENTA_DETAL
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   DICA_FEC_REGI,
                                   DICA_CAM_PROC,
                                   COR_DIVE_DETA,
                                   COD_SUBG,
                                   COD_REGI,
                                   COD_ZONA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   DICA_COR_DIRE_VENT)
                                VALUES
                                  (pscodigoPais,
                                   'RE',
                                   vsCargoBase,
                                   vsCodClie,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                   substr(vsUA,1,2),
                                   substr(vsUA,3,2),
                                   substr(vsUA,5,4),
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec);

                                dbms_output.put_line('--2 CAMBIO DE CARGO (Z)----------------------------------------------------- ');

                                SELECT COUNT(1) INTO vsRegHG
                                  FROM ZON_DIREC_HISTO_GEREN ZDHG
                                  WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                       AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                       AND ZDHG.UNI_ADMI = vsUA;

                                IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                  DELETE FROM  ZON_DIREC_HISTO_GEREN ZDHG
                                   WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                       AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                       AND ZDHG.UNI_ADMI = vsUA;

                                END IF;

                                dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                                INSERT INTO ZON_DIREC_HISTO_GEREN
                                  (COR_HIST_GERE, pais_cod_pais,
                                   uni_admi, clie_cod_clie,
                                   fec_desd, fec_hast,
                                   cam_desd, cam_hast,
                                   usu_crea, fec_crea,
                                   usu_modi, fec_modi)
                                VALUES
                                  (zon_direc_hger_seq.nextval, pscodigoPais,
                                   vsUA, vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'), NULL,
                                   vnPerioFechaRegi, NULL,
                                   pscodigoUsuario, SYSDATE,
                                   NULL, NULL);

                                dbms_output.put_line('--ACTUALIZAMOS LA ZONA CON EL GERENTE FUTURO');
                                UPDATE zon_direc_zona z
                                 SET z.clie_cod_clie = vsCodGere,
                                     z.usu_modi = pscodigoUsuario,
                                     z.fec_modi = SYSDATE
                                WHERE z.pais_cod_pais = pscodigoPais
                                     and z.regi_cod_regi = vsCodRegi
                                     and z.cod_zona = vsCodZona;

                                dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                                dbms_output.put_line('--inactivar en el Directorio al GZ TITULAR');

                                UPDATE ZON_DIREC_VENTA_CABEC C
                                SET C.ESCA_COD_ESTA_CARG = 'I',
                                    C.IND_NOVE = 'A',
                                    C.USU_MODI = pscodigoUsuario,
                                    C.FEC_MODI = SYSDATE,
                                    C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')
                                WHERE C.PAIS_COD_PAIS = pscodigoPais
                                  AND C.COD_CLIE = vsCodGere
                                  AND C.ESCA_COD_ESTA_CARG = 'A'
                                  AND C.TICA_COD_TIPO_CARG = vsTipCarg;

                                 dbms_output.put_line('--Grabamos en directorio,  al GZ TITULAR COMO GZ BASE');
                                 dbms_output.put_line('--INSERTAMOS CABECERA');
                                 lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                INSERT INTO ZON_DIREC_VENTA_CABEC
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   FEC_REGI,
                                   CAM_PROC,
                                   ESCA_COD_ESTA_CARG,
                                   MOTI_COD_MOTI_LICE,
                                   COD_CLIE_REEM,
                                   FEC_REGR,
                                   IND_ESTA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   COR_DIRE_VENT,
                                   FEC_VENT,
                                   FEC_FACT,
                                   IND_NOVE,
                                   FEC_REGI_FIN)
                                VALUES
                                    (pscodigoPais,
                                     'NM',
                                     vsCargoBase,
                                     vsCodGere,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     'A',
                                     NULL,
                                     NULL,
                                     NULL,
                                     'A',
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec,
                                     TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                     TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                     'A',
                                     TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                                dbms_output.put_line('--INSERTAMOS DETALLE');
                                INSERT INTO ZON_DIREC_VENTA_DETAL
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   DICA_FEC_REGI,
                                   DICA_CAM_PROC,
                                   COR_DIVE_DETA,
                                   COD_SUBG,
                                   COD_REGI,
                                   COD_ZONA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   DICA_COR_DIRE_VENT)
                                VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                   substr(vsUA,1,2),
                                   substr(vsUA,3,2),
                                   substr(vsUA,5,4),
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec);
                             ELSE
                               dbms_output.put_line('--Si el GR BASE no existe en ZHG');

                               continue;

                             END IF;

                          ELSE
                            dbms_output.put_line('CODIGO DEL CLIENTE BASE ES NULL');
                            dbms_output.put_line(' --1 CAMBIO DE CARGO');
                            vsUA := vsCodSubge||vsCodRegi||vsCodZona;

                           SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_DIREC_HISTO_GEREN ZDHG
                                WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                     AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                     AND ZDHG.UNI_ADMI = vsUA;

                              IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_DIREC_HISTO_GEREN ZDHG
                                 WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                     AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                     AND ZDHG.UNI_ADMI = vsUA;

                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GZ TITULAR FUTURO.');
                              INSERT INTO ZON_DIREC_HISTO_GEREN
                                  (COR_HIST_GERE, pais_cod_pais,
                                   uni_admi, clie_cod_clie,
                                   fec_desd, fec_hast,
                                   cam_desd, cam_hast,
                                   usu_crea, fec_crea,
                                   usu_modi, fec_modi)
                               VALUES
                                  (zon_direc_hger_seq.nextval, pscodigoPais,
                                   vsUA, vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'), NULL,
                                   vnPerioFechaRegi, NULL,
                                   pscodigoUsuario, SYSDATE,
                                   NULL, NULL);


                              dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                              UPDATE zon_direc_zona z
                                 SET z.clie_cod_clie = vsCodGere,
                                     z.usu_modi = pscodigoUsuario,
                                     z.fec_modi = SYSDATE
                              WHERE z.pais_cod_pais = pscodigoPais
                                    and z.regi_cod_regi = vsCodRegi
                                    and z.cod_zona = vsCodZona;

                              dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                              dbms_output.put_line('--Inactivar en el Directorio al GZ TITULAR');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE
                              WHERE C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                               dbms_output.put_line('--Grabamos en directorio, al GZ TITULAR como GZ BASE');
                               dbms_output.put_line('--INSERTAMOS CABECERA');
                               lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                               INSERT INTO ZON_DIREC_VENTA_CABEC
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   FEC_REGI,
                                   CAM_PROC,
                                   ESCA_COD_ESTA_CARG,
                                   MOTI_COD_MOTI_LICE,
                                   COD_CLIE_REEM,
                                   FEC_REGR,
                                   IND_ESTA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   COR_DIRE_VENT,
                                   FEC_VENT,
                                   FEC_FACT,
                                   IND_NOVE,
                                   FEC_REGI_FIN)
                               VALUES
                                    (pscodigoPais,
                                     'NM',
                                     vsCargoBase,
                                     vsCodGere,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     'A',
                                     NULL,
                                     NULL,
                                     NULL,
                                     'A',
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec,
                                     TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                     TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                     'A',
                                     TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                                dbms_output.put_line('--INSERTAMOS DETALLE');
                                INSERT INTO ZON_DIREC_VENTA_DETAL
                                  (PAIS_COD_PAIS,
                                   TIOP_COD_TIPO_OPER,
                                   TICA_COD_TIPO_CARG,
                                   COD_CLIE,
                                   DICA_FEC_REGI,
                                   DICA_CAM_PROC,
                                   COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                   COD_SUBG,
                                   COD_REGI,
                                   COD_ZONA,
                                   USU_CREA,
                                   FEC_CREA,
                                   EST_REGI,
                                   DICA_COR_DIRE_VENT)
                                VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                   substr(vsUA,1,2),
                                   substr(vsUA,3,2),
                                   substr(vsUA,5,4),
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec);

                          END IF;

                     END IF;

                 END IF;

                 --Si el tipo de UA es REGION
                 IF(vsTipUni = 'R') THEN
                     dbms_output.put_line('UA Tipo------------------------> R');
                     dbms_output.put_line('UA := '||vsCodSubge||vsCodRegi);
                     IF(TO_DATE(vsfechaRegi,'DD/MM/YYYY') <= TO_DATE(psfechaFactu,'DD/MM/YYYY')+1) THEN
                     dbms_output.put_line('Entro a Region');
                        BEGIN
                          SELECT c.cod_clie, d.cod_subg||d.cod_regi, c.fec_vent, c.fec_fact
                           INTO vsCodClie, vsUA, vsfecVenta, vsfecFactu
                          FROM zon_direc_venta_cabec c, zon_direc_venta_detal d, zon_tipo_cargo tc
                         WHERE c.cor_dire_vent = d.dica_cor_dire_vent
                               and c.cod_clie = d.cod_clie
                               and c.pais_cod_pais = d.pais_cod_pais
                               and c.fec_regi = d.dica_fec_regi
                               and c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper
                               and c.tica_cod_tipo_carg = d.tica_cod_tipo_carg
                               and c.cam_proc = d.dica_cam_proc
                               and c.tica_cod_tipo_carg = tc.cod_tipo_carg
                               and c.esca_cod_esta_carg = 'A'
                               and c.pais_cod_pais = pscodigoPais
                               and tc.rol_cod_rol in ('GR','GZ') --Solo Roles Gerentes
                               and tc.perf_cod_perf = 'TI'       --Solo Titulares
                               and c.tica_cod_tipo_carg = vsCargoBase --GR
                               and d.cod_subg = vsCodSubge
                               and d.cod_regi = vsCodRegi
                               order by c.fec_regi desc;
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            vsCodClie := NULL;
                        END;

                          dbms_output.put_line('--Inicio del Proceso de reasignacion de cargos');
                          IF(vsCodClie IS NOT NULL) THEN
                             dbms_output.put_line('--1 RETIRO DEL GR BASE (EN R)*********');
                             dbms_output.put_line('--Buscamos al GR BASE EN EL ZHG');
                             BEGIN
                               SELECT ZDHG.CLIE_COD_CLIE into vsCodClie
                               FROM ZON_DIREC_HISTO_GEREN ZDHG
                               WHERE ZDHG.UNI_ADMI = vsUA
                                     AND ZDHG.PAIS_COD_PAIS = pscodigoPais
                                     AND ZDHG.FEC_HAST IS NULL
                                     AND ZDHG.CAM_HAST IS NULL;
                             EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsCodClie := NULL;
                             END;

                             dbms_output.put_line('--Si el GR BASE existe en ZHG');
                             dbms_output.put_line('--Cerrar el cliente vigente con la FEC_HAST y PERD_OID_PERI_HAST');
                             IF(vsCodClie IS NOT NULL) THEN
                                  UPDATE ZON_DIREC_HISTO_GEREN ZDHG
                                    SET ZDHG.FEC_HAST = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                      ZDHG.CAM_HAST = vnPerioFechaRegi,
                                      ZDHG.UNI_ADMI = vsUA,
                                      ZDHG.USU_MODI = pscodigoUsuario,
                                      ZDHG.FEC_MODI = SYSDATE
                                    WHERE ZDHG.CLIE_COD_CLIE = vsCodClie
                                      AND ZDHG.UNI_ADMI = vsUA
                                      AND ZDHG.FEC_HAST IS NULL
                                      AND ZDHG.CAM_HAST IS NULL;

                                  --vnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(vsCodClie);
                                  BEGIN
                                    SELECT ZR.COD_REGI INTO regRegi
                                     FROM ZON_DIREC_REGIO ZR
                                     WHERE ZR.PAIS_COD_PAIS = pscodigoPais
                                       AND ZR.COD_REGI = SUBSTR(vsUA,3,2)
                                       AND ZR.CLIE_COD_CLIE = vsCodClie;
                                  EXCEPTION
                                     WHEN NO_DATA_FOUND THEN
                                       regRegi := NULL;
                                  END;

                                  dbms_output.put_line('--Actualizar tabla ZON_REGIO con clie_oid_clie NULL');
                                  IF(regRegi IS NOT NULL) THEN
                                   UPDATE ZON_DIREC_REGIO ZR
                                    SET    ZR.CLIE_COD_CLIE    = NULL,
                                           ZR.FEC_MODI    = SYSDATE,
                                           ZR.USU_MODI    = pscodigoUsuario
                                     WHERE ZR.PAIS_COD_PAIS = pscodigoPais
                                       AND ZR.COD_REGI = SUBSTR(vsUA,3,2)
                                       AND ZR.CLIE_COD_CLIE = vsCodClie;
                                  END IF;

                                  dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');

                                   BEGIN
                                      SELECT C.FEC_REGI_FIN INTO tieneFechaFin
                                        FROM ZON_DIREC_VENTA_CABEC C, ZON_DIREC_VENTA_DETAL D
                                       WHERE C.PAIS_COD_PAIS = D.PAIS_COD_PAIS
                                             AND C.TIOP_COD_TIPO_OPER = D.TIOP_COD_TIPO_OPER
                                             AND C.TICA_COD_TIPO_CARG = D.TICA_COD_TIPO_CARG
                                             AND C.COD_CLIE = D.COD_CLIE
                                             AND C.FEC_REGI = D.DICA_FEC_REGI
                                             AND C.CAM_PROC = D.DICA_CAM_PROC
                                             AND C.COR_DIRE_VENT = D.DICA_COR_DIRE_VENT
                                             AND C.PAIS_COD_PAIS = pscodigoPais
                                             AND C.COD_CLIE = vsCodClie
                                             AND C.ESCA_COD_ESTA_CARG = 'A'
                                             AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                             AND D.COD_SUBG = SUBSTR(vsUA,1,2)
                                             AND D.COD_REGI = SUBSTR(vsUA,3,2);
                                   EXCEPTION

                                     WHEN NO_DATA_FOUND THEN
                                       tieneFechaFin := NULL;
                                   END;

                                  IF(tieneFechaFin IS NOT NULL) THEN
                                     UPDATE ZON_DIREC_VENTA_CABEC C
                                      SET C.ESCA_COD_ESTA_CARG = 'I',
                                          C.IND_NOVE = 'A',
                                          C.USU_MODI = pscodigoUsuario,
                                          C.FEC_MODI = SYSDATE
                                      WHERE C.COD_CLIE = vsCodClie
                                        AND C.ESCA_COD_ESTA_CARG = 'A'
                                        AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                        AND C.PAIS_COD_PAIS = pscodigoPais;

                                  ELSE
                                      UPDATE ZON_DIREC_VENTA_CABEC C
                                      SET C.ESCA_COD_ESTA_CARG = 'I',
                                          C.IND_NOVE = 'A',
                                          C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')-1,
                                          C.USU_MODI = pscodigoUsuario,
                                          C.FEC_MODI = SYSDATE
                                      WHERE C.COD_CLIE = vsCodClie
                                        AND C.ESCA_COD_ESTA_CARG = 'A'
                                        AND C.TICA_COD_TIPO_CARG = vsCargoBase
                                        AND C.PAIS_COD_PAIS = pscodigoPais;

                                  END IF;

                                  dbms_output.put_line('--Grabamos en maestro directorio,  el retiro');
                                  dbms_output.put_line('--INSERTAMOS CABECERA');
                                  lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                  INSERT INTO ZON_DIREC_VENTA_CABEC
                                    (PAIS_COD_PAIS,
                                     TIOP_COD_TIPO_OPER,
                                     TICA_COD_TIPO_CARG,
                                     COD_CLIE,
                                     FEC_REGI,
                                     CAM_PROC,
                                     ESCA_COD_ESTA_CARG,
                                     MOTI_COD_MOTI_LICE,
                                     COD_CLIE_REEM,
                                     FEC_REGR,
                                     IND_ESTA,
                                     USU_CREA,
                                     FEC_CREA,
                                     EST_REGI,
                                     COR_DIRE_VENT,
                                     FEC_VENT,
                                     FEC_FACT,
                                     IND_NOVE,
                                     FEC_REGI_FIN)
                                  VALUES
                                      (pscodigoPais,
                                       'RE',
                                       vsCargoBase,
                                       vsCodClie,
                                       TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                       vnPerioFechaRegi,
                                       'I',
                                       NULL,
                                       NULL,
                                       NULL,
                                       'A',
                                       pscodigoUsuario,
                                       SYSDATE,
                                       1,
                                       lnOidCabec,
                                       TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                       TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                       'A',
                                       TO_DATE(vsfechaRegi,'DD/MM/YYYY'));

                                  dbms_output.put_line('--INSERTAMOS DETALLE');
                                  INSERT INTO ZON_DIREC_VENTA_DETAL
                                    (PAIS_COD_PAIS,
                                     TIOP_COD_TIPO_OPER,
                                     TICA_COD_TIPO_CARG,
                                     COD_CLIE,
                                     DICA_FEC_REGI,
                                     DICA_CAM_PROC,
                                     COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                     COD_SUBG,
                                     COD_REGI,
                                     COD_ZONA,
                                     USU_CREA,
                                     FEC_CREA,
                                     EST_REGI,
                                     DICA_COR_DIRE_VENT)
                                  VALUES
                                    (pscodigoPais,
                                     'RE',
                                     vsCargoBase,
                                     vsCodClie,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                     substr(vsUA,1,2),
                                     substr(vsUA,3,2),
                                     NULL,
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec);

                                    dbms_output.put_line('--2 CAMBIO DE CARGO (EN R)********');
                                    SELECT COUNT(1) INTO vsRegHG
                                      FROM ZON_DIREC_HISTO_GEREN ZDHG
                                        WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                           AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                           AND ZDHG.UNI_ADMI = vsUA;

                                    IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                      DELETE FROM  ZON_DIREC_HISTO_GEREN ZDHG
                                       WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                         AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                         AND ZDHG.UNI_ADMI = vsUA;

                                    END IF;

                                  dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                                  INSERT INTO ZON_DIREC_HISTO_GEREN
                                    (COR_HIST_GERE, pais_cod_pais,
                                     uni_admi, clie_cod_clie,
                                     fec_desd, fec_hast,
                                     cam_desd, cam_hast,
                                     usu_crea, fec_crea,
                                     usu_modi, fec_modi)
                                  VALUES
                                    (zon_direc_hger_seq.nextval, pscodigoPais,
                                     vsUA, vsCodGere,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'), NULL,
                                     vnPerioFechaRegi, NULL,
                                     pscodigoUsuario, SYSDATE,
                                     NULL, NULL);

                                  dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                                  UPDATE zon_direc_regio r
                                   SET r.clie_cod_clie = vsCodGere,
                                       r.usu_modi = pscodigoUsuario,
                                       r.fec_modi = SYSDATE
                                  WHERE r.pais_cod_pais = pscodigoPais
                                        and r.cod_regi = vsCodRegi;

                                  dbms_output.put_line('--REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                                  dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');
                                  UPDATE ZON_DIREC_VENTA_CABEC C
                                  SET C.ESCA_COD_ESTA_CARG = 'I',
                                      C.IND_NOVE = 'A',
                                      C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                      C.USU_MODI = pscodigoUsuario,
                                      C.FEC_MODI = SYSDATE
                                  WHERE C.COD_CLIE = vsCodGere
                                    AND C.ESCA_COD_ESTA_CARG = 'A'
                                    AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                    AND C.PAIS_COD_PAIS = pscodigoPais;

                                  dbms_output.put_line('--Grabamos en directorio AL GR TITULAR COMO GR BASE');
                                  dbms_output.put_line('--INSERTAMOS CABECERA');
                                  lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                                  INSERT INTO ZON_DIREC_VENTA_CABEC
                                    (PAIS_COD_PAIS,
                                     TIOP_COD_TIPO_OPER,
                                     TICA_COD_TIPO_CARG,
                                     COD_CLIE,
                                     FEC_REGI,
                                     CAM_PROC,
                                     ESCA_COD_ESTA_CARG,
                                     MOTI_COD_MOTI_LICE,
                                     COD_CLIE_REEM,
                                     FEC_REGR,
                                     IND_ESTA,
                                     USU_CREA,
                                     FEC_CREA,
                                     EST_REGI,
                                     COR_DIRE_VENT,
                                     FEC_VENT,
                                     FEC_FACT,
                                     IND_NOVE,
                                     FEC_REGI_FIN)
                                  VALUES
                                      (pscodigoPais,
                                       'NM',
                                       vsCargoBase,
                                       vsCodGere,
                                       TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                       vnPerioFechaRegi,
                                       'A',
                                       NULL,
                                       NULL,
                                       NULL,
                                       'A',
                                       pscodigoUsuario,
                                       SYSDATE,
                                       1,
                                       lnOidCabec,
                                       TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                       TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                       'A',
                                       TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                                  dbms_output.put_line('--INSERTAMOS DETALLE');
                                  INSERT INTO ZON_DIREC_VENTA_DETAL
                                    (PAIS_COD_PAIS,
                                     TIOP_COD_TIPO_OPER,
                                     TICA_COD_TIPO_CARG,
                                     COD_CLIE,
                                     DICA_FEC_REGI,
                                     DICA_CAM_PROC,
                                     COR_DIVE_DETA,--CORRELATIVO DE DETALLE
                                     COD_SUBG,
                                     COD_REGI,
                                     COD_ZONA,
                                     USU_CREA,
                                     FEC_CREA,
                                     EST_REGI,
                                     DICA_COR_DIRE_VENT)
                                  VALUES
                                    (pscodigoPais,
                                     'NM',
                                     vsCargoBase,
                                     vsCodGere,
                                     TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                     vnPerioFechaRegi,
                                     ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                     substr(vsUA,1,2),
                                     substr(vsUA,3,2),
                                     NULL,
                                     pscodigoUsuario,
                                     SYSDATE,
                                     1,
                                     lnOidCabec);

                             ELSE
                               dbms_output.put_line('--Si el GR BASE no existe en ZHG');

                               continue;

                             END IF;

                          ELSE
                            vsUA := vsCodSubge||vsCodRegi;
                            dbms_output.put_line('--1 CAMBIO DE CARGO----(EN R)******');
                             SELECT COUNT(1) INTO vsRegHG
                                FROM ZON_DIREC_HISTO_GEREN ZDHG
                                  WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                     AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                     AND ZDHG.UNI_ADMI = vsUA;

                              IF(vsRegHG > 0)THEN--SI HAY EL REGISTRO, SE ELIMINA
                                DELETE FROM  ZON_DIREC_HISTO_GEREN ZDHG
                                 WHERE ZDHG.PAIS_COD_PAIS = pscodigoPais
                                   AND ZDHG.CLIE_COD_CLIE = vsCodClie
                                   AND ZDHG.UNI_ADMI = vsUA;
                              END IF;

                              dbms_output.put_line('--SE REINCORPORA AL REGISTRO AL GERENTE TITULAR FUTURO.');
                              INSERT INTO ZON_DIREC_HISTO_GEREN
                                (COR_HIST_GERE, pais_cod_pais,
                                 uni_admi, clie_cod_clie,
                                 fec_desd, fec_hast,
                                 cam_desd, cam_hast,
                                 usu_crea, fec_crea,
                                 usu_modi, fec_modi)
                              VALUES
                                (zon_direc_hger_seq.nextval, pscodigoPais,
                                 vsUA, vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'), NULL,
                                 vnPerioFechaRegi, NULL,
                                 pscodigoUsuario, SYSDATE,
                                 NULL, NULL);

                              dbms_output.put_line('--ACTUALIZAMOS LA REGION CON EL GERENTE FUTURO');
                              UPDATE zon_direc_regio r
                               SET r.clie_cod_clie = vsCodGere,
                                   r.usu_modi = pscodigoUsuario,
                                   r.fec_modi = SYSDATE
                              WHERE r.pais_cod_pais = pscodigoPais
                                    and r.cod_regi = vsCodRegi;

                              dbms_output.put_line(' --REGISTRAMOS EN DIRECTORIO VENTAS LO REFERENTE AL CAMBIO DE CARGO');
                              dbms_output.put_line('--INACTIVAR en el Directorio al GR TITULAR');
                              UPDATE ZON_DIREC_VENTA_CABEC C
                              SET C.ESCA_COD_ESTA_CARG = 'I',
                                  C.IND_NOVE = 'A',
                                  C.USU_MODI = pscodigoUsuario,
                                  C.FEC_MODI = SYSDATE,
                                  C.FEC_REGI_FIN = TO_DATE(vsfechaRegi,'DD/MM/YYYY')
                              WHERE C.COD_CLIE = vsCodGere
                                AND C.ESCA_COD_ESTA_CARG = 'A'
                                AND C.TICA_COD_TIPO_CARG = vsTipCarg
                                AND C.PAIS_COD_PAIS = pscodigoPais;

                              dbms_output.put_line('--Grabamos en directorio, AL GR TITULAR COMO GR BASE');
                              dbms_output.put_line('--INSERTAMOS CABECERA');
                              lnOidCabec := ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL;
                              INSERT INTO ZON_DIREC_VENTA_CABEC
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 FEC_REGI,
                                 CAM_PROC,
                                 ESCA_COD_ESTA_CARG,
                                 MOTI_COD_MOTI_LICE,
                                 COD_CLIE_REEM,
                                 FEC_REGR,
                                 IND_ESTA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 COR_DIRE_VENT,
                                 FEC_VENT,
                                 FEC_FACT,
                                 IND_NOVE,
                                 FEC_REGI_FIN)
                              VALUES
                                  (pscodigoPais,
                                   'NM',
                                   vsCargoBase,
                                   vsCodGere,
                                   TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                   vnPerioFechaRegi,
                                   'A',
                                   NULL,
                                   NULL,
                                   NULL,
                                   'A',
                                   pscodigoUsuario,
                                   SYSDATE,
                                   1,
                                   lnOidCabec,
                                   TO_DATE(vsfechaVent,'DD/MM/YYYY'),
                                   TO_DATE(vsfechaFact,'DD/MM/YYYY'),
                                   'A',
                                   TO_DATE(vsfechaFin,'DD/MM/YYYY'));

                              dbms_output.put_line('--INSERTAMOS DETALLE');
                              INSERT INTO ZON_DIREC_VENTA_DETAL
                                (PAIS_COD_PAIS,
                                 TIOP_COD_TIPO_OPER,
                                 TICA_COD_TIPO_CARG,
                                 COD_CLIE,
                                 DICA_FEC_REGI,
                                 DICA_CAM_PROC,
                                 COR_DIVE_DETA,
                                 COD_SUBG,
                                 COD_REGI,
                                 COD_ZONA,
                                 USU_CREA,
                                 FEC_CREA,
                                 EST_REGI,
                                 DICA_COR_DIRE_VENT)
                              VALUES
                                (pscodigoPais,
                                 'NM',
                                 vsCargoBase,
                                 vsCodGere,
                                 TO_DATE(vsfechaRegi,'DD/MM/YYYY'),
                                 vnPerioFechaRegi,
                                 ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                                 substr(vsUA,1,2),
                                 substr(vsUA,3,2),
                                 NULL,
                                 pscodigoUsuario,
                                 SYSDATE,
                                 1,
                                 lnOidCabec);

                          END IF;

                     END IF;

                 END IF;

               END LOOP;

          CLOSE c_listGereActiDire;

    END IF;


    EXCEPTION
      WHEN  OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR zon_pr_react_gere_futur_fox: ' || ls_sqlerrm);
  END zon_pr_react_gere_futur_fox;

/***************************************************************************
    Descripcion       : Relaiza la retiro en el directorio
    Fecha Creacion    : 20/09/2010
    Autor             : Sergio Buchellli
    parametros
        psCodigoPais                codigo pais,
        psCodigoCargo               codigo cargo
        psCodigoCliente               codigo cliente
        psFechaRetiro               fecha retiro
        psCodigoUsuario          codigo usuario
        psMsgRetorno             mensaje retorno
  ***************************************************************************/
  PROCEDURE zon_pr_retir_direc
  (
    pscodigopais    IN VARCHAR2,
    pscodigocargo   IN VARCHAR2,
    pscodigocliente IN VARCHAR2,
    pscodigoregion  IN VARCHAR2,
    pscodigozona    IN VARCHAR2,
    psfecharetiro   IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    psfila          IN VARCHAR2,
    psmsgretorno    OUT VARCHAR2
  ) IS
    regparam            zon_tipo_cargo%ROWTYPE;
    regdirectorio       zon_direc_venta_cabec%ROWTYPE;
    lsretono            VARCHAR2(100) := ' ';
    lnexistegerente     NUMBER;
    lncont              NUMBER;
    lscodigoestado      zon_estat_cargo.cod_esta_carg%TYPE;
    lscodigosubgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
    lsua                VARCHAR2(10);
    lnoid               NUMBER;
  BEGIN

    SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

    IF (regparam.val_titu = '1') THEN

      --obtenemos UA
      IF (regparam.val_tipo_unid_admi = 'Z') THEN

        --OBTENEMOS LA UA
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO lsua
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND a.cod_zona = pscodigozona; --psCodigoZona;
      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN

        --OBTENEMOS LA ua
        SELECT cod_subg_vent || b.cod_regi
          INTO lsua
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND b.cod_regi = pscodigoregion; --psCodigoRegion;

      END IF;

      --actualizamos el gerente de zona quien reemplazo
      /*
       UPDATE ZON_HISTO_GEREN
      SET FEC_HAST = TO_DATE(psFechaRetiro,'dd/MM/yyyy'),
          USU_MODI = psCodigoUsuario,
          FEC_MODI= SYSDATE
      WHERE MARC_OID_MARC=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T')
      AND  CANA_OID_CANA=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD')
       AND UA=lsUA
       AND GERE=psCodigoCliente
       AND FEC_HAST IS NULL;*/

      /*                     IF(regParam.VAL_TIPO_UNID_ADMI='Z') THEN--IF(regParam.VAL_TIPO_UNID_ADMI='Z')THEN

                               UPDATE ZON_ZONA Z
                               SET Z.CLIE_OID_CLIE='',
                                   Z.FEC_ULTI_ACTU= SYSDATE
                               WHERE Z.COD_ZONA=psCodigoZona;
                           END IF;

                           IF(regParam.VAL_TIPO_UNID_ADMI='R')THEN


                               UPDATE ZON_REGIO R
                               SET R.CLIE_OID_CLIE='',
                                   R.FEC_ULTI_ACTU= SYSDATE
                               WHERE R.COD_REGI=psCodigoRegion;

                            END IF;
      */

      /*                  BEGIN
                       --actualiza como activo el cargo anterior ACTIVO
                        SELECT  * INTO regDirectorio
                        FROM (SELECT A.*
                              FROM ZON_DIREC_VENTA_CABEC A
                              WHERE A.COD_CLIE = psCodigoCliente
                               AND (ESCA_COD_ESTA_CARG='IT' OR ESCA_COD_ESTA_CARG='A')
                               AND TICA_COD_TIPO_CARG = psCodigoCargo
                              ORDER BY A.FEC_REGI DESC)
                       WHERE ROWNUM=1;

                       UPDATE ZON_DIREC_VENTA_CABEC
                       SET ESCA_COD_ESTA_CARG='I',
                          USU_MODI=psCodigoUsuario,
                          FEC_MODI =SYSDATE
                       WHERE COD_CLIE= regDirectorio.COD_CLIE
                        AND  TICA_COD_TIPO_CARG= regDirectorio.COD_CARG
                        AND TRUNC(FEC_REGI)= TRUNC(regDirectorio.FEC_OPER)
                        AND TIOP_COD_TIPO_OPER = regDirectorio.COD_DIRE_OPER;


                    EXCEPTION
                      WHEN OTHERS THEN
                       NULL; --NO HAY ESTATUS ANTERIOR
                    END;


      */ --grabamos en maestro directoio RETIRO
      BEGIN
        INSERT INTO zon_direc_venta_cabec
          (cod_clie,
           TICA_COD_TIPO_CARG,
           FEC_REGI,
           TIOP_COD_TIPO_OPER,
           ESCA_COD_ESTA_CARG,
           USU_CREA,
           FEC_CREA,
           IND_ESTA)
        VALUES
          (pscodigocliente,
           pscodigocargo,
           to_date(psfecharetiro,
                   'dd/MM/yyyy'),
           'RE',
           'A',
           pscodigousuario,
           SYSDATE,
           'G');
      EXCEPTION
        WHEN OTHERS THEN
          NULL; --YA EXISTE CABECEREA

      END;

      --INSERTAMOS DETALLE
      BEGIN

        INSERT INTO zon_direc_venta_detal
          (COR_DIVE_DETA,
           cod_clie,
           TICA_COD_TIPO_CARG,
           DICA_FEC_REGI,
           TIOP_COD_TIPO_OPER,
           cod_subg,
           cod_regi,
           cod_zona,
           USU_CREA,
           FEC_CREA)
        VALUES
          (zon_seq_direc_venta_detal.nextval,
           pscodigocliente,
           pscodigocargo,
           to_date(psfecharetiro,
                   'dd/MM/yyyy'),
           'RE',
           substr(lsua,
                  1,
                  2),
           substr(lsua,
                  3,
                  2),
           substr(lsua,
                  5,
                  4),
           pscodigousuario,
           SYSDATE);

      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;

    END IF; --fin titular
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ZON_PR_RETIR_DIREC: ' || ls_sqlerrm);
  END zon_pr_retir_direc;

  /***************************************************************************
    Descripcion       : Realiza la Reactivacion de Licencia
    Fecha Creacion    : 30/03/2011
    Autor             : Christian Luque
    parametros
        psCodigoPais                codigo pais,
        psCodigoMarca               codigo marca
        psCodigoCanal               codigo canal
        psFechaProceso              fecha proceso
        psPeriodoActivo             periodo activo
        psUsuario                   Usuario del proceso
  ***************************************************************************/
  PROCEDURE zon_pr_react_licen
  (
    pscodigopais    IN VARCHAR2,
    pscodigomarca   IN VARCHAR2,
    pscodigocanal   IN VARCHAR2,
    psfechaproceso  IN VARCHAR2,
    psperiodoactivo IN VARCHAR2,
    psUsuario       IN VARCHAR2
  ) IS

    -- Identificamos a todas las consultoras que estna de licencia y que van a reactivarse
    CURSOR clisreapen IS
    SELECT
    C.COD_CLIE,
    c.TICA_COD_TIPO_CARG,
    CASE c.TICA_COD_TIPO_CARG
               WHEN 'GZ' THEN
            d.cod_subg || d.cod_regi || d.cod_zona
               WHEN 'GR' THEN
            d.cod_subg || d.cod_regi
               ELSE
            decode(d.cod_zona, NULL, d.cod_subg || d.cod_regi, d.cod_subg || d.cod_zona)
             END AS ua,
    C.FEC_REGR,
    C.TIOP_COD_TIPO_OPER,
    C.FEC_REGI,
    C.CAM_PROC,
    D.COD_SUBG,
    D.COD_REGI,
    D.COD_ZONA,
    C.FEC_REGI
    FROM zon_direc_venta_cabec c, zon_direc_venta_detal d
    WHERE (    (c.tiop_cod_tipo_oper = 'LI')
        AND (c.esca_cod_esta_carg = 'IT')
        AND (c.ind_esta = 'A')
        AND (c.est_regi = '1')
        AND (TO_CHAR(c.fec_regr, 'DD/MM/YYYY') = TO_CHAR(TO_DATE(psfechaproceso, 'DD/MM/YYYY') + 1, 'DD/MM/YYYY'))
        AND (c.pais_cod_pais = d.pais_cod_pais)
        AND (c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper)
        AND (c.tica_cod_tipo_carg = d.tica_cod_tipo_carg)
        AND (c.cod_clie = d.cod_clie)
        AND (c.fec_regi = d.dica_fec_regi)
        AND (c.cam_proc = d.dica_cam_proc)
        AND (c.cor_dire_vent = d.dica_cor_dire_vent)
       );

    TYPE reactlicenrecord IS RECORD(
        codigoCliente   zon_direc_venta_cabec.cod_clie%type,
        tipoCargo       zon_direc_venta_cabec.tica_cod_tipo_carg%type,
      ua         VARCHAR2(10),
        fechaRegreso    zon_direc_venta_cabec.fec_regr%type,
        tipoOperacion   zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE,
        fechaRegistro   zon_direc_venta_cabec.fec_regi%TYPE,
        campanyaProceso zon_direc_venta_cabec.cam_proc%TYPE,
        codigoSubgeren  zon_direc_venta_detal.COD_SUBG%TYPE,
        codigoRegion    zon_direc_venta_detal.COD_REGI%TYPE,
        codigoZona      zon_direc_venta_detal.COD_ZONA%TYPE,
        fechaSalida     zon_direc_venta_cabec.fec_regi%type
    );

    TYPE reactlicentab IS TABLE OF reactlicenrecord;
    reactlicen reactlicentab;

    lsClienteReemplazante       zon_direc_venta_cabec.cod_clie%TYPE;
    lsTipoCargoReemplazante     zon_direc_venta_cabec.tica_cod_tipo_carg%type;
    lsUAReemplazante            VARCHAR2(10);
    lsTipoOperaReemplazante     zon_direc_venta_cabec.tiop_cod_tipo_oper%TYPE;
    lsFechaReemplazante         zon_direc_venta_cabec.fec_regi%TYPE;
    lsCampanyaReemplazante      zon_direc_venta_cabec.cam_proc%TYPE;
    lsCodigoSubgReemplazante    zon_direc_venta_detal.COD_SUBG%TYPE;
    lsCodigoRegionReemplazante  zon_direc_venta_detal.COD_REGI%TYPE;
    lsCodigoZonaReemplazante    zon_direc_venta_detal.COD_ZONA%TYPE;
    lnCorrelativoCabecera       zon_direc_venta_cabec.cor_dire_vent%TYPE;

    zon_id_pais  seg_pais.oid_pais%TYPE;
    zon_id_canal seg_canal.oid_cana%TYPE;
    zon_id_marca seg_marca.oid_marc%TYPE;

    lnIdCabecera    zon_direc_venta_cabec.cor_dire_vent%TYPE;

  BEGIN

    zon_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais, TRUE);
    zon_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T', TRUE);
    zon_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD', TRUE);

    OPEN clisreapen;
    LOOP
      FETCH clisreapen BULK COLLECT
        INTO reactlicen LIMIT w_filas;

      IF reactlicen.count > 0 THEN
        FOR i IN reactlicen.first .. reactlicen.last
        LOOP

                BEGIN
                    -- Recuperar el codigo de la cliente reemplazante
                    SELECT GERE
                    INTO lsClienteReemplazante
                    FROM ZON_HISTO_GEREN
                    where ua = reactlicen(i).ua
                    and FEC_HAST is null
                    and PERD_OID_PERI_HAST is null
                    and rownum = 1;

                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        lsClienteReemplazante := '';
                END;

                IF LENGTH(lsClienteReemplazante) > 0 THEN

                    BEGIN
                        --Buscar en el Directorio de venta con el codigo del cliente y UA de la reemplazante
                        SELECT
                        C.COD_CLIE,
                        c.TICA_COD_TIPO_CARG,
                        CASE c.TICA_COD_TIPO_CARG
                            WHEN 'GZ' THEN
                                d.cod_subg || d.cod_regi || d.cod_zona
                            WHEN 'GR' THEN
                                d.cod_subg || d.cod_regi
                            ELSE
                                decode(d.cod_zona, NULL, d.cod_subg || d.cod_regi, d.cod_subg || d.cod_zona)
                        END AS ua,
                        C.TIOP_COD_TIPO_OPER,
                        C.FEC_REGI,
                        C.CAM_PROC,
                        D.COD_SUBG,
                        D.COD_REGI,
                        D.COD_ZONA,
                        c.cor_dire_vent
                        INTO lsClienteReemplazante, lsTipoCargoReemplazante, lsUAReemplazante, lsTipoOperaReemplazante, lsFechaReemplazante, lsCampanyaReemplazante, lsCodigoSubgReemplazante, lsCodigoRegionReemplazante, lsCodigoZonaReemplazante, lnCorrelativoCabecera
                        FROM zon_direc_venta_cabec c, zon_direc_venta_detal d
                        WHERE ( c.COD_CLIE = lsClienteReemplazante
                        AND (
                                    CASE c.TICA_COD_TIPO_CARG
                     WHEN 'GZ' THEN
                                            d.cod_subg || d.cod_regi || d.cod_zona
                     WHEN 'GR' THEN
                                            d.cod_subg || d.cod_regi
                     ELSE
                                            decode(d.cod_zona, NULL, d.cod_subg || d.cod_regi, d.cod_subg || d.cod_zona)
                                    END) = reactlicen(i).ua
                            AND (c.esca_cod_esta_carg = 'A')
                            AND (c.ind_esta = 'A')
                            AND (c.est_regi = '1')
                            AND (c.pais_cod_pais = d.pais_cod_pais)
                            AND (c.tiop_cod_tipo_oper = d.tiop_cod_tipo_oper)
                            AND (c.tica_cod_tipo_carg = d.tica_cod_tipo_carg)
                            AND (c.cod_clie = d.cod_clie)
                            AND (c.fec_regi = d.dica_fec_regi)
                            AND (c.cam_proc = d.dica_cam_proc)
                            AND (c.cor_dire_vent = d.dica_cor_dire_vent)
                           );

                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            lsClienteReemplazante := '';
                    END;

                    IF LENGTH(lsClienteReemplazante) > 0 THEN
                        -- HASTA ESTE PUNTO SE HA UBICADO A LA REEMPLAZANTE

                        --Damos de baja a la Reemplazante
                        IF lsTipoCargoReemplazante = 'GR' OR lsTipoCargoReemplazante = 'GZ' THEN

                            UPDATE ZON_HISTO_GEREN
                            SET
                                FEC_HAST = reactlicen(i).fechaRegreso,
                                PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_perio_fecha(pscodigopais, 'T', 'VD', reactlicen(i).fechaRegreso)
                            WHERE UA = lsUAReemplazante
                            AND FEC_HAST is null
                            AND PERD_OID_PERI_HAST IS NULL;

                            IF lsTipoCargoReemplazante = 'GR' THEN

                                UPDATE ZON_REGIO
                                SET
                                    CLIE_OID_CLIE = NULL,
                                    FEC_ULTI_ACTU = SYSDATE
                                WHERE COD_REGI = lsCodigoRegionReemplazante;

                            ELSIF lsTipoCargoReemplazante = 'GZ' THEN

                                UPDATE ZON_ZONA
                                SET
                                    CLIE_OID_CLIE = NULL,
                                    FEC_ULTI_ACTU = SYSDATE
                                WHERE COD_ZONA = lsCodigoZonaReemplazante;

                            END IF;

                            --Actualizamos el directorio

                            UPDATE ZON_DIREC_VENTA_CABEC
                            SET
                                ESCA_COD_ESTA_CARG = 'I',
                                USU_MODI = psUsuario,
                                FEC_MODI = SYSDATE
                            WHERE PAIS_COD_PAIS = pscodigopais
                            AND TIOP_COD_TIPO_OPER = lsTipoOperaReemplazante
                            AND TICA_COD_TIPO_CARG = lsTipoCargoReemplazante
                            AND COD_CLIE = lsClienteReemplazante
                            AND FEC_REGI = lsFechaReemplazante
                            AND CAM_PROC = lsCampanyaReemplazante
                            AND cor_dire_vent = lnCorrelativoCabecera;

                        END IF;
                        -- --

                        -- Damos de alta a la que esta retornando de la licencia
                        IF reactlicen(i).tipoCargo = 'GZ' THEN

                            UPDATE ZON_ZONA
                            SET
                                clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = reactlicen(i).codigoCliente),
                                FEC_ULTI_ACTU = SYSDATE
                            WHERE COD_ZONA = reactlicen(i).codigoZona
                            AND ZORG_OID_REGI IN(SELECT OID_REGI FROM ZON_REGIO WHERE COD_REGI = reactlicen(i).codigoRegion);

                        ELSIF reactlicen(i).tipoCargo = 'GR' THEN

                            UPDATE ZON_REGIO
                            SET
                                clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = reactlicen(i).codigoCliente),
                                FEC_ULTI_ACTU = SYSDATE
                            WHERE COD_REGI = reactlicen(i).codigoRegion;

                        END IF;

                        IF reactlicen(i).tipoCargo = 'GZ' OR reactlicen(i).tipoCargo = 'GR' THEN

            INSERT INTO zon_histo_geren
              (oid_hist_gere,
               marc_oid_marc,
               cana_oid_cana,
               ua,
               gere,
               fec_desd,
               perd_oid_peri_desd,
                                           pais_oid_pais)
            VALUES
              (zon_hger_seq.nextval,
               zon_id_marca,
               zon_id_canal,
                                           reactlicen(i).ua,
                                           reactlicen(i).codigoCliente,
                                           reactlicen(i).fechaRegreso,
                                           GEN_PKG_GENER.gen_fn_devuelve_perio_fecha(pscodigopais, 'T', 'VD', reactlicen(i).fechaSalida),
                                           zon_id_pais);
                        END IF;

                        lnIdCabecera := ZON_SEQ_DIREC_VENTA_CABEC.nextval;

                        INSERT INTO zon_direc_venta_cabec(
                            pais_cod_pais,      tiop_cod_tipo_oper,
                            tica_cod_tipo_carg, cod_clie,
                            fec_regi,           cam_proc,
                            esca_cod_esta_carg, ind_esta,
                            usu_crea,           fec_crea,
                            est_regi,           cor_dire_vent)
                        VALUES(
                            pscodigopais,               'RA',
                            reactlicen(i).tipoCargo,    reactlicen(i).codigoCliente,
                            reactlicen(i).fechaRegreso, GEN_PKG_GENER.gen_fn_devuelve_perio_fecha(pscodigopais, 'T', 'VD', reactlicen(i).fechaRegreso),
                            'A',                        'A',
                            psUsuario,                  SYSDATE,
                            '1',                        lnIdCabecera);

                        INSERT INTO zon_direc_venta_detal(
                            pais_cod_pais,      tiop_cod_tipo_oper,
                            tica_cod_tipo_carg, cod_clie,
                            dica_fec_regi,      dica_cam_proc,
                            dica_cor_dire_vent, cor_dive_deta,
                            cod_subg,           cod_regi,
                            cod_zona,           usu_crea,
                            fec_crea,           est_regi)
                        VALUES(
                            pscodigopais,               'RA',
                            reactlicen(i).tipoCargo,    reactlicen(i).codigoCliente,
                            reactlicen(i).fechaRegreso, GEN_PKG_GENER.gen_fn_devuelve_perio_fecha(pscodigopais, 'T', 'VD', reactlicen(i).fechaRegreso),
                            lnIdCabecera,               ZON_SEQ_DIREC_VENTA_DETAL.nextval,
                            reactlicen(i).codigoSubgeren,   reactlicen(i).codigoRegion,
                            reactlicen(i).codigoZona,       psUsuario,
                            SYSDATE,                        '1');
                        -- --

            END IF;

            END IF;


            END LOOP;
          END IF;

      EXIT WHEN clisreapen%NOTFOUND;
    END LOOP;

    CLOSE clisreapen;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_REACT_LICEN: ' || ls_sqlerrm);
  END zon_pr_react_licen;

  /***************************************************************************
    Descripcion       : Realiza Aprobacion Operacion
    Fecha Creacion    : 04/04/2011
    Autor             : Christian Luque
    parametros
        psCodigoPais                 codigo pais
        psCodigoUsuario              codigo usuario
        psCodigoOperacion            codigo operacion
        psCodigoConsultora           codigo consultora
        psCodigoConsultoraReemplazo  codigo consultora reemplazo
        psFechaProceso               fecha proceso
        psCodigoSubgerencia          codigo subgerencia
        psCodigoRegion               codigo region
        psCodigoZona                 codigo zona
        psCodigoSubgerenciaActual    codigo subgerencia
        psCodigoRegionActual         codigo region
        psCodigoZonaActual           codigo zona
        psMensajeError               mensaje error

  ***************************************************************************/
  PROCEDURE zon_pr_aprob_opera
  (
    pscodigopais                IN VARCHAR2,
    pscodigousuario             IN VARCHAR2,
    pscodigoconsultora          IN VARCHAR2,
    pscodigoconsultorareemplazo IN VARCHAR2,
    pscodigocargo               IN VARCHAR2,
    pscodigooperacion           IN VARCHAR2,
    psfechaproceso              IN VARCHAR2,
    pscodigosubgerencia         IN VARCHAR2,
    pscodigoregion              IN VARCHAR2,
    pscodigozona                IN VARCHAR2,
    pscodigosubgerenciaactual   IN VARCHAR2,
    pscodigoregionactual        IN VARCHAR2,
    pscodigozonaactual          IN VARCHAR2,
    psfechasalida               IN VARCHAR2,
    psfecharegreso              IN VARCHAR2,
    pscodigolicencia            IN VARCHAR2,
    pserror                     OUT VARCHAR2
  )

   IS

    zon_id_pais   seg_pais.oid_pais%TYPE;
    zon_id_canal  seg_canal.oid_cana%TYPE;
    zon_id_marca  seg_marca.oid_marc%TYPE;
    id_consu      mae_clien.oid_clie%TYPE;
    id_consu_reem mae_clien.oid_clie%TYPE;

    zon_oid_clien zon_zona.clie_oid_clie%TYPE;
    band          NUMERIC;
    regparam      zon_tipo_cargo%ROWTYPE;
    regparamdetal zon_direc_venta_detal%ROWTYPE;
    regparamcabec zon_direc_venta_cabec%ROWTYPE;
    lsuaanterior  VARCHAR2(10);
    lsuanueva     VARCHAR2(10);
    ua            VARCHAR2(10);
    nele          NUMERIC;

  BEGIN
    band         := 1;
    zon_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,TRUE);
    zon_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T', TRUE);
    zon_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD',TRUE);
    IF pscodigoconsultora IS NOT NULL THEN
      id_consu := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigoconsultora);
    END IF;
    IF pscodigoconsultorareemplazo IS NOT NULL THEN
      id_consu_reem := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigoconsultorareemplazo);
    END IF;
    pserror := ' ';

    --PROCESO A REALIZAR CUANDO EL TIPO DE OPERACION ES INGRESO
    IF pscodigooperacion = 'IN' THEN

      /*UPDATE zon_direc_venta_cabec
         SET FEC_REGI = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND TIOP_COD_TIPO_OPER = pscodigooperacion
         AND FEC_REGI = psfechaproceso;

      UPDATE zon_direc_venta_detal
         SET DICA_FEC_REGI = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND DICA_FEC_REGI = psfechaproceso
         AND TIOP_COD_TIPO_OPER = pscodigooperacion;*/

      --TIPO DE UA
      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

      IF regparam.val_tipo_unid_admi = 'Z' THEN
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO lsuanueva
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND a.cod_zona = pscodigozona;
      END IF;

      IF regparam.val_tipo_unid_admi = 'R' THEN

        --OBTENEMOS LA ua
        SELECT cod_subg_vent || b.cod_regi
          INTO lsuanueva
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND b.cod_regi = pscodigoregion;

      END IF;

      IF regparam.val_titu = 1 THEN
        INSERT INTO zon_histo_geren
          (oid_hist_gere,
           marc_oid_marc,
           cana_oid_cana,
           ua,
           gere,
           fec_desd,
           fec_hast,
           pais_oid_pais,
           usu_modi,
           fec_modi)
        VALUES
          (zon_hger_seq.nextval,
           zon_id_marca,
           zon_id_canal,
           lsuanueva,
           pscodigoconsultora,
           SYSDATE,
           NULL,
           zon_id_pais,
           pscodigousuario,
           SYSDATE);

      END IF;

      IF regparam.val_tipo_unid_admi = 'Z' AND regparam.val_titu = 1 THEN
        SELECT clie_oid_clie INTO zon_oid_clien FROM zon_zona WHERE cod_zona = pscodigozona;
        IF zon_oid_clien IS NOT NULL THEN
          pserror := 'ZTT';
          UPDATE zon_direc_venta_cabec
             SET IND_ESTA = 'R'
           WHERE cod_clie = pscodigoconsultora
             AND TICA_COD_TIPO_CARG = pscodigocargo
             AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(SYSDATE,'dd/mm/yyyy')
             AND TIOP_COD_TIPO_OPER = pscodigooperacion;
          band := 2;
        ELSE
          UPDATE zon_zona SET clie_oid_clie = id_consu WHERE cod_zona = pscodigozona;
        END IF;
      END IF;

      IF regparam.val_tipo_unid_admi = 'R' AND regparam.val_titu = 1 THEN
        SELECT clie_oid_clie INTO zon_oid_clien FROM zon_regio WHERE cod_regi = pscodigoregion;
        IF zon_oid_clien IS NOT NULL THEN
          pserror := 'RTT';
          UPDATE zon_direc_venta_cabec
             SET IND_ESTA = 'R'
           WHERE cod_clie = pscodigoconsultora
             AND TICA_COD_TIPO_CARG = pscodigocargo
             AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(SYSDATE,'dd/mm/yyyy')
             AND TIOP_COD_TIPO_OPER = pscodigooperacion;
          band := 2;
        ELSE
          UPDATE zon_regio SET clie_oid_clie = id_consu WHERE cod_regi = pscodigoregion;
        END IF;
      END IF;

      IF band = 1 THEN
        UPDATE zon_direc_venta_cabec
           SET IND_ESTA = 'A'
         WHERE cod_clie = pscodigoconsultora
           AND TICA_COD_TIPO_CARG = pscodigocargo
           AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(SYSDATE,'dd/mm/yyyy')
           AND TIOP_COD_TIPO_OPER = pscodigooperacion;
      END IF;

    END IF;

    --PROCESO A REALIZAR CUANDO EL TIPO DEO PERACION ES NOMBRAMIENTO
    IF pscodigooperacion = 'NM' THEN

      /*UPDATE zon_direc_venta_cabec
         SET FEC_REGI = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND TIOP_COD_TIPO_OPER = pscodigooperacion
         AND FEC_REGI = psfechaproceso;

      UPDATE zon_direc_venta_detal
         SET DICA_FEC_REGI = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND TIOP_COD_TIPO_OPER = pscodigooperacion
         AND DICA_FEC_REGI = psfechaproceso;*/

      --TIPO DE UA
      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

      IF regparam.val_tipo_unid_admi = 'Z' THEN
        SELECT *
          INTO regparamdetal
          FROM zon_direc_venta_detal
         WHERE cod_clie = pscodigoconsultora
           AND TICA_COD_TIPO_CARG = pscodigocargo
           AND DICA_FEC_REGI = psfechaproceso
           AND TIOP_COD_TIPO_OPER = pscodigooperacion
           AND cod_subg || cod_regi || cod_zona =
               pscodigosubgerencia || pscodigoregion || pscodigozona;
      END IF;
      IF regparam.val_tipo_unid_admi = 'R' THEN
        SELECT d.*
          INTO regparamdetal
          FROM zon_direc_venta_detal d
         WHERE d.cod_clie = pscodigoconsultora
           AND d.TICA_COD_TIPO_CARG = pscodigocargo
           AND TO_CHAR(d.DICA_FEC_REGI,'dd/mm/yyyy') = psfechaproceso
           AND d.TIOP_COD_TIPO_OPER = pscodigooperacion
           AND d.cod_subg || d.cod_regi = pscodigosubgerencia || pscodigoregion;
      END IF;

      IF regparam.val_tipo_unid_admi = 'Z' AND regparam.val_titu = 1 THEN
        UPDATE zon_zona SET clie_oid_clie = id_consu WHERE cod_zona = regparamdetal.cod_zona;
      END IF;

      IF regparam.val_tipo_unid_admi = 'R' AND regparam.val_titu = 1 THEN
        UPDATE zon_regio SET clie_oid_clie = id_consu WHERE cod_regi = regparamdetal.cod_regi;
      END IF;

      --OBTIENE EL REGISTRO DEL ULTIMO CARGO PARA EL CLIENTE
      SELECT x.*
        INTO regparamcabec
        FROM (SELECT *
                FROM zon_direc_venta_cabec a
               WHERE a.cod_clie = pscodigoconsultora
                 AND a.ESCA_COD_ESTA_CARG = 'A'
                 AND a.IND_ESTA = 'A'
               ORDER BY a.FEC_REGI DESC) x
       WHERE x.cod_clie = pscodigoconsultora
         AND x.ESCA_COD_ESTA_CARG = 'A'
         AND rownum = 1;

      UPDATE zon_direc_venta_cabec
         SET IND_ESTA = 'A',
             ESCA_COD_ESTA_CARG = 'A'
       WHERE TICA_COD_TIPO_CARG = pscodigocargo
         AND TIOP_COD_TIPO_OPER = pscodigooperacion
         AND cod_clie = pscodigoconsultora
         AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(SYSDATE,'dd/mm/yyyy');

      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = regparamcabec.TICA_COD_TIPO_CARG;

      IF regparam.val_tipo_unid_admi = 'Z' THEN
        SELECT *
          INTO regparamdetal
          FROM zon_direc_venta_detal
         WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
           AND cod_clie = regparamcabec.cod_clie
           AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
           AND TO_CHAR(DICA_FEC_REGI,'dd/mm/yyyy') = TO_CHAR(regparamcabec.FEC_REGI,'dd/mm/yyyy')
           AND cod_subg = pscodigosubgerenciaactual
           AND cod_regi = pscodigoregionactual
           AND cod_zona = pscodigozonaactual;
      END IF;
      IF regparam.val_tipo_unid_admi = 'R' THEN
        SELECT *
          INTO regparamdetal
          FROM zon_direc_venta_detal
         WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
           AND cod_clie = regparamcabec.cod_clie
           AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
           AND TO_CHAR(DICA_FEC_REGI,'dd/mm/yyyy') = TO_CHAR(regparamcabec.FEC_REGI,'dd/mm/yyyy')
           AND cod_subg = pscodigosubgerenciaactual
           AND cod_regi = pscodigoregionactual;
      END IF;

      UPDATE zon_direc_venta_cabec
         SET IND_ESTA = 'A',
             ESCA_COD_ESTA_CARG = 'I'
       WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
         AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
         AND cod_clie = regparamcabec.cod_clie
         AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(regparamcabec.FEC_REGI,'dd/mm/yyyy');

      IF regparamcabec.TICA_COD_TIPO_CARG = 'GZ' OR regparamcabec.TICA_COD_TIPO_CARG = 'GR' THEN

        --obtenemos UA anterior
        IF (regparam.val_tipo_unid_admi = 'Z') THEN

          --OBTENEMOS LA UA
          SELECT cod_subg_vent || b.cod_regi || a.cod_zona
            INTO lsuaanterior
            FROM zon_zona            a,
                 zon_regio           b,
                 zon_sub_geren_venta c
           WHERE a.zorg_oid_regi = b.oid_regi
             AND c.oid_subg_vent = b.zsgv_oid_subg_vent
             AND a.cod_zona = regparamdetal.cod_zona;
        END IF;

        IF (regparam.val_tipo_unid_admi = 'R') THEN

          --OBTENEMOS LA UA
          SELECT cod_subg_vent || b.cod_regi
            INTO lsuaanterior
            FROM zon_regio           b,
                 zon_sub_geren_venta c
           WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
             AND b.cod_regi = regparamdetal.cod_regi;

        END IF;

        UPDATE zon_histo_geren
           SET fec_hast = trunc(SYSDATE)
         WHERE marc_oid_marc = zon_id_marca
           AND cana_oid_cana = zon_id_canal
           AND ua = lsuaanterior
           AND gere = regparamdetal.cod_clie;

        IF regparamcabec.TICA_COD_TIPO_CARG = 'GR' THEN
          UPDATE zon_regio SET clie_oid_clie = NULL WHERE cod_regi = regparamdetal.cod_regi;
        END IF;

        IF regparamcabec.TICA_COD_TIPO_CARG = 'GZ' THEN
          UPDATE zon_zona SET clie_oid_clie = NULL WHERE cod_zona = regparamdetal.cod_zona;
        END IF;

      END IF;

      INSERT INTO zon_histo_geren
        (oid_hist_gere,
         marc_oid_marc,
         cana_oid_cana,
         ua,
         gere,
         fec_desd,
         fec_hast,
         pais_oid_pais,
         perd_oid_peri_desd,
         perd_oid_peri_hast,
         cod_lide_clas,
         acfi_exig_secc_nomb,
         num_acti_fina_secc,
         acfi_exig_zona_nomb,
         num_acti_fina_zona,
         ind_desv_auto,
         usu_modi,
         fec_modi)
      VALUES
        (zon_hger_seq.nextval,
         zon_id_marca,
         zon_id_canal,
         regparamdetal.cod_subg || regparamdetal.cod_regi || regparamdetal.cod_zona,
         pscodigoconsultora,
         SYSDATE,
         NULL,
         zon_id_pais,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL);

    END IF;

    --PROCESO A REALIZAR CUANDO EL TIPO DE OPERACION ES ROTACION
    IF pscodigooperacion = 'RO' THEN

      --OBTIENE EL REGISTRO DEL ULTIMO CARGO PARA EL CLIENTE
      /*SELECT COUNT(*)
        INTO nele
        FROM (SELECT *
                FROM zon_direc_venta_cabec a
               WHERE a.cod_clie = pscodigoconsultora
                 AND a.ESCA_COD_ESTA_CARG = 'A'
                 AND a.IND_ESTA = 'A'
               ORDER BY a.FEC_REGI DESC) x
       WHERE x.cod_clie = pscodigoconsultora
         AND x.ESCA_COD_ESTA_CARG = 'A'
         AND rownum = 1;

      IF nele = 1 THEN

        SELECT x.*
          INTO regparamcabec
          FROM (SELECT *
                  FROM zon_direc_venta_cabec a
                 WHERE a.cod_clie = pscodigoconsultora
                   AND a.ESCA_COD_ESTA_CARG = 'A'
                   AND a.IND_ESTA = 'A'
                 ORDER BY a.FEC_REGI DESC) x
         WHERE x.cod_clie = pscodigoconsultora
           AND x.ESCA_COD_ESTA_CARG = 'A'
           AND rownum = 1;

        UPDATE zon_direc_venta_cabec
           SET IND_ESTA = 'A',
               ESCA_COD_ESTA_CARG = 'I'
         WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
           AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
           AND cod_clie = regparamcabec.cod_clie
           AND FEC_REGI = regparamcabec.FEC_REGI;
      END IF;*/

      UPDATE zon_direc_venta_cabec
         SET IND_ESTA = 'A',
             FEC_REGI      = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = psfechaproceso
         AND TIOP_COD_TIPO_OPER = pscodigooperacion;

      UPDATE zon_direc_venta_detal
         SET DICA_FEC_REGI = trunc(SYSDATE)
       WHERE cod_clie = pscodigoconsultora
         AND TICA_COD_TIPO_CARG = pscodigocargo
         AND TO_CHAR(DICA_FEC_REGI,'dd/mm/yyyy') = psfechaproceso
         AND TIOP_COD_TIPO_OPER = pscodigooperacion;

      --TIPO DE UA
      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

      IF (regparam.val_tipo_unid_admi = 'Z') THEN
        --OBTENEMOS LA UA
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO ua
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND a.cod_zona = pscodigozonaactual;
      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN
        --OBTENEMOS LA UA
        SELECT cod_subg_vent || b.cod_regi
          INTO ua
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND b.cod_regi = pscodigoregionactual;
      END IF;

      UPDATE zon_histo_geren
         SET fec_hast = TO_DATE(psfechaproceso,'dd/MM/yyyy')
       WHERE marc_oid_marc = zon_id_marca
         AND cana_oid_cana = zon_id_canal
         AND ua = pscodigosubgerencia || pscodigoregion || pscodigozona
         AND gere = pscodigoconsultora;

        SELECT x.*
        INTO regparamcabec
        FROM (SELECT *
                FROM zon_direc_venta_cabec a
               WHERE a.cod_clie = pscodigoconsultora
                 AND a.ESCA_COD_ESTA_CARG = 'A'
                 AND a.IND_ESTA = 'A'
               ORDER BY a.FEC_REGI DESC) x
       WHERE x.cod_clie = pscodigoconsultora
         AND x.ESCA_COD_ESTA_CARG = 'A'
         AND rownum = 1;

      UPDATE zon_direc_venta_cabec
        SET ESCA_COD_ESTA_CARG = 'I'
       WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
         AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
         AND IND_ESTA = 'A'
         AND cod_clie = regparamcabec.cod_clie
         AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(regparamcabec.FEC_REGI,'dd/mm/yyyy');

      INSERT INTO zon_histo_geren
        (oid_hist_gere,
         marc_oid_marc,
         cana_oid_cana,
         ua,
         gere,
         fec_desd,
         fec_hast,
         pais_oid_pais,
         usu_modi,
         fec_modi)
      VALUES
        (zon_hger_seq.nextval,
         zon_id_marca,
         zon_id_canal,
         ua,
         pscodigoconsultora,
         SYSDATE,
         NULL,
         zon_id_pais,
         pscodigousuario,
         SYSDATE);

      IF (regparam.val_tipo_unid_admi = 'Z') THEN
        UPDATE zon_zona SET clie_oid_clie = id_consu WHERE cod_zona = pscodigozonaactual;

      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN
        UPDATE zon_regio SET clie_oid_clie = id_consu WHERE cod_regi = pscodigoregionactual;
      END IF;

    END IF;

    --PROCESO A REALIZAR CUANDO EL TIPO DE OPERACION ES INGRESO
    IF pscodigooperacion = 'LI' THEN

      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

      --ES TITULAR
      IF regparam.val_titu = 1 THEN
        UPDATE zon_histo_geren
           SET fec_hast = to_date(psfechasalida) - 1
         WHERE fec_hast = NULL
           AND gere = pscodigoconsultora;
      END IF;

      IF (regparam.val_tipo_unid_admi = 'Z') THEN
        --OBTENEMOS LA UA
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO ua
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND a.cod_zona = pscodigozona;
      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN

        --OBTENEMOS LA UA
        SELECT cod_subg_vent || b.cod_regi
          INTO ua
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND b.cod_regi = pscodigoregion;

      END IF;

       SELECT x.*
        INTO regparamcabec
        FROM (SELECT *
                FROM zon_direc_venta_cabec a
               WHERE a.cod_clie = pscodigoconsultora
                 AND a.ESCA_COD_ESTA_CARG = 'A'
                 AND a.IND_ESTA = 'A'
               ORDER BY a.FEC_REGI DESC) x
       WHERE x.cod_clie = pscodigoconsultora
         AND x.ESCA_COD_ESTA_CARG = 'A'
         AND rownum = 1;

      UPDATE zon_direc_venta_cabec
        SET IND_ESTA = 'A',
            ESCA_COD_ESTA_CARG = 'I'
       WHERE TICA_COD_TIPO_CARG = regparamcabec.TICA_COD_TIPO_CARG
         AND TIOP_COD_TIPO_OPER = regparamcabec.TIOP_COD_TIPO_OPER
         AND cod_clie = regparamcabec.cod_clie
         AND TO_CHAR(FEC_REGI,'dd/mm/yyyy') = TO_CHAR(regparamcabec.FEC_REGI,'dd/mm/yyyy');

      INSERT INTO zon_histo_geren
        (oid_hist_gere,
         marc_oid_marc,
         cana_oid_cana,
         ua,
         gere,
         fec_desd,
         fec_hast,
         pais_oid_pais,
         usu_modi,
         fec_modi)
      VALUES
        (zon_hger_seq.nextval,
         zon_id_marca,
         zon_id_canal,
         ua,
         pscodigoconsultorareemplazo,
         to_date(psfechasalida,
                 'dd/MM/yyyy'),
         NULL,
         zon_id_pais,
         pscodigousuario,
         SYSDATE);

      IF (regparam.val_tipo_unid_admi = 'Z') THEN
        UPDATE zon_zona SET clie_oid_clie = id_consu_reem WHERE cod_zona = pscodigozona;

      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN
        UPDATE zon_regio SET clie_oid_clie = id_consu_reem WHERE cod_regi = pscodigoregion;
      END IF;

    END IF;

    --PROCESO A REALIZAR CUANDO EL TIPO DE OPERACION ES RETIRO
    IF pscodigooperacion = 'RE' THEN

      SELECT * INTO regparam FROM zon_tipo_cargo WHERE cod_tipo_carg = pscodigocargo;

      IF regparam.val_tipo_unid_admi = 'Z' THEN
        SELECT cod_subg_vent || b.cod_regi || a.cod_zona
          INTO ua
          FROM zon_zona            a,
               zon_regio           b,
               zon_sub_geren_venta c
         WHERE a.zorg_oid_regi = b.oid_regi
           AND c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND a.cod_zona = pscodigozona;
      END IF;

      IF regparam.val_tipo_unid_admi = 'R' THEN

        --OBTENEMOS LA ua
        SELECT cod_subg_vent || b.cod_regi
          INTO ua
          FROM zon_regio           b,
               zon_sub_geren_venta c
         WHERE c.oid_subg_vent = b.zsgv_oid_subg_vent
           AND b.cod_regi = pscodigoregion;

      END IF;

      IF (regparam.val_tipo_unid_admi = 'Z') THEN
        UPDATE zon_zona SET clie_oid_clie = NULL WHERE cod_zona = pscodigozona;

        UPDATE zon_histo_geren
           SET fec_hast = psfechaproceso
         WHERE fec_hast IS NULL
           AND gere = pscodigoconsultora
           AND ua = ua;

      END IF;

      IF (regparam.val_tipo_unid_admi = 'R') THEN
        UPDATE zon_regio SET clie_oid_clie = NULL WHERE cod_regi = pscodigoregion;

        UPDATE zon_histo_geren
           SET fec_hast = psfechaproceso
         WHERE fec_hast IS NULL
           AND gere = pscodigoconsultora
           AND ua = ua;
      END IF;

    END IF;

  IF pserror IS NULL THEN
    pserror := 'Aprobación Realizada';
  END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_APROB_OPERA: ' || ls_sqlerrm);
  END zon_pr_aprob_opera;

  /***************************************************************************
  Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                      determinado periodo
  Fecha Creacion    : 01/06/2011
  Autor             :
  ***************************************************************************/
  FUNCTION zon_fn_devue_respo_unida_histo
  (
    pscodresponsable OUT VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pnidpais         NUMBER,
    pscodsubgerencia VARCHAR2,
    pscodregion      VARCHAR2,
    pscodzona        VARCHAR2,
    pscodseccion     VARCHAR2 := NULL
  ) RETURN VARCHAR2 IS
    lsbuscar     zon_histo_geren.ua%TYPE;
    lsretorno    zon_histo_geren.gere%TYPE;
    lstempo      zon_histo_geren.gere%TYPE;
    lsnombres    VARCHAR2(500);
    ldfechatempo DATE;
    ldfecha      DATE;

    CURSOR c1
    (
      vsbuscar    VARCHAR2,
      vnidperiodo NUMBER
    ) IS

      SELECT lideres.gere,
             lideres.perd_oid_peri_desd,
             lideres.perd_oid_peri_hast,
             lideres.ind_desv_auto
        FROM (
              -- Gerentes que son activas a hoy --
              SELECT zhg.gere,
                      zhg.perd_oid_peri_desd,
                      zhg.perd_oid_peri_hast,
                      zhg.ind_desv_auto
                FROM zon_histo_geren zhg
               WHERE zhg.ua = vsbuscar
                 AND zhg.perd_oid_peri_desd <= vnidperiodo
                 AND zhg.perd_oid_peri_hast IS NULL
              UNION
              -- Gerentes activas a la campaña seleccionada (actualmente inactivas) --
              SELECT zhg.gere,
                     zhg.perd_oid_peri_desd,
                     zhg.perd_oid_peri_hast,
                     zhg.ind_desv_auto
                FROM zon_histo_geren zhg
               WHERE zhg.ua = vsbuscar
                 AND zhg.perd_oid_peri_desd <= vnidperiodo
                 AND zhg.perd_oid_peri_hast >= vnidperiodo
                 AND zhg.perd_oid_peri_hast IS NOT NULL) lideres;

    lnidperiodo NUMBER;

  BEGIN
    lsbuscar := pscodsubgerencia;
    IF (pscodregion IS NOT NULL) THEN
      lsbuscar := lsbuscar || pscodregion;
    END IF;
    IF (pscodzona IS NOT NULL) THEN
      lsbuscar := lsbuscar || pscodzona;
    END IF;
    IF (pscodseccion IS NOT NULL) THEN
      lsbuscar := lsbuscar || pscodseccion;
    END IF;

    /* Buscando codigo de Gerente en la tabla ZON_HISTO_GEREN */
    lsretorno        := '';
    pscodresponsable := '';

    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    FOR cursor1 IN c1(lsbuscar,
                      lnidperiodo)
    LOOP
      lsretorno := cursor1.gere;
      EXIT;
    END LOOP;

    lsretorno        := TRIM(lsretorno);
    pscodresponsable := lsretorno;

    /* Encontrando nombre respectivo en la tabla MAE_CLIEN */
    IF (lsretorno IS NOT NULL) THEN
      BEGIN
        SELECT TRIM(nvl(a.val_ape1,
                        ' ')) || ' ' || TRIM(nvl(a.val_ape2,
                                                 ' ')) || ' ' ||
               TRIM(nvl(a.val_nom1,
                        ' '))
          INTO lsnombres
          FROM mae_clien a
         WHERE a.cod_clie = lsretorno
           AND a.pais_oid_pais = pnidpais;

        RETURN lsnombres;

      EXCEPTION
        WHEN no_data_found THEN
          RETURN '';
      END;
    END IF;
    RETURN lsretorno;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_FN_DEVUE_RESPO_UNIDA_HISTO: ' || ls_sqlerrm);
  END zon_fn_devue_respo_unida_histo;

  /***************************************************************************
  Descripcion       : Valida la Creacion de Territorios de Demanda Anticipada

  Fecha Creacion    : 30/05/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_valid_crear_terri_deman
  (
    pscodigopais     IN VARCHAR2,
    pscodigoregiones IN VARCHAR2
  ) IS
    TYPE typregdemandada IS RECORD(
      cod_regi zon_tmp_unida_admin_deman.cod_regi%TYPE,
      cod_zona zon_tmp_unida_admin_deman.cod_zona%TYPE,
      cod_terr zon_tmp_unida_admin_deman.cod_terr%TYPE,
      cod_ubig zon_tmp_unida_admin_deman.cod_ubig%TYPE,
      val_fila zon_tmp_unida_admin_deman.val_fila%TYPE);

    TYPE typtabdemandada IS TABLE OF typregdemandada;
    tabdemandada typtabdemandada;

    lncontador       NUMBER;
    lsestadovalidado zon_tmp_unida_admin_deman.est_vali%TYPE;

    CURSOR cdemandadas IS
      SELECT cod_regi,
             cod_zona,
             cod_terr,
             cod_ubig,
             val_fila
        FROM zon_tmp_unida_admin_deman
       WHERE cod_pais = pscodigopais
       ORDER BY val_fila;

  BEGIN

    OPEN cdemandadas;
    LOOP
      FETCH cdemandadas BULK COLLECT
        INTO tabdemandada LIMIT w_filas;
      EXIT WHEN tabdemandada.count = 0;

      FOR i IN 1 .. tabdemandada.count
      LOOP
        lsestadovalidado := 'OK';

        --Verificar que la region a la cual se va a crear el territorio corresponda
        --a las regiones de demanda seleccionadas
        IF (instr(pscodigoregiones,
                  tabdemandada(i).cod_regi) = 0) THEN
          lsestadovalidado := '1';
        END IF;

        --Valida que la zona exista
        IF (lsestadovalidado = 'OK') THEN
          SELECT COUNT(1)
            INTO lncontador
            FROM zon_zona
           WHERE cod_zona = tabdemandada(i).cod_zona
             AND ind_acti = 1;

          IF (lncontador = 0) THEN
            lsestadovalidado := '2';
          END IF;
        END IF;

        --Valida si el codigo de Ubigeo sea valido
        IF (lsestadovalidado = 'OK') THEN
          SELECT COUNT(1)
            INTO lncontador
            FROM zon_valor_estru_geopo
           WHERE orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 || orde_7 || orde_8 ||
                 orde_9 = tabdemandada(i).cod_ubig
             AND rownum = 1;

          IF (lncontador = 0) THEN
            lsestadovalidado := '3';
          END IF;
        END IF;

        --Valida que no exista el codigo de Territorio en el maestro de Territorios
        IF (lsestadovalidado = 'OK') THEN
          SELECT COUNT(1)
            INTO lncontador
            FROM zon_terri
           WHERE cod_terr = tabdemandada(i).cod_terr
             AND ind_borr = 0;

          IF (lncontador > 0) THEN
            lsestadovalidado := '4';
          END IF;
        END IF;

        --Actualizamos el Estado de Validacion del Registro
        UPDATE zon_tmp_unida_admin_deman
           SET est_vali = lsestadovalidado,
               val_obse =
               (SELECT des_erro FROM zon_error_admin_deman WHERE cod_erro = lsestadovalidado)
         WHERE val_fila = tabdemandada(i).val_fila;

      END LOOP;

    END LOOP;
    CLOSE cdemandadas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_VALID_CREAR_TERRI_DEMAN: ' || ls_sqlerrm);
  END zon_pr_valid_crear_terri_deman;

  /***************************************************************************
  Descripcion       : Procesa la Creacion de Territorios de Demanda Anticipada

  Fecha Creacion    : 30/05/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE zon_pr_proce_crear_terri_deman(pscodigopais IN VARCHAR2) IS
    TYPE typregdemandada IS RECORD(
      cod_zona zon_tmp_unida_admin_deman.cod_zona%TYPE,
      cod_secc zon_tmp_unida_admin_deman.cod_secc%TYPE,
      cod_terr zon_tmp_unida_admin_deman.cod_terr%TYPE,
      cod_ubig zon_tmp_unida_admin_deman.cod_ubig%TYPE);

    TYPE typtabdemandada IS TABLE OF typregdemandada;
    tabdemandada typtabdemandada;

    lncontador NUMBER;

    lnoidpais    seg_pais.oid_pais%TYPE;
    lnoidmarca   seg_marca.oid_marc%TYPE;
    lnoidcanal   seg_canal.oid_cana%TYPE;
    lnoidperiodo cra_perio.oid_peri%TYPE;

    lscodigomarca   zon_tmp_unida_admin_deman.cod_marc%TYPE;
    lscodigocanal   zon_tmp_unida_admin_deman.cod_cana%TYPE;
    lscodigoperiodo zon_tmp_unida_admin_deman.cod_peri%TYPE;

    lnoidzona          zon_zona.oid_zona%TYPE;
    lnoidseccion       zon_secci.oid_secc%TYPE;
    lnoidterritorio    zon_terri.oid_terr%TYPE;
    lnoidterritorioadm zon_terri_admin.oid_terr_admi%TYPE;
    lnoidubigeo        zon_valor_estru_geopo.oid_valo_estr_geop%TYPE;

    CURSOR cdemandadas IS
      SELECT cod_zona,
             cod_secc,
             cod_terr,
             cod_ubig
        FROM zon_tmp_unida_admin_deman
       WHERE cod_pais = pscodigopais
       ORDER BY val_fila;

  BEGIN

    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnoidperiodo := NULL;

    OPEN cdemandadas;
    LOOP
      FETCH cdemandadas BULK COLLECT
        INTO tabdemandada LIMIT w_filas;
      EXIT WHEN tabdemandada.count = 0;

      FOR i IN 1 .. tabdemandada.count
      LOOP

        --Obtenemos el OidPeriodo del Primer Registro
        IF (lnoidperiodo IS NULL) THEN
          SELECT cod_marc,
                 cod_cana,
                 cod_peri
            INTO lscodigomarca,
                 lscodigocanal,
                 lscodigoperiodo
            FROM zon_tmp_unida_admin_deman
           WHERE val_fila = 1;

          lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(lscodigomarca);
          lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(lscodigocanal);
          lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodigoperiodo,
                                                                     lnoidmarca,
                                                                     lnoidcanal);
        END IF;

        --Obtenemos el Oid Zona
        SELECT oid_zona
          INTO lnoidzona
          FROM zon_zona
         WHERE cod_zona = tabdemandada(i).cod_zona
           AND ind_acti = 1;

        --Verificar que la seccion existe, sino se procedera a su creacion
        SELECT COUNT(1)
          INTO lncontador
          FROM zon_secci sec
         WHERE sec.zzon_oid_zona = lnoidzona
           AND sec.cod_secc = tabdemandada(i).cod_secc;

        IF (lncontador > 0) THEN
          SELECT oid_secc
            INTO lnoidseccion
            FROM zon_secci sec
           WHERE sec.zzon_oid_zona = lnoidzona
             AND sec.cod_secc = tabdemandada(i).cod_secc;
        ELSE
          SELECT zon_zscc_seq.nextval INTO lnoidseccion FROM dual;

          INSERT INTO zon_secci
            (oid_secc,
             zzon_oid_zona,
             cod_secc,
             des_secci,
             perd_oid_peri_inic,
             ind_acti,
             ind_borr)
          VALUES
            (lnoidseccion,
             lnoidzona,
             tabdemandada(i).cod_secc,
             'Zona ' || tabdemandada(i).cod_zona || ' Seccion ' || tabdemandada(i).cod_secc,
             lnoidperiodo,
             1,
             0);
        END IF;

        --Se crea el Territorio
        SELECT zon_terr_seq.nextval INTO lnoidterritorio FROM dual;
        SELECT zon_ztad_seq.nextval INTO lnoidterritorioadm FROM dual;

        SELECT oid_valo_estr_geop
          INTO lnoidubigeo
          FROM zon_valor_estru_geopo
         WHERE orde_1 || orde_2 || orde_3 || orde_4 || orde_5 || orde_6 || orde_7 || orde_8 ||
               orde_9 = tabdemandada(i).cod_ubig
           AND rownum = 1;

        INSERT INTO zon_terri
          (pais_oid_pais,
           oid_terr,
           cod_terr,
           vepo_oid_valo_estr_geop,
           fec_rtz,
           ind_borr)
        VALUES
          (lnoidpais,
           lnoidterritorio,
           tabdemandada(i).cod_terr,
           lnoidubigeo,
           SYSDATE,
           0);
        INSERT INTO zon_terri_admin
          (oid_terr_admi,
           zscc_oid_secc,
           terr_oid_terr,
           pais_oid_pais,
           marc_oid_marc,
           cana_oid_cana,
           perd_oid_peri_inic,
           fec_rtz,
           ind_borr)
        VALUES
          (lnoidterritorioadm,
           lnoidseccion,
           lnoidterritorio,
           lnoidpais,
           lnoidmarca,
           lnoidcanal,
           lnoidperiodo,
           SYSDATE,
           0);

      END LOOP;

    END LOOP;
    CLOSE cdemandadas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_PR_PROCE_CREAR_TERRI_DEMAN: ' || ls_sqlerrm);
  END zon_pr_proce_crear_terri_deman;

  /***************************************************************************
  Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                      determinado periodo
  Fecha Creacion    : 08/06/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION zon_fn_devue_respo_unida_admin
  (
    pscodigoperiodo  VARCHAR2,
    pscodigopais     VARCHAR2,
    pscodsubgerencia VARCHAR2,
    pscodregion      VARCHAR2,
    pscodzona        VARCHAR2,
    pscodseccion     VARCHAR2 := NULL
  ) RETURN VARCHAR2 IS
    lscodigoresponsable VARCHAR2(20);
    lsnombres           VARCHAR2(100);
    lnoidpais           seg_pais.oid_pais%TYPE;
  BEGIN
    --Recuperamos el oid Pais
    lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    lscodigoresponsable := '';

    lsnombres := zon_fn_devue_respo_unida_histo(lscodigoresponsable,
                                                pscodigoperiodo,
                                                lnoidpais,
                                                pscodsubgerencia,
                                                pscodregion,
                                                pscodzona,
                                                pscodseccion);

    RETURN lscodigoresponsable;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ZON_FN_DEVUE_RESPO_UNIDA_ADMIN: ' || ls_sqlerrm);
  END zon_fn_devue_respo_unida_admin;

     /**********************************************************************************
    Descripcion       : Valida la zona y region para la asignacion
    Fecha Creacion    : 12/02/2013
    Autor             :
    **********************************************************************************/
   PROCEDURE ZON_PR_ASIGN_REGI_ZON(psCodigoPais VARCHAR2,
                                  psCodigoCliente VARCHAR2,
                                  pstipo varchar2,
                                  pscodregi varchar2,
                                  pscodzona varchar2,
                                  psusuario VARCHAR2,
                                  psFechaProceso VARCHAR2,
                                  psCodigoPeriodoActual VARCHAR2,
                                  psResultado OUT VARCHAR2)
    IS
    v_cod_clien varchar2(50);
    v_cod_tipo_clie varchar2(50);
    v_oid_regi number;
    v_cod_regi varchar2(50);
    v_des_regi varchar2(50);
    v_oid_zona number;
    v_cod_zona varchar2(50);
    v_des_zona  varchar2(50);
    v_ua   varchar2(50);
    v_oid_hist_gere   varchar2(50);
    v_PERD_OID_PERI_DESD    varchar2(50);
    v_resultado boolean := false;
    v_resultadoHistorico boolean := false;

    vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    vnCampanyaProceso       BAS_CTRL_FACT.COD_PERI%TYPE;
    vnOidCampanyaProceso    CRA_PERIO.OID_PERI%TYPE;
  begin

    vnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    vnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

    SELECT COD_PERI
      INTO vnCampanyaProceso
      FROM BAS_CTRL_FACT X
     WHERE X.STA_CAMP = 0 AND X.IND_CAMP_ACT = 1;

    vnOidCampanyaProceso := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vnCampanyaProceso);

    psResultado := '';

  --punto 44
    BEGIN
      select cod_clie
        into v_cod_clien
        from mae_clien
       where cod_clie = psCodigoCliente;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        psResultado := 'La consultora '|| psCodigoCliente ||' no está registrada en Maestro de Clientes';
        RETURN;
    END;

  --punto 45

    BEGIN
        select t.COD_TIPO_CLIE into v_cod_tipo_clie
        from MAE_CLIEN_TIPO_SUBTI m, mae_tipo_clien t
         where t.OID_TIPO_CLIE = m.ticl_oid_tipo_clie AND
            m.clie_oid_clie =  GEN_PKG_GENER.gen_fn_devuelve_id_cliente(v_cod_clien) AND
            t.COD_TIPO_CLIE = '04';
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            psResultado := 'La consultora '|| psCodigoCliente ||' no tiene el tipo de cliente Gerente';
            RETURN;
    END;

  --punto 48
    --Validar si una consultora se encuentra asignada a una region o zona
    BEGIN
    select r.oid_regi, r.cod_regi, r.des_regi
      into v_oid_regi , v_cod_regi, v_des_regi
      from zon_regio r
      where r.clie_oid_clie =  GEN_PKG_GENER.gen_fn_devuelve_id_cliente(v_cod_clien)
        and r.ind_acti = '1';

      psResultado := 'Código ya asignado en Región ' || v_des_regi ;--|| ' ' || psUnidadAdm;
      RETURN;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            --Si no existe data pasamos a verificar si se encuentra asignado a una Zona
            BEGIN
                select r.oid_zona, r.cod_zona, r.des_zona
                  into v_oid_zona,v_cod_zona,v_des_zona
                  from zon_zona r
                 where r.clie_oid_clie =  GEN_PKG_GENER.gen_fn_devuelve_id_cliente(v_cod_clien)
                   and r.ind_acti = '1';
                   psResultado := 'Código ya asignado en ZONA '|| v_des_zona ;
                   RETURN;

            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   v_resultado := true;
            END;
    END;


    if v_resultado THEN
        --49
          if pstipo = 'Z' then
             v_ua := '01' || pscodregi || pscodzona;
          elsif pstipo = 'R' then
              v_ua := '01' || pscodregi;
          end if;

           BEGIN
              select oid_hist_gere, PERD_OID_PERI_DESD
                into v_oid_hist_gere, v_PERD_OID_PERI_DESD
                from ZON_HISTO_GEREN
              where PERD_OID_PERI_HAST is null
              and ua = v_ua;
              v_resultadoHistorico:= true;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
                v_resultadoHistorico:= false;
           END;


          if v_resultadoHistorico then
             if v_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual) then
                delete ZON_HISTO_GEREN where oid_hist_gere =  v_oid_hist_gere;

             else
                 update ZON_HISTO_GEREN set
                 PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
                 fec_hast = (select fec_fina - 5
                               from cra_perio
                              where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))), --campaña anterior a la campaña de proceso)
                 usu_modi = psusuario,
                 fec_modi = sysdate
                 where oid_hist_gere =  v_oid_hist_gere;
             end if;
          end if;

          insert into ZON_HISTO_GEREN
             (OID_HIST_GERE,
              MARC_OID_MARC,
              CANA_OID_CANA,
              PAIS_OID_PAIS,
              fec_hast,
              perd_oid_peri_hast,
              ua,
              fec_desd,
              PERD_OID_PERI_DESD,
              gere,
              usu_modi,
              fec_modi)
          values
              (ZON_HGER_SEQ.NEXTVAL,
               vnOidMarca,
               vnOidCanal,
               vnOidPais,
               null,
               null,
               v_ua,
               TO_DATE(psFechaProceso,'dd/mm/yyyy'),
               vnOidCampanyaProceso,
               psCodigoCliente,
               psusuario,
               sysdate
              );

         if pstipo = 'Z' then
            update zon_zona
                set clie_oid_clie = GEN_PKG_GENER.gen_fn_devuelve_id_cliente(v_cod_clien)
              where cod_zona = pscodzona;

         elsif pstipo = 'R' then
             update zon_regio
                set clie_oid_clie = GEN_PKG_GENER.gen_fn_devuelve_id_cliente(v_cod_clien)
              where cod_regi = pscodregi;

        end if;

    end if;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_ASIGN_REGI_ZON: ' || ls_sqlerrm);
  END ZON_PR_ASIGN_REGI_ZON;

  /***************************************************************************
  Descripcion       : Valida tipo Cargo Futuro.
  Fecha Creacion    : 22/11/2013
  Autor             : Yahir Rivas Luna

  ***************************************************************************/
  FUNCTION ZON_FN_VALID_FECHA_INGRE
  (psTipoCargo  VARCHAR2,
   psCodigoPais   VARCHAR2,
   psFechaIngreso VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2
  ) RETURN VARCHAR2
  IS
  -- -1 : Otro cargo
    lsResult   VARCHAR2(2):='-1';
    lsCodtipocargbase VARCHAR2(1);
    lsCampaniaVigente bas_ctrl_fact.cod_peri%TYPE;
    ldFechaProceso bas_ctrl_fact.fec_proc%TYPE;
    ldFechaRegistro ZON_DIREC_VENTA_CABEC.FEC_REGI%TYPE;
    ldFechaInicio CRA_PERIO.Fec_Inic%TYPE;
    ldFechaFin CRA_PERIO.Fec_Fina%TYPE;
    ldFechaIngresoPantalla ZON_DIREC_VENTA_CABEC.FEC_REGI%TYPE;
    ldFechaRegistroMaxInicio CRA_PERIO.Fec_Inic%TYPE;
    ldFechaRegistroMaxFin CRA_PERIO.Fec_Fina%TYPE;
  BEGIN

    ldFechaIngresoPantalla := TO_DATE(psFechaIngreso, 'DD/MM/YYYY');

     -- 0 : cargos que no son gerentes futuros , 1 : cargos futuros
    select decode(cod_tipo_carg_base,NULL,'0','1')
    INTO lsCodtipocargbase
    from Zon_tipo_cargo
    where cod_tipo_carg = psTipoCargo;

    BEGIN
    select f.cod_peri, f.fec_proc
    INTO lsCampaniaVigente, ldFechaProceso
    from bas_ctrl_fact f
    where f.sta_camp = 0
    and f.ind_camp_act = 1
    AND f.cod_pais = psCodigoPais;
      EXCEPTION
          WHEN NO_DATA_FOUND THEN
              lsResult:='3'; -- No existe campaña vigente
          return lsResult;
    END;

    SELECT cr.fec_inic, cr.fec_fina
    INTO ldFechaInicio,ldFechaFin
    FROM CRA_PERIO CR
    WHERE CR.VAL_NOMB_PERI LIKE '%'||lsCampaniaVigente||'%'
    AND CR.PAIS_OID_PAIS = gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

    IF ldFechaIngresoPantalla < ldFechaInicio THEN
        lsResult:='2'; -- 2 = NO cumple con la validacion
        RETURN lsResult;
    END IF;

     IF lsCodtipocargbase = '0'  THEN

          IF (ldFechaIngresoPantalla >= ldFechaInicio AND ldFechaIngresoPantalla <= ldFechaProceso) THEN
                 lsResult:='1'; -- 1 = cumple con la validacion
            ELSE
                 lsResult:='0'; -- 0 = No cumple con la validacion
          END IF;

       ELSIF lsCodtipocargbase = '1' THEN

          IF psCodigoRegion IS NULL AND psCodigoZona IS NULL THEN
                lsResult:='1'; -- 1 = cumple con la validacion
               RETURN lsResult;
          END IF;

         select  MAX(C.FEC_REGI), MAX(C.FEC_REGI_FIN)
         INTO ldFechaRegistroMaxInicio, ldFechaRegistroMaxFin
         from ZON_DIREC_VENTA_CABEC c,
         ZON_DIREC_VENTA_detal d,
         ZON_TIPO_CARGO ztc
         where c.PAIS_COD_PAIS = d.PAIS_COD_PAIS
         and c.TIOP_COD_TIPO_OPER = d.TIOP_COD_TIPO_OPER
         and c.TICA_COD_TIPO_CARG = d.TICA_COD_TIPO_CARG
         and c.COD_CLIE = d.COD_CLIE
         and c.FEC_REGI = d.DICA_FEC_REGI
         and c.CAM_PROC = d.DICA_CAM_PROC
         and c.COR_DIRE_VENT= d.DICA_COR_DIRE_VENT
         and C.TICA_COD_TIPO_CARG = ZTC.COD_TIPO_CARG
         AND ZTC.COD_TIPO_CARG_BASE IS NOT NULL
         AND c.PAIS_COD_PAIS = psCodigoPais
         AND D.COD_REGI = psCodigoRegion
         AND NVL(D.COD_ZONA,' ') = NVL(psCodigoZona,' ')
         AND c.TICA_COD_TIPO_CARG IN(
                SELECT ZTC.COD_TIPO_CARG
                  FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE IN(SELECT Z.COD_TIPO_CARG_BASE
                                               FROM ZON_TIPO_CARGO Z
                                              WHERE Z.COD_TIPO_CARG = psTipoCargo)
         )
         AND C.EST_rEGI = '1'
         AND C.ESCA_COD_ESTA_CARG = 'A'
         ORDER BY FEC_REGI  DESC;

         IF ldFechaRegistroMaxInicio IS NULL AND ldFechaRegistroMaxFin IS NULL THEN
            ldFechaRegistro := NULL;
         ELSE
            IF ldFechaRegistroMaxFin IS NULL THEN
                ldFechaRegistro := ldFechaRegistroMaxInicio;

            ELSE
                IF ldFechaRegistroMaxInicio > ldFechaRegistroMaxFin THEN
                    ldFechaRegistro := ldFechaRegistroMaxInicio;
                ELSE
                    ldFechaRegistro := ldFechaRegistroMaxFin;
                END IF;
            END IF;
         END IF;

            IF ldFechaRegistro IS NULL THEN

               IF ldFechaIngresoPantalla > ldFechaProceso THEN
                     lsResult:='1'; -- 1 = cumple con la validacion
               ELSE
                     lsResult:='0'; -- 0 = no cumple con la validacion
               END IF;

              ELSE

                 IF ldFechaIngresoPantalla > ldFechaRegistro THEN
                     lsResult:='1'; -- 1 = cumple con la validacion
                 ELSE
                     lsResult:='0'; -- 0 = no cumple con la validacion
               END IF;

           END IF;

     END IF;

    RETURN lsResult;

  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_VALID_FECHA_INGRE: '||ls_sqlerrm);
    RETURN lsResult;
  END ZON_FN_VALID_FECHA_INGRE;


/***************************************************************************
  Descripcion       : Valida el cruce de fechas gerentes.
  Fecha Creacion    : 27/11/2013
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  FUNCTION ZON_FN_VALID_CRUCE_FECHA_GEREN
  (psCodigoPais   VARCHAR2,
   psFechaIngresoIncio VARCHAR2,
   psFechaIngresoHasta VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2,
   psTipoCargo VARCHAR2,
   psTipoOperacion VARCHAR2
  ) RETURN VARCHAR2
  IS

    lsResult   VARCHAR2(2):='-1';
    lsCodigoConsultora ZON_DIREC_VENTA_CABEC.COD_CLIE%TYPE;
    ldFechaIngresoIncio ZON_DIREC_VENTA_CABEC.FEC_REGI%TYPE;
    ldFechaIngresoFin ZON_DIREC_VENTA_CABEC.FEC_REGI_FIN%TYPE;
    lsCodtipocargbase VARCHAR2(1);
  BEGIN

     -- 0 : cargos que no son gerentes futuros , 1 : cargos futuros
    select decode(cod_tipo_carg_base,NULL,'0','1')
    INTO lsCodtipocargbase
    from Zon_tipo_cargo
    where cod_tipo_carg = psTipoCargo;

    ldFechaIngresoIncio := TO_DATE(psFechaIngresoIncio, 'DD/MM/YYYY');
    ldFechaIngresoFin := TO_DATE(psFechaIngresoHasta, 'DD/MM/YYYY');

   IF ldFechaIngresoFin IS NOT NULL THEN

   select c.cod_clie
   INTO lsCodigoConsultora
   from ZON_DIREC_VENTA_CABEC c, ZON_DIREC_VENTA_detal d
   where c.PAIS_COD_PAIS = d.PAIS_COD_PAIS
   and c.TIOP_COD_TIPO_OPER = d.TIOP_COD_TIPO_OPER
   and c.TICA_COD_TIPO_CARG = d.TICA_COD_TIPO_CARG
   and c.COD_CLIE = d.COD_CLIE
   and c.FEC_REGI = d.DICA_FEC_REGI
   and c.CAM_PROC = d.DICA_CAM_PROC
   and c.COR_DIRE_VENT= d.DICA_COR_DIRE_VENT
   AND c.PAIS_COD_PAIS = psCodigoPais
   AND D.COD_REGI = psCodigoRegion
   AND NVL(D.COD_ZONA,' ') = NVL(psCodigoZona,' ')
   AND C.EST_rEGI = '1'
   AND C.ESCA_COD_ESTA_CARG = 'A'
   and c.fec_regi IS NOT NULL
   AND ( (c.fec_regi BETWEEN ldFechaIngresoIncio AND ldFechaIngresoFin) OR
         (ldFechaIngresoIncio >= c.fec_regi AND ldFechaIngresoFin <= c.fec_regi_fin) OR
         (c.fec_regi_fin BETWEEN ldFechaIngresoIncio AND ldFechaIngresoFin)
        )
            AND C.TICA_COD_TIPO_CARG in(
                 SELECT ZTC.COD_TIPO_CARG
                 FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE =psTipoCargo
                 UNION
                 SELECT ZTC.COD_TIPO_CARG
                 FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE IN (SELECT Z.COD_TIPO_CARG_BASE
                                       FROM ZON_TIPO_CARGO Z
                                       WHERE Z.COD_TIPO_CARG = psTipoCargo)
                 )
    AND ROWNUM = 1;
              RETURN lsCodigoConsultora;
    ELSE
         -- Bloque solo para Asignacion de Cargo
            IF psTipoOperacion = 'A' THEN
              BEGIN
               select c.cod_clie
               INTO lsCodigoConsultora
               from ZON_DIREC_VENTA_CABEC c, ZON_DIREC_VENTA_detal d
               where c.PAIS_COD_PAIS = d.PAIS_COD_PAIS
               and c.TIOP_COD_TIPO_OPER = d.TIOP_COD_TIPO_OPER
               and c.TICA_COD_TIPO_CARG = d.TICA_COD_TIPO_CARG
               and c.COD_CLIE = d.COD_CLIE
               and c.FEC_REGI = d.DICA_FEC_REGI
               and c.CAM_PROC = d.DICA_CAM_PROC
               and c.COR_DIRE_VENT= d.DICA_COR_DIRE_VENT
               AND c.PAIS_COD_PAIS = psCodigoPais
               AND D.COD_REGI = psCodigoRegion
               AND NVL(D.COD_ZONA,' ') = NVL(psCodigoZona,' ')
               AND C.EST_rEGI = '1'
               AND C.ESCA_COD_ESTA_CARG = 'A'
               and c.fec_regi IS NOT NULL
               and c.fec_regi_fin IS NOT NULL
               AND (ldFechaIngresoIncio > c.fec_regi AND ldFechaIngresoIncio < c.fec_regi_fin)
                AND C.TICA_COD_TIPO_CARG in(
                 SELECT ZTC.COD_TIPO_CARG
                 FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE =psTipoCargo
                 UNION
                 SELECT ZTC.COD_TIPO_CARG
                 FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE IN (SELECT Z.COD_TIPO_CARG_BASE
                                       FROM ZON_TIPO_CARGO Z
                                       WHERE Z.COD_TIPO_CARG = psTipoCargo)
                 UNION
                 SELECT Z.COD_TIPO_CARG_BASE
                                       FROM ZON_TIPO_CARGO Z
                                       WHERE Z.COD_TIPO_CARG = psTipoCargo
                )
               AND ROWNUM = 1;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    lsCodigoConsultora := NULL;
            END;

           IF lsCodigoConsultora IS NULL THEN
                RETURN lsResult;
              END IF;

            RETURN lsCodigoConsultora;

          END IF;

           END IF;

     RETURN lsResult;

  EXCEPTION
  WHEN NO_DATA_FOUND THEN
     RETURN lsResult;
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_VALID_CRUCE_FECHA_GEREN: '||ls_sqlerrm);
  END ZON_FN_VALID_CRUCE_FECHA_GEREN;

/***************************************************************************
  Descripcion       : Valida tipo Cargo Futuro.
  Fecha Creacion    : 03/11/2013
  Autor             : Yahir Rivas Luna

  ***************************************************************************/
  FUNCTION ZON_FN_VALID_FECHA_INGRE_FOX
  (psTipoCargo  VARCHAR2,
   psCodigoPais   VARCHAR2,
   psFechaIngreso VARCHAR2,
   psCodigoRegion VARCHAR2,
   psCodigoZona VARCHAR2
  ) RETURN VARCHAR2
  IS
  -- -1 : Otro cargo
    lsResult   VARCHAR2(2):='-1';
    lsCodtipocargbase VARCHAR2(1);
    lsCampaniaVigente zon_direc_cntrl_factu.cam_proc%TYPE;
    ldFechaProceso zon_direc_cntrl_factu.fec_proc%TYPE;
    ldFechaRegistro ZON_DIREC_VENTA_CABEC.FEC_REGI%TYPE;
    ldFechaInicio ZON_DIREC_CAMPA.Fec_Inic%TYPE;
    ldFechaFin ZON_DIREC_CAMPA.Fec_Fina%TYPE;
    ldFechaIngresoPantalla ZON_DIREC_VENTA_CABEC.FEC_REGI%TYPE;
    ldFechaRegistroMaxInicio CRA_PERIO.Fec_Inic%TYPE;
    ldFechaRegistroMaxFin CRA_PERIO.Fec_Fina%TYPE;
  BEGIN

    ldFechaIngresoPantalla := TO_DATE(psFechaIngreso, 'DD/MM/YYYY');

     -- 0 : cargos que no son gerentes futuros , 1 : cargos futuros
    select decode(cod_tipo_carg_base,NULL,'0','1')
    INTO lsCodtipocargbase
    from Zon_tipo_cargo
    where cod_tipo_carg = psTipoCargo;

    BEGIN
    select f.Cam_Proc, f.fec_proc
    INTO lsCampaniaVigente, ldFechaProceso
    from zon_direc_cntrl_factu f
    where f.est_camp = 0
    and f.ind_camp_acti = 1
    AND f.pais_cod_pais = psCodigoPais;
      EXCEPTION
          WHEN NO_DATA_FOUND THEN
              lsResult:='3'; -- No existe campaña vigente
          return lsResult;
    END;

    SELECT cr.fec_inic, cr.fec_fina
    INTO ldFechaInicio,ldFechaFin
    FROM ZON_DIREC_CAMPA CR
    WHERE CR.COD_CAMP LIKE '%'||lsCampaniaVigente||'%'
    AND CR.PAIS_COD_PAIS = psCodigoPais;

    IF ldFechaIngresoPantalla < ldFechaInicio THEN
        lsResult:='2'; -- 2 = NO cumple con la validacion
        RETURN lsResult;
    END IF;

     IF lsCodtipocargbase = '0'  THEN

          IF (ldFechaIngresoPantalla >= ldFechaInicio AND ldFechaIngresoPantalla <= ldFechaProceso) THEN
                 lsResult:='1'; -- 1 = cumple con la validacion
            ELSE
                 lsResult:='0'; -- 0 = No cumple con la validacion
          END IF;

       ELSIF lsCodtipocargbase = '1' THEN

           IF psCodigoRegion IS NULL AND psCodigoZona IS NULL THEN
                lsResult:='1'; -- 1 = cumple con la validacion
               RETURN lsResult;
          END IF;

         select  MAX(C.FEC_REGI), MAX(C.FEC_REGI_FIN)
         INTO ldFechaRegistroMaxInicio, ldFechaRegistroMaxFin
         from ZON_DIREC_VENTA_CABEC c,
         ZON_DIREC_VENTA_detal d,
         ZON_TIPO_CARGO ztc
         where c.PAIS_COD_PAIS = d.PAIS_COD_PAIS
         and c.TIOP_COD_TIPO_OPER = d.TIOP_COD_TIPO_OPER
         and c.TICA_COD_TIPO_CARG = d.TICA_COD_TIPO_CARG
         and c.COD_CLIE = d.COD_CLIE
         and c.FEC_REGI = d.DICA_FEC_REGI
         and c.CAM_PROC = d.DICA_CAM_PROC
         and c.COR_DIRE_VENT= d.DICA_COR_DIRE_VENT
         and C.TICA_COD_TIPO_CARG = ZTC.COD_TIPO_CARG
         AND ZTC.COD_TIPO_CARG_BASE IS NOT NULL
         AND c.PAIS_COD_PAIS = psCodigoPais
         AND D.COD_REGI = psCodigoRegion
         AND NVL(D.COD_ZONA,' ') = NVL(psCodigoZona,' ')
         AND c.TICA_COD_TIPO_CARG IN(
                SELECT ZTC.COD_TIPO_CARG
                  FROM ZON_TIPO_CARGO ZTC
                 WHERE ZTC.COD_TIPO_CARG_BASE IN(SELECT Z.COD_TIPO_CARG_BASE
                                               FROM ZON_TIPO_CARGO Z
                                              WHERE Z.COD_TIPO_CARG = psTipoCargo)
         )
         AND C.EST_rEGI = '1'
         AND C.ESCA_COD_ESTA_CARG = 'A'
         ORDER BY FEC_REGI  DESC;

         IF ldFechaRegistroMaxInicio IS NULL AND ldFechaRegistroMaxFin IS NULL THEN
            ldFechaRegistro := NULL;
         ELSE
            IF ldFechaRegistroMaxFin IS NULL THEN
                ldFechaRegistro := ldFechaRegistroMaxInicio;

            ELSE
                IF ldFechaRegistroMaxInicio > ldFechaRegistroMaxFin THEN
                    ldFechaRegistro := ldFechaRegistroMaxInicio;
                ELSE
                    ldFechaRegistro := ldFechaRegistroMaxFin;
                END IF;
            END IF;
         END IF;

            IF ldFechaRegistro IS NULL THEN

               IF ldFechaIngresoPantalla > ldFechaProceso THEN
                     lsResult:='1'; -- 1 = cumple con la validacion
               ELSE
                     lsResult:='0'; -- 0 = no cumple con la validacion
               END IF;

              ELSE

                 IF ldFechaIngresoPantalla > ldFechaRegistro THEN
                     lsResult:='1'; -- 1 = cumple con la validacion
                 ELSE
                     lsResult:='0'; -- 0 = no cumple con la validacion
               END IF;

           END IF;

     END IF;

    RETURN lsResult;

  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_VALID_FECHA_INGRE_FOX: '||ls_sqlerrm);
    RETURN lsResult;
  END ZON_FN_VALID_FECHA_INGRE_FOX;

/***************************************************************************
Descripcion : Procedure que Procesa Carga de Regiones
Fecha Creacion : 11/12/2013
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE ZON_PR_CARGA_REGIO (
    psCodigoPais VARCHAR2,
    psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 SELECT
    pais_cod_pais,
    cod_regi,
    des_regi,
    clie_cod_clie,
    ind_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from
       ZON_TMP_DIREC_REGIO reg
 WHERE reg.pais_cod_pais = psCodigoPais
    AND not exists ( select nu.cod_regi
                     from ZON_DIREC_REGIO nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.cod_regi = reg.cod_regi ) ;

 CURSOR curUPDregiones
 IS
 select
    pais_cod_pais,
    cod_regi,
    des_regi,
    clie_cod_clie,
    ind_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from ZON_TMP_DIREC_REGIO reg
 WHERE reg.pais_cod_pais = psCodigoPais
   AND exists ( select nu.cod_regi
                from ZON_DIREC_REGIO nu
                where nu.pais_cod_pais = psCodigoPais
                  AND nu.cod_regi = reg.cod_regi ) ;

  TYPE t_pais_cod_pais   IS TABLE OF ZON_TMP_DIREC_REGIO.pais_cod_pais%TYPE ;
  TYPE t_cod_regi        IS TABLE OF ZON_TMP_DIREC_REGIO.cod_regi%TYPE ;
  TYPE t_des_regi        IS TABLE OF ZON_TMP_DIREC_REGIO.des_regi%TYPE ;
  TYPE t_clie_cod_clie   IS TABLE OF ZON_TMP_DIREC_REGIO.clie_cod_clie%TYPE ;
  TYPE t_ind_acti        IS TABLE OF ZON_TMP_DIREC_REGIO.ind_acti%TYPE ;
  TYPE t_usu_crea        IS TABLE OF ZON_TMP_DIREC_REGIO.usu_crea%TYPE ;
  TYPE t_fec_crea        IS TABLE OF ZON_TMP_DIREC_REGIO.fec_crea%TYPE ;
  TYPE t_usu_modi        IS TABLE OF ZON_TMP_DIREC_REGIO.usu_modi%TYPE ;
  TYPE t_fec_modi        IS TABLE OF ZON_TMP_DIREC_REGIO.fec_modi%TYPE ;
  TYPE t_est_regi        IS TABLE OF ZON_TMP_DIREC_REGIO.est_regi%TYPE ;


  v_pais_cod_pais     t_pais_cod_pais    ;
  v_cod_regi          t_cod_regi         ;
  v_des_regi          t_des_regi         ;
  v_clie_cod_clie     t_clie_cod_clie    ;
  v_ind_acti          t_ind_acti         ;
  v_usu_crea          t_usu_crea         ;
  v_fec_crea          t_fec_crea         ;
  v_usu_modi          t_usu_modi         ;
  v_fec_modi          t_fec_modi         ;
  v_est_regi          t_est_regi         ;


  rows NATURAL := 5000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;
  v_row_count NUMBER := 0;
  v_row_count_ins NUMBER := 0;

BEGIN
 COMMIT;
 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
   FETCH curUPDregiones BULK COLLECT INTO
      v_pais_cod_pais   ,
      v_cod_regi        ,
      v_des_regi        ,
      v_clie_cod_clie   ,
      v_ind_acti        ,
      v_usu_crea        ,
      v_fec_crea        ,
      v_usu_modi        ,
      v_fec_modi        ,
      v_est_regi
   LIMIT rows;

   EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
   v_row_count := curUPDregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     UPDATE ZON_DIREC_REGIO regio
     SET
        des_regi      = v_des_regi      (i),
        clie_cod_clie = v_clie_cod_clie (i),
        ind_acti      = v_ind_acti      (i),
        usu_crea      = v_usu_crea      (i),
        fec_crea      = v_fec_crea      (i),
        usu_modi      = v_usu_modi      (i),
        fec_modi      = v_fec_modi      (i),
        est_regi      = v_est_regi      (i)
     where
       regio.pais_cod_pais = v_pais_cod_pais(i) and
       regio.cod_regi = v_cod_regi(i) ;
 END LOOP;
 CLOSE curUPDregiones;

 OPEN curINSregiones;
 LOOP
   -- Bulk collect data into memory table - X rows at a time
   FETCH curINSregiones BULK COLLECT INTO
        v_pais_cod_pais   ,
        v_cod_regi        ,
        v_des_regi        ,
        v_clie_cod_clie   ,
        v_ind_acti        ,
        v_usu_crea        ,
        v_fec_crea        ,
        v_usu_modi        ,
        v_fec_modi        ,
        v_est_regi
     LIMIT rows;
   EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
   v_row_count_ins := curINSregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     insert into ZON_DIREC_REGIO (
        pais_cod_pais,
        cod_regi,
        des_regi,
        clie_cod_clie,
        ind_acti,
        usu_crea,
        fec_crea,
        usu_modi,
        fec_modi,
        est_regi
     )
     VALUES (
      v_pais_cod_pais (i),
      v_cod_regi      (i),
      v_des_regi      (i),
      v_clie_cod_clie (i),
      v_ind_acti      (i),
      v_usu_crea      (i),
      v_fec_crea      (i),
      v_usu_modi      (i),
      v_fec_modi      (i),
      v_est_regi      (i)
     ) ;
 END LOOP;
 CLOSE curINSregiones;
 COMMIT;
 EXCEPTION
 WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGA_REGIO: '||ls_sqlerrm);

 END ZON_PR_CARGA_REGIO;

/***************************************************************************
Descripcion : Procedure que Procesa Carga de ZONAS
Fecha Creacion : 11/12/2013
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE ZON_PR_CARGA_ZONAS (
    psCodigoPais VARCHAR2,
    psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 SELECT
    pais_cod_pais,
    regi_cod_regi,
    cod_zona,
    des_zona,
    clie_cod_clie,
    ind_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from
       ZON_TMP_DIREC_ZONA reg
 WHERE reg.pais_cod_pais = psCodigoPais
    AND not exists ( select nu.cod_zona
                     from ZON_DIREC_ZONA nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.regi_cod_regi = reg.regi_cod_regi
                       AND nu.cod_zona = reg.cod_zona  ) ;

 CURSOR curUPDregiones
 IS
 select
    pais_cod_pais,
    regi_cod_regi,
    cod_zona,
    des_zona,
    clie_cod_clie,
    ind_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from ZON_TMP_DIREC_ZONA reg
 WHERE reg.pais_cod_pais = psCodigoPais
   AND exists ( select nu.cod_zona
                     from ZON_DIREC_ZONA nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.regi_cod_regi = reg.regi_cod_regi
                       AND nu.cod_zona = reg.cod_zona  );

  TYPE t_pais_cod_pais   IS TABLE OF ZON_TMP_DIREC_ZONA.pais_cod_pais%TYPE ;
  TYPE t_cod_regi        IS TABLE OF ZON_TMP_DIREC_ZONA.regi_cod_regi%TYPE ;
  TYPE t_cod_zona        IS TABLE OF ZON_TMP_DIREC_ZONA.cod_zona%TYPE ;
  TYPE t_des_zona        IS TABLE OF ZON_TMP_DIREC_ZONA.des_zona%TYPE ;
  TYPE t_clie_cod_clie   IS TABLE OF ZON_TMP_DIREC_ZONA.clie_cod_clie%TYPE ;
  TYPE t_ind_acti        IS TABLE OF ZON_TMP_DIREC_ZONA.ind_acti%TYPE ;
  TYPE t_usu_crea        IS TABLE OF ZON_TMP_DIREC_ZONA.usu_crea%TYPE ;
  TYPE t_fec_crea        IS TABLE OF ZON_TMP_DIREC_ZONA.fec_crea%TYPE ;
  TYPE t_usu_modi        IS TABLE OF ZON_TMP_DIREC_ZONA.usu_modi%TYPE ;
  TYPE t_fec_modi        IS TABLE OF ZON_TMP_DIREC_ZONA.fec_modi%TYPE ;
  TYPE t_est_regi        IS TABLE OF ZON_TMP_DIREC_ZONA.est_regi%TYPE ;


  v_pais_cod_pais     t_pais_cod_pais    ;
  v_cod_regi          t_cod_regi         ;
  v_cod_zona          t_cod_zona         ;
  v_des_zona          t_des_zona         ;
  v_clie_cod_clie     t_clie_cod_clie    ;
  v_ind_acti          t_ind_acti         ;
  v_usu_crea          t_usu_crea         ;
  v_fec_crea          t_fec_crea         ;
  v_usu_modi          t_usu_modi         ;
  v_fec_modi          t_fec_modi         ;
  v_est_regi          t_est_regi         ;


  rows NATURAL := 5000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;
  v_row_count NUMBER := 0;
  v_row_count_ins NUMBER := 0;

BEGIN
 COMMIT;
 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
   FETCH curUPDregiones BULK COLLECT INTO
      v_pais_cod_pais   ,
      v_cod_regi        ,
      v_cod_zona        ,
      v_des_zona        ,
      v_clie_cod_clie   ,
      v_ind_acti        ,
      v_usu_crea        ,
      v_fec_crea        ,
      v_usu_modi        ,
      v_fec_modi        ,
      v_est_regi
   LIMIT rows;

   EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
   v_row_count := curUPDregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     UPDATE ZON_DIREC_ZONA regio
     SET
        des_zona      = v_des_zona      (i),
        clie_cod_clie = v_clie_cod_clie (i),
        ind_acti      = v_ind_acti      (i),
        usu_crea      = v_usu_crea      (i),
        fec_crea      = v_fec_crea      (i),
        usu_modi      = v_usu_modi      (i),
        fec_modi      = v_fec_modi      (i),
        est_regi      = v_est_regi      (i)
     where regio.pais_cod_pais = v_pais_cod_pais(i)
       AND regio.regi_cod_regi = v_cod_regi(i)
       AND regio.cod_zona = v_cod_zona(i) ;
 END LOOP;
 CLOSE curUPDregiones;

 OPEN curINSregiones;
 LOOP
   -- Bulk collect data into memory table - X rows at a time
   FETCH curINSregiones BULK COLLECT INTO
        v_pais_cod_pais   ,
        v_cod_regi        ,
        v_cod_zona        ,
        v_des_zona        ,
        v_clie_cod_clie   ,
        v_ind_acti        ,
        v_usu_crea        ,
        v_fec_crea        ,
        v_usu_modi        ,
        v_fec_modi        ,
        v_est_regi
     LIMIT rows;
   EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
   v_row_count_ins := curINSregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     insert into ZON_DIREC_ZONA (
        pais_cod_pais,
        regi_cod_regi,
        cod_zona,
        des_zona,
        clie_cod_clie,
        ind_acti,
        usu_crea,
        fec_crea,
        usu_modi,
        fec_modi,
        est_regi
     )
     VALUES (
      v_pais_cod_pais (i),
      v_cod_regi      (i),
      v_cod_zona      (i),
      v_des_zona      (i),
      v_clie_cod_clie (i),
      v_ind_acti      (i),
      v_usu_crea      (i),
      v_fec_crea      (i),
      v_usu_modi      (i),
      v_fec_modi      (i),
      v_est_regi      (i)
     ) ;
 END LOOP;
 CLOSE curINSregiones;
 COMMIT;
 EXCEPTION
 WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGA_ZONAS: '||ls_sqlerrm);

 END ZON_PR_CARGA_ZONAS;


 /***************************************************************************
Descripcion : Procedure que Procesa Carga de CAMPAÑAS
Fecha Creacion : 11/12/2013
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE ZON_PR_CARGA_CAMPA (
    psCodigoPais VARCHAR2,
    psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 SELECT
    pais_cod_pais,
    cod_camp,
    fec_inic,
    fec_fina,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from
       ZON_TMP_DIREC_CAMPA reg
 WHERE reg.pais_cod_pais = psCodigoPais
    AND not exists ( select nu.cod_camp
                     from ZON_DIREC_CAMPA nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.cod_camp = reg.cod_camp ) ;

 CURSOR curUPDregiones
 IS
 select
    pais_cod_pais,
    cod_camp,
    fec_inic,
    fec_fina,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from ZON_TMP_DIREC_CAMPA reg
 WHERE reg.pais_cod_pais = psCodigoPais
   AND exists ( select nu.cod_camp
                    from ZON_DIREC_CAMPA nu
                    where nu.pais_cod_pais = psCodigoPais
                      AND nu.cod_camp = reg.cod_camp ) ;

  TYPE t_pais_cod_pais   IS TABLE OF ZON_TMP_DIREC_CAMPA.pais_cod_pais%TYPE ;
  TYPE t_cod_camp        IS TABLE OF ZON_TMP_DIREC_CAMPA.cod_camp%TYPE ;
  TYPE t_fec_inic        IS TABLE OF ZON_TMP_DIREC_CAMPA.fec_inic%TYPE ;
  TYPE t_fec_fina        IS TABLE OF ZON_TMP_DIREC_CAMPA.fec_fina%TYPE ;
  TYPE t_usu_crea        IS TABLE OF ZON_TMP_DIREC_CAMPA.usu_crea%TYPE ;
  TYPE t_fec_crea        IS TABLE OF ZON_TMP_DIREC_CAMPA.fec_crea%TYPE ;
  TYPE t_usu_modi        IS TABLE OF ZON_TMP_DIREC_CAMPA.usu_modi%TYPE ;
  TYPE t_fec_modi        IS TABLE OF ZON_TMP_DIREC_CAMPA.fec_modi%TYPE ;
  TYPE t_est_regi        IS TABLE OF ZON_TMP_DIREC_CAMPA.est_regi%TYPE ;


  v_pais_cod_pais     t_pais_cod_pais    ;
  v_cod_camp          t_cod_camp         ;
  v_fec_inic          t_fec_inic         ;
  v_fec_fina          t_fec_fina         ;
  v_usu_crea          t_usu_crea         ;
  v_fec_crea          t_fec_crea         ;
  v_usu_modi          t_usu_modi         ;
  v_fec_modi          t_fec_modi         ;
  v_est_regi          t_est_regi         ;


  rows NATURAL := 5000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;
  v_row_count NUMBER := 0;
  v_row_count_ins NUMBER := 0;

BEGIN
 COMMIT;
 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
   FETCH curUPDregiones BULK COLLECT INTO
      v_pais_cod_pais,
      v_cod_camp     ,
      v_fec_inic     ,
      v_fec_fina     ,
      v_usu_crea     ,
      v_fec_crea     ,
      v_usu_modi     ,
      v_fec_modi     ,
      v_est_regi
   LIMIT rows;

   EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
   v_row_count := curUPDregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     UPDATE ZON_DIREC_CAMPA regio
     SET
        fec_inic      = v_fec_inic      (i),
        fec_fina      = v_fec_fina      (i),
        usu_crea      = v_usu_crea      (i),
        fec_crea      = v_fec_crea      (i),
        usu_modi      = v_usu_modi      (i),
        fec_modi      = v_fec_modi      (i),
        est_regi      = v_est_regi      (i)
     where
       regio.pais_cod_pais = v_pais_cod_pais(i) and
       regio.cod_camp = v_cod_camp(i) ;
 END LOOP;
 CLOSE curUPDregiones;

 OPEN curINSregiones;
 LOOP
   -- Bulk collect data into memory table - X rows at a time
   FETCH curINSregiones BULK COLLECT INTO
        v_pais_cod_pais,
        v_cod_camp     ,
        v_fec_inic     ,
        v_fec_fina     ,
        v_usu_crea     ,
        v_fec_crea     ,
        v_usu_modi     ,
        v_fec_modi     ,
        v_est_regi
     LIMIT rows;
   EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
   v_row_count_ins := curINSregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     insert into ZON_DIREC_CAMPA (
        pais_cod_pais,
        cod_camp,
        fec_inic,
        fec_fina,
        usu_crea,
        fec_crea,
        usu_modi,
        fec_modi,
        est_regi
     )
     VALUES (
      v_pais_cod_pais (i),
      v_cod_camp      (i),
      v_fec_inic      (i),
      v_fec_fina      (i),
      v_usu_crea      (i),
      v_fec_crea      (i),
      v_usu_modi      (i),
      v_fec_modi      (i),
      v_est_regi      (i)
     ) ;
 END LOOP;
 CLOSE curINSregiones;
 COMMIT;
 EXCEPTION
 WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGA_CAMPA: '||ls_sqlerrm);

 END ZON_PR_CARGA_CAMPA;

 /***************************************************************************
Descripcion : Procedure que Procesa Carga de CONTROL DE FACTURACION
Fecha Creacion : 11/12/2013
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE ZON_PR_CARGA_CNTRL_FACTU (
    psCodigoPais VARCHAR2,
    psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 SELECT
    cod_pais,
    cam_proc,
    fec_proc,
    est_camp,
    ind_camp_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from
       ZON_TMP_DIREC_CNTRL_FACTU reg
 WHERE reg.cod_pais = psCodigoPais
    AND not exists ( select nu.cam_proc
                     from ZON_DIREC_CNTRL_FACTU nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.cam_proc = reg.cam_proc ) ;

 CURSOR curUPDregiones
 IS
 select
    cod_pais,
    cam_proc,
    fec_proc,
    est_camp,
    ind_camp_acti,
    usu_crea,
    fec_crea,
    usu_modi,
    fec_modi,
    est_regi
 from ZON_TMP_DIREC_CNTRL_FACTU reg
 WHERE reg.cod_pais = psCodigoPais
   AND exists ( select nu.cam_proc
                    from ZON_DIREC_CNTRL_FACTU nu
                    where nu.pais_cod_pais = psCodigoPais
                      AND nu.cam_proc = reg.cam_proc ) ;

  TYPE t_pais_cod_pais   IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.cod_pais%TYPE ;
  TYPE t_cam_proc        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.cam_proc%TYPE ;
  TYPE t_fec_proc        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.fec_proc%TYPE ;
  TYPE t_est_camp        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.est_camp%TYPE ;
  TYPE t_ind_camp_acti   IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.ind_camp_acti%TYPE ;
  TYPE t_usu_crea        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.usu_crea%TYPE ;
  TYPE t_fec_crea        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.fec_crea%TYPE ;
  TYPE t_usu_modi        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.usu_modi%TYPE ;
  TYPE t_fec_modi        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.fec_modi%TYPE ;
  TYPE t_est_regi        IS TABLE OF ZON_TMP_DIREC_CNTRL_FACTU.est_regi%TYPE ;


  v_pais_cod_pais     t_pais_cod_pais    ;
  v_cam_proc          t_cam_proc         ;
  v_fec_proc          t_fec_proc         ;
  v_est_camp          t_est_camp         ;
  v_ind_camp_acti     t_ind_camp_acti    ;
  v_usu_crea          t_usu_crea         ;
  v_fec_crea          t_fec_crea         ;
  v_usu_modi          t_usu_modi         ;
  v_fec_modi          t_fec_modi         ;
  v_est_regi          t_est_regi         ;


  rows NATURAL := 5000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;
  v_row_count NUMBER := 0;
  v_row_count_ins NUMBER := 0;

BEGIN
  COMMIT;
 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
   FETCH curUPDregiones BULK COLLECT INTO
      v_pais_cod_pais  ,
      v_cam_proc       ,
      v_fec_proc       ,
      v_est_camp       ,
      v_ind_camp_acti  ,
      v_usu_crea       ,
      v_fec_crea       ,
      v_usu_modi       ,
      v_fec_modi       ,
      v_est_regi
   LIMIT rows;

   EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
   v_row_count := curUPDregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     UPDATE ZON_DIREC_CNTRL_FACTU regio
     SET
        fec_proc      = v_fec_proc      (i),
        est_camp      = v_est_camp (i),
        ind_camp_acti = v_ind_camp_acti      (i),
        usu_crea      = v_usu_crea      (i),
        fec_crea      = v_fec_crea      (i),
        usu_modi      = v_usu_modi      (i),
        fec_modi      = v_fec_modi      (i),
        est_regi      = v_est_regi      (i)
     where
       regio.pais_cod_pais = v_pais_cod_pais(i) and
       regio.cam_proc = v_cam_proc(i) ;
 END LOOP;
 CLOSE curUPDregiones;

 OPEN curINSregiones;
 LOOP
   -- Bulk collect data into memory table - X rows at a time
   FETCH curINSregiones BULK COLLECT INTO
        v_pais_cod_pais  ,
        v_cam_proc       ,
        v_fec_proc       ,
        v_est_camp       ,
        v_ind_camp_acti  ,
        v_usu_crea       ,
        v_fec_crea       ,
        v_usu_modi       ,
        v_fec_modi       ,
        v_est_regi
     LIMIT rows;
   EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
   v_row_count_ins := curINSregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     insert into ZON_DIREC_CNTRL_FACTU (
        pais_cod_pais,
        cam_proc,
        fec_proc,
        est_camp,
        ind_camp_acti,
        usu_crea,
        fec_crea,
        usu_modi,
        fec_modi,
        est_regi
     )
     VALUES (
      v_pais_cod_pais  (i),
      v_cam_proc       (i),
      v_fec_proc       (i),
      v_est_camp       (i),
      v_ind_camp_acti  (i),
      v_usu_crea       (i),
      v_fec_crea       (i),
      v_usu_modi       (i),
      v_fec_modi       (i),
      v_est_regi       (i)
     ) ;
 END LOOP;
 CLOSE curINSregiones;
 COMMIT;
 EXCEPTION
 WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGA_CNTRL_FACTU: '||ls_sqlerrm);

 END ZON_PR_CARGA_CNTRL_FACTU;


 /***************************************************************************
Descripcion : Procedure que Procesa Carga de CONSULTORAS
Fecha Creacion : 11/12/2013
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE ZON_PR_CARGA_CONSU (
    psCodigoPais VARCHAR2,
    psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 SELECT
  pais_cod_pais,
  cod_clie,
  ape_pate,
  ape_mate,
  ape_casa,
  val_nom1,
  val_nom2,
  num_docu,
  num_telf_casa,
  num_telf_celu,
  dir_mail,
  dir_domi,
  ubi_domi,
  dir_entr,
  ubi_entr,
  fec_naci,
  fec_ingr,
  cam_ingr,
  ind_acti,
  usu_crea,
  fec_crea,
  usu_modi,
  fec_modi,
  est_regi,
  cod_cub,
  cod_empl,
  cod_grup_func,
  des_grup_func,
  cod_cub_jefe,
  val_rela_cont,
  val_usua_red,
  val_nom_jefe,
  val_pue_org,
  dir_mail_belc,
  cod_tipo_clie,
  cod_subt_clie
 from
       ZON_TMP_DIREC_CLIEN reg
 WHERE reg.pais_cod_pais = psCodigoPais
    AND not exists ( select nu.cod_clie
                     from ZON_DIREC_CLIEN nu
                     where nu.pais_cod_pais = psCodigoPais
                       AND nu.cod_clie = reg.cod_clie ) ;

 CURSOR curUPDregiones
 IS
 select
  pais_cod_pais,
  cod_clie,
  ape_pate,
  ape_mate,
  ape_casa,
  val_nom1,
  val_nom2,
  num_docu,
  num_telf_casa,
  num_telf_celu,
  dir_mail,
  dir_domi,
  ubi_domi,
  dir_entr,
  ubi_entr,
  fec_naci,
  fec_ingr,
  cam_ingr,
  ind_acti,
  usu_crea,
  fec_crea,
  usu_modi,
  fec_modi,
  est_regi,
  cod_cub,
  cod_empl,
  cod_grup_func,
  des_grup_func,
  cod_cub_jefe,
  val_rela_cont,
  val_usua_red,
  val_nom_jefe,
  val_pue_org,
  dir_mail_belc,
  cod_tipo_clie,
  cod_subt_clie
 from ZON_TMP_DIREC_CLIEN reg
 WHERE reg.pais_cod_pais = psCodigoPais
   AND exists ( select nu.cod_clie
                    from ZON_DIREC_CLIEN nu
                    where nu.pais_cod_pais = psCodigoPais
                      AND nu.cod_clie = reg.cod_clie ) ;

  TYPE t_pais_cod_pais    IS TABLE OF ZON_TMP_DIREC_CLIEN.pais_cod_pais%TYPE ;
  TYPE t_cod_clie         IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_clie%TYPE ;
  TYPE t_ape_pate         IS TABLE OF ZON_TMP_DIREC_CLIEN.ape_pate%TYPE ;
  TYPE t_ape_mate         IS TABLE OF ZON_TMP_DIREC_CLIEN.ape_mate%TYPE ;
  TYPE t_ape_casa         IS TABLE OF ZON_TMP_DIREC_CLIEN.ape_casa%TYPE ;
  TYPE t_val_nom1         IS TABLE OF ZON_TMP_DIREC_CLIEN.val_nom1%TYPE ;
  TYPE t_val_nom2         IS TABLE OF ZON_TMP_DIREC_CLIEN.val_nom2%TYPE ;
  TYPE t_num_docu         IS TABLE OF ZON_TMP_DIREC_CLIEN.num_docu%TYPE ;
  TYPE t_num_telf_casa    IS TABLE OF ZON_TMP_DIREC_CLIEN.num_telf_casa%TYPE ;
  TYPE t_num_telf_celu    IS TABLE OF ZON_TMP_DIREC_CLIEN.num_telf_celu%TYPE ;
  TYPE t_dir_mail         IS TABLE OF ZON_TMP_DIREC_CLIEN.dir_mail%TYPE ;
  TYPE t_dir_domi         IS TABLE OF ZON_TMP_DIREC_CLIEN.dir_domi%TYPE ;
  TYPE t_ubi_domi         IS TABLE OF ZON_TMP_DIREC_CLIEN.ubi_domi%TYPE ;
  TYPE t_dir_entr         IS TABLE OF ZON_TMP_DIREC_CLIEN.dir_entr%TYPE ;
  TYPE t_ubi_entr         IS TABLE OF ZON_TMP_DIREC_CLIEN.ubi_entr%TYPE ;
  TYPE t_fec_naci         IS TABLE OF ZON_TMP_DIREC_CLIEN.fec_naci%TYPE ;
  TYPE t_fec_ingr         IS TABLE OF ZON_TMP_DIREC_CLIEN.fec_ingr%TYPE ;
  TYPE t_cam_ingr         IS TABLE OF ZON_TMP_DIREC_CLIEN.cam_ingr%TYPE ;
  TYPE t_ind_acti         IS TABLE OF ZON_TMP_DIREC_CLIEN.ind_acti%TYPE ;
  TYPE t_usu_crea         IS TABLE OF ZON_TMP_DIREC_CLIEN.usu_crea%TYPE ;
  TYPE t_fec_crea         IS TABLE OF ZON_TMP_DIREC_CLIEN.fec_crea%TYPE ;
  TYPE t_usu_modi         IS TABLE OF ZON_TMP_DIREC_CLIEN.usu_modi%TYPE ;
  TYPE t_fec_modi         IS TABLE OF ZON_TMP_DIREC_CLIEN.fec_modi%TYPE ;
  TYPE t_est_regi         IS TABLE OF ZON_TMP_DIREC_CLIEN.est_regi%TYPE ;
  TYPE t_cod_cub          IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_cub%TYPE ;
  TYPE t_cod_empl         IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_empl%TYPE ;
  TYPE t_cod_grup_func    IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_grup_func%TYPE ;
  TYPE t_des_grup_func    IS TABLE OF ZON_TMP_DIREC_CLIEN.des_grup_func%TYPE ;
  TYPE t_cod_cub_jefe     IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_cub_jefe%TYPE ;
  TYPE t_val_rela_cont    IS TABLE OF ZON_TMP_DIREC_CLIEN.val_rela_cont%TYPE ;
  TYPE t_val_usua_red     IS TABLE OF ZON_TMP_DIREC_CLIEN.val_usua_red%TYPE ;
  TYPE t_val_nom_jefe     IS TABLE OF ZON_TMP_DIREC_CLIEN.val_nom_jefe%TYPE ;
  TYPE t_val_pue_org      IS TABLE OF ZON_TMP_DIREC_CLIEN.val_pue_org%TYPE ;
  TYPE t_dir_mail_belc    IS TABLE OF ZON_TMP_DIREC_CLIEN.dir_mail_belc%TYPE ;
  TYPE t_cod_tipo_clie    IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_tipo_clie%TYPE ;
  TYPE t_cod_subt_clie    IS TABLE OF ZON_TMP_DIREC_CLIEN.cod_subt_clie%TYPE ;

  v_pais_cod_pais     t_pais_cod_pais    ;
  v_cod_clie          t_cod_clie         ;
  v_ape_pate          t_ape_pate         ;
  v_ape_mate          t_ape_mate         ;
  v_ape_casa          t_ape_casa         ;
  v_val_nom1          t_val_nom1         ;
  v_val_nom2          t_val_nom2         ;
  v_num_docu          t_num_docu         ;
  v_num_telf_casa     t_num_telf_casa    ;
  v_num_telf_celu     t_num_telf_celu    ;
  v_dir_mail          t_dir_mail         ;
  v_dir_domi          t_dir_domi         ;
  v_ubi_domi          t_ubi_domi         ;
  v_dir_entr          t_dir_entr         ;
  v_ubi_entr          t_ubi_entr         ;
  v_fec_naci          t_fec_naci         ;
  v_fec_ingr          t_fec_ingr         ;
  v_cam_ingr          t_cam_ingr         ;
  v_ind_acti          t_ind_acti         ;
  v_usu_crea          t_usu_crea         ;
  v_fec_crea          t_fec_crea         ;
  v_usu_modi          t_usu_modi         ;
  v_fec_modi          t_fec_modi         ;
  v_est_regi          t_est_regi         ;
  v_cod_cub           t_cod_cub          ;
  v_cod_empl          t_cod_empl         ;
  v_cod_grup_func     t_cod_grup_func    ;
  v_des_grup_func     t_des_grup_func    ;
  v_cod_cub_jefe      t_cod_cub_jefe     ;
  v_val_rela_cont     t_val_rela_cont    ;
  v_val_usua_red      t_val_usua_red     ;
  v_val_nom_jefe      t_val_nom_jefe     ;
  v_val_pue_org       t_val_pue_org      ;
  v_dir_mail_belc     t_dir_mail_belc    ;
  v_cod_tipo_clie     t_cod_tipo_clie    ;
  v_cod_subt_clie     t_cod_subt_clie    ;

  rows NATURAL := 5000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;
  v_row_count NUMBER := 0;
  v_row_count_ins NUMBER := 0;

BEGIN
 COMMIT;
 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
   FETCH curUPDregiones BULK COLLECT INTO
      v_pais_cod_pais     ,
      v_cod_clie          ,
      v_ape_pate          ,
      v_ape_mate          ,
      v_ape_casa          ,
      v_val_nom1          ,
      v_val_nom2          ,
      v_num_docu          ,
      v_num_telf_casa     ,
      v_num_telf_celu     ,
      v_dir_mail          ,
      v_dir_domi          ,
      v_ubi_domi          ,
      v_dir_entr          ,
      v_ubi_entr          ,
      v_fec_naci          ,
      v_fec_ingr          ,
      v_cam_ingr          ,
      v_ind_acti          ,
      v_usu_crea          ,
      v_fec_crea          ,
      v_usu_modi          ,
      v_fec_modi          ,
      v_est_regi          ,
      v_cod_cub           ,
      v_cod_empl          ,
      v_cod_grup_func     ,
      v_des_grup_func     ,
      v_cod_cub_jefe      ,
      v_val_rela_cont     ,
      v_val_usua_red      ,
      v_val_nom_jefe      ,
      v_val_pue_org       ,
      v_dir_mail_belc     ,
      v_cod_tipo_clie     ,
      v_cod_subt_clie
   LIMIT rows;

   EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
   v_row_count := curUPDregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     UPDATE ZON_DIREC_CLIEN regio
     SET
      ape_pate           = v_ape_pate          (i),
      ape_mate           = v_ape_mate          (i),
      --ape_casa           = v_ape_casa          (i),
      val_nom1           = v_val_nom1          (i),
      val_nom2           = v_val_nom2          (i),
      num_docu           = v_num_docu          (i),
      num_telf_casa      = v_num_telf_casa     (i),
      num_telf_celu      = v_num_telf_celu     (i),
      dir_mail           = v_dir_mail          (i),
      dir_domi           = v_dir_domi          (i),
      ubi_domi           = v_ubi_domi          (i),
      dir_entr           = v_dir_entr          (i),
      ubi_entr           = v_ubi_entr          (i),
      fec_naci           = v_fec_naci          (i),
      fec_ingr           = v_fec_ingr          (i),
      cam_ingr           = v_cam_ingr          (i),
      ind_acti           = v_ind_acti          (i),
      usu_crea           = v_usu_crea          (i),
      fec_crea           = v_fec_crea          (i),
      usu_modi           = v_usu_modi          (i),
      fec_modi           = v_fec_modi          (i),
      est_regi           = v_est_regi          (i),
      cod_cub            = v_cod_cub           (i),
      cod_empl           = v_cod_empl          (i),
      cod_grup_func      = v_cod_grup_func     (i),
      des_grup_func      = v_des_grup_func     (i),
      cod_cub_jefe       = v_cod_cub_jefe      (i),
      val_rela_cont      = v_val_rela_cont     (i),
      val_usua_red       = v_val_usua_red      (i),
      val_nom_jefe       = v_val_nom_jefe      (i),
      val_pues_org        = v_val_pue_org       (i),
      dir_mail_belc      = v_dir_mail_belc     (i),
      cod_tipo_clie      = v_cod_tipo_clie     (i),
      cod_subt_clie      = v_cod_subt_clie     (i)
     where
       regio.pais_cod_pais = v_pais_cod_pais(i) and
       regio.cod_clie = v_cod_clie(i) ;
 END LOOP;
 CLOSE curUPDregiones;
 COMMIT;

 OPEN curINSregiones;
 LOOP
   -- Bulk collect data into memory table - X rows at a time
   FETCH curINSregiones BULK COLLECT INTO
      v_pais_cod_pais     ,
      v_cod_clie          ,
      v_ape_pate          ,
      v_ape_mate          ,
      v_ape_casa          ,
      v_val_nom1          ,
      v_val_nom2          ,
      v_num_docu          ,
      v_num_telf_casa     ,
      v_num_telf_celu     ,
      v_dir_mail          ,
      v_dir_domi          ,
      v_ubi_domi          ,
      v_dir_entr          ,
      v_ubi_entr          ,
      v_fec_naci          ,
      v_fec_ingr          ,
      v_cam_ingr          ,
      v_ind_acti          ,
      v_usu_crea          ,
      v_fec_crea          ,
      v_usu_modi          ,
      v_fec_modi          ,
      v_est_regi          ,
      v_cod_cub           ,
      v_cod_empl          ,
      v_cod_grup_func     ,
      v_des_grup_func     ,
      v_cod_cub_jefe      ,
      v_val_rela_cont     ,
      v_val_usua_red      ,
      v_val_nom_jefe      ,
      v_val_pue_org       ,
      v_dir_mail_belc     ,
      v_cod_tipo_clie     ,
      v_cod_subt_clie
     LIMIT rows;
   EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
   v_row_count_ins := curINSregiones%ROWCOUNT;

   -- Bulk bind of data in memory table...
   FORALL i IN 1..v_pais_cod_pais.count
     insert into ZON_DIREC_CLIEN (
        pais_cod_pais,
        cod_clie,
        ape_pate,
        ape_mate,
        --ape_casa,
        val_nom1,
        val_nom2,
        num_docu,
        num_telf_casa,
        num_telf_celu,
        dir_mail,
        dir_domi,
        ubi_domi,
        dir_entr,
        ubi_entr,
        fec_naci,
        fec_ingr,
        cam_ingr,
        ind_acti,
        usu_crea,
        fec_crea,
        usu_modi,
        fec_modi,
        est_regi,
        cod_cub,
        cod_empl,
        cod_grup_func,
        des_grup_func,
        cod_cub_jefe,
        val_rela_cont,
        val_usua_red,
        val_nom_jefe,
        val_pues_org,
        dir_mail_belc,
        cod_tipo_clie,
        cod_subt_clie
     )
     VALUES (
      v_pais_cod_pais     (i),
      v_cod_clie          (i),
      v_ape_pate          (i),
      v_ape_mate          (i),
      --v_ape_casa          (i),
      v_val_nom1          (i),
      v_val_nom2          (i),
      v_num_docu          (i),
      v_num_telf_casa     (i),
      v_num_telf_celu     (i),
      v_dir_mail          (i),
      v_dir_domi          (i),
      v_ubi_domi          (i),
      v_dir_entr          (i),
      v_ubi_entr          (i),
      v_fec_naci          (i),
      v_fec_ingr          (i),
      v_cam_ingr          (i),
      v_ind_acti          (i),
      v_usu_crea          (i),
      v_fec_crea          (i),
      v_usu_modi          (i),
      v_fec_modi          (i),
      v_est_regi          (i),
      v_cod_cub           (i),
      v_cod_empl          (i),
      v_cod_grup_func     (i),
      v_des_grup_func     (i),
      v_cod_cub_jefe      (i),
      v_val_rela_cont     (i),
      v_val_usua_red      (i),
      v_val_nom_jefe      (i),
      v_val_pue_org       (i),
      v_dir_mail_belc     (i),
      v_cod_tipo_clie     (i),
      v_cod_subt_clie     (i)
     ) ;
 END LOOP;
 CLOSE curINSregiones;
 COMMIT;
 EXCEPTION
 WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGA_CONSU: '||ls_sqlerrm);

 END ZON_PR_CARGA_CONSU;


/******************************************************************************
Descripcion       : Proceso de Actualización de Gerentes Region y Zona SSICC
Fecha Modificacion: 17-01-2014
Parametros:
psCodigoPais     : Codigo de Pais
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ZON_ACTUA_GEREN_SSICC
  (psCodigoPais       VARCHAR2)
IS
   CURSOR c_interfazRegion IS
     WITH temp_regio AS
     (
       (
        SELECT  zr.cod_regi codRegion,
                cli.cod_clie codCliente,
                TO_CHAR(zr.fec_ulti_actu , 'DD/MM/YYYY')  fecInicio
          FROM zon_regio zr,
               mae_clien cli
         WHERE zr.ind_acti = '1'
           AND zr.ind_borr = '0'
           AND zr.clie_oid_clie = cli.oid_clie
       )
     )
     SELECT * FROM temp_regio ORDER BY codRegion;

   CURSOR c_interfazZona IS
     WITH temp_zona AS
     (
       (
        SELECT  zr.cod_regi codigoRegion,
                zz.cod_zona codigoZona,
                cli.cod_clie codCliente,
                TO_CHAR(zz.fec_ulti_actu, 'DD/MM/YYYY')  fecInicio
          FROM  zon_regio zr,
                zon_zona zz,
                mae_clien cli
         WHERE zr.ind_acti = '1'
           AND zr.ind_borr = '0'
           AND zz.ind_acti = '1'
           AND zz.ind_borr = '0'
           AND zr.oid_regi = zz.zorg_oid_regi
           AND zz.clie_oid_clie = cli.oid_clie
       )
     )
     SELECT * FROM temp_zona ORDER BY codigoRegion, codigoZona ;

   TYPE interfazReg IS RECORD
     (
       codRegion       zon_regio.cod_regi%TYPE,
       codCliente      mae_clien.cod_clie%TYPE,
       fecInicio       VARCHAR2(10)
     );

   TYPE interfazRegTab  IS TABLE OF interfazReg ;
   interfazRecReg interfazRegTab;

      TYPE interfazZon IS RECORD
     (
       codRegion       zon_regio.cod_regi%TYPE,
       codZona         zon_zona.cod_zona%TYPE,
       codCliente      mae_clien.cod_clie%TYPE,
       fecInicio       VARCHAR2(10)
     );

   TYPE interfazZonTab  IS TABLE OF interfazZon ;
   interfazRecZon interfazZonTab;

   W_FILAS             NUMBER := 1000 ;

BEGIN
        OPEN c_interfazRegion;
          LOOP
             FETCH c_interfazRegion BULK COLLECT INTO interfazRecReg LIMIT W_FILAS;

               IF interfazRecReg.COUNT > 0 THEN
                  FOR x IN interfazRecReg.FIRST .. interfazRecReg.LAST LOOP

                     INSERT INTO ZON_DIREC_VENTA_CABEC(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     FEC_REGI,CAM_PROC,COR_DIRE_VENT,ESCA_COD_ESTA_CARG,MOTI_COD_MOTI_LICE,COD_CLIE_REEM,FEC_REGR,
                     IND_ESTA,USU_CREA,FEC_CREA,EST_REGI,IND_NOVE) VALUES

                     (psCodigoPais,'IN','GR', interfazRecReg(x).codCliente , interfazRecReg(x).fecInicio ,
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA2(psCodigoPais,'T','VD',interfazRecReg(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL,'A',NULL,NULL,NULL,'A',USER,SYSDATE,1,'M');

                     INSERT INTO ZON_DIREC_VENTA_DETAL(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     DICA_FEC_REGI,DICA_CAM_PROC,DICA_COR_DIRE_VENT,COR_DIVE_DETA,COD_SUBG,COD_REGI,COD_ZONA,USU_CREA,
                     FEC_CREA,EST_REGI)  VALUES

                     (psCodigoPais,'IN','GR', interfazRecReg(x).codCliente , interfazRecReg(x).fecInicio ,
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA2(psCodigoPais,'T','VD', interfazRecReg(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.CURRVAL,ZON_SEQ_DIREC_VENTA_DETAL.nextval,'01',
                     interfazRecReg(x).codRegion , NULL,USER,SYSDATE,1);

                  END LOOP;
               END IF;
             EXIT WHEN c_interfazRegion%NOTFOUND;
          END LOOP;
        CLOSE c_interfazRegion;

        OPEN c_interfazZona;
          LOOP
             FETCH c_interfazZona BULK COLLECT INTO interfazRecZon LIMIT W_FILAS;

               IF interfazRecZon.COUNT > 0 THEN
                  FOR x IN interfazRecZon.FIRST .. interfazRecZon.LAST LOOP

                     INSERT INTO ZON_DIREC_VENTA_CABEC (PAIS_COD_PAIS, TIOP_COD_TIPO_OPER, TICA_COD_TIPO_CARG,
                     COD_CLIE, FEC_REGI, CAM_PROC, COR_DIRE_VENT, ESCA_COD_ESTA_CARG, MOTI_COD_MOTI_LICE,
                     COD_CLIE_REEM, FEC_REGR,
                      IND_ESTA, USU_CREA, FEC_CREA, EST_REGI, IND_NOVE) VALUES

                     (psCodigoPais, 'IN', 'GZ', interfazRecZon(x).codCliente ,  interfazRecZon(x).fecInicio ,
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA2(psCodigoPais,'T','VD',interfazRecZon(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL,'A',NULL,NULL,NULL,'A',USER,SYSDATE,1,'M');

                     INSERT INTO ZON_DIREC_VENTA_DETAL(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     DICA_FEC_REGI,DICA_CAM_PROC,DICA_COR_DIRE_VENT,COR_DIVE_DETA,COD_SUBG,COD_REGI,COD_ZONA,USU_CREA,
                     FEC_CREA,EST_REGI)  VALUES

                     (psCodigoPais,'IN','GZ', interfazRecZon(x).codCliente , interfazRecZon(x).fecInicio ,
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA2(psCodigoPais,'T','VD', interfazRecZon(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.CURRVAL,ZON_SEQ_DIREC_VENTA_DETAL.nextval,'01',
                     interfazRecZon(x).codRegion ,  interfazRecZon(x).codZona ,USER,SYSDATE,1);

                  END LOOP;
               END IF;
             EXIT WHEN c_interfazZona%NOTFOUND;
          END LOOP;
        CLOSE c_interfazZona;

  EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_ZON_ACTUA_GEREN_SSICC: '||ls_sqlerrm);

END INT_PR_ZON_ACTUA_GEREN_SSICC;

/******************************************************************************
Descripcion       : Proceso de Actualización de Gerentes Region y Zona
Fecha Modificacion: 17-01-2014
Parametros:
psCodigoPais     : Codigo de Pais
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ZON_ACTUA_GEREN_FOX
  (psCodigoPais       VARCHAR2)
IS
   CURSOR c_interfazRegion IS
     WITH temp_regio AS
     (
       (
        SELECT  zr.cod_regi codRegion,
                cli.cod_clie codCliente,
                TO_CHAR(zr.fec_crea , 'DD/MM/YYYY')  fecInicio
          FROM zon_direc_regio zr,
               zon_direc_clien cli
         WHERE zr.ind_acti = '1'
           AND zr.est_regi <> '9'
           AND zr.clie_cod_clie = cli.cod_clie
           AND zr.pais_cod_pais = cli.pais_cod_pais
           AND cli.pais_cod_pais = psCodigoPais
       )
     )
     SELECT * FROM temp_regio ORDER BY codRegion;

   CURSOR c_interfazZona IS
     WITH temp_zona AS
     (
       (
        SELECT  zr.cod_regi codRegion,
                zz.cod_zona codZona,
                cli.cod_clie codCliente,
                TO_CHAR(zz.fec_crea, 'DD/MM/YYYY')  fecInicio
          FROM  zon_direc_regio zr,
                zon_direc_zona zz,
                zon_direc_clien cli
         WHERE zr.ind_acti = '1'
           AND zr.est_regi <> '9'
           AND zz.ind_acti = '1'
           AND zz.est_regi <> '9'
           AND zr.cod_regi = zz.regi_cod_regi
           AND zr.pais_cod_pais = zz.pais_cod_pais
           AND zz.clie_cod_clie = cli.cod_clie
           AND zz.pais_cod_pais = cli.pais_cod_pais
           AND cli.pais_cod_pais = psCodigoPais
       )
     )
     SELECT * FROM temp_zona ORDER BY codRegion, codZona ;

   TYPE interfazReg IS RECORD
     (
        codRegion       zon_direc_regio.cod_regi%TYPE,
        codCliente      zon_direc_regio.clie_cod_clie%TYPE,
        fecInicio       VARCHAR2(10)

     );

   TYPE interfazRegTab  IS TABLE OF interfazReg ;
   interfazRecReg interfazRegTab;

      TYPE interfazZon IS RECORD
     (

       codRegion       zon_direc_regio.cod_regi%TYPE,
       codZona         zon_direc_zona.cod_zona%TYPE,
       codCliente      zon_direc_zona.clie_cod_clie%TYPE,
       fecInicio       VARCHAR2(10)
     );

   TYPE interfazZonTab  IS TABLE OF interfazZon ;
   interfazRecZon interfazZonTab;

   W_FILAS             NUMBER := 1000 ;

BEGIN
        OPEN c_interfazRegion;
          LOOP
             FETCH c_interfazRegion BULK COLLECT INTO interfazRecReg LIMIT W_FILAS;

               IF interfazRecReg.COUNT > 0 THEN
                  FOR x IN interfazRecReg.FIRST .. interfazRecReg.LAST LOOP

                     INSERT INTO ZON_DIREC_VENTA_CABEC(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     FEC_REGI,CAM_PROC,COR_DIRE_VENT,ESCA_COD_ESTA_CARG,MOTI_COD_MOTI_LICE,COD_CLIE_REEM,FEC_REGR,
                     IND_ESTA,USU_CREA,FEC_CREA,EST_REGI,IND_NOVE) VALUES

                     (psCodigoPais,'IN','GR', interfazRecReg(x).codCliente , interfazRecReg(x).fecInicio ,
                     GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais,interfazRecReg(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL,'A',NULL,NULL,NULL,'A',USER,SYSDATE,1,'M');

                     INSERT INTO ZON_DIREC_VENTA_DETAL(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     DICA_FEC_REGI,DICA_CAM_PROC,DICA_COR_DIRE_VENT,COR_DIVE_DETA,COD_SUBG,COD_REGI,COD_ZONA,USU_CREA,
                     FEC_CREA,EST_REGI)  VALUES

                     (psCodigoPais,'IN','GR', interfazRecReg(x).codCliente , interfazRecReg(x).fecInicio ,
                     GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais, interfazRecReg(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.CURRVAL,ZON_SEQ_DIREC_VENTA_DETAL.nextval,'01',
                     interfazRecReg(x).codRegion , NULL,USER,SYSDATE,1);

                     INSERT INTO ZON_DIREC_HISTO_GEREN(PAIS_COD_PAIS, COR_HIST_GERE,UNI_ADMI,REGI_COD_REGI,ZONA_COD_ZONA,
                     CLIE_COD_CLIE,FEC_DESD,FEC_HAST,CAM_DESD,CAM_HAST,USU_CREA,FEC_CREA,USU_MODI,FEC_MODI,EST_REGI) VALUES

                     (psCodigoPais, zon_direc_hger_seq.nextval , '01'|| interfazRecReg(x).codRegion ,
                     interfazRecReg(x).codRegion, null, interfazRecReg(x).codCliente , interfazRecReg(x).fecInicio ,
                     NULL, GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais, interfazRecReg(x).fecInicio),
                     NULL, USER,SYSDATE, NULL, NULL, 1);

                  END LOOP;
               END IF;
             EXIT WHEN c_interfazRegion%NOTFOUND;
          END LOOP;
        CLOSE c_interfazRegion;

        OPEN c_interfazZona;
          LOOP
             FETCH c_interfazZona BULK COLLECT INTO interfazRecZon LIMIT W_FILAS;

               IF interfazRecZon.COUNT > 0 THEN
                  FOR x IN interfazRecZon.FIRST .. interfazRecZon.LAST LOOP

                     INSERT INTO ZON_DIREC_VENTA_CABEC(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     FEC_REGI,CAM_PROC,COR_DIRE_VENT,ESCA_COD_ESTA_CARG,MOTI_COD_MOTI_LICE,COD_CLIE_REEM,FEC_REGR,
                     IND_ESTA,USU_CREA,FEC_CREA,EST_REGI,IND_NOVE) VALUES

                     (psCodigoPais,'IN','GZ', interfazRecZon(x).codCliente , interfazRecZon(x).fecInicio ,
                     GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais,interfazRecZon(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.NEXTVAL,'A',NULL,NULL,NULL,'A',USER,SYSDATE,1,'M');

                     INSERT INTO ZON_DIREC_VENTA_DETAL(PAIS_COD_PAIS,TIOP_COD_TIPO_OPER,TICA_COD_TIPO_CARG,COD_CLIE,
                     DICA_FEC_REGI,DICA_CAM_PROC,DICA_COR_DIRE_VENT,COR_DIVE_DETA,COD_SUBG,COD_REGI,COD_ZONA,USU_CREA,
                     FEC_CREA,EST_REGI)  VALUES

                     (psCodigoPais,'IN','GZ', interfazRecZon(x).codCliente , interfazRecZon(x).fecInicio ,
                     GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais, interfazRecZon(x).fecInicio),
                     ZON_SEQ_DIREC_VENTA_CABEC.CURRVAL,ZON_SEQ_DIREC_VENTA_DETAL.nextval,'01',
                     interfazRecZon(x).codRegion ,  interfazRecZon(x).codZona ,USER,SYSDATE,1);

                     INSERT INTO ZON_DIREC_HISTO_GEREN(PAIS_COD_PAIS, COR_HIST_GERE,UNI_ADMI,REGI_COD_REGI,ZONA_COD_ZONA,
                     CLIE_COD_CLIE,FEC_DESD,FEC_HAST,CAM_DESD,CAM_HAST,USU_CREA,FEC_CREA,USU_MODI,FEC_MODI,EST_REGI) VALUES

                     (psCodigoPais, zon_direc_hger_seq.nextval , '01'|| interfazRecZon(x).codRegion || interfazRecZon(x).codZona ,
                     interfazRecZon(x).codRegion, interfazRecZon(x).codZona, interfazRecZon(x).codCliente , interfazRecZon(x).fecInicio ,
                     NULL, GEN_PKG_GENER.gen_fn_devuelve_perio_fecfox(psCodigoPais,interfazRecZon(x).fecInicio),
                     NULL, USER,SYSDATE, NULL, NULL, 1);

                  END LOOP;
               END IF;
             EXIT WHEN c_interfazZona%NOTFOUND;
          END LOOP;
        CLOSE c_interfazZona;


EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_ZON_ACTUA_GEREN_FOX: '||ls_sqlerrm);

END INT_PR_ZON_ACTUA_GEREN_FOX;


/**********************************************************************************
    Descripcion       : Realizamos las diferentes validaciones para ver si se puede
                        efectuar la asignacion de la lider a una respectiva seccion
    Fecha Creacion     : 24/02/2011
    Fecha Modificacion : 24/03/2014
    Autor             : Carlos Diaz Valverde, Juan Altamirano
    **********************************************************************************/
   FUNCTION ZON_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoCliente VARCHAR2,
                                           psIndicadorReingreso VARCHAR2,
                                           psIndicadorNoValidaUnicoLider VARCHAR2,
                                           lnNumeroActivasFinalesZona NUMBER,
                                           lnPromedioActFinalesSeccion NUMBER,
                                           psCodigoPeriodoActual VARCHAR2,
                                           psCodigoPeriodoValAsiLid VARCHAR2,
                                           pnOidSeccion NUMBER,
                                           psUnidadAdm VARCHAR2,
                                           pnOidPeriodoAnt NUMBER,
                                           psCodigoPeriodoAsigLiderSgte VARCHAR2,
                                           pscodsubgerencia varchar2,
                                           pscodregi varchar2,
                                           pscodzona varchar2,
                                           pscodsecc varchar2,
                                           psRealizarValidaciones varchar2,
                                           psIndicadorWEB varchar2
                                          )
   RETURN VARCHAR2 IS

     lnIdPais NUMBER;
     lnIdMarca NUMBER;
     lnIdCanal NUMBER;

     lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
     lnTotalTipos NUMBER;
     lsUnidadAdministrativa VARCHAR2(15);
     lnMinimoCampanas NUMBER;

     ldCodigoPeriodoHasta SEG_PERIO_CORPO.COD_PERI%TYPE;
     lnMinActivasFinalesZona NUMBER;
     lnMinActivasFinalesSeccion NUMBER;
     lnDiferenciaCampanas NUMBER;
     lsCodigoValidacion VARCHAR2(200);

     indicadorUnicoLiderSeccion LID_PARAM.IND_UNIC_LIDE_SECC%TYPE;

     psCodigoEstatus VARCHAR2(2);
     lnValidaCodigoEstatus NUMBER;
     lnOidCliente MAE_CLIEN.OID_CLIE%TYPE;
     vnIndProLid BAS_PAIS.IND_PROG_LIDE%TYPE;

     vnIndPerIni NUMBER(10);
     vnIndValSeccSinLid NUMBER(10);
     vsCodigoPeriodoValAsiLid VARCHAR2(6);
     vnIndValHisLid NUMBER(10);
     vnFlagValLid NUMBER(1) := 0;
     vsOidPeriodoValAsiLid ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnIndValSeccLid NUMBER(10);
     vsUnidadAdm zon_histo_geren.ua%type;
     vnOidPeriodoAnt ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnIndDesvAuto ZON_HISTO_GEREN.IND_DESV_AUTO%TYPE;
     vnIndRein     ZON_TIPO_DESVI.IND_REIN%TYPE;
     vsCodigoPeridoProceso SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vnCantidadPedidos let_param_rango_premi.Can_Pedi%TYPE;
     vnFinalesExijidasSeccion NUMBER;
     vnPorcentajeActividadMeta LET_PARAM_CONCU_LIDER.POR_ACTI_META%TYPE;
     vnIndActiMiniSecc LET_PARAM_CONCU_LIDER.IND_ACTI_MINI_SECC%TYPE;
     vnOidPeriodoQuiebreAnio ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnOidMaxPeriodoHasta ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnDescrTipoDesvi ZON_TIPO_DESVI.DES_TIPO_DESV%TYPE;
     vnOidHistGeren zon_histo_geren.oid_hist_gere%type;

     vsCountLiderCampAnte NUMBER;
     lscodigoperiodoant   Lec_Progr.Cam_Inic%type;
     lnidperiodoant       ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vsIndPosiLider       LEC_PROGR.Ind_Posi_Lide%type;
     vsIndPL              NUMBER;
     vsIndPLHG            NUMBER;

     vbRealizarValidaciones BOOLEAN:= TRUE;
   BEGIN
     IF psRealizarValidaciones IS NOT NULL AND psRealizarValidaciones = 'N' THEN
        vbRealizarValidaciones := FALSE;
     END IF;

     -- OBTENEMOS EL INDICADOR DE UNICO LIDER EN SECCION DE LID_PARAM
     indicadorUnicoLiderSeccion := 1;

     --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
     lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     lnIdCliente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente, TRUE);
     lsCodigoValidacion := '0__OK';

     lscodigoperiodoant := per_pkg_repor_perce.per_fn_obtie_perio(psCodigoPeriodoActual,
                                                                 lnIdPais,
                                                                 lnIdMarca,
                                                                 lnIdCanal,
                                                                 -1);

     lnidperiodoant := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodigoperiodoant,
                                                                 lnIdMarca,
                                                                 lnIdCanal);


     --(1) Validamos si existe el cliente ---------------------------------------------------------
     IF(lnIdCliente = -1) THEN
       lsCodigoValidacion := '1__ER';
       RETURN lsCodigoValidacion;
     END IF;

     --(2) Validamos si existe el cliente tiene tipoCliente : Consultora --------------------------
     SELECT    COUNT(1)
       INTO    lnTotalTipos
     FROM      MAE_CLIEN_TIPO_SUBTI sub,
               MAE_TIPO_CLIEN tip
     WHERE     sub.CLIE_OID_CLIE = lnIdCliente
       AND     sub.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
       AND     tip.COD_TIPO_CLIE = '02';

     IF(lnTotalTipos = 0) THEN
       lsCodigoValidacion := '2__ER';
       RETURN lsCodigoValidacion;
     END IF;

     --OBTENEMOS EL INDICADOR DE PROGRAMA LIDER.
     SELECT    NVL(IND_PROG_LIDE, 0)
       INTO    vnIndProLid
     FROM      BAS_PAIS
     WHERE     COD_PAIS = psCodigoPais;


     --Validar que cliente se encuentre en campania anterior para determinar
     --si se debe buscar en la Lista de Posibles Lideres.
     IF(vnIndProLid = 4) THEN
         SELECT COUNT(*)
            INTO vsCountLiderCampAnte
            FROM zon_histo_geren zh
           WHERE zh.gere = psCodigoCliente
             AND lnidperiodoant >= zh.perd_oid_peri_desd
             AND (lnidperiodoant <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

         --1- SI NO SE ENCUENTRA EN ZON_HISTO_GEREN
         IF(vsCountLiderCampAnte = 0)THEN
             --Obtenemos Indicador de posibleLider de LEC_PROGR
             BEGIN
               SELECT ind_posi_lide INTO vsIndPosiLider
                 FROM LEC_PROGR
                WHERE psCodigoPeriodoActual >= CAM_INIC
                  AND (psCodigoPeriodoActual <= CAM_FIN OR CAM_FIN IS NULL)
                  AND IND_ACTI = 1;
             EXCEPTION
               WHEN NO_DATA_FOUND THEN
                 vsIndPosiLider := 0;
             END;

             --Si indicador es = 1, Accesamos tabla LEC_PROGR_POSIB_LIDER
             IF(vsIndPosiLider = 1)THEN
               SELECT COUNT(*) INTO vsIndPL
                 FROM LEC_PROGR_POSIB_LIDER_TEMP
                WHERE PAIS_COD_PAIS = psCodigoPais
                  AND TRIM(COD_LIDE) = psCodigoCliente
                  AND IND_ACTI = '1';

                  IF(vsIndPL = 0)THEN

                    SELECT COUNT(*) INTO vsIndPLHG
                      FROM ZON_HISTO_GEREN
                     WHERE GERE = psCodigoCliente
                       AND COD_SECC IS NOT NULL
                       AND PERD_OID_PERI_HAST = lnidperiodoant;


                    IF(vsIndPLHG = 1)THEN
                          BEGIN
                             SELECT IND_DESV_AUTO INTO vnIndDesvAuto
                              FROM (SELECT  *
                                      FROM ZON_HISTO_GEREN
                                     WHERE LENGTH(UA) = 9
                                       AND PERD_OID_PERI_HAST IS NOT NULL
                                       AND GERE = psCodigoCliente
                                     ORDER BY PERD_OID_PERI_HAST DESC)
                             WHERE ROWNUM = 1;
                          EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                              vnIndDesvAuto := 0;
                          END;

                            IF(vnIndDesvAuto <> 0 AND vnIndDesvAuto <> 3)THEN
                               lsCodigoValidacion := '8__ER';
                               IF vbRealizarValidaciones AND psIndicadorWEB = 'S' THEN
                                 RETURN lsCodigoValidacion;
                               END IF;
                            END IF;

                    ELSE
                         lsCodigoValidacion := '8__ER';
                         IF vbRealizarValidaciones AND psIndicadorWEB = 'S' THEN
                           RETURN lsCodigoValidacion;
                         END IF;
                    END IF;


                  END IF;

             END IF;

         --2 DE ENCONTRARSE EN ZON_HISTO_GEREN
         ELSE
             BEGIN
               SELECT ind_posi_lide INTO vsIndPosiLider
                 FROM LEC_PROGR
                WHERE psCodigoPeriodoActual >= CAM_INIC
                  AND (psCodigoPeriodoActual <= CAM_FIN OR CAM_FIN IS NULL)
                  AND IND_ACTI = 1;
             EXCEPTION
               WHEN NO_DATA_FOUND THEN
                 vsIndPosiLider := 0;
             END;

             --Si indicador es = 1, Accesamos tabla LEC_PROGR_POSIB_LIDER
             IF(vsIndPosiLider = 1)THEN
                BEGIN
                   SELECT IND_DESV_AUTO INTO vnIndDesvAuto
                    FROM (SELECT  *
                            FROM ZON_HISTO_GEREN
                           WHERE LENGTH(UA) = 9
                             AND PERD_OID_PERI_HAST IS NOT NULL
                             AND GERE = psCodigoCliente
                           ORDER BY PERD_OID_PERI_HAST DESC)
                   WHERE ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vnIndDesvAuto := 0;
                END;

                --SI EL INDICADOR = 2
                IF(vnIndDesvAuto <> 0 AND vnIndDesvAuto <> 3)THEN
                   --Accesamos tabla LEC_PROGR_POSIB_LIDER_TEMP
                   SELECT COUNT(*) INTO vsIndPL
                     FROM LEC_PROGR_POSIB_LIDER_TEMP
                    WHERE PAIS_COD_PAIS = psCodigoPais
                      AND TRIM(COD_LIDE) = psCodigoCliente
                      AND IND_ACTI = '1';

                      --SI NO LO ENCUENTRA EN LEC_PROGR_POSIB_LIDER_TEMP, MOSTRAR ERROR.
                      IF(vsIndPL = 0)THEN
                         lsCodigoValidacion := '8__ER';
                         IF vbRealizarValidaciones AND psIndicadorWEB = 'S' THEN
                           RETURN lsCodigoValidacion;
                         END IF;
                      END IF;

                END IF;
            END IF;

         END IF;

     END IF;

     -- (3B) Validamos si la consultora ya es lider de alguna seccion. ----------------------------
     --      y si lo es le permite varios lideres en seccion
     IF(indicadorUnicoLiderSeccion = 0 AND psIndicadorNoValidaUnicoLider='N') THEN

       BEGIN
         SELECT    uni_adm
           INTO    lsUnidadAdministrativa
         FROM      (
                     SELECT    (
                                 sub.COD_SUBG_VENT ||
                                 reg.COD_REGI ||
                                 zon.COD_ZONA ||
                                 sec.COD_SECC
                               ) uni_adm
                     FROM      ZON_SECCI sec,
                               ZON_ZONA zon,
                               ZON_REGIO reg,
                               ZON_SUB_GEREN_VENTA sub
                     WHERE     sec.ZZON_OID_ZONA = zon.oid_zona
                       AND     zon.ZORG_OID_REGI = reg.oid_regi
                       AND     reg.zsgv_oid_subg_vent = sub.oid_subg_vent
                       AND     zon.pais_oid_pais = lnIdPais
                       AND     zon.marc_oid_marc = lnIdMarca
                       AND     zon.cana_oid_cana = lnIdCanal
                       AND     sec.ind_acti = '1'
                       AND     sec.clie_oid_clie = lnIdCliente
                   )
         WHERE     ROWNUM = 1;
       EXCEPTION
         WHEN OTHERS THEN
           lsUnidadAdministrativa := NULL;
       END;

       IF(lsUnidadAdministrativa IS NOT NULL) THEN
         lsCodigoValidacion := '3B__' || lsUnidadAdministrativa;
         RETURN lsCodigoValidacion;
       END IF;

     END IF;

   --(3) Validamos si la consultora ya es lider de alguna seccion. ------------------------------

     IF vnFlagValLid = 0 THEN
       vsCodigoPeriodoValAsiLid := psCodigoPeriodoValAsiLid;
     END IF;


     --(4) Validamos si la Consultora no cumple minimo de campañas para reingreso -----------------
     IF(psIndicadorReingreso = 'N') THEN

       -- capturar valor minimo de campaña
       BEGIN
         SELECT    VAL_NUME_MINI_CAMP
           INTO    lnMinimoCampanas
         FROM      ZON_PARAM_REING_CONSU
         WHERE     PAIS_OID_PAIS = lnIdPais
           AND     TIPO_DE_UA = 'SECCION';
       EXCEPTION
         WHEN OTHERS THEN
           lnMinimoCampanas := NULL;
       END;

       IF(lnMinimoCampanas IS NOT NULL) THEN

         BEGIN
           /* INI JJ PER-SiCC-2012-0201*/
           --Obtenemos el mayor periodo del historico de responsable para la consultora
           SELECT GEN_PKG_GENER.gen_fn_devuelve_des_perio(MAX(PERD_OID_PERI_HAST))
           INTO ldCodigoPeriodoHasta
           FROM ZON_HISTO_GEREN
           WHERE  PAIS_OID_PAIS = lnIdPais
           AND  MARC_OID_MARC = lnIdMarca
           AND  CANA_OID_CANA = lnIdCanal
           AND  GERE = psCodigoCliente
           AND  LENGTH(UA) = 9;

         EXCEPTION
           WHEN OTHERS THEN
             ldCodigoPeriodoHasta := NULL;
         END;

         IF(ldCodigoPeriodoHasta IS NOT NULL) THEN

           SELECT NVL(IND_DESV_AUTO,0)
           INTO vnIndDesvAuto
           FROM ZON_HISTO_GEREN
           WHERE  PAIS_OID_PAIS = lnIdPais
           AND  MARC_OID_MARC = lnIdMarca
           AND  CANA_OID_CANA = lnIdCanal
           AND  GERE = psCodigoCliente
           AND  LENGTH(UA) = 9
           AND  PERD_OID_PERI_HAST = (SELECT MAX(PERD_OID_PERI_HAST)
                                      FROM ZON_HISTO_GEREN
                                      WHERE  PAIS_OID_PAIS = lnIdPais
                                      AND  MARC_OID_MARC = lnIdMarca
                                      AND  CANA_OID_CANA = lnIdCanal
                                      AND  GERE = psCodigoCliente
                                      AND  LENGTH(UA) = 9);

          /* FIN JJ PER-SiCC-2012-0201*/

           lnDiferenciaCampanas := ( (VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(ldCodigoPeriodoHasta,vsCodigoPeriodoValAsiLid,lnIdPais,lnIdMarca,lnIdCanal) - 1) ) - 1;

           IF vnIndProLid = 1 OR vnIndProLid = 0 OR vnIndProLid IS NULL THEN

             IF(lnDiferenciaCampanas < lnMinimoCampanas) THEN
               lsCodigoValidacion := '4__' || TO_CHAR(lnMinimoCampanas)||';'||'0';
               IF vbRealizarValidaciones THEN
               RETURN lsCodigoValidacion;
             END IF;
             END IF;

           END IF;

           IF vnIndProLid = 2 THEN

             IF(lnDiferenciaCampanas < lnMinimoCampanas) THEN

               select IND_REIN
               into vnIndRein
               from ZON_TIPO_DESVI --se cambio el nombre 12022013
               where cod_tipo_desv = vnIndDesvAuto
               and EST_REGI = 1;

               IF vnIndRein <> 1 THEN
                 lsCodigoValidacion := '4__' || TO_CHAR(lnMinimoCampanas)||';'||'1';
                 IF vbRealizarValidaciones THEN
                 RETURN lsCodigoValidacion;
               END IF;

             END IF;

           END IF;

           END IF;

         END IF; -- IF(ldCodigoPeriodoHasta IS NOT NULL) THEN

       END IF; -- IF(lnMinimoCampanas IS NOT NULL) THEN

     END IF; -- IF(psIndicadorReingreso = 'N') THEN


     -- RECUPERAMOS LOS MININOS ACTIVAS FINALES DE ZONA (5) Y SECCION (6) -------------------------

     BEGIN

       IF(vnIndProLid = 2) THEN
         SELECT MIN_ACTI_FINA_ZONA, MIN_ACTI_FINA_SECC
           INTO lnMinActivasFinalesZona, lnMinActivasFinalesSeccion
           FROM LET_PARAM_CONCU_LIDER
          WHERE psCodigoPeriodoActual >= CAM_INIC
            AND psCodigoPeriodoActual <= CAM_FINA
            AND EST_REGI = '1';
       END IF;

       IF(vnIndProLid = 1) THEN
          SELECT MIN_ACTI_FINA_ZONA, MIN_ACTI_FINA_SECC
            INTO lnMinActivasFinalesZona, lnMinActivasFinalesSeccion
            FROM LID_PARAM
           WHERE COD_PAIS = psCodigoPais;
       END IF;

       IF(vnIndProLid = 0) THEN
           lnMinActivasFinalesZona := 0;
           vnFinalesExijidasSeccion := 0;
       END IF;
     EXCEPTION
       WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20123, 'NO SE ENCONTRO DATOS DE MINIMO DE ACTIVAS FINALES DE ZONA Y SECCION PARA EL PAIS');
     END;

     --Validamos el minimo de activas finales por Zona
     IF(lnNumeroActivasFinalesZona < lnMinActivasFinalesZona) THEN

       lsCodigoValidacion := '5__ER';
       IF vbRealizarValidaciones THEN
         RETURN lsCodigoValidacion;
       END IF;

     END IF;


     --PARA vnIndProLid = 2
     --Validamos el minimo de activas finales por Seccion
     IF vnIndProLid = 2 THEN
         BEGIN
         SELECT IND_ACTI_MINI_SECC
           INTO vnIndActiMiniSecc
           FROM LET_PARAM_CONCU_LIDER
          WHERE psCodigoPeriodoActual >= CAM_INIC
            AND psCodigoPeriodoActual <= CAM_FINA;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
             vnIndActiMiniSecc := 0;
         END;

         IF (vnIndActiMiniSecc = 1 AND psCodigoCliente IS NOT NULL) THEN

            SELECT COD_PERI INTO vsCodigoPeridoProceso
            FROM bas_ctrl_fact
            WHERE sta_camp = 0
            AND ind_camp_act = 1;

            SELECT COD_CONC,POR_ACTI_META INTO vsCodigoConcurso, vnPorcentajeActividadMeta
            FROM LET_PARAM_CONCU_LIDER
            WHERE vsCodigoPeridoProceso >= CAM_INIC
            AND vsCodigoPeridoProceso <= CAM_FINA
            AND EST_REGI = '1';

            SELECT CAN_PEDI INTO vnCantidadPedidos
            FROM let_param_rango_premi
            WHERE pais_cod_pais = psCodigoPais
            AND conc_cod_conc = vsCodigoConcurso
            AND rang_num_rang = 1;

            vnFinalesExijidasSeccion := FLOOR(vnCantidadPedidos / (vnPorcentajeActividadMeta / 100));

            --Si lnPromedioActFinalesSeccion >= vnFinalesExijidasSeccion pasa, sino:
            IF(lnPromedioActFinalesSeccion < vnFinalesExijidasSeccion) THEN
             lsCodigoValidacion := '6A__'||vnFinalesExijidasSeccion;

             IF vbRealizarValidaciones THEN
               RETURN lsCodigoValidacion;
             END IF;
            END IF;

           END IF;

     END IF;

     --(9.4) Decu v10.4
     --PARA vnIndProLid = 3
     --Validamos el minimo de activas finales por Seccion
     IF(vnIndProLid = 3) THEN
         BEGIN
             SELECT NUM_MINI_ACTI_SECC INTO vnFinalesExijidasSeccion
               FROM LET_CORPO_PARAM_PROGR
              WHERE psCodigoPeriodoActual >= CAM_INIC
                AND (psCodigoPeriodoActual <= CAM_FIN OR CAM_FIN IS NULL);
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
             vnFinalesExijidasSeccion := 0;
         END;

         --(34) Decu v10.4
         --Si lnPromedioActFinalesSeccion >= vnFinalesExijidasSeccion pasa, sino:
         IF lnPromedioActFinalesSeccion < vnFinalesExijidasSeccion THEN
           lsCodigoValidacion := '6B__'||vnFinalesExijidasSeccion;
           IF vbRealizarValidaciones THEN
             RETURN lsCodigoValidacion;
           END IF;
         END IF;

     END IF;

     --(9.6)--(42) Decu v17.0
     --PARA vnIndProLid = 4
     --Validamos el minimo de activas finales por Seccion
     IF(vnIndProLid = 4) THEN
         BEGIN
             SELECT NUM_ACTI_SECC_APTA INTO vnFinalesExijidasSeccion
               FROM LEC_PROGR
              WHERE psCodigoPeriodoActual >= CAM_INIC
                AND (psCodigoPeriodoActual <= CAM_FIN OR CAM_FIN IS NULL)
                AND IND_ACTI = 1
                AND ROWNUM = 1;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
             vnFinalesExijidasSeccion := 0;
         END;
          --Si lnPromedioActFinalesSeccion >= vnFinalesExijidasSeccion pasa, sino:
          IF lnPromedioActFinalesSeccion < vnFinalesExijidasSeccion THEN
             lsCodigoValidacion := '6C__'||vnFinalesExijidasSeccion;
             IF vbRealizarValidaciones THEN
               RETURN lsCodigoValidacion;
             END IF;
          END IF;
     END IF;


     --(7) Validamos si el status de la consultora se encuentra registrada en  --------------------

     IF(vnIndProLid = 2 OR vnIndProLid = 3) THEN

       lnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente);

       SELECT    COD_ESTA_CLIE
         INTO    psCodigoEstatus
       FROM      MAE_CLIEN_DATOS_ADICI A,
                 MAE_ESTAT_CLIEN B
       WHERE     A.ESTA_OID_ESTA_CLIE = B.OID_ESTA_CLIE
         AND     CLIE_OID_CLIE = lnOidCliente;

       SELECT    COUNT(1)
         INTO    lnValidaCodigoEstatus
       FROM      LET_ESTAT_LIDER
       WHERE     COD_ESTA = psCodigoEstatus;

       IF lnValidaCodigoEstatus = 0 THEN
         lsCodigoValidacion := '7__ER';
         IF vbRealizarValidaciones THEN
         RETURN lsCodigoValidacion;
       END IF;

     END IF;

     END IF;

     --(0) Si paso toda validación ----------------------------------------------------------------
     lsCodigoValidacion := '0__' || TO_CHAR(lnIdCliente);

   -- b) Validar lider si existe en otra seccion
     vsOidPeriodoValAsiLid := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeriodoValAsiLid);

     SELECT COUNT(1) INTO vnIndValSeccLid
       FROM zon_histo_geren zh
      WHERE zh.gere = psCodigoCliente
        AND  vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
        AND (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast OR zh.perd_oid_peri_hast IS NULL);

     IF(vnIndValSeccLid > 0)THEN
       SELECT zh.ua
         INTO vsUnidadAdm
         FROM zon_histo_geren zh
        WHERE zh.gere = psCodigoCliente
          AND vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
          AND (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast OR zh.perd_oid_peri_hast IS NULL);

        lsCodigoValidacion := '3__' || 'n;' || vsUnidadAdm || ';'|| vsCodigoPeriodoValAsiLid;
        RETURN lsCodigoValidacion;

     ELSE
       IF(vsCodigoPeriodoValAsiLid = psCodigoPeriodoActual) THEN
          vsOidPeriodoValAsiLid := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodoAsigLiderSgte);

          SELECT COUNT(1)
           INTO vnIndValSeccLid
           FROM zon_histo_geren zh
          WHERE zh.gere = psCodigoCliente
            AND vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
            AND (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast OR zh.perd_oid_peri_hast IS NULL);

          IF vnIndValSeccLid > 0 THEN

             SELECT zh.oid_hist_gere
               INTO vnOidHistGeren
               FROM zon_histo_geren zh
              WHERE zh.gere = psCodigoCliente
                AND vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
                AND (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast OR zh.perd_oid_peri_hast IS NULL);

             lsCodigoValidacion := '3__' || 'n2;' || vsUnidadAdm || ';'|| vnOidHistGeren;
             RETURN lsCodigoValidacion;
          END IF;

          SELECT COUNT(1) INTO vnIndValSeccLid
            FROM zon_histo_geren zh
           WHERE zh.ua = pscodsubgerencia || pscodregi || pscodzona || pscodsecc
             AND zh.perd_oid_peri_desd >= vsOidPeriodoValAsiLid;

          IF(vnIndValSeccLid > 0) THEN
            SELECT zh.oid_hist_gere
              INTO vnOidHistGeren
              FROM zon_histo_geren zh
             WHERE zh.ua = pscodsubgerencia || pscodregi || pscodzona || pscodsecc
               AND zh.perd_oid_peri_desd >= vsOidPeriodoValAsiLid;

            lsCodigoValidacion := '3__' || 'n2;' || vsUnidadAdm || ';'|| vnOidHistGeren;
            RETURN lsCodigoValidacion;

          END IF;

       END IF;

     END IF;

     -- Retornar valor ----------------------------------------------------------------------------
     RETURN lsCodigoValidacion;

   EXCEPTION

     -- (9) En caso de excepcion. También es una validacion ---------------------------------------
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM,1,150);
       RETURN '9__' || ls_sqlerrm;

   END ZON_FN_VALID_ASIGN_LIDER_SECCI;


  /******************************************************************************
  Descripcion       : Proceso que Modifica Territorio a Consultoras de forma masiva
  Fecha Modificacion: 21/08/2014
  Parametros:
        psCodigoPais     : Codigo de Pais
        psCampanaProceso : Campaña de Proceso
        psCodigoUsuario  : Codigo de Usuario
  Autor: Carlos Bazalar
  *******************************************************************************/
  PROCEDURE ZON_PR_ACTUA_UADMI (
    psCodigoPais       VARCHAR2,
    psCampanaProceso   VARCHAR2,
    psCodigoUsuario    VARCHAR2
  )
  IS
   lnIdPeriActual  NUMBER;
   lnIdPeriSgte    NUMBER;
   lnIdPais        NUMBER;
   lnIdCanal       NUMBER;
   lnIdMarca       NUMBER;
   lsCodPeriSgte   VARCHAR2(6);

  BEGIN
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
   lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
   lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
   lnIdPeriActual := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCampanaProceso, lnIdMarca, lnIdCanal);
   lsCodPeriSgte  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCampanaProceso, lnIdPais, lnIdMarca, lnIdCanal, 1);
   lnIdPeriSgte   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSgte, lnIdMarca, lnIdCanal);

   UPDATE MAE_CLIEN_UNIDA_ADMIN x
   SET ind_acti = 0
   WHERE x.perd_oid_peri_fin = lnIdPeriActual;

   UPDATE MAE_CLIEN_UNIDA_ADMIN x
   SET ind_acti = 1
   WHERE x.perd_oid_peri_ini = lnIdPeriSgte;

  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM,1,150);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_ACTUA_UADMI: '||ls_sqlerrm);
  END ZON_PR_ACTUA_UADMI;

	/***************************************************************************
    Descripcion       : Valida la carga unidades geograficas
    Fecha Creacion    : 13/01/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE ZON_PR_VALIDAR_TERRI_UNGEO(psCodigoUsuario VARCHAR2) IS

        CURSOR C_TERRITORIOS IS
        SELECT
        NUM_FILA,
        COD_TERR,
        COD_GEOG
        FROM ZON_TEMPO_TERRI_GEOPO
        WHERE COD_USUA = psCodigoUsuario;

        TYPE tipoTerritorios IS RECORD
        (
            numeroFila              ZON_TEMPO_TERRI_GEOPO.NUM_FILA%TYPE,
            codigoTerritorio        ZON_TEMPO_TERRI_GEOPO.COD_TERR%TYPE,
            codigoGeografia         ZON_TEMPO_TERRI_GEOPO.COD_GEOG%TYPE
        );

      TYPE tipoTerritoriosTab  IS TABLE OF tipoTerritorios;
      tipoTerritoriosRecordN tipoTerritoriosTab;

      lsMensajeError            ZON_TEMPO_TERRI_GEOPO.MEN_ERRO%TYPE;
      lnNumeroFila              ZON_TEMPO_TERRI_GEOPO.NUM_FILA%TYPE;
      lnTempo   NUMBER;
      lsOrde1   ZON_VALOR_ESTRU_GEOPO.ORDE_1%TYPE;
      lsOrde2   ZON_VALOR_ESTRU_GEOPO.ORDE_2%TYPE;
      lsOrde3   ZON_VALOR_ESTRU_GEOPO.ORDE_3%TYPE;

      lnOidTerritorio NUMBER;
      lnOidEG NUMBER;
      esNumerico BOOLEAN;

    BEGIN


      OPEN C_TERRITORIOS;
      LOOP
        FETCH C_TERRITORIOS BULK COLLECT INTO tipoTerritoriosRecordN LIMIT W_FILAS;
        IF tipoTerritoriosRecordN.COUNT > 0 THEN
          FOR x IN tipoTerritoriosRecordN.FIRST .. tipoTerritoriosRecordN.LAST LOOP

            lnNumeroFila   := tipoTerritoriosRecordN(x).numeroFila;
            lsMensajeError := '';
            esNumerico := TRUE;

            -- (1) Validar que la columna de  Geografia sea numérico y contenga 6 dígitos,
            BEGIN
                lnTempo := TO_NUMBER(tipoTerritoriosRecordN(x).codigoGeografia);
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError := lsMensajeError||'Código de geografía no es numérico, ';
                    esNumerico := FALSE;
            END;

            IF LENGTH(tipoTerritoriosRecordN(x).codigoGeografia) != 6 THEN
                lsMensajeError := lsMensajeError||'Código de geografía debe de tener 6 dígitos, ';
            END IF;
            -- --

            -- (2) Validar que el código de territorio sea numérico.
            BEGIN
                lnTempo := TO_NUMBER(tipoTerritoriosRecordN(x).codigoTerritorio);
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError := lsMensajeError||'Código de territorio no es numérico, ';
                    esNumerico := FALSE;
            END;
            -- --

            -- (3) Validar si el territorio esta registrado y activo
            IF esNumerico THEN
                BEGIN
                    SELECT OID_TERR
                    INTO lnOidTerritorio
                    FROM ZON_TERRI
                    WHERE COD_TERR = tipoTerritoriosRecordN(x).codigoTerritorio
                    AND IND_BORR = '0';
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        lsMensajeError := lsMensajeError||'Territorio no existe o no se encuentra activo, ';
                END;

                -- (4) Validar que el codigo de geografía corresponda al territorio
                BEGIN
                    SELECT EG.ORDE_1, EG.ORDE_2, EG.ORDE_3
                    INTO lsOrde1, lsOrde2, lsOrde3
                    FROM ZON_TERRI ZT, ZON_VALOR_ESTRU_GEOPO EG
                    WHERE ZT.VEPO_OID_VALO_ESTR_GEOP = EG.OID_VALO_ESTR_GEOP
                    AND ZT.COD_TERR = tipoTerritoriosRecordN(x).codigoTerritorio
                    AND ZT.IND_BORR = '0';
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        lsMensajeError := lsMensajeError||'Unidad Geográfica '|| tipoTerritoriosRecordN(x).codigoGeografia ||' no registrada, ';
                END;

                IF LENGTH(lsOrde1) > 0 THEN

                    BEGIN
                        SELECT OID_VALO_ESTR_GEOP
                        INTO lnOidEG
                    FROM ZON_VALOR_ESTRU_GEOPO
                    WHERE ORDE_1 = lsOrde1
                    AND ORDE_2 = lsOrde2
                    AND ORDE_3 = lsOrde3
                    AND ORDE_4 = tipoTerritoriosRecordN(x).codigoGeografia;
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            lsMensajeError := lsMensajeError||'Unidad Geográfica '|| lsOrde1 || lsOrde2 || lsOrde3 || tipoTerritoriosRecordN(x).codigoGeografia ||' no registrada, ';
                    END;

                    END IF;
                -- --

                END IF;
                -- --

            -- (5) Validar duplicado
            SELECT COUNT(*)
            INTO lnTempo
            FROM ZON_TEMPO_TERRI_GEOPO
            WHERE COD_TERR = tipoTerritoriosRecordN(x).codigoTerritorio
            AND COD_GEOG = tipoTerritoriosRecordN(x).codigoGeografia;

            IF lnTempo > 1 THEN
                lsMensajeError := lsMensajeError||'Existe más de un registro con los mísmos valores, ';
            END IF;
            -- --

            IF(length(lsMensajeError) > 0) THEN
               lsMensajeError := substr(lsMensajeError,1,length(lsMensajeError)-2);
               UPDATE ZON_TEMPO_TERRI_GEOPO
               SET EST_REGI = 0,
               MEN_ERRO = lsMensajeError
               WHERE COD_USUA = psCodigoUsuario
               AND NUM_FILA = tipoTerritoriosRecordN(x).numeroFila;
            ELSE
               UPDATE ZON_TEMPO_TERRI_GEOPO
               SET
               OID_TERR = lnOidTerritorio,
               OID_VEPO = lnOidEG,
               EST_REGI = '1'
               WHERE COD_USUA = psCodigoUsuario
               AND NUM_FILA = tipoTerritoriosRecordN(x).numeroFila;

            END IF;

          END LOOP;
        END IF;
      EXIT WHEN C_TERRITORIOS%NOTFOUND;
      END LOOP;
      CLOSE C_TERRITORIOS;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_VALIDAR_TERRI_UNGEO: '||ls_sqlerrm);
    END ZON_PR_VALIDAR_TERRI_UNGEO;

    /***************************************************************************
    Descripcion       : Realiza la carga unidades geograficas
    Fecha Creacion    : 13/01/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE ZON_PR_CARGAR_TERRI_UNGEO(psCodigoUsuario VARCHAR2) IS
    BEGIN

        DELETE ZON_TERRI_GEOPO;

        INSERT INTO ZON_TERRI_GEOPO(
            VEPO_OID_VEPO,
            TERR_OID_TERR,
            IND_ACTI,
            USU_CREA,
            FEC_CREA)
        SELECT
        OID_VEPO,
        OID_TERR,
        '1',
        psCodigoUsuario,
        SYSDATE
        FROM ZON_TEMPO_TERRI_GEOPO
        WHERE EST_REGI = '1'
        AND COD_USUA = psCodigoUsuario;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_CARGAR_TERRI_UNGEO: '||ls_sqlerrm);
    END ZON_PR_CARGAR_TERRI_UNGEO;
    
   /***************************************************************************
   Descripcion       : Valida que las Zonas no hayan cerrado
  Fecha Creacion    : 19/11/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE zon_pr_valid_zonas_cierr_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigozona   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2    
  ) IS
    lncant        NUMBER;
    lnoidperi    NUMBER;
  BEGIN
    
    SELECT cper.oid_peri
      INTO lnoidperi
      FROM cra_perio cper
     WHERE cper.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = pscodigopais)
       AND cper.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = pscodigomarca)
       AND cper.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = pscodigocanal)
       AND cper.peri_oid_peri =
           (SELECT oid_peri
              FROM seg_perio_corpo
             WHERE cod_peri = pscodigoperiodo
               AND tipe_oid_tipo_peri =
                   (SELECT oid_tipo_peri FROM seg_tipo_perio WHERE cod_tipo_peri = cte_tipo_peri_cm));
                   
                   
    SELECT COUNT(1)
      INTO lncant
      FROM zon_regio       reg,
           ZON_ZONA        zzon,
           fac_contr_cierr ccr
     WHERE reg.oid_regi = ccr.zorg_oid_regi
       AND zzon.oid_zona = ccr.zzon_oid_zona
       AND ccr.tcie_oid_tipo_cier = 1       
       AND zzon.cod_zona = pscodigozona
       AND ccr.perd_oid_peri =lnoidperi;
       
    IF lncant > 0 THEN
      pscodigoretorno := '1';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1,250);
      raise_application_error(-20123,'ERROR ZON_PR_VALID_ZONAS_CIERR_UNADM: ' || ls_sqlerrm);
  END zon_pr_valid_zonas_cierr_unadm;
  
  /***************************************************************************
  Descripcion       : Valida que las Zonas no hayan facturado
  Fecha Creacion    : 19/11/2015
  Autor             : Karina Valencia
  ***************************************************************************/
  PROCEDURE zon_pr_valid_zonas_factu_unadm
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigozona   VARCHAR2,
    pscodigoretorno  OUT VARCHAR2   
  ) IS
    lnfact    NUMBER;
    lnoidperi NUMBER;
  BEGIN

    SELECT cper.oid_peri
      INTO lnoidperi
      FROM cra_perio cper
     WHERE cper.pais_oid_pais = (SELECT oid_pais FROM seg_pais WHERE cod_pais = pscodigopais)
       AND cper.marc_oid_marc = (SELECT oid_marc FROM seg_marca WHERE cod_marc = pscodigomarca)
       AND cper.cana_oid_cana = (SELECT oid_cana FROM seg_canal WHERE cod_cana = pscodigocanal)
       AND cper.peri_oid_peri =
           (SELECT oid_peri
              FROM seg_perio_corpo
             WHERE cod_peri = pscodigoperiodo
               AND tipe_oid_tipo_peri =
                   (SELECT oid_tipo_peri FROM seg_tipo_perio WHERE cod_tipo_peri = cte_tipo_peri_cm));

    SELECT COUNT(1)
      INTO lnfact
      FROM ped_solic_cabec     cab,
           zon_zona            zon,
           zon_regio           reg,
           ped_tipo_solic_pais tslp,
           ped_tipo_solic      tsol
     WHERE cab.zzon_oid_zona = zon.oid_zona
       AND zon.zorg_oid_regi = reg.oid_regi
       AND zon.cod_zona = pscodigozona
       AND cab.perd_oid_peri = lnoidperi
       AND cab.ind_oc = 1
       AND cab.tspa_oid_tipo_soli_pais = tslp.oid_tipo_soli_pais
       AND tslp.tsol_oid_tipo_soli = tsol.oid_tipo_soli
       AND tsol.ind_anul = 0
       AND tsol.ind_devo = 0
       AND cab.fec_fact IS NOT NULL;

    IF lnfact > 0 THEN
      pscodigoretorno := '1';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,'ERROR ZON_PR_VALID_ZONAS_FACTU_UNADM: ' || ls_sqlerrm);
  END zon_pr_valid_zonas_factu_unadm;


END zon_pkg_unida_admin;
/
