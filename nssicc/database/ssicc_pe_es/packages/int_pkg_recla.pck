CREATE OR REPLACE PACKAGE "INT_PKG_RECLA" IS

  -- Author  : PEEXTMSILVA
  -- Created : 08/03/2007 15:45:18
  -- Purpose : Interfaces Recepcion Fisica de Mercaderia
  /* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  /*******************************************/
  /*            GEN_FN_OID_REG               */
  /*******************************************/
FUNCTION GEN_FN_OID_REG( nCliente IN NUMBER,
                                        vchPais      IN VARCHAR2)
    RETURN VARCHAR2;
  /***************************/
  /* devuelve acceso          */
  /*******************************/

FUNCTION GEN_FN_ACCESO( psPeriOid IN NUMBER)
RETURN VARCHAR2;

  /***************************/
  /* devuelve subacceso          */
  /*******************************/

FUNCTION GEN_FN_SUB_ACCESO( psSbacOid IN NUMBER)
RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_OID_REGIO_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
    RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve el oid de Zona dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 09/07/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_OID_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
    RETURN NUMBER;
  /***************************/
  /* devuelve campa?a          */
  /*******************************/

FUNCTION GEN_FN_CAMPANHA( psPeriOid IN NUMBER)
RETURN VARCHAR2;

  /***************************/
  /* devuelve canal          */
  /*******************************/

FUNCTION GEN_FN_CANAL( psPeriOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_CODSAP_PROD                              */
  /*******************************************/

FUNCTION GEN_FN_CODSAP_PROD( psProductoOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_COD_CLIE                              */
  /*******************************************/

FUNCTION GEN_FN_COD_CLIE( psClienteOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_COD_VENTA                              */
  /*******************************************/
 FUNCTION GEN_FN_COD_VENTA( psMatrFactOid IN NUMBER)
  RETURN VARCHAR2;
  /******************************************************************/
  /*                   GEN_FN_COD_ESTR                              */
  /******************************************************************/

FUNCTION GEN_FN_COD_ESTR( psMatrFactOid IN NUMBER)
RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de Territorio dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
FUNCTION GEN_FN_COD_TERRI_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
    RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 09/07/2007
  Autor              : Marco Agurto
  ***************************************************************************/
FUNCTION GEN_FN_COD_REGI_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
    RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de Zona dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_COD_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2,booTodosCondiciones BOOLEAN := FALSE)
    RETURN VARCHAR2;
  /**************************************************************************
    Descripcion        : Devuelve el codigo de Zona dado un OID de ZON_TERRI_AMIN
                         considerando a todos los territorios
    Fecha Creacion     : 12/06/2007
    Autor              : Jose A. Cairampoma
  ***************************************************************************/
FUNCTION GEN_FN_COD_ZONA_BYZON_TERRI2( oidTerriAdmin IN NUMBER,vchPais   IN varchar2,vchTodosCondiciones IN varchar2)
    RETURN VARCHAR2;
  /******************************************************************/
  /*                   GEN_FN_COD_TIPO_OFERT                               */
  /******************************************************************/

FUNCTION GEN_FN_COD_TIPO_OFERT( psTipOfertOid IN NUMBER)
RETURN VARCHAR2;

  /***************************/
  /* deveueve DESCRIPCION         */
  /*******************************/

FUNCTION GEN_FN_DESC_GENER( psTablaOid IN NUMBER, psDescripcion IN VARCHAR2)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

FUNCTION GEN_FN_DESC_PROD( psProductoOid IN NUMBER)
RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la zona dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2,booTodosCondiciones BOOLEAN := FALSE)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 29/08/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_ZONA_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
    RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 18/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_REGIO_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
    RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 29/08/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_OID_ZONA_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
  RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 18/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_REGIO_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
    RETURN VARCHAR2;
  /***************************/
  /*  GEN_FN_DES_REG         */
  /*******************************/
  FUNCTION GEN_FN_DES_REG( nCliente IN NUMBER,
                                          vchPais      IN varchar2 )
    RETURN VARCHAR2;
  /*****************************************/
  /*            GEN_FN_DEV_REC_ENTRE_MERCA                              */
  /*******************************************/
  FUNCTION GEN_FN_DEV_REC_ENTRE_MERCA(psOidMerca number)
    RETURN varchar2 ;

  /*****************************************/
  /*            GEN_FN_DEV_REC_PRECI_PERDI                              */
  /*******************************************/
  FUNCTION GEN_FN_DEV_REC_PRECI_PERDI(psOidPrecioPer number)
    RETURN varchar2 ;

  /*****************************************/
  /*            GEN_FN_MARCA_PROD                               */
  /*******************************************/

FUNCTION GEN_FN_MARCA_PROD( psProductoOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_NEGO_PROD                              */
  /*******************************************/

FUNCTION GEN_FN_NEGO_PROD( psProductoOid IN NUMBER)
RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_NUMLIN_OFER                               */
  /******************************************************************/

FUNCTION GEN_FN_NUMLIN_OFER( psMatrFactOid IN NUMBER)
RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_NUM_OFER                              */
  /******************************************************************/

FUNCTION GEN_FN_NUM_OFER( psMatrFactOid IN NUMBER)
RETURN number;

  /*****************************************/
  /*            GEN_FN_REC_COD_ALMAP                              */
  /*******************************************/

FUNCTION GEN_FN_REC_COD_ALMAP( pscodigointerfaz IN varchar2 , psOidAlmaPed IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_COD_ALMAC                              */
  /*******************************************/

FUNCTION GEN_FN_REC_COD_ALMAC( psTipoOperOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_COD_OPER                              */
  /*******************************************/

FUNCTION GEN_FN_REC_COD_OPER( psTipoOperOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_COD_TIPO_ING                              */
  /*******************************************/

FUNCTION GEN_FN_REC_COD_TIPO_ING( psTipoIngOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_NUM_LOTE                              */
  /*******************************************/

FUNCTION GEN_FN_REC_NUM_LOTE( psLineaOperRecOid IN NUMBER)
RETURN VARCHAR2;
  /*****************************************/
  /*            GEN_FN_REC_TIPO_MOVI_ALMAC                              */
  /*******************************************/

FUNCTION GEN_FN_REC_TIPO_MOVI_ALMAC( psTipoOperOid IN NUMBER)
    RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_TIPO_OPER                              */
  /*******************************************/

FUNCTION GEN_FN_REC_TIPO_OPER( psTipoOperOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

FUNCTION GEN_FN_TIPO_PERIO( psPeriOid IN NUMBER)
RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_ofer                              */
  /*******************************************/

FUNCTION GEN_FN_TIPO_OFER( psvalpain IN varchar2,psCodTipoOfer in varchar2 ,psCopaoidparagene in NUMBER)
RETURN VARCHAR2;

  /* Declaracion de procedures */
  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar SAM 7
                        Cabecera
   Fecha Creacion     : 27/02/2007
   Autor              : Marco Silva
  ***************************************************************************/
PROCEDURE INT_PR_REC_ENVIA_PROD
  ( psCodigoPais         VARCHAR2,
    psCodigoSistema      VARCHAR2,
    psCodigoInterfaz     VARCHAR2,
    psNombreArchivo      VARCHAR2,
    psFecha              VARCHAR2 ,
    psTipoMovim          VARCHAR2 ,
    psNumeroLote         VARCHAR2,
    psNumeroSecuencia    VARCHAR2
   ) ;

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz INT_PR_REC_CABEC_RECLA
                        Detalle
   Fecha Creacion     : 27/02/2007
   Autor              : Marco Silva
  ***************************************************************************/
PROCEDURE INT_PR_REC_CABEC_RECLA
  (psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2
   ) ;

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar INT_PR_REC_OPERA_RECLA
                        del Cliente
   Fecha Creacion     : 27/02/2007
   Autor              : Marco Silva
  ***************************************************************************/
PROCEDURE INT_PR_REC_OPERA_RECLA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2
   ) ;

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar INT_PR_REC_LINEA_RECLA
                        de Referencia
   Fecha Creacion     : 27/02/2007
   Autor              : Marco Silva
  ***************************************************************************/
PROCEDURE INT_PR_REC_LINEA_RECLA
  (psCodigoPais        VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2
   ) ;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_PERI_MATR_FACT
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO existe CAMPANHA 1: Existe CAMPANHA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION GEN_FN_DEV_PERI_MATR_FACT(pnOidMatrizFact  number)
    RETURN NUMBER ;

  /**************************************************************************
  Descripcion        : Devuelve Id de Subacceso
  Fecha Creacion     : 05/01/2007
  Parametros Entrada :
             psCodSociedad : Codigo de Subacceso
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco  SIlva
  ***************************************************************************/
  FUNCTION GEN_FN_DEV_ID_SUBAC(psCodSubac          VARCHAR2)
    RETURN NUMBER ;

/**************************************************************************
  Descripcion        : Genera atenciones inteligentes
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_gener_aten_inte
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
);

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Generar
                       Boletas de Recojo del Cliente
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_gener_bolet_recoj
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
);
/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cabeceras de Boleta de Recojo
  Fecha Creacion    : 12/12/2007
  Autor             : Jose A. Cairampoma
  ***************************************************************************/

PROCEDURE int_pr_rec_recep_borec_cabec(pscodigopais VARCHAR2);

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalle de Boleta de Recojo
  Fecha Creacion    : 12/12/2007
  Autor             : Jose A. Cairampoma
  ***************************************************************************/

PROCEDURE int_pr_rec_recep_borec_detal(pscodigopais VARCHAR2);
/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabeceras de Boleta de Recojo
  Fecha Creacion    : 27/02/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_cabec
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Lineas de Boleta de Recojo
  Fecha Creacion    : 27/02/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_detal
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Lineas de Boleta de Recojo
  Fecha Creacion    : 27/02/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_ctrl
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2
);

  /**************************************************************************
  Descripcion        : D0evuelve el oid del No Motivo de la Boleta de Recojo si no encuetra
                       devuelve 0 si no existe
  Fecha Creacion     : 28/02/2008
  Autor              : Leonardo Lizana
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_MOTNO_BOREC(psCodPais         IN VARCHAR2,
                                        psCodMontRecoBore IN VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion        : D0evuelve el oid del Estado de la Boleta de Recojo si no encuetra
                       devuelve 0 si no existe
  Fecha Creacion     : 28/02/2008
  Autor              : Leonardo Lizana
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodPais         IN VARCHAR2,
                                        psCodMontRecoBore IN VARCHAR2)
    RETURN NUMBER;
  /**************************************************************************
  Descripcion        : D0evuelve el ocodigo de periodo de la cabecera de Reclamo
  Fecha Creacion     : 28/02/2008
  Autor              : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_PERIO_CAREC(pnOidCabeceraReclamo NUMBER)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el ocdigo de Venta de la matriz de facturacion
  Fecha Creacion     : 28/02/2008
  Autor              : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_VENTA_MFACT(pnOidTipoOper NUMBER,
                                        pnOidMatrizFacturacion NUMBER,
                                        pnProdOidProd     NUMBER)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el ocdigo de Venta de la matriz de facturacion
  Fecha Creacion     : 28/02/2008
  Autor              : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_VENTA_FICTI(pnOidLotePremArtic NUMBER,
                                        pnOidParamNivePrem NUMBER ,
                                        pnOidParamGeneral NUMBER,
                                        pnProdOidProd     NUMBER)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : D0evuelve el oid del Estado de la Boleta de Recojo si no encuetra
                       devuelve 0 si no existe
  Fecha Creacion     : 28/02/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_GRUPO_PROCE(psCodGrupoProceso IN VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve el Oid del producto discrepante
                       devuelve NULL si no existe
  Fecha Creacion     : 28/02/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_PRODU_DISCR(psCodigoProducto IN VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve el numero de secuencia siguiente del detalle
                       de Boleta de Recojo.
  Fecha Creacion     : 28/02/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_SIGSE_LINEA_BOREC(psCodigoPais IN VARCHAR2,
                                    psCodigoCabecera IN VARCHAR2)
    RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve el Oid Tipo de solicitud pasi por codigo
  Fecha Creacion     : 28/02/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_TIPO_SOLPA(psCodigoTipoSolicitud VARCHAR2)RETURN NUMBER;

  /**************************************************************************
   Descripcion        : Enviar OCS Boletas de Recojo
   Fecha Creacion     : 13/03/2007
   Autor              : Leonardo Lizana
  ***************************************************************************/
  PROCEDURE rec_pr_envia_ocs_borec
  (
    pscodpais                 VARCHAR2,
    psfechafacturacioninicial VARCHAR2,
    psfechafacturacionfinal   VARCHAR2,
    psvalusuario              VARCHAR2
  );
  /**************************************************************************
   Descripcion        : Genera la Interfaz d Envio de Transferenci de Boletas de Recojo
                        Detalle
   Fecha Creacion     : 27/03/2008
   Autor              : Jose A. cairampoma Granados
  ***************************************************************************/
  PROCEDURE int_pr_rec_envia_trans_borec
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psindicadorvirtual VARCHAR2,
	  psindicadoranulacion VARCHAR2,
    psboletarecojocorte VARCHAR2
  );
  /**************************************************************************
   Descripcion        : Genera la Interfaz d Envio de Unidades a almacen Virtual
                        Detalle
   Fecha Creacion     : 25/06/2008
   Autor              : Jose A. cairampoma Granados
  ***************************************************************************/
  PROCEDURE int_pr_rec_envia_unida_almvi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2,
    pscodigoPeriodo    VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Devuelve la descripcion del estado de Boleta de Recojo
  Fecha Creacion     : 14/07/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_devue_deest_borec(pnoidestado NUMBER)
    RETURN VARCHAR2;

/**************************************************************************
  Descripcion        : Procesa una Boleta de Recojo No Exitosa
  Fecha Creacion     : 12/09/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_borec_noexi
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2
);

  /**************************************************************************
   Descripcion        : Procesa una Boleta de Recojo Con Discreapncia
   Fecha Creacion     : 12/09/2008
   Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_borec_discr
  (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos acumulados
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_rec_gener_mensj_recla
  (
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigodmensaje VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Ejecuta un Mensaje de Reclamos
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psSentencia : Sentencia SQL a ejecutar
  Autor              : Jose
  ***************************************************************************/
  PROCEDURE int_pr_ejec_mensa_recla_dinam
  (
    psprocedure     VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnoidmensaje    NUMBER
  );
  /**************************************************************************
  Descripcion        : Bloquea Consultora POr Boleta de Recojo CErrada no Exitosa
  Fecha Creacion     : 10/12/2006
  Parametros Entrada :
             psSentencia : Sentencia SQL a ejecutar
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE rec_pr_bloqu_consu
  (
    pscodigocliente VARCHAR2,
    pscodigousuario VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Desbloquea Consultora POr Boleta de Recojo a cierre de campa¿a
  Fecha Creacion     : 17/03/2015
  Parametros Entrada :
             psSentencia : Sentencia SQL a ejecutar
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE rec_pr_desbl_consu
  (
    pscampana       VARCHAR2
  );

  /**************************************************************************
   Descripcion        : Devuelve el indicador de Bloqueo por post Venta
                        1: Bolequeado
                        0: No bLoqueado
   Fecha Creacion     : 10/12/2006
   Autor              : Jose Cairampoma
   ***************************************************************************/
   FUNCTION rec_fn_devue_indic_bloqu_consu(pscodigocliente VARCHAR2)
  RETURN NUMBER;

   /**************************************************************************
   Descripcion        : Ejecuta un Mensaje de Reclamos
   Fecha Creacion     : 21/01/2009
   Autor              : Jose Cairampoma
   ***************************************************************************/
 PROCEDURE int_pr_calcu_abono_cargo_consu
 (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
 );
   /**************************************************************************
   Descripcion        : Modifica la tabla REC_LINEA_OPERA_RECLA  despues de la ejecucion
   					     de la interfaz de envio a almacen virtual
   Fecha Creacion     : 01/04/2009
   Autor              : Cristhian Roman
   ***************************************************************************/
 PROCEDURE int_pr_rec_updat_envio_virtu
 (
    pscodigopais     VARCHAR2,
    psnumLote		 VARCHAR2
 );
    /**************************************************************************
   Descripcion        : Modifica la tabla INT_TMP_TRANS_BOREC despues de la ejecucion
   					     de la interfaz de envio de tranferencia de boleta de recojo
   Fecha Creacion     : 02/04/2009
   Autor              : Cristhian Roman
   ***************************************************************************/
 PROCEDURE int_pr_rec_updat_trans_borec
 (
    pscodigopais          VARCHAR2,
    psindicadoranulacion  VARCHAR2
 );
 /**************************************************************************
   Descripcion        : Inserta registros en  la tabla INT_HISTO_TRANS_BOREC despues de la ejecucion
   					     de la interfaz de envio de tranferencia de boleta de recojo
   Fecha Creacion     : 02/04/2009
   Autor              : Cristhian Roman
***************************************************************************/
 PROCEDURE int_pr_rec_histo_trans_borec
 (
    pscodigopais          VARCHAR2
 );
/**************************************************************************
  Descripcion        : Procesa una Boleta de Recojo No Exitosa
  Fecha Creacion     : 05/01/2010
  Autor              : Cristhian Roman
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_borec_noex2
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2
);

  /**************************************************************************
   Descripcion        : Procesa una Boleta de Recojo Con Discreapncia
   Fecha Creacion     : 05/01/2010
   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_borec_disc2
  (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
  );

  /**************************************************************************
   Descripcion        : Devuelve el saldo del producto
   Fecha Creacion     : 11/07/2012
   Autor              : Jorge Velasquez
  ***************************************************************************/
  FUNCTION REC_FN_DEVUE_SALDO_PRODUC(psnumeropedido VARCHAR2,pscuvdesea VARCHAR2)
  RETURN NUMBER;

  /**************************************************************************
   Descripcion        : Devuelve el estado del CDR
   Fecha Creacion     : 27/08/2012
   Autor              : Sandro Quintana
  ***************************************************************************/
  FUNCTION REC_FN_DEVUE_ESTAD_CDR(  psoid VARCHAR2,  pstipo VARCHAR2)
  RETURN VARCHAR2;

 /***************************************************************************
    Descripcion       : Activacion para reimprimir la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_activa_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psUsuario         in VARCHAR2

  );

 /***************************************************************************
    Descripcion       : Anula la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_anula_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psUsuario         in VARCHAR2

  );

 /***************************************************************************
    Descripcion       : Procesa las Boletas de Recojo
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_proce_borec
  (
    psNumLote           in  varchar2,
    psCodEstaBR         in varchar2,
    psUsuario           in VARCHAR2

  );

 /***************************************************************************
    Descripcion       : Anula la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_proce_borec_detal
  (
    psNumLote           in  varchar2,
    psNroBolRecojo    in  number,
    pscodigopais      in varchar2

  );
 /***************************************************************************
    Descripcion       : Validacion de la  Boletas de Recojo
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_valid_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psNroRecojo       out varchar2,
    psCodClie         out varchar2,
    psNomClie         out varchar2

  );

/**************************************************************************
  Descripcion        : Procesa el cargo por Reversion de CDR
  Fecha Creacion     : 01/02/2013
  Autor              : Sandro Quintana
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_rever_cdr
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2,
  psNuevaSolicitud out VARCHAR2
);

 /***************************************************************************
    Descripcion       : Procesa Abono pendiente
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
 PROCEDURE int_pr_rec_proce_abono
  (
    psNroBolRecojo    in  number,
    psUsuario         in VARCHAR2

  );
  /*****************************************/
  /*            GEN_FN_COD_CLIE                              */
  /*******************************************/

FUNCTION GEN_FN_PED_BR( psClienteOid IN NUMBER)
RETURN VARCHAR2;

/**************************************************************************
  Descripcion        : Genera atenciones inteligentes
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_elimi_aten_inte
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
);

  /*****************************************/
  /*            gen_fn_to_date                              */
  /*******************************************/

FUNCTION gen_fn_to_date
  ( psFecha     IN VARCHAR2,
    psFormatoF  IN VARCHAR2,
    psHora      IN VARCHAR2,
    psFormatoH  IN VARCHAR2
  )
RETURN DATE;


END int_pkg_recla;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_RECLA" IS

COD_VALIDACION_CENTRO_SERVICIO    CONSTANT VARCHAR2(7) := 'SPVD-40';

  /*******************************************/
  /*            GEN_FN_OID_REG               */
  /*******************************************/
  FUNCTION GEN_FN_OID_REG( nCliente IN NUMBER,
                                          vchPais      IN varchar2 )
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select zreg.oid_regi into ls_valor
            from mae_clien cl,
                 mae_clien_unida_admin cladm,
                 zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa,
                 zon_sub_geren_venta  zsgv
           where (cl.oid_clie = cladm.clie_oid_clie)
                  and (zadm.oid_terr_admi =cladm.ztad_oid_terr_admi)
                  and (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (zreg.oid_regi = zzon.zorg_oid_regi)
                  and (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
                  and (pa.oid_pais = cl.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and cladm.ind_acti = 1
                  and zreg.ind_acti =1
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and cl.oid_clie = nCliente;
   return ls_valor;
  END GEN_FN_OID_REG;
/**************************************************************************
Descripcion        : Devuelve el codigo de Region dado un OID de ZON_TERRI_AMIN
Fecha Creacion     : 12/06/2007
Autor              : Marco Agurto
***************************************************************************/
  FUNCTION GEN_FN_OID_REGIO_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN NUMBER
  IS
   ls_valor NUMBER;
  BEGIN
  IF booTodosCondiciones THEN
      select zreg.oid_regi into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa,
                 zon_sub_geren_venta  zsgv
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (zreg.oid_regi = zzon.zorg_oid_regi)
                  and (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zadm.oid_terr_admi = oidTerriAdmin;
  ELSE
    select zreg.oid_regi into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa,
                 zon_sub_geren_venta  zsgv
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (zreg.oid_regi = zzon.zorg_oid_regi)
                  and (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zreg.ind_acti =1
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and zadm.oid_terr_admi = oidTerriAdmin;
   END IF;
   return ls_valor;
     EXCEPTION
     WHEN no_data_found THEN
          RETURN -1;
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_OID_REGIO_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_OID_REGIO_BYZON_TERRI;
  /**************************************************************************
  Descripcion        : Devuelve el oid de Zona dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 09/07/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_OID_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN NUMBER
  IS
   ls_valor NUMBER;
  BEGIN
   IF booTodosCondiciones THEN
       select zzon.oid_zona into ls_valor
        FROM zon_terri_admin zadm,
             zon_secci       zsec,
             zon_zona        zzon,
             seg_pais        pa,
             zon_sub_geren_venta  zsgv
       where   (zsec.oid_secc = zadm.zscc_oid_secc)
              and (zzon.oid_zona = zsec.zzon_oid_zona)
              and (pa.oid_pais = zadm.pais_oid_pais)
              and (pa.cod_pais = vchPais )
              and zadm.oid_terr_admi = oidTerriAdmin;
   ELSE
    select zzon.oid_zona into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 seg_pais        pa,
                 zon_sub_geren_venta  zsgv
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and zadm.oid_terr_admi = oidTerriAdmin;
    END IF;
   return ls_valor;
     EXCEPTION
     WHEN no_data_found THEN
          RETURN -1;
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_OID_ZONA_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_OID_ZONA_BYZON_TERRI;

  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 29/08/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_OID_ZONA_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
  RETURN VARCHAR2
  IS
  BEGIN
   IF vchCondicion= 'A' THEN
      RETURN GEN_FN_OID_ZONA_BYZON_TERRI(oidTerriAdmin, vchPais,FALSE);
   ELSE
      RETURN GEN_FN_OID_ZONA_BYZON_TERRI(oidTerriAdmin, vchPais,TRUE);
   END IF;
    EXCEPTION
     WHEN no_data_found THEN
          RETURN ' ';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_OID_ZONA_TERRI_INAC: '||ls_sqlerrm);
  END GEN_FN_OID_ZONA_TERRI_INAC;
  /***************************/
  /* devuelve acceso          */
  /*******************************/

  FUNCTION GEN_FN_ACCESO( psPeriOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select ACCESO.COD_ACCE from Seg_Acces ACCESO
                  where ACCESO.OID_ACCE = PERCRA.ACCE_OID_ACCE ) into ls_valor
            from CRA_PERIO PERCRA
           where PERCRA.OID_PERI = psPeriOid ;

   return ls_valor;

  END GEN_FN_ACCESO;

  /***************************/
  /* devuelve subacceso          */
  /*******************************/

  FUNCTION GEN_FN_SUB_ACCESO( psSbacOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select subACCESO.Cod_Sbac into ls_valor from Seg_Subac subACCESO
                  where subACCESO.Oid_Sbac = psSbacOid;

   return ls_valor;

  END GEN_FN_SUB_ACCESO;

  /***************************/
  /* devuelve campa?a          */
  /*******************************/

  FUNCTION GEN_FN_CAMPANHA( psPeriOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select PERCORP.COD_PERI from SEG_PERIO_CORPO PERCORP
                  where PERCORP.OID_PERI = PERCRA.PERI_OID_PERI ) into ls_valor
            from CRA_PERIO PERCRA
           where PERCRA.OID_PERI  = psPeriOid ;

   return ls_valor;
  END GEN_FN_CAMPANHA;

  /***************************/
  /* devuelve canal          */
  /*******************************/

  FUNCTION GEN_FN_CANAL( psPeriOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select CANAL.COD_CANA from SEG_CANAL CANAL
                  where CANAL.OID_CANA = PERCRA.CANA_OID_CANA ) into ls_valor
            from CRA_PERIO PERCRA
           where PERCRA.OID_PERI = psPeriOid ;

   return ls_valor;
  END GEN_FN_CANAL;

  /*****************************************/
  /*            GEN_FN_CODSAP_PROD                              */
  /*******************************************/

  FUNCTION GEN_FN_CODSAP_PROD( psProductoOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select PROD.COD_SAP into ls_valor
            from MAE_PRODU PROD
           where PROD.OID_PROD  = psProductoOid ;

   return ls_valor;

  END GEN_FN_CODSAP_PROD;

  /*****************************************/
  /*            GEN_FN_COD_CLIE                              */
  /*******************************************/

  FUNCTION GEN_FN_COD_CLIE( psClienteOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select CLI.COD_CLIE into ls_valor
            from MAE_CLIEN CLI
           where CLI.OID_CLIE  = psClienteOid ;

   return ls_valor;

  END GEN_FN_COD_CLIE;

  /******************************************************************/
  /*                   GEN_FN_COD_ESTR                              */
  /******************************************************************/

  FUNCTION GEN_FN_COD_ESTR( psMatrFactOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN

    select ( select est.cod_estr
              from pre_ofert ofer, pre_estra est
              where ofer.oid_ofer = ofdet.ofer_oid_ofer and
                    ofer.coes_oid_estr = est.oid_estr ) into ls_valor
            from PRE_MATRI_FACTU MAFAC , PRE_OFERT_DETAL OFDET
           where MAFAC.OID_MATR_FACT = psMatrFactOid and
                 OFDET.OID_DETA_OFER = MAFAC.OFDE_OID_DETA_OFER ;

   return ls_valor;

  END GEN_FN_COD_ESTR;
    /**************************************************************************
  Descripcion        : Devuelve el codigo de Territorio dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_COD_TERRI_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN NUMBER
  IS
   ls_valor NUMBER(6);
  BEGIN
    select zterr.Cod_Terr into ls_valor
            FROM zon_terri_admin zadm,
                 zon_terri zterr,
                 seg_pais        pa
           where   (zterr.oid_terr = zadm.terr_oid_terr)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zadm.oid_terr_admi = oidTerriAdmin;
   return ls_valor;
     EXCEPTION
    WHEN no_data_found THEN
          RETURN -1;
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_COD_TERRI_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_COD_TERRI_BYZON_TERRI;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de Zona dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 12/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_COD_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN VARCHAR2
  IS
   ls_valor VARCHAR2(4);
  BEGIN
   IF booTodosCondiciones THEN
    select zzon.Cod_Zona into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (pa.cod_pais = vchPais )
            and zadm.oid_terr_admi = oidTerriAdmin;
    ELSE
        select zzon.Cod_Zona into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (pa.cod_pais = vchPais )
            and zsec.ind_acti =1
            and zzon.ind_acti =1
            and zadm.oid_terr_admi = oidTerriAdmin;
    END IF;
   return ls_valor;
     EXCEPTION
    WHEN no_data_found THEN
          RETURN '';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_COD_ZONA_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_COD_ZONA_BYZON_TERRI;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de Zona dado un OID de ZON_TERRI_AMIN
                       considerando a todos los territorios
  Fecha Creacion     : 12/06/2007
  Autor              : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION GEN_FN_COD_ZONA_BYZON_TERRI2( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchTodosCondiciones IN varchar2)
  RETURN VARCHAR2
  IS
   ls_valor VARCHAR2(4);
  BEGIN
   IF (vchTodosCondiciones='SI') THEN
    select zzon.Cod_Zona into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (pa.cod_pais = vchPais )
            and zadm.oid_terr_admi = oidTerriAdmin;
    ELSE
        select zzon.Cod_Zona into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (pa.cod_pais = vchPais )
            and zsec.ind_acti =1
            and zzon.ind_acti =1
            and zadm.oid_terr_admi = oidTerriAdmin;
    END IF;
   return ls_valor;
     EXCEPTION
    WHEN no_data_found THEN
          RETURN '';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_COD_ZONA_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_COD_ZONA_BYZON_TERRI2;
  /******************************************************************/
  /*                   GEN_FN_COD_VENTA                               */
  /******************************************************************/

  FUNCTION GEN_FN_COD_VENTA( psMatrFactOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN

    select (select OFDET.VAL_CODI_VENT  from PRE_OFERT_DETAL OFDET
                   where  OFDET.OID_DETA_OFER = MAFAC.OFDE_OID_DETA_OFER ) into ls_valor
            from PRE_MATRI_FACTU MAFAC
           where MAFAC.OID_MATR_FACT = psMatrFactOid ;

   return ls_valor;

  END GEN_FN_COD_VENTA;

    /**************************************************************************
  Descripcion        : Devuelve el codigo de Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 09/07/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_COD_REGI_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN VARCHAR2
  IS
   ls_valor VARCHAR2(2);
  BEGIN
   IF booTodosCondiciones THEN
    select zreg.cod_regi into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           zon_regio       zreg,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (zreg.oid_regi = zzon.zorg_oid_regi)
            and (pa.cod_pais = vchPais )
            and zadm.oid_terr_admi = oidTerriAdmin;
    ELSE
     select zreg.cod_regi into ls_valor
      FROM zon_terri_admin zadm,
           zon_secci       zsec,
           zon_zona        zzon,
           zon_regio       zreg,
           seg_pais        pa
     where   (zsec.oid_secc = zadm.zscc_oid_secc)
            and (zzon.oid_zona = zsec.zzon_oid_zona)
            and (pa.oid_pais = zadm.pais_oid_pais)
            and (zreg.oid_regi = zzon.zorg_oid_regi)
            and (pa.cod_pais = vchPais )
            and zsec.ind_acti =1
            and zzon.ind_acti =1
            and zadm.oid_terr_admi = oidTerriAdmin;
    END IF;
   return ls_valor;
     EXCEPTION
    WHEN no_data_found THEN
          RETURN '';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_COD_REGI_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_COD_REGI_BYZON_TERRI;
  /***************************/
  /* deveueve DESCRIPCION         */
  /*******************************/

  FUNCTION GEN_FN_DESC_GENER( psTablaOid IN NUMBER, psDescripcion IN VARCHAR2)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select GEN.VAL_I18N into ls_valor
            from GEN_I18N_SICC_COMUN GEN,
              SEG_IDIOM IDIO
           where GEN.VAL_OID = psTablaOid  AND
              GEN.IDIO_OID_IDIO = IDIO.OID_IDIO  AND
              GEN.ATTR_ENTI = psDescripcion AND
              IDIO.COD_ISO_IDIO = 'es'    ;


   return ls_valor;
  END GEN_FN_DESC_GENER;

  /*****************************************/
  /*            GEN_FN_DES_REG                              */
  /*******************************************/
  FUNCTION GEN_FN_DES_REG( nCliente IN NUMBER,
                                          vchPais      IN varchar2 )
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select zreg.des_regi into ls_valor
            from mae_clien cl,
                 mae_clien_unida_admin cladm,
                 zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa,
                 zon_sub_geren_venta  zsgv
           where (cl.oid_clie = cladm.clie_oid_clie)
                  and (zadm.oid_terr_admi =cladm.ztad_oid_terr_admi)
                  and (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (zreg.oid_regi = zzon.zorg_oid_regi)
                  and (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
                  and (pa.oid_pais = cl.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and cladm.ind_acti = 1
                  and zreg.ind_acti =1
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and cl.oid_clie = nCliente;
   return ls_valor;
  END GEN_FN_DES_REG;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION GEN_FN_DESC_PROD( psProductoOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
/*    select PROD.DES_CORT into ls_valor
            from MAE_PRODU PROD
           where PROD.OID_PROD  = psProductoOid ;
*/
         select GEN.VAL_I18N into ls_valor
          from GEN_I18N_SICC_PAIS GEN,
            SEG_IDIOM IDIO
         where GEN.VAL_OID = psProductoOid AND
            GEN.IDIO_OID_IDIO = IDIO.OID_IDIO  AND
            GEN.ATTR_ENTI = 'MAE_PRODU' AND
            IDIO.COD_ISO_IDIO = 'es'  ;

   return ls_valor;

  END GEN_FN_DESC_PROD;
  /**************************************************************************
Descripcion        : Devuelve la descripcion de la zona dado un OID de ZON_TERRI_AMIN
Fecha Creacion     : 12/06/2007
Autor              : Marco Agurto
***************************************************************************/
  FUNCTION GEN_FN_DESC_ZONA_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN VARCHAR2
  IS
   ls_valor VARCHAR2(40);
  BEGIN
  IF booTodosCondiciones THEN
    select zzon.des_zona into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 seg_pais        pa
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zadm.oid_terr_admi = oidTerriAdmin;
   ELSE
     select zzon.des_zona into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 seg_pais        pa
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and zadm.oid_terr_admi = oidTerriAdmin;
   END IF;
   return ls_valor;
     EXCEPTION
     WHEN no_data_found THEN
          RETURN ' ';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_DESC_ZONA_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_DESC_ZONA_BYZON_TERRI;
    /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 29/08/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_ZONA_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
  RETURN VARCHAR2
  IS
  BEGIN
   IF vchCondicion= 'A' THEN
      RETURN GEN_FN_DESC_ZONA_BYZON_TERRI(oidTerriAdmin, vchPais,FALSE);
   ELSE
      RETURN GEN_FN_DESC_ZONA_BYZON_TERRI(oidTerriAdmin, vchPais,TRUE);
   END IF;
    EXCEPTION
     WHEN no_data_found THEN
          RETURN ' ';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_DESC_ZONA_TERRI_INAC: '||ls_sqlerrm);
  END GEN_FN_DESC_ZONA_TERRI_INAC;
   /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 18/06/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_REGIO_BYZON_TERRI( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,booTodosCondiciones BOOLEAN := FALSE)
  RETURN VARCHAR2
  IS
   ls_valor VARCHAR2(40);
  BEGIN
    IF booTodosCondiciones THEN
          select zreg.des_regi into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  AND zreg.oid_regi = zzon.zorg_oid_regi
                  and zadm.oid_terr_admi = oidTerriAdmin;
   ELSE
          select zreg.des_regi into ls_valor
            FROM zon_terri_admin zadm,
                 zon_secci       zsec,
                 zon_zona        zzon,
                 zon_regio       zreg,
                 seg_pais        pa
           where   (zsec.oid_secc = zadm.zscc_oid_secc)
                  and (zzon.oid_zona = zsec.zzon_oid_zona)
                  and (pa.oid_pais = zadm.pais_oid_pais)
                  and (pa.cod_pais = vchPais )
                  AND zreg.oid_regi = zzon.zorg_oid_regi
                  and zsec.ind_acti =1
                  and zzon.ind_acti =1
                  and zadm.oid_terr_admi = oidTerriAdmin;
   END IF;
   return ls_valor;
     EXCEPTION
     WHEN no_data_found THEN
          RETURN ' ';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_DESC_REGIO_BYZON_TERRI: '||ls_sqlerrm);
  END GEN_FN_DESC_REGIO_BYZON_TERRI;
  /**************************************************************************
  Descripcion        : Devuelve la descripcion de la Region dado un OID de ZON_TERRI_AMIN
  Fecha Creacion     : 29/08/2007
  Autor              : Marco Agurto
  ***************************************************************************/
  FUNCTION GEN_FN_DESC_REGIO_TERRI_INAC( oidTerriAdmin IN NUMBER,vchPais   IN varchar2 ,vchCondicion VARCHAR2)
  RETURN VARCHAR2
  IS
  BEGIN
   IF vchCondicion= 'A' THEN
      RETURN GEN_FN_DESC_REGIO_BYZON_TERRI(oidTerriAdmin, vchPais,FALSE);
   ELSE
      RETURN GEN_FN_DESC_REGIO_BYZON_TERRI(oidTerriAdmin, vchPais,TRUE);
   END IF;
    EXCEPTION
     WHEN no_data_found THEN
          RETURN ' ';
    WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_DESC_REGIO_TERRI_INAC: '||ls_sqlerrm);
  END GEN_FN_DESC_REGIO_TERRI_INAC;
  /******************************************************************/
  /*                   GEN_FN_COD_TIPO_OFERT                               */
  /******************************************************************/

  FUNCTION GEN_FN_COD_TIPO_OFERT( psTipOfertOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN

    select tof.cod_tipo_ofer into ls_valor
            from PRE_TIPO_OFERT TOF
           where TOF.OID_TIPO_OFER = psTipOfertOid ;

   return ls_valor;

  END GEN_FN_COD_TIPO_OFERT;

  /*****************************************/
  /*            GEN_FN_DEV_REC_ENTRE_MERCA                              */
  /*******************************************/
    FUNCTION GEN_FN_DEV_REC_ENTRE_MERCA(psOidMerca number)
      RETURN varchar2 IS
      ln_cod_merca varchar2(2):= ' ';
    BEGIN
      /* Obteniendo id de canal */
      SELECT a.cod_indi
        INTO ln_cod_merca
        FROM REC_INDIC_ENTRE_MERCA a
       WHERE a.oid_indi_entr_merc = psOidMerca ;

      RETURN ln_cod_merca;

    EXCEPTION
      WHEN no_data_found THEN
          RETURN ' ';

      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 1000);
        IF ln_sqlcode < 0 THEN
          RAISE_APPLICATION_ERROR(-20123,
                                  'ERROR GEN_FN_DEV_REC_ENTRE_MERCA: ' ||
                                  ls_sqlerrm);
        END IF;
    END GEN_FN_DEV_REC_ENTRE_MERCA;

  /*****************************************/
  /*            GEN_FN_DEV_REC_PRECI_PERDI                              */
  /*******************************************/
    FUNCTION GEN_FN_DEV_REC_PRECI_PERDI(psOidPrecioPer number)
      RETURN varchar2 IS
      ls_cod_precio varchar2(2):= ' ';
    BEGIN
      /* Obteniendo id de canal */
      SELECT a.cod_prec_perd
        INTO ls_cod_precio
        FROM REC_PRECI_PERDI a
       WHERE a.oid_prec_perd = psOidPrecioPer ;

      RETURN ls_cod_precio;

    EXCEPTION
      WHEN no_data_found THEN
          RETURN ' ';

      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 1000);
        IF ln_sqlcode < 0 THEN
          RAISE_APPLICATION_ERROR(-20123,
                                  'ERROR GEN_FN_DEV_REC_PRECI_PERDI: ' ||
                                  ls_sqlerrm);
        END IF;

    END GEN_FN_DEV_REC_PRECI_PERDI;

  /*****************************************/
  /*            GEN_FN_MARCA_PROD                               */
  /*******************************************/

  FUNCTION GEN_FN_MARCA_PROD( psProductoOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select MARKA.COD_MARC_PROD from SEG_MARCA_PRODU MARKA
                  where MARKA.OID_MARC_PROD = PROD.MAPR_OID_MARC_PROD ) into ls_valor
            from MAE_PRODU PROD
           where PROD.OID_PROD  = psProductoOid ;

   return ls_valor;

  END GEN_FN_MARCA_PROD;

  /*****************************************/
  /*            GEN_FN_NEGO_PROD                              */
  /*******************************************/

  FUNCTION GEN_FN_NEGO_PROD( psProductoOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select NEG.COD_NEGO from MAE_NEGOC NEG
                  where NEG.OID_NEGO = PROD.NEGO_OID_NEGO ) into ls_valor
            from MAE_PRODU PROD
           where PROD.OID_PROD  = psProductoOid ;

   return ls_valor;

  END GEN_FN_NEGO_PROD;

  /******************************************************************/
  /*                   GEN_FN_NUMLIN_OFER                               */
  /******************************************************************/

  FUNCTION GEN_FN_NUMLIN_OFER( psMatrFactOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN

    select (select OFDET.NUM_LINE_OFER  from PRE_OFERT_DETAL OFDET
                   where  OFDET.OID_DETA_OFER = MAFAC.OFDE_OID_DETA_OFER ) into ls_valor
            from PRE_MATRI_FACTU MAFAC
           where MAFAC.OID_MATR_FACT = psMatrFactOid ;

   return ls_valor;

  END GEN_FN_NUMLIN_OFER;

  /******************************************************************/
  /*                   GEN_FN_NUM_OFER                              */
  /******************************************************************/

  FUNCTION GEN_FN_NUM_OFER( psMatrFactOid IN NUMBER)
  RETURN number
  IS
   ls_valor  number;
  BEGIN

    select ( select ofer.num_ofer
              from pre_ofert ofer
              where ofer.oid_ofer = ofdet.ofer_oid_ofer ) into ls_valor
            from PRE_MATRI_FACTU MAFAC , PRE_OFERT_DETAL OFDET
           where MAFAC.OID_MATR_FACT = psMatrFactOid and
                 OFDET.OID_DETA_OFER = MAFAC.OFDE_OID_DETA_OFER ;

   return ls_valor;

  END GEN_FN_NUM_OFER;

  /*****************************************/
  /*            GEN_FN_REC_COD_ALMAP                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_COD_ALMAP( pscodigointerfaz IN varchar2 ,  psOidAlmaPed IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
   ls_valpaal bas_param_inter.val_pain%TYPE;

  BEGIN

    select nvl(min(b.val_pain),'2002')
     into ls_valpaal
     from bas_param_inter b
    where b.inte_cod_inte = psCodigoInterfaz
      and b.nom_pain = 'enviaAlmaPedido'||psOidAlmaPed ;

    select almac.cod_alma
      into ls_valor
    from BEL_ALMAC ALMAC
    where almac.oid_alma = ls_valpaal;

   return ls_valor;

  END GEN_FN_REC_COD_ALMAP;

  /*****************************************/
  /*            GEN_FN_REC_COD_ALMAC                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_COD_ALMAC( psTipoOperOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select almac.cod_alma from REC_OPERA OP, BEL_ALMAC ALMAC
                   where OP.OID_OPER = TOP.Rope_Oid_Oper and
                         OP.ALMC_OID_ALMA = ALMAC.OID_ALMA) into ls_valor
            from REC_TIPOS_OPERA TOP
           where TOP.OID_TIPO_OPER  = psTipoOperOid ;

   return ls_valor;

  END GEN_FN_REC_COD_ALMAC;

  /*****************************************/
  /*            GEN_FN_REC_COD_OPER                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_COD_OPER( psTipoOperOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select op.cod_oper from REC_OPERA OP where OP.OID_OPER = TOP.Rope_Oid_Oper) into ls_valor
            from REC_TIPOS_OPERA TOP
           where TOP.OID_TIPO_OPER  = psTipoOperOid ;

   return ls_valor;

  END GEN_FN_REC_COD_OPER;


  /*****************************************/
  /*            GEN_FN_REC_COD_TIPO_ING                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_COD_TIPO_ING( psTipoIngOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select TIN.COD_TIPO_INGR into ls_valor
            from REC_TIPO_INGRE TIN
           where TIN.OID_TIPO_INGR  = psTipoIngOid ;

   return ls_valor;

  END GEN_FN_REC_COD_TIPO_ING;

  /*****************************************/
  /*            GEN_FN_REC_NUM_LOTE                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_NUM_LOTE( psLineaOperRecOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select MAX(LIN.NUM_LOTE) into ls_valor
            from INT_REC_LINEA_OPERA_RECLA LIN
           where LIN.OID_LINE_OPER_RECL  = psLineaOperRecOid ;

   return ls_valor;

  END GEN_FN_REC_NUM_LOTE;

  /*****************************************/
  /*            GEN_FN_REC_TIPO_MOVI_ALMAC                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_TIPO_MOVI_ALMAC( psTipoOperOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select TMALMAC.COD_TIPO_MOVI from REC_OPERA OP, BEL_TIPO_MOVIM_ALMAC TMALMAC
                   where OP.OID_OPER = TOP.Rope_Oid_Oper and
                         OP.TMAL_OID_TIPO_MOVI_ALMA = TMALMAC.OID_TIPO_MOVI_ALMA) into ls_valor
            from REC_TIPOS_OPERA TOP
           where TOP.OID_TIPO_OPER  = psTipoOperOid ;

   return ls_valor;

  END GEN_FN_REC_TIPO_MOVI_ALMAC;

  /*****************************************/
  /*            GEN_FN_REC_TIPO_OPER                              */
  /*******************************************/

  FUNCTION GEN_FN_REC_TIPO_OPER( psTipoOperOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select TOP.VAL_TIPO_OPER into ls_valor
            from REC_TIPOS_OPERA TOP
           where TOP.OID_TIPO_OPER  = psTipoOperOid ;

   return ls_valor;

  END GEN_FN_REC_TIPO_OPER;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION GEN_FN_TIPO_PERIO( psPeriOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN
    select (select TIPE.COD_TIPO_PERI from SEG_PERIO_CORPO PERCORP, SEG_TIPO_PERIO TIPE
                  where PERCORP.OID_PERI = PERCRA.PERI_OID_PERI and
                        TIPE.OID_TIPO_PERI = PERCORP.TIPE_OID_TIPO_PERI ) into ls_valor
            from CRA_PERIO PERCRA
           where PERCRA.OID_PERI  = psPeriOid ;

   return ls_valor;

  END GEN_FN_TIPO_PERIO;

  /*****************************************/
  /*            GEN_FN_TIPO_OFER                              */
  /*******************************************/

  FUNCTION GEN_FN_TIPO_OFER( psvalpain IN varchar2,psCodTipoOfer in varchar2 ,psCopaoidparagene in NUMBER )
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(100);
  BEGIN

    if ( psvalpain = 'S' ) then
       if psCodTipoOfer is null then
          select max(ito.COD_TIPO_OFER_CONC)
          into ls_valor
          from inc_concu_param_gener ic, INC_TIPO_OFERT_CONCU ito
          where ic.tioc_oid_tipo_ofer_conc = ito.oid_tipo_ofer_conc
          and ic.oid_para_gral = psCopaoidparagene;
       else
          ls_valor := psCodTipoOfer;
       end if;
    else
       ls_valor := ' ';
    end if;

   return ls_valor;

  END GEN_FN_TIPO_OFER;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar SAM 7
                       Cabecera
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva

****************************************************************************
Proyecto Optimizacion  c1

Descripcion       :   Genera interfaz de envio de productos.

Fecha Modicicacion: 05/05/2011  14:00
Autor             : Jorge Angulo   JANGULO
 ***************************************************************************/
PROCEDURE int_pr_rec_envia_prod
(
  pscodigopais      VARCHAR2,
  pscodigosistema   VARCHAR2,
  pscodigointerfaz  VARCHAR2,
  psnombrearchivo   VARCHAR2,
  psfecha           VARCHAR2,
  pstipomovim       VARCHAR2,
  psnumerolote      VARCHAR2,
  psnumerosecuencia VARCHAR2
   )

IS
   CURSOR c_interfaz IS
    SELECT num_lote,
           cod_pais,
           cod_cana,
           cod_acce,
           cod_suba,
           tip_peri,
           cod_peri,
           fec_envi,
           tip_ofer,
           tip_mova,
           cod_nego,
           cod_marc,
           ind_ist,
           cod_prod,
           SUM(uni_devo),
           pre_neto,
           pre_cont,
           alm_orig,
           alm_dest,
           num_docu,
           cod_oper_homo,
           tip_oper
      FROM rec_histo_envio_produ_recla
     WHERE num_lote = psnumerolote
     GROUP BY num_lote,
              cod_pais,
              cod_cana,
              cod_acce,
              cod_suba,
              tip_peri,
              cod_peri,
              fec_envi,
              tip_ofer,
              tip_mova,
              cod_nego,
              cod_marc,
              ind_ist,
              cod_prod,
              pre_neto,
              pre_cont,
              alm_orig,
              alm_dest,
              num_docu,
              cod_oper_homo,
              tip_oper;

  TYPE interfazcab IS RECORD(
    numlote             VARCHAR2(20),
    codigopais          VARCHAR2(3),
    codigocanal         VARCHAR2(3),
    codigoacceso        VARCHAR2(3),
    codigosubacceso     VARCHAR2(3),
    tipoperiodo         VARCHAR2(2),
    codigoperiodo       VARCHAR2(6),
    fechaenvio          VARCHAR2(8),
    tipooferta          VARCHAR2(4),
    tipomovalmac        VARCHAR2(2),
    codigonegocio       VARCHAR2(9),
    codigomarca         VARCHAR2(3),
    indicadorist        VARCHAR2(1),
    codigosap           VARCHAR2(18),
    unidadadevolverrec  NUMBER(12, 2),
    precionetounitloc   VARCHAR2(12),
    preciocontabunitloc VARCHAR2(12),
    almacorig           VARCHAR2(3),
    almacdest           VARCHAR2(3),
    numdoc              VARCHAR2(12),
    codigooperahomol    VARCHAR2(2),
    tipooperacion       VARCHAR2(2));

  TYPE interfazcabtab IS TABLE OF interfazcab;
  interfazrecord interfazcabtab;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  lsvalpain bas_param_inter.val_pain%TYPE;
  lsvalpaal bas_param_inter.val_pain%TYPE;
  lsparametro bas_param_pais.val_para%TYPE;

--c1  w_filas    NUMBER := 1000;
  w_filas    NUMBER := 10000;  --c1
  v_handle   utl_file.file_type;

  lslinea VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);

  vnnumerosecuencia NUMBER;

BEGIN

--- Nueva variable para obtener parametro de activacion
    SELECT MIN(val_para)
      INTO lsparametro
      FROM bas_param_pais
     WHERE cod_para = '018'
       AND cod_sist = pscodigosistema
       AND cod_pais = pscodigopais
       AND ind_acti = '1';

   --- Nueva variable para enviar el tipo de oferta a la sam7x
    select min(b.val_pain)
     into lsvalpain
     from bas_param_inter b
    where b.inte_cod_inte = psCodigoInterfaz
      and b.nom_pain = 'enviaTipoOferta';

   --- Nueva variable para enviar el almacen segun el pedido
    select nvl(min(b.val_pain),'N')
     into lsvalpaal
     from bas_param_inter b
    where b.inte_cod_inte = psCodigoInterfaz
      and b.nom_pain = 'enviaAlmaPedido';

  vnnumerosecuencia := to_number(psnumerosecuencia);


  INSERT INTO rec_histo_envio_produ_recla
    (num_lote,
     cod_pais,
     cod_cana,
     cod_acce,
     cod_suba,
     tip_peri,
     cod_peri,
     fec_envi,
     tip_ofer,
     tip_mova,
     cod_nego,
     cod_marc,
     ind_ist,
     cod_prod,
     uni_devo,
     pre_neto,
     pre_cont,
     alm_orig,
     alm_dest,
     num_docu,
     cod_oper,
     tip_oper,
     oid_line_oper_recl,
     cod_oper_homo)
    SELECT numlote,
           codpais,
           codcanal,
           codacc,
           codsubac,
           tipoper,
           codper,
           fecenvio,
           tipofer,
           tipomov,
           negocio,
           marca,
           indicador,
           codsap,
           unidadevolvrec,
           precioneto,
           preciocont,
           almacorig,
           almacdest,
           numdoc,
           codoper,
           tipooper,
           oidlinearec,
           (SELECT cod_oper_homo FROM rec_homol_opera_sap WHERE cod_oper = codoper) codoperhomo
      FROM (SELECT psnumerolote AS numlote,
                   pscodigopais AS codpais,
                   int_pkg_recla.gen_fn_canal(pedcab.perd_oid_peri) AS codcanal,
                   int_pkg_recla.gen_fn_acceso(pedcab.perd_oid_peri) AS codacc,
                   int_pkg_recla.gen_fn_sub_acceso(pedcab.sbac_oid_sbac) AS codsubac,
                   int_pkg_recla.gen_fn_tipo_perio(pedcab.perd_oid_peri) AS tipoper,
                   ' ' AS codper,
                   psfecha AS fecenvio,
                   ---' ' AS tipofer,
                   ---decode(lsvalpain,null,' ',nvl(pto.cod_tipo_ofer,(select ito.COD_TIPO_OFER_CONC from inc_concu_param_gener ic, INC_TIPO_OFERT_CONCU ito where ic.tioc_oid_tipo_ofer_conc=ito.oid_tipo_ofer_conc and ic.oid_para_gral=pedcab.copa_oid_para_gene))) tipofer,
                   int_pkg_recla.gen_fn_tipo_ofer(lsvalpain,pto.cod_tipo_ofer,pedcab.copa_oid_para_gene) AS tipofer,
                   pstipomovim AS tipomov,
                   int_pkg_recla.gen_fn_nego_prod(linrec.prod_oid_prod) AS negocio,
                   int_pkg_recla.gen_fn_marca_prod(linrec.prod_oid_prod) AS marca,
                   'I' AS indicador,
--c1                   int_pkg_recla.gen_fn_codsap_prod(linrec.prod_oid_prod) AS codsap,
                   mp.cod_sap AS codsap, --c1
                   SUM(linrec.num_unid_recl) AS unidadevolvrec,
                   '0' AS precioneto,
                   '0' AS preciocont,
                   decode(lsvalpaal,'N',
                   int_pkg_recla.gen_fn_rec_cod_almac(operec.tiop_oid_tipo_oper) ,
                   int_pkg_recla.gen_fn_rec_cod_almap(psCodigoInterfaz,pedcab.almc_oid_alma) )
                   AS almacorig,
                   '' AS almacdest,
                   '' AS numdoc,
                   int_pkg_recla.gen_fn_rec_cod_oper(operec.tiop_oid_tipo_oper) AS codoper,
--c1                   int_pkg_recla.gen_fn_rec_tipo_oper(operec.tiop_oid_tipo_oper) AS tipooper,
                   top.val_tipo_oper AS tipooper,         --c1
                   linrec.oid_line_oper_recl AS oidlinearec
              FROM rec_opera_recla       operec,
                   rec_cabec_recla       cabrec,
                   rec_linea_opera_recla linrec,
                   ped_solic_cabec       pedcab,
                 ped_solic_posic pos,
                 pre_ofert_detal pod,
                 pre_tipo_ofert pto,
				   mae_produ             mp,                         --c1
				   rec_tipos_opera       top                         --c1
--c1             WHERE EXISTS (SELECT inter.num_line
              WHERE EXISTS (SELECT 1                                 --c1
                      FROM int_ret_linea_opera_recla inter
                     WHERE inter.num_line = linrec.oid_line_oper_recl
                           AND inter.num_secu_usua = vnnumerosecuencia)
                   AND linrec.opre_oid_oper_recl = operec.oid_oper_recl
                   AND operec.care_oid_cabe_recl = cabrec.oid_cabe_recl
                   AND cabrec.soca_oid_soli_cabe = pedcab.oid_soli_cabe
				   AND mp.oid_prod (+)           = linrec.prod_oid_prod  --c1
				   AND top.oid_tipo_oper(+)      = operec.tiop_oid_tipo_oper --c1
                        and linrec.sopo_oid_soli_posi = pos.oid_soli_posi(+)
                        AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
                        and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)

						and (lsparametro is null or ( (operec.tiop_oid_tipo_oper = (
                                                        select RTO.OID_TIPO_OPER
                                                        from int_rec_oper_homol roh , rec_opera ro,   rec_tipos_opera rto
                                                        where ROH.COD_OPER = RO.COD_OPER
                                                        and RO.OID_OPER = RTO.ROPE_OID_OPER
                                                        and RTO.OID_TIPO_OPER = operec.tiop_oid_tipo_oper
                                                        and ROH.COD_OPER_HOMOL = 'E'
                                                       AND linrec.sopo_oid_soli_posi is null
                                                        AND linrec.val_prec = 0
                                                        ) )
                           or
                                (operec.tiop_oid_tipo_oper not in (
                                                                        select RTO.OID_TIPO_OPER
                                                                        from int_rec_oper_homol roh , rec_opera ro,   rec_tipos_opera rto
                                                                        where ROH.COD_OPER = RO.COD_OPER
                                                                        and RO.OID_OPER = RTO.ROPE_OID_OPER
                                                                        and RTO.OID_TIPO_OPER = operec.tiop_oid_tipo_oper
                                                                        and ROH.COD_OPER_HOMOL = 'E'
                                                                        ) )
                          ))





             GROUP BY operec.tiop_oid_tipo_oper,
--c1                                                    int_pkg_recla.gen_fn_codsap_prod(linrec.prod_oid_prod),
                      mp.cod_sap,      --c1
                      int_pkg_recla.gen_fn_tipo_perio(pedcab.perd_oid_peri),
                      int_pkg_recla.gen_fn_sub_acceso(pedcab.sbac_oid_sbac),
                      int_pkg_recla.gen_fn_acceso(pedcab.perd_oid_peri),
                      int_pkg_recla.gen_fn_canal(pedcab.perd_oid_peri),
                      int_pkg_recla.gen_fn_marca_prod(linrec.prod_oid_prod),
                      int_pkg_recla.gen_fn_nego_prod(linrec.prod_oid_prod),
                      -----int_pkg_recla.gen_fn_rec_tipo_movi_almac(operec.tiop_oid_tipo_oper),
                      -----int_pkg_recla.gen_fn_rec_cod_almac(operec.tiop_oid_tipo_oper),
                      decode(lsvalpaal,'N',
                          int_pkg_recla.gen_fn_rec_cod_almac(operec.tiop_oid_tipo_oper) ,
                          int_pkg_recla.gen_fn_rec_cod_almap(psCodigoInterfaz,pedcab.almc_oid_alma) ),
                      int_pkg_recla.gen_fn_tipo_ofer(lsvalpain,pto.cod_tipo_ofer,pedcab.copa_oid_para_gene),
--c1                                                    int_pkg_recla.gen_fn_rec_tipo_oper(operec.tiop_oid_tipo_oper),
                      top.val_tipo_oper ,             --c1
                      linrec.oid_line_oper_recl);






    /* Procedimiento inicial para generar interfaz */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.count > 0 THEN
      FOR x IN interfazrecord.first .. interfazrecord.last
      LOOP
              lsLinea :=
                  interfazRecord(x).numLote             ||';'||
                  interfazRecord(x).codigoPais           ||';'||
                  interfazRecord(x).codigoCanal         ||';'||
                  interfazRecord(x).codigoAcceso         ||';'||
                  interfazRecord(x).codigoSubacceso      ||';'||
                  interfazRecord(x).tipoPeriodo         ||';'||
                  interfazRecord(x).codigoPeriodo       ||';'||
                  interfazRecord(x).fechaEnvio           ||';'||
                  interfazRecord(x).tipoOferta           ||';'||
                  interfazRecord(x).tipoMovAlmac         ||';'||
                  interfazRecord(x).codigoNegocio       ||';'||
                  interfazRecord(x).codigoMarca         ||';'||
                  interfazRecord(x).indicadorIST         ||';'||
                  interfazRecord(x).codigoSap            ||';'||
                  interfazRecord(x).unidadAdevolverRec    ||';'||
                  interfazRecord(x).precioNetoUnitLoc    ||';'||
                  interfazRecord(x).precioContabUnitLoc  ||';'||
                  interfazRecord(x).almacOrig           ||';'||
                  interfazRecord(x).almacDest           ||';'||
                  interfazRecord(x).numDoc              ||';'||
        interfazRecord(x).codigooperahomol      ||';'||
                  interfazRecord(x).tipoOperacion  ;

        utl_file.put_line(v_handle,
                          lslinea);

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_PROD: ' || ls_sqlerrm);

END int_pr_rec_envia_prod;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz INT_PR_REC_CABEC_RECLA
                       Detalle
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_REC_CABEC_RECLA
  (psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2
   )

IS
   CURSOR c_interfaz IS
    select
             psCodigoPais ,
             cabrec.num_aten ,
             cabrec.num_recl ,
             (select marca.cod_marc
                     from   ped_tipo_solic_pais ptsp, ped_tipo_solic pts, seg_marca marca
                     where    ptsp.oid_tipo_soli_pais = t.tspa_oid_tipo_soli_pais   and
                              ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli and
                              marca.oid_marc =  pts.marc_oid_marc ) marc ,
             INT_PKG_RECLA.GEN_FN_CANAL(t.perd_oid_peri) AS CANAL ,
             INT_PKG_RECLA.GEN_FN_ACCESO(t.perd_oid_peri) AS ACC ,
             INT_PKG_RECLA.GEN_FN_SUB_ACCESO(t.Sbac_Oid_Sbac) AS SUBAC ,
             t.VAL_NUME_SOLI DOCREF ,
             INT_PKG_RECLA.GEN_FN_CAMPANHA(t.perd_oid_peri) AS CAMPREF ,
             TRIM(to_char(cabrec.fec_docu_refe,'yyyymmdd')) as   "fecdocref"    ,
             INT_PKG_RECLA.GEN_FN_COD_CLIE(t.CLIE_OID_CLIE) Cliente ,
             (select MIN(tpc.cod_tipo_clie) from mae_clien_tipo_subti ctst, mae_tipo_clien tpc
                     where ctst.clie_oid_clie =  t.clie_oid_clie and
                           tpc.oid_tipo_clie = ctst.ticl_oid_tipo_clie ) TC ,
             (select stpc.cod_subt_clie from mae_clien_tipo_subti ctst, mae_subti_clien stpc
                     where ctst.clie_oid_clie =  t.clie_oid_clie and
                           ctst.ticl_oid_tipo_clie =
                           (select MIN(ts.ticl_oid_tipo_clie) from mae_clien_tipo_subti ts, mae_tipo_clien tpc
                                   where ts.clie_oid_clie = ctst.clie_oid_clie ) and
                           stpc.oid_subt_clie = ctst.sbti_oid_subt_clie  ) STC,
            (select sgv.cod_subg_vent from zon_regio region, zon_sub_geren_venta sgv
                      where region.oid_regi = INT_PKG_RECLA.GEN_FN_OID_REG(t.CLIE_OID_CLIE, 'PE')) SGV ,
              GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(INT_PKG_RECLA.GEN_FN_COD_CLIE(t.CLIE_OID_CLIE),'COD_REGI')  REG ,
              GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(INT_PKG_RECLA.GEN_FN_COD_CLIE(t.CLIE_OID_CLIE),'COD_SECC')  SEC ,
              GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(INT_PKG_RECLA.GEN_FN_COD_CLIE(t.CLIE_OID_CLIE),'COD_ZONA')  ZON ,
              GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(INT_PKG_RECLA.GEN_FN_COD_CLIE(t.CLIE_OID_CLIE),'COD_TERR')  TER ,
             ( select td.cod_tipo_docu from  fac_tipo_docum td where td.oid_tipo_docu = cabrec.tido_oid_tipo_docu) TD ,
             ( select er.cod_esta from rec_estad_recla er where er.oid_esta_recl = cabrec.esre_oid_esta_recl )  EST ,
             null          as   "motivbloq"     ,
             null          as   "motivrech"     ,
             null          as   "motivdesb"     ,
             nvl(cabrec.num_tota_envi,0)  as   "totalenvia"    ,
             nvl(cabrec.num_tota_devu,0)  as   "totaldevuelve" ,
             nvl(cabrec.imp_sald_paga,0)  as   "saldopagar"    ,
             ( select ti.cod_tipo_ingr from rec_tipo_ingre ti where ti.oid_tipo_ingr = cabrec.tiin_oid_tipo_ingr ) TING ,
             cabrec.cod_usua_ingr  AS   "USUINGRESO"    ,       --      '000'     AS   "USUINGRESO"    ,
             INT_PKG_RECLA.GEN_FN_CAMPANHA(cabrec.perd_oid_peri_recl) AS CAMPREC ,
             to_char(cabrec.fec_ingr,'yyyymmdd') as   "fecingreso"    ,
             ' '           AS   "NUMATENINTER"
      from ped_solic_cabec t ,
           rec_cabec_recla cabrec
      where  -- RCR - PER-SiCC-2007-0012
             --Se solicita que cada vez que se ejecute la interfase se generen los archivos RET-1.* y RET-2.* en blanco.
             (1=2) and
             exists  (select inter.num_aten from int_ret_linea_opera_recla inter
                                  where inter.num_aten =  cabrec.oid_cabe_recl ) and
             cabrec.soca_oid_soli_cabe = t.oid_soli_cabe  ;


   TYPE interfazCab IS RECORD
   (
      codigoPais   VARCHAR2(3),
      numAtencion   VARCHAR2(10),
      numReclamo   VARCHAR2(10),
      codigoMarca   VARCHAR2(2),
      codigoCanal   VARCHAR2(3),
      codigoAcceso   VARCHAR2(3),
      codigoSubacceso  VARCHAR2(3),
      numDocRef   VARCHAR2(100),
      perioDocRef   VARCHAR2(100),
      fecDocRef   VARCHAR2(100),
      codigoCliente  VARCHAR2(100),
      codigoTipoCliente  VARCHAR2(100),
      codSubTipoCliente  VARCHAR2(2),
      codigoSubger   VARCHAR2(2),
      codigoRegion   VARCHAR2(2),
      codigoSeccion   VARCHAR2(1),
      codigoZona  VARCHAR2(4),
      codigoTerritCliente  VARCHAR2(6),
      codigoTipoDoc   VARCHAR2(3) ,
      estadoRec   VARCHAR2(1),
      codMotivBloq   VARCHAR2(2),
      codMotivRech   VARCHAR2(2),
      codMotivDesb   VARCHAR2(2),
      totalEnvia   VARCHAR2(12),
      totalDevuelve   VARCHAR2(12),
      saldoPagar   VARCHAR2(12),
      codTipoIng   VARCHAR2(1),
      usuIngreso   VARCHAR2(20),
      periodRec   VARCHAR2(6),
      fecIngreso   VARCHAR2(8),
      numAtenInter   VARCHAR2(10)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);

  lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                    interfazRecord(x).codigoPais   ||';'||
                    interfazRecord(x).numAtencion   ||';'||
                    interfazRecord(x).numReclamo   ||';'||
                    interfazRecord(x).codigoMarca   ||';'||
                    interfazRecord(x).codigoCanal   ||';'||
                    interfazRecord(x).codigoAcceso   ||';'||
                    interfazRecord(x).codigoSubacceso  ||';'||
                    interfazRecord(x).numDocRef   ||';'||
                    interfazRecord(x).perioDocRef   ||';'||
                    interfazRecord(x).fecDocRef   ||';'||
                    interfazRecord(x).codigoCliente  ||';'||
                    interfazRecord(x).codigoTipoCliente  ||';'||
                    interfazRecord(x).codSubTipoCliente  ||';'||
                    interfazRecord(x).codigoSubger   ||';'||
                    interfazRecord(x).codigoRegion   ||';'||
                    interfazRecord(x).codigoSeccion  ||';'||
                    interfazRecord(x).codigoZona  ||';'||
                    interfazRecord(x).codigoTerritCliente  ||';'||
                    interfazRecord(x).codigoTipoDoc  ||';'||
                    interfazRecord(x).estadoRec   ||';'||
                    interfazRecord(x).codMotivBloq   ||';'||
                    interfazRecord(x).codMotivRech   ||';'||
                    interfazRecord(x).codMotivDesb   ||';'||
                    interfazRecord(x).totalEnvia   ||';'||
                    interfazRecord(x).totalDevuelve  ||';'||
                    interfazRecord(x).saldoPagar   ||';'||
                    interfazRecord(x).codTipoIng   ||';'||
                    interfazRecord(x).usuIngreso   ||';'||
                    interfazRecord(x).periodRec   ||';'||
                    interfazRecord(x).fecIngreso   ||';'||
                    interfazRecord(x).numAtenInter  ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_REC_CABEC_RECLA: '||ls_sqlerrm);

END INT_PR_REC_CABEC_RECLA;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar INT_PR_REC_OPERA_RECLA
                       del Cliente
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_REC_OPERA_RECLA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2
   )

IS
   CURSOR c_interfaz IS
    select   -- into ls_cantidad
             (select marca.cod_marc
                     from   ped_tipo_solic_pais ptsp, ped_tipo_solic pts, seg_marca marca
                     where    ptsp.oid_tipo_soli_pais = t.tspa_oid_tipo_soli_pais   and
                              ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli and
                              marca.oid_marc =  pts.marc_oid_marc ) marc ,
             psCodigoPais ,
             cabrec.num_aten ,
             opera.num_secu_oper ,
             INT_PKG_RECLA.GEN_FN_CAMPANHA(cabrec.perd_oid_peri_recl) AS CAMPREC ,
             INT_PKG_RECLA.GEN_FN_REC_TIPO_OPER(opera.tiop_oid_tipo_oper ) AS TIPOOPER,
             INT_PKG_RECLA.GEN_FN_REC_COD_OPER(opera.tiop_oid_tipo_oper) AS CODOPER,
             NULL        AS "MOTIVBLOQ"   ,
             NULL        AS "TIPOBLOQ"   ,
             NULL        AS "MOTIVRECH"    ,
             NULL        AS "MOTIVDESB"  ,
             INT_PKG_RECLA.GEN_FN_CANAL(t.perd_oid_peri) AS CANAL ,
             INT_PKG_RECLA.GEN_FN_ACCESO(t.perd_oid_peri) AS ACC ,
             INT_PKG_RECLA.GEN_FN_SUB_ACCESO(t.Sbac_Oid_Sbac) AS SUBAC ,
             t.VAL_NUME_SOLI DOCREF ,
             INT_PKG_RECLA.GEN_FN_CAMPANHA(t.perd_oid_peri) AS CAMPREF ,
             ( select eo.cod_esta_oper from rec_estad_opera eo where eo.oid_esta_oper = opera.esop_oid_esta_oper )  EST ,
             INT_PKG_RECLA.GEN_FN_DEV_REC_ENTRE_MERCA(opera.inem_oid_indi_entr_merc) AS "INDENTRMERC"  ,
             NULL        AS "RESPONPERD"   ,
             NULL        AS "FLGASUMEPERD" ,
             NULL        AS "CODIGOART"    ,
             NULL        AS "CLIENTESEG"   ,
             NULL        AS "MONTPERDIDA"  ,
             INT_PKG_RECLA.GEN_FN_DEV_REC_PRECI_PERDI(opera.pper_oid_prec_perd)  AS "PRECPERD"    ,
             NULL        AS "PORCPERDIDA"  ,
             NULL        AS "TIPOSOLICPERD",
             opera.IND_ATEN     AS "INDATENDIDO" ,
             TO_CHAR(opera.FEC_FACT ,'YYYYMMDD')   AS "FECFACT" ,
             ' '           AS   "NUMATENINTER"
      from ped_solic_cabec t ,
           rec_cabec_recla cabrec , rec_opera_recla opera
      where  -- RCR - PER-SiCC-2007-0012
             --Se solicita que cada vez que se ejecute la interfase se generen los archivos RET-1.* y RET-2.* en blanco.
             (1=2) and
             exists  (select inter.num_aten from int_ret_linea_opera_recla inter
                                  where inter.num_secu_oper =  opera.oid_oper_recl ) and
             opera.care_oid_cabe_recl = cabrec.oid_cabe_recl and
             cabrec.soca_oid_soli_cabe = t.oid_soli_cabe ;

   TYPE interfazCab IS RECORD
   (
        codigoMarca            VARCHAR2(3),
        codigoPais              VARCHAR2(3),
        numAtencion             VARCHAR2(10),
        numSecOper              VARCHAR2(3),
        periodRec               VARCHAR2(6),
        tipoOperacion           VARCHAR2(2),
        codigoOperacion         VARCHAR2(2),
        codMotivBloq            VARCHAR2(2),
        tipoBloq                VARCHAR2(1),
        codMotivRech            VARCHAR2(2),
        codMotivDesb            VARCHAR2(2),
        codigoCanal             VARCHAR2(3),
        codigoAcceso            VARCHAR2(3),
        codigoSubacceso         VARCHAR2(3),
        docRef                  VARCHAR2(10),
        codigoPeriodo           VARCHAR2(6),
        estadoOper              VARCHAR2(1),
        indEntrMerc             VARCHAR2(1),
        responPerd              VARCHAR2(15),
        flgAsumePerd            VARCHAR2(1),
        codigoArt               VARCHAR2(20),
        clientSeg               VARCHAR2(15),
        montPerdida             VARCHAR2(12),
        precPerd                VARCHAR2(1),
        porcPerdida             VARCHAR2(5),
        tipoSolicPerd           VARCHAR2(4),
        indAtendido             VARCHAR2(1),
        fecFact                 VARCHAR2(8),
        numAtenInter            VARCHAR2(10)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);

  lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

              lsLinea :=
                interfazRecord(x).codigoMarca     ||';'||
                interfazRecord(x).codigoPais            ||';'||
                interfazRecord(x).numAtencion           ||';'||
                interfazRecord(x).numSecOper            ||';'||
                interfazRecord(x).periodRec             ||';'||
                interfazRecord(x).tipoOperacion         ||';'||
                interfazRecord(x).codigoOperacion       ||';'||
                interfazRecord(x).codMotivBloq          ||';'||
                interfazRecord(x).tipoBloq              ||';'||
                interfazRecord(x).codMotivRech          ||';'||
                interfazRecord(x).codMotivDesb          ||';'||
                interfazRecord(x).codigoCanal           ||';'||
                interfazRecord(x).codigoAcceso          ||';'||
                interfazRecord(x).codigoSubacceso       ||';'||
                interfazRecord(x).docRef                ||';'||
                interfazRecord(x).codigoPeriodo         ||';'||
                interfazRecord(x).estadoOper            ||';'||
                interfazRecord(x).indEntrMerc           ||';'||
                interfazRecord(x).responPerd            ||';'||
                interfazRecord(x).flgAsumePerd          ||';'||
                interfazRecord(x).codigoArt             ||';'||
                interfazRecord(x).clientSeg             ||';'||
                interfazRecord(x).montPerdida           ||';'||
                interfazRecord(x).precPerd              ||';'||
                interfazRecord(x).porcPerdida           ||';'||
                interfazRecord(x).tipoSolicPerd         ||';'||
                interfazRecord(x).indAtendido           ||';'||
                interfazRecord(x).fecFact               ||';'||
                interfazRecord(x).numAtenInter          ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

   /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_REC_OPERA_RECLA: '||ls_sqlerrm);

END INT_PR_REC_OPERA_RECLA;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar INT_PR_REC_LINEA_RECLA
                       de Referencia
  Fecha Creacion     : 27/02/2007
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_REC_LINEA_RECLA
  (psCodigoPais        VARCHAR2,
   psCodigoSistema     VARCHAR2,
   psCodigoInterfaz    VARCHAR2,
   psNombreArchivo     VARCHAR2
   )

IS
   CURSOR c_interfaz IS
    select   -- into ls_cantidad
             psCodigoPais ,
             cabrec.num_aten ,
             opera.num_secu_oper ,
             linrec.num_line ,
             (select tm.cod_tipo_movi
                     from rec_tipo_movim tm
                     where tm.oid_tipo_movi = linrec.timo_oid_tipo_movi) "TIPOMOVIM" ,
             NULL         AS "MOTIVDESB",
              decode(INT_PKG_RECLA.GEN_FN_CAMPANHA(INT_PKG_RECLA.GEN_FN_DEV_PERI_MATR_FACT(linrec.mafa_oid_matr_fact)),
                                                 null,INT_PKG_RECLA.GEN_FN_CAMPANHA(t.perd_oid_peri),
                                                 INT_PKG_RECLA.GEN_FN_CAMPANHA(INT_PKG_RECLA.GEN_FN_DEV_PERI_MATR_FACT(linrec.mafa_oid_matr_fact))) AS CODPER	,
             INT_PKG_RECLA.GEN_FN_CANAL(t.perd_oid_peri) AS CANAL ,
             (select marca.cod_marc
                     from   ped_tipo_solic_pais ptsp, ped_tipo_solic pts, seg_marca marca
                     where    ptsp.oid_tipo_soli_pais = t.tspa_oid_tipo_soli_pais   and
                              ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli and
                              marca.oid_marc =  pts.marc_oid_marc ) marc ,
             INT_PKG_RECLA.GEN_FN_COD_TIPO_OFERT(LINREC.TOFE_OID_TIPO_OFER) AS TIPOFER,
             INT_PKG_RECLA.GEN_FN_CODSAP_PROD(LINREC.PROD_OID_PROD) AS CODSAP ,
            NVL(linrec.NUM_UNID_RECL,0)  AS "UNIDADRECLAM",
            NVL(linrec.VAL_PREC,0)  AS "PRECIO"  ,
            NVL(linrec.IMP_ABON,0)  AS "IMPORTEABONO" ,
            NVL(linrec.IMP_CARG,0)  AS "IMPORTECARGO" ,
            NVL(linrec.NUM_UNID_RECL,0)  AS "UNIDADDEVUELT",
            linrec.IND_ESTA     AS "INDESTADO"   ,
             (select pts.cod_tipo_soli
                     from   ped_tipo_solic_pais ptsp, ped_tipo_solic pts
                     where    ptsp.oid_tipo_soli_pais = linrec.tspa_oid_tipo_soli_pais   and
                              ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli ) "TIPOSOLIC",
            (select tp.cod_tipo_posi
                    from ped_tipo_posic tp
                    where tp.oid_tipo_posi = linrec.tpos_oid_tipo_posi) "TIPOPOSIC",
            NULL      AS "MONTOPERDIDA" ,
            NULL      AS "PRECPERDIDA" ,
            linrec.IND_ATEN    AS "INDATENDIDO" ,
            (select md.cod_moti_devo
                    from rec_motiv_devol md
                    where md.oid_moti_devo = linrec.modv_oid_moti_devo ) "MOTIVDEVOL"  ,
            INT_PKG_RECLA.GEN_FN_COD_ESTR(linrec.Mafa_Oid_Matr_Fact) AS "DTEESTRATEG" ,
            INT_PKG_RECLA.GEN_FN_NUM_OFER(linrec.Mafa_Oid_Matr_Fact) AS "DTENUMOFERTA" ,
            INT_PKG_RECLA.GEN_FN_NUMLIN_OFER(linrec.Mafa_Oid_Matr_Fact) AS "DTELINEAOFERTA" ,
           ' '    AS   "NUMATENINTER"
      from ped_solic_cabec t ,
           rec_cabec_recla cabrec , rec_opera_recla opera, rec_linea_opera_recla linrec
      where  exists  (select inter.num_line from int_ret_linea_opera_recla inter
                                  where inter.num_line =  linrec.oid_line_oper_recl ) and
             linrec.opre_oid_oper_recl = opera.oid_oper_recl      and
             opera.care_oid_cabe_recl = cabrec.oid_cabe_recl and
             cabrec.soca_oid_soli_cabe = t.oid_soli_cabe ;


   TYPE interfazCab IS RECORD
   (
      codigoPais      VARCHAR2(3),
      numAtencion             VARCHAR2(10),
      numSecOper              VARCHAR2(3),
      numLinea                VARCHAR2(4),
      tipoMovim               VARCHAR2(1),
      codMotivDesb            VARCHAR2(2),
      codigoPeriodo           VARCHAR2(6),
      codigoCanal             VARCHAR2(2),
      codigoMarca             VARCHAR2(3),
      tipoOferta              VARCHAR2(4),
      codigoProducto          VARCHAR2(20),
      unidadReclam            VARCHAR2(4),
      precio                  VARCHAR2(12),
      importeAbono            VARCHAR2(12),
      importeCargo            VARCHAR2(12),
      unidadDevuelt           VARCHAR2(4),
      indEstado               VARCHAR2(1),
      tipoSolic               VARCHAR2(4),
      tipoPosic               VARCHAR2(2),
      montoPerdida            VARCHAR2(12),
      precioPerdida           VARCHAR2(1),
      atendido                VARCHAR2(1),
      codMotivDevol           VARCHAR2(2),
      dteEstrateg             VARCHAR2(3),
      dteNumOferta            VARCHAR2(6),
      dteLineaOferta          VARCHAR2(3),
      numAtenInter            VARCHAR2(10)
   );

   TYPE interfazCabTab  IS TABLE OF interfazCab ;
   interfazRecord interfazCabTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);

  lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
        interfazRecord(x).codigoPais     ||';'||
        interfazRecord(x).numAtencion           ||';'||
        interfazRecord(x).numSecOper            ||';'||
        interfazRecord(x).numLinea              ||';'||
        interfazRecord(x).tipoMovim             ||';'||
        interfazRecord(x).codMotivDesb          ||';'||
        interfazRecord(x).codigoPeriodo         ||';'||
        interfazRecord(x).codigoCanal           ||';'||
        interfazRecord(x).codigoMarca           ||';'||
        interfazRecord(x).tipoOferta            ||';'||
        interfazRecord(x).codigoProducto        ||';'||
        interfazRecord(x).unidadReclam          ||';'||
        interfazRecord(x).precio                ||';'||
        interfazRecord(x).importeAbono          ||';'||
        interfazRecord(x).importeCargo          ||';'||
        interfazRecord(x).unidadDevuelt         ||';'||
        interfazRecord(x).indEstado             ||';'||
        interfazRecord(x).tipoSolic             ||';'||
        interfazRecord(x).tipoPosic             ||';'||
        interfazRecord(x).montoPerdida          ||';'||
        interfazRecord(x).precioPerdida         ||';'||
        interfazRecord(x).atendido              ||';'||
        interfazRecord(x).codMotivDevol         ||';'||
        interfazRecord(x).dteEstrateg           ||';'||
        interfazRecord(x).dteNumOferta          ||';'||
        interfazRecord(x).dteLineaOferta        ||';'||
        interfazRecord(x).numAtenInter         ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_REC_LINEA_RECLA: '||ls_sqlerrm);

END INT_PR_REC_LINEA_RECLA;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_PERI_MATR_FACT
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO existe CAMPANHA 1: Existe CAMPANHA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION GEN_FN_DEV_PERI_MATR_FACT(pnOidMatrizFact  number)
    RETURN NUMBER IS
    ln_id_periodo number;
  BEGIN

     SELECT (  SELECT ca.perd_oid_peri
            FROM Pre_Matri_Factu_Cabec ca
           WHERE ca.oid_cabe = ma.mfca_oid_cabe  )
      INTO ln_id_periodo
      FROM Pre_Matri_Factu ma
     WHERE ma.oid_matr_fact = pnOidMatrizFact ;

    RETURN ln_id_periodo;

    EXCEPTION
     WHEN OTHERS THEN
      return 0;


  END GEN_FN_DEV_PERI_MATR_FACT;

  /**************************************************************************
  Descripcion        : Devuelve Id de Subacceso
  Fecha Creacion     : 05/01/2007
  Parametros Entrada :
             psCodSociedad : Codigo de Subacceso
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco  SIlva
  ***************************************************************************/
  FUNCTION GEN_FN_DEV_ID_SUBAC(psCodSubac          VARCHAR2)
    RETURN NUMBER IS
    ln_id_subac seg_subac.oid_sbac%TYPE;
  BEGIN
    /* Obteniendo id de subacceso */
    SELECT a.oid_sbac
      INTO ln_id_subac
      FROM seg_subac a
     WHERE a.cod_sbac = psCodSubac ;

    RETURN ln_id_subac;

  EXCEPTION
    WHEN no_data_found THEN
        RETURN - 1;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR GEN_FN_DEV_ID_SUBAC: ' ||
                                ls_sqlerrm);
      END IF;
  END GEN_FN_DEV_ID_SUBAC;

/**************************************************************************
  Descripcion        : Genera atenciones inteligentes
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_gener_aten_inte 
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
) IS
  CURSOR c_boleta_recojo(vnoidperiodo NUMBER, vngrupofacturacion NUMBER, vnoidestreclafacturado NUMBER, vnoidestreclaenviado NUMBER, vnoidestoperafacturado NUMBER, vnoidestoperaenviado NUMBER, vnoidtipocierra NUMBER, vnoidtiposolipaisoc NUMBER,
                         vnoidtiposolipaisex NUMBER, vnoidperiodo1 NUMBER, vnoidperiodo2 NUMBER
  ) IS
    SELECT DISTINCT rc.pais_oid_pais, -- OID DEL PAIS
                    ---rc.ztad_oid_terr_admi, -- OID DE LA UNIDAD ADMINISTRATIVA
                    UA.ZTAD_OID_TERR_ADMI, -- OID DE LA UNIDAD ADMINISTRATIVA
                    rc.esre_oid_esta_recl, -- OID DEL ESTADO DEL RECLAMO
                    rc.oid_cabe_recl, -- OID DE LA CABECERA DE RECLAMO
                    rc.num_aten, -- NUMERO DE ATENCION DE RECLMOA
                    rc.num_recl, -- NUMERO DE RECLMOA
                    rc.clie_oid_clie, -- OID DEL CLIENTE
                    rc.soca_oid_soli_cabe, --OID DEL DOCUMENTO DE REFERENCIA
                    rc.perd_oid_peri_docu_refe, -- OID DEL PERIODO DEL DOCUMENTO DE REFERENCIA
                    rc.perd_oid_peri_recl, -- OID DEL PERIODO DE PROCESO DE RECLAMO
                    ro.oid_oper_recl, -- OID DE LA OPERACION RECLAMO
                    ro.num_secu_oper, -- NUMERO DE SECUENCIA DE OPERACION
                    op.cod_oper, -- CODIGO DE OPERACION
                    top.val_tipo_oper, -- TIPO OPERACION
                    rl.mafa_oid_matr_fact, -- OID DE MATRIZ DE FACTURACION
                    rl.tofe_oid_tipo_ofer, -- OID DE TIPO OFERTA
                    rl.copa_oid_para_gral, -- OID DE CONCURSO
                    rl.lopa_oid_lote_prem_arti, -- OID DE LOTE PREMIO
                    rl.panp_oid_para_nive_prem, -- OID DE NIVEL DE PREMIO
                    rl.prod_oid_prod, -- OID DE PRODUCTO
                    rl.val_prec, -- PRECIO FACTURA DE PRODUCTO
                    rl.val_prec_cont, -- PRECIO CONTABLE
                    rl.num_unid_recl, -- NUMERO DE UNIDADES RECLAMADAS
                    trunc(SYSDATE) fec_inic, -- FECHA DE INICIO DE ACTIVIDAD DE FACTURACION O REFACTURACION
                    zr.cod_regi, -- CODIGO DE REGION
                    zz.cod_zona, -- CODIGO DE ZONA
                    zs.cod_secc, -- CODIGO DE SECCION
                    zt.cod_terr, -- CODIGO DE TERRITORIO
                    mp.cod_sap, -- CODIGO SAP
                    mp.codi_anti, -- CODIGO ANTIGUO
                    NULL oid_soli_desp, -- OID DE SOLICITUD DESPACHO
                    NULL val_num_desp, -- NUMERO DE BOLETA DE DESPACHO
                    rh.cod_oper_homl, -- OPERACION HOMOLOGADA
                    mh.des_oper_homl, -- DESCRIPCION OPERACION HOMOLOGADA
                    sp.cod_peri, -- PERIODO DE PROCESO
                    rl.oid_line_oper_recl, -- LINEA DE OPERACION DE RECLAMOS
                    (select rmd.cod_moti_devo from rec_motiv_devol rmd where rmd.oid_moti_devo = rl.modv_oid_moti_devo), ---codigo motivo
                    zz.oid_zona,
                    zr.oid_regi,
                    '0' ind_premio,
                    '0' ind_gene_br_ok,
                    0 cuenta_br,
                    0 cuenta_aten,
                    0 cuenta_aten_pre
      FROM rec_cabec_recla           rc,
           rec_opera_recla           ro,
           rec_linea_opera_recla     rl,
           rec_opera                 op,
           rec_tipos_opera           top,
           mae_clien_unida_admin     ua,
           zon_regio                 zr,
           zon_zona                  zz,
           zon_secci                 zs,
           zon_terri_admin           zta,
           zon_terri                 zt,
           mae_produ                 mp,
           int_rec_opera_homol_borec rh,
           int_mae_opera_homol_borec mh,
           cra_perio                 cra,
           seg_perio_corpo           sp,
           int_rec_cierr_borec cie
     WHERE rc.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rl.opre_oid_oper_recl
       AND rc.esre_oid_esta_recl IN
           (vnoidestreclafacturado, vnoidestreclaenviado)
       AND ro.esop_oid_esta_oper IN
           (vnoidestoperafacturado, vnoidestoperaenviado)
       AND ro.tiop_oid_tipo_oper = top.oid_tipo_oper
       AND top.rope_oid_oper = op.oid_oper
       AND rl.timo_oid_tipo_movi = 2
       and RC.CLIE_OID_CLIE = UA.CLIE_OID_CLIE
       and UA.IND_ACTI = 1
       AND UA.ZTAD_OID_TERR_ADMI = zta.oid_terr_admi
       ---AND rc.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = zs.oid_secc
       AND rl.num_unid_recl > 0
       AND zs.ind_acti = 1
       AND zs.zzon_oid_zona = zz.oid_zona
       AND zz.ind_acti = 1
       AND zz.zorg_oid_regi = zr.oid_regi
       AND zr.ind_acti = 1
       AND zta.terr_oid_terr = zt.oid_terr
       ---AND rc.perd_oid_peri_recl = vnoidperiodo
       AND rc.perd_oid_peri_recl in ( vnoidperiodo, vnoidperiodo1, vnoidperiodo2)
       AND op.ind_anul <> 1
       AND (op.ind_falt_merc = 0 OR
           (op.ind_falt_merc = 1 AND op.ind_devu_fisi_fact = 0 AND
           rl.sopo_oid_soli_posi IS NULL))
       AND mp.oid_prod = rl.prod_oid_prod
       AND rh.cod_pais = pscodigopais
       AND mh.cod_pais = pscodigopais
       AND rh.cod_oper_rec = op.cod_oper
       AND rh.cod_oper_homl = mh.cod_oper_homl
       and rh.ind_gene_bore = '1'    ---- Solo los operaciones que generan BR
       AND cra.oid_peri = rc.perd_oid_peri_recl
       AND cra.peri_oid_peri = sp.oid_peri
       AND NOT EXISTS
     (SELECT rc.oid_cabe_recl
              FROM int_rec_linea_borec lb
             WHERE lb.care_oid_cabe_recl = rc.oid_cabe_recl)
       AND op.cod_oper NOT IN
           (SELECT cod_oper
              FROM int_rec_opera_exclu
             WHERE cod_pais = pscodigopais)
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = zr.cod_regi
         AND cie.cod_zona = zz.cod_zona   ---- SQA agregado
         AND cie.cod_pais = pscodigopais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
     ORDER BY oid_regi,
              oid_zona,
              clie_oid_clie;

  TYPE boletarecojorec IS RECORD(
    pais_oid_pais           rec_cabec_recla.pais_oid_pais %TYPE,
    ztad_oid_terr_admi      rec_cabec_recla.ztad_oid_terr_admi %TYPE,
    esre_oid_esta_recl      rec_cabec_recla.esre_oid_esta_recl %TYPE,
    oid_cabe_recl           rec_cabec_recla.oid_cabe_recl %TYPE,
    num_aten                rec_cabec_recla.num_aten %TYPE,
    num_recl                rec_cabec_recla.num_recl %TYPE,
    clie_oid_clie           rec_cabec_recla.clie_oid_clie %TYPE,
    soca_oid_soli_cabe      rec_cabec_recla.soca_oid_soli_cabe %TYPE,
    perd_oid_peri_docu_refe rec_cabec_recla.perd_oid_peri_docu_refe %TYPE,
    perd_oid_peri_recl      rec_cabec_recla.perd_oid_peri_recl %TYPE,
    oid_oper_recl           rec_opera_recla.oid_oper_recl %TYPE,
    num_secu_oper           rec_opera_recla.num_secu_oper %TYPE,
    cod_oper                rec_opera.cod_oper %TYPE,
    val_tipo_oper           rec_tipos_opera.val_tipo_oper %TYPE,
    mafa_oid_matr_fact      rec_linea_opera_recla.mafa_oid_matr_fact %TYPE,
    tofe_oid_tipo_ofer      rec_linea_opera_recla.tofe_oid_tipo_ofer %TYPE,
    copa_oid_para_gral      rec_linea_opera_recla.copa_oid_para_gral %TYPE,
    lopa_oid_lote_prem_arti rec_linea_opera_recla.lopa_oid_lote_prem_arti %TYPE,
    panp_oid_para_nive_prem rec_linea_opera_recla.panp_oid_para_nive_prem %TYPE,
    prod_oid_prod           rec_linea_opera_recla.prod_oid_prod %TYPE,
    val_prec                rec_linea_opera_recla.val_prec %TYPE,
    val_prec_cont           rec_linea_opera_recla.val_prec_cont %TYPE,
    num_unid_recl           rec_linea_opera_recla.num_unid_recl %TYPE,
    fec_inic                cra_crono.fec_inic %TYPE,
    cod_regi                zon_regio.cod_regi %TYPE,
    cod_zona                zon_zona.cod_zona %TYPE,
    cod_secc                zon_secci.cod_secc %TYPE,
    cod_terr                zon_terri.cod_terr %TYPE,
    cod_sap                 mae_produ.cod_sap %TYPE,
    cod_anti                mae_produ.codi_anti %TYPE,
    oid_soli_desp           ped_solic_cabec.oid_soli_cabe %TYPE,
    val_num_desp            ped_solic_cabec.val_nume_soli %TYPE,
    cod_oper_homl           int_rec_opera_homol_borec.cod_oper_homl %TYPE,
    des_oper_homol          int_mae_oper_homol.des_oper_homol %TYPE,
    cod_peri                seg_perio_corpo.cod_peri %TYPE,
    oid_line_oper_recl      rec_linea_opera_recla.oid_line_oper_recl %TYPE,
    cod_moti_devo           rec_motiv_devol.cod_moti_devo%TYPE,
    oid_zon                 zon_zona.oid_zona%TYPE,
    oid_regi                zon_regio.oid_regi%TYPE,
    ind_premio              int_rec_opera_homol_borec.ind_gene_bore%TYPE,
    ind_gene_br_ok          int_rec_opera_homol_borec.ind_gene_bore%TYPE,
    cuenta_br               rec_linea_opera_recla.num_unid_recl %TYPE,
    cuenta_aten             rec_linea_opera_recla.num_unid_recl %TYPE,
    cuenta_aten_pre         ped_solic_Cabec.Val_Tota_Paga_Loca %TYPE
    );

  TYPE boletarecojotype IS TABLE OF boletarecojorec;
  boletarecojorecord    boletarecojotype;
  w_filas               NUMBER := 100000;
  lb_clienteold         NUMBER(12) := NULL;
  lb_boletaold          NUMBER(12) := NULL;
  lb_boletacurr         NUMBER(12) := NULL;
  lb_lineacurr          NUMBER(12) := NULL;
  lb_num_recojo         NUMBER(4);
  lb_num_sec            NUMBER(4);
  lb_unidades_reclamdas NUMBER(10) := 0;

  --PARAMETROS DE CURSOR
  lnoidmarca   NUMBER;
  lnoidcanal   NUMBER;
  lnoidperiodo NUMBER;
  lnoidperiodo1 NUMBER;
  lnoidperiodo2 NUMBER;
  lnoidperiodoEI NUMBER;
  lnoidperiodoEF NUMBER;


  lngrupofacturacion     NUMBER;
  lnoidestreclafacturado NUMBER;
  lnoidestreclaenviado   NUMBER;
  lnoidestoperafacturado NUMBER;
  lnoidestoperaenviado   NUMBER;
  lnoidtipocierra        NUMBER;
  lnoidtipocierrar       NUMBER;
  lnoidtiposolipaisoc    NUMBER;
  lnoidtiposolipaisex    NUMBER;

  lncuenta                 NUMBER := 0;
  lncuentacierrezonaregion NUMBER := 0;
  lbindicadorinsert        BOOLEAN := TRUE;

    lscodpais        VARCHAR2(15);
    lsactuacronobr   VARCHAR2(1);
    lscodigoperiodo  VARCHAR2(6);
    lscodigoperiodo1  VARCHAR2(6);
    lscodigoperiodo2  VARCHAR2(6);
    lscodigoperiodoBR VARCHAR2(6);
    lscodigoperiodoEI VARCHAR2(6);
    lscodigoperiodoEF VARCHAR2(6);
    lsindtsolgenbr     VARCHAR2(1);
    lsindgenebr       VARCHAR2(1);
    lsindgenebrUA     VARCHAR2(1);
    lsindgenebrOK     VARCHAR2(1);
    lncuentaUA        NUMBER := 0;
    lncantmaxBR  NUMBER;
    lncamparaBR  NUMBER;
    lnpregenBR   NUMBER;
    lncuentaBR        NUMBER := 0;
    lncuentaATEN      NUMBER := 0;
    lncuentaATENPRE   NUMBER := 0;

    lsnroregact        NUMBER := 0;
    lb_clientebr       NUMBER(12) := NULL;
    lsindpremio        VARCHAR2(1);
    lsindanulaBR       VARCHAR2(1);
    lsindSinPedBR      VARCHAR2(1);
    lsindanulaBRold    VARCHAR2(1);
    lsindSinPedBRold   VARCHAR2(1);
    lsvarmensaje       VARCHAR2(50);

  lsnumlote        int_rec_cabec_borec.num_lote_envi%TYPE;


BEGIN

   -- Actualiza Cronograma de Boleta de Recojo segun el pais

    lscodpais := pscodigopais;
    lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_ACTUA_CRONO_BR');
    lsindtsolgenbr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_TSOL_GENBR');
    lsindgenebr    := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR');
    lsindgenebrUA  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR_UA'),'0');
    lncamparaBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_CPBR'),24);
    lncantmaxBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_NUM_BR'),999);
    lnpregenBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_DESV_BR'),0);


  --INICIALIZANDO PARAMETROS DE CURSOR
  lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
  lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                             lnoidmarca,
                                                             lnoidcanal);

  lngrupofacturacion := int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5');

   select to_char(sysdate,'YYYYMMDD') into lsnumlote from dual;


    --- Si no se procesa las UA, todos los registros se dan como OK
    if lsindgenebrUA = '0' then
       lsindgenebrOK := '1';
    else
       lsindgenebrOK := '0';
    end if;

    IF lsactuacronobr = '1' or
       lsactuacronobr = '2' or
       lsactuacronobr = '3' or
       lsactuacronobr = '4' THEN
      --- Obtiene el periodo
      SELECT MIN(cod_peri)INTO lscodigoperiodo FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0' AND c.ind_camp_act = '1';

      --- Borra registros del cronograma de BR
      DELETE FROM int_rec_cierr_borec WHERE cod_peri = lscodigoperiodo;

      IF lsactuacronobr = '1' THEN
        --- Inserta registros del cronograma de BR en general
        INSERT INTO int_rec_cierr_borec
        SELECT a.cod_pais,c.cod_regi,a.cod_peri,b.fec_inic,b.fec_fina,USER,SYSDATE,'','',F.COD_ZONA
          FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f
         WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
           and C.OID_REGI = F.ZORG_OID_REGI;
      ELSE
        IF lsactuacronobr = '2' THEN

            INSERT INTO int_rec_cierr_borec
              SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', F.COD_ZONA
              FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                        (select RR.COD_REGI, zz.cod_Zona,  min(cc.fec_inic) fec_inic
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI , zz.cod_Zona) d,
                 (SELECT X.COD_REGI, X.cod_Zona , X.FEC_CIER FROM fac_progr_cierr x
                  WHERE x.tip_cier = 'Z' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') and X.CAM_PROC = lscodigoperiodo) e
             WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
             and C.OID_REGI = F.ZORG_OID_REGI
             and F.COD_ZONA = d.COD_ZONA(+)
             and F.COD_ZONA = e.COD_ZONA(+);

         else
             IF lsactuacronobr = '3' THEN

            --- Inserta registros del cronograma de BR en funcion a actividad FA y RF
            INSERT INTO int_rec_cierr_borec
              SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', f.cod_zona
              FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                        (select RR.COD_REGI, zz.cod_zona, min(cc.fec_inic) fec_inic
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI, zz.cod_zona) d,
                        (select RR.COD_REGI, zz.cod_zona ,  max(cc.fec_inic) fec_cier
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'RF' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI, zz.cod_zona) e
             WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
             and C.OID_REGI = F.ZORG_OID_REGI
             and F.COD_ZONA = d.COD_ZONA(+)
             and F.COD_ZONA = e.COD_ZONA(+);
               else

                    INSERT INTO int_rec_cierr_borec
                      SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', F.COD_ZONA
                      FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                                (select RR.COD_REGI, zz.cod_Zona,  min(cc.fec_inic) fec_inic
                                from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                                where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                                and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                                and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI , zz.cod_Zona) d,
                         (SELECT X.COD_REGI, X.cod_Zona , X.FEC_CIER FROM fac_progr_cierr x
                          WHERE x.tip_cier = 'R' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') and X.CAM_PROC = lscodigoperiodo) e
                     WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
                     and C.OID_REGI = F.ZORG_OID_REGI
                     and F.COD_ZONA = d.COD_ZONA(+)
                     and c.COD_REGI = e.COD_REGI(+);

               END IF;

         END IF;
      END IF;

      --- Obtiene periodos para controlar la nueva forma de trabajo de colombia
      lscodigoperiodo1  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-1);
      lscodigoperiodo2  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-2);
      lscodigoperiodoEF := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-3);
      lscodigoperiodoEI := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-10);
      lscodigoperiodoBR := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,lncamparaBR*-1);

      if lsindgenebr = '1' then
         lnoidperiodo1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo1);
         lnoidperiodo2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo2);
         lnoidperiodoEI := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodoEI);
         lnoidperiodoEF := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodoEF);
      else
         lnoidperiodo1 := lnoidperiodo;
         lnoidperiodo2 := lnoidperiodo;
         lnoidperiodoEI := lnoidperiodo;
         lnoidperiodoEF := lnoidperiodo;
      end if;
    END IF;

  lnoidtiposolipaisoc := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1');
  lnoidtiposolipaisex := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C8');
  lnoidtipocierra     := 2;
  lnoidtipocierrar    := 1;

  OPEN c_boleta_recojo(lnoidperiodo, lngrupofacturacion,
                       lnoidestreclafacturado,
                       lnoidestreclaenviado,
                       lnoidestoperafacturado,
                       lnoidestoperaenviado, lnoidtipocierra,
                       lnoidtiposolipaisoc, lnoidtiposolipaisex,
                       lnoidperiodo1, lnoidperiodo2);
  LOOP
    FETCH c_boleta_recojo BULK COLLECT
      INTO boletarecojorecord LIMIT w_filas;
    IF boletarecojorecord.COUNT > 0 THEN

       --- si esta activo el indicador para generar nueva forma de BR
       if lsindgenebr = '1' or lsindgenebr = '2' then
          --- para marcar el primer registro con el flag de premio
          FOR j IN boletarecojorecord.FIRST .. boletarecojorecord.LAST
          LOOP

            IF (lb_clienteBR IS NULL OR
               lb_clienteBR <> boletarecojorecord(j).clie_oid_clie) THEN
               -- si no es el primer registro no actualiza
               if j <> 1 then
                  boletarecojorecord(lsnroregact).ind_premio := lsindpremio;
               end if;

               --- carga valores por quiebre de consultora
               lb_clienteBR := boletarecojorecord(j).clie_oid_clie;
               lsnroregact := j;
               lsindpremio := '0';

               ---- Proceso para Colombia
               ---- Si se genera como colombia y todo esta ok con las zonas se valida
               ---- si no trabaja como antes.
                ---- valida indicador si se encuentra en tabla UA
                 if lsindgenebrUA = '1' then
                    lsindgenebrOK := '0';
                    --- valida la UA
                    select count(*) into lncuentaUA from INT_REC_GENE_BOREC
                    where cod_pais = lscodpais
                    and (cod_regi = boletarecojorecord(j).cod_regi  or
                         cod_zona = boletarecojorecord(j).cod_zona)
                    and ind_reg = '1';
                    if lncuentaUA > 0 then
                       lsindgenebrOK := '1';
                    end if;
                 end if;
                 boletarecojorecord(j).ind_gene_br_ok := lsindgenebrOK;

              if lsindgenebr = '1' then  --- solo hace esto para colombia
                 ---- Valida recurrencia de BR
                  select count(*) into lncuentaBR from int_rec_cabec_borec br
                  where BR.COD_PERI_PROC between lscodigoperiodoBR and lscodigoperiodo
                  and BR.clie_oid_clie = boletarecojorecord(j).clie_oid_clie;

                 boletarecojorecord(j).cuenta_br := lncuentaBR;

                 ---- Valida atenciones de premio o con precio cero
                  select count(*) into lncuentaATEN
                  from ped_solic_cabec psc, ped_solic_posic psp
                  where PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                  and PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and ( PSP.VAL_CODI_VENT_FICT is not null or
                           (PSP.VAL_CODI_VENT is not null and PSP.VAL_PREC_CATA_UNIT_LOCA = 0))
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = boletarecojorecord(j).clie_oid_clie ;

                 boletarecojorecord(j).cuenta_aten := lncuentaATEN;

                  ---- Valoriza las atenciones
                  select sum( PSP.NUM_UNID_DEMA * PSP.VAL_PREC_CATA_UNIT_LOCA)
                        into lncuentaATENPRE
                  from ped_solic_cabec psc, ped_solic_posic psp
                  where PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                  and PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSP.VAL_CODI_VENT  is not null
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = boletarecojorecord(j).clie_oid_clie ;

                 boletarecojorecord(j).cuenta_aten_pre := lncuentaATENPRE;

             end if;

            END IF;
            if (boletarecojorecord(j).cod_oper_homl = 'CP' or
                boletarecojorecord(j).cod_oper_homl = 'DP' or
                boletarecojorecord(j).cod_oper_homl = 'AP' or
                boletarecojorecord(j).cod_oper_homl = 'SP' or
                boletarecojorecord(j).cod_oper_homl = 'TP') then
               lsindpremio := '1';
            end if;
          END LOOP;

         -- Actualiza el ultimo quiebre
         boletarecojorecord(lsnroregact).ind_premio := lsindpremio;

      end if;

      FOR i IN boletarecojorecord.FIRST .. boletarecojorecord.LAST
      LOOP
        IF (lb_clienteold IS NULL OR
           lb_clienteold <> boletarecojorecord(i).clie_oid_clie) THEN

          lbindicadorinsert := TRUE;

          lsindanulaBR := '0';

          lsindSinPedBR := '0';  --- Indicador solo para Chile

          ---- Con las que estan en Gp4
            SELECT COUNT(*)
              INTO lncuenta
              FROM ped_solic_cabec con
             WHERE con.clie_oid_clie = boletarecojorecord(i).clie_oid_clie
               AND con.perd_oid_peri = lnoidperiodo -- periodo de pantalla de SSICC
               AND con.grpr_oid_grup_proc = 4
               ----AND con.soca_oid_soli_cabe IS NULL
               AND con.tspa_oid_tipo_soli_pais IN
                   (SELECT TSP.OID_TIPO_SOLI_PAIS
                    FROM ped_tipo_solic_pais tsp,ped_tipo_solic      ts
                    WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                    and   tsp.tsol_oid_tipo_cons in(lnoidtiposolipaisoc, lnoidtiposolipaisex)); -- C1, C8

          ---------*********--------****

          --Si la consultora ha pasado pedido
          IF lncuenta > 0 THEN

              --- Actualiza las atenciones para la campa?a actual
              update ped_solic_cabec psc
              set psc.perd_oid_peri = lnoidperiodo,
                  psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                  psc.val_punt_emis = 777
              where PSC.FEC_FACT is null
              and PSC.IND_OC = 0
              AND psc.fec_repo_falt IS NULL
              and psc.modu_oid_modu <> 23
              and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
              and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;            

          ELSE
             --- Verifica el cierre de region

            SELECT COUNT(*) INTO lncuentacierrezonaregion
              FROM fac_contr_cierr ci
             WHERE ci.perd_oid_peri = lnoidperiodo
               AND ((ci.tcie_oid_tipo_cier = lnoidtipocierra AND
                   ci.zzon_oid_zona = boletarecojorecord(i)
                   .oid_zon) OR
                   (ci.tcie_oid_tipo_cier = lnoidtipocierrar AND
                   ci.zorg_oid_regi = boletarecojorecord(i)
                   .oid_regi));

            -- Si cerro la zona o region
            IF lncuentacierrezonaregion > 0 THEN
               lbindicadorinsert := TRUE;
            ELSE
               lbindicadorinsert := FALSE;
            END IF;

             -- Antes de insertar valida con los nuevas condiciones
             IF lbindicadorinsert THEN

               --- Empieza la validacion con los registros ok
               if lsindgenebr = '1' and boletarecojorecord(i).ind_gene_br_ok = '1' then

                     --- si no paso pedido y es acampa?a actual o x+2 se valida
                 if boletarecojorecord(i).perd_oid_peri_recl = lnoidperiodo2 then
                   lbindicadorinsert := true;

                   ---- Valida recurrencia de BR
                    if boletarecojorecord(i).cuenta_br >= lncantmaxBR then
                       lbindicadorinsert := TRUE;

                       --- Recupera las atenciones solo para Recojo inteligente (Colombia)
                       if lsindgenebr = '1' then
                          --- Actualiza las atenciones para la campa?a actual
                          update ped_solic_cabec psc
                          set psc.perd_oid_peri = lnoidperiodo,
                              psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                              psc.val_punt_emis = 777
                          where PSC.FEC_FACT is null
                          AND psc.fec_repo_falt IS NULL
                          and psc.modu_oid_modu <> 23
                          and PSC.IND_OC = 0
                          and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                          and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;
                       end if;
                    else
                       ---- Valida atenciones de premio o con precio cero

                        if boletarecojorecord(i).cuenta_aten > 0 or
                           boletarecojorecord(i).ind_premio = '1' then
                           lbindicadorinsert := TRUE;

                               --- Actualiza las atenciones para la campa?a actual
                            update ped_solic_cabec psc
                            set psc.perd_oid_peri = lnoidperiodo,
                                psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                                psc.val_punt_emis = 777
                            where PSC.FEC_FACT is null
                            and PSC.IND_OC = 0
                            AND psc.fec_repo_falt IS NULL
                            and psc.modu_oid_modu <> 23
                            and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                            and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;

                        else
                            --- si la atencion valorizada es mayor al parametro
                            if boletarecojorecord(i).cuenta_aten_pre > lnpregenBR  then
                               lbindicadorinsert := TRUE;

                                   --- Actualiza las atenciones para la campa?a actual
                                update ped_solic_cabec psc
                                set psc.perd_oid_peri = lnoidperiodo,
                                    psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                                    psc.val_punt_emis = 777
                                where PSC.FEC_FACT is null
                                and PSC.IND_OC = 0
                                AND psc.fec_repo_falt IS NULL
                                and psc.modu_oid_modu <> 23
                                and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                                and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;
                            else
                               ---- Genera la boleta de recojo pero como anulada
                               lbindicadorinsert := TRUE;
                               lsindanulaBR := '1';
                            end if;
                        end if;
                    end if;
                 else
                       --- si no paso pedido y es acampa?a actual o x+1 no se valida
                   lbindicadorinsert := FALSE;
                 end if;
              END IF;
            END IF;
          END IF;
          ---------*********--------****

          lb_boletaold          := lb_boletacurr;
          lb_clienteold         := boletarecojorecord(i).clie_oid_clie;
          lsindanulaBRold       := lsindanulaBR;
          lsindSinPedBRold      := lsindSinPedBR;


          lb_unidades_reclamdas := 0;
          lb_num_sec            := 1;
        END IF;

        lb_num_sec            := lb_num_sec + 1;
        lb_unidades_reclamdas := lb_unidades_reclamdas +
                                 boletarecojorecord(i)
                                .num_unid_recl;
      END LOOP;

    END IF;
    EXIT WHEN c_boleta_recojo%NOTFOUND;
  END LOOP;
  CLOSE c_boleta_recojo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR int_pr_rec_gener_aten_inte: ' ||
                             ls_sqlerrm);

END int_pr_rec_gener_aten_inte;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Generar
                       Boletas de Recojo del Cliente
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_gener_bolet_recoj
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
) IS
  CURSOR c_boleta_recojo(vnoidperiodo NUMBER, vngrupofacturacion NUMBER, vnoidestreclafacturado NUMBER, vnoidestreclaenviado NUMBER, vnoidestoperafacturado NUMBER, vnoidestoperaenviado NUMBER, vnoidtipocierra NUMBER, vnoidtiposolipaisoc NUMBER,
                         vnoidtiposolipaisex NUMBER, vnoidperiodo1 NUMBER, vnoidperiodo2 NUMBER
  ) IS
    SELECT DISTINCT rc.pais_oid_pais, -- OID DEL PAIS
                    ---rc.ztad_oid_terr_admi, -- OID DE LA UNIDAD ADMINISTRATIVA
                    UA.ZTAD_OID_TERR_ADMI, -- OID DE LA UNIDAD ADMINISTRATIVA
                    rc.esre_oid_esta_recl, -- OID DEL ESTADO DEL RECLAMO
                    rc.oid_cabe_recl, -- OID DE LA CABECERA DE RECLAMO
                    rc.num_aten, -- NUMERO DE ATENCION DE RECLMOA
                    rc.num_recl, -- NUMERO DE RECLMOA
                    rc.clie_oid_clie, -- OID DEL CLIENTE
                    rc.soca_oid_soli_cabe, --OID DEL DOCUMENTO DE REFERENCIA
                    rc.perd_oid_peri_docu_refe, -- OID DEL PERIODO DEL DOCUMENTO DE REFERENCIA
                    rc.perd_oid_peri_recl, -- OID DEL PERIODO DE PROCESO DE RECLAMO
                    ro.oid_oper_recl, -- OID DE LA OPERACION RECLAMO
                    ro.num_secu_oper, -- NUMERO DE SECUENCIA DE OPERACION
                    op.cod_oper, -- CODIGO DE OPERACION
                    top.val_tipo_oper, -- TIPO OPERACION
                    rl.mafa_oid_matr_fact, -- OID DE MATRIZ DE FACTURACION
                    rl.tofe_oid_tipo_ofer, -- OID DE TIPO OFERTA
                    rl.copa_oid_para_gral, -- OID DE CONCURSO
                    rl.lopa_oid_lote_prem_arti, -- OID DE LOTE PREMIO
                    rl.panp_oid_para_nive_prem, -- OID DE NIVEL DE PREMIO
                    rl.prod_oid_prod, -- OID DE PRODUCTO
                    rl.val_prec, -- PRECIO FACTURA DE PRODUCTO
                    rl.val_prec_cont, -- PRECIO CONTABLE
                    rl.num_unid_recl, -- NUMERO DE UNIDADES RECLAMADAS
                    trunc(SYSDATE) fec_inic, -- FECHA DE INICIO DE ACTIVIDAD DE FACTURACION O REFACTURACION
                    zr.cod_regi, -- CODIGO DE REGION
                    zz.cod_zona, -- CODIGO DE ZONA
                    zs.cod_secc, -- CODIGO DE SECCION
                    zt.cod_terr, -- CODIGO DE TERRITORIO
                    mp.cod_sap, -- CODIGO SAP
                    mp.codi_anti, -- CODIGO ANTIGUO
                    NULL oid_soli_desp, -- OID DE SOLICITUD DESPACHO
                    NULL val_num_desp, -- NUMERO DE BOLETA DE DESPACHO
                    rh.cod_oper_homl, -- OPERACION HOMOLOGADA
                    mh.des_oper_homl, -- DESCRIPCION OPERACION HOMOLOGADA
                    sp.cod_peri, -- PERIODO DE PROCESO
                    rl.oid_line_oper_recl, -- LINEA DE OPERACION DE RECLAMOS
                    (select rmd.cod_moti_devo from rec_motiv_devol rmd where rmd.oid_moti_devo = rl.modv_oid_moti_devo), ---codigo motivo
                    zz.oid_zona,
                    zr.oid_regi,
                    '0' ind_premio,
                    '0' ind_gene_br_ok,
                    0 cuenta_br,
                    0 cuenta_aten,
                    0 cuenta_aten_pre
      FROM rec_cabec_recla           rc,
           rec_opera_recla           ro,
           rec_linea_opera_recla     rl,
           rec_opera                 op,
           rec_tipos_opera           top,
           mae_clien_unida_admin     ua,
           zon_regio                 zr,
           zon_zona                  zz,
           zon_secci                 zs,
           zon_terri_admin           zta,
           zon_terri                 zt,
           mae_produ                 mp,
           int_rec_opera_homol_borec rh,
           int_mae_opera_homol_borec mh,
           cra_perio                 cra,
           seg_perio_corpo           sp,
           int_rec_cierr_borec cie
     WHERE rc.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rl.opre_oid_oper_recl
       AND rc.esre_oid_esta_recl IN
           (vnoidestreclafacturado, vnoidestreclaenviado)
       AND ro.esop_oid_esta_oper IN
           (vnoidestoperafacturado, vnoidestoperaenviado)
       AND ro.tiop_oid_tipo_oper = top.oid_tipo_oper
       AND top.rope_oid_oper = op.oid_oper
       AND rl.timo_oid_tipo_movi = 2
       and RC.CLIE_OID_CLIE = UA.CLIE_OID_CLIE
       and UA.IND_ACTI = 1
       AND UA.ZTAD_OID_TERR_ADMI = zta.oid_terr_admi
       ---AND rc.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = zs.oid_secc
       AND rl.num_unid_recl > 0
       AND zs.ind_acti = 1
       AND zs.zzon_oid_zona = zz.oid_zona
       AND zz.ind_acti = 1
       AND zz.zorg_oid_regi = zr.oid_regi
       AND zr.ind_acti = 1
       AND zta.terr_oid_terr = zt.oid_terr
       ---AND rc.perd_oid_peri_recl = vnoidperiodo
       AND rc.perd_oid_peri_recl in ( vnoidperiodo, vnoidperiodo1, vnoidperiodo2)
       AND op.ind_anul <> 1
       AND (op.ind_falt_merc = 0 OR
           (op.ind_falt_merc = 1 AND op.ind_devu_fisi_fact = 0 AND
           rl.sopo_oid_soli_posi IS NULL))
       AND mp.oid_prod = rl.prod_oid_prod
       AND rh.cod_pais = pscodigopais
       AND mh.cod_pais = pscodigopais
       AND rh.cod_oper_rec = op.cod_oper
       AND rh.cod_oper_homl = mh.cod_oper_homl
       and rh.ind_gene_bore = '1'    ---- Solo los operaciones que generan BR
       AND cra.oid_peri = rc.perd_oid_peri_recl
       AND cra.peri_oid_peri = sp.oid_peri
       AND NOT EXISTS
     (SELECT rc.oid_cabe_recl
              FROM int_rec_linea_borec lb
             WHERE lb.care_oid_cabe_recl = rc.oid_cabe_recl)
       AND op.cod_oper NOT IN
           (SELECT cod_oper
              FROM int_rec_opera_exclu
             WHERE cod_pais = pscodigopais)
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = zr.cod_regi
         AND cie.cod_zona = zz.cod_zona   ---- SQA agregado
         AND cie.cod_pais = pscodigopais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
     ORDER BY oid_regi,
              oid_zona,
              clie_oid_clie;

  TYPE boletarecojorec IS RECORD(
    pais_oid_pais           rec_cabec_recla.pais_oid_pais %TYPE,
    ztad_oid_terr_admi      rec_cabec_recla.ztad_oid_terr_admi %TYPE,
    esre_oid_esta_recl      rec_cabec_recla.esre_oid_esta_recl %TYPE,
    oid_cabe_recl           rec_cabec_recla.oid_cabe_recl %TYPE,
    num_aten                rec_cabec_recla.num_aten %TYPE,
    num_recl                rec_cabec_recla.num_recl %TYPE,
    clie_oid_clie           rec_cabec_recla.clie_oid_clie %TYPE,
    soca_oid_soli_cabe      rec_cabec_recla.soca_oid_soli_cabe %TYPE,
    perd_oid_peri_docu_refe rec_cabec_recla.perd_oid_peri_docu_refe %TYPE,
    perd_oid_peri_recl      rec_cabec_recla.perd_oid_peri_recl %TYPE,
    oid_oper_recl           rec_opera_recla.oid_oper_recl %TYPE,
    num_secu_oper           rec_opera_recla.num_secu_oper %TYPE,
    cod_oper                rec_opera.cod_oper %TYPE,
    val_tipo_oper           rec_tipos_opera.val_tipo_oper %TYPE,
    mafa_oid_matr_fact      rec_linea_opera_recla.mafa_oid_matr_fact %TYPE,
    tofe_oid_tipo_ofer      rec_linea_opera_recla.tofe_oid_tipo_ofer %TYPE,
    copa_oid_para_gral      rec_linea_opera_recla.copa_oid_para_gral %TYPE,
    lopa_oid_lote_prem_arti rec_linea_opera_recla.lopa_oid_lote_prem_arti %TYPE,
    panp_oid_para_nive_prem rec_linea_opera_recla.panp_oid_para_nive_prem %TYPE,
    prod_oid_prod           rec_linea_opera_recla.prod_oid_prod %TYPE,
    val_prec                rec_linea_opera_recla.val_prec %TYPE,
    val_prec_cont           rec_linea_opera_recla.val_prec_cont %TYPE,
    num_unid_recl           rec_linea_opera_recla.num_unid_recl %TYPE,
    fec_inic                cra_crono.fec_inic %TYPE,
    cod_regi                zon_regio.cod_regi %TYPE,
    cod_zona                zon_zona.cod_zona %TYPE,
    cod_secc                zon_secci.cod_secc %TYPE,
    cod_terr                zon_terri.cod_terr %TYPE,
    cod_sap                 mae_produ.cod_sap %TYPE,
    cod_anti                mae_produ.codi_anti %TYPE,
    oid_soli_desp           ped_solic_cabec.oid_soli_cabe %TYPE,
    val_num_desp            ped_solic_cabec.val_nume_soli %TYPE,
    cod_oper_homl           int_rec_opera_homol_borec.cod_oper_homl %TYPE,
    des_oper_homol          int_mae_oper_homol.des_oper_homol %TYPE,
    cod_peri                seg_perio_corpo.cod_peri %TYPE,
    oid_line_oper_recl      rec_linea_opera_recla.oid_line_oper_recl %TYPE,
    cod_moti_devo           rec_motiv_devol.cod_moti_devo%TYPE,
    oid_zon                 zon_zona.oid_zona%TYPE,
    oid_regi                zon_regio.oid_regi%TYPE,
    ind_premio              int_rec_opera_homol_borec.ind_gene_bore%TYPE,
    ind_gene_br_ok          int_rec_opera_homol_borec.ind_gene_bore%TYPE,
    cuenta_br               rec_linea_opera_recla.num_unid_recl %TYPE,
    cuenta_aten             rec_linea_opera_recla.num_unid_recl %TYPE,
    cuenta_aten_pre         ped_solic_Cabec.Val_Tota_Paga_Loca %TYPE
    );

  TYPE boletarecojotype IS TABLE OF boletarecojorec;
  boletarecojorecord    boletarecojotype;
  w_filas               NUMBER := 100000;
  lb_clienteold         NUMBER(12) := NULL;
  lb_boletaold          NUMBER(12) := NULL;
  lb_boletacurr         NUMBER(12) := NULL;
  lb_lineacurr          NUMBER(12) := NULL;
  lb_num_recojo         NUMBER(4);
  lb_num_sec            NUMBER(4);
  lb_unidades_reclamdas NUMBER(10) := 0;

  --PARAMETROS DE CURSOR
  lnoidmarca   NUMBER;
  lnoidcanal   NUMBER;
  lnoidperiodo NUMBER;
  lnoidperiodo1 NUMBER;
  lnoidperiodo2 NUMBER;


  lngrupofacturacion     NUMBER;
  lnoidestreclafacturado NUMBER;
  lnoidestreclaenviado   NUMBER;
  lnoidestoperafacturado NUMBER;
  lnoidestoperaenviado   NUMBER;
  lnoidtipocierra        NUMBER;
  lnoidtipocierrar       NUMBER;
  lnoidtiposolipaisoc    NUMBER;
  lnoidtiposolipaisex    NUMBER;

  lncuenta                 NUMBER := 0;
  lncuentacierrezonaregion NUMBER := 0;
  lbindicadorinsert        BOOLEAN := TRUE;

    lscodpais        VARCHAR2(15);
    lsactuacronobr   VARCHAR2(1);
    lscodigoperiodo  VARCHAR2(6);
    lscodigoperiodo1  VARCHAR2(6);
    lscodigoperiodo2  VARCHAR2(6);
    lscodigoperiodoBR VARCHAR2(6);
    lsindtsolgenbr     VARCHAR2(1);
    lsindgenebr       VARCHAR2(1);
    lsindgenebrUA     VARCHAR2(1);
    lsindgenebrOK     VARCHAR2(1);
    lncuentaUA        NUMBER := 0;
    lncantmaxBR  NUMBER;
    lncamparaBR  NUMBER;
    lnpregenBR   NUMBER;
    lncuentaBR        NUMBER := 0;
    lncuentaATEN      NUMBER := 0;
    lncuentaATENPRE   NUMBER := 0;

    lsnroregact        NUMBER := 0;
    lb_clientebr       NUMBER(12) := NULL;
    lsindpremio        VARCHAR2(1);
    lsindanulaBR       VARCHAR2(1);
    lsindSinPedBR      VARCHAR2(1);
    lsindanulaBRold    VARCHAR2(1);
    lsindSinPedBRold   VARCHAR2(1);
    lsvarmensaje       VARCHAR2(50);

  lsnumlote        int_rec_cabec_borec.num_lote_envi%TYPE;


BEGIN

   -- Actualiza Cronograma de Boleta de Recojo segun el pais

    lscodpais := pscodigopais;
    lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_ACTUA_CRONO_BR');
    lsindtsolgenbr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_TSOL_GENBR');
    lsindgenebr    := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR');
    lsindgenebrUA  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR_UA'),'0');
    lncamparaBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_CPBR'),24);
    lncantmaxBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_NUM_BR'),999);
    lnpregenBR  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_DESV_BR'),0);


  --INICIALIZANDO PARAMETROS DE CURSOR
  lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
  lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                             lnoidmarca,
                                                             lnoidcanal);

  lngrupofacturacion := int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5');

   select to_char(sysdate,'YYYYMMDD') into lsnumlote from dual;


    --- Si no se procesa las UA, todos los registros se dan como OK
    if lsindgenebrUA = '0' then
       lsindgenebrOK := '1';
    else
       lsindgenebrOK := '0';
    end if;

    IF lsactuacronobr = '1' or
       lsactuacronobr = '2' or
       lsactuacronobr = '3' or
       lsactuacronobr = '4' THEN
      --- Obtiene el periodo
      SELECT MIN(cod_peri)INTO lscodigoperiodo FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0' AND c.ind_camp_act = '1';

      --- Borra registros del cronograma de BR
      DELETE FROM int_rec_cierr_borec WHERE cod_peri = lscodigoperiodo;

      IF lsactuacronobr = '1' THEN
        --- Inserta registros del cronograma de BR en general
        INSERT INTO int_rec_cierr_borec
        SELECT a.cod_pais,c.cod_regi,a.cod_peri,b.fec_inic,b.fec_fina,USER,SYSDATE,'','',F.COD_ZONA
          FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f
         WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
           and C.OID_REGI = F.ZORG_OID_REGI;
      ELSE
        IF lsactuacronobr = '2' THEN
            --- Inserta registros del cronograma de BR en funcion a la tabla de cierre de SAC
              /*SELECT a.cod_pais,c.cod_regi,a.cod_peri,b.fec_inic, decode(d.fec_cier,null,b.fec_fina,d.fec_cier) fec_fina,USER,SYSDATE,'',''
              FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c ,
                 (SELECT X.COD_REGI, X.FEC_CIER FROM fac_progr_cierr x
                  WHERE x.tip_cier = 'R' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') and X.CAM_PROC = lscodigoperiodo) d
              WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
              and C.COD_REGI = d.COD_REGI(+);*/

            INSERT INTO int_rec_cierr_borec
              SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', F.COD_ZONA
              FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                        (select RR.COD_REGI, zz.cod_Zona,  min(cc.fec_inic) fec_inic
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI , zz.cod_Zona) d,
                 (SELECT X.COD_REGI, X.cod_Zona , X.FEC_CIER FROM fac_progr_cierr x
                  WHERE x.tip_cier = 'Z' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') and X.CAM_PROC = lscodigoperiodo) e
             WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
             ---and C.COD_REGI = d.COD_REGI(+)
             ---and C.COD_REGI = e.COD_REGI(+);
             and C.OID_REGI = F.ZORG_OID_REGI
             ---and C.IND_ACTI = 1
             ---and F.IND_ACTI = 1
             and F.COD_ZONA = d.COD_ZONA(+)
             and F.COD_ZONA = e.COD_ZONA(+);

         else
             IF lsactuacronobr = '3' THEN

            --- Inserta registros del cronograma de BR en funcion a actividad FA y RF
            INSERT INTO int_rec_cierr_borec
              SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', f.cod_zona
              FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                        (select RR.COD_REGI, zz.cod_zona, min(cc.fec_inic) fec_inic
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI, zz.cod_zona) d,
                        (select RR.COD_REGI, zz.cod_zona ,  max(cc.fec_inic) fec_cier
                        from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                        where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                        and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                        and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'RF' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI, zz.cod_zona) e
             WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
             ---and C.COD_REGI = d.COD_REGI(+)
             --and C.COD_REGI = e.COD_REGI(+);
             and C.OID_REGI = F.ZORG_OID_REGI
             ---and C.IND_ACTI = 1
             ---and F.IND_ACTI = 1
             and F.COD_ZONA = d.COD_ZONA(+)
             and F.COD_ZONA = e.COD_ZONA(+);
               else

                    INSERT INTO int_rec_cierr_borec
                      SELECT a.cod_pais,c.cod_regi,a.cod_peri, decode(d.fec_inic,null,b.fec_inic,d.fec_inic) fec_inic, decode(e.fec_cier,null,b.fec_fina,e.fec_cier) fec_fina,USER,SYSDATE,'','', F.COD_ZONA
                      FROM bas_ctrl_fact a, cra_perio     b, zon_regio     c , zon_zona     f ,
                                (select RR.COD_REGI, zz.cod_Zona,  min(cc.fec_inic) fec_inic
                                from cra_crono cc, cra_perio cp, seg_perio_corpo sp, cra_activ ca, zon_zona zz, zon_regio rr
                                where cc.perd_oid_peri = cp.oid_peri and cp.peri_oid_peri = sp.oid_peri and ca.oid_acti = cc.cact_oid_acti
                                and zz.oid_zona = cc.zzon_oid_zona and ZZ.ZORG_OID_REGI = RR.OID_REGI
                                and sp.cod_peri = lscodigoperiodo and ca.cod_acti = 'FA' and ZZ.IND_ACTI = '1' and RR.IND_ACTI = '1'group by RR.COD_REGI , zz.cod_Zona) d,
                         (SELECT X.COD_REGI, X.cod_Zona , X.FEC_CIER FROM fac_progr_cierr x
                          WHERE x.tip_cier = 'R' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') and X.CAM_PROC = lscodigoperiodo) e
                     WHERE a.cod_peri = lscodigoperiodo AND b.val_nomb_peri LIKE '%' || a.cod_peri || '%'
                     and C.OID_REGI = F.ZORG_OID_REGI
                     and F.COD_ZONA = d.COD_ZONA(+)
                     and c.COD_REGI = e.COD_REGI(+);

               END IF;

         END IF;
      END IF;

      --- Obtiene periodos para controlar la nueva forma de trabajo de colombia
      lscodigoperiodo1  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-1);
      lscodigoperiodo2  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-2);
      lscodigoperiodoBR := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,lncamparaBR*-1);

      if lsindgenebr = '1' then
      lnoidperiodo1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo1);
      lnoidperiodo2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo2);
      else
         lnoidperiodo1 := lnoidperiodo;
         lnoidperiodo2 := lnoidperiodo;
      end if;
    END IF;


  SELECT b.num_reco_tota
    INTO lb_num_recojo
    FROM int_rec_num_recoj_borec b
   WHERE b.cod_pais = pscodigopais;


  SELECT er.oid_esta_recl
    INTO lnoidestreclafacturado
    FROM rec_estad_recla er
   WHERE er.cod_esta = 'F';

  SELECT er.oid_esta_recl
    INTO lnoidestreclaenviado
    FROM rec_estad_recla er
   WHERE er.cod_esta = 'E';

  SELECT eo.oid_esta_oper
    INTO lnoidestoperafacturado
    FROM rec_estad_opera eo
   WHERE eo.cod_esta_oper = 'F';

  SELECT eo.oid_esta_oper
    INTO lnoidestoperaenviado
    FROM rec_estad_opera eo
   WHERE eo.cod_esta_oper = 'E';

  lnoidtiposolipaisoc := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1');
  lnoidtiposolipaisex := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C8');
  lnoidtipocierra     := 2;
  lnoidtipocierrar    := 1;

  OPEN c_boleta_recojo(lnoidperiodo, lngrupofacturacion,
                       lnoidestreclafacturado,
                       lnoidestreclaenviado,
                       lnoidestoperafacturado,
                       lnoidestoperaenviado, lnoidtipocierra,
                       lnoidtiposolipaisoc, lnoidtiposolipaisex,
                       lnoidperiodo1, lnoidperiodo2);
  LOOP
    FETCH c_boleta_recojo BULK COLLECT
      INTO boletarecojorecord LIMIT w_filas;
    IF boletarecojorecord.COUNT > 0 THEN

       --- si esta activo el indicador para generar nueva forma de BR
       if lsindgenebr = '1' or lsindgenebr = '2' then
          --- para marcar el primer registro con el flag de premio
          FOR j IN boletarecojorecord.FIRST .. boletarecojorecord.LAST
          LOOP

            IF (lb_clienteBR IS NULL OR
               lb_clienteBR <> boletarecojorecord(j).clie_oid_clie) THEN
               -- si no es el primer registro no actualiza
               if j <> 1 then
                  boletarecojorecord(lsnroregact).ind_premio := lsindpremio;
               end if;

               --- carga valores por quiebre de consultora
               lb_clienteBR := boletarecojorecord(j).clie_oid_clie;
               lsnroregact := j;
               lsindpremio := '0';

               ---- Proceso para Colombia
               ---- Si se genera como colombia y todo esta ok con las zonas se valida
               ---- si no trabaja como antes.
                ---- valida indicador si se encuentra en tabla UA
                 if lsindgenebrUA = '1' then
                    lsindgenebrOK := '0';
                    --- valida la UA
                    select count(*) into lncuentaUA from INT_REC_GENE_BOREC
                    where cod_pais = lscodpais
                    and (cod_regi = boletarecojorecord(j).cod_regi  or
                         cod_zona = boletarecojorecord(j).cod_zona)
                    and ind_reg = '1';
                    if lncuentaUA > 0 then
                       lsindgenebrOK := '1';
                    end if;
                 end if;
                 boletarecojorecord(j).ind_gene_br_ok := lsindgenebrOK;

              if lsindgenebr = '1' then  --- solo hace esto para colombia
                 ---- Valida recurrencia de BR
                  select count(*) into lncuentaBR from int_rec_cabec_borec br
                  where BR.COD_PERI_PROC between lscodigoperiodoBR and lscodigoperiodo
                  and BR.clie_oid_clie = boletarecojorecord(j).clie_oid_clie;

                 boletarecojorecord(j).cuenta_br := lncuentaBR;

                 ---- Valida atenciones de premio o con precio cero
                  select count(*) into lncuentaATEN
                  from ped_solic_cabec psc, ped_solic_posic psp
                  where PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                  and PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and ( PSP.VAL_CODI_VENT_FICT is not null or
                           (PSP.VAL_CODI_VENT is not null and PSP.VAL_PREC_CATA_UNIT_LOCA = 0))
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = boletarecojorecord(j).clie_oid_clie ;

                 boletarecojorecord(j).cuenta_aten := lncuentaATEN;

                  ---- Valoriza las atenciones
                  select sum( PSP.NUM_UNID_DEMA * PSP.VAL_PREC_CATA_UNIT_LOCA)
                        into lncuentaATENPRE
                  from ped_solic_cabec psc, ped_solic_posic psp
                  where PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                  and PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSP.VAL_CODI_VENT  is not null
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = boletarecojorecord(j).clie_oid_clie ;

                 boletarecojorecord(j).cuenta_aten_pre := lncuentaATENPRE;

             end if;

            END IF;
            if (boletarecojorecord(j).cod_oper_homl = 'CP' or
                boletarecojorecord(j).cod_oper_homl = 'DP' or
                boletarecojorecord(j).cod_oper_homl = 'AP' or
                boletarecojorecord(j).cod_oper_homl = 'SP' or
                boletarecojorecord(j).cod_oper_homl = 'TP') then
               lsindpremio := '1';
            end if;
          END LOOP;

         -- Actualiza el ultimo quiebre
         boletarecojorecord(lsnroregact).ind_premio := lsindpremio;

      end if;

      FOR i IN boletarecojorecord.FIRST .. boletarecojorecord.LAST
      LOOP
        IF (lb_clienteold IS NULL OR
           lb_clienteold <> boletarecojorecord(i).clie_oid_clie) THEN

          lbindicadorinsert := TRUE;

          lsindanulaBR := '0';

          lsindSinPedBR := '0';  --- Indicador solo para Chile

          if lsindtsolgenbr = '1' then  ---- Con las que estan en Gp4
            SELECT COUNT(*)
              INTO lncuenta
              FROM ped_solic_cabec con
             WHERE con.clie_oid_clie = boletarecojorecord(i).clie_oid_clie
               AND con.perd_oid_peri = lnoidperiodo -- periodo de pantalla de SSICC
               AND con.grpr_oid_grup_proc = 4
               ----AND con.soca_oid_soli_cabe IS NULL
               AND con.tspa_oid_tipo_soli_pais IN
                   (SELECT TSP.OID_TIPO_SOLI_PAIS
                    FROM ped_tipo_solic_pais tsp,ped_tipo_solic      ts
                    WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                    and   tsp.tsol_oid_tipo_cons in(lnoidtiposolipaisoc, lnoidtiposolipaisex)); -- C1, C8
          else                          ---- Con el consolidado de GP5
            SELECT COUNT(*)
              INTO lncuenta
              FROM ped_solic_cabec con
             WHERE con.clie_oid_clie = boletarecojorecord(i).clie_oid_clie
               AND con.perd_oid_peri = lnoidperiodo -- periodo de pantalla de SSICC
               AND con.grpr_oid_grup_proc = lngrupofacturacion -- 5
               AND con.soca_oid_soli_cabe IS NULL
               AND con.tspa_oid_tipo_soli_pais IN (lnoidtiposolipaisoc, lnoidtiposolipaisex)  -- C1, C8
               AND (select count(*) from ped_solic_Cabec x
                    where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
                    and x.TSPA_OID_TIPO_SOLI_PAIS <>
                     (select tsp.oid_tipo_soli_pais
                      from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                      where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                      and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0  ; --- que no sea cargo de uso flexipago
          end if;

          ---------*********--------****

          if lsindgenebr = '2' then

              --Si la consultora ha pasado pedido y es de Santiago
              IF lncuenta > 0 and boletarecojorecord(i).ind_gene_br_ok = '1' THEN

                -- Recupero el ultimo facturado
                SELECT t_aux.val_nume_soli,
                       t_aux.oid_soli_cabe
                  INTO boletarecojorecord(i) .val_num_desp,
                       boletarecojorecord(i) .oid_soli_desp
                  FROM (SELECT val_nume_soli,
                               oid_soli_cabe
                          FROM ped_solic_cabec con
                           WHERE con.clie_oid_clie = boletarecojorecord(i).clie_oid_clie
                           AND con.perd_oid_peri = lnoidperiodo -- periodo de pantalla de SSICC
                             AND con.grpr_oid_grup_proc = lngrupofacturacion -- 5
                           AND con.soca_oid_soli_cabe IS NULL
                           AND con.tspa_oid_tipo_soli_pais IN
                               (lnoidtiposolipaisoc,lnoidtiposolipaisex) -- C1, C8
                           AND (select count(*) from ped_solic_Cabec x
                                where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
                                and x.TSPA_OID_TIPO_SOLI_PAIS <>
                                 (select tsp.oid_tipo_soli_pais
                                  from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                  where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                  and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
                         ORDER BY fec_fact DESC) t_aux
                 WHERE rownum = 1;
              else
                --- Verifica el cierre de region a fin de generar las BR
                /*SELECT COUNT(*) INTO lncuentacierrezonaregion
                  FROM fac_contr_cierr ci
                 WHERE ci.perd_oid_peri = lnoidperiodo
                   AND (
                        ---(ci.tcie_oid_tipo_cier = lnoidtipocierra AND
                        --- ci.zzon_oid_zona = boletarecojorecord(i).oid_zon) OR
                       (ci.tcie_oid_tipo_cier = lnoidtipocierrar AND
                       ci.zorg_oid_regi = boletarecojorecord(i).oid_regi)); */

                SELECT COUNT(*) INTO lncuentacierrezonaregion
                  FROM fac_progr_cierr x, bas_ctrl_fact y
                 WHERE X.FEC_CIER = Y.FEC_PROC and y.sta_camp = 0 and y.ind_camp_act = 1
                   and x.tip_cier = 'R' AND x.est_regi = '1' AND x.est_cier in ( 'A','P')
                   and x.cod_regi = boletarecojorecord(i).cod_regi
                   and X.CAM_PROC = lscodigoperiodo;

                 IF lncuentacierrezonaregion > 0 THEN
                   boletarecojorecord(i).val_num_desp := NULL;
                   boletarecojorecord(i).oid_soli_desp := NULL;

                   lbindicadorinsert := TRUE;
                   --- Si es cierre de region y es de Santiago nace BR con motivo
                   if boletarecojorecord(i).ind_gene_br_ok = '1' THEN
                      lsindSinPedBR := '1';
                   end if;
                 ELSE
                   lbindicadorinsert := FALSE;
                 END IF;

              end if;

          else
          --Si la consultora ha pasado pedido
          IF lncuenta > 0 THEN
             if lsindtsolgenbr = '1' then  ---- Con las que estan en Gp4
                 boletarecojorecord(i).val_num_desp := NULL;
                 boletarecojorecord(i).oid_soli_desp := NULL;
             else
              -- Recupero el ultimo facturado
              SELECT t_aux.val_nume_soli,
                     t_aux.oid_soli_cabe
                INTO boletarecojorecord(i).val_num_desp,
                     boletarecojorecord(i).oid_soli_desp
                FROM (SELECT val_nume_soli,
                             oid_soli_cabe
                        FROM ped_solic_cabec con
                       WHERE con.clie_oid_clie = boletarecojorecord(i).clie_oid_clie
                         AND con.perd_oid_peri = lnoidperiodo -- periodo de pantalla de SSICC
                         AND con.grpr_oid_grup_proc = lngrupofacturacion -- 5
                         AND con.soca_oid_soli_cabe IS NULL
                         AND con.tspa_oid_tipo_soli_pais IN
                             (lnoidtiposolipaisoc,lnoidtiposolipaisex) -- C1, C8
                         AND (select count(*) from ped_solic_Cabec x
                              where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
                              and x.TSPA_OID_TIPO_SOLI_PAIS <>
                               (select tsp.oid_tipo_soli_pais
                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
                       ORDER BY fec_fact DESC) t_aux
               WHERE rownum = 1;

               --- Recupera las atenciones solo para Recojo inteligente (Colombia)
               if lsindgenebr = '1' then
                  --- Actualiza las atenciones para la campa?a actual
                  update ped_solic_cabec psc
                  set psc.perd_oid_peri = lnoidperiodo,
                      psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                      psc.val_punt_emis = 777
                  where PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;
               end if;

             end if;
          ELSE
             --- Verifica el cierre de region
             if lsindtsolgenbr = '1' then  ---- Con las que estan en Gp4
                lncuentacierrezonaregion := 0;
             else
                SELECT COUNT(*) INTO lncuentacierrezonaregion
                  FROM fac_contr_cierr ci
                 WHERE ci.perd_oid_peri = lnoidperiodo
                   AND ((ci.tcie_oid_tipo_cier = lnoidtipocierra AND
                       ci.zzon_oid_zona = boletarecojorecord(i)
                       .oid_zon) OR
                       (ci.tcie_oid_tipo_cier = lnoidtipocierrar AND
                       ci.zorg_oid_regi = boletarecojorecord(i)
                       .oid_regi));
             end if;
            -- Si cerro la zona o region
            IF lncuentacierrezonaregion > 0 THEN
              boletarecojorecord(i).val_num_desp := NULL;
              boletarecojorecord(i).oid_soli_desp := NULL;
               lbindicadorinsert := TRUE;
            ELSE
              lbindicadorinsert := FALSE;
            END IF;

             -- Antes de insertar valida con los nuevas condiciones
             IF lbindicadorinsert THEN

               --- Empieza la validacion con los registros ok
               if lsindgenebr = '1' and boletarecojorecord(i).ind_gene_br_ok = '1' then

                     --- si no paso pedido y es acampa?a actual o x+2 se valida
                 if boletarecojorecord(i).perd_oid_peri_recl = lnoidperiodo2 then
                   lbindicadorinsert := true;

                   ---- Valida recurrencia de BR
                    if boletarecojorecord(i).cuenta_br >= lncantmaxBR then
                       lbindicadorinsert := TRUE;

                       --- Recupera las atenciones solo para Recojo inteligente (Colombia)
                       if lsindgenebr = '1' then
                          --- Actualiza las atenciones para la campa?a actual
                          update ped_solic_cabec psc
                          set psc.perd_oid_peri = lnoidperiodo,
                              psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                              psc.val_punt_emis = 777
                          where PSC.FEC_FACT is null
                          AND psc.fec_repo_falt IS NULL
                          and psc.modu_oid_modu <> 23
                          and PSC.IND_OC = 0
                          and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                          and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;
                       end if;
                    else
                       ---- Valida atenciones de premio o con precio cero

                        if boletarecojorecord(i).cuenta_aten > 0 or
                           boletarecojorecord(i).ind_premio = '1' then
                           lbindicadorinsert := TRUE;

                               --- Actualiza las atenciones para la campa?a actual
                            update ped_solic_cabec psc
                            set psc.perd_oid_peri = lnoidperiodo,
                                psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                                psc.val_punt_emis = 777
                            where PSC.FEC_FACT is null
                            and PSC.IND_OC = 0
                            AND psc.fec_repo_falt IS NULL
                            and psc.modu_oid_modu <> 23
                            and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                            and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;

                        else
                            --- si la atencion valorizada es mayor al parametro
                            if boletarecojorecord(i).cuenta_aten_pre > lnpregenBR  then
                               lbindicadorinsert := TRUE;

                                   --- Actualiza las atenciones para la campa?a actual
                                update ped_solic_cabec psc
                                set psc.perd_oid_peri = lnoidperiodo,
                                    psc.fec_prog_fact = (select fec_fina from cra_perio where oid_peri = lnoidperiodo),
                                    psc.val_punt_emis = 777
                                where PSC.FEC_FACT is null
                                and PSC.IND_OC = 0
                                AND psc.fec_repo_falt IS NULL
                                and psc.modu_oid_modu <> 23
                                and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                                and psc.clie_oid_clie = boletarecojorecord(i).clie_oid_clie ;
                            else
                               ---- Genera la boleta de recojo pero como anulada
                               lbindicadorinsert := TRUE;
                               lsindanulaBR := '1';
                            end if;
                        end if;
                    end if;
                 else
                       --- si no paso pedido y es acampa?a actual o x+1 no se valida
                   lbindicadorinsert := FALSE;
                 end if;
              END IF;
            END IF;
          END IF;
          end if;
          ---------*********--------****

          IF lbindicadorinsert THEN

            SELECT int_rcb_seq.NEXTVAL
              INTO lb_boletacurr
              FROM dual;

            INSERT INTO int_rec_cabec_borec
              (cod_pais,
               cod_cabe_bore,
               soca_oid_soli_cabe,
               val_nume_bole_desp,
               esbo_oid_esta_bor1,
               esbo_oid_esta_bor2,
               num_reco,
               clie_oid_clie,
               cod_clie,
               ztad_oid_terri_admin,
               cod_regi,
               cod_zona,
               cod_secc,
               cod_terr,
               more_oid_motn_reco_bore,
               fec_ingr,
               fec_prog_reco,
               fec_reco,
               hor_reco,
               fec_ulti_actu,
               val_nume_bore,
               num_tota_unid_recl,
               num_tota_unid_reco,
               ind_envi_xero,
               ind_envi_yobe,
               ind_regr_yobe,
               ind_chk_auto,
               ind_ocs_proc,
               ind_proc_alma_fisi,
               num_lote_envi,
               num_lote_devu,
               val_nomb_terc,
               cod_peri_proc)
            VALUES
              (pscodigopais --COD_PAIS
              ,
               lb_boletacurr --int_rcb_seq.NEXTVAL --COD_CABE_BORE
              ,
               boletarecojorecord(i).oid_soli_desp --SOCA_OID_SOLI_CABE
              ,
               boletarecojorecord(i).val_num_desp --VAL_NUME_BOLE_DESP
              ,
               '1' --ESBO_OID_ESTA_BOR1
              ,
               NULL --ESBO_OID_ESTA_BOR2
              ,
               1 --NUM_RECO
              ,
               boletarecojorecord(i).clie_oid_clie --CLIE_OID_CLIE
              ,
               gen_pkg_gener.gen_fn_devuelve_cod_clie(boletarecojorecord(i)
                                                      .clie_oid_clie) --COD_CLIE
              ,
               boletarecojorecord(i).ztad_oid_terr_admi --ZTAD_OID_TERRI_ADMIN
              ,
               boletarecojorecord(i).cod_regi --COD_REGI
              ,
               boletarecojorecord(i).cod_zona --COD_ZONA
              ,
               boletarecojorecord(i).cod_secc --COD_SECC
              ,
               boletarecojorecord(i).cod_terr --COD_TERR
              ,
               NULL --MORE_OID_MOTN_RECO_BORE
              ,
               (
                  select fec_proc
                  from bas_ctrl_fact
                  where sta_camp = 0
                  and ind_camp_act = 1
                  and rownum=1
               ) --FEC_INGR
              ,
               boletarecojorecord(i).fec_inic --FEC_PROG_RECO
              ,
               NULL --FEC_RECO
              ,
               NULL --HOR_RECO
              ,
               SYSDATE --FEC_ULTI_ACTU
              ,
               lb_boletacurr --int_rcb_seq.CURRVAL --VAL_NUME_BORE
              ,
               '0' --NUM_TOTA_UNID_RECL
              ,
               '0' --NUM_TOTA_UNID_RECO
              ,
               '0' --IND_ENVI_XERO
              ,
               '0' --IND_ENVI_YOBE
              ,
               '0' --IND_REGR_YOBE
              ,
               NULL --IND_CHK_AUTO
              ,
               'F' --IND_OCS_PROC
              ,
               'V' --IND_PROC_ALMA_FISI
              ,
               NULL --NUM_LOTE_ENVI
              ,
               NULL --NUM_LOTE_DEVU
              ,
               NULL --VAL_NOMB_TERC
              ,
               boletarecojorecord(i).cod_peri);
          END IF;
          IF (lb_clienteold IS NOT NULL) THEN
            UPDATE int_rec_cabec_borec c
               SET c.num_tota_unid_recl = lb_unidades_reclamdas
             WHERE c.clie_oid_clie = lb_clienteold
               AND c.cod_cabe_bore = lb_boletaold;

             -- verifica si la BR nace anulada
               if lsindanulaBRold = '1' then
                ---- Anula la boleta creada
                int_pr_rec_anula_borec(lb_boletaold,lsvarmensaje, 'ADMIN');

                -- Elimina las atenciones
                  delete from ped_solic_posic psp
                  where psp.soca_oid_soli_cabe in(
                  select psc.oid_soli_cabe from ped_solic_cabec psc
                  where PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = lb_clienteold);

                  delete from rec_solic_opera rso
                  where rso.soca_oid_soli_cabe in(
                  select psc.oid_soli_cabe from ped_solic_cabec psc
                  where PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = lb_clienteold);

                  delete ped_solic_cabec psc
                  where PSC.FEC_FACT is null
                  and PSC.IND_OC = 0
                  AND psc.fec_repo_falt IS NULL
                  and psc.modu_oid_modu <> 23
                  and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
                  and psc.clie_oid_clie = lb_clienteold ;

               end if  ;

               -- verifica si la BR nace con motivo sin pedido
               if lsindSinPedBRold = '1' then

                  UPDATE int_rec_cabec_borec c
                     SET c.esbo_oid_esta_bor1 = 3, c.esbo_oid_esta_bor2 = 6, c.num_reco = 1,
                         c.more_oid_motn_reco_bore = 7, c.fec_reco = trunc(sysdate()), c.fec_ulti_actu = trunc(sysdate()) ,
                         c.ind_envi_xero = '1', c.ind_envi_yobe = '1', c.ind_regr_yobe = '1', c.num_lote_devu = lsnumlote
                   WHERE c.clie_oid_clie = lb_clienteold
                     AND c.cod_cabe_bore = lb_boletaold;

               END IF;

          END IF;

          lb_boletaold          := lb_boletacurr;
          lb_clienteold         := boletarecojorecord(i).clie_oid_clie;
          lsindanulaBRold       := lsindanulaBR;
          lsindSinPedBRold      := lsindSinPedBR;


          lb_unidades_reclamdas := 0;
          lb_num_sec            := 1;
        END IF;
        IF lbindicadorinsert THEN

            SELECT int_rlb_seq.NEXTVAL
              INTO lb_lineacurr
              FROM dual;

          INSERT INTO int_rec_linea_borec
            (cod_pais,
             cod_cabe_bore,
             cod_line_bore,
             num_secu,
             care_oid_cabe_recl,
             soca_oid_soli_cabe,
             opre_oid_oper_recl,
             prod_oid_prod,
             mafa_oid_matr_fact,
             tofe_oid_tipo_ofer,
             copa_oid_para_gral,
             panp_oid_para_nive_prem,
             lopa_oid_lote_prem_arti,
             num_unid_recl,
             num_unid_reco,
             obse_bore,
             prod_oid_prod_disc,
             ind_disc,
             lor_oid_line_oper_recl,
             ind_proc_alma_fisi,
             ind_envi_xero,
             ind_envi_yobe,
             ind_regr_yobe,
             ind_ocs_proc,
             cod_oper_homol,
             val_prec,
             val_prec_cont,
             num_lote_envi,
             num_lote_devu,
             des_oper,
             cod_oper,
             cod_anti,
             cod_prod,
             COD_MOTI_DEVO)
          VALUES
            (pscodigopais --COD_PAIS
            ,
             lb_boletacurr --- int_rcb_seq.CURRVAL -- COD_CABE_BORE
            ,
             lb_lineacurr  ----- int_rlb_seq.NEXTVAL --COD_LINE_BORE
            ,
             lb_num_sec --NUM_SECU
            ,
             boletarecojorecord(i).oid_cabe_recl --CARE_OID_CABE_RECL
            ,
             boletarecojorecord(i).soca_oid_soli_cabe --SOCA_OID_SOLI_CABE
            ,
             boletarecojorecord(i).oid_oper_recl --OPRE_OID_OPER_RECL
            ,
             boletarecojorecord(i).prod_oid_prod --PROD_OID_PROD
            ,
             boletarecojorecord(i).mafa_oid_matr_fact --MAFA_OID_MATR_FACT
            ,
             boletarecojorecord(i).tofe_oid_tipo_ofer --TOFE_OID_TIPO_OFER
            ,
             boletarecojorecord(i).copa_oid_para_gral --COPA_OID_PARA_GRAL
            ,
             boletarecojorecord(i).panp_oid_para_nive_prem --PANP_OID_PARA_NIVE_PREM
            ,
             boletarecojorecord(i).lopa_oid_lote_prem_arti --LOPA_OID_LOTE_PREM_ARTI
            ,
             boletarecojorecord(i).num_unid_recl --NUM_UNID_RECL
            ,
             '0' --NUM_UNID_RECO
            ,
             NULL --OBSE_BORE
            ,
             NULL --PRD_OID_PROD_DISC
            ,
             '0' --IND_DISC  *\
            ,
             boletarecojorecord(i).oid_line_oper_recl --LOR_OID_LINE_OFER_RECL
            ,
             'V' --IND_PROC_ALMA_FISI
            ,
             '0' --IND_ENVI_XERO
            ,
             '0' --IND_ENVI_YOBE
            ,
             '0' --IND_REGR_YOBE
            ,
             'F' --IND_OCS_PROC
            ,
             boletarecojorecord(i).cod_oper_homl --COD_OPER_HOMOL
            ,
             boletarecojorecord(i).val_prec --VAL_PREC
            ,
             boletarecojorecord(i).val_prec_cont --VAL_PREC_CONT
            ,
             NULL --NUM_LOTE_ENVI
            ,
             NULL --NUM_LOTE_DEVU
            ,
             boletarecojorecord(i).des_oper_homol,
             boletarecojorecord(i).cod_oper,
             boletarecojorecord(i).cod_anti,
             boletarecojorecord(i).cod_sap,
             boletarecojorecord(i).cod_moti_devo
             );

             -- verifica si la BR nace con motivo sin pedido
             if lsindSinPedBR = '1' then

                UPDATE int_rec_linea_borec l
                   SET l.ind_envi_xero = '1', l.ind_envi_yobe = '1', l.ind_regr_yobe = '1', l.num_lote_devu = lsnumlote
                 WHERE l.cod_line_bore = lb_lineacurr
                   AND l.cod_cabe_bore = lb_boletacurr;

             END IF;

        END IF;
        lb_num_sec            := lb_num_sec + 1;
        lb_unidades_reclamdas := lb_unidades_reclamdas +
                                 boletarecojorecord(i)
                                .num_unid_recl;
      END LOOP;

      IF (lb_clienteold IS NOT NULL) THEN
        UPDATE int_rec_cabec_borec c
           SET c.num_tota_unid_recl = lb_unidades_reclamdas
         WHERE c.clie_oid_clie = lb_clienteold
           AND c.cod_cabe_bore = lb_boletaold;

         -- verifica si la BR nace anulada
         if lsindanulaBR = '1' then
           -- Anula la boleta creada
            int_pr_rec_anula_borec(lb_boletaold,lsvarmensaje, 'ADMIN');

            -- Elimina las atenciones en tablas PED
            delete from ped_solic_posic psp
            where psp.soca_oid_soli_cabe in(
            select psc.oid_soli_cabe from ped_solic_cabec psc
            where PSC.FEC_FACT is null
            and PSC.IND_OC = 0
            AND psc.fec_repo_falt IS NULL
            and psc.modu_oid_modu <> 23
            and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
            and psc.clie_oid_clie = lb_clienteold);

            delete from rec_solic_opera rso
            where rso.soca_oid_soli_cabe in(
            select psc.oid_soli_cabe from ped_solic_cabec psc
            where PSC.FEC_FACT is null
            and PSC.IND_OC = 0
            AND psc.fec_repo_falt IS NULL
            and psc.modu_oid_modu <> 23
            and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
            and psc.clie_oid_clie = lb_clienteold);

            delete ped_solic_cabec psc
            where PSC.FEC_FACT is null
            and PSC.IND_OC = 0
            AND psc.fec_repo_falt IS NULL
            and psc.modu_oid_modu <> 23
            and PSC.PERD_OID_PERI in ( lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
            and psc.clie_oid_clie = lb_clienteold ;
         end if  ;

         -- verifica si la BR nace con motivo sin pedido
         if lsindSinPedBRold = '1' then

            UPDATE int_rec_cabec_borec c
               SET c.esbo_oid_esta_bor1 = 3, c.esbo_oid_esta_bor2 = 6, c.num_reco = 1,
                   c.more_oid_motn_reco_bore = 7, c.fec_reco = trunc(sysdate()), c.fec_ulti_actu = trunc(sysdate()) ,
                   c.ind_envi_xero = '1', c.ind_envi_yobe = '1', c.ind_regr_yobe = '1', c.num_lote_devu = lsnumlote
             WHERE c.clie_oid_clie = lb_clienteold
               AND c.cod_cabe_bore = lb_boletaold;

         END IF;


      END IF;
      --COMMIT;

    END IF;
    EXIT WHEN c_boleta_recojo%NOTFOUND;
  END LOOP;
  CLOSE c_boleta_recojo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_GENER_BOLET_RECOJ: ' ||
                             ls_sqlerrm);

END int_pr_rec_gener_bolet_recoj;

/***************************************************************************
Descripcion       : Genera Interfaz de Recepcion de Cabeceras de Boleta de Recojo
Fecha Creacion    : 12/12/2007
Autor             : Jose A. Cairampoma
***************************************************************************/

PROCEDURE int_pr_rec_recep_borec_cabec(pscodigopais VARCHAR2) IS
  CURSOR c_interfaz IS
    SELECT cod_regi_cabe_bore,
           num_bole_reco,
           cod_clie,
           fec_reco,
           hor_reco,
           cod_moti,
           cod_resu,
           val_nomb_terc
      FROM int_rec_cabec_borec_tempo;

  TYPE interfazrec IS RECORD(
    cod_regi int_rec_cabec_borec_tempo.cod_regi_cabe_bore%TYPE,
    num_bole int_rec_cabec_borec_tempo.num_bole_reco%TYPE,
    cod_clie int_rec_cabec_borec_tempo.cod_clie%TYPE,
    fec_reco int_rec_cabec_borec_tempo.fec_reco%TYPE,
    hor_reco int_rec_cabec_borec_tempo.hor_reco%TYPE,
    cod_moti int_rec_cabec_borec_tempo.cod_moti%TYPE,
    cod_resu int_rec_cabec_borec_tempo.cod_resu%TYPE,
    nom_terc int_rec_cabec_borec_tempo.val_nomb_terc%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord  interfazrectab;
  lnnumreco       int_rec_cabec_borec.num_reco%TYPE;
  lsoidestabore1  int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsoidestabore2  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnparamnumreco  int_rec_num_recoj_borec.num_reco_tota%TYPE;
  lnidestacerrado int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  ldfechacierre   int_rec_cabec_borec.fec_cierr%TYPE:=NULL;
  lsCodigoResultado int_rec_cabec_borec_tempo.cod_resu%TYPE;
  lsMotivoNORecojo   int_rec_motno_recoj_borec.cod_motn_reco_bore%TYPE;
  lnOidMotivoNoRecojo int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  ldFechaRecojo       int_rec_cabec_borec.fec_reco%TYPE;
  lsHoraRecojo        int_rec_cabec_borec.hor_reco%TYPE;
  lsCodigoRegistro    int_rec_cabec_borec.num_lote_devu%TYPE;
  lsNombreTercero     int_rec_cabec_borec.val_nomb_terc%TYPE;

BEGIN
  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'CE');

  DELETE FROM  int_rec_cabec_borec_tempo X
  WHERE EXISTS (
      SELECT NULL
      FROM INT_REC_CABEC_BOREC Y
      WHERE TRIM(X.NUM_BOLE_RECO) = Y.COD_CABE_BORE
      AND Y.ESBO_OID_ESTA_BOR1 = lnidestacerrado
  );

  DELETE FROM  int_rec_detal_borec_tempo X
  WHERE EXISTS (
      SELECT NULL
      FROM INT_REC_CABEC_BOREC Y
      WHERE TRIM(X.NUM_BORE) = Y.COD_CABE_BORE
      AND Y.ESBO_OID_ESTA_BOR1 = lnidestacerrado
  );

  SELECT num_reco_tota
    INTO lnparamnumreco
    FROM int_rec_num_recoj_borec
   WHERE cod_pais = pscodigopais;

  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP

        SELECT cb.num_reco,
               cb.esbo_oid_esta_bor1,
               cb.esbo_oid_esta_bor2
          INTO lnnumreco,
               lsoidestabore1,
               lsoidestabore2
          FROM int_rec_cabec_borec cb
         WHERE val_nume_bore = interfazrecord(x).num_bole;

        --IF (lsoidestabore1 <> lnidestacerrado) THEN
          ldfechacierre := NULL;

          lsCodigoResultado :=interfazrecord(x).cod_resu;
          lsMotivoNORecojo  := interfazrecord(x).cod_moti;

          IF ( lsCodigoResultado= 'EX' OR lsCodigoResultado = 'CD'
               OR lsCodigoResultado = 'NE'
               OR (lsCodigoResultado = 'NX' AND lsMotivoNORecojo='05' )) THEN
            lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
            lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
            ldfechacierre  := trunc(SYSDATE);
          ELSE
            IF (lsCodigoResultado = 'NX') THEN
              IF (lnnumreco < lnparamnumreco) THEN
                lnnumreco      := 2;
                lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'GE');
                lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
              ELSE

                lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
                lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
                ldfechacierre  := trunc(SYSDATE);
                rec_pr_bloqu_consu(interfazrecord(x).cod_clie,'USUYOBEL');
              END IF;

            END IF;

          END IF;

          lnOidMotivoNoRecojo := int_pkg_recla.gen_fn_devue_oid_motno_borec(pscodigopais,lsMotivoNORecojo);
          ldFechaRecojo  := to_date(interfazrecord(x).fec_reco,'YYYYMMDD');
          lsHoraRecojo   := substr(interfazrecord(x).hor_reco,1, 2) || ':' ||substr(interfazrecord(x).hor_reco,3, 2);
          lsCodigoRegistro  := interfazrecord(x).cod_regi;
          lsNombreTercero  := interfazrecord(x).nom_terc;


          UPDATE int_rec_cabec_borec
             SET esbo_oid_esta_bor1      = lsoidestabore1,
                 esbo_oid_esta_bor2      = lsoidestabore2,
                 num_reco                = lnnumreco,
                 more_oid_motn_reco_bore = decode(num_reco, 1,lnOidMotivoNoRecojo,more_oid_motn_reco_bore),
                 fec_reco                = decode(num_reco, 1,ldFechaRecojo,fec_reco),
                 hor_reco                = decode(num_reco, 1,lsHoraRecojo,hor_reco),
                 fec_ulti_actu           = SYSDATE,
                 ind_regr_yobe           = decode(num_reco, 1, 1,ind_regr_yobe),
                 num_lote_devu           = decode(num_reco, 1,lsCodigoRegistro,num_lote_devu),
                 val_nomb_terc           = decode(num_reco, 1,lsNombreTercero,val_nomb_terc),
                 more_oid_motn_reco_bor2 = decode(num_reco, 2,lnOidMotivoNoRecojo,more_oid_motn_reco_bor2),
                 fec_rec2                = decode(num_reco, 2,ldFechaRecojo,fec_rec2),
                 hor_rec2                = decode(num_reco, 2,lsHoraRecojo, hor_rec2),
                 ind_regr_yob2           = decode(num_reco, 2, 1,ind_regr_yob2),
                 num_lote_dev2           = decode(num_reco, 2,lsCodigoRegistro,num_lote_dev2),
                 val_nomb_ter2           = decode(num_reco, 2,lsNombreTercero,val_nomb_ter2),
                 fec_cierr               = ldfechacierre
           WHERE val_nume_bore = interfazrecord(x).num_bole;
        --END IF;
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

EXCEPTION

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_RECEP_BOREC_CABEC: ' ||
                             ls_sqlerrm);

END int_pr_rec_recep_borec_cabec;

/***************************************************************************
Descripcion       : Genera Interfaz de Recepcion de Detalle de Boleta de Recojo
Fecha Creacion    : 12/12/2007
Autor             : Jose A. Cairampoma
***************************************************************************/

PROCEDURE int_pr_rec_recep_borec_detal(pscodigopais VARCHAR2) IS
  CURSOR c_interfaz(vnidestacerrado NUMBER) IS

    SELECT det.cod_pais,
           det.cod_cabe_bore,
           det.cod_line_bore,
           det.num_secu,
           det.care_oid_cabe_recl,
           det.soca_oid_soli_cabe,
           det.opre_oid_oper_recl,
           det.prod_oid_prod,
           det.cod_prod,
           det.mafa_oid_matr_fact,
           det.tofe_oid_tipo_ofer,
           det.copa_oid_para_gral,
           det.panp_oid_para_nive_prem,
           det.lopa_oid_lote_prem_arti,
           det.num_unid_recl,
           det.num_unid_reco,
           det.obse_bore,
           det.prod_oid_prod_disc,
           det.cod_prod_disc,
           det.ind_disc,
           det.lor_oid_line_oper_recl,
           det.ind_proc_alma_fisi,
           det.ind_envi_xero,
           det.ind_envi_yobe,
           det.ind_regr_yobe,
           det.ind_ocs_proc,
           det.cod_oper_homol,
           det.val_prec,
           det.val_prec_cont,
           det.num_lote_envi,
           det.num_lote_devu,
           det.des_oper,
           det.cod_oper,
           det.cod_anti,
           tmp.cod_regi_deta_bore,
           tmp.cod_prod_disc,
           tmp.val_unid,
           cab.num_reco,
           cab.esbo_oid_esta_bor1
      FROM int_rec_cabec_borec       cab,
           int_rec_linea_borec       det,
           int_rec_detal_borec_tempo tmp
     WHERE cab.cod_pais = det.cod_pais
       AND cab.cod_cabe_bore = det.cod_cabe_bore
       AND tmp.num_bore = cab.val_nume_bore
       AND tmp.cod_prod = det.cod_prod
       AND tmp.cod_secu_bore = det.num_secu
     ORDER BY det.cod_pais,
              det.cod_cabe_bore,
              tmp.cod_secu_bore;

  TYPE interfazrec IS RECORD(
    cod_pais                int_rec_linea_borec.cod_pais%TYPE,
    cod_cabe_bore           int_rec_linea_borec.cod_cabe_bore%TYPE,
    cod_line_bore           int_rec_linea_borec.cod_line_bore%TYPE,
    num_secu                int_rec_linea_borec.num_secu%TYPE,
    care_oid_cabe_recl      int_rec_linea_borec.care_oid_cabe_recl%TYPE,
    soca_oid_soli_cabe      int_rec_linea_borec.soca_oid_soli_cabe%TYPE,
    opre_oid_oper_recl      int_rec_linea_borec.opre_oid_oper_recl%TYPE,
    prod_oid_prod           int_rec_linea_borec.prod_oid_prod%TYPE,
    cod_prod                int_rec_linea_borec.cod_prod%TYPE,
    mafa_oid_matr_fact      int_rec_linea_borec.mafa_oid_matr_fact%TYPE,
    tofe_oid_tipo_ofer      int_rec_linea_borec.tofe_oid_tipo_ofer%TYPE,
    copa_oid_para_gral      int_rec_linea_borec.copa_oid_para_gral%TYPE,
    panp_oid_para_nive_prem int_rec_linea_borec.panp_oid_para_nive_prem%TYPE,
    lopa_oid_lote_prem_arti int_rec_linea_borec.lopa_oid_lote_prem_arti%TYPE,
    num_unid_recl           int_rec_linea_borec.num_unid_recl%TYPE,
    num_unid_reco           int_rec_linea_borec.num_unid_reco%TYPE,
    obse_bore               int_rec_linea_borec.obse_bore%TYPE,
    prod_oid_prod_disc      int_rec_linea_borec.prod_oid_prod_disc%TYPE,
    cod_prod_disc           int_rec_linea_borec.cod_prod_disc%TYPE,
    ind_disc                int_rec_linea_borec.ind_disc%TYPE,
    lor_oid_line_oper_recl  int_rec_linea_borec.lor_oid_line_oper_recl%TYPE,
    ind_proc_alma_fisi      int_rec_linea_borec.ind_proc_alma_fisi%TYPE,
    ind_envi_xero           int_rec_linea_borec.ind_envi_xero%TYPE,
    ind_envi_yobe           int_rec_linea_borec.ind_envi_yobe%TYPE,
    ind_regr_yobe           int_rec_linea_borec.ind_regr_yobe%TYPE,
    ind_ocs_proc            int_rec_linea_borec.ind_ocs_proc%TYPE,
    cod_oper_homol          int_rec_linea_borec.cod_oper_homol%TYPE,
    val_prec                int_rec_linea_borec.val_prec%TYPE,
    val_prec_cont           int_rec_linea_borec.val_prec_cont%TYPE,
    num_lote_envi           int_rec_linea_borec.num_lote_envi%TYPE,
    num_lote_devu           int_rec_linea_borec.num_lote_devu%TYPE,
    des_oper                int_rec_linea_borec.des_oper%TYPE,
    cod_oper                int_rec_linea_borec.cod_oper%TYPE,
    cod_anti                int_rec_linea_borec.cod_anti%TYPE,
    cod_regi_deta_bore_arch int_rec_detal_borec_tempo.cod_regi_deta_bore%TYPE,
    cod_prod_disc_arch      int_rec_detal_borec_tempo.cod_prod_disc%TYPE,
    val_unid_arch           int_rec_detal_borec_tempo.val_unid%TYPE,
    num_reco                int_rec_cabec_borec.num_reco%TYPE,
    esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;

  lscodigoproductoddisc  VARCHAR2(20);
  lnoidproductodisc      NUMBER(12);
  lscodigoregistroant    VARCHAR2(100);
  lscodigoregistroact    VARCHAR2(100);
  lscodigocabeceraant    VARCHAR2(100);
  lscodigocabeceraact    VARCHAR2(100);
  lnnumunidadesrecototal NUMBER;
  lnnumunidadesreco      NUMBER;

  lnidestadogestion NUMBER;

  ln_lot2           NUMBER;
  ln_lote           NUMBER;
  ln_ind2           NUMBER;
  ln_ind1           NUMBER;

  lnidestacerrado int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;

BEGIN

  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
  --INICIALIZANDO VALORES
  lscodigoregistroant    := NULL;
  lscodigocabeceraant    := NULL;
  lnnumunidadesrecototal := 0;

  lnidestadogestion := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                  'GE');

  OPEN c_interfaz(lnidestacerrado);
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP

        lscodigocabeceraact := interfazrecord(x).cod_cabe_bore; --OBTENGO EL CODIGO DE CABECERA ACTUAL

        --CADA VEZ QUE SE TERMINE DE LEER UNA CABECERA ACTUALIZO EL tOTAL DE UNIDADES RECOGIDAS
        IF lscodigocabeceraant IS NOT NULL AND lscodigocabeceraact <> lscodigocabeceraant THEN

          UPDATE int_rec_cabec_borec
             SET num_tota_unid_reco = lnnumunidadesrecototal
           WHERE cod_pais = interfazrecord(x).cod_pais
             AND cod_cabe_bore = lscodigocabeceraant;

          int_pr_calcu_abono_cargo_consu(interfazrecord(x).cod_pais,lscodigocabeceraant);

          lnnumunidadesrecototal := 0;

        END IF;

        lnnumunidadesreco := to_number(nvl(TRIM(interfazrecord(x).val_unid_arch),
                                           0)); /*UNIDADES RECOGIDAS DE ARCHIVO*/

        --Acumulo el numero de unidades;
        lnnumunidadesrecototal := lnnumunidadesrecototal + lnnumunidadesreco;

        --Obtenemo el codigo y Oid Del producto Discrepante
        lscodigoproductoddisc := TRIM(interfazrecord(x).cod_prod_disc_arch);
        lnoidproductodisc     := int_pkg_recla.gen_fn_devue_oid_produ_discr(lscodigoproductoddisc);

        --Identifico el codigo de registro
        lscodigoregistroact := interfazrecord(x).cod_pais || interfazrecord(x).cod_cabe_bore || interfazrecord(x).num_secu || interfazrecord(x).cod_prod;

        --Si el Codigo del producto discrepante del Detalle es nulo se actualiza los valores

        IF (lscodigoproductoddisc IS NULL) THEN

          IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
            UPDATE int_rec_linea_borec
               SET ind_regr_yobe      = 1, --IND_REGR_YOB
                   num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                   cod_prod_disc      = lscodigoproductoddisc,
                   num_unid_reco      = lnnumunidadesreco,
                   prod_oid_prod_disc = lnoidproductodisc
             WHERE cod_pais = interfazrecord(x).cod_pais
               AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
               AND cod_line_bore = interfazrecord(x).cod_line_bore;
          ELSE
            UPDATE int_rec_linea_borec
               SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                               1,
                                               1,
                                               ind_regr_yobe), --IND_REGR_YOB
                   num_lote_devu      = decode(interfazrecord(x).num_reco,
                                               1,
                                               interfazrecord(x).cod_regi_deta_bore_arch,
                                               num_lote_devu), --LOTE
                   ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                               2,
                                               1,
                                               ind_regr_yob2), --IND_REGR_YOB
                   num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                               2,
                                               interfazrecord(x).cod_regi_deta_bore_arch,
                                               num_lote_dev2), --LOTE
                   cod_prod_disc      = lscodigoproductoddisc,
                   num_unid_reco      = lnnumunidadesreco,
                   prod_oid_prod_disc = lnoidproductodisc
             WHERE cod_pais = interfazrecord(x).cod_pais
               AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
               AND cod_line_bore = interfazrecord(x).cod_line_bore;
          END IF;

        ELSE

          IF (lnnumunidadesreco < interfazrecord(x).num_unid_recl) THEN
            --Si es el primer registro
            IF lscodigoregistroant IS NULL OR lscodigoregistroant <> lscodigoregistroact THEN
              IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
                UPDATE int_rec_linea_borec
                   SET ind_regr_yobe      = 1, --IND_REGR_YOB
                       num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                       ind_disc           = 1,
                       cod_prod_disc      = lscodigoproductoddisc,
                       prod_oid_prod_disc = lnoidproductodisc,
                       num_unid_reco      = lnnumunidadesreco
                 WHERE cod_pais = interfazrecord(x).cod_pais
                   AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                   AND cod_line_bore = interfazrecord(x).cod_line_bore;
              ELSE
                UPDATE int_rec_linea_borec
                   SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                                   1,
                                                   1,
                                                   ind_regr_yobe), --IND_REGR_YOB
                       num_lote_devu      = decode(interfazrecord(x).num_reco,
                                                   1,
                                                   interfazrecord(x).cod_regi_deta_bore_arch,
                                                   num_lote_devu), --LOTE
                       ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                                   2,
                                                   1,
                                                   ind_regr_yob2), --IND_REGR_YOB
                       num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                                   2,
                                                   interfazrecord(x).cod_regi_deta_bore_arch,
                                                   num_lote_dev2), --LOTE
                       ind_disc           = 1,
                       cod_prod_disc      = lscodigoproductoddisc,
                       prod_oid_prod_disc = lnoidproductodisc,
                       num_unid_reco      = lnnumunidadesreco
                 WHERE cod_pais = interfazrecord(x).cod_pais
                   AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                   AND cod_line_bore = interfazrecord(x).cod_line_bore;
              END IF;

            ELSE

                ln_lot2 := NULL;
                ln_lote := NULL;
                ln_ind2 := NULL;
                ln_ind1 := 1;

              IF (interfazrecord(x).num_reco = 2) THEN

                    IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
                       ln_lot2 := NULL;
                       ln_lote := interfazrecord(x).cod_regi_deta_bore_arch;
                       ln_ind2 := NULL;
                    ELSE
                       ln_lot2 := interfazrecord(x).cod_regi_deta_bore_arch;
                       ln_lote := interfazrecord(x).num_lote_devu;
                       ln_ind2 := 1;
                    END IF;

              ELSE
                   ln_lot2 := NULL;
                   ln_lote := interfazrecord(x).cod_regi_deta_bore_arch;
                   ln_ind2 := NULL;
              END IF;


              INSERT INTO int_rec_linea_borec
                (cod_pais,
                 cod_cabe_bore,
                 cod_line_bore,
                 num_secu,
                 care_oid_cabe_recl,
                 soca_oid_soli_cabe,
                 opre_oid_oper_recl,
                 prod_oid_prod,
                 cod_prod,
                 mafa_oid_matr_fact,
                 tofe_oid_tipo_ofer,
                 copa_oid_para_gral,
                 panp_oid_para_nive_prem,
                 lopa_oid_lote_prem_arti,
                 num_unid_recl,
                 num_unid_reco,
                 obse_bore,
                 prod_oid_prod_disc,
                 cod_prod_disc,
                 ind_disc,
                 lor_oid_line_oper_recl,
                 ind_proc_alma_fisi,
                 ind_envi_xero,
                 ind_envi_yobe,
                 ind_regr_yobe,
                 ind_ocs_proc,
                 cod_oper_homol,
                 val_prec,
                 val_prec_cont,
                 num_lote_envi,
                 num_lote_devu,
                 des_oper,
                 cod_oper,
                 cod_anti,
                 refe_cod_line_bore,
                 ind_regr_yob2,
                 num_lote_dev2)
              VALUES
                (interfazrecord(x).cod_pais,
                 interfazrecord(x).cod_cabe_bore,
                 int_rlb_seq.NEXTVAL,
                 int_pkg_recla.gen_fn_sigse_linea_borec(interfazrecord(x).cod_pais,
                                                        interfazrecord(x).cod_cabe_bore),
                 interfazrecord(x).care_oid_cabe_recl,
                 interfazrecord(x).soca_oid_soli_cabe,
                 interfazrecord(x).opre_oid_oper_recl,
                 interfazrecord(x).prod_oid_prod,
                 interfazrecord(x).cod_prod,
                 interfazrecord(x).mafa_oid_matr_fact,
                 interfazrecord(x).tofe_oid_tipo_ofer,
                 interfazrecord(x).copa_oid_para_gral,
                 interfazrecord(x).panp_oid_para_nive_prem,
                 interfazrecord(x).lopa_oid_lote_prem_arti,
                 interfazrecord(x).num_unid_recl,
                 lnnumunidadesreco,
                 interfazrecord(x).obse_bore,
                 lnoidproductodisc,
                 lscodigoproductoddisc,
                 1,
                 interfazrecord(x).lor_oid_line_oper_recl,
                 interfazrecord(x).ind_proc_alma_fisi,
                 interfazrecord(x).ind_envi_xero,
                 interfazrecord(x).ind_envi_yobe,
                 ln_ind1,
                 interfazrecord(x).ind_ocs_proc,
                 interfazrecord(x).cod_oper_homol,
                 0, --INTERFAZRECORD(X).VAL_PREC,
                 0, --INTERFAZRECORD(X).VAL_PREC_CONT,
                 interfazrecord(x).num_lote_envi,
                 ln_lote,--interfazrecord(x).cod_regi_deta_bore_arch,
                 interfazrecord(x).des_oper,
                 interfazrecord(x).cod_oper,
                 interfazrecord(x).cod_anti,
                 interfazrecord(x).cod_line_bore,
                 ln_ind2,
                 ln_lot2); --LINEA DE REFERENCIA

            END IF;

          ELSE
            IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
              UPDATE int_rec_linea_borec
                 SET ind_regr_yobe      = 1, --IND_REGR_YOB
                     num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                     cod_prod_disc      = lscodigoproductoddisc,
                     prod_oid_prod_disc = lnoidproductodisc,
                     num_unid_reco      = lnnumunidadesreco,
                     ind_disc           = 1
               WHERE cod_pais = interfazrecord(x).cod_pais
                 AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                 AND cod_line_bore = interfazrecord(x).cod_line_bore;
            ELSE
              UPDATE int_rec_linea_borec
                 SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                                 1,
                                                 1,
                                                 ind_regr_yobe), --IND_REGR_YOB
                     num_lote_devu      = decode(interfazrecord(x).num_reco,
                                                 1,
                                                 interfazrecord(x).cod_regi_deta_bore_arch,
                                                 num_lote_devu), --LOTE
                     ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                                 2,
                                                 1,
                                                 ind_regr_yob2), --IND_REGR_YOB
                     num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                                 2,
                                                 interfazrecord(x).cod_regi_deta_bore_arch,
                                                 num_lote_dev2), --LOTE
                     cod_prod_disc      = lscodigoproductoddisc,
                     prod_oid_prod_disc = lnoidproductodisc,
                     num_unid_reco      = lnnumunidadesreco,
                     ind_disc           = 1
               WHERE cod_pais = interfazrecord(x).cod_pais
                 AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                 AND cod_line_bore = interfazrecord(x).cod_line_bore;
            END IF;
          END IF;

        END IF;
        lscodigoregistroant := lscodigoregistroact;
        lscodigocabeceraant := lscodigocabeceraact;

      END LOOP;

      IF lscodigocabeceraant IS NOT NULL AND lscodigocabeceraact = lscodigocabeceraant THEN

        UPDATE int_rec_cabec_borec
           SET num_tota_unid_reco = lnnumunidadesrecototal
         WHERE cod_pais = pscodigopais
           AND cod_cabe_bore = lscodigocabeceraant;

        lnnumunidadesrecototal := 0;

      END IF;

    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

EXCEPTION

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_RECEP_BOREC_DETAL: ' || ls_sqlerrm);

END int_pr_rec_recep_borec_detal;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Cabeceras de Boleta de Recojo
Fecha Creacion    : 27/02/2008
Autor             : Jose A. Cairampoma
***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_cabec
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
) IS
  CURSOR c_interfaz(vnoidperiodo NUMBER, vnoidestadoingresada NUMBER, vnoidestadoengestion NUMBER, vnoidestadonoexitosa NUMBER, vnoidestadonoenviada NUMBER) IS
    SELECT DISTINCT TGEN.CODIGOPAIS,
                 TGEN.CODIGOCABECERA,
                 TGEN.CODIGOREGISTRO,
                 TGEN.NUMEROBOLETA,
                 TGEN.CODIGOCONSULTORA,
                 TGEN.FECHARECOJO,
                 TGEN.ESTADO,
                 TGEN.TOTALUNIDADES,
                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TGEN.CODIGOCONSULTORA, 'COD_REGI') as  region,
                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TGEN.CODIGOCONSULTORA, 'COD_ZONA') as zona,
                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TGEN.CODIGOCONSULTORA, 'COD_TERR') as territorio,
                 TGEN.NUMFACTURA,
                 TGEN.PERIODOPROCESO,
                 TGEN.NUM_RECO
	FROM
	(
	SELECT cb.cod_pais codigopais,
	           cb.cod_cabe_bore codigocabecera,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) codigoregistro,
	           cb.val_nume_bore numeroboleta,
	           mc.cod_clie codigoconsultora,
	           to_char(cb.fec_ingr, 'DDMMYY') fecharecojo,
	           cb.num_reco estado,
	           cb.num_tota_unid_recl totalunidades,
	           cb.val_nume_bole_desp numfactura,
	           cb.cod_peri_proc periodoproceso,
	           cb.num_reco
	      FROM int_rec_cabec_borec cb,
	           mae_clien           mc,
             int_rec_cierr_borec cie
	     WHERE EXISTS (SELECT 1
	              FROM int_rec_linea_borec d
	             WHERE d.cod_pais = cb.cod_pais
	               AND cb.cod_cabe_bore = d.cod_cabe_bore)
	       AND cb.cod_pais = pscodigopais
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoingresada
	       AND cb.esbo_oid_esta_bor2 IS NULL
	       AND cb.num_reco = 1
	       AND cb.ind_regr_yobe = 0
	       AND mc.oid_clie = cb.clie_oid_clie
	       AND cb.ind_envi_yobe = 0
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
	    UNION
	    SELECT cb.cod_pais codigopais,
	           cb.cod_cabe_bore codigocabecera,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) codigoregistro,
	           cb.val_nume_bore numeroboleta,
	           cb.cod_clie codigoconsultora,
	           to_char(cb.fec_ingr, 'DDMMYY') fecharecojo,
	           cb.num_reco estado,
	           (cb.num_tota_unid_recl - (SELECT NVL(SUM(NUM_UNID_ELIM),0)
	                                    FROM INT_REC_LINEA_BOREC
	                                    WHERE COD_CABE_BORE = cb.cod_cabe_bore)) AS totalunidades,
	           cb.val_nume_bole_desp numfactura,
	           cb.cod_peri_proc periodoproceso,
	           cb.num_reco
	      FROM int_rec_cabec_borec cb,
	           ped_solic_cabec     con,
             int_rec_cierr_borec cie
	     WHERE EXISTS (SELECT 1
	              FROM int_rec_linea_borec d
	             WHERE d.cod_pais = cb.cod_pais
	               AND cb.cod_cabe_bore = d.cod_cabe_bore)
	       AND cb.cod_pais = pscodigopais
	       AND cb.esbo_oid_esta_bor2 IN
	           (vnoidestadonoexitosa)
	       AND cb.num_reco = 2
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoengestion
	       AND cb.ind_envi_yobe = 1
	       AND cb.ind_envi_yob2 IS NULL
	       AND cb.ind_regr_yobe = 1
	       AND con.clie_oid_clie = cb.clie_oid_clie
	       AND con.perd_oid_peri = vnoidperiodo
	       AND con.grpr_oid_grup_proc = 5
	       AND con.soca_oid_soli_cabe IS NULL
	       AND con.tspa_oid_tipo_soli_pais = int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1')
         AND (select count(*) from ped_solic_Cabec x
              where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
              and x.TSPA_OID_TIPO_SOLI_PAIS <>
               (select tsp.oid_tipo_soli_pais
                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
	    UNION
	    SELECT cb.cod_pais codigopais,
	           cb.cod_cabe_bore codigocabecera,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) codigoregistro,
	           cb.val_nume_bore numeroboleta,
	           cb.cod_clie codigoconsultora,
	           to_char(cb.fec_ingr, 'DDMMYY') fecharecojo,
	           cb.num_reco, -- NUMERO DE RECOJO
	           (cb.num_tota_unid_recl - (SELECT NVL(SUM(NUM_UNID_ELIM),0)
	                                    FROM INT_REC_LINEA_BOREC
	                                    WHERE COD_CABE_BORE = cb.cod_cabe_bore)) AS totalunidades,
	           cb.val_nume_bole_desp numfactura,
	           cb.cod_peri_proc periodoproceso,
	           cb.num_reco
	      FROM int_rec_cabec_borec   cb,
             int_rec_cierr_borec cie
	     WHERE EXISTS (SELECT 1
	              FROM int_rec_linea_borec d
	             WHERE d.cod_pais = cb.cod_pais
	               AND cb.cod_cabe_bore = d.cod_cabe_bore)
	       AND cb.cod_pais = pscodigopais
	       AND cb.esbo_oid_esta_bor2 IN
	           (vnoidestadonoexitosa)
	       AND cb.num_reco = 2
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoengestion
	       AND cb.ind_envi_yobe = 1
	       AND cb.ind_envi_yob2 IS NULL
	       AND cb.ind_regr_yobe = 1
	       AND cb.clie_oid_clie NOT IN
	           (SELECT clie_oid_clie
	              FROM ped_solic_cabec con1
	             WHERE con1.grpr_oid_grup_proc = 5
	               AND con1.perd_oid_peri = vnoidperiodo
	               AND con1.soca_oid_soli_cabe IS NULL
	               AND con1.tspa_oid_tipo_soli_pais in (
	                   int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                 AND (select count(*) from ped_solic_Cabec x
                      where X.SOCA_OID_SOLI_CABE = con1.OID_SOLI_CABE
                      and x.TSPA_OID_TIPO_SOLI_PAIS <>
                       (select tsp.oid_tipo_soli_pais
                        from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                        where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                        and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
                     )
	       AND (
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_ZONA')  IN
	           (SELECT zzon_oid_zona
	              FROM fac_contr_cierr ci
	             WHERE ci.perd_oid_peri = vnoidperiodo
	               AND ci.tcie_oid_tipo_cier = 2)
             OR
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_REGI')  IN (
                SELECT ZORG_OID_REGI
	              FROM fac_contr_cierr ci
	             WHERE ci.perd_oid_peri = vnoidperiodo
	               AND ci.tcie_oid_tipo_cier = 1
             )
             )
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
	)  TGEN
	WHERE TGEN.TOTALUNIDADES > 0
	ORDER BY 9,10,5;

  TYPE interfazrec IS RECORD(
    codigopais       seg_pais.cod_pais%TYPE,
    codigocabecera   int_rec_cabec_borec.cod_cabe_bore%TYPE,
    codigoregistro   VARCHAR2(10),
    numeroboleta     int_rec_cabec_borec.val_nume_bore%TYPE,
    codigoconsultora int_rec_cabec_borec.cod_clie%TYPE,
    fecharecojo      VARCHAR2(7),
    estado           int_rec_cabec_borec.num_reco%TYPE,
    totalunidades    int_rec_cabec_borec.num_tota_unid_recl%TYPE,
    region           int_rec_cabec_borec.cod_regi%TYPE,
    zona             int_rec_cabec_borec.cod_zona%TYPE,
    territorio       int_rec_cabec_borec.cod_terr%TYPE,
    numfactura       int_rec_cabec_borec.val_nume_bole_desp%TYPE,
    periodoproceso   seg_perio_corpo.cod_peri%TYPE,
    numreco          int_rec_cabec_borec.num_reco%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;

  lslinea         VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);

  lnoidmarca   NUMBER;
  lnoidcanal   NUMBER;
  lnoidperiodo NUMBER;

  lnoidestadoingresada NUMBER;
  lnoidestadoengestion NUMBER;
  lnoidestadonoexitosa NUMBER;
  lnoidestadonoenviada NUMBER;

BEGIN

  /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /*Obteniendo Valores de las variables*/
  lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
  lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                             lnoidmarca,
                                                             lnoidcanal);

  lnoidestadoingresada := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'I'); --1
  lnoidestadoengestion := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'GE'); --1
  lnoidestadonoexitosa := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'NX');
  lnoidestadonoenviada := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'NE');

  /* Generando Archivo de Texto (Detalle) */

  OPEN c_interfaz(lnoidperiodo,
                  lnoidestadoingresada,
                  lnoidestadoengestion,
                  lnoidestadonoexitosa,
                  lnoidestadonoenviada);
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP
        lslinea := interfazrecord(x)
                  .codigoregistro || ';' || interfazrecord(x)
                  .numeroboleta || ';' || interfazrecord(x)
                  .codigoconsultora || ';' || interfazrecord(x)
                  .fecharecojo || ';' || interfazrecord(x)
                  .estado || ';' || interfazrecord(x)
                  .totalunidades || ';' || interfazrecord(x)
                  .region || ';' || interfazrecord(x)
                  .zona || ';' || interfazrecord(x)
                  .territorio || ';' || interfazrecord(x)
                  .numfactura || ';' || interfazrecord(x).periodoproceso;

        IF interfazrecord(x).numreco = 1 THEN

          UPDATE int_rec_cabec_borec
             SET ind_envi_yobe = 1,
                 num_lote_envi = interfazrecord(x).codigoregistro
           WHERE cod_pais = interfazrecord(x)
          .codigopais
             AND cod_cabe_bore = interfazrecord(x).codigocabecera;

        ELSE

          --SI NUM_RECO = 2 ENTONCES

          UPDATE int_rec_cabec_borec
             SET ind_envi_yob2 = 1,
                 num_lote_env2 = interfazrecord(x).codigoregistro,
                 fec_ing2      = (
                                    select fec_proc
                                    from bas_ctrl_fact
                                    where sta_camp = 0
                                    and ind_camp_act = 1
                                    and rownum=1
                                 )
           WHERE cod_pais = interfazrecord(x)
          .codigopais
             AND cod_cabe_bore = interfazrecord(x).codigocabecera;

        END IF;

        utl_file.put_line(v_handle, lslinea);
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  utl_file.fclose(v_handle);

  /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_BOREC_DETAL: ' ||
                            ls_sqlerrm);
END int_pr_rec_envia_borec_cabec;


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Lineas de Boleta de Recojo
Fecha Creacion    : 27/02/2008
Autor             : Jose A. Cairampoma
***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_detal
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
) IS
  CURSOR c_interfaz(vnoidperiodo NUMBER, vnoidestadoingresada NUMBER, vnoidestadoengestion NUMBER, vnoidestadonoexitosa NUMBER, vnoidestadonoenviada NUMBER) IS
    SELECT DISTINCT DGEN.CODIGOPAIS,
                DGEN.CODIGOCABECERA,
                DGEN.CODIGODETALLE,
                DGEN.IDREGISTRO,
                DGEN.NUMBOLETA,
                DGEN.codigoproducto,
                DGEN.codigoproddisc,
                DGEN.unidades,
                DGEN.periodoproceso,
                DGEN.tipooperacion,
                DGEN.secuencia,
                DGEN.codigounicoventa,
                DGEN.NUM_RECO
	FROM
	(
	SELECT lb.cod_pais codigopais,
	           lb.cod_cabe_bore codigocabecera,
	           lb.cod_line_bore codigodetalle,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) idregistro,
	           cb.val_nume_bore numboleta,
	           lb.cod_prod codigoproducto,
	           lb.cod_anti codigoproddisc,
	           lb.num_unid_recl-lb.Num_Unid_Elim unidades,
	           sp.cod_peri periodoproceso,
	           lb.des_oper tipooperacion,
	           lb.num_secu secuencia,
	           decode(lb.copa_oid_para_gral,
	                  NULL,
	                  int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
	                                                             lb.mafa_oid_matr_fact,
	                                                             lb.prod_oid_prod),
	                  int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
	                                                             lb.panp_oid_para_nive_prem,
	                                                             lb.copa_oid_para_gral,
	                                                             lb.prod_oid_prod)) codigounicoventa,
	           cb.num_reco
	      FROM int_rec_linea_borec lb,
	           int_rec_cabec_borec cb,
	           rec_cabec_recla     rc,
	           cra_perio           cra,
	           seg_perio_corpo     sp,
             int_rec_cierr_borec cie
	     WHERE lb.cod_pais = pscodigopais
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoingresada
	       AND cb.esbo_oid_esta_bor2 IS NULL
	       AND cb.num_reco = 1
	       AND lb.ind_regr_yobe = 0
	       AND cb.cod_cabe_bore = lb.cod_cabe_bore
	       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
	       AND cra.peri_oid_peri = sp.oid_peri
	       AND cra.oid_peri = rc.PERD_OID_PERI_DOCU_REFE
	       AND lb.ind_envi_yobe = 0
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
	    UNION
	    SELECT lb.cod_pais codigopais,
	           lb.cod_cabe_bore codigocabecera,
	           lb.cod_line_bore codigodetalle,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) idregistro,
	           cb.val_nume_bore numboleta,
	           lb.cod_prod codigoproducto,
	           lb.cod_anti codigoproddisc,
	           lb.num_unid_recl-lb.Num_Unid_Elim unidades,
	           sp.cod_peri periodoproceso,
	           lb.des_oper tipooperacion,
	           lb.num_secu secuencia,
	           decode(lb.copa_oid_para_gral,
	                  NULL,
	                  int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
	                                                             lb.mafa_oid_matr_fact,
	                                                             lb.prod_oid_prod),
	                  int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
	                                                             lb.panp_oid_para_nive_prem,
	                                                             lb.copa_oid_para_gral,
	                                                             lb.prod_oid_prod)) codigounicoventa,
	           cb.num_reco
	      FROM int_rec_linea_borec lb,
	           int_rec_cabec_borec cb,
	           rec_cabec_recla     rc,
	           cra_perio           cra,
	           seg_perio_corpo     sp,
	           ped_solic_cabec     con,
             int_rec_cierr_borec cie
	     WHERE lb.cod_pais = pscodigopais
	       AND cb.cod_cabe_bore = lb.cod_cabe_bore
	       AND cb.esbo_oid_esta_bor2 IN
	           (vnoidestadonoexitosa)
	       AND cb.num_reco = 2
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoengestion
	       AND lb.ind_envi_yobe = 1
	       AND lb.ind_envi_yob2 IS NULL
	       AND lb.ind_regr_yobe = 1
	       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
	       AND cra.peri_oid_peri = sp.oid_peri
	       AND cra.oid_peri = rc.PERD_OID_PERI_DOCU_REFE
	       AND con.clie_oid_clie = cb.clie_oid_clie
	       AND con.perd_oid_peri = vnoidperiodo
	       AND con.grpr_oid_grup_proc = 5
	       AND con.soca_oid_soli_cabe IS NULL
	       AND con.tspa_oid_tipo_soli_pais = int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1')
         AND (select count(*) from ped_solic_Cabec x
              where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
              and x.TSPA_OID_TIPO_SOLI_PAIS <>
               (select tsp.oid_tipo_soli_pais
                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
         AND cie.cod_peri = pscodigoperiodo
	    UNION
	    SELECT lb.cod_pais codigopais,
	           lb.cod_cabe_bore codigocabecera,
	           lb.cod_line_bore codigodetalle,
	           substr(psnumerolote, 1, 8) || substr(psnumerolote, 11, 2) idregistro,
	           cb.val_nume_bore numboleta,
	           lb.cod_prod codigoproducto,
	           lb.cod_anti codigoproddisc,
	           lb.num_unid_recl-lb.Num_Unid_Elim unidades,
	           sp.cod_peri periodoproceso,
	           lb.des_oper tipooperacion,
	           lb.num_secu secuencia,
	           decode(lb.copa_oid_para_gral,
	                  NULL,
	                  int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
	                                                             lb.mafa_oid_matr_fact,
	                                                             lb.prod_oid_prod),
	                  int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
	                                                             lb.panp_oid_para_nive_prem,
	                                                             lb.copa_oid_para_gral,
	                                                             lb.prod_oid_prod)) codigounicoventa,
	           cb.num_reco
	      FROM int_rec_linea_borec   lb,
	           int_rec_cabec_borec   cb,
	           rec_cabec_recla       rc,
	           cra_perio             cra,
	           seg_perio_corpo       sp,
             INT_REC_CIERR_BOREC cie
	     WHERE lb.cod_pais = pscodigopais
	       AND cb.esbo_oid_esta_bor2 IN
	           (vnoidestadonoexitosa)
	       AND cb.num_reco = 2
	       AND cb.esbo_oid_esta_bor1 = vnoidestadoengestion
	       AND lb.ind_envi_yobe = 1
	       AND lb.ind_envi_yob2 IS NULL
	       AND lb.ind_regr_yobe = 1
	       AND cb.cod_cabe_bore = lb.cod_cabe_bore
	       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
	       AND cra.peri_oid_peri = sp.oid_peri
	       AND cra.oid_peri = rc.PERD_OID_PERI_DOCU_REFE
	       AND cb.clie_oid_clie NOT IN
	           (SELECT clie_oid_clie
	              FROM ped_solic_cabec con1
	             WHERE con1.grpr_oid_grup_proc = 5
	               AND con1.perd_oid_peri = vnoidperiodo
	               AND con1.soca_oid_soli_cabe IS NULL
	               AND con1.tspa_oid_tipo_soli_pais in (
	                   int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                 AND (select count(*) from ped_solic_Cabec x
                      where X.SOCA_OID_SOLI_CABE = con1.OID_SOLI_CABE
                      and x.TSPA_OID_TIPO_SOLI_PAIS <>
                       (select tsp.oid_tipo_soli_pais
                        from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                        where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                        and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago
	                   )
	       AND (
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_ZONA')  IN
	           (SELECT zzon_oid_zona
	              FROM fac_contr_cierr ci
	             WHERE ci.perd_oid_peri = vnoidperiodo
	               AND ci.tcie_oid_tipo_cier = 2)
             OR
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_REGI')  IN (
                SELECT ZORG_OID_REGI
	              FROM fac_contr_cierr ci
	             WHERE ci.perd_oid_peri = vnoidperiodo
	               AND ci.tcie_oid_tipo_cier = 1
             )
             )
         AND cie.cod_peri = pscodigoperiodo
         AND cie.cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
         AND cie.cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
         AND cie.cod_pais = cb.cod_pais
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) >= trunc(cie.fec_cierr)
         AND (
            select fec_proc
            from bas_ctrl_fact
            where sta_camp = 0
            and ind_camp_act = 1
         ) <= trunc(cie.fec_cie2)
	) DGEN
	WHERE DGEN.UNIDADES>0
	ORDER BY DGEN.codigopais,
	         DGEN.codigocabecera,
	         DGEN.codigodetalle,
	         DGEN.secuencia;

  TYPE interfazrec IS RECORD(
    codigopais         seg_pais.cod_pais%TYPE,
    codigocabecera     int_rec_cabec_borec.cod_cabe_bore%TYPE,
    codigodetalle      int_rec_linea_borec.cod_line_bore%TYPE,
    codigoregistro     VARCHAR2(10),
    numeroboleta       int_rec_cabec_borec.val_nume_bore%TYPE,
    codigoproducto     mae_produ.cod_sap%TYPE,
    codigoproductobpcs mae_produ.cod_sap%TYPE,
    unidades           int_rec_linea_borec.num_unid_recl%TYPE,
    periodoreferencia  seg_perio_corpo.cod_peri%TYPE,
    tipooperacion      VARCHAR2(30),
    secuencia          int_rec_linea_borec.num_secu%TYPE,
    cuv                VARCHAR2(15),
    numreco            int_rec_cabec_borec.num_reco%TYPE

    );
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;

  lslinea         VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);

  lnoidmarca   NUMBER;
  lnoidcanal   NUMBER;
  lnoidperiodo NUMBER;

  lnoidestadoingresada NUMBER;
  lnoidestadoengestion NUMBER;
  lnoidestadonoexitosa NUMBER;
  lnoidestadonoenviada NUMBER;
BEGIN
  /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /*Obteniendo Valores de las variables*/
  lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
  lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                             lnoidmarca,
                                                             lnoidcanal);

  lnoidestadoingresada := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'I'); --1
  lnoidestadoengestion := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'GE'); --1
  lnoidestadonoexitosa := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'NX');
  lnoidestadonoenviada := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                     'NE');

  /* Generando Archivo de Texto (Detalle) */

  OPEN c_interfaz(lnoidperiodo,
                  lnoidestadoingresada,
                  lnoidestadoengestion,
                  lnoidestadonoexitosa,
                  lnoidestadonoenviada);
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP
        lslinea := interfazrecord(x)
                  .codigoregistro || ';' || interfazrecord(x)
                  .numeroboleta || ';' || interfazrecord(x)
                  .codigoproducto || ';' || interfazrecord(x)
                  .codigoproductobpcs || ';' || interfazrecord(x)
                  .unidades || ';' || interfazrecord(x)
                  .periodoreferencia || ';' || interfazrecord(x)
                  .tipooperacion || ';' || interfazrecord(x)
                  .secuencia || ';' || interfazrecord(x)
                  .cuv || ';' || interfazrecord(x).numreco;

        IF interfazrecord(x).numreco = 1 THEN

          UPDATE int_rec_linea_borec
             SET ind_envi_yobe = 1,
                 num_lote_envi = interfazrecord(x).codigoregistro
           WHERE cod_pais = interfazrecord(x)
          .codigopais
             AND cod_cabe_bore = interfazrecord(x)
          .codigocabecera
             AND cod_line_bore = interfazrecord(x).codigodetalle;

        ELSE

          --SI NUM_RECO = 2 ENTONCES

          UPDATE int_rec_linea_borec
             SET ind_envi_yob2 = 1,
                 num_lote_env2 = interfazrecord(x).codigoregistro
           WHERE cod_pais = interfazrecord(x)
          .codigopais
             AND cod_cabe_bore = interfazrecord(x).codigocabecera;

        END IF;

        utl_file.put_line(v_handle, lslinea);
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  utl_file.fclose(v_handle);

  /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_BOREC_DETAL: ' ||
                            ls_sqlerrm);
END int_pr_rec_envia_borec_detal;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Lineas de Boleta de Recojo
Fecha Creacion    : 27/02/2008
Autor             : Jose A. Cairampoma
***************************************************************************/

PROCEDURE int_pr_rec_envia_borec_ctrl
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2
) IS
  CURSOR c_interfaz IS
    SELECT substr(psnumerolote,
                  1,
                  8) || substr(psnumerolote,
                               11,
                               2) codigoreg,
           COUNT(*) numtotalreg,
           to_char(SYSDATE,
                   'DDMMYY') fechacarga,
           to_char(SYSDATE,
                   'HH:MI:SS') horacarga
      FROM int_rec_cabec_borec c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_envi_yobe = 1
       AND c.ind_envi_xero = 0
       AND c.ind_regr_yobe = 0;

  TYPE interfazrec IS RECORD(
    codigoreg   VARCHAR2(10),
    numtotalreg NUMBER,
    fechacarga  VARCHAR2(6),
    horacarga   VARCHAR2(8));
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;

  lslinea         VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);
  lbabrirutlfile  BOOLEAN;
BEGIN

  lbabrirutlfile := TRUE;
  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                             pscodigosistema,
                                             pscodigointerfaz,
                                             psnombrearchivo,
                                             lsdirtempo,
                                             lsnombrearchivo,
                                             v_handle);
      lbabrirutlfile := FALSE;
    END IF;

    IF interfazrecord.count > 0 THEN
      FOR x IN interfazrecord.first .. interfazrecord.last
      LOOP
        lslinea := interfazrecord(x).codigoreg || ';' ||
                   interfazrecord(x).numtotalreg || ';' ||
                   interfazrecord(x).fechacarga || ';' ||
                   interfazrecord(x).horacarga;

        utl_file.put_line(v_handle,
                          lslinea);
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  IF NOT lbabrirutlfile THEN
  utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  END IF;

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_BOREC_CTRL: ' || ls_sqlerrm);
END int_pr_rec_envia_borec_ctrl;


 /**************************************************************************
Descripcion        : D0evuelve el oid del No Motivo de la Boleta de Recojo si no encuetra
                     devuelve 0 si no existe
Fecha Creacion     : 28/02/2008
Autor              : Leonardo Lizana
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_MOTNO_BOREC(psCodPais         IN VARCHAR2,
                                        psCodMontRecoBore IN VARCHAR2)
    RETURN NUMBER IS
    ls_valor NUMBER;
  BEGIN

       SELECT A.OID_MOTN_RECO_BORE INTO ls_valor
       FROM INT_REC_MOTNO_RECOJ_BOREC A
       WHERE A.COD_PAIS = psCodPais
             AND A.COD_MOTN_RECO_BORE = psCodMontRecoBore;
  RETURN ls_valor;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_OID_REGIO_BYZON_TERRI: no existe oid ' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_OID_MOTNO_BOREC;

  /**************************************************************************
Descripcion        : D0evuelve el oid del Estado de la Boleta de Recojo si no encuetra
                     devuelve 0 si no existe
Fecha Creacion     : 28/02/2008
Autor              : Leonardo Lizana
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodPais         IN VARCHAR2,
                                        psCodMontRecoBore IN VARCHAR2)
    RETURN NUMBER IS
    ls_valor NUMBER;
  BEGIN

       SELECT A.OID_ESTA_BORE INTO LS_VALOR
       FROM INT_REC_ESTAD_BOREC A
       WHERE A.COD_PAIS = psCodPais
       AND A.COD_ESTA_BORE = psCodMontRecoBore;
  RETURN ls_valor;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN -1;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_OID_ESTAD_BOREC: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_OID_ESTAD_BOREC;

/**************************************************************************
Descripcion        : D0evuelve el ocodigo de periodo de la cabecera de Reclamo
Fecha Creacion     : 28/02/2008
Autor              : Jose A. Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_PERIO_CAREC(pnOidCabeceraReclamo NUMBER)
    RETURN VARCHAR2 IS
    lnCodigoPeriodo NUMBER;
  BEGIN

    SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(rec.perd_oid_peri_recl)
      INTO lncodigoperiodo
      FROM rec_cabec_recla rec
     WHERE rec.oid_cabe_recl = pnoidcabecerareclamo;

     RETURN lnCodigoPeriodo;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_COD_PERIO_CAREC: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_COD_PERIO_CAREC;


/**************************************************************************
Descripcion        : Devuelve el ocdigo de Venta de la matriz de facturacion
Fecha Creacion     : 28/02/2008
Autor              : Jose A. Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_VENTA_MFACT(pnOidTipoOper NUMBER,
                                        pnOidMatrizFacturacion NUMBER,
                                        pnProdOidProd NUMBER)
    RETURN VARCHAR2 IS
    lnCodigoVenta VARCHAR2(30);
  BEGIN

     SELECT md.val_codi_vent
       INTO lnCodigoVenta
       FROM pre_matri_factu mf,
            pre_ofert_detal md,
            pre_tipo_ofert  tof
      WHERE md.tofe_oid_tipo_ofer = pnOidTipoOper
        AND md.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
        AND mf.ofde_oid_deta_ofer = md.oid_deta_ofer
        AND mf.oid_matr_fact = pnOidMatrizFacturacion
        AND md.PROD_OID_PROD = pnProdOidProd;

     RETURN lnCodigoVenta;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_COD_VENTA_MFACT: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_COD_VENTA_MFACT;

/**************************************************************************
Descripcion        : Devuelve el ocdigo de Venta de la matriz de facturacion
Fecha Creacion     : 28/02/2008
Autor              : Jose A. Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_COD_VENTA_FICTI(pnOidLotePremArtic NUMBER,
                                        pnOidParamNivePrem NUMBER ,
                                        pnOidParamGeneral NUMBER,
                                        pnProdOidProd     NUMBER)
    RETURN VARCHAR2 IS
    lnCodigoVenta VARCHAR2(30);
  BEGIN

    /*SELECT al.cod_vent_fict
      INTO lncodigoventa
      FROM mae_produ             mp,
           inc_artic_lote        al,
           inc_lote_premi_artic  la,
           inc_premi_artic       pa,
           inc_param_nivel_premi pn,
           inc_param_gener_premi pg,
           inc_concu_param_gener cpg
     WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
       AND al.prod_oid_prod = mp.oid_prod
       AND la.prar_oid_prem_arti = pa.oid_prem_arti
       AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
       AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
       AND pg.copa_oid_para_gral = cpg.oid_para_gral
       AND pg.copa_oid_para_gral = pnoidparamgeneral
       AND pn.oid_para_nive_prem = pnoidparamniveprem
       AND la.oid_lote_prem_arti = pnoidlotepremartic
       AND al.PROD_OID_PROD = pnProdOidProd; */


     --- Se agrega la tabla de reemplazos
        select  cod_vent_fict  INTO lncodigoventa
        from(
            SELECT al.cod_vent_fict,pg.copa_oid_para_gral,pn.oid_para_nive_prem, la.oid_lote_prem_arti,al.PROD_OID_PROD
              FROM mae_produ             mp,
                   inc_artic_lote        al,
                   inc_lote_premi_artic  la,
                   inc_premi_artic       pa,
                   inc_param_nivel_premi pn,
                   inc_param_gener_premi pg,
                   inc_concu_param_gener cpg
             WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
               AND al.prod_oid_prod = mp.oid_prod
               AND la.prar_oid_prem_arti = pa.oid_prem_arti
               AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
               AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
               AND pg.copa_oid_para_gral = cpg.oid_para_gral
            union
            SELECT ral.cod_vent_fict,pg.copa_oid_para_gral,pn.oid_para_nive_prem,la.oid_lote_prem_arti  ,ral.PROD_OID_PROD
              FROM mae_produ             mp,
                   inc_artic_lote        al,
                   inc_lote_premi_artic  la,
                   inc_premi_artic       pa,
                   inc_param_nivel_premi pn,
                   inc_param_gener_premi pg,
                   inc_concu_param_gener cpg,
                   inc_reemp_artic_lote ral
             WHERE al.lopa_oid_lote_prem_arti = la.oid_lote_prem_arti
               AND la.prar_oid_prem_arti = pa.oid_prem_arti
               AND pa.panp_oid_para_nive_prem = pn.oid_para_nive_prem
               AND pn.pagp_oid_para_gene_prem = pg.oid_para_gene_prem
               AND pg.copa_oid_para_gral = cpg.oid_para_gral
               and al.oid_arti_lote = ral.arlo_oid_arti_lote
               AND ral.prod_oid_prod = mp.oid_prod
        ) premios
        where 1=1
        AND copa_oid_para_gral = pnoidparamgeneral
        AND oid_para_nive_prem = pnoidparamniveprem
        AND oid_lote_prem_arti = pnoidlotepremartic
        AND PROD_OID_PROD = pnProdOidProd
        and rownum = 1;

     RETURN lnCodigoVenta;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_COD_VENTA_FICTI: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_COD_VENTA_FICTI;

 /**************************************************************************
Descripcion        : D0evuelve el oid del Estado de la Boleta de Recojo si no encuetra
                     devuelve 0 si no existe
Fecha Creacion     : 28/02/2008
Autor              : Jose Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_GRUPO_PROCE(psCodGrupoProceso IN VARCHAR2)
    RETURN NUMBER IS
    lnOid NUMBER;
  BEGIN

    SELECT g.oid_grup_proc
      INTO lnoid
      FROM ped_grupo_proce g
     WHERE g.cod_grup_proc = pscodgrupoproceso;

  RETURN lnOid;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_OID_GRUPO_PROCE: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_OID_GRUPO_PROCE;


/**************************************************************************
Descripcion        : Devuelve el Oid del producto discrepante
                     devuelve NULL si no existe
Fecha Creacion     : 28/02/2008
Autor              : Jose Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_PRODU_DISCR(psCodigoProducto IN VARCHAR2)
    RETURN NUMBER IS
    lnOid NUMBER(12);
  BEGIN

    SELECT P.OID_PROD
      INTO LNOID
      FROM MAE_PRODU P
     WHERE P.COD_SAP = psCodigoProducto;

  RETURN lnOid;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_OID_PRODU_DISCR: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_OID_PRODU_DISCR;


/**************************************************************************
Descripcion        : Devuelve el numero de secuencia siguiente del detalle
                     de Boleta de Recojo.
Fecha Creacion     : 28/02/2008
Autor              : Jose Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_SIGSE_LINEA_BOREC(psCodigoPais IN VARCHAR2,
                                    psCodigoCabecera IN VARCHAR2)
    RETURN NUMBER IS
    lnNumSecuencia NUMBER;
  BEGIN


    SELECT NVL(MAX(NUM_SECU),0) + 1
      INTO lnNumSecuencia
      FROM INT_REC_LINEA_BOREC
     WHERE COD_PAIS = psCodigoPais
       AND COD_CABE_BORE = psCodigoCabecera;


  RETURN lnNumSecuencia;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN 1;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_SECUE_SIGUI_BOREC: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_SIGSE_LINEA_BOREC;
/**************************************************************************
Descripcion        : Devuelve el Oid Tipo de solicitud pasi por codigo
Fecha Creacion     : 28/02/2008
Autor              : Jose Cairampoma
***************************************************************************/
  FUNCTION GEN_FN_DEVUE_OID_TIPO_SOLPA(psCodigoTipoSolicitud VARCHAR2)
    RETURN NUMBER IS
    lnIdTipoSolictud NUMBER;
  BEGIN

   SELECT TSP.OID_TIPO_SOLI_PAIS
     INTO LNIDTIPOSOLICTUD
     FROM PED_TIPO_SOLIC_PAIS TSP, PED_TIPO_SOLIC TS
    WHERE TS.OID_TIPO_SOLI = TSP.TSOL_OID_TIPO_SOLI
      AND TS.COD_TIPO_SOLI = psCodigoTipoSolicitud
      AND ROWNUM = 1;

  RETURN lnIdTipoSolictud;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR GEN_FN_DEVUE_OID_TIPO_SOLPA: no existe oid' ||
                              ls_sqlerrm);
  END GEN_FN_DEVUE_OID_TIPO_SOLPA;
 /**************************************************************************
  Descripcion        : Enviar OCS Boletas de Recojo
  Fecha Creacion     : 13/03/2007
  Autor              : Leonardo Lizana
 ***************************************************************************/
PROCEDURE REC_PR_ENVIA_OCS_BOREC(psCodPais                     VARCHAR2,
                                 psFechaFacturacionInicial     VARCHAR2,
                                 psFechaFacturacionFinal       VARCHAR2,
                                 psValUsuario VARCHAR2)

IS

CURSOR c_BoletaRecojo IS
              SELECT CB.COD_PAIS,
                     CB.CLIE_OID_CLIE,
                     CB.COD_PERI_PROC,
                     MC.COD_CLIE,
                     SP.COD_PERI,
                     CB.VAL_NUME_BORE,
                     CB.FEC_INGR,
                     CB.NUM_RECO,
                     CB.NUM_TOTA_UNID_RECL,
                     CB.COD_REGI,
                     CB.COD_ZONA,
                     CB.COD_TERR,
                     CB.VAL_NUME_BOLE_DESP,
                     CB.COD_PERI_PROC CODPERIPROCES,
                     LB.COD_PROD,
                     LB.COD_ANTI,
                     LB.NUM_UNID_RECL,
                     LB.DES_OPER,
                     LB.NUM_SECU,
                     LB.VAL_PREC,
                     LB.VAL_PREC_CONT,
                     ZR.OID_REGI,
                     ZZ.OID_ZONA,
                     ZT.OID_TERR,
                     ZS.OID_SECC,
                     ZTD.OID_TERR_ADMI,
                     ZT.VEPO_OID_VALO_ESTR_GEOP,
                     CC.SBTI_OID_SUBT_CLIE,
                     CI.TDOC_OID_TIPO_DOCU,
                     MP.COD_IND_DENT_CAJA,
                      (SELECT MD.OID_DETA_OFER
                       FROM PRE_MATRI_FACTU MF,
                            PRE_OFERT_DETAL MD,
                            PRE_TIPO_OFERT TOF
                       WHERE MD.TOFE_OID_TIPO_OFER = LB.TOFE_OID_TIPO_OFER
                       AND MD.TOFE_OID_TIPO_OFER = TOF.OID_TIPO_OFER
                       AND MF.OFDE_OID_DETA_OFER = MD.OID_DETA_OFER
                       AND MF.OID_MATR_FACT = LB.MAFA_OID_MATR_FACT
                       ) OID_DETA_OFER, -- CODIGO DE VENTA DE MATRIZ DE FACTURACION,
                      (SELECT MD.VAL_CODI_VENT
                       FROM PRE_MATRI_FACTU MF,
                            PRE_OFERT_DETAL MD,
                            PRE_TIPO_OFERT TOF
                       WHERE MD.TOFE_OID_TIPO_OFER = LB.TOFE_OID_TIPO_OFER
                       AND MD.TOFE_OID_TIPO_OFER = TOF.OID_TIPO_OFER
                       AND MF.OFDE_OID_DETA_OFER = MD.OID_DETA_OFER
                       AND MF.OID_MATR_FACT = LB.MAFA_OID_MATR_FACT
                       ) VAL_CODI_VENT, -- CODIGO DE VENTA DE MATRIZ DE FACTURACION
                     LB.PROD_OID_PROD,
                     LB.SOCA_OID_SOLI_CABE,
                     MDIR.OID_CLIE_DIRE


                FROM INT_REC_LINEA_BOREC LB,
                     INT_REC_CABEC_BOREC CB,
                     MAE_CLIEN             MC,
                     REC_CABEC_RECLA       RC,
                     CRA_PERIO             CRA,
                     SEG_PERIO_CORPO       SP,
                     ZON_REGIO             ZR,
                     ZON_ZONA              ZZ,
                     ZON_SECCI             ZS,
                     ZON_TERRI             ZT,
                     ZON_TERRI_ADMIN       ZTD,
                     MAE_CLIEN_TIPO_SUBTI  CC,
                     MAE_CLIEN_IDENT       CI,
                     ZON_VALOR_ESTRU_GEOPO GE,
                     MAE_PRODU             MP,
                     MAE_CLIEN_DIREC MDIR
               WHERE CB.COD_PAIS = psCodPais
                 AND CB.IND_ENVI_YOBE = 1
                 AND CB.IND_OCS_PROC = 'F'
                 AND LB.COD_PAIS = psCodPais
                 AND LB.IND_ENVI_YOBE = 1
                 AND LB.IND_OCS_PROC = 'F'
                 AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                 AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                 AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                 AND CRA.PERI_OID_PERI = SP.OID_PERI
                 AND CRA.OID_PERI = RC.PERD_OID_PERI_RECL
                 AND ZR.OID_REGI = ZZ.ZORG_OID_REGI
                 AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
                 AND ZTD.ZSCC_OID_SECC = ZS.OID_SECC
                 AND ZTD.TERR_OID_TERR = ZT.OID_TERR
                 AND ZS.IND_ACTI = 1
                 AND ZZ.IND_ACTI = 1
                 AND ZR.IND_ACTI = 1
                 AND ZTD.IND_BORR = 0 ------ SE AGREGO
                 AND GE.IND_ACTI = 1 ------- SE AGREGO
                 AND GE.IND_BORR = 0  ------- SE AGREGO
                 AND CB.ZTAD_OID_TERRI_ADMIN = ZTD.OID_TERR_ADMI
                 AND GE.OID_VALO_ESTR_GEOP = ZT.VEPO_OID_VALO_ESTR_GEOP
                 AND CC.CLIE_OID_CLIE = CB.CLIE_OID_CLIE
                 AND CI.CLIE_OID_CLIE = CB.CLIE_OID_CLIE
                 AND MP.OID_PROD = LB.PROD_OID_PROD
                 AND CB.FEC_INGR>=TO_DATE(psFechaFacturacionInicial,'DD/MM/YYYY')
                 AND TO_DATE(CB.FEC_INGR,'DD/MM/YYYY')<=TO_DATE(psFechaFacturacionFinal,'DD/MM/YYYY')
                  ------- SE AGREGO
                 AND CI.TDOC_OID_TIPO_DOCU IN (SELECT TDOC_OID_TIPO_DOCU FROM MAE_CLIEN_IDENT WHERE clie_oid_clie = CI.CLIE_OID_CLIE and ROWNUM = 1)
                 AND MDIR.CLIE_OID_CLIE = MC.OID_CLIE
                 AND MDIR.IND_ELIM = 0
               ORDER BY CB.COD_REGI, CB.COD_ZONA, MC.COD_CLIE;

boletaRecojo  c_BoletaRecojo%ROWTYPE;


lv_val_tasa_impu PED_TASA_IMPUE.Val_Tasa_Impu%TYPE;
lv_oid_tipo_desp PED_TIPO_DESPA.Oid_Tipo_Desp%TYPE;
lv_almc_oid_alma      		 PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA			  %TYPE;
lv_fopa_oid_form_pago      PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO	  %TYPE;
lv_ind_perm_unio           PED_TIPO_SOLIC_PAIS.IND_PERM_UNIO			  %TYPE;
lv_mone_oid_mone           PED_TIPO_SOLIC_PAIS.MONE_OID_MONE			  %TYPE;
lv_oid_tipo_soli_pais      PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS	  %TYPE;
lv_pais_oid_pais           PED_TIPO_SOLIC_PAIS.PAIS_OID_PAIS			  %TYPE;
lv_soci_oid_soci           PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI        %TYPE;
lv_tido_oid_tipo_docu      PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU   %TYPE;
lv_tsol_oid_tipo_cons      PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS   %TYPE;
lv_tsol_oid_tipo_soli      PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI   %TYPE;
lv_acce_oid_acce           PED_TIPO_SOLIC.ACCE_OID_ACCE             %TYPE;
lv_clso_oid_clas_soli      PED_TIPO_SOLIC.CLSO_OID_CLAS_SOLI        %TYPE;
lv_cod_grup_soli           PED_TIPO_SOLIC.COD_GRUP_SOLI             %TYPE;
lv_cod_tipo_soli           PED_TIPO_SOLIC.COD_TIPO_SOLI             %TYPE;
lv_ind_cons                PED_TIPO_SOLIC.IND_CONS                  %TYPE;
lv_marc_oid_marc           PED_TIPO_SOLIC.MARC_OID_MARC             %TYPE;
lv_oid_tipo_soli           PED_TIPO_SOLIC.OID_TIPO_SOLI             %TYPE;
lv_sbac_oid_sbac           PED_TIPO_SOLIC.SBAC_OID_SBAC             %TYPE;
lv_ticl_oid_tipo_clie      PED_TIPO_SOLIC.TICL_OID_TIPO_CLIE        %TYPE;
lv_cod_clas_soli           PED_CLASE_SOLIC.COD_CLAS_SOLI            %TYPE;
lv_ind_orde_comp           PED_CLASE_SOLIC.IND_ORDE_COMP            %TYPE;
lv_oid_clas_soli           PED_CLASE_SOLIC.OID_CLAS_SOLI            %TYPE;

lv_nume_bore               INT_REC_CABEC_BOREC.VAL_NUME_BORE        %TYPE;
lv_aux_nume_bore           INT_REC_CABEC_BOREC.VAL_NUME_BORE        %TYPE :='-1';
lv_cod_posi                NUMBER(10);
lv_oidTasaImpuesto         PED_TASA_IMPUE.OID_TASA_IMPU%TYPE;

BEGIN

        /* QUERY DE INFORMACION DEL TIPO DE SOLICITUD*/
        SELECT TSP.ALMC_OID_ALMA,
               TSP.FOPA_OID_FORM_PAGO,
               TSP.IND_PERM_UNIO,
               TSP.MONE_OID_MONE,
               TSP.OID_TIPO_SOLI_PAIS,
               TSP.PAIS_OID_PAIS,
               TSP.SOCI_OID_SOCI,
               TSP.TIDO_OID_TIPO_DOCU,
               TSP.TSOL_OID_TIPO_CONS,
               TSP.TSOL_OID_TIPO_SOLI,
               TS.ACCE_OID_ACCE,
               TS.CLSO_OID_CLAS_SOLI,
               TS.COD_GRUP_SOLI,
               TS.COD_TIPO_SOLI,
               TS.IND_CONS,
               TS.MARC_OID_MARC,
               TS.OID_TIPO_SOLI,
               TS.SBAC_OID_SBAC,
               TS.TICL_OID_TIPO_CLIE,
               CS.COD_CLAS_SOLI,
               CS.IND_ORDE_COMP,
               CS.OID_CLAS_SOLI
          INTO lv_almc_oid_alma,
               lv_fopa_oid_form_pago,
               lv_ind_perm_unio,
               lv_mone_oid_mone,
               lv_oid_tipo_soli_pais,
               lv_pais_oid_pais,
               lv_soci_oid_soci,
               lv_tido_oid_tipo_docu,
               lv_tsol_oid_tipo_cons,
               lv_tsol_oid_tipo_soli,
               lv_acce_oid_acce,
               lv_clso_oid_clas_soli,
               lv_cod_grup_soli,
               lv_cod_tipo_soli,
               lv_ind_cons,
               lv_marc_oid_marc,
               lv_oid_tipo_soli,
               lv_sbac_oid_sbac,
               lv_ticl_oid_tipo_clie,
               lv_cod_clas_soli,
               lv_ind_orde_comp,
               lv_oid_clas_soli
          FROM PED_TIPO_SOLIC_PAIS TSP
               , PED_TIPO_SOLIC TS
               , PED_CLASE_SOLIC CS
         WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
           AND TS.CLSO_OID_CLAS_SOLI = CS.OID_CLAS_SOLI
           AND TS.COD_TIPO_SOLI = 'SC08';---CAMBIAARRR....FALATA DEFINIR.......!!!!!!!!!!!!!!

        /* OBTENER PORCENTAJE DE IMPUESTO */
        SELECT S.VAL_TASA_IMPU, S.OID_TASA_IMPU INTO lv_val_tasa_impu, lv_oidTasaImpuesto
          FROM PED_TASA_IMPUE S, SEG_PAIS P
         WHERE S.VAL_INDI_IMPU = 'IGV'
           AND S.PAIS_OID_PAIS = P.OID_PAIS
           AND P.COD_PAIS = psCodPais;

        /* OBTENER TIPO DESPACHO*/
        SELECT OID_TIPO_DESP INTO lv_oid_tipo_desp
          FROM PED_TIPO_DESPA, SEG_PAIS
         WHERE COD_TIPO_DESP = 'NR'
           AND PAIS_OID_PAIS = OID_PAIS
           AND COD_PAIS = psCodPais;


 OPEN c_BoletaRecojo;
   LOOP
       FETCH c_BoletaRecojo INTO boletaRecojo;
       EXIT WHEN c_BoletaRecojo%NOTFOUND;
       lv_nume_bore:= boletaRecojo.VAL_NUME_BORE;
       IF (lv_nume_bore <> lv_aux_nume_bore) THEN
            INSERT INTO PED_SOLIC_CABEC
                (OID_SOLI_CABE,
                FEC_PROG_FACT,
                FEC_FACT,
                NUM_CLIEN,
                VAL_GRUP_REVE,
                TSPA_OID_TIPO_SOLI_PAIS,
                MONE_OID_MONE,
                TIDS_OID_TIPO_DESP,
                ALMC_OID_ALMA,
                MODU_OID_MODU,
                TICL_OID_TIPO_CLIE,
                TAIM_OID_TASA_IMPU,
                PERD_OID_PERI,
                SOCA_OID_SOLI_CABE,
                CLIE_OID_CLIE,
                CLIE_OID_CLIE_RECE_FACT,
                CLIE_OID_CLIE_PAGA,
                CLIE_OID_CLIE_DEST,
                CLDI_OID_CLIE_DIRE,
                TDOC_OID_TIPO_DOCU,
                SOCI_OID_SOCI,
                SBAC_OID_SBAC,
                TERR_OID_TERR,
                ZZON_OID_ZONA,
                IND_ESTA,
                IND_IMPR,
                IND_EXEN_FLET,
                VAL_NUME_SOLI,
                VAL_USUA,
                VAL_TASA_IMPU,
                FEC_CRON,
                IND_PERM_UNIO_SOL,
                IND_GENE_CC,
                IND_APLI_MANU,
                VAL_TIPO_CAMB,
                NUM_DOCU_CONT_INTE,
                NUM_DOCU_ORIG,
                VAL_LOTE_REPO_FALT,
                FEC_REPO_FALT,
                VAL_BASE_FLET_LOCA,
                VAL_IMPO_FLET_LOCA,
                VAL_IMPO_FLET_TOTA_LOCA,
                VAL_IMPO_FLET_SIN_IMPU_TOTA,
                VAL_RECA_FLET_LOCA,
                VAL_OTRO_RECA_LOCA,
                VAL_TOTA_PAGA_LOCA,
                VAL_PREC_CATA_TOTA_LOCA,
                VAL_PREC_CATA_SIN_IMPU_TOTA,
                VAL_PREC_FACT_TOTA_LOCA,
                VAL_IMPO_IMPU_TOTA_LOCA,
                VAL_IMPO_DESC_1_TOTA_LOCA,
                VAL_IMPO_DESC_1_TOTA_DOCU,
                VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
                VAL_IMPO_DESC_3_TOTA_DOCU,
                VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
                VAL_IMPO_DESC_TOTA_LOCA,
                VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
                VAL_IMPO_REDO_LOCA,
                VAL_BASE_FLET_DOCU,
                VAL_IMPO_FLET_DOCU,
                VAL_IMPO_DESC_TOTA_DOCU,
                VAL_IMPO_FLET_SIN_IMPU_DOCU,
                VAL_RECA_FLET_DOCU,
                VAL_OTRO_RECA_DOCU,
                VAL_TOTA_FLET_DOCU,
                VAL_IMPO_FLET_TOTA_DOCU,
                VAL_TOTA_FLET_LOCA,
                VAL_TOTA_PAGA_DOCU,
                VAL_PREC_CATA_TOTA_DOCU,
                VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
                VAL_PREC_CONT_TOTA_LOCA,
                VAL_PREC_CONT_SIN_IMPU_TOTA,
                VAL_PREC_CONT_SIN_IMPU_TOTA_1,
                VAL_PREC_FACT_TOTA_DOCU,
                VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
                VAL_PREC_NETO_TOTA_DOCU,
                VAL_PREC_NETO_TOTA_LOCA,
                VAL_IMPO_IMPU_TOTA_DOCU,
                VAL_IMPO_REDO_DOCU,
                VAL_IMPO_REDO_CONS_LOCA,
                VAL_IMPO_REDO_CONS_DOCU,
                VAL_UNID_DEMA_REAL_TOTA,
                NUM_UNID_POR_ATEN_TOTA,
                NUM_UNID_ATEN_TOTA,
                IND_OC,
                IND_PEDI_PRUE,
                IND_TS_NO_CONSO,
                VAL_GLOS_OBSE,
                VAL_OBSE_REVI,
                NUM_PREM,
                VAL_IMPO_DESC_3_TOTA_LOCA,
                VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
                PAIS_OID_PAIS,
                TIDO_OID_TIPO_DOCU,
                VEPO_OID_VALO_ESTR_GEOP,
                RECQ_OID_RESU_CHEQ,
                ESSO_OID_ESTA_SOLI,
                COPA_OID_PARA_GENE,
                GRPR_OID_GRUP_PROC,
                SBTI_OID_SUBT_CLIE,
                ACFI_OID_ACCE_FISI,
                TSPA_OID_TIPO_SOLI_PAIS_CONS,
                FOPA_OID_FORM_PAGO,
                CLIE_OID_CONS_ASOC,
                ESPE_OID_ESTA_PEDI,
                CLSO_OID_CLAS_SOLI,
                ZTAD_OID_TERR_ADMI,
                INRE_OID_INDI_REVI,
                OPER_OID_OPER,
                PROC_OID_PROC,
                SOCA_OID_DOCU_REFE,
                TCCL_OID_TCCL_FLET,
                CLAS_OID_CLAS_FLET,
                VAL_PUNT_EMIS,
                NUM_LOTE_FACT,
                VAL_PREC_CONT_TOTA_DOCU,
                IND_INTE_LARI_GENE,
                FEC_PROG_FACT_COMP,
                ICTP_OID_TIPO_PROG,
                ICTP_OID_CONC_TIPO_PROG,
                VAL_ORIG_CHEQ)
             VALUES
               (PED_SOCA_SEQ.NEXTVAL,--OID_SOLI_CABE
                TRUNC(SYSDATE),--FEC_PROG_FACT
                NULL,--FEC_FACT
                NULL,--V_NUM_CLIEN,
                NULL,--V_VAL_GRUP_REVE,
                lv_oid_tipo_soli_pais,--TSPA_OID_TIPO_SOLI_PAIS,--
                NULL,--V_MONE_OID_MONE,
                NULL,--V_TIDS_OID_TIPO_DESP,
                lv_almc_oid_alma,--ALMC_OID_ALMA
                '15',--MODU_OID_MODU
                lv_ticl_oid_tipo_clie,--TICL_OID_TIPO_CLIE
                NULL,--V_TAIM_OID_TASA_IMPU,
                '2383',-- DURO0000!!!!!!!!!!!!!!!!!!! V_PERD_OID_PERI,--
                boletaRecojo.SOCA_OID_SOLI_CABE,--SOCA_OID_SOLI_CABE,
                boletaRecojo.CLIE_OID_CLIE,--V_CLIE_OID_CLIE,
                boletaRecojo.CLIE_OID_CLIE,--V_CLIE_OID_CLIE_RECE_FACT,
                boletaRecojo.CLIE_OID_CLIE,--V_CLIE_OID_CLIE_PAGA,
                boletaRecojo.CLIE_OID_CLIE,--V_CLIE_OID_CLIE_DEST,
                boletaRecojo.OID_CLIE_DIRE ,--boletaRecojo.CLIE_OID_CLIE,--V_CLDI_OID_CLIE_DIRE,
                boletaRecojo.TDOC_OID_TIPO_DOCU,--V_TDOC_OID_TIPO_DOCU,
                lv_soci_oid_soci,--V_SOCI_OID_SOCI
                lv_sbac_oid_sbac,--V_SBAC_OID_SBAC
                boletaRecojo.OID_TERR,--V_TERR_OID_TERR,
                boletaRecojo.OID_ZONA,--V_ZZON_OID_ZONA,
                NULL,--V_IND_ESTA,
                NULL,--V_IND_IMPR,
                NULL,--V_IND_EXEN_FLET,
                PED_SOCA_SEQ.CURRVAL,--V_VAL_NUME_SOLI,
                psValUsuario,--V_VAL_USUA,
                lv_val_tasa_impu,--V_VAL_TASA_IMPU,
                TRUNC(SYSDATE),---V_FEC_CRON,
                lv_ind_perm_unio,--V_IND_PERM_UNIO_SOL,
                NULL,--V_IND_GENE_CC,
                NULL,--V_IND_APLI_MANU,
                NULL,--V_VAL_TIPO_CAMB,
                NULL,--V_NUM_DOCU_CONT_INTE,
                NULL,--V_NUM_DOCU_ORIG,
                NULL,--V_VAL_LOTE_REPO_FALT,
                NULL,--V_FEC_REPO_FALT,
                NULL,--V_VAL_BASE_FLET_LOCA,
                NULL,--V_VAL_IMPO_FLET_LOCA,
                NULL,--V_VAL_IMPO_FLET_TOTA_LOCA,
                NULL,--V_VAL_IMPO_FLET_SIN_IMPU_TOTA,
                NULL,--V_VAL_RECA_FLET_LOCA,
                NULL,--V_VAL_OTRO_RECA_LOCA,
                NULL,--V_VAL_TOTA_PAGA_LOCA,
                NULL,--V_VAL_PREC_CATA_TOTA_LOCA,
                NULL,--V_VAL_PREC_CATA_SIN_IMPU_TOTA,
                NULL,--V_VAL_PREC_FACT_TOTA_LOCA,
                NULL,--V_VAL_IMPO_IMPU_TOTA_LOCA,
                NULL,--V_VAL_IMPO_DESC_1_TOTA_LOCA,
                NULL,--V_VAL_IMPO_DESC_1_TOTA_DOCU,
                NULL,--V_VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
                NULL,--V_VAL_IMPO_DESC_3_TOTA_DOCU,
                NULL,--V_VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
                NULL,--V_VAL_IMPO_DESC_TOTA_LOCA,
                NULL,--V_VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
                NULL,--V_VAL_IMPO_REDO_LOCA,
                NULL,--V_VAL_BASE_FLET_DOCU,
                NULL,--V_VAL_IMPO_FLET_DOCU,
                NULL,--V_VAL_IMPO_DESC_TOTA_DOCU,
                NULL,--V_VAL_IMPO_FLET_SIN_IMPU_DOCU,
                NULL,--V_VAL_RECA_FLET_DOCU,
                NULL,--V_VAL_OTRO_RECA_DOCU,
                NULL,--V_VAL_TOTA_FLET_DOCU,
                NULL,--V_VAL_IMPO_FLET_TOTA_DOCU,
                NULL,--V_VAL_TOTA_FLET_LOCA,
                NULL,--V_VAL_TOTA_PAGA_DOCU,
                NULL,--V_VAL_PREC_CATA_TOTA_DOCU,
                NULL,--V_VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
                NULL,--V_VAL_PREC_CONT_TOTA_LOCA,
                NULL,--V_VAL_PREC_CONT_SIN_IMPU_TOTA,
                NULL,--V_VAL_PREC_CONT_SIN_IMPU_TOTA_1,
                NULL,--V_VAL_PREC_FACT_TOTA_DOCU,
                NULL,--V_VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
                NULL,--V_VAL_PREC_NETO_TOTA_DOCU,
                NULL,--V_VAL_PREC_NETO_TOTA_LOCA,
                NULL,--V_VAL_IMPO_IMPU_TOTA_DOCU,
                NULL,--V_VAL_IMPO_REDO_DOCU,
                NULL,--V_VAL_IMPO_REDO_CONS_LOCA,
                NULL,--V_VAL_IMPO_REDO_CONS_DOCU,
                boletaRecojo.NUM_TOTA_UNID_RECL,--V_VAL_UNID_DEMA_REAL_TOTA,
                boletaRecojo.NUM_TOTA_UNID_RECL,--V_NUM_UNID_POR_ATEN_TOTA,
                NULL,--V_NUM_UNID_ATEN_TOTA,
                lv_ind_orde_comp,--V_IND_OC,
                NULL,--V_IND_PEDI_PRUE,
                DECODE(lv_ind_cons,1,0,1),--V_IND_TS_NO_CONSO,
                NULL,--V_VAL_GLOS_OBSE,
                NULL,--V_VAL_OBSE_REVI,
                NULL,--V_NUM_PREM,
                NULL,--V_VAL_IMPO_DESC_3_TOTA_LOCA,
                NULL,--V_VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
                lv_pais_oid_pais,--V_PAIS_OID_PAIS,
                lv_tido_oid_tipo_docu,--V_TIDO_OID_TIPO_DOCU,
                boletaRecojo.VEPO_OID_VALO_ESTR_GEOP,--V_VEPO_OID_VALO_ESTR_GEOP,
                NULL,--V_RECQ_OID_RESU_CHEQ,
                '1',--V_ESSO_OID_ESTA_SOLI,
                NULL,--V_COPA_OID_PARA_GENE,
                '4',--V_GRPR_OID_GRUP_PROC,
                boletaRecojo.SBTI_OID_SUBT_CLIE,--V_SBTI_OID_SUBT_CLIE,
                NULL,--V_ACFI_OID_ACCE_FISI,
                lv_tsol_oid_tipo_cons,--V_TSPA_OID_TIPO_SOLI_PAIS_CONS,
                lv_fopa_oid_form_pago,--V_FOPA_OID_FORM_PAGO,
                NULL,--V_CLIE_OID_CONS_ASOC,
                NULL,--V_ESPE_OID_ESTA_PEDI,
                lv_clso_oid_clas_soli,--V_CLSO_OID_CLAS_SOLI,
                boletaRecojo.OID_TERR_ADMI,--V_ZTAD_OID_TERR_ADMI,
                NULL,--V_INRE_OID_INDI_REVI,
                '40',-- V_OPER_OID_OPER,
                '1', -- V_PROC_OID_PROC,
                NULL,--V_SOCA_OID_DOCU_REFE,
                NULL,--V_TCCL_OID_TCCL_FLET,
                NULL,--V_CLAS_OID_CLAS_FLET,
                NULL,--V_VAL_PUNT_EMIS,
                NULL,--V_NUM_LOTE_FACT,
                NULL,--V_VAL_PREC_CONT_TOTA_DOCU,
                '1',--V_IND_INTE_LARI_GENE,
                NULL,--V_FEC_PROG_FACT_COMP,
                NULL,--V_ICTP_OID_TIPO_PROG,
                NULL,--V_ICTP_OID_CONC_TIPO_PROG,
                NULL--V_VAL_ORIG_CHEQ
                );
                lv_cod_posi:=0;
       END IF;
           INSERT INTO PED_SOLIC_POSIC
                (OID_SOLI_POSI,
                 COD_POSI,
                 VAL_LOTE_PROD,
                 NUM_UNID_DEMA,
                 NUM_UNID_POR_ATEN,
                 VAL_TASA_IMPU,
                 SOCA_OID_SOLI_CABE,
                 TAIM_OID_TASA_IMPU,
                 TPOS_OID_TIPO_POSI,
                 PROD_OID_PROD,
                 FOPA_OID_FORM_PAGO,
                 IND_LIMI_VENT,
                 IND_CTRL_STOC,
                 IND_CTRL_LIQU,
                 VAL_PREC_CATA_UNIT_LOCA,
                 VAL_PREC_CONT_UNIT_LOCA,
                 VAL_PREC_CATA_UNIT_DOCU,
                 VAL_PREC_CONTA_UNIT_DOCU,
                 VAL_PREC_FACT_UNIT_LOCA,
                 VAL_PREC_FACT_UNIT_DOCU,
                 VAL_PREC_SIN_IMPU_UNIT_LOCA,
                 VAL_PREC_SIN_IMPU_UNIT_DOCU,
                 VAL_PREC_SIN_IMPU_TOTA_DOCU,
                 VAL_IMPO_DESC_UNIT_LOCA,
                 VAL_IMPO_DESC_UNIT_DOCU,
                 VAL_PREC_NETO_UNIT_LOCA,
                 VAL_PREC_NETO_TOTA_DOCU,
                 VAL_PREC_NETO_UNIT_DOCU,
                 VAL_PREC_TOTA_TOTA_LOCA,
                 VAL_PREC_TOTA_TOTA_DOCU,
                 VAL_IMPO_IMPU_UNIT_LOCA,
                 VAL_IMPO_IMPU_UNIT_DOCU,
                 VAL_IMPO_DESC_TOTA_DOCU,
                 VAL_IMPO_IMPU_TOTA_LOCA,
                 VAL_IMPO_IMPU_TOTA_DOCU,
                 VAL_IMPO_DESC_TOTA_LOCA,
                 VAL_PREC_TOTA_UNIT_LOCA,
                 VAL_PREC_TOTA_UNIT_DOCU,
                 VAL_PREC_CONT_TOTA_LOCA,
                 VAL_PREC_CATA_TOTA_LOCA,
                 VAL_PREC_CATA_TOTA_DOCU,
                 VAL_PREC_CONT_TOTA_DOCU,
                 VAL_PORC_DESC,
                 VAL_PREC_CATA_TOTA_LOCA_UNID,
                 NUM_UNID_DEMA_REAL,
                 NUM_UNID_COMPR,
                 NUM_UNID_CAMB,
                 NUM_UNID_VENT,
                 NUM_UNID_ATEN,
                 VAL_CODI_VENT_FICT,
                 VAL_PREC_FACT_TOTA_LOCA,
                 VAL_PREC_FACT_TOTA_DOCU,
                 VAL_PREC_SIN_IMPU_TOTA_LOCA,
                 VAL_PREC_NETO_TOTA_LOCA,
                 OFDE_OID_DETA_OFER,
                 ESPO_OID_ESTA_POSI,
                 STPO_OID_SUBT_POSI,
                 IND_RECU_OBLI,
                 VAL_CODI_VENT,
                 SOPO_OID_SOLI_POSI,
                 IND_NO_IMPR,
                 IND_DENT_FUER_CAJA_BOLS,
                 VAL_CATA,
                 NUM_PAGI_CATA,
                 NUM_CONS,
                 NUM_DOCU_CONT_INTE,
                 VAL_EJER_DOCU_CONT_INTE,
                 VAL_IMPO_DES_SIN_IMP_UNIT_LOCA,
                 VAL_IMPO_DES_SIN_IMP_UNIT_DOCU,
                 VAL_IMPO_DES_SIN_IMP_TOTA,
                 VAL_IMPO_DES_SIN_IMP_TOTA_DOCU,
                 VAL_OBSE)
              VALUES
                (PED_SOPO_SEQ.NEXTVAL, --V_OID_SOLI_POSI,
                 lv_cod_posi,--V_COD_POSI,
                 boletaRecojo.Num_Unid_Recl,--V_VAL_LOTE_PROD,
                 NULL,--V_NUM_UNID_DEMA,
                 boletaRecojo.Num_Unid_Recl,--V_NUM_UNID_POR_ATEN,
                 lv_val_tasa_impu,--V_VAL_TASA_IMPU,
                 PED_SOCA_SEQ.CURRVAL,--V_SOCA_OID_SOLI_CABE,
                 lv_oidTasaImpuesto,--V_TAIM_OID_TASA_IMPU,
                 NULL,--V_TPOS_OID_TIPO_POSI,
                 boletaRecojo.Prod_Oid_Prod,--V_PROD_OID_PROD,
                 NULL,--V_FOPA_OID_FORM_PAGO,
                 NULL,--V_IND_LIMI_VENT,
                 NULL,--V_IND_CTRL_STOC,
                 NULL,--V_IND_CTRL_LIQU,
                 boletaRecojo.Val_Prec,--V_VAL_PREC_CATA_UNIT_LOCA,
                 boletaRecojo.Val_Prec_Cont,--V_VAL_PREC_CONT_UNIT_LOCA,
                 NULL,--V_VAL_PREC_CATA_UNIT_DOCU,
                 NULL,--V_VAL_PREC_CONTA_UNIT_DOCU,
                 boletaRecojo.Val_Prec,--V_VAL_PREC_FACT_UNIT_LOCA,
                 boletaRecojo.Val_Prec,--V_VAL_PREC_FACT_UNIT_DOCU,
                 NULL,--V_VAL_PREC_SIN_IMPU_UNIT_LOCA,
                 NULL,--V_VAL_PREC_SIN_IMPU_UNIT_DOCU,
                 NULL,--V_VAL_PREC_SIN_IMPU_TOTA_DOCU,
                 NULL,--V_VAL_IMPO_DESC_UNIT_LOCA,
                 NULL,--V_VAL_IMPO_DESC_UNIT_DOCU,
                 NULL,--V_VAL_PREC_NETO_UNIT_LOCA,
                 NULL,--V_VAL_PREC_NETO_TOTA_DOCU,
                 NULL,--V_VAL_PREC_NETO_UNIT_DOCU,
                 NULL,--V_VAL_PREC_TOTA_TOTA_LOCA,
                 NULL,--V_VAL_PREC_TOTA_TOTA_DOCU,
                 NULL,--V_VAL_IMPO_IMPU_UNIT_LOCA,
                 NULL,--V_VAL_IMPO_IMPU_UNIT_DOCU,
                 NULL,--V_VAL_IMPO_DESC_TOTA_DOCU,
                 NULL,--V_VAL_IMPO_IMPU_TOTA_LOCA,
                 NULL,--V_VAL_IMPO_IMPU_TOTA_DOCU,
                 NULL,--V_VAL_IMPO_DESC_TOTA_LOCA,
                 NULL,--V_VAL_PREC_TOTA_UNIT_LOCA,
                 NULL,--V_VAL_PREC_TOTA_UNIT_DOCU,
                 NULL,--V_VAL_PREC_CONT_TOTA_LOCA,
                 NULL,--V_VAL_PREC_CATA_TOTA_LOCA,
                 NULL,--V_VAL_PREC_CATA_TOTA_DOCU,
                 NULL,--V_VAL_PREC_CONT_TOTA_DOCU,
                 NULL,--V_VAL_PORC_DESC,
                 NULL,--V_VAL_PREC_CATA_TOTA_LOCA_UNID,
                 boletaRecojo.Num_Unid_Recl,--V_NUM_UNID_DEMA_REAL,
                 boletaRecojo.Num_Unid_Recl,--V_NUM_UNID_COMPR,
                 NULL,--V_NUM_UNID_CAMB,
                 NULL,--V_NUM_UNID_VENT,
                 boletaRecojo.Num_Unid_Recl,--V_NUM_UNID_ATEN,
                 NULL,--V_VAL_CODI_VENT_FICT,
                 NULL,--V_VAL_PREC_FACT_TOTA_LOCA,
                 NULL,--V_VAL_PREC_FACT_TOTA_DOCU,
                 NULL,--V_VAL_PREC_SIN_IMPU_TOTA_LOCA,
                 NULL,--V_VAL_PREC_NETO_TOTA_LOCA,
                 boletaRecojo.OID_DETA_OFER,--V_OFDE_OID_DETA_OFER,
                 '4',--V_ESPO_OID_ESTA_POSI,
                 NULL,--V_STPO_OID_SUBT_POSI,
                 NULL,--V_IND_RECU_OBLI,
                 boletaRecojo.VAL_CODI_VENT,  --V_VAL_CODI_VENT,
                 NULL,--V_SOPO_OID_SOLI_POSI,
                 NULL,--V_IND_NO_IMPR,
                 boletaRecojo.Cod_Ind_Dent_Caja,--V_IND_DENT_FUER_CAJA_BOLS,
                 NULL,--V_VAL_CATA,
                 NULL,--V_NUM_PAGI_CATA,
                 NULL,--V_NUM_CONS,
                 NULL,--V_NUM_DOCU_CONT_INTE,
                 NULL,--V_VAL_EJER_DOCU_CONT_INTE,
                 NULL,--V_VAL_IMPO_DES_SIN_IMP_UNIT_LOCA,
                 NULL,--V_VAL_IMPO_DES_SIN_IMP_UNIT_DOCU,
                 NULL,--V_VAL_IMPO_DES_SIN_IMP_TOTA,
                 NULL,--V_VAL_IMPO_DES_SIN_IMP_TOTA_DOCU,
                 NULL--V_VAL_OBSE
                 );

      lv_aux_nume_bore:= lv_nume_bore;
      lv_cod_posi:=lv_cod_posi+1;

   END LOOP;
   CLOSE c_BoletaRecojo;
END REC_PR_ENVIA_OCS_BOREC;

/**************************************************************************
  Descripcion        : Genera la Interfaz d Envio de Transferenci de Boletas de Recojo
                       Detalle
  Fecha Creacion     : 27/03/2008
  Autor              : Jose A. cairampoma Granados
 ***************************************************************************/
PROCEDURE int_pr_rec_envia_trans_borec
(
  pscodigopais         VARCHAR2,
  pscodigosistema      VARCHAR2,
  pscodigointerfaz     VARCHAR2,
  psnombrearchivo      VARCHAR2,
  psindicadorvirtual   VARCHAR2,
  psindicadoranulacion VARCHAR2,
  psboletarecojocorte VARCHAR2
)

 IS
  CURSOR c_ianulacion IS
    SELECT num_lote,
           cod_pais,
           cod_cana,
           cod_acce,
           cod_suac,
           tip_peri,
           '' cod_peri,
           to_char(fec_envi,
                   'YYYYMMDD'),
           tip_ofer,
           tip_movi_alma,
           cod_nego,
           cod_marc,
           ind_ist,
           cod_sap,
           SUM(num_unid_recl) num_unid_recl_tot,
           val_prec_vent,
           val_prec_cont,
           cod_alma_orig,
           cod_alma_dest,
           val_num_docu,
           tip_oper,
           COD_OPER_HOMO
      FROM int_tmp_trans_borec t
     GROUP BY num_lote,
              cod_pais,
              cod_cana,
              cod_acce,
              cod_suac,
              tip_peri,
              --cod_peri,
              fec_envi,
              tip_ofer,
              tip_movi_alma,
              cod_nego,
              cod_marc,
              ind_ist,
              cod_sap,
              val_prec_vent,
              val_prec_cont,
              cod_alma_orig,
              cod_alma_dest,
              val_num_docu,
              COD_OPER_HOMO,
              tip_oper;

  TYPE interfazrec IS RECORD(
    v_num_lote      int_tmp_trans_borec.num_lote%TYPE,
    v_cod_pais      int_tmp_trans_borec.cod_pais%TYPE,
    v_cod_cana      int_tmp_trans_borec.cod_cana%TYPE,
    v_cod_acce      int_tmp_trans_borec.cod_acce%TYPE,
    v_cod_suac      int_tmp_trans_borec.cod_suac%TYPE,
    v_tip_peri      int_tmp_trans_borec.tip_peri%TYPE,
    v_cod_peri      int_tmp_trans_borec.cod_peri%TYPE,
    v_fec_envi      VARCHAR2(8),
    v_tip_ofer      int_tmp_trans_borec.tip_ofer%TYPE,
    v_tip_movi_alma int_tmp_trans_borec.tip_movi_alma%TYPE,
    v_cod_nego      int_tmp_trans_borec.cod_nego%TYPE,
    v_cod_marc      int_tmp_trans_borec.cod_marc%TYPE,
    v_ind_ist       int_tmp_trans_borec.ind_ist%TYPE,
    v_cod_sap       int_tmp_trans_borec.cod_sap%TYPE,
    v_num_unid_recl int_tmp_trans_borec.num_unid_recl%TYPE,
    v_val_prec_vent int_tmp_trans_borec.val_prec_vent%TYPE,
    v_val_prec_cont int_tmp_trans_borec.val_prec_cont%TYPE,
    v_cod_alma_orig int_tmp_trans_borec.cod_alma_orig%TYPE,
    v_cod_alma_dest int_tmp_trans_borec.cod_alma_dest%TYPE,
    v_val_num_docu  int_tmp_trans_borec.val_num_docu%TYPE,
    v_tip_oper      int_tmp_trans_borec.tip_oper%TYPE,
    v_cod_oper_homo      int_tmp_trans_borec.cod_oper%TYPE);

  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord             interfazrectab;
  interfazrecordsinanulacion interfazrectab;

  CURSOR c_inoanulacion(vnboletarecojocorte NUMBER) IS
SELECT num_lote,
       cod_pais,
       cod_cana,
       cod_acce,
       cod_suac,
       tip_peri,
       cod_peri,
       fec_envi,
       tip_ofer,
       tip_movi_alma,
       cod_nego,
       cod_marc,
       ind_ist,
       cod_sap,
       SUM(num_unid_recl_tot),
       val_prec_vent,
       val_prec_cont,
       cod_alma_orig,
       cod_alma_dest,
       val_num_docu,
           tip_oper,
           cod_oper_homo
      FROM (SELECT num_lote,
                t.cod_pais,
                cod_cana,
                cod_acce,
                cod_suac,
                tip_peri,
                '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                   to_char(fec_envi,
                           'YYYYMMDD') fec_envi,
                tip_ofer,
                CASE
                  -- Se envia el movimiento 19 solo si es virtual y es menor o igual a boleta de corte
                     WHEN (psindicadorvirtual = '1' AND vnboletarecojocorte IS NOT NULL AND
                          t.val_nume_bore <= vnboletarecojocorte) THEN
                   '19'
                  ELSE
                   b.cod_movi_sap
                END tip_movi_alma,
                cod_nego,
                cod_marc,
                b.ind_movim ind_ist,
                cod_sap,
                   nvl(num_unid_recl,
                       0) - nvl(num_unid_elim,
                                0) num_unid_recl_tot,
                val_prec_vent,
                val_prec_cont,
                cod_alma_orig,
                cod_alma_dest,
                val_num_docu,
                   tip_oper,
                   cod_oper_homo
          FROM int_tmp_trans_borec t,
                int_rec_movim_borec b
         WHERE t.cod_esta_bore = 'EX'
           AND b.cod_esta_bore = t.cod_esta_bore
           AND b.ind_virtu = psindicadorvirtual

        UNION ALL

        -- DISCREPANTES SI LLEGAN
        SELECT tl.num_lote,
               tl.cod_pais,
               tl.cod_cana,
               tl.cod_acce,
               tl.cod_suac,
               tl.tip_peri,
               tl.cod_peri,
               tl.fec_envi,
               tl.tip_ofer,
               tl.tip_movi_alma,
               tl.cod_nego,
               tl.cod_marc,
               tl.ind_ist,
               tl.cod_sap,
               tl.num_unid_recl num_unid_recl_tot,
               tl.val_prec_vent,
               tl.val_prec_cont,
               tl.cod_alma_orig,
               tl.cod_alma_dest,
               tl.val_num_docu,
                   tl.tip_oper,
                   cod_oper_homo
          FROM (SELECT num_lote,
                       tm.cod_pais,
                       cod_cana,
                       cod_acce,
                       cod_suac,
                       tip_peri,
                       '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                           to_char(fec_envi,
                                   'YYYYMMDD') fec_envi,
                       tip_ofer,
                       b.cod_movi_sap tip_movi_alma,
                       cod_nego,
                       cod_marc,
                       b.ind_movim ind_ist,
                       cod_prod_disc cod_sap,
                           nvl(num_unid_reco,
                               0) num_unid_recl,
                       val_prec_vent,
                       val_prec_cont,
                       cod_alma_orig,
                       cod_alma_dest,
                       val_num_docu,
                           tip_oper,
                           cod_oper_homo
                  FROM int_tmp_trans_borec tm,
                       int_rec_movim_borec b
                 WHERE tm.cod_esta_bore IN ('CD')
                   AND b.cod_esta_bore = tm.cod_esta_bore
                   AND tm.ind_disc = 1 -- Linea SItiene discrepante
                   AND b.ind_disc = 0 -- Movimiento de producto que SI llega
                   AND b.ind_virtu = psindicadorvirtual
                UNION ALL
                SELECT num_lote,
                       tm.cod_pais,
                       cod_cana,
                       cod_acce,
                       cod_suac,
                       tip_peri,
                       '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                           to_char(fec_envi,
                                   'YYYYMMDD') fec_envi,
                       tip_ofer,
                       b.cod_movi_sap tip_movi_alma,
                       cod_nego,
                       cod_marc,
                       b.ind_movim ind_ist,
                       cod_sap,
                           nvl(num_unid_reco,
                               0) num_unid_recl,
                       val_prec_vent,
                       val_prec_cont,
                       cod_alma_orig,
                       cod_alma_dest,
                       val_num_docu,
                           tip_oper,
                           cod_oper_homo
                  FROM int_tmp_trans_borec tm,
                       int_rec_movim_borec b
                 WHERE tm.cod_esta_bore IN ('CD')
                   AND tm.ind_disc = 0 -- Linea NO tiene discrepante
                   AND b.ind_virtu = psindicadorvirtual
                   AND b.cod_esta_bore = tm.cod_esta_bore
                   AND b.ind_disc = 0 -- Movimiento de producto que SI llega
                   ) tl

        UNION ALL

        -- NO EXITOSAS / ELIMINADAS
        SELECT t.num_lote,
               t.cod_pais,
               t.cod_cana,
               t.cod_acce,
               t.cod_suac,
               t.tip_peri,
               t.cod_peri,
               t.fec_envi,
               t.tip_ofer,
               t.tip_movi_alma,
               t.cod_nego,
               t.cod_marc,
               t.ind_ist,
               t.cod_sap,
               t.num_unid_recl num_unid_recl_tot,
               t.val_prec_vent,
               t.val_prec_cont,
               t.cod_alma_orig,
               t.cod_alma_dest,
               t.val_num_docu,
                   t.tip_oper,
                   t.cod_oper_homo
          FROM (SELECT t1.num_lote,
                       t1.cod_pais,
                       t1.cod_cana,
                       t1.cod_acce,
                       t1.cod_suac,
                       t1.tip_peri,
                       '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                           to_char(t1.fec_envi,
                                   'YYYYMMDD') fec_envi,
                       t1.tip_ofer,
                       b.cod_movi_sap tip_movi_alma,
                       t1.cod_nego,
                       t1.cod_marc,
                       b.ind_movim ind_ist,
                       t1.cod_sap,
                           (nvl(t1.num_unid_recl,
                                0) - nvl(t1.num_unid_elim,
                                          0)) num_unid_recl,
                       t1.val_prec_vent,
                       t1.val_prec_cont,
                       t1.cod_alma_orig,
                       t1.cod_alma_dest,
                       t1.val_num_docu,
                           t1.tip_oper,
                           t1.cod_oper_homo
                  FROM int_tmp_trans_borec t1,
                       int_rec_movim_borec b
                     WHERE t1.cod_esta_bore IN ('NX',
                                                'NE')
                   AND b.cod_esta_bore = t1.cod_esta_bore
                   AND 1 = psindicadorvirtual -- Solo para virtual
                           AND
                           (vnboletarecojocorte IS NULL OR t1.val_nume_bore > vnboletarecojocorte) -- Solo se envia si es > a boleta de corte
                UNION ALL
                SELECT num_lote,
                       cod_pais,
                       cod_cana,
                       cod_acce,
                       cod_suac,
                       tip_peri,
                       '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                           to_char(fec_envi,
                                   'YYYYMMDD') fec_envi,
                       tip_ofer,
                       '18' tip_movi_alma,
                       cod_nego,
                       cod_marc,
                       'I' ind_ist,
                       cod_sap,
                           nvl(num_unid_elim,
                               0) num_unid_recl,
                       val_prec_vent,
                       val_prec_cont,
                       cod_alma_orig,
                       cod_alma_dest,
                       val_num_docu,
                           tip_oper,
                           cod_oper_homo
                  FROM int_tmp_trans_borec
                     WHERE nvl(num_unid_elim,
                               0) > 0 -- Unidades eliminadas
                   AND 1 = psindicadorvirtual -- Solo para virtual
                   AND (vnboletarecojocorte IS NULL OR val_nume_bore > vnboletarecojocorte) -- Solo se envia si es > a boleta de corte
                   ) t

        UNION ALL

        -- DISCREPANTES NO LLEGAN
        SELECT num_lote,
               t.cod_pais,
               cod_cana,
               cod_acce,
               cod_suac,
               tip_peri,
               '' cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
                   to_char(fec_envi,
                           'YYYYMMDD'),
               tip_ofer,
               CASE
                     WHEN psindicadorvirtual = '1' AND
                          (vnboletarecojocorte IS NULL OR t.val_nume_bore > vnboletarecojocorte) THEN
                  b.cod_movi_sap
                 ELSE
                  '19'
               END tip_movi_alma,
               cod_nego,
               cod_marc,
               b.ind_movim ind_ist,
               cod_sap,
                   nvl(num_unid_recl,
                       0) - nvl(num_unid_elim,
                                0) num_unid_recl_tot,
               val_prec_vent,
               val_prec_cont,
               cod_alma_orig,
               cod_alma_dest,
               val_num_docu,
                   tip_oper,
                   cod_oper_homo
          FROM int_tmp_trans_borec t,
               int_rec_movim_borec b
         WHERE t.cod_esta_bore = 'CD'
           AND b.cod_esta_bore = t.cod_esta_bore
           AND b.ind_virtu = psindicadorvirtual
           AND b.ind_disc = 1 -- Movimiento de producto que no llega
           AND 1 = psindicadorvirtual -- Solo se aplica al virtual
           AND t.refe_cod_line_bore IS NULL -- Linea de boleta de recojo original
                   AND (nvl(t.num_unid_recl,
                            0) - nvl(t.num_unid_elim,
                                          0)) > 0 -- Solo se envian unidades mayores a 0
           AND (vnboletarecojocorte IS NULL OR t.val_nume_bore > vnboletarecojocorte) -- Solo se envia si es > a boleta corte
        ) temp
 GROUP BY temp.num_lote,
          temp.cod_pais,
          temp.cod_cana,
          temp.cod_acce,
          temp.cod_suac,
          temp.tip_peri,
          temp.cod_peri,
          temp.fec_envi,
          temp.tip_ofer,
          temp.tip_movi_alma,
          temp.cod_nego,
          temp.cod_marc,
          temp.ind_ist,
          temp.cod_sap,
          temp.val_prec_vent,
          temp.val_prec_cont,
          temp.cod_alma_orig,
          temp.cod_alma_dest,
          temp.val_num_docu,
              temp.tip_oper,
              temp.cod_oper_homo
     ORDER BY temp.tip_movi_alma,
              temp.cod_sap,
              temp.cod_oper_homo;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  w_filas    NUMBER := 1000;
  v_handle   utl_file.file_type;

  lslinea VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);

  lnboletarecojocorte int_rec_cabec_borec.val_nume_bore%TYPE;
BEGIN

  IF psindicadorvirtual = '1' THEN
      UPDATE int_tmp_trans_borec
         SET cod_oper = NULL,
             tip_oper = NULL
       WHERE (ind_anul IS NULL OR ind_anul <> '1');
  END IF;

  lnboletarecojocorte := nvl(psboletarecojocorte,
                             0);
  /* Procedimiento inicial para generar interfaz */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);

  /* Generando Archivo de Texto (Detalle) */
  IF (psindicadoranulacion = '1') THEN
    OPEN c_ianulacion;
    LOOP
      FETCH c_ianulacion BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN

        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).v_num_lote || ';' ||
                     interfazrecord(x).v_cod_pais || ';' ||
                     interfazrecord(x).v_cod_cana || ';' ||
                     interfazrecord(x).v_cod_acce || ';' ||
                     interfazrecord(x).v_cod_suac || ';' ||
                     interfazrecord(x).v_tip_peri || ';' ||
                     interfazrecord(x).v_cod_peri || ';' ||
                     interfazrecord(x).v_fec_envi || ';' ||
                     interfazrecord(x).v_tip_ofer || ';' ||
                     interfazrecord(x).v_tip_movi_alma || ';' ||
                     interfazrecord(x).v_cod_nego || ';' ||
                     interfazrecord(x).v_cod_marc || ';' ||
                     interfazrecord(x).v_ind_ist || ';' ||
                     interfazrecord(x).v_cod_sap || ';' ||
                     interfazrecord(x).v_num_unid_recl || ';' ||
                     interfazrecord(x).v_val_prec_vent || ';' ||
                     interfazrecord(x).v_val_prec_cont || ';' ||
                     interfazrecord(x).v_cod_alma_orig || ';' ||
                     interfazrecord(x).v_cod_alma_dest || ';' ||
                     interfazrecord(x).v_val_num_docu || ';' ||
                               interfazrecord(x).v_cod_oper_homo || ';' ||
                     interfazrecord(x).v_tip_oper;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_ianulacion%NOTFOUND;
    END LOOP;
    CLOSE c_ianulacion;

    utl_file.fclose(v_handle);
  ELSE
    OPEN c_inoanulacion(lnboletarecojocorte);
    LOOP
      FETCH c_inoanulacion BULK COLLECT
        INTO interfazrecordsinanulacion LIMIT w_filas;
      IF interfazrecordsinanulacion.count > 0 THEN

        FOR x IN interfazrecordsinanulacion.first .. interfazrecordsinanulacion.last
        LOOP
            -- Solo escribimos en el archivo si las unidades son mayores a cero
            IF interfazrecordsinanulacion(x).v_num_unid_recl > 0 THEN
              lslinea := interfazrecordsinanulacion(x).v_num_lote || ';' ||
                         interfazrecordsinanulacion(x) .v_cod_pais || ';' ||
                         interfazrecordsinanulacion(x).v_cod_cana || ';' ||
                         interfazrecordsinanulacion(x).v_cod_acce || ';' ||
                         interfazrecordsinanulacion(x).v_cod_suac || ';' ||
                         interfazrecordsinanulacion(x).v_tip_peri || ';' ||
                         interfazrecordsinanulacion(x).v_cod_peri || ';' ||
                         interfazrecordsinanulacion(x).v_fec_envi || ';' ||
                         interfazrecordsinanulacion(x).v_tip_ofer || ';' ||
                         interfazrecordsinanulacion(x).v_tip_movi_alma || ';' ||
                         interfazrecordsinanulacion(x).v_cod_nego || ';' ||
                         interfazrecordsinanulacion(x).v_cod_marc || ';' ||
                         interfazrecordsinanulacion(x).v_ind_ist || ';' ||
                         interfazrecordsinanulacion(x).v_cod_sap || ';' ||
                         interfazrecordsinanulacion(x).v_num_unid_recl || ';' ||
                         interfazrecordsinanulacion(x).v_val_prec_vent || ';' ||
                         interfazrecordsinanulacion(x).v_val_prec_cont || ';' ||
                         interfazrecordsinanulacion(x).v_cod_alma_orig || ';' ||
                         interfazrecordsinanulacion(x).v_cod_alma_dest || ';' ||
                         interfazrecordsinanulacion(x).v_val_num_docu || ';' ||
                                     interfazrecordsinanulacion(x).v_cod_oper_homo || ';' ||
                         interfazrecordsinanulacion(x).v_tip_oper;

            utl_file.put_line(v_handle,
                              lslinea);
            END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_inoanulacion%NOTFOUND;
    END LOOP;
    CLOSE c_inoanulacion;

    utl_file.fclose(v_handle);

  END IF;

  /* Procedimiento final para generar interfaz */
  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);

  int_pr_rec_histo_trans_borec(pscodigopais);
  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_TRANS_BOREC: ' || ls_sqlerrm);
END int_pr_rec_envia_trans_borec;
/**************************************************************************
  Descripcion        : Genera la Interfaz d Envio de Unidades a almacen Virtual
                       Detalle
  Fecha Creacion     : 25/06/2008
  Autor              : Jose A. cairampoma Granados
 ***************************************************************************/
PROCEDURE int_pr_rec_envia_unida_almvi
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2
)

 IS
  CURSOR c_interfaz IS

    SELECT tmp.num_lote      v_num_lote,
           tmp.cod_pais      v_cod_pais,
           tmp.cod_cana      v_cod_cana,
           tmp.cod_acce      v_cod_acce,
           tmp.cod_suac      v_cod_suba,
           tmp.tip_peri      v_cod_tipo_peri,
           tmp.cod_peri      v_cod_peri,
           to_char(tmp.fec_envi,
                   'YYYYMMDD') v_fec_envi,
           tmp.tip_ofer      v_tip_ofer,
           tmp.tip_movi_alma v_tip_movi_alma,
           tmp.cod_nego      v_cod_nego,
           tmp.cod_marc      v_cod_marc_prod,
           tmp.ind_ist       v_ind_ist,
           tmp.cod_sap       v_cod_sap,
           SUM(tmp.num_unid_recl) v_num_unid_recl,
           SUM(tmp.num_unid_devu) v_num_unid_devu,
           tmp.val_prec_vent v_val_prec_vent,
           tmp.val_prec_cont v_val_prec_cont,
           tmp.cod_alma_orig v_cod_alma_orig,
           tmp.cod_alma_dest v_cod_alma_dest,
           tmp.val_nume_docu v_val_num_docu,
           tmp.cod_oper_homo v_cod_oper,
           tmp.tip_oper      v_tip_oper
      FROM int_tmp_envio_virtu tmp
     WHERE tmp.num_lote = psnumerolote
     GROUP BY tmp.num_lote,
              tmp.cod_pais,
              tmp.cod_cana,
              tmp.cod_acce,
              tmp.cod_suac,
              tmp.tip_peri,
              tmp.cod_peri,
              tmp.fec_envi,
              tmp.tip_ofer,
              tmp.tip_movi_alma,
              tmp.cod_nego,
              tmp.cod_marc,
              tmp.ind_ist,
              tmp.cod_sap,
              tmp.val_prec_vent,
              tmp.val_prec_cont,
              tmp.cod_alma_orig,
              tmp.cod_alma_dest,
              tmp.val_nume_docu,
              tmp.cod_oper_homo,
              tmp.tip_oper;

  TYPE interfazrec IS RECORD(
    v_num_lote      int_tmp_envio_virtu.num_lote%TYPE,
    v_cod_pais      int_tmp_envio_virtu.cod_pais%TYPE,
    v_cod_cana      int_tmp_envio_virtu.cod_cana%TYPE,
    v_cod_acce      int_tmp_envio_virtu.cod_acce%TYPE,
    v_cod_suac      int_tmp_envio_virtu.cod_suac%TYPE,
    v_tip_peri      int_tmp_envio_virtu.tip_peri%TYPE,
    v_cod_peri      int_tmp_envio_virtu.cod_peri%TYPE,
    v_fec_envi      VARCHAR2(8),
    v_tip_ofer      int_tmp_envio_virtu.tip_ofer%TYPE,
    v_tip_movi_alma int_tmp_envio_virtu.tip_movi_alma%TYPE,
    v_cod_nego      int_tmp_envio_virtu.cod_nego%TYPE,
    v_cod_marc      int_tmp_envio_virtu.cod_marc%TYPE,
    v_ind_ist       int_tmp_envio_virtu.ind_ist%TYPE,
    v_cod_sap       int_tmp_envio_virtu.cod_sap%TYPE,
    v_num_unid_recl int_tmp_envio_virtu.num_unid_recl%TYPE,
    v_num_unid_devu int_tmp_envio_virtu.num_unid_devu%TYPE,
    v_val_prec_vent int_tmp_envio_virtu.val_prec_vent%TYPE,
    v_val_prec_cont int_tmp_envio_virtu.val_prec_cont%TYPE,
    v_cod_alma_orig int_tmp_envio_virtu.cod_alma_orig%TYPE,
    v_cod_alma_dest int_tmp_envio_virtu.cod_alma_dest%TYPE,
    v_val_num_docu  int_tmp_envio_virtu.val_nume_docu%TYPE,
    v_cod_oper_homo      int_tmp_envio_virtu.cod_oper%TYPE,
    v_tip_oper      int_tmp_envio_virtu.tip_oper%TYPE

    );
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  w_filas    NUMBER := 1000;
  v_handle   utl_file.file_type;
  lsvalpain bas_param_inter.val_pain%TYPE;

  lslinea VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);
BEGIN
   --- Nueva variable para enviar el tipo de oferta a la sam7x
    select min(b.val_pain)
     into lsvalpain
     from bas_param_inter b
    where b.inte_cod_inte = psCodigoInterfaz
      and b.nom_pain = 'enviaTipoOferta';

  DELETE FROM int_tmp_envio_virtu;

  INSERT INTO int_tmp_envio_virtu
    (num_lote,
     cod_pais,
     cod_cana,
     cod_acce,
     cod_suac,
     tip_peri,
     cod_peri,
     fec_envi,
     tip_ofer,
     tip_movi_alma,
     cod_nego,
     cod_marc,
     ind_ist,
     cod_sap,
     num_unid_recl,
     num_unid_devu,
     val_prec_vent,
     val_prec_cont,
     cod_alma_orig,
     cod_alma_dest,
     val_nume_docu,
     cod_oper,
     tip_oper,
     oid_line_oper_recl,
     cod_oper_homo)
    SELECT psnumerolote v_num_lote,
           par.cod_pais v_cod_pais,
           par.cod_cana v_cod_cana,
           par.cod_acce v_cod_acce,
           par.cod_suba v_cod_suac,
           par.cod_tipo_peri v_tip_peri,
           '' v_cod_peri, -- Enviamos periodo en blanco (CHR 30/05/2009)
           SYSDATE v_fec_envi,
           ---'000' v_tip_ofer,
           ---decode(lsvalpain,null,' ',nvl(pto.cod_tipo_ofer,(select ito.COD_TIPO_OFER_CONC from inc_concu_param_gener ic, INC_TIPO_OFERT_CONCU ito where ic.tioc_oid_tipo_ofer_conc=ito.oid_tipo_ofer_conc and ic.oid_para_gral=pedcab.copa_oid_para_gene))) v_tip_ofer,
           int_pkg_recla.gen_fn_tipo_ofer(lsvalpain,pto.cod_tipo_ofer,pedcab.copa_oid_para_gene) AS v_tip_ofer,
           par.cod_movi_sap v_tip_movi_alma,
           '000' v_cod_nego,
           '000' v_cod_marc_prod,
           par.ind_movi v_ind_ist,
           ocr_solic_pedidos.gen_fn_codsap_prod(lor.prod_oid_prod) v_cod_sap,
           lor.num_unid_recl v_num_unid_recl,
           lor.num_unid_devu v_num_unid_devu,
           0 v_val_prec_vent,
           0 v_val_prec_cont,
           par.cod_alma v_cod_alma_orig,
           '' v_cod_alma_dest,
           '' v_val_num_docu,
           decode(par.ind_oper,
                  0,
                  NULL,
                  op.cod_oper) v_cod_oper,
           decode(par.ind_oper,
                  0,
                  NULL,
                  top.val_tipo_oper) v_tip_oper,
           lor.oid_line_oper_recl v_oid_line_oper_recl,
           (SELECT cod_oper_homo FROM rec_homol_opera_sap WHERE cod_oper = decode(par.ind_oper,
                  0,
                  NULL,
                  op.cod_oper))
      FROM rec_cabec_recla       cr,
           rec_opera_recla       ror,
           rec_linea_opera_recla lor,
           rec_opera             op,
           rec_tipos_opera       top,
           seg_pais              p,
           rec_param_envio_sap   par,
           cra_perio             cra,
                 ped_solic_cabec pedcab,
                 ped_solic_posic pos,
                 pre_ofert_detal pod,
                 pre_tipo_ofert  pto,
           seg_perio_corpo       sp
     WHERE cr.oid_cabe_recl = ror.care_oid_cabe_recl
       AND lor.opre_oid_oper_recl = ror.oid_oper_recl
           AND cr.esre_oid_esta_recl IN (6,
                                         4)
           AND ror.esop_oid_esta_oper IN (2,
                                          5)
       AND ror.tiop_oid_tipo_oper = top.oid_tipo_oper
       AND top.rope_oid_oper = op.oid_oper
       AND lor.timo_oid_tipo_movi = 2
       AND lor.num_unid_devu = 0
       AND op.ind_anul <> 1
       AND lor.num_unid_recl <> 0
       ---AND op.ind_falt_merc = 0
       AND OP.IND_FALT_MERC = decode((select count(*) flag from sto_param_gener_occrr where cod_para = 'IND_ENVI_SAP_FM' and val_param = '1'),0,0,OP.IND_FALT_MERC )
           AND cr.perd_oid_peri_recl = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo)
       AND (op.ind_devu_fisi_fact = 1 OR
           (op.ind_devu_fisi_fact = 0 AND lor.sopo_oid_soli_posi IS NULL))
       AND p.cod_pais = pscodigopais
       AND p.cod_pais = par.cod_pais
       AND cr.perd_oid_peri_recl = cra.oid_peri
       AND sp.oid_peri = cra.peri_oid_peri
               AND cr.soca_oid_soli_cabe  = pedcab.oid_soli_cabe
               and lor.sopo_oid_soli_posi = pos.oid_soli_posi(+)
               AND pos.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               and pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer(+)
           AND op.cod_oper NOT IN
           (SELECT cod_oper FROM int_rec_opera_exclu WHERE cod_pais = pscodigopais and IND_ENVI = 0 );

  /* Procedimiento inicial para generar interfaz */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);

  /* Generando Archivo de Texto (Detalle) */

  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.count > 0 THEN

      FOR x IN interfazrecord.first .. interfazrecord.last
      LOOP

        lslinea := interfazrecord(x).v_num_lote || ';' || interfazrecord(x).v_cod_pais || ';' || interfazrecord(x)
                   .v_cod_cana || ';' || interfazrecord(x).v_cod_acce || ';' || interfazrecord(x)
                   .v_cod_suac || ';' || interfazrecord(x).v_tip_peri || ';' || interfazrecord(x)
                   .v_cod_peri || ';' || interfazrecord(x).v_fec_envi || ';' || interfazrecord(x)
                   .v_tip_ofer || ';' || interfazrecord(x).v_tip_movi_alma || ';' || interfazrecord(x)
                   .v_cod_nego || ';' || interfazrecord(x).v_cod_marc || ';' || interfazrecord(x)
                   .v_ind_ist || ';' || interfazrecord(x).v_cod_sap || ';' || interfazrecord(x)
                   .v_num_unid_recl || ';' || interfazrecord(x).v_val_prec_vent || ';' || interfazrecord(x)
                   .v_val_prec_cont || ';' || interfazrecord(x).v_cod_alma_orig || ';' || interfazrecord(x)
                   .v_cod_alma_dest || ';' || interfazrecord(x).v_val_num_docu || ';' || interfazrecord(x)
                   .v_cod_oper_homo || ';' || interfazrecord(x).v_tip_oper;

        utl_file.put_line(v_handle,
                          lslinea);

      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  utl_file.fclose(v_handle);

  /* Procedimiento final para generar interfaz */
  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);

  INSERT INTO int_histo_envio_virtu
    SELECT tmp.* FROM int_tmp_envio_virtu tmp WHERE tmp.num_lote = psnumerolote;

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_UNIDA_ALMVI: ' || ls_sqlerrm);
END int_pr_rec_envia_unida_almvi;
/**************************************************************************
Descripcion        : Devuelve la descripcion del estado de Boleta de Recojo
Fecha Creacion     : 14/07/2008
Autor              : Jose Cairampoma
***************************************************************************/
FUNCTION gen_fn_devue_deest_borec(pnoidestado NUMBER) RETURN VARCHAR2 IS
  lsdesestado int_rec_estad_borec.des_esta_bore%TYPE;
BEGIN

  SELECT des_esta_bore
    INTO lsdesestado
    FROM int_rec_estad_borec
   WHERE oid_esta_bore = pnoidestado;

  RETURN lsdesestado;

EXCEPTION
  WHEN no_data_found THEN
    RETURN NULL;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         250);
    raise_application_error(-20123,
                            'ERROR GEN_FN_DEVUE_DEEST_BOREC: no existe oid' || ls_sqlerrm);
END gen_fn_devue_deest_borec;

/**************************************************************************
  Descripcion        : Procesa una Boleta de Recojo No Exitosa
  Fecha Creacion     : 12/09/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_borec_noexi
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2
) IS

  v_cod_pais                int_rec_cabec_borec.cod_pais%TYPE;
  v_cod_cabe_bore           int_rec_cabec_borec.cod_cabe_bore%TYPE;
  v_soca_oid_soli_cabe      int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_val_nume_bole_desp      int_rec_cabec_borec.val_nume_bole_desp%TYPE;
  v_esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  v_esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  v_num_reco                int_rec_cabec_borec.num_reco%TYPE;
  v_clie_oid_clie           int_rec_cabec_borec.clie_oid_clie%TYPE;
  v_cod_clie                int_rec_cabec_borec.cod_clie%TYPE;
  v_ztad_oid_terri_admin    int_rec_cabec_borec.ztad_oid_terri_admin%TYPE;
  v_cod_regi                int_rec_cabec_borec.cod_regi%TYPE;
  v_cod_zona                int_rec_cabec_borec.cod_zona%TYPE;
  v_cod_secc                int_rec_cabec_borec.cod_secc%TYPE;
  v_cod_terr                int_rec_cabec_borec.cod_terr%TYPE;
  v_more_oid_motn_reco_bore int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  v_fec_ingr                int_rec_cabec_borec.fec_ingr%TYPE;
  v_fec_prog_reco           int_rec_cabec_borec.fec_prog_reco%TYPE;
  v_fec_reco                int_rec_cabec_borec.fec_reco%TYPE;
  v_hor_reco                int_rec_cabec_borec.hor_reco%TYPE;
  v_fec_ulti_actu           int_rec_cabec_borec.fec_ulti_actu%TYPE;
  v_val_nume_bore           int_rec_cabec_borec.val_nume_bore%TYPE;
  v_num_tota_unid_recl      int_rec_cabec_borec.num_tota_unid_recl%TYPE;
  v_num_tota_unid_reco      int_rec_cabec_borec.num_tota_unid_reco%TYPE;
  v_ind_envi_xero           int_rec_cabec_borec.ind_envi_xero%TYPE;
  v_ind_envi_yobe           int_rec_cabec_borec.ind_envi_yobe%TYPE;
  v_ind_regr_yobe           int_rec_cabec_borec.ind_regr_yobe%TYPE;
  v_ind_chk_auto            int_rec_cabec_borec.ind_chk_auto%TYPE;
  v_ind_ocs_proc            int_rec_cabec_borec.ind_ocs_proc%TYPE;
  v_ind_proc_alma_fisi      int_rec_cabec_borec.ind_proc_alma_fisi%TYPE;
  v_num_lote_envi           int_rec_cabec_borec.num_lote_envi%TYPE;
  v_num_lote_devu           int_rec_cabec_borec.num_lote_devu%TYPE;
  v_val_nomb_terc           int_rec_cabec_borec.val_nomb_terc%TYPE;
  v_cod_peri_proc           int_rec_cabec_borec.cod_peri_proc%TYPE;
  v_ind_envi_xer2           int_rec_cabec_borec.ind_envi_xer2%TYPE;
  v_ind_envi_yob2           int_rec_cabec_borec.ind_envi_yob2%TYPE;
  v_ind_regr_yob2           int_rec_cabec_borec.ind_regr_yob2%TYPE;
  v_num_lote_env2           int_rec_cabec_borec.num_lote_env2%TYPE;
  v_num_lote_dev2           int_rec_cabec_borec.num_lote_dev2%TYPE;
  v_fec_ing2                int_rec_cabec_borec.fec_ing2%TYPE;
  v_fec_rec2                int_rec_cabec_borec.fec_rec2%TYPE;
  v_hor_rec2                int_rec_cabec_borec.hor_rec2%TYPE;
  v_more_oid_motn_reco_bor2 int_rec_cabec_borec.more_oid_motn_reco_bor2%TYPE;
  v_val_nomb_ter2           int_rec_cabec_borec.val_nomb_ter2%TYPE;

  v_cldi_oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE;
  v_ztad_oid_terr_admi      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
  v_terr_oid_terr           zon_terri_admin.terr_oid_terr%TYPE;
  v_zzon_oid_zona           zon_secci.zzon_oid_zona%TYPE;
  v_tdoc_oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE;
  v_tido_oid_tipo_docu      mae_tipo_docum.tido_oid_tipo_docu%TYPE;
  v_ticl_oid_tipo_clie      mae_clien_tipo_subti.ticl_oid_tipo_clie %TYPE;
  v_sbti_oid_subt_clie      mae_clien_tipo_subti.sbti_oid_subt_clie%TYPE;
  v_vepo_oid_valo_estr_geop zon_terri.vepo_oid_valo_estr_geop%TYPE;

  v_fopa_oid_form_pago           ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
  v_ind_perm_unio_sol            ped_tipo_solic_pais.ind_perm_unio%TYPE;
  v_soci_oid_soci                ped_tipo_solic_pais.soci_oid_soci%TYPE;
  v_tspa_oid_tipo_soli_pais_cons ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  v_val_glos_obse                ped_tipo_solic_pais.val_glos%TYPE;
  v_ind_pedi_prue                ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  v_mone_oid_mone                ped_tipo_solic_pais.mone_oid_mone%TYPE;
  v_acfi_oid_acce_fisi           ped_acces_fisic.oid_acce_fisi%TYPE;
  v_sbac_oid_sbac                ped_tipo_solic.sbac_oid_sbac%TYPE;
  v_clso_oid_clas_soli           ped_tipo_solic.clso_oid_clas_soli%TYPE;
  v_oid_alma                     ped_tipo_solic_pais.almc_oid_alma%TYPE;
  v_ind_orde_comp                ped_clase_solic.ind_orde_comp%TYPE;
  v_ind_soli_nega                ped_tipo_solic.ind_soli_nega%TYPE;
  v_oid_tipo_soli_pais           ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  v_almc_oid_alma                ped_tipo_solic_pais.almc_oid_alma%TYPE;

  v_tido_oid_tipo_doc2           mae_tipo_docum.tido_oid_tipo_docu%TYPE;

  v_fec_prog_fact cra_perio.fec_fina%TYPE;
  v_perd_oid_peri cra_perio.oid_peri%TYPE;

  v_prod_oid_prod mae_produ.oid_prod%TYPE;

  v_val_prec_cata_unit_loca int_rec_linea_borec.val_prec%TYPE;
  lnoidsolicabe ped_solic_cabec.oid_soli_cabe%TYPE;


  lnIdPais NUMBER;

      lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;

BEGIN

  SELECT cod_pais,
         cod_cabe_bore,
         soca_oid_soli_cabe,
         val_nume_bole_desp,
         esbo_oid_esta_bor1,
         esbo_oid_esta_bor2,
         num_reco,
         clie_oid_clie,
         cod_clie,
         ztad_oid_terri_admin,
         cod_regi,
         cod_zona,
         cod_secc,
         cod_terr,
         more_oid_motn_reco_bore,
         fec_ingr,
         fec_prog_reco,
         fec_reco,
         hor_reco,
         fec_ulti_actu,
         val_nume_bore,
         num_tota_unid_recl,
         num_tota_unid_reco,
         ind_envi_xero,
         ind_envi_yobe,
         ind_regr_yobe,
         ind_chk_auto,
         ind_ocs_proc,
         ind_proc_alma_fisi,
         num_lote_envi,
         num_lote_devu,
         val_nomb_terc,
         cod_peri_proc,
         ind_envi_xer2,
         ind_envi_yob2,
         ind_regr_yob2,
         num_lote_env2,
         num_lote_dev2,
         fec_ing2,
         fec_rec2,
         hor_rec2,
         more_oid_motn_reco_bor2,
         val_nomb_ter2
    INTO v_cod_pais,
         v_cod_cabe_bore,
         v_soca_oid_soli_cabe,
         v_val_nume_bole_desp,
         v_esbo_oid_esta_bor1,
         v_esbo_oid_esta_bor2,
         v_num_reco,
         v_clie_oid_clie,
         v_cod_clie,
         v_ztad_oid_terri_admin,
         v_cod_regi,
         v_cod_zona,
         v_cod_secc,
         v_cod_terr,
         v_more_oid_motn_reco_bore,
         v_fec_ingr,
         v_fec_prog_reco,
         v_fec_reco,
         v_hor_reco,
         v_fec_ulti_actu,
         v_val_nume_bore,
         v_num_tota_unid_recl,
         v_num_tota_unid_reco,
         v_ind_envi_xero,
         v_ind_envi_yobe,
         v_ind_regr_yobe,
         v_ind_chk_auto,
         v_ind_ocs_proc,
         v_ind_proc_alma_fisi,
         v_num_lote_envi,
         v_num_lote_devu,
         v_val_nomb_terc,
         v_cod_peri_proc,
         v_ind_envi_xer2,
         v_ind_envi_yob2,
         v_ind_regr_yob2,
         v_num_lote_env2,
         v_num_lote_dev2,
         v_fec_ing2,
         v_fec_rec2,
         v_hor_rec2,
         v_more_oid_motn_reco_bor2,
         v_val_nomb_ter2
    FROM int_rec_cabec_borec
   WHERE cod_cabe_bore = pscodigocabecera
     AND cod_pais = pscodigopais;

  SELECT m.cod_clie                   v_cod_clie,
         m.oid_clie                   v_clie_oid_clie,
         d.oid_clie_dire              v_cldi_oid_clie_dire,
         ad.ztad_oid_terr_admi        v_ztad_oid_terr_admi,
         ztad.terr_oid_terr           v_terr_oid_terr,
         zs.zzon_oid_zona             v_zzon_oid_zona,
         ide.tdoc_oid_tipo_docu       v_tdoc_oid_tipo_docu,
         mt.tido_oid_tipo_docu        v_tido_oid_tipo_docu,
         ms.ticl_oid_tipo_clie        v_ticl_oid_tipo_clie,
         ms.sbti_oid_subt_clie        v_sbti_oid_subt_clie,
         terr.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop
    INTO v_cod_clie,
         v_clie_oid_clie,
         v_cldi_oid_clie_dire,
         v_ztad_oid_terr_admi,
         v_terr_oid_terr,
         v_zzon_oid_zona,
         v_tdoc_oid_tipo_docu,
         v_tido_oid_tipo_docu,
         v_ticl_oid_tipo_clie,
         v_sbti_oid_subt_clie,
         v_vepo_oid_valo_estr_geop
    FROM mae_clien             m,
         mae_clien_direc       d,
         mae_clien_unida_admin ad,
         mae_clien_ident       ide,
         zon_terri_admin       ztad,
         zon_terri             terr,
         zon_secci             zs,
         mae_tipo_docum        mt,
         mae_clien_tipo_subti  ms
   WHERE m.oid_clie = d.clie_oid_clie
     AND m.oid_clie = ad.clie_oid_clie
     AND m.oid_clie = ide.clie_oid_clie
     AND ztad.oid_terr_admi = ad.ztad_oid_terr_admi
     AND ztad.zscc_oid_secc = zs.oid_secc
     AND mt.oid_tipo_docu = ide.tdoc_oid_tipo_docu
     AND ide.val_iden_docu_prin = 1
     AND ad.perd_oid_peri_fin IS NULL
     AND d.ind_dire_ppal = 1
     AND d.ind_elim = 0
     AND m.oid_clie = v_clie_oid_clie -- oid de cliente se recupera de la cabecera de la boleta de recojo
     AND ms.clie_oid_clie = m.oid_clie
     AND ms.ind_ppal = 1
     AND terr.oid_terr = ztad.terr_oid_terr;

  SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
         tsp.ind_perm_unio      v_ind_perm_unio_sol,
         tsp.soci_oid_soci      v_soci_oid_soci,
         tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
         tsp.val_glos           v_val_glos_obse,
         tsp.ind_pedi_prue      v_ind_pedi_prue,
         tsp.mone_oid_mone      v_mone_oid_mone,
         NULL                   v_acfi_oid_acce_fisi,
         ts.sbac_oid_sbac       v_sbac_oid_sbac,
         ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
         tsp.almc_oid_alma      v_oid_alma,
         cs.ind_orde_comp       v_ind_orde_comp,
         ts.ind_soli_nega       v_ind_soli_nega,
         tsp.oid_tipo_soli_pais v_oid_tipo_soli_pais,
         tsp.almc_oid_alma      v_almc_oid_alma,
         tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu
    INTO v_fopa_oid_form_pago,
         v_ind_perm_unio_sol,
         v_soci_oid_soci,
         v_tspa_oid_tipo_soli_pais_cons,
         v_val_glos_obse,
         v_ind_pedi_prue,
         v_mone_oid_mone,
         v_acfi_oid_acce_fisi,
         v_sbac_oid_sbac,
         v_clso_oid_clas_soli,
         v_oid_alma,
         v_ind_orde_comp,
         v_ind_soli_nega,
         v_oid_tipo_soli_pais,
         v_almc_oid_alma,
         v_tido_oid_tipo_doc2
    FROM ped_tipo_solic_pais tsp,
         ped_tipo_solic      ts,
         ped_clase_solic     cs
   WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
     AND ts.clso_oid_clas_soli = cs.oid_clas_soli
     AND ts.cod_tipo_soli = 'CCBR';

  SELECT fec_fina v_fec_prog_fact,
         oid_peri v_perd_oid_peri
    INTO v_fec_prog_fact,
         v_perd_oid_peri
    FROM cra_perio
   WHERE trunc(SYSDATE) <= fec_fina
     AND trunc(SYSDATE) >= fec_inic
     AND rownum = 1;

  IF v_tido_oid_tipo_doc2 IS NOT NULL THEN
     v_tido_oid_tipo_docu := v_tido_oid_tipo_doc2;
  END IF;

  SELECT ped_soca_seq.NEXTVAL
              INTO lnoidsolicabe
              FROM dual;


  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000');

    SELECT to_char(SYSDATE,
                   'YY') || lpad(lnnumsoliinicio,
                                8,
                                '0')
      INTO lnnumsoliformat
      FROM dual;

  lnnumsoliformat := lnnumsoliformat + 1;

  INSERT INTO ped_solic_cabec
    (oid_soli_cabe,
     fec_prog_fact,
     fec_fact,
     num_clien,
     tspa_oid_tipo_soli_pais,
     tids_oid_tipo_desp,
     almc_oid_alma,
     modu_oid_modu,
     ticl_oid_tipo_clie,
     perd_oid_peri,
     soca_oid_soli_cabe,
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
     val_usua,
     val_tasa_impu,
     fec_cron,
     ind_perm_unio_sol,
     val_tipo_camb,
     num_docu_orig,
     val_base_flet_loca,
     val_impo_flet_loca,
     val_impo_flet_tota_loca,
     val_impo_flet_sin_impu_tota,
     val_reca_flet_loca,
     val_otro_reca_loca,
     val_tota_paga_loca,
     val_prec_cata_tota_loca,
     val_prec_cata_sin_impu_tota,
     val_prec_fact_tota_loca,
     val_impo_impu_tota_loca,
     val_impo_desc_1_tota_loca,
     val_impo_desc_1_tota_docu,
     val_impo_desc_1_sin_impu_tota,
     val_impo_desc_3_tota_docu,
     val_impo_desc_3_sin_impu_tota,
     val_impo_desc_tota_loca,
     val_impo_dto_1_sin_imp_tot_loc,
     val_impo_redo_loca,
     val_base_flet_docu,
     val_impo_flet_docu,
     val_impo_desc_tota_docu,
     val_impo_flet_sin_impu_docu,
     val_reca_flet_docu,
     val_otro_reca_docu,
     val_tota_flet_docu,
     val_impo_flet_tota_docu,
     val_tota_flet_loca,
     val_tota_paga_docu,
     val_prec_cata_tota_docu,
     val_prec_cata_sin_impu_tota_do,
     val_prec_cont_tota_loca,
     val_prec_cont_sin_impu_tota,
     val_prec_cont_sin_impu_tota_1,
     val_prec_fact_tota_docu,
     val_prec_cata_tota_loc_uni_dem,
     val_prec_neto_tota_docu,
     val_prec_neto_tota_loca,
     val_impo_impu_tota_docu,
     val_impo_redo_docu,
     val_impo_redo_cons_loca,
     val_impo_redo_cons_docu,
     ind_oc,
     ind_pedi_prue,
     ind_ts_no_conso,
     val_glos_obse,
     val_impo_desc_3_tota_loca,
     val_impo_dto_3_sin_imp_tot_loc,
     pais_oid_pais,
     tido_oid_tipo_docu,
     vepo_oid_valo_estr_geop,
     esso_oid_esta_soli,
     grpr_oid_grup_proc,
     sbti_oid_subt_clie,
     acfi_oid_acce_fisi,
     tspa_oid_tipo_soli_pais_cons,
     fopa_oid_form_pago,
     clso_oid_clas_soli,
     ztad_oid_terr_admi,
     oper_oid_oper,
     proc_oid_proc,
     ind_inte_lari_gene,
     fec_prog_fact_comp)
  VALUES
    (lnoidsolicabe,--seq_oid_soli_cabe,
     v_fec_prog_fact,
     NULL,
     0,
     v_oid_tipo_soli_pais,---2001,--v_tspa_oid_tipo_soli_pais,
     3,--v_tids_oid_tipo_desp, --:3
     v_almc_oid_alma,
     27,--v_modu_oid_modu, --:27
     v_ticl_oid_tipo_clie,
     v_perd_oid_peri,
     NULL,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_cldi_oid_clie_dire,
     v_tdoc_oid_tipo_docu,
     v_soci_oid_soci,
     v_sbac_oid_sbac,
     v_terr_oid_terr,
     v_zzon_oid_zona,
     lnnumsoliformat,-- + 1,--seq_val_nume_soli,
     NULL,--v_val_usua,
     0,
     trunc(SYSDATE),
     1,
     1,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     1,
     'OPERACION SUJETA A PERCEPCION DEL IGV',
     0,
     0,
     lnIdPais,--v_pais_oid_pais,
     v_tido_oid_tipo_docu,
     v_vepo_oid_valo_estr_geop,
     1,
     4,
     v_sbti_oid_subt_clie,
     1,
     v_tspa_oid_tipo_soli_pais_cons,
     v_fopa_oid_form_pago,
     v_clso_oid_clas_soli,
     v_ztad_oid_terr_admi,
     44,--v_oper_oid_oper, --:44
     6,--v_proc_oid_proc, --:6
     0,
     to_char(SYSDATE, 'yyyymmdd')); --:--v_fec_prog_fact_comp

  /*OidProducto si es cargo*/
  SELECT oid_prod v_prod_oid_prod
    INTO v_prod_oid_prod
    FROM mae_produ
   WHERE cod_sap = 9999999998;

  SELECT imp_cargo v_val_prec_cata_unit_loca
    INTO v_val_prec_cata_unit_loca
    FROM int_rec_cabec_borec
   WHERE cod_cabe_bore = pscodigocabecera
     AND cod_pais= pscodigopais;

  INSERT INTO ped_solic_posic
    (oid_soli_posi,
     cod_posi,
     num_unid_dema,
     num_unid_por_aten,
     val_tasa_impu,
     soca_oid_soli_cabe,
     taim_oid_tasa_impu,
     tpos_oid_tipo_posi,
     prod_oid_prod,
     val_prec_cata_unit_loca,
     val_prec_cont_unit_loca,
     val_prec_cata_unit_docu,
     val_prec_conta_unit_docu,
     val_prec_fact_unit_loca,
     val_prec_fact_unit_docu,
     val_prec_sin_impu_unit_loca,
     val_prec_sin_impu_unit_docu,
     val_prec_sin_impu_tota_docu,
     val_impo_desc_unit_loca,
     val_prec_neto_unit_loca,
     val_prec_neto_tota_docu,
     val_prec_neto_unit_docu,
     val_prec_tota_tota_loca,
     val_prec_tota_tota_docu,
     val_impo_impu_unit_loca,
     val_impo_impu_unit_docu,
     val_impo_desc_tota_docu,
     val_impo_impu_tota_loca,
     val_impo_impu_tota_docu,
     val_impo_desc_tota_loca,
     val_prec_tota_unit_loca,
     val_prec_tota_unit_docu,
     val_prec_cont_tota_loca,
     val_prec_cata_tota_loca,
     val_prec_cata_tota_docu,
     val_prec_cont_tota_docu,
     val_prec_cata_tota_loca_unid,
     num_unid_dema_real,
     num_unid_compr,
     num_unid_aten,
     val_prec_fact_tota_loca,
     val_prec_fact_tota_docu,
     val_prec_sin_impu_tota_loca,
     val_prec_neto_tota_loca,
     espo_oid_esta_posi,
     stpo_oid_subt_posi,
     ind_no_impr,
     num_cons,
     val_impo_des_sin_imp_unit_loca,
     val_impo_des_sin_imp_unit_docu,
     val_impo_des_sin_imp_tota,
     val_impo_des_sin_imp_tota_docu)
  VALUES
    (ped_sopo_seq.NEXTVAL,--seq_oid_soli_posi,
     0,
     1,
     1,
     NULL,
     lnoidsolicabe,--seq_oid_soli_cabe,
     NULL,
     11,--v_tpos_oid_tipo_posi, --:11
     v_prod_oid_prod,
     v_val_prec_cata_unit_loca,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     1,
     1,
     1,
     0,
     0,
     0,
     0,
     4,--v_espo_oid_esta_posi, --:4
     15,--v_stpo_oid_subt_posi, --:15
     0,
     NULL,
     0,
     0,
     0,
     0);


     UPDATE ped_numer_solic ns
           SET ns.val_ulti_nume_soli = lnnumsoliinicio+1
         WHERE ns.val_oper = 'PED001'
           AND ns.val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')))
           AND ns.cod_suba = '000'
           AND ns.cod_pais = pscodigopais;

        UPDATE int_rec_cabec_borec c
           SET c.soca_oid_soli_cabe_cccc = lnoidsolicabe
         WHERE c.cod_pais = pscodigopais
           AND c.cod_cabe_bore = pscodigocabecera;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_PROCE_BOREC_NOEXI: ' ||
                             ls_sqlerrm);

END int_pr_rec_proce_borec_noexi;

/**************************************************************************
  Descripcion        : Procesa una Boleta de Recojo Con Discreapncia
  Fecha Creacion     : 12/09/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_borec_discr
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2
) IS

  v_cod_pais                int_rec_cabec_borec.cod_pais%TYPE;
  v_cod_cabe_bore           int_rec_cabec_borec.cod_cabe_bore%TYPE;
  v_soca_oid_soli_cabe      int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_val_nume_bole_desp      int_rec_cabec_borec.val_nume_bole_desp%TYPE;
  v_esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  v_esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  v_num_reco                int_rec_cabec_borec.num_reco%TYPE;
  v_clie_oid_clie           int_rec_cabec_borec.clie_oid_clie%TYPE;
  v_cod_clie                int_rec_cabec_borec.cod_clie%TYPE;
  v_ztad_oid_terri_admin    int_rec_cabec_borec.ztad_oid_terri_admin%TYPE;
  v_cod_regi                int_rec_cabec_borec.cod_regi%TYPE;
  v_cod_zona                int_rec_cabec_borec.cod_zona%TYPE;
  v_cod_secc                int_rec_cabec_borec.cod_secc%TYPE;
  v_cod_terr                int_rec_cabec_borec.cod_terr%TYPE;
  v_more_oid_motn_reco_bore int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  v_fec_ingr                int_rec_cabec_borec.fec_ingr%TYPE;
  v_fec_prog_reco           int_rec_cabec_borec.fec_prog_reco%TYPE;
  v_fec_reco                int_rec_cabec_borec.fec_reco%TYPE;
  v_hor_reco                int_rec_cabec_borec.hor_reco%TYPE;
  v_fec_ulti_actu           int_rec_cabec_borec.fec_ulti_actu%TYPE;
  v_val_nume_bore           int_rec_cabec_borec.val_nume_bore%TYPE;
  v_num_tota_unid_recl      int_rec_cabec_borec.num_tota_unid_recl%TYPE;
  v_num_tota_unid_reco      int_rec_cabec_borec.num_tota_unid_reco%TYPE;
  v_ind_envi_xero           int_rec_cabec_borec.ind_envi_xero%TYPE;
  v_ind_envi_yobe           int_rec_cabec_borec.ind_envi_yobe%TYPE;
  v_ind_regr_yobe           int_rec_cabec_borec.ind_regr_yobe%TYPE;
  v_ind_chk_auto            int_rec_cabec_borec.ind_chk_auto%TYPE;
  v_ind_ocs_proc            int_rec_cabec_borec.ind_ocs_proc%TYPE;
  v_ind_proc_alma_fisi      int_rec_cabec_borec.ind_proc_alma_fisi%TYPE;
  v_num_lote_envi           int_rec_cabec_borec.num_lote_envi%TYPE;
  v_num_lote_devu           int_rec_cabec_borec.num_lote_devu%TYPE;
  v_val_nomb_terc           int_rec_cabec_borec.val_nomb_terc%TYPE;
  v_cod_peri_proc           int_rec_cabec_borec.cod_peri_proc%TYPE;
  v_ind_envi_xer2           int_rec_cabec_borec.ind_envi_xer2%TYPE;
  v_ind_envi_yob2           int_rec_cabec_borec.ind_envi_yob2%TYPE;
  v_ind_regr_yob2           int_rec_cabec_borec.ind_regr_yob2%TYPE;
  v_num_lote_env2           int_rec_cabec_borec.num_lote_env2%TYPE;
  v_num_lote_dev2           int_rec_cabec_borec.num_lote_dev2%TYPE;
  v_fec_ing2                int_rec_cabec_borec.fec_ing2%TYPE;
  v_fec_rec2                int_rec_cabec_borec.fec_rec2%TYPE;
  v_hor_rec2                int_rec_cabec_borec.hor_rec2%TYPE;
  v_more_oid_motn_reco_bor2 int_rec_cabec_borec.more_oid_motn_reco_bor2%TYPE;
  v_val_nomb_ter2           int_rec_cabec_borec.val_nomb_ter2%TYPE;
  v_val_dife_disc           int_rec_cabec_borec.val_dife_disc%TYPE;

  v_cldi_oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE;
  v_ztad_oid_terr_admi      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
  v_terr_oid_terr           zon_terri_admin.terr_oid_terr%TYPE;
  v_zzon_oid_zona           zon_secci.zzon_oid_zona%TYPE;
  v_tdoc_oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE;
  v_tido_oid_tipo_docu      mae_tipo_docum.tido_oid_tipo_docu%TYPE;
  v_ticl_oid_tipo_clie      mae_clien_tipo_subti.ticl_oid_tipo_clie %TYPE;
  v_sbti_oid_subt_clie      mae_clien_tipo_subti.sbti_oid_subt_clie%TYPE;
  v_vepo_oid_valo_estr_geop zon_terri.vepo_oid_valo_estr_geop%TYPE;

  v_fopa_oid_form_pago           ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
  v_ind_perm_unio_sol            ped_tipo_solic_pais.ind_perm_unio%TYPE;
  v_soci_oid_soci                ped_tipo_solic_pais.soci_oid_soci%TYPE;
  v_tspa_oid_tipo_soli_pais_cons ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  v_val_glos_obse                ped_tipo_solic_pais.val_glos%TYPE;
  v_ind_pedi_prue                ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  v_mone_oid_mone                ped_tipo_solic_pais.mone_oid_mone%TYPE;
  v_acfi_oid_acce_fisi           ped_acces_fisic.oid_acce_fisi%TYPE;
  v_sbac_oid_sbac                ped_tipo_solic.sbac_oid_sbac%TYPE;
  v_clso_oid_clas_soli           ped_tipo_solic.clso_oid_clas_soli%TYPE;
  v_oid_alma                     ped_tipo_solic_pais.almc_oid_alma%TYPE;
  v_ind_orde_comp                ped_clase_solic.ind_orde_comp%TYPE;
  v_ind_soli_nega                ped_tipo_solic.ind_soli_nega%TYPE;
  v_oid_tipo_soli_pais           ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  v_almc_oid_alma                ped_tipo_solic_pais.almc_oid_alma%TYPE;

  v_fec_prog_fact cra_perio.fec_fina%TYPE;
  v_perd_oid_peri cra_perio.oid_peri%TYPE;

  v_prod_oid_prod mae_produ.oid_prod%TYPE;

  v_val_prec_cata_unit_loca int_rec_linea_borec.val_prec%TYPE;
  lnoidsolicabe             ped_solic_cabec.oid_soli_cabe%TYPE;

  lnidpais NUMBER;

  lnnumsoliinicio NUMBER;
  lnnumsoliformat NUMBER;

BEGIN

  SELECT cod_pais,
         cod_cabe_bore,
         soca_oid_soli_cabe,
         val_nume_bole_desp,
         esbo_oid_esta_bor1,
         esbo_oid_esta_bor2,
         num_reco,
         clie_oid_clie,
         cod_clie,
         ztad_oid_terri_admin,
         cod_regi,
         cod_zona,
         cod_secc,
         cod_terr,
         more_oid_motn_reco_bore,
         fec_ingr,
         fec_prog_reco,
         fec_reco,
         hor_reco,
         fec_ulti_actu,
         val_nume_bore,
         num_tota_unid_recl,
         num_tota_unid_reco,
         ind_envi_xero,
         ind_envi_yobe,
         ind_regr_yobe,
         ind_chk_auto,
         ind_ocs_proc,
         ind_proc_alma_fisi,
         num_lote_envi,
         num_lote_devu,
         val_nomb_terc,
         cod_peri_proc,
         ind_envi_xer2,
         ind_envi_yob2,
         ind_regr_yob2,
         num_lote_env2,
         num_lote_dev2,
         fec_ing2,
         fec_rec2,
         hor_rec2,
         more_oid_motn_reco_bor2,
         val_nomb_ter2,
         val_dife_disc
    INTO v_cod_pais,
         v_cod_cabe_bore,
         v_soca_oid_soli_cabe,
         v_val_nume_bole_desp,
         v_esbo_oid_esta_bor1,
         v_esbo_oid_esta_bor2,
         v_num_reco,
         v_clie_oid_clie,
         v_cod_clie,
         v_ztad_oid_terri_admin,
         v_cod_regi,
         v_cod_zona,
         v_cod_secc,
         v_cod_terr,
         v_more_oid_motn_reco_bore,
         v_fec_ingr,
         v_fec_prog_reco,
         v_fec_reco,
         v_hor_reco,
         v_fec_ulti_actu,
         v_val_nume_bore,
         v_num_tota_unid_recl,
         v_num_tota_unid_reco,
         v_ind_envi_xero,
         v_ind_envi_yobe,
         v_ind_regr_yobe,
         v_ind_chk_auto,
         v_ind_ocs_proc,
         v_ind_proc_alma_fisi,
         v_num_lote_envi,
         v_num_lote_devu,
         v_val_nomb_terc,
         v_cod_peri_proc,
         v_ind_envi_xer2,
         v_ind_envi_yob2,
         v_ind_regr_yob2,
         v_num_lote_env2,
         v_num_lote_dev2,
         v_fec_ing2,
         v_fec_rec2,
         v_hor_rec2,
         v_more_oid_motn_reco_bor2,
         v_val_nomb_ter2,
         v_val_dife_disc
    FROM int_rec_cabec_borec
   WHERE cod_cabe_bore = pscodigocabecera
     AND cod_pais = pscodigopais;

  SELECT m.cod_clie                   v_cod_clie,
         m.oid_clie                   v_clie_oid_clie,
         d.oid_clie_dire              v_cldi_oid_clie_dire,
         ad.ztad_oid_terr_admi        v_ztad_oid_terr_admi,
         ztad.terr_oid_terr           v_terr_oid_terr,
         zs.zzon_oid_zona             v_zzon_oid_zona,
         ide.tdoc_oid_tipo_docu       v_tdoc_oid_tipo_docu,
         mt.tido_oid_tipo_docu        v_tido_oid_tipo_docu,
         ms.ticl_oid_tipo_clie        v_ticl_oid_tipo_clie,
         ms.sbti_oid_subt_clie        v_sbti_oid_subt_clie,
         terr.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop
    INTO v_cod_clie,
         v_clie_oid_clie,
         v_cldi_oid_clie_dire,
         v_ztad_oid_terr_admi,
         v_terr_oid_terr,
         v_zzon_oid_zona,
         v_tdoc_oid_tipo_docu,
         v_tido_oid_tipo_docu,
         v_ticl_oid_tipo_clie,
         v_sbti_oid_subt_clie,
         v_vepo_oid_valo_estr_geop
    FROM mae_clien             m,
         mae_clien_direc       d,
         mae_clien_unida_admin ad,
         mae_clien_ident       ide,
         zon_terri_admin       ztad,
         zon_terri             terr,
         zon_secci             zs,
         mae_tipo_docum        mt,
         mae_clien_tipo_subti  ms
   WHERE m.oid_clie = d.clie_oid_clie
     AND m.oid_clie = ad.clie_oid_clie
     AND m.oid_clie = ide.clie_oid_clie
     AND ztad.oid_terr_admi = ad.ztad_oid_terr_admi
     AND ztad.zscc_oid_secc = zs.oid_secc
     AND mt.oid_tipo_docu = ide.tdoc_oid_tipo_docu
     AND ide.val_iden_docu_prin = 1
     AND ad.perd_oid_peri_fin IS NULL
     AND d.ind_dire_ppal = 1
     AND d.ind_elim = 0
     AND m.oid_clie = v_clie_oid_clie -- oid de cliente se recupera de la cabecera de la boleta de recojo
     AND ms.clie_oid_clie = m.oid_clie
     AND ms.ind_ppal = 1
     AND terr.oid_terr = ztad.terr_oid_terr;

    SELECT tido_oid_tipo_docu_cont
      INTO v_tido_oid_tipo_docu
      FROM fac_tipo_docum
     WHERE oid_tipo_docu = v_tido_oid_tipo_docu;

    SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
           tsp.ind_perm_unio      v_ind_perm_unio_sol,
           tsp.soci_oid_soci      v_soci_oid_soci,
           tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
           tsp.val_glos           v_val_glos_obse,
           tsp.ind_pedi_prue      v_ind_pedi_prue,
           tsp.mone_oid_mone      v_mone_oid_mone,
           NULL                   v_acfi_oid_acce_fisi,
           ts.sbac_oid_sbac       v_sbac_oid_sbac,
           ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
           tsp.almc_oid_alma      v_oid_alma,
           cs.ind_orde_comp       v_ind_orde_comp,
           ts.ind_soli_nega       v_ind_soli_nega,
           tsp.oid_tipo_soli_pais v_oid_tipo_soli_pais,
           tsp.almc_oid_alma      v_almc_oid_alma
      INTO v_fopa_oid_form_pago,
           v_ind_perm_unio_sol,
           v_soci_oid_soci,
           v_tspa_oid_tipo_soli_pais_cons,
           v_val_glos_obse,
           v_ind_pedi_prue,
           v_mone_oid_mone,
           v_acfi_oid_acce_fisi,
           v_sbac_oid_sbac,
           v_clso_oid_clas_soli,
           v_oid_alma,
           v_ind_orde_comp,
           v_ind_soli_nega,
           v_oid_tipo_soli_pais,
           v_almc_oid_alma
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts,
           ped_clase_solic     cs
     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.clso_oid_clas_soli = cs.oid_clas_soli
       AND ts.cod_tipo_soli = 'CABR';

    SELECT fec_fina v_fec_prog_fact,
           oid_peri v_perd_oid_peri
      INTO v_fec_prog_fact,
           v_perd_oid_peri
      FROM cra_perio
     WHERE trunc(SYSDATE) <= fec_fina
       AND trunc(SYSDATE) >= fec_inic
       AND rownum = 1
     ORDER BY fec_inic ASC;

    SELECT ped_soca_seq.NEXTVAL INTO lnoidsolicabe FROM dual;

    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000');

    SELECT to_char(SYSDATE, 'YY') ||
           lpad(lnnumsoliinicio, 8, '0')
      INTO lnnumsoliformat
      FROM dual;

    INSERT INTO ped_solic_cabec
      (oid_soli_cabe,
       fec_prog_fact,
       fec_fact,
       num_clien,
       tspa_oid_tipo_soli_pais,
       tids_oid_tipo_desp,
       almc_oid_alma,
       modu_oid_modu,
       ticl_oid_tipo_clie,
       perd_oid_peri,
       soca_oid_soli_cabe,
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
       val_usua,
       val_tasa_impu,
       fec_cron,
       ind_perm_unio_sol,
       val_tipo_camb,
       num_docu_orig,
       val_base_flet_loca,
       val_impo_flet_loca,
       val_impo_flet_tota_loca,
       val_impo_flet_sin_impu_tota,
       val_reca_flet_loca,
       val_otro_reca_loca,
       val_tota_paga_loca,
       val_prec_cata_tota_loca,
       val_prec_cata_sin_impu_tota,
       val_prec_fact_tota_loca,
       val_impo_impu_tota_loca,
       val_impo_desc_1_tota_loca,
       val_impo_desc_1_tota_docu,
       val_impo_desc_1_sin_impu_tota,
       val_impo_desc_3_tota_docu,
       val_impo_desc_3_sin_impu_tota,
       val_impo_desc_tota_loca,
       val_impo_dto_1_sin_imp_tot_loc,
       val_impo_redo_loca,
       val_base_flet_docu,
       val_impo_flet_docu,
       val_impo_desc_tota_docu,
       val_impo_flet_sin_impu_docu,
       val_reca_flet_docu,
       val_otro_reca_docu,
       val_tota_flet_docu,
       val_impo_flet_tota_docu,
       val_tota_flet_loca,
       val_tota_paga_docu,
       val_prec_cata_tota_docu,
       val_prec_cata_sin_impu_tota_do,
       val_prec_cont_tota_loca,
       val_prec_cont_sin_impu_tota,
       val_prec_cont_sin_impu_tota_1,
       val_prec_fact_tota_docu,
       val_prec_cata_tota_loc_uni_dem,
       val_prec_neto_tota_docu,
       val_prec_neto_tota_loca,
       val_impo_impu_tota_docu,
       val_impo_redo_docu,
       val_impo_redo_cons_loca,
       val_impo_redo_cons_docu,
       ind_oc,
       ind_pedi_prue,
       ind_ts_no_conso,
       val_glos_obse,
       val_impo_desc_3_tota_loca,
       val_impo_dto_3_sin_imp_tot_loc,
       pais_oid_pais,
       tido_oid_tipo_docu,
       vepo_oid_valo_estr_geop,
       esso_oid_esta_soli,
       grpr_oid_grup_proc,
       sbti_oid_subt_clie,
       acfi_oid_acce_fisi,
       tspa_oid_tipo_soli_pais_cons,
       fopa_oid_form_pago,
       clso_oid_clas_soli,
       ztad_oid_terr_admi,
       oper_oid_oper,
       proc_oid_proc,
       ind_inte_lari_gene,
       fec_prog_fact_comp)
    VALUES
      (lnoidsolicabe, --seq_oid_soli_cabe,
       v_fec_prog_fact,
       NULL,--trunc(SYSDATE),
       0,
       v_oid_tipo_soli_pais, ---2001,--v_tspa_oid_tipo_soli_pais,
       3, --v_tids_oid_tipo_desp, --:3
       v_almc_oid_alma,
       27, --v_modu_oid_modu, --:27
       v_ticl_oid_tipo_clie,
       v_perd_oid_peri,
       NULL,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_cldi_oid_clie_dire,
       v_tdoc_oid_tipo_docu,
       v_soci_oid_soci,
       v_sbac_oid_sbac,
       v_terr_oid_terr,
       v_zzon_oid_zona,
       lnnumsoliformat + 1, --seq_val_nume_soli,
       NULL, --v_val_usua,
       0,
       trunc(SYSDATE),
       1,
       1,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       1,
       'OPERACION SUJETA A PERCEPCION DEL IGV',
       0,
       0,
       lnidpais, --v_pais_oid_pais,
       v_tido_oid_tipo_docu,
       v_vepo_oid_valo_estr_geop,
       1,
       4,
       v_sbti_oid_subt_clie,
       1,
       v_tspa_oid_tipo_soli_pais_cons,
       v_fopa_oid_form_pago,
       v_clso_oid_clas_soli,
       v_ztad_oid_terr_admi,
       44, --v_oper_oid_oper, --:44
       6, --v_proc_oid_proc, --:6
       0,
       to_char(SYSDATE, 'yyyymmdd')); --:--v_fec_prog_fact_comp

    /*OidProducto si es cargo*/
    SELECT oid_prod v_prod_oid_prod
      INTO v_prod_oid_prod
      FROM mae_produ
     WHERE cod_sap = 9999999999;

    SELECT imp_abono--(-1) * val_dife_disc
      INTO v_val_prec_cata_unit_loca -- crear campo que contiene diferencia de discrepante
      FROM int_rec_cabec_borec
     WHERE cod_cabe_bore = pscodigocabecera;

    INSERT INTO ped_solic_posic
      (oid_soli_posi,
       cod_posi,
       num_unid_dema,
       num_unid_por_aten,
       val_tasa_impu,
       soca_oid_soli_cabe,
       taim_oid_tasa_impu,
       tpos_oid_tipo_posi,
       prod_oid_prod,
       val_prec_cata_unit_loca,
       val_prec_cont_unit_loca,
       val_prec_cata_unit_docu,
       val_prec_conta_unit_docu,
       val_prec_fact_unit_loca,
       val_prec_fact_unit_docu,
       val_prec_sin_impu_unit_loca,
       val_prec_sin_impu_unit_docu,
       val_prec_sin_impu_tota_docu,
       val_impo_desc_unit_loca,
       val_prec_neto_unit_loca,
       val_prec_neto_tota_docu,
       val_prec_neto_unit_docu,
       val_prec_tota_tota_loca,
       val_prec_tota_tota_docu,
       val_impo_impu_unit_loca,
       val_impo_impu_unit_docu,
       val_impo_desc_tota_docu,
       val_impo_impu_tota_loca,
       val_impo_impu_tota_docu,
       val_impo_desc_tota_loca,
       val_prec_tota_unit_loca,
       val_prec_tota_unit_docu,
       val_prec_cont_tota_loca,
       val_prec_cata_tota_loca,
       val_prec_cata_tota_docu,
       val_prec_cont_tota_docu,
       val_prec_cata_tota_loca_unid,
       num_unid_dema_real,
       num_unid_compr,
       num_unid_aten,
       val_prec_fact_tota_loca,
       val_prec_fact_tota_docu,
       val_prec_sin_impu_tota_loca,
       val_prec_neto_tota_loca,
       espo_oid_esta_posi,
       stpo_oid_subt_posi,
       ind_no_impr,
       num_cons,
       val_impo_des_sin_imp_unit_loca,
       val_impo_des_sin_imp_unit_docu,
       val_impo_des_sin_imp_tota,
       val_impo_des_sin_imp_tota_docu)
    VALUES
      (ped_sopo_seq.NEXTVAL, --seq_oid_soli_posi,
       0,
       -1,
       -1,
       NULL,
       lnoidsolicabe, --seq_oid_soli_cabe,
       NULL,
       11, --v_tpos_oid_tipo_posi, --:11
       v_prod_oid_prod,
       v_val_prec_cata_unit_loca,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       -1,
       -1,
       -1,
       0,
       0,
       0,
       0,
       4, --v_espo_oid_esta_posi, --:4
       15, --v_stpo_oid_subt_posi, --:15
       0,
       NULL,
       0,
       0,
       0,
       0);

  UPDATE ped_numer_solic ns
     SET ns.val_ulti_nume_soli = lnnumsoliinicio + 1
   WHERE ns.val_oper = 'PED001'
     AND ns.val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')))
     AND ns.cod_suba = '000'
     AND ns.cod_pais = pscodigopais;

  UPDATE int_rec_cabec_borec c
     SET --c.ind_ocs_proc = 'V',
         c.soca_oid_soli_cabe_abon = lnoidsolicabe
   WHERE c.cod_pais = pscodigopais
     AND c.cod_cabe_bore = pscodigocabecera;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_PROCE_BOREC_DISCR: ' ||
                             ls_sqlerrm);

END int_pr_rec_proce_borec_discr;
/**************************************************************************
  Descripcion       : Devuelve el numero de pedidos acumulados
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
PROCEDURE int_pr_rec_gener_mensj_recla
(
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigodmensaje    VARCHAR2
) IS

  lnidmarca   NUMBER;
  lnidcanal   NUMBER;
  lnidperiodo NUMBER;
  lnOidTipoSoli NUMBER;

  lnOidMensaje NUMBER;

BEGIN

  lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca); -- id del marca consultante
  lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal); -- id del canal consultante
  lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                            lnidmarca,
                                                            lnidcanal); -- id del periodo consultante

  lnOidTipoSoli := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('SOC');

BEGIN

  SELECT oid_mens
    INTO lnoidmensaje
    FROM msg_mensa
   WHERE cod_mens = pscodigodmensaje;


  INSERT INTO msg_buzon_mensa
    (oid_buzo_mens,
     num_secu,
     clie_oid_clie,
     mens_oid_mens,
     modu_oid_modu_orig,
     dato_vari_11,
     dato_vari_10,
     dato_vari_04,
     dato_vari_09,
     dato_vari_12,
     dato_vari_13,
     dato_vari_14,
     fec_grab,
     ind_list_cons,
     ind_acti)
    (SELECT msg_bume_seq.NEXTVAL,
            msg_bum2_seq.NEXTVAL,
            clie_oid_clie,
            lnOidMensaje,
            15,
            substr(producto,0,26),
            codigo,
            documento,
            reclamo,
            solic,
            atend,
            observaciones,
            SYSDATE,
            1,
            1
       FROM (select *
from
(
select clie_oid_clie , producto, codigo,documento,reclamo,solic,atend, observaciones from
(
select distinct clie_oid_clie , producto, codigo,documento,reclamo,solic,OPRE_OID_OPER_RECL, max(atend) atend,  max(observaciones) observaciones
from (
SELECT rec.clie_oid_clie,
                    ocr_solic_pedidos.gen_fn_desc_prod(lin.prod_oid_prod) producto,
                    decode(lin.copa_oid_para_gral, NULL,
                           int_pkg_recla.gen_fn_devue_cod_venta_mfact(lin.tofe_oid_tipo_ofer,
                                                                       lin.mafa_oid_matr_fact,
                                                                       lin.prod_oid_prod),
                           int_pkg_recla.gen_fn_devue_cod_venta_ficti(lin.lopa_oid_lote_prem_arti,
                                                                       lin.panp_oid_para_nive_prem,
                                                                       lin.copa_oid_para_gral,
                                                                       lin.prod_oid_prod)) codigo,
                    to_char(rec.num_recl) documento,
                    m.des_oper_homl reclamo,
                    lin.num_unid_recl solic,
                    0 as atend,
                    'NOTA CREDITO: ' || fd.NUM_DOCU_CONT_INTE
                               || ', VAL: ' || lin.imp_abon observaciones,LIN.OPRE_OID_OPER_RECL
               FROM rec_cabec_recla       rec,
                    rec_opera_recla       ope,
                    rec_tipos_opera       top,
                    rec_linea_opera_recla lin,
                    rec_opera             rop,
                    rec_solic_opera       ss,
                    ped_solic_posic       pos,
                    ped_solic_cabec       ped,
                    fac_docum_conta_cabec fd,
                    fac_docum_conta_linea fdl,
                    INT_MAE_OPERA_HOMOL_BOREC m,
                    INT_REC_OPERA_HOMOL_BOREC r
              WHERE rec.esre_oid_esta_recl in (4,6)
                AND lin.opre_oid_oper_recl = ope.oid_oper_recl
                AND ope.care_oid_cabe_recl = rec.oid_cabe_recl
                AND rec.perd_oid_peri_recl = lnidperiodo
                AND top.oid_tipo_oper = ope.tiop_oid_tipo_oper
                AND rop.oid_oper = top.rope_oid_oper
                AND lin.timo_oid_tipo_movi = 2
                AND r.COD_OPER_homl = m.COD_OPER_HOML
                AND r.COD_OPER_REC = rop.cod_oper
                AND ss.OPRE_OID_OPER_RECL = lin.opre_oid_oper_recl
                AND lin.tspa_oid_tipo_soli_pais = ss.tspa_oid_tipo_soli_pais
                AND pos.soca_oid_soli_cabe = ss.soca_oid_soli_cabe
                AND pos.prod_oid_prod = lin.prod_oid_prod
                and pos.soca_oid_soli_cabe = ped.oid_soli_cabe
                and ss.soca_oid_soli_cabe = ped.oid_soli_cabe
                and ped.soca_oid_soli_cabe = fd.soca_oid_soli_cabe
                and FD.OID_CABE = FDL.DCCA_OID_CABE
                and POS.OID_SOLI_POSI = FDL.SOPO_OID_SOLI_POSI (+)
                AND decode(lin.copa_oid_para_gral, NULL,
                           int_pkg_recla.gen_fn_devue_cod_venta_mfact(lin.tofe_oid_tipo_ofer,
                                                                       lin.mafa_oid_matr_fact,
                                                                       lin.prod_oid_prod),
                           int_pkg_recla.gen_fn_devue_cod_venta_ficti(lin.lopa_oid_lote_prem_arti,
                                                                       lin.panp_oid_para_nive_prem,
                                                                       lin.copa_oid_para_gral,
                                                                       lin.prod_oid_prod)) = decode(lin.copa_oid_para_gral, NULL,pos.val_codi_vent,pos.val_codi_vent_fict)
                AND (rec.clie_oid_clie IN
                    (
                    SELECT clie_oid_clie
                        FROM ped_solic_cabec d
                       WHERE d.perd_oid_peri = lnidperiodo
                         AND (d.tspa_oid_tipo_soli_pais IN
                             (SELECT oid_tipo_soli_pais
                                 FROM ped_tipo_solic_pais c,
                                      ped_tipo_solic      s
                                WHERE s.oid_tipo_soli =
                                      c.tsol_oid_tipo_soli
                                  AND s.clso_oid_clas_soli IN
                                      (14, 7)
                                  AND s.ind_soli_nega = 0
                                  AND s.ind_cons = 0) OR
                             d.tspa_oid_tipo_soli_pais = lnOidTipoSoli)
                         AND d.fec_fact IS NULL
                         AND d.grpr_oid_grup_proc = 4
                         AND d.clie_oid_clie = rec.clie_oid_clie
                         ))
UNION
SELECT rec.clie_oid_clie,
                    ocr_solic_pedidos.gen_fn_desc_prod(lin.prod_oid_prod) producto,
                    decode(lin.copa_oid_para_gral, NULL,
                           int_pkg_recla.gen_fn_devue_cod_venta_mfact(lin.tofe_oid_tipo_ofer,
                                                                       lin.mafa_oid_matr_fact,
                                                                       lin.prod_oid_prod),
                           int_pkg_recla.gen_fn_devue_cod_venta_ficti(lin.lopa_oid_lote_prem_arti,
                                                                       lin.panp_oid_para_nive_prem,
                                                                       lin.copa_oid_para_gral,
                                                                       lin.prod_oid_prod)) codigo,
                    to_char(rec.num_recl) documento,
                    m.des_oper_homl reclamo,
                    lin.num_unid_recl solic,
                    ABS(pos.num_unid_compr) atend,
                    '' as observaciones,LIN.OPRE_OID_OPER_RECL
               FROM rec_cabec_recla       rec,
                    rec_opera_recla       ope,
                    rec_tipos_opera       top,
                    rec_linea_opera_recla lin,
                    rec_opera             rop,
                    rec_solic_opera       ss,
                    ped_solic_posic       pos,
                    INT_MAE_OPERA_HOMOL_BOREC m,
                    INT_REC_OPERA_HOMOL_BOREC r
              WHERE rec.esre_oid_esta_recl in (4,6)
                AND lin.opre_oid_oper_recl = ope.oid_oper_recl
                AND ope.care_oid_cabe_recl = rec.oid_cabe_recl
                AND rec.perd_oid_peri_recl = lnidperiodo
                AND top.oid_tipo_oper = ope.tiop_oid_tipo_oper
                AND rop.oid_oper = top.rope_oid_oper
                AND lin.timo_oid_tipo_movi = 1
                AND r.COD_OPER_homl = m.COD_OPER_HOML
                AND r.COD_OPER_REC = rop.cod_oper
                AND ss.OPRE_OID_OPER_RECL = lin.opre_oid_oper_recl
                AND lin.tspa_oid_tipo_soli_pais = ss.tspa_oid_tipo_soli_pais
                AND pos.soca_oid_soli_cabe = ss.soca_oid_soli_cabe
                AND pos.prod_oid_prod = lin.prod_oid_prod
                AND decode(lin.copa_oid_para_gral, NULL,
                           int_pkg_recla.gen_fn_devue_cod_venta_mfact(lin.tofe_oid_tipo_ofer,
                                                                       lin.mafa_oid_matr_fact,
                                                                       lin.prod_oid_prod),
                           int_pkg_recla.gen_fn_devue_cod_venta_ficti(lin.lopa_oid_lote_prem_arti,
                                                                       lin.panp_oid_para_nive_prem,
                                                                       lin.copa_oid_para_gral,
                                                                       lin.prod_oid_prod)) = decode(lin.copa_oid_para_gral, NULL,pos.val_codi_vent,pos.val_codi_vent_fict)
                AND (rec.clie_oid_clie IN
                    (
                    SELECT clie_oid_clie
                        FROM ped_solic_cabec d
                       WHERE d.perd_oid_peri = lnidperiodo
                         AND (d.tspa_oid_tipo_soli_pais IN
                             (SELECT oid_tipo_soli_pais
                                 FROM ped_tipo_solic_pais c,
                                      ped_tipo_solic      s
                                WHERE s.oid_tipo_soli =
                                      c.tsol_oid_tipo_soli
                                  AND s.clso_oid_clas_soli IN
                                      (14, 7)
                                  AND s.ind_soli_nega = 0
                                  AND s.ind_cons = 0) OR
                             d.tspa_oid_tipo_soli_pais = lnOidTipoSoli)
                         AND d.fec_fact IS NULL
                         AND d.grpr_oid_grup_proc = 4
                         AND d.clie_oid_clie = rec.clie_oid_clie
                         ))
) tablacdr
group by clie_oid_clie , producto, codigo,documento,reclamo,solic,OPRE_OID_OPER_RECL) tablafinal
UNION
SELECT  C.OID_CLIE CLIE_OID_CLIE,
        INT_PKG_RECLA.GEN_FN_DESC_PROD(D.PROD_OID_PROD_DEVU) PRODUCTO,
        TO_CHAR(D.COD_VENT_DEVU)  CODIGO,
        C.NUM_DOCU DOCUMENTO,
        DES_OPER_HOML  RECLAMO,
        D.CAN_VENT_DEVU SOLIC,
        0 as atend,
        /*'RECHAZO: ' || MV.DES_LARG_MENS OBSERVACIONES*/
        'RECHAZO: ' || MV.DES_LARG_MENS || ' ' || decode(DE.cod_vali,COD_VALIDACION_CENTRO_SERVICIO, D.DES_CENT_SERV
                                                                                                    ,'') OBSERVACIONES
 FROM INT_SOLIC_CONSO_POVEN_DETAL D,
 INT_SOLIC_CONSO_POVEN_CABEC C,
 STO_DETAL_DOCUM_EXCEP DE,
 STO_DOCUM_DIGIT DG,
 STO_PARAM_VALID SP,
 MAE_CLIEN M,
 STO_MENSA_VALID MV,
 INT_MAE_OPERA_HOMOL_BOREC mb,
 INT_REC_OPERA_HOMOL_BOREC r
 WHERE  DE.COD_TIPO_DOCU IN ('SPVD')
 AND D.NUM_DOCU = C.NUM_DOCU
 AND D.NUM_LOTE = C.NUM_LOTE
 AND D.COD_CLIE = C.COD_CLIE
 AND D.COD_PAIS = C.COD_PAIS
 AND D.COD_PERI = C.COD_PERI
 AND M.COD_CLIE = C.COD_CLIE
 and r.COD_OPER_homl = mb.COD_OPER_HOML
 and r.COD_OPER_REC = D.cod_oper
 AND D.SEC_NUME_DOCU = DG.SEC_NUME_DOCU
 AND DG.SEC_NUME_DOCU = DE.SEC_NUME_DOCU
 AND DG.IND_RECH = 1
 AND SP.COD_VALI = DE.COD_VALI
 AND DE.COD_TIPO_DOCU = D.DOCU_COD_TIPO_DOCU
 AND DE.COD_TIPO_DOCU = DG.COD_TIPO_DOCU
 AND DG.COD_ULTI_VALI_ERRO = DE.COD_VALI
 AND MV.COD_VALI = SP.COD_VALI
 AND D.COD_VENT_DEVU IS NOT NULL
 AND C.OID_PERI_RECL = lnidperiodo
 AND (C.oid_clie IN
                    (SELECT clie_oid_clie
                        FROM ped_solic_cabec d
                       WHERE d.perd_oid_peri = lnidperiodo
                         AND (d.tspa_oid_tipo_soli_pais IN
                             (SELECT oid_tipo_soli_pais
                                 FROM ped_tipo_solic_pais c,
                                      ped_tipo_solic      s
                                WHERE s.oid_tipo_soli =
                                      c.tsol_oid_tipo_soli
                                  AND s.clso_oid_clas_soli IN
                                      (14, 7)
                                  AND s.ind_soli_nega = 0
                                  AND s.ind_cons = 0) OR
                             d.tspa_oid_tipo_soli_pais = lnOidTipoSoli)
                         AND d.fec_fact IS NULL
                         AND d.grpr_oid_grup_proc = 4
                         AND d.clie_oid_clie = c.oid_clie)
                         )
)
order by 1,3,8                                                  ));

     EXCEPTION
       WHEN no_data_found THEN
      RETURN ;
     END;

EXCEPTION

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_GENER_MENSJ_RECLA: ' ||
                             ls_sqlerrm);

END int_pr_rec_gener_mensj_recla;


 /**************************************************************************
   Descripcion        : Ejecuta un Mensaje de Reclamos
   Fecha Creacion     : 25/09/2006
   Parametros Entrada :
              psSentencia : Sentencia SQL a ejecutar
   Autor              : Jose Cairampoma
   ***************************************************************************/
 PROCEDURE int_pr_ejec_mensa_recla_dinam
 (
   psprocedure     VARCHAR2,
   pscodigomarca   VARCHAR2,
   pscodigocanal   VARCHAR2,
   pscodigoperiodo VARCHAR2,
   pnoidmensaje    NUMBER
 ) IS

 BEGIN
   EXECUTE IMMEDIATE 'begin '   || psprocedure ||
                     '(:2, :3, :4,:5); end;'
     USING IN pscodigomarca, IN pscodigocanal, IN pscodigoperiodo, IN pnoidmensaje;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     IF ln_sqlcode < 0 THEN
       raise_application_error(-20123,
                               'ERROR INT_PR_EJEC_MENSA_RECLA_DINAM: ' ||
                                ls_sqlerrm);
     END IF;
 END int_pr_ejec_mensa_recla_dinam;


 /**************************************************************************
   Descripcion        : Bloquea Consultora POr Boleta de Recojo CErrada no Exitosa
   Fecha Creacion     : 10/12/2006
   Parametros Entrada :
              psSentencia : Sentencia SQL a ejecutar
   Autor              : Jose Cairampoma
   ***************************************************************************/
PROCEDURE rec_pr_bloqu_consu
(
  pscodigocliente VARCHAR2,
  pscodigousuario VARCHAR2
) IS
  lnoidtipobloqueo        NUMBER(12);
  lnoidvaloraccionbloqueo NUMBER(12);
  lnoidcliente            mae_clien.oid_clie%TYPE;

  pscodigopais            sto_param_gener_occrr.cod_pais%TYPE;
  lsparametrovalBLOQBR    sto_param_gener_occrr.val_param%TYPE;
  lsflagBLOQBR            sto_param_gener_occrr.val_param%TYPE;


  CURSOR c_oidbloqueo(vnoidtipobloqueo NUMBER, vnoidcliente NUMBER) IS

    SELECT oid_bloq
      FROM mae_clien_bloqu
     WHERE clie_oid_clie = vnoidcliente
       AND fec_desb IS NULL
       AND tibq_oid_tipo_bloq = vnoidtipobloqueo;

  TYPE t_oid_bloq IS TABLE OF mae_clien_bloqu.oid_bloq%TYPE;

  v_oid_bloq t_oid_bloq;

  rows NATURAL := 10000; -- Numero de filas a procesar cada vez
  i    BINARY_INTEGER := 0;

BEGIN

  --- se verifica si se bloquea o no a la consultora
  select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

  lsparametrovalBLOQBR := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_BLOQ_BR');

  lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente);

  IF lsparametrovalBLOQBR = 'N' then
      lsflagBLOQBR := 'N';
  else

      lsflagBLOQBR := 'S';

      SELECT oid_tipo_bloq
        INTO lnoidtipobloqueo
        FROM mae_tipo_bloqu
       WHERE cod_tipo_bloq = 'BR';

      SELECT OID_VALO_ACCI_BLOQ
      INTO lnOidValorAccionBloqueo
      FROM MAE_VALOR_ACCIO_BLOQU
      WHERE COD_VALO_BLOQ = 'A';

      OPEN c_oidbloqueo(lnoidtipobloqueo, lnoidcliente);
      LOOP
        FETCH c_oidbloqueo BULK COLLECT
          INTO v_oid_bloq LIMIT rows;

        IF v_oid_bloq.COUNT > 0 THEN
          -- Actualizamos Documentos Validados OK
          FORALL i IN 1 .. v_oid_bloq.COUNT
            UPDATE mae_clien_bloqu
               SET fec_desb                = trunc(SYSDATE),
                   val_usua_desb           = pscodigousuario,
                   maab_oid_valo_acci_desb = lnoidvaloraccionbloqueo,
                   obs_desb                = 'NUEVA BR NO EXITOSA'
             WHERE oid_bloq = v_oid_bloq(i);
        END IF;
        EXIT WHEN c_oidbloqueo%NOTFOUND;
      END LOOP;
      CLOSE c_oidbloqueo;

      INSERT INTO mae_clien_bloqu
        (oid_bloq,
         clie_oid_clie,
         tibq_oid_tipo_bloq,
         fec_bloq,
         val_moti_bloq,
         val_usua_bloq,
         obs_bloq,
         maab_oid_valo_acci_bloq)
      VALUES
        (mae_clbl_seq.NEXTVAL,
         lnoidcliente,
         lnoidtipobloqueo,
         trunc(SYSDATE),
         'BR NO EXITOSA',
         pscodigousuario,
         'BR NO EXITOSA',
         lnoidvaloraccionbloqueo);
     END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      raise_application_error(-20123,
                              'ERROR REC_PR_BLOQU_CONSU: ' ||
                               ls_sqlerrm);
    END IF;
END rec_pr_bloqu_consu;

 /**************************************************************************
   Descripcion        : Desbloquea Consultora POr Boleta de Recojo CErrada no Exitosa
   Fecha Creacion     : 10/12/2006
   Parametros Entrada :
              psSentencia : Sentencia SQL a ejecutar
   Autor              : Jose Cairampoma
   ***************************************************************************/
PROCEDURE rec_pr_desbl_consu
(
  pscampana         VARCHAR2
) IS
  lnoidtipobloqueo        NUMBER(12);
  lnoidvaloraccionbloqueo NUMBER(12);
  lnoidcliente            mae_clien.oid_clie%TYPE;

  pscodigopais            sto_param_gener_occrr.cod_pais%TYPE;
  lsparametrovalBLOQBR    sto_param_gener_occrr.val_param%TYPE;
  lsflagBLOQBR            sto_param_gener_occrr.val_param%TYPE;


  CURSOR c_oidbloqueo(vnoidtipobloqueo NUMBER, vnoidcliente NUMBER) IS

    SELECT oid_bloq
      FROM mae_clien_bloqu
     WHERE clie_oid_clie = vnoidcliente
       AND fec_desb IS NULL
       AND tibq_oid_tipo_bloq = vnoidtipobloqueo;

  TYPE t_oid_bloq IS TABLE OF mae_clien_bloqu.oid_bloq%TYPE;

  v_oid_bloq t_oid_bloq;

  rows NATURAL := 10000; -- Numero de filas a procesar cada vez
  i    BINARY_INTEGER := 0;

BEGIN

  --- se verifica si se bloquea o no a la consultora
  select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

  lsparametrovalBLOQBR := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_BLOQ_BR');


  IF lsparametrovalBLOQBR = 'N' then
      lsflagBLOQBR := 'N';
  else

      lsflagBLOQBR := 'S';


      UPDATE MAE_CLIEN_BLOQU
      SET FEC_DESB = TRUNC(SYSDATE),
          VAL_USUA_DESB = 'USUARIO_CIERRE',
          MAAB_OID_VALO_ACCI_DESB = (
                  SELECT OID_VALO_ACCI_BLOQ
                  FROM MAE_VALOR_ACCIO_BLOQU
                  WHERE COD_VALO_BLOQ = 'A'
          ),
          OBS_DESB = 'DESBLOQUEO BR NO EXITOSA POR CIERRE'
      WHERE OID_BLOQ IN (
              SELECT OID_BLOQ
              FROM MAE_CLIEN_BLOQU
              WHERE FEC_BLOQ <= (
                      SELECT FEC_FINA
                      FROM
                      (
                      SELECT ROWNUM AS NUM, T.COD_PERI PERI, T.FEC_FINA
                      FROM
                      (
                      SELECT C.OID_PERI,
                             C.FEC_INIC,
                             C.FEC_FINA,
                             O.COD_PERI
                      FROM CRA_PERIO C, SEG_PERIO_CORPO O
                      WHERE FEC_INIC < (
                              SELECT CC.FEC_INIC
                              FROM CRA_PERIO CC,
                                   SEG_PERIO_CORPO OO
                              WHERE CC.PERI_OID_PERI = OO.OID_PERI
                              AND OO.COD_PERI = pscampana -- Parametro PERIODO que se esta cerrando
                      )
                      AND O.OID_PERI = C.PERI_OID_PERI
                      ORDER BY 2 DESC
                      ) T
                      )
                      WHERE NUM = 2
              )
              AND FEC_DESB IS NULL
              AND TIBQ_OID_TIPO_BLOQ IN (
                      SELECT OID_TIPO_BLOQ
                      FROM MAE_TIPO_BLOQU
                      WHERE COD_TIPO_BLOQ = 'BR'
                  )
      );


 END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      raise_application_error(-20123,
                              'ERROR REC_PR_DESBL_CONSU: ' ||
                               ls_sqlerrm);
    END IF;
END rec_pr_desbl_consu;


/**************************************************************************
   Descripcion        : Devuelve el indicador de Bloqueo por post Venta
                        1: Bolequeado
                        0: No bLoqueado
   Fecha Creacion     : 10/12/2006
   Autor              : Jose Cairampoma
   ***************************************************************************/
FUNCTION rec_fn_devue_indic_bloqu_consu(pscodigocliente VARCHAR2)
  RETURN NUMBER IS
  lnoidtipobloqueo        NUMBER(12);
  lnoidvaloraccionbloqueo NUMBER(12);

  lnoidcliente mae_clien.oid_clie%TYPE;

  lnnumbloqueos NUMBER(12);

BEGIN

  lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente);

  SELECT oid_tipo_bloq
    INTO lnoidtipobloqueo
    FROM mae_tipo_bloqu
   WHERE cod_tipo_bloq = 'BR';

  SELECT oid_valo_acci_bloq
    INTO lnoidvaloraccionbloqueo
    FROM mae_valor_accio_bloqu
   WHERE cod_valo_bloq = 'A';

  SELECT COUNT(*)
    INTO lnnumbloqueos
    FROM mae_clien_bloqu
   WHERE clie_oid_clie = lnoidcliente
     AND fec_desb IS NULL
     AND tibq_oid_tipo_bloq = lnoidtipobloqueo;

  IF (lnnumbloqueos > 0) THEN
    RETURN 1;
  ELSE
    RETURN 0;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR rec_fn_devue_indic_bloqu_consu: ' ||
                             ls_sqlerrm);

END rec_fn_devue_indic_bloqu_consu;


 /**************************************************************************
   Descripcion        : Ejecuta un Mensaje de Reclamos
   Fecha Creacion     : 21/01/2009
   Autor              : Jose Cairampoma
   ***************************************************************************/
 PROCEDURE int_pr_calcu_abono_cargo_consu
 (
   pscodigopais     VARCHAR2,
   pscodigocabecera VARCHAR2
 ) IS

   lncargototal NUMBER;
   lnabonototal NUMBER;
 BEGIN

   SELECT SUM(cargo) cargototal,
          SUM(abono) abonototal
     INTO lncargototal,
          lnabonototal
     FROM (SELECT decode(num_unid_recl, 0, 0,
                         (num_unid_recl -
                          decode(l.ind_disc, 1, 0, l.num_unid_reco)) *
                          val_prec) cargo,
                  decode(num_unid_recl, 0,
                         decode(l.ind_disc, 1, 0, l.num_unid_reco) *
                          val_prec_disc, 0) abono
             FROM int_rec_linea_borec l
            WHERE l.cod_cabe_bore = pscodigocabecera
              AND l.cod_pais = pscodigopais
              AND l.prod_oid_prod IS NOT NULL
              AND l.refe_cod_line_bore IS NULL
           UNION ALL
           SELECT 0 cargo,
                  decode(num_unid_recl, 0, 0,
                         num_unid_reco * val_prec_disc) abono
             FROM int_rec_linea_borec l
            WHERE l.cod_cabe_bore = pscodigocabecera
              AND l.cod_pais = pscodigopais
              AND l.prod_oid_prod IS NOT NULL
              AND l.prod_oid_prod_disc IS NOT NULL) a;

   UPDATE int_rec_cabec_borec c
      SET c.imp_cargo = lncargototal,
          c.imp_abono = lnabonototal
    WHERE cod_pais = pscodigopais
      AND cod_cabe_bore = pscodigocabecera
      and c.ind_ocs_proc = 'F';

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     IF ln_sqlcode < 0 THEN
       raise_application_error(-20123,
                               'ERROR INT_PR_CALCU_ABONO_CARGO_CONSU: ' ||
                                ls_sqlerrm);
     END IF;
 END int_pr_calcu_abono_cargo_consu;
 /**************************************************************************
   Descripcion        : Modifica la tabla REC_LINEA_OPERA_RECLA  despues de la ejecucion
   					     de la interfaz de envio a almacen virtual
   Fecha Creacion     : 01/04/2009
   Autor              : Cristhian Roman
 ***************************************************************************/
PROCEDURE int_pr_rec_updat_envio_virtu
(
  pscodigopais VARCHAR2,
  psnumlote    VARCHAR2
) IS
  CURSOR c_temporal IS
    SELECT tmp.num_lote           v_num_lote,
           tmp.cod_pais           v_cod_pais,
           tmp.num_unid_recl      v_num_unid_recl,
           tmp.oid_line_oper_recl v_oid_line_oper_recl
      FROM int_tmp_envio_virtu tmp
     WHERE tmp.cod_pais = pscodigopais
       AND tmp.num_lote = psnumlote;

  TYPE temporalrec IS RECORD(
    v_num_lote           int_tmp_envio_virtu.num_lote%TYPE,
    v_cod_pais           int_tmp_envio_virtu.cod_pais%TYPE,
    v_num_unid_recl      int_tmp_envio_virtu.num_unid_recl%TYPE,
    v_oid_line_oper_recl int_tmp_envio_virtu.oid_line_oper_recl%TYPE

    );
  TYPE temporalrectab IS TABLE OF temporalrec;
  temporalrecord temporalrectab;

BEGIN

  OPEN c_temporal;
  LOOP
    FETCH c_temporal BULK COLLECT
      INTO temporalrecord LIMIT w_filas;
    IF temporalrecord.COUNT > 0 THEN

      FOR x IN temporalrecord.FIRST .. temporalrecord.LAST
      LOOP

        UPDATE rec_linea_opera_recla
           SET num_unid_devu = (-1) * temporalrecord(x).v_num_unid_recl
         WHERE oid_line_oper_recl = temporalrecord(x).v_oid_line_oper_recl;

      END LOOP;
    END IF;
    EXIT WHEN c_temporal%NOTFOUND;
  END LOOP;
  CLOSE c_temporal;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_UPDAT_UNIDA_ALMVI: ' ||
                             ls_sqlerrm);

END int_pr_rec_updat_envio_virtu;
 /**************************************************************************
   Descripcion        : Modifica la tabla INT_TMP_TRANS_BOREC despues de la ejecucion
   					     de la interfaz de envio de tranferencia de boleta de recojo
   Fecha Creacion     : 02/04/2009
   Autor              : Cristhian Roman
***************************************************************************/
PROCEDURE int_pr_rec_updat_trans_borec
(
  pscodigopais         VARCHAR2,
  psindicadoranulacion VARCHAR2
) IS
  CURSOR c_temporal IS
    SELECT tmp.num_lote           v_num_lote,
           tmp.cod_pais           v_cod_pais,
           tmp.num_unid_recl      v_num_unid_recl,
           tmp.oid_line_oper_recl v_oid_line_oper_recl,
           tmp.cod_cabe_bore      v_cod_cabe_bore,
           tmp.cod_line_bore      v_cod_line_bore
      FROM int_tmp_trans_borec tmp
     WHERE tmp.cod_pais = pscodigopais;

  TYPE temporalrec IS RECORD(
    v_num_lote           int_tmp_trans_borec.num_lote%TYPE,
    v_cod_pais           int_tmp_trans_borec.cod_pais%TYPE,
    v_num_unid_recl      int_tmp_trans_borec.num_unid_recl%TYPE,
    v_oid_line_oper_recl int_tmp_trans_borec.oid_line_oper_recl%TYPE,
    v_cod_cabe_bore      int_tmp_trans_borec.cod_cabe_bore%TYPE,
    v_cod_line_bore      int_tmp_trans_borec.cod_line_bore%TYPE);
  TYPE temporalrectab IS TABLE OF temporalrec;
  temporalrecord temporalrectab;

BEGIN

  OPEN c_temporal;
  LOOP
    FETCH c_temporal BULK COLLECT
      INTO temporalrecord LIMIT w_filas;
    IF temporalrecord.COUNT > 0 THEN

      FOR x IN temporalrecord.FIRST .. temporalrecord.LAST
      LOOP

        IF (psindicadoranulacion = '1') THEN

          UPDATE rec_linea_opera_recla
             SET num_unid_devu = temporalrecord(x)
                                .v_num_unid_recl
           WHERE oid_line_oper_recl = temporalrecord(x)
          .v_oid_line_oper_recl;

        ELSE

          UPDATE int_rec_linea_borec
             SET ind_proc_alma_fisi = 'F'
           WHERE cod_pais = pscodigopais
             AND cod_cabe_bore = temporalrecord(x)
          .v_cod_cabe_bore
             AND cod_line_bore = temporalrecord(x)
          .v_cod_line_bore;

          UPDATE rec_linea_opera_recla
             SET num_unid_devu = temporalrecord(x)
                                .v_num_unid_recl
           WHERE oid_line_oper_recl = temporalrecord(x)
          .v_oid_line_oper_recl;

        END IF;
      END LOOP;
    END IF;
    EXIT WHEN c_temporal%NOTFOUND;
  END LOOP;
  CLOSE c_temporal;
  IF (psindicadoranulacion <> '1') THEN

    UPDATE int_rec_cabec_borec c
       SET c.ind_proc_alma_fisi = 'F'
     WHERE c.cod_cabe_bore IN
           (SELECT DISTINCT cod_cabe_bore
              FROM int_tmp_trans_borec)
       AND 0 = (SELECT COUNT(*)
                  FROM int_rec_linea_borec l
                 WHERE l.ind_proc_alma_fisi = 'V'
                   AND l.cod_cabe_bore = c.cod_cabe_bore
                   AND l.cod_pais = pscodigopais);
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_ENVIA_TRANS_BOREC: ' ||
                             ls_sqlerrm);

END int_pr_rec_updat_trans_borec;

/**************************************************************************
   Descripcion        : Inserta registros en  la tabla INT_HISTO_TRANS_BOREC despues de la ejecucion
   					     de la interfaz de envio de tranferencia de boleta de recojo
   Fecha Creacion     : 02/04/2009
   Autor              : Cristhian Roman
***************************************************************************/
 PROCEDURE int_pr_rec_histo_trans_borec(pscodigopais VARCHAR2) IS

   CURSOR curinshistorico IS
     SELECT c.*
       FROM int_tmp_trans_borec c
      WHERE c.cod_pais = pscodigopais;

   TYPE int_tmp_trans_borec_tab_t IS TABLE OF int_tmp_trans_borec%ROWTYPE INDEX BY BINARY_INTEGER;
   int_tmp_trans_borec_tab int_tmp_trans_borec_tab_t; -- In-memory table

   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;

 BEGIN

   OPEN curinshistorico;
   LOOP
     -- Bulk collect data into memory table - X rows at a time
     FETCH curinshistorico BULK COLLECT
       INTO int_tmp_trans_borec_tab LIMIT rows;
     EXIT WHEN int_tmp_trans_borec_tab.COUNT = 0;

     -- Bulk bind of data in memory table...
     FORALL i IN int_tmp_trans_borec_tab.FIRST .. int_tmp_trans_borec_tab.LAST
       INSERT INTO int_histo_trans_borec
       VALUES int_tmp_trans_borec_tab
         (i);

   END LOOP;

   CLOSE curinshistorico;


 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 1000);
     raise_application_error(-20123,
                             'ERROR INT_PR_REC_HISTO_TRANS_BOREC: ' ||
                              ls_sqlerrm);

 END int_pr_rec_histo_trans_borec;

/**************************************************************************
  Descripcion        : Procesa una Boleta de Recojo No Exitosa
  Fecha Creacion     : 05/01/2010
  Autor              : Cristhian Roman
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_borec_noex2
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2
) IS

  CURSOR c_detalle IS
    select prod_oid_prod_deta, val_prec_cata_unit_loca_deta, unit_total,
            (select MF.OFDE_OID_DETA_OFER
            from int_rec_linea_borec brl , pre_matri_factu mf
            where brl.MAFA_OID_MATR_FACT = MF.OID_MATR_FACT
            and brl.cod_cabe_bore = pscodigocabecera
            and BRL.PROD_OID_PROD = art.prod_oid_prod_deta
            and rownum = 1) OFDE_OID_DETA_OFER
     from
     ( SELECT
    oid_prod prod_oid_prod_deta,
    cargo_unitario  val_prec_cata_unit_loca_deta,
    SUM(unidades) unit_total
     FROM (SELECT
                l.prod_oid_prod as oid_prod,
                decode(num_unid_recl, 0, 0,
                          val_prec) cargo_unitario,
                decode(num_unid_recl, 0, 0,
                         (num_unid_recl -
                          decode(l.ind_disc, 1, 0, l.num_unid_reco))) unidades
             FROM int_rec_linea_borec l
            WHERE l.cod_cabe_bore = pscodigocabecera
              AND l.cod_pais = pscodigopais
              AND l.prod_oid_prod IS NOT NULL
              AND l.refe_cod_line_bore IS NULL) a
    WHERE cargo_unitario>0
    GROUP BY
    oid_prod ,
    cargo_unitario
        HAVING SUM(unidades)<>0 ) art
        ;

  v_oid_soli_cabe           int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_cod_pais                int_rec_cabec_borec.cod_pais%TYPE;
  v_cod_cabe_bore           int_rec_cabec_borec.cod_cabe_bore%TYPE;
  v_soca_oid_soli_cabe      int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_val_nume_bole_desp      int_rec_cabec_borec.val_nume_bole_desp%TYPE;
  v_esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  v_esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  v_num_reco                int_rec_cabec_borec.num_reco%TYPE;
  v_clie_oid_clie           int_rec_cabec_borec.clie_oid_clie%TYPE;
  v_cod_clie                int_rec_cabec_borec.cod_clie%TYPE;
  v_ztad_oid_terri_admin    int_rec_cabec_borec.ztad_oid_terri_admin%TYPE;
  v_cod_regi                int_rec_cabec_borec.cod_regi%TYPE;
  v_cod_zona                int_rec_cabec_borec.cod_zona%TYPE;
  v_cod_secc                int_rec_cabec_borec.cod_secc%TYPE;
  v_cod_terr                int_rec_cabec_borec.cod_terr%TYPE;
  v_more_oid_motn_reco_bore int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  v_fec_ingr                int_rec_cabec_borec.fec_ingr%TYPE;
  v_fec_prog_reco           int_rec_cabec_borec.fec_prog_reco%TYPE;
  v_fec_reco                int_rec_cabec_borec.fec_reco%TYPE;
  v_hor_reco                int_rec_cabec_borec.hor_reco%TYPE;
  v_fec_ulti_actu           int_rec_cabec_borec.fec_ulti_actu%TYPE;
  v_val_nume_bore           int_rec_cabec_borec.val_nume_bore%TYPE;
  v_num_tota_unid_recl      int_rec_cabec_borec.num_tota_unid_recl%TYPE;
  v_num_tota_unid_reco      int_rec_cabec_borec.num_tota_unid_reco%TYPE;
  v_ind_envi_xero           int_rec_cabec_borec.ind_envi_xero%TYPE;
  v_ind_envi_yobe           int_rec_cabec_borec.ind_envi_yobe%TYPE;
  v_ind_regr_yobe           int_rec_cabec_borec.ind_regr_yobe%TYPE;
  v_ind_chk_auto            int_rec_cabec_borec.ind_chk_auto%TYPE;
  v_ind_ocs_proc            int_rec_cabec_borec.ind_ocs_proc%TYPE;
  v_ind_proc_alma_fisi      int_rec_cabec_borec.ind_proc_alma_fisi%TYPE;
  v_num_lote_envi           int_rec_cabec_borec.num_lote_envi%TYPE;
  v_num_lote_devu           int_rec_cabec_borec.num_lote_devu%TYPE;
  v_val_nomb_terc           int_rec_cabec_borec.val_nomb_terc%TYPE;
  v_cod_peri_proc           int_rec_cabec_borec.cod_peri_proc%TYPE;
  v_ind_envi_xer2           int_rec_cabec_borec.ind_envi_xer2%TYPE;
  v_ind_envi_yob2           int_rec_cabec_borec.ind_envi_yob2%TYPE;
  v_ind_regr_yob2           int_rec_cabec_borec.ind_regr_yob2%TYPE;
  v_num_lote_env2           int_rec_cabec_borec.num_lote_env2%TYPE;
  v_num_lote_dev2           int_rec_cabec_borec.num_lote_dev2%TYPE;
  v_fec_ing2                int_rec_cabec_borec.fec_ing2%TYPE;
  v_fec_rec2                int_rec_cabec_borec.fec_rec2%TYPE;
  v_hor_rec2                int_rec_cabec_borec.hor_rec2%TYPE;
  v_more_oid_motn_reco_bor2 int_rec_cabec_borec.more_oid_motn_reco_bor2%TYPE;
  v_val_nomb_ter2           int_rec_cabec_borec.val_nomb_ter2%TYPE;

  v_cldi_oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE;
  v_ztad_oid_terr_admi      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
  v_terr_oid_terr           zon_terri_admin.terr_oid_terr%TYPE;
  v_zzon_oid_zona           zon_secci.zzon_oid_zona%TYPE;
  v_tdoc_oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE;
  v_tido_oid_tipo_docu      mae_tipo_docum.tido_oid_tipo_docu%TYPE;
  v_ticl_oid_tipo_clie      mae_clien_tipo_subti.ticl_oid_tipo_clie %TYPE;
  v_sbti_oid_subt_clie      mae_clien_tipo_subti.sbti_oid_subt_clie%TYPE;
  v_vepo_oid_valo_estr_geop zon_terri.vepo_oid_valo_estr_geop%TYPE;

  v_fopa_oid_form_pago           ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
  v_ind_perm_unio_sol            ped_tipo_solic_pais.ind_perm_unio%TYPE;
  v_soci_oid_soci                ped_tipo_solic_pais.soci_oid_soci%TYPE;
  v_tspa_oid_tipo_soli_pais_cons ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  v_val_glos_obse                ped_tipo_solic_pais.val_glos%TYPE;
  v_ind_pedi_prue                ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  v_mone_oid_mone                ped_tipo_solic_pais.mone_oid_mone%TYPE;
  v_acfi_oid_acce_fisi           ped_acces_fisic.oid_acce_fisi%TYPE;
  v_sbac_oid_sbac                ped_tipo_solic.sbac_oid_sbac%TYPE;
  v_clso_oid_clas_soli           ped_tipo_solic.clso_oid_clas_soli%TYPE;
  v_oid_alma                     ped_tipo_solic_pais.almc_oid_alma%TYPE;
  v_ind_orde_comp                ped_clase_solic.ind_orde_comp%TYPE;
  v_ind_soli_nega                ped_tipo_solic.ind_soli_nega%TYPE;
  v_oid_tipo_soli_pais           ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  v_almc_oid_alma                ped_tipo_solic_pais.almc_oid_alma%TYPE;

  v_tido_oid_tipo_doc2           mae_tipo_docum.tido_oid_tipo_docu%TYPE;

  v_fec_prog_fact cra_perio.fec_fina%TYPE;
  v_perd_oid_peri cra_perio.oid_peri%TYPE;

  v_prod_oid_prod mae_produ.oid_prod%TYPE;

  v_val_prec_cata_unit_loca int_rec_linea_borec.val_prec%TYPE;

   TYPE t_prod_oid_prod_deta IS TABLE OF int_rec_linea_borec.PROD_OID_PROD%TYPE;
   TYPE t_val_prec_cata_unit_loca_deta IS TABLE OF int_rec_linea_borec.VAL_PREC%TYPE;
   TYPE t_unit_total IS TABLE OF int_rec_linea_borec.NUM_UNID_RECL%TYPE;
   TYPE t_ofde_oid_deta_ofer IS TABLE OF pre_matri_factu.OFDE_OID_DETA_OFER%TYPE;

   v_prod_oid_prod_deta            			 t_prod_oid_prod_deta;
   v_val_prec_cata_unit_loca_deta            t_val_prec_cata_unit_loca_deta;
   v_unit_total            					 t_unit_total;
   v_ofde_oid_deta_ofer            	t_ofde_oid_deta_ofer;

  lnoidsolicabe ped_solic_cabec.oid_soli_cabe%TYPE;


  lnIdPais NUMBER;

      lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;
  lsvalanio 				ped_numer_solic.VAL_ANIO%TYPE;
  rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    lscod_posi number;

BEGIN

  SELECT cod_pais,
         cod_cabe_bore,
         soca_oid_soli_cabe,
         val_nume_bole_desp,
         esbo_oid_esta_bor1,
         esbo_oid_esta_bor2,
         num_reco,
         clie_oid_clie,
         cod_clie,
         ztad_oid_terri_admin,
         cod_regi,
         cod_zona,
         cod_secc,
         cod_terr,
         more_oid_motn_reco_bore,
         fec_ingr,
         fec_prog_reco,
         fec_reco,
         hor_reco,
         fec_ulti_actu,
         val_nume_bore,
         num_tota_unid_recl,
         num_tota_unid_reco,
         ind_envi_xero,
         ind_envi_yobe,
         ind_regr_yobe,
         ind_chk_auto,
         ind_ocs_proc,
         ind_proc_alma_fisi,
         num_lote_envi,
         num_lote_devu,
         val_nomb_terc,
         cod_peri_proc,
         ind_envi_xer2,
         ind_envi_yob2,
         ind_regr_yob2,
         num_lote_env2,
         num_lote_dev2,
         fec_ing2,
         fec_rec2,
         hor_rec2,
         more_oid_motn_reco_bor2,
         val_nomb_ter2,
         (select max(irlb.soca_oid_soli_cabe) from INT_REC_LINEA_BOREC irlb
          where irlb.COD_CABE_BORE = ircb.COD_CABE_BORE)  soca_oid_soli_cabe
    INTO v_cod_pais,
         v_cod_cabe_bore,
         v_soca_oid_soli_cabe,
         v_val_nume_bole_desp,
         v_esbo_oid_esta_bor1,
         v_esbo_oid_esta_bor2,
         v_num_reco,
         v_clie_oid_clie,
         v_cod_clie,
         v_ztad_oid_terri_admin,
         v_cod_regi,
         v_cod_zona,
         v_cod_secc,
         v_cod_terr,
         v_more_oid_motn_reco_bore,
         v_fec_ingr,
         v_fec_prog_reco,
         v_fec_reco,
         v_hor_reco,
         v_fec_ulti_actu,
         v_val_nume_bore,
         v_num_tota_unid_recl,
         v_num_tota_unid_reco,
         v_ind_envi_xero,
         v_ind_envi_yobe,
         v_ind_regr_yobe,
         v_ind_chk_auto,
         v_ind_ocs_proc,
         v_ind_proc_alma_fisi,
         v_num_lote_envi,
         v_num_lote_devu,
         v_val_nomb_terc,
         v_cod_peri_proc,
         v_ind_envi_xer2,
         v_ind_envi_yob2,
         v_ind_regr_yob2,
         v_num_lote_env2,
         v_num_lote_dev2,
         v_fec_ing2,
         v_fec_rec2,
         v_hor_rec2,
         v_more_oid_motn_reco_bor2,
         v_val_nomb_ter2,
         v_oid_soli_cabe
    FROM int_rec_cabec_borec ircb
   WHERE ircb.cod_cabe_bore = pscodigocabecera
     AND ircb.cod_pais = pscodigopais;

  SELECT m.cod_clie                   v_cod_clie,
         m.oid_clie                   v_clie_oid_clie,
         d.oid_clie_dire              v_cldi_oid_clie_dire,
         ad.ztad_oid_terr_admi        v_ztad_oid_terr_admi,
         ztad.terr_oid_terr           v_terr_oid_terr,
         zs.zzon_oid_zona             v_zzon_oid_zona,
         ide.tdoc_oid_tipo_docu       v_tdoc_oid_tipo_docu,
         mt.tido_oid_tipo_docu        v_tido_oid_tipo_docu,
         ms.ticl_oid_tipo_clie        v_ticl_oid_tipo_clie,
         ms.sbti_oid_subt_clie        v_sbti_oid_subt_clie,
         terr.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop
    INTO v_cod_clie,
         v_clie_oid_clie,
         v_cldi_oid_clie_dire,
         v_ztad_oid_terr_admi,
         v_terr_oid_terr,
         v_zzon_oid_zona,
         v_tdoc_oid_tipo_docu,
         v_tido_oid_tipo_docu,
         v_ticl_oid_tipo_clie,
         v_sbti_oid_subt_clie,
         v_vepo_oid_valo_estr_geop
    FROM mae_clien             m,
         mae_clien_direc       d,
         mae_clien_unida_admin ad,
         mae_clien_ident       ide,
         zon_terri_admin       ztad,
         zon_terri             terr,
         zon_secci             zs,
         mae_tipo_docum        mt,
         mae_clien_tipo_subti  ms
   WHERE m.oid_clie = d.clie_oid_clie
     AND m.oid_clie = ad.clie_oid_clie
     AND m.oid_clie = ide.clie_oid_clie
     AND ztad.oid_terr_admi = ad.ztad_oid_terr_admi
     AND ztad.zscc_oid_secc = zs.oid_secc
     AND mt.oid_tipo_docu = ide.tdoc_oid_tipo_docu
     AND ide.val_iden_docu_prin = 1
     AND ad.perd_oid_peri_fin IS NULL
     AND d.ind_dire_ppal = 1
     AND d.ind_elim = 0
     AND m.oid_clie = v_clie_oid_clie -- oid de cliente se recupera de la cabecera de la boleta de recojo
     AND ms.clie_oid_clie = m.oid_clie
     AND ms.ind_ppal = 1
     AND terr.oid_terr = ztad.terr_oid_terr;

  SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
         tsp.ind_perm_unio      v_ind_perm_unio_sol,
         tsp.soci_oid_soci      v_soci_oid_soci,
         tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
         tsp.val_glos           v_val_glos_obse,
         tsp.ind_pedi_prue      v_ind_pedi_prue,
         tsp.mone_oid_mone      v_mone_oid_mone,
         NULL                   v_acfi_oid_acce_fisi,
         ts.sbac_oid_sbac       v_sbac_oid_sbac,
         ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
         tsp.almc_oid_alma      v_oid_alma,
         cs.ind_orde_comp       v_ind_orde_comp,
         ts.ind_soli_nega       v_ind_soli_nega,
         tsp.oid_tipo_soli_pais v_oid_tipo_soli_pais,
         tsp.almc_oid_alma      v_almc_oid_alma,
         tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu
    INTO v_fopa_oid_form_pago,
         v_ind_perm_unio_sol,
         v_soci_oid_soci,
         v_tspa_oid_tipo_soli_pais_cons,
         v_val_glos_obse,
         v_ind_pedi_prue,
         v_mone_oid_mone,
         v_acfi_oid_acce_fisi,
         v_sbac_oid_sbac,
         v_clso_oid_clas_soli,
         v_oid_alma,
         v_ind_orde_comp,
         v_ind_soli_nega,
         v_oid_tipo_soli_pais,
         v_almc_oid_alma,
         v_tido_oid_tipo_doc2
    FROM ped_tipo_solic_pais tsp,
         ped_tipo_solic      ts,
         ped_clase_solic     cs
   WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
     AND ts.clso_oid_clas_soli = cs.oid_clas_soli
     AND ts.cod_tipo_soli = 'CCBR';

  SELECT fec_fina v_fec_prog_fact,
         oid_peri v_perd_oid_peri
    INTO v_fec_prog_fact,
         v_perd_oid_peri
    FROM cra_perio
   WHERE trunc(SYSDATE) <= fec_fina
     AND trunc(SYSDATE) >= fec_inic
     AND rownum = 1;

  IF v_tido_oid_tipo_doc2 IS NOT NULL THEN
     v_tido_oid_tipo_docu := v_tido_oid_tipo_doc2;
  END IF;

  SELECT ped_soca_seq.NEXTVAL
              INTO lnoidsolicabe
              FROM dual;


  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  /*lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000');*/

    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              1);

  lnnumsoliformat := to_char(SYSDATE,
                   'YY') || lpad(lnnumsoliinicio,
                                8,
                                '0');

  lnnumsoliformat := lnnumsoliformat + 1;

  lsvalanio		  := to_char(to_number(to_char(SYSDATE,
                                                'YY')));
 lscod_posi := 0;

  INSERT INTO ped_solic_cabec
    (oid_soli_cabe,
     fec_prog_fact,
     fec_fact,
     num_clien,
     tspa_oid_tipo_soli_pais,
     tids_oid_tipo_desp,
     almc_oid_alma,
     modu_oid_modu,
     ticl_oid_tipo_clie,
     perd_oid_peri,
     soca_oid_soli_cabe,
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
     val_usua,
     val_tasa_impu,
     fec_cron,
     ind_perm_unio_sol,
     val_tipo_camb,
     num_docu_orig,
     val_base_flet_loca,
     val_impo_flet_loca,
     val_impo_flet_tota_loca,
     val_impo_flet_sin_impu_tota,
     val_reca_flet_loca,
     val_otro_reca_loca,
     val_tota_paga_loca,
     val_prec_cata_tota_loca,
     val_prec_cata_sin_impu_tota,
     val_prec_fact_tota_loca,
     val_impo_impu_tota_loca,
     val_impo_desc_1_tota_loca,
     val_impo_desc_1_tota_docu,
     val_impo_desc_1_sin_impu_tota,
     val_impo_desc_3_tota_docu,
     val_impo_desc_3_sin_impu_tota,
     val_impo_desc_tota_loca,
     val_impo_dto_1_sin_imp_tot_loc,
     val_impo_redo_loca,
     val_base_flet_docu,
     val_impo_flet_docu,
     val_impo_desc_tota_docu,
     val_impo_flet_sin_impu_docu,
     val_reca_flet_docu,
     val_otro_reca_docu,
     val_tota_flet_docu,
     val_impo_flet_tota_docu,
     val_tota_flet_loca,
     val_tota_paga_docu,
     val_prec_cata_tota_docu,
     val_prec_cata_sin_impu_tota_do,
     val_prec_cont_tota_loca,
     val_prec_cont_sin_impu_tota,
     val_prec_cont_sin_impu_tota_1,
     val_prec_fact_tota_docu,
     val_prec_cata_tota_loc_uni_dem,
     val_prec_neto_tota_docu,
     val_prec_neto_tota_loca,
     val_impo_impu_tota_docu,
     val_impo_redo_docu,
     val_impo_redo_cons_loca,
     val_impo_redo_cons_docu,
     ind_oc,
     ind_pedi_prue,
     ind_ts_no_conso,
     val_glos_obse,
     val_impo_desc_3_tota_loca,
     val_impo_dto_3_sin_imp_tot_loc,
     pais_oid_pais,
     tido_oid_tipo_docu,
     vepo_oid_valo_estr_geop,
     esso_oid_esta_soli,
     grpr_oid_grup_proc,
     sbti_oid_subt_clie,
     acfi_oid_acce_fisi,
     tspa_oid_tipo_soli_pais_cons,
     fopa_oid_form_pago,
     clso_oid_clas_soli,
     ztad_oid_terr_admi,
     oper_oid_oper,
     proc_oid_proc,
     ind_inte_lari_gene,
     fec_prog_fact_comp,
     soca_oid_docu_refe)
  VALUES
    (lnoidsolicabe,--seq_oid_soli_cabe,
     v_fec_prog_fact,
     NULL,
     0,
     v_oid_tipo_soli_pais,---2001,--v_tspa_oid_tipo_soli_pais,
     3,--v_tids_oid_tipo_desp, --:3
     v_almc_oid_alma,
     27,--v_modu_oid_modu, --:27
     v_ticl_oid_tipo_clie,
     v_perd_oid_peri,
     NULL,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_cldi_oid_clie_dire,
     v_tdoc_oid_tipo_docu,
     v_soci_oid_soci,
     v_sbac_oid_sbac,
     v_terr_oid_terr,
     v_zzon_oid_zona,
     lnnumsoliformat,-- + 1,--seq_val_nume_soli,
     NULL,--v_val_usua,
     0,
     trunc(SYSDATE),
     1,
     1,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     1,
     'OPERACION SUJETA A PERCEPCION DEL IGV',
     0,
     0,
     lnIdPais,--v_pais_oid_pais,
     v_tido_oid_tipo_docu,
     v_vepo_oid_valo_estr_geop,
     1,
     4,
     v_sbti_oid_subt_clie,
     1,
     v_tspa_oid_tipo_soli_pais_cons,
     v_fopa_oid_form_pago,
     v_clso_oid_clas_soli,
     v_ztad_oid_terr_admi,
     44,--v_oper_oid_oper, --:44
     6,--v_proc_oid_proc, --:6
     0,
     to_char(SYSDATE, 'yyyymmdd') , --:--v_fec_prog_fact_comp
     v_oid_soli_cabe);

 OPEN c_detalle;
    LOOP
      FETCH c_detalle BULK COLLECT
        INTO v_prod_oid_prod_deta, v_val_prec_cata_unit_loca_deta, v_unit_total,v_ofde_oid_deta_ofer

      LIMIT rows;

      IF v_prod_oid_prod_deta.COUNT > 0 THEN


        FOR i IN v_prod_oid_prod_deta.FIRST .. v_prod_oid_prod_deta.LAST
        LOOP

			INSERT INTO ped_solic_posic
		    (oid_soli_posi,
		     cod_posi,
		     num_unid_dema,
		     num_unid_por_aten,
		     val_tasa_impu,
		     soca_oid_soli_cabe,
		     taim_oid_tasa_impu,
		     tpos_oid_tipo_posi,
		     prod_oid_prod,
		     val_prec_cata_unit_loca,
		     val_prec_cont_unit_loca,
		     val_prec_cata_unit_docu,
		     val_prec_conta_unit_docu,
		     val_prec_fact_unit_loca,
		     val_prec_fact_unit_docu,
		     val_prec_sin_impu_unit_loca,
		     val_prec_sin_impu_unit_docu,
		     val_prec_sin_impu_tota_docu,
		     val_impo_desc_unit_loca,
		     val_prec_neto_unit_loca,
		     val_prec_neto_tota_docu,
		     val_prec_neto_unit_docu,
		     val_prec_tota_tota_loca,
		     val_prec_tota_tota_docu,
		     val_impo_impu_unit_loca,
		     val_impo_impu_unit_docu,
		     val_impo_desc_tota_docu,
		     val_impo_impu_tota_loca,
		     val_impo_impu_tota_docu,
		     val_impo_desc_tota_loca,
		     val_prec_tota_unit_loca,
		     val_prec_tota_unit_docu,
		     val_prec_cont_tota_loca,
		     val_prec_cata_tota_loca,
		     val_prec_cata_tota_docu,
		     val_prec_cont_tota_docu,
		     val_prec_cata_tota_loca_unid,
		     num_unid_dema_real,
		     num_unid_compr,
		     num_unid_aten,
		     val_prec_fact_tota_loca,
		     val_prec_fact_tota_docu,
		     val_prec_sin_impu_tota_loca,
		     val_prec_neto_tota_loca,
		     espo_oid_esta_posi,
		     stpo_oid_subt_posi,
		     ind_no_impr,
		     num_cons,
		     val_impo_des_sin_imp_unit_loca,
		     val_impo_des_sin_imp_unit_docu,
		     val_impo_des_sin_imp_tota,
		     val_impo_des_sin_imp_tota_docu,
         val_codi_vent,
         VAL_CODI_VENT_FICT,
         OFDE_OID_DETA_OFER)
		  VALUES
		    (ped_sopo_seq.NEXTVAL,--seq_oid_soli_posi,
		     lscod_posi,
		     v_unit_total(i),
		     v_unit_total(i),
		     NULL,
		     lnoidsolicabe,--seq_oid_soli_cabe,
		     NULL,
		     11,--v_tpos_oid_tipo_posi, --:11
		     v_prod_oid_prod_deta(i),
		     v_val_prec_cata_unit_loca_deta(i),
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     v_unit_total(i),
		     v_unit_total(i),
		     v_unit_total(i),
		     0,
		     0,
		     0,
		     0,
		     4,--v_espo_oid_esta_posi, --:4
		     15,--v_stpo_oid_subt_posi, --:15
		     0,
		     NULL,
		     0,
		     0,
		     0,
		     0,
         '00000',
         '00000',
         v_ofde_oid_deta_ofer(i) );
 lscod_posi := lscod_posi+1;
        END LOOP;
      END IF;
      EXIT WHEN c_detalle%NOTFOUND;
    END LOOP;
    CLOSE c_detalle;

  /*   UPDATE ped_numer_solic ns
           SET ns.val_ulti_nume_soli = lnnumsoliinicio+1
         WHERE ns.val_oper = 'PED001'
           AND ns.val_anio = lsvalanio
           AND ns.cod_suba = '000'
           AND ns.cod_pais = pscodigopais; */

        UPDATE int_rec_cabec_borec c
           SET c.soca_oid_soli_cabe_cccc = lnoidsolicabe
         WHERE c.cod_pais = pscodigopais
           AND c.cod_cabe_bore = pscodigocabecera;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_PROCE_BOREC_NOEX2: ' ||
                             ls_sqlerrm);

END int_pr_rec_proce_borec_noex2;

  /**************************************************************************
   Descripcion        : Procesa una Boleta de Recojo Con Discreapncia
   Fecha Creacion     : 05/01/2010
   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_borec_disc2
  (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
  )  IS

  CURSOR c_detalle IS
    select prod_oid_prod, val_prec_cata_unit_loca, unit_total,
            (select MF.OFDE_OID_DETA_OFER
            from int_rec_linea_borec brl , pre_matri_factu mf
            where brl.MAFA_OID_MATR_FACT = MF.OID_MATR_FACT
            and brl.cod_cabe_bore = pscodigocabecera
            and (BRL.PROD_OID_PROD = art.prod_oid_prod or
                 BRL.PROD_OID_PROD_disc = art.prod_oid_prod )
            and rownum = 1
            ) OFDE_OID_DETA_OFER
     from
       (SELECT  prod_oid_prod prod_oid_prod,
          abono_unitario  val_prec_cata_unit_loca,
          SUM(unidades)  unit_total
     FROM (
     SELECT
                decode(num_unid_recl, 0,
                          l.prod_oid_prod_disc, l.prod_oid_prod_disc) prod_oid_prod,
                  decode(num_unid_recl, 0,
                          val_prec_disc, 0) abono_unitario,
                  decode(num_unid_recl, 0,
                         decode(l.ind_disc, 1, 0, l.num_unid_reco) , 0) unidades
             FROM int_rec_linea_borec l
            WHERE l.cod_cabe_bore = pscodigocabecera
              AND l.cod_pais = pscodigopais
              AND l.prod_oid_prod IS NOT NULL
              AND l.refe_cod_line_bore IS NULL
           UNION ALL
           SELECT decode(num_unid_recl, 0,
                          l.prod_oid_prod_disc, l.prod_oid_prod_disc) v_prod_oid_prod,
                  decode(num_unid_recl, 0, 0,
                         val_prec_disc) abono_unitario,
                  decode(num_unid_recl, 0, 0,
                         num_unid_reco ) unidades
             FROM int_rec_linea_borec l
            WHERE l.cod_cabe_bore = pscodigocabecera
              AND l.cod_pais = pscodigopais
              AND l.prod_oid_prod IS NOT NULL
              AND l.prod_oid_prod_disc IS NOT NULL) a
    WHERE abono_unitario>0
    GROUP BY    prod_oid_prod,
                abono_unitario
          HAVING SUM(unidades)<>0 ) art
      ;

  v_oid_soli_cabe           int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_cod_pais                int_rec_cabec_borec.cod_pais%TYPE;
  v_cod_cabe_bore           int_rec_cabec_borec.cod_cabe_bore%TYPE;
  v_soca_oid_soli_cabe      int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_val_nume_bole_desp      int_rec_cabec_borec.val_nume_bole_desp%TYPE;
  v_esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  v_esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  v_num_reco                int_rec_cabec_borec.num_reco%TYPE;
  v_clie_oid_clie           int_rec_cabec_borec.clie_oid_clie%TYPE;
  v_cod_clie                int_rec_cabec_borec.cod_clie%TYPE;
  v_ztad_oid_terri_admin    int_rec_cabec_borec.ztad_oid_terri_admin%TYPE;
  v_cod_regi                int_rec_cabec_borec.cod_regi%TYPE;
  v_cod_zona                int_rec_cabec_borec.cod_zona%TYPE;
  v_cod_secc                int_rec_cabec_borec.cod_secc%TYPE;
  v_cod_terr                int_rec_cabec_borec.cod_terr%TYPE;
  v_more_oid_motn_reco_bore int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  v_fec_ingr                int_rec_cabec_borec.fec_ingr%TYPE;
  v_fec_prog_reco           int_rec_cabec_borec.fec_prog_reco%TYPE;
  v_fec_reco                int_rec_cabec_borec.fec_reco%TYPE;
  v_hor_reco                int_rec_cabec_borec.hor_reco%TYPE;
  v_fec_ulti_actu           int_rec_cabec_borec.fec_ulti_actu%TYPE;
  v_val_nume_bore           int_rec_cabec_borec.val_nume_bore%TYPE;
  v_num_tota_unid_recl      int_rec_cabec_borec.num_tota_unid_recl%TYPE;
  v_num_tota_unid_reco      int_rec_cabec_borec.num_tota_unid_reco%TYPE;
  v_ind_envi_xero           int_rec_cabec_borec.ind_envi_xero%TYPE;
  v_ind_envi_yobe           int_rec_cabec_borec.ind_envi_yobe%TYPE;
  v_ind_regr_yobe           int_rec_cabec_borec.ind_regr_yobe%TYPE;
  v_ind_chk_auto            int_rec_cabec_borec.ind_chk_auto%TYPE;
  v_ind_ocs_proc            int_rec_cabec_borec.ind_ocs_proc%TYPE;
  v_ind_proc_alma_fisi      int_rec_cabec_borec.ind_proc_alma_fisi%TYPE;
  v_num_lote_envi           int_rec_cabec_borec.num_lote_envi%TYPE;
  v_num_lote_devu           int_rec_cabec_borec.num_lote_devu%TYPE;
  v_val_nomb_terc           int_rec_cabec_borec.val_nomb_terc%TYPE;
  v_cod_peri_proc           int_rec_cabec_borec.cod_peri_proc%TYPE;
  v_ind_envi_xer2           int_rec_cabec_borec.ind_envi_xer2%TYPE;
  v_ind_envi_yob2           int_rec_cabec_borec.ind_envi_yob2%TYPE;
  v_ind_regr_yob2           int_rec_cabec_borec.ind_regr_yob2%TYPE;
  v_num_lote_env2           int_rec_cabec_borec.num_lote_env2%TYPE;
  v_num_lote_dev2           int_rec_cabec_borec.num_lote_dev2%TYPE;
  v_fec_ing2                int_rec_cabec_borec.fec_ing2%TYPE;
  v_fec_rec2                int_rec_cabec_borec.fec_rec2%TYPE;
  v_hor_rec2                int_rec_cabec_borec.hor_rec2%TYPE;
  v_more_oid_motn_reco_bor2 int_rec_cabec_borec.more_oid_motn_reco_bor2%TYPE;
  v_val_nomb_ter2           int_rec_cabec_borec.val_nomb_ter2%TYPE;
  v_val_dife_disc           int_rec_cabec_borec.val_dife_disc%TYPE;

  v_cldi_oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE;
  v_ztad_oid_terr_admi      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
  v_terr_oid_terr           zon_terri_admin.terr_oid_terr%TYPE;
  v_zzon_oid_zona           zon_secci.zzon_oid_zona%TYPE;
  v_tdoc_oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE;
  v_tido_oid_tipo_docu      mae_tipo_docum.tido_oid_tipo_docu%TYPE;
  v_ticl_oid_tipo_clie      mae_clien_tipo_subti.ticl_oid_tipo_clie %TYPE;
  v_sbti_oid_subt_clie      mae_clien_tipo_subti.sbti_oid_subt_clie%TYPE;
  v_vepo_oid_valo_estr_geop zon_terri.vepo_oid_valo_estr_geop%TYPE;

  v_fopa_oid_form_pago           ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
  v_ind_perm_unio_sol            ped_tipo_solic_pais.ind_perm_unio%TYPE;
  v_soci_oid_soci                ped_tipo_solic_pais.soci_oid_soci%TYPE;
  v_tspa_oid_tipo_soli_pais_cons ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  v_val_glos_obse                ped_tipo_solic_pais.val_glos%TYPE;
  v_ind_pedi_prue                ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  v_mone_oid_mone                ped_tipo_solic_pais.mone_oid_mone%TYPE;
  v_acfi_oid_acce_fisi           ped_acces_fisic.oid_acce_fisi%TYPE;
  v_sbac_oid_sbac                ped_tipo_solic.sbac_oid_sbac%TYPE;
  v_clso_oid_clas_soli           ped_tipo_solic.clso_oid_clas_soli%TYPE;
  v_oid_alma                     ped_tipo_solic_pais.almc_oid_alma%TYPE;
  v_ind_orde_comp                ped_clase_solic.ind_orde_comp%TYPE;
  v_ind_soli_nega                ped_tipo_solic.ind_soli_nega%TYPE;
  v_oid_tipo_soli_pais           ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  v_almc_oid_alma                ped_tipo_solic_pais.almc_oid_alma%TYPE;

  v_fec_prog_fact cra_perio.fec_fina%TYPE;
  v_perd_oid_peri cra_perio.oid_peri%TYPE;

  v_prod_oid_prod mae_produ.oid_prod%TYPE;

  v_val_prec_cata_unit_loca int_rec_linea_borec.val_prec%TYPE;
  lnoidsolicabe             ped_solic_cabec.oid_soli_cabe%TYPE;

  lnidpais NUMBER;

  lnnumsoliinicio NUMBER;
  lnnumsoliformat NUMBER;

  TYPE t_prod_oid_prod_deta IS TABLE OF int_rec_linea_borec.PROD_OID_PROD%TYPE;
  TYPE t_val_prec_cata_unit_loca_deta IS TABLE OF int_rec_linea_borec.VAL_PREC%TYPE;
  TYPE t_unit_total IS TABLE OF int_rec_linea_borec.NUM_UNID_RECL%TYPE;
   TYPE t_ofde_oid_deta_ofer IS TABLE OF pre_matri_factu.OFDE_OID_DETA_OFER%TYPE;

   v_prod_oid_prod_deta            			 t_prod_oid_prod_deta;
   v_val_prec_cata_unit_loca_deta            t_val_prec_cata_unit_loca_deta;
   v_unit_total            					 t_unit_total;
   v_ofde_oid_deta_ofer            	t_ofde_oid_deta_ofer;

  lsvalanio 				ped_numer_solic.VAL_ANIO%TYPE;
  rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
lscod_posi number;

BEGIN

  SELECT cod_pais,
         cod_cabe_bore,
         soca_oid_soli_cabe,
         val_nume_bole_desp,
         esbo_oid_esta_bor1,
         esbo_oid_esta_bor2,
         num_reco,
         clie_oid_clie,
         cod_clie,
         ztad_oid_terri_admin,
         cod_regi,
         cod_zona,
         cod_secc,
         cod_terr,
         more_oid_motn_reco_bore,
         fec_ingr,
         fec_prog_reco,
         fec_reco,
         hor_reco,
         fec_ulti_actu,
         val_nume_bore,
         num_tota_unid_recl,
         num_tota_unid_reco,
         ind_envi_xero,
         ind_envi_yobe,
         ind_regr_yobe,
         ind_chk_auto,
         ind_ocs_proc,
         ind_proc_alma_fisi,
         num_lote_envi,
         num_lote_devu,
         val_nomb_terc,
         cod_peri_proc,
         ind_envi_xer2,
         ind_envi_yob2,
         ind_regr_yob2,
         num_lote_env2,
         num_lote_dev2,
         fec_ing2,
         fec_rec2,
         hor_rec2,
         more_oid_motn_reco_bor2,
         val_nomb_ter2,
         val_dife_disc,
         (select max(irlb.soca_oid_soli_cabe) from INT_REC_LINEA_BOREC irlb
          where irlb.COD_CABE_BORE = ircb.COD_CABE_BORE)  soca_oid_soli_cabe
    INTO v_cod_pais,
         v_cod_cabe_bore,
         v_soca_oid_soli_cabe,
         v_val_nume_bole_desp,
         v_esbo_oid_esta_bor1,
         v_esbo_oid_esta_bor2,
         v_num_reco,
         v_clie_oid_clie,
         v_cod_clie,
         v_ztad_oid_terri_admin,
         v_cod_regi,
         v_cod_zona,
         v_cod_secc,
         v_cod_terr,
         v_more_oid_motn_reco_bore,
         v_fec_ingr,
         v_fec_prog_reco,
         v_fec_reco,
         v_hor_reco,
         v_fec_ulti_actu,
         v_val_nume_bore,
         v_num_tota_unid_recl,
         v_num_tota_unid_reco,
         v_ind_envi_xero,
         v_ind_envi_yobe,
         v_ind_regr_yobe,
         v_ind_chk_auto,
         v_ind_ocs_proc,
         v_ind_proc_alma_fisi,
         v_num_lote_envi,
         v_num_lote_devu,
         v_val_nomb_terc,
         v_cod_peri_proc,
         v_ind_envi_xer2,
         v_ind_envi_yob2,
         v_ind_regr_yob2,
         v_num_lote_env2,
         v_num_lote_dev2,
         v_fec_ing2,
         v_fec_rec2,
         v_hor_rec2,
         v_more_oid_motn_reco_bor2,
         v_val_nomb_ter2,
         v_val_dife_disc,
         v_oid_soli_cabe
    FROM int_rec_cabec_borec ircb
   WHERE ircb.cod_cabe_bore = pscodigocabecera
     AND ircb.cod_pais = pscodigopais;

  SELECT m.cod_clie                   v_cod_clie,
         m.oid_clie                   v_clie_oid_clie,
         d.oid_clie_dire              v_cldi_oid_clie_dire,
         ad.ztad_oid_terr_admi        v_ztad_oid_terr_admi,
         ztad.terr_oid_terr           v_terr_oid_terr,
         zs.zzon_oid_zona             v_zzon_oid_zona,
         ide.tdoc_oid_tipo_docu       v_tdoc_oid_tipo_docu,
         mt.tido_oid_tipo_docu        v_tido_oid_tipo_docu,
         ms.ticl_oid_tipo_clie        v_ticl_oid_tipo_clie,
         ms.sbti_oid_subt_clie        v_sbti_oid_subt_clie,
         terr.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop
    INTO v_cod_clie,
         v_clie_oid_clie,
         v_cldi_oid_clie_dire,
         v_ztad_oid_terr_admi,
         v_terr_oid_terr,
         v_zzon_oid_zona,
         v_tdoc_oid_tipo_docu,
         v_tido_oid_tipo_docu,
         v_ticl_oid_tipo_clie,
         v_sbti_oid_subt_clie,
         v_vepo_oid_valo_estr_geop
    FROM mae_clien             m,
         mae_clien_direc       d,
         mae_clien_unida_admin ad,
         mae_clien_ident       ide,
         zon_terri_admin       ztad,
         zon_terri             terr,
         zon_secci             zs,
         mae_tipo_docum        mt,
         mae_clien_tipo_subti  ms
   WHERE m.oid_clie = d.clie_oid_clie
     AND m.oid_clie = ad.clie_oid_clie
     AND m.oid_clie = ide.clie_oid_clie
     AND ztad.oid_terr_admi = ad.ztad_oid_terr_admi
     AND ztad.zscc_oid_secc = zs.oid_secc
     AND mt.oid_tipo_docu = ide.tdoc_oid_tipo_docu
     AND ide.val_iden_docu_prin = 1
     AND ad.perd_oid_peri_fin IS NULL
     AND d.ind_dire_ppal = 1
     AND d.ind_elim = 0
     AND m.oid_clie = v_clie_oid_clie -- oid de cliente se recupera de la cabecera de la boleta de recojo
     AND ms.clie_oid_clie = m.oid_clie
     AND ms.ind_ppal = 1
     AND terr.oid_terr = ztad.terr_oid_terr;

    SELECT tido_oid_tipo_docu_cont
      INTO v_tido_oid_tipo_docu
      FROM fac_tipo_docum
     WHERE oid_tipo_docu = v_tido_oid_tipo_docu;

    SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
           tsp.ind_perm_unio      v_ind_perm_unio_sol,
           tsp.soci_oid_soci      v_soci_oid_soci,
           tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
           tsp.val_glos           v_val_glos_obse,
           tsp.ind_pedi_prue      v_ind_pedi_prue,
           tsp.mone_oid_mone      v_mone_oid_mone,
           NULL                   v_acfi_oid_acce_fisi,
           ts.sbac_oid_sbac       v_sbac_oid_sbac,
           ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
           tsp.almc_oid_alma      v_oid_alma,
           cs.ind_orde_comp       v_ind_orde_comp,
           ts.ind_soli_nega       v_ind_soli_nega,
           tsp.oid_tipo_soli_pais v_oid_tipo_soli_pais,
           tsp.almc_oid_alma      v_almc_oid_alma
      INTO v_fopa_oid_form_pago,
           v_ind_perm_unio_sol,
           v_soci_oid_soci,
           v_tspa_oid_tipo_soli_pais_cons,
           v_val_glos_obse,
           v_ind_pedi_prue,
           v_mone_oid_mone,
           v_acfi_oid_acce_fisi,
           v_sbac_oid_sbac,
           v_clso_oid_clas_soli,
           v_oid_alma,
           v_ind_orde_comp,
           v_ind_soli_nega,
           v_oid_tipo_soli_pais,
           v_almc_oid_alma
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts,
           ped_clase_solic     cs
     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.clso_oid_clas_soli = cs.oid_clas_soli
       AND ts.cod_tipo_soli = 'CABR';

    SELECT fec_fina v_fec_prog_fact,
           oid_peri v_perd_oid_peri
      INTO v_fec_prog_fact,
           v_perd_oid_peri
      FROM cra_perio
     WHERE trunc(SYSDATE) <= fec_fina
       AND trunc(SYSDATE) >= fec_inic
       AND rownum = 1
     ORDER BY fec_inic ASC;

    SELECT ped_soca_seq.NEXTVAL INTO lnoidsolicabe FROM dual;

    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

   /* lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000');*/

    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              1);

	lnnumsoliformat := to_char(SYSDATE,
                   'YY') || lpad(lnnumsoliinicio,
                                8,
                                '0');

	lsvalanio		:= to_char(to_number(to_char(SYSDATE,
                                                'YY')));

  lscod_posi := 0;

    INSERT INTO ped_solic_cabec
      (oid_soli_cabe,
       fec_prog_fact,
       fec_fact,
       num_clien,
       tspa_oid_tipo_soli_pais,
       tids_oid_tipo_desp,
       almc_oid_alma,
       modu_oid_modu,
       ticl_oid_tipo_clie,
       perd_oid_peri,
       soca_oid_soli_cabe,
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
       val_usua,
       val_tasa_impu,
       fec_cron,
       ind_perm_unio_sol,
       val_tipo_camb,
       num_docu_orig,
       val_base_flet_loca,
       val_impo_flet_loca,
       val_impo_flet_tota_loca,
       val_impo_flet_sin_impu_tota,
       val_reca_flet_loca,
       val_otro_reca_loca,
       val_tota_paga_loca,
       val_prec_cata_tota_loca,
       val_prec_cata_sin_impu_tota,
       val_prec_fact_tota_loca,
       val_impo_impu_tota_loca,
       val_impo_desc_1_tota_loca,
       val_impo_desc_1_tota_docu,
       val_impo_desc_1_sin_impu_tota,
       val_impo_desc_3_tota_docu,
       val_impo_desc_3_sin_impu_tota,
       val_impo_desc_tota_loca,
       val_impo_dto_1_sin_imp_tot_loc,
       val_impo_redo_loca,
       val_base_flet_docu,
       val_impo_flet_docu,
       val_impo_desc_tota_docu,
       val_impo_flet_sin_impu_docu,
       val_reca_flet_docu,
       val_otro_reca_docu,
       val_tota_flet_docu,
       val_impo_flet_tota_docu,
       val_tota_flet_loca,
       val_tota_paga_docu,
       val_prec_cata_tota_docu,
       val_prec_cata_sin_impu_tota_do,
       val_prec_cont_tota_loca,
       val_prec_cont_sin_impu_tota,
       val_prec_cont_sin_impu_tota_1,
       val_prec_fact_tota_docu,
       val_prec_cata_tota_loc_uni_dem,
       val_prec_neto_tota_docu,
       val_prec_neto_tota_loca,
       val_impo_impu_tota_docu,
       val_impo_redo_docu,
       val_impo_redo_cons_loca,
       val_impo_redo_cons_docu,
       ind_oc,
       ind_pedi_prue,
       ind_ts_no_conso,
       val_glos_obse,
       val_impo_desc_3_tota_loca,
       val_impo_dto_3_sin_imp_tot_loc,
       pais_oid_pais,
       tido_oid_tipo_docu,
       vepo_oid_valo_estr_geop,
       esso_oid_esta_soli,
       grpr_oid_grup_proc,
       sbti_oid_subt_clie,
       acfi_oid_acce_fisi,
       tspa_oid_tipo_soli_pais_cons,
       fopa_oid_form_pago,
       clso_oid_clas_soli,
       ztad_oid_terr_admi,
       oper_oid_oper,
       proc_oid_proc,
       ind_inte_lari_gene,
       fec_prog_fact_comp,
       soca_oid_docu_refe)
    VALUES
      (lnoidsolicabe, --seq_oid_soli_cabe,
       v_fec_prog_fact,
       NULL,--trunc(SYSDATE),
       0,
       v_oid_tipo_soli_pais, ---2001,--v_tspa_oid_tipo_soli_pais,
       3, --v_tids_oid_tipo_desp, --:3
       v_almc_oid_alma,
       27, --v_modu_oid_modu, --:27
       v_ticl_oid_tipo_clie,
       v_perd_oid_peri,
       NULL,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_clie_oid_clie,
       v_cldi_oid_clie_dire,
       v_tdoc_oid_tipo_docu,
       v_soci_oid_soci,
       v_sbac_oid_sbac,
       v_terr_oid_terr,
       v_zzon_oid_zona,
       lnnumsoliformat + 1, --seq_val_nume_soli,
       NULL, --v_val_usua,
       0,
       trunc(SYSDATE),
       1,
       1,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       0,
       1,
       'OPERACION SUJETA A PERCEPCION DEL IGV',
       0,
       0,
       lnidpais, --v_pais_oid_pais,
       v_tido_oid_tipo_docu,
       v_vepo_oid_valo_estr_geop,
       1,
       4,
       v_sbti_oid_subt_clie,
       1,
       v_tspa_oid_tipo_soli_pais_cons,
       v_fopa_oid_form_pago,
       v_clso_oid_clas_soli,
       v_ztad_oid_terr_admi,
       44, --v_oper_oid_oper, --:44
       6, --v_proc_oid_proc, --:6
       0,
       to_char(SYSDATE, 'yyyymmdd'),  --:--v_fec_prog_fact_comp
       v_oid_soli_cabe);

   OPEN c_detalle;
    LOOP
      FETCH c_detalle BULK COLLECT
        INTO v_prod_oid_prod_deta, v_val_prec_cata_unit_loca_deta, v_unit_total,v_ofde_oid_deta_ofer

      LIMIT rows;

      IF v_prod_oid_prod_deta.COUNT > 0 THEN


        FOR i IN v_prod_oid_prod_deta.FIRST .. v_prod_oid_prod_deta.LAST
        LOOP
     		  INSERT INTO ped_solic_posic
		      (oid_soli_posi,
		       cod_posi,
		       num_unid_dema,
		       num_unid_por_aten,
		       val_tasa_impu,
		       soca_oid_soli_cabe,
		       taim_oid_tasa_impu,
		       tpos_oid_tipo_posi,
		       prod_oid_prod,
		       val_prec_cata_unit_loca,
		       val_prec_cont_unit_loca,
		       val_prec_cata_unit_docu,
		       val_prec_conta_unit_docu,
		       val_prec_fact_unit_loca,
		       val_prec_fact_unit_docu,
		       val_prec_sin_impu_unit_loca,
		       val_prec_sin_impu_unit_docu,
		       val_prec_sin_impu_tota_docu,
		       val_impo_desc_unit_loca,
		       val_prec_neto_unit_loca,
		       val_prec_neto_tota_docu,
		       val_prec_neto_unit_docu,
		       val_prec_tota_tota_loca,
		       val_prec_tota_tota_docu,
		       val_impo_impu_unit_loca,
		       val_impo_impu_unit_docu,
		       val_impo_desc_tota_docu,
		       val_impo_impu_tota_loca,
		       val_impo_impu_tota_docu,
		       val_impo_desc_tota_loca,
		       val_prec_tota_unit_loca,
		       val_prec_tota_unit_docu,
		       val_prec_cont_tota_loca,
		       val_prec_cata_tota_loca,
		       val_prec_cata_tota_docu,
		       val_prec_cont_tota_docu,
		       val_prec_cata_tota_loca_unid,
		       num_unid_dema_real,
		       num_unid_compr,
		       num_unid_aten,
		       val_prec_fact_tota_loca,
		       val_prec_fact_tota_docu,
		       val_prec_sin_impu_tota_loca,
		       val_prec_neto_tota_loca,
		       espo_oid_esta_posi,
		       stpo_oid_subt_posi,
		       ind_no_impr,
		       num_cons,
		       val_impo_des_sin_imp_unit_loca,
		       val_impo_des_sin_imp_unit_docu,
		       val_impo_des_sin_imp_tota,
		       val_impo_des_sin_imp_tota_docu,
           val_codi_vent,
           VAL_CODI_VENT_FICT,
           OFDE_OID_DETA_OFER)
		    VALUES
		      (ped_sopo_seq.NEXTVAL, --seq_oid_soli_posi,
		       lscod_posi,
		       (-1)*v_unit_total(i),
		       (-1)*v_unit_total(i),
		       NULL,
		       lnoidsolicabe, --seq_oid_soli_cabe,
		       NULL,
		       11, --v_tpos_oid_tipo_posi, --:11
		       v_prod_oid_prod_deta(i),
		       v_val_prec_cata_unit_loca_deta(i),
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       0,
		       (-1)*v_unit_total(i),
		       (-1)*v_unit_total(i),
		       (-1)*v_unit_total(i),
		       0,
		       0,
		       0,
		       0,
		       4, --v_espo_oid_esta_posi, --:4
		       15, --v_stpo_oid_subt_posi, --:15
		       0,
		       NULL,
		       0,
		       0,
		       0,
		       0,
           '00000',
           '00000',
           v_ofde_oid_deta_ofer(i) );
lscod_posi := lscod_posi +1;
        END LOOP;
      END IF;
      EXIT WHEN c_detalle%NOTFOUND;
    END LOOP;
    CLOSE c_detalle;


  /*UPDATE ped_numer_solic ns
     SET ns.val_ulti_nume_soli = lnnumsoliinicio + 1
   WHERE ns.val_oper = 'PED001'
     AND ns.val_anio = lsvalanio
     AND ns.cod_suba = '000'
     AND ns.cod_pais = pscodigopais;*/

  UPDATE int_rec_cabec_borec c
     SET --c.ind_ocs_proc = 'V',
         c.soca_oid_soli_cabe_abon = lnoidsolicabe
   WHERE c.cod_pais = pscodigopais
     AND c.cod_cabe_bore = pscodigocabecera;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_REC_PROCE_BOREC_DISC2: ' ||
                             ls_sqlerrm);

END int_pr_rec_proce_borec_disc2;

/**************************************************************************
   Descripcion        : Devuelve el saldo del producto
   Fecha Creacion     : 11/07/2012
   Autor              : Jorge Velasquez
  ***************************************************************************/
FUNCTION REC_FN_DEVUE_SALDO_PRODUC(psnumeropedido VARCHAR2,pscuvdesea VARCHAR2)
  RETURN NUMBER IS

  lnvalorSaldo NUMBER(12);

BEGIN

    select nvl(max(val_sald),'0') saldo INTO lnvalorSaldo
        from bel_stock stock ,
                (SELECT d.prod_oid_prod
                FROM pre_ofert_detal       d,
                     pre_matri_factu       maf,
                     pre_matri_factu_cabec cab
                WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                 AND cab.oid_cabe = maf.mfca_oid_cabe
                 AND cab.perd_oid_peri in (select perd_oid_peri from ped_solic_Cabec where val_nume_soli = psnumeropedido)
                 AND to_number(d.val_codi_vent) = pscuvdesea
                 and rownum = 1
                UNION
                select prod_oid_prod
                from inc_artic_lote a,
                        (select max(oid_arti_lote) oid_arti_lote
                         from inc_artic_lote b
                         where cod_vent_fict = pscuvdesea) b
                where  a.oid_arti_lote = b.oid_arti_lote ) producto
        where producto.prod_oid_prod = stock.prod_oid_prod (+)
        and rownum = 1;

        RETURN lnvalorSaldo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR REC_FN_DEVUE_SALDO_PRODUC: ' ||
                             ls_sqlerrm);
END REC_FN_DEVUE_SALDO_PRODUC;

/**************************************************************************
   Descripcion        : Devuelve Status de Estatus del CDR
   Fecha Creacion     : 27/08/2012
   Autor              : Sandro Quintana
                        psoid = es el oid de rec_Cabe_recla o rec_opera_recla
                        pstipo = 'RCR'  Si es del rec_cabec_recl
                                 'ROR'  Si es del rec_opera_Recla
  ***************************************************************************/
FUNCTION REC_FN_DEVUE_ESTAD_CDR(
  psoid VARCHAR2,
  pstipo VARCHAR2)
  RETURN varchar2 IS

  lsoidestado NUMBER(12);
  lscantidad NUMBER(12);
  lsdesea    NUMBER(12);
  lsdevuelve NUMBER(12);
  lsestado varchar2(20);

BEGIN
    if  pstipo = 'RCR' then
       begin
         select ESRE_OID_ESTA_RECL into lsoidestado from rec_cabec_recla where OID_CABE_RECL = psoid;
       EXCEPTION
         WHEN OTHERS THEN
           lsoidestado := 7;
       END;

        select count(1) into lscantidad
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ROR.CARE_OID_CABE_RECL = psoid
        and PSC.FEC_FACT is null;

        select count(1) into lsdesea
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc, rec_linea_opera_recla rlo
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and ror.oid_oper_recl = rlo.opre_oid_oper_recl
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ROR.CARE_OID_CABE_RECL = psoid
        and PSC.FEC_FACT is null
        and rlo.timo_oid_tipo_movi = 1;

        select count(1) into lsdevuelve
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc, rec_linea_opera_recla rlo
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and ror.oid_oper_recl = rlo.opre_oid_oper_recl
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ROR.CARE_OID_CABE_RECL = psoid
        and PSC.FEC_FACT is null
        and rlo.timo_oid_tipo_movi = 2;

       if lscantidad >= 1 and ( lsoidestado = 4 or lsoidestado = 6 ) then
         if lsdevuelve >= 1 then
            lsoidestado := 6;  --- Pendiente
         else
            lsoidestado := 8;  --- Envio Pendiente
         end if;
       end if;

       if lscantidad = 0 and ( lsoidestado = 6 ) then
         lsoidestado := 4;  --- Facturado
       end if;

       lsestado := PQ_APL_AUX.VALOR_GEN_I18N_SICC(1, lsoidestado, 'REC_ESTAD_RECLA');
    else
       begin
         select ESOP_OID_ESTA_OPER into lsoidestado from rec_opera_recla where OID_OPER_RECL = psoid;
       EXCEPTION
         WHEN OTHERS THEN
           lsoidestado := 8;
       END;

        select count(1) into lscantidad
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ror.oid_oper_recl = psoid
        and PSC.FEC_FACT is null;

        select count(1) into lsdesea
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc, rec_linea_opera_recla rlo
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and ror.oid_oper_recl = rlo.opre_oid_oper_recl
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ror.oid_oper_recl = psoid
        and PSC.FEC_FACT is null
        and rlo.timo_oid_tipo_movi = 1;

        select count(1) into lsdevuelve
        from rec_opera_recla ror, rec_solic_opera rso , ped_solic_cabec psc, rec_linea_opera_recla rlo
        where ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and ror.oid_oper_recl = rlo.opre_oid_oper_recl
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and ror.oid_oper_recl = psoid
        and PSC.FEC_FACT is null
        and rlo.timo_oid_tipo_movi = 2;


       if lscantidad >= 1 and lsoidestado = 5 then
         if lsdevuelve >= 1 then
            lsoidestado := 2;  --- Pendiente
         else
            lsoidestado := 3;  --- Envio Pendiente
         end if;
       end if;
       lsestado := PQ_APL_AUX.VALOR_GEN_I18N_SICC(1, lsoidestado, 'REC_ESTAD_OPERA');
    end if ;

  RETURN lsestado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR REC_FN_DEVUE_ESTAD_CDR: ' ||
                             ls_sqlerrm);
END REC_FN_DEVUE_ESTAD_CDR;


 /***************************************************************************
    Descripcion       : Activacion para reimprimir la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_activa_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psUsuario         in VARCHAR2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;

  lsoidestabore1   int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsoidestabore2   int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestacerrado  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lsindenvixerox1  int_rec_cabec_borec.ind_envi_xero%TYPE;
  lsindenvixerox2  int_rec_cabec_borec.ind_envi_xer2%TYPE;
  lscodclie        int_rec_cabec_borec.cod_clie%TYPE;
  lnnumreco        int_rec_cabec_borec.num_reco%TYPE;

  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

  BEGIN

   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'CE');


   ----- Recupera datos de la BR
        SELECT cb.num_reco,
               cb.esbo_oid_esta_bor1,
               cb.esbo_oid_esta_bor2,
               cb.ind_envi_xero,
               cb.ind_envi_xer2,
               cb.cod_clie
          INTO lnnumreco,
               lsoidestabore1,
               lsoidestabore2,
               lsindenvixerox1,
               lsindenvixerox2,
               lscodclie
          FROM int_rec_cabec_borec cb
         WHERE cb.cod_cabe_bore = psNroBolRecojo
           AND cb.cod_pais      = pscodigopais ;

   ----- Valida la informacion
   if lsoidestabore1 = lnidestacerrado then
      pmensaje := 'Boleta de Recojo cerrada';
   else
      if lnnumreco = 1 then
         if lsindenvixerox1 = 1 then

             update int_rec_cabec_borec
                 set ind_envi_xero = 0
                 where cod_pais = pscodigopais
                   and cod_cabe_bore = psNroBolRecojo;

             update int_rec_linea_borec
                 set ind_envi_xero = 0
                 where cod_pais = pscodigopais
                   and cod_cabe_bore = psNroBolRecojo;
         else
              pmensaje := 'Primer recojo aun no se ha impreso';
         end if;

      else
         if lsindenvixerox2 = 1 then
             update int_rec_cabec_borec
                 set ind_envi_xer2 = null
                 where cod_pais = pscodigopais
                   and cod_cabe_bore = psNroBolRecojo;

             update int_rec_linea_borec
                 set ind_envi_xer2 = null
                 where cod_pais = pscodigopais
                   and cod_cabe_bore = psNroBolRecojo;
         else
              pmensaje := 'Segundo recojo aun no se ha impreso';
         end if;

      end if;

      if pmensaje is null then
           ----- actualiza auditoria
           insert into REC_AUDIT_BOREC
           (COD_CORR,
            NUM_LOTE,
            COD_CABE_BORE,
            NUM_RECO,
            COD_CLIE,
            COD_ESTA_BORE,
            FLAG_PROC,
            DES_ERRO,
            FEC_INGR,
            USU_INGR)
           values
           ( REC_SEQ_AUDIT_BOREC.NEXTVAL,
             lpad(REC_SEQ_LOTE_AUDIT_BOREC.NEXTVAL,10,'0') ,
             psNroBolRecojo,
             lnnumreco,
             lscodclie,
             'RE',
             2, ----- identifica los que son reimpresiones
             '',
             sysdate,
             psUsuario
            );
      end if;

   end if;



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR int_pr_rec_activa_borec : ' || ls_sqlerrm);

  END int_pr_rec_activa_borec ;

 /***************************************************************************
    Descripcion       : Anula la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_anula_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psUsuario         in VARCHAR2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;

  lscodclie        int_rec_cabec_borec.cod_clie%TYPE;
  lsoidestabore1   int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsoidestabore2   int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestacerrado  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestaanulado  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lsindenvixerox1  int_rec_cabec_borec.ind_envi_xero%TYPE;
  lsindenvixerox2  int_rec_cabec_borec.ind_envi_xer2%TYPE;
  lnnumreco        int_rec_cabec_borec.num_reco%TYPE;
  lsnumlote        int_rec_cabec_borec.num_lote_envi%TYPE;

  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

  BEGIN


   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'CE');

  lnidestaanulado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'NE');


   ----- Recupera datos de la BR
        SELECT cb.num_reco,
               cb.esbo_oid_esta_bor1,
               cb.esbo_oid_esta_bor2,
               cb.ind_envi_xero,
               cb.ind_envi_xer2,
               cb.cod_clie
          INTO lnnumreco,
               lsoidestabore1,
               lsoidestabore2,
               lsindenvixerox1,
               lsindenvixerox2,
               lscodclie
          FROM int_rec_cabec_borec cb
         WHERE cb.cod_cabe_bore = psNroBolRecojo
           AND cb.cod_pais      = pscodigopais ;

   ----- Valida la informacion
   if lsoidestabore1 = lnidestacerrado then
      pmensaje := 'Boleta de Recojo cerrada';
   else


         insert into REC_GTT_PROCE_BOREC
                (cod_cabe_bore,cod_clie)
            values(psNroBolRecojo,lscodclie);

         select lpad(REC_SEQ_LOTE_AUDIT_BOREC.NEXTVAL,10,'0')
         into lsnumlote
         from dual;

         ----- invoca al proceso de actualizacion con NE para identificar lo anulado
         INT_PKG_RECLA.int_pr_rec_proce_borec (lsNumLote,'NE',psUsuario);


   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR int_pr_rec_anula_borec : ' || ls_sqlerrm);

  END int_pr_rec_anula_borec ;

 /***************************************************************************
    Descripcion       : Procesa las BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_borec
  (
    psNumLote           in  varchar2,
    psCodEstaBR         in varchar2,
    psUsuario           in VARCHAR2

  ) IS

  CURSOR c_interfaz IS
    SELECT psNumLote      cod_regi_cabe_bore,
           cod_cabe_bore  num_bole_reco,
           cod_clie,
           to_char(sysdate,'YYYYMMDD') fec_reco,
           to_char(sysdate,'hh24mi')   hor_reco,
           ''             cod_moti,
           psCodEstaBR    cod_resu,
           ''             val_nomb_terc
      FROM REC_GTT_PROCE_BOREC;

  TYPE interfazrec IS RECORD(
    cod_regi int_rec_cabec_borec_tempo.cod_regi_cabe_bore%TYPE,
    num_bole int_rec_cabec_borec_tempo.num_bole_reco%TYPE,
    cod_clie int_rec_cabec_borec_tempo.cod_clie%TYPE,
    fec_reco int_rec_cabec_borec_tempo.fec_reco%TYPE,
    hor_reco int_rec_cabec_borec_tempo.hor_reco%TYPE,
    cod_moti int_rec_cabec_borec_tempo.cod_moti%TYPE,
    cod_resu int_rec_cabec_borec_tempo.cod_resu%TYPE,
    nom_terc int_rec_cabec_borec_tempo.val_nomb_terc%TYPE);

  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord  interfazrectab;
  lnnumreco       int_rec_cabec_borec.num_reco%TYPE;
  lnnumrecoant    int_rec_cabec_borec.num_reco%TYPE;
  lsoidestabore1  int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsoidestabore2  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lsoidmotnreco   int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  lnparamnumreco  int_rec_num_recoj_borec.num_reco_tota%TYPE;
  lnidestacerrado int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  ldfechacierre   int_rec_cabec_borec.fec_cierr%TYPE:=NULL;
  lsCodigoResultado int_rec_cabec_borec_tempo.cod_resu%TYPE;
  lsMotivoNORecojo   int_rec_motno_recoj_borec.cod_motn_reco_bore%TYPE;
  lnOidMotivoNoRecojo int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  ldFechaRecojo       int_rec_cabec_borec.fec_reco%TYPE;
  lsHoraRecojo        int_rec_cabec_borec.hor_reco%TYPE;
  lsCodigoRegistro    int_rec_cabec_borec.num_lote_devu%TYPE;
  lsNombreTercero     int_rec_cabec_borec.val_nomb_terc%TYPE;
  lsOidSoliCabeCCC    int_rec_cabec_borec.soca_oid_soli_cabe_cccc%TYPE;

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;

  lsRevisaBR NUMBER(10);

  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

    lsparametrofecha VARCHAR2(10);
    lsparametroconso sto_param_gener_occrr.val_param%TYPE;


  BEGIN

   SELECT to_char(SYSDATE,'dd/mm/yyyy') INTO lsparametrofecha FROM dual;

   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

   /*lsparametroconso := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_CONSOL');*/
   lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_TIPO_CALC_FACT'),'1');

  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'CE');

  SELECT num_reco_tota
    INTO lnparamnumreco
    FROM int_rec_num_recoj_borec
   WHERE cod_pais = pscodigopais;

  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP

        SELECT cb.num_reco,
               cb.esbo_oid_esta_bor1,
               cb.esbo_oid_esta_bor2,
               cb.more_oid_motn_reco_bore
          INTO lnnumreco,
               lsoidestabore1,
               lsoidestabore2,
               lsoidmotnreco
          FROM int_rec_cabec_borec cb
         WHERE val_nume_bore = interfazrecord(x).num_bole;

         lnnumrecoant := lnnumreco;

        --IF (lsoidestabore1 <> lnidestacerrado) THEN
          ldfechacierre := NULL;

          lsCodigoResultado :=interfazrecord(x).cod_resu;
          lsMotivoNORecojo  := interfazrecord(x).cod_moti;

          IF ( lsCodigoResultado= 'EX' OR lsCodigoResultado = 'CD'
               OR lsCodigoResultado = 'NE'
               OR (lsCodigoResultado = 'NX' AND lsMotivoNORecojo='05' )) THEN
            lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
            lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
            ldfechacierre  := trunc(SYSDATE);
          ELSE
            IF (lsCodigoResultado = 'NX') THEN
              IF (lnnumreco < lnparamnumreco) THEN
                lnnumreco      := 2;
                lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'GE');
                lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
              ELSE

                lsoidestabore2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,lsCodigoResultado);
                lsoidestabore1 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
                ldfechacierre  := trunc(SYSDATE);
                rec_pr_bloqu_consu(interfazrecord(x).cod_clie,'USUYOBEL');
              END IF;

            END IF;

          END IF;

          lnOidMotivoNoRecojo := int_pkg_recla.gen_fn_devue_oid_motno_borec(pscodigopais,lsMotivoNORecojo);
          ldFechaRecojo  := to_date(interfazrecord(x).fec_reco,'YYYYMMDD');
          lsHoraRecojo   := substr(interfazrecord(x).hor_reco,1, 2) || ':' ||substr(interfazrecord(x).hor_reco,3, 2);
          lsCodigoRegistro  := interfazrecord(x).cod_regi;
          lsNombreTercero  := interfazrecord(x).nom_terc;


          UPDATE int_rec_cabec_borec
             SET esbo_oid_esta_bor1      = lsoidestabore1,
                 esbo_oid_esta_bor2      = lsoidestabore2,
                 num_reco                = lnnumreco,
                 more_oid_motn_reco_bore = decode(num_reco, 1,lnOidMotivoNoRecojo,more_oid_motn_reco_bore),
                 fec_reco                = decode(num_reco, 1,ldFechaRecojo,fec_reco),
                 hor_reco                = decode(num_reco, 1,lsHoraRecojo,hor_reco),
                 fec_ulti_actu           = SYSDATE,
                 ind_regr_yobe           = decode(num_reco, 1, 1,ind_regr_yobe),
                 num_lote_devu           = decode(num_reco, 1,lsCodigoRegistro,num_lote_devu),
                 val_nomb_terc           = decode(num_reco, 1,lsNombreTercero,val_nomb_terc),
                 more_oid_motn_reco_bor2 = decode(num_reco, 2,lnOidMotivoNoRecojo,more_oid_motn_reco_bor2),
                 fec_rec2                = decode(num_reco, 2,ldFechaRecojo,fec_rec2),
                 hor_rec2                = decode(num_reco, 2,lsHoraRecojo, hor_rec2),
                 ind_regr_yob2           = decode(num_reco, 2, 1,ind_regr_yob2),
                 num_lote_dev2           = decode(num_reco, 2,lsCodigoRegistro,num_lote_dev2),
                 val_nomb_ter2           = decode(num_reco, 2,lsNombreTercero,val_nomb_ter2),
                 fec_cierr               = ldfechacierre
           WHERE val_nume_bore = interfazrecord(x).num_bole;

           ----- actualiza el detalle
           INT_PKG_RECLA.int_pr_rec_proce_borec_detal (psNumLote, interfazrecord(x).num_bole,pscodigopais);

           ----- Verifica si la BR se cerro en este proceso
           if lsoidestabore1 = 4 and
              ((lsoidestabore2 in (6, 7)) or lnOidMotivoNoRecojo = 6
                or lsoidmotnreco = 6) then

               ----- actualiza el saldo
               INT_PKG_RECLA.int_pr_calcu_abono_cargo_consu(pscodigopais,interfazrecord(x).num_bole);

               ----- Genera la solicitud de Cargo
               REC_PKG_PROCE.REC_PR_PROCE_APRUE_RECLA_DIGIT(pscodigopais,interfazrecord(x).num_bole, psUsuario);

               ---- Este Proceso de paso al SP REC_PR_PROCE_APRUE_RECLA_DIGIT

               /*
               ---- Obtiene el oid de la solicitud generada
               select SOCA_OID_SOLI_CABE_CCCC
               into lsOidSoliCabeCCC
               from int_rec_cabec_borec c
               WHERE c.cod_pais = pscodigopais
               AND c.cod_cabe_bore = interfazrecord(x).num_bole;

               ---- Ejecuta el cargo en linea
               if lsOidSoliCabeCCC > 0 then
                  sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(lsOidSoliCabeCCC,
                                                                     lsparametrofecha,
                                                                     pscodigopais,
                                                                     lsparametroconso,
                                                                     'C');
               end if;
               */

           end if;

           --- Nuevo proceso de abono pendiente
           INT_PKG_RECLA.int_pr_rec_proce_abono(interfazrecord(x).num_bole,psUsuario);

           ----- actualiza globa temporary
           UPDATE REC_GTT_PROCE_BOREC
           set FLAG_PROC = '1', DES_ERRO = 'OK'
           WHERE cod_cabe_bore = interfazrecord(x).num_bole;

           ----- actualiza auditoria
           insert into REC_AUDIT_BOREC
           (COD_CORR,
            NUM_LOTE,
            COD_CABE_BORE,
            NUM_RECO,
            COD_CLIE,
            COD_ESTA_BORE,
            FLAG_PROC,
            DES_ERRO,
            FEC_INGR,
            USU_INGR)
           values
           ( REC_SEQ_AUDIT_BOREC.NEXTVAL,
             psNumLote ,
             interfazrecord(x).num_bole,
             lnnumrecoant,
             interfazrecord(x).cod_clie,
             interfazrecord(x).cod_resu,
             1,
             '',
             sysdate,
             psUsuario
            );
        --END IF;
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR int_pr_rec_proce_borec  : ' || ls_sqlerrm);

  END int_pr_rec_proce_borec  ;

 /***************************************************************************
    Descripcion       : Anula la BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_borec_detal
  (
    psNumLote         in  varchar2,
    psNroBolRecojo    in  number,
    pscodigopais      in varchar2

  ) IS

  CURSOR c_interfaz(vnidestacerrado NUMBER) IS

    SELECT det.cod_pais,
           det.cod_cabe_bore,
           det.cod_line_bore,
           det.num_secu,
           det.care_oid_cabe_recl,
           det.soca_oid_soli_cabe,
           det.opre_oid_oper_recl,
           det.prod_oid_prod,
           det.cod_prod,
           det.mafa_oid_matr_fact,
           det.tofe_oid_tipo_ofer,
           det.copa_oid_para_gral,
           det.panp_oid_para_nive_prem,
           det.lopa_oid_lote_prem_arti,
           det.num_unid_recl,
           det.num_unid_reco,
           det.num_unid_elim,
           det.obse_bore,
           det.prod_oid_prod_disc,
           det.cod_prod_disc,
           det.ind_disc,
           det.lor_oid_line_oper_recl,
           det.ind_proc_alma_fisi,
           det.ind_envi_xero,
           det.ind_envi_yobe,
           det.ind_regr_yobe,
           det.ind_ocs_proc,
           det.cod_oper_homol,
           det.val_prec,
           det.val_prec_cont,
           det.num_lote_envi,
           det.num_lote_devu,
           det.des_oper,
           det.cod_oper,
           det.cod_anti,
           psNumLote         cod_regi_deta_bore,
           null              cod_prod_disc,
           det.num_unid_recl val_unid,
           cab.num_reco,
           cab.esbo_oid_esta_bor1,
           cab.esbo_oid_esta_bor2
      FROM int_rec_cabec_borec       cab,
           int_rec_linea_borec       det
     WHERE cab.cod_pais = det.cod_pais
       AND cab.cod_cabe_bore = det.cod_cabe_bore
       and cab.cod_cabe_bore = psNroBolRecojo
     ORDER BY det.cod_pais,
              det.cod_cabe_bore,
              det.cod_line_bore;

  TYPE interfazrec IS RECORD(
    cod_pais                int_rec_linea_borec.cod_pais%TYPE,
    cod_cabe_bore           int_rec_linea_borec.cod_cabe_bore%TYPE,
    cod_line_bore           int_rec_linea_borec.cod_line_bore%TYPE,
    num_secu                int_rec_linea_borec.num_secu%TYPE,
    care_oid_cabe_recl      int_rec_linea_borec.care_oid_cabe_recl%TYPE,
    soca_oid_soli_cabe      int_rec_linea_borec.soca_oid_soli_cabe%TYPE,
    opre_oid_oper_recl      int_rec_linea_borec.opre_oid_oper_recl%TYPE,
    prod_oid_prod           int_rec_linea_borec.prod_oid_prod%TYPE,
    cod_prod                int_rec_linea_borec.cod_prod%TYPE,
    mafa_oid_matr_fact      int_rec_linea_borec.mafa_oid_matr_fact%TYPE,
    tofe_oid_tipo_ofer      int_rec_linea_borec.tofe_oid_tipo_ofer%TYPE,
    copa_oid_para_gral      int_rec_linea_borec.copa_oid_para_gral%TYPE,
    panp_oid_para_nive_prem int_rec_linea_borec.panp_oid_para_nive_prem%TYPE,
    lopa_oid_lote_prem_arti int_rec_linea_borec.lopa_oid_lote_prem_arti%TYPE,
    num_unid_recl           int_rec_linea_borec.num_unid_recl%TYPE,
    num_unid_reco           int_rec_linea_borec.num_unid_reco%TYPE,
    num_unid_elim           int_rec_linea_borec.num_unid_elim%TYPE,
    obse_bore               int_rec_linea_borec.obse_bore%TYPE,
    prod_oid_prod_disc      int_rec_linea_borec.prod_oid_prod_disc%TYPE,
    cod_prod_disc           int_rec_linea_borec.cod_prod_disc%TYPE,
    ind_disc                int_rec_linea_borec.ind_disc%TYPE,
    lor_oid_line_oper_recl  int_rec_linea_borec.lor_oid_line_oper_recl%TYPE,
    ind_proc_alma_fisi      int_rec_linea_borec.ind_proc_alma_fisi%TYPE,
    ind_envi_xero           int_rec_linea_borec.ind_envi_xero%TYPE,
    ind_envi_yobe           int_rec_linea_borec.ind_envi_yobe%TYPE,
    ind_regr_yobe           int_rec_linea_borec.ind_regr_yobe%TYPE,
    ind_ocs_proc            int_rec_linea_borec.ind_ocs_proc%TYPE,
    cod_oper_homol          int_rec_linea_borec.cod_oper_homol%TYPE,
    val_prec                int_rec_linea_borec.val_prec%TYPE,
    val_prec_cont           int_rec_linea_borec.val_prec_cont%TYPE,
    num_lote_envi           int_rec_linea_borec.num_lote_envi%TYPE,
    num_lote_devu           int_rec_linea_borec.num_lote_devu%TYPE,
    des_oper                int_rec_linea_borec.des_oper%TYPE,
    cod_oper                int_rec_linea_borec.cod_oper%TYPE,
    cod_anti                int_rec_linea_borec.cod_anti%TYPE,
    cod_regi_deta_bore_arch int_rec_detal_borec_tempo.cod_regi_deta_bore%TYPE,
    cod_prod_disc_arch      int_rec_detal_borec_tempo.cod_prod_disc%TYPE,
    val_unid_arch           int_rec_detal_borec_tempo.val_unid%TYPE,
    num_reco                int_rec_cabec_borec.num_reco%TYPE,
    esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE,
    esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;

  lscodigoproductoddisc  VARCHAR2(20);
  lnoidproductodisc      NUMBER(12);
  lscodigoregistroant    VARCHAR2(100);
  lscodigoregistroact    VARCHAR2(100);
  lscodigocabeceraant    VARCHAR2(100);
  lscodigocabeceraact    VARCHAR2(100);
  lnnumunidadesrecototal NUMBER;
  lnnumunidadesreco      NUMBER;

  lnidestadogestion NUMBER;

  ln_lot2           NUMBER;
  ln_lote           NUMBER;
  ln_ind2           NUMBER;
  ln_ind1           NUMBER;

  lnidestacerrado int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lnidestacerrado2 int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;

BEGIN

  lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');
  lnidestacerrado2 := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'NX');

  --INICIALIZANDO VALORES
  lscodigoregistroant    := NULL;
  lscodigocabeceraant    := NULL;
  lnnumunidadesrecototal := 0;

  lnidestadogestion := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                  'GE');

  OPEN c_interfaz(lnidestacerrado);
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP

        lscodigocabeceraact := interfazrecord(x).cod_cabe_bore; --OBTENGO EL CODIGO DE CABECERA ACTUAL

        --CADA VEZ QUE SE TERMINE DE LEER UNA CABECERA ACTUALIZO EL tOTAL DE UNIDADES RECOGIDAS
        IF lscodigocabeceraant IS NOT NULL AND lscodigocabeceraact <> lscodigocabeceraant THEN

          UPDATE int_rec_cabec_borec
             SET num_tota_unid_reco = lnnumunidadesrecototal
           WHERE cod_pais = interfazrecord(x).cod_pais
             AND cod_cabe_bore = lscodigocabeceraant;

          int_pr_calcu_abono_cargo_consu(interfazrecord(x).cod_pais,lscodigocabeceraant);

          lnnumunidadesrecototal := 0;

        END IF;

        if interfazrecord(x).esbo_oid_esta_bor2 = lnidestacerrado2 then
           lnnumunidadesreco := 0;
        else
           lnnumunidadesreco := to_number(nvl(TRIM(interfazrecord(x).val_unid_arch),0)) -
                                to_number(nvl(TRIM(interfazrecord(x).num_unid_elim),0));

        end if;
        --Acumulo el numero de unidades;
        lnnumunidadesrecototal := lnnumunidadesrecototal + lnnumunidadesreco;

        --Obtenemo el codigo y Oid Del producto Discrepante
        lscodigoproductoddisc := TRIM(interfazrecord(x).cod_prod_disc_arch);
        lnoidproductodisc     := int_pkg_recla.gen_fn_devue_oid_produ_discr(lscodigoproductoddisc);

        --Identifico el codigo de registro
        lscodigoregistroact := interfazrecord(x).cod_pais || interfazrecord(x).cod_cabe_bore || interfazrecord(x).num_secu || interfazrecord(x).cod_prod;

        --Si el Codigo del producto discrepante del Detalle es nulo se actualiza los valores

        IF (lscodigoproductoddisc IS NULL) THEN

          IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
            UPDATE int_rec_linea_borec
               SET ind_regr_yobe      = 1, --IND_REGR_YOB
                   num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                   cod_prod_disc      = lscodigoproductoddisc,
                   num_unid_reco      = lnnumunidadesreco,
                   prod_oid_prod_disc = lnoidproductodisc
             WHERE cod_pais = interfazrecord(x).cod_pais
               AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
               AND cod_line_bore = interfazrecord(x).cod_line_bore;
          ELSE
            UPDATE int_rec_linea_borec
               SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                               1,
                                               1,
                                               ind_regr_yobe), --IND_REGR_YOB
                   num_lote_devu      = decode(interfazrecord(x).num_reco,
                                               1,
                                               interfazrecord(x).cod_regi_deta_bore_arch,
                                               num_lote_devu), --LOTE
                   ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                               2,
                                               1,
                                               ind_regr_yob2), --IND_REGR_YOB
                   num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                               2,
                                               interfazrecord(x).cod_regi_deta_bore_arch,
                                               num_lote_dev2), --LOTE
                   cod_prod_disc      = lscodigoproductoddisc,
                   num_unid_reco      = lnnumunidadesreco,
                   prod_oid_prod_disc = lnoidproductodisc
             WHERE cod_pais = interfazrecord(x).cod_pais
               AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
               AND cod_line_bore = interfazrecord(x).cod_line_bore;
          END IF;

        ELSE

          IF (lnnumunidadesreco < interfazrecord(x).num_unid_recl) THEN
            --Si es el primer registro
            IF lscodigoregistroant IS NULL OR lscodigoregistroant <> lscodigoregistroact THEN
              IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
                UPDATE int_rec_linea_borec
                   SET ind_regr_yobe      = 1, --IND_REGR_YOB
                       num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                       ind_disc           = 1,
                       cod_prod_disc      = lscodigoproductoddisc,
                       prod_oid_prod_disc = lnoidproductodisc,
                       num_unid_reco      = lnnumunidadesreco
                 WHERE cod_pais = interfazrecord(x).cod_pais
                   AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                   AND cod_line_bore = interfazrecord(x).cod_line_bore;
              ELSE
                UPDATE int_rec_linea_borec
                   SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                                   1,
                                                   1,
                                                   ind_regr_yobe), --IND_REGR_YOB
                       num_lote_devu      = decode(interfazrecord(x).num_reco,
                                                   1,
                                                   interfazrecord(x).cod_regi_deta_bore_arch,
                                                   num_lote_devu), --LOTE
                       ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                                   2,
                                                   1,
                                                   ind_regr_yob2), --IND_REGR_YOB
                       num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                                   2,
                                                   interfazrecord(x).cod_regi_deta_bore_arch,
                                                   num_lote_dev2), --LOTE
                       ind_disc           = 1,
                       cod_prod_disc      = lscodigoproductoddisc,
                       prod_oid_prod_disc = lnoidproductodisc,
                       num_unid_reco      = lnnumunidadesreco
                 WHERE cod_pais = interfazrecord(x).cod_pais
                   AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                   AND cod_line_bore = interfazrecord(x).cod_line_bore;
              END IF;

            ELSE

                ln_lot2 := NULL;
                ln_lote := NULL;
                ln_ind2 := NULL;
                ln_ind1 := 1;

              IF (interfazrecord(x).num_reco = 2) THEN

                    IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
                       ln_lot2 := NULL;
                       ln_lote := interfazrecord(x).cod_regi_deta_bore_arch;
                       ln_ind2 := NULL;
                    ELSE
                       ln_lot2 := interfazrecord(x).cod_regi_deta_bore_arch;
                       ln_lote := interfazrecord(x).num_lote_devu;
                       ln_ind2 := 1;
                    END IF;

              ELSE
                   ln_lot2 := NULL;
                   ln_lote := interfazrecord(x).cod_regi_deta_bore_arch;
                   ln_ind2 := NULL;
              END IF;


              INSERT INTO int_rec_linea_borec
                (cod_pais,
                 cod_cabe_bore,
                 cod_line_bore,
                 num_secu,
                 care_oid_cabe_recl,
                 soca_oid_soli_cabe,
                 opre_oid_oper_recl,
                 prod_oid_prod,
                 cod_prod,
                 mafa_oid_matr_fact,
                 tofe_oid_tipo_ofer,
                 copa_oid_para_gral,
                 panp_oid_para_nive_prem,
                 lopa_oid_lote_prem_arti,
                 num_unid_recl,
                 num_unid_reco,
                 obse_bore,
                 prod_oid_prod_disc,
                 cod_prod_disc,
                 ind_disc,
                 lor_oid_line_oper_recl,
                 ind_proc_alma_fisi,
                 ind_envi_xero,
                 ind_envi_yobe,
                 ind_regr_yobe,
                 ind_ocs_proc,
                 cod_oper_homol,
                 val_prec,
                 val_prec_cont,
                 num_lote_envi,
                 num_lote_devu,
                 des_oper,
                 cod_oper,
                 cod_anti,
                 refe_cod_line_bore,
                 ind_regr_yob2,
                 num_lote_dev2)
              VALUES
                (interfazrecord(x).cod_pais,
                 interfazrecord(x).cod_cabe_bore,
                 int_rlb_seq.NEXTVAL,
                 int_pkg_recla.gen_fn_sigse_linea_borec(interfazrecord(x).cod_pais,
                                                        interfazrecord(x).cod_cabe_bore),
                 interfazrecord(x).care_oid_cabe_recl,
                 interfazrecord(x).soca_oid_soli_cabe,
                 interfazrecord(x).opre_oid_oper_recl,
                 interfazrecord(x).prod_oid_prod,
                 interfazrecord(x).cod_prod,
                 interfazrecord(x).mafa_oid_matr_fact,
                 interfazrecord(x).tofe_oid_tipo_ofer,
                 interfazrecord(x).copa_oid_para_gral,
                 interfazrecord(x).panp_oid_para_nive_prem,
                 interfazrecord(x).lopa_oid_lote_prem_arti,
                 interfazrecord(x).num_unid_recl,
                 lnnumunidadesreco,
                 interfazrecord(x).obse_bore,
                 lnoidproductodisc,
                 lscodigoproductoddisc,
                 1,
                 interfazrecord(x).lor_oid_line_oper_recl,
                 interfazrecord(x).ind_proc_alma_fisi,
                 interfazrecord(x).ind_envi_xero,
                 interfazrecord(x).ind_envi_yobe,
                 ln_ind1,
                 interfazrecord(x).ind_ocs_proc,
                 interfazrecord(x).cod_oper_homol,
                 0, --INTERFAZRECORD(X).VAL_PREC,
                 0, --INTERFAZRECORD(X).VAL_PREC_CONT,
                 interfazrecord(x).num_lote_envi,
                 ln_lote,--interfazrecord(x).cod_regi_deta_bore_arch,
                 interfazrecord(x).des_oper,
                 interfazrecord(x).cod_oper,
                 interfazrecord(x).cod_anti,
                 interfazrecord(x).cod_line_bore,
                 ln_ind2,
                 ln_lot2); --LINEA DE REFERENCIA

            END IF;

          ELSE
            IF (interfazrecord(x).esbo_oid_esta_bor1 = lnidestadogestion) THEN
              UPDATE int_rec_linea_borec
                 SET ind_regr_yobe      = 1, --IND_REGR_YOB
                     num_lote_devu      = interfazrecord(x).cod_regi_deta_bore_arch, --LOTE
                     cod_prod_disc      = lscodigoproductoddisc,
                     prod_oid_prod_disc = lnoidproductodisc,
                     num_unid_reco      = lnnumunidadesreco,
                     ind_disc           = 1
               WHERE cod_pais = interfazrecord(x).cod_pais
                 AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                 AND cod_line_bore = interfazrecord(x).cod_line_bore;
            ELSE
              UPDATE int_rec_linea_borec
                 SET ind_regr_yobe      = decode(interfazrecord(x).num_reco,
                                                 1,
                                                 1,
                                                 ind_regr_yobe), --IND_REGR_YOB
                     num_lote_devu      = decode(interfazrecord(x).num_reco,
                                                 1,
                                                 interfazrecord(x).cod_regi_deta_bore_arch,
                                                 num_lote_devu), --LOTE
                     ind_regr_yob2      = decode(interfazrecord(x).num_reco,
                                                 2,
                                                 1,
                                                 ind_regr_yob2), --IND_REGR_YOB
                     num_lote_dev2      = decode(interfazrecord(x).num_reco,
                                                 2,
                                                 interfazrecord(x).cod_regi_deta_bore_arch,
                                                 num_lote_dev2), --LOTE
                     cod_prod_disc      = lscodigoproductoddisc,
                     prod_oid_prod_disc = lnoidproductodisc,
                     num_unid_reco      = lnnumunidadesreco,
                     ind_disc           = 1
               WHERE cod_pais = interfazrecord(x).cod_pais
                 AND cod_cabe_bore = interfazrecord(x).cod_cabe_bore
                 AND cod_line_bore = interfazrecord(x).cod_line_bore;
            END IF;
          END IF;
        END IF;
        lscodigoregistroant := lscodigoregistroact;
        lscodigocabeceraant := lscodigocabeceraact;

      END LOOP;

      IF lscodigocabeceraant IS NOT NULL AND lscodigocabeceraact = lscodigocabeceraant THEN

        UPDATE int_rec_cabec_borec
           SET num_tota_unid_reco = lnnumunidadesrecototal
         WHERE cod_pais = pscodigopais
           AND cod_cabe_bore = lscodigocabeceraant;

        lnnumunidadesrecototal := 0;

      END IF;

    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

EXCEPTION

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR int_pr_rec_proce_borec_detal: ' || ls_sqlerrm);

  END int_pr_rec_proce_borec_detal ;

 /***************************************************************************
    Descripcion       : Realiza la validacion de BR
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_valid_borec
  (
    psNroBolRecojo    in  number,
    pmensaje          out varchar2,
    psNroRecojo       out varchar2,
    psCodClie         out varchar2,
    psNomClie         out varchar2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;

  lsNumreco        int_rec_cabec_borec.num_reco%TYPE;
  lsCodclie        int_rec_cabec_borec.cod_clie%TYPE;
  lsEstadoBR       int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsEstadoBR2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lsNomclie        varchar2(100);
  lnidestacerrado int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;

  lnoidmotivo     int_rec_cabec_borec.MORE_OID_MOTN_RECO_BORE%TYPE;
  lnenviyob2      int_rec_cabec_borec.IND_ENVI_YOB2%TYPE;

  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

  BEGIN

   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

   lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                'CE');

   pmensaje  := null;

   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
      BEGIN

        select br.num_reco , br.cod_clie,
               gen_pkg_gener.GEN_FN_CLIEN_DATOS(br.cod_clie, 'NOM_CLIE') nom_clie, 
               esbo_oid_esta_bor1, esbo_oid_esta_bor2,MORE_OID_MOTN_RECO_BORE,
               IND_ENVI_YOB2
          into lsNumreco, lsCodclie, lsNomclie, lsEstadoBR, lsEstadoBR2,lnoidmotivo,lnenviyob2
          from int_rec_cabec_borec br
        where br.cod_cabe_bore = psNroBolRecojo;

      EXCEPTION
        WHEN no_data_found THEN
            lsNumreco  := 0;
            lsEstadoBR := 0;
            lsEstadoBR2 := 0;
            lsCodclie  := null;
            lsNomclie  := null;
            pmensaje   := 'No existe Boleta de Recojo';
      END;

   if lsEstadoBR = lnidestacerrado then
      pmensaje  := 'Boleta de Recojo ya fue cerrada';
      
   else
     --- Si fue gestionada como no exitosa con diferencia de unidades y aun no se genera 
     --- no peude cerrarse por masivo
     if lsEstadoBR = 3 and lsEstadoBR2 = 6 and lnoidmotivo = 6 and 
        ( lnenviyob2 is null  or  lnenviyob2 = 0) then
        pmensaje  := 'Boleta de Recojo gestionada manualmente, debe esperar a su generacion';
     end if;
   end if;

    psNroRecojo := lsNumreco;
    psCodClie   := lsCodclie;
    psNomClie   := lsNomclie;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR int_pr_rec_valid_borec   : ' || ls_sqlerrm);

  END int_pr_rec_valid_borec   ;

/**************************************************************************
  Descripcion        : Procesa el cargo por Reversion de CDR
  Fecha Creacion     : 01/02/2013
  Autor              : Sandro Quintana
 ***************************************************************************/
PROCEDURE int_pr_rec_proce_rever_cdr
(
  pscodigopais     VARCHAR2,
  pscodigocabecera VARCHAR2,
  psNuevaSolicitud out VARCHAR2
) IS

  CURSOR c_detalle IS

      select
           psp.prod_oid_prod            prod_oid_prod_deta,
           psp.val_prec_cata_unit_loca  val_prec_cata_unit_loca_deta,
           psp.val_prec_cont_unit_loca  val_prec_cont_unit_loca_deta,
           abs(psp.num_unid_aten)       unit_total,
           VAL_PORC_DESC                ,
           VAL_CODI_VENT                ,
           OFDE_OID_DETA_OFER           ,
           VAL_CODI_VENT_FICT
      from ped_solic_posic psp
      where psp.soca_oid_soli_cabe = pscodigocabecera;

  v_oid_soli_cabe           int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;

  v_cod_pais                int_rec_cabec_borec.cod_pais%TYPE;
  v_cod_cabe_bore           int_rec_cabec_borec.cod_cabe_bore%TYPE;
  v_soca_oid_soli_cabe      int_rec_cabec_borec.soca_oid_soli_cabe%TYPE;
  v_val_nume_bole_desp      int_rec_cabec_borec.val_nume_bole_desp%TYPE;
  v_esbo_oid_esta_bor1      int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  v_esbo_oid_esta_bor2      int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  v_num_reco                int_rec_cabec_borec.num_reco%TYPE;
  v_clie_oid_clie           int_rec_cabec_borec.clie_oid_clie%TYPE;
  v_cod_clie                int_rec_cabec_borec.cod_clie%TYPE;
  v_ztad_oid_terri_admin    int_rec_cabec_borec.ztad_oid_terri_admin%TYPE;
  v_cod_regi                int_rec_cabec_borec.cod_regi%TYPE;
  v_cod_zona                int_rec_cabec_borec.cod_zona%TYPE;
  v_cod_secc                int_rec_cabec_borec.cod_secc%TYPE;
  v_cod_terr                int_rec_cabec_borec.cod_terr%TYPE;
  v_more_oid_motn_reco_bore int_rec_cabec_borec.more_oid_motn_reco_bore%TYPE;
  v_fec_ingr                int_rec_cabec_borec.fec_ingr%TYPE;
  v_fec_prog_reco           int_rec_cabec_borec.fec_prog_reco%TYPE;
  v_fec_reco                int_rec_cabec_borec.fec_reco%TYPE;
  v_hor_reco                int_rec_cabec_borec.hor_reco%TYPE;
  v_fec_ulti_actu           int_rec_cabec_borec.fec_ulti_actu%TYPE;
  v_val_nume_bore           int_rec_cabec_borec.val_nume_bore%TYPE;
  v_num_tota_unid_recl      int_rec_cabec_borec.num_tota_unid_recl%TYPE;
  v_num_tota_unid_reco      int_rec_cabec_borec.num_tota_unid_reco%TYPE;
  v_ind_envi_xero           int_rec_cabec_borec.ind_envi_xero%TYPE;
  v_ind_envi_yobe           int_rec_cabec_borec.ind_envi_yobe%TYPE;
  v_ind_regr_yobe           int_rec_cabec_borec.ind_regr_yobe%TYPE;
  v_ind_chk_auto            int_rec_cabec_borec.ind_chk_auto%TYPE;
  v_ind_ocs_proc            int_rec_cabec_borec.ind_ocs_proc%TYPE;
  v_ind_proc_alma_fisi      int_rec_cabec_borec.ind_proc_alma_fisi%TYPE;
  v_num_lote_envi           int_rec_cabec_borec.num_lote_envi%TYPE;
  v_num_lote_devu           int_rec_cabec_borec.num_lote_devu%TYPE;
  v_val_nomb_terc           int_rec_cabec_borec.val_nomb_terc%TYPE;
  v_cod_peri_proc           int_rec_cabec_borec.cod_peri_proc%TYPE;
  v_ind_envi_xer2           int_rec_cabec_borec.ind_envi_xer2%TYPE;
  v_ind_envi_yob2           int_rec_cabec_borec.ind_envi_yob2%TYPE;
  v_ind_regr_yob2           int_rec_cabec_borec.ind_regr_yob2%TYPE;
  v_num_lote_env2           int_rec_cabec_borec.num_lote_env2%TYPE;
  v_num_lote_dev2           int_rec_cabec_borec.num_lote_dev2%TYPE;
  v_fec_ing2                int_rec_cabec_borec.fec_ing2%TYPE;
  v_fec_rec2                int_rec_cabec_borec.fec_rec2%TYPE;
  v_hor_rec2                int_rec_cabec_borec.hor_rec2%TYPE;
  v_more_oid_motn_reco_bor2 int_rec_cabec_borec.more_oid_motn_reco_bor2%TYPE;
  v_val_nomb_ter2           int_rec_cabec_borec.val_nomb_ter2%TYPE;

  v_cldi_oid_clie_dire      mae_clien_direc.oid_clie_dire%TYPE;
  v_ztad_oid_terr_admi      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
  v_terr_oid_terr           zon_terri_admin.terr_oid_terr%TYPE;
  v_zzon_oid_zona           zon_secci.zzon_oid_zona%TYPE;
  v_tdoc_oid_tipo_docu      mae_clien_ident.tdoc_oid_tipo_docu%TYPE;
  v_tido_oid_tipo_docu      mae_tipo_docum.tido_oid_tipo_docu%TYPE;
  v_ticl_oid_tipo_clie      mae_clien_tipo_subti.ticl_oid_tipo_clie %TYPE;
  v_sbti_oid_subt_clie      mae_clien_tipo_subti.sbti_oid_subt_clie%TYPE;
  v_vepo_oid_valo_estr_geop zon_terri.vepo_oid_valo_estr_geop%TYPE;

  v_fopa_oid_form_pago           ped_tipo_solic_pais.fopa_oid_form_pago%TYPE;
  v_ind_perm_unio_sol            ped_tipo_solic_pais.ind_perm_unio%TYPE;
  v_soci_oid_soci                ped_tipo_solic_pais.soci_oid_soci%TYPE;
  v_tspa_oid_tipo_soli_pais_cons ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE;
  v_val_glos_obse                ped_tipo_solic_pais.val_glos%TYPE;
  v_ind_pedi_prue                ped_tipo_solic_pais.ind_pedi_prue%TYPE;
  v_mone_oid_mone                ped_tipo_solic_pais.mone_oid_mone%TYPE;
  v_acfi_oid_acce_fisi           ped_acces_fisic.oid_acce_fisi%TYPE;
  v_sbac_oid_sbac                ped_tipo_solic.sbac_oid_sbac%TYPE;
  v_clso_oid_clas_soli           ped_tipo_solic.clso_oid_clas_soli%TYPE;
  v_oid_alma                     ped_tipo_solic_pais.almc_oid_alma%TYPE;
  v_ind_orde_comp                ped_clase_solic.ind_orde_comp%TYPE;
  v_ind_soli_nega                ped_tipo_solic.ind_soli_nega%TYPE;
  v_oid_tipo_soli_pais           ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  v_almc_oid_alma                ped_tipo_solic_pais.almc_oid_alma%TYPE;
  --v_VAL_PORC_DESC                ped_solic_posic.VAL_PORC_DESC%TYPE;
  --v_VAL_CODI_VENT                ped_solic_posic.VAL_CODI_VENT%TYPE;
  --v_OFDE_OID_DETA_OFER           ped_solic_posic.OFDE_OID_DETA_OFER%TYPE;
  --v_VAL_CODI_VENT_FICT           ped_solic_posic.VAL_CODI_VENT_FICT%TYPE;

  v_tido_oid_tipo_doc2           mae_tipo_docum.tido_oid_tipo_docu%TYPE;

  v_fec_prog_fact cra_perio.fec_fina%TYPE;
  v_perd_oid_peri cra_perio.oid_peri%TYPE;

  v_prod_oid_prod mae_produ.oid_prod%TYPE;

  v_val_prec_cata_unit_loca int_rec_linea_borec.val_prec%TYPE;
  v_val_prec_cont_unit_loca int_rec_linea_borec.val_prec%TYPE;

   TYPE t_prod_oid_prod_deta IS TABLE OF int_rec_linea_borec.PROD_OID_PROD%TYPE;
   TYPE t_val_prec_cata_unit_loca_deta IS TABLE OF int_rec_linea_borec.VAL_PREC%TYPE;
   TYPE t_val_prec_cont_unit_loca_deta IS TABLE OF int_rec_linea_borec.VAL_PREC%TYPE;
   TYPE t_unit_total IS TABLE OF int_rec_linea_borec.NUM_UNID_RECL%TYPE;

   TYPE t_VAL_PORC_DESC       IS TABLE OF ped_solic_posic.VAL_PORC_DESC%TYPE;
   TYPE t_VAL_CODI_VENT       IS TABLE OF ped_solic_posic.VAL_CODI_VENT%TYPE;
   TYPE t_OFDE_OID_DETA_OFER  IS TABLE OF ped_solic_posic.OFDE_OID_DETA_OFER%TYPE;
   TYPE t_VAL_CODI_VENT_FICT  IS TABLE OF ped_solic_posic.VAL_CODI_VENT_FICT%TYPE;

   v_prod_oid_prod_deta            			 t_prod_oid_prod_deta;
   v_val_prec_cata_unit_loca_deta            t_val_prec_cata_unit_loca_deta;
   v_val_prec_cont_unit_loca_deta            t_val_prec_cont_unit_loca_deta;
   v_unit_total            					 t_unit_total;

  v_VAL_PORC_DESC                t_VAL_PORC_DESC;
  v_VAL_CODI_VENT                t_VAL_CODI_VENT;
  v_OFDE_OID_DETA_OFER           t_OFDE_OID_DETA_OFER;
  v_VAL_CODI_VENT_FICT           t_VAL_CODI_VENT_FICT;


  lnoidsolicabe ped_solic_cabec.oid_soli_cabe%TYPE;


  lnIdPais NUMBER;

      lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;
  lsvalanio 				ped_numer_solic.VAL_ANIO%TYPE;
  rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    lscod_posi number;

BEGIN

  SELECT soca_oid_docu_refe
    INTO v_oid_soli_cabe
    FROM ped_solic_cabec psc
   WHERE psc.oid_soli_cabe = pscodigocabecera;

  SELECT
         clie_oid_clie,
         cod_clie
    INTO
         v_clie_oid_clie,
         v_cod_clie
    FROM ped_solic_cabec psc, mae_clien mae
   WHERE psc.clie_oid_clie = mae.oid_clie
     AND psc.oid_soli_cabe = pscodigocabecera;

  SELECT m.cod_clie                   v_cod_clie,
         m.oid_clie                   v_clie_oid_clie,
         d.oid_clie_dire              v_cldi_oid_clie_dire,
         ad.ztad_oid_terr_admi        v_ztad_oid_terr_admi,
         ztad.terr_oid_terr           v_terr_oid_terr,
         zs.zzon_oid_zona             v_zzon_oid_zona,
         ide.tdoc_oid_tipo_docu       v_tdoc_oid_tipo_docu,
         mt.tido_oid_tipo_docu        v_tido_oid_tipo_docu,
         ms.ticl_oid_tipo_clie        v_ticl_oid_tipo_clie,
         ms.sbti_oid_subt_clie        v_sbti_oid_subt_clie,
         terr.vepo_oid_valo_estr_geop v_vepo_oid_valo_estr_geop
    INTO v_cod_clie,
         v_clie_oid_clie,
         v_cldi_oid_clie_dire,
         v_ztad_oid_terr_admi,
         v_terr_oid_terr,
         v_zzon_oid_zona,
         v_tdoc_oid_tipo_docu,
         v_tido_oid_tipo_docu,
         v_ticl_oid_tipo_clie,
         v_sbti_oid_subt_clie,
         v_vepo_oid_valo_estr_geop
    FROM mae_clien             m,
         mae_clien_direc       d,
         mae_clien_unida_admin ad,
         mae_clien_ident       ide,
         zon_terri_admin       ztad,
         zon_terri             terr,
         zon_secci             zs,
         mae_tipo_docum        mt,
         mae_clien_tipo_subti  ms
   WHERE m.oid_clie = d.clie_oid_clie
     AND m.oid_clie = ad.clie_oid_clie
     AND m.oid_clie = ide.clie_oid_clie
     AND ztad.oid_terr_admi = ad.ztad_oid_terr_admi
     AND ztad.zscc_oid_secc = zs.oid_secc
     AND mt.oid_tipo_docu = ide.tdoc_oid_tipo_docu
     AND ide.val_iden_docu_prin = 1
     AND ad.perd_oid_peri_fin IS NULL
     AND d.ind_dire_ppal = 1
     AND d.ind_elim = 0
     AND m.oid_clie = v_clie_oid_clie -- oid de cliente se recupera de la cabecera de la boleta de recojo
     AND ms.clie_oid_clie = m.oid_clie
     AND ms.ind_ppal = 1
     AND terr.oid_terr = ztad.terr_oid_terr;

  SELECT tsp.fopa_oid_form_pago v_fopa_oid_form_pago,
         tsp.ind_perm_unio      v_ind_perm_unio_sol,
         tsp.soci_oid_soci      v_soci_oid_soci,
         tsp.tsol_oid_tipo_cons v_tspa_oid_tipo_soli_pais_cons,
         tsp.val_glos           v_val_glos_obse,
         tsp.ind_pedi_prue      v_ind_pedi_prue,
         tsp.mone_oid_mone      v_mone_oid_mone,
         NULL                   v_acfi_oid_acce_fisi,
         ts.sbac_oid_sbac       v_sbac_oid_sbac,
         ts.clso_oid_clas_soli  v_clso_oid_clas_soli,
         tsp.almc_oid_alma      v_oid_alma,
         cs.ind_orde_comp       v_ind_orde_comp,
         ts.ind_soli_nega       v_ind_soli_nega,
         tsp.oid_tipo_soli_pais v_oid_tipo_soli_pais,
         tsp.almc_oid_alma      v_almc_oid_alma,
         tsp.tido_oid_tipo_docu v_tido_oid_tipo_docu
    INTO v_fopa_oid_form_pago,
         v_ind_perm_unio_sol,
         v_soci_oid_soci,
         v_tspa_oid_tipo_soli_pais_cons,
         v_val_glos_obse,
         v_ind_pedi_prue,
         v_mone_oid_mone,
         v_acfi_oid_acce_fisi,
         v_sbac_oid_sbac,
         v_clso_oid_clas_soli,
         v_oid_alma,
         v_ind_orde_comp,
         v_ind_soli_nega,
         v_oid_tipo_soli_pais,
         v_almc_oid_alma,
         v_tido_oid_tipo_doc2
    FROM ped_tipo_solic_pais tsp,
         ped_tipo_solic      ts,
         ped_clase_solic     cs
   WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
     AND ts.clso_oid_clas_soli = cs.oid_clas_soli
     AND ts.cod_tipo_soli = 'CCEC';

  SELECT fec_fina v_fec_prog_fact,
         oid_peri v_perd_oid_peri
    INTO v_fec_prog_fact,
         v_perd_oid_peri
    FROM cra_perio
   WHERE trunc(SYSDATE) <= fec_fina
     AND trunc(SYSDATE) >= fec_inic
     AND rownum = 1;

  IF v_tido_oid_tipo_doc2 IS NOT NULL THEN
     v_tido_oid_tipo_docu := v_tido_oid_tipo_doc2;
  END IF;

  SELECT ped_soca_seq.NEXTVAL
              INTO lnoidsolicabe
              FROM dual;


  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

/*  lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000');
*/

    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              1);


  lnnumsoliformat := to_char(SYSDATE,
                   'YY') || lpad(lnnumsoliinicio,
                                8,
                                '0');

  lnnumsoliformat := lnnumsoliformat + 1;

  lsvalanio		  := to_char(to_number(to_char(SYSDATE,
                                                'YY')));
 lscod_posi := 1;

 psNuevaSolicitud := lnoidsolicabe;

  INSERT INTO ped_solic_cabec
    (oid_soli_cabe,
     fec_prog_fact,
     fec_fact,
     num_clien,
     tspa_oid_tipo_soli_pais,
     tids_oid_tipo_desp,
     almc_oid_alma,
     modu_oid_modu,
     ticl_oid_tipo_clie,
     perd_oid_peri,
     soca_oid_soli_cabe,
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
     val_usua,
     val_tasa_impu,
     fec_cron,
     ind_perm_unio_sol,
     val_tipo_camb,
     num_docu_orig,
     val_base_flet_loca,
     val_impo_flet_loca,
     val_impo_flet_tota_loca,
     val_impo_flet_sin_impu_tota,
     val_reca_flet_loca,
     val_otro_reca_loca,
     val_tota_paga_loca,
     val_prec_cata_tota_loca,
     val_prec_cata_sin_impu_tota,
     val_prec_fact_tota_loca,
     val_impo_impu_tota_loca,
     val_impo_desc_1_tota_loca,
     val_impo_desc_1_tota_docu,
     val_impo_desc_1_sin_impu_tota,
     val_impo_desc_3_tota_docu,
     val_impo_desc_3_sin_impu_tota,
     val_impo_desc_tota_loca,
     val_impo_dto_1_sin_imp_tot_loc,
     val_impo_redo_loca,
     val_base_flet_docu,
     val_impo_flet_docu,
     val_impo_desc_tota_docu,
     val_impo_flet_sin_impu_docu,
     val_reca_flet_docu,
     val_otro_reca_docu,
     val_tota_flet_docu,
     val_impo_flet_tota_docu,
     val_tota_flet_loca,
     val_tota_paga_docu,
     val_prec_cata_tota_docu,
     val_prec_cata_sin_impu_tota_do,
     val_prec_cont_tota_loca,
     val_prec_cont_sin_impu_tota,
     val_prec_cont_sin_impu_tota_1,
     val_prec_fact_tota_docu,
     val_prec_cata_tota_loc_uni_dem,
     val_prec_neto_tota_docu,
     val_prec_neto_tota_loca,
     val_impo_impu_tota_docu,
     val_impo_redo_docu,
     val_impo_redo_cons_loca,
     val_impo_redo_cons_docu,
     ind_oc,
     ind_pedi_prue,
     ind_ts_no_conso,
     val_glos_obse,
     val_impo_desc_3_tota_loca,
     val_impo_dto_3_sin_imp_tot_loc,
     pais_oid_pais,
     tido_oid_tipo_docu,
     vepo_oid_valo_estr_geop,
     esso_oid_esta_soli,
     grpr_oid_grup_proc,
     sbti_oid_subt_clie,
     acfi_oid_acce_fisi,
     tspa_oid_tipo_soli_pais_cons,
     fopa_oid_form_pago,
     clso_oid_clas_soli,
     ztad_oid_terr_admi,
     oper_oid_oper,
     proc_oid_proc,
     ind_inte_lari_gene,
     fec_prog_fact_comp,
     soca_oid_docu_refe)
  VALUES
    (lnoidsolicabe,--seq_oid_soli_cabe,
     v_fec_prog_fact,
     NULL,
     0,
     v_oid_tipo_soli_pais,---2001,--v_tspa_oid_tipo_soli_pais,
     3,--v_tids_oid_tipo_desp, --:3
     v_almc_oid_alma,
     27,--v_modu_oid_modu, --:27
     v_ticl_oid_tipo_clie,
     v_perd_oid_peri,
     NULL,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_clie_oid_clie,
     v_cldi_oid_clie_dire,
     v_tdoc_oid_tipo_docu,
     v_soci_oid_soci,
     v_sbac_oid_sbac,
     v_terr_oid_terr,
     v_zzon_oid_zona,
     lnnumsoliformat,-- + 1,--seq_val_nume_soli,
     NULL,--v_val_usua,
     0,
     trunc(SYSDATE),
     1,
     1,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     0,
     1,
     'OPERACION SUJETA A PERCEPCION DEL IGV',
     0,
     0,
     lnIdPais,--v_pais_oid_pais,
     v_tido_oid_tipo_docu,
     v_vepo_oid_valo_estr_geop,
     1,
     4,
     v_sbti_oid_subt_clie,
     1,
     v_tspa_oid_tipo_soli_pais_cons,
     v_fopa_oid_form_pago,
     v_clso_oid_clas_soli,
     v_ztad_oid_terr_admi,
     44,--v_oper_oid_oper, --:44
     6,--v_proc_oid_proc, --:6
     0,
     to_char(SYSDATE, 'yyyymmdd'),   --:--v_fec_prog_fact_comp
     v_oid_soli_cabe);

 OPEN c_detalle;
    LOOP
      FETCH c_detalle BULK COLLECT
        INTO v_prod_oid_prod_deta, v_val_prec_cata_unit_loca_deta,
             v_val_prec_cont_unit_loca_deta,v_unit_total,
             v_VAL_PORC_DESC, v_VAL_CODI_VENT, v_OFDE_OID_DETA_OFER, v_VAL_CODI_VENT_FICT
      LIMIT rows;

      IF v_prod_oid_prod_deta.COUNT > 0 THEN


        FOR i IN v_prod_oid_prod_deta.FIRST .. v_prod_oid_prod_deta.LAST
        LOOP

			INSERT INTO ped_solic_posic
		    (oid_soli_posi,
		     cod_posi,
		     num_unid_dema,
		     num_unid_por_aten,
		     val_tasa_impu,
		     soca_oid_soli_cabe,
		     taim_oid_tasa_impu,
		     tpos_oid_tipo_posi,
		     prod_oid_prod,
		     val_prec_cata_unit_loca,
		     val_prec_cont_unit_loca,
		     val_prec_cata_unit_docu,
		     val_prec_conta_unit_docu,
		     val_prec_fact_unit_loca,
		     val_prec_fact_unit_docu,
		     val_prec_sin_impu_unit_loca,
		     val_prec_sin_impu_unit_docu,
		     val_prec_sin_impu_tota_docu,
		     val_impo_desc_unit_loca,
		     val_prec_neto_unit_loca,
		     val_prec_neto_tota_docu,
		     val_prec_neto_unit_docu,
		     val_prec_tota_tota_loca,
		     val_prec_tota_tota_docu,
		     val_impo_impu_unit_loca,
		     val_impo_impu_unit_docu,
		     val_impo_desc_tota_docu,
		     val_impo_impu_tota_loca,
		     val_impo_impu_tota_docu,
		     val_impo_desc_tota_loca,
		     val_prec_tota_unit_loca,
		     val_prec_tota_unit_docu,
		     val_prec_cont_tota_loca,
		     val_prec_cata_tota_loca,
		     val_prec_cata_tota_docu,
		     val_prec_cont_tota_docu,
		     val_prec_cata_tota_loca_unid,
		     num_unid_dema_real,
		     num_unid_compr,
		     num_unid_aten,
		     val_prec_fact_tota_loca,
		     val_prec_fact_tota_docu,
		     val_prec_sin_impu_tota_loca,
		     val_prec_neto_tota_loca,
		     espo_oid_esta_posi,
		     stpo_oid_subt_posi,
		     ind_no_impr,
		     num_cons,
		     val_impo_des_sin_imp_unit_loca,
		     val_impo_des_sin_imp_unit_docu,
		     val_impo_des_sin_imp_tota,
		     val_impo_des_sin_imp_tota_docu,
         val_codi_vent,
         VAL_CODI_VENT_FICT,
         VAL_PORC_DESC,
         OFDE_OID_DETA_OFER)
		  VALUES
		    (ped_sopo_seq.NEXTVAL,--seq_oid_soli_posi,
		     lscod_posi,
		     v_unit_total(i),
		     v_unit_total(i),
		     NULL,
		     lnoidsolicabe,--seq_oid_soli_cabe,
		     NULL,
		     11,--v_tpos_oid_tipo_posi, --:11
		     v_prod_oid_prod_deta(i),
		     v_val_prec_cata_unit_loca_deta(i),
		     v_val_prec_cont_unit_loca_deta(i),  ---  0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     0,
		     v_unit_total(i),
		     v_unit_total(i),
		     v_unit_total(i),
		     0,
		     0,
		     0,
		     0,
		     4,--v_espo_oid_esta_posi, --:4
		     15,--v_stpo_oid_subt_posi, --:15
		     0,
		     NULL,
		     0,
		     0,
		     0,
		     0,
         v_VAL_CODI_VENT(i),
         v_VAL_CODI_VENT_FICT(i),
         v_VAL_PORC_DESC(i),
         v_OFDE_OID_DETA_OFER(i));

         lscod_posi := lscod_posi+1;
        END LOOP;
      END IF;
      EXIT WHEN c_detalle%NOTFOUND;
    END LOOP;
    CLOSE c_detalle;

/*     UPDATE ped_numer_solic ns
           SET ns.val_ulti_nume_soli = lnnumsoliinicio+1
         WHERE ns.val_oper = 'PED001'
           AND ns.val_anio = lsvalanio
           AND ns.cod_suba = '000'
           AND ns.cod_pais = pscodigopais;
*/

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR int_pr_rec_proce_rever_cdr: ' ||
                             ls_sqlerrm);

END int_pr_rec_proce_rever_cdr;

 /***************************************************************************
    Descripcion       : Abono en proceso
    Fecha Creacion    : 20/12/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE int_pr_rec_proce_abono
  (
    psNroBolRecojo    in  number,
    psUsuario         in VARCHAR2

  ) IS

    CURSOR c_cursor4 IS
        select DISTINCT rso.soca_oid_soli_cabe oid_soli_cabe,
                        rso.opre_oid_oper_recl oid_oper_recl,
                        lb.care_oid_cabe_recl  oid_cabe_recl
        from int_rec_cabec_borec cb,
             int_rec_linea_borec lb,
             rec_linea_opera_recla rlo,
             rec_solic_opera rso,
             ped_solic_cabec psc,
             ped_tipo_solic_pais ptsp,
             ped_tipo_solic pts
        where CB.COD_CABE_BORE = LB.COD_CABE_BORE
        and LB.LOR_OID_LINE_OPER_RECL = RLO.OID_LINE_OPER_RECL
        and LB.OPRE_OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and psc.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
        AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
        AND pts.ind_soli_nega = 1
        and RLO.TIMO_OID_TIPO_MOVI = 2
        and PSC.GRPR_OID_GRUP_PROC = 3
        and cB.COD_CABE_BORE = psNroBolRecojo
        ORDER BY rso.soca_oid_soli_cabe, rso.opre_oid_oper_recl, lb.care_oid_cabe_recl ;

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;

  lscodclie        int_rec_cabec_borec.cod_clie%TYPE;
  lsoidclie        int_rec_cabec_borec.clie_oid_clie%TYPE;
  lsoidestabore1   int_rec_cabec_borec.esbo_oid_esta_bor1%TYPE;
  lsoidestabore2   int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestacerrado  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestaanulado  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestaexitoso  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lnidestanoexitoso  int_rec_cabec_borec.esbo_oid_esta_bor2%TYPE;
  lsindenvixerox1  int_rec_cabec_borec.ind_envi_xero%TYPE;
  lsindenvixerox2  int_rec_cabec_borec.ind_envi_xer2%TYPE;
  lnnumreco        int_rec_cabec_borec.num_reco%TYPE;
  lsnumlote        int_rec_cabec_borec.num_lote_envi%TYPE;

    lsparametropunta sto_param_gener_occrr.val_param%TYPE;
    lsparametroconso sto_param_gener_occrr.val_param%TYPE;
    lsparametrobolec sto_param_gener_occrr.val_param%TYPE;
    lsparametroabonoBR sto_param_gener_occrr.val_param%TYPE;
    lsparametrofecha VARCHAR2(10);
    varoidsolicabe                NUMBER;
    r_enviado NUMBER;
    r_total   NUMBER;
    w_filas                       NUMBER := 1000;

  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

    TYPE registrorec4 IS RECORD(
      oid_soli_cabe rec_cabec_recla.soca_oid_soli_cabe%TYPE,
      oid_oper_recl rec_solic_opera.soca_oid_soli_cabe%TYPE,
      oid_cabe_recl int_rec_linea_borec.care_oid_cabe_recl%TYPE);
    TYPE registrorectab4 IS TABLE OF registrorec4;
    registrorecord4 registrorectab4;


  BEGIN


   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

    lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_TIPO_CALC_FACT'),'1');
    lsparametropunta := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_DESPUNT');

    lsparametroabonoBR := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_ABONO_BR'),'X');

    if lsparametroabonoBR = 'P' or lsparametroabonoBR = 'C' then
          SELECT to_char(SYSDATE, 'dd/mm/yyyy') INTO lsparametrofecha FROM dual;

        lnidestacerrado := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'CE');

        lnidestanoexitoso := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'NX');

        lnidestaexitoso := int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,'EX');

         ----- Recupera datos de la BR
          SELECT cb.num_reco,
                 cb.esbo_oid_esta_bor1,
                 cb.esbo_oid_esta_bor2,
                 cb.ind_envi_xero,
                 cb.ind_envi_xer2,
                 cb.cod_clie,
                 mae.oid_clie
            INTO lnnumreco,
                 lsoidestabore1,
                 lsoidestabore2,
                 lsindenvixerox1,
                 lsindenvixerox2,
                 lscodclie,
                 lsoidclie
            FROM int_rec_cabec_borec cb,
                 mae_clien mae
           WHERE cb.cod_clie = mae.cod_clie
             and cb.cod_cabe_bore = psNroBolRecojo
             AND cb.cod_pais      = pscodigopais ;

          ---IF lsparametroconso is not null THEN

          ---- PROCESA Boleta de recojo exitosa
          ---- Procesa los CDRs y actualiza los status del CDR
          if lsoidestabore1 = lnidestacerrado and lsoidestabore2 = lnidestaexitoso then
            OPEN c_cursor4;
            LOOP
              FETCH c_cursor4 BULK COLLECT
                INTO registrorecord4 LIMIT w_filas;
              IF registrorecord4.count > 0 THEN
                FOR c4 IN registrorecord4.first .. registrorecord4.last
                LOOP
                  varoidsolicabe := registrorecord4(c4).oid_soli_cabe;

                  sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(varoidsolicabe,
                                                                     lsparametrofecha,
                                                                     pscodigopais,
                                                                     lsparametroconso,
                                                                     'A');

                  --- Actualizar estado del rec_opera_recla
                  SELECT COUNT(0)
                    INTO r_enviado
                    FROM rec_solic_opera a,
                         ped_solic_cabec b,
                         rec_solic_opera c
                   WHERE a.opre_oid_oper_recl = c.opre_oid_oper_recl
                     AND c.soca_oid_soli_cabe = b.oid_soli_cabe
                     AND a.soca_oid_soli_cabe = varoidsolicabe
                     AND b.fec_fact IS NULL;

                  IF r_enviado = 0 THEN

                    UPDATE rec_opera_recla b
                       SET b.esop_oid_esta_oper = 5
                     WHERE b.oid_oper_recl IN
                           (SELECT opre_oid_oper_recl
                              FROM rec_solic_opera a
                             WHERE a.soca_oid_soli_cabe = varoidsolicabe);

                  END IF;

                  --- Descuenta puntaje
                  IF lsparametropunta = '1' THEN
                    inc_pkg_proce_incen.inc_pr_calcu_punta_consu(pscodigopais,
                                                                 varoidsolicabe,
                                                                 psusuario);
                  END IF;

                  ---- Actualiza estado en rec_cabec_recla si todas fueron facturadas
                  SELECT COUNT(1)
                    INTO r_enviado
                    FROM rec_opera_recla o
                   WHERE o.care_oid_cabe_recl = registrorecord4(c4).oid_cabe_recl
                     AND o.esop_oid_esta_oper = 5;

                  SELECT COUNT(1)
                    INTO r_total
                    FROM rec_opera_recla
                   WHERE care_oid_cabe_recl = registrorecord4(c4).oid_cabe_recl;

                  IF r_total = r_enviado THEN
                    UPDATE rec_cabec_recla
                       SET esre_oid_esta_recl = 4,
                           fec_ulti_actu     =
                           (SELECT SYSDATE FROM dual)
                     WHERE oid_cabe_recl = registrorecord4(c4).oid_cabe_recl;
                  END IF;

                END LOOP;
              END IF;
              EXIT WHEN c_cursor4%NOTFOUND;
            END LOOP;
            CLOSE c_cursor4;


          END IF;

          ---- PROCESA Boleta de recojo NO exitosa
          ---- Las solicites de PED las borra y actualiza los status del CDR
          if lsoidestabore1 = lnidestacerrado and lsoidestabore2 = lnidestanoexitoso then
            OPEN c_cursor4;
            LOOP
              FETCH c_cursor4 BULK COLLECT
                INTO registrorecord4 LIMIT w_filas;
              IF registrorecord4.count > 0 THEN
                FOR c4 IN registrorecord4.first .. registrorecord4.last
                LOOP
                    --- se marca como BR no exitosa en el reclamo
                    UPDATE rec_opera_recla b
                       SET b.esop_oid_esta_oper = 4
                     WHERE b.oid_oper_recl = registrorecord4(c4).oid_oper_recl;

                    UPDATE rec_cabec_recla
                       SET esre_oid_esta_recl = 3,
                           fec_ulti_actu     =
                           (SELECT SYSDATE FROM dual)
                     WHERE oid_cabe_recl = registrorecord4(c4).oid_cabe_recl;

                    --- elimina la solicitud generada en PED
                    DELETE FROM ped_solic_posic
                     WHERE soca_oid_soli_cabe = registrorecord4(c4).oid_soli_cabe;

                    DELETE FROM rec_solic_opera
                     WHERE opre_oid_oper_recl = registrorecord4(c4).oid_oper_recl
                       AND soca_oid_soli_cabe = registrorecord4(c4).oid_soli_cabe;

                    DELETE FROM ped_solic_cabec
                     WHERE oid_soli_cabe = registrorecord4(c4).oid_soli_cabe;

                END LOOP;
              END IF;
              EXIT WHEN c_cursor4%NOTFOUND;
            END LOOP;
            CLOSE c_cursor4;

            --- Actualiza BR
            update int_rec_cabec_borec cbr
               set cbr.ind_ocs_proc = 'R', cbr.imp_cargo = 0, cbr.imp_abono = 0
            where cbr.cod_cabe_bore = psNroBolRecojo;

          END IF;

            --- Actualizar saldo de abono pendiente
            UPDATE mae_clien SET VAL_RECL_PEND = REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(lsoidclie,null)
            WHERE oid_clie = lsoidclie;

     END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR int_pr_rec_proce_abono : ' || ls_sqlerrm);

  END int_pr_rec_proce_abono ;


  /*****************************************/
  /*            GEN_FN_PED_BR                              */
  /*  Devuelve el valor a grabar cada vez que genera una atencion */
  /* valor nullo o 666 si no se toma en cuenta en el proceso de cierre */
  /*******************************************/

  FUNCTION GEN_FN_PED_BR( psClienteOid IN NUMBER)
  RETURN VARCHAR2
  IS
   ls_valor  VARCHAR2(5) := null;

    lncuentaUA        NUMBER := 0;

    lsindgenebr       VARCHAR2(1);
    lsindgenebrUA     VARCHAR2(1);
    lsindgenebrOK     VARCHAR2(1);

    lscodpais  bas_ctrl_fact.cod_pais%TYPE;
    lscodzona  INT_REC_GENE_BOREC.cod_zona%TYPE;
    lscodregi  INT_REC_GENE_BOREC.cod_regi%TYPE;

  BEGIN

    ---- Codigo Pais
    SELECT MAX(cod_pais) INTO lscodpais FROM bas_ctrl_fact WHERE rownum = 1;

    lsindgenebr    := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR');
    lsindgenebrUA  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR_UA'),'0');

   if lsindgenebr = '1' then


      --- Si no se procesa las UA, todos los registros se dan como OK
      if lsindgenebrUA = '0' then
         ls_valor := '666';
      end if;

       ---- Proceso para Colombia
       ---- Si se genera como colombia y todo esta ok con las zonas se valida
       ---- si no trabaja como antes.
        ---- valida indicador si se encuentra en tabla UA
         if lsindgenebrUA = '1' then

            select cli.cod_regi, cli.cod_zona
              into lscodregi, lscodzona
              from V_MAE_CLIE_UNIDA_ADMIN  CLI
              where CLI.OID_CLIE  = psClienteOid and cli.ind_acti = 1;

            --- valida la UA
            select count(*) into lncuentaUA from INT_REC_GENE_BOREC
            where cod_pais = lscodpais
            and (cod_regi = lscodregi  or
                 cod_zona = lscodzona)
            and ind_reg = '1';
            if lncuentaUA > 0 then
                ls_valor := '666';
            end if;
         end if;
      end if;

   return ls_valor;

  END GEN_FN_PED_BR;

/**************************************************************************
  Descripcion        : elimina atenciones inteligentes pasadas
  Fecha Creacion     : 26/02/2007
  Autor              : Leonardo Lizana
  Fecha Modificacion : 06/06/2008
  Autor              : Jose Cairampoma
 ***************************************************************************/
PROCEDURE int_pr_rec_elimi_aten_inte 
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigomarca   VARCHAR2,
  pscodigocanal   VARCHAR2
) IS

  w_filas               NUMBER := 100000;
  lb_clienteold         NUMBER(12) := NULL;
  lb_boletaold          NUMBER(12) := NULL;
  lb_boletacurr         NUMBER(12) := NULL;
  lb_lineacurr          NUMBER(12) := NULL;
  lb_num_recojo         NUMBER(4);
  lb_num_sec            NUMBER(4);
  lb_unidades_reclamdas NUMBER(10) := 0;

  --PARAMETROS DE CURSOR
  lnoidmarca   NUMBER;
  lnoidcanal   NUMBER;
  lnoidperiodo NUMBER;
  lnoidperiodo1 NUMBER;
  lnoidperiodo2 NUMBER;
  lnoidperiodoEI NUMBER;
  lnoidperiodoEF NUMBER;


  lngrupofacturacion     NUMBER;
  lnoidestreclafacturado NUMBER;
  lnoidestreclaenviado   NUMBER;
  lnoidestoperafacturado NUMBER;
  lnoidestoperaenviado   NUMBER;
  lnoidtipocierra        NUMBER;
  lnoidtipocierrar       NUMBER;
  lnoidtiposolipaisoc    NUMBER;
  lnoidtiposolipaisex    NUMBER;

  lncuenta                 NUMBER := 0;
  lncuentacierrezonaregion NUMBER := 0;
  lbindicadorinsert        BOOLEAN := TRUE;

    lscodpais        VARCHAR2(15);
    lsactuacronobr   VARCHAR2(1);
    lscodigoperiodo  VARCHAR2(6);
    lscodigoperiodo1  VARCHAR2(6);
    lscodigoperiodo2  VARCHAR2(6);
    lscodigoperiodoBR VARCHAR2(6);
    lscodigoperiodoEI VARCHAR2(6);
    lscodigoperiodoEF VARCHAR2(6);
    lsindtsolgenbr     VARCHAR2(1);
    lsindgenebr       VARCHAR2(1);
    lsindgenebrUA     VARCHAR2(1);
    lsindgenebrOK     VARCHAR2(1);
    lncuentaUA        NUMBER := 0;
    lncantmaxBR  NUMBER;
    lncamparaBR  NUMBER;
    lnpregenBR   NUMBER;
    lncuentaBR        NUMBER := 0;
    lncuentaATEN      NUMBER := 0;
    lncuentaATENPRE   NUMBER := 0;

    lsnroregact        NUMBER := 0;
    lb_clientebr       NUMBER(12) := NULL;
    lsindpremio        VARCHAR2(1);
    lsindanulaBR       VARCHAR2(1);
    lsindSinPedBR      VARCHAR2(1);
    lsindanulaBRold    VARCHAR2(1);
    lsindSinPedBRold   VARCHAR2(1);
    lsvarmensaje       VARCHAR2(50);

  lsnumlote        int_rec_cabec_borec.num_lote_envi%TYPE;


BEGIN

   -- Actualiza Cronograma de Boleta de Recojo segun el pais

    lscodpais := pscodigopais;
    lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_ACTUA_CRONO_BR');
    lsindgenebr    := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_IND_GENE_BR');


  --INICIALIZANDO PARAMETROS DE CURSOR
  lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
  lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                             lnoidmarca,
                                                             lnoidcanal);

  lngrupofacturacion := int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5');

   select to_char(sysdate,'YYYYMMDD') into lsnumlote from dual;


    IF lsactuacronobr = '1' or
       lsactuacronobr = '2' or
       lsactuacronobr = '3' or
       lsactuacronobr = '4' THEN
      --- Obtiene el periodo
      SELECT MIN(cod_peri)INTO lscodigoperiodo FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0' AND c.ind_camp_act = '1';

      --- Obtiene periodos para controlar la nueva forma de trabajo de colombia
      lscodigoperiodo1  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-1);
      lscodigoperiodo2  := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-2);
      lscodigoperiodoEF := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-3);
      lscodigoperiodoEI := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,-10);
      lscodigoperiodoBR := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lscodigoperiodo,lncamparaBR*-1);

      if lsindgenebr = '1' then
         lnoidperiodo1 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo1);
         lnoidperiodo2 := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodo2);
         lnoidperiodoEI := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodoEI);
         lnoidperiodoEF := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,lscodigoperiodoEF);
      else
         lnoidperiodo1 := lnoidperiodo;
         lnoidperiodo2 := lnoidperiodo;
         lnoidperiodoEI := lnoidperiodo;
         lnoidperiodoEF := lnoidperiodo;
      end if;
    END IF;


   if lsindgenebr = '1' then  --- solo hace esto para colombia

      -- Elimina las atenciones en tablas PED
      delete from ped_solic_posic psp
      where psp.soca_oid_soli_cabe in(
      select psc.oid_soli_cabe from ped_solic_cabec psc
      where PSC.FEC_FACT is null
      and PSC.IND_OC = 0
      AND psc.fec_repo_falt IS NULL
      and psc.modu_oid_modu <> 23
      and PSC.PERD_OID_PERI between lnoidperiodoEI and lnoidperiodoEF);

      delete from rec_solic_opera rso
      where rso.soca_oid_soli_cabe in(
      select psc.oid_soli_cabe from ped_solic_cabec psc
      where PSC.FEC_FACT is null
      and PSC.IND_OC = 0
      AND psc.fec_repo_falt IS NULL
      and psc.modu_oid_modu <> 23
      and PSC.PERD_OID_PERI between lnoidperiodoEI and lnoidperiodoEF);

      delete ped_solic_cabec psc
      where PSC.FEC_FACT is null
      and PSC.IND_OC = 0
      AND psc.fec_repo_falt IS NULL
      and psc.modu_oid_modu <> 23
      and PSC.PERD_OID_PERI between lnoidperiodoEI and lnoidperiodoEF;
   end if  ;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR int_pr_rec_elimi_aten_inte: ' ||
                             ls_sqlerrm);

END int_pr_rec_elimi_aten_inte;


  /*****************************************/
  /*            gen_fn_to_date                              */
  /*  Valida y devuelvela fecha de acuedoal formato     */
  /* psFecha     = es la fecha char               */
  /* psFormatoF  = formato de fecha               */
  /* psHora      = es la hora char               */
  /* psFormatoH  = si es de 12/24 horas               */
  /*******************************************/

  FUNCTION gen_fn_to_date( 
    psFecha     IN VARCHAR2,
    psFormatoF  IN VARCHAR2,
    psHora      IN VARCHAR2,
    psFormatoH  IN VARCHAR2    
  )
  RETURN date
  IS
   lv_fecha  date;

  BEGIN

    -- valida la fecha para actualizar
    BEGIN
       SELECT TO_DATE(psfecha || ' '|| pshora, 
                      psFormatoF || ' ' || 'HH' || psFormatoH || ':MI:SS' ) 
              into lv_fecha FROM dual;

    EXCEPTION
      WHEN OTHERS THEN

        BEGIN
         SELECT TO_DATE(psfecha, psFormatoF) into lv_fecha FROM dual;

        EXCEPTION
          WHEN OTHERS THEN
            lv_fecha := null;
        END;    
    END;    

   return lv_fecha;

  END gen_fn_to_date;


END INT_PKG_RECLA;
/
