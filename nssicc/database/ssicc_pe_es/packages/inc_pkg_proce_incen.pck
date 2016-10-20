CREATE OR REPLACE PACKAGE INC_PKG_PROCE_INCEN IS
  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  /* Definicion de Constantes */
  PROCESO_FACTURACION    CONSTANT VARCHAR2(1) := 'F';
  PROCESO_INICIO_CAMPANA CONSTANT VARCHAR2(1) := 'P';
  MODULO_INCENTIVOS      CONSTANT NUMBER := 13;

  TYPE t_lineaOblig IS TABLE OF VARCHAR2(100);
  TYPE t_totalOblig IS TABLE OF NUMBER;

  /***************************************************************************
  Descripcion : Proceso que carga los puntajes en cuenta corriente
  Fecha Creacion : 01/06/2009
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psNumLote : Numero Lote
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_CUENT_CORRI_PUNTA(psCodigoPais VARCHAR2,
                                           psNumLote    VARCHAR2);

  /***************************************************************************
  Descripcion :
       Ejecuta Validaciones de premios electivos para la consultora,
   si es correcta las validacion retorna
       los datos de la consultora que se mostararn en patalla asi mismo consolida
       la lista de elegidos y seleccionables guaradndolos en un temporal
  Fecha Creacion : 15/07/2009
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais Codigo Pais
   oidConcurso OidConcurso
   codigoConsultora Consultora
   psTipo Tipo validacion 0:seleccion 1:digitacion
   mensajeError Mensaje error
   mensajeResultado Mensaje Respuesta con datos concatenados separados por ','
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PREMI_ELECT(psCodigoPais       VARCHAR2,
                                     psoidConcurso      VARCHAR2,
                                     pscodigoConsultora VARCHAR2,
                                     psTipo             VARCHAR2,
                                     psmensajeError     OUT VARCHAR2,
                                     psmensajeResultado OUT VARCHAR2);

  /**************************************************************************
  Descripcion : Valida las clasificaciones del cliente en las clasificaciones del concurso
  Fecha Creacion : 16/07/2009
  Parametros Entrada :
  psOidConcurso : oidConcurso
  psOidCliente :oidCliente
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CLASI_CONCU(psOidConcurso NUMBER,
                                    psOidCliente  NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Valida el Ambito Geografico del cliente en el ambito geografico del concurso,se ingrsa a esta validacion
  Siempre y cuando el concurso tenga Ambito
  Fecha Creacion : 09/11/2009
  Parametros Entrada :
  psOidConcurso : oidConcurso
  psOidCliente :oidCliente
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_CONCU(psOidConcurso NUMBER,
                                          psOidCliente  NUMBER)
    RETURN VARCHAR2;

  /**********************************************************************************
  Descripcion : Genera la data para el Reporte de Proyecciones de Permisos de Concurso
  
  Fecha Creacion : 01/12/2009
  Parametros Entrada :
  psNumeroNivel       : Numero de Niveles
  psPuntajeNivelDesde : Puntaje de Nivel Desde
  psPuntajeNivelHasta : Puntaje de Nivel Hasta
  Autor : Jesse Rios
  ************************************************************************************/
  PROCEDURE INC_PR_REPOR_PROYE_PREMI_CONCU(psNumeroConcurso VARCHAR2,
                                           psCodigoPais     VARCHAR2);

  /* Procesos de Incentivos */
  PROCESO_GP4            CONSTANT VARCHAR2(3) := 'G';
  PROCESO_CIERRE_ZONA    CONSTANT VARCHAR2(3) := 'Z';
  PROCESO_CIERRE_REGION  CONSTANT VARCHAR2(3) := 'R';
  PROCESO_CIERRE_CAMPANA CONSTANT VARCHAR2(3) := 'P';
  PROCESO_CIERRE_DIARIO  CONSTANT VARCHAR2(3) := 'D';

  TYPE tRegSolicitud IS RECORD(
    COD_PAIS                       SEG_PAIS.COD_PAIS%TYPE,
    COD_MARC                       SEG_MARCA.COD_MARC%TYPE,
    COD_CANA                       SEG_CANAL.COD_CANA%TYPE,
    OID_CLIE                       MAE_CLIEN.OID_CLIE%TYPE,
    COD_PERI                       SEG_PERIO_CORPO.COD_PERI%TYPE,
    FEC_PROG_FACT                  DATE,
    COD_OPER                       BEL_OPERA.COD_OPER%TYPE,
    COD_CLAS_SOLI                  PED_CLASE_SOLIC.COD_CLAS_SOLI%TYPE,
    COD_TIPO_SOLI                  PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE,
    COD_TIPO_CLIE                  MAE_SUBTI_CLIEN.COD_SUBT_CLIE%TYPE,
    OID_PARA_GRAL                  INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
    NUM_PREM                       PED_SOLIC_CABEC.NUM_PREM%TYPE,
    COD_USUA                       VARCHAR2(20),
    OID_ACCE_FISI                  PED_SOLIC_CABEC.ACFI_OID_ACCE_FISI%TYPE,
    OID_GRUP_PROC                  PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC%TYPE,
    OID_GRUP_PROC_SECU             PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC%TYPE, --PARA DETERMINAR EL OID SECUENCIA PROCESO
    OID_MODU                       PED_SOLIC_CABEC.MODU_OID_MODU%TYPE,
    NUM_CLIEN                      PED_SOLIC_CABEC.NUM_CLIEN%TYPE,
    VAL_GLOS_OBSE                  PED_SOLIC_CABEC.VAL_GLOS_OBSE%TYPE,
    VAL_BASE_FLET_LOCA             PED_SOLIC_CABEC.VAL_BASE_FLET_LOCA%TYPE,
    VAL_TOTA_PAGA_LOCA             PED_SOLIC_CABEC.VAL_TOTA_PAGA_LOCA%TYPE,
    VAL_PREC_CATA_TOTA_LOCA        PED_SOLIC_CABEC.VAL_PREC_CATA_TOTA_LOCA%TYPE,
    VAL_BASE_FLET_DOCU             PED_SOLIC_CABEC.VAL_BASE_FLET_DOCU%TYPE,
    VAL_PREC_CATA_TOTA_LOC_UNI_DEM PED_SOLIC_CABEC.VAL_PREC_CATA_TOTA_LOC_UNI_DEM%TYPE,
    VAL_UNID_DEMA_REAL_TOTA        PED_SOLIC_CABEC.VAL_UNID_DEMA_REAL_TOTA%TYPE,
    NUM_UNID_POR_ATEN_TOTA         PED_SOLIC_CABEC.NUM_UNID_POR_ATEN_TOTA%TYPE,
    VAL_PREC_CONT_TOTA_LOCA        PED_SOLIC_CABEC.VAL_PREC_CONT_TOTA_LOCA%TYPE,
    OID_SOLI_CABE_GENE             PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE);

  TYPE tRegPosicion IS RECORD(
    OID_PROD                MAE_PRODU.OID_PROD%TYPE,
    NUM_UNID                PED_SOLIC_POSIC.NUM_UNID_DEMA%TYPE,
    VAL_CODI_VENT           PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE,
    VAL_CODI_VENT_FICT      PED_SOLIC_POSIC.VAL_CODI_VENT_FICT%TYPE,
    NUM_UNID_COMPR          PED_SOLIC_POSIC.NUM_UNID_COMPR%TYPE,
    OID_DETA_OFER           PED_SOLIC_POSIC.OFDE_OID_DETA_OFER%TYPE,
    VAL_PREC_CATA_UNIT_LOCA PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA%TYPE,
    VAL_PREC_CONT_UNIT_LOCA PED_SOLIC_POSIC.VAL_PREC_CONT_UNIT_LOCA%TYPE,
    VAL_PREC_CATA_UNIT_DOCU PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_DOCU%TYPE,
    OID_SOLI_POSI_GENE      PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE);

  TYPE tablaRegPosicion IS TABLE OF tRegPosicion;

  /**************************************************************************
  Descripcion       : Retener Solicitudes de Premiacion de Niveles Electivos
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         GP4: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_RETEN_SOLIC_PREMI_ELECT(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZona       VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Despachar Premios de Solicitudes Retenidas
  Fecha Creacion    : 06/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         GP4: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_DESPA_PREMI_SOLIC_RETEN(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZonas      VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Genera la Solicitud de Pedido
  Fecha Creacion    : 06/08/2010
  Parametros Entrada:
    ptSolicitud     :  Registro con Datos para la Solicitud de pais
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         GP4: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
    psCodigoUsuario     : Usuario que ejecuta el Proceso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_PEDID(ptSolicitud        IN OUT tRegSolicitud,
                                     psIndicadorProceso VARCHAR2,
                                     ptRegPosicion      IN OUT tablaRegPosicion);

  /**************************************************************************
  Descripcion        : Recupera el Codigo Zona de un determinado Cliente
  Fecha Creacion     : 06/08/2010
  Parametros Entrada :
             pnOidCliente : Oid Cliente
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_DEVUE_CODIG_ZONA(pnOidCliente NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Valida Si el Cliente esta pasando un pedido O/C
                       para un determinado periodo
  Fecha Creacion     : 06/08/2010
  Parametros Entrada :
             pnOidPais : Oid Pais
             pnOidPeriodo : Oid Periodo
             pnOidCliente : Oid Cliente
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_PEDID(pnOidPais    NUMBER,
                              pnOidPeriodo NUMBER,
                              pnOidCliente NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Obtiene el Oid Periodo Inicial para un pais determinado
  Fecha Creacion     : 09/08/2010
  Parametros Entrada :
             pnOidPais : Oid Pais
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_PERIO_INICI(psCodigoPais  VARCHAR2,
                                    psCodigoMarca VARCHAR2,
                                    psCodigoCanal VARCHAR2) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Calcula Ranking para los concursos de Reconocimiento.
  Fecha Creacion    : 26/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_RANKI_CONCU_RECON(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZona       VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 13/10/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_REEMP(psOidReemArtiLote NUMBER,
                                          psOidCliente      NUMBER)
    RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve 1 si el reemplazo es para el ámbito
                       geográfico de la consultora
  Fecha Creacion     : 11/02/2013
  Parametros Entrada :
             psOidReemArtiLote : Oid premio reemplazo
             psOidCliente      : Oid cliente
  Autor              : Hernán Ramos
  ***************************************************************************/

  PROCEDURE INC_PR_CARGA_ORDEN_RETAI(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Anula la Carda de Ordenes Retail por Campaña
  Fecha Creacion    : 15/10/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidControlCarga : Oid Control Carga ECM
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ANULA_CARGA_ORDEN_RETAI(psCodigoPais      VARCHAR2,
                                           pnOidControlCarga NUMBER,
                                           psCodigoUsuario   VARCHAR2);

  /**************************************************************************
  Descripcion       : Permite dar por atendido a premios de bolsa de faltantes
                      para que no puedan atenderse en el futuro
  Fecha Creacion    : 02/11/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psIndConsultoras    :  Codigo de Marca
    psNumeroLote    :  Numero Lote
    psOidConcurso  :  Oid Concurso
    psOidPeriodo  :  Oid Periodo
    psOidProducto  :  Oid Periodo
    psObservaciones  :  Observaciones
    psCodigoUsuario  :  Codigo Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_BOLSA_FALTA(psCodigoPais     VARCHAR2,
                                     psIndConsultoras VARCHAR2,
                                     psNumeroLote     VARCHAR2,
                                     psOidConcurso    VARCHAR2,
                                     psOidPeriodo     VARCHAR2,
                                     psOidProducto    VARCHAR2,
                                     psObservaciones  VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /**************************************************************************
  Descripcion       : Regulariza el registro de recomendaciones en tablas de incentivos
  Fecha Creacion    : 01/02/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Marca
    psIndicadorProceso : F: Facturacion,
                         P: Inicio de Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_REGIS_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psUsuario          VARCHAR2,
                                     psServer           VARCHAR2);

  /**************************************************************************
  Descripcion        : Valida si la consultora realizo pedido
  Fecha Creacion     :  02/02/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             pnOidPeriodo : oid Periodo
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VERIF_PEDID_CLIEN(pnOidCliente NUMBER,
                                    pnOidPeriodo NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Valida que la zona del Cliente Recomendante perteneza a
                       una zona configurada para el concurso
  Fecha Creacion     :  02/02/2011
  Parametros Entrada :
             pnOidConcurso : oid Concurso
             pnOidCliente :  oid Cliente Recomendante
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR(pnOidConcurso NUMBER,
                                    pnOidCliente  NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Inserta los registros de recomendaciones para la consultora
  Fecha Creacion    : 16/02/2011
  Parametros Entrada:
    pnOidPais       :  Oid Pais
    pnOidCliente    :  Oid Cliente
    pnOidPeriodo    :  Oid Periodo
    pnOidClienteRcdte : Oid Cliente Recomendante
    pnOidModulo      : Oid Modulo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_INSER_REGIS_RECOM(pnOidPais         NUMBER,
                                     pnOidCliente      NUMBER,
                                     pnOidPeriodo      NUMBER,
                                     pnOidClienteRcdte NUMBER,
                                     pnOidModulo       NUMBER,
                                     psTipoProc        VARCHAR2);

  /**************************************************************************
  Descripcion       : Cierra los Concuros
  Fecha Creacion    : 07/03/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
    psServer         :  Nombre del Servidor
  
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE INC_PR_CERRA_CONCU(psCodigoPais    VARCHAR2,
                               psCodigoMarca   VARCHAR2,
                               psCodigoCanal   VARCHAR2,
                               psCodigoPeriodo VARCHAR2,
                               psUsuario       VARCHAR2,
                               psServer        VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida y Carga los premios electivos
  Fecha Creacion    : 10/05/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso    : Numero de Concurso
    psCodigoUsuario    :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PREMI_ELECT(psCodigoPais     VARCHAR2,
                                     psNumeroConcurso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /**************************************************************************
  Descripcion       : Proceso que realiza la Activacion de Concursos
  Fecha Creacion    : 10/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psUsuario        :  Codigo de Usuario
    psServer         :  Nombre del Servidor
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTIV_CONCU(psCodigoPais    VARCHAR2,
                               psCodigoMarca   VARCHAR2,
                               psCodigoCanal   VARCHAR2,
                               psCodigoPeriodo VARCHAR2,
                               psUsuario       VARCHAR2,
                               psServer        VARCHAR2);

  /**************************************************************************
  Descripcion       : Regulariza el registro de recomendaciones en tablas de incentivos
  Fecha Creacion    : 01/02/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Marca
    psIndicadorProceso : F: Facturacion,
                         P: Inicio de Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_REGIS_RECOM_INICI(psCodigoPais       VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psUsuario          VARCHAR2,
                                           psServer           VARCHAR2);

  /***************************************************************************
    Descripcion       : Procedimiento que actualiza la tabla de Pedidos por
                        Campaña de las consultoras ( ped_solic_cabec_acum2 ).
    Fecha Creacion    : 01/06/2011
    Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RESUM_PEDID(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2);

  /***************************************************************************
    Descripcion       : Procedimiento que actualiza la tabla de Pedidos por
                        Campaña de las consultoras ( ped_solic_cabec_acum2 )
                        SE EJECUTA EN FORMA DIARIA.
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RESUM_PEDID_DIARI(psCodigoPais VARCHAR2);

  /***************************************************************************
    Descripcion       : Procedimiento que verifica el registro de pedidos de
                        las recomendadas en INC y crea los registros para
                        aquellas que no lo tienen.
    Fecha Creacion    : 01/06/2011
    Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_PEDID_INCEN(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2);

  /***************************************************************************
  Descripcion : Proceso que valida los registros de la carga de premios
  Fecha Creacion : 13/06/2011
  Autor : Nicolás López
  Parametros:
    psOidSecuencia : Id de Secuencia de Tabla temporal
    psOidConcurso : Id del Concurso
    psNumRegError : Numero de Registros Error
    psNumNivFalta : Numero de Niveles Faltantes
    psNumRegTotal : Numero total de Registros
  ***************************************************************************/
  PROCEDURE INC_PR_PROCE_VALID_CARGA_PREMI(psOidSecuencia VARCHAR2,
                                           psOidConcurso  VARCHAR2,
                                           psNumRegError  OUT VARCHAR2,
                                           psNumNivFalta  OUT VARCHAR2,
                                           psNumRegTotal  OUT VARCHAR2);

  /**************************************************************************
  Descripcion       : Carga los premios de la tabla temporal INC_TMP_CARGA_PREMI
  Fecha Creacion    : 14/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso :  Número de Concurso
    psOidSecuencia   :  Secuencia de tabla temporal
  Autor              :  Nicolás López
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PREMI_EXCEL(psCodigoPais     VARCHAR2,
                                     psNumeroConcurso VARCHAR2,
                                     psOidSecuencia   VARCHAR2);

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Carga de Despacho de Concurso RxP
  Fecha Creacion     : 04/07/2011
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidConcurso : Oid Concurso
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_DESPA_CORXP(psCodigoPais    VARCHAR2,
                                    psCodigoPeriodo VARCHAR2,
                                    pnOidConcurso   NUMBER) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta de Concursos RxP
                      a la tabla de consolidado de detalles. Ejecutado desde la
                      validacion de STO
  Fecha Creacion    : 07/07/2011
  Autor             : Sergio Apaza
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE inc_pr_despa_premi_corxp(pscodigopais    IN VARCHAR2,
                                     pscodigoperiodo IN VARCHAR2,
                                     pscodigousuario IN VARCHAR2,
                                     pscodtipodocu   IN VARCHAR2,
                                     psnumeroproceso IN VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que agrega mensajes x venta de Concursos RxP
                      Ejecutado desde la creacion de Ordenes de Compras x STO
  
  Fecha Creacion    : 07/07/2011
  Autor             : Sergio Apaza
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE inc_pr_despa_mensa_corxp(pscodigopais    IN VARCHAR2,
                                     pscodigoperiodo IN VARCHAR2,
                                     pscodtipodocu   VARCHAR2,
                                     psnumeroproceso VARCHAR2);

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de si el Cliente se le va a realizar
                       Despacho de Concurso RxP
  Fecha Creacion     : 07/07/2011
  Parametros Entrada :
             psCodigoCliente  : Codigo Cliente
             pnOidConcurso : Oid Concurso
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CLIEN_CORXP(psCodigoCliente VARCHAR2,
                                    pnOidConcurso   NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Regulariza las recomendaciones en Incentivos
                      (INC_CLIEN_RECTE, INC_CLIEN_RECDO)
  Fecha Creacion    : 10/08/2011
  Parametros Entrada:
  psCodigoPeriodo   :  Codigo Campaña
  Autor             :
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RECTE_RECDO(psCodigoPeriodo VARCHAR2);

  /***********************************************************************************
  Descripcion        : Realiza Validaciones del ambito Geografico del Concurso, si
                       dentro de sus regiones se encuentra las regiones de bloquo de premios
  
  Fecha Creacion     : 17/11/2011
  Parametros Entrada :
             pnOidConcurso : Oid Concurso
             psRegiones : psRegiones
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_BLOQU(pnOidConcurso NUMBER,
                                          psRegiones    VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Obtiene el codigo de Periodo en base al Oid Periodo
  Fecha Creacion     : 06/12/2011
  Parametros Entrada :
             pnOidPeriodo : Oid Periodo
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_CODIG_PERIO(pnOidPeriodo NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Obtiene el ultimo numero de Concurso a crearse
  Fecha Creacion     : 16/12/2011
  Parametros Entrada :
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_NUMER_CONCU RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Replica la Configuracion de un determinado Concurso
  Fecha Creacion    : 28/12/2011
  Parametros Entrada:
    psCodigoPa     :  Codigo de pais
    pnOidConcurso  :  Codigo de Marca
    psCodigoUsuario : Codigo de Usuario
    psCodigoConcurso : Codigo del Nuevo Concurso creado
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REPLI_CONFI_CONCU(psCodigoPais              VARCHAR2,
                                     pnOidConcurso             NUMBER,
                                     psNombreConcurso          VARCHAR2,
                                     psCodigoPeriodoInicio     VARCHAR2,
                                     psFlagCopiarProducto      VARCHAR2,
                                     psFlagCopiarParticipantes VARCHAR2,
                                     psCodigoUsuario           VARCHAR2,
                                     psCodigoConcurso          OUT VARCHAR2);

  /**************************************************************************
  Descripcion       : Elimina los Niveles, Lotes y Articulos de Premios del Concurso
  Fecha Creacion    : 28/12/2011
  Parametros Entrada:
    pnOidPremiacion : oid Premiacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ELIMI_NIVEL_PREMI(pnOidPremiacion NUMBER,
                                     pnNumeroNiveles NUMBER);

  /**************************************************************************
  Descripcion       : Obtiene el ultimo valor de Contador Articulo que sera
                      utilizado en los codigos de Venta de los premios
  Fecha Creacion    : 30/12/2011
  Parametros Entrada:
    psUltimoCorrelativo  :  representa el ultimo correlativo de articulo de premio
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTEN_ULTIM_CONTA_ARTIC(psUltimoCorrelativo OUT VARCHAR2);

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Cierre de Zona
  Fecha Creacion     : 26/01/2012
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidZona : Oid Zona
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CIERR_ZONA(psCodigoPais    VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   pnOidZona       NUMBER) RETURN VARCHAR2;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Cierre de Region
  Fecha Creacion     : 26/01/2012
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidZona : Oid Region
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CIERR_REGIO(psCodigoPais    VARCHAR2,
                                    psCodigoPeriodo VARCHAR2,
                                    pnOidRegion     NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 21/02/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 21/02/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_DISPO_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Calcula Puntaje Retail de las Consultoras para una Campaña
  
  Fecha Creacion    : 12/03/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida si el Estatus Venta del Cliente
  
  Fecha Creacion    : 13/03/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente    :  oid Cliente
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_ESTAT_VENTA(pnOidConcurso NUMBER,
                                    pnOidCliente  NUMBER) RETURN VARCHAR2;

  PROCEDURE INC_PR_INSER_REGIS_RECOM_JPCC(pnOidPais         NUMBER,
                                          pnOidCliente      NUMBER,
                                          pnOidPeriodo      NUMBER,
                                          pnOidClienteRcdte NUMBER,
                                          pnOidModulo       NUMBER);

  /**************************************************************************
  Descripcion       : Generar de  forma automática las Solicitudes de Servicio
                      Bolsa de Faltante para las consultoras que no se les despacho
                      el premio por falta de stock.
  Fecha Creacion    : 26/04/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoZona     :  Codigo de zona
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_BOLSA_FALTA(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoZona    VARCHAR2,
                                           psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Obtiene el precio reclamo de un articulo de premio o de
                      su reemplazo
  
  Fecha Creacion    : 13/03/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente    :  oid Cliente
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_PRECI_RECLA(pnOidConcurso NUMBER,
                                    psCodigoVenta VARCHAR2) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Aplicar descuento adicional en Facturación.
  Fecha Creacion    : 24/05/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_APLIC_DESCU_ADICI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     psCodigoZona       VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Realiza el cálculo de puntaje a abonar por los concursos
                      que exigen puntos por pedidos  multimarca
  Fecha Creacion    : 06/06/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_MULTI_MARCA(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Generar pedidos de recomendación. Este proceso deberá
                      reemplazar al proceso P440 de SICC
  Fecha Creacion    : 18/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_PEDID_RECOM(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida si el Estatus Venta del Cliente
  
  Fecha Creacion    : 18/06/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
    pnOidCliente         :  oid Cliente
    psCodigoPeriodo      :  codigo Periodo
    psCodigoPeriodoAnt   :  codigo Periodo Anterior
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_ESTAT_VENTA_CONCU(pnOidConcurso      NUMBER,
                                          pnOidCliente       NUMBER,
                                          psCodigoPeriodo    VARCHAR2,
                                          psCodigoPeriodoAnt VARCHAR2)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Este proceso consiste en que cuando se reciba una solicitud
                      anulación, el sistema debe anular o revertir todas las operaciones de
                      incentivos realizadas con ellaenerar pedidos de recomendación
  
  Fecha Creacion    : 02/08/2012
  Parametros Entrada:
    pnOidSolicitud     :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_TRATA_ANULA_SOLIC(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Venta es de Tipo Anulacion
  
  Fecha Creacion    : 03/08/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
    pnOidPeriodo         :  oid Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_VENTA_ANUL(pnOidConcurso NUMBER,
                                         pnOidPeriodo  NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Recomendacion es de Tipo Anulacion
  
  Fecha Creacion    : 03/08/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_RECOM_ANUL(pnOidConcurso NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Este proceso consiste en que cuando se reciba una solicitud
                      anulación, el sistema debe anular o revertir todas las operaciones de
                      incentivos realizadas con ellaenerar pedidos de recomendación
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidSolicitud     :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_CONSU(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida Exigencias del Pedido configurado al concurso para
                      un determinado Cliente
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente      :  oid Cliente
    pnOidSolicitud    :  oid Solicitud
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_EXIGE_PEDID(pnOidConcurso  NUMBER,
                                    pnOidCliente   NUMBER,
                                    pnOidPeriodo   NUMBER,
                                    pnOidSolicitud NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Valida Si la Posicion cumple con las caracteristicas de los
                      productos Validos, Excluidos y Bonificados del Concurso
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    psTipoProducto    :  '1': Productos Validos
                         '2': Productos Excluidos
                         '3': Productos Bonificados
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    pnOidPeriodo        :  oid Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_PRODU_CONCU(psTipoProducto   VARCHAR2,
                                    pnOidConcurso    NUMBER,
                                    pnOidProducto    NUMBER,
                                    pnOidTipoOferta  NUMBER,
                                    pnOidMarcaProd   NUMBER,
                                    pnOidNegocio     NUMBER,
                                    pnOidUnidNegocio NUMBER,
                                    pnOidGenerico    NUMBER,
                                    pnOidSupGenerico NUMBER,
                                    pnOidDetOferta   NUMBER,
                                    psCodigoPeriodo  VARCHAR2)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Calcula Puntaje Bonificado para un detalle de Posicion
                      de una Solicitud
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    psCodigoPeriodo        :  codigo Periodo
    pnPuntajePos        :  Puntaje Posicion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_CALCU_PUNTA_BONIF(pnOidConcurso    NUMBER,
                                    pnOidProducto    NUMBER,
                                    pnOidTipoOferta  NUMBER,
                                    pnOidMarcaProd   NUMBER,
                                    pnOidNegocio     NUMBER,
                                    pnOidUnidNegocio NUMBER,
                                    pnOidGenerico    NUMBER,
                                    pnOidSupGenerico NUMBER,
                                    pnOidDetOferta   NUMBER,
                                    psCodigoPeriodo  VARCHAR2,
                                    pnPuntajePos     NUMBER,
                                    pnDemandaReal    NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Valida Si la Posicion cumple con las caracteristicas de
                      productos Exigidos del Concurso
  
  Fecha Creacion    : 09/08/2012
  Parametros Entrada:
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    pnOidPeriodo        :  oid Periodo
    psPasoValidacion    :  '1' : OK, '0' caso contrario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PRODU_EXIGI_CONCU(pnOidConcurso    NUMBER,
                                           pnOidProducto    NUMBER,
                                           pnOidTipoOferta  NUMBER,
                                           pnOidMarcaProd   NUMBER,
                                           pnOidNegocio     NUMBER,
                                           pnOidUnidNegocio NUMBER,
                                           pnOidGenerico    NUMBER,
                                           pnOidSupGenerico NUMBER,
                                           pnOidDetOferta   NUMBER,
                                           psCodigoPeriodo  VARCHAR2,
                                           psPasoValidacion OUT VARCHAR2,
                                           pvLineaOblig     IN OUT t_lineaOblig,
                                           pvTotalOblig     IN OUT t_totalOblig);

  /**************************************************************************
  Descripcion       : Obtiene el siguiente nivel a alcanzar por una consultora
  
  Fecha Creacion    : 12/08/2012
  Parametros Entrada:
    pnOidConcurso  : oid Concurso
    pnPuntaje      : Puntaje de Concurso
    pnPuntajeSup   : Numero de Puntaje Superior
    pnDescNivel    : Descripcion de Nivel
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTIE_SIGUI_NIVEL(pnOidConcurso NUMBER,
                                     pnPuntaje     NUMBER,
                                     pnPuntajeSup  OUT NUMBER,
                                     psDescNivel   OUT VARCHAR2);

  /**************************************************************************
  Descripcion       : Obtiene los puntos faltantes para llegar a la Meta
  
  Fecha Creacion    : 13/08/2012
  Parametros Entrada:
    pnOidConcurso  : oid Concurso
    pnOidCliente   : oid Cliente
    pnPuntosMeta   : puntos Meta Faltante
    pnIncrementoReal : incremento Real
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTIE_PUNTA_FALTA_METAS(pnOidConcurso    NUMBER,
                                           pnOidCliente     NUMBER,
                                           pnPuntosMeta     OUT NUMBER,
                                           pnIncrementoReal OUT NUMBER);

  /**************************************************************************
  Descripcion       : Reemplazo de premios en bolsa de faltantes
  
  Fecha Creacion    : 16/10/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso :  Numero de Concurso
    psCodigoVentaPremio :  Codigo Venta Premio
    pnOidReemplazo   :  Oid Reemplazo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REEMP_PREMI_BOLSA_FALTA(psCodigoPais        VARCHAR2,
                                           psNumeroConcurso    VARCHAR2,
                                           psCodigoVentaPremio VARCHAR2,
                                           pnOidReemplazo      NUMBER,
                                           psCodigoUsuario     VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Recomendacion es de Tipo Devolucion
  
  Fecha Creacion    : 18/12/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_RECOM_DEVOL(pnOidConcurso NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Realiza la evaluacion de consultoras, de si no paso
                      pedidos O/C en diferentes campañas
  Fecha Creacion    : 14/02/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_EVALU_NPASO_PEDID(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Realiza el calculo de puntajes de los pedidos O/C que se
                      encuentran en gp3.
  Fecha Creacion    : 26/03/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : CSVD
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_OC(psCodigoPais    VARCHAR2,
                                  psCodigoMarca   VARCHAR2,
                                  psCodigoCanal   VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion        : Proceso de reversión de devoluciones de ciclo de pedidos
  
  Fecha Creacion      : 22/04/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidSolicitud   :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor              : Sebastian Guerra
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_CARGO_DEVOL(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Genera cupones electrónicos en base a los resultados de
                      un concurso.
  Fecha Creacion    : 27/05/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_CUPON_ELECT(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Revierte la entrega de premios si se ha incumplido las
                      exigencias de puntos por nivel y campaña
  Fecha Creacion    : 13/06/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_ENTRE_PREMI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Revierte la entrega de premios si se ha sobrepasado el
                      límite final del último nivel del concurso
  Fecha Creacion    : 18/06/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_ENTRE_PREMI_ULTIM(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Despacho de premios pendientes de despacho de concursos
                      para ti para mi.
  
  Fecha Creacion    : 28/08/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_DESPA_PREMI_PTIMI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Proceso Identifica si cada recomendante es participante
                      o no del concurso para el cual fue registrado.Además corrige
                      una data de elección de premio para las recomendadas
  Fecha Creacion    : 09/10/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_PARTI_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion        : Obtiene datos del Oid Detalle Oferta
  Fecha Creacion     : 18/11/2013
  Parametros Entrada :
             pnOidDetOferta : Oid Detalle Oferta
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_DETAL_CUV(pnOidDetOferta NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Obtiene datos del Oid Detalle Oferta
  Fecha Creacion     : 12/12/2013
  Parametros Entrada :
             pnOidConcurso : Oid Concurs
             psCodigoVenta : Codigo Venta
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_POSIC_REEMP(pnOidConcurso NUMBER,
                                    psCodigoVenta VARCHAR2) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Generar las Solicitudes de Servicio Bolsa de Faltante
                      para las consultoras en Facturacion
                      el premio por falta de stock.
  Fecha Creacion    : 27/02/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_BOLSA_FACTU(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Validacion de Premios Electivos
  Fecha Creacion    : 07/04/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PREMI_ELEGI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Realiza el calculo del total demanda de la ordenes de
                      compra que se encuentran en gp3.
  Fecha Creacion    : 19/08/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_TOTAL_FACTU(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2);

  /***************************************************************************
  Descripcion       : Valida de Migracion Puntos Consultora
  Fecha Creacion    : 10/09/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_MIGRA_PUNTO_CONSU(psCodigoPais  VARCHAR2,
                                           pnNumeroCarga NUMBER);

  /***************************************************************************
  Descripcion       : Actualiza Migracion Puntos Consultora
  Fecha Creacion    : 28/08/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_MIGRA_PUNTO_CONSU(psCodigoPais            VARCHAR2,
                                           pnNumeroCarga           NUMBER,
                                           psNumeroConcursoOrigen  VARCHAR2,
                                           psNumeroConcursoDestino VARCHAR2,
                                           psCodigoUsuario         VARCHAR2);

  /**************************************************************************
  Descripcion       : Clasifica los pagos de los concursos de ventas en gestión,
                      se les genera Abono Automático en su cuenta corriente
  Fecha Creacion    : 06/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_CLASI_PAGOS_CONSU(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Generar Abono Directo Cuenta Corriente
  Fecha Creacion    : 14/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion : Fecha Facturacion
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_ABONO_DIREC(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Actualiza Transacciones Pago Concurso
  Fecha Creacion    : 16/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    pnNumeroCarga    :  Numero Carga
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion : Fecha Facturacion
    psCodigoPago       : Codigo Pago
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_TRANS_PAGO_CONCU(psCodigoPais       VARCHAR2,
                                          pnNumeroCarga      NUMBER,
                                          psCodigoPeriodo    VARCHAR2,
                                          psFechaFacturacion VARCHAR2,
                                          psCodigoPago       VARCHAR2,
                                          psCodigoUsuario    VARCHAR2);

  /**************************************************************************
  Descripcion       : Actualiza Histórico de Incentivos
  Fecha Creacion    : 16/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de periodo
  Autor             : Juan Gutiérrez
  ***************************************************************************/
  PROCEDURE INC_PR_MOVER_INFOR_HISTO(psCodigoPais    VARCHAR2,
                                     psCodigoPeriodo VARCHAR2);

  /**************************************************************************
  Descripcion       : Acumulación de puntos para Programa de Puntos
  Fecha Creacion    : 04/02/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de periodo
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_ACUMU_PUNTO_PROGR_PUNTO(psCodigoPais    VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Cancelacion de puntos para Programa de Puntos
  Fecha Creacion    : 04/02/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCampanyaProceso  :  Campaña de proceso
    psFechaFacturacion  :  Fecha de facturacion
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_CANCE_PUNTO_PROGR_PUNTO(psCodigoPais       VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCampanyaProceso  VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psUsuario          VARCHAR2);

  /**************************************************************************
  Descripcion       : Acumulación de puntos por Programas de Constancia
  Fecha Creacion    : 06/04/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCampanyaProceso  :  Campaña de proceso
    psFechaFacturacion  :  Fecha de facturacion
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_ACUMU_PUNTO_PROGR_CONST(psCodigoPais       VARCHAR2,
                                           psCampanyaProceso  VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psUsuario          VARCHAR2);

  /**************************************************************************
  Descripcion : Obtiene la cantidad de pedidos en periodos consecutivosque ha 
             realizado la consultora
  Fecha Creacion : 06/04/2015
  Parametros Entrada :
   psCodigoPais     :  Codigo de pais
   psCampanyaProceso  :  Campaña de proceso
   psOidCliente  :  Oid del cliente
  Autor : Ivan Tocto
  ***************************************************************************/
  FUNCTION INC_FN_OBTEN_PEDPE_CONSEC(psCodigoPais      VARCHAR2,
                                     psCampanyaProceso VARCHAR2,
                                     psOidCliente      NUMBER,
                                     psCampinicio      VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion : Obtiene el indicador de Región piloto Retail para una consultora
  Fecha Creacion : 06/07/2015
  Parametros Entrada :
   psCodConsu     :  Codigo de consultora
  Autor : Hernán Ramos
  ***************************************************************************/
  FUNCTION INC_FN_OBTEN_INDPI_RETAI(psCodConsu      VARCHAR2) 
           RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Realiza el Calculo de Vencimiento de Puntos por Consultora
  Fecha Creacion    : 20/01/2016
  Parametros Entrada:
    pnOidConcurso     :  Oid Concurso
    pnOidCliente    :  Oid Cliente
    pnIndBorrado  :  Indicador de Borrado: 0->Borrar, 1->No Borrar
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_VENCI_PUNTO_CONCU(pnOidConcurso     NUMBER,
                                     pnOidCliente      NUMBER,
                                     pnIndBorrado      NUMBER);
                                     
  /**************************************************************************
  Descripcion       : Realiza el Calculo de vencimiento de punto al Cierre
                      de Campaña
  Fecha Creacion    : 20/01/2016
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_VENCI_PUNTO_CONCU(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2);
                                           
  /**************************************************************************
  Descripcion       : Realiza el Proceso de vencimiento de puntos
  Fecha Creacion    : 03/02/2016
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Segundo Leiva
  
  ***************************************************************************/
  PROCEDURE INC_PR_PROCE_VENCI_PUNTO_CONCU(psCodigoPais    VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2);
                                           
END Inc_Pkg_Proce_Incen;
/
CREATE OR REPLACE PACKAGE BODY "INC_PKG_PROCE_INCEN" IS

  /***************************************************************************
  Descripcion : Proceso que carga los puntajes en cuenta corriente
  Fecha Creacion : 01/06/2009
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psNumLote : Numero Lote
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_CUENT_CORRI_PUNTA(psCodigoPais VARCHAR2,
                                           psNumLote    VARCHAR2) IS
    lnOidPeri NUMBER;
    ldFecha   DATE;
    lsFecha   VARCHAR2(10);
    lsPeriodo INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
    lnTotal   NUMBER;
  BEGIN
  
    /* Obteniendo periodos */
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');
  
    SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;
  
    --Actualizamos el indicador de aplicacion
    UPDATE INC_CARGA_PUNTA_CONSU
       SET IND_APLI = '1', FEC_PROC = SYSDATE
     WHERE NUM_LOTE = psNumLote
       AND IND_CARG = '1';
  
    --Insertamos en cuenta corrriente Puntaje
    INSERT INTO INC_CUENT_CORRI_PUNTO
      (OID_CUEN_CORR_PUNT,
       NUM_MOVI,
       NUM_PUNT,
       NUM_PUNT_EXIG,
       FEC_MOVI,
       COPA_OID_PARA_GRAL,
       CLIE_OID_CLIE,
       PERD_OID_PERI,
       TMOV_OID_TIPO_MOVI,
       FEC_ULTI_ACTU,
       VAL_DESC,
       USU_MODI)
      SELECT INC_CUCP_SEQ.NEXTVAL,
             INC_CUCP_SEQ.NEXTVAL,
             A.NUM_PUNT,
             0,
             TO_DATE(SYSDATE),
             (SELECT X.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER X
               WHERE X.NUM_CONC = A.NUM_CONC
                 AND X.IND_ACTI = '1') OID_PARA_GRAL,
             Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE),
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(A.COD_PERI), --lnOidPeri,
             CASE
               WHEN A.NUM_PUNT >= 0 THEN
                '1'
               ELSE
                '2'
             END AS OID_TIPO_MOVI,
             SYSDATE,
             A.COD_MOTI,
             A.COD_USUR
        FROM INC_CARGA_PUNTA_CONSU A
       WHERE A.NUM_LOTE = psNumLote
         AND A.IND_APLI = '1'
         AND A.IND_CARG = '1';
  
    -- Se valida que solo se insertaran las que no existen en INC_SOLIC_CONCU_PUNTA
    -- ademas que deben tener oid cabcera
  
    INSERT INTO INC_SOLIC_CONCU_PUNTA
      (OID_SOLI_CONC_PUNT,
       NUM_PUNT,
       VAL_PUNT_BONI,
       VAL_PUNT_FALT_NANU,
       FEC_DOCU,
       IND_ANUL,
       COPA_OID_PARA_GRAL,
       SOCA_OID_SOLI_CABE,
       PERD_OID_PERI,
       CLIE_OID_CLIE,
       IMP_MONT,
       CLIE_OID_CLIE_GERE,
       NUM_UNID)
      SELECT INC_SOCP_SEQ.NEXTVAL,
             0,
             0,
             0,
             (SELECT MAX(Z.FEC_FACT)
                FROM PED_SOLIC_CABEC     Z,
                     PED_TIPO_SOLIC_PAIS B,
                     PED_TIPO_SOLIC      C,
                     PED_SOLIC_CABEC     D
               WHERE Z.IND_OC = 1
                 AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
                 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
                 AND Z.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
                 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
                 AND Z.CLIE_OID_CLIE =
                     Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE)),
             0,
             (SELECT Z.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER Z
               WHERE Z.NUM_CONC = A.NUM_CONC
                 AND Z.IND_ACTI = '1'),
             (SELECT MAX(Z.OID_SOLI_CABE)
                FROM PED_SOLIC_CABEC     Z,
                     PED_TIPO_SOLIC_PAIS B,
                     PED_TIPO_SOLIC      C,
                     PED_SOLIC_CABEC     D
               WHERE Z.IND_OC = 1
                 AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
                 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
                 AND Z.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
                 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
                 AND Z.CLIE_OID_CLIE =
                     Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE)),
             lnOidPeri,
             Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE),
             0,
             NULL,
             0
        FROM INC_CARGA_PUNTA_CONSU A
       WHERE A.NUM_LOTE = psNumLote
         AND A.IND_APLI = '1'
         AND A.IND_CARG = '1'
         AND NOT EXISTS (SELECT X.CLIE_OID_CLIE
                FROM INC_SOLIC_CONCU_PUNTA X
               WHERE X.CLIE_OID_CLIE =
                     Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE)
                 AND A.IND_APLI = '1'
                 AND A.IND_CARG = '1'
                 AND X.COPA_OID_PARA_GRAL =
                     (SELECT Y.OID_PARA_GRAL
                        FROM INC_CONCU_PARAM_GENER Y
                       WHERE Y.NUM_CONC = A.NUM_CONC
                         AND Y.IND_ACTI = '1'))
         AND EXISTS
       (SELECT 1
                FROM PED_SOLIC_CABEC     Z,
                     PED_TIPO_SOLIC_PAIS B,
                     PED_TIPO_SOLIC      C,
                     PED_SOLIC_CABEC     D
               WHERE Z.IND_OC = 1
                 AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
                 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
                 AND Z.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
                 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
                 AND Z.CLIE_OID_CLIE =
                     Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE) --psClieOidClie
                 AND A.IND_APLI = '1'
                 AND A.IND_CARG = '1');
  
    -- se verifica que no existan en INC_CANDI_GANAD para insertarlas
  
    INSERT INTO INC_CANDI_GANAD
      (OID_CAND_GANA,
       IND_META_SUPE,
       VAL_REQU_PREM_SUPE,
       PERD_OID_PERI,
       COPA_OID_PARA_GRAL,
       BINC_OID_BASE_INCU,
       PERD_OID_PERI_EVAL,
       CLIE_OID_CLIE,
       FEC_ULTI_ACTU,
       NUM_PERI_EVAL)
      SELECT INC_CAGA_SEQ.NEXTVAL,
             0,
             0,
             lnOidPeri,
             (SELECT Z.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER Z
               WHERE Z.NUM_CONC = A.NUM_CONC
                 AND Z.IND_ACTI = '1'),
             NULL,
             NULL,
             Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE),
             SYSDATE,
             1
        FROM INC_CARGA_PUNTA_CONSU A
       WHERE A.NUM_LOTE = psNumLote
         AND A.IND_APLI = '1'
         AND A.IND_CARG = '1'
         AND NOT EXISTS (SELECT X.CLIE_OID_CLIE
                FROM INC_CANDI_GANAD X
               WHERE X.CLIE_OID_CLIE =
                     Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE)
                 AND A.IND_APLI = '1'
                 AND A.IND_CARG = '1'
                 AND X.COPA_OID_PARA_GRAL =
                     (SELECT Y.OID_PARA_GRAL
                        FROM INC_CONCU_PARAM_GENER Y
                       WHERE Y.NUM_CONC = A.NUM_CONC
                         AND Y.IND_ACTI = '1'));
  
    -- Para los Concursos de Recomendacion, si existe para el periodo se actualiza,
    -- sino, se inserta un nuevo registro
    FOR x IN (SELECT Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE) OID_CLIE,
                     B.OID_PARA_GRAL
                FROM INC_CARGA_PUNTA_CONSU A, INC_CONCU_PARAM_GENER B
               WHERE A.NUM_LOTE = psNumLote
                 AND A.IND_APLI = '1'
                 AND A.IND_CARG = '1'
                 AND A.NUM_CONC = B.NUM_CONC
                 AND B.IND_ACTI = '1'
                 AND B.BCAL_OID_BASE_CALC = 4 --Recomendacion
                 AND EXISTS
               (SELECT X.CLIE_OID_CLIE
                        FROM INC_CANDI_GANAD X
                       WHERE X.CLIE_OID_CLIE =
                             Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_CLIE)
                         AND A.IND_APLI = '1'
                         AND A.IND_CARG = '1'
                         AND X.COPA_OID_PARA_GRAL = B.OID_PARA_GRAL)) LOOP
    
      SELECT COUNT(1)
        INTO lnTotal
        FROM INC_CANDI_GANAD
       WHERE CLIE_OID_CLIE = x.OID_CLIE
         AND COPA_OID_PARA_GRAL = x.OID_PARA_GRAL
         AND PERD_OID_PERI = lnOidPeri;
    
      IF (lnTotal = 0) THEN
        INSERT INTO INC_CANDI_GANAD
          (OID_CAND_GANA,
           IND_META_SUPE,
           VAL_REQU_PREM_SUPE,
           PERD_OID_PERI,
           COPA_OID_PARA_GRAL,
           BINC_OID_BASE_INCU,
           PERD_OID_PERI_EVAL,
           CLIE_OID_CLIE,
           FEC_ULTI_ACTU,
           NUM_PERI_EVAL)
        VALUES
          (INC_CAGA_SEQ.NEXTVAL,
           0,
           0,
           lnOidPeri,
           x.OID_PARA_GRAL,
           NULL,
           NULL,
           x.OID_CLIE,
           SYSDATE,
           1);
      ELSE
        UPDATE INC_CANDI_GANAD
           SET IND_META_SUPE      = 0,
               VAL_REQU_PREM_SUPE = 0,
               BINC_OID_BASE_INCU = NULL,
               PERD_OID_PERI_EVAL = NULL,
               FEC_ULTI_ACTU      = SYSDATE,
               NUM_PERI_EVAL      = 1
         WHERE CLIE_OID_CLIE = x.OID_CLIE
           AND COPA_OID_PARA_GRAL = x.OID_PARA_GRAL
           AND PERD_OID_PERI = lnOidPeri;
      END IF;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CARGA_CUENT_CORRI_PUNTA: ' ||
                              ls_sqlerrm);
  END INC_PR_CARGA_CUENT_CORRI_PUNTA;

  /***************************************************************************
  Descripcion :
   Ejecuta Validaciones de premios electivos para la consultora,
   si es correcta las validacion retorna
   los datos de la consultora que se mostararn en patalla asi mismo consolida
   la lista de elegidos y seleccionables guaradndolos en un temporal
  Fecha Creacion : 15/07/2009
  Autor : Sergio Buchelli
  Parametros:
  Parametros:
   psCodigoPais Codigo Pais
   oidConcurso OidConcurso
   codigoConsultora Consultora
   psTipo Tipo validacion 0:seleccion 1:digitacion
   mensajeError Mensaje error
   mensajeResultado Mensaje Respuesta con datos concatenados separados por ','
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PREMI_ELECT(psCodigoPais       VARCHAR2,
                                     psOidConcurso      VARCHAR2,
                                     psCodigoConsultora VARCHAR2,
                                     psTipo             VARCHAR2,
                                     psMensajeError     OUT VARCHAR2,
                                     psMensajeResultado OUT VARCHAR2) IS
    lnOidPeri NUMBER;
    ldFecha   DATE;
    lsFecha   VARCHAR2(10);
  
    regParamGenerPremi INC_PARAM_GENER_PREMI%ROWTYPE;
    regConcurso        INC_CONCU_PARAM_GENER%ROWTYPE;
    regRequePremio     INC_REQUI_PREMI%ROWTYPE;
  
    regGttPremioElectivo INC_GTT_PREMI_ELECT%ROWTYPE;
    lnNumNivel           NUMBER;
    lnNumPremio          NUMBER;
  
    lnPuntajeObtenido     NUMBER := 0;
    lnPuntajeCanjeado     NUMBER := 0;
    lnSaldoPuntos         NUMBER := 0;
    lnPuntajeComprometido NUMBER := 0;
    lnPuntajeDisponible   NUMBER := 0;
    lsIndParticipacion    INC_PLANT_CONCU.VAL_PART%TYPE;
    lsIndFaseCalificacion INC_PLANT_CONCU.VAL_FASE_CALI%TYPE;
    lnfactorConversion    INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
    lsNombreConsultora    VARCHAR2(100);
    lsPeriodoDesde        VARCHAR2(6);
    lsPeriodoHasta        VARCHAR2(6);
    lsPeriodoActual       VARCHAR2(6);
    oidPais               seg_pais.OID_PAIS%TYPE;
    lsTipoCliente         MAE_TIPO_CLIEN.COD_TIPO_CLIE%TYPE;
    lnOidTipoCliente      MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
    lnValmeta             NUMBER := 0;
    oidCliente            MAE_CLIEN.OID_CLIE%TYPE;
    lnResult              NUMBER;
    lsResult              VARCHAR2(100);
  
    primerElemento      BOOLEAN := TRUE;
    codigoVentaAuxiliar VARCHAR2(6);
    lnExisteActualizado NUMBER;
  
    lnContGeografico NUMBER;
  
    CURSOR cursorPremioElectivo(lnNumNivel NUMBER, lnNumPremio NUMBER) IS
      SELECT X.COD_VENT_FICT
        FROM INC_GTT_PREMI_ELECT X
       WHERE NUM_NIVE = lnNumNivel
         AND NUM_PREM = lnNumPremio
       ORDER BY X.COD_VENT_FICT ASC;
  
    CURSOR cursorPremioElegidos IS
      SELECT I.OID_PARA_GRAL oidConcursoGral,
             I.NUM_NIVE      numeroNivel,
             I.NUM_PREM      numPremio,
             I.NUM_NIVE      numNivel,
             I.IND_PEND      indPendiente,
             I.NUM_UNID      numUnidades
        FROM INC_GTT_PREMI_ELEGI I;
  
  BEGIN
    oidPais    := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    oidCliente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(pscodigoConsultora);
  
    /*Obteniendo los parametrso iniciales ,TIPO PREMIACION, TIPO ELECCION, IS ACUMULATIVO,Perido de despacho*/
    SELECT *
      INTO regParamGenerPremi
      FROM INC_PARAM_GENER_PREMI X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso;
  
    /* CONCURSO DIRIGIDO,*/
    SELECT *
      INTO regConcurso
      FROM INC_CONCU_PARAM_GENER B
     WHERE B.OID_PARA_GRAL = psOidConcurso;
  
    /*cuota minimo de ingreso*/
    SELECT *
      INTO regRequePremio
      FROM INC_REQUI_PREMI X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso;
  
    /*puntaje obtenido de la consultora*/
    SELECT NVL(SUM(X.NUM_PUNT), 0)
      INTO lnPuntajeObtenido
      FROM INC_CUENT_CORRI_PUNTO X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
       AND X.CLIE_OID_CLIE = oidCliente
       AND UPPER(TRIM(VAL_DESC)) <> UPPER(TRIM('Entrega de Premio'));
  
    /*Indicador Participacion,Indicador de Fase*/
    SELECT X.VAL_PART, X.VAL_FASE_CALI
      INTO lsIndParticipacion, lsIndFaseCalificacion
      FROM INC_PLANT_CONCU X, INC_CONCU_PARAM_GENER Y
     WHERE X.OID_PLAN_CONC = Y.PLC2_OID_PLAN_CONC
       AND Y.OID_PARA_GRAL = psOidConcurso;
  
    /*Clasificacion de la particapante del concurso*/
    /*clasifcaicones de la consultora */
  
    /*Factor Conversion*/
    SELECT X.VAL_FACT_CONV
      INTO lnfactorConversion
      FROM INC_OBTEN_PUNTO X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso;
  
    /*Nombres y apeelidos de la consultora*/
    SELECT X.VAL_APE1 || ' ' || X.VAL_APE2 || ' ' || X.VAL_NOM1 || ' ' ||
           X.VAL_NOM2
      INTO lsNombreConsultora
      FROM MAE_CLIEN X
     WHERE X.COD_CLIE = pscodigoConsultora;
  
    /* Obteniendo entidad periodos ACTUAL*/
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');
  
    SELECT MAX(OID_PERI)
      INTO lnOidPeri
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;
  
    SELECT A.COD_PERI
      INTO lsPeriodoActual
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = lnOidPeri
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';
  
    /*Perido Inicio Concurso*/
    SELECT A.COD_PERI
      INTO lsPeriodoDesde
      FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
       AND B.OID_PERI = regConcurso.PERD_OID_PERI_DESD
       AND B.CANA_OID_CANA = C.OID_CANA
       AND B.MARC_OID_MARC = D.OID_MARC
       AND C.COD_CANA = 'VD'
       AND D.COD_MARC = 'T';
  
    /*INICIO VALIDACION tipo de elccion 1:DURANTE EL CONCURSO 2: FINALIZAR CONCURSO*/
  
    /*IF (regParamGenerPremi.TELE_OID_TIPO_ELEC = '1') THEN
    
      --OBTENIENDO EL PERIODO HASTA
      IF (regParamGenerPremi.PERD_OID_PERI IS NOT NULL) THEN
    
        SELECT A.COD_PERI
          INTO lsPeriodoHasta
          FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
         WHERE A.OID_PERI = B.PERI_OID_PERI
           AND B.OID_PERI = regParamGenerPremi.PERD_OID_PERI
           AND B.CANA_OID_CANA = C.OID_CANA
           AND B.MARC_OID_MARC = D.OID_MARC
           AND C.COD_CANA = 'VD'
           AND D.COD_MARC = 'T';
      ELSE
        --PERIODO HASTE DEL CONCURSO MAS 1
        SELECT A.COD_PERI
          INTO lsPeriodoHasta
          FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
         WHERE A.OID_PERI = B.PERI_OID_PERI
           AND B.OID_PERI = regConcurso.PERD_OID_PERI_HAST
           AND B.CANA_OID_CANA = C.OID_CANA
           AND B.MARC_OID_MARC = D.OID_MARC
           AND C.COD_CANA = 'VD'
           AND D.COD_MARC = 'T';
    
        lsPeriodoHasta := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(lsPeriodoHasta,
                                                                 oidPais,
                                                                 Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T'),
                                                                 Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD'),
                                                                 1);
      END IF;
    
      --Error si no se encuentra en el Rango
    
      IF (TO_NUMBER(lsPeriodoActual) < TO_NUMBER(lsPeriodoDesde) OR
         TO_NUMBER(lsPeriodoActual) > TO_NUMBER(lsPeriodoHasta)) THEN
        psMensajeError := 'La elección de premios para el concurso es partir de la campaña ' ||
                          lsPeriodoDesde || ' hasta la campaña ' ||
                          lsPeriodoHasta ||
                          ' (durante la vigencia del concurso)';
        RETURN;
      END IF;
    
    ELSE
      --TIPO ELECCION FINALIZAR CONCURSO
      --periodo fin del concurso
      SELECT A.COD_PERI
        INTO lsPeriodoHasta
        FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
       WHERE A.OID_PERI = B.PERI_OID_PERI
         AND B.OID_PERI = regConcurso.PERD_OID_PERI_HAST
         AND B.CANA_OID_CANA = C.OID_CANA
         AND B.MARC_OID_MARC = D.OID_MARC
         AND C.COD_CANA = 'VD'
         AND D.COD_MARC = 'T';
      --NO RANGO
      IF (TO_NUMBER(lsPeriodoActual) < TO_NUMBER(lsPeriodoHasta)) THEN
        psMensajeError := 'La elección de premios para el concurso es a partir de la campaña ' ||
                          lsPeriodoHasta || ' (al finalizar el concurso)';
        RETURN;
      END IF;
    
    END IF;*/ --FIN DE LA VALICACION TIPO ELECCION DEL CONCURSO
  
    /* INICIO VALIDACION PUNTAJE OBTENIDO CONCULTORA*/
  
    /*IF (lnPuntajeObtenido < regRequePremio.VAL_CUOT_INGR) THEN
      psMensajeError := 'El Puntaje de ' || lnPuntajeObtenido ||
                        ' de la consultora, es menor que la cuota mínima de ingreso de ' ||
                        regRequePremio.VAL_CUOT_INGR;
      RETURN;
    END IF;*/
    /*FIN VALIDACION PUNTAJE OBTENIDO*/
  
    /*Inicio Validacion Indicador Participacion SI ES ACTIVO*/
    /*IF (lsIndParticipacion = '1') THEN
    
      BEGIN
        SELECT Y.COD_TIPO_CLIE, Y.OID_TIPO_CLIE
          INTO lsTipoCliente, lnOidTipoCliente
          FROM MAE_CLIEN_TIPO_SUBTI X, MAE_TIPO_CLIEN Y
         WHERE X.CLIE_OID_CLIE = oidCliente
           AND Y.OID_TIPO_CLIE = X.TICL_OID_TIPO_CLIE
           AND X.IND_PPAL = '1';
      EXCEPTION
        WHEN TOO_MANY_ROWS THEN
          psMensajeError := 'La consultora tiene más de una tipología de cliente configurado como principal. Por favor modificar los datos de la consultora correctamente';
          RETURN;
      END;
    
      IF (regConcurso.DIRI_OID_DIRI = '1') THEN
        --Conultoras
        --el tipo debe ser consultotas sino hay error
        IF (lsTipoCliente <> '02') THEN
          --NO ES CONSULTORA
          psMensajeError := 'El código de cliente ingresado no corresponde a una Consultora';
          RETURN;
        END IF;
    
      END IF;
    
      IF (regConcurso.DIRI_OID_DIRI = '2') THEN
        --Gerentes
        --el tipo debe ser gerentes sino hay error
        IF (lsTipoCliente <> '04') THEN
          --NO ES CONSULTORA
          psMensajeError := 'Error en tipo de Cliente : Concurso Dirigido a Gerentes. Tipo Cliente Actual: ' ||
                            UPPER(pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                                                 lnOidTipoCliente,
                                                                 'MAE_TIPO_CLIEN'));
          RETURN;
        END IF;
      END IF;
    
    ELSE
      --IND participacion desactivado
      lsResult := INC_FN_VALID_CLASI_CONCU(psOidConcurso, oidCliente); --1:valid 0:no valido
    
      IF (lsResult <> '1') THEN
        psMensajeError := lsResult; --'La consultora no tiene la clasificación ' || lsResult || ' requerida por el concurso';
        RETURN;
      END IF;
    
    END IF;*/ /*FIN VALIDACION INDICADOR PARTICIPACION COMPLETA*/
  
    /*Validacion en la fase de calificacion*/
    /*IF (lsIndFaseCalificacion = '1') THEN
      --SE VALIDA QUE EL PUNTAJE OBTENIDO SEA >= META CONSULTORA SINO MENSAJE ERROR
      BEGIN
        SELECT NVL(X.VAL_META, 0) / lnfactorConversion
          INTO lnValmeta
          FROM INC_METAS_TIPO_VENTA X
         WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
           AND X.CLIE_OID_CLIE = oidCliente;
      EXCEPTION
        WHEN OTHERS THEN
          lnValmeta := 0;
      END;
      --SI NO HAY META SE OBTINE MONTO MINIMO
      IF (lnValmeta = 0) THEN
    
        BEGIN
          SELECT Y.IMP_MONT_MINI / lnfactorConversion
            INTO lnValmeta
            FROM INC_MONTO_MINIM_RANGO_CONSU Y,
                 INC_CLASI_PARTI_CALIF       X,
                 INC_ESTAT_VENTA             Z
           WHERE X.OID_CLAS_PART_CALI = Y.CLPC_OID_CLAS_PART_CALI
             AND X.COPA_OID_PARA_GRAL = psoidConcurso
             AND Y.ESTV_OID_ESTA_VENT = Z.OID_ESTA_VENT
             AND X.COPA_OID_PARA_GRAL = Z.COPA_OID_PARA_GRAL
             AND Z.ESTA_OID_ESTA_CLIE IN
                 (SELECT W.OID_ESTA_CLIE
                    FROM MAE_ESTAT_CLIEN W
                   WHERE W.OID_ESTA_CLIE = Z.ESTA_OID_ESTA_CLIE
                     AND W.COD_ESTA_CLIE = '02'); --INGRESO/NUEVA
    
        EXCEPTION
          WHEN OTHERS THEN
            lnValmeta := 0;
        END;
    
      END IF;
    
      IF (lnPuntajeObtenido < lnValmeta) THEN
        psMensajeError := 'El puntaje de ' || TO_CHAR(lnPuntajeObtenido) ||
                          ' de la consultora es menor a su meta de ' ||
                          TO_CHAR(lnValmeta);
        RETURN;
      END IF;
    
    END IF;*/ --FIN DE VALIDACIN FASE CALIFICACION
  
    /*Validacion del Ambito Geografico*/
    /*SELECT COUNT(geo.oid_ambito_geografico)
      INTO lnContGeografico
      FROM INC_AMBIT_GEOGR geo
     WHERE geo.copa_oid_para_gral = psOidConcurso;
    
    IF (lnContGeografico > 0) THEN
      --SE VALIDARA SI CONSULTORA PERTENCE
    
      lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(psOidConcurso, oidCliente);
      IF (lsResult <> '1') THEN
        psMensajeError := 'La consultora no pertenece al ámbito geográfico requerido por el concurso ';
        RETURN;
    
      END IF;
    
    END IF;*/
  
    /*UNA VEZ TERMINADO LAS VALIDACION SE PROCESE A OBTENER EL MENSAJE RESULTADI
    nombreConsultora,meta,puntajeObtenido,puntajeCanjeado,puntajeSaldo,puntajeComprometido,puntajeDisponible
    */
    DELETE FROM INC_GTT_PREMI_ELEGI; --borramos temporal de premios elegidos
    --insertamos los premios elegidos
    INSERT INTO INC_GTT_PREMI_ELEGI
      (OID_PARA_GRAL,
       NUM_NIVE,
       NUM_PREM,
       VAL_COST_PUNT,
       NUM_UNID,
       IND_PEND)
      SELECT DISTINCT X.COPA_OID_PARA_GRAL,
                      (SELECT A.NUM_NIVE
                         FROM INC_PARAM_NIVEL_PREMI A
                        WHERE A.OID_PARA_NIVE_PREM =
                              X.PANP_OID_PARA_NIVE_PREM) AS NUM_NIVE,
                      X.NUM_PREM,
                      (SELECT NVL(A.NUM_CANT_FIJA_PUNT,
                                  NVL(A.NUM_CANT_INIC_PUNT, 0))
                         FROM INC_PARAM_NIVEL_PREMI A
                        WHERE A.OID_PARA_NIVE_PREM =
                              X.PANP_OID_PARA_NIVE_PREM) AS COS_PUNT,
                      (SELECT COUNT(1)
                         FROM INC_PREMI_ELEGI z
                        WHERE z.COPA_OID_PARA_GRAL = X.COPA_OID_PARA_GRAL
                          AND z.CLIE_OID_CLIE = X.CLIE_OID_CLIE
                          AND z.PANP_OID_PARA_NIVE_PREM =
                              x.PANP_OID_PARA_NIVE_PREM
                          AND z.NUM_PREM = x.NUM_PREM
                          AND Z.IND_PEND = '1') AS NUM_UNID,
                      X.IND_PEND
        FROM INC_PREMI_ELEGI X
       WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
         AND X.CLIE_OID_CLIE = oidCliente
         AND X.IND_PEND = '1'
      UNION
      SELECT DISTINCT X.COPA_OID_PARA_GRAL,
                      (SELECT A.NUM_NIVE
                         FROM INC_PARAM_NIVEL_PREMI A
                        WHERE A.OID_PARA_NIVE_PREM =
                              X.PANP_OID_PARA_NIVE_PREM) AS NUM_NIVE,
                      X.NUM_PREM,
                      (SELECT NVL(A.NUM_CANT_FIJA_PUNT,
                                  NVL(A.NUM_CANT_INIC_PUNT, 0))
                         FROM INC_PARAM_NIVEL_PREMI A
                        WHERE A.OID_PARA_NIVE_PREM =
                              X.PANP_OID_PARA_NIVE_PREM) AS COS_PUNT,
                      (SELECT COUNT(1)
                         FROM INC_PREMI_ELEGI z
                        WHERE z.COPA_OID_PARA_GRAL = X.COPA_OID_PARA_GRAL
                          AND z.CLIE_OID_CLIE = X.CLIE_OID_CLIE
                          AND z.PANP_OID_PARA_NIVE_PREM =
                              x.PANP_OID_PARA_NIVE_PREM
                          AND z.NUM_PREM = x.NUM_PREM
                          AND z.IND_PEND = '0') AS NUM_UNID,
                      X.IND_PEND
        FROM INC_PREMI_ELEGI X
       WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
         AND X.CLIE_OID_CLIE = oidCliente
         AND X.IND_PEND = '0';
  
    --puntaje canjeado
    SELECT NVL(ABS(SUM(X.NUM_PUNT)), 0)
      INTO lnPuntajeCanjeado
      FROM INC_CUENT_CORRI_PUNTO X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
       AND X.CLIE_OID_CLIE = oidCliente
       AND UPPER(TRIM(VAL_DESC)) = UPPER(TRIM('Entrega de Premio'));
  
    --Saldo Puntos
    SELECT NVL(SUM(X.NUM_PUNT), 0)
      INTO lnSaldoPuntos
      FROM INC_CUENT_CORRI_PUNTO X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
       AND X.CLIE_OID_CLIE = oidCliente;
  
    --si indicador de no validacion esta desactivado se obtiene puntaje
    IF (regParamGenerPremi.IND_NOVA_PUNT_PREM IS NULL OR
       regParamGenerPremi.IND_NOVA_PUNT_PREM = '0') THEN
    
      --Puntaje Comprometido , es aquel putaje ya elegido
      --si el concurso es por niveles y acumulativo solo lo comprometido es el maximo nivel si es que ha sido elegido
      --
      IF (regParamGenerPremi.TPRM_OID_TIPO_PION = '2' AND
         regParamGenerPremi.IND_PREM_ACUM_NIVE = '1') THEN
        SELECT NVL(SUM(VAL_COST_PUNT * NUM_UNID), 0)
          INTO lnPuntajeComprometido
          FROM INC_GTT_PREMI_ELEGI A
         WHERE ind_pend = '1'
           AND NUM_NIVE =
               (SELECT MAX(NUM_NIVE)
                  FROM INC_GTT_PREMI_ELEGI B
                 WHERE A.OID_PARA_GRAL = B.OID_PARA_GRAL);
      ELSE
        SELECT NVL(SUM(VAL_COST_PUNT * NUM_UNID), 0)
          INTO lnPuntajeComprometido
          FROM INC_GTT_PREMI_ELEGI
         WHERE ind_pend = '1';
      END IF;
      --Puntaje Disppnible
    
    END IF;
  
    IF (regParamGenerPremi.NUM_PERI IS NOT NULL AND
       regParamGenerPremi.NUM_PERI > 0) THEN
      lsPeriodoHasta := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(lsPeriodoActual,
                                                               oidPais,
                                                               Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T'),
                                                               Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD'),
                                                               -1 *
                                                               NVL(regParamGenerPremi.NUM_PERI,
                                                                   0));
    ELSE
      lsPeriodoHasta := lsPeriodoActual;
    END IF;
  
    SELECT NVL(SUM(X.NUM_PUNT), 0) - lnPuntajeCanjeado -
           lnPuntajeComprometido
      INTO lnPuntajeDisponible
      FROM INC_CUENT_CORRI_PUNTO X
     WHERE X.COPA_OID_PARA_GRAL = psOidConcurso
       AND X.CLIE_OID_CLIE = oidCliente
       AND UPPER(TRIM(VAL_DESC)) <> UPPER(TRIM('Entrega de Premio'))
       AND X.PERD_OID_PERI BETWEEN regConcurso.PERD_OID_PERI_DESD AND
           Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsPeriodoHasta);
  
    IF (lnPuntajeDisponible < 0) THEN
      lnPuntajeDisponible := 0;
    END IF;
  
    /*FIN DE PUNTAJE DISPONIBLE*/
  
    psMensajeResultado := lsNombreConsultora || ',' || lnValmeta || ',' ||
                          lnPuntajeObtenido || ',' || lnPuntajeCanjeado || ',' ||
                          lnSaldoPuntos || ',' || lnPuntajeComprometido || ',' ||
                          lnPuntajeDisponible || ',' ||
                          NVL(regParamGenerPremi.NUM_PERI, 0) || ',' ||
                          NVL(regParamGenerPremi.IND_NOVA_PUNT_PREM, '0');
  
    /*Se realiza el consolidado de los premios electivos*/
    DELETE FROM INC_GTT_PREMI_ELECT;
    IF (psTipo = '0') THEN
    
      INSERT INTO INC_GTT_PREMI_ELECT
        (OID_PARA_GRAL,
         NUM_NIVE,
         NUM_PREM,
         COD_VENT_FICT,
         OID_PROD,
         DES_PROD,
         VAL_COST_PUNT,
         NUM_UNID,
         IND_PEND,
         OID_TIPO_PION,
         IND_PREM_ACUM,
         OID_CLIE,
         OID_PARA_NIVE_PREM,
         OID_LOTE_PREM_ARTI)
        SELECT psOidConcurso,
               B.NUM_NIVE,
               D.NUM_PREM,
               '' COD_VENT_FICT,
               '' OID_PROD,
               D.VAL_DESC_LOTE_PREM_ARTI DES_PROD,
               NVL(B.NUM_CANT_FIJA_PUNT, NVL(B.NUM_CANT_INIC_PUNT, 0)) AS COS_PUNT,
               C.NUM_UNID,
               '1' IND_PEND,
               regParamGenerPremi.TPRM_OID_TIPO_PION,
               regParamGenerPremi.IND_PREM_ACUM_NIVE,
               oidCliente,
               B.OID_PARA_NIVE_PREM,
               D.OID_LOTE_PREM_ARTI
          FROM INC_PARAM_GENER_PREMI A,
               INC_PARAM_NIVEL_PREMI B,
               INC_PREMI_ARTIC       C,
               INC_LOTE_PREMI_ARTIC  D
         WHERE A.OID_PARA_GENE_PREM = B.PAGP_OID_PARA_GENE_PREM
           AND B.OID_PARA_NIVE_PREM = C.PANP_OID_PARA_NIVE_PREM
           AND C.OID_PREM_ARTI = D.PRAR_OID_PREM_ARTI
           AND D.PRAR_OID_PREM_ARTI = C.OID_PREM_ARTI
           AND B.VAL_NIVE_SELE = '1'
           AND A.COPA_OID_PARA_GRAL = psOidConcurso;
    ELSE
      --digitacion
      INSERT INTO INC_GTT_PREMI_ELECT
        (OID_PARA_GRAL,
         NUM_NIVE,
         NUM_PREM,
         COD_VENT_FICT,
         OID_PROD,
         DES_PROD,
         VAL_COST_PUNT,
         NUM_UNID,
         IND_PEND,
         OID_TIPO_PION,
         IND_PREM_ACUM,
         OID_CLIE,
         OID_PARA_NIVE_PREM,
         OID_LOTE_PREM_ARTI)
        SELECT psOidConcurso,
               B.NUM_NIVE,
               D.NUM_PREM,
               E.COD_VENT_FICT,
               E.PROD_OID_PROD,
               pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                              E.PROD_OID_PROD,
                                              'MAE_PRODU') DES_PROD,
               NVL(B.NUM_CANT_FIJA_PUNT, NVL(B.NUM_CANT_INIC_PUNT, 0)) AS COS_PUNT,
               E.NUM_UNID,
               '1' IND_PEND,
               regParamGenerPremi.TPRM_OID_TIPO_PION,
               regParamGenerPremi.IND_PREM_ACUM_NIVE,
               oidCliente,
               B.OID_PARA_NIVE_PREM,
               D.OID_LOTE_PREM_ARTI
          FROM INC_PARAM_GENER_PREMI A,
               INC_PARAM_NIVEL_PREMI B,
               INC_PREMI_ARTIC       C,
               INC_LOTE_PREMI_ARTIC  D,
               INC_ARTIC_LOTE        E
         WHERE A.OID_PARA_GENE_PREM = B.PAGP_OID_PARA_GENE_PREM
           AND B.OID_PARA_NIVE_PREM = C.PANP_OID_PARA_NIVE_PREM
           AND C.OID_PREM_ARTI = D.PRAR_OID_PREM_ARTI
           AND D.OID_LOTE_PREM_ARTI = E.LOPA_OID_LOTE_PREM_ARTI
           AND B.VAL_NIVE_SELE = '1'
           AND A.COPA_OID_PARA_GRAL = psOidConcurso;
    
      --elimnamod si el numero de premios contine mas de un codigo de venta
      BEGIN
        SELECT NUM_NIVE, NUM_PREM
          INTO lnNumNivel, lnNumPremio
          FROM INC_GTT_PREMI_ELECT X
         GROUP BY NUM_NIVE, NUM_PREM
        HAVING COUNT(1) > 1;
      
        primerElemento := TRUE;
        FOR cPremio IN cursorPremioElectivo(lnNumNivel, lnNumPremio) LOOP
        
          IF (primerElemento = FALSE) THEN
          
            UPDATE INC_GTT_PREMI_ELECT X
               SET X.IND_ANUL      = '1',
                   X.COD_VENT_AUXI = codigoVentaAuxiliar,
                   X.IND_ELEG      = '0'
             WHERE NUM_NIVE = lnNumNivel
               AND NUM_PREM = lnNumPremio
               AND COD_VENT_FICT = cPremio.COD_VENT_FICT;
          ELSE
            codigoVentaAuxiliar := cPremio.COD_VENT_FICT;
            primerElemento      := FALSE; --YA NO ERES PRIMER ELEMNTO
          END IF;
        
        END LOOP;
      
      EXCEPTION
        --si no hay mas de un cod venta para un premio
        WHEN OTHERS THEN
          NULL;
      END;
    
    END IF;
  
    /*actualizamos si el tipo de premiacion es x bolsa */
    /* actualizamos el tempral d epremios electivos que sera mostrado por pantalla*/
  
    --Se reccore el cursos de premios elegidos paraa ctulaizr los electivos
    FOR cPremioElegidos IN cursorPremioElegidos LOOP
    
      SELECT COUNT(1)
        INTO lnExisteActualizado
        FROM INC_GTT_PREMI_ELECT T
       WHERE T.OID_PARA_GRAL = cPremioElegidos.oidConcursoGral
         AND T.NUM_NIVE = cPremioElegidos.numeroNivel
         AND T.NUM_PREM = cPremioElegidos.numPremio
         AND T.IND_ACTU = '1'
         AND T.IND_ANUL IS NULL;
    
      IF (lnExisteActualizado = 0) THEN
      
        UPDATE INC_GTT_PREMI_ELECT T
           SET T.NUM_PREM = cPremioElegidos.numPremio,
               T.IND_ELEG = '1',
               T.IND_PEND = cPremioElegidos.indPendiente,
               T.IND_ACTU = '1',
               T.NUM_UNID = cPremioElegidos.numUnidades
         WHERE T.OID_PARA_GRAL = cPremioElegidos.oidConcursoGral
           AND T.NUM_NIVE = cPremioElegidos.numeroNivel
           AND T.NUM_PREM = cPremioElegidos.numPremio
           AND T.IND_ANUL IS NULL;
      ELSE
        --SE REGISTAR EL DESGLOSADO
        INSERT INTO INC_GTT_PREMI_ELECT
          (OID_PARA_GRAL,
           NUM_NIVE,
           NUM_PREM,
           COD_VENT_FICT,
           OID_PROD,
           DES_PROD,
           VAL_COST_PUNT,
           NUM_UNID,
           IND_PEND,
           OID_TIPO_PION,
           IND_PREM_ACUM,
           OID_CLIE,
           OID_PARA_NIVE_PREM,
           IND_ELEG,
           OID_LOTE_PREM_ARTI)
          SELECT OID_PARA_GRAL,
                 NUM_NIVE,
                 NUM_PREM,
                 COD_VENT_FICT,
                 OID_PROD,
                 DES_PROD,
                 VAL_COST_PUNT,
                 cPremioElegidos.numUnidades,
                 cPremioElegidos.indPendiente,
                 OID_TIPO_PION,
                 IND_PREM_ACUM,
                 OID_CLIE,
                 OID_PARA_NIVE_PREM,
                 IND_ELEG,
                 OID_LOTE_PREM_ARTI
            FROM INC_GTT_PREMI_ELECT Z
           WHERE Z.OID_PARA_GRAL = cPremioElegidos.oidConcursoGral
             AND Z.NUM_NIVE = cPremioElegidos.numeroNivel
             AND Z.NUM_PREM = cPremioElegidos.numPremio
             AND Z.IND_ANUL IS NULL;
      
      END IF;
    
    END LOOP;
  
    --Se actualiza los niveles diferidos
    UPDATE INC_GTT_PREMI_ELECT
       SET IND_NIVE_DIFE = 1
     WHERE NUM_NIVE IN (SELECT P.NUM_NIVE
                          FROM INC_SOLIC_PREMI_RETEN R,
                               INC_PARAM_NIVEL_PREMI p,
                               INC_GTT_PREMI_ELECT   g
                         WHERE R.OID_CLIE = oidCliente
                           AND R.OID_PARA_GRAL = psOidConcurso
                           AND R.OID_PARA_NIVE_PREM = P.OID_PARA_NIVE_PREM
                           AND IND_ATEN = 0
                           AND R.OID_PARA_GRAL = g.OID_PARA_GRAL
                           AND P.NUM_NIVE = g.NUM_NIVE
                           AND R.NUM_PREM = g.NUM_PREM
                           AND g.IND_PEND = 1);
  
    --CASO BOLSA DE PREMIOS Y PANTALLA DE SELECCION
    --INSERTAR LINEAS AQUELLAS QUE TIENE INDICADOR DESPACHO = 0
    -- SON REGISTROS POR DEFAULT , DEBIDO A QUE YA SON HAN SIDO DESPACHADOS
  
    IF (regParamGenerPremi.TPRM_OID_TIPO_PION = '1' AND psTipo = '0') THEN
    
      INSERT INTO INC_GTT_PREMI_ELECT
        (OID_PARA_GRAL,
         NUM_NIVE,
         NUM_PREM,
         COD_VENT_FICT,
         OID_PROD,
         DES_PROD,
         VAL_COST_PUNT,
         NUM_UNID,
         IND_PEND,
         OID_TIPO_PION,
         IND_PREM_ACUM,
         OID_CLIE,
         OID_PARA_NIVE_PREM,
         IND_ELEG,
         OID_LOTE_PREM_ARTI)
        SELECT OID_PARA_GRAL,
               NUM_NIVE,
               NUM_PREM,
               COD_VENT_FICT,
               OID_PROD,
               DES_PROD,
               VAL_COST_PUNT,
               1,
               '1' IND_PEND,
               OID_TIPO_PION,
               IND_PREM_ACUM,
               OID_CLIE,
               OID_PARA_NIVE_PREM,
               '0' IND_ELEG,
               OID_LOTE_PREM_ARTI
          FROM INC_GTT_PREMI_ELECT Z
         WHERE Z.IND_ANUL IS NULL
           AND Z.IND_PEND = '0'
           AND NOT EXISTS (SELECT a.IND_PEND
                  FROM INC_GTT_PREMI_ELECT a
                 WHERE a.OID_PARA_GRAL = z.OID_PARA_GRAL
                   AND a.NUM_NIVE = z.NUM_NIVE
                   AND a.NUM_PREM = z.NUM_PREM
                   AND a.IND_PEND = '1'
                   AND a.IND_ANUL IS NULL);
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_VALID_PREMI_ELECT: ' ||
                              ls_sqlerrm);
  END INC_PR_VALID_PREMI_ELECT;

  /**************************************************************************
  Descripcion : Valida las clasificaciones del cliente en las clasificaciones del concurso
  Fecha Creacion : 16/07/2009
  Parametros Entrada :
  psOidConcurso : oidConcurso
  psOidCliente :oidCliente
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CLASI_CONCU(psOidConcurso NUMBER,
                                    psOidCliente  NUMBER) RETURN VARCHAR2 IS
  
    CURSOR cursorClasifCliente(lsOidClasificacion     VARCHAR2,
                               lsOidTipoClasificacion VARCHAR2,
                               lsOidTipoCliente       VARCHAR2,
                               lsOidSubTipoCliente    VARCHAR2) IS
      SELECT COUNT(1) AS lnCont
        FROM MAE_CLIEN_CLASI X, MAE_CLIEN_TIPO_SUBTI Y
       WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
         AND Y.CLIE_OID_CLIE = psOidCliente
         AND Y.IND_PPAL = '1'
         AND (lsOidClasificacion IS NULL OR
             X.CLAS_OID_CLAS = lsOidClasificacion)
         AND (lsOidTipoClasificacion IS NULL OR
             X.TCCL_OID_TIPO_CLASI = lsOidTipoClasificacion)
         AND (lsOidTipoCliente IS NULL OR
             Y.TICL_OID_TIPO_CLIE = lsOidTipoCliente)
         AND (lsOidSubTipoCliente IS NULL OR
             Y.SBTI_OID_SUBT_CLIE = lsOidSubTipoCliente);
  
    CURSOR cursorClasifConcurso IS
      SELECT C.*, NVL(A.IND_EXCL, 0) IND_EXCL
        FROM INC_CLASI_PARTI_CONCU A,
             INC_PARTI_CONCU_CABEC B,
             INC_PARTI_CONCU_DETAL C
       WHERE A.COPA_OID_PARA_GRAL = psOidConcurso
         AND B.OID_PART_CONC_CABE = A.PACI_OID_PART_CONC_CABE
         AND C.PACI_OID_PART_CONC_CABE = B.OID_PART_CONC_CABE;
  
    lnOidClasificacion NUMBER;
    regConcursoDetalle INC_PARTI_CONCU_DETAL%ROWTYPE;
    lbCumplio          BOOLEAN;
  BEGIN
  
    FOR cClasifConcurso IN cursorClasifConcurso LOOP
      --recorremos las clasificaciones del concurso para encontralas en las
      --clasificaciones del cliente la validacion puede ser viceversa
      FOR cClasifCliente IN cursorClasifCliente(cClasifConcurso.CLAS_OID_CLAS,
                                                cClasifConcurso.TCCL_OID_TIPO_CLAS,
                                                cClasifConcurso.TICL_OID_TIPO_CLIE,
                                                cClasifConcurso.SBTI_OID_SUBT_CLIE) LOOP
        IF (cClasifCliente.lnCont > 0) THEN
          lbCumplio := TRUE;
        
          IF (cClasifConcurso.IND_EXCL = 1) THEN
            --Es de Exclusion
            RETURN 'Consultora tiene una exclusión requerida por el concurso';
          END IF;
        END IF;
      END LOOP;
    
    END LOOP;
  
    IF (lbCumplio) THEN
      RETURN '1';
    END IF;
  
    --CLIENTE NO SE ENCUENTRA EN LA CLASIFICACION DEL CONCURSO
    BEGIN
      BEGIN
        SELECT C.*
          INTO regConcursoDetalle
          FROM INC_CLASI_PARTI_CONCU A,
               INC_PARTI_CONCU_CABEC B,
               INC_PARTI_CONCU_DETAL C
         WHERE A.COPA_OID_PARA_GRAL = psOidConcurso
           AND B.OID_PART_CONC_CABE = A.PACI_OID_PART_CONC_CABE
           AND C.PACI_OID_PART_CONC_CABE = B.OID_PART_CONC_CABE
           AND NVL(A.IND_EXCL, 0) <> 1
           AND ROWNUM = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          RETURN '1';
      END;
    
      IF (regConcursoDetalle.CLAS_OID_CLAS IS NOT NULL) THEN
        --NO TIENE CLASIFICACION
        RETURN 'La consultora no tiene la clasificación ' || UPPER(pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                                                                                  regConcursoDetalle.CLAS_OID_CLAS,
                                                                                                  'MAE_CLASI')) || ' requerida por el concurso';
      
      END IF;
    
      IF (regConcursoDetalle.TCCL_OID_TIPO_CLAS IS NOT NULL) THEN
        --NO TIENE TIPO CLASIFICACION
        RETURN 'La consultora no tiene tipo clasificación ' || UPPER(pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                                                                                    regConcursoDetalle.TCCL_OID_TIPO_CLAS,
                                                                                                    'MAE_TIPO_CLASI_CLIEN')) || ' requerida por el concurso';
      END IF;
    
      IF (regConcursoDetalle.TICL_OID_TIPO_CLIE IS NOT NULL) THEN
        --NO TIENE TIPO CLASI CLIENTE
        RETURN 'La consultora no tiene tipo clasificación cliente ' || UPPER(pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                                                                                            regConcursoDetalle.TICL_OID_TIPO_CLIE,
                                                                                                            'MAE_TIPO_CLIEN')) || ' requerida por el concurso';
      END IF;
    
      IF (regConcursoDetalle.SBTI_OID_SUBT_CLIE IS NOT NULL) THEN
        --NO TIENE SUB TIPO CLASI CLIENTE
        RETURN 'La consultora no tiene subtipo clasificación cliente ' || UPPER(pq_apl_aux.Valor_Gen_I18n_Sicc(1,
                                                                                                               regConcursoDetalle.SBTI_OID_SUBT_CLIE,
                                                                                                               'MAE_SUBTI_CLIEN')) || ' requerida por el concurso';
      END IF;
    
      RETURN 'La consultora no pertence a la clasificación del concurso';
    
    EXCEPTION
      WHEN OTHERS THEN
        RETURN 'La consultora no pertence a la clasificación del concurso';
    END;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CLASI_CONCU: ' ||
                              ls_sqlerrm);
      RETURN 0;
  END INC_FN_VALID_CLASI_CONCU;

  /**************************************************************************
  Descripcion : Valida el Ambito Geografico del cliente en el ambito geografico del concurso,se ingrsa a esta validacion
  Siempre y cuando el concurso tenga Ambito
  Fecha Creacion : 09/11/2009
  Parametros Entrada :
  psOidConcurso : oidConcurso
  psOidCliente :oidCliente
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_CONCU(psOidConcurso NUMBER,
                                          psOidCliente  NUMBER)
    RETURN VARCHAR2 IS
  
    /* Creando el tipo de dato territorio */
    TYPE cTerritorio IS RECORD(
      OID_TERR_ADMI ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE,
      OID_TERR      ZON_TERRI.OID_TERR%TYPE,
      COD_TERR      ZON_TERRI.COD_TERR%TYPE,
      OID_SECC      ZON_SECCI.OID_SECC%TYPE,
      COD_SECC      ZON_SECCI.COD_SECC%TYPE,
      OID_ZONA      ZON_ZONA.OID_ZONA%TYPE,
      COD_ZONA      ZON_ZONA.COD_ZONA%TYPE,
      OID_REGI      ZON_REGIO.OID_REGI%TYPE,
      COD_REGI      ZON_REGIO.COD_REGI%TYPE,
      OID_SUBG_VENT INC_AMBIT_GEOGR.ZSGV_OID_SUBG_VENT%TYPE);
  
    tmpTerritorio cTerritorio;
    lnCont        NUMBER;
  
  BEGIN
  
    --Obtenemos la Unidad Aministrativa Actual
    SELECT E.OID_TERR_ADMI,
           F.OID_TERR,
           F.COD_TERR,
           G.OID_SECC,
           G.COD_SECC,
           H.OID_ZONA,
           H.COD_ZONA,
           I.OID_REGI,
           I.COD_REGI,
           I.ZSGV_OID_SUBG_VENT OID_SUBG_VENT
      INTO tmpTerritorio
      FROM MAE_CLIEN             A,
           MAE_CLIEN_UNIDA_ADMIN D,
           ZON_TERRI_ADMIN       E,
           ZON_TERRI             F,
           ZON_SECCI             G,
           ZON_ZONA              H,
           ZON_REGIO             I
     WHERE A.OID_CLIE = psOidCliente
       AND A.OID_CLIE = D.CLIE_OID_CLIE
       AND D.IND_ACTI = '1'
       AND D.ZTAD_OID_TERR_ADMI = E.OID_TERR_ADMI
       AND E.TERR_OID_TERR = F.OID_TERR
       AND E.ZSCC_OID_SECC = G.OID_SECC
       AND G.ZZON_OID_ZONA = H.OID_ZONA
       AND H.ZORG_OID_REGI = I.OID_REGI;
  
    SELECT COUNT(1)
      into lnCont
      FROM INC_AMBIT_GEOGR geo
     WHERE geo.COPA_OID_PARA_GRAL = psOidConcurso --gen.oid_para_gral
       AND (geo.ZORG_OID_REGI IS NULL OR
           geo.ZORG_OID_REGI = tmpTerritorio.OID_REGI)
       AND (geo.ZZON_OID_ZONA IS NULL OR
           geo.ZZON_OID_ZONA = tmpTerritorio.OID_ZONA)
       AND (geo.ZSCC_OID_SECC IS NULL OR
           geo.ZSCC_OID_SECC = tmpTerritorio.OID_SECC)
       AND (geo.TERR_OID_TERR IS NULL OR
           geo.TERR_OID_TERR = tmpTerritorio.OID_TERR)
       AND (geo.ZSGV_OID_SUBG_VENT IS NULL OR
           geo.ZSGV_OID_SUBG_VENT = tmpTerritorio.OID_SUBG_VENT);
  
    IF (lnCont > 0) THEN
      RETURN '1'; --SE ENCONTRO CLIENTE EN EL AMBITO DEL CONCURSO
    END IF;
  
    RETURN '0';
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_AMBIT_GEOGR_CONCU: ' ||
                              ls_sqlerrm);
      RETURN '0';
  END INC_FN_VALID_AMBIT_GEOGR_CONCU;

  /**********************************************************************************
  Descripcion : Genera la data para el Reporte de Proyecciones de Permisos de Concurso
  Siempre y cuando el concurso tenga Ambito
  Fecha Creacion : 01/12/2009
  Parametros Entrada :
  psNumeroConcurso : Numero de Concurso
  psCodigoPais : Codigo de Pais
  Autor : Jesse Rios
  ************************************************************************************/
  PROCEDURE inc_pr_repor_proye_premi_concu(psnumeroconcurso VARCHAR2,
                                           pscodigopais     VARCHAR2) IS
    CURSOR c_niveles IS
      SELECT c.oid_para_gral,
             c.num_nive,
             c.num_cant_inic_punt,
             c.num_cant_fina_punt
        FROM INC_REPOR_PROY_CONC c;
  
    CURSOR c_interfaz(v_oid_para_gral      NUMBER,
                      v_num_nive           NUMBER,
                      v_num_cant_inic_punt NUMBER,
                      v_num_cant_fina_punt NUMBER,
                      montoBaseDefecto     NUMBER) IS
      SELECT t.clie_oid_clie,
             t.copa_oid_para_gral,
             t.cod_subg_vent,
             t.des_subg_vent,
             t.cod_regi,
             t.des_regi,
             t.cod_zona,
             t.des_zona,
             t.puntaje,
             t.meta,
             (t.puntaje - t.meta) puntos,
             pnp.num_nive
        FROM (SELECT ccp.clie_oid_clie,
                     ccp.copa_oid_para_gral,
                     sgv.cod_subg_vent,
                     sgv.des_subg_vent,
                     r.cod_regi,
                     r.des_regi,
                     z.cod_zona,
                     z.des_zona,
                     CASE
                       WHEN (SELECT COUNT(Oid_Para_Cali)
                               FROM INC_PARAM_CALIF
                              WHERE COPA_OID_PARA_GRAL =
                                    ccp.copa_oid_para_gral) = 1 THEN
                        DECODE(mtv.val_meta,
                               null,
                               montoBaseDefecto,
                               trunc(mtv.val_meta))
                       ELSE
                        0
                     END meta,
                     SUM(ccp.num_punt) puntaje
                FROM inc_cuent_corri_punto ccp,
                     mae_clien_unida_admin cua,
                     zon_terri_admin       ta,
                     zon_secci             s,
                     zon_zona              z,
                     zon_regio             r,
                     zon_sub_geren_venta   sgv,
                     inc_metas_tipo_venta  mtv
               WHERE ccp.copa_oid_para_gral = v_oid_para_gral
                 AND ccp.val_desc NOT LIKE '%Entrega de Premio%'
                 AND ccp.clie_oid_clie = cua.clie_oid_clie
                 AND cua.ind_acti = 1
                 AND cua.ztad_oid_terr_admi = ta.oid_terr_admi
                 AND ta.zscc_oid_secc = s.oid_secc
                 AND s.zzon_oid_zona = z.oid_zona
                 AND z.zorg_oid_regi = r.oid_regi
                 AND r.zsgv_oid_subg_vent = sgv.oid_subg_vent
                 AND ccp.copa_oid_para_gral = mtv.copa_oid_para_gral(+)
                 AND ccp.clie_oid_clie = mtv.clie_oid_clie(+)
               GROUP BY ccp.clie_oid_clie,
                        ccp.copa_oid_para_gral,
                        sgv.cod_subg_vent,
                        sgv.des_subg_vent,
                        r.cod_regi,
                        r.des_regi,
                        z.cod_zona,
                        z.des_zona,
                        mtv.val_meta) t,
             inc_concu_param_gener cpg,
             inc_param_gener_premi pgp,
             inc_param_nivel_premi pnp
       WHERE t.copa_oid_para_gral = cpg.oid_para_gral
         AND cpg.oid_para_gral = pgp.copa_oid_para_gral
         AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
         AND pnp.num_nive = v_num_nive
         AND v_num_cant_inic_punt <= (t.puntaje - t.meta)
         AND v_num_cant_fina_punt >= (t.puntaje - t.meta);
  
    TYPE interfazcab IS RECORD(
      clie_oid_clie      inc_cuent_corri_punto.clie_oid_clie%TYPE,
      copa_oid_para_gral inc_cuent_corri_punto.copa_oid_para_gral%TYPE,
      cod_subg_vent      zon_sub_geren_venta.cod_subg_vent%TYPE,
      des_subg_vent      zon_sub_geren_venta.des_subg_vent%TYPE,
      cod_regi           zon_regio.cod_regi%TYPE,
      des_regi           zon_regio.des_regi%TYPE,
      cod_zona           zon_zona.cod_zona%TYPE,
      des_zona           zon_zona.des_zona%TYPE,
      puntaje            NUMBER,
      meta               inc_metas_tipo_venta.val_meta%TYPE,
      puntos             NUMBER,
      num_nive           inc_param_nivel_premi.num_nive%TYPE);
  
    TYPE nivelcab IS RECORD(
      oid_para_gral      INC_REPOR_PROY_CONC.oid_para_gral%TYPE,
      num_nive           INC_REPOR_PROY_CONC.num_nive%TYPE,
      num_cant_inic_punt INC_REPOR_PROY_CONC.num_cant_inic_punt%TYPE,
      num_cant_fina_punt INC_REPOR_PROY_CONC.num_cant_fina_punt%TYPE);
  
    TYPE nivelcabtab IS TABLE OF nivelcab;
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
    nivelrecord    nivelcabtab;
  
    w_filas NUMBER := 1000;
  
    valorniveles NUMBER;
  
    v_montoBaseDefecto NUMBER;
  
    v_oidConcurso NUMBER;
  
  BEGIN
    --Primero Insertamos Todos Los niveles del concurso
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_REPOR_PROY_CONC';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_REPOR_PROY_CLIEN';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_REPOR_PROY_PREM';
  
    /*Se recuperan Datos del Concurso y los niveles que tiene el concurso*/
    INSERT INTO INC_REPOR_PROY_CONC
      (peri_desd,
       peri_hast,
       perd_oid_peri_desd,
       perd_oid_peri_hast,
       val_nomb,
       ind_prem_acum_nive,
       val_hast_nive,
       num_nive,
       punt_nive,
       val_nive_sele,
       oid_para_nive_prem,
       num_conc,
       oid_para_gral,
       ind_nive_rota,
       num_cant_inic_punt,
       num_cant_fina_punt,
       num_rota)
      SELECT Gen_Pkg_Gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_desd) AS peri_desd,
             Gen_Pkg_Gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_hast) AS peri_hast,
             cpg.perd_oid_peri_desd,
             cpg.perd_oid_peri_hast,
             cpg.val_nomb,
             pgp.ind_prem_acum_nive,
             pgp.val_hast_nive,
             pnp.num_nive,
             DECODE(pnp.num_cant_fija_punt,
                    0,
                    pnp.num_cant_inic_punt,
                    NULL,
                    pnp.num_cant_inic_punt,
                    pnp.num_cant_fija_punt) AS punt_nive,
             pnp.val_nive_sele,
             pnp.oid_para_nive_prem,
             cpg.num_conc,
             cpg.oid_para_gral,
             pgp.ind_nive_rota,
             pnp.num_cant_inic_punt,
             pnp.num_cant_fina_punt,
             pgp.num_rota
        FROM inc_concu_param_gener cpg,
             inc_param_gener_premi pgp,
             inc_param_nivel_premi pnp
       WHERE pgp.copa_oid_para_gral = cpg.oid_para_gral
         AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
         AND cpg.num_conc = NVL(psnumeroconcurso, cpg.num_conc);
  
    /*
    * Segundo insertamos los clientes que esten en el nivel seleccionado
    * por el sistema
    */
  
    OPEN c_niveles;
  
    LOOP
      FETCH c_niveles BULK COLLECT
        INTO nivelrecord LIMIT w_filas;
      IF nivelrecord.COUNT > 0 THEN
        FOR j IN nivelrecord.FIRST .. nivelrecord.LAST LOOP
        
          --Obtenemos el Monto por Defecto
          BEGIN
            select imp_mont_mini
              into v_montoBaseDefecto
              from inc_monto_minim_rango_consu
             where estv_oid_esta_vent in
                   (select oid_esta_vent
                      from inc_estat_venta
                     where esta_oid_esta_clie =
                           (select oid_esta_clie
                              from mae_estat_clien
                             where cod_esta_clie = '02'))
               and clpc_oid_clas_part_cali in
                   (select oid_clas_part_cali
                      from inc_clasi_parti_calif
                     where copa_oid_para_gral = nivelrecord(j).oid_para_gral);
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              v_montoBaseDefecto := 0;
          END;
        
          OPEN c_interfaz(nivelrecord       (j).oid_para_gral,
                          nivelrecord       (j).num_nive,
                          nivelrecord       (j).num_cant_inic_punt,
                          nivelrecord       (j).num_cant_fina_punt,
                          v_montoBaseDefecto);
        
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
          
            IF interfazrecord.COUNT > 0 THEN
              FOR i IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
                INSERT INTO INC_REPOR_PROY_CLIEN
                  (clie_oid_clie,
                   copa_oid_para_gral,
                   cod_sub_geren_vent,
                   des_sub_geren_vent,
                   cod_regi,
                   des_regi,
                   cod_zona,
                   des_zona,
                   num_nive,
                   punt_clie,
                   VAL_META,
                   VAL_PUNT_CCPU)
                VALUES
                  (interfazrecord(i).clie_oid_clie,
                   interfazrecord(i).copa_oid_para_gral,
                   interfazrecord(i).cod_subg_vent,
                   interfazrecord(i).des_subg_vent,
                   interfazrecord(i).cod_regi,
                   interfazrecord(i).des_regi,
                   interfazrecord(i).cod_zona,
                   interfazrecord(i).des_zona,
                   interfazrecord(i).num_nive,
                   interfazrecord(i).puntos,
                   interfazrecord(i).meta,
                   interfazrecord(i).puntaje);
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        END LOOP;
      
      END IF;
      EXIT WHEN c_niveles%NOTFOUND;
    END LOOP;
  
    CLOSE c_niveles;
  
    /*
    * Invocamos al Store que determinara finalmente que consultoras son las
    * ganadoras y que premios reciben
    */
    Inc_Pkg_Repor_Incen.inc_pr_gener_proye_premi_concu(pscodigopais);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REPOR_PROYE_PREMI_CONCU: ' ||
                              ls_sqlerrm);
    
  END inc_pr_repor_proye_premi_concu;

  /**************************************************************************
  Descripcion       : Retener Solicitudes de Premiacion de Niveles Electivos
  Fecha Creacion    : 04/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_RETEN_SOLIC_PREMI_ELECT(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZona       VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnTotal       NUMBER(12);
    lnOidEstadoLD BEL_ESTAD_MERCA.OID_ESTA_MERC%TYPE;
    lnOidEstadoAS BEL_ESTAD_MERCA.OID_ESTA_MERC%TYPE;
  
    lnOidPeriodoIni CRA_PERIO.OID_PERI%TYPE;
    ldFechaInicio   CRA_PERIO.FEC_INIC%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT psc.OID_SOLI_CABE,
             psc.CLIE_OID_CLIE,
             conc.OID_PARA_GRAL,
             conc.OID_PARA_NIVE_PREM,
             conc.NUM_PREM,
             psc.PERD_OID_PERI,
             psc.FEC_PROG_FACT,
             psc.ALMC_OID_ALMA
        FROM PED_SOLIC_CABEC psc,
             PED_TIPO_SOLIC_PAIS tsp,
             (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SINC') ts,
             ZON_ZONA zon,
             (SELECT c.OID_PARA_GRAL,
                     p.OID_PARA_NIVE_PREM,
                     p.NUM_NIVE,
                     l.NUM_PREM
                FROM INC_CONCU_PARAM_GENER c,
                     INC_NIVEL_DESPA_DIFER d,
                     INC_PARAM_GENER_PREMI g,
                     INC_PARAM_NIVEL_PREMI p,
                     INC_PREMI_ARTIC       q,
                     INC_LOTE_PREMI_ARTIC  l
               WHERE d.COD_PAIS = psCodigoPais
                 AND d.COD_PERI_INIC > psCodigoPeriodo
                 AND d.NUM_CONC = c.NUM_CONC
                 AND g.COPA_OID_PARA_GRAL = c.OID_PARA_GRAL
                 AND p.PAGP_OID_PARA_GENE_PREM = g.OID_PARA_GENE_PREM
                 AND d.NUM_NIVE = p.NUM_NIVE
                 AND p.OID_PARA_NIVE_PREM = q.PANP_OID_PARA_NIVE_PREM
                 AND q.OID_PREM_ARTI = l.PRAR_OID_PREM_ARTI) conc
      
       WHERE psc.PAIS_OID_PAIS = oidPais
         AND psc.PERD_OID_PERI = oidPeriodo
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND psc.FEC_FACT IS NULL
         AND psc.ZZON_OID_ZONA = zon.OID_ZONA
         AND psc.ESSO_OID_ESTA_SOLI = 1
         AND psc.COPA_OID_PARA_GENE = conc.OID_PARA_GRAL
         AND psc.NUM_PREM = conc.NUM_PREM;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud       PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente         MAE_CLIEN.OID_CLIE%TYPE,
      oidConcurso        INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      oidNivelPremio     INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
      numeroPremio       INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE,
      oidPeriodo         PED_SOLIC_CABEC.PERD_OID_PERI%TYPE,
      fecProgFacturacion PED_SOLIC_CABEC.FEC_PROG_FACT%TYPE,
      oidAlmacen         PED_SOLIC_CABEC.ALMC_OID_ALMA%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Verificamos si Existe Concurso con Despacho Diferido x Niveles
    SELECT COUNT(1)
      INTO lnTotal
      FROM INC_NIVEL_DESPA_DIFER
     WHERE COD_PAIS = psCodigoPais
       AND COD_PERI_INIC > psCodigoPeriodo;
  
    IF (lnTotal = 0) THEN
      RETURN;
    END IF;
  
    --Recuperamos los Oids de Estado de Mercaderia de: LD : Libre Disponibilidad, AS : Asignado
    SELECT OID_ESTA_MERC
      INTO lnOidEstadoLD
      FROM BEL_ESTAD_MERCA
     WHERE COD_ESTA = 'LD';
    SELECT OID_ESTA_MERC
      INTO lnOidEstadoAS
      FROM BEL_ESTAD_MERCA
     WHERE COD_ESTA = 'AS';
  
    --Recuperamos el periodo inicial y la fecha de Inicio
    lnOidPeriodoIni := INC_FN_OBTIE_PERIO_INICI(psCodigoPais,
                                                psCodigoMarca,
                                                psCodigoCanal);
  
    SELECT FEC_INIC
      INTO ldFechaInicio
      FROM CRA_PERIO
     WHERE OID_PERI = lnOidPeriodoIni;
  
    --(1) PROCESAMOS A LOS PEDIDOS QUE VAN A SER RETENIDAS
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Actualiza la Solicitud para que no sea procesada
          UPDATE PED_SOLIC_CABEC
             SET PERD_OID_PERI      = lnOidPeriodoIni,
                 FEC_PROG_FACT      = ldFechaInicio,
                 ESSO_OID_ESTA_SOLI = 6,
                 GRPR_OID_GRUP_PROC = 3
           WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
        
          --Insertamos en la tabla INC_SOLIC_PREMI_RETEN
          INSERT INTO INC_SOLIC_PREMI_RETEN
            (OID_SOLI_CABE,
             OID_CLIE,
             OID_PARA_GRAL,
             OID_PARA_NIVE_PREM,
             NUM_PREM,
             OID_PERI_PROC,
             FEC_PROG_FACT,
             FEC_RETE,
             IND_ATEN)
          VALUES
            (interfazRecordN(x).oidSolicitud,
             interfazRecordN(x).oidCliente,
             interfazRecordN(x).oidConcurso,
             interfazRecordN(x).oidNivelPremio,
             interfazRecordN(x).numeroPremio,
             interfazRecordN(x).oidPeriodo,
             interfazRecordN(x).fecProgFacturacion,
             SYSDATE,
             0);
        
          --Actualizamos en la tabla IND_PREMI_ELEGI
          UPDATE INC_PREMI_ELEGI
             SET IND_PEND = 1
           WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
             AND NUM_PREM = interfazRecordN(x).numeroPremio
             AND IND_PEND = 0;
        
          --Recuperamos los productos y unidades comprometidas del detalle del Pedido
          FOR y IN (SELECT pos.OID_SOLI_POSI,
                           pos.PROD_OID_PROD,
                           pos.NUM_UNID_COMPR
                      FROM PED_SOLIC_POSIC pos
                     WHERE pos.SOCA_OID_SOLI_CABE = interfazRecordN(x)
                          .oidSolicitud) LOOP
          
            --Suma las unidades comprometidas a Libre Disponibilidad en BEL_STOCK
            UPDATE BEL_STOCK
               SET VAL_SALD = VAL_SALD + y.NUM_UNID_COMPR
             WHERE PROD_OID_PROD = y.PROD_OID_PROD
               AND ALMC_OID_ALMA = interfazRecordN(x).oidAlmacen
               AND ESME_OID_ESTA_MERC = lnOidEstadoLD;
          
            --Suma las unidades comprometidas a Asignado en BEL_STOCK
            UPDATE BEL_STOCK
               SET VAL_SALD = VAL_SALD - y.NUM_UNID_COMPR
             WHERE PROD_OID_PROD = y.PROD_OID_PROD
               AND ALMC_OID_ALMA = interfazRecordN(x).oidAlmacen
               AND ESME_OID_ESTA_MERC = lnOidEstadoAS;
          
            --Se elimina si la posicion tiene registro en la tabla de Bolsa de Faltantes
            DELETE FROM INC_BOLSA_FALTA
             WHERE SOPO_OID_SOLI_POSI = y.OID_SOLI_POSI;
          
          END LOOP;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_RETEN_SOLIC_PREMI_ELECT: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_RETEN_SOLIC_PREMI_ELECT;

  /**************************************************************************
  Descripcion       : Despachar Premios de Solicitudes Retenidas
  Fecha Creacion    : 06/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZonas      : lista de Zonas
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_DESPA_PREMI_SOLIC_RETEN(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZonas      VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnTotal                NUMBER(12);
    lnOidAlmacen           PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
    lnOidEstadoLD          BEL_ESTAD_MERCA.OID_ESTA_MERC%TYPE;
    lnOidEstadoAS          BEL_ESTAD_MERCA.OID_ESTA_MERC%TYPE;
    lnNumeroPremioEntregar INC_PREMI_ELEGI.NUM_PREM%TYPE;
  
    lnOidProducto    INC_ARTIC_LOTE.PROD_OID_PROD%TYPE;
    lnPrecioPublico  INC_ARTIC_LOTE.IMP_PREC_PUBL%TYPE;
    lsCodigoVenta    INC_ARTIC_LOTE.COD_VENT_FICT%TYPE;
    lnNumeroUnidades INC_ARTIC_LOTE.NUM_UNID%TYPE;
    lnSaldo          BEL_STOCK.VAL_SALD%TYPE;
  
    lnOidRegion ZON_REGIO.OID_REGI%TYPE;
    lnOidZona   ZON_ZONA.OID_ZONA%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT OID_SOLI_CABE,
             OID_CLIE,
             inc.OID_PARA_GRAL,
             inc.OID_PARA_NIVE_PREM,
             NUM_PREM
        FROM INC_SOLIC_PREMI_RETEN inc,
             (SELECT c.OID_PARA_GRAL, p.OID_PARA_NIVE_PREM, p.NUM_NIVE
                FROM INC_CONCU_PARAM_GENER c,
                     INC_NIVEL_DESPA_DIFER d,
                     INC_PARAM_GENER_PREMI g,
                     INC_PARAM_NIVEL_PREMI p
               WHERE d.COD_PAIS = psCodigoPais
                 AND d.COD_PERI_INIC = psCodigoPeriodo
                 AND d.NUM_CONC = c.NUM_CONC
                 AND g.COPA_OID_PARA_GRAL = c.OID_PARA_GRAL
                 AND p.PAGP_OID_PARA_GENE_PREM = g.OID_PARA_GENE_PREM
                 AND d.NUM_NIVE = p.NUM_NIVE) conc
      
       WHERE inc.OID_PARA_GRAL = conc.OID_PARA_GRAL
         AND inc.OID_PARA_NIVE_PREM = conc.OID_PARA_NIVE_PREM
            
         AND (((psIndicadorProceso = PROCESO_GP4) AND
             (INC_FN_VALID_PEDID(oidPais, oidPeriodo, OID_CLIE) = 1)) OR
             ((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
             (INSTR(psCodigoZonas, INC_FN_DEVUE_CODIG_ZONA(OID_CLIE)) > 0)))
            
         AND inc.IND_ATEN = 0;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud   PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      oidConcurso    INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      oidNivelPremio INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
      numeroPremio   INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    ltRegSolicitud tRegSolicitud;
    ltRegPosicion  tablaRegPosicion := tablaRegPosicion();
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Verificamos si Existe Concurso con Despacho Diferido x Niveles igual al Periodo de Proceso
    SELECT COUNT(1)
      INTO lnTotal
      FROM INC_NIVEL_DESPA_DIFER
     WHERE COD_PAIS = psCodigoPais
       AND COD_PERI_INIC = psCodigoPeriodo;
  
    IF (lnTotal = 0) THEN
      RETURN;
    END IF;
  
    --Recuperamos el Oid Almacen
    SELECT tsp.ALMC_OID_ALMA
      INTO lnOidAlmacen
      FROM PED_TIPO_SOLIC ts, PED_TIPO_SOLIC_PAIS tsp
     WHERE ts.COD_TIPO_SOLI = 'SINC'
       AND ts.OID_TIPO_SOLI = tsp.TSOL_OID_TIPO_SOLI;
  
    --Recuperamos los Oids de Estado de Mercaderia de: LD : Libre Disponibilidad, AS : Asignado
    SELECT OID_ESTA_MERC
      INTO lnOidEstadoLD
      FROM BEL_ESTAD_MERCA
     WHERE COD_ESTA = 'LD';
    SELECT OID_ESTA_MERC
      INTO lnOidEstadoAS
      FROM BEL_ESTAD_MERCA
     WHERE COD_ESTA = 'AS';
  
    --(1) PROCESAMOS A LOS PEDIDOS QUE VAN A SER RETENIDAS
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Accedemos a la entidad INC_PREMI_ELEGI
          BEGIN
            SELECT NUM_PREM
              INTO lnNumeroPremioEntregar
              FROM INC_PREMI_ELEGI
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
               AND PANP_OID_PARA_NIVE_PREM = interfazRecordN(x)
                  .oidNivelPremio
               AND IND_PEND = 1;
          EXCEPTION
            WHEN OTHERS THEN
              lnNumeroPremioEntregar := NULL;
          END;
        
          IF (lnNumeroPremioEntregar IS NULL) THEN
            lnNumeroPremioEntregar := interfazRecordN(x).numeroPremio;
          
          ELSE
            UPDATE INC_PREMI_ELEGI
               SET IND_PEND = 0
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
               AND PANP_OID_PARA_NIVE_PREM = interfazRecordN(x)
                  .oidNivelPremio
               AND IND_PEND = 1;
          
          END IF;
        
          --Actualiza la tabla INC_SOLIC_PREMI_RETEN
          UPDATE INC_SOLIC_PREMI_RETEN
             SET IND_ATEN           = 1,
                 NUM_PREM_NUEV      = lnNumeroPremioEntregar,
                 FEC_PROG_FACT_NUEV = TO_DATE(psFechaFacturacion,
                                              'DD/MM/YYYY'),
                 OID_PERI_NUEV      = lnOidPeriodo,
                 FEC_GENE_NUEV      = SYSDATE,
                 COD_PROC           = DECODE(psIndicadorProceso,
                                             PROCESO_GP4,
                                             'D',
                                             PROCESO_CIERRE_ZONA,
                                             'Z')
           WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
        
          --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
          ltRegSolicitud.COD_PAIS      := psCodigoPais;
          ltRegSolicitud.COD_MARC      := psCodigoMarca;
          ltRegSolicitud.COD_CANA      := psCodigoCanal;
          ltRegSolicitud.OID_CLIE      := interfazRecordN(x).oidCliente;
          ltRegSolicitud.COD_PERI      := psCodigoPeriodo;
          ltRegSolicitud.FEC_PROG_FACT := NULL; --Se calculara en la Creacion de la Solicitud
          ltRegSolicitud.COD_OPER      := 'INC030';
          ltRegSolicitud.COD_CLAS_SOLI := 'I1';
          ltRegSolicitud.COD_TIPO_SOLI := 'SIN';
          ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
          ltRegSolicitud.OID_PARA_GRAL := interfazRecordN(x).oidConcurso;
          ltRegSolicitud.NUM_PREM      := lnNumeroPremioEntregar;
          ltRegSolicitud.COD_USUA      := psCodigoUsuario;
        
          ltRegSolicitud.OID_ACCE_FISI      := NULL;
          ltRegSolicitud.OID_GRUP_PROC      := 1; --(GP1)
          ltRegSolicitud.OID_GRUP_PROC_SECU := 1; --(GP1)
          ltRegSolicitud.NUM_CLIEN          := NULL;
          ltRegSolicitud.OID_MODU           := 13; --Incentivos
          ltRegSolicitud.VAL_GLOS_OBSE      := 'TRANSFERENCIA GRATUITA';
        
          --Obtener la lista de los Premios de los articulos a Entregar
          FOR y IN (SELECT a.PROD_OID_PROD,
                           a.IMP_PREC_PUBL,
                           a.COD_VENT_FICT,
                           a.NUM_UNID,
                           r.OID_REEM_ARTI_LOTE,
                           r.PROD_OID_PROD      PROD_OID_PROD_REEM,
                           r.IMP_PREC_PUBL      IMP_PREC_PUBL_REEM,
                           r.COD_VENT_FICT      COD_VENT_FICT_REEM,
                           r.NUM_UNID           NUM_UNID_REEM,
                           r.CTRE_OID_CRIT_REEM,
                           r.VAL_CRIT_REEM
                      FROM INC_PREMI_ARTIC      p,
                           INC_LOTE_PREMI_ARTIC l,
                           INC_ARTIC_LOTE       a,
                           INC_REEMP_ARTIC_LOTE r
                     WHERE p.PANP_OID_PARA_NIVE_PREM = interfazRecordN(x)
                          .oidNivelPremio
                       AND p.OID_PREM_ARTI = l.PRAR_OID_PREM_ARTI
                       AND l.NUM_PREM = lnNumeroPremioEntregar
                       AND l.OID_LOTE_PREM_ARTI = a.LOPA_OID_LOTE_PREM_ARTI
                       AND r.ARLO_OID_ARTI_LOTE(+) = a.OID_ARTI_LOTE) LOOP
          
            --Recuperamos los Datos del Premio
            lnOidProducto    := y.PROD_OID_PROD;
            lnPrecioPublico  := y.IMP_PREC_PUBL;
            lsCodigoVenta    := y.COD_VENT_FICT;
            lnNumeroUnidades := y.NUM_UNID;
          
            --Si el Premio Articulo Tiene Reemplazo
            IF (y.OID_REEM_ARTI_LOTE IS NOT NULL) THEN
              SELECT COUNT(1)
                INTO lnTotal
                FROM INC_REEMP_REGIO_ZONA
               WHERE RARL_OID_REEM_ARTI_LOTE = y.OID_REEM_ARTI_LOTE;
            
              IF (lnTotal > 0) THEN
                --Recuperamos la Region y Zona de la Consultora
                SELECT zon.ZORG_OID_REGI, zon.OID_ZONA
                  INTO lnOidRegion, lnOidZona
                  FROM MAE_CLIEN_UNIDA_ADMIN adm,
                       ZON_TERRI_ADMIN       ter,
                       ZON_SECCI             sec,
                       ZON_ZONA              zon
                 WHERE adm.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND adm.IND_ACTI = 1
                   AND ter.OID_TERR_ADMI = adm.ZTAD_OID_TERR_ADMI
                   AND sec.OID_SECC = ter.ZSCC_OID_SECC
                   AND zon.OID_ZONA = sec.ZZON_OID_ZONA;
              
                --Verificamos si la region o zona de la consultora pertenece al
                --ambito geografico del articulo de reemplazo
                SELECT COUNT(1)
                  INTO lnTotal
                  FROM (SELECT OID_REEM_REGI_ZONA
                          FROM INC_REEMP_REGIO_ZONA
                         WHERE RARL_OID_REEM_ARTI_LOTE =
                               y.OID_REEM_ARTI_LOTE
                           AND ZZON_OID_ZONA = lnOidZona
                           AND ZZON_OID_ZONA IS NOT NULL
                        UNION
                        SELECT OID_REEM_REGI_ZONA
                          FROM INC_REEMP_REGIO_ZONA
                         WHERE RARL_OID_REEM_ARTI_LOTE =
                               y.OID_REEM_ARTI_LOTE
                           AND ZORG_OID_REGI = lnOidRegion
                           AND ZZON_OID_ZONA IS NULL);
              ELSE
                lnTotal := 1;
              END IF;
            
              IF (lnTotal = 1) THEN
                --Maneja Reemplazo
                IF (y.CTRE_OID_CRIT_REEM = 1 OR y.CTRE_OID_CRIT_REEM = 2) THEN
                
                  SELECT VAL_SALD
                    INTO lnSaldo
                    FROM BEL_STOCK
                   WHERE PROD_OID_PROD = y.PROD_OID_PROD
                     AND ALMC_OID_ALMA = lnOidAlmacen
                     AND ESME_OID_ESTA_MERC = lnOidEstadoLD;
                
                ELSIF (y.CTRE_OID_CRIT_REEM = 3) THEN
                  SELECT VAL_SALD
                    INTO lnSaldo
                    FROM BEL_STOCK
                   WHERE PROD_OID_PROD = y.PROD_OID_PROD_REEM
                     AND ALMC_OID_ALMA = lnOidAlmacen
                     AND ESME_OID_ESTA_MERC = lnOidEstadoLD;
                END IF;
              
                IF ((y.CTRE_OID_CRIT_REEM = 1 AND lnSaldo <= 0) OR
                   (y.CTRE_OID_CRIT_REEM = 2 AND
                   lnSaldo <= y.VAL_CRIT_REEM) OR
                   (y.CTRE_OID_CRIT_REEM = 3 AND lnSaldo > 0)) THEN
                
                  --Aplicamos el Producto Reemplazo
                  lnOidProducto    := y.PROD_OID_PROD_REEM;
                  lnPrecioPublico  := y.IMP_PREC_PUBL_REEM;
                  lsCodigoVenta    := y.COD_VENT_FICT_REEM;
                  lnNumeroUnidades := y.NUM_UNID * y.NUM_UNID_REEM;
                
                END IF;
              
              END IF;
            
            END IF;
          
            ltRegPosicion.EXTEND(1);
            ltRegPosicion(ltRegPosicion.LAST).OID_PROD := lnOidProducto;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := lnNumeroUnidades;
            ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
            ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := lsCodigoVenta;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := NULL;
            ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := lnPrecioPublico;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
          
          END LOOP;
        
          --CREAMOS LA SOLICITUD DE PEDIDO
          INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud,
                                   psIndicadorProceso,
                                   ltRegPosicion);
        
          --Inicializamos la lista de Posiciones de la Nueva Solicitud
          ltRegPosicion.DELETE;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_DESPA_PREMI_SOLIC_RETEN: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_DESPA_PREMI_SOLIC_RETEN;

  /**************************************************************************
  Descripcion       : Genera la Solicitud de Pedido
  Fecha Creacion    : 06/08/2010
  Parametros Entrada:
    ptSolicitud     :  Registro con Datos para la Solicitud de pais
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
    psCodigoUsuario     : Usuario que ejecuta el Proceso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_PEDID(ptSolicitud        IN OUT tRegSolicitud,
                                     psIndicadorProceso VARCHAR2,
                                     ptRegPosicion      IN OUT tablaRegPosicion) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnOidTipoPrograma INC_CONCU_PARAM_GENER.ICTP_OID_TIPO_PROG%TYPE;
  
    lnOidOperacion      BEL_OPERA.OID_OPER%TYPE;
    lnOidClaseSolicitud PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
    lnIndicadorOC       PED_CLASE_SOLIC.IND_ORDE_COMP%TYPE;
  
    lnOidTipoSoliPais       PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
    lnOidTipoPosi           PED_TIPO_SOLIC_PROCE.TPOS_OID_TIPO_POSI%TYPE;
    lnOidSubTipoPosi        PED_TIPO_SOLIC_PROCE.STPO_OID_SUBT_POSI%TYPE;
    lnOidAcceso             PED_TIPO_SOLIC.ACCE_OID_ACCE%TYPE;
    lnOidSubAcceso          PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
    lnOidTipoCliente        PED_TIPO_SOLIC.TICL_OID_TIPO_CLIE%TYPE;
    lnOidActividad          PED_TIPO_SOLIC_PAIS.CACT_OID_ACTI%TYPE;
    lnOidFormaPago          PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
    lnIndPedidoPrueba       PED_TIPO_SOLIC_PAIS.IND_PEDI_PRUE%TYPE;
    lnIndPermitirUnion      PED_TIPO_SOLIC_PAIS.IND_PERM_UNIO%TYPE;
    lnOidTipoCons           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
    lnOidTipoDocumentoLegal PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
    lnOidSociedad           PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
    lnOidMoneda             PED_TIPO_SOLIC_PAIS.MONE_OID_MONE%TYPE;
  
    lnOidEstadoSolicitud PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;
    lnOidEstadoPosicion  PED_ESTAD_POSIC.OID_ESTA_POSI%TYPE;
    lnOidProceso         PED_SECUE_PROCE.PROC_OID_PROC%TYPE;
    lnTipoCambio         PRE_MATRI_FACTU_CABEC.VAL_TIPO_CAMB%TYPE;
    lnOidTipoDespacho    PED_TIPO_DESPA.OID_TIPO_DESP%TYPE;
    lnIndCronograma      PED_TIPO_DESPA.IND_CRON%TYPE;
    lnOidAlmacen         BEL_ALMAC.OID_ALMA%TYPE;
    lsCodigoPeriodoSig   SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoDespacho CRA_PERIO.OID_PERI%TYPE;
  
    lnOidSubTipoCliente MAE_CLIEN_TIPO_SUBTI.SBTI_OID_SUBT_CLIE%TYPE;
    lnOidClienteDir     MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
    lnOidValEstruGeopo  ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP%TYPE;
  
    lnOidTipoDocumento MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
    lnOidTerriAdmin    ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
    lnOidTerritorio    ZON_TERRI.OID_TERR%TYPE;
    lnOidSeccion       ZON_SECCI.OID_SECC%TYPE;
    lnOidZona          ZON_ZONA.OID_ZONA%TYPE;
    lnOidRegion        ZON_REGIO.OID_REGI%TYPE;
    lnOidSolicitud     PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
    lnOidPosicion      PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE;
  
    ldFechaProgFacturacion DATE;
    lnContadorPosiciones   NUMBER;
  
    lsCodigoSubAcceso  SEG_SUBAC.COD_SBAC%TYPE;
    lnNumeroSolicitud  PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
    lnSolicitudFormato PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(ptSolicitud.COD_PAIS);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(ptSolicitud.COD_MARC);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(ptSolicitud.COD_CANA);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(ptSolicitud.COD_PERI,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --RECUPERAMOS INFORMACION DE LAS TABLAS NECESARIAS
    --PARA LA GENERACION DE LAS SOLICITUDES DE PEDIDO
    SELECT OID_OPER
      INTO lnOidOperacion
      FROM BEL_OPERA
     WHERE COD_OPER = ptSolicitud.COD_OPER;
  
    SELECT OID_CLAS_SOLI, IND_ORDE_COMP
      INTO lnOidClaseSolicitud, lnIndicadorOC
      FROM PED_CLASE_SOLIC
     WHERE COD_CLAS_SOLI = ptSolicitud.COD_CLAS_SOLI;
  
    --Recuperamos datos relacionado al tipo de Solicitud de Incentivos
    SELECT tsp.OID_TIPO_SOLI_PAIS,
           pro.TPOS_OID_TIPO_POSI,
           pro.STPO_OID_SUBT_POSI,
           sol.ACCE_OID_ACCE,
           sol.SBAC_OID_SBAC,
           sol.TICL_OID_TIPO_CLIE,
           tsp.CACT_OID_ACTI,
           tsp.FOPA_OID_FORM_PAGO,
           tsp.IND_PEDI_PRUE,
           tsp.IND_PERM_UNIO,
           tsp.TSOL_OID_TIPO_CONS,
           tsp.TIDO_OID_TIPO_DOCU,
           tsp.SOCI_OID_SOCI,
           tsp.MONE_OID_MONE
      INTO lnOidTipoSoliPais,
           lnOidTipoPosi,
           lnOidSubTipoPosi,
           lnOidAcceso,
           lnOidSubAcceso,
           lnOidTipoCliente,
           lnOidActividad,
           lnOidFormaPago,
           lnIndPedidoPrueba,
           lnIndPermitirUnion,
           lnOidTipoCons,
           lnOidTipoDocumentoLegal,
           lnOidSociedad,
           lnOidMoneda
      FROM PED_TIPO_SOLIC_PROCE pro,
           PED_TIPO_SOLIC_PAIS  tsp,
           PED_TIPO_SOLIC       sol,
           MAE_TIPO_CLIEN       tip
     WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
       AND pro.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
       AND sol.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
       AND tip.Cod_Tipo_Clie = ptSolicitud.COD_TIPO_CLIE --Codigo Tipo Cliente
       AND tsp.PAIS_OID_PAIS = lnOidPais
       AND sol.MARC_OID_MARC = lnOidMarca
       AND sol.CLSO_OID_CLAS_SOLI = lnOidClaseSolicitud
       AND pro.OPER_OID_OPER = lnOidOperacion;
  
    --Recupermos el Oid Estado de la Solicitud
    SELECT OID_ESTA_SOLI
      INTO lnOidEstadoSolicitud
      FROM PED_ESTAD_SOLIC
     WHERE COD_ESTA_SOLI = 'VA';
  
    --Recupermos el Oid Estado de la Posicion
    SELECT OID_ESTA_POSI
      INTO lnOidEstadoPosicion
      FROM PED_ESTAD_POSIC
     WHERE COD_ESTA_POSI = 'CO';
  
    --Recuperamos el Oid Secuencia de Procesos
    BEGIN 
     SELECT PROC_OID_PROC
      INTO lnOidProceso
      FROM PED_SECUE_PROCE
      WHERE TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSoliPais
       AND GRPR_OID_GRUP_PROC = ptSolicitud.OID_GRUP_PROC_SECU;
    EXCEPTION
      WHEN OTHERS THEN
          lnOidProceso:= 1;
    END;      
  
    --Recuperamos el Tipo de Cambio
    SELECT VAL_TIPO_CAMB
      INTO lnTipoCambio
      FROM PRE_MATRI_FACTU_CABEC
     WHERE PERD_OID_PERI = lnOidPeriodo;
  
    --Recuperamos el Oid Tipo Despacho y el indicador de Cronograma
    SELECT OID_TIPO_DESP, IND_CRON
      INTO lnOidTipoDespacho, lnIndCronograma
      FROM PED_TIPO_DESPA
     WHERE PAIS_OID_PAIS = lnOidPais
       AND IND_CRON = 1;
  
    --Recuperamos el Oid Asignacion Almacen
    SELECT ALMC_OID_ALMA
      INTO lnOidAlmacen
      FROM PED_ASIGN_ALMAC
     WHERE PAIS_OID_PAIS = lnOidPais
       AND MARC_OID_MARC = lnOidMarca
       AND SBAC_OID_SBAC = lnOidSubAcceso;
  
    --Obtenemos el Oid del Periodo de Despacho
    IF (psIndicadorProceso = 'P') THEN
      lsCodigoPeriodoSig := per_pkg_repor_perce.per_fn_obtie_perio(ptSolicitud.COD_PERI,
                                                                   lnOidPais,
                                                                   lnOidMarca,
                                                                   lnOidCanal,
                                                                   1);
    
      lnOidPeriodoDespacho := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoSig,
                                                                         lnOidMarca,
                                                                         lnOidCanal);
    ELSE
      lnOidPeriodoDespacho := lnOidPeriodo;
    END IF;
  
    --RECUPERAMOS LOS DATOS DEL CLIENTE
    --Recuperamos el OidSubTipo Cliente
    SELECT SBTI_OID_SUBT_CLIE
      INTO lnOidSubTipoCliente
      FROM MAE_CLIEN_TIPO_SUBTI
     WHERE CLIE_OID_CLIE = ptSolicitud.OID_CLIE
       AND TICL_OID_TIPO_CLIE = lnOidTipoCliente;
  
    --Recuperamos datos de la direccion del Cliente
    SELECT OID_CLIE_DIRE,
           (CASE
             WHEN (SUBSTR(COD_UNID_GEOG, 19, 6) IS NULL) THEN
              (SELECT VEG.OID_VALO_ESTR_GEOP
                 FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG, 0, 6)
                  AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG, 7, 6)
                  AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG, 13, 6)
                  AND VEG.ORDE_4 IS NULL)
             ELSE
              (SELECT VEG.OID_VALO_ESTR_GEOP
                 FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG, 0, 6)
                  AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG, 7, 6)
                  AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG, 13, 6)
                  AND VEG.ORDE_4 = SUBSTR(MCD.COD_UNID_GEOG, 19, 6))
           END) VEPO_OID_VALO_ESTR_GEOP
      INTO lnOidClienteDir, lnOidValEstruGeopo
      FROM MAE_CLIEN_DIREC MCD
     WHERE MCD.CLIE_OID_CLIE = ptSolicitud.OID_CLIE
       AND MCD.IND_DIRE_PPAL = 1
       AND MCD.IND_ELIM = 0;
  
    --Recuperamos los datos del documento de identidad del Cliente
    SELECT TDOC_OID_TIPO_DOCU
       INTO lnOidTipoDocumento
       FROM
        (SELECT TDOC_OID_TIPO_DOCU
         FROM MAE_CLIEN_IDENT
         WHERE CLIE_OID_CLIE = ptSolicitud.OID_CLIE
         ORDER BY VAL_IDEN_DOCU_PRIN DESC)
    WHERE ROWNUM = 1;
  
    --Recuperamos los datos de la unidad administrativa del Cliente
     SELECT OID_TERR_ADMI, TERR_OID_TERR, OID_SECC,
            OID_ZONA, ZORG_OID_REGI
     INTO lnOidTerriAdmin, lnOidTerritorio, lnOidSeccion,
          lnOidZona, lnOidRegion
     FROM 
      (SELECT ter.OID_TERR_ADMI, ter.TERR_OID_TERR, sec.OID_SECC,
       zon.OID_ZONA, zon.ZORG_OID_REGI
       FROM MAE_CLIEN_UNIDA_ADMIN adm, ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon
       WHERE adm.CLIE_OID_CLIE = ptSolicitud.OID_CLIE
             AND (adm.PERD_OID_PERI_FIN IS NULL OR adm.IND_ACTI=1)
             AND adm.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
             AND ter.ZSCC_OID_SECC = sec.OID_SECC
             AND sec.ZZON_OID_ZONA = zon.OID_ZONA
       ORDER BY adm.ind_acti DESC)
      WHERE ROWNUM = 1;
  
    --GENERAMOS LA SOLICITUD DE PEDIDO
    --Obtenemos la Ultima Secuencia para el OidSolicitud
    SELECT PED_SOCA_SEQ.NEXTVAL INTO lnOidSolicitud FROM DUAL;
  
    --Calculamos la Fecha Programada de Facturacion
    IF (ptSolicitud.FEC_PROG_FACT IS NULL) THEN
      IF (lnIndCronograma = 1) THEN
        BEGIN
            SELECT FEC_FINA
            INTO ldFechaProgFacturacion
            FROM CRA_PERIO
            WHERE OID_PERI = lnOidPeriodo;
        EXCEPTION
          WHEN OTHERS THEN
            ldFechaProgFacturacion := TRUNC(SYSDATE);
        END;
      ELSE
        ldFechaProgFacturacion := TRUNC(SYSDATE);
      END IF;
    ELSE
      ldFechaProgFacturacion := ptSolicitud.FEC_PROG_FACT;
    END IF;
 
    --Obtenemos el codigo de SubAcceso
    SELECT COD_SBAC
      INTO lsCodigoSubAcceso
      FROM SEG_SUBAC
     WHERE OID_SBAC = lnOidSubAcceso;
  
    --Obtenemos el oid Programa
    IF (ptSolicitud.OID_PARA_GRAL IS NOT NULL) THEN
      SELECT ICTP_OID_TIPO_PROG
        INTO lnOidTipoPrograma
        FROM INC_CONCU_PARAM_GENER
       WHERE OID_PARA_GRAL = ptSolicitud.OID_PARA_GRAL;
    END IF;
  
    --Obtenemos el Numero de Solicitud
    lnNumeroSolicitud := STO_PKG_GENER.STO_FN_RESRV_SECUE_NSOLI(ptSolicitud.COD_PAIS,
                                                                'PED001',
                                                                lsCodigoSubAcceso,
                                                                0);
  
    lnSolicitudFormato := to_char(SYSDATE, 'YY') ||
                          lpad(lnNumeroSolicitud, 8, '0') + 1;
  
    --INSERTAMOS UN REGISTRO EN PED_SOLIC_CABEC
    INSERT INTO PED_SOLIC_CABEC
      (OID_SOLI_CABE,
       ACFI_OID_ACCE_FISI,
       ALMC_OID_ALMA,
       CLDI_OID_CLIE_DIRE,
       CLIE_OID_CLIE,
       CLIE_OID_CLIE_DEST,
       CLIE_OID_CLIE_PAGA,
       CLIE_OID_CLIE_RECE_FACT,
       CLIE_OID_CONS_ASOC,
       CLSO_OID_CLAS_SOLI,
       COPA_OID_PARA_GENE,
       ESSO_OID_ESTA_SOLI,
       FEC_CRON,
       FEC_PROG_FACT,
       FOPA_OID_FORM_PAGO,
       GRPR_OID_GRUP_PROC,
       IND_OC,
       IND_PEDI_PRUE,
       IND_PERM_UNIO_SOL,
       IND_TS_NO_CONSO,
       MODU_OID_MODU,
       MONE_OID_MONE,
       NUM_CLIEN,
       NUM_DOCU_ORIG,
       NUM_PREM,
       OPER_OID_OPER,
       PAIS_OID_PAIS,
       PERD_OID_PERI,
       PROC_OID_PROC,
       SBAC_OID_SBAC,
       SBTI_OID_SUBT_CLIE,
       SOCA_OID_DOCU_REFE,
       SOCI_OID_SOCI,
       TDOC_OID_TIPO_DOCU,
       TERR_OID_TERR,
       TICL_OID_TIPO_CLIE,
       TIDO_OID_TIPO_DOCU,
       TIDS_OID_TIPO_DESP,
       TSPA_OID_TIPO_SOLI_PAIS,
       TSPA_OID_TIPO_SOLI_PAIS_CONS,
       VAL_GLOS_OBSE,
       VAL_NUME_SOLI,
       VAL_USUA,
       VEPO_OID_VALO_ESTR_GEOP,
       VAL_TIPO_CAMB,
       ZZON_OID_ZONA,
       ZTAD_OID_TERR_ADMI,
       ICTP_OID_TIPO_PROG,
       VAL_BASE_FLET_LOCA,
       VAL_TOTA_PAGA_LOCA,
       VAL_PREC_CATA_TOTA_LOCA,
       VAL_BASE_FLET_DOCU,
       VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
       VAL_UNID_DEMA_REAL_TOTA,
       NUM_UNID_POR_ATEN_TOTA,
       VAL_PREC_CONT_TOTA_LOCA)
    VALUES
      (lnOidSolicitud, --OID_SOLI_CABE
       NULL, --ACFI_OID_ACCE_FISI
       lnOidAlmacen, --ALMC_OID_ALMA
       lnOidClienteDir, --CLDI_OID_CLIE_DIRE
       ptSolicitud.OID_CLIE, --CLIE_OID_CLIE
       ptSolicitud.OID_CLIE, --CLIE_OID_CLIE_DEST
       ptSolicitud.OID_CLIE, --CLIE_OID_CLIE_PAGA
       ptSolicitud.OID_CLIE, --CLIE_OID_CLIE_RECE_FACT
       NULL, --CLIE_OID_CONS_ASOC
       lnOidClaseSolicitud, --CLSO_OID_CLAS_SOLI
       ptSolicitud.OID_PARA_GRAL, --COPA_OID_PARA_GENE
       lnOidEstadoSolicitud, --ESSO_OID_ESTA_SOLI
       TRUNC(SYSDATE), --FEC_CRON
       ldFechaProgFacturacion, --FEC_PROG_FACT
       lnOidFormaPago, --FOPA_OID_FORM_PAGO
       ptSolicitud.OID_GRUP_PROC, --GRPR_OID_GRUP_PROC
       0, --IND_OC
       lnIndPedidoPrueba, --IND_PEDI_PRUE
       lnIndPermitirUnion, --IND_PERM_UNIO_SOL
       1, --IND_TS_NO_CONSO
       13, --MODU_OID_MODU
       lnOidMoneda, --MONE_OID_MONE
       1, --NUM_CLIEN
       NULL, --NUM_DOCU_ORIG
       ptSolicitud.NUM_PREM, --NUM_PREM      ( Modificado )
       lnOidOperacion, --OPER_OID_OPER
       lnOidPais, --PAIS_OID_PAIS
       lnOidPeriodoDespacho, --PERD_OID_PERI
       lnOidProceso, --PROC_OID_PROC
       lnOidSubAcceso, --SBAC_OID_SBAC
       lnOidSubTipoCliente, --SBTI_OID_SUBT_CLIE
       NULL, --SOCA_OID_DOCU_REFE
       lnOidSociedad, --SOCI_OID_SOCI
       lnOidTipoDocumento, --TDOC_OID_TIPO_DOCU
       lnOidTerritorio, --TERR_OID_TERR
       lnOidTipoCliente, --TICL_OID_TIPO_CLIE
       lnOidTipoDocumentoLegal, --TIDO_OID_TIPO_DOCU
       lnOidTipoDespacho, --TIDS_OID_TIPO_DESP
       lnOidTipoSoliPais, --TSPA_OID_TIPO_SOLI_PAIS
       lnOidTipoCons, --TSPA_OID_TIPO_SOLI_PAIS_CONS
       ptSolicitud.VAL_GLOS_OBSE, --VAL_GLOS_OBSE
       lnSolicitudFormato, --VAL_NUME_SOLI
       ptSolicitud.COD_USUA, --VAL_USUA
       lnOidValEstruGeopo, --VEPO_OID_VALO_ESTR_GEOP
       DECODE(lnOidMoneda, NULL, 1, lnTipoCambio), --VAL_TIPO_CAMB
       lnOidZona, --ZZON_OID_ZONA
       lnOidTerriAdmin, --ZTAD_OID_TERR_ADMI
       lnOidTipoPrograma, --ICTP_OID_TIPO_PROG
       0, --VAL_BASE_FLET_LOCA
       0, --VAL_TOTA_PAGA_LOCA
       0, --VAL_PREC_CATA_TOTA_LOCA
       0, --VAL_BASE_FLET_DOCU
       0, --VAL_PREC_CATA_TOTA_LOC_UNI_DEM
       ptSolicitud.VAL_UNID_DEMA_REAL_TOTA, --VAL_UNID_DEMA_REAL_TOTA
       ptSolicitud.NUM_UNID_POR_ATEN_TOTA, --NUM_UNID_POR_ATEN_TOTA
       ptSolicitud.VAL_PREC_CONT_TOTA_LOCA --VAL_PREC_CONT_TOTA_LOCA
       );
  
    --RECUPERAMOS EL OID SOLICITUD GENERADO
    ptSolicitud.OID_SOLI_CABE_GENE := lnOidSolicitud;
  
    lnContadorPosiciones := 0;
    FOR y IN 1 .. ptRegPosicion.COUNT LOOP
      --Obtenemos la Ultima Secuencia para el oidPosicion
      SELECT PED_SOPO_SEQ.NEXTVAL INTO lnOidPosicion FROM DUAL;
    
      --Insertando Detalle en PED_SOLIC_POSIC
      INSERT INTO PED_SOLIC_POSIC
        (OID_SOLI_POSI,
         SOCA_OID_SOLI_CABE,
         COD_POSI,
         NUM_UNID_DEMA,
         NUM_UNID_POR_ATEN,
         TPOS_OID_TIPO_POSI,
         PROD_OID_PROD,
         FOPA_OID_FORM_PAGO,
         VAL_CODI_VENT,
         ESPO_OID_ESTA_POSI,
         STPO_OID_SUBT_POSI,
         VAL_CODI_VENT_FICT,
         NUM_UNID_DEMA_REAL,
         VAL_PREC_CATA_UNIT_LOCA,
         VAL_PREC_CONT_UNIT_LOCA,
         VAL_PREC_CATA_UNIT_DOCU,
         VAL_PREC_CONTA_UNIT_DOCU,
         VAL_PORC_DESC,
         VAL_IMPO_DESC_UNIT_DOCU,
         OFDE_OID_DETA_OFER,
         SOPO_OID_SOLI_POSI,
         NUM_UNID_COMPR,
         VAL_IMPO_DESC_UNIT_LOCA,
         NUM_PAGI_CATA,
         VAL_CATA)
      VALUES
        (lnOidPosicion, --OID_SOLI_POSI
         lnOidSolicitud, --SOCA_OID_SOLI_CABE
         lnContadorPosiciones, --COD_POSI
         ptRegPosicion       (y).NUM_UNID, --NUM_UNID_DEMA
         ptRegPosicion       (y).NUM_UNID, --NUM_UNID_POR_ATEN
         lnOidTipoPosi, --TPOS_OID_TIPO_POSI
         ptRegPosicion       (y).OID_PROD, --PROD_OID_PROD
         NULL, --FOPA_OID_FORM_PAGO
         ptRegPosicion       (y).VAL_CODI_VENT, --VAL_CODI_VENT
         lnOidEstadoPosicion, --ESPO_OID_ESTA_POSI
         lnOidSubTipoPosi, --STPO_OID_SUBT_POSI
         ptRegPosicion       (y).VAL_CODI_VENT_FICT, --VAL_CODI_VENT_FICT
         ptRegPosicion       (y).NUM_UNID, --NUM_UNID_DEMA_REAL
         0, --VAL_PREC_CATA_UNIT_LOCA
         ptRegPosicion       (y).VAL_PREC_CONT_UNIT_LOCA, --VAL_PREC_CONT_UNIT_LOCA
         0, --VAL_PREC_CATA_UNIT_DOCU
         0, --VAL_PREC_CONTA_UNIT_DOCU
         NULL, --VAL_PORC_DESC
         NULL, --VAL_IMPO_DESC_UNIT_DOCU
         NULL, --OFDE_OID_DETA_OFER
         NULL, --SOPO_OID_SOLI_POSI
         ptRegPosicion       (y).NUM_UNID_COMPR, --NUM_UNID_COMPR
         NULL, --VAL_IMPO_DESC_UNIT_LOCA
         NULL, --NUM_PAGI_CATA
         NULL); --VAL_CATA
    
      --RECUPERAMOS EL OID POSICION GENERADO
      ptRegPosicion(y).OID_SOLI_POSI_GENE := lnOidPosicion;
    
      lnContadorPosiciones := lnContadorPosiciones + 1;
    END LOOP;
  
    /*EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_SOLIC_PEDID: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);*/
  END INC_PR_GENER_SOLIC_PEDID;

  /**************************************************************************
  Descripcion        : Recupera el Codigo Zona de un determinado Cliente
  Fecha Creacion     : 06/08/2010
  Parametros Entrada :
             pnOidCliente : Oid Cliente
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_DEVUE_CODIG_ZONA(pnOidCliente NUMBER) RETURN NUMBER IS
    lsCodigoZona ZON_ZONA.COD_ZONA%TYPE;
  BEGIN
  
    --Recuperamos los datos de la unidad administrativa del Cliente
    SELECT zon.COD_ZONA
      INTO lsCodigoZona
      FROM MAE_CLIEN_UNIDA_ADMIN adm,
           ZON_TERRI_ADMIN       ter,
           ZON_SECCI             sec,
           ZON_ZONA              zon
     WHERE adm.CLIE_OID_CLIE = pnOidCliente
       AND adm.IND_ACTI = 1
       AND adm.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
       AND ter.ZSCC_OID_SECC = sec.OID_SECC
       AND sec.ZZON_OID_ZONA = zon.OID_ZONA;
  
    RETURN lsCodigoZona;
  
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_DEVUE_CODIG_ZONA: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_DEVUE_CODIG_ZONA;

  /**************************************************************************
  Descripcion        : Valida Si el Cliente esta pasando un pedido O/C
                       para un determinado periodo
  Fecha Creacion     : 06/08/2010
  Parametros Entrada :
             pnOidPais : Oid Pais
             pnOidPeriodo : Oid Periodo
             pnOidCliente : Oid Cliente
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_PEDID(pnOidPais    NUMBER,
                              pnOidPeriodo NUMBER,
                              pnOidCliente NUMBER) RETURN NUMBER IS
    lnIndicador NUMBER(1);
  BEGIN
  
    --Recuperamos los datos de la unidad administrativa del Cliente
    SELECT COUNT(1)
      INTO lnIndicador
      FROM PED_SOLIC_CABEC psc,
           PED_TIPO_SOLIC_PAIS tsp,
           (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts
     WHERE psc.PAIS_OID_PAIS = pnOidPais
       AND psc.PERD_OID_PERI = pnOidPeriodo
       AND psc.CLIE_OID_CLIE = pnOidCliente
       AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
       AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
       AND psc.FEC_FACT IS NULL
       AND psc.ESSO_OID_ESTA_SOLI = 1;
  
    RETURN lnIndicador;
  
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_PEDID: (' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_VALID_PEDID;

  /**************************************************************************
  Descripcion        : Obtiene el Oid Periodo Inicial para un pais determinado
  Fecha Creacion     : 09/08/2010
  Parametros Entrada :
             pnOidPais : Oid Pais
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_PERIO_INICI(psCodigoPais  VARCHAR2,
                                    psCodigoMarca VARCHAR2,
                                    psCodigoCanal VARCHAR2) RETURN NUMBER IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  
    --Recuperamos los datos de la unidad administrativa del Cliente
    SELECT OID_PERI
      INTO lnOidPeriodo
      FROM (SELECT c.OID_PERI, s.COD_PERI
              FROM CRA_PERIO c, SEG_PERIO_CORPO s
             WHERE c.PERI_OID_PERI = s.OID_PERI
               AND c.PAIS_OID_PAIS = lnOidPais
               AND c.MARC_OID_MARC = lnOidMarca
               AND c.CANA_OID_CANA = lnOidCanal
             ORDER BY s.COD_PERI)
     WHERE ROWNUM = 1;
  
    RETURN lnOidPeriodo;
  
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_OBTIE_PERIO_INICI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_OBTIE_PERIO_INICI;

  /**************************************************************************
  Descripcion       : Calcula Ranking para los concursos de Reconocimiento.
  Fecha Creacion    : 26/08/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_RANKI_CONCU_RECON(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCodigoZona       VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnTotal       NUMBER(12);
    lnOCurrencias NUMBER(12);
    lnRanking     INC_RANKI_RECON.NUM_RANK%TYPE;
    lbInsertar    BOOLEAN;
  
    regClasiConcu INC_CONCU_CLASI_RANKI%ROWTYPE;
  
    CURSOR c_Pedidos(numeroConcurso VARCHAR2) IS
      SELECT pc.NUM_CONC, cli.OID_CLIE, cli.COD_CLIE, pc.PUNTAJE
        FROM MAE_CLIEN cli,
             MAE_CLIEN_DATOS_ADICI clia,
             (SELECT icp.NUM_CONC,
                     icp.OID_PARA_GRAL,
                     icc.CLIE_OID_CLIE,
                     SUM(icc.NUM_PUNT) Puntaje
                FROM INC_CUENT_CORRI_PUNTO icc,
                     CRA_PERIO             cra,
                     SEG_PERIO_CORPO       cor,
                     INC_CONCU_PARAM_GENER ICP
               WHERE icp.NUM_CONC = numeroConcurso
                 AND icc.COPA_OID_PARA_GRAL = ICP.OID_PARA_GRAL
                 AND icc.PERD_OID_PERI = cra.OID_PERI
                 AND cra.PERI_OID_PERI = cor.OID_PERI
                 AND cor.COD_PERI <= psCodigoPeriodo
                 AND EXISTS
               (SELECT *
                        FROM INC_CANDI_GANAD icg
                       WHERE icc.CLIE_OID_CLIE = icg.CLIE_OID_CLIE
                         AND icg.COPA_OID_PARA_GRAL = icp.OID_PARA_GRAL
                         AND icg.BINC_OID_BASE_INCU IS NULL)
                 AND NOT EXISTS
               (SELECT *
                        FROM INC_CANDI_GANAD icg
                       WHERE icc.CLIE_OID_CLIE = icg.CLIE_OID_CLIE
                         AND icg.COPA_OID_PARA_GRAL = icp.OID_PARA_GRAL
                         AND icg.BINC_OID_BASE_INCU IS NOT NULL)
                 AND NOT EXISTS
               (SELECT *
                        FROM INC_DESCA ids
                       WHERE icc.CLIE_OID_CLIE = ids.CLIE_OID_CLIE
                         AND ids.COPA_OID_PARA_GRAL = icp.OID_PARA_GRAL)
               GROUP BY icp.NUM_CONC, icp.OID_PARA_GRAL, icc.CLIE_OID_CLIE) pc
       WHERE cli.OID_CLIE = pc.CLIE_OID_CLIE
         AND cli.OID_CLIE = clia.CLIE_OID_CLIE
         AND clia.IND_ACTI = 1
       ORDER BY pc.PUNTAJE DESC;
  
    TYPE interfazPedidos IS RECORD(
      numeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      codigoCliente  MAE_CLIEN.COD_CLIE%TYPE,
      puntaje        INC_RANKI_RECON.NUM_PUNT%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Se recupera los concursos de reconocimiento activos para el periodo
    SELECT COUNT(1)
      INTO lnTotal
      FROM INC_CONCU_PARAM_GENER C,
           INC_CLASI_CONCU       U,
           CRA_PERIO             PI,
           SEG_PERIO_CORPO       SI,
           CRA_PERIO             PF,
           SEG_PERIO_CORPO       SF
     WHERE C.PAIS_OID_PAIS = lnOidPais
       AND C.CCON_OID_CLAS_CONC = U.OID_CLAS_CONC
       AND U.COD_CLAS_CONC = 'T'
       AND C.IND_ACTI = 1
       AND C.PERD_OID_PERI_DESD = PI.OID_PERI
       AND PI.PERI_OID_PERI = SI.OID_PERI
       AND C.PERD_OID_PERI_HAST = PF.OID_PERI
       AND PF.PERI_OID_PERI = SF.OID_PERI
       AND SI.COD_PERI <= psCodigoPeriodo
       AND SF.COD_PERI >= psCodigoPeriodo;
  
    IF (lnTotal = 0) THEN
      RETURN;
    END IF;
  
    --Se borran los registros de ranking ya generados para la campaña de proceso si ya existiesen
    DELETE FROM INC_RANKI_RECON WHERE COD_PERI = psCodigoPeriodo;
  
    --PROCESAMOS A LAS CONSULTORAS QUE TIENEN PUNTAJE HASTA EL PERIODO DE PROCESO Y
    --NO SE ENCUENTRAN DESCALIFICADAS, PARA LUEGO ORDENARLAS DESCENTEMENTE POR PUNTAJE
    FOR y IN (SELECT C.NUM_CONC
                FROM INC_CONCU_PARAM_GENER C,
                     INC_CLASI_CONCU       U,
                     CRA_PERIO             PI,
                     SEG_PERIO_CORPO       SI,
                     CRA_PERIO             PF,
                     SEG_PERIO_CORPO       SF
               WHERE C.PAIS_OID_PAIS = lnOidPais
                 AND C.CCON_OID_CLAS_CONC = U.OID_CLAS_CONC
                 AND U.COD_CLAS_CONC = 'T'
                 AND C.IND_ACTI = 1
                 AND C.PERD_OID_PERI_DESD = PI.OID_PERI
                 AND PI.PERI_OID_PERI = SI.OID_PERI
                 AND C.PERD_OID_PERI_HAST = PF.OID_PERI
                 AND PF.PERI_OID_PERI = SF.OID_PERI
                 AND SI.COD_PERI <= psCodigoPeriodo
                 AND SF.COD_PERI >= psCodigoPeriodo) LOOP
    
      --Inicializamos el Ranking para el concurso procesado
      lnRanking := 1;
    
      --Verificamos si tiene clasificacion el Concurso
      SELECT COUNT(1)
        INTO lnTotal
        FROM INC_CONCU_CLASI_RANKI
       WHERE COD_CONC = y.NUM_CONC;
    
      OPEN c_Pedidos(y.NUM_CONC);
      LOOP
        FETCH c_Pedidos BULK COLLECT
          INTO interfazRecordN LIMIT W_FILAS;
        IF interfazRecordN.COUNT > 0 THEN
        
          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
            lbInsertar := FALSE;
          
            IF (lnTotal = 0) THEN
              lbInsertar := TRUE;
            
            ELSE
              --Recuperamos la clasificacion definida para el Concurso
              SELECT *
                INTO regClasiConcu
                FROM INC_CONCU_CLASI_RANKI
               WHERE COD_CONC = y.NUM_CONC;
            
              --Validamos si la Consultora tiene la clasificacion definida para el Concurso
              SELECT COUNT(1)
                INTO lnOCurrencias
                FROM MAE_CLIEN_TIPO_SUBTI S, MAE_CLIEN_CLASI C
               WHERE S.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND S.OID_CLIE_TIPO_SUBT = C.CTSU_OID_CLIE_TIPO_SUBT
                 AND S.TICL_OID_TIPO_CLIE =
                     regClasiConcu.TICL_OID_TIPO_CLIE
                 AND S.SBTI_OID_SUBT_CLIE =
                     NVL(regClasiConcu.SBTI_OID_SUBT_CLIE,
                         S.SBTI_OID_SUBT_CLIE)
                 AND C.TCCL_OID_TIPO_CLASI =
                     NVL(regClasiConcu.TCCL_OID_TIPO_CLASI,
                         C.TCCL_OID_TIPO_CLASI)
                 AND C.CLAS_OID_CLAS =
                     NVL(regClasiConcu.CLAS_OID_CLAS, C.CLAS_OID_CLAS);
            
              IF (lnOCurrencias > 0) THEN
                lbInsertar := TRUE;
              END IF;
            
            END IF;
          
            IF (lbInsertar) THEN
              --Insertamos un registro en la tabla INC_RANKI_RECON
              INSERT INTO INC_RANKI_RECON
                (COD_PERI, COD_CONC, COD_CLIE, NUM_PUNT, NUM_RANK)
              VALUES
                (psCodigoPeriodo,
                 y.NUM_CONC,
                 interfazRecordN(x).codigoCliente,
                 interfazRecordN(x).puntaje,
                 lnRanking);
            
            END IF;
          
            lnRanking := lnRanking + 1;
          
          END LOOP;
        
        END IF;
        EXIT WHEN c_Pedidos%NOTFOUND;
      END LOOP;
      CLOSE c_Pedidos;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_RANKI_CONCU_RECON: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_CALCU_RANKI_CONCU_RECON;

  /**************************************************************************
  Descripcion        : Devuelve 1 si el reemplazo es para el ámbito
                       geográfico de la consultora
  Fecha Creacion     : 11/02/2013
  Parametros Entrada :
             psOidReemArtiLote : Oid premio reemplazo
             psOidCliente      : Oid cliente
  Autor              : Hernán Ramos
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_REEMP(psOidReemArtiLote NUMBER,
                                          psOidCliente      NUMBER)
    RETURN VARCHAR2 IS
    /* Creando el tipo de dato  */
    TYPE cTerritorio IS RECORD(
      OID_ZONA ZON_ZONA.OID_ZONA%TYPE,
      OID_REGI ZON_REGIO.OID_REGI%TYPE);
    tmpTerritorio cTerritorio;
    lnConte       NUMBER;
    lnCont        NUMBER;
  
  BEGIN
  
    SELECT COUNT(1)
      INTO lnConte
      FROM INC_REEMP_REGIO_ZONA
     WHERE RARL_OID_REEM_ARTI_LOTE = psOidReemArtiLote;
  
    IF NOT (lnConte > 0) THEN
      RETURN '1'; -- NO HAY AMBITO GEOGRAFICO
    END IF;
  
    --Caso contrario Obtenemos los oids e zona y region:
    SELECT H.OID_ZONA, I.OID_REGI
      INTO tmpTerritorio
      FROM MAE_CLIEN             A,
           MAE_CLIEN_UNIDA_ADMIN D,
           ZON_TERRI_ADMIN       E,
           ZON_TERRI             F,
           ZON_SECCI             G,
           ZON_ZONA              H,
           ZON_REGIO             I
     WHERE A.OID_CLIE = psOidCliente
       AND A.OID_CLIE = D.CLIE_OID_CLIE
       AND D.IND_ACTI = '1'
       AND D.ZTAD_OID_TERR_ADMI = E.OID_TERR_ADMI
       AND E.TERR_OID_TERR = F.OID_TERR
       AND E.ZSCC_OID_SECC = G.OID_SECC
       AND G.ZZON_OID_ZONA = H.OID_ZONA
       AND H.ZORG_OID_REGI = I.OID_REGI
       AND ROWNUM = 1;
  
    SELECT COUNT(1)
      into lnCont
      FROM INC_REEMP_REGIO_ZONA geo
     WHERE geo.RARL_OID_REEM_ARTI_LOTE = psOidReemArtiLote
       AND (geo.ZORG_OID_REGI IS NULL OR
           geo.ZORG_OID_REGI = tmpTerritorio.OID_REGI)
       AND (geo.ZZON_OID_ZONA IS NULL OR
           geo.ZZON_OID_ZONA = tmpTerritorio.OID_ZONA);
  
    IF (lnCont > 0) THEN
      RETURN '1'; --SE ENCONTRO CLIENTE EN EL AMBITO DEL CONCURSO
    END IF;
  
    RETURN '0';
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_AMBIT_GEOGR_REEMP: ' ||
                              ls_sqlerrm);
      RETURN '0';
  END INC_FN_VALID_AMBIT_GEOGR_REEMP;

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 13/10/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_ORDEN_RETAI(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnTotalConsultoras NUMBER(12);
    lnTotalPosiciones  NUMBER(12);
  
    lnOidDetOferta PED_SOLIC_POSIC.OFDE_OID_DETA_OFER%TYPE;
    lsCodigoVenta  PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE;
    lnOidCliente   MAE_CLIEN.OID_CLIE%TYPE;
  
    CURSOR c_Pedidos(oidPeriodo NUMBER) IS
      SELECT CAB.VAL_CUEN_CONSU,
             LID_PKG_PROCE_LIDER.GEN_FN_DEVUELVE_ID_CLIENTE(CAB.VAL_CUEN_CONSU),
             SUM(DET.UNI_VEND) VAL_UNID,
             SUM(DET.UNI_VEND * DET.VAL_MONT_CATA) VAL_IMPO
        FROM RET_VENTA_CABEC CAB, RET_VENTA_DETAL DET, CRA_PERIO CRA
       WHERE CAB.COD_PAIS = SUBSTR(psCodigoPais, 1, 2)
         AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
         AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
         AND CAB.COD_SBAC = DET.COD_SBAC
         AND DET.TIPO_TRAN_RET = 'VR'
         AND CAB.VAL_CUEN_CONSU IS NOT NULL
         AND CRA.OID_PERI = oidPeriodo
         AND CAB.FEC_ENVI >= CRA.FEC_INIC
         AND CAB.FEC_ENVI <= CRA.FEC_FINA
       GROUP BY CAB.VAL_CUEN_CONSU;
  
    TYPE interfazPedidos IS RECORD(
      codCliente RET_VENTA_CABEC.VAL_CUEN_CONSU%TYPE,
      oidCliente MAE_CLIEN.OID_CLIE%TYPE,
      unidades   PED_SOLIC_CABEC.VAL_UNID_DEMA_REAL_TOTA%TYPE,
      importe    PED_SOLIC_CABEC.VAL_TOTA_PAGA_LOCA%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    ltRegSolicitud tRegSolicitud;
    ltRegPosicion  tablaRegPosicion := tablaRegPosicion();
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Inicializamos Contadores
    lnTotalConsultoras := 0;
    lnTotalPosiciones  := 0;
  
    --PROCESAMOS A LAS ORDENES RETAIL
    OPEN c_Pedidos(lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          IF (interfazRecordN(x).oidCliente <> -1) THEN
            --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
            ltRegSolicitud.COD_PAIS      := psCodigoPais;
            ltRegSolicitud.COD_MARC      := psCodigoMarca;
            ltRegSolicitud.COD_CANA      := psCodigoCanal;
            ltRegSolicitud.OID_CLIE      := interfazRecordN(x).oidCliente;
            ltRegSolicitud.COD_PERI      := psCodigoPeriodo;
            ltRegSolicitud.FEC_PROG_FACT := NULL; --Se calculara en la Creacion de la Solicitud
            ltRegSolicitud.COD_OPER      := 'RET010';
            ltRegSolicitud.COD_CLAS_SOLI := 'O1';
            ltRegSolicitud.COD_TIPO_SOLI := 'SOCR';
            ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
            ltRegSolicitud.OID_PARA_GRAL := NULL;
            ltRegSolicitud.NUM_PREM      := NULL;
            ltRegSolicitud.COD_USUA      := psCodigoUsuario;
          
            lnOidCliente := interfazRecordN(x).oidCliente;
          
            ltRegSolicitud.OID_ACCE_FISI                  := 1;
            ltRegSolicitud.OID_GRUP_PROC                  := 3; --(GP3)
            ltRegSolicitud.OID_GRUP_PROC_SECU             := 4; --(GP4)
            ltRegSolicitud.NUM_CLIEN                      := 1;
            ltRegSolicitud.OID_MODU                       := 1; --Pedidos
            ltRegSolicitud.VAL_GLOS_OBSE                  := NULL;
            ltRegSolicitud.VAL_BASE_FLET_LOCA             := interfazRecordN(x)
                                                             .importe;
            ltRegSolicitud.VAL_TOTA_PAGA_LOCA             := interfazRecordN(x)
                                                             .importe;
            ltRegSolicitud.VAL_PREC_CATA_TOTA_LOCA        := interfazRecordN(x)
                                                             .importe;
            ltRegSolicitud.VAL_BASE_FLET_DOCU             := interfazRecordN(x)
                                                             .importe;
            ltRegSolicitud.VAL_PREC_CATA_TOTA_LOC_UNI_DEM := interfazRecordN(x)
                                                             .importe;
            ltRegSolicitud.VAL_UNID_DEMA_REAL_TOTA        := interfazRecordN(x)
                                                             .unidades;
            ltRegSolicitud.NUM_UNID_POR_ATEN_TOTA         := interfazRecordN(x)
                                                             .unidades;
          
            --Obtener la lista de los Premios de los articulos a Entregar
            FOR y IN (SELECT DET.UNI_VEND,
                             (SELECT OID_PROD
                                FROM MAE_PRODU
                               WHERE COD_SAP = DET.COD_PROD) OID_PROD,
                             DET.COD_PROD,
                             DET.COD_VENT,
                             DET.VAL_MONT_CATA
                        FROM RET_VENTA_CABEC CAB,
                             RET_VENTA_DETAL DET,
                             CRA_PERIO       CRA
                       WHERE CAB.COD_PAIS = SUBSTR(psCodigoPais, 1, 2)
                         AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
                         AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
                         AND CAB.COD_SBAC = DET.COD_SBAC
                         AND DET.TIPO_TRAN_RET = 'VR'
                         AND CRA.OID_PERI = lnOidPeriodo
                         AND CAB.FEC_ENVI >= CRA.FEC_INIC
                         AND CAB.FEC_ENVI <= CRA.FEC_FINA
                         AND CAB.VAL_CUEN_CONSU = interfazRecordN(x)
                            .codCliente) LOOP
            
              --Si el codigo de venta tiene menos de 5 caracteres, completarlo con 0 a la izquierda,
              --Si tiene 0 colocar nulo
              lsCodigoVenta := y.COD_VENT;
            
              IF (lsCodigoVenta = '0') THEN
                lsCodigoVenta := NULL;
              ELSIF (lsCodigoVenta IS NOT NULL) THEN
                lsCodigoVenta := LPAD(y.COD_VENT, 5, '0');
              END IF;
            
              --Obtenemos el Oid Detalle Oferta
              BEGIN
                SELECT det.OID_DETA_OFER
                  INTO lnOidDetOferta
                  FROM PRE_MATRI_FACTU_CABEC mat,
                       PRE_OFERT             ofe,
                       PRE_OFERT_DETAL       det
                 WHERE mat.PERD_OID_PERI = lnOidPeriodo
                   AND mat.OID_CABE = ofe.MFCA_OID_CABE
                   AND ofe.OID_OFER = det.OFER_OID_OFER
                   AND det.val_codi_vent = y.COD_VENT;
              EXCEPTION
                WHEN OTHERS THEN
                  lnOidDetOferta := NULL;
              END;
            
              ltRegPosicion.EXTEND(1);
              ltRegPosicion(ltRegPosicion.LAST).OID_PROD := y.OID_PROD;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := y.UNI_VEND;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := lsCodigoVenta;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := NULL;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := y.UNI_VEND;
              ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := lnOidDetOferta;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := y.VAL_MONT_CATA;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := y.VAL_MONT_CATA;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := y.VAL_MONT_CATA;
            
              lnTotalPosiciones := lnTotalPosiciones + 1;
            
            END LOOP;
          
            --Obtener la lista de los Premios de los articulos a Devolver
            FOR y IN (SELECT DISTINCT DETDEV.UNI_DEVU,
                                      (SELECT OID_PROD
                                         FROM MAE_PRODU
                                        WHERE COD_SAP = DETDEV.COD_PROD) OID_PROD,
                                      DETDEV.COD_PROD,
                                      DETDEV.COD_VENT,
                                      DETDEV.VAL_MONT_CATA
                        FROM RET_VENTA_CABEC CAB,
                             RET_VENTA_DETAL DET,
                             RET_VENTA_CABEC CABDEV,
                             RET_VENTA_DETAL DETDEV,
                             CRA_PERIO       CRA
                       WHERE CAB.COD_PAIS = SUBSTR(psCodigoPais, 1, 2)
                         AND CAB.VAL_CUEN_CONSU = interfazRecordN(x)
                            .codCliente
                         AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
                         AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
                         AND CAB.COD_SBAC = DET.COD_SBAC
                         AND DET.TIPO_TRAN_RET = 'VR'
                         AND CRA.OID_PERI = lnOidPeriodo
                         AND CAB.FEC_ENVI >= CRA.FEC_INIC
                         AND CAB.FEC_ENVI <= CRA.FEC_FINA
                         AND CAB.NUM_DOCU_RETA = CABDEV.FAC_REFE
                         AND DETDEV.NUM_DOCU_RETA = CABDEV.NUM_DOCU_RETA
                         AND DETDEV.COD_TIPO_DOCU = CABDEV.COD_TIPO_DOCU
                         AND DETDEV.COD_SBAC = CABDEV.COD_SBAC
                         AND DETDEV.TIPO_TRAN_RET = 'RR') LOOP
            
              --Si el codigo de venta tiene menos de 5 caracteres, completarlo con 0 a la izquierda,
              --Si tiene 0 colocar nulo
              lsCodigoVenta := y.COD_VENT;
            
              IF (lsCodigoVenta = '0') THEN
                lsCodigoVenta := NULL;
              ELSIF (lsCodigoVenta IS NOT NULL) THEN
                lsCodigoVenta := LPAD(y.COD_VENT, 5, '0');
              END IF;
            
              --Obtenemos el Oid Detalle Oferta
              BEGIN
                SELECT det.OID_DETA_OFER
                  INTO lnOidDetOferta
                  FROM PRE_MATRI_FACTU_CABEC mat,
                       PRE_OFERT             ofe,
                       PRE_OFERT_DETAL       det
                 WHERE mat.PERD_OID_PERI = lnOidPeriodo
                   AND mat.OID_CABE = ofe.MFCA_OID_CABE
                   AND ofe.OID_OFER = det.OFER_OID_OFER
                   AND det.val_codi_vent = y.COD_VENT;
              EXCEPTION
                WHEN OTHERS THEN
                  lnOidDetOferta := NULL;
              END;
            
              ltRegPosicion.EXTEND(1);
              ltRegPosicion(ltRegPosicion.LAST).OID_PROD := y.OID_PROD;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := y.UNI_DEVU * (-1);
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := lsCodigoVenta;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := NULL;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := y.UNI_DEVU * (-1);
              ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := lnOidDetOferta;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := y.VAL_MONT_CATA;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := y.VAL_MONT_CATA;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := y.VAL_MONT_CATA;
            
              lnTotalPosiciones := lnTotalPosiciones + 1;
            
            END LOOP;
          
            --CREAMOS LA SOLICITUD DE PEDIDO
            INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud, 'T', ltRegPosicion);
          
            --Inicializamos la lista de Posiciones de la Nueva Solicitud
            ltRegPosicion.DELETE;
          
            lnTotalConsultoras := lnTotalConsultoras + 1;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
    --Se graba un registro en la tabla de Carga de Retail
    INSERT INTO INC_CONTR_CARGA_ECM
      (OID_CONT_CARG,
       PAIS_OID_PAIS,
       PERD_OID_PERI,
       COD_PERI,
       FEC_PROC,
       VAL_CANT_CONS_PROC,
       VAL_CANT_POSI_PROC,
       IND_ESTA,
       FEC_FEGI,
       USU_REGI)
    VALUES
      (INC_CECM_SEQ.NEXTVAL,
       lnOidPais,
       lnOidPeriodo,
       psCodigoPeriodo,
       TRUNC(SYSDATE),
       lnTotalConsultoras,
       lnTotalPosiciones,
       '1', --Procesado
       SYSDATE,
       psCodigoUsuario);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CARGA_ORDEN_RETAI: (' ||
                              ln_sqlcode || ', oidCliente: ' ||
                              TO_CHAR(lnOidCliente) || ')' || ls_sqlerrm);
    
  END INC_PR_CARGA_ORDEN_RETAI;

  /**************************************************************************
  Descripcion       : Anula la Carda de Ordenes Retail por Campaña
  Fecha Creacion    : 15/10/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidControlCarga : Oid Control Carga ECM
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ANULA_CARGA_ORDEN_RETAI(psCodigoPais      VARCHAR2,
                                           pnOidControlCarga NUMBER,
                                           psCodigoUsuario   VARCHAR2) IS
    lnOidPais SEG_PAIS.OID_PAIS%TYPE;
  
    lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
    lnOidTipoSolicPais PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  
    --Ubicamos el Oid Periodo de la Carga
    SELECT PERD_OID_PERI
      INTO lnOidPeriodo
      FROM INC_CONTR_CARGA_ECM
     WHERE OID_CONT_CARG = pnOidControlCarga;
  
    --Eliminamos los registros de los puntos creados
    DELETE FROM INC_CUENT_CORRI_PUNTO
     WHERE PERD_OID_PERI = lnOidPeriodo
       AND TMOV_OID_TIPO_MOVI = 1
       AND VAL_DESC LIKE '%ECM%';
  
    --Ubicamos el Tipo Solicitud Pais 'SOCR'
    SELECT tsp.OID_TIPO_SOLI_PAIS
      INTO lnOidTipoSolicPais
      FROM PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
     WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
       AND sol.COD_TIPO_SOLI = 'SOCR'
       AND tsp.PAIS_OID_PAIS = lnOidPais;
  
    --Eliminamos los registros de la tabla  INC_SOLIC_CONCU_PUNTA
    DELETE FROM INC_SOLIC_CONCU_PUNTA
     WHERE SOCA_OID_SOLI_CABE IN
           (SELECT OID_SOLI_CABE
              FROM PED_SOLIC_CABEC
             WHERE PERD_OID_PERI = lnOidPeriodo
               AND TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicPais);
  
    --Eliminamos los registros de la tabla  INC_SOLIC_CONCU_RECOM
    DELETE FROM INC_SOLIC_CONCU_RECOM
     WHERE SOCA_OID_SOLI_CABE IN
           (SELECT OID_SOLI_CABE
              FROM PED_SOLIC_CABEC
             WHERE PERD_OID_PERI = lnOidPeriodo
               AND TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicPais);
  
    --Eliminamos las posiciones registradas de las tablas PED_SOLIC_POSIC
    DELETE FROM PED_SOLIC_POSIC
     WHERE SOCA_OID_SOLI_CABE IN
           (SELECT OID_SOLI_CABE
              FROM PED_SOLIC_CABEC
             WHERE PERD_OID_PERI = lnOidPeriodo
               AND TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicPais);
  
    --Eliminamos las cabeceras registradas de las tablas PED_SOLIC_CABEC
    DELETE FROM PED_SOLIC_CABEC
     WHERE PERD_OID_PERI = lnOidPeriodo
       AND TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicPais;
  
    --Anulamos la Carga de Orden Retail
    UPDATE INC_CONTR_CARGA_ECM
       SET IND_ESTA = '2', --Anulado
           FEC_MODI = SYSDATE,
           USU_MODI = psCodigoUsuario
     WHERE OID_CONT_CARG = pnOidControlCarga;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ANULA_CARGA_ORDEN_RETAIL: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_ANULA_CARGA_ORDEN_RETAI;

  /**************************************************************************
  Descripcion       : Permite dar por atendido a premios de bolsa de faltantes
                      para que no puedan atenderse en el futuro
  Fecha Creacion    : 02/11/2010
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psIndConsultoras    :  Codigo de Marca
    psNumeroLote    :  Numero Lote
    psOidConcurso  :  Oid Concurso
    psOidPeriodo  :  Oid Periodo
    psOidProducto  :  Oid Periodo
    psObservaciones  :  Observaciones
    psCodigoUsuario  :  Codigo Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_BOLSA_FALTA(psCodigoPais     VARCHAR2,
                                     psIndConsultoras VARCHAR2,
                                     psNumeroLote     VARCHAR2,
                                     psOidConcurso    VARCHAR2,
                                     psOidPeriodo     VARCHAR2,
                                     psOidProducto    VARCHAR2,
                                     psObservaciones  VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS
  
    lsObservaciones INC_BOLSA_FALTA.VAL_OBSE%TYPE;
  BEGIN
    --Si viene en blanco las observaciones, se le pone un mensaje con el usuario que ha atendido
    IF (psObservaciones IS NULL) THEN
      lsObservaciones := 'Dado por atendido por ' || psCodigoUsuario;
    ELSE
      lsObservaciones := psObservaciones;
    END IF;
  
    IF (psIndConsultoras = '0') THEN
      --Se carga la informacion de todas las consultoras
      UPDATE INC_BOLSA_FALTA
         SET FEC_SOLU = SYSDATE,
             VAL_OBSE = lsObservaciones,
             USU_MODI = psCodigoUsuario
       WHERE OID_BOLS_FALT IN
             (SELECT bol.OID_BOLS_FALT
                FROM INC_BOLSA_FALTA     bol,
                     PED_SOLIC_CABEC     psc,
                     PED_SOLIC_POSIC     psp,
                     PED_TIPO_SOLIC_PAIS tsp,
                     PED_TIPO_SOLIC      ts
               WHERE psc.COPA_OID_PARA_GENE = psOidConcurso
                 AND psc.PERD_OID_PERI =
                     NVL(psOidPeriodo, psc.PERD_OID_PERI)
                 AND psp.PROD_OID_PROD =
                     NVL(psOidProducto, psp.PROD_OID_PROD)
                 AND psp.SOCA_OID_SOLI_CABE = psc.OID_SOLI_CABE
                 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                 AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                 AND ts.COD_TIPO_SOLI in ('SIFC', 'SINC', 'SIN')
                 AND psp.OID_SOLI_POSI = bol.SOPO_OID_SOLI_POSI
                 AND bol.FEC_SOLU IS NULL);
    
    ELSE
      --Se carga la informacion de las consultoras de la tabla de Bolsa de Atendidos
      UPDATE INC_BOLSA_FALTA
         SET FEC_SOLU = SYSDATE,
             VAL_OBSE = lsObservaciones,
             USU_MODI = psCodigoUsuario
       WHERE OID_BOLS_FALT IN
             (SELECT bol.OID_BOLS_FALT
                FROM INC_BOLSA_FALTA     bol,
                     PED_SOLIC_CABEC     psc,
                     PED_SOLIC_POSIC     psp,
                     PED_TIPO_SOLIC_PAIS tsp,
                     PED_TIPO_SOLIC      ts,
                     MAE_CLIEN           cli,
                     INC_BOLSA_ATEND     ate
               WHERE psc.COPA_OID_PARA_GENE = psOidConcurso
                 AND psc.PERD_OID_PERI =
                     NVL(psOidPeriodo, psc.PERD_OID_PERI)
                 AND psp.PROD_OID_PROD =
                     NVL(psOidProducto, psp.PROD_OID_PROD)
                 AND psp.SOCA_OID_SOLI_CABE = psc.OID_SOLI_CABE
                 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                 AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                 AND ts.COD_TIPO_SOLI in ('SIFC', 'SINC', 'SIN')
                 AND psp.OID_SOLI_POSI = bol.SOPO_OID_SOLI_POSI
                 AND psc.CLIE_OID_CLIE = cli.OID_CLIE
                 AND cli.COD_CLIE = ate.COD_CLIE
                 AND ate.NUM_LOTE = psNumeroLote
                 AND ate.IND_CARG = 1
                 AND bol.FEC_SOLU IS NULL);
    
      UPDATE INC_BOLSA_ATEND
         SET VAL_OBSE = lsObservaciones
       WHERE NUM_LOTE = psNumeroLote;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_BOLSA_FALTA: ' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_BOLSA_FALTA;

  /**************************************************************************
  Descripcion       : Regulariza el registro de recomendaciones en tablas de incentivos
  Fecha Creacion    : 01/02/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Marca
    psIndicadorProceso : F: Facturacion,
                         P: Inicio de Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_REGIS_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psUsuario          VARCHAR2,
                                     psServer           VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsCodPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoProceso  CRA_PERIO.OID_PERI%TYPE;
  
    lnOidClienteRcdte INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidConcurso     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lnTotal            NUMBER;
    lnPasoPedido       NUMBER;
    lnTotalPedidoRecom NUMBER;
  
    CURSOR c_Pedidos(oidPeriodo NUMBER, oidPeriodoActual NUMBER) IS
      SELECT DISTINCT cli.OID_CLIE,
                      vin.CLIE_OID_CLIE_VNTE,
                      INC_FN_VERIF_PEDID_CLIEN(cli.OID_CLIE, oidPeriodo)
        FROM MAE_CLIEN             cli,
             MAE_CLIEN_DATOS_ADICI adi,
             MAE_CLIEN_VINCU       vin,
             CRA_PERIO             per
       WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
         AND cli.OID_CLIE = vin.CLIE_OID_CLIE_VNDO
         AND adi.ESTA_OID_ESTA_CLIE IN (1, 7) --REGISTRADAS y RETIRADAS
         AND per.OID_PERI = oidPeriodo
         AND FEC_DESD >= per.FEC_INIC
         AND FEC_DESD <= per.FEC_FINA
         AND NOT EXISTS
       (SELECT *
                FROM INC_CLIEN_RECDO
               WHERE CLIE_OID_CLIE = cli.OID_CLIE
                 AND PERD_OID_PERI = oidPeriodoActual);
  
    TYPE interfazPedidos IS RECORD(
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      oidClienteVnte MAE_CLIEN.OID_CLIE%TYPE,
      indPasoPedido  NUMBER);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    CURSOR c_recomendacion(oidPais        NUMBER,
                           oidPeriodo     NUMBER,
                           oidClienteVnte NUMBER) IS
      SELECT oidconcurso, numeronivel
        FROM (SELECT cpg.oid_para_gral oidconcurso,
                     pgp.num_nive numeronivel,
                     lov_pkg_proce.lov_fn_valid_clasi_concu(cpg.oid_para_gral,
                                                            oidClienteVnte) validacionclasificacion
                FROM inc_concu_param_gener cpg,
                     inc_param_gener_premi pgp,
                     cra_perio             peridesde,
                     cra_perio             perihasta,
                     cra_perio             periactu,
                     seg_perio_corpo       speridesde,
                     seg_perio_corpo       sperihasta
               WHERE cpg.diri_oid_diri = 1 --es dirigido a la Consultora
                 AND cpg.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                 AND pgp.copa_oid_para_gral = cpg.oid_para_gral
                 AND periactu.oid_peri = oidPeriodo -- Periodo Actual
                 AND peridesde.fec_inic <= periactu.fec_inic
                 AND periactu.fec_fina <= perihasta.fec_fina
                 AND cpg.perd_oid_peri_desd = peridesde.oid_peri
                 AND cpg.perd_oid_peri_hast = perihasta.oid_peri
                 AND cpg.pais_oid_pais = oidPais -- Oid del País
                 AND cpg.ind_acti = 1
                 AND speridesde.oid_peri = peridesde.peri_oid_peri
                 AND sperihasta.oid_peri = perihasta.peri_oid_peri)
       WHERE validacionclasificacion = '1';
  
    TYPE crecomrec IS RECORD(
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroNivel INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE);
  
    TYPE crecomrectab IS TABLE OF crecomrec;
    crecomrecord crecomrectab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Realizamos el Proceso de Activacion de Concursos
    INC_PR_ACTIV_CONCU(psCodigoPais,
                       'T',
                       'VD',
                       psCodigoPeriodo,
                       psUsuario,
                       psServer);
  
    --RECUPERAMOS EL PERIODO ANTERIOR
    lsCodPeriodoAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                   lnOidPais,
                                                                   lnOidMarca,
                                                                   lnOidCanal,
                                                                   -1);
  
    lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnterior,
                                                                       lnOidMarca,
                                                                       lnOidCanal);
  
    --DEFINIMOS SI CONSULTAMOS CONSULTORAS DEl PERIODO ANTERIO O DEL PERIODO ACTUAL
    IF (psIndicadorProceso = PROCESO_INICIO_CAMPANA) THEN
      lnOidPeriodoProceso := lnOidPeriodoAnterior;
    ELSIF (psIndicadorProceso = PROCESO_FACTURACION) THEN
      lnOidPeriodoProceso := lnOidPeriodo;
    END IF;
  
    --(1) PROCESAMOS A LAS CONSULTORAS CON ESTATUS=REGISTRADA
    OPEN c_Pedidos(lnOidPeriodoProceso, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Verificamos que no hay pasado Pedido
          lnPasoPedido := interfazRecordN(x).indPasoPedido;
        
          IF (lnPasoPedido = 0) THEN
          
            SELECT COUNT(1)
              INTO lnTotalPedidoRecom
              FROM INC_CLIEN_RECDO rdo, INC_PEDID_CONCU_RECOM rec
             WHERE rdo.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND rdo.PERD_OID_PERI = lnOidPeriodoProceso
               AND rdo.OID_CLIE_REDO = rec.CLRE_OID_CLIE_REDO;
          
            IF (lnTotalPedidoRecom = 0) THEN
            
              DELETE FROM INC_CLIEN_RECDO rdo
               WHERE rdo.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND rdo.PERD_OID_PERI = lnOidPeriodoProceso;
            
              --Insertamos los concursos de Recomendaciones para la Consultora
              INC_PR_INSER_REGIS_RECOM(lnOidPais,
                                       interfazRecordN  (x).oidCliente,
                                       lnOidPeriodo,
                                       interfazRecordN  (x).oidClienteVnte,
                                       MODULO_INCENTIVOS,
                                       'X');
            
              --Actualizamos Primer Contacto
              UPDATE MAE_CLIEN_PRIME_CONTA
                 SET PERD_OID_PERI = lnOidPeriodo
               WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
            
            END IF;
          
          END IF;
        
        END LOOP;
      END IF;
    
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REGUL_REGIS_RECOM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REGUL_REGIS_RECOM;

  /**************************************************************************
  Descripcion        : Valida si la consultora realizo pedido
  Fecha Creacion     :  02/02/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             pnOidPeriodo : oid Periodo
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VERIF_PEDID_CLIEN(pnOidCliente NUMBER,
                                    pnOidPeriodo NUMBER) RETURN NUMBER IS
    lnValidado NUMBER;
    lnTotal    NUMBER;
  BEGIN
  
    SELECT COUNT(1)
      INTO lnTotal
      FROM ped_solic_cabec     A,
           ped_solic_cabec     B,
           ped_tipo_solic_pais C,
           ped_tipo_solic      D
     WHERE A.clie_oid_clie = pnOidCliente
       and A.perd_oid_peri = pnOidPeriodo
       and B.perd_oid_peri = A.perd_oid_peri
       and A.soca_oid_soli_cabe = B.OID_SOLI_CABE
       and A.TSPA_OID_TIPO_SOLI_PAIS = C.Oid_Tipo_Soli_Pais
       AND C.TSOL_OID_TIPO_SOLI = D.OID_TIPO_SOLI
       AND D.COD_TIPO_SOLI = 'SOC'
       AND A.fec_fact IS NOT NULL
       AND B.ESSO_OID_ESTA_SOLI <> 4;
  
    IF (lnTotal > 0) THEN
      lnValidado := 1;
    ELSE
      lnValidado := 0;
    END IF;
  
    RETURN lnValidado;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
    
  END INC_FN_VERIF_PEDID_CLIEN;

  /**************************************************************************
  Descripcion        : Valida que la zona del Cliente Recomendante perteneza a
                       una zona configurada para el concurso
  Fecha Creacion     :  02/02/2011
  Parametros Entrada :
             pnOidConcurso : oid Concurso
             pnOidCliente :  oid Cliente Recomendante
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR(pnOidConcurso NUMBER,
                                    pnOidCliente  NUMBER) RETURN NUMBER IS
    lsCodigoZona ZON_ZONA.COD_ZONA%TYPE;
    lnValidado   NUMBER;
    lnTotal      NUMBER;
  BEGIN
  
    SELECT COUNT(1)
      INTO lnTotal
      FROM INC_AMBIT_GEOGR
     WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    IF (lnTotal > 0) THEN
      SELECT zz.COD_ZONA
        INTO lsCodigoZona
        FROM MAE_CLIEN_UNIDA_ADMIN ua,
             ZON_TERRI_ADMIN       zta,
             ZON_TERRI             zt,
             ZON_ZONA              zz,
             ZON_SECCI             zs
       WHERE ua.CLIE_OID_CLIE = pnOidCliente
         AND ua.PERD_OID_PERI_FIN IS NULL
         AND ua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
         AND zs.OID_SECC = zta.ZSCC_OID_SECC
         AND zt.OID_TERR = zta.TERR_OID_TERR
         AND zt.PAIS_OID_PAIS = zta.PAIS_OID_PAIS
         AND zz.OID_ZONA = zs.ZZON_OID_ZONA;
    
      SELECT COUNT(1)
        INTO lnTotal
        FROM (SELECT z.cod_zona
                FROM inc_ambit_geogr a, zon_zona z
               WHERE a.copa_oid_para_gral = pnOidConcurso
                 AND a.zzon_oid_zona IS NOT NULL
                 AND a.zzon_oid_zona = z.oid_zona
                 AND z.cod_zona = lsCodigoZona
              UNION
              SELECT z.cod_zona
                FROM inc_ambit_geogr a, zon_zona z
               WHERE a.copa_oid_para_gral = pnOidConcurso
                 AND a.zorg_oid_regi IS NOT NULL
                 AND a.zzon_oid_zona IS NULL
                 AND a.zorg_oid_regi = z.zorg_oid_regi
                 AND z.cod_zona = lsCodigoZona);
    
      IF (lnTotal > 0) THEN
        lnValidado := 1;
      ELSE
        lnValidado := 0;
      END IF;
    ELSE
      lnValidado := 1;
    END IF;
  
    RETURN lnValidado;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
    
  END INC_FN_VALID_AMBIT_GEOGR;

  /**************************************************************************
  Descripcion       : Inserta los registros de recomendaciones para la consultora
  Fecha Creacion    : 16/02/2011
  Parametros Entrada:
    pnOidPais       :  Oid Pais
    pnOidCliente    :  Oid Cliente
    pnOidPeriodo    :  Oid Periodo
    pnOidClienteRcdte : Oid Cliente Recomendante
    pnOidModulo      : Oid Modulo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_INSER_REGIS_RECOM(pnOidPais         NUMBER,
                                     pnOidCliente      NUMBER,
                                     pnOidPeriodo      NUMBER,
                                     pnOidClienteRcdte NUMBER,
                                     pnOidModulo       NUMBER,
                                     pstipoproc        VARCHAR2) IS
    lsCodPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoProceso  CRA_PERIO.OID_PERI%TYPE;
  
    lnOidClienteRcdte INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidConcurso     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lnTotal            NUMBER;
    lnPasoPedido       NUMBER;
    lnTotalPedidoRecom NUMBER;
    lnCanReg           NUMBER;
    lsResult           VARCHAR2(1);
  
    CURSOR c_recomendacion(oidPais        NUMBER,
                           oidPeriodo     NUMBER,
                           oidClienteVnte NUMBER) IS
      SELECT oidconcurso, numeronivel
        FROM (SELECT cpg.oid_para_gral oidconcurso, pgp.num_nive numeronivel
                FROM inc_concu_param_gener cpg,
                     inc_param_gener_premi pgp,
                     cra_perio             peridesde,
                     cra_perio             perihasta,
                     cra_perio             periactu,
                     seg_perio_corpo       speridesde,
                     seg_perio_corpo       sperihasta
               WHERE cpg.diri_oid_diri = 1 --es dirigido a la Consultora
                 AND cpg.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                 AND pgp.copa_oid_para_gral = cpg.oid_para_gral
                 AND periactu.oid_peri = oidPeriodo -- Periodo Actual
                 AND peridesde.fec_inic <= periactu.fec_inic
                 AND periactu.fec_fina <= perihasta.fec_fina
                 AND cpg.perd_oid_peri_desd = peridesde.oid_peri
                 AND cpg.perd_oid_peri_hast = perihasta.oid_peri
                 AND cpg.pais_oid_pais = oidPais -- Oid del País
                 AND cpg.ind_acti = 1
                 AND speridesde.oid_peri = peridesde.peri_oid_peri
                 AND sperihasta.oid_peri = perihasta.peri_oid_peri);
  
    TYPE crecomrec IS RECORD(
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroNivel INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE);
  
    TYPE crecomrectab IS TABLE OF crecomrec;
    crecomrecord crecomrectab;
  
  BEGIN
    --Se recuperan los concursos activos para la campaña de proceso
    OPEN c_recomendacion(pnOidPais, pnOidPeriodo, pnOidClienteRcdte);
    LOOP
      FETCH c_recomendacion BULK COLLECT
        INTO crecomrecord LIMIT 1000;
      IF crecomrecord.count > 0 THEN
        FOR xx IN crecomrecord.first .. crecomrecord.last LOOP
          lnOidConcurso := crecomrecord(xx).oidConcurso;
          --Verificamos si concurso tiene configurado ambito geográfico
          SELECT COUNT(1)
            INTO lnCanReg
            FROM inc_ambit_geogr
           WHERE copa_oid_para_gral = lnOidConcurso;
        
          lsResult := '1';
          IF (lnCanReg > 0) THEN
            --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
            BEGIN
              lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(lnOidConcurso,
                                                         pnOidClienteRcdte);
            EXCEPTION
              WHEN OTHERS THEN
                lsResult := '0';
            END;
          END IF;
        
          IF (lsResult = '1') THEN
            SELECT MAX(a.oid_clie_rete)
              INTO lnOidClienteRcdte
              FROM inc_clien_recte a
             WHERE clie_oid_clie = pnOidClienteRcdte
               AND copa_oid_para_gral = lnOidConcurso;
          
            IF lnOidClienteRcdte IS NULL THEN
            
              SELECT inc_clr3_seq.NEXTVAL INTO lnOidClienteRcdte FROM dual;
            
              INSERT INTO inc_clien_recte
                (oid_clie_rete,
                 ind_fin_vinc,
                 clie_oid_clie,
                 copa_oid_para_gral,
                 ind_eval,
                 oid_modu,
                 fec_crea,
                 ind_tipo_proc)
              VALUES
                (lnOidClienteRcdte,
                 NULL,
                 pnOidClienteRcdte,
                 lnOidConcurso,
                 NULL,
                 pnOidModulo,
                 SYSDATE,
                 psTipoProc);
            END IF;
          
            IF (crecomrecord(xx).numeroNivel > 1) THEN
              INSERT INTO INC_CLIEN_RECDO
                (oid_clie_redo,
                 ind_efec,
                 num_prem,
                 clie_oid_clie,
                 clr3_oid_clie_rete,
                 perd_oid_peri,
                 panp_oid_para_nive_prem,
                 ind_eval,
                 oid_modu,
                 fec_crea,
                 ind_tipo_proc)
              VALUES
                (INC_CLRE_SEQ.nextval,
                 NULL,
                 NULL,
                 pnOidCliente,
                 lnOidClienteRcdte,
                 pnOidPeriodo,
                 NULL,
                 NULL,
                 pnOidModulo,
                 SYSDATE,
                 psTipoProc);
            
            ELSE
              FOR z IN (SELECT oidPremio, numeroPremio
                          FROM (SELECT pnp.oid_para_nive_prem oidPremio,
                                       lpa.num_prem           numeroPremio
                                  FROM inc_param_gener_premi pgp,
                                       inc_param_nivel_premi pnp,
                                       inc_premi_artic       pa,
                                       inc_lote_premi_artic  lpa,
                                       inc_premi_monet       pm,
                                       inc_premi_descu       pd,
                                       inc_premi_punto       pp
                                 WHERE pgp.copa_oid_para_gral = lnOidConcurso
                                   AND pgp.oid_para_gene_prem =
                                       pnp.pagp_oid_para_gene_prem
                                   AND pnp.oid_para_nive_prem =
                                       pa.panp_oid_para_nive_prem(+)
                                   AND lpa.prar_oid_prem_arti(+) =
                                       pa.oid_prem_arti
                                   AND pnp.oid_para_nive_prem =
                                       pm.panp_oid_para_nive_prem(+)
                                   AND pnp.oid_para_nive_prem =
                                       pd.oid_para_nive_prem(+)
                                   AND pnp.oid_para_nive_prem =
                                       pp.panp_oid_para_nive_prem(+)
                                 ORDER BY pnp.num_nive, numeroPremio)
                         WHERE ROWNUM = 1) LOOP
              
                INSERT INTO INC_CLIEN_RECDO
                  (oid_clie_redo,
                   ind_efec,
                   num_prem,
                   clie_oid_clie,
                   clr3_oid_clie_rete,
                   perd_oid_peri,
                   panp_oid_para_nive_prem,
                   ind_eval,
                   oid_modu,
                   fec_crea,
                   ind_tipo_proc)
                VALUES
                  (INC_CLRE_SEQ.nextval,
                   NULL,
                   z.numeroPremio,
                   pnOidCliente,
                   lnOidClienteRcdte,
                   pnOidPeriodo,
                   z.oidPremio,
                   NULL,
                   pnOidModulo,
                   SYSDATE,
                   psTipoProc);
              END LOOP;
            END IF;
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_recomendacion%NOTFOUND;
    END LOOP;
    CLOSE c_recomendacion;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_INSER_REGIS_RECOM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_INSER_REGIS_RECOM;

  /**************************************************************************
  Descripcion       : Cierra los Concuros
  Fecha Creacion    : 07/03/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
    psServer         :  Nombre del Servidor
  
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE INC_PR_CERRA_CONCU(psCodigoPais    VARCHAR2,
                               psCodigoMarca   VARCHAR2,
                               psCodigoCanal   VARCHAR2,
                               psCodigoPeriodo VARCHAR2,
                               psUsuario       VARCHAR2,
                               psServer        VARCHAR2) IS
    CURSOR c_concursocandidato(vnIdPais    NUMBER,
                               vnIdMarca   NUMBER,
                               vnIdCanal   NUMBER,
                               vnIdPeriodo NUMBER) IS
      SELECT cpg.oid_para_gral oidConcurso,
             vc.esc2_oid_esta_conc oidEstadoConcurso,
             vc.copa_oid_para_gral_orig oidConcursoOrigen,
             cpg.perd_oid_peri_desd oidPeriodoDesde,
             cpg.perd_oid_peri_hast oidPeriodoHasta,
             pn.num_pedi_eval periodosEvaluar,
             pgp.num_peri periodosObtencion,
             pgp.perd_oid_peri oidPeriodoDespacho,
             pr.perd_oid_peri_prem oidPeriodoPremiacion,
             mul.num_peri_sobr_calc periodoSobreCalculo,
             TRUNC(SYSDATE) fechaActual,
             cpg.num_conc numeroConcurso,
             --           cpc.val_peri_eval           periodosRecomendadas
             (case
               when cpg.bcal_oid_base_calc = 4 then
                greatest(cpc.num_mini_pedi, num_mini_pedi_reco)
               when cpg.bcal_oid_base_calc in (1, 2) and
                    pgp.perd_oid_peri is null then
                3
               else
                0
             end) periodosRecomendadas
        FROM inc_concu_param_gener cpg,
             inc_versi_concu       vc,
             inc_progr_nueva       pn,
             inc_param_gener_premi pgp,
             inc_param_ranki       pr,
             inc_multi             mul,
             inc_concu_param_consu cpc,
             inc_clasi_concu       clasi -- Ajuste concurso ficticio
       WHERE cpg.pais_oid_pais = vnIdPais
         AND vc.copa_oid_para_gral = cpg.oid_para_gral
         AND vc.vico_oid_vige_conc NOT IN (4, 5, 6) -- Excluye los cerrados o cancelados
         AND pn.copa_oid_para_gral(+) = cpg.oid_para_gral
         AND pgp.copa_oid_para_gral(+) = cpg.oid_para_gral
         AND pr.copa_oid_para_gral(+) = cpg.oid_para_gral
         AND mul.copa_oid_para_gral(+) = cpg.oid_para_gral
         AND cpc.copa_oid_para_gral(+) = cpg.oid_para_gral
         AND oid_clas_conc(+) = CCON_OID_CLAS_CONC -- Ajuste concurso ficticio
         AND NVL(cod_clas_conc, 'R') <> 'F'; -- Ajuste concurso ficticio
  
    TYPE concursocandidatoRec IS RECORD(
      oidConcurso          INC_CONCU_PARAM_GENER.oid_para_gral%TYPE,
      oidEstadoConcurso    INC_VERSI_CONCU.ESC2_OID_ESTA_CONC%TYPE,
      oidConcursoOrigen    INC_CONCU_PARAM_GENER.oid_para_gral%TYPE,
      oidPeriodoDesde      INC_CONCU_PARAM_GENER.perd_oid_peri_desd%TYPE,
      oidPeriodoHasta      INC_CONCU_PARAM_GENER.perd_oid_peri_desd%TYPE,
      periodosEvaluar      INC_PROGR_NUEVA.NUM_PEDI_EVAL%TYPE,
      periodosObtencion    INC_PARAM_GENER_PREMI.NUM_PERI%TYPE,
      oidPeriodoDespacho   INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE,
      oidPeriodoPremiacion inc_param_ranki.PERD_OID_PERI_PREM%TYPE,
      periodoSobreCalculo  INC_MULTI.NUM_PERI_SOBR_CALC%TYPE,
      fechaActual          DATE,
      numeroConcurso       INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
      periodosRecomendadas INC_CONCU_PARAM_CONSU.VAL_PERI_EVAL%TYPE);
  
    TYPE concursocandidatoRecTab IS TABLE OF concursocandidatoRec;
    concursocandidatoRecord concursocandidatoRecTab;
  
    lnIdPais            NUMBER;
    lnIdCanal           NUMBER;
    lnIdMarca           NUMBER;
    lnIdPeriodo         NUMBER;
    lvconcursoscerrados VARCHAR2(499);
    --
    lnperiodo1     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo2     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo3     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo4     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo5     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo6     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodo7     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnperiodofinal SEG_PERIO_CORPO.COD_PERI%TYPE;
  
  BEGIN
    lnIdPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnIdCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnIdMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  
    /* Procesando Concursos a Cerrar */
    OPEN c_concursocandidato(lnIdPais, lnIdMarca, lnIdCanal, lnIdPeriodo);
    LOOP
      FETCH c_concursocandidato BULK COLLECT
        INTO concursocandidatoRecord LIMIT W_FILAS;
      IF concursocandidatoRecord.COUNT > 0 THEN
        FOR x IN concursocandidatoRecord.FIRST .. concursocandidatoRecord.LAST LOOP
        
          lnperiodo1 := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                               .oidPeriodoHasta);
        
          lnperiodo2 := GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                                                  .oidPeriodoHasta),
                                           NVL(concursocandidatoRecord(x)
                                               .periodosEvaluar,
                                               0));
        
          lnperiodo3 := GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                                                  .oidPeriodoHasta),
                                           NVL(concursocandidatoRecord(x)
                                               .periodosRecomendadas,
                                               0));
        
          lnperiodo4 := GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                                                  .oidPeriodoHasta),
                                           NVL(concursocandidatoRecord(x)
                                               .periodosObtencion,
                                               0));
        
          lnperiodo5 := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                               .oidPeriodoDespacho);
          lnperiodo6 := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                               .oidPeriodoPremiacion);
          lnperiodo7 := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(concursocandidatoRecord(x)
                                                               .periodoSobreCalculo);
        
          -- Obtenemos Periodo Final Maximo --
          lnperiodofinal := Greatest(lnperiodo1,
                                     lnperiodo2,
                                     lnperiodo3,
                                     lnperiodo4,
                                     lnperiodo5,
                                     lnperiodo6,
                                     lnperiodo7);
        
          /*
                      -- Obtenemos Fecha Final de periodo final mayor --
                      SELECT fec_fina
                        INTO ldfecfina
                        FROM cra_perio
                       WHERE oid_peri = lnperiodofinal;
          
                      -- Obtenemos Fecha Proceso de periodo actual --
                      SELECT fec_proc
                        INTO ldfecproc
                        FROM bas_ctrl_fact bas
                       WHERE cod_peri = psCodigoPeriodo;
          
          */
          -- Procesando Concursos
          IF psCodigoPeriodo >= lnperiodofinal THEN
          
            -- actualiza en paramtros generales concurso --
            UPDATE inc_concu_param_gener cpg
               SET cpg.ind_acti = 0,
                   cpg.usu_modi = psUsuario,
                   cpg.fec_modi = SYSDATE
             WHERE cpg.oid_para_gral = concursocandidatorecord(x)
                  .oidconcurso;
          
            -- actualiza en version concurso --
            UPDATE inc_versi_concu vc
               SET vc.vico_oid_vige_conc = 6,
                   vc.usu_modi           = psUsuario,
                   vc.fec_modi           = SYSDATE
             WHERE vc.copa_oid_para_gral = concursocandidatorecord(x)
                  .oidconcurso;
          
            lvconcursoscerrados := TRIM(SUBSTR(lvconcursoscerrados || CASE
                                                 WHEN lvconcursoscerrados is NULL THEN
                                                  ''
                                                 ELSE
                                                  ','
                                               END || concursocandidatorecord(x)
                                               .oidconcurso,
                                               1,
                                               499));
          
            --             END IF;
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_concursocandidato%NOTFOUND;
    END LOOP;
    CLOSE c_concursocandidato;
  
    -- Actualiza Historico Concursos Cerrados --
    INSERT INTO inc_histo_activ_cerra
      (oid_hist_acti_cerr,
       val_serv,
       perd_oid_peri,
       fec_proc,
       val_tipo_proc,
       val_usua,
       val_obse)
    VALUES
      (inc_hiac_seq.NEXTVAL,
       psServer,
       lnIdPeriodo,
       SYSDATE,
       'Cerrado',
       psUsuario,
       NVL(lvconcursoscerrados, ' '));
  
    -- Refrescar Concursos --
    DELETE inc_ultim_actua_concu;
  
    INSERT INTO inc_ultim_actua_concu
      (val_serv, fec_crea)
    VALUES
      (psServer, SYSDATE);
  
    -- Se inserta en forma temporal procedimiento para bloquear entrega de premios en la campaña en curso. Solo para Colombia --
  
    /*
      update inc_premi_elegi set ind_pend=1 where oid_prem_eleg in
      (select oid_prem_eleg
      from inc_premi_elegi ele, cra_perio,inc_concu_param_gener con
               where con.oid_para_gral = ele.copa_oid_para_gral
        and bcal_oid_base_calc=1 and ind_acti=1
        and fec_sist <= fec_fina
        and oid_peri = lnidperiodo and ind_pend=3);
    */
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CERRA_CONCU: ' || ls_sqlerrm);
  END INC_PR_CERRA_CONCU;

  /**************************************************************************
  Descripcion       : Valida y Carga los premios electivos
  Fecha Creacion    : 10/05/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso    : Numero de Concurso
    psCodigoUsuario    :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PREMI_ELECT(psCodigoPais     VARCHAR2,
                                     psNumeroConcurso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS
    lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    lnOidCliente    MAE_CLIEN.OID_CLIE%TYPE;
    lsPeriodoActual SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lsCodigoCliente  MAE_CLIEN.COD_CLIE%TYPE;
    lsCodigoVenta    VARCHAR2(20);
    lnNumeroUnidades NUMBER;
  
    lsMotivoRechazo INC_MOTIV_PREMI_INVAL.COD_MOTI_INVA%TYPE;
    lnOcurrencias   NUMBER;
    lsResult        VARCHAR2(3);
  
    lnOidParaGral    INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
    lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
    lnOidNivelPremio INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE;
    lnNumeroPuntos   INC_PARAM_NIVEL_PREMI.NUM_CANT_FIJA_PUNT%TYPE;
    lnNumeroPremio   INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE;
  
    lnIndPremAcumNive     INC_PARAM_GENER_PREMI.IND_PREM_ACUM_NIVE%TYPE;
    lnSaldoPuntos         NUMBER;
    lnPuntajeComprometido NUMBER;
  
    lsPeriodoProceso BAS_CTRL_FACT.COD_PERI%TYPE;
  
    CURSOR c_Premios IS
      SELECT NUM_FILA, COD_CLIE, COD_VENT, NUM_UNID
        FROM INC_TMP_PREMI_ELECT
       ORDER BY NUM_FILA;
  
    TYPE interfazPremios IS RECORD(
      numeroFila     INC_TMP_PREMI_ELECT.NUM_FILA%TYPE,
      codigoCliente  INC_TMP_PREMI_ELECT.COD_CLIE%TYPE,
      codigoVenta    INC_TMP_PREMI_ELECT.COD_VENT%TYPE,
      numeroUnidades INC_TMP_PREMI_ELECT.NUM_UNID%TYPE);
  
    TYPE interfazPremiosTab IS TABLE OF interfazPremios;
    interfazRecordN interfazPremiosTab;
  
  BEGIN
    --Recuperamos el oid Pais, Canal
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Obtenemos el Periodo Actual
    lsPeriodoActual := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(lnOidPais,
                                                                'T',
                                                                lnOidCanal);
  
    --Obtenemos el Periodo Proceso
    SELECT COD_PERI
      INTO lsPeriodoProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --(1) PROCESAMOS A LOS PREMIOS
    OPEN c_Premios;
    LOOP
      FETCH c_Premios BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Seteamos las variables
          lsMotivoRechazo   := NULL;
          lnOidCliente      := NULL;
          lnOidParaGral     := NULL;
          lsNumeroConcurso  := psNumeroConcurso;
          lnOidNivelPremio  := NULL;
          lnNumeroPuntos    := NULL;
          lnNumeroPremio    := NULL;
          lnIndPremAcumNive := NULL;
        
          lsCodigoCliente  := interfazRecordN(x).codigoCliente;
          lsCodigoVenta    := interfazRecordN(x).codigoVenta;
          lnNumeroUnidades := interfazRecordN(x).numeroUnidades;
        
          --0. Validar si la consultora existe en el Maestro de consultoras
          BEGIN
            SELECT OID_CLIE
              INTO lnOidCliente
              FROM MAE_CLIEN
             WHERE COD_CLIE = lsCodigoCliente;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lsMotivoRechazo := '0';
          END;
        
          --1. Se valida si el número de premio ingresado corresponde al concurso
          IF (lsMotivoRechazo IS NULL) THEN
            BEGIN
              SELECT cpg.oid_para_gral,
                     cpg.num_conc,
                     pnp.oid_para_nive_prem,
                     DECODE(pnp.num_cant_fija_punt,
                            null,
                            pnp.num_cant_inic_punt,
                            pnp.num_cant_fija_punt) puntos,
                     lpa.num_prem,
                     pgp.ind_prem_acum_nive
                INTO lnOidParaGral,
                     lsNumeroConcurso,
                     lnOidNivelPremio,
                     lnNumeroPuntos,
                     lnNumeroPremio,
                     lnIndPremAcumNive
                FROM inc_concu_param_gener cpg,
                     inc_param_gener_premi pgp,
                     inc_param_nivel_premi pnp,
                     inc_premi_artic       pa,
                     inc_lote_premi_artic  lpa,
                     inc_artic_lote        al,
                     mae_produ             p
               WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
                 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
                 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
                 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
                 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
                 AND al.prod_oid_prod = p.oid_prod
                 AND al.cod_vent_fict = lsCodigoVenta
                 AND cpg.Num_Conc = psNumeroConcurso;
            
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                lsMotivoRechazo := '1';
            END;
          END IF;
        
          --2. El concurso seleccionado en la patalla debe estar activo
          IF (lsMotivoRechazo IS NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_CONCU_PARAM_GENER
             WHERE OID_PARA_GRAL = lnOidParaGral --concurso
               AND IND_ACTI = 1;
          
            IF (lnOcurrencias = 0) THEN
              lsMotivoRechazo := '2';
            END IF;
          END IF;
        
          --3. Concurso debe ser de premios electivos, para ello el siguiente query debe devolver algún registro
          IF (lsMotivoRechazo IS NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_PARAM_GENER_PREMI
             WHERE COPA_OID_PARA_GRAL = lnOidParaGral --concurso
               AND IND_PREM_ELEC = 1;
          
            IF (lnOcurrencias = 0) THEN
              lsMotivoRechazo := '3';
            END IF;
          END IF;
        
          --4.  Se valida que el nivel del premio sea de niveles electivos, para ello el siguiente query
          --debe devolver algún registro
          IF (lsMotivoRechazo IS NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
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
               AND al.cod_vent_fict = lsCodigoVenta
               AND pnp.val_nive_sele = 1;
          
            IF (lnOcurrencias = 0) THEN
              lsMotivoRechazo := '4';
            END IF;
          END IF;
        
          --5. Se valida si la campaña de proceso es válida para poder registrar premios electivos para el concurso,
          --para ello el siguiente query debe devolver algún registro
          /*IF(lsMotivoRechazo IS NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
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
                           WHEN pgp.perd_oid_peri is null THEN
                            Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(gen_pkg_gener.gen_fn_devuelve_des_perio(cpg.perd_oid_peri_hast),
                                                                   lnOidPais, -- pais
                                                                   Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T'),
                                                                   Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD'),
                                                                   2)
                           ELSE
                            gen_pkg_gener.gen_fn_devuelve_des_perio(pgp.perd_oid_peri)
                         END perhastvali
                    FROM inc_concu_param_gener cpg, inc_param_gener_premi pgp
                   WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral) t
           WHERE t.oid_para_gral = lnOidParaGral --concurso
             AND t.perinicvali <= lsPeriodoActual -- periodo actual
             AND t.perhastvali >= lsPeriodoActual; -- periodo actual
          
            IF(lnOcurrencias = 0) THEN
              lsMotivoRechazo := '5';
            END IF;
          END IF;*/
        
          --6. Se valida que la tipología de la consultora pertenezca a la tipología del concurso
          /*IF(lsMotivoRechazo IS NULL) THEN
            lsResult := LOV_PKG_PROCE.LOV_FN_VALID_CLASI_CONCU(lnOidParaGral, lnOidCliente);
          
            IF(lsResult <> '1') THEN
              lsMotivoRechazo := '6';
            END IF;
          END IF;*/
        
          --7. Se valida que el ámbito geográfico de la consultora pertenezca al ámbito geográfico del concurso
          /*IF(lsMotivoRechazo IS NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_AMBIT_GEOGR geo
             WHERE geo.copa_oid_para_gral = lnOidParaGral;
          
            IF(lnOcurrencias > 0) THEN
              lsResult := INC_PKG_PROCE_INCEN.INC_FN_VALID_AMBIT_GEOGR_CONCU(lnOidParaGral, lnOidCliente);
          
              IF(lsResult <> '1') THEN
                lsMotivoRechazo := '7';
              END IF;
            END IF;
          END IF;*/
        
          --8.  Se valida el saldo disponible para el premio elegido con los siguientes pasos
          /*IF(lsMotivoRechazo IS NULL) THEN
            --Se calcula el Saldo de puntos
            SELECT NVL(SUM(NUM_PUNT),0)
              INTO lnSaldoPuntos
              FROM INC_CUENT_CORRI_PUNTO
             WHERE CLIE_OID_CLIE = lnOidCliente
               AND COPA_OID_PARA_GRAL = lnOidParaGral;
          
            --Se calcula el Puntaje comprometido
            IF(lnIndPremAcumNive = 1) THEN
              BEGIN
                SELECT NUM_CANT_INIC_PUNT
                 INTO lnPuntajeComprometido
                 FROM (
                       SELECT niv.NUM_NIVE, NVL(niv.NUM_CANT_INIC_PUNT,0) NUM_CANT_INIC_PUNT
                         FROM INC_PREMI_ELEGI ele, INC_PARAM_NIVEL_PREMI niv
                        WHERE ele.CLIE_OID_CLIE = lnOidCliente
                          AND ele.COPA_OID_PARA_GRAL = lnOidParaGral
                          AND ele.Panp_Oid_Para_Nive_Prem = niv.Oid_Para_Nive_Prem
                          AND ele.IND_PEND = 1
                        ORDER BY niv.NUM_NIVE DESC)
                WHERE ROWNUM = 1;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  lnPuntajeComprometido := 0;
              END;
            ELSE
              SELECT NVL(SUM(NVL(NUM_CANT_INIC_PUNT,0)),0)
                INTO lnPuntajeComprometido
                FROM INC_PREMI_ELEGI ele, INC_PARAM_NIVEL_PREMI niv
               WHERE ele.CLIE_OID_CLIE = lnOidCliente
                 AND ele.COPA_OID_PARA_GRAL = lnOidParaGral
                 AND ele.Panp_Oid_Para_Nive_Prem = niv.Oid_Para_Nive_Prem
                 AND ele.IND_PEND = 1;
            END IF;
          
            --Calcular el Puntaje disponible restando Saldo de puntos - Puntaje comprometido
            IF((lnSaldoPuntos - lnPuntajeComprometido) < lnNumeroPuntos) THEN
              lsMotivoRechazo := '8';
            END IF;
          
          END IF;*/
        
          --Si se pasan las validaciones, entonces se graba en INC_PREMI_ELEGI
          IF (lsMotivoRechazo IS NULL) THEN
          
            --Se grabará un registro por cada unidad ingresada
            FOR k IN 1 .. lnNumeroUnidades LOOP
              INSERT INTO INC_PREMI_ELEGI
                (OID_PREM_ELEG,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PANP_OID_PARA_NIVE_PREM,
                 NUM_PREM,
                 FEC_SIST,
                 IND_PEND,
                 USU_CREA,
                 PERD_OID_PERI,
                 TIP_RECE)
              VALUES
                (INC_PREL_SEQ.nextval,
                 lnOidParaGral,
                 lnOidCliente,
                 lnOidNivelPremio,
                 lnNumeroPremio,
                 SYSDATE,
                 1,
                 psCodigoUsuario,
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsPeriodoProceso),
                 'M');
            END LOOP;
          
            --Se actualiza el regisro de Premios Electivos como Validado
            UPDATE INC_TMP_PREMI_ELECT
               SET IND_VALI      = 1,
                   NUM_CONC      = lsNumeroConcurso,
                   FEC_ULTI_ACTU = SYSDATE
             WHERE NUM_FILA = interfazRecordN(x).numeroFila;
          ELSE
            --En el caso que cualquiera de estas validaciones no se cumpla se finaliza y no se continua con el resto
            --de validaciones y se debe grabar en la tabla INC_PREMI_DIGIT_INVAL
            INSERT INTO INC_PREMI_DIGIT_INVAL
              (COD_CLIE,
               COD_CONC,
               COD_VENT_FICT,
               FEC_DIGI,
               NUM_UNID,
               NUM_PUNT_DISP,
               NUM_PUNT_PREM,
               COD_MOTI_INVA,
               COD_USUA,
               CLIE_OID_CLIE,
               COPA_OID_PARA_GRAL,
               PANP_OID_PARA_NIVE_PREM,
               NUM_PREM,
               COD_PERI)
            VALUES
              (lsCodigoCliente,
               lsNumeroConcurso,
               lsCodigoVenta,
               SYSDATE,
               lnNumeroUnidades,
               NULL,
               lnNumeroPuntos,
               lsMotivoRechazo,
               psCodigoUsuario,
               lnOidCliente,
               lnOidParaGral,
               lnOidNivelPremio,
               lnNumeroPremio,
               lsPeriodoActual);
          
            --Se actualiza el regisro de Premios Electivos como InValidado
            UPDATE INC_TMP_PREMI_ELECT
               SET IND_VALI      = 0,
                   NUM_CONC      = lsNumeroConcurso,
                   COD_MOTI_RECH = lsMotivoRechazo,
                   FEC_ULTI_ACTU = SYSDATE
             WHERE NUM_FILA = interfazRecordN(x).numeroFila;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_Premios%NOTFOUND;
    END LOOP;
    CLOSE c_Premios;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CARGA_PREMI_ELECT: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_CARGA_PREMI_ELECT;

  /**************************************************************************
  Descripcion       : Proceso que realiza la Activacion de Concursos
  Fecha Creacion    : 10/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psUsuario        :  Codigo de Usuario
    psServer         :  Nombre del Servidor
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTIV_CONCU(psCodigoPais    VARCHAR2,
                               psCodigoMarca   VARCHAR2,
                               psCodigoCanal   VARCHAR2,
                               psCodigoPeriodo VARCHAR2,
                               psUsuario       VARCHAR2,
                               psServer        VARCHAR2) IS
    -- Obtenemos Concursos a Activar --
    CURSOR c_concursocandidato(vnIdPais    NUMBER,
                               vnIdMarca   NUMBER,
                               vnIdCanal   NUMBER,
                               vnIdPeriodo NUMBER) IS
      SELECT cpg.oid_para_gral          oidConcurso,
             vc.esc2_oid_esta_conc      oidEstadoConcurso,
             vc.copa_oid_para_gral_orig oidConcursoOrigen,
             cpg.perd_oid_peri_desd     oidPeriodoDesde,
             cpg.perd_oid_peri_hast     oidPeriodoHasta,
             vc.vico_oid_vige_conc      oidVigenciaConcurso
        FROM inc_concu_param_gener cpg, inc_versi_concu vc, cra_perio peri
       WHERE cpg.pais_oid_pais = vnIdPais
         AND cpg.ind_acti = 1 -- Activo
         AND peri.oid_peri = vnIdPeriodo
         AND peri.pais_oid_pais = vnIdPais
         AND peri.marc_oid_marc = vnIdMarca
         AND peri.cana_oid_cana = vnIdCanal
         AND peri.val_esta = 1 -- Activo
         AND vc.copa_oid_para_gral = cpg.oid_para_gral
         AND vc.perd_oid_peri = peri.oid_peri
         AND vc.vico_oid_vige_conc <> 1; -- No Vigente
  
    TYPE concursocandidatoRec IS RECORD(
      oidConcurso         INC_CONCU_PARAM_GENER.oid_para_gral%TYPE,
      oidEstadoConcurso   INC_VERSI_CONCU.ESC2_OID_ESTA_CONC%TYPE,
      oidConcursoOrigen   INC_CONCU_PARAM_GENER.oid_para_gral%TYPE,
      oidPeriodoDesde     INC_CONCU_PARAM_GENER.perd_oid_peri_desd%TYPE,
      oidPeriodoHasta     INC_CONCU_PARAM_GENER.perd_oid_peri_desd%TYPE,
      oidVigenciaConcurso INC_VERSI_CONCU.vico_oid_vige_conc%TYPE);
  
    TYPE concursocandidatoRecTab IS TABLE OF concursocandidatoRec;
    concursocandidatoRecord concursocandidatoRecTab;
  
    lnIdPais           NUMBER;
    lnIdCanal          NUMBER;
    lnIdMarca          NUMBER;
    lnIdPeriodo        NUMBER;
    lvconcursosactivos VARCHAR2(200);
    W_FILAS            NUMBER := 1000;
    ln_sqlcode         NUMBER;
    ls_sqlerrm         VARCHAR2(1000);
  
  BEGIN
  
    lnIdPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnIdCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnIdMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  
    /* Procesando Concursos a Activar */
    OPEN c_concursocandidato(lnIdPais, lnIdMarca, lnIdCanal, lnIdPeriodo);
    LOOP
      FETCH c_concursocandidato BULK COLLECT
        INTO concursocandidatoRecord LIMIT W_FILAS;
      IF concursocandidatoRecord.COUNT > 0 THEN
        FOR x IN concursocandidatoRecord.FIRST .. concursocandidatoRecord.LAST LOOP
        
          -- Actualiza en Version Concurso --
          --     IF concursocandidatorecord(x).oidEstadoConcurso <> 11 THEN  --SI NO ESTA APROBADO
          UPDATE inc_versi_concu vc
             SET vc.vico_oid_vige_conc = 1,
                 vc.esc2_oid_esta_conc = 2,
                 vc.usu_modi           = psUsuario,
                 vc.fec_modi           = SYSDATE
           WHERE vc.copa_oid_para_gral = concursocandidatorecord(x)
                .oidconcurso;
        
          lvconcursosactivos := lvconcursosactivos || CASE
                                  WHEN lvconcursosactivos is NULL THEN
                                   ''
                                  ELSE
                                   ','
                                END || concursocandidatorecord(x).oidconcurso;
        
        END LOOP;
      END IF;
      EXIT WHEN c_concursocandidato%NOTFOUND;
    END LOOP;
    CLOSE c_concursocandidato;
  
    -- Actualiza Historico Concursos Cerrados --
  
    IF lvconcursosactivos IS NOT NULL THEN
      INSERT INTO inc_histo_activ_cerra
        (oid_hist_acti_cerr,
         val_serv,
         perd_oid_peri,
         fec_proc,
         val_tipo_proc,
         val_usua,
         val_obse)
      VALUES
        (INC_HIAC_SEQ.NEXTVAL,
         psServer,
         lnIdPeriodo,
         SYSDATE,
         'Activacion',
         psUsuario,
         NVL(lvconcursosactivos, ' '));
    END IF;
  
    --Se activa Indicador de Refresco de Concursos para ejecutar Factoria de SICC
    DELETE INC_ULTIM_ACTUA_CONCU;
  
    INSERT INTO INC_ULTIM_ACTUA_CONCU
      (val_serv, fec_crea)
    VALUES
      ('--', SYSDATE);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTIV_CONCU: ' || ls_sqlerrm);
  END INC_PR_ACTIV_CONCU;

  /**************************************************************************
  Descripcion       : Regulariza el registro de recomendaciones en tablas de incentivos
  Fecha Creacion    : 01/02/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Marca
    psIndicadorProceso : F: Facturacion,
                         P: Inicio de Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_REGIS_RECOM_INICI(psCodigoPais       VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psUsuario          VARCHAR2,
                                           psServer           VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsCodPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoProceso  CRA_PERIO.OID_PERI%TYPE;
  
    lnOidClienteRcdte INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidConcurso     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lnTotal            NUMBER;
    lnPasoPedido       NUMBER;
    lnTotalPedidoRecom NUMBER;
  
    lsTipoProceso VARCHAR2(1);
  
    CURSOR c_Pedidos(oidPeriodo NUMBER, oidPeriodoActual NUMBER) IS
      SELECT DISTINCT cli.OID_CLIE,
                      vin.CLIE_OID_CLIE_VNTE,
                      INC_FN_VERIF_PEDID_CLIEN(cli.OID_CLIE, oidPeriodo),
                      adi.ESTA_OID_ESTA_CLIE
        FROM MAE_CLIEN             cli,
             MAE_CLIEN_DATOS_ADICI adi,
             MAE_CLIEN_VINCU       vin,
             mae_clien_tipo_subti  mcts,
             CRA_PERIO             per,
             MAE_CLIEN_PRIME_CONTA pconta
       WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
         AND cli.OID_CLIE = vin.CLIE_OID_CLIE_VNDO
         AND cli.oid_clie = mcts.clie_oid_clie
         AND cli.oid_clie = pconta.clie_oid_clie(+)
         AND mcts.ticl_oid_tipo_clie = 2
         AND vin.tivc_oid_tipo_vinc = 9
         AND adi.ESTA_OID_ESTA_CLIE IN (1, 7) --REGISTRADAS y RETIRADAS
         AND per.OID_PERI = oidPeriodo
         AND FEC_DESD <= per.FEC_FINA
         AND (FEC_DESD >= per.FEC_INIC or pconta.PERD_OID_PERI = oidPeriodo)
         AND NOT EXISTS
       (SELECT *
                FROM INC_CLIEN_RECDO
               WHERE CLIE_OID_CLIE = cli.OID_CLIE
                 AND PERD_OID_PERI = oidPeriodoActual);
  
    TYPE interfazPedidos IS RECORD(
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      oidClienteVnte MAE_CLIEN.OID_CLIE%TYPE,
      indPasoPedido  NUMBER,
      estCliente     NUMBER);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    CURSOR c_recomendacion(oidPais        NUMBER,
                           oidPeriodo     NUMBER,
                           oidClienteVnte NUMBER) IS
      SELECT oidconcurso, numeronivel
        FROM (SELECT cpg.oid_para_gral oidconcurso, pgp.num_nive numeronivel
                FROM inc_concu_param_gener cpg,
                     inc_param_gener_premi pgp,
                     cra_perio             peridesde,
                     cra_perio             perihasta,
                     cra_perio             periactu,
                     seg_perio_corpo       speridesde,
                     seg_perio_corpo       sperihasta
               WHERE cpg.diri_oid_diri = 1 --es dirigido a la Consultora
                 AND cpg.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                 AND pgp.copa_oid_para_gral = cpg.oid_para_gral
                 AND periactu.oid_peri = oidPeriodo -- Periodo Actual
                 AND peridesde.fec_inic <= periactu.fec_inic
                 AND periactu.fec_fina <= perihasta.fec_fina
                 AND cpg.perd_oid_peri_desd = peridesde.oid_peri
                 AND cpg.perd_oid_peri_hast = perihasta.oid_peri
                 AND cpg.pais_oid_pais = oidPais -- Oid del País
                 AND cpg.ind_acti = 1
                 AND speridesde.oid_peri = peridesde.peri_oid_peri
                 AND sperihasta.oid_peri = perihasta.peri_oid_peri);
  
    TYPE crecomrec IS RECORD(
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroNivel INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE);
  
    TYPE crecomrectab IS TABLE OF crecomrec;
    crecomrecord crecomrectab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --RECUPERAMOS EL PERIODO ANTERIOR
    lsCodPeriodoAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                   lnOidPais,
                                                                   lnOidMarca,
                                                                   lnOidCanal,
                                                                   -1);
  
    lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAnterior,
                                                                       lnOidMarca,
                                                                       lnOidCanal);
  
    --DEFINIMOS SI CONSULTAMOS CONSULTORAS DEl PERIODO ANTERIO O DEL PERIODO ACTUAL
    IF (psIndicadorProceso = PROCESO_INICIO_CAMPANA) THEN
      lnOidPeriodoProceso := lnOidPeriodoAnterior;
      lsTipoProceso       := 'I';
    ELSIF (psIndicadorProceso = PROCESO_FACTURACION) THEN
      lnOidPeriodoProceso := lnOidPeriodo;
      lsTipoProceso       := 'F';
    END IF;
  
    --(1) PROCESAMOS A LAS CONSULTORAS CON ESTATUS=REGISTRADA
    OPEN c_Pedidos(lnOidPeriodoProceso, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Verificamos que no hay pasado Pedido
          lnPasoPedido := interfazRecordN(x).indPasoPedido;
        
          IF (lnPasoPedido = 0) THEN
          
            SELECT COUNT(1)
              INTO lnTotalPedidoRecom
              FROM INC_CLIEN_RECDO rdo, INC_PEDID_CONCU_RECOM rec
             WHERE rdo.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND rdo.PERD_OID_PERI = lnOidPeriodoProceso
               AND rdo.OID_CLIE_REDO = rec.CLRE_OID_CLIE_REDO;
          
            IF (lnTotalPedidoRecom = 0) THEN
            
              DELETE FROM INC_CLIEN_RECDO rdo
               WHERE rdo.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND rdo.PERD_OID_PERI = lnOidPeriodoProceso;
 --           END IF;
            --Insertamos los concursos de Recomendaciones para la Consultora
            INC_PR_INSER_REGIS_RECOM(lnOidPais,
                                     interfazRecordN  (x).oidCliente,
                                     lnOidPeriodo,
                                     interfazRecordN  (x).oidClienteVnte,
                                     MODULO_INCENTIVOS,
                                     lsTipoProceso);
            END IF;
            --Actualizamos Primer Contacto solo para Estatus = 1
            IF interfazRecordN(x).estCliente = 1 THEN
              UPDATE MAE_CLIEN_PRIME_CONTA
                 SET PERD_OID_PERI = lnOidPeriodo
               WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
            
              UPDATE MAE_CLIEN_HISTO_ESTAT
                 SET PERD_OID_PERI = lnOidPeriodo,
                     FEC_MODI      = SYSDATE,
                     USU_MODI      = psUsuario
               WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND PERD_OID_PERI_PERI_FIN IS NULL
                 AND ESTA_OID_ESTA_CLIE = 1;
            END IF;
          
            --         END IF;
          
          END IF;
        
        END LOOP;
      END IF;
    
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REGUL_REGIS_RECOM_INICI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REGUL_REGIS_RECOM_INICI;

  /***************************************************************************
    Descripcion       : Procedimiento que actualiza la tabla de Pedidos por
                        Campaña de las consultoras ( ped_solic_cabec_acum2 ).
    Fecha Creacion    : 01/06/2011
    Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RESUM_PEDID(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2) IS
  
    lnIdPeriodo cra_perio.oid_peri%TYPE;
    lsindpedid  ped_solic_cabec_acum2.val_cant_pedi%TYPE;
    lsindpedidanul ped_solic_cabec_acum2.ind_pedi_anul%TYPE;
    lnOk NUMBER(1) := 0;
  
    /*
      Definir Cursores
    */
  
    CURSOR c_pedacum(vnIdPeriodo INTEGER) IS
    SELECT soca.clie_oid_clie,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  THEN
                        sopo.num_unid_dema_real
                   ELSE
                        0
                   END) val_mont_cata_dema_unid,                    
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  THEN
                        sopo.num_unid_aten * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_aten,                          
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4 AND tofe.val_form_vent = 1 AND cana.cod_cana = 'VD' THEN
                        sopo.val_prec_neto_tota_loca
                   ELSE
                        0
                 END) val_mont_form_cata,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli = 4  THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema_anul,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=1 THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema_Lbel,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=2 THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema_Esika,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=3 THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema_Cyzone,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod not in (1,2,3) THEN
                        sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_dema_OtrasMarcas,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=1 THEN
                        sopo.num_unid_aten * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_aten_Lbel,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=2 THEN
                        sopo.num_unid_aten * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_aten_Esika,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod=3 THEN
                        sopo.num_unid_aten * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_aten_Cyzone,
             SUM(CASE
                   WHEN conso.esso_oid_esta_soli <> 4  and prod.mapr_oid_marc_prod not in (1,2,3) THEN
                        sopo.num_unid_aten * sopo.val_prec_cata_unit_loca
                   ELSE
                        0
                 END) val_mont_cata_aten_OtrasMarcas,
             MAX(sca2.oid_soca_acu2) oid_soca_acu2
        FROM ped_solic_posic       sopo,
             ped_solic_cabec       soca,
             ped_solic_cabec       conso,
             ped_tipo_solic_pais   tspa,
             ped_tipo_solic        tsol,
             ped_solic_cabec_acum2 sca2,
             pre_ofert_detal       ofde,
             pre_tipo_ofert        tofe,
             seg_canal             cana,
             mae_produ             prod
       WHERE soca.perd_oid_peri =  vnIdPeriodo
         AND soca.ind_oc = 1
         AND soca.grpr_oid_grup_proc = 5
         AND tsol.cod_tipo_soli = 'SOC'
            --
         AND sopo.soca_oid_soli_cabe = soca.oid_soli_cabe
         AND sopo.ofde_oid_deta_ofer = ofde.oid_deta_ofer(+)
         AND ofde.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer
         AND tofe.cana_oid_cana = cana.oid_cana(+)
         AND soca.soca_oid_soli_cabe = conso.oid_soli_cabe(+)
         AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
         AND soca.clie_oid_clie = sca2.clie_oid_clie(+)
         AND soca.perd_oid_peri = sca2.perd_oid_peri(+)
         AND sopo.prod_oid_prod = prod.oid_prod(+)
         AND sopo.espo_oid_esta_posi != 2 -- Nuevo
       GROUP BY soca.clie_oid_clie;
  
    TYPE pedacumTab IS TABLE OF c_pedacum%ROWTYPE INDEX BY BINARY_INTEGER;
    pedacum pedacumTab;
  
  BEGIN
    -- Buscar oid de la campaña
    lnIdPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPeriodo);
    
    -- Inicializa variables antes de recálculo de cierre de campaña
    update ped_solic_cabec_acum2 set 
                                 val_cant_pedi=0,
                                 ind_pedi_anul=null,
                                 val_mont_tota=0,
                                 val_mont_form_cata=null,
                                 val_unid_tota=null,
                                 val_mont_tota_anul=null,
                                 val_mont_tota_lbel=null,
                                 val_mont_tota_esik=null,
                                 val_mont_tota_cyzo=null,
                                 val_mont_tota_otma=null,
                                 val_mont_aten_lbel=null,
                                 val_mont_aten_esik=null,
                                 val_mont_aten_cyzo=null,
                                 val_mont_aten_otma=null
    where perd_oid_peri= lnIdPeriodo;
     
    OPEN c_pedacum(lnIdPeriodo);
    LOOP
      FETCH c_pedacum BULK COLLECT
        INTO pedacum LIMIT W_FILAS;
    
      IF pedacum.COUNT > 0 THEN
        FOR i IN pedacum.FIRST .. pedacum.LAST LOOP
          
          IF NVL(pedacum(i).val_mont_cata_dema_anul,0)>0 
            OR NVL(pedacum(i).val_mont_cata_dema_unid,0)>0 THEN
               lsindpedid := 1;
          ELSE
               lsindpedid := 0;
          END IF;
          --
          IF NVL(pedacum(i).val_mont_cata_dema_anul,0)>0 AND NVL(pedacum(i).val_mont_cata_dema_unid,0)=0 THEN
               lsindpedidanul := '1';
          ELSE
               lsindpedidanul := null;
          END IF;
                           
          UPDATE ped_solic_cabec_acum2 soc2
             SET soc2.val_cant_pedi      = lsindpedid,
                 soc2.val_mont_tota      = pedacum(i).val_mont_cata_dema,
                 soc2.val_mont_cata      = pedacum(i).val_mont_cata_aten,
                 soc2.val_mont_form_cata = pedacum(i).val_mont_form_cata,
                 soc2.val_unid_tota      = pedacum(i).val_mont_cata_dema_unid,
                 soc2.val_mont_tota_anul = pedacum(i).val_mont_cata_dema_anul,
                 soc2.val_mont_tota_lbel = pedacum(i).val_mont_cata_dema_Lbel,
                 soc2.val_mont_tota_esik = pedacum(i).val_mont_cata_dema_Esika,
                 soc2.val_mont_tota_cyzo = pedacum(i).val_mont_cata_dema_Cyzone,
                 soc2.val_mont_tota_otma = pedacum(i).val_mont_cata_dema_OtrasMarcas,
                 soc2.val_mont_aten_lbel = pedacum(i).val_mont_cata_aten_Lbel,
                 soc2.val_mont_aten_esik = pedacum(i).val_mont_cata_aten_Esika,
                 soc2.val_mont_aten_cyzo = pedacum(i).val_mont_cata_aten_Cyzone,
                 soc2.val_mont_aten_otma = pedacum(i).val_mont_cata_aten_OtrasMarcas,
                 soc2.ind_pedi_anul      = lsindpedidanul,
                 soc2.fec_modi           = sysdate
           WHERE soc2.oid_soca_acu2 = pedacum(i).oid_soca_acu2;
           
           
           IF pedacum(i).oid_soca_acu2 IS NULL THEN
             BEGIN
              INSERT INTO ped_solic_cabec_acum2
                     (oid_soca_acu2,
                     clie_oid_clie,
                     perd_oid_peri,                     
                     val_cant_pedi,
                     val_mont_tota,
                     val_mont_cata,
                     val_mont_form_cata,
                     val_unid_tota,
                     val_mont_tota_anul,
                     val_mont_tota_lbel,
                     val_mont_tota_esik,
                     val_mont_tota_cyzo,
                     val_mont_tota_otma,
                     val_mont_aten_lbel,
                     val_mont_aten_esik,
                     val_mont_aten_cyzo,
                     val_mont_aten_otma,
                     ind_pedi_anul,
                     fec_crea)
              VALUES
                     (ped_sca2_seq.nextval,
                     pedacum(i).clie_oid_clie,
                     lnIdPeriodo,
                     lsindpedid,
                     pedacum(i).val_mont_cata_dema,
                     pedacum(i).val_mont_cata_aten,
                     pedacum(i).val_mont_form_cata,
                     pedacum(i).val_mont_cata_dema_unid,
                     pedacum(i).val_mont_cata_dema_anul,
                     pedacum(i).val_mont_cata_dema_Lbel,
                     pedacum(i).val_mont_cata_dema_Esika,
                     pedacum(i).val_mont_cata_dema_Cyzone,
                     pedacum(i).val_mont_cata_dema_OtrasMarcas,
                     pedacum(i).val_mont_cata_aten_Lbel,
                     pedacum(i).val_mont_cata_aten_Esika,
                     pedacum(i).val_mont_cata_aten_Cyzone,
                     pedacum(i).val_mont_cata_aten_OtrasMarcas,
                     lsindpedidanul,
                     sysdate
                     );
            EXCEPTION WHEN dup_val_on_index THEN
                lnOk := 0;         
            END;
           END IF;
                      
             --AND ( /*NVL(soc2.val_mont_tota,0) != NVL(pedacum(i).val_mont_cata_dema,0) OR*/
      --            NVL(soc2.val_mont_cata, 0) !=
      --            NVL(pedacum(i).val_mont_cata_aten, 0) OR
      --            NVL(soc2.val_mont_form_cata, 0) !=
      --            NVL(pedacum(i).val_mont_form_cata, 0));
                  
        END LOOP;
      END IF;
      EXIT WHEN c_pedacum%NOTFOUND;
    END LOOP;
    CLOSE c_pedacum;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_RESUM_PEDID: ' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_RESUM_PEDID;

  /***************************************************************************
    Descripcion       : Procedimiento que actualiza la tabla de Pedidos por
                        Campaña de las consultoras ( ped_solic_cabec_acum2 )
                        SE EJECUTA EN FORMA DIARIA.
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Toctoi
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RESUM_PEDID_DIARI(psCodigoPais VARCHAR2) IS
  
    CURSOR c_pedacum IS
      SELECT soca.clie_oid_clie,
             SUM(sopo.num_unid_dema_real * sopo.val_prec_cata_unit_loca) val_mont_cata_dema,
             SUM(sopo.num_unid_aten * sopo.val_prec_cata_unit_loca) val_mont_cata_aten,
             SUM(CASE
                   WHEN tofe.val_form_vent = 1 AND cana.cod_cana = 'VD' THEN
                    sopo.val_prec_neto_tota_loca
                   ELSE
                    0
                 END) val_mont_form_cata,
             MAX(sca2.oid_soca_acu2) oid_soca_acu2
        FROM ped_solic_posic sopo,
             ped_solic_cabec soca,
             ped_tipo_solic_pais tspa,
             ped_tipo_solic tsol,
             ped_solic_cabec_acum2 sca2,
             pre_ofert_detal ofde,
             pre_tipo_ofert tofe,
             seg_canal cana,
             (SELECT DISTINCT clie_oid_clie, perd_oid_peri
                FROM msg_tmp_pedid_clien
               WHERE cod_tipo_soli = 'SOC') pedidos
       WHERE soca.perd_oid_peri = pedidos.perd_oid_peri
         AND soca.clie_oid_clie = pedidos.clie_oid_clie
         AND soca.ind_oc = 1
         AND soca.grpr_oid_grup_proc = 5
         AND tsol.cod_tipo_soli = 'SOC'
            --
         AND sopo.soca_oid_soli_cabe = soca.oid_soli_cabe
         AND sopo.ofde_oid_deta_ofer = ofde.oid_deta_ofer(+)
         AND ofde.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer
         AND tofe.cana_oid_cana = cana.oid_cana(+)
         AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
         AND soca.clie_oid_clie = sca2.clie_oid_clie(+)
         AND soca.perd_oid_peri = sca2.perd_oid_peri(+)
         AND sopo.espo_oid_esta_posi != 2
       GROUP BY soca.clie_oid_clie;
  
    TYPE pedacumTab IS TABLE OF c_pedacum%ROWTYPE INDEX BY BINARY_INTEGER;
    pedacum pedacumTab;
  
  BEGIN
    OPEN c_pedacum;
    LOOP
      FETCH c_pedacum BULK COLLECT
        INTO pedacum LIMIT W_FILAS;
    
      IF pedacum.COUNT > 0 THEN
        FOR i IN pedacum.FIRST .. pedacum.LAST LOOP
          UPDATE ped_solic_cabec_acum2 soc2
             SET --soc2.val_cant_pedi = 1,
                 --soc2.val_mont_tota = pedacum(i).val_mont_cata_dema,
                       soc2.val_mont_cata = pedacum(i).val_mont_cata_aten,
                 soc2.val_mont_form_cata = pedacum(i).val_mont_form_cata
           WHERE soc2.oid_soca_acu2 = pedacum(i).oid_soca_acu2
             AND ( /*NVL(soc2.val_mont_tota,0) != NVL(pedacum(i).val_mont_cata_dema,0) OR*/
                  NVL(soc2.val_mont_cata, 0) !=
                  NVL(pedacum(i).val_mont_cata_aten, 0) OR
                  NVL(soc2.val_mont_form_cata, 0) !=
                  NVL(pedacum(i).val_mont_form_cata, 0));
        END LOOP;
      END IF;
      EXIT WHEN c_pedacum%NOTFOUND;
    END LOOP;
    CLOSE c_pedacum;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_RESUM_PEDID_DIARI: ' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_RESUM_PEDID_DIARI;

  /***************************************************************************
    Descripcion       : Procedimiento que verifica el registro de pedidos de
                        las recomendadas en INC y crea los registros para
                        aquellas que no lo tienen.
    Fecha Creacion    : 01/06/2011
    Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_PEDID_INCEN(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2) IS
  
    /*
      Definir Variables
    */
  
    lnIdPeriodo   cra_perio.oid_peri%TYPE;
    lnIndIncSolic NUMBER(1);
    lnIndIncPedid NUMBER(1);
  
    /*
      Definir Cursores
    */
  
    -- Carga todas las recomendadas que se registraron en la campaña
  
    CURSOR c_base(vnIdPeriodo INTEGER) IS
    
SELECT pecm.fec_fact,
       DECODE(pecm.esso_oid_esta_soli,4,0,1) AS ind_soli_vali,
       pecm.val_mont_tota val_prec_cata_tota_loc_uni_dem,
       pecm.val_unid_tota num_unid_por_aten_tota,
       DECODE(pecm.esso_oid_esta_soli,4,1,0) AS ind_anul,
       reco.copa_oid_para_gral,
       pecm.oid_soli_cabe,
       reco.clie_oid_clie_rete,
       reco.clie_oid_clie_redo,
       pecm.perd_oid_peri,
       reco.oid_clie_rete,
       reco.oid_clie_redo
        FROM (SELECT redo.perd_oid_peri,
               rete.copa_oid_para_gral,
               rete.oid_clie_rete,
               redo.oid_clie_redo,
               rete.clie_oid_clie AS clie_oid_clie_rete,
               redo.clie_oid_clie AS clie_oid_clie_redo
                FROM inc_clien_recdo redo, inc_clien_recte rete
         WHERE redo.clr3_oid_clie_rete = rete.oid_clie_rete
                 AND redo.perd_oid_peri = vnIdPeriodo) reco,
             (SELECT soca.clie_oid_clie,
               soca.oid_soli_cabe,
               soca.fec_fact,
               soca2.esso_oid_esta_soli,
               acum.val_mont_tota,
               acum.val_unid_tota,
               soca.perd_oid_peri
          FROM ped_solic_cabec soca,
               ped_solic_cabec soca2,
               mae_clien clie,
               cra_perio perd,
               seg_perio_corpo peri,
               ped_tipo_solic_pais tspa,
               ped_tipo_solic tsol,
               ped_solic_cabec_acum2 acum
         WHERE soca.soca_oid_soli_cabe = soca2.oid_soli_cabe
           AND soca.clie_oid_clie = clie.oid_clie
           AND soca.perd_oid_peri = perd.oid_peri
           AND perd.peri_oid_peri = peri.oid_peri
           AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
           AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
           and acum.clie_oid_clie=soca.clie_oid_clie
           and acum.perd_oid_peri=soca.perd_oid_peri
           --
           AND tsol.cod_tipo_soli = 'SOC'
           AND soca.ind_oc = 1
           AND soca.modu_oid_modu NOT IN (13,15)
                 AND soca.perd_oid_peri = vnIdPeriodo) pecm
 WHERE reco.clie_oid_clie_redo = pecm.clie_oid_clie;
  
  
    r_base c_base%ROWTYPE;
  
  BEGIN
    -- Buscar oid de la campaña
    lnIdPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPeriodo);
  
    -- PROCESO PRINCIPAL
    -- Verificar si la recomendada tiene registro de pedidos en inc_solic_concu_recom.
    -- Si no tiene, le graba un registro x concurso ( no por periodo ).
    -- Y en inc_pedid_concu_recom graba x campaña .
  
    OPEN c_base(lnIdPeriodo);
    LOOP
      FETCH c_base
        INTO r_base;
      EXIT WHEN c_base%NOTFOUND;
      BEGIN
      
        -- Verificar si recomendada tiene registro en "inc_solic_concu_recom"
        -- Se espera que el cursor base siempre tenga registros con
        -- un concurso asociado al periodo que se esta procesando.
      
        SELECT COUNT(1)
          INTO lnIndIncSolic
          FROM inc_solic_concu_recom
         WHERE copa_oid_para_gral = r_base.copa_oid_para_gral
           AND clie_oid_clie = r_base.clie_oid_clie_rete
           AND clie_oid_reco_dado = r_base.clie_oid_clie_redo;
      
        -- Si no tiene registro en inc_solic_concu_recom, graba los datos del pedido
        -- por unica vez durante todo el concurso. Si existe mas de 1 concurso de recom
        -- traslapado, se creará tantos registros como concursos existan.
      
        IF lnIndIncSolic = 0 THEN
          BEGIN
            INSERT INTO inc_solic_concu_recom
            VALUES
              (INC_SOCR_SEQ.NEXTVAL,
               r_base.fec_fact,
               r_base.ind_soli_vali,
               r_base.val_prec_cata_tota_loc_uni_dem,
               r_base.num_unid_por_aten_tota,
               DECODE(r_base.ind_anul, 1, 1, NULL),
               r_base.copa_oid_para_gral,
               r_base.oid_soli_cabe,
               r_base.clie_oid_clie_rete,
               lnIdPeriodo,
               r_base.clie_oid_clie_redo,
               NULL);
          EXCEPTION
            WHEN OTHERS THEN
              ln_sqlcode := SQLCODE;
              ls_sqlerrm := substr(sqlerrm, 1, 1000);
              RAISE_APPLICATION_ERROR(-20123,
                                      'ERROR: INSERT inc_solic_concu_recom ' ||
                                      TO_CHAR(r_base.oid_clie_redo) ||
                                      ls_sqlerrm);
          END;
        END IF;
      
        -- Verificar si recomendada tiene registro del pedido en inc_pedid_concu_recom
      
        SELECT COUNT(1)
          INTO lnIndIncPedid
          FROM inc_pedid_concu_recom
         WHERE copa_oid_para_gral = r_base.copa_oid_para_gral
           AND clr3_oid_clie_rete = r_base.oid_clie_rete
           AND clre_oid_clie_redo = r_base.oid_clie_redo
           AND perd_oid_peri = lnIdPeriodo;
      
        -- Si no tiene registro en inc_pedid_concu_recom, graba los datos del pedido
        -- de la campaña. Se graba siempre que exista pedido de la recomendada en cada
        -- campaña.
      
        IF lnIndIncPedid = 0 AND NVL(r_base.ind_anul,0)<>1 THEN
          BEGIN
            INSERT INTO inc_pedid_concu_recom
              (OID_PEDI_CONC_RECO,
               IMP_MONT_PEDI,
               NUM_UNID_PEDI,
               IND_PEDI_VALI,
               CLRE_OID_CLIE_REDO,
               PERD_OID_PERI,
               CLIE_OID_CLIE,
               COPA_OID_PARA_GRAL,
               CLR3_OID_CLIE_RETE,
               FEC_CREA)
            VALUES
              (INC_PECR_SEQ.NEXTVAL,
               r_base.val_prec_cata_tota_loc_uni_dem,
               r_base.num_unid_por_aten_tota,
               NULL,
               r_base.oid_clie_redo,
               lnIdPeriodo,
               r_base.clie_oid_clie_redo,
               r_base.copa_oid_para_gral,
               r_base.oid_clie_rete,
               SYSDATE);
          EXCEPTION
            WHEN OTHERS THEN
              ln_sqlcode := SQLCODE;
              ls_sqlerrm := substr(sqlerrm, 1, 1000);
              RAISE_APPLICATION_ERROR(-20123,
                                      'ERROR: INSERT inc_pedid_concu_recom ' ||
                                      TO_CHAR(r_base.oid_clie_redo) ||
                                      ls_sqlerrm);
          END;
        END IF;
      END;
    END LOOP;
    CLOSE c_base;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_PEDID_INCEN: ' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_PEDID_INCEN;

  /***************************************************************************
  Descripcion : Proceso que valida los registros de la carga de premios
  Fecha Creacion : 13/06/2011
  Autor : Nicolás López
  Parametros:
    psOidSecuencia : Id de Secuencia de Tabla temporal
    psOidConcurso : Id del Concurso
    psNumRegError : Numero de Registros Error
    psNumNivFalta : Numero de Niveles Faltantes
    psNumRegTotal : Numero total de Registros
  ***************************************************************************/
  PROCEDURE INC_PR_PROCE_VALID_CARGA_PREMI(psOidSecuencia VARCHAR2,
                                           psOidConcurso  VARCHAR2,
                                           psNumRegError  OUT VARCHAR2,
                                           psNumNivFalta  OUT VARCHAR2,
                                           psNumRegTotal  OUT VARCHAR2) IS
    lnOidPeri NUMBER;
    ldFecha   DATE;
    lsFecha   VARCHAR2(10);
    lsPeriodo INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
  
    CURSOR c_cargapremio IS
      SELECT c.oid_tmp_carg_premi,
             c.cod_conc,
             c.val_nive,
             c.num_prem,
             c.cod_sap,
             c.num_unid
        FROM INC_TMP_CARGA_PREMI c
       WHERE c.oid_sec_carg_premi = TO_NUMBER(psOidSecuencia);
  
    TYPE registroPremio IS RECORD(
      oidTmpCarga    inc_tmp_carga_premi.oid_tmp_carg_premi %TYPE,
      codigoConcurso inc_tmp_carga_premi.cod_conc %TYPE,
      valorNivel     inc_tmp_carga_premi.val_nive %TYPE,
      numPremio      inc_tmp_carga_premi.num_prem %TYPE,
      codigoSAP      inc_tmp_carga_premi.cod_sap %TYPE,
      numUnidad      inc_tmp_carga_premi.num_unid %TYPE);
  
    TYPE registroPremiotab IS TABLE OF registroPremio;
    increcord registroPremiotab;
  
    lnOidParaGral     inc_concu_param_gener.oid_para_gral %TYPE;
    lnOidParaNivePrem inc_param_nivel_premi.oid_para_nive_prem %TYPE;
    lnOidPremiArti    inc_premi_artic.oid_prem_arti %TYPE;
    lnOidProd         mae_produ.oid_prod %TYPE;
    lsCodError        inc_tmp_carga_premi.cod_erro %TYPE;
    lnCantPremio      NUMBER(4);
    lnCantFilasErr    NUMBER(4);
    lnTotNivFaltan    NUMBER(4);
    lnTotRegistros    NUMBER(4);
    lsNumConc         INC_CONCU_PARAM_GENER.NUM_CONC %TYPE;
  
    W_FILAS NUMBER := 1000;
  
  BEGIN
  
    lnCantFilasErr := 0;
    lnTotNivFaltan := 0;
    lnTotRegistros := 0;
  
    OPEN c_cargapremio;
    LOOP
      FETCH c_cargapremio BULK COLLECT
        INTO increcord LIMIT W_FILAS;
    
      IF increcord.COUNT > 0 THEN
        FOR x IN increcord.FIRST .. increcord.LAST LOOP
        
          lsCodError     := NULL;
          lnTotRegistros := lnTotRegistros + 1;
        
          BEGIN
            SELECT d.oid_para_gral
              INTO lnOidParaGral
              FROM INC_CONCU_PARAM_GENER d
             WHERE d.num_conc = increcord(x).codigoConcurso;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnOidParaGral := NULL;
          END;
        
          -- Se asigna el ID Parametro de Concurso General correspondiente al Número de concurso.
          UPDATE INC_TMP_CARGA_PREMI
             SET OID_PARA_GRAL = lnOidParaGral
           WHERE OID_TMP_CARG_PREMI = increcord(x).oidTmpCarga;
        
          -- Se asigna el oidParaNivelPremio
          BEGIN
            SELECT np.oid_para_nive_prem
              INTO lnOidParaNivePrem
              FROM inc_param_gener_premi gp, inc_param_nivel_premi np
             WHERE np.pagp_oid_para_gene_prem = gp.oid_para_gene_prem
               AND gp.copa_oid_para_gral = TO_NUMBER(psOidConcurso)
               AND np.num_nive = increcord(x).valorNivel;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnOidParaNivePrem := NULL;
          END;
        
          BEGIN
            SELECT pa.oid_prem_arti
              INTO lnOidPremiArti
              FROM inc_param_nivel_premi nivs,
                   inc_param_gener_premi gp,
                   inc_premi_artic       pa
             WHERE nivs.pagp_oid_para_gene_prem = gp.oid_para_gene_prem
               AND nivs.oid_para_nive_prem = pa.panp_oid_para_nive_prem
               AND gp.copa_oid_para_gral = TO_NUMBER(psOidConcurso)
               AND nivs.num_nive = increcord(x).valorNivel;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnOidPremiArti := NULL;
          END;
        
          -- Se asigna el Id premio Artic correspondiente al Nivel y al concurso seleccionado en Pantalla.
          UPDATE INC_TMP_CARGA_PREMI
             SET OID_PREM_ARTI = lnOidPremiArti
           WHERE OID_TMP_CARG_PREMI = increcord(x).oidTmpCarga;
        
          BEGIN
            SELECT p.oid_prod
              INTO lnOidProd
              FROM MAE_PRODU p
             WHERE p.cod_sap = increcord(x).codigoSAP;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnOidProd := NULL;
          END;
        
          -- Se asigna el Id de Producto correspondiente al código de Producto.
          UPDATE INC_TMP_CARGA_PREMI
             SET OID_PROD = lnOidProd
           WHERE OID_TMP_CARG_PREMI = increcord(x).oidTmpCarga;
        
          -- Cod Error 01: Si el concurso no corresponde al seleccionado en la pantalla.
        
          -- Primero obtenemos el número de concurso
          BEGIN
            SELECT con.num_conc
              INTO lsNumConc
              FROM INC_CONCU_PARAM_GENER con
             WHERE con.oid_para_gral = TO_NUMBER(psOidConcurso);
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lsNumConc := NULL;
          END;
        
          IF NOT (TRIM(increcord(x).codigoConcurso) = TRIM(lsNumConc)) THEN
            lsCodError := '01';
          END IF;
        
          -- Cod Error 02: Si el campo hallado oid_para_nivel_prem es nulo.
        
          IF (lnOidParaNivePrem IS NULL) THEN
            lsCodError := '02';
          END IF;
        
          --IF (lnOidParaGral IS NULL) THEN
          --   lsCodError:='02';
          --END IF;
        
          -- Cod Error 03: Si el campo NUM_PREM existe para mas de un NIVEL distinto dentro de toda la carga.
        
          SELECT COUNT(1)
            INTO lnCantPremio
            FROM (SELECT val_nive
                    FROM INC_TMP_CARGA_PREMI cp
                   WHERE cp.num_prem = increcord(x).numPremio
                     AND cp.oid_sec_carg_premi = TO_NUMBER(psOidSecuencia)
                   GROUP BY cp.val_nive);
        
          IF (lnCantPremio > 1) THEN
            lsCodError := '03';
          END IF;
        
          -- Cod Error 04: Si el campo OID_PROD es nulo.
          IF (lnOidProd IS NULL) THEN
            lsCodError := '04';
          END IF;
        
          -- Cod Error 05: Si el número de premio esta fuera del rango 1-999
          IF (increcord(x).numPremio < 1 OR increcord(x).numPremio > 999) THEN
            lsCodError := '05';
          END IF;
        
          UPDATE INC_TMP_CARGA_PREMI
             SET COD_ERRO = lsCodError
           WHERE OID_TMP_CARG_PREMI = increcord(x).oidTmpCarga;
        
          -- Cod Error 06: Si la unidad es igual a cero ó nulo.
          IF (increcord(x).numUnidad IS NOT NULL) THEN
            IF (increcord(x).numUnidad = 0) THEN
              lsCodError := '06';
            END IF;
          ELSE
            lsCodError := '06';
          END IF;
        
          --IF (lnOidPremiArti IS NULL) THEN
          --   lsCodError:='06';
          --END IF;
        
          UPDATE INC_TMP_CARGA_PREMI
             SET COD_ERRO = lsCodError
           WHERE OID_TMP_CARG_PREMI = increcord(x).oidTmpCarga;
        
          -- Se contabilizan los registros con error
          IF (lsCodError IS NOT NULL) THEN
            lnCantFilasErr := lnCantFilasErr + 1;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cargapremio%NOTFOUND;
    END LOOP;
    CLOSE c_cargapremio;
  
    -- Asignamos a la variable de salida el valor de registros con error
    psNumRegError := lnCantFilasErr;
  
    -- Número de Niveles Faltantes
    SELECT COUNT(1)
      INTO lnTotNivFaltan
      FROM inc_param_nivel_premi nvs, inc_param_gener_premi gp
     WHERE nvs.pagp_oid_para_gene_prem = gp.oid_para_gene_prem
       AND gp.copa_oid_para_gral = TO_NUMBER(psOidConcurso)
       AND nvs.num_nive NOT IN
           (SELECT DISTINCT VAL_NIVE FROM INC_TMP_CARGA_PREMI);
  
    psNumNivFalta := lnTotNivFaltan;
    psNumRegTotal := lnTotRegistros;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR inc_pr_proce_valid_carga_premi: ' ||
                              ls_sqlerrm);
  END inc_pr_proce_valid_carga_premi;

  /**************************************************************************
  Descripcion       : Carga los premios de la tabla temporal INC_TMP_CARGA_PREMI
  Fecha Creacion    : 14/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso :  Número de Concurso
    psOidSecuencia   :  Secuencia de tabla temporal
  Autor              :  Nicolás López
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PREMI_EXCEL(psCodigoPais     VARCHAR2,
                                     psNumeroConcurso VARCHAR2,
                                     psOidSecuencia   VARCHAR2) IS
    lnOidPais          SEG_PAIS.OID_PAIS%TYPE;
    lnNumeroUnidades   NUMBER;
    lnOidLotePremArtic INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI %TYPE;
    lnSecNumLote       INC_LOTE_PREMI_ARTIC.NUM_LOTE %TYPE;
    ln_num_nivel_ante  INC_TMP_CARGA_PREMI.VAL_NIVE %TYPE := NULL;
    ln_num_nivel       INC_TMP_CARGA_PREMI.VAL_NIVE %TYPE;
    lsValDescLotePremi INC_LOTE_PREMI_ARTIC.VAL_DESC_LOTE_PREM_ARTI %TYPE;
    lnOidArticLote     INC_ARTIC_LOTE.OID_ARTI_LOTE %TYPE;
    lsCodVentFict      INC_ARTIC_LOTE.COD_VENT_FICT %TYPE;
    lnRangoDesde       INC_CONTA_PREMI_ARTIC.NUM_RANGO_DESDE %TYPE;
    lnValUltConta      INC_CONTA_PREMI_ARTIC.VAL_ULTIM_CONTA %TYPE;
    lnValidaConta      NUMBER(4);
  
    CURSOR c_premiosCab IS
      SELECT DISTINCT a.val_nive, a.num_prem, a.oid_prem_arti
        FROM INC_TMP_CARGA_PREMI a
       WHERE a.oid_sec_carg_premi = TO_NUMBER(psOidSecuencia)
       ORDER BY a.val_nive, a.num_prem;
  
    CURSOR c_premiosDet(lnNumNivel NUMBER, lnNumPremio NUMBER) IS
      SELECT a.num_unid, a.oid_prod
        FROM INC_TMP_CARGA_PREMI a
       WHERE a.oid_sec_carg_premi = TO_NUMBER(psOidSecuencia)
         AND a.val_nive = lnNumNivel
         AND a.num_prem = lnNumPremio
       ORDER BY a.val_nive, a.num_prem;
  
    TYPE interfazPremiosCab IS RECORD(
      numNivel    INC_TMP_CARGA_PREMI.VAL_NIVE %TYPE,
      numPremio   INC_TMP_CARGA_PREMI.NUM_PREM %TYPE,
      oidPremArti INC_TMP_CARGA_PREMI.OID_PREM_ARTI %TYPE);
  
    TYPE interfazPremiosDet IS RECORD(
      numeroUnidades INC_TMP_CARGA_PREMI.NUM_UNID %TYPE,
      oidProd        INC_TMP_CARGA_PREMI.OID_PROD %TYPE);
  
    TYPE interfazPremiosTab IS TABLE OF interfazPremiosCab;
    interfazRecordCab interfazPremiosTab;
  
    TYPE interfazPremiosDetTab IS TABLE OF interfazPremiosDet;
    interfazRecordDet interfazPremiosDetTab;
  
  BEGIN
    --Recuperamos el oid Pais
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnSecNumLote := 2;
  
    -- Se eliminan los registros de la tabla INC_ARTIC_LOTE
  
    DELETE FROM INC_ARTIC_LOTE lt
     WHERE lt.oid_arti_lote IN
           (SELECT al.oid_arti_lote
              FROM inc_param_gener_premi pgp,
                   inc_param_nivel_premi pnp,
                   inc_premi_artic       pa,
                   inc_lote_premi_artic  lpa,
                   inc_artic_lote        al
             WHERE pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
               AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
               AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
               AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
               AND pgp.copa_oid_para_gral = TO_NUMBER(psNumeroConcurso));
  
    -- Se eliminan los registros de la tabla INC_LOTE_PREMI_ARTIC
  
    DELETE FROM INC_LOTE_PREMI_ARTIC h
     WHERE h.oid_lote_prem_arti IN
           (SELECT lpa.oid_lote_prem_arti
              FROM inc_param_gener_premi pgp,
                   inc_param_nivel_premi pnp,
                   inc_premi_artic       pa,
                   inc_lote_premi_artic  lpa
             WHERE pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
               AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
               AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
               AND pgp.copa_oid_para_gral = TO_NUMBER(psNumeroConcurso));
  
    OPEN c_premiosCab;
    LOOP
    
      FETCH c_premiosCab BULK COLLECT
        INTO interfazRecordCab LIMIT W_FILAS;
    
      IF interfazRecordCab.COUNT > 0 THEN
      
        FOR x IN interfazRecordCab.FIRST .. interfazRecordCab.LAST LOOP
        
          SELECT inc_lopa_seq.nextval INTO lnOidLotePremArtic FROM DUAL;
        
          ln_num_nivel := interfazRecordCab(x).numNivel;
        
          lsValDescLotePremi := 'Premio ' ||
                                TO_CHAR(interfazRecordCab(x).numPremio) ||
                                ' Nivel ' || TO_CHAR(ln_num_nivel);
        
          IF (ln_num_nivel_ante IS NULL OR
             ln_num_nivel_ante <> ln_num_nivel) THEN
            lnSecNumLote := 2;
            INSERT INTO INC_LOTE_PREMI_ARTIC
              (OID_LOTE_PREM_ARTI,
               NUM_LOTE,
               NUM_PREM,
               PRAR_OID_PREM_ARTI,
               VAL_DESC_LOTE_PREM_ARTI)
            VALUES
              (lnOidLotePremArtic,
               lnSecNumLote,
               interfazRecordCab (x).numPremio,
               interfazRecordCab (x).oidPremArti,
               lsValDescLotePremi);
            lnSecNumLote := lnSecNumLote + 1;
          
          ELSE
            INSERT INTO INC_LOTE_PREMI_ARTIC
              (OID_LOTE_PREM_ARTI,
               NUM_LOTE,
               NUM_PREM,
               PRAR_OID_PREM_ARTI,
               VAL_DESC_LOTE_PREM_ARTI)
            VALUES
              (lnOidLotePremArtic,
               lnSecNumLote,
               interfazRecordCab (x).numPremio,
               interfazRecordCab (x).oidPremArti,
               lsValDescLotePremi);
            lnSecNumLote := lnSecNumLote + 1;
          END IF;
        
          OPEN c_premiosDet(interfazRecordCab(x).numNivel,
                            interfazRecordCab(x).numPremio);
        
          LOOP
          
            FETCH c_premiosDet BULK COLLECT
              INTO interfazRecordDet LIMIT W_FILAS;
          
            IF interfazRecordDet.COUNT > 0 THEN
            
              FOR i IN interfazRecordDet.FIRST .. interfazRecordDet.LAST LOOP
              
                SELECT inc_arlo_seq.nextval INTO lnOidArticLote FROM DUAL;
              
                -- Se obtiene el Ultimo Contador de la tabla INC_CONTA_PREMI_ARTIC
                SELECT c.val_ultim_conta, c.num_rango_desde
                  INTO lnValUltConta, lnRangoDesde
                  FROM INC_CONTA_PREMI_ARTIC c
                 WHERE c.pais_oid_pais = lnOidPais;
              
                SELECT COUNT(1)
                  INTO lnValidaConta
                  FROM INC_CONTA_PREMI_ARTIC d
                 WHERE d.num_rango_desde <= lnValUltConta
                   AND lnValUltConta <= d.num_rango_hasta
                   AND d.pais_oid_pais = lnOidPais;
              
                IF (lnValidaConta > 0) THEN
                
                  UPDATE INC_CONTA_PREMI_ARTIC
                     SET VAL_ULTIM_CONTA = lnValUltConta + 1
                   WHERE pais_oid_pais = lnOidPais;
                
                  lsCodVentFict := lnValUltConta + 1;
                
                ELSE
                
                  UPDATE INC_CONTA_PREMI_ARTIC
                     SET VAL_ULTIM_CONTA = lnRangoDesde + 1
                   WHERE pais_oid_pais = lnOidPais;
                
                  lsCodVentFict := lnRangoDesde + 1;
                END IF;
              
                INSERT INTO INC_ARTIC_LOTE
                  (OID_ARTI_LOTE,
                   IND_DESP,
                   IMP_PREC_PUBL,
                   NUM_UNID,
                   COD_VENT_FICT,
                   PROD_OID_PROD,
                   LOPA_OID_LOTE_PREM_ARTI,
                   IND_CENT_DIST_GARA,
                   IND_TIPO_ENTR_PREM)
                VALUES
                  (lnOidArticLote,
                   1,
                   0,
                   interfazRecordDet (i).numeroUnidades,
                   lsCodVentFict,
                   interfazRecordDet (i).oidProd,
                   lnOidLotePremArtic,
                   0,
                   'B');
              
              END LOOP;
            
            END IF;
          
            EXIT WHEN(c_premiosDet%NOTFOUND);
          END LOOP;
        
          CLOSE c_premiosDet;
        
          ln_num_nivel_ante := ln_num_nivel;
        
        END LOOP;
      END IF;
      EXIT WHEN c_premiosCab%NOTFOUND;
    END LOOP;
    CLOSE c_premiosCab;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CARGA_PREMI_EXCEL: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_CARGA_PREMI_EXCEL;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Carga de Despacho de Concurso RxP
  Fecha Creacion     : 04/07/2011
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidConcurso : Oid Concurso
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_DESPA_CORXP(psCodigoPais    VARCHAR2,
                                    psCodigoPeriodo VARCHAR2,
                                    pnOidConcurso   NUMBER) RETURN VARCHAR2 IS
    lnOcurrencias  NUMBER;
    lsMensajeError VARCHAR2(80);
  
    lnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    lnOidCanal SEG_CANAL.OID_CANA%TYPE;
  
    lsCodigoPeriodoAct  SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoDesp SEG_PERIO_CORPO.COD_PERI%TYPE;
  BEGIN
    --Recuperamos el oid Pais, Canal
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --(1) Verificamos si el codigo de Periodo es Valido
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM SEG_PERIO_CORPO
     WHERE COD_PERI = psCodigoPeriodo;
  
    IF (lnOcurrencias = 0) THEN
      lsMensajeError := 'procesoINCCargaDespachoConcursoRxPForm.msg.periodoInvalido';
    ELSE
    
      --Obtenemos el Periodo Actual
      lsCodigoPeriodoAct := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(lnOidPais,
                                                                     'T',
                                                                     lnOidCanal);
    
      --(2) Validamos que la campaña ingresada sea >= a la campaña actual.
      IF (psCodigoPeriodo < lsCodigoPeriodoAct) THEN
        lsMensajeError := 'procesoINCCargaDespachoConcursoRxPForm.msg.periodoMenorActual';
      ELSE
      
        --(3) Validamos que la campaña ingresada = campaña de despacho del concurso
        SELECT cor.COD_PERI
          INTO lsCodigoPeriodoDesp
          FROM INC_CONCU_PARAM_GENER con,
               INC_PARAM_GENER_PREMI gen,
               CRA_PERIO             cra,
               SEG_PERIO_CORPO       cor
         WHERE con.Oid_Para_Gral = gen.copa_oid_para_gral
           AND gen.perd_oid_peri = cra.oid_peri
           AND cra.peri_oid_peri = cor.oid_peri
           AND con.oid_para_gral = pnOidConcurso;
      
        IF (lsCodigoPeriodoDesp <> psCodigoPeriodo) THEN
          lsMensajeError := 'procesoINCCargaDespachoConcursoRxPForm.msg.periodoDiferenteDespacho';
        END IF;
      
      END IF;
    END IF;
  
    RETURN lsMensajeError;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_DESPA_CORXP: ' ||
                              ls_sqlerrm);
  END INC_FN_VALID_DESPA_CORXP;

  /***************************************************************************
  Descripcion       : Procedimiento que agrega codigos de venta de Concursos RxP
                      a la tabla de consolidado de detalles. Ejecutado desde la
                      validacion de STO
  Fecha Creacion    : 07/07/2011
  Autor             : Sergio Apaza
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
              psCodigoUsuario : Codigo de Usuario
  ***************************************************************************/
  PROCEDURE inc_pr_despa_premi_corxp(pscodigopais    IN VARCHAR2,
                                     pscodigoperiodo IN VARCHAR2,
                                     pscodigousuario IN VARCHAR2,
                                     pscodtipodocu   IN VARCHAR2,
                                     psnumeroproceso IN VARCHAR2) IS
  
    CURSOR cinsertdetalle IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             cab.num_lote,
             ppd.cod_vent,
             'OC' tip_posic,
             1 num_unid_soli,
             'R', -- las agregadas por el programa de concursos RxP
             '0' ind_erro_sse,
             '0' ind_erro_rech,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             cab.fec_soli
        FROM int_solic_conso_cabec cab,
             inc_despa_corxp_cabec cyz,
             inc_despa_corxp_detal ppd,
             sto_proce_docum_digit tmp,
             seg_perio_corpo       cor,
             cra_perio             cra
       WHERE cab.cod_peri = cor.cod_peri
         AND cor.oid_peri = cra.peri_oid_peri
         AND cra.oid_peri = cyz.perd_oid_peri
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND cyz.pais_oid_pais = ppd.pais_oid_pais
         AND cyz.perd_oid_peri = ppd.perd_oid_peri
         AND cyz.copa_oid_para_gral = ppd.copa_oid_para_gral
         AND cab.ind_ocs_proc = '0' -- Pedidos no enviados
         AND cab.ind_proc_gp2 = '0' -- Pedidos no facturados
         AND cab.ind_erro_remp = '0' -- Ultimos pedidos
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cyz.ind_acti = '1'
            -- El codigo de venta no debe existir en el detalle
         AND NOT EXISTS
       (SELECT NULL
                FROM int_solic_conso_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.cod_vent = ppd.cod_vent)
         AND inc_pkg_proce_incen.inc_fn_valid_clien_corxp(cab.cod_clie,
                                                          cyz.copa_oid_para_gral) > 0;
  
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
  
    rows          NATURAL := 1000;
    j             BINARY_INTEGER := 0;
    v_row_count   NUMBER := 0;
    lnocurrencias NUMBER;
  
  BEGIN
  
    --Verificamos si hay Concursos RxP para la Campaña de Proceso de STO
    SELECT COUNT(1)
      INTO lnocurrencias
      FROM inc_despa_corxp_cabec cab, cra_perio cra, seg_perio_corpo cor
     WHERE cab.perd_oid_peri = cra.oid_peri
       AND cra.peri_oid_peri = cor.oid_peri
       AND cor.cod_peri = pscodigoperiodo
       AND cab.ind_acti = '1';
  
    IF (lnocurrencias = 0) THEN
      RETURN;
    END IF;
  
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
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INC_PR_DESPA_PREMI_CORXP: ' || ls_sqlerrm);
    
  END inc_pr_despa_premi_corxp;

  /***************************************************************************
  Descripcion       : Procedimiento que agrega mensajes x venta de Concursos RxP
                      Ejecutado desde la creacion de Ordenes de Compras x STO
  
  Fecha Creacion    : 07/07/2011
  Autor             : Sergio Apaza
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodigoPeriodo : Codigo de Periodo
  ***************************************************************************/
  PROCEDURE inc_pr_despa_mensa_corxp(pscodigopais    IN VARCHAR2,
                                     pscodigoperiodo IN VARCHAR2,
                                     pscodtipodocu   VARCHAR2,
                                     psnumeroproceso VARCHAR2) IS
  
    lnocurrencias NUMBER;
  
  BEGIN
  
    --Verificamos si hay Concursos RxP para la Campaña de Proceso de STO
    SELECT COUNT(1)
      INTO lnocurrencias
      FROM inc_despa_corxp_cabec cab, cra_perio cra, seg_perio_corpo cor
     WHERE cab.perd_oid_peri = cra.oid_peri
       AND cra.peri_oid_peri = cor.oid_peri
       AND cor.cod_peri = pscodigoperiodo
       AND cab.ind_acti = '1';
  
    IF (lnocurrencias = 0) THEN
      RETURN;
    END IF;
  
    --Insertamos los Mensajes para los Clientes que han sido despachados x Concurso
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
      SELECT msg_bume_seq.nextval,
             msg_bum2_seq.nextval,
             'REGUL.DE REGALO POR PEDIDO',
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
             cli.oid_clie,
             (SELECT oid_mens
                FROM msg_mensa a
               WHERE a.cod_mens = cyz.cod_mens),
             13,
             cli.val_nom1,
             cli.val_nom2,
             cli.val_ape1,
             cli.val_ape2,
             cli.val_apel_casa,
             cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' ||
             cli.val_ape2,
             con.num_conc,
             cab.cod_peri,
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
        FROM int_solic_conso_cabec cab,
             inc_despa_corxp_cabec cyz,
             seg_perio_corpo       cor,
             cra_perio             cra,
             mae_clien             cli,
             inc_concu_param_gener con,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cab.sec_nume_docu
         AND occ.num_lote = cab.num_lote
         AND occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodtipodocu
         AND cab.cod_peri = cor.cod_peri
         AND cor.oid_peri = cra.peri_oid_peri
         AND cra.oid_peri = cyz.perd_oid_peri
         AND cab.cod_clie = cli.cod_clie
         AND cyz.copa_oid_para_gral = con.oid_para_gral
         AND cyz.pais_oid_pais = cyz.pais_oid_pais
         AND cyz.perd_oid_peri = cyz.perd_oid_peri
         AND cyz.copa_oid_para_gral = cyz.copa_oid_para_gral
         AND cab.ind_erro_rech = '0' -- No rechazados
         AND cab.ind_error_sgpe = '0' -- No son segundos pedidos
         AND cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cyz.ind_acti = '1'
            -- El codigo de venta no debe existir en el detalle
         AND inc_pkg_proce_incen.inc_fn_valid_clien_corxp(cab.cod_clie,
                                                          cyz.copa_oid_para_gral) > 0;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INC_PR_DESPA_MENSA_CORXP: ' || ls_sqlerrm);
    
  END inc_pr_despa_mensa_corxp;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de si el Cliente se le va a realizar
                       Despacho de Concurso RxP
  Fecha Creacion     : 07/07/2011
  Parametros Entrada :
             psCodigoCliente  : Codigo Cliente
             pnOidConcurso : Oid Concurso
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CLIEN_CORXP(psCodigoCliente VARCHAR2,
                                    pnOidConcurso   NUMBER) RETURN NUMBER IS
    lnResultado NUMBER;
  
    lnPuntaje       NUMBER;
    lnPuntajeExig   NUMBER;
    lnIndGanadora   NUMBER;
    lnPuntajeMinimo NUMBER;
    lnExigMinimo    NUMBER;
  BEGIN
    lnResultado := 0;
  
    SELECT NVL(SUM(NUM_PUNT), 0) as puntaje,
           NVL(SUM(NUM_PUNT_EXIG), 0) as punexig,
           NVL(MAX(VAL_REQU_PREM_SUPE), 0) as ganadora
      INTO lnPuntaje, lnPuntajeExig, lnIndGanadora
      FROM INC_CUENT_CORRI_PUNTO cuen, INC_CANDI_GANAD ganad, MAE_CLIEN cli
     WHERE ganad.CLIE_OID_CLIE = cuen.CLIE_OID_CLIE
       AND ganad.COPA_OID_PARA_GRAL = cuen.COPA_OID_PARA_GRAL
       AND VAL_DESC NOT LIKE 'Entrega de Premio%'
       AND cuen.CLIE_OID_CLIE = cli.OID_CLIE
       AND cli.COD_CLIE = psCodigoCliente
       AND cuen.copa_oid_para_gral = pnOidConcurso;
  
    SELECT MIN(num_cant_inic_punt) as punmin,
           MIN(num_punt_prod_exig) as exigmin
      INTO lnPuntajeMinimo, lnExigMinimo
      FROM INC_PARAM_NIVEL_PREMI ni,
           INC_PARAM_GENER_PREMI ge,
           INC_PREMI_ARTIC       pa
     WHERE ni.PAGP_OID_PARA_GENE_PREM = ge.OID_PARA_GENE_PREM
       AND ni.OID_PARA_NIVE_PREM = pa.PANP_OID_PARA_NIVE_PREM
       AND ge.COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --Para que la consultora sea candidata a que se le agreguen despachos debe
    --cumplirse la condición Ganadora<>1 y Puntaje>=Punmin y Punexig=Exigmin
    IF ((lnIndGanadora <> 1) AND (lnPuntaje >= lnPuntajeMinimo) AND
       (lnPuntajeExig = lnExigMinimo)) THEN
      lnResultado := 1;
    END IF;
  
    RETURN lnResultado;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CLIEN_CORXP: ' ||
                              ls_sqlerrm);
  END INC_FN_VALID_CLIEN_CORXP;

  /**************************************************************************
  Descripcion       : Regulariza las recomendaciones en Incentivos para que
                      esten igual que MAE
                      (INC_CLIEN_RECTE, INC_CLIEN_RECDO)
  Fecha Creacion    : 10/08/2011
  Fecha Modificacion: 29/02/2012
  Parametros Entrada:
  psCodigoCampaña   :  Codigo Campaña
  Autor             : CSVD-FFVV
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_RECTE_RECDO(psCodigoPeriodo VARCHAR2) IS
    vnOidPeriodoActual CRA_PERIO.OID_PERI%TYPE;
  BEGIN
  

    vnOidPeriodoActual := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  
    -- Insertamos en RECTE las recomendantes que estan en MAE y no estan en INC --
  
    INSERT INTO inc_clien_recte
      (oid_clie_rete,
       ind_fin_vinc,
       clie_oid_clie,
       copa_oid_para_gral,
       ind_eval,
       oid_modu,
       fec_crea,
       ind_tipo_proc)
      SELECT inc_clr3_seq.NEXTVAL,
             NULL,
             recte.clie_oid_clie_vnte,
             recte.copa_oid_para_gral,
             NULL,
             13, -- 13: Incentivos
             SYSDATE,
             'R'
        FROM (SELECT data.clie_oid_clie_vnte,
                     clr3.clie_oid_clie,
                     data.oid_para_gral as copa_oid_para_gral
                FROM (SELECT cvin.clie_oid_clie_vnte,
                             zorg.oid_regi,
                             zzon.oid_zona,
                             concurso.oid_para_gral,
                             concurso.oid_regi,
                             concurso.oid_zona
                        FROM mae_clien_vincu cvin,
                             mae_tipo_vincu tvin,
                             mae_clien_prime_conta cprc,
                             cra_perio perd,
                             (SELECT zorg.oid_regi,
                                     zzon.oid_zona,
                                     copa.oid_para_gral,
                                     copa.perd_oid_peri_desd,
                                     copa.perd_oid_peri_hast,
                                     copa.oid_peri
                                FROM inc_ambit_geogr terc,
                                     zon_regio zorg,
                                     zon_zona zzon,
                                     (SELECT copa.oid_para_gral,
                                             copa.perd_oid_peri_desd,
                                             copa.perd_oid_peri_hast,
                                             vnOidPeriodoActual AS oid_peri
                                        FROM inc_concu_param_gener copa
                                       WHERE copa.perd_oid_peri_desd <=
                                             vnOidPeriodoActual
                                         AND copa.perd_oid_peri_hast >=
                                             vnOidPeriodoActual
                                         AND copa.diri_oid_diri = 1 --es dirigido a la Consultora
                                         AND copa.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                                         AND copa.ind_acti = 1 --concurso activo
                                      ) copa
                               WHERE terc.copa_oid_para_gral(+) =
                                     copa.oid_para_gral
                                 AND terc.zorg_oid_regi = zorg.oid_regi(+)
                                 AND terc.zorg_oid_regi =
                                     zzon.zorg_oid_regi(+)
                                 AND terc.zzon_oid_zona = zzon.oid_zona(+)) concurso,
                             mae_clien_unida_admin cuad,
                             zon_terri_admin ztad,
                             zon_terri terr,
                             zon_secci zscc,
                             zon_zona zzon,
                             zon_regio zorg
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
                             concurso.oid_zona = zzon.oid_zona)
                         AND cvin.tivc_oid_tipo_vinc = tvin.oid_tipo_vinc
                         AND cvin.clie_oid_clie_vndo = cprc.clie_oid_clie
                         AND cprc.perd_oid_peri = vnOidPeriodoActual
                         AND (perd.oid_peri >= concurso.perd_oid_peri_desd AND
                             perd.oid_peri <= concurso.perd_oid_peri_hast)
                         AND tvin.ind_reco = 1
                       GROUP BY cvin.clie_oid_clie_vnte,
                                zorg.oid_regi,
                                zzon.oid_zona,
                                concurso.oid_para_gral,
                                concurso.oid_regi,
                                concurso.oid_zona) data,
                     inc_clien_recte clr3
               WHERE data.clie_oid_clie_vnte = clr3.clie_oid_clie(+)
                 AND data.oid_para_gral = clr3.copa_oid_para_gral(+)
                 AND clr3.clie_oid_clie IS NULL) recte;
  
    -- Insertamos en RECDO las recomendadas que estan en MAE y no estan en INC --
    INSERT INTO inc_clien_recdo
      (oid_clie_redo,
       ind_efec,
       num_prem,
       clie_oid_clie,
       clr3_oid_clie_rete,
       perd_oid_peri,
       panp_oid_para_nive_prem,
       ind_eval,
       oid_modu,
       fec_crea,
       fec_efec,
       ind_anul,
       ind_tipo_proc)
      SELECT inc_clre_seq.NEXTVAL,
             NULL,
             NULL,
             recdo.clie_oid_clie_vndo,
             recdo.oid_clie_rete,
             vnOidPeriodoActual,
             NULL,
             NULL,
             13, -- 13: Incentivos
             SYSDATE,
             NULL,
             NULL,
             'R'
        FROM (SELECT data.clie_oid_clie_vnte,
                     data.clie_oid_clie_vndo,
                     clr3.oid_clie_rete,
                     data.oid_para_gral
--                     recom.red_oid_clie
                FROM (SELECT cvin.clie_oid_clie_vnte,
                             cvin.clie_oid_clie_vndo,
                             zorg1.oid_regi,
                             zzon1.oid_zona,
                             concurso.oid_para_gral,
                             concurso.oid_regi_conc,
                             concurso.oid_zona_conc,
                             oid_peri
                        FROM mae_clien_vincu cvin,
                             mae_tipo_vincu tvin,
                             mae_clien_prime_conta mpc,
                             (SELECT zorg.oid_regi           AS oid_regi_conc,
                                     zzon.oid_zona           as oid_zona_conc,
                                     copa.oid_para_gral,
                                     copa.perd_oid_peri_desd,
                                     copa.perd_oid_peri_hast,
                                     copa.oid_peri
                                FROM inc_ambit_geogr terc,
                                     zon_regio zorg,
                                     zon_zona zzon,
                                     (SELECT copa.oid_para_gral,
                                             copa.perd_oid_peri_desd,
                                             copa.perd_oid_peri_hast,
                                             vnOidPeriodoActual AS oid_peri
                                        FROM inc_concu_param_gener copa
                                       WHERE copa.perd_oid_peri_desd <=
                                             vnOidPeriodoActual
                                         AND copa.perd_oid_peri_hast >=
                                             vnOidPeriodoActual
                                         AND copa.diri_oid_diri = 1 --es dirigido a la Consultora
                                         AND copa.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                                         AND copa.ind_acti = 1 --concurso activo
                                      ) copa
                               WHERE terc.copa_oid_para_gral(+) =
                                     copa.oid_para_gral
                                 AND terc.zorg_oid_regi = zorg.oid_regi(+)
                                 AND terc.zorg_oid_regi =
                                     zzon.zorg_oid_regi(+)
                                 AND terc.zzon_oid_zona = zzon.oid_zona(+)) concurso,
                             mae_clien_unida_admin cuad,
                             zon_terri_admin ztad,
                             zon_terri terr,
                             zon_secci zscc,
                             zon_zona zzon1,
                             zon_regio zorg1
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
                             concurso.oid_zona_conc = zzon1.oid_zona)
                         AND tvin.ind_reco = 1
                         AND cvin.tivc_oid_tipo_vinc = tvin.oid_tipo_vinc
                         AND tvin.ind_reco = 1
                         AND cvin.clie_oid_clie_vndo = mpc.clie_oid_clie
                         AND mpc.perd_oid_peri = vnOidPeriodoActual) data,
                         inc_clien_recte clr3
/*                         
                             (select clr3.copa_oid_para_gral, clr3.clie_oid_clie ret_oid_clie, 
                                     clre.clie_oid_clie                          red_oid_clie, 
                                     clr3.oid_clie_rete
                              from
                                inc_clien_recte clr3,
                                inc_clien_recdo clre
                              where
                                clr3.oid_clie_rete = clre.clr3_oid_clie_rete(+)) recom    */
               WHERE data.clie_oid_clie_vnte = clr3.clie_oid_clie
                 AND data.oid_para_gral      = clr3.copa_oid_para_gral
                 and data.clie_oid_clie_vndo not in (select redo.clie_oid_clie from inc_clien_recdo redo 
                                                        where redo.clr3_oid_clie_rete=clr3.oid_clie_rete) 
                 ) recdo;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_RECTE_RECDO: ' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_RECTE_RECDO;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones del ambito Geografico del Concurso, si
                       dentro de sus regiones se encuentra las regiones de bloquo de premios
  
  Fecha Creacion     : 17/11/2011
  Parametros Entrada :
             pnOidConcurso : Oid Concurso
             psRegiones : psRegiones
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_AMBIT_GEOGR_BLOQU(pnOidConcurso NUMBER,
                                          psRegiones    VARCHAR2)
    RETURN NUMBER IS
    lnResultado NUMBER;
  
    lnTotalAmbito      NUMBER;
    lnTotalRegiones    NUMBER;
    lnTotalRegionesAmb NUMBER;
  BEGIN
    lnResultado := 0;
  
    SELECT COUNT(1)
      INTO lnTotalAmbito
      FROM INC_AMBIT_GEOGR
     WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    IF (lnTotalAmbito = 0) THEN
      lnResultado := 1;
    ELSE
      IF (psRegiones = ' ') THEN
        SELECT COUNT(1)
          INTO lnTotalRegiones
          FROM ZON_REGIO reg
         WHERE IND_ACTI = 1
           AND IND_BLOQ_PREM = '1';
      ELSe
        SELECT COUNT(1)
          INTO lnTotalRegiones
          FROM ZON_REGIO reg
         WHERE COD_REGI IN (psRegiones);
      END IF;
    
      SELECT COUNT(DISTINCT reg.OID_REGI)
        INTO lnTotalRegionesAmb
        FROM ZON_REGIO reg, INC_AMBIT_GEOGR amb
       WHERE INSTR(',' || psRegiones || ',',
                   ',' || TO_CHAR(reg.COD_REGI) || ',') > 0
         AND reg.OID_REGI = amb.ZORG_OID_REGI
         AND amb.COPA_OID_PARA_GRAL = pnOidConcurso;
    
      IF (lnTotalRegiones = lnTotalRegionesAmb) THEN
        lnResultado := 1;
      ELSE
        lnResultado := 0;
      END IF;
    
    END IF;
  
    RETURN lnResultado;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_AMBIT_GEOGR_BLOQU: ' ||
                              ls_sqlerrm);
  END INC_FN_VALID_AMBIT_GEOGR_BLOQU;

  /**************************************************************************
  Descripcion        : Obtiene el codigo de Periodo en base al Oid Periodo
  Fecha Creacion     : 06/12/2011
  Parametros Entrada :
             pnOidPeriodo : Oid Periodo
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_CODIG_PERIO(pnOidPeriodo NUMBER) RETURN VARCHAR2 IS
    lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
  
  BEGIN
  
    IF (pnOidPeriodo IS NULL) THEN
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
                              'ERROR INC_FN_OBTIE_CODIG_PERIO: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_OBTIE_CODIG_PERIO;

  /**************************************************************************
  Descripcion        : Obtiene el ultimo numero de Concurso a crearse
  Fecha Creacion     : 16/12/2011
  Parametros Entrada :
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_NUMER_CONCU RETURN VARCHAR2 IS
    lsAnio           VARCHAR2(2);
    lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
    lsNumero         VARCHAR2(3);
  BEGIN
    SELECT TO_CHAR(SYSDATE, 'YY') INTO lsAnio FROM DUAL;
  
    BEGIN
      SELECT TO_CHAR(TO_NUMBER(SUBSTR(NUM_CONC, 4)) + 1)
        INTO lsNumero
        FROM (SELECT NUM_CONC
                FROM INC_CONCU_PARAM_GENER
               WHERE NUM_CONC LIKE lsAnio || '%'
               ORDER BY NUM_CONC DESC)
       WHERE ROWNUM = 1;
    EXCEPTION
      WHEN OTHERS THEN
        lsNumero := '1';
    END;
  
    lsNumeroConcurso := lsAnio || '/' || LPAD(lsNumero, 3, '0');
  
    RETURN lsNumeroConcurso;
  
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_OBTIE_NUMER_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_OBTIE_NUMER_CONCU;

  /**************************************************************************
  Descripcion       : Replica la Configuracion de un determinado Concurso
  Fecha Creacion    : 28/12/2011
  Parametros Entrada:
    psCodigoPa     :  Codigo de pais
    pnOidConcurso  :  Codigo de Marca
    psCodigoUsuario : Codigo de Usuario
    psCodigoConcurso : Codigo del Nuevo Concurso creado
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REPLI_CONFI_CONCU(psCodigoPais              VARCHAR2,
                                     pnOidConcurso             NUMBER,
                                     psNombreConcurso          VARCHAR2,
                                     psCodigoPeriodoInicio     VARCHAR2,
                                     psFlagCopiarProducto      VARCHAR2,
                                     psFlagCopiarParticipantes VARCHAR2,
                                     psCodigoUsuario           VARCHAR2,
                                     psCodigoConcurso          OUT VARCHAR2) IS
    lnOidPais     SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca    SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal    SEG_CANAL.OID_CANA%TYPE;
    lnOidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lsNumeroConcurso        INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
    lsNumeroConcursoOrigen  INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
    lsCodigoPeriodoDesde    SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoHasta    SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoEval     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoDespacho SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lsTipoConcurso        INC_CLASI_CONCU.COD_CLAS_CONC%TYPE;
    lsCodigoPeriodoInicio SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoFin    SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoPremio SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnNumeroCampanias     NUMBER;
    ldFechaHoraActual     DATE;
    lnOidPremiacion       INC_PARAM_GENER_PREMI.OID_PARA_GENE_PREM%TYPE;
    lnOidNuevoPremiacion  INC_PARAM_GENER_PREMI.OID_PARA_GENE_PREM%TYPE;
    lnOidProducto         INC_PRODU.OID_PROD%TYPE;
    lnOidNuevoProducto    INC_PRODU.OID_PROD%TYPE;
    lnOidNivelPremiacion  INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Obtenemos Datos del Concurso
    SELECT cla.COD_CLAS_CONC,
           INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_DESD),
           INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_HAST),
           INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(pre.PERD_OID_PERI),
           pre.OID_PARA_GENE_PREM,
           pro.OID_PROD,
           gen.NUM_CONC
      INTO lsTipoConcurso,
           lsCodigoPeriodoInicio,
           lsCodigoPeriodoFin,
           lsCodigoPeriodoPremio,
           lnOidPremiacion,
           lnOidProducto,
           lsNumeroConcursoOrigen
      FROM INC_CONCU_PARAM_GENER gen,
           INC_CLASI_CONCU       cla,
           INC_PARAM_GENER_PREMI pre,
           INC_PRODU             pro
     WHERE gen.OID_PARA_GRAL = pnOidConcurso
       AND gen.CCON_OID_CLAS_CONC = cla.OID_CLAS_CONC
       AND gen.OID_PARA_GRAL = pre.COPA_OID_PARA_GRAL(+)
       AND gen.OID_PARA_GRAL = pro.COPA_OID_PARA_GRAL(+);
  
    --Obtenemos el Nuevo Numero de Concurso
    lsNumeroConcurso := INC_FN_OBTIE_NUMER_CONCU;
  
    --Obtenemos el rango de periodos de Concursos, y determinamos los nuevos periodo de vigencia
    --y de despacho del nuevo Concurso
    lsCodigoPeriodoDesde := psCodigoPeriodoInicio;
  
    lnNumeroCampanias := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodigoPeriodoInicio,
                                                               lsCodigoPeriodoFin,
                                                               lnOidPais,
                                                               lnOidMarca,
                                                               lnOidCanal) - 1;
  
    lsCodigoPeriodoHasta := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodigoPeriodoDesde,
                                                                   lnOidPais,
                                                                   lnOidMarca,
                                                                   lnOidCanal,
                                                                   lnNumeroCampanias);
  
    lsCodigoPeriodoEval := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodigoPeriodoDesde,
                                                                  lnOidPais,
                                                                  lnOidMarca,
                                                                  lnOidCanal,
                                                                  1);
  
    IF (lsCodigoPeriodoPremio IS NOT NULL) THEN
      lnNumeroCampanias := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodigoPeriodoInicio,
                                                                 lsCodigoPeriodoPremio,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal) - 1;
    
      lsCodigoPeriodoDespacho := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodigoPeriodoDesde,
                                                                        lnOidPais,
                                                                        lnOidMarca,
                                                                        lnOidCanal,
                                                                        lnNumeroCampanias);
    END IF;
  
    --Obtenemos el Id Identificador del Nuevo concurso
    lnOidConcurso := INC_COPA_SEQ.NEXTVAL;
  
    ldFechaHoraActual := SYSDATE;
  
    --INSERTAMOS EN LA TABLA INC_CONCU_PARAM_GENER
    INSERT INTO INC_CONCU_PARAM_GENER
      (OID_PARA_GRAL,
       NUM_CONC,
       NUM_VERS,
       PAIS_OID_PAIS,
       IND_RANK,
       IND_DEVO,
       IND_ANUL,
       VAL_FALT_NANU,
       VAL_EXPR_PUNT,
       IND_PRUE,
       VAL_OBSE,
       IND_ACTI,
       VAL_ESTA_GENE_META,
       VAL_CICL_CONC,
       IND_COMU,
       COD_MENS,
       PLC2_OID_PLAN_CONC,
       COIV_OID_CONC_IVR,
       PERD_OID_PERI_DESD,
       PERD_OID_PERI_HAST,
       TEXI_OID_TIPO_EXIG,
       DIRI_OID_DIRI,
       BCAL_OID_BASE_CALC,
       MARC_OID_MARC,
       CANA_OID_CANA,
       VAL_NOMB,
       IND_DUPL_CYZO,
       ICTP_OID_TIPO_PROG,
       ICTP_OID_CONC_TIPO_PROG,
       IND_NO_GENE_PUNT,
       CCON_OID_CLAS_CONC,
       TIOC_OID_TIPO_OFER_CONC,
       USU_CREA,
       FEC_CREA)
      SELECT lnOidConcurso,
             lsNumeroConcurso,
             NUM_VERS,
             PAIS_OID_PAIS,
             IND_RANK,
             IND_DEVO,
             IND_ANUL,
             VAL_FALT_NANU,
             VAL_EXPR_PUNT,
             IND_PRUE,
             'Réplica del concurso ' || lsNumeroConcursoOrigen, --VAL_OBSE
             0, --IND_ACTI
             VAL_ESTA_GENE_META,
             VAL_CICL_CONC,
             IND_COMU,
             COD_MENS,
             PLC2_OID_PLAN_CONC,
             COIV_OID_CONC_IVR,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoDesde,
                                                        lnOidMarca,
                                                        lnOidCanal),
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoHasta,
                                                        lnOidMarca,
                                                        lnOidCanal),
             TEXI_OID_TIPO_EXIG,
             DIRI_OID_DIRI,
             BCAL_OID_BASE_CALC,
             MARC_OID_MARC,
             CANA_OID_CANA,
             psNombreConcurso,
             IND_DUPL_CYZO,
             ICTP_OID_TIPO_PROG,
             ICTP_OID_CONC_TIPO_PROG,
             IND_NO_GENE_PUNT,
             CCON_OID_CLAS_CONC,
             TIOC_OID_TIPO_OFER_CONC,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_CONCU_PARAM_GENER
       WHERE OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_VERSI_CONCU
    INSERT INTO INC_VERSI_CONCU
      (OID_VERS_CONC,
       VAL_VERS_NUEV,
       NUM_CONC,
       PERD_OID_PERI,
       COPA_OID_PARA_GRAL,
       ESC2_OID_ESTA_CONC,
       COPA_OID_PARA_GRAL_ORIG,
       VICO_OID_VIGE_CONC,
       USU_CREA,
       FEC_CREA)
      SELECT INC_VECO_SEQ.NEXTVAL,
             VAL_VERS_NUEV,
             lsNumeroConcurso,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoDesde,
                                                        lnOidMarca,
                                                        lnOidCanal),
             lnOidConcurso,
             1, --ESC2_OID_ESTA_CONC
             lnOidConcurso,
             2, --VICO_OID_VIGE_CONC
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_VERSI_CONCU
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    IF psFlagCopiarParticipantes = '1' THEN
    
      --INSERTAMOS EN LA TABLA INC_AMBIT_GEOGR
      INSERT INTO INC_AMBIT_GEOGR
        (OID_AMBITO_GEOGRAFICO,
         ZSCC_OID_SECC,
         ZORG_OID_REGI,
         ZSGV_OID_SUBG_VENT,
         TERR_OID_TERR,
         ZZON_OID_ZONA,
         COPA_OID_PARA_GRAL,
         USU_CREA,
         FEC_CREA)
        SELECT INC_TERC_SEQ.NEXTVAL,
               ZSCC_OID_SECC,
               ZORG_OID_REGI,
               ZSGV_OID_SUBG_VENT,
               TERR_OID_TERR,
               ZZON_OID_ZONA,
               lnOidConcurso,
               psCodigoUsuario,
               ldFechaHoraActual
          FROM INC_AMBIT_GEOGR
         WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
    
      --INSERTAMOS EN LA TABLA INC_ESTAT_VENTA_CONSU
      INSERT INTO INC_ESTAT_VENTA_CONSU
        (OID_ESTA_VENT,
         COPA_OID_PARA_GRAL,
         ESTA_OID_ESTA_CLIE,
         PERD_OID_PERI_DESD,
         PERD_OID_PERI_HAST,
         USU_CREA,
         FEC_CREA)
        SELECT INC_ESVC_SEQ.NEXTVAL,
               lnOidConcurso,
               ESTA_OID_ESTA_CLIE,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                           lsCodigoPeriodoDesde,
                                                                                           (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                         lsCodigoPeriodoInicio,
                                                                                                                                         GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                          lnOidMarca,
                                                          lnOidCanal),
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                           lsCodigoPeriodoDesde,
                                                                                           (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                         lsCodigoPeriodoInicio,
                                                                                                                                         GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_HAST)))),
                                                          lnOidMarca,
                                                          lnOidCanal),
               psCodigoUsuario,
               ldFechaHoraActual
          FROM INC_ESTAT_VENTA_CONSU
         WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
    
      --INSERTAMOS EN LA TABLA INC_CLASI_PARTI_CONCU
      INSERT INTO INC_CLASI_PARTI_CONCU
        (OID_CLAS_PART_CONC,
         COPA_OID_PARA_GRAL,
         PACI_OID_PART_CONC_CABE,
         IND_EXCL,
         USU_CREA,
         FEC_CREA)
        SELECT INC_CLPC_SEQ.NEXTVAL,
               lnOidConcurso,
               PACI_OID_PART_CONC_CABE,
               IND_EXCL,
               psCodigoUsuario,
               ldFechaHoraActual
          FROM INC_CLASI_PARTI_CONCU
         WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
    
    END IF;
  
    IF psFlagCopiarProducto = '1' THEN
    
      --INSERTAMOS EN LA TABLA INC_PRODU
      IF (lnOidProducto IS NOT NULL) THEN
        --Obtenemos el Id Identificador del Nueva Premiacion
        lnOidNuevoProducto := INC_PRDU_SEQ.NEXTVAL;
      
        INSERT INTO INC_PRODU
          (OID_PROD,
           VAL_COMU_VALI,
           VAL_MENS_VALI,
           VAL_COMU_EXIG,
           VAL_MENS_EXIG,
           VAL_COMU_EXCL,
           VAL_MENS_EXCL,
           VAL_COMU_BONI,
           COPA_OID_PARA_GRAL,
           VAL_MENS_BONI,
           USU_CREA,
           FEC_CREA)
          SELECT lnOidNuevoProducto,
                 VAL_COMU_VALI,
                 VAL_MENS_VALI,
                 VAL_COMU_EXIG,
                 VAL_MENS_EXIG,
                 VAL_COMU_EXCL,
                 VAL_MENS_EXCL,
                 VAL_COMU_BONI,
                 lnOidConcurso,
                 VAL_MENS_BONI,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PRODU
           WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
      
        --INSERTAMOS EN LA TABLA INC_PRODU_VALID
        INSERT INTO INC_PRODU_VALID
          (OID_PROD_VALI,
           TOFE_OID_TIPO_OFER,
           PROD_OID_PROD,
           UNEG_OID_UNID_NEGO,
           SGEN_OID_SUPE_GENE,
           CIVI_OID_CICL_VIDA,
           NEGO_OID_NEGO,
           GENE_OID_GENE,
           PRDU_OID_PROD,
           MAPR_OID_MARC_PROD,
           USU_CREA,
           FEC_CREA)
          SELECT INC_PRVA_SEQ.NEXTVAL,
                 TOFE_OID_TIPO_OFER,
                 PROD_OID_PROD,
                 UNEG_OID_UNID_NEGO,
                 SGEN_OID_SUPE_GENE,
                 CIVI_OID_CICL_VIDA,
                 NEGO_OID_NEGO,
                 GENE_OID_GENE,
                 lnOidNuevoProducto,
                 MAPR_OID_MARC_PROD,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PRODU_VALID
           WHERE PRDU_OID_PROD = lnOidProducto
             AND OFDE_OID_DETA_OFER IS NULL;
      
        --INSERTAMOS EN LA TABLA INC_PRODU_BONIF
        INSERT INTO INC_PRODU_BONIF
          (OID_PROD_BONI,
           NUM_PUNT_UNID,
           VAL_FACT_MULT,
           PROD_OID_PROD,
           CIVI_OID_CICL_VIDA,
           NEGO_OID_NEGO,
           SGEN_OID_SUPE_GENE,
           TOFE_OID_TIPO_OFER,
           GENE_OID_GENE,
           PERD_OID_PERI_DESD,
           PERD_OID_PERI_HAST,
           UNEG_OID_UNID_NEGO,
           PRDU_OID_PROD,
           MAPR_OID_MARC_PROD,
           USU_CREA,
           FEC_CREA)
          SELECT INC_PRBO_SEQ.NEXTVAL,
                 NUM_PUNT_UNID,
                 VAL_FACT_MULT,
                 PROD_OID_PROD,
                 CIVI_OID_CICL_VIDA,
                 NEGO_OID_NEGO,
                 SGEN_OID_SUPE_GENE,
                 TOFE_OID_TIPO_OFER,
                 GENE_OID_GENE,
                 
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                             lsCodigoPeriodoDesde,
                                                                                             (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                           lsCodigoPeriodoInicio,
                                                                                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                            lnOidMarca,
                                                            lnOidCanal),
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                             lsCodigoPeriodoDesde,
                                                                                             (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                           lsCodigoPeriodoInicio,
                                                                                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_HAST)))),
                                                            lnOidMarca,
                                                            lnOidCanal),
                 
                 UNEG_OID_UNID_NEGO,
                 lnOidNuevoProducto,
                 MAPR_OID_MARC_PROD,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PRODU_BONIF
           WHERE PRDU_OID_PROD = lnOidProducto
             AND OFDE_OID_DETA_OFER IS NULL;
      
        --INSERTAMOS EN LA TABLA INC_PRODU_EXCLU
        INSERT INTO INC_PRODU_EXCLU
          (OID_PROD_EXCL,
           CIVI_OID_CICL_VIDA,
           TOFE_OID_TIPO_OFER,
           PERD_OID_PERI_DESD,
           PERD_OID_PERI_HAST,
           SGEN_OID_SUPE_GENE,
           PROD_OID_PROD,
           GENE_OID_GENE,
           NEGO_OID_NEGO,
           UNEG_OID_UNID_NEGO,
           PRDU_OID_PROD,
           MAPR_OID_MARC_PROD,
           USU_CREA,
           FEC_CREA)
          SELECT INC_PREX_SEQ.NEXTVAL,
                 CIVI_OID_CICL_VIDA,
                 TOFE_OID_TIPO_OFER,
                 
                 (CASE
                   WHEN PERD_OID_PERI_DESD IS NULL THEN
                    NULL
                   ELSE
                    GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                lsCodigoPeriodoDesde,
                                                                                                (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                              lsCodigoPeriodoInicio,
                                                                                                                                              GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                               lnOidMarca,
                                                               lnOidCanal)
                 END),
                 
                 (CASE
                   WHEN PERD_OID_PERI_HAST IS NULL THEN
                    NULL
                   ELSE
                    GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                lsCodigoPeriodoDesde,
                                                                                                (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                              lsCodigoPeriodoInicio,
                                                                                                                                              GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_HAST)))),
                                                               lnOidMarca,
                                                               lnOidCanal)
                 END),
                 
                 SGEN_OID_SUPE_GENE,
                 PROD_OID_PROD,
                 GENE_OID_GENE,
                 NEGO_OID_NEGO,
                 UNEG_OID_UNID_NEGO,
                 lnOidNuevoProducto,
                 MAPR_OID_MARC_PROD,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PRODU_EXCLU
           WHERE PRDU_OID_PROD = lnOidProducto
             AND OFDE_OID_DETA_OFER IS NULL;
      
        --INSERTAMOS EN LA TABLA INC_PRODU_EXIGI
        INSERT INTO INC_PRODU_EXIGI
          (OID_PROD_EXIG,
           NUM_UNID_EXIG,
           IMP_MONT_EXIG,
           NUM_PUNT_EXIG,
           SGEN_OID_SUPE_GENE,
           GENE_OID_GENE,
           TOFE_OID_TIPO_OFER,
           UNEG_OID_UNID_NEGO,
           PROD_OID_PROD,
           NEGO_OID_NEGO,
           CIVI_OID_CICL_VIDA,
           PERD_OID_PERI_DESD,
           PERD_OID_PERI_HAST,
           PRDU_OID_PROD,
           MAPR_OID_MARC_PROD,
           PRE2_OID_PROD_EXIG,
           IND_AGRUP,
           USU_CREA,
           FEC_CREA)
          SELECT INC_PREX_SEQ.NEXTVAL,
                 NUM_UNID_EXIG,
                 IMP_MONT_EXIG,
                 NUM_PUNT_EXIG,
                 SGEN_OID_SUPE_GENE,
                 GENE_OID_GENE,
                 TOFE_OID_TIPO_OFER,
                 UNEG_OID_UNID_NEGO,
                 PROD_OID_PROD,
                 NEGO_OID_NEGO,
                 CIVI_OID_CICL_VIDA,
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                             lsCodigoPeriodoDesde,
                                                                                             (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                           lsCodigoPeriodoInicio,
                                                                                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                            lnOidMarca,
                                                            lnOidCanal),
                 GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                             lsCodigoPeriodoDesde,
                                                                                             (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                           lsCodigoPeriodoInicio,
                                                                                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_HAST)))),
                                                            lnOidMarca,
                                                            lnOidCanal),
                 lnOidNuevoProducto,
                 MAPR_OID_MARC_PROD,
                 PRE2_OID_PROD_EXIG,
                 IND_AGRUP,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PRODU_EXIGI
           WHERE PRDU_OID_PROD = lnOidProducto
             AND OFDE_OID_DETA_OFER IS NULL;
      END IF;
    END IF;
  
    --INSERTAMOS EN LA TABLA INC_AMBIT_GEOGR
    INSERT INTO INC_ACCES_CONCU
      (OID_ACCE_CONC, COPA_OID_PARA_GRAL, ACCE_OID_ACCE)
      SELECT INC_ACC2_SEQ.NEXTVAL, lnOidConcurso, ACCE_OID_ACCE
        FROM INC_ACCES_CONCU
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_OBTEN_PUNTO
    INSERT INTO INC_OBTEN_PUNTO
      (OID_OBTE_PUNT,
       VAL_FACT_CONV,
       NUM_PUNT_ASIG,
       IND_COMU_OBTE,
       VAL_PUNT_ACUM,
       IND_ACTI,
       IND_CONS,
       COPA_OID_PARA_GRAL,
       MENS_OID_MENS,
       VAL_TEXT_RANK,
       USU_CREA,
       FEC_CREA)
      SELECT INC_OBPU_SEQ.NEXTVAL,
             VAL_FACT_CONV,
             NUM_PUNT_ASIG,
             IND_COMU_OBTE,
             VAL_PUNT_ACUM,
             IND_ACTI,
             IND_CONS,
             lnOidConcurso,
             MENS_OID_MENS,
             VAL_TEXT_RANK,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_OBTEN_PUNTO
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_DESPA_PREMI
    INSERT INTO INC_DESPA_PREMI
      (OID_DESP_PREM,
       IND_COMU_DESP_AUTO,
       IND_COMU_DESP_MANU,
       COPA_OID_PARA_GRAL,
       MENS_OID_MENS_AUTO,
       MENS_OID_MENS_MANU,
       USU_CREA,
       FEC_CREA)
      SELECT INC_DEPR_SEQ.NEXTVAL,
             IND_COMU_DESP_AUTO,
             IND_COMU_DESP_MANU,
             lnOidConcurso,
             MENS_OID_MENS_AUTO,
             MENS_OID_MENS_MANU,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_DESPA_PREMI
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_CONCU_PARAM_CONSU
    INSERT INTO INC_CONCU_PARAM_CONSU
      (OID_CONC_PARA_CONS,
       IND_RECO_EFEC,
       VAL_PERI_EVAL,
       IND_CONT_RECO,
       NUM_MINI_PEDI,
       NUM_MINI_PEDI_RECO,
       IMP_MONT_MINI_PEDI,
       NUM_UNID_MINI_PEDI,
       IND_REIN_PIER_PUNT,
       COPA_OID_PARA_GRAL,
       PERD_OID_PERI_INIC_EVAL,
       PERD_OID_PERI_DESD,
       PERD_OID_PERI_HAST,
       TVEN_OID_TIPO_VENT,
       IND_PREM_CAMP_EFEC,
       IND_GENE_PUNT_A_RECO,
       COPA_CONC_PUNT_RECO,
       USU_CREA,
       FEC_CREA)
      SELECT INC_COPC_SEQ.NEXTVAL,
             IND_RECO_EFEC,
             VAL_PERI_EVAL,
             IND_CONT_RECO,
             NUM_MINI_PEDI,
             NUM_MINI_PEDI_RECO,
             IMP_MONT_MINI_PEDI,
             NUM_UNID_MINI_PEDI,
             IND_REIN_PIER_PUNT,
             lnOidConcurso,
             (CASE
               WHEN (lsTipoConcurso = 'E' OR lsTipoConcurso = 'P') THEN
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoEval,
                                                           lnOidMarca,
                                                           lnOidCanal)
               ELSE
                PERD_OID_PERI_INIC_EVAL
             END),
             
             (CASE
               WHEN PERD_OID_PERI_DESD IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                            lsCodigoPeriodoDesde,
                                                                                            (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                          lsCodigoPeriodoInicio,
                                                                                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                           lnOidMarca,
                                                           lnOidCanal)
             END),
             
             (CASE
               WHEN PERD_OID_PERI_HAST IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                            lsCodigoPeriodoDesde,
                                                                                            (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                          lsCodigoPeriodoInicio,
                                                                                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_HAST)))),
                                                           lnOidMarca,
                                                           lnOidCanal)
             END),
             
             TVEN_OID_TIPO_VENT,
             IND_PREM_CAMP_EFEC,
             IND_GENE_PUNT_A_RECO,
             COPA_CONC_PUNT_RECO,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_CONCU_PARAM_CONSU
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_MONTO_VENTA_RECOM
    INSERT INTO INC_MONTO_VENTA_RECOM
      (OID_MONT_VENT_RECO,
       VAL_CANT_MONT_VENT,
       COPA_OID_PARA_GRAL,
       TMVR_OID_TIPO_MONT_VENT_RECO,
       USU_CREA,
       FEC_CREA)
      SELECT INC_MOVR_SEQ.NEXTVAL,
             VAL_CANT_MONT_VENT,
             lnOidConcurso,
             TMVR_OID_TIPO_MONT_VENT_RECO,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_MONTO_VENTA_RECOM
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_REQUI_PREMI
    INSERT INTO INC_REQUI_PREMI
      (OID_REQU_PREM,
       VAL_MONT_MINI_CONC,
       NUM_PEDI,
       VAL_CUOT_INGR,
       IND_PEDI_PERI,
       VAL_MONT_MINI_PEDI,
       IND_PAGO_TIEM,
       NUM_DIAS_GRAC,
       IND_ASIS_CURS,
       COPA_OID_PARA_GRAL,
       PERD_OID_PERI_DESD,
       PERD_OID_PERI,
       VADE_OID_VALI_DEUD,
       NUM_PERI_ESPE_PAGO,
       PERD_OID_PERI_MAXI,
       USU_CREA,
       FEC_CREA)
      SELECT INC_REPR_SEQ.NEXTVAL,
             VAL_MONT_MINI_CONC,
             NUM_PEDI,
             VAL_CUOT_INGR,
             IND_PEDI_PERI,
             VAL_MONT_MINI_PEDI,
             IND_PAGO_TIEM,
             NUM_DIAS_GRAC,
             IND_ASIS_CURS,
             lnOidConcurso,
             (CASE
               WHEN PERD_OID_PERI_DESD IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                            lsCodigoPeriodoDesde,
                                                                                            (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                          lsCodigoPeriodoInicio,
                                                                                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_DESD)))),
                                                           lnOidMarca,
                                                           lnOidCanal)
             END),
             (CASE
               WHEN PERD_OID_PERI IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                            lsCodigoPeriodoDesde,
                                                                                            (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                          lsCodigoPeriodoInicio,
                                                                                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI)))),
                                                           lnOidMarca,
                                                           lnOidCanal)
             END),
             VADE_OID_VALI_DEUD,
             NUM_PERI_ESPE_PAGO,
             PERD_OID_PERI_MAXI,
             psCodigoUsuario,
             ldFechaHoraActual
        FROM INC_REQUI_PREMI
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --INSERTAMOS EN LA TABLA INC_PARAM_GENER_PREMI
    IF (lnOidPremiacion IS NOT NULL) THEN
      --Obtenemos el Id Identificador del Nueva Premiacion
      lnOidNuevoPremiacion := INC_PAGP_SEQ.NEXTVAL;
    
      INSERT INTO INC_PARAM_GENER_PREMI
        (OID_PARA_GENE_PREM,
         NUM_NIVE,
         IND_NIVE_ELEG,
         IND_PREM_ACUM_NIVE,
         VAL_HAST_NIVE,
         IND_NIVE_ROTA,
         NUM_ROTA,
         IND_ACCE_NIVE_SUPE,
         IND_PREM_ELEC,
         IND_PERI_DESP_EXIG,
         NUM_PERI,
         IND_COMU,
         MENS_OID_MENS,
         TELE_OID_TIPO_ELEC,
         TPRM_OID_TIPO_PION,
         COPA_OID_PARA_GRAL,
         PERD_OID_PERI_INIC,
         PERD_OID_PERI,
         IND_NOVA_PUNT_PREM,
         USU_CREA,
         FEC_CREA)
        SELECT lnOidNuevoPremiacion,
               NUM_NIVE,
               IND_NIVE_ELEG,
               IND_PREM_ACUM_NIVE,
               VAL_HAST_NIVE,
               IND_NIVE_ROTA,
               NUM_ROTA,
               IND_ACCE_NIVE_SUPE,
               IND_PREM_ELEC,
               IND_PERI_DESP_EXIG,
               NUM_PERI,
               IND_COMU,
               MENS_OID_MENS,
               TELE_OID_TIPO_ELEC,
               TPRM_OID_TIPO_PION,
               lnOidConcurso,
               
               (CASE
                 WHEN PERD_OID_PERI_INIC IS NULL THEN
                  NULL
                 ELSE
                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                              lsCodigoPeriodoDesde,
                                                                                              (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                            lsCodigoPeriodoInicio,
                                                                                                                                            GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI_INIC)))),
                                                             lnOidMarca,
                                                             lnOidCanal)
               END),
               
               (CASE
                 WHEN (lsCodigoPeriodoDespacho IS NOT NULL) THEN
                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoDespacho,
                                                             lnOidMarca,
                                                             lnOidCanal)
                 ELSE
                  (CASE
                    WHEN PERD_OID_PERI IS NULL THEN
                     NULL
                    ELSE
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                 lsCodigoPeriodoDesde,
                                                                                                 (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                                                                               lsCodigoPeriodoInicio,
                                                                                                                                               GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PERD_OID_PERI)))),
                                                                lnOidMarca,
                                                                lnOidCanal)
                  END)
               END),
               
               IND_NOVA_PUNT_PREM,
               psCodigoUsuario,
               ldFechaHoraActual
          FROM INC_PARAM_GENER_PREMI
         WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
    
      --INSERTAMOS EN LA TABLA INC_PARAM_NIVEL_PREMI
      FOR y IN (SELECT niv.*
                  FROM INC_PARAM_NIVEL_PREMI niv, INC_PARAM_GENER_PREMI pre
                 WHERE pre.COPA_OID_PARA_GRAL = pnOidConcurso
                   AND niv.PAGP_OID_PARA_GENE_PREM = pre.OID_PARA_GENE_PREM) LOOP
      
        --Obtenemos el Id Identificador del Nueva Nivel Premiacion
        lnOidNivelPremiacion := INC_PANP_SEQ.NEXTVAL;
      
        INSERT INTO INC_PARAM_NIVEL_PREMI
          (OID_PARA_NIVE_PREM,
           NUM_NIVE,
           NUM_CANT_FIJA_PUNT,
           NUM_CANT_INIC_PUNT,
           NUM_CANT_FINA_PUNT,
           VAL_NIVE_SELE,
           VAL_PUNT_SERV,
           NUM_ASPI,
           PAGP_OID_PARA_GENE_PREM,
           TPRE_OID_TIPO_PREM,
           FEC_ULTI_ACTU,
           NUM_PUNT_PROD_EXIG,
           VAL_DESC,
           USU_CREA,
           FEC_CREA)
          SELECT lnOidNivelPremiacion,
                 niv.NUM_NIVE,
                 niv.NUM_CANT_FIJA_PUNT,
                 niv.NUM_CANT_INIC_PUNT,
                 niv.NUM_CANT_FINA_PUNT,
                 niv.VAL_NIVE_SELE,
                 niv.VAL_PUNT_SERV,
                 niv.NUM_ASPI,
                 lnOidNuevoPremiacion,
                 niv.TPRE_OID_TIPO_PREM,
                 niv.FEC_ULTI_ACTU,
                 niv.NUM_PUNT_PROD_EXIG,
                 niv.VAL_DESC,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PARAM_NIVEL_PREMI niv, INC_PARAM_GENER_PREMI pre
           WHERE pre.COPA_OID_PARA_GRAL = pnOidConcurso
             AND niv.PAGP_OID_PARA_GENE_PREM = pre.OID_PARA_GENE_PREM
             AND niv.OID_PARA_NIVE_PREM = y.OID_PARA_NIVE_PREM;
      
        INSERT INTO INC_PREMI_ARTIC
          (OID_PREM_ARTI,
           NUM_UNID,
           PANP_OID_PARA_NIVE_PREM,
           USU_CREA,
           FEC_CREA)
          SELECT INC_PRAR_SEQ.NEXTVAL,
                 NUM_UNID,
                 lnOidNivelPremiacion,
                 psCodigoUsuario,
                 ldFechaHoraActual
            FROM INC_PREMI_ARTIC
           WHERE PANP_OID_PARA_NIVE_PREM = y.OID_PARA_NIVE_PREM;
      
      END LOOP;
    
    END IF;
  
    INSERT INTO INC_CONCU_CAMPA_DESPA
      (COD_PAIS, NUM_CONC, USU_MODI, FEC_MODI, COD_PERI, COD_PERI_FIN)
      SELECT COD_PAIS,
             lsNumeroConcurso,
             psCodigoUsuario,
             SYSDATE,
             
             (CASE
               WHEN COD_PERI IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                 lsCodigoPeriodoDesde,
                                                 (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                               lsCodigoPeriodoInicio,
                                                                                               COD_PERI)))
             END),
             
             (CASE
               WHEN COD_PERI_FIN IS NULL THEN
                NULL
               ELSE
                GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                 lsCodigoPeriodoDesde,
                                                 (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                               lsCodigoPeriodoInicio,
                                                                                               COD_PERI_FIN)))
             END)
      
        FROM INC_CONCU_CAMPA_DESPA
       WHERE NUM_CONC = lsNumeroConcursoOrigen;
  
    INSERT INTO INC_PARAM_NIVEL_PREMI_PUNEX
      (PAIS_COD_PAIS,
       NUM_CONC,
       NUM_NIVE,
       CAM_EXIG,
       PUN_EXIG,
       USU_CREA,
       FEC_CREA,
       EST_REGI)
      SELECT PAIS_COD_PAIS,
             lsNumeroConcurso,
             NUM_NIVE,
             GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                              lsCodigoPeriodoDesde,
                                              (GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais,
                                                                                            lsCodigoPeriodoInicio,
                                                                                            CAM_EXIG))),
             PUN_EXIG,
             psCodigoUsuario,
             SYSDATE,
             EST_REGI
        FROM INC_PARAM_NIVEL_PREMI_PUNEX
       WHERE NUM_CONC = lsNumeroConcursoOrigen;
  
    psCodigoConcurso := lsNumeroConcurso;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REPLI_CONFI_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REPLI_CONFI_CONCU;

  /**************************************************************************
  Descripcion       : Elimina los Niveles, Lotes y Articulos de Premios del Concurso
  Fecha Creacion    : 28/12/2011
  Parametros Entrada:
    pnOidPremiacion : oid Premiacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ELIMI_NIVEL_PREMI(pnOidPremiacion NUMBER,
                                     pnNumeroNiveles NUMBER) IS
  BEGIN
    --Recuperamos los niveles y lo eliminamos
    FOR x IN (SELECT niv.OID_PARA_NIVE_PREM, pre.OID_PREM_ARTI
                FROM INC_PARAM_NIVEL_PREMI niv, INC_PREMI_ARTIC pre
               WHERE niv.PAGP_OID_PARA_GENE_PREM = pnOidPremiacion
                 AND niv.OID_PARA_NIVE_PREM = pre.PANP_OID_PARA_NIVE_PREM
                 AND niv.NUM_NIVE > pnNumeroNiveles) LOOP
    
      --Recuperamos los lotes de premios y sus articulos para eliminarlos
      FOR y IN (SELECT OID_LOTE_PREM_ARTI
                  FROM INC_LOTE_PREMI_ARTIC
                 WHERE PRAR_OID_PREM_ARTI = x.OID_PREM_ARTI) LOOP
      
        DELETE FROM INC_ARTIC_LOTE
         WHERE LOPA_OID_LOTE_PREM_ARTI = y.OID_LOTE_PREM_ARTI;
      
        DELETE FROM INC_LOTE_PREMI_ARTIC
         WHERE OID_LOTE_PREM_ARTI = y.OID_LOTE_PREM_ARTI;
      
      END LOOP;
    
      DELETE FROM INC_PREMI_ARTIC WHERE OID_PREM_ARTI = x.OID_PREM_ARTI;
    
      DELETE FROM INC_PARAM_NIVEL_PREMI
       WHERE OID_PARA_NIVE_PREM = x.OID_PARA_NIVE_PREM;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ELIMI_NIVEL_PREMI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_ELIMI_NIVEL_PREMI;

  /**************************************************************************
  Descripcion       : Obtiene el ultimo valor de Contador Articulo que sera
                      utilizado en los codigos de Venta de los premios
  Fecha Creacion    : 30/12/2011
  Parametros Entrada:
    psUltimoCorrelativo  :  representa el ultimo correlativo de articulo de premio
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTEN_ULTIM_CONTA_ARTIC(psUltimoCorrelativo OUT VARCHAR2) IS
    lnRangoDesde  INC_CONTA_PREMI_ARTIC.NUM_RANGO_DESDE%TYPE;
    lnRangoHasta  INC_CONTA_PREMI_ARTIC.NUM_RANGO_HASTA%TYPE;
    lnUltimoValor INC_CONTA_PREMI_ARTIC.VAL_ULTIM_CONTA%TYPE;
  
    lnUltimoCorrelativo INC_CONTA_PREMI_ARTIC.NUM_RANGO_DESDE%TYPE;
  BEGIN
    SELECT NUM_RANGO_DESDE, NUM_RANGO_HASTA, VAL_ULTIM_CONTA
      INTO lnRangoDesde, lnRangoHasta, lnUltimoValor
      FROM INC_CONTA_PREMI_ARTIC;
  
    IF ((lnUltimoValor IS NULL) OR (lnUltimoValor = 0) OR
       (lnUltimoValor < lnRangoDesde) OR (lnUltimoValor >= lnRangoHasta)) THEN
    
      lnUltimoCorrelativo := lnRangoDesde;
    ELSE
      lnUltimoCorrelativo := lnUltimoValor + 1;
    END IF;
  
    UPDATE INC_CONTA_PREMI_ARTIC SET VAL_ULTIM_CONTA = lnUltimoCorrelativo;
  
    psUltimoCorrelativo := TO_CHAR(lnUltimoCorrelativo);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_OBTEN_ULTIM_CONTA_ARTIC: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_OBTEN_ULTIM_CONTA_ARTIC;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Cierre de Zona
  Fecha Creacion     : 26/01/2012
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidZona : Oid Zona
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CIERR_ZONA(psCodigoPais    VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   pnOidZona       NUMBER) RETURN VARCHAR2 IS
    lnOcurrencias NUMBER;
    lsValidacion  VARCHAR2(3);
  BEGIN
    --Recuperamos el oid Pais, Canal
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM FAC_PROGR_CIERR F, ZON_ZONA Z
     WHERE Z.OID_ZONA = pnOidZona
       AND Z.COD_ZONA = F.COD_ZONA
       AND F.COD_PAIS = psCodigoPais
       AND F.CAM_PROC = psCodigoPeriodo
       AND F.TIP_CIER = 'Z'
       AND F.EST_CIER = 'P';
  
    IF (lnOcurrencias > 0) THEN
      lsValidacion := 'OK';
    ELSE
      lsValidacion := 'ERR';
    END IF;
  
    RETURN lsValidacion;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CIERR_ZONA: ' ||
                              ls_sqlerrm);
  END INC_FN_VALID_CIERR_ZONA;

  /***********************************************************************************
  Descripcion        : Realiza Validaciones de Cierre de Region
  Fecha Creacion     : 26/01/2012
  Parametros Entrada :
             psCodigoPais  : Codigo Pais
             psCodigoPeriodo  : Codigo Periodo
             pnOidZona : Oid Region
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CIERR_REGIO(psCodigoPais    VARCHAR2,
                                    psCodigoPeriodo VARCHAR2,
                                    pnOidRegion     NUMBER) RETURN VARCHAR2 IS
    lnOcurrencias NUMBER;
    lsValidacion  VARCHAR2(3);
  BEGIN
    --Recuperamos el oid Pais, Canal
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM FAC_PROGR_CIERR F, ZON_REGIO R
     WHERE R.OID_REGI = pnOidRegion
       AND R.COD_REGI = F.COD_REGI
       AND F.COD_PAIS = psCodigoPais
       AND F.CAM_PROC = psCodigoPeriodo
       AND F.TIP_CIER = 'R'
       AND F.EST_CIER = 'P';
  
    IF (lnOcurrencias > 0) THEN
      lsValidacion := 'OK';
    ELSE
      lsValidacion := 'ERR';
    END IF;
  
    RETURN lsValidacion;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CIERR_REGIO: ' ||
                              ls_sqlerrm);
  END INC_FN_VALID_CIERR_REGIO;

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 21/02/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CARGA_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    lnOidPais        SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca       SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal       SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo     CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoreta CRA_PERIO.OID_PERI%TYPE;
    lnOidCliente     MAE_CLIEN.OID_CLIE%TYPE;
  
    lsCodigoPeriodoSig  SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoSig     CRA_PERIO.OID_PERI%TYPE;
    lsIndPuntMontReco   INC_PUNTO_RETAI.IND_PUNT_MONT_RECO%TYPE;
    lsIndProductos      BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lsIndfrecuenciaCRet BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lsIndActCampRetail  BAS_PARAM_PAIS.VAL_PARA%TYPE;
  
    lnTotalOcurrencias NUMBER(12);
    lnTotalPedidos     NUMBER(12);
    lsIndicadorProceso VARCHAR2(3);
  
    CURSOR c_Pedidos(indicadorProceso VARCHAR2, indicadorCmp VARCHAR2) IS
    SELECT VAL_CUEN_CONSU, CAM_RETA,
            SUM(VAL_IMPO) VAL_IMPO,
            SUM(VAL_UNID)  VAL_UNID 
    FROM 
      (
       SELECT CAB.VAL_CUEN_CONSU,
             (CASE WHEN INC_PKG_PROCE_INCEN.INC_FN_OBTEN_INDPI_RETAI(CAB.VAL_CUEN_CONSU)='1'
                        OR indicadorCmp='1' THEN
                           NVL(CAB.CAM_RETA,CAB.CAM_PROC)
                   ELSE
                           psCodigoPeriodo
               END) CAM_RETA,                                   
             (DET.UNI_VEND * DET.VAL_MONT_CATA - DET.UNI_DEVU * DET.VAL_MONT_CATA) VAL_IMPO,
             (DET.UNI_VEND - DET.UNI_DEVU) VAL_UNID
        FROM RET_VENTA_CABEC CAB, RET_VENTA_DETAL DET
        WHERE CAB.COD_PAIS = psCodigoPais
              AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
              AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
              AND CAB.COD_SBAC = DET.COD_SBAC
              AND DET.TIPO_TRAN_RET IN ('VR','RR', 'RD')
              AND CAB.VAL_CUEN_CONSU IS NOT NULL
              AND (CAB.CAM_PROC = psCodigoPeriodo OR CAB.CAM_RETA = psCodigoPeriodo)
              AND (((indicadorProceso = PROCESO_CIERRE_DIARIO) AND (NVL(CAB.IND_PUNT,'0') = '0'))
               OR
              (indicadorProceso = PROCESO_CIERRE_CAMPANA))
        )
     GROUP BY VAL_CUEN_CONSU, CAM_RETA;
  
    TYPE interfazPedidos IS RECORD(
      codCliente RET_VENTA_CABEC.VAL_CUEN_CONSU%TYPE,
      camreta    RET_VENTA_CABEC.CAM_RETA%TYPE,
      importe    PED_SOLIC_CABEC.VAL_TOTA_PAGA_LOCA%TYPE,
      unidades   INC_PUNTO_RETAI.NUM_UNID%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos el periodo Siguiente
    lsCodigoPeriodoSig := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal,
                                                                 1);
  
    --Recuperamos el parametro de Indicador Retail de frecuencia de carga (Diario/Sólo al Cierre de campaña)
    lsIndfrecuenciaCRet := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                               'INC',
                                                               '006'),
                               '0');
  
    --Recuperamos el parametro de activación de uso de la campaña de venta Retail:
    lsIndActCampRetail := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                              'INC',
                                                              '009'),
                              '0');
  
    IF (((psIndicadorProceso IS NULL) OR
       (psIndicadorProceso = PROCESO_CIERRE_CAMPANA)) and
       lsIndfrecuenciaCRet = '1') THEN
      lsIndicadorProceso := PROCESO_CIERRE_CAMPANA;
    ELSE
      lsIndicadorProceso := PROCESO_CIERRE_DIARIO;
    END IF;
  
    --PROCESAMOS A LAS ORDENES RETAIL
    OPEN c_Pedidos(lsIndicadorProceso, lsIndActCampRetail);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lnOidCliente     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazRecordN(x)
                                                                       .codCliente,
                                                                       true);
          lnOidPeriodoreta := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(interfazRecordN(x)
                                                                         .camreta,
                                                                         lnOidMarca,
                                                                         lnOidCanal);
        
          IF (lnOidCliente <> -1) THEN
            IF (lsIndicadorProceso = PROCESO_CIERRE_CAMPANA) THEN
              --VERIFICAMOS SI EXISTE EN LA TABLA CARGA PUNTOS RETAIL, Y YA FUE PROCESADO
              --PARA PERIODO Y FECHA DE FACTURACION
              SELECT COUNT(1)
                INTO lnTotalOcurrencias
                FROM INC_PUNTO_RETAI
               WHERE COD_PAIS = psCodigoPais
                 AND CAM_INIC = psCodigoPeriodo
                 AND COD_CONS = interfazRecordN(x).codCliente
                 AND IND_GENE_PUNT = '1'
                 AND EST_REGI = '1';
            
              IF (lnTotalOcurrencias = 0) THEN
                SELECT COUNT(1)
                  INTO lnTotalOcurrencias
                  FROM INC_PUNTO_RETAI
                 WHERE COD_PAIS = psCodigoPais
                   AND CAM_INIC = psCodigoPeriodo
                   AND COD_CONS = interfazRecordN(x).codCliente
                   AND IND_GENE_PUNT = '0'
                   AND EST_REGI = '1';
              
                --Verificamos si paso pedido en la campaña de Proceso Actual
                SELECT COUNT(1)
                  INTO lnTotalPedidos
                  FROM PED_SOLIC_CABEC_ACUM2
                 WHERE CLIE_OID_CLIE = lnOidCliente
                   AND PERD_OID_PERI = lnOidPeriodoreta;
              
                lsIndPuntMontReco := '0';
              
                --EN CASO QUE LA CONSULTORA SEA RECOMENDANTE
                FOR y IN (SELECT pedid.OID_PEDI_CONC_RECO
                            FROM INC_CLIEN_RECTE       rete,
                                 INC_PEDID_CONCU_RECOM pedid,
                                 INC_CONCU_PARAM_GENER gen
                           WHERE rete.CLIE_OID_CLIE = lnOidCliente
                             AND gen.OID_PARA_GRAL = rete.COPA_OID_PARA_GRAL
                             AND gen.IND_ACTI = 1
                             AND rete.OID_CLIE_RETE =
                                 pedid.CLR3_OID_CLIE_RETE
                             AND pedid.PERD_OID_PERI = lnOidPeriodoreta
                             AND pedid.CLRE_OID_CLIE_REDO is null) LOOP
                  lsIndPuntMontReco := '1';
                
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_RETL = interfazRecordN(x).importe,
                         USU_MODI      = psCodigoUsuario,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = y.OID_PEDI_CONC_RECO;
                
                END LOOP;
              
                --EN CASO QUE LA CONSULTORA SEA RECOMENDADA
                FOR y IN (SELECT pedid.OID_PEDI_CONC_RECO
                            FROM INC_CLIEN_RECDO       redo,
                                 INC_PEDID_CONCU_RECOM pedid,
                                 INC_CONCU_PARAM_GENER gen
                           WHERE redo.CLIE_OID_CLIE = lnOidCliente
                             AND gen.OID_PARA_GRAL =
                                 pedid.Copa_Oid_Para_Gral
                             AND gen.IND_ACTI = 1
                             AND NVL(redo.IND_EFEC, 0) <> 1
                             AND redo.CLR3_OID_CLIE_RETE =
                                 pedid.CLR3_OID_CLIE_RETE
                             AND pedid.PERD_OID_PERI = lnOidPeriodoreta
                             AND pedid.CLRE_OID_CLIE_REDO =
                                 redo.oid_clie_redo) LOOP
                  lsIndPuntMontReco := '1';
                
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_RETL = interfazRecordN(x).importe,
                         USU_MODI      = psCodigoUsuario,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = y.OID_PEDI_CONC_RECO;
                
                END LOOP;
              
                IF (lnTotalOcurrencias = 0) THEN
                  INSERT INTO INC_PUNTO_RETAI
                    (COR_PUNT_RETA,
                     COD_PAIS,
                     CAM_INIC,
                     COD_CONS,
                     NUM_PUNT,
                     NUM_UNID,
                     CAM_FINA,
                     IND_GENE_PUNT,
                     IND_PUNT_MONT_RECO,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     EST_REGI,
                     CAM_RETA)
                  VALUES
                    (INC_PURE_SEQ.NEXTVAL,
                     psCodigoPais,
                     psCodigoPeriodo,
                     interfazRecordN(x).codCliente,
                     interfazRecordN(x).importe,
                     interfazRecordN(x).unidades,
                     DECODE(lnTotalPedidos,
                            0,
                            lsCodigoPeriodoSig,
                            psCodigoPeriodo),
                     '0',
                     lsIndPuntMontReco,
                     psCodigoUsuario,
                     SYSDATE,
                     psCodigoUsuario,
                     SYSDATE,
                     '1',
                     interfazRecordN(x).camreta);
                ELSE
                  UPDATE INC_PUNTO_RETAI
                     SET NUM_PUNT           = interfazRecordN(x).importe,
                         NUM_UNID           = interfazRecordN(x).unidades,
                         CAM_FINA           = DECODE(lnTotalPedidos,
                                                     0,
                                                     lsCodigoPeriodoSig,
                                                     psCodigoPeriodo),
                         IND_PUNT_MONT_RECO = lsIndPuntMontReco,
                         USU_MODI           = psCodigoUsuario,
                         FEC_MODI           = SYSDATE
                   WHERE COD_PAIS = psCodigoPais
                     AND CAM_INIC = psCodigoPeriodo
                     AND COD_CONS = interfazRecordN(x).codCliente
                     AND IND_GENE_PUNT = '0'
                     AND EST_REGI = '1';
                END IF;
              
              END IF;
            
            ELSE
              --Verificamos si paso pedido en la campaña de Proceso Actual
              
              IF INC_PKG_PROCE_INCEN.INC_FN_OBTEN_INDPI_RETAI(interfazRecordN(x).codCliente)='1' THEN
                lnTotalPedidos:=1;
              ELSE                                
                 SELECT COUNT(1)
                 INTO lnTotalPedidos
                 FROM PED_SOLIC_CABEC_ACUM2
                 WHERE CLIE_OID_CLIE = lnOidCliente
                       AND PERD_OID_PERI = lnOidPeriodoreta;
              END IF;         
            
              IF (lnTotalPedidos > 0) THEN
                lsIndPuntMontReco := '0';
              
                --EN CASO QUE LA CONSULTORA SEA RECOMENDANTE
                FOR y IN (SELECT pedid.OID_PEDI_CONC_RECO
                            FROM INC_CLIEN_RECTE       rete,
                                 INC_PEDID_CONCU_RECOM pedid,
                                 INC_CONCU_PARAM_GENER gen
                           WHERE rete.CLIE_OID_CLIE = lnOidCliente
                             AND gen.OID_PARA_GRAL = rete.COPA_OID_PARA_GRAL
                             AND gen.IND_ACTI = 1
                             AND rete.OID_CLIE_RETE =
                                 pedid.CLR3_OID_CLIE_RETE
                             AND pedid.PERD_OID_PERI = lnOidPeriodoreta
                             AND pedid.CLRE_OID_CLIE_REDO is null) LOOP
                  lsIndPuntMontReco := '1';
                
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_RETL = NVL(IMP_MONT_RETL, 0) + interfazRecordN(x)
                                        .importe,
                         USU_MODI      = psCodigoUsuario,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = y.OID_PEDI_CONC_RECO;
                
                END LOOP;
              
                --EN CASO QUE LA CONSULTORA SEA RECOMENDADA
                FOR y IN (SELECT pedid.OID_PEDI_CONC_RECO
                            FROM INC_CLIEN_RECDO       redo,
                                 INC_PEDID_CONCU_RECOM pedid,
                                 INC_CONCU_PARAM_GENER gen
                           WHERE redo.CLIE_OID_CLIE = lnOidCliente
                             AND pedid.COPA_OID_PARA_GRAL =
                                 gen.OID_PARA_GRAL
                             AND gen.IND_ACTI = 1
                             AND NVL(redo.IND_EFEC, 0) <> 1
                             AND redo.CLR3_OID_CLIE_RETE =
                                 pedid.CLR3_OID_CLIE_RETE
                             AND pedid.PERD_OID_PERI = lnOidPeriodoreta
                             AND pedid.CLRE_OID_CLIE_REDO =
                                 redo.oid_clie_redo) LOOP
                  lsIndPuntMontReco := '1';
                
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_RETL = NVL(IMP_MONT_RETL, 0) + interfazRecordN(x)
                                        .importe,
                         USU_MODI      = psCodigoUsuario,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = y.OID_PEDI_CONC_RECO;
                
                END LOOP;
              
                INSERT INTO INC_PUNTO_RETAI
                  (COR_PUNT_RETA,
                   COD_PAIS,
                   CAM_INIC,
                   COD_CONS,
                   NUM_PUNT,
                   NUM_UNID,
                   CAM_FINA,
                   IND_GENE_PUNT,
                   IND_PUNT_MONT_RECO,
                   USU_CREA,
                   FEC_CREA,
                   USU_MODI,
                   FEC_MODI,
                   EST_REGI,
                   CAM_RETA)
                VALUES
                  (INC_PURE_SEQ.NEXTVAL,
                   psCodigoPais,
                   psCodigoPeriodo,
                   interfazRecordN     (x).codCliente,
                   interfazRecordN     (x).importe,
                   interfazRecordN     (x).unidades,
                   psCodigoPeriodo,
                   '0',
                   lsIndPuntMontReco,
                   psCodigoUsuario,
                   SYSDATE,
                   psCodigoUsuario,
                   SYSDATE,
                   '1',
                   interfazRecordN     (x).camreta);
              
              END IF;
            
            END IF;
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
    -- Calcula totales para base de descuentos ---

    For J IN (   
               SELECT CAB.COD_PAIS, CAB.COD_ACCE, CAB.COD_SBAC, 
                     CAB.NUM_DOCU_RETA, CAB.COD_TIPO_DOCU,CAB.VAL_CUEN_CONSU,
                     SUM(DET.UNI_VEND * DET.VAL_MONT_CATA - DET.UNI_DEVU * DET.VAL_MONT_CATA) VAL_IMPO,
                     SUM(DET.UNI_VEND - DET.UNI_DEVU) VAL_UNID
               FROM RET_VENTA_CABEC CAB, RET_VENTA_DETAL DET,
                     PRE_TIPO_OFERT TOF
               WHERE CAB.COD_PAIS = psCodigoPais
                     AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
                     AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
                     AND CAB.COD_ACCE = DET.COD_ACCE
                     AND CAB.COD_SBAC = DET.COD_SBAC
                     AND DET.TIPO_TRAN_RET ='VR'
                     AND CAB.VAL_CUEN_CONSU IS NOT NULL
                     AND NVL(CAB.VAL_MONT_BAPL_DCTO,0)=0
                     AND CAB.CAM_PROC = psCodigoPeriodo
                     AND TO_NUMBER(TOF.COD_TIPO_OFER(+)) = TO_NUMBER(TRIM(DET.COD_TIPO_OFER))
                     AND TOF.CANA_OID_CANA(+) = lnOidcanal    
                     AND TOF.IND_APOR_MONT_ESCA(+) = 1
               GROUP BY CAB.COD_PAIS, CAB.COD_ACCE, CAB.COD_SBAC, 
                        CAB.NUM_DOCU_RETA, CAB.COD_TIPO_DOCU, CAB.VAL_CUEN_CONSU
        
              ) LOOP
              
              UPDATE RET_VENTA_CABEC SET VAL_MONT_BAPL_DCTO=J.VAL_IMPO WHERE 
                                     COD_PAIS=psCodigoPais AND COD_ACCE=J.COD_ACCE 
                                     AND COD_SBAC=J.COD_SBAC
                                     AND NUM_DOCU_RETA=J.NUM_DOCU_RETA AND COD_TIPO_DOCU=J.COD_TIPO_DOCU
                                     AND VAL_CUEN_CONSU=J.VAL_CUEN_CONSU;
              
              END LOOP;

    --Invoca al proceso de Calcular Puntaje Retail

    INC_PR_CALCU_PUNTA_RETAI(psCodigoPais,
                             psCodigoMarca,
                             psCodigoCanal,
                             psCodigoPeriodo,
                             psIndicadorProceso,
                             psCodigoUsuario);
  
  
  /*
    --Invoca al proceso de Actualizar Disponibilidad Puntaje Retail
    INC_PR_ACTUA_DISPO_PUNTA_RETAI(psCodigoPais,
                                   psCodigoMarca,
                                   psCodigoCanal,
                                   psCodigoPeriodo,
                                   psIndicadorProceso,
                                  psCodigoUsuario);
                       
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CARGA_PUNTA_RETAI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    */
  END INC_PR_CARGA_PUNTA_RETAI;

  /**************************************************************************
  Descripcion       : Carda de Ordenes Retail por Campaña para proceso de
                      Calculo de Puntos
  Fecha Creacion    : 21/02/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_DISPO_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psIndicadorProceso VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal SEG_CANAL.OID_CANA%TYPE;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  
    --Si Campaña tope =< Campaña de Cierre
    -- Si Campaña Origen < Campaña Tope
    -- Actualizar Indicador de Puntaje Generado = 3 (se desactiva opción de generar puntaje para la siguiente campaña)
    UPDATE INC_PUNTO_RETAI
       SET IND_GENE_PUNT = '3',
           USU_MODI      = psCodigoUsuario,
           FEC_MODI      = SYSDATE
     WHERE COD_PAIS = psCodigoPais
       AND CAM_FINA <= psCodigoPeriodo
       AND CAM_INIC < CAM_FINA
       AND IND_GENE_PUNT = '0'
       AND EST_REGI = '1';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_DISPO_PUNTA_RETAI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_ACTUA_DISPO_PUNTA_RETAI;

  /**************************************************************************
  Descripcion       : Calcula Puntaje Retail de las Consultoras para una Campaña
  
  Fecha Creacion    : 12/03/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
      psIndicadorProceso : P: Cierre de Campana, G: GP4  , GP3: GP3
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_RETAI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
  
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsCodPeriodoAnt SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lsCodigoPeriodoSig SEG_PERIO_CORPO.COD_PERI%TYPE;
    ldFechaProceso     BAS_CTRL_FACT.FEC_PROC%TYPE;
  
    lsResult           VARCHAR2(100);
    lnTotalOcurrencias NUMBER(12);
    lnNumeroPuntos     INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;
    lnNumeroPuntosExi  INC_CUENT_CORRI_PUNTO.NUM_PUNT_EXIG%TYPE;
    lnOidSecuencia     NUMBER(12);
    lbGenerado         BOOLEAN;
    lsIndicadorProceso VARCHAR2(3);
    lnTipoMov          INC_CUENT_CORRI_PUNTO.TMOV_OID_TIPO_MOVI%TYPE;
    lnindreproceso     NUMBER(1);
  
    CURSOR c_Puntaje IS
      SELECT COD_CONS,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(COD_CONS),
             COR_PUNT_RETA,
             NUM_PUNT,
             NUM_UNID,
             CAM_INIC,
             CAM_RETA,
             IND_GENE_PUNT
        FROM INC_PUNTO_RETAI
       WHERE COD_PAIS  = psCodigoPais
         AND (CAM_INIC >= psCodigoPeriodo
              OR CAM_RETA >= psCodigoPeriodo)
--         AND IND_GENE_PUNT = '0'
         AND EST_REGI = '1';
  
    TYPE interfazPedidos IS RECORD(
      codCliente  INC_PUNTO_RETAI.COD_CONS%TYPE,
      oidCliente  MAE_CLIEN.OID_CLIE%TYPE,
      correlativo INC_PUNTO_RETAI.COR_PUNT_RETA%TYPE,
      puntaje     INC_PUNTO_RETAI.NUM_PUNT%TYPE,
      unidades    INC_PUNTO_RETAI.NUM_UNID%TYPE,
      periodo     INC_PUNTO_RETAI.CAM_INIC%TYPE,
      camreta     INC_PUNTO_RETAI.CAM_RETA%TYPE,
      indgenepunt INC_PUNTO_RETAI.IND_GENE_PUNT%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    lsIndProductos      BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lsIndfrecuenciaCRet BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lsIndActCampRetail  BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lnPuntajePosicion   NUMBER;
    lnPuntajePosBonif   NUMBER;
    lnPuntajeTotal      NUMBER;
    lnPuntajeTotalBon   NUMBER;
    lnPuntajeTotalExi   NUMBER;
  
    lnTotalProdVal NUMBER;
    lnTotalProdExc NUMBER;
    lnTotalProdBon NUMBER;
  
    lnTotalProdExi      NUMBER;
    lnTotalProdExiOblig NUMBER;
    lnTotalOblig        NUMBER;
  
    lnImporte  NUMBER;
    lnUnidades NUMBER;
    lnIndNoPiloRetail  NUMBER;
  
    v_lineaOblig t_lineaOblig := t_lineaOblig();
    v_totalOblig t_totalOblig := t_totalOblig();
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  
    --Obtenemos la Fecha de Proceso
    SELECT FEC_PROC
      INTO ldFechaProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --Recuperamos el parametro de Indicador Retail de exigencia de productos
    lsIndProductos := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                          'INC',
                                                          '004'),
                          '0');
  
    --Recuperamos el parametro de Indicador Retail de frecuencia de carga (Diario/Sólo al Cierre de campaña)
    lsIndfrecuenciaCRet := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                               'INC',
                                                               '006'),
                               '0');
  
    --Recuperamos el parametro de activación de uso de la campaña de venta Retail:
    lsIndActCampRetail := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                              'INC',
                                                              '009'),
                              '0');
  
    IF (((psIndicadorProceso IS NULL) OR
       (psIndicadorProceso = PROCESO_CIERRE_CAMPANA)) and
       lsIndfrecuenciaCRet = '1') THEN
      lsIndicadorProceso := PROCESO_CIERRE_CAMPANA;
    ELSE
      lsIndicadorProceso := PROCESO_CIERRE_DIARIO;
    END IF;
  
    --PROCESAMOS A LAS CONSULTORAS RETAIL
    OPEN c_Puntaje;
    LOOP
      FETCH c_Puntaje BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lbGenerado := FALSE;
          
          IF (NOT INC_PKG_PROCE_INCEN.INC_FN_OBTEN_INDPI_RETAI(interfazRecordN(x).codCliente)='1')
                   AND lsIndActCampRetail='0' THEN
                       lnIndNoPiloRetail := 1;
          ELSE
                       lnIndNoPiloRetail := 0;
          END IF;
        
          --Obtenemos el periodo Anterior
          lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(interfazRecordN(x)
                                                                    .camreta,
                                                                    lnOidPais,
                                                                    lnOidMarca,
                                                                    lnOidCanal,
                                                                    -1);
        
          lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(interfazRecordN(x)
                                                                     .camreta,
                                                                     lnOidMarca,
                                                                     lnOidCanal);
        
          FOR y IN (SELECT COPA.OID_PARA_GRAL      oidConcurso,
                           OBT.VAL_FACT_CONV       factorConversion,
                           OBT.NUM_PUNT_ASIG       puntosAsignar,
                           COPA.BCAL_OID_BASE_CALC baseCalculo
                      FROM INC_CONCU_PARAM_GENER COPA,
                           INC_PARAM_GENER_PREMI PAGP,
                           INC_OBTEN_PUNTO       OBT
                     WHERE COPA.OID_PARA_GRAL = PAGP.COPA_OID_PARA_GRAL
                       AND COPA.IND_ACTI = 1
                       AND COPA.BCAL_OID_BASE_CALC in (1, 2) --Base Calculo = Monto y Unidades
                       AND COPA.DIRI_OID_DIRI = 1 --Consultoras
                       AND COPA.OID_PARA_GRAL NOT IN
                           (SELECT COPA_CONC_PUNT_RECO
                              FROM INC_CONCU_PARAM_CONSU
                             WHERE COPA_CONC_PUNT_RECO IS NOT NULL)
                       AND OBT.COPA_OID_PARA_GRAL = COPA.OID_PARA_GRAL
                       AND ((PAGP.PERD_OID_PERI IS NULL OR
                           (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PAGP.PERD_OID_PERI) >
                           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(COPA.PERD_OID_PERI_HAST))) OR
                           lsIndicadorProceso = PROCESO_CIERRE_DIARIO)
                       AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(COPA.PERD_OID_PERI_DESD) <= interfazRecordN(x)
                          .camreta
                       AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(COPA.PERD_OID_PERI_HAST) >= interfazRecordN(x)
                          .camreta
                    UNION
                    SELECT COPA.OID_PARA_GRAL      oidConcurso,
                           OBT.VAL_FACT_CONV       factorConversion,
                           OBT.NUM_PUNT_ASIG       puntosAsignar,
                           COPA.BCAL_OID_BASE_CALC baseCalculo
                      FROM INC_CONCU_PARAM_GENER COPA, INC_OBTEN_PUNTO OBT
                     WHERE COPA.IND_ACTI = 1
                       AND COPA.BCAL_OID_BASE_CALC IN (1, 2) --Base Calculo = Monto y Unidades
                       AND COPA.DIRI_OID_DIRI = 1 --Consultoras
                       AND COPA.OID_PARA_GRAL NOT IN
                           (SELECT COPA_CONC_PUNT_RECO
                              FROM INC_CONCU_PARAM_CONSU
                             WHERE COPA_CONC_PUNT_RECO IS NOT NULL)
                       AND OBT.COPA_OID_PARA_GRAL = COPA.OID_PARA_GRAL
                       AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(COPA.PERD_OID_PERI_DESD) <= interfazRecordN(x)
                          .camreta
                       AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(COPA.PERD_OID_PERI_HAST) > interfazRecordN(x)
                          .camreta) LOOP
          
            lnindreproceso := 0;
            lnTotalOcurrencias:=0;
            
            IF lnIndNoPiloRetail=0 OR interfazRecordN(x).indgenepunt = '2' THEN
                 SELECT COUNT(0) 
                 INTO lnTotalOcurrencias
                 FROM inc_cuent_corri_punto 
                              WHERE copa_oid_para_gral=y.oidConcurso
                                    AND clie_oid_clie = interfazRecordN(x).oidCliente
                                    AND val_desc like '%/ECM%';
                                    
                 IF lnTotalOcurrencias=0 THEN
                    lnindreproceso := 1;
                 END IF;                          
            END IF;
                             
            --VERIFICAR SI SE ENCUENTRA DESCALIFICADA PARA EL CONCURSO
            
            SELECT COUNT(1)
               INTO lnTotalOcurrencias
               FROM INC_DESCA
               WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                     AND COPA_OID_PARA_GRAL = y.oidConcurso;                     
          
            IF lnTotalOcurrencias = 0 and (lnindreproceso = 1 or interfazRecordN(x).indgenepunt = '0') THEN
            
              --VALIDACION AMBITO GEOGRAFICO
              SELECT COUNT(OID_AMBITO_GEOGRAFICO)
                INTO lnTotalOcurrencias
                FROM INC_AMBIT_GEOGR geo
               WHERE COPA_OID_PARA_GRAL = y.oidConcurso;
            
              lsResult := '1';
              IF (lnTotalOcurrencias > 0) THEN
                --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
                BEGIN
                  lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(y.oidConcurso,
                                                             interfazRecordN(x)
                                                             .oidCliente);
                EXCEPTION
                  WHEN OTHERS THEN
                    lsResult := '0';
                END;
              END IF;
            
              IF (lsResult = '1') THEN
                --VALIDACION DEL TIPO DE PARTICIPANTE
                SELECT COUNT(OID_CLAS_PART_CONC)
                  INTO lnTotalOcurrencias
                  FROM INC_CLASI_PARTI_CONCU par
                 WHERE par.copa_oid_para_gral = y.oidConcurso;
              
                lsResult := '1';
                IF (lnTotalOcurrencias > 0) THEN
                  lsResult := INC_FN_VALID_CLASI_CONCU(y.oidConcurso,
                                                       interfazRecordN(x)
                                                       .oidCliente); --1:valid 0:no valido
                END IF;
              
                IF (lsResult = '1') THEN
                  --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
                  SELECT COUNT(OID_ESTA_VENT)
                    INTO lnTotalOcurrencias
                    FROM INC_ESTAT_VENTA_CONSU
                   WHERE COPA_OID_PARA_GRAL = y.oidConcurso;
                
                  lsResult := '1';
                  IF (lnTotalOcurrencias > 0) THEN
                    lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(y.oidConcurso,
                                                               interfazRecordN(x)
                                                               .oidCliente,
                                                               interfazRecordN(x)
                                                               .camreta,
                                                               lsCodPeriodoant); --1:valid 0:no valido
                  END IF;
                
                  IF (lsResult = '1') THEN
                    IF (lsIndProductos = '1') THEN
                      SELECT (SELECT COUNT(1)
                                FROM INC_PRODU_VALID
                               WHERE PRDU_OID_PROD = pro.OID_PROD),
                             (SELECT COUNT(1)
                                FROM INC_PRODU_EXCLU
                               WHERE PRDU_OID_PROD = pro.OID_PROD),
                             (SELECT COUNT(1)
                                FROM INC_PRODU_BONIF
                               WHERE PRDU_OID_PROD = pro.OID_PROD),
                             (SELECT COUNT(1)
                                FROM INC_PRODU_EXIGI
                               WHERE PRDU_OID_PROD = pro.OID_PROD),
                             (SELECT COUNT(1)
                                FROM INC_PRODU_EXIGI
                               WHERE PRDU_OID_PROD = pro.OID_PROD
                                 AND NVL(IND_AGRUP, 'T') = 'T')
                        INTO lnTotalProdVal,
                             lnTotalProdExc,
                             lnTotalProdBon,
                             lnTotalProdExi,
                             lnTotalProdExiOblig
                        FROM INC_PRODU pro, INC_CONCU_PARAM_GENER gen
                       WHERE pro.COPA_OID_PARA_GRAL(+) = gen.OID_PARA_GRAL
                         AND gen.OID_PARA_GRAL = y.oidConcurso;
                    
                      lnPuntajeTotal    := 0;
                      lnPuntajeTotalBon := 0;
                      lnPuntajeTotalExi := 0;
                      lnImporte         := 0;
                      lnUnidades        := 0;
                      lnTotalOblig      := 0;
                    
                      IF (lnTotalProdExiOblig > 0) THEN
                        v_lineaOblig.DELETE;
                        v_totalOblig.DELETE;
                      
                        FOR k IN (SELECT DECODE(PROD_OID_PROD,
                                                NULL,
                                                '',
                                                'A' || TO_CHAR(PROD_OID_PROD)) ||
                                         DECODE(TOFE_OID_TIPO_OFER,
                                                NULL,
                                                '',
                                                'B' ||
                                                TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                                         DECODE(MAPR_OID_MARC_PROD,
                                                NULL,
                                                '',
                                                'C' ||
                                                TO_CHAR(MAPR_OID_MARC_PROD)) ||
                                         DECODE(NEGO_OID_NEGO,
                                                NULL,
                                                '',
                                                'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                                         DECODE(UNEG_OID_UNID_NEGO,
                                                NULL,
                                                '',
                                                'E' ||
                                                TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                                         DECODE(GENE_OID_GENE,
                                                NULL,
                                                '',
                                                'F' || TO_CHAR(GENE_OID_GENE)) ||
                                         DECODE(SGEN_OID_SUPE_GENE,
                                                NULL,
                                                '',
                                                'G' ||
                                                TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                                         DECODE(OFDE_OID_DETA_OFER,
                                                NULL,
                                                '',
                                                'H' ||
                                                TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA
                                    FROM INC_PRODU pro, INC_PRODU_EXIGI exi
                                   WHERE pro.OID_PROD = exi.PRDU_OID_PROD
                                     AND pro.COPA_OID_PARA_GRAL =
                                         y.oidConcurso) LOOP
                        
                          v_lineaOblig.EXTEND(1);
                          v_lineaOblig(v_lineaOblig.COUNT) := k.linea;
                          v_totalOblig.EXTEND(1);
                          v_totalOblig(v_totalOblig.COUNT) := 0;
                        END LOOP;
                      
                      END IF;
                    
                      --BARREMOS TODAS LAS POSICIONES DE LA ORDEN DE RETAIL
                      FOR z IN (SELECT PRO.OID_PROD PROD_OID_PROD,
                                       TIP.OID_TIPO_OFER TOFE_OID_TIPO_OFER,
                                       pro.MAPR_OID_MARC_PROD,
                                       pro.NEGO_OID_NEGO,
                                       pro.UNEG_OID_UNID_NEGO,
                                       pro.GENE_OID_GENE,
                                       pro.SGEN_OID_SUPE_GENE,
                                       DET.VAL_MONT_CATA VAL_PREC_CATA_UNIT_LOCA,
                                       (DET.UNI_VEND - DET.UNI_DEVU) NUM_UNID_DEMA_REAL,
                                       (SELECT MAX(a.OID_DETA_OFER)
                                          FROM PRE_OFERT_DETAL       a,
                                               PRE_MATRI_FACTU       b,
                                               PRE_MATRI_FACTU_CABEC c
                                         WHERE a.OID_DETA_OFER =
                                               b.OFDE_OID_DETA_OFER
                                           AND b.MFCA_OID_CABE = c.oid_cabe
                                           AND c.PERD_OID_PERI =
                                               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(NVL(DET.CAM_RETA,
                                                                                              CAB.CAM_PROC),
                                                                                          lnOidMarca,
                                                                                          lnOidCanal)
                                           AND a.VAL_CODI_VENT = DET.COD_VENT) OFDE_OID_DETA_OFER,
                                       NVL(DET.CAM_RETA, CAB.CAM_PROC) CAM_RETA
                                  FROM RET_VENTA_CABEC CAB,
                                       RET_VENTA_DETAL DET,
                                       MAE_PRODU       PRO,
                                       PRE_TIPO_OFERT  TIP
                                 WHERE CAB.COD_PAIS = psCodigoPais
                                   AND CAB.COD_PAIS = DET.COD_PAIS
                                   AND CAB.COD_SBAC = DET.COD_SBAC
                                   AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
                                   AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
                                   AND PRO.COD_SAP(+) = DET.COD_PROD
                                   AND TO_NUMBER(TIP.COD_TIPO_OFER(+)) =
                                       TO_NUMBER(TRIM(DET.COD_TIPO_OFER))
                                   AND TIP.CANA_OID_CANA(+) = lnOidcanal
                                   AND DET.TIPO_TRAN_RET IN ('VR', 'RR', 'RD')
                                   AND ( CAB.CAM_PROC = interfazRecordN(x).periodo 
                                         OR
                                         (NVL(CAB.CAM_RETA, CAB.CAM_PROC) = interfazRecordN(x).camreta AND lnIndNoPiloRetail=0)
                                       )
                                   AND (
                                         ( (lsIndicadorProceso=PROCESO_CIERRE_DIARIO) 
                                               AND 
                                           ((NVL(CAB.IND_PUNT, '0') = '0') OR lnindreproceso = 1)
                                         ) 
                                          OR
                                         (lsIndicadorProceso =PROCESO_CIERRE_CAMPANA)
                                        )
                                      
                                   AND CAB.VAL_CUEN_CONSU = interfazRecordN(x).codCliente) LOOP
                      
                        lsResult := '1';
                      
                        --HACEMOS EL CALCULO DE PUNTAJE POR POSICION
                        IF (lsResult = '1') THEN
                          --REALIZAMOS VALIDACIONES DE PRODUCTOS VALIDOS
                          IF (lnTotalProdVal > 0) THEN
                            lsResult := INC_FN_VALID_PRODU_CONCU('1',
                                                                 y.oidConcurso,
                                                                 z.PROD_OID_PROD,
                                                                 z.TOFE_OID_TIPO_OFER,
                                                                 z.MAPR_OID_MARC_PROD,
                                                                 z.NEGO_OID_NEGO,
                                                                 z.UNEG_OID_UNID_NEGO,
                                                                 z.GENE_OID_GENE,
                                                                 z.SGEN_OID_SUPE_GENE,
                                                                 z.OFDE_OID_DETA_OFER,
                                                                 z.cam_reta);
                          END IF;
                          IF (lsResult = '1' AND lnTotalProdExc > 0) THEN
                            lsResult := INC_FN_VALID_PRODU_CONCU('2',
                                                                 y.oidConcurso,
                                                                 z.PROD_OID_PROD,
                                                                 z.TOFE_OID_TIPO_OFER,
                                                                 z.MAPR_OID_MARC_PROD,
                                                                 z.NEGO_OID_NEGO,
                                                                 z.UNEG_OID_UNID_NEGO,
                                                                 z.GENE_OID_GENE,
                                                                 z.SGEN_OID_SUPE_GENE,
                                                                 z.OFDE_OID_DETA_OFER,
                                                                 z.cam_reta);
                          END IF;
                        
                          --Si es Producto Valido, Hacemos el Calculo de Puntaje
                          IF (lsResult = '1') THEN
                            lnImporte  := lnImporte + (z.VAL_PREC_CATA_UNIT_LOCA *
                                          z.NUM_UNID_DEMA_REAL);
                            lnUnidades := lnUnidades + z.NUM_UNID_DEMA_REAL;
                          
                            IF (y.baseCalculo = 1) THEN
                              --Monto
                              lnPuntajePosicion := ((z.VAL_PREC_CATA_UNIT_LOCA *
                                                   z.NUM_UNID_DEMA_REAL) /
                                                   y.factorConversion) *
                                                   y.puntosAsignar;
                            END IF;
                          
                            IF (y.baseCalculo = 2) THEN
                              --Unidades
                              lnPuntajePosicion := ((z.NUM_UNID_DEMA_REAL) /
                                                   y.factorConversion) *
                                                   y.puntosAsignar;
                            END IF;
                          
                            --Verificamos si es un producto Bonificado
                            lnPuntajePosBonif := 0;
                            IF (lnTotalProdBon > 0) THEN
                              lsResult := INC_FN_VALID_PRODU_CONCU('3',
                                                                   y.oidConcurso,
                                                                   z.PROD_OID_PROD,
                                                                   z.TOFE_OID_TIPO_OFER,
                                                                   z.MAPR_OID_MARC_PROD,
                                                                   z.NEGO_OID_NEGO,
                                                                   z.UNEG_OID_UNID_NEGO,
                                                                   z.GENE_OID_GENE,
                                                                   z.SGEN_OID_SUPE_GENE,
                                                                   z.OFDE_OID_DETA_OFER,
                                                                   z.cam_reta);
                            
                              IF (lsResult = '1') THEN
                                lnPuntajePosBonif := INC_FN_CALCU_PUNTA_BONIF(y.oidConcurso,
                                                                              z.PROD_OID_PROD,
                                                                              z.TOFE_OID_TIPO_OFER,
                                                                              z.MAPR_OID_MARC_PROD,
                                                                              z.NEGO_OID_NEGO,
                                                                              z.UNEG_OID_UNID_NEGO,
                                                                              z.GENE_OID_GENE,
                                                                              z.SGEN_OID_SUPE_GENE,
                                                                              z.OFDE_OID_DETA_OFER,
                                                                              z.cam_reta,
                                                                              lnPuntajePosicion,
                                                                              z.NUM_UNID_DEMA_REAL);
                              END IF;
                            
                              lnPuntajeTotalBon := lnPuntajeTotalBon +
                                                   lnPuntajePosBonif;
                            END IF;
                          
                            --                            lnPuntajeTotal := lnPuntajeTotal + lnPuntajePosicion +
                            --                                              lnPuntajePosBonif;
                            lnPuntajeTotal := lnPuntajeTotal +
                                              lnPuntajePosicion;
                          END IF;
                        
                          --HACEMOS EL CALCULO DE PUNTAJE EXIGIDO POR POSICION
                          IF (lnTotalProdExi > 0) THEN
                            INC_PR_VALID_PRODU_EXIGI_CONCU(y.oidConcurso,
                                                           z.PROD_OID_PROD,
                                                           z.TOFE_OID_TIPO_OFER,
                                                           z.MAPR_OID_MARC_PROD,
                                                           z.NEGO_OID_NEGO,
                                                           z.UNEG_OID_UNID_NEGO,
                                                           z.GENE_OID_GENE,
                                                           z.SGEN_OID_SUPE_GENE,
                                                           z.OFDE_OID_DETA_OFER,
                                                           z.cam_reta,
                                                           lsResult,
                                                           v_lineaOblig,
                                                           v_totalOblig);
                          ELSE
                            lsResult := 0;
                          END IF;
                        
                          IF (lsResult = '1') THEN
                            lnPuntajePosicion := 0;
                          
                            IF (y.baseCalculo = 1) THEN
                              --Monto
                              lnPuntajePosicion := ((z.VAL_PREC_CATA_UNIT_LOCA *
                                                   z.NUM_UNID_DEMA_REAL) /
                                                   y.factorConversion) *
                                                   y.puntosAsignar;
                            END IF;
                          
                            IF (y.baseCalculo = 2) THEN
                              --Unidades
                              lnPuntajePosicion := (z.NUM_UNID_DEMA_REAL /
                                                   y.factorConversion) *
                                                   y.puntosAsignar;
                            END IF;
                          
                            --Si Puntaje Exigido <> 0, recien se contabiliza los marcados como Obligatorio
                            IF (lnPuntajePosicion <> 0) THEN
                              lnPuntajeTotalExi := lnPuntajeTotalExi +
                                                   lnPuntajePosicion;
                            
                            END IF;
                          
                          END IF;
                        
                        END IF;
                      
                      END LOOP;
                    
                      --PARA LAS ORDENES DE COMPRA
                      --Si no cumple con el total de Obligatorios el puntaje de Total Exigido sera 0
                      IF (lnTotalProdExiOblig > 0) THEN
                        lnTotalOblig := 1;
                        FOR i IN 1 .. v_totalOblig.COUNT LOOP
                          lnTotalOblig := lnTotalOblig * v_totalOblig(i);
                        END LOOP;
                      
                        IF (lnTotalOblig = 0) THEN
                          lnPuntajeTotalExi := 0;
                        END IF;
                      
                      END IF;
                    
                      --TRUNCAMOS LOS TOTALES DE PUNTAJE
                      lnPuntajeTotalBon := round(lnPuntajeTotalBon, 0);
                      lnNumeroPuntos    := round(lnPuntajeTotal, 0) +
                                           lnPuntajeTotalBon;
                      lnNumeroPuntosExi := round(lnPuntajeTotalExi, 0);
                    
                    ELSE
                    
                      IF (y.baseCalculo = 1) THEN
                        --MONTO
                        --Obtenemos obtencion Puntos
                        lnNumeroPuntos := interfazRecordN(x)
                                          .puntaje * y.puntosAsignar /
                                           y.factorConversion;
                      
                        lnNumeroPuntosExi := 0;
                      END IF;
                    
                      IF (y.baseCalculo = 2) THEN
                        --UNIDADES
                        --Obtenemos obtencion Puntos
                        lnNumeroPuntos := interfazRecordN(x)
                                          .unidades * y.puntosAsignar /
                                           y.factorConversion;
                      
                        lnNumeroPuntosExi := 0;
                      END IF;
                    
                    END IF;
                  
                    IF (lnNumeroPuntos <> 0 OR lnNumeroPuntosExi <> 0) THEN
                      lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                    
                      lnTipoMov := 1;
                      IF (lnNumeroPuntos < 0) THEN
                        lnTipoMov := 2;
                      END IF;

    
                      SELECT COUNT(*)
                      INTO lnTotalOcurrencias
                      FROM INC_CANDI_GANAD
                      WHERE COPA_OID_PARA_GRAL = y.oidConcurso
                         AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
    
                      IF lnTotalOcurrencias = 0 THEN

                            INSERT INTO INC_CANDI_GANAD
                              (OID_CAND_GANA,
                                                    IND_META_SUPE,
                                                    VAL_REQU_PREM_SUPE,
                                                    PERD_OID_PERI,
                                                    COPA_OID_PARA_GRAL,
                                                    BINC_OID_BASE_INCU,
                                                    PERD_OID_PERI_EVAL,
                                                    CLIE_OID_CLIE,
                                                    FEC_ULTI_ACTU,
                                                    NUM_PERI_EVAL)
                            VALUES
                              (INC_CAGA_SEQ.nextval,
                                                     0,
                                                     0,
                                                     lnOidPeriodo,
                                                     y.oidConcurso,
                                                     NULL,
                                                     NULL,
                                                     interfazRecordN(x).oidCliente,
                                                     SYSDATE,
                                                     1);
                      END IF;
    
  
                      --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
                      INSERT INTO INC_CUENT_CORRI_PUNTO
                        (OID_CUEN_CORR_PUNT,
                         NUM_MOVI,
                         NUM_PUNT,
                         NUM_PUNT_EXIG,
                         FEC_MOVI,
                         COPA_OID_PARA_GRAL,
                         CLIE_OID_CLIE,
                         PERD_OID_PERI,
                         TMOV_OID_TIPO_MOVI,
                         FEC_ULTI_ACTU,
                         VAL_DESC,
                         USU_MODI,
                         NUM_PUNT_BONI,
                         DES_MOTI)
                      VALUES
                        (lnOidSecuencia,
                         lnOidSecuencia,
                         lnNumeroPuntos,
                         lnNumeroPuntosExi,
                         ldFechaProceso,
                         y.oidConcurso,
                         interfazRecordN(x).oidCliente,
                         lnOidPeriodo,
                         lnTipoMov,
                         SYSDATE,
                         'Puntos por Venta Retail campaña ' || interfazRecordN(x)
                         .camreta || ' /ECM',
                         psCodigoUsuario,
                         lnPuntajeTotalBon,
                         'Venta Retail');
                    
                      lbGenerado := TRUE;
                    
                    END IF;
                  END IF;
                
                END IF;
              END IF;
            
            END IF;
          
          END LOOP;
        
          IF interfazRecordN(x).indgenepunt<>'1' then
        
             IF  (lbGenerado) THEN
             --Actualizamos como Puntaje Generado = 1
                 UPDATE INC_PUNTO_RETAI
                 SET IND_GENE_PUNT = '1'
                 WHERE COR_PUNT_RETA = interfazRecordN(x).correlativo;
          
             ELSE
             --Actualizamos como Puntaje Generado =2
                 UPDATE INC_PUNTO_RETAI
                 SET IND_GENE_PUNT = '2'
                 WHERE COR_PUNT_RETA = interfazRecordN(x).correlativo;
             END IF;
          END IF;
        
          IF (lsIndicadorProceso = PROCESO_CIERRE_DIARIO) AND (lbGenerado) THEN
            FOR z IN (SELECT DISTINCT CAB.COD_PAIS,
                                      CAB.COD_SBAC,
                                      CAB.COD_TIPO_DOCU,
                                      CAB.NUM_DOCU_RETA
                        FROM RET_VENTA_CABEC CAB, RET_VENTA_DETAL DET
                       WHERE CAB.COD_PAIS = psCodigoPais
                         AND CAB.COD_PAIS = DET.COD_PAIS
                         AND CAB.NUM_DOCU_RETA = DET.NUM_DOCU_RETA
                         AND CAB.COD_TIPO_DOCU = DET.COD_TIPO_DOCU
                         AND CAB.COD_SBAC = DET.COD_SBAC
                            --AND DET.TIPO_TRAN_RET IN ('VR', 'RR')  -- Cambio 10/11/2014
                         AND CAB.VAL_CUEN_CONSU = interfazRecordN(x)
                            .codCliente
                         AND ( CAB.CAM_PROC = interfazRecordN(x).periodo 
                                  OR
                               (NVL(CAB.CAM_RETA, CAB.CAM_PROC) = interfazRecordN(x).camreta AND lnIndNoPiloRetail=0)
                              )                                                                                    
--                         AND CAB.CAM_PROC = psCodigoPeriodo
--                         AND (NVL(CAB.CAM_RETA, psCodigoPeriodo) = interfazRecordN(x)
--                             .camreta OR lnIndNoPiloRetail=1)
                         AND NVL(CAB.IND_PUNT, '0') = '0') LOOP
            
              UPDATE RET_VENTA_CABEC
                 SET IND_PUNT = '1',
                     USU_MODI = psCodigoUsuario,
                     FEC_MODI = SYSDATE
               WHERE COD_PAIS = z.COD_PAIS
                 AND COD_SBAC = z.COD_SBAC
                 AND COD_TIPO_DOCU = z.COD_TIPO_DOCU
                 AND NUM_DOCU_RETA = z.NUM_DOCU_RETA;
            END LOOP;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Puntaje%NOTFOUND;
    END LOOP;
    CLOSE c_Puntaje;
  /*
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_PUNTA_RETAI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    */
  END INC_PR_CALCU_PUNTA_RETAI;

  /**************************************************************************
  Descripcion       : Valida si el Estatus Venta del Cliente
  
  Fecha Creacion    : 13/03/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente    :  oid Cliente
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_ESTAT_VENTA(pnOidConcurso NUMBER,
                                    pnOidCliente  NUMBER) RETURN VARCHAR2 IS
  
    lnTotalOcurrencias NUMBER;
  BEGIN
  
    FOR x IN (SELECT est.ESTA_OID_ESTA_CLIE,
                     cord.COD_PERI          COD_PERI_DESD,
                     corh.COD_PERI          COD_PERI_HAST
                FROM INC_ESTAT_VENTA_CONSU est,
                     CRA_PERIO             crad,
                     CRA_PERIO             crah,
                     SEG_PERIO_CORPO       cord,
                     SEG_PERIO_CORPO       corh
               WHERE est.COPA_OID_PARA_GRAL = pnOidConcurso
                 AND est.PERD_OID_PERI_DESD = crad.OID_PERI
                 AND crad.PERI_OID_PERI = cord.OID_PERI
                 AND est.PERD_OID_PERI_HAST = crah.OID_PERI
                 AND crah.PERI_OID_PERI = corh.OID_PERI) LOOP
    
      SELECT COUNT(1)
        INTO lnTotalOcurrencias
        FROM MAE_CLIEN_HISTO_ESTAT his, CRA_PERIO cra, seg_perio_corpo cor
       WHERE his.CLIE_OID_CLIE = pnOidCliente
         AND his.PERD_OID_PERI = cra.OID_PERI
         AND cra.PERI_OID_PERI = cor.OID_PERI
         AND his.ESTA_OID_ESTA_CLIE = x.ESTA_OID_ESTA_CLIE
         AND cor.COD_PERI >= x.COD_PERI_DESD
         AND cor.COD_PERI <= x.COD_PERI_HAST;
    
      IF (lnTotalOcurrencias > 0) THEN
        RETURN '1';
      END IF;
    
    END LOOP;
  
    RETURN '0';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_ESTAT_VENTA: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_ESTAT_VENTA;

  ------------------------------

  PROCEDURE INC_PR_INSER_REGIS_RECOM_JPCC(pnOidPais         NUMBER,
                                          pnOidCliente      NUMBER,
                                          pnOidPeriodo      NUMBER,
                                          pnOidClienteRcdte NUMBER,
                                          pnOidModulo       NUMBER) IS
    lsCodPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoProceso  CRA_PERIO.OID_PERI%TYPE;
  
    lnOidClienteRcdte INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidConcurso     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lnTotal            NUMBER;
    lnPasoPedido       NUMBER;
    lnTotalPedidoRecom NUMBER;
  
    CURSOR c_recomendacion(oidPais        NUMBER,
                           oidPeriodo     NUMBER,
                           oidClienteVnte NUMBER) IS
    
      SELECT copa.oid_para_gral
        FROM inc_concu_param_gener copa
       WHERE copa.perd_oid_peri_desd <= oidPeriodo
         AND copa.perd_oid_peri_hast >= oidPeriodo
         AND copa.diri_oid_diri = 1 --es dirigido a la Consultora
         AND copa.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
         AND copa.ind_acti = 1; --concurso activo
  
    TYPE crecomrec IS RECORD(
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE);
  
    TYPE crecomrectab IS TABLE OF crecomrec;
    crecomrecord crecomrectab;
  
  BEGIN
    --Se recuperan los concursos activos para la campaña de proceso
    OPEN c_recomendacion(pnOidPais, pnOidPeriodo, pnOidClienteRcdte);
    LOOP
      FETCH c_recomendacion BULK COLLECT
        INTO crecomrecord LIMIT 1000;
      IF crecomrecord.count > 0 THEN
      
        FOR xx IN crecomrecord.first .. crecomrecord.last LOOP
        
          lnOidConcurso := crecomrecord(xx).oidConcurso;
        
          IF (INC_FN_VALID_AMBIT_GEOGR_CONCU(lnOidConcurso,
                                             pnOidClienteRcdte) = 1) THEN
          
            SELECT a.oid_clie_rete
              INTO lnOidClienteRcdte
              FROM inc_clien_recte a
             WHERE clie_oid_clie = pnOidClienteRcdte
               AND copa_oid_para_gral = lnOidConcurso;
          
            IF lnOidClienteRcdte IS NULL THEN
            
              SELECT inc_clr3_seq.NEXTVAL INTO lnOidClienteRcdte FROM dual;
            
              INSERT INTO inc_clien_recte
                (oid_clie_rete,
                 ind_fin_vinc,
                 clie_oid_clie,
                 copa_oid_para_gral,
                 ind_eval,
                 oid_modu,
                 fec_crea)
              VALUES
                (lnOidClienteRcdte,
                 NULL,
                 pnOidClienteRcdte,
                 lnOidConcurso,
                 NULL,
                 pnOidModulo,
                 SYSDATE);
            END IF;
          
            IF (pnOidCliente IS NOT NULL AND lnOidClienteRcdte IS NOT NULL) THEN
            
              INSERT INTO inc_clien_recdo
                (oid_clie_redo,
                 ind_efec,
                 num_prem,
                 clie_oid_clie,
                 clr3_oid_clie_rete,
                 perd_oid_peri,
                 panp_oid_para_nive_prem,
                 ind_eval,
                 oid_modu,
                 fec_crea)
              VALUES
                (inc_clre_seq.nextval,
                 NULL,
                 NULL,
                 pnOidCliente,
                 lnOidClienteRcdte,
                 pnOidPeriodo,
                 NULL,
                 NULL,
                 pnOidModulo,
                 SYSDATE);
            END IF;
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_recomendacion%NOTFOUND;
    END LOOP;
    CLOSE c_recomendacion;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_INSER_REGIS_RECOM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_INSER_REGIS_RECOM_JPCC;

  /**************************************************************************
  Descripcion       : Generar de  forma automática las Solicitudes de Servicio
                      Bolsa de Faltante para las consultoras que no se les despacho
                      el premio por falta de stock.
  Fecha Creacion    : 26/04/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoZona     :  Codigo de zona
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_BOLSA_FALTA(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoZona    VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
    lnOidZona    ZON_ZONA.OID_ZONA%TYPE;
  
    lsConfFaltante BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lbProcesar     BOOLEAN;
  
    CURSOR c_Pedidos(oidPais NUMBER) IS
      SELECT BOLF.OID_BOLS_FALT,
             ZON.OID_ZONA,
             BOLF.COPA_OID_PARA_GENE,
             BOLF.VAL_CODI_VENT_FICT,
             BOLF.CLIE_OID_CLIE,
             PROD.OID_PROD,
             NVL(BOLF.NUM_PREM, 1) NUM_PREM, -- Modificado
             BOLF.NUM_UNID_FALT,
             INC_PKG_PROCE_INCEN.INC_FN_OBTIE_PRECI_RECLA(BOLF.COPA_OID_PARA_GENE,
                                                          BOLF.VAL_CODI_VENT_FICT) VAL_PREC_PUBL,
             BOLF.PERD_OID_PERI,
             BOLF.SOPO_OID_SOLI_POSI,
             BOLF.COD_ORIG_FALT,
             BOLF.IND_DESP_SERV
        FROM INC_BOLSA_FALTA BOLF,
             BEL_STOCK STO,
             MAE_PRODU PROD,
             (SELECT OID_ZONA, COD_ZONA
                FROM ZON_ZONA
               WHERE INSTR(psCodigoZona, COD_ZONA) > 0
                 AND IND_ACTI = 1
                 AND IND_BORR = 0) ZON,
             bel_estad_merca em,
             MAE_CLIEN_UNIDA_ADMIN UA,
             ZON_TERRI_ADMIN ZA,
             ZON_SECCI SEC
       WHERE BOLF.PROD_OID_PROD = STO.PROD_OID_PROD
         AND PROD.OID_PROD = BOLF.PROD_OID_PROD
         AND STO.ALMC_OID_ALMA =
             nvl((SELECT DISTINCT a1.oid_alma
                   FROM bel_almac             a1,
                        app_confi_centr_distr b1,
                        ape_confi_liafp_cabec c1,
                        ape_confi_liafp_detal d1
                  WHERE a1.ccdi_oid_confi_centr_distr =
                        b1.oid_conf_cent_dist
                    AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                    AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                    AND d1.zzon_oid_zona = ZON.OID_ZONA),
                 2001)
         AND BOLF.FEC_SOLU IS NULL
         AND EM.COD_ESTA = 'LD'
         AND EM.PAIS_OID_PAIS = oidPais
         AND EM.OID_ESTA_MERC = STO.ESME_OID_ESTA_MERC
         AND STO.VAL_SALD >= BOLF.NUM_UNID_FALT
            ---
         AND UA.CLIE_OID_CLIE = BOLF.CLIE_OID_CLIE
         AND UA.ZTAD_OID_TERR_ADMI = ZA.OID_TERR_ADMI
         AND ZA.ZSCC_OID_SECC = SEC.OID_SECC
         AND UA.IND_ACTI = 1
         AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA;
  
    TYPE interfazPedidos IS RECORD(
      oidBolsaFaltante INC_BOLSA_FALTA.OID_BOLS_FALT%TYPE,
      oidZona          ZON_ZONA.OID_ZONA%TYPE,
      oidConcursoSoli  PED_SOLIC_CABEC.COPA_OID_PARA_GENE%TYPE,
      codigoVenta      PED_SOLIC_POSIC.VAL_CODI_VENT_FICT%TYPE,
      oidCliente       MAE_CLIEN.OID_CLIE%TYPE,
      oidProducto      MAE_PRODU.OID_PROD%TYPE,
      numeroPremio     PED_SOLIC_CABEC.NUM_PREM%TYPE,
      numUnidFaltantes INC_BOLSA_FALTA.NUM_UNID_FALT%TYPE,
      precioPublico    INC_ARTIC_LOTE.IMP_PREC_PUBL%TYPE,
      oidPeriodoFalt   INC_BOLSA_FALTA.PERD_OID_PERI%TYPE,
      oidSolicitudPos  PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE,
      codOrigFaltante  INC_BOLSA_FALTA.COD_ORIG_FALT%TYPE,
      indDespServicio  INC_BOLSA_FALTA.IND_DESP_SERV%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    ltRegSolicitud  tRegSolicitud;
    ltRegPosicion   tablaRegPosicion := tablaRegPosicion();
    lnESTPEDIDOORIG PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI%TYPE;
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Recuperamos el parametro de Configuracion de Faltantes
    lsConfFaltante := GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                      'INC',
                                                      '003');
  
    --PROCESAMOS LOS FALTANTES
    OPEN c_Pedidos(lnOidPais);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lbProcesar := TRUE;
        
          --Si es Faltante Original y esta Marcado como Despacho Solicitud de Servicio
          --se procesa dicha solicitud, sino, no se procesa
          IF (lsConfFaltante = '1') THEN
            IF ((interfazRecordN(x).oidPeriodoFalt = lnOidPeriodo) AND
               (interfazRecordN(x).codOrigFaltante = 'FO')) THEN
            
              IF (interfazRecordN(x).indDespServicio = '1') THEN
                lbProcesar := TRUE;
              ELSE
                lbProcesar := FALSE;
              END IF;
            END IF;
          END IF;
        
          IF (lbProcesar) THEN
          
            lnESTPEDIDOORIG := 0;
          
            SELECT COUNT(*)
              INTO lnESTPEDIDOORIG
              FROM PED_SOLIC_CABEC SC,
                   PED_SOLIC_CABEC CON,
                   PED_SOLIC_POSIC SP
             WHERE SP.OID_SOLI_POSI = interfazRecordN(x).oidSolicitudPos
               AND SP.SOCA_OID_SOLI_CABE = SC.OID_SOLI_CABE
               AND SC.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
               AND SC.PERD_OID_PERI = CON.PERD_OID_PERI
               AND SC.PERD_OID_PERI = interfazRecordN(x).oidPeriodoFalt
               AND CON.ESSO_OID_ESTA_SOLI = 4;
          
            IF (lnESTPEDIDOORIG > 0) THEN
              --Pedido Anulado
              UPDATE INC_BOLSA_FALTA
                 SET FEC_SOLU = SYSDATE,
                     VAL_OBSE = 'Cerrado por factura origen anulada'
               WHERE OID_BOLS_FALT = interfazRecordN(x).oidBolsaFaltante;
            
            ELSE
            
              --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
              ltRegSolicitud.COD_PAIS      := psCodigoPais;
              ltRegSolicitud.COD_MARC      := psCodigoMarca;
              ltRegSolicitud.COD_CANA      := psCodigoCanal;
              ltRegSolicitud.OID_CLIE      := interfazRecordN(x).oidCliente;
              ltRegSolicitud.COD_PERI      := psCodigoPeriodo;
              ltRegSolicitud.FEC_PROG_FACT := NULL; --Se calculara en la Creacion de la Solicitud
              ltRegSolicitud.COD_OPER      := 'INC050';
              ltRegSolicitud.COD_CLAS_SOLI := 'I1';
              ltRegSolicitud.COD_TIPO_SOLI := NULL;
              ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
              ltRegSolicitud.OID_PARA_GRAL := interfazRecordN(x)
                                              .oidConcursoSoli;
              ltRegSolicitud.NUM_PREM      := interfazRecordN(x)
                                              .numeroPremio;
              ltRegSolicitud.COD_USUA      := psCodigoUsuario;
            
              ltRegSolicitud.OID_ACCE_FISI                  := 1;
              ltRegSolicitud.OID_GRUP_PROC                  := 3; --(GP3)
              ltRegSolicitud.OID_GRUP_PROC_SECU             := 3; --(GP3)
              ltRegSolicitud.NUM_CLIEN                      := 1;
              ltRegSolicitud.OID_MODU                       := 13; --Incentivos
              ltRegSolicitud.VAL_GLOS_OBSE                  := 'ATENCION BOLSA DE FALTANTE CIERRE DE ZONA';
              ltRegSolicitud.VAL_BASE_FLET_LOCA             := 0;
              ltRegSolicitud.VAL_TOTA_PAGA_LOCA             := 0;
              ltRegSolicitud.VAL_PREC_CATA_TOTA_LOCA        := 0;
              ltRegSolicitud.VAL_BASE_FLET_DOCU             := 0;
              ltRegSolicitud.VAL_PREC_CATA_TOTA_LOC_UNI_DEM := 0;
              ltRegSolicitud.VAL_UNID_DEMA_REAL_TOTA        := interfazRecordN(x)
                                                               .numUnidFaltantes;
              ltRegSolicitud.NUM_UNID_POR_ATEN_TOTA         := interfazRecordN(x)
                                                               .numUnidFaltantes;
              ltRegSolicitud.VAL_PREC_CONT_TOTA_LOCA        := interfazRecordN(x)
                                                               .precioPublico * interfazRecordN(x)
                                                               .numUnidFaltantes;
            
              --CREAMOS LOS DATOS DEL DETALLE DE LA SOLICITUD DE PEDIDO
              ltRegPosicion.EXTEND(1);
              ltRegPosicion(ltRegPosicion.LAST).OID_PROD := interfazRecordN(x)
                                                            .oidProducto;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := interfazRecordN(x)
                                                            .numUnidFaltantes;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := interfazRecordN(x)
                                                                      .codigoVenta;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := interfazRecordN(x)
                                                                  .numUnidFaltantes;
              ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := interfazRecordN(x)
                                                                           .precioPublico;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
            
              --CREAMOS LA SOLICITUD DE PEDIDO
              INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud, 'Z', ltRegPosicion);
            
              --Inicializamos la lista de Posiciones de la Nueva Solicitud
              ltRegPosicion.DELETE;
            
              --Actualizamos la bolsa faltantes como Atendida
              UPDATE INC_BOLSA_FALTA
                 SET FEC_SOLU = SYSDATE,
                     VAL_OBSE = 'Atendido campaña ' || psCodigoPeriodo
               WHERE OID_BOLS_FALT = interfazRecordN(x).oidBolsaFaltante;
            
            END IF;
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
    --  END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_SOLIC_BOLSA_FALTA: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_GENER_SOLIC_BOLSA_FALTA;

  /**************************************************************************
  Descripcion       : Obtiene el precio reclamo de un articulo de premio o de
                      su reemplazo
  
  Fecha Creacion    : 13/03/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente    :  oid Cliente
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_PRECI_RECLA(pnOidConcurso NUMBER,
                                    psCodigoVenta VARCHAR2) RETURN NUMBER IS
  
    lnPrecioPublico INC_ARTIC_LOTE.IMP_PREC_PUBL%TYPE;
  BEGIN
  
    SELECT IMP_PREC_PUBL
      INTO lnPrecioPublico
      FROM (SELECT E.IMP_PREC_PUBL, E.PROD_OID_PROD
              FROM INC_PARAM_GENER_PREMI A,
                   INC_PARAM_NIVEL_PREMI B,
                   INC_PREMI_ARTIC       C,
                   INC_LOTE_PREMI_ARTIC  D,
                   INC_ARTIC_LOTE        E
             WHERE A.COPA_OID_PARA_GRAL = pnOidConcurso
               AND B.PAGP_OID_PARA_GENE_PREM = A.OID_PARA_GENE_PREM
               AND C.PANP_OID_PARA_NIVE_PREM = B.OID_PARA_NIVE_PREM
               AND D.PRAR_OID_PREM_ARTI = C.OID_PREM_ARTI
               AND E.LOPA_OID_LOTE_PREM_ARTI = D.OID_LOTE_PREM_ARTI
               AND E.COD_VENT_FICT = psCodigoVenta
            UNION
            SELECT reem.IMP_PREC_PUBL, reem.PROD_OID_PROD
              FROM INC_PARAM_GENER_PREMI A,
                   INC_PARAM_NIVEL_PREMI B,
                   INC_PREMI_ARTIC       C,
                   INC_LOTE_PREMI_ARTIC  D,
                   INC_ARTIC_LOTE        E,
                   INC_REEMP_ARTIC_LOTE  reem
             WHERE A.COPA_OID_PARA_GRAL = pnOidConcurso
               AND B.PAGP_OID_PARA_GENE_PREM = A.OID_PARA_GENE_PREM
               AND C.PANP_OID_PARA_NIVE_PREM = B.OID_PARA_NIVE_PREM
               AND D.PRAR_OID_PREM_ARTI = C.OID_PREM_ARTI
               AND E.LOPA_OID_LOTE_PREM_ARTI = D.OID_LOTE_PREM_ARTI
               AND reem.ARLO_OID_ARTI_LOTE = E.OID_ARTI_LOTE
               AND reem.COD_VENT_FICT = psCodigoVenta)
     WHERE ROWNUM = 1;
  
    RETURN lnPrecioPublico;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 1;
      /*
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_OBTIE_PRECI_RECLA: ' || ls_sqlerrm);
      */
  END INC_FN_OBTIE_PRECI_RECLA;

  /**************************************************************************
  Descripcion       : Aplicar descuento adicional en Facturación.
  Fecha Creacion    : 24/05/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                         G: GP4
    psCodigoRegion     : Codigo de Region
    psCodigoZona       : Codigo de Zona
    psFechaFacturacion  :  Fecha de Facturacion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_APLIC_DESCU_ADICI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psIndicadorProceso VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     psCodigoZona       VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnMontoTotal  PED_SOLIC_CABEC_ACUM2.VAL_MONT_TOTA%TYPE;
    lnIndDctoFijo DTO_ESCLN.IND_DCTO_FIJO%TYPE;
  
    lnPorcentaje    PED_SOLIC_POSIC.VAL_PORC_DESC%TYPE;
    lnMontoPosicion PED_SOLIC_POSIC.VAL_IMPO_DESC_UNIT_LOCA%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT psc.OID_SOLI_CABE,
             psc.CLIE_OID_CLIE,
             cli.COD_CLIE,
             met.IMP_OBJE_DEMA_CATA,
             met.POR_APLI
        FROM PED_SOLIC_CABEC             psc,
             PED_TIPO_SOLIC_PAIS         tsp,
             PED_TIPO_SOLIC              ts,
             MAE_CLIEN                   cli,
             INC_CONSU_REGIS_METAS_OBJET met
       WHERE psc.PAIS_OID_PAIS = oidPais
         AND psc.PERD_OID_PERI = oidPeriodo
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND ts.COD_TIPO_SOLI = 'SOC'
         AND psc.CLIE_OID_CLIE = cli.OID_CLIE
         AND psc.FEC_FACT IS NULL
         AND psc.GRPR_OID_GRUP_PROC = 4
         AND met.COD_CLIE = cli.COD_CLIE
         AND met.COD_CAMP_EVAL = psCodigoPeriodo
         AND met.IND_OBJE IS NULL
         AND met.EST_REGI = 1;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud  PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente    MAE_CLIEN.OID_CLIE%TYPE,
      codigoCliente MAE_CLIEN.COD_CLIE%TYPE,
      importe       INC_CONSU_REGIS_METAS_OBJET.IMP_OBJE_DEMA_CATA%TYPE,
      porcentaje    INC_CONSU_REGIS_METAS_OBJET.POR_APLI%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --PROCESAMOS A LOS PEDIDOS DE GP4
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          SELECT VAL_MONT_TOTA
            INTO lnMontoTotal
            FROM PED_SOLIC_CABEC_ACUM2
           WHERE PERD_OID_PERI = lnOidPeriodo
             AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
        
          --Si se cumple entonces se alcanzó la meta
          IF (lnMontoTotal >= interfazRecordN(x).importe) THEN
            UPDATE INC_CONSU_REGIS_METAS_OBJET
               SET IND_OBJE = 1
             WHERE COD_CLIE = interfazRecordN(x).codigoCliente
               AND COD_CAMP_EVAL = psCodigoPeriodo;
          
            --Recorremos todas las posiciones de la orden de compra en donde val_porc_desc>0
            FOR y IN (SELECT OID_SOLI_POSI,
                             ESLO_OID_ESLN,
                             VAL_PORC_DESC,
                             VAL_PREC_CATA_UNIT_LOCA
                        FROM PED_SOLIC_POSIC
                       WHERE SOCA_OID_SOLI_CABE = interfazRecordN(x)
                            .oidSolicitud
                         AND VAL_PORC_DESC > 0) LOOP
            
              SELECT IND_DCTO_FIJO
                INTO lnIndDctoFijo
                FROM DTO_ESCLN
               WHERE OID_ESLN = y.ESLO_OID_ESLN;
            
              --si IND_DCTO_FIJO=1 entonces pasar al siguiente registro
              --luego recalcular los campos de descuento unitario de la posición
              IF ((lnIndDctoFijo = 0) OR (lnIndDctoFijo IS NULL)) THEN
                lnPorcentaje := y.VAL_PORC_DESC + interfazRecordN(x)
                               .porcentaje;
              
                lnMontoPosicion := ROUND((lnPorcentaje *
                                         y.VAL_PREC_CATA_UNIT_LOCA) / 100,
                                         2);
              
                UPDATE PED_SOLIC_POSIC
                   SET VAL_PORC_DESC           = lnPorcentaje,
                       VAL_IMPO_DESC_UNIT_DOCU = lnMontoPosicion,
                       VAL_IMPO_DESC_UNIT_LOCA = lnMontoPosicion
                 WHERE OID_SOLI_POSI = y.OID_SOLI_POSI;
              
              END IF;
            
            END LOOP;
          
          ELSE
            UPDATE INC_CONSU_REGIS_METAS_OBJET
               SET IND_OBJE = 0
             WHERE COD_CLIE = interfazRecordN(x).codigoCliente
               AND COD_CAMP_EVAL = psCodigoPeriodo;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_APLIC_DESCU_ADICI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_APLIC_DESCU_ADICI;

  /**************************************************************************
  Descripcion       : Realiza el cálculo de puntaje a abonar por los concursos
                      que exigen puntos por pedidos  multimarca
  Fecha Creacion    : 06/06/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_MULTI_MARCA(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsCodigoPeriodoSig SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoSig    CRA_PERIO.OID_PERI%TYPE;
    ldFechaProceso     BAS_CTRL_FACT.FEC_PROC%TYPE;
    lnOidSecuencia     INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
  
    CURSOR c_Consultoras(oidConcurso NUMBER, oidPeriodo NUMBER) IS
      SELECT DISTINCT CLIE_OID_CLIE
        FROM INC_CUENT_CORRI_PUNTO
       WHERE COPA_OID_PARA_GRAL = oidConcurso
         AND PERD_OID_PERI = lnOidPeriodo
         AND NUM_PUNT_EXIG > 0;
  
    TYPE interfazConsultoras IS RECORD(
      oidCliente MAE_CLIEN.OID_CLIE%TYPE);
  
    TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
    interfazRecordN interfazConsultorasTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos el periodo Siguiente
    lsCodigoPeriodoSig := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal,
                                                                 1);
  
    lnOidPeriodoSig := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoSig,
                                                                  lnOidMarca,
                                                                  lnOidCanal);
  
    --Obtenemos la Fecha de Proceso
    SELECT FEC_PROC
      INTO ldFechaProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --PROCESAMOS A LAS CONCURSOS MULTIMARCA
    FOR y IN (SELECT C.OID_PARA_GRAL, C.NUM_PUNT_ABON
                FROM INC_CONCU_PARAM_GENER C,
                     INC_VERSI_CONCU       V,
                     CRA_PERIO             PI,
                     SEG_PERIO_CORPO       SI,
                     CRA_PERIO             PF,
                     SEG_PERIO_CORPO       SF
               WHERE C.IND_ACTI = 1
                 AND C.OID_PARA_GRAL = V.COPA_OID_PARA_GRAL
                 AND V.VICO_OID_VIGE_CONC = 1
                 AND C.PERD_OID_PERI_DESD = PI.OID_PERI
                 AND PI.PERI_OID_PERI = SI.OID_PERI
                 AND C.PERD_OID_PERI_HAST = PF.OID_PERI
                 AND PF.PERI_OID_PERI = SF.OID_PERI
                 AND SI.COD_PERI <= psCodigoPeriodo
                 AND SF.COD_PERI >= psCodigoPeriodo
                 AND C.IND_MULT_MARC = 1
                 AND C.BCAL_OID_BASE_CALC = 1) LOOP
    
      OPEN c_Consultoras(y.OID_PARA_GRAL, lnOidPeriodo);
      LOOP
        FETCH c_Consultoras BULK COLLECT
          INTO interfazRecordN LIMIT W_FILAS;
        IF interfazRecordN.COUNT > 0 THEN
        
          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          
            lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
          
            INSERT INTO INC_CUENT_CORRI_PUNTO
              (OID_CUEN_CORR_PUNT,
               NUM_MOVI,
               NUM_PUNT,
               NUM_PUNT_EXIG,
               FEC_MOVI,
               COPA_OID_PARA_GRAL,
               CLIE_OID_CLIE,
               PERD_OID_PERI,
               TMOV_OID_TIPO_MOVI,
               FEC_ULTI_ACTU,
               VAL_DESC,
               USU_MODI)
            VALUES
              (lnOidSecuencia,
               lnOidSecuencia,
               y.NUM_PUNT_ABON,
               0,
               ldFechaProceso,
               y.OID_PARA_GRAL,
               interfazRecordN(x).oidCliente,
               lnOidPeriodoSig,
               1,
               SYSDATE,
               'Abono Puntaje por Pedido Multimarca campaña ' ||
               lsCodigoPeriodoSig,
               psCodigoUsuario);
          
          END LOOP;
        
        END IF;
        EXIT WHEN c_Consultoras%NOTFOUND;
      END LOOP;
      CLOSE c_Consultoras;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_PUNTA_MULTI_MARCA: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_CALCU_PUNTA_MULTI_MARCA;

  /**************************************************************************
  Descripcion       : Generar pedidos de recomendación. Este proceso deberá
                      reemplazar al proceso P440 de SICC
  Fecha Creacion    : 18/06/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_PEDID_RECOM(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    ldFechaProceso     BAS_CTRL_FACT.FEC_PROC%TYPE;
    lsCodigoPeriodoAnt SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lnOidPediReco  INC_PEDID_CONCU_RECOM.OID_PEDI_CONC_RECO%TYPE;
    lnMontoPedido  INC_PEDID_CONCU_RECOM.IMP_MONT_PEDI%TYPE;
    lnUnidadPedido INC_PEDID_CONCU_RECOM.NUM_UNID_PEDI%TYPE;
  
    CURSOR c_Recomendantes(oidPeriodo NUMBER) IS
  SELECT OID_CLIE,
             CLRE_OID_CLIE_REDO,
             COPA_OID_PARA_GRAL,
             CLR3_OID_CLIE_RETE,
             CLIE_OID_CLIE_RTE,
             NVL(MAX(IMP_MONT_PEDI),0) IMP_MONT_PEDI,
             NVL(MAX(NUM_UNID_PEDI),0) NUM_UNID_PEDI,
             MAX(SOCA_OID_SOLI_CABE) SOCA_OID_SOLI_CABE
        FROM (SELECT              
               A.CLIE_OID_CLIE OID_CLIE,
               E.VAL_MONT_TOTA IMP_MONT_PEDI,
               E.VAL_UNID_TOTA NUM_UNID_PEDI,
               NULL CLRE_OID_CLIE_REDO,
               D.COPA_OID_PARA_GRAL,
               D.OID_CLIE_RETE CLR3_OID_CLIE_RETE,
               A.OID_SOLI_CABE SOCA_OID_SOLI_CABE,
               NULL CLIE_OID_CLIE_RTE              
                FROM PED_SOLIC_CABEC     A,
                     PED_TIPO_SOLIC_PAIS B,
                     PED_TIPO_SOLIC      C,
                     INC_CLIEN_RECTE     D,
                     PED_SOLIC_CABEC_ACUM2 E
               WHERE A.PERD_OID_PERI =  OidPeriodo
                 AND A.TSPA_OID_TIPO_SOLI_PAIS = B.Oid_Tipo_Soli_Pais
                 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                 AND C.COD_TIPO_SOLI = 'SOC'
                    --       AND A.FEC_FACT IS NULL  -- Ajuste para incluir a todas las recomendantes
                 AND A.GRPR_OID_GRUP_PROC IN (3,4,5) -- Ajuste para incluir a todas las recomendantes
                 AND A.CLIE_OID_CLIE = D.CLIE_OID_CLIE
                 AND A.CLIE_OID_CLIE   = E.CLIE_OID_CLIE(+)
                 AND A.PERD_OID_PERI = E.PERD_OID_PERI(+)
                 AND D.COPA_OID_PARA_GRAL IN
                     (SELECT cpg.oid_para_gral
                        FROM INC_CONCU_PARAM_GENER cpg,
                             INC_CONCU_PARAM_CONSU cpc
                       WHERE cpg.diri_oid_diri = 1 --es dirigido a la Consultora
                         AND cpg.bcal_oid_base_calc = 4 --Valor para base Calculo de Recomendación
                         AND cpg.oid_para_gral = cpc.copa_oid_para_gral
                         AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(OidPeriodo) BETWEEN
                             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(nvl(cpg.perd_oid_peri_desd,
                                                                        0)) AND
                             GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_hast),
                                                nvl(cpc.num_mini_pedi, 0) - 1)
                         AND cpg.ind_acti = 1)                       
                 AND NOT EXISTS
               (SELECT CLR3_OID_CLIE_RETE
                        FROM INC_PEDID_CONCU_RECOM RECOM
                       WHERE CLRE_OID_CLIE_REDO IS NULL
                         AND A.GRPR_OID_GRUP_PROC = 5
                         AND RECOM.PERD_OID_PERI = OidPeriodo
                         AND RECOM.CLR3_OID_CLIE_RETE = D.OID_CLIE_RETE)                    
                         )
       GROUP BY OID_CLIE,
                CLRE_OID_CLIE_REDO,
                COPA_OID_PARA_GRAL,
                CLR3_OID_CLIE_RETE,
                CLIE_OID_CLIE_RTE;
  
    CURSOR c_Recomendandos(oidPeriodo NUMBER) IS
      SELECT OID_CLIE,
             CLRE_OID_CLIE_REDO,
             COPA_OID_PARA_GRAL,
             CLR3_OID_CLIE_RETE,
             CLIE_OID_CLIE_RTE,
             NVL(MAX(IMP_MONT_PEDI),0) IMP_MONT_PEDI,
             NVL(MAX(NUM_UNID_PEDI),0) NUM_UNID_PEDI,
             MAX(SOCA_OID_SOLI_CABE) SOCA_OID_SOLI_CABE
        FROM (SELECT A.CLIE_OID_CLIE OID_CLIE,
                     G.VAL_MONT_TOTA IMP_MONT_PEDI,
                     G.VAL_UNID_TOTA NUM_UNID_PEDI,
                     D.OID_CLIE_REDO CLRE_OID_CLIE_REDO,
                     E.COPA_OID_PARA_GRAL,
                     OID_CLIE_RETE CLR3_OID_CLIE_RETE,
                     A.OID_SOLI_CABE SOCA_OID_SOLI_CABE,
                     E.CLIE_OID_CLIE CLIE_OID_CLIE_RTE              
                FROM PED_SOLIC_CABEC       A,
                     PED_TIPO_SOLIC_PAIS   B,
                     PED_TIPO_SOLIC        C,
                     INC_CLIEN_RECDO       D,
                     INC_CLIEN_RECTE       E,
                     INC_CONCU_PARAM_CONSU F,
                     PED_SOLIC_CABEC_ACUM2   G
               WHERE A.PERD_OID_PERI = OidPeriodo
                 AND A.TSPA_OID_TIPO_SOLI_PAIS = B.Oid_Tipo_Soli_Pais
                 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                 AND C.COD_TIPO_SOLI = 'SOC'
                 AND A.FEC_FACT IS NULL
                 AND GRPR_OID_GRUP_PROC IN (3,4)
                 AND A.CLIE_OID_CLIE = D.CLIE_OID_CLIE
                 AND A.CLIE_OID_CLIE   = G.CLIE_OID_CLIE(+)
                 AND A.PERD_OID_PERI = G.PERD_OID_PERI(+)                 
                 AND E.COPA_OID_PARA_GRAL = F.COPA_OID_PARA_GRAL
                 AND D.IND_EFEC IS NULL
                 AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(D.PERD_OID_PERI) BETWEEN
                     GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(A.PERD_OID_PERI),
                                        1 - F.NUM_MINI_PEDI_RECO) AND
                     FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(A.PERD_OID_PERI)
                 AND D.CLR3_OID_CLIE_RETE = E.OID_CLIE_RETE
                 AND E.COPA_OID_PARA_GRAL IN
                     (SELECT cpg.oid_para_gral oidconcurso
                        FROM inc_concu_param_gener cpg,
                             inc_concu_param_consu cpc
                       WHERE cpg.diri_oid_diri = 1 --es dirigido a la Consultora
                         AND cpg.bcal_oid_base_calc = 4 --  Valor para base Calculo de Recomendación
                         AND cpg.oid_para_gral = cpc.copa_oid_para_gral
                         AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(OidPeriodo) BETWEEN
                             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(nvl(cpg.perd_oid_peri_desd,
                                                                        0)) AND
                             GEN_FN_CALCU_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.perd_oid_peri_hast),
                                                nvl(cpc.num_mini_pedi_reco, 0) - 1)
                         AND cpg.ind_acti = 1)
              /*AND NOT EXISTS (SELECT CLRE_OID_CLIE_REDO
              FROM INC_PEDID_CONCU_RECOM PRECOM
              WHERE PRECOM.PERD_OID_PERI = OidPeriodo
              AND PRECOM.CLRE_OID_CLIE_REDO = D.OID_CLIE_REDO
              AND PRECOM.CLR3_OID_CLIE_RETE = D.CLR3_OID_CLIE_RETE
                )*/
              )
       GROUP BY OID_CLIE,
                CLRE_OID_CLIE_REDO,
                COPA_OID_PARA_GRAL,
                CLR3_OID_CLIE_RETE,
                CLIE_OID_CLIE_RTE;
  
    TYPE interfazRecomendantes IS RECORD(
      oidCliente      MAE_CLIEN.OID_CLIE%TYPE,
      oidRecomendado  INC_PEDID_CONCU_RECOM.CLRE_OID_CLIE_REDO%TYPE,
      oidConcurso     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      oidRecomendante INC_PEDID_CONCU_RECOM.CLR3_OID_CLIE_RETE%TYPE,
      oidClienteRdte  MAE_CLIEN.OID_CLIE%TYPE,
      montoPedido     PED_SOLIC_CABEC_ACUM2.VAL_MONT_TOTA%TYPE,
      unidadPedido    PED_SOLIC_CABEC.NUM_UNID_POR_ATEN_TOTA%TYPE,
      oidSolicitud    PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE);
  
    TYPE interfazRecomendantesTab IS TABLE OF interfazRecomendantes;
    interfazRecordN interfazRecomendantesTab;
  
    TYPE t_oidCliente IS TABLE OF MAE_CLIEN.OID_CLIE%TYPE;
    TYPE t_montoPedido IS TABLE OF PED_SOLIC_CABEC_ACUM2.VAL_MONT_TOTA%TYPE;
    TYPE t_unidadPedido IS TABLE OF PED_SOLIC_CABEC.NUM_UNID_POR_ATEN_TOTA%TYPE;
    TYPE t_oidRecomendado IS TABLE OF INC_PEDID_CONCU_RECOM.CLRE_OID_CLIE_REDO%TYPE;
    TYPE t_oidConcurso IS TABLE OF INC_PEDID_CONCU_RECOM.COPA_OID_PARA_GRAL%TYPE;
    TYPE t_oidRecomendante IS TABLE OF INC_PEDID_CONCU_RECOM.CLR3_OID_CLIE_RETE%TYPE;
    TYPE t_oidSolicitud IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
    TYPE t_oidClienteRdte IS TABLE OF MAE_CLIEN.OID_CLIE%TYPE;
    TYPE t_oidPediReco IS TABLE OF INC_PEDID_CONCU_RECOM.OID_PEDI_CONC_RECO%TYPE;
  
    v_oidCliente      t_oidCliente := t_oidCliente();
    v_montoPedido     t_montoPedido := t_montoPedido();
    v_unidadPedido    t_unidadPedido := t_unidadPedido();
    v_oidRecomendado  t_oidRecomendado := t_oidRecomendado();
    v_oidConcurso     t_oidConcurso := t_oidConcurso();
    v_oidRecomendante t_oidRecomendante := t_oidRecomendante();
    v_oidSolicitud    t_oidSolicitud := t_oidSolicitud();
  
    v_oidCliente2     t_oidCliente := t_oidCliente();
    v_montoPedido2    t_montoPedido := t_montoPedido();
    v_unidadPedido2   t_unidadPedido := t_unidadPedido();
    v_oidConcurso2    t_oidConcurso := t_oidConcurso();
    v_oidSolicitud2   t_oidSolicitud := t_oidSolicitud();
    v_oidClienteRdte2 t_oidClienteRdte := t_oidClienteRdte();
  
    v_oidCliente3   t_oidCliente := t_oidCliente();
    v_montoPedido3  t_montoPedido := t_montoPedido();
    v_unidadPedido3 t_unidadPedido := t_unidadPedido();
    v_oidConcurso3  t_oidConcurso := t_oidConcurso();
    v_oidSolicitud3 t_oidSolicitud := t_oidSolicitud();
  
    v_oidPediReco     t_oidPediReco := t_oidPediReco();
    v_montoPedidoUpd  t_montoPedido := t_montoPedido();
    v_unidadPedidoUpd t_unidadPedido := t_unidadPedido();
  
    lnTotalOcurrencias NUMBER;
    lsResult           VARCHAR2(1);
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos el periodo Anterior
    lsCodigoPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal,
                                                                 -1);
  
    --Obtenemos la Fecha de Proceso
    SELECT FEC_PROC
      INTO ldFechaProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --(1) PROCESAMOS A LAS RECOMENDANDOS
    OPEN c_Recomendandos(lnOidPeriodo);
    LOOP
      FETCH c_Recomendandos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lsResult      := '1';
          lnOidPediReco := NULL;
        
          --validar previamente unicidad en la tabla INC_PEDIC_CONCU_RECOM
          BEGIN
            SELECT PRECOM.OID_PEDI_CONC_RECO,
                   PRECOM.IMP_MONT_PEDI,
                   PRECOM.NUM_UNID_PEDI
              INTO lnOidPediReco, lnMontoPedido, lnUnidadPedido
              FROM INC_PEDID_CONCU_RECOM PRECOM
             WHERE PRECOM.PERD_OID_PERI = lnOidPeriodo
               AND PRECOM.CLRE_OID_CLIE_REDO = interfazRecordN(x)
                  .oidRecomendado
               AND PRECOM.CLR3_OID_CLIE_RETE = interfazRecordN(x)
                  .oidRecomendante;
          EXCEPTION
            WHEN OTHERS THEN
              NULL;
          END;
        
          IF (lnOidPediReco IS NULL) THEN
            --Insertamos en la tabla de Pedidos Recomendados
            /*v_oidCliente.EXTEND(1);
            v_montoPedido.EXTEND(1);
            v_unidadPedido.EXTEND(1);
            v_oidRecomendado.EXTEND(1);
            v_oidConcurso.EXTEND(1);
            v_oidRecomendante.EXTEND(1);
            v_oidSolicitud.EXTEND(1);
            
              v_oidCliente(v_oidCliente.COUNT) := interfazRecordN(x)
                                                  .oidCliente;
              v_montoPedido(v_montoPedido.COUNT) := interfazRecordN(x)
                                                    .montoPedido;
              v_unidadPedido(v_unidadPedido.COUNT) := interfazRecordN(x)
                                                      .unidadPedido;
              v_oidRecomendado(v_oidRecomendado.COUNT) := interfazRecordN(x)
                                                          .oidRecomendado;
              v_oidConcurso(v_oidConcurso.COUNT) := interfazRecordN(x)
                                                    .oidConcurso;
              v_oidRecomendante(v_oidRecomendante.COUNT) := interfazRecordN(x)
                                                            .oidRecomendante;
              v_oidSolicitud(v_oidSolicitud.COUNT) := interfazRecordN(x)
                                                      .oidSolicitud;*/
          
            INSERT INTO INC_PEDID_CONCU_RECOM
              (OID_PEDI_CONC_RECO,
               IMP_MONT_PEDI,
               NUM_UNID_PEDI,
               IND_PEDI_VALI,
               CLRE_OID_CLIE_REDO,
               PERD_OID_PERI,
               CLIE_OID_CLIE,
               COPA_OID_PARA_GRAL,
               CLR3_OID_CLIE_RETE,
               FEC_CREA)
            VALUES
              (INC_PECR_SEQ.NEXTVAL,
               interfazRecordN     (x).montoPedido,
               interfazRecordN     (x).unidadPedido,
               NULL,
               interfazRecordN     (x).oidRecomendado,
               lnOidPeriodo,
               NULL,
               interfazRecordN     (x).oidConcurso,
               interfazRecordN     (x).oidRecomendante,
               SYSDATE);
          ELSE
            --Actualizamos en la tabla de Pedidos Recomendados
            /*v_oidPediReco.EXTEND(1);
            v_montoPedidoUpd.EXTEND(1);
            v_unidadPedidoUpd.EXTEND(1);
            
            v_oidPediReco(v_oidPediReco.COUNT) := lnOidPediReco;
              v_montoPedidoUpd(v_montoPedidoUpd.COUNT) := lnMontoPedido + interfazRecordN(x)
                                                         .montoPedido;
              v_unidadPedidoUpd(v_unidadPedidoUpd.COUNT) := lnUnidadPedido + interfazRecordN(x)
                                                           .unidadPedido;*/
          
            UPDATE INC_PEDID_CONCU_RECOM
               SET IMP_MONT_PEDI = interfazRecordN(x)
                                  .montoPedido,
                   NUM_UNID_PEDI = interfazRecordN(x)
                                  .unidadPedido,
                   FEC_MODI = SYSDATE
             WHERE OID_PEDI_CONC_RECO = lnOidPediReco;
          END IF;
        
          --validar previamente unicidad en la tabla INC_SOLIC_CONCU_RECOM
          SELECT COUNT(1)
            INTO lnTotalOcurrencias
            FROM INC_SOLIC_CONCU_RECOM
           WHERE CLIE_OID_CLIE = interfazRecordN(x).oidClienteRdte
             AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
             AND SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
        
          IF (lnTotalOcurrencias = 0) THEN
            /*v_oidCliente2.EXTEND(1);
            v_montoPedido2.EXTEND(1);
            v_unidadPedido2.EXTEND(1);
            v_oidConcurso2.EXTEND(1);
            v_oidSolicitud2.EXTEND(1);
            v_oidClienteRdte2.EXTEND(1);
            
              v_oidCliente2(v_oidCliente2.COUNT) := interfazRecordN(x)
                                                    .oidCliente;
              v_montoPedido2(v_montoPedido2.COUNT) := interfazRecordN(x)
                                                      .montoPedido;
              v_unidadPedido2(v_unidadPedido2.COUNT) := interfazRecordN(x)
                                                        .unidadPedido;
              v_oidConcurso2(v_oidConcurso2.COUNT) := interfazRecordN(x)
                                                      .oidConcurso;
              v_oidSolicitud2(v_oidSolicitud2.COUNT) := interfazRecordN(x)
                                                        .oidSolicitud;
              v_oidClienteRdte2(v_oidClienteRdte2.COUNT) := interfazRecordN(x)
                                                            .oidClienteRdte;*/
          
            INSERT INTO INC_SOLIC_CONCU_RECOM
              (OID_SOLI_CONC_RECO,
               FEC_DOCU,
               IND_SOLI_VALI,
               IMP_MONT_SOLI,
               NUM_UNID_SOLI,
               IND_ANUL,
               COPA_OID_PARA_GRAL,
               SOCA_OID_SOLI_CABE,
               CLIE_OID_CLIE,
               PERD_OID_PERI,
               CLIE_OID_RECO_DADO,
               CLIE_OID_CLIE_GERE)
            VALUES
              (INC_SOCR_SEQ.NEXTVAL,
               ldFechaProceso,
               1,
               interfazRecordN     (x).montoPedido,
               interfazRecordN     (x).unidadPedido,
               NULL,
               interfazRecordN     (x).oidConcurso,
               interfazRecordN     (x).oidSolicitud,
               interfazRecordN     (x).oidClienteRdte,
               lnOidPeriodo,
               interfazRecordN     (x).oidCliente,
               NULL);
          END IF;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_Recomendandos%NOTFOUND;
    END LOOP;
    CLOSE c_Recomendandos;
  
  
    --Limpiamos los arreglos de Pedidos Recomendados y Solicitudes Recomendadas
    v_oidCliente.DELETE;
    v_montoPedido.DELETE;
    v_unidadPedido.DELETE;
    v_oidRecomendado.DELETE;
    v_oidConcurso.DELETE;
    v_oidRecomendante.DELETE;
    v_oidSolicitud.DELETE;
  
    v_oidCliente2.DELETE;
    v_montoPedido2.DELETE;
    v_unidadPedido2.DELETE;
    v_oidConcurso2.DELETE;
    v_oidSolicitud2.DELETE;
  
    v_oidPediReco.DELETE;
    v_montoPedidoUpd.DELETE;
    v_unidadPedidoUpd.DELETE;
  
    --(2) PROCESAMOS A LAS RECOMENDANTES
    OPEN c_Recomendantes(lnOidPeriodo);
    LOOP
      FETCH c_Recomendantes BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lsResult := '1';
        
          --VALIDACION AMBITO GEOGRAFICO
          SELECT COUNT(1)
            INTO lnTotalOcurrencias
            FROM INC_AMBIT_GEOGR geo
           WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso;
        
          IF (lnTotalOcurrencias > 0) THEN
            --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
            BEGIN
              lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(interfazRecordN(x)
                                                         .oidConcurso,
                                                         interfazRecordN(x)
                                                         .oidCliente);
            EXCEPTION
              WHEN OTHERS THEN
                lsResult := '0';
            END;
          END IF;
        
          IF (lsResult = '1') THEN
            --VALIDACION DEL TIPO DE PARTICIPANTE
            SELECT COUNT(1)
              INTO lnTotalOcurrencias
              FROM INC_CLASI_PARTI_CONCU par
             WHERE par.copa_oid_para_gral = interfazRecordN(x).oidConcurso;
          
            lsResult := '1';
            IF (lnTotalOcurrencias > 0) THEN
              lsResult := SUBSTR(INC_FN_VALID_CLASI_CONCU(interfazRecordN(x)
                                                          .oidConcurso,
                                                          interfazRecordN(x)
                                                          .oidCliente),
                                 1,
                                 1); --1:valid <>1:no valido
            END IF;
          
            IF (lsResult = '1') THEN
              --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
              SELECT COUNT(1)
                INTO lnTotalOcurrencias
                FROM INC_ESTAT_VENTA_CONSU
               WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso;
            
              lsResult := '1';
              IF (lnTotalOcurrencias > 0) THEN
                lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(interfazRecordN   (x)
                                                           .oidConcurso,
                                                           interfazRecordN   (x)
                                                           .oidCliente,
                                                           psCodigoPeriodo,
                                                           lsCodigoPeriodoAnt); --1:valid 0:no valido
              END IF;
            
              IF (lsResult = '1') THEN
                lnOidPediReco := NULL;
              
                --validar previamente unicidad en la tabla INC_PEDIC_CONCU_RECOM
                BEGIN
                  SELECT PRECOM.OID_PEDI_CONC_RECO,
                         PRECOM.IMP_MONT_PEDI,
                         PRECOM.NUM_UNID_PEDI
                    INTO lnOidPediReco, lnMontoPedido, lnUnidadPedido
                    FROM INC_PEDID_CONCU_RECOM PRECOM
                   WHERE PRECOM.PERD_OID_PERI = lnOidPeriodo
                     AND PRECOM.CLRE_OID_CLIE_REDO IS NULL
                     AND PRECOM.CLR3_OID_CLIE_RETE = interfazRecordN(x)
                        .oidRecomendante;
                EXCEPTION
                  WHEN OTHERS THEN
                    NULL;
                END;
              
                IF (lnOidPediReco IS NULL) THEN
                  --Insertamos en la tabla de Pedidos Recomendados
                
                  INSERT INTO INC_PEDID_CONCU_RECOM
                    (OID_PEDI_CONC_RECO,
                     IMP_MONT_PEDI,
                     NUM_UNID_PEDI,
                     IND_PEDI_VALI,
                     CLRE_OID_CLIE_REDO,
                     PERD_OID_PERI,
                     CLIE_OID_CLIE,
                     COPA_OID_PARA_GRAL,
                     CLR3_OID_CLIE_RETE,
                     FEC_CREA)
                  VALUES
                    (INC_PECR_SEQ.NEXTVAL,
                     interfazRecordN     (x).montoPedido,
                     interfazRecordN     (x).unidadPedido,
                     NULL,
                     interfazRecordN     (x).oidRecomendado,
                     lnOidPeriodo,
                     NULL,
                     interfazRecordN     (x).oidConcurso,
                     interfazRecordN     (x).oidRecomendante,
                     SYSDATE);
                
                ELSE               
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_PEDI = interfazRecordN(x)
                                        .montoPedido,
                         NUM_UNID_PEDI = interfazRecordN(x)
                                        .unidadPedido,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = lnOidPediReco;
                END IF;
              
                --validar previamente unicidad en la tabla INC_SOLIC_CONCU_RECOM
                SELECT COUNT(1)
                  INTO lnTotalOcurrencias
                  FROM INC_SOLIC_CONCU_RECOM
                 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
                   AND SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
              
                IF (lnTotalOcurrencias = 0) THEN
                
                  INSERT INTO INC_SOLIC_CONCU_RECOM
                    (OID_SOLI_CONC_RECO,
                     FEC_DOCU,
                     IND_SOLI_VALI,
                     IMP_MONT_SOLI,
                     NUM_UNID_SOLI,
                     IND_ANUL,
                     COPA_OID_PARA_GRAL,
                     SOCA_OID_SOLI_CABE,
                     CLIE_OID_CLIE,
                     PERD_OID_PERI,
                     CLIE_OID_RECO_DADO,
                     CLIE_OID_CLIE_GERE)
                  VALUES
                    (INC_SOCR_SEQ.NEXTVAL,
                     ldFechaProceso,
                     1,
                     interfazRecordN     (x).montoPedido,
                     interfazRecordN     (x).unidadPedido,
                     NULL,
                     interfazRecordN     (x).oidConcurso,
                     interfazRecordN     (x).oidSolicitud,
                     interfazRecordN     (x).oidCliente,
                     lnOidPeriodo,
                     NULL,
                     NULL);
                END IF;
              
                --validar previamente unicidad en la tabla INC_SOLIC_CONCU_PUNTA
                SELECT COUNT(1)
                  INTO lnTotalOcurrencias
                  FROM INC_SOLIC_CONCU_PUNTA
                 WHERE PERD_OID_PERI = lnOidPeriodo
                   AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
                   AND SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
              
                IF (lnTotalOcurrencias = 0) THEN
                
                  INSERT INTO INC_SOLIC_CONCU_PUNTA
                    (OID_SOLI_CONC_PUNT,
                     NUM_PUNT,
                     VAL_PUNT_BONI,
                     VAL_PUNT_FALT_NANU,
                     FEC_DOCU,
                     IND_ANUL,
                     COPA_OID_PARA_GRAL,
                     SOCA_OID_SOLI_CABE,
                     PERD_OID_PERI,
                     CLIE_OID_CLIE,
                     IMP_MONT,
                     CLIE_OID_CLIE_GERE,
                     NUM_UNID)
                  VALUES
                    (INC_SOCP_SEQ.NEXTVAL,
                     0,
                     0,
                     0,
                     ldFechaProceso,
                     0,
                     interfazRecordN     (x).oidConcurso,
                     interfazRecordN     (x).oidSolicitud,
                     lnOidPeriodo,
                     interfazRecordN     (x).oidCliente,
                     interfazRecordN     (x).montoPedido,
                     NULL,
                     interfazRecordN     (x).unidadPedido);
                END IF;
              END IF;
            END IF;
          END IF;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_Recomendantes%NOTFOUND;
    END LOOP;
    CLOSE c_Recomendantes;  
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_PEDID_RECOM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_GENER_PEDID_RECOM;

  /**************************************************************************
  Descripcion       : Valida si el Estatus Venta del Cliente
  
  Fecha Creacion    : 18/06/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
    pnOidCliente         :  oid Cliente
    psCodigoPeriodo      :  codigo Periodo
    psCodigoPeriodoAnt   :  codigo Periodo Anterior
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_ESTAT_VENTA_CONCU(pnOidConcurso      NUMBER,
                                          pnOidCliente       NUMBER,
                                          psCodigoPeriodo    VARCHAR2,
                                          psCodigoPeriodoAnt VARCHAR2)
    RETURN VARCHAR2 IS
  
    lnTotalOcurrencias NUMBER;
    lsCodigoPeriodoAnt SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoIni SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoFin SEG_PERIO_CORPO.COD_PERI%TYPE;
  BEGIN
  
    lnTotalOcurrencias := 0;
  
    FOR x IN (SELECT est.ESTA_OID_ESTA_CLIE,
                     est2.OID_ESTA_CLIE,
                     FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(est.PERD_OID_PERI_DESD) PERD_OID_PERI_DESD,
                     FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(est.PERD_OID_PERI_HAST) PERD_OID_PERI_HAST
                FROM INC_ESTAT_VENTA_CONSU est, MAE_ESTAT_CLIEN est2
               WHERE est.COPA_OID_PARA_GRAL = pnOidConcurso
                 AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI_HAST) <=
                     psCodigoPeriodo
                 AND est.ESTA_OID_ESTA_CLIE = est2.ESTA_OID_ESTA_CLIE(+)) LOOP
    
      IF (x.PERD_OID_PERI_HAST < psCodigoPeriodo) THEN
        lsCodigoPeriodoAnt := x.PERD_OID_PERI_HAST;
      ELSE
        lsCodigoPeriodoAnt := psCodigoPeriodoAnt;
      END IF;
    
      --SE EVALUA EL RANGO DEL ESTATUS VENTA: PERIODO INICIO - PERIODO HASTA o PERIODO ANTERIOR HASTA
      -- (esto depende si el periodo Hasta es igual a Periodo Proceso)
      IF (x.PERD_OID_PERI_DESD < psCodigoPeriodo) THEN
        BEGIN
          --Buscamos en los periodos anterioroes, el estatus de ventas del concurso
        
          BEGIN
            SELECT FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI)
              INTO lsCodigoPeriodoIni
              FROM MAE_CLIEN_HISTO_ESTAT his
             WHERE his.CLIE_OID_CLIE = pnOidCliente
               AND ((FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <=
                   x.PERD_OID_PERI_DESD AND
                   x.PERD_OID_PERI_DESD <=
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI_PERI_FIN)) OR
                   (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <=
                   x.PERD_OID_PERI_DESD AND
                   his.PERD_OID_PERI_PERI_FIN IS NULL));
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lsCodigoPeriodoIni := x.PERD_OID_PERI_DESD;
          END;
        
          SELECT FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI)
            INTO lsCodigoPeriodoFin
            FROM MAE_CLIEN_HISTO_ESTAT his
           WHERE his.CLIE_OID_CLIE = pnOidCliente
             AND ((FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <=
                 lsCodigoPeriodoAnt AND
                 lsCodigoPeriodoAnt <=
                 FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI_PERI_FIN)) OR
                 (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <=
                 lsCodigoPeriodoAnt AND
                 his.PERD_OID_PERI_PERI_FIN IS NULL));
        
          SELECT COUNT(1)
            INTO lnTotalOcurrencias
            FROM MAE_CLIEN_HISTO_ESTAT his
           WHERE his.CLIE_OID_CLIE = pnOidCliente
             AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.Perd_Oid_Peri) >=
                 lsCodigoPeriodoIni
             AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.Perd_Oid_Peri) <=
                 lsCodigoPeriodoFin
             AND his.ESTA_OID_ESTA_CLIE = x.ESTA_OID_ESTA_CLIE;
        
        EXCEPTION
          WHEN OTHERS THEN
            lnTotalOcurrencias := 0;
        END;
      END IF;
    
      --SE EVALUA EL RANGO DEL ESTATUS VENTA: PERIODO PROCESO, SIEMPRE Y CUANDO PERIODO HASTA = PERIODO PROCESO
      IF ((lnTotalOcurrencias = 0) AND
         (x.PERD_OID_PERI_HAST = psCodigoPeriodo)) THEN
        --Buscamos en el periodo anterior, el estatus inferido
        SELECT COUNT(1)
          INTO lnTotalOcurrencias
          FROM MAE_CLIEN_HISTO_ESTAT his
         WHERE his.CLIE_OID_CLIE = pnOidCliente
           AND ((FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <
               psCodigoPeriodoAnt AND his.PERD_OID_PERI_PERI_FIN IS NULL) OR
               (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) =
               psCodigoPeriodoAnt) OR (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI_PERI_FIN) =
               psCodigoPeriodoAnt))
           AND his.ESTA_OID_ESTA_CLIE = x.OID_ESTA_CLIE;
      
        --En caso de Nuevas buscamos en el periodo proceso
        IF ((lnTotalOcurrencias = 0) AND (x.ESTA_OID_ESTA_CLIE = 2)) THEN
          SELECT COUNT(1)
            INTO lnTotalOcurrencias
            FROM MAE_CLIEN_HISTO_ESTAT his
           WHERE his.CLIE_OID_CLIE = pnOidCliente
             AND ((FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) <
                 psCodigoPeriodo AND his.PERD_OID_PERI_PERI_FIN IS NULL) OR
                 (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(his.PERD_OID_PERI) =
                 psCodigoPeriodo))
             AND his.ESTA_OID_ESTA_CLIE = x.OID_ESTA_CLIE;
        END IF;
      
      END IF;
    
      IF (lnTotalOcurrencias > 0) THEN
        RETURN '1';
      END IF;
    
    END LOOP;
  
    RETURN '0';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_ESTAT_VENTA_CONCU: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_ESTAT_VENTA_CONCU;

  /**************************************************************************
  Descripcion       : Este proceso consiste en que cuando se reciba una solicitud
                      anulación, el sistema debe anular o revertir todas las operaciones de
                      incentivos realizadas con ellaenerar pedidos de recomendación
  
  Fecha Creacion    : 02/08/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidSolicitud     :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_TRATA_ANULA_SOLIC(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2) IS
    lnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal SEG_CANAL.OID_CANA%TYPE;
  
    lnOidSoliRefe      PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE%TYPE;
    ldOidSoliConso     PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE%TYPE;
    lnOidCliente       PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE;
    lnOidPeriodo       PED_SOLIC_CABEC.PERD_OID_PERI%TYPE;
    ldFechaFacturacion PED_SOLIC_CABEC.FEC_FACT%TYPE;
    lnNumeroSolicitud  PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  
    lnOidPediRecom INC_PEDID_CONCU_RECOM.OID_PEDI_CONC_RECO%TYPE;
    lnMontoPedido  INC_PEDID_CONCU_RECOM.IMP_MONT_PEDI%TYPE;
    lnUnidPedido   INC_PEDID_CONCU_RECOM.NUM_UNID_PEDI%TYPE;
  
    lnNumPuntaje     INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;
    lnNumPuntajeExig INC_CUENT_CORRI_PUNTO.NUM_PUNT_EXIG%TYPE;
  
    lnOidClienRete INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidClienRcdo INC_CLIEN_RECDO.OID_CLIE_REDO%TYPE;
  
    lsCodPeriodo     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodPeriodoAnt  SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodPeriodoAnul SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lnOidSecuencia         NUMBER;
    lnOcurrencias          NUMBER;
    lbAnulada              BOOLEAN;
    lbTipoSoliSocProcesada BOOLEAN := FALSE;
    lnPuntosRevertir       INC_PROGR_CONST_MOVIM.NUM_PUNT%TYPE;
  
  BEGIN
  
    --Ubicamos el oid consolidado de referencia, cliente, campaña, oid Consolidado, fecha Facturacion
    SELECT SOCA_OID_DOCU_REFE,
           CLIE_OID_CLIE,
           PERD_OID_PERI,
           SOCA_OID_SOLI_CABE,
           FEC_FACT
      INTO lnOidSoliRefe,
           lnOidCliente,
           lnOidPeriodo,
           ldOidSoliConso,
           ldFechaFacturacion
      FROM PED_SOLIC_CABEC
     WHERE OID_SOLI_CABE = pnOidSolicitud;
  
    --Ubicamos el Numero de Solicitud del Consolidado de Anulacion
    SELECT VAL_NUME_SOLI
      INTO lnNumeroSolicitud
      FROM PED_SOLIC_CABEC
     WHERE OID_SOLI_CABE = ldOidSoliConso;
  
    --Recuperamos el Periodo Anterior
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
    lsCodPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo);
  
    --Obtenemos el periodo Anterior
    lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriodo,
                                                              lnOidPais,
                                                              lnOidMarca,
                                                              lnOidCanal,
                                                              -1);
  
    --Se recuperan todas las solicitudes asociadas al consolidado  de Referencia
    FOR x IN (SELECT PSC.OID_SOLI_CABE,
                     PSC.CLIE_OID_CLIE,
                     PSC.FEC_FACT,
                     TSO.COD_TIPO_SOLI,
                     PSC.PERD_OID_PERI
                FROM PED_SOLIC_CABEC     PSC,
                     PED_TIPO_SOLIC_PAIS TSP,
                     PED_TIPO_SOLIC      TSO
               WHERE PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                 AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
                 AND PSC.SOCA_OID_SOLI_CABE = lnOidSoliRefe) LOOP
    
      --Para cada solicitud recuperan los registros asociados de la tabla INC_SOLIC_CONCU_PUNTA
      FOR y IN (SELECT OID_SOLI_CONC_PUNT,
                       COPA_OID_PARA_GRAL,
                       NUM_PUNT,
                       NVL(NUM_PUNT_EXIG, 0) NUM_PUNT_EXIG,
                       PERD_OID_PERI,
                       INC_FN_VALID_CONCU_VENTA_ANUL(COPA_OID_PARA_GRAL,
                                                     lnOidPeriodo) IND_VALI,
                       VAL_PUNT_BONI
                  FROM INC_SOLIC_CONCU_PUNTA
                 WHERE SOCA_OID_SOLI_CABE = x.OID_SOLI_CABE
                   AND CLIE_OID_CLIE = lnOidCliente
                   AND IND_ANUL <> 1) LOOP
      
        --Se verifica si el concurso es valido para Tipo Venta o Monto con indicador de Anulacion
        IF (y.IND_VALI = 1) THEN
        
          lnOidSecuencia   := INC_CUCP_SEQ.NEXTVAL;
          lsCodPeriodoAnul := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(y.PERD_OID_PERI);
        
          INSERT INTO INC_CUENT_CORRI_PUNTO
            (OID_CUEN_CORR_PUNT,
             NUM_MOVI,
             NUM_PUNT,
             NUM_PUNT_EXIG,
             FEC_MOVI,
             COPA_OID_PARA_GRAL,
             CLIE_OID_CLIE,
             PERD_OID_PERI,
             TMOV_OID_TIPO_MOVI,
             FEC_ULTI_ACTU,
             VAL_DESC,
             USU_MODI,
             PERD_OID_PERI_REFE,
             NUM_PUNT_BONI,
             DES_MOTI)
          VALUES
            (lnOidSecuencia,
             lnOidSecuencia,
             y.NUM_PUNT * (-1),
             y.NUM_PUNT_EXIG * (-1),
             ldFechaFacturacion,
             y.COPA_OID_PARA_GRAL,
             lnOidCliente,
             lnOidPeriodo,
             2,
             SYSDATE,
             'Cargo Anulación periodo ' || lsCodPeriodoAnul || ' (REC)',
             psCodigoUsuario,
             y.PERD_OID_PERI,
             y.VAL_PUNT_BONI,
             'Anulación');
        
          UPDATE INC_SOLIC_CONCU_PUNTA
             SET IND_ANUL = 1
           WHERE OID_SOLI_CONC_PUNT = y.OID_SOLI_CONC_PUNT;
        
          /* Se comenta porque la anulación de por si libera al premio para su siguiente despacho.
                  --Se verifica si el premio ya fue despachado
                  SELECT COUNT(1)
                    INTO lnOcurrencias
                    FROM INC_GANAD gan, INC_PARAM_NIVEL_PREMI niv, INC_PARAM_GENER_PREMI pre, PED_SOLIC_CABEC cab
                   WHERE gan.CLIE_OID_CLIE = lnOidCliente
                     AND gan.PERD_OID_PERI = lnOidPeriodo
                     AND gan.PANP_OID_PARA_NIVE_PREM = niv.OID_PARA_NIVE_PREM
                     AND niv.PAGP_OID_PARA_GENE_PREM = pre.OID_PARA_GENE_PREM
                     AND pre.COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                     AND gan.SOCA_OID_SOLI_CABE = cab.OID_SOLI_CABE
                     AND cab.CLIE_OID_CLIE = lnOidCliente
                     AND cab.PERD_OID_PERI = lnOidPeriodo
                     AND cab.SOCA_OID_SOLI_CABE = lnOidSoliRefe;
          
                  IF(lnOcurrencias > 0) THEN
                    UPDATE INC_CANDI_GANAD
                       SET VAL_REQU_PREM_SUPE = 0,
                           BINC_OID_BASE_INCU = NULL
                     WHERE CLIE_OID_CLIE = lnOidCliente
                       AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL;
          
                    SELECT NUM_PUNT,
                           NUM_PUNT_EXIG
                      INTO lnNumPuntaje,
                           lnNumPuntajeExig
                      FROM INC_CUENT_CORRI_PUNTO
                     WHERE CLIE_OID_CLIE = lnOidCliente
                       AND PERD_OID_PERI = lnOidPeriodo
                       AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                       AND TMOV_OID_TIPO_MOVI = 2
                       AND VAL_DESC = 'Entrega de Premio';
          
                    lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                    lsCodPeriodoAnul := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(y.PERD_OID_PERI);
          
                    INSERT INTO INC_CUENT_CORRI_PUNTO
                     (OID_CUEN_CORR_PUNT,
                      NUM_MOVI,
                      NUM_PUNT,
                      NUM_PUNT_EXIG,
                      FEC_MOVI,
                      COPA_OID_PARA_GRAL,
                      CLIE_OID_CLIE,
                      PERD_OID_PERI,
                      TMOV_OID_TIPO_MOVI,
                      FEC_ULTI_ACTU,
                      VAL_DESC,
                      USU_MODI)
                    VALUES
                     (lnOidSecuencia,
                      lnOidSecuencia,
                      lnNumPuntaje * (-1),
                      lnNumPuntajeExig * (-1),
                      ldFechaFacturacion,
                      y.COPA_OID_PARA_GRAL,
                      lnOidCliente,
                      lnOidPeriodo,
                      1,
                      SYSDATE,
                      'Reversión Entrega de Premio Anulacion N. ' || TO_CHAR(lnNumeroSolicitud),
                      psCodigoUsuario);
                  END IF;
          */
        
        END IF;
      
      END LOOP;
    
      UPDATE inc_bolsa_falta
         SET FEC_SOLU = SYSDATE,
             VAL_OBSE = 'Cerrado por factura origen anulada'
       WHERE oid_bols_falt IN (SELECT oid_bols_falt
                                 FROM inc_bolsa_falta a, ped_solic_posic b
                                WHERE a.sopo_oid_soli_posi = b.oid_soli_posi
                                  AND b.soca_oid_soli_cabe = x.OID_SOLI_CABE
                                  AND a.fec_solu IS NULL);
    
      --Para cada solicitud recuperan los registros asociados de la tabla INC_SOLIC_CONCU_PUNTA
      FOR y IN (SELECT OID_SOLI_CONC_RECO,
                       COPA_OID_PARA_GRAL,
                       PERD_OID_PERI,
                       (CASE
                         WHEN (CLIE_OID_RECO_DADO IS NULL) THEN
                          1
                         ELSE
                          0
                       END) IND_PEDI_RCTE,
                       INC_FN_VALID_CONCU_RECOM_ANUL(COPA_OID_PARA_GRAL) IND_VALI,
                       IMP_MONT_SOLI,
                       NUM_UNID_SOLI
                  FROM INC_SOLIC_CONCU_RECOM
                 WHERE SOCA_OID_SOLI_CABE = x.OID_SOLI_CABE
                   AND ((CLIE_OID_CLIE = lnOidCliente AND
                       CLIE_OID_RECO_DADO IS NULL) OR
                       (CLIE_OID_RECO_DADO = lnOidCliente))
                   AND NVL(IND_ANUL, 0) <> 1) LOOP
      
        --Se verifica si el concurso es valido para Tipo Recomendacion con indicador de Anulacion
        IF (y.IND_VALI = 1) THEN
        
          lbAnulada := FALSE;
        
          --Si es pedido de Recomendante
          IF (y.IND_PEDI_RCTE = 1) THEN
          
            --Buscar en la tabla INC_CLIEN_RECTE, el oid de recomendante para el concurso y consultoras tratados (Oid_clie_rete).
            SELECT OID_CLIE_RETE
              INTO lnOidClienRete
              FROM INC_CLIEN_RECTE
             WHERE CLIE_OID_CLIE = lnOidCliente
               AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL;
          
            --Buscamos en la tabla INC_CLIEN_RECDO, si hay alguna recomendada afectada
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_CLIEN_RECDO
             WHERE CLR3_OID_CLIE_RETE = lnOidClienRete
               AND (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI) >=
                   lsCodPeriodoAnt)
               AND IND_EFEC IN (1, 0);
          
            IF (lnOcurrencias = 0) THEN
              BEGIN
                SELECT OID_PEDI_CONC_RECO, IMP_MONT_PEDI, NUM_UNID_PEDI
                  INTO lnOidPediRecom, lnMontoPedido, lnUnidPedido
                  FROM INC_PEDID_CONCU_RECOM
                 WHERE CLR3_OID_CLIE_RETE = lnOidClienRete
                   AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                   AND PERD_OID_PERI = y.PERD_OID_PERI
                   AND CLRE_OID_CLIE_REDO IS NULL;
              
                lnMontoPedido := lnMontoPedido - y.IMP_MONT_SOLI;
                lnUnidPedido  := lnUnidPedido - y.NUM_UNID_SOLI;
              
                IF ((lnMontoPedido = 0) AND (lnUnidPedido = 0)) THEN
                  --Se elimina el Registro de INC_PEDID_CONCU_RECOM
                  DELETE FROM INC_PEDID_CONCU_RECOM
                   WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
                ELSE
                  --Se actualiza el Registro de INC_PEDID_CONCU_RECOM
                  UPDATE INC_PEDID_CONCU_RECOM
                     SET IMP_MONT_PEDI = lnMontoPedido,
                         NUM_UNID_PEDI = lnUnidPedido,
                         USU_MODI      = psCodigoUsuario,
                         FEC_MODI      = SYSDATE
                   WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
                END IF;
              
                UPDATE INC_SOLIC_CONCU_RECOM
                   SET IND_SOLI_VALI = 0, IND_ANUL = 1
                 WHERE OID_SOLI_CONC_RECO = y.OID_SOLI_CONC_RECO;
              
                lbAnulada := TRUE;
              
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  NULL;
              END;
            END IF;
          
          ELSE
            --En caso de Ser Recomendada
            BEGIN
              SELECT rdo.OID_CLIE_REDO
                INTO lnOidClienRcdo
                FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
               WHERE rdo.CLIE_OID_CLIE = lnOidCliente
                 AND rdo.CLR3_OID_CLIE_RETE = rte.OID_CLIE_RETE
                 AND rte.COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                 AND rdo.IND_EFEC IS NULL;
            
              SELECT OID_PEDI_CONC_RECO, IMP_MONT_PEDI, NUM_UNID_PEDI
                INTO lnOidPediRecom, lnMontoPedido, lnUnidPedido
                FROM INC_PEDID_CONCU_RECOM
               WHERE CLRE_OID_CLIE_REDO = lnOidClienRcdo
                 AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                 AND PERD_OID_PERI = y.PERD_OID_PERI;
            
              lnMontoPedido := lnMontoPedido - y.IMP_MONT_SOLI;
              lnUnidPedido  := lnUnidPedido - y.NUM_UNID_SOLI;
            
              IF ((lnMontoPedido = 0) AND (lnUnidPedido = 0)) THEN
                --Se elimina el Registro de INC_PEDID_CONCU_RECOM
                DELETE FROM INC_PEDID_CONCU_RECOM
                 WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
              ELSE
                --Se actualiza el Registro de INC_PEDID_CONCU_RECOM
                UPDATE INC_PEDID_CONCU_RECOM
                   SET IMP_MONT_PEDI = lnMontoPedido,
                       NUM_UNID_PEDI = lnUnidPedido,
                       USU_MODI      = psCodigoUsuario,
                       FEC_MODI      = SYSDATE                       
                 WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
              END IF;
            
              UPDATE INC_SOLIC_CONCU_RECOM
                 SET IND_SOLI_VALI = 0, IND_ANUL = 1
               WHERE OID_SOLI_CONC_RECO = y.OID_SOLI_CONC_RECO;
            
              lbAnulada := TRUE;
            
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                NULL;
            END;
          
          END IF;
        
          /*
                  IF(lbAnulada) THEN
                    --Se verifica si el premio ya fue despachado
                    SELECT COUNT(1)
                      INTO lnOcurrencias
                      FROM INC_GANAD gan, INC_PARAM_NIVEL_PREMI niv, INC_PARAM_GENER_PREMI pre, PED_SOLIC_CABEC cab
                     WHERE gan.CLIE_OID_CLIE = lnOidCliente
                       AND gan.PERD_OID_PERI = lnOidPeriodo
                       AND gan.PANP_OID_PARA_NIVE_PREM = niv.OID_PARA_NIVE_PREM
                       AND niv.PAGP_OID_PARA_GENE_PREM = pre.OID_PARA_GENE_PREM
                       AND pre.COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                       AND gan.SOCA_OID_SOLI_CABE = cab.OID_SOLI_CABE
                       AND cab.CLIE_OID_CLIE = lnOidCliente
                       AND cab.PERD_OID_PERI = lnOidPeriodo
                       AND cab.SOCA_OID_SOLI_CABE = lnOidSoliRefe;
          
                    IF(lnOcurrencias > 0) THEN
                      UPDATE INC_CANDI_GANAD
                         SET VAL_REQU_PREM_SUPE = 0,
                             BINC_OID_BASE_INCU = NULL
                       WHERE CLIE_OID_CLIE = lnOidCliente
                         AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL;
          
                      SELECT NUM_PUNT,
                             NUM_PUNT_EXIG
                        INTO lnNumPuntaje,
                             lnNumPuntajeExig
                        FROM INC_CUENT_CORRI_PUNTO
                       WHERE CLIE_OID_CLIE = lnOidCliente
                         AND PERD_OID_PERI = lnOidPeriodo
                         AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                         AND TMOV_OID_TIPO_MOVI = 2
                         AND VAL_DESC = 'Entrega de Premio';
          
                      lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                      lsCodPeriodoAnul := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(y.PERD_OID_PERI);
          
                      INSERT INTO INC_CUENT_CORRI_PUNTO
                       (OID_CUEN_CORR_PUNT,
                        NUM_MOVI,
                        NUM_PUNT,
                        NUM_PUNT_EXIG,
                        FEC_MOVI,
                        COPA_OID_PARA_GRAL,
                        CLIE_OID_CLIE,
                        PERD_OID_PERI,
                        TMOV_OID_TIPO_MOVI,
                        FEC_ULTI_ACTU,
                        VAL_DESC,
                        USU_MODI)
                      VALUES
                       (lnOidSecuencia,
                        lnOidSecuencia,
                        lnNumPuntaje * (-1),
                        lnNumPuntajeExig * (-1),
                        ldFechaFacturacion,
                        y.COPA_OID_PARA_GRAL,
                        lnOidCliente,
                        lnOidPeriodo,
                        1,
                        SYSDATE,
                        'Reversión Entrega de Premio Anulacion N. ' || TO_CHAR(lnNumeroSolicitud),
                        psCodigoUsuario);
                    END IF;
          
                  END IF;
          */
        END IF;
      
      END LOOP;
    
      -- Revertir los puntajes de los programas de constancia --
      IF x.COD_TIPO_SOLI = 'SOC' AND NOT lbTipoSoliSocProcesada THEN
      
        FOR pc IN (SELECT IPC.COD_PROG_CONS,
                          IPC.COD_PROG_PUNT,
                          CPG.OID_PARA_GRAL
                     FROM INC_PROGR_CONST IPC, INC_CONCU_PARAM_GENER CPG
                    WHERE IPC.COD_PROG_PUNT = CPG.NUM_CONC
                      AND IPC.IND_ACTI = '1'
                      AND CPG.IND_ACTI = '1') LOOP
        
          BEGIN
          
            SELECT SUM(PCM.NUM_PUNT)
              INTO lnPuntosRevertir
              FROM INC_PROGR_CONST_MOVIM PCM
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND TRUNC(PCM.FEC_PROC) = TRUNC(X.FEC_FACT)
               AND PCM.EST_REG = 0;
          
            UPDATE INC_PROGR_CONST_MOVIM PCM
               SET PCM.EST_REG = 9
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND TRUNC(PCM.FEC_PROC) = TRUNC(x.FEC_FACT)
               AND PCM.EST_REG = 0;
          
            SELECT COUNT(*)
              INTO lnOcurrencias
              FROM INC_PROGR_CONST_MOVIM PCM
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.EST_REG = 0;
          
            IF lnOcurrencias = 0 AND lnPuntosRevertir > 0 THEN
            
              INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG,
                 IND_APOR_PROG_PUNT,
                 DES_MOTI,
                 CANA_ORIG)
              VALUES
                (INC_CUCP_SEQ.NEXTVAL,
                 INC_CUCP_SEQ.NEXTVAL,
                 -1 * lnPuntosRevertir,
                 0,
                 TO_DATE(ldFechaFacturacion, 'dd/mm/yyyy'),
                 pc.OID_PARA_GRAL,
                 lnOidCliente,
                 lnOidPeriodo,
                 2,
                 SYSDATE,
                 'Reversión Programa de constancia por Anulación pedido campaña ' ||
                 FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI),
                 psCodigoUsuario,
                 x.PERD_OID_PERI,
                 NULL,
                 NULL,
                 1,
                 'Nro de Pedidos Consecutivos',
                 NULL);
            
            END IF;
          
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnPuntosRevertir := 0;
          END;
              
          BEGIN
          
            SELECT SUM(PCM.NUM_PUNT)
              INTO lnPuntosRevertir
              FROM INC_PROGR_CONST_MOVNV PCM
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND TRUNC(PCM.FEC_PROC) = TRUNC(X.FEC_FACT)
               AND PCM.EST_REG = 0;
          
            UPDATE INC_PROGR_CONST_MOVNV PCM
               SET PCM.EST_REG = 9
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND TRUNC(PCM.FEC_PROC) = TRUNC(x.FEC_FACT)
               AND PCM.EST_REG = 0;
          
            SELECT COUNT(*)
              INTO lnOcurrencias
              FROM INC_PROGR_CONST_MOVNV PCM
             WHERE PCM.COD_PROG_CONS = pc.COD_PROG_CONS
               AND PCM.NUM_CONC = pc.COD_PROG_PUNT
               AND PCM.CAM_PROC =
                   FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI)
               AND PCM.COD_CLIE =
                   (SELECT MC.COD_CLIE
                      FROM MAE_CLIEN MC
                     WHERE MC.OID_CLIE = x.CLIE_OID_CLIE)
               AND PCM.EST_REG = 0;
          
            IF lnOcurrencias = 0 AND lnPuntosRevertir > 0 THEN
            
              INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG,
                 IND_APOR_PROG_PUNT,
                 DES_MOTI,
                 CANA_ORIG)
              VALUES
                (INC_CUCP_SEQ.NEXTVAL,
                 INC_CUCP_SEQ.NEXTVAL,
                 -1 * lnPuntosRevertir,
                 0,
                 TO_DATE(ldFechaFacturacion, 'dd/mm/yyyy'),
                 pc.OID_PARA_GRAL,
                 lnOidCliente,
                 lnOidPeriodo,
                 2,
                 SYSDATE,
                 'Reversión Programa de constancia por Anulación pedido campaña ' ||
                 FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI),
                 psCodigoUsuario,
                 x.PERD_OID_PERI,
                 NULL,
                 NULL,
                 1,
                 'Constancia de Nuevas',
                 NULL);
            
            END IF;
          
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnPuntosRevertir := 0;
          END;
                        
        END LOOP;
      
        lbTipoSoliSocProcesada := TRUE;
      
      END IF;
      -- --
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
    
  END INC_PR_TRATA_ANULA_SOLIC;

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Venta es de Tipo Anulacion
  
  Fecha Creacion    : 03/08/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
    pnOidPeriodo         :  oid Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_VENTA_ANUL(pnOidConcurso NUMBER,
                                         pnOidPeriodo  NUMBER) RETURN NUMBER IS
  
    lnTotalOcurrencias NUMBER;
    lsCodigoPeriodoAnt SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoIni SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoFin SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lnResultado NUMBER;
  BEGIN
    lnResultado := 0;
  
    SELECT COUNT(1)
      INTO lnResultado
      FROM INC_CONCU_PARAM_GENER con, INC_PARAM_GENER_PREMI pre
     WHERE con.OID_PARA_GRAL = pnOidConcurso
       AND con.IND_ANUL = 1
       AND con.BCAL_OID_BASE_CALC IN (1, 2)
       AND con.OID_PARA_GRAL = pre.COPA_OID_PARA_GRAL
       AND (pre.PERD_OID_PERI IS NULL OR
           (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(pnOidPeriodo) <=
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(pre.PERD_OID_PERI)));
  
    RETURN lnResultado;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CONCU_VENTA_ANUL: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_CONCU_VENTA_ANUL;

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Recomendacion es de Tipo Anulacion
  
  Fecha Creacion    : 03/08/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_RECOM_ANUL(pnOidConcurso NUMBER) RETURN NUMBER IS
  
    lnResultado NUMBER;
  BEGIN
    lnResultado := 0;
  
    SELECT COUNT(1)
      INTO lnResultado
      FROM INC_CONCU_PARAM_GENER con
     WHERE con.OID_PARA_GRAL = pnOidConcurso
       AND con.IND_ANUL = 1
       AND con.BCAL_OID_BASE_CALC = 4;
  
    RETURN lnResultado;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CONCU_RECOM_ANUL: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_CONCU_RECOM_ANUL;

  /**************************************************************************
  Descripcion       : Este proceso consiste en que cuando se reciba una solicitud
                      anulación, el sistema debe anular o revertir todas las operaciones de
                      incentivos realizadas con ellaenerar pedidos de recomendación
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidSolicitud     :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_CONSU(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2) IS
    lnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal SEG_CANAL.OID_CANA%TYPE;
  
    lnOidCliente       PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE;
    lnOidPeriodo       PED_SOLIC_CABEC.PERD_OID_PERI%TYPE;
    lnOidperiodorefe   PED_SOLIC_CABEC.PERD_OID_PERI%TYPE;
    ldFechaFacturacion PED_SOLIC_CABEC.FEC_FACT%TYPE;
    lnOidSoliRefe      PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE%TYPE;
    lsCodTipoSoli      PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE;
    lnNumPuntajeDevo   INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;
  
    lsCodPeriodo     SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodPeriodoAnt  SEG_PERIO_CORPO.COD_PERI%TYPE;
    ldcampanaproceso SEG_PERIO_CORPO.COD_PERI%TYPE;
  
    lnOidBaseCalc     INC_CONCU_PARAM_GENER.BCAL_OID_BASE_CALC%TYPE;
    lsNombreConcurso  INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE;
    lsNumeroConcurso  INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
    lsCodPeriodoDesde SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodPeriodoHasta SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnIndDuplaCyzone  INC_CONCU_PARAM_GENER.IND_DUPL_CYZO%TYPE;
    lnFactConversion  INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
    lnPuntosAsignar   INC_OBTEN_PUNTO.NUM_PUNT_ASIG%TYPE;
    lnIndComuObte     INC_OBTEN_PUNTO.IND_COMU_OBTE%TYPE;
    lnOidMensaje      INC_OBTEN_PUNTO.MENS_OID_MENS%TYPE;
    lsTextoRanking    INC_OBTEN_PUNTO.VAL_TEXT_RANK%TYPE;
  
    lsTipoClasiConcu INC_CLASI_CONCU.COD_CLAS_CONC%TYPE;
    lnImporte        NUMBER;
    lnUnidades       NUMBER;
  
    lnPuntajePosicion NUMBER;
    lnPuntajePosBonif NUMBER;
    lnPuntajeTotal    NUMBER;
    lnPuntajeTotalBon NUMBER;
    lnPuntajeTotalExi NUMBER;
  
    lnTotalProdVal NUMBER;
    lnTotalProdExc NUMBER;
    lnTotalProdBon NUMBER;
  
    lnTotalProdExi      NUMBER;
    lnTotalProdExiOblig NUMBER;
    lnTotalOblig        NUMBER;
    lnHayMensaje        NUMBER;
    lnOcurrencias2      NUMBER;
  
    lnOidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  
    lnOidSecuencia NUMBER;
    lnOcurrencias  NUMBER;
    lsResult       VARCHAR2(200);
    lsIndReemplazo BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lbValPosicion  BOOLEAN;
    lnIndTieneMeta NUMBER;
  /*
    lnPtjeTotalMsg      NUMBER;
    lnPtjeDevAnulMsg    NUMBER;
    lnPtjeOtrosCanMsg   NUMBER;
    lnPtjeUtilizadosMsg NUMBER;
    lnSaldoAnteriorMsg  NUMBER;
  */
    lnMontoMinConcu  INC_REQUI_PREMI.VAL_MONT_MINI_CONC%TYPE;
    lnMetaImporte    INC_METAS_TIPO_VENTA.IMP_MONTO_VENTA%TYPE;
    lnMetaUnidades   INC_METAS_TIPO_VENTA.NUM_UNID_VEND%TYPE;
    lnMetaIncremento INC_METAS_TIPO_VENTA.VAL_INCR%TYPE;
    lnMetaVenta      INC_METAS_TIPO_VENTA.VAL_META%TYPE;
  
    lnPuntajeSup      NUMBER;
    lsDescNivel       INC_PARAM_NIVEL_PREMI.VAL_DESC%TYPE;
    lnNumeroRanking   INC_RANKI_RECON.NUM_RANK%TYPE;
    lnIndTieneRanking NUMBER;
    lsTextoRankingAux VARCHAR2(200);
  
    lnPuntosMetaFal  NUMBER;
    lnIncrementoReal NUMBER;
    lnUltevaluacion  NUMBER;
  
    lnOidClienRete  INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE;
    lnOidClienRcdo  INC_CLIEN_RECDO.OID_CLIE_REDO%TYPE;
    lnOidPediRecom  INC_PEDID_CONCU_RECOM.OID_PEDI_CONC_RECO%TYPE;
    lnMontoPedido   INC_PEDID_CONCU_RECOM.IMP_MONT_PEDI%TYPE;
    lnUnidPedido    INC_PEDID_CONCU_RECOM.NUM_UNID_PEDI%TYPE;
    lnMontoDevuelto INC_PEDID_CONCU_RECOM.IMP_MONT_PEDI%TYPE;
    lnUnidDevuelto  INC_PEDID_CONCU_RECOM.NUM_UNID_PEDI%TYPE;
  
    lsMaxCodTipoSoli PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE;
    lnIndTipoCuadre	 INC_OBTEN_PUNTO.IND_TIPO_CUAD%TYPE;  
    lnOidSoliPunta   INC_SOLIC_CONCU_PUNTA.OID_SOLI_CONC_PUNT%TYPE;

    lnPuntajeTotalBonResto  INC_SOLIC_CONCU_PUNTA.DEV_PUNT_BONI_REST%TYPE;
    lnPuntajeTotalResto     INC_SOLIC_CONCU_PUNTA.DEV_PUNT_REST%TYPE;
    lnPuntajeTotalExiResto  INC_SOLIC_CONCU_PUNTA.DEV_PUNT_EXIG_REST%TYPE;
    
    lnPuntajeTotalBon2      NUMBER;
    lnPuntajeTotal2         NUMBER;
    lnPuntajeTotalExi2      NUMBER;
              
    CURSOR c_ConcursosDevolucion(oidSolicitud  NUMBER,
                                 oidCliente    NUMBER,
                                 codigoPeriodo VARCHAR2) IS
      SELECT cpg.OID_PARA_GRAL, 
             NVL(scp.NUM_PUNT_EXIG, 0),
             scp.OID_SOLI_CONC_PUNT
        FROM PED_SOLIC_CABEC       cab,
             PED_TIPO_SOLIC_PAIS   tsp,
             PED_TIPO_SOLIC        sol,
             INC_SOLIC_CONCU_PUNTA scp,
             INC_CONCU_PARAM_GENER cpg,
             INC_PARAM_GENER_PREMI pgp
       WHERE cab.SOCA_OID_SOLI_CABE = oidSolicitud
         AND cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
         AND sol.COD_TIPO_SOLI = 'SOC'
         AND scp.SOCA_OID_SOLI_CABE = cab.OID_SOLI_CABE
         AND scp.COPA_OID_PARA_GRAL = cpg.OID_PARA_GRAL
         AND pgp.COPA_OID_PARA_GRAL = cpg.OID_PARA_GRAL
         AND cpg.IND_DEVO = 1
         AND scp.NUM_PUNT > 0
         AND scp.IND_ANUL <> 1
         AND (pgp.PERD_OID_PERI IS NULL OR
             (codigoPeriodo <=
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(pgp.PERD_OID_PERI)))
         AND NOT EXISTS
       (SELECT 1
                FROM INC_CANDI_GANAD gan
               WHERE gan.COPA_OID_PARA_GRAL = cpg.OID_PARA_GRAL
                 AND gan.CLIE_OID_CLIE = cab.CLIE_OID_CLIE
                 AND gan.VAL_REQU_PREM_SUPE = 1)
         AND NOT EXISTS
       (SELECT *
                FROM INC_DESCA des
               WHERE des.CLIE_OID_CLIE = oidCliente
                 AND des.COPA_OID_PARA_GRAL = cpg.OID_PARA_GRAL);
  
    CURSOR c_ConcursosOrdenCompra(oidCliente    NUMBER,
                                  codigoPeriodo VARCHAR2) IS
      SELECT cpg.OID_PARA_GRAL
        FROM INC_CONCU_PARAM_GENER cpg
       WHERE cpg.IND_ACTI = 1
         AND cpg.BCAL_OID_BASE_CALC in (1, 2)
         AND cpg.IND_NO_GENE_PUNT = 0
         AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.PERD_OID_PERI_DESD) <=
             codigoPeriodo
         AND codigoPeriodo <=
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cpg.PERD_OID_PERI_HAST)
         AND NOT EXISTS
       (SELECT *
                FROM INC_DESCA des
               WHERE des.CLIE_OID_CLIE = oidCliente
                 AND des.COPA_OID_PARA_GRAL = cpg.OID_PARA_GRAL);
  
    TYPE t_oidConcurso IS TABLE OF INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
    TYPE t_numPuntaje IS TABLE OF INC_SOLIC_CONCU_PUNTA.NUM_PUNT%TYPE;
    TYPE t_oidSoliPunta IS TABLE OF INC_SOLIC_CONCU_PUNTA.OID_SOLI_CONC_PUNT%TYPE;
      
    v_oidConcurso t_oidConcurso := t_oidConcurso();
    v_numPuntaje  t_numPuntaje := t_numPuntaje();
    v_oidSoliPunta  t_oidSoliPunta := t_oidSoliPunta();
  
    v_lineaOblig t_lineaOblig := t_lineaOblig();
    v_totalOblig t_totalOblig := t_totalOblig();
  
  BEGIN
  
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Recuperamos la Fecha y Campaña de Proceso
    SELECT FEC_PROC, COD_PERI
      INTO ldFechaFacturacion, ldcampanaproceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(ldcampanaproceso,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos datos de la Solicitud
    SELECT psc.CLIE_OID_CLIE,
           sol.COD_TIPO_SOLI,
           --         psc.FEC_FACT,
           psc.SOCA_OID_DOCU_REFE,
           (SELECT REFE.PERD_OID_PERI
              FROM PED_SOLIC_CABEC REFE
             WHERE REFE.OID_SOLI_CABE = psc.SOCA_OID_DOCU_REFE)
      INTO lnOidCliente,
           lsCodTipoSoli,
           --         ldFechaFacturacion,
           lnOidSoliRefe,
           lnOIdperiodorefe
      FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
     WHERE psc.OID_SOLI_CABE = pnOidSolicitud
       AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
       AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI;
  
    --Recuperamos el Periodo Anterior
    lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  
    IF (lsCodTipoSoli = 'SOC') THEN
      lsCodPeriodo     := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo);
      lnOidSoliRefe    := NULL;
      lnOIdperiodorefe := NULL;
    ELSE
      lsCodPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodorefe);
    END IF;
  
    --Obtenemos el periodo Anterior
    lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriodo,
                                                              lnOidPais,
                                                              lnOidMarca,
                                                              lnOidCanal,
                                                              -1);
  
    --Si la Solicitud es de tipo: DEVOLUCION NORMAL
    IF (lsCodTipoSoli = 'SDDN') THEN
      --Recuperamos los Concursos Objetivos
      OPEN c_ConcursosDevolucion(lnOidSoliRefe,
                                 lnOidCliente,
                                 ldcampanaproceso);
      LOOP
        FETCH c_ConcursosDevolucion
          INTO lnOidConcurso, lnNumPuntajeDevo, lnOidSoliPunta;
        EXIT WHEN c_ConcursosDevolucion%NOTFOUND;
      
        v_oidConcurso.EXTEND(1);
        v_oidConcurso(v_oidConcurso.COUNT) := lnOidConcurso;
      
        v_numPuntaje.EXTEND(1);
        v_numPuntaje(v_numPuntaje.COUNT) := lnNumPuntajeDevo;
        
        v_oidSoliPunta.EXTEND(1);
        v_oidSoliPunta(v_oidSoliPunta.COUNT) := lnOidSoliPunta;
      
      END LOOP;
      CLOSE c_ConcursosDevolucion;
    
      lsIndReemplazo := GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                        'INC',
                                                        '001');
    END IF;
  
    --Si la Solicitud es de tipo: ORDEN DE COMPRA
    IF (lsCodTipoSoli = 'SOC') THEN
      /*
          --Recuperamos la Fecha de Proceso
          SELECT FEC_PROC
            INTO ldFechaFacturacion
            FROM BAS_CTRL_FACT
           WHERE COD_PAIS = psCodigoPais
             AND STA_CAMP = '0'
             AND IND_CAMP_ACT = '1';
      */
      --Recuperamos los Concursos Objetivos
      OPEN c_ConcursosOrdenCompra(lnOidCliente, lsCodPeriodo);
      LOOP
        FETCH c_ConcursosOrdenCompra
          INTO lnOidConcurso;
        EXIT WHEN c_ConcursosOrdenCompra%NOTFOUND;
      
        v_oidConcurso.EXTEND(1);
        v_oidConcurso(v_oidConcurso.COUNT) := lnOidConcurso;
      
      END LOOP;
      CLOSE c_ConcursosOrdenCompra;
    
    END IF;
  
    --RECORREMOS LA LISTA DE CONCURSOS OBJETIVOS
    FOR i IN 1 .. v_oidConcurso.COUNT LOOP
      lnOidConcurso := v_oidConcurso(i);
      lsResult      := '1';
    
      IF (lsCodTipoSoli = 'SDDN') THEN
        lnNumPuntajeDevo := v_numPuntaje(i);
        lnOidSoliPunta := v_oidSoliPunta(i);
      END IF;
    
      --RECUPERAMOS DATOS DEL CONCURSO
      SELECT gen.BCAL_OID_BASE_CALC,
             gen.VAL_NOMB,
             gen.NUM_CONC,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_DESD),
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_HAST),
             gen.IND_DUPL_CYZO,
             obt.VAL_FACT_CONV,
             obt.NUM_PUNT_ASIG,
             obt.IND_COMU_OBTE,
             obt.MENS_OID_MENS,
             obt.VAL_TEXT_RANK,
             cla.COD_CLAS_CONC,
             req.VAL_MONT_MINI_CONC,
             (SELECT COUNT(1)
                FROM INC_PRODU_VALID
               WHERE PRDU_OID_PROD = pro.OID_PROD),
             (SELECT COUNT(1)
                FROM INC_PRODU_EXCLU
               WHERE PRDU_OID_PROD = pro.OID_PROD),
             (SELECT COUNT(1)
                FROM INC_PRODU_BONIF
               WHERE PRDU_OID_PROD = pro.OID_PROD),
             (SELECT COUNT(1)
                FROM INC_PRODU_EXIGI
               WHERE PRDU_OID_PROD = pro.OID_PROD),
             (SELECT COUNT(1)
                FROM INC_PRODU_EXIGI
               WHERE PRDU_OID_PROD = pro.OID_PROD
                 AND NVL(IND_AGRUP, 'T') = 'T'),
             obt.IND_TIPO_CUAD    
        INTO lnOidBaseCalc,
             lsNombreConcurso,
             lsNumeroConcurso,
             lsCodPeriodoDesde,
             lsCodPeriodoHasta,
             lnIndDuplaCyzone,
             lnFactConversion,
             lnPuntosAsignar,
             lnIndComuObte,
             lnOidMensaje,
             lsTextoRanking,
             lsTipoClasiConcu,
             lnMontoMinConcu,
             lnTotalProdVal,
             lnTotalProdExc,
             lnTotalProdBon,
             lnTotalProdExi,
             lnTotalProdExiOblig,
             lnIndTipoCuadre
        FROM INC_CONCU_PARAM_GENER gen,
             INC_OBTEN_PUNTO       obt,
             INC_PRODU             pro,
             INC_CLASI_CONCU       cla,
             INC_REQUI_PREMI       req
       WHERE gen.OID_PARA_GRAL = obt.COPA_OID_PARA_GRAL
         AND gen.OID_PARA_GRAL = lnOidConcurso
         AND gen.OID_PARA_GRAL = pro.COPA_OID_PARA_GRAL(+)
         AND gen.CCON_OID_CLAS_CONC = cla.OID_CLAS_CONC(+)
         AND gen.OID_PARA_GRAL = req.COPA_OID_PARA_GRAL(+);

      lnHayMensaje:=0;
    
      IF (lsCodTipoSoli = 'SOC') THEN
        --VERIFICA SI HAY MENSAJE DE CONCURSO INSCRITO EN EL PATRON
        SELECT COUNT(1)
           INTO lnOcurrencias2
        FROM MSG_MENSA_CAMPA B
        WHERE lnOidMensaje = B.mens_oid_mens
           AND B.cam_proc = lsCodPeriodo
           AND B.ind_acti=1
           AND B.est_regi ='1';
        -----   
        IF lnOcurrencias2 >0 THEN
           lnHayMensaje:=1;
        END IF;
        ----------   
      
      
        --VALIDACION AMBITO GEOGRAFICO
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM INC_AMBIT_GEOGR geo
         WHERE COPA_OID_PARA_GRAL = lnOidConcurso;
      
        IF (lnOcurrencias > 0) THEN
          --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
          BEGIN
            lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(lnOidConcurso,
                                                       lnOidCliente);
          EXCEPTION
            WHEN OTHERS THEN
              lsResult := '0';
          END;
        END IF;
      
        IF (lsResult = '1') THEN
          --VALIDACION DEL TIPO DE PARTICIPANTE
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM INC_CLASI_PARTI_CONCU par
           WHERE par.copa_oid_para_gral = lnOidConcurso;
        
          lsResult := '1';
          IF (lnOcurrencias > 0) THEN
            lsResult := INC_FN_VALID_CLASI_CONCU(lnOidConcurso,
                                                 lnOidCliente); --1:valid 0:no valido
          END IF;
        
          IF (lsResult = '1') THEN
            --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_ESTAT_VENTA_CONSU
             WHERE COPA_OID_PARA_GRAL = lnOidConcurso;
          
            lsResult := '1';
            IF (lnOcurrencias > 0) THEN
              lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(lnOidConcurso,
                                                         lnOidCliente,
                                                         lsCodPeriodo,
                                                         lsCodPeriodoAnt); --1:valid 0:no valido
            END IF;
          
            IF (lsResult = '1') THEN
              --VALIDACION DE EXIGENCIAS DE PEDIDO
              lsResult := INC_FN_VALID_EXIGE_PEDID(lnOidConcurso,
                                                   lnOidCliente,
                                                   lnOidPeriodo,
                                                   pnOidSolicitud);
            END IF;
          
          END IF;
        
        END IF;
      END IF;
    
      IF (lsResult = '1') THEN
        lnPuntajeTotal    := 0;
        lnPuntajeTotalBon := 0;
        lnPuntajeTotalExi := 0;
        lnImporte         := 0;
        lnUnidades        := 0;
        lnTotalOblig      := 0;
      
        IF (lnTotalProdExiOblig > 0) THEN
          v_lineaOblig.DELETE;
          v_totalOblig.DELETE;
        
          FOR k IN (SELECT DECODE(PROD_OID_PROD,
                                  NULL,
                                  '',
                                  'A' || TO_CHAR(PROD_OID_PROD)) ||
                           DECODE(TOFE_OID_TIPO_OFER,
                                  NULL,
                                  '',
                                  'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                           DECODE(MAPR_OID_MARC_PROD,
                                  NULL,
                                  '',
                                  'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                           DECODE(NEGO_OID_NEGO,
                                  NULL,
                                  '',
                                  'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                           DECODE(UNEG_OID_UNID_NEGO,
                                  NULL,
                                  '',
                                  'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                           DECODE(GENE_OID_GENE,
                                  NULL,
                                  '',
                                  'F' || TO_CHAR(GENE_OID_GENE)) ||
                           DECODE(SGEN_OID_SUPE_GENE,
                                  NULL,
                                  '',
                                  'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                           DECODE(OFDE_OID_DETA_OFER,
                                  NULL,
                                  '',
                                  'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA
                      FROM INC_PRODU pro, INC_PRODU_EXIGI exi
                     WHERE pro.OID_PROD = exi.PRDU_OID_PROD
                       AND pro.COPA_OID_PARA_GRAL = lnOidConcurso) LOOP
          
            v_lineaOblig.EXTEND(1);
            v_lineaOblig(v_lineaOblig.COUNT) := k.linea;
            v_totalOblig.EXTEND(1);
            v_totalOblig(v_totalOblig.COUNT) := 0;
          END LOOP;
        
        END IF;
      
        --BARREMOS TODAS LAS POSICIONES DE SOLICITUD, ACUMULANDO EL PUNTAJE POR CONCURSO
        FOR y IN (SELECT pos.OID_SOLI_POSI,
                         pos.PROD_OID_PROD,
                         pos.OFDE_OID_DETA_OFER,
                         det.TOFE_OID_TIPO_OFER,
                         pro.MAPR_OID_MARC_PROD,
                         pro.NEGO_OID_NEGO,
                         pro.UNEG_OID_UNID_NEGO,
                         pro.GENE_OID_GENE,
                         pro.SGEN_OID_SUPE_GENE,
                         pos.VAL_PREC_CATA_UNIT_LOCA,
                         pos.NUM_UNID_DEMA_REAL
                    FROM PED_SOLIC_POSIC pos,
                         PRE_OFERT_DETAL det,
                         MAE_PRODU       pro
                   WHERE pos.SOCA_OID_SOLI_CABE = pnOidSolicitud
                     AND pos.OFDE_OID_DETA_OFER = det.OID_DETA_OFER
                     AND pos.PROD_OID_PROD = pro.OID_PROD
                     AND pos.ESPO_OID_ESTA_POSI <> 2) LOOP
        
          lsResult      := '1';
          lbValPosicion := TRUE;
        
          --PARA LAS DEVOLUCIONES, VERIFICAMOS SI EL PRODUCTO ES DE REEMPLAZO
          IF (lsCodTipoSoli = 'SDDN' AND lsIndReemplazo = '1') THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM PRE_MATRI_FACTU mfa, PRE_MATRI_REEMP ree
             WHERE mfa.ofde_oid_deta_ofer = y.OFDE_OID_DETA_OFER
               AND mfa.OID_MATR_FACT = ree.MAFA_OID_COD_REEM;
          
            IF (lnOcurrencias > 0) THEN
              lsResult      := '0';
              lbValPosicion := FALSE;
            END IF;
          END IF;
        
          IF (lsCodTipoSoli = 'SDDN') THEN
          
            SELECT MAX(cod_tipo_soli)
              INTO lsMaxCodTipoSoli
              FROM ped_solic_posic       sp,
                   rec_linea_opera_recla lin,
                   ped_solic_posic       sp2,
                   ped_solic_cabec       sc2,
                   ped_tipo_solic_pais   tsp,
                   ped_tipo_solic        ts
             WHERE sp.OID_LINE_OPER_RECL = lin.OID_LINE_OPER_RECL
               AND sp.oid_soli_posi = y.oid_soli_posi
               AND lin.sopo_oid_soli_posi = sp2.oid_soli_posi
               AND sc2.oid_soli_cabe = sp2.soca_oid_soli_cabe
               AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND tsp.oid_tipo_soli_pais = sc2.tspa_oid_tipo_soli_pais;
          
            IF NOT (lsMaxCodTipoSoli = 'SOC' OR lsMaxCodTipoSoli IS NULL) THEN
              lsResult      := '0';
              lbValPosicion := FALSE;
            END IF;
          END IF;
        
          --HACEMOS EL CALCULO DE PUNTAJE POR POSICION
          IF (lsResult = '1') THEN
            --REALIZAMOS VALIDACIONES DE PRODUCTOS VALIDOS
            IF (lnTotalProdVal > 0) THEN
              lsResult := INC_FN_VALID_PRODU_CONCU('1',
                                                   lnOidConcurso,
                                                   y.PROD_OID_PROD,
                                                   y.TOFE_OID_TIPO_OFER,
                                                   y.MAPR_OID_MARC_PROD,
                                                   y.NEGO_OID_NEGO,
                                                   y.UNEG_OID_UNID_NEGO,
                                                   y.GENE_OID_GENE,
                                                   y.SGEN_OID_SUPE_GENE,
                                                   y.OFDE_OID_DETA_OFER,
                                                   lsCodPeriodo);
            END IF;
            IF (lsResult = '1' AND lnTotalProdExc > 0) THEN
              lsResult := INC_FN_VALID_PRODU_CONCU('2',
                                                   lnOidConcurso,
                                                   y.PROD_OID_PROD,
                                                   y.TOFE_OID_TIPO_OFER,
                                                   y.MAPR_OID_MARC_PROD,
                                                   y.NEGO_OID_NEGO,
                                                   y.UNEG_OID_UNID_NEGO,
                                                   y.GENE_OID_GENE,
                                                   y.SGEN_OID_SUPE_GENE,
                                                   y.OFDE_OID_DETA_OFER,
                                                   lsCodPeriodo);
            END IF;
          
            --Si es Producto Valido, Hacemos el Calculo de Puntaje
            IF (lsResult = '1') THEN
              lnImporte  := lnImporte + (y.VAL_PREC_CATA_UNIT_LOCA *
                            y.NUM_UNID_DEMA_REAL);
              lnUnidades := lnUnidades + y.NUM_UNID_DEMA_REAL;
            
              IF (lnOidBaseCalc = 1) THEN
                --Monto
                lnPuntajePosicion := ((y.VAL_PREC_CATA_UNIT_LOCA *
                                     y.NUM_UNID_DEMA_REAL) /
                                     lnFactConversion) * lnPuntosAsignar;
              END IF;
            
              IF (lnOidBaseCalc = 2) THEN
                --Unidades
                lnPuntajePosicion := ((y.NUM_UNID_DEMA_REAL) /
                                     lnFactConversion) * lnPuntosAsignar;
              END IF;
            
              --Verificamos si es un producto Bonificado
              lnPuntajePosBonif := 0;
              IF (lnTotalProdBon > 0) THEN
                lsResult := INC_FN_VALID_PRODU_CONCU('3',
                                                     lnOidConcurso,
                                                     y.PROD_OID_PROD,
                                                     y.TOFE_OID_TIPO_OFER,
                                                     y.MAPR_OID_MARC_PROD,
                                                     y.NEGO_OID_NEGO,
                                                     y.UNEG_OID_UNID_NEGO,
                                                     y.GENE_OID_GENE,
                                                     y.SGEN_OID_SUPE_GENE,
                                                     y.OFDE_OID_DETA_OFER,
                                                     lsCodPeriodo);
              
                IF (lsResult = '1') THEN
                  lnPuntajePosBonif := INC_FN_CALCU_PUNTA_BONIF(lnOidConcurso,
                                                                y.PROD_OID_PROD,
                                                                y.TOFE_OID_TIPO_OFER,
                                                                y.MAPR_OID_MARC_PROD,
                                                                y.NEGO_OID_NEGO,
                                                                y.UNEG_OID_UNID_NEGO,
                                                                y.GENE_OID_GENE,
                                                                y.SGEN_OID_SUPE_GENE,
                                                                y.OFDE_OID_DETA_OFER,
                                                                lsCodPeriodo,
                                                                lnPuntajePosicion,
                                                                y.NUM_UNID_DEMA_REAL);
                END IF;
              
                lnPuntajeTotalBon := lnPuntajeTotalBon + lnPuntajePosBonif;
              END IF;
            
              --            lnPuntajeTotal := lnPuntajeTotal + lnPuntajePosicion + lnPuntajePosBonif;
              lnPuntajeTotal := lnPuntajeTotal + lnPuntajePosicion; -- Ajuste 17/07/2014
            END IF;
          
            --HACEMOS EL CALCULO DE PUNTAJE EXIGIDO POR POSICION
            IF (lbValPosicion) THEN
              IF (lnTotalProdExi > 0) THEN
                INC_PR_VALID_PRODU_EXIGI_CONCU(lnOidConcurso,
                                               y.PROD_OID_PROD,
                                               y.TOFE_OID_TIPO_OFER,
                                               y.MAPR_OID_MARC_PROD,
                                               y.NEGO_OID_NEGO,
                                               y.UNEG_OID_UNID_NEGO,
                                               y.GENE_OID_GENE,
                                               y.SGEN_OID_SUPE_GENE,
                                               y.OFDE_OID_DETA_OFER,
                                               lsCodPeriodo,
                                               lsResult,
                                               v_lineaOblig,
                                               v_totalOblig);
              ELSE
                lsResult := 0;
              END IF;
            
              IF (lsResult = '1') THEN
                lnPuntajePosicion := 0;
              
                IF (lnOidBaseCalc = 1) THEN
                  --Monto
                  lnPuntajePosicion := ((y.VAL_PREC_CATA_UNIT_LOCA *
                                       y.NUM_UNID_DEMA_REAL) /
                                       lnFactConversion) * lnPuntosAsignar;
                END IF;
              
                IF (lnOidBaseCalc = 2) THEN
                  --Unidades
                  lnPuntajePosicion := (y.NUM_UNID_DEMA_REAL /
                                       lnFactConversion) * lnPuntosAsignar;
                END IF;
              
                --Si Puntaje Exigido <> 0, recien se contabiliza los marcados como Obligatorio
                IF (lnPuntajePosicion <> 0) THEN
                  lnPuntajeTotalExi := lnPuntajeTotalExi +
                                       lnPuntajePosicion;
                
                END IF;
              
              END IF;
            END IF;
          
          END IF;
        
        END LOOP;
      
        --PARA LAS ORDENES DE COMPRA
        --Si no cumple con el total de Obligatorios el puntaje de Total Exigido sera 0
        IF ((lsCodTipoSoli = 'SOC') AND (lnTotalProdExiOblig > 0)) THEN
          lnTotalOblig := 1;
          FOR i IN 1 .. v_totalOblig.COUNT LOOP
            lnTotalOblig := lnTotalOblig * v_totalOblig(i);
          END LOOP;
        
          IF (lnTotalOblig = 0) THEN
            lnPuntajeTotalExi := 0;
          END IF;
        
        END IF;
      
        --PARA LAS DEVOLUCIONES
        --Si el puntaje de Exigido > Puntaje Exigido Solicitud Devolucion sera 0
        IF (lsCodTipoSoli = 'SDDN') THEN
          IF (ABS(lnPuntajeTotalExi) > ABS(lnNumPuntajeDevo)) THEN
            lnPuntajeTotalExi := 0;
          END IF;
        END IF;
      
        --TRUNCAMOS O REDONDEAMOS LOS TOTALES DE PUNTAJE, DEPENDE DEL VALOR DEL 
        --INDICADOR DE TIPO CUADRE
        IF(lnIndTipoCuadre = 2) THEN
          IF(lsCodTipoSoli = 'SDDN') THEN
          
            SELECT NVL(DEV_PUNT_BONI_REST, 0),
                   NVL(DEV_PUNT_REST, 0), 
                   NVL(DEV_PUNT_EXIG_REST, 0)
              INTO lnPuntajeTotalBonResto, 
                   lnPuntajeTotalResto, 
                   lnPuntajeTotalExiResto
              FROM INC_SOLIC_CONCU_PUNTA
             WHERE OID_SOLI_CONC_PUNT = lnOidSoliPunta;
            
            lnPuntajeTotalBon2 := TRUNC(lnPuntajeTotalBon + lnPuntajeTotalBonResto,0);
            lnPuntajeTotal2    := TRUNC(lnPuntajeTotal + lnPuntajeTotalResto,0) + lnPuntajeTotalBon;
            lnPuntajeTotalExi2 := TRUNC(lnPuntajeTotalExi + lnPuntajeTotalExiResto,0);

            lnPuntajeTotalBonResto := lnPuntajeTotalBon - lnPuntajeTotalBon2;
            lnPuntajeTotalResto    := lnPuntajeTotal - TRUNC(lnPuntajeTotal + lnPuntajeTotalResto,0);
            lnPuntajeTotalExiResto := lnPuntajeTotalExi - lnPuntajeTotalExi2;

            lnPuntajeTotalBon  := lnPuntajeTotalBon2;
            lnPuntajeTotal     := lnPuntajeTotal2;
            lnPuntajeTotalExi  := lnPuntajeTotalExi2;
            
            UPDATE INC_SOLIC_CONCU_PUNTA
               SET DEV_PUNT_BONI_REST = lnPuntajeTotalBonResto,
                   DEV_PUNT_REST = lnPuntajeTotalResto,
                   DEV_PUNT_EXIG_REST = lnPuntajeTotalExiResto
             WHERE OID_SOLI_CONC_PUNT = lnOidSoliPunta;      
                       
          ELSE
            
            lnPuntajeTotalBon := TRUNC(lnPuntajeTotalBon, 0);
            lnPuntajeTotal    := TRUNC(lnPuntajeTotal, 0) + lnPuntajeTotalBon;
            lnPuntajeTotalExi := TRUNC(lnPuntajeTotalExi, 0);
          END IF;
 
        ELSE
          lnPuntajeTotalBon := ROUND(lnPuntajeTotalBon, 0);
          lnPuntajeTotal    := ROUND(lnPuntajeTotal, 0) + lnPuntajeTotalBon;
          lnPuntajeTotalExi := ROUND(lnPuntajeTotalExi, 0);
        END IF;
        
        --INSERTAMOS EN LA TABLA INC_CANDI_GANAD
        IF (lsCodTipoSoli = 'SOC' AND
           ((lnPuntajeTotal > 0) OR (lnPuntajeTotalExi > 0))) THEN
          --validar previamente unicidad en la tabla INC_CANDI_GANAD
          BEGIN
            SELECT NVL(MAX(NUM_PERI_EVAL), 0),
                   NVL(SUM(CASE
                             WHEN PERD_OID_PERI = lnOidPeriodo THEN
                              1
                             ELSE
                              0
                           END),
                       0)
              INTO lnUltevaluacion, lnOcurrencias
              FROM INC_CANDI_GANAD
             WHERE COPA_OID_PARA_GRAL = lnOidConcurso
               AND CLIE_OID_CLIE = lnOidCliente;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnUltevaluacion := 0;
              lnOcurrencias   := 0;
          END;
        
          IF (lnOcurrencias = 0) THEN
            INSERT INTO INC_CANDI_GANAD
              (OID_CAND_GANA,
               IND_META_SUPE,
               VAL_REQU_PREM_SUPE,
               PERD_OID_PERI,
               COPA_OID_PARA_GRAL,
               BINC_OID_BASE_INCU,
               PERD_OID_PERI_EVAL,
               CLIE_OID_CLIE,
               FEC_ULTI_ACTU,
               NUM_PERI_EVAL,
               REGL_OID_REGL)
            VALUES
              (INC_CAGA_SEQ.NEXTVAL,
               0,
               0,
               lnOidPeriodo,
               lnOidConcurso,
               NULL,
               NULL,
               lnOidCliente,
               SYSDATE,
               lnUltevaluacion + 1,
               NULL);
          ELSE
            UPDATE INC_CANDI_GANAD
               SET NUM_PERI_EVAL = lnUltevaluacion + 1,
                   FEC_ULTI_ACTU = SYSDATE
             WHERE PERD_OID_PERI = lnOidPeriodo
               AND COPA_OID_PARA_GRAL = lnOidConcurso
               AND CLIE_OID_CLIE = lnOidCliente;
          
          END IF;
        END IF;
      
        --INSERTAMOS EN LA TABLA INC_SOLIC_CONCU_PUNTA
        IF ((lnPuntajeTotal <> 0) OR (lnPuntajeTotalExi <> 0)) THEN
          INSERT INTO INC_SOLIC_CONCU_PUNTA
            (OID_SOLI_CONC_PUNT,
             NUM_PUNT,
             VAL_PUNT_BONI,
             NUM_PUNT_EXIG,
             VAL_PUNT_FALT_NANU,
             FEC_DOCU,
             IND_ANUL,
             COPA_OID_PARA_GRAL,
             SOCA_OID_SOLI_CABE,
             PERD_OID_PERI,
             CLIE_OID_CLIE,
             IMP_MONT,
             CLIE_OID_CLIE_GERE,
             NUM_UNID)
          VALUES
            (INC_SOCP_SEQ.NEXTVAL,
             lnPuntajeTotal,
             lnPuntajeTotalBon,
             lnPuntajeTotalExi,
             0,
             ldFechaFacturacion,
             DECODE(lsCodTipoSoli, 'SOC', 0, 1),
             lnOidConcurso,
             pnOidSolicitud,
             lnOidPeriodo,
             lnOidCliente,
             lnImporte,
             NULL,
             lnUnidades);
        
          lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
          --CREAMOS EL REGISTRO EN LA ENTIDAD INC_CUENT_CORRI_PUNTO
          INSERT INTO INC_CUENT_CORRI_PUNTO
            (OID_CUEN_CORR_PUNT,
             NUM_MOVI,
             NUM_PUNT,
             NUM_PUNT_EXIG,
             FEC_MOVI,
             COPA_OID_PARA_GRAL,
             CLIE_OID_CLIE,
             PERD_OID_PERI,
             TMOV_OID_TIPO_MOVI,
             FEC_ULTI_ACTU,
             VAL_DESC,
             USU_MODI,
             PERD_OID_PERI_REFE,
             NUM_PUNT_BONI,
             DES_MOTI)
          VALUES
            (lnOidSecuencia,
             lnOidSecuencia,
             lnPuntajeTotal,
             lnPuntajeTotalExi,
             ldFechaFacturacion,
             lnOidConcurso,
             lnOidCliente,
             lnOidPeriodo,
             DECODE(lsCodTipoSoli, 'SOC', 1, 2),
             SYSDATE,
             DECODE(lsCodTipoSoli,
                    'SOC',
                    'Abono Puntaje del periodo Grupo Todas Venta Directa ' ||
                    lsCodPeriodo,
                    'Cargo Devolución del periodo ' || lsCodPeriodo ||
                    ' (REC)'),
             psCodigoUsuario,
             lnoidperiodorefe,
             lnPuntajeTotalBon,
             DECODE(lsCodTipoSoli, 'SOC', NULL, 'Devolución'));
        
          IF (lsCodTipoSoli = 'SOC' AND lnIndComuObte = 1 AND lnHayMensaje = 1) THEN
          
            --En caso de Clasificacion = Recocimiento, Puntaje Superior y Nivel
            IF (lsTipoClasiConcu = 'T') THEN
            
              lnIndTieneRanking := 0;
              BEGIN
                SELECT A.NUM_RANK
                  INTO lnNumeroRanking
                  FROM INC_RANKI_RECON A, MAE_CLIEN B
                 WHERE B.OID_CLIE = lnOidCliente
                   AND A.COD_CLIE = B.COD_CLIE
                   AND A.COD_CONC = lsNumeroConcurso
                   AND A.COD_PERI = lsCodPeriodoAnt;
              
                lnIndTieneRanking := 1;
              
                IF ((lsTextoRanking IS NOT NULL) AND
                   (lnNumeroRanking IS NOT NULL)) THEN
                  lsTextoRankingAux := lsTextoRanking || ' ' ||
                                       TO_CHAR(lnNumeroRanking);
                ELSE
                  lsTextoRankingAux := ' ';
                END IF;
              
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  NULL;
              END;
            END IF;
          
            --INSERTAMOS REGISTRO EN BUZON DE MENSAJES
            INSERT INTO MSG_BUZON_MENSA
              (OID_BUZO_MENS,
               NUM_SECU,
               DATO_VARI_10,
               DATO_VARI_11,
               DATO_VARI_12,
               DATO_VARI_13,
               DATO_VARI_14,
               DATO_VARI_15,
               DATO_VARI_16,
               DATO_VARI_17,
               DATO_VARI_18,
               DATO_VARI_19,
               DATO_VARI_20,
               IND_ESTA_MENS,
               CLIE_OID_CLIE,
               MENS_OID_MENS,
               MODU_OID_MODU_ORIG,
               VAL_NOM1_CLIE,
               VAL_NOM2_CLIE,
               VAL_APE1_CLIE,
               VAL_APE2_CLIE,
               VAL_APEL_CASA_CLIE,
               DATO_VARI_01,
               DATO_VARI_02,
               DATO_VARI_03,
               DATO_VARI_04,
               DATO_VARI_05,
               DATO_VARI_06,
               DATO_VARI_07,
               DATO_VARI_08,
               DATO_VARI_09,
               NUM_LOTE_IMPR,
               FEC_GRAB,
               FEC_IMPR,
               IND_LIST_CONS,
               PERI_OID_PERI,
               IND_ACTI)
              SELECT MSG_BUME_SEQ.NEXTVAL,
                     MSG_BUM2_SEQ.NEXTVAL,
                     0,
                     0, --datoVariable11
                     0, --datoVariable12
                     0, --datoVariable13
                     (CASE
                       WHEN (lsTipoClasiConcu = 'T' AND
                            lnIndTieneRanking = 1) THEN
                        lsTextoRankingAux
                       WHEN (lsTipoClasiConcu = 'T') THEN
                        ' '
                       ELSE
                        NULL
                     END), --datoVariable14
                     NULL, --datoVariable15
                     NULL, --datoVariable16
                     NULL, --datoVariable17,
                     lsNombreConcurso, --datoVariable18
                     0, --datoVariable19
                     0, --datoVariable20
                     NULL,
                     lnOidCliente,
                     lnOidMensaje,
                     13, --MODULO INCENTIVOS
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     (SELECT VAL_NOM1 || ' ' || VAL_NOM2 || ' ' || VAL_APE1 || ' ' ||
                             VAL_APE2
                        FROM MAE_CLIEN
                       WHERE OID_CLIE = lnOidCliente), --datoVariable01
                     lsNumeroConcurso, --datoVariable02
                     0, --datoVariable03
                     lsCodPeriodoDesde, --datoVariable04
                     lsCodPeriodoHasta, --datoVariable05
                     0, --datoVariable06
                     '0', --datoVariable07
                     0, --datoVariable08
                     0, --datoVariable09
                     NULL,
                     SYSDATE,
                     NULL,
                     0,
                     NULL,
                     1
                FROM DUAL;
          END IF;
        
        END IF;
      
      END IF;
    
    END LOOP;
  
    --Para el Caso de Devolucion, Procesamos los concurso de Recomendacion
    IF (lsCodTipoSoli = 'SDDN') THEN
    
      --Obtenemos el monto Devuelto y las unidades Devueltas
      SELECT SUM(VAL_PREC_CATA_UNIT_LOCA * NUM_UNID_DEMA_REAL * (-1)),
             SUM(NUM_UNID_DEMA_REAL * (-1))
        INTO lnMontoDevuelto, lnUnidDevuelto
        FROM PED_SOLIC_POSIC POS
       WHERE SOCA_OID_SOLI_CABE = pnOidSolicitud
         AND (select max(cod_tipo_soli)
                FROM ped_solic_posic       sp,
                     rec_linea_opera_recla lin,
                     ped_solic_posic       sp2,
                     ped_solic_cabec       sc2,
                     ped_tipo_solic_pais   tsp,
                     ped_tipo_solic        ts
               WHERE sp.OID_LINE_OPER_RECL = lin.OID_LINE_OPER_RECL
                 AND sp.oid_soli_posi = pos.oid_soli_posi
                 AND lin.sopo_oid_soli_posi = sp2.oid_soli_posi
                 AND sc2.oid_soli_cabe = sp2.soca_oid_soli_cabe
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND tsp.oid_tipo_soli_pais = sc2.tspa_oid_tipo_soli_pais) IN
             ('SOC', null);
    
      --Se recuperan todas las solicitudes SOC asociadas al consolidado  de Referencia
      FOR y IN (SELECT cab.OID_SOLI_CABE,
                       cab.CLIE_OID_CLIE,
                       rec.COPA_OID_PARA_GRAL,
                       rec.PERD_OID_PERI,
                       (CASE
                         WHEN (rec.CLIE_OID_RECO_DADO IS NULL) THEN
                          1
                         ELSE
                          0
                       END) IND_PEDI_RCTE
                  FROM PED_SOLIC_CABEC       cab,
                       PED_TIPO_SOLIC_PAIS   tsp,
                       PED_TIPO_SOLIC        sol,
                       INC_SOLIC_CONCU_RECOM rec
                 WHERE cab.SOCA_OID_SOLI_CABE = lnOidSoliRefe
                   AND tsp.OID_TIPO_SOLI_PAIS = cab.TSPA_OID_TIPO_SOLI_PAIS
                   AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
                   AND sol.COD_TIPO_SOLI = 'SOC'
                   AND cab.OID_SOLI_CABE = rec.SOCA_OID_SOLI_CABE
                   AND ((rec.CLIE_OID_CLIE = lnOidCliente AND
                       rec.CLIE_OID_RECO_DADO IS NULL) OR
                       (rec.CLIE_OID_RECO_DADO = lnOidCliente))
                   AND NVL(rec.IND_ANUL, 0) <> 1
                   AND INC_FN_VALID_CONCU_RECOM_DEVOL(rec.COPA_OID_PARA_GRAL) = 1) LOOP
      
        --Si es pedido de Recomendante
        IF (y.IND_PEDI_RCTE = 1) THEN
        
          --Buscar en la tabla INC_CLIEN_RECTE, el oid de recomendante para el concurso y consultoras tratados (Oid_clie_rete).
          SELECT OID_CLIE_RETE
            INTO lnOidClienRete
            FROM INC_CLIEN_RECTE
           WHERE CLIE_OID_CLIE = lnOidCliente
             AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL;
        
          --Buscamos en la tabla INC_CLIEN_RECDO, si hay alguna recomendada afectada
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM INC_CLIEN_RECDO
           WHERE CLR3_OID_CLIE_RETE = lnOidClienRete
             AND (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI) >=
                 lsCodPeriodoAnt)
             AND IND_EFEC IN (1, 0);
        
          IF (lnOcurrencias = 0) THEN
            BEGIN
              SELECT OID_PEDI_CONC_RECO, IMP_MONT_PEDI, NUM_UNID_PEDI
                INTO lnOidPediRecom, lnMontoPedido, lnUnidPedido
                FROM INC_PEDID_CONCU_RECOM
               WHERE CLR3_OID_CLIE_RETE = lnOidClienRete
                 AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
                 AND PERD_OID_PERI = y.PERD_OID_PERI
                 AND CLRE_OID_CLIE_REDO IS NULL;
            
              lnMontoPedido := nvl(lnMontoPedido, 0) -
                               nvl(lnMontoDevuelto, 0);
              lnUnidPedido  := nvl(lnUnidPedido, 0) -
                               nvl(lnUnidDevuelto, 0);
            
              IF ((lnMontoPedido = 0) AND (lnUnidPedido = 0)) THEN
                --Se elimina el Registro de INC_PEDID_CONCU_RECOM
                DELETE FROM INC_PEDID_CONCU_RECOM
                 WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
              ELSE
                --Se actualiza el Registro de INC_PEDID_CONCU_RECOM
                UPDATE INC_PEDID_CONCU_RECOM
                   SET IMP_MONT_PEDI = lnMontoPedido,
                       NUM_UNID_PEDI = lnUnidPedido,
                       IMP_MONT_DEVU = NVL(IMP_MONT_DEVU, 0) +
                                       lnMontoDevuelto,
                       NUM_UNID_DEVU = NVL(NUM_UNID_DEVU, 0) +
                                       lnUnidDevuelto,
                       USU_MODI      = psCodigoUsuario,
                       FEC_MODI      = SYSDATE
                 WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
              END IF;
            
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                NULL;
            END;
          END IF;
        
        ELSE
          --En caso de Ser Recomendada
          BEGIN
            SELECT rdo.OID_CLIE_REDO
              INTO lnOidClienRcdo
              FROM INC_CLIEN_RECDO rdo, INC_CLIEN_RECTE rte
             WHERE rdo.CLIE_OID_CLIE = lnOidCliente
               AND rdo.CLR3_OID_CLIE_RETE = rte.OID_CLIE_RETE
               AND rte.COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
               AND rdo.IND_EFEC IS NULL;
          
            SELECT OID_PEDI_CONC_RECO, IMP_MONT_PEDI, NUM_UNID_PEDI
              INTO lnOidPediRecom, lnMontoPedido, lnUnidPedido
              FROM INC_PEDID_CONCU_RECOM
             WHERE CLRE_OID_CLIE_REDO = lnOidClienRcdo
               AND COPA_OID_PARA_GRAL = y.COPA_OID_PARA_GRAL
               AND PERD_OID_PERI = y.PERD_OID_PERI;
          
            lnMontoPedido := nvl(lnMontoPedido, 0) -
                             nvl(lnMontoDevuelto, 0);
            lnUnidPedido  := nvl(lnUnidPedido, 0) - nvl(lnUnidDevuelto, 0);
          
            IF ((lnMontoPedido = 0) AND (lnUnidPedido = 0)) THEN
              --Se elimina el Registro de INC_PEDID_CONCU_RECOM
              DELETE FROM INC_PEDID_CONCU_RECOM
               WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
            ELSE
              --Se actualiza el Registro de INC_PEDID_CONCU_RECOM
              UPDATE INC_PEDID_CONCU_RECOM
                 SET IMP_MONT_PEDI = lnMontoPedido,
                     NUM_UNID_PEDI = lnUnidPedido,
                     IMP_MONT_DEVU = NVL(IMP_MONT_DEVU, 0) + lnMontoDevuelto,
                     NUM_UNID_DEVU = NVL(NUM_UNID_DEVU, 0) + lnUnidDevuelto,
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE OID_PEDI_CONC_RECO = lnOidPediRecom;
            END IF;
          
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;
        
        END IF;
      
      END LOOP;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
    
  END INC_PR_CALCU_PUNTA_CONSU;

  /**************************************************************************
  Descripcion       : Valida Exigencias del Pedido configurado al concurso para
                      un determinado Cliente
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    pnOidConcurso     :  oid Concurso
    pnOidCliente      :  oid Cliente
    pnOidSolicitud    :  oid Solicitud
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_EXIGE_PEDID(pnOidConcurso  NUMBER,
                                    pnOidCliente   NUMBER,
                                    pnOidPeriodo   NUMBER,
                                    pnOidSolicitud NUMBER) RETURN VARCHAR2 IS
    lnMontoPedido PED_SOLIC_CABEC_ACUM2.VAL_MONT_TOTA%TYPE;
    lnUnidPedido  PED_SOLIC_CABEC.NUM_UNID_POR_ATEN_TOTA%TYPE;
  
    lnMontoMinPedido INC_CONCU_PARAM_CONSU.IMP_MONT_MINI_PEDI%TYPE;
    lnUnidMinPedido  INC_CONCU_PARAM_CONSU.NUM_UNID_MINI_PEDI%TYPE;
  BEGIN
  
    SELECT IMP_MONT_MINI_PEDI, NUM_UNID_MINI_PEDI
      INTO lnMontoMinPedido, lnUnidMinPedido
      FROM INC_CONCU_PARAM_CONSU
     WHERE COPA_OID_PARA_GRAL = pnOidConcurso;
  
    IF (lnMontoMinPedido IS NOT NULL AND (lnMontoMinPedido > 0)) THEN
      SELECT VAL_MONT_TOTA
        INTO lnMontoPedido
        FROM PED_SOLIC_CABEC_ACUM2
       WHERE CLIE_OID_CLIE = pnOidCliente
         AND PERD_OID_PERI = pnOidPeriodo;
    
      IF (lnMontoPedido < lnMontoMinPedido) THEN
        RETURN '0';
      END IF;
    END IF;
  
    IF (lnUnidMinPedido IS NOT NULL AND (lnUnidMinPedido > 0)) THEN
      SELECT NUM_UNID_POR_ATEN_TOTA
        INTO lnUnidPedido
        FROM PED_SOLIC_CABEC
       WHERE OID_SOLI_CABE = pnOidSolicitud;
    
      IF (lnUnidPedido < lnUnidMinPedido) THEN
        RETURN '0';
      END IF;
    END IF;
  
    RETURN '1';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_EXIGE_PEDID: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_EXIGE_PEDID;

  /**************************************************************************
  Descripcion       : Valida Si la Posicion cumple con las caracteristicas de los
                      productos Validos, Excluidos y Bonificados del Concurso
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    psTipoProducto    :  '1': Productos Validos
                         '2': Productos Excluidos
                         '3': Productos Bonificados
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    pnOidPeriodo        :  oid Periodo
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_PRODU_CONCU(psTipoProducto   VARCHAR2,
                                    pnOidConcurso    NUMBER,
                                    pnOidProducto    NUMBER,
                                    pnOidTipoOferta  NUMBER,
                                    pnOidMarcaProd   NUMBER,
                                    pnOidNegocio     NUMBER,
                                    pnOidUnidNegocio NUMBER,
                                    pnOidGenerico    NUMBER,
                                    pnOidSupGenerico NUMBER,
                                    pnOidDetOferta   NUMBER,
                                    psCodigoPeriodo  VARCHAR2)
    RETURN VARCHAR2 IS
  
    lnOcurrencias NUMBER;
  BEGIN
  
    --VALIDACION PARA PRODUCTOS VALIDOS
    IF (psTipoProducto = '1') THEN
      SELECT COUNT(1)
        INTO lnOcurrencias
        FROM (SELECT DECODE(PROD_OID_PROD,
                            NULL,
                            '',
                            'A' || TO_CHAR(PROD_OID_PROD)) ||
                     DECODE(TOFE_OID_TIPO_OFER,
                            NULL,
                            '',
                            'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                     DECODE(MAPR_OID_MARC_PROD,
                            NULL,
                            '',
                            'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                     DECODE(NEGO_OID_NEGO,
                            NULL,
                            '',
                            'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                     DECODE(UNEG_OID_UNID_NEGO,
                            NULL,
                            '',
                            'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                     DECODE(GENE_OID_GENE,
                            NULL,
                            '',
                            'F' || TO_CHAR(GENE_OID_GENE)) ||
                     DECODE(SGEN_OID_SUPE_GENE,
                            NULL,
                            '',
                            'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                     DECODE(OFDE_OID_DETA_OFER,
                            NULL,
                            '',
                            'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA,
                     DECODE(PROD_OID_PROD,
                            NULL,
                            '',
                            'A' || TO_CHAR(pnOidProducto)) ||
                     DECODE(TOFE_OID_TIPO_OFER,
                            NULL,
                            '',
                            'B' || TO_CHAR(pnOidTipoOferta)) ||
                     DECODE(MAPR_OID_MARC_PROD,
                            NULL,
                            '',
                            'C' || TO_CHAR(pnOidMarcaProd)) ||
                     DECODE(NEGO_OID_NEGO,
                            NULL,
                            '',
                            'D' || TO_CHAR(pnOidNegocio)) ||
                     DECODE(UNEG_OID_UNID_NEGO,
                            NULL,
                            '',
                            'E' || TO_CHAR(pnOidUnidNegocio)) ||
                     DECODE(GENE_OID_GENE,
                            NULL,
                            '',
                            'F' || TO_CHAR(pnOidGenerico)) ||
                     DECODE(SGEN_OID_SUPE_GENE,
                            NULL,
                            '',
                            'G' || TO_CHAR(pnOidSupGenerico)) ||
                     DECODE(OFDE_OID_DETA_OFER,
                            NULL,
                            '',
                            'H' || TO_CHAR(pnOidDetOferta)) AS LINEA_POS
                FROM INC_PRODU pro, INC_PRODU_VALID val
               WHERE pro.OID_PROD = val.PRDU_OID_PROD
                 AND pro.COPA_OID_PARA_GRAL = pnOidConcurso)
       WHERE LINEA = LINEA_POS;
    
      IF (lnOcurrencias > 0) THEN
        RETURN '1';
      ELSE
        RETURN '0';
      END IF;
    
    END IF;
  
    --VALIDACION PARA PRODUCTOS EXCLUIDOS
    IF (psTipoProducto = '2') THEN
    
      FOR x IN (SELECT PERD_OID_PERI_DESD, PERD_OID_PERI_HAST
                  FROM (SELECT exc.PERD_OID_PERI_DESD,
                               exc.PERD_OID_PERI_HAST,
                               DECODE(PROD_OID_PROD,
                                      NULL,
                                      '',
                                      'A' || TO_CHAR(PROD_OID_PROD)) ||
                               DECODE(TOFE_OID_TIPO_OFER,
                                      NULL,
                                      '',
                                      'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                               DECODE(MAPR_OID_MARC_PROD,
                                      NULL,
                                      '',
                                      'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                               DECODE(NEGO_OID_NEGO,
                                      NULL,
                                      '',
                                      'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                               DECODE(UNEG_OID_UNID_NEGO,
                                      NULL,
                                      '',
                                      'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                               DECODE(GENE_OID_GENE,
                                      NULL,
                                      '',
                                      'F' || TO_CHAR(GENE_OID_GENE)) ||
                               DECODE(SGEN_OID_SUPE_GENE,
                                      NULL,
                                      '',
                                      'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                               DECODE(OFDE_OID_DETA_OFER,
                                      NULL,
                                      '',
                                      'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA,
                               DECODE(PROD_OID_PROD,
                                      NULL,
                                      '',
                                      'A' || TO_CHAR(pnOidProducto)) ||
                               DECODE(TOFE_OID_TIPO_OFER,
                                      NULL,
                                      '',
                                      'B' || TO_CHAR(pnOidTipoOferta)) ||
                               DECODE(MAPR_OID_MARC_PROD,
                                      NULL,
                                      '',
                                      'C' || TO_CHAR(pnOidMarcaProd)) ||
                               DECODE(NEGO_OID_NEGO,
                                      NULL,
                                      '',
                                      'D' || TO_CHAR(pnOidNegocio)) ||
                               DECODE(UNEG_OID_UNID_NEGO,
                                      NULL,
                                      '',
                                      'E' || TO_CHAR(pnOidUnidNegocio)) ||
                               DECODE(GENE_OID_GENE,
                                      NULL,
                                      '',
                                      'F' || TO_CHAR(pnOidGenerico)) ||
                               DECODE(SGEN_OID_SUPE_GENE,
                                      NULL,
                                      '',
                                      'G' || TO_CHAR(pnOidSupGenerico)) ||
                               DECODE(OFDE_OID_DETA_OFER,
                                      NULL,
                                      '',
                                      'H' || TO_CHAR(pnOidDetOferta)) AS LINEA_POS
                          FROM INC_PRODU pro, INC_PRODU_EXCLU exc
                         WHERE pro.OID_PROD = exc.PRDU_OID_PROD
                           AND pro.COPA_OID_PARA_GRAL = pnOidConcurso)
                 WHERE LINEA = LINEA_POS) LOOP
      
        IF (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_DESD) <=
           psCodigoPeriodo AND
           psCodigoPeriodo <=
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_HAST)) THEN
        
          RETURN '0';
        END IF;
      
      END LOOP;
    
      RETURN '1';
    
    END IF;
  
    --VALIDACION PARA PRODUCTOS BONIFICADOS
    IF (psTipoProducto = '3') THEN
    
      FOR x IN (SELECT PERD_OID_PERI_DESD, PERD_OID_PERI_HAST
                  FROM (SELECT bon.PERD_OID_PERI_DESD,
                               bon.PERD_OID_PERI_HAST,
                               DECODE(PROD_OID_PROD,
                                      NULL,
                                      '',
                                      'A' || TO_CHAR(PROD_OID_PROD)) ||
                               DECODE(TOFE_OID_TIPO_OFER,
                                      NULL,
                                      '',
                                      'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                               DECODE(MAPR_OID_MARC_PROD,
                                      NULL,
                                      '',
                                      'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                               DECODE(NEGO_OID_NEGO,
                                      NULL,
                                      '',
                                      'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                               DECODE(UNEG_OID_UNID_NEGO,
                                      NULL,
                                      '',
                                      'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                               DECODE(GENE_OID_GENE,
                                      NULL,
                                      '',
                                      'F' || TO_CHAR(GENE_OID_GENE)) ||
                               DECODE(SGEN_OID_SUPE_GENE,
                                      NULL,
                                      '',
                                      'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                               DECODE(OFDE_OID_DETA_OFER,
                                      NULL,
                                      '',
                                      'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA,
                               DECODE(PROD_OID_PROD,
                                      NULL,
                                      '',
                                      'A' || TO_CHAR(pnOidProducto)) ||
                               DECODE(TOFE_OID_TIPO_OFER,
                                      NULL,
                                      '',
                                      'B' || TO_CHAR(pnOidTipoOferta)) ||
                               DECODE(MAPR_OID_MARC_PROD,
                                      NULL,
                                      '',
                                      'C' || TO_CHAR(pnOidMarcaProd)) ||
                               DECODE(NEGO_OID_NEGO,
                                      NULL,
                                      '',
                                      'D' || TO_CHAR(pnOidNegocio)) ||
                               DECODE(UNEG_OID_UNID_NEGO,
                                      NULL,
                                      '',
                                      'E' || TO_CHAR(pnOidUnidNegocio)) ||
                               DECODE(GENE_OID_GENE,
                                      NULL,
                                      '',
                                      'F' || TO_CHAR(pnOidGenerico)) ||
                               DECODE(SGEN_OID_SUPE_GENE,
                                      NULL,
                                      '',
                                      'G' || TO_CHAR(pnOidSupGenerico)) ||
                               DECODE(OFDE_OID_DETA_OFER,
                                      NULL,
                                      '',
                                      'H' || TO_CHAR(pnOidDetOferta)) AS LINEA_POS
                          FROM INC_PRODU pro, INC_PRODU_BONIF bon
                         WHERE pro.OID_PROD = bon.PRDU_OID_PROD
                           AND pro.COPA_OID_PARA_GRAL = pnOidConcurso)
                 WHERE LINEA = LINEA_POS) LOOP
      
        IF (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_DESD) <=
           psCodigoPeriodo AND
           psCodigoPeriodo <=
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_HAST)) THEN
        
          RETURN '1';
        END IF;
      
      END LOOP;
    
      RETURN '0';
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_PRODU_CONCU: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_PRODU_CONCU;

  /**************************************************************************
  Descripcion       : Calcula Puntaje Bonificado para un detalle de Posicion
                      de una Solicitud
  
  Fecha Creacion    : 08/08/2012
  Parametros Entrada:
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    psCodigoPeriodo        :  codigo Periodo
    pnPuntajePos        :  Puntaje Posicion
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_CALCU_PUNTA_BONIF(pnOidConcurso    NUMBER,
                                    pnOidProducto    NUMBER,
                                    pnOidTipoOferta  NUMBER,
                                    pnOidMarcaProd   NUMBER,
                                    pnOidNegocio     NUMBER,
                                    pnOidUnidNegocio NUMBER,
                                    pnOidGenerico    NUMBER,
                                    pnOidSupGenerico NUMBER,
                                    pnOidDetOferta   NUMBER,
                                    psCodigoPeriodo  VARCHAR2,
                                    pnPuntajePos     NUMBER,
                                    pnDemandaReal    NUMBER) RETURN NUMBER IS
  
    lnPuntaje NUMBER;
  BEGIN
  
    lnPuntaje := 0;
  
    FOR x IN (SELECT PERD_OID_PERI_DESD,
                     PERD_OID_PERI_HAST,
                     NUM_PUNT_UNID,
                     VAL_FACT_MULT
                FROM (SELECT bon.PERD_OID_PERI_DESD,
                             bon.PERD_OID_PERI_HAST,
                             bon.NUM_PUNT_UNID,
                             bon.VAL_FACT_MULT,
                             DECODE(PROD_OID_PROD,
                                    NULL,
                                    '',
                                    'A' || TO_CHAR(PROD_OID_PROD)) ||
                             DECODE(TOFE_OID_TIPO_OFER,
                                    NULL,
                                    '',
                                    'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                             DECODE(MAPR_OID_MARC_PROD,
                                    NULL,
                                    '',
                                    'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                             DECODE(NEGO_OID_NEGO,
                                    NULL,
                                    '',
                                    'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                             DECODE(UNEG_OID_UNID_NEGO,
                                    NULL,
                                    '',
                                    'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                             DECODE(GENE_OID_GENE,
                                    NULL,
                                    '',
                                    'F' || TO_CHAR(GENE_OID_GENE)) ||
                             DECODE(SGEN_OID_SUPE_GENE,
                                    NULL,
                                    '',
                                    'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                             DECODE(OFDE_OID_DETA_OFER,
                                    NULL,
                                    '',
                                    'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA,
                             DECODE(PROD_OID_PROD,
                                    NULL,
                                    '',
                                    'A' || TO_CHAR(pnOidProducto)) ||
                             DECODE(TOFE_OID_TIPO_OFER,
                                    NULL,
                                    '',
                                    'B' || TO_CHAR(pnOidTipoOferta)) ||
                             DECODE(MAPR_OID_MARC_PROD,
                                    NULL,
                                    '',
                                    'C' || TO_CHAR(pnOidMarcaProd)) ||
                             DECODE(NEGO_OID_NEGO,
                                    NULL,
                                    '',
                                    'D' || TO_CHAR(pnOidNegocio)) ||
                             DECODE(UNEG_OID_UNID_NEGO,
                                    NULL,
                                    '',
                                    'E' || TO_CHAR(pnOidUnidNegocio)) ||
                             DECODE(GENE_OID_GENE,
                                    NULL,
                                    '',
                                    'F' || TO_CHAR(pnOidGenerico)) ||
                             DECODE(SGEN_OID_SUPE_GENE,
                                    NULL,
                                    '',
                                    'G' || TO_CHAR(pnOidSupGenerico)) ||
                             DECODE(OFDE_OID_DETA_OFER,
                                    NULL,
                                    '',
                                    'H' || TO_CHAR(pnOidDetOferta)) AS LINEA_POS
                        FROM INC_PRODU pro, INC_PRODU_BONIF bon
                       WHERE pro.OID_PROD = bon.PRDU_OID_PROD
                         AND pro.COPA_OID_PARA_GRAL = pnOidConcurso)
               WHERE LINEA = LINEA_POS) LOOP
    
      IF (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_DESD) <=
         psCodigoPeriodo AND
         psCodigoPeriodo <=
         FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_HAST)) THEN
      
        IF (x.NUM_PUNT_UNID IS NOT NULL AND x.NUM_PUNT_UNID > 0) THEN
          lnPuntaje := lnPuntaje + (pnDemandaReal * x.NUM_PUNT_UNID);
        END IF;
      
        IF (x.VAL_FACT_MULT IS NOT NULL AND x.VAL_FACT_MULT > 0) THEN
          lnPuntaje := lnPuntaje + (pnPuntajePos * x.VAL_FACT_MULT);
        END IF;
      END IF;
    
    END LOOP;
  
    RETURN lnPuntaje;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_CALCU_PUNTA_BONIF: ' ||
                              ls_sqlerrm);
    
  END INC_FN_CALCU_PUNTA_BONIF;

  /**************************************************************************
  Descripcion       : Valida Si la Posicion cumple con las caracteristicas de
                      productos Exigidos del Concurso
  
  Fecha Creacion    : 09/08/2012
  Parametros Entrada:
    pnOidConcurso      :  oid Concurso
    pnOidProducto      :  oid Producto
    pnOidTipoOferta    :  oid Tipo Oferta
    pnOidMarcaProd     :  oid MarcaProducto
    pnOidNegocio       :  oid Negocio
    pnOidUnidNegocio    :  oid Unidad Negocio
    pnOidGenerico       :  oid Generico
    pnOidSupGenerico    :  oid SuperGenerico
    pnOidDetOferta      :  oid Detalle Oferta
    pnOidPeriodo        :  oid Periodo
    psPasoValidacion    :  '1' : OK, '0' caso contrario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PRODU_EXIGI_CONCU(pnOidConcurso    NUMBER,
                                           pnOidProducto    NUMBER,
                                           pnOidTipoOferta  NUMBER,
                                           pnOidMarcaProd   NUMBER,
                                           pnOidNegocio     NUMBER,
                                           pnOidUnidNegocio NUMBER,
                                           pnOidGenerico    NUMBER,
                                           pnOidSupGenerico NUMBER,
                                           pnOidDetOferta   NUMBER,
                                           psCodigoPeriodo  VARCHAR2,
                                           psPasoValidacion OUT VARCHAR2,
                                           pvLineaOblig     IN OUT t_lineaOblig,
                                           pvTotalOblig     IN OUT t_totalOblig) IS
    lsPasoValidacion VARCHAR2(1);
  BEGIN
  
    lsPasoValidacion := '0';
  
    FOR x IN (SELECT LINEA,
                     PERD_OID_PERI_DESD,
                     PERD_OID_PERI_HAST,
                     IND_AGRUP
                FROM (SELECT exi.PERD_OID_PERI_DESD,
                             exi.PERD_OID_PERI_HAST,
                             NVL(exi.Ind_Agrup, 'T') IND_AGRUP,
                             DECODE(PROD_OID_PROD,
                                    NULL,
                                    '',
                                    'A' || TO_CHAR(PROD_OID_PROD)) ||
                             DECODE(TOFE_OID_TIPO_OFER,
                                    NULL,
                                    '',
                                    'B' || TO_CHAR(TOFE_OID_TIPO_OFER)) ||
                             DECODE(MAPR_OID_MARC_PROD,
                                    NULL,
                                    '',
                                    'C' || TO_CHAR(MAPR_OID_MARC_PROD)) ||
                             DECODE(NEGO_OID_NEGO,
                                    NULL,
                                    '',
                                    'D' || TO_CHAR(NEGO_OID_NEGO)) ||
                             DECODE(UNEG_OID_UNID_NEGO,
                                    NULL,
                                    '',
                                    'E' || TO_CHAR(UNEG_OID_UNID_NEGO)) ||
                             DECODE(GENE_OID_GENE,
                                    NULL,
                                    '',
                                    'F' || TO_CHAR(GENE_OID_GENE)) ||
                             DECODE(SGEN_OID_SUPE_GENE,
                                    NULL,
                                    '',
                                    'G' || TO_CHAR(SGEN_OID_SUPE_GENE)) ||
                             DECODE(OFDE_OID_DETA_OFER,
                                    NULL,
                                    '',
                                    'H' || TO_CHAR(OFDE_OID_DETA_OFER)) AS LINEA,
                             DECODE(PROD_OID_PROD,
                                    NULL,
                                    '',
                                    'A' || TO_CHAR(pnOidProducto)) ||
                             DECODE(TOFE_OID_TIPO_OFER,
                                    NULL,
                                    '',
                                    'B' || TO_CHAR(pnOidTipoOferta)) ||
                             DECODE(MAPR_OID_MARC_PROD,
                                    NULL,
                                    '',
                                    'C' || TO_CHAR(pnOidMarcaProd)) ||
                             DECODE(NEGO_OID_NEGO,
                                    NULL,
                                    '',
                                    'D' || TO_CHAR(pnOidNegocio)) ||
                             DECODE(UNEG_OID_UNID_NEGO,
                                    NULL,
                                    '',
                                    'E' || TO_CHAR(pnOidUnidNegocio)) ||
                             DECODE(GENE_OID_GENE,
                                    NULL,
                                    '',
                                    'F' || TO_CHAR(pnOidGenerico)) ||
                             DECODE(SGEN_OID_SUPE_GENE,
                                    NULL,
                                    '',
                                    'G' || TO_CHAR(pnOidSupGenerico)) ||
                             DECODE(OFDE_OID_DETA_OFER,
                                    NULL,
                                    '',
                                    'H' || TO_CHAR(pnOidDetOferta)) AS LINEA_POS
                        FROM INC_PRODU pro, INC_PRODU_EXIGI exi
                       WHERE pro.OID_PROD = exi.PRDU_OID_PROD
                         AND pro.COPA_OID_PARA_GRAL = pnOidConcurso)
               WHERE LINEA = LINEA_POS) LOOP
    
      IF (FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_DESD) <=
         psCodigoPeriodo AND
         psCodigoPeriodo <=
         FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(x.PERD_OID_PERI_HAST)) THEN
      
        lsPasoValidacion := '1';
      
        IF (x.IND_AGRUP = 'T') THEN
          FOR i IN 1 .. pvLineaOblig.COUNT LOOP
            IF (x.LINEA = pvLineaOblig(i)) THEN
              pvTotalOblig(i) := pvTotalOblig(i) + 1;
            END IF;
          END LOOP;
        END IF;
      
      END IF;
    
    END LOOP;
  
    psPasoValidacion := lsPasoValidacion;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_VALID_PRODU_EXIGI_CONCU: ' ||
                              ls_sqlerrm);
    
  END INC_PR_VALID_PRODU_EXIGI_CONCU;

  /**************************************************************************
  Descripcion       : Obtiene el siguiente nivel a alcanzar por una consultora
  
  Fecha Creacion    : 12/08/2012
  Parametros Entrada:
    pnOidConcurso  : oid Concurso
    pnPuntaje      : Puntaje de Concurso
    pnPuntajeSup   : Numero de Puntaje Superior
    pnDescNivel    : Descripcion de Nivel
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTIE_SIGUI_NIVEL(pnOidConcurso NUMBER,
                                     pnPuntaje     NUMBER,
                                     pnPuntajeSup  OUT NUMBER,
                                     psDescNivel   OUT VARCHAR2) IS
  
    lnEncontrado NUMBER;
    lnPuntajeSup NUMBER;
    lsDescNivel  INC_PARAM_NIVEL_PREMI.VAL_DESC%TYPE;
  BEGIN
    lnEncontrado := 0;
  
    BEGIN
      SELECT 1, PUNTFALSUP, VAL_DESC
        INTO lnEncontrado, lnPuntajeSup, lsDescNivel
        FROM (SELECT (nivSup.NUM_CANT_INIC_PUNT - pnPuntaje) PUNTFALSUP,
                     nivSup.VAL_DESC
                FROM INC_PARAM_GENER_PREMI prem,
                     INC_PARAM_NIVEL_PREMI niv,
                     INC_PARAM_NIVEL_PREMI nivSup
               WHERE prem.COPA_OID_PARA_GRAL = pnOidConcurso
                 AND prem.TPRM_OID_TIPO_PION = 2
                 AND niv.PAGP_OID_PARA_GENE_PREM = prem.OID_PARA_GENE_PREM
                 AND pnPuntaje BETWEEN niv.NUM_CANT_INIC_PUNT AND
                     niv.NUM_CANT_FINA_PUNT
                 AND nivSup.NUM_NIVE = (niv.NUM_NIVE + 1)
                 AND nivSup.PAGP_OID_PARA_GENE_PREM =
                     prem.OID_PARA_GENE_PREM
              UNION
              SELECT (nivSup.NUM_CANT_FIJA_PUNT - pnPuntaje) PUNTFALSUP,
                     nivSup.VAL_DESC
                FROM INC_PARAM_GENER_PREMI prem,
                     INC_PARAM_NIVEL_PREMI niv,
                     INC_PARAM_NIVEL_PREMI nivSup
               WHERE prem.COPA_OID_PARA_GRAL = pnOidConcurso
                 AND prem.TPRM_OID_TIPO_PION != 2
                 AND niv.PAGP_OID_PARA_GENE_PREM = prem.OID_PARA_GENE_PREM
                 AND pnPuntaje BETWEEN niv.NUM_CANT_FIJA_PUNT AND
                     nivSup.NUM_CANT_FIJA_PUNT
                 AND nivSup.NUM_NIVE = (niv.NUM_NIVE + 1)
                 AND nivSup.PAGP_OID_PARA_GENE_PREM =
                     prem.OID_PARA_GENE_PREM);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lnEncontrado := 0;
    END;
  
    IF (lnEncontrado = 0) THEN
      BEGIN
        SELECT PUNTFALSUP, VAL_DESC
          INTO lnPuntajeSup, lsDescNivel
          FROM (SELECT (niv.num_cant_inic_punt - pnPuntaje) PUNTFALSUP,
                       niv.VAL_DESC
                  FROM inc_param_gener_premi prem, inc_param_nivel_premi niv
                 WHERE prem.copa_oid_para_gral = pnOidConcurso
                   AND prem.tprm_oid_tipo_pion = 2
                   AND niv.pagp_oid_para_gene_prem = prem.oid_para_gene_prem
                   AND niv.num_nive = 1
                   AND pnPuntaje < niv.num_cant_inic_punt
                UNION
                SELECT (niv.num_cant_fija_punt - pnPuntaje) PUNTFALSUP,
                       niv.VAL_DESC
                  FROM inc_param_gener_premi prem, inc_param_nivel_premi niv
                 WHERE prem.copa_oid_para_gral = pnOidConcurso
                   AND prem.tprm_oid_tipo_pion != 2
                   AND niv.pagp_oid_para_gene_prem = prem.oid_para_gene_prem
                   AND niv.num_nive = 1
                   AND pnPuntaje < niv.num_cant_fija_punt);
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lnPuntajeSup := 0;
          lsDescNivel  := '';
      END;
    
    END IF;
  
    pnPuntajeSup := lnPuntajeSup;
    psDescNivel  := lsDescNivel;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_OBTIE_SIGUI_NIVEL: ' ||
                              ls_sqlerrm);
    
  END INC_PR_OBTIE_SIGUI_NIVEL;

  /**************************************************************************
  Descripcion       : Obtiene los puntos faltantes para llegar a la Meta
  
  Fecha Creacion    : 13/08/2012
  Parametros Entrada:
    pnOidConcurso  : oid Concurso
    pnOidCliente   : oid Cliente
    pnPuntosMeta   : puntos Meta Faltante
    pnIncrementoReal : incremento Real
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_OBTIE_PUNTA_FALTA_METAS(pnOidConcurso    NUMBER,
                                           pnOidCliente     NUMBER,
                                           pnPuntosMeta     OUT NUMBER,
                                           pnIncrementoReal OUT NUMBER) IS
  
    lnIndDuplaCyzone INC_CONCU_PARAM_GENER.IND_DUPL_CYZO%TYPE;
    lnFactConversion INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
    lnPuntosAsignar  INC_OBTEN_PUNTO.NUM_PUNT_ASIG%TYPE;
  
    lnSaldoPuntos     NUMBER;
    lnPuntosVentaBase NUMBER;
    lnIncrementoReal  NUMBER;
    lnMontoMinimo     NUMBER;
    lnIncremento      NUMBER;
    lnMeta            NUMBER;
    lnNumNivelInc     NUMBER;
    lnPuntosMeta      NUMBER;
  BEGIN
    lnIncrementoReal := 0;
    lnMontoMinimo    := 0;
    lnIncremento     := 0;
    lnMeta           := 0;
    lnNumNivelInc    := 0;
  
    SELECT gen.IND_DUPL_CYZO, obt.VAL_FACT_CONV, obt.NUM_PUNT_ASIG
      INTO lnIndDuplaCyzone, lnFactConversion, lnPuntosAsignar
      FROM INC_CONCU_PARAM_GENER gen, INC_OBTEN_PUNTO obt
     WHERE gen.OID_PARA_GRAL = obt.COPA_OID_PARA_GRAL
       AND gen.OID_PARA_GRAL = pnOidConcurso;
  
    --Obtenemos el Saldo de Puntos x el Concurso
    SELECT SUM(NUM_PUNT)
      INTO lnSaldoPuntos
      FROM INC_CUENT_CORRI_PUNTO
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND COPA_OID_PARA_GRAL = pnOidConcurso;
  
    --Recuperar Meta
    SELECT VAL_META
      INTO lnMeta
      FROM INC_METAS_TIPO_VENTA
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND COPA_OID_PARA_GRAL = pnOidConcurso;
  
    IF (lnIndDuplaCyzone = 1) THEN
      IF (lnMeta = 0) THEN
        BEGIN
          SELECT M.IMP_MONT_MINI
            INTO lnMontoMinimo
            FROM INC_MONTO_MINIM_RANGO_CONSU M, INC_ESTAT_VENTA E
           WHERE M.ESTV_OID_ESTA_VENT = E.OID_ESTA_VENT
             AND E.COPA_OID_PARA_GRAL = pnOidConcurso
             AND E.ESTA_OID_ESTA_CLIE = 2;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            NULL;
        END;
      
        lnMeta := lnMontoMinimo;
      END IF;
    
      --Recuperar Incremento
      BEGIN
        SELECT P.NUM_NIVE, P.NUM_CANT_INIC_PUNT
          INTO lnNumNivelInc, lnIncremento
          FROM INC_PARAM_NIVEL_PREMI P, INC_PARAM_GENER_PREMI pg
         WHERE P.PAGP_OID_PARA_GENE_PREM = pg.OID_PARA_GENE_PREM
           AND pg.COPA_OID_PARA_GRAL = pnOidConcurso
           AND P.NUM_NIVE = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lnIncremento := 0;
      END;
    END IF;
  
    --CALCULAR
    lnPuntosVentaBase := ROUND(lnMeta / lnFactConversion, 0) *
                         lnPuntosAsignar;
    lnMeta            := lnMeta + lnIncremento;
  
    lnIncrementoReal := lnSaldoPuntos - lnPuntosVentaBase;
    lnPuntosMeta     := ROUND(lnMeta / lnFactConversion, 0) *
                        lnPuntosAsignar - lnSaldoPuntos;
  
    pnPuntosMeta     := lnPuntosMeta;
    pnIncrementoReal := lnIncrementoReal;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_OBTIE_PUNTA_FALTA_METAS: ' ||
                              ls_sqlerrm);
    
  END INC_PR_OBTIE_PUNTA_FALTA_METAS;

  /**************************************************************************
  Descripcion       : Reemplazo de premios en bolsa de faltantes
  
  Fecha Creacion    : 16/10/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psNumeroConcurso :  Numero de Concurso
    psCodigoVentaPremio :  Codigo Venta Premio
    pnOidReemplazo   :  Oid Reemplazo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REEMP_PREMI_BOLSA_FALTA(psCodigoPais        VARCHAR2,
                                           psNumeroConcurso    VARCHAR2,
                                           psCodigoVentaPremio VARCHAR2,
                                           pnOidReemplazo      NUMBER,
                                           psCodigoUsuario     VARCHAR2) IS
  
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsCodigoPeriodo   SEG_PERIO_CORPO.COD_PERI%TYPE;
    ldFechaFinPeriodo CRA_PERIO.FEC_FINA%TYPE;
  
    lnOidConcurso    INC_PARAM_GENER_PREMI.OID_PARA_GENE_PREM%TYPE;
    lnOidProdReemp   INC_REEMP_ARTIC_LOTE.PROD_OID_PROD%TYPE;
    lsCodVentaReemp  INC_REEMP_ARTIC_LOTE.COD_VENT_FICT%TYPE;
    lsTipoAgrupacion INC_REEMP_ARTIC_LOTE.COD_TIPO_AGRU%TYPE;
    lnPrecio         INC_REEMP_ARTIC_LOTE.IMP_PREC_PUBL%TYPE;
    lnUnidades       INC_REEMP_ARTIC_LOTE.NUM_UNID%TYPE;
  
    lsCodigoSAP    MAE_PRODU.COD_SAP%TYPE;
    lnNumeroPremio INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE;
  
    CURSOR c_Pedidos IS
      SELECT bol.clie_oid_clie,
             bol.num_prem,
             bol.oid_bols_falt,
             bol.num_unid_falt,
             bol.val_codi_vent_fict
        FROM INC_BOLSA_FALTA bol, INC_CONCU_PARAM_GENER gen
       WHERE bol.FEC_SOLU IS NULL
         AND bol.COPA_OID_PARA_GENE = gen.OID_PARA_GRAL
         AND gen.NUM_CONC = psNumeroConcurso
         AND bol.VAL_CODI_VENT_FICT = psCodigoVentaPremio
         AND INC_FN_VALID_AMBIT_GEOGR_REEMP(pnOidReemplazo,
                                            bol.clie_oid_clie) = '1';
  
    TYPE interfazPedidos IS RECORD(
      oidCliente       MAE_CLIEN.OID_CLIE%TYPE,
      numeroPremio     PED_SOLIC_CABEC.NUM_PREM%TYPE,
      oidBolsaFaltante INC_BOLSA_FALTA.OID_BOLS_FALT%TYPE,
      numUnidFaltantes INC_BOLSA_FALTA.NUM_UNID_FALT%TYPE,
      codigoVenta      INC_BOLSA_FALTA.VAL_CODI_VENT_FICT%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    ltRegSolicitud tRegSolicitud;
    ltRegPosicion  tablaRegPosicion := tablaRegPosicion();
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Obtenemos el Periodo de Proceso
    SELECT COD_PERI
      INTO lsCodigoPeriodo
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --Obtenemos el Oid Periodo
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos el Oid Concurso
    SELECT OID_PARA_GRAL
      INTO lnOidConcurso
      FROM INC_CONCU_PARAM_GENER
     WHERE NUM_CONC = psNumeroConcurso;
  
    --Obtenemos la Fecha Fin de la Campaña
    SELECT FEC_FINA
      INTO ldFechaFinPeriodo
      FROM CRA_PERIO
     WHERE OID_PERI = lnOidPeriodo;
  
    --Obtenemos el oidProducto Reemplazo y Codigo Venta Reemplazo
    SELECT PROD_OID_PROD,
           COD_VENT_FICT,
           IMP_PREC_PUBL,
           NVL(COD_TIPO_AGRU, 'I'),
           NUM_UNID
      INTO lnOidProdReemp,
           lsCodVentaReemp,
           lnPrecio,
           lsTipoAgrupacion,
           lnUnidades
      FROM INC_REEMP_ARTIC_LOTE
     WHERE OID_REEM_ARTI_LOTE = pnOidReemplazo;
  
    --Obtenemos el Codigo SAP Reemplazo y Numero Premio
    SELECT pro.COD_SAP, lpa.NUM_PREM
      INTO lsCodigoSAP, lnNumeroPremio
      FROM INC_CONCU_PARAM_GENER cpg,
           INC_PARAM_GENER_PREMI pgp,
           INC_PARAM_NIVEL_PREMI pnp,
           INC_PREMI_ARTIC       pa,
           INC_LOTE_PREMI_ARTIC  lpa,
           INC_ARTIC_LOTE        al,
           MAE_PRODU             pro,
           INC_REEMP_ARTIC_LOTE  ral
     WHERE cpg.OID_PARA_GRAL = pgp.COPA_OID_PARA_GRAL
       AND pgp.OID_PARA_GENE_PREM = pnp.PAGP_OID_PARA_GENE_PREM
       AND pnp.OID_PARA_NIVE_PREM = pa.PANP_OID_PARA_NIVE_PREM
       AND pa.OID_PREM_ARTI = lpa.PRAR_OID_PREM_ARTI
       AND lpa.OID_LOTE_PREM_ARTI = al.LOPA_OID_LOTE_PREM_ARTI
       AND al.OID_ARTI_LOTE = ral.ARLO_OID_ARTI_LOTE
       AND ral.PROD_OID_PROD = pro.OID_PROD
       AND ral.OID_REEM_ARTI_LOTE = pnOidReemplazo;
  
    --PROCESAMOS A LAS BOLSAS FALTANTES
    OPEN c_Pedidos;
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
          ltRegSolicitud.COD_PAIS      := psCodigoPais;
          ltRegSolicitud.COD_MARC      := 'T';
          ltRegSolicitud.COD_CANA      := 'VD';
          ltRegSolicitud.OID_CLIE      := interfazRecordN(x).oidCliente;
          ltRegSolicitud.COD_PERI      := lsCodigoPeriodo;
          ltRegSolicitud.FEC_PROG_FACT := ldFechaFinPeriodo;
          ltRegSolicitud.COD_OPER      := 'INC030'; --Premios Normales
          ltRegSolicitud.COD_CLAS_SOLI := 'I1';
          ltRegSolicitud.COD_TIPO_SOLI := NULL;
          ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
          ltRegSolicitud.OID_PARA_GRAL := lnOidConcurso;
          ltRegSolicitud.NUM_PREM      := lnNumeroPremio;
          ltRegSolicitud.COD_USUA      := psCodigoUsuario;
        
          ltRegSolicitud.OID_ACCE_FISI                  := NULL; --1;
          ltRegSolicitud.OID_GRUP_PROC                  := 1; --(GP1)
          ltRegSolicitud.OID_GRUP_PROC_SECU             := 1; --(GP1)
          ltRegSolicitud.NUM_CLIEN                      := 1;
          ltRegSolicitud.OID_MODU                       := 13; --Incentivos
          ltRegSolicitud.VAL_GLOS_OBSE                  := 'Reemplazo de premio ' || interfazRecordN(x)
                                                          .codigoVenta ||
                                                           ' de la bolsa de faltantes';
          ltRegSolicitud.VAL_BASE_FLET_LOCA             := 0;
          ltRegSolicitud.VAL_TOTA_PAGA_LOCA             := 0;
          ltRegSolicitud.VAL_PREC_CATA_TOTA_LOCA        := 0;
          ltRegSolicitud.VAL_BASE_FLET_DOCU             := 0;
          ltRegSolicitud.VAL_PREC_CATA_TOTA_LOC_UNI_DEM := 0;
          ltRegSolicitud.VAL_UNID_DEMA_REAL_TOTA        := lnUnidades * interfazRecordN(x)
                                                          .numUnidFaltantes;
          ltRegSolicitud.NUM_UNID_POR_ATEN_TOTA         := lnUnidades * interfazRecordN(x)
                                                          .numUnidFaltantes;
          ltRegSolicitud.VAL_PREC_CONT_TOTA_LOCA        := 0;
        
          --CREAMOS LOS DATOS DEL DETALLE DE LA SOLICITUD DE PEDIDO
          ltRegPosicion.EXTEND(1);
          ltRegPosicion(ltRegPosicion.LAST).OID_PROD := lnOidProdReemp;
          ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := lnUnidades * interfazRecordN(x)
                                                       .numUnidFaltantes;
          ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
          ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := lsCodVentaReemp;
          ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := lnUnidades * interfazRecordN(x)
                                                             .numUnidFaltantes;
          ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
          ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
          ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := lnPrecio; --PRECIO
          ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
        
          --SI ES REEMPLAZO TIPO COMPUESTA, SE RECUPERA LOS DETALLES DE REEMPLAZO RELACIONADOS
          IF (lsTipoAgrupacion = 'C') THEN
            FOR y IN (SELECT PROD_OID_PROD,
                             COD_VENT_FICT,
                             NUM_UNID,
                             IMP_PREC_PUBL
                        FROM INC_REEMP_ARTIC_LOTE
                       WHERE COMP_OID_REEM_ARTI_LOTE = pnOidReemplazo
                         AND IND_ACTI = 1) LOOP
            
              ltRegPosicion.EXTEND(1);
              ltRegPosicion(ltRegPosicion.LAST).OID_PROD := y.PROD_OID_PROD;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := y.NUM_UNID * interfazRecordN(x)
                                                           .numUnidFaltantes;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
              ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := y.COD_VENT_FICT;
              ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := y.NUM_UNID * interfazRecordN(x)
                                                                 .numUnidFaltantes;
              ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := y.IMP_PREC_PUBL; --PRECIO
              ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
            
            END LOOP;
          
          END IF;
        
          --CREAMOS LA SOLICITUD DE PEDIDO
          INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud, 'Z', ltRegPosicion);
        
          --Actualizamos la bolsa faltantes como Atendida
          UPDATE INC_BOLSA_FALTA
             SET FEC_SOLU                = SYSDATE,
                 VAL_OBSE                = 'Reemplazado por ' ||
                                           lsCodVentaReemp || ' - ' ||
                                           lsCodigoSAP,
                 SOPO_OID_SOLI_POSI_REEM = ltRegPosicion(1)
                                           .OID_SOLI_POSI_GENE,
                 USU_MODI                = psCodigoUsuario
           WHERE OID_BOLS_FALT = interfazRecordN(x).oidBolsaFaltante;
        
          --Inicializamos la lista de Posiciones de la Nueva Solicitud
          ltRegPosicion.DELETE;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
    --Actualizamos en la tabla de Reemplazos
    UPDATE INC_REEMP_ARTIC_LOTE
       SET VAL_USUA = psCodigoUsuario
     WHERE OID_REEM_ARTI_LOTE = pnOidReemplazo;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REEMP_PREMI_BOLSA_FALTA: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_REEMP_PREMI_BOLSA_FALTA;

  /**************************************************************************
  Descripcion       : Valida si el Concurso de Recomendacion es de Tipo Devolucion
  
  Fecha Creacion    : 18/12/2012
  Parametros Entrada:
    pnOidConcurso        :  oid Concurso
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  FUNCTION INC_FN_VALID_CONCU_RECOM_DEVOL(pnOidConcurso NUMBER) RETURN NUMBER IS
  
    lnResultado NUMBER;
  BEGIN
    lnResultado := 0;
  
    SELECT COUNT(1)
      INTO lnResultado
      FROM INC_CONCU_PARAM_GENER con
     WHERE con.OID_PARA_GRAL = pnOidConcurso
       AND con.IND_DEVO = 1
       AND con.BCAL_OID_BASE_CALC = 4;
  
    RETURN lnResultado;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_VALID_CONCU_RECOM_DEVOL: ' ||
                              ls_sqlerrm);
    
  END INC_FN_VALID_CONCU_RECOM_DEVOL;

  /**************************************************************************
  Descripcion       : Realiza la evaluacion de consultoras, de si no paso
                      pedidos O/C en diferentes campañas
  Fecha Creacion    : 14/02/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_EVALU_NPASO_PEDID(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnOidSecuencia     INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
    lsCodigoPeriodoPri SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoIni SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPeriodoFin SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoPri    CRA_PERIO.OID_PERI%TYPE;
    lnOcurrencias      NUMBER;
    lnCantNoPasoPedido NUMBER;
    lnNumeroPuntos     NUMBER;
    lnNumeroPuntosExig NUMBER;
    lnnumcampanas      NUMBER;
    lnIndEvCierrCamp   NUMBER;
    lnperiodosw        NUMBER;
    lnnoexisteanul     NUMBER;
  
    CURSOR c_Consultoras(oidConcurso NUMBER) IS
      SELECT DISTINCT icg.CLIE_OID_CLIE
        FROM INC_CANDI_GANAD icg
       WHERE icg.COPA_OID_PARA_GRAL = oidConcurso
         AND NOT EXISTS
       (SELECT 1
                FROM INC_DESCA ids
               WHERE icg.CLIE_OID_CLIE = ids.CLIE_OID_CLIE
                 AND ids.COPA_OID_PARA_GRAL = icg.COPA_OID_PARA_GRAL);
  
    TYPE interfazConsultoras IS RECORD(
      oidCliente MAE_CLIEN.OID_CLIE%TYPE);
  
    TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
    interfazRecordN interfazConsultorasTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    lnIndEvCierrCamp := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                            'INC',
                                                            '008'),
                            '0');
  
    --PROCESAMOS A LAS CONCURSOS VIGENTES
    FOR y IN (SELECT C.OID_PARA_GRAL,
                     FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(C.PERD_OID_PERI_DESD) COD_PERI,
                     O.NUM_CAMP_SINP_PERM,
                     O.IND_CONS,
                     O.IND_ACTI,
                     C.PERD_OID_PERI_DESD
                FROM INC_CONCU_PARAM_GENER C,
                     INC_OBTEN_PUNTO       O,
                     INC_REQUI_PREMI       R
               WHERE C.IND_ACTI = 1
                 AND C.OID_PARA_GRAL = O.COPA_OID_PARA_GRAL
                 AND C.OID_PARA_GRAL = R.COPA_OID_PARA_GRAL
                 AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(C.PERD_OID_PERI_DESD) <=
                     psCodigoPeriodo
                 AND psCodigoPeriodo <=
                     FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(C.PERD_OID_PERI_HAST)
                 AND C.BCAL_OID_BASE_CALC IN (1, 2)
                 AND NVL(R.NUM_PEDI, 0) = 0
                 AND C.PERD_OID_PERI_DESD <> C.PERD_OID_PERI_HAST
                 AND (NVL(O.NUM_CAMP_SINP_PERM, 0) >= 1 OR
                     NVL(O.IND_ACTI, 0) = 1 OR NVL(O.IND_CONS, 0) = 1)) LOOP
    
      OPEN c_Consultoras(y.OID_PARA_GRAL);
      LOOP
        FETCH c_Consultoras BULK COLLECT
          INTO interfazRecordN LIMIT W_FILAS;
        IF interfazRecordN.COUNT > 0 THEN
        
          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
            --Recuperamos periodo de Primer Pedido que seria igual al de Primer contacto
            BEGIN
              SELECT PERD_OID_PERI
                INTO lnOidPeriodoPri
                FROM MAE_CLIEN_PRIME_CONTA cpc
               WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
            
              lsCodigoPeriodoPri := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodoPri);
            
              IF (lsCodigoPeriodoPri < y.COD_PERI) THEN
                lsCodigoPeriodoPri := y.COD_PERI;
              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                lsCodigoPeriodoPri := y.COD_PERI;
                lnOidPeriodoPri    := y.PERD_OID_PERI_DESD;
            END;
          
            IF y.IND_CONS = 1 AND lnIndEvCierrCamp = 1 THEN
              -- **Para cambiar despues
            
              lnOcurrencias := 0;
              lnnumcampanas      := gen_pkg_gener.gen_fn_devue_difer_perio(lsCodigoPeriodoPri,
                                                                             psCodigoPeriodo) + 1;

              SELECT COUNT(0)
              INTO lnOcurrencias
              FROM PED_SOLIC_CABEC_ACUM2 SOC2
              WHERE FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(SOC2.PERD_OID_PERI)
                    between lsCodigoPeriodoPri
                    and psCodigoPeriodo
                    AND SOC2.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                    AND SOC2.VAL_CANT_PEDI>=1 AND NVL(SOC2.IND_PEDI_ANUL,'0')<>'1';  
        
              IF lnOcurrencias < lnnumcampanas THEN
              
                INSERT INTO INC_DESCA
                  (OID_DESC,
                   FEC_DESC,
                   CLIE_OID_CLIE,
                   COPA_OID_PARA_GRAL,
                   PERD_OID_PERI,
                   CADE_OID_CAUS_DESC)
                VALUES
                  (INC_CESC_SEQ.NEXTVAL,
                   TRUNC(SYSDATE),
                   interfazRecordN(x).oidCliente,
                   y.OID_PARA_GRAL,
                   lnOidPeriodo,
                   2); --Por falta de constancia
              
                lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
              
                SELECT SUM(NUM_PUNT), SUM(NUM_PUNT_EXIG)
                  INTO lnNumeroPuntos, lnNumeroPuntosExig
                  FROM INC_CUENT_CORRI_PUNTO
                 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND COPA_OID_PARA_GRAL = y.OID_PARA_GRAL;
              
                INSERT INTO INC_CUENT_CORRI_PUNTO
                  (OID_CUEN_CORR_PUNT,
                   NUM_MOVI,
                   NUM_PUNT,
                   NUM_PUNT_EXIG,
                   FEC_MOVI,
                   COPA_OID_PARA_GRAL,
                   CLIE_OID_CLIE,
                   PERD_OID_PERI,
                   TMOV_OID_TIPO_MOVI,
                   FEC_ULTI_ACTU,
                   VAL_DESC,
                   USU_MODI)
                VALUES
                  (lnOidSecuencia,
                   lnOidSecuencia,
                   lnNumeroPuntos * (-1),
                   lnNumeroPuntosExig * (-1),
                   TRUNC(SYSDATE),
                   y.OID_PARA_GRAL,
                   interfazRecordN(x).oidCliente,
                   lnOidPeriodo,
                   2, --Cargo
                   SYSDATE,
                   'Por Falta de Constancia',
                   psCodigoUsuario);
              END IF;
            
            ELSIF y.IND_ACTI = 1 AND lnIndEvCierrCamp = 1 THEN
              lnOcurrencias := 0;
              
                --Buscamos en el rango del concurso el status de retirada ya que con esto se rompe la actividad.
              
              FOR I IN (SELECT CRA.OID_PERI, CRA.VAL_NOMB_PERI, SOC2.OID_SOCA_ACU2
                       FROM PED_SOLIC_CABEC_ACUM2 SOC2, CRA_PERIO CRA
                       WHERE FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(cra.oid_peri)
                        between lsCodigoPeriodoPri
                        and psCodigoPeriodo
                        AND soc2.clie_oid_clie(+) = interfazRecordN(x).oidCliente
                        AND soc2.val_cant_pedi(+)>=1 AND nvl(soc2.ind_pedi_anul,'0')<>'1'
                        AND cra.oid_peri = soc2.perd_oid_peri(+)
                        ORDER BY val_nomb_peri DESC) LOOP 
                    
                          IF i.oid_soca_acu2 is null THEN
                             lnOcurrencias := lnOcurrencias + 1;
                          ELSE
                             lnOcurrencias := 0;
                          END IF;
                            
                          EXIT WHEN lnOcurrencias >= 2;  -- 2 campañas seguidas sin pedido deja de ser activa.

              END LOOP;
            
              IF lnOcurrencias >=2  THEN
              
                INSERT INTO INC_DESCA
                  (OID_DESC,
                   FEC_DESC,
                   CLIE_OID_CLIE,
                   COPA_OID_PARA_GRAL,
                   PERD_OID_PERI,
                   CADE_OID_CAUS_DESC)
                VALUES
                  (INC_CESC_SEQ.NEXTVAL,
                   TRUNC(SYSDATE),
                   interfazRecordN(x).oidCliente,
                   y.OID_PARA_GRAL,
                   lnOidPeriodo,
                   1); --Por falta de actividad
              
                lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
              
                SELECT SUM(NUM_PUNT), SUM(NUM_PUNT_EXIG)
                  INTO lnNumeroPuntos, lnNumeroPuntosExig
                  FROM INC_CUENT_CORRI_PUNTO
                 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND COPA_OID_PARA_GRAL = y.OID_PARA_GRAL;
              
                INSERT INTO INC_CUENT_CORRI_PUNTO
                  (OID_CUEN_CORR_PUNT,
                   NUM_MOVI,
                   NUM_PUNT,
                   NUM_PUNT_EXIG,
                   FEC_MOVI,
                   COPA_OID_PARA_GRAL,
                   CLIE_OID_CLIE,
                   PERD_OID_PERI,
                   TMOV_OID_TIPO_MOVI,
                   FEC_ULTI_ACTU,
                   VAL_DESC,
                   USU_MODI)
                VALUES
                  (lnOidSecuencia,
                   lnOidSecuencia,
                   lnNumeroPuntos * (-1),
                   lnNumeroPuntosExig * (-1),
                   TRUNC(SYSDATE),
                   y.OID_PARA_GRAL,
                   interfazRecordN(x).oidCliente,
                   lnOidPeriodo,
                   2, --Cargo
                   SYSDATE,
                   'Por Falta de Actividad',
                   psCodigoUsuario);
              END IF;
            ELSE
            
              IF y.NUM_CAMP_SINP_PERM >= 1 THEN
                lnCantNoPasoPedido := 0;
                lnnumcampanas      := gen_pkg_gener.gen_fn_devue_difer_perio(lsCodigoPeriodoPri,
                                                                             psCodigoPeriodo) + 1;
              
                               
                              SELECT COUNT(0)
                              INTO lnOcurrencias
                              FROM PED_SOLIC_CABEC_ACUM2 SOC2
                              WHERE FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(SOC2.PERD_OID_PERI)
                                                 between lsCodigoPeriodoPri
                                                 and psCodigoPeriodo
                               AND SOC2.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                               AND SOC2.VAL_CANT_PEDI>=1 AND NVL(SOC2.IND_PEDI_ANUL,'0')<>'1';
                   
              
                lnCantNoPasoPedido := lnnumcampanas - lnOcurrencias;
              
                IF (lnCantNoPasoPedido > y.NUM_CAMP_SINP_PERM) THEN
                  INSERT INTO INC_DESCA
                    (OID_DESC,
                     FEC_DESC,
                     CLIE_OID_CLIE,
                     COPA_OID_PARA_GRAL,
                     PERD_OID_PERI,
                     CADE_OID_CAUS_DESC)
                  VALUES
                    (INC_CESC_SEQ.NEXTVAL,
                     TRUNC(SYSDATE),
                     interfazRecordN(x).oidCliente,
                     y.OID_PARA_GRAL,
                     lnOidPeriodo,
                     3); --Excede periodos sin pedido
                
                  lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                
                  SELECT SUM(NUM_PUNT), SUM(NUM_PUNT_EXIG)
                    INTO lnNumeroPuntos, lnNumeroPuntosExig
                    FROM INC_CUENT_CORRI_PUNTO
                   WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                     AND COPA_OID_PARA_GRAL = y.OID_PARA_GRAL;
                
                  INSERT INTO INC_CUENT_CORRI_PUNTO
                    (OID_CUEN_CORR_PUNT,
                     NUM_MOVI,
                     NUM_PUNT,
                     NUM_PUNT_EXIG,
                     FEC_MOVI,
                     COPA_OID_PARA_GRAL,
                     CLIE_OID_CLIE,
                     PERD_OID_PERI,
                     TMOV_OID_TIPO_MOVI,
                     FEC_ULTI_ACTU,
                     VAL_DESC,
                     USU_MODI)
                  VALUES
                    (lnOidSecuencia,
                     lnOidSecuencia,
                     lnNumeroPuntos * (-1),
                     lnNumeroPuntosExig * (-1),
                     TRUNC(SYSDATE),
                     y.OID_PARA_GRAL,
                     interfazRecordN(x).oidCliente,
                     lnOidPeriodo,
                     2, --Cargo
                     SYSDATE,
                     'Descal. por exceder periodos sin pedido',
                     psCodigoUsuario);
                END IF;
              END IF;
            END IF;
          END LOOP;
        END IF;
        EXIT WHEN c_Consultoras%NOTFOUND;
      END LOOP;
      CLOSE c_Consultoras;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_EVALU_NPASO_PEDID: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_EVALU_NPASO_PEDID;

  /**************************************************************************
  Descripcion       : Realiza el calculo de puntajes de los pedidos O/C que se
                      encuentran en gp3.
  Fecha Creacion    : 26/03/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : CSVD
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_PUNTA_OC(psCodigoPais    VARCHAR2,
                                  psCodigoMarca   VARCHAR2,
                                  psCodigoCanal   VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  psCodigoUsuario VARCHAR2) IS
    CURSOR c_pedidos(pnOidPeriodo cra_perio.oid_peri%TYPE) IS
      SELECT cab.OID_SOLI_CABE
        FROM PED_SOLIC_CABEC     cab,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC      sol
       WHERE cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
         AND sol.COD_TIPO_SOLI = 'SOC'
         AND cab.GRPR_OID_GRUP_PROC = 4
         AND PERD_OID_PERI = pnOidPeriodo;
  
    --se define un tipo de dato tipo Tabla de Registros de los pedidos
    TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
    --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
    pedidoReg RegTab;
  
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
    OPEN c_pedidos(lnOidPeriodo);
    LOOP
      FETCH c_pedidos BULK COLLECT
        INTO pedidoReg LIMIT W_FILAS;
    
      IF pedidoReg.COUNT > 0 THEN
        FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
          --Calcula el Puntaje por Orden de Compra
          INC_PKG_PROCE_INCEN.INC_PR_CALCU_PUNTA_CONSU(psCodigoPais,
                                                       pedidoReg(x)
                                                       .OID_SOLI_CABE,
                                                       psCodigoUsuario);
        END LOOP;
      END IF;
    
      EXIT WHEN c_pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_PUNTA_OC: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_CALCU_PUNTA_OC;

  /**************************************************************************
  Descripcion        : Proceso de reversión de devoluciones de ciclo de pedidos
  
  Fecha Creacion      : 22/04/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    pnOidSolicitud   :  Oid Solicitud
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor              : Sebastian Guerra
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_CARGO_DEVOL(psCodigoPais    VARCHAR2,
                                     pnOidSolicitud  NUMBER,
                                     psCodigoUsuario VARCHAR2) IS
  
    CURSOR c_ConcursosDevolucion(oidSolicitud NUMBER, oidCliente NUMBER) IS
      SELECT num_punt, num_punt_exig, copa_oid_para_gral
        FROM inc_solic_concu_punta
       WHERE soca_oid_soli_cabe = oidSolicitud
         AND clie_oid_clie = oidCliente
         AND ind_anul = 1;
  
    lnOidCliente      ped_solic_cabec.clie_oid_clie%TYPE;
    lsCodTipoSoli     ped_tipo_solic.cod_tipo_soli%TYPE;
    lsCodPeri         bas_ctrl_fact.cod_peri%TYPE;
    ldFecProc         bas_ctrl_fact.fec_proc%TYPE;
    lnnumpunt         inc_solic_concu_punta.num_punt%TYPE;
    lnnumpuntexig     inc_solic_concu_punta.num_punt_exig%TYPE;
    lncopaoidparagral inc_solic_concu_punta.copa_oid_para_gral%TYPE;
    lnUltCamDespa     inc_param_gener_premi.perd_oid_peri%TYPE;
  
    lbProcesoOK  boolean;
    lnActivos    number;
    lnOidCodPeri number;
  
  BEGIN
    --Obtenemos datos de la Solicitud
    SELECT psc.clie_oid_clie, sol.cod_tipo_soli
      INTO lnOidCliente, lsCodTipoSoli
      FROM ped_solic_cabec psc, ped_tipo_solic_pais tsp, ped_tipo_solic sol
     WHERE psc.oid_soli_cabe = pnOidSolicitud
       AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = sol.oid_tipo_soli;
  
    IF lsCodTipoSoli = 'SDDN' THEN
      SELECT cod_peri, fec_proc
        INTO lsCodPeri, ldFecProc
        FROM bas_ctrl_fact
       WHERE cod_pais = psCodigoPais
         AND sta_camp = 0
         AND ind_camp_act = 1;
    
      OPEN c_ConcursosDevolucion(pnOidSolicitud, lnOidCliente);
      LOOP
        FETCH c_ConcursosDevolucion
          INTO lnnumpunt, lnnumpuntexig, lncopaoidparagral;
        EXIT WHEN c_ConcursosDevolucion%NOTFOUND;
      
        lbProcesoOK := false;
      
        SELECT COUNT(*)
          INTO lnActivos
          FROM inc_concu_param_gener
         WHERE ind_acti = 1
           AND oid_para_gral = lncopaoidparagral;
      
        IF lnActivos > 0 THEN
        
          SELECT perd_oid_peri
            INTO lnUltCamDespa
            FROM inc_param_gener_premi
           WHERE copa_oid_para_gral = lncopaoidparagral;
        
          lnOidCodPeri := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCodPeri);
        
          IF lnUltCamDespa IS NOT NULL THEN
            IF lnOidCodPeri <= lnUltCamDespa THEN
              lbProcesoOK := true;
            END IF;
          ELSE
            lbProcesoOK := true;
          END IF;
        
          IF lbProcesoOK THEN
            INSERT INTO inc_cuent_corri_punto
              (OID_CUEN_CORR_PUNT,
               NUM_MOVI,
               NUM_PUNT,
               NUM_PUNT_EXIG,
               FEC_MOVI,
               COPA_OID_PARA_GRAL,
               CLIE_OID_CLIE,
               PERD_OID_PERI,
               TMOV_OID_TIPO_MOVI,
               FEC_ULTI_ACTU,
               VAL_DESC,
               USU_MODI)
            VALUES
              (INC_CUCP_SEQ.NEXTVAL,
               INC_CUCP_SEQ.NEXTVAL,
               abs(lnnumpunt),
               abs(lnnumpuntexig),
               ldFecProc,
               lncopaoidparagral,
               lnOidCliente,
               lnOidCodPeri,
               1,
               SYSDATE,
               'Abono por reversión de Devolución (REC)',
               psCodigoUsuario);
          END IF;
        END IF;
      END LOOP;
      CLOSE c_ConcursosDevolucion;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END INC_PR_REVER_CARGO_DEVOL;

  /**************************************************************************
  Descripcion       : Genera cupones electrónicos en base a los resultados de
                      un concurso.
  Fecha Creacion    : 27/05/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_CUPON_ELECT(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    ldFechaProceso BAS_CTRL_FACT.FEC_PROC%TYPE;
    lnOidSecuencia INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
  
    lnSaldoPuntos    NUMBER;
    lnSaldoPuntosExi NUMBER;
    lnCupon          NUMBER;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT psc.OID_SOLI_CABE, psc.CLIE_OID_CLIE, cli.COD_CLIE
        FROM PED_SOLIC_CABEC     psc,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC      ts,
             MAE_CLIEN           cli
       WHERE psc.PAIS_OID_PAIS = oidPais
         AND psc.PERD_OID_PERI = oidPeriodo
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND ts.COD_TIPO_SOLI = 'SOC'
         AND psc.CLIE_OID_CLIE = cli.OID_CLIE
         AND psc.FEC_FACT IS NULL
         AND psc.GRPR_OID_GRUP_PROC = 4;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud  PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente    MAE_CLIEN.OID_CLIE%TYPE,
      codigoCliente MAE_CLIEN.COD_CLIE%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos la Fecha de Proceso
    SELECT FEC_PROC
      INTO ldFechaProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --PROCESAMOS A LOS PEDIDOS DE GP4
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnCupon := 0;
        
          FOR y IN (SELECT GEN.OID_PARA_GRAL,
                           ELE.NUM_CONC,
                           ELE.VAL_ACTU_CUPO
                      FROM INC_PARAM_CUPON_ELECT ELE,
                           INC_CONCU_PARAM_GENER GEN
                     WHERE ELE.PAIS_COD_PAIS = psCodigoPais
                       AND ELE.NUM_CONC = GEN.NUM_CONC
                       AND GEN.IND_ACTI = 1
                       AND ELE.COD_PERI_PROC <= psCodigoPeriodo
                       AND psCodigoPeriodo <= ELE.COD_PERI_PROC_FINA
                       AND ELE.IND_ACTI = 1
                       AND ELE.EST_REGI = 1) LOOP
          
            SELECT NVL(SUM(NUM_PUNT), 0), NVL(SUM(NUM_PUNT_EXIG), 0)
              INTO lnSaldoPuntos, lnSaldoPuntosExi
              FROM INC_CUENT_CORRI_PUNTO
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND COPA_OID_PARA_GRAL = y.OID_PARA_GRAL;
          
            IF (lnSaldoPuntos > 0) THEN
              FOR k IN 1 .. lnSaldoPuntos LOOP
                INSERT INTO INC_CUPON_ELECT
                  (PAIS_COD_PAIS,
                   NUM_CONC,
                   NUM_CUPO,
                   COD_CLIE,
                   COD_PERI_PROC,
                   FEC_FACT,
                   USU_CREA,
                   FEC_CREA)
                VALUES
                  (psCodigoPais,
                   y.NUM_CONC,
                   y.VAL_ACTU_CUPO + k,
                   interfazRecordN(x).codigoCliente,
                   psCodigoPeriodo,
                   ldFechaProceso,
                   psCodigoUsuario,
                   SYSDATE);
              END LOOP;
            
              UPDATE INC_PARAM_CUPON_ELECT
                 SET VAL_ACTU_CUPO = y.VAL_ACTU_CUPO + lnSaldoPuntos,
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND NUM_CONC = y.NUM_CONC;
            
              lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
            
              INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI)
              VALUES
                (lnOidSecuencia,
                 lnOidSecuencia,
                 lnSaldoPuntos * (-1),
                 lnSaldoPuntosExi * (-1),
                 ldFechaProceso,
                 y.OID_PARA_GRAL,
                 interfazRecordN(x).oidCliente,
                 lnOidPeriodo,
                 2,
                 SYSDATE,
                 'Redención de puntos por Cupones Electrónicos',
                 psCodigoUsuario);
            
            END IF;
          
          END LOOP;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_CUPON_ELECT: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_GENER_CUPON_ELECT;

  /**************************************************************************
  Descripcion       : Revierte la entrega de premios si se ha incumplido las
                      exigencias de puntos por nivel y campaña
  Fecha Creacion    : 13/06/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_ENTRE_PREMI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoExi CRA_PERIO.OID_PERI%TYPE;
  
    lbValido         BOOLEAN;
    lnSaldoPuntos    NUMBER;
    lnOidCuenCorr    INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
    lnOidPeriodoCorp SEG_PERIO_CORPO.OID_PERI%TYPE;
    lnOidPeriodoIni  CRA_PERIO.OID_PERI%TYPE;
    ldFechaFin       CRA_PERIO.FEC_FINA%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT psc.OID_SOLI_CABE,
             psc.CLIE_OID_CLIE,
             psc.COPA_OID_PARA_GENE,
             con.NUM_CONC
        FROM PED_SOLIC_CABEC psc,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC ts,
             (SELECT DISTINCT a.OID_PARA_GRAL, a.NUM_CONC
                FROM INC_CONCU_PARAM_GENER       a,
                     INC_PARAM_GENER_PREMI       b,
                     INC_PARAM_NIVEL_PREMI_PUNEX c
               WHERE a.OID_PARA_GRAL = b.COPA_OID_PARA_GRAL
                 AND a.BCAL_OID_BASE_CALC IN (1, 2)
                 AND a.IND_ACTI = 1
                 AND b.NUM_NIVE = 1
                 AND ((oidPeriodo BETWEEN b.PERD_OID_PERI_INIC AND
                     b.PERD_OID_PERI) AND b.PERD_OID_PERI_INIC IS NOT NULL)
                  OR (b.PERD_OID_PERI = oidPeriodo AND
                     b.PERD_OID_PERI_INIC IS NULL)
                 AND a.NUM_CONC = c.NUM_CONC
                 AND c.EST_REGI = 1
                 AND c.NUM_NIVE = 1) con
       WHERE psc.PAIS_OID_PAIS = oidPais
         AND psc.PERD_OID_PERI = oidPeriodo
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND ts.COD_TIPO_SOLI = 'SINC'
         AND psc.VAL_GLOS_OBSE <> 'GENERADO POR ANULACION'
         AND psc.GRPR_OID_GRUP_PROC = 3
         AND psc.COPA_OID_PARA_GENE = con.OID_PARA_GRAL;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud   PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      oidConcurso    INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Recuperamos el oid Periodo Corporativo
    SELECT cor.OID_PERI
      INTO lnOidPeriodoCorp
      FROM SEG_PERIO_CORPO cor, CRA_PERIO cra
     WHERE cor.OID_PERI = Cra.PERI_OID_PERI
       and cra.OID_PERI = lnOidPeriodo;
  
    SELECT OID_PERI, FEC_FINA
      INTO lnOidPeriodoIni, ldFechaFin
      FROM (SELECT OID_PERI, FEC_FINA FROM CRA_PERIO ORDER BY FEC_INIC)
     WHERE ROWNUM = 1;
  
    --PROCESAMOS A LOS PEDIDOS DE GP3
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lbValido := TRUE;
        
          FOR y IN (SELECT a.OID_PARA_GRAL,
                           a.NUM_CONC,
                           c.CAM_EXIG,
                           c.PUN_EXIG
                      FROM INC_CONCU_PARAM_GENER       a,
                           INC_PARAM_NIVEL_PREMI_PUNEX c
                     WHERE a.NUM_CONC = c.NUM_CONC
                       AND a.OID_PARA_GRAL = interfazRecordN(x).oidConcurso
                       AND c.EST_REGI = 1
                       AND c.NUM_NIVE = 1
                     ORDER BY c.CAM_EXIG) LOOP
          
            lnOidPeriodoExi := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(y.CAM_EXIG,
                                                                          lnOidMarca,
                                                                          lnOidCanal);
          
            SELECT NVL(SUM(NUM_PUNT), 0)
              INTO lnSaldoPuntos
              FROM INC_CUENT_CORRI_PUNTO
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
               AND DECODE(PERD_OID_PERI_REFE,
                          NULL,
                          PERD_OID_PERI,
                          PERD_OID_PERI_REFE) = lnOidPeriodoExi -- Ajuste HRG 14/01/2014
                  --             AND PERD_OID_PERI = lnOidPeriodoExi  Eliminado HRG 14/01/2014
               AND upper(val_desc) not like '%ENTREGA DE PREMIO%';
          
            IF (lnSaldoPuntos < y.PUN_EXIG) THEN
              lbValido := FALSE;
            END IF;
          
          END LOOP;
        
          --Si no cumplio con la Exigencia de Campañas, hacemos la Reversa del Premio
          IF (NOT lbValido) THEN
          
            --Cambiamos el periodo y fecha de programacion de la solicitud
            UPDATE PED_SOLIC_CABEC
               SET PERD_OID_PERI      = lnOidPeriodoIni,
                   FEC_PROG_FACT      = ldFechaFin,
                   FEC_PROG_FACT_COMP = TO_NUMBER(TO_CHAR(ldFechaFin,
                                                          'YYYYMMDD'))
             WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
          
            --Eliminamos el premio de la tabla de Cuenta Corriente de Puntos
            BEGIN
              SELECT OID_CUEN_CORR_PUNT
                INTO lnOidCuenCorr
                FROM (SELECT OID_CUEN_CORR_PUNT
                        FROM INC_CUENT_CORRI_PUNTO
                       WHERE COPA_OID_PARA_GRAL = interfazRecordN(x)
                            .oidConcurso
                         AND PERD_OID_PERI = lnOidPeriodo
                         AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                         AND TMOV_OID_TIPO_MOVI = 2
                         AND upper(val_desc) like '%ENTREGA DE PREMIO%'
                       ORDER BY OID_CUEN_CORR_PUNT DESC)
               WHERE ROWNUM = 1;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                lnOidCuenCorr := NULL;
            END;
          
            IF (lnOidCuenCorr IS NOT NULL) THEN
              DELETE FROM INC_CUENT_CORRI_PUNTO
               WHERE OID_CUEN_CORR_PUNT = lnOidCuenCorr;
            END IF;
          
            --Actualizamos la tabla de Candidata Ganadoras
            UPDATE INC_CANDI_GANAD
               SET VAL_REQU_PREM_SUPE = 0,
                   BINC_OID_BASE_INCU = NULL,
                   PERD_OID_PERI_EVAL = NULL
             WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
               AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
          
            --Eliminamos registro de la tabla de Ganadoras
            DELETE FROM INC_GANAD
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND PERD_OID_PERI = lnOidPeriodo
               AND SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
          
            --Eliminamos registro del Buzon de Mensajes
            DELETE FROM MSG_BUZON_MENSA
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND PERI_OID_PERI = lnOidPeriodoCorp
               AND DATO_VARI_17 = interfazRecordN(x).numeroConcurso;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REVER_ENTRE_PREMI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REVER_ENTRE_PREMI;

  /**************************************************************************
  Descripcion       : Revierte la entrega de premios si se ha sobrepasado el
                      límite final del último nivel del concurso
  Fecha Creacion    : 18/06/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REVER_ENTRE_PREMI_ULTIM(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnOcurrencias    NUMBER;
    lnSaldoPuntos    NUMBER;
    lnOidCuenCorr    INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
    lnOidPeriodoCorp SEG_PERIO_CORPO.OID_PERI%TYPE;
    lnOidPeriodoIni  CRA_PERIO.OID_PERI%TYPE;
    ldFechaFin       CRA_PERIO.FEC_FINA%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT psc.OID_SOLI_CABE,
             psc.CLIE_OID_CLIE,
             psc.COPA_OID_PARA_GENE,
             con.NUM_CONC,
             con.NUM_CANT_FINA_PUNT
        FROM PED_SOLIC_CABEC psc,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC ts,
             (SELECT a.OID_PARA_GRAL,
                     a.NUM_CONC,
                     MAX(c.NUM_CANT_FINA_PUNT) NUM_CANT_FINA_PUNT
                FROM inc_concu_param_gener a,
                     inc_param_gener_premi b,
                     inc_param_nivel_premi c
               WHERE a.oid_para_gral = b.copa_oid_para_gral
                 AND oid_para_gene_prem = pagp_oid_para_gene_prem
                 AND ind_nive_rota = 0
                 AND ind_acti = 1
                 AND TPRM_OID_TIPO_PION = 2
                 AND bcal_oid_base_calc = 1
                 AND (((oidPeriodo BETWEEN b.perd_oid_peri_inic AND
                     b.perd_oid_peri) AND b.perd_oid_peri_inic IS NOT NULL) OR
                     (b.perd_oid_peri = oidPeriodo AND
                     b.perd_oid_peri_inic IS NULL))
               GROUP BY a.OID_PARA_GRAL, a.NUM_CONC
              HAVING MAX(NUM_CANT_FINA_PUNT) < 999999) con
       WHERE psc.PAIS_OID_PAIS = oidPais
         AND psc.PERD_OID_PERI = oidPeriodo
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND ts.COD_TIPO_SOLI = 'SINC'
         AND psc.VAL_GLOS_OBSE <> 'GENERADO POR ANULACION'
         AND psc.GRPR_OID_GRUP_PROC = 3
         AND psc.COPA_OID_PARA_GENE = con.OID_PARA_GRAL;
  
    TYPE interfazPedidos IS RECORD(
      oidSolicitud   PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidCliente     MAE_CLIEN.OID_CLIE%TYPE,
      oidConcurso    INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
      maximoNivel    INC_PARAM_NIVEL_PREMI.NUM_CANT_FINA_PUNT%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Verificamos si existen concursos configurados para el proceso, sino termina el caso de uso
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM (SELECT a.OID_PARA_GRAL,
                   a.NUM_CONC,
                   MAX(c.NUM_CANT_FINA_PUNT) NUM_CANT_FINA_PUNT
              FROM inc_concu_param_gener a,
                   inc_param_gener_premi b,
                   inc_param_nivel_premi c
             WHERE a.oid_para_gral = b.copa_oid_para_gral
               AND oid_para_gene_prem = pagp_oid_para_gene_prem
               AND ind_nive_rota = 0
               AND ind_acti = 1
               AND TPRM_OID_TIPO_PION = 2
               AND bcal_oid_base_calc = 1
               AND (((lnOidPeriodo BETWEEN b.perd_oid_peri_inic AND
                   b.perd_oid_peri) AND b.perd_oid_peri_inic IS NOT NULL) OR
                   (b.perd_oid_peri = lnOidPeriodo AND
                   b.perd_oid_peri_inic IS NULL))
             GROUP BY a.OID_PARA_GRAL, a.NUM_CONC
            HAVING MAX(NUM_CANT_FINA_PUNT) < 999999);
  
    IF (lnOcurrencias = 0) THEN
      RETURN;
    END IF;
  
    --Recuperamos el oid Periodo Corporativo
    SELECT cor.OID_PERI
      INTO lnOidPeriodoCorp
      FROM SEG_PERIO_CORPO cor, CRA_PERIO cra
     WHERE cor.OID_PERI = Cra.PERI_OID_PERI
       and cra.OID_PERI = lnOidPeriodo;
  
    SELECT OID_PERI, FEC_FINA
      INTO lnOidPeriodoIni, ldFechaFin
      FROM (SELECT OID_PERI, FEC_FINA FROM CRA_PERIO ORDER BY FEC_INIC)
     WHERE ROWNUM = 1;
  
    --PROCESAMOS A LOS PEDIDOS DE GP3
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          --Se ubica el cargo por Entrega de Premio
          BEGIN
            SELECT OID_CUEN_CORR_PUNT
              INTO lnOidCuenCorr
              FROM (SELECT OID_CUEN_CORR_PUNT
                      FROM INC_CUENT_CORRI_PUNTO
                     WHERE COPA_OID_PARA_GRAL = interfazRecordN(x)
                          .oidConcurso
                       AND PERD_OID_PERI = lnOidPeriodo
                       AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                       AND upper(val_desc) like '%ENTREGA DE PREMIO%'
                     ORDER BY OID_CUEN_CORR_PUNT DESC)
             WHERE ROWNUM = 1;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnOidCuenCorr := 0;
          END;
        
          --Luego se debe totalizar el puntaje sin el cargo obtenido en el paso anterior
          SELECT NVL(SUM(NUM_PUNT), 0)
            INTO lnSaldoPuntos
            FROM INC_CUENT_CORRI_PUNTO
           WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
             AND OID_CUEN_CORR_PUNT <> lnOidCuenCorr;
        
          --Si el saldo anterior sobrepasa el limite maximo del Concurso,
          --hacemos la Reversa del Premio
          IF (lnSaldoPuntos > interfazRecordN(x).maximoNivel) THEN
          
            --Cambiamos el periodo y fecha de programacion de la solicitud
            UPDATE PED_SOLIC_CABEC
               SET PERD_OID_PERI      = lnOidPeriodoIni,
                   FEC_PROG_FACT      = ldFechaFin,
                   FEC_PROG_FACT_COMP = TO_NUMBER(TO_CHAR(ldFechaFin,
                                                          'YYYYMMDD'))
             WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
          
            DELETE FROM INC_CUENT_CORRI_PUNTO
             WHERE OID_CUEN_CORR_PUNT = lnOidCuenCorr;
          
            --Actualizamos la tabla de Candidata Ganadoras
            UPDATE INC_CANDI_GANAD
               SET BINC_OID_BASE_INCU = 2
             WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
               AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;
          
            --Eliminamos registro de la tabla de Ganadoras
            DELETE FROM INC_GANAD
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND PERD_OID_PERI = lnOidPeriodo
               AND SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
          
            --Eliminamos registro del Buzon de Mensajes
            DELETE FROM MSG_BUZON_MENSA
             WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND PERI_OID_PERI = lnOidPeriodoCorp
               AND DATO_VARI_17 = interfazRecordN(x).numeroConcurso;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REVER_ENTRE_PREMI_ULTIM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REVER_ENTRE_PREMI_ULTIM;

  /**************************************************************************
  Descripcion       : Despacho de premios pendientes de despacho de concursos
                      para ti para mi.
  
  Fecha Creacion    : 28/08/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_DESPA_PREMI_PTIMI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
  
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnOidCabecera     PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
    lsNumeroSolicitud PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
    lsNumeroFormato   PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
    ldFechaProgFact   CRA_CRONO.FEC_INIC%TYPE;
  
    lnOidEstadoPosicion  PED_ESTAD_POSIC.OID_ESTA_POSI%TYPE;
    lnContadorPosiciones NUMBER;
  
    CURSOR cursorPedidos(oidPeriodo NUMBER) IS
      SELECT cc.CLIE_OID_CLIE,
             cc.COPA_OID_PARA_GRAL,
             PUNGANA,
             SUM(NUM_PUNT) / COUNT(DISTINCT sc.OID_SOLI_CABE)
        FROM INC_CUENT_CORRI_PUNTO cc,
             (SELECT DISTINCT a.OID_PARA_GRAL, d.NUM_CANT_INIC_PUNT pungana
                FROM INC_CONCU_PARAM_GENER a,
                     INC_CONCU_PARAM_CONSU b,
                     INC_PARAM_GENER_PREMI c,
                     INC_PARAM_NIVEL_PREMI d,
                     INC_PREMI_ARTIC       e,
                     INC_LOTE_PREMI_ARTIC  f,
                     INC_ARTIC_LOTE        g
               WHERE a.OID_PARA_GRAL = b.COPA_CONC_PUNT_RECO
                 AND a.IND_ACTI = 1
                 AND a.BCAL_OID_BASE_CALC in (1, 2)
                 AND a.OID_PARA_GRAL = c.COPA_OID_PARA_GRAL
                 AND d.NUM_NIVE = 1
                 AND d.PAGP_OID_PARA_GENE_PREM = c.OID_PARA_GENE_PREM
                 AND d.OID_PARA_NIVE_PREM = e.PANP_OID_PARA_NIVE_PREM
                 AND f.PRAR_OID_PREM_ARTI = e.OID_PREM_ARTI
                 AND g.IND_DESP = 1
                 AND f.OID_LOTE_PREM_ARTI = g.LOPA_OID_LOTE_PREM_ARTI
                 AND (c.PERD_OID_PERI IS NULL OR
                     c.PERD_OID_PERI = oidPeriodo or
                     ((oidPeriodo BETWEEN c.PERD_OID_PERI_INIC AND
                     c.PERD_OID_PERI) AND c.PERD_OID_PERI_INIC IS NOT NULL))) cp,
             PED_SOLIC_CABEC sc,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC ts
       WHERE sc.PERD_OID_PERI = oidPeriodo
         AND sc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND ts.COD_TIPO_SOLI = 'SOC'
         AND sc.GRPR_OID_GRUP_PROC = 4
         AND sc.FEC_FACT IS NULL
         AND cc.CLIE_OID_CLIE = sc.CLIE_OID_CLIE
         AND cc.COPA_OID_PARA_GRAL = cp.OID_PARA_GRAL
         AND NOT EXISTS
       (SELECT 1
                FROM INC_CANDI_GANAD gan
               WHERE gan.COPA_OID_PARA_GRAL = cp.OID_PARA_GRAL
                 AND gan.CLIE_OID_CLIE = cc.CLIE_OID_CLIE
                 AND gan.VAL_REQU_PREM_SUPE = 1)
       GROUP BY cc.CLIE_OID_CLIE, cc.COPA_OID_PARA_GRAL, cp.PUNGANA
      HAVING SUM(NUM_PUNT) / COUNT(DISTINCT sc.OID_SOLI_CABE) >= PUNGANA
       ORDER BY cc.CLIE_OID_CLIE, cc.COPA_OID_PARA_GRAL;
  
    TYPE interfazPedidos IS RECORD(
      oidCliente      INC_CUENT_CORRI_PUNTO.CLIE_OID_CLIE%TYPE,
      oidConcurso     INC_CUENT_CORRI_PUNTO.COPA_OID_PARA_GRAL%TYPE,
      puntajeGanadora INC_PARAM_NIVEL_PREMI.NUM_CANT_INIC_PUNT%TYPE,
      numeroPuntos    NUMBER(12));
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    lnOidOperacion      BEL_OPERA.OID_OPER%TYPE;
    lnOidClaseSolicitud PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
  
    lnOidTipoSoliPais       PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
    lnOidTipoPosi           PED_TIPO_SOLIC_PROCE.TPOS_OID_TIPO_POSI%TYPE;
    lnOidSubTipoPosi        PED_TIPO_SOLIC_PROCE.STPO_OID_SUBT_POSI%TYPE;
    lnOidAcceso             PED_TIPO_SOLIC.ACCE_OID_ACCE%TYPE;
    lnOidSubAcceso          PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
    lnOidTipoCliente        PED_TIPO_SOLIC.TICL_OID_TIPO_CLIE%TYPE;
    lnOidActividad          PED_TIPO_SOLIC_PAIS.CACT_OID_ACTI%TYPE;
    lnOidFormaPago          PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
    lnIndPedidoPrueba       PED_TIPO_SOLIC_PAIS.IND_PEDI_PRUE%TYPE;
    lnIndPermitirUnion      PED_TIPO_SOLIC_PAIS.IND_PERM_UNIO%TYPE;
    lnOidTipoCons           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
    lnOidTipoDocumentoLegal PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
    lnOidSociedad           PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
    lnOidMoneda             PED_TIPO_SOLIC_PAIS.MONE_OID_MONE%TYPE;
  
    lnOidSolicitud   PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
    lnNumeroPremio   INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE;
    lnOidNivelPremio INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE;
    lnTotalUnidades  NUMBER;
    lnOidCuenta      NUMBER;
    lnOidPrograma    PED_SOLIC_CABEC.ICTP_OID_TIPO_PROG%TYPE;
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal, Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Recupermos el Oid Estado de la Posicion
    SELECT OID_ESTA_POSI
      INTO lnOidEstadoPosicion
      FROM PED_ESTAD_POSIC
     WHERE COD_ESTA_POSI = 'CO';
  
    --Obtenemos la Fecha Programada de Facturacion
    SELECT FEC_FINA
      INTO ldFechaProgFact
      FROM CRA_PERIO
     WHERE OID_PERI = lnOidPeriodo;
  
    --RECUPERAMOS INFORMACION DE LAS TABLAS NECESARIAS
    --PARA LA GENERACION DE LAS SOLICITUDES DE PEDIDO
    SELECT OID_OPER
      INTO lnOidOperacion
      FROM BEL_OPERA
     WHERE COD_OPER = 'INC020';
  
    SELECT OID_CLAS_SOLI
      INTO lnOidClaseSolicitud
      FROM PED_CLASE_SOLIC
     WHERE COD_CLAS_SOLI = 'I1';
  
    --PROCESAMOS A LAS BOLSAS FALTANTES
    OPEN cursorPedidos(lnOidPeriodo);
    LOOP
      FETCH cursorPedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
    
      IF interfazRecordN.COUNT > 0 THEN
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          --Recuperamos oid Solicitud
          SELECT sc.OID_SOLI_CABE
            INTO lnOidSolicitud
            FROM PED_SOLIC_CABEC     sc,
                 PED_TIPO_SOLIC_PAIS tsp,
                 PED_TIPO_SOLIC      ts
           WHERE sc.PERD_OID_PERI = lnOidPeriodo --oidPeriodo
             AND sc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND sc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
             AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
             AND ts.COD_TIPO_SOLI = 'SOC'
             AND sc.GRPR_OID_GRUP_PROC = 4
             AND sc.FEC_FACT IS NULL
             AND ROWNUM = 1;
        
          --Recuperamos el tipo de programa del concurso
          SELECT ICTP_OID_TIPO_PROG
            INTO lnOidPrograma
            FROM INC_CONCU_PARAM_GENER
           WHERE OID_PARA_GRAL = interfazRecordN(x).oidConcurso;
        
          --Recuperamos datos relacionado al tipo de Solicitud de Incentivos
          SELECT tsp.OID_TIPO_SOLI_PAIS,
                 pro.TPOS_OID_TIPO_POSI,
                 pro.STPO_OID_SUBT_POSI,
                 sol.ACCE_OID_ACCE,
                 sol.SBAC_OID_SBAC,
                 sol.TICL_OID_TIPO_CLIE,
                 tsp.CACT_OID_ACTI,
                 tsp.FOPA_OID_FORM_PAGO,
                 tsp.IND_PEDI_PRUE,
                 tsp.IND_PERM_UNIO,
                 tsp.TSOL_OID_TIPO_CONS,
                 tsp.TIDO_OID_TIPO_DOCU,
                 tsp.SOCI_OID_SOCI,
                 tsp.MONE_OID_MONE
            INTO lnOidTipoSoliPais,
                 lnOidTipoPosi,
                 lnOidSubTipoPosi,
                 lnOidAcceso,
                 lnOidSubAcceso,
                 lnOidTipoCliente,
                 lnOidActividad,
                 lnOidFormaPago,
                 lnIndPedidoPrueba,
                 lnIndPermitirUnion,
                 lnOidTipoCons,
                 lnOidTipoDocumentoLegal,
                 lnOidSociedad,
                 lnOidMoneda
            FROM PED_TIPO_SOLIC_PROCE pro,
                 PED_TIPO_SOLIC_PAIS  tsp,
                 PED_TIPO_SOLIC       sol,
                 MAE_TIPO_CLIEN       tip
           WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
             AND pro.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
             AND sol.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
             AND tip.COD_TIPO_CLIE = '02' --Consultoras
             AND tsp.PAIS_OID_PAIS = lnOidPais
             AND sol.MARC_OID_MARC = lnOidMarca
             AND sol.CLSO_OID_CLAS_SOLI = lnOidClaseSolicitud
             AND pro.OPER_OID_OPER = lnOidOperacion;
        
          -- obtenemos el siguiente numero de Secuencia
          SELECT PED_SOCA_SEQ.NEXTVAL INTO lnOidCabecera FROM DUAL;
        
          --Obtenemos el Numero de Solicitud
          lsNumeroSolicitud := STO_PKG_GENER.STO_FN_RESRV_SECUE_NSOLI(pscodigopais,
                                                                      'PED001',
                                                                      '000',
                                                                      0);
        
          lsNumeroFormato := to_char(SYSDATE, 'YY') ||
                             lpad(lsNumeroSolicitud, 8, '0') + 1;
        
          INSERT INTO PED_SOLIC_CABEC
            (ACFI_OID_ACCE_FISI,
             ALMC_OID_ALMA,
             CLDI_OID_CLIE_DIRE,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_DEST,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CONS_ASOC,
             CLSO_OID_CLAS_SOLI,
             COPA_OID_PARA_GENE,
             ESSO_OID_ESTA_SOLI,
             FEC_CRON,
             FEC_PROG_FACT,
             FOPA_OID_FORM_PAGO,
             GRPR_OID_GRUP_PROC,
             IND_OC,
             IND_PEDI_PRUE,
             IND_PERM_UNIO_SOL,
             IND_TS_NO_CONSO,
             MODU_OID_MODU,
             MONE_OID_MONE,
             NUM_CLIEN,
             NUM_DOCU_ORIG,
             NUM_PREM,
             OID_SOLI_CABE,
             OPER_OID_OPER,
             PAIS_OID_PAIS,
             PERD_OID_PERI,
             PROC_OID_PROC,
             SBAC_OID_SBAC,
             SBTI_OID_SUBT_CLIE,
             SOCA_OID_DOCU_REFE,
             SOCI_OID_SOCI,
             TDOC_OID_TIPO_DOCU,
             TERR_OID_TERR,
             TICL_OID_TIPO_CLIE,
             TIDO_OID_TIPO_DOCU,
             TIDS_OID_TIPO_DESP,
             TSPA_OID_TIPO_SOLI_PAIS,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             VAL_GLOS_OBSE,
             VAL_NUME_SOLI,
             VAL_USUA,
             VEPO_OID_VALO_ESTR_GEOP,
             VAL_TIPO_CAMB,
             ZZON_OID_ZONA,
             ZTAD_OID_TERR_ADMI,
             ICTP_OID_TIPO_PROG)
            SELECT NULL,
                   ALMC_OID_ALMA,
                   CLDI_OID_CLIE_DIRE,
                   interfazRecordN(x).oidCliente,
                   interfazRecordN(x).oidCliente,
                   interfazRecordN(x).oidCliente,
                   interfazRecordN(x).oidCliente,
                   NULL,
                   lnOidClaseSolicitud,
                   interfazRecordN(x).oidConcurso,
                   ESSO_OID_ESTA_SOLI,
                   TRUNC(SYSDATE),
                   ldFechaProgFact,
                   lnOidFormaPago,
                   3,
                   0,
                   lnIndPedidoPrueba,
                   lnIndPermitirUnion,
                   1,
                   13, --INCENTIVOS
                   lnOidMoneda,
                   1,
                   NULL,
                   NULL, --VA EL NUMERO DE PREMIO
                   lnOidCabecera,
                   lnOidOperacion,
                   lnOidPais,
                   lnOidPeriodo,
                   1,
                   lnOidSubAcceso,
                   SBTI_OID_SUBT_CLIE,
                   NULL,
                   lnOidSociedad,
                   TDOC_OID_TIPO_DOCU,
                   TERR_OID_TERR,
                   lnOidTipoCliente,
                   lnOidTipoDocumentoLegal,
                   TIDS_OID_TIPO_DESP,
                   lnOidTipoSoliPais,
                   lnOidTipoCons,
                   'REGULARIZACION DE PREMIO PARA TI PARA MI',
                   lsNumeroFormato,
                   psCodigoUsuario,
                   VEPO_OID_VALO_ESTR_GEOP,
                   VAL_TIPO_CAMB,
                   ZZON_OID_ZONA,
                   ZTAD_OID_TERR_ADMI,
                   lnOidPrograma
              FROM PED_SOLIC_CABEC
             WHERE OID_SOLI_CABE = lnOidSolicitud;
        
          lnTotalUnidades      := 0;
          lnContadorPosiciones := 0;
        
          --Por cada premio elegido, se recupera los productos a despachar
          FOR z IN (SELECT f.NUM_PREM,
                           d.OID_PARA_NIVE_PREM,
                           CASE
                             WHEN ral.oid_reem_arti_lote IS NULL THEN
                              g.COD_VENT_FICT
                             ELSE
                              ral.cod_vent_fict
                           END COD_VENT_FICT,
                           CASE
                             WHEN ral.oid_reem_arti_lote IS NULL THEN
                              g.PROD_OID_PROD
                             ELSE
                              ral.PROD_OID_PROD
                           END PROD_OID_PROD,
                           CASE
                             WHEN ral.oid_reem_arti_lote IS NULL THEN
                              g.NUM_UNID
                             ELSE
                              g.num_unid * ral.num_unid
                           END NUM_UNID,
                           CASE
                             WHEN ral.oid_reem_arti_lote IS NULL THEN
                              g.IMP_PREC_PUBL
                             else
                              ral.IMP_PREC_PUBL
                           end IMP_PREC_PUBL
                      FROM INC_CONCU_PARAM_GENER a,
                           INC_CONCU_PARAM_CONSU b,
                           INC_PARAM_GENER_PREMI c,
                           INC_PARAM_NIVEL_PREMI d,
                           INC_PREMI_ARTIC e,
                           INC_LOTE_PREMI_ARTIC f,
                           INC_ARTIC_LOTE g,
                           (SELECT *
                              FROM inc_reemp_artic_lote aa
                             WHERE aa.ind_acti = 1
                               AND aa.ctre_oid_crit_reem = 3
                               AND INC_FN_VALID_AMBIT_GEOGR_REEMP(nvl(aa.comp_oid_reem_arti_lote,
                                                                      aa.oid_reem_arti_lote),
                                                                  interfazRecordN(x)
                                                                  .oidCliente) = '1'
                               AND aa.num_orde =
                                   (SELECT MIN(bb.num_orde)
                                      FROM inc_reemp_artic_lote bb
                                     WHERE bb.ind_acti = 1
                                       AND bb.ctre_oid_crit_reem = 3
                                       AND aa.ARLO_OID_ARTI_LOTE =
                                           bb.ARLO_OID_ARTI_LOTE)) ral
                     WHERE a.OID_PARA_GRAL = interfazRecordN(x).oidConcurso
                       AND a.OID_PARA_GRAL = b.COPA_CONC_PUNT_RECO
                       AND a.OID_PARA_GRAL = c.COPA_OID_PARA_GRAL
                       AND d.NUM_NIVE = 1
                       AND d.PAGP_OID_PARA_GENE_PREM = c.OID_PARA_GENE_PREM
                       AND d.OID_PARA_NIVE_PREM = e.PANP_OID_PARA_NIVE_PREM
                       AND PRAR_OID_PREM_ARTI = OID_PREM_ARTI
                       AND g.IND_DESP = 1
                       AND f.OID_LOTE_PREM_ARTI = g.LOPA_OID_LOTE_PREM_ARTI
                       AND g.OID_ARTI_LOTE = ral.ARLO_OID_ARTI_LOTE(+)
                       AND (c.PERD_OID_PERI IS NULL OR
                           c.PERD_OID_PERI = lnOidPeriodo or
                           ((lnOidPeriodo BETWEEN c.PERD_OID_PERI_INIC and
                           c.PERD_OID_PERI) and
                           c.PERD_OID_PERI_INIC IS NOT NULL))
                     ORDER BY f.NUM_PREM) LOOP
          
            INSERT INTO PED_SOLIC_POSIC
              (OID_SOLI_POSI,
               SOCA_OID_SOLI_CABE,
               COD_POSI,
               NUM_UNID_DEMA,
               TPOS_OID_TIPO_POSI,
               PROD_OID_PROD,
               FOPA_OID_FORM_PAGO,
               VAL_CODI_VENT,
               VAL_CODI_VENT_FICT,
               ESPO_OID_ESTA_POSI,
               STPO_OID_SUBT_POSI,
               NUM_UNID_DEMA_REAL,
               VAL_PREC_CATA_UNIT_LOCA,
               VAL_PREC_CONT_UNIT_LOCA,
               VAL_PREC_CATA_UNIT_DOCU,
               VAL_PREC_CONTA_UNIT_DOCU,
               VAL_PORC_DESC,
               VAL_IMPO_DESC_UNIT_DOCU,
               OFDE_OID_DETA_OFER,
               NUM_UNID_COMPR,
               VAL_IMPO_DESC_UNIT_LOCA,
               NUM_PAGI_CATA,
               VAL_CATA,
               NUM_UNID_POR_ATEN)
            VALUES
              (PED_SOPO_SEQ.NEXTVAL,
               lnOidCabecera,
               lnContadorPosiciones,
               z.NUM_UNID,
               lnOidTipoPosi,
               z.PROD_OID_PROD,
               NULL,
               NULL,
               z.COD_VENT_FICT,
               lnOidEstadoPosicion,
               lnOidSubTipoPosi,
               z.NUM_UNID,
               0,
               z.IMP_PREC_PUBL,
               0,
               0,
               NULL,
               NULL,
               NULL,
               z.NUM_UNID,
               NULL,
               NULL,
               NULL,
               z.NUM_UNID);
          
            lnContadorPosiciones := lnContadorPosiciones + 1;
            lnTotalUnidades      := lnTotalUnidades + z.NUM_UNID;
            lnNumeroPremio       := z.NUM_PREM;
            lnOidNivelPremio     := z.OID_PARA_NIVE_PREM;
          END LOOP;
        
          --Actualizamos la Solicitud con el Numero de Premio y Total Unidades Atendidas
          UPDATE PED_SOLIC_CABEC
             SET NUM_PREM                = lnNumeroPremio,
                 VAL_UNID_DEMA_REAL_TOTA = lnTotalUnidades,
                 NUM_UNID_POR_ATEN_TOTA  = lnTotalUnidades
           WHERE OID_SOLI_CABE = lnOidCabecera;
        
          --Se actualiza la cuenta corriente de puntos con el cargo por el despacho de premios
          SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;
        
          --Insertar cargo puntaje en  Cuenta Corriente
          INSERT INTO INC_CUENT_CORRI_PUNTO
            (OID_CUEN_CORR_PUNT,
             NUM_MOVI,
             NUM_PUNT,
             NUM_PUNT_EXIG,
             FEC_MOVI,
             COPA_OID_PARA_GRAL,
             CLIE_OID_CLIE,
             PERD_OID_PERI,
             TMOV_OID_TIPO_MOVI,
             FEC_ULTI_ACTU,
             VAL_DESC)
          VALUES
            (lnOidCuenta,
             lnOidCuenta,
             interfazRecordN(x).puntajeGanadora * (-1),
             0,
             TRUNC(SYSDATE),
             interfazRecordN(x).oidConcurso,
             interfazRecordN(x).oidCliente,
             lnOidPeriodo,
             2,
             SYSDATE,
             'Entrega de Premio');
        
          --Se registra el cliente como Ganadora
          INSERT INTO INC_GANAD
            (OID_GANA,
             FEC_OBTE,
             NUM_UNID,
             IND_LIST_GANA,
             IND_DESC,
             IND_DESP,
             CLIE_OID_CLIE,
             PERD_OID_PERI,
             PANP_OID_PARA_NIVE_PREM,
             SOCA_OID_SOLI_CABE,
             IND_CLIE_BLOQ)
          VALUES
            (INC_GANA_SEQ.NEXTVAL,
             TRUNC(SYSDATE),
             lnTotalUnidades,
             0,
             0,
             1,
             interfazRecordN(x).oidCliente,
             lnOidPeriodo,
             lnOidNivelPremio,
             lnOidSolicitud,
             0);
        
          --Actualizando Inc candidata ganadora
          UPDATE INC_CANDI_GANAD
             SET VAL_REQU_PREM_SUPE = 1, PERD_OID_PERI_EVAL = lnOidPeriodo
           WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso
             AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND VAL_REQU_PREM_SUPE = 0;
        
        END LOOP;
      
      END IF;
      EXIT WHEN cursorPedidos%NOTFOUND;
    END LOOP;
    CLOSE cursorPedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_DESPA_PREMI_PTIMI: ' ||
                              ls_sqlerrm);
    
  END INC_PR_DESPA_PREMI_PTIMI;

  /**************************************************************************
  Descripcion       : Proceso Identifica si cada recomendante es participante
                      o no del concurso para el cual fue registrado.Además corrige
                      una data de elección de premio para las recomendadas
  Fecha Creacion    : 09/10/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_REGUL_PARTI_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
  
    lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
    lnOidPeriodoAnt CRA_PERIO.OID_PERI%TYPE;
  
    lsCodigoPeriodoAnt SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOcurrencias      NUMBER;
    lnTotalOcurrencias NUMBER;
    lsResult           VARCHAR2(1);
  
    CURSOR c_Recomendantes(codigoPeriodo VARCHAR2) IS
      SELECT DISTINCT rte.CLIE_OID_CLIE,
                      rte.OID_CLIE_RETE,
                      con.oid_para_gral,
                      pgp.TPRM_OID_TIPO_PION
        FROM INC_CLIEN_RECTE       rte,
             INC_CLIEN_RECDO       rdo,
             INC_CONCU_PARAM_GENER con,
             INC_PARAM_GENER_PREMI pgp,
             CRA_PERIO             cra,
             SEG_PERIO_CORPO       cor
       WHERE rte.Oid_Clie_Rete = rdo.clr3_oid_clie_rete
         AND rte.copa_oid_para_gral = con.oid_para_gral
         AND con.oid_para_gral = pgp.copa_oid_para_gral
         AND con.ind_acti = 1
         AND rdo.perd_oid_peri = cra.oid_peri
         AND cra.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri >= codigoPeriodo;
  
    TYPE interfazPedidos IS RECORD(
      oidCliente  INC_CLIEN_RECTE.CLIE_OID_CLIE%TYPE,
      oidRecom    INC_CLIEN_RECTE.OID_CLIE_RETE%TYPE,
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      tipoOpcion  INC_PARAM_GENER_PREMI.TPRM_OID_TIPO_PION%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos el periodo Anterior
    lsCodigoPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal,
                                                                 -1);
  
    --PROCESAMOS A LAS RECOMENDANTES
    OPEN c_Recomendantes(lsCodigoPeriodoAnt);
    LOOP
      FETCH c_Recomendantes BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
    
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lsResult := '1';
        
          --Verificamsos si existe algun pedido para la Recomendante
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM INC_PEDID_CONCU_RECOM
           WHERE CLR3_OID_CLIE_RETE = interfazRecordN(x).oidRecom
             AND CLRE_OID_CLIE_REDO IS NULL;
        
          IF (lnOcurrencias = 0) THEN
          
            --VALIDACION AMBITO GEOGRAFICO
            SELECT COUNT(1)
              INTO lnTotalOcurrencias
              FROM INC_AMBIT_GEOGR geo
             WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso;
          
            IF (lnTotalOcurrencias > 0) THEN
              --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
              BEGIN
                lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(interfazRecordN(x)
                                                           .oidConcurso,
                                                           interfazRecordN(x)
                                                           .oidCliente);
              EXCEPTION
                WHEN OTHERS THEN
                  lsResult := '0';
              END;
            END IF;
          
            IF (lsResult = '1') THEN
              --VALIDACION DEL TIPO DE PARTICIPANTE
              SELECT COUNT(1)
                INTO lnTotalOcurrencias
                FROM INC_CLASI_PARTI_CONCU par
               WHERE par.copa_oid_para_gral = interfazRecordN(x)
                    .oidConcurso;
            
              lsResult := '1';
              IF (lnTotalOcurrencias > 0) THEN
                lsResult := SUBSTR(INC_FN_VALID_CLASI_CONCU(interfazRecordN(x)
                                                            .oidConcurso,
                                                            interfazRecordN(x)
                                                            .oidCliente),
                                   1,
                                   1); --1:valid <>1:no valido
              END IF;
            
              IF (lsResult = '1') THEN
                --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
                SELECT COUNT(1)
                  INTO lnTotalOcurrencias
                  FROM INC_ESTAT_VENTA_CONSU
                 WHERE COPA_OID_PARA_GRAL = interfazRecordN(x).oidConcurso;
              
                lsResult := '1';
                IF (lnTotalOcurrencias > 0) THEN
                  lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(interfazRecordN   (x)
                                                             .oidConcurso,
                                                             interfazRecordN   (x)
                                                             .oidCliente,
                                                             psCodigoPeriodo,
                                                             lsCodigoPeriodoAnt); --1:valid 0:no valido
                END IF;
              END IF;
            END IF;
          END IF;
        
          IF (lsResult = '1') THEN
            UPDATE INC_CLIEN_RECTE
               SET IND_PART = 1
             WHERE OID_CLIE_RETE = interfazRecordN(x).oidRecom;
          ELSE
            UPDATE INC_CLIEN_RECTE
               SET IND_PART = 0
             WHERE OID_CLIE_RETE = interfazRecordN(x).oidRecom;
          END IF;
        
          --Para el caso de Bolsa de Premios
          IF (interfazRecordN(x).tipoOpcion = 1) THEN
            UPDATE INC_CLIEN_RECDO
               SET PANP_OID_PARA_NIVE_PREM = NULL, NUM_PREM = NULL
             WHERE CLR3_OID_CLIE_RETE = interfazRecordN(x).oidRecom;
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Recomendantes%NOTFOUND;
    END LOOP;
    CLOSE c_Recomendantes;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_REGUL_PARTI_RECOM: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_REGUL_PARTI_RECOM;

  /**************************************************************************
  Descripcion        : Obtiene datos del Oid Detalle Oferta
  Fecha Creacion     : 18/11/2013
  Parametros Entrada :
             pnOidDetOferta : Oid Detalle Oferta
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_DETAL_CUV(pnOidDetOferta NUMBER) RETURN VARCHAR2 IS
    lsDetalleOferta VARCHAR2(100);
  
  BEGIN
  
    IF (pnOidDetOferta IS NULL) THEN
      RETURN NULL;
    END IF;
  
    SELECT INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(c.PERD_OID_PERI) ||
           ' - ' || a.VAL_CODI_VENT
      INTO lsDetalleOferta
      FROM PRE_OFERT_DETAL a, PRE_MATRI_FACTU b, PRE_MATRI_FACTU_CABEC c
     WHERE a.OID_DETA_OFER = b.OFDE_OID_DETA_OFER
       AND b.MFCA_OID_CABE = c.OID_CABE
       AND a.OID_DETA_OFER = pnOidDetOferta;
  
    RETURN lsDetalleOferta;
  
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_OBTIE_DETAL_CUV: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
      RETURN NULL;
    
  END INC_FN_OBTIE_DETAL_CUV;

  /**************************************************************************
  Descripcion        : Obtiene datos del Oid Detalle Oferta
  Fecha Creacion     : 12/12/2013
  Parametros Entrada :
             pnOidConcurso : Oid Concurs
             psCodigoVenta : Codigo Venta
  
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION INC_FN_OBTIE_POSIC_REEMP(pnOidConcurso NUMBER,
                                    psCodigoVenta VARCHAR2) RETURN NUMBER IS
    lnOcurrencias NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PED_SOLIC_POSIC pos, PED_SOLIC_CABEC cab
     WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
       AND pos.soca_oid_soli_cabe = cab.oid_soli_cabe
       AND pos.val_codi_vent_fict = psCodigoVenta
       AND cab.copa_oid_para_gene = pnOidConcurso
       AND ROWNUM = 1;
  
    RETURN lnOcurrencias;
  
  END INC_FN_OBTIE_POSIC_REEMP;

  /**************************************************************************
  Descripcion       : Generar las Solicitudes de Servicio Bolsa de Faltante
                      para las consultoras en Facturacion
                      el premio por falta de stock.
  Fecha Creacion    : 27/02/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_SOLIC_BOLSA_FACTU(psCodigoPais       VARCHAR2,
                                           psCodigoMarca      VARCHAR2,
                                           psCodigoCanal      VARCHAR2,
                                           psCodigoPeriodo    VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
      SELECT BOLF.OID_BOLS_FALT,
             ZON.OID_ZONA,
             BOLF.COPA_OID_PARA_GENE,
             BOLF.VAL_CODI_VENT_FICT,
             BOLF.CLIE_OID_CLIE,
             PROD.OID_PROD,
             NVL(BOLF.NUM_PREM, 1) NUM_PREM,
             BOLF.NUM_UNID_FALT,
             INC_FN_OBTIE_PRECI_RECLA(BOLF.COPA_OID_PARA_GENE,
                                      BOLF.VAL_CODI_VENT_FICT) VAL_PREC_PUBL,
             BOLF.PERD_OID_PERI,
             BOLF.SOPO_OID_SOLI_POSI
        FROM INC_BOLSA_FALTA     BOLF,
             BEL_STOCK           STO,
             ZON_ZONA            ZON,
             MAE_PRODU           PROD,
             bel_estad_merca     em,
             PED_SOLIC_CABEC     CAB,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC      sol
       WHERE BOLF.PROD_OID_PROD = STO.PROD_OID_PROD
         AND BOLF.PROD_OID_PROD = PROD.OID_PROD
         AND STO.ALMC_OID_ALMA =
             nvl((SELECT DISTINCT a1.oid_alma
                   FROM bel_almac             a1,
                        app_confi_centr_distr b1,
                        ape_confi_liafp_cabec c1,
                        ape_confi_liafp_detal d1
                  WHERE a1.ccdi_oid_confi_centr_distr =
                        b1.oid_conf_cent_dist
                    AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
                    AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
                    AND d1.zzon_oid_zona = ZON.OID_ZONA),
                 2001)
         AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
         AND CAB.CLIE_OID_CLIE = BOLF.CLIE_OID_CLIE
         AND BOLF.FEC_SOLU IS NULL
         AND em.COD_ESTA = 'LD'
         AND em.PAIS_OID_PAIS = oidPais
         AND em.OID_ESTA_MERC = sto.esme_oid_esta_merc
         AND STO.VAL_SALD >= BOLF.NUM_UNID_FALT
         AND CAB.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
         AND TSP.TSOL_OID_TIPO_SOLI = SOL.OID_TIPO_SOLI
         AND SOL.COD_TIPO_SOLI = 'SOC'
         AND CAB.GRPR_OID_GRUP_PROC = 4
         AND CAB.PERD_OID_PERI = oidPeriodo;
  
    TYPE interfazPedidos IS RECORD(
      oidBolsaFaltante INC_BOLSA_FALTA.OID_BOLS_FALT%TYPE,
      oidZona          ZON_ZONA.OID_ZONA%TYPE,
      oidConcursoSoli  PED_SOLIC_CABEC.COPA_OID_PARA_GENE%TYPE,
      codigoVenta      PED_SOLIC_POSIC.VAL_CODI_VENT_FICT%TYPE,
      oidCliente       MAE_CLIEN.OID_CLIE%TYPE,
      oidProducto      MAE_PRODU.OID_PROD%TYPE,
      numeroPremio     PED_SOLIC_CABEC.NUM_PREM%TYPE,
      numUnidFaltantes INC_BOLSA_FALTA.NUM_UNID_FALT%TYPE,
      precioPublico    INC_ARTIC_LOTE.IMP_PREC_PUBL%TYPE,
      oidPeriodoFalt   INC_BOLSA_FALTA.PERD_OID_PERI%TYPE,
      oidSolicitudPos  PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
    ltRegSolicitud  tRegSolicitud;
    ltRegPosicion   tablaRegPosicion := tablaRegPosicion();
    lnESTPEDIDOORIG PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI%TYPE;
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --PROCESAMOS A LAS ORDENES RETAIL
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        
          lnESTPEDIDOORIG := 0;
        
          SELECT COUNT(*)
            INTO lnESTPEDIDOORIG
            FROM PED_SOLIC_CABEC SC,
                 PED_SOLIC_CABEC CON,
                 PED_SOLIC_POSIC SP
           WHERE SP.OID_SOLI_POSI = interfazRecordN(x).oidSolicitudPos
             AND SP.SOCA_OID_SOLI_CABE = SC.OID_SOLI_CABE
             AND SC.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
             AND SC.PERD_OID_PERI = CON.PERD_OID_PERI
             AND SC.PERD_OID_PERI = interfazRecordN(x).oidPeriodoFalt
             AND CON.ESSO_OID_ESTA_SOLI = 4;
        
          IF (lnESTPEDIDOORIG > 0) THEN
            --Pedido Anulado
            UPDATE INC_BOLSA_FALTA
               SET FEC_SOLU = SYSDATE,
                   VAL_OBSE = 'Cerrado por factura origen anulada'
             WHERE OID_BOLS_FALT = interfazRecordN(x).oidBolsaFaltante;
          
          ELSE
          
            --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
            ltRegSolicitud.COD_PAIS      := psCodigoPais;
            ltRegSolicitud.COD_MARC      := psCodigoMarca;
            ltRegSolicitud.COD_CANA      := psCodigoCanal;
            ltRegSolicitud.OID_CLIE      := interfazRecordN(x).oidCliente;
            ltRegSolicitud.COD_PERI      := psCodigoPeriodo;
            ltRegSolicitud.FEC_PROG_FACT := NULL; --Se calculara en la Creacion de la Solicitud
            ltRegSolicitud.COD_OPER      := 'INC050';
            ltRegSolicitud.COD_CLAS_SOLI := 'I1';
            ltRegSolicitud.COD_TIPO_SOLI := NULL;
            ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
            ltRegSolicitud.OID_PARA_GRAL := interfazRecordN(x)
                                            .oidConcursoSoli;
            ltRegSolicitud.NUM_PREM      := interfazRecordN(x).numeroPremio;
            ltRegSolicitud.COD_USUA      := psCodigoUsuario;
          
            ltRegSolicitud.OID_ACCE_FISI                  := 1;
            ltRegSolicitud.OID_GRUP_PROC                  := 3; --(GP3)
            ltRegSolicitud.OID_GRUP_PROC_SECU             := 3; --(GP3)
            ltRegSolicitud.NUM_CLIEN                      := 1;
            ltRegSolicitud.OID_MODU                       := 13; --Incentivos
            ltRegSolicitud.VAL_GLOS_OBSE                  := 'ATENCION BOLSA DE FALTANTE FACTURACION';
            ltRegSolicitud.VAL_BASE_FLET_LOCA             := 0;
            ltRegSolicitud.VAL_TOTA_PAGA_LOCA             := 0;
            ltRegSolicitud.VAL_PREC_CATA_TOTA_LOCA        := 0;
            ltRegSolicitud.VAL_BASE_FLET_DOCU             := 0;
            ltRegSolicitud.VAL_PREC_CATA_TOTA_LOC_UNI_DEM := 0;
            ltRegSolicitud.VAL_UNID_DEMA_REAL_TOTA        := interfazRecordN(x)
                                                             .numUnidFaltantes;
            ltRegSolicitud.NUM_UNID_POR_ATEN_TOTA         := interfazRecordN(x)
                                                             .numUnidFaltantes;
            ltRegSolicitud.VAL_PREC_CONT_TOTA_LOCA        := interfazRecordN(x)
                                                             .precioPublico * interfazRecordN(x)
                                                             .numUnidFaltantes;
          
            --CREAMOS LOS DATOS DEL DETALLE DE LA SOLICITUD DE PEDIDO
            ltRegPosicion.EXTEND(1);
            ltRegPosicion(ltRegPosicion.LAST).OID_PROD := interfazRecordN(x)
                                                          .oidProducto;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := interfazRecordN(x)
                                                          .numUnidFaltantes;
            ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
            ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := interfazRecordN(x)
                                                                    .codigoVenta;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := interfazRecordN(x)
                                                                .numUnidFaltantes;
            ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := interfazRecordN(x)
                                                                         .precioPublico;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
          
            --CREAMOS LA SOLICITUD DE PEDIDO
            INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud, 'Z', ltRegPosicion);
          
            --Inicializamos la lista de Posiciones de la Nueva Solicitud
            ltRegPosicion.DELETE;
          
            --Actualizamos la bolsa faltantes como Atendida
            UPDATE INC_BOLSA_FALTA
               SET FEC_SOLU = SYSDATE,
                   VAL_OBSE = 'Atendido campaña ' || psCodigoPeriodo
             WHERE OID_BOLS_FALT = interfazRecordN(x).oidBolsaFaltante;
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_SOLIC_BOLSA_FACTU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_GENER_SOLIC_BOLSA_FACTU;

  /**************************************************************************
  Descripcion       : Validacion de Premios Electivos
  Fecha Creacion    : 07/04/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion  : Fecha de Facturacion
    psCodigoUsuario     : Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_PREMI_ELEGI(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsIndRechazo      BAS_PARAM_PAIS.VAL_PARA%TYPE;
    lnOcurrencias     NUMBER;
    lnOcurrenciasAux  NUMBER;
    lnDescalificado   NUMBER;
    lnYaPremiado      NUMBER;
    lsPeriodoPlazo    SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoPlazo CRA_PERIO.OID_PERI%TYPE;
  
    CURSOR c_Pedidos(oidPeriodo NUMBER) IS
      SELECT pre.OID_PREM_ELEG,
             gen.COPA_OID_PARA_GRAL,
             con.NUM_CONC,
             NVL(niv.VAL_PLAZ_ENTR, 0) VAL_PLAZ_ENTR,
             pre.TIP_RECE,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.perd_oid_peri_inic) ini_despa,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.perd_oid_peri) fin_despa,
             gen.perd_oid_peri_inic,
             gen.perd_oid_peri,
             pre.clie_oid_clie,
             con.bcal_oid_base_calc,
             gen.tprm_oid_tipo_pion
        FROM INC_PREMI_ELEGI       pre,
             INC_PARAM_NIVEL_PREMI niv,
             INC_PARAM_GENER_PREMI gen,
             INC_CONCU_PARAM_GENER con
       WHERE gen.COPA_OID_PARA_GRAL = con.OID_PARA_GRAL
         AND niv.PAGP_OID_PARA_GENE_PREM = gen.OID_PARA_GENE_PREM
         AND pre.PANP_OID_PARA_NIVE_PREM = niv.OID_PARA_NIVE_PREM
         AND pre.PERD_OID_PERI = oidPeriodo
         AND IND_PEND = 1;
  
    TYPE interfazPedidos IS RECORD(
      oidPremio      INC_PREMI_ELEGI.OID_PREM_ELEG%TYPE,
      oidConcurso    INC_PARAM_GENER_PREMI.COPA_OID_PARA_GRAL%TYPE,
      numeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
      plazoEntrega   INC_PARAM_NIVEL_PREMI.VAL_PLAZ_ENTR%TYPE,
      tipoRecepcion  INC_PREMI_ELEGI.TIP_RECE%TYPE,
      inidespa       SEG_PERIO_CORPO.COD_PERI%TYPE,
      findespa       SEG_PERIO_CORPO.COD_PERI%TYPE,
      oidperiini     CRA_PERIO.OID_PERI%TYPE,
      oidperi        CRA_PERIO.OID_PERI%TYPE,
      oidclie        MAE_CLIEN.OID_CLIE%TYPE,
      base_calc      INC_CONCU_PARAM_GENER.BCAL_OID_BASE_CALC%TYPE,
      tipo_pion      INC_PARAM_GENER_PREMI.TPRM_OID_TIPO_PION%TYPE);
  
    TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
    interfazRecordN interfazPedidosTab;
  
  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Recuperamos el parametro de Indicador de rechazo de premio extemporáneo
    lsIndRechazo := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais,
                                                        'INC',
                                                        '007'),
                        '0');
  
    --PROCESAMOS A LOS PREMIOS ELEGIDOS
    OPEN c_Pedidos(lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          --
          lnDescalificado := 0;
          lnYaPremiado    := 0;
        
          select count(1)
            into lnDescalificado
            from inc_candi_ganad
           where copa_oid_para_gral = interfazRecordN(x).OidConcurso
             and clie_oid_clie = interfazRecordN(x).OidClie
             and interfazRecordN(x).base_calc in (1, 2)
             and interfazRecordN(x).tipo_pion = 2
             and regl_oid_regl is not null;
        
          select count(1)
            into lnYaPremiado
            from inc_candi_ganad
           where copa_oid_para_gral = interfazRecordN(x).OidConcurso
             and clie_oid_clie = interfazRecordN(x).OidClie
             and interfazRecordN(x).base_calc in (1, 2)
             and interfazRecordN(x).tipo_pion = 2
             and val_requ_prem_supe = 1;
          --
          CASE
            WHEN lnDescalificado > 0 THEN
              UPDATE INC_PREMI_ELEGI
                 SET IND_PEND      = 4,
                     COD_MOTI_INVA = 10,
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE IND_PEND = 1
                 AND CLIE_OID_CLIE = interfazRecordN(x).OidClie
                 AND COPA_OID_PARA_GRAL = interfazRecordN(x).OidConcurso;
            
            WHEN lnYaPremiado > 0 THEN
              UPDATE INC_PREMI_ELEGI
                 SET IND_PEND      = 4,
                     COD_MOTI_INVA = 14,
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE IND_PEND = 1
                 AND CLIE_OID_CLIE = interfazRecordN(x).OidClie
                 AND COPA_OID_PARA_GRAL = interfazRecordN(x).OidConcurso;
            
            ELSE
            
              lnOcurrencias := 0;
            
              IF (interfazRecordN(x).tipoRecepcion = 'A') THEN
              
                lsPeriodoPlazo := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                         lnOidPais,
                                                                         lnOidMarca,
                                                                         lnOidCanal,
                                                                         interfazRecordN(x)
                                                                         .plazoEntrega);
              
                lnOidPeriodoPlazo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodoPlazo,
                                                                                lnOidMarca,
                                                                                lnOidCanal);
              
                IF psCodigoPeriodo BETWEEN interfazRecordN(x).inidespa AND interfazRecordN(x)
                  .findespa OR (interfazRecordN(x).oidperi IS NULL) THEN
                
                  lnOcurrencias := 1;
                
                  --Validamos si existe en la tabla INC_CONCU_CAMPA_DESPA
                  SELECT COUNT(1)
                    INTO lnOcurrencias
                    FROM INC_CONCU_CAMPA_DESPA
                   WHERE NUM_CONC = interfazRecordN(x).numeroConcurso;
                
                  IF (lnOcurrencias > 0) THEN
                    SELECT COUNT(1)
                      INTO lnOcurrenciasAux
                      FROM INC_CONCU_CAMPA_DESPA
                     WHERE NUM_CONC = interfazRecordN(x).numeroConcurso
                       AND COD_PERI = lsPeriodoPlazo;
                  END IF;
                
                  IF ((lnOcurrencias = 0) OR
                     (lnOcurrencias > 0 AND lnOcurrenciasAux > 0)) THEN
                  
                    UPDATE INC_PREMI_ELEGI
                       SET PERD_OID_PERI_IATN = lnOidPeriodoPlazo,
                           USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE
                     WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
                  ELSE
                  
                    IF (lsIndRechazo = '1') THEN
                      UPDATE INC_PREMI_ELEGI
                         SET IND_PEND      = 4,
                             COD_MOTI_INVA = 5,
                             USU_MODI      = psCodigoUsuario,
                             FEC_MODI      = SYSDATE
                       WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
                    
                    ELSE
                      UPDATE INC_PREMI_ELEGI
                         SET PERD_OID_PERI_IATN = lnOidPeriodoPlazo,
                             USU_MODI           = psCodigoUsuario,
                             FEC_MODI           = SYSDATE
                       WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
                    END IF;
                  
                  END IF;
                ELSE
                  IF (lsIndRechazo = '1') OR
                     (psCodigoPeriodo > interfazRecordN(x).findespa AND interfazRecordN(x)
                     .oidperi IS NOT NULL) THEN
                    UPDATE INC_PREMI_ELEGI
                       SET IND_PEND      = 4,
                           COD_MOTI_INVA = 5,
                           USU_MODI      = psCodigoUsuario,
                           FEC_MODI      = SYSDATE
                     WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
                  
                  ELSE
                    IF interfazRecordN(x).oidperi IS NOT NULL AND psCodigoPeriodo < interfazRecordN(x)
                       .inidespa THEN
                      lnOidPeriodoPlazo := interfazRecordN(x).oidperiini;
                    ELSE
                      lnOidPeriodoPlazo := lnOidPeriodo;
                    END IF;
                  
                    UPDATE INC_PREMI_ELEGI
                       SET PERD_OID_PERI_IATN = lnOidPeriodoPlazo,
                           USU_MODI           = psCodigoUsuario,
                           FEC_MODI           = SYSDATE
                     WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
                  END IF;
                
                END IF;
              
                -- Cuando el premio es ingreso manual
              ELSE
              
                IF interfazRecordN(x).oidperi IS NOT NULL AND psCodigoPeriodo < interfazRecordN(x)
                   .inidespa THEN
                  lnOidPeriodoPlazo := interfazRecordN(x).oidperiini;
                ELSE
                  lnOidPeriodoPlazo := lnOidPeriodo;
                END IF;
              
                UPDATE INC_PREMI_ELEGI
                   SET PERD_OID_PERI_IATN = lnOidPeriodoPlazo,
                       USU_MODI           = psCodigoUsuario,
                       FEC_MODI           = SYSDATE
                 WHERE OID_PREM_ELEG = interfazRecordN(x).oidPremio;
              
              END IF;
          END CASE;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_VALID_PREMI_ELEGI: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_VALID_PREMI_ELEGI;

  /**************************************************************************
  Descripcion       : Realiza el calculo del total demanda de la ordenes de
                      compra que se encuentran en gp3.
  Fecha Creacion    : 19/08/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_TOTAL_FACTU(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2) IS
    CURSOR c_pedidos(oidPeriodo NUMBER) IS
      SELECT cab.CLIE_OID_CLIE
        FROM PED_SOLIC_CABEC     cab,
             PED_TIPO_SOLIC_PAIS tsp,
             PED_TIPO_SOLIC      sol
       WHERE cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
         AND sol.COD_TIPO_SOLI = 'SOC'
         AND cab.GRPR_OID_GRUP_PROC in (3,4)
         AND PERD_OID_PERI = oidPeriodo;
  
    --se define un tipo de dato tipo Tabla de Registros de los pedidos
    TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
    --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
    pedidoReg RegTab;
  
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnMonto            NUMBER;
    lnMonto_cata_aten  NUMBER;
    lnMonto_forma_cata NUMBER;
    lnOcurrencias      NUMBER;
    lnTotUnids         NUMBER;
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
    OPEN c_pedidos(lnOidPeriodo);
    LOOP
      FETCH c_pedidos BULK COLLECT
        INTO pedidoReg LIMIT W_FILAS;
    
      IF pedidoReg.COUNT > 0 THEN
        FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
        
          SELECT nvl(SUM(num_unid_dema_real * val_prec_cata_unit_loca), 0) val_mont_cata,
                 SUM(num_unid_aten * val_prec_cata_unit_loca) val_mont_cata_aten,
                 SUM(CASE
                       WHEN val_form_vent = 1 AND cod_cana = 'VD' THEN
                        val_prec_neto_tota_loca
                       ELSE
                        0
                     END) val_mont_form_cata,
                 SUM(num_unid_dema_real) num_unids
            INTO lnMonto, lnMonto_cata_aten, lnMonto_forma_cata, lntotUnids
            FROM (SELECT e.*, tofe.val_form_vent, cana.cod_cana
                    FROM ped_solic_cabec     a,
                         ped_solic_cabec     b,
                         ped_tipo_solic_pais c,
                         ped_tipo_solic      d,
                         ped_solic_posic     e,
                         pre_ofert_detal     ofde,
                         pre_tipo_ofert      tofe,
                         seg_canal           cana
                   WHERE a.clie_oid_clie = pedidoReg(x).CLIE_OID_CLIE
                     AND a.perd_oid_peri = lnOidPeriodo
                     AND b.perd_oid_peri = a.perd_oid_peri
                     AND a.soca_oid_soli_cabe = b.oid_soli_cabe
                     AND e.soca_oid_soli_cabe = a.oid_soli_cabe
                     AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
                     AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                     AND d.cod_tipo_soli = 'SOC'
                     AND a.fec_fact IS NOT NULL
                     AND e.espo_oid_esta_posi <> 2
                     AND b.esso_oid_esta_soli <> 4
                     AND e.ofde_oid_deta_ofer = ofde.oid_deta_ofer(+)
                     AND ofde.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer
                     AND tofe.cana_oid_cana = cana.oid_cana(+)
                  UNION ALL
                  SELECT e.*, tofe.val_form_vent, cana.cod_cana
                    FROM ped_solic_cabec     a,
                         ped_tipo_solic_pais c,
                         ped_tipo_solic      d,
                         ped_solic_posic     e,
                         pre_ofert_detal     ofde,
                         pre_tipo_ofert      tofe,
                         seg_canal           cana
                   WHERE a.clie_oid_clie = pedidoReg(x).CLIE_OID_CLIE
                     AND a.perd_oid_peri = lnOidPeriodo
                     AND e.soca_oid_soli_cabe = a.oid_soli_cabe
                     AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
                     AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                     AND d.cod_tipo_soli = 'SOC'
                     AND a.fec_fact IS NULL
                     AND a.grpr_oid_grup_proc in (3, 4)
                     AND e.espo_oid_esta_posi <> 2
                     AND e.ofde_oid_deta_ofer = ofde.oid_deta_ofer(+)
                     AND ofde.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer
                     AND tofe.cana_oid_cana = cana.oid_cana(+)
                  
                  );
        
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM PED_SOLIC_CABEC_ACUM2
           WHERE PERD_OID_PERI = lnOidPeriodo
             AND CLIE_OID_CLIE = pedidoReg(x).CLIE_OID_CLIE;
        
          IF (lnOcurrencias > 0) THEN
            UPDATE PED_SOLIC_CABEC_ACUM2
               SET VAL_CANT_PEDI      = 1,
                   VAL_MONT_TOTA      = lnMonto,
                   VAL_MONT_CATA      = lnMonto_cata_aten,
                   VAL_MONT_FORM_CATA = lnMonto_forma_cata,
                   VAL_UNID_TOTA      = lnTotUnids,
                   FEC_MODI           = SYSDATE,
                   USU_MODI           = psCodigoUsuario
             WHERE PERD_OID_PERI = lnOidPeriodo
               AND CLIE_OID_CLIE = pedidoReg(x).CLIE_OID_CLIE;
          
          ELSE
            INSERT INTO PED_SOLIC_CABEC_ACUM2
              (OID_SOCA_ACU2,
               VAL_CANT_PEDI,
               VAL_MONT_TOTA,
               VAL_MONT_CATA,
               VAL_MONT_FORM_CATA,
               VAL_UNID_TOTA,
               CLIE_OID_CLIE,
               PERD_OID_PERI,
               FEC_CREA,
               USU_CREA)
            VALUES
              (PED_SCA2_SEQ.nextval,
               1,
               lnMonto,
               lnMonto_cata_aten,
               lnMonto_forma_cata,
               lnTotUnids,
               pedidoReg(x).CLIE_OID_CLIE,
               lnOidPeriodo,
               SYSDATE,
               psCodigoUsuario);
          END IF;
        
        END LOOP;
      END IF;
    
      EXIT WHEN c_pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_pedidos;
  
    INC_PR_ACTIV_CONCU(psCodigoPais,
                       psCodigoMarca,
                       psCodigoCanal,
                       psCodigoPeriodo,
                       psCodigoUsuario,
                       'GP3');
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_TOTAL_FACTU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_CALCU_TOTAL_FACTU;

  /***************************************************************************
  Descripcion       : Valida de Migracion Puntos Consultora
  Fecha Creacion    : 10/09/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_VALID_MIGRA_PUNTO_CONSU(psCodigoPais  VARCHAR2,
                                           pnNumeroCarga NUMBER) IS
  
    CURSOR c_clientes IS
      SELECT NUM_FILA, COD_CLIE
        FROM INC_CARGA_MIGRA_PUNTO
       WHERE NUM_CARG = pnNumeroCarga;
  
    TYPE interfazClientes IS RECORD(
      numeroFila    INC_CARGA_MIGRA_PUNTO.NUM_FILA%TYPE,
      codigoCliente INC_CARGA_MIGRA_PUNTO.COD_CLIE%TYPE);
  
    TYPE interfazClientesTab IS TABLE OF interfazClientes;
    interfazRecordN interfazClientesTab;
  
    lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    lnNumeroFila    INC_CARGA_MIGRA_PUNTO.NUM_FILA%TYPE;
    lsCodigoCliente INC_CARGA_MIGRA_PUNTO.COD_CLIE%TYPE;
    lsCodigoMotivo  INC_MOTIV_MIGRA_PUNTO.COD_MOTI%TYPE;
    lnOidCliente    MAE_CLIEN.OID_CLIE%TYPE;
    lnOcurrencias   NUMBER(12);
  
  BEGIN
  
    --Recuperamos el oid Pais
    lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  
    --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_clientes;
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnNumeroFila    := interfazRecordN(x).numeroFila;
          lsCodigoCliente := interfazRecordN(x).codigoCliente;
        
          lsCodigoMotivo := NULL;
          lnOidCliente   := NULL;
        
          --(1), Validamos si existe el Codigo de Cliente
          BEGIN
            SELECT OID_CLIE
              INTO lnOidCliente
              FROM MAE_CLIEN
             WHERE PAIS_OID_PAIS = lnOidPais
               AND COD_CLIE = lsCodigoCliente;
          EXCEPTION
            WHEN OTHERS THEN
              NULL;
          END;
        
          IF (lnOidCliente IS NULL) THEN
            lsCodigoMotivo := '01';
          END IF;
        
          UPDATE INC_CARGA_MIGRA_PUNTO
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
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_VALID_MIGRA_PUNTO_CONSU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_VALID_MIGRA_PUNTO_CONSU;

  /***************************************************************************
  Descripcion       : Actualiza Migracion Puntos Consultora
  Fecha Creacion    : 28/08/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_MIGRA_PUNTO_CONSU(psCodigoPais            VARCHAR2,
                                           pnNumeroCarga           NUMBER,
                                           psNumeroConcursoOrigen  VARCHAR2,
                                           psNumeroConcursoDestino VARCHAR2,
                                           psCodigoUsuario         VARCHAR2) IS
  
    CURSOR c_clientes(oidConcurso NUMBER) IS
      SELECT DISTINCT NUM_FILA, COD_CLIE
        FROM INC_CARGA_MIGRA_PUNTO
       WHERE NUM_CARG = pnNumeroCarga
         AND IND_VALI = 1
      UNION
      SELECT DISTINCT NULL, COD_CLIE
        FROM MAE_CLIEN             cli,
             MAE_CLIEN_UNIDA_ADMIN adm,
             ZON_TERRI_ADMIN       ter,
             ZON_SECCI             sec,
             ZON_ZONA              zon,
             INC_MIGRA_PUNTO_AMBIT amb,
             INC_CANDI_GANAD       gan
       WHERE adm.CLIE_OID_CLIE = cli.OID_CLIE
         AND gan.clie_oid_clie = cli.oid_clie
         AND gan.copa_oid_para_gral = oidConcurso
         AND adm.IND_ACTI = 1
         AND ter.OID_TERR_ADMI = adm.ZTAD_OID_TERR_ADMI
         AND sec.OID_SECC = ter.ZSCC_OID_SECC
         AND zon.OID_ZONA = sec.ZZON_OID_ZONA
         AND zon.OID_ZONA = amb.OID_ZONA
         AND amb.NUM_CARG = pnNumeroCarga
      UNION
      SELECT DISTINCT NULL, COD_CLIE
        FROM MAE_CLIEN             cli,
             MAE_CLIEN_UNIDA_ADMIN adm,
             ZON_TERRI_ADMIN       ter,
             ZON_SECCI             sec,
             ZON_ZONA              zon,
             INC_MIGRA_PUNTO_AMBIT amb,
             INC_CANDI_GANAD       gan
       WHERE adm.CLIE_OID_CLIE = cli.OID_CLIE
         AND gan.clie_oid_clie = cli.oid_clie
         AND gan.copa_oid_para_gral = oidConcurso
         AND adm.IND_ACTI = 1
         AND ter.OID_TERR_ADMI = adm.ZTAD_OID_TERR_ADMI
         AND sec.OID_SECC = ter.ZSCC_OID_SECC
         AND zon.OID_ZONA = sec.ZZON_OID_ZONA
         AND zon.ZORG_OID_REGI = amb.OID_REGI
         AND amb.OID_ZONA IS NULL
         AND amb.NUM_CARG = pnNumeroCarga;
  
    TYPE interfazClientes IS RECORD(
      numeroFila    MAE_CARGA_NIVEL_RIESG.NUM_FILA%TYPE,
      codigoCliente MAE_CARGA_NIVEL_RIESG.COD_CLIE%TYPE);
  
    TYPE interfazClientesTab IS TABLE OF interfazClientes;
    interfazRecordN interfazClientesTab;
  
    lnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal SEG_CANAL.OID_CANA%TYPE;
  
    lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
    lnNumeroFila       MAE_CARGA_IMPRE_PADOC.NUM_FILA%TYPE;
    lsCodigoCliente    MAE_CARGA_IMPRE_PADOC.COD_CLIE%TYPE;
    lnOidCliente       MAE_CLIEN.OID_CLIE%TYPE;
    lnOcurrencias      NUMBER;
    lnTotalPuntaje     NUMBER;
    lnTotalPuntajeExig NUMBER;
    lnUltevaluacion    NUMBER;
    lnOidSecuencia     NUMBER;
    lnContadorFila     NUMBER;
  
    lnOidConcursoOrigen     INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
    lnOidConcursoDestino    INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
    lsNombreConcursoOrigen  INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE;
    lsNombreConcursoDestino INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE;
    lsCodigoMotivo          INC_MOTIV_MIGRA_PUNTO.COD_MOTI%TYPE;
    lsCodigoPeriodo         BAS_CTRL_FACT.COD_PERI%TYPE;
  
  BEGIN
  
    --Recuperamos el oid Pais
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Obtenemos el Periodo Proceso
    SELECT COD_PERI
      INTO lsCodigoPeriodo
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    --Obtenemos el Oid Periodo
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --Obtenemos datos del Concurso Origen
    SELECT OID_PARA_GRAL, VAL_NOMB
      INTO lnOidConcursoOrigen, lsNombreConcursoOrigen
      FROM INC_CONCU_PARAM_GENER
     WHERE NUM_CONC = psNumeroConcursoOrigen;
  
    --Obtenemos datos del Concurso Destino
    SELECT OID_PARA_GRAL, VAL_NOMB
      INTO lnOidConcursoDestino, lsNombreConcursoDestino
      FROM INC_CONCU_PARAM_GENER
     WHERE NUM_CONC = psNumeroConcursoDestino;
  
    lnContadorFila := 1;
  
    --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_clientes(lnOidConcursoOrigen);
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lsCodigoMotivo  := NULL;
          lnNumeroFila    := interfazRecordN(x).numeroFila;
          lsCodigoCliente := interfazRecordN(x).codigoCliente;
          lnOidCliente    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazRecordN(x)
                                                                      .codigoCliente);
        
          /*Verificar que no haya registros en inc_candi_ganad para la consultora y concurso origen
          que estén con val_requ_prem_supe =1*/
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM INC_CANDI_GANAD
           WHERE CLIE_OID_CLIE = lnOidCliente
             AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen
             AND VAL_REQU_PREM_SUPE = 1;
        
          IF (lnOcurrencias = 0) THEN
            /*Verificar que no haya registros en inc_candi_ganad para la consultora y concurso destino
            que estén con val_requ_prem_supe =1*/
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_CANDI_GANAD
             WHERE CLIE_OID_CLIE = lnOidCliente
               AND COPA_OID_PARA_GRAL = lnOidConcursoDestino
               AND VAL_REQU_PREM_SUPE = 1;
          
            IF (lnOcurrencias = 0) THEN
              /*  Verficar que la consultora no exista como descalificada para el concurso origen*/
              SELECT COUNT(1)
                INTO lnOcurrencias
                FROM INC_DESCA
               WHERE CLIE_OID_CLIE = lnOidCliente
                 AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen;
            
              IF (lnOcurrencias = 0) THEN
                /*o Totalizar los puntos de la cuenta corriente de puntos.
                Si el total es <=0 entonces no migrar puntos */
                SELECT NVL(SUM(NUM_PUNT), 0), NVL(SUM(NUM_PUNT_EXIG), 0)
                  INTO lnTotalPuntaje, lnTotalPuntajeExig
                  FROM INC_CUENT_CORRI_PUNTO
                 WHERE CLIE_OID_CLIE = lnOidCliente
                   AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen;
              
                IF (lnTotalPuntaje > 0) THEN
                  BEGIN
                    SELECT NVL(MAX(NUM_PERI_EVAL), 0)
                      INTO lnUltevaluacion
                      FROM INC_CANDI_GANAD
                     WHERE COPA_OID_PARA_GRAL = lnOidConcursoDestino
                       AND CLIE_OID_CLIE = lnOidCliente
                       AND PERD_OID_PERI = lnOidPeriodo;
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                      lnUltevaluacion := 0;
                  END;
                
                  IF (lnUltevaluacion = 0) THEN
                    INSERT INTO INC_CANDI_GANAD
                      (OID_CAND_GANA,
                       IND_META_SUPE,
                       VAL_REQU_PREM_SUPE,
                       PERD_OID_PERI,
                       COPA_OID_PARA_GRAL,
                       BINC_OID_BASE_INCU,
                       PERD_OID_PERI_EVAL,
                       CLIE_OID_CLIE,
                       FEC_ULTI_ACTU,
                       NUM_PERI_EVAL,
                       REGL_OID_REGL)
                    VALUES
                      (INC_CAGA_SEQ.NEXTVAL,
                       0,
                       0,
                       lnOidPeriodo,
                       lnOidConcursoDestino,
                       NULL,
                       NULL,
                       lnOidCliente,
                       SYSDATE,
                       1,
                       NULL);
                  ELSE
                    UPDATE INC_CANDI_GANAD
                       SET NUM_PERI_EVAL = lnUltevaluacion + 1
                     WHERE COPA_OID_PARA_GRAL = lnOidConcursoDestino
                       AND CLIE_OID_CLIE = lnOidCliente
                       AND PERD_OID_PERI = lnOidPeriodo;
                  END IF;
                
                  FOR y IN (SELECT *
                              FROM INC_CUENT_CORRI_PUNTO
                             WHERE CLIE_OID_CLIE = lnOidCliente
                               AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen) LOOP
                    --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
                  
                    lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                  
                    INSERT INTO INC_CUENT_CORRI_PUNTO
                      (OID_CUEN_CORR_PUNT,
                       NUM_MOVI,
                       NUM_PUNT,
                       NUM_PUNT_EXIG,
                       FEC_MOVI,
                       COPA_OID_PARA_GRAL,
                       CLIE_OID_CLIE,
                       PERD_OID_PERI,
                       TMOV_OID_TIPO_MOVI,
                       FEC_ULTI_ACTU,
                       VAL_DESC,
                       USU_MODI,
                       NUM_PUNT_BONI,
                       COPA_OID_MIGR_ORIG)
                    VALUES
                      (lnOidSecuencia,
                       lnOidSecuencia,
                       y.NUM_PUNT,
                       y.NUM_PUNT_EXIG,
                       y.FEC_MOVI,
                       lnOidConcursoDestino,
                       y.CLIE_OID_CLIE,
                       y.PERD_OID_PERI,
                       y.TMOV_OID_TIPO_MOVI,
                       SYSDATE,
                       y.VAL_DESC,
                       psCodigoUsuario,
                       y.NUM_PUNT_BONI,
                       lnOidConcursoOrigen);
                  
                  END LOOP;
                
                  /*Actualizar la tabla inc_solic_concu_punta*/
                  UPDATE INC_SOLIC_CONCU_PUNTA
                     SET COPA_OID_PARA_GRAL = lnOidConcursoDestino
                   WHERE CLIE_OID_CLIE = lnOidCliente
                     AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen;
                
                  /*En la cuenta corriente del concurso origen se debe crear un registro de cargo
                  por todo el saldo disponible de puntos y puntos exigidos*/
                  lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
                
                  INSERT INTO INC_CUENT_CORRI_PUNTO
                    (OID_CUEN_CORR_PUNT,
                     NUM_MOVI,
                     NUM_PUNT,
                     NUM_PUNT_EXIG,
                     FEC_MOVI,
                     COPA_OID_PARA_GRAL,
                     CLIE_OID_CLIE,
                     PERD_OID_PERI,
                     TMOV_OID_TIPO_MOVI,
                     FEC_ULTI_ACTU,
                     VAL_DESC,
                     USU_MODI)
                  VALUES
                    (lnOidSecuencia,
                     lnOidSecuencia,
                     lnTotalPuntaje * (-1),
                     lnTotalPuntajeExig * (-1),
                     TRUNC(SYSDATE),
                     lnOidConcursoOrigen,
                     lnOidCliente,
                     lnOidPeriodo,
                     2,
                     SYSDATE,
                     'Cargo por migración a concurso: ' ||
                     psNumeroConcursoDestino || ' ' ||
                     lsNombreConcursoDestino,
                     psCodigoUsuario);
                
                  /*Se actualiza en la entidad de Candidatas Ganadoras */
                  UPDATE INC_CANDI_GANAD
                     SET VAL_REQU_PREM_SUPE = 1,
                         COPA_OID_MIGR_DEST = lnOidConcursoDestino
                   WHERE CLIE_OID_CLIE = lnOidCliente
                     AND COPA_OID_PARA_GRAL = lnOidConcursoOrigen;
                
                ELSE
                  lsCodigoMotivo := '06';
                END IF;
              
              ELSE
                lsCodigoMotivo := '05';
              END IF;
            
            ELSE
              lsCodigoMotivo := '04';
            END IF;
          
          ELSE
            lsCodigoMotivo := '03';
          END IF;
        
          IF (lnNumeroFila IS NOT NULL) THEN
            --Actualiza la fila procesada
            UPDATE INC_CARGA_MIGRA_PUNTO
               SET FEC_PROC = SYSDATE, COD_MOTI = lsCodigoMotivo
             WHERE NUM_CARG = pnNumeroCarga
               AND NUM_FILA = lnNumeroFila;
          ELSE
            IF (lsCodigoMotivo IS NOT NULL) THEN
              lnNumeroFila := lnContadorFila;
            
              INSERT INTO INC_CARGA_MIGRA_PUNTO
                (NUM_CARG,
                 NUM_FILA,
                 COD_CLIE,
                 FEC_CARG,
                 IND_VALI,
                 COD_MOTI,
                 FEC_PROC,
                 COD_USUA)
              VALUES
                (pnNumeroCarga,
                 lnNumeroFila,
                 lsCodigoCliente,
                 SYSDATE,
                 0,
                 lsCodigoMotivo,
                 SYSDATE,
                 psCodigoUsuario);
            
              lnContadorFila := lnContadorFila + 1;
            END IF;
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;
    CLOSE c_clientes;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_MIGRA_PUNTO_CONSU: (' ||
                              lsCodigoCliente || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END INC_PR_ACTUA_MIGRA_PUNTO_CONSU;

  /**************************************************************************
  Descripcion       : Clasifica los pagos de los concursos de ventas en gestión,
                      se les genera Abono Automático en su cuenta corriente
  Fecha Creacion    : 06/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_CLASI_PAGOS_CONSU(psCodigoPais    VARCHAR2,
                                     psCodigoMarca   VARCHAR2,
                                     psCodigoCanal   VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psCodigoUsuario VARCHAR2) IS
    CURSOR c_pedidos(oidPeriodo     NUMBER,
                     codigoPago     VARCHAR2,
                     numeroConcurso VARCHAR2) IS
      SELECT cli.OID_CLIE,
             cli.COD_CLIE,
             cab.NUM_PREM,
             (SELECT NVL(SUM(ccp.Num_Punt), 0)
                FROM INC_CUENT_CORRI_PUNTO ccp
               WHERE ccp.Clie_Oid_Clie = cli.oid_clie
                 AND ccp.copa_oid_para_gral = cpg.oid_para_gral
                 AND ccp.tmov_oid_tipo_movi = 1) PUN_OBTE,
             (SELECT NVL(SUM(ccp.Num_Punt), 0)
                FROM INC_CUENT_CORRI_PUNTO ccp
               WHERE ccp.Clie_Oid_Clie = cli.oid_clie
                 AND ccp.copa_oid_para_gral = cpg.oid_para_gral
                 AND ccp.tmov_oid_tipo_movi = 2) PUN_CARG
        FROM PED_SOLIC_CABEC       cab,
             INC_CONCU_PARAM_GENER cpg,
             MAE_CLIEN             cli,
             PED_SOLIC_CABEC       con
       WHERE cli.OID_CLIE = cab.CLIE_OID_CLIE
         AND cpg.OID_PARA_GRAL = cab.COPA_OID_PARA_GENE
         AND cab.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
         AND cab.PERD_OID_PERI = oidPeriodo
         AND cpg.NUM_CONC = numeroConcurso
         AND cab.fec_fact IS NOT NULL
         AND con.ESSO_OID_ESTA_SOLI <> 4
         AND cab.num_prem in
             (select NUM_PREM
                from INC_CONCU_PAGOS_BONO_PREMI
               where pgpa_cod_pago = codigoPago)
         AND NOT EXISTS (SELECT 1
                FROM INC_CONCU_PAGOS_DETAL det
               WHERE det.Pais_Cod_Pais = psCodigoPais
                 AND det.pgpa_cod_pago = codigoPago
                 AND det.Copa_Num_Concu = numeroConcurso
                 AND det.Clie_Cod_Clie = cli.Cod_Clie
                 AND det.Ind_Esta_Pago IN ('2', '3', '4')
                 AND det.est_regi = '1');
  
    --se define un tipo de dato tipo Tabla de Registros de los pedidos
    TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
    --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
    pedidoReg RegTab;
  
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lsTipoClasificacion INC_CONCU_PAGOS_DETAL.TCCL_COD_TIPO_CLASI%TYPE;
    lsClasificacion     INC_CONCU_PAGOS_DETAL.CLAS_COD_CLASI%TYPE;
    lsTipoAbono         INC_CONCU_PAGOS_DETAL.PGTA_COD_TIPO_ABON%TYPE;
    lnNumeroNivel       INC_CONCU_PAGOS_DETAL.NUM_NIVE%TYPE;
    lnMontoNivel        NUMBER;
  
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    DELETE FROM INC_CONCU_PAGOS_DETAL
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND IND_ESTA_PAGO = '1'
       AND EST_REGI = '1';
  
    FOR y IN (SELECT COD_PAGO, COPA_NUM_CONC, TCCL_COD_TIPO_CLASI
                FROM INC_CONCU_PAGOS_PARAM
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND IND_ESTA_PAGO = '1'
                 AND EST_REGI = '1') LOOP
    
      OPEN c_pedidos(lnOidPeriodo, y.COD_PAGO, y.COPA_NUM_CONC);
      LOOP
        FETCH c_pedidos BULK COLLECT
          INTO pedidoReg LIMIT W_FILAS;
      
        IF pedidoReg.COUNT > 0 THEN
          FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
            --Obtenemos el Tipo de Clasificacion y Clasificacion de la Consultora
            BEGIN
              SELECT tcc.cod_tipo_clas, cla.cod_clas
                INTO lsTipoClasificacion, lsClasificacion
                FROM MAE_CLIEN_TIPO_SUBTI sub,
                     MAE_CLIEN_CLASI      ccl,
                     MAE_TIPO_CLASI_CLIEN tcc,
                     MAE_CLASI            cla
               WHERE sub.oid_clie_tipo_subt = ccl.ctsu_oid_clie_tipo_subt
                 AND tcc.oid_tipo_clas = ccl.tccl_oid_tipo_clasi
                 AND cla.oid_clas = ccl.clas_oid_clas
                 AND tcc.cod_tipo_clas = y.TCCL_COD_TIPO_CLASI
                 AND sub.Clie_Oid_Clie = pedidoReg(x).OID_CLIE;
            EXCEPTION
              WHEN OTHERS THEN
                lsTipoClasificacion := NULL;
                lsClasificacion     := NULL;
            END;
          
            --Obtenemos el Tipo de Abono
            IF (lsTipoClasificacion IS NOT NULL) THEN
              lsTipoAbono := '001';
            ELSE
              SELECT PGTA_COD_TIPO_ABON
                INTO lsTipoAbono
                FROM INC_CONCU_PAGOS_BONO_PREMI
               WHERE PGPA_COD_PAGO = y.COD_PAGO
                 AND NUM_PREM = pedidoReg(x).NUM_PREM;
            END IF;
          
            --Obtenemos el Nivel y Monto Obtenido
            SELECT pnp.num_nive, pnp.Num_Cant_Inic_Punt
              INTO lnNumeroNivel, lnMontoNivel
              FROM INC_CONCU_PARAM_GENER con,
                   INC_PARAM_GENER_PREMI pgp,
                   INC_PARAM_NIVEL_PREMI pnp,
                   INC_PREMI_ARTIC       pre,
                   INC_LOTE_PREMI_ARTIC  lot
             WHERE con.oid_para_gral = pgp.copa_oid_para_gral
               AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
               AND pnp.oid_para_nive_prem = pre.panp_oid_para_nive_prem
               AND pre.oid_prem_arti = lot.prar_oid_prem_arti
               AND lot.num_prem = pedidoReg(x).NUM_PREM
               AND con.Num_Conc = y.COPA_NUM_CONC;
          
            INSERT INTO INC_CONCU_PAGOS_DETAL
              (PAIS_COD_PAIS,
               PGPA_COD_PAGO,
               COPA_NUM_CONCU,
               CLIE_COD_CLIE,
               PGTA_COD_TIPO_ABON,
               TCCL_COD_TIPO_CLASI,
               CLAS_COD_CLASI,
               NUM_NIVE,
               VAL_PUNT_OBTE,
               VAL_PUNT_REST,
               VAL_MONT_NIVE,
               VAL_MONT_PROC,
               IND_ESTA_PAGO,
               CAM_EVAL,
               FEC_ENVI,
               FEC_PROC,
               USU_CREA,
               FEC_CREA,
               EST_REGI)
            VALUES
              (psCodigoPais,
               y.COD_PAGO,
               y.COPA_NUM_CONC,
               pedidoReg          (x).COD_CLIE,
               lsTipoAbono,
               lsTipoClasificacion,
               lsClasificacion,
               lnNumeroNivel,
               pedidoReg          (x).PUN_OBTE,
               pedidoReg          (x).PUN_CARG,
               lnMontoNivel,
               0,
               '1',
               psCodigoPeriodo,
               NULL,
               NULL,
               psCodigoUsuario,
               SYSDATE,
               '1');
          
          END LOOP;
        END IF;
      
        EXIT WHEN c_pedidos%NOTFOUND;
      END LOOP;
      CLOSE c_pedidos;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CLASI_PAGOS_CONSU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_CLASI_PAGOS_CONSU;

  /**************************************************************************
  Descripcion       : Generar Abono Directo Cuenta Corriente
  Fecha Creacion    : 14/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion : Fecha Facturacion
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_GENER_ABONO_DIREC(psCodigoPais       VARCHAR2,
                                     psCodigoMarca      VARCHAR2,
                                     psCodigoCanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psFechaFacturacion VARCHAR2,
                                     psCodigoUsuario    VARCHAR2) IS
    CURSOR c_pedidos(codigoPago VARCHAR2) IS
      SELECT CLIE_COD_CLIE, VAL_MONT_NIVE
        FROM INC_CONCU_PAGOS_DETAL
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND PGPA_COD_PAGO = codigoPago
         AND PGTA_COD_TIPO_ABON = '001'
         AND IND_ESTA_PAGO IN ('1', '2')
         AND EST_REGI = '1';
  
    --se define un tipo de dato tipo Tabla de Registros de los pedidos
    TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
    --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
    pedidoReg RegTab;
  
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    lnResultado NUMBER;
  BEGIN
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    FOR y IN (SELECT COD_PAGO, COPA_NUM_CONC
                FROM INC_CONCU_PAGOS_PARAM
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND psCodigoPeriodo >= CAM_INIC
                 AND psCodigoPeriodo <= CAM_FINA
                 AND IND_ESTA_PAGO = '1'
                 AND EST_REGI = '1') LOOP
    
      OPEN c_pedidos(y.COD_PAGO);
      LOOP
        FETCH c_pedidos BULK COLLECT
          INTO pedidoReg LIMIT W_FILAS;
      
        IF pedidoReg.COUNT > 0 THEN
          FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
          
            /*llamamos a la funcion de Cuenta Corriente: CCC_XX*/
            CCC_PKG_PROCE.CCC_PR_GENER_CARGO_ABONO(psCodigoPeriodo,
                                                   pedidoReg(x)
                                                   .CLIE_COD_CLIE,
                                                   psFechaFacturacion,
                                                   'ABONO DIRECTO CONCURSO ' ||
                                                   y.COPA_NUM_CONC,
                                                   pedidoReg(x)
                                                   .VAL_MONT_NIVE,
                                                   lnResultado);
          
            IF (lnResultado = 1) THEN
              UPDATE INC_CONCU_PAGOS_DETAL
                 SET VAL_MONT_PROC = pedidoReg(x).VAL_MONT_NIVE,
                     IND_ESTA_PAGO = '4',
                     FEC_ENVI      = SYSDATE,
                     FEC_PROC      = SYSDATE,
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND PGPA_COD_PAGO = y.COD_PAGO
                 AND CLIE_COD_CLIE = pedidoReg(x).CLIE_COD_CLIE
                 AND PGTA_COD_TIPO_ABON = '001'
                 AND IND_ESTA_PAGO IN ('1', '2')
                 AND EST_REGI = '1';
            END IF;
          
          END LOOP;
        END IF;
      
        EXIT WHEN c_pedidos%NOTFOUND;
      END LOOP;
      CLOSE c_pedidos;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_GENER_ABONO_DIREC: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_GENER_ABONO_DIREC;

  /**************************************************************************
  Descripcion       : Actualiza Transacciones Pago Concurso
  Fecha Creacion    : 16/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    pnNumeroCarga    :  Numero Carga
    psCodigoPeriodo  :  Codigo de periodo
    psFechaFacturacion : Fecha Facturacion
    psCodigoPago       : Codigo Pago
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_ACTUA_TRANS_PAGO_CONCU(psCodigoPais       VARCHAR2,
                                          pnNumeroCarga      NUMBER,
                                          psCodigoPeriodo    VARCHAR2,
                                          psFechaFacturacion VARCHAR2,
                                          psCodigoPago       VARCHAR2,
                                          psCodigoUsuario    VARCHAR2) IS
  
    CURSOR c_clientes IS
      SELECT tpc.NUM_FILA,
             tpc.NUM_CONC,
             tpc.NUM_DOCU,
             cli.COD_CLIE,
             tpc.VAL_MONT,
             tpc.FEC_ABON,
             tpc.EST_ABON
        FROM INC_CONCU_PAGOS_PARAM      cpp,
             INC_RECEP_TRANS_PAGO_CONCU tpc,
             MAE_CLIEN_IDENT            ide,
             MAE_CLIEN                  cli
       WHERE cpp.COD_PAGO = psCodigoPago
         AND tpc.NUM_CARG = pnNumeroCarga
         AND cpp.COPA_NUM_CONC = tpc.NUM_CONC
         AND tpc.NUM_DOCU = ide.NUM_DOCU_IDEN
         AND ide.CLIE_OID_CLIE = cli.OID_CLIE
         AND ide.VAL_IDEN_DOCU_PRIN = 1;
  
    TYPE interfazClientes IS RECORD(
      numeroFila      INC_RECEP_TRANS_PAGO_CONCU.NUM_FILA%TYPE,
      numeroConcurso  INC_RECEP_TRANS_PAGO_CONCU.NUM_CONC%TYPE,
      numeroDocumento INC_RECEP_TRANS_PAGO_CONCU.NUM_DOCU%TYPE,
      codigoCliente   MAE_CLIEN.COD_CLIE%TYPE,
      montoAbono      INC_RECEP_TRANS_PAGO_CONCU.VAL_MONT%TYPE,
      fechaAbono      INC_RECEP_TRANS_PAGO_CONCU.FEC_ABON%TYPE,
      estadoAbono     INC_RECEP_TRANS_PAGO_CONCU.EST_ABON%TYPE);
  
    TYPE interfazClientesTab IS TABLE OF interfazClientes;
    interfazRecordN interfazClientesTab;
  
    lnOidPais     SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca    SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal    SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo  CRA_PERIO.OID_PERI%TYPE;
    lnOcurrencias NUMBER;
  
  BEGIN
  
    --Recuperamos el oid Pais
    lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    --Obtenemos el Oid Periodo
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
  
    --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_clientes;
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN
      
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          IF (interfazRecordN(x).estadoAbono = '0') THEN
          
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_CONCU_PAGOS_DETAL det
             WHERE det.pais_cod_pais = psCodigoPais
               AND det.pgpa_cod_pago = psCodigoPago
               AND det.copa_num_concu = interfazRecordN(x).numeroConcurso
               AND det.clie_cod_clie = interfazRecordN(x).codigoCliente
               AND det.pgta_cod_tipo_abon = '002'
               AND det.ind_esta_pago = '3'
               AND det.est_regi = '1';
          
            IF (lnOcurrencias > 0) THEN
            
              INSERT INTO INC_CONCU_PAGOS_DETAL
                (PAIS_COD_PAIS,
                 PGPA_COD_PAGO,
                 COPA_NUM_CONCU,
                 CLIE_COD_CLIE,
                 PGTA_COD_TIPO_ABON,
                 TCCL_COD_TIPO_CLASI,
                 CLAS_COD_CLASI,
                 NUM_NIVE,
                 VAL_PUNT_OBTE,
                 VAL_PUNT_REST,
                 VAL_MONT_NIVE,
                 VAL_MONT_PROC,
                 IND_ESTA_PAGO,
                 CAM_EVAL,
                 FEC_ENVI,
                 FEC_PROC,
                 USU_CREA,
                 FEC_CREA,
                 EST_REGI)
                SELECT det.pais_cod_pais,
                       det.pgpa_cod_pago,
                       det.copa_num_concu,
                       det.clie_cod_clie,
                       '001',
                       det.tccl_cod_tipo_clasi,
                       det.clas_cod_clasi,
                       det.num_nive,
                       det.val_punt_obte,
                       det.val_punt_rest,
                       interfazRecordN(x).montoAbono,
                       NULL,
                       '1',
                       psCodigoPeriodo,
                       NULL,
                       NULL,
                       psCodigoUsuario,
                       SYSDATE,
                       '1'
                  FROM INC_CONCU_PAGOS_DETAL det
                 WHERE det.pais_cod_pais = psCodigoPais
                   AND det.pgpa_cod_pago = psCodigoPago
                   AND det.copa_num_concu = interfazRecordN(x)
                      .numeroConcurso
                   AND det.clie_cod_clie = interfazRecordN(x).codigoCliente
                   AND det.pgta_cod_tipo_abon = '002'
                   AND det.ind_esta_pago = '3'
                   AND det.est_regi = '1';
            
              UPDATE INC_CONCU_PAGOS_DETAL
                 SET IND_ESTA_PAGO = '9',
                     FEC_PROC      = TO_DATE(psFechaFacturacion,
                                             'dd/MM/yyyy'),
                     USU_MODI      = psCodigoUsuario,
                     FEC_MODI      = SYSDATE
               WHERE pais_cod_pais = psCodigoPais
                 AND pgpa_cod_pago = psCodigoPago
                 AND copa_num_concu = interfazRecordN(x).numeroConcurso
                 AND clie_cod_clie = interfazRecordN(x).codigoCliente
                 AND pgta_cod_tipo_abon = '002'
                 AND ind_esta_pago = '3'
                 AND est_regi = '1';
            END IF;
          END IF;
        
          IF (interfazRecordN(x).estadoAbono = '1') THEN
            UPDATE INC_CONCU_PAGOS_DETAL
               SET IND_ESTA_PAGO = '4',
                   VAL_MONT_PROC = interfazRecordN(x).montoAbono,
                   FEC_PROC      = SYSDATE,
                   USU_MODI      = psCodigoUsuario,
                   FEC_MODI      = SYSDATE
             WHERE pais_cod_pais = psCodigoPais
               AND pgpa_cod_pago = psCodigoPago
               AND copa_num_concu = interfazRecordN(x).numeroConcurso
               AND clie_cod_clie = interfazRecordN(x).codigoCliente
               AND pgta_cod_tipo_abon = '002'
               AND ind_esta_pago = '3'
               AND est_regi = '1';
          END IF;
        
          --Actualiza la fila procesada
          UPDATE INC_RECEP_TRANS_PAGO_CONCU
             SET FEC_PROC = SYSDATE
           WHERE NUM_CARG = pnNumeroCarga
             AND NUM_FILA = interfazRecordN(x).numeroFila;
        
        END LOOP;
      END IF;
      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;
    CLOSE c_clientes;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACTUA_TRANS_PAGO_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END INC_PR_ACTUA_TRANS_PAGO_CONCU;

  /**************************************************************************
  Descripcion       : Actualiza Histórico de Incentivos
  Fecha Creacion    : 16/10/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de periodo
  Autor             : Juan Gutiérrez
  ***************************************************************************/
  PROCEDURE INC_PR_MOVER_INFOR_HISTO(psCodigoPais    VARCHAR2,
                                     psCodigoPeriodo VARCHAR2) IS
  
    CURSOR c_buzon_punto IS
      SELECT ROWID
        FROM inc_cuent_corri_punto
       WHERE copa_oid_para_gral in
             (SELECT DISTINCT oid_para_gral
                FROM inc_versi_concu, inc_concu_param_gener
               WHERE 1 = 1
                 AND (ind_acti = 0)
                 AND copa_oid_para_gral = oid_para_gral
                 AND (perd_oid_peri_hast <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(psCodigoPeriodo,
                                                                              -9))));
  
    TYPE vIDPTO IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tIDPTO vIDPTO;
  
    CURSOR c_buzon_punta IS
      SELECT ROWID
        FROM inc_solic_concu_punta
       WHERE copa_oid_para_gral in
             (SELECT DISTINCT oid_para_gral
                FROM inc_versi_concu, inc_concu_param_gener
               WHERE 1 = 1
                 AND (ind_acti = 0)
                 AND copa_oid_para_gral = oid_para_gral
                 AND (perd_oid_peri_hast <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(psCodigoPeriodo,
                                                                              -9))));
  
    TYPE vIDPTA IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tIDPTA vIDPTA;
  
    CURSOR c_buzon_mensa IS
      SELECT ROWID
        FROM inc_solic_concu_mensa
       WHERE copa_oid_para_gral in
             (SELECT DISTINCT oid_para_gral
                FROM inc_versi_concu, inc_concu_param_gener
               WHERE 1 = 1
                 AND (ind_acti = 0)
                 AND copa_oid_para_gral = oid_para_gral
                 AND (perd_oid_peri_hast <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(psCodigoPeriodo,
                                                                              -9))));
  
    TYPE vIDMEN IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tIDMEN vIDMEN;
  
    CURSOR c_buzon_candi IS
      SELECT ROWID
        FROM inc_candi_ganad
       WHERE copa_oid_para_gral in
             (SELECT DISTINCT oid_para_gral
                FROM inc_versi_concu, inc_concu_param_gener
               WHERE 1 = 1
                 AND (ind_acti = 0)
                 AND copa_oid_para_gral = oid_para_gral
                 AND (perd_oid_peri_hast <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(gen_fn_calcu_perio(psCodigoPeriodo,
                                                                              -9))));
  
    TYPE vIDCAN IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tIDCAN vIDCAN;
  
    CURSOR c_buzon_ganad IS
      SELECT ROWID
        FROM inc_ganad
       WHERE panp_oid_para_nive_prem in
             (select distinct oid_para_nive_prem
                from inc_versi_concu       versi,
                     inc_concu_param_gener param,
                     inc_param_gener_premi gener,
                     inc_param_nivel_premi nivel
               where 1 = 1
                 AND (param.ind_acti = 0)
                 and versi.copa_oid_para_gral = param.oid_para_gral
                 and gener.copa_oid_para_gral = param.oid_para_gral
                 and oid_para_gene_prem = pagp_oid_para_gene_prem
                 and (perd_oid_peri_hast <=
                     FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(GEN_FN_CALCU_PERIO(psCodigoPeriodo,
                                                                              -9))));
    TYPE vIDGAN IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tIDGAN vIDGAN;
  
  BEGIN
    -- INSERCIONES EN HISTORICO
    -- cuenta corriente
    OPEN c_buzon_punto;
    LOOP
      FETCH c_buzon_punto BULK COLLECT
        INTO tIDPTO LIMIT 10000;
      EXIT WHEN c_buzon_punto%NOTFOUND;
      FORALL i IN tIDPTO.FIRST .. tIDPTO.LAST
        INSERT INTO INC_HISTO_CUENT_CORRI_PUNTO
          SELECT OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG
            FROM inc_cuent_corri_punto
           WHERE ROWID = tIDPTO(i);
    END LOOP;
  
    IF tIDPTO.COUNT > 0 THEN
      FORALL i IN tIDPTO.FIRST .. tIDPTO.LAST
        INSERT INTO INC_HISTO_CUENT_CORRI_PUNTO
          SELECT OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG
            FROM inc_cuent_corri_punto
           WHERE ROWID = tIDPTO(i);
    END IF;
    CLOSE c_buzon_punto;
  
    -- puntaje concurso
  
    OPEN c_buzon_punta;
    LOOP
      FETCH c_buzon_punta BULK COLLECT
        INTO tIDPTA LIMIT 10000;
      EXIT WHEN c_buzon_punta%NOTFOUND;
      FORALL i IN tIDPTA.FIRST .. tIDPTA.LAST
        INSERT INTO INC_HISTO_SOLIC_CONCU_PUNTA
          SELECT OID_SOLI_CONC_PUNT,
                 NUM_PUNT,
                 VAL_PUNT_BONI,
                 VAL_PUNT_FALT_NANU,
                 FEC_DOCU,
                 IND_ANUL,
                 COPA_OID_PARA_GRAL,
                 SOCA_OID_SOLI_CABE,
                 PERD_OID_PERI,
                 CLIE_OID_CLIE,
                 IMP_MONT,
                 CLIE_OID_CLIE_GERE,
                 NUM_UNID,
                 NUM_PUNT_EXIG
            FROM INC_SOLIC_CONCU_PUNTA
           WHERE ROWID = tIDPTA(i);
    END LOOP;
  
    IF tIDPTA.COUNT > 0 THEN
      FORALL i IN tIDPTA.FIRST .. tIDPTA.LAST
        INSERT INTO INC_HISTO_SOLIC_CONCU_PUNTA
          SELECT OID_SOLI_CONC_PUNT,
                 NUM_PUNT,
                 VAL_PUNT_BONI,
                 VAL_PUNT_FALT_NANU,
                 FEC_DOCU,
                 IND_ANUL,
                 COPA_OID_PARA_GRAL,
                 SOCA_OID_SOLI_CABE,
                 PERD_OID_PERI,
                 CLIE_OID_CLIE,
                 IMP_MONT,
                 CLIE_OID_CLIE_GERE,
                 NUM_UNID,
                 NUM_PUNT_EXIG
            FROM INC_SOLIC_CONCU_PUNTA
           WHERE ROWID = tIDPTA(i);
    END IF;
    CLOSE c_buzon_punta;
  
    --- MENSAJES
    OPEN c_buzon_mensa;
    LOOP
      FETCH c_buzon_mensa BULK COLLECT
        INTO tIDMEN LIMIT 10000;
      EXIT WHEN c_buzon_mensa%NOTFOUND;
      FORALL i IN tIDMEN.FIRST .. tIDMEN.LAST
        INSERT INTO INC_HISTO_SOLIC_CONCU_MENSA
          SELECT OID_SOLI_CONC_MENS,
                 VAL_BUZO_MENS,
                 SOCA_OID_SOLI_CABE,
                 COPA_OID_PARA_GRAL
            FROM INC_SOLIC_CONCU_MENSA
           WHERE ROWID = tIDMEN(i);
    END LOOP;
  
    IF tIDMEN.COUNT > 0 THEN
      FORALL i IN tIDMEN.FIRST .. tIDMEN.LAST
        INSERT INTO INC_HISTO_SOLIC_CONCU_MENSA
          SELECT OID_SOLI_CONC_MENS,
                 VAL_BUZO_MENS,
                 SOCA_OID_SOLI_CABE,
                 COPA_OID_PARA_GRAL
            FROM INC_SOLIC_CONCU_MENSA
           WHERE ROWID = tIDMEN(i);
    END IF;
    CLOSE c_buzon_mensa;
  
    -- candidatos
  
    OPEN c_buzon_candi;
    LOOP
      FETCH c_buzon_candi BULK COLLECT
        INTO tIDCAN LIMIT 10000;
      EXIT WHEN c_buzon_candi%NOTFOUND;
      FORALL i IN tIDCAN.FIRST .. tIDCAN.LAST
      
        INSERT INTO INC_HISTO_CANDI_GANAD
          SELECT OID_CAND_GANA,
                 IND_META_SUPE,
                 VAL_REQU_PREM_SUPE,
                 PERD_OID_PERI,
                 COPA_OID_PARA_GRAL,
                 BINC_OID_BASE_INCU,
                 PERD_OID_PERI_EVAL,
                 CLIE_OID_CLIE,
                 FEC_ULTI_ACTU,
                 NUM_PERI_EVAL,
                 REGL_OID_REGL,
                 COPA_OID_MIGR_DEST
            FROM INC_CANDI_GANAD
           WHERE ROWID = tIDCAN(i);
    END LOOP;
  
    IF tIDCAN.COUNT > 0 THEN
      FORALL i IN tIDCAN.FIRST .. tIDCAN.LAST
      
        INSERT INTO INC_HISTO_CANDI_GANAD
          SELECT OID_CAND_GANA,
                 IND_META_SUPE,
                 VAL_REQU_PREM_SUPE,
                 PERD_OID_PERI,
                 COPA_OID_PARA_GRAL,
                 BINC_OID_BASE_INCU,
                 PERD_OID_PERI_EVAL,
                 CLIE_OID_CLIE,
                 FEC_ULTI_ACTU,
                 NUM_PERI_EVAL,
                 REGL_OID_REGL,
                 COPA_OID_MIGR_DEST
            FROM INC_CANDI_GANAD
           WHERE ROWID = tIDCAN(i);
    END IF;
    CLOSE c_buzon_candi;
  
    -- ganadoras
    OPEN c_buzon_ganad;
    LOOP
      FETCH c_buzon_ganad BULK COLLECT
        INTO tIDGAN LIMIT 10000;
      EXIT WHEN c_buzon_ganad%NOTFOUND;
      FORALL i IN tIDGAN.FIRST .. tIDGAN.LAST
        INSERT INTO INC_HISTO_GANAD
          SELECT OID_GANA,
                 FEC_OBTE,
                 NUM_UNID,
                 IND_LIST_GANA,
                 IND_DESC,
                 IND_DESP,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 PANP_OID_PARA_NIVE_PREM,
                 SOCA_OID_SOLI_CABE,
                 IND_CLIE_BLOQ
            FROM INC_GANAD
           WHERE ROWID = tIDGAN(i);
    
    END LOOP;
  
    IF tIDGAN.COUNT > 0 THEN
      FORALL i IN tIDGAN.FIRST .. tIDGAN.LAST
        INSERT INTO INC_HISTO_GANAD
          SELECT OID_GANA,
                 FEC_OBTE,
                 NUM_UNID,
                 IND_LIST_GANA,
                 IND_DESC,
                 IND_DESP,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 PANP_OID_PARA_NIVE_PREM,
                 SOCA_OID_SOLI_CABE,
                 IND_CLIE_BLOQ
            FROM INC_GANAD
           WHERE ROWID = tIDGAN(i);
    END IF;
    CLOSE c_buzon_ganad;
  
    COMMIT;
  
    -- DEPURACION DE TABLAS
    -- TEMPORALES
    delete INC_PEDID_CONCU_RECOM_TEMP;
    COMMIT;
    delete INC_SOLIC_CONCU_RECOM_TEMP;
    COMMIT;
    delete INC_SOLIC_CONCU_PUNTA_TEMP;
    COMMIT;
  
    -- cuenta corriente
    OPEN c_buzon_punto;
    LOOP
      FETCH c_buzon_punto BULK COLLECT
        INTO tIDPTO LIMIT 10000;
      EXIT WHEN c_buzon_punto%NOTFOUND;
      FORALL i IN tIDPTO.FIRST .. tIDPTO.LAST
        DELETE INC_CUENT_CORRI_PUNTO WHERE ROWID = tIDPTO(i);
      COMMIT;
    END LOOP;
  
    IF tIDPTO.COUNT > 0 THEN
      FORALL i IN tIDPTO.FIRST .. tIDPTO.LAST
        DELETE INC_CUENT_CORRI_PUNTO WHERE ROWID = tIDPTO(i);
      COMMIT;
    END IF;
  
    CLOSE c_buzon_punto;
  
    -- puntaje concurso
    OPEN c_buzon_punta;
    LOOP
      FETCH c_buzon_punta BULK COLLECT
        INTO tIDPTA LIMIT 10000;
      EXIT WHEN c_buzon_punta%NOTFOUND;
      FORALL i IN tIDPTA.FIRST .. tIDPTA.LAST
        DELETE INC_SOLIC_CONCU_PUNTA WHERE ROWID = tIDPTA(i);
      COMMIT;
    END LOOP;
  
    IF tIDPTA.COUNT > 0 THEN
      FORALL i IN tIDPTA.FIRST .. tIDPTA.LAST
        DELETE INC_SOLIC_CONCU_PUNTA WHERE ROWID = tIDPTA(i);
      COMMIT;
    END IF;
  
    CLOSE c_buzon_punta;
  
    -- mensaje
    OPEN c_buzon_mensa;
    LOOP
      FETCH c_buzon_mensa BULK COLLECT
        INTO tIDMEN LIMIT 10000;
      EXIT WHEN c_buzon_mensa%NOTFOUND;
      FORALL i IN tIDMEN.FIRST .. tIDMEN.LAST
        DELETE INC_SOLIC_CONCU_MENSA WHERE ROWID = tIDMEN(i);
      COMMIT;
    END LOOP;
  
    IF tIDMEN.COUNT > 0 THEN
      FORALL i IN tIDMEN.FIRST .. tIDMEN.LAST
        DELETE INC_SOLIC_CONCU_MENSA WHERE ROWID = tIDMEN(i);
      COMMIT;
    END IF;
  
    CLOSE c_buzon_mensa;
  
    -- candidatos
  
    OPEN c_buzon_candi;
    LOOP
      FETCH c_buzon_candi BULK COLLECT
        INTO tIDCAN LIMIT 10000;
      EXIT WHEN c_buzon_candi%NOTFOUND;
      FORALL i IN tIDCAN.FIRST .. tIDCAN.LAST
        DELETE inc_candi_ganad WHERE ROWID = tIDCAN(i);
      COMMIT;
    END LOOP;
  
    IF tIDCAN.COUNT > 0 THEN
      FORALL i IN tIDCAN.FIRST .. tIDCAN.LAST
        DELETE inc_candi_ganad WHERE ROWID = tIDCAN(i);
      COMMIT;
    END IF;
  
    CLOSE c_buzon_candi;
  
    -- ganadoras
    OPEN c_buzon_ganad;
    LOOP
      FETCH c_buzon_ganad BULK COLLECT
        INTO tIDGAN LIMIT 10000;
      EXIT WHEN c_buzon_ganad%NOTFOUND;
      FORALL i IN tIDGAN.FIRST .. tIDGAN.LAST
        DELETE INC_GANAD WHERE ROWID = tIDGAN(i);
    
      COMMIT;
    END LOOP;
  
    IF tIDGAN.COUNT > 0 THEN
      FORALL i IN tIDGAN.FIRST .. tIDGAN.LAST
        DELETE INC_GANAD WHERE ROWID = tIDGAN(i);
      COMMIT;
    END IF;
  
    CLOSE c_buzon_ganad;
  
  END INC_PR_MOVER_INFOR_HISTO;

  /**************************************************************************
  Descripcion       : Acumulación de puntos para Programa de Puntos
  Fecha Creacion    : 04/02/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de periodo
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_ACUMU_PUNTO_PROGR_PUNTO(psCodigoPais    VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psUsuario       VARCHAR2) IS
  
    lsCampanyaActiva        NUMBER;
    lnCantidadRegistros     NUMBER;
    lnOidPais               NUMBER;
    lnOidSoliCabe           NUMBER;
    lsResult                VARCHAR2(1);
    lnOcurrencias           NUMBER;
    lnTienesolic            NUMBER;
    lsCodigoPeriodoAnterior VARCHAR2(6);
    lsDescrGrupoTodas1      INC_CUENT_CORRI_PUNTO.VAL_DESC%TYPE := 'ABONO PUNTAJE DEL PERIODO GRUPO TODAS VENTA DIRECTA';
    lsDescrGrupoTodas2      INC_CUENT_CORRI_PUNTO.VAL_DESC%TYPE := 'ABONOPUNTAJE DEL PERIODOGRUPO TODAS VENTA DIRECTA';
  
    cursor c_puntos is
      SELECT CCP.OID_CUEN_CORR_PUNT,
             CCP.COPA_OID_PARA_GRAL,
             CCP.TMOV_OID_TIPO_MOVI,
             UPPER(CCP.VAL_DESC),
             CCP.CLIE_OID_CLIE,
             (SELECT MC.COD_CLIE
                FROM MAE_CLIEN MC
               WHERE MC.OID_CLIE = CCP.CLIE_OID_CLIE) COD_CLIE,
             CCP.PERD_OID_PERI,
             TO_CHAR(CCP.FEC_MOVI, 'YYYYMMDD'),
             NULL COD_CLAS,
             NULL NUM_MIPR,
             NULL TIP_CONC,
             CCP.COPA_OID_PARA_GRAL,
             CCP.DES_MOTI,
             CCP.NUM_PUNT PUNTOS,
             CCP.PERD_OID_PERI_REFE,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CCP.PERD_OID_PERI_REFE),
             CON.NUM_CONC
        FROM INC_CUENT_CORRI_PUNTO CCP, INC_CONCU_PARAM_GENER CON
       WHERE CCP.IND_APOR_PROG_PUNT IS NULL
         AND CON.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
         AND CCP.COPA_OID_PARA_GRAL IN
             (SELECT PG.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER PG
               WHERE PG.PAIS_OID_PAIS = lnOidPais
                 AND PG.IND_PROG_PUNT = 1
                 AND PG.IND_ACTI = 1
                 AND GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PG.PERD_OID_PERI_DESD) <=
                     lsCampanyaActiva);
  
    CURSOR c_traslado IS
      SELECT CCP.OID_CUEN_CORR_PUNT,
             CCP.COPA_OID_PARA_GRAL,
             CCP.TMOV_OID_TIPO_MOVI,
             UPPER(CCP.VAL_DESC) VAL_DESC,
             CCP.CLIE_OID_CLIE,
             (SELECT MC.COD_CLIE
                FROM MAE_CLIEN MC
               WHERE MC.OID_CLIE = CCP.CLIE_OID_CLIE) COD_CLIE,
             CCP.PERD_OID_PERI,
             TO_CHAR(CCP.FEC_MOVI, 'YYYYMMDD') FEC_MOVI,
             (SELECT CC.COD_CLAS_CONC
                FROM INC_CLASI_CONCU CC
               WHERE CC.OID_CLAS_CONC = PG.CCON_OID_CLAS_CONC) COD_CLAS,
             NVL((SELECT CPC.NUM_MINI_PEDI_RECO
                   FROM INC_CONCU_PARAM_CONSU CPC
                  WHERE CPC.COPA_OID_PARA_GRAL = PG.OID_PARA_GRAL),
                 1) NUM_MIPR,
             (SELECT ICPG.BCAL_OID_BASE_CALC
                FROM INC_CONCU_PARAM_GENER ICPG
               WHERE ICPG.OID_PARA_GRAL = PG.COPA_CONC_PROG_PUNT) TIP_CONC,
             PG.COPA_CONC_PROG_PUNT,
             CCP.DES_MOTI,
             DECODE(PG.BCAL_OID_BASE_CALC,
                    4,
                    NVL((CCP.NUM_PUNT * OBT.VAL_FACT_CONV /
                        OBT.NUM_PUNT_ASIG) * OBT.PUN_ABON_REFE_PROG_PUNT,
                        0),
                    CCP.NUM_PUNT) PUNTOS,
             CCP.PERD_OID_PERI_REFE,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CCP.PERD_OID_PERI_REFE),
             PG.NUM_CONC
        FROM INC_CUENT_CORRI_PUNTO CCP,
             INC_CONCU_PARAM_GENER PG,
             INC_OBTEN_PUNTO       OBT
       WHERE CCP.COPA_OID_PARA_GRAL = PG.OID_PARA_GRAL
         AND PG.PAIS_OID_PAIS = lnOidPais
         AND OBT.COPA_OID_PARA_GRAL = PG.OID_PARA_GRAL
         AND CCP.IND_APOR_PROG_PUNT IS NULL
         AND CCP.COPA_OID_PARA_GRAL IN
             (SELECT CPG.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER CPG
               WHERE CPG.PAIS_OID_PAIS = lnOidPais
                 AND CPG.IND_ACTI = 1
                 AND CPG.COPA_CONC_PROG_PUNT IS NOT NULL
                 AND lsCampanyaActiva >=
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(CPG.PERD_OID_PERI_DESD)
                 AND CPG.COPA_CONC_PROG_PUNT IN
                     (SELECT CPA.OID_PARA_GRAL
                        FROM INC_CONCU_PARAM_GENER CPA
                       WHERE CPA.PAIS_OID_PAIS = CPG.PAIS_OID_PAIS
                         AND CPA.IND_PROG_PUNT = 1
                         AND CPA.IND_ACTI = 1
                         AND lsCampanyaActiva >=
                             GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(CPA.PERD_OID_PERI_DESD)));
  
    TYPE puntosStruct IS RECORD(
      oidCtaCte            INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE,
      oidParamGener        INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      oidTipoMovimiento    INC_CUENT_CORRI_PUNTO.TMOV_OID_TIPO_MOVI%TYPE,
      valDescripcion       INC_CUENT_CORRI_PUNTO.VAL_DESC%TYPE,
      oidCliente           INC_CUENT_CORRI_PUNTO.CLIE_OID_CLIE%TYPE,
      codigoCliente        MAE_CLIEN.COD_CLIE%TYPE,
      oidPeriodo           INC_CUENT_CORRI_PUNTO.PERD_OID_PERI%TYPE,
      fechaMovimiento      VARCHAR2(8),
      codigoClasificacion  INC_CLASI_CONCU.COD_CLAS_CONC%TYPE,
      numeroMinimoPediReco INC_CONCU_PARAM_CONSU.NUM_MINI_PEDI_RECO%TYPE,
      tipoConcurso         INC_CONCU_PARAM_GENER.BCAL_OID_BASE_CALC%TYPE,
      oidParamGenerMadre   INC_CONCU_PARAM_GENER.COPA_CONC_PROG_PUNT%TYPE,
      desmotivo            INC_CUENT_CORRI_PUNTO.DES_MOTI%TYPE,
      puntoscc             INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE,
      OidPeriodoRefe       INC_CUENT_CORRI_PUNTO.PERD_OID_PERI_REFE%TYPE,
      CodPeriodoRefe       VARCHAR(6),
      CodConcurso          INC_CONCU_PARAM_GENER.NUM_CONC%TYPE);
  
    TYPE puntosTab IS TABLE OF puntosStruct;
    puntosRecordN puntosTab;
  
    ldFecha            DATE;
    lsFecha            VARCHAR2(10);
    lnOidPeriByFecha   NUMBER;
    lnOidSecuenciaIccp INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
    lnfacMultiplicidad NUMBER;
    lsPeriodoRefe      VARCHAR2(6);
  
  BEGIN
  
    lnOidPais               := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lsCodigoPeriodoAnterior := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                psCodigoPeriodo,
                                                                -1);
  
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE, 'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha, 'DD/MM/YYYY');
  
    SELECT MAX(OID_PERI)
      INTO lnOidPeriByFecha
      FROM CRA_PERIO A
     WHERE ldFecha >= A.FEC_INIC
       AND ldFecha <= A.FEC_FINA;
  
    BEGIN
      SELECT CF.COD_PERI
        INTO lsCampanyaActiva
        FROM BAS_CTRL_FACT CF
       WHERE CF.COD_PAIS = psCodigoPais
         AND CF.STA_CAMP = '0'
         AND CF.IND_CAMP_ACT = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsCampanyaActiva := '';
    END;
  
    -- Ajuste de puntos del Concurso Tipo Programa de Puntos
    SELECT COUNT(*)
      INTO lnCantidadRegistros
      FROM INC_CONCU_PARAM_GENER PG
     WHERE PG.PAIS_OID_PAIS = lnOidPais
       AND PG.IND_PROG_PUNT = 1
       AND PG.IND_ACTI = 1
       AND GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(PG.PERD_OID_PERI_DESD) <=
           lsCampanyaActiva;
  
    IF lnCantidadRegistros > 0 THEN
      OPEN c_puntos;
      LOOP
        FETCH c_puntos BULK COLLECT
          INTO puntosRecordN LIMIT W_FILAS;
      
        IF puntosRecordN.COUNT > 0 THEN
        
          FOR x IN puntosRecordN.FIRST .. puntosRecordN.LAST LOOP              
          
              IF (puntosRecordN(x)
                  .oidTipoMovimiento = 1 AND
                  INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas1) > 0) THEN
            
                  BEGIN
                      SELECT SOCA_OID_SOLI_CABE
                      INTO lnOidSoliCabe
                      FROM (SELECT SCP.SOCA_OID_SOLI_CABE
                            FROM INC_SOLIC_CONCU_PUNTA SCP
                            WHERE SCP.CLIE_OID_CLIE = puntosRecordN(x)
                                    .oidCliente
                                  AND SCP.PERD_OID_PERI = puntosRecordN(x)
                                      .oidPeriodo
                                  AND TO_CHAR(SCP.FEC_DOCU, 'YYYYMMDD') = puntosRecordN(x)
                                      .fechaMovimiento
                                  AND SCP.NUM_PUNT >= 0
                            ORDER BY SCP.NUM_PUNT DESC)
                      WHERE ROWNUM = 1;
                  EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                           lnOidSoliCabe := 0;
                  END;
            
                  -- IF lnOidSoliCabe > 0 THEN
            
                  UPDATE INC_CUENT_CORRI_PUNTO
                         SET IND_APOR_PROG_PUNT = 1,
                             CANA_ORIG = CASE
                                         WHEN lnOidSoliCabe > 0 THEN
                                              HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(lnOidSoliCabe,
                                                puntosRecordN(x).codigoCliente)
                                         ELSE
                                                NULL
                                         END,
                              DES_MOTI           = 'Venta'
                         WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
            
                  --                      END IF;
              ELSE
                  -- OTRO TIPO DE MOVIMIENTO Y OTRA DESCRIPCION
                  UPDATE INC_CUENT_CORRI_PUNTO
                         SET IND_APOR_PROG_PUNT = 1,
                             DES_MOTI = NVL(puntosRecordN(x).DesMotivo,
                                        'Venta')
                         WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
            
              END IF;
              ---- Recupera el factor de multiplicidad por constancia de la campaña de transacción si es que este existe

              BEGIN
                   IF puntosRecordN(x).OidPeriodoRefe IS NULL THEN
                      lsPeriodoRefe := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(puntosRecordN(x).OidPeriodo);
                   ELSE
                      lsPeriodoRefe := puntosRecordN(x).CodPeriodoRefe;
                   END IF;
              
                   SELECT MAX(FAC_MULT -1)
                   INTO lnfacMultiplicidad
                   FROM INC_PROGR_CONST_MULTI
                   WHERE lsPeriodoRefe = CAM_PROC
                         AND puntosRecordN(x).OidCliente = CLIE_OID_CLIE
                         AND puntosRecordN(x).CodConcurso = COD_PROG_PUNT;
              EXCEPTION
                WHEN OTHERS THEN
                     lnfacMultiplicidad:=0;
              END;
              IF lnfacMultiplicidad > 0 THEN
                 IF INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas1) > 0 OR
                    INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas2) > 0 OR
                    puntosRecordN(x).valDescripcion LIKE '%(REC)%' OR
                    puntosRecordN(x).valDescripcion LIKE '%/ECM%' THEN

                    -- IN_CUENT_CORRI_PUNTO
                    lnOidSecuenciaIccp := INC_CUCP_SEQ.NEXTVAL;
                    INSERT INTO INC_CUENT_CORRI_PUNTO
                           (OID_CUEN_CORR_PUNT,
                           NUM_MOVI,
                           NUM_PUNT,
                           NUM_PUNT_EXIG,
                           FEC_MOVI,
                           COPA_OID_PARA_GRAL,
                           CLIE_OID_CLIE,
                           PERD_OID_PERI,
                           TMOV_OID_TIPO_MOVI,
                           FEC_ULTI_ACTU,
                           VAL_DESC,
                           USU_MODI,
                           PERD_OID_PERI_REFE,
                           NUM_PUNT_BONI,
                           COPA_OID_MIGR_ORIG,
                           IND_APOR_PROG_PUNT,
                           DES_MOTI,
                           CANA_ORIG)
                    VALUES (lnOidSecuenciaIccp,
                           lnOidSecuenciaIccp,
                           puntosRecordN(x).puntoscc*lnfacMultiplicidad,
                           0,
                           ldFecha,
                           puntosRecordN(x).oidParamGenerMadre,
                           puntosRecordN(x).OidCliente,
                           puntosRecordN(x).OidPeriodo,
                           puntosRecordN(x).oidTipoMovimiento,
                           sysdate,
                           'Multiplicidad de '||puntosRecordN(x).valDescripcion,
                           psUsuario,                           
                           puntosRecordN(x).OidPeriodoRefe,
                           NULL,
                           NULL,
                           1,
                           'Nro de Pedidos Consecutivos',
                           NULL);                    
                 END IF;
              END IF;
              ----------------
  
          END LOOP;
        END IF;
      
        EXIT WHEN c_puntos%NOTFOUND;
      END LOOP;
      CLOSE c_puntos;
    END IF;
    -- --
  
    -- Traslado de puntos al concurso Programa de Puntos (Concurso madre)
    SELECT COUNT(*)
      INTO lnCantidadRegistros
      FROM INC_CONCU_PARAM_GENER CPG
     WHERE CPG.PAIS_OID_PAIS = lnOidPais
       AND CPG.IND_ACTI = 1
       AND CPG.IND_PROG_PUNT = 1
       AND lsCampanyaActiva >=
           GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(CPG.PERD_OID_PERI_DESD)
       AND CPG.OID_PARA_GRAL IN
           (SELECT CPA.COPA_CONC_PROG_PUNT
              FROM INC_CONCU_PARAM_GENER CPA
             WHERE CPA.PAIS_OID_PAIS = CPG.PAIS_OID_PAIS
               AND CPA.COPA_CONC_PROG_PUNT IS NOT NULL);
  
    IF lnCantidadRegistros > 0 THEN
      OPEN c_traslado;
      LOOP
        FETCH c_traslado BULK COLLECT
          INTO puntosRecordN LIMIT W_FILAS;
      
        IF puntosRecordN.COUNT > 0 THEN
        
          FOR x IN puntosRecordN.FIRST .. puntosRecordN.LAST LOOP
          
            lsResult := '1';
            --VALIDACION AMBITO GEOGRAFICO
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM INC_AMBIT_GEOGR geo
             WHERE COPA_OID_PARA_GRAL = puntosRecordN(x).oidParamGenerMadre;
          
            IF (lnOcurrencias > 0) THEN
              --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
              BEGIN
                lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(puntosRecordN(x)
                                                           .oidParamGenerMadre,
                                                           puntosRecordN(x)
                                                           .oidCliente);
              EXCEPTION
                WHEN OTHERS THEN
                  lsResult := '0';
              END;
            END IF;
          
            IF (lsResult = '1') THEN
              --VALIDACION DEL TIPO DE PARTICIPANTE
              SELECT COUNT(1)
                INTO lnOcurrencias
                FROM INC_CLASI_PARTI_CONCU par
               WHERE par.copa_oid_para_gral = puntosRecordN(x)
                    .oidParamGenerMadre;
            
              IF (lnOcurrencias > 0) THEN
                lsResult := INC_FN_VALID_CLASI_CONCU(puntosRecordN(x)
                                                     .oidParamGenerMadre,
                                                     puntosRecordN(x)
                                                     .oidCliente);
              END IF;
            
              IF (lsResult = '1') THEN
                --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
                SELECT COUNT(1)
                  INTO lnOcurrencias
                  FROM INC_ESTAT_VENTA_CONSU
                 WHERE COPA_OID_PARA_GRAL = puntosRecordN(x)
                      .oidParamGenerMadre;
              
                IF (lnOcurrencias > 0) THEN
                  lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(puntosRecordN          (x)
                                                             .oidParamGenerMadre,
                                                             puntosRecordN          (x)
                                                             .oidCliente,
                                                             psCodigoPeriodo,
                                                             lsCodigoPeriodoAnterior);
                END IF;
              
              END IF;
            
            END IF;
          
            IF puntosRecordN(x).valDescripcion LIKE 'ENTREGA DE PREMIO%' THEN
              lsResult := '0';
            END IF;
          
            IF lsResult = '1' THEN
              -- Pasó las 3 validaciones
              IF (puntosRecordN(x)
                 .oidTipoMovimiento = 1 AND
                  INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas1) > 0) THEN
              
                BEGIN
                  SELECT SOCA_OID_SOLI_CABE
                    INTO lnOidSoliCabe
                    FROM (SELECT SCP.SOCA_OID_SOLI_CABE
                            FROM INC_SOLIC_CONCU_PUNTA SCP
                           WHERE SCP.CLIE_OID_CLIE = puntosRecordN(x)
                                .oidCliente
                             AND SCP.PERD_OID_PERI = puntosRecordN(x)
                                .oidPeriodo
                             AND TO_CHAR(SCP.FEC_DOCU, 'YYYYMMDD') = puntosRecordN(x)
                                .fechaMovimiento
                             AND SCP.NUM_PUNT >= 0
                           ORDER BY SCP.NUM_PUNT DESC)
                   WHERE ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    lnOidSoliCabe := 0;
                END;
              
                --                            IF lnOidSoliCabe > 0 THEN
              
                UPDATE INC_CUENT_CORRI_PUNTO
                   SET IND_APOR_PROG_PUNT = 1,
                       --                                CANA_ORIG = HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(lnOidSoliCabe, puntosRecordN(x).codigoCliente),
                       CANA_ORIG = CASE
                                     WHEN lnOidSoliCabe > 0 THEN
                                      HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(lnOidSoliCabe,
                                                                                   puntosRecordN(x)
                                                                                   .codigoCliente)
                                     ELSE
                                      NULL
                                   END,
                       DES_MOTI = (CASE
                                    WHEN puntosRecordN(x)
                                     .codigoClasificacion = 'A' THEN
                                     'Lanzamiento de Productos'
                                    ELSE
                                     'Venta'
                                  END)
                 WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
              
                --                          END IF;
              
              ELSIF (puntosRecordN(x)
                    .oidTipoMovimiento = 1 AND
                     INSTR(puntosRecordN(x).valDescripcion,
                           lsDescrGrupoTodas2) > 0) THEN
              
                UPDATE INC_CUENT_CORRI_PUNTO
                   SET DES_MOTI           = 'Recomendaciones Efectivas',
--                       PERD_OID_PERI_REFE = GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
--                                                                             GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(puntosRecordN(x)
--                                                                                                                     .oidPeriodo),
--                                                                             -1 *
--                                                                             (puntosRecordN(x)
--                                                                             .numeroMinimoPediReco - 1)),
                       IND_APOR_PROG_PUNT = 1
                 WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
              
              ELSE
                IF puntosRecordN(x).codigoClasificacion = 'E' THEN
                  UPDATE INC_CUENT_CORRI_PUNTO
                     SET IND_APOR_PROG_PUNT = 1,
                         DES_MOTI           = NVL(puntosRecordN(x).DesMotivo,
                                                  'Recomendaciones Efectivas')
                   WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
                
                ELSE
                  UPDATE INC_CUENT_CORRI_PUNTO
                     SET IND_APOR_PROG_PUNT = 1,
                         DES_MOTI           = NVL(puntosRecordN(x).DesMotivo,
                                                  'Venta')
                   WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
                END IF;
              
              END IF;
            
              -- Copiar el registro a un registro nuevo --
            
              SELECT COUNT(*)
                INTO lnOcurrencias
                FROM INC_SOLIC_CONCU_PUNTA
               WHERE CLIE_OID_CLIE = puntosRecordN(x).oidCliente
                 AND COPA_OID_PARA_GRAL = puntosRecordN(x)
                    .oidParamGenerMadre;
            
              IF lnOcurrencias = 0 THEN
              
                SELECT COUNT(0)
                  INTO lnTienesolic
                  FROM INC_SOLIC_CONCU_PUNTA
                 WHERE CLIE_OID_CLIE = puntosRecordN(x).oidCliente
                   AND COPA_OID_PARA_GRAL = puntosRecordN(x).oidParamGener;
              
                IF lnTienesolic > 0 THEN
                
                  INSERT INTO INC_SOLIC_CONCU_PUNTA
                    (OID_SOLI_CONC_PUNT,
                     NUM_PUNT,
                     VAL_PUNT_BONI,
                     VAL_PUNT_FALT_NANU,
                     IND_ANUL,
                     COPA_OID_PARA_GRAL,
                     SOCA_OID_SOLI_CABE,
                     FEC_DOCU,
                     PERD_OID_PERI,
                     CLIE_OID_CLIE,
                     IMP_MONT,
                     CLIE_OID_CLIE_GERE,
                     NUM_UNID)
                  VALUES
                    (INC_SOCP_SEQ.NEXTVAL,
                     0,
                     0,
                     0,
                     0,
                     puntosRecordN(x).oidParamGenerMadre,
                     (SELECT MAX(Z.SOCA_OID_SOLI_CABE)
                        FROM INC_SOLIC_CONCU_PUNTA Z
                       WHERE Z.COPA_OID_PARA_GRAL = puntosRecordN(x)
                            .oidParamGener
                         AND Z.CLIE_OID_CLIE = puntosRecordN(x).oidCliente),
                     (SELECT MAX(Z.FEC_DOCU)
                        FROM INC_SOLIC_CONCU_PUNTA Z
                       WHERE Z.COPA_OID_PARA_GRAL = puntosRecordN(x)
                            .oidParamGener
                         AND Z.CLIE_OID_CLIE = puntosRecordN(x).oidCliente),
                     lnOidPeriByFecha,
                     puntosRecordN(x).oidCliente,
                     0,
                     NULL,
                     0);
                END IF;
              END IF;
            
              SELECT COUNT(*)
                INTO lnOcurrencias
                FROM INC_CANDI_GANAD
               WHERE CLIE_OID_CLIE = puntosRecordN(x).oidCliente
                 AND COPA_OID_PARA_GRAL = puntosRecordN(x)
                    .oidParamGenerMadre;
            
              IF lnOcurrencias = 0 THEN
                INSERT INTO INC_CANDI_GANAD
                  (OID_CAND_GANA,
                   IND_META_SUPE,
                   VAL_REQU_PREM_SUPE,
                   PERD_OID_PERI,
                   COPA_OID_PARA_GRAL,
                   BINC_OID_BASE_INCU,
                   PERD_OID_PERI_EVAL,
                   CLIE_OID_CLIE,
                   FEC_ULTI_ACTU,
                   NUM_PERI_EVAL)
                VALUES
                  (INC_CAGA_SEQ.NEXTVAL,
                   0,
                   0,
                   lnOidPeriByFecha,
                   puntosRecordN       (x).oidParamGenerMadre,
                   NULL,
                   NULL,
                   puntosRecordN       (x).oidCliente,
                   SYSDATE,
                   1);
              ELSE
              
                IF puntosRecordN(x).tipoConcurso = 4 THEN
                
                  SELECT COUNT(*)
                    INTO lnOcurrencias
                    FROM INC_CANDI_GANAD
                   WHERE CLIE_OID_CLIE = puntosRecordN(x).oidCliente
                     AND COPA_OID_PARA_GRAL = puntosRecordN(x)
                        .oidParamGenerMadre
                     AND PERD_OID_PERI = puntosRecordN(x).oidPeriodo;
                
                  IF lnOcurrencias = 0 THEN
                  
                    INSERT INTO INC_CANDI_GANAD
                      (OID_CAND_GANA,
                       IND_META_SUPE,
                       VAL_REQU_PREM_SUPE,
                       PERD_OID_PERI,
                       COPA_OID_PARA_GRAL,
                       BINC_OID_BASE_INCU,
                       PERD_OID_PERI_EVAL,
                       CLIE_OID_CLIE,
                       FEC_ULTI_ACTU,
                       NUM_PERI_EVAL)
                    VALUES
                      (INC_CAGA_SEQ.NEXTVAL,
                       0,
                       0,
                       puntosRecordN       (x).oidPeriodo,
                       puntosRecordN       (x).oidParamGenerMadre,
                       NULL,
                       NULL,
                       puntosRecordN       (x).oidCliente,
                       SYSDATE,
                       1);
                  
                  ELSE
                    UPDATE INC_CANDI_GANAD
                       SET IND_META_SUPE      = 0,
                           VAL_REQU_PREM_SUPE = 0,
                           PERD_OID_PERI      = puntosRecordN(x).oidPeriodo,
                           COPA_OID_PARA_GRAL = puntosRecordN(x)
                                                .oidParamGenerMadre,
                           BINC_OID_BASE_INCU = NULL,
                           PERD_OID_PERI_EVAL = NULL,
                           CLIE_OID_CLIE      = puntosRecordN(x).oidCliente,
                           FEC_ULTI_ACTU      = SYSDATE,
                           NUM_PERI_EVAL      = 1
                     WHERE CLIE_OID_CLIE = puntosRecordN(x).oidCliente
                       AND COPA_OID_PARA_GRAL = puntosRecordN(x)
                          .oidParamGenerMadre
                       AND PERD_OID_PERI = puntosRecordN(x).oidPeriodo;
                  
                  END IF;
                
                END IF;
              
              END IF;
            
              -- --
            
              -- IN_CUENT_CORRI_PUNTO
              lnOidSecuenciaIccp := INC_CUCP_SEQ.NEXTVAL;
              INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG,
                 IND_APOR_PROG_PUNT,
                 DES_MOTI,
                 CANA_ORIG)
                SELECT lnOidSecuenciaIccp,
                       lnOidSecuenciaIccp,
                       puntosRecordN     (x).puntoscc,
                       NUM_PUNT_EXIG,
                       FEC_MOVI,
                       puntosRecordN     (x).oidParamGenerMadre,
                       CLIE_OID_CLIE,
                       PERD_OID_PERI,
                       TMOV_OID_TIPO_MOVI,
                       FEC_ULTI_ACTU,
                       VAL_DESC,
                       psUsuario,
                       PERD_OID_PERI_REFE,
                       NUM_PUNT_BONI,
                       COPA_OID_PARA_GRAL,
                       IND_APOR_PROG_PUNT,
                       DES_MOTI,
                       CANA_ORIG
                  FROM INC_CUENT_CORRI_PUNTO ICCP
                 WHERE ICCP.OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
                 
                 ---- Recupera el factor de multiplicidad por constancia de la campaña de transacción si es que este existe

                 BEGIN
                      IF puntosRecordN(x).OidPeriodoRefe IS NULL THEN
                         lsPeriodoRefe := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(puntosRecordN(x).OidPeriodo);
                      ELSE
                         lsPeriodoRefe := puntosRecordN(x).CodPeriodoRefe;
                      END IF;
              
                      SELECT MAX(FAC_MULT -1)
                      INTO lnfacMultiplicidad
                      FROM INC_PROGR_CONST_MULTI
                      WHERE lsPeriodoRefe = CAM_PROC
                         AND puntosRecordN(x).OidCliente  = CLIE_OID_CLIE
                         AND puntosRecordN(x).CodConcurso = COD_PROG_PUNT;
                 EXCEPTION
                    WHEN OTHERS THEN
                       lnfacMultiplicidad:=0;
                 END;
                 --
                 IF lnfacMultiplicidad > 0 THEN
                    IF INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas1) > 0 OR
                       INSTR(puntosRecordN(x).valDescripcion, lsDescrGrupoTodas2) > 0 OR
                       puntosRecordN(x).valDescripcion LIKE '%(REC)%' OR
                       puntosRecordN(x).valDescripcion LIKE '%/ECM%' THEN

                       -- IN_CUENT_CORRI_PUNTO
                       lnOidSecuenciaIccp := INC_CUCP_SEQ.NEXTVAL;
                       INSERT INTO INC_CUENT_CORRI_PUNTO
                           (OID_CUEN_CORR_PUNT,
                           NUM_MOVI,
                           NUM_PUNT,
                           NUM_PUNT_EXIG,
                           FEC_MOVI,
                           COPA_OID_PARA_GRAL,
                           CLIE_OID_CLIE,
                           PERD_OID_PERI,
                           TMOV_OID_TIPO_MOVI,
                           FEC_ULTI_ACTU,
                           VAL_DESC,
                           USU_MODI,
                           PERD_OID_PERI_REFE,
                           NUM_PUNT_BONI,
                           COPA_OID_MIGR_ORIG,
                           IND_APOR_PROG_PUNT,
                           DES_MOTI,
                           CANA_ORIG)
                    VALUES (lnOidSecuenciaIccp,
                           lnOidSecuenciaIccp,
                           puntosRecordN(x).puntoscc*lnfacMultiplicidad,
                           0,
                           ldFecha,
                           puntosRecordN(x).oidParamGenerMadre,
                           puntosRecordN(x).OidCliente,
                           puntosRecordN(x).OidPeriodo,
                           puntosRecordN(x).oidTipoMovimiento,
                           sysdate,
                           'Multiplicidad de '||puntosRecordN(x).valDescripcion,
                           psUsuario,                           
                           puntosRecordN(x).OidPeriodoRefe,
                           NULL,
                           NULL,
                           1,
                           'Nro de Pedidos Consecutivos',
                           NULL);                    
                 END IF;
              END IF;
              ----------------
            ELSE
              -- NO CUMPLIÓ CON LAS VALIDACIONES
              UPDATE INC_CUENT_CORRI_PUNTO
                 SET IND_APOR_PROG_PUNT = 1
               WHERE OID_CUEN_CORR_PUNT = puntosRecordN(x).oidCtaCte;
            END IF;
          
          END LOOP;
        
        END IF;
      
        EXIT WHEN c_traslado%NOTFOUND;
      
      END LOOP;
    
      CLOSE c_traslado;
    
    END IF;
    -- --
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACUMU_PUNTO_PROGR_PUNTO: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_ACUMU_PUNTO_PROGR_PUNTO;

  /**************************************************************************
  Descripcion       : Cancelacion de puntos para Programa de Puntos
  Fecha Creacion    : 04/02/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCampanyaProceso  :  Campaña de proceso
    psFechaFacturacion  :  Fecha de facturacion
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  Corregido       : Hernán Ramos
  ***************************************************************************/
  PROCEDURE INC_PR_CANCE_PUNTO_PROGR_PUNTO(psCodigoPais       VARCHAR2,
                                           psCodigoRegion     VARCHAR2,
                                           psCampanyaProceso  VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psUsuario          VARCHAR2) IS
  
    lnOidCampanyaProceso NUMBER;
    lnOidRegion          NUMBER;
  
    -- CONCURSO TIPO PROGRAMA DE PUNTOS 
    CURSOR c_programas IS
      SELECT CPP.OID_PARA_GRAL, NVL(NUM_CAMP_SINP_CANC_PUNT, 0)
        FROM INC_CONCU_PARAM_GENER CPP, INC_OBTEN_PUNTO IOP
       WHERE CPP.OID_PARA_GRAL = IOP.COPA_OID_PARA_GRAL
         AND CPP.IND_PROG_PUNT = 1
         AND CPP.IND_ACTI = 1
         AND NVL(NUM_CAMP_SINP_CANC_PUNT, 0) > 0
         AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CPP.PERD_OID_PERI_DESD) <=
             psCampanyaProceso;
  
    CURSOR c_puntosCancelar(cnOidParam NUMBER, cnCampanyas NUMBER) IS
      SELECT clie_oid_clie, ultped, -punt, -punt_exig, -punt_boni
        FROM (SELECT (SELECT SUM(num_punt)
                        from inc_cuent_corri_punto cc
                       WHERE cc.copa_oid_para_gral = gan.copa_oid_para_gral
                         AND cc.clie_oid_clie = gan.clie_oid_clie) punt,
                     (SELECT SUM(num_punt_exig)
                        FROM inc_cuent_corri_punto cc
                       WHERE cc.copa_oid_para_gral = gan.copa_oid_para_gral
                         AND cc.clie_oid_clie = gan.clie_oid_clie) punt_exig,
                     (SELECT SUM(num_punt_boni)
                        FROM inc_cuent_corri_punto cc
                       WHERE cc.copa_oid_para_gral = gan.copa_oid_para_gral
                         AND cc.clie_oid_clie = gan.clie_oid_clie) punt_boni,
                     clie_oid_clie,
                     GEN_PKG_GENER.gen_fn_devue_difer_perio_pais(psCodigoPais,
                                                                 (SELECT MAX(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(pp.PERD_OID_PERI))
                                                                    FROM ped_solic_cabec_acum2 pp
                                                                   WHERE pp.clie_oid_clie =
                                                                         gan.clie_oid_clie),
                                                                 psCampanyaProceso) ultped
                FROM (SELECT DISTINCT copa_oid_para_gral, clie_oid_clie
                        FROM inc_candi_ganad ganad
                       WHERE copa_oid_para_gral = cnOidParam
                         AND clie_oid_clie NOT IN
                             (SELECT desca.clie_oid_clie
                                FROM inc_desca desca
                               WHERE ganad.copa_oid_para_gral =
                                     desca.copa_oid_para_gral)
                         AND CLIE_OID_CLIE IN
                             (SELECT UA.CLIE_OID_CLIE
                                FROM MAE_CLIEN_UNIDA_ADMIN UA,
                                     ZON_TERRI_ADMIN       ZA,
                                     ZON_SECCI             SEC,
                                     ZON_ZONA              ZON,
                                     ZON_TERRI             ZT
                               WHERE UA.ZTAD_OID_TERR_ADMI = ZA.OID_TERR_ADMI
                                 AND ZA.TERR_OID_TERR = ZT.OID_TERR
                                 AND ZA.ZSCC_OID_SECC = SEC.OID_SECC
                                 AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
                                 AND UA.IND_ACTI = 1
                                 AND ZON.ZORG_OID_REGI = lnOidRegion)) gan)
       WHERE punt > 0
         AND ultped >= cnCampanyas;
  
    TYPE programasStruct IS RECORD(
      oidParamGener   INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      numeroCampanyas INC_OBTEN_PUNTO.NUM_CAMP_SINP_CANC_PUNT%TYPE);
  
    TYPE programasTab IS TABLE OF programasStruct;
    programasRecordN programasTab;
  
    TYPE puntosStruct IS RECORD(
      oidCliente    PED_SOLIC_CABEC_ACUM2.CLIE_OID_CLIE%TYPE,
      difCampanyas  PED_SOLIC_CABEC_ACUM2.PERD_OID_PERI%TYPE,
      numPuntos     INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE,
      numPuntosExig INC_CUENT_CORRI_PUNTO.NUM_PUNT_EXIG%TYPE,
      numPuntosBoni INC_CUENT_CORRI_PUNTO.NUM_PUNT_BONI%TYPE);
  
    TYPE puntosTab IS TABLE OF puntosStruct;
    puntosRecordN puntosTab;
  
  BEGIN
  
    SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanyaProceso)
      INTO lnOidCampanyaProceso
      FROM DUAL;
    SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais,
                                                   'T',
                                                   'VD',
                                                   psCodigoRegion)
      INTO lnOidRegion
      FROM DUAL;
  
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO programasRecordN LIMIT W_FILAS;
    
      IF programasRecordN.COUNT > 0 THEN
      
        FOR x IN programasRecordN.FIRST .. programasRecordN.LAST LOOP
        
          OPEN c_puntosCancelar(programasRecordN(x).oidParamGener,
                                programasRecordN(x).numeroCampanyas);
          LOOP
          
            FETCH c_puntosCancelar BULK COLLECT
              INTO puntosRecordN LIMIT W_FILAS;
          
            IF puntosRecordN.COUNT > 0 THEN
            
              FOR y IN puntosRecordN.FIRST .. puntosRecordN.LAST LOOP
              
                INSERT INTO INC_CUENT_CORRI_PUNTO
                  (OID_CUEN_CORR_PUNT,
                   NUM_MOVI,
                   NUM_PUNT,
                   NUM_PUNT_EXIG,
                   FEC_MOVI,
                   COPA_OID_PARA_GRAL,
                   CLIE_OID_CLIE,
                   PERD_OID_PERI,
                   TMOV_OID_TIPO_MOVI,
                   FEC_ULTI_ACTU,
                   VAL_DESC,
                   USU_MODI,
                   PERD_OID_PERI_REFE,
                   NUM_PUNT_BONI,
                   COPA_OID_MIGR_ORIG,
                   IND_APOR_PROG_PUNT,
                   DES_MOTI,
                   CANA_ORIG)
                VALUES
                  (INC_CUCP_SEQ.NEXTVAL,
                   INC_CUCP_SEQ.NEXTVAL,
                   puntosRecordN(y).numPuntos,
                   puntosRecordN(y).numPuntosExig,
                   to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                   programasRecordN(x).oidParamGener,
                   puntosRecordN(y).oidCliente,
                   lnOidCampanyaProceso,
                   2,
                   SYSDATE,
                   'Cancelación de puntos por estar ' || puntosRecordN(y)
                   .difCampanyas || ' sin pasar pedidos',
                   psUsuario,
                   NULL,
                   puntosRecordN(y).numPuntosBoni,
                   NULL,
                   1,
                   'Máximo de campañas sin pedido',
                   NULL);
              
                UPDATE INC_CUENT_CORRI_PUNTO CTE
                   SET CTE.IND_APOR_PROG_PUNT = 1
                 WHERE CTE.CLIE_OID_CLIE = puntosRecordN(y).oidCliente
                   AND CTE.IND_APOR_PROG_PUNT IS NULL
                   AND (CTE.COPA_OID_PARA_GRAL = programasRecordN(x)
                       .oidParamGener OR
                       CTE.COPA_OID_PARA_GRAL IN (
                                                   --CONCURSOS APORTANTES
                                                   SELECT CPTP.OID_PARA_GRAL
                                                     FROM INC_CONCU_PARAM_GENER CPTP
                                                    WHERE CPTP.IND_ACTI = 1
                                                      AND CPTP.COPA_CONC_PROG_PUNT IN
                                                          (SELECT CPG.OID_PARA_GRAL
                                                             FROM INC_CONCU_PARAM_GENER CPG
                                                            WHERE CPG.IND_PROG_PUNT = 1
                                                              AND CPG.IND_ACTI = 1
                                                              AND CPG.OID_PARA_GRAL = programasRecordN(x)
                                                                 .oidParamGener
                                                              AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CPG.PERD_OID_PERI_DESD) <=
                                                                  psCampanyaProceso)));
              
                UPDATE INC_SOLIC_CONCU_PUNTA ISCP
                   SET ISCP.IND_ANUL = 1
                 WHERE ISCP.CLIE_OID_CLIE = puntosRecordN(y).oidCliente
                   AND ISCP.IND_ANUL <> 1
                   AND ISCP.NUM_PUNT > 0
                   AND (ISCP.COPA_OID_PARA_GRAL = programasRecordN(x)
                       .oidParamGener OR
                       ISCP.COPA_OID_PARA_GRAL IN (
                                                    --CONCURSOS APORTANTES
                                                    SELECT CPTP.OID_PARA_GRAL
                                                      FROM INC_CONCU_PARAM_GENER CPTP
                                                     WHERE CPTP.IND_ACTI = 1
                                                       AND CPTP.COPA_CONC_PROG_PUNT IN
                                                           (SELECT CPG.OID_PARA_GRAL
                                                              FROM INC_CONCU_PARAM_GENER CPG
                                                             WHERE CPG.IND_PROG_PUNT = 1
                                                               AND CPG.IND_ACTI = 1
                                                               AND CPG.OID_PARA_GRAL = programasRecordN(x)
                                                                  .oidParamGener
                                                               AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CPG.PERD_OID_PERI_DESD) <=
                                                                   psCampanyaProceso)));
              
              END LOOP;
            
            END IF;
            EXIT WHEN c_puntosCancelar%NOTFOUND;
          
          END LOOP;
          CLOSE c_puntosCancelar;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_programas%NOTFOUND;
    
    END LOOP;
    CLOSE c_programas;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CANCE_PUNTO_PROGR_PUNTO: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_CANCE_PUNTO_PROGR_PUNTO;

  /**************************************************************************
  Descripcion       : Acumulación de puntos por Programas de Constancia
  Fecha Creacion    : 06/04/2015
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCampanyaProceso  :  Campaña de proceso
    psFechaFacturacion  :  Fecha de facturacion
    psUsuario     :  Codigo de usuario
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE INC_PR_ACUMU_PUNTO_PROGR_CONST(psCodigoPais       VARCHAR2,
                                           psCampanyaProceso  VARCHAR2,
                                           psFechaFacturacion VARCHAR2,
                                           psUsuario          VARCHAR2) IS
  
    CURSOR c_programas IS
      SELECT IPC.COD_PROG_CONS,
             CPG.OID_PARA_GRAL,
             IPC.VAL_MONT_MINI,
             CPG.NUM_CONC,
             IPC.CAM_INIC,
             IPC.IND_INIC_MAXC,
             IPC.COD_TIPO_EVAL,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CPG.PERD_OID_PERI_DESD)
        FROM INC_PROGR_CONST IPC, INC_CONCU_PARAM_GENER CPG
       WHERE IPC.COD_PROG_PUNT = CPG.NUM_CONC
         AND IPC.IND_ACTI = '1'
         AND CPG.IND_ACTI = '1'
         AND psCampanyaProceso >= IPC.CAM_INIC
         AND (psCampanyaProceso <= IPC.CAM_FINA OR IPC.CAM_FINA IS NULL)
         AND CPG.IND_PROG_PUNT = '1'
         AND psCampanyaProceso >=
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(CPG.PERD_OID_PERI_DESD);
  
    CURSOR c_consultoras IS
      SELECT SC.CLIE_OID_CLIE,
             MAX(SC.OID_SOLI_CABE) OID_SOLI_CABE,
             MAX(SC.PERD_OID_PERI) PERD_OID_PERI
        FROM PED_SOLIC_CABEC     SC,
             CRA_PERIO           CRA,
             SEG_PERIO_CORPO     SEG,
             PED_TIPO_SOLIC      TS,
             PED_TIPO_SOLIC_PAIS TSP
       WHERE GRPR_OID_GRUP_PROC = 4
         AND SC.PERD_OID_PERI = CRA.OID_PERI
         AND CRA.PERI_OID_PERI = SEG.OID_PERI
         AND SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
         AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
         AND SEG.COD_PERI = psCampanyaProceso
         AND COD_TIPO_SOLI = 'SOC'
       GROUP BY SC.CLIE_OID_CLIE;
  
    TYPE programasStruct IS RECORD(
      codigoPrograma    INC_PROGR_CONST.COD_PROG_CONS%TYPE,
      oidProgramaPuntos INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
      montoMinimoPedido INC_PROGR_CONST.VAL_MONT_MINI%TYPE,
      codigoConcurso    INC_PROGR_CONST.COD_PROG_PUNT%TYPE,
      campinicio        INC_PROGR_CONST.CAM_INIC%TYPE,
      iniciomaxc        INC_PROGR_CONST.IND_INIC_MAXC%TYPE,
      tipoeval          INC_PROGR_CONST.COD_TIPO_EVAL%TYPE,
      campinicioConcu   SEG_PERIO_CORPO.COD_PERI%TYPE);
  
    TYPE programasTab IS TABLE OF programasStruct;
    programasRecordN programasTab;
  
    TYPE consultorasStruct IS RECORD(
      oidCliente   PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
      oidSolicitud PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
      oidPeriodo   PED_SOLIC_CABEC.PERD_OID_PERI%TYPE);
  
    TYPE consultorasTab IS TABLE OF consultorasStruct;
    consultorasRecordN consultorasTab;
  
    lsResult                VARCHAR2(1);
    lnOcurrencias           NUMBER;
    lnOcurrencias2          NUMBER;
    lsCodigoPeriodoAnterior VARCHAR2(6);
    lnValMontoTotal         ped_solic_cabec_acum2.VAL_MONT_TOTA%TYPE;
    lnCampanyasConstante    NUMBER;
    lnNivelasignado         NUMBER;
    lnultnivel              NUMBER;
    lnnivelinicio           NUMBER;
    lnmaxnivel              NUMBER;
    lnstatusconsultora      NUMBER;
    lnPuntosAbonar          INC_PROGR_CONST_RANGO.NUM_PUNT_ABON%TYPE;
    lnfacmult               NUMBER;
    lnOidPeriodoAnterior    NUMBER;
  
    lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriInicio NUMBER;
  
    lsCampInicio            SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCampPrimeConta        SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnNumCampNuevas         NUMBER;
    lnNumPediExig           NUMBER;
    lnNumPuntAbon           NUMBER;
    lnNumPediNuevas         NUMBER;
  BEGIN
  
    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
    lsCodigoPeriodoAnterior := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                psCampanyaProceso,
                                                                -1);

    lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoAnterior,
                                                                       lnOidMarca,
                                                                       lnOidCanal);
                                                                  
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO programasRecordN LIMIT W_FILAS;
    
      IF programasRecordN.COUNT > 0 THEN
      
        FOR x IN programasRecordN.FIRST .. programasRecordN.LAST LOOP
        
          --Obtenemos el Oid Periodo de la campaña de inicio 
          lnOidPeriInicio := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(programasRecordN(x)
                                                                        .campinicio,
                                                                        lnOidMarca,
                                                                        lnOidCanal);
        
          BEGIN
            SELECT MAX(val_rang_fina)
              INTO lnmaxnivel
              FROM INC_PROGR_CONST_RANGO
             WHERE PAIS_COD_PAIS = psCodigoPais
               AND COD_PROG_CONS = programasRecordN(x).codigoPrograma;
          EXCEPTION
            WHEN OTHERS THEN
              lnmaxnivel := 0;
          END;
        
          IF programasRecordN(x).iniciomaxc = '1' THEN
            lnnivelinicio := lnmaxnivel;
          ELSE
            lnnivelinicio := 1;
          END IF;
          --------
          OPEN c_consultoras;
          LOOP
          
            FETCH c_consultoras BULK COLLECT
              INTO consultorasRecordN LIMIT W_FILAS;
          
            IF consultorasRecordN.COUNT > 0 THEN
            
              FOR y IN consultorasRecordN.FIRST .. consultorasRecordN.LAST LOOP
              
                lsResult := '1';
                --VALIDACION AMBITO GEOGRAFICO
                SELECT COUNT(1)
                  INTO lnOcurrencias
                  FROM INC_AMBIT_GEOGR geo
                 WHERE COPA_OID_PARA_GRAL = programasRecordN(x)
                      .oidProgramaPuntos;
              
                IF (lnOcurrencias > 0) THEN
                  --SE VALIDARA SI CONSULTORA PERTENCE AL AMBITO GEOGRAFICO DEL CONCURSO
                  BEGIN
                    lsResult := INC_FN_VALID_AMBIT_GEOGR_CONCU(programasRecordN  (x)
                                                               .oidProgramaPuntos,
                                                               consultorasRecordN(y)
                                                               .oidCliente);
                  EXCEPTION
                    WHEN OTHERS THEN
                      lsResult := '0';
                  END;
                END IF;
              
                IF (lsResult = '1') THEN
                  --VALIDACION DEL TIPO DE PARTICIPANTE
                  SELECT COUNT(1)
                    INTO lnOcurrencias
                    FROM INC_CLASI_PARTI_CONCU par
                   WHERE par.copa_oid_para_gral = programasRecordN(x)
                        .oidProgramaPuntos;
                
                  IF (lnOcurrencias > 0) THEN
                    lsResult := INC_FN_VALID_CLASI_CONCU(programasRecordN  (x)
                                                         .oidProgramaPuntos,
                                                         consultorasRecordN(y)
                                                         .oidCliente);
                  END IF;
                
                  IF (lsResult = '1') THEN
                    --VALIDACION DEL ESTATUS VENTA DEL CLIENTE
                    SELECT COUNT(1)
                      INTO lnOcurrencias
                      FROM INC_ESTAT_VENTA_CONSU
                     WHERE COPA_OID_PARA_GRAL = programasRecordN(x)
                          .oidProgramaPuntos;
                  
                    IF (lnOcurrencias > 0) THEN
                      lsResult := INC_FN_VALID_ESTAT_VENTA_CONCU(programasRecordN       (x)
                                                                 .oidProgramaPuntos,
                                                                 consultorasRecordN     (y)
                                                                 .oidCliente,
                                                                 psCampanyaProceso,
                                                                 lsCodigoPeriodoAnterior);
                    END IF;
                  
                  END IF;
                
                END IF;
              
                IF lsResult = '1' THEN
                  -- Pasó las 3 validaciones
                
                  --Obtenemos el monto de la consultora de la campaña de proceso
                  SELECT NVL(SUM(val_mont_tota), 0)
                    INTO lnValMontoTotal
                    FROM ped_solic_cabec_acum2
                   WHERE CLIE_OID_CLIE = consultorasRecordN(y).oidCliente
                     AND PERD_OID_PERI = consultorasRecordN(y).oidPeriodo;
                
                  --                                IF lnValMontoTotal >= programasRecordN(x).montoMinimoPedido THEN
                
                  -- Contabilizar periodos consecutivos
                  lnCampanyasConstante := INC_FN_OBTEN_PEDPE_CONSEC(psCodigoPais,
                                                                    psCampanyaProceso,
                                                                    consultorasRecordN(y)
                                                                    .oidCliente,
                                                                    programasRecordN  (x)
                                                                    .campinicio);
                  -- -- 
                
                  IF programasRecordN(x).tipoeval = 'M' THEN
                  ---------------- Condición para factor de Multiplicidad ------------------
                    BEGIN
                                         
                      SELECT COUNT(1)
                        INTO lnOcurrencias
                        FROM PED_SOLIC_CABEC_ACUM2
                       WHERE CLIE_OID_CLIE = consultorasRecordN(y).oidCliente
                         AND PERD_OID_PERI = lnOidPeriodoAnterior
                         AND NVL(IND_PEDI_ANUL,'0')<>'1';
                      
                      lnstatusconsultora := 0;
                      
                      IF(lnOcurrencias = 0) THEN                  
                        SELECT COUNT(1)
                          INTO lnOcurrencias
                          FROM INC_PROGR_CONST_MULTI
                         WHERE COD_PROG_CONS = programasRecordN(x).codigoPrograma
                           AND COD_PROG_PUNT = programasRecordN(x).codigoconcurso
                           AND COD_CLIE = (SELECT COD_CLIE FROM MAE_CLIEN 
                                           WHERE OID_CLIE = consultorasRecordN(y).oidCliente);
                      
                        IF(lnOcurrencias = 0) THEN
                          lnstatusconsultora := 1;
                        END IF;
                      
                      END IF;
                            
                    EXCEPTION
                      WHEN OTHERS THEN
                        lnstatusconsultora := 0;
                    END;
                    ---- Determina el último nivel en que se quedó en la campaña anterior ------
                    BEGIN
                      SELECT val_rang
                        INTO lnultnivel
                        FROM inc_progr_const_multi
                       WHERE COD_PROG_CONS = programasRecordN(x).codigoPrograma
                         AND COD_PROG_PUNT = programasRecordN(x).codigoconcurso
                         AND CAM_PROC = lsCodigoPeriodoAnterior
                         AND COD_CLIE = (SELECT MAX(COD_CLIE)
                                           FROM MAE_CLIEN
                                          WHERE OID_CLIE = consultorasRecordN(y)
                                               .oidCliente);
                    EXCEPTION
                      WHEN OTHERS THEN
                        lnultnivel := 0;
                    END;
                    
                    CASE
                      WHEN consultorasRecordN(y).oidPeriodo = lnOidPeriInicio THEN
                        lnNivelasignado := lnnivelinicio;
                      WHEN lnstatusconsultora = 1 THEN
                        lnNivelasignado := lnnivelinicio;
                      WHEN lnValMontoTotal < programasRecordN(x).montoMinimoPedido THEN
                        lnNivelasignado := lnultnivel;
                      WHEN lnValMontoTotal >= programasRecordN(x).montoMinimoPedido THEN
                           IF lnultnivel + 1 > lnmaxnivel THEN
                              lnNivelasignado := lnmaxnivel;
                           ELSE
                              lnNivelasignado := lnultnivel + 1;
                           END IF;
                    END CASE;
                    ------------
                    IF lnValMontoTotal >= programasRecordN(x)
                      .montoMinimoPedido THEN
                    
                      -- Obtenemos el factor de multiplicidad a aplicar
                      BEGIN
                        SELECT FAC_MULT
                          INTO lnfacmult
                          FROM (SELECT FAC_MULT
                                  FROM INC_PROGR_CONST_RANGO
                                 WHERE PAIS_COD_PAIS = psCodigoPais
                                   AND COD_PROG_CONS = programasRecordN(x)
                                      .codigoPrograma
                                   AND lnNivelasignado = VAL_RANG_FINA
                                 ORDER BY VAL_RANG_FINA ASC)
                         WHERE ROWNUM = 1;
                      EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                          lnfacmult := 0;
                      END;
                      -- --                                           
                    ELSE
                      lnfacmult := 0;
                    END IF;
                    ---------- Agrega registro del factor de multplicidad consultora-campaña
                    SELECT COUNT(*)
                      INTO lnOcurrencias
                      FROM INC_PROGR_CONST_MULTI
                     WHERE COD_PROG_CONS = programasRecordN(x)
                          .codigoPrograma
                       AND COD_PROG_PUNT = programasRecordN(x)
                          .codigoConcurso
                       AND CAM_PROC = psCampanyaProceso
                       AND CLIE_OID_CLIE = consultorasRecordN(y).oidCliente;
                  
                    IF lnOcurrencias = 0 THEN
                      INSERT INTO INC_PROGR_CONST_MULTI
                        (PAIS_COD_PAIS,
                         COD_PROG_CONS,
                         COD_PROG_PUNT,
                         CAM_PROC,
                         FEC_PROC,
                         COD_CLIE,
                         CLIE_OID_CLIE,
                         VAL_RANG,
                         FAC_MULT,
                         USU_CREA,
                         FEC_CREA,
                         USU_MODI,
                         FEC_MODI,
                         EST_REG)
                      VALUES
                        (psCodigoPais,
                         programasRecordN(x).codigoPrograma,
                         programasRecordN(x).codigoConcurso,
                         psCampanyaProceso,
                         to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                         (SELECT COD_CLIE
                            FROM MAE_CLIEN
                           WHERE OID_CLIE = consultorasRecordN(y).oidCliente),
                         consultorasRecordN(y).oidCliente,
                         lnNivelasignado,
                         lnfacmult,
                         psUsuario,
                         SYSDATE,
                         NULL,
                         NULL,
                         0);
                    ELSE
                        UPDATE INC_PROGR_CONST_MULTI
                               SET FEC_PROC=to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                                   VAL_RANG=lnNivelasignado,
                                   FAC_MULT=lnfacmult,
                                   USU_MODI=psUsuario,
                                   FEC_MODI=SYSDATE
                        WHERE COD_PROG_CONS = programasRecordN(x).codigoPrograma
                              AND COD_PROG_PUNT = programasRecordN(x).codigoConcurso
                              AND CAM_PROC = psCampanyaProceso
                              AND CLIE_OID_CLIE = consultorasRecordN(y).oidCliente
                              AND FAC_MULT=0 AND lnfacmult>0;
                    END IF;
                  
                  ELSE --- Cuando el tipo es Fijo
                    IF lnValMontoTotal >= programasRecordN(x)
                      .montoMinimoPedido THEN
                    
                      IF lnCampanyasConstante > lnmaxnivel THEN
                         lnCampanyasConstante := lnmaxnivel;
                      END IF;
                      
                      --Calculamos el valor de la variable lnstatusconsultora
                      SELECT COUNT(1)
                        INTO lnOcurrencias
                        FROM PED_SOLIC_CABEC_ACUM2
                       WHERE CLIE_OID_CLIE = consultorasRecordN(y).oidCliente
                         AND PERD_OID_PERI = lnOidPeriodoAnterior
                         AND NVL(IND_PEDI_ANUL,'0')<>'1';
                      
                      lnstatusconsultora := 0;
                      
                      IF(lnOcurrencias = 0) THEN                  
                        SELECT COUNT(1)
                          INTO lnOcurrencias
                          FROM INC_PROGR_CONST_MULTI
                         WHERE COD_PROG_CONS = programasRecordN(x).codigoPrograma
                           AND COD_PROG_PUNT = programasRecordN(x).codigoconcurso
                           AND COD_CLIE = (SELECT COD_CLIE FROM MAE_CLIEN 
                                           WHERE OID_CLIE = consultorasRecordN(y).oidCliente);
                      
                        IF(lnOcurrencias = 0) THEN
                          lnstatusconsultora := 1;
                        END IF;
                      
                      END IF;
                      
                      IF(lnstatusconsultora = 1) THEN
                        lnCampanyasConstante := lnmaxnivel;
                      END IF;
                      
                      -- Obtenemos el puntaje a abonar
                      BEGIN
                        SELECT NUM_PUNT_ABON
                          INTO lnPuntosAbonar
                          FROM (SELECT NUM_PUNT_ABON
                                  FROM INC_PROGR_CONST_RANGO
                                 WHERE PAIS_COD_PAIS = psCodigoPais
                                   AND COD_PROG_CONS = programasRecordN(x)
                                      .codigoPrograma
                                   AND lnCampanyasConstante = VAL_RANG_FINA
                                 ORDER BY VAL_RANG_FINA ASC)
                         WHERE ROWNUM = 1;
                      EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                          lnPuntosAbonar := 0;
                      END;
                      -- --
                    
                      -- Si alcanzó algún rango
                      IF lnPuntosAbonar > 0 THEN
                      
                        SELECT COUNT(*)
                          INTO lnOcurrencias
                          FROM INC_PROGR_CONST_MOVIM
                         WHERE COD_PROG_CONS = programasRecordN(x)
                              .codigoPrograma
                           AND NUM_CONC = programasRecordN(x)
                              .codigoConcurso
                           AND CAM_PROC = psCampanyaProceso
                           AND COD_CLIE = (SELECT COD_CLIE
                                             FROM MAE_CLIEN
                                            WHERE OID_CLIE = consultorasRecordN(y)
                                                 .oidCliente)
                           AND EST_REG = 0;
                      
                        IF lnOcurrencias > 0 THEN
                        
                          -- Verificamos si ya existe registro para la pk 
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_PROGR_CONST_MOVIM
                           WHERE COD_PROG_CONS = programasRecordN(x)
                                .codigoPrograma
                             AND NUM_CONC = programasRecordN(x)
                                .codigoConcurso
                             AND CAM_PROC = psCampanyaProceso
                             AND TO_CHAR(FEC_PROC, 'DD/MM/YYYY') =
                                 psFechaFacturacion
                             AND COD_CLIE = (SELECT COD_CLIE
                                               FROM MAE_CLIEN
                                              WHERE OID_CLIE = consultorasRecordN(y)
                                                   .oidCliente);
                        
                          IF lnOcurrencias = 0 THEN
                            INSERT INTO INC_PROGR_CONST_MOVIM
                              (COD_PROG_CONS,
                               NUM_CONC,
                               CAM_PROC,
                               FEC_PROC,
                               COD_CLIE,
                               NUM_PUNT,
                               EST_REG,
                               USU_CREA,
                               FEC_CREA,
                               USU_MODI,
                               FEC_MODI)
                              SELECT COD_PROG_CONS,
                                     NUM_CONC,
                                     CAM_PROC,
                                     to_date(psFechaFacturacion,
                                             'DD/MM/YYYY'),
                                     COD_CLIE,
                                     NUM_PUNT,
                                     EST_REG,
                                     psUsuario,
                                     SYSDATE,
                                     NULL,
                                     NULL
                                FROM INC_PROGR_CONST_MOVIM
                               WHERE COD_PROG_CONS = programasRecordN(x)
                                    .codigoPrograma
                                 AND NUM_CONC = programasRecordN(x)
                                    .codigoConcurso
                                 AND CAM_PROC = psCampanyaProceso
                                 AND COD_CLIE =
                                     (SELECT COD_CLIE
                                        FROM MAE_CLIEN
                                       WHERE OID_CLIE = consultorasRecordN(y)
                                            .oidCliente)
                                 AND EST_REG = 0
                                 AND ROWNUM = 1;
                          END IF;
                        
                        ELSE
                          -- Alcanzó rango pero no existe registro con est_reg = 0
                        
                          DELETE FROM INC_PROGR_CONST_MOVIM
                           WHERE COD_PROG_CONS = programasRecordN(x)
                                .codigoPrograma
                             AND NUM_CONC = programasRecordN(x)
                                .codigoConcurso
                             AND CAM_PROC = psCampanyaProceso
                             AND TO_CHAR(FEC_PROC, 'DD/MM/YYYY') =
                                 psFechaFacturacion
                             AND COD_CLIE = (SELECT COD_CLIE
                                               FROM MAE_CLIEN
                                              WHERE OID_CLIE = consultorasRecordN(y)
                                                   .oidCliente);
                        
                          INSERT INTO INC_PROGR_CONST_MOVIM
                            (COD_PROG_CONS,
                             NUM_CONC,
                             CAM_PROC,
                             FEC_PROC,
                             COD_CLIE,
                             NUM_PUNT,
                             EST_REG,
                             USU_CREA,
                             FEC_CREA,
                             USU_MODI,
                             FEC_MODI)
                          VALUES
                            (programasRecordN(x).codigoPrograma,
                             programasRecordN(x).codigoConcurso,
                             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(consultorasRecordN(y)
                                                                    .oidPeriodo),
                             to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                             (SELECT COD_CLIE
                                FROM MAE_CLIEN
                               WHERE OID_CLIE = consultorasRecordN(y)
                                    .oidCliente),
                             lnPuntosAbonar,
                             0,
                             psUsuario,
                             SYSDATE,
                             NULL,
                             NULL);
                        
                          -------
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_SOLIC_CONCU_PUNTA
                           WHERE CLIE_OID_CLIE = consultorasRecordN(y)
                                .oidCliente
                             AND COPA_OID_PARA_GRAL = programasRecordN(x)
                                .oidProgramaPuntos;
                        
                          IF lnOcurrencias = 0 THEN
                          
                            SELECT COUNT(*)
                              INTO lnOcurrencias2
                              FROM INC_SOLIC_CONCU_PUNTA
                             WHERE PERD_OID_PERI = consultorasRecordN(y)
                                  .oidPeriodo
                               AND SOCA_OID_SOLI_CABE = consultorasRecordN(y)
                                  .oidSolicitud
                               AND COPA_OID_PARA_GRAL = programasRecordN(x)
                                  .oidProgramaPuntos;
                          
                            IF lnOcurrencias2 = 0 THEN
                            
                              INSERT INTO INC_SOLIC_CONCU_PUNTA
                                (OID_SOLI_CONC_PUNT,
                                 NUM_PUNT,
                                 VAL_PUNT_BONI,
                                 VAL_PUNT_FALT_NANU,
                                 IND_ANUL,
                                 COPA_OID_PARA_GRAL,
                                 SOCA_OID_SOLI_CABE,
                                 FEC_DOCU,
                                 PERD_OID_PERI,
                                 CLIE_OID_CLIE,
                                 IMP_MONT,
                                 CLIE_OID_CLIE_GERE,
                                 NUM_UNID)
                              VALUES
                                (INC_SOCP_SEQ.NEXTVAL,
                                 0,
                                 0,
                                 0,
                                 0,
                                 programasRecordN(x).oidProgramaPuntos,
                                 consultorasRecordN(y).oidSolicitud,
                                 to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                                 consultorasRecordN(y).oidPeriodo,
                                 consultorasRecordN(y).oidCliente,
                                 0,
                                 NULL,
                                 0);
                            END IF;
                          END IF;
                        
                          -------                                            
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_CANDI_GANAD
                           WHERE COPA_OID_PARA_GRAL = programasRecordN(x)
                                .oidProgramaPuntos
                             AND CLIE_OID_CLIE = consultorasRecordN(y)
                                .oidCliente;
                        
                          IF lnOcurrencias = 0 THEN
                          
                            INSERT INTO INC_CANDI_GANAD
                              (OID_CAND_GANA,
                               IND_META_SUPE,
                               VAL_REQU_PREM_SUPE,
                               PERD_OID_PERI,
                               COPA_OID_PARA_GRAL,
                               BINC_OID_BASE_INCU,
                               PERD_OID_PERI_EVAL,
                               CLIE_OID_CLIE,
                               FEC_ULTI_ACTU,
                               NUM_PERI_EVAL)
                            VALUES
                              (INC_CAGA_SEQ.nextval,
                               0,
                               0,
                               consultorasRecordN  (y).oidPeriodo,
                               programasRecordN    (x).oidProgramaPuntos,
                               NULL,
                               NULL,
                               consultorasRecordN  (y).oidCliente,
                               SYSDATE,
                               1);
                          END IF;
                        
                          INSERT INTO INC_CUENT_CORRI_PUNTO
                            (OID_CUEN_CORR_PUNT,
                             NUM_MOVI,
                             NUM_PUNT,
                             NUM_PUNT_EXIG,
                             FEC_MOVI,
                             COPA_OID_PARA_GRAL,
                             CLIE_OID_CLIE,
                             PERD_OID_PERI,
                             TMOV_OID_TIPO_MOVI,
                             FEC_ULTI_ACTU,
                             VAL_DESC,
                             USU_MODI,
                             PERD_OID_PERI_REFE,
                             NUM_PUNT_BONI,
                             COPA_OID_MIGR_ORIG,
                             IND_APOR_PROG_PUNT,
                             DES_MOTI,
                             CANA_ORIG)
                          VALUES
                            (INC_CUCP_SEQ.NEXTVAL,
                             INC_CUCP_SEQ.NEXTVAL,
                             lnPuntosAbonar,
                             0,
                             to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                             programasRecordN(x).oidProgramaPuntos,
                             consultorasRecordN(y).oidCliente,
                             consultorasRecordN(y).oidPeriodo,
                             1,
                             SYSDATE,
                             'Abono de puntos por Programa de Constancia ' || programasRecordN(x)
                             .codigoPrograma,
                             psUsuario,
                             NULL,
                             NULL,
                             NULL,
                             1,
                             'Nro de Pedidos Consecutivos',
                             HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(consultorasRecordN(y)
                                                                          .oidSolicitud,
                                                                          (SELECT COD_CLIE
                                                                             FROM MAE_CLIEN
                                                                            WHERE OID_CLIE = consultorasRecordN(y)
                                                                                 .oidCliente)));
                        
                        END IF;
                      
                      END IF;
                    END IF;
                  END IF;
                  
                  --VERIFICAMOS SI PARA EL PROGRAMA DE CONSTANCIA EXISTEN REGISTROS
                  --DE RANGO DE BONIFICACION PARA NUEVAS
                  SELECT COUNT(1)
                    INTO lnOcurrencias
                    FROM INC_PROGR_CONST_NUEVA
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND COD_PROG_CONS = programasRecordN(x).codigoPrograma;
                         
                  
                  IF (lnOcurrencias > 0) AND (lnValMontoTotal >= programasRecordN(x)
                      .montoMinimoPedido) THEN
                      
                    BEGIN  
                      SELECT FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI)
                        INTO lsCampPrimeConta
                        FROM MAE_CLIEN_PRIME_CONTA
                       WHERE CLIE_OID_CLIE = consultorasRecordN(y).oidCliente;
                      
                      IF(lsCampPrimeConta > programasRecordN(x).campinicioConcu) THEN
                        lsCampInicio := lsCampPrimeConta;
                      ELSE
                        lsCampInicio := programasRecordN(x).campinicioConcu;  
                      END IF;   
                      
                      lnNumCampNuevas := gen_pkg_gener.gen_fn_devue_difer_perio(lsCampInicio,
                                                                             psCampanyaProceso) + 1;

                      SELECT NUM_PEDI_EXIG,
                             NUM_PUNT_ABON
                        INTO lnNumPediExig,
                             lnNumPuntAbon     
                        FROM INC_PROGR_CONST_NUEVA
                       WHERE PAIS_COD_PAIS = psCodigoPais
                         AND COD_PROG_CONS = programasRecordN(x).codigoPrograma
                         AND NUM_CAMP_EXIG = lnNumCampNuevas;
                      
                      SELECT COUNT(1)
                        INTO lnNumPediNuevas
                        FROM PED_SOLIC_CABEC_ACUM2
                       WHERE CLIE_OID_CLIE = consultorasRecordN(y).oidCliente
                         AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI)
                              BETWEEN lsCampInicio  and psCampanyaProceso
                         AND NVL(IND_PEDI_ANUL, '0') = '1';
                    
                      IF lnNumPediNuevas >= lnNumPediExig THEN
                         lnPuntosAbonar := lnNumPuntAbon;
                      END IF;
                    EXCEPTION
                      WHEN OTHERS THEN
                        lnPuntosAbonar := 0;
                    END;
                                          
                      -- Si alcanz? alg?n rango
                      IF lnPuntosAbonar > 0 THEN
                      
                        SELECT COUNT(*)
                          INTO lnOcurrencias
                          FROM INC_PROGR_CONST_MOVNV
                         WHERE COD_PROG_CONS = programasRecordN(x)
                              .codigoPrograma
                           AND NUM_CONC = programasRecordN(x)
                              .codigoConcurso
                           AND CAM_PROC = psCampanyaProceso
                           AND COD_CLIE = (SELECT COD_CLIE
                                             FROM MAE_CLIEN
                                            WHERE OID_CLIE = consultorasRecordN(y)
                                                 .oidCliente)
                           AND EST_REG = 0;
                      
                        IF lnOcurrencias > 0 THEN
                        
                          -- Verificamos si ya existe registro para la pk 
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_PROGR_CONST_MOVNV
                           WHERE COD_PROG_CONS = programasRecordN(x)
                                .codigoPrograma
                             AND NUM_CONC = programasRecordN(x)
                                .codigoConcurso
                             AND CAM_PROC = psCampanyaProceso
                             AND TO_CHAR(FEC_PROC, 'DD/MM/YYYY') =
                                 psFechaFacturacion
                             AND COD_CLIE = (SELECT COD_CLIE
                                               FROM MAE_CLIEN
                                              WHERE OID_CLIE = consultorasRecordN(y)
                                                   .oidCliente);
                        
                          IF lnOcurrencias = 0 THEN
                            INSERT INTO INC_PROGR_CONST_MOVNV
                              (COD_PROG_CONS,
                               NUM_CONC,
                               CAM_PROC,
                               FEC_PROC,
                               COD_CLIE,
                               NUM_PUNT,
                               EST_REG,
                               USU_CREA,
                               FEC_CREA,
                               USU_MODI,
                               FEC_MODI)
                              SELECT COD_PROG_CONS,
                                     NUM_CONC,
                                     CAM_PROC,
                                     to_date(psFechaFacturacion,
                                             'DD/MM/YYYY'),
                                     COD_CLIE,
                                     NUM_PUNT,
                                     EST_REG,
                                     psUsuario,
                                     SYSDATE,
                                     NULL,
                                     NULL
                                FROM INC_PROGR_CONST_MOVNV
                               WHERE COD_PROG_CONS = programasRecordN(x)
                                    .codigoPrograma
                                 AND NUM_CONC = programasRecordN(x)
                                    .codigoConcurso
                                 AND CAM_PROC = psCampanyaProceso
                                 AND COD_CLIE =
                                     (SELECT COD_CLIE
                                        FROM MAE_CLIEN
                                       WHERE OID_CLIE = consultorasRecordN(y)
                                            .oidCliente)
                                 AND EST_REG = 0
                                 AND ROWNUM = 1;
                          END IF;
                        
                        ELSE
                          -- Alcanz? rango pero no existe registro con est_reg = 0
                        
                          DELETE FROM INC_PROGR_CONST_MOVNV
                           WHERE COD_PROG_CONS = programasRecordN(x)
                                .codigoPrograma
                             AND NUM_CONC = programasRecordN(x)
                                .codigoConcurso
                             AND CAM_PROC = psCampanyaProceso
                             AND TO_CHAR(FEC_PROC, 'DD/MM/YYYY') =
                                 psFechaFacturacion
                             AND COD_CLIE = (SELECT COD_CLIE
                                               FROM MAE_CLIEN
                                              WHERE OID_CLIE = consultorasRecordN(y)
                                                   .oidCliente);
                        
                          INSERT INTO INC_PROGR_CONST_MOVNV
                            (COD_PROG_CONS,
                             NUM_CONC,
                             CAM_PROC,
                             FEC_PROC,
                             COD_CLIE,
                             NUM_PUNT,
                             EST_REG,
                             USU_CREA,
                             FEC_CREA,
                             USU_MODI,
                             FEC_MODI)
                          VALUES
                            (programasRecordN(x).codigoPrograma,
                             programasRecordN(x).codigoConcurso,
                             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(consultorasRecordN(y)
                                                                    .oidPeriodo),
                             to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                             (SELECT COD_CLIE
                                FROM MAE_CLIEN
                               WHERE OID_CLIE = consultorasRecordN(y)
                                    .oidCliente),
                             lnPuntosAbonar,
                             0,
                             psUsuario,
                             SYSDATE,
                             NULL,
                             NULL);
                        
                          -------
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_SOLIC_CONCU_PUNTA
                           WHERE CLIE_OID_CLIE = consultorasRecordN(y)
                                .oidCliente
                             AND COPA_OID_PARA_GRAL = programasRecordN(x)
                                .oidProgramaPuntos;
                        
                          IF lnOcurrencias = 0 THEN
                          
                            SELECT COUNT(*)
                              INTO lnOcurrencias2
                              FROM INC_SOLIC_CONCU_PUNTA
                             WHERE PERD_OID_PERI = consultorasRecordN(y)
                                  .oidPeriodo
                               AND SOCA_OID_SOLI_CABE = consultorasRecordN(y)
                                  .oidSolicitud
                               AND COPA_OID_PARA_GRAL = programasRecordN(x)
                                  .oidProgramaPuntos;
                          
                            IF lnOcurrencias2 = 0 THEN
                            
                              INSERT INTO INC_SOLIC_CONCU_PUNTA
                                (OID_SOLI_CONC_PUNT,
                                 NUM_PUNT,
                                 VAL_PUNT_BONI,
                                 VAL_PUNT_FALT_NANU,
                                 IND_ANUL,
                                 COPA_OID_PARA_GRAL,
                                 SOCA_OID_SOLI_CABE,
                                 FEC_DOCU,
                                 PERD_OID_PERI,
                                 CLIE_OID_CLIE,
                                 IMP_MONT,
                                 CLIE_OID_CLIE_GERE,
                                 NUM_UNID)
                              VALUES
                                (INC_SOCP_SEQ.NEXTVAL,
                                 0,
                                 0,
                                 0,
                                 0,
                                 programasRecordN(x).oidProgramaPuntos,
                                 consultorasRecordN(y).oidSolicitud,
                                 to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                                 consultorasRecordN(y).oidPeriodo,
                                 consultorasRecordN(y).oidCliente,
                                 0,
                                 NULL,
                                 0);
                            END IF;
                          END IF;
                        
                          -------                                            
                          SELECT COUNT(*)
                            INTO lnOcurrencias
                            FROM INC_CANDI_GANAD
                           WHERE COPA_OID_PARA_GRAL = programasRecordN(x)
                                .oidProgramaPuntos
                             AND CLIE_OID_CLIE = consultorasRecordN(y)
                                .oidCliente;
                        
                          IF lnOcurrencias = 0 THEN
                          
                            INSERT INTO INC_CANDI_GANAD
                              (OID_CAND_GANA,
                               IND_META_SUPE,
                               VAL_REQU_PREM_SUPE,
                               PERD_OID_PERI,
                               COPA_OID_PARA_GRAL,
                               BINC_OID_BASE_INCU,
                               PERD_OID_PERI_EVAL,
                               CLIE_OID_CLIE,
                               FEC_ULTI_ACTU,
                               NUM_PERI_EVAL)
                            VALUES
                              (INC_CAGA_SEQ.nextval,
                               0,
                               0,
                               consultorasRecordN  (y).oidPeriodo,
                               programasRecordN    (x).oidProgramaPuntos,
                               NULL,
                               NULL,
                               consultorasRecordN  (y).oidCliente,
                               SYSDATE,
                               1);
                          END IF;
                        
                          INSERT INTO INC_CUENT_CORRI_PUNTO
                            (OID_CUEN_CORR_PUNT,
                             NUM_MOVI,
                             NUM_PUNT,
                             NUM_PUNT_EXIG,
                             FEC_MOVI,
                             COPA_OID_PARA_GRAL,
                             CLIE_OID_CLIE,
                             PERD_OID_PERI,
                             TMOV_OID_TIPO_MOVI,
                             FEC_ULTI_ACTU,
                             VAL_DESC,
                             USU_MODI,
                             PERD_OID_PERI_REFE,
                             NUM_PUNT_BONI,
                             COPA_OID_MIGR_ORIG,
                             IND_APOR_PROG_PUNT,
                             DES_MOTI,
                             CANA_ORIG)
                          VALUES
                            (INC_CUCP_SEQ.NEXTVAL,
                             INC_CUCP_SEQ.NEXTVAL,
                             lnPuntosAbonar,
                             0,
                             to_date(psFechaFacturacion, 'DD/MM/YYYY'),
                             programasRecordN(x).oidProgramaPuntos,
                             consultorasRecordN(y).oidCliente,
                             consultorasRecordN(y).oidPeriodo,
                             1,
                             SYSDATE,
                             'Abono de puntos por Programa de Constancia ' || programasRecordN(x)
                             .codigoPrograma,
                             psUsuario,
                             NULL,
                             NULL,
                             NULL,
                             1,
                             'Constancia de Nuevas',
                             HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(consultorasRecordN(y)
                                                                          .oidSolicitud,
                                                                          (SELECT COD_CLIE
                                                                             FROM MAE_CLIEN
                                                                            WHERE OID_CLIE = consultorasRecordN(y)
                                                                                 .oidCliente)));
                        
                        END IF;
                      
                      END IF;
                    END IF;

                END IF;
              
              END LOOP;
            
            END IF;
            EXIT WHEN c_consultoras%NOTFOUND;
          
          END LOOP;
          CLOSE c_consultoras;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_programas%NOTFOUND;
    
    END LOOP;
    CLOSE c_programas;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_ACUMU_PUNTO_PROGR_CONST: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_ACUMU_PUNTO_PROGR_CONST;

  /**************************************************************************
  Descripcion : Obtiene la cantidad de pedidos en periodos consecutivosque ha 
             realizado la consultora
  Fecha Creacion : 06/04/2015
  Parametros Entrada :
   psCodigoPais     :  Codigo de pais
   psCampanyaProceso  :  Campaña de proceso
   psOidCliente  :  Oid del cliente
  Autor : Ivan Tocto
  ***************************************************************************/
  FUNCTION INC_FN_OBTEN_PEDPE_CONSEC(psCodigoPais      VARCHAR2,
                                     psCampanyaProceso VARCHAR2,
                                     psOidCliente      NUMBER,
                                     psCampinicio      VARCHAR2)
    RETURN NUMBER IS
  
    TYPE periodoType IS TABLE OF VARCHAR2(6) INDEX BY PLS_INTEGER;
    periodosTable        periodoType;
    lsCampanyaAnterior   varchar2(6);
    lnCampanyasConstante number := 0;
  
  BEGIN
  
    SELECT PERI BULK COLLECT
      INTO periodosTable
      FROM (SELECT psCampanyaProceso PERI
              FROM DUAL
            UNION
            SELECT FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(Z.PERD_OID_PERI) PERI
              FROM PED_SOLIC_CABEC     Z,
                   PED_TIPO_SOLIC_PAIS B,
                   PED_TIPO_SOLIC      C,
                   PED_SOLIC_CABEC     CON
             WHERE Z.IND_OC = 1
               AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
               AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
               AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
               AND CON.OID_SOLI_CABE = Z.SOCA_OID_SOLI_CABE
               AND CON.PERD_OID_PERI = Z.PERD_OID_PERI
               AND CON.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
               AND Z.CLIE_OID_CLIE = psOidCliente
               AND FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(Z.PERD_OID_PERI) BETWEEN
                   psCampinicio AND psCampanyaProceso
             ORDER BY 1 DESC);
  
    IF periodosTable.COUNT > 1 THEN
      FOR indx IN periodosTable.FIRST .. periodosTable.LAST LOOP
        IF indx < periodosTable.COUNT THEN
          lsCampanyaAnterior := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                                 periodosTable(indx),
                                                                 -1);
        
          IF (lsCampanyaAnterior = periodosTable(indx + 1)) THEN
            lnCampanyasConstante := lnCampanyasConstante + 1;
          ELSE
            EXIT;
          END IF;
        
        END IF;
      END LOOP;
    END IF;
  
    IF lnCampanyasConstante >= 0 THEN
      lnCampanyasConstante := lnCampanyasConstante + 1;
    END IF;
  
    RETURN lnCampanyasConstante;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_FN_OBTEN_PEDPE_CONSEC: ' ||
                              ls_sqlerrm);
      RETURN 0;
    
  END INC_FN_OBTEN_PEDPE_CONSEC;

  /**************************************************************************
  Descripcion : Obtiene el indicador de Región piloto Retail para una consultora
  Fecha Creacion : 06/07/2015
  Parametros Entrada :
   psCodConsu     :  Codigo de consultora
  Autor : Hernán Ramos
  ***************************************************************************/
  FUNCTION INC_FN_OBTEN_INDPI_RETAI(psCodConsu      VARCHAR2) 
           RETURN VARCHAR2 is lsIndPiRetai VARCHAR2(1);

  BEGIN
       SELECT NVL(TRIM(ZON_REGIO.IND_PILO_RETA),'0')
       INTO lsIndPiRetai
       FROM MAE_CLIEN CLI, MAE_CLIEN_UNIDA_ADMIN UA, ZON_TERRI_ADMIN ZA, ZON_SECCI SEC, ZON_ZONA ZON,
                ZON_REGIO
       WHERE 
          CLI.COD_CLIE=psCodConsu
          AND CLI.OID_CLIE = UA.CLIE_OID_CLIE 
          AND UA.ZTAD_OID_TERR_ADMI = ZA.OID_TERR_ADMI
          AND ZA.ZSCC_OID_SECC = SEC.OID_SECC
          AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
          AND UA.IND_ACTI = 1
          AND ZORG_OID_REGI = OID_REGI;    
 
       RETURN lsIndPiRetai; 
  
  EXCEPTION
    WHEN OTHERS THEN      
      RETURN '0';
  END;

  /**************************************************************************
  Descripcion       : Realiza el Calculo de Vencimiento de Puntos por Consultora
  Fecha Creacion    : 20/01/2016
  Parametros Entrada:
    pnOidConcurso     :  Oid Concurso
    pnOidCliente    :  Oid Cliente
    pnIndBorrado  :  Indicador de Borrado: 0->Borrar, 1->No Borrar
  
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE INC_PR_VENCI_PUNTO_CONCU(pnOidConcurso     NUMBER,
                                     pnOidCliente      NUMBER,
                                     pnIndBorrado      NUMBER) IS
    lsCampEval      SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCampEvalMax   SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodPeriodo    SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
    
    lsCodPeriodoSig SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnOidPeriodoSig CRA_PERIO.OID_PERI%TYPE;
    
    lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
    
    lnAbono         INC_TEMPO_VCMTO_PNTOS.ABN_PTOS%TYPE;  
    lnCargo         NUMBER;                                   
  BEGIN                                        

    lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
                                                              
    IF(pnIndBorrado = 1) THEN
      DELETE INC_TEMPO_VCMTO_PNTOS 
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente;
         
    END IF;
    
      INSERT INTO INC_TEMPO_VCMTO_PNTOS
      SELECT pnOidConcurso,
             pnOidCliente,
             PERD_OID_PERI,
             FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(PERD_OID_PERI),
             SUM(NUM_PUNT),
             NULL
        FROM INC_CUENT_CORRI_PUNTO
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente
         AND NUM_PUNT > 0
       GROUP BY PERD_OID_PERI;     

      --Calculamos Campaña Inicio a aplicar Cargos
      SELECT MIN(COD_PERI)
        INTO lsCampEval
        FROM INC_TEMPO_VCMTO_PNTOS
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente;   

      SELECT MAX(COD_PERI)
        INTO lsCampEvalMax
        FROM INC_TEMPO_VCMTO_PNTOS
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente; 
               
      IF(lsCampEval IS NOT NULL) THEN
        SELECT COD_PERI,
               PERD_OID_PERI,
               ABN_PTOS
          INTO lsCodPeriodo,
               lnOidPeriodo,
               lnAbono     
          FROM INC_TEMPO_VCMTO_PNTOS
         WHERE COPA_OID_PARA_GRAL = pnOidConcurso
           AND CLIE_OID_CLIE = pnOidCliente
           AND COD_PERI = lsCampEval;
        
        lnCargo := 0;
        
        FOR y IN (SELECT (ccp.NUM_PUNT * -1) NUM_PUNT
                    FROM INC_CUENT_CORRI_PUNTO ccp, CRA_PERIO cra, SEG_PERIO_CORPO  spc
                   WHERE ccp.COPA_OID_PARA_GRAL = pnOidConcurso
                     AND ccp.CLIE_OID_CLIE = pnOidCliente
                     AND ccp.NUM_PUNT < 0
                     AND ccp.PERD_OID_PERI = cra.OID_PERI
                     AND cra.PERI_OID_PERI = spc.OID_PERI
                   ORDER BY spc.COD_PERI, ccp.FEC_MOVI) LOOP
          
          lnCargo := lnCargo + y.NUM_PUNT;
          
          IF((lnAbono > 0) AND  (lnCargo > lnAbono)) THEN
             UPDATE INC_TEMPO_VCMTO_PNTOS
                SET CAR_PTOS = ABN_PTOS
              WHERE COPA_OID_PARA_GRAL = pnOidConcurso
                AND CLIE_OID_CLIE = pnOidCliente
                AND PERD_OID_PERI = lnOidPeriodo; 
                
            lnCargo := lnCargo - lnAbono;   
            
            lnAbono := 0;
            
            FOR x IN (SELECT COD_PERI,
                             PERD_OID_PERI,
                             ABN_PTOS  
                      FROM INC_TEMPO_VCMTO_PNTOS
                     WHERE COPA_OID_PARA_GRAL = pnOidConcurso
                       AND CLIE_OID_CLIE = pnOidCliente
                       AND COD_PERI > lsCampEval
                      ORDER BY COD_PERI) LOOP
           
              lsCampEval := x.COD_PERI;
              lsCodPeriodo := x.COD_PERI;
              lnOidPeriodo := x.PERD_OID_PERI;
              lnAbono := x.ABN_PTOS;      
              
              IF((lnAbono > 0) AND  (lnCargo > lnAbono)) THEN
                UPDATE INC_TEMPO_VCMTO_PNTOS
                   SET CAR_PTOS = ABN_PTOS
                 WHERE COPA_OID_PARA_GRAL = pnOidConcurso
                   AND CLIE_OID_CLIE = pnOidCliente
                   AND PERD_OID_PERI = lnOidPeriodo; 
                
                lnCargo := lnCargo - lnAbono;   
                lnAbono := 0;   
              ELSE
                EXIT;  
              END IF;
                  
            END LOOP;
               
          END IF;  
                   
        END LOOP;
        
        IF(lnAbono = 0) THEN
          SELECT PAIS_OID_PAIS
            INTO lnOidPais
            FROM MAE_CLIEN
           WHERE OID_CLIE = pnOidCliente;

          --Obtenemos el periodo Anterior
          lsCodPeriodoSig := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriodo,
                                                                    lnOidPais,
                                                                    lnOidMarca,
                                                                    lnOidCanal,
                                                                    1);   
                                                                    
          lnOidPeriodoSig := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSig,
                                                                     lnOidMarca,
                                                                     lnOidCanal);                                                                        
        
          INSERT INTO INC_TEMPO_VCMTO_PNTOS
          VALUES(pnOidConcurso,
                 pnOidCliente,
                 lnOidPeriodoSig,
                 lsCodPeriodoSig,
                 NULL,
                 lnCargo);    
        ELSE
          UPDATE INC_TEMPO_VCMTO_PNTOS
             SET CAR_PTOS = lnCargo
           WHERE COPA_OID_PARA_GRAL = pnOidConcurso
             AND CLIE_OID_CLIE = pnOidCliente
             AND PERD_OID_PERI = lnOidPeriodo;          
        END IF;


      
    END IF;
                                              
/*  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_VENCI_PUNTO_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);*/
    
  END INC_PR_VENCI_PUNTO_CONCU;

  /**************************************************************************
  Descripcion       : Realiza el Calculo de vencimiento de punto al Cierre
                      de Campaña
  Fecha Creacion    : 20/01/2016
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Sergio Apaza
  
  ***************************************************************************/
  PROCEDURE INC_PR_CALCU_VENCI_PUNTO_CONCU(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS
    lnOidPais    SEG_PAIS.OID_PAIS%TYPE;
    lnOidMarca   SEG_MARCA.OID_MARC%TYPE;
    lnOidCanal   SEG_CANAL.OID_CANA%TYPE;
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
  
    ldFechaProceso     BAS_CTRL_FACT.FEC_PROC%TYPE;
    lnOidSecuencia     INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
    lnNumCampanas      NUMBER;
    lnTotal            NUMBER;
    
    CURSOR c_Consultoras(oidConcurso NUMBER) IS
      SELECT DISTINCT CLIE_OID_CLIE
        FROM INC_CANDI_GANAD gan
       WHERE gan.COPA_OID_PARA_GRAL = oidConcurso
         AND NOT EXISTS
             (SELECT *
                      FROM INC_DESCA des
                     WHERE des.CLIE_OID_CLIE = gan.CLIE_OID_CLIE
                       AND des.COPA_OID_PARA_GRAL = gan.COPA_OID_PARA_GRAL);

    TYPE interfazConsultoras IS RECORD(
      oidCliente MAE_CLIEN.OID_CLIE%TYPE);
  
    TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
    interfazRecordN interfazConsultorasTab;
    
    CURSOR c_Vencimientos(numeroCampanas NUMBER) IS
      SELECT DISTINCT CLIE_OID_CLIE,
             COPA_OID_PARA_GRAL
        FROM INC_TEMPO_VCMTO_PNTOS
       WHERE gen_pkg_gener.gen_fn_devue_difer_perio_pais1(psCodigoPais, COD_PERI, psCodigoPeriodo) >= numeroCampanas
         AND (NVL(ABN_PTOS,0) - NVL(CAR_PTOS,0)) >0;
  
    TYPE interfazVencimientos IS RECORD(
      oidCliente MAE_CLIEN.OID_CLIE%TYPE,
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE);
  
    TYPE interfazVencimientosTab IS TABLE OF interfazVencimientos;
    interfazRecordV interfazVencimientosTab;
  
  BEGIN
    
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TEMPO_VCMTO_PNTOS';
  
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                               lnOidMarca,
                                                               lnOidCanal);
                                                               
    --Obtenemos la Fecha de Proceso
    SELECT FEC_PROC
      INTO ldFechaProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
                                                                      
    lnNumCampanas := gen_pkg_gener.gen_fn_param_pais(psCodigoPais, 'GEN', '000');
    
    --PROCESAMOS A LAS CONCURSOS MULTIMARCA
    FOR y IN (SELECT C.OID_PARA_GRAL
                FROM INC_CONCU_PARAM_GENER C,
                     CRA_PERIO             PI,
                     SEG_PERIO_CORPO       SI
               WHERE C.IND_ACTI = 1
                 AND C.PERD_OID_PERI_DESD = PI.OID_PERI
                 AND PI.PERI_OID_PERI = SI.OID_PERI
                 AND psCodigoPeriodo>=SI.COD_PERI -- Nuevo por ajuste.
--                 AND gen_pkg_gener.gen_fn_devue_difer_perio_pais1(psCodigoPais, SI.COD_PERI, psCodigoPeriodo)>= lnNumCampanas -- Ajuste
                 AND C.IND_PROG_PUNT = '1') LOOP
    
      OPEN c_Consultoras(y.OID_PARA_GRAL);
      LOOP
        FETCH c_Consultoras BULK COLLECT
          INTO interfazRecordN LIMIT W_FILAS;
        IF interfazRecordN.COUNT > 0 THEN
        
          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          
            INC_PR_VENCI_PUNTO_CONCU(y.OID_PARA_GRAL, 
                                     interfazRecordN(x).oidCliente,
                                     0);                            
          
          END LOOP;
        
        END IF;
        EXIT WHEN c_Consultoras%NOTFOUND;
      END LOOP;
      CLOSE c_Consultoras;
    
    END LOOP;
  
    OPEN c_Vencimientos(lnNumCampanas);
    LOOP
      FETCH c_Vencimientos BULK COLLECT
        INTO interfazRecordV LIMIT W_FILAS;
      IF interfazRecordV.COUNT > 0 THEN
        
        FOR x IN interfazRecordV.FIRST .. interfazRecordV.LAST LOOP
          
          SELECT SUM(NVL(ABN_PTOS,0) - NVL(CAR_PTOS,0))
            INTO lnTotal
            FROM INC_TEMPO_VCMTO_PNTOS                          
           WHERE COPA_OID_PARA_GRAL = interfazRecordV(x).oidConcurso
             AND CLIE_OID_CLIE = interfazRecordV(x).oidCliente
             AND gen_pkg_gener.gen_fn_devue_difer_perio_pais1(psCodigoPais, COD_PERI, psCodigoPeriodo) >= lnNumCampanas
             AND (NVL(ABN_PTOS,0) - NVL(CAR_PTOS,0)) >0;
             
          IF(lnTotal > 0) THEN
            lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
            
            INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 USU_MODI,
                 PERD_OID_PERI_REFE,
                 NUM_PUNT_BONI,
                 COPA_OID_MIGR_ORIG,
                 IND_APOR_PROG_PUNT,
                 DES_MOTI,
                 CANA_ORIG)
              VALUES
                (lnOidSecuencia,
                 lnOidSecuencia,
                 lnTotal * (-1),
                 0,
                 ldFechaProceso,
                 interfazRecordV(x).oidConcurso,
                 interfazRecordV(x).oidCliente,
                 lnOidPeriodo,
                 2,
                 SYSDATE,
                 'Cargo por Vencimiento de Puntos',
                 psCodigoUsuario,
                 NULL,
                 0,
                 NULL,
                 1,
                 'Vencimiento de Puntos',
                 NULL);   
                 
            DELETE FROM INC_TEMPO_VCMTO_PNTOS                          
             WHERE COPA_OID_PARA_GRAL = interfazRecordV(x).oidConcurso
               AND CLIE_OID_CLIE = interfazRecordV(x).oidCliente
               AND gen_pkg_gener.gen_fn_devue_difer_perio_pais1(psCodigoPais, COD_PERI, psCodigoPeriodo) >= lnNumCampanas
               AND (NVL(ABN_PTOS,0) - NVL(CAR_PTOS,0)) >0;            
          END IF;      
        END LOOP;
        
      END IF;
      EXIT WHEN c_Vencimientos%NOTFOUND;
    END LOOP;
    CLOSE c_Vencimientos;
                 
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_CALCU_VENCI_PUNTO_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
    
  END INC_PR_CALCU_VENCI_PUNTO_CONCU;
       
  /**************************************************************************
  Descripcion       : Realiza el Proceso de vencimiento de puntos
  Fecha Creacion    : 03/02/2016
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de Marca
    psCodigoCanal    :  Codigo de Canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario
  
  Autor             : Segundo Leiva
  
  ***************************************************************************/
  PROCEDURE INC_PR_PROCE_VENCI_PUNTO_CONCU(psCodigoPais    VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS
    lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;


    CURSOR c_Consultoras(pnOidPeriodo NUMBER) IS
       SELECT distinct clie_oid_clie
               FROM PED_SOLIC_CABEC cab,
                     PED_TIPO_SOLIC_PAIS tsp,
                     PED_TIPO_SOLIC sol
               WHERE cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                 AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
                 AND sol.COD_TIPO_SOLI = 'SOC'
                   AND cab.GRPR_OID_GRUP_PROC = 4
                 AND PERD_OID_PERI = pnOidPeriodo;  -- Oid de periodo de proceso pasado como parámetro de la pantalla;

    TYPE interfazConsultoras IS RECORD(
      oidCliente MAE_CLIEN.OID_CLIE%TYPE);

    TYPE interfazConsultorasTab IS TABLE OF interfazConsultoras;
    interfazRecordN interfazConsultorasTab;

    CURSOR c_Concursos IS
      SELECT C.OID_PARA_GRAL oidConcurso-- Oid de concurso
                FROM INC_CONCU_PARAM_GENER C,
                     CRA_PERIO             PI,
                     SEG_PERIO_CORPO       SI
               WHERE C.IND_ACTI = 1
                 AND C.PERD_OID_PERI_DESD = PI.OID_PERI
                 AND PI.PERI_OID_PERI = SI.OID_PERI
                 AND C.IND_PROG_PUNT = '1';

    TYPE interfazConcursos IS RECORD(
      oidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE);

    TYPE interfazConcursosTab IS TABLE OF interfazConcursos;
    interfazRecordC interfazConcursosTab;

  BEGIN


    --Recuperamos el oid Pais,Marca,Canal,Periodo
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);


      OPEN c_Consultoras(lnOidPeriodo);
      LOOP
        FETCH c_Consultoras BULK COLLECT
          INTO interfazRecordN LIMIT W_FILAS;
        IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          OPEN c_Concursos;
          LOOP
              FETCH c_Concursos BULK COLLECT
                INTO interfazRecordC LIMIT W_FILAS;
                      IF interfazRecordC.COUNT > 0 THEN

                        FOR y IN interfazRecordC.FIRST .. interfazRecordC.LAST LOOP

                          INC_PR_VENCI_PUNTO_CONCU(interfazRecordC(y).oidConcurso,
                                                   interfazRecordN(x).oidCliente,
                                                   1);

                        END LOOP;

                      END IF;
              EXIT WHEN c_Concursos%NOTFOUND;
          END LOOP;
          CLOSE c_Concursos;

          END LOOP;

        END IF;
        EXIT WHEN c_Consultoras%NOTFOUND;
      END LOOP;
      CLOSE c_Consultoras;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INC_PR_PROCE_VENCI_PUNTO_CONCU: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);

  END INC_PR_PROCE_VENCI_PUNTO_CONCU;
                       
END Inc_Pkg_Proce_Incen;
/
