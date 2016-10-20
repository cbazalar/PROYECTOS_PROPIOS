CREATE OR REPLACE PACKAGE "MSG_PKG_PROCE_MENSA" IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  /* Declaracion de constantes */
   TIPO_RETORNO_OID                     VARCHAR2(1) := '0';
   TIPO_RETORNO_CODIGO                  VARCHAR2(1) := '1';
   TIPO_DATO_MARCA                      VARCHAR2(3) := '01';
   TIPO_DATO_CANAL                      VARCHAR2(3) := '02';
   TIPO_DATO_MODULO                     VARCHAR2(3) := '03';--DESTINO
   TIPO_DATO_TIPO_EJECUCION             VARCHAR2(3) := '04';
   TIPO_DATO_PERIODO_INI_CADU           VARCHAR2(3) := '05';
   TIPO_DATO_PERIODO_FIN_CADU           VARCHAR2(3) := '06';
   TIPO_DATO_FECHA_INI_CADU             VARCHAR2(3) := '07';
   TIPO_DATO_FECHA_FIN_CADU             VARCHAR2(3) := '08';
   TIPO_DATO_MODULO_ORIGEN              VARCHAR2(3) := '09';
   TIPO_DATO_CAMPANHA_PROCESO           VARCHAR2(3) := '10';
   TIPO_DATO_FECHA_FACTURACION          VARCHAR2(3) := '11';
   TIPO_DATO_CAMPANA_REFERENCIA         VARCHAR2(3) := '12';
   TIPO_DATO_ACTIVIDAD_CRONOGRAMA       VARCHAR2(3) := '13';
   TIPO_DATO_TIPO_SOLICITUD             VARCHAR2(3) := '14';
   TIPO_DATO_CANTIDAD_CAMPANA           VARCHAR2(3) := '15';
   TIPO_DATO_TIPO_CLIE                  VARCHAR2(4) := '16_1';
   TIPO_DATO_SUB_TIPO_CLIE              VARCHAR2(4) := '16_2';
   TIPO_DATO_TIPO_CLASI_CLIE            VARCHAR2(4) := '16_3';
   TIPO_DATO_CLASI_CLIE                 VARCHAR2(4) := '16_4';
   TIPO_DATO_PRODUCTO                   VARCHAR2(3) := '17';
   TIPO_DATO_CODIGO_VENTA               VARCHAR2(3) := '18';
   TIPO_DATO_TIPO_OFERTA                VARCHAR2(3) := '19';
   TIPO_DATO_CONCURSO                   VARCHAR2(3) := '20';
   TIPO_DATO_MENSAJE                    VARCHAR2(3) := '21';
   TIPO_DATO_REGION                     VARCHAR2(3) := '22';
   TIPO_DATO_ZONA                       VARCHAR2(3) := '23';
   TIPO_DATO_TERRITORIO                 VARCHAR2(3) := '24';
   TIPO_DATO_ACTIVIDAD                  VARCHAR2(3) := '25';
   TIPO_DATO_FECHA_ACTIVIDAD            VARCHAR2(3) := '26';
   TIPO_DATO_HORA_ACTIVIDAD             VARCHAR2(3) := '27';
   TIPO_DATO_LUGAR_ACTIVIDAD            VARCHAR2(3) := '28';
   TIPO_DATO_ESTATUS                    VARCHAR2(3) := '29';
   PY1_MENSAJE_CUMPLE                   VARCHAR2(6) := 'PY101';
   PY1_MENSAJE_ANIVER                   VARCHAR2(6) := 'PY102';

   PY1_MENSAJE_VENTA                    VARCHAR2(6) := 'PY103';
   PY1_MENSAJE_VENTA2                   VARCHAR2(6) := 'PY116';

   PY1_MENSAJE_DSCTO                    VARCHAR2(6) := 'PY104';

   PY1_MENSAJE_AHORRO_CON_METAS         VARCHAR2(6) := 'PY105';
   PY1_MENSAJE_AHORRO_CON_METAS2        VARCHAR2(6) := 'PY113';
   PY1_MENSAJE_AHORRO_CON_METAS3        VARCHAR2(6) := 'PY114';

   PY1_MENSAJE_LOGRO_META               VARCHAR2(6) := 'PY106';
   PY1_MENSAJE_NOLOGRO_META             VARCHAR2(6) := 'PY107';

   PY1_MENSAJE_AHORRO_SIN_METAS         VARCHAR2(6) := 'PY108';
   PY1_MENSAJE_AHORRO_SIN_METAS2        VARCHAR2(6) := 'PY115';

   PY1_MENSAJE_DSCTO_MAXIMO             VARCHAR2(6) := 'PY109';
   PY1_MENSAJE_DSCTO_OFICINA            VARCHAR2(6) := 'PY110';

   PY1_MENSAJE_NOLOGRO_META_PREV        VARCHAR2(6) := 'PY112';
   PY1_MENSAJE_NUEVA_SINMETAS           VARCHAR2(6) := 'PY117';

   PY1_NUMERO_CAMPANHAS                 NUMBER:=3;

   TIPO_MENSAJE_VARIABLE                VARCHAR2(3) := 'G1';

   TIPO_CONS_REST_ANIVERSARIO            VARCHAR(12) := 'ANV';
   TIPO_CONS_REST_MONTO_CATALOGO         VARCHAR(12) := 'CAT';
   TIPO_CONS_REST_CODIGO_PREMIO          VARCHAR(12) := 'COP';
   TIPO_CONS_REST_CTD_CAMP_1_PED         VARCHAR(12) := 'CTC';
   TIPO_CONS_REST_CTD_PED_NUEVA          VARCHAR(12) := 'CTP';
   TIPO_CONS_REST_CUMPLEANHOS            VARCHAR(12) := 'CUM';
   TIPO_CONS_REST_CUV_REEMPLAZO          VARCHAR(12) := 'CUR';
   TIPO_CONS_REST_CODIGO_VENTA           VARCHAR(12) := 'CUV';
   TIPO_CONS_REST_ESTATUS                VARCHAR(12) := 'EST';
   TIPO_CONS_REST_LISTA_CONS             VARCHAR(12) := 'LCL';
   TIPO_CONS_REST_GENERA_PEDIDO          VARCHAR(12) := 'PED';
   TIPO_CONS_REST_RANGOS_VENTA           VARCHAR(12) := 'RNV';
   TIPO_CONS_REST_TIPO_CLASIF            VARCHAR(12) := 'TCL';
   TIPO_CONS_REST_UNIDAD_ADMIN           VARCHAR(12) := 'UAS';
   TIPO_CONS_REST_CUV_FALTANTE           VARCHAR(12) := 'CUF';

   CRA_REPAR_PEDID                       VARCHAR2(6) := 'CRARP';
   CRA_FACTU                             VARCHAR2(6) := 'CRARE';
   CRA_CANJE_DEVOL                       VARCHAR2(6) := 'CRACD';
   CRA_CONFE_CAMPA_ACTUA                 VARCHAR2(6) := 'CRACCA';
   CRA_CONFE_CAMPA_FUTUR                 VARCHAR2(6) := 'CRACCF';


   INC_CONCU_CUPEL_ACUMU                 VARCHAR2(6) := 'INCCEA';
   INC_CONCU_CUPEL_CAMPA                 VARCHAR2(6) := 'INCCEC';

   NVS_DESCU_CAMPA_ACTUA                 VARCHAR2(6) := 'NVSDCA';
   NVS_DESCU_CAMPA_ASIGN                 VARCHAR2(6) := 'NVSDCS';
   FAC_REPAR_CATAL_LBEL                  VARCHAR2(6) := 'REPLB';

   TYPE TABLA_VALORES IS TABLE OF VARCHAR2(32767) ;



/***************************************************************************
Descripcion : funcion que retorna el valor correspondiente a una plantilla y proceso,
  si no se encuentra retorna null
Fecha Creacion : 05/08/2009
Autor : Sergio Buchelli
Parametros:
 psCondigoPlantilla : Codigo de Plantilla
 psCondigoIdent : Codigo de Identificacion proceso
 psCodigoTipoDato  :codigo tipo dato
 psTipoRetorno : 0:Oid  1:Codigo
***************************************************************************/
FUNCTION MSG_FN_OBTEN_DATO(psCodigoPlantilla  VARCHAR2,
   psCodigoIdenti    VARCHAR2,
   psCodigoTipoDato  VARCHAR2,
   psTipoRetorno VARCHAR2)
RETURN VARCHAR2;


/**************************************************************************************
Descripcion : Proceso que inserta en tabla temporal los Pedidos que estan en GP4
              (se usa tabla temporal para que sea reutilizada por todos los procesos)
Fecha Creacion : 30/01/2012
Fecha Modificacion: 21/08/2015
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoMarca ; Codigo Marca
 psCodigoCanal ; Codigo Canal
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoUsuario : Codigo Usuario
***************************************************************************************/
PROCEDURE MSG_PR_PEDID_CLIEN(
  psCodigoPais       VARCHAR2,
  psCodigoMarca      VARCHAR2,
  psCodigoCanal      VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psCodigoUsuario   VARCHAR2

);


/***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes segun codigo de identificacion del proceso
Fecha Creacion : 04/08/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
 psCondigousuario : Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2,
  psCodigoUsuario  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes segun actividad en crono de actividades

Fecha Creacion : 04/08/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CRONO_ACTIV
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que genera mensajes para consultoras que estan en sus 4
              primeros pedidos (Nuevas)
Fecha Creacion : 09/01/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_NUEVAS
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

 /***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes en buzón para los mensajes gestionados por usuarios
Fecha Creacion : 04/08/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_GESTI_USUAR
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Primer Pedido
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Segundo Pedido
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_2DPED
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Tercer Pedido
              Consecutivo
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_3EPED_CONSE
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

 /***************************************************************************
Descripcion : Proceso que genera mensajes diversos y que solo se habilitan
              para algunas campañas, el script puede variar dependiendo de
              la necesidad del usuario
Fecha Modificacion : 08/05/2012
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_VARI1
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Cuarto Pedido
              Consecutivo
Fecha Creacion : 09/05/2011
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_4EPED_CONSE
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

 /***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes para consultoras que hayan facturado
Fecha Creacion : 21/12/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONSU_FACTU
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que genera mensajes de Concursos Cerrados
Fecha Creacion : 22/01/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_CERRA
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que genera mensajes de Concursos Vigentes
Fecha Creacion : 22/01/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_VIGEN
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes para Concurso
 							de los Sueños
Fecha Creacion : 30/07/2013
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_BANSU
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/**************************************************************************************
Descripcion : Proceso que genera mensajes de Concurso Cupones Electronicos

Fecha Creacion : 26/09/2013
Fecha Modificacion: 26/09/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_CUPEL (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que carga de la eliminacion  de mensajes no utilizados
Fecha Creacion : 21/12/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_REUTI
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Tercer Pedido
              No Consecutivo
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_3EPED_NOCON
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

 /***************************************************************************
Descripcion : Proceso que carga de generar las metas y logros
Fecha Creacion : 09/02/2011
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_CONSU_METAS
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : devulve el texto no considerando etiquets xml configuardas
              en la tabla de parametrso del mensaje
Fecha Creacion : 03/01/2011
Autor : Sergio Buchelli
Parametros:
 psCadenaTexto : Texto del mensaje con etiquetes de XML

***************************************************************************/
FUNCTION MSG_FN_DEVUE_TEXTO_REEMP(
  psCadenaTexto VARCHAR2) RETURN VARCHAR2;


/***************************************************************************
Descripcion : funcion que retorna lista de oid en un typo de dato pipeline
Fecha Creacion : 18/01/2011
Autor : Sergio Buchelli
Parametros:
 psCondigoPlantilla : Codigo de Plantilla
 psCondigoIdent : Codigo de Identificacion proceso
 psCodigoTipoDato  :codigo tipo dato
  psTipoRetorno : 0:Oid  1:Codigo
***************************************************************************/
FUNCTION MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla  VARCHAR2,
   psCodigoIdenti    VARCHAR2,
   psCodigoTipoDato  VARCHAR2,
   psTipoRetorno VARCHAR2 ) RETURN TABLA_VALORES PIPELINED;

/***************************************************************************
Descripcion : devulve la venta de atendido en la campanhas de un grupo de proceso
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidCliente  :oid cliente
  lnOidGrupoProceso : oid grupo proceso
  lnOidPeriodo :oidPeriodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  lnOidCliente NUMBER,
                                  lnOidGrupoProceso NUMBER,
                                  lnOidPeriodo NUMBER) RETURN NUMBER;


/***************************************************************************
Descripcion : devulve el ahorro en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
  psCodigoPeriodo : codigo periodo facturacion
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti     VARCHAR2,
                                  campanhaInicio     VARCHAR2,
                                  cantidadPeriodo    NUMBER,
                                  lnOidCliente       NUMBER,
                                  lnpais             NUMBER,
                                  lnmarca            NUMBER,
                                  lncanal            NUMBER,
                                  psCodigoPeriodo    VARCHAR2,
                                  psMontoMeta        NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve venta atendida en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidCliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER,
                                  psCodigoPeriodo VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve descripcion en la campanha real o plaenada
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  campanhaInicio :campanha inicio
  psCodigoPeriodo codigo Periodo
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_DESCR_CAMPA(campanhaInicio VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion : devulve el ahorro en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidcliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve venta atendida en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidcliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER;

 /***************************************************************************
Descripcion : Proceso que carga de generar mensaje de escalera de superacion
Fecha Creacion : 25/04/2011
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_ESCAL_SUPER
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que carga de generar mensaje de FAMILIA SEGURA
Fecha Creacion : 30/05/2011
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_FAMSE_SEGUR
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Insertamos mensajes impresos en Buzon Historico y los eliminamos
	            del buzon de mensajes
Fecha Creacion : 09/08/2011
Autor : Jose Luis rodríguez
Parametros:
 psCodigoPais : Codigo de Pais
***************************************************************************/
PROCEDURE  MSG_PR_ELIMI_BUZON_MENSA(
  psCodigoPais       VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que carga de generar mensaje de PREMIOS ATENDIDOS POR CONSULTORA
Fecha Creacion : 23/09/2011
Autor : Carlos Diaz Valverde
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_PREMI_ATEND
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que genera mensajes para Nuevas con Primer Pedido por
              consultora en cada facturación
Fecha Creacion : 28/09/2011
Autor : Sergio Apaza
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED_WEB
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
);

/********************************************************************************
Descripcion : Proceso que genera mensajes para Consultoras Nuevas Primer Pedido
              que compran productos de una determinada unidad negocio - negocio
Fecha Creacion : 03/10/2011
Autor : Sergio Apaza
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*********************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED_PRODU (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que genera mensajes Conferencia de Triunfadoras
Fecha Creacion : 05/10/2011
Autor :
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONFE_TRIUN
 (psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );

/********************************************************************************
Descripcion : Proceso que genera mensajes para Producto Exigido - INC
Fecha Creacion : 07/02/2012
Autor :
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion Proceso
 psCondigoPlantilla : Codigo de Plantilla
*********************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_PRODU_EXIGI (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
);

/***************************************************************************
Descripcion : Valida el Mensaje si es todo ok graba las tablas principales
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psCodigoPais            Codigo Pais,
  psCampanhaProceso       Campaña Proceso,
  psCodigoDocumento       Codigo Documento,
  psDescripcionPatron     Descripcion Patron ,
  psTipoMensaje           Tipo Mensaje,
  psCodigoModulo          Codigo Modulo,
  psCodigoMensaje         Codigo Mensaje,
  psCodigoSeccion         Codigo Seccion,
  psCorrelativo           Correlativo,
  psIndicadorActivo       Indicador Activo,
  psDescripcionMensaje    Descripcin Mensaje,
  psTextoMensaje          Texto mensaje,
  psOidPatron             Oid Patron,
  psOidPeriodoCorpo       Oid Periodo,
  psCodigoUsuario         Codigo usuario,
  psMensajeResultado       Mensaje Resultado
***************************************************************************/
PROCEDURE MSG_PR_VALID_MENSA
 (psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psTipoMensaje           VARCHAR2,
  psOidModulo             VARCHAR2,
  psCodigoMensaje         VARCHAR2,
  psCodigoSeccion         VARCHAR2,
  psCorrelativo           VARCHAR2,
  psIndicadorActivo       VARCHAR2,
  psDescripcionMensaje    VARCHAR2,
  psTextoMensaje          VARCHAR2,
  psTextoHtml             VARCHAR2,
  psOidPatron             VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoUsuario         VARCHAR2,
  psNombreArchivoImgFondo VARCHAR2,
  psMensajeResultado      OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Valida el Mensaje si es todo ok graba las tablas principales
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psCodigoPais            Codigo Pais,
  psCampanhaProceso       Campaña Proceso,
  psCodigoDocumento       Codigo Documento,
  psDescripcionPatron     Descripcion Patron ,
  psTipoMensaje           Tipo Mensaje,
  psCodigoModulo          Codigo Modulo,
  psCodigoMensaje         Codigo Mensaje,
  psCodigoSeccion         Codigo Seccion,
  psCorrelativo           Correlativo,
  psIndicadorActivo       Indicador Activo,
  psDescripcionMensaje    Descripcin Mensaje,
  psTextoMensaje          Texto mensaje,
  psOidPatron             Oid Patron,
  psOidPeriodoCorpo       Oid Periodo,
  psCodigoUsuario         Codigo usuario,
  psMensajeResultado       Mensaje Resultado
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA
 (psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psTipoMensaje           VARCHAR2,
  psOidModulo             VARCHAR2,
  psCodigoMensaje         VARCHAR2,
  psCodigoSeccion         VARCHAR2,
  psCorrelativo           VARCHAR2,
  psIndicadorActivo       VARCHAR2,
  psEstadoRegistro        VARCHAR2,
  psDescripcionMensaje    VARCHAR2,
  psTextoMensaje          VARCHAR2,
  psTextoHtml             VARCHAR2,
  psOidPatron             VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoUsuario         VARCHAR2,
  psIndicadorActualizar   VARCHAR2,
  psNombreArchivoImgFondo VARCHAR2,
  psMensajeResultado      OUT VARCHAR2
 );

/***************************************************************************
Descripcion : replica el patron
Fecha Creacion : 17/11/2011
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE MSG_PR_REPLI_PATRO;

/***************************************************************************
Descripcion : Elimina el patron
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psOidPatron             Oid Patron,

***************************************************************************/
PROCEDURE MSG_PR_ELIMI_PATRO
 (
  psOidPatron             VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psLogin                 VARCHAR2
 );

/***************************************************************************
Descripcion : devulve el ahorro EN CATALOGOS Y LIQUIDACIONES en la campmanha
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidcliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve el ahorro EN REVISTAS Y OFERTAS ESPECIALES en la campmanha
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidcliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve descripcion en la campanha real o plaenada utilizado por la version B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  campanhaInicio :campanha inicio
  psCodigoPeriodo codigo Periodo
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(campanhaInicio VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion : devulve el ahorro en la campmanha UTILIZADO POR LA VERSION B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti     VARCHAR2,
                                  campanhaInicio     VARCHAR2,
                                  cantidadPeriodo    NUMBER,
                                  lnOidCliente       NUMBER,
                                  lnpais             NUMBER,
                                  lnmarca            NUMBER,
                                  lncanal            NUMBER,
                                  psCodigoPeriodo    VARCHAR2,
                                  psMontoMeta        NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve el ahorro en CATALOGO Y LIQUIDACIONES UTILIZADO POR LA VERSION B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti     VARCHAR2,
                                  campanhaInicio     VARCHAR2,
                                  cantidadPeriodo    NUMBER,
                                  lnOidCliente       NUMBER,
                                  lnpais             NUMBER,
                                  lnmarca            NUMBER,
                                  lncanal            NUMBER,
                                  psCodigoPeriodo    VARCHAR2,
                                  psMontoMeta        NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion : devulve el ahorro en REVISTA Y OF ESPECIALES UTILIZADO POR LA VERSION B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti     VARCHAR2,
                                  campanhaInicio     VARCHAR2,
                                  cantidadPeriodo    NUMBER,
                                  lnOidCliente       NUMBER,
                                  lnpais             NUMBER,
                                  lnmarca            NUMBER,
                                  lncanal            NUMBER,
                                  psCodigoPeriodo    VARCHAR2,
                                  psMontoMeta        NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER;


/*****************************************************************************
  Descripcion : Proceso que genera informacion en el buzon de mensajes en base
                a la configuracion realizada en el nuevo modulo de mensajes
  Fecha Creacion : 06/12/2011
  Autor : CSVD-FFVV
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psFechaFacturacion : Fecha Facturacion
   psTipoProceso : Tipo Proceso
   psCondigoIdent : Codigo de Identificacion Proceso
   psCondigoPlantilla : Codigo de Plantilla
   psCondigousuario : Codigo de Usuario
******************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONSI_RESTR
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psTipoProceso      VARCHAR2,
   psCondigoIdent     VARCHAR2,
   psCodigoPlantilla  VARCHAR2,
   psCodigoUsuario    VARCHAR2
 );


/************************************************************************************
  Descripcion : Proceso que genera la actualizacion de mensajes al cierre de campanha
  Fecha Creacion : 16/05/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA_CAMPA
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoUsuario    VARCHAR2,
   psIndicadorCruce   VARCHAR2
 );


/************************************************************************************
  Descripcion : Proceso que genera la actualizacion TIPO DE ASIGNACION EN SICC
    para mensajes fijos
  Fecha Creacion : 31/07/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA_ASIGN
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psCodigoMensaje    VARCHAR2,
   psCodigoUsuario    VARCHAR2
 );
/************************************************************************************
  Descripcion : Proceso que genera excluir un mensaje desde el ordenamiento
   sigue la misma logica de eliminacion de mensajes
  Fecha Creacion : 24/09/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psOidDocumento : oid o codigo de mensaje
   psOidSeccion : oid seccion
   psOidMensaje : oid mensaje
   psCampanhaProceso : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_EXCLU_MENSA
 (
   psCodigoPais       VARCHAR2,
   psOidDocumento     VARCHAR2,
   psOidSeccion       VARCHAR2,
   psOidMensaje       VARCHAR2,
   psCampanhaProceso  VARCHAR2,
   psCodigoUsuario    VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que genera mensajes Informacion de Gerentes
Fecha Creacion : 17/10/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_GEREN
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que genera mensajes de Faltantes Anunciados
Fecha Creacion : 13/12/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_FALTA_ANUNC
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que inserta o actualiza registro en la tabla de Faltante
Anunciando
Fecha Creacion : 11/04/2013
Autor : Carlos Bazalar
Parametros:
  psCodigoPais: Codigo de Pais,
  psCodigoPeriodo: Codigo de Periodo,
  psCodigoCUV: Codigo CUV,
  psdescripcionProducto: Descripcion del Producto,
  psprecio: Precio del Producto,
  psdescripcionCatalogo: Descripcion del Catalogo,
  pspagina: Pagina del Catalogo,
  pscodigoUsuario: Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_FALTA_ANUNC
(
  psCodigoPais VARCHAR2,
  psCodigoPeriodo VARCHAR2,
  psCodigoCUV  VARCHAR2,
  psdescripcionProducto VARCHAR2,
  psprecio VARCHAR2,
  psdescripcionCatalogo VARCHAR2,
  pspagina VARCHAR2,
  pscodigoUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que inserta o actualiza registro en la tabla de Conferencias
Fecha Creacion : 19/04/2013
Autor : Aurelio Oviedo
Parametros:
  psCodigoPais: Codigo de Pais,
  psCodigoPeriodo: Codigo de Periodo,
  psCodigoRegion: Codigo Region,
  psCodigoZona: Codigo Zona,
  psGerente: Nombre Gerente,
  psDescripcionLocal: Descripcion del Local,
  psDireccion: Direccion,
  psFecha: Fecha,
  psHora: Hora,
  psCodigoUsuario: Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_CONFE
(
  psCodigoPais VARCHAR2,
  psCodigoPeriodo VARCHAR2,
  psCodigoRegion  VARCHAR2,
  psCodigoZona VARCHAR2,
  psGerente VARCHAR2,
  psDescripcionLocal VARCHAR2,
  psDireccion VARCHAR2,
  psFecha VARCHAR2,
  psHora VARCHAR2,
  pscodigoUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que genera mensajes Descuento Programa Nuevas
Fecha Creacion : 17/10/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_DESCU_GANAD
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que genera mensajes de Recomendaciones INC
Fecha Creacion : 19/11/2014
Fecha Modificacion: 19/11/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_RECOM
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que genera mensajes de Metas
Fecha Creacion : 20/11/2014
Fecha Modificacion: 20/11/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_METAS
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que genera mensajes del concurso Programa de Puntos
Fecha Creacion : 17/03/2015
Fecha Modificacion:
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_CONCU_PRPTO
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );


/***************************************************************************
Descripcion : Eliminar Caracteres Especiales de varias tablas
Fecha Creacion : 14/04/2015
Fecha Modificacion: 14/04/2015
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
***************************************************************************/
PROCEDURE MSG_PR_ELIMI_CARAC_ESPEC(
  psCodigoPais       VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que genera mensajes Reparto catálogo LBEL
Fecha Creacion : 26/10/2015
Autor : CSVD - FFVV - Juan Gutiérrez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_REPAR_CATAL
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que graba imagen en los mensajes
Fecha Creacion : 28/12/2015
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_GRABA_IMAGE_BLOB
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psCodigoMensaje      VARCHAR2,
  psCodigoUsuario      VARCHAR2,
  psArchivoImagen      VARCHAR2,
  psIndicadorImgFondo  VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que LEE imagen en los mensajes
Fecha Creacion : 28/12/2015
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_LEER_IMAGE_BLOB
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psCodigoMensaje      VARCHAR2
 );

/***************************************************************************
Descripcion : Funcion que retorna el valor correspondiente al indicador de Edicion
para el tema de Replica de Patron
Fecha Creacion : 04/01/2016
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION MSG_FN_DEVUE_INDIC_EDITA(
   psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   pnOidMensaje NUMBER,
   pnOidTipoMensaje NUMBER)
RETURN NUMBER;

/***************************************************************************
Descripcion : Graba Flyer en las tablas de Patron
Fecha Creacion : 21/01/2016
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_GRABA_FLYER
 (pnOidPatron             VARCHAR2,
  psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psCodigoBandeja         VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psCodigoUsuario         VARCHAR2
 );

END MSG_PKG_PROCE_MENSA;
/
CREATE OR REPLACE PACKAGE BODY "MSG_PKG_PROCE_MENSA" IS

/**************************************************************************************
Descripcion : Proceso que inserta en tabla temporal los Pedidos que estan en GP4
              (se usa tabla temporal para que sea reutilizada por todos los procesos)
Fecha Creacion : 30/01/2012
Fecha Modificacion: 21/08/2015
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoMarca ; Codigo Marca
 psCodigoCanal ; Codigo Canal
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psCodigoUsuario : Codigo Usuario
***************************************************************************************/
PROCEDURE MSG_PR_PEDID_CLIEN(
  psCodigoPais       VARCHAR2,
  psCodigoMarca      VARCHAR2,
  psCodigoCanal      VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psCodigoUsuario    VARCHAR2

) IS

  lnOidPais        NUMBER;
  lnOidMarca       NUMBER;
  lnOidCanal       NUMBER;
  lnOidPeriodo     NUMBER;
  lsIndPedStock    VARCHAR2(1);
  lsIndImprimirPD  VARCHAR2(1);

BEGIN
    -- Inicializar variables requeridas -------------------------------
    lnOidPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
    lnOidMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);
    lnOidCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);
    lnOidPeriodo   := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo, lnOidMarca, lnOidCanal);

    -- Obtenemos el indicador indPedStock a fin de validar si está permitida la ejecución del sp:  ped_pr_stock
    BEGIN
      SELECT VAL_PARA
        INTO lsIndPedStock
        FROM BAS_PARAM_PAIS
       WHERE COD_SIST = 'MSG'
         AND NOM_PARA = 'indPedStock'
         AND COD_PAIS = psCodigoPais
         AND IND_ACTI =  1;

      EXCEPTION
     WHEN OTHERS THEN
        lsIndPedStock := '1';
    END;

    -- Invocamos al procedimiento ped_pr_stock,  enviado por CCPP --
    IF (lsIndPedStock = '1') THEN
       PED_PKG_CUADR_OFERT.PED_PR_STOCK;
    END IF;

    -- Obtenemos el indicador Imprimir Paquete Documentario --
    BEGIN
      SELECT VAL_PARA
        INTO lsIndImprimirPD
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = psCodigoPais
         AND COD_SIST = 'MSG'
         AND NOM_PARA = 'indImprimirPD'
         AND IND_ACTI =  1;

      EXCEPTION
     WHEN OTHERS THEN
        lsIndImprimirPD := '0';
    END;

    -- Actualizamos Indicador Impresion Paquete Documentario --
    IF (lsIndImprimirPD = '1') THEN

       UPDATE mae_clien_datos_adici
          SET ind_impr_pdoc = 'N'
        WHERE ind_impr_pdoc IS NULL
          AND clie_oid_clie IN (
                                SELECT DISTINCT
                                       sc.clie_oid_clie
                                  FROM ped_solic_cabec sc,
                                       ped_tipo_solic_pais tsp,
                                       ped_tipo_solic ts
                                 WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                                   AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                   AND sc.fec_fact IS NULL
                                   AND sc.grpr_oid_grup_proc = 4
                                   AND sc.perd_oid_peri = lnOidPeriodo
                               );
    END IF;

    -- Invocamos al procedimiento para eliminar caracteres especiales --
    MSG_PR_ELIMI_CARAC_ESPEC (psCodigoPais);

    -- borramos la tabla temporal
    EXECUTE IMMEDIATE 'TRUNCATE TABLE msg_tmp_pedid_clien';

    -- Obtenemos los pedidos que estan en GP4
    INSERT INTO msg_tmp_pedid_clien
    (
     clie_oid_clie,
     oid_soli_cabe,
     perd_oid_peri,
     oid_soli_posi,
     prod_oid_prod,
     val_codi_vent,
     val_codi_vent_fict,
     ofde_oid_deta_ofer,
     oid_tipo_soli,
     cod_tipo_soli,
     num_unid_compr,
     copa_oid_para_gene,
	   num_prem,
     usu_crea,
     fec_crea,
     VAL_PORC_DESC,
     num_unid_dema_real,
     num_unid_aten,
     num_unid_por_aten
    )
    SELECT DISTINCT
    	     sc.clie_oid_clie,
           sc.oid_soli_cabe,
           sc.perd_oid_peri,
           sd.oid_soli_posi,
           sd.prod_oid_prod,
           sd.val_codi_vent,
           sd.val_codi_vent_fict,
           sd.ofde_oid_deta_ofer,
           ts.oid_tipo_soli,
           ts.cod_tipo_soli,
           sd.num_unid_compr,
           sc.copa_oid_para_gene,
           sc.num_prem,
           psCodigoUsuario,
           SYSDATE,
           sd.VAL_PORC_DESC,
           sd.num_unid_dema_real,
           sd.num_unid_aten,
           sd.num_unid_por_aten
      FROM ped_solic_cabec sc,
           ped_solic_posic sd,
           ped_tipo_solic_pais tsp,
           ped_tipo_solic ts
     WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND sc.fec_fact IS NULL
       AND sc.grpr_oid_grup_proc = 4
       AND sc.perd_oid_peri = lnOidPeriodo
       AND sc.oid_soli_cabe = sd.soca_oid_soli_cabe;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_PEDID_CLIEN: '||ls_sqlerrm);

END MSG_PR_PEDID_CLIEN;


/***************************************************************************
Descripcion : Proceso que encarga de invocar a los procesos de mensajes
Fecha Creacion : 04/08/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Cdigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
 psCondigousuario : Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2,
  psCodigoUsuario  VARCHAR2
 )
IS

BEGIN

  IF(psCondigoIdent='MSG-1')THEN
      MSG_PR_GENER_MENSA_CRONO_ACTIV(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-2')THEN
      MSG_PR_GENER_MENSA_NUEVAS(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-3')THEN
      MSG_PR_GENER_MENSA_1EPED(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-4')THEN
      MSG_PR_GENER_MENSA_2DPED(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-5')THEN
      MSG_PR_GENER_MENSA_3EPED_CONSE(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-6')THEN
      MSG_PR_GENER_MENSA_3EPED_NOCON(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-7')THEN
      MSG_PR_GENER_MENSA_4EPED_CONSE(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-8')THEN
      MSG_PR_GENER_MENSA_CONSU_FACTU(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-9')THEN
      MSG_PR_GENER_MENSA_CONCU_CERRA(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-10')THEN
      MSG_PR_GENER_MENSA_CONCU_VIGEN(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-11')THEN
      MSG_PR_GENER_MENSA_CONCU_BANSU(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-12')THEN
      MSG_PR_GENER_MENSA_CONSU_METAS(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-13')THEN
      MSG_PR_GENER_MENSA_ESCAL_SUPER(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-14')THEN
      MSG_PR_GENER_MENSA_FAMSE_SEGUR(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-15')THEN
      MSG_PR_GENER_MENSA_PREMI_ATEND(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-16')THEN
      MSG_PR_GENER_MENSA_1EPED_WEB(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-17')THEN
      MSG_PR_GENER_MENSA_1EPED_PRODU(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-18')THEN
      MSG_PR_GENER_MENSA_CONFE_TRIUN(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-19')THEN
      MSG_PR_GENER_MENSA_CONCU_CUPEL(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-20')THEN
      MSG_PR_GENER_MENSA_CONSI_RESTR(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla,psCodigoUsuario);
  END IF;

  IF(psCondigoIdent='MSG-21')THEN
      MSG_PR_GENER_MENSA_GEREN(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-22')THEN
      MSG_PR_GENER_FALTA_ANUNC(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-97')THEN
      MSG_PR_GENER_MENSA_VARI1(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-98')THEN
      MSG_PR_GENER_MENSA_GESTI_USUAR(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-99')THEN
      MSG_PR_GENER_MENSA_REUTI(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-26')THEN
    MSG_PR_GENER_MENSA_DESCU_GANAD(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-27')THEN
      MSG_PR_GENER_MENSA_CONCU_RECOM(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-28')THEN
      MSG_PR_GENER_MENSA_METAS(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-29')THEN
      MSG_PR_GENER_CONCU_PRPTO(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

  IF(psCondigoIdent='MSG-30')THEN
      MSG_PR_GENER_MENSA_REPAR_CATAL(psCodigoPais,psCodigoPeriodo,psFechaFacturacion,psTipoProceso,psCondigoIdent,psCodigoPlantilla);
  END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA;



/**************************************************************************************
Descripcion : Proceso que genera mensajes segun actividad (cronograma de actividades)

Fecha Creacion : 04/08/2009
Fecha Modificacion: 08/01/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CRONO_ACTIV (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnPais                     NUMBER;
  lnMarca                    NUMBER;
  lnCanal                    NUMBER;
  lsCodMensaje               msg_mensa.cod_mens%TYPE;
  lsCodModulo                seg_modul.cod_modu%TYPE;
  lsCodTipoSoli              ped_tipo_solic.cod_tipo_soli%TYPE;
  lnCantidadPeriodo          NUMBER;

  lsPeriodoDev               VARCHAR2(6);

  lnOidModuloOrigen          NUMBER;
  lnOidMensaje               NUMBER;
  lnOidMensajeActividadRE    NUMBER;
  lnOidMensajeActividadRP    NUMBER;
  lnOidMensajeActividadCD    NUMBER;


BEGIN
  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MARCA, TIPO_RETORNO_OID);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_CANAL, TIPO_RETORNO_OID);
  --lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MENSAJE, TIPO_RETORNO_CODIGO);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MODULO_ORIGEN, TIPO_RETORNO_CODIGO);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_TIPO_SOLICITUD, TIPO_RETORNO_CODIGO);
  lnCantidadPeriodo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_CANTIDAD_CAMPANA, NULL);

  lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lsPeriodoDev := GEN_FN_CALCU_PERIO(psCodigoPeriodo, lnCantidadPeriodo); -- CodCamp, NumCamp

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  /*SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro*/

  --Mensaje Facturacion - RE ( CRARE )--
  BEGIN
    SELECT msg.oid_mens
      INTO lnOidMensajeActividadRE
      FROM msg_mensa msg
     WHERE msg.cod_mens = CRA_FACTU
       AND msg.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeActividadRE := '';
  END;

  --Mensaje Reparto Pedido - RP ( CRARP )--
  BEGIN
    SELECT msg.oid_mens
      INTO lnOidMensajeActividadRP
      FROM msg_mensa msg
     WHERE msg.cod_mens = CRA_REPAR_PEDID
       AND msg.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeActividadRP := '';
  END;

  --Mensaje Canjes y Devoluciones - CD  ( CRACD )--
  BEGIN
    SELECT msg.oid_mens
      INTO lnOidMensajeActividadCD
      FROM msg_mensa msg
     WHERE msg.cod_mens = CRA_CANJE_DEVOL
       AND msg.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeActividadCD := '';
  END;

  INSERT INTO MSG_BUZON_MENSA
  (
    oid_buzo_mens, num_secu,
    ind_esta_mens,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie,
    val_nom2_clie,
    val_ape1_clie,
    val_ape2_clie,
    val_apel_casa_clie,
    dato_vari_01, dato_vari_02, dato_vari_03, dato_vari_04, dato_vari_05,
    num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          CASE base.cod_acti
               WHEN 'RP' THEN lnOidMensajeActividadRP
               WHEN 'RE' THEN lnOidMensajeActividadRE
               ELSE lnOidMensajeActividadCD
          END lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, base.cod_acti, base.des_clas_acti, base.cod_peri, TO_CHAR(base.fecprog),
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  acti.cod_acti,
                  acti.des_clas_acti,
                  acti.cod_peri,
                  TO_CHAR(acti.fec_inic,'dd/mm/yyyy') fecprog
             FROM msg_tmp_pedid_clien pedc,
                  ped_solic_cabec sc,
                  mae_clien mc,
                  (
                   SELECT zz.oid_zona,
                          zz.cod_zona,
                          cac.cod_acti,
                          ccl.des_clas_acti,
                          spc.cod_peri,
                          ccr.fec_inic
                     FROM cra_crono ccr,
                          cra_activ cac,
                          cra_clase_activ ccl,
                          cra_perio cp,
                          seg_perio_corpo spc,
                          zon_zona zz
                    WHERE ccr.cact_oid_acti = cac.oid_acti
                      AND cac.clac_oid_clas_acti = ccl.oid_clas_acti
                      AND cac.cod_acti IN ('RP','RE','CD')
                      AND ccr.perd_oid_peri = cp.oid_peri
                      AND cp.peri_oid_peri = spc.oid_peri
                      AND spc.cod_peri = lsPeriodoDev -- parametro
                      AND ccr.zzon_oid_zona = zz.oid_zona
                  ) acti
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
              AND pedc.oid_soli_cabe = sc.oid_soli_cabe
              AND pedc.clie_oid_clie = mc.oid_clie
              AND sc.zzon_oid_zona = acti.oid_zona
        ) base
  WHERE CASE base.cod_acti
             WHEN 'RP' THEN lnOidMensajeActividadRP
             WHEN 'RE' THEN lnOidMensajeActividadRE
             ELSE lnOidMensajeActividadCD
        END IS NOT NULL
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CRONO_ACTIV: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CRONO_ACTIV;


/***************************************************************************
Descripcion : Proceso que genera mensajes para consultoras que estan en sus 4
              primeros pedidos (Nuevas)
Fecha Creacion : 09/01/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_NUEVAS
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
    )
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.clie_oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.codigoCliente, --'DV01'
            base.numDocumento, -- 'DV02'
            base.numDocumIncom, -- 'DV03'
            base.campanaIngreso, -- 'DV04'
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT DISTINCT pedc.clie_oid_clie,
                    pedcam.val_nom1 AS nom1,
                    pedcam.val_nom2 AS nom2,
                    pedcam.val_ape1 AS ape1,
                    pedcam.val_ape2 AS ape2,
                    pedcam.val_apel_casa AS apec,
                    pedcam.cod_clie AS codigoCliente,
                    mci.num_docu_iden numDocumento,
                    SUBSTR( mci.num_docu_iden,-4) numDocumIncom,
                    pedcam.val_camp_ingr AS campanaIngreso,
                    pedcam.ind_pedi_X1 AS indpedidoX1,
                    pedcam.ind_pedi_X2 AS indpedidoX2,
                    pedcam.ind_pedi_X3 AS indpedidoX3,
                    pedcam.ind_pedi_X4 AS indpedidoX4
               FROM msg_tmp_pedid_clien pedc,
                    (
                     SELECT clie.cod_clie,
                            clda.clie_oid_clie,
                            clie.val_nom1,
                            clie.val_nom2,
                            clie.val_ape1,
                            clie.val_ape2,
                            clie.val_apel_casa,
                            FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( cprc.perd_oid_peri ) val_camp_ingr,
                            ( SELECT COUNT(1) FROM ped_solic_cabec_acum2 sca2 WHERE sca2.clie_oid_clie = clda.clie_oid_clie AND sca2.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( pscodigoperiodo, -3 ) ) ) ind_pedi_X1,
                            ( SELECT COUNT(1) FROM ped_solic_cabec_acum2 sca2 WHERE sca2.clie_oid_clie = clda.clie_oid_clie AND sca2.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( pscodigoperiodo, -2 ) ) ) ind_pedi_X2,
                            ( SELECT COUNT(1) FROM ped_solic_cabec_acum2 sca2 WHERE sca2.clie_oid_clie = clda.clie_oid_clie AND sca2.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( pscodigoperiodo, -1 ) ) ) ind_pedi_X3,
                            ( SELECT COUNT(1) FROM ped_solic_cabec_acum2 sca2 WHERE sca2.clie_oid_clie = clda.clie_oid_clie AND sca2.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( pscodigoPeriodo ) ) ind_pedi_X4
                       FROM mae_clien_prime_conta clda,
                            mae_clien_prime_conta cprc,
                            mae_clien_tipo_subti ctsu,
                            mae_clien clie
                      WHERE clda.clie_oid_clie = cprc.clie_oid_clie
                        AND clda.clie_oid_clie = ctsu.clie_oid_clie
                        AND clda.clie_oid_clie = clie.oid_clie
                        --
                        AND ctsu.ticl_oid_tipo_clie = 2 -- consultora
                        AND ctsu.sbti_oid_subt_clie = 1 -- negocio
                        AND cprc.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( pscodigoperiodo, -3 ) )
                    ) pedcam,
                    mae_clien_ident mci
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = pedcam.clie_oid_clie
                AND pedc.clie_oid_clie = mci.clie_oid_clie
            ) base
    );

  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_NUEVAS: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_NUEVAS;


 /***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes en buzón para los mensajes gestionados por usuarios
Fecha Creacion : 04/08/2009
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_GESTI_USUAR
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2) IS
 lnmarca NUMBER;
 lncanal NUMBER;
BEGIN
 lnmarca:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MARCA,TIPO_RETORNO_OID);
 lncanal:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_CANAL,TIPO_RETORNO_OID);

  INSERT INTO MSG_BUZON_MENSA
( OID_BUZO_MENS,  NUM_SECU ,
  DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
  IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
  DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
  NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
)
( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
		'DV10','DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
		NULL, BASE.CLIE_OID_CLIE,
        (SELECT TMSG.OID_MENS FROM MSG_MENSA TMSG WHERE TMSG.COD_MENS = MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent,TIPO_DATO_MENSAJE,TIPO_RETORNO_CODIGO)),
        (SELECT MOD.OID_MODU FROM SEG_MODUL MOD WHERE MOD.COD_MODU=MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent,TIPO_DATO_MODULO_ORIGEN,TIPO_RETORNO_CODIGO)),
		BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
		BASE.NOMBRE, BASE.CodigoActividad, base.DescripcionActividad, base.CampanaActividad,base.fecprog, base.HoraActividad, base.LugarActividad,'DV08','DV09',
        NULL, SYSDATE, NULL, 1, NULL, 1
  FROM
        (SELECT DISTINCT SC.CLIE_OID_CLIE,
                MC.VAL_NOM1 AS NOM1,
                MC.VAL_NOM2 AS NOM2,
                MC.VAL_APE1 AS APE1,
                MC.VAL_APE2 AS APE2,
                MC.VAL_APEL_CASA AS APEC,
                MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
                MUSR.COD_ACTI CodigoActividad,
                MUSR.DES_CLAS_ACTI DescripcionActividad,
                MUSR.CAM_ACTI CampanaActividad,
                MUSR.FEC_ACTI FECPROG,
                MUSR.HOR_ACTI HoraActividad,
                MUSR.LUG_ACTI LugarActividad
        FROM
        PED_SOLIC_CABEC SC,
        PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC,
        ZON_ZONA ZZ,
        ZON_REGIO ZR,
        MSG_MENSA_REUNI_GEREN_ZONAL MUSR
        WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO)
        AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = 4
        AND SC.CLIE_OID_CLIE = MC.OID_CLIE
        AND SC.ZZON_OID_ZONA = ZZ.OID_ZONA
        AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
        AND (( ZR.COD_REGI = MUSR.COD_REGI AND MUSR.COD_ZONA = NULL)
             OR   (ZR.COD_REGI = MUSR.COD_REGI AND ZZ.COD_ZONA = MUSR.COD_ZONA))
        AND SC.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(
                                     psCodigoPeriodo,
                                     lnmarca,
                                     lncanal)
        AND SC.PERD_OID_PERI = MUSR.OID_CAMP_PROC
        AND MUSR.COD_ACTI = MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_ACTIVIDAD_CRONOGRAMA,TIPO_RETORNO_CODIGO)
        AND MUSR.COD_PAIS = psCodigoPais
        AND MUSR.COD_MARC =MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MARCA,TIPO_RETORNO_CODIGO)
        AND MUSR.COD_CANA =MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_CANAL,TIPO_RETORNO_CODIGO)
        AND MUSR.COD_MENS = MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_CODIGO)
        ) BASE);


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_GESTI_USUAR: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_GESTI_USUAR;


/****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Primer Pedido
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,
   num_secu,
   ind_esta_mens,
   dato_vari_10,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT msg_bume_seq.nextval,
          msg_bum2_seq.nextval,
          NULL,
          'PRIMER PEDIDO', -- dv10
          base.clie_oid_clie,
          lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- dv01
          base.cod_prog, -- dv02
          base.camp_ini_ccc, -- dv03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                           mc.val_nom1 AS nom1,
                           mc.val_nom2 AS nom2,
                           mc.val_ape1 AS ape1,
                           mc.val_ape2 AS ape2,
                           mc.val_apel_casa AS apec,
                           mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                           nu.cod_prog,
                           nu.camp_ini_ccc
                      FROM msg_tmp_pedid_clien pedc,
                           mae_clien mc,
                           cup_consu_nueva nu,
                           cup_prog_nueva_cupon pn,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr

                     WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                       AND pedc.clie_oid_clie = mc.oid_clie
                       AND nu.cod_pais = psCodigoPais
                       AND mc.cod_clie = nu.cod_cons
                       AND nu.camp_ini_ccc = psCodigoPeriodo
                       AND nu.cod_prog = pn.cod_prog
                       AND pn.cod_pais = nu.cod_pais
                       AND pn.ind_gene_mens = '1'
                       AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
                       AND zs.oid_secc = zta.zscc_oid_secc
                       AND zz.oid_zona = zs.zzon_oid_zona
                       AND zr.oid_regi = zz.zorg_oid_regi
                       AND mc.oid_clie = mcua.clie_oid_clie
                       AND mcua.ind_acti = '1'
                       AND (zr.cod_regi not in ( SELECT cod_regi FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND nu.cod_prog = prog_cod_prog )
                       OR zz.cod_zona not in ( SELECT cod_zona FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND COD_REGI = zr.cod_regi AND nu.cod_prog = prog_cod_prog))
          ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_1EPED: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_1EPED;


/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Segundo Pedido
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_2DPED
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,
   num_secu,
   ind_esta_mens,
   dato_vari_10,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT msg_bume_seq.nextval,
          msg_bum2_seq.nextval,
          NULL,
          'SEGUNDO PEDIDO', -- dv10
          base.clie_oid_clie,
          lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- dv01
          base.cod_prog, -- dv02
          base.camp_ini_ccc, -- dv03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                           mc.val_nom1 AS nom1,
                           mc.val_nom2 AS nom2,
                           mc.val_ape1 AS ape1,
                           mc.val_ape2 AS ape2,
                           mc.val_apel_casa AS apec,
                           mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                           nu.cod_prog,
                           nu.camp_ini_ccc
                      FROM msg_tmp_pedid_clien pedc,
                           mae_clien mc,
                           cup_consu_nueva nu,
                           cup_prog_nueva_cupon pn,
	                         mae_clien_unida_admin mcua,
						               zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
                     WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                       AND pedc.clie_oid_clie = mc.oid_clie
                       AND nu.cod_pais = psCodigoPais
                       AND mc.cod_clie = nu.cod_cons
                       AND nu.camp_ini_ccc = CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo, -1)
                       AND nu.cod_prog = pn.cod_prog
                       AND pn.cod_pais = nu.cod_pais
                       AND pn.ind_gene_mens = '1'
                       AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
                       AND zs.oid_secc = zta.zscc_oid_secc
                       AND zz.oid_zona = zs.zzon_oid_zona
                       AND zr.oid_regi = zz.zorg_oid_regi
                       AND mc.oid_clie = mcua.clie_oid_clie
                       AND mcua.ind_acti = '1'
                       AND (zr.cod_regi not in ( SELECT cod_regi FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND nu.cod_prog = prog_cod_prog)
                       OR zz.cod_zona not in ( SELECT cod_zona FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND COD_REGI = zr.cod_regi AND nu.cod_prog = prog_cod_prog))
          ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_2DPED: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_2DPED;


/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Tercer Pedido
              Consecutivo
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_3EPED_CONSE
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,
   num_secu,
   ind_esta_mens,
   dato_vari_10,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT msg_bume_seq.nextval,
          msg_bum2_seq.nextval,
          NULL,
          'TERCER PEDIDO CONSECUTIVO', -- dv10
          base.clie_oid_clie,
          lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- dv01
          base.cod_prog, -- dv02
          base.camp_ini_ccc, -- dv03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                           mc.val_nom1 AS nom1,
                           mc.val_nom2 AS nom2,
                           mc.val_ape1 AS ape1,
                           mc.val_ape2 AS ape2,
                           mc.val_apel_casa AS apec,
                           mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                           nu.cod_prog,
                           nu.camp_ini_ccc
                      FROM msg_tmp_pedid_clien pedc,
                           mae_clien mc,
                           cup_consu_nueva nu,
                           cup_prog_nueva_cupon pn,
                           cup_consu_factu fc,
                           mae_clien_unida_admin mcua,
						               zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
                     WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                       AND pedc.clie_oid_clie = mc.oid_clie
                       AND nu.cod_pais = psCodigoPais
                       AND mc.cod_clie = nu.cod_cons
                       AND nu.camp_ini_ccc = CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo, -2)
                       AND nu.cod_prog = pn.cod_prog
                       AND nu.cod_cons = fc.cod_cons
                       AND fc.cod_pais = nu.cod_pais
                       AND fc.ind_cons = '0'
                       AND fc.cod_prog = nu.cod_prog
                       AND pn.cod_pais = nu.cod_pais
                       AND pn.ind_gene_mens = '1'
                       AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
                       AND zs.oid_secc = zta.zscc_oid_secc
                       AND zz.oid_zona = zs.zzon_oid_zona
                       AND zr.oid_regi = zz.zorg_oid_regi
                       AND mc.oid_clie = mcua.clie_oid_clie
                       AND mcua.ind_acti = '1'
                       AND (zr.cod_regi not in ( SELECT cod_regi FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND nu.cod_prog = prog_cod_prog)
                       OR zz.cod_zona not in ( SELECT cod_zona FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND COD_REGI = zr.cod_regi AND nu.cod_prog = prog_cod_prog))

          ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_3EPED_CONSE: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_3EPED_CONSE;


 /***************************************************************************
Descripcion : Proceso que genera mensajes diversos y que solo se habilitan
              para algunas campañas. No se debe usar ya que emite correos con
              mensajes para GZ por default
Fecha Modificacion : 19/09/2013
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_VARI1
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_VARI1: '||ls_sqlerrm);

END MSG_PR_GENER_MENSA_VARI1;

/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Cuarto Pedido
              Consecutivo
Fecha Creacion : 09/05/2011
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_4EPED_CONSE
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,
   num_secu,
   ind_esta_mens,
   dato_vari_10,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT msg_bume_seq.nextval,
          msg_bum2_seq.nextval,
          NULL,
          'CUARTO PEDIDO CONSECUTIVO', -- dv10
          base.clie_oid_clie,
          lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- dv01
          base.cod_prog, -- dv02
          base.camp_ini_ccc, -- dv03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                           mc.val_nom1 AS nom1,
                           mc.val_nom2 AS nom2,
                           mc.val_ape1 AS ape1,
                           mc.val_ape2 AS ape2,
                           mc.val_apel_casa AS apec,
                           mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                           nu.cod_prog,
                           nu.camp_ini_ccc
                      FROM msg_tmp_pedid_clien pedc,
                           mae_clien mc,
                           cup_consu_nueva nu,
                           cup_prog_nueva_cupon pn,
                           cup_consu_factu fc
                     WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                       AND pedc.clie_oid_clie = mc.oid_clie
                       AND nu.cod_pais = psCodigoPais
                       AND mc.cod_clie = nu.cod_cons
                       AND nu.camp_ini_ccc = CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo, -3)
                       AND nu.cod_prog = pn.cod_prog
                       AND nu.cod_cons = fc.cod_cons
                       AND fc.cod_pais = nu.cod_pais
                       AND fc.ind_cons = '0'
                       AND fc.cod_prog = nu.cod_prog
                       AND pn.cod_pais = nu.cod_pais
                       AND pn.ind_gene_mens = '1'
          ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_4EPED_CONSE: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_4EPED_CONSE;


/***************************************************************************
Descripcion : funcion que retorna el valor correspondiente a una plantilla y proceso,
  si no se encuentra retorna null ES PARA TIPO DE DATOS SIMPLE
Fecha Creacion : 05/08/2009
Autor : Sergio Buchelli
Parametros:
 psCondigoPlantilla : Codigo de Plantilla
 psCondigoIdent : Codigo de Identificacion proceso
 psCodigoTipoDato  :codigo tipo dato
  psTipoRetorno : 0:Oid  1:Codigo
***************************************************************************/
FUNCTION MSG_FN_OBTEN_DATO(psCodigoPlantilla  VARCHAR2,
   psCodigoIdenti    VARCHAR2,
   psCodigoTipoDato  VARCHAR2,
   psTipoRetorno VARCHAR2 )RETURN VARCHAR2
IS
 lsCodigoProceso MSG_PROCE_MENSA.COD_PROC%TYPE;
 lsResultado VARCHAR2(100);
 lsTabla VARCHAR2(100);
 lsIndicadorTabla varchar2(1);
 lsTipoDato VARCHAR2(50);
BEGIN

     SELECT COD_PROC INTO lsCodigoProceso
     FROM MSG_PROCE_MENSA
     WHERE COD_IDEN_APLI=psCodigoIdenti;


   BEGIN


          SELECT A.VAL_SELE ,
                 X.VAL_POSI,
                 X.TIP_DATO,
                 X.IND_TABL
         INTO lsResultado, lsTabla,lsTipoDato,lsIndicadorTabla
         FROM MSG_CONFI_PROCE_DETAL A,
              MSG_CONFI_PLANT_TIPO_DATO X
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO=psCodigoTipoDato
          AND X.COD_TIPO_DATO=A.TIDA_COD_TIPO_DATO;



          IF(psTipoRetorno IS NOT NULL) THEN
            --SI EL INDICADOR D
            --TABLA ES 1 SE PUEDE RETORNA COIDO O OID SI ES 0 SE TRATA DE CONTANTES , SOLO SE DEVUELVE CODIGO
             IF(lsIndicadorTabla='1')THEN
                IF(psTipoRetorno='1') THEN

                 --EL OID A CODIGO
                 SELECT CODIGO INTO lsResultado
                 FROM MSG_PLANT_TABLE
                 WHERE VAL_TABL = lsTabla
                  AND VAL_OID=lsResultado;

                END IF;
             ELSE
                IF(psTipoRetorno='0') THEN
                 --EL CODIGO A OID
                 SELECT VAL_OID INTO lsResultado
                 FROM MSG_PLANT_TABLE
                 WHERE VAL_TABL = lsTabla
                  AND CODIGO=lsResultado;

                END IF;
             END IF;

          END IF;

       RETURN lsResultado;
   EXCEPTION
    WHEN NO_DATA_FOUND THEN
     --SE TRATA DE TIPO DATO MULTIPLE SIEMPRE SE DEVUELVE OID
       IF( psCodigoTipoDato='16_1') THEN
         SELECT A.VAL_SELE into lsResultado
         FROM MSG_CONFI_PROCE_DETAL A, MSG_CONFI_PLANT_DETAL B
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO='16'
          AND A.PLAN_COD_PLAN= B.PLAN_COD_PLAN
          AND A.TIDA_COD_TIPO_DATO= B.COD_TIPO_DATO
          AND A.NUM_CORR = B.NUM_CORR
          AND B.VAL_LIST_TABL ='MAE_TIPO_CLIEN';

       END IF;

        IF( psCodigoTipoDato='16_2') THEN
         SELECT A.VAL_SELE into lsResultado
         FROM MSG_CONFI_PROCE_DETAL A, MSG_CONFI_PLANT_DETAL B
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO='16'
          AND A.PLAN_COD_PLAN= B.PLAN_COD_PLAN
          AND A.TIDA_COD_TIPO_DATO= B.COD_TIPO_DATO
          AND A.NUM_CORR = B.NUM_CORR
          AND B.VAL_LIST_TABL ='MAE_SUBTI_CLIEN';

       END IF;

        IF( psCodigoTipoDato='16_3') THEN
         SELECT A.VAL_SELE into lsResultado
         FROM MSG_CONFI_PROCE_DETAL A, MSG_CONFI_PLANT_DETAL B
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO='16'
          AND A.PLAN_COD_PLAN= B.PLAN_COD_PLAN
          AND A.TIDA_COD_TIPO_DATO= B.COD_TIPO_DATO
          AND A.NUM_CORR = B.NUM_CORR
          AND B.VAL_LIST_TABL ='MAE_TIPO_CLASI_CLIEN';

       END IF;

        IF( psCodigoTipoDato='16_4') THEN
         SELECT A.VAL_SELE into lsResultado
         FROM MSG_CONFI_PROCE_DETAL A, MSG_CONFI_PLANT_DETAL B
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO='16'
          AND A.PLAN_COD_PLAN= B.PLAN_COD_PLAN
          AND A.TIDA_COD_TIPO_DATO= B.COD_TIPO_DATO
          AND A.NUM_CORR = B.NUM_CORR
          AND B.VAL_LIST_TABL ='MAE_CLASI';

       END IF;
   END;

    RETURN lsResultado;
EXCEPTION
  WHEN OTHERS THEN
     RETURN NULL;
END MSG_FN_OBTEN_DATO;


 /***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes para
              consultoras que hayan facturado
Fecha Creacion : 21/12/2010
Fecha Modificacion : 27/03/2012
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONSU_FACTU
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 ) IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO MSG_BUZON_MENSA
  (
    oid_buzo_mens,  num_secu ,
    ind_esta_mens,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie,
    val_nom2_clie,
    val_ape1_clie,
    val_ape2_clie,
    val_apel_casa_clie,
    dato_vari_01,  dato_vari_02,  dato_vari_03,
    num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
    SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
           NULL,
           base.clie_oid_clie,
           lnOidMensaje,  -- parametro
           lnOidModuloOrigen,  -- parametro
           base.nom1,
           base.nom2,
           base.ape1,
           base.ape2,
           base.apec,
           base.nombre, base.oidproducto, base.codigoventa,
           NULL, SYSDATE, NULL, 1, NULL, 1
      FROM (
            SELECT DISTINCT
                   pedc.clie_oid_clie,
                   mc.val_nom1 AS nom1,
                   mc.val_nom2 AS nom2,
                   mc.val_ape1 AS ape1,
                   mc.val_ape2 AS ape2,
                   mc.val_apel_casa AS apec,
                   mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                   pedc.prod_oid_prod oidproducto,
                   pedc.val_codi_vent codigoventa
              FROM msg_tmp_pedid_clien pedc,
                   mae_clien mc
             WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
               AND pedc.clie_oid_clie = mc.oid_clie
               AND pedc.prod_oid_prod IN (
                                          SELECT column_value
                                            FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent,tipo_dato_producto,tipo_retorno_oid))
                                         )
               AND pedc.ofde_oid_deta_ofer IN (
                                               SELECT column_value
                                                 FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent,tipo_dato_codigo_venta,tipo_retorno_oid))
                                              )
               AND pedc.num_unid_compr > 0
           ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONSU_FACTU: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONSU_FACTU;


/***************************************************************************
Descripcion : Proceso que genera mensajes de Concursos Cerrados
Fecha Creacion : 22/01/2013
Fecha Modificacion: 07/08/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_CERRA
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     dato_vari_10,
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
)
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.clie_oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.codigoCliente, --'DV01'
            base.nombreConcurso, -- 'DV02'
            base.codigoConcurso, -- 'DV03'
            base.nivel, -- 'DV04'
            base.unidades, -- 'DV05'
            base.codigoReclamo, -- 'DV06'
            base.totalPuntos,  -- 'DV07'
            base.gano, -- 'DV08'
            base.descripcionPremio, -- 'DV09'
            base.estadoPremio, -- 'DV10'
        NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT sc.clie_oid_clie,
                    mc.val_nom1 nom1,
                    mc.val_nom2 nom2,
                    mc.val_ape1 ape1,
                    mc.val_ape2 ape2,
                    mc.val_apel_casa apec,
                    mc.cod_clie codigoCliente,
                    SUBSTR(UPPER(val_nomb),1,33) nombreConcurso,
                    inc.num_conc codigoConcurso,
                    premios.num_nive nivel,
                    num_unid_dema_real unidades,
                    sp.val_codi_vent_fict codigoReclamo,
                    (
                     SELECT SUM(num_punt)
                       FROM inc_cuent_corri_punto cc
                      WHERE cc.copa_oid_para_gral = inc.oid_para_gral
                        AND cc.clie_oid_clie = sc.clie_oid_clie
                        AND UPPER(cc.val_desc) NOT LIKE 'ENTREGA DE PREMIO%'
                    ) totalPuntos,
                    'SI' gano,
                    SUBSTR(UPPER(des.val_i18n),1,23) descripcionPremio,
                    CASE WHEN num_unid_dema_real - num_unid_compr = 0 THEN 'ENTREGADO'
                         ELSE 'PENDIENTE'
                    END estadoPremio
               FROM ped_solic_cabec sc,
                    ped_solic_posic sp,
                    gen_i18n_sicc_pais des,
                    mae_produ,
                    inc_concu_param_gener inc,
                    ped_tipo_solic_pais tsp,
                    ped_tipo_solic ts,
                    (
                     SELECT DISTINCT clie_oid_clie
                       FROM msg_tmp_pedid_clien
                      WHERE cod_tipo_soli = lsCodTipoSoli
                    ) tempmsg,
                    (
                     SELECT cpg.num_conc,
                            pnp.num_nive,
                            p.cod_sap,
                            al.cod_vent_fict cod_prem,
                            'O' tipo   -- Premio original
                       FROM inc_concu_param_gener cpg,
                            inc_param_gener_premi pgp,
                            inc_param_nivel_premi pnp,
                            inc_premi_artic pa,
                            inc_lote_premi_artic lpa,
                            inc_artic_lote al,
                            mae_produ p
                      WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
                        AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
                        AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
                        AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
                        AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
                        AND al.prod_oid_prod = p.oid_prod
                      UNION
                     SELECT cpg.num_conc,
                            pnp.num_nive,
                            p.cod_sap,
                            ral.cod_vent_fict cod_premio,
                            'R' tipo  -- Premio reemplazo
                       FROM inc_concu_param_gener cpg,
                            inc_param_gener_premi pgp,
                            inc_param_nivel_premi pnp,
                            inc_premi_artic pa,
                            inc_lote_premi_artic lpa,
                            inc_artic_lote al,
                            mae_produ p,
                            inc_reemp_artic_lote ral
                      where cpg.oid_para_gral = pgp.copa_oid_para_gral
                        AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
                        AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
                        AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
                        AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
                        AND al.oid_arti_lote = ral.arlo_oid_arti_lote
                        AND ral.prod_oid_prod = p.oid_prod
                    ) premios,
                    mae_clien mc
              where sc.oid_soli_cabe = sp.soca_oid_soli_cabe
                AND des.val_oid = sp.prod_oid_prod
                AND des.attr_enti = 'MAE_PRODU'
                AND sp.prod_oid_prod = mae_produ.oid_prod
                AND sc.copa_oid_para_gene = inc.oid_para_gral
                AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                AND cod_tipo_soli IN ('SIFC', 'SIN', 'SINC')
                AND inc.num_conc = premios.num_conc
                AND sc.clie_oid_clie = tempmsg.clie_oid_clie
                AND sp.val_codi_vent_fict = premios.cod_prem(+)
                AND sc.grpr_oid_grup_proc in (3,4)
                AND sc.perd_oid_peri = lnOidPeriodo
                AND sc.clie_oid_clie = mc.oid_clie
                AND sp.espo_oid_esta_posi <> 2
             UNION
             SELECT oid_clie clie_oid_clie,
                    c.val_nom1 nom1,
                    c.val_nom2 nom2,
                    c.val_ape1 ape1,
                    c.val_ape2 ape2,
                    c.val_apel_casa apec,
                    c.cod_clie codigoCliente,
                    SUBSTR(UPPER(val_nomb),1,33) nombreConcurso,
                    a.num_conc codigoconcurso,
                    1 nivel,
                    COUNT(*) unidades,
                    0 codigoreclamo,
                    COUNT(*) totalpuntos,
                    'SI' gano,
                    'DE '||MIN(num_cupo)||' A '|| MAX(num_cupo) descripcionpremio,
                    'ENTREGADO' estadopremio
               FROM inc_cupon_elect a,
                    inc_concu_param_gener b,
                    mae_clien c,
                    (
                     SELECT cod_peri,
                            fec_proc
                       FROM bas_ctrl_fact
                      WHERE cod_peri IN (
                                         SELECT cod_peri
                                           FROM cra_perio cra,
                                                seg_perio_corpo corp
                                          WHERE cra.peri_oid_peri = corp.oid_peri
                                            AND cra.oid_peri = lnOidPeriodo
                                            AND ind_camp_act = 1
                                            AND sta_camp = 0
                                        )
                    ) d,
                    (
                     SELECT DISTINCT clie_oid_clie
                       FROM msg_tmp_pedid_clien
                      WHERE cod_tipo_soli = 'SOC'
                    ) tempmsg
              WHERE a.cod_clie = c.cod_clie
                AND a.num_conc = b.num_conc
                AND a.cod_peri_proc = d.cod_peri
                AND a.fec_fact = d.fec_proc
                AND c.oid_clie = tempmsg.clie_oid_clie
              GROUP BY oid_clie,c.val_nom1, c.val_nom2,
                       c.val_ape1,c.val_ape2, c.val_apel_casa,
                       c.cod_clie, val_nomb, a.num_conc
             ------ Nuevo HRG 08/04/2014 --
              UNION
              SELECT ele.clie_oid_clie, mc.val_nom1 nom1,
                    mc.val_nom2 nom2,
                    mc.val_ape1 ape1,
                    mc.val_ape2 ape2,
                    mc.val_apel_casa apec,
                    mc.cod_clie codigoCliente,
                    SUBSTR(UPPER(con.val_nomb),1,33) nombreConcurso,
                    con.num_conc codigoconcurso,
                    niv.num_nive nivel,
                    ele.cnt*art.num_unid unidades,
                    to_number(art.cod_vent_fict) codigoreclamo,
                    case when prem.tprm_oid_tipo_pion=2 then niv.num_cant_inic_punt
                            else niv.num_cant_fija_punt end totalpuntos,
                    '--' gano,
                    SUBSTR(UPPER(des.val_i18n),1,23) descripcionPremio,
                    'E.PENDIEN' estadopremio
              FROM  (SELECT DISTINCT clie_oid_clie
                        FROM msg_tmp_pedid_clien
                        WHERE cod_tipo_soli = 'SOC') tempmsg,
                        mae_clien mc,
                        (select clie_oid_clie,copa_oid_para_gral, panp_oid_para_nive_prem,num_prem,
                         count(1) cnt from inc_premi_elegi
                         where ind_pend=1
                         group by  clie_oid_clie,copa_oid_para_gral,
                                       panp_oid_para_nive_prem, num_prem) ele,
                         inc_concu_param_gener con, inc_param_gener_premi prem,
                         inc_param_nivel_premi niv, inc_premi_artic niv2,
                         inc_lote_premi_artic lot, inc_artic_lote art,
                          gen_i18n_sicc_pais des
                          WHERE ele.clie_oid_clie= tempmsg.clie_oid_clie
                          and ele.clie_oid_clie = mc.oid_clie
                          and ele.copa_oid_para_gral=con.oid_para_gral
                          and con.oid_para_gral = prem.copa_oid_para_gral
                          and niv.pagp_oid_para_gene_prem =prem.oid_para_gene_prem
                          and niv.oid_para_nive_prem =  ele.panp_oid_para_nive_prem
                          and niv2.panp_oid_para_nive_prem =niv.oid_para_nive_prem
                          and lot.prar_oid_prem_arti = niv2.oid_prem_arti
                          and niv.val_nive_sele=1
                          and con.ind_acti=1
                          and lot.num_prem = ele.num_prem
                          and art.lopa_oid_lote_prem_arti = lot.oid_lote_prem_arti
                          and des.val_oid = art.prod_oid_prod
                          and des.attr_enti = 'MAE_PRODU'
                    --
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONCU_CERRA: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONCU_CERRA;


/***************************************************************************
Descripcion : Proceso que genera mensajes de Concursos Vigentes
Fecha Creacion : 22/01/2013
Fecha Modificacion: 22/07/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_VIGEN
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     dato_vari_10,
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
)
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.clie_oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.codigoCliente, --'DV01'
            base.nombreConcurso, -- 'DV02'
            base.codigoConcurso, -- 'DV03'
            base.ptosAcumVtaDirecta, -- 'DV04'
            base.ptosCampVtaDirecta, -- 'DV05'
            base.ptosAcumBelcenter, -- 'DV06'
            base.ptosCampBelcenter,  -- 'DV07'
            base.ptosBonificados, -- 'DV08'
            base.ptosDevueltos, -- 'DV09'
            base.ptosTotal, -- 'DV10'
        NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT detalle.clie_oid_clie,
                    mc.val_nom1 nom1,
                    mc.val_nom2 nom2,
                    mc.val_ape1 ape1,
                    mc.val_ape2 ape2,
                    mc.val_apel_casa apec,
                    mc.cod_clie codigoCliente,
                    SUBSTR(UPPER(cpg.val_nomb),1,35) nombreConcurso,
                    cpg.num_conc codigoConcurso,
                    SUM(Ptos_acum_vtadir) ptosAcumVtaDirecta,
                    SUM(Ptos_camp_vtadir) ptosCampVtaDirecta,
                    SUM(Ptos_acum_belcenter) ptosAcumBelcenter,
                    SUM(Ptos_camp_belcenter) ptosCampBelcenter,
                    SUM(Ptos_bonificados) ptosBonificados,
                    SUM(Ptos_devueltos) ptosDevueltos,
                    SUM(Ptos_Total) ptosTotal
               FROM (
                     SELECT cc.clie_oid_clie,
                            cc.copa_oid_para_gral,
                            (CASE WHEN tmov_oid_tipo_movi = 1 AND (val_desc  NOT LIKE '%Venta Retail%'  AND val_desc  NOT LIKE '%TIENDA%' )
                                                        AND cc.perd_oid_peri < lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_acum_vtadir,
                            (CASE WHEN tmov_oid_tipo_movi = 1 AND (val_desc  NOT LIKE '%Venta Retail%'  AND val_desc  NOT LIKE '%TIENDA%' )
                                                        AND cc.perd_oid_peri = lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_camp_vtadir,
                            (CASE WHEN tmov_oid_tipo_movi = 1 AND (val_desc  LIKE '%Venta Retail%' or  val_desc  LIKE '%TIENDA%')
                                                        AND cc.perd_oid_peri < lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_acum_belcenter,
                            (CASE WHEN tmov_oid_tipo_movi = 1 AND (val_desc  LIKE '%Venta Retail%' or  val_desc  LIKE '%TIENDA%')
                                                        AND cc.perd_oid_peri = lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_camp_belcenter,
                            0 Ptos_bonificados,
                            (CASE WHEN tmov_oid_tipo_movi = 2  AND cc.perd_oid_peri <= lnOidPeriodo AND val_desc NOT LIKE '%Entrega de Premio%' THEN num_punt ELSE 0 END) Ptos_devueltos,
                            (CASE WHEN cc.perd_oid_peri <= lnOidPeriodo AND val_desc NOT LIKE '%Entrega de Premio%' THEN num_punt ELSE 0 END) Ptos_Total
                       FROM inc_cuent_corri_punto cc,
                            inc_concu_param_gener concu
                      WHERE cc.clie_oid_clie NOT IN (
                                                     SELECT inc_desca.clie_oid_clie
                                                       FROM inc_desca
                                                      WHERE inc_desca.copa_oid_para_gral = cc.copa_oid_para_gral
                                                    )
                        AND cc.clie_oid_clie IN (
                                                 SELECT distinct clie_oid_clie
                                                   FROM msg_tmp_pedid_clien
                                                  WHERE cod_tipo_soli = lsCodTipoSoli
           )
                        AND cc.copa_oid_para_gral = concu.oid_para_gral
                        AND concu.ind_acti=1              -- Nuevo HRG 12/03/2015
                        AND NVL(concu.ind_comu,1) = 1  	  -- Nuevo HRG 12/03/2015
	                      AND concu.ind_prog_punt <> 1  	  -- Nuevo HRG 12/03/2015

                    ) detalle,
                    inc_concu_param_gener cpg,
                    inc_param_gener_premi gen,
                    inc_concu_param_consu cons,
                    mae_clien mc
              WHERE cpg.oid_para_gral = detalle.copa_oid_para_gral
                AND cons.copa_oid_para_gral = cpg.oid_para_gral
                AND gen.copa_oid_para_gral = cpg.oid_para_gral
                AND detalle.clie_oid_clie = mc.oid_clie
                AND tprm_oid_tipo_pion<>1
                AND cpg.ind_acti = 1
                AND NVL(cpg.ind_comu,1) = 1   -- Nuevo HRG 12/03/2015
		            AND cpg.ind_prog_punt <> 1    -- Nuevo HRG 12/03/2015
                AND ((bcal_oid_base_calc in (1,2) AND
                        FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo) BETWEEN FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_desd) AND
                        FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_hast)) or
                       (bcal_oid_base_calc = 4 AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo) BETWEEN FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_desd) AND
                       GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_hast),NVL(cons.NUM_MINI_PEDI,0)-1)))
                AND cpg.num_conc NOT IN (SELECT num_conc FROM inc_param_cupon_elect WHERE ind_acti = 1)
              GROUP BY detalle.clie_oid_clie,
                    mc.val_nom1,
                    mc.val_nom2,
                    mc.val_ape1,
                    mc.val_ape2,
                    mc.val_apel_casa,
                    mc.cod_clie,
                    cpg.val_nomb,
                    cpg.num_conc
             HAVING SUM(Ptos_Total) > 0
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONCU_VIGEN: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONCU_VIGEN;


/***************************************************************************
Descripcion : Proceso que carga de la generacion de mensajes para Concurso
 							de los Sueños
Fecha Creacion : 30/07/2013
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_BANSU
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
     ind_esta_mens,
     clie_oid_clie,
     mens_oid_mens,
     modu_oid_modu_orig,
     dato_vari_01,
     dato_vari_02,
     dato_vari_03,
     dato_vari_04,
     dato_vari_05,
     dato_vari_06,
     dato_vari_07,
     dato_vari_08,
     dato_vari_09,
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
)
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.num_conc, --'DV01'
            base.val_nomb, -- 'DV02'
            base.toting, -- 'DV03'
            base.totingcmp, -- 'DV04'
            base.totpun, -- 'DV05'
            base.puncmp, -- 'DV06'
            base.punbonif,  -- 'DV07'
            base.totredim, -- 'DV08'
            base.porredim, -- 'DV09'
        NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT oid_clie,
                    num_conc,
                    SUBSTR(UPPER(val_nomb),1,35) val_nomb,
                    toting,  -- INGRESOS ACUMULADOS
                    totingcmp,   -- INGRESOS CAMPAÑA ACTUAL
                    NVL(
                        (
                         SELECT SUM(CASE WHEN UPPER(val_desc) NOT LIKE '%ENTREGA DE PREMIO%' THEN num_punt ELSE 0 END)
                           FROM inc_cuent_corri_punto cc
                          WHERE cc.copa_oid_para_gral = oid_conc
                            AND cc.clie_oid_clie = oid_clie
                        ),0
                       ) totpun,   -- PUNTOS ACUMULADOS
                    NVL(
                        (
                         SELECT SUM(CASE WHEN UPPER(val_desc) NOT LIKE '%ENTREGA DE PREMIO%' AND perd_oid_peri = lnOidPeriodo THEN num_punt ELSE 0 END)
                           FROM inc_cuent_corri_punto cc
                          WHERE cc.copa_oid_para_gral = oid_conc
                            AND cc.clie_oid_clie = oid_clie
                        ),0
                       ) puncmp,   -- PUNTOS CAMP. ACTUAL.
                    0 punbonif,   -- PUNTOS BONIFICADOS
                    NVL(
                        (
                         SELECT SUM(case when upper(val_desc) LIKE '%ENTREGA DE PREMIO%' then abs(num_punt) else 0 end)
                           FROM inc_cuent_corri_punto cc
                          where cc.copa_oid_para_gral = oid_conc
                            and cc.clie_oid_clie = oid_clie
                        ),0
                       ) totredim,   -- PUNTOS REDIMIDOS
                    NVL(
                        (
                         SELECT SUM(num_punt) porredim
                           FROM inc_cuent_corri_punto cc
                          where cc.copa_oid_para_gral = oid_conc
                            and cc.clie_oid_clie = oid_clie
                        ),0
                       ) porredim   -- PUNTOS POR REDIMIR
               FROM (
                     SELECT num_conc,
                            val_nomb,
                            rete.clie_oid_clie oid_clie,
                            copa_oid_para_gral oid_conc,
                            COUNT(*) as toting,
                            SUM(CASE WHEN redo.perd_oid_peri = lnOidPeriodo THEN 1 ELSE 0 END) totingcmp
                       FROM inc_clien_recte rete,
                            inc_clien_recdo redo,
                            (
                             SELECT gen.*
                               FROM inc_concu_param_gener gen,
                                    inc_param_gener_premi prem
                              WHERE copa_oid_para_gral = oid_para_gral
                                AND tprm_oid_tipo_pion = 1
                                AND ind_acti =1
                                AND bcal_oid_base_calc = 4
                            ) con,
                            (
                             SELECT distinct clie_oid_clie
                               FROM msg_tmp_pedid_clien
                              WHERE cod_tipo_soli = lsCodTipoSoli
                                AND perd_oid_peri = lnOidPeriodo
                            ) tempmsg
                      WHERE oid_clie_rete = clr3_oid_clie_rete
                        AND rete.copa_oid_para_gral = con.oid_para_gral
                        AND tempmsg.clie_oid_clie = rete.clie_oid_clie
                        AND oid_clie_rete IN (SELECT clr3_oid_clie_rete FROM inc_pedid_concu_recom WHERE clre_oid_clie_redo IS NULL)
                      GROUP BY num_conc, val_nomb, copa_oid_para_gral, rete.clie_oid_clie
           )
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONCU_BANSU: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONCU_BANSU;


/**************************************************************************************
Descripcion : Proceso que genera mensajes de Concurso Cupones Electronicos

Fecha Creacion : 26/09/2013
Fecha Modificacion: 09/04/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_CUPEL (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnPais                     NUMBER;
  lnMarca                    NUMBER;
  lnCanal                    NUMBER;
  lsCodMensaje               msg_mensa.cod_mens%TYPE;
  lsCodModulo                seg_modul.cod_modu%TYPE;
  lsCodTipoSoli              ped_tipo_solic.cod_tipo_soli%TYPE;
  lsCodConcurso              inc_concu_param_gener.num_conc%TYPE;

  lnOidModuloOrigen          NUMBER;
  --lnOidMensaje               NUMBER;
  lnOidMensajeAcumulado      NUMBER;
  lnOidMensajeCampana        NUMBER;


BEGIN
  -- Inicializar variables requeridas --
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MARCA, TIPO_RETORNO_OID);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_CANAL, TIPO_RETORNO_OID);
  --lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MENSAJE, TIPO_RETORNO_CODIGO);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_MODULO_ORIGEN, TIPO_RETORNO_CODIGO);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_TIPO_SOLICITUD, TIPO_RETORNO_CODIGO);
  lsCodConcurso := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_CONCURSO, TIPO_RETORNO_CODIGO);

  lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  --Mensaje Opciones Total Acumulado ( INCCEA )--
  BEGIN
    SELECT msg.mens_oid_mens
      INTO lnOidMensajeAcumulado
      FROM msg_mensa_campa msg
     WHERE msg.cod_mens = INC_CONCU_CUPEL_ACUMU
       AND msg.cam_proc = psCodigoPeriodo
       AND msg.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeAcumulado := '';
  END;

  --Mensaje Opciones Total Campaña ( INCCEC )--
  BEGIN
    SELECT msg.mens_oid_mens
      INTO lnOidMensajeCampana
      FROM msg_mensa_campa msg
     WHERE msg.cod_mens = INC_CONCU_CUPEL_CAMPA
       AND msg.cam_proc = psCodigoPeriodo
       AND msg.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeCampana := '';
  END;

  -- Insertamos Mensaje Opciones Total Acumulado ( INCCEA ) --
  IF lnOidMensajeAcumulado IS NOT NULL THEN
  INSERT INTO MSG_BUZON_MENSA
  (
    oid_buzo_mens, num_secu,
    ind_esta_mens,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie,
    val_nom2_clie,
    val_ape1_clie,
    val_ape2_clie,
    val_apel_casa_clie,
    dato_vari_01, dato_vari_02, dato_vari_03,
    num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeAcumulado,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, base.num_conc, base.num_opci,
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  acum.num_conc,
                  acum.num_opci
             FROM msg_tmp_pedid_clien pedc,
                  ped_solic_cabec sc,
                  mae_clien mc,
                  (
                   SELECT cue.num_conc,
                          cod_clie,
                          count(*) num_opci
                     FROM inc_cupon_elect cue
                    WHERE cue.num_conc = lsCodConcurso -- parametro
                          AND cod_peri_proc = psCodigoPeriodo -- parametro
                    GROUP BY cue.num_conc, cod_clie
                  ) acum
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
              AND pedc.oid_soli_cabe = sc.oid_soli_cabe
              AND pedc.clie_oid_clie = mc.oid_clie
              AND mc.cod_clie = acum.cod_clie
        ) base
  );
  END IF;

  -- Insertamos Mensaje Opciones Total Campaña ( INCCEC ) --
  IF lnOidMensajeCampana IS NOT NULL THEN
  INSERT INTO MSG_BUZON_MENSA
  (
    oid_buzo_mens, num_secu,
    ind_esta_mens,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie,
    val_nom2_clie,
    val_ape1_clie,
    val_ape2_clie,
    val_apel_casa_clie,
    dato_vari_01, dato_vari_02, dato_vari_03, dato_vari_04,
    num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeCampana,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, base.num_conc, base.cam_proc, base.num_opci,
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  camp.num_conc,
                  camp.cam_proc,
                  camp.num_opci
             FROM msg_tmp_pedid_clien pedc,
                  ped_solic_cabec sc,
                  mae_clien mc,
                  (
                   SELECT cue.num_conc,
                          cod_clie,
                          cod_peri_proc cam_proc,
                          count(*) num_opci
                     FROM inc_cupon_elect cue
                    WHERE cue.num_conc = lsCodConcurso -- parametro
                    GROUP BY cue.num_conc, cod_clie, cod_peri_proc
                  ) camp
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
              AND pedc.oid_soli_cabe = sc.oid_soli_cabe
              AND pedc.clie_oid_clie = mc.oid_clie
              AND mc.cod_clie = camp.cod_clie
        ) base
  );
  END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONCU_CUPEL: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONCU_CUPEL;


/***************************************************************************
Descripcion : Proceso que genera mensajes para Carta Web (la consultora debe
              tener 3 pedios Web y en su 4to pedido sin importar si es Web
              o no se le emite una Carta.
Fecha Creacion : 21/12/2010
Fecha Modificacion : 19/09/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_REUTI
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 ) IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  -- Logica de insercion --------------------------------------------
  INSERT INTO MSG_BUZON_MENSA
  (
   oid_buzo_mens, num_secu ,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensaje,  -- parametro
          lnOidModuloOrigen,  -- parametro
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- DV01
          base.cod_clie, -- DV02
          base.num_pedi_web, --DV03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT distinct pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  mc.cod_clie,
                  mcda.num_pedi_web
             FROM msg_tmp_pedid_clien pedc,
                  mae_clien mc,
                  mae_clien_datos_adici mcda
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND pedc.clie_oid_clie = mc.oid_clie
              AND mc.oid_clie = mcda.clie_oid_clie
              AND mcda.num_pedi_web = 3
          ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_REUTI: '||ls_sqlerrm);

END MSG_PR_GENER_MENSA_REUTI;


/*****************************************************************************
Descripcion : Proceso que genera mensajes para consultoras con Tercer Pedido
              No Consecutivo
Fecha Creacion : 09/05/201
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*****************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_3EPED_NOCON
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN
   -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;


  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,
   num_secu,
   ind_esta_mens,
   dato_vari_10,
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
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT msg_bume_seq.nextval,
          msg_bum2_seq.nextval,
          NULL,
          'TERCER PEDIDO NO CONSECUTIVO', -- dv10
          base.clie_oid_clie,
          lnOidMensaje,
          lnOidModuloOrigen,
          base.nom1,
          base.nom2,
          base.ape1,
          base.ape2,
          base.apec,
          base.nombre, -- dv01
          base.cod_prog, -- dv02
          base.camp_ini_ccc, -- dv03
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT pedc.clie_oid_clie,
                           mc.val_nom1 AS nom1,
                           mc.val_nom2 AS nom2,
                           mc.val_ape1 AS ape1,
                           mc.val_ape2 AS ape2,
                           mc.val_apel_casa AS apec,
                           mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                           nu.cod_prog,
                           nu.camp_ini_ccc
                      FROM msg_tmp_pedid_clien pedc,
                           mae_clien mc,
                           cup_consu_nueva nu,
                           cup_prog_nueva_cupon pn,
                           cup_consu_factu fc,
                           mae_clien_unida_admin mcua,
						               zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
                     WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                       AND pedc.clie_oid_clie = mc.oid_clie
                       AND nu.cod_pais = psCodigoPais
                       AND mc.cod_clie = nu.cod_cons
                       AND nu.camp_ini_ccc = CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(psCodigoPeriodo, -2)
                       AND nu.cod_prog = pn.cod_prog
                       AND nu.cod_cons = fc.cod_cons
                       AND fc.cod_pais = nu.cod_pais
                       AND fc.ind_cons = '1'
                       AND fc.cod_prog = nu.cod_prog
                       AND pn.cod_pais = nu.cod_pais
                       AND pn.ind_gene_mens = '1'
                       AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
                       AND zs.oid_secc = zta.zscc_oid_secc
                       AND zz.oid_zona = zs.zzon_oid_zona
                       AND zr.oid_regi = zz.zorg_oid_regi
                       AND mc.oid_clie = mcua.clie_oid_clie
                       AND mcua.ind_acti = '1'
                       AND (zr.cod_regi not in ( SELECT cod_regi FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND nu.cod_prog = prog_cod_prog)
                       OR zz.cod_zona not in ( SELECT cod_zona FROM nvs_param_descu_unadm WHERE ind_excl = '0' AND est_regi <> 9 AND COD_REGI = zr.cod_regi AND nu.cod_prog = prog_cod_prog))

          ) base
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_3EPED_NOCON: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_3EPED_NOCON;


/***************************************************************************
Descripcion : Proceso que carga de generar las metas y logros
Fecha Creacion : 09/02/2011
Fecha Modificacion : 04/07/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_CONSU_METAS
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )IS

 lnpais  NUMBER;
 lnmarca NUMBER;
 lncanal NUMBER;
 lnOidPeriodo NUMBER;
 lnOidMensajeMeta NUMBER;
 lnOidMensajeMeta2 NUMBER;--PARA LOS MENSAJES DESAGREGADOS
 lnOidMensajeMeta3 NUMBER;
 lnOidMensajeMeta4 NUMBER;

 lsFechaIni   VARCHAR2(8);
 lsFichaFinal VARCHAR2(8);
 anho         VARCHAR2(4);

 lsCodPeriodoAnt1 SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCodPeriodoAnt2 SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCodPeriodoAnt3 SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCodPeriodoAnt4 SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCodPeriodoAnt5 SEG_PERIO_CORPO.COD_PERI%TYPE;

 lnOidPeriodoAnt1 SEG_PERIO_CORPO.OID_PERI%TYPE;
 lnOidPeriodoAnt2 SEG_PERIO_CORPO.OID_PERI%TYPE;
 lnOidPeriodoAnt3 SEG_PERIO_CORPO.OID_PERI%TYPE;
 lnOidPeriodoAnt4 SEG_PERIO_CORPO.OID_PERI%TYPE;
 lnOidPeriodoAnt5 SEG_PERIO_CORPO.OID_PERI%TYPE;

 lnOidMensajeMetaP10 NUMBER;
 lnOidMensajeMetaP9 NUMBER;

 lsCodigoTipoSolicitud  PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE;
 lnOidModuloOrigen      NUMBER;
 lsIndMetas             BAS_PARAM_PAIS.VAL_PARA%TYPE;

 lnFaltaMeta            NUMBER;
 --version B py03 cambio : considerar si es nueva (con metas y sin metas)o establecida para mostrar ventas
  --VERSION B PY05 Consultora nuevas con metas o sin metas (no considerar establecidas)
 cursor
   c_consufactu(lsCodigoTipoSolicitud VARCHAR2,lsCodPeriodoAnt3 VARCHAR2,lnOidMensajeMeta NUMBER) IS
              SELECT DISTINCT
                        SC.CLIE_OID_CLIE,
                        MC.COD_CLIE,
                        MC.VAL_NOM1 AS NOM1,
                        MC.VAL_NOM2 AS NOM2,
                        MC.VAL_APE1 AS APE1,
                        MC.VAL_APE2 AS APE2,
                        MC.VAL_APEL_CASA AS APEC,
                        MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
                        MC.FEC_INGR AS FEC_INGR,
                        SC.PERD_OID_PERI,
                        (SELECT A.COD_PERI
                         FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                         WHERE A.OID_PERI = B.PERI_OID_PERI
                           AND B.OID_PERI = SC.PERD_OID_PERI
                           AND B.CANA_OID_CANA = C.OID_CANA
                           AND B.MARC_OID_MARC = D.OID_MARC
                           AND C.COD_CANA = 'VD'
                           AND D.COD_MARC = 'T') COD_PERI
                FROM
                --PED_SOLIC_CABEC SC,
                --PED_TIPO_SOLIC_PAIS TSP,
                --PED_TIPO_SOLIC TS,
                MAE_CLIEN MC,
                msg_tmp_pedid_clien SC
                WHERE
                 SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
                 AND MC.OID_CLIE = SC.CLIE_OID_CLIE
                 AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   );

 -- r_consufactu c_consufactu%ROWTYPE;

  TYPE consufacturecord IS RECORD (
    CLIE_OID_CLIE        mae_clien.oid_clie%TYPE,
    COD_CLIE             mae_clien.cod_clie%TYPE,
    NOM1 VARCHAR2(50),
    NOM2 VARCHAR2(50),
    APE1 VARCHAR2(50),
    APE2 VARCHAR2(50),
    APEC VARCHAR2(50),
    NOMBRE VARCHAR2(200),
    FEC_INGR  MAE_CLIEN.FEC_INGR%TYPE,
    PERD_OID_PERI CRA_PERIO.OID_PERI%TYPE,
    COD_PERI SEG_PERIO_CORPO.COD_PERI%TYPE
 );

TYPE consufactutype IS TABLE OF consufacturecord;
r_consufactu    consufactutype;



  lnCount NUMBER;
  lnOidCamIngreso NUMBER;

  lsCodigoIngreso   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoSgte1 SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoSgte2 SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoSgte3 SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnOidIngreso      SEG_PERIO_CORPO.OID_PERI%TYPE;
  lnOidPeriodoSgte1 SEG_PERIO_CORPO.OID_PERI%TYPE;
  lnOidPeriodoSgte2 SEG_PERIO_CORPO.OID_PERI%TYPE;
  lnOidPeriodoSgte3 SEG_PERIO_CORPO.OID_PERI%TYPE;

  lnVentaIngreso      NUMBER;
  lnVentaIngresoSgte1 NUMBER;
  lnVentaIngresoSgte2 NUMBER;
  lnVentaIngresoSgte3 NUMBER;

 --VERSION B PY05

CURSOR  c_consufactuNueva(lsCodigoTipoSolicitud VARCHAR2,lsCodPeriodoAnt3 VARCHAR2,lnOidMensajeMeta NUMBER) is

 SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            ntl.DES_TIPO_LOGR logrodescip,
      NCL.des_larg,
            NCL.IMP_LOGR logroplan,
            NCL.CMP_INIC logrocpi,
            NCL.CMP_FINA logrocpf,
            '1' ind_meta
   FROM msg_tmp_pedid_clien sc,
        mae_clien mc,
        nvs_consu_logro ncl,
        nvs_tipo_logro ntl
  WHERE sc.clie_oid_clie = mc.oid_clie
    AND mc.cod_clie = ncl.cod_clie
    AND ncl.cod_tipo_logr =  ntl.cod_tipo_logr
    AND psCodigoPeriodo BETWEEN ncl.cmp_inic AND ncl.cmp_fina
    AND sc.cod_tipo_soli = lsCodigoTipoSolicitud
    AND ncl.est_regi != '9'
    AND ncl.est_logr = '1'
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )

    UNION ALL

   SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            '' logrodescip,
            '' des_larg,
            0 logroplan,
            (
             SELECT A.COD_PERI
            FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
            WHERE A.OID_PERI = B.PERI_OID_PERI
            AND B.OID_PERI = e.perd_oid_peri
            AND B.CANA_OID_CANA = C.OID_CANA
            AND B.MARC_OID_MARC = D.OID_MARC
            AND C.COD_CANA = 'VD'
                AND D.COD_MARC = 'T'
            ) logrocpi,
            '' logrocpf,
            '0' ind_meta
        FROM msg_tmp_pedid_clien SC,
             mae_clien mc,
            mae_clien_datos_adici y,
            mae_clien_tipo_subti z,
            mae_clien_clasi w,
            mae_clien_prime_conta e
       WHERE sc.clie_oid_clie = mc.oid_clie
         AND mc.oid_clie = y.clie_oid_clie
         AND mc.oid_clie= z.clie_oid_clie
         AND mc.oid_clie= e.clie_oid_clie
         AND z.ticl_oid_tipo_clie=2
         AND z.oid_clie_tipo_subt= w.ctsu_oid_clie_tipo_subt
         AND w.tccl_oid_tipo_clasi=2010
         AND w.clas_oid_clas = 2008
         AND y.ind_acti=1
         AND Sc.cod_tipo_soli = lsCodigoTipoSolicitud
         AND NOT EXISTS(
                        SELECT NULL
                          FROM nvs_consu_logro z
                         WHERE z.cod_pais= psCodigoPais
                           AND z.cod_clie= mc.cod_clie
                           AND z.EST_REGI != '9'
                           AND z.est_logr = '1'
                           AND pscodigoPeriodo BETWEEN z.cmp_inic AND z.cmp_fina
        )
        AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   );

  TYPE consufactunuevarecord IS RECORD (
    CLIE_OID_CLIE        mae_clien.oid_clie%TYPE,
    --COD_CLIE             mae_clien.cod_clie%TYPE,
    NOM1 VARCHAR2(50),
    NOM2 VARCHAR2(50),
    APE1 VARCHAR2(50),
    APE2 VARCHAR2(50),
    APEC VARCHAR2(50),
    NOMBRE VARCHAR2(200),
    logrodescip VARCHAR2(50),
    des_larg VARCHAR2(100),
    logroplan   NUMBER,
    logrocpi SEG_PERIO_CORPO.COD_PERI%TYPE,
    logrocpf SEG_PERIO_CORPO.COD_PERI%TYPE,
    ind_meta VARCHAR2(1)
 );

TYPE consufactunuevatype IS TABLE OF consufactunuevarecord;
r_consufactuNueva    consufactunuevatype;

    --r_consufactuNueva c_consufactuNueva%ROWTYPE;

    lnAhorro            NUMBER;
    lnAhorroSgte1       NUMBER;
    lnAhorroSgte2       NUMBER;
    lnAhorroSgte3       NUMBER;

    lnAhorroCata        NUMBER;
    lnAhorroCataSgte1   NUMBER;
    lnAhorroCataSgte2   NUMBER;
    lnAhorroCataSgte3   NUMBER;


    lnAhorroRevista          NUMBER;
    lnAhorroRevistaSgte1     NUMBER;
    lnAhorroRevistaSgte2     NUMBER;
    lnAhorroRevistaSgte3     NUMBER;




  --VERSION B PY08  consultoras sin metas y establecidas
cursor  c_consufactuEstab(lsCodigoTipoSolicitud VARCHAR2,lsCodPeriodoAnt3 VARCHAR2,lnOidMensajeMeta NUMBER) IS

  SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE
   FROM msg_tmp_pedid_clien SC,
        mae_clien mc
  WHERE SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
   AND lnOidMensajeMeta IN (
                            SELECT column_value
                              FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                           )
    AND EXISTS (
                 SELECT NULL
                     FROM mae_clien_datos_adici y2,
                        mae_clien_tipo_subti z2,
                        mae_clien_clasi w2,
                        mae_clien_prime_conta e2
                     WHERE mc.oid_clie = y2.clie_oid_clie
                      and mc.oid_clie= z2.clie_oid_clie
                      and mc.oid_clie= e2.clie_oid_clie
                      and z2.ticl_oid_tipo_clie=2
                      and z2.oid_clie_tipo_subt= w2.ctsu_oid_clie_tipo_subt
                    and w2.tccl_oid_tipo_clasi=2011
                    and w2.clas_oid_clas = 2009
                    and y2.ind_acti=1
               )

       UNION

        SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE
       FROM msg_tmp_pedid_clien SC,
            mae_clien mc
      WHERE SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND NOT EXISTS(
                       SELECT NULL
                         FROM nvs_consu_logro z
                        WHERE z.cod_pais= psCodigoPais
                          AND z.cod_clie= mc.cod_clie
                          AND z.EST_REGI != '9'
                          AND z.est_logr = '1'
                          AND pscodigoPeriodo BETWEEN z.cmp_inic AND z.cmp_fina
                       )
        AND pscodigoPeriodo >  NVL((
                                     SELECT MAX( f.cmp_fina )
                                       FROM nvs_consu_logro f
                                      WHERE f.cod_pais= psCodigoPais
                                        AND f.cod_clie= mc.cod_clie
                                        AND f.est_regi != '9'
                                        AND f.est_logr = '1'
                                    ),pscodigoperiodo
     )
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
               );

  TYPE consufactuestabrecord IS RECORD (
    CLIE_OID_CLIE        mae_clien.oid_clie%TYPE,
    --COD_CLIE             mae_clien.cod_clie%TYPE,
    NOM1 VARCHAR2(50),
    NOM2 VARCHAR2(50),
    APE1 VARCHAR2(50),
    APE2 VARCHAR2(50),
    APEC VARCHAR2(50),
    NOMBRE VARCHAR2(200)

 );

TYPE consufactuestabtype IS TABLE OF consufactuestabrecord;
r_consufactuEstab    consufactuestabtype;

    lnVentaReal         NUMBER;
    lnVentaRealAnt1     NUMBER;
    lnVentaRealAnt2     NUMBER;
    lnVentaRealAnt3     NUMBER;
    lnVentaRealAnt4     NUMBER;
    lnVentaRealAnt5     NUMBER;
    lnSuma              NUMBER;
    lnNivel             NUMBER;
    lnPromedio          NUMBER;

    lnAhorroCataReal         NUMBER;
    lnAhorroCataRealAnt1     NUMBER;
    lnAhorroCataRealAnt2     NUMBER;
    lnAhorroCataRealAnt3     NUMBER;
    lnAhorroCataRealAnt4     NUMBER;
    lnAhorroCataRealAnt5     NUMBER;

    lnAhorroRevistaReal         NUMBER;
    lnAhorroRevistaRealAnt1     NUMBER;
    lnAhorroRevistaRealAnt2     NUMBER;
    lnAhorroRevistaRealAnt3     NUMBER;
    lnAhorroRevistaRealAnt4     NUMBER;
    lnAhorroRevistaRealAnt5     NUMBER;

    lnAhorroTotalReal         NUMBER;
    lnAhorroTotalRealAnt1     NUMBER;
    lnAhorroTotalRealAnt2     NUMBER;
    lnAhorroTotalRealAnt3     NUMBER;
    lnAhorroTotalRealAnt4     NUMBER;
    lnAhorroTotalRealAnt5     NUMBER;

  /* INI SA Optimizacion Gestor de Mensajes */
  TYPE consuLogroMetarecord IS RECORD (
    CLIE_OID_CLIE        mae_clien.oid_clie%TYPE,
    NOM1 VARCHAR2(50),
    NOM2 VARCHAR2(50),
    APE1 VARCHAR2(50),
    APE2 VARCHAR2(50),
    APEC VARCHAR2(50),
    NOMBRE VARCHAR2(200),
    DES_TIPO_LOGR  NVS_TIPO_LOGRO.DES_TIPO_LOGR%TYPE,
    IMP_LOGR       NVS_CONSU_LOGRO.IMP_LOGR%TYPE,
    CMP_INIC       NVS_CONSU_LOGRO.CMP_INIC%TYPE,
    CMP_FINA       NVS_CONSU_LOGRO.CMP_FINA%TYPE,
    AHORROACUM     NUMBER(12,2)
  );

  TYPE consuLogroMetatype IS TABLE OF consuLogroMetarecord;
  r_consuLogroMeta    consuLogroMetatype;

  cursor
   c_consuLogroMeta(lsCodigoTipoSolicitud VARCHAR2, lnOidMensajeMeta NUMBER) IS
     SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            ntl.DES_TIPO_LOGR logrodescip,
            NCL.IMP_LOGR logroplan,
            NCL.CMP_INIC logrocpi,
            NCL.CMP_FINA logrocpf,
            MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,NCL.CMP_INIC,-1,mc.OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,NCL.IMP_LOGR,NCL.CMP_FINA) ahorroAcum
        FROM
            msg_tmp_pedid_clien SC,
            MAE_CLIEN MC,
            NVS_CONSU_LOGRO NCL,
            NVS_TIPO_LOGRO NTL
         WHERE
            SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
            AND mc.COD_CLIE = ncl.COD_CLIE
            AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
            and psCodigoPeriodo>= NCL.CMP_INIC
            and psCodigoPeriodo <= NCL.CMP_FINA
            AND NCL.EST_REGI != '9'
            AND NCL.EST_LOGR = '1' -- Nuevo
             AND(
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') = NCL.CMP_FINA
               );

  TYPE consuNoLogroMetarecord IS RECORD (
    CLIE_OID_CLIE        mae_clien.oid_clie%TYPE,
    NOM1 VARCHAR2(50),
    NOM2 VARCHAR2(50),
    APE1 VARCHAR2(50),
    APE2 VARCHAR2(50),
    APEC VARCHAR2(50),
    NOMBRE VARCHAR2(200),
    DES_TIPO_LOGR  NVS_TIPO_LOGRO.DES_TIPO_LOGR%TYPE,
    IMP_LOGR       NVS_CONSU_LOGRO.IMP_LOGR%TYPE,
    CMP_INIC       NVS_CONSU_LOGRO.CMP_INIC%TYPE,
    CMP_FINA       NVS_CONSU_LOGRO.CMP_FINA%TYPE,
    AHORROACUM    NUMBER(12,2)
 );

  TYPE consuNoLogroMetatype IS TABLE OF consuNoLogroMetarecord;
  r_consuNoLogroMeta    consuNoLogroMetatype;

CURSOR c_consuNoLogroMeta( lsCodigoTipoSolicitud VARCHAR2, lnOidMensajeMeta NUMBER) IS
    SELECT soca.clie_oid_clie,
           MAX( pedc.val_nom1 ) nom1,
           MAX( pedc.val_nom2 ) nom2,
           MAX( pedc.val_ape1 ) ape1,
           MAX( pedc.val_ape2 ) ape2,
           MAX( pedc.val_apel_casa ) apec,
           MAX( pedc.val_nom1 || ' ' || pedc.val_nom2 || ' ' || pedc.val_ape1 || ' ' || pedc.val_ape2 ) nombre,
           MAX( pedc.des_tipo_logr ) des_tipo_logr,
           MAX( pedc.imp_logr ) imp_logr,
           MAX( pedc.cmp_inic ) cmp_inic,
           MAX( pedc.cmp_fina ) cmp_fina,
           SUM( soca.val_gana_tota_loca ) ahorroacum
      FROM (
            SELECT clie.oid_clie,
                   clie.val_ape1,
                   clie.val_ape2,
                   clie.val_apel_casa,
                   clie.val_nom1,
                   clie.val_nom2,
                   nvtl.des_tipo_logr,
                   nvcg.imp_logr,
                   nvcg.cmp_inic,
                   nvcg.cmp_fina
              FROM (
                    SELECT clie_oid_clie
                      FROM msg_tmp_pedid_clien
                     WHERE cod_tipo_soli = lsCodigoTipoSolicitud
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                                                  FROM TABLE(MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
                     GROUP BY clie_oid_clie
                   ) spedc,
                   nvs_consu_logro nvcg,
                   nvs_tipo_logro nvtl,
                   mae_clien clie
             WHERE spedc.clie_oid_clie = clie.oid_clie
               AND clie.cod_clie = nvcg.cod_clie
               AND nvcg.cod_tipo_logr = nvtl.cod_tipo_logr
               AND nvcg.est_regi != '9'
               AND nvcg.est_logr = '1' -- Nuevo
               AND nvcg.cmp_fina = psCodigoPeriodo
           ) pedc,
           ped_solic_cabec soca,
           ped_tipo_solic_pais tspa,
           ped_tipo_solic tsol
     WHERE pedc.oid_clie = soca.clie_oid_clie
       AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
       AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
       AND tsol.cod_tipo_soli = lsCodigoTipoSolicitud
       AND soca.perd_oid_peri BETWEEN lnOidPeriodoAnt3 AND lnOidPeriodo
     GROUP BY soca.clie_oid_clie
    HAVING SUM( NVL( soca.val_gana_tota_loca ,0 ) ) < MAX( pedc.imp_logr );

BEGIN
 lnmarca:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MARCA,TIPO_RETORNO_OID);
 lncanal:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_CANAL,TIPO_RETORNO_OID);
 lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(
                                     psCodigoPeriodo,
                                     lnmarca,
                                     lncanal);

  lsCodigoTipoSolicitud:=MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

  SELECT MOD.OID_MODU
    INTO lnOidModuloOrigen
  FROM SEG_MODUL MOD
  WHERE MOD.COD_MODU=MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCondigoIdent,TIPO_DATO_MODULO_ORIGEN,TIPO_RETORNO_CODIGO);

  anho:= SUBSTR(psCodigoPeriodo, 1, 4);

      --obteniendo el rango d efechas del peridood
     SELECT TO_CHAR( x.fec_inic, 'yyyyMMdd' ),
            TO_CHAR( x.fec_fina, 'yyyyMMdd' )
          INTO lsFechaIni,lsFichaFinal
       FROM cra_perio x
      WHERE x.oid_peri = lnOidPeriodo;

    SELECT VAL_PARA
      INTO lsIndMetas
    FROM BAS_PARAM_PAIS
    WHERE COD_PAIS=psCodigoPais
     AND COD_SIST= 'MSG'
     AND COD_PARA ='001';
     --AND NOM_PARA = 'indmsgmetlog'


/*
  -------------------------------------------------------------------------
  SECCION 01: Mensaje de cumpleaños ( PY101 )
  -------------------------------------------------------------------------
*/

       begin
         SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_CUMPLE;
       exception
        when others then
          lnOidMensajeMeta:='';
       end;


          INSERT INTO MSG_BUZON_MENSA
        ( oid_buzo_mens,  num_secu ,
          dato_vari_10,  dato_vari_11,  dato_vari_12,  dato_vari_13,  dato_vari_14,  dato_vari_15,  dato_vari_16,  dato_vari_17,  dato_vari_18,  dato_vari_19,  dato_vari_20,
          ind_esta_mens, clie_oid_clie, mens_oid_mens, modu_oid_modu_orig, val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
          dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,  dato_vari_06,  dato_vari_07,  dato_vari_08,  dato_vari_09,
          num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                'DV10','DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                NULL, BASE.CLIE_OID_CLIE,
                lnOidMensajeMeta,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                BASE.NOMBRE,
                to_char(BASE.FEC_NACI,'dd/MM/yyyy'),
                'DV03','DV04','DV05', 'DV06', 'DV07','DV08','DV09',
                NULL, SYSDATE, NULL, 1, NULL, 1
          FROM
                (SELECT DISTINCT SC.CLIE_OID_CLIE,
                        MC.VAL_NOM1 AS NOM1,
                        MC.VAL_NOM2 AS NOM2,
                        MC.VAL_APE1 AS APE1,
                        MC.VAL_APE2 AS APE2,
                        MC.VAL_APEL_CASA AS APEC,
                        MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
                        MCDA.FEC_NACI AS FEC_NACI
                FROM
                --PED_SOLIC_CABEC SC,
                --PED_TIPO_SOLIC_PAIS TSP,
                --PED_TIPO_SOLIC TS,
                msg_tmp_pedid_clien SC,
                MAE_CLIEN MC,
                MAE_CLIEN_DATOS_ADICI MCDA
                WHERE
                    -- SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                    --AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
                AND SC.PERD_OID_PERI = lnOidPeriodo
                AND MC.OID_CLIE = SC.CLIE_OID_CLIE
                AND MCDA.CLIE_OID_CLIE = SC.CLIE_OID_CLIE
                AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
                AND(--QUE CUMPLE ANHOS SE ENCUENTRE EN EL RANGO DE FECHAS DE PERIODO
                 to_number(anho ||  to_char(MCDA.FEC_NACI,'MMdd')) >= to_number(lsFechaIni)
                      and to_number(anho ||  to_char(MCDA.FEC_NACI,'MMdd')) <= to_number(lsFichaFinal)
                )
            ) BASE);


 -- (2) SECCION ANIVERSARIO MENSAJE = PY102

         --OBTENIENDO OID MENSAJE ANIVERSARIO
        begin
         SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_ANIVER;
        exception
         when others then
           lnOidMensajeMeta:='';
        end;


          INSERT INTO MSG_BUZON_MENSA
        ( OID_BUZO_MENS,  NUM_SECU ,
          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                'DV10','DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                NULL, BASE.CLIE_OID_CLIE,
                lnOidMensajeMeta,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                BASE.NOMBRE,
                TO_NUMBER(TO_CHAR(SYSDATE,'yyyy')) - TO_NUMBER(TO_CHAR(BASE.FEC_INGR,'yyyy')),
                to_char(BASE.FEC_INGR,'dd/MM/yyyy'),--'DV03'
                'DV04','DV05', 'DV06', 'DV07','DV08','DV09',
                NULL, SYSDATE, NULL, 1, NULL, 1
          FROM
                (SELECT DISTINCT SC.CLIE_OID_CLIE,
                        MC.VAL_NOM1 AS NOM1,
                        MC.VAL_NOM2 AS NOM2,
                        MC.VAL_APE1 AS APE1,
                        MC.VAL_APE2 AS APE2,
                        MC.VAL_APEL_CASA AS APEC,
                        MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
                        MC.FEC_INGR AS FEC_INGR
                FROM
                --PED_SOLIC_CABEC SC,
                --PED_TIPO_SOLIC_PAIS TSP,
                --PED_TIPO_SOLIC TS,
                msg_tmp_pedid_clien SC,
                MAE_CLIEN MC
                WHERE
                  SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
                AND SC.PERD_OID_PERI = lnOidPeriodo
                AND MC.OID_CLIE = SC.CLIE_OID_CLIE
                AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
                AND(--QUE ANIVERSARIO SE ENCUENTRE EN EL RANGO DE FECHAS DE PERIODO
                 to_number(anho ||  to_char(MC.FEC_INGR,'MMdd')) >= to_number(lsFechaIni)
                      and to_number(anho ||  to_char(MC.FEC_INGR,'MMdd')) <= to_number(lsFichaFinal)
                )
                AND (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy')) - TO_NUMBER(TO_CHAR(MC.FEC_INGR,'yyyy'))) > 0
            ) BASE);

/*
  -------------------------------------------------------------------------
  SECCION 07: Historia de ventas de consultoras que pasan pedido ( PY103 )
  -------------------------------------------------------------------------
*/

-- Definir variables de trabajo
  lnpais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lsCodPeriodoAnt1:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-1);
  lsCodPeriodoAnt2:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-2);
  lsCodPeriodoAnt3:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-3);
--
  lnOidPeriodoAnt1:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt1,lnmarca,lncanal);
  lnOidPeriodoAnt2:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt2,lnmarca,lncanal);
  lnOidPeriodoAnt3:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt3,lnmarca,lncanal);

  BEGIN
  SELECT tmsg.oid_mens
    INTO lnoidmensajemeta
       FROM msg_mensa tmsg
   WHERE tmsg.cod_mens = PY1_MENSAJE_VENTA
     AND PY1_MENSAJE_VENTA IN (
                               SELECT column_value
                                 FROM TABLE( MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_RANGO_DATO( psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID ) )
                              );
  EXCEPTION
  WHEN OTHERS THEN lnoidmensajemeta:='';
  END;

IF( lsIndMetas = '0' ) AND lnOidMensajeMeta IS NOT NULL THEN

          INSERT INTO MSG_BUZON_MENSA
     (
      oid_buzo_mens,  num_secu ,
      ind_esta_mens, clie_oid_clie,
      mens_oid_mens, modu_oid_modu_orig,
      val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
      dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,  dato_vari_06,  dato_vari_07,  dato_vari_08,  dato_vari_09,
      num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
        )
     (
      SELECT msg_bume_seq.nextval oid_buzo_mens,
             msg_bum2_seq.nextval num_secu,
             NULL ind_esta_mens,
             base.clie_oid_clie,
             lnOidMensajeMeta mens_oid_mens,
             lnOidModuloOrigen modu_oid_modu_orig,
             clie.val_nom1 val_nom1_clie,
             clie.val_nom2 val_nom2_clie,
             clie.val_ape1 val_ape1_clie,
             clie.val_ape2 val_ape2_clie,
             clie.val_apel_casa val_apel_casa_clie,
             --
             clie.val_nom1 || ' ' || clie.val_nom2 || ' ' || clie.val_ape1 || ' ' || clie.val_ape2 dato_vari_01,
             base.val_prec_cata_tota_loca_per0 dato_vari_02,
             base.val_prec_cata_tota_loca_per1 dato_vari_03,
             base.val_prec_cata_tota_loca_per2 dato_vari_04,
             base.val_prec_cata_tota_loca_per3 dato_vari_05,
             'C'|| SUBSTR( psCodigoPeriodo, 5, 2 ) dato_vari_06,
             'C'|| SUBSTR( lsCodPeriodoAnt1, 5, 2 ) dato_vari_07,
             'C'|| SUBSTR( lsCodPeriodoAnt2, 5, 2 ) dato_vari_08,
             'C'|| SUBSTR( lsCodPeriodoAnt3, 5, 2 ) dato_vari_09,
             --
             NULL num_lote_impr,
             SYSDATE fec_grab,
             NULL fec_impr,
             1 ind_list_cons,
             NULL peri_oid_peri,
             1 ind_acti
        FROM (
              SELECT temp.clie_oid_clie,
                     SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodo     THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_prec_cata_tota_loca_per0,
                     SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt1 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_prec_cata_tota_loca_per1,
                     SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt2 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_prec_cata_tota_loca_per2,
                     SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt3 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_prec_cata_tota_loca_per3
        FROM (
                      SELECT clie_oid_clie
                        FROM msg_tmp_pedid_clien
                       WHERE cod_tipo_soli = lsCodigoTipoSolicitud
                       GROUP BY clie_oid_clie
                     ) temp,
                     ped_solic_cabec soca,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol
               WHERE temp.clie_oid_clie = soca.clie_oid_clie
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND tsol.cod_tipo_soli = lsCodigoTipoSolicitud
                 AND soca.perd_oid_peri BETWEEN lnOidPeriodoAnt3 AND lnOidPeriodo
               GROUP BY temp.clie_oid_clie
             ) base,
             mae_clien clie
       WHERE base.clie_oid_clie = clie.oid_clie
         );
    ELSE
      --VERSION B
      --NUEVAS CON METAS O SIN METAS /ESTABLECIDAS
      OPEN c_consufactu(lsCodigoTipoSolicitud,lsCodPeriodoAnt3, lnOidMensajeMeta);
            LOOP
            FETCH c_consufactu  BULK COLLECT INTO r_consufactu  LIMIT W_FILAS;
            --EXIT WHEN c_consufactu%NOTFOUND;
             --   BEGIN
            IF  r_consufactu.COUNT > 0 THEN
              FOR i IN r_consufactu.FIRST..r_consufactu.LAST
              LOOP

                  --SI ES NUEVA CON LOGRO EXISTE EN TABLA DE NEUVAS CON METAS
                  select count(1) into lnCount
                  from  NVS_CONSU_LOGRO ncl
                  where  ncl.COD_CLIE = r_consufactu(i).cod_clie
                   and psCodigoPeriodo >= NCL.CMP_INIC
                   and psCodigoPeriodo <= NCL.CMP_FINA
                   and r_consufactu(i).cod_peri >= NCL.CMP_INIC
                   and r_consufactu(i).cod_peri <= NCL.CMP_FINA
                   AND ncl.EST_REGI != '9'
                   AND ncl.est_logr = '1' -- Nuevo
                   ;

                if(lnCount =  0) then
                   --nuevas sin metas
                      begin

                        select e.perd_oid_peri--campanha de ingreso
                                   into lnOidCamIngreso
                        from mae_clien a,
                             mae_clien_datos_adici b,
                             mae_clien_tipo_subti c,
                             mae_clien_clasi d,
                             mae_clien_prime_conta e
                        where a.oid_clie = r_consufactu(i).clie_oid_clie
                          and a.oid_clie = b.clie_oid_clie
                          and a.oid_clie= c.clie_oid_clie
                          and a.oid_clie= e.clie_oid_clie
                          and c.ticl_oid_tipo_clie=2
                          and c.oid_clie_tipo_subt= d.ctsu_oid_clie_tipo_subt
                          and d.tccl_oid_tipo_clasi=2010
                          and d.clas_oid_clas = 2008
                          and b.ind_acti=1
                        AND(--periodo de ingreso esta en el rango
                         (SELECT A.COD_PERI
                           FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                         WHERE A.OID_PERI = B.PERI_OID_PERI
                           AND B.OID_PERI = e.perd_oid_peri
                           AND B.CANA_OID_CANA = C.OID_CANA
                           AND B.MARC_OID_MARC = D.OID_MARC
                           AND C.COD_CANA = 'VD'
                           AND D.COD_MARC = 'T') >= lsCodPeriodoAnt3
                         AND
                          (SELECT A.COD_PERI
                           FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                           WHERE A.OID_PERI = B.PERI_OID_PERI
                           AND B.OID_PERI = e.perd_oid_peri
                           AND B.CANA_OID_CANA = C.OID_CANA
                           AND B.MARC_OID_MARC = D.OID_MARC
                           AND C.COD_CANA = 'VD'
                           AND D.COD_MARC = 'T') <= psCodigoPeriodo
                        );

                       --se inserta nuevas sin meta
                       --campanha de ingreso consultora

                     SELECT A.COD_PERI into lsCodigoIngreso
                     FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                     WHERE A.OID_PERI = B.PERI_OID_PERI
                       AND B.OID_PERI = lnOidCamIngreso
                       AND B.CANA_OID_CANA = C.OID_CANA
                       AND B.MARC_OID_MARC = D.OID_MARC
                       AND C.COD_CANA = 'VD'
                       AND D.COD_MARC = 'T';

                       --nuevas con metas
                         lsCodPeriodoSgte1:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,1);
                         lsCodPeriodoSgte2:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,2);
                         lsCodPeriodoSgte3:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,3);

                         lnOidIngreso     := lnOidCamIngreso;--Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoIngreso,lnmarca,lncanal);
                         lnOidPeriodoSgte1:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte1,lnmarca,lncanal);
                         lnOidPeriodoSgte2:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte2,lnmarca,lncanal);
                         lnOidPeriodoSgte3:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte3,lnmarca,lncanal);

                         if(lsCodigoIngreso = psCodigoPeriodo) then
                            lnVentaIngreso := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidIngreso);
                         else
                            lnVentaIngreso :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidIngreso);
                         end if;

                         if(lsCodPeriodoSgte1 = psCodigoPeriodo) then
                            lnVentaIngresoSgte1 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte1);
                         else
                            lnVentaIngresoSgte1 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte1);
                         end if;

                         if(lsCodPeriodoSgte2 = psCodigoPeriodo) then
                            lnVentaIngresoSgte2 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte2);
                         else
                            lnVentaIngresoSgte2 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte2);
                         end if;

                         if(lsCodPeriodoSgte3 = psCodigoPeriodo) then
                            lnVentaIngresoSgte3 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte3);
                         else
                            lnVentaIngresoSgte3 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte3);
                         end if;


                           INSERT INTO MSG_BUZON_MENSA
                            ( OID_BUZO_MENS,  NUM_SECU ,
                              DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                              IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                              DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                              NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                            )
                            VALUES( MSG_BUME_SEQ.NEXTVAL,
                                     MSG_BUM2_SEQ.NEXTVAL,
                                    '</GRAFICN>',--'DV10',
                                    'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                    NULL,
                                    r_consufactu(i).CLIE_OID_CLIE,
                                    lnOidMensajeMeta,
                                    lnOidModuloOrigen,
                                    r_consufactu(i).NOM1, r_consufactu(i).NOM2, r_consufactu(i).APE1, r_consufactu(i).APE2, r_consufactu(i).APEC,
                                    '<GRAFICN>' --dv01
                                    ,lnVentaIngreso--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidIngreso)
                                    ,lnVentaIngresoSgte1--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte1)
                                    ,lnVentaIngresoSgte2 --MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte2)
                                    ,lnVentaIngresoSgte3--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte3)
                                    ,'C'|| SUBSTR(lsCodigoIngreso,5,2)--'DV06'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte1,5,2)--'DV07'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte2,5,2)--'DV08'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte3,5,2)--'DV09',
                                    ,NULL, SYSDATE, NULL, 1, NULL, 1);


                        exception
                         when others then
                           --es establecida
                           lsCodPeriodoAnt4:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-4);
                           lsCodPeriodoAnt5:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-5);


                           lnOidPeriodoAnt4:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt4,lnmarca,lncanal);
                           lnOidPeriodoAnt5:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnt5,lnmarca,lncanal);

                          begin
                             SELECT TMSG.OID_MENS INTO lnOidMensajeMeta2
                             FROM MSG_MENSA TMSG
                             WHERE TMSG.COD_MENS = PY1_MENSAJE_VENTA2;



                           INSERT INTO MSG_BUZON_MENSA
                            ( OID_BUZO_MENS,  NUM_SECU ,
                              DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                              IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                              DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                              NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                            )
                            VALUES( MSG_BUME_SEQ.NEXTVAL,
                                    MSG_BUM2_SEQ.NEXTVAL,
                                    'C'|| SUBSTR(lsCodPeriodoAnt4,5,2),--'DV10',
                                    'C'|| SUBSTR(lsCodPeriodoAnt5,5,2)--'DV11',
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoAnt4)--'DV12',
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoAnt5)--'DV13',
                                    ,'</GRAFICE>'--'DV14',
                                    ,'DV15', 'DV16','DV17','DV18','DV19','DV20',
                                    NULL,
                                    r_consufactu(i).CLIE_OID_CLIE,
                                    lnOidMensajeMeta2,
                                    lnOidModuloOrigen,
                                    r_consufactu(i).NOM1,
                                    r_consufactu(i).NOM2, r_consufactu(i).APE1, r_consufactu(i).APE2, r_consufactu(i).APEC,
                                    '<GRAFICE>' --dv01
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodo)
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoAnt1)
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoAnt2)
                                    ,MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoAnt3)
                                    ,'C'|| SUBSTR(psCodigoPeriodo,5,2)--'DV06'
                                    ,'C'|| SUBSTR(lsCodPeriodoAnt1,5,2)--'DV07'
                                    ,'C'|| SUBSTR(lsCodPeriodoAnt2,5,2)--'DV08'
                                    ,'C'|| SUBSTR(lsCodPeriodoAnt3,5,2)--'DV09',
                                    ,NULL, SYSDATE, NULL, 1, NULL, 1);
                            exception
                             when others then
                               lnOidMensajeMeta2:='';
                            end;

                          END;



                 else
                        --campanha de ingreso consultora
                        select CMP_INIC into lsCodigoIngreso
                        from NVS_CONSU_LOGRO ncl
                        where  ncl.COD_CLIE = r_consufactu(i).cod_clie
                         and psCodigoPeriodo >= NCL.CMP_INIC
                         and psCodigoPeriodo <= NCL.CMP_FINA
                         and r_consufactu(i).cod_peri >= NCL.CMP_INIC
                         and r_consufactu(i).cod_peri <= NCL.CMP_FINA
                         AND ncl.EST_REGI != '9'
                         and ncl.est_logr = '1' -- Nuevo
                         ;

                       --nuevas con metas
                         lsCodPeriodoSgte1:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,1);
                         lsCodPeriodoSgte2:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,2);
                         lsCodPeriodoSgte3:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,3);

                         lnOidIngreso     := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoIngreso,lnmarca,lncanal);
                         lnOidPeriodoSgte1:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte1,lnmarca,lncanal);
                         lnOidPeriodoSgte2:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte2,lnmarca,lncanal);
                         lnOidPeriodoSgte3:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte3,lnmarca,lncanal);

                         if(lsCodigoIngreso = psCodigoPeriodo)  then
                            lnVentaIngreso := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidIngreso);
                         else
                            lnVentaIngreso :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidIngreso);
                         end if;

                         if(lsCodPeriodoSgte1 = psCodigoPeriodo) then
                            lnVentaIngresoSgte1 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte1);
                         else
                            lnVentaIngresoSgte1 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte1);
                         end if;

                         if(lsCodPeriodoSgte2 = psCodigoPeriodo) then
                            lnVentaIngresoSgte2 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte2);
                         else
                            lnVentaIngresoSgte2 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte2);
                         end if;

                         if(lsCodPeriodoSgte3 = psCodigoPeriodo) then
                            lnVentaIngresoSgte3 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte3);
                         else
                            lnVentaIngresoSgte3 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte3);
                         end if;



                           INSERT INTO MSG_BUZON_MENSA
                            ( OID_BUZO_MENS,  NUM_SECU ,
                              DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                              IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                              DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                              NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                            )
                            VALUES( MSG_BUME_SEQ.NEXTVAL,
                                     MSG_BUM2_SEQ.NEXTVAL,
                                    '</GRAFICN>',--'DV10',
                                    'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                    NULL,
                                    r_consufactu(i).CLIE_OID_CLIE,
                                    lnOidMensajeMeta,
                                    lnOidModuloOrigen,
                                    r_consufactu(i).NOM1, r_consufactu(i).NOM2, r_consufactu(i).APE1, r_consufactu(i).APE2, r_consufactu(i).APEC,
                                    '<GRAFICN>' --dv01
                                    ,lnVentaIngreso--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidIngreso)
                                    ,lnVentaIngresoSgte1--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte1)
                                    ,lnVentaIngresoSgte2 --MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte2)
                                    ,lnVentaIngresoSgte3--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte3)
                                    ,'C'|| SUBSTR(lsCodigoIngreso,5,2)--'DV06'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte1,5,2)--'DV07'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte2,5,2)--'DV08'
                                    ,'C'|| SUBSTR(lsCodPeriodoSgte3,5,2)--'DV09',
                                    ,NULL, SYSDATE, NULL, 1, NULL, 1);

                end if;



                END LOOP;

             END IF;
             EXIT WHEN c_consufactu%NOTFOUND;
            END LOOP;
      CLOSE c_consufactu;


    END IF;

-- (4) SECCION  DESCUENTO Y ACCESO AL SGTE NIVEL MENSAJE = PY104

        begin
         --OBTENIENDO OID MENSAJE DESCUENTO
         SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_DSCTO;

         --OBTENIENDO OID MENSAJE DESCUENTO MAXIMO
         SELECT TMSG.OID_MENS INTO lnOidMensajeMetaP9
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_DSCTO_MAXIMO;

        --OBTENIENDO OID MENSAJE DESCUENTO OFICNINA
         SELECT TMSG.OID_MENS INTO lnOidMensajeMetaP10
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_DSCTO_OFICINA;
        exception
         when others then
           lnOidMensajeMeta:='';
           lnOidMensajeMetaP9:='';
           lnOidMensajeMetaP10:='';
        end;

          INSERT INTO MSG_BUZON_MENSA
        ( OID_BUZO_MENS,  NUM_SECU ,
          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                'DV10','DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                NULL, BASE.CLIE_OID_CLIE,
                 CASE
                  WHEN
                    (SELECT MAX(A.POR_DESC)
                        FROM DTO_DESCU_GRUPO_RANGO A, DTO_DESCU_GRUPO B
                        WHERE A.COD_GRUP_DESC=B.COD_GRUP_DESC AND B.IND_ESCL_PRAL=1)=BASE.DTO
                                   THEN lnOidMensajeMetaP9
                  WHEN
                   ( (NVL((SELECT X.TICL_OID_TIPO_CLIE
                           FROM PED_SOLIC_CABEC X
                           WHERE X.FEC_FACT IS NULL
                            AND X. grpr_oid_grup_proc = 4
                            AND X.PERD_OID_PERI = lnOidPeriodo
                            AND X.TSPA_OID_TIPO_SOLI_PAIS = (SELECT Z.OID_TIPO_SOLI_PAIS
                                                             FROM PED_TIPO_SOLIC_PAIS Z
                                                             WHERE Z.TSOL_OID_TIPO_SOLI = BASE.OID_TIPO_SOLI
                                                              AND X.TSPA_OID_TIPO_SOLI_PAIS = Z.OID_TIPO_SOLI_PAIS)
                            AND X.CLIE_OID_CLIE = BASE.CLIE_OID_CLIE
                            AND ROWNUM <=1),0) = 2
                    AND
                     NVL((SELECT X.SBTI_OID_SUBT_CLIE
                           FROM PED_SOLIC_CABEC X
                           WHERE X.FEC_FACT IS NULL
                            AND X. grpr_oid_grup_proc = 4
                            AND X.PERD_OID_PERI = lnOidPeriodo
                            AND X.TSPA_OID_TIPO_SOLI_PAIS = (SELECT Z.OID_TIPO_SOLI_PAIS
                                                             FROM PED_TIPO_SOLIC_PAIS Z
                                                             WHERE Z.TSOL_OID_TIPO_SOLI = BASE.OID_TIPO_SOLI
                                                              AND X.TSPA_OID_TIPO_SOLI_PAIS = Z.OID_TIPO_SOLI_PAIS)
                            AND X.CLIE_OID_CLIE = BASE.CLIE_OID_CLIE
                            AND ROWNUM <=1),0) = 21
                            )
                       OR NVL(BASE.DTO,0) NOT IN (SELECT POR_DESC FROM DTO_DESCU_GRUPO_RANGO A,
                                                                    DTO_DESCU_GRUPO B
                                    WHERE A.COD_GRUP_DESC=B.COD_GRUP_DESC AND B.IND_ESCL_PRAL=1)
                    ) THEN lnOidMensajeMetaP10
                  ELSE
                    lnOidMensajeMeta
                 END,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                BASE.NOMBRE
                ,BASE.dto --V02
                ,(SELECT MAX(A.VAL_HAST) FROM DTO_ESCLN A WHERE A.VAL_PORC_DESC = BASE.dto)
                ,(SELECT MIN(A.POR_DESC)
                  FROM DTO_DESCU_GRUPO_RANGO A, DTO_DESCU_GRUPO B
                  WHERE A.POR_DESC > BASE.dto AND A.COD_GRUP_DESC=B.COD_GRUP_DESC
                                                                  AND B.IND_ESCL_PRAL=1)
                ,(SELECT MIN(A.VAL_IMPO_HASTA+0.01)
                  FROM DTO_DESCU_GRUPO_RANGO A, DTO_DESCU_GRUPO B
                    WHERE A.POR_DESC = BASE.dto AND A.COD_GRUP_DESC=B.COD_GRUP_DESC
                                                        AND B.IND_ESCL_PRAL=1)
                , (SELECT NVL(SUM(X.VAL_GANA_TOTA_LOCA),0)
                   FROM PED_SOLIC_CABEC X
                   WHERE X.FEC_FACT IS NULL
                    AND X. grpr_oid_grup_proc = 4
                    AND X.PERD_OID_PERI = lnOidPeriodo
                    AND X.TSPA_OID_TIPO_SOLI_PAIS = (SELECT Z.OID_TIPO_SOLI_PAIS
                                                     FROM PED_TIPO_SOLIC_PAIS Z
                                                     WHERE Z.TSOL_OID_TIPO_SOLI = BASE.OID_TIPO_SOLI
                                                      AND X.TSPA_OID_TIPO_SOLI_PAIS = Z.OID_TIPO_SOLI_PAIS)
                    AND X.CLIE_OID_CLIE = BASE.CLIE_OID_CLIE
                    GROUP BY X.CLIE_OID_CLIE
                   )--'DV06'
                , 'DV07','DV08','DV09',
                NULL, SYSDATE, NULL, 1, NULL, 1
          FROM
      (SELECT
            DISTINCT  SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            SC.OID_TIPO_SOLI,
            mc.cod_clie,
            MAX(SC.VAL_PORC_DESC) dto
        FROM
            --PED_SOLIC_CABEC SC,
            --PED_SOLIC_POSIC SD,
            --PED_TIPO_SOLIC_PAIS TSP,
            --PED_TIPO_SOLIC TS,
            msg_tmp_pedid_clien SC,
            MAE_CLIEN MC
        WHERE
            SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            AND SC.PERD_OID_PERI = lnOidPeriodo
            AND SC.CLIE_OID_CLIE = mc.OID_CLIE
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent,
                                                TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
            GROUP BY
                SC.CLIE_OID_CLIE,
                MC.VAL_NOM1 ,
                MC.VAL_NOM2 ,
                MC.VAL_APE1 ,
                MC.VAL_APE2 ,
                MC.VAL_APEL_CASA ,
                MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 ,
                SC.OID_TIPO_SOLI,
                mc.cod_clie
            ORDER BY     mc.cod_clie
        )BASE
      );


-- (5) SECCION  DATOS AHORRO CON META MENSAJE = PY105

        begin
         --OBTENIENDO OID MENSAJE VENTA
         SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_AHORRO_CON_METAS;
        exception
         when others then
           lnOidMensajeMeta:='';
        end;

     IF(lsIndMetas = '0') THEN
       INSERT INTO MSG_BUZON_MENSA
        ( OID_BUZO_MENS,  NUM_SECU ,
          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                 MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,1,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf) --VAR 10
                ,MSG_FN_DEVUE_DESCR_CAMPA(BASE.logrocpi,psCodigoPeriodo,2,lnPais,lnMarca,lnCanal) --REAL O PLAN
                ,MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,2,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo) -- VAR 12
                ,MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,2,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf)-- VAR 13
                ,MSG_FN_DEVUE_DESCR_CAMPA(BASE.logrocpi,psCodigoPeriodo,3,lnPais,lnMarca,lnCanal) --REAL O PLAN
                ,MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,3,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo) --VAR 15
                ,MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,3,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf) --VAR 16
                ,'C'|| SUBSTR(BASE.logrocpi,5,2)  --'DV17'
                ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (BASE.logrocpi,lnpais,lnmarca,lncanal,1),5,2)--'DV18'
                ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (BASE.logrocpi,lnpais,lnmarca,lncanal,2),5,2)--'DV19'
                ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (BASE.logrocpi,lnpais,lnmarca,lncanal,3),5,2)--'DV20',
                ,NULL, BASE.CLIE_OID_CLIE,
                lnOidMensajeMeta,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                DECODE( logrodescip, NULL, '', logrodescip || ': ' ) || des_larg --'V01
                ,BASE.logroplan --V02 MONTO DE TU META
                ,MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,-1,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf) --V03 OPORTUNIDAD DE AHORRO ACUMULADO
                ,(BASE.logroplan - MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,-1,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf))--ESTA SOLO A TU META
                , MSG_FN_DEVUE_DESCR_CAMPA(BASE.logrocpi,psCodigoPeriodo,0,lnPais,lnMarca,lnCanal) --REAL O PLAN
                , MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,0,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo) --VAR 6
                , MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,0,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,BASE.logroplan,BASE.logrocpf) --VAR 7
                , MSG_FN_DEVUE_DESCR_CAMPA(BASE.logrocpi,psCodigoPeriodo,1,lnPais,lnMarca,lnCanal) --REAL O PLAN
                , MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla,psCondigoIdent,BASE.logrocpi,1,BASE.CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo) --VAR 9
                , NULL, SYSDATE, NULL, 1, NULL, 1
          FROM
      (SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            ntl.DES_TIPO_LOGR logrodescip,
      ncl.des_larg,
            NCL.IMP_LOGR logroplan,
            NCL.CMP_INIC logrocpi,
            NCL.CMP_FINA logrocpf
        FROM
            --PED_SOLIC_CABEC SC,
            --PED_TIPO_SOLIC_PAIS TSP,
            --PED_TIPO_SOLIC TS,
            msg_tmp_pedid_clien SC,
            MAE_CLIEN MC,
            NVS_CONSU_LOGRO NCL,
            NVS_TIPO_LOGRO NTL
         WHERE
            SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            --AND SC.PERD_OID_PERI = lnOidPeriodo
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
            AND mc.COD_CLIE = ncl.COD_CLIE
            AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
            and psCodigoPeriodo>= NCL.CMP_INIC
            and psCodigoPeriodo <= NCL.CMP_FINA

            AND NCL.EST_REGI !='9'
            AND NCL.EST_LOGR = '1' -- Nuevo
             AND((SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
                AND
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
               )
        )BASE
      );

   ELSE
    --VERSION B consultoras neuvas
       OPEN c_consufactuNueva(lsCodigoTipoSolicitud,lsCodPeriodoAnt3, lnOidMensajeMeta);
            LOOP
            FETCH c_consufactuNueva BULK COLLECT INTO r_consufactuNueva LIMIT W_FILAS;
            --EXIT WHEN c_consufactuNueva%NOTFOUND;
               -- BEGIN
              IF  r_consufactuNueva.COUNT > 0 THEN
              FOR i IN r_consufactuNueva.FIRST..r_consufactuNueva.LAST
              LOOP

                    begin
                     --OBTENIENDO OID MENSAJE VENTA
                     SELECT TMSG.OID_MENS INTO lnOidMensajeMeta2
                     FROM MSG_MENSA TMSG
                     WHERE TMSG.COD_MENS = PY1_MENSAJE_AHORRO_CON_METAS2;

                     SELECT TMSG.OID_MENS INTO lnOidMensajeMeta3
                     FROM MSG_MENSA TMSG
                     WHERE TMSG.COD_MENS = PY1_MENSAJE_AHORRO_CON_METAS3;

                    exception
                     when others then
                       lnOidMensajeMeta2:='';
                       lnOidMensajeMeta3:='';
                    end;

                    if( lnOidMensajeMeta2 is not null  and lnOidMensajeMeta3 is not null
                       and length(lnOidMensajeMeta2)>0 and length(lnOidMensajeMeta3) >0
                      ) then

                          --PY105
                           if(r_consufactuNueva(i).ind_meta = '1') then
                               lnFaltaMeta := (r_consufactuNueva(i).logroplan -
                                                  MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,-1,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf));

                               if(lnFaltaMeta < 0) then lnFaltaMeta :=0; end if;

                               INSERT INTO MSG_BUZON_MENSA
                                ( OID_BUZO_MENS,  NUM_SECU ,
                                  DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                                  IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                                  DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                                  NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                                )
                                VALUES( MSG_BUME_SEQ.NEXTVAL,
                                         MSG_BUM2_SEQ.NEXTVAL,
                                        'DV10',
                                        'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                        NULL,
                                        r_consufactuNueva(i).CLIE_OID_CLIE,
                                        lnOidMensajeMeta,
                                        lnOidModuloOrigen,
                                        r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                        DECODE( r_consufactuNueva(i).logrodescip, NULL, '', r_consufactuNueva(i).logrodescip || ': ' ) || r_consufactuNueva(i).des_larg   --dv01
                                        ,r_consufactuNueva(i).logroplan --dv02
                                        ,MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,-1,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf) --V03 OPORTUNIDAD DE AHORRO ACUMULADO
                                        ,lnFaltaMeta--ESTA SOLO A TU META
                                        ,''--'DV05'
                                        ,''--'DV06'
                                        ,''--'DV07'
                                        ,''--'DV08'
                                        ,''--'DV09',
                                        ,NULL, SYSDATE, NULL, 1, NULL, 1);
                           else
                            --PY117 Mensaje para neuvas sin metas

                                    begin
                                     --OBTENIENDO OID MENSAJE VENTA
                                     SELECT TMSG.OID_MENS INTO lnOidMensajeMeta4
                                     FROM MSG_MENSA TMSG
                                     WHERE TMSG.COD_MENS = PY1_MENSAJE_NUEVA_SINMETAS;



                                         INSERT INTO MSG_BUZON_MENSA
                                        ( OID_BUZO_MENS,  NUM_SECU ,
                                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                                        )
                                        VALUES( MSG_BUME_SEQ.NEXTVAL,
                                                 MSG_BUM2_SEQ.NEXTVAL,
                                                'DV10',
                                                'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                                NULL,
                                                r_consufactuNueva(i).CLIE_OID_CLIE,
                                                lnOidMensajeMeta4,
                                                lnOidModuloOrigen,
                                                r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                                 '' --dv01
                                                ,'' --dv02
                                                ,'' --V03 OPORTUNIDAD DE AHORRO ACUMULADO
                                                ,''--ESTA SOLO A TU META
                                                ,''--'DV05'
                                                ,''--'DV06'
                                                ,''--'DV07'
                                                ,''--'DV08'
                                                ,''--'DV09',
                                                ,NULL, SYSDATE, NULL, 1, NULL, 1);
                                    exception
                                     when others then
                                       lnOidMensajeMeta4:='';
                                    end;

                           end if;


                           --PY113
                            INSERT INTO MSG_BUZON_MENSA
                                ( OID_BUZO_MENS,  NUM_SECU ,
                                  DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                                  IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                                  DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                                  NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                                )
                                VALUES( MSG_BUME_SEQ.NEXTVAL,
                                         MSG_BUM2_SEQ.NEXTVAL,
                                        'DV10',
                                        'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                        NULL,
                                        r_consufactuNueva(i).CLIE_OID_CLIE,
                                        lnOidMensajeMeta2,
                                        lnOidModuloOrigen,
                                        r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                         ''--dv01
                                        ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (r_consufactuNueva(i).logrocpi,lnpais,lnmarca,lncanal,3),5,2) --dv02
                                        ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (r_consufactuNueva(i).logrocpi,lnpais,lnmarca,lncanal,2),5,2)--dv03
                                        ,'C'|| SUBSTR(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (r_consufactuNueva(i).logrocpi,lnpais,lnmarca,lncanal,1),5,2)
                                        ,'C'|| SUBSTR(r_consufactuNueva(i).logrocpi,5,2)--'DV05'
                                        ,'Promedio'--'DV06'
                                        ,''--'DV07'
                                        ,''--'DV08'
                                        ,''--'DV09',
                                        ,NULL, SYSDATE, NULL, 1, NULL, 1);

                               INSERT INTO MSG_BUZON_MENSA
                                ( OID_BUZO_MENS,  NUM_SECU ,
                                  DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                                  IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                                  DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                                  NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                                )
                                VALUES( MSG_BUME_SEQ.NEXTVAL,
                                         MSG_BUM2_SEQ.NEXTVAL,
                                        'DV10',
                                        'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                        NULL,
                                        r_consufactuNueva(i).CLIE_OID_CLIE,
                                        lnOidMensajeMeta2,
                                        lnOidModuloOrigen,
                                        r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                         ''--dv01
                                        ,MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(r_consufactuNueva(i).logrocpi,psCodigoPeriodo,3,lnPais,lnMarca,lnCanal)  --dv02
                                        ,MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(r_consufactuNueva(i).logrocpi,psCodigoPeriodo,2,lnPais,lnMarca,lnCanal) --dv03
                                        ,MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(r_consufactuNueva(i).logrocpi,psCodigoPeriodo,1,lnPais,lnMarca,lnCanal)
                                        ,MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(r_consufactuNueva(i).logrocpi,psCodigoPeriodo,0,lnPais,lnMarca,lnCanal)
                                        ,''--'DV06'
                                        ,''--'DV07'
                                        ,''--'DV08'
                                        ,''--'DV09',
                                        ,NULL, SYSDATE, NULL, 1, NULL, 1);


                             lsCodigoIngreso:= r_consufactuNueva(i).logrocpi;

                           --nuevas
                             lsCodPeriodoSgte1:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,1);
                             lsCodPeriodoSgte2:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,2);
                             lsCodPeriodoSgte3:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsCodigoIngreso, lnpais,lnmarca,lncanal,3);

                             lnOidIngreso     := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoIngreso,lnmarca,lncanal);
                             lnOidPeriodoSgte1:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte1,lnmarca,lncanal);
                             lnOidPeriodoSgte2:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte2,lnmarca,lncanal);
                             lnOidPeriodoSgte3:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSgte3,lnmarca,lncanal);

                             if(lsCodigoIngreso = psCodigoPeriodo)  then
                                lnVentaIngreso := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,4,lnOidIngreso);
                             else
                                lnVentaIngreso :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,5,lnOidIngreso);
                             end if;

                             if(lsCodPeriodoSgte1 = psCodigoPeriodo) then
                                lnVentaIngresoSgte1 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte1);
                             else
                                lnVentaIngresoSgte1 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte1);
                             end if;

                             if(lsCodPeriodoSgte2 = psCodigoPeriodo) then
                                lnVentaIngresoSgte2 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte2);
                             else
                                lnVentaIngresoSgte2 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte2);
                             end if;

                             if(lsCodPeriodoSgte3 = psCodigoPeriodo) then
                                lnVentaIngresoSgte3 := MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,4,lnOidPeriodoSgte3);
                             else
                                lnVentaIngresoSgte3 :=  MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).CLIE_OID_CLIE,5,lnOidPeriodoSgte3);
                             end if;

                             lnSuma:=0;
                             lnNivel:=0;

                             if(lnVentaIngreso > 0) then
                               lnSuma:=lnSuma + lnVentaIngreso;
                               lnNivel:=lnNivel+1;
                             end if;

                             if(lnVentaIngresoSgte1 > 0) then
                              lnSuma:=lnSuma + lnVentaIngresoSgte1;
                              lnNivel:=lnNivel+1;
                             end if;
                             if(lnVentaIngresoSgte2 > 0) then
                              lnSuma:=lnSuma + lnVentaIngresoSgte2;
                              lnNivel:=lnNivel+1;
                             end if;
                             if(lnVentaIngresoSgte3 > 0) then
                              lnSuma:=lnSuma + lnVentaIngresoSgte3;
                              lnNivel:=lnNivel+1;
                             end if;

                             if(lnNivel > 0 ) then
                              lnPromedio:= round(lnSuma /lnNivel,2);
                             else
                              lnPromedio :=0;
                             end if;


                               --1er regisrto detalle py114
                               INSERT INTO MSG_BUZON_MENSA
                                ( OID_BUZO_MENS,  NUM_SECU ,
                                  DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                                  IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                                  DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                                  NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                                )
                                VALUES( MSG_BUME_SEQ.NEXTVAL,
                                         MSG_BUM2_SEQ.NEXTVAL,
                                        'DV10',--'DV10',
                                        'DV11','DV12','DV13','DV14','DV15', 'DV16','DV17','DV18','DV19','DV20',
                                        NULL,
                                        r_consufactuNueva(i).CLIE_OID_CLIE,
                                        lnOidMensajeMeta3,
                                        lnOidModuloOrigen,
                                        r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                        'Compra(*)' --dv01
                                        ,TO_CHAR(lnVentaIngresoSgte3,'99,999,999')--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidIngreso)
                                        ,TO_CHAR(lnVentaIngresoSgte2,'99,999,999')--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte1)
                                        ,TO_CHAR(lnVentaIngresoSgte1,'99,999,999') --MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte2)
                                        ,TO_CHAR(lnVentaIngreso,'99,999,999')--MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla,psCondigoIdent,r_consufactu.CLIE_OID_CLIE,5,lnOidPeriodoSgte3)
                                        ,TO_CHAR(ROUND(lnpromedio,0),'99,999,999')--'DV06'
                                        ,''--'DV07'
                                        ,''--'DV08'
                                        ,''--'DV09',
                                        ,NULL, SYSDATE, NULL, 1, NULL, 1);

                        --py114 oportunidad de ahorro catalogo y liquidaciones 2registro de detalle
                         --ya no existen las planeadas
                        lnAhorroCata:=  MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,0,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroCataSgte1:= MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,1,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroCataSgte2:= MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,2,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroCataSgte3:= MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,3,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorroCata >0 )then
                            lnSuma:=lnSuma + lnAhorroCata;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataSgte1 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataSgte1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataSgte2 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataSgte2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataSgte3 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataSgte3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --segundo REGISTRO DE DETALLE
                       /* INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab.CLIE_OID_CLIE,
                                lnOidMensajeMeta3,
                                lnOidModuloOrigen,
                                r_consufactuEstab.NOM1, r_consufactuEstab.NOM2, r_consufactuEstab.APE1, r_consufactuEstab.APE2, r_consufactuEstab.APEC,
                                'Op. Ahorro Catalogo y Liquidaciones'--''VAR01
                                ,lnAhorroCataSgte3 --V02
                                ,lnAhorroCataSgte2 --V03
                                ,lnAhorroCataSgte1 --V04
                                ,lnAhorroCata --V05
                                ,'lnpromedio-VAR 6
                                ,''--VAR 7
                                ,'' --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);  */




                        --py114 oportunidad de ahorro revistas y ofertas especiales 3registro de detalle
                         --ya no existen las planeadas
                        lnAhorroRevista:=  MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,0,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroRevistaSgte1:= MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,1,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroRevistaSgte2:= MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,2,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroRevistaSgte3:= MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,3,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorroRevista >0 )then
                            lnSuma:=lnSuma + lnAhorroRevista;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaSgte1 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaSgte1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaSgte2 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaSgte2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaSgte3 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaSgte3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --TERCER REGISTRO DE DETALLE
                       /* INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab.CLIE_OID_CLIE,
                                lnOidMensajeMeta3,
                                lnOidModuloOrigen,
                                r_consufactuEstab.NOM1, r_consufactuEstab.NOM2, r_consufactuEstab.APE1, r_consufactuEstab.APE2, r_consufactuEstab.APEC,
                                'Op. Ahorro Revista y Of. Especiales'--''VAR01
                                ,lnAhorroRevistaSgte3 --V02
                                ,lnAhorroRevistaSgte2 --V03
                                ,lnAhorroRevistaSgte1 --V04
                                ,lnAhorroRevista --V05
                                ,lnpromedio --VAR 6
                                ,''--VAR 7
                                ,'' --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);  */


                        --py114 oportunidad TOTAL de ahorro 4registro de detalle
                         --ya no existen las planeadas
                        lnAhorro:=  MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,0,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroSgte1:= MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,1,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroSgte2:= MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,2,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7
                        lnAhorroSgte3:= MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla,psCondigoIdent,r_consufactuNueva(i).logrocpi,3,r_consufactuNueva(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,r_consufactuNueva(i).logroplan,r_consufactuNueva(i).logrocpf); --VAR 7

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorro >0 )then
                            lnSuma:=lnSuma + lnAhorro;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroSgte1 >0 )then
                            lnSuma:=lnSuma + lnAhorroSgte1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroSgte2 >0 )then
                            lnSuma:=lnSuma + lnAhorroSgte2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroSgte3 >0 )then
                            lnSuma:=lnSuma + lnAhorroSgte3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --CUARTO REGISTRO DE DETALLE
                        INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuNueva(i).CLIE_OID_CLIE,
                                lnOidMensajeMeta3,
                                lnOidModuloOrigen,
                                r_consufactuNueva(i).NOM1, r_consufactuNueva(i).NOM2, r_consufactuNueva(i).APE1, r_consufactuNueva(i).APE2, r_consufactuNueva(i).APEC,
                                'Op. Ahorro Total'--''VAR01
                                ,TO_CHAR(lnAhorroSgte3,'99,999,999') --V02
                                ,TO_CHAR(lnAhorroSgte2,'99,999,999') --V03
                                ,TO_CHAR(lnAhorroSgte1,'99,999,999') --V04
                                ,TO_CHAR(lnAhorro,'99,999,999') --V05
                                ,TO_CHAR(ROUND(lnpromedio,0),'99,999,999')--VAR 6
                                ,''--VAR 7
                                ,'' --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);



                  end if;--fin de oid mensajes 2 y 3


              END LOOP;

             END IF;
            EXIT WHEN c_consufactuNueva%NOTFOUND;
               -- END;
            END LOOP;
      CLOSE c_consufactuNueva;
   END IF;

-- (6) SECCION  LOGRO META MENSAJE = PY106

  BEGIN
         --OBTENIENDO OID MENSAJE VENTA
     SELECT tmsg.oid_mens INTO lnOidMensajeMeta
       FROM msg_mensa tmsg
      WHERE tmsg.cod_mens = PY1_MENSAJE_LOGRO_META;
    EXCEPTION
     WHEN OTHERS THEN
           lnOidMensajeMeta:='';
  END;

  IF(lsIndMetas = '0') THEN

     INSERT INTO MSG_BUZON_MENSA
     (
      oid_buzo_mens,  num_secu ,
      ind_esta_mens, clie_oid_clie,
      mens_oid_mens, modu_oid_modu_orig,
      val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
      dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,
      num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
     )

     SELECT MSG_BUME_SEQ.NEXTVAL,
                MSG_BUM2_SEQ.NEXTVAL,
            NULL, base.clie_oid_clie,
            lnOidMensajeMeta, lnOidModuloOrigen,
            base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
            base.nombre, -- DV01
            base.logrodescip, -- DV02
            base.logroplan, --DV03
            base.mont_opor_ahor, --DV04
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
              SELECT soca.clie_oid_clie,
                     MAX( pedc.val_nom1 ) nom1,
                     MAX( pedc.val_nom2 ) nom2,
                     MAX( pedc.val_ape1 ) ape1,
                     MAX( pedc.val_ape2 ) ape2,
                     MAX( pedc.val_apel_casa ) apec,
                     MAX( pedc.nombre ) nombre,
                     MAX( pedc.des_tipo_logr ) logrodescip,
                     MAX( pedc.imp_logr ) logroplan,
                     MAX( pedc.cmp_inic ) logrocpi,
                     MAX( pedc.cmp_fina ) logrocpf,
                     NVL( SUM( soca.val_gana_tota_loca),0) mont_opor_ahor
                FROM (
                      SELECT spedc.clie_oid_clie,
                             snvcg.perd_oid_peri_inic,
                             snvcg.perd_oid_peri_fina,
                             snvcg.val_ape1,
                             snvcg.val_ape2,
                             snvcg.val_apel_casa,
                             snvcg.val_nom1,
                             snvcg.val_nom2,
                             snvcg.nombre,
                             snvcg.des_tipo_logr,
                             snvcg.imp_logr,
                             snvcg.cmp_inic,
                             snvcg.cmp_fina
                        FROM (
                              SELECT clie.oid_clie,
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( nvcg.cmp_inic ) perd_oid_peri_inic,
                                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( nvcg.cmp_fina ) perd_oid_peri_fina,
                                     clie.val_ape1,
                                     clie.val_ape2,
                                     clie.val_apel_casa,
                                     clie.val_nom1,
                                     clie.val_nom2,
                                     clie.val_nom1 || ' ' || clie.val_nom2 || ' ' || clie.val_ape1 || ' ' || clie.val_ape2 nombre,
                                     nvtl.des_tipo_logr,
                                     nvcg.imp_logr,
                                     nvcg.cmp_inic,
                                     nvcg.cmp_fina
                                FROM nvs_consu_logro nvcg,
                                     nvs_tipo_logro nvtl,
                                     mae_clien clie
                               WHERE nvcg.cod_clie = clie.cod_clie
                                 AND nvcg.cod_tipo_logr = nvtl.cod_tipo_logr
                                 AND psCodigoPeriodo BETWEEN nvcg.cmp_inic AND nvcg.cmp_fina
                                 AND nvcg.cmp_fina = psCodigoPeriodo
                                 AND nvcg.est_regi != '9'
                                 AND nvcg.est_logr = '1' -- Nuevo
                             ) snvcg,
                             (
                              SELECT clie_oid_clie
                                FROM msg_tmp_pedid_clien
                               WHERE cod_tipo_soli = lsCodigoTipoSolicitud
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                                                            FROM TABLE(MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
                               GROUP BY clie_oid_clie
                             ) spedc
                       WHERE snvcg.oid_clie = spedc.clie_oid_clie
                     ) pedc,
                     ped_solic_cabec soca,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol
               WHERE pedc.clie_oid_clie = soca.clie_oid_clie
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 --
                 AND soca.perd_oid_peri BETWEEN pedc.perd_oid_peri_inic AND pedc.perd_oid_peri_fina
                 AND tsol.cod_tipo_soli = lsCodigoTipoSolicitud
               GROUP BY soca.clie_oid_clie
               HAVING NVL( SUM( soca.val_gana_tota_loca),0) - MAX( pedc.imp_logr ) >= 0
            ) base ;

  ELSE
     --VERSION B - ENVIAR MENSAJES EN CUALQUIER CAMPANHA
    INSERT INTO MSG_BUZON_MENSA
        ( OID_BUZO_MENS,  NUM_SECU ,
          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                 'DV10'
                ,'DV11'
                ,'DV12'
                ,'DV13'
                ,'DV14'
                ,'DV15'
                ,'DV16'
                ,'DV17'
                ,'DV18'
                ,'DV19'
                ,'DV20',
                NULL, BASE.CLIE_OID_CLIE,
                lnOidMensajeMeta,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                BASE.NOMBRE
                , logrodescip--'DV02'
                , logroplan--'DV03'
                , ahorroAcum--'DV04'
                ,'DV05'
                ,'DV06'
                ,'DV07'
                ,'DV08'
                ,'DV09'
                , NULL, SYSDATE, NULL, 1, NULL, 1
    FROM
      (SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            ntl.DES_TIPO_LOGR logrodescip,
            NCL.IMP_LOGR logroplan,
            NCL.CMP_INIC logrocpi,
            NCL.CMP_FINA logrocpf,
            MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,NCL.CMP_INIC,-1,mc.OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,NCL.IMP_LOGR,NCL.CMP_FINA) ahorroAcum
        FROM
            --PED_SOLIC_CABEC SC,
            --PED_TIPO_SOLIC_PAIS TSP,
            --PED_TIPO_SOLIC TS,
            msg_tmp_pedid_clien SC,
            MAE_CLIEN MC,
            NVS_CONSU_LOGRO NCL,
            NVS_TIPO_LOGRO NTL
         WHERE
            SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            --AND SC.PERD_OID_PERI = lnOidPeriodo
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
            AND mc.COD_CLIE = ncl.COD_CLIE
            AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
            and psCodigoPeriodo>= NCL.CMP_INIC
            and psCodigoPeriodo <= NCL.CMP_FINA
            and NCL.EST_REGI != '9'
            AND NCL.EST_LOGR = '1' -- Nuevo
             AND(
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
                AND
                 (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
               )
            AND( --que haya cumplido su meta
               (MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,NCL.CMP_INIC,-1,mc.OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,NCL.IMP_LOGR,NCL.CMP_FINA)
                         - NCL.IMP_LOGR >= 0)
             )
        )BASE
      );


  END IF;



-- (12) SECCION  LOGRO META MENSAJE = PY112


        begin
         --OBTENIENDO OID MENSAJE VENTA
         SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
         FROM MSG_MENSA TMSG
         WHERE TMSG.COD_MENS = PY1_MENSAJE_NOLOGRO_META_PREV;
        exception
         when others then
           lnOidMensajeMeta:='';
        end;

  IF(lsIndMetas = '1') THEN

     --VERSION B - ENVIAR MENSAJES EN CUALQUIER CAMPANHA
    INSERT INTO MSG_BUZON_MENSA
        ( OID_BUZO_MENS,  NUM_SECU ,
          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
        )
        ( SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
                 'DV10'
                ,'DV11'
                ,'DV12'
                ,'DV13'
                ,'DV14'
                ,'DV15'
                ,'DV16'
                ,'DV17'
                ,'DV18'
                ,'DV19'
                ,'DV20',
                NULL, BASE.CLIE_OID_CLIE,
                lnOidMensajeMeta,
                lnOidModuloOrigen,
                BASE.NOM1, BASE.NOM2, BASE.APE1, BASE.APE2, BASE.APEC,
                BASE.NOMBRE
                , logrodescip--'DV02'
                , logroplan--'DV03'
                , ahorroAcum--'DV04'
                ,'DV05'
                ,'DV06'
                ,'DV07'
                ,'DV08'
                ,'DV09'
                , NULL, SYSDATE, NULL, 1, NULL, 1
    FROM
      (SELECT DISTINCT
            SC.CLIE_OID_CLIE,
            MC.VAL_NOM1 AS NOM1,
            MC.VAL_NOM2 AS NOM2,
            MC.VAL_APE1 AS APE1,
            MC.VAL_APE2 AS APE2,
            MC.VAL_APEL_CASA AS APEC,
            MC.VAL_NOM1 ||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2 AS NOMBRE,
            ntl.DES_TIPO_LOGR logrodescip,
            NCL.IMP_LOGR logroplan,
            NCL.CMP_INIC logrocpi,
            NCL.CMP_FINA logrocpf,
            MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,NCL.CMP_INIC,-1,mc.OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,NCL.IMP_LOGR,NCL.CMP_FINA) ahorroAcum
        FROM
            --PED_SOLIC_CABEC SC,
            --PED_TIPO_SOLIC_PAIS TSP,
            --PED_TIPO_SOLIC TS,
            msg_tmp_pedid_clien SC,
            MAE_CLIEN MC,
            NVS_CONSU_LOGRO NCL,
            NVS_TIPO_LOGRO NTL
         WHERE
            SC.COD_TIPO_SOLI = lsCodigoTipoSolicitud
            --AND SC.PERD_OID_PERI = lnOidPeriodo
            AND sc.CLIE_OID_CLIE = mc.OID_CLIE
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                     FROM TABLE(MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
            AND mc.COD_CLIE = ncl.COD_CLIE
            AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
            and psCodigoPeriodo>= NCL.CMP_INIC
            and psCodigoPeriodo <= NCL.CMP_FINA
            AND NCL.EST_REGI != '9'
            AND NCL.EST_LOGR = '1' -- Nuevo
             AND(
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
                AND
                 (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
               )
            AND( --que no haya cumplido su meta en campanhas previas
               (MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla,psCondigoIdent,NCL.CMP_INIC,-1,mc.OID_CLIE,lnpais,lnmarca,lncanal,psCodigoPeriodo,NCL.IMP_LOGR,NCL.CMP_FINA)
                         - NCL.IMP_LOGR < 0)
             )
        )BASE
      );


  END IF;


/*
  -----------------------------------------------------------------------------
  SECCION 07: Consultoras que no lograron llegar a la meta de Logros ( PY107 )
  -----------------------------------------------------------------------------
*/

BEGIN
  SELECT tmsg.oid_mens
    INTO lnoidmensajemeta
    FROM msg_mensa tmsg
   WHERE tmsg.cod_mens = py1_mensaje_nologro_meta
     AND py1_mensaje_nologro_meta IN (
                                      SELECT column_value
                                        FROM TABLE(MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                                     );
EXCEPTION
  WHEN OTHERS THEN lnoidmensajemeta:='';
END;

IF lnoidmensajemeta IS NOT NULL THEN
    BEGIN
              INSERT INTO MSG_BUZON_MENSA
        (
          oid_buzo_mens,
          num_secu ,
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
          num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
        )
        (
          SELECT msg_bume_seq.nextval oid_buzo_mens,
                 msg_bum2_seq.nextval num_secu,
                 NULL ind_esta_mens,
                 base.clie_oid_clie,
                 lnOidMensajeMeta mens_oid_mens,
                 lnOidModuloOrigen modu_oid_modu_orig,
                 clie.val_nom1 val_nom1_clie,
                 clie.val_nom2 val_nom2_clie,
                 clie.val_ape1 val_ape1_clie,
                 clie.val_ape2 val_ape2_clie,
                 clie.val_apel_casa val_apel_casa_clie,
                 clie.val_nom1 || ' ' || clie.val_nom2 || ' ' || clie.val_ape1 || ' ' || clie.val_ape2 dato_vari_01,
                 base.des_tipo_logr dato_vari_02,
                 base.imp_logr dato_vari_03,
                 base.val_gana_tota_loca dato_vari_04,
                 NULL num_lote_impr,
                 SYSDATE fec_grab,
                 NULL fec_impr,
                 1 ind_list_cons,
                 NULL peri_oid_peri,
                 1 ind_acti
            FROM (
                  SELECT
                         soca.clie_oid_clie,
                         MAX( pedc.des_tipo_logr ) des_tipo_logr,
                         MAX( pedc.imp_logr ) imp_logr,
                         SUM( soca.val_gana_tota_loca ) val_gana_tota_loca
                    FROM (
                          SELECT clie.oid_clie,
                                 nvtl.des_tipo_logr,
                                 nvcg.imp_logr
                            FROM (
                                  SELECT clie_oid_clie
                                    FROM msg_tmp_pedid_clien
                                   WHERE cod_tipo_soli = lsCodigoTipoSolicitud
                                   GROUP BY clie_oid_clie
                                 ) spedc,
                                 nvs_consu_logro nvcg,
                                 nvs_tipo_logro nvtl,
                                 mae_clien clie
                           WHERE spedc.clie_oid_clie = clie.oid_clie
                             AND clie.cod_clie = nvcg.cod_clie
                             AND nvcg.cod_tipo_logr = nvtl.cod_tipo_logr
                             AND nvcg.est_regi != '9'
                             AND nvcg.est_logr = '1' -- Nuevo
                             AND nvcg.cmp_fina = psCodigoPeriodo
                         ) pedc,
                         ped_solic_cabec soca,
                         ped_tipo_solic_pais tspa,
                         ped_tipo_solic tsol
                   WHERE pedc.oid_clie = soca.clie_oid_clie
                     AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                     AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                     AND tsol.cod_tipo_soli = lsCodigoTipoSolicitud
                     AND soca.perd_oid_peri BETWEEN lnOidPeriodoAnt3 AND lnOidPeriodo
                   GROUP BY soca.clie_oid_clie
                  HAVING SUM( NVL( soca.val_gana_tota_loca ,0 ) ) < MAX( pedc.imp_logr )
                 ) base,
                 mae_clien clie
           WHERE base.clie_oid_clie = clie.oid_clie
        );
    EXCEPTION
      WHEN OTHERS THEN NULL;
    END;
                END IF;

/*
  -----------------------------------------------------------------------------
  SECCION 08: Generación de mensajes para consultoras establecidas ( PY108 )
  -----------------------------------------------------------------------------
*/

-- Variables de trabajo

BEGIN
    SELECT tmsg.oid_mens
      INTO lnoidmensajemeta
      FROM msg_mensa tmsg
     WHERE tmsg.cod_mens = py1_mensaje_ahorro_sin_metas;
EXCEPTION
    WHEN OTHERS THEN
    lnoidmensajemeta := '';
END;

    IF(lsIndMetas = '0') THEN

        INSERT INTO MSG_BUZON_MENSA
        ( oid_buzo_mens,  num_secu ,
          dato_vari_10, dato_vari_11, dato_vari_12, dato_vari_13, dato_vari_14, dato_vari_15, dato_vari_16, dato_vari_17, dato_vari_18, dato_vari_19, dato_vari_20,
          ind_esta_mens, clie_oid_clie, mens_oid_mens, modu_oid_modu_orig, val_nom1_clie, val_nom2_clie, val_ape1_clie, val_ape2_clie, val_apel_casa_clie,
          dato_vari_01, dato_vari_05, dato_vari_06, dato_vari_07, dato_vari_08, dato_vari_09,
          num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
        )

        SELECT msg_bume_seq.nextval oid_buzo_mens,
               msg_bum2_seq.nextval num_secu,
               val_mont_ahorr_2 dato_vari_10,
               '3ra REAL' dato_vari_11,
               val_mont_cata_1 dato_vari_12,
               val_mont_ahorr_1 dato_vari_13,
               '4ta REAL' dato_vari_14,
               val_mont_cata_0 dato_vari_15,
               val_mont_ahorr_0 dato_vari_16,
               'C' || SUBSTR( psCodigoPeriodo,5,2 ) dato_vari_17,
               'C' || SUBSTR( lsCodPeriodoAnt1,5,2 ) dato_vari_18,
               'C' || SUBSTR( lsCodPeriodoAnt2,5,2 ) dato_vari_19,
               'C' || SUBSTR( lsCodPeriodoAnt3,5,2 ) dato_vari_20,
               NULL ind_esta_mens,
               base.clie_oid_clie,
               lnOidMensajeMeta mens_oid_mens,
               lnOidModuloOrigen modu_oid_modu_orig,
               val_nom1 val_nom1_clie,
               val_nom2 val_nom2_clie,
               val_ape1 val_ape1_clie,
               val_ape2 val_ape2_clie,
               val_apel_casa val_apel_casa_clie,
               nombre dato_vari_01,
               '1ra REAL' dato_vari_05,
               val_mont_cata_3 dato_vari_06,
               val_mont_ahorr_3 dato_vari_07,
               '2da REAL' dato_vari_08,
               val_mont_cata_2 dato_vari_09,
               --
               NULL num_lote_impr,
               SYSDAte fec_grab,
               NULL fec_impr,
               1 ind_list_cons,
               NULL peri_oid_peri,
               1 ind_acti
          FROM (
                SELECT soca.clie_oid_clie,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodo THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_mont_cata_0,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt1 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_mont_cata_1,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt2 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_mont_cata_2,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt3 THEN soca.val_prec_cata_tota_loca + soca.val_prec_cont_tota_loca ELSE 0 END ) val_mont_cata_3,
                       --
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodo THEN soca.val_gana_tota_loca ELSE 0 END ) val_mont_ahorr_0,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt1 THEN soca.val_gana_tota_loca ELSE 0 END ) val_mont_ahorr_1,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt2 THEN soca.val_gana_tota_loca ELSE 0 END ) val_mont_ahorr_2,
                       SUM( CASE WHEN soca.perd_oid_peri = lnOidPeriodoAnt3 THEN soca.val_gana_tota_loca ELSE 0 END ) val_mont_ahorr_3,
                       --
                       MAX( pedc.val_nom1 ) val_nom1,
                       MAX( pedc.val_nom2 ) val_nom2,
                       MAX( pedc.val_ape1 ) val_ape1,
                       MAX( pedc.val_ape2 ) val_ape2,
                       MAX( pedc.val_apel_casa ) val_apel_casa,
                       MAX( pedc.nombre ) nombre
                 FROM (
                        SELECT spedc.clie_oid_clie,
                               clie.val_ape1,
                               clie.val_ape2,
                               clie.val_apel_casa,
                               clie.val_nom1,
                               clie.val_nom2,
                               clie.val_nom1 || ' ' || clie.val_nom2 || ' ' || clie.val_ape1 || ' ' || clie.val_ape2 nombre
                          FROM (
                                SELECT clie.oid_clie,
                                       MAX( nvcg.cmp_fina ) cmp_fina
                                  FROM nvs_consu_logro nvcg,
                                       mae_clien clie
                                 WHERE nvcg.cod_clie = clie.cod_clie
                                   AND nvcg.est_regi != '9'
                                   AND nvcg.est_logr = '1' -- Nuevo
                                   AND psCodigoPeriodo BETWEEN nvcg.cmp_inic AND nvcg.cmp_fina
                                 GROUP BY clie.oid_clie
                               ) snvcg,
                               (
                                SELECT clie_oid_clie
                                  FROM msg_tmp_pedid_clien
                                 WHERE cod_tipo_soli = lsCodigoTipoSolicitud
            AND lnOidMensajeMeta IN(
                     SELECT column_value
                                                              FROM TABLE(MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla,psCondigoIdent, TIPO_DATO_MENSAJE,TIPO_RETORNO_OID))
                   )
                                 GROUP BY clie_oid_clie
                               ) spedc,
                               mae_clien clie
                         WHERE spedc.clie_oid_clie = clie.oid_clie
                           AND spedc.clie_oid_clie = snvcg.oid_clie(+)
                           AND ( snvcg.oid_clie IS NULL OR snvcg.cmp_fina < psCodigoPeriodo )
                       ) pedc,
                       ped_solic_cabec soca,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic tsol
                 WHERE pedc.clie_oid_clie = soca.clie_oid_clie
                   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   AND tsol.cod_tipo_soli = lsCodigoTipoSolicitud
                   AND soca.perd_oid_peri BETWEEN lnOidPeriodoAnt3 AND lnOidPeriodo
                 GROUP BY soca.clie_oid_clie
               ) base;
   ELSE
     --version B
           lsCodPeriodoAnt4:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-4);
           lsCodPeriodoAnt5:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnpais,lnmarca,lncanal,-5);

            begin
             --OBTENIENDO OID MENSAJE VENTA
             SELECT TMSG.OID_MENS INTO lnOidMensajeMeta2
             FROM MSG_MENSA TMSG
             WHERE TMSG.COD_MENS = PY1_MENSAJE_AHORRO_SIN_METAS2;
            exception
             when others then
               lnOidMensajeMeta2:='';
            end;

             IF(lnOidMensajeMeta2 IS NOT NULL AND LENGTH(lnOidMensajeMeta2) > 0 ) THEN
                OPEN c_consufactuEstab(lsCodigoTipoSolicitud,lsCodPeriodoAnt3, lnOidMensajeMeta);
                LOOP
                FETCH c_consufactuEstab BULK COLLECT INTO  r_consufactuEstab LIMIT W_FILAS;
                --EXIT WHEN c_consufactuEstab%NOTFOUND;
                --    BEGIN
                IF  r_consufactuEstab.COUNT > 0 THEN
                  FOR i IN r_consufactuEstab.FIRST..r_consufactuEstab.LAST
                  LOOP


                    --PY108
                    INSERT INTO MSG_BUZON_MENSA
                    ( OID_BUZO_MENS,  NUM_SECU ,
                      DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                      IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                      DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                      NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                    )
                    VALUES(  MSG_BUME_SEQ.NEXTVAL,
                             MSG_BUM2_SEQ.NEXTVAL,
                             '' --VAR 10
                            ,'' --VAR 11
                            ,''-- VAR 12
                            ,''-- VAR 13
                            ,'' --REAL O PLAN
                            ,''--VAR 15
                            ,'' --VAR 16
                            ,''--'DV17'
                            ,''--'DV18'
                            ,''--'DV19'
                            ,''--'DV20',
                            ,NULL, r_consufactuEstab(i).CLIE_OID_CLIE,
                            lnOidMensajeMeta,
                            lnOidModuloOrigen,
                            r_consufactuEstab(i).NOM1, r_consufactuEstab(i).NOM2, r_consufactuEstab(i).APE1, r_consufactuEstab(i).APE2, r_consufactuEstab(i).APEC,
                            ''--''VAR01
                            ,'C'|| SUBSTR(lsCodPeriodoAnt5,5,2) --V02
                            ,'C'|| SUBSTR(lsCodPeriodoAnt4,5,2) --V03
                            ,'C'|| SUBSTR(lsCodPeriodoAnt3,5,2)--V04
                            ,'C'|| SUBSTR(lsCodPeriodoAnt2,5,2) --V05
                            ,'C'|| SUBSTR(lsCodPeriodoAnt1,5,2)--VAR 6
                            ,'C'|| SUBSTR(psCodigoPeriodo,5,2)--VAR 7
                            ,'Promedio' --V08
                            , '' --VAR 9
                            , NULL, SYSDATE, NULL, 1, NULL, 1);


                    --PY115 --primer registro de detalle compra

                        lnVentaReal:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,0,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnVentaRealAnt1:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-1,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnVentaRealAnt2:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-2,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnVentaRealAnt3:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-3,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnVentaRealAnt4:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-4,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnVentaRealAnt5:= MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-5,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnVentaReal >0 )then
                            lnSuma:=lnSuma + lnVentaReal;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnVentaRealAnt1 >0 )then
                            lnSuma:=lnSuma + lnVentaRealAnt1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnVentaRealAnt2 >0 )then
                            lnSuma:=lnSuma + lnVentaRealAnt2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnVentaRealAnt3 >0 )then
                            lnSuma:=lnSuma + lnVentaRealAnt3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnVentaRealAnt4 >0 )then
                            lnSuma:=lnSuma + lnVentaRealAnt4;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnVentaRealAnt5 >0 )then
                            lnSuma:=lnSuma + lnVentaRealAnt5;
                            lnNivel:=lnNivel+1;
                        end if;

                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --PRIMER REGISTRO DE DETALLE
                        INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab(i).CLIE_OID_CLIE,
                                lnOidMensajeMeta2,
                                lnOidModuloOrigen,
                                r_consufactuEstab(i).NOM1, r_consufactuEstab(i).NOM2, r_consufactuEstab(i).APE1, r_consufactuEstab(i).APE2, r_consufactuEstab(i).APEC,
                                'Compra(*)'--''VAR01
                                ,TO_CHAR(lnVentaRealAnt5,'99,999,999') --V02
                                ,TO_CHAR(lnVentaRealAnt4,'99,999,999') --V03
                                ,TO_CHAR(lnVentaRealAnt3,'99,999,999')--V04
                                ,TO_CHAR(lnVentaRealAnt2,'99,999,999') --V05
                                ,TO_CHAR(lnVentaRealAnt1,'99,999,999')--VAR 6
                                ,TO_CHAR(lnVentaReal,'99,999,999')--VAR 7
                                ,TO_CHAR(ROUND(lnpromedio,0),'99,999,999') --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);


                    --PY115 --segundo registro de detalle Op. Ahorro Catálogo y Liquidaciones

                        lnAhorroCataReal:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,0,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroCataRealAnt1:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-1,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroCataRealAnt2:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-2,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroCataRealAnt3:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-3,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroCataRealAnt4:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-4,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroCataRealAnt5:= MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-5,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorroCataReal >0 )then
                            lnSuma:=lnSuma + lnAhorroCataReal;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataRealAnt1 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataRealAnt1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataRealAnt2 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataRealAnt2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataRealAnt3 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataRealAnt3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataRealAnt4 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataRealAnt4;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroCataRealAnt5 >0 )then
                            lnSuma:=lnSuma + lnAhorroCataRealAnt5;
                            lnNivel:=lnNivel+1;
                        end if;

                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --segundo REGISTRO DE DETALLE
                       /* INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab(i).CLIE_OID_CLIE,
                                lnOidMensajeMeta2,
                                lnOidModuloOrigen,
                                r_consufactuEstab(i).NOM1, r_consufactuEstab(i).NOM2, r_consufactuEstab(i).APE1, r_consufactuEstab(i).APE2, r_consufactuEstab(i).APEC,
                                'Op. Ahorro Catalogo y Liquidaciones'--''VAR01
                                ,lnAhorroCataRealAnt5 --V02
                                ,lnAhorroCataRealAnt4 --V03
                                ,lnAhorroCataRealAnt3--V04
                                ,lnAhorroCataRealAnt2 --V05
                                ,lnAhorroCataRealAnt1--VAR 6
                                ,lnAhorroCataReal--VAR 7
                                ,lnpromedio --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);  */


                    --PY115 --TERCER registro de detalle Op. Ahorro Revista y Of. Especiales

                        lnAhorroRevistaReal:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,0,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroRevistaRealAnt1:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-1,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroRevistaRealAnt2:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-2,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroRevistaRealAnt3:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-3,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroRevistaRealAnt4:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-4,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroRevistaRealAnt5:= MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-5,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorroRevistaReal >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaReal;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaRealAnt1 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaRealAnt1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaRealAnt2 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaRealAnt2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaRealAnt3 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaRealAnt3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaRealAnt4 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaRealAnt4;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroRevistaRealAnt5 >0 )then
                            lnSuma:=lnSuma + lnAhorroRevistaRealAnt5;
                            lnNivel:=lnNivel+1;
                        end if;

                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --TERCER REGISTRO DE DETALLE
                       /* INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab(i).CLIE_OID_CLIE,
                                lnOidMensajeMeta2,
                                lnOidModuloOrigen,
                                r_consufactuEstab(i).NOM1, r_consufactuEstab(i).NOM2, r_consufactuEstab(i).APE1, r_consufactuEstab(i).APE2, r_consufactuEstab(i).APEC,
                                'Op. Ahorro Revista y Of.Especiales'--''VAR01
                                ,lnAhorroRevistaRealAnt5 --V02
                                ,lnAhorroRevistaRealAnt4 --V03
                                ,lnAhorroRevistaRealAnt3--V04
                                ,lnAhorroRevistaRealAnt2 --V05
                                ,lnAhorroRevistaRealAnt1--VAR 6
                                ,lnAhorroRevistaReal--VAR 7
                                ,lnpromedio --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);  */

                        --PY115 --CUARTO registro de detalle Op. Ahorro TOTAL

                        lnAhorroTotalReal:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,0,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroTotalRealAnt1:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-1,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroTotalRealAnt2:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-2,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroTotalRealAnt3:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-3,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroTotalRealAnt4:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-4,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);
                        lnAhorroTotalRealAnt5:= MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla,psCondigoIdent,psCodigoPeriodo,-5,r_consufactuEstab(i).CLIE_OID_CLIE,lnpais,lnmarca,lncanal);

                        lnSuma:=0;
                        lnNivel:=0;
                        if(lnAhorroTotalReal >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalReal;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroTotalRealAnt1 >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalRealAnt1;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroTotalRealAnt2 >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalRealAnt2;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroTotalRealAnt3 >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalRealAnt3;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroTotalRealAnt4 >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalRealAnt4;
                            lnNivel:=lnNivel+1;
                        end if;
                        if(lnAhorroTotalRealAnt5 >0 )then
                            lnSuma:=lnSuma + lnAhorroTotalRealAnt5;
                            lnNivel:=lnNivel+1;
                        end if;

                        if(lnNivel > 0) then
                          lnPromedio:= round(lnSuma / lnNivel,2);
                        else
                          lnPromedio:= 0;
                        end if;

                        --CUARTO REGISTRO DE DETALLE
                       INSERT INTO MSG_BUZON_MENSA
                        ( OID_BUZO_MENS,  NUM_SECU ,
                          DATO_VARI_10,  DATO_VARI_11,  DATO_VARI_12,  DATO_VARI_13,  DATO_VARI_14,  DATO_VARI_15,  DATO_VARI_16,  DATO_VARI_17,  DATO_VARI_18,  DATO_VARI_19,  DATO_VARI_20,
                          IND_ESTA_MENS, CLIE_OID_CLIE, MENS_OID_MENS, MODU_OID_MODU_ORIG, VAL_NOM1_CLIE, VAL_NOM2_CLIE,  VAL_APE1_CLIE,  VAL_APE2_CLIE,  VAL_APEL_CASA_CLIE,
                          DATO_VARI_01,  DATO_VARI_02,  DATO_VARI_03,  DATO_VARI_04,  DATO_VARI_05,  DATO_VARI_06,  DATO_VARI_07,  DATO_VARI_08,  DATO_VARI_09,
                          NUM_LOTE_IMPR, FEC_GRAB,  FEC_IMPR,  IND_LIST_CONS,  PERI_OID_PERI,  IND_ACTI
                        )
                        VALUES(  MSG_BUME_SEQ.NEXTVAL,
                                 MSG_BUM2_SEQ.NEXTVAL,
                                 '' --VAR 10
                                ,'' --VAR 11
                                ,''-- VAR 12
                                ,''-- VAR 13
                                ,'' --REAL O PLAN
                                ,''--VAR 15
                                ,'' --VAR 16
                                ,''--'DV17'
                                ,''--'DV18'
                                ,''--'DV19'
                                ,''--'DV20',
                                ,NULL, r_consufactuEstab(i).CLIE_OID_CLIE,
                                lnOidMensajeMeta2,
                                lnOidModuloOrigen,
                                r_consufactuEstab(i).NOM1, r_consufactuEstab(i).NOM2, r_consufactuEstab(i).APE1, r_consufactuEstab(i).APE2, r_consufactuEstab(i).APEC,
                                'Op. Ahorro Total'--''VAR01
                                ,TO_CHAR(lnAhorroTotalRealAnt5,'99,999,999') --V02
                                ,TO_CHAR(lnAhorroTotalRealAnt4,'99,999,999') --V03
                                ,TO_CHAR(lnAhorroTotalRealAnt3,'99,999,999')--V04
                                ,TO_CHAR(lnAhorroTotalRealAnt2,'99,999,999') --V05
                                ,TO_CHAR(lnAhorroTotalRealAnt1,'99,999,999')--VAR 6
                                ,TO_CHAR(lnAhorroTotalReal,'99,999,999')--VAR 7
                                ,TO_CHAR(ROUND(lnpromedio,0),'99,999,999') --V08
                                ,'' --VAR 9
                                , NULL, SYSDATE, NULL, 1, NULL, 1);

                    ---END;
                    END LOOP;

                  END IF;
                  EXIT WHEN c_consufactuEstab%NOTFOUND;

                END LOOP;
                CLOSE c_consufactuEstab;
           END IF;


   END IF;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONSU_METAS: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONSU_METAS;

/***************************************************************************
Descripcion : devulve el texto no considerando etiquets xml configuardas
              en la tabla de parametrso del mensaje
Fecha Creacion : 03/01/2011
Autor : Sergio Buchelli
Parametros:
 psCadenaTexto : Texto del mensaje con etiquetes de XML

***************************************************************************/
FUNCTION MSG_FN_DEVUE_TEXTO_REEMP(
  psCadenaTexto VARCHAR2)
RETURN VARCHAR2
IS
 ls_devuelve VARCHAR2(2000);

 CURSOR cursorReemplazo
 IS
 SELECT X.VAL_PARA , X.VAL_REEM
 FROM MSG_PARAM_REEMP X;

BEGIN
  ls_devuelve:= psCadenaTexto;

  FOR cReemplazo IN cursorReemplazo LOOP
     ls_devuelve :=REPLACE(ls_devuelve,cReemplazo.VAL_PARA,cReemplazo.VAL_REEM);
  END LOOP;

 --ls_devuelve :=REPLACE(psCadenaTexto,lsCaracterBuscado,lsCaracterReemp);
 RETURN ls_devuelve;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_FN_DEVUE_TEXTO_REEMP: '||ls_sqlerrm);
END MSG_FN_DEVUE_TEXTO_REEMP;


/***************************************************************************
Descripcion : funcion que retorna lista de oid en un typo de dato pipeline
Fecha Creacion : 18/01/2011
Autor : Sergio Buchelli
Parametros:
 psCondigoPlantilla : Codigo de Plantilla
 psCondigoIdent : Codigo de Identificacion proceso
 psCodigoTipoDato  :codigo tipo dato
  psTipoRetorno : 0:Oid  1:Codigo
***************************************************************************/
FUNCTION MSG_FN_OBTEN_RANGO_DATO(psCodigoPlantilla  VARCHAR2,
   psCodigoIdenti    VARCHAR2,
   psCodigoTipoDato  VARCHAR2,
   psTipoRetorno VARCHAR2 )RETURN TABLA_VALORES PIPELINED
IS
 lsCodigoProceso MSG_PROCE_MENSA.COD_PROC%TYPE;
 lsResultado VARCHAR2(100);
 lsTabla VARCHAR2(100);
 lsIndicadorTabla varchar2(1);
 lsTipoDato VARCHAR2(50);

 vnIndice  NUMBER;
 vslista   VARCHAR2(32767);
 vsvalue   VARCHAR2(32767);
 psDelimitador VARCHAR2(1);

 valOid VARCHAR2(20);
 val_codigo VARCHAR2(20);
BEGIN

     SELECT COD_PROC INTO lsCodigoProceso
     FROM MSG_PROCE_MENSA
     WHERE COD_IDEN_APLI=psCodigoIdenti;


   BEGIN
          SELECT A.VAL_SELE ,
                 X.VAL_POSI,
                 X.TIP_DATO,
                 X.IND_TABL
                  into lsResultado, lsTabla,lsTipoDato,lsIndicadorTabla
         FROM MSG_CONFI_PROCE_DETAL A,
              MSG_CONFI_PLANT_TIPO_DATO X
         WHERE A.PLAN_COD_PLAN=psCodigoPlantilla
           AND A.PROC_COD_PROC= lsCodigoProceso
           AND A.TIDA_COD_TIPO_DATO=psCodigoTipoDato
          AND X.COD_TIPO_DATO=A.TIDA_COD_TIPO_DATO;

    psDelimitador:=',';
    vslista:= lsResultado;


          IF(psTipoRetorno IS NOT NULL) THEN
            --SI EL INDICADOR D
            --TABLA ES 1 SE PUEDE RETORNA COIDO O OID SI ES 0 SE TRATA DE CONTANTES , SOLO SE DEVUELVE CODIGO
             IF(lsIndicadorTabla='1')THEN
                IF(psTipoRetorno='1') THEN

                 --EL OID A CODIGO
                 --GRUPOS DE OIDS A CODIGOS
                       loop
                        vnIndice := instr(vslista,psDelimitador);
                        if vnIndice > 0 then
                            valOid := substr(vslista,1,vnIndice-1);

                            --DBMS_OUTPUT.PUT_LINE('val_oid XX  '|| valOid || ' lsTabla ' || lsTabla  );

                            SELECT CODIGO INTO val_codigo
                            FROM MSG_PLANT_TABLE
                            WHERE VAL_TABL = lsTabla
                               AND VAL_OID=valOid;


                            --DBMS_OUTPUT.PUT_LINE('val_codigo   '|| val_codigo );
                            pipe row(val_codigo);
                            vslista := substr(vslista,vnIndice+length(psDelimitador));
                        else
                              valOid := vslista;
                              SELECT CODIGO INTO val_codigo
                              FROM MSG_PLANT_TABLE
                              WHERE VAL_TABL = lsTabla
                                AND VAL_OID=valOid;
                              pipe row(val_codigo);
                            exit;
                        end if;
                       end loop;

                ELSE
                    --GRUPOS DE OIDS
                       loop
                        vnIndice := instr(vslista,psDelimitador);
                        if vnIndice > 0 then
                            pipe row(substr(vslista,1,vnIndice-1));
                            vslista := substr(vslista,vnIndice+length(psDelimitador));
                        else
                            pipe row(vslista);
                            exit;
                        end if;
                       end loop;


                END IF;
             ELSE --se trata de contantes grupos de codigos

                IF(psTipoRetorno='0') THEN
                 --EL CODIGO A OID
                 --grupo de codigos A OIDS
                   loop
                    vnIndice := instr(vslista,psDelimitador);
                    if vnIndice > 0 then
                        val_codigo := substr(vslista,1,vnIndice-1);

                        SELECT VAL_OID INTO valOid
                        FROM MSG_PLANT_TABLE
                        WHERE VAL_TABL = lsTabla
                            AND CODIGO=val_codigo;

                        pipe row(valOid);
                        vslista := substr(vslista,vnIndice+length(psDelimitador));
                    else
                          val_codigo := vslista;
                          SELECT VAL_OID INTO valOid
                          FROM MSG_PLANT_TABLE
                          WHERE VAL_TABL = lsTabla
                            AND CODIGO=val_codigo;
                          pipe row(valOid);
                        exit;
                    end if;
                   end loop;

                ELSE
                 --GRUPO DE CODIGOS
                        loop
                        vnIndice := instr(vslista,psDelimitador);
                        if vnIndice > 0 then
                            pipe row(substr(vslista,1,vnIndice-1));
                            vslista := substr(vslista,vnIndice+length(psDelimitador));
                        else
                            pipe row(vslista);
                            exit;
                        end if;
                       end loop;
                END IF;
             END IF;

          END IF;

       RETURN ;
   EXCEPTION
    WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_FN_OBTEN_RANGO_DATO SQL: '||ls_sqlerrm || ' val_oid '|| valOid || ' vnIndice '|| vnIndice) ;
   END;

    RETURN ;
EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_FN_OBTEN_RANGO_DATO: '||ls_sqlerrm);
END MSG_FN_OBTEN_RANGO_DATO;


/***************************************************************************
Descripcion : devulve la venta de atendido en la campanhas de un grupo de proceso
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidCliente  :oid cliente
  lnOidGrupoProceso : oid grupo proceso
  lnOidPeriodo :oidPeriodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_CAMPA(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  lnOidCliente NUMBER,
                                  lnOidGrupoProceso NUMBER,
                                  lnOidPeriodo NUMBER) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;
BEGIN
  lnVenta:=0;
  lsCodigoTipoSolicitud:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);
         --obteniendo las ventas
     SELECT sC.VAL_PREC_CATA_TOTA_LOCA/*+SC.VAL_PREC_CONT_TOTA_LOCA*/
       INTO lnVenta
      FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS
  	 WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
        and  SC.CLIE_OID_CLIE = lnOidCliente;

 RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
      lnVenta:=0;
 RETURN  lnVenta;
END MSG_FN_DEVUE_VENTA_CAMPA;


/***************************************************************************
Descripcion : devulve venta atendida en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_ATEND_CAMPA(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidcliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER,
                                  psCodigoPeriodo VARCHAR2) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnOidGrupoProceso NUMBER;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;

BEGIN
  lnVenta:=0;


   lsPeriodo:= campanhaInicio;
   lsCodigoTipoSolicitud:=  MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

    if(cantidadPeriodo > 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
    end if;

   if(psCodigoPeriodo = lsPeriodo) then
    lnOidGrupoProceso:=4;
   else
    lnOidGrupoProceso:=5;
   end if;


   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);
         --obteniendo las ventas
    SELECT
		NVL(SC.VAL_PREC_CATA_TOTA_LOCA+SC.VAL_PREC_CONT_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.clie_oid_clie = lnOidcliente;
		/*AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR*/

 RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;

 RETURN  lnVenta;
END MSG_FN_DEVUE_VENTA_ATEND_CAMPA;


/***************************************************************************
Descripcion : devulve el ahorro en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
  psCodigoPeriodo : codigo periodo facturacion
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CAMPA(psCodigoPlantilla VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio    VARCHAR2,
                                  cantidadPeriodo   NUMBER,
                                  lnOidCliente      NUMBER,
                                  lnpais            NUMBER,
                                  lnmarca           NUMBER,
                                  lncanal           NUMBER,
                                  psCodigoPeriodo   VARCHAR2,
                                  psMontoMeta       NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnOidGrupoProceso NUMBER;

 lnNumeroCampPlaneadas NUMBER;
 lnVentaPlaneada NUMBER;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;
BEGIN
  lnVenta:=0;

  lsCodigoTipoSolicitud:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);


   if(cantidadPeriodo=-1)then --SUMA TODODS LOS AHORROS DE LA CAMPANHA

       SELECT
		NVL(SUM(SC.VAL_GANA_TOTA_LOCA),0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC,
		NVS_CONSU_LOGRO NCL,
		NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        --AND SC. grpr_oid_grup_proc = 4
		--AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
		AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
        AND sc.CLIE_OID_CLIE = lnOidCliente
         AND((SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
                AND
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
               )
   AND psCodigoPeriodo >= NCL.CMP_INIC
   AND psCodigoPeriodo <= NCL.CMP_FINA
   AND NCL.EST_REGI != '9'
   AND NCL.EST_LOGR = '1' -- Nuevo
   ;



       RETURN  lnVenta;

   end if;

   lsPeriodo:= campanhaInicio;


    if(cantidadPeriodo > 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
    end if;


   if(psCodigoPeriodo = lsPeriodo) then
    lnOidGrupoProceso:=4;
   else
    lnOidGrupoProceso:=5;
   end if;


   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);

      --

  if(lsPeriodo <= psCodigoPeriodo) then
     -- se trata de una campnaha real
         --obteniendo las ventas AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;
		/*AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR;*/

   else--CAMPNAS PLANEADAS

     --se obtine el numero de campnhas planeadas
      lnNumeroCampPlaneadas:= VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(psCodigoPeriodo,psCodPeriodoFinMeta,lnPais,lnMarca,lnCanal)-1;

     -- se obtine el acumulado de ahorro
      SELECT
		NVL(SUM(SC.VAL_GANA_TOTA_LOCA),0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC,
		NVS_CONSU_LOGRO NCL,
		NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        --AND SC. grpr_oid_grup_proc = 4
		--AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
		AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
        AND sc.CLIE_OID_CLIE = lnOidCliente
         AND((SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
                AND
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
               )
   AND psCodigoPeriodo >= NCL.CMP_INIC
   AND psCodigoPeriodo <= NCL.CMP_FINA
   AND NCL.EST_REGI != '9'
   AND NCL.EST_LOGR = '1' -- Nuevo
   ;

             -- se obtine el valor de la meta
             if(lnNumeroCampPlaneadas >0) then
              lnVentaPlaneada:= ROUND((psMontoMeta - lnVenta) / lnNumeroCampPlaneadas);
             end if;

     --SE HACE UN REAJUSTE SI NOS ENCONTRAMOS EN LA ULTIMA CAMP PLANEADA
              if(psCodPeriodoFinMeta=lsPeriodo) then
                 lnVentaPlaneada:=(psMontoMeta - lnVenta) - (lnNumeroCampPlaneadas-1)*(lnVentaPlaneada);
              end if;

          lnVenta:=lnVentaPlaneada;
   end if;


  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
  RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_CAMPA;


/***************************************************************************
Descripcion : devulve descripcion en la campanha real o plaenada
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  campanhaInicio :campanha inicio
  psCodigoPeriodo codigo Periodo
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_DESCR_CAMPA(campanhaInicio VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN VARCHAR2
IS
 ls_devuelve VARCHAR2(2000);

 lsPeriodo VARCHAR2(6);
BEGIN

 lsPeriodo:= campanhaInicio;

 if(cantidadPeriodo > 0) then
    lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
 end if;

 IF( lsPeriodo <= psCodigoPeriodo ) THEN
   ls_devuelve := 'REAL';
 ELSE
   ls_devuelve := 'PLAN';
 END IF;

 if(cantidadPeriodo = 0) then
   RETURN '1ra ' || ls_devuelve;
 end if;

  if(cantidadPeriodo = 1) then
   RETURN '2da ' || ls_devuelve;
  end if;

  if(cantidadPeriodo = 2) then
   RETURN '3ra ' || ls_devuelve;
  end if;

  if(cantidadPeriodo = 3) then
   RETURN '4ta ' || ls_devuelve;
  end if;

 RETURN ls_devuelve;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_FN_DEVUE_DESCR_CAMPA: '||ls_sqlerrm);
END MSG_FN_DEVUE_DESCR_CAMPA;


/***************************************************************************
Descripcion : devulve venta atendida en la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_VENTA_CAMPA_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidCliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnGrupoProceso NUMBER;

 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;

BEGIN
  lnVenta:=0;

   lsPeriodo:= campanhaInicio;--periodo de facturacion
   lsCodigoTipoSolicitud:=MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

    if(cantidadPeriodo < 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
        lnGrupoProceso:=5;
    else
        lnGrupoProceso:=4;
    end if;

   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);

         --obteniendo las ventas

   IF(lnGrupoProceso = 5) THEN

     SELECT --NVL(SUM(NUM_UNID_COMPR*val_prec_cata_unit_loca),0)    INTO lnVenta
            sc.val_base_flet_docu INTO lnVenta
       FROM ped_solic_cabec sc,
            --ped_solic_posic sd,
         		ped_tipo_solic_pais tsp,
            ped_tipo_solic ts,
            mae_clien mc
     	WHERE --sd.SOCA_OID_SOLI_CABE =sc.OID_SOLI_CABE
            sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
        AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        AND ts.cod_tipo_soli = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND sc.grpr_oid_grup_proc = lnGrupoProceso
    		AND sc.perd_oid_peri = lnOidPeriodo
		    AND sc.clie_oid_clie = mc.oid_clie
        AND sc.clie_oid_clie = lnOidCliente
        --AND sd.ESPO_OID_ESTA_POSI <> 2
        ;
   ELSE

     SELECT NVL(SUM(sd.num_unid_compr * sd.val_prec_cata_unit_loca),0)  INTO lnventa
       FROM msg_tmp_pedid_clien pedc,
            ped_solic_posic sd
      WHERE pedc.cod_tipo_soli = lsCodigoTipoSolicitud
        AND sd.oid_soli_posi = pedc.oid_soli_posi
        AND pedc.clie_oid_clie = lnOidCliente
        AND sd.espo_oid_esta_posi <> 2;

   END IF;

  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
  RETURN  lnVenta;
END MSG_FN_DEVUE_VENTA_CAMPA_REAL;


/***************************************************************************
Descripcion : devulve ahorro la campmanha
Fecha Creacion : 08/02/2011
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CAMPA_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidCliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;

 lnOidPeriodo NUMBER;
 lnGrupoProceso NUMBER;
BEGIN
  lnVenta:=0;


   lsPeriodo:= campanhaInicio;--periodo facturacion
   lsCodigoTipoSolicitud:=MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

    if(cantidadPeriodo < 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
        lnGrupoProceso:=5;
    else
        lnGrupoProceso:=4;
    end if;

   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);
         --obteniendo AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS
        --,MAE_CLIEN MC
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		--AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;

  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
 RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_CAMPA_REAL;

 /***************************************************************************
Descripcion : Proceso que carga de generar mensaje de escalera de superacion
Fecha Creacion : 25/04/2011
Fecha Modificacion : 27/03/2012
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_ESCAL_SUPER
   (
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psFechaFacturacion VARCHAR2,
    psTipoProceso      VARCHAR2,
    psCondigoIdent     VARCHAR2,
    psCodigoPlantilla  VARCHAR2
   )
IS
   lnPais              NUMBER;
   lnmarca             NUMBER;
   lncanal             NUMBER;
   lnOidPeriodo        NUMBER;
   lnOidMensajeMeta    NUMBER;

   lsFechaIni          VARCHAR2(8);
   lsFichaFinal        VARCHAR2(8);
   anho                VARCHAR2(4);


   lsCodTipoSoli       ped_tipo_solic.cod_tipo_soli%TYPE;
   lnOidModuloOrigen   NUMBER;
   lsCodModulo         seg_modul.cod_modu%TYPE;

BEGIN
   -- Inicializar variables requeridas -------------------------------
   lnPais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo; -- parametro


   -- (1) SECCION MENSAJE GENERAL = EGA01 PARA TODAS LAS CONSULTORAS

   --OBTENIENDO OID MENSAJE  EGA01
   BEGIN
      SELECT TMSG.OID_MENS INTO lnOidMensajeMeta
        FROM MSG_MENSA TMSG
       WHERE TMSG.COD_MENS = 'EGA01';
   EXCEPTION
      WHEN OTHERS THEN
         lnOidMensajeMeta := '';
   END;


   INSERT INTO MSG_BUZON_MENSA
   (
    oid_buzo_mens,  num_secu ,
    ind_esta_mens, clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
    dato_vari_01,
    dato_vari_02,
    num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
   )
   (
    SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
           NULL, base.clie_oid_clie,
           lnOidMensajeMeta,
           lnOidModuloOrigen,
           base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
           base.nombre,
           base.dto,--porcentaje descuento
           NULL, SYSDATE, NULL, 1, NULL, 1
      FROM (
            SELECT pedc.clie_oid_clie,
                   mc.val_nom1 AS nom1,
                   mc.val_nom2 AS nom2,
                   mc.val_ape1 AS ape1,
                   mc.val_ape2 AS ape2,
                   mc.val_apel_casa AS apec,
                   mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                   pedc.oid_tipo_soli,
                   mc.cod_clie,
                   MAX(sp.val_porc_desc) dto
              FROM msg_tmp_pedid_clien pedc,
                   mae_clien mc,
                   ped_solic_posic sp
             WHERE pedc.cod_tipo_soli = lsCodTipoSoli
               AND pedc.clie_oid_clie = mc.oid_clie
               AND pedc.oid_soli_posi = sp.oid_soli_posi
             GROUP BY pedc.clie_oid_clie,
                   mc.val_nom1 ,
                   mc.val_nom2 ,
                   mc.val_ape1 ,
                   mc.val_ape2 ,
                   mc.val_apel_casa ,
                   mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 ,
                   pedc.oid_tipo_soli,
                   mc.cod_clie
           ) base
   );

  -- (2) EGAXX Mensaje por Código de Venta

   INSERT INTO MSG_BUZON_MENSA
   (
    oid_buzo_mens,  num_secu ,
    ind_esta_mens, clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
    dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,
    num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
   )
   (
    SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
           NULL, base.clie_oid_clie,
           base.mens_oid_mens,
           lnOidModuloOrigen,
           base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
           base.nombre,
           base.cod_vent,--'DV02'
           (
            SELECT v.val_i18n
              FROM pre_ofert_detal y,
                   pre_ofert x,
                   pre_matri_factu_cabec z,
                   v_gen_i18n_sicc v
             WHERE y.ofer_oid_ofer = x.oid_ofer
               AND z.oid_cabe = x.mfca_oid_cabe
               AND z.perd_oid_peri = lnOidPeriodo
               AND val_codi_vent = base.cod_vent
               AND v.attr_enti = 'MAE_PRODU'
               AND v.idio_oid_idio = 1
               AND v.val_oid = y.prod_oid_prod
               AND rownum=1
           ),--'DV03'
           base.num_unid_compr,--'DV04' unidades atendidas,
           base.cod_mens,--'DV05',
           NULL, SYSDATE, NULL, 1, NULL, 1
      FROM (
            SELECT pedc.clie_oid_clie,
                   mc.val_nom1 AS nom1,
                   mc.val_nom2 AS nom2,
                   mc.val_ape1 AS ape1,
                   mc.val_ape2 AS ape2,
                   mc.val_apel_casa AS apec,
                   mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                   pedc.num_unid_compr,
                   x.mens_oid_mens,
                   x.cod_mens,
                   x.cod_vent
              FROM msg_tmp_pedid_clien pedc,
                   mae_clien mc,
                   msg_mensa_coven x
             WHERE pedc.cod_tipo_soli = lsCodTipoSoli
               AND mc.oid_clie = pedc.clie_oid_clie
               AND x.cam_proc = psCodigoPeriodo
               AND x.cod_vent = pedc.val_codi_vent
               AND x.ind_acti='1'
               AND x.est_regi='1'
               AND pedc.num_unid_compr > 0
             GROUP BY pedc.clie_oid_clie, mc.val_nom1, mc.val_nom2, mc.val_ape1, mc.val_ape2, mc.val_apel_casa,
                      mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2,
                      pedc.num_unid_compr,
                      x.mens_oid_mens,
                      x.cod_mens,
                      x.cod_vent
           ) base
   );


   -- (3) EGAYY Mensaje por escalón

   INSERT INTO MSG_BUZON_MENSA
   (
    oid_buzo_mens, num_secu ,
    ind_esta_mens,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
    dato_vari_01, dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,
    num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
   )
    SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
           base.mens_oid_mens,
           lnOidModuloOrigen,
          mc.val_nom1 AS nom1, mc.val_nom2 AS nom2,
          mc.val_ape1 AS ape1, mc.val_ape2 AS ape2, mc.val_apel_casa AS apec,
          mc.val_nom1 ||' '||mc.val_nom2||' '||mc.val_ape1||' '||mc.val_ape2 AS nombre, --dato 01
          base.ventaCata, -- dato 02
          base.ran_inic, -- dato 03
          base.ran_fina, -- dato 04
           (
           SELECT esga.ran_inic
              FROM (
                    SELECT z.cod_esca, z.ran_inic
                      FROM msg_escal_ganan z
                     WHERE z.ind_acti = '1'
                       AND z.est_regi = '1'
                     ORDER BY 1 ASC
                  ) esga
            WHERE esga.cod_esca > base.cod_esca
               AND ROWNUM = 1
          ) proxrango,      -- 'dato 05 RAN_INIC_NEXT,
           NULL, SYSDATE, NULL, 1, NULL, 1
      FROM (
           SELECT pedc.clie_oid_clie,
                   x.ran_inic,
                   x.ran_fina,
                   x.cod_esca,
                  x.mens_oid_mens,
                  NVL(SUM(sd.num_unid_compr * sd.val_prec_cata_unit_loca),0) ventaCata,
                  CASE WHEN NVL(SUM(sd.num_unid_compr * sd.val_prec_cata_unit_loca),0) BETWEEN x.ran_inic AND x.ran_fina THEN 1 ELSE 0 END indVenta
              FROM msg_tmp_pedid_clien pedc,
                  msg_escal_ganan x,
                  ped_solic_posic sd
             WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND sd.oid_soli_posi = pedc.oid_soli_posi
               AND x.ind_acti = '1'
               AND x.est_regi = '1'
            GROUP BY pedc.clie_oid_clie,
                  x.ran_inic,
                  x.ran_fina,
                  x.cod_esca,
                  x.mens_oid_mens
          ) base,
          mae_clien mc
    WHERE base.clie_oid_clie = mc.oid_clie
      AND base.indVenta = 1 ;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_ESCAL_SUPER: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_ESCAL_SUPER;


/***************************************************************************
Descripcion : Proceso que carga de generar mensaje de FAMILIA SEGURA
Fecha Creacion : 30/05/2011
Fecha Modificacion : 31/03/2015
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE  MSG_PR_GENER_MENSA_FAMSE_SEGUR
 (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
 )
IS
  lnPais                    NUMBER;
  lnMarca                   NUMBER;
  lnCanal                   NUMBER;
  lnOidPeriodo              NUMBER;
  lnOidPeriodoAnt           NUMBER;
  lnOidMensajeMeta          NUMBER;
  lnOidModuloOrigen         NUMBER;
  lnOidMensajePolizaActiva  NUMBER;
  lnOidMensajeSinPoliza     NUMBER;

  lsFechaIni         VARCHAR2(8);
  lsFechaFinal       VARCHAR2(8);
  anho               VARCHAR2(4);

  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsPeriodoAnt       seg_perio_corpo.cod_peri%TYPE;

BEGIN
  -- Inicializar variables requeridas -------------------------------
  lnPais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro


  lsPeriodoAnt:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnPais, lnMarca, lnCanal, -1);
  lnOidPeriodoAnt:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodoAnt, lnMarca, lnCanal);

  -- (1) SGR01 Mensaje campaña de activación del seguro

  --OBTENIENDO OID MENSAJE  SGR01

   BEGIN
      SELECT mmc.mens_oid_mens
        INTO lnOidMensajeMeta
        FROM msg_mensa_campa mmc
       WHERE mmc.cam_proc = psCodigoPeriodo
         AND mmc.cod_mens = 'SGR01'
         AND mmc.ind_acti = '1'
         AND mmc.est_regi <> '9';
      EXCEPTION
        WHEN OTHERS THEN
          lnOidMensajeMeta:='';
   END;


  IF lnOidMensajeMeta IS NOT NULL THEN

  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,  num_secu ,
   ind_esta_mens,
   clie_oid_clie,
   mens_oid_mens,
   modu_oid_modu_orig,
   val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
   dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeMeta,
          lnOidModuloOrigen,
          base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
          base.nombre,
          base.cod_clie,
          base.cam_acti,
          TO_CHAR(base.fec_ini_cober,'dd/MM/yyyy'),--fecha inicio cobertura
          TO_CHAR(base.fec_fin_cober,'dd/MM/yyyy'),--fecha fin cobertura
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT
                  pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  pedc.oid_tipo_soli,
                  mc.cod_clie,
                  x.cam_acti,
                  c.fec_inic_poli fec_ini_cober,
                  c.fec_fina_poli fec_fin_cober
             FROM msg_tmp_pedid_clien pedc,
                  mae_clien mc,
                  sgr_famse_poliz_regis x,
                  sgr_famse_poliz y,
                  ccc_histo_cargo_famil_segur c
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND pedc.clie_oid_clie = mc.oid_clie
              AND y.cod_poli = x.poli_cod_poli
              AND x.clie_oid_clie = mc.oid_clie
              AND c.cod_poli = x.poli_cod_poli
              AND c.num_poli = x.num_poli
              AND c.cod_peri = psCodigoPeriodo
              AND x.est_poli = '3'
              AND x.est_regi = y.est_regi
              AND x.est_regi = '1'
          ) base
  );
  END IF;

  -- (2) (SGR02) Mensaje a las reingresantes

  -- Obteniendo OID Mensaje SGR02 si existe en patron campaña actual

    BEGIN
      SELECT mmc.mens_oid_mens
        INTO lnOidMensajeMeta
        FROM msg_mensa_campa mmc
       WHERE mmc.cam_proc = psCodigoPeriodo
         AND mmc.cod_mens = 'SGR02'
         AND mmc.ind_acti = '1'
         AND mmc.est_regi <> '9';
      EXCEPTION
        WHEN OTHERS THEN
          lnOidMensajeMeta:='';
     END;

  IF lnOidMensajeMeta IS NOT NULL THEN

  INSERT INTO MSG_BUZON_MENSA
  (
   oid_buzo_mens,  num_secu ,
   ind_esta_mens,
   clie_oid_clie,
   mens_oid_mens,
   modu_oid_modu_orig,
   val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
   dato_vari_01, dato_vari_02, dato_vari_03, dato_vari_04, dato_vari_05, dato_vari_06,
   num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeMeta,
          lnOidModuloOrigen,
          base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
          base.nombre,
          base.cod_clie,
          base.num_poli,--numero poliza
          base.cam_canc,--camp cancelacion
          base.est_poli,--estado
          base.des_moti,--motivo cancelacion,
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT
                  pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  pedc.oid_tipo_soli,
                  mc.cod_clie,
                  x.num_poli,
                  x.cam_canc,
                  x.est_poli,
                  (
                   SELECT z.des_moti_canc
                     FROM sgr_famse_motiv_cance z
                    WHERE z.cod_moti_canc = x.moti_cod_moti_canc
                  ) des_moti
             FROM msg_tmp_pedid_clien pedc,
                  mae_clien mc,
                  sgr_famse_poliz_regis x,
                  sgr_famse_poliz y
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND pedc.clie_oid_clie = mc.oid_clie
              AND y.cod_poli = x.poli_cod_poli
              AND x.clie_oid_clie = mc.oid_clie
              AND x.cam_canc = lsPeriodoAnt
              AND x.est_poli = '4'
              AND x.moti_cod_moti_canc = '1'
              AND x.est_regi = y.est_regi
              AND x.est_regi = '1'
          ) base
  );
  END IF;

  -- (3) (SGR03) Mensaje por rechazo de solicitudes

    -- Obteniendo OID Mensaje SGR03 si existe en patron campaña actual
      BEGIN
        SELECT mmc.mens_oid_mens
          INTO lnOidMensajeMeta
          FROM msg_mensa_campa mmc
         WHERE mmc.cam_proc = psCodigoPeriodo
           AND mmc.cod_mens = 'SGR03'
           AND mmc.ind_acti = '1'
           AND mmc.est_regi <> '9';
        EXCEPTION
          WHEN OTHERS THEN
            lnOidMensajeMeta:='';
       END;

  IF lnOidMensajeMeta IS NOT NULL THEN
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens,  num_secu ,
   ind_esta_mens,
   clie_oid_clie,
   mens_oid_mens,
   modu_oid_modu_orig,
   val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
   dato_vari_01,  dato_vari_02,  dato_vari_03,  dato_vari_04,  dato_vari_05,  dato_vari_06,
   num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
  )
  WITH temp AS
  (
   SELECT w.num_docu,
          z.cod_clie,
          w.cod_peri,
          TRUNC(w.fec_digi) fec_soli,
          w.val_obse_rech_defi
     FROM sto_docum_digit w,
          int_solic_conso_famil_segur z
    WHERE w.cod_pais = psCodigoPais
      AND w.cod_tipo_docu = 'FAS'
      AND w.num_docu = z.num_docu
      AND w.cod_clie = z.cod_clie
      AND w.ind_rech = '1'
      AND z.cod_pais = w.cod_pais
      AND (( w.cod_peri = psCodigoPeriodo ) OR
           ( w.cod_peri = lsPeriodoAnt AND
             (
              SELECT COUNT(*)
                FROM ped_solic_cabec x,
                     ped_solic_cabec y,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol
               WHERE x.clie_oid_clie = z.clie_oid_clie
                 AND x.perd_oid_peri = lnOidPeriodoAnt
                 AND x.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND tsol.cod_tipo_soli = 'SOC'
                 AND x.grpr_oid_grup_proc = 5
                 AND x.fec_fact IS NOT NULL
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.esso_oid_esta_soli <> 4
             ) = 0
           )
          )
    GROUP BY w.num_docu,
          z.cod_clie,
          w.cod_peri,
          TRUNC(w.fec_digi),
          w.val_obse_rech_defi
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeMeta,
          lnOidModuloOrigen,
          base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
          base.nombre,
          base.cod_clie,
          base.num_poli, --numero poliza
          base.cam_proc, --camp proceso
          TO_CHAR(base.fec_soli,'dd/MM/yyyy'),--fecha solicitud
          base.des_moti,--motivo cancelacion,
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT
                  pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  pedc.oid_tipo_soli,
                  mc.cod_clie,
                  t.num_docu num_poli,
                  psCodigoPeriodo cam_proc,
                  t.fec_soli fec_soli,
                  t.val_obse_rech_defi des_moti
             FROM msg_tmp_pedid_clien pedc,
                  mae_clien mc,
                  temp t
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND pedc.clie_oid_clie = mc.oid_clie
              AND t.cod_clie = mc.cod_clie
          ) base
  );

  END IF;

  -- (4) (SGR04) Mensaje por cancelacion polizas

     -- --Obteniendo OID Mensaje SGR04 si existe en patron campaña actual
      BEGIN
        SELECT mmc.mens_oid_mens
          INTO lnOidMensajeMeta
          FROM msg_mensa_campa mmc
         WHERE mmc.cam_proc = psCodigoPeriodo
           AND mmc.cod_mens = 'SGR04'
           AND mmc.ind_acti = '1'
           AND mmc.est_regi <> '9';
        EXCEPTION
          WHEN OTHERS THEN
            lnOidMensajeMeta:='';
       END;

  -- Insertamos Mensaje SGR04 --
  IF lnOidMensajeMeta IS NOT NULL THEN
  INSERT INTO msg_buzon_mensa
  (
   oid_buzo_mens, num_secu ,
   ind_esta_mens,
   clie_oid_clie,
   mens_oid_mens,
   modu_oid_modu_orig,
   val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
   dato_vari_01, dato_vari_02, dato_vari_03, dato_vari_04, dato_vari_05, dato_vari_06, dato_vari_07,
   num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
          NULL,
          base.clie_oid_clie,
          lnOidMensajeMeta,
          lnOidModuloOrigen,
          base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
          base.nombre,
          base.cod_clie,
          base.num_poli,--numero poliza
          base.cam_canc,--camp cancelacion
          base.est_poli,--estado
          base.des_moti,--motivo cancelacion,
          base.fec_fin_cober,--fecha fin de cobertura
          NULL, SYSDATE, NULL, 1, NULL, 1
     FROM (
           SELECT DISTINCT
                  pedc.clie_oid_clie,
                  mc.val_nom1 AS nom1,
                  mc.val_nom2 AS nom2,
                  mc.val_ape1 AS ape1,
                  mc.val_ape2 AS ape2,
                  mc.val_apel_casa AS apec,
                  mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                  pedc.oid_tipo_soli,
                  mc.cod_clie,
                  x.num_poli,
                  x.cam_canc,
                  x.est_poli,
                  (
                   SELECT z.des_moti_canc
                     FROM sgr_famse_motiv_cance z
                    WHERE z.cod_moti_canc = x.moti_cod_moti_canc
                  ) des_moti,
                  (
                   SELECT TO_CHAR(MAX(z.fec_fina_poli),'dd/MM/yyyy')
                     FROM ccc_histo_cargo_famil_segur z
                    WHERE z.cod_poli = x.poli_cod_poli
                      AND z.num_poli = x.num_poli
                  ) fec_fin_cober
             FROM msg_tmp_pedid_clien pedc,
                  mae_clien mc,
                  sgr_famse_poliz_regis x,
                  sgr_famse_poliz y
            WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              AND pedc.clie_oid_clie = mc.oid_clie
              AND y.cod_poli = x.poli_cod_poli
              AND x.clie_oid_clie = mc.oid_clie
              AND x.est_poli = '4'
              AND x.moti_cod_moti_canc IN( '5','7')
              AND x.est_regi= y.est_regi
              AND x.est_regi = '1'
              AND ( ( x.cam_canc = psCodigoPeriodo) OR
                    ( x.cam_canc = lsPeriodoAnt AND
                    (
                     SELECT COUNT(*)
                       FROM ped_solic_cabec x1,
                            ped_solic_cabec y1,
                            ped_tipo_solic_pais tspa,
                            ped_tipo_solic tsol
                      WHERE x1.clie_oid_clie = mc.oid_clie
                        AND x1.perd_oid_peri = lnOidPeriodoAnt
                        AND x1.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                        AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                        AND tsol.cod_tipo_soli = 'SOC'
                        AND x1.grpr_oid_grup_proc = 5
                        AND x1.fec_fact IS NOT NULL
                        AND x1.soca_oid_soli_cabe = y1.oid_soli_cabe
                        AND y1.esso_oid_esta_soli <> 4
                    ) = 0
                    )
                  )
          ) base
  );
  END IF;

  -- (5) SGR05 Mensaje Clientes con Poliza Activa

  --Obteniendo OID Mensaje SGR05 si existe en patron campaña actual
  BEGIN
     SELECT mmc.mens_oid_mens
       INTO lnOidMensajePolizaActiva
       FROM msg_mensa_campa mmc
      WHERE mmc.cam_proc = psCodigoPeriodo
        AND mmc.cod_mens = 'SGR05'
        AND mmc.ind_acti = '1'
        AND mmc.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajePolizaActiva := '';
  END;

  -- Insertamos Mensaje SGR05 --
  IF lnOidMensajePolizaActiva IS NOT NULL THEN
      INSERT INTO msg_buzon_mensa
      (
       oid_buzo_mens,
       num_secu ,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       dato_vari_03,
       dato_vari_04,
       dato_vari_05,
       num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
      )
      (
       SELECT MSG_BUME_SEQ.NEXTVAL,
              MSG_BUM2_SEQ.NEXTVAL,
              NULL,
              base.oid_clie,
              lnOidMensajePolizaActiva,
              lnOidModuloOrigen,
              base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
              base.nombre, -- DV01
              base.cod_clie, -- DV02
              base.cam_acti, -- DV03
              base.cod_peri, -- DV04
              TO_CHAR(base.fec_inic_poli,'dd/MM/yyyy'), -- DV05
              NULL, SYSDATE, NULL, 1, NULL, 1
         FROM (
               SELECT DISTINCT
                      mc.oid_clie,
                      mc.val_nom1 nom1,
                      mc.val_nom2 nom2,
                      mc.val_ape1 ape1,
                      mc.val_ape2 ape2,
                      mc.val_apel_casa apec,
                      mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 nombre,
                      mc.cod_clie,
                      fpr.cam_acti, -- Campaña Activacion
                      c.cod_peri, -- Campaña Cobertura
                      c.fec_inic_poli -- Fecha Inicio Cobertura
                 FROM msg_tmp_pedid_clien pedc,
                      mae_clien mc,
                      sgr_famse_poliz_regis fpr,
                      sgr_famse_poliz fp,
                      (SELECT cod_clie, MAX(cod_peri) cod_peri, MAX(fec_inic_poli) fec_inic_poli FROM ccc_histo_cargo_famil_segur GROUP BY cod_clie) c
                WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                  AND pedc.clie_oid_clie = mc.oid_clie
                  AND fp.cod_poli = fpr.poli_cod_poli
                  AND fpr.clie_oid_clie = mc.oid_clie
                  AND mc.cod_clie = c.cod_clie
                  AND fpr.est_poli = '3'
                  AND fpr.est_regi = '1'
                  AND fp.est_regi ='1'
              ) base
      );
  END IF;


  -- (6) SGR06 Mensaje Clientes sin Poliza

  --Obteniendo OID Mensaje SGR06 si existe en patron campaña actual
  BEGIN
     SELECT mmc.mens_oid_mens
       INTO lnOidMensajeSinPoliza
       FROM msg_mensa_campa mmc
      WHERE mmc.cam_proc = psCodigoPeriodo
        AND mmc.cod_mens = 'SGR06'
        AND mmc.ind_acti = '1'
        AND mmc.est_regi <> '9';
  EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeSinPoliza := '';
  END;

  -- Insertamos Mensaje SGR05 --
  IF lnOidMensajeSinPoliza IS NOT NULL THEN
      INSERT INTO msg_buzon_mensa
      (
       oid_buzo_mens,
       num_secu ,
       ind_esta_mens,
       clie_oid_clie,
       mens_oid_mens,
       modu_oid_modu_orig,
       val_nom1_clie, val_nom2_clie,  val_ape1_clie,  val_ape2_clie,  val_apel_casa_clie,
       dato_vari_01,
       dato_vari_02,
       num_lote_impr, fec_grab,  fec_impr,  ind_list_cons,  peri_oid_peri,  ind_acti
      )
      (
       SELECT MSG_BUME_SEQ.NEXTVAL,
              MSG_BUM2_SEQ.NEXTVAL,
              NULL,
              base.oid_clie,
              lnOidMensajeSinPoliza,
              lnOidModuloOrigen,
              base.nom1, base.nom2, base.ape1, base.ape2, base.apec,
              base.nombre, -- DV01
              base.cod_clie, -- DV02
              NULL, SYSDATE, NULL, 1, NULL, 1
         FROM (
               SELECT DISTINCT
                      mc.oid_clie,
                      mc.val_nom1 nom1,
                      mc.val_nom2 nom2,
                      mc.val_ape1 ape1,
                      mc.val_ape2 ape2,
                      mc.val_apel_casa apec,
                      mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 nombre,
                      mc.cod_clie
                 FROM msg_tmp_pedid_clien pedc,
                      mae_clien mc
                WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                  AND pedc.clie_oid_clie = mc.oid_clie
                  AND mc.oid_clie NOT IN (
                                          SELECT clie_oid_clie
                                            FROM sgr_famse_poliz_regis fpr,
                                                 sgr_famse_poliz fp
                                           WHERE fp.cod_poli = fpr.poli_cod_poli
                                             AND fpr.est_poli = '3'
                                             AND fpr.est_regi = '1'
                                             AND fp.est_regi ='1'
                                         )
              ) base
      );
  END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_FAMSE_SEGUR: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_FAMSE_SEGUR;


/***************************************************************************
Descripcion : Insertamos mensajes impresos en Buzon Historico y los eliminamos
	            del buzon de mensa,jes
Fecha Creacion : 09/08/2011
Fecha Modificacion: 25/09/2014
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
***************************************************************************/
PROCEDURE  MSG_PR_ELIMI_BUZON_MENSA(
  psCodigoPais       VARCHAR2
)
IS

  CURSOR c_buzon IS

    SELECT a.oid_buzo_mens ID
      FROM msg_buzon_mensa a
     WHERE (a.ind_acti = 0 AND a.fec_impr IS NOT NULL)
        OR (a.ind_acti = 1 AND a.fec_impr IS NULL)
        OR (a.est_regi = 9);

  TYPE vID IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;

  tID vID;

  W_FILAS    NUMBER := 5000;

BEGIN

  -- Insertamos mensajes Impresos en el Buzon de Mensajes Historico ---
  INSERT INTO msg_buzon_mensa_histo (
              oid_buzo_mens, num_secu, dato_vari_10,
              dato_vari_11, dato_vari_12, dato_vari_13,
              dato_vari_14, dato_vari_15, dato_vari_16,
              dato_vari_17, dato_vari_18, dato_vari_19,
              dato_vari_20, ind_esta_mens, clie_oid_clie,
              mens_oid_mens, modu_oid_modu_orig, val_nom1_clie,
              val_nom2_clie, val_ape1_clie, val_ape2_clie,
              val_apel_casa_clie, dato_vari_01, dato_vari_02,
              dato_vari_03, dato_vari_04, dato_vari_05,
              dato_vari_06, dato_vari_07, dato_vari_08,
              dato_vari_09, num_lote_impr, fec_grab,
              fec_impr, ind_list_cons, peri_oid_peri,
              ind_acti)
   SELECT oid_buzo_mens, num_secu, dato_vari_10,
          dato_vari_11, dato_vari_12, dato_vari_13,
          dato_vari_14, dato_vari_15, dato_vari_16,
          dato_vari_17, dato_vari_18, dato_vari_19,
          dato_vari_20, ind_esta_mens, clie_oid_clie,
          mens_oid_mens, modu_oid_modu_orig, val_nom1_clie,
          val_nom2_clie, val_ape1_clie, val_ape2_clie,
          val_apel_casa_clie, dato_vari_01, dato_vari_02,
          dato_vari_03, dato_vari_04, dato_vari_05,
          dato_vari_06, dato_vari_07, dato_vari_08,
          dato_vari_09, num_lote_impr, fec_grab,
          fec_impr, ind_list_cons, peri_oid_peri,
          ind_acti
     FROM msg_buzon_mensa a
    WHERE (a.ind_acti = 0 AND a.fec_impr IS NOT NULL)
       OR (a.ind_acti = 1 AND a.fec_impr IS NULL);

  -- Eliminamos mensajes impresos del buzon de mensajes ---
  OPEN c_buzon;
    LOOP
      FETCH c_buzon BULK COLLECT INTO tID LIMIT W_FILAS;

        IF tID.COUNT > 0 THEN

          FORALL i IN tID.FIRST .. tID.LAST

            DELETE msg_buzon_mensa WHERE oid_buzo_mens = tID(i);

            COMMIT;

        END IF;

      EXIT WHEN c_buzon%NOTFOUND;

    END LOOP;

  CLOSE c_buzon;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ELIMI_BUZON_MENSA: '||ls_sqlerrm);
END MSG_PR_ELIMI_BUZON_MENSA;

/******************************************************************************
Descripcion : Proceso que genera mensajes de Premios Atendidos por Consultora
Fecha Creacion : 23/09/2011
Fecha Modificacion : 27/03/2012
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*******************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_PREMI_ATEND(
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
) IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;

BEGIN

  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);

  lnOidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,lnMarca,lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo;

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje;

  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
    oid_buzo_mens,
    num_secu,
    dato_vari_11,
    dato_vari_12,
    dato_vari_13,
    dato_vari_16,
    dato_vari_17,
    dato_vari_18,
    dato_vari_19,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    dato_vari_01,
    dato_vari_02,
    dato_vari_03,
    dato_vari_08,
    fec_grab,
    ind_list_cons,
    ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL,
          MSG_BUM2_SEQ.NEXTVAL,
        	CASE
        		  WHEN cod_ind_dent_caja = 'B' OR cod_ind_dent_caja = 'F' THEN
                (premios.descripcion || ' *')
        			ELSE
                premios.descripcion
        	END AS descripcion_producto, --var 11
          'Artículo',--var 12
        	(
      		 SELECT inc_param_nivel_premi.num_nive
      			 FROM inc_param_gener_premi,
              		inc_param_nivel_premi,
              		inc_premi_artic,
              		inc_lote_premi_artic
      			WHERE inc_param_gener_premi.copa_oid_para_gral = icpg.oid_para_gral
      				AND inc_param_nivel_premi.pagp_oid_para_gene_prem = inc_param_gener_premi.oid_para_gene_prem
      				AND inc_premi_artic.panp_oid_para_nive_prem = inc_param_nivel_premi.oid_para_nive_prem
      				AND inc_lote_premi_artic.prar_oid_prem_arti = inc_premi_artic.oid_prem_arti
      				AND inc_lote_premi_artic.num_prem = pedc.num_prem
      		) nivel, --var13
      		icpg.val_nomb, --var 16
      		icpg.num_conc, --var 17
      		premios.cod_sap,--var 18
      		pedc.val_codi_vent_fict,--var 19
      		cli.oid_clie,
          lnOidMensaje, -- parametro
          lnOidModuloOrigen, -- parametro
          (cli.val_nom1 || ' ' || cli.val_nom2) AS nombre, --var 01
          (cli.val_ape1 || ' ' || cli.val_ape2) AS apellido, --var 02
          cli.cod_clie,--var 03
          pedc.num_unid_compr AS u_atend, --var 08 validar
          SYSDATE,
          0,
          1
     FROM msg_tmp_pedid_clien pedc,
          mae_clien cli,
      		inc_concu_param_gener icpg,
      		(
      		 SELECT oid_prod,
              		val_i18n AS descripcion,
              		cod_sap,
              		cod_ind_dent_caja
      			 FROM mae_produ,
      				    (
                   SELECT val_i18n,
                          val_oid
                     FROM gen_i18n_sicc_pais WHERE attr_enti = 'MAE_PRODU'
                  ) dep
      			WHERE cod_sap NOT IN ('9999999999')
      				AND dep.val_oid = oid_prod
      		) premios,
      		(
           SELECT val_i18n,
                  val_oid
             FROM gen_i18n_sicc_comun
            WHERE attr_enti = 'PED_TIPO_SOLIC'
          ) ts
    WHERE pedc.oid_tipo_soli = ts.val_oid
      AND premios.oid_prod = pedc.prod_oid_prod
      AND pedc.clie_oid_clie = cli.oid_clie
      AND pedc.num_unid_compr > 0
      AND pedc.num_prem IS NOT NULL
      AND pedc.copa_oid_para_gene = icpg.oid_para_gral
      AND icpg.ind_dupl_cyzo = 0
		  AND (icpg.ccon_oid_clas_conc IS NULL
		       OR icpg.ccon_oid_clas_conc NOT IN (
                                              SELECT oid_clas_conc
                                                FROM inc_clasi_concu
                                               WHERE cod_clas_conc = 'A'
                                             )
          )
  );

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_PREMI_ATEND: '||ls_sqlerrm);

END MSG_PR_GENER_MENSA_PREMI_ATEND;


/***************************************************************************
Descripcion : Proceso que genera mensajes para Nuevas con Primer Pedido por
              consultora en cada facturación
Fecha Creacion : 28/09/2011
Fecha Modificacion : 27/03/2012
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED_WEB
 (psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2)
IS
  lnMarca             NUMBER;
  lnCanal             NUMBER;
  lsCodMensaje        MSG_MENSA.cod_mens%TYPE;
  lsCodModulo         SEG_MODUL.cod_modu%TYPE;

  lnOidModuloOrigen   NUMBER;
  lnOidMensaje        NUMBER;

BEGIN

  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo;

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje;


  INSERT INTO MSG_BUZON_MENSA
  (
    oid_buzo_mens,num_secu,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    dato_vari_01, dato_vari_02, dato_vari_03,dato_vari_04,dato_vari_05,
    fec_grab, ind_list_cons, ind_acti
  )
  (
	 SELECT MSG_BUME_SEQ.NEXTVAL, MSG_BUM2_SEQ.NEXTVAL,
      		oid_clie,
      		lnOidMensaje, --oidMensaje
      		lnOidModuloOrigen, --oidModuloOrigen
      		nombre, cod_clie, SUBSTR(num_docu_iden,7,4)AS iden, 'N1P', 'OPEN',
          SYSDATE, 1, 1
   	 FROM (
      		 SELECT oid_clie,
      				    cod_clie,
      				    val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 as nombre,
      				    cod_regi,
      				    cod_zona,
      				    num_docu_iden
      			 FROM msg_tmp_pedid_clien pedc,
      				    ped_solic_cabec d,
      				    mae_clien c,
      	          zon_zona e,
      				    zon_regio f,
      				    (
                   SELECT *
                     FROM mae_clien_ident
      				      WHERE val_iden_docu_prin = 1
                  )	g,
      				    (
                   SELECT *
                     FROM mae_clien_datos_adici
      				      WHERE esta_oid_esta_clie = 1
                  )	h
      			WHERE pedc.oid_soli_cabe = d.oid_soli_cabe
      				AND d.clie_oid_clie = c.oid_clie
      				AND d.zzon_oid_zona = e.oid_zona
              AND e.zorg_oid_regi = f.oid_regi
              AND c.oid_clie = h.clie_oid_clie
      				AND c.oid_clie = g.clie_oid_clie
            GROUP BY c.oid_clie,
            				 c.cod_clie,
            				 val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2,
            				 cod_regi,
            				 cod_zona,
            				 num_docu_iden
      		)ped
  );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_1EPED_WEB: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_1EPED_WEB;


/********************************************************************************
Descripcion : Proceso que genera mensajes para Consultoras Nuevas Primer Pedido
              que compran productos de una determinada unidad negocio - negocio
Fecha Creacion : 03/10/2011
Autor : Sergio Apaza
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
*********************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_1EPED_PRODU (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
) IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen   NUMBER;
  lnOidMensaje        NUMBER;

BEGIN

  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(pscodigoperiodo, lnMarca, lnCanal);


  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro '6'

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro 'SPT20'

  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa (
    oid_buzo_mens,
    num_secu,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    dato_vari_01,
    fec_grab, ind_list_cons, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL oid_buzon,
	        MSG_BUM2_SEQ.NEXTVAL secuencia,
		      oid_clie,
          lnOidMensaje,  -- parametro
          lnOidModuloOrigen,  -- parametro
          nombre,
          SYSDATE, 1, 1
	   FROM (
      		 SELECT cli.oid_clie,
      				    cli.cod_clie,
      				    cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2 AS nombre
      			 FROM msg_tmp_pedid_clien pedc,
      				    ped_solic_cabec sc,
      				    (
          				 SELECT oid_prod,
          						    val_i18n AS des_cort,
          						    uneg_oid_unid_nego,
          						    nego_oid_nego
          					 FROM	mae_produ,
          						    (
                           SELECT val_i18n, val_oid
                             FROM gen_i18n_sicc_pais
                            WHERE attr_enti = 'MAE_PRODU'
                          ) dep
          					WHERE cod_sap NOT IN ('999999999')
          						AND uneg_oid_unid_nego = 2001 -- Unidad de Negocio Cosméticos (cambiar cuando tipo dato este en gestor mensajes)
          						AND nego_oid_nego = 2004 -- Tratamiento Facial (cambiar cuando tipo dato este en gestor mensajes)
                      AND dep.val_oid = oid_prod
          				) prod,
				          mae_clien cli,
	            		zon_zona zon,
				          zon_regio reg,
                  mae_clien_datos_adici cda
      			WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
      				AND pedc.prod_oid_prod = prod.oid_prod
      				AND pedc.clie_oid_clie = cli.oid_clie
              and pedc.oid_soli_cabe = sc.oid_soli_cabe
      				AND pedc.num_unid_compr > 0
      				AND sc.zzon_oid_zona = zon.oid_zona
              AND zon.zorg_oid_regi = reg.oid_regi
      				AND pedc.clie_oid_clie = cda.clie_oid_clie
      				AND cda.esta_oid_esta_clie = 1 -- nueva primer pedido
      			GROUP BY cli.oid_clie,
      				       cli.cod_clie,
      				       cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2
		      ) ped
  );

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_1EPED_PRODU: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_1EPED_PRODU;


/***************************************************************************
Descripcion : Proceso que genera mensajes Conferencias
Fecha Creacion : 05/10/2011
Fecha Modificacion : 05/12/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONFE_TRIUN
 (psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lsPeriodoCalcuIni     seg_perio_corpo.cod_peri%TYPE;
 lsPeriodoCalcuFin     seg_perio_corpo.cod_peri%TYPE;

 lnPais                NUMBER;
 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;
 lnCantidadPeriodo     NUMBER;
 lnOidMensajeConfeCampanaActual   NUMBER;
 lnOidMensajeConfeCampanaFutura   NUMBER;


 BEGIN
   lnMarca := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   --lsCodMensaje := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);
   lnCantidadPeriodo := TO_NUMBER(MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_cantidad_campana,NULL));

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   lsPeriodoCalcuIni := GEN_FN_CALCU_PERIO(psCodigoPeriodo, lnCantidadPeriodo); -- CodCamp, NumCamp
   lsPeriodoCalcuFin:= GEN_FN_CALCU_PERIO(lsPeriodoCalcuIni, lnCantidadPeriodo - 1); -- CodCamp, NumCamp

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   --Mensaje Conferencia Campaña Actual ( CRACCA )--
   BEGIN
     SELECT msg.mens_oid_mens
       INTO lnOidMensajeConfeCampanaActual
       FROM msg_mensa_campa msg
      WHERE msg.cod_mens = CRA_CONFE_CAMPA_ACTUA
        AND msg.cam_proc = psCodigoPeriodo
        AND msg.est_regi <> '9';
   EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeConfeCampanaActual := '';
   END;

   --Mensaje Conferencia Campaña Futura ( CRACCF )--
   BEGIN
     SELECT msg.mens_oid_mens
       INTO lnOidMensajeConfeCampanaFutura
       FROM msg_mensa_campa msg
      WHERE msg.cod_mens = CRA_CONFE_CAMPA_FUTUR
        AND msg.cam_proc = psCodigoPeriodo
        AND msg.est_regi <> '9';
   EXCEPTION
     WHEN OTHERS THEN
        lnOidMensajeConfeCampanaFutura := '';
   END;

   -- Insertamos Mensaje de Conferencias de Campaña Actual --
   IF lnOidMensajeConfeCampanaActual IS NOT NULL THEN
  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
    )
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.oid_clie,
                lnOidMensajeConfeCampanaActual,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.cod_clie, -- 'DV01',
            base.cam_proc, -- 'DV02',
            base.cod_zona, -- 'DV03',
            base.nom_gere_zona, -- 'DV04',
            base.val_loca, -- 'DV05',
            base.val_dire, -- 'DV06',
            base.val_fech, -- 'DV07',
            base.val_hora, -- 'DV08',
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT DISTINCT mc.oid_clie,
                    mc.cod_clie,
                    mc.val_nom1 AS nom1,
                    mc.val_nom2 AS nom2,
                    mc.val_ape1 AS ape1,
                    mc.val_ape2 AS ape2,
                    mc.val_apel_casa AS apec,
                    mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                    conf.cam_proc,
                    conf.cod_zona,
                    conf.nom_gere_zona,
                        conf.val_loca,
                        conf.val_dire,
                        conf.val_fech,
                        conf.val_hora
                   FROM msg_tmp_pedid_clien pedc,
                        ped_solic_cabec sc,
                        mae_clien mc,
                        zon_zona zon,
                        (
                         SELECT cam_proc,
                                cod_regi,
                                cod_zona,
                                nom_gere_zona,
                                val_loca,
                                val_dire,
                                val_fech,
                                val_hora
                           FROM msg_mensa_confe
                          WHERE est_regi <> '9'
                            AND cam_proc = psCodigoPeriodo
                          ORDER BY cam_proc
                         ) conf
                   WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                     AND pedc.oid_soli_cabe = sc.oid_soli_cabe
                     AND sc.clie_oid_clie = mc.oid_clie
                     AND sc.zzon_oid_zona = zon.oid_zona
                     AND zon.cod_zona = conf.cod_zona
                ORDER BY mc.oid_clie, conf.cam_proc
                ) base
        );
   END IF;

   -- Insertamos Mensaje de Conferencias de Campañas Futuras --
   IF lnOidMensajeConfeCampanaFutura IS NOT NULL THEN
      INSERT INTO MSG_BUZON_MENSA
        (
         oid_buzo_mens,
         num_secu ,
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
         num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
        )
        (
         SELECT MSG_BUME_SEQ.NEXTVAL,
                MSG_BUM2_SEQ.NEXTVAL,
                NULL,
                base.oid_clie,
                lnOidMensajeConfeCampanaFutura,
                lnOidModuloOrigen,
                base.nom1,
                base.nom2,
                base.ape1,
                base.ape2,
                base.apec,
                base.cod_clie, -- 'DV01',
                base.cam_proc, -- 'DV02',
                base.cod_zona, -- 'DV03',
                base.nom_gere_zona, -- 'DV04',
                base.val_loca, -- 'DV05',
                base.val_dire, -- 'DV06',
                base.val_fech, -- 'DV07',
                base.val_hora, -- 'DV08',
                NULL, SYSDATE, NULL, 1, NULL, 1
           FROM (
                 SELECT DISTINCT mc.oid_clie,
                        mc.cod_clie,
                        mc.val_nom1 AS nom1,
                        mc.val_nom2 AS nom2,
                        mc.val_ape1 AS ape1,
                        mc.val_ape2 AS ape2,
                        mc.val_apel_casa AS apec,
                        mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 AS nombre,
                        conf.cam_proc,
                        conf.cod_zona,
                        conf.nom_gere_zona,
                    SUBSTR(conf.val_loca,1,70) val_loca,
                    conf.val_dire,
                    conf.val_fech,
                    '-' || conf.val_hora val_hora
               FROM msg_tmp_pedid_clien pedc,
                    ped_solic_cabec sc,
                    mae_clien mc,
                    zon_zona zon,
                    (
                     SELECT cam_proc,
                            cod_regi,
                            cod_zona,
                            nom_gere_zona,
                            val_loca,
                            val_dire,
                            val_fech,
                            val_hora
                       FROM msg_mensa_confe
                      WHERE est_regi <> '9'
                        AND cam_proc BETWEEN lsPeriodoCalcuIni AND lsPeriodoCalcuFin
                   ORDER BY cam_proc
                     ) conf
               WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                 AND pedc.oid_soli_cabe = sc.oid_soli_cabe
                 AND sc.clie_oid_clie = mc.oid_clie
                 AND sc.zzon_oid_zona = zon.oid_zona
                 AND zon.cod_zona = conf.cod_zona
            ORDER BY mc.oid_clie, conf.cam_proc
            ) base
    );
   END IF;

  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONFE_TRIUN: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONFE_TRIUN;


/********************************************************************************
Descripcion : Proceso que genera mensajes para Producto Exigido - INC
Fecha Creacion : 07/02/2012
Autor :
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion Proceso
 psCondigoPlantilla : Codigo de Plantilla
*********************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_PRODU_EXIGI (
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psTipoProceso      VARCHAR2,
  psCondigoIdent     VARCHAR2,
  psCodigoPlantilla  VARCHAR2
) IS

  lnMarca            NUMBER;
  lnCanal            NUMBER;
  lnOidPeriodo       NUMBER;
  lsCodMensaje       msg_mensa.cod_mens%TYPE;
  lsCodModulo        seg_modul.cod_modu%TYPE;
  lsCodTipoSoli      ped_tipo_solic.cod_tipo_soli%TYPE;

  lnOidModuloOrigen  NUMBER;
  lnOidMensaje       NUMBER;
  lnOidTipoSoliPais  NUMBER;

BEGIN

  -- Inicializar variables requeridas -------------------------------
  lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
  lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
  lsCodMensaje := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_mensaje, tipo_retorno_codigo);
  lsCodModulo := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_modulo_origen, tipo_retorno_codigo);
  lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(pscodigoperiodo, lnMarca, lnCanal);

  SELECT oid_modu
    INTO lnOidModuloOrigen
    FROM seg_modul
   WHERE cod_modu = lsCodModulo; -- parametro

  SELECT oid_mens
    INTO lnOidMensaje
    FROM msg_mensa
   WHERE cod_mens = lsCodMensaje; -- parametro

  SELECT tsp.oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM ped_tipo_solic ts,
		     ped_tipo_solic_pais tsp
   WHERE ts.cod_tipo_soli = lsCodTipoSoli  -- parametro
     AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli;

  -- Logica de insercion --------------------------------------------
  INSERT INTO msg_buzon_mensa
  (
    oid_buzo_mens,
    num_secu,
    clie_oid_clie,
    mens_oid_mens,
    modu_oid_modu_orig,
    dato_vari_01, dato_vari_02, dato_vari_03, dato_vari_04,
    fec_grab, ind_list_cons, ind_acti
  )
  (
   SELECT MSG_BUME_SEQ.NEXTVAL oid_buzon,
	        MSG_BUM2_SEQ.NEXTVAL secuencia,
		      clie_oid_clie,
          lnOidMensaje,  -- parametro
          lnOidModuloOrigen,  -- parametro
          cmConcurso, desPremio, cmEnvio, desEnvia,
          SYSDATE, 1, 1
	   FROM (
            SELECT pedc.clie_oid_clie,
                   (
                    SELECT spc.cod_peri
                      FROM seg_perio_corpo spc,
                           cra_perio cra
                     WHERE cra.peri_oid_peri = spc.oid_peri
                       AND cra.oid_peri = prex.perd_oid_peri_hast
                   ) cmConcurso,
                   prex.desPremio,
                   (
                    SELECT spc.cod_peri
                      FROM seg_perio_corpo spc,
                           cra_perio cra
                     WHERE cra.peri_oid_peri = spc.oid_peri
                       AND cra.oid_peri =  lnOidPeriodo -- parametro
                   ) cmEnvio,
                   prex.desEnvia
              FROM msg_tmp_pedid_clien pedc,
                   (
                    SELECT copa_oid_para_gral,
                           perd_oid_peri_hast,
                           oid_prex,
                           cod_vent,
                           oid_pdes,
                           (
                            SELECT i.val_i18n
                              FROM gen_i18n_sicc i
                             WHERE i.idio_oid_idio = 1
                               AND i.attr_enti = 'MAE_PRODU'
                               AND i.val_oid = oid_prex
                           ) desPremio,
                           (
                            SELECT i.val_i18n
                              FROM gen_i18n_sicc i
                             WHERE i.idio_oid_idio = 1
                               AND i.attr_enti = 'MAE_PRODU'
                               AND i.val_oid = oid_pdes
                           ) desEnvia
                      FROM (
                            SELECT copa_oid_para_gral,
                                   perd_oid_peri_hast,
                                   oid_prex,
                                   cod_vent,
                                   (
                                    SELECT prod_oid_prod
                                      FROM pre_matri_factu_cabec a,
                                           pre_ofert b,
                                           pre_ofert_detal c,
                                           mae_produ d,
                                           pre_matri_factu e,
                                           pre_estra f
                                     WHERE a.oid_cabe = b.mfca_oid_cabe
                                       AND b.oid_ofer = c.ofer_oid_ofer
                                       AND c.prod_oid_prod = d.oid_prod
                                       AND c.oid_deta_ofer = e.ofde_oid_deta_ofer
                                       AND e.mfca_oid_cabe = a.oid_cabe
                                       AND b.coes_oid_estr = f.oid_estr
                                       AND a.perd_oid_peri = lnOidPeriodo -- parametro
                                       AND c.val_codi_vent = cod_vent
                                   ) oid_pdes
                              FROM (
                                    SELECT copa_oid_para_gral,
                                           perd_oid_peri_hast,
                                           MIN(oid_prex) oid_prex,
                                           MIN(cod_vent) cod_vent
                                      FROM (
                                            SELECT produ.copa_oid_para_gral,
                                                   concu.perd_oid_peri_hast,
                                                   proex.prod_oid_prod oid_prex,
                                                   detal.cod_vent
                                              FROM inc_produ produ,
                                                   inc_produ_exigi proex,
                                                   inc_concu_param_gener concu,
                                                   inc_despa_corxp_detal detal
                                             WHERE proex.prdu_oid_prod = produ.oid_prod
                                               AND produ.copa_oid_para_gral = concu.oid_para_gral
                                               AND produ.copa_oid_para_gral = detal.copa_oid_para_gral
                                               AND detal.perd_oid_peri = lnOidPeriodo -- parametro
                                               AND produ.copa_oid_para_gral in (
                                                                                SELECT cab.copa_oid_para_gral
                                                                                  FROM inc_despa_corxp_cabec cab
                                                                                 WHERE cab.perd_oid_peri = lnOidPeriodo -- parametro
                                                                                   AND cab.ind_acti = '1'
                                                                               )
                                           )
                                     GROUP BY copa_oid_para_gral, perd_oid_peri_hast
                                   )
                           )
                   ) prex
             WHERE pedc.cod_tipo_soli = lsCodTipoSoli -- parametro
               AND pedc.perd_oid_peri = lnOidPeriodo -- parametro
               AND pedc.val_codi_vent = prex.cod_vent
          )
    );

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_PRODU_EXIGI: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_PRODU_EXIGI;


/***************************************************************************
Descripcion : Valida el Mensaje si es todo ok graba las tablas principales
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psCodigoPais            Codigo Pais,
  psCampanhaProceso       Campaña Proceso,
  psCodigoDocumento       Codigo Documento,
  psDescripcionPatron     Descripcion Patron ,
  psTipoMensaje           Tipo Mensaje,
  psCodigoModulo          Codigo Modulo,
  psCodigoMensaje         Codigo Mensaje,
  psCodigoSeccion         Codigo Seccion,
  psCorrelativo           Correlativo,
  psIndicadorActivo       Indicador Activo,
  psDescripcionMensaje    Descripcin Mensaje,
  psTextoMensaje          Texto mensaje,
  psOidPatron             Oid Patron,
  psOidPeriodoCorpo       Oid Periodo,
  psCodigoUsuario         Codigo usuario,
  psMensajeResultado       Mensaje Resultado
***************************************************************************/
PROCEDURE MSG_PR_VALID_MENSA
 (psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psTipoMensaje           VARCHAR2,
  psOidModulo             VARCHAR2,
  psCodigoMensaje         VARCHAR2,
  psCodigoSeccion         VARCHAR2,
  psCorrelativo           VARCHAR2,
  psIndicadorActivo       VARCHAR2,
  psDescripcionMensaje    VARCHAR2,
  psTextoMensaje          VARCHAR2,
  psTextoHtml             VARCHAR2,
  psOidPatron             VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoUsuario         VARCHAR2,
  psNombreArchivoImgFondo VARCHAR2,
  psMensajeResultado      OUT VARCHAR2
 )
IS
 lnpais number;
 lnOidPatron number;
 lnOidPatronSecci  number;
 lnOidMensaje      number;
 lsCodigoPatron    MSG_PATRO.COD_PATR%TYPE;
 lsAbrvSeccionDocu MSG_SECCI_DOCUM.ABR_SECC_DOCU%TYPE;
 lnCont            number;
 lsCampanhaActual  VARCHAR2(6);
 lnOidPeri NUMBER;
 ldFecha   DATE;
 lsFecha   VARCHAR2(10);
 lnContPatronMensa NUMBER;


BEGIN
  lnpais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

      /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');

    /*SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;

    SELECT A.COD_PERI
      INTO lsCampanhaActual
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = lnOidPeri
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';*/
    select COD_PERI
       INTO lsCampanhaActual
    from bas_ctrl_fact
    where   STA_CAMP=0
     and  IND_CAMP_ACT =1;

  if(psOidPatron is not null  and length(psOidPatron)>0) then

    --venos si existe seccion en secci ´patrom , si no existe lo cramos
    select count(1) into lnCont
    from MSG_PATRO_SECCI x
    where  x.PATR_OID_PATR = psOidPatron
     and x.MDOC_COD_MENS_DOCU = psCodigoDocumento
     and x.MSEC_COD_SECC_DOCU = psCodigoSeccion;

    UPDATE MSG_PATRO
    SET DES_PATR= psDescripcionPatron ,
        USU_MODI = psCodigoUsuario,
        FEC_MODI = SYSDATE
    WHERE OID_PATR = psOidPatron;

    if( lnCont = 0 ) then


       SELECT lower(ABR_SECC_DOCU) into lsAbrvSeccionDocu
       FROM MSG_SECCI_DOCUM
       WHERE MDOC_COD_MENS_DOCU = psCodigoDocumento
        AND COD_SECC_DOCU = psCodigoSeccion;

       SELECT lower(COD_PATR) INTO lsCodigoPatron
       FROM MSG_PATRO
       WHERE  OID_PATR = psOidPatron;

       select MSG_PASE_SEQ.NEXTVAL into lnOidPatronSecci from dual;

       INSERT INTO MSG_PATRO_SECCI (
            OID_PATR_SECC,
            COD_SECC,
            PATR_OID_PATR,
            NUM_ORDE_SECC,
            METC_OID_META,
            MDOC_COD_MENS_DOCU,
            MSEC_COD_SECC_DOCU,
            IND_ACTI,
            USU_MODI,
            FEC_MODI,
            EST_REGI)
        VALUES (lnOidPatronSecci,
                 (lsCodigoPatron || '_' ||lsAbrvSeccionDocu),
                 psOidPatron,
                 (NVL((select MAX(X.NUM_ORDE_SECC) +1 FROM MSG_PATRO_SECCI X WHERE X.PATR_OID_PATR=psOidPatron),0) ),
                 (SELECT OID_META FROM MSG_METAC WHERE COD_META=1001),
                 psCodigoDocumento,
                 psCodigoSeccion,
                 '1',
                 psCodigoUsuario,
                 SYSDATE,
                 '1');
    else

       SELECT OID_PATR_SECC  INTO lnOidPatronSecci
       FROM MSG_PATRO_SECCI
       WHERE PATR_OID_PATR=psOidPatron
        and MDOC_COD_MENS_DOCU = psCodigoDocumento
        and MSEC_COD_SECC_DOCU = psCodigoSeccion;

    end if;

       begin
        SELECT OID_MENS  INTO lnOidMensaje
        FROM MSG_MENSA
        WHERE COD_MENS=psCodigoMensaje;
        	--AND modu_oid_modu= psOidModulo;
		    --and peri_oid_peri= psOidPeriodoCorpo;
       exception
        when others then
            lnOidMensaje:=null;
       end;

       if(lnOidMensaje is not null) then

                 begin

                   INSERT INTO MSG_MENSA_CAMPA (
                    PAIS_COD_PAIS,
                    CAM_PROC,
                    MENS_OID_MENS,
                    COD_MENS,
                    DES_MENS,
                    VAL_TEXT,
                    VAL_HTML,
                    MODU_OID_MODU,
                    PERI_OID_PERI,
                    TMEN_OID_TIPO_MENS,
                    IND_EDIT,
                    IND_ACTI,
                    NOM_IMAG_FOND,
                    USU_CREA,
                    FEC_CREA,
                    EST_REGI)
                    VALUES (
                         psCodigoPais,
                         psCampanhaProceso,
                         lnOidMensaje,
                         psCodigoMensaje,
                         psDescripcionMensaje,
                         psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
                         psTextoHtml,--cONTENIDO DE mensaje en formato html
                         psOidModulo,
                         psOidPeriodoCorpo,
                         psTipoMensaje,
                         1,
                         1,
                         psNombreArchivoImgFondo,
                         psCodigoUsuario,
                         sysdate,
                         1 );

                  exception
                   when others then

                        UPDATE MSG_MENSA_CAMPA
                        SET    DES_MENS           = psDescripcionMensaje,
                               VAL_TEXT           = psTextoMensaje,
                               VAL_HTML           = psTextoHtml,
                               TMEN_OID_TIPO_MENS = psTipoMensaje,
                               USU_MODI           = psCodigoUsuario,
                               FEC_MODI           = SYSDATE,
                               IND_ACTI           = psIndicadorActivo,
                               NOM_IMAG_FOND      = psNombreArchivoImgFondo
                        WHERE  MENS_OID_MENS           = lnOidMensaje
                          AND CAM_PROC=psCampanhaProceso
                          AND COD_MENS =psCodigoMensaje;
                  end;

                 --SOLO SI CAMPANHA DE PROCESO DEL MENSAJE ES IGUAL ALA CAMPANHA ACTUAL
                 IF(lsCampanhaActual=psCampanhaProceso) THEN
                            UPDATE MSG_MENSA
                            SET    DES_MENS           = psDescripcionMensaje,
                                   VAL_TEXT           = psTextoMensaje,
                                   TMEN_OID_TIPO_MENS = psTipoMensaje,
                                   USU_MODI           = psCodigoUsuario,
                                   FEC_MODI           = SYSDATE
                            WHERE  OID_MENS           = lnOidMensaje;
                 END IF;


                select count(1) into lnContPatronMensa
                from MSG_PATRO_MENSA
                where
                  PASE_OID_PATR_SECC =lnOidPatronSecci
                 and MENS_OID_MENS =  lnOidMensaje;

                if(lnContPatronMensa = 0) then
                     INSERT INTO MSG_PATRO_MENSA (
                       OID_PATR_MENS,
                       PASE_OID_PATR_SECC,
                       MENS_OID_MENS,
                       NUM_ORDE_IMPR,
                       IND_ACTI,
                       USU_MODI,
                       FEC_MODI,
                       EST_REGI)
                    VALUES (MSG_PAMS_SEQ.NEXTVAL ,
                            lnOidPatronSecci,
                            lnOidMensaje,
                            psCorrelativo ,
                            '1' ,
                            psCodigoUsuario ,
                            SYSDATE,
                             '1');
                 end if;

       else

          select MSG_MENS_SEQ.NEXTVAL into lnOidMensaje from DUAL;

          INSERT INTO MSG_MENSA (
            OID_MENS,
            COD_MENS,
            PAIS_OID_PAIS,
            DES_MENS,
            VAL_BLOQ_GRUP_PERM,
            FEC_PERM_DESD,
            FEC_PERM_HAST,
            VAL_TEXT,
            MODU_OID_MODU,
            PERI_OID_PERI_DESD,
            PERI_OID_PERI_HAST,
            TPER_OID_TIPO_PERM,
            TMEN_OID_TIPO_MENS,
            IND_EXCL_TIPO,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
        VALUES ( lnOidMensaje,
             psCodigoMensaje,
             lnpais,
             psDescripcionMensaje,
             null,
             null,
             null,
             psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
             psOidModulo,
             null,
             null,
             2,
             psTipoMensaje,
             null,
             psCodigoUsuario,
             sysdate,1 );


          INSERT INTO MSG_MENSA_CAMPA (
            PAIS_COD_PAIS,
            CAM_PROC,
            MENS_OID_MENS,
            COD_MENS,
            DES_MENS,
            VAL_TEXT,
            VAL_HTML,
            MODU_OID_MODU,
            PERI_OID_PERI,
            TMEN_OID_TIPO_MENS,
            IND_EDIT,
            IND_ACTI,
            NOM_IMAG_FOND,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
        VALUES (
             psCodigoPais,
             psCampanhaProceso,
             lnOidMensaje,
             psCodigoMensaje,
             psDescripcionMensaje,
             psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
             psTextoHtml,
             psOidModulo,
             psOidPeriodoCorpo,
             psTipoMensaje,
             1,
             1,
             psNombreArchivoImgFondo,
             psCodigoUsuario,
             sysdate,
             1 );

        INSERT INTO MSG_PATRO_MENSA (
           OID_PATR_MENS,
           PASE_OID_PATR_SECC,
           MENS_OID_MENS,
           NUM_ORDE_IMPR,
           IND_ACTI,
           USU_MODI,
           FEC_MODI,
           EST_REGI)
        VALUES (MSG_PAMS_SEQ.NEXTVAL ,
                lnOidPatronSecci,
                lnOidMensaje,
                psCorrelativo ,
                '1' ,
                psCodigoUsuario ,
                SYSDATE,
                 '1');


        --psMensajeResultado:= lnOidMensaje;

       end if;


        psMensajeResultado:=psOidPatron||','||lnOidMensaje;

  else
       --es nuevo patron insertar todo
         select MSG_PATR_SEQ.NEXTVAL into lnOidPatron from dual;
         select MSG_PASE_SEQ.NEXTVAL into lnOidPatronSecci from dual;
         select NVL((SELECT lower(SUBSTR(ABR_DOCU,1,4)) FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')
                  || substr(psCampanhaProceso,5,2)
                  || substr(psCampanhaProceso,1,4)
                  || NVL((SELECT lower(SUBSTR(ABR_DOCU,5)) FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')
          into   lsCodigoPatron
         from dual;


        --SE BUSCA SI MENSAJE EXISTA
        begin
         SELECT oid_mens into lnOidMensaje
         FROM MSG_MENSA
         WHERE COD_MENS = psCodigoMensaje;
       exception
          when others then
            lnOidMensaje:=null ;
       end;



        INSERT INTO MSG_PATRO (
            OID_PATR,
            COD_PATR,
            IND_ACTI,
            MEEP_OID_MEDI_ENVI_PAIS,
            FORS_OID_FORM,
            DES_PATR,
            IND_PATR_PERI,
            PAIS_OID_PAIS,
            MDOC_COD_MENS_DOCU,
            EST_REGI,
            USU_MODI,
            FEC_MODI)
        VALUES (lnOidPatron,
            (LOWER(lsCodigoPatron)),
            '1',
            (NVL((SELECT MEEP_OID_MEDI_ENVI_PAIS FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')),
            (NVL((SELECT FORS_OID_FORM FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')),
            psDescripcionPatron,
            1,
            lnpais,
            psCodigoDocumento,
            '1',
             psCodigoUsuario,
             SYSDATE);


        INSERT INTO MSG_PATRO_PERIO (
           OID_PATR_PERI,
           PATR_OID_PATR_ORIG,
           PATR_OID_PATR,
           PERI_OID_PERI,
           IND_ACTI,
           USU_MODI,
           FEC_MODI,
            EST_REGI)
       VALUES ( MSG_PAPE_SEQ.nextval,
        lnOidPatron,--null,
        lnOidPatron,
        psOidPeriodoCorpo,
        '1',
        psCodigoUsuario,
        SYSDATE,
        '1');

       SELECT lower(ABR_SECC_DOCU) into lsAbrvSeccionDocu
       FROM MSG_SECCI_DOCUM
       WHERE MDOC_COD_MENS_DOCU = psCodigoDocumento
        AND COD_SECC_DOCU = psCodigoSeccion;

       INSERT INTO MSG_PATRO_SECCI (
            OID_PATR_SECC,
            COD_SECC,
            PATR_OID_PATR,
            NUM_ORDE_SECC,
            METC_OID_META,
            MDOC_COD_MENS_DOCU,
            MSEC_COD_SECC_DOCU,
            IND_ACTI,
            USU_MODI,
            FEC_MODI,
            EST_REGI)
        VALUES (lnOidPatronSecci ,
                 (lsCodigoPatron || '_' ||lsAbrvSeccionDocu),
                 lnOidPatron,
                 (NVL((select MAX(X.NUM_ORDE_SECC) +1 FROM MSG_PATRO_SECCI X WHERE X.PATR_OID_PATR=lnOidPatron),0)),
                 (SELECT OID_META FROM MSG_METAC WHERE COD_META=1001),
                 psCodigoDocumento,
                 psCodigoSeccion,
                 '1',
                 psCodigoUsuario,
                 SYSDATE,
                 '1');

      if(lnOidMensaje is null) then

         select MSG_MENS_SEQ.NEXTVAL into lnOidMensaje from dual;

          INSERT INTO MSG_MENSA (
            OID_MENS,
            COD_MENS,
            PAIS_OID_PAIS,
            DES_MENS,
            VAL_BLOQ_GRUP_PERM,
            FEC_PERM_DESD,
            FEC_PERM_HAST,
            VAL_TEXT,
            MODU_OID_MODU,
            PERI_OID_PERI_DESD,
            PERI_OID_PERI_HAST,
            TPER_OID_TIPO_PERM,
            TMEN_OID_TIPO_MENS,
            IND_EXCL_TIPO,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
        VALUES ( lnOidMensaje,
             psCodigoMensaje,
             lnpais,
             psDescripcionMensaje,
             null,
             null,
             null,
             psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
             psOidModulo,
             null,
             null,
             2,
             psTipoMensaje,
             null,
             psCodigoUsuario,
             sysdate,1 );


          INSERT INTO MSG_MENSA_CAMPA (
            PAIS_COD_PAIS,
            CAM_PROC,
            MENS_OID_MENS,
            COD_MENS,
            DES_MENS,
            VAL_TEXT,
            VAL_HTML,
            MODU_OID_MODU,
            PERI_OID_PERI,
            TMEN_OID_TIPO_MENS,
            IND_EDIT,
            IND_ACTI,
            NOM_IMAG_FOND,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
        VALUES (
             psCodigoPais,
             psCampanhaProceso,
             lnOidMensaje,
             psCodigoMensaje,
             psDescripcionMensaje,
             psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
             psTextoHtml,
             psOidModulo,
             psOidPeriodoCorpo,
             psTipoMensaje,
             1,
             1,
             psNombreArchivoImgFondo,
             psCodigoUsuario,
             sysdate,
             1 );
      else
         --insertamos el mensaje para la campanha
          begin

           INSERT INTO MSG_MENSA_CAMPA (
            PAIS_COD_PAIS,
            CAM_PROC,
            MENS_OID_MENS,
            COD_MENS,
            DES_MENS,
            VAL_TEXT,
            VAL_HTML,
            MODU_OID_MODU,
            PERI_OID_PERI,
            TMEN_OID_TIPO_MENS,
            IND_EDIT,
            IND_ACTI,
            NOM_IMAG_FOND,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
            VALUES (
                 psCodigoPais,
                 psCampanhaProceso,
                 lnOidMensaje,
                 psCodigoMensaje,
                 psDescripcionMensaje,
                 psTextoMensaje,--Contenido de Mensaje transformado al lenguaje Xerox
                 psTextoHtml,
                 psOidModulo,
                 psOidPeriodoCorpo,
                 psTipoMensaje,
                 1,
                 1,
                 psNombreArchivoImgFondo,
                 psCodigoUsuario,
                 sysdate,
                 1 );

              --sctualizamos mensaje si campanha = proceso

          exception
           when others then

                UPDATE MSG_MENSA_CAMPA
                SET    DES_MENS           = psDescripcionMensaje,
                       VAL_TEXT           = psTextoMensaje,
                       VAL_HTML           = psTextoHtml,
                       TMEN_OID_TIPO_MENS = psTipoMensaje,
                       NOM_IMAG_FOND      = psNombreArchivoImgFondo,
                       USU_MODI           = psCodigoUsuario,
                       FEC_MODI           = SYSDATE,
                       IND_ACTI           = psIndicadorActivo
                WHERE  MENS_OID_MENS           = lnOidMensaje
                  AND CAM_PROC=psCampanhaProceso
                  AND COD_MENS =psCodigoMensaje;
          end;

     --SOLO SI CAMPANHA DE PROCESO DEL MENSAJE ES IGUAL ALA CAMPANHA ACTUAL
                 IF(lsCampanhaActual=psCampanhaProceso) THEN
                            UPDATE MSG_MENSA
                            SET    DES_MENS           = psDescripcionMensaje,
                                   VAL_TEXT           = psTextoMensaje,
                                   TMEN_OID_TIPO_MENS = psTipoMensaje,
                                   USU_MODI           = psCodigoUsuario,
                                   FEC_MODI           = SYSDATE
                            WHERE  OID_MENS           = lnOidMensaje;
                 END IF;


      end if;

        INSERT INTO MSG_PATRO_MENSA (
           OID_PATR_MENS,
           PASE_OID_PATR_SECC,
           MENS_OID_MENS,
           NUM_ORDE_IMPR,
           IND_ACTI,
           USU_MODI,
           FEC_MODI,
           EST_REGI)
        VALUES (MSG_PAMS_SEQ.NEXTVAL ,
                lnOidPatronSecci,
                lnOidMensaje,
                psCorrelativo ,
                '1' ,
                psCodigoUsuario ,
                SYSDATE,
                 '1');


        psMensajeResultado:= lnOidPatron ||','||lnOidMensaje;
        RETURN;
  end if;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_VALID_MENSA: '||ls_sqlerrm);
END MSG_PR_VALID_MENSA;


/***************************************************************************
Descripcion : Valida el Mensaje si es todo ok graba las tablas principales
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psCodigoPais            Codigo Pais,
  psCampanhaProceso       Campaña Proceso,
  psCodigoDocumento       Codigo Documento,
  psDescripcionPatron     Descripcion Patron ,
  psTipoMensaje           Tipo Mensaje,
  psCodigoModulo          Codigo Modulo,
  psCodigoMensaje         Codigo Mensaje,
  psCodigoSeccion         Codigo Seccion,
  psCorrelativo           Correlativo,
  psIndicadorActivo       Indicador Activo,
  psDescripcionMensaje    Descripcin Mensaje,
  psTextoMensaje          Texto mensaje,
  psOidPatron             Oid Patron,
  psOidPeriodoCorpo       Oid Periodo,
  psCodigoUsuario         Codigo usuario,
  psMensajeResultado       Mensaje Resultado
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA
 (psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psTipoMensaje           VARCHAR2,
  psOidModulo             VARCHAR2,
  psCodigoMensaje         VARCHAR2,
  psCodigoSeccion         VARCHAR2,
  psCorrelativo           VARCHAR2,
  psIndicadorActivo       VARCHAR2,
  psEstadoRegistro        VARCHAR2,
  psDescripcionMensaje    VARCHAR2,
  psTextoMensaje          VARCHAR2,
  psTextoHtml             VARCHAR2,
  psOidPatron             VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoUsuario         VARCHAR2,
  psIndicadorActualizar   VARCHAR2,
  psNombreArchivoImgFondo VARCHAR2,
  psMensajeResultado      OUT VARCHAR2
 )
IS
 lnpais number;
 lnOidPatron number;
 lnOidPatronSecci  number;
 lnOidMensaje      number;
 lsCodigoPatron    MSG_PATRO.COD_PATR%TYPE;
 lsAbrvSeccionDocu MSG_SECCI_DOCUM.ABR_SECC_DOCU%TYPE;
 lnCont            number;
 lsCampanhaActual  VARCHAR2(6);
 lnOidPeri NUMBER;
 ldFecha   DATE;
 lsFecha   VARCHAR2(10);


BEGIN
  lnpais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

   /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');

    /*SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;

    SELECT A.COD_PERI
      INTO lsCampanhaActual
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = lnOidPeri
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';*/

    select COD_PERI
       INTO lsCampanhaActual
    from bas_ctrl_fact
    where   STA_CAMP=0
     and  IND_CAMP_ACT =1;

  IF(psIndicadorActualizar = '1') THEN
    --ACTUALIZACION
    UPDATE MSG_PATRO
    SET DES_PATR= psDescripcionPatron
    WHERE OID_PATR = psOidPatron;


        SELECT MENS_OID_MENS  INTO lnOidMensaje
        FROM MSG_MENSA_CAMPA
        WHERE COD_MENS=psCodigoMensaje
          AND CAM_PROC =psCampanhaProceso;
        --	AND modu_oid_modu= psOidModulo
		--    and peri_oid_peri= psOidPeriodoCorpo;

                UPDATE MSG_MENSA_CAMPA
                SET    DES_MENS           = psDescripcionMensaje,
                       VAL_TEXT           = psTextoMensaje,
                       VAL_HTML           = psTextoHtml,
                       TMEN_OID_TIPO_MENS = psTipoMensaje,
                       USU_MODI           = psCodigoUsuario,
                       FEC_MODI           = SYSDATE,
                       IND_ACTI           = psIndicadorActivo,
                       NOM_IMAG_FOND      = psNombreArchivoImgFondo
                WHERE  MENS_OID_MENS           = lnOidMensaje
                  AND CAM_PROC=psCampanhaProceso
                  AND COD_MENS =psCodigoMensaje;

               IF(psCampanhaProceso = lsCampanhaActual) THEN
                  UPDATE MSG_MENSA
                    SET    DES_MENS           = psDescripcionMensaje,
                           VAL_TEXT           = psTextoMensaje,
                           TMEN_OID_TIPO_MENS = psTipoMensaje,
                           USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE
                           --IND_ACTI           = psIndicadorActivo
                    WHERE  OID_MENS           = lnOidMensaje;
               END IF;

          psMensajeResultado:=psOidPatron||','||lnOidMensaje;

  ELSE
   --ELIMINACION
     begin
      SELECT MENS_OID_MENS  INTO lnOidMensaje
        FROM MSG_MENSA_CAMPA
        WHERE COD_MENS=psCodigoMensaje
        	AND CAM_PROC= psCampanhaProceso;
     exception
       WHEN OTHERS THEN
       lnOidMensaje:=null;
     end;

      if(lnOidMensaje is not null)then
        --obtenemos el oid_patr_secc
        /*
        El sistema verifica que exista en memoria otro registro distinto en la entidad Maestro Mensajes
        con los datos de Documento, campaña y sección del mensaje eliminado.
        */
          select OID_PATR_SECC into lnOidPatronSecci
          from MSG_PATRO_SECCI x
            where  x.PATR_OID_PATR = psOidPatron
             and x.MDOC_COD_MENS_DOCU = psCodigoDocumento
             and x.MSEC_COD_SECC_DOCU = psCodigoSeccion
             and x.ind_acti = 1 ;

        --verica mensajes en la seccion
          select count(1) into lnCont
          from msg_patro_mensa
          where PASE_OID_PATR_SECC = lnOidPatronSecci
           and  MENS_OID_MENS <> lnOidMensaje
           and IND_ACTI =  1  ;

        --eliminamos mensajes cabeceras y detalles
        --eliminamos consideraciones y restricciones
                   delete from msg_cores_mensa_detal
                      where meca_cam_proc = psCampanhaProceso
                         and meca_cod_mens = psCodigoMensaje;


                     delete from msg_cores_mensa_cabec
                      where meca_cam_proc = psCampanhaProceso
                         and meca_cod_mens = psCodigoMensaje;




          if(lnCont= 0) then
            /*

            El sistema verifica que exista otro registro en memoria de la entidad Sección Patrón
            con los datos de Documento y campaña de la sección eliminada.
               UPDATE MSG_PATRO CON OID_:PATRO A ELIMINADO
            */
                 --eliminamos mensaje campa imagenes
                 delete from MSG_MENSA_CAMPA_IMAGE
                 where pais_cod_pais = psCodigoPais
                   AND MECA_CAM_PROC =  psCampanhaProceso
                   and MECA_COD_MENS = psCodigoMensaje;

                  --eliminamos mensaje_campa
                     delete from msg_mensa_campa
                      where cam_proc =  psCampanhaProceso
                        and COD_MENS = psCodigoMensaje;

                /*
                IF(psCampanhaProceso = lsCampanhaActual) THEN
                  UPDATE MSG_MENSA
                    SET    USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE,
                          -- IND_ACTI           = psIndicadorActivo,
                           EST_REGI            = psEstadoRegistro
                    WHERE  OID_MENS           = lnOidMensaje;
                END IF;
                */

                 --eliminamos patron mensa
                  delete from msg_patro_mensa
                   where pase_oid_patr_secc = lnOidPatronSecci
                      and  MENS_OID_MENS = lnOidMensaje;

                    --eliminamos ls secciones
                  delete from msg_patro_secci
                   where patr_oid_patr =  psOidPatron
                     and MDOC_COD_MENS_DOCU = psCodigoDocumento
                      and MSEC_COD_SECC_DOCU = psCodigoSeccion;

            --verificar secciones y documentos en el patrom
              SELECT COUNT(1) INTO lnCont
              from MSG_PATRO_SECCI x
                where  x.PATR_OID_PATR = psOidPatron
                 and x.MDOC_COD_MENS_DOCU = psCodigoDocumento
                 AND x.OID_PATR_SECC <> lnOidPatronSecci
                 and x.ind_acti = 1 ;


               --no existen secciones activas para el patron, por lotanto eliminamos el patron
               if(lnCont = 0) then

                --se procede a eliminar ltodo el patron de ese periodo Y DOCUMENTO
                  delete from msg_patro_perio
                  where
                    PATR_OID_PATR = psOidPatron
                    AND PERI_OID_PERI = psOidPeriodoCorpo;

                  delete from msg_patro
                  where  oid_patr  = psOidPatron;

               end if;

          ELSE
                --eliminamos mensaje campa imagenes
                 delete from MSG_MENSA_CAMPA_IMAGE
                 where pais_cod_pais = psCodigoPais
                   AND MECA_CAM_PROC =  psCampanhaProceso
                   and MECA_COD_MENS = psCodigoMensaje;

                --eliminamos mensaje_campa
                     delete from msg_mensa_campa
                      where cam_proc =  psCampanhaProceso
                        and COD_MENS = psCodigoMensaje;


                 --eliminamos patron mensa
                  delete from msg_patro_mensa
                   where pase_oid_patr_secc = lnOidPatronSecci
                      and  MENS_OID_MENS = lnOidMensaje;

                /*
                IF(psCampanhaProceso = lsCampanhaActual) THEN
                  UPDATE MSG_MENSA
                    SET    USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE,
                          -- IND_ACTI           = psIndicadorActivo,
                           EST_REGI            = psEstadoRegistro
                    WHERE  OID_MENS           = lnOidMensaje;
                END IF;
                */
          end if;



        --ACTUALIZAMOS SI EXISTE MENSAJES EN E BUZON X LISTA EXTERNA
       UPDATE MSG_BUZON_MENSA
	   SET EST_REGI = '9',
	       IND_ACTI =0,
	       USU_MODI = psCodigoUsuario,
	       FEC_MODI = SYSDATE
	   WHERE
		 MENS_OID_MENS = lnOidMensaje
		 AND MODU_OID_MODU_ORIG = psOidModulo
		 AND PERI_OID_PERI = psOidPeriodoCorpo
		 AND IND_ACTI =1;


        psMensajeResultado:=psOidPatron||','||lnOidMensaje;


      end if;  --fin del oidmensaje  is not null

  END IF;



EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ACTUA_MENSA: '||ls_sqlerrm);
END MSG_PR_ACTUA_MENSA;

/***************************************************************************
Descripcion : Replicar Patron
Fecha Creacion : 17/11/2011
Fecha Modificacion: 07/11/2013
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE MSG_PR_REPLI_PATRO
IS
 lnpais                     NUMBER;
 lnOidPatron                NUMBER;
 lnOidPatronSecci           NUMBER;
 lnOidMensaje               NUMBER;
 lsCodigoPatron    MSG_PATRO.COD_PATR%TYPE;
 lsAbrvSeccionDocu MSG_SECCI_DOCUM.ABR_SECC_DOCU%TYPE;
 lnCont                     NUMBER;

w_Filas           NUMBER := 1000;
ls_sqlerrm        VARCHAR2(150);
lnOidTipoMensaje  NUMBER;
lnIndicadorEditar NUMBER(1);

CURSOR c_replica IS
    select
        COD_PAIS,
        COD_NIVE,
        OID_REPL,
        CAM_ORIG,
        CAM_DEST,
        COD_USUA
    from MSG_TMP_REPLI_PATRO;


r_replica c_replica%ROWTYPE;
lnFila NUMBER;
lnOidPatronSecciOrigen NUMBER(12);
lsCampanhaActual  VARCHAR2(6);
 lsCampanaSig1               VARCHAR2(6);
 lsCampanaSig2               VARCHAR2(6);
 lsCampanaOrigen             VARCHAR2(6);
 lsCampanaDestino            VARCHAR2(6);
 lsCodigoPais                VARCHAR2(3);
 lnOidPeri NUMBER;
 ldFecha   DATE;
 lsFecha   VARCHAR2(10);
 lnOidDocumento NUMBER;

BEGIN

    /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');

    SELECT cod_peri, cod_pais
      INTO lsCampanhaActual, lsCodigoPais
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    SELECT OID_REPL INTO lnOidDocumento
    FROM MSG_TMP_REPLI_PATRO
    WHERE COD_NIVE ='0';

    lnFila:=0;
    OPEN c_replica;
    LOOP
    FETCH c_replica INTO r_replica;
    EXIT WHEN c_replica%NOTFOUND;
        BEGIN

          if(lnFila = 0) then
                   --VERIFICAMOS SI EXIETE PATRON EN EL PERIODO DESTINO
                SELECT COUNT(1) into lnCont
                    FROM MSG_PATRO_PERIO x,
                         MSG_PATRO y
                        where
                          x.ind_acti=1
                          and x.ind_acti = y.ind_acti
                          and x.peri_oid_peri = (SELECT A.OID_PERI
                                                 FROM SEG_PERIO_CORPO A
                                                 WHERE A.COD_PERI = r_replica.CAM_DEST)
                         and x.patr_oid_patr = y.oid_patr
                         AND y.MDOC_COD_MENS_DOCU = lnOidDocumento;

               if(lnCont > 0) then


                    --obtenemos el oid_patr
                    select y.oid_patr INTO lnOidPatron
                    FROM MSG_PATRO_PERIO x,
                         MSG_PATRO y
                        where
                          x.ind_acti=1
                          and x.ind_acti = y.ind_acti
                          and x.peri_oid_peri = (SELECT A.OID_PERI
                                                 FROM SEG_PERIO_CORPO A
                                                 WHERE A.COD_PERI = r_replica.CAM_DEST)
                         and x.patr_oid_patr = y.oid_patr
                         AND y.MDOC_COD_MENS_DOCU = lnOidDocumento;

                  --eliminamos consideraciones y restricciones



                      delete from msg_cores_mensa_detal
                      where meca_cam_proc = r_replica.CAM_DEST
                         and meca_cod_mens in(
                                select x.cod_mens
                                from msg_patro_mensa y,
                                     msg_mensa_campa x
                                where
                                   x.mens_oid_mens = y.mens_oid_mens
                                   and x.cam_proc = r_replica.CAM_DEST
                                   and y.pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  lnOidPatron
                                    )
                                );


                     delete from msg_cores_mensa_cabec
                      where meca_cam_proc = r_replica.CAM_DEST
                         and meca_cod_mens in(
                                select x.cod_mens
                                from msg_patro_mensa y,
                                     msg_mensa_campa x
                                where
                                   x.mens_oid_mens = y.mens_oid_mens
                                   and x.cam_proc = r_replica.CAM_DEST
                                   and y.pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  lnOidPatron)
                                );

                     --eliminamos mensaje_campa_image
                     delete from msg_mensa_campa_image x
                      where x.pais_cod_pais = lsCodigoPais
                        AND X.MECA_CAM_PROC =  r_replica.CAM_DEST;

                     --eliminamos mensaje_campa
                     delete from msg_mensa_campa
                      where cam_proc =  r_replica.CAM_DEST
                        and mens_oid_mens in(
                                select mens_oid_mens
                                from msg_patro_mensa
                                where pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  lnOidPatron
                                    )
                                );

                   --eliminamos mensajes
                   if(r_replica.CAM_DEST = lsCampanhaActual)then

                     update msg_mensa
                      set --ind_acti = 0,
                          est_regi = '9',
                          fec_modi= sysdate,
                          usu_modi =  r_replica.COD_USUA
                      where oid_mens in(
                                select mens_oid_mens
                                from msg_patro_mensa
                                where pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  lnOidPatron
                                    )
                                );
                     end if;


                    --eliminamos patron mensa
                     delete from msg_patro_mensa
                      where pase_oid_patr_secc in(
                                select OID_PATR_SECC
                                from msg_patro_secci
                                where patr_oid_patr= lnOidPatron);

                    --eliminamos ls secciones
                  delete from msg_patro_secci
                  where patr_oid_patr =  lnOidPatron;

                  --se procede a eliminar ltodo el patron de ese periodo Y DOCUMENTO
                  delete from msg_patro_perio
                  where
                    PATR_OID_PATR = lnOidPatron;

                  delete from msg_patro
                  where    oid_patr  = lnOidPatron;


                end if;

          end if;


          if(r_replica.COD_NIVE = '0') then

             select MSG_PATR_SEQ.NEXTVAL into lnOidPatron from dual;

              INSERT INTO MSG_PATRO (
                        OID_PATR,
                        COD_PATR,
                        IND_ACTI,
                        MEEP_OID_MEDI_ENVI_PAIS,
                        FORS_OID_FORM,
                        DES_PATR,
                        IND_PATR_PERI,
                        PAIS_OID_PAIS,
                        MDOC_COD_MENS_DOCU,
                        EST_REGI,
                        USU_MODI,
                        FEC_MODI)
            SELECT lnOidPatron,
                   (SELECT LOWER(SUBSTR(ABR_DOCU,1,4)) || substr(r_replica.CAM_DEST,5,2) || substr(r_replica.CAM_DEST,1,4)  || LOWER(SUBSTR(ABR_DOCU,5))  FROM MSG_DOCUM WHERE COD_MENS_DOCU= r_replica.OID_REPL),
                   1,
                  (NVL((SELECT MEEP_OID_MEDI_ENVI_PAIS FROM MSG_DOCUM WHERE COD_MENS_DOCU= r_replica.OID_REPL),'')),
                  (NVL((SELECT FORS_OID_FORM FROM MSG_DOCUM WHERE COD_MENS_DOCU= r_replica.OID_REPL),'')),
                  (select DES_MENS_DOCU ||' C'|| substr(r_replica.CAM_DEST,5,2) ||' ' || substr(r_replica.CAM_DEST,1,4)
                     from msg_docum where COD_MENS_DOCU = y.MDOC_COD_MENS_DOCU), --y.DES_PATR,
                  1,
                  PAIS_OID_PAIS,
                  r_replica.OID_REPL ,
                  '1',
                  r_replica.COD_USUA,
                  SYSDATE
            FROM MSG_PATRO_PERIO x,MSG_PATRO y
            where x.ind_Acti=1
             and x.ind_Acti= y.ind_acti
             and peri_oid_peri = (SELECT A.OID_PERI
                                     FROM SEG_PERIO_CORPO A
                                     WHERE A.COD_PERI = r_replica.CAM_ORIG)
             and patr_oid_patr = oid_patr
             and mdoc_cod_mens_docu = r_replica.OID_REPL;


              INSERT INTO MSG_PATRO_PERIO (
                       OID_PATR_PERI,
                       PATR_OID_PATR_ORIG,
                       PATR_OID_PATR,
                       PERI_OID_PERI,
                       IND_ACTI,
                       USU_MODI,
                       FEC_MODI,
                       EST_REGI)
            SELECT MSG_PAPE_SEQ.nextval,
                 (select y.oid_patr
                                FROM MSG_PATRO_PERIO x,
                                     MSG_PATRO y
                                    where x.ind_Acti=1
                                     and x.ind_Acti= y.ind_acti
                                     and peri_oid_peri = (SELECT A.OID_PERI
                                                             FROM SEG_PERIO_CORPO A
                                                             WHERE A.COD_PERI = r_replica.CAM_ORIG)
                                    and patr_oid_patr = oid_patr
                                    and mdoc_cod_mens_docu = r_replica.OID_REPL),
                   lnOidPatron,
                   (SELECT A.OID_PERI FROM SEG_PERIO_CORPO A WHERE A.COD_PERI = r_replica.CAM_DEST),
                  1,
                  r_replica.COD_USUA,
                  SYSDATE,
                  '1'
            FROM MSG_PATRO_PERIO x,MSG_PATRO y
            where x.ind_Acti=1
             and x.ind_Acti= y.ind_acti
             and peri_oid_peri = (SELECT A.OID_PERI
                                     FROM SEG_PERIO_CORPO A
                                     WHERE A.COD_PERI = r_replica.CAM_ORIG)
             and patr_oid_patr = oid_patr
             and mdoc_cod_mens_docu = r_replica.OID_REPL;


          end if;

          if(r_replica.COD_NIVE = '1') then
             lnOidPatronSecciOrigen:= r_replica.OID_REPL;
              select MSG_PASE_SEQ.NEXTVAL into lnOidPatronSecci from dual;


               SELECT lower(COD_PATR) INTO lsCodigoPatron
               FROM MSG_PATRO
               WHERE  OID_PATR = lnOidPatron;


               INSERT INTO MSG_PATRO_SECCI (
                OID_PATR_SECC,
                COD_SECC,
                PATR_OID_PATR,
                NUM_ORDE_SECC,
                METC_OID_META,
                MDOC_COD_MENS_DOCU,
                MSEC_COD_SECC_DOCU,
                IND_ACTI,
                USU_MODI,
                FEC_MODI,
                EST_REGI)
               SELECT  lnOidPatronSecci,
                    (lsCodigoPatron || ' ' ||
                        (SELECT lower(x.ABR_SECC_DOCU)
                          FROM MSG_SECCI_DOCUM x
                            WHERE x.MDOC_COD_MENS_DOCU = y.MDOC_COD_MENS_DOCU
                                AND x.COD_SECC_DOCU = y.MSEC_COD_SECC_DOCU)),
                    lnOidPatron,
                    NUM_ORDE_SECC,
                    METC_OID_META,
                    MDOC_COD_MENS_DOCU,
                    MSEC_COD_SECC_DOCU,
                    1,
                    r_replica.COD_USUA,
                    SYSDATE,
                    '1'
               FROM MSG_PATRO_SECCI y
               WHERE y.OID_PATR_SECC = lnOidPatronSecciOrigen;


          end if;

          if(r_replica.COD_NIVE = '2') then

            -- select MSG_MENS_SEQ.NEXTVAL into lnOidMensaje from DUAL;

            INSERT INTO MSG_PATRO_MENSA
            (OID_PATR_MENS,
             PASE_OID_PATR_SECC,
             MENS_OID_MENS,
             NUM_ORDE_IMPR,
             IND_ACTI,
             USU_MODI,
             FEC_MODI,
             EST_REGI
            )
            SELECT MSG_PAMS_SEQ.NEXTVAL,
                   lnOidPatronSecci,
                   r_replica.OID_REPL,
                   NUM_ORDE_IMPR,
                   1,
                    r_replica.COD_USUA,
                    SYSDATE,
                    '1'
            FROM MSG_PATRO_MENSA
            WHERE PASE_OID_PATR_SECC = lnOidPatronSecciOrigen
             AND MENS_OID_MENS = r_replica.OID_REPL;


            select TMEN_OID_TIPO_MENS
            INTO lnOidTipoMensaje
            from msg_mensa
            where oid_mens = r_replica.OID_REPL;

            lnIndicadorEditar := MSG_FN_DEVUE_INDIC_EDITA(
                    r_replica.COD_PAIS,
                    r_replica.CAM_ORIG,
                    r_replica.OID_REPL,
                    lnOidTipoMensaje);

            INSERT INTO MSG_MENSA_CAMPA (
                PAIS_COD_PAIS,
                CAM_PROC,
                MENS_OID_MENS,
                COD_MENS,
                DES_MENS,
                VAL_TEXT,
                MODU_OID_MODU,
                PERI_OID_PERI,
                TMEN_OID_TIPO_MENS,
                IND_EDIT,
                IND_ACTI,
                USU_CREA,
                FEC_CREA,
                EST_REGI)
             select r_replica.COD_PAIS,
                r_replica.CAM_DEST,
                OID_MENS,
                COD_MENS,
                DES_MENS,
                VAL_TEXT,
                MODU_OID_MODU,
                (SELECT A.OID_PERI FROM SEG_PERIO_CORPO A WHERE A.COD_PERI = r_replica.CAM_DEST),
                TMEN_OID_TIPO_MENS,
                lnIndicadorEditar,
                1,
                r_replica.COD_USUA,
                SYSDATE,
                '1'
            from msg_mensa
            where oid_mens = r_replica.OID_REPL;

            INSERT INTO MSG_MENSA_CAMPA_IMAGE (
                PAIS_COD_PAIS,
                MECA_CAM_PROC,
                MECA_COD_MENS,
                NOM_IMAG,
                VAL_IMAG,
                IND_ACTI,
                USU_CREA,
                FEC_CREA,
                USU_MODI,
                FEC_MODI)
            SELECT
                X.PAIS_COD_PAIS,
                r_replica.CAM_DEST,
                X.MECA_COD_MENS,
                X.NOM_IMAG,
                X.VAL_IMAG,
                X.IND_ACTI,
                X.USU_CREA,
                X.FEC_CREA,
                X.USU_MODI,
                X.FEC_MODI
            FROM MSG_MENSA_CAMPA_IMAGE X,
                msg_mensa Y
            WHERE Y.oid_mens = r_replica.OID_REPL
              AND Y.COD_MENS = X.MECA_COD_MENS
              AND X.PAIS_COD_PAIS = lsCodigoPais
              AND X.MECA_CAM_PROC =  r_replica.CAM_ORIG;


             -- replicamos consideracions y/o restricciones excepto lista de consultoras
             INSERT INTO MSG_CORES_MENSA_CABEC
             (PAIS_COD_PAIS,
              MECA_CAM_PROC,
              MECA_COD_MENS,
              CORE_COD_CONS_REST,
              IND_CONS_REST,
              IND_ACTI,
              USU_CREA,
              FEC_CREA,
              EST_REGI,
              NUM_REG
             )
             SELECT A.PAIS_COD_PAIS,
                    r_replica.CAM_DEST,
                    A.MECA_COD_MENS,
                    CORE_COD_CONS_REST,
                    IND_CONS_REST,
                    1,
                    r_replica.COD_USUA,
                    SYSDATE,
                    '1',
                     NUM_REG
             FROM MSG_CORES_MENSA_CABEC A,
                  MSG_MENSA_CAMPA B
             WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
               AND A.MECA_CAM_PROC = B.CAM_PROC
               AND A.MECA_COD_MENS = B.COD_MENS
               AND B.CAM_PROC = r_replica.CAM_ORIG
               AND B.mens_oid_mens = r_replica.OID_REPL;
               --and CORE_COD_CONS_REST not in(2010,2024); --distintos de lista de consultoras


            INSERT INTO MSG_CORES_MENSA_DETAL
             (PAIS_COD_PAIS,
              MECA_CAM_PROC,
              MECA_COD_MENS,
              CORE_COD_CONS_REST,
              COR_CONS_REST_DETA,
              IND_CONS_REST,
              VAL_CON1,
              VAL_CON2,
              VAL_CON3,
              VAL_CON4,
              IND_ACTI,
              USU_CREA,
              FEC_CREA,
              EST_REGI
             )
             SELECT A.PAIS_COD_PAIS,
                    r_replica.CAM_DEST,
                    A.MECA_COD_MENS,
                    CORE_COD_CONS_REST,
                    COR_CONS_REST_DETA,
                    IND_CONS_REST,
                    VAL_CON1,
                    VAL_CON2,
                    VAL_CON3,
                    VAL_CON4,
                    1,
                    r_replica.COD_USUA,
                    SYSDATE,
                    '1'
             FROM MSG_CORES_MENSA_DETAL A,
                  MSG_MENSA_CAMPA B
             WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
               AND A.MECA_CAM_PROC = B.CAM_PROC
               AND A.MECA_COD_MENS = B.COD_MENS
               AND B.CAM_PROC = r_replica.CAM_ORIG
               AND B.mens_oid_mens = r_replica.OID_REPL
               AND A.IND_ACTI= '1'
               AND A.EST_REGI <> '9';
               --and CORE_COD_CONS_REST not in(2010,2024); --distintos de lista de consultoras


          end if;

          lnFila:=lnFila +1;
        END;
    END LOOP;
    CLOSE c_replica;

    -- Actualizamos la campaña del mensaje FAN01 de Faltantes Anunciado automatico --
    SELECT DISTINCT cam_orig INTO lsCampanaOrigen FROM msg_tmp_repli_patro;
    SELECT DISTINCT cam_dest INTO lsCampanaDestino FROM msg_tmp_repli_patro;
    lsCampanaSig1 := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(lsCodigoPais,lsCampanaOrigen,1);
    lsCampanaSig2 := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(lsCodigoPais,lsCampanaOrigen,2);

    UPDATE msg_mensa_campa
       SET val_text = REPLACE(val_text,SUBSTR(val_text,INSTR(val_text,'C',1,2),3),'C'||SUBSTR(lsCampanaSig2,5,2) )
     WHERE cod_mens='FAN01'
       AND cam_proc = lsCampanaSig1;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_REPLI_PATRO: '||ls_sqlerrm);
END MSG_PR_REPLI_PATRO;


/***************************************************************************
Descripcion : Elimina el patron
Fecha Creacion : 03/10/2011
Autor : Sergio Buchelli
Parametros:
  psOidPatron             Oid Patron,

***************************************************************************/
PROCEDURE MSG_PR_ELIMI_PATRO
 (
  psOidPatron             VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  pslogin                 VARCHAR2
 )
IS
 lnpais number;
 lnOidPatron number;
 lnOidPatronSecci  number;
 lnOidMensaje      number;
 lsCodigoPatron    MSG_PATRO.COD_PATR%TYPE;
 lsAbrvSeccionDocu MSG_SECCI_DOCUM.ABR_SECC_DOCU%TYPE;
 lnCont            number;

w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);

lsCampanhaActual  VARCHAR2(6);
 lnOidPeri NUMBER;
 ldFecha   DATE;
 lsFecha   VARCHAR2(10);
 lnOidDocumento NUMBER;
BEGIN

    /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');

/*    SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;

    SELECT A.COD_PERI
      INTO lsCampanhaActual
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = lnOidPeri
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';*/

    select COD_PERI
    INTO lsCampanhaActual
    from bas_ctrl_fact
    where   STA_CAMP=0
     and  IND_CAMP_ACT =1;


                  --eliminamos consideraciones y restricciones

                      delete from msg_cores_mensa_detal
                      where MECA_cam_proc = psCampanhaProceso
                         and MECA_cod_mens in(
                                select x.cod_mens
                                from msg_patro_mensa y,
                                     msg_mensa_campa x
                                where
                                   x.mens_oid_mens = y.mens_oid_mens
                                   and x.cam_proc = psCampanhaProceso
                                   and y.pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  psOidPatron
                                    )
                                );


                     delete from msg_cores_mensa_cabec
                      where MECA_cam_proc = psCampanhaProceso
                         and MECA_cod_mens in(
                                select x.cod_mens
                                from msg_patro_mensa y,
                                     msg_mensa_campa x
                                where
                                   x.mens_oid_mens = y.mens_oid_mens
                                   and x.cam_proc = psCampanhaProceso
                                   and y.pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  psOidPatron)
                                );

                     --eliminamos mensaje_campa
                     delete from msg_mensa_campa
                      where cam_proc = psCampanhaProceso
                        and mens_oid_mens in(
                                select mens_oid_mens
                                from msg_patro_mensa
                                where pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  psOidPatron
                                    )
                                );

                   --eliminamos mensajes
                   if(psCampanhaProceso = lsCampanhaActual)THEN
                     update msg_mensa
                      set est_regi = '9',
                          fec_modi= sysdate,
                          usu_modi =  psLogin
                      where oid_mens in(
                                select mens_oid_mens
                                from msg_patro_mensa
                                where pase_oid_patr_secc in (
                                        select oid_patr_secc
                                        from msg_patro_secci
                                        where patr_oid_patr =  psOidPatron
                                    )
                                );

                     end if;


                    --eliminamos patron mensa
                     delete from msg_patro_mensa
                      where pase_oid_patr_secc in(
                                select OID_PATR_SECC
                                from msg_patro_secci
                                where patr_oid_patr= psOidPatron);

                    --eliminamos ls secciones
                  delete from msg_patro_secci
                  where patr_oid_patr =  psOidPatron;

                  --se procede a eliminar ltodo el patron de ese periodo Y DOCUMENTO
                  delete from msg_patro_perio
                  where
                    oid_patr_peri  = (select x.oid_patr_peri
                                      FROM MSG_PATRO_PERIO x
                                      where x.patr_oid_patr = psOidPatron);
                  delete from msg_patro
                  where    oid_patr  = psOidPatron;



EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ELIMI_PATRO: '||ls_sqlerrm);
END MSG_PR_ELIMI_PATRO;

/***************************************************************************
Descripcion : devulve el ahorro EN CATALOGOS Y LIQUIDACIONES en la campmanha
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CATAL_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidCliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;

 lnOidPeriodo NUMBER;
 lnGrupoProceso NUMBER;
BEGIN
  lnVenta:=0;


   lsPeriodo:= campanhaInicio;--periodo facturacion
   lsCodigoTipoSolicitud:=MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

    if(cantidadPeriodo < 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
        lnGrupoProceso:=5;
    else
        lnGrupoProceso:=4;
    end if;

   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);
         --obteniendo AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;

  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
 RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_CATAL_REAL;


/***************************************************************************
Descripcion : devulve el ahorro EN REVISTAS Y OFERTAS ESPECIALES en la campmanha
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_REVIS_REAL(psCodigoPlantilla  VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnOidCliente NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;

 lnOidPeriodo NUMBER;
 lnGrupoProceso NUMBER;
BEGIN
  lnVenta:=0;


   lsPeriodo:= campanhaInicio;--periodo facturacion
   lsCodigoTipoSolicitud:=MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);

    if(cantidadPeriodo < 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
        lnGrupoProceso:=5;
    else
        lnGrupoProceso:=4;
    end if;

   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);
         --obteniendo AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;

  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
 RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_REVIS_REAL;



/***************************************************************************
Descripcion : devulve descripcion en la campanha real o plaenada
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  campanhaInicio :campanha inicio
  psCodigoPeriodo codigo Periodo
  cantidadPeriodo: cant periodo

***************************************************************************/
FUNCTION MSG_FN_DEVUE_DESCR_CAMPA_ACTUA(campanhaInicio VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  cantidadPeriodo NUMBER,
                                  lnpais NUMBER,
                                  lnmarca NUMBER,
                                  lncanal NUMBER) RETURN VARCHAR2
IS
 ls_devuelve VARCHAR2(2000);

 lsPeriodo VARCHAR2(6);
BEGIN

 lsPeriodo:= campanhaInicio;

 if(cantidadPeriodo > 0) then
    lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
 end if;

 IF( lsPeriodo <= psCodigoPeriodo ) THEN
   ls_devuelve := 'REAL';
 ELSE
   ls_devuelve := 'PLAN';
 END IF;


 RETURN ls_devuelve;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_FN_DEVUE_DESCR_CAMPA_ACTUA: '||ls_sqlerrm);
END MSG_FN_DEVUE_DESCR_CAMPA_ACTUA;

/***************************************************************************
Descripcion : devulve el ahorro en la campmanha UTILIZADO POR LA VERSION B/NUEVAS CON METAS
 Y NUEVAS SIN META
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_TOTAL(psCodigoPlantilla VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio    VARCHAR2,
                                  cantidadPeriodo   NUMBER,
                                  lnOidCliente      NUMBER,
                                  lnpais            NUMBER,
                                  lnmarca           NUMBER,
                                  lncanal           NUMBER,
                                  psCodigoPeriodo   VARCHAR2,
                                  psMontoMeta       NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnOidGrupoProceso NUMBER;

 lnNumeroCampPlaneadas NUMBER;
 lnVentaPlaneada NUMBER;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;
BEGIN
  lnVenta:=0;

  lsCodigoTipoSolicitud:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);


   if(cantidadPeriodo=-1)then --SUMA TODODS LOS AHORROS DE LA CAMPANHA

       SELECT
		NVL(SUM(SC.VAL_GANA_TOTA_LOCA),0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        --AND SC. grpr_oid_grup_proc = 4
		--AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
		--AND mc.COD_CLIE = ncl.COD_CLIE
		--AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
        AND sc.CLIE_OID_CLIE = lnOidCliente;
         /*AND((SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
                AND
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
               )*/


       RETURN  lnVenta;

   end if;

   lsPeriodo:= campanhaInicio;


    if(cantidadPeriodo > 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
    end if;


   if(psCodigoPeriodo = lsPeriodo) then
    lnOidGrupoProceso:=4;
   else
    lnOidGrupoProceso:=5;
   end if;


   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);

      --

  if(lsPeriodo <= psCodigoPeriodo) then
     -- se trata de una campnaha real
         --obteniendo las ventas AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;
		--AND mc.COD_CLIE = ncl.COD_CLIE
		--AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR;

   else--CAMPNAS PLANEADAS
        --YA NO SE CALCULA
          lnVenta:=0;
   end if;


  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
  RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_TOTAL;

/***************************************************************************
Descripcion : devulve el ahorro en la campmanha UTILIZADO POR LA VERSION B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_CATAL(psCodigoPlantilla VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio    VARCHAR2,
                                  cantidadPeriodo   NUMBER,
                                  lnOidCliente      NUMBER,
                                  lnpais            NUMBER,
                                  lnmarca           NUMBER,
                                  lncanal           NUMBER,
                                  psCodigoPeriodo   VARCHAR2,
                                  psMontoMeta       NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnOidGrupoProceso NUMBER;

 lnNumeroCampPlaneadas NUMBER;
 lnVentaPlaneada NUMBER;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;
BEGIN
  lnVenta:=0;

  lsCodigoTipoSolicitud:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);


   if(cantidadPeriodo=-1)then --SUMA TODODS LOS AHORROS DE LA CAMPANHA

     --PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,3);

       SELECT
		NVL(SUM(SC.VAL_GANA_TOTA_LOCA),0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        --AND SC. grpr_oid_grup_proc = 4
		--AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
		--AND mc.COD_CLIE = ncl.COD_CLIE
		--AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
        AND sc.CLIE_OID_CLIE = lnOidCliente;

       RETURN  lnVenta;

   end if;

   lsPeriodo:= campanhaInicio;


    if(cantidadPeriodo > 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
    end if;


   if(psCodigoPeriodo = lsPeriodo) then
    lnOidGrupoProceso:=4;
   else
    lnOidGrupoProceso:=5;
   end if;


   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);

      --

  if(lsPeriodo <= psCodigoPeriodo) then
     -- se trata de una campnaha real
         --obteniendo las ventas AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;
		--AND mc.COD_CLIE = ncl.COD_CLIE
		--AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR;

   else--CAMPNAS PLANEADAS
        --YA NO SE CALCULA
          lnVenta:=0;
   end if;


  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
  RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_CATAL;

/***************************************************************************
Descripcion : devulve el ahorro en la campmanha UTILIZADO POR LA VERSION B
Fecha Creacion : 22/02/2012
Autor : Sergio Buchelli
Parametros:
  psCondigoPlantilla : Codigo de Plantilla
  psCondigoIdent : Codigo de Identificacion proceso
  lnOidPeriodo :oidPeriodo
  campanhaInicio :campanha inicio
  cantidadPeriodo: cant periodo
***************************************************************************/
FUNCTION MSG_FN_DEVUE_AHORR_REVIS(psCodigoPlantilla VARCHAR2,
                                  psCodigoIdenti    VARCHAR2,
                                  campanhaInicio    VARCHAR2,
                                  cantidadPeriodo   NUMBER,
                                  lnOidCliente      NUMBER,
                                  lnpais            NUMBER,
                                  lnmarca           NUMBER,
                                  lncanal           NUMBER,
                                  psCodigoPeriodo   VARCHAR2,
                                  psMontoMeta       NUMBER,
                                  psCodPeriodoFinMeta VARCHAR2) RETURN NUMBER
IS
 lnVenta NUMBER:=0;
 lsPeriodo VARCHAR2(6);

 lnOidPeriodo NUMBER;
 lnOidGrupoProceso NUMBER;

 lnNumeroCampPlaneadas NUMBER;
 lnVentaPlaneada NUMBER;
 lsCodigoTipoSolicitud PED_TIPO_SOLIC.COD_TIPO_SOLI%type;
BEGIN
  lnVenta:=0;

  lsCodigoTipoSolicitud:= MSG_FN_OBTEN_DATO(psCodigoPlantilla,psCodigoIdenti,TIPO_DATO_TIPO_SOLICITUD,TIPO_RETORNO_CODIGO);


   if(cantidadPeriodo=-1)then --SUMA TODODS LOS AHORROS DE LA CAMPANHA

       SELECT
		NVL(SUM(SC.VAL_GANA_TOTA_LOCA),0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC,
		NVS_CONSU_LOGRO NCL,
		NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        --AND SC. grpr_oid_grup_proc = 4
		--AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
		AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR
        AND sc.CLIE_OID_CLIE = lnOidCliente
         AND((SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') >= NCL.CMP_INIC
                AND
                  (SELECT A.COD_PERI
                   FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = SC.PERD_OID_PERI
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T') <= NCL.CMP_FINA
               )
   AND psCodigoPeriodo >= NCL.CMP_INIC
   AND psCodigoPeriodo <= NCL.CMP_FINA
   AND NCL.EST_REGI != '9'
   AND NCL.EST_LOGR = '1' -- Nuevo
   ;

       RETURN  lnVenta;

   end if;

   lsPeriodo:= campanhaInicio;


    if(cantidadPeriodo > 0) then
        lsPeriodo:= PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (campanhaInicio, lnpais,lnmarca,lncanal,cantidadPeriodo);
    end if;


   if(psCodigoPeriodo = lsPeriodo) then
    lnOidGrupoProceso:=4;
   else
    lnOidGrupoProceso:=5;
   end if;


   lnOidPeriodo:= Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo,lnmarca,lncanal);

      --

  if(lsPeriodo <= psCodigoPeriodo) then
     -- se trata de una campnaha real
         --obteniendo las ventas AHORRO
    SELECT
		NVL(SC.VAL_GANA_TOTA_LOCA,0)
      INTO lnVenta
    FROM
        PED_SOLIC_CABEC SC,
		PED_TIPO_SOLIC_PAIS TSP,
        PED_TIPO_SOLIC TS,
        MAE_CLIEN MC
		--NVS_CONSU_LOGRO NCL,
		--NVS_TIPO_LOGRO NTL
	WHERE
        SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
        AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
        AND TS.COD_TIPO_SOLI = lsCodigoTipoSolicitud
        --AND SC.FEC_FACT IS NULL
        AND SC. grpr_oid_grup_proc = lnOidGrupoProceso
		AND SC.PERD_OID_PERI = lnOidPeriodo
		AND sc.CLIE_OID_CLIE = mc.OID_CLIE
        AND sc.CLIE_OID_CLIE = lnOidCliente;
		/*AND mc.COD_CLIE = ncl.COD_CLIE
		AND ncl.COD_TIPO_LOGR =  ntl.COD_TIPO_LOGR;*/

   else--CAMPNAS PLANEADAS
        --YA NO SE CALCULA
          lnVenta:=0;
   end if;


  RETURN  lnVenta;
EXCEPTION
 WHEN OTHERS THEN
   lnVenta:=0;
  RETURN  lnVenta;
END MSG_FN_DEVUE_AHORR_REVIS;


/*****************************************************************************
  Descripcion : Proceso que genera informacion en el buzon de mensajes en base
                a la configuracion realizada en el nuevo modulo de mensajes
  Fecha Creacion : 06/12/2011
  Fecha Modificacion : 05/03/2014
  Autor : CSVD-FFVV
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psFechaFacturacion : Fecha Facturacion
   psTipoProceso : Tipo Proceso
   psCondigoIdent : Codigo de Identificacion Proceso
   psCondigoPlantilla : Codigo de Plantilla
   psCondigousuario : Codigo de Usuario
******************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONSI_RESTR
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psTipoProceso      VARCHAR2,
   psCondigoIdent     VARCHAR2,
   psCodigoPlantilla  VARCHAR2,
   psCodigoUsuario    VARCHAR2
 ) IS

    CURSOR cursorMSG IS
      SELECT crcab.meca_cam_proc, msg.oid_mens, msg.modu_oid_modu, msg.cod_mens
        FROM msg_mensa msg, msg_cores_mensa_cabec crcab, msg_consi_restr cr, msg_tipo_mensa mtm
       WHERE crcab.meca_cod_mens = msg.cod_mens
         AND crcab.meca_cam_proc = psCodigoPeriodo
         AND msg.est_regi = '1'
         AND crcab.est_regi = '1'
         AND crcab.ind_acti = '1'
         AND crcab.CORE_cod_cons_rest = cr.cod_cons_rest
         AND cr.abr_cons_rest <> TIPO_CONS_REST_LISTA_CONS
         AND msg.tmen_oid_tipo_mens = mtm.oid_tipo_mens
         AND mtm.cod_tipo_mens = TIPO_MENSAJE_VARIABLE;

    CURSOR cursorConsRest(vscamproc VARCHAR2, vscodmens VARCHAR2) IS
      SELECT cr.abr_cons_rest,
             cr.ind_cons_rest,
             cr.ind_tipo,
             crdet.val_con1,
             crdet.val_con2,
             crdet.val_con3,
             crdet.val_con4
        FROM msg_consi_restr cr, msg_cores_mensa_cabec crcab, msg_cores_mensa_detal crdet
       WHERE cr.cod_cons_rest = crcab.core_cod_cons_rest
         AND crcab.meca_cam_proc = vscamproc
         AND crcab.meca_cod_mens = vscodmens
         AND crdet.core_cod_cons_rest(+) = crcab.core_cod_cons_rest
         AND crdet.meca_cam_proc(+) = crcab.meca_cam_proc
         AND crdet.meca_cod_mens(+) = crcab.meca_cod_mens
         AND crdet.est_regi(+) = '1'
         AND crdet.ind_acti(+) = '1';

    lnOidPais             seg_pais.oid_pais%TYPE;
    lnOidMarca            seg_marca.oid_marc%TYPE;
    lnOidCanal            seg_canal.oid_cana%TYPE;
    lnOidPeriodo          NUMBER;
    lnOidPeriodoCorpo     NUMBER;
    lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
    lnCont                NUMBER;
    lbInicioRestric       BOOLEAN;

    lsFechaIni            VARCHAR2(8);
    lsFichaFinal          VARCHAR2(8);
    anho                  VARCHAR2(4);

    hayConsideracion      NUMBER;
    hayRestriccion        NUMBER;

    contRestriccion        NUMBER;

  BEGIN


    lnOidMarca := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
    lnOidCanal := MSG_PKG_PROCE_MENSA.MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
    lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud, tipo_retorno_codigo);

    lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);
    lnOidPeriodoCorpo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(psCodigoPeriodo);


    anho:= SUBSTR(psCodigoPeriodo, 1, 4);

    --obteniendo el rango d efechas del peridood
    SELECT TO_CHAR(x.fec_inic,'yyyyMMdd'),
           TO_CHAR(x.fec_fina,'yyyyMMdd')
      INTO lsFechaIni, lsFichaFinal
      FROM cra_perio x
     WHERE x.oid_peri = lnOidPeriodo;

    --  Recorriendo Cursor --
    FOR cMSG IN cursorMSG LOOP

      EXECUTE IMMEDIATE 'truncate table msg_tmp_consi';
      EXECUTE IMMEDIATE 'truncate table msg_tmp_restr';
      lbInicioRestric := TRUE;

      hayRestriccion:=0;
      hayConsideracion:=0;
      contRestriccion :=0;
      FOR cCORE IN cursorConsRest(cMSG.meca_cam_proc, cMSG.cod_mens) LOOP


        CASE cCORE.abr_cons_rest

          WHEN TIPO_CONS_REST_ANIVERSARIO THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                    mae_clien mc
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = mc.oid_clie
                  AND (
                     TO_NUMBER(anho ||  TO_CHAR(mc.fec_ingr,'MMdd')) >= TO_NUMBER(lsFechaIni)
                     AND TO_NUMBER(anho ||  TO_CHAR(mc.fec_ingr,'MMdd')) <= TO_NUMBER(lsFichaFinal)
                      )
                AND (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy')) - TO_NUMBER(TO_CHAR(mc.fec_ingr,'yyyy'))) > 0
                GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_CUMPLEANHOS THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                    mae_clien_datos_adici mcda
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = mcda.clie_oid_clie
                  AND (
                     TO_NUMBER(anho ||  TO_CHAR(mcda.fec_naci,'MMdd')) >= TO_NUMBER(lsFechaIni)
                     and TO_NUMBER(anho ||  TO_CHAR(mcda.fec_naci,'MMdd')) <= TO_NUMBER(lsFichaFinal)
                      )
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_GENERA_PEDIDO THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_CTD_CAMP_1_PED THEN -- No se esta usando en SSiCC, validar a que se refiere en FOX --

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
              (oid_clie)
              (SELECT sc.clie_oid_clie
                 FROM ped_solic_cabec sc, ped_tipo_solic_pais tsp, ped_tipo_solic ts
                WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND ts.cod_tipo_soli = lsCodTipoSoli
                  AND sc.fec_fact IS NULL
                  AND sc.grpr_oid_grup_proc = 4
                  AND sc.perd_oid_peri = lnoidperiodo
                  AND ven_pkg_repor.ven_fn_devue_nume_campa((SELECT a.cod_peri
                                                              FROM mae_clien_prime_conta x,
                                                                   seg_perio_corpo       a,
                                                                   cra_perio             b
                                                             WHERE x.perd_oid_peri = b.oid_peri
                                                               AND b.peri_oid_peri = a.oid_peri
                                                               AND x.clie_oid_clie = sc.clie_oid_clie),
                                                            pscodigoperiodo,
                                                            lnoidpais,
                                                            lnoidmarca,
                                                            lnoidcanal) = to_number(cCORE.val_con1)
                GROUP BY sc.clie_oid_clie);


          WHEN TIPO_CONS_REST_CTD_PED_NUEVA THEN -- No se esta usando en SSiCC, validar a que se refiere en FOX --

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
              (oid_clie)
              (SELECT sc.clie_oid_clie
                 FROM ped_solic_cabec sc, ped_tipo_solic_pais tsp, ped_tipo_solic ts, mae_clien_prime_conta mp
                WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND ts.cod_tipo_soli = lsCodTipoSoli
                  AND sc.fec_fact IS NULL
                  AND sc.grpr_oid_grup_proc = 4
                  AND sc.perd_oid_peri = lnoidperiodo
                  AND sc.clie_oid_clie = mp.clie_oid_clie
                  AND (SELECT COUNT(*)
                         FROM ped_solic_cabec x, ped_solic_cabec y, ped_tipo_solic_pais tspa, ped_tipo_solic tsol
                        WHERE x.clie_oid_clie = sc.clie_oid_clie
                          AND x.perd_oid_peri > mp.perd_oid_peri
                          AND x.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                          AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                          AND tsol.cod_tipo_soli = 'SOC'
                          AND x.grpr_oid_grup_proc = 5
                          AND x.fec_fact IS NOT NULL
                          AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                          AND y.esso_oid_esta_soli <> 4) = to_number(cCORE.val_con1)
                GROUP BY sc.clie_oid_clie);


          WHEN TIPO_CONS_REST_CODIGO_PREMIO THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.val_codi_vent = cCORE.val_con1
                AND pedc.num_unid_compr > 0
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_CODIGO_VENTA THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.val_codi_vent = cCORE.val_con1
                --AND pedc.num_unid_compr > 0
              GROUP BY pedc.clie_oid_clie
            );

          WHEN TIPO_CONS_REST_CUV_FALTANTE THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.val_codi_vent = cCORE.val_con1
                AND pedc.num_unid_dema_real - pedc.num_unid_compr > 0
                AND pedc.clie_oid_clie not in (SELECT oid_clie FROM msg_tmp_clien)
              GROUP BY pedc.clie_oid_clie
            );



          WHEN TIPO_CONS_REST_CUV_REEMPLAZO THEN --revisar la logica

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
              (oid_clie)
              (SELECT sc.clie_oid_clie
                 FROM ped_solic_cabec     sc,
                      ped_solic_posic     sd1,
                      ped_solic_posic     sd2,
                      ped_tipo_solic_pais tsp,
                      ped_tipo_solic      ts
                WHERE sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND ts.cod_tipo_soli = lsCodTipoSoli
                  AND sc.fec_fact IS NULL
                  AND sc.grpr_oid_grup_proc = 4
                  AND sc.perd_oid_peri = lnoidperiodo
                  AND sc.oid_soli_cabe = sd1.soca_oid_soli_cabe
                  AND sc.oid_soli_cabe = sd2.soca_oid_soli_cabe
                  AND sd1.val_codi_vent = cCORE.val_con1
                  AND sd2.val_codi_vent = cCORE.val_con2
                  AND sd1.num_unid_compr = 0
                  AND sd2.num_unid_compr > 0
                GROUP BY sc.clie_oid_clie);


          WHEN TIPO_CONS_REST_ESTATUS THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                    mae_clien_datos_adici mcda
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = mcda.clie_oid_clie
                AND mcda.esta_oid_esta_clie = TO_NUMBER(cCORE.val_con1)
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_MONTO_CATALOGO THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                    ped_solic_posic sd
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.oid_soli_posi = sd.oid_soli_posi
                  AND sd.espo_oid_esta_posi <> 2 HAVING
                    NVL(SUM(sd.num_unid_dema * sd.val_prec_cata_unit_loca), 0) BETWEEN TO_NUMBER(cCORE.val_con1) AND TO_NUMBER(cCORE.val_con2)
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_RANGOS_VENTA THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                    ped_solic_posic sd
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.oid_soli_posi = sd.oid_soli_posi
                  AND sd.espo_oid_esta_posi <> 2 HAVING
                    NVL(SUM(sd.num_unid_compr * sd.val_prec_cata_unit_loca), 0) BETWEEN TO_NUMBER(cCORE.val_con1) AND TO_NUMBER(cCORE.val_con2)
              GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_TIPO_CLASIF THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                      mae_clien_tipo_subti mcts,
                      mae_clien_clasi      mccl,
                      mae_tipo_clien       mt,
                      mae_subti_clien      mst,
                      mae_tipo_clasi_clien mtc,
                      mae_clasi            mc
                WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                  AND pedc.clie_oid_clie = mcts.clie_oid_clie
                  AND mcts.oid_clie_tipo_subt = mccl.ctsu_oid_clie_tipo_subt(+)
                  AND mcts.ticl_oid_tipo_clie = mt.oid_tipo_clie(+)
                  AND mcts.sbti_oid_subt_clie = mst.oid_subt_clie(+)
                  AND mccl.tccl_oid_tipo_clasi = mtc.oid_tipo_clas(+)
                  AND mccl.clas_oid_clas = mc.oid_clas(+)
                  AND (mt.cod_tipo_clie = cCORE.val_con1)
                  AND (cCORE.val_con2 IS NULL OR mst.cod_subt_clie = cCORE.val_con2)
                  AND (cCORE.val_con3 IS NULL OR mtc.cod_tipo_clas = cCORE.val_con3)
                  AND (cCORE.val_con4 IS NULL OR mc.cod_clas = cCORE.val_con4)
                GROUP BY pedc.clie_oid_clie
            );


          WHEN TIPO_CONS_REST_UNIDAD_ADMIN THEN

            EXECUTE IMMEDIATE 'truncate table msg_tmp_clien';

            INSERT INTO msg_tmp_clien
            (
             oid_clie
            )
            (
             SELECT pedc.clie_oid_clie
               FROM msg_tmp_pedid_clien pedc,
                      mae_clien_unida_admin uniadm,
                      zon_terri_admin       zta,
                      zon_terri             zt,
                      zon_secci             zsec,
                      zon_zona              zzon,
                      zon_regio             zreg
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = uniadm.clie_oid_clie
                  AND uniadm.ind_acti = 1
                  AND uniadm.ztad_oid_terr_admi = zta.oid_terr_admi
                  AND zta.terr_oid_terr = zt.oid_terr(+)
                  AND zta.zscc_oid_secc = zsec.oid_secc(+)
                  AND zsec.zzon_oid_zona = zzon.oid_zona(+)
                  AND zzon.zorg_oid_regi = zreg.oid_regi(+)
                  AND (cCORE.val_con4 IS NULL OR zt.cod_terr = cCORE.val_con4)
                  AND (cCORE.val_con3 IS NULL OR zsec.cod_secc = cCORE.val_con3)
                  AND (cCORE.val_con2 IS NULL OR zzon.cod_zona = cCORE.val_con2)
                  AND (cCORE.val_con1 IS NULL OR zreg.cod_regi = cCORE.val_con1)
              GROUP BY pedc.clie_oid_clie
            );

        ELSE

            CONTINUE;

        END CASE;

        -- SI ES CONSIDERACION, SE ACUMULAN TODAS LAS CONSIDERACIONES EN LA TABLA TEMPORAL MSG_TMP_CONSI
        IF cCORE.ind_cons_rest = 'C' THEN

          INSERT INTO msg_tmp_consi
          (
           SELECT cMSG.cod_mens,
                  cMSG.oid_mens,
                  base.oid_clie,
                  cMSG.modu_oid_modu
             FROM msg_tmp_clien base
          );
        END IF;

        IF cCORE.ind_cons_rest = 'R' THEN

            contRestriccion:=contRestriccion+1;

            INSERT INTO msg_tmp_restr
            (
             SELECT cMSG.cod_mens,
                    cMSG.oid_mens,
                    base.oid_clie,
                    cMSG.modu_oid_modu
               FROM msg_tmp_clien base
            );

        END IF;

      END LOOP;


       select count(1) into hayConsideracion from msg_tmp_consi;
       select count(1) into hayRestriccion from msg_tmp_restr;

      if(hayConsideracion >0 and hayRestriccion>0) then
        --se queda con aquellas que cumplan con el nuero de restrciiones

      INSERT INTO msg_buzon_mensa
      (
       oid_buzo_mens,
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
       ind_acti
      )
      (
       SELECT msg_bume_seq.nextval,
              msg_bum2_seq.nextval,
              'DV10',
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
              base.oid_clie,
              base.oid_mens,
              base.modu_oid_modu,
              base.nom1,
              base.nom2,
              base.ape1,
              base.ape2,
              base.apec,
              base.nombre,
              base.cod_clie,
              'DV03',
              'DV04',
              'DV05',
              'DV06',
              'DV07',
              'DV08',
              'DV09',
              NULL,
              SYSDATE,
              NULL,
              0,
              lnOidPeriodoCorpo,
              1
         FROM (
                       SELECT
                              mc.oid_clie,
                              mc.val_nom1 AS nom1,
                              mc.val_nom2 AS nom2,
                              mc.val_ape1 AS ape1,
                              mc.val_ape2 AS ape2,
                              mc.val_apel_casa AS apec,
                              mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                              mc.cod_clie,
                              cons.mens_oid_mens oid_mens,
                              cons.modu_oid_modu
                         FROM msg_tmp_consi cons,
                              mae_clien mc
                        WHERE cons.clie_oid_clie = mc.oid_clie
                              and cons.clie_oid_clie in (
                                    SELECT x.CLIE_OID_CLIE
                                    FROM msg_tmp_restr X
                                    where x.CLIE_OID_CLIE = cons.clie_oid_clie
                                    GROUP BY x.CLIE_OID_CLIE
                                    HAVING COUNT(x.CLIE_OID_CLIE) = contRestriccion
                                )
                        GROUP BY mc.oid_clie,
                                 mc.val_nom1,
                                 mc.val_nom2,
                                 mc.val_ape1,
                                 mc.val_ape2,
                                 mc.val_apel_casa,
                                 mc.cod_clie,
                                 cons.mens_oid_mens,
                                 cons.modu_oid_modu
                      ) base
              );

        end if;


     if(hayConsideracion >0 and hayRestriccion=0) then

      INSERT INTO msg_buzon_mensa
      (
       oid_buzo_mens,
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
       ind_acti
      )
      (
       SELECT msg_bume_seq.nextval,
                msg_bum2_seq.nextval,
                'DV10',
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
                base.oid_clie,
                base.oid_mens,
                base.modu_oid_modu,
                base.nom1,
                base.nom2,
                base.ape1,
                base.ape2,
                base.apec,
                base.nombre,
                base.cod_clie,
                'DV03',
                'DV04',
                'DV05',
                'DV06',
                'DV07',
                'DV08',
                'DV09',
                NULL,
                SYSDATE,
                NULL,
                0,
                lnOidPeriodoCorpo,
                1
         FROM (
                       SELECT
                              mc.oid_clie,
                        mc.val_nom1 AS nom1,
                        mc.val_nom2 AS nom2,
                        mc.val_ape1 AS ape1,
                        mc.val_ape2 AS ape2,
                        mc.val_apel_casa AS apec,
                        mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                        mc.cod_clie,
                        cons.mens_oid_mens oid_mens,
                        cons.modu_oid_modu
                         FROM msg_tmp_consi cons,
                              mae_clien mc
                  WHERE cons.clie_oid_clie = mc.oid_clie
                  GROUP BY mc.oid_clie,
                           mc.val_nom1,
                           mc.val_nom2,
                           mc.val_ape1,
                           mc.val_ape2,
                           mc.val_apel_casa,
                           mc.cod_clie,
                           cons.mens_oid_mens,
                         cons.modu_oid_modu
              ) base
      );

      end if;

     if(hayConsideracion =0 and hayRestriccion>0) then

              INSERT INTO msg_buzon_mensa
              (
               oid_buzo_mens,
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
               ind_acti
              )
              (
               SELECT msg_bume_seq.nextval,
                      msg_bum2_seq.nextval,
                      'DV10',
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
                      base.oid_clie,
                      base.oid_mens,
                      base.modu_oid_modu,
                      base.nom1,
                      base.nom2,
                      base.ape1,
                      base.ape2,
                      base.apec,
                      base.nombre,
                      base.cod_clie,
                      'DV03',
                      'DV04',
                      'DV05',
                      'DV06',
                      'DV07',
                      'DV08',
                      'DV09',
                      NULL,
                      SYSDATE,
                      NULL,
                      0,
                      lnOidPeriodoCorpo,
                      1
                 FROM (
                       SELECT
                              mc.oid_clie,
                              mc.val_nom1 AS nom1,
                              mc.val_nom2 AS nom2,
                              mc.val_ape1 AS ape1,
                              mc.val_ape2 AS ape2,
                              mc.val_apel_casa AS apec,
                              mc.val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 AS nombre,
                              mc.cod_clie,
                              rest.mens_oid_mens oid_mens,
                              rest.modu_oid_modu
                         FROM msg_tmp_restr rest,
                              mae_clien mc
                        WHERE rest.clie_oid_clie = mc.oid_clie
                        GROUP BY mc.oid_clie,
                                 mc.val_nom1,
                                 mc.val_nom2,
                                 mc.val_ape1,
                                 mc.val_ape2,
                                 mc.val_apel_casa,
                                 mc.cod_clie,
                                 rest.mens_oid_mens,
                                 rest.modu_oid_modu
                        HAVING COUNT(CLIE_OID_CLIE) = contRestriccion
                      ) base
              );

        end if;

    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONSI_RESTR: ' || ls_sqlerrm);
  END MSG_PR_GENER_MENSA_CONSI_RESTR;

/************************************************************************************
  Descripcion : Proceso que genera la actualizacion de mensajes al cierre de campanha
  Fecha Creacion : 16/05/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA_CAMPA
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoUsuario    VARCHAR2,
   psIndicadorCruce   VARCHAR2
 )
 AS

 CURSOR curINSMensa(lsPeriodoSgte VARCHAR2) IS
         SELECT
           COD_MENS,
           DES_MENS,
           VAL_TEXT,
           MENS_OID_MENS,
           MODU_OID_MODU,
           TMEN_OID_TIPO_MENS
         FROM MSG_MENSA_CAMPA
         WHERE  PAIS_COD_PAIS = psCodigoPais
          AND CAM_PROC = lsPeriodoSgte
          AND NOT EXISTS(
             SELECT OID_MENS
             FROM MSG_MENSA
             WHERE OID_MENS =MENS_OID_MENS);


 CURSOR curUPDMensa(lsPeriodoSgte VARCHAR2) IS
         SELECT
           COD_MENS,
           DES_MENS,
           VAL_TEXT,
           MENS_OID_MENS,
           MODU_OID_MODU,
           TMEN_OID_TIPO_MENS
         FROM MSG_MENSA_CAMPA
         WHERE  PAIS_COD_PAIS = psCodigoPais
          AND CAM_PROC = lsPeriodoSgte
          AND EXISTS(
             SELECT OID_MENS
             FROM MSG_MENSA
             WHERE OID_MENS =MENS_OID_MENS);


 CURSOR curConsiRestri(lsPeriodoSgte VARCHAR2) IS
         SELECT
            MECA_COD_MENS COD_MENS,
           (SELECT OID_MENS FROM MSG_MENSA WHERE COD_MENS = y.MECA_COD_MENS) OID_MENS,
           CORE_COD_CONS_REST,
           X.ABR_CONS_REST
         FROM MSG_CORES_MENSA_CABEC y,
              MSG_CONSI_RESTR x
         WHERE  Y.PAIS_COD_PAIS = psCodigoPais
          AND Y.MECA_CAM_PROC = lsPeriodoSgte
          AND Y.IND_ACTI = 1
          and X.COD_CONS_REST = Y.CORE_COD_CONS_REST
          AND CORE_COD_CONS_REST IN (2009,2010,2013,2014,2008,2005,
                                     2023,2024,2027,2028);--GENERACION PEDIDO, LISTA CONSULTORA ,UNIDAD Y TIPOLOGIAA



 TYPE t_cod_mens        IS TABLE OF MSG_MENSA_CAMPA.COD_MENS%TYPE;
 TYPE t_des_mens        IS TABLE OF MSG_MENSA_CAMPA.DES_MENS%TYPE;
 TYPE t_val_text        IS TABLE OF MSG_MENSA_CAMPA.VAL_TEXT%TYPE;
 TYPE t_oid_mens        IS TABLE OF MSG_MENSA_CAMPA.MENS_OID_MENS%TYPE;
 TYPE t_oid_modulo      IS TABLE OF MSG_MENSA_CAMPA.MODU_OID_MODU%TYPE;
 TYPE t_oid_tipo_mens   IS TABLE OF MSG_MENSA_CAMPA.TMEN_OID_TIPO_MENS%TYPE;

 v_cod_mens t_cod_mens ;
 v_des_mens t_des_mens ;
 v_val_text t_val_text ;
 v_oid_mens t_oid_mens ;
 v_oid_modulo t_oid_modulo;
 v_oid_tipo_mens t_oid_tipo_mens;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

 v_row_count_ins NUMBER := 0;

 lsCampanaSgte   MSG_MENSA_CAMPA.CAM_PROC%TYPE;
 lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
 cantidadPeriodo NUMBER;


 -- variables para la asigtnacion
-- TYPE t_oid_mens    IS TABLE OF MSG_MENSA.OID_MENS%TYPE;
 TYPE t_cod_consi   IS TABLE OF MSG_CORES_MENSA_CABEC.CORE_COD_CONS_REST%TYPE;
 TYPE t_abrev_consi IS TABLE OF MSG_CONSI_RESTR.ABR_CONS_REST%TYPE;

-- v_oid_mens t_oid_mens ;
 v_cod_consi t_cod_consi;
 v_abrev_consi t_abrev_consi;
 lnOidTipoAsign NUMBER;

begin
  cantidadPeriodo:=1;--SGTE PERIODO
  lnOidPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
  lnOidMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);
  lnOidCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);
  if(psIndicadorCruce = '0') then
  lsCampanaSgte  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo, lnOidPais,lnOidMarca,lnOidCanal,cantidadPeriodo);
    end if;
   if(psIndicadorCruce = '1') then
       lsCampanaSgte  := psCodigoPeriodo;
    end if;

         OPEN curUPDMensa(lsCampanaSgte);
         LOOP
         -- Bulk collect data into memory table - X rows at a time
                 FETCH curUPDMensa BULK COLLECT INTO
                                 v_cod_mens ,
                                 v_des_mens ,
                                 v_val_text,
                                 v_oid_mens,
                                 v_oid_modulo,
                                 v_oid_tipo_mens  LIMIT rows;

                 EXIT WHEN v_row_count = curUPDMensa%ROWCOUNT;
                 v_row_count := curUPDMensa%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                 FORALL i IN 1..v_cod_mens.count
                         update msg_mensa
                         SET
                            COD_MENS =  v_cod_mens(i),
                            DES_MENS =  v_des_mens(i),
                            VAL_TEXT =  v_val_text(i),
                            TMEN_OID_TIPO_MENS = v_oid_tipo_mens(i),
                            MODU_OID_MODU = v_oid_modulo(i),
                            usu_modi = psCodigoUsuario ,
                            fec_modi = sysdate
                         where pais_oid_pais = lnOidPais
                          and oid_mens = v_oid_mens(i);

         END LOOP;
         CLOSE curUPDMensa;


         --no deberia ocurrir cuado es un nuevo mensaje x nuevo modulo d emensaje se inserta en mensa_campa y msg_mensa a la vez
         OPEN curINSMensa(lsCampanaSgte);
         LOOP
         -- Bulk collect data into memory table - X rows at a time
          FETCH curINSMensa BULK COLLECT INTO v_cod_mens ,
                                              v_des_mens ,
                                              v_val_text,
                                              v_oid_mens,
                                              v_oid_modulo,
                                              v_oid_tipo_mens  LIMIT rows;

           IF v_cod_mens.COUNT > 0 THEN

             FOR j IN v_cod_mens.FIRST .. v_cod_mens.LAST LOOP
               BEGIN
                     INSERT INTO MSG_MENSA (
                                OID_MENS,
                                COD_MENS,
                                PAIS_OID_PAIS,
                                DES_MENS,
                                VAL_BLOQ_GRUP_PERM,
                                FEC_PERM_DESD,
                                FEC_PERM_HAST,
                                VAL_TEXT,
                                MODU_OID_MODU,
                                PERI_OID_PERI_DESD,
                                PERI_OID_PERI_HAST,
                                TPER_OID_TIPO_PERM,
                                TMEN_OID_TIPO_MENS,
                                IND_EXCL_TIPO,
                                USU_CREA,
                                FEC_CREA,
                                EST_REGI)
                            VALUES ( v_oid_mens(j),
                                 v_cod_mens(j),
                                 lnOidPais,
                                 v_des_mens(j),
                                 null,
                                 null,
                                 null,
                                 v_val_text(j),--Contenido de Mensaje transformado al lenguaje Xerox
                                 v_oid_modulo(j),
                                 null,
                                 null,
                                 2,
                                 v_oid_tipo_mens(j),
                                 null,
                                 psCodigoUsuario,
                                 sysdate,
                                 1 );
                 EXCEPTION
                 WHEN OTHERS THEN
                   NULL;
                 END;
             END LOOP;
           END IF;
          EXIT WHEN curINSMensa%NOTFOUND;
          END LOOP;
         CLOSE curINSMensa;


        --LIMPIAMOS TABLAS DE ASIGNACION DE SICC
         DELETE FROM MSG_MENSA_UNIDA_ADMIN;
         DELETE FROM MSG_MENSA_TIPO_CLIEN;
         DELETE FROM MSG_MENSA_TIPO_ASIGN;

        OPEN curConsiRestri(lsCampanaSgte);
         LOOP
         -- Bulk collect data into memory table - X rows at a time

                 FETCH curConsiRestri BULK COLLECT INTO
                                 v_cod_mens ,
                                 v_oid_mens ,
                                 v_cod_consi,
                                 v_abrev_consi  LIMIT rows;

                 IF v_oid_mens.COUNT > 0 THEN

                    FOR x IN v_oid_mens.FIRST .. v_oid_mens.LAST LOOP

                           --validamos si existe en tipo_asig de sicc
                          lnOidTipoAsign:=-1;
                          case  v_abrev_consi(x)
                            when TIPO_CONS_REST_TIPO_CLASIF then
                                lnOidTipoAsign:= 2;

                            when TIPO_CONS_REST_UNIDAD_ADMIN  then
                                lnOidTipoAsign:= 1;

                            when TIPO_CONS_REST_GENERA_PEDIDO  then
                                 lnOidTipoAsign:= 5;

                            when TIPO_CONS_REST_LISTA_CONS  then
                                 lnOidTipoAsign:= 6;

                            when TIPO_CONS_REST_CODIGO_VENTA  then
                                 lnOidTipoAsign:= 6;

                            when TIPO_CONS_REST_ESTATUS  then
                                 lnOidTipoAsign:= 6;

                            else
                                  lnOidTipoAsign:= -1;
                          end case;

                       IF(lnOidTipoAsign != -1) THEN


                              INSERT INTO MSG_MENSA_TIPO_ASIGN (
                                   OID_MENS_TIPO_DEST,
                                   MENS_OID_MENS,
                                   TIDE_OID_TIPO_DEST,
                                   IND_ACTI,
                                   USU_MODI,
                                   FEC_MODI,
                                   EST_REGI)
                               VALUES ( MSG_MSTD_SEQ.NEXTVAL,
                                        v_oid_mens(x),
                                        lnOidTipoAsign,
                                        1,
                                        psCodigoUsuario,
                                        SYSDATE,
                                        1);


                         --los detalles solo cuando es unidad administrativa y topologia
                          if(lnOidTipoAsign = 1) then
                           -- delete from MSG_MENSA_UNIDA_ADMIN where MENS_OID_MENS = v_oid_mens(x);

                            INSERT INTO MSG_MENSA_UNIDA_ADMIN (
                               OID_MENS_UNID_ADMI,
                               MENS_OID_MENS,
                               ZORG_OID_REGI,
                               ZZON_OID_ZONA,
                               ZSCC_OID_SECC,
                               TERR_OID_TERR)
                            SELECT
                                MSG_MSUA_SEQ.NEXTVAL,
                                v_oid_mens(x),
                                (SELECT OID_REGI FROM ZON_REGIO WHERE COD_REGI = VAL_CON1),
                                (SELECT OID_ZONA
                                    FROM ZON_ZONA,ZON_REGIO
                                    WHERE COD_ZONA = VAL_CON2 AND ZORG_OID_REGI = OID_REGI AND COD_REGI = VAL_CON1),
                                (SELECT OID_SECC
                                    FROM ZON_SECCI,ZON_ZONA,ZON_REGIO
                                    WHERE COD_SECC = VAL_CON3 AND
                                          ZZON_OID_ZONA = OID_ZONA AND
                                          COD_ZONA = VAL_CON2 AND
                                          ZORG_OID_REGI = OID_REGI AND
                                          COD_REGI = VAL_CON1),
                                (SELECT  zon_terri.oid_terr
                                    FROM zon_zona,
                                         zon_regio,
                                         zon_secci,
                                         zon_terri,
                                         zon_terri_admin
                                   WHERE zon_secci.ind_acti = 1
                                     and zon_regio.ind_acti = zon_secci.ind_acti
                                     and zon_zona.ind_acti = zon_secci.ind_acti
                                     AND zon_regio.cod_regi = VAL_CON1
                                     AND zon_zona.cod_zona = VAL_CON2
                                     AND zon_secci.cod_secc = VAL_CON3
                                     AND zon_terri.COD_TERR =VAL_CON4
                                     AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
                                     AND zon_terri.oid_terr = zon_terri_admin.terr_oid_terr
                                     AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
                                     AND zon_zona.oid_zona = zon_secci.zzon_oid_zona)
                            FROM MSG_CORES_MENSA_DETAL
                            WHERE PAIS_COD_PAIS = psCodigoPais
                             AND MECA_CAM_PROC = lsCampanaSgte
                             AND MECA_COD_MENS = v_cod_mens(x)
                              AND IND_ACTI=1
                              AND EST_REGI='1'
                             AND CORE_COD_CONS_REST = v_cod_consi(x);

                          end if;
                          --topologia
                          if(lnOidTipoAsign = 2) then
                            --delete from MSG_MENSA_TIPO_CLIEN where MENS_OID_MENS = v_oid_mens(x);

                            INSERT INTO MSG_MENSA_TIPO_CLIEN (
                                   OID_MENS_TIPO_CLIE,
                                   MENS_OID_MENS,
                                   TICL_OID_TIPO_CLIE,
                                   SBTI_OID_SUBT_CLIE,
                                   TCCL_OID_TIPO_CLAS,
                                   CLAS_OID_CLAS)
                            SELECT
                                MSG_MSTC_SEQ.NEXTVAL,
                                v_oid_mens(x),
                                (select OID_TIPO_CLIE FROM MAE_TIPO_CLIEN WHERE COD_TIPO_CLIE = VAL_CON1),
                                (SELECT OID_SUBT_CLIE
                                    FROM MAE_SUBTI_CLIEN,
                                         MAE_TIPO_CLIEN
                                    WHERE COD_SUBT_CLIE = VAL_CON2
                                        AND TICL_OID_TIPO_CLIE = OID_TIPO_CLIE
                                        AND COD_TIPO_CLIE = VAL_CON1),
                                 (SELECT   oid_tipo_clas
                                    FROM mae_tipo_clasi_clien,
                                         mae_tipo_clien,
                                         mae_subti_clien
                                   WHERE mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie
                                     AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
                                     AND cod_tipo_clie = VAL_CON1
                                     AND cod_subt_clie =VAL_CON2
                                     and  COD_TIPO_CLAS = VAL_CON3),
                                 (SELECT   oid_clas
                                        FROM mae_clasi,
                                             mae_tipo_clasi_clien,
                                             mae_subti_clien,
                                             mae_tipo_clien
                                       WHERE mae_clasi.tccl_oid_tipo_clas = mae_tipo_clasi_clien.oid_tipo_clas
                                         AND mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie
                                         AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
                                         AND cod_tipo_clie = VAL_CON1
                                         AND cod_subt_clie = VAL_CON2
                                         AND cod_tipo_clas = VAL_CON3
                                         and COD_CLAS = VAL_CON4)
                            FROM MSG_CORES_MENSA_DETAL
                            WHERE PAIS_COD_PAIS = psCodigoPais
                             AND MECA_CAM_PROC = lsCampanaSgte
                             AND MECA_COD_MENS = v_cod_mens(x)
                             AND CORE_COD_CONS_REST = v_cod_consi(x)
                              AND IND_ACTI=1
                              AND EST_REGI='1';

                          end if;


                      END IF;

                    END LOOP;

                 END IF;
                EXIT WHEN curConsiRestri%NOTFOUND;

         END LOOP;
         CLOSE curConsiRestri;



 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ACTUA_MENSA_CAMPA: '||ls_sqlerrm);
 END MSG_PR_ACTUA_MENSA_CAMPA;


/************************************************************************************
  Descripcion : Proceso que genera la actualizacion TIPO DE ASIGNACION EN SICC
    para mensajes fijos
  Fecha Creacion : 31/07/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psCodigoPeriodo : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_ACTUA_MENSA_ASIGN
 (
   psCodigoPais       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psCodigoMensaje    VARCHAR2,
   psCodigoUsuario    VARCHAR2
 )
 AS

 CURSOR curConsiRestri IS
         SELECT
           (SELECT OID_MENS FROM MSG_MENSA WHERE COD_MENS = psCodigoMensaje) OID_MENS,
           CORE_COD_CONS_REST,
           X.ABR_CONS_REST
         FROM MSG_CORES_MENSA_CABEC y,
              MSG_CONSI_RESTR x
         WHERE  Y.PAIS_COD_PAIS = psCodigoPais
          AND Y.MECA_CAM_PROC = psCodigoPeriodo
          AND Y.MECA_COD_MENS = psCodigoMensaje
          AND Y.IND_ACTI = 1
          and X.COD_CONS_REST = Y.CORE_COD_CONS_REST;
          --AND CORE_COD_CONS_REST IN (2013,2014);--UNIDAD Y TIPOLOGIA



 TYPE t_oid_mens    IS TABLE OF MSG_MENSA.OID_MENS%TYPE;
 TYPE t_cod_consi   IS TABLE OF MSG_CORES_MENSA_CABEC.CORE_COD_CONS_REST%TYPE;
 TYPE t_abrev_consi IS TABLE OF MSG_CONSI_RESTR.ABR_CONS_REST%TYPE;

 v_oid_mens t_oid_mens ;
 v_cod_consi t_cod_consi;
 v_abrev_consi t_abrev_consi;

 rows NATURAL := 1000; -- Number of rows to process at a time
 v_row_count NUMBER := 0;
 lsCampanhaActual   MSG_MENSA_CAMPA.CAM_PROC%TYPE;
 lnOidPeri NUMBER;
 ldFecha   DATE;
 lsFecha   VARCHAR2(10);


 lnOidTipoAsign NUMBER;
 lnCount NUMBER;
 lnOidMensaje   NUMBER;
begin

    /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');

  /*  SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;

    SELECT A.COD_PERI
      INTO lsCampanhaActual
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = lnOidPeri
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';*/

    select COD_PERI
        INTO lsCampanhaActual
    from bas_ctrl_fact
    where   STA_CAMP=0
     and  IND_CAMP_ACT =1;

    select oid_mens into lnOidMensaje
    from msg_mensa
    where cod_mens = psCodigoMensaje;

   --solo si es el mismo periodo
   --lsCampanhaActual:='201111' ;--PARA EFECTOS DE PRUEBA
   IF(psCodigoPeriodo = lsCampanhaActual)    THEN

         --si no existe u.a en consi y rest se elmina si hubiera en sicc las unidades administrativas
         select count(1) into lnCount
          FROM MSG_CORES_MENSA_DETAL
            WHERE PAIS_COD_PAIS = psCodigoPais
             AND MECA_CAM_PROC = psCodigoPeriodo
             AND MECA_COD_MENS = psCodigoMensaje
             AND IND_ACTI=1
             AND EST_REGI='1'
             AND CORE_COD_CONS_REST in (2028,2014);

          if(lnCount = 0) then
              delete from MSG_MENSA_UNIDA_ADMIN where MENS_OID_MENS = lnOidMensaje;
          end if;


           --si no existe u.a en consi y rest se elmina si hubiera en sicc las topologia cliente
         select count(1) into lnCount
          FROM MSG_CORES_MENSA_DETAL
            WHERE PAIS_COD_PAIS = psCodigoPais
             AND MECA_CAM_PROC = psCodigoPeriodo
             AND MECA_COD_MENS = psCodigoMensaje
             AND IND_ACTI=1
             AND EST_REGI='1'
             AND CORE_COD_CONS_REST in (2027,2013);

          if(lnCount = 0) then
              delete from MSG_MENSA_TIPO_CLIEN where MENS_OID_MENS = lnOidMensaje;
          end if;

        --BORRAMOS DE LA TABLA ASIGNACION , aquellas q uya no se encuntren en con y restr,
        --ES DECIR YA FUERON ELIMINADAS EN SSICC
        delete MSG_MENSA_TIPO_ASIGN
         where MENS_OID_MENS = lnOidMensaje
          AND TIDE_OID_TIPO_DEST NOT IN(
                SELECT
                    case  X.ABR_CONS_REST
                            when TIPO_CONS_REST_TIPO_CLASIF then  2
                            when TIPO_CONS_REST_UNIDAD_ADMIN  then 1
                            when TIPO_CONS_REST_GENERA_PEDIDO  then 5
                            when TIPO_CONS_REST_LISTA_CONS  then 6
                            when TIPO_CONS_REST_CODIGO_VENTA  then 6
                            when TIPO_CONS_REST_ESTATUS  then 6
                            else -1
                     end
                FROM MSG_CORES_MENSA_CABEC y,
                     MSG_CONSI_RESTR x
                WHERE y.PAIS_COD_PAIS = psCodigoPais
                       AND y.MECA_CAM_PROC = psCodigoPeriodo
                      AND y.MECA_COD_MENS = psCodigoMensaje
                      AND y.IND_ACTI=1
                      AND y.EST_REGI='1'
                      and Y.CORE_COD_CONS_REST = X.COD_CONS_REST);

         OPEN curConsiRestri;
         LOOP
         -- Bulk collect data into memory table - X rows at a time
                 FETCH curConsiRestri BULK COLLECT INTO
                                 v_oid_mens ,
                                 v_cod_consi,
                                 v_abrev_consi  LIMIT rows;

                 IF v_oid_mens.COUNT > 0 THEN
                    FOR x IN v_oid_mens.FIRST .. v_oid_mens.LAST LOOP

                           --validamos si existe en tipo_asig de sicc
                          lnOidTipoAsign:=-1;
                          case  v_abrev_consi(x)
                            when TIPO_CONS_REST_TIPO_CLASIF then
                                lnOidTipoAsign:= 2;

                            when TIPO_CONS_REST_UNIDAD_ADMIN  then
                                lnOidTipoAsign:= 1;

                            when TIPO_CONS_REST_GENERA_PEDIDO  then
                                 lnOidTipoAsign:= 5;

                            when TIPO_CONS_REST_LISTA_CONS  then
                                 lnOidTipoAsign:= 6;

                            when TIPO_CONS_REST_CODIGO_VENTA  then
                                 lnOidTipoAsign:= 6;

                            when TIPO_CONS_REST_ESTATUS  then
                                 lnOidTipoAsign:= 6;

                             else
                                lnOidTipoAsign:= -1;
                          end case;

                       IF(lnOidTipoAsign != -1) THEN
                          --validamos q si existe ya nose crea
                          select count(1) into lncount
                          from MSG_MENSA_TIPO_ASIGN
                          where   TIDE_OID_TIPO_DEST = lnOidTipoAsign
                            and MENS_OID_MENS = v_oid_mens(x);

                          if(lncount = 0) then
                              INSERT INTO MSG_MENSA_TIPO_ASIGN (
                                   OID_MENS_TIPO_DEST,
                                   MENS_OID_MENS,
                                   TIDE_OID_TIPO_DEST,
                                   IND_ACTI,
                                   USU_MODI,
                                   FEC_MODI,
                                   EST_REGI)
                               VALUES ( MSG_MSTD_SEQ.NEXTVAL,
                                        v_oid_mens(x),
                                        lnOidTipoAsign,
                                        1,
                                        psCodigoUsuario,
                                        SYSDATE,
                                        1);
                         else--ctualizamos
                            update MSG_MENSA_TIPO_ASIGN
                            set USU_MODI = psCodigoUsuario,
                                FEC_MODI = sysdate
                            where MENS_OID_MENS = v_oid_mens(x)
                             and TIDE_OID_TIPO_DEST = lnOidTipoAsign;

                         end if;

                         --los detalles solo cuando es unidad administrativa y topologia
                          if(lnOidTipoAsign = 1) then
                            DELETE FROM MSG_MENSA_UNIDA_ADMIN WHERE MENS_OID_MENS = v_oid_mens(x);

                            INSERT INTO MSG_MENSA_UNIDA_ADMIN (
                               OID_MENS_UNID_ADMI,
                               MENS_OID_MENS,
                               ZORG_OID_REGI,
                               ZZON_OID_ZONA,
                               ZSCC_OID_SECC,
                               TERR_OID_TERR)
                            SELECT
                                MSG_MSUA_SEQ.NEXTVAL,
                                v_oid_mens(x),
                                (SELECT OID_REGI FROM ZON_REGIO WHERE COD_REGI = VAL_CON1),
                                (SELECT OID_ZONA
                                    FROM ZON_ZONA,ZON_REGIO
                                    WHERE COD_ZONA = VAL_CON2 AND ZORG_OID_REGI = OID_REGI AND COD_REGI = VAL_CON1),
                                (SELECT OID_SECC
                                    FROM ZON_SECCI,ZON_ZONA,ZON_REGIO
                                    WHERE COD_SECC = VAL_CON3 AND
                                          ZZON_OID_ZONA = OID_ZONA AND
                                          COD_ZONA = VAL_CON2 AND
                                          ZORG_OID_REGI = OID_REGI AND
                                          COD_REGI = VAL_CON1),
                                (SELECT  zon_terri.oid_terr
                                    FROM zon_zona,
                                         zon_regio,
                                         zon_secci,
                                         zon_terri,
                                         zon_terri_admin
                                   WHERE zon_secci.ind_acti = 1
                                     and zon_regio.ind_acti = zon_secci.ind_acti
                                     and zon_zona.ind_acti = zon_secci.ind_acti
                                     AND zon_regio.cod_regi = VAL_CON1
                                     AND zon_zona.cod_zona = VAL_CON2
                                     AND zon_secci.cod_secc = VAL_CON3
                                     AND zon_terri.COD_TERR =VAL_CON4
                                     AND zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc
                                     AND zon_terri.oid_terr = zon_terri_admin.terr_oid_terr
                                     AND zon_regio.oid_regi = zon_zona.zorg_oid_regi
                                     AND zon_zona.oid_zona = zon_secci.zzon_oid_zona)
                            FROM MSG_CORES_MENSA_DETAL
                            WHERE PAIS_COD_PAIS = psCodigoPais
                             AND MECA_CAM_PROC = psCodigoPeriodo
                             AND MECA_COD_MENS = psCodigoMensaje
                              AND IND_ACTI=1
                              AND EST_REGI='1'
                             AND CORE_COD_CONS_REST = v_cod_consi(x);

                          end if;
                          --topologia
                          if(lnOidTipoAsign = 2) then
                            delete from MSG_MENSA_TIPO_CLIEN where MENS_OID_MENS = v_oid_mens(x);

                            INSERT INTO MSG_MENSA_TIPO_CLIEN (
                                   OID_MENS_TIPO_CLIE,
                                   MENS_OID_MENS,
                                   TICL_OID_TIPO_CLIE,
                                   SBTI_OID_SUBT_CLIE,
                                   TCCL_OID_TIPO_CLAS,
                                   CLAS_OID_CLAS)
                            SELECT
                                MSG_MSTC_SEQ.NEXTVAL,
                                v_oid_mens(x),
                                (select OID_TIPO_CLIE FROM MAE_TIPO_CLIEN WHERE COD_TIPO_CLIE = VAL_CON1),
                                (SELECT OID_SUBT_CLIE
                                    FROM MAE_SUBTI_CLIEN,
                                         MAE_TIPO_CLIEN
                                    WHERE COD_SUBT_CLIE = VAL_CON2
                                        AND TICL_OID_TIPO_CLIE = OID_TIPO_CLIE
                                        AND COD_TIPO_CLIE = VAL_CON1),
                                 (SELECT   oid_tipo_clas
                                    FROM mae_tipo_clasi_clien,
                                         mae_tipo_clien,
                                         mae_subti_clien
                                   WHERE mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie
                                     AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
                                     AND cod_tipo_clie = VAL_CON1
                                     AND cod_subt_clie =VAL_CON2
                                     and  COD_TIPO_CLAS = VAL_CON3),
                                 (SELECT   oid_clas
                                        FROM mae_clasi,
                                             mae_tipo_clasi_clien,
                                             mae_subti_clien,
                                             mae_tipo_clien
                                       WHERE mae_clasi.tccl_oid_tipo_clas = mae_tipo_clasi_clien.oid_tipo_clas
                                         AND mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie
                                         AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
                                         AND cod_tipo_clie = VAL_CON1
                                         AND cod_subt_clie = VAL_CON2
                                         AND cod_tipo_clas = VAL_CON3
                                         and COD_CLAS = VAL_CON4)
                            FROM MSG_CORES_MENSA_DETAL
                            WHERE PAIS_COD_PAIS = psCodigoPais
                             AND MECA_CAM_PROC = psCodigoPeriodo
                             AND MECA_COD_MENS = psCodigoMensaje
                             AND CORE_COD_CONS_REST = v_cod_consi(x)
                              AND IND_ACTI=1
                              AND EST_REGI='1';

                          end if;


                      END IF;

                    END LOOP;

                 END IF;
                EXIT WHEN curConsiRestri%NOTFOUND;

         END LOOP;
         CLOSE curConsiRestri;

     END IF;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ACTUA_MENSA_ASIGN: '||ls_sqlerrm);
 END MSG_PR_ACTUA_MENSA_ASIGN;

/************************************************************************************
  Descripcion : Proceso que genera excluir un mensaje desde el ordenamiento
   sigue la misma logica de eliminacion de mensajes
  Fecha Creacion : 24/09/2012
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psOidDocumento : oid o codigo de mensaje
   psOidSeccion : oid seccion
   psOidMensaje : oid mensaje
   psCampanhaProceso : Cdigo de Periodo
   psCondigousuario : Codigo de Usuario
***********************************************************************************/
PROCEDURE MSG_PR_EXCLU_MENSA
 (
   psCodigoPais       VARCHAR2,
   psOidDocumento     VARCHAR2,
   psOidSeccion       VARCHAR2,
   psOidMensaje       VARCHAR2,
   psCampanhaProceso  VARCHAR2,
   psCodigoUsuario    VARCHAR2
 )IS
 lnpais number;
 lnCont number;

 lsCodMensaje        msg_mensa.cod_mens%type;
 lsCampanhaActual    bas_ctrl_fact.COD_PERI%type;
 lnOidPeriodoCorpo   SEG_PERIO_CORPO.OID_PERI%type;
 lnOidModulo         MSG_MENSA_CAMPA.MODU_OID_MODU%type;
 lnOidPatron         msg_patro_secci.PATR_OID_PATR%type;

BEGIN
  lnpais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  SELECT A.OID_PERI into lnOidPeriodoCorpo FROM SEG_PERIO_CORPO A  WHERE A.COD_PERI = psCampanhaProceso;

  select COD_PERI  INTO lsCampanhaActual
  from bas_ctrl_fact
  where   STA_CAMP=0
    and  IND_CAMP_ACT =1;

   begin
      SELECT COD_MENS , MODU_OID_MODU
       INTO lsCodMensaje, lnOidModulo
        FROM MSG_MENSA_CAMPA
        WHERE MENS_OID_MENS = psOidMensaje
        	AND CAM_PROC= psCampanhaProceso
            AND EST_REGI = '1';

     exception
       WHEN OTHERS THEN
       lsCodMensaje:=null;
       lnOidModulo:=null;
     end;

      if(lsCodMensaje is not null and length(lsCodMensaje)>0)then

        --obtenemos patron
         select PATR_OID_PATR into lnOidPatron
         from msg_patro_secci
         where OID_PATR_SECC = psOidSeccion;

        --verica mensajes en la seccion
          select count(1) into lnCont
          from msg_patro_mensa
          where PASE_OID_PATR_SECC = psOidSeccion
           and  MENS_OID_MENS <> psOidMensaje
           and IND_ACTI =  1  ;

        --eliminamos mensajes cabeceras y detalles
        --eliminamos consideraciones y restricciones
           delete from msg_cores_mensa_detal
              where meca_cam_proc = psCampanhaProceso
                 and meca_cod_mens = lsCodMensaje;


           delete from msg_cores_mensa_cabec
             where meca_cam_proc = psCampanhaProceso
                 and meca_cod_mens = lsCodMensaje;


          if(lnCont= 0) then
            /*

            El sistema verifica que exista otro registro en memoria de la entidad Sección Patrón
            con los datos de Documento y campaña de la sección eliminada.
               UPDATE MSG_PATRO CON OID_:PATRO A ELIMINADO
            */
                  --eliminamos mensaje_campa
               delete from msg_mensa_campa
                  where cam_proc =  psCampanhaProceso
                    and COD_MENS = lsCodMensaje;

              /*  IF(psCampanhaProceso = lsCampanhaActual) THEN
                  UPDATE MSG_MENSA
                    SET    USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE,
                           EST_REGI            = '9'
                    WHERE  OID_MENS           = psOidMensaje;
                END IF;
              */

             --eliminamos patron mensa
              delete from msg_patro_mensa
               where pase_oid_patr_secc = psOidSeccion
                  and  MENS_OID_MENS = psOidMensaje;

                --eliminamos ls secciones
              delete from msg_patro_secci
               where oid_patr_secc = psOidSeccion;

            --verificar secciones y documentos en el patrom
              SELECT COUNT(1) INTO lnCont
              from MSG_PATRO_SECCI x
                where  x.PATR_OID_PATR = lnOidPatron
                 and x.MDOC_COD_MENS_DOCU = psOidDocumento--se trata de cod documento ver desc en parametros
                 AND x.OID_PATR_SECC <> psOidSeccion
                 and x.ind_acti = 1 ;


               --no existen secciones activas para el patron, por lo tanto eliminamos el patron
               if(lnCont = 0) then

                --se procede a eliminar ltodo el patron de ese periodo Y DOCUMENTO
                  delete from msg_patro_perio
                  where
                    PATR_OID_PATR = lnOidPatron
                    AND PERI_OID_PERI = lnOidPeriodoCorpo;

                  delete from msg_patro
                  where  oid_patr  = lnOidPatron;

               end if;

          else

                --eliminamos mensaje_campa
                 delete from msg_mensa_campa
                  where cam_proc =  psCampanhaProceso
                    and COD_MENS = lsCodMensaje;


                 --eliminamos patron mensa
                  delete from msg_patro_mensa
                   where pase_oid_patr_secc = psOidSeccion
                      and  MENS_OID_MENS = psOidMensaje;

                /*
                IF(psCampanhaProceso = lsCampanhaActual) THEN
                  UPDATE MSG_MENSA
                    SET    USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE,
                          -- IND_ACTI           = psIndicadorActivo,
                           EST_REGI            = '9'
                    WHERE  OID_MENS           = psOidMensaje;
                END IF;
                */

          end if;



        --ACTUALIZAMOS SI EXISTE MENSAJES EN E BUZON X LISTA EXTERNA
       UPDATE MSG_BUZON_MENSA
	   SET EST_REGI = '9',
	       IND_ACTI =0,
	       USU_MODI = psCodigoUsuario,
	       FEC_MODI = SYSDATE
	   WHERE
		 MENS_OID_MENS = psOidMensaje
		 AND MODU_OID_MODU_ORIG = lnOidModulo
		 AND PERI_OID_PERI = lnOidPeriodoCorpo
		 AND IND_ACTI =1;

      end if;  --fin del oidmensaje  is not null


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_EXCLU_MENSA: '||ls_sqlerrm);
 END MSG_PR_EXCLU_MENSA;

/***************************************************************************
Descripcion : Proceso que genera mensajes Informacion de Gerentes
Fecha Creacion : 17/10/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_GEREN
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

 BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
    )
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.clie_oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.nombre, --'DV01'
            base.codigoGZ, -- 'DV02',
            base.codigoZona, -- 'DV03',
            base.nombreGZ, -- 'DV04',
            base.movilGZ, -- 'DV05',
            base.emailGZ, -- 'DV06',
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT DISTINCT pedc.clie_oid_clie,
                    mc.val_nom1 AS nom1,
                    mc.val_nom2 AS nom2,
                    mc.val_ape1 AS ape1,
                    mc.val_ape2 AS ape2,
                    mc.val_apel_casa AS apec,
                    mc.val_nom1 ||' '||mc.val_nom2||' '||mc.val_ape1||' '||mc.val_ape2 AS nombre,
                    uadm.cod_clie AS codigoGZ,
                    uadm.cod_zona AS codigoZona,
                    uadm.val_ape1 ||' '|| uadm.val_ape2 ||' '|| uadm.val_nom1 ||' '|| uadm.val_nom2 AS nombreGZ,
                    uadm.movilGZ,
                    uadm.emailGZ
               FROM msg_tmp_pedid_clien pedc,
                    mae_clien mc,
                    (
                     SELECT DISTINCT mcua.clie_oid_clie,
                            zorg.oid_regi, zzon.oid_zona, zorg.cod_regi, zzon.cod_zona,
                            gzon.cod_clie, gzon.val_ape1, gzon.val_ape2, gzon.val_nom1, gzon.val_nom2,
                            cltm.val_text_comu movilGZ, clml.val_text_comu emailGZ
                       FROM mae_clien_unida_admin mcua,
                            zon_terri_admin ztad,
                            zon_secci zscc,
                            zon_zona zzon,
                            zon_regio zorg,
                            mae_clien gzon,
                            (
                             SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
                               FROM mae_clien_comun clco,
                                    mae_tipo_comun ticm
                              WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                                AND cod_tipo_comu = 'TM'
                            ) cltm,
                            (
                             SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
                               FROM mae_clien_comun clco,
                                    mae_tipo_comun ticm
                              WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                                AND cod_tipo_comu = 'ML'
                            ) clml
                      WHERE mcua.ztad_oid_terr_admi = ztad.oid_terr_admi
                        AND ztad.zscc_oid_secc = zscc.oid_secc
                        AND zscc.zzon_oid_zona = zzon.oid_zona
                        AND zzon.zorg_oid_regi = zorg.oid_regi
                        AND zzon.clie_oid_clie = gzon.oid_clie(+)
                        AND gzon.oid_clie = cltm.clie_oid_clie(+)
                        AND gzon.oid_clie = clml.clie_oid_clie(+)
                        -- Condicion adicional --
                        AND mcua.ind_acti = 1
                    ) uadm
              WHERE pedc.cod_tipo_soli = lsCodTipoSoli
                AND pedc.clie_oid_clie = mc.oid_clie
                AND mc.oid_clie = uadm.clie_oid_clie
            ) base
    );

  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_GEREN: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_GEREN;

/***************************************************************************
Descripcion : Proceso que genera mensajes de Faltantes Anunciados
Fecha Creacion : 13/12/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_FALTA_ANUNC
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

CURSOR curMensa ( lnoidperiodo NUMBER, lsCodigoTipoSolicitud VARCHAR2  ) IS
  SELECT temp.clie_oid_clie
    FROM msg_tmp_pedid_clien temp
   WHERE temp.perd_oid_peri = lnoidperiodo
     AND temp.cod_tipo_soli = lsCodigoTipoSolicitud
   GROUP BY temp.clie_oid_clie
       ;

TYPE t_curMensa IS TABLE OF curMensa%ROWTYPE INDEX BY BINARY_INTEGER;
regMensa t_curMensa;

-- Variables de trabajo
lsCodigoTipoSolicitud PED_TIPO_SOLIC.cod_tipo_soli%TYPE;
lnoidperiodo NUMBER;
lnoidmarca SEG_MARCA.oid_marc%TYPE;
lnoidcanal SEG_CANAL.oid_cana%TYPE;
lsnumerolote MSG_BUZON_MENSA.num_lote%TYPE;
lscodmensaje MSG_MENSA.cod_mens%TYPE;
lscodmodulo SEG_MODUL.cod_modu%TYPE;
lnOidModuloOrigen NUMBER;
lnoidmensaje NUMBER;
lnlimitrows NUMBER := 1000;

BEGIN
    -- Asignación de valores de variables

    lsCodMensaje := MSG_FN_OBTEN_DATO( pscodigoplantilla,
                                       pscondigoident,
                                       TIPO_DATO_MENSAJE,
                                       TIPO_RETORNO_CODIGO );

    lsCodModulo := MSG_FN_OBTEN_DATO( pscodigoplantilla,
                                      pscondigoident,
                                      TIPO_DATO_MODULO_ORIGEN,
                                      TIPO_RETORNO_CODIGO );

    lnoidmarca := MSG_FN_OBTEN_DATO( pscodigoplantilla,
                                    pscondigoident,
                                    TIPO_DATO_MARCA,
                                    TIPO_RETORNO_OID );

    lnoidcanal := MSG_FN_OBTEN_DATO( pscodigoplantilla,
                                    pscondigoident,
                                    TIPO_DATO_CANAL,
                                    TIPO_RETORNO_OID );

    lnoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodigoPeriodo,
                                                                lnoidmarca,
                                                                lnoidcanal );

    lsCodigoTipoSolicitud := MSG_FN_OBTEN_DATO( psCodigoPlantilla,
                                                psCondigoIdent,
                                                TIPO_DATO_TIPO_SOLICITUD,
                                                TIPO_RETORNO_CODIGO );

    SELECT oid_mens
      INTO lnoidmensaje
      FROM msg_mensa
     WHERE cod_mens = lsCodMensaje;

    SELECT oid_modu
      INTO lnOidModuloOrigen
      FROM seg_modul
     WHERE cod_modu = lsCodModulo;
    /*
      Proceso de Generación del mensaje Faltante Anunciado
    */
    OPEN curMensa( lnoidperiodo, lsCodigoTipoSolicitud );
    LOOP
      FETCH curMensa BULK COLLECT INTO regMensa LIMIT lnlimitrows;
      EXIT WHEN regMensa.COUNT = 0;
        FOR x IN regMensa.FIRST .. regMensa.LAST LOOP
                           --
            FOR z IN (
                      SELECT SUBSTR( a.val_codi_vent,1,5 ) || '  ' val_codi_vent_a,
                             SUBSTR( a.des_prod,1,27 ) || '  ' des_prod_a,
                             SUBSTR( a.val_prec_prod,1,7 ) || '  ' val_prec_prod_a,
                             SUBSTR( a.des_cata,1,8 ) || '  ' des_cata_a,
                             SUBSTR( a.val_nume_pagi,1,3 ) || '  ' val_nume_pagi_a,
                             --
                             SUBSTR( b.val_codi_vent,1,5 ) || '  ' val_codi_vent_b,
                             SUBSTR( b.des_prod,1,27 ) || '  ' des_prod_b,
                             SUBSTR( b.val_prec_prod,1,7 ) || '  ' val_prec_prod_b,
                             SUBSTR( b.des_cata,1,8 ) || '  ' des_cata_b,
                             SUBSTR( b.val_nume_pagi,1,3 ) || '  ' val_nume_pagi_b
                        FROM (
                              SELECT data1.secuencia - (data1.secuencia/2-0.5) Linea,
                                     data1.val_codi_vent,
                                     data1.des_prod,
                                     data1.val_prec_prod,
                                     data1.des_cata,
                                     data1.val_nume_pagi
                                FROM (
                                      SELECT rownum secuencia,
                                             faan.*
                                        FROM msg_produ_falta_anunc faan
                                       WHERE faan.pais_cod_pais = psCodigoPais
                                         AND faan.camp_cod_camp = psCodigoPeriodo
                                     ) data1
                               WHERE MOD(data1.secuencia,2) = 1
                             ) a,
                             (
                              SELECT data2.secuencia - (data2.secuencia/2) Linea,
                                     data2.val_codi_vent,
                                     data2.des_prod,
                                     data2.val_prec_prod,
                                     data2.des_cata,
                                     data2.val_nume_pagi
                                FROM (
                                      SELECT rownum secuencia,
                                             faan.*
                                        FROM msg_produ_falta_anunc faan
                                       WHERE faan.pais_cod_pais = psCodigoPais
                                         AND faan.camp_cod_camp = psCodigoPeriodo
                                     ) data2
                               WHERE MOD(data2.secuencia,2) = 0
                             ) b
                       WHERE a.linea = b.linea(+)
                     ) LOOP
                    /*
                      Insertar registros en el buzón de mensajes
                    */
                    INSERT INTO MSG_BUZON_MENSA
                      (oid_buzo_mens,
                       num_secu,
                       clie_oid_clie,
                       mens_oid_mens,
                       modu_oid_modu_orig,
                       dato_vari_01,
                       dato_vari_02,
                       dato_vari_03,
                       dato_vari_04,
                       dato_vari_05,
                       dato_vari_06,
                       dato_vari_07,
                       dato_vari_08,
                       dato_vari_09,
                       dato_vari_10,
                       fec_grab,
                       ind_list_cons,
                       ind_acti,
                       fec_modi)
                    SELECT MSG_BUME_SEQ.NEXTVAL oid_buzo_mens,
                           MSG_BUM2_SEQ.NEXTVAL num_secu,
                           --
                           regMensa(x).clie_oid_clie clie_oid_clie,
                           lnoidmensaje mens_oid_mens,
                           lnOidModuloOrigen modu_oid_modu_orig,
                           --
                           z.val_codi_vent_a dato_vari_01,
                           z.des_prod_a dato_vari_02,
                           z.val_prec_prod_a dato_vari_03,
                           z.des_cata_a dato_vari_04,
                           z.val_nume_pagi_a dato_vari_05,
                           --
                           z.val_codi_vent_b dato_vari_06,
                           z.des_prod_b dato_vari_07,
                           z.val_prec_prod_b dato_vari_08,
                           z.des_cata_b dato_vari_09,
                           z.val_nume_pagi_b dato_vari_10,
                           --
                           SYSDATE fec_grab,
                           0 ind_list_cons,
                           1 ind_acti,
                           SYSDATE fec_modi
                    FROM DUAL;
            END LOOP ;
        END LOOP;
    END LOOP;
    CLOSE curMensa;
EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_FALTA_ANUNC: '||ls_sqlerrm);
END MSG_PR_GENER_FALTA_ANUNC;


/***************************************************************************
Descripcion : Proceso que inserta o actualiza registro en la tabla de Faltante
Anunciando
Fecha Creacion : 11/04/2013
Autor : Carlos Bazalar
Parametros:
  psCodigoPais: Codigo de Pais,
  psCodigoPeriodo: Codigo de Periodo,
  psCodigoCUV: Codigo CUV,
  psdescripcionProducto: Descripcion del Producto,
  psprecio: Precio del Producto,
  psdescripcionCatalogo: Descripcion del Catalogo,
  pspagina: Pagina del Catalogo,
  pscodigoUsuario: Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_FALTA_ANUNC
(
  psCodigoPais VARCHAR2,
  psCodigoPeriodo VARCHAR2,
  psCodigoCUV  VARCHAR2,
  psdescripcionProducto VARCHAR2,
  psprecio VARCHAR2,
  psdescripcionCatalogo VARCHAR2,
  pspagina VARCHAR2,
  pscodigoUsuario VARCHAR2
)
IS
 lsCodigoPeriodoSiguiente VARCHAR2(6);
 lnIdPais                SEG_PAIS.OID_PAIS%TYPE;
 lnIdCanal               SEG_CANAL.OID_CANA%TYPE;
 lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
 lnPrecio                msg_produ_falta_anunc.VAL_PREC_PROD%TYPE;
 lnPagina                msg_produ_falta_anunc.val_nume_pagi%TYPE;

BEGIN
 /* Obteniendo oids */
 lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
 lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
 lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
 lsCodigoPeriodoSiguiente := per_pkg_repor_perce.per_fn_obtie_perio(psCodigoPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1);
 lnPrecio := to_number(psprecio, '999999999.99');
 lnPagina := to_number(pspagina,'999');
 BEGIN

   INSERT INTO msg_produ_falta_anunc(
		pais_cod_pais,
		camp_cod_camp,
		val_codi_vent,
		cod_camp_anun,
		des_prod,
		val_prec_prod,
		des_cata,
		val_nume_pagi,
		usu_crea,
		fec_crea,
    est_regi
		)
	values (
	   psCodigoPais,
	   pscodigoPeriodo,
	   pscodigoCUV,
	   lsCodigoPeriodoSiguiente,
	   psdescripcionProducto,
	   lnPrecio,
	   psdescripcionCatalogo,
	   lnPagina,
	   pscodigoUsuario,
	   SYSDATE,
	   '1'
	  )	;

   EXCEPTION
   WHEN DUP_VAL_ON_INDEX THEN
       UPDATE msg_produ_falta_anunc
       SET
        cod_camp_anun = lsCodigoPeriodoSiguiente,
          des_prod = psdescripcionProducto,
          val_prec_prod = lnPrecio,
          des_cata = psdescripcionCatalogo,
          val_nume_pagi = lnpagina,
          usu_modi = pscodigoUsuario,
          fec_modi = SYSDATE,
          est_regi = '1'
       WHERE pais_cod_pais = psCodigoPais
		  AND camp_cod_camp = pscodigoPeriodo
        AND val_codi_vent = pscodigoCUV;
   END;
 EXCEPTION
 WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ACTUA_FALTA_ANUNC: '||ls_sqlerrm);

END MSG_PR_ACTUA_FALTA_ANUNC;

/***************************************************************************
Descripcion : Proceso que inserta o actualiza registro en la tabla de Conferencias
Fecha Creacion : 19/04/2013
Autor : Aurelio Oviedo
Parametros:
  psCodigoPais: Codigo de Pais,
  psCodigoPeriodo: Codigo de Periodo,
  psCodigoRegion: Codigo Region,
  psCodigoZona: Codigo Zona,
  psGerente: Nombre Gerente,
  psDescripcionLocal: Descripcion del Local,
  psDireccion: Direccion,
  psFecha: Fecha,
  psHora: Hora,
  psCodigoUsuario: Codigo de Usuario
***************************************************************************/
PROCEDURE MSG_PR_ACTUA_CONFE
(
  psCodigoPais       VARCHAR2,
  psCodigoPeriodo    VARCHAR2,
  psCodigoRegion     VARCHAR2,
  psCodigoZona       VARCHAR2,
  psGerente          VARCHAR2,
  psDescripcionLocal VARCHAR2,
  psDireccion        VARCHAR2,
  psFecha            VARCHAR2,
  psHora             VARCHAR2,
  pscodigoUsuario    VARCHAR2
)
IS
lnCorrelativo NUMBER;

BEGIN
  SELECT NVL(MAX(COR_CONF), 0) + 1
    INTO lnCorrelativo
    FROM MSG_MENSA_CONFE;

 BEGIN

   INSERT INTO MSG_MENSA_CONFE(
		COR_CONF,
		CAM_PROC,
		COD_REGI,
		COD_ZONA,
		NOM_GERE_ZONA,
		VAL_LOCA,
		VAL_DIRE,
		VAL_FECH,
		VAL_HORA,
		USU_CREA,
    FEC_CREA,
    EST_REGI
		)
	values (
	   lnCorrelativo,
	   psCodigoPeriodo,
	   psCodigoRegion,
     psCodigoZona,
	   psGerente,
	   psDescripcionLocal,
	   psDireccion,
	   psFecha,
	   psHora,
	   pscodigoUsuario,
	   SYSDATE,
	   '1'
	  )	;

 /*EXCEPTION
 WHEN DUP_VAL_ON_INDEX THEN
     UPDATE msg_produ_falta_anunc
     SET
        cod_camp_anun = lsCodigoPeriodoSiguiente,
        des_prod = psdescripcionProducto,
        val_prec_prod = lnPrecio,
        des_cata = psdescripcionCatalogo,
        val_nume_pagi = lnpagina,
        usu_modi = pscodigoUsuario,
        fec_modi = SYSDATE,
        est_regi = '1'
     WHERE pais_cod_pais = psCodigoPais
		  AND camp_cod_camp = pscodigoPeriodo
		  AND val_codi_vent = pscodigoCUV;*/
 END;
EXCEPTION
 WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ACTUA_CONFE: '||ls_sqlerrm);

END MSG_PR_ACTUA_CONFE;


/***************************************************************************
Descripcion : Proceso que genera mensajes Descuento Programa Nuevas
Fecha Creacion : 17/10/2012
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_DESCU_GANAD
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;
 lnOidMensajeCamActua  NUMBER;
 lnOidMensajeCamAsign  NUMBER;

 BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   --lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   --Mensaje Descuento Campaña Actual ( NVSDCA )--
   BEGIN
        SELECT msg.mens_oid_mens
          INTO lnOidMensajeCamActua
          FROM msg_mensa_campa msg
         WHERE msg.cod_mens = NVS_DESCU_CAMPA_ACTUA
           AND msg.cam_proc = psCodigoPeriodo
           AND msg.est_regi <> '9';
      EXCEPTION
         WHEN OTHERS THEN
            lnOidMensajeCamActua := 0;
      END;

      --Mensaje Descuento Campaña Asignacion ( NVSDCS )--
      BEGIN
        SELECT msg.mens_oid_mens
          INTO lnOidMensajeCamAsign
          FROM msg_mensa_campa msg
         WHERE msg.cod_mens = NVS_DESCU_CAMPA_ASIGN
           AND msg.cam_proc = psCodigoPeriodo
           AND msg.est_regi <> '9';
      EXCEPTION
         WHEN OTHERS THEN
            lnOidMensajeCamAsign := 0;
      END;

      IF (lnOidMensajeCamActua <> 0) THEN

          INSERT INTO MSG_BUZON_MENSA
            (
             oid_buzo_mens,
             num_secu ,
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
             num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
            )
            (
             SELECT MSG_BUME_SEQ.NEXTVAL,
                    MSG_BUM2_SEQ.NEXTVAL,
                    NULL,
                    base.oid_clie,
                    lnOidMensajeCamActua,
                    lnOidModuloOrigen,
                    base.nom1,
                    base.nom2,
                    base.ape1,
                    base.ape2,
                    base.apec,
                    base.cod_clie, --'DV01'
                    base.mon_desc, --'DV02'
                    base.cam_asig, --'DV03'
                    NULL, SYSDATE, NULL, 1, NULL, 1
               FROM (
                     SELECT DISTINCT MC.OID_CLIE,
                            mc.val_nom1 AS nom1,
                            mc.val_nom2 AS nom2,
                            mc.val_ape1 AS ape1,
                            mc.val_ape2 AS ape2,
                            mc.val_apel_casa AS apec,
                            mc.val_nom1 ||' '||mc.val_nom2||' '||mc.val_ape1||' '||mc.val_ape2 AS nombre,
                            mc.cod_clie,
                            cde.mon_desc,
                            cde.cam_asig
                       FROM msg_tmp_pedid_clien pedc,
                            mae_clien mc,
                            nvs_clien_descu cde
                      WHERE cde.cam_proc = psCodigoPeriodo
                        AND cde.cod_clie = mc.cod_clie
                        AND est_regi <> 9
                        AND est_desc = 'P' -- Pendiente
                        AND pedc.cod_tipo_soli = lsCodTipoSoli
                        AND pedc.clie_oid_clie = mc.oid_clie
                    ) base
            );
       END IF;

      IF (lnOidMensajeCamAsign <> 0) THEN

          INSERT INTO MSG_BUZON_MENSA
            (
             oid_buzo_mens,
             num_secu ,
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
             num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
            )
            (
             SELECT MSG_BUME_SEQ.NEXTVAL,
                    MSG_BUM2_SEQ.NEXTVAL,
                    NULL,
                    base.oid_clie,
                    lnOidMensajeCamAsign,
                    lnOidModuloOrigen,
                    base.nom1,
                    base.nom2,
                    base.ape1,
                    base.ape2,
                    base.apec,
                    base.cod_clie, --'DV01'
                    base.mon_desc, --'DV02'
                    base.cam_asig, --'DV03'
                    NULL, SYSDATE, NULL, 1, NULL, 1
               FROM (
                     SELECT DISTINCT MC.OID_CLIE,
                            mc.val_nom1 AS nom1,
                            mc.val_nom2 AS nom2,
                            mc.val_ape1 AS ape1,
                            mc.val_ape2 AS ape2,
                            mc.val_apel_casa AS apec,
                            mc.val_nom1 ||' '||mc.val_nom2||' '||mc.val_ape1||' '||mc.val_ape2 AS nombre,
                            mc.cod_clie,
                            cde.mon_desc,
                            cde.cam_asig
                       FROM msg_tmp_pedid_clien pedc,
                            mae_clien mc,
                            nvs_clien_descu cde
                      WHERE cde.cam_asig = psCodigoPeriodo
                        AND cde.cod_clie = mc.cod_clie
                        AND est_regi <> 9
                        AND est_desc = 'S' -- Asignado
                        AND pedc.cod_tipo_soli = lsCodTipoSoli
                        AND pedc.clie_oid_clie = mc.oid_clie
                    ) base
             );
       END IF;

  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_DESCU_GANAD: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_DESCU_GANAD;


/***************************************************************************
Descripcion : Proceso que genera mensajes de Recomendaciones INC
Fecha Creacion : 19/11/2014
Fecha Modificacion: 28/11/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_CONCU_RECOM
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
    )
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.oidRecomendante,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1Recomendante,
            base.nom2Recomendante,
            base.ape1Recomendante,
            base.ape2Recomendante,
            base.apecRecomendante,
            base.codigoRecomendante, --'DV01'
            base.codigoConcurso, -- 'DV02'
            base.nombreConcurso, -- 'DV03'
            base.campanaInicioConcurso, -- 'DV04'
            base.codigoRecomendada, -- 'DV05'
            base.campanaIngresoRecomendada, -- 'DV06'
            base.nombreRecomendada, -- 'DV07'
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT cli1.oid_clie oidRecomendante,
                    cli1.val_nom1 nom1Recomendante,
                    cli1.val_nom2 nom2Recomendante,
                    cli1.val_ape1 ape1Recomendante,
                    cli1.val_ape2 ape2Recomendante,
                    cli1.val_apel_casa apecRecomendante,
                    cli1.cod_clie codigoRecomendante,
                    gen.num_conc codigoConcurso,
                    SUBSTR(UPPER(gen.val_nomb),1,50) nombreConcurso,
                    FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.perd_oid_peri_desd) campanaInicioConcurso,
                    cli2.cod_clie codigoRecomendada,
                    SUBSTR(UPPER(cli2.val_nom1||' '||cli2.val_nom2||' '||cli2.val_ape1||' '||cli2.val_ape2),1,50) nombreRecomendada,
                    FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(redo.perd_oid_peri) campanaIngresoRecomendada
               FROM inc_clien_recte rete,
                    inc_clien_recdo redo,
                    inc_concu_param_consu con,
                    inc_concu_param_gener gen,
                    mae_clien cli1,
                    mae_clien cli2
              WHERE redo.clr3_oid_clie_rete = rete.oid_clie_rete
                AND rete.copa_oid_para_gral = con.copa_oid_para_gral
                AND redo.ind_efec = 1
                AND rete.copa_oid_para_gral = gen.oid_para_gral
                AND rete.clie_oid_clie = cli1.oid_clie
                AND redo.clie_oid_clie = cli2.oid_clie
                AND gen.ind_acti = 1
                AND rete.clie_oid_clie IN (
                                           SELECT DISTINCT clie_oid_clie
                                             FROM msg_tmp_pedid_clien
                                            WHERE cod_tipo_soli = lsCodTipoSoli
                                          )
                AND GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(redo.perd_oid_peri),
                    GREATEST(con.num_mini_pedi, con.num_mini_pedi_reco)) = psCodigoPeriodo   -- Campaña Facturación
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_CONCU_RECOM: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_CONCU_RECOM;


/***************************************************************************
Descripcion : Proceso que genera mensajes de Metas
Fecha Creacion : 20/11/2014
Fecha Modificacion: 20/11/2014
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_METAS
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
    )
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.oidCliente,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.codigoCliente, --'DV01'
            base.descripcionTipoMeta, -- 'DV02'
            base.importeMeta, -- 'DV03'
            base.importeOAAcumulado, -- 'DV04'
            base.importeFaltaLograrMeta, -- 'DV05'
            base.numCampanasLograrMeta, -- 'DV06'
            base.campanaInicioMeta, -- 'DV07'
            base.campanaFinMeta, -- 'DV08'
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             WITH meta AS (
                           SELECT nvcg.cod_clie,
                                  temp.clie_oid_clie,
                                  nvtl.des_tipo_logr,
                                  ROUND(nvcg.imp_logr,(SELECT num_deci
                                                         FROM seg_pais p,
                                                              seg_moned m
                                                        WHERE p.mone_oid_mone = m.oid_mone
                                                          AND p.cod_pais =  psCodigoPais)) imp_logr,
                                 (
                                  SELECT SUM(cons.val_tota_paga_loca)
                                    FROM ped_solic_cabec soca,
                                         ped_solic_cabec cons,
                                         ped_tipo_solic_pais tspa,
                                         ped_tipo_solic tsol,
                                         cra_perio perd,
                                         seg_perio_corpo peri,
                                         mae_clien clie
                                   WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                     AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                     AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                     AND soca.perd_oid_peri = perd.oid_peri
                                     AND perd.peri_oid_peri = peri.oid_peri
                                     AND soca.clie_oid_clie = clie.oid_clie
                                     --
                                     AND clie.cod_clie = nvcg.cod_clie
                                     AND tsol.cod_tipo_soli = lsCodTipoSoli
                                     AND cons.esso_oid_esta_soli != '4'
                                     AND peri.cod_peri BETWEEN nvcg.cmp_inic AND nvcg.cmp_fina
                                 ) imp_opor_ahor_acum,
                                 nvcg.cmp_fina,
                                 nvcg.cmp_inic,
                                 psCodigoPeriodo cmp_proc,
                                 GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO(psCodigoPeriodo,nvcg.cmp_fina) val_dife_peri
                            FROM nvs_consu_logro nvcg,
                                 nvs_tipo_logro nvtl,
                                 (
                                  SELECT DISTINCT clie.cod_clie,
                                         tmsg.clie_oid_clie
                                    FROM msg_tmp_pedid_clien tmsg,
                                         mae_clien clie
                                   WHERE tmsg.clie_oid_clie = clie.oid_clie
                                 ) temp
                           WHERE nvcg.cod_tipo_logr = nvtl.cod_tipo_logr
                             AND nvcg.cod_clie = temp.cod_clie
                             AND nvcg.cod_pais = psCodigoPais
                             --
                             AND psCodigoPeriodo BETWEEN nvcg.cmp_inic AND nvcg.cmp_fina
                             AND nvcg.est_regi = '1' -- Activo
                             AND nvcg.est_logr = '1' -- Registro Válido
                         )
              SELECT mc.val_nom1 nom1,
                     mc.val_nom2 nom2,
                     mc.val_ape1 ape1,
                     mc.val_ape2 ape2,
                     mc.val_apel_casa apec,
                     meta.cod_clie codigoCliente,
                     meta.clie_oid_clie oidCliente,
                     meta.des_tipo_logr descripcionTipoMeta,
                     meta.imp_logr importeMeta,
                     NVL(meta.imp_opor_ahor_acum,0) importeOAAcumulado,
                     CASE WHEN meta.imp_logr - NVL(meta.imp_opor_ahor_acum,0)  > 0 THEN meta.imp_logr - NVL(meta.imp_opor_ahor_acum,0) ELSE 0 END importeFaltaLograrMeta,
                     meta.val_dife_peri numCampanasLograrMeta,
                     meta.cmp_inic campanaInicioMeta,
                     meta.cmp_fina campanaFinMeta
                FROM meta,
                     mae_clien mc
               WHERE mc.cod_clie = meta.cod_clie
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_METAS: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_METAS;


/***************************************************************************
Descripcion : Proceso que genera mensajes del concurso Programa de Puntos
Fecha Creacion : 17/03/2015
Fecha Modificacion:
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_CONCU_PRPTO
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;

BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);

   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   SELECT oid_mens
     INTO lnOidMensaje
     FROM msg_mensa
    WHERE cod_mens = lsCodMensaje;

  INSERT INTO MSG_BUZON_MENSA
    (
     oid_buzo_mens,
     num_secu ,
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
     dato_vari_10,
     dato_vari_11,
     dato_vari_12,
     dato_vari_13,

     num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
)
    (
     SELECT MSG_BUME_SEQ.NEXTVAL,
            MSG_BUM2_SEQ.NEXTVAL,
            NULL,
            base.clie_oid_clie,
            lnOidMensaje,
            lnOidModuloOrigen,
            base.nom1,
            base.nom2,
            base.ape1,
            base.ape2,
            base.apec,
            base.codigoCliente, --'DV01'
            base.nombreConcurso, -- 'DV02'
            base.codigoConcurso, -- 'DV03'
            base.ptosAcumCamAnte, -- 'DV04'
            base.ptosPedCon, -- 'DV05'
            base.ptosVentasCmp, -- 'DV06'
            base.ptosPromoCmp,  -- 'DV07'
            base.ptosRecomCmp, -- 'DV08'
            base.ptosRetailCmp, -- 'DV09'
            base.ptosCanjesCmp, -- 'DV10'
            base.ptosCDRCmp, -- 'DV11'
            base.ptosTotal, -- 'DV12'
            base.ptosCmp,     -- 'DV13'
            NULL, SYSDATE, NULL, 1, NULL, 1
       FROM (
             SELECT detalle.clie_oid_clie,
                    mc.val_nom1 nom1,
                    mc.val_nom2 nom2,
                    mc.val_ape1 ape1,
                    mc.val_ape2 ape2,
                    mc.val_apel_casa apec,
                    mc.cod_clie codigoCliente,
                    SUBSTR(UPPER(cpg.val_nomb),1,35) nombreConcurso,
                    cpg.num_conc codigoConcurso,
                    SUM(Ptos_acum_cante) ptosAcumCamAnte,
                    SUM(Ptos_pedcon_cmp)  ptosPedCon,
                    SUM(Ptos_ventas_cmp) ptosVentasCmp,
                    SUM(Ptos_promo_cmp) ptosPromoCmp,
                    SUM(Ptos_recom_cmp) ptosRecomCmp,
                    SUM(Ptos_retail_cmp) ptosRetailCmp,
                    SUM(Ptos_canjes_cmp) ptosCanjesCmp,
                    SUM(Ptos_cdr_cmp) ptosCDRCmp,
                    SUM(Ptos_Total) ptosTotal,
                    SUM(Ptos_Cmp) ptosCmp
               FROM (
                     SELECT cc.clie_oid_clie,
                            cc.copa_oid_para_gral,
                            (CASE WHEN  cc.perd_oid_peri < lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_acum_cante,
--                            (CASE WHEN  cc.perd_oid_peri >= lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_acum_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_pedcon_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE 'VENTA%' or  des_moti IS NULL) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_ventas_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_promo_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_recom_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_retail_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' ) AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_canjes_cmp,
                            (CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%'
                                                        OR upper(des_moti)  LIKE '%SIN PEDIDO%'   )
                                                        AND cc.perd_oid_peri >= lnOidPeriodo
                                                        THEN num_punt ELSE 0 END)  Ptos_cdr_cmp,
                            num_punt Ptos_Total,
                            (CASE WHEN  cc.perd_oid_peri >= lnOidPeriodo THEN num_punt ELSE 0 END) Ptos_cmp
                       FROM inc_cuent_corri_punto cc,
                            inc_concu_param_gener concu
                      WHERE cc.clie_oid_clie NOT IN (
                                                     SELECT inc_desca.clie_oid_clie
                                                       FROM inc_desca
                                                      WHERE inc_desca.copa_oid_para_gral = cc.copa_oid_para_gral
                                                    )
                        AND cc.clie_oid_clie IN (
                                                 SELECT distinct clie_oid_clie
                                                   FROM msg_tmp_pedid_clien
                                                  WHERE cod_tipo_soli = 'SOC'
                          )
                            AND cc.copa_oid_para_gral = concu.oid_para_gral
                            AND concu.ind_acti=1
                            AND NVL(concu.ind_comu,1) = 1
                            AND concu.ind_prog_punt = 1
                    ) detalle,
                    inc_concu_param_gener cpg,
                    inc_param_gener_premi gen,
                    inc_concu_param_consu cons,
                    mae_clien mc
              WHERE cpg.oid_para_gral = detalle.copa_oid_para_gral
                AND cons.copa_oid_para_gral = cpg.oid_para_gral
                AND gen.copa_oid_para_gral = cpg.oid_para_gral
                AND detalle.clie_oid_clie = mc.oid_clie
                AND cpg.ind_acti = 1
                AND NVL(cpg.ind_comu,1) = 1
                AND cpg.ind_prog_punt = 1
              GROUP BY detalle.clie_oid_clie,
                    mc.val_nom1,
                    mc.val_nom2,
                    mc.val_ape1,
                    mc.val_ape2,
                    mc.val_apel_casa,
                    mc.cod_clie,
                    cpg.val_nomb,
                    cpg.num_conc
             HAVING (SUM(Ptos_Total) > 0 or SUM(Ptos_canjes_cmp)>0)
            ) base
    );

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_CONCU_PRPTO: '||ls_sqlerrm);
END MSG_PR_GENER_CONCU_PRPTO;


/***************************************************************************
Descripcion : Eliminar Caracteres Especiales de varias tablas
Fecha Creacion : 14/04/2015
Fecha Modificacion: 14/04/2015
Autor : CSVD-FFVV
Parametros:
 psCodigoPais : Codigo de Pais
***************************************************************************/
PROCEDURE MSG_PR_ELIMI_CARAC_ESPEC(
  psCodigoPais       VARCHAR2
)
IS

BEGIN
  -- Elimina caracteres especiales de Comunicacion
	UPDATE mae_clien_comun mcc
     SET mcc.val_text_comu = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcc.val_text_comu,'///',''),'~',''),'&',''),'>',''),'<',''),',',''),';','')
   WHERE (mcc.val_text_comu LIKE '%///%' OR mcc.val_text_comu LIKE '%~%' OR mcc.val_text_comu LIKE '%&%'
      OR mcc.val_text_comu LIKE '%>%' OR mcc.val_text_comu LIKE '%<%' OR mcc.val_text_comu LIKE '%,%'
	  	OR mcc.val_text_comu LIKE '%;%');

  UPDATE mae_clien_comun mcc
     SET mcc.val_text_comu = REPLACE(REPLACE(REPLACE (mcc.val_text_comu,CHR(10),' '),CHR(9),' '),CHR(31),'')
   WHERE mcc.val_text_comu LIKE '%'||CHR(10)||'%' OR mcc.val_text_comu LIKE '%'||CHR(9)||'%' OR mcc.val_text_comu LIKE '%'||CHR(31)||'%';

  -- Retira del texto, los caracteres creados con combinaciones de teclas iniciadas con CTRL ( Ejem "CTRL" + "_" )
  UPDATE mae_clien_comun mcc
     SET mcc.val_text_comu = REGEXP_REPLACE(mcc.val_text_comu, '[[:cntrl:]]','')
   WHERE REGEXP_LIKE(mcc.val_text_comu, '[[:cntrl:]]');


	-- Elimina caracteres especiales de Comunicacion Referencia
	UPDATE mae_refer mr
     SET mr.val_telf = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mr.val_telf,'///',''),'~',''),'&',''),'>',''),'<',''),',',''),';','')
   WHERE (mr.val_telf LIKE '%///%' OR mr.val_telf LIKE '%~%' OR mr.val_telf LIKE '%&%'
	    OR mr.val_telf LIKE '%>%' OR mr.val_telf LIKE '%<%' OR mr.val_telf LIKE  '%,%'
	  	OR mr.val_telf LIKE '%;%');

  UPDATE mae_refer mr
     SET mr.val_telf = REPLACE(REPLACE(REPLACE(mr.val_telf,CHR(10),' '),CHR(9),' '),CHR(31),'')
   WHERE mr.val_telf LIKE '%'||CHR(10)||'%' OR mr.val_telf LIKE '%'||CHR(9)||'%' OR mr.val_telf LIKE '%'||CHR(31)||'%';


  -- Elimina caracteres especiales de Direccion
  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),'/D','/ D'),'/d','/ d'),'!',''),'´',''),'`',''),'*',''),1,60)
   WHERE mcd.val_nomb_via LIKE '%///%' OR mcd.val_nomb_via LIKE '%~%' OR mcd.val_nomb_via LIKE '%&%'
      OR mcd.val_nomb_via LIKE '%>%' OR mcd.val_nomb_via LIKE '%<%' OR mcd.val_nomb_via LIKE '%;%'
      OR mcd.val_nomb_via LIKE '%/D%' OR mcd.val_nomb_via LIKE '%/d%' OR mcd.val_nomb_via LIKE '%!%'
      OR mcd.val_nomb_via LIKE '%´%'  OR mcd.val_nomb_via LIKE '%`%' OR mcd.val_nomb_via LIKE '%*%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'|',''),'÷',''),'+',''),'{',''),'}',''),'=',''),'\',''),'?',''),'¢',''),'Ç',''),'·',''),'¿','')
   WHERE mcd.val_nomb_via LIKE '%|%' OR mcd.val_nomb_via LIKE '%÷%' OR mcd.val_nomb_via LIKE '%+%'
      OR mcd.val_nomb_via LIKE '%{%' OR mcd.val_nomb_via LIKE '%}%' OR mcd.val_nomb_via LIKE '%=%'
      OR mcd.val_nomb_via LIKE '%\%' OR mcd.val_nomb_via LIKE '%?%' OR mcd.val_nomb_via LIKE '%¢%'
      OR mcd.val_nomb_via LIKE '%Ç%' OR mcd.val_nomb_via LIKE '%·%' OR mcd.val_nomb_via LIKE '%¿%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'Ø',''),'¥',''),'$',''),'¿',''),'[',''),']',''),'@',''),'«',''),'¦',''),'¹',''),'²',''),'¿','')
   WHERE mcd.val_nomb_via LIKE '%Ø%' OR mcd.val_nomb_via LIKE '%¥%' OR mcd.val_nomb_via LIKE '%$%'
      OR mcd.val_nomb_via LIKE '%¿%' OR mcd.val_nomb_via LIKE '%[%' OR mcd.val_nomb_via LIKE '%]%'
      OR mcd.val_nomb_via LIKE '%@%' OR mcd.val_nomb_via LIKE '%«%' OR mcd.val_nomb_via LIKE '%¦%'
      OR mcd.val_nomb_via LIKE '%¹%' OR mcd.val_nomb_via LIKE '%²%' OR mcd.val_nomb_via LIKE '%¿%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'´',''),'¬',''),'¨',''),'¿',''),'[',''),']',''),'@',''),'«',''),'¦',''),'¹',''),'²',''),'¿','')
   WHERE mcd.val_nomb_via LIKE '%´%' OR mcd.val_nomb_via LIKE '%¬%' OR mcd.val_nomb_via LIKE '%¨%'
      OR mcd.val_nomb_via LIKE '%¿%' OR mcd.val_nomb_via LIKE '%[%' OR mcd.val_nomb_via LIKE '%]%'
      OR mcd.val_nomb_via LIKE '%@%' OR mcd.val_nomb_via LIKE '%«%' OR mcd.val_nomb_via LIKE '%¦%'
      OR mcd.val_nomb_via LIKE '%¹%' OR mcd.val_nomb_via LIKE '%²%' OR mcd.val_nomb_via LIKE '%¿%';
      
  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,CHR(9),' '),CHR(10),' '),CHR(31),'')
   WHERE mcd.val_nomb_via LIKE '%'||CHR(9)||'%' OR mcd.val_nomb_via LIKE '%'||CHR(10)||'%' OR mcd.val_nomb_via LIKE '%'||CHR(31)||'%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE( REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),'/D','/ D'),'/d','/ d'),'!',''),'´',''),'`',''),'*','')
   WHERE mcd.val_obse LIKE '%///%' OR mcd.val_obse LIKE '%~%' OR mcd.val_obse LIKE '%&%'
      OR mcd.val_obse LIKE '%>%' OR mcd.val_obse LIKE '%<%' OR mcd.val_obse LIKE '%;%'
      OR mcd.val_obse LIKE '%/D%' OR mcd.val_obse LIKE '%/d%' OR mcd.val_obse LIKE '%!%'
      OR mcd.val_obse LIKE '%´%'  OR mcd.val_obse LIKE '%`%' OR mcd.val_obse LIKE '%*%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'|',''),'÷',''),'+',''),'{',''),'}',''),'=',''),'\',''),'?',''),'¢',''),'Ç',''),'·',''),'¿','')
   WHERE mcd.val_obse LIKE '%|%' OR mcd.val_obse LIKE '%÷%' OR mcd.val_obse LIKE '%+%'
      OR mcd.val_obse LIKE '%{%' OR mcd.val_obse LIKE '%}%' OR mcd.val_obse LIKE '%=%'
      OR mcd.val_obse LIKE '%\%' OR mcd.val_obse LIKE '%?%' OR mcd.val_obse LIKE '%¢%'
      OR mcd.val_obse LIKE '%Ç%' OR mcd.val_obse LIKE '%·%' OR mcd.val_obse LIKE '%¿%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'Ø',''),'¥',''),'$',''),'¿',''),'[',''),']',''),'@',''),'«',''),'¦',''),'¹',''),'²',''),'¿','')
   WHERE mcd.val_obse LIKE '%Ø%' OR mcd.val_obse LIKE '%¥%' OR mcd.val_obse LIKE '%$%'
      OR mcd.val_obse LIKE '%¿%' OR mcd.val_obse LIKE '%[%' OR mcd.val_obse LIKE '%]%'
      OR mcd.val_obse LIKE '%@%' OR mcd.val_obse LIKE '%«%' OR mcd.val_obse LIKE '%¦%'
      OR mcd.val_obse LIKE '%¹%' OR mcd.val_obse LIKE '%²%' OR mcd.val_obse LIKE '%¿%';
  
  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'´',''),'¬',''),'¨',''),'¿',''),'[',''),']',''),'@',''),'«',''),'¦',''),'¹',''),'²',''),'¿','')
   WHERE mcd.val_obse LIKE '%´%' OR mcd.val_obse LIKE '%¬%' OR mcd.val_obse LIKE '%¨%'
      OR mcd.val_obse LIKE '%¿%' OR mcd.val_obse LIKE '%[%' OR mcd.val_obse LIKE '%]%'
      OR mcd.val_obse LIKE '%@%' OR mcd.val_obse LIKE '%«%' OR mcd.val_obse LIKE '%¦%'
      OR mcd.val_obse LIKE '%¹%' OR mcd.val_obse LIKE '%²%' OR mcd.val_obse LIKE '%¿%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE (mcd.val_obse,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mcd.val_obse LIKE '%'||CHR(9)||'%' OR mcd.val_obse LIKE '%'||CHR(10)||'%' OR mcd.val_obse LIKE '%'||CHR(31)||'%';

  UPDATE mae_clien_direc mcd
     SET mcd.des_villa_pobl = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.des_villa_pobl,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),'/','')
   WHERE mcd.des_villa_pobl LIKE '%///%' OR mcd.des_villa_pobl LIKE '%~%' OR mcd.des_villa_pobl LIKE '%&%'
   OR mcd.des_villa_pobl LIKE '%>%' OR mcd.des_villa_pobl LIKE '%<%' OR mcd.des_villa_pobl LIKE '%;%'
   OR mcd.des_villa_pobl LIKE '%/%';

  UPDATE mae_clien_direc mcd
     SET mcd.des_villa_pobl = REPLACE(REPLACE(REPLACE (mcd.des_villa_pobl,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mcd.des_villa_pobl LIKE '%'||CHR(9)||'%' OR mcd.des_villa_pobl LIKE '%'||CHR(10)||'%' OR mcd.des_villa_pobl LIKE '%'||CHR(31)||'%';


	-- Elimina caracteres especiales de Nombres y Apellidos
  UPDATE mae_clien mc
     SET mc.val_ape1 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_ape1,'|',''),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'+',''),'{',''),'}',''),'--',''),'+','')
   WHERE mc.val_ape1 LIKE '%|%' OR mc.val_ape1 LIKE '%~%' OR mc.val_ape1 LIKE '%&%' OR mc.val_ape1 LIKE '%>%'  
      OR mc.val_ape1 LIKE '%<%' OR mc.val_ape1 LIKE '%;%' OR mc.val_ape1 LIKE '%,%' OR mc.val_ape1 LIKE '%+%'
      OR mc.val_ape1 LIKE '%{%' OR mc.val_ape1 LIKE '%}%' OR mc.val_ape1 LIKE '%--%' OR mc.val_ape1 LIKE '%+%';

  UPDATE mae_clien mc
     SET mc.val_ape2 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_ape2,'|',''),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'+',''),'{',''),'}',''),'--',''),'+','')
   WHERE mc.val_ape2 LIKE '%|%' OR mc.val_ape2 LIKE '%~%' OR mc.val_ape2 LIKE '%&%' OR mc.val_ape2 LIKE '%>%'  
      OR mc.val_ape2 LIKE '%<%' OR mc.val_ape2 LIKE '%;%' OR mc.val_ape2 LIKE '%,%' OR mc.val_ape2 LIKE '%+%'
      OR mc.val_ape2 LIKE '%{%' OR mc.val_ape2 LIKE '%}%' OR mc.val_ape2 LIKE '%--%' OR mc.val_ape2 LIKE '%+%';

  UPDATE mae_clien mc
     SET mc.val_nom1 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_nom1,'|',''),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'+',''),'{',''),'}',''),'--',''),'+','')
   WHERE mc.val_nom1 LIKE '%|%' OR mc.val_nom1 LIKE '%~%' OR mc.val_nom1 LIKE '%&%' OR mc.val_nom1 LIKE '%>%'  
      OR mc.val_nom1 LIKE '%<%' OR mc.val_nom1 LIKE '%;%' OR mc.val_nom1 LIKE '%,%' OR mc.val_nom1 LIKE '%+%'
      OR mc.val_nom1 LIKE '%{%' OR mc.val_nom1 LIKE '%}%' OR mc.val_nom1 LIKE '%--%' OR mc.val_nom1 LIKE '%+%';

  UPDATE mae_clien mc
     SET mc.val_nom2 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_nom2,'|',''),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'+',''),'{',''),'}',''),'--',''),'+','')
   WHERE mc.val_nom2 LIKE '%|%' OR mc.val_nom2 LIKE '%~%' OR mc.val_nom2 LIKE '%&%' OR mc.val_nom2 LIKE '%>%'  
      OR mc.val_nom2 LIKE '%<%' OR mc.val_nom2 LIKE '%;%' OR mc.val_nom2 LIKE '%,%' OR mc.val_nom2 LIKE '%+%'
      OR mc.val_nom2 LIKE '%{%' OR mc.val_nom2 LIKE '%}%' OR mc.val_nom2 LIKE '%--%' OR mc.val_nom2 LIKE '%+%';

  UPDATE mae_clien mc
     SET mc.val_ape1 = REPLACE(REPLACE(REPLACE (mc.val_ape1,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mc.val_ape1 LIKE '%'||CHR(9)||'%' OR mc.val_ape1 LIKE  '%'||CHR(10)||'%' OR mc.val_ape1 LIKE  '%'||CHR(31)||'%';

  UPDATE mae_clien mc
     SET mc.val_ape2 = REPLACE(REPLACE(REPLACE (mc.val_ape2,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mc.val_ape2 LIKE '%'||CHR(9)||'%' OR mc.val_ape2 LIKE  '%'||CHR(10)||'%' OR mc.val_ape2 LIKE  '%'||CHR(31)||'%';

  UPDATE mae_clien mc
     SET mc.val_nom1 = REPLACE(REPLACE(REPLACE (mc.val_nom1,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mc.val_nom1 LIKE '%'||CHR(9)||'%' OR mc.val_nom1 LIKE  '%'||CHR(10)||'%' OR mc.val_nom1 LIKE  '%'||CHR(31)||'%';

  UPDATE mae_clien mc
     SET mc.val_nom2 = REPLACE(REPLACE(REPLACE (mc.val_nom2,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mc.val_nom2 LIKE '%'||CHR(9)||'%' OR mc.val_nom2 LIKE  '%'||CHR(10)||'%' OR mc.val_nom2 LIKE  '%'||CHR(31)||'%';


  -- Elimina caracteres especiales de Numero Documento
  UPDATE mae_clien_ident mci
     SET mci.num_docu_iden = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mci.num_docu_iden,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),',','')
   WHERE mci.num_docu_iden LIKE '%///%' OR mci.num_docu_iden LIKE '%~%' OR mci.num_docu_iden LIKE '%&%'
      OR mci.num_docu_iden LIKE '%>%' OR mci.num_docu_iden LIKE '%<%' OR mci.num_docu_iden LIKE '%;%' OR mci.num_docu_iden LIKE '%,%';

  UPDATE mae_clien_ident mci
     SET mci.num_docu_iden = REPLACE(REPLACE(REPLACE (mci.num_docu_iden,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mci.num_docu_iden LIKE '%'||CHR(9)||'%' OR mci.num_docu_iden LIKE  '%'||CHR(10)||'%' OR mci.num_docu_iden LIKE  '%'||CHR(31)||'%';


  -- Elimina caracteres especiales de Codigo Postal
  UPDATE mae_clien_direc cdi
     SET cdi.val_cod_post = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (cdi.val_cod_post,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),',','')
   WHERE cdi.val_cod_post LIKE '%///%' OR cdi.val_cod_post LIKE '%~%' OR cdi.val_cod_post LIKE '%&%'
      OR cdi.val_cod_post LIKE '%>%' OR cdi.val_cod_post LIKE '%<%' OR cdi.val_cod_post LIKE '%;%' OR cdi.val_cod_post LIKE '%,%';

  UPDATE mae_clien_direc cdi
     SET cdi.val_cod_post = REPLACE(REPLACE(REPLACE (cdi.val_cod_post,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE cdi.val_cod_post LIKE '%'||CHR(9)||'%' OR cdi.val_cod_post LIKE  '%'||CHR(10)||'%' OR cdi.val_cod_post LIKE  '%'||CHR(31)||'%';


  -- Elimina caracteres especiales de Descripciones de Logros
  UPDATE nvs_consu_logro ncl
     SET ncl.des_larg = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (ncl.des_larg,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),',','')
   WHERE ncl.des_larg LIKE '%///%' OR ncl.des_larg LIKE '%~%' OR ncl.des_larg LIKE '%&%'
      OR ncl.des_larg LIKE '%>%' OR ncl.des_larg LIKE '%<%' OR ncl.des_larg LIKE '%;%' OR ncl.des_larg LIKE '%,%';

  UPDATE nvs_consu_logro ncl
     SET ncl.des_larg = REPLACE(REPLACE(REPLACE (ncl.des_larg,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE ncl.des_larg LIKE '%'||CHR(9)||'%' OR ncl.des_larg LIKE  '%'||CHR(10)||'%' OR ncl.des_larg LIKE  '%'||CHR(31)||'%';


  -- Elimina caracteres especiales de Conferencias con Direccion, Local
  UPDATE msg_mensa_confe mmc
     SET mmc.val_dire = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mmc.val_dire,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),'/','')
   WHERE mmc.val_dire LIKE '%///%' OR mmc.val_dire LIKE '%~%' OR mmc.val_dire LIKE '%&%'
      OR mmc.val_dire LIKE '%>%' OR mmc.val_dire LIKE '%<%' OR mmc.val_dire LIKE '%;%' OR mmc.val_dire LIKE '%/%';

  UPDATE msg_mensa_confe mmc
     SET mmc.val_dire = REPLACE(REPLACE(REPLACE (mmc.val_dire,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mmc.val_dire LIKE '%'||CHR(9)||'%' OR mmc.val_dire LIKE '%'||CHR(10)||'%' OR mmc.val_dire LIKE '%'||CHR(31)||'%';

  UPDATE msg_mensa_confe mmc
     SET mmc.val_loca = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mmc.val_loca,'///',''),'~',''),'&',''),'>',''),'<',''),';',''),'/','')
   WHERE mmc.val_loca LIKE '%///%' OR mmc.val_loca LIKE '%~%' OR mmc.val_loca LIKE '%&%'
      OR mmc.val_loca LIKE '%>%' OR mmc.val_loca LIKE '%<%' OR mmc.val_loca LIKE '%;%' OR mmc.val_loca LIKE '%/%';

  UPDATE msg_mensa_confe mmc
     SET mmc.val_loca = REPLACE(REPLACE(REPLACE (mmc.val_loca,CHR(9),' '),CHR(10),' '),CHR(31),' ')
   WHERE mmc.val_loca LIKE '%'||CHR(9)||'%' OR mmc.val_loca LIKE '%'||CHR(10)||'%' OR mmc.val_loca LIKE '%'||CHR(31)||'%';


  -- Reemplaza caracteres especiales de Direccion --
  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'À','A'),'È','E'),'Ì','I'),'Ò','O'),'Ù','U'),'ã','O'),'½','.5'),'ü','U')
   WHERE mcd.val_obse LIKE '%À%' OR mcd.val_obse LIKE '%È%' OR mcd.val_obse LIKE '%Ì%'
      OR mcd.val_obse LIKE '%Ò%' OR mcd.val_obse LIKE '%Ù%' OR mcd.val_obse LIKE '%ã%'
      OR mcd.val_obse LIKE '%½%' OR mcd.val_obse LIKE '%ü%';

  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_obse,'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U'),'ª','º'),'"',' '),'Ð','Ñ'),'Ë','O')
   WHERE mcd.val_obse LIKE '%Á%' OR mcd.val_obse LIKE '%É%' OR mcd.val_obse LIKE '%Í%'
      OR mcd.val_obse LIKE '%Ó%' OR mcd.val_obse LIKE '%Ú%' OR mcd.val_obse LIKE '%ª%'
      OR mcd.val_obse LIKE '%"%' OR mcd.val_obse LIKE '%Ð%' OR mcd.val_obse LIKE '%Ë%';

  UPDATE mae_clien_direc mcd
        SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'À','A'),'È','E'),'Ì','I'),'Ò','O'),'Ù','U'),'ã','O')
    WHERE mcd.val_nomb_via LIKE '%À%' OR mcd.val_nomb_via LIKE '%È%' OR mcd.val_nomb_via LIKE '%Ì%'
      OR mcd.val_nomb_via LIKE '%Ò%' OR mcd.val_nomb_via LIKE '%Ù%' OR mcd.val_nomb_via LIKE '%ã%' ;

  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mcd.val_nomb_via,'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U'),'ª','º'),'"',' '),'Ð','Ñ'),'Ë','O')
   WHERE mcd.val_nomb_via LIKE '%Á%' OR mcd.val_nomb_via LIKE '%É%' OR mcd.val_nomb_via LIKE '%Í%'
      OR mcd.val_nomb_via LIKE '%Ó%' OR mcd.val_nomb_via LIKE '%Ú%' OR mcd.val_nomb_via LIKE '%ª%'
      OR mcd.val_nomb_via LIKE '%"%' OR mcd.val_nomb_via LIKE '%Ð%' OR mcd.val_obse LIKE '%Ë%';


  -- Reemplaza caracteres especiales de Nombres y Apellidos --
  UPDATE mae_clien mc
     SET mc.val_ape1 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_ape1,'¥','Ñ'),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'-',' '),'Ü','U'),'Ì','I'),'-',' '),'-',' ')
   WHERE mc.val_ape1 LIKE '%¥%' OR mc.val_ape1 LIKE '%~%' OR mc.val_ape1 LIKE '%&%' OR mc.val_ape1 LIKE '%>%' 
      OR mc.val_ape1 LIKE '%<%' OR mc.val_ape1 LIKE '%;%' OR mc.val_ape1 LIKE '%,%' OR mc.val_ape1 LIKE '%-%' 
      OR mc.val_ape1 LIKE '%Ü%' OR mc.val_ape1 LIKE '%Ì%' OR mc.val_ape1 LIKE '%-%' OR mc.val_ape1 LIKE '%-%';

  UPDATE mae_clien mc
     SET mc.val_ape2 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_ape2,'¥','Ñ'),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'-',' '),'Ü','U'),'Ì','I'),'-',' '),'-',' ')
   WHERE mc.val_ape2 LIKE '%¥%' OR mc.val_ape2 LIKE '%~%' OR mc.val_ape2 LIKE '%&%' OR mc.val_ape2 LIKE '%>%' 
      OR mc.val_ape2 LIKE '%<%' OR mc.val_ape2 LIKE '%;%' OR mc.val_ape2 LIKE '%,%' OR mc.val_ape2 LIKE '%-%' 
      OR mc.val_ape2 LIKE '%Ü%' OR mc.val_ape2 LIKE '%Ì%' OR mc.val_ape2 LIKE '%-%' OR mc.val_ape2 LIKE '%-%';

  UPDATE mae_clien mc
     SET mc.val_nom1 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_nom1,'¥','Ñ'),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'-',' '),'Ü','U'),'Ì','I'),'-',' '),'-',' ')
   WHERE mc.val_nom1 LIKE '%¥%' OR mc.val_nom1 LIKE '%~%' OR mc.val_nom1 LIKE '%&%' OR mc.val_nom1 LIKE '%>%' 
      OR mc.val_nom1 LIKE '%<%' OR mc.val_nom1 LIKE '%;%' OR mc.val_nom1 LIKE '%,%' OR mc.val_nom1 LIKE '%-%' 
      OR mc.val_nom1 LIKE '%Ü%' OR mc.val_nom1 LIKE '%Ì%' OR mc.val_nom1 LIKE '%-%' OR mc.val_nom1 LIKE '%-%';

  UPDATE mae_clien mc
     SET mc.val_nom2 = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE (mc.val_nom2,'¥','Ñ'),'~',''),'&',''),'>',''),'<',''),';',''),',',''),'-',' '),'Ü','U'),'Ì','I'),'-',' '),'-',' ')
   WHERE mc.val_nom2 LIKE '%¥%' OR mc.val_nom2 LIKE '%~%' OR mc.val_nom2 LIKE '%&%' OR mc.val_nom2 LIKE '%>%' 
      OR mc.val_nom2 LIKE '%<%' OR mc.val_nom2 LIKE '%;%' OR mc.val_nom2 LIKE '%,%' OR mc.val_nom2 LIKE '%-%' 
      OR mc.val_nom2 LIKE '%Ü%' OR mc.val_nom2 LIKE '%Ì%' OR mc.val_nom2 LIKE '%-%' OR mc.val_nom2 LIKE '%-%';


  -- Actualiza a Mayusculas la Direccion --
  UPDATE mae_clien_direc mcd
     SET mcd.val_obse = UPPER(TRIM(mcd.val_obse))
   WHERE mcd.OID_CLIE_DIRE in ( SELECT OID_CLIE_DIRE
                                  FROM MAE_CLIEN_DIREC
                                 WHERE TRANSLATE ( REPLACE(val_nomb_via,' ',NULL) 
                                                   , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'
                                                   , '?'
                                                 ) IS NOT NULL
                                    OR TRANSLATE ( REPLACE(val_obse,' ',NULL) 
                                                   , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'  
                                                   , '?'
                                                 ) IS NOT NULL   
                              );

  UPDATE mae_clien_direc mcd
     SET mcd.val_nomb_via = UPPER(TRIM(mcd.val_nomb_via))
   WHERE mcd.OID_CLIE_DIRE in ( SELECT OID_CLIE_DIRE
                                  FROM MAE_CLIEN_DIREC
                                 WHERE TRANSLATE ( REPLACE(val_nomb_via,' ',NULL) 
                                                   , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'
                                                   , '?'
                                                 ) IS NOT NULL
                                    OR TRANSLATE ( REPLACE(val_obse,' ',NULL) 
                                                   , '?0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz./,-():#º°_áéíóúÁÉÍÓÚ'  
                                                   , '?'
                                                 ) IS NOT NULL   
                              );

  -- Actualiza a Mayusculas los Nombres y Apellidos--
   UPDATE mae_clien x 
      SET x.val_nom1 = UPPER(x.val_nom1), 
          x.val_nom2 = UPPER(x.val_nom2), 
          x.val_ape1 = UPPER(x.val_ape1), 
          x.val_ape2 = UPPER(x.val_ape2), 
          x.val_apel_casa = UPPER(x.val_apel_casa), 
          x.val_crit_bus1 = UPPER(x.val_crit_bus1), 
          x.val_crit_bus2 = UPPER(x.val_crit_bus2)
    WHERE x.oid_clie in (
                         SELECT oid_clie
                           FROM MAE_CLIEN 
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
                                          ) IS NOT NULL
                        );


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ELIMI_CARAC_ESPEC: '||ls_sqlerrm);
END MSG_PR_ELIMI_CARAC_ESPEC;



/***************************************************************************
Descripcion : Proceso que genera mensajes Reparto catálogo LBEL
Fecha Creacion : 26/10/2015
Autor : CSVD - FFVV - Juan Gutiérrez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de Periodo
 psFechaFacturacion : Fecha Facturacion
 psTipoProceso : Tipo Proceso
 psCondigoIdent : Codigo de Identificacion proceso
 psCondigoPlantilla : Codigo de Plantilla
***************************************************************************/
PROCEDURE MSG_PR_GENER_MENSA_REPAR_CATAL
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psFechaFacturacion   VARCHAR2,
  psTipoProceso        VARCHAR2,
  psCondigoIdent       VARCHAR2,
  psCodigoPlantilla    VARCHAR2
 )IS

 lnMarca               NUMBER;
 lnCanal               NUMBER;
 lsCodMensaje          msg_mensa.cod_mens%TYPE;
 lsCodModulo           seg_modul.cod_modu%TYPE;
 lsCodTipoSoli         ped_tipo_solic.cod_tipo_soli%TYPE;
 lnPais                NUMBER;

 lnOidModuloOrigen     NUMBER;
 lnOidMensajeRepLbel   NUMBER;
 lnOidMensaje          NUMBER;
 lnOidPeriodo          NUMBER;
 lnOidPeriodoDemanda   NUMBER;
 lnNroCampa            NUMBER;

 BEGIN
   lnMarca := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_marca, tipo_retorno_oid);
   lnCanal := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_canal, tipo_retorno_oid);
   --lsCodMensaje := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_mensaje,tipo_retorno_codigo);
   lsCodModulo := MSG_FN_OBTEN_DATO(pscodigoplantilla,pscondigoident,tipo_dato_modulo_origen,tipo_retorno_codigo);
   lsCodTipoSoli := MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, tipo_dato_tipo_solicitud,tipo_retorno_codigo);

   lnPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
   lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnMarca, lnCanal);
   lnNroCampa:= to_number(MSG_FN_OBTEN_DATO(psCodigoPlantilla, psCondigoIdent, TIPO_DATO_CANTIDAD_CAMPANA, tipo_retorno_oid));


   SELECT oid_modu
     INTO lnOidModuloOrigen
     FROM seg_modul
    WHERE cod_modu = lsCodModulo;

   lnOidPeriodoDemanda:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( per_pkg_repor_perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,psCodigoPais,lnNroCampa),lnMarca, lnCanal);

   --Mensaje REPARTO CATALOGO LBEL ( REPLB )--
   BEGIN
        SELECT msg.mens_oid_mens
          INTO lnOidMensajeRepLbel
          FROM msg_mensa_campa msg
         WHERE msg.cod_mens = FAC_REPAR_CATAL_LBEL
           AND msg.cam_proc = psCodigoPeriodo
           AND msg.est_regi <> '9';
      EXCEPTION
         WHEN OTHERS THEN
            lnOidMensajeRepLbel := 0;
      END;


          INSERT INTO MSG_BUZON_MENSA
            (
             oid_buzo_mens,
             num_secu ,
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
             num_lote_impr, fec_grab, fec_impr, ind_list_cons, peri_oid_peri, ind_acti
            )
            (
             SELECT MSG_BUME_SEQ.NEXTVAL,
                    MSG_BUM2_SEQ.NEXTVAL,
                    NULL,
                    base.oid_clie,
                    lnOidMensajeRepLbel,
                    lnOidModuloOrigen,
                    base.nom1,
                    base.nom2,
                    base.ape1,
                    base.ape2,
                    base.apec,
                    base.cod_clie, --'DV01'
                    base.val_mont_aten_lbel, --'DV02'
                    base.VAL_DEMA_LBEL, --'DV03'
                    NULL, SYSDATE, NULL, 1, NULL, 1
               FROM (
                     SELECT DISTINCT MC.OID_CLIE,
                            mc.val_nom1 AS nom1,
                            mc.val_nom2 AS nom2,
                            mc.val_ape1 AS ape1,
                            mc.val_ape2 AS ape2,
                            mc.val_apel_casa AS apec,
                            mc.val_nom1 ||' '||mc.val_nom2||' '||mc.val_ape1||' '||mc.val_ape2 AS nombre,
                            mc.cod_clie,
                            psca.val_mont_aten_lbel,
                            (SELECT  SUM(VAL_MONT_TOTA_LBEL) FROM ped_solic_cabec_acum2
                             WHERE clie_oid_clie = oid_clie
                             AND PERD_OID_PERI>=lnOidPeriodoDemanda ) AS VAL_DEMA_LBEL

                       FROM msg_tmp_pedid_clien pedc,
                            mae_clien mc,
                            ped_solic_cabec_acum2 psca
                      WHERE psca.val_mont_aten_lbel = lnOidPeriodo
                        AND psca.clie_oid_clie = mc.oid_clie
                         AND pedc.cod_tipo_soli = lsCodTipoSoli
                        AND pedc.clie_oid_clie = mc.oid_clie
                    ) base
             );


  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GENER_MENSA_REPAR_CATAL: '||ls_sqlerrm);
END MSG_PR_GENER_MENSA_REPAR_CATAL;

/***************************************************************************
Descripcion : Proceso que graba imagen en los mensajes
Fecha Creacion : 28/12/2015
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_GRABA_IMAGE_BLOB
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psCodigoMensaje      VARCHAR2,
  psCodigoUsuario      VARCHAR2,
  psArchivoImagen      VARCHAR2,
  psIndicadorImgFondo  VARCHAR2
 )IS
  l_blob               BLOB;
  l_blobTempo          BLOB;
  l_raw                RAW(32767);
  l_amount             BINARY_INTEGER := 32767;
  l_pos                INTEGER := 1;
  l_file               UTL_FILE.FILE_TYPE;
  lnContador           NUMBER(3);
  lsdirtempo           VARCHAR2(400);

  CURSOR cimagen IS
  SELECT val_imag
  FROM MSG_GTT_MENSA_CAMPA_IMAGE;

BEGIN
    DELETE FROM MSG_GTT_MENSA_CAMPA_IMAGE;

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    SELECT x.val_para
    INTO lsdirtempo
    FROM bas_param_pais x
    WHERE x.cod_pais = pscodigopais
      AND x.cod_sist = 'MSG'
      AND x.nom_para = 'valRutaImg';

    -- Creando directorio a crear para el UTL_FILE
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

     --Abrir archivo
    l_file := UTL_FILE.fopen(lsdirtempo, psArchivoImagen,'rb', 32767);
    IF utl_file.is_open(l_file) THEN
      LOOP
          BEGIN
             utl_file.GET_RAW (l_file, l_raw);
          EXCEPTION
          WHEN no_data_found THEN
            EXIT;
          END;
          IF l_raw IS NULL THEN
            EXIT;
          END IF;
          INSERT INTO MSG_GTT_MENSA_CAMPA_IMAGE(VAL_IMAG)
          VALUES (l_raw);

      END LOOP;

      lncontador := 0;
      FOR c1 IN cimagen LOOP
          IF (lnContador = 0) THEN
             l_blob := c1.val_imag;
          ELSE
             l_blobTempo := c1.val_imag;
             DBMS_LOB.append(l_blob, l_blobTempo);
          END IF;
          lnContador := lnContador + 1;
      END LOOP;

    END IF;
    utl_file.fclose(l_file);

    -- Grabando informacion
    SELECT COUNT(1)
    INTO lnContador
    FROM MSG_MENSA_CAMPA_IMAGE
    WHERE pais_cod_pais = pscodigopais
      AND meca_cam_proc = psCodigoPeriodo
      AND meca_cod_mens = psCodigoMensaje
      AND nom_imag = psArchivoImagen;

    IF lnContador = 0 THEN
      INSERT INTO MSG_MENSA_CAMPA_IMAGE
      ( pais_cod_pais,
        meca_cam_proc,
        meca_cod_mens,
        nom_imag,
        val_imag,
        ind_acti,
        ind_imag_fond,
        usu_crea,
        fec_crea
      )
      VALUES ( pscodigopais,
               psCodigoPeriodo,
               psCodigoMensaje,
               psArchivoImagen,
               l_blob,
               '1',
               psIndicadorImgFondo,
               psCodigoUsuario,
               SYSDATE );
    ELSE
      UPDATE MSG_MENSA_CAMPA_IMAGE
      SET val_imag = l_blob,
          ind_acti = '1',
          ind_imag_fond = psIndicadorImgFondo,
          usu_modi = psCodigoUsuario,
          fec_modi = SYSDATE
      WHERE pais_cod_pais = pscodigopais
        AND meca_cam_proc = psCodigoPeriodo
        AND meca_cod_mens = psCodigoMensaje
        AND nom_imag = psArchivoImagen;
    END IF;

 EXCEPTION
  WHEN OTHERS THEN
   utl_file.fclose_all;
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GRABA_IMAGE_BLOB: '||ls_sqlerrm);
END MSG_PR_GRABA_IMAGE_BLOB;

/***************************************************************************
Descripcion : Proceso que LEE imagen en los mensajes
Fecha Creacion : 28/12/2015
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_LEER_IMAGE_BLOB
 (
  psCodigoPais         VARCHAR2,
  psCodigoPeriodo      VARCHAR2,
  psCodigoMensaje      VARCHAR2
 )IS

  lsdirtempo           VARCHAR2(400);
  l_file               UTL_FILE.FILE_TYPE;
  l_buffer             RAW(32767);
  l_amount             BINARY_INTEGER := 32767;
  l_pos                INTEGER := 1;
  l_blob               BLOB;
  l_blob_len           INTEGER;
  lbEncontro           BOOLEAN;
  CURSOR cimagen IS
    SELECT nom_imag, val_imag
    FROM MSG_MENSA_CAMPA_IMAGE
    WHERE pais_cod_pais = pscodigopais
      AND meca_cam_proc = psCodigoPeriodo
      AND meca_cod_mens = psCodigoMensaje
      AND ind_acti = '1';
BEGIN
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    SELECT x.val_para
    INTO lsdirtempo
    FROM bas_param_pais x
    WHERE x.cod_pais = pscodigopais
      AND x.cod_sist = 'MSG'
      AND x.nom_para = 'valRutaImg';

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    FOR c1 IN cimagen LOOP
      l_pos := 1;
      l_blob := c1.val_imag;
      l_blob_len := DBMS_LOB.getlength(l_blob);

      BEGIN
        utl_file.fremove('SICC_DIR', c1.nom_imag);
      EXCEPTION
      WHEN OTHERS THEN
         lbEncontro := FALSE;
      END;

      -- Open the destination file.
      l_file := utl_file.fopen('SICC_DIR', c1.nom_imag, 'wb', 32767);

      -- Read chunks of the BLOB and write them to the file
      -- until complete.
      WHILE l_pos < l_blob_len LOOP
        DBMS_LOB.read(l_blob, l_amount, l_pos, l_buffer);
        UTL_FILE.put_raw(l_file, l_buffer, TRUE);
        l_pos := l_pos + l_amount;
      END LOOP;


      -- Close the file.
      UTL_FILE.fclose(l_file);
   END LOOP;

 EXCEPTION
  WHEN OTHERS THEN
   utl_file.fclose_all;
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_LEER_IMAGE_BLOB: '||ls_sqlerrm);
END MSG_PR_LEER_IMAGE_BLOB;

/***************************************************************************
Descripcion : Funcion que retorna el valor correspondiente al indicador de Edicion
para el tema de Replica de Patron
Fecha Creacion : 04/01/2016
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION MSG_FN_DEVUE_INDIC_EDITA(
   psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   pnOidMensaje NUMBER,
   pnOidTipoMensaje NUMBER)
RETURN NUMBER
IS
   lsResultado NUMBER(1);
   lnContador  NUMBER;
BEGIN
   lsResultado := 1;
   IF pnOidTipoMensaje = 0 THEN
      RETURN lsResultado;
   END IF;

   BEGIN
      SELECT COUNT(1)
      INTO lnContador
      FROM MSG_CORES_MENSA_CABEC A,
           MSG_MENSA_CAMPA B
      WHERE A.PAIS_COD_PAIS = psCodigoPais
        AND A.MECA_CAM_PROC = B.CAM_PROC
        AND A.MECA_COD_MENS = B.COD_MENS
        AND B.CAM_PROC = psCodigoPeriodo
        AND B.mens_oid_mens = pnOidMensaje
        AND A.CORE_COD_CONS_REST = 2010;

      IF lnContador = 0 THEN
         RETURN lsResultado;
      END IF;
      lsResultado := 0;
   EXCEPTION
   WHEN NO_DATA_FOUND THEN
        RETURN lsResultado;
   END;

   RETURN lsResultado;
END MSG_FN_DEVUE_INDIC_EDITA;

/***************************************************************************
Descripcion : Graba Flyer en las tablas de Patron
Fecha Creacion : 21/01/2016
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE MSG_PR_GRABA_FLYER
 (pnOidPatron             VARCHAR2,
  psCodigoPais            VARCHAR2,
  psCampanhaProceso       VARCHAR2,
  psOidPeriodoCorpo       VARCHAR2,
  psCodigoDocumento       VARCHAR2,
  psCodigoBandeja         VARCHAR2,
  psDescripcionPatron     VARCHAR2,
  psCodigoUsuario         VARCHAR2
 )
IS
  lnOidPatron       NUMBER;
  lnOidPatronSecci  NUMBER;
  lnpais            NUMBER;
  lsCodigoPatron    MSG_PATRO.cod_patr%TYPE;
  lsMaximoPatron    MSG_PATRO.cod_patr%TYPE;
  lsContadorPatron  MSG_PATRO.cod_patr%TYPE;
  lnContadorPatron  NUMBER;
BEGIN
  lnpais:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  IF pnOidPatron IS NULL THEN
  
      SELECT MSG_PATR_SEQ.NEXTVAL into lnOidPatron from dual;
      SELECT MSG_PASE_SEQ.NEXTVAL into lnOidPatronSecci from dual;
      
      SELECT NVL((SELECT lower(SUBSTR(ABR_DOCU,1,4)) FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')
                      || substr(psCampanhaProceso,5,2)
                      || substr(psCampanhaProceso,1,4)
                      || NVL((SELECT lower(SUBSTR(ABR_DOCU,5)) FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')
      INTO  lsCodigoPatron
      FROM dual;
      
      select MAX(X.COD_PATR)
      INTO lsMaximoPatron
      FROM MSG_PATRO x,
           MSG_PATRO_PERIO y
      where x.oid_patr = y.PATR_OID_PATR
        AND X.PAIS_OID_PAIS = lnpais
        AND y.PERI_OID_PERI = psOidPeriodoCorpo
			  AND x.cod_patr LIKE 'fl%';
      
      IF lsMaximoPatron IS NOT NULL THEN
         lsContadorPatron := rtrim(ltrim(substr(lsMaximoPatron, 9)));
         lnContadorPatron := to_number(lsContadorPatron) + 1;
         lsContadorPatron := rtrim(ltrim(to_char(lnContadorPatron)));
      ELSE
         lsContadorPatron := '1';
      END IF;
      lsCodigoPatron :=  lsCodigoPatron || lsContadorPatron;
      
      /* Insertando valores */
      INSERT INTO MSG_PATRO (
                OID_PATR,
                COD_PATR,
                IND_ACTI,
                MEEP_OID_MEDI_ENVI_PAIS,
                FORS_OID_FORM,
                DES_PATR,
                IND_PATR_PERI,
                PAIS_OID_PAIS,
                MDOC_COD_MENS_DOCU,
                NOM_BAND_FLYE,
                EST_REGI,
                USU_MODI,
                FEC_MODI)
       VALUES ( lnOidPatron,
                (LOWER(lsCodigoPatron)),
                '1',
                (NVL((SELECT MEEP_OID_MEDI_ENVI_PAIS FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')),
                (NVL((SELECT FORS_OID_FORM FROM MSG_DOCUM WHERE COD_MENS_DOCU= psCodigoDocumento),'')),
                psDescripcionPatron,
                1,
                lnpais,
                psCodigoDocumento,
                psCodigoBandeja,
                '1',
                psCodigoUsuario,
                SYSDATE);
       
       INSERT INTO MSG_PATRO_PERIO (
               OID_PATR_PERI,
               PATR_OID_PATR_ORIG,
               PATR_OID_PATR,
               PERI_OID_PERI,
               IND_ACTI,
               USU_MODI,
               FEC_MODI,
               EST_REGI)
           VALUES ( MSG_PAPE_SEQ.nextval,
            lnOidPatron,--null,
            lnOidPatron,
            psOidPeriodoCorpo,
            '1',
            psCodigoUsuario,
            SYSDATE,
            '1');
  ELSE
     /* Actualizado valores */
     UPDATE MSG_PATRO
     SET 
        MDOC_COD_MENS_DOCU = psCodigoDocumento,
        NOM_BAND_FLYE = psCodigoBandeja,
        DES_PATR = psDescripcionPatron
     WHERE
        OID_PATR = pnOidPatron;
  
  END IF;
  
EXCEPTION
WHEN OTHERS THEN
   utl_file.fclose_all;
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_GRABA_FLYER: '||ls_sqlerrm);
END MSG_PR_GRABA_FLYER;

END MSG_PKG_PROCE_MENSA;
/
