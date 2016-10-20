CREATE OR REPLACE PACKAGE "MAE_PKG_PROCE_CLIEN" IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  /* Estatus de Consultora */
  ESTADO_REGISTRADA CONSTANT VARCHAR2(2) := '01';
  ESTADO_NUEVA CONSTANT VARCHAR2(2) := '02';
  ESTADO_NORMAL CONSTANT VARCHAR2(2) := '03';
  ESTADO_EGRESANTE CONSTANT VARCHAR2(2) := '04';
  ESTADO_EGRESADA CONSTANT VARCHAR2(2) := '05';
  ESTADO_REINGRESO CONSTANT VARCHAR2(2) := '06';
  ESTADO_RETIRADA CONSTANT VARCHAR2(2) := '07';
  ESTADO_REACTIVADA CONSTANT VARCHAR2(2) := '08';

  /* Carga Masiva Nivel Riesgo */
  NIVRIE_NUEVAS CONSTANT VARCHAR2(1) := 'N';
  NIVRIE_PRIMER_PEDIDO CONSTANT VARCHAR2(1) := '-';
  NIVRIE_SEGUNDO_PEDIDO CONSTANT VARCHAR2(1) := 'S';
  NIVRIE_TERCER_PEDIDO CONSTANT VARCHAR2(1) := 'T';
  NIVRIE_CUARTO_PEDIDO CONSTANT VARCHAR2(1) := 'C';

  NIVRIE_NUEVAS_ESPECIALES CONSTANT VARCHAR2(1) := 'V';
  NIVRIE_PRIMER_PEDIDO_ESPECIAL CONSTANT VARCHAR2(1) := '-';
  NIVRIE_SEGUNDO_PEDIDO_ESPECIAL CONSTANT VARCHAR2(1) := 'W';
  NIVRIE_TERCER_PEDIDO_ESPECIAL CONSTANT VARCHAR2(1) := 'X';
  NIVRIE_CUARTO_PEDIDO_ESPECIAL CONSTANT VARCHAR2(1) := 'Y';

/***************************************************************************
Descripcion : Proceso que carga de obtener las conbsultoras deudoras avaladas
Fecha Creacion : 19/10/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoConsultora : Consultora
 psNumeroDocumentoIdentidad : Numero Documento Identidad
 psTipo : tipo de registro 0: Referencia  1:Vinculo
 psMensajeError :mensaje de Error
***************************************************************************/
PROCEDURE MAE_PR_GENER_CLIEN_DAVAL
 (psCodigoPais       VARCHAR2,
  psCodigoConsultora VARCHAR2,
  psNumeroDocumentoIdentidad VARCHAR2,
  psTipo VARCHAR2,
  psMensajeError OUT VARCHAR2
 );

/**************************************************************************
Descripcion       : Generar Puntaje por Compras para el programa LOVE
Fecha Creacion    : 20/10/2009
Fecha Modificacion: 13/06/2013
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo    :  Oid Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_PERIO_INGRE
  (pnOidCliente               NUMBER,
   pnOidPeriodo               NUMBER,
   psCodigoUsuario            VARCHAR2);

/**************************************************************************
Descripcion       : Obtiebe el nivel de riesgo correspondiente al territorio
                    administrativo, por Seccion o el Default.
Fecha Creacion    : 03/03/2010
Parametros Entrada:
  pnOidTerritorioAdmin     :  Oid Territorio Administrativo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_OBTIE_NIVEL_RIESG
  (pnOidTerritorioAdmin       NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Redefine el periodo de Ingreso para las Retiradas
Fecha Creacion    : 07/04/2010
Fecha Modificacion: 13/06/2013
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo    :  Oid Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_PERIO_INGRE_RETIR
  (pnOidCliente               NUMBER,
   pnOidPeriodo               NUMBER,
   psCodigoUsuario            VARCHAR2);

/**************************************************************************
Descripcion       : Valida si el concurso de recomendacion matriculado en el
                    ingreso de Clientes esta vigente y si no es efectiva
                    1 - Valido,  0 -Invalido
Fecha Creacion    : 26/05/2010
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  Oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_VIGEN_RECOM
  (pnOidCliente         NUMBER,
   pnOidPeriodo         NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Inserta las correspondiente registros en las tablas de
                    incentivos para la recomendante y la recomendad
Fecha Creacion    : 26/05/2010
Parametros Entrada:
  pnOidPais            : Oid Pais
  pnCodClienteRcdo     :  Oid Cliente Recomendado
  pnCodClienteRcte     :  Oid Cliente Recomendante
  pnOidParaGral    :  Oid Concurso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REHAC_TABLA_INCEN
  (pnOidPais                   NUMBER,
   pnCodClienteRcdo            VARCHAR2,
   pnCodClienteRcte            VARCHAR2,
   pnOidParaGral               NUMBER);

/**************************************************************************
Descripcion       : Borra al registro en INC_CLIEN_RECDO y sus dependencias
Fecha Creacion    : 21/06/2010
Parametros Entrada:
  pnOidClienteRcdo     :  Oid Cliente Recomendado
  pnOidClienteRcte    :  Oid Cliente Recomendante

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_BORRA_CLIEN_RECDO
  (pnOidClienteRcdo           NUMBER,
   pnOidClienteRcte           NUMBER);

/**************************************************************************
Descripcion       : Borra al registro en INC_CLIEN_RECTE y sus dependencias
Fecha Creacion    : 21/06/2010
Parametros Entrada:

  pnOidClienteRcte    :  Oid Cliente Recomendante

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_BORRA_CLIEN_RECTE
  (pnOidClienteRcte           NUMBER);

/**************************************************************************
Descripcion       : Recuperamos el Oid Periodo de Recomendacion para la
                    Consultora
Fecha Creacion    : 22/07/2010
Parametros Entrada:
  pnOidPais        : Oid Pais
  pnOidCliente     :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_OBTEN_PERIO_RECOM
  (pnOidPais                   NUMBER,
   pnOidCliente                NUMBER)
RETURN NUMBER;


/**************************************************************************
Descripcion       : Valida si la zona y region hicieron sus cierres respectivos
                    S - Antes del cierre,  N -Despues del cierre
Fecha Creacion    : 20/12/2010
Parametros Entrada:
  pnOidPais        :     Oid Pais
  pnOidPeriodo     :     Oid Periodo
  pnOidRegion      :     Oid Region
  pnOidActividad   :     Oid Actividad
  pnFecCrea         :    Fecha de Creacion

Autor             : Christian Gonzales

***************************************************************************/
FUNCTION MAE_FN_VALID_CIERR_REGIO
  (pnOidPais        VARCHAR2,
   pnOidPeriodo     NUMBER,
   pnOidRegion      NUMBER,
   pnOidActividad   VARCHAR2,
   pnFecCrea        DATE)
RETURN CHAR;

/**************************************************************************
Descripcion       : Actualizacion Datos Clientes que tengan caracteres especiales
Fecha Creacion    : 24/07/2011
Parametros Entrada:

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_DATOS_CLIEN;

/**************************************************************************
Descripcion       : Ejecuta las validaciones de cliente
Fecha Creacion    : 11/08/2011
Parametros Entrada:
  psCodigoPais          :   Codigo Pais
  psCodigoPeriodo       :   Campanha Facturacion
  psFechaFacturacion    :   Fecha Facturacion

Autor             : Sergio Buchelli

***************************************************************************/
PROCEDURE MAE_PR_VALID_INFOR_CLIEN
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psFechaFacturacion        VARCHAR2,
   psUsuario                 VARCHAR2,
   psIndTipoValid            VARCHAR2,
   psIndTipoPrev             VARCHAR2,
   psEntorno                 VARCHAR2);

/**************************************************************************
Descripcion       : Ejecuta EL ENVIO VIA CORREO DE LAS VALIDACIONES
Fecha Creacion    : 11/08/2011
Parametros Entrada:
  psCodigoPais          :   Codigo Pais
  psCodigoPeriodo       :   Campanha Facturacion
  psFechaFacturacion    :   Fecha Facturacion

Autor             : Sergio Buchelli

***************************************************************************/
PROCEDURE MAE_PR_ENVIO_INFOR_CLIEN
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psFechaFacturacion        VARCHAR2,
   psUsuario                 VARCHAR2,
   psIndTipoValid            VARCHAR2,
   psIndTipoPrev             VARCHAR2,
   psEntorno                 VARCHAR2);

/**************************************************************************
Descripcion       : Actualiza Periodo de Contacto para el Caso de uso: Informacion
                    del Cliente
Fecha Creacion    : 10/10/2011
Parametros Entrada:

  pnOidCliente    :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_PRIME_CONTA
  (pnOidCliente           NUMBER);

/*********************************************************************************
Descripcion       : Actualiza Niveles de Riesgo del Cliente al Cierre de Campaña
Fecha Creacion    : 24/11/2011
Parametros Entrada:

  pnOidCliente    :

Autor             :
**********************************************************************************/
PROCEDURE MAE_PR_ACTUA_NIVEL_RIESG;

/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo MOD11V, aplicado al tipo de Documento RUT
Fecha Creacion    : 12/06/2012
Parametros Entrada:
  psNumeroDocumento        :     Numero Documento

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_NUMER_DOCUM_MOD11
  (psNumeroDocumento        VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Obtiene la ultima secuencia para que sea utilizada
                     en la generacion del codigo de Cliente
Fecha Creacion     : 14/06/2012
Parametros Entrada:
  psCodigoPais        :    Codigo de Pais

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTEN_SECUE_CODIG_CLIEN
  (psCodigoPais       VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Verifica si el cliente ha ingresado Direccion de Vacaciones
                    y se validara si se le agrega clasificacion de Vacaciones
Fecha Creacion    : 16/07/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_DIREC_VACAC
  (psCodigoPais         VARCHAR2,
   pnOidCliente         NUMBER,
   psCodigoUsuario      VARCHAR2);

/**************************************************************************
Descripcion       : Verifica si el cliente ha ingresado Direccion de Vacaciones
                    y se eliminar la clasificacion de Vacaciones
Fecha Creacion    : 16/07/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ELIMI_CLASI_DIREC_VACAC
  (psCodigoPais         VARCHAR2,
   pnOidCliente         NUMBER);

/**************************************************************************
Descripcion        : Obtiene el codigo de Periodo en base al Oid Periodo
Fecha Creacion     : 18/07/2012
Parametros Entrada :
           pnOidPeriodo : Oid Periodo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTIE_CODIG_PERIO(pnOidPeriodo  NUMBER) RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Activar Entrega en Dirección de Vacaciones
Fecha Creacion    : 18/07/2012
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTIV_CLASI_DIREC_VACAC
  (psCodigoPais          VARCHAR2,
   psCodigoMarca         VARCHAR2,
   psCodigoCanal         VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2);

/**************************************************************************
Descripcion       : Desactivar Entrega en Dirección de Vacaciones
Fecha Creacion    : 18/07/2012
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_DESAC_CLASI_DIREC_VACAC
  (psCodigoPais          VARCHAR2,
   psCodigoMarca         VARCHAR2,
   psCodigoCanal         VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2);

/**************************************************************************
Descripcion       : Valida si se puede Bloquear/Desbloquer el cliente
Fecha Creacion    : 17/08/2012
Parametros Entrada:
  psCodigoCliente       :     Codigo Cliente
  psAccionBloqueo       :     Accion Bloqueo: B-Bloquear, D-Desbloquear
  psTipoBloqueo         :     Tipo Bloqueo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_BLOQU_DESBL_CLIEN
  (psCodigoCliente        VARCHAR2,
   psAccionBloqueo        VARCHAR2,
   psTipoBloqueo          VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion        : Obtiene la unidad Administrativa completa:
                     Region + Zona + Seccion + Territorio
Fecha Creacion     : 06/09/2012
Parametros Entrada:
  psCodigoZona       :     Codigo Zona
  psCodigoTerritorio :     Codigo Territorio

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTEN_CODUA_SOLIC_CREDI
  (psCodigoZona         VARCHAR2,
   psCodigoTerritorio   VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Generar Solicitud de Credito Rechazada para los Clientes
                    Inactivos que estan queriendo pasarse a Activo
Fecha Creacion    : 06/09/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  psCodigoCliente :  Codigo Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_GENER_SOLIC_CREDI_RECHA
  (psCodigoPais         VARCHAR2,
   psCodigoCliente      VARCHAR2,
   psCodigoUsuario      VARCHAR2);

/****************************************************************************
Descripcion       : Generar Bloqueo por Reingreso No Autorizado
Fecha Creacion    : 11/12/2012
Fecha Modificacion: 11/12/2012
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_GENER_BLOQU_REING_NOAUT(
  psCodigoPais      VARCHAR2,
  psCodigoRegion    VARCHAR2,
  pscodigomarca     VARCHAR2,
  pscodigocanal     VARCHAR2,
  pscodigoperiodo   VARCHAR2,
  psCodigoUsuario   VARCHAR2
  );

/****************************************************************************
Descripcion       : Desbloquear Reingreso No Autorizado
Fecha Creacion    : 18/12/2012
Fecha Modificacion: 18/12/2012
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_DESBL_REING_NOAUT(
  psCodigoPais      VARCHAR2,
  psCodigoRegion    VARCHAR2,
  pscodigomarca     VARCHAR2,
  pscodigocanal     VARCHAR2,
  pscodigoperiodo   VARCHAR2,
  psCodigoUsuario   VARCHAR2
  );

/****************************************************************************
Descripcion       : Obtiene  Periodo de Último Reingreso
Fecha Creacion    : 17/12/2012
Fecha Modificacion: 17/12/2012
Parametros:
        pnOidCliente: Oid Cliente

Autor: CSVD - FFVV
*****************************************************************************/
FUNCTION MAE_FN_OBTIE_PERIO_REING(pnOidCliente  NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion : Proceso que desbloquea a los clientes que tiene pedidos
Fecha Creacion : 17/01/2013
Autor : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Campaña
 psUsuario: Usuario de ejecucion
***************************************************************************/
PROCEDURE MAE_PR_GENER_DESBL_CLIEN_PEDID
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo VARCHAR2,
  psUsuario VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que actualzia datos de consultora enviado deesde portal
Fecha Creacion : 14/02/2012
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoConsultora : Consultora
 psMensajeError :mensaje de Error
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLIEN_PORTA
 (psCodigoPais       VARCHAR2,
  psCodigoConsultora VARCHAR2,
  psMail VARCHAR2,
  psTelefonoFijo VARCHAR2,
  psTelefonoCelular  VARCHAR2,
  psMensajeError OUT VARCHAR2
 );

/**************************************************************************
Descripcion       : Ejecuta el Proceso de Asignación de Estatus
Fecha Creacion    : 22/05/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_PROCE_ASIGN_ESTAT(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psCodigoRegion        VARCHAR2,
                                   psCodigoUsuario       VARCHAR2);

/**************************************************************************
Descripcion       : Ejecuta el Proceso de Asignación de Estatus
Fecha Creacion    : 22/05/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_PROCE_REEVA_ESTAT_CONSU(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psCodigoUsuario       VARCHAR2);

/**************************************************************************
Descripcion       : Actualizamos al Estatus Anterior de la Consultora
Fecha Creacion    : 16/07/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  pnOidEstatus :  oid Estatus
  pnOidPeriodo :  oid Periodo Actual
  pnOidPeriodoAnt :  oid Periodo Anterior
  pnOidEstatusReev :  oid Estatus Reevaluado

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_ESTAT_REEVA(psCodigoPais         VARCHAR2,
                                   pnOidCliente         NUMBER,
                                   pnOidEstatus         NUMBER,
                                   pnOidPeriodo         NUMBER,
                                   pnOidPeriodoAnt      NUMBER,
                                   pnOidEstatusReev OUT NUMBER);

/**************************************************************************
Descripcion        : Obtiene Siguiente Estado
Fecha Creacion     : 16/07/2013
Parametros Entrada:
  psCodigoZona       :     Codigo Zona
  psCodigoTerritorio :     Codigo Territorio

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTIE_ESTAD_SIGUI(psCodigoPais           VARCHAR2,
                                  pnOidEstatus           NUMBER,
                                  pnNumPeriodoSinPedido  NUMBER) RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Permite generar el histórico de cambios de datos
Fecha Creacion    : 13/08/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  psCodigoCliente    :  Codigo Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_INSER_HISTO_DATOS(psCodigoPais           VARCHAR2,
                                   psCodigoCliente        VARCHAR2,
                                   psPrimerApellidoAnt    VARCHAR2,
                                   psSegundoApellidoAnt   VARCHAR2,
                                   psPrimerNombreAnt      VARCHAR2,
                                   psSegundoNombreAnt     VARCHAR2,
                                   psFechaNacimientoAnt   VARCHAR2,
                                   psNumeroDocumentoAnt   VARCHAR2,
                                   psTelefonoCelularAnt   VARCHAR2,
                                   psTelefonoFijoAnt      VARCHAR2,
                                   psEmailAnt             VARCHAR2,
                                   psUbigeoAnt            VARCHAR2,
                                   psTipoViaAnt           VARCHAR2,
                                   psNumeroPrincipalAnt   VARCHAR2,
                                   psBarrioAnt            VARCHAR2,
                                   psDireccionAnt         VARCHAR2,
                                   psReferenciaAnt        VARCHAR2,
                                   psRegionAnt            VARCHAR2,
                                   psZonaAnt              VARCHAR2,
                                   psSeccionAnt           VARCHAR2,
                                   psTerritorioAnt        VARCHAR2,
                                   psPrimerApellido       VARCHAR2,
                                   psSegundoApellido      VARCHAR2,
                                   psPrimerNombre         VARCHAR2,
                                   psSegundoNombre        VARCHAR2,
                                   psFechaNacimiento      VARCHAR2,
                                   psNumeroDocumento      VARCHAR2,
                                   psTelefonoCelular      VARCHAR2,
                                   psTelefonoFijo         VARCHAR2,
                                   psEmail                VARCHAR2,
                                   psUbigeo               VARCHAR2,
                                   psTipoVia              VARCHAR2,
                                   psNumeroPrincipal      VARCHAR2,
                                   psBarrio               VARCHAR2,
                                   psDireccion            VARCHAR2,
                                   psReferencia           VARCHAR2,
                                   psRegion               VARCHAR2,
                                   psZona                 VARCHAR2,
                                   psSeccion              VARCHAR2,
                                   psTerritorio           VARCHAR2,
                                   psIndicadorOrigen      VARCHAR2,
                                   psCodigoUsuario        VARCHAR2);

/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo MOD11V, aplicado al tipo de Documento RUC
Fecha Creacion    : 14/08/2013
Parametros Entrada:
           psNumeroDocumento:  Numero Documento (RUC)

Autor             : Gonzalo Javier Huertas Agurto

***************************************************************************/
FUNCTION MAE_FN_VALID_NUMER_RUC_MOD11
  (psNumeroDocumento        VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Valida Canert de Identidad
Fecha Creacion    : 20/05/2015
Parametros Entrada:
           psNumeroDocumento:  Numero Documento

Autor             : Diego Torres Loyola

***************************************************************************/
FUNCTION MAE_FN_VALID_CARNE_IDENT
  (psNumeroDocumento        VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Actualizamos Datos de la Ejecutiva
Fecha Creacion    : 05/09/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ELIMI_DATOS_EJECU(psCodigoPais         VARCHAR2,
                                   pnOidCliente         NUMBER);

/****************************************************************************
Descripcion       : Revierte el estatus de la consultora por anulacion de pedido
Fecha Creacion    : 22/11/2013
Fecha Modificacion: 22/11/2013
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_GENER_REVER_ESTAT(
  psCodigoPais     VARCHAR2,
  psCodigoMarca    VARCHAR2,
  psCodigoCanal    VARCHAR2,
  psCodigoCampana  VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psCodigoRegion   VARCHAR2,
  psCodigoUsuario  VARCHAR2
  );
/**************************************************************************
Descripcion       : Se retorna al Estatus Anterior de la Consultora
Fecha Creacion    : 27/11/2013
Parametros Entrada:
    psCodigoPais    :
    pnOidCliente    :
    pnOidPeriodo    :
    psCodigoCampana :
    pnOidPeriodoAnt :
    psCodigoUsuario :
Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_REVER_ESTAT_CLIEN (
    psCodigoPais         VARCHAR2,
    pnOidCliente         NUMBER,
    pnOidPeriodo         NUMBER,
    psCodigoCampana      VARCHAR2,
    pnOidPeriodoAnt      NUMBER,
    psCodigoUsuario      VARCHAR2
);


/**************************************************************************
Descripcion       : Devuelve modulo 10 de un valor
Fecha Creacion    : 28/11/2013
Parametros Entrada:
    psCodigoPais    :
    pnOidCliente    :
    pnOidPeriodo    :
    psCodigoCampana :
    pnOidPeriodoAnt :
    psCodigoUsuario :
Autor             : CSVD - FFVV
***************************************************************************/
FUNCTION MAE_FN_DEVUE_MODUL_DIEZ(pscodigo VARCHAR2) RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Proceso de Calculo de Estatus por Consultora
Fecha Creacion    : 18/12/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoCliente  : Codigo Cliente
  psCodigoUsuario  :  Codigo de Usuario

Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_PROCE_CALCU_ESTAT(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psCodigoRegion        VARCHAR2,
                                   psCodigoCliente       VARCHAR2,
                                   psCodigoUsuario       VARCHAR2
                                   ) ;

/**************************************************************************
Descripcion       : Devuelve Descripcion de Bloqueo del cliente
Fecha Creacion    : 22/01/2014
Parametros Entrada:
  psCodigoCliente       :     Codigo Cliente
  psAccionBloqueo       :     Accion Bloqueo: B-Bloquear, D-Desbloquear
  psTipoBloqueo         :     Tipo Bloqueo

Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION MAE_FN_DEVUE_BLOQU_DESBL_CLIEN
  (psCodigoCliente        VARCHAR2,
   psAccionBloqueo        VARCHAR2,
   psTipoBloqueo          VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
Devuelve Periodo MAYOR si hay cruce de campa?a
Fecha Creacion : 23/01/2014
Autor : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_DEVUE_PERIO_MAYOR_FECHA
( pscodpais  VARCHAR2,
  pscodmarca VARCHAR2,
  pscodcanal VARCHAR2,
  pdfecha    DATE
) RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Redifine vigencia de la unidad administrativa actual y
                    anterior
Fecha Creacion    : 20/02/2014
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  psCodigoPeriodo    :  Codigo Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_VIGEN_UNIDA_ADMIN
  (psCodigoPais               VARCHAR2,
   pnOidCliente               NUMBER,
   psCodigoPeriodo            VARCHAR2,
   psCodigoUsuario            VARCHAR2);

/***************************************************************************
    Descripcion           : Valida la Regularizacion de Bloqueos - Desbloqueos de Clientes
    Fecha Creacion    : 26/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
FUNCTION MAE_FN_VALID_REGUL_CLIEN
( psCodigoCliente                   VARCHAR2,
  psCodigoUsuario                  VARCHAR2
) RETURN NUMBER;

/***************************************************************************
    Descripcion           : Actualiza la Regularizacion de Bloqueos - Desbloqueos de Clientes
    Fecha Creacion    : 26/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_REGUL_CLIEN
  (psCodigoCliente                   VARCHAR2,
   pnOidBloqueo                       NUMBER,
   psCodigoRetorno                  OUT VARCHAR2 );

/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo PTR
Fecha Creacion    : 04/04/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_MODUL_PTR
  (psCodigo          VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Inactivar conultoras registradas despues de dos campañas
                    de creadas en el maestro
Fecha Creacion    : 14/05/2014
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE MAE_PR_INACT_CLIEN_SINPE
  (psCodigoPais          VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Activa, actualiza o retira las clasificaciones disponibles
                      en el programa Flexipago
  Fecha Creacion    : 10/06/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Gonzalo Huertas

  ***************************************************************************/
  PROCEDURE MAE_PR_ACTUA_CLASI_FLEXI
    (psCodigoPais               VARCHAR2,
     psCodigoMarca              VARCHAR2,
     psCodigoCanal              VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2);

/***************************************************************************
Descripcion       : Valida Carga de Masiva Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER);

/***************************************************************************
Descripcion       : Inserta Carga Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_INSER_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psOidTipoCliente           VARCHAR2,
   psOidSubTipoCliente        VARCHAR2,
   psOidTipoClasificacion     VARCHAR2,
   psOidClasificacion         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psCodigoUsuario            VARCHAR2,
   psNumeroRegistros          OUT VARCHAR2);

/***************************************************************************
Descripcion       : Actualiza Carga Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psIndicadorPaqDoc          VARCHAR2,
   psCodigoUsuario            VARCHAR2);

/***************************************************************************
Descripcion       : Valida Carga de Masiva Nivel de Riesgo
Fecha Creacion    : 28/08/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_NIVEL_RIESG
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER);

/***************************************************************************
Descripcion       : Actualiza Carga Nivel Riesgo
Fecha Creacion    : 28/08/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_NIVEL_RIESG
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psCodigoUsuario            VARCHAR2);


/***************************************************************************
Descripcion       : Actualiza e Inserta Dirección Entrega
Fecha Creacion    : 20/11/2014
Autor             : Karina Gomez
***************************************************************************/
PROCEDURE MAE_PR_INSER_DIREC_ENT
   (psUsuario                 VARCHAR2,
    psCliente                 VARCHAR2,
    psDireccion               VARCHAR2,
    psReferencia              VARCHAR2,
    psTipoDireccion           VARCHAR2);

/***************************************************************************
Descripcion       : Inserta Clasificacion
Fecha Creacion    : 01/12/2014
Autor             : Karina Gomez
***************************************************************************/
PROCEDURE MAE_PR_INSER_CLAS
  (psTipoClasif               VARCHAR2,
   psCodClas                  VARCHAR2,
   psDescripcion              VARCHAR2,
   psindicador                VARCHAR2,
   psindHiper                 VARCHAR2,
   psindIVR                   VARCHAR2
   );


PROCEDURE MAE_PR_INSER_TIPO_CLAS
  (psSubTipo                  VARCHAR2,
   psTipoClas                 VARCHAR2,
   psPais                     VARCHAR2,
   psDescripcion              VARCHAR2,
   psindicador                VARCHAR2,
   psindHiper                 VARCHAR2);

/***************************************************************************
Descripcion       : Permite actualizar información Básica al cierre de campaña.
Fecha Creacion    : 09/12/2014
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_INFOR_BASIC(
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2);

/***************************************************************************
Descripcion       : Permite actualizar información Básica Por cada uno de los tipos
                    al cierre de campaña.
Fecha Creacion    : 09/12/2014
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_INFOR_BASIC_TIPO(
     psCodigoPeriodo            VARCHAR2,
     psCodigoTipo               VARCHAR2,
     pnValor                    NUMBER,
     psCodigoUsuario            VARCHAR2);

/***************************************************************************
Descripcion       : Bloquea masivamente a todas las consultoras que han
                    facturado su primer pedido con el tipo de bloqueo
                    ¿Documentos de Contrato en Verificacion¿.
Fecha Creacion    : 21/04/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE MAE_PR_BLOQU_CONSU_PRIPE(
     psCodigoPais               VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2,
     psFechaFacturacion         VARCHAR2);

/**************************************************************************
Descripcion       : Validar tipo documento NIT
Fecha Creacion    : 27/05/2015
Parametros Entrada:
  psNumDoc        :     Número Documento
Autor             : Juan Gutiérrez
***************************************************************************/
FUNCTION MAE_FN_VALID_TDOC_NIT
  (psNumDoc        VARCHAR2
  )
  RETURN NUMBER;

END MAE_PKG_PROCE_CLIEN;
/
CREATE OR REPLACE PACKAGE BODY "MAE_PKG_PROCE_CLIEN" IS

/***************************************************************************
Descripcion : Proceso que carga de obtener las conbsultoras deudoras avaladas
Fecha Creacion : 19/10/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoConsultora :Codigo Consultora
 psNumeroDocumentoIdentidad : Numero Documento Identidad
 psTipo : tipo de registro 0: Referencia  1:Vinculo

***************************************************************************/
PROCEDURE MAE_PR_GENER_CLIEN_DAVAL
 (psCodigoPais VARCHAR2,
  psCodigoConsultora VARCHAR2,
  psNumeroDocumentoIdentidad VARCHAR2,
  psTipo VARCHAR2,
  psMensajeError OUT VARCHAR2
 )
IS
--se obtiene las avaladas Referencia
  CURSOR c_avaladas IS
        SELECT mc.OID_CLIE,
               mc.COD_CLIE,
               mc.VAL_APE1,
               mc.VAL_APE2,
               mc.VAL_NOM1,
               mc.VAL_NOM2
        FROM
         mae_refer rf,
         mae_clien mc,
         mae_tipo_docum td
        WHERE rf.TIPO_REFE = 3
         AND rf.NUM_DOCU_REFE  = psNumeroDocumentoIdentidad
         AND mc.COD_CLIE = rf.COD_CLIE
         AND rf.TIPO_DOCU_REFE = td.COD_TIPO_DOCU;

--se obtiene las avaladas Vinculo
CURSOR c_avaladas_Vinculo(lnOidConsultora NUMBER) IS
        SELECT  mc.OID_CLIE,
                mc.COD_CLIE,
                mc.VAL_APE1,
                mc.VAL_APE2,
                mc.VAL_NOM1,
                mc.VAL_NOM2
        FROM
            mae_clien_vincu tv,
            mae_clien mc,
            mae_tipo_vincu tt
        WHERE tt.cod_tipo_vinc = '05'  --Consultora/Aval
        AND tv.CLIE_OID_CLIE_VNDO = mc.OID_CLIE
        AND tv.TIVC_OID_TIPO_VINC = tt.OID_TIPO_VINC
        AND tv.CLIE_OID_CLIE_VNTE =lnOidConsultora
      UNION
        SELECT
               mc.OID_CLIE,
               mc.COD_CLIE,
               mc.VAL_APE1,
               mc.VAL_APE2,
               mc.VAL_NOM1,
               mc.VAL_NOM2
        FROM
         mae_refer rf,
         mae_clien mc,
         mae_tipo_docum td
        WHERE rf.TIPO_REFE = 3
         AND rf.NUM_DOCU_REFE  = psNumeroDocumentoIdentidad
         AND mc.COD_CLIE = rf.COD_CLIE
         AND rf.TIPO_DOCU_REFE = td.COD_TIPO_DOCU;

   TYPE cursorAvaladasRecord IS RECORD
   (
      oidConsultora             MAE_CLIEN.OID_CLIE%TYPE,
      codConsultora             MAE_CLIEN.COD_CLIE%TYPE,
      apellidoPaterno             MAE_CLIEN.VAL_APE1%TYPE,
      apellidoMaterno            MAE_CLIEN.VAL_APE2%TYPE,
      nombre1                     MAE_CLIEN.VAL_NOM1%TYPE,
      nombre2                     MAE_CLIEN.VAL_NOM2%TYPE
   );

   TYPE cursorAvaladas  IS TABLE OF cursorAvaladasRecord ;
   deudorasAvaladasRecord cursorAvaladas;

    lnOidCliente         MAE_CLIEN.OID_CLIE%TYPE;
    lnSaldoVencido      NUMBER;
    lnSumImpPend        NUMBER;
    lnMonto             NUMBER;
    lnDiaVcto           NUMBER;
    lbExistenDeudora     BOOLEAN:=FALSE;
BEGIN

           --borramos temporal
        delete FROM MAE_GTT_CONSU_DAVAL;
        --Obtenemos Monto de Deuda Vencida y Dias de Vencimiento parametrizadas
       BEGIN
        SELECT A.MON_DEUD_VCDA, A.DIA_DEUD_VCDA INTO lnMonto,lnDiaVcto
        FROM MAE_CLIEN_DAVAL_CONSU A
        WHERE A.COD_PAIS = psCodigoPais;
       EXCEPTION
        WHEN OTHERS THEN
          psMensajeError:='No existe Configuración Para el País, en la Entidad Deudoras Avaladas';
          return;
       END;

    IF(psTipo='0') THEN
        OPEN c_avaladas;
            LOOP
               FETCH c_avaladas BULK COLLECT INTO deudorasAvaladasRecord LIMIT W_FILAS;

               IF deudorasAvaladasRecord.COUNT > 0 THEN

                  FOR x IN deudorasAvaladasRecord.FIRST .. deudorasAvaladasRecord.LAST LOOP
                    lbExistenDeudora:=false;
                    lnOidCliente:=deudorasAvaladasRecord(x).oidConsultora;
                    lnSaldoVencido :=CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI(lnOidCliente);

                    --OBTENIENDO  DIAS DE VENCIMIENTO

                    SELECT NVL(SUM(mcc.imp_pend),0)  INTO lnSumImpPend
                    FROM ccc_movim_cuent_corri mcc
                    WHERE mcc.clie_oid_clie=lnOidCliente
                        AND TRUNC(SYSDATE) - mcc.fec_venc >= lnDiaVcto;

                    IF(lnSaldoVencido >= lnMonto OR lnSumImpPend > 0) THEN
                      --insertamos en el tempotal de cosultras avaladas deudoras
                                INSERT INTO MAE_GTT_CONSU_DAVAL (
                                    COD_PAIS, COD_CLIE, VAL_APE1,
                                    VAL_APE2, VAL_NOM1, VAL_NOM2, IMP_SALD_VENC, IMP_PEND)
                                 VALUES ( psCodigoPais,
                                          deudorasAvaladasRecord(x).codConsultora,
                                          deudorasAvaladasRecord(x).apellidoPaterno,
                                          deudorasAvaladasRecord(x).apellidoMaterno,
                                          deudorasAvaladasRecord(x).nombre1,
                                          deudorasAvaladasRecord(x).nombre2,
                                          lnSaldoVencido, lnSumImpPend);
                                 lbExistenDeudora:=true;

                    END IF;

                  END LOOP;
               END IF;
               EXIT WHEN c_avaladas%NOTFOUND;
            END LOOP;
         CLOSE c_avaladas;
     END IF;


   IF(psTipo='1') THEN
      --oBTENEMOS OIDConsultora
        lnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoConsultora);
        OPEN c_avaladas_Vinculo(lnOidCliente);
            LOOP
               FETCH c_avaladas_Vinculo BULK COLLECT INTO deudorasAvaladasRecord LIMIT W_FILAS;

               IF deudorasAvaladasRecord.COUNT > 0 THEN

                  FOR x IN deudorasAvaladasRecord.FIRST .. deudorasAvaladasRecord.LAST LOOP
                    lbExistenDeudora:=false;
                    lnOidCliente:=deudorasAvaladasRecord(x).oidConsultora;
                    lnSaldoVencido :=CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI(lnOidCliente);

                    --OBTENIENDO  DIAS DE VENCIMIENTO

                    SELECT NVL(SUM(mcc.imp_pend),0)  INTO lnSumImpPend
                    FROM ccc_movim_cuent_corri mcc
                    WHERE mcc.clie_oid_clie=lnOidCliente
                        AND TRUNC(SYSDATE) - mcc.fec_venc >= lnDiaVcto;

                    IF(lnSaldoVencido >= lnMonto OR lnSumImpPend > 0) THEN
                      --insertamos en el tempotal de cosultras avaladas deudoras
                           begin
                                INSERT INTO MAE_GTT_CONSU_DAVAL (
                                    COD_PAIS, COD_CLIE, VAL_APE1,
                                    VAL_APE2, VAL_NOM1, VAL_NOM2, IMP_SALD_VENC, IMP_PEND)
                                 VALUES ( psCodigoPais,
                                          deudorasAvaladasRecord(x).codConsultora,
                                          deudorasAvaladasRecord(x).apellidoPaterno,
                                          deudorasAvaladasRecord(x).apellidoMaterno,
                                          deudorasAvaladasRecord(x).nombre1,
                                          deudorasAvaladasRecord(x).nombre2,
                                          lnSaldoVencido, lnSumImpPend);
                                  lbExistenDeudora:=true;
                            exception
                             when others THEN
                               lbExistenDeudora:=true;
                            END;

                    END IF;

                  END LOOP;
               END IF;
               EXIT WHEN c_avaladas_Vinculo%NOTFOUND;
            END LOOP;
         CLOSE c_avaladas_Vinculo;
     END IF;

 IF(lbExistenDeudora) THEN
    psMensajeError:='Existen consultoras Deudoras, revisar Popup';
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 psMensajeError := substr(sqlerrm,1,100);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_CLIEN_DAVAL: '||ls_sqlerrm);
END MAE_PR_GENER_CLIEN_DAVAL;


/**************************************************************************
Descripcion       : Generar Puntaje por Compras para el programa LOVE
Fecha Creacion    : 20/10/2009
Fecha Modificacion: 13/06/2013
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo    :  Oid Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_PERIO_INGRE
  (pnOidCliente               NUMBER,
   pnOidPeriodo               NUMBER,
   psCodigoUsuario            VARCHAR2)
IS
  lnTotalConsultora       NUMBER;
  lnOidClienteVnte        MAE_CLIEN.OID_CLIE%TYPE;
  lnOidSubTipo            MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
  lnTotal                 NUMBER;

BEGIN

  SELECT COUNT(1)
    INTO lnTotalConsultora
    FROM MAE_CLIEN_TIPO_SUBTI sub, MAE_TIPO_CLIEN tip
   WHERE sub.ticl_oid_tipo_clie = tip.oid_tipo_clie
     AND tip.cod_tipo_clie = '02'
     AND sub.Clie_Oid_Clie = pnOidCliente;

  --Si es consultora, se actualiza las entidades de Datos Adicionales e Historico de Estatus
  IF(lnTotalConsultora >= 0) THEN
    UPDATE MAE_CLIEN_DATOS_ADICI
      SET PERD_OID_PERI_NIVE_RIES = pnOidPeriodo,
          PERD_OID_PERI_LINE_CRED = pnOidPeriodo,
          USU_MODI = psCodigoUsuario
    WHERE CLIE_OID_CLIE = pnOidCliente;

    UPDATE MAE_CLIEN_HISTO_ESTAT m
       SET m.PERD_OID_PERI = pnOidPeriodo,
           m.USU_MODI = psCodigoUsuario
     WHERE m.CLIE_OID_CLIE = pnOidCliente
       AND m.oid_hist_esta =
           (SELECT MAX(m1.oid_hist_esta)
              FROM MAE_CLIEN_HISTO_ESTAT m1
             WHERE m1.clie_oid_clie = pnOidCliente);
  END IF;

  --Obtenemos el tipoSubtipo principal del cliente
  SELECT OID_CLIE_TIPO_SUBT
    INTO lnOidSubTipo
    FROM MAE_CLIEN_TIPO_SUBTI
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND IND_PPAL = 1;

  --Actualizamos la Entidad Primer Contacto
  UPDATE MAE_CLIEN_PRIME_CONTA
     SET PERD_OID_PERI = pnOidPeriodo,
         CTSU_CLIE_CONT = lnOidSubTipo,
         USU_MODI = psCodigoUsuario
   WHERE CLIE_OID_CLIE = pnOidCliente;

  --Actualizamos la Entidad Unidad Administrativa
  UPDATE MAE_CLIEN_UNIDA_ADMIN
     SET PERD_OID_PERI_INI = pnOidPeriodo,
         USU_MODI = psCodigoUsuario
   WHERE CLIE_OID_CLIE = pnOidCliente
   AND IND_ACTI=1;

  --Actualizamos la Entidad Clasificaciones
  UPDATE MAE_CLIEN_CLASI
     SET PERD_OID_PERI = pnOidPeriodo,
         USU_MODI = psCodigoUsuario
   WHERE CTSU_OID_CLIE_TIPO_SUBT IN
          (SELECT OID_CLIE_TIPO_SUBT FROM MAE_CLIEN_TIPO_SUBTI WHERE CLIE_OID_CLIE = pnOidCliente);

  --Recuperamos el Oid Cliente Recomendante
  BEGIN
    SELECT vin.clie_oid_clie_vnte
      INTO lnOidClienteVnte
      FROM MAE_CLIEN_VINCU vin, MAE_TIPO_VINCU tip
     WHERE vin.clie_oid_clie_vndo = pnOidCliente
       AND vin.tivc_oid_tipo_vinc = tip.oid_tipo_vinc
       AND tip.cod_tipo_vinc = '03';
  EXCEPTION
    WHEN OTHERS THEN
      lnOidClienteVnte := NULL;
  END;

  IF(lnOidClienteVnte IS NOT NULL) THEN
    FOR x IN (SELECT rte.OID_CLIE_RETE
  	            FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
            	  WHERE rdo.CLIE_OID_CLIE = pnOidCliente
            		  AND rte.OID_CLIE_RETE = rdo.CLR3_OID_CLIE_RETE
             		  AND rte.CLIE_OID_CLIE = lnOidClienteVnte) LOOP

      DELETE INC_CLIEN_RECDO rdo
       WHERE rdo.clr3_oid_clie_rete = x.oid_clie_rete
         AND rdo.clie_oid_clie = pnOidCliente;

      SELECT COUNT(rdo.OID_CLIE_REDO)
        INTO lnTotal
 		    FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
		   WHERE rte.OID_CLIE_RETE = rdo.CLR3_OID_CLIE_RETE
   			 AND rte.OID_CLIE_RETE = x.oid_clie_rete;

      IF(lnTotal = 0) THEN

        SELECT COUNT(pcr.oid_pedi_conc_reco)
          INTO lnTotal
          FROM INC_PEDID_CONCU_RECOM pcr, INC_CLIEN_RECTE rte
         WHERE rte.oid_clie_rete = pcr.clr3_oid_clie_rete
           AND rte.OID_CLIE_RETE = x.oid_clie_rete;

         IF (lnTotal = 0) THEN
        DELETE FROM INC_CLIEN_RECTE
           WHERE OID_CLIE_RETE = x.oid_clie_rete;
         END IF;
      END IF;

    END LOOP;

  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REDEF_PERIO_INGRE: ' || ls_sqlerrm);

END MAE_PR_REDEF_PERIO_INGRE;


/**************************************************************************
Descripcion       : Obtiebe el nivel de riesgo correspondiente al territorio
                    administrativo, por Seccion o el Default.
Fecha Creacion    : 03/03/2010
Parametros Entrada:
  pnOidTerritorioAdmin     :  Oid Territorio Administrativo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_OBTIE_NIVEL_RIESG
  (pnOidTerritorioAdmin       NUMBER)
RETURN NUMBER
IS
  lnOidSeccion                ZON_SECCI.OID_SECC%TYPE;
  lnOidNivelRiesgo            CAR_NIVEL_RIESG.OID_NIVE_RIES%TYPE;
BEGIN

  --Se ubica la Seccion a la cual pertenece el Territorio Administrativo
  SELECT adm.ZSCC_OID_SECC
    INTO lnOidSeccion
    FROM ZON_TERRI_ADMIN adm
   WHERE adm.oid_terr_admi = pnOidTerritorioAdmin;

  --Se obtiene el nivel de riesgo asociado a la seccion
  BEGIN
    SELECT sec.OID_NIVE_RIES
      INTO lnOidNivelRiesgo
      FROM CAR_NIVRI_SECCI sec
     WHERE sec.OID_SECC = lnOidSeccion;
  EXCEPTION
    WHEN OTHERS THEN
      --Se obtiene el nivel de riesgo por default
      SELECT rie.OID_NIVE_RIES
        INTO lnOidNivelRiesgo
        FROM CAR_NIVEL_RIESG rie
       WHERE rie.ind_nive_defa = 1;
  END;

  RETURN lnOidNivelRiesgo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_OBTIE_NIVEL_RIESG: ' || ls_sqlerrm);

END MAE_FN_OBTIE_NIVEL_RIESG;


/**************************************************************************
Descripcion       : Redefine el periodo de Ingreso para las Retiradas
Fecha Creacion    : 07/04/2010
Fecha Modificacion: 13/06/2013
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo    :  Oid Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_PERIO_INGRE_RETIR
  (pnOidCliente               NUMBER,
   pnOidPeriodo               NUMBER,
   psCodigoUsuario            VARCHAR2)
IS
  lnOidUnidadAdmi        MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE;
  lnOidUnidadAdmiAnt     MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE;
  lnoidPeriodoIni        CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoFin        CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoAnt        CRA_PERIO.OID_PERI%TYPE;
  lnOidClienteVnte       MAE_CLIEN.OID_CLIE%TYPE;
  lnTotal                NUMBER;

BEGIN

  --Se ubica el ultimo registro de las Unidades Administrativas del Cliente
  BEGIN
    SELECT OID_CLIE_UNID_ADMI, PERD_OID_PERI_INI
      INTO lnOidUnidadAdmi, lnoidPeriodoIni
      FROM MAE_CLIEN_UNIDA_ADMIN
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND PERD_OID_PERI_FIN IS NULL
       AND IND_ACTI = 1;
  EXCEPTION
    WHEN OTHERS THEN
      lnoidPeriodoIni := NULL;
  END;

  IF((lnoidPeriodoIni IS NOT NULL) AND (lnoidPeriodoIni <> pnOidPeriodo)) THEN
    BEGIN
      SELECT MAX(PERD_OID_PERI_FIN)
        INTO lnOidPeriodoFin
        FROM MAE_CLIEN_UNIDA_ADMIN
       WHERE CLIE_OID_CLIE = pnOidCliente;
    EXCEPTION
      WHEN OTHERS THEN
        lnOidPeriodoFin := NULL;
    END;

    IF(lnOidPeriodoFin IS NOT NULL) THEN
      SELECT OID_CLIE_UNID_ADMI
        INTO lnOidUnidadAdmiAnt
        FROM MAE_CLIEN_UNIDA_ADMIN
       WHERE CLIE_OID_CLIE = pnOidCliente
         AND PERD_OID_PERI_FIN = lnOidPeriodoFin;

      --Actualizamos el registro vigente de Unidad Administrativa
      UPDATE MAE_CLIEN_UNIDA_ADMIN
         SET PERD_OID_PERI_INI = pnOidPeriodo,
             USU_MODI = psCodigoUsuario
       WHERE OID_CLIE_UNID_ADMI = lnOidUnidadAdmi;

      --OBTENEMOS EL PERIODO ANTERIOR
      SELECT B.oid_peri
        INTO lnOidPeriodoAnt
  		  FROM cra_perio A, cra_perio B
  		 WHERE A.oid_peri = pnOidPeriodo
         AND B.pais_oid_pais = A.pais_oid_pais
         AND B.marc_oid_marc = A.marc_oid_marc
         AND B.cana_oid_cana = A.cana_oid_cana
         AND B.fec_inic < A.fec_inic
         AND ROWNUM <= 1
  		 ORDER BY B.fec_inic DESC;

      --Actualizamos el registro anterior del vigente de Unidad Administrativa
      UPDATE MAE_CLIEN_UNIDA_ADMIN
         SET PERD_OID_PERI_FIN = lnOidPeriodoAnt,
             USU_MODI = psCodigoUsuario
       WHERE OID_CLIE_UNID_ADMI = lnOidUnidadAdmiAnt;

    END IF;
  END IF;

  --Recuperamos el Oid Cliente Recomendante

  BEGIN
    SELECT vin.clie_oid_clie_vnte
      INTO lnOidClienteVnte
      FROM MAE_CLIEN_VINCU vin, MAE_TIPO_VINCU tip
     WHERE vin.clie_oid_clie_vndo = pnOidCliente
       AND vin.tivc_oid_tipo_vinc = tip.oid_tipo_vinc
       AND tip.cod_tipo_vinc = '03';
  EXCEPTION
    WHEN OTHERS THEN
      lnOidClienteVnte := NULL;
  END;

  IF(lnOidClienteVnte IS NOT NULL) THEN
    FOR x IN (SELECT rte.OID_CLIE_RETE, rdo.OID_CLIE_REDO
  	            FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
            	  WHERE rdo.CLIE_OID_CLIE = pnOidCliente
            		  AND rte.OID_CLIE_RETE = rdo.CLR3_OID_CLIE_RETE
             		  AND rte.CLIE_OID_CLIE = lnOidClienteVnte) LOOP

        SELECT COUNT(pcr.oid_pedi_conc_reco)
          INTO lnTotal
          FROM INC_PEDID_CONCU_RECOM pcr
         WHERE pcr.clre_oid_clie_redo = x.OID_CLIE_REDO;

        IF (lnTotal = 0) THEN
      DELETE INC_CLIEN_RECDO rdo
       WHERE rdo.clr3_oid_clie_rete = x.oid_clie_rete
         AND rdo.clie_oid_clie = pnOidCliente;
        END IF;

      SELECT COUNT(rdo.OID_CLIE_REDO)
        INTO lnTotal
 		    FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
		   WHERE rte.OID_CLIE_RETE = rdo.CLR3_OID_CLIE_RETE
   			 AND rte.OID_CLIE_RETE = x.oid_clie_rete;

      IF (lnTotal = 0) THEN
        SELECT COUNT(pcr.oid_pedi_conc_reco)
          INTO lnTotal
          FROM INC_PEDID_CONCU_RECOM pcr, INC_CLIEN_RECTE rte
         WHERE rte.OID_CLIE_RETE = pcr.clr3_oid_clie_rete
           AND rte.OID_CLIE_RETE = x.oid_clie_rete;

      IF(lnTotal = 0) THEN
        DELETE FROM INC_CLIEN_RECTE
	    	 WHERE OID_CLIE_RETE = x.oid_clie_rete;
      END IF;
      END IF;

    END LOOP;

  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REDEF_PERIO_INGRE_RETIR: ' || ls_sqlerrm);

END MAE_PR_REDEF_PERIO_INGRE_RETIR;



/**************************************************************************
Descripcion       : Valida si el concurso de recomendacion matriculado en el
                    ingreso de Clientes esta vigente y si no es efectiva
                    1 - Valido,  0 -Invalido
Fecha Creacion    : 26/05/2010
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  Oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_VIGEN_RECOM
  (pnOidCliente         NUMBER,
   pnOidPeriodo         NUMBER)
RETURN NUMBER
IS
  lnOidParaGral          INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lnIndPremCampEfec      INC_CONCU_PARAM_CONSU.IND_PREM_CAMP_EFEC%TYPE;
  lnResultado            NUMBER;
BEGIN
  lnResultado := 0;

  --Ubicamos el concurso matriculado en el Ingreso de Clientes
  BEGIN
    SELECT COPA_OID_PARA_GRAL
      INTO lnOidParaGral
      FROM (SELECT rec.COPA_OID_PARA_GRAL
              FROM INC_CLIEN_RECDO cli, INC_CLIEN_RECTE rec, CRA_PERIO per
             WHERE cli.CLIE_OID_CLIE = pnOidCliente
               AND cli.CLR3_OID_CLIE_RETE = rec.OID_CLIE_RETE
               AND cli.PERD_OID_PERI = per.OID_PERI
             ORDER BY per.FEC_INIC DESC)
      WHERE ROWNUM = 1;
  EXCEPTION
    WHEN OTHERS THEN
      lnOidParaGral := NULL;
  END;

  IF(lnOidParaGral IS NOT NULL) THEN
    SELECT NVL(IND_PREM_CAMP_EFEC,0)
      INTO lnIndPremCampEfec
      FROM INC_CONCU_PARAM_CONSU
     WHERE COPA_OID_PARA_GRAL = lnOidParaGral;

     --El Concurso esta efectivo
     IF(lnIndPremCampEfec = 1) THEN
       RETURN 0;
     END IF;

    --Verificamos si el concurso esta vigente, para el periodo de proceso
    SELECT COUNT(1)
      INTO lnResultado
      FROM INC_CONCU_PARAM_GENER a,
           INC_VERSI_CONCU       b,
           INC_CONCU_PARAM_CONSU d,
           CRA_PERIO ci,
           CRA_PERIO cf,
           (SELECT * FROM CRA_PERIO WHERE OID_PERI = pnOidPeriodo) cp
     WHERE a.IND_ACTI = 1
       AND a.BCAL_OID_BASE_CALC = 4
       AND a.PERD_OID_PERI_DESD = ci.OID_PERI
       AND d.PERD_OID_PERI_INIC_EVAL = cf.OID_PERI
       AND a.OID_PARA_GRAL = b.COPA_OID_PARA_GRAL
       AND a.OID_PARA_GRAL = d.COPA_OID_PARA_GRAL
       AND b.VICO_OID_VIGE_CONC IN (1,6)
       AND ci.FEC_INIC <= cp.FEC_INIC
       AND cp.FEC_INIC <= cf.FEC_INIC
       AND OID_PARA_GRAL = lnOidParaGral;
  END IF;

  RETURN lnResultado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VALID_VIGEN_RECOM: ' || ls_sqlerrm);

END MAE_FN_VALID_VIGEN_RECOM;


/**************************************************************************
Descripcion       : Inserta las correspondiente registros en las tablas de
                    incentivos para la recomendante y la recomendad
Fecha Creacion    : 26/05/2010
Parametros Entrada:
  pnOidPais            : Oid Pais
  pnCodClienteRcdo     :  Oid Cliente Recomendado
  pnCodClienteRcte     :  Oid Cliente Recomendante
  pnOidParaGral    :  Oid Concurso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REHAC_TABLA_INCEN
  (pnOidPais                   NUMBER,
   pnCodClienteRcdo            VARCHAR2,
   pnCodClienteRcte            VARCHAR2,
   pnOidParaGral               NUMBER)
IS
  lnOidTipoSoliPais      PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
  lnOidEstadoSolicitud   PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;
  lnOidPeriodoDesde      CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoHasta      CRA_PERIO.OID_PERI%TYPE;

  lnOidClienteRcte       MAE_CLIEN.OID_CLIE%TYPE;
  lnOidClienteRcdo       MAE_CLIEN.OID_CLIE%TYPE;
BEGIN
  --Recuperamos el TipoSolicitud Pais para Orden de Compra
  SELECT tsp.OID_TIPO_SOLI_PAIS
    INTO lnOidTipoSoliPais
    FROM PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
   WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
     AND sol.COD_TIPO_SOLI = 'SOC'
     AND tsp.PAIS_OID_PAIS = pnOidPais;

  --Recuperamos el OidEstadoSolicitud de estado = Anulado
  SELECT OID_ESTA_SOLI
    INTO lnOidEstadoSolicitud
    FROM PED_ESTAD_SOLIC
   WHERE COD_ESTA_SOLI = 'AN';

  --Recuperamos el periodo desde y hasta del concurso
  SELECT a.PERD_OID_PERI_DESD, d.PERD_OID_PERI_INIC_EVAL
    INTO lnOidPeriodoDesde, lnOidPeriodoHasta
    FROM INC_CONCU_PARAM_GENER a,
         INC_CONCU_PARAM_CONSU d
   WHERE a.OID_PARA_GRAL = pnOidParaGral
     AND a.OID_PARA_GRAL = d.COPA_OID_PARA_GRAL;

  lnOidClienteRcdo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(pnCodClienteRcdo);
  lnOidClienteRcte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(pnCodClienteRcte);

  --Insertamos en INC_SOLIC_CONCU_PUNTA, para el Recomendante
  BEGIN
    INSERT INTO INC_SOLIC_CONCU_PUNTA
	  (OID_SOLI_CONC_PUNT, NUM_PUNT, VAL_PUNT_BONI,
       VAL_PUNT_FALT_NANU, FEC_DOCU, IND_ANUL,
       COPA_OID_PARA_GRAL, SOCA_OID_SOLI_CABE, PERD_OID_PERI,
       CLIE_OID_CLIE, IMP_MONT, CLIE_OID_CLIE_GERE, NUM_UNID)
     (SELECT inc_socp_seq.NEXTVAL,0, 0,
             0, psc.fec_fact, 0,
             pnOidParaGral, -------- oid Concurso
             psc.oid_soli_cabe,
             psc.perd_oid_peri,
             psc.clie_oid_clie,
             psc.val_prec_cata_tota_loca,
             NULL,
             psc.val_unid_dema_real_tota
        FROM PED_SOLIC_CABEC psc,
             PED_SOLIC_CABEC psca,
             (SELECT cp.OID_PERI
                FROM CRA_PERIO ci, CRA_PERIO cf, CRA_PERIO cp
               WHERE ci.OID_PERI = lnOidPeriodoDesde
                 AND cf.OID_PERI = lnOidPeriodoHasta
                 AND ci.PAIS_OID_PAIS = cp.PAIS_OID_PAIS
                 AND ci.MARC_OID_MARC = cp.MARC_OID_MARC
                 AND ci.CANA_OID_CANA = cp.CANA_OID_CANA
                 AND ci.FEC_INIC <= cp.FEC_INIC
                 AND cp.FEC_INIC <= cf.FEC_INIC) cra
       WHERE psc.clie_oid_clie = lnOidClienteRcte --oid Recomendante
         AND psc.perd_oid_peri = cra.oid_peri --------- oid periodos de vigencia del concurso
         AND psca.perd_oid_peri = psc.perd_oid_peri
         AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
         AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais --------- oid tipo de solicitud pais
         AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN NULL;
  END;

  --Insertamos en INC_SOLIC_CONCU_RECOM, para el Recomendante
  BEGIN
    INSERT INTO INC_SOLIC_CONCU_RECOM
     (SELECT inc_socr_seq.NEXTVAL,
             psc.fec_fact,
             1,
             psc.val_prec_cata_tota_loca,
             psc.val_unid_dema_real_tota,
             NULL,
             recte.copa_oid_para_gral,
             psc.oid_soli_cabe,
             recte.clie_oid_clie,
             psc.perd_oid_peri,
             NULL,
             NULL
        FROM PED_SOLIC_CABEC psc,
             PED_SOLIC_CABEC psca,
             INC_CLIEN_RECTE recte,
             (SELECT cp.OID_PERI
                FROM CRA_PERIO ci, CRA_PERIO cf, CRA_PERIO cp
               WHERE ci.OID_PERI = lnOidPeriodoDesde
                 AND cf.OID_PERI = lnOidPeriodoHasta
                 AND ci.PAIS_OID_PAIS = cp.PAIS_OID_PAIS
                 AND ci.MARC_OID_MARC = cp.MARC_OID_MARC
                 AND ci.CANA_OID_CANA = cp.CANA_OID_CANA
                 AND ci.FEC_INIC <= cp.FEC_INIC
                 AND cp.FEC_INIC <= cf.FEC_INIC) cra
       WHERE recte.copa_oid_para_gral = pnOidParaGral -------- oid Concurso
         AND psc.clie_oid_clie = recte.clie_oid_clie
         AND psc.perd_oid_peri = cra.oid_peri --------- oid periodos de vigencia del concurso
         AND psca.perd_oid_peri = psc.perd_oid_peri
         AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
         AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais --------- oid tipo de solicitud pais
         AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud
         AND recte.clie_oid_clie = lnOidClienteRcte);  --------- oid Recomendante
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN NULL;
  END;

  --Insertamos en INC_SOLIC_CONCU_RECOM, para el Recomendante
  BEGIN
    INSERT INTO INC_SOLIC_CONCU_RECOM
     (SELECT inc_socr_seq.NEXTVAL,
               psc.fec_fact,
               1,
               psc.val_prec_cata_tota_loca,
               psc.val_unid_dema_real_tota,
               NULL,
               recte.copa_oid_para_gral,
               psc.oid_soli_cabe,
               recte.clie_oid_clie,
               psc.perd_oid_peri,
               recdo.clie_oid_clie,
               NULL
          FROM PED_SOLIC_CABEC psc,
               PED_SOLIC_CABEC psca,
               INC_CLIEN_RECTE recte,
               INC_CLIEN_RECDO recdo,
               (SELECT cp.OID_PERI
                FROM CRA_PERIO ci, CRA_PERIO cf, CRA_PERIO cp
               WHERE ci.OID_PERI = lnOidPeriodoDesde
                 AND cf.OID_PERI = lnOidPeriodoHasta
                 AND ci.PAIS_OID_PAIS = cp.PAIS_OID_PAIS
                 AND ci.MARC_OID_MARC = cp.MARC_OID_MARC
                 AND ci.CANA_OID_CANA = cp.CANA_OID_CANA
                 AND ci.FEC_INIC <= cp.FEC_INIC
                 AND cp.FEC_INIC <= cf.FEC_INIC) cra
         WHERE recte.copa_oid_para_gral = pnOidParaGral -------- oid Concurso
           AND psc.clie_oid_clie = recdo.clie_oid_clie
           AND psc.perd_oid_peri = cra.oid_peri --------- oid periodos de vigencia del concurso
           AND psca.perd_oid_peri = psc.perd_oid_peri
           AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
           AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais --------- oid tipo de solicitud pais
           AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud
           AND recdo.clr3_oid_clie_rete = recte.oid_clie_rete
           AND recdo.clie_oid_clie = lnOidClienteRcdo);  --------- oid Recomendado
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN NULL;
  END;

  --Insertamos en INC_PEDID_CONCU_RECOM, para el Recomendante
  BEGIN
    INSERT INTO INC_PEDID_CONCU_RECOM
     (OID_PEDI_CONC_RECO,
      IMP_MONT_PEDI,
      NUM_UNID_PEDI,
      IND_PEDI_VALI,
      CLRE_OID_CLIE_REDO,
      PERD_OID_PERI,
      CLIE_OID_CLIE,
      COPA_OID_PARA_GRAL,
      CLR3_OID_CLIE_RETE)
     (SELECT inc_pecr_seq.NEXTVAL,
             psc.val_prec_cata_tota_loca,
             psc.val_unid_dema_real_tota,
             NULL,
             NULL,
             psc.perd_oid_peri,
             NULL,
             recte.copa_oid_para_gral,
             recte.oid_clie_rete
        FROM PED_SOLIC_CABEC psc,
             PED_SOLIC_CABEC psca,
             INC_CLIEN_RECTE recte,
             (SELECT cp.OID_PERI
                FROM CRA_PERIO ci, CRA_PERIO cf, CRA_PERIO cp
               WHERE ci.OID_PERI = lnOidPeriodoDesde
                 AND cf.OID_PERI = lnOidPeriodoHasta
                 AND ci.PAIS_OID_PAIS = cp.PAIS_OID_PAIS
                 AND ci.MARC_OID_MARC = cp.MARC_OID_MARC
                 AND ci.CANA_OID_CANA = cp.CANA_OID_CANA
                 AND ci.FEC_INIC <= cp.FEC_INIC
                 AND cp.FEC_INIC <= cf.FEC_INIC) cra
       WHERE recte.copa_oid_para_gral = pnOidParaGral -------- oid Concurso
         AND psc.perd_oid_peri = cra.oid_peri --------- oid periodos de vigencia del concurso
         AND psc.clie_oid_clie = recte.clie_oid_clie
         AND psca.perd_oid_peri = psc.perd_oid_peri
         AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
         AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais --------- oid tipo de solicitud pais
         AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud
         AND recte.clie_oid_clie = lnOidClienteRcte);  --------- oid Recomendante
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN NULL;
  END;

  --Insertamos en INC_PEDID_CONCU_RECOM, para el Recomendado
  BEGIN
    INSERT INTO INC_PEDID_CONCU_RECOM
     (OID_PEDI_CONC_RECO,
      IMP_MONT_PEDI,
      NUM_UNID_PEDI,
      IND_PEDI_VALI,
      CLRE_OID_CLIE_REDO,
      PERD_OID_PERI,
      CLIE_OID_CLIE,
      COPA_OID_PARA_GRAL,
      CLR3_OID_CLIE_RETE)
     (SELECT inc_pecr_seq.NEXTVAL,
             psc.val_prec_cata_tota_loca,
             psc.val_unid_dema_real_tota,
             NULL,
             recdo.oid_clie_redo,
             psc.perd_oid_peri,
             NULL,
             recte.copa_oid_para_gral,
             recte.oid_clie_rete
        FROM PED_SOLIC_CABEC psc,
             PED_SOLIC_CABEC psca,
             INC_CLIEN_RECTE recte,
             INC_CLIEN_RECDO recdo,
             (SELECT cp.OID_PERI
                FROM CRA_PERIO ci, CRA_PERIO cf, CRA_PERIO cp
               WHERE ci.OID_PERI = lnOidPeriodoDesde
                 AND cf.OID_PERI = lnOidPeriodoHasta
                 AND ci.PAIS_OID_PAIS = cp.PAIS_OID_PAIS
                 AND ci.MARC_OID_MARC = cp.MARC_OID_MARC
                 AND ci.CANA_OID_CANA = cp.CANA_OID_CANA
                 AND ci.FEC_INIC <= cp.FEC_INIC
                 AND cp.FEC_INIC <= cf.FEC_INIC) cra
       WHERE recte.copa_oid_para_gral = pnOidParaGral -------- oid Concurso
         AND psc.perd_oid_peri = cra.oid_peri --------- oid periodos de vigencia del concurso
         AND psc.clie_oid_clie = recte.clie_oid_clie
         AND psca.perd_oid_peri = psc.perd_oid_peri
         AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
         AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais --------- oid tipo de solicitud pais
         AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud
         AND recdo.clr3_oid_clie_rete = recte.oid_clie_rete
         AND recdo.clie_oid_clie = lnOidClienteRcdo);  --------- oid Recomendado
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN NULL;
  END;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REHAC_TABLA_INCEN: ' || ls_sqlerrm);
END MAE_PR_REHAC_TABLA_INCEN;


/**************************************************************************
Descripcion       : Borra al registro en INC_CLIEN_RECDO y sus dependencias
Fecha Creacion    : 21/06/2010
Parametros Entrada:
  pnOidClienteRcdo     :  Oid Cliente Recomendado
  pnOidClienteRcte    :  Oid Cliente Recomendante

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_BORRA_CLIEN_RECDO
  (pnOidClienteRcdo           NUMBER,
   pnOidClienteRcte           NUMBER)
IS
BEGIN

  FOR x IN (SELECT rdo.OID_CLIE_REDO
                            FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
                           WHERE rdo.CLIE_OID_CLIE = pnOidClienteRcdo
                             AND rte.OID_CLIE_RETE = rdo.CLR3_OID_CLIE_RETE
                             AND rte.CLIE_OID_CLIE = pnOidClienteRcte) LOOP

    --Borramos registros de la tabla INC_PEDID_CONCU_RECOM
    DELETE FROM INC_PEDID_CONCU_RECOM WHERE CLRE_OID_CLIE_REDO = x.OID_CLIE_REDO;

    --Borramos registros de la tabla INC_PEDID_CONCU_RECOM_TEMP
    DELETE FROM INC_PEDID_CONCU_RECOM_TEMP WHERE CLRE_OID_CLIE_REDO = x.OID_CLIE_REDO;

    --Borramos registros de la tabla INC_SOLIC_CONCU_RECOM_TEMP
    DELETE FROM INC_SOLIC_CONCU_RECOM_TEMP WHERE CLIE_OID_RECO_DADO = x.OID_CLIE_REDO;

    --Borramos registros de la tabla INC_CLIEN_RECDO
    DELETE FROM INC_CLIEN_RECDO WHERE OID_CLIE_REDO = x.OID_CLIE_REDO;

  END LOOP;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_BORRA_CLIEN_RECDO: ' || ls_sqlerrm);

END MAE_PR_BORRA_CLIEN_RECDO;


/**************************************************************************
Descripcion       : Borra al registro en INC_CLIEN_RECTE y sus dependencias
Fecha Creacion    : 21/06/2010
Parametros Entrada:

  pnOidClienteRcte    :  Oid Cliente Recomendante

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_BORRA_CLIEN_RECTE
  (pnOidClienteRcte           NUMBER)
IS
BEGIN

  --Borramos registros de la tabla INC_PEDID_CONCU_RECOM
  DELETE FROM INC_PEDID_CONCU_RECOM
   WHERE CLR3_OID_CLIE_RETE = pnOidClienteRcte;

  --Borramos registros de la tabla INC_CLIEN_RECTE
	DELETE FROM INC_CLIEN_RECTE
	 WHERE OID_CLIE_RETE = pnOidClienteRcte;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_BORRA_CLIEN_RECTE: ' || ls_sqlerrm);

END MAE_PR_BORRA_CLIEN_RECTE;


/**************************************************************************
Descripcion       : Recuperamos el Oid Periodo de Recomendacion para la
                    Consultora
Fecha Creacion    : 22/07/2010
Parametros Entrada:
  pnOidPais        : Oid Pais
  pnOidCliente     :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_OBTEN_PERIO_RECOM
  (pnOidPais                   NUMBER,
   pnOidCliente                NUMBER)
RETURN NUMBER
IS
  lsCodigoEstatus        MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;
  lnOcurrencias          NUMBER;

  lnOidPeriodo           CRA_PERIO.OID_PERI%TYPE;
  lnOidTipoSoliPais      PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
  lnOidEstadoSolicitud   PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;

  lnExiste          NUMBER;
BEGIN
  --Obtenemos el Estado de la Consultora
  SELECT b.COD_ESTA_CLIE
    INTO lsCodigoEstatus
    FROM MAE_CLIEN_DATOS_ADICI a, MAE_ESTAT_CLIEN b
   WHERE a.CLIE_OID_CLIE = pnOidCliente
     AND b.OID_ESTA_CLIE = a.ESTA_OID_ESTA_CLIE;

  IF(lsCodigoEstatus = '02') THEN --Para el Caso de Nuevas
    --Verificamos si Tiene Recomendante
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM MAE_CLIEN_VINCU V, MAE_TIPO_VINCU T
     WHERE V.CLIE_OID_CLIE_VNDO = pnOidCliente
       AND V.TIVC_OID_TIPO_VINC = T.OID_TIPO_VINC
       AND T.IND_RECO = 1;

    IF(lnOcurrencias = 0) THEN --No Tiene Recomendante
      --Recuperamos el TipoSolicitud Pais para Orden de Compra
      SELECT tsp.OID_TIPO_SOLI_PAIS
        INTO lnOidTipoSoliPais
        FROM PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
       WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
         AND sol.COD_TIPO_SOLI = 'SOC'
         AND tsp.PAIS_OID_PAIS = pnOidPais;

      --Recuperamos el OidEstadoSolicitud de estado = Anulado
      SELECT OID_ESTA_SOLI
        INTO lnOidEstadoSolicitud
        FROM PED_ESTAD_SOLIC
       WHERE COD_ESTA_SOLI = 'AN';

      --Recuperamos el periodo del primer pedido
      SELECT PERD_OID_PERI
        INTO lnOidPeriodo
        FROM (
              SELECT psc.perd_oid_peri
                FROM PED_SOLIC_CABEC psc,
                     PED_SOLIC_CABEC psca,
                     CRA_PERIO cra
               WHERE cra.Oid_Peri = psc.perd_oid_peri
                 AND psc.soca_oid_soli_cabe = psca.oid_soli_cabe
                 AND psc.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais
                 AND psca.esso_oid_esta_soli <> lnOidEstadoSolicitud
                 AND psc.clie_oid_clie = pnOidCliente
               ORDER BY cra.FEC_INIC)
        WHERE ROWNUM = 1;
    ELSE

      SELECT count(1)
        INTO lnExiste
        FROM (SELECT per.OID_PERI
                FROM INC_CLIEN_RECDO cli, INC_CLIEN_RECTE rec, CRA_PERIO per
               WHERE cli.CLIE_OID_CLIE = pnOidCliente
                 AND cli.CLR3_OID_CLIE_RETE = rec.OID_CLIE_RETE
                 AND cli.PERD_OID_PERI = per.OID_PERI
               ORDER BY per.FEC_INIC DESC)
        WHERE ROWNUM = 1;

        IF (lnExiste<>0) THEN


         SELECT OID_PERI
        INTO lnOidPeriodo
        FROM (SELECT per.OID_PERI
                FROM INC_CLIEN_RECDO cli, INC_CLIEN_RECTE rec, CRA_PERIO per
               WHERE cli.CLIE_OID_CLIE = pnOidCliente
                 AND cli.CLR3_OID_CLIE_RETE = rec.OID_CLIE_RETE
                 AND cli.PERD_OID_PERI = per.OID_PERI
               ORDER BY per.FEC_INIC DESC)
        WHERE ROWNUM = 1;

        ELSE
             select X.OID_PERI
             INTO lnOidPeriodo
             from cra_perio x where
             ( SELECT v.fec_desd
              FROM MAE_CLIEN_VINCU V, MAE_TIPO_VINCU T
             WHERE V.CLIE_OID_CLIE_VNDO = pnOidCliente
               AND V.TIVC_OID_TIPO_VINC = T.OID_TIPO_VINC
               AND T.IND_RECO = 1) between x.fec_inic
               and  NVL(x.fec_fina,
                    ( SELECT v.fec_desd
                      FROM MAE_CLIEN_VINCU V, MAE_TIPO_VINCU T
                     WHERE V.CLIE_OID_CLIE_VNDO = pnOidCliente
                       AND V.TIVC_OID_TIPO_VINC = T.OID_TIPO_VINC
                       AND T.IND_RECO = 1));

         END IF;



    END IF;
  END IF;

  RETURN lnOidPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_OBTEN_PERIO_RECOM: ' || ls_sqlerrm);
END MAE_FN_OBTEN_PERIO_RECOM;


/**************************************************************************
Descripcion       : Valida si la zona y region hicieron sus cierres respectivos
                    S - Antes del cierre,  N -Despues del cierre
Fecha Creacion    : 20/12/2010
Parametros Entrada:
  pnOidPais        :     Oid Pais
  pnOidPeriodo     :     Oid Periodo
  pnOidRegion      :     Oid Region
  pnOidActividad   :     Oid Actividad
  pnFecCrea         :    Fecha de Creacion

Autor             : Christian Gonzales

***************************************************************************/
FUNCTION MAE_FN_VALID_CIERR_REGIO
  (pnOidPais        VARCHAR2,
   pnOidPeriodo     NUMBER,
   pnOidRegion      NUMBER,
   pnOidActividad   VARCHAR2,
   pnFecCrea        DATE)
RETURN CHAR
IS
vnFecInic           date;
vnContadorRegion    int;


BEGIN

SELECT max(cc.fec_inic) INTO vnFecInic FROM cra_crono cc
where cc.perd_oid_peri= pnOidPeriodo
AND cc.ZZON_OID_ZONA in (SELECT a.oid_zona FROM zon_zona a	where a.ZORG_OID_REGI = pnOidRegion)
AND cc.cact_oid_acti = pnOidActividad;

IF   pnFecCrea < vnFecInic THEN
     return 'S';
ELSE
    SELECT COUNT(fcc.zorg_oid_regi) INTO vnContadorRegion FROM FAC_CONTR_CIERR fcc
    where fcc.perd_oid_peri = pnOidPeriodo
    AND fcc.zorg_oid_regi = pnOidRegion
    AND fcc.tcie_oid_tipo_cier = '1'
    AND fcc.pais_oid_pais = pnOidPais;

    IF vnContadorRegion > 0 THEN
       return 'N';
    ELSE
       return 'S';
    END IF;

END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VALID_CIERR_REGIO: ' || ls_sqlerrm);


END MAE_FN_VALID_CIERR_REGIO;


/*********************************************************************************
Descripcion       : Actualizacion Datos Clientes que tengan caracteres especiales
Fecha Creacion    : 24/07/2011
Fecha Modificacion: 09/04/2015
Parametros Entrada:

Autor             : CSVD - FFVV

**********************************************************************************/
PROCEDURE MAE_PR_ACTUA_DATOS_CLIEN
IS

lnOidPeriodo INT := NULL;
lsUsuario           VARCHAR2(25):= 'OPERADOR FN';
lnIndEjecucion      NUMBER(1) := 0;
ldfecproc           DATE;


    CURSOR c_error_ua IS
       SELECT ua.clie_oid_clie oidCliente
         FROM mae_clien_unida_admin ua
     GROUP BY ua.clie_oid_clie
       HAVING sum(ua.ind_acti)>1;

        TYPE t_error_ua IS TABLE OF c_error_ua%ROWTYPE;
        tabla_error_ua t_error_ua;

    lsIndCorregirUADuplicado        bas_param_pais.val_para%TYPE; --indicador corregir Ua Duplicado

 CURSOR c_difer_ua (psCodigoPeriodo VARCHAR2) is
     SELECT p.clie_oid_clie,
            cl.cod_clie,
            p.ztad_oid_terr_admi as ped_oid_terr_admi,
            ua.ztad_oid_terr_admi as ua_oid_terr_admi
       FROM ped_solic_cabec p,
            ped_tipo_solic_pais tsp,
            ped_tipo_solic ts,
            seg_perio_corpo pc,
            cra_perio cr,
            mae_clien_unida_admin ua,
            zon_terri_admin za,
            mae_clien cl
      WHERE p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
        AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
        AND ts.cod_tipo_soli = 'SOC'
        and p.clie_oid_clie = cl.oid_clie
        AND p.perd_oid_peri = cr.oid_peri
        and cr.peri_oid_peri = pc.oid_peri
      --  and cr.oid_peri = psOidPeri
        and pc.cod_peri = psCodigoPeriodo
        and p.clie_oid_clie = ua.clie_oid_clie
        and cr.oid_peri >= ua.perd_oid_peri_ini
        and (cr.oid_peri <= ua.perd_oid_peri_fin OR ua.perd_oid_peri_fin IS NULL)
        and ua.perd_oid_peri_fin IS NULL
        and ua.ztad_oid_terr_admi = za.oid_terr_admi
        and p.terr_oid_terr <> za.terr_oid_terr;

 CURSOR c_difer_lider (psCodigoPeriodo VARCHAR2) is
       SELECT base.oid_clie, base.gere, base.oid_secc
       FROM (
        SELECT gere.*, peri.cod_peri, clie.oid_clie,
               (
                 SELECT zscc.oid_secc
                   FROM zon_secci zscc,
                        zon_zona zzon,
                        zon_regio zorg
                  WHERE zscc.zzon_oid_zona = zzon.oid_zona
                    AND zzon.zorg_oid_regi = zorg.oid_regi
                    AND zscc.cod_secc = gere.cod_secc
                    AND zzon.cod_zona = gere.cod_zona
                    AND zorg.cod_regi = gere.cod_regi
                    AND (select oid_peri from cra_perio where val_nomb_peri like '%'||psCodigoPeriodo||'%') BETWEEN zscc.perd_oid_peri_inic AND
                         NVL(zscc.perd_oid_peri_fina,(select oid_peri from cra_perio where val_nomb_peri like '%'||psCodigoPeriodo||'%'))
               ) oid_secc
          FROM zon_histo_geren gere,
               cra_perio perd,
               seg_perio_corpo peri,
               cra_perio perd2,
               seg_perio_corpo peri2,
               mae_clien clie
         WHERE 1=1
           AND gere.gere = clie.cod_clie
           AND gere.perd_oid_peri_desd = perd.oid_peri
           AND perd.peri_oid_peri = peri.oid_peri
           AND nvl(gere.perd_oid_peri_hast,(select oid_peri from cra_perio where val_nomb_peri like '%'||psCodigoPeriodo||'%')) = perd2.oid_peri
           AND perd2.peri_oid_peri = peri2.oid_peri
           AND gere.cod_regi IS NOT NULL
           AND gere.cod_zona IS NOT NULL
           AND gere.cod_secc IS NOT NULL
           AND psCodigoPeriodo BETWEEN peri.cod_peri AND peri2.cod_peri
       ) base,
       (
         SELECT zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, clie.cod_clie, zscc.oid_secc
           FROM zon_secci zscc,
                zon_zona zzon,
                zon_regio zorg,
                mae_clien clie
          WHERE zscc.clie_oid_clie = clie.oid_clie
            AND zscc.zzon_oid_zona = zzon.oid_zona
            AND zzon.zorg_oid_regi = zorg.oid_regi
       ) resp
      WHERE 1=1
         AND base.gere = resp.cod_clie(+)
         AND resp.cod_clie IS NULL;

 TYPE t_difer_lider IS TABLE OF c_difer_lider%ROWTYPE;
 tabla_difer_lider t_difer_lider;

  TYPE t_difer_ua IS TABLE OF c_difer_ua%ROWTYPE;
  tabla_difer_ua t_difer_ua;
  lnIdMarca           NUMBER;
  lnIdCanal           NUMBER;
  lnIdPeriSgte        NUMBER;
  lsCodPeriSgte       VARCHAR2(6);
  lsCodigoPeriodo     VARCHAR2(6);
  lsCodPais           VARCHAR2(3);
  lnIdPais            NUMBER;


BEGIN
  SELECT perd.oid_peri, bcf.fec_proc , bcf.cod_peri, bcf.cod_pais
    INTO lnOidPeriodo, ldfecproc, lsCodigoPeriodo, lsCodPais
    FROM bas_ctrl_fact bcf,
         cra_perio perd,
         seg_perio_corpo peri
   WHERE bcf.sta_camp = 0 AND bcf.ind_camp_act = 1
     AND perd.peri_oid_peri = peri.oid_peri
     AND peri.cod_peri = bcf.cod_peri;

  -- Invocamos a Eliminar Caracteres Especiales --
  MSG_PKG_PROCE_MENSA.MSG_PR_ELIMI_CARAC_ESPEC (lsCodPais);

  -- 6. Corrige dato de campaña de primer contacto
  UPDATE mae_clien_prime_conta cprc
     SET cprc.perd_oid_peri = nvl((SELECT MAX(clhe.perd_oid_peri)
                                 FROM mae_clien_histo_estat clhe
                                WHERE clhe.esta_oid_esta_clie IN (2,8)
                                  AND clhe.clie_oid_clie = cprc.clie_oid_clie
                                GROUP BY clhe.clie_oid_clie),cprc.perd_oid_peri)
   WHERE cprc.clie_oid_clie IN
         (
          SELECT cprc.clie_oid_clie
            FROM mae_clien clie,
                 mae_clien_prime_conta cprc,
                 mae_clien_datos_adici clda,
                 (
                   SELECT soc2.clie_oid_clie, MAX(soc2.perd_oid_peri) AS perd_oid_peri
                     FROM ped_solic_cabec_acum2 soc2,
                          mae_clien clie
                    WHERE soc2.clie_oid_clie = clie.oid_clie
                    GROUP BY soc2.clie_oid_clie
                 ) soc2
           WHERE clda.ind_acti = 1
             AND cprc.perd_oid_peri = lnOidPeriodo
             AND clda.esta_oid_esta_clie <> 1
             AND cprc.perd_oid_peri > NVL(soc2.perd_oid_peri,0)
             AND clie.oid_clie = cprc.clie_oid_clie
             AND clie.oid_clie = clda.clie_oid_clie
             AND clie.oid_clie = soc2.clie_oid_clie(+)
         );


  -- 8. Actualiza Clientes sin UA activa en Campana Actual --
  UPDATE mae_clien_unida_admin mcua
     SET mcua.ind_acti = 0,
         mcua.fec_ulti_actu = SYSDATE
--         ,mcua.usu_modi = lsUsuario
   WHERE mcua.ind_acti = 1
     AND mcua.clie_oid_clie IN (
                                SELECT mcua.clie_oid_clie
                                  FROM mae_clien             c,
                                       mae_clien_unida_admin mcua,
                                       mae_clien_tipo_subti  mcts,
                                       mae_clien_datos_adici mcda
                                 WHERE c.oid_clie = mcts.clie_oid_clie
                                   AND mcua.clie_oid_clie = mcts.clie_oid_clie
                                   AND mcts.ticl_oid_tipo_clie = 2
                                   AND mcda.clie_oid_clie = mcua.clie_oid_clie
                                   AND mcda.ind_acti = 1
                                   AND mcua.ind_acti = 0
                                   AND lnOidPeriodo BETWEEN mcua.perd_oid_peri_ini AND NVL(mcua.perd_oid_peri_fin, lnOidPeriodo)
                               );

  UPDATE mae_clien_unida_admin mcua
     SET mcua.ind_acti = 1,
         mcua.fec_ulti_actu = SYSDATE
--         ,mcua.usu_modi = lsUsuario
   WHERE mcua.oid_clie_unid_admi IN (
                                     SELECT mcua.oid_clie_unid_admi
                                       FROM mae_clien             c,
                                            mae_clien_unida_admin mcua,
                                            mae_clien_tipo_subti  mcts,
                                            mae_clien_datos_adici mcda
                                      WHERE c.oid_clie = mcts.clie_oid_clie
                                        AND mcua.clie_oid_clie = mcts.clie_oid_clie
                                        AND mcts.ticl_oid_tipo_clie = 2
                                        AND mcda.clie_oid_clie = mcua.clie_oid_clie
                                        AND mcda.ind_acti = 1
                                        AND mcua.ind_acti = 0
                                        AND lnOidPeriodo BETWEEN mcua.perd_oid_peri_ini AND NVL(mcua.perd_oid_peri_fin, lnOidPeriodo)
                                    );


   --correcion de ua duplicada
   /* Generando informacion */
    BEGIN
         SELECT bpp.val_para
           INTO lsIndCorregirUADuplicado
           FROM bas_param_pais bpp
          WHERE bpp.cod_pais = ( SELECT p.cod_pais
                                   FROM seg_pais p
                                  WHERE p.oid_pais = ( SELECT a.pais_oid_pais
                                                         FROM gen_pais_activ a
                                                     )
                                )
            AND bpp.cod_sist = 'MAE'
            AND bpp.nom_para = 'IndCorregirUADuplicado'
            AND bpp.ind_acti = '1';
         EXCEPTION
           WHEN NO_DATA_FOUND
           THEN lsIndCorregirUADuplicado := '1';
    END;

   IF lsIndCorregirUADuplicado = 1 THEN
         OPEN c_error_ua;
         LOOP
         FETCH c_error_ua BULK COLLECT INTO tabla_error_ua LIMIT W_FILAS;
            IF tabla_error_ua.COUNT > 0 THEN
               FOR i IN tabla_error_ua.FIRST .. tabla_error_ua.LAST LOOP
                  BEGIN
                      --desactivamos los registros activos de las consultoras con duplicidad de unidad administrativa
                      UPDATE mae_clien_unida_admin ua
                         SET ua.ind_acti = 0,
                             ua.fec_ulti_actu = SYSDATE
                       WHERE ua.ind_acti      = 1 AND
                             ua.clie_oid_clie = tabla_error_ua(i).oidCliente;
                      --Activamos solo un registro de unidad administrativa
                      UPDATE mae_clien_unida_admin ua
                         SET ua.ind_acti = 1,
                             ua.fec_ulti_actu = SYSDATE
                       WHERE ua.oid_clie_unid_admi = (SELECT MIN(mcua.oid_clie_unid_admi)
                                                        FROM mae_clien_unida_admin mcua
                                                       WHERE mcua.clie_oid_clie = tabla_error_ua(i).oidCliente AND
                                                             lnOidPeriodo BETWEEN mcua.perd_oid_peri_ini AND NVL(mcua.perd_oid_peri_fin, lnOidPeriodo)
                                                      );
                  END;
                END LOOP;
            END IF;
         EXIT WHEN c_error_ua%NOTFOUND;
         END LOOP;
         CLOSE c_error_ua;
  END IF;

  --corrige direccion duplicada
  UPDATE mae_clien_direc cd
     SET cd.ind_elim = 1,
         cd.usu_modi = lsUsuario,
         cd.fec_ulti_actu = SYSDATE
   WHERE cd.oid_clie_dire = (    SELECT MIN(mcd.oid_clie_dire)
                                   FROM mae_clien_direc mcd,
                                        mae_clien mc
                                  WHERE mcd.ind_dire_ppal = 1
                                    AND mcd.ind_elim <> 1
                                    AND mcd.clie_oid_clie = mc.oid_clie
                               GROUP BY mcd.clie_oid_clie, mc.cod_clie
                                 HAVING COUNT (1) > 1
                             );

  -- Actualizamos el Indicador IND_PROC_GP2 = 1 necesario para Programa Nuevas --
  UPDATE int_solic_conso_cabec consol
     SET consol.ind_proc_gp2 = 1
   WHERE consol.soca_oid_soli_cabe_refe IN
         (
          SELECT oid_soli_cabe
            FROM ped_solic_cabec ped
           WHERE ped.perd_oid_peri = lnOidPeriodo
             AND trunc(ped.fec_fact) >= trunc(ldfecproc)
             AND ped.grpr_oid_grup_proc = 5
             AND EXISTS(
                        SELECT pts.oid_tipo_soli
                          FROM ped_tipo_solic_pais ptsp,
                               ped_tipo_solic pts
                         WHERE ptsp.oid_tipo_soli_pais = ped.tspa_oid_tipo_soli_pais
                           AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                           AND pts.cod_tipo_soli = 'SOC'
                       )
             AND ped.ind_oc = 1
         );

  -- Actualizamos registros que tengan Comunicacion Duplicada --
  UPDATE mae_clien_comun mco
     SET mco.ind_comu_ppal = 0,
         mco.usu_modi = lsUsuario,
         mco.fec_ulti_actu = SYSDATE
   WHERE mco.oid_clie_comu = (
                          SELECT MIN (mcc.oid_clie_comu)
                            FROM mae_clien_comun mcc
                           WHERE mcc.ind_comu_ppal = 1
                             AND mcc.clie_oid_clie = mco.clie_oid_clie
                           GROUP BY mcc.clie_oid_clie
                          HAVING COUNT(1) > 1
                         );

  -- Actualiza Documento Identidad Principal
  UPDATE mae_clien_ident mci
     SET mci.val_iden_docu_prin = '1',
         mci.fec_ulti_actu      = SYSDATE
   WHERE mci.tdoc_oid_tipo_docu = (SELECT td.oid_tipo_docu
                                   FROM mae_tipo_docum td
                                  WHERE td.cod_tipo_docu = '02'
                                ) -- 'RUC'
    AND mci.clie_oid_clie IN ( SELECT c.oid_clie
                                FROM mae_clien c,
                                     mae_clien_tipo_subti mcts,
                                     mae_clien_ident mci
                               WHERE c.oid_clie = mcts.clie_oid_clie
                                 AND mcts.ticl_oid_tipo_clie = 2
                                 AND c.oid_clie = mci.clie_oid_clie
                            GROUP BY c.oid_clie
                              HAVING SUM(mci.val_iden_docu_prin)=0
                            );

  UPDATE mae_clien_ident mci
     SET mci.val_iden_docu_prin = '1',
         mci.fec_ulti_actu      = SYSDATE
   WHERE mci.tdoc_oid_tipo_docu = (SELECT td.oid_tipo_docu
                                     FROM mae_tipo_docum td
                                    WHERE td.cod_tipo_docu = '01'
                                   ) -- 'DNI'
     AND mci.clie_oid_clie IN ( SELECT c.oid_clie
                                  FROM mae_clien c,
                                       mae_clien_tipo_subti mcts,
                                       mae_clien_ident mci
                                 WHERE c.oid_clie = mcts.clie_oid_clie
                                   AND mcts.ticl_oid_tipo_clie = 2
                                   AND c.oid_clie = mci.clie_oid_clie
                              GROUP BY c.oid_clie
                                HAVING SUM(mci.val_iden_docu_prin)=0
                              );

  UPDATE mae_clien_ident mci
     SET mci.val_iden_docu_prin = '1',
         mci.fec_ulti_actu      = SYSDATE
   WHERE mci.tdoc_oid_tipo_docu = (SELECT td.oid_tipo_docu
                                     FROM mae_tipo_docum td
                                    WHERE td.cod_tipo_docu = '10'
                                   ) -- 'DNI'
     AND mci.clie_oid_clie IN ( SELECT c.oid_clie
                                  FROM mae_clien c,
                                       mae_clien_tipo_subti mcts,
                                       mae_clien_ident mci
                                 WHERE c.oid_clie = mcts.clie_oid_clie
                                   AND mcts.ticl_oid_tipo_clie = 2
                                   AND c.oid_clie = mci.clie_oid_clie
                              GROUP BY c.oid_clie
                                HAVING SUM(mci.val_iden_docu_prin)=0
                              );

  UPDATE mae_clien_ident mci
     SET mci.val_iden_docu_prin = '1',
         mci.fec_ulti_actu      = SYSDATE
   WHERE mci.tdoc_oid_tipo_docu = (SELECT td.oid_tipo_docu
                                     FROM mae_tipo_docum td
                                    WHERE td.cod_tipo_docu = '04'
                                   ) -- 'DNI'
     AND mci.clie_oid_clie IN ( SELECT c.oid_clie
                                  FROM mae_clien c,
                                       mae_clien_tipo_subti mcts,
                                       mae_clien_ident mci
                                 WHERE c.oid_clie = mcts.clie_oid_clie
                                   AND mcts.ticl_oid_tipo_clie = 2
                                   AND c.oid_clie = mci.clie_oid_clie
                              GROUP BY c.oid_clie
                                HAVING SUM(mci.val_iden_docu_prin)=0
                              );

  -- Actualizamos Tipo de Documento de Referencias que tienen Numero de Documento --
  UPDATE mae_refer SET tipo_docu_refe = '01' WHERE num_docu_refe IS NOT NULL AND tipo_docu_refe IS NULL;

  -- Actualizamos Consultoras con diferentes UA entre pedido y campaña --

  lnIdMarca      := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnIdCanal      := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnIdPais       := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(lsCodPais);
  lsCodPeriSgte  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodigoPeriodo, lnidPais, lnIdMarca, lnIdCanal, 1);
  lnIdPeriSgte   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSgte, lnIdMarca, lnIdCanal);
/*
  OPEN c_difer_ua(lsCodigoPeriodo);
         LOOP
         FETCH c_difer_ua BULK COLLECT INTO tabla_difer_ua LIMIT W_FILAS;

            IF tabla_difer_ua.COUNT > 0 THEN
               FOR i IN tabla_difer_ua.FIRST .. tabla_difer_ua.LAST LOOP

                  BEGIN
                      --

                      UPDATE MAE_CLIEN_UNIDA_ADMIN
                         SET PERD_OID_PERI_FIN  = lnOidPeriodo,
                             IND_ACTI           = 1,
                             FEC_CAMB           = SYSDATE,
                             USU_MODI           = lsUsuario
                       WHERE ZTAD_OID_TERR_ADMI = tabla_difer_ua(i).ped_oid_terr_admi
                         AND CLIE_OID_CLIE      = tabla_difer_ua(i).clie_oid_clie ;


                      --
                      UPDATE MAE_CLIEN_UNIDA_ADMIN UA
                         SET UA.IND_ACTI          = 0,
                             UA.PERD_OID_PERI_INI = lnIdPeriSgte,
                             UA.FEC_CAMB          = SYSDATE,
                             UA.USU_MODI          = lsUsuario
                       WHERE UA.OID_CLIE_UNID_ADMI = (SELECT MAX(mcua.oid_clie_unid_admi)
                                                        FROM mae_clien_unida_admin mcua
                                                       WHERE mcua.clie_oid_clie = tabla_difer_ua(i).clie_oid_clie AND
                                                             lnOidPeriodo >= mcua.perd_oid_peri_ini AND
                                                             mcua.perd_oid_peri_fin IS NULL
                                                      )
                         AND UA.ZTAD_OID_TERR_ADMI = tabla_difer_ua(i).ua_oid_terr_admi
                         AND CLIE_OID_CLIE         = tabla_difer_ua(i).clie_oid_clie ;

                  END;
                END LOOP;
            END IF;
         EXIT WHEN c_difer_ua%NOTFOUND;
         END LOOP;
         CLOSE c_difer_ua;*/

  -- Actualizamos Inconsistencias de líderes vigentes que aparecen en ZON_HISTO_GEREN y NO dentro de ZON_SECCI --

  OPEN c_difer_lider(lsCodigoPeriodo);
     LOOP
     FETCH c_difer_lider BULK COLLECT INTO tabla_difer_lider LIMIT W_FILAS;
        IF tabla_difer_lider.COUNT > 0 THEN
           FOR i IN tabla_difer_lider.FIRST .. tabla_difer_lider.LAST LOOP
              BEGIN
               UPDATE zon_secci S
                  SET clie_oid_clie =  tabla_difer_lider(i).oid_clie ,
                      usu_modi      =  lsUsuario
                WHERE oid_secc = tabla_difer_lider(i).oid_secc  ;
              END;
            END LOOP;
        END IF;
     EXIT WHEN c_difer_lider%NOTFOUND;
     END LOOP;
  CLOSE c_difer_lider;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'MAE_PR_ACTUA_DATOS_CLIEN: '||ls_sqlerrm);

END MAE_PR_ACTUA_DATOS_CLIEN;

/**************************************************************************
Descripcion       : Ejecuta las validaciones de cliente
Fecha Creacion    : 11/08/2011
Fecha Modificacion: 27/11/2014
Parametros Entrada:
  psCodigoPais          :   Codigo Pais
  psCodigoPeriodo       :   Campanha Facturacion
  psFechaFacturacion    :   Fecha Facturacion
  psUsuario             :   Usuario
  psIndTipoValid        :   Tipo Validacion
Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_VALID_INFOR_CLIEN
  (
   psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psFechaFacturacion        VARCHAR2,
   psUsuario                 VARCHAR2,
   psIndTipoValid            VARCHAR2,
   psIndTipoPrev             VARCHAR2,
   psEntorno                 VARCHAR2
  )
IS


  -- Se obtienen validaciones activas --
  CURSOR c_validaciones IS
     SELECT num_vali
       FROM mae_valid_clien_param
      WHERE pais_cod_pais = psCodigoPais
        AND (tip_vali = psIndTipoValid OR tip_vali = '3')
        --AND tip_prev = psIndTipoPrev
        AND nvl(psIndTipoPrev, tip_prev) = tip_prev
        AND est_regi = '1'
      ORDER BY num_vali;


  lsCodigoValidacion         mae_valid_clien_param.num_vali%TYPE;

 --VARIABLES PARA VALIDACION ESTATUS
  TYPE cEstatus IS RECORD
 (
  cod_pais         VARCHAR2(3),
  cod_sist         VARCHAR2(10),
  num_vali         VARCHAR2(10),
  clie_oid_clie    mae_clien.oid_clie%TYPE,
  cod_clie         mae_clien.cod_clie%TYPE,
  cod_peri_mini    seg_perio_corpo.cod_peri%TYPE,
  cod_peri_maxi    seg_perio_corpo.cod_peri%TYPE,
  num_regi         NUMBER
 );

    TYPE tabla_estatus IS TABLE OF cEstatus;
--    rowEstatus cEstatus;
    tablaEstatus tabla_estatus;

    CURSOR c_Estatus is
            WITH TEMP AS
             (
              SELECT clhe.clie_oid_clie,
                     clie.cod_clie,
                     MIN(clhe.perd_oid_peri ) AS peri_min,
                     MAX(clhe.perd_oid_peri ) AS peri_max,
                     COUNT(1) AS registros
                FROM mae_clien_histo_estat clhe,
                     mae_clien clie
               WHERE clhe.clie_oid_clie = clie.oid_clie
                 AND clhe.perd_oid_peri_peri_fin IS NULL
               GROUP BY clhe.clie_oid_clie, clie.cod_clie
               HAVING COUNT(1) > 1
             )
             SELECT psCodigoPais AS cod_pais,
                    'MAE' AS cod_sist,
                    'MAE-VAL-10' AS num_vali,
                    temp.clie_oid_clie,
                    temp.cod_clie,
                    FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( peri_min ) AS COD_PERI_MINI,
                    FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( peri_max ) AS COD_PERI_MAXI,
                    Registros NUM_REGI
               FROM temp
              ORDER BY temp.peri_min;

--VARIABLES PARA VALIDACION HISTORICOS D EGERENTES
 TYPE chistGerentes IS RECORD
 (
  cod_pais        VARCHAR2(3),
  cod_sist        VARCHAR2(10),
  num_vali        VARCHAR2(10),
  oid_hist_gere   zon_histo_geren.oid_hist_gere%TYPE,
  cod_campa_desd  cra_perio.val_nomb_peri%TYPE,
  cod_campa_hast  cra_perio.val_nomb_peri%TYPE
 );

    TYPE tabla_hisgere IS TABLE OF chistGerentes;
    tablaHisGere tabla_hisgere;

    CURSOR c_histGerentes is
    WITH TEMP AS
       (
        SELECT hger.oid_hist_gere,
             hger.ind_desv_auto,
             hger.perd_oid_peri_desd,
             hger.fec_desd,
             perd1.fec_inic AS fec_inic_desd,
             perd1.fec_fina AS fec_fina_desd,
            hger.perd_oid_peri_hast,
             hger.fec_hast,
             perd2.fec_inic AS fec_inic_hast,
             perd2.fec_fina AS fec_fina_hast,
            CASE WHEN TRUNC(hger.fec_desd) >= TRUNC(perd1.fec_inic) AND TRUNC(hger.fec_desd) <= TRUNC(perd1.fec_fina) THEN 0 ELSE 1 END AS Ind_Err_Desd,
            CASE WHEN TRUNC(hger.fec_hast) >= TRUNC(perd2.fec_inic) AND TRUNC(hger.fec_hast) <= TRUNC(perd2.fec_fina) THEN 0 ELSE 1 END AS Ind_Err_Hast,
            sgd.cod_peri AS cod_camp_desd,
            sgh.cod_peri AS cod_camp_hast
       FROM zon_histo_geren hger,
            cra_perio perd1,
            cra_perio perd2,
            seg_perio_corpo sgd,
            seg_perio_corpo sgh
      WHERE hger.perd_oid_peri_desd = perd1.oid_peri(+)
        AND perd1.peri_oid_peri = sgd.oid_peri(+)
        AND hger.perd_oid_peri_hast = perd2.oid_peri(+)
         AND perd2.peri_oid_peri = sgh.oid_peri(+)
        AND LENGTH(TRIM(ua)) = 9
        AND (hger.perd_oid_peri_desd IS NOT NULL AND hger.perd_oid_peri_hast IS NOT NULL)
     )
       SELECT psCodigoPais AS cod_pais,
              'MAE' AS cod_sist,
              'MAE-VAL-11' AS num_val,
       temp.oid_hist_gere,
      temp.cod_camp_desd,
      temp.cod_camp_hast
     FROM temp
    WHERE ind_err_desd = 1 OR ind_err_hast = 1 ;

   lnOidPeriodo     cra_perio.oid_peri%TYPE;
   lnOidCamAnte1    cra_perio.oid_peri%TYPE;
   lnOidCamAnte2    cra_perio.oid_peri%TYPE;
   psCodigoPeriodoAnte seg_perio_corpo.cod_peri%TYPE;
   psCodigoPeriodoSig  seg_perio_corpo.cod_peri%TYPE;
   lnIdPais         seg_pais.oid_pais%TYPE;

   lnActivasFuenteReal  NUMBER:=0;
   lnActivasMAE NUMBER:=0;
   lsindicadorRE        bas_param_pais.val_para%TYPE; --indicador reversion estatus


BEGIN

  -- Calcular Periodo Anterior al actual

  psCodigoPeriodoAnte  := GEN_FN_CALCU_PERIO( psCodigoPeriodo, -1 );
  psCodigoPeriodoSig := GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 );
  lnOidCamAnte1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodoAnte );
  lnOidCamAnte2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( psCodigoPeriodo, -2 ) );
  lnOidPeriodo  := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );
  lnIdPais      := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_VALID_CLIEN';


  OPEN c_validaciones;
  LOOP
    FETCH c_validaciones INTO lsCodigoValidacion;
    EXIT WHEN c_validaciones%NOTFOUND;

    -- 1.Obtiene Consultoras con Documento Prinicipal duplicado --
    IF(lsCodigoValidacion = 'MAE-VAL-01') THEN
      INSERT INTO mae_valid_clien
         (
         pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-01' AS num_vali,
             mci.clie_oid_clie,
             mc.cod_clie,
             COUNT (1) NUM_DUPL
        FROM mae_clien_ident mci,
             mae_clien mc
       WHERE mci.val_iden_docu_prin = 1
         AND mci.clie_oid_clie = mc.oid_clie
       GROUP BY mci.clie_oid_clie,
             mc.cod_clie
      HAVING COUNT (1) > 1;
    END IF;

    -- 2.Obtiene Consultoras con Dirección Principal duplicado --
    IF(lsCodigoValidacion = 'MAE-VAL-02') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-02' AS num_vali,
             mcd.clie_oid_clie,
             mc.cod_clie,
             COUNT(1)
        FROM mae_clien_direc mcd,
             mae_clien mc
       WHERE mcd.ind_dire_ppal = 1
         AND mcd.ind_elim <> 1
         AND mcd.clie_oid_clie = mc.oid_clie
       GROUP BY mcd.clie_oid_clie, mc.cod_clie
      HAVING COUNT (1) > 1;
    END IF;

    -- 3.Obtiene Consultoras con UA duplicado --
    IF( lsCodigoValidacion = 'MAE-VAL-03' ) THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
          )
          SELECT psCodigoPais AS cod_pais,
                 'MAE' AS cod_sist,
                 'MAE-VAL-03' AS num_vali,
                 cuad.clie_oid_clie,
                 clie.cod_clie,
                 COUNT(*)
            FROM mae_clien_unida_admin cuad,
                 mae_clien clie
           WHERE cuad.clie_oid_clie = clie.oid_clie
             AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,lnOidPeriodo)
           GROUP BY clie.cod_clie, cuad.clie_oid_clie
          HAVING COUNT(*) > 1;
    END IF;

    -- 4. Obtiene consultoras con Comunicación Principal duplicada  --
    IF(lsCodigoValidacion = 'MAE-VAL-04') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-04' AS num_vali,
             mcc.clie_oid_clie,
             mc.cod_clie,
             COUNT (1)
        FROM mae_clien_comun mcc,
             mae_clien mc
       WHERE mcc.ind_comu_ppal = 1
         AND mcc.clie_oid_clie = mc.oid_clie
       GROUP BY mcc.clie_oid_clie, mc.cod_clie
      HAVING COUNT(1) > 1;
    END IF;

  -- 5. Obtiene Consultoras Duplas con Actividad en NULL --
 /* INSERT INTO mae_valid_clien(
            num_vali,
            clie_oid_clie,
            COD_CLIE
        )
    SELECT 'MAE-VAL-05' AS num_vali,
       mcda.clie_oid_clie,
       mc.cod_clie
      FROM mae_clien mc,
           mae_clien_datos_adici mcda,
           mae_clien_tipo_subti mcts
     WHERE mc.oid_clie = mcda.clie_oid_clie
       AND mcda.clie_oid_clie = mcts.clie_oid_clie
       AND mcts.ticl_oid_tipo_clie = 5
       AND mcts.sbti_oid_subt_clie = 6
       AND mcda.ind_acti is  NULL;*/


    -- 6. Obtiene Consultoras con Nivel de Riesgo en NULL --
    IF(lsCodigoValidacion = 'MAE-VAL-06') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-06' AS num_vali,
             mcda.clie_oid_clie,
             mc.cod_clie
        FROM mae_clien mc,
             mae_clien_datos_adici mcda,
             mae_clien_tipo_subti mcts
       WHERE mcda.clie_oid_clie = mc.oid_clie
         AND mcda.niri_oid_nive_ries IS NULL
         AND mcda.clie_oid_clie = mcts.clie_oid_clie
         AND mcts.ticl_oid_tipo_clie = 2;
    END IF;

    /*************************************************************************
    * 7. Verificación de estatus de consultoras entre ADICI e HISTO_ESTAT    *
    * Objetivo : Encontrar consultoras con diferencia de Estatus             *
    * Condiciones: - Se considera solo consultoras activas de ADICI          *
    *************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-07') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-07' AS num_vali,
             mcda.clie_oid_clie,
             mc.cod_clie
        FROM mae_clien_datos_adici mcda,
             mae_clien_histo_estat mche,
             mae_clien mc
       WHERE mcda.clie_oid_clie = mche.clie_oid_clie(+)
         AND mcda.clie_oid_clie = mc.oid_clie
         AND mcda.ind_acti = 1
         AND mche.perd_oid_peri_peri_fin IS NULL
         AND mcda.esta_oid_esta_clie <> mche.esta_oid_esta_clie;
    END IF;

    /*******************************************************************************
    * 8. Verificación de estatus de consultoras entre ADICI e HISTO_ESTAT       *
    * Objetivo : Encontrar estatus de consultoras con mas de 1 periodo_fin null *
    * Condiciones : - Se considera solo consultoras activas de ADICI            *
    *******************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-08') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
          )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-08' AS num_vali,
             mcda.clie_oid_clie,
             mc.cod_clie
        FROM mae_clien_datos_adici mcda,
             mae_clien_histo_estat mche,
             mae_clien mc,
             (
              SELECT mche.clie_oid_clie,
             COUNT(1) AS Registros
                FROM mae_clien_histo_estat mche
               WHERE mche.perd_oid_peri_peri_fin IS NULL
               GROUP BY mche.clie_oid_clie
               HAVING COUNT(1) > 1
             ) mche2
       WHERE mcda.clie_oid_clie = mche.clie_oid_clie(+)
         AND mcda.clie_oid_clie = mche2.clie_oid_clie
         AND mcda.clie_oid_clie = mc.oid_clie
         AND mcda.ind_acti = 1
         AND mche.perd_oid_peri_peri_fin IS NULL;
    END IF;

    /*********************************************************************
    * 9. Verifica estatus de consultoras                                 *
    * Objetivo : Verificar estatus de consultoras nuevas                 *
    * Condiciones : - Solo activas                                       *
    **********************************************************************/
    --indicador para considerar reversion de estatus por anulaciones

        BEGIN
             SELECT bpp.val_para
               INTO lsindicadorRE
               FROM bas_param_pais bpp
              WHERE bpp.cod_pais = pscodigopais
                AND bpp.cod_sist = 'MAE'
                AND bpp.nom_para = 'indReversionEstatus'
                AND bpp.ind_acti = '1';
             EXCEPTION
               WHEN NO_DATA_FOUND
               THEN lsindicadorRE := '0';
        END;

    IF(lsCodigoValidacion = 'MAE-VAL-09') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
          )
      SELECT cod_pais,
            cod_sist,
            num_vali,
             clie_oid_clie,
             cod_clie,
             data_error
        FROM (
              SELECT   psCodigoPais AS cod_pais,
                      'MAE' AS cod_sist,
                      'MAE-VAL-09' AS num_vali,
                     data.clie_oid_clie,
                     data.cod_clie,
                     CASE
                           WHEN data.esta_oid_esta_clie IS NULL AND data.num_camp_sin_pedi = 0 AND data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 0 AND data.ped_cmp_x2 = 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 1 AND data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 2 AND data.num_camp_sin_pedi = 0 AND data.ped_cmp_actual = 1 AND data.ped_cmp_x1 = 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 3 AND data.num_camp_sin_pedi = 0 AND data.ped_cmp_actual = 1 AND (data.ped_cmp_x1 + data.ped_cmp_x2) > 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 4 AND data.num_camp_sin_pedi = 1 AND data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 1 THEN 0
                           WHEN data.esta_oid_esta_clie = 5 AND data.num_camp_sin_pedi BETWEEN 3 AND 18 AND data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 5 AND data.num_camp_sin_pedi = 2 AND  data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 0 AND data.ped_cmp_x2 = 1 THEN 0
                           WHEN data.esta_oid_esta_clie = 6 AND data.num_camp_sin_pedi = 0 AND data.ped_cmp_actual = 1 AND data.ped_cmp_x1 = 0 AND data.ped_cmp_x2 = 0THEN 0
                           WHEN data.esta_oid_esta_clie = 7 AND data.num_camp_sin_pedi >= 18 AND data.ped_cmp_actual = 0 AND data.ped_cmp_x1 = 0 AND data.ped_cmp_x2 = 0 THEN 0
                           WHEN data.esta_oid_esta_clie = 8 AND data.num_camp_sin_pedi = 0 AND data.ped_cmp_actual = 1 AND data.ped_cmp_x1 = 0 THEN 0
                           ELSE 1
                     END AS data_error
                FROM (
                       SELECT clda.clie_oid_clie, clie.cod_clie, clda.esta_oid_esta_clie, clhe.esta_oid_esta_clie AS esta_oid_esta_clie_clhe, clda.num_camp_sin_pedi,
                              DECODE(pedx.clie_oid_clie,NULL,0,1) AS ped_cmp_actual, pedx.fec_fact AS fec_fact_actual,
                              DECODE(pedx1.clie_oid_clie,NULL,0,1) AS ped_cmp_x1, pedx1.fec_fact AS fec_fact_x1,
                              DECODE(pedx2.clie_oid_clie,NULL,0,1) AS ped_cmp_x2, pedx2.fec_fact AS fec_fact_x2,
                              uadm.oid_regi, uadm.cod_regi, DECODE(cier.oid_regi,NULL,0,1) AS cier_regi_actu, cier.fec_cier,
                              FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( ulti.perd_oid_peri ) AS Cmp_Ulti_Pedi
                         FROM mae_clien_datos_adici clda,
                              mae_clien clie,
                              mae_clien_prime_conta cprc,
                              mae_clien_tipo_subti ctsu,
                              (
                               SELECT Clie_Oid_Clie,
                                      MAX(Perd_Oid_Peri) AS Perd_Oid_Peri
                                 FROM PED_SOLIC_CABEC_ACUM2 SCA2
                                GROUP BY Clie_Oid_Clie
                              ) ulti,
                              ( -- Unidad administrativa por consultora --
                                SELECT cuad.clie_oid_clie, zorg.oid_regi, zzon.oid_zona, zscc.oid_secc, terr.oid_terr,
                                       zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr
                                  FROM mae_clien_unida_admin cuad,
                                       zon_terri_admin ztad,
                                       zon_secci zscc,
                                       zon_terri terr,
                                       zon_zona zzon,
                                       zon_regio zorg
                                 WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                                   AND ztad.zscc_oid_secc = zscc.oid_secc
                                   AND ztad.terr_oid_terr = terr.oid_terr
                                   AND zscc.zzon_oid_zona = zzon.oid_zona
                                   AND zzon.zorg_oid_regi = zorg.oid_regi
                                   -- Condicion adicional --
                                   AND cuad.ind_acti = 1
                              ) uadm,
                              ( -- Pedido en campaña actual --
                               select soca.clie_oid_clie, soca.fec_fact
                                 from ped_solic_cabec soca,
                                      ped_solic_cabec cons,
                                      ped_tipo_solic_pais tspa,
                                      ped_tipo_solic tsol
                                where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                  and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                  and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                  --
                                  and soca.perd_oid_peri = lnOidPeriodo
                                  and tsol.cod_tipo_soli = 'SOC'
                                  --and soca.esso_oid_esta_soli = 1
                                  and soca.ind_oc = 1
                                  and soca.grpr_oid_grup_proc = 5
                                  AND (psIndTipoValid <> 2  OR
                                        ( psIndTipoValid  = 2 AND
                                          (lsindicadorRE = 0 OR ( lsindicadorRE  >= 1 AND
                                            (cons.esso_oid_esta_soli <> 4
                                              OR (cons.esso_oid_esta_soli = 4 AND
                                                  0  < (SELECT COUNT(*)
                                                          FROM ped_solic_cabec sc,
                                                               ped_tipo_solic_pais tsp,
                                                               ped_tipo_solic ts
                                                         WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais AND
                                                               tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli AND
                                                               ts.cod_tipo_soli           IN ('SDAA','SDAN') AND
                                                               sc.fec_fact                IS NOT NULL        AND
                                                               sc.soca_oid_docu_refe      =  cons.oid_soli_cabe AND--oc anulada
                                                             --  sc.perd_oid_peri           =  cons.perd_oid_peri AND --campaña de oc anulada
                                                               sc.perd_oid_peri           >  lnOidPeriodo   AND
                                                               sc.clie_oid_clie           =  cons.clie_oid_clie

                                                        )
                                                  )
                                             )
                                        )
                                       )
                                       )
                                      )
                              ) pedx,
                              ( -- Pedido en campaña actual - 1
                               select soca.clie_oid_clie, soca.fec_fact
                                 from ped_solic_cabec soca,
                                      ped_solic_cabec cons,
                                      ped_tipo_solic_pais tspa,
                                      ped_tipo_solic tsol
                                where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                  and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                  and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                  --
                                  and soca.perd_oid_peri = lnOidCamAnte1
                                  and tsol.cod_tipo_soli = 'SOC'
                                  --and soca.esso_oid_esta_soli = 1
                                  and soca.ind_oc = 1
                                  and soca.grpr_oid_grup_proc = 5
                                  AND (lsindicadorRE = 0
                                       OR
                                       (lsindicadorRE >= 2
                                        AND (cons.esso_oid_esta_soli <> 4
                                              OR (cons.esso_oid_esta_soli = 4 AND
                                                  0  < (SELECT COUNT(*)
                                                          FROM ped_solic_cabec sc,
                                                               ped_tipo_solic_pais tsp,
                                                               ped_tipo_solic ts
                                                         WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais AND
                                                               tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli AND
                                                               ts.cod_tipo_soli           IN ('SDAA','SDAN') AND
                                                               sc.fec_fact                IS NOT NULL        AND
                                                               sc.soca_oid_docu_refe      =  cons.oid_soli_cabe AND--oc anulada
                                                             --  sc.perd_oid_peri           =  cons.perd_oid_peri AND --campaña de oc anulada
                                                               sc.perd_oid_peri           >  lnOidCamAnte1   AND
                                                               sc.clie_oid_clie           =  cons.clie_oid_clie

                                                        )
                                                  )
                                             )
                                        )
                                       )
                              ) pedx1,
                              ( -- Pedido en campaña actual - 2 --
                               select soca.clie_oid_clie, soca.fec_fact
                                 from ped_solic_cabec soca,
                                      ped_solic_cabec cons,
                                      ped_tipo_solic_pais tspa,
                                      ped_tipo_solic tsol
                                where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                  and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                  and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                  --
                                  and soca.perd_oid_peri = lnOidCamAnte2
                                  and tsol.cod_tipo_soli = 'SOC'
                                  --and soca.esso_oid_esta_soli = 1
                                  and soca.ind_oc = 1
                                  and soca.grpr_oid_grup_proc = 5
                                  AND (lsindicadorRE = 0
                                       OR
                                       (lsindicadorRE >= 3
                                        AND (cons.esso_oid_esta_soli <> 4
                                              OR (cons.esso_oid_esta_soli = 4 AND
                                                  0  < (SELECT COUNT(*)
                                                          FROM ped_solic_cabec sc,
                                                               ped_tipo_solic_pais tsp,
                                                               ped_tipo_solic ts
                                                         WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais AND
                                                               tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli AND
                                                               ts.cod_tipo_soli           IN ('SDAA','SDAN') AND
                                                               sc.fec_fact                IS NOT NULL        AND
                                                               sc.soca_oid_docu_refe      =  cons.oid_soli_cabe AND--oc anulada
                                                             --  sc.perd_oid_peri           =  cons.perd_oid_peri AND --campaña de oc anulada
                                                               sc.perd_oid_peri           >  lnOidCamAnte2   AND
                                                               sc.clie_oid_clie           =  cons.clie_oid_clie

                                                        )
                                                  )
                                             )
                                        )
                                       )
                              ) pedx2,
                              ( -- Cierres de regiones --
                                SELECT zorg.oid_regi, zorg.cod_regi, zorg.des_regi, coci.fec_cier
                                  FROM fac_contr_cierr coci,
                                       fac_tipos_cierr tcie,
                                       zon_regio zorg,
                                       zon_regio zorg2,
                                       zon_zona zzon,
                                       cra_perio perd
                                 WHERE coci.zzon_oid_zona = zzon.oid_zona(+)
                                   AND coci.perd_oid_peri = perd.oid_peri
                                   AND tcie.oid_tipo_cier(+) = coci.tcie_oid_tipo_cier
                                   AND zorg.oid_regi(+) = coci.zorg_oid_regi
                                   AND zzon.ZORG_OID_REGI = zorg2.OID_REGI(+)
                                   AND perd.oid_peri  IN (
                                                          SELECT perd.oid_peri
                                                            FROM seg_perio_corpo peri,
                                                                 cra_perio perd
                                                           WHERE peri.oid_peri = perd.peri_oid_peri
                                                             AND peri.cod_peri = psCodigoPeriodo
                                                         )
                                   AND tcie.oid_tipo_cier =1  ---1 = Region
                                 GROUP BY zorg.OID_REGI, zorg.cod_regi, zorg.des_regi, coci.fec_cier
                              ) cier,
                              (
                               SELECT clie_oid_clie, esta_oid_esta_clie
                                 FROM mae_clien_histo_estat clhe
                                WHERE perd_oid_peri_peri_fin IS NULL
                              ) clhe
                        WHERE clda.clie_oid_clie = clie.oid_clie
                          AND clda.clie_oid_clie = ctsu.clie_oid_clie
                          AND clda.clie_oid_clie = uadm.clie_oid_clie
                          AND clda.clie_oid_clie = ulti.clie_oid_clie(+)
                          AND clda.clie_oid_clie = pedx.clie_oid_clie(+)
                          AND clda.clie_oid_clie = pedx1.clie_oid_clie(+)
                          AND clda.clie_oid_clie = pedx2.clie_oid_clie(+)
                          AND clda.clie_oid_clie = clhe.clie_oid_clie(+)
                          AND clda.clie_oid_clie = cprc.clie_oid_clie(+)
                          AND uadm.oid_regi = cier.oid_regi(+)
                          AND clda.ind_acti = 1
                          AND ctsu.ticl_oid_tipo_clie = 2
                      ) data
                WHERE data.cier_regi_actu = 1
              )
        WHERE data_error = 1;
     END IF;

     /**************************************************************************************************
     * 10. Obtener lista de consultoras de Histórico de Estatus, con mas de 1 periodo_final en Nulo    *
     * Objetivo : Encontrar consultoras con mas de 1 campaña final de estatus en NULL                  *
     **************************************************************************************************/

        /* Generando informacion */
     IF(lsCodigoValidacion = 'MAE-VAL-10') THEN
         OPEN c_estatus;
         LOOP
         FETCH c_estatus BULK COLLECT INTO tablaEstatus LIMIT W_FILAS;
            IF tablaEstatus.COUNT > 0 THEN
               FOR i IN tablaEstatus.FIRST .. tablaEstatus.LAST LOOP
                  BEGIN
                    INSERT INTO mae_valid_clien
                       (
                        pais_cod_pais,
                        sist_cod_sist,
                        vali_num_vali,
                        val_etq1,
                        val_etq2,
                        val_etq3,
                        val_etq4,
                        num_regi)
                    VALUES
                       (tablaEstatus(i).cod_pais,
                        tablaEstatus(i).cod_sist,
                        tablaEstatus(i).num_vali,
                              tablaEstatus(i).clie_oid_clie,
                        tablaEstatus(i).cod_clie,
                        tablaEstatus(i).cod_peri_mini,
                        tablaEstatus(i).cod_peri_maxi,
                        tablaEstatus(i).num_regi);
                  EXCEPTION
                   WHEN OTHERS THEN
                      NULL;
                  END;
                END LOOP;
            END IF;
         EXIT WHEN c_estatus%NOTFOUND;
         END LOOP;
         CLOSE c_estatus;
     END IF;

     /**************************************************************************************************
     * 11. Obtener lista de lideres con fecha desde y fecha hasta fuera del rango del periodo que le   *
     *     corresponde                                                                                 *
     * Objetivo : Encontrar lideres con fechas inconsistentes                                          *
     * Condiciones: UA = 9 posiciones                                                                  *
     *              Periodo desde y hasta con datos                                                    *
     **************************************************************************************************/
       /* Generando informacion */
     IF(lsCodigoValidacion = 'MAE-VAL-11') THEN
         OPEN c_histGerentes;
         LOOP
         FETCH c_histGerentes BULK COLLECT INTO tablaHisGere LIMIT W_FILAS;
            IF tablaHisGere.COUNT > 0 THEN
               FOR i IN tablaHisGere.FIRST .. tablaHisGere.LAST LOOP
                 BEGIN
                   INSERT INTO mae_valid_clien
                      (
                       pais_cod_pais,
                       sist_cod_sist,
                       vali_num_vali,
                       val_etq1,
                       val_etq2,
                       val_etq3,
                       val_etq4
                      )
                   VALUES
                      (
                       tablaHisGere(i).cod_pais,
                       tablaHisGere(i).cod_sist,
                       tablaHisGere(i).num_vali,
                              tablaHisGere(i).oid_hist_gere,
                              tablaHisGere(i).cod_campa_desd,
                              tablaHisGere(i).cod_campa_hast,
                       tablaHisGere(i).oid_hist_gere
                      );
                  EXCEPTION
                   WHEN OTHERS THEN
                     NULL;
                  END;
                 END LOOP;
            END IF;
         EXIT WHEN c_histGerentes%NOTFOUND;
         END LOOP;
         CLOSE c_histGerentes;
     END IF;

     /*****************************************************************************************************
     * 12. Obtener Consultoras  donde su Campaña Primer Pedido es diferente a la Campaña Primer Contacto  *
     * Condiciones: - Solo Campaña Actual                                                                 *
     *              - Nuevas con pedido en campaña actual y con cierre de region                          *
     *****************************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-12') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
            'MAE' AS cod_sist,
            'MAE-VAL-12' AS num_vali,
              mp.clie_oid_clie,
              m.cod_clie
         FROM ped_solic_cabec x,
              ped_solic_cabec y,
              ped_tipo_solic_pais tspa,
              ped_tipo_solic tsol,
              (
              SELECT cuad.clie_oid_clie,
                     zorg.oid_regi
                       FROM mae_clien_unida_admin cuad,
                            zon_terri_admin       ztad,
                            zon_secci             zscc,
                            zon_terri             terr,
                            zon_zona              zzon,
                            zon_regio             zorg
                      WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                        AND ztad.zscc_oid_secc = zscc.oid_secc
                        AND ztad.terr_oid_terr = terr.oid_terr
                        AND zscc.zzon_oid_zona = zzon.oid_zona
                        AND zzon.zorg_oid_regi = zorg.oid_regi
                        AND cuad.ind_acti = 1
              ) uadm, -- Unidad administrativa por consultora
              (
               SELECT c.oid_peri, s.cod_peri
                       FROM seg_perio_corpo s, cra_perio c
                      WHERE s.oid_peri = c.peri_oid_peri
                        AND s.cod_peri = psCodigoPeriodo
              ) cam, -- campaña ingresada
              mae_clien m,
              mae_clien_datos_adici md,
              mae_clien_prime_conta mp
        WHERE x.soca_oid_soli_cabe = y.oid_soli_cabe
          AND x.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
          AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
          AND tsol.cod_tipo_soli = 'SOC'
          AND x.grpr_oid_grup_proc = 5
          AND x.fec_fact IS NOT NULL
          AND x.perd_oid_peri = cam.oid_peri
          AND x.clie_oid_clie = md.clie_oid_clie
          AND y.esso_oid_esta_soli <> 4
          AND m.oid_clie = uadm.clie_oid_clie
          AND uadm.oid_regi in
              (
               SELECT zorg.oid_regi
                 FROM fac_contr_cierr           coci,
                      fac_tipos_cierr tcie,
                      zon_regio                 zorg,
                      zon_regio                 zorg2,
                      zon_zona                  zzon,
                      cra_perio                 perd
                WHERE coci.zzon_oid_zona = zzon.oid_zona(+)
                  AND coci.perd_oid_peri = perd.oid_peri
                  AND tcie.oid_tipo_cier(+) = coci.tcie_oid_tipo_cier
                  AND zorg.oid_regi(+) = coci.zorg_oid_regi
                  AND zzon.zorg_oid_regi = zorg2.oid_regi(+)
                  AND perd.oid_peri = cam.oid_peri
                  AND tcie.oid_tipo_cier = 1 ---1 = Region
                GROUP BY zorg.OID_REGI, zorg.cod_regi, zorg.des_regi, coci.fec_cier
              )
          AND md.esta_oid_esta_clie = 1
          AND md.ind_acti = 1
          AND m.oid_clie = md.clie_oid_clie
          AND m.oid_clie = mp.clie_oid_clie
          AND mp.perd_oid_peri <> cam.oid_peri;
    END IF;

    /*************************************************************************
     * 13. Obtener Recomendantes que no estan registradas en Incentivos        *
     * Condiciones: - Campaña segun parametro                                 *
    *      c_recomendantes                                                    *
     *************************************************************************/

     lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    IF(lsCodigoValidacion = 'MAE-VAL-13') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
         )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-13',
             recte.clie_oid_clie_vnte,
                 (
              SELECT cod_clie
                FROM mae_clien
               WHERE oid_clie = recte.clie_oid_clie_vnte
             ) AS cod_clie,
                 recte.copa_oid_para_gral
            FROM (
              SELECT data.clie_oid_clie_vnte,clr3.clie_oid_clie, data.oid_para_gral AS copa_oid_para_gral
                    FROM (
                          SELECT cvin.clie_oid_clie_vnte,
                             zorg.oid_regi,zzon.oid_zona,
                             concurso.oid_para_gral,
                             concurso.oid_regi,concurso.oid_zona
                            FROM mae_clien_vincu cvin,
                                 mae_tipo_vincu tvin,
                             mae_clien_prime_conta cprc,
                                 cra_perio perd,
                                 (
                              SELECT zorg.oid_regi,zzon.oid_zona,copa.oid_para_gral,
                                     copa.perd_oid_peri_desd,
                                     copa.perd_oid_peri_hast ,
                                     copa.oid_peri
                                FROM inc_ambit_geogr terc,
                                     zon_regio zorg ,
                                     zon_zona zzon ,
                                     (
                                  SELECT copa.oid_para_gral,
                                         copa.perd_oid_peri_desd,
                                         copa.perd_oid_peri_hast,
                                         lnOidPeriodo AS oid_peri
                                    FROM inc_concu_param_gener copa
                                   WHERE copa.perd_oid_peri_desd <= lnOidPeriodo
                                     AND copa.perd_oid_peri_hast >= lnOidPeriodo
                                     AND copa.diri_oid_diri = 1 --es dirigido a la Consultora
                                     AND copa.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                                     AND copa.ind_acti = 1  --concurso activo
                                 ) copa
                               WHERE terc.copa_oid_para_gral(+) = copa.oid_para_gral
                                 AND  terc.zorg_oid_regi = zorg.oid_regi(+)
                                 AND  terc.zorg_oid_regi = zzon.zorg_oid_regi(+)
                                 AND  terc.zzon_oid_zona = zzon.oid_zona(+)
                             ) concurso,
                             mae_clien_unida_admin cuad,
                             zon_terri_admin       ztad,
                             zon_terri             terr,
                             zon_secci             zscc,
                             zon_zona              zzon,
                             zon_regio             zorg
                       WHERE cvin.clie_oid_clie_vnte = cuad.clie_oid_clie
                         AND cuad.ind_acti = '1'
                         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                         AND ztad.terr_oid_terr = terr.oid_terr
                         AND ztad.zscc_oid_secc = zscc.oid_secc
                         AND zscc.zzon_oid_zona = zzon.oid_zona
                         AND zzon.zorg_oid_regi = zorg.oid_regi
                         AND (concurso.oid_regi IS NULL OR
                              concurso.oid_regi = zorg.oid_regi)
                         AND (concurso.oid_zona IS NULL OR
                              concurso.oid_zona =  zzon.oid_zona)
                         AND cvin.tivc_oid_tipo_vinc = tvin.oid_tipo_vinc
                         AND cvin.clie_oid_clie_vndo = cprc.clie_oid_clie
                         AND cprc.perd_oid_peri = lnOidPeriodo
                         AND (perd.oid_peri >= concurso.perd_oid_peri_desd
                         AND perd.oid_peri <= concurso.perd_oid_peri_hast )
                             AND tvin.ind_reco = 1
                       GROUP BY cvin.clie_oid_clie_vnte, zorg.oid_regi,zzon.oid_zona,concurso.oid_para_gral,
                             concurso.oid_regi,concurso.oid_zona
                        ) data,
                        inc_clien_recte clr3
                  WHERE data.clie_oid_clie_vnte = clr3.clie_oid_clie(+)
                    AND data.oid_para_gral = clr3.copa_oid_para_gral(+)
                    AND clr3.clie_oid_clie IS NULL
                 ) recte;
     END IF;

     /*************************************************************************
     * 14. Obtener Recomendadas que no estan registradas en Incentivos        *
     * Condiciones: - Campaña segun parametro                                 *
     *************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-14') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
         )
      SELECT  psCodigoPais AS cod_pais,
              'MAE' AS cod_sist,
              'MAE-VAL-14' AS num_vali,
             recdo.clie_oid_clie_vndo,
              (
               SELECT z.cod_clie
                 FROM mae_clien z
               WHERE z.oid_clie = recdo.clie_oid_clie_vndo
              ) cod_clie,
             recdo.oid_para_gral
        FROM (
               SELECT data.clie_oid_clie_vnte ,
                      data.clie_oid_clie_vndo ,
                     clr3.oid_clie_rete ,
                     data.oid_para_gral,
                     clre.clie_oid_clie
                FROM (
                      SELECT cvin.clie_oid_clie_vnte, cvin.clie_oid_clie_vndo,
                             zorg1.oid_regi,zzon1.oid_zona,concurso.oid_para_gral,
                             concurso.oid_regi_conc,concurso.oid_zona_conc,oid_peri
                        FROM mae_clien_vincu cvin,
              mae_tipo_vincu tvin,
              mae_clien_prime_conta mpc,
              (
                              SELECT zorg.oid_regi AS oid_regi_conc, zzon.oid_zona as oid_zona_conc,
                                     copa.oid_para_gral,
                                     copa.perd_oid_peri_desd,
                                     copa.perd_oid_peri_hast ,
                                     copa.oid_peri
                                FROM inc_ambit_geogr terc,
                                     zon_regio zorg ,
                                     zon_zona zzon ,
                      (
                       SELECT copa.oid_para_gral,
                              copa.perd_oid_peri_desd,
                              copa.perd_oid_peri_hast,
                              lnOidPeriodo AS oid_peri
                         FROM inc_concu_param_gener copa
                        WHERE copa.perd_oid_peri_desd <= lnOidPeriodo
                          AND copa.perd_oid_peri_hast >= lnOidPeriodo
                          AND copa.diri_oid_diri = 1 --es dirigido a la Consultora
                                         AND copa.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                          AND copa.ind_acti = 1  --concurso activo
                                     ) copa
                               WHERE terc.copa_oid_para_gral(+) = copa.oid_para_gral
                                 AND terc.zorg_oid_regi = zorg.oid_regi(+)
                                 AND terc.zorg_oid_regi = zzon.zorg_oid_regi(+)
                                 AND terc.zzon_oid_zona = zzon.oid_zona(+)
                             ) concurso,
                             mae_clien_unida_admin cuad,
                             zon_terri_admin       ztad,
                             zon_terri             terr,
                             zon_secci             zscc,
                             zon_zona              zzon1,
                             zon_regio             zorg1
                       WHERE cvin.clie_oid_clie_vnte = cuad.clie_oid_clie
                         AND cuad.ind_acti = '1'
                         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                         AND ztad.terr_oid_terr = terr.oid_terr
                         AND ztad.zscc_oid_secc = zscc.oid_secc
                         AND zscc.zzon_oid_zona = zzon1.oid_zona
                         AND zzon1.zorg_oid_regi = zorg1.oid_regi
                         AND (concurso.oid_regi_conc IS NULL OR
                              concurso.oid_regi_conc = zorg1.oid_regi)
                         AND (concurso.oid_zona_conc IS NULL OR
                              concurso.oid_zona_conc =  zzon1.oid_zona)
                         AND tvin.ind_reco = 1
                         AND cvin.tivc_oid_tipo_vinc = tvin.oid_tipo_vinc
          AND tvin.ind_reco = 1
                         AND cvin.clie_oid_clie_vndo = mpc.clie_oid_clie
                         AND mpc.perd_oid_peri =  lnOidPeriodo
                     ) data,
                     inc_clien_recte clr3,
                     inc_clien_recdo clre
               WHERE data.clie_oid_clie_vnte = clr3.clie_oid_clie(+)
                 AND data.oid_para_gral = clr3.copa_oid_para_gral(+)
                 AND data.clie_oid_clie_vndo =clre.clie_oid_clie(+)
                 AND clre.clie_oid_clie IS NULL
             ) recdo ;
     END IF;

     /*********************************************************************************************
     * 15. Obtener Consultoras que pasaron pedidods y tienen Indicador Actividad igual a Inactivo *
     * Condiciones: - Campaña segun parametro                                                     *
     *********************************************************************************************/
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    IF(lsCodigoValidacion = 'MAE-VAL-15') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-15' AS num_vali,
              psc.clie_oid_clie,
              (
               SELECT z.cod_clie
                 FROM mae_clien z
                WHERE z.oid_clie = psc.clie_oid_clie
              ) cod_clie
         FROM ped_solic_cabec psc,
              mae_clien_datos_adici mcda,
              (
               SELECT cuad.clie_oid_clie,
                      zorg.oid_regi
                 FROM mae_clien_unida_admin cuad,
                      zon_terri_admin       ztad,
                      zon_secci             zscc,
                      zon_terri             terr,
                      zon_zona              zzon,
                      zon_regio             zorg
                WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                  AND ztad.zscc_oid_secc = zscc.oid_secc
                  AND ztad.terr_oid_terr = terr.oid_terr
                  AND zscc.zzon_oid_zona = zzon.oid_zona
                  AND zzon.zorg_oid_regi = zorg.oid_regi
                  AND cuad.ind_acti = 1
              ) uadm -- Unidad administrativa por consultora
        WHERE psc.clie_oid_clie = mcda.clie_oid_clie
          AND psc.clie_oid_clie   = uadm.clie_oid_clie
          AND psc.perd_oid_peri = lnOidPeriodo
          AND psc.ind_oc = 1
          AND psc.modu_oid_modu <> 15
          AND psc.fec_fact IS NOT NULL
          AND psc.ind_ts_no_conso = 1
          AND mcda.ind_acti       = 0
          AND EXISTS (SELECT NULL
                        FROM ped_solic_cabec sc,
                             ped_tipo_solic_pais tsp,
                             ped_tipo_solic ts
                       WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                         AND tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli
                         AND ts.cod_tipo_soli           = 'C1'
                         AND sc.fec_fact                IS NOT NULL
                         AND sc.oid_soli_cabe           = psc.soca_oid_soli_cabe
                         AND sc.esso_oid_esta_soli      <> 4
                     )
          AND EXISTS
              (
               SELECT NULL
                 FROM fac_contr_cierr           coci,
                      fac_tipos_cierr tcie,
                      zon_regio                 zorg,
                      zon_regio                 zorg2,
                      zon_zona                  zzon,
                      cra_perio                 perd
                WHERE coci.zzon_oid_zona     = zzon.oid_zona(+)
                  AND coci.perd_oid_peri     = perd.oid_peri
                  AND tcie.oid_tipo_cier(+)  = coci.tcie_oid_tipo_cier
                  AND zorg.oid_regi(+)       = coci.zorg_oid_regi
                  AND zzon.zorg_oid_regi     = zorg2.oid_regi(+)
                  AND perd.oid_peri          = lnOidPeriodo
                  AND tcie.oid_tipo_cier     = 1 ---1 = Region
                  AND uadm.oid_regi          = zorg.oid_regi
                GROUP BY zorg.OID_REGI, zorg.cod_regi, zorg.des_regi, coci.fec_cier
              );
    END IF;

      /***************************************************************************************************
    * 16. Verificar si en los pedidos, existen consultoras que no generaron registro de primer contacto
    *     en MAE_CLIEN_PRIME_CONTA.
     ****************************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-16') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-16' AS num_vali,
                 clie.oid_clie,
                 clie.cod_clie
          FROM ped_solic_cabec soca,
               ped_solic_cabec soca2,
               (
                SELECT sca2.clie_oid_clie,
                       MIN(sca2.perd_oid_peri) AS perd_oid_peri
                  FROM ped_solic_cabec_acum2 sca2
                 GROUP BY sca2.clie_oid_clie
               ) sca2,
               mae_clien_prime_conta cprc,
               gen_i18n_sicc_comun gen,
               mae_clien clie,
               mae_clien_datos_adici clda,
               (
                SELECT clhe.clie_oid_clie,
                       MAX(clhe.perd_oid_peri) AS perd_oid_peri
                  FROM mae_clien_histo_estat clhe
                 WHERE clhe.esta_oid_esta_clie IN (1,2,8)
                 GROUP BY clhe.clie_oid_clie
               ) clhe
         WHERE soca.perd_oid_peri = lnOidPeriodo
         AND soca.ind_oc = 1
         AND soca.modu_oid_modu NOT IN (13,15)
         AND gen.attr_enti = 'PED_ESTAD_SOLIC'
         AND cprc.clie_oid_clie IS NULL
         AND soca.clie_oid_clie = cprc.clie_oid_clie(+)
         AND soca.clie_oid_clie = sca2.clie_oid_clie(+)
         AND soca.clie_oid_clie = clhe.clie_oid_clie(+)
         AND soca.soca_oid_soli_cabe = soca2.oid_soli_cabe
         AND soca.clie_oid_clie = clie.oid_clie
         AND soca.clie_oid_clie = clda.clie_oid_clie
         AND soca.esso_oid_esta_soli = gen.val_oid;
    END IF;

    /*****************************************************************************************************
    * 17. Validación MAE
    *     Verifica las consultoras que tienen un Tipo de Documento que pertenecen solo a las Dupla Cyzone
    *****************************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-17') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT  psCodigoPais AS cod_pais,
              'MAE' AS cod_sist,
              'MAE-VAL-17' AS num_vali,
               clie.oid_clie,
              clie.cod_clie
          FROM mae_clien            clie,
               mae_clien_tipo_subti ctsu,
               mae_tipo_clien       ticl,
               mae_clien_ident      clid,
               mae_tipo_docum       tdoc
         WHERE clie.oid_clie = clid.clie_oid_clie
           AND clie.oid_clie = ctsu.clie_oid_clie
           AND clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
           AND ctsu.ticl_oid_tipo_clie = ticl.oid_tipo_clie
           AND clid.val_iden_docu_prin = 1
           AND ticl.cod_tipo_clie <> '10' -- Condición principal
           AND tdoc.cod_tipo_docu = '12'  -- Condición principal
             ;
    END IF;

    /*********************************************************************************
    * 18. Validación MAE
    *     Verifica las consultoras con territorios diferentes entre el pedido y su UA
    *     cuya UA ha sido modificada posterior al cierre de campaña
    **********************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-18') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-18',
               soca.clie_oid_clie,
               clie.cod_clie
          FROM ped_solic_cabec       soca,
               ped_tipo_solic_pais   tspa,
             Ped_tipo_solic        tsol,
               seg_perio_corpo       peri,
               cra_perio             perd,
               mae_clien_unida_admin cuad,
               zon_terri_admin       ztad,
               mae_clien             clie
         WHERE soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND tsol.oid_tipo_soli = tspa.tsol_oid_tipo_soli
         AND tsol.cod_tipo_soli = 'SOC'
           AND soca.clie_oid_clie = clie.oid_clie
           AND soca.perd_oid_peri = perd.oid_peri
           AND perd.peri_oid_peri = peri.oid_peri
            AND peri.cod_peri = psCodigoPeriodoAnte
           AND soca.clie_oid_clie = cuad.clie_oid_clie
            AND (perd.oid_peri >= cuad.perd_oid_peri_ini OR cuad.perd_oid_peri_ini IS NULL)
            AND cuad.perd_oid_peri_fin IS NULL
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND soca.terr_oid_terr <> ztad.terr_oid_terr
         AND TRUNC(cuad.fec_ulti_actu) > perd.fec_fina
           ;
    END IF;
    /*****************************************************************
    * 19. Consultoras nuevas no registradas en el Programa de Nuevas
    *****************************************************************/
    IF(lsCodigoValidacion = 'NVS-VAL-01') THEN
           INSERT INTO mae_valid_clien v
           (
            pais_cod_pais,
            sist_cod_sist,
            vali_num_vali,
            val_etq1,
            val_etq2,
            val_etq3,
            val_etq4,
            val_etq5
           )
             SELECT pscodigopais AS Cod_Pais,
                    'NVS' AS Cod_Sist,
                    'NVS-VAL-01' AS Num_Vali,
                    c.oid_clie,
                    c.cod_clie,
                    pnva_ua.cod_prog,
                    zorg.oid_regi,
                    zzon.oid_zona
               FROM mae_clien c,
                    mae_clien_tipo_subti mcts,
                    mae_tipo_clien mtc,
                    mae_subti_clien msc,
                    mae_clien_datos_adici mcda,
                    mae_clien_unida_admin cuad,
                    zon_terri_admin       ztad,
                    zon_secci             zscc,
                    zon_terri             terr,
                    zon_zona              zzon,
                    zon_regio             zorg,
                    (
                      SELECT  pnva.cod_pais,
                              pscodigoperiodo cod_peri,
                              pnva.cod_prog,
                              zr.cod_regi,
                              zz.cod_zona,
                              pnva.ind_prog_obli,
                              NULL
                         FROM cup_prog_nueva_cupon pnva,
                              zon_zona  zz,
                              zon_regio zr
                        WHERE zz.ind_acti = '1'
                          AND zz.ind_borr = '0'
                          AND zr.oid_regi = zz.zorg_oid_regi
                          AND 0 = (SELECT COUNT(1)
                                     FROM nvs_param_progr_unadm a
                                    WHERE a.pnvs_cod_prog = pnva.cod_prog
                                      AND a.pais_cod_pais = pnva.cod_pais
                                      AND a.est_regi = '1')
                          AND pnva.cam_inic <= psCodigoPeriodo
                          AND pnva.cam_fin  >= psCodigoPeriodo
                          AND pnva.cod_pais  = pscodigopais

                       UNION
                       SELECT pnva.cod_pais,
                              psCodigoPeriodo cod_peri,
                              pnva.cod_prog,
                              a.cod_regi,
                              zz.cod_zona,
                              pnva.ind_prog_obli,
                              NULL
                         FROM cup_prog_nueva_cupon pnva,
                              zon_zona              zz,
                              nvs_param_progr_unadm a
                        WHERE pnva.cod_prog         =a.pnvs_cod_prog
                          AND pnva.cod_pais         =a.pais_cod_pais
                          AND a.est_regi = '1'
                          AND a.cod_regi IS NOT NULL
                          AND a.cod_zona IS NULL
                          AND zz.zorg_oid_regi = a.zorg_oid_regi
                          AND zz.ind_acti = '1'
                          AND zz.ind_borr = '0'
                          AND pnva.cam_inic <= psCodigoPeriodo
                          AND pnva.cam_fin  >= psCodigoPeriodo
                          AND pnva.cod_pais  = pscodigopais

                           UNION
                           SELECT pnva.cod_pais,
                                  psCodigoPeriodo cod_peri,
                                  pnva.cod_prog,
                                  a.cod_regi,
                                  a.cod_zona,
                                  pnva.ind_prog_obli,
                                  NULL
                             FROM cup_prog_nueva_cupon pnva,
                                  nvs_param_progr_unadm a
                            WHERE pnva.cod_pais   = a.pais_cod_pais
                              AND pnva.cod_prog   = a.pnvs_cod_prog
                              AND a.est_regi = '1'
                              AND a.cod_regi IS NOT NULL
                              AND a.cod_zona IS NOT NULL
                              AND pnva.cam_inic <= psCodigoPeriodo
                              AND pnva.cam_fin  >= psCodigoPeriodo
                              AND pnva.cod_pais =  pscodigopais

                    ) pnva_ua,
                    int_solic_conso_cabec iscc
                  WHERE c.oid_clie              = mcda.clie_oid_clie
                    AND c.oid_clie              = cuad.clie_oid_clie
                    AND c.oid_clie              = mcts.clie_oid_clie
                    AND c.cod_clie              = iscc.cod_clie
                    AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                    AND mtc.cod_tipo_clie       = '02'
                    AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                    AND msc.cod_subt_clie       = '04'
                    AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                    AND ztad.zscc_oid_secc      = zscc.oid_secc
                    AND ztad.terr_oid_terr      = terr.oid_terr
                    AND zscc.zzon_oid_zona      = zzon.oid_zona
                    AND zzon.zorg_oid_regi      = zorg.oid_regi
                    AND cuad.ind_acti           = 1
                    AND zorg.cod_regi           = pnva_ua.cod_regi
                    AND zzon.cod_zona           = pnva_ua.cod_zona
                    AND iscc.ind_ocs_proc       = 1
                    AND ( ( pnva_ua.ind_prog_obli = 0
                          AND NOT EXISTS (SELECT NULL
                                            FROM cup_consu_nueva nva
                                           WHERE nva.cod_prog     = pnva_ua.cod_prog
                                             AND nva.cod_cons     = c.cod_clie
                                             AND nva.camp_ini_ccc = pnva_ua.cod_peri
                                         )
                          AND EXISTS ( SELECT NULL
                                            FROM int_solic_conso_cabec iscc
                                           WHERE iscc.cod_clie           = c.cod_clie
                                             AND iscc.esta_oid_esta_clie IN (1,7)
                                             AND iscc.ind_ocs_proc       = 1
                                             AND iscc.cod_peri           = pnva_ua.cod_peri
                                     )

                        )

                     OR ( pnva_ua.ind_prog_obli = 1
                           AND NOT EXISTS (SELECT NULL
                                             FROM cup_consu_nueva nva
                                            WHERE nva.cod_prog     = pnva_ua.cod_prog
                                              AND nva.cod_cons     = c.cod_clie
                                              AND nva.camp_ini_ccc = pnva_ua.cod_peri
                                           )
                           AND ( EXISTS ( SELECT NULL
                                            FROM int_solic_conso_cabec iscc
                                           WHERE iscc.cod_clie           = c.cod_clie
                                             AND iscc.esta_oid_esta_clie IN (1,7)
                                             AND iscc.ind_ocs_proc       = 1
                                             AND iscc.cod_peri           = pnva_ua.cod_peri
                                        )
                                 AND EXISTS (SELECT NULL
                                               FROM int_solic_conso_detal iscd,
                                                    lov_equiv_matr lem
                                              WHERE iscd.cod_clie = c.cod_clie
                                                AND iscd.cod_peri = pnva_ua.cod_peri
                                                AND lem.cod_pais  = iscd.cod_pais
                                                AND lem.cod_prog  = pnva_ua.cod_prog
                                                AND lem.cod_nivel = '01'
                                                AND lem.cod_peri  = iscd.cod_peri
                                                AND lem.cod_cupon = iscd.cod_vent
                                                AND iscc.num_lote = iscd.num_lote
                                               -- AND iscc.docu_num_docu = iscd.docu_num_docu
                                             )
                                )
                         )
                        );
    END IF;
    /***********************************************************
    * 20. No se parametrizó Kit en Programa de Nuevas Opcional *
    ************************************************************/
    IF(lsCodigoValidacion = 'NVS-VAL-02') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1
         )
      SELECT psCodigoPais AS cod_pais,
             'NVS' AS cod_sist,
             'NVS-VAL-02' AS num_vali,
             cp.cod_prog
            FROM cup_prog_nueva_cupon cp
           WHERE psCodigoPeriodo >= cp.cam_inic
           AND psCodigoPeriodo <= cp.cam_fin
           AND cp.ind_prog_obli = 1
         AND cp.cod_prog NOT IN (
                                 SELECT L.COD_PROG
                                       FROM lov_equiv_matr L
                                   WHERE l.cod_peri = psCodigoPeriodo
                                    AND l.cod_nivel = '01'
                                );
    END IF;
    /*********************************************************************
    * 21. No se parametrizó Kit en Programa de Nuevas cuando es Electivo *
    *********************************************************************/
    IF(lsCodigoValidacion = 'NVS-VAL-03') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
             'NVS' AS cod_sist,
             'NVS-VAL-03' AS num_vali,
             cl.oid_clie,
             cl.cod_clie
        FROM ped_solic_cabec pc,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic ts,
             mae_clien cl,
             ped_solic_posic pp,
             cup_prog_nueva_cupon cp,
             lov_equiv_matr L
       WHERE pc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
         AND ts.cod_tipo_soli = 'SOC'
         AND pc.perd_oid_peri   =
             (
              SELECT c.oid_peri
                 FROM cra_perio c,
                      seg_perio_corpo s
                 WHERE c.peri_oid_peri = s.oid_peri
                 AND s.cod_peri        = psCodigoPeriodo
             )
         AND pc.clie_oid_clie = cl.oid_clie
         AND psCodigoPeriodo >= cp.cam_inic
          AND   psCodigoPeriodo   <= cp.cam_fin
          AND   cp.ind_prog_obli = 1
         AND cp.cod_prog = l.cod_prog
         AND psCodigoPeriodo = l.cod_peri
          AND   l.cod_nivel = '01'
         AND pc.oid_soli_cabe = pp.soca_oid_soli_cabe
         AND pp.val_codi_vent = l.cod_venta
         AND EXISTS (
                     SELECT NULL
                       FROM mae_clien_tipo_subti mcts,
                            mae_subti_clien msc,
                            mae_tipo_clien mtc
                      WHERE mcts.clie_oid_clie = cl.oid_clie
                        AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                        AND mtc.cod_tipo_clie = '02'
                        AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
                        AND msc.cod_subt_clie = '04'
                  )
         AND cl.cod_clie NOT IN (
                                 SELECT cod_cons
                                   FROM cup_consu_nueva nv,
                                        cup_prog_nueva_cupon cpn
                WHERE nv.camp_ini_ccc = psCodigoPeriodo
                AND psCodigoPeriodo >= cpn.cam_inic
                AND psCodigoPeriodo   <= cpn.cam_fin
                                    AND nv.cod_prog = cp.cod_prog
                                    AND cpn.ind_prog_obli = 1
                                );
    END IF;
   /**************************************************************************
    * 22. Existen Zonas Asignadas a Diferentes Programas en la misma campaña *
    **************************************************************************/
    IF(lsCodigoValidacion = 'NVS-VAL-04') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
         )
       SELECT psCodigoPais AS cod_pais,
             'NVS' AS cod_sist,
             'NVS-VAL-04' AS num_vali,
              pnva_ua_val.cod_prog,
              pnva_ua_val.cod_regi,
              pnva_ua_val.cod_zona
         FROM (
                SELECT pnva_ua.cod_prog,
                       pnva_ua.cod_regi,
                       pnva_ua.cod_zona
                  FROM (
                        SELECT  pnva.cod_pais,
                                pscodigoperiodo,
                                pnva.cod_prog,
                                zr.cod_regi,
                                zz.cod_zona,
                                pnva.ind_prog_obli
                           FROM cup_prog_nueva_cupon pnva,
                                zon_zona  zz,
                                zon_regio zr
                          WHERE zz.ind_acti = '1'
                            AND zz.ind_borr = '0'
                            AND zr.oid_regi = zz.zorg_oid_regi
                            AND 0 = (SELECT COUNT(1)
                                       FROM nvs_param_progr_unadm a
                                      WHERE a.pnvs_cod_prog = pnva.cod_prog
                                        AND a.pais_cod_pais = pnva.cod_pais
                                        AND a.est_regi = '1')
                            AND pnva.cam_inic <= pscodigoperiodo
                            AND pnva.cam_fin  >= pscodigoperiodo
                            AND pnva.cod_pais = pscodigopais

                         UNION
                         SELECT pnva.cod_pais,
                                pscodigoperiodo,
                                pnva.cod_prog,
                                a.cod_regi,
                                zz.cod_zona,
                                pnva.ind_prog_obli
                           FROM cup_prog_nueva_cupon pnva,
                                zon_zona              zz,
                                nvs_param_progr_unadm a
                          WHERE pnva.cod_prog         =a.pnvs_cod_prog
                            AND pnva.cod_pais         =a.pais_cod_pais
                            AND a.est_regi = '1'
                            AND a.cod_regi IS NOT NULL
                            AND a.cod_zona IS NULL
                            AND zz.zorg_oid_regi = a.zorg_oid_regi
                            AND zz.ind_acti = '1'
                            AND zz.ind_borr = '0'
                            AND pnva.cam_inic <= pscodigoperiodo
                            AND pnva.cam_fin  >= pscodigoperiodo
                            AND pnva.cod_pais = pscodigopais
                         UNION
                         SELECT pnva.cod_pais,
                                pscodigoperiodo,
                                pnva.cod_prog,
                                a.cod_regi,
                                a.cod_zona,
                                pnva.ind_prog_obli
                           FROM cup_prog_nueva_cupon pnva,
                                nvs_param_progr_unadm a
                          WHERE pnva.cod_pais   = a.pais_cod_pais
                            AND pnva.cod_prog   = a.pnvs_cod_prog
                            AND a.est_regi      = '1'
                            AND a.cod_regi IS NOT NULL
                            AND a.cod_zona IS NOT NULL
                            AND pnva.cam_inic <= pscodigoperiodo
                            AND pnva.cam_fin  >= pscodigoperiodo
                            AND pnva.cod_pais  = pscodigopais
                      ) pnva_ua
                 GROUP BY  pnva_ua.cod_prog,
                           pnva_ua.cod_regi,
                           pnva_ua.cod_zona
                 HAVING COUNT(*)>1
              ) pnva_ua_val;
    END IF;

    /**************************************************************
    * 23. No se parametrizó Kit en Programa de Nuevas Obligatorio *
    ***************************************************************/
    IF(lsCodigoValidacion = 'NVS-VAL-05') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1
         )
      SELECT psCodigoPais AS cod_pais,
             'NVS' AS cod_sist,
             'NVS-VAL-05' AS num_vali,
             cp.cod_prog
            FROM cup_prog_nueva_cupon cp
           WHERE psCodigoPeriodo >= cp.cam_inic
           AND psCodigoPeriodo <= cp.cam_fin
           AND cp.ind_prog_obli = 0
         AND cp.cod_prog NOT IN (
                                 SELECT cdp.COD_PROG
                                   FROM cup_desp_prod cdp
                                  WHERE cdp.cod_peri = psCodigoPeriodo
                                    AND cdp.cod_nivel = '01'
                                );
    END IF;
    /**************************************************************************************************
    * 22. Validación INC
    *     Verifica si en los pedidos de la campaña existen premios sin referencia en Lote de Articulos
    ***************************************************************************************************/
    IF(lsCodigoValidacion = 'INC-VAL-01') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3
         )
          SELECT DISTINCT
             psCodigoPais AS cod_pais,
             'INC' AS cod_sist,
                 'INC-VAL-01' AS num_vali,
                 soca.copa_oid_para_gene AS clie_oid_clie,
                 TRIM(TO_CHAR(soca.num_prem)) AS cod_clie,
                 sopo.val_codi_vent_fict As num_dupl
            FROM ped_solic_cabec soca,
                 ped_solic_cabec soca2,
                 ped_solic_posic sopo,
                 inc_concu_param_gener copa
           WHERE soca.soca_oid_soli_cabe = soca2.oid_soli_cabe
             AND soca.oid_soli_cabe = sopo.soca_oid_soli_cabe
             AND soca.perd_oid_peri = lnOidPeriodo
             AND soca.copa_oid_para_gene IS NOT NULL
             AND soca.copa_oid_para_gene  = copa.oid_para_gral
             AND soca.tspa_oid_tipo_soli_pais IN
             (
               SELECT tsp.OID_TIPO_SOLI_PAIS
                 FROM PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
                 WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
                      AND sol.COD_TIPO_SOLI IN ('SINC','SIN')--PREMIOS NORMALES
                      AND tsp.PAIS_OID_PAIS = lnIdPais
              )
             AND copa.ind_acti = 1
             AND soca2.esso_oid_esta_soli <> 4
             AND NOT EXISTS(
                              SELECT copa.oid_para_gral,
                                     arlo.cod_vent_fict
                              FROM inc_concu_param_gener   copa,
                                   inc_versi_concu       veco,
                                   inc_param_gener_premi pagp,
                                   inc_param_nivel_premi panp,
                                   inc_premi_artic       prar,
                                   inc_lote_premi_artic  lopa,
                                   inc_artic_lote        arlo
                             WHERE copa.oid_para_gral = pagp.copa_oid_para_gral
                               AND copa.oid_para_gral = soca.copa_oid_para_gene
                               AND copa.oid_para_gral = veco.copa_oid_para_gral_orig(+)
                               AND pagp.oid_para_gene_prem = panp.pagp_oid_para_gene_prem(+)
                               AND panp.oid_para_nive_prem = prar.panp_oid_para_nive_prem(+)
                               AND prar.oid_prem_arti = lopa.prar_oid_prem_arti(+)
                               AND lopa.oid_lote_prem_arti = arlo.lopa_oid_lote_prem_arti(+)
                               AND arlo.cod_vent_fict = sopo.val_codi_vent_fict
                               AND copa.ind_acti = 1 --  1 activado , 2 registrado , 6 cerrado
                               AND ( veco.vico_oid_vige_conc IN (1, 2) )
                             UNION
                            SELECT copa.oid_para_gral,
                                   rarl.cod_vent_fict
                                FROM inc_concu_param_gener copa,
                                     inc_versi_concu       veco,
                                     inc_param_gener_premi pagp,
                                     inc_param_nivel_premi panp,
                                     inc_premi_artic       prar,
                                     inc_lote_premi_artic  lopa,
                                     inc_artic_lote        arlo,
                                     inc_reemp_artic_lote  rarl
                               WHERE copa.oid_para_gral = pagp.copa_oid_para_gral
                                 AND copa.oid_para_gral = soca.copa_oid_para_gene
                                 AND copa.oid_para_gral = veco.copa_oid_para_gral_orig(+)
                                 AND pagp.oid_para_gene_prem = panp.pagp_oid_para_gene_prem(+)
                                 AND panp.oid_para_nive_prem = prar.panp_oid_para_nive_prem(+)
                                 AND prar.oid_prem_arti = lopa.prar_oid_prem_arti(+)
                                 AND lopa.oid_lote_prem_arti = arlo.lopa_oid_lote_prem_arti(+)
                               AND arlo.oid_arti_lote = rarl.arlo_oid_arti_lote
                               AND rarl.cod_vent_fict = sopo.val_codi_vent_fict
                               AND copa.ind_acti = 1
                               AND ( veco.vico_oid_vige_conc IN (1, 2) )
                               );
    END IF;
    /*******************************************************
    * 23. UA diferente en Pedido y MAE en la misma campaña *
    *******************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-19') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3,
          val_etq4
         )
       SELECT psCodigoPais AS cod_pais,
              'MAE' AS cod_sist,
              'MAE-VAL-19' AS num_vali,
              p.clie_oid_clie,
              cl.cod_clie,
              p.ztad_oid_terr_admi,
              ua.ztad_oid_terr_admi
         FROM ped_solic_cabec p,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts,
              seg_perio_corpo pc,
              cra_perio cr,
              mae_clien_unida_admin ua,
              zon_terri_admin za,
              mae_clien cl
        WHERE p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
          AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
          AND ts.cod_tipo_soli = 'SOC'
          and p.clie_oid_clie = cl.oid_clie
          AND p.perd_oid_peri = cr.oid_peri
          and cr.peri_oid_peri = pc.oid_peri
          and pc.cod_peri = psCodigoPeriodo
          and p.clie_oid_clie = ua.clie_oid_clie
          and cr.oid_peri >= ua.perd_oid_peri_ini
          and (cr.oid_peri <= ua.perd_oid_peri_fin OR ua.perd_oid_peri_fin IS NULL)
          and ua.perd_oid_peri_fin IS NULL
          and ua.ztad_oid_terr_admi = za.oid_terr_admi
          and p.terr_oid_terr <> za.terr_oid_terr;
    END IF;
    /************************************************************
    * 24.Obtiene Consultoras con Documento Prinicipal duplicado *
    *************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-20') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-20' AS num_vali,
             mc.oid_clie,
             mc.cod_clie
        FROM ped_solic_cabec sc,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic ts,
             seg_perio_corpo pc,
             cra_perio cp,
             mae_clien mc,
             mae_clien_datos_adici mcda,
             zon_zona zon,
             fac_contr_cierr fcc
       WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
         AND sc.clie_oid_clie = mc.oid_clie
         AND sc.perd_oid_peri = cp.oid_peri
         AND cp.peri_oid_peri = pc.oid_peri
         AND mc.oid_clie = mcda.clie_oid_clie
         AND sc.zzon_oid_zona = zon.oid_zona
         AND zon.zorg_oid_regi = fcc.zorg_oid_regi
         AND fcc.perd_oid_peri = sc.perd_oid_peri
         --
         AND ts.cod_tipo_soli = 'SOC'
         AND pc.cod_peri = psCodigoPeriodo
         AND mcda.esta_oid_esta_clie = 1 -- Registrada
         AND fcc.tcie_oid_tipo_cier = 1 -- Cierre Region
         AND sc.fec_fact IS NOT NULL
       GROUP BY mc.oid_clie,
                mc.cod_clie;
    END IF;
    /*******************************************************
    * 25. Clientes sin una UA activo *
    *******************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-21') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2,
          val_etq3,
          val_etq4,
          val_etq5
         )
          SELECT
                  psCodigoPais AS cod_pais
                  ,'MAE' AS cod_sist
                  ,'MAE-VAL-21' AS num_vali
                  ,cli.oid_clie
                  ,cli.cod_clie
                  ,mcda.esta_oid_esta_clie
                  ,mcua.usu_modi
                  ,mcua.fec_ulti_actu
           FROM
                 (SELECT c.oid_clie,c.cod_clie, (select count(1) from mae_clien_unida_admin where clie_oid_clie = c.oid_clie
                          and perd_oid_peri_ini = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,1))
                          and ind_acti = 0) contador
                     FROM mae_clien c
                     WHERE c.oid_clie in (select clie_oid_clie from mae_clien_unida_admin group by clie_oid_clie having sum(ind_acti)=0)
                  ) cli,
                  mae_clien_unida_admin mcua,
                  mae_clien_tipo_subti mcts,
                  mae_clien_datos_adici mcda
         WHERE    cli.oid_clie              = mcts.clie_oid_clie AND
                  mcua.clie_oid_clie      = mcts.clie_oid_clie AND
                  mcda.clie_oid_clie      = mcua.clie_oid_clie AND
                  contador = 0;
    END IF;

    /*******************************************************
    * 25. Clientes con pedido actual  sin una UA activo *
    *******************************************************/
    /*
    INSERT INTO mae_valid_clien
       (
        pais_cod_pais,
        sist_cod_sist,
        vali_num_vali,
        val_etq1,
        val_etq2,
        val_etq3,
        val_etq4,
        val_etq5
       )
        SELECT
               psCodigoPais AS cod_pais
              ,'MAE' AS cod_sist
              ,'MAE-VAL-21' AS num_vali
              ,c.oid_clie
              ,c.cod_clie
              ,mcda.esta_oid_esta_clie
              ,mcua.usu_modi
              ,mcua.fec_ulti_actu
          FROM mae_clien c,
               mae_clien_unida_admin mcua,
               mae_clien_tipo_subti mcts,
               mae_clien_datos_adici mcda
         WHERE c.oid_clie              = mcts.clie_oid_clie AND
               mcua.clie_oid_clie      = mcts.clie_oid_clie AND
               mcts.ticl_oid_tipo_clie = 2 AND
               mcda.clie_oid_clie      = mcua.clie_oid_clie AND
               mcda.ind_acti           = 1 AND
               lnOidPeriodo           BETWEEN mcua.perd_oid_peri_ini AND nvl(mcua.perd_oid_peri_fin,lnOidPeriodo) AND
               mcua.ind_acti          = 0 ;

    */

    /*******************************************
    * 26. Clientes con pedido actual sin Marca *
    ********************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-22') THEN
      INSERT INTO mae_valid_clien
         (
          pais_cod_pais,
          sist_cod_sist,
          vali_num_vali,
          val_etq1,
          val_etq2
         )
          SELECT
                 psCodigoPais AS cod_pais
                ,'MAE' AS cod_sist
                ,'MAE-VAL-22' AS num_vali
                ,c.oid_clie
                ,c.cod_clie
            FROM mae_clien c
                ,ped_solic_cabec sc
                ,ped_tipo_solic_pais tsp
                ,ped_tipo_solic ts
           WHERE c.oid_clie                 = sc.clie_oid_clie
             AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND ts.oid_tipo_soli           = tsp.tsol_oid_tipo_soli
             AND ts.cod_tipo_soli           = 'SOC'
             AND sc.perd_oid_peri           = lnOidPeriodo
             AND NOT EXISTS (SELECT NULL
                               FROM mae_clien_marca cm
                              WHERE cm.clie_oid_clie = c.oid_clie
                             );
    END IF;
    /****************************************
        * 27. Clientes sin documento principal *
     ****************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-23') THEN
        INSERT INTO mae_valid_clien
           (
            pais_cod_pais,
            sist_cod_sist,
            vali_num_vali,
            val_etq1,
            val_etq2
           )
            SELECT psCodigoPais AS cod_pais
                  ,'MAE'        AS cod_sist
                  ,'MAE-VAL-23' AS num_vali
                  ,cli.oid_clie
                  ,cli.cod_clie
              FROM mae_clien cli
                  ,mae_clien_datos_adici cda
             WHERE  cli.oid_clie = cda.clie_oid_clie
                AND cda.esta_oid_esta_clie NOT IN (1,7)
                AND NOT EXISTS (SELECT NULL
                                 FROM mae_clien_ident iden
                                WHERE cli.oid_clie = iden.clie_oid_clie
                                  AND iden.val_iden_docu_prin = 1
                               );
    END IF;
    /*********************************************************************************
     * 28. MAE-VAL-26 - Validación de Número de Activas Finales al Cierre de Campaña *
     *********************************************************************************/
IF psIndTipoValid = 2 THEN
   BEGIN
    SELECT SUM ( FV.NUM_ACTI_FINA ) INTO lnActivasFuenteReal
      FROM INT_FUENT_VENTAS_REAL FV
    WHERE FV.PERD_OID_PERI = ( SELECT OID_PERI
                                 FROM CRA_PERIO
                                WHERE VAL_NOMB_PERI LIKE '%' || psCodigoPeriodo || '%' );
   EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lnActivasFuenteReal := 0;
   END;

   BEGIN
    SELECT COUNT(*) INTO lnActivasMAE
      FROM mae_clien_datos_adici ad
     WHERE AD.ESTA_OID_ESTA_CLIE IN (2,3,4,6,8);
    EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lnActivasMAE := 0;
   END;

    IF lnActivasFuenteReal <> lnActivasMAE THEN
       INSERT INTO mae_valid_clien
       ( pais_cod_pais,
         sist_cod_sist,
         vali_num_vali,
         val_etq1,
         val_etq2
        )
        VALUES
        ( psCodigoPais,
          'MAE',
          'MAE-VAL-26',
          lnActivasFuenteReal,
          lnActivasMAE
        );

    END IF;
END IF;

    /**************************************************************************
     * 28. MSG-VAL-01 - Validación para obtener si existen patrones inactivos *
     **************************************************************************/
    IF(lsCodigoValidacion = 'MSG-VAL-01') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MSG' AS cod_sist,
             'MSG-VAL-01' AS num_vali,
             mp.oid_patr,
             mp.cod_patr,
             mp.des_patr,
             mp.ind_acti
        FROM msg_patro mp,
             msg_patro_perio mpp
       WHERE SUBSTR(mp.cod_patr,1,2) = 'un'
         AND mp.ind_acti = '0'
         AND mp.oid_patr = mpp.patr_oid_patr(+);
    END IF;
    /**********************************************************************************
     * 29. PED-VAL-01 - Verificación de ejecución de procesos de cdrs sin facturación *
     **********************************************************************************/
    IF(lsCodigoValidacion = 'PED-VAL-01') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4,
       val_etq5
      )
      SELECT psCodigoPais cod_pais,
             'PED' cod_sist,
             'PED-VAL-01' num_vali,
             TO_CHAR(soc.fec_fact,'DD/MM/YYYY') FechaFacturacion,
             SUM(CASE
                   WHEN pts.num_unid_vend = 1 OR pts.num_unid_falt = 1 THEN
                    1
                   ELSE
                    0
                 END) Ventas,
             SUM(CASE
                   WHEN pts.num_unid_anul = 1 THEN
                    1
                   ELSE
                    0
                 END) Anulaciones,
             SUM(CASE
                   WHEN pts.num_unid_devu = 1 THEN
                    1
                   ELSE
                    0
                 END) Devoluciones,
             SUM(CASE
                   WHEN (pts.num_unid_canj = 1 or pts.num_unid_true = 1) THEN
                    1
                   ELSE
                    0
                 END) CanjTrueq
        FROM ped_solic_cabec      soc,
             ped_tipo_solic_pais  tsp,
             cra_perio            pe,
             seg_perio_corpo      pc,
             int_param_tipo_solic pts,
             bas_ctrl_fact bas
       WHERE soc.tspa_oid_tipo_soli_pais = pts.tspa_oid_tipo_soli_pais
         AND fec_fact IS NOT NULL
         AND soc.perd_oid_peri = pe.oid_peri(+)
         AND pe.peri_oid_peri = pc.oid_peri(+)
         AND pts.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND fec_fact BETWEEN fec_inic and fec_fina
         AND pc.cod_peri IN ( psCodigoPeriodo, psCodigoPeriodoSig )
         AND ( pts.num_unid_vend = 1 OR pts.num_unid_falt = 1 OR
               pts.num_unid_anul = 1 OR pts.num_unid_devu = 1 OR
               pts.num_unid_canj = 1 OR pts.num_unid_true = 1 )
         AND fec_fact NOT IN
             (SELECT DISTINCT fec_fact
                FROM int_venta_diari_total
               WHERE pc.cod_peri IN ( psCodigoPeriodo, psCodigoPeriodoSig ) )
         AND bas.cod_peri = psCodigoPeriodo
         AND fec_fact < bas.fec_proc
       GROUP BY soc.fec_fact, pc.cod_peri;
    END IF;

     /********************************************************************
     * 30. MAE-VAL-27 - Verificación de Fechas Inicio y Fin de Campañas  *
     ********************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-27') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4,
       val_etq5
      )
      WITH TEMP AS
      (
       SELECT psCodigoPais cod_pais,
              'MAE' cod_sist,
              'MAE-VAL-27' num_vali,
              peri.cod_peri,
              perd.fec_inic ,
              perd.fec_fina,
              perd.ind_peri_cruc,
              (
               SELECT b.cod_peri
                 FROM cra_perio a,
                      seg_perio_corpo b
                WHERE a.peri_oid_peri = b.oid_peri
                  AND b.cod_peri = FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE( peri.cod_peri, 1 )
              ) cod_peri_ante,
              (
               SELECT a.fec_inic
                 FROM cra_perio a,
                      seg_perio_corpo b
                WHERE a.peri_oid_peri = b.oid_peri
                  AND b.cod_peri = FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE( peri.cod_peri, 1 )
              ) fec_inic_ante,
              (
               SELECT a.fec_fina
                 FROM cra_perio a,
                      seg_perio_corpo b
                WHERE a.peri_oid_peri = b.oid_peri
                  AND b.cod_peri = FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE( peri.cod_peri, 1 )
              ) fec_fina_ante
         FROM cra_perio perd,
              seg_perio_corpo peri
        WHERE perd.peri_oid_peri = peri.oid_peri
          AND peri.cod_peri >=  FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE(psCodigoPeriodo, 1 )
        ORDER BY peri.cod_peri DESC
      )
      SELECT cod_pais,
             cod_sist,
             num_vali,
             cod_peri,
             TO_CHAR(fec_inic,'dd/mm/yyyy'),
             cod_peri_ante,
             TO_CHAR(fec_fina_ante,'dd/mm/yyyy'),
             ind_peri_cruc
        FROM temp
       WHERE CASE
                WHEN temp.fec_inic - temp.fec_fina_ante < 0 or temp.fec_inic - temp.fec_fina_ante > 1 THEN 1
                ELSE 0
             END = 1;
    END IF;

    /**************************************************************************
     * 31. MAE-VAL-28 - Validación para obtener Clientes que tienen el mismo
     *                  OID Tipo Subtipo en entidad Primer Contacto           *
     **************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-28') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-28' AS num_vali,
             mc.oid_clie,
             mc.cod_clie,
             mcts.oid_clie_tipo_subt,
             mcpc.ctsu_clie_cont
        FROM mae_clien_prime_conta mcpc,
             mae_clien_tipo_subti mcts,
             mae_clien mc
       WHERE mcts.clie_oid_clie = mcpc.clie_oid_clie
         AND mc.oid_clie = mcpc.clie_oid_clie
         AND mcpc.ctsu_clie_cont IN (
                                     SELECT ctsu_clie_cont
                                       FROM mae_clien_prime_conta
                                      GROUP BY ctsu_clie_cont
                                     HAVING COUNT(ctsu_clie_cont) > 1
                                        AND ctsu_clie_cont NOT IN (99999,9999999,199999,299999,999999,461343,1,66279)
                                    )
                                AND NOT EXISTS (
                                                select PA.CTSU_CLIE_CONT
                                                from MAE_CLIEN_TIPO_SUBTI SB
                                                LEFT JOIN MAE_CLIEN_PRIME_CONTA PA ON PA.CLIE_OID_CLIE=SB.CLIE_OID_CLIE
                                                where TICL_OID_TIPO_CLIE=5
                                                  and pa.ctsu_clie_cont = mcpc.ctsu_clie_cont);

     END IF;
     /*****************************************************************************
     * 32. MAE-VAL-29 - Validación para obtener Clientes con diferente territorio *
     *                  en Unidad Administrativa y Cliente Direccion              *
     ******************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-29') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-29' AS num_vali,
             mcd.clie_oid_clie,
             mc.cod_clie,
             zta.terr_oid_terr,
             mcd.terr_oid_terr
        FROM mae_clien_unida_admin mcua,
             mae_clien_direc mcd,
             mae_clien mc,
             zon_terri_admin zta,
             mae_clien_datos_adici adi
       WHERE mcd.clie_oid_clie = mcua.clie_oid_clie
         AND mcd.clie_oid_clie = mc.oid_clie
         AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND adi.clie_oid_clie=mc.oid_clie
         AND mcua.perd_oid_peri_fin is null
         --AND mcua.ind_acti = 1
         AND mcd.ind_dire_ppal = 1
         AND mcd.ind_elim = 0
         AND zta.ind_borr = 0
         and mcua.ind_acti <> 0 -- descarto las ua futuras
         and adi.esta_oid_esta_clie not in (7,5)
         AND zta.terr_oid_terr <> mcd.terr_oid_terr ;
   END IF;

  /*****************************************************************************
     * 33. MAE-VAL-30 - Validación para obtener Clientes con clasificación Antigua y Nueva *
     ******************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-30') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
            'MAE' AS cod_sist,
            'MAE-VAL-30' AS num_vali,
             mc.oid_clie,
             mc.cod_clie,
             adi.esta_oid_esta_clie,
             seg.cod_peri
       FROM mae_clien mc,
            mae_clien_tipo_subti mcts,
            mae_clien_clasi mcc,
            mae_clien_datos_adici adi,
            mae_clien_prime_conta pri,
            cra_perio cpe,
            seg_perio_corpo seg
       WHERE mc.oid_clie= mcts.clie_oid_clie
         AND mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
         AND mc.oid_clie=adi.clie_oid_clie
         AND pri.clie_oid_clie=mc.oid_clie
         AND cpe.oid_peri=pri.perd_oid_peri
         AND seg.oid_peri=cpe.peri_oid_peri
         AND (mcc.tccl_oid_tipo_clasi=2010)
         AND mc.oid_clie IN
         (
           SELECT mc.oid_clie
           FROM mae_clien mc
              ,mae_clien_tipo_subti mcts
              ,mae_clien_clasi mcc
              ,mae_clien_datos_adici adi
              ,MAE_ESTAT_CLIEN est
           WHERE mc.oid_clie             = mcts.clie_oid_clie
             AND mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
             AND adi.clie_oid_clie=mc.oid_clie
             AND est.cod_esta_clie IN ('03', '04', '05', '06')
             AND (mcc.tccl_oid_tipo_clasi=2011)
             and adi.esta_oid_esta_clie not in (7));
    END IF;
    /*****************************************************************************
     * 34. MAE-VAL-31 - Validación para obtener Clientes con bloqueo repetido sin fecha de desbloqueo *
     ******************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-31') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-31' AS num_vali,
             clbl.clie_oid_clie ,
             (select z.cod_clie from mae_clien z where z.oid_clie=clbl.clie_oid_clie) as cod_clie,
             tibq.cod_tipo_bloq,
             x.val_i18n
        FROM mae_clien_bloqu clbl,
             mae_tipo_bloqu tibq,
             gen_i18n_sicc_comun x
      WHERE clbl.tibq_oid_tipo_bloq = tibq.oid_tipo_bloq
         AND tibq.oid_tipo_bloq = x.val_oid
         AND x.attr_enti = 'MAE_TIPO_BLOQU'
         AND clbl.fec_desb IS NULL
         AND clbl.clie_oid_clie IN (
                 SELECT clbl.clie_oid_clie
                  FROM mae_clien_bloqu clbl,
                       mae_tipo_bloqu tibq,
                       mae_accio_proce_bloqu mpbl,
                       mae_accio_bloqu mabl,
                       mae_proce_bloqu mapb,
                       gen_i18n_sicc_comun x
                 WHERE clbl.tibq_oid_tipo_bloq = tibq.oid_tipo_bloq
                   AND tibq.oid_tipo_bloq = mpbl.tibq_oid_tipo_bloq
                   AND mpbl.mabl_oid_acci_bloq = mabl.oid_acci_bloq
                   AND mpbl.mapb_oid_proc_bloq = mapb.oid_proc_bloq
                   AND tibq.oid_tipo_bloq = x.val_oid
                   AND mapb.cod_proc_bloq = 'FA'
                   AND mabl.cod_acci_bloq = 'FN'
                   AND x.attr_enti = 'MAE_TIPO_BLOQU'
                   AND clbl.fec_desb IS NULL
                   AND tibq.num_nive_grav_bloq IN (
                                                   SELECT MIN(tibq2.num_nive_grav_bloq)
                                                     FROM mae_clien_bloqu clbl2,
                                                          mae_tipo_bloqu tibq2,
                                                          mae_accio_proce_bloqu mpbl2,
                                                          mae_accio_bloqu mabl2,
                                                          mae_proce_bloqu mapb2,
                                                          gen_i18n_sicc_comun x2
                                                    WHERE clbl2.tibq_oid_tipo_bloq = tibq2.oid_tipo_bloq
                                                      AND tibq2.oid_tipo_bloq = mpbl2.tibq_oid_tipo_bloq
                                                      AND mpbl2.mabl_oid_acci_bloq = mabl2.oid_acci_bloq
                                                      AND mpbl2.mapb_oid_proc_bloq = mapb2.oid_proc_bloq
                                                      AND tibq2.oid_tipo_bloq = x2.val_oid
                                                      AND clbl2.clie_oid_clie = clbl.clie_oid_clie
                                                      AND mapb2.cod_proc_bloq = 'FA'
                                                      AND mabl2.cod_acci_bloq = 'FN'
                                                      AND x2.attr_enti = 'MAE_TIPO_BLOQU'
                                                      AND clbl2.fec_desb IS NULL
                                                 )
                   group by clbl.clie_oid_clie,clbl.tibq_oid_Tipo_bloq,to_char(fec_bloq,'dd/mm/yyyy')
                   having  count(clbl.clie_oid_clie) >1
                   );
     END IF;
     /******************************************************************************************
     * 33. MAE-VAL-32 - Validación para obtener Clientes con dos o mas clasificaciones iguales *
     *                  en la tabla mae_clien_clasi para diferentes ctsu_oid_clie_tipo_subt    *
     *******************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-32') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-32' AS num_vali
            ,che.clie_oid_clie
            ,ma.cod_clie
            ,cc.clas_oid_clas
            ,count(*) as repetidos
        FROM mae_clien_clasi cc
            ,mae_clien_tipo_subti cts
            ,mae_clien_histo_estat che
            ,mae_clien ma
       WHERE cc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
         AND che.clie_oid_clie          =  cts.clie_oid_clie
         AND ma.oid_clie=che.clie_oid_clie
         AND che.perd_oid_peri_peri_fin IS NULL
         group by che.clie_oid_clie,che.oid_hist_esta
            ,cc.clas_oid_clas,ma.cod_clie
        having count(*)>1;
    END IF;

    /*****************************************************************************
     * 34. MAV-VAL-01 - Clientes cuyas solicitudes de envío no se encuentran en el consolidado de pedidos *
     ******************************************************************************/
    IF(lsCodigoValidacion = 'MAV-VAL-01') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4,
       val_etq5
      )
      SELECT  distinct
       psCodigoPais AS cod_pais,
       'MAV' AS cod_sist,
       'MAV-VAL-01' AS num_vali,
       en.clie_oid_clie,
      (SELECT Mc.COD_CLIE FROM MAE_CLIEN Mc WHERE Mc.OID_CLIE= EN.CLIE_OID_CLIE) COD_CLIE  ,
       ec.oid_soli_envi,
       en.oid_envi,
       ec.soca_oid_soli_cabe
       FROM MAV_PARAM_CONFI M,
            MAV_ENVIO_CONFI EN,
            MAV_SOLIC_ENVIO_CONFI EC ,
            PED_SOLIC_CABEC psc
       WHERE M.COR_PARA_CONF = EN.COR_PARA_CONF AND m.pais_cod_pais = EN.PAIS_COD_PAIS
       AND EN.EST_REGI = 1
       AND M.CAM_PARA_MAV =psCodigoPeriodo
       AND M.TICL_OID_TIPO_CLIE= 2---2:cons-4:gere
       AND EC.MENV_OID_ENVI = EN.OID_ENVI
       --and ec.soca_oid_soli_cabe NOT in (select oid_soli_cabe from ped_solic_cabec ps where ps.perd_oid_peri = 2291 and ps.grpr_oid_grup_proc = 5  )
       AND EC.soca_oid_soli_cabe =  psc.oid_soli_cabe(+)
       AND psc.fec_fact is null
       --ADD
       AND PSC.GRPR_OID_GRUP_PROC IS NULL
       AND EXISTS (
                    SELECT 1
                      FROM PED_SOLIC_CABEC_ACUM2 AC
                     WHERE AC.PERD_OID_PERI =
                           (select cr.oid_peri
                              from cra_perio cr, seg_perio_corpo sc
                             where cr.peri_oid_peri = sc.oid_peri
                               and sc.cod_peri = M.CAM_PARA_MAV)
                       AND ac.clie_oid_clie = EN.CLIE_OID_CLIE
                    );
    END IF;


 /*****************************************************************************
     * 35. DIR-VAL-01 - Gerentes y Lideres que no cuentan con la UA en los campos individuales *
     ******************************************************************************/
    IF(lsCodigoValidacion = 'DIR-VAL-01') THEN
        INSERT INTO mae_valid_clien
        (
         pais_cod_pais,
         sist_cod_sist,
         vali_num_vali,
         val_etq1,
         val_etq2
        )
       SELECT psCodigoPais AS cod_pais,
             'DIR' AS cod_sist,
             'DIR-VAL-01' AS num_vali,
              x.oid_hist_gere,
              x.ua
        from zon_histo_geren x
       where ( length(x.ua) = 9 and cod_secc is null )
          or ( length(x.ua) = 8 and cod_zona is null )
          or ( length(x.ua) = 4 and cod_regi is null );
    END IF;


    /******************************************************************************************
     * 36. MAE-VAL-33 - Validación para obtener programacion de cierres fuera del rango de fechas de cra_perio *
     *
     *******************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-33') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-33' AS num_vali,
             COR_PROG_CIER,
             TIP_CIER,
             FEC_CIER,
             CAM_PROC
      FROM   fac_progr_cierr
      WHERE  cam_proc=psCodigoPeriodo
             AND est_regi=1
             AND fec_cier NOT BETWEEN (SELECT fec_inic FROM cra_perio WHERE oid_peri=lnOidPeriodo) AND (SELECT fec_fina FROM cra_perio WHERE oid_peri=lnOidPeriodo);
    END IF;


    /******************************************************************************************
     * 37. MAE-VAL-34 - Validación para detectar caracteres especiales en apellidos y nombres *
     *                  de una consultora
     *******************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-34') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-34' AS num_vali,
             OID_CLIE,
             COD_CLIE
       FROM  MAE_CLIEN
       WHERE TRANSLATE ( REPLACE(val_nom1,' ',NULL)
                    , '?ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789ÁÉÍÓÚ.'
                    , '?'
                    ) IS NOT NULL
             OR TRANSLATE ( REPLACE(val_nom2,' ',NULL)
                                , '?ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789ÁÉÍÓÚ.'
                                , '?'
                                ) IS NOT NULL
             OR TRANSLATE ( REPLACE(val_ape1,' ',NULL)
                                , '?ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789ÁÉÍÓÚ.'
                                , '?'
                                ) IS NOT NULL
             OR TRANSLATE ( REPLACE(val_ape2,' ',NULL)
                                , '?ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789ÁÉÍÓÚ.'
                                , '?'
                                ) IS NOT NULL;
    END IF;

    /******************************************************************************************
     * 38. MAE-VAL-35 - Validación para detectar caracteres especiales en nombre via y observacion(direccion) *
     *                  de una consultora
     *******************************************************************************************/
    IF(lsCodigoValidacion = 'MAE-VAL-35') THEN
      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-35' AS num_vali,
             CLIE_OID_CLIE,
             COD_CLIE,
             OID_CLIE_DIRE
      FROM   MAE_CLIEN_DIREC DI, MAE_CLIEN MA
      WHERE
             DI.CLIE_OID_CLIE=MA.OID_CLIE AND
             (TRANSLATE ( REPLACE(VAL_NOMB_VIA,' ',NULL)
                                , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'
                                , '?'
                                ) IS NOT NULL
             OR TRANSLATE ( REPLACE(VAL_OBSE,' ',NULL)
                                , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'
                                , '?'
                                ) IS NOT NULL);
     END IF;


     /*****************************************************************************
* 39. MAE-VAL-36 - Validar recomendadas con mas 1 recomendante               *
******************************************************************************/
 IF(lsCodigoValidacion = 'MAE-VAL-36') THEN

    INSERT INTO mae_valid_clien
    (
     pais_cod_pais,
     sist_cod_sist,
     vali_num_vali,
     val_etq1,
     val_etq2,
     val_etq3
    )
    SELECT psCodigoPais AS cod_pais,
           'MAE' AS cod_sist,
           'MAE-VAL-36' AS num_vali,
           CVIN.CLIE_OID_CLIE_VNDO,
           CLIE.COD_CLIE,
           COUNT(*) val_nume_dupl
      FROM MAE_CLIEN_VINCU CVIN,
           MAE_CLIEN CLIE
     WHERE CVIN.CLIE_OID_CLIE_VNDO = CLIE.OID_CLIE AND TIVC_OID_TIPO_VINC=9
     GROUP BY CVIN.CLIE_OID_CLIE_VNDO,
              CLIE.COD_CLIE
    HAVING COUNT(*) > 1;
END IF;


 /*****************************************************************************
* 40. MAE-VAL-37 - Validar clientes sin registro en MAE_PRIME_CONTA            *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAE-VAL-37') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-37' AS num_vali,
             CLIE.OID_CLIE,
             CLIE.COD_CLIE
        FROM MAE_CLIEN CLIE,
             MAE_CLIEN_TIPO_SUBTI CTS
       WHERE CTS.CLIE_OID_CLIE = CLIE.OID_CLIE
         AND CTS.TICL_OID_TIPO_CLIE = 2
         AND CTS.SBTI_OID_SUBT_CLIE = 1
         AND NOT EXISTS ( SELECT 1
                          FROM MAE_CLIEN_PRIME_CONTA
                          WHERE CLIE_OID_CLIE = CLIE.OID_CLIE )
         AND CLIE.COD_CLIE NOT LIKE '%MIGRACION%';

   END IF;

/*****************************************************************************
* 41. MAE-VAL-38 - Validar Clientes con más de una UA activa            *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAE-VAL-38') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3
      )
      SELECT psCodigoPais AS cod_pais,
             'MAE' AS cod_sist,
             'MAE-VAL-38' AS num_vali,
             UA.CLIE_OID_CLIE,
             CLIE.COD_CLIE,
             SUM(IND_ACTI)
        FROM MAE_CLIEN_UNIDA_ADMIN UA, MAE_CLIEN CLIE
       WHERE UA.CLIE_OID_CLIE = CLIE.OID_CLIE
       GROUP BY UA.CLIE_OID_CLIE, CLIE.COD_CLIE
      HAVING SUM(IND_ACTI) > 1;


   END IF;
   
/*****************************************************************************
* 42. MAV-VAL-02 - Validar MAV con envios duplicados                          *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAV-VAL-02') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT  DISTINCT PSCODIGOPAIS AS COD_PAIS,
                        'MAV' AS COD_SIST,
                        'MAV-VAL-02' AS NUM_VALI,
                        X.TICL_OID_TIPO_CLIE,
                        X.MAV,
                        X.CANTIDAD,
                        X.CAM_PARA_MAV CAMP_MAV
          FROM (SELECT MV.TICL_OID_TIPO_CLIE,
                       ENV.COR_PARA_CONF MAV,
                       MV.CAM_PARA_MAV,
                       ENV.TIP_CONS_DESP,
                       ENV.COD_REGI,
                       ENV.COD_ZONA,
                       COUNT(*) CANTIDAD
                  FROM MAV_ENVIO_CONFI ENV,
                       MAV_PARAM_CONFI MV
                 WHERE ENV.COR_PARA_CONF = MV.COR_PARA_CONF
                   AND ENV.PAIS_COD_PAIS = MV.PAIS_COD_PAIS
                   AND ENV.IND_ENVI <> 'D'
                   AND MV.CAM_PARA_MAV = 
                 (SELECT FA.COD_PERI
                          FROM BAS_CTRL_FACT FA
                         WHERE FA.STA_CAMP = 0
                           AND FA.IND_CAMP_ACT = 1
                           AND ROWNUM = 1)
                 GROUP BY MV.TICL_OID_TIPO_CLIE,
                          ENV.COR_PARA_CONF,
                          ENV.CLIE_OID_CLIE,
                          MV.CAM_PARA_MAV,
                          ENV.TIP_CONS_DESP,
                          ENV.COD_REGI,
                          ENV.COD_ZONA
                HAVING COUNT(*) > 1) X;        


   END IF;

/*****************************************************************************
* 43. MAV-VAL-03 - Validar MAV errados por alguna casuistica no contemplada                      *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAV-VAL-03') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAV' AS cod_sist,
             'MAV-VAL-03' AS num_vali,
             MA.COD_CLIE, 
             TM.CLIE_OID_CLIE, 
             TM.OID_SOLI_CABE, 
             TM.FEC_INCI
        FROM MAV_TMP_CLIEN_ENVIO TM, MAE_CLIEN MA
       WHERE TM.CLIE_OID_CLIE = MA.OID_CLIE(+)
         AND TM.FEC_INCI IS NOT NULL
         AND EXISTS
       (SELECT PERD.VAL_NOMB_PERI
                FROM CRA_PERIO PERD, SEG_PERIO_CORPO PERI
               WHERE PERD.PERI_OID_PERI = PERI.OID_PERI
                 AND PERI.COD_PERI = (SELECT FA.COD_PERI
                                        FROM BAS_CTRL_FACT FA
                                       WHERE FA.STA_CAMP = 0
                                         AND FA.IND_CAMP_ACT = 1
                                         AND ROWNUM = 1)
                 AND TM.FEC_INCI BETWEEN PERD.FEC_INIC AND PERD.FEC_FINA)
       ORDER BY TM.FEC_INCI DESC;   
       
   END IF;

/*****************************************************************************
* 44. MAV-VAL-04 - Validar  MAV de tipo Lista con unidades en Null                     *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAV-VAL-04') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3
      )
      SELECT psCodigoPais AS cod_pais,
             'MAV' AS cod_sist,
             'MAV-VAL-04' AS num_vali,
             MA.COR_PARA_CONF MAV,
             MA.COD_ESTA_MAV ESTA_MAV,
             MA.CAM_PARA_MAV CAMP_MAV
        FROM MAV_PARAM_CONFI MA,
             (SELECT D.PACO_COR_PARA_CONF, D.CAM_PROC_MAV
                FROM MAV_PARAM_CONFI       M,
                     MAV_PARAM_CORES_CABEC C,
                     MAV_PARAM_CORES_DETAL D
               WHERE M.COR_PARA_CONF = C.PACO_COR_PARA_CONF
                 AND M.PAIS_COD_PAIS = C.PAIS_COD_PAIS
                 AND C.PACO_COR_PARA_CONF = D.PACO_COR_PARA_CONF
                 AND C.PAIS_COD_PAIS = D.PAIS_COD_PAIS
                 AND C.CORE_COR_CONS_REST = D.CORE_COR_CONS_REST
                 --AND M.TICL_OID_TIPO_CLIE = 2
                 --AND C.CORE_COR_CONS_REST = 18 --LISTA 
                 --AND M.COD_ESTA_MAV = 2
                 --AND C.IND_CONS_REST = 'C'
                 AND M.IND_UNID_DIF = 'S' -- ADICC
                 AND (D.NUM_UNID_PROD IS NULL OR D.NUM_UNID_PROD = 0)
                 AND D.EST_REGI = '1'
                 AND M.CAM_PARA_MAV >=  
                     (SELECT FA.COD_PERI
                        FROM BAS_CTRL_FACT FA
                       WHERE FA.STA_CAMP = 0
                         AND FA.IND_CAMP_ACT = 1
                         AND ROWNUM = 1)
               GROUP BY D.PACO_COR_PARA_CONF, D.CAM_PROC_MAV) X
       WHERE MA.COR_PARA_CONF = X.PACO_COR_PARA_CONF
         AND MA.COD_ESTA_MAV <> 5;
       
   END IF;

/*****************************************************************************
* 45. MAV-VAL-05 - Validar MAV Todos con Unidad en NULL                      *
******************************************************************************/
   IF (lsCodigoValidacion = 'MAV-VAL-05') THEN

      INSERT INTO mae_valid_clien
      (
       pais_cod_pais,
       sist_cod_sist,
       vali_num_vali,
       val_etq1,
       val_etq2,
       val_etq3,
       val_etq4
      )
      SELECT psCodigoPais AS cod_pais,
             'MAV' AS cod_sist,
             'MAV-VAL-05' AS num_vali,
             M.COR_PARA_CONF MAV,
             M.CAM_PARA_MAV CAM_MAV,
             M.COD_SAP,
             M.FEC_MODI
        FROM MAV_PARAM_CONFI M, MAV_PARAM_CORES_CABEC C
       WHERE M.COR_PARA_CONF = C.PACO_COR_PARA_CONF
         AND M.PAIS_COD_PAIS = C.PAIS_COD_PAIS
         AND M.COD_ESTA_MAV <> 5
         AND C.CORE_COR_CONS_REST = 1 --TODOS
         AND C.IND_CONS_REST = 'C'
         AND M.NUM_UNID_PROD IS NULL
         AND M.CAM_PARA_MAV >=  
             (SELECT FA.COD_PERI
                FROM BAS_CTRL_FACT FA
               WHERE FA.STA_CAMP = 0
                 AND FA.IND_CAMP_ACT = 1
                 AND ROWNUM = 1)
       GROUP BY M.COR_PARA_CONF,
                M.CAM_PARA_MAV,
                M.COD_SAP,
                M.VAL_CODI_VENT,
                M.FEC_CREA,
                M.FEC_MODI;
       
   END IF;

 END LOOP;
  CLOSE c_validaciones;


      /* ENVIANDO LOS CORREOS LUEGO DE LAS VALIDACIONES */
      MAE_PR_ENVIO_INFOR_CLIEN(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psUsuario,psIndTipoValid,psIndTipoPrev,psEntorno);


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'MAE_PR_VALID_INFOR_CLIEN: '||ls_sqlerrm);
END MAE_PR_VALID_INFOR_CLIEN;


/**************************************************************************
Descripcion       : Envia mail de validaciones a ffvv y de cierre a Datamart
Fecha Creacion    : 11/08/2011
Fecha Modificacion: 14/05/2012
Parametros Entrada: psCodigoPais       = Pais
                    psCodigoPeriodo    = Campaña de facturación
                    psFechaFacturacion = Fecha de facturación
                    psUsuario          = Usuario
                    psIndTipoValid     = Indicador de tipo de validacion
                                         [1] Diario
                                         [2] Diario y cierre de campaña
                                         [3] Cierre de campaña
Autor             : Carlos Mori
***************************************************************************/
PROCEDURE MAE_PR_ENVIO_INFOR_CLIEN
(
  psCodigoPais             VARCHAR2,
  psCodigoPeriodo          VARCHAR2,
  psFechaFacturacion       VARCHAR2,
  psUsuario                VARCHAR2,
  psIndTipoValid           VARCHAR2,
  psIndTipoPrev            VARCHAR2,
  psEntorno                VARCHAR2
)
IS
CURSOR cursorIngresos ( vnOidPeriodo NUMBER ) IS
  SELECT clie.cod_clie
    FROM mae_clien_histo_estat clhe,
         mae_clien             clie,
         mae_clien_datos_adici clda
   WHERE clhe.clie_oid_clie = clie.oid_clie
     AND clhe.clie_oid_clie = clda.clie_oid_clie
     AND clda.ind_acti = 1
     AND clhe.perd_oid_peri = vnOidPeriodo
     AND clhe.esta_oid_esta_clie in (2, 8)
       ;

CURSOR cursorReingresos ( vnOidPeriodo NUMBER ) IS
  SELECT clie.cod_clie
    FROM mae_clien_histo_estat clhe,
         mae_clien             clie,
         mae_clien_datos_adici clda
   WHERE clhe.clie_oid_clie = clie.oid_clie
     AND clhe.clie_oid_clie = clda.clie_oid_clie
     AND clda.ind_acti = 1
     AND clhe.perd_oid_peri = vnOidPeriodo
     AND clhe.esta_oid_esta_clie = 6
       ;

CURSOR cursorEgresos ( vnOidPeriodo NUMBER ) IS
  SELECT clie.cod_clie
    FROM mae_clien_histo_estat clhe,
         mae_clien             clie,
         mae_clien_datos_adici clda
   WHERE clhe.clie_oid_clie = clie.oid_clie
     AND clhe.clie_oid_clie = clda.clie_oid_clie
     AND clda.ind_acti = 1
     AND clhe.perd_oid_peri = vnOidPeriodo
     AND clda.num_camp_sin_pedi = 2
     AND clhe.esta_oid_esta_clie = 5
       ;

CURSOR cursorValidacion(lsCodigoValidacion VARCHAR2,lsNumFila VARCHAR2) IS
  SELECT *
    FROM mae_valid_clien
   WHERE vali_num_vali = lsCodigoValidacion
     AND rownum <= lsNumFila
       ;

CURSOR cursorCabeceras IS
  SELECT *
    FROM mae_valid_clien_param m
   WHERE m.est_regi = '1'
     AND m.pais_cod_pais = psCodigoPais
     AND (m.tip_vali = psIndTipoValid OR tip_vali = '3')
     AND nvl(psIndTipoPrev, m.tip_prev) = m.tip_prev;


 regReporte bas_repor_param%ROWTYPE;
 regPais    bas_pais%ROWTYPE;
l_mail_conn     UTL_SMTP.connection;
 lsFecha VARCHAR2(10);
 lsCampanha VARCHAR2(7);
 lsSubject       VARCHAR2(500);
 lsLinea         VARCHAR2(4000);
 lnExiste        NUMBER;
 lsNumFilas      VARCHAR2(3);

lnActivasIni    NUMBER;
lnIngresos      NUMBER;
lnReingresos    NUMBER;
lnEgresos       NUMBER;
lnActivasFin    NUMBER;
lnNroPedidos    NUMBER;
lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;

BEGIN
   lsLinea:='';
   lnOidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );

   SELECT *
     INTO regReporte
     FROM bas_repor_param
    WHERE ( psIndTipoValid='1' AND nom_repo = 'procesoGENValidacionCliente' )
       OR ( psIndTipoValid= '2' AND nom_repo = 'procesoGENCuadreCierreCampana' );

   SELECT *
     INTO regPais
     FROM bas_pais
    WHERE cod_pais = psCodigoPais;

   SELECT val_para
     INTO lsNumFilas
     FROM bas_param_pais
    WHERE cod_pais = psCodigoPais
      AND cod_sist ='MAE'
      AND nom_para = 'rowNum';

      -- CORREOS DE VALIDACIONS DIARIAS
      IF(regReporte.IND_EMAI = 'S' AND regReporte.NOM_REPO = 'procesoGENValidacionCliente') THEN

          lsFecha:= substr(psFechaFacturacion,1,2) ||'-' ||substr(psFechaFacturacion,4,2)||'-' || substr(psFechaFacturacion,7,4);
          lsCampanha:=substr(psCodigoPeriodo,1,4) ||'-' ||substr(psCodigoPeriodo,5,2);

          --lsSubject := regReporte.VAL_SUBJ ||' - '|| lsFecha || ' - '||regPais.DES_PAIS||' - '|| psCodigoPeriodo;
          SELECT COUNT(1) INTO lnExiste
              FROM mae_valid_clien;

          IF (lnExiste > 0 ) THEN
             lsSubject := UPPER(SUBSTR(psEntorno,-3)) ||' - '|| regReporte.VAL_SUBJ ||' - '|| lsFecha || ' - '||regPais.DES_PAIS||' - '|| psCodigoPeriodo;
          ELSE
            lsSubject := 'NO SE ENCONTRARON INCONSISTENCIAS - ' || UPPER(SUBSTR(psEntorno,-3)) ||' '|| regReporte.VAL_SUBJ ||' - '|| lsFecha || ' - '||regPais.DES_PAIS||' - '|| psCodigoPeriodo;
          END IF;


          l_mail_conn := log_email.begin_mail(
                  sender => regReporte.EMA_ORIG,
                  recipients => regReporte.EMA_COPI,
                  subject => lsSubject,
                                               mime_type => 'text/html'
                                              );

          lsLinea:= lsLinea ||
            '<html><head><meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
          lsLinea:= lsLinea ||
            '<table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td><font face="Arial" size="2">';
          lsLinea:= lsLinea ||
            'Se ha realizado el envio de  <strong>INCONSISTENCIAS</strong>&'||'nbsp;en la información de clientes desde el Sistema Comercial correspondiente ';
          lsLinea:= lsLinea ||
            'al pais &'||'nbsp;<strong>'|| regPais.DES_PAIS ||'</strong></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td><font face="Arial" size="2"><br><strong>NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada.';
          lsLinea:= lsLinea ||
            '</strong><br>Proceso ha sido <strong>ejecutado</strong> por el Usuario <strong>'||psUsuario ||'</strong>.<br><br></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td width="80%"><table border="1" cellpadding="0" cellspacing="0"><tr><td background="#7030A0" bgcolor="#7030A0">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Campa&'||'ntildea </b></font></td><td align="right"><font face="Arial" size="2">'|| psCodigoPeriodo ||'</font></td>';
          lsLinea:= lsLinea ||
            '</tr><tr><td background="#7030A0" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Fecha</b></font></td><td align="right">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2"> &'||'nbsp'|| psFechaFacturacion ||'</font></td></tr></table></td></tr><tr></tr>';

          log_email.write_text(l_mail_conn,lsLinea);

          FOR cCabecera IN cursorCabeceras LOOP

            SELECT COUNT(1) INTO lnExiste
              FROM mae_valid_clien
             WHERE vali_num_vali = cCabecera.Num_Vali;

             IF (lnExiste > 0 ) THEN

             lsLinea:='<tr><td width="95%"><table  border="1" cellpadding="0" cellspacing="0"><tr>'||
                      '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> Validación </b></font></td>'||
                        '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| cCabecera.Num_Vali ||' </font></td>'||
                        '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| cCabecera.Des_Vali ||' </font></td>'||
                      '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| lnExiste ||' registros </font></td></tr></table></td></tr><tr></tr><tr></tr>';
               log_email.write_text(l_mail_conn,lsLinea);

                 IF(to_number(lsNumFilas) > 0) THEN
                   lsLinea:='<tr><td width="95%"><table  border="1" cellpadding="0" cellspacing="0"><tr>';
                   IF (cCabecera.Val_Etq1 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq1||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq2 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq2||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq3 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq3||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq4 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq4||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq5 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq5||' </b></font></td>';
                   END IF;

                    lsLinea:=lsLinea ||'</tr>';
                    log_email.write_text(l_mail_conn,lsLinea);

                        FOR cValidacion IN cursorValidacion(cCabecera.Num_Vali,lsNumFilas) LOOP
                          lsLinea:='<tr>';
                          IF (cCabecera.Val_Etq1 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq1 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq2 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq2 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq3 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq3 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq4 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq4 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq5 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq5 ||'</font></td>';
                          END IF;

                          lsLinea:=lsLinea || '</tr>';
                          log_email.write_text(l_mail_conn,lsLinea);
                  END LOOP;

                 lsLinea:='</table></td></tr><tr></tr><tr></tr>';
                   log_email.write_text(l_mail_conn,lsLinea);

                   END IF;

             END IF;

          END LOOP;


          lsLinea:='</tbody></table><br></body></html>';
          log_email.write_text(l_mail_conn,lsLinea);
          log_email.end_mail( conn => l_mail_conn );
     END IF;


     SELECT COUNT(1)
       INTO lnExiste
       FROM mae_valid_clien vacl,
            mae_valid_clien_param vcpa
      WHERE vacl.pais_cod_pais = vcpa.pais_cod_pais
        AND vacl.sist_cod_sist = vcpa.sist_cod_sist
        AND vacl.vali_num_vali = vcpa.num_vali
        AND vcpa.est_regi = '1'
        AND (vcpa.tip_vali = psIndTipoValid OR vcpa.tip_vali = '3')
        AND nvl(psIndTipoPrev, vcpa.tip_prev) = vcpa.tip_prev;


     -- CORREOS DE CIERRE CON ERROR EN VALIDACIONES

     IF(regReporte.IND_EMAI = 'S' AND regReporte.NOM_REPO = 'procesoGENCuadreCierreCampana' AND lnExiste>0) THEN

          lsFecha:= substr(psFechaFacturacion,1,2) ||'-' ||substr(psFechaFacturacion,4,2)||'-' || substr(psFechaFacturacion,7,4);
          lsCampanha:=substr(psCodigoPeriodo,1,4) ||'-' ||substr(psCodigoPeriodo,5,2);

          lsSubject := 'ERRORES : ' || regReporte.VAL_SUBJ || ' ' || lsCampanha || ' '||regPais.DES_PAIS;
          l_mail_conn := log_email.begin_mail(
                                                          sender => regReporte.EMA_ORIG,
                                                          recipients => regReporte.EMA_COPI,
                                                          subject => lsSubject,
                                                          mime_type => 'text/html'
                                                         );

          lsLinea:= lsLinea ||
            '<html><head><meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
          lsLinea:= lsLinea ||
            '<table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td><font face="Arial" size="2">';
          lsLinea:= lsLinea ||
            'Se ha realizado el envio de  <strong>INCONSISTENCIAS</strong>&'||'nbsp;en la información de Cuadre Cierre Campana correspondiente ';
          lsLinea:= lsLinea ||
            'al pais &'||'nbsp;<strong>'|| regPais.DES_PAIS ||'</strong></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td><font face="Arial" size="2"><br><strong>NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada.';
          lsLinea:= lsLinea ||
            '</strong><br>Proceso ha sido ejecutado por el usuario <strong>'||psUsuario ||'</strong>.<br><br></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td width="80%"><table border="1" cellpadding="0" cellspacing="0"><tr><td background="#7030A0" bgcolor="#7030A0">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Campa&'||'ntildea </b></font></td><td align="right"><font face="Arial" size="2">'|| lsCampanha ||'</font></td>';
          lsLinea:= lsLinea ||
            '</tr><tr><td background="#7030A0" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Fecha</b></font></td><td align="right">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2"> &'||'nbsp'|| psFechaFacturacion ||'</font></td></tr></table></td></tr><tr></tr>';

          log_email.write_text(l_mail_conn,lsLinea);

          FOR cCabecera IN cursorCabeceras LOOP

            SELECT COUNT(1) INTO lnExiste
              FROM mae_valid_clien
             WHERE vali_num_vali = cCabecera.Num_Vali;

             IF (lnExiste > 0 ) THEN

               lsLinea:='<tr><td width="95%"><table  border="1" cellpadding="0" cellspacing="0"><tr>'||
                        '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> Validación </b></font></td>'||
                        '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| cCabecera.Num_Vali ||' </font></td>'||
                        '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| cCabecera.Des_Vali ||' </font></td>'||
                        '<td align="left"><font face="Arial" size="2" >&'||'nbsp '|| lnExiste ||' registros </font></td></tr></table></td></tr><tr></tr><tr></tr>';
               log_email.write_text(l_mail_conn,lsLinea);

                 IF(to_number(lsNumFilas) > 0) THEN
                   lsLinea:='<tr><td width="95%"><table  border="1" cellpadding="0" cellspacing="0"><tr>';
                   IF (cCabecera.Val_Etq1 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq1||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq2 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq2||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq3 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq3||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq4 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq4||' </b></font></td>';
                   END IF;
                   IF (cCabecera.Val_Etq5 IS NOT NULL) THEN
                      lsLinea:=lsLinea || '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b> '|| cCabecera.Val_Etq5||' </b></font></td>';
                   END IF;

                    lsLinea:=lsLinea ||'</tr>';
                    log_email.write_text(l_mail_conn,lsLinea);

                        FOR cValidacion IN cursorValidacion(cCabecera.Num_Vali,lsNumFilas) LOOP
                          lsLinea:='<tr>';
                          IF (cCabecera.Val_Etq1 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq1 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq2 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq2 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq3 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq3 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq4 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq4 ||'</font></td>';
                          END IF;
                          IF (cCabecera.Val_Etq5 IS NOT NULL) THEN
                              lsLinea:=lsLinea || '<td align="left"><font face="Arial" size="2" >&'||'nbsp'|| cValidacion.Val_Etq5 ||'</font></td>';
             END IF;

                          lsLinea:=lsLinea || '</tr>';
                          log_email.write_text(l_mail_conn,lsLinea);
                  END LOOP;

                   lsLinea:='</table></td></tr><tr></tr><tr></tr>';
                   log_email.write_text(l_mail_conn,lsLinea);

                   END IF;

             END IF;

          END LOOP;

          lsLinea:='</tbody></table><br></body></html>';
          log_email.write_text(l_mail_conn,lsLinea);
          log_email.end_mail( conn => l_mail_conn );
     END IF;

     SELECT COUNT(1)
       INTO lnExiste
       FROM mae_valid_clien vacl,
            mae_valid_clien_param vcpa
      WHERE vacl.pais_cod_pais = vcpa.pais_cod_pais
        AND vacl.sist_cod_sist = vcpa.sist_cod_sist
        AND vacl.vali_num_vali = vcpa.num_vali
        AND vcpa.est_regi = '1'
        AND (vcpa.tip_vali = psIndTipoValid OR vcpa.tip_vali = '3')
        AND nvl(psIndTipoPrev, vcpa.tip_prev) = vcpa.tip_prev;

     -- CORREOS DE CIERRE CON VALIDACIONES OK
     IF(regReporte.IND_EMAI = 'S' AND regReporte.NOM_REPO = 'procesoGENCuadreCierreCampana' AND lnExiste = 0) THEN

          lsFecha:= substr(psFechaFacturacion,1,2) ||'-' ||substr(psFechaFacturacion,4,2)||'-' || substr(psFechaFacturacion,7,4);
          lsCampanha:=substr(psCodigoPeriodo,1,4) ||'-' ||substr(psCodigoPeriodo,5,2);

          -- SE OBTIENEN LOS TOTALES DE CONSULTORAS
          SELECT SUM( fv.num_acti_inic ) aini,
                 SUM( fv.num_ingr ) ingr,
                 SUM( fv.num_rein ) rein,
                 SUM( fv.num_egre ) egre,
                 SUM( fv.num_acti_fina ) afin
                 INTO lnActivasIni, lnIngresos, lnReingresos, lnEgresos, lnActivasFin
            FROM int_fuent_ventas_real fv, cra_perio cp, seg_perio_corpo sp
           WHERE fv.perd_oid_peri = cp.oid_peri AND cp.peri_oid_peri = sp.oid_peri
             AND sp.cod_peri = psCodigoPeriodo;

          -- SE OBTIENEN EL NRO DE PEDIDOS
          SELECT COUNT(DISTINCT psc.clie_oid_clie) cant_pedidos
            INTO lnNroPedidos
            FROM ped_solic_cabec     psc,
                 ped_solic_cabec     psc2,
                 mae_clien           clie,
                 seg_modul           sm,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic      pts,
                 cra_perio           cp,
                 seg_perio_corpo     sp
           WHERE psc.soca_oid_soli_cabe = psc2.oid_soli_cabe
             AND psc.clie_oid_clie = clie.oid_clie
             AND psc.ind_ts_no_conso = 1
             AND psc.ind_oc = 1
             AND psc.ind_pedi_prue <> 1
             AND psc.fec_fact IS NOT NULL
             AND psc.modu_oid_modu = sm.oid_modu
             AND sm.cod_modu <> 15
             AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
             AND pts.ind_anul <> 1
             AND pts.ind_devo <> 1
             AND psc.perd_oid_peri = cp.oid_peri
             AND cp.peri_oid_peri = sp.oid_peri
             AND sp.cod_peri = pscodigoperiodo
            ;

          -- SE REVISAN LOS TOTALES
          IF (lnActivasIni + lnIngresos + lnReingresos - lnEgresos = lnActivasFin) THEN

            lsSubject := regReporte.VAL_SUBJ || ' ' || lsCampanha || ' '||regPais.DES_PAIS;
            l_mail_conn := log_email.begin_mail(
                                                sender => regReporte.EMA_ORIG,
                                                recipients => regReporte.EMA_DEFA,
                                                subject => lsSubject,
                                                mime_type => log_email.MULTIPART_MIME_TYPE,
                                                cc_recipients => regReporte.EMA_COPI
                                               );
          ELSE
            -- SI NO CUADRAN LOS TOTALES NO SE ENVÍA CORREO A DATAMART
            lsSubject := 'ERRORES : ' || regReporte.VAL_SUBJ || ' ' || lsCampanha || ' '||regPais.DES_PAIS;
            l_mail_conn := log_email.begin_mail(
                                                sender => regReporte.EMA_ORIG,
                                                recipients => regReporte.EMA_COPI,
                                                subject => lsSubject,
                                                mime_type => log_email.MULTIPART_MIME_TYPE
                                               );
          END IF;

          lsLinea:= lsLinea ||
            '<html><head><meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
          lsLinea:= lsLinea ||
            '<table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td><font face="Arial" size="2">';
          lsLinea:= lsLinea ||
            'Se ha realizado el envio de  <strong>Cuadre Cierre de Campana '|| lsCampanha ||'</strong> correspondiente ';
          lsLinea:= lsLinea ||
            'al pais &'||'nbsp;<strong>'|| regPais.DES_PAIS ||'</strong> ';
          lsLinea:= lsLinea ||
            '<br> <br> <strong>NOTA:</strong> Cualquier consulta o confirmación responder a la cuenta <strong>Corp. Soporte FFVV</strong></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td><font face="Arial" size="2"><br>Proceso ha sido ejecutado por el usuario <strong>'||psUsuario ||'</strong>.<br><br></font></td></tr>';
          lsLinea:= lsLinea ||
            '<tr><td width="90%"><table border="1" cellpadding="0" cellspacing="0"><tr><td background="#7030A0" bgcolor="#7030A0">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Campa&'||'ntildea </b></font></td><td align="right"><font face="Arial" size="2">'|| lsCampanha ||'</font></td>';
          lsLinea:= lsLinea ||
            '</tr><tr><td background="#7030A0" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>&'||'nbsp Fecha</b></font></td><td align="right">';
          lsLinea:= lsLinea ||
            '<font face="Arial" size="2"> &'||'nbsp'|| psFechaFacturacion ||'</font></td></tr></table></td></tr><tr></tr>';

          lsLinea:= lsLinea ||'<tr><td width="95%"><table  border="1" cellpadding="0" cellspacing="0"><tr>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"> <b>Activas&'||'nbsp Iniciales</b></font></td>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>Ingresos&'||'nbsp</b></font></td>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>Reingresos&'||'nbsp</b></font></td>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>Egresos&'||'nbsp</b></font></td>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>Activas&'||'nbsp Finales</b></font></td>'||
                              '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>Nro.&'||'nbsp Pedidos</b></font></td></tr><tr>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnActivasIni,'999,999,999') ||' </font></td>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnIngresos,'999,999,999')   ||' </font></td>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnReingresos,'999,999,999') ||' </font></td>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnEgresos,'999,999,999')    ||' </font></td>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnActivasFin,'999,999,999') ||' </font></td>'||
                              '<td align="center"><font face="Arial" size="2" >'|| to_char(lnNroPedidos,'999,999,999') ||' </font></td>'||
                              '</tr></table></td></tr><tr></tr><tr></tr>';
          IF (lnActivasIni + lnIngresos + lnReingresos - lnEgresos <> lnActivasFin) THEN
             lsLinea:= lsLinea ||'<tr><td colspan=6 ><br><font face="Arial" size="2" color="#ff0000"><b>OBSERVACIONES : Los totales no están correctos</b></font><br><br></td></tr>';
          END IF;

          lsLinea:= lsLinea ||'<tr><td colspan=6><font face="Arial" size="2" >Se adjuntan archivos detalle.</font></td></tr>';
          lsLinea:= lsLinea ||'</tbody></table><br></body></html>';

          log_email.attach_text(l_mail_conn,lsLinea,'text/html',true,null);

          -- CREACION DE ARCHIVOS TXT

          log_email.begin_attachment(l_mail_conn,'text/plain',false,'Detalle Ingresos - '||psCodigoPais||' '||psCodigoPeriodo||'.TXT');
          FOR c IN cursorIngresos( lnOidPeriodo ) LOOP
               log_email.write_text(l_mail_conn,c.cod_clie||CHR(13)||CHR(10));
          END LOOP;
          log_email.end_attachment(l_mail_conn, false);

          log_email.begin_attachment(l_mail_conn,'text/plain',false,'Detalle Reingresos - '||psCodigoPais||' '||psCodigoPeriodo||'.TXT');
          FOR c IN cursorReingresos( lnOidPeriodo ) LOOP
               log_email.write_text(l_mail_conn,c.cod_clie||CHR(13)||CHR(10));
          END LOOP;
          log_email.end_attachment(l_mail_conn, false);

          log_email.begin_attachment(l_mail_conn,'text/plain',false,'Detalle Egresos - '||psCodigoPais||' '||psCodigoPeriodo||'.TXT');
          FOR c IN cursorEgresos( lnOidPeriodo ) LOOP
               log_email.write_text(l_mail_conn,c.cod_clie||CHR(13)||CHR(10));
          END LOOP;
          log_email.end_attachment(l_mail_conn, true);

          log_email.end_mail( conn => l_mail_conn );
     END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'MAE_PR_ENVIO_INFOR_CLIEN: '||ls_sqlerrm);
END MAE_PR_ENVIO_INFOR_CLIEN;


/**************************************************************************
Descripcion       : Actualiza Periodo de Contacto para el Caso de uso: Informacion
                    del Cliente
Fecha Creacion    : 10/10/2011
Parametros Entrada:

  pnOidCliente    :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_PRIME_CONTA
  (pnOidCliente           NUMBER)
IS
  ldFechaContacto       MAE_CLIEN_PRIME_CONTA.FEC_CONT%TYPE;
  ldFechaSigContacto    MAE_CLIEN_PRIME_CONTA.FEC_CONT%TYPE;
  lsCodPeriodo          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodo          CRA_PERIO.OID_PERI%TYPE;
  lnOidSubTipo          MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
  lnTotalOcurrencias    NUMBER;

  lnOidMarca            SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal            SEG_CANAL.OID_CANA%TYPE;
  lnOidPais             SEG_PAIS.OID_PAIS%TYPE;
  lsCodigoPais          SEG_PAIS.COD_PAIS%TYPE;
BEGIN

  --Se verifica si tiene primer contacto
  SELECT COUNT(1)
    INTO lnTotalOcurrencias
    FROM MAE_CLIEN_PRIME_CONTA
   WHERE CLIE_OID_CLIE = pnOidCliente;

  ldFechaContacto := NULL;
  IF(lnTotalOcurrencias > 0) THEN
  --Obtenemos la Fecha de Contacto del Cliente
  SELECT FEC_CONT
    INTO ldFechaContacto
    FROM MAE_CLIEN_PRIME_CONTA
   WHERE CLIE_OID_CLIE = pnOidCliente;
  END IF;

  IF(ldFechaContacto IS NULL) THEN
    --Se reecalcula la fecha de contacto en base al histórico de estatus y a los pedidos del cliente
    SELECT MAX(mh.perd_oid_peri)
      INTO lnOidPeriodo
      FROM mae_clien_histo_estat mh
     WHERE mh.clie_oid_clie = pnOidCliente
       AND mh.esta_oid_esta_clie in (2, 8);

    SELECT MIN(x.fec_fact)
      INTO ldFechaContacto
      FROM ped_solic_cabec x, ped_solic_cabec y, PED_TIPO_SOLIC_PAIS TSPA, ped_tipo_solic tsol
     WHERE x.soca_oid_soli_cabe = y.oid_soli_cabe
       AND x.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
       AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
       AND x.clie_oid_clie = pnOidCliente
       AND x.perd_oid_peri = lnOidPeriodo
       AND tsol.cod_tipo_soli = 'SOC'
       AND x.grpr_oid_grup_proc = 5
       AND x.fec_fact IS NOT NULL
       AND y.esso_oid_esta_soli <> 4;

    --De no tener pedidos, obtiene la fecha de ingreso de la entidad Maestro de Clientes
    IF(ldFechaContacto IS NULL) THEN
      SELECT FEC_INGR
        INTO ldFechaContacto
        FROM MAE_CLIEN
       WHERE OID_CLIE = pnOidCliente;
    END IF;
  END IF;

  --Obtenemos el tipoSubtipo principal del cliente
  SELECT OID_CLIE_TIPO_SUBT
    INTO lnOidSubTipo
    FROM MAE_CLIEN_TIPO_SUBTI
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND IND_PPAL = 1;

  IF(lnTotalOcurrencias > 0) THEN
    UPDATE MAE_CLIEN_PRIME_CONTA
       SET FEC_CONT = ldFechaContacto,
           CTSU_CLIE_CONT = lnOidSubTipo
     WHERE CLIE_OID_CLIE = pnOidCliente;
  ELSE
    --Obtenemos la Fecha Siguiente Contacto = Fecha Contacto + 1 Año
    ldFechaSigContacto := ADD_MONTHS(ldFechaContacto, 12);

    --Obtenemos el oidMarca, oidCanal, oidPeriodo
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

    --Obtenemos el oidPais
    SELECT PAIS_OID_PAIS INTO lnOidPais FROM MAE_CLIEN WHERE OID_CLIE = pnOidCliente;

    --Obtenemos el codigo Pais
    SELECT COD_PAIS INTO lsCodigoPais FROM SEG_PAIS WHERE OID_PAIS = lnOidPais;

    --Obtenemos el Oid Periodo
    lsCodPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(lsCodigoPais,'T','VD', ldFechaContacto);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodo, lnOidMarca, lnOidCanal);

    INSERT INTO MAE_CLIEN_PRIME_CONTA
      (OID_CLIE_PRIM_CONT, CLIE_OID_CLIE, CTSU_CLIE_CONT,
       FEC_CONT, FEC_SIGU_CONT, COD_TIPO_CONT,
       MARC_OID_MARC, CANA_OID_CANA, PERD_OID_PERI)
    VALUES
      (MAE_CPRC_SEQ.NEXTVAL, pnOidCliente, lnOidSubTipo,
       ldFechaContacto, ldFechaSigContacto, 'I',
       lnOidMarca, lnOidCanal, lnOidPeriodo);

  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_PRIME_CONTA: ' || ls_sqlerrm);

END MAE_PR_ACTUA_PRIME_CONTA;

/*********************************************************************************
Descripcion       : Actualiza Niveles de Riesgo del Cliente al Cierre de Campaña
Fecha Creacion    : 24/11/2011
Parametros Entrada:

  pnOidCliente    :

Autor             :
**********************************************************************************/
PROCEDURE MAE_PR_ACTUA_NIVEL_RIESG
IS

BEGIN
  -- Actualiza Nivel Riesgo "Consultora Antigua" a consultoras con Nivel Riesgo "4to Pedido" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 3,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 9
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL ;

  -- Actualiza Nivel Riesgo "6to Pedido" a consultoras con Nivel Riesgo "5to Pedido" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 9,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 8
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL ;

  -- Actualiza Nivel Riesgo "5to Pedido" a consultoras con Nivel Riesgo "4to Pedido" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 8,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 7
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL ;

  -- Actualiza Nivel Riesgo "4to Pedido" a consultoras con Nivel Riesgo "3er Pedido" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 7,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 6
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL ;

  -- Actualiza Nivel Riesgo "3er Pedido" a consultoras con Nivel Riesgo "2do Pedido" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 6,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 5
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL;

  -- Actualiza Nivel Riesgo "2do Pedido" a consultoras con Nivel Riesgo "Nuevas" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 5,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries = 4
     AND a.ind_acti = 1
     AND NVL(a.perd_oid_peri_nive_ries,0) <> (
                                              SELECT MAX (perd_oid_peri)
                                                FROM fac_contr_cierr
                                               WHERE tcie_oid_tipo_cier = 3
                                             )
     AND (
          SELECT MAX (oid_soli_cabe)
            FROM ped_solic_cabec,
                  ped_tipo_solic_pais,
                 ped_tipo_solic
           WHERE perd_oid_peri = (
                                  SELECT MAX (perd_oid_peri)
                                    FROM fac_contr_cierr
                                   WHERE tcie_oid_tipo_cier = 3
                                 )
             AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
             AND tsol_oid_tipo_soli = oid_tipo_soli
             AND cod_tipo_soli = 'SOC'
             AND clie_oid_clie = a.clie_oid_clie
             AND fec_fact IS NOT NULL
         ) IS NOT NULL;


  -- Actualiza Nivel Riesgo "Nuevas" a consultoras con Estatus "Registrada" ó "Retirada" --
  UPDATE mae_clien_datos_adici a
     SET a.niri_oid_nive_ries = 4,
         a.perd_oid_peri_nive_ries = (
                                      SELECT MAX (perd_oid_peri)
                                        FROM fac_contr_cierr
                                       WHERE tcie_oid_tipo_cier = 3
                                     )
   WHERE a.niri_oid_nive_ries NOT IN NVL((
                                      SELECT oid_nive_ries
                                        FROM car_agrup_nivri
                                       WHERE num_pedi = 1
                                     ),4)
     AND a.esta_oid_esta_clie IN (1,7) ;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_NIVEL_RIESG: ' || ls_sqlerrm);

END MAE_PR_ACTUA_NIVEL_RIESG;


/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo MOD11V, aplicado al tipo de Documento RUT
Fecha Creacion    : 12/06/2012
Parametros Entrada:
  psNumeroDocumento        :     Numero Documento

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_NUMER_DOCUM_MOD11
  (psNumeroDocumento        VARCHAR2)
RETURN NUMBER
IS
  lnOidParaGral          INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lnIndPremCampEfec      INC_CONCU_PARAM_CONSU.IND_PREM_CAMP_EFEC%TYPE;
  lnResultado            NUMBER;

  lsDigitoVerif          VARCHAR2(15);
  lsParteNumerica        VARCHAR2(15);
  lnTotal                NUMBER;
  lnMultiplo             NUMBER;
  lnTamanio              NUMBER;
  lnDigito               NUMBER;

BEGIN
  lnResultado := 1;

  --Extrae digito verific
  lsDigitoVerif := SUBSTR(psNumeroDocumento, -1);

  --Extrae parte  numerica
  lsParteNumerica := SUBSTR(psNumeroDocumento, 1, LENGTH(psNumeroDocumento) - 1);

  lnTotal := 0;
  lnMultiplo := 2;
  lnTamanio := LENGTH(lsParteNumerica);

  FOR J IN REVERSE 1 .. lnTamanio LOOP
    lnTotal := lnTotal + TO_NUMBER(SUBSTR(lsParteNumerica, j, 1)) * lnMultiplo;

    IF(lnMultiplo < 7) THEN
      lnMultiplo := lnMultiplo + 1;
    ELSE
      lnMultiplo := 2;
    END IF;
  END LOOP;

  --Calculo Digito
  lnDigito := 11 - MOD(lnTotal, 11);

  IF((lnDigito = 10) AND (lsDigitoVerif <> 'K')) THEN
    lnResultado := 0;
  ELSIF ((lnDigito = 11) AND (lsDigitoVerif <> '0')) THEN
    lnResultado := 0;
  ELSIF ((lnDigito < 10) AND (TO_CHAR(lnDigito) <> lsDigitoVerif)) THEN
    lnResultado := 0;
  END IF;

  RETURN lnResultado;

EXCEPTION
  WHEN OTHERS THEN
    RETURN 0;

END MAE_FN_VALID_NUMER_DOCUM_MOD11;


/**************************************************************************
Descripcion        : Obtiene la ultima secuencia para que sea utilizada
                     en la generacion del codigo de Cliente
Fecha Creacion     : 14/06/2012
Parametros Entrada:
  psCodigoPais        :    Codigo de Pais

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTEN_SECUE_CODIG_CLIEN
  (psCodigoPais       VARCHAR2)
RETURN NUMBER IS
  PRAGMA AUTONOMOUS_TRANSACTION;

  lnNumeroSecuencia      NUMBER;
  lnOcurrencias          NUMBER;
  lnSecuencia            NUMBER;
BEGIN

  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM BAS_PARAM_PAIS
   WHERE COD_PAIS = psCodigoPais
     AND COD_SIST = 'MAE'
     AND NOM_PARA = 'secuenciaCliente'
     --AND COD_PARA = '003'
     AND IND_ACTI = '1';

  lnNumeroSecuencia := NULL;
  IF(lnOcurrencias > 0) THEN
    BEGIN
      SELECT NUM_SECU
        INTO lnNumeroSecuencia
        FROM MAE_SECUE_CLIEN
       WHERE COD_CLIE IS NULL
         AND ROWNUM = 1 FOR UPDATE;

    UPDATE MAE_SECUE_CLIEN
       SET COD_CLIE = LPAD(lnNumeroSecuencia, 7, '0')
     WHERE NUM_SECU = lnNumeroSecuencia;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        UPDATE BAS_PARAM_PAIS
           SET IND_ACTI = '0'
         WHERE COD_PAIS = psCodigoPais
           AND COD_SIST = 'MAE'
           AND NOM_PARA = 'secuenciaCliente';
           --AND COD_PARA = '003';

      WHEN OTHERS THEN
        NULL;
    END;
  END IF;

  IF(lnNumeroSecuencia IS NULL) THEN
    SELECT VAL_ULTI_NUME_SOLI
      INTO lnNumeroSecuencia
      FROM PED_NUMER_SOLIC ns
     WHERE ns.val_oper = 'MAECLT'
       AND ns.cod_pais = psCodigoPais FOR UPDATE;

    lnNumeroSecuencia := lnNumeroSecuencia + 1;

    UPDATE ped_numer_solic ns
       SET ns.VAL_ULTI_NUME_SOLI = lnNumeroSecuencia
     WHERE ns.val_oper = 'MAECLT'
       AND ns.cod_pais = psCodigoPais;
  END IF;

  COMMIT;

  RETURN lnNumeroSecuencia;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         250);
    raise_application_error(-20123,
                            'ERROR MAE_FN_OBTEN_SECUE_CODIG_CLIEN: ' || ls_sqlerrm);

END MAE_FN_OBTEN_SECUE_CODIG_CLIEN;


/**************************************************************************
Descripcion       : Verifica si el cliente ha ingresado Direccion de Vacaciones
                    y se validara si se le agrega clasificacion de Vacaciones
Fecha Creacion    : 16/07/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_DIREC_VACAC
  (psCodigoPais         VARCHAR2,
   pnOidCliente         NUMBER,
   psCodigoUsuario      VARCHAR2)
IS
  lsCodPeriodoProc      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoIni       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoFin       SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnOidPeriodo          CRA_PERIO.OID_PERI%TYPE;
  lnOidSubTipo          MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
  lsCodTipoClasi        MAE_TIPO_CLASI_CLIEN.COD_TIPO_CLAS%TYPE;
  lsCodClasi            MAE_CLASI.COD_CLAS%TYPE;
  lnOcurrencias         NUMBER;

  lnOidClieSubTipo      MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
  lnOidTipoClasi        MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
  lnOidClasi            MAE_CLASI.OID_CLAS%TYPE;

BEGIN
  --Verificamos si tiene Direccion de Vacaciones Activo
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM MAE_CLIEN_DIREC dir, MAE_TIPO_DIREC tip
   WHERE dir.TIDC_OID_TIPO_DIRE = tip.OID_TIPO_DIRE
     AND tip.COD_TIPO_DIRE = '08'
     AND dir.CLIE_OID_CLIE = pnOidCliente
     AND dir.IND_ELIM = 0;

  IF(lnOcurrencias > 0) THEN
    --Obtenemos la Campaña de Proceso
    SELECT COD_PERI
      INTO lsCodPeriodoProc
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = 0
       AND IND_CAMP_ACT = 1;

    --Recuperamos el codigo Perido Inicio y Codigo Periodo Fin
    SELECT MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_INIC),
           MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_FINA),
           dir.PERD_OID_CAMP_INIC
      INTO lsCodPeriodoIni,
           lsCodPeriodoFin,
           lnOidPeriodo
      FROM MAE_CLIEN_DIREC dir, MAE_TIPO_DIREC tip
     WHERE dir.TIDC_OID_TIPO_DIRE = tip.OID_TIPO_DIRE
       AND tip.COD_TIPO_DIRE = '08'
       AND dir.CLIE_OID_CLIE = pnOidCliente
       AND dir.IND_ELIM = 0;

    --Verifica Si la Campaña de Inicio de la Dirección de Vacaciones = Campaña de Proceso
    IF(lsCodPeriodoIni = lsCodPeriodoProc) THEN

      --Si el SubTipoCliente Principal esta configurado en Rangos de Vacaciones
      --y si es asi, se verifica si se le ha ingresado su clasificacion de Vacaciones, si
      --no tiene, se le ingrese una clasificacion 'Vacaciones'
      BEGIN
        SELECT sub.OID_CLIE_TIPO_SUBT,
               sti.OID_SUBT_CLIE,
               par.COD_TIPO_CLAS,
               par.COD_CLAS
          INTO lnOidClieSubTipo,
               lnOidSubTipo,
               lsCodTipoClasi,
               lsCodClasi
          FROM MAE_CLIEN_TIPO_SUBTI sub,
               MAE_PARAM_DIREC_VACAC  par,
               MAE_TIPO_CLIEN tip,
               MAE_SUBTI_CLIEN sti
         WHERE sub.Clie_Oid_Clie = pnOidCliente
           AND sub.IND_PPAL = 1
           AND sub.SBTI_OID_SUBT_CLIE = sti.OID_SUBT_CLIE
           AND sti.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
           AND tip.COD_TIPO_CLIE = par.COD_TIPO_CLIE
           AND sti.COD_SUBT_CLIE = par.COD_SUBT_CLIE
           AND par.CAM_INIC <= lsCodPeriodoProc
           AND lsCodPeriodoProc <= par.CAM_FINA

           AND NOT EXISTS (
                SELECT mcl.oid_clie_clas
                  FROM MAE_CLIEN_CLASI mcl,
                       MAE_PARAM_DIREC_VACAC pca,
                       MAE_TIPO_CLASI_CLIEN tcc,
                       MAE_CLASI cla
                 WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                   AND pca.cod_dire_vaca = par.cod_dire_vaca
                   AND tcc.sbti_oid_subt_clie = sti.oid_subt_clie
                   AND tcc.oid_tipo_clas = mcl.tccl_oid_tipo_clasi
                   AND tcc.cod_tipo_clas = pca.cod_tipo_clas
                   AND cla.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                   AND cla.cod_clas = pca.cod_clas
                   AND cla.oid_clas = mcl.clas_oid_clas);
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lnOidSubTipo := NULL;
      END;

      IF(lnOidSubTipo IS NOT NULL) THEN
        SELECT OID_TIPO_CLAS
          INTO lnOidTipoClasi
          FROM MAE_TIPO_CLASI_CLIEN
         WHERE SBTI_OID_SUBT_CLIE = lnOidSubTipo
           AND COD_TIPO_CLAS = lsCodTipoClasi;

        SELECT OID_CLAS
          INTO lnOidClasi
          FROM MAE_CLASI
         WHERE TCCL_OID_TIPO_CLAS = lnOidTipoClasi
           AND COD_CLAS = lsCodClasi;

        INSERT INTO MAE_CLIEN_CLASI
          (OID_CLIE_CLAS,
           CTSU_OID_CLIE_TIPO_SUBT,
           CLAS_OID_CLAS,
           PERD_OID_PERI,
           TCCL_OID_TIPO_CLASI,
           FEC_CLAS,
           IND_PPAL,
           FEC_ULTI_ACTU,
           USU_MODI)
        VALUES
          (MAE_CLCL_SEQ.nextval,
           lnOidClieSubTipo,
           lnOidClasi,
           lnOidPeriodo,
           lnOidTipoClasi,
           TRUNC(SYSDATE),
           0,
           SYSDATE,
           psCodigoUsuario);

      END IF;

    END IF;

  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CLASI_DIREC_VACAC: ' || ls_sqlerrm);

END MAE_PR_ACTUA_CLASI_DIREC_VACAC;


/**************************************************************************
Descripcion       : Verifica si el cliente ha ingresado Direccion de Vacaciones
                    y se eliminar la clasificacion de Vacaciones
Fecha Creacion    : 16/07/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ELIMI_CLASI_DIREC_VACAC
  (psCodigoPais         VARCHAR2,
   pnOidCliente         NUMBER)
IS
  lsCodigoPeriodo   SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
  --Obtiene el Periodo de Proceso
  SELECT COD_PERI
    INTO lsCodigoPeriodo
    FROM BAS_CTRL_FACT
   WHERE COD_PAIS = psCodigoPais
     AND STA_CAMP = 0
     AND IND_CAMP_ACT = 1;

    DELETE FROM MAE_CLIEN_CLASI
     WHERE OID_CLIE_CLAS IN (
                  SELECT mcl.OID_CLIE_CLAS
                        FROM MAE_CLIEN_TIPO_SUBTI sub,
                             MAE_CLIEN_CLASI mcl,
                             MAE_PARAM_DIREC_VACAC  par,
                             MAE_TIPO_CLIEN tip,
                             MAE_SUBTI_CLIEN sti,
                             MAE_TIPO_CLASI_CLIEN tcc,
                             MAE_CLASI cla
                     WHERE sub.Clie_Oid_Clie = pnOidCliente
                         AND sub.IND_PPAL = 1
                         AND mcl.CTSU_OID_CLIE_TIPO_SUBT = sub.OID_CLIE_TIPO_SUBT
                         AND sub.SBTI_OID_SUBT_CLIE = sti.OID_SUBT_CLIE
                         AND sti.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
                         AND tip.COD_TIPO_CLIE = par.COD_TIPO_CLIE
                         AND sti.COD_SUBT_CLIE = par.COD_SUBT_CLIE
                         AND tcc.sbti_oid_subt_clie = sti.oid_subt_clie
                         AND tcc.oid_tipo_clas = mcl.tccl_oid_tipo_clasi
                         AND tcc.cod_tipo_clas = par.cod_tipo_clas
                         AND cla.tccl_oid_tipo_clas = tcc.oid_tipo_clas
                         AND cla.cod_clas = par.cod_clas
                         AND cla.oid_clas = mcl.clas_oid_clas
                         AND par.CAM_INIC <= lsCodigoPeriodo
                         AND lsCodigoPeriodo <= par.CAM_FINA);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ELIMI_CLASI_DIREC_VACAC: ' || ls_sqlerrm);

END MAE_PR_ELIMI_CLASI_DIREC_VACAC;


/**************************************************************************
Descripcion        : Obtiene el codigo de Periodo en base al Oid Periodo
Fecha Creacion     : 18/07/2012
Parametros Entrada :
           pnOidPeriodo : Oid Periodo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTIE_CODIG_PERIO(pnOidPeriodo  NUMBER) RETURN VARCHAR2 IS
  lsCodigoPeriodo    SEG_PERIO_CORPO.COD_PERI%TYPE;

BEGIN

  IF(pnOidPeriodo IS NULL) THEN
    RETURN NULL;
  END IF;

  SELECT cor.COD_PERI
    INTO lsCodigoPeriodo
    FROM CRA_PERIO cra, SEG_PERIO_CORPO cor
   WHERE cra.OID_PERI = pnOidPeriodo
     AND cra.PERI_OID_PERI = cor.OID_PERI;

  RETURN lsCodigoPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR MAE_FN_OBTIE_CODIG_PERIO: (' ||
                            ln_sqlcode || ')' || ls_sqlerrm);
    RETURN NULL;

END MAE_FN_OBTIE_CODIG_PERIO;


/**************************************************************************
Descripcion       : Activar Entrega en Dirección de Vacaciones
Fecha Creacion    : 18/07/2012
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTIV_CLASI_DIREC_VACAC
  (psCodigoPais          VARCHAR2,
   psCodigoMarca         VARCHAR2,
   psCodigoCanal         VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2)
IS

  lnOidPais      SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca     SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal     SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo   CRA_PERIO.OID_PERI%TYPE;

  CURSOR c_clientesNuevos(oidTipoCliente NUMBER, oidSubTipoCliente NUMBER,
                          oidTipoClasificacion NUMBER, oidClasificacion NUMBER) IS
    SELECT sub.OID_CLIE_TIPO_SUBT,
           MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_INIC) CAM_INIC,
           MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_FINA) CAM_FINA
      FROM MAE_CLIEN_TIPO_SUBTI sub,
           MAE_CLIEN_DIREC dir,
           MAE_TIPO_DIREC tip
     WHERE sub.SBTI_OID_SUBT_CLIE = oidSubTipoCliente
       AND sub.TICL_OID_TIPO_CLIE = oidTipoCliente
       AND sub.IND_PPAL = 1
       AND dir.CLIE_OID_CLIE = sub.CLIE_OID_CLIE
       AND dir.IND_ELIM = 0
       AND dir.TIDC_OID_TIPO_DIRE = tip.OID_TIPO_DIRE
       AND tip.COD_TIPO_DIRE = '08'

       AND NOT EXISTS (
           SELECT mcl.oid_clie_clas
             FROM MAE_CLIEN_CLASI mcl
            WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
              AND oidTipoClasificacion = mcl.tccl_oid_tipo_clasi
              AND oidClasificacion = mcl.clas_oid_clas);

  TYPE interfazClientesNuevos IS RECORD
  (
   oidClienteSubTipo         MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE,
   codPeriodoInicio          SEG_PERIO_CORPO.COD_PERI%TYPE,
   codPeriodoFin             SEG_PERIO_CORPO.COD_PERI%TYPE
  );

  TYPE interfazClientesNuevosTab  IS TABLE OF interfazClientesNuevos;
  interfazRecordN interfazClientesNuevosTab;

  TYPE t_oidClasiCli         IS TABLE OF MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE ;
  TYPE t_oidsubTipoCli       IS TABLE OF MAE_CLIEN_CLASI.CTSU_OID_CLIE_TIPO_SUBT%TYPE ;
  TYPE t_clasificacion       IS TABLE OF MAE_CLIEN_CLASI.CLAS_OID_CLAS%TYPE ;
  TYPE t_periodo             IS TABLE OF MAE_CLIEN_CLASI.PERD_OID_PERI%TYPE ;
  TYPE t_tipoClasi           IS TABLE OF MAE_CLIEN_CLASI.TCCL_OID_TIPO_CLASI%TYPE ;
  TYPE t_fechaClasi          IS TABLE OF MAE_CLIEN_CLASI.FEC_CLAS%TYPE ;
  TYPE t_indPrincipal        IS TABLE OF MAE_CLIEN_CLASI.IND_PPAL%TYPE ;

  v_oidClasiCli              t_oidClasiCli  := t_oidClasiCli() ;
  v_oidsubTipoCli            t_oidsubTipoCli  := t_oidsubTipoCli() ;
  v_clasificacion            t_clasificacion  := t_clasificacion() ;
  v_periodo                  t_periodo  := t_periodo() ;
  v_tipoClasi                t_tipoClasi  := t_tipoClasi() ;
  v_fechaClasi               t_fechaClasi  := t_fechaClasi() ;
  v_indPrincipal             t_indPrincipal  := t_indPrincipal() ;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  FOR x IN (SELECT CAM_INIC, CAM_FINA,
                   TCL.OID_TIPO_CLIE, SUB.OID_SUBT_CLIE,
                   TCC.OID_TIPO_CLAS, CLA.OID_CLAS
              FROM MAE_PARAM_DIREC_VACAC PAR,
                   MAE_TIPO_CLIEN TCL,
                   MAE_SUBTI_CLIEN SUB,
                   MAE_TIPO_CLASI_CLIEN TCC,
                   MAE_CLASI CLA
             WHERE CAM_INIC <= psCodigoPeriodo
               AND psCodigoPeriodo <= par.CAM_FINA
               AND tcl.COD_TIPO_CLIE = par.COD_TIPO_CLIE
               AND sub.COD_SUBT_CLIE = par.COD_SUBT_CLIE
               AND sub.TICL_OID_TIPO_CLIE = tcl.OID_TIPO_CLIE
               AND tcc.COD_TIPO_CLAS = par.COD_TIPO_CLAS
               AND tcc.SBTI_OID_SUBT_CLIE = sub.OID_SUBT_CLIE
               AND cla.COD_CLAS = par.COD_CLAS
               AND cla.TCCL_OID_TIPO_CLAS = tcc.OID_TIPO_CLAS
               ) LOOP

    --(1) PROCESAMOS A LOS CLIENTES CON DIRECCIONES DE VACACIONES
    OPEN c_clientesNuevos(x.OID_TIPO_CLIE, x.OID_SUBT_CLIE, x.OID_TIPO_CLAS, x.OID_CLAS);
    LOOP
       FETCH c_clientesNuevos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR y IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

              IF((interfazRecordN(y).codPeriodoInicio <= psCodigoPeriodo) AND
                 (psCodigoPeriodo <= interfazRecordN(y).codPeriodoFin)) THEN

                --Insertamos la clasificacion de Direccion de Vacaciones
                v_oidsubTipoCli.EXTEND(1);
                v_clasificacion.EXTEND(1);
                v_periodo.EXTEND(1);
                v_tipoClasi.EXTEND(1);
                v_fechaClasi.EXTEND(1);
                v_indPrincipal.EXTEND(1);

                v_oidsubTipoCli(v_oidsubTipoCli.COUNT) := interfazRecordN(y).oidClienteSubTipo;
                v_clasificacion(v_clasificacion.COUNT) := x.OID_CLAS;
                v_periodo(v_periodo.COUNT) := lnOidPeriodo;
                v_tipoClasi(v_tipoClasi.COUNT) := x.OID_TIPO_CLAS;
                v_fechaClasi(v_fechaClasi.COUNT) := trunc(SYSDATE);
                v_indPrincipal(v_indPrincipal.COUNT) := 0;
              END IF;

          END LOOP;

       END IF;
       EXIT WHEN c_clientesNuevos%NOTFOUND;
    END LOOP;
    CLOSE c_clientesNuevos;

  END LOOP;

  --Eliminamos la clasificacion de Direccion de Vacaciones
  FORALL i IN 1..v_oidsubTipoCli.COUNT
    INSERT INTO MAE_CLIEN_CLASI
      (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
       PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL,
       FEC_ULTI_ACTU, USU_MODI)
    VALUES
       (MAE_CLCL_SEQ.nextval, v_oidsubTipoCli(i), v_clasificacion(i),
        v_periodo(i), v_tipoClasi(i), v_fechaClasi(i), v_indPrincipal(i),
        SYSDATE, psCodigoUsuario);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTIV_CLASI_DIREC_VACAC: ' || ls_sqlerrm);

END MAE_PR_ACTIV_CLASI_DIREC_VACAC;


/**************************************************************************
Descripcion       : Desactivar Entrega en Dirección de Vacaciones
Fecha Creacion    : 18/07/2012
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_DESAC_CLASI_DIREC_VACAC
  (psCodigoPais          VARCHAR2,
   psCodigoMarca         VARCHAR2,
   psCodigoCanal         VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2)
IS

  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo      CRA_PERIO.OID_PERI%TYPE;
  lsCodPeriodoAnt   SEG_PERIO_CORPO.COD_PERI%TYPE;

  CURSOR c_clientesNuevos(oidTipoCliente NUMBER, oidSubTipoCliente NUMBER,
                          oidTipoClasificacion NUMBER, oidClasificacion NUMBER) IS
    SELECT mcl.OID_CLIE_CLAS,
           MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_INIC) CAM_INIC,
           MAE_PKG_PROCE_CLIEN.MAE_FN_OBTIE_CODIG_PERIO(dir.PERD_OID_CAMP_FINA) CAM_FINA
      FROM MAE_CLIEN_TIPO_SUBTI sub,
           MAE_CLIEN_DIREC dir,
           MAE_TIPO_DIREC tip,
           MAE_CLIEN_CLASI mcl
     WHERE sub.SBTI_OID_SUBT_CLIE = oidSubTipoCliente
       AND sub.TICL_OID_TIPO_CLIE = oidTipoCliente
       AND sub.IND_PPAL = 1
       AND dir.CLIE_OID_CLIE = sub.CLIE_OID_CLIE
       AND dir.IND_ELIM = 0
       AND dir.TIDC_OID_TIPO_DIRE = tip.OID_TIPO_DIRE
       AND tip.COD_TIPO_DIRE = '08'
       AND mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
       AND mcl.tccl_oid_tipo_clasi = oidTipoClasificacion
       AND mcl.clas_oid_clas = oidClasificacion;

  TYPE interfazClientesNuevos IS RECORD
  (
   oidClienteClasi           MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE,
   codPeriodoInicio          SEG_PERIO_CORPO.COD_PERI%TYPE,
   codPeriodoFin             SEG_PERIO_CORPO.COD_PERI%TYPE
  );

  TYPE interfazClientesNuevosTab  IS TABLE OF interfazClientesNuevos;
  interfazRecordN interfazClientesNuevosTab;

  TYPE t_oidClasiCli         IS TABLE OF MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE ;
  v_oidClasiCli              t_oidClasiCli  := t_oidClasiCli() ;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --RECUPERAMOS EL PERIODO ANTERIOR
  lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                          lnOidPais, lnOidMarca, lnOidCanal,-1);

  FOR x IN (SELECT CAM_INIC, CAM_FINA,
                   TCL.OID_TIPO_CLIE, SUB.OID_SUBT_CLIE,
                   TCC.OID_TIPO_CLAS, CLA.OID_CLAS
              FROM MAE_PARAM_DIREC_VACAC PAR,
                   MAE_TIPO_CLIEN TCL,
                   MAE_SUBTI_CLIEN SUB,
                   MAE_TIPO_CLASI_CLIEN TCC,
                   MAE_CLASI CLA
             WHERE CAM_INIC <= psCodigoPeriodo
               AND psCodigoPeriodo <= par.CAM_FINA
               AND tcl.COD_TIPO_CLIE = par.COD_TIPO_CLIE
               AND sub.COD_SUBT_CLIE = par.COD_SUBT_CLIE
               AND sub.TICL_OID_TIPO_CLIE = tcl.OID_TIPO_CLIE
               AND tcc.COD_TIPO_CLAS = par.COD_TIPO_CLAS
               AND tcc.SBTI_OID_SUBT_CLIE = sub.OID_SUBT_CLIE
               AND cla.COD_CLAS = par.COD_CLAS
               AND cla.TCCL_OID_TIPO_CLAS = tcc.OID_TIPO_CLAS
               ) LOOP

    --(1) PROCESAMOS A LOS CLIENTES CON DIRECCIONES DE VACACIONES
    --QUE TIENE CLASIFICACION DE VACACIONES
    OPEN c_clientesNuevos(x.OID_TIPO_CLIE, x.OID_SUBT_CLIE, x.OID_TIPO_CLAS, x.OID_CLAS);
    LOOP
       FETCH c_clientesNuevos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR y IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

              IF((interfazRecordN(y).codPeriodoFin = psCodigoPeriodo) OR
                 (lsCodPeriodoAnt = interfazRecordN(y).codPeriodoFin)) THEN

                --Eliminamos la clasificacion de Direccion de Vacaciones
                v_oidClasiCli.EXTEND(1);
                v_oidClasiCli(v_oidClasiCli.COUNT) := interfazRecordN(y).oidClienteClasi;
              END IF;

          END LOOP;

       END IF;
       EXIT WHEN c_clientesNuevos%NOTFOUND;
    END LOOP;
    CLOSE c_clientesNuevos;

  END LOOP;

  --Eliminamos la clasificacion Direccion de Vacaciones
  FORALL i IN 1..v_oidClasiCli.COUNT
    DELETE FROM MAE_CLIEN_CLASI
     WHERE OID_CLIE_CLAS = v_oidClasiCli(i);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_DESAC_CLASI_DIREC_VACAC: ' || ls_sqlerrm);

END MAE_PR_DESAC_CLASI_DIREC_VACAC;


/**************************************************************************
Descripcion       : Valida si se puede Bloquear/Desbloquer el cliente
Fecha Creacion    : 17/08/2012
Parametros Entrada:
  psCodigoCliente       :     Codigo Cliente
  psAccionBloqueo       :     Accion Bloqueo: B-Bloquear, D-Desbloquear
  psTipoBloqueo         :     Tipo Bloqueo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_BLOQU_DESBL_CLIEN
  (psCodigoCliente        VARCHAR2,
   psAccionBloqueo        VARCHAR2,
   psTipoBloqueo          VARCHAR2)
RETURN VARCHAR2
IS
  lnOidCliente      MAE_CLIEN.OID_CLIE%TYPE;
  lnOcurrencias     NUMBER;
  lnParametro       NUMBER;
  lsResultado       VARCHAR2(2);
  lnCodigoPais      VARCHAR2(3);
  lnCodigoPeriodo   VARCHAR2(6);
  lnOidClienteNoAct      MAE_CLIEN.OID_CLIE%TYPE;
  lbEncontrado      BOOLEAN;
BEGIN
    lsResultado := '00';

    --El Sistema busca al cliente
    BEGIN
        SELECT CLIE_OID_CLIE
        INTO lnOidCliente
        FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi
        WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
        AND cli.COD_CLIE = psCodigoCliente;

        lnOcurrencias := 1;
    EXCEPTION
        WHEN OTHERS THEN
            lnOcurrencias := 0;
    END;

    IF(lnOcurrencias > 0) THEN

        -- verificando q cliente exista pero con indicador 1 ACTIVO
        lnOcurrencias := 0;
        begin
             SELECT CLIE_OID_CLIE
              INTO lnOidClienteNoAct
              FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi
             WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
               AND cli.COD_CLIE = psCodigoCliente
               AND adi.IND_ACTI = 1;

               lnOcurrencias := 1;
        EXCEPTION
            WHEN OTHERS THEN
                lnOcurrencias := 0;
        END;

        IF(lnOcurrencias > 0) THEN
            --Cliente existe y está activo
            IF(psAccionBloqueo = 'B') THEN
                -- Validaciones para bloqueo de clientes

                --Verifica si el Cliente tiene al menos un bloqueo vigente
                SELECT COUNT(1)
                INTO lnOcurrencias
                FROM MAE_CLIEN_BLOQU blo,
                     MAE_TIPO_BLOQU tip
                WHERE blo.CLIE_OID_CLIE = lnOidCliente
                AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                AND tip.COD_TIPO_BLOQ = psTipoBloqueo
                AND blo.FEC_DESB IS NULL
                AND blo.FEC_BLOQ IS NOT NULL;

                IF(lnOcurrencias = 0) THEN
                    SELECT COUNT(1)
                    INTO lnOcurrencias
                    FROM MAE_CLIEN_BLOQU blo,
                       MAE_TIPO_BLOQU tip,
                       MAE_TIPO_BLOQU tipx
                    WHERE blo.CLIE_OID_CLIE = lnOidCliente
                    AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                    AND blo.FEC_DESB IS NULL
                    AND blo.FEC_BLOQ IS NOT NULL
                    AND tipx.COD_TIPO_BLOQ = psTipoBloqueo
                    AND tip.NUM_NIVE_GRAV_BLOQ > tipx.NUM_NIVE_GRAV_BLOQ;

                    IF(lnOcurrencias > 0) THEN
                        lsResultado := '06';
                    END IF;

                    --NUEVA VALIDACION DE PEDIDOS
                    --OBTENEMOS EL PAIS

                    SELECT SP.COD_PAIS
                      INTO lnCodigoPais
                      FROM SEG_PAIS SP, MAE_CLIEN MC
                     WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
                       AND MC.COD_CLIE = psCodigoCliente;

                        SELECT COUNT(1)
                          INTO lnParametro
                          FROM BAS_PARAM_PAIS
                         WHERE COD_PAIS = lnCodigoPais
                           AND COD_SIST = 'MAE'
                           AND NOM_PARA = 'indBloqClienPreFacturacion'
                           AND VAL_PARA = '1'
                           AND IND_ACTI = '1';

                       IF (lnParametro > 0) THEN

                           --OBTENEMOS EL PERIODO
                         SELECT DISTINCT SEG_PERIO_CORPO.COD_PERI
                           INTO lnCodigoPeriodo
                           FROM SEG_PAIS, SEG_CANAL, CRA_PERIO, SEG_PERIO_CORPO
                          WHERE ((SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
                                (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
                                (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
                                (SEG_PAIS.COD_PAIS = lnCodigoPais) AND (SEG_CANAL.COD_CANA = 'VD') AND
                                (CRA_PERIO.FEC_INIC <= TRUNC(SYSDATE)) AND
                                (CRA_PERIO.FEC_FINA >= TRUNC(SYSDATE)) AND ROWNUM = 1)
                          order by SEG_PERIO_CORPO.COD_PERI;

                          SELECT count(1)
                            INTO lnParametro
                            FROM PED_SOLIC_CABEC PSC, MAE_CLIEN MC
                           WHERE PSC.CLIE_OID_CLIE = MC.OID_CLIE
                             AND MC.COD_CLIE = psCodigoCliente
                             AND PSC.TSPA_OID_TIPO_SOLI_PAIS = fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC')
                             AND PSC.GRPR_OID_GRUP_PROC IN (1, 2, 3)
                             AND PSC.PERD_OID_PERI = OCR_SOLIC_PEDIDOS.GEN_FN_DEVUELVE_OID_CRA_PERIO(lnCodigoPeriodo)
                             AND PSC.FEC_FACT IS NULL;

                             IF (lnParametro > 0) THEN
                                lsResultado := '10';
                             END IF;

                       END IF;

                ELSE
                    lsResultado := '05';
                END IF;

            END IF;

            IF(psAccionBloqueo = 'D') THEN

                lbEncontrado := FALSE;
                lnOcurrencias := 0;

                FOR x IN (SELECT tip.COD_TIPO_BLOQ
                          FROM MAE_CLIEN_BLOQU blo,
                               MAE_TIPO_BLOQU tip
                         WHERE blo.CLIE_OID_CLIE = lnOidCliente
                           AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                           AND blo.FEC_DESB IS NULL
                           AND blo.FEC_BLOQ IS NOT NULL) LOOP

                    IF(x.COD_TIPO_BLOQ = psTipoBloqueo) THEN
                        lbEncontrado := TRUE;
                    END IF;

                    lnOcurrencias := lnOcurrencias + 1;
                END LOOP;

                IF(NOT lbEncontrado) THEN
                    IF(lnOcurrencias = 0) THEN
                        lsResultado := '08';
                    ELSE
                        lsResultado := '07';
                    END IF;
                END IF;

            END IF;

        ELSE
            --Cliente existe, pero está INACTIVO
            lsResultado := '09';
        END IF;

    ELSE
        --Cliente no existe
        lsResultado := '01';

    END IF;

    RETURN lsResultado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VALID_BLOQU_DESBL_CLIEN: ' || ls_sqlerrm);

END MAE_FN_VALID_BLOQU_DESBL_CLIEN;


/**************************************************************************
Descripcion        : Obtiene la unidad Administrativa completa:
                     Region + Zona + Seccion + Territorio
Fecha Creacion     : 06/09/2012
Parametros Entrada:
  psCodigoZona       :     Codigo Zona
  psCodigoTerritorio :     Codigo Territorio

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTEN_CODUA_SOLIC_CREDI
  (psCodigoZona         VARCHAR2,
   psCodigoTerritorio   VARCHAR2)
RETURN VARCHAR2
IS
  lnOidZona          ZON_ZONA.OID_ZONA%TYPE;
  lnOidTerritorio    ZON_TERRI.OID_TERR%TYPE;
  lsCodigoUA         VARCHAR2(20);

BEGIN
  SELECT OID_ZONA
    INTO lnOidZona
	  FROM ZON_ZONA
 	 WHERE COD_ZONA = psCodigoZona
		 AND IND_ACTI = '1';

  SELECT OID_TERR
    INTO lnOidTerritorio
    FROM ZON_TERRI
   WHERE COD_TERR = psCodigoTerritorio
     AND IND_BORR = '0';

  SELECT REG.COD_REGI || ZON.COD_ZONA || SEC.COD_SECC || TER.COD_TERR
    INTO lsCodigoUA
    FROM ZON_TERRI_ADMIN ADM,
         ZON_TERRI TER,
         ZON_SECCI SEC,
         ZON_ZONA ZON,
         ZON_REGIO REG
   WHERE ADM.TERR_OID_TERR = TER.OID_TERR
     AND ADM.PERD_OID_PERI_FINA IS NULL
     AND TER.OID_TERR = lnOidTerritorio
     AND SEC.OID_SECC = ADM.ZSCC_OID_SECC
     AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
     AND ZON.OID_ZONA = lnOidZona
     AND ZON.ZORG_OID_REGI = REG.OID_REGI;

  RETURN lsCodigoUA;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR MAE_FN_OBTEN_CODUA_SOLIC_CREDI: (' ||
                            ln_sqlcode || ')' || ls_sqlerrm);
    RETURN NULL;

END MAE_FN_OBTEN_CODUA_SOLIC_CREDI;


/**************************************************************************
Descripcion       : Generar Solicitud de Credito Rechazada para los Clientes
                    Inactivos que estan queriendo pasarse a Activo
Fecha Creacion    : 06/09/2012
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  psCodigoCliente :  Codigo Cliente
  psCodigoUsuario :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_GENER_SOLIC_CREDI_RECHA
  (psCodigoPais         VARCHAR2,
   psCodigoCliente      VARCHAR2,
   psCodigoUsuario      VARCHAR2)
IS
  lsCodigoPeriodo      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsNumeroLote         INT_SOLIC_CONSO_CREDI.NUM_LOTE%TYPE;
  lnNumeroSecuencia    INT_SOLIC_CONSO_CREDI.SEC_NUME_DOCU%TYPE;
BEGIN
  --Obtenemos el Numero de Lote de STO
  SELECT STO_PKG_GENER.STO_FN_DEVUE_NUME_LOTE(psCodigoPais,'SCC')
    INTO lsNumeroLote
    FROM DUAL;

  STO_PKG_GENER.STO_PR_UPDAT_NUME_LOTE(psCodigoPais,'SCC');

  --Obtiene el Periodo de Proceso
  SELECT COD_PERI
    INTO lsCodigoPeriodo
    FROM BAS_CTRL_FACT
   WHERE COD_PAIS = psCodigoPais
     AND STA_CAMP = 0
     AND IND_CAMP_ACT = 1;

  --Obtenemos el Numero de Secuencia de STO
  SELECT SEQ_DOCU_STO.nextval
    INTO lnNumeroSecuencia
    FROM DUAL;

  INSERT INTO INT_SOLIC_CONSO_CREDI
    (COD_PAIS,
     COD_CLIE,
     NUM_LOTE,
     SEC_NUME_DOCU,
     UNI_ADMI,
     FEC_PROC,
     COD_PERI,
     VAL_APE1,
     VAL_APE2,
     VAL_NOM1,
     VAL_NOM2,
     TIP_DOCU,
     NUM_DOCU_IDEN,
     FEC_HORA_CREA_SOLI
     )
  SELECT
        psCodigoPais,
        psCodigoCliente,
        lsNumeroLote,
        lnNumeroSecuencia,
        (SELECT REG.COD_REGI || ZON.COD_ZONA || SEC.COD_SECC || TER.COD_TERR
          FROM MAE_CLIEN_UNIDA_ADMIN UNI,
               ZON_TERRI_ADMIN ADM,
               ZON_TERRI TER,
               ZON_SECCI SEC,
               ZON_ZONA ZON,
               ZON_REGIO REG
         WHERE UNI.CLIE_OID_CLIE = cli.OID_CLIE
           AND UNI.IND_ACTI = 1
           AND UNI.ZTAD_OID_TERR_ADMI = ADM.OID_TERR_ADMI
           AND ADM.TERR_OID_TERR = TER.OID_TERR
           AND SEC.OID_SECC = ADM.ZSCC_OID_SECC
           AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
           AND ZON.ZORG_OID_REGI = REG.OID_REGI),
    		trunc(SYSDATE),
        lsCodigoPeriodo,
    		cli.VAL_APE1,
        cli.VAL_APE2,
        cli.VAL_NOM1,
        cli.VAL_NOM2,
        tip.cod_tipo_docu,
        ide.num_docu_iden,
        TO_CHAR(SYSDATE, 'dd/mm/yyyy hh24:mi:ss')
  FROM  MAE_CLIEN cli,
        MAE_CLIEN_IDENT ide,
        MAE_TIPO_DOCUM tip
  WHERE cli.COD_CLIE = psCodigoCliente
    AND cli.OID_CLIE = ide.CLIE_OID_CLIE
    AND ide.TDOC_OID_TIPO_DOCU = tip.OID_TIPO_DOCU;

  INSERT INTO STO_DOCUM_DIGIT
	  (COD_PAIS,
	   COD_TIPO_DOCU,
	   NUM_LOTE,
	   SEC_NUME_DOCU,
	   IND_ENVI,
	   IND_RECH,
	   COD_CLIE,
	   USU_DIGI,
	   FEC_DIGI
	   )
	VALUES
	  (psCodigoPais,
	   'SCC',
	   lsNumeroLote,
	   lnNumeroSecuencia,
	   '0',
	   '1',
	   psCodigoCliente,
	   psCodigoUsuario,
	   SYSDATE
	   );

  INSERT INTO STO_DETAL_DOCUM_EXCEP
    (COD_PAIS,
     COD_TIPO_DOCU,
     NUM_LOTE,
     COD_VALI,
     SEC_NUME_DOCU,
     IND_APRO,
     IND_GEST,
     IND_LEVA_ERRO,
     COD_MENS,
     USU_DIGI,
	   FEC_DIGI)
	VALUES
	  (psCodigoPais,
	   'SCC',
	   lsNumeroLote,
     'SCC-44',
	   lnNumeroSecuencia,
	   '0',
	   '0',
	   '0',
     '4400',
     psCodigoUsuario,
	   SYSDATE
	   );

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_SOLIC_CREDI_RECHA: ' || ls_sqlerrm);

END MAE_PR_GENER_SOLIC_CREDI_RECHA;


/****************************************************************************
Descripcion       : Generar Bloqueo por Reingreso No Autorizado
Fecha Creacion    : 11/12/2012
Fecha Modificacion: 11/12/2012
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_GENER_BLOQU_REING_NOAUT(
  psCodigoPais     VARCHAR2,
  psCodigoRegion   VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  psCodigoUsuario  VARCHAR2

  ) IS

  lsindicadorRN bas_param_pais.val_para%TYPE;
  lsmotivoRN bas_param_pais.val_para%TYPE;
  lsReingresoPermitido bas_param_pais.val_para%TYPE;
  lscantidadCampanaRN bas_param_pais.val_para%TYPE;

  lnidpais              NUMBER;
  lnidmarca             NUMBER;
  lnidcanal             NUMBER;
  lnidperiodo           NUMBER;

  lnidTipoBloqBA        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnidTipoBloqRN        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnnivelGravedadBA     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  lnnivelGravedadRN     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  lnidAccion            mae_valor_accio_bloqu.oid_valo_acci_bloq%TYPE;
  lnoidBloqActuClie     mae_clien_bloqu.oid_bloq%TYPE;
  lnoidCampIni          cra_perio.oid_peri%TYPE;
  lnoidCampFin          cra_perio.oid_peri%TYPE;

  --clientes con bloqueo activo
  CURSOR c_clien_bloqueo(pscodRegion VARCHAR2,pnoidCampIni cra_perio.oid_peri%TYPE,pnoidCampFin cra_perio.oid_peri%TYPE) IS
       SELECT  c.oid_clie AS oidCliente
          FROM mae_clien c,
               mae_clien_datos_adici mcda,
               mae_clien_tipo_subti mcts,
               mae_tipo_clien tc,
               mae_clien_unida_admin cuad,
               zon_terri_admin       ztad,
               zon_secci             zscc,
               zon_terri             terr,
               zon_zona              zzon,
               zon_regio             zorg
         WHERE c.oid_clie              = mcts.clie_oid_clie
           AND c.oid_clie              = mcda.clie_oid_clie
           AND mcda.esta_oid_esta_clie = 5
           AND mcts.ticl_oid_tipo_clie = tc.oid_tipo_clie
--           AND tc.cod_tipo_clie        = '02'
           AND mcts.ticl_oid_tipo_clie = 2
           AND mcts.sbti_oid_subt_clie = 1
           AND c.oid_clie              = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc      = zscc.oid_secc
           AND ztad.terr_oid_terr      = terr.oid_terr
           AND zscc.zzon_oid_zona      = zzon.oid_zona
           AND zzon.zorg_oid_regi      = zorg.oid_regi
           AND cuad.ind_acti           = 1
           AND (zorg.cod_regi           = pscodRegion OR  pscodRegion IS NULL)
           AND EXISTS (    SELECT NULL
                             FROM mae_clien_histo_estat clhe
                            WHERE clhe.esta_oid_esta_clie = 6
                              AND clhe.perd_oid_peri BETWEEN pnoidCampIni AND pnoidCampFin
                              AND clhe.clie_oid_clie = c.oid_clie
                         GROUP BY clhe.clie_oid_clie
                           HAVING COUNT(*) > lsReingresoPermitido
                       )
--           AND mcda.num_rein          >= lsReingresoPermitido
           AND (SELECT COUNT(1)
                         FROM mae_clien_bloqu mcb,
                              mae_tipo_bloqu mtb
                        WHERE mcb.clie_oid_clie      =  c.oid_clie
                          AND mcb.fec_desb           IS NULL
                          AND mcb.tibq_oid_tipo_bloq =  mtb.oid_tipo_bloq
                          AND mtb.cod_tipo_bloq      <> 'RN'
                ) > 0
          AND  NOT EXISTS (  SELECT NULL
                               FROM mae_clien_bloqu mcb,
                                    mae_tipo_bloqu mtb
                              WHERE mcb.clie_oid_clie      =  c.oid_clie
                                AND mcb.fec_desb           IS NULL
                                AND mcb.tibq_oid_tipo_bloq =  mtb.oid_tipo_bloq
                                AND mtb.cod_tipo_bloq      = 'RN'
                           );


--clientes sin bloqueo
  CURSOR c_clien_sin_bloqueo(pscodRegion VARCHAR2,pnoidCampIni cra_perio.oid_peri%TYPE,pnoidCampFin cra_perio.oid_peri%TYPE) IS
    SELECT c.oid_clie oidCliente
      FROM mae_clien c,
           mae_clien_datos_adici mcda,
           mae_clien_tipo_subti mcts,
           mae_tipo_clien tc,
           mae_clien_unida_admin cuad,
           zon_terri_admin       ztad,
           zon_secci             zscc,
           zon_terri             terr,
           zon_zona              zzon,
           zon_regio             zorg
     WHERE c.oid_clie              = mcts.clie_oid_clie
       AND c.oid_clie              = mcda.clie_oid_clie
       AND mcda.esta_oid_esta_clie = 5
       AND mcts.ticl_oid_tipo_clie = tc.oid_tipo_clie
--       AND tc.cod_tipo_clie        = '02'
       AND mcts.ticl_oid_tipo_clie = 2
       AND mcts.sbti_oid_subt_clie = 1
       AND c.oid_clie              = cuad.clie_oid_clie
       AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
       AND ztad.zscc_oid_secc      = zscc.oid_secc
       AND ztad.terr_oid_terr      = terr.oid_terr
       AND zscc.zzon_oid_zona      = zzon.oid_zona
       AND zzon.zorg_oid_regi      = zorg.oid_regi
       AND cuad.ind_acti           = 1
       AND (zorg.cod_regi          = pscodRegion OR pscodRegion IS NULL)
       AND EXISTS (    SELECT NULL
                         FROM mae_clien_histo_estat clhe
                        WHERE clhe.esta_oid_esta_clie = 6
                          AND clhe.perd_oid_peri BETWEEN pnoidCampIni AND pnoidCampFin
                          AND clhe.clie_oid_clie = c.oid_clie
                     GROUP BY clhe.clie_oid_clie
                       HAVING COUNT(*) > lsReingresoPermitido
                   )
--       AND mcda.num_rein          >= lsReingresoPermitido
       AND (SELECT COUNT(1)
                     FROM mae_clien_bloqu mcb
                    WHERE mcb.clie_oid_clie = c.oid_clie
                      AND mcb.fec_desb      IS NULL
            ) = 0;

  TYPE t_cliente IS TABLE OF c_clien_bloqueo%ROWTYPE;
  TYPE t_cliente_sb IS TABLE OF c_clien_sin_bloqueo%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada
  registroCliente t_cliente;
  registroClienteSB t_cliente_sb;


BEGIN

--obtenemos el Oid Periodo Actual
  lnidpais              := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,TRUE);
  lnidmarca             := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca,TRUE);
  lnidcanal             := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal,TRUE);
  lnidperiodo           := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo, lnidmarca, lnidcanal, TRUE);

--obtenemos indicador de bloqueo de reingreso por pais
  BEGIN
       SELECT bpp.val_para
         INTO lsindicadorRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'indBloqueoRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsindicadorRN := '0';
  END;

  --obtenemos mensaje de bloqueo
  BEGIN
       SELECT bpp.val_para
         INTO lsmotivoRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'motivoBloqueoRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsmotivoRN := '0';
  END;
--obtenemos cantidad de egresos permitidos
  BEGIN
       SELECT bpp.val_para
         INTO lsReingresoPermitido
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'numReingresoPermitido'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsReingresoPermitido := '0';
  END;

--obtenemos cantidad de campañas permitidas para reingreso
  BEGIN
       SELECT bpp.val_para
         INTO lscantidadCampanaRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'numCampPermitidasRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lscantidadCampanaRN := '0';
  END;

  --obtenemos oid Tipo Bloqueo Reingreso No Autorizado
  BEGIN
       SELECT mtb.oid_tipo_bloq,
              mtb.num_nive_grav_bloq
         INTO lnidTipoBloqRN,
              lnnivelGravedadRN
         FROM mae_tipo_bloqu mtb
        WHERE mtb.cod_tipo_bloq = 'RN';
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lnidTipoBloqRN := '0';
              lnnivelGravedadRN :='999';
  END;

  IF lsindicadorRN = '1' THEN

     --obtenemos el periodo de inicio del rango de evaluación
      BEGIN
           SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                                                               PSCODIGOPERIODO,
                                                                                               -LSCANTIDADCAMPANARN))
             INTO lnoidCampIni
             FROM DUAL;
           EXCEPTION
             WHEN OTHERS THEN
                  lnoidCampIni := 0;

      END;

      --obtenemos el perido fina el rango de evaluación
        lnoidCampFin := lnidperiodo;

    --obtenemos accion de desbloqueo
      SELECT mvab.oid_valo_acci_bloq
        INTO lnidAccion
        FROM MAE_VALOR_ACCIO_BLOQU  mvab
       WHERE mvab.cod_valo_bloq = 'A';
    --clientes con bloqueo Activo
    OPEN c_clien_bloqueo(psCodigoRegion,lnoidCampIni,lnoidCampFin);
      LOOP
      FETCH c_clien_bloqueo BULK COLLECT INTO registroCliente LIMIT W_FILAS;

        IF registroCliente.COUNT > 0 THEN
          FOR x IN registroCliente.FIRST .. registroCliente.LAST LOOP

                --obtenemos el mayor nivel de gravedad del bloqueo activo
                BEGIN
                  SELECT cb.oid_bloq,
                         cb.oid_tipo_bloq,
                         cb.num_nive_grav_bloq
                    INTO lnoidBloqActuClie,
                         lnidTipoBloqBA,
                         lnnivelGravedadBA
                    FROM (
                             SELECT mcb.oid_bloq,
                                    mtb.oid_tipo_bloq,
                                    mtb.num_nive_grav_bloq
                               FROM mae_clien_bloqu mcb,
                                    mae_tipo_bloqu mtb
                              WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                                AND mcb.fec_desb IS NULL
                                AND mcb.clie_oid_clie = registroCliente(x).oidCliente
                           ORDER BY mtb.num_nive_grav_bloq ASC
                          ) cb
                    WHERE rownum=1;
                EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                            lnidTipoBloqBA:='0';
                            lnnivelGravedadBA := '999';
                END;

                IF(lnnivelGravedadRN < lnnivelGravedadBA) THEN
                   --desbloqueamos el bloqueo de mayor gravedad menor al bloqueo por RN
                   UPDATE mae_clien_bloqu mcb
                      SET mcb.fec_desb                = SYSDATE,
                          mcb.val_usua_desb           = psCodigoUsuario,
                          mcb.maab_oid_valo_acci_desb = lnidAccion,
                          mcb.obs_desb                = lsmotivoRN
                    WHERE mcb.oid_bloq                = lnoidBloqActuClie;
                   --insertamos el bloqueo por RN
                    INSERT INTO mae_clien_bloqu
                     (OID_BLOQ,
                      CLIE_OID_CLIE,
                      TIBQ_OID_TIPO_BLOQ,
                      FEC_BLOQ,
                      VAL_MOTI_BLOQ,
                      VAL_USUA_BLOQ,
                      OBS_BLOQ,
                      FEC_DESB,
                      VAL_USUA_DESB,
                      MAAB_OID_VALO_ACCI_BLOQ,
                      MAAB_OID_VALO_ACCI_DESB,
                      OBS_DESB
                      )
                    VALUES
                     (MAE_CLBL_SEQ.NEXTVAL,
                      registroCliente(x).oidCliente,
                      lnidTipoBloqRN,
                      SYSDATE,
                      lsmotivoRN,
                      psCodigoUsuario,
                      lsmotivoRN,
                      NULL,
                      NULL,
                      1000,--automatico
                      NULL,
                      NULL
                     );

            /*  UPDATE mae_clien_datos_adici mcda
                 SET mcda.num_rein = 0,
                     mcda.fec_ulti_actu = SYSDATE,
                     mcda.usu_modi      = psCodigoUsuario
               WHERE mcda.clie_oid_clie = registroCliente(x).oidCliente;*/

                END IF;
          END LOOP;
        END IF;
      EXIT WHEN c_clien_bloqueo%NOTFOUND;
      END LOOP;
    CLOSE c_clien_bloqueo;

         --clientes sin bloqueo Activo
     OPEN c_clien_sin_bloqueo(psCodigoRegion,lnoidCampIni,lnoidCampFin);
      LOOP
        FETCH c_clien_sin_bloqueo BULK COLLECT INTO registroClienteSB LIMIT W_FILAS;
           IF registroClienteSB.COUNT > 0 THEN
             FOR x IN registroClienteSB.FIRST .. registroClienteSB.LAST LOOP
      INSERT INTO mae_clien_bloqu
       (OID_BLOQ,
        CLIE_OID_CLIE,
        TIBQ_OID_TIPO_BLOQ,
        FEC_BLOQ,
        VAL_MOTI_BLOQ,
        VAL_USUA_BLOQ,
        OBS_BLOQ,
        FEC_DESB,
        VAL_USUA_DESB,
        MAAB_OID_VALO_ACCI_BLOQ,
        MAAB_OID_VALO_ACCI_DESB,
        OBS_DESB
        )
             VALUES
              (MAE_CLBL_SEQ.NEXTVAL,
               registroClienteSB(x).oidCliente,
           lnidTipoBloqRN,
           SYSDATE,
           lsmotivoRN,
           psCodigoUsuario,
           lsmotivoRN,
           NULL,
           NULL,
           1000,--automatico
           NULL,
           NULL
              );

          /*  UPDATE mae_clien_datos_adici mcda
               SET mcda.num_rein = 0,
                   mcda.fec_ulti_actu = SYSDATE,
                   mcda.usu_modi      = psCodigoUsuario
             WHERE mcda.clie_oid_clie = registroClienteSB(x).oidCliente;*/
          END LOOP;
        END IF;
      EXIT WHEN c_clien_sin_bloqueo%NOTFOUND;
      END LOOP;
    CLOSE c_clien_sin_bloqueo;

   /* MAE_PR_DESBL_REING_NOAUT(psCodigoPais, psCodigoRegion, pscodigomarca,
                             pscodigocanal, pscodigoperiodo, psCodigoUsuario);*/
  END IF;
END MAE_PR_GENER_BLOQU_REING_NOAUT;

/****************************************************************************
Descripcion       : Desbloquear Reingreso No Autorizado
Fecha Creacion    : 18/12/2012
Fecha Modificacion: 18/12/2012
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_DESBL_REING_NOAUT(
  psCodigoPais     VARCHAR2,
  psCodigoRegion   VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  psCodigoUsuario  VARCHAR2

  ) IS

  lsindicadorRN bas_param_pais.val_para%TYPE;
  lsmotivoRN bas_param_pais.val_para%TYPE;
  lscantidadCampanaRN bas_param_pais.val_para%TYPE;

  lnidpais              NUMBER;
  lnidmarca             NUMBER;
  lnidcanal             NUMBER;
  lnidperiodo           NUMBER;

  lnidTipoBloqBA        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnidTipoBloqRN        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnnivelGravedadBA     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  lnnivelGravedadRN     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  --clientes con bloqueo RN Activo
  CURSOR c_clien_desbloqueo(codigoRegion VARCHAR2) IS
       SELECT  c.oid_clie AS oidCliente
          FROM mae_clien c,
               mae_clien_tipo_subti mcts,
               mae_tipo_clien tc,
               mae_clien_unida_admin cuad,
               zon_terri_admin       ztad,
               zon_secci             zscc,
               zon_terri             terr,
               zon_zona              zzon,
               zon_regio             zorg
         WHERE c.oid_clie = mcts.clie_oid_clie
           AND mcts.ticl_oid_tipo_clie= tc.oid_tipo_clie
           AND tc.cod_tipo_clie= '02'
           AND c.oid_clie = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND ztad.terr_oid_terr = terr.oid_terr
           AND zscc.zzon_oid_zona = zzon.oid_zona
           AND zzon.zorg_oid_regi = zorg.oid_regi
           AND cuad.ind_acti = 1
           AND zorg.cod_regi = codigoRegion
           AND (SELECT COUNT(1)
                         FROM mae_clien_bloqu mcb,
                              mae_tipo_bloqu mtb
                        WHERE mcb.clie_oid_clie      =  c.oid_clie
                          AND mcb.fec_desb           IS NULL
                          AND mcb.tibq_oid_tipo_bloq =  mtb.oid_tipo_bloq
                          AND mtb.cod_tipo_bloq      = 'RN'
                ) > 0;

  TYPE t_cliente IS TABLE OF c_clien_desbloqueo%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada
  registroCliente t_cliente;

BEGIN

  --obtenemos el Oid Periodo Actual
  lnidpais              := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,TRUE);
  lnidmarca             := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca,TRUE);
  lnidcanal             := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal,TRUE);
  lnidperiodo           := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo, lnidmarca, lnidcanal, TRUE);

--obtenemos indicador de bloqueo de reingreso por pais
  BEGIN
       SELECT bpp.val_para
         INTO lsindicadorRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'indBloqueoRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsindicadorRN := '0';
  END;

  --obtenemos cantidad de campañas permitidas para reingreso
  BEGIN
       SELECT bpp.val_para
         INTO lscantidadCampanaRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'numCampPermitidasRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
           lscantidadCampanaRN := '0';
  END;

  --obtenemos mensaje de desbloqueo
  BEGIN
       SELECT bpp.val_para
         INTO lsmotivoRN
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'motivoDesbloqueoRN'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
           lsmotivoRN := '0';
  END;

    --obtenemos oid Tipo Bloqueo Reingreso No Autorizado
  BEGIN
       SELECT mtb.oid_tipo_bloq,
              mtb.num_nive_grav_bloq
         INTO lnidTipoBloqRN,
              lnnivelGravedadRN
         FROM mae_tipo_bloqu mtb
        WHERE mtb.cod_tipo_bloq = 'RN';
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lnidTipoBloqRN := '0';
              lnnivelGravedadRN :='999';
  END;

  --clientes con bloqueo RN vigente a desbloquear
  OPEN c_clien_desbloqueo(psCodigoRegion);
    LOOP
    FETCH c_clien_desbloqueo BULK COLLECT INTO registroCliente LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
      IF registroCliente.COUNT > 0 THEN
        FOR x IN registroCliente.FIRST .. registroCliente.LAST LOOP


         IF ( gen_pkg_gener.gen_fn_devue_difer_perio
              ( gen_pkg_gener.gen_fn_devuelve_des_perio
                ( MAE_FN_OBTIE_PERIO_REING(registroCliente(x).oidCliente) ),
                 pscodigoperiodo
               )
             ) > lscantidadCampanaRN  THEN


           UPDATE mae_clien_bloqu mcb
               SET mcb.fec_desb                = SYSDATE,
                   mcb.val_usua_desb           = psCodigoUsuario,
                   mcb.maab_oid_valo_acci_desb = 1000,--automatico
                   mcb.obs_desb                = lsmotivoRN
             WHERE mcb.clie_oid_clie           = registroCliente(x).oidCliente
               AND mcb.tibq_oid_tipo_bloq      = lnidTipoBloqRN
               AND mcb.fec_desb                IS NULL;
         END IF;

        END LOOP;
      END IF;
    EXIT WHEN c_clien_desbloqueo%NOTFOUND;
    END LOOP;
  CLOSE c_clien_desbloqueo;

END MAE_PR_DESBL_REING_NOAUT;


/****************************************************************************
Descripcion       : Obtiene  Periodo de Último Reingreso
Fecha Creacion    : 17/12/2012
Fecha Modificacion: 17/12/2012
Parametros:
        pnOidCliente: Oid Cliente

Autor: CSVD - FFVV
*****************************************************************************/
FUNCTION MAE_FN_OBTIE_PERIO_REING(pnOidCliente  NUMBER) RETURN VARCHAR2 IS
  lsOidPeriodo    cra_perio.oid_peri%TYPE;

BEGIN

  IF(pnOidCliente IS NULL) THEN
    RETURN NULL;
  END IF;

  BEGIN
      SELECT perd_oid_peri_peri_fin INTO lsOidPeriodo
        FROM (   SELECT mche.perd_oid_peri_peri_fin
                   FROM mae_clien_histo_estat mche
                  WHERE mche.esta_oid_esta_clie = 6
                    AND mche.clie_oid_clie      = pnOidCliente
               ORDER BY mche.perd_oid_peri_peri_fin DESC
              )
       WHERE ROWNUM = 1;

      IF lsOidPeriodo IS NULL THEN
        SELECT p.oid_peri INTO lsOidPeriodo
          FROM cra_perio p,
               seg_perio_corpo spc
         WHERE p.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = ( SELECT bcf.cod_peri
                                  FROM bas_ctrl_fact bcf
                                 WHERE bcf.ind_camp_act = 1
                                   AND bcf.sta_camp = 0
                               );
      END IF;

      EXCEPTION
        WHEN NO_DATA_FOUND
         THEN lsOidPeriodo := NULL;
   END;

  RETURN lsOidPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR MAE_FN_OBTIE_PERIO_REING: (' ||
                            ln_sqlcode || ')' || ls_sqlerrm);
    RETURN NULL;

END MAE_FN_OBTIE_PERIO_REING;

/***************************************************************************
Descripcion : Proceso que desbloquea a los clientes que tiene pedidos
Fecha Creacion : 17/01/2013
Autor : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Campaña
 psUsuario: Usuario de ejecucion
***************************************************************************/
PROCEDURE MAE_PR_GENER_DESBL_CLIEN_PEDID
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo VARCHAR2,
  psUsuario VARCHAR2
 )IS

    lnidperiodo NUMBER;
    lnidAccion  mae_valor_accio_bloqu.oid_valo_acci_bloq%TYPE;
    lsParametro bas_param_pais.val_para%TYPE;

    --clientes con bloqueos activos y que facturaron pedido en la campaña de proceso
    CURSOR c_clien_desbloqueo(oidPeriodo NUMBER) IS
    SELECT mcb.oid_bloq
      FROM mae_clien_bloqu mcb,
           ped_solic_cabec p,
           (SELECT tsp.oid_tipo_soli_pais
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic ts
             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli AND
                   ts.cod_tipo_soli = 'SOC'
            ) pts
    WHERE mcb.fec_desb IS NULL AND
          p.clie_oid_clie = mcb.clie_oid_clie AND
          p.tspa_oid_tipo_soli_pais = pts.oid_tipo_soli_pais AND
          p.fec_fact IS NOT NULL AND
          p.perd_oid_peri = oidPeriodo;


    TYPE t_cliente IS TABLE OF c_clien_desbloqueo%ROWTYPE;

    --se define una variable de tipo de dato de tabla personalizada
    registroCliente t_cliente;

BEGIN

    BEGIN
        SELECT VAL_PARA
        INTO lsParametro
        FROM BAS_PARAM_PAIS
        WHERE COD_PAIS = psCodigoPais
        AND COD_SIST = 'MAE'
        AND NOM_PARA = 'indDesblClienPedid';
    EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsParametro := '0';
    END;

    IF lsParametro = '1' THEN
        lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo, TRUE);

        SELECT mvab.oid_valo_acci_bloq
        INTO lnidAccion
        FROM MAE_VALOR_ACCIO_BLOQU  mvab
        WHERE mvab.cod_valo_bloq = 'A';

        --clientes con bloqueo a desbloquear
        OPEN c_clien_desbloqueo(lnidperiodo);
        LOOP
        FETCH c_clien_desbloqueo BULK COLLECT INTO registroCliente LIMIT W_FILAS;

          IF registroCliente.COUNT > 0 THEN
            FOR x IN registroCliente.FIRST .. registroCliente.LAST LOOP

               UPDATE mae_clien_bloqu mcb
                   SET mcb.fec_desb                = SYSDATE,
                       mcb.val_usua_desb           = psUsuario,
                       mcb.maab_oid_valo_acci_desb = lnidAccion,
                       mcb.obs_desb                = 'Consultora Facturó Pedido'
                 WHERE mcb.oid_bloq                = registroCliente(x).oid_bloq;

            END LOOP;
          END IF;
        EXIT WHEN c_clien_desbloqueo%NOTFOUND;
        END LOOP;
        CLOSE c_clien_desbloqueo;
    END IF;


END MAE_PR_GENER_DESBL_CLIEN_PEDID;

/***************************************************************************
Descripcion : Proceso que actualzia datos de consultora enviado deesde portal
Fecha Creacion : 14/02/2012
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoConsultora : Consultora
 psMensajeError :mensaje de Error
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLIEN_PORTA
 (psCodigoPais       VARCHAR2,
  psCodigoConsultora VARCHAR2,
  psMail VARCHAR2,
  psTelefonoFijo VARCHAR2,
  psTelefonoCelular  VARCHAR2,
  psMensajeError OUT VARCHAR2
 )
IS
  lnOidCliente      NUMBER;

  lnOidComunTF      NUMBER;
  lnOidComunTM      NUMBER;
  lnOidComunML      NUMBER;
  lnOidComunOtro    NUMBER;

  lnOidTipoComuTF   NUMBER;
  lnOidTipoComuTM   NUMBER;
  lnOidTipoComuML   NUMBER;
  lnIndPrincipal    NUMBER;
BEGIN
    psMensajeError:='';

  --Obtenemos el Oid de Telefono Fijo
  SELECT OID_TIPO_COMU INTO lnOidTipoComuTF FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'TF';

  --Obtenemos el Oid de Telefono Celular
  SELECT OID_TIPO_COMU INTO lnOidTipoComuTM FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'TM';

  --Obtenemos el Oid de Mail
  SELECT OID_TIPO_COMU INTO lnOidTipoComuML FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'ML';


  BEGIN
    --Obtenemos el oid de la Consultora
    SELECT OID_CLIE
      INTO lnOidCliente
      FROM MAE_CLIEN
     WHERE COD_CLIE = psCodigoConsultora;

    lnOidComunOtro := NULL;
    FOR x IN (SELECT OID_CLIE_COMU, TICM_OID_TIPO_COMU
                FROM MAE_CLIEN_COMUN
               WHERE CLIE_OID_CLIE = lnOidCliente) LOOP

      IF(x.TICM_OID_TIPO_COMU = lnOidTipoComuTF) THEN
        lnOidComunTF := x.OID_CLIE_COMU;
      ELSIF(x.TICM_OID_TIPO_COMU = lnOidTipoComuTM) THEN
        lnOidComunTM := x.OID_CLIE_COMU;
      ELSIF(x.TICM_OID_TIPO_COMU = lnOidTipoComuML) THEN
        lnOidComunML := x.OID_CLIE_COMU;
      ELSE
        lnOidComunOtro := x.OID_CLIE_COMU;
      END IF;

    END LOOP;

    lnIndPrincipal := 1;

    --ACTUALIZAMOS EL TELEFONO FIJO
    IF((psTelefonoFijo IS NOT NULL) OR (psTelefonoFijo <> '')) THEN
      IF(lnOidComunTF IS NULL) THEN
        INSERT INTO MAE_CLIEN_COMUN
          (OID_CLIE_COMU,
           CLIE_OID_CLIE,
           TICM_OID_TIPO_COMU,
           VAL_DIA_COMU,
           VAL_TEXT_COMU,
           FEC_HORA_DESD,
           FEC_HORA_HAST,
           VAL_INTE_COMU,
           IND_COMU_PPAL
          )
       	VALUES
     	    (MAE_CLCO_SEQ.NEXTVAL,
     	     lnOidCliente,
     	     lnOidTipoComuTF,
     	     NULL,
           psTelefonoFijo,
           NULL,
           NULL,
           NULL,
           lnIndPrincipal
          );
      ELSE
        UPDATE MAE_CLIEN_COMUN
           SET VAL_TEXT_COMU = psTelefonoFijo,
               IND_COMU_PPAL = lnIndPrincipal
         WHERE OID_CLIE_COMU = lnOidComunTF;

      END IF;

      IF(lnIndPrincipal = 1) THEN
        lnIndPrincipal := 0;
      END IF;
    ELSE
      IF(lnOidComunTF IS NOT NULL) THEN
        DELETE FROM MAE_CLIEN_COMUN
         WHERE OID_CLIE_COMU = lnOidComunTF;
      END IF;
    END IF;

    --ACTUALIZAMOS EL TELEFONO MOVIL
    IF((psTelefonoCelular IS NOT NULL) OR (psTelefonoCelular <> '')) THEN
      IF(lnOidComunTM IS NULL) THEN
        INSERT INTO MAE_CLIEN_COMUN
          (OID_CLIE_COMU,
           CLIE_OID_CLIE,
           TICM_OID_TIPO_COMU,
           VAL_DIA_COMU,
           VAL_TEXT_COMU,
           FEC_HORA_DESD,
           FEC_HORA_HAST,
           VAL_INTE_COMU,
           IND_COMU_PPAL
          )
       	VALUES
     	    (MAE_CLCO_SEQ.NEXTVAL,
     	     lnOidCliente,
     	     lnOidTipoComuTM,
     	     NULL,
           psTelefonoCelular,
           NULL,
           NULL,
           NULL,
           lnIndPrincipal
          );
      ELSE
        UPDATE MAE_CLIEN_COMUN
           SET VAL_TEXT_COMU = psTelefonoCelular,
               IND_COMU_PPAL = lnIndPrincipal
         WHERE OID_CLIE_COMU = lnOidComunTM;

      END IF;

      IF(lnIndPrincipal = 1) THEN
        lnIndPrincipal := 0;
      END IF;
    ELSE
      IF(lnOidComunTM IS NOT NULL) THEN
        DELETE FROM MAE_CLIEN_COMUN
         WHERE OID_CLIE_COMU = lnOidComunTM;
      END IF;
    END IF;

    --ACTUALIZAMOS EL MAIL
    IF((psMail IS NOT NULL) OR (psMail <> '')) THEN
      IF(lnOidComunML IS NULL) THEN
        INSERT INTO MAE_CLIEN_COMUN
          (OID_CLIE_COMU,
           CLIE_OID_CLIE,
           TICM_OID_TIPO_COMU,
           VAL_DIA_COMU,
           VAL_TEXT_COMU,
           FEC_HORA_DESD,
           FEC_HORA_HAST,
           VAL_INTE_COMU,
           IND_COMU_PPAL
          )
       	VALUES
     	    (MAE_CLCO_SEQ.NEXTVAL,
     	     lnOidCliente,
     	     lnOidTipoComuML,
     	     NULL,
           psMail,
           NULL,
           NULL,
           NULL,
           lnIndPrincipal
          );
      ELSE
        UPDATE MAE_CLIEN_COMUN
           SET VAL_TEXT_COMU = psMail,
               IND_COMU_PPAL = lnIndPrincipal
         WHERE OID_CLIE_COMU = lnOidComunML;

      END IF;

    ELSE
      IF(lnOidComunML IS NOT NULL) THEN
        DELETE FROM MAE_CLIEN_COMUN
         WHERE OID_CLIE_COMU = lnOidComunML;
      END IF;
    END IF;

    --En caso se pone en blanco uno de los 3 datos y tenga otras comunicaciones, se le actualiza
    --su indicador principal = 1 a uno de ellos, y a los otros lo dejamos en 0
    IF( ((psTelefonoFijo IS NULL) OR (psTelefonoFijo = '')) AND
        ((psTelefonoCelular IS NULL) OR (psTelefonoCelular = '')) AND
        ((psMail IS NULL) OR (psMail = ''))) THEN

      IF(lnOidComunOtro IS NOT NULL) THEN
        UPDATE MAE_CLIEN_COMUN
           SET IND_COMU_PPAL = 1
         WHERE CLIE_OID_CLIE = lnOidCliente
           AND OID_CLIE_COMU = lnOidComunOtro;

        UPDATE MAE_CLIEN_COMUN
           SET IND_COMU_PPAL = 0
         WHERE CLIE_OID_CLIE = lnOidCliente
           AND OID_CLIE_COMU <> lnOidComunOtro;

      END IF;
    ELSE
      --En caso se actualizo uno de los 3 datos y tenga otras comunicaciones, se le actualiza
      --su indicador principal = 0
      IF(lnOidComunOtro IS NOT NULL) THEN
        UPDATE MAE_CLIEN_COMUN
           SET IND_COMU_PPAL = 0
         WHERE CLIE_OID_CLIE = lnOidCliente
           AND TICM_OID_TIPO_COMU NOT IN (lnOidTipoComuTF, lnOidTipoComuTM, lnOidTipoComuML);
      END IF;
    END IF;

  EXCEPTION
     WHEN OTHERS THEN
       psMensajeError:='cliente no existe';
       return;
  END;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 psMensajeError := substr(sqlerrm,1,100);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CLIEN_PORTA: '||ls_sqlerrm);
END MAE_PR_ACTUA_CLIEN_PORTA;


/**************************************************************************
Descripcion       : Ejecuta el Proceso de Asignación de Estatus
Fecha Creacion    : 22/05/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_PROCE_ASIGN_ESTAT(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psCodigoRegion        VARCHAR2,
                                   psCodigoUsuario       VARCHAR2) IS
  lnOidPais          SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca         SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal         SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
  lnOidRegion        ZON_REGIO.OID_REGI%TYPE;

  lsCodPeriodoAnt    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAnt    CRA_PERIO.OID_PERI%TYPE;

  lnIndEjecRein      SEG_PARAM_INTER_PAIS.IND_EJRN%TYPE;
  lnNivelGravBloq    NUMBER;
  lnNumPeriRein      SEG_PAIS.NUM_PERI_REIN%TYPE;
  lsCodPeriodoBlo    SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnNumCampSinPedi   NUMBER;
  lnOidHistoEsta     MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
  lnOidPeriodoEsta   MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;

  lnOidBloqueo       MAE_CLIEN_BLOQU.OID_BLOQ%TYPE;
  lnNivelGravBloq2   NUMBER;
  lbBloqueoHecho     BOOLEAN;
  lnOcurrencias      NUMBER;

  CURSOR c_Consultoras(oidPeriodo NUMBER, oidRegion NUMBER) IS
    SELECT consulta2.*,
          (SELECT mes.OID_ESTA_CLIE
            FROM MAE_ESTAT_CLIEN mes
            WHERE mes.COD_ESTA_CLIE = consulta2.cod_esta_clie_nuevo  ) esta_oid_esta_clie_nuevo
          FROM (
          SELECT consulta.clie_oid_clie,
                 consulta.oid_clie_dato_adic,
                 consulta.num_camp_sin_pedi,
                 consulta.cod_esta_clie,
                 consulta.esta_oid_esta_clie,
                 consulta.cantidadPedidos,
                 CASE WHEN (cantidadPedidos > 0) THEN
                      case when cod_esta_clie = '03' then '03' else
                      case when cod_esta_clie = '01' then '02' else
                      case when cod_esta_clie = '02' then '03' else
                      case when cod_esta_clie = '04' then '03' else
                      case when cod_esta_clie = '05' then '06' else
                      case when cod_esta_clie = '06' then '03' else
                      case when cod_esta_clie = '07' then '08' else
                      case when cod_esta_clie = '08' then '03'
                      else cod_esta_clie     end end end end end end end end
                 ELSE case when cod_esta_clie = '02' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '03' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '04' and ((num_camp_sin_pedi + 1) > num_peri_egre) then '05' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) >= num_peri_reti) then '07' else
                      case when cod_esta_clie = '06' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '08' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) < num_peri_reti)then '05'
                      else cod_esta_clie     end end end end end end end
                 END cod_esta_clie_nuevo
           FROM (SELECT a.clie_oid_clie,
                        a.oid_clie_dato_adic,
                        a.num_camp_sin_pedi,
                        p.num_peri_egre,
                        p.num_peri_reti,
                        e.cod_esta_clie,
                        a.esta_oid_esta_clie,
                        a.ind_acti,
                        (SELECT count(1)
                           FROM PED_SOLIC_CABEC psc,
                                PED_TIPO_SOLIC_PAIS tsp,
                                PED_TIPO_SOLIC ts
                          WHERE psc.ind_oc = 1
                            AND psc.clie_oid_clie = b.oid_clie
                            AND psc.perd_oid_peri = oidPeriodo
                            AND psc.FEC_FACT IS NOT null
                            AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
                            AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                            AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                            AND ts.IND_DEVO  = 0
                            AND psc.MODU_OID_MODU <> 15
                            AND ts.IND_ANUL  = 0
                            AND psc.IND_TS_NO_CONSO = 1) cantidadPedidos,
                        p.oid_pais
                 FROM MAE_CLIEN_DATOS_ADICI a,
                      SEG_PAIS p,
                      MAE_CLIEN b,
                      MAE_CLIEN_TIPO_SUBTI ct,
                      MAE_TIPO_CLIEN t,
                      MAE_ESTAT_CLIEN e
                WHERE a.clie_oid_clie = b.oid_clie
                  AND b.pais_oid_pais = p.oid_pais
                  AND b.oid_clie = ct.clie_oid_clie
                  AND ct.ticl_oid_tipo_clie = t.oid_tipo_clie
                  AND a.esta_oid_esta_clie = e.oid_esta_clie
                  AND a.esta_oid_esta_clie IS NOT NULL
                  AND t.cod_tipo_clie = '02'
                  AND b.oid_clie IN (SELECT c.oid_clie
                                       FROM mae_clien c
                                       JOIN mae_clien_unida_admin u ON (u.clie_oid_clie = c.oid_clie)
                                       JOIN zon_terri_admin t ON (u.ztad_oid_terr_admi = t.oid_terr_admi)
                                       JOIN zon_secci s ON (t.zscc_oid_secc = s.oid_secc)
                                       JOIN zon_zona z ON (s.zzon_oid_zona = z.oid_zona),
                                            cra_perio periodoinicio,
                                            cra_perio periodoactual
                                      WHERE u.perd_oid_peri_ini = periodoinicio.oid_peri
                                        AND periodoactual.oid_peri = oidPeriodo
                                        AND periodoinicio.marc_oid_marc = periodoactual.marc_oid_marc
                                        AND periodoinicio.cana_oid_cana = periodoactual.cana_oid_cana
                                        AND periodoinicio.fec_inic <= periodoactual.fec_inic
                                        AND u.ind_acti = 1
                                        AND zorg_oid_regi = oidRegion )
                ) consulta
          ) consulta2;

  TYPE interfazConsultoras IS RECORD(
    oidCliente        MAE_CLIEN.OID_CLIE%TYPE,
    oidDatosAdici     MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE,
    numCampSinPedi    MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE,
    codEstatus        MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatus        MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE,
    cantPedidos       NUMBER,
    codEstatusNuevo   MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatusNuevo   MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE
  );

  TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
  interfazRecordN interfazConsultorasTab;

  TYPE t_oidDatosAdi         IS TABLE OF MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE;
  TYPE t_numCampSinPedi      IS TABLE OF MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE;
  TYPE t_oidEstatus          IS TABLE OF MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidCliente          IS TABLE OF MAE_CLIEN.OID_CLIE%TYPE;

  TYPE t_oidPeriodoIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
  TYPE t_oidEstatusIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidClienteIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;

  TYPE t_oidEstatusUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidHistoEsta       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  TYPE t_oidPeriodoUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI_PERI_FIN%TYPE;
  TYPE t_oidHistoEsta2        IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  v_oidDatosAdi              t_oidDatosAdi  := t_oidDatosAdi();
  v_numCampSinPedi           t_numCampSinPedi  := t_numCampSinPedi();
  v_oidEstatus               t_oidEstatus  := t_oidEstatus();
  v_oidCliente               t_oidCliente  := t_oidCliente();

  v_oidPeriodoIns            t_oidPeriodoIns  := t_oidPeriodoIns();
  v_oidEstatusIns            t_oidEstatusIns  := t_oidEstatusIns();
  v_oidClienteIns            t_oidClienteIns  := t_oidClienteIns();

  v_oidEstatusUpd            t_oidEstatusUpd  := t_oidEstatusUpd();
  v_oidHistoEsta             t_oidHistoEsta  := t_oidHistoEsta();

  v_oidPeriodoUpd            t_oidPeriodoUpd  := t_oidPeriodoUpd();
  v_oidHistoEsta2            t_oidHistoEsta2  := t_oidHistoEsta2();

  W_FILAS_PROCE      NUMBER:=10000;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Obtenemos el periodo Anterior
  lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                             lnOidPais, lnOidMarca, lnOidCanal, -1);
  lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt, lnOidMarca, lnOidCanal);

  --Recuperamos el Oid Region
  SELECT OID_REGI
    INTO lnOidRegion
    FROM ZON_REGIO
   WHERE COD_REGI = psCodigoRegion
     AND IND_ACTI = 1;

  --Recuperamos el indicador de Ejecucion de Reingreso y el Nivel de Gravedad de Bloqueo del Pais
  SELECT (SELECT SPIP.IND_EJRN
            FROM SEG_PARAM_INTER_PAIS SPIP
           WHERE SPIP.PAIS_OID_PAIS = lnOidPais) IND_EJRN,
         NVL((SELECT NVL(MTB.NUM_NIVE_GRAV_BLOQ, 999)
               FROM MAE_TIPO_BLOQU MTB
              WHERE MTB.COD_TIPO_BLOQ = 'RN'),
             0) NUM_NIVE_GRAV_BLOQ
    INTO lnIndEjecRein,
         lnNivelGravBloq
    FROM DUAL;

  IF(lnIndEjecRein IS NULL) THEN
    lnNivelGravBloq := 0;
  ELSE
    --Recuperamos el Numero de Periodo de ReIngreso
    SELECT NUM_PERI_REIN
      INTO lnNumPeriRein
      FROM SEG_PAIS
     WHERE OID_PAIS = lnOidPais;

    --Obtenemos el periodo de Inicio para el Reingreso
    lsCodPeriodoBlo := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                               lnOidPais, lnOidMarca, lnOidCanal, (-1)*(lnNumPeriRein-1));
  END IF;

  --REALIZAMOS EL PROCESO DE LA EVALUACION DE ESTATUS DE LAS CONSULTORA
  OPEN c_Consultoras(lnOidPeriodo, lnOidRegion);
  LOOP
    FETCH c_Consultoras BULK COLLECT INTO interfazRecordN LIMIT W_FILAS_PROCE;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        --Evaluamos Numero de Campaña sin Pedidos
        IF(interfazRecordN(x).cantPedidos > 0) THEN
          lnNumCampSinPedi := 0;
        ELSE
          IF(interfazRecordN(x).numCampSinPedi IS NULL) THEN
            lnNumCampSinPedi := 1;
          ELSE
            lnNumCampSinPedi := interfazRecordN(x).numCampSinPedi + 1;
          END IF;
        END IF;

        --Se actualiza en la tabla MAE_CLIEN_DATOS_ADICI, los campos
        --Numero de Campaña sin Pedido y oid Estatus
        v_oidDatosAdi.EXTEND(1);
        v_numCampSinPedi.EXTEND(1);
        v_oidEstatus.EXTEND(1);

        v_oidDatosAdi(v_oidDatosAdi.COUNT) := interfazRecordN(x).oidDatosAdici;
        v_numCampSinPedi(v_numCampSinPedi.COUNT) := lnNumCampSinPedi;
        v_oidEstatus(v_oidEstatus.COUNT) := interfazRecordN(x).oidEstatusNuevo;

        --Si el Nuevo Estatus es REINGRESO, se actualiza el campo fecha Reingreso en MAE_CLIEN
        IF(interfazRecordN(x).codEstatusNuevo = '06') THEN
          v_oidCliente.EXTEND(1);
          v_oidCliente(v_oidCliente.COUNT) := interfazRecordN(x).oidCliente;
        END IF;

        /*IF(lnNivelGravBloq > 0) THEN
          lbBloqueoHecho := FALSE;

          --BLOQUEO Automatico por Reingreso no Autorizado
          --Verificamos si estatus Actual <> 'Reingreso' y estatus Nuevo = 'Reingreso', verificamos
          IF((interfazRecordN(x).codEstatus <> '06') AND (interfazRecordN(x).codEstatusNuevo = '06')) THEN
            --Validamos en un rango de periodos si anteriormente ha tenido el estatus 'Reingreso'
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM MAE_CLIEN_HISTO_ESTAT CHE,
                   MAE_ESTAT_CLIEN EST,
                   CRA_PERIO CRA,
                   SEG_PERIO_CORPO COR
             WHERE CHE.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND CHE.ESTA_OID_ESTA_CLIE = EST.OID_ESTA_CLIE
               AND CHE.PERD_OID_PERI_PERI_FIN = CRA.OID_PERI
               AND CRA.PERI_OID_PERI = COR.OID_PERI
               AND COR.COD_PERI < psCodigoPeriodo
               AND COR.COD_PERI >= lsCodPeriodoBlo
               AND EST.COD_ESTA_CLIE = '06';

            IF(lnOcurrencias > 0) THEN
              BEGIN
                SELECT MCB.OID_BLOQ,
                       NVL(MTB.NUM_NIVE_GRAV_BLOQ, 999) - lnNivelGravBloq DIF_NIVEL
                  INTO lnOidBloqueo,
                       lnNivelGravBloq2
                  FROM MAE_CLIEN_BLOQU MCB,
                       MAE_TIPO_BLOQU MTB
                 WHERE MCB.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND MCB.FEC_DESB IS NULL
                   AND MTB.OID_TIPO_BLOQ = MCB.TIBQ_OID_TIPO_BLOQ;
              EXCEPTION
                WHEN OTHERS THEN
                  lnNivelGravBloq2 := NULL;
              END;

              IF(lnNivelGravBloq2 IS NOT NULL) THEN
                --Si el Nivel de Gravedad Hallado es > Nivel Gravedad 'Reingreso', se actualiza dicho bloqueo
                --y si crea un nuevo registro de bloqueo
                IF(lnNivelGravBloq2 > 0) THEN
                  UPDATE MAE_CLIEN_BLOQU
                     SET FEC_DESB = SYSDATE,
                         VAL_USUA_DESB = psCodigoUsuario,
                         MAAB_OID_VALO_ACCI_DESB = (SELECT OID_VALO_ACCI_BLOQ FROM MAE_VALOR_ACCIO_BLOQU  WHERE COD_VALO_BLOQ = 'A'),
                         OBS_DESB = 'Bloqueo por Reingreso no Autorizado - Automático'
                   WHERE OID_BLOQ = lnOidBloqueo;

                  INSERT INTO MAE_CLIEN_BLOQU
                    (OID_BLOQ, CLIE_OID_CLIE,
                     TIBQ_OID_TIPO_BLOQ, FEC_BLOQ,
                     VAL_USUA_BLOQ, VAL_MOTI_BLOQ,
                     MAAB_OID_VALO_ACCI_BLOQ,
                     OBS_BLOQ)
                  VALUES
                    (MAE_CLBL_SEQ.NEXTVAL, interfazRecordN(x).oidCliente,
                    (SELECT MTB.OID_TIPO_BLOQ FROM MAE_TIPO_BLOQU MTB WHERE MTB.COD_TIPO_BLOQ = 'RN'), SYSDATE,
                    psCodigoUsuario, 'Bloqueo por Reingreso no Autorizado - Automático',
                     (SELECT OID_VALO_ACCI_BLOQ FROM MAE_VALOR_ACCIO_BLOQU  WHERE COD_VALO_BLOQ = 'A'),
                     'Bloqueo por Reingreso no Autorizado - Automático');


                  lbBloqueoHecho := TRUE;
                END IF;

              ELSE
                --Si no tiene ningun registro bloqueado, se crea un nuevo registro de bloqueo
                INSERT INTO MAE_CLIEN_BLOQU
                  (OID_BLOQ, CLIE_OID_CLIE,
                   TIBQ_OID_TIPO_BLOQ, FEC_BLOQ,
                   VAL_USUA_BLOQ, VAL_MOTI_BLOQ,
                   MAAB_OID_VALO_ACCI_BLOQ,
                   OBS_BLOQ)
                VALUES
                  (MAE_CLBL_SEQ.NEXTVAL, interfazRecordN(x).oidCliente,
                   (SELECT MTB.OID_TIPO_BLOQ FROM MAE_TIPO_BLOQU MTB WHERE MTB.COD_TIPO_BLOQ = 'RN'), SYSDATE,
                   psCodigoUsuario, 'Bloqueo por Reingreso no Autorizado - Automático',
                   (SELECT OID_VALO_ACCI_BLOQ FROM MAE_VALOR_ACCIO_BLOQU  WHERE COD_VALO_BLOQ = 'A'),
                   'Bloqueo por Reingreso no Autorizado - Automático');

                lbBloqueoHecho := TRUE;
              END IF;

            END IF;

          END IF;

          --DESBLOQUEO Automatico por Reingreso no Autorizado
          IF(NOT lbBloqueoHecho) THEN
            SELECT COUNT(1)
                INTO lnOcurrencias
                FROM MAE_CLIEN_HISTO_ESTAT CHE,
                     MAE_ESTAT_CLIEN EST,
                     CRA_PERIO CRA,
                     SEG_PERIO_CORPO COR
               WHERE CHE.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND CHE.ESTA_OID_ESTA_CLIE = EST.OID_ESTA_CLIE
                 AND CHE.PERD_OID_PERI_PERI_FIN = CRA.OID_PERI
                 AND CRA.PERI_OID_PERI = COR.OID_PERI
                 AND COR.COD_PERI < psCodigoPeriodo
                 AND COR.COD_PERI >= lsCodPeriodoBlo
                 AND EST.COD_ESTA_CLIE = '06';

              --Si no encontramos registro de estatus 'Reingreso' en un rango de periodos
              --Se verifica si tiene un bloqueo por 'Reingreso No Autorizado', si es asi
              --se desbloquea dicho registro y se actualiza el campo fechaReingreso = null en la tabla de Cliente
              IF(lnOcurrencias = 0) THEN
                BEGIN
                  SELECT CB.OID_BLOQ
                    INTO lnOidBloqueo
                    FROM MAE_CLIEN_BLOQU CB, MAE_TIPO_BLOQU TB
                   WHERE CB.FEC_DESB IS NULL
                     AND CB.TIBQ_OID_TIPO_BLOQ = TB.OID_TIPO_BLOQ
                     AND TB.COD_TIPO_BLOQ = 'RN'
                     AND CB.CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
                EXCEPTION
                  WHEN OTHERS THEN
                    lnOidBloqueo := NULL;
                END;

                IF(lnOidBloqueo IS NOT NULL) THEN
                  UPDATE MAE_CLIEN_BLOQU
                     SET FEC_DESB = SYSDATE,
                         VAL_USUA_DESB = psCodigoUsuario,
                         MAAB_OID_VALO_ACCI_DESB = (SELECT OID_VALO_ACCI_BLOQ FROM MAE_VALOR_ACCIO_BLOQU  WHERE COD_VALO_BLOQ = 'A'),
                         OBS_DESB = 'DesBloqueo por Reingreso no Autorizado - Automático'
                   WHERE OID_BLOQ = lnOidBloqueo;

                   UPDATE MAE_CLIEN
                      SET FEC_REIN = NULL
                    WHERE OID_CLIE = interfazRecordN(x).oidCliente;
                END IF;
              END IF;
          END IF;

        END IF;*/

        --Si se ha cambiado el Estatus del Cliente, Se realiza una evaluacion del Historico de Estatus
        IF(interfazRecordN(x).codEstatus <> interfazRecordN(x).codEstatusNuevo) THEN
          BEGIN
            SELECT OID_HIST_ESTA,
                   PERD_OID_PERI
              INTO lnOidHistoEsta,
                   lnOidPeriodoEsta
              FROM (
                    SELECT h.oid_hist_esta,
                           h.PERD_OID_PERI,
                           i.FEC_INIC
                      FROM mae_clien_histo_estat h, cra_perio i, cra_perio a
                     WHERE h.perd_oid_peri_peri_fin IS NULL
                       AND h.perd_oid_peri = i.oid_peri
                       AND i.pais_oid_pais = a.pais_oid_pais
                       AND i.marc_oid_marc = a.marc_oid_marc
                       AND i.cana_oid_cana = a.cana_oid_cana
                       AND i.fec_inic <= a.fec_inic
                       AND a.oid_peri = lnOidPeriodo
                       AND h.esta_oid_esta_clie <> interfazRecordN(x).oidEstatusNuevo
                       AND h.clie_oid_clie = interfazRecordN(x).oidCliente
                     ORDER BY CLIE_OID_CLIE, FEC_INIC DESC)
             WHERE ROWNUM = 1;
          EXCEPTION
            WHEN OTHERS THEN
              lnOidHistoEsta := NULL;
          END;

          --Si no se encuentra registro en Historico se crea uno nuevo
          IF(lnOidHistoEsta IS NULL) THEN
            v_oidPeriodoIns.EXTEND(1);
            v_oidEstatusIns.EXTEND(1);
            v_oidClienteIns.EXTEND(1);

            v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
            v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
            v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;
          ELSE

            --En caso de que el periodo del registro en Historico sea igual a Periodo Actual
            --Solo se actualizara el campo Estatus
            IF(lnOidPeriodoEsta = lnOidPeriodo) THEN
              v_oidEstatusUpd.EXTEND(1);
              v_oidHistoEsta.EXTEND(1);

              v_oidEstatusUpd(v_oidEstatusUpd.COUNT) := interfazRecordN(x).oidEstatusNuevo;
              v_oidHistoEsta(v_oidHistoEsta.COUNT) := lnOidHistoEsta;
            ELSE
              --Se actualiza el campo Fecha Fin para el registro del Historico
              v_oidPeriodoUpd.EXTEND(1);
              v_oidHistoEsta2.EXTEND(1);

              v_oidPeriodoUpd(v_oidPeriodoUpd.COUNT) := lnOidPeriodoAnt;
              v_oidHistoEsta2(v_oidHistoEsta2.COUNT) := lnOidHistoEsta;

              --Y se crea un nuevo registro para el estatus nuevo
              v_oidPeriodoIns.EXTEND(1);
              v_oidEstatusIns.EXTEND(1);
              v_oidClienteIns.EXTEND(1);

              v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
              v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
              v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;

            END IF;

          END IF;

        END IF;

      END LOOP;

    END IF;
    EXIT WHEN c_Consultoras%NOTFOUND;
  END LOOP;
  CLOSE c_Consultoras;

  --Actualizamos en la tabla MAE_CLIEN_DATOS_ADICI
  FORALL i IN 1..v_oidDatosAdi.COUNT
    UPDATE MAE_CLIEN_DATOS_ADICI
       SET NUM_CAMP_SIN_PEDI = v_numCampSinPedi(i),
           ESTA_OID_ESTA_CLIE = v_oidEstatus(i)
     WHERE OID_CLIE_DATO_ADIC = v_oidDatosAdi(i);

  --Actualizamos en la tabla MAE_CLIEN
  FORALL i IN 1..v_oidCliente.COUNT
    UPDATE MAE_CLIEN
       SET FEC_REIN = SYSDATE
     WHERE OID_CLIE = v_oidCliente(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET ESTA_OID_ESTA_CLIE = v_oidEstatusUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta2.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET PERD_OID_PERI_PERI_FIN = v_oidPeriodoUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta2(i);

  --Insertamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidPeriodoIns.COUNT
    INSERT INTO MAE_CLIEN_HISTO_ESTAT
      (OID_HIST_ESTA, PERD_OID_PERI,
       CLIE_OID_CLIE, ESTA_OID_ESTA_CLIE)
    VALUES
      (MAE_CLHE_SEQ.NEXTVAL, v_oidPeriodoIns(i),
       v_oidClienteIns(i), v_oidEstatusIns(i));

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR MAE_PR_PROCE_ASIGN_ESTAT: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);

END MAE_PR_PROCE_ASIGN_ESTAT;


/**************************************************************************
Descripcion       : Ejecuta el Proceso de Asignación de Estatus
Fecha Creacion    : 22/05/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_PROCE_REEVA_ESTAT_CONSU(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psCodigoUsuario       VARCHAR2) IS
  lnOidPais          SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca         SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal         SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;

  lsCodPeriodoAnt    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAnt    CRA_PERIO.OID_PERI%TYPE;

  lnNumCampSinPedi   NUMBER;
  lnOidHistoEsta     MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
  lnOidPeriodoEsta   MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;

  CURSOR c_Consultoras(oidPeriodo NUMBER, oidRegion NUMBER) IS
    SELECT consulta2.*,
          (SELECT mes.OID_ESTA_CLIE
            FROM MAE_ESTAT_CLIEN mes
            WHERE mes.COD_ESTA_CLIE = consulta2.cod_esta_clie_nuevo  ) esta_oid_esta_clie_nuevo
          FROM (
          SELECT consulta.clie_oid_clie,
                 consulta.oid_clie_dato_adic,
                 NVL(consulta.num_camp_sin_pedi,0) num_camp_sin_pedi,
                 consulta.cod_esta_clie,
                 consulta.esta_oid_esta_clie,
                 consulta.cantidadPedidos,
                 CASE WHEN (cantidadPedidos > 0) THEN
                      case when cod_esta_clie = '03' then '03' else
                      case when cod_esta_clie = '01' then '02' else
                      case when cod_esta_clie = '02' then '03' else
                      case when cod_esta_clie = '04' then '03' else
                      case when cod_esta_clie = '05' then '06' else
                      case when cod_esta_clie = '06' then '03' else
                      case when cod_esta_clie = '07' then '08' else
                      case when cod_esta_clie = '08' then '03'
                      else cod_esta_clie     end end end end end end end end
                 ELSE case when cod_esta_clie = '02' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '03' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '04' and ((num_camp_sin_pedi + 1) > num_peri_egre) then '05' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) >= num_peri_reti) then '07' else
                      case when cod_esta_clie = '06' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '08' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) < num_peri_reti)then '05'
                      else cod_esta_clie     end end end end end end end
                 END cod_esta_clie_nuevo
           FROM (SELECT a.clie_oid_clie,
                        a.oid_clie_dato_adic,
                        a.num_camp_sin_pedi,
                        p.num_peri_egre,
                        p.num_peri_reti,
                        e.cod_esta_clie,
                        a.esta_oid_esta_clie,
                        a.ind_acti,
                        (SELECT count(1)
                           FROM PED_SOLIC_CABEC psc,
                                PED_TIPO_SOLIC_PAIS tsp,
                                PED_TIPO_SOLIC ts
                          WHERE psc.ind_oc = 1
                            AND psc.clie_oid_clie = b.oid_clie
                            AND psc.perd_oid_peri = oidPeriodo
                            AND psc.FEC_FACT IS NOT null
                            AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
                            AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                            AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                            AND ts.IND_DEVO  = 0
                            AND psc.MODU_OID_MODU <> 15
                            AND ts.IND_ANUL  = 0
                            AND psc.IND_TS_NO_CONSO = 1) cantidadPedidos,
                        p.oid_pais
                 FROM MAE_CLIEN_DATOS_ADICI a,
                      SEG_PAIS p,
                      MAE_CLIEN b,
                      MAE_CLIEN_TIPO_SUBTI ct,
                      MAE_TIPO_CLIEN t,
                      MAE_ESTAT_CLIEN e
                WHERE a.clie_oid_clie = b.oid_clie
                  AND b.pais_oid_pais = p.oid_pais
                  AND b.oid_clie = ct.clie_oid_clie
                  AND ct.ticl_oid_tipo_clie = t.oid_tipo_clie
                  AND a.esta_oid_esta_clie = e.oid_esta_clie
                  AND a.esta_oid_esta_clie IS NOT NULL
                  AND t.cod_tipo_clie = '02'
                  AND b.oid_clie IN (SELECT c.oid_clie
                                       FROM mae_clien c
                                       JOIN mae_clien_unida_admin u ON (u.clie_oid_clie = c.oid_clie)
                                       JOIN zon_terri_admin t ON (u.ztad_oid_terr_admi = t.oid_terr_admi)
                                       JOIN zon_secci s ON (t.zscc_oid_secc = s.oid_secc)
                                       JOIN zon_zona z ON (s.zzon_oid_zona = z.oid_zona),
                                            cra_perio periodoinicio,
                                            cra_perio periodoactual
                                      WHERE u.perd_oid_peri_ini = periodoinicio.oid_peri
                                        AND periodoactual.oid_peri = oidPeriodo
                                        AND periodoinicio.marc_oid_marc = periodoactual.marc_oid_marc
                                        AND periodoinicio.cana_oid_cana = periodoactual.cana_oid_cana
                                        AND periodoinicio.fec_inic <= periodoactual.fec_inic
                                        AND u.ind_acti = 1
                                        AND zorg_oid_regi = oidRegion )
                ) consulta
                WHERE consulta.cantidadPedidos > 0
          ) consulta2
         WHERE consulta2.num_camp_sin_pedi > 0
             OR (consulta2.num_camp_sin_pedi = 0 AND consulta2.cod_esta_clie='01') ;

  TYPE interfazConsultoras IS RECORD(
    oidCliente        MAE_CLIEN.OID_CLIE%TYPE,
    oidDatosAdici     MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE,
    numCampSinPedi    MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE,
    codEstatus        MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatus        MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE,
    cantPedidos       NUMBER,
    codEstatusNuevo   MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatusNuevo   MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE
  );

  TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
  interfazRecordN interfazConsultorasTab;

  TYPE t_oidDatosAdi         IS TABLE OF MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE;
  TYPE t_numCampSinPedi      IS TABLE OF MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE;
  TYPE t_oidEstatus          IS TABLE OF MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidCliente          IS TABLE OF MAE_CLIEN.OID_CLIE%TYPE;

  TYPE t_oidPeriodoIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
  TYPE t_oidEstatusIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidClienteIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;

  TYPE t_oidEstatusUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidHistoEsta       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  TYPE t_oidPeriodoUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI_PERI_FIN%TYPE;
  TYPE t_oidHistoEsta2        IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  v_oidDatosAdi              t_oidDatosAdi  := t_oidDatosAdi();
  v_numCampSinPedi           t_numCampSinPedi  := t_numCampSinPedi();
  v_oidEstatus               t_oidEstatus  := t_oidEstatus();
  v_oidCliente               t_oidCliente  := t_oidCliente();

  v_oidPeriodoIns            t_oidPeriodoIns  := t_oidPeriodoIns();
  v_oidEstatusIns            t_oidEstatusIns  := t_oidEstatusIns();
  v_oidClienteIns            t_oidClienteIns  := t_oidClienteIns();

  v_oidEstatusUpd            t_oidEstatusUpd  := t_oidEstatusUpd();
  v_oidHistoEsta             t_oidHistoEsta  := t_oidHistoEsta();

  v_oidPeriodoUpd            t_oidPeriodoUpd  := t_oidPeriodoUpd();
  v_oidHistoEsta2            t_oidHistoEsta2  := t_oidHistoEsta2();

  lnOidEstatusActualReev     NUMBER;
  lnOidEstatusNuevoReev      NUMBER;
  lsCodEstatusActualReev     MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;
  lsCodEstatusNuevoReev      MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Obtenemos el periodo Anterior
  lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                             lnOidPais, lnOidMarca, lnOidCanal, -1);
  lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt, lnOidMarca, lnOidCanal);

  --Activa Consultoras
   UPDATE MAE_CLIEN_DATOS_ADICI datosAdic
      SET datosAdic.IND_ACTI = 1
    WHERE datosAdic.IND_ACTI = 0
      AND EXISTS(SELECT 1
                   FROM PED_SOLIC_CABEC ped
                  WHERE ped.CLIE_OID_CLIE = datosAdic.CLIE_OID_CLIE
                    AND ped.PAIS_OID_PAIS = lnOidPais
                    AND ped.PERD_OID_PERI = lnOidPeriodo
                    AND ped.FEC_FACT IS NOT NULL
                    AND ped.IND_OC = 1
                    AND ped.IND_TS_NO_CONSO = 1
                    AND ped.MODU_OID_MODU != 15
                    AND ped.IND_PEDI_PRUE != 1);


  --REEVALUAMOS SOBRE LAS REGIONES CERRADAS
  FOR z IN (SELECT cierre.ZORG_OID_REGI
              FROM FAC_CONTR_CIERR cierre, FAC_TIPOS_CIERR tipoCierre
             WHERE cierre.TCIE_OID_TIPO_CIER = tipoCierre.OID_TIPO_CIER
               and tipoCierre.COD_TIPO_CIER = 'R'
               and cierre.PAIS_OID_PAIS = lnOidPais
               and cierre.PERD_OID_PERI = lnOidPeriodo
               and cierre.VAL_PROC_EJEC = 'Asignar_estatus_en_lotes'
               and cierre.VAL_RESU_PROC = 'OK') LOOP

    --REALIZAMOS EL PROCESO DE LA EVALUACION DE ESTATUS DE LAS CONSULTORA
    OPEN c_Consultoras(lnOidPeriodo, z.ZORG_OID_REGI);
    LOOP
      FETCH c_Consultoras BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          --Si en el cierre de region se produjo un cambio de estatus
          --lo detectamos y 'deshacemos' a traves del Historico de Estatus
          IF(interfazRecordN(x).codEstatus <> ESTADO_REGISTRADA) THEN
            MAE_PR_ACTUA_ESTAT_REEVA(psCodigoPais,
                                     interfazRecordN(x).oidCliente,
                                     interfazRecordN(x).oidEstatus,
                                     lnOidPeriodo,
                                     lnOidPeriodoAnt,
                                     lnOidEstatusActualReev);
            IF(lnOidEstatusActualReev <> interfazRecordN(x).oidEstatus) THEN
              lsCodEstatusNuevoReev := MAE_FN_OBTIE_ESTAD_SIGUI(psCodigoPais,
                                                             lnOidEstatusActualReev,
                                                             interfazRecordN(x).numCampSinPedi);

              SELECT COD_ESTA_CLIE
                INTO lsCodEstatusActualReev
                FROM MAE_ESTAT_CLIEN
               WHERE OID_ESTA_CLIE = lnOidEstatusActualReev;

              SELECT OID_ESTA_CLIE
                INTO lnOidEstatusNuevoReev
                FROM MAE_ESTAT_CLIEN
               WHERE COD_ESTA_CLIE = lsCodEstatusNuevoReev;

              interfazRecordN(x).oidEstatus := lnOidEstatusActualReev;
              interfazRecordN(x).codEstatus := lsCodEstatusActualReev;
              interfazRecordN(x).oidEstatusNuevo := lnOidEstatusNuevoReev;
              interfazRecordN(x).codEstatusNuevo := lsCodEstatusNuevoReev;
            END IF;

            IF(interfazRecordN(x).numCampSinPedi > 0) THEN
              interfazRecordN(x).numCampSinPedi := interfazRecordN(x).numCampSinPedi - 1;
            END IF;

          END IF;

          --Evaluamos Numero de Campaña sin Pedidos
          IF(interfazRecordN(x).cantPedidos > 0) THEN
            lnNumCampSinPedi := 0;
          ELSE
            IF(interfazRecordN(x).numCampSinPedi IS NULL) THEN
              lnNumCampSinPedi := 1;
            ELSE
              lnNumCampSinPedi := interfazRecordN(x).numCampSinPedi + 1;
            END IF;
          END IF;

          --Se actualiza en la tabla MAE_CLIEN_DATOS_ADICI, los campos
          --Numero de Campaña sin Pedido y oid Estatus
          v_oidDatosAdi.EXTEND(1);
          v_numCampSinPedi.EXTEND(1);
          v_oidEstatus.EXTEND(1);

          v_oidDatosAdi(v_oidDatosAdi.COUNT) := interfazRecordN(x).oidDatosAdici;
          v_numCampSinPedi(v_numCampSinPedi.COUNT) := lnNumCampSinPedi;
          v_oidEstatus(v_oidEstatus.COUNT) := interfazRecordN(x).oidEstatusNuevo;

          --Si el Nuevo Estatus es REINGRESO, se actualiza el campo fecha Reingreso en MAE_CLIEN
          IF(interfazRecordN(x).codEstatusNuevo = '06') THEN
            v_oidCliente.EXTEND(1);
            v_oidCliente(v_oidCliente.COUNT) := interfazRecordN(x).oidCliente;
          END IF;

          --Si se ha cambiado el Estatus del Cliente, Se realiza una evaluacion del Historico de Estatus
          IF(interfazRecordN(x).codEstatus <> interfazRecordN(x).codEstatusNuevo) THEN
            BEGIN
              SELECT OID_HIST_ESTA,
                     PERD_OID_PERI
                INTO lnOidHistoEsta,
                     lnOidPeriodoEsta
                FROM (
                      SELECT h.oid_hist_esta,
                             h.PERD_OID_PERI,
                             i.FEC_INIC
                        FROM mae_clien_histo_estat h, cra_perio i, cra_perio a
                       WHERE h.perd_oid_peri_peri_fin IS NULL
                         AND h.perd_oid_peri = i.oid_peri
                         AND i.pais_oid_pais = a.pais_oid_pais
                         AND i.marc_oid_marc = a.marc_oid_marc
                         AND i.cana_oid_cana = a.cana_oid_cana
                         AND i.fec_inic <= a.fec_inic
                         AND a.oid_peri = lnOidPeriodo
                         AND h.esta_oid_esta_clie <> interfazRecordN(x).oidEstatusNuevo
                         AND h.clie_oid_clie = interfazRecordN(x).oidCliente
                       ORDER BY CLIE_OID_CLIE, FEC_INIC DESC)
               WHERE ROWNUM = 1;
            EXCEPTION
              WHEN OTHERS THEN
                lnOidHistoEsta := NULL;
            END;

            --Si no se encuentra registro en Historico se crea uno nuevo
            IF(lnOidHistoEsta IS NULL) THEN
              v_oidPeriodoIns.EXTEND(1);
              v_oidEstatusIns.EXTEND(1);
              v_oidClienteIns.EXTEND(1);

              v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
              v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
              v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;
            ELSE

              --En caso de que el periodo del registro en Historico sea igual a Periodo Actual
              --Solo se actualizara el campo Estatus
              IF(lnOidPeriodoEsta = lnOidPeriodo) THEN
                v_oidEstatusUpd.EXTEND(1);
                v_oidHistoEsta.EXTEND(1);

                v_oidEstatusUpd(v_oidEstatusUpd.COUNT) := interfazRecordN(x).oidEstatusNuevo;
                v_oidHistoEsta(v_oidHistoEsta.COUNT) := lnOidHistoEsta;
              ELSE
                --Se actualiza el campo Fecha Fin para el registro del Historico
                v_oidPeriodoUpd.EXTEND(1);
                v_oidHistoEsta2.EXTEND(1);

                v_oidPeriodoUpd(v_oidPeriodoUpd.COUNT) := lnOidPeriodoAnt;
                v_oidHistoEsta2(v_oidHistoEsta2.COUNT) := lnOidHistoEsta;

                --Y se crea un nuevo registro para el estatus nuevo
                v_oidPeriodoIns.EXTEND(1);
                v_oidEstatusIns.EXTEND(1);
                v_oidClienteIns.EXTEND(1);

                v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
                v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
                v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;

              END IF;

            END IF;

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_Consultoras%NOTFOUND;
    END LOOP;
    CLOSE c_Consultoras;

  END LOOP;

  --Actualizamos en la tabla MAE_CLIEN_DATOS_ADICI
  FORALL i IN 1..v_oidDatosAdi.COUNT
    UPDATE MAE_CLIEN_DATOS_ADICI
       SET NUM_CAMP_SIN_PEDI = v_numCampSinPedi(i),
           ESTA_OID_ESTA_CLIE = v_oidEstatus(i)
     WHERE OID_CLIE_DATO_ADIC = v_oidDatosAdi(i);

  --Actualizamos en la tabla MAE_CLIEN
  FORALL i IN 1..v_oidCliente.COUNT
    UPDATE MAE_CLIEN
       SET FEC_REIN = SYSDATE
     WHERE OID_CLIE = v_oidCliente(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET ESTA_OID_ESTA_CLIE = v_oidEstatusUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta2.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET PERD_OID_PERI_PERI_FIN = v_oidPeriodoUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta2(i);

  --Insertamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidPeriodoIns.COUNT
    INSERT INTO MAE_CLIEN_HISTO_ESTAT
      (OID_HIST_ESTA, PERD_OID_PERI,
       CLIE_OID_CLIE, ESTA_OID_ESTA_CLIE)
    VALUES
      (MAE_CLHE_SEQ.NEXTVAL, v_oidPeriodoIns(i),
       v_oidClienteIns(i), v_oidEstatusIns(i));

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR MAE_PR_PROCE_REEVA_ESTAT_CONSU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);

END MAE_PR_PROCE_REEVA_ESTAT_CONSU;

/**************************************************************************
Descripcion       : Actualizamos al Estatus Anterior de la Consultora
Fecha Creacion    : 16/07/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente
  pnOidEstatus :  oid Estatus
  pnOidPeriodo :  oid Periodo Actual
  pnOidPeriodoAnt :  oid Periodo Anterior
  pnOidEstatusReev :  oid Estatus Reevaluado

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ACTUA_ESTAT_REEVA(psCodigoPais         VARCHAR2,
                                   pnOidCliente         NUMBER,
                                   pnOidEstatus         NUMBER,
                                   pnOidPeriodo         NUMBER,
                                   pnOidPeriodoAnt      NUMBER,
                                   pnOidEstatusReev OUT NUMBER)
IS
  lnOidHistorico   MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
BEGIN
  BEGIN
    SELECT OID_HIST_ESTA
      INTO lnOidHistorico
      FROM MAE_CLIEN_HISTO_ESTAT
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND PERD_OID_PERI = pnOidPeriodo;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      pnOidEstatusReev := pnOidEstatus;
      lnOidHistorico := NULL;
  END;

  IF(lnOidHistorico IS NOT NULL) THEN

    --Eliminamos el registro del Historico de Estatus de Clientes
    DELETE FROM MAE_CLIEN_HISTO_ESTAT
     WHERE OID_HIST_ESTA = lnOidHistorico;

    --Actualizamos el anterior estatus del cliente
    BEGIN
      SELECT OID_HIST_ESTA,
             ESTA_OID_ESTA_CLIE
        INTO lnOidHistorico,
             pnOidEstatusReev
        FROM MAE_CLIEN_HISTO_ESTAT
       WHERE CLIE_OID_CLIE = pnOidCliente
         AND PERD_OID_PERI_PERI_FIN = pnOidPeriodoAnt;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        pnOidEstatusReev := pnOidEstatus;
        lnOidHistorico := NULL;
    END;

    IF(lnOidHistorico IS NOT NULL) THEN
      UPDATE mae_clien_histo_estat
         SET PERD_OID_PERI_PERI_FIN = null
       WHERE OID_HIST_ESTA = lnOidHistorico;
    END IF;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_ESTAT_REEVA: ' || ls_sqlerrm);

END MAE_PR_ACTUA_ESTAT_REEVA;

/**************************************************************************
Descripcion        : Obtiene Siguiente Estado
Fecha Creacion     : 16/07/2013
Parametros Entrada:
  psCodigoZona       :     Codigo Zona
  psCodigoTerritorio :     Codigo Territorio

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_OBTIE_ESTAD_SIGUI(psCodigoPais           VARCHAR2,
                                  pnOidEstatus           NUMBER,
                                  pnNumPeriodoSinPedido  NUMBER) RETURN VARCHAR2 IS
  lsCodEstatus       MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;
  lsCodEstatusNuevo  MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;
  lnNumPeriReti      SEG_PAIS.NUM_PERI_RETI%TYPE;
BEGIN

  SELECT NUM_PERI_RETI
    INTO lnNumPeriReti
    FROM SEG_PAIS
   WHERE COD_PAIS = psCodigoPais;

  SELECT COD_ESTA_CLIE
    INTO lsCodEstatus
    FROM MAE_ESTAT_CLIEN
   WHERE OID_ESTA_CLIE = pnOidEstatus;

  IF(lsCodEstatus = ESTADO_REGISTRADA) THEN
    lsCodEstatusNuevo := ESTADO_NUEVA;

  ELSIF(lsCodEstatus = ESTADO_NUEVA) THEN
    lsCodEstatusNuevo := ESTADO_NORMAL;

  ELSIF(lsCodEstatus = ESTADO_NORMAL) THEN
    lsCodEstatusNuevo := ESTADO_NORMAL;

  ELSIF(lsCodEstatus = ESTADO_EGRESANTE) THEN
    lsCodEstatusNuevo := ESTADO_NORMAL;

  ELSIF((lsCodEstatus = ESTADO_EGRESADA) AND (pnNumPeriodoSinPedido <= lnNumPeriReti)) THEN
    lsCodEstatusNuevo := ESTADO_REINGRESO;

  ELSIF(lsCodEstatus = ESTADO_REINGRESO) THEN
    lsCodEstatusNuevo := ESTADO_NORMAL;

  ELSIF(lsCodEstatus = ESTADO_RETIRADA) THEN
    lsCodEstatusNuevo := ESTADO_REACTIVADA;

  ELSIF(lsCodEstatus = ESTADO_REACTIVADA) THEN
    lsCodEstatusNuevo := ESTADO_NORMAL;

  ELSE
    lsCodEstatusNuevo := lsCodEstatus;
  END IF;


  RETURN lsCodEstatusNuevo;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR MAE_FN_OBTIE_ESTAD_SIGUI: (' ||
                            ln_sqlcode || ')' || ls_sqlerrm);
    RETURN NULL;

END MAE_FN_OBTIE_ESTAD_SIGUI;


/**************************************************************************
Descripcion       : Permite generar el histórico de cambios de datos
Fecha Creacion    : 13/08/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  psCodigoCliente    :  Codigo Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_INSER_HISTO_DATOS(psCodigoPais           VARCHAR2,
                                   psCodigoCliente        VARCHAR2,
                                   psPrimerApellidoAnt    VARCHAR2,
                                   psSegundoApellidoAnt   VARCHAR2,
                                   psPrimerNombreAnt      VARCHAR2,
                                   psSegundoNombreAnt     VARCHAR2,
                                   psFechaNacimientoAnt   VARCHAR2,
                                   psNumeroDocumentoAnt   VARCHAR2,
                                   psTelefonoCelularAnt   VARCHAR2,
                                   psTelefonoFijoAnt      VARCHAR2,
                                   psEmailAnt             VARCHAR2,
                                   psUbigeoAnt            VARCHAR2,
                                   psTipoViaAnt           VARCHAR2,
                                   psNumeroPrincipalAnt   VARCHAR2,
                                   psBarrioAnt            VARCHAR2,
                                   psDireccionAnt         VARCHAR2,
                                   psReferenciaAnt        VARCHAR2,
                                   psRegionAnt            VARCHAR2,
                                   psZonaAnt              VARCHAR2,
                                   psSeccionAnt           VARCHAR2,
                                   psTerritorioAnt        VARCHAR2,
                                   psPrimerApellido       VARCHAR2,
                                   psSegundoApellido      VARCHAR2,
                                   psPrimerNombre         VARCHAR2,
                                   psSegundoNombre        VARCHAR2,
                                   psFechaNacimiento      VARCHAR2,
                                   psNumeroDocumento      VARCHAR2,
                                   psTelefonoCelular      VARCHAR2,
                                   psTelefonoFijo         VARCHAR2,
                                   psEmail                VARCHAR2,
                                   psUbigeo               VARCHAR2,
                                   psTipoVia              VARCHAR2,
                                   psNumeroPrincipal      VARCHAR2,
                                   psBarrio               VARCHAR2,
                                   psDireccion            VARCHAR2,
                                   psReferencia           VARCHAR2,
                                   psRegion               VARCHAR2,
                                   psZona                 VARCHAR2,
                                   psSeccion              VARCHAR2,
                                   psTerritorio           VARCHAR2,
                                   psIndicadorOrigen      VARCHAR2,
                                   psCodigoUsuario        VARCHAR2)
IS
  lnOidHistorico   MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
  lbCambio         BOOLEAN;
  lsComodin        VARCHAR2(10):='___*||*___';

  lsCambioZona     VARCHAR2(1);
BEGIN
  lbCambio := FALSE;

  IF((NVL(psPrimerApellido,lsComodin) <> NVL(psPrimerApellidoAnt,lsComodin)) OR
     (NVL(psSegundoApellido,lsComodin) <> NVL(psSegundoApellidoAnt,lsComodin)) OR
     (NVL(psPrimerNombre,lsComodin) <> NVL(psPrimerNombreAnt,lsComodin)) OR
     (NVL(psSegundoNombre,lsComodin) <> NVL(psSegundoNombreAnt,lsComodin)) OR
     (NVL(psFechaNacimiento,lsComodin) <> NVL(psFechaNacimientoAnt,lsComodin)) OR
     (NVL(psNumeroDocumento,lsComodin) <> NVL(psNumeroDocumentoAnt,lsComodin)) OR
     (NVL(psTelefonoCelular,lsComodin) <> NVL(psTelefonoCelularAnt,lsComodin)) OR
     (NVL(psTelefonoFijo,lsComodin) <> NVL(psTelefonoFijoAnt,lsComodin)) OR
     (NVL(psEmail,lsComodin) <> NVL(psEmailAnt,lsComodin)) OR
     (NVL(psUbigeo,lsComodin) <> NVL(psUbigeoAnt,lsComodin)) OR
     (NVL(psTipoVia,lsComodin) <> NVL(psTipoViaAnt,lsComodin)) OR
     (NVL(psNumeroPrincipal,lsComodin) <> NVL(psNumeroPrincipalAnt,lsComodin)) OR
     (NVL(psBarrio,lsComodin) <> NVL(psBarrioAnt,lsComodin)) OR
     (NVL(psDireccion,lsComodin) <> NVL(psDireccionAnt,lsComodin)) OR
     (NVL(psReferencia,lsComodin) <> NVL(psReferenciaAnt,lsComodin)) OR
     (NVL(psRegion,lsComodin) <> NVL(psRegionAnt,lsComodin)) OR
     (NVL(psZona,lsComodin) <> NVL(psZonaAnt,lsComodin)) OR
     (NVL(psSeccion,lsComodin) <> NVL(psSeccionAnt,lsComodin)) OR
     (NVL(psTerritorio,lsComodin) <> NVL(psTerritorioAnt,lsComodin))
     ) THEN
    lbCambio := TRUE;
  END IF;

  IF(lbCambio) THEN
    IF(NVL(psTerritorio,lsComodin) <> NVL(psTerritorioAnt,lsComodin)) THEN
      lsCambioZona := 'T';
    END IF;
    IF(NVL(psZona,lsComodin) <> NVL(psZonaAnt,lsComodin)) THEN
      lsCambioZona := 'Z';
    END IF;

    INSERT INTO MAE_DATOS_CLIEN_HISTO
 	     (COD_DATO_CLIE_HIST,
  			FEC_REGI,
  			COD_CLIE,
  			NUM_DECU_IDEN_ANTE,
  			NUM_DECU_IDEN_NUEV,
  			VAL_APE1_ANTE,
  			VAL_APE1_NUEV,
  			VAL_APE2_ANTE,
  			VAL_APE2_NUEV,
  			VAL_NOM1_ANTE,
  			VAL_NOM1_NUEV,
  			VAL_NOM2_ANTE,
  			VAL_NOM2_NUEV,
  			VAL_TELE_CELU_ANTE,
  			VAL_TELE_CELU_NUEV,
  			VAL_TELE_FIJO_NUEV,
  			VAL_TELE_FIJO_ANTE,
  			VAL_EMAI_ANTE,
  			VAL_EMAI_NUEV,
  			FEC_NACI_ANTE,
  			FEC_NACI_NUEV,
  			COD_UNID_GEOG_ANTE,
  			COD_UNID_GEOG_NUEV,
  			TIPO_VIA_ANTE,
  			TIP_VIA_NUEV,
  			NUM_PPAL_ANTE,
  			NUM_PPAL_NUEV,
  			VAL_BARR_ANTE,
  			VAL_BARR_NUEV,
  			VAL_NOMB_VIA_ANTE,
  			VAL_NOMB_VIA_NUEV,
  			VAL_OBSE_ANTE,
  			VAL_OBSE_NUEV,
  			COD_REGI_ANTE,
  			COD_REGI_NUEV,
  			COD_ZONA_ANTE,
  			COD_ZONA_NUEV,
  			COD_SECC_ANTE,
  			COD_SECC_NUEV,
  			COD_TERR_ANTE,
  			COD_TERR_NUEV,
  			IND_PROC,
  			FEC_PROC,
  			IND_CAMB_ZONA,
  			USU_CREA,
  			IND_CANA_ORIG,
  			FEC_CREA,
  			IND_ACTI)
  		VALUES
  			(MAE_DCLH_SEQ.NEXTVAL,
  			 TRUNC(SYSDATE),
  			 psCodigoCliente,
  			 psNumeroDocumentoAnt,
  			 psNumeroDocumento,
  			 psPrimerApellidoAnt,
  			 psPrimerApellido,
  			 psSegundoApellidoAnt,
  			 psSegundoApellido,
  			 psPrimerNombreAnt,
  			 psPrimerNombre,
  			 psSegundoNombreAnt,
  			 psSegundoNombre,
  			 psTelefonoCelularAnt,
  			 psTelefonoCelular,
  			 psTelefonoFijoAnt,
  			 psTelefonoFijo,
  			 psEmailAnt,
  			 psEmail,
  			 TO_DATE(psFechaNacimientoAnt,'dd/MM/yyyy'),
  			 TO_DATE(psFechaNacimiento,'dd/MM/yyyy'),
  			 psUbigeoAnt,
  			 psUbigeo,
  			 psTipoViaAnt,
  			 psTipoVia,
  			 psNumeroPrincipalAnt,
  			 psNumeroPrincipal,
  			 psBarrioAnt,
  			 psBarrio,
  			 psDireccionAnt,
  			 psDireccion,
  			 psReferenciaAnt,
  			 psReferencia,
  			 psRegionAnt,
  			 psRegion,
  			 psZonaAnt,
  			 psZona,
  			 psSeccionAnt,
  			 psSeccion,
  			 psTerritorioAnt,
  			 psTerritorio,
  			 'P',
  			 NULL,
  			 lsCambioZona,
  			 psCodigoUsuario,
  			 psIndicadorOrigen,
  			 SYSDATE,
  			 1);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_INSER_HISTO_DATOS: ' || ls_sqlerrm);

END MAE_PR_INSER_HISTO_DATOS;

/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo MOD11V, aplicado al tipo de Documento RUC
Fecha Creacion    : 14/08/2013
Parametros Entrada:
  psNumeroDocumento        :     Numero Documento (RUC)

Autor             : Gonzalo Javier Huertas Agurto

***************************************************************************/
FUNCTION MAE_FN_VALID_NUMER_RUC_MOD11
  (psNumeroDocumento        VARCHAR2)
RETURN NUMBER
IS
  --digitos
  dig01 number;
  dig02 number;
  dig03 number;
  dig04 number;
  dig05 number;
  dig06 number;
  dig07 number;
  dig08 number;
  dig09 number;
  dig10 number;
  dig11 number;
  suma number;
  resta number;
  residuo number;
  digChk number;
  resultado number;

BEGIN

 if(LENGTH(TRIM(TRANSLATE(psNumeroDocumento, '0123456789',' ')))<>'' or length(trim(psNumeroDocumento))<>11) then
   resultado := 0;
 else

   dig01 := to_number(SUBSTR(psNumeroDocumento,1,1))*5;
   dig02 := to_number(SUBSTR(psNumeroDocumento,2,1))*4;
   dig03 := to_number(SUBSTR(psNumeroDocumento,3,1))*3;
   dig04 := to_number(SUBSTR(psNumeroDocumento,4,1))*2;
   dig05 := to_number(SUBSTR(psNumeroDocumento,5,1))*7;
   dig06 := to_number(SUBSTR(psNumeroDocumento,6,1))*6;
   dig07 := to_number(SUBSTR(psNumeroDocumento,7,1))*5;
   dig08 := to_number(SUBSTR(psNumeroDocumento,8,1))*4;
   dig09 := to_number(SUBSTR(psNumeroDocumento,9,1))*3;
   dig10 := to_number(SUBSTR(psNumeroDocumento,10,1))*2;
   dig11 := to_number(SUBSTR(psNumeroDocumento,11,1));

   suma := dig01 + dig02 + dig03 + dig04 + dig05 + dig06 + dig07 + dig08 + dig09 + dig10;

   residuo := mod(suma,11);

   resta := 11 - residuo;

   If (resta = 10) Then
      digChk := 0;
   Elsif (resta = 11) Then
       digChk := 1;
     Else
       digChk := resta;
   End If;

   If (dig11 = digChk) Then
      resultado := 1;
   Else
      resultado := 0;
   End If;

end if;

return resultado;

EXCEPTION
  WHEN OTHERS THEN
    RETURN 0;

END MAE_FN_VALID_NUMER_RUC_MOD11;


/**************************************************************************
Descripcion       : Valida Canert de Identidad
Fecha Creacion    : 20/05/2015
Parametros Entrada:
           psNumeroDocumento:  Numero Documento

Autor             : Diego Torres Loyola

***************************************************************************/
FUNCTION MAE_FN_VALID_CARNE_IDENT
  (psNumeroDocumento        VARCHAR2)
RETURN VARCHAR2
IS
  caracteres varchar2(3);
  numeros varchar2(10);
  resultado varchar2(130);
  validaNumero number;
  validaCaracter number;
BEGIN
  resultado := ' ';
  validaNumero:=null;
  validaCaracter:=null;

  caracteres := SUBSTR(psNumeroDocumento, LENGTH(psNumeroDocumento)-2, 3);
  numeros := SUBSTR(psNumeroDocumento, 1, LENGTH(psNumeroDocumento)-3);



  --Validamos si es numero
  select length(translate(numeros, 'T_0123456789 +-.,;:*!¡=/\()%^[]', 'T'))
  into validaNumero
  from dual;

  --validamos si es letra
  select length(translate(caracteres, 'T_0123456789 +-.,;:*!¡=/\()%^[]', 'T'))
  into validaCaracter
  from dual;

  -- validamos que sean 13 digitos
  if(LENGTH(psNumeroDocumento) <= 13) then
    if(caracteres is not null and numeros is not null) then
     if (validaNumero is null and validaCaracter = 3) then
       if (caracteres <> 'SCZ'
            and caracteres <> 'LPZ'
            and caracteres <> 'CBB'
            and caracteres <> 'CHU'
            and caracteres <> 'PAN'
            and caracteres <> 'BEN'
            and caracteres <> 'ORU'
            and caracteres <> 'POT'
            and caracteres <> 'TJA'
            and caracteres <> 'EXT' ) then
           resultado := 'Los caracteres alfabéticos no hacen referencia a alguna provincia del país';
         end if;
      else
         resultado := 'Formato Incorrecto: El Carnet deberá estar formado por números y sus 3 últimos caracteres deben ser alfabéticos y en mayúscula';
      end if;
    else
       resultado := 'Formato Incorrecto: El Carnet deberá estar formado por números y sus 3 últimos caracteres deben ser alfabéticos y en mayúscula';
    end if;
  else
    resultado := 'Longitud no permitida (MAX. 13 caracteres)';
  end if;


return resultado;

EXCEPTION
  WHEN OTHERS THEN
    RETURN 'Longitud no permitida (MAX. 13 caracteres)';

END MAE_FN_VALID_CARNE_IDENT;


/**************************************************************************
Descripcion       : Actualizamos Datos de la Ejecutiva
Fecha Creacion    : 05/09/2013
Parametros Entrada:

  psCodigoPais    :  Codigo Pais
  pnOidCliente    :  Oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_ELIMI_DATOS_EJECU(psCodigoPais         VARCHAR2,
                                   pnOidCliente         NUMBER)
IS
  lsClasificaciones    BAS_PARAM_PAIS.VAL_PARA%TYPE;
  lnOidClasificacion   NUMBER;
  lnPosicion           NUMBER;
BEGIN
  --Eliminar el estatus de la Ejecutiva
  UPDATE ZON_SECCI
     SET CLIE_OID_CLIE = NULL
   WHERE CLIE_OID_CLIE = pnOidCliente;

  --obtenemos indicador de bloqueo de reingreso por pais
  BEGIN
    SELECT bpp.val_para
      INTO lsClasificaciones
      FROM bas_param_pais bpp
     WHERE bpp.cod_pais = psCodigoPais
       AND bpp.cod_sist = 'HIP'
       AND bpp.nom_para = 'clasiEjecutivas'
       AND bpp.ind_acti = '1';
   EXCEPTION
     WHEN NO_DATA_FOUND
       THEN lsClasificaciones := NULL;
  END;

  --Eliminar la Clasificacion de la Ejecutiva
  IF(lsClasificaciones IS NOT NULL) THEN
    lnPosicion := INSTR(lsClasificaciones, ',', 1);

    WHILE(lnPosicion >= 0) LOOP
      IF(lnPosicion > 0) THEN
        lnOidClasificacion := TO_NUMBER(SUBSTR(lsClasificaciones,
                                        1,
                                        lnPosicion - 1));

        lsClasificaciones := SUBSTR(lsClasificaciones,
                                  lnPosicion + 1,
                                  length(lsClasificaciones));
      ELSE
        lnOidClasificacion := TO_NUMBER(lsClasificaciones);
      END IF;

      DELETE MAE_CLIEN_CLASI
  	   WHERE CTSU_OID_CLIE_TIPO_SUBT =
            (SELECT OID_CLIE_TIPO_SUBT FROM MAE_CLIEN_TIPO_SUBTI WHERE CLIE_OID_CLIE = pnOidCliente)
         AND CLAS_OID_CLAS IN (lnOidClasificacion);

      IF(lnPosicion = 0) THEN
        lnPosicion := -1;
      ELSE
        lnPosicion := INSTR(lsClasificaciones, ',', 1);
      END IF;

    END LOOP;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ELIMI_DATOS_EJECU: ' || ls_sqlerrm);

END MAE_PR_ELIMI_DATOS_EJECU;


/****************************************************************************
Descripcion       : Revierte el estatus de la consultora por anulacion de pedido
Fecha Creacion    : 22/11/2013
Fecha Modificacion: 22/11/2013
Parametros:

Autor: CSVD - FFVV
*****************************************************************************/
PROCEDURE MAE_PR_GENER_REVER_ESTAT(
  psCodigoPais     VARCHAR2,
  psCodigoMarca    VARCHAR2,
  psCodigoCanal    VARCHAR2,
  psCodigoCampana  VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psCodigoRegion   VARCHAR2,
  psCodigoUsuario  VARCHAR2
  ) IS

  lsindicadorRE         bas_param_pais.val_para%TYPE; --indicador reversion estatus
  lsmotivoRN bas_param_pais.val_para%TYPE;
  lsReingresoPermitido bas_param_pais.val_para%TYPE;
  lscantidadCampanaRN bas_param_pais.val_para%TYPE;

  lnidpais              NUMBER;
  lnidmarca             NUMBER;
  lnidcanal             NUMBER;
  lnidperiodo           NUMBER;

  lnidTipoBloqBA        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnidTipoBloqRN        mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lnnivelGravedadBA     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  lnnivelGravedadRN     mae_tipo_bloqu.num_nive_grav_bloq%TYPE;
  lnidAccion            mae_valor_accio_bloqu.oid_valo_acci_bloq%TYPE;
  lnoidBloqActuClie     mae_clien_bloqu.oid_bloq%TYPE;
  lnoidCamp             cra_perio.oid_peri%TYPE;
  lsCodPeriodoAnt       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAnt       CRA_PERIO.OID_PERI%TYPE;

  --clientes con anulación de pedidos procesado y deben reversar su estatus
  CURSOR c_clien_estatu(pscodRegion VARCHAR2 , pnoidCamp cra_perio.oid_peri%TYPE, pdfecFact VARCHAR2) IS
    SELECT DISTINCT clie.oid_clie oidCliente,
           clie.cod_clie CodCliente
      FROM ped_solic_cabec x,
           ped_solic_cabec soca,
           ped_tipo_solic_pais tsp,
           ped_tipo_solic ts,
           mae_clien clie,
               mae_clien_unida_admin cuad,
               zon_terri_admin       ztad,
               zon_secci             zscc,
               zon_zona              zzon,
           zon_regio zorg,
           (
               SELECT zorg.cod_regi,
                      TRUNC( coci.fec_cier ) fec_cier
                 FROM fac_contr_cierr coci,
                      own_comun.fac_tipos_cierr tcie,
               zon_regio             zorg
                WHERE coci.tcie_oid_tipo_cier = tcie.oid_tipo_cier
                  AND coci.zorg_oid_regi      = zorg.oid_regi
                  AND coci.perd_oid_peri      = pnoidCamp
                  AND tcie.oid_tipo_cier      = 1
             GROUP BY zorg.cod_regi,
                      TRUNC( coci.fec_cier )
           ) cier,
           cra_perio p,
           mae_clien_datos_adici mcda
    WHERE x.soca_oid_docu_refe      = soca.oid_soli_cabe
      AND soca.clie_oid_clie        = clie.oid_clie
      AND clie.oid_clie             = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc      = zscc.oid_secc
           AND zscc.zzon_oid_zona      = zzon.oid_zona
           AND zzon.zorg_oid_regi      = zorg.oid_regi
      AND zorg.cod_regi             = cier.cod_regi
           AND cuad.ind_acti           = 1
      AND x.perd_oid_peri           = pnoidCamp
      AND soca.perd_oid_peri        = pnoidCamp
      AND x.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
      AND tsp.tsol_oid_tipo_soli    = ts.oid_tipo_soli
      AND ts.cod_tipo_soli          IN ('SDAA','SDAN')
--      AND x.fec_fact                = to_date(pdfecFact,'dd/mm/yyyy')
      AND x.fec_fact                BETWEEN p.fec_inic AND p.fec_fina
      AND mcda.clie_oid_clie = clie.oid_clie
      AND 0 = (
         SELECT COUNT(*)
          FROM ped_solic_cabec pdx,
               ped_solic_cabec sc2
         WHERE pdx.soca_oid_soli_cabe = sc2.oid_soli_cabe
           AND pdx.tspa_oid_tipo_soli_pais = 2075
           AND sc2.tspa_oid_tipo_soli_pais = 2043
           AND sc2.esso_oid_esta_soli  <> 4
           AND sc2.clie_oid_clie = clie.oid_clie
           AND sc2.perd_oid_peri = pnoidCamp
           AND sc2.fec_fact IS NOT NULL
         );
  TYPE t_cliente IS TABLE OF c_clien_estatu%ROWTYPE;

  --se define una variable de tipo de dato de tabla personalizada
  registroCliente t_cliente;

BEGIN

--obtenemos el Oid Periodo Actual
  lnidpais              := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,TRUE);
  lnidmarca             := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca,TRUE);
  lnidcanal             := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal,TRUE);
  lnidperiodo           := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigocampana, lnidmarca, lnidcanal, TRUE);

--obtenemos indicador de reversion estatus
  BEGIN
       SELECT bpp.val_para
         INTO lsindicadorRE
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'indReversionEstatus'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsindicadorRE := '0';
  END;


  --obtenemos el periodo de inicio del rango de evaluación
  BEGIN
           lnoidCamp:=GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(pscodigocampana);
           --Obtenemos el periodo Anterior
           lsCodPeriodoAnt := GEN_PKG_GENER.gen_fn_perio_nsigu(pscodigopais,pscodigocampana,-1);
           lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt, lnidMarca, lnidCanal);
       EXCEPTION
            WHEN OTHERS THEN
                  lnoidCamp := 0;
  END;


    --clientes con pedidos anulados facturados
    OPEN c_clien_estatu(psCodigoRegion,lnoidCamp,psFechaFacturacion);
      LOOP
      FETCH c_clien_estatu BULK COLLECT INTO registroCliente LIMIT W_FILAS;
        IF registroCliente.COUNT > 0 THEN
          FOR x IN registroCliente.FIRST .. registroCliente.LAST LOOP

                --proceso para revertir estatus
                --oidCliente
                --oidPeriodo
                --oidPeriodoAnt

                MAE_PR_REVER_ESTAT_CLIEN(psCodigoPais,registroCliente(x).oidCliente,lnidperiodo,pscodigocampana,lnOidPeriodoAnt,psCodigoUsuario);

          END LOOP;
        END IF;
      EXIT WHEN c_clien_estatu%NOTFOUND;
      END LOOP;
    CLOSE c_clien_estatu;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_REVER_ESTAT: ' || ls_sqlerrm);
END MAE_PR_GENER_REVER_ESTAT;

/**************************************************************************
Descripcion       : Se retorna al Estatus Anterior de la Consultora
Fecha Creacion    : 27/11/2013
Parametros Entrada:
    psCodigoPais    :
    pnOidCliente    :
    pnOidPeriodo    :
    psCodigoCampana :
    pnOidPeriodoAnt :
    psCodigoUsuario :
Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_REVER_ESTAT_CLIEN (
    psCodigoPais         VARCHAR2,
    pnOidCliente         NUMBER,
    pnOidPeriodo         NUMBER,
    psCodigoCampana      VARCHAR2,
    pnOidPeriodoAnt      NUMBER,
    psCodigoUsuario      VARCHAR2
)
IS
  lnOidHistorico   MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
  lnoidEstatus     mae_clien_histo_estat.esta_oid_esta_clie%TYPE;
  lnoidEstatusAnt  mae_clien_histo_estat.esta_oid_esta_clie%TYPE;
  lvCodUltiPedi    seg_perio_corpo.cod_peri%TYPE;
  lnCamSinPedi     mae_clien_datos_adici.num_camp_sin_pedi%TYPE;
  lnOidEstatusReev mae_clien_histo_estat.esta_oid_esta_clie%TYPE;
  lnindEstaActu    NUMBER:=0;
  indNoCrear       NUMBER:=1;
  lnAux            NUMBER;
BEGIN
  --se obtiene el estatus actual, si cambio de estatus en el periodo actual
  BEGIN
    SELECT OID_HIST_ESTA,he.esta_oid_esta_clie
      INTO lnOidHistorico,lnoidEstatus
      FROM MAE_CLIEN_HISTO_ESTAT he
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND PERD_OID_PERI = pnOidPeriodo;
       EXCEPTION
    WHEN NO_DATA_FOUND THEN -- si no cambio de estatus en periodo actual, obtiene ultimo estatus actual
      lnindEstaActu := 1;
    SELECT OID_HIST_ESTA,he.esta_oid_esta_clie
      INTO lnOidHistorico,lnoidEstatus
      FROM MAE_CLIEN_HISTO_ESTAT he
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND he.perd_oid_peri_peri_fin IS NULL;

  END;

  BEGIN
  SELECT he.esta_oid_esta_clie,he.esta_oid_esta_clie
    INTO lnAux,lnoidEstatusAnt
    FROM MAE_CLIEN_HISTO_ESTAT he
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND he.perd_oid_peri_peri_fin = pnOidPeriodoAnt;
     EXCEPTION
    WHEN NO_DATA_FOUND THEN
    lnAux := 0;
    lnoidEstatusAnt := NULL;
  END;

  IF(lnOidHistorico IS NOT NULL) THEN

    --Se verifica si el estatus actual es 2 y no tiene estatus anterior, la registrada se actualizo a Nueva.
    IF lnoidEstatus = 2 AND lnAux=0 THEN

  BEGIN
        UPDATE MAE_CLIEN_HISTO_ESTAT he
           SET he.perd_oid_peri          = nvl((SELECT p.oid_peri
                                                  FROM mae_clien c,
                                                       cra_perio p
                                                 WHERE c.oid_clie      = he.clie_oid_clie AND
                                                       c.fec_ingr BETWEEN p.fec_inic AND p.fec_fina
                                               ),pnOidPeriodoAnt), --campaña registro consultora
               he.perd_oid_peri_peri_fin = NULL,
               he.esta_oid_esta_clie     = 1,
               he.fec_modi               = SYSDATE,
               he.usu_modi               = psCodigoUsuario
         WHERE he.clie_oid_clie = pnOidCliente AND
               he.oid_hist_esta = lnOidHistorico;

         lnOidEstatusReev := 1;
       EXCEPTION
           WHEN DUP_VAL_ON_INDEX THEN
             BEGIN
              DELETE FROM MAE_CLIEN_HISTO_ESTAT
              WHERE OID_HIST_ESTA = lnOidHistorico;
              --Actualizamos el anterior estatus del cliente
  BEGIN
                SELECT OID_HIST_ESTA,
                       ESTA_OID_ESTA_CLIE
                  INTO lnOidHistorico,
                       lnOidEstatusReev
                  FROM MAE_CLIEN_HISTO_ESTAT
                 WHERE CLIE_OID_CLIE = pnOidCliente
                   AND PERD_OID_PERI_PERI_FIN = pnOidPeriodoAnt;
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
                  lnOidEstatusReev := lnoidEstatus;
                  lnOidHistorico := NULL;
              END;
  END;

       END;
    ELSE
      IF lnoidEstatus = 3 AND lnindEstaActu=1 THEN --si no cambio de estatus en el periodo y su estatus es 3, se genera estatus 4
          lnOidEstatusReev := 4;
          UPDATE mae_clien_histo_estat clhe
          SET clhe.perd_oid_peri_peri_fin =   pnOidPeriodoAnt,                --optimizar para que obtenga la campaña actual -1
              clhe.usu_modi = psCodigoUsuario,
              clhe.fec_modi = SYSDATE
          WHERE 1=1
            AND clhe.clie_oid_clie          IN (pnOidCliente)
            AND clhe.perd_oid_peri_peri_fin IS NULL;

        --inserta el nuevo estado vigente en el historial
         INSERT INTO mae_clien_histo_estat(
            OID_HIST_ESTA,
            PERD_OID_PERI,
            CLIE_OID_CLIE,
            PERD_OID_PERI_PERI_FIN,
            ESTA_OID_ESTA_CLIE,
            USU_MODI,
            FEC_MODI,
            FEC_CREA,
            USU_CREA)
          SELECT MAE_CLHE_SEQ.NEXTVAL
                ,pnOidPeriodo     --periodo actual
                ,clie.oid_clie -- oid de cliente
                ,NULL     -- periodo fin
                ,4
                ,USER
                ,SYSDATE
                ,SYSDATE
                ,USER
          FROM  mae_clien clie
          WHERE clie.oid_clie IN (pnOidCliente);


          SELECT OID_HIST_ESTA,he.esta_oid_esta_clie
          INTO lnOidHistorico,lnoidEstatus
          FROM MAE_CLIEN_HISTO_ESTAT he
          WHERE CLIE_OID_CLIE = pnOidCliente
          AND he.perd_oid_peri_peri_fin IS NULL;

      ELSE

      DELETE FROM MAE_CLIEN_HISTO_ESTAT
      WHERE OID_HIST_ESTA = lnOidHistorico;
          --Actualizamos el anterior estatus del cliente
           lnoidEstatusAnt:=nvl(lnoidEstatusAnt,lnoidEstatus);
           --calculamos el estatus nuevo
         CASE lnoidEstatusAnt
            WHEN 1 THEN  lnOidEstatusReev:= 1;
            WHEN 2 THEN  lnOidEstatusReev:= 4;
            WHEN 3 THEN  lnOidEstatusReev:= 4;
            WHEN 4 THEN  lnOidEstatusReev:= 5;
            WHEN 5 THEN
      BEGIN
                           BEGIN
                                SELECT COD_PERI INTO lvCodUltiPedi
                                  FROM (SELECT PER.COD_PERI, PSC.VAL_NUME_SOLI, PSC.VAL_TOTA_PAGA_LOCA
                                          FROM PED_SOLIC_CABEC     PSC,
                                               PED_TIPO_SOLIC_PAIS TSP,
                                               PED_TIPO_SOLIC      TS,
                                               CRA_PERIO           CRA,
                                               SEG_PERIO_CORPO     PER
                                         WHERE CRA.OID_PERI = PSC.PERD_OID_PERI
                                           AND PER.OID_PERI = CRA.PERI_OID_PERI
                                           AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                                           AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                                           AND PSC.CLIE_OID_CLIE = pnOidCliente
                                           AND TS.COD_TIPO_SOLI = 'C1'
                                           AND (PSC.ESSO_OID_ESTA_SOLI <> 4 OR (PSC.ESSO_OID_ESTA_SOLI = 4 AND EXISTS ( SELECT NULL
                                                                                                                          FROM ped_solic_cabec sc,
                                                                                                                               ped_tipo_solic_pais tsp,
                                                                                                                               ped_tipo_solic ts
                                                                                                                         WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                                                                                                                           AND tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli
                                                                                                                           AND ts.cod_tipo_soli IN ('SDAA','SDAN')
                                                                                                                           AND sc.fec_fact IS NOT NULL
                                                                                                                           AND sc.soca_oid_docu_refe = psc.oid_soli_cabe
                                                                                                                           AND sc.perd_oid_peri      > psc.perd_oid_peri
                                                                                                                       )
                                                                              )
                                                )
                                           AND psc.val_tota_paga_loca > 0
                                      ORDER BY CRA.FEC_INIC DESC, PSC.VAL_NUME_SOLI DESC)
                                WHERE ROWNUM = 1;
                           EXCEPTION WHEN no_data_found THEN
                                     SELECT MAX(per.cod_peri) INTO lvCodUltiPedi
                                      FROM Mae_Clien_Histo_Estat he,
                                           CRA_PERIO           CRA,
                                           SEG_PERIO_CORPO     PER
                                     WHERE he.clie_oid_clie      =pnOidCliente  AND
                                           he.esta_oid_esta_clie = 6 AND
                                           he.perd_oid_peri      = cra.oid_peri AND
                                           per.oid_peri          = cra.peri_oid_peri;
                           END;
                                lnCamSinPedi := gen_pkg_gener.gen_fn_devue_difer_perio(lvCodUltiPedi,psCodigoCampana);

                                IF lnCamSinPedi >= 18 THEN
                                   lnOidEstatusReev:= 7;
                                ELSE
                                   lnOidEstatusReev:= 5;
                                   indNoCrear := 0;
                                END IF;
                         END;
            WHEN 6 THEN lnOidEstatusReev:= 4;
            WHEN 7 THEN lnOidEstatusReev:= 7;
            WHEN 8 THEN lnOidEstatusReev:= 4;
          END CASE;

          IF indNoCrear = 0 THEN
             --Abrimos el estatus anterior
            UPDATE mae_clien_histo_estat clhe
               SET clhe.perd_oid_peri_peri_fin = NULL,                --optimizar para que obtenga la campaña actual -1
                   clhe.usu_modi               = psCodigoUsuario,
                   clhe.fec_modi               = SYSDATE
              WHERE 1=1
                AND clhe.clie_oid_clie          IN (pnOidCliente)
              AND clhe.perd_oid_peri_peri_fin  = pnOidPeriodoAnt;

          ELSE

          --cerramos el estatus anterior
          UPDATE mae_clien_histo_estat clhe
             SET clhe.perd_oid_peri_peri_fin = pnOidPeriodoAnt,                --optimizar para que obtenga la campaña actual -1
                 clhe.usu_modi               = psCodigoUsuario,
                 clhe.fec_modi               = SYSDATE
            WHERE 1=1
              AND clhe.clie_oid_clie          IN (pnOidCliente)
              AND clhe.perd_oid_peri_peri_fin IS NULL;

          --insertamos el nuevo estado vigente en el historial
            INSERT INTO mae_clien_histo_estat
           (
                OID_HIST_ESTA,
                PERD_OID_PERI,
                CLIE_OID_CLIE,
                PERD_OID_PERI_PERI_FIN,
                ESTA_OID_ESTA_CLIE,
                USU_MODI,
                FEC_MODI,
                FEC_CREA,
                USU_CREA)
            SELECT MAE_CLHE_SEQ.NEXTVAL
                  ,pnOidPeriodo     --periodo actual
                  ,clie.oid_clie -- oid de cliente
                  ,NULL     -- periodo fin
                  ,lnOidEstatusReev
                  ,psCodigoUsuario
                  ,SYSDATE
                  ,SYSDATE
                  ,psCodigoUsuario
            FROM  mae_clien clie
            WHERE clie.oid_clie IN (pnOidCliente);

           END IF;

            SELECT OID_HIST_ESTA,he.esta_oid_esta_clie
            INTO lnOidHistorico,lnoidEstatus
            FROM MAE_CLIEN_HISTO_ESTAT he
         WHERE CLIE_OID_CLIE = pnOidCliente
            AND he.perd_oid_peri_peri_fin IS NULL;

        END IF;
    END IF;


    IF(lnOidHistorico IS NOT NULL) THEN
      UPDATE mae_clien_histo_estat he
         SET PERD_OID_PERI_PERI_FIN = null,
             he.fec_modi            = SYSDATE,
             he.usu_modi            = psCodigoUsuario
       WHERE OID_HIST_ESTA = lnOidHistorico;

      CASE lnOidEstatusReev
         WHEN 1 THEN lnCamSinPedi:=0;
         WHEN 2 THEN lnCamSinPedi:=0;
         WHEN 3 THEN lnCamSinPedi:=0;
         WHEN 4 THEN lnCamSinPedi:=1;
         WHEN 5 THEN
           BEGIN
             BEGIN
             SELECT COD_PERI INTO lvCodUltiPedi
                FROM (SELECT PER.COD_PERI, PSC.VAL_NUME_SOLI, PSC.VAL_TOTA_PAGA_LOCA
                        FROM PED_SOLIC_CABEC     PSC,
                             PED_TIPO_SOLIC_PAIS TSP,
                             PED_TIPO_SOLIC      TS,
                             CRA_PERIO           CRA,
                             SEG_PERIO_CORPO     PER,
                             ped_solic_cabec     pdx,
                             PED_TIPO_SOLIC_PAIS TSPx,
                             PED_TIPO_SOLIC      TSx

                       WHERE pdx.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND pdx.tspa_oid_tipo_soli_pais = tspx.oid_tipo_soli_pais
                         AND tspx.tsol_oid_tipo_soli     = tsx.oid_tipo_soli
                         AND tsx.cod_tipo_soli           = 'SOC'
                         AND CRA.OID_PERI = PSC.PERD_OID_PERI
                         AND PER.OID_PERI = CRA.PERI_OID_PERI
                         AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                         AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                         AND PSC.CLIE_OID_CLIE = pnOidCliente
                         AND TS.COD_TIPO_SOLI = 'C1'
                         AND (PSC.ESSO_OID_ESTA_SOLI <> 4 OR (PSC.ESSO_OID_ESTA_SOLI = 4 AND EXISTS ( SELECT NULL
                                                                                                        FROM ped_solic_cabec sc,
                                                                                                             ped_tipo_solic_pais tsp,
                                                                                                             ped_tipo_solic ts
                                                                                                       WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                                                                                                         AND tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli
                                                                                                         AND ts.cod_tipo_soli IN ('SDAA','SDAN')
                                                                                                         AND sc.fec_fact IS NOT NULL
                                                                                                         AND sc.soca_oid_docu_refe = psc.oid_soli_cabe
                                                                                                         AND sc.perd_oid_peri      > psc.perd_oid_peri
                                                                                                     )
                                                            )
                              )
                         AND psc.val_tota_paga_loca > 0
                    ORDER BY CRA.FEC_INIC DESC, PSC.VAL_NUME_SOLI DESC)
              WHERE ROWNUM = 1;
              EXCEPTION WHEN no_data_found THEN
                   SELECT MAX(per.cod_peri) INTO lvCodUltiPedi
                    FROM Mae_Clien_Histo_Estat he,
                         CRA_PERIO           CRA,
                         SEG_PERIO_CORPO     PER
                   WHERE he.clie_oid_clie      =pnOidCliente  AND
                         he.esta_oid_esta_clie = 6 AND
                         he.perd_oid_peri      = cra.oid_peri AND
                         per.oid_peri          = cra.peri_oid_peri;
             END;
             lnCamSinPedi := gen_pkg_gener.gen_fn_devue_difer_perio(lvCodUltiPedi,psCodigoCampana);
           END;
         WHEN 6 THEN lnCamSinPedi:=0;
         WHEN 7 THEN
                BEGIN
             lvCodUltiPedi:=19;--gen_pkg_gener.gen_fn_clien_perio_ultim_pedid(pnOidCliente);
             lnCamSinPedi:=19;--gen_pkg_gener.gen_fn_devue_difer_perio(lvCodUltiPedi,psCodigoCampana);
                END;
         WHEN 8 THEN lnCamSinPedi:=0;
       END CASE;

      UPDATE mae_clien_datos_adici mcda
         SET mcda.esta_oid_esta_clie = lnOidEstatusReev,
             --mcda.ind_acti = decode(lnOidEstatusReev,7,0,mcda.ind_acti),
             mcda.num_camp_sin_pedi = lnCamSinPedi,
             mcda.fec_ulti_actu = SYSDATE,
             mcda.usu_modi = psCodigoUsuario
       WHERE mcda.clie_oid_clie = pnOidCliente;

      END IF;
   END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REVER_ESTAT_CLIEN: ' || ls_sqlerrm);

END MAE_PR_REVER_ESTAT_CLIEN;

/**************************************************************************
Descripcion       : Devuelve modulo 10 de un valor
Fecha Creacion    : 28/11/2013
Parametros Entrada:
    psCodigoPais    :
    pnOidCliente    :
    pnOidPeriodo    :
    psCodigoCampana :
    pnOidPeriodoAnt :
    psCodigoUsuario :
Autor             : CSVD - FFVV
***************************************************************************/
FUNCTION MAE_FN_DEVUE_MODUL_DIEZ(pscodigo VARCHAR2) RETURN VARCHAR2 IS
    lscodigo mae_clien.cod_clie%TYPE;
    cont     NUMBER;
    factor   NUMBER;
    multi    NUMBER;
    suma     NUMBER := 0;
    res      NUMBER := 0;

    lnPrefijo       NUMBER;
    lnCaracter      NUMBER;
  BEGIN
    --Obtenemos los 2 Primeros Caracteres
    lnPrefijo := TO_NUMBER(substr(pscodigo,1,2));

    --Validamos Rango 1 a 24
    IF((lnPrefijo >= 1) and (lnPrefijo <=24)) THEN
      lnCaracter := TO_NUMBER(substr(pscodigo,3,1));
   /*   IF(lnCaracter >= 6) THEN   -- RETIRO DE LA VALIDACION DEL 3ER CARACTER A SOLICITUD DEL RCR
        RETURN NULL;               -- ECU-SSICC-2012-0206
      END IF;*/
    ELSE --Validamos Rango 81 a 84
      IF((lnPrefijo < 81) OR (lnPrefijo >84)) THEN
        RETURN NULL;
      END IF;
    END IF;

    --Numero a trabajar
    lscodigo := substr(pscodigo, 0, length(pscodigo) - 1);
    cont   := length(lscodigo);
    factor := 2;

    LOOP
      IF SUBSTR(lscodigo, 1, 1) IN ('3','5') AND cont IN (2,3) THEN
         cont := cont - 1;
      ELSE
         -- Multiplica el digito por el factor segun corresponda [2 - 1]
         multi := to_number(substr(lscodigo, cont, 1)) * factor;
         -- Suma los digitos del resultado de la multiplicacion
         suma := suma + (trunc(multi / 10) + MOD(multi, 10));
         -- Actualiza el factor
         IF factor = 2 THEN
           factor := 1;
         ELSE
           factor := 2;
         END IF;
         cont := cont - 1;
      END IF;
      EXIT WHEN cont = 0;
    END LOOP;
    -- Obtiene el residuo de la suma entre 10
    res := MOD(suma, 10);

    IF (res != 0) THEN
      res := 10 - res;
    END IF;

    -- Devuelve el digito verificador
    RETURN to_char(res);

  END MAE_FN_DEVUE_MODUL_DIEZ;

/**************************************************************************
Descripcion       : Proceso de Calculo de Estatus por Consultora
Fecha Creacion    : 18/12/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoRegion   :  Codigo de Region
  psCodigoCliente  : Codigo Cliente
  psCodigoUsuario  :  Codigo de Usuario

Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_PROCE_CALCU_ESTAT(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psCodigoRegion        VARCHAR2,
                                   psCodigoCliente       VARCHAR2,
                                   psCodigoUsuario       VARCHAR2
                                   ) IS

  lnOidPais          SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca         SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal         SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
  lnOidRegion        ZON_REGIO.OID_REGI%TYPE;

  lsCodPeriodoAnt    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAnt    CRA_PERIO.OID_PERI%TYPE;

  lnIndEjecRein      SEG_PARAM_INTER_PAIS.IND_EJRN%TYPE;
  lnNivelGravBloq    NUMBER;
  lnNumPeriRein      SEG_PAIS.NUM_PERI_REIN%TYPE;
  lsCodPeriodoBlo    SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnNumCampSinPedi   NUMBER;
  lnOidHistoEsta     MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;
  lnOidPeriodoEsta   MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;

  lnOidBloqueo       MAE_CLIEN_BLOQU.OID_BLOQ%TYPE;
  lnNivelGravBloq2   NUMBER;
  lbBloqueoHecho     BOOLEAN;
  lnOcurrencias      NUMBER;

  CURSOR c_Consultoras(oidPeriodo NUMBER, oidRegion NUMBER) IS
    SELECT consulta2.*,
          (SELECT mes.OID_ESTA_CLIE
            FROM MAE_ESTAT_CLIEN mes
            WHERE mes.COD_ESTA_CLIE = consulta2.cod_esta_clie_nuevo  ) esta_oid_esta_clie_nuevo
          FROM (
          SELECT consulta.clie_oid_clie,
                 consulta.oid_clie_dato_adic,
                 consulta.num_camp_sin_pedi,
                 consulta.cod_esta_clie,
                 consulta.esta_oid_esta_clie,
                 consulta.cantidadPedidos,
                 CASE WHEN (cantidadPedidos > 0) THEN
                      case when cod_esta_clie = '03' then '03' else
                      case when cod_esta_clie = '01' then '02' else
                      case when cod_esta_clie = '02' then '03' else
                      case when cod_esta_clie = '04' then '03' else
                      case when cod_esta_clie = '05' then '06' else
                      case when cod_esta_clie = '06' then '03' else
                      case when cod_esta_clie = '07' then '08' else
                      case when cod_esta_clie = '08' then '03'
                      else cod_esta_clie     end end end end end end end end
                 ELSE case when cod_esta_clie = '02' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '03' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '04' and ((num_camp_sin_pedi + 1) > num_peri_egre) then '05' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) >= num_peri_reti) then '07' else
                      case when cod_esta_clie = '06' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '08' and ((num_camp_sin_pedi + 1) = 1) then '04' else
                      case when cod_esta_clie = '05' and ((num_camp_sin_pedi + 1) < num_peri_reti)then '05'
                      else cod_esta_clie     end end end end end end end
                 END cod_esta_clie_nuevo
           FROM (SELECT a.clie_oid_clie,
                        a.oid_clie_dato_adic,
                        a.num_camp_sin_pedi,
                        p.num_peri_egre,
                        p.num_peri_reti,
                        e.cod_esta_clie,
                        a.esta_oid_esta_clie,
                        a.ind_acti,
                        (SELECT count(1)
                           FROM PED_SOLIC_CABEC psc,
                                PED_TIPO_SOLIC_PAIS tsp,
                                PED_TIPO_SOLIC ts
                          WHERE psc.ind_oc = 1
                            AND psc.clie_oid_clie = b.oid_clie
                            AND psc.perd_oid_peri = oidPeriodo
                            AND psc.FEC_FACT IS NOT null
                            AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
                            AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                            AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                            AND ts.IND_DEVO  = 0
                            AND psc.MODU_OID_MODU <> 15
                            AND ts.IND_ANUL  = 0
                            AND psc.IND_TS_NO_CONSO = 1) cantidadPedidos,
                        p.oid_pais
                 FROM MAE_CLIEN_DATOS_ADICI a,
                      SEG_PAIS p,
                      MAE_CLIEN b,
                      MAE_CLIEN_TIPO_SUBTI ct,
                      MAE_TIPO_CLIEN t,
                      MAE_ESTAT_CLIEN e
                WHERE a.clie_oid_clie = b.oid_clie
                  AND b.pais_oid_pais = p.oid_pais
                  AND b.oid_clie = ct.clie_oid_clie
                  AND ct.ticl_oid_tipo_clie = t.oid_tipo_clie
                  AND a.esta_oid_esta_clie = e.oid_esta_clie
                  AND a.esta_oid_esta_clie IS NOT NULL
                  AND t.cod_tipo_clie = '02'
                  AND ((oidRegion IS NULL AND b.cod_clie = psCodigoCliente) OR
                       (oidRegion IS NOT NULL AND b.oid_clie IN (SELECT c.oid_clie
                                                                   FROM mae_clien c
                                                                   JOIN mae_clien_unida_admin u ON (u.clie_oid_clie = c.oid_clie)
                                                                   JOIN zon_terri_admin t ON (u.ztad_oid_terr_admi = t.oid_terr_admi)
                                                                   JOIN zon_secci s ON (t.zscc_oid_secc = s.oid_secc)
                                                                   JOIN zon_zona z ON (s.zzon_oid_zona = z.oid_zona),
                                                                        cra_perio periodoinicio,
                                                                        cra_perio periodoactual
                                                                  WHERE u.perd_oid_peri_ini = periodoinicio.oid_peri
                                                                    AND periodoactual.oid_peri = oidPeriodo
                                                                    AND periodoinicio.marc_oid_marc = periodoactual.marc_oid_marc
                                                                    AND periodoinicio.cana_oid_cana = periodoactual.cana_oid_cana
                                                                    AND periodoinicio.fec_inic <= periodoactual.fec_inic
                                                                    AND u.ind_acti = 1
                                                                    AND zorg_oid_regi = oidRegion )
                        )
                      )
                ) consulta
          ) consulta2;

  TYPE interfazConsultoras IS RECORD(
    oidCliente        MAE_CLIEN.OID_CLIE%TYPE,
    oidDatosAdici     MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE,
    numCampSinPedi    MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE,
    codEstatus        MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatus        MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE,
    cantPedidos       NUMBER,
    codEstatusNuevo   MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
    oidEstatusNuevo   MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE
  );

  TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
  interfazRecordN interfazConsultorasTab;

  TYPE t_oidDatosAdi         IS TABLE OF MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE;
  TYPE t_numCampSinPedi      IS TABLE OF MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE;
  TYPE t_oidEstatus          IS TABLE OF MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidCliente          IS TABLE OF MAE_CLIEN.OID_CLIE%TYPE;

  TYPE t_oidPeriodoIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
  TYPE t_oidEstatusIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidClienteIns       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;

  TYPE t_oidEstatusUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  TYPE t_oidHistoEsta       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  TYPE t_oidPeriodoUpd       IS TABLE OF MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI_PERI_FIN%TYPE;
  TYPE t_oidHistoEsta2        IS TABLE OF MAE_CLIEN_HISTO_ESTAT.OID_HIST_ESTA%TYPE;

  v_oidDatosAdi              t_oidDatosAdi  := t_oidDatosAdi();
  v_numCampSinPedi           t_numCampSinPedi  := t_numCampSinPedi();
  v_oidEstatus               t_oidEstatus  := t_oidEstatus();
  v_oidCliente               t_oidCliente  := t_oidCliente();

  v_oidPeriodoIns            t_oidPeriodoIns  := t_oidPeriodoIns();
  v_oidEstatusIns            t_oidEstatusIns  := t_oidEstatusIns();
  v_oidClienteIns            t_oidClienteIns  := t_oidClienteIns();

  v_oidEstatusUpd            t_oidEstatusUpd  := t_oidEstatusUpd();
  v_oidHistoEsta             t_oidHistoEsta  := t_oidHistoEsta();

  v_oidPeriodoUpd            t_oidPeriodoUpd  := t_oidPeriodoUpd();
  v_oidHistoEsta2            t_oidHistoEsta2  := t_oidHistoEsta2();

  W_FILAS_PROCE      NUMBER:=10000;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Obtenemos el periodo Anterior
  lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                             lnOidPais, lnOidMarca, lnOidCanal, -1);
  lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt, lnOidMarca, lnOidCanal);

  --Recuperamos el Oid Region
  BEGIN
  SELECT OID_REGI
    INTO lnOidRegion
    FROM ZON_REGIO
   WHERE COD_REGI = psCodigoRegion
     AND IND_ACTI = 1;
     EXCEPTION
       WHEN NO_DATA_FOUND THEN
         lnOidRegion := NULL;
  END;


  --REALIZAMOS EL PROCESO DE LA EVALUACION DE ESTATUS DE LAS CONSULTORA
  OPEN c_Consultoras(lnOidPeriodo,lnOidRegion);
  LOOP
    FETCH c_Consultoras BULK COLLECT INTO interfazRecordN LIMIT W_FILAS_PROCE;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        --Evaluamos Numero de Campaña sin Pedidos
        IF(interfazRecordN(x).cantPedidos > 0) THEN
          lnNumCampSinPedi := 0;
        ELSE
          IF(interfazRecordN(x).numCampSinPedi IS NULL) THEN
            lnNumCampSinPedi := 1;
          ELSE
            lnNumCampSinPedi := interfazRecordN(x).numCampSinPedi + 1;
          END IF;
        END IF;

        --Se actualiza en la tabla MAE_CLIEN_DATOS_ADICI, los campos
        --Numero de Campaña sin Pedido y oid Estatus
        v_oidDatosAdi.EXTEND(1);
        v_numCampSinPedi.EXTEND(1);
        v_oidEstatus.EXTEND(1);

        v_oidDatosAdi(v_oidDatosAdi.COUNT)       := interfazRecordN(x).oidDatosAdici;
        v_numCampSinPedi(v_numCampSinPedi.COUNT) := lnNumCampSinPedi;
        v_oidEstatus(v_oidEstatus.COUNT)         := interfazRecordN(x).oidEstatusNuevo;

        --Si el Nuevo Estatus es REINGRESO, se actualiza el campo fecha Reingreso en MAE_CLIEN
        IF(interfazRecordN(x).codEstatusNuevo = '06') THEN
          v_oidCliente.EXTEND(1);
          v_oidCliente(v_oidCliente.COUNT) := interfazRecordN(x).oidCliente;
        END IF;


        --Si se ha cambiado el Estatus del Cliente, Se realiza una evaluacion del Historico de Estatus
        IF(interfazRecordN(x).codEstatus <> interfazRecordN(x).codEstatusNuevo) THEN
          BEGIN
            SELECT OID_HIST_ESTA,
                   PERD_OID_PERI
              INTO lnOidHistoEsta,
                   lnOidPeriodoEsta
              FROM (
                    SELECT h.oid_hist_esta,
                           h.PERD_OID_PERI,
                           i.FEC_INIC
                      FROM mae_clien_histo_estat h, cra_perio i, cra_perio a
                     WHERE h.perd_oid_peri_peri_fin IS NULL
                       AND h.perd_oid_peri = i.oid_peri
                       AND i.pais_oid_pais = a.pais_oid_pais
                       AND i.marc_oid_marc = a.marc_oid_marc
                       AND i.cana_oid_cana = a.cana_oid_cana
                       AND i.fec_inic <= a.fec_inic
                       AND a.oid_peri = lnOidPeriodo
                       AND h.esta_oid_esta_clie <> interfazRecordN(x).oidEstatusNuevo
                       AND h.clie_oid_clie = interfazRecordN(x).oidCliente
                     ORDER BY CLIE_OID_CLIE, FEC_INIC DESC)
             WHERE ROWNUM = 1;
          EXCEPTION
            WHEN OTHERS THEN
              lnOidHistoEsta := NULL;
          END;

          --Si no se encuentra registro en Historico se crea uno nuevo
          IF(lnOidHistoEsta IS NULL) THEN
            v_oidPeriodoIns.EXTEND(1);
            v_oidEstatusIns.EXTEND(1);
            v_oidClienteIns.EXTEND(1);

            v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
            v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
            v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;
          ELSE

            --En caso de que el periodo del registro en Historico sea igual a Periodo Actual
            --Solo se actualizara el campo Estatus
            IF(lnOidPeriodoEsta = lnOidPeriodo) THEN
              v_oidEstatusUpd.EXTEND(1);
              v_oidHistoEsta.EXTEND(1);

              v_oidEstatusUpd(v_oidEstatusUpd.COUNT) := interfazRecordN(x).oidEstatusNuevo;
              v_oidHistoEsta(v_oidHistoEsta.COUNT) := lnOidHistoEsta;
            ELSE
              --Se actualiza el campo Fecha Fin para el registro del Historico
              v_oidPeriodoUpd.EXTEND(1);
              v_oidHistoEsta2.EXTEND(1);

              v_oidPeriodoUpd(v_oidPeriodoUpd.COUNT) := lnOidPeriodoAnt;
              v_oidHistoEsta2(v_oidHistoEsta2.COUNT) := lnOidHistoEsta;

              --Y se crea un nuevo registro para el estatus nuevo
              v_oidPeriodoIns.EXTEND(1);
              v_oidEstatusIns.EXTEND(1);
              v_oidClienteIns.EXTEND(1);

              v_oidPeriodoIns(v_oidPeriodoIns.COUNT) := lnOidPeriodo;
              v_oidEstatusIns(v_oidEstatusIns.COUNT) := interfazRecordN(x).oidEstatusNuevo;
              v_oidClienteIns(v_oidClienteIns.COUNT) := interfazRecordN(x).oidCliente;

            END IF;

          END IF;

        END IF;

      END LOOP;

    END IF;
    EXIT WHEN c_Consultoras%NOTFOUND;
  END LOOP;
  CLOSE c_Consultoras;

  --Actualizamos en la tabla MAE_CLIEN_DATOS_ADICI
  FORALL i IN 1..v_oidDatosAdi.COUNT
    UPDATE MAE_CLIEN_DATOS_ADICI
       SET NUM_CAMP_SIN_PEDI = v_numCampSinPedi(i),
           ESTA_OID_ESTA_CLIE = v_oidEstatus(i)
     WHERE OID_CLIE_DATO_ADIC = v_oidDatosAdi(i);

  --Actualizamos en la tabla MAE_CLIEN
  FORALL i IN 1..v_oidCliente.COUNT
    UPDATE MAE_CLIEN
       SET FEC_REIN = SYSDATE
     WHERE OID_CLIE = v_oidCliente(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET ESTA_OID_ESTA_CLIE = v_oidEstatusUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta(i);

  --Actualizamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidHistoEsta2.COUNT
    UPDATE MAE_CLIEN_HISTO_ESTAT
       SET PERD_OID_PERI_PERI_FIN = v_oidPeriodoUpd(i)
     WHERE OID_HIST_ESTA = v_oidHistoEsta2(i);

  --Insertamos en la tabla MAE_CLIEN_HISTO_ESTAT
  FORALL i IN 1..v_oidPeriodoIns.COUNT
    INSERT INTO MAE_CLIEN_HISTO_ESTAT
      (OID_HIST_ESTA, PERD_OID_PERI,
       CLIE_OID_CLIE, ESTA_OID_ESTA_CLIE,USU_MODI,FEC_MODI)
    VALUES
      (MAE_CLHE_SEQ.NEXTVAL, v_oidPeriodoIns(i),
       v_oidClienteIns(i), v_oidEstatusIns(i),psCodigoUsuario,SYSDATE);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR MAE_PR_PROCE_CALCU_ESTAT: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);

END MAE_PR_PROCE_CALCU_ESTAT;


/**************************************************************************
Descripcion       : Devuelve Descripcion de Bloqueo del cliente
Fecha Creacion    : 22/01/2014
Parametros Entrada:
  psCodigoCliente       :     Codigo Cliente
  psAccionBloqueo       :     Accion Bloqueo: B-Bloquear, D-Desbloquear
  psTipoBloqueo         :     Tipo Bloqueo

Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION MAE_FN_DEVUE_BLOQU_DESBL_CLIEN
  (psCodigoCliente        VARCHAR2,
   psAccionBloqueo        VARCHAR2,
   psTipoBloqueo          VARCHAR2)
RETURN VARCHAR2
IS
  lnOidCliente      MAE_CLIEN.OID_CLIE%TYPE;
  lnOcurrencias     NUMBER;
  lsResultado       VARCHAR2(50);
  lnOidClienteNoAct MAE_CLIEN.OID_CLIE%TYPE;
  lbEncontrado      BOOLEAN;
BEGIN
    lsResultado := '';

    --El Sistema busca al cliente
    BEGIN
        SELECT CLIE_OID_CLIE
        INTO lnOidCliente
        FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi
        WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
        AND cli.COD_CLIE = psCodigoCliente;

        lnOcurrencias := 1;
    EXCEPTION
    WHEN OTHERS THEN
        lnOcurrencias := 0;
    END;

    IF(lnOcurrencias > 0) THEN
        -- verificando q cliente exista pero con indicador 1 ACTIVO
        lnOcurrencias := 0;
        begin
             SELECT CLIE_OID_CLIE
              INTO lnOidClienteNoAct
              FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi
             WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
               AND cli.COD_CLIE = psCodigoCliente
               AND adi.IND_ACTI = 1;

               lnOcurrencias := 1;
        EXCEPTION
            WHEN OTHERS THEN
                lnOcurrencias := 0;
        END;

        IF(lnOcurrencias > 0) THEN
            --Cliente existe y está activo
            IF(psAccionBloqueo = 'B') THEN
                -- Validaciones para bloqueo de clientes

                --Verifica si el Cliente tiene al menos un bloqueo vigente
                SELECT COUNT(1)
                INTO lnOcurrencias
                FROM MAE_CLIEN_BLOQU blo,
                     MAE_TIPO_BLOQU tip
                WHERE blo.CLIE_OID_CLIE = lnOidCliente
                AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                AND tip.COD_TIPO_BLOQ = psTipoBloqueo
                AND blo.FEC_DESB IS NULL
                AND blo.FEC_BLOQ IS NOT NULL;

                IF(lnOcurrencias = 0) THEN
                   BEGIN
                      SELECT VAL_I18N
                      INTO lsResultado
                      FROM MAE_CLIEN_BLOQU blo,
                         MAE_TIPO_BLOQU tip,
                         MAE_TIPO_BLOQU tipx,
                         GEN_I18N_SICC_COMUN X
                      WHERE blo.CLIE_OID_CLIE = lnOidCliente
                      AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                      AND blo.FEC_DESB IS NULL
                      AND blo.FEC_BLOQ IS NOT NULL
                      AND tipx.COD_TIPO_BLOQ = psTipoBloqueo
                      AND tip.NUM_NIVE_GRAV_BLOQ > tipx.NUM_NIVE_GRAV_BLOQ
                      AND X.ATTR_ENTI = 'MAE_TIPO_BLOQU'
                      AND tip.OID_TIPO_BLOQ = X.VAL_OID
                      AND rownum = 1;
                   EXCEPTION
                   WHEN no_data_found THEN
                        lsResultado := '';
                   END;

                ELSE
                   BEGIN
                      SELECT VAL_I18N
                      INTO lsResultado
                      FROM
                        GEN_I18N_SICC_COMUN X,
                        MAE_TIPO_BLOQU Y
                      WHERE X.ATTR_ENTI = 'MAE_TIPO_BLOQU'
                       AND Y.OID_TIPO_BLOQ = X.VAL_OID
                       AND Y.COD_TIPO_BLOQ = psTipoBloqueo;
                   EXCEPTION
                   WHEN no_data_found THEN
                        lsResultado := '';
                   END;
                END IF;
            END IF;

            IF(psAccionBloqueo = 'D') THEN

                lbEncontrado := FALSE;
                lnOcurrencias := 0;

                FOR x IN (SELECT tip.COD_TIPO_BLOQ
                          FROM MAE_CLIEN_BLOQU blo,
                               MAE_TIPO_BLOQU tip
                         WHERE blo.CLIE_OID_CLIE = lnOidCliente
                           AND blo.TIBQ_OID_TIPO_BLOQ = tip.OID_TIPO_BLOQ
                           AND blo.FEC_DESB IS NULL
                           AND blo.FEC_BLOQ IS NOT NULL) LOOP

                    IF(x.COD_TIPO_BLOQ = psTipoBloqueo) THEN
                        lbEncontrado := TRUE;
                        EXIT;
                    ELSE
                        BEGIN
                            SELECT VAL_I18N
                            INTO lsResultado
                            FROM
                              GEN_I18N_SICC_COMUN X1,
                              MAE_TIPO_BLOQU Y1
                            WHERE X1.ATTR_ENTI = 'MAE_TIPO_BLOQU'
                             AND Y1.OID_TIPO_BLOQ = X1.VAL_OID
                             AND Y1.COD_TIPO_BLOQ = x.COD_TIPO_BLOQ;
                         EXCEPTION
                         WHEN no_data_found THEN
                              lsResultado := '';
                         END;
                    END IF;

                END LOOP;
            END IF;

        ELSE
            --Cliente existe, pero está INACTIVO
            lsResultado := '';
        END IF;

    ELSE
        --Cliente no existe
        lsResultado := '';
    END IF;

    RETURN lsResultado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_BLOQU_DESBL_CLIEN: ' || ls_sqlerrm);

END MAE_FN_DEVUE_BLOQU_DESBL_CLIEN;

/**************************************************************************
Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
Devuelve Periodo MAYOR si hay cruce de campa?a
Fecha Creacion : 23/01/2014
Autor : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_DEVUE_PERIO_MAYOR_FECHA
( pscodpais  VARCHAR2,
  pscodmarca VARCHAR2,
  pscodcanal VARCHAR2,
  pdfecha    DATE
) RETURN VARCHAR2 IS

  lsPeriodo   VARCHAR2(20);
BEGIN
  lsPeriodo := gen_pkg_gener.gen_fn_devue_perio_mayor_fecha(pscodpais, pscodmarca, pscodcanal, pdfecha);

  RETURN lsPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    RETURN '';
END MAE_FN_DEVUE_PERIO_MAYOR_FECHA;


/**************************************************************************
Descripcion       : Redifine vigencia de la unidad administrativa actual y
                    anterior
Fecha Creacion    : 20/02/2014
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  psCodigoPeriodo    :  Codigo Periodo
  psCodigoUsuario    :  Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_REDEF_VIGEN_UNIDA_ADMIN
  (psCodigoPais               VARCHAR2,
   pnOidCliente               NUMBER,
   psCodigoPeriodo            VARCHAR2,
   psCodigoUsuario            VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lsCodigoPeriodoAct          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoAnt          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoAnt2         SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAct             CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoAnt             CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoAnt2            CRA_PERIO.OID_PERI%TYPE;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Obtenemos el Periodo Inicio de la UNIDAD ADMINISTRATIVA ACTUAL
  SELECT PERD_OID_PERI_INI
    INTO lnOidPeriodoAct
    FROM MAE_CLIEN_UNIDA_ADMIN
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND PERD_OID_PERI_FIN IS NULL;

  --Obtenemos el codigo Periodo Anterior
  lsCodigoPeriodoAct := INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(lnOidPeriodoAct);

  --RECUPERAMOS EL PERIODO ANTERIOR PARA UBICAR EL PENULTIMO REGISTRO
  lsCodigoPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodigoPeriodoAct,
                               lnOidPais, lnOidMarca, lnOidCanal,-1);

  lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoAnt, lnOidMarca, lnOidCanal);

  --RECUPERAMOS EL PERIODO ANTERIOR PARA ACTUALIZAR EL PENULTIMO REGISTRO
  lsCodigoPeriodoAnt2 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                               lnOidPais, lnOidMarca, lnOidCanal,-1);

  lnOidPeriodoAnt2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoAnt2, lnOidMarca, lnOidCanal);

  -- Actualizamos el penultimo registro de la entidad Unidad Administrativa
  UPDATE MAE_CLIEN_UNIDA_ADMIN
     SET PERD_OID_PERI_INI = (CASE WHEN PERD_OID_PERI_INI = PERD_OID_PERI_FIN THEN lnOidPeriodoAnt2
                                  ELSE PERD_OID_PERI_INI END),
         PERD_OID_PERI_FIN = lnOidPeriodoAnt2,
         IND_ACTI = 0,
         USU_MODI = psCodigoUsuario,
         FEC_ULTI_ACTU = SYSDATE
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND PERD_OID_PERI_FIN = lnOidPeriodoAnt;

  -- Actualizamos el ultimo registro de la entidad Unidad Administrativa
  UPDATE MAE_CLIEN_UNIDA_ADMIN
     SET PERD_OID_PERI_INI = lnOidPeriodo,
         IND_ACTI = 1,
         USU_MODI = psCodigoUsuario,
         FEC_ULTI_ACTU = SYSDATE
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND PERD_OID_PERI_FIN IS NULL;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REDEF_VIGEN_UNIDA_ADMIN: ' || ls_sqlerrm);

END MAE_PR_REDEF_VIGEN_UNIDA_ADMIN;

/***************************************************************************
    Descripcion           : Valida la Regularizacion de Bloqueos - Desbloqueos de Clientes
    Fecha Creacion    : 26/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
FUNCTION MAE_FN_VALID_REGUL_CLIEN
( psCodigoCliente                   VARCHAR2,
  psCodigoUsuario                  VARCHAR2
) RETURN NUMBER IS

    lnOidCliente        MAE_CLIEN.OID_CLIE%TYPE;
    lnOcurrencias     NUMBER := 0;
BEGIN
    lnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(psCodigoCliente);

    SELECT COUNT (1)
      INTO lnOcurrencias
      FROM mae_clien_bloqu
     WHERE clie_oid_clie = lnOidCliente AND
                  fec_bloq IS NOT NULL AND
                  fec_desb IS NOT NULL;

    IF(lnOcurrencias = 0) THEN
        RETURN 0;   --datos no son validos
    ELSE
        SELECT COUNT (1)
          INTO lnOcurrencias
          FROM mae_clien_bloqu
         WHERE clie_oid_clie = lnOidCliente AND
                      fec_desb IS NOT NULL;

        IF(lnOcurrencias = 0) THEN
            RETURN 1;   --cliente sin desbloqueo
        ELSE
            SELECT COUNT (1)
              INTO lnOcurrencias
              FROM mae_clien_bloqu
             WHERE clie_oid_clie = lnOidCliente AND
                          fec_desb IS NOT NULL AND
                          ind_desb = '1';

            IF(lnOcurrencias = 0) THEN
                RETURN 2;   --desbloqueo del cliente ya fue completado por el usuario
            ELSE
                SELECT COUNT (1)
                  INTO lnOcurrencias
                  FROM seg_usuar_bloqu sub, mae_tipo_bloqu mtb, mae_valor_accio_bloqu mvab
                 WHERE sub.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                   AND mtb.maab_oid_valo_acci_desb = mvab.oid_valo_acci_bloq
                   AND mvab.cod_valo_bloq IN ('B', 'M')
                   AND sub.usua_cod_usua = psCodigoUsuario;

                IF(lnOcurrencias = 0) THEN
                    RETURN 0;   --datos no son validos
                ELSE
                    RETURN 3;   --Esta seguro que desea dar por solucionado el desbloqueo de la(s) consultora(s) seleccionada(s)?
                END IF;
            END IF;
        END IF;
    END IF;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VALID_REGUL_CLIEN: '||ls_sqlerrm);
END MAE_FN_VALID_REGUL_CLIEN;

/***************************************************************************
    Descripcion           : Actualiza la Regularizacion de Bloqueos - Desbloqueos de Clientes
    Fecha Creacion    : 26/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_REGUL_CLIEN
  (psCodigoCliente                   VARCHAR2,
   pnOidBloqueo                       NUMBER,
   psCodigoRetorno                  OUT VARCHAR2)
IS
    lnOidCliente        MAE_CLIEN.OID_CLIE%TYPE;
    lnOidBloqueo        mae_clien_bloqu.oid_bloq%TYPE;

BEGIN
    lnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(psCodigoCliente);
    lnOidBloqueo := pnOidBloqueo;
    psCodigoRetorno := '0'; --Actualizacion OK

    IF lnOidBloqueo IS NULL THEN
      UPDATE mae_clien_bloqu
         SET ind_desb = '0',
                obs_desb = 'Observación resuelta. ' || obs_desb
             WHERE clie_oid_clie = lnOidCliente AND
                    ind_desb = 1 AND
                    fec_desb = (select max(a.fec_desb) from mae_clien_bloqu a);
    END IF;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_REGUL_CLIEN: '||ls_sqlerrm);
END MAE_PR_ACTUA_REGUL_CLIEN;


/**************************************************************************
Descripcion       : Valida si el numero de documento pasa la validacion
                    del modulo PTR
Fecha Creacion    : 04/04/2014
Parametros Entrada:
  psCodigo        :     Codigo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAE_FN_VALID_MODUL_PTR
  (psCodigo          VARCHAR2)
RETURN VARCHAR2
IS
  lsCadena               VARCHAR2(7);
  x                      NUMBER;
  y                      NUMBER;
  z                      NUMBER;

  lnMontop               NUMBER;
  lnMonto                NUMBER;

  lnMontou               NUMBER;
  lnMontod               NUMBER;
  lnMontot               NUMBER;
  lnDigitoVer            NUMBER;

  lsNumCtrl              VARCHAR2(1);
  lsResultado            VARCHAR2(1);
BEGIN
  lsCadena := LPAD(psCodigo, 7, '0');
  x := 1;
  y := 2;
  z := 7;

  lnMontop := 0;

  FOR x IN 1 .. 7 LOOP
    lnMonto := TO_NUMBER(SUBSTR(lsCadena, z, 1))*y;
    lnMontop := lnMontop + lnMonto;

    IF(y=1) THEN
      y:=2;
    ELSE
      y:=y-1;
    END IF;

    z := z-1;
  END LOOP;

  lnMontou := TRUNC(lnMontop/10);
  lnMontod := lnMontou * 10;
  lnMontot := lnMontop - lnMontod;
  lnDigitoVer := 10 - lnMontot;

  IF(LENGTH(trim(to_char(10-lnMontot)))=2) THEN
    lsNumCtrl := SUBSTR(TRIM(TO_CHAR(10-lnMontot)),LENGTH(TRIM(TO_CHAR(10-lnMontot))),1);
  ELSE
    lsNumCtrl := LPAD(TRIM(TO_CHAR(10-lnMontot)),1);
  END IF;

  lsResultado := lsNumCtrl;

  RETURN lsResultado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VALID_MODUL_PTR: ' || ls_sqlerrm);

END MAE_FN_VALID_MODUL_PTR;


/**************************************************************************
Descripcion       : Inactivar conultoras registradas despues de dos campañas
                    de creadas en el maestro
Fecha Creacion    : 14/05/2014
Fecha Modificacion: 26/01/2016
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Carlos Bazalar
Autor Modificacion: CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_INACT_CLIEN_SINPE
  (psCodigoPais          VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2)
IS

  lnIdCanal              NUMBER;
  lnIdMarca              NUMBER;
  lnOidPais              SEG_PAIS.OID_PAIS%TYPE;
  lnOidPeriodo           CRA_PERIO.OID_PERI%TYPE;
  lsCodPeriodoAnt        SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoAnt        CRA_PERIO.OID_PERI%TYPE;
  lsEstatusClienteSP     VARCHAR2(10);
  lnNumCampanaClienteSP  NUMBER;

  CURSOR c_clientes(voidPeriodo NUMBER, vEstatusClienteSP VARCHAR) IS

     SELECT cda.clie_oid_clie
       FROM mae_clien_datos_adici cda,
            mae_clien_prime_conta cpc,
            mae_clien_tipo_subti ctsu
      WHERE cda.clie_oid_clie = cpc.clie_oid_clie
        AND cpc.clie_oid_clie  = ctsu.clie_oid_clie
        AND cda.ind_acti = '1'
        AND ctsu.ticl_oid_tipo_clie = 2
        AND INSTR(vEstatusClienteSP,cda.esta_oid_esta_clie) > 0
        AND cpc.perd_oid_peri <= voidPeriodo;

  TYPE interfazClientes IS RECORD
  (
   oidCliente                mae_clien_datos_adici.CLIE_OID_CLIE%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecord interfazClientesTab;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnIdCanal    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnIdMarca    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

  -- Obtenemos parametro Estatus a evaluar de Cliente Sin Pedido --
  BEGIN
    SELECT val_para
      INTO lsEstatusClienteSP
      FROM bas_param_pais
     WHERE cod_pais = psCodigoPais
       AND cod_sist = 'MAE'
       AND nom_para = 'estatusClienteSinPedido'
       AND ind_acti =  1;

    EXCEPTION
       WHEN OTHERS THEN
          lsEstatusClienteSP := '0';
  END;

  -- Obtenemos parametro Numero de Campañas a evaluar de Cliente Sin Pedido --
  BEGIN
    SELECT val_para
      INTO lnNumCampanaClienteSP
      FROM bas_param_pais
     WHERE cod_pais = psCodigoPais
       AND cod_sist = 'MAE'
       AND nom_para = 'numCampanaClienteSinPedido'
       AND ind_acti =  1;

    EXCEPTION
       WHEN OTHERS THEN
          lnNumCampanaClienteSP := NULL;
  END;

  -- Recuperamos Campaña segun el parametro de Numero de Campañas --
  lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,lnOidPais, lnIdMarca, lnIdCanal, -lnNumCampanaClienteSP);
  lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCodPeriodoAnt);

    --(1) PROCESAMOS
    OPEN c_clientes(lnOidPeriodoAnt, lsEstatusClienteSP);
    LOOP
       FETCH c_clientes BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FORALL i IN 1..interfazRecord.COUNT

            UPDATE mae_clien_datos_adici x
               SET x.ind_acti = '0',
                   x.fec_ulti_actu = SYSDATE,
                   x.usu_modi = psCodigoUsuario,
                   x.cam_inac = psCodigoPeriodo,
                   x.fec_inac = SYSDATE,
                   x.usu_inac = psCodigoUsuario
             WHERE x.clie_oid_clie = interfazRecord(i).oidCliente;

       END IF;
       EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;
    CLOSE c_clientes;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_INACT_CLIEN_SINPE: ' || ls_sqlerrm);

END MAE_PR_INACT_CLIEN_SINPE;

  /**************************************************************************
  Descripcion       : Activa, actualiza o retira las clasificaciones disponibles
                      en el programa Flexipago
  Fecha Creacion    : 10/06/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Juan Gutiérrez

  ***************************************************************************/
  PROCEDURE MAE_PR_ACTUA_CLASI_FLEXI
    (psCodigoPais               VARCHAR2,
     psCodigoMarca              VARCHAR2,
     psCodigoCanal              VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2)
     IS


     CURSOR c_InvPorInvitar( oidTClasInvita NUMBER ) IS
         SELECT flh.cod_clie,
                mts.oid_clie_tipo_subt
         FROM FLX_CONSU_HABIL_FLEXI flh,
              MAE_CLIEN             mae,
              MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts,
              MAE_TIPO_CLIEN        mtc
         WHERE COD_PERI_FACT = psCodigoPeriodo
         AND IND_HABI = 1
         AND mcda.ind_acti = 1
         AND mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND MAE.OID_CLIE = mcda.clie_oid_clie
         AND mae.cod_clie = flh.cod_clie
         AND mts.clie_oid_clie = mae.oid_clie
         AND mts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
         AND mtc.cod_tipo_clie = '02'
         AND (SELECT count(1) FROM MAE_CLIEN_CLASI MCC
              WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
              AND MCC.TCCL_OID_TIPO_CLASI =  oidTClasInvita ) = 0 ;

     CURSOR c_InvInvitada(oidTClasInvita NUMBER, oidClasPInvitar NUMBER, oidPeriodo NUMBER , oidPeriodoAnt NUMBER ) IS
         SELECT flh.cod_clie,
                mts.oid_clie_tipo_subt
         FROM FLX_CONSU_HABIL_FLEXI flh,
              MAE_CLIEN             mae,
              MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts
         WHERE COD_PERI_FACT =  psCodigoPeriodo
         AND flh.IND_HABI = 1
         AND mcda.ind_acti = 1
         AND mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND mae.cod_clie = flh.cod_clie
         AND MAE.OID_CLIE = mcda.clie_oid_clie
         AND mts.clie_oid_clie = mae.oid_clie
         AND (SELECT count(1) FROM MAE_CLIEN_CLASI MCC
              WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
              AND MCC.TCCL_OID_TIPO_CLASI =  oidTClasInvita
              AND MCC.CLAS_OID_CLAS = oidClasPInvitar
              AND MCC.PERD_OID_PERI = oidPeriodoAnt ) = 1
         AND (SELECT COUNT(SOCA.OID_SOLI_CABE)
                FROM ped_solic_cabec soca,
                     ped_solic_cabec cons,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol
                WHERE 1=1
                 AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND soca.perd_oid_peri = oidperiodo
                 AND soca.grpr_oid_grup_proc = 5
                 AND cons.esso_oid_esta_soli != 4
                 AND tsol.cod_tipo_soli = 'SOC'
                 AND soca.clie_oid_clie = MAE.OID_CLIE ) >= 1 ;

     CURSOR c_InsInscrita(oidTClasInscrita NUMBER ) IS
         SELECT flh.cod_clie,
                mts.oid_clie_tipo_subt
         FROM FLX_CONSU_HABIL_FLEXI flh,
              MAE_CLIEN             mae,
              MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts,
              MAE_TIPO_CLIEN        mtc
         WHERE COD_PERI_FACT =  psCodigoPeriodo
         AND   flh.IND_HABI = 1
         AND   flh.Ind_Acti = 1
         AND   mcda.ind_acti = 1
         AND   mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND   mae.cod_clie = flh.cod_clie
         AND   MAE.OID_CLIE = mcda.clie_oid_clie
         AND   mts.clie_oid_clie = mae.oid_clie
         AND   mts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
         AND   mtc.cod_tipo_clie = '02'
         AND   (SELECT COUNT(1) FROM MAE_CLIEN_CLASI MCC
                WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
                AND MCC.TCCL_OID_TIPO_CLASI =  oidTClasInscrita) = 0;

     CURSOR c_InsInscritaCampa(oidTClasInscrita NUMBER, oidClasInscrita NUMBER, oidPeriodo NUMBER , oidPeriodoAnt NUMBER ) IS
         SELECT flh.cod_clie,
                mts.oid_clie_tipo_subt
         FROM FLX_CONSU_HABIL_FLEXI flh,
              MAE_CLIEN             mae,
               MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts
         WHERE flh.COD_PERI_FACT =  psCodigoPeriodo
         AND flh.IND_HABI = 1
         AND mcda.ind_acti = 1
         AND mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND mae.cod_clie = flh.cod_clie
         AND MAE.OID_CLIE = mcda.clie_oid_clie
         AND mts.clie_oid_clie = mae.oid_clie
         AND (SELECT COUNT(1) FROM MAE_CLIEN_CLASI MCC
              WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
              AND MCC.TCCL_OID_TIPO_CLASI =  oidTClasInscrita
              AND MCC.CLAS_OID_CLAS = oidClasInscrita
              AND MCC.PERD_OID_PERI = oidPeriodoAnt ) = 1
         AND  (SELECT COUNT(SOCA.OID_SOLI_CABE)
                FROM ped_solic_cabec soca,
                     ped_solic_cabec cons,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol
              WHERE 1=1
                 AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND soca.perd_oid_peri = oidPeriodo
                 AND soca.grpr_oid_grup_proc = 5
                 AND cons.esso_oid_esta_soli != 4
                 AND tsol.cod_tipo_soli = 'SOC'
                 AND soca.clie_oid_clie = MAE.OID_CLIE ) >= 1 ;

     CURSOR c_UsosUsuaria(oidTClasUsos NUMBER) IS

         SELECT fgc.cod_clie,
                mts.oid_clie_tipo_subt
         FROM
              FLX_GENER_FINAN_CONSU_FLEXI fgc,
              MAE_CLIEN             mae,
              MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts
         WHERE fgc.COD_PERI =  psCodigoPeriodo
         AND fgc.cod_moti_rech is null
         AND fgc.ind_gene_fina_flex = 1
         AND fgc.ind_acti = 1
         AND fgc.ind_habi = 1
         AND mcda.ind_acti = 1
         AND mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND mae.cod_clie = fgc.cod_clie
         AND MAE.OID_CLIE = mcda.clie_oid_clie
         AND mts.clie_oid_clie = mae.oid_clie
         AND (SELECT count(1) FROM MAE_CLIEN_CLASI MCC
              WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
              AND MCC.TCCL_OID_TIPO_CLASI =  oidTClasUsos) = 0      ;

     CURSOR c_Eliminar(oidTClasInvita NUMBER ,oidTClasInscrita NUMBER,oidTClasUsos NUMBER) IS
       SELECT   flh.cod_clie,
                mts.oid_clie_tipo_subt
         FROM
              FLX_CONSU_HABIL_FLEXI flh,
              MAE_CLIEN             mae,
              MAE_CLIEN_DATOS_ADICI mcda,
              MAE_CLIEN_TIPO_SUBTI  mts,
              MAE_CLIEN_CLASI       mcl
         WHERE flh.COD_PERI_FACT =  psCodigoPeriodo
         AND   flh.ind_habi = 0
         AND   mcda.ind_acti = 1
         AND   mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND   mae.cod_clie = flh.cod_clie
         AND   MAE.OID_CLIE = mcda.clie_oid_clie
         AND   mts.clie_oid_clie = mae.oid_clie
         AND   mts.oid_clie_tipo_subt = mcl.ctsu_oid_clie_tipo_subt
         AND   mcl.tccl_oid_tipo_clasi in (oidTClasInvita,oidTClasInscrita,oidTClasUsos );

     vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
     vnOidPeriodoAnterior    CRA_PERIO.OID_PERI%TYPE;
     vnOidTClasInvita        MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
     vnOidTClasInscrita      MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
     vnOidTClasUsos          MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
     vnOidClasPInvitar       MAE_CLASI.OID_CLAS%TYPE;
     vnOidClasInvitada       MAE_CLASI.OID_CLAS%TYPE;
     vnOidClasInscrita       MAE_CLASI.OID_CLAS%TYPE;
     vnOidClasInsCampa       MAE_CLASI.OID_CLAS%TYPE;
     vnOidClasUsuaria        MAE_CLASI.OID_CLAS%TYPE;
     vsCodClie               MAE_CLIEN.OID_CLIE%TYPE;
     vsOidCliTipoSTipo       MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;

     BEGIN

      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
      vnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais, psCodigoPeriodo, -1));

      SELECT tcc.oid_tipo_clas
      INTO vnOidTClasInvita
      FROM mae_tipo_clasi_clien tcc
          ,gen_i18n_sicc_comun des_tip_cla
      WHERE tcc.oid_tipo_clas    = des_tip_cla.val_oid
       AND des_tip_cla.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
       AND des_tip_cla.val_i18n  = 'Flexipago Invitaciones';

      SELECT tcc.oid_tipo_clas
      INTO vnOidTClasInscrita
      FROM mae_tipo_clasi_clien tcc
          ,gen_i18n_sicc_comun des_tip_cla
      WHERE tcc.oid_tipo_clas    = des_tip_cla.val_oid
       AND des_tip_cla.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
       AND des_tip_cla.val_i18n  = 'Flexipago Inscripciones';

      SELECT tcc.oid_tipo_clas
      INTO vnOidTClasUsos
      FROM mae_tipo_clasi_clien tcc
          ,gen_i18n_sicc_comun des_tip_cla
      WHERE  tcc.oid_tipo_clas    = des_tip_cla.val_oid
       AND des_tip_cla.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
       AND des_tip_cla.val_i18n  = 'Flexipago Usos';

      SELECT c.oid_clas
        INTO vnOidClasInvitada
        FROM mae_clasi c
            ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   =  vnOidTClasInvita
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Invitada';

      SELECT c.oid_clas
        INTO vnOidClasPInvitar
        FROM mae_clasi c
            ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   = vnOidTClasInvita
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Por Invitar';

      SELECT c.oid_clas
        INTO vnOidClasInscrita
        FROM mae_clasi c
            ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   =  vnOidTClasInscrita
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Inscrita Nueva';

      SELECT c.oid_clas
        INTO vnOidClasInsCampa
        FROM mae_clasi c
            ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   =   vnOidTClasInscrita
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Inscrita Antigua';

      SELECT c.oid_clas
        INTO vnOidClasUsuaria
        FROM mae_clasi c
            ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   = vnOidTClasUsos
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Usuaria';

      OPEN c_InvPorInvitar(vnOidTClasInvita);
        LOOP FETCH c_InvPorInvitar INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_InvPorInvitar%NOTFOUND;
            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasPInvitar  , vnOidPeriodo ,vnOidTClasInvita , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_InvPorInvitar;

      OPEN c_InvInvitada(vnOidTClasInvita, vnOidClasPInvitar, vnOidPeriodo,vnOidPeriodoAnterior );
        LOOP FETCH c_InvInvitada INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_InvInvitada%NOTFOUND;
            DELETE FROM MAE_CLIEN_CLASI
            WHERE CTSU_OID_CLIE_TIPO_SUBT = vsOidCliTipoSTipo
            AND CLAS_OID_CLAS = vnOidClasPInvitar;

            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasInvitada , vnOidPeriodo ,vnOidTClasInvita , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_InvInvitada;

      OPEN c_InsInscrita(vnOidTClasInscrita);
        LOOP FETCH c_InsInscrita INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_InsInscrita%NOTFOUND;

            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasInscrita , vnOidPeriodo ,vnOidTClasInscrita , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_InsInscrita;

      OPEN c_InsInscritaCampa(vnOidTClasInscrita, vnOidClasInscrita, vnOidPeriodo,vnOidPeriodoAnterior );
        LOOP FETCH c_InsInscritaCampa INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_InsInscritaCampa%NOTFOUND;

            DELETE FROM MAE_CLIEN_CLASI
            WHERE CTSU_OID_CLIE_TIPO_SUBT = vsOidCliTipoSTipo
            AND CLAS_OID_CLAS = vnOidClasInscrita;

            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasInsCampa , vnOidPeriodo ,vnOidTClasInscrita , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_InsInscritaCampa;

      OPEN c_UsosUsuaria(vnOidTClasUsos);
        LOOP FETCH c_UsosUsuaria INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_UsosUsuaria%NOTFOUND;
            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasUsuaria  , vnOidPeriodo ,vnOidTClasUsos  , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_UsosUsuaria;

       OPEN c_Eliminar(vnOidTClasInvita,vnOidTClasInscrita,vnOidTClasUsos);
        LOOP FETCH c_Eliminar INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_Eliminar%NOTFOUND;
            DELETE FROM MAE_CLIEN_CLASI
            WHERE CTSU_OID_CLIE_TIPO_SUBT = vsOidCliTipoSTipo
            AND TCCL_OID_TIPO_CLASI IN  (vnOidTClasInvita,vnOidTClasInscrita,vnOidTClasUsos);

        END LOOP;
      CLOSE c_Eliminar;

       RETURN;
     END MAE_PR_ACTUA_CLASI_FLEXI;

/***************************************************************************
Descripcion       : Valida Carga de Masiva Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER)
IS

CURSOR c_clientes IS
SELECT NUM_FILA, COD_CLIE
  FROM MAE_CARGA_IMPRE_PADOC
 WHERE NUM_CARG = pnNumeroCarga;

  TYPE interfazClientes IS RECORD
  (
    numeroFila         MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE,
    codigoCliente      MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnNumeroFila               MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE;
  lsCodigoMotivo             MAE_MOTIV_CARGA_IMPRE.COD_MOTI%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;
  lnOcurrencias              NUMBER(12);

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;

        lsCodigoMotivo := NULL;
        lnOidCliente := NULL;

        --(1), Validamos si existe el Codigo de Cliente
        BEGIN
          SELECT OID_CLIE
            INTO lnOidCliente
            FROM MAE_CLIEN
           WHERE PAIS_OID_PAIS = lnOidPais
             AND COD_CLIE = lsCodigoCliente;
        EXCEPTION
          WHEN OTHERS THEN NULL;
        END;

        IF(lnOidCliente IS NULL) THEN
          lsCodigoMotivo := '01';
        END IF;

        UPDATE MAE_CARGA_IMPRE_PADOC
           SET IND_VALI = DECODE(lsCodigoMotivo, NULL, 1, 0),
               COD_MOTI = lsCodigoMotivo
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_VALID_CARGA_IMPRE: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_VALID_CARGA_IMPRE;


/***************************************************************************
Descripcion       : Inserta Carga Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_INSER_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psOidTipoCliente           VARCHAR2,
   psOidSubTipoCliente        VARCHAR2,
   psOidTipoClasificacion     VARCHAR2,
   psOidClasificacion         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psCodigoUsuario            VARCHAR2,
   psNumeroRegistros          OUT VARCHAR2)
IS
  lnNumeroRegistros NUMBER;
  lnOidTipoCliente        NUMBER;
  lnOidSubTipoCliente     NUMBER;
  lnOidTipoClasificacion  NUMBER;
  lnOidClasificacion      NUMBER;
BEGIN

  IF(psOidTipoCliente = 'TODOS') THEN
    lnOidTipoCliente := -1;
  ELSE
    lnOidTipoCliente := TO_NUMBER(psOidTipoCliente);
  END IF;
  IF(psOidSubTipoCliente = 'TODOS') THEN
    lnOidSubTipoCliente := -1;
  ELSE
    lnOidSubTipoCliente := TO_NUMBER(psOidSubTipoCliente);
  END IF;
  IF(psOidTipoClasificacion = 'TODOS') THEN
    lnOidTipoClasificacion := -1;
  ELSE
    lnOidTipoClasificacion := TO_NUMBER(psOidTipoClasificacion);
  END IF;
  IF(psOidClasificacion = 'TODOS') THEN
    lnOidClasificacion := -1;
  ELSE
    lnOidClasificacion := TO_NUMBER(psOidClasificacion);
  END IF;

  --(1) PROCESAMOS A LOS CLIENTES
  INSERT INTO MAE_CARGA_IMPRE_PADOC (
    NUM_CARG, NUM_FILA, COD_CLIE, FEC_CARG, COD_USUA)
  SELECT pnNumeroCarga,
         rownum,
         cli.COD_CLIE,
         SYSDATE,
         psCodigoUsuario
    FROM MAE_CLIEN cli,
         MAE_CLIEN_UNIDA_ADMIN adm,
         ZON_TERRI_ADMIN tad,
         ZON_SECCI sec,
         ZON_ZONA zon,
         ZON_REGIO reg,
         MAE_CLIEN_TIPO_SUBTI cts,
         MAE_CLIEN_CLASI cla
   WHERE cli.Oid_Clie = adm.clie_oid_clie
     AND adm.ind_acti = 1
     AND adm.ztad_oid_terr_admi = tad.oid_terr_admi
     AND tad.zscc_oid_secc = sec.oid_secc
     AND sec.zzon_oid_zona = zon.oid_zona
     AND zon.zorg_oid_regi = reg.oid_regi
     AND (psCodigoRegion = 'TODOS' OR reg.cod_regi = psCodigoRegion)
     AND (psCodigoZona = 'TODOS' OR zon.cod_zona = psCodigoZona)
     AND cli.oid_clie = cts.clie_oid_clie
     AND cts.oid_clie_tipo_subt = cla.ctsu_oid_clie_tipo_subt
     AND ((lnOidTipoCliente = -1) OR (cts.ticl_oid_tipo_clie = lnOidTipoCliente))
     AND ((lnOidSubTipoCliente = -1) OR cts.sbti_oid_subt_clie = lnOidSubTipoCliente)
     AND ((lnOidTipoClasificacion = -1) OR cla.tccl_oid_tipo_clasi = lnOidTipoClasificacion)
     AND ((lnOidClasificacion = -1) OR cla.clas_oid_clas = lnOidClasificacion);

  --Obtenemos la cantidad de registros cargados
  SELECT COUNT(1)
    INTO lnNumeroRegistros
    FROM MAE_CARGA_IMPRE_PADOC
   WHERE NUM_CARG = pnNumeroCarga;

  psNumeroRegistros := TO_CHAR(lnNumeroRegistros);

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_INSER_CARGA_IMPRE: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_INSER_CARGA_IMPRE;


/***************************************************************************
Descripcion       : Actualiza Carga Impresion de Paquete Documentario
Fecha Creacion    : 23/07/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_IMPRE
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psIndicadorPaqDoc          VARCHAR2,
   psCodigoUsuario            VARCHAR2)
IS

CURSOR c_clientes IS
SELECT NUM_FILA, COD_CLIE
  FROM MAE_CARGA_IMPRE_PADOC
 WHERE NUM_CARG = pnNumeroCarga
   AND IND_VALI = 1;

  TYPE interfazClientes IS RECORD
  (
    numeroFila             MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE,
    codigoCliente          MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                 SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                 SEG_CANAL.OID_CANA%TYPE;

  lnOidPeriodo               CRA_PERIO.OID_PERI%TYPE;
  lnNumeroFila               MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;


BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;
        lnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazRecordN(x).codigoCliente);

        --Actualizamos el indicador de Impresion de Paquete Documentario
        UPDATE MAE_CLIEN_DATOS_ADICI
           SET IND_IMPR_PDOC = DECODE(psIndicadorPaqDoc, '1', null, 'N'),
               FEC_ULTI_ACTU = SYSDATE
         WHERE CLIE_OID_CLIE = lnOidCliente;

        --Actualiza la fila procesada
        UPDATE MAE_CARGA_IMPRE_PADOC
           SET FEC_PROC = SYSDATE
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CARGA_IMPRE: ('|| lsCodigoCliente || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_CARGA_IMPRE;


/***************************************************************************
Descripcion       : Valida Carga de Masiva Nivel de Riesgo
Fecha Creacion    : 28/08/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_NIVEL_RIESG
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER)
IS

CURSOR c_clientes IS
SELECT NUM_FILA, COD_CLIE
  FROM MAE_CARGA_NIVEL_RIESG
 WHERE NUM_CARG = pnNumeroCarga;

  TYPE interfazClientes IS RECORD
  (
    numeroFila         MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE,
    codigoCliente      MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnNumeroFila               MAE_CARGA_NIVEL_RIESG.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_NIVEL_RIESG.COD_CLIE%TYPE;
  lsCodigoMotivo             MAE_MOTIV_CARGA_IMPRE.COD_MOTI%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;
  lnOidNivelRiesgo           MAE_CLIEN_DATOS_ADICI.NIRI_OID_NIVE_RIES%TYPE;
  lsCodNivelRiesgo           CAR_NIVEL_RIESG.COD_NIVE_RIES%TYPE;
  lnOcurrencias              NUMBER(12);

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;

        lsCodigoMotivo := NULL;
        lnOidCliente := NULL;

        --(1), Validamos si existe el Codigo de Cliente
        BEGIN
          SELECT OID_CLIE
            INTO lnOidCliente
            FROM MAE_CLIEN
           WHERE PAIS_OID_PAIS = lnOidPais
             AND COD_CLIE = lsCodigoCliente;
        EXCEPTION
          WHEN OTHERS THEN NULL;
        END;

        IF(lnOidCliente IS NULL) THEN
          lsCodigoMotivo := '01';

        ELSE
          --(2), Validamos Nivel Riesgo
          SELECT adi.NIRI_OID_NIVE_RIES, car.COD_NIVE_RIES
            INTO lnOidNivelRiesgo, lsCodNivelRiesgo
            FROM MAE_CLIEN_DATOS_ADICI adi, CAR_NIVEL_RIESG car
           WHERE adi.CLIE_OID_CLIE = lnOidCliente
             AND adi.NIRI_OID_NIVE_RIES = car.OID_NIVE_RIES(+);

          IF((lsCodNivelRiesgo = NIVRIE_NUEVAS_ESPECIALES) OR
            (lsCodNivelRiesgo = NIVRIE_PRIMER_PEDIDO_ESPECIAL) OR
            (lsCodNivelRiesgo = NIVRIE_SEGUNDO_PEDIDO_ESPECIAL) OR
            (lsCodNivelRiesgo = NIVRIE_TERCER_PEDIDO_ESPECIAL) OR
            (lsCodNivelRiesgo = NIVRIE_CUARTO_PEDIDO_ESPECIAL)) THEN

            lsCodigoMotivo := '02';

          END IF;

        END IF;

        UPDATE MAE_CARGA_NIVEL_RIESG
           SET IND_VALI = DECODE(lsCodigoMotivo, NULL, 1, 0),
               COD_NIVE_RIES_ACTU = lsCodNivelRiesgo,
               COD_NIVE_RIES_NUEV = DECODE(lsCodNivelRiesgo,NIVRIE_NUEVAS, NIVRIE_NUEVAS_ESPECIALES,
                                                       NIVRIE_PRIMER_PEDIDO, NIVRIE_PRIMER_PEDIDO_ESPECIAL,
                                                       NIVRIE_SEGUNDO_PEDIDO, NIVRIE_SEGUNDO_PEDIDO_ESPECIAL,
                                                       NIVRIE_TERCER_PEDIDO, NIVRIE_TERCER_PEDIDO_ESPECIAL,
                                                       NIVRIE_CUARTO_PEDIDO, NIVRIE_CUARTO_PEDIDO_ESPECIAL,
                                                       lsCodNivelRiesgo),
               COD_MOTI = lsCodigoMotivo
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_VALID_CARGA_NIVEL_RIESG: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_VALID_CARGA_NIVEL_RIESG;


/***************************************************************************
Descripcion       : Actualiza Carga Nivel Riesgo
Fecha Creacion    : 28/08/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_NIVEL_RIESG
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psCodigoUsuario            VARCHAR2)
IS

CURSOR c_clientes IS
SELECT NUM_FILA,
       COD_CLIE,
       COD_NIVE_RIES_ACTU,
       COD_NIVE_RIES_NUEV,
       (SELECT OID_NIVE_RIES FROM CAR_NIVEL_RIESG WHERE COD_NIVE_RIES = COD_NIVE_RIES_ACTU) OID_NIVE_RIES_ACTU,
       (SELECT OID_NIVE_RIES FROM CAR_NIVEL_RIESG WHERE COD_NIVE_RIES = COD_NIVE_RIES_NUEV) OID_NIVE_RIES_NUEV
  FROM MAE_CARGA_NIVEL_RIESG
 WHERE NUM_CARG = pnNumeroCarga
   AND IND_VALI = 1;

  TYPE interfazClientes IS RECORD
  (
    numeroFila             MAE_CARGA_NIVEL_RIESG.NUM_FILA%TYPE,
    codigoCliente          MAE_CARGA_NIVEL_RIESG.COD_CLIE%TYPE,
    codNivelRiesgoAct      MAE_CARGA_NIVEL_RIESG.COD_NIVE_RIES_ACTU%TYPE,
    codNivelRiesgoNue      MAE_CARGA_NIVEL_RIESG.COD_NIVE_RIES_NUEV%TYPE,
    oidNivelRiesgoAct      CAR_NIVEL_RIESG.OID_NIVE_RIES%TYPE,
    oidNivelRiesgoNue      CAR_NIVEL_RIESG.OID_NIVE_RIES%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                 SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                 SEG_CANAL.OID_CANA%TYPE;

  lnOidPeriodo               CRA_PERIO.OID_PERI%TYPE;
  lnNumeroFila               MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;


BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;
        lnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazRecordN(x).codigoCliente);

        --Actualizamos el Nivel de Riesgo
        UPDATE MAE_CLIEN_DATOS_ADICI
           SET NIRI_OID_NIVE_RIES = interfazRecordN(x).oidNivelRiesgoNue,
               FEC_ULTI_ACTU = SYSDATE
         WHERE CLIE_OID_CLIE = lnOidCliente;

        --Obtenemos el OidPeriodo
        SELECT PERD_OID_PERI_NIVE_RIES
          INTO lnOidPeriodo
          FROM MAE_CLIEN_DATOS_ADICI
         WHERE CLIE_OID_CLIE = lnOidCliente;

        --Insertamos en el Historico de Nivel Riesgo del Cliente
        INSERT INTO MAE_CLIEN_NIVEL_RIESG
          (COD_PAIS,
           COD_CLIE,
           CLIE_OID_CLIE,
           OID_NIVE_RIES_ANTE,
           COD_NIVE_RIES_ANTE,
           OID_NIVE_RIES_ACTU,
           COD_NIVE_RIES_ACTU,
           USU_CREA,
           FEC_CREA,
           OID_PERI_NIVE_RIES)
        VALUES
          (psCodigoPais,
           interfazRecordN(x).codigoCliente,
           lnOidCliente,
           interfazRecordN(x).oidNivelRiesgoAct,
           interfazRecordN(x).codNivelRiesgoAct,
           interfazRecordN(x).oidNivelRiesgoNue,
           interfazRecordN(x).codNivelRiesgoNue,
           psCodigoUsuario,
           SYSDATE,
           lnOidPeriodo);

        --Actualiza la fila procesada
        UPDATE MAE_CARGA_IMPRE_PADOC
           SET FEC_PROC = SYSDATE
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CARGA_NIVEL_RIESG: ('|| lsCodigoCliente || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_CARGA_NIVEL_RIESG;


PROCEDURE MAE_PR_INSER_DIREC_ENT(psUsuario in VARCHAR2,psCliente in VARCHAR2,psDireccion in VARCHAR2,psReferencia in VARCHAR2,psTipoDireccion in VARCHAR2)
AS
lnValida NUMBER;
lnDireccion VARCHAR2(300);
lnReferencia VARCHAR2(300);
lnCLIE_OID_CLIE VARCHAR2(20);
lnTERR_OID_TERR VARCHAR2(20);
lnCOD_UNID_GEOG VARCHAR2(100);
lnTIVI_OID_TIPO_VIA VARCHAR2(20);
BEGIN
      SELECT COUNT(*) INTO lnValida FROM MAE_CLIEN_DIREC
      Where CLIE_OID_CLIE IN (SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE =psCliente)
      and ind_elim=0
      and tidc_oid_tipo_dire =(SELECT td.oid_tipo_dire
                                        FROM MAE_TIPO_DIREC td,gen_i18n_sicc_comun x
                                        WHERE td.oid_tipo_dire = x.val_oid AND
                                              x.attr_enti = 'MAE_TIPO_DIREC' AND
                                              td.cod_tipo_dire=psTipoDireccion);

        IF (lnValida = 0) THEN
          SELECT SUBSTR(TRIM(psDireccion),0,(select max(data_length) as cantidad from all_tab_columns where table_name = 'MAE_CLIEN_DIREC' and column_name='VAL_NOMB_VIA'))  INTO lnDireccion FROM DUAL;
          SELECT SUBSTR(TRIM(psReferencia),0,(select max(data_length) as cantidad from all_tab_columns where table_name = 'MAE_CLIEN_DIREC' and column_name='VAL_OBSE')) INTO lnReferencia FROM DUAL;

          SELECT DISTINCT CLIE_OID_CLIE INTO lnCLIE_OID_CLIE FROM MAE_CLIEN_DIREC WHERE CLIE_OID_CLIE IN (SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE = psCliente)AND ind_elim=0;
          SELECT DISTINCT TERR_OID_TERR INTO lnTERR_OID_TERR FROM MAE_CLIEN_DIREC WHERE CLIE_OID_CLIE IN (SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE = psCliente)AND ind_elim=0;
          SELECT DISTINCT COD_UNID_GEOG INTO lnCOD_UNID_GEOG FROM MAE_CLIEN_DIREC WHERE CLIE_OID_CLIE IN (SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE = psCliente)AND ind_elim=0;
          SELECT DISTINCT TIVI_OID_TIPO_VIA INTO lnTIVI_OID_TIPO_VIA FROM MAE_CLIEN_DIREC WHERE CLIE_OID_CLIE IN (SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE = psCliente) AND ind_elim=0;

          INSERT INTO MAE_CLIEN_DIREC (OID_CLIE_DIRE,CLIE_OID_CLIE,TERR_OID_TERR, COD_UNID_GEOG, TIVI_OID_TIPO_VIA,TIDC_OID_TIPO_DIRE,VAL_NOMB_VIA,VAL_OBSE,FEC_CREA,USU_CREA,IND_DIRE_PPAL,IND_ELIM)
                                VALUES(MAE_CLDI_SEQ.NEXTVAL,lnCLIE_OID_CLIE,lnTERR_OID_TERR,lnCOD_UNID_GEOG,lnTIVI_OID_TIPO_VIA,'2007',psDireccion,psReferencia,SYSDATE,psUsuario,0,0);

        ELSE
           update  mae_clien_direc set usu_modi=psUsuario,
                                       fec_ulti_actu=SYSDATE,
				    val_nomb_via=SUBSTR(TRIM(psDireccion),0,(select max(data_length) as cantidad from all_tab_columns where table_name = 'MAE_CLIEN_DIREC' and column_name='VAL_NOMB_VIA')),
				    val_obse=SUBSTR(TRIM(psReferencia),0,(select max(data_length) as cantidad from all_tab_columns where table_name = 'MAE_CLIEN_DIREC' and column_name='VAL_OBSE'))
				   WHERE CLIE_OID_CLIE =(SELECT OID_CLIE  FROM MAE_CLIEN WHERE COD_CLIE =psCliente) AND TIDC_OID_TIPO_DIRE='2007' AND ind_elim=0;

        END IF;


END MAE_PR_INSER_DIREC_ENT;



PROCEDURE MAE_PR_INSER_CLAS(
  psTipoClasif in VARCHAR2,
  psCodClas in VARCHAR2,
  psDescripcion in VARCHAR2,
  psindicador IN VARCHAR2,
  psindHiper VARCHAR2,
  psindIVR   VARCHAR2)
AS
lnRegistro NUMBER;
BEGIN

INSERT INTO MAE_CLASI (OID_CLAS,TCCL_OID_TIPO_CLAS,COD_CLAS,IND_ACTI, IND_HIPE, IND_IVR)
VALUES(MAE_CLAS_SEQ.NEXTVAL,psTipoClasif,psCodClas,psindicador, psindHiper, psindIVR);

select MAE_CLAS_SEQ.Currval into lnRegistro from dual;

INSERT INTO GEN_I18N_SICC_COMUN (OID_I18N,ATTR_ENTI,ATTR_NUM_ATRI,IDIO_OID_IDIO,VAL_I18N,VAL_OID)
            VALUES(gen_i18n_seq.nextval,'MAE_CLASI',1,1,psDescripcion,lnRegistro);
END MAE_PR_INSER_CLAS;


PROCEDURE MAE_PR_INSER_TIPO_CLAS(
  psSubTipo in VARCHAR2,
  psTipoClas in VARCHAR2,
  psPais in VARCHAR2,
  psDescripcion in VARCHAR2,
  psindicador IN VARCHAR2, 
  psindHiper IN VARCHAR2)
AS
lnRegistro NUMBER;
BEGIN

INSERT INTO MAE_TIPO_CLASI_CLIEN 
(OID_TIPO_CLAS,
SBTI_OID_SUBT_CLIE,
COD_TIPO_CLAS,
IND_TIPO_CLAS_PAIS,
IND_ACTI, 
IND_HIPE)
VALUES(MAE_TCCL_SEQ.NEXTVAL,psSubTipo,psTipoClas,psPais,psindicador, TO_NUMBER(psindHiper));

select MAE_TCCL_SEQ.Currval into lnRegistro from dual;

INSERT INTO GEN_I18N_SICC_COMUN (OID_I18N,ATTR_ENTI,ATTR_NUM_ATRI,IDIO_OID_IDIO,VAL_I18N,VAL_OID)
            VALUES(gen_i18n_seq.nextval,'MAE_TIPO_CLASI_CLIEN',1,1,psDescripcion,lnRegistro);

END MAE_PR_INSER_TIPO_CLAS;

/***************************************************************************
Descripcion       : Permite actualizar información Básica al cierre de campaña.
Fecha Creacion    : 09/12/2014
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_INFOR_BASIC(
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2)
IS

    lnOidPeri NUMBER;

    lnIngresos NUMBER(12, 2) := 0;
    lnReingresos NUMBER(12, 2) := 0;
    lnEgresos NUMBER(12, 2) := 0;
    lnActivasFinales NUMBER(12, 2) := 0;
    lnCantLideres NUMBER(12, 2) := 0;
    lnCantGerentesZona NUMBER(12, 2) := 0;
    lnCantGerentesRegion NUMBER(12, 2) := 0;
    lnGananciaLideres NUMBER(12, 2) := 0;
    lnComisionGZ NUMBER(12, 2) := 0;
    lnCantidadRecom NUMBER := 0;

BEGIN

    lnOidPeri := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (psCodigoPeriodo);

    -- Obtener las Activas Finales, Ingresos, reingresos, egresos de la campaña de la Fuente de Ventas Real
    SELECT
    NVL(SUM(FV.NUM_INGR), 0) INGR, -- Proceso 01
    NVL(SUM(FV.NUM_REIN), 0) REIN, -- Proceso 02
    NVL(SUM(FV.NUM_EGRE), 0) EGRE, -- Proceso 03
    NVL(SUM(FV.NUM_ACTI_FINA), 0) FINA -- Proceso 04
    INTO
    lnIngresos,
    lnReingresos,
    lnEgresos,
    lnActivasFinales
    FROM INT_FUENT_VENTAS_REAL FV
    WHERE FV.PERD_OID_PERI = lnOidPeri;

    -- Cantidad de Lideres por campaña - Proceso 05
    SELECT COUNT(*)
    INTO lnCantLideres
    FROM ZON_HISTO_GEREN zh
    WHERE zh.cod_secc is not null
    AND lnOidPeri >= zh.perd_oid_peri_desd
    AND ( lnOidPeri <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

    -- Cantidad Gerentes de Zona  por campaña - Proceso 06
    SELECT COUNT(*)
    INTO lnCantGerentesZona
    FROM ZON_HISTO_GEREN zh
    WHERE zh.cod_secc is  null AND zh.cod_zona is not null
    AND lnOidPeri >= zh.perd_oid_peri_desd
    AND ( lnOidPeri <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

    -- Cantidad Gerentes de Región  por campaña - Proceso 07
    SELECT COUNT(*)
    INTO lnCantGerentesRegion
    FROM ZON_HISTO_GEREN zh
    WHERE zh.cod_secc is  null AND zh.cod_zona is  null AND zh.cod_regi is not null
    AND lnOidPeri >= zh.perd_oid_peri_desd
    AND ( lnOidPeri <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

    -- Ganancia de líderes  por campaña - Proceso 08
    SELECT NVL(SUM(LG.MON_GANA), 0)
    INTO lnGananciaLideres
    FROM LEC_TIPO_GANAN TP, LEC_LIDER_GANAN LG
    WHERE TP.LTPA_COD_TIPO_PAGO = '01'
    AND TP.COD_TIPO_GANA = LG.LTGA_COD_TIPO_GANA
    AND LG.CAM_REFE = psCodigoPeriodo;

    -- Comisión de Gerentes de Zona por campaña - Proceso 09
    SELECT NVL(SUM(NVL(CO.IMP_COMI_TRA1, 0) + NVL(CO.IMP_COMI_TRA2, 0)), 0)
    INTO lnComisionGZ
    FROM COM_COMIS_PERIO_CALCU_ZONA CO
    WHERE CO.PERI_COD_PERI = psCodigoPeriodo;

    -- Cantidad de Recomendaciones por campaña - Proceso 10
    SELECT COUNT(*)
    INTO lnCantidadRecom
    FROM INC_CLIEN_RECDO RD
    WHERE RD.PERD_OID_PERI = lnOidPeri;

    -- Actualizamos los valores de cada uno de los tipos
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '01', lnIngresos, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '02', lnReingresos, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '03', lnEgresos, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '04', lnActivasFinales, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '05', lnCantLideres, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '06', lnCantGerentesZona, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '07', lnCantGerentesRegion, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '08', lnGananciaLideres, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '09', lnComisionGZ, psCodigoUsuario);
    MAE_PR_ACTUA_INFOR_BASIC_TIPO (psCodigoPeriodo, '10', lnCantidadRecom, psCodigoUsuario);

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_INFOR_BASIC: ('|| psCodigoPeriodo || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_INFOR_BASIC;


/***************************************************************************
Descripcion       : Permite actualizar información Básica Por cada uno de los tipos
                    al cierre de campaña.
Fecha Creacion    : 09/12/2014
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_INFOR_BASIC_TIPO(
     psCodigoPeriodo            VARCHAR2,
     psCodigoTipo               VARCHAR2,
     pnValor                    NUMBER,
     psCodigoUsuario            VARCHAR2)
IS

    lnCount NUMBER;

BEGIN

    SELECT COUNT(*)
    INTO lnCount
    FROM MAE_INFOR_BASIC_CLIEN
    WHERE VAL_ANIO = SUBSTR(psCodigoPeriodo, 1, 4)
    AND INBP_COD_PROC = psCodigoTipo;

    IF lnCount = 0 THEN
        -- INSERTAR

        EXECUTE IMMEDIATE
        'INSERT INTO MAE_INFOR_BASIC_CLIEN(VAL_ANIO, INBP_COD_PROC, VAL_DATO_'|| TRIM(TO_CHAR(TO_NUMBER(SUBSTR(psCodigoPeriodo, 5, 2)))) ||', USU_CREA, FEC_CREA, IND_ACTI) '||
        'VALUES(SUBSTR('''|| psCodigoPeriodo || ''', 1, 4), '''|| psCodigoTipo ||''', ' || TO_CHAR(pnValor, '9999999999.99') ||', '''|| psCodigoUsuario || ''', SYSDATE, ''1'')';

    ELSE
        -- ACTUALIZAR
        EXECUTE IMMEDIATE
        'UPDATE MAE_INFOR_BASIC_CLIEN SET VAL_DATO_'|| TRIM(TO_CHAR(TO_NUMBER(SUBSTR(psCodigoPeriodo, 5, 2)))) ||' = '|| TO_CHAR(pnValor, '9999999999.99')|| ',
         USU_MODI = '''|| psCodigoUsuario || ''',
         FEC_MODI = SYSDATE
         WHERE VAL_ANIO = SUBSTR('''|| psCodigoPeriodo || ''', 1, 4)
         AND INBP_COD_PROC = '''|| psCodigoTipo ||''' ';

    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_INFOR_BASIC_TIPO: ('|| psCodigoPeriodo || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_INFOR_BASIC_TIPO;

/***************************************************************************
Descripcion       : Bloquea masivamente a todas las consultoras que han
                    facturado su primer pedido con el tipo de bloqueo
                    ¿Documentos de Contrato en Verificacion¿.
Fecha Creacion    : 21/04/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE MAE_PR_BLOQU_CONSU_PRIPE
    (psCodigoPais               VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2,
     psFechaFacturacion         VARCHAR2)
IS
  lnOidTipoBloqueo          MAE_TIPO_BLOQU.OID_TIPO_BLOQ%TYPE;
  lnMotivoBloqueo           VARCHAR2(50);
  lnCodArea                 NUMBER(10);

CURSOR c_clientes IS

SELECT
       MC.OID_CLIE,
       MC.COD_CLIE
  FROM ped_solic_cabec soca,
       ped_solic_cabec cons,
       ped_tipo_solic_pais tspa,
       ped_tipo_solic tsol,
       MAE_CLIEN_DATOS_ADICI MCDA,
       MAE_CLIEN MC
 WHERE 1=1
   AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
   AND soca.clie_oid_clie = MCDA.CLIE_OID_CLIE
   AND soca.fec_fact IS NOT NULL
   AND soca.grpr_oid_grup_proc = 5
   AND cons.esso_oid_esta_soli != 4
   AND tsol.cod_tipo_soli = 'SOC'
   AND MCDA.CLIE_OID_CLIE = MC.OID_CLIE
   AND MCDA.ESTA_OID_ESTA_CLIE = 1
   AND SOCA.PERD_OID_PERI = (SELECT oid_peri FROM cra_perio WHERE val_nomb_peri LIKE '%'||psCodigoPeriodo||'%')
   AND TRUNC(soca.FEC_FACT) = to_date(psFechaFacturacion,'dd/mm/yyyy')
   AND mc.oid_clie NOT IN (SELECT MCB.CLIE_OID_CLIE FROM  MAE_CLIEN_BLOQU MCB WHERE MCB.FEC_DESB IS NULL AND MCB.TIBQ_OID_TIPO_BLOQ = lnOidTipoBloqueo);

  TYPE interfazClientes IS RECORD
  (
    oidCliente          MAE_CLIEN.OID_CLIE%TYPE,
    codigoCliente       MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

BEGIN

  SELECT MTB.OID_TIPO_BLOQ, val_i18n
  INTO lnOidTipoBloqueo, lnMotivoBloqueo
  FROM MAE_TIPO_BLOQU MTB, gen_i18n_sicc_comun
  WHERE MTB.COD_TIPO_BLOQ = 'CV'
  AND attr_enti = 'MAE_TIPO_BLOQU'
  AND val_oid = MTB.OID_TIPO_BLOQ;

  BEGIN
    SELECT COD_AREA
    INTO lnCodArea
    FROM MAE_AREA_NEGOC
    WHERE DES_AREA_NEGO LIKE '%Primeros Pedidos%';
  EXCEPTION WHEN OTHERS THEN
       lnCodArea := 9;
  END;

 --(1) PROCESAMOS A LOS CLIENTES

  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_TMP_BLOQ_ERROR';

  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
         BEGIN
          INSERT INTO MAE_CLIEN_BLOQU(
              OID_BLOQ,
              CLIE_OID_CLIE,
              TIBQ_OID_TIPO_BLOQ,
              FEC_BLOQ,
              VAL_MOTI_BLOQ,
              VAL_USUA_BLOQ,
              OBS_BLOQ,
              MAAB_OID_VALO_ACCI_BLOQ,
              ARNE_COD_PAIS,
              ARNE_COD_AREA
              )
          VALUES(
              mae_clbl_seq.NEXTVAL,
              interfazRecordN(x).oidCliente,
              lnOidTipoBloqueo,
              trunc(SYSDATE),
              lnMotivoBloqueo,
              psCodigoUsuario,
              'DOCUMENTOS DE CONTRATO EN VERIFICACION',
              GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_VACC_BLOQ('A'),
              psCodigoPais,
              lnCodArea
              );
         EXCEPTION WHEN OTHERS THEN
              ls_sqlerrm := substr(sqlerrm,1,200);
              INSERT INTO REC_GTT_DETAL_INGRE_ATEN_MAS(COD_CLIE) VALUES (interfazRecordN(x).codigoCliente);

              INSERT INTO MAE_TMP_BLOQ_ERROR VALUES (psCodigoPais,
                                                     interfazRecordN(x).codigoCliente,
                                                     interfazRecordN(x).oidCliente,
                                                     psCodigoPeriodo,
                                                     ls_sqlerrm,
                                                     psCodigoUsuario,
                                                     sysdate );
         END;
      END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_BLOQU_CONSU_PRIPE: '||ls_sqlerrm);
END MAE_PR_BLOQU_CONSU_PRIPE;



/**************************************************************************
Descripcion       : Validar tipo documento NIT
Fecha Creacion    : 27/05/2015
Parametros Entrada:
  psNumDoc        :     Número Documento
Autor             : Juan Gutiérrez
***************************************************************************/
FUNCTION MAE_FN_VALID_TDOC_NIT
  (psNumDoc        VARCHAR2
  )
  RETURN NUMBER
  IS

  wNumberOne NUMBER;
  wSum NUMBER := 0;
  wDV NUMBER;
  wLength NUMBER;
BEGIN
  wLength := INSTR(psNumDoc,'-');

  IF wLength = 0 THEN
     wLength := LENGTH(psNumDoc);
  END IF;

  FOR i IN 2..wLength LOOP --LENGTH(psNumDoc) LOOP
      wNumberOne := SUBSTR(psNumDoc,i-1,1) * wLength;
      wSum := wSum + wNumberOne ;
      wLength := wLength - 1;
  END LOOP; /*i IN 2..LENGTH(iNit) LOOP*/

  wDV := (11-(wSum MOD 11)) MOD 11;

  IF (((wDV = 10) AND (UPPER(SUBSTR(psNumDoc,LENGTH(psNumDoc), 1)) = 'K')))
  OR (to_char(wDV) = SUBSTR(psNumDoc,LENGTH(psNumDoc))) THEN
     RETURN 1;
  ELSE
     RETURN 0;
  END IF; /*(((wDV = 10) AND (SUBSTR(iNit,LENGTH(iNit), 1) = 'K')))*/

END MAE_FN_VALID_TDOC_NIT;


END MAE_PKG_PROCE_CLIEN;
/
